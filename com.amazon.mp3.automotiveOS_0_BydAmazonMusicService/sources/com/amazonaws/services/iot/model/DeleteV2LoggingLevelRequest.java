package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DeleteV2LoggingLevelRequest extends AmazonWebServiceRequest implements Serializable {
    private String targetName;
    private String targetType;

    public String getTargetType() {
        return this.targetType;
    }

    public void setTargetType(String str) {
        this.targetType = str;
    }

    public DeleteV2LoggingLevelRequest withTargetType(String str) {
        this.targetType = str;
        return this;
    }

    public void setTargetType(LogTargetType logTargetType) {
        this.targetType = logTargetType.toString();
    }

    public DeleteV2LoggingLevelRequest withTargetType(LogTargetType logTargetType) {
        this.targetType = logTargetType.toString();
        return this;
    }

    public String getTargetName() {
        return this.targetName;
    }

    public void setTargetName(String str) {
        this.targetName = str;
    }

    public DeleteV2LoggingLevelRequest withTargetName(String str) {
        this.targetName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTargetType() != null) {
            sb.append("targetType: " + getTargetType() + ",");
        }
        if (getTargetName() != null) {
            sb.append("targetName: " + getTargetName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getTargetType() == null ? 0 : getTargetType().hashCode()) + 31) * 31) + (getTargetName() != null ? getTargetName().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteV2LoggingLevelRequest)) {
            return false;
        }
        DeleteV2LoggingLevelRequest deleteV2LoggingLevelRequest = (DeleteV2LoggingLevelRequest) obj;
        if ((deleteV2LoggingLevelRequest.getTargetType() == null) ^ (getTargetType() == null)) {
            return false;
        }
        if (deleteV2LoggingLevelRequest.getTargetType() != null && !deleteV2LoggingLevelRequest.getTargetType().equals(getTargetType())) {
            return false;
        }
        if ((deleteV2LoggingLevelRequest.getTargetName() == null) ^ (getTargetName() == null)) {
            return false;
        }
        return deleteV2LoggingLevelRequest.getTargetName() == null || deleteV2LoggingLevelRequest.getTargetName().equals(getTargetName());
    }
}
