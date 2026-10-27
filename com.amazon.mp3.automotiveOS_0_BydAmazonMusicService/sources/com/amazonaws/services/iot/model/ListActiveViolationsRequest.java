package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ListActiveViolationsRequest extends AmazonWebServiceRequest implements Serializable {
    private String behaviorCriteriaType;
    private Boolean listSuppressedAlerts;
    private Integer maxResults;
    private String nextToken;
    private String securityProfileName;
    private String thingName;
    private String verificationState;

    public String getThingName() {
        return this.thingName;
    }

    public void setThingName(String str) {
        this.thingName = str;
    }

    public ListActiveViolationsRequest withThingName(String str) {
        this.thingName = str;
        return this;
    }

    public String getSecurityProfileName() {
        return this.securityProfileName;
    }

    public void setSecurityProfileName(String str) {
        this.securityProfileName = str;
    }

    public ListActiveViolationsRequest withSecurityProfileName(String str) {
        this.securityProfileName = str;
        return this;
    }

    public String getBehaviorCriteriaType() {
        return this.behaviorCriteriaType;
    }

    public void setBehaviorCriteriaType(String str) {
        this.behaviorCriteriaType = str;
    }

    public ListActiveViolationsRequest withBehaviorCriteriaType(String str) {
        this.behaviorCriteriaType = str;
        return this;
    }

    public void setBehaviorCriteriaType(BehaviorCriteriaType behaviorCriteriaType) {
        this.behaviorCriteriaType = behaviorCriteriaType.toString();
    }

    public ListActiveViolationsRequest withBehaviorCriteriaType(BehaviorCriteriaType behaviorCriteriaType) {
        this.behaviorCriteriaType = behaviorCriteriaType.toString();
        return this;
    }

    public Boolean isListSuppressedAlerts() {
        return this.listSuppressedAlerts;
    }

    public Boolean getListSuppressedAlerts() {
        return this.listSuppressedAlerts;
    }

    public void setListSuppressedAlerts(Boolean bool) {
        this.listSuppressedAlerts = bool;
    }

    public ListActiveViolationsRequest withListSuppressedAlerts(Boolean bool) {
        this.listSuppressedAlerts = bool;
        return this;
    }

    public String getVerificationState() {
        return this.verificationState;
    }

    public void setVerificationState(String str) {
        this.verificationState = str;
    }

    public ListActiveViolationsRequest withVerificationState(String str) {
        this.verificationState = str;
        return this;
    }

    public void setVerificationState(VerificationState verificationState) {
        this.verificationState = verificationState.toString();
    }

    public ListActiveViolationsRequest withVerificationState(VerificationState verificationState) {
        this.verificationState = verificationState.toString();
        return this;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public ListActiveViolationsRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public ListActiveViolationsRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getThingName() != null) {
            sb.append("thingName: " + getThingName() + ",");
        }
        if (getSecurityProfileName() != null) {
            sb.append("securityProfileName: " + getSecurityProfileName() + ",");
        }
        if (getBehaviorCriteriaType() != null) {
            sb.append("behaviorCriteriaType: " + getBehaviorCriteriaType() + ",");
        }
        if (getListSuppressedAlerts() != null) {
            sb.append("listSuppressedAlerts: " + getListSuppressedAlerts() + ",");
        }
        if (getVerificationState() != null) {
            sb.append("verificationState: " + getVerificationState() + ",");
        }
        if (getNextToken() != null) {
            sb.append("nextToken: " + getNextToken() + ",");
        }
        if (getMaxResults() != null) {
            sb.append("maxResults: " + getMaxResults());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((getThingName() == null ? 0 : getThingName().hashCode()) + 31) * 31) + (getSecurityProfileName() == null ? 0 : getSecurityProfileName().hashCode())) * 31) + (getBehaviorCriteriaType() == null ? 0 : getBehaviorCriteriaType().hashCode())) * 31) + (getListSuppressedAlerts() == null ? 0 : getListSuppressedAlerts().hashCode())) * 31) + (getVerificationState() == null ? 0 : getVerificationState().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getMaxResults() != null ? getMaxResults().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListActiveViolationsRequest)) {
            return false;
        }
        ListActiveViolationsRequest listActiveViolationsRequest = (ListActiveViolationsRequest) obj;
        if ((listActiveViolationsRequest.getThingName() == null) ^ (getThingName() == null)) {
            return false;
        }
        if (listActiveViolationsRequest.getThingName() != null && !listActiveViolationsRequest.getThingName().equals(getThingName())) {
            return false;
        }
        if ((listActiveViolationsRequest.getSecurityProfileName() == null) ^ (getSecurityProfileName() == null)) {
            return false;
        }
        if (listActiveViolationsRequest.getSecurityProfileName() != null && !listActiveViolationsRequest.getSecurityProfileName().equals(getSecurityProfileName())) {
            return false;
        }
        if ((listActiveViolationsRequest.getBehaviorCriteriaType() == null) ^ (getBehaviorCriteriaType() == null)) {
            return false;
        }
        if (listActiveViolationsRequest.getBehaviorCriteriaType() != null && !listActiveViolationsRequest.getBehaviorCriteriaType().equals(getBehaviorCriteriaType())) {
            return false;
        }
        if ((listActiveViolationsRequest.getListSuppressedAlerts() == null) ^ (getListSuppressedAlerts() == null)) {
            return false;
        }
        if (listActiveViolationsRequest.getListSuppressedAlerts() != null && !listActiveViolationsRequest.getListSuppressedAlerts().equals(getListSuppressedAlerts())) {
            return false;
        }
        if ((listActiveViolationsRequest.getVerificationState() == null) ^ (getVerificationState() == null)) {
            return false;
        }
        if (listActiveViolationsRequest.getVerificationState() != null && !listActiveViolationsRequest.getVerificationState().equals(getVerificationState())) {
            return false;
        }
        if ((listActiveViolationsRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (listActiveViolationsRequest.getNextToken() != null && !listActiveViolationsRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((listActiveViolationsRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        return listActiveViolationsRequest.getMaxResults() == null || listActiveViolationsRequest.getMaxResults().equals(getMaxResults());
    }
}
