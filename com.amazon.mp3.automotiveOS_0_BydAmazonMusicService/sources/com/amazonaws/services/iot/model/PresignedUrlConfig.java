package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class PresignedUrlConfig implements Serializable {
    private Long expiresInSec;
    private String roleArn;

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public PresignedUrlConfig withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public Long getExpiresInSec() {
        return this.expiresInSec;
    }

    public void setExpiresInSec(Long l) {
        this.expiresInSec = l;
    }

    public PresignedUrlConfig withExpiresInSec(Long l) {
        this.expiresInSec = l;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getExpiresInSec() != null) {
            sb.append("expiresInSec: " + getExpiresInSec());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getExpiresInSec() != null ? getExpiresInSec().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PresignedUrlConfig)) {
            return false;
        }
        PresignedUrlConfig presignedUrlConfig = (PresignedUrlConfig) obj;
        if ((presignedUrlConfig.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (presignedUrlConfig.getRoleArn() != null && !presignedUrlConfig.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((presignedUrlConfig.getExpiresInSec() == null) ^ (getExpiresInSec() == null)) {
            return false;
        }
        return presignedUrlConfig.getExpiresInSec() == null || presignedUrlConfig.getExpiresInSec().equals(getExpiresInSec());
    }
}
