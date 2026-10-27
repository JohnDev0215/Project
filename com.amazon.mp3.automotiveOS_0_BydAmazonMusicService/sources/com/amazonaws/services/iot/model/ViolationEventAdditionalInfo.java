package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ViolationEventAdditionalInfo implements Serializable {
    private String confidenceLevel;

    public String getConfidenceLevel() {
        return this.confidenceLevel;
    }

    public void setConfidenceLevel(String str) {
        this.confidenceLevel = str;
    }

    public ViolationEventAdditionalInfo withConfidenceLevel(String str) {
        this.confidenceLevel = str;
        return this;
    }

    public void setConfidenceLevel(ConfidenceLevel confidenceLevel) {
        this.confidenceLevel = confidenceLevel.toString();
    }

    public ViolationEventAdditionalInfo withConfidenceLevel(ConfidenceLevel confidenceLevel) {
        this.confidenceLevel = confidenceLevel.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getConfidenceLevel() != null) {
            sb.append("confidenceLevel: " + getConfidenceLevel());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getConfidenceLevel() == null ? 0 : getConfidenceLevel().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ViolationEventAdditionalInfo)) {
            return false;
        }
        ViolationEventAdditionalInfo violationEventAdditionalInfo = (ViolationEventAdditionalInfo) obj;
        if ((violationEventAdditionalInfo.getConfidenceLevel() == null) ^ (getConfidenceLevel() == null)) {
            return false;
        }
        return violationEventAdditionalInfo.getConfidenceLevel() == null || violationEventAdditionalInfo.getConfidenceLevel().equals(getConfidenceLevel());
    }
}
