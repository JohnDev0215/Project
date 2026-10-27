package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class ViolationEvent implements Serializable {
    private Behavior behavior;
    private MetricValue metricValue;
    private String securityProfileName;
    private String thingName;
    private String verificationState;
    private String verificationStateDescription;
    private ViolationEventAdditionalInfo violationEventAdditionalInfo;
    private Date violationEventTime;
    private String violationEventType;
    private String violationId;

    public String getViolationId() {
        return this.violationId;
    }

    public void setViolationId(String str) {
        this.violationId = str;
    }

    public ViolationEvent withViolationId(String str) {
        this.violationId = str;
        return this;
    }

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public ViolationEvent withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public ViolationEvent withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public Behavior getBehavior() {
        return this.behavior;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    public ViolationEvent withBehavior(Behavior behavior) {
        this.behavior = behavior;
        return this;
    }

    public MetricValue getMetricValue() {
        return this.metricValue;
    }

    public void setMetricValue(MetricValue metricValue) {
        this.metricValue = metricValue;
    }

    public ViolationEvent withMetricValue(MetricValue metricValue) {
        this.metricValue = metricValue;
        return this;
    }

    public ViolationEventAdditionalInfo getViolationEventAdditionalInfo() {
        return this.violationEventAdditionalInfo;
    }

    public void setViolationEventAdditionalInfo(ViolationEventAdditionalInfo violationEventAdditionalInfo) {
        this.violationEventAdditionalInfo = violationEventAdditionalInfo;
    }

    public ViolationEvent withViolationEventAdditionalInfo(ViolationEventAdditionalInfo violationEventAdditionalInfo) {
        this.violationEventAdditionalInfo = violationEventAdditionalInfo;
        return this;
    }

    public String getViolationEventType() {
        return this.violationEventType;
    }

    public void setViolationEventType(String str) {
        this.violationEventType = str;
    }

    public ViolationEvent withViolationEventType(String str) {
        this.violationEventType = str;
        return this;
    }

    public void setViolationEventType(ViolationEventType violationEventType) {
        this.violationEventType = violationEventType.toString();
    }

    public ViolationEvent withViolationEventType(ViolationEventType violationEventType) {
        this.violationEventType = violationEventType.toString();
        return this;
    }

    public String getVerificationState() {
        return this.verificationState;
    }

    public void setVerificationState(String str) {
        this.verificationState = str;
    }

    public ViolationEvent withVerificationState(String str) {
        this.verificationState = str;
        return this;
    }

    public void setVerificationState(VerificationState verificationState) {
        this.verificationState = verificationState.toString();
    }

    public ViolationEvent withVerificationState(VerificationState verificationState) {
        this.verificationState = verificationState.toString();
        return this;
    }

    public String getVerificationStateDescription() {
        return this.verificationStateDescription;
    }

    public void setVerificationStateDescription(String str) {
        this.verificationStateDescription = str;
    }

    public ViolationEvent withVerificationStateDescription(String str) {
        this.verificationStateDescription = str;
        return this;
    }

    public Date getViolationEventTime() {
        return this.violationEventTime;
    }

    public void setViolationEventTime(Date date) {
        this.violationEventTime = date;
    }

    public ViolationEvent withViolationEventTime(Date date) {
        this.violationEventTime = date;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getViolationId() != null) {
            sb.append("violationId: " + getViolationId() + ",");
        }
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName() + ",");
        }
        if (getSecurityProfileName() != null) {
            sb.append("securityProfileName: " + getSecurityProfileName() + ",");
        }
        if (getBehavior() != null) {
            sb.append("behavior: " + getBehavior() + ",");
        }
        if (getMetricValue() != null) {
            sb.append("metricValue: " + getMetricValue() + ",");
        }
        if (getViolationEventAdditionalInfo() != null) {
            sb.append("violationEventAdditionalInfo: " + getViolationEventAdditionalInfo() + ",");
        }
        if (getViolationEventType() != null) {
            sb.append("violationEventType: " + getViolationEventType() + ",");
        }
        if (getVerificationState() != null) {
            sb.append("verificationState: " + getVerificationState() + ",");
        }
        if (getVerificationStateDescription() != null) {
            sb.append("verificationStateDescription: " + getVerificationStateDescription() + ",");
        }
        if (getViolationEventTime() != null) {
            sb.append("violationEventTime: " + getViolationEventTime());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((getViolationId() == null ? 0 : getViolationId().hashCode()) + 31) * 31) + (getThingName() == null ? 0 : getThingName().hashCode())) * 31) + (getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode())) * 31) + (getBehavior() == null ? 0 : getBehavior().hashCode())) * 31) + (getMetricValue() == null ? 0 : getMetricValue().hashCode())) * 31) + (getViolationEventAdditionalInfo() == null ? 0 : getViolationEventAdditionalInfo().hashCode())) * 31) + (getViolationEventType() == null ? 0 : getViolationEventType().hashCode())) * 31) + (getVerificationState() == null ? 0 : getVerificationState().hashCode())) * 31) + (getVerificationStateDescription() == null ? 0 : getVerificationStateDescription().hashCode())) * 31) + (getViolationEventTime() != null ? getViolationEventTime().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ViolationEvent)) {
            return false;
        }
        ViolationEvent violationEvent = (ViolationEvent) obj;
        if ((violationEvent.getViolationId() == null) ^ (getViolationId() == null)) {
            return false;
        }
        if (violationEvent.getViolationId() != null && !violationEvent.getViolationId().equals(getViolationId())) {
            return false;
        }
        if ((violationEvent.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (violationEvent.getThingName() != null && !violationEvent.getThingName().equals(getThingName())) {
            return false;
        }
        if ((violationEvent.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (violationEvent.getSecurityProfileName() != null && !violationEvent.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((violationEvent.getBehavior() == null) ^ (getBehavior() == null)) {
            return false;
        }
        if (violationEvent.getBehavior() != null && !violationEvent.getBehavior().equals(getBehavior())) {
            return false;
        }
        if ((violationEvent.getMetricValue() == null) ^ (getMetricValue() == null)) {
            return false;
        }
        if (violationEvent.getMetricValue() != null && !violationEvent.getMetricValue().equals(getMetricValue())) {
            return false;
        }
        if ((violationEvent.getViolationEventAdditionalInfo() == null) ^ (getViolationEventAdditionalInfo() == null)) {
            return false;
        }
        if (violationEvent.getViolationEventAdditionalInfo() != null && !violationEvent.getViolationEventAdditionalInfo().equals(getViolationEventAdditionalInfo())) {
            return false;
        }
        if ((violationEvent.getViolationEventType() == null) ^ (getViolationEventType() == null)) {
            return false;
        }
        if (violationEvent.getViolationEventType() != null && !violationEvent.getViolationEventType().equals(getViolationEventType())) {
            return false;
        }
        if ((violationEvent.getVerificationState() == null) ^ (getVerificationState() == null)) {
            return false;
        }
        if (violationEvent.getVerificationState() != null && !violationEvent.getVerificationState().equals(getVerificationState())) {
            return false;
        }
        if ((violationEvent.getVerificationStateDescription() == null) ^ (getVerificationStateDescription() == null)) {
            return false;
        }
        if (violationEvent.getVerificationStateDescription() != null && !violationEvent.getVerificationStateDescription().equals(getVerificationStateDescription())) {
            return false;
        }
        if ((violationEvent.getViolationEventTime() == null) ^ (getViolationEventTime() == null)) {
            return false;
        }
        return violationEvent.getViolationEventTime() == null || violationEvent.getViolationEventTime().equals(getViolationEventTime());
    }
}
