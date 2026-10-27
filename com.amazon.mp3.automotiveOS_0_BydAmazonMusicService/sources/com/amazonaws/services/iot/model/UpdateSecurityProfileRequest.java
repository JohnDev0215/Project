package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateSecurityProfileRequest extends AmazonWebServiceRequest implements Serializable {
    private List<String> additionalMetricsToRetain;
    private List<MetricToRetain> additionalMetricsToRetainV2;
    private Map<String, AlertTarget> alertTargets;
    private List<Behavior> behaviors;
    private Boolean deleteAdditionalMetricsToRetain;
    private Boolean deleteAlertTargets;
    private Boolean deleteBehaviors;
    private Long expectedVersion;
    private String securityProfileDescription;
    private String securityProfileName;

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public UpdateSecurityProfileRequest withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public String getSecurityProfileDescription() {
        return this.securityProfileDescription;
    }

    public void setSecurityProfileDescription(String str) {
        this.securityProfileDescription = str;
    }

    public UpdateSecurityProfileRequest withSecurityProfileDescription(String str) {
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

    public UpdateSecurityProfileRequest withBehaviors(Behavior... behaviorArr) {
        if (getBehaviors() == null) {
            this.behaviors = new ArrayList(behaviorArr.length);
        }
        for (Behavior behavior : behaviorArr) {
            this.behaviors.add(behavior);
        }
        return this;
    }

    public UpdateSecurityProfileRequest withBehaviors(Collection<Behavior> collection) {
        setBehaviors(collection);
        return this;
    }

    public Map<String, AlertTarget> getAlertTargets() {
        return this.alertTargets;
    }

    public void setAlertTargets(Map<String, AlertTarget> map) {
        this.alertTargets = map;
    }

    public UpdateSecurityProfileRequest withAlertTargets(Map<String, AlertTarget> map) {
        this.alertTargets = map;
        return this;
    }

    public UpdateSecurityProfileRequest addalertTargetsEntry(String str, AlertTarget alertTarget) {
        if (this.alertTargets == null) {
            this.alertTargets = new HashMap();
        }
        if (this.alertTargets.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.alertTargets.put(str, alertTarget);
        return this;
    }

    public UpdateSecurityProfileRequest clearalertTargetsEntries() {
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

    public UpdateSecurityProfileRequest withAdditionalMetricsToRetain(String... strArr) {
        if (getAdditionalMetricsToRetain() == null) {
            this.additionalMetricsToRetain = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.additionalMetricsToRetain.add(str);
        }
        return this;
    }

    public UpdateSecurityProfileRequest withAdditionalMetricsToRetain(Collection<String> collection) {
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

    public UpdateSecurityProfileRequest withAdditionalMetricsToRetainV2(MetricToRetain... metricToRetainArr) {
        if (getAdditionalMetricsToRetainV2() == null) {
            this.additionalMetricsToRetainV2 = new ArrayList(metricToRetainArr.length);
        }
        for (MetricToRetain metricToRetain : metricToRetainArr) {
            this.additionalMetricsToRetainV2.add(metricToRetain);
        }
        return this;
    }

    public UpdateSecurityProfileRequest withAdditionalMetricsToRetainV2(Collection<MetricToRetain> collection) {
        setAdditionalMetricsToRetainV2(collection);
        return this;
    }

    public Boolean isDeleteBehaviors() {
        return this.deleteBehaviors;
    }

    public Boolean getDeleteBehaviors() {
        return this.deleteBehaviors;
    }

    public void setDeleteBehaviors(Boolean bool) {
        this.deleteBehaviors = bool;
    }

    public UpdateSecurityProfileRequest withDeleteBehaviors(Boolean bool) {
        this.deleteBehaviors = bool;
        return this;
    }

    public Boolean isDeleteAlertTargets() {
        return this.deleteAlertTargets;
    }

    public Boolean getDeleteAlertTargets() {
        return this.deleteAlertTargets;
    }

    public void setDeleteAlertTargets(Boolean bool) {
        this.deleteAlertTargets = bool;
    }

    public UpdateSecurityProfileRequest withDeleteAlertTargets(Boolean bool) {
        this.deleteAlertTargets = bool;
        return this;
    }

    public Boolean isDeleteAdditionalMetricsToRetain() {
        return this.deleteAdditionalMetricsToRetain;
    }

    public Boolean getDeleteAdditionalMetricsToRetain() {
        return this.deleteAdditionalMetricsToRetain;
    }

    public void setDeleteAdditionalMetricsToRetain(Boolean bool) {
        this.deleteAdditionalMetricsToRetain = bool;
    }

    public UpdateSecurityProfileRequest withDeleteAdditionalMetricsToRetain(Boolean bool) {
        this.deleteAdditionalMetricsToRetain = bool;
        return this;
    }

    public Long getExpectedVersion() {
        return this.expectedVersion;
    }

    public void setExpectedVersion(Long l) {
        this.expectedVersion = l;
    }

    public UpdateSecurityProfileRequest withExpectedVersion(Long l) {
        this.expectedVersion = l;
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
        if (getDeleteBehaviors() != null) {
            sb.append("deleteBehaviors: " + getDeleteBehaviors() + ",");
        }
        if (getDeleteAlertTargets() != null) {
            sb.append("deleteAlertTargets: " + getDeleteAlertTargets() + ",");
        }
        if (getDeleteAdditionalMetricsToRetain() != null) {
            sb.append("deleteAdditionalMetricsToRetain: " + getDeleteAdditionalMetricsToRetain() + ",");
        }
        if (getExpectedVersion() != null) {
            sb.append("expectedVersion: " + getExpectedVersion());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode()) + 31) * 31) + (getSecurityProfileDescription() == null ? 0 : getSecurityProfileDescription().hashCode())) * 31) + (getBehaviors() == null ? 0 : getBehaviors().hashCode())) * 31) + (getAlertTargets() == null ? 0 : getAlertTargets().hashCode())) * 31) + (getAdditionalMetricsToRetain() == null ? 0 : getAdditionalMetricsToRetain().hashCode())) * 31) + (getAdditionalMetricsToRetainV2() == null ? 0 : getAdditionalMetricsToRetainV2().hashCode())) * 31) + (getDeleteBehaviors() == null ? 0 : getDeleteBehaviors().hashCode())) * 31) + (getDeleteAlertTargets() == null ? 0 : getDeleteAlertTargets().hashCode())) * 31) + (getDeleteAdditionalMetricsToRetain() == null ? 0 : getDeleteAdditionalMetricsToRetain().hashCode())) * 31) + (getExpectedVersion() != null ? getExpectedVersion().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateSecurityProfileRequest)) {
            return false;
        }
        UpdateSecurityProfileRequest updateSecurityProfileRequest = (UpdateSecurityProfileRequest) obj;
        if ((updateSecurityProfileRequest.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (updateSecurityProfileRequest.getSecurityProfileName() != null && !updateSecurityProfileRequest.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((updateSecurityProfileRequest.getSecurityProfileDescription() == null) ^ (getSecurityProfileDescription() == null)) {
            return false;
        }
        if (updateSecurityProfileRequest.getSecurityProfileDescription() != null && !updateSecurityProfileRequest.getSecurityProfileDescription().equals(getSecurityProfileDescription())) {
            return false;
        }
        if ((updateSecurityProfileRequest.getBehaviors() == null) ^ (getBehaviors() == null)) {
            return false;
        }
        if (updateSecurityProfileRequest.getBehaviors() != null && !updateSecurityProfileRequest.getBehaviors().equals(getBehaviors())) {
            return false;
        }
        if ((updateSecurityProfileRequest.getAlertTargets() == null) ^ (getAlertTargets() == null)) {
            return false;
        }
        if (updateSecurityProfileRequest.getAlertTargets() != null && !updateSecurityProfileRequest.getAlertTargets().equals(getAlertTargets())) {
            return false;
        }
        if ((updateSecurityProfileRequest.getAdditionalMetricsToRetain() == null) ^ (getAdditionalMetricsToRetain() == null)) {
            return false;
        }
        if (updateSecurityProfileRequest.getAdditionalMetricsToRetain() != null && !updateSecurityProfileRequest.getAdditionalMetricsToRetain().equals(getAdditionalMetricsToRetain())) {
            return false;
        }
        if ((updateSecurityProfileRequest.getAdditionalMetricsToRetainV2() == null) ^ (getAdditionalMetricsToRetainV2() == null)) {
            return false;
        }
        if (updateSecurityProfileRequest.getAdditionalMetricsToRetainV2() != null && !updateSecurityProfileRequest.getAdditionalMetricsToRetainV2().equals(getAdditionalMetricsToRetainV2())) {
            return false;
        }
        if ((updateSecurityProfileRequest.getDeleteBehaviors() == null) ^ (getDeleteBehaviors() == null)) {
            return false;
        }
        if (updateSecurityProfileRequest.getDeleteBehaviors() != null && !updateSecurityProfileRequest.getDeleteBehaviors().equals(getDeleteBehaviors())) {
            return false;
        }
        if ((updateSecurityProfileRequest.getDeleteAlertTargets() == null) ^ (getDeleteAlertTargets() == null)) {
            return false;
        }
        if (updateSecurityProfileRequest.getDeleteAlertTargets() != null && !updateSecurityProfileRequest.getDeleteAlertTargets().equals(getDeleteAlertTargets())) {
            return false;
        }
        if ((updateSecurityProfileRequest.getDeleteAdditionalMetricsToRetain() == null) ^ (getDeleteAdditionalMetricsToRetain() == null)) {
            return false;
        }
        if (updateSecurityProfileRequest.getDeleteAdditionalMetricsToRetain() != null && !updateSecurityProfileRequest.getDeleteAdditionalMetricsToRetain().equals(getDeleteAdditionalMetricsToRetain())) {
            return false;
        }
        if ((updateSecurityProfileRequest.getExpectedVersion() == null) ^ (getExpectedVersion() == null)) {
            return false;
        }
        return updateSecurityProfileRequest.getExpectedVersion() == null || updateSecurityProfileRequest.getExpectedVersion().equals(getExpectedVersion());
    }
}
