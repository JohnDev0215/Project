package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class S3Location implements Serializable {
    private String bucket;
    private String key;
    private String version;

    public String getBucket() {
        return this.bucket;
    }

    public void setBucket(String str) {
        this.bucket = str;
    }

    public S3Location withBucket(String str) {
        this.bucket = str;
        return this;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public S3Location withKey(String str) {
        this.key = str;
        return this;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public S3Location withVersion(String str) {
        this.version = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getBucket() != null) {
            sb.append("bucket: " + getBucket() + ",");
        }
        if (getKey() != null) {
            sb.append("key: " + getKey() + ",");
        }
        if (getVersion() != null) {
            sb.append("version: " + getVersion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getBucket() == null ? 0 : getBucket().hashCode()) + 31) * 31) + (getKey() == null ? 0 : getKey().hashCode())) * 31) + (getVersion() != null ? getVersion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof S3Location)) {
            return false;
        }
        S3Location s3Location = (S3Location) obj;
        if ((s3Location.getBucket() == null) ^ (getBucket() == null)) {
            return false;
        }
        if (s3Location.getBucket() != null && !s3Location.getBucket().equals(getBucket())) {
            return false;
        }
        if ((s3Location.getKey() == null) ^ (getKey() == null)) {
            return false;
        }
        if (s3Location.getKey() != null && !s3Location.getKey().equals(getKey())) {
            return false;
        }
        if ((s3Location.getVersion() == null) ^ (getVersion() == null)) {
            return false;
        }
        return s3Location.getVersion() == null || s3Location.getVersion().equals(getVersion());
    }
}
