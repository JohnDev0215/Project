package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class AwsJobPresignedUrlConfig implements Serializable {
    private Long expiresInSec;

    public Long getExpiresInSec() {
        return this.expiresInSec;
    }

    public void setExpiresInSec(Long l) {
        this.expiresInSec = l;
    }

    public AwsJobPresignedUrlConfig withExpiresInSec(Long l) {
        this.expiresInSec = l;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getExpiresInSec() != null) {
            sb.append("expiresInSec: " + getExpiresInSec());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getExpiresInSec() == null ? 0 : getExpiresInSec().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AwsJobPresignedUrlConfig)) {
            return false;
        }
        AwsJobPresignedUrlConfig awsJobPresignedUrlConfig = (AwsJobPresignedUrlConfig) obj;
        if ((awsJobPresignedUrlConfig.getExpiresInSec() == null) ^ (getExpiresInSec() == null)) {
            return false;
        }
        return awsJobPresignedUrlConfig.getExpiresInSec() == null || awsJobPresignedUrlConfig.getExpiresInSec().equals(getExpiresInSec());
    }
}
