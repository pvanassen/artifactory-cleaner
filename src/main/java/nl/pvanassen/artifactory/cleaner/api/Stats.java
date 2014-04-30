package nl.pvanassen.artifactory.cleaner.api;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats {
    private String uri;
    private String lastDownloaded;
    private int downloadCount;
    private String lastDownloadedBy;
    private DateTime lastDownloadedDate;

    public String getUri() {
        return uri;
    }

    void setUri(String uri) {
        this.uri = uri;
    }

    public String getLastDownloaded() {
        return lastDownloaded;
    }

    public DateTime getLastDownloadedDate() {
        return lastDownloadedDate;
    }

    void setLastDownloaded(String lastDownloaded) {
        this.lastDownloadedDate = new DateTime(Long.valueOf(lastDownloaded).longValue(), DateTimeZone.UTC);
        this.lastDownloaded = lastDownloaded;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getLastDownloadedBy() {
        return lastDownloadedBy;
    }

    void setLastDownloadedBy(String lastDownloadedBy) {
        this.lastDownloadedBy = lastDownloadedBy;
    }

}
