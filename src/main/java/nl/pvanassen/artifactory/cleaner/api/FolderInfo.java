package nl.pvanassen.artifactory.cleaner.api;

import java.util.Arrays;

public class FolderInfo {

    public static final class Children {
        private String uri;
        private String folder;

        public String getUri() {
            return uri;
        }

        void setUri( String uri ) {
            this.uri = uri;
        }

        public String getFolder() {
            return folder;
        }

        void setFolder( String folder ) {
            this.folder = folder;
        }

        @Override
        public String toString() {
            return "Children [uri=" + uri + ", folder=" + folder + "]";
        }

    }

    private String     uri;
    private String     repo;
    private String     path;
    private String     created;
    private String     createdBy;
    private String     lastModified;
    private String     modifiedBy;
    private String     lastUpdated;
    private Children[] children;

    public String getUri() {
        return uri;
    }

    void setUri( String uri ) {
        this.uri = uri;
    }

    public String getRepo() {
        return repo;
    }

    void setRepo( String repo ) {
        this.repo = repo;
    }

    public String getCreated() {
        return created;
    }

    void setCreated( String created ) {
        this.created = created;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    void setCreatedBy( String createdBy ) {
        this.createdBy = createdBy;
    }

    public String getLastModified() {
        return lastModified;
    }

    void setLastModified( String lastModified ) {
        this.lastModified = lastModified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    void setModifiedBy( String modifiedBy ) {
        this.modifiedBy = modifiedBy;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    void setLastUpdated( String lastUpdated ) {
        this.lastUpdated = lastUpdated;
    }

    public Children[] getChildren() {
        return children;
    }

    void setChildren( Children[] children ) {
        this.children = children;
    }

    public String getPath() {
        return path;
    }

    void setPath( String path ) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "FolderInfo [uri=" + uri + ", repo=" + repo + ", path=" + path + ", created=" + created + ", createdBy=" + createdBy + ", lastModified=" + lastModified + ", modifiedBy=" + modifiedBy + ", lastUpdated=" + lastUpdated + ", children=" + Arrays.toString( children ) + "]";
    }

}
