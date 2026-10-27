package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateOTAUpdateResult implements Serializable {
    private String awsIotJobArn;
    private String awsIotJobId;
    private String otaUpdateArn;
    private String otaUpdateId;
    private String otaUpdateStatus;

    public String getOtaUpdateId() {
        return this.otaUpdateId;
    }

    public void setOtaUpdateId(String str) {
        this.otaUpdateId = str;
    }

    public CreateOTAUpdateResult withOtaUpdateId(String str) {
        this.otaUpdateId = str;
        return this;
    }

    public String getAwsIotJobId() {
        return this.awsIotJobId;
    }

    public void setAwsIotJobId(String str) {
        this.awsIotJobId = str;
    }

    public CreateOTAUpdateResult withAwsIotJobId(String str) {
        this.awsIotJobId = str;
        return this;
    }

    public String getOtaUpdateArn() {
        return this.otaUpdateArn;
    }

    public void setOtaUpdateArn(String str) {
        this.otaUpdateArn = str;
    }

    public CreateOTAUpdateResult withOtaUpdateArn(String str) {
        this.otaUpdateArn = str;
        return this;
    }

    public String getAwsIotJobArn() {
        return this.awsIotJobArn;
    }

    public void setAwsIotJobArn(String str) {
        this.awsIotJobArn = str;
    }

    public CreateOTAUpdateResult withAwsIotJobArn(String str) {
        this.awsIotJobArn = str;
        return this;
    }

    public String getOtaUpdateStatus() {
        return this.otaUpdateStatus;
    }

    public void setOtaUpdateStatus(String str) {
        this.otaUpdateStatus = str;
    }

    public CreateOTAUpdateResult withOtaUpdateStatus(String str) {
        this.otaUpdateStatus = str;
        return this;
    }

    public void setOtaUpdateStatus(OTAUpdateStatus oTAUpdateStatus) {
        this.otaUpdateStatus = oTAUpdateStatus.toString();
    }

    public CreateOTAUpdateResult withOtaUpdateStatus(OTAUpdateStatus oTAUpdateStatus) {
        this.otaUpdateStatus = oTAUpdateStatus.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getOtaUpdateId() != null) {
            sb.append("otaUpdateId: " + getOtaUpdateId() + ",");
        }
        if (getAwsIotJobId() != null) {
            sb.append("awsIotJobId: " + getAwsIotJobId() + ",");
        }
        if (getOtaUpdateArn() != null) {
            sb.append("otaUpdateArn: " + getOtaUpdateArn() + ",");
        }
        if (getAwsIotJobArn() != null) {
            sb.append("awsIotJobArn: " + getAwsIotJobArn() + ",");
        }
        if (getOtaUpdateStatus() != null) {
            sb.append("otaUpdateStatus: " + getOtaUpdateStatus());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getOtaUpdateId() == null ? 0 : getOtaUpdateId().hashCode()) + 31) * 31) + (getAwsIotJobId() == null ? 0 : getAwsIotJobId().hashCode())) * 31) + (getOtaUpdateArn() == null ? 0 : getOtaUpdateArn().hashCode())) * 31) + (getAwsIotJobArn() == null ? 0 : getAwsIotJobArn().hashCode())) * 31) + (getOtaUpdateStatus() != null ? getOtaUpdateStatus().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateOTAUpdateResult)) {
            return false;
        }
        CreateOTAUpdateResult createOTAUpdateResult = (CreateOTAUpdateResult) obj;
        if ((createOTAUpdateResult.getOtaUpdateId() == null) ^ (getOtaUpdateId() == null)) {
            return false;
        }
        if (createOTAUpdateResult.getOtaUpdateId() != null && !createOTAUpdateResult.getOtaUpdateId().equals(getOtaUpdateId())) {
            return false;
        }
        if ((createOTAUpdateResult.getAwsIotJobId() == null) ^ (getAwsIotJobId() == null)) {
            return false;
        }
        if (createOTAUpdateResult.getAwsIotJobId() != null && !createOTAUpdateResult.getAwsIotJobId().equals(getAwsIotJobId())) {
            return false;
        }
        if ((createOTAUpdateResult.getOtaUpdateArn() == null) ^ (getOtaUpdateArn() == null)) {
            return false;
        }
        if (createOTAUpdateResult.getOtaUpdateArn() != null && !createOTAUpdateResult.getOtaUpdateArn().equals(getOtaUpdateArn())) {
            return false;
        }
        if ((createOTAUpdateResult.getAwsIotJobArn() == null) ^ (getAwsIotJobArn() == null)) {
            return false;
        }
        if (createOTAUpdateResult.getAwsIotJobArn() != null && !createOTAUpdateResult.getAwsIotJobArn().equals(getAwsIotJobArn())) {
            return false;
        }
        if ((createOTAUpdateResult.getOtaUpdateStatus() == null) ^ (getOtaUpdateStatus() == null)) {
            return false;
        }
        return createOTAUpdateResult.getOtaUpdateStatus() == null || createOTAUpdateResult.getOtaUpdateStatus().equals(getOtaUpdateStatus());
    }
}
