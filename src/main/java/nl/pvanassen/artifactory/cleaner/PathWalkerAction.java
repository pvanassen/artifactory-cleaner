package nl.pvanassen.artifactory.cleaner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import nl.pvanassen.artifactory.cleaner.api.ArtifactoryApi;
import nl.pvanassen.artifactory.cleaner.api.FolderInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathWalkerAction extends RecursiveTask<Boolean> {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String path;
    private final ArtifactoryApi artifactoryApi;
    private final RemovalActionFactory factory;

    public PathWalkerAction(String path, ArtifactoryApi artifactoryApi, RemovalActionFactory factory) {
        this.path = path;
        this.artifactoryApi = artifactoryApi;
        this.factory = factory;
    }

    /**
     * Returns true if the folder still has children
     * Returns false if all the children are empty {@inheritDoc}
     * 
     * @see java.util.concurrent.RecursiveTask#compute()
     */
    @Override
    protected Boolean compute() {
        logger.info("Going into " + path);
        List<ForkJoinTask<Boolean>> tasks = new LinkedList<>();
        int fork = 0;
        FolderInfo folderInfo = artifactoryApi.getFolderInfo(path);
        if ("_system_".equals(folderInfo.getCreatedBy())) {
            return Boolean.TRUE;
        }
        if (folderInfo.getChildren().length == 0) {
            // Remove folder, done
            logger.info("Removing " + folderInfo.getUri());
            artifactoryApi.delete(folderInfo.getPath());
            return Boolean.FALSE;
        }
        List<Boolean> stillHasChildrenList = new ArrayList<>(folderInfo.getChildren().length);
        for (FolderInfo.Children child : folderInfo.getChildren()) {
            if (child.getFolder().startsWith(".")) {
                continue;
            }
            if ("true".equals(child.getFolder())) {
                RecursiveTask<Boolean> action = new PathWalkerAction(path + child.getUri(),
                        artifactoryApi, factory);
                if (fork <= 2) {
                    tasks.add(action.fork());
                }
                else {
                    stillHasChildrenList.add(action.invoke());
                }
                fork++;
            }
            else {
                tasks.add(factory.getFileTask(path + child.getUri()).fork());
            }
        }
        for (ForkJoinTask<Boolean> task : tasks) {
            stillHasChildrenList.add(task.join());
        }
        for (Boolean b : stillHasChildrenList) {
            if (b == Boolean.TRUE) {
                return Boolean.TRUE;
            }
        }
        logger.info("Removing " + folderInfo.getUri());
        artifactoryApi.delete(folderInfo.getPath());
        return Boolean.FALSE;
    }
}
