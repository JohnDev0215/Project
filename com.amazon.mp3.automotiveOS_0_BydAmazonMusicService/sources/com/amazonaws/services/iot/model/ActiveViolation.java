package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class ActiveViolation implements Serializable {
    private Behavior behavior;
    private Date lastViolationTime;
    private MetricValue lastViolationValue;
    private String securityProfileName;
    private String thingName;
    private String verificationState;
    private String verificationStateDescription;
    private ViolationEventAdditionalInfo violationEventAdditionalInfo;
    private String violationId;
    private Date violationStartTime;

    public String getViolationId() {
        return this.violationId;
    }

    public void setViolationId(String str) {
        this.violationId = str;
    }

    public ActiveViolation withViolationId(String str) {
        this.violationId = str;
        return this;
    }

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public ActiveViolation withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public ActiveViolation withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public Behavior getBehavior() {
        return this.behavior;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    public ActiveViolation withBehavior(Behavior behavior) {
        this.behavior = behavior;
        return this;
    }

    public MetricValue getLastViolationValue() {
        return this.lastViolationValue;
    }

    public void setLastViolationValue(MetricValue metricValue) {
        this.lastViolationValue = metricValue;
    }

    public ActiveViolation withLastViolationValue(MetricValue metricValue) {
        this.lastViolationValue = metricValue;
        return this;
    }

    public ViolationEventAdditionalInfo getViolationEventAdditionalInfo() {
        return this.violationEventAdditionalInfo;
    }

    public void setViolationEventAdditionalInfo(ViolationEventAdditionalInfo violationEventAdditionalInfo) {
        this.violationEventAdditionalInfo = violationEventAdditionalInfo;
    }

    public ActiveViolation withViolationEventAdditionalInfo(ViolationEventAdditionalInfo violationEventAdditionalInfo) {
        this.violationEventAdditionalInfo = violationEventAdditionalInfo;
        return this;
    }

    public String getVerificationState() {
        return this.verificationState;
    }

    public void setVerificationState(String str) {
        this.verificationState = str;
    }

    public ActiveViolation withVerificationState(String str) {
        this.verificationState = str;
        return this;
    }

    public void setVerificationState(VerificationState verificationState) {
        this.verificationState = verificationState.toString();
    }

    public ActiveViolation withVerificationState(VerificationState verificationState) {
        this.verificationState = verificationState.toString();
        return this;
    }

    public String getVerificationStateDescription() {
        return this.verificationStateDescription;
    }

    public void setVerificationStateDescription(String str) {
        this.verificationStateDescription = str;
    }

    public ActiveViolation withVerificationStateDescription(String str) {
        this.verificationStateDescription = str;
        return this;
    }

    public Date getLastViolationTime() {
        return this.lastViolationTime;
    }

    public void setLastViolationTime(Date date) {
        this.lastViolationTime = date;
    }

    public ActiveViolation withLastViolationTime(Date date) {
        this.lastViolationTime = date;
        return this;
    }

    public Date getViolationStartTime() {
        return this.violationStartTime;
    }

    public void setViolationStartTime(Date date) {
        this.violationStartTime = date;
    }

    public ActiveViolation withViolationStartTime(Date date) {
        this.violationStartTime = date;
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
        if (getLastViolationValue() != null) {
            sb.append("lastViolationValue: " + getLastViolationValue() + ",");
        }
        if (getViolationEventAdditionalInfo() != null) {
            sb.append("violationEventAdditionalInfo: " + getViolationEventAdditionalInfo() + ",");
        }
        if (getVerificationState() != null) {
            sb.append("verificationState: " + getVerificationState() + ",");
        }
        if (getVerificationStateDescription() != null) {
            sb.append("verificationStateDescription: " + getVerificationStateDescription() + ",");
        }
        if (getLastViolationTime() != null) {
            sb.append("lastViolationTime: " + getLastViolationTime() + ",");
        }
        if (getViolationStartTime() != null) {
            sb.append("violationStartTime: " + getViolationStartTime());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((getViolationId() == null ? 0 : getViolationId().hashCode()) + 31) * 31) + (getThingName() == null ? 0 : getThingName().hashCode())) * 31) + (getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode())) * 31) + (getBehavior() == null ? 0 : getBehavior().hashCode())) * 31) + (getLastViolationValue() == null ? 0 : getLastViolationValue().hashCode())) * 31) + (getViolationEventAdditionalInfo() == null ? 0 : getViolationEventAdditionalInfo().hashCode())) * 31) + (getVerificationState() == null ? 0 : getVerificationState().hashCode())) * 31) + (getVerificationStateDescription() == null ? 0 : getVerificationStateDescription().hashCode())) * 31) + (getLastViolationTime() == null ? 0 : getLastViolationTime().hashCode())) * 31) + (getViolationStartTime() != null ? getViolationStartTime().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ActiveViolation)) {
            return false;
        }
        ActiveViolation activeViolation = (ActiveViolation) obj;
        if ((activeViolation.getViolationId() == null) ^ (getViolationId() == null)) {
            return false;
        }
        if (activeViolation.getViolationId() != null && !activeViolation.getViolationId().equals(getViolationId())) {
            return false;
        }
        if ((activeViolation.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (activeViolation.getThingName() != null && !activeViolation.getThingName().equals(getThingName())) {
            return false;
        }
        if ((activeViolation.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (activeViolation.getSecurityProfileName() != null && !activeViolation.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((activeViolation.getBehavior() == null) ^ (getBehavior() == null)) {
            return false;
        }
        if (activeViolation.getBehavior() != null && !activeViolation.getBehavior().equals(getBehavior())) {
            return false;
        }
        if ((activeViolation.getLastViolationValue() == null) ^ (getLastViolationValue() == null)) {
            return false;
        }
        if (activeViolation.getLastViolationValue() != null && !activeViolation.getLastViolationValue().equals(getLastViolationValue())) {
            return false;
        }
        if ((activeViolation.getViolationEventAdditionalInfo() == null) ^ (getViolationEventAdditionalInfo() == null)) {
            return false;
        }
        if (activeViolation.getViolationEventAdditionalInfo() != null && !activeViolation.getViolationEventAdditionalInfo().equals(getViolationEventAdditionalInfo())) {
            return false;
        }
        if ((activeViolation.getVerificationState() == null) ^ (getVerificationState() == null)) {
            return false;
        }
        if (activeViolation.getVerificationState() != null && !activeViolation.getVerificationState().equals(getVerificationState())) {
            return false;
        }
        if ((activeViolation.getVerificationStateDescription() == null) ^ (getVerificationStateDescription() == null)) {
            return false;
        }
        if (activeViolation.getVerificationStateDescription() != null && !activeViolation.getVerificationStateDescription().equals(getVerificationStateDescription())) {
            return false;
        }
        if ((activeViolation.getLastViolationTime() == null) ^ (getLastViolationTime() == null)) {
            return false;
        }
        if (activeViolation.getLastViolationTime() != null && !activeViolation.getLastViolationTime().equals(getLastViolationTime())) {
            return false;
        }
        if ((activeViolation.getViolationStartTime() == null) ^ (getViolationStartTime() == null)) {
            return false;
        }
        return activeViolation.getViolationStartTime() == null || activeViolation.getViolationStartTime().equals(getViolationStartTime());
    }
}
