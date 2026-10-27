package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class MachineLearningDetectionConfig implements Serializable {
    private String confidenceLevel;

    public String getConfidenceLevel() {
        return this.confidenceLevel;
    }

    public void setConfidenceLevel(String str) {
        this.confidenceLevel = str;
    }

    public MachineLearningDetectionConfig withConfidenceLevel(String str) {
        this.confidenceLevel = str;
        return this;
    }

    public void setConfidenceLevel(ConfidenceLevel confidenceLevel) {
        this.confidenceLevel = confidenceLevel.toString();
    }

    public MachineLearningDetectionConfig withConfidenceLevel(ConfidenceLevel confidenceLevel) {
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
        if (obj == null || !(obj instanceof MachineLearningDetectionConfig)) {
            return false;
        }
        MachineLearningDetectionConfig machineLearningDetectionConfig = (MachineLearningDetectionConfig) obj;
        if ((machineLearningDetectionConfig.getConfidenceLevel() == null) ^ (getConfidenceLevel() == null)) {
            return false;
        }
        return machineLearningDetectionConfig.getConfidenceLevel() == null || machineLearningDetectionConfig.getConfidenceLevel().equals(getConfidenceLevel());
    }
}
