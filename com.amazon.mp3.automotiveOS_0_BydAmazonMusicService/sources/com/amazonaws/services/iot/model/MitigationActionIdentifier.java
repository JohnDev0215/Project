package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class MitigationActionIdentifier implements Serializable {
    private String actionArn;
    private String actionName;
    private Date creationDate;

    public String getActionName() {
        return this.actionName;
    }

    public void setActionName(String str) {
        this.actionName = str;
    }

    public MitigationActionIdentifier withActionName(String str) {
        this.actionName = str;
        return this;
    }

    public String getActionArn() {
        return this.actionArn;
    }

    public void setActionArn(String str) {
        this.actionArn = str;
    }

    public MitigationActionIdentifier withActionArn(String str) {
        this.actionArn = str;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public MitigationActionIdentifier withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getActionName() != null) {
            sb.append("actionName: " + getActionName() + ",");
        }
        if (getActionArn() != null) {
            sb.append("actionArn: " + getActionArn() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getActionName() == null ? 0 : getActionName().hashCode()) + 31) * 31) + (getActionArn() == null ? 0 : getActionArn().hashCode())) * 31) + (getCreationDate() != null ? getCreationDate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MitigationActionIdentifier)) {
            return false;
        }
        MitigationActionIdentifier mitigationActionIdentifier = (MitigationActionIdentifier) obj;
        if ((mitigationActionIdentifier.getActionName() == null) ^ (getActionName() == null)) {
            return false;
        }
        if (mitigationActionIdentifier.getActionName() != null && !mitigationActionIdentifier.getActionName().equals(getActionName())) {
            return false;
        }
        if ((mitigationActionIdentifier.getActionArn() == null) ^ (getActionArn() == null)) {
            return false;
        }
        if (mitigationActionIdentifier.getActionArn() != null && !mitigationActionIdentifier.getActionArn().equals(getActionArn())) {
            return false;
        }
        if ((mitigationActionIdentifier.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        return mitigationActionIdentifier.getCreationDate() == null || mitigationActionIdentifier.getCreationDate().equals(getCreationDate());
    }
}
