/**
 * 
 */
package nl.pvanassen.artifactory.cleaner.api;

import java.io.IOException;
import java.util.Properties;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

/**
 * @author Paul van Assen
 */
public class ArtifactoryApi {
    private final Properties properties;

    public ArtifactoryApi() {
        properties = new Properties();
        try {
            properties.load( getClass().getResourceAsStream( "cleaner.properties" ) );
        }
        catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FileInfo getFileInfo( String repository, String pathInRepository ) {
        String host = properties.getProperty( repository + "repository.host" );
        String path = properties.getProperty( repository + "repository.path" );
        String user = properties.getProperty( repository + "repository.user" );
        String pass = properties.getProperty( repository + "repository.pass" );
        WebResource resource = getService( user, pass, host );
        return resource.path( "api" ).path( "storage" ).path( path ).path( pathInRepository ).accept( MediaType.APPLICATION_JSON ).get( FileInfo.class );
    }

    public FolderInfo getFolderInfo( String repository, String pathInRepository ) {
        String host = properties.getProperty( repository + "repository.host" );
        String path = properties.getProperty( repository + "repository.path" );
        String user = properties.getProperty( repository + "repository.user" );
        String pass = properties.getProperty( repository + "repository.pass" );
        WebResource resource = getService( user, pass, host );
        return resource.path( "api" ).path( "storage" ).path( path ).path( pathInRepository ).accept( MediaType.APPLICATION_JSON ).get( FolderInfo.class );
    }

    public Stats getStats( String repository, String pathInRepository ) {
        String host = properties.getProperty( repository + "repository.host" );
        String path = properties.getProperty( repository + "repository.path" );
        String user = properties.getProperty( repository + "repository.user" );
        String pass = properties.getProperty( repository + "repository.pass" );
        WebResource resource = getService( user, pass, host );
        return resource.path( "api" ).path( "storage" ).path( path ).path( pathInRepository ).accept( MediaType.APPLICATION_JSON ).get( Stats.class );
    }

    private WebResource getService( String user, String pass, String baseUri ) {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create( config );
        client.addFilter( new HTTPBasicAuthFilter( user, pass ) );
        return client.resource( baseUri );
    }
}
