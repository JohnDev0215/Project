package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeSecurityProfileResult implements Serializable {
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

    public DescribeSecurityProfileResult withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public String getSecurityProfileArn() {
        return this.securityProfileArn;
    }

    public void setSecurityProfileArn(String str) {
        this.securityProfileArn = str;
    }

    public DescribeSecurityProfileResult withSecurityProfileArn(String str) {
        this.securityProfileArn = str;
        return this;
    }

    public String getSecurityProfileDescription() {
        return this.securityProfileDescription;
    }

    public void setSecurityProfileDescription(String str) {
        this.securityProfileDescription = str;
    }

    public DescribeSecurityProfileResult withSecurityProfileDescription(String str) {
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

    public DescribeSecurityProfileResult withBehaviors(Behavior... behaviorArr) {
        if (getBehaviors() == null) {
            this.behaviors = new ArrayList(behaviorArr.length);
        }
        for (Behavior behavior : behaviorArr) {
            this.behaviors.add(behavior);
        }
        return this;
    }

    public DescribeSecurityProfileResult withBehaviors(Collection<Behavior> collection) {
        setBehaviors(collection);
        return this;
    }

    public Map<String, AlertTarget> getAlertTargets() {
        return this.alertTargets;
    }

    public void setAlertTargets(Map<String, AlertTarget> map) {
        this.alertTargets = map;
    }

    public DescribeSecurityProfileResult withAlertTargets(Map<String, AlertTarget> map) {
        this.alertTargets = map;
        return this;
    }

    public DescribeSecurityProfileResult addalertTargetsEntry(String str, AlertTarget alertTarget) {
        if (this.alertTargets == null) {
            this.alertTargets = new HashMap();
        }
        if (this.alertTargets.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.alertTargets.put(str, alertTarget);
        return this;
    }

    public DescribeSecurityProfileResult clearalertTargetsEntries() {
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

    public DescribeSecurityProfileResult withAdditionalMetricsToRetain(String... strArr) {
        if (getAdditionalMetricsToRetain() == null) {
            this.additionalMetricsToRetain = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.additionalMetricsToRetain.add(str);
        }
        return this;
    }

    public DescribeSecurityProfileResult withAdditionalMetricsToRetain(Collection<String> collection) {
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

    public DescribeSecurityProfileResult withAdditionalMetricsToRetainV2(MetricToRetain... metricToRetainArr) {
        if (getAdditionalMetricsToRetainV2() == null) {
            this.additionalMetricsToRetainV2 = new ArrayList(metricToRetainArr.length);
        }
        for (MetricToRetain metricToRetain : metricToRetainArr) {
            this.additionalMetricsToRetainV2.add(metricToRetain);
        }
        return this;
    }

    public DescribeSecurityProfileResult withAdditionalMetricsToRetainV2(Collection<MetricToRetain> collection) {
        setAdditionalMetricsToRetainV2(collection);
        return this;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public DescribeSecurityProfileResult withVersion(Long l) {
        this.version = l;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public DescribeSecurityProfileResult withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public DescribeSecurityProfileResult withLastModifiedDate(Date date) {
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
        if (obj == null || !(obj instanceof DescribeSecurityProfileResult)) {
            return false;
        }
        DescribeSecurityProfileResult describeSecurityProfileResult = (DescribeSecurityProfileResult) obj;
        if ((describeSecurityProfileResult.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (describeSecurityProfileResult.getSecurityProfileName() != null && !describeSecurityProfileResult.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((describeSecurityProfileResult.getSecurityProfileArn() == null) ^ (getSecurityProfileArn() == null)) {
            return false;
        }
        if (describeSecurityProfileResult.getSecurityProfileArn() != null && !describeSecurityProfileResult.getSecurityProfileArn().equals(getSecurityProfileArn())) {
            return false;
        }
        if ((describeSecurityProfileResult.getSecurityProfileDescription() == null) ^ (getSecurityProfileDescription() == null)) {
            return false;
        }
        if (describeSecurityProfileResult.getSecurityProfileDescription() != null && !describeSecurityProfileResult.getSecurityProfileDescription().equals(getSecurityProfileDescription())) {
            return false;
        }
        if ((describeSecurityProfileResult.getBehaviors() == null) ^ (getBehaviors() == null)) {
            return false;
        }
        if (describeSecurityProfileResult.getBehaviors() != null && !describeSecurityProfileResult.getBehaviors().equals(getBehaviors())) {
            return false;
        }
        if ((describeSecurityProfileResult.getAlertTargets() == null) ^ (getAlertTargets() == null)) {
            return false;
        }
        if (describeSecurityProfileResult.getAlertTargets() != null && !describeSecurityProfileResult.getAlertTargets().equals(getAlertTargets())) {
            return false;
        }
        if ((describeSecurityProfileResult.getAdditionalMetricsToRetain() == null) ^ (getAdditionalMetricsToRetain() == null)) {
            return false;
        }
        if (describeSecurityProfileResult.getAdditionalMetricsToRetain() != null && !describeSecurityProfileResult.getAdditionalMetricsToRetain().equals(getAdditionalMetricsToRetain())) {
            return false;
        }
        if ((describeSecurityProfileResult.getAdditionalMetricsToRetainV2() == null) ^ (getAdditionalMetricsToRetainV2() == null)) {
            return false;
        }
        if (describeSecurityProfileResult.getAdditionalMetricsToRetainV2() != null && !describeSecurityProfileResult.getAdditionalMetricsToRetainV2().equals(getAdditionalMetricsToRetainV2())) {
            return false;
        }
        if ((describeSecurityProfileResult.getVersion() == null) ^ (getVersion() == null)) {
            return false;
        }
        if (describeSecurityProfileResult.getVersion() != null && !describeSecurityProfileResult.getVersion().equals(getVersion())) {
            return false;
        }
        if ((describeSecurityProfileResult.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (describeSecurityProfileResult.getCreationDate() != null && !describeSecurityProfileResult.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((describeSecurityProfileResult.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        return describeSecurityProfileResult.getLastModifiedDate() == null || describeSecurityProfileResult.getLastModifiedDate().equals(getLastModifiedDate());
    }
}
