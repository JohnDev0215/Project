package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DetectMitigationActionsTaskTarget implements Serializable {
    private String behaviorName;
    private String securityProfileName;
    private List<String> violationIds;

    public List<String> getViolationIds() {
        return this.violationIds;
    }

    public void setViolationIds(Collection<String> collection) {
        if (collection == null) {
            this.violationIds = null;
        } else {
            this.violationIds = new ArrayList(collection);
        }
    }

    public DetectMitigationActionsTaskTarget withViolationIds(String... strArr) {
        if (getViolationIds() == null) {
            this.violationIds = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.violationIds.add(str);
        }
        return this;
    }

    public DetectMitigationActionsTaskTarget withViolationIds(Collection<String> collection) {
        setViolationIds(collection);
        return this;
    }

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public DetectMitigationActionsTaskTarget withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public String getBehaviorName() {
        return this.behaviorName;
    }

    public void setBehaviorName(String str) {
        this.behaviorName = str;
    }

    public DetectMitigationActionsTaskTarget withBehaviorName(String str) {
        this.behaviorName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getViolationIds() != null) {
            sb.append("violationIds: " + getViolationIds() + ",");
        }
        if (getSecurityProfileName() != null) {
            sb.append("securityProfileName: " + getSecurityProfileName() + ",");
        }
        if (getBehaviorName() != null) {
            sb.append("behaviorName: " + getBehaviorName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getViolationIds() == null ? 0 : getViolationIds().hashCode()) + 31) * 31) + (getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode())) * 31) + (getBehaviorName() != null ? getBehaviorName().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DetectMitigationActionsTaskTarget)) {
            return false;
        }
        DetectMitigationActionsTaskTarget detectMitigationActionsTaskTarget = (DetectMitigationActionsTaskTarget) obj;
        if ((detectMitigationActionsTaskTarget.getViolationIds() == null) ^ (getViolationIds() == null)) {
            return false;
        }
        if (detectMitigationActionsTaskTarget.getViolationIds() != null && !detectMitigationActionsTaskTarget.getViolationIds().equals(getViolationIds())) {
            return false;
        }
        if ((detectMitigationActionsTaskTarget.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (detectMitigationActionsTaskTarget.getSecurityProfileName() != null && !detectMitigationActionsTaskTarget.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((detectMitigationActionsTaskTarget.getBehaviorName() == null) ^ (getBehaviorName() == null)) {
            return false;
        }
        return detectMitigationActionsTaskTarget.getBehaviorName() == null || detectMitigationActionsTaskTarget.getBehaviorName().equals(getBehaviorName());
    }
}
