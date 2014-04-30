package nl.pvanassen.artifactory.cleaner.api;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FileInfo {

    public static class Checksums {

        private String md5;
        private String sha1;

        public String getMd5() {
            return md5;
        }

        void setMd5( String md5 ) {
            this.md5 = md5;
        }

        public String getSha1() {
            return sha1;
        }

        void setSha1( String sha1 ) {
            this.sha1 = sha1;
        }

    }

    private String    uri;
    private String    downloadUri;
    private String    repo;
    private String    path;
    private String    remoteUrl;
    private String    created;
    private DateTime  createdDate;
    private String    createdBy;
    private String    lastModified;
    private String    modifiedBy;
    private String    lastUpdated;
    private String    size;
    private String    mimeType;
    private Checksums checksums;
    private Checksums originalChecksums;

    public String getUri() {
        return uri;
    }

    void setUri( String uri ) {
        this.uri = uri;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    void setDownloadUri( String downloadUri ) {
        this.downloadUri = downloadUri;
    }

    public String getRepo() {
        return repo;
    }

    void setRepo( String repo ) {
        this.repo = repo;
    }

    public String getPath() {
        return path;
    }

    void setPath( String path ) {
        this.path = path;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    void setRemoteUrl( String remoteUrl ) {
        this.remoteUrl = remoteUrl;
    }

    public String getCreated() {
        return created;
    }

    void setCreated( String created ) {
        // 2013-10-18T10:26:51.223Z
        this.createdDate = ISODateTimeFormat.dateTime().parseDateTime( created );
        // this.createdDate = new DateTime(Long.valueOf(created).longValue(), DateTimeZone.UTC);
        this.created = created;
    }

    public DateTime getCreatedDate() {
        return createdDate;
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

    public String getSize() {
        return size;
    }

    void setSize( String size ) {
        this.size = size;
    }

    public String getMimeType() {
        return mimeType;
    }

    void setMimeType( String mimeType ) {
        this.mimeType = mimeType;
    }

    public Checksums getChecksums() {
        return checksums;
    }

    void setChecksums( Checksums checksums ) {
        this.checksums = checksums;
    }

    public Checksums getOriginalChecksums() {
        return originalChecksums;
    }

    void setOriginalChecksums( Checksums originalChecksums ) {
        this.originalChecksums = originalChecksums;
    }

    @Override
    public String toString() {
        return "FileInfo [uri=" + uri + ", downloadUri=" + downloadUri + ", repo=" + repo + ", path=" + path + ", remoteUrl=" + remoteUrl + ", created=" + created + ", createdBy=" + createdBy + ", lastModified=" + lastModified + ", modifiedBy=" + modifiedBy + ", lastUpdated=" + lastUpdated + ", size=" + size + ", mimeType=" + mimeType + ", checksums=" + checksums + ", originalChecksums=" + originalChecksums + "]";
    }

}
