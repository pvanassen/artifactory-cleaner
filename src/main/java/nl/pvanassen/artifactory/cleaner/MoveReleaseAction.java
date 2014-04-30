package nl.pvanassen.artifactory.cleaner;

import java.util.concurrent.RecursiveTask;

import nl.pvanassen.artifactory.cleaner.api.ArtifactoryApi;
import nl.pvanassen.artifactory.cleaner.api.FileInfo;
import nl.pvanassen.artifactory.cleaner.api.Stats;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MoveReleaseAction extends RecursiveTask<Boolean> {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String file;
    private final ArtifactoryApi artifactoryApi;
    private final DateTime cutoff;

    MoveReleaseAction(String file, ArtifactoryApi artifactoryApi, ArtifactoryApi targetApi, DateTime cutoff) {
        super();
        this.file = file;
        this.artifactoryApi = artifactoryApi;
        this.cutoff = cutoff;
    }

    /**
     * Returns false if the child has been removed (no more children) or true if there is still
     * something left {@inheritDoc}
     * 
     * @see java.util.concurrent.RecursiveTask#compute()
     */
    @Override
    protected Boolean compute() {
        FileInfo fileInfo = artifactoryApi.getFileInfo(file);
        if (fileInfo.getCreatedDate().isAfter(cutoff)) {
            return Boolean.TRUE;
        }
        Stats stats = artifactoryApi.getStats(file);
        if (stats.getDownloadCount() > 0 && stats.getLastDownloadedDate().isAfter(cutoff)) {
            return Boolean.TRUE;
        }
        logger.info("Removing " + stats.getUri());
        artifactoryApi.delete(fileInfo.getPath());
        return Boolean.FALSE;
    }

}
