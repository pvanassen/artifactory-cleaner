package nl.pvanassen.artifactory.cleaner;

import java.util.concurrent.RecursiveTask;

/**
 * Interface for building the removal action.
 * This will allow switching between removal for snapshots and moving for releases
 * 
 * @author Paul van Assen
 */
public interface RemovalActionFactory {
    /**
     * 
     * @param path Path to handle
     * @return Task to handle the file
     */
    RecursiveTask<Boolean> getFileTask(String path);
}
