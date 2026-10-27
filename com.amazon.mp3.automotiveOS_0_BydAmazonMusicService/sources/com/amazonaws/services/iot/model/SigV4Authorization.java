package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class SigV4Authorization implements Serializable {
    private String roleArn;
    private String serviceName;
    private String signingRegion;

    public String getSigningRegion() {
        return this.signingRegion;
    }

    public void setSigningRegion(String str) {
        this.signingRegion = str;
    }

    public SigV4Authorization withSigningRegion(String str) {
        this.signingRegion = str;
        return this;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String str) {
        this.serviceName = str;
    }

    public SigV4Authorization withServiceName(String str) {
        this.serviceName = str;
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public SigV4Authorization withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSigningRegion() != null) {
            sb.append("signingRegion: " + getSigningRegion() + ",");
        }
        if (getServiceName() != null) {
            sb.append("serviceName: " + getServiceName() + ",");
        }
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getSigningRegion() == null ? 0 : getSigningRegion().hashCode()) + 31) * 31) + (getServiceName() == null ? 0 : getServiceName().hashCode())) * 31) + (getRoleArn() != null ? getRoleArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SigV4Authorization)) {
            return false;
        }
        SigV4Authorization sigV4Authorization = (SigV4Authorization) obj;
        if ((sigV4Authorization.getSigningRegion() == null) ^ (getSigningRegion() == null)) {
            return false;
        }
        if (sigV4Authorization.getSigningRegion() != null && !sigV4Authorization.getSigningRegion().equals(getSigningRegion())) {
            return false;
        }
        if ((sigV4Authorization.getServiceName() == null) ^ (getServiceName() == null)) {
            return false;
        }
        if (sigV4Authorization.getServiceName() != null && !sigV4Authorization.getServiceName().equals(getServiceName())) {
            return false;
        }
        if ((sigV4Authorization.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        return sigV4Authorization.getRoleArn() == null || sigV4Authorization.getRoleArn().equals(getRoleArn());
    }
}
