package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeMitigationActionResult implements Serializable {
    private String actionArn;
    private String actionId;
    private String actionName;
    private MitigationActionParams actionParams;
    private String actionType;
    private Date creationDate;
    private Date lastModifiedDate;
    private String roleArn;

    public String getActionName() {
        return this.actionName;
    }

    public void setActionName(String str) {
        this.actionName = str;
    }

    public DescribeMitigationActionResult withActionName(String str) {
        this.actionName = str;
        return this;
    }

    public String getActionType() {
        return this.actionType;
    }

    public void setActionType(String str) {
        this.actionType = str;
    }

    public DescribeMitigationActionResult withActionType(String str) {
        this.actionType = str;
        return this;
    }

    public void setActionType(MitigationActionType mitigationActionType) {
        this.actionType = mitigationActionType.toString();
    }

    public DescribeMitigationActionResult withActionType(MitigationActionType mitigationActionType) {
        this.actionType = mitigationActionType.toString();
        return this;
    }

    public String getActionArn() {
        return this.actionArn;
    }

    public void setActionArn(String str) {
        this.actionArn = str;
    }

    public DescribeMitigationActionResult withActionArn(String str) {
        this.actionArn = str;
        return this;
    }

    public String getActionId() {
        return this.actionId;
    }

    public void setActionId(String str) {
        this.actionId = str;
    }

    public DescribeMitigationActionResult withActionId(String str) {
        this.actionId = str;
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public DescribeMitigationActionResult withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public MitigationActionParams getActionParams() {
        return this.actionParams;
    }

    public void setActionParams(MitigationActionParams mitigationActionParams) {
        this.actionParams = mitigationActionParams;
    }

    public DescribeMitigationActionResult withActionParams(MitigationActionParams mitigationActionParams) {
        this.actionParams = mitigationActionParams;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public DescribeMitigationActionResult withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public DescribeMitigationActionResult withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getActionName() != null) {
            sb.append("actionName: " + getActionName() + ",");
        }
        if (getActionType() != null) {
            sb.append("actionType: " + getActionType() + ",");
        }
        if (getActionArn() != null) {
            sb.append("actionArn: " + getActionArn() + ",");
        }
        if (getActionId() != null) {
            sb.append("actionId: " + getActionId() + ",");
        }
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getActionParams() != null) {
            sb.append("actionParams: " + getActionParams() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate() + ",");
        }
        if (getLastModifiedDate() != null) {
            sb.append("lastModifiedDate: " + getLastModifiedDate());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((getActionName() == null ? 0 : getActionName().hashCode()) + 31) * 31) + (getActionType() == null ? 0 : getActionType().hashCode())) * 31) + (getActionArn() == null ? 0 : getActionArn().hashCode())) * 31) + (getActionId() == null ? 0 : getActionId().hashCode())) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getActionParams() == null ? 0 : getActionParams().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() != null ? getLastModifiedDate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeMitigationActionResult)) {
            return false;
        }
        DescribeMitigationActionResult describeMitigationActionResult = (DescribeMitigationActionResult) obj;
        if ((describeMitigationActionResult.getActionName() == null) ^ (getActionName() == null)) {
            return false;
        }
        if (describeMitigationActionResult.getActionName() != null && !describeMitigationActionResult.getActionName().equals(getActionName())) {
            return false;
        }
        if ((describeMitigationActionResult.getActionType() == null) ^ (getActionType() == null)) {
            return false;
        }
        if (describeMitigationActionResult.getActionType() != null && !describeMitigationActionResult.getActionType().equals(getActionType())) {
            return false;
        }
        if ((describeMitigationActionResult.getActionArn() == null) ^ (getActionArn() == null)) {
            return false;
        }
        if (describeMitigationActionResult.getActionArn() != null && !describeMitigationActionResult.getActionArn().equals(getActionArn())) {
            return false;
        }
        if ((describeMitigationActionResult.getActionId() == null) ^ (getActionId() == null)) {
            return false;
        }
        if (describeMitigationActionResult.getActionId() != null && !describeMitigationActionResult.getActionId().equals(getActionId())) {
            return false;
        }
        if ((describeMitigationActionResult.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (describeMitigationActionResult.getRoleArn() != null && !describeMitigationActionResult.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((describeMitigationActionResult.getActionParams() == null) ^ (getActionParams() == null)) {
            return false;
        }
        if (describeMitigationActionResult.getActionParams() != null && !describeMitigationActionResult.getActionParams().equals(getActionParams())) {
            return false;
        }
        if ((describeMitigationActionResult.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (describeMitigationActionResult.getCreationDate() != null && !describeMitigationActionResult.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((describeMitigationActionResult.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        return describeMitigationActionResult.getLastModifiedDate() == null || describeMitigationActionResult.getLastModifiedDate().equals(getLastModifiedDate());
    }
}
