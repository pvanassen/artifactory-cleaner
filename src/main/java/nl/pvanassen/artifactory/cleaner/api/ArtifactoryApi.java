/**
 * 
 */
package nl.pvanassen.artifactory.cleaner.api;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 * @author Paul van Assen
 */
public class ArtifactoryApi {
    private final String host;
    private final String path;
    private final Client client;

    public ArtifactoryApi(String host, String path, String user, String pass) {
        super();
        this.host = host;
        this.path = path;
        client = ClientBuilder.newClient();
        client.register(HttpAuthenticationFeature.basic(user, pass));
    }

    public FileInfo getFileInfo(String pathInRepository) {
        WebTarget resource = getService(host);
        return resource.path("api").path("storage").path(path).path(pathInRepository)
                .request(MediaType.APPLICATION_JSON).get(FileInfo.class);
    }

    public FolderInfo getFolderInfo(String pathInRepository) {
        WebTarget resource = getService(host);
        return resource.path("api").path("storage").path(path).path(pathInRepository)
                .request(MediaType.APPLICATION_JSON).get(FolderInfo.class);
    }

    public Stats getStats(String pathInRepository) {
        WebTarget resource = getService(host);
        return resource.path("api").path("storage").path(path).path(pathInRepository)
                .request(MediaType.APPLICATION_JSON).get(Stats.class);
    }

    private WebTarget getService(String baseUri) {
        return client.target(baseUri);
    }

    /**
     * Remove a path
     * 
     * @param path Path within the repository to remove
     */
    public void delete(String path) {
        WebTarget resource = getService(host);
        resource.path(this.path).path(path).request().delete();
    }
}
