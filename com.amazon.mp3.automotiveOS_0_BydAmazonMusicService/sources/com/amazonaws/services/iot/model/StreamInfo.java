package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class StreamInfo implements Serializable {
    private Date createdAt;
    private String description;
    private List<StreamFile> files;
    private Date lastUpdatedAt;
    private String roleArn;
    private String streamArn;
    private String streamId;
    private Integer streamVersion;

    public String getStreamId() {
        return this.streamId;
    }

    public void setStreamId(String str) {
        this.streamId = str;
    }

    public StreamInfo withStreamId(String str) {
        this.streamId = str;
        return this;
    }

    public String getStreamArn() {
        return this.streamArn;
    }

    public void setStreamArn(String str) {
        this.streamArn = str;
    }

    public StreamInfo withStreamArn(String str) {
        this.streamArn = str;
        return this;
    }

    public Integer getStreamVersion() {
        return this.streamVersion;
    }

    public void setStreamVersion(Integer num) {
        this.streamVersion = num;
    }

    public StreamInfo withStreamVersion(Integer num) {
        this.streamVersion = num;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public StreamInfo withDescription(String str) {
        this.description = str;
        return this;
    }

    public List<StreamFile> getFiles() {
        return this.files;
    }

    public void setFiles(Collection<StreamFile> collection) {
        if (collection == null) {
            this.files = null;
        } else {
            this.files = new ArrayList(collection);
        }
    }

    public StreamInfo withFiles(StreamFile... streamFileArr) {
        if (getFiles() == null) {
            this.files = new ArrayList(streamFileArr.length);
        }
        for (StreamFile streamFile : streamFileArr) {
            this.files.add(streamFile);
        }
        return this;
    }

    public StreamInfo withFiles(Collection<StreamFile> collection) {
        setFiles(collection);
        return this;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    public StreamInfo withCreatedAt(Date date) {
        this.createdAt = date;
        return this;
    }

    public Date getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
    }

    public StreamInfo withLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public StreamInfo withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getStreamId() != null) {
            sb.append("streamId: " + getStreamId() + ",");
        }
        if (getStreamArn() != null) {
            sb.append("streamArn: " + getStreamArn() + ",");
        }
        if (getStreamVersion() != null) {
            sb.append("streamVersion: " + getStreamVersion() + ",");
        }
        if (getDescription() != null) {
            sb.append("description: " + getDescription() + ",");
        }
        if (getFiles() != null) {
            sb.append("files: " + getFiles() + ",");
        }
        if (getCreatedAt() != null) {
            sb.append("createdAt: " + getCreatedAt() + ",");
        }
        if (getLastUpdatedAt() != null) {
            sb.append("lastUpdatedAt: " + getLastUpdatedAt() + ",");
        }
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((getStreamId() == null ? 0 : getStreamId().hashCode()) + 31) * 31) + (getStreamArn() == null ? 0 : getStreamArn().hashCode())) * 31) + (getStreamVersion() == null ? 0 : getStreamVersion().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getFiles() == null ? 0 : getFiles().hashCode())) * 31) + (getCreatedAt() == null ? 0 : getCreatedAt().hashCode())) * 31) + (getLastUpdatedAt() == null ? 0 : getLastUpdatedAt().hashCode())) * 31) + (getRoleArn() != null ? getRoleArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StreamInfo)) {
            return false;
        }
        StreamInfo streamInfo = (StreamInfo) obj;
        if ((streamInfo.getStreamId() == null) ^ (getStreamId() == null)) {
            return false;
        }
        if (streamInfo.getStreamId() != null && !streamInfo.getStreamId().equals(getStreamId())) {
            return false;
        }
        if ((streamInfo.getStreamArn() == null) ^ (getStreamArn() == null)) {
            return false;
        }
        if (streamInfo.getStreamArn() != null && !streamInfo.getStreamArn().equals(getStreamArn())) {
            return false;
        }
        if ((streamInfo.getStreamVersion() == null) ^ (getStreamVersion() == null)) {
            return false;
        }
        if (streamInfo.getStreamVersion() != null && !streamInfo.getStreamVersion().equals(getStreamVersion())) {
            return false;
        }
        if ((streamInfo.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (streamInfo.getDescription() != null && !streamInfo.getDescription().equals(getDescription())) {
            return false;
        }
        if ((streamInfo.getFiles() == null) ^ (getFiles() == null)) {
            return false;
        }
        if (streamInfo.getFiles() != null && !streamInfo.getFiles().equals(getFiles())) {
            return false;
        }
        if ((streamInfo.getCreatedAt() == null) ^ (getCreatedAt() == null)) {
            return false;
        }
        if (streamInfo.getCreatedAt() != null && !streamInfo.getCreatedAt().equals(getCreatedAt())) {
            return false;
        }
        if ((streamInfo.getLastUpdatedAt() == null) ^ (getLastUpdatedAt() == null)) {
            return false;
        }
        if (streamInfo.getLastUpdatedAt() != null && !streamInfo.getLastUpdatedAt().equals(getLastUpdatedAt())) {
            return false;
        }
        if ((streamInfo.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        return streamInfo.getRoleArn() == null || streamInfo.getRoleArn().equals(getRoleArn());
    }
}
