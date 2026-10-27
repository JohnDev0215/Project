package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateMitigationActionRequest extends AmazonWebServiceRequest implements Serializable {
    private String actionName;
    private MitigationActionParams actionParams;
    private String roleArn;

    public String getActionName() {
        return this.actionName;
    }

    public void setActionName(String str) {
        this.actionName = str;
    }

    public UpdateMitigationActionRequest withActionName(String str) {
        this.actionName = str;
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public UpdateMitigationActionRequest withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public MitigationActionParams getActionParams() {
        return this.actionParams;
    }

    public void setActionParams(MitigationActionParams mitigationActionParams) {
        this.actionParams = mitigationActionParams;
    }

    public UpdateMitigationActionRequest withActionParams(MitigationActionParams mitigationActionParams) {
        this.actionParams = mitigationActionParams;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getActionName() != null) {
            sb.append("actionName: " + getActionName() + ",");
        }
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getActionParams() != null) {
            sb.append("actionParams: " + getActionParams());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getActionName() == null ? 0 : getActionName().hashCode()) + 31) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getActionParams() != null ? getActionParams().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateMitigationActionRequest)) {
            return false;
        }
        UpdateMitigationActionRequest updateMitigationActionRequest = (UpdateMitigationActionRequest) obj;
        if ((updateMitigationActionRequest.getActionName() == null) ^ (getActionName() == null)) {
            return false;
        }
        if (updateMitigationActionRequest.getActionName() != null && !updateMitigationActionRequest.getActionName().equals(getActionName())) {
            return false;
        }
        if ((updateMitigationActionRequest.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (updateMitigationActionRequest.getRoleArn() != null && !updateMitigationActionRequest.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((updateMitigationActionRequest.getActionParams() == null) ^ (getActionParams() == null)) {
            return false;
        }
        return updateMitigationActionRequest.getActionParams() == null || updateMitigationActionRequest.getActionParams().equals(getActionParams());
    }
}
