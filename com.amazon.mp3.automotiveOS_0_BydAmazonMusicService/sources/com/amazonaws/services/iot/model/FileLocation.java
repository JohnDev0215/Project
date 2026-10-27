package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class FileLocation implements Serializable {
    private S3Location s3Location;
    private Stream stream;

    public Stream getStream() {
        return this.stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }

    public FileLocation withStream(Stream stream) {
        this.stream = stream;
        return this;
    }

    public S3Location getS3Location() {
        return this.s3Location;
    }

    public void setS3Location(S3Location s3Location) {
        this.s3Location = s3Location;
    }

    public FileLocation withS3Location(S3Location s3Location) {
        this.s3Location = s3Location;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getStream() != null) {
            sb.append("stream: " + getStream() + ",");
        }
        if (getS3Location() != null) {
            sb.append("s3Location: " + getS3Location());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getStream() == null ? 0 : getStream().hashCode()) + 31) * 31) + (getS3Location() != null ? getS3Location().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof FileLocation)) {
            return false;
        }
        FileLocation fileLocation = (FileLocation) obj;
        if ((fileLocation.getStream() == null) ^ (getStream() == null)) {
            return false;
        }
        if (fileLocation.getStream() != null && !fileLocation.getStream().equals(getStream())) {
            return false;
        }
        if ((fileLocation.getS3Location() == null) ^ (getS3Location() == null)) {
            return false;
        }
        return fileLocation.getS3Location() == null || fileLocation.getS3Location().equals(getS3Location());
    }
}
