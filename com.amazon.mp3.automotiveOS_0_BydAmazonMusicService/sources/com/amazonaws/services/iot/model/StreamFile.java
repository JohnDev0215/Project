package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class StreamFile implements Serializable {
    private Integer fileId;
    private S3Location s3Location;

    public Integer getFileId() {
        return this.fileId;
    }

    public void setFileId(Integer num) {
        this.fileId = num;
    }

    public StreamFile withFileId(Integer num) {
        this.fileId = num;
        return this;
    }

    public S3Location getS3Location() {
        return this.s3Location;
    }

    public void setS3Location(S3Location s3Location) {
        this.s3Location = s3Location;
    }

    public StreamFile withS3Location(S3Location s3Location) {
        this.s3Location = s3Location;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getFileId() != null) {
            sb.append("fileId: " + getFileId() + ",");
        }
        if (getS3Location() != null) {
            sb.append("s3Location: " + getS3Location());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getFileId() == null ? 0 : getFileId().hashCode()) + 31) * 31) + (getS3Location() != null ? getS3Location().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StreamFile)) {
            return false;
        }
        StreamFile streamFile = (StreamFile) obj;
        if ((streamFile.getFileId() == null) ^ (getFileId() == null)) {
            return false;
        }
        if (streamFile.getFileId() != null && !streamFile.getFileId().equals(getFileId())) {
            return false;
        }
        if ((streamFile.getS3Location() == null) ^ (getS3Location() == null)) {
            return false;
        }
        return streamFile.getS3Location() == null || streamFile.getS3Location().equals(getS3Location());
    }
}
