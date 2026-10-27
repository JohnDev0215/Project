package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class S3Destination implements Serializable {
    private String bucket;
    private String prefix;

    public String getBucket() {
        return this.bucket;
    }

    public void setBucket(String str) {
        this.bucket = str;
    }

    public S3Destination withBucket(String str) {
        this.bucket = str;
        return this;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public S3Destination withPrefix(String str) {
        this.prefix = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getBucket() != null) {
            sb.append("bucket: " + getBucket() + ",");
        }
        if (getPrefix() != null) {
            sb.append("prefix: " + getPrefix());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getBucket() == null ? 0 : getBucket().hashCode()) + 31) * 31) + (getPrefix() != null ? getPrefix().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof S3Destination)) {
            return false;
        }
        S3Destination s3Destination = (S3Destination) obj;
        if ((s3Destination.getBucket() == null) ^ (getBucket() == null)) {
            return false;
        }
        if (s3Destination.getBucket() != null && !s3Destination.getBucket().equals(getBucket())) {
            return false;
        }
        if ((s3Destination.getPrefix() == null) ^ (getPrefix() == null)) {
            return false;
        }
        return s3Destination.getPrefix() == null || s3Destination.getPrefix().equals(getPrefix());
    }
}
