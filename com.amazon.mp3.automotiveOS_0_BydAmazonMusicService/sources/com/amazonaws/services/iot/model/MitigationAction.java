package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class MitigationAction implements Serializable {
    private MitigationActionParams actionParams;
    private String id;
    private String name;
    private String roleArn;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public MitigationAction withName(String str) {
        this.name = str;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public MitigationAction withId(String str) {
        this.id = str;
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public MitigationAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public MitigationActionParams getActionParams() {
        return this.actionParams;
    }

    public void setActionParams(MitigationActionParams mitigationActionParams) {
        this.actionParams = mitigationActionParams;
    }

    public MitigationAction withActionParams(MitigationActionParams mitigationActionParams) {
        this.actionParams = mitigationActionParams;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getName() != null) {
            sb.append("name: " + getName() + ",");
        }
        if (getId() != null) {
            sb.append("id: " + getId() + ",");
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
        return (((((((getName() == null ? 0 : getName().hashCode()) + 31) * 31) + (getId() == null ? 0 : getId().hashCode())) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getActionParams() != null ? getActionParams().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MitigationAction)) {
            return false;
        }
        MitigationAction mitigationAction = (MitigationAction) obj;
        if ((mitigationAction.getName() == null) ^ (getName() == null)) {
            return false;
        }
        if (mitigationAction.getName() != null && !mitigationAction.getName().equals(getName())) {
            return false;
        }
        if ((mitigationAction.getId() == null) ^ (getId() == null)) {
            return false;
        }
        if (mitigationAction.getId() != null && !mitigationAction.getId().equals(getId())) {
            return false;
        }
        if ((mitigationAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (mitigationAction.getRoleArn() != null && !mitigationAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((mitigationAction.getActionParams() == null) ^ (getActionParams() == null)) {
            return false;
        }
        return mitigationAction.getActionParams() == null || mitigationAction.getActionParams().equals(getActionParams());
    }
}
