package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class CreateSecurityProfileRequest extends AmazonWebServiceRequest implements Serializable {
    private List<String> additionalMetricsToRetain;
    private List<MetricToRetain> additionalMetricsToRetainV2;
    private Map<String, AlertTarget> alertTargets;
    private List<Behavior> behaviors;
    private String securityProfileDescription;
    private String securityProfileName;
    private List<Tag> tags;

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public CreateSecurityProfileRequest withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public String getSecurityProfileDescription() {
        return this.securityProfileDescription;
    }

    public void setSecurityProfileDescription(String str) {
        this.securityProfileDescription = str;
    }

    public CreateSecurityProfileRequest withSecurityProfileDescription(String str) {
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

    public CreateSecurityProfileRequest withBehaviors(Behavior... behaviorArr) {
        if (getBehaviors() == null) {
            this.behaviors = new ArrayList(behaviorArr.length);
        }
        for (Behavior behavior : behaviorArr) {
            this.behaviors.add(behavior);
        }
        return this;
    }

    public CreateSecurityProfileRequest withBehaviors(Collection<Behavior> collection) {
        setBehaviors(collection);
        return this;
    }

    public Map<String, AlertTarget> getAlertTargets() {
        return this.alertTargets;
    }

    public void setAlertTargets(Map<String, AlertTarget> map) {
        this.alertTargets = map;
    }

    public CreateSecurityProfileRequest withAlertTargets(Map<String, AlertTarget> map) {
        this.alertTargets = map;
        return this;
    }

    public CreateSecurityProfileRequest addalertTargetsEntry(String str, AlertTarget alertTarget) {
        if (this.alertTargets == null) {
            this.alertTargets = new HashMap();
        }
        if (this.alertTargets.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.alertTargets.put(str, alertTarget);
        return this;
    }

    public CreateSecurityProfileRequest clearalertTargetsEntries() {
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

    public CreateSecurityProfileRequest withAdditionalMetricsToRetain(String... strArr) {
        if (getAdditionalMetricsToRetain() == null) {
            this.additionalMetricsToRetain = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.additionalMetricsToRetain.add(str);
        }
        return this;
    }

    public CreateSecurityProfileRequest withAdditionalMetricsToRetain(Collection<String> collection) {
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

    public CreateSecurityProfileRequest withAdditionalMetricsToRetainV2(MetricToRetain... metricToRetainArr) {
        if (getAdditionalMetricsToRetainV2() == null) {
            this.additionalMetricsToRetainV2 = new ArrayList(metricToRetainArr.length);
        }
        for (MetricToRetain metricToRetain : metricToRetainArr) {
            this.additionalMetricsToRetainV2.add(metricToRetain);
        }
        return this;
    }

    public CreateSecurityProfileRequest withAdditionalMetricsToRetainV2(Collection<MetricToRetain> collection) {
        setAdditionalMetricsToRetainV2(collection);
        return this;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(Collection<Tag> collection) {
        if (collection == null) {
            this.tags = null;
        } else {
            this.tags = new ArrayList(collection);
        }
    }

    public CreateSecurityProfileRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateSecurityProfileRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSecurityProfileName() != null) {
            sb.append("securityProfileName: " + getSecurityProfileName() + ",");
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
        if (getTags() != null) {
            sb.append("tags: " + getTags());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode()) + 31) * 31) + (getSecurityProfileDescription() == null ? 0 : getSecurityProfileDescription().hashCode())) * 31) + (getBehaviors() == null ? 0 : getBehaviors().hashCode())) * 31) + (getAlertTargets() == null ? 0 : getAlertTargets().hashCode())) * 31) + (getAdditionalMetricsToRetain() == null ? 0 : getAdditionalMetricsToRetain().hashCode())) * 31) + (getAdditionalMetricsToRetainV2() == null ? 0 : getAdditionalMetricsToRetainV2().hashCode())) * 31) + (getTags() != null ? getTags().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateSecurityProfileRequest)) {
            return false;
        }
        CreateSecurityProfileRequest createSecurityProfileRequest = (CreateSecurityProfileRequest) obj;
        if ((createSecurityProfileRequest.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (createSecurityProfileRequest.getSecurityProfileName() != null && !createSecurityProfileRequest.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((createSecurityProfileRequest.getSecurityProfileDescription() == null) ^ (getSecurityProfileDescription() == null)) {
            return false;
        }
        if (createSecurityProfileRequest.getSecurityProfileDescription() != null && !createSecurityProfileRequest.getSecurityProfileDescription().equals(getSecurityProfileDescription())) {
            return false;
        }
        if ((createSecurityProfileRequest.getBehaviors() == null) ^ (getBehaviors() == null)) {
            return false;
        }
        if (createSecurityProfileRequest.getBehaviors() != null && !createSecurityProfileRequest.getBehaviors().equals(getBehaviors())) {
            return false;
        }
        if ((createSecurityProfileRequest.getAlertTargets() == null) ^ (getAlertTargets() == null)) {
            return false;
        }
        if (createSecurityProfileRequest.getAlertTargets() != null && !createSecurityProfileRequest.getAlertTargets().equals(getAlertTargets())) {
            return false;
        }
        if ((createSecurityProfileRequest.getAdditionalMetricsToRetain() == null) ^ (getAdditionalMetricsToRetain() == null)) {
            return false;
        }
        if (createSecurityProfileRequest.getAdditionalMetricsToRetain() != null && !createSecurityProfileRequest.getAdditionalMetricsToRetain().equals(getAdditionalMetricsToRetain())) {
            return false;
        }
        if ((createSecurityProfileRequest.getAdditionalMetricsToRetainV2() == null) ^ (getAdditionalMetricsToRetainV2() == null)) {
            return false;
        }
        if (createSecurityProfileRequest.getAdditionalMetricsToRetainV2() != null && !createSecurityProfileRequest.getAdditionalMetricsToRetainV2().equals(getAdditionalMetricsToRetainV2())) {
            return false;
        }
        if ((createSecurityProfileRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return createSecurityProfileRequest.getTags() == null || createSecurityProfileRequest.getTags().equals(getTags());
    }
}
