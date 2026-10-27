package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class BehaviorModelTrainingSummary implements Serializable {
    private String behaviorName;
    private Double datapointsCollectionPercentage;
    private Date lastModelRefreshDate;
    private String modelStatus;
    private String securityProfileName;
    private Date trainingDataCollectionStartDate;

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public BehaviorModelTrainingSummary withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public String getBehaviorName() {
        return this.behaviorName;
    }

    public void setBehaviorName(String str) {
        this.behaviorName = str;
    }

    public BehaviorModelTrainingSummary withBehaviorName(String str) {
        this.behaviorName = str;
        return this;
    }

    public Date getTrainingDataCollectionStartDate() {
        return this.trainingDataCollectionStartDate;
    }

    public void setTrainingDataCollectionStartDate(Date date) {
        this.trainingDataCollectionStartDate = date;
    }

    public BehaviorModelTrainingSummary withTrainingDataCollectionStartDate(Date date) {
        this.trainingDataCollectionStartDate = date;
        return this;
    }

    public String getModelStatus() {
        return this.modelStatus;
    }

    public void setModelStatus(String str) {
        this.modelStatus = str;
    }

    public BehaviorModelTrainingSummary withModelStatus(String str) {
        this.modelStatus = str;
        return this;
    }

    public void setModelStatus(ModelStatus modelStatus) {
        this.modelStatus = modelStatus.toString();
    }

    public BehaviorModelTrainingSummary withModelStatus(ModelStatus modelStatus) {
        this.modelStatus = modelStatus.toString();
        return this;
    }

    public Double getDatapointsCollectionPercentage() {
        return this.datapointsCollectionPercentage;
    }

    public void setDatapointsCollectionPercentage(Double d) {
        this.datapointsCollectionPercentage = d;
    }

    public BehaviorModelTrainingSummary withDatapointsCollectionPercentage(Double d) {
        this.datapointsCollectionPercentage = d;
        return this;
    }

    public Date getLastModelRefreshDate() {
        return this.lastModelRefreshDate;
    }

    public void setLastModelRefreshDate(Date date) {
        this.lastModelRefreshDate = date;
    }

    public BehaviorModelTrainingSummary withLastModelRefreshDate(Date date) {
        this.lastModelRefreshDate = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSecurityProfileName() != null) {
            sb.append("securityProfileName: " + getSecurityProfileName() + ",");
        }
        if (getBehaviorName() != null) {
            sb.append("behaviorName: " + getBehaviorName() + ",");
        }
        if (getTrainingDataCollectionStartDate() != null) {
            sb.append("trainingDataCollectionStartDate: " + getTrainingDataCollectionStartDate() + ",");
        }
        if (getModelStatus() != null) {
            sb.append("modelStatus: " + getModelStatus() + ",");
        }
        if (getDatapointsCollectionPercentage() != null) {
            sb.append("datapointsCollectionPercentage: " + getDatapointsCollectionPercentage() + ",");
        }
        if (getLastModelRefreshDate() != null) {
            sb.append("lastModelRefreshDate: " + getLastModelRefreshDate());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode()) + 31) * 31) + (getBehaviorName() == null ? 0 : getBehaviorName().hashCode())) * 31) + (getTrainingDataCollectionStartDate() == null ? 0 : getTrainingDataCollectionStartDate().hashCode())) * 31) + (getModelStatus() == null ? 0 : getModelStatus().hashCode())) * 31) + (getDatapointsCollectionPercentage() == null ? 0 : getDatapointsCollectionPercentage().hashCode())) * 31) + (getLastModelRefreshDate() != null ? getLastModelRefreshDate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BehaviorModelTrainingSummary)) {
            return false;
        }
        BehaviorModelTrainingSummary behaviorModelTrainingSummary = (BehaviorModelTrainingSummary) obj;
        if ((behaviorModelTrainingSummary.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (behaviorModelTrainingSummary.getSecurityProfileName() != null && !behaviorModelTrainingSummary.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((behaviorModelTrainingSummary.getBehaviorName() == null) ^ (getBehaviorName() == null)) {
            return false;
        }
        if (behaviorModelTrainingSummary.getBehaviorName() != null && !behaviorModelTrainingSummary.getBehaviorName().equals(getBehaviorName())) {
            return false;
        }
        if ((behaviorModelTrainingSummary.getTrainingDataCollectionStartDate() == null) ^ (getTrainingDataCollectionStartDate() == null)) {
            return false;
        }
        if (behaviorModelTrainingSummary.getTrainingDataCollectionStartDate() != null && !behaviorModelTrainingSummary.getTrainingDataCollectionStartDate().equals(getTrainingDataCollectionStartDate())) {
            return false;
        }
        if ((behaviorModelTrainingSummary.getModelStatus() == null) ^ (getModelStatus() == null)) {
            return false;
        }
        if (behaviorModelTrainingSummary.getModelStatus() != null && !behaviorModelTrainingSummary.getModelStatus().equals(getModelStatus())) {
            return false;
        }
        if ((behaviorModelTrainingSummary.getDatapointsCollectionPercentage() == null) ^ (getDatapointsCollectionPercentage() == null)) {
            return false;
        }
        if (behaviorModelTrainingSummary.getDatapointsCollectionPercentage() != null && !behaviorModelTrainingSummary.getDatapointsCollectionPercentage().equals(getDatapointsCollectionPercentage())) {
            return false;
        }
        if ((behaviorModelTrainingSummary.getLastModelRefreshDate() == null) ^ (getLastModelRefreshDate() == null)) {
            return false;
        }
        return behaviorModelTrainingSummary.getLastModelRefreshDate() == null || behaviorModelTrainingSummary.getLastModelRefreshDate().equals(getLastModelRefreshDate());
    }
}
