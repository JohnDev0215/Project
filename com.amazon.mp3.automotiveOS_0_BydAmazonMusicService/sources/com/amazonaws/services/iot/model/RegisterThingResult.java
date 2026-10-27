package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class RegisterThingResult implements Serializable {
    private String certificatePem;
    private Map<String, String> resourceArns;

    public String getCertificatePem() {
        return this.certificatePem;
    }

    public void setCertificatePem(String str) {
        this.certificatePem = str;
    }

    public RegisterThingResult withCertificatePem(String str) {
        this.certificatePem = str;
        return this;
    }

    public Map<String, String> getResourceArns() {
        return this.resourceArns;
    }

    public void setResourceArns(Map<String, String> map) {
        this.resourceArns = map;
    }

    public RegisterThingResult withResourceArns(Map<String, String> map) {
        this.resourceArns = map;
        return this;
    }

    public RegisterThingResult addresourceArnsEntry(String str, String str2) {
        if (this.resourceArns == null) {
            this.resourceArns = new HashMap();
        }
        if (this.resourceArns.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.resourceArns.put(str, str2);
        return this;
    }

    public RegisterThingResult clearresourceArnsEntries() {
        this.resourceArns = null;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCertificatePem() != null) {
            sb.append("certificatePem: " + getCertificatePem() + ",");
        }
        if (getResourceArns() != null) {
            sb.append("resourceArns: " + getResourceArns());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getCertificatePem() == null ? 0 : getCertificatePem().hashCode()) + 31) * 31) + (getResourceArns() != null ? getResourceArns().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RegisterThingResult)) {
            return false;
        }
        RegisterThingResult registerThingResult = (RegisterThingResult) obj;
        if ((registerThingResult.getCertificatePem() == null) ^ (getCertificatePem() == null)) {
            return false;
        }
        if (registerThingResult.getCertificatePem() != null && !registerThingResult.getCertificatePem().equals(getCertificatePem())) {
            return false;
        }
        if ((registerThingResult.getResourceArns() == null) ^ (getResourceArns() == null)) {
            return false;
        }
        return registerThingResult.getResourceArns() == null || registerThingResult.getResourceArns().equals(getResourceArns());
    }
}
