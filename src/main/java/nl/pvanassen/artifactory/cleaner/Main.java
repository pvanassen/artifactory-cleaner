package nl.pvanassen.artifactory.cleaner;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

import nl.pvanassen.artifactory.cleaner.api.ArtifactoryApi;

import org.joda.time.DateTime;
import org.joda.time.Weeks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry point for the cleaner
 * @author Paul van Assen
 *
 */
public class Main {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * @param args
     * @throws ExecutionException 
     * @throws InterruptedException 
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        new Main();

    }

    private Main() throws IOException, InterruptedException, ExecutionException {
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream("/cleaner.properties"));
        String host = properties.getProperty( "snapshot.repository.host" );
        String path = properties.getProperty( "snapshot.repository.path" );
        String user = properties.getProperty( "snapshot.repository.user" );
        String pass = properties.getProperty( "snapshot.repository.pass" );
        final ArtifactoryApi snapshotApi = new ArtifactoryApi(host, path, user, pass);
        final DateTime cutoff = DateTime.now().minus(Weeks.TWO);
        RemovalActionFactory factory = new RemovalActionFactory (){
            @Override
            public RecursiveTask<Boolean> getFileTask(String path) {
                return new RemovalAction(path, snapshotApi, cutoff);
            }
        };
        ForkJoinPool pool = new ForkJoinPool(8);
        RecursiveTask<Boolean> task = new PathWalkerAction("", snapshotApi, factory);
        Future<?> statsListFuture = pool.submit(task);
        pool.shutdown();
        while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
            logger.info(pool.toString());
        }
        statsListFuture.get();
    }
}
