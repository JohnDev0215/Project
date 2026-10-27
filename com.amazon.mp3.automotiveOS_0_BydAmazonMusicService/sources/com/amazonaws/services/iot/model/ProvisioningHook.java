package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ProvisioningHook implements Serializable {
    private String payloadVersion;
    private String targetArn;

    public String getPayloadVersion() {
        return this.payloadVersion;
    }

    public void setPayloadVersion(String str) {
        this.payloadVersion = str;
    }

    public ProvisioningHook withPayloadVersion(String str) {
        this.payloadVersion = str;
        return this;
    }

    public String getTargetArn() {
        return this.targetArn;
    }

    public void setTargetArn(String str) {
        this.targetArn = str;
    }

    public ProvisioningHook withTargetArn(String str) {
        this.targetArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPayloadVersion() != null) {
            sb.append("payloadVersion: " + getPayloadVersion() + ",");
        }
        if (getTargetArn() != null) {
            sb.append("targetArn: " + getTargetArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getPayloadVersion() == null ? 0 : getPayloadVersion().hashCode()) + 31) * 31) + (getTargetArn() != null ? getTargetArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ProvisioningHook)) {
            return false;
        }
        ProvisioningHook provisioningHook = (ProvisioningHook) obj;
        if ((provisioningHook.getPayloadVersion() == null) ^ (getPayloadVersion() == null)) {
            return false;
        }
        if (provisioningHook.getPayloadVersion() != null && !provisioningHook.getPayloadVersion().equals(getPayloadVersion())) {
            return false;
        }
        if ((provisioningHook.getTargetArn() == null) ^ (getTargetArn() == null)) {
            return false;
        }
        return provisioningHook.getTargetArn() == null || provisioningHook.getTargetArn().equals(getTargetArn());
    }
}
