package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CreateMitigationActionResult implements Serializable {
    private String actionArn;
    private String actionId;

    public String getActionArn() {
        return this.actionArn;
    }

    public void setActionArn(String str) {
        this.actionArn = str;
    }

    public CreateMitigationActionResult withActionArn(String str) {
        this.actionArn = str;
        return this;
    }

    public String getActionId() {
        return this.actionId;
    }

    public void setActionId(String str) {
        this.actionId = str;
    }

    public CreateMitigationActionResult withActionId(String str) {
        this.actionId = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getActionArn() != null) {
            sb.append("actionArn: " + getActionArn() + ",");
        }
        if (getActionId() != null) {
            sb.append("actionId: " + getActionId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getActionArn() == null ? 0 : getActionArn().hashCode()) + 31) * 31) + (getActionId() != null ? getActionId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateMitigationActionResult)) {
            return false;
        }
        CreateMitigationActionResult createMitigationActionResult = (CreateMitigationActionResult) obj;
        if ((createMitigationActionResult.getActionArn() == null) ^ (getActionArn() == null)) {
            return false;
        }
        if (createMitigationActionResult.getActionArn() != null && !createMitigationActionResult.getActionArn().equals(getActionArn())) {
            return false;
        }
        if ((createMitigationActionResult.getActionId() == null) ^ (getActionId() == null)) {
            return false;
        }
        return createMitigationActionResult.getActionId() == null || createMitigationActionResult.getActionId().equals(getActionId());
    }
}
