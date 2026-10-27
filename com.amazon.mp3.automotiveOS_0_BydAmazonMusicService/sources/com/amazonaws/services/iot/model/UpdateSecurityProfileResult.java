package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateSecurityProfileResult implements Serializable {
    private List<String> additionalMetricsToRetain;
    private List<MetricToRetain> additionalMetricsToRetainV2;
    private Map<String, AlertTarget> alertTargets;
    private List<Behavior> behaviors;
    private Date creationDate;
    private Date lastModifiedDate;
    private String securityProfileArn;
    private String securityProfileDescription;
    private String securityProfileName;
    private Long version;

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public UpdateSecurityProfileResult withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public String getSecurityProfileArn() {
        return this.securityProfileArn;
    }

    public void setSecurityProfileArn(String str) {
        this.securityProfileArn = str;
    }

    public UpdateSecurityProfileResult withSecurityProfileArn(String str) {
        this.securityProfileArn = str;
        return this;
    }

    public String getSecurityProfileDescription() {
        return this.securityProfileDescription;
    }

    public void setSecurityProfileDescription(String str) {
        this.securityProfileDescription = str;
    }

    public UpdateSecurityProfileResult withSecurityProfileDescription(String str) {
        this.securityProfileDescription = str;
        return this;
    }

    public List<Behavior> getBehaviors() {
        return this.behaviors;
    }

    public void setBehaviors(Collection<Behavior> collection) {
        if (collection == null) {
            this.behaviors = null;
        } else {
            this.behaviors = new ArrayList(collection);
        }
    }

    public UpdateSecurityProfileResult withBehaviors(Behavior... behaviorArr) {
        if (getBehaviors() == null) {
            this.behaviors = new ArrayList(behaviorArr.length);
        }
        for (Behavior behavior : behaviorArr) {
            this.behaviors.add(behavior);
        }
        return this;
    }

    public UpdateSecurityProfileResult withBehaviors(Collection<Behavior> collection) {
        setBehaviors(collection);
        return this;
    }

    public Map<String, AlertTarget> getAlertTargets() {
        return this.alertTargets;
    }

    public void setAlertTargets(Map<String, AlertTarget> map) {
        this.alertTargets = map;
    }

    public UpdateSecurityProfileResult withAlertTargets(Map<String, AlertTarget> map) {
        this.alertTargets = map;
        return this;
    }

    public UpdateSecurityProfileResult addalertTargetsEntry(String str, AlertTarget alertTarget) {
        if (this.alertTargets == null) {
            this.alertTargets = new HashMap();
        }
        if (this.alertTargets.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.alertTargets.put(str, alertTarget);
        return this;
    }

    public UpdateSecurityProfileResult clearalertTargetsEntries() {
        this.alertTargets = null;
        return this;
    }

    public List<String> getAdditionalMetricsToRetain() {
        return this.additionalMetricsToRetain;
    }

    public void setAdditionalMetricsToRetain(Collection<String> collection) {
        if (collection == null) {
            this.additionalMetricsToRetain = null;
        } else {
            this.additionalMetricsToRetain = new ArrayList(collection);
        }
    }

    public UpdateSecurityProfileResult withAdditionalMetricsToRetain(String... strArr) {
        if (getAdditionalMetricsToRetain() == null) {
            this.additionalMetricsToRetain = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.additionalMetricsToRetain.add(str);
        }
        return this;
    }

    public UpdateSecurityProfileResult withAdditionalMetricsToRetain(Collection<String> collection) {
        setAdditionalMetricsToRetain(collection);
        return this;
    }

    public List<MetricToRetain> getAdditionalMetricsToRetainV2() {
        return this.additionalMetricsToRetainV2;
    }

    public void setAdditionalMetricsToRetainV2(Collection<MetricToRetain> collection) {
        if (collection == null) {
            this.additionalMetricsToRetainV2 = null;
        } else {
            this.additionalMetricsToRetainV2 = new ArrayList(collection);
        }
    }

    public UpdateSecurityProfileResult withAdditionalMetricsToRetainV2(MetricToRetain... metricToRetainArr) {
        if (getAdditionalMetricsToRetainV2() == null) {
            this.additionalMetricsToRetainV2 = new ArrayList(metricToRetainArr.length);
        }
        for (MetricToRetain metricToRetain : metricToRetainArr) {
            this.additionalMetricsToRetainV2.add(metricToRetain);
        }
        return this;
    }

    public UpdateSecurityProfileResult withAdditionalMetricsToRetainV2(Collection<MetricToRetain> collection) {
        setAdditionalMetricsToRetainV2(collection);
        return this;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public UpdateSecurityProfileResult withVersion(Long l) {
        this.version = l;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public UpdateSecurityProfileResult withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public UpdateSecurityProfileResult withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSecurityProfileName() != null) {
            sb.append("securityProfileName: " + getSecurityProfileName() + ",");
        }
        if (getSecurityProfileArn() != null) {
            sb.append("securityProfileArn: " + getSecurityProfileArn() + ",");
        }
        if (getSecurityProfileDescription() != null) {
            sb.append("securityProfileDescription: " + getSecurityProfileDescription() + ",");
        }
        if (getBehaviors() != null) {
            sb.append("behaviors: " + getBehaviors() + ",");
        }
        if (getAlertTargets() != null) {
            sb.append("alertTargets: " + getAlertTargets() + ",");
        }
        if (getAdditionalMetricsToRetain() != null) {
            sb.append("additionalMetricsToRetain: " + getAdditionalMetricsToRetain() + ",");
        }
        if (getAdditionalMetricsToRetainV2() != null) {
            sb.append("additionalMetricsToRetainV2: " + getAdditionalMetricsToRetainV2() + ",");
        }
        if (getVersion() != null) {
            sb.append("version: " + getVersion() + ",");
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
        return (((((((((((((((((((getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode()) + 31) * 31) + (getSecurityProfileArn() == null ? 0 : getSecurityProfileArn().hashCode())) * 31) + (getSecurityProfileDescription() == null ? 0 : getSecurityProfileDescription().hashCode())) * 31) + (getBehaviors() == null ? 0 : getBehaviors().hashCode())) * 31) + (getAlertTargets() == null ? 0 : getAlertTargets().hashCode())) * 31) + (getAdditionalMetricsToRetain() == null ? 0 : getAdditionalMetricsToRetain().hashCode())) * 31) + (getAdditionalMetricsToRetainV2() == null ? 0 : getAdditionalMetricsToRetainV2().hashCode())) * 31) + (getVersion() == null ? 0 : getVersion().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() != null ? getLastModifiedDate().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateSecurityProfileResult)) {
            return false;
        }
        UpdateSecurityProfileResult updateSecurityProfileResult = (UpdateSecurityProfileResult) obj;
        if ((updateSecurityProfileResult.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (updateSecurityProfileResult.getSecurityProfileName() != null && !updateSecurityProfileResult.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((updateSecurityProfileResult.getSecurityProfileArn() == null) ^ (getSecurityProfileArn() == null)) {
            return false;
        }
        if (updateSecurityProfileResult.getSecurityProfileArn() != null && !updateSecurityProfileResult.getSecurityProfileArn().equals(getSecurityProfileArn())) {
            return false;
        }
        if ((updateSecurityProfileResult.getSecurityProfileDescription() == null) ^ (getSecurityProfileDescription() == null)) {
            return false;
        }
        if (updateSecurityProfileResult.getSecurityProfileDescription() != null && !updateSecurityProfileResult.getSecurityProfileDescription().equals(getSecurityProfileDescription())) {
            return false;
        }
        if ((updateSecurityProfileResult.getBehaviors() == null) ^ (getBehaviors() == null)) {
            return false;
        }
        if (updateSecurityProfileResult.getBehaviors() != null && !updateSecurityProfileResult.getBehaviors().equals(getBehaviors())) {
            return false;
        }
        if ((updateSecurityProfileResult.getAlertTargets() == null) ^ (getAlertTargets() == null)) {
            return false;
        }
        if (updateSecurityProfileResult.getAlertTargets() != null && !updateSecurityProfileResult.getAlertTargets().equals(getAlertTargets())) {
            return false;
        }
        if ((updateSecurityProfileResult.getAdditionalMetricsToRetain() == null) ^ (getAdditionalMetricsToRetain() == null)) {
            return false;
        }
        if (updateSecurityProfileResult.getAdditionalMetricsToRetain() != null && !updateSecurityProfileResult.getAdditionalMetricsToRetain().equals(getAdditionalMetricsToRetain())) {
            return false;
        }
        if ((updateSecurityProfileResult.getAdditionalMetricsToRetainV2() == null) ^ (getAdditionalMetricsToRetainV2() == null)) {
            return false;
        }
        if (updateSecurityProfileResult.getAdditionalMetricsToRetainV2() != null && !updateSecurityProfileResult.getAdditionalMetricsToRetainV2().equals(getAdditionalMetricsToRetainV2())) {
            return false;
        }
        if ((updateSecurityProfileResult.getVersion() == null) ^ (getVersion() == null)) {
            return false;
        }
        if (updateSecurityProfileResult.getVersion() != null && !updateSecurityProfileResult.getVersion().equals(getVersion())) {
            return false;
        }
        if ((updateSecurityProfileResult.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (updateSecurityProfileResult.getCreationDate() != null && !updateSecurityProfileResult.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((updateSecurityProfileResult.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        return updateSecurityProfileResult.getLastModifiedDate() == null || updateSecurityProfileResult.getLastModifiedDate().equals(getLastModifiedDate());
    }
}
