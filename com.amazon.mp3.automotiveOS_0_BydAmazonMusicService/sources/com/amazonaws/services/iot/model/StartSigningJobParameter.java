package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class StartSigningJobParameter implements Serializable {
    private Destination destination;
    private String signingProfileName;
    private SigningProfileParameter signingProfileParameter;

    public SigningProfileParameter getSigningProfileParameter() {
        return this.signingProfileParameter;
    }

    public void setSigningProfileParameter(SigningProfileParameter signingProfileParameter) {
        this.signingProfileParameter = signingProfileParameter;
    }

    public StartSigningJobParameter withSigningProfileParameter(SigningProfileParameter signingProfileParameter) {
        this.signingProfileParameter = signingProfileParameter;
        return this;
    }

    public String getSigningProfileName() {
        return this.signingProfileName;
    }

    public void setSigningProfileName(String str) {
        this.signingProfileName = str;
    }

    public StartSigningJobParameter withSigningProfileName(String str) {
        this.signingProfileName = str;
        return this;
    }

    public Destination getDestination() {
        return this.destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public StartSigningJobParameter withDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getSigningProfileParameter() != null) {
            sb.append("signingProfileParameter: " + getSigningProfileParameter() + ",");
        }
        if (getSigningProfileName() != null) {
            sb.append("signingProfileName: " + getSigningProfileName() + ",");
        }
        if (getDestination() != null) {
            sb.append("destination: " + getDestination());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getSigningProfileParameter() == null ? 0 : getSigningProfileParameter().hashCode()) + 31) * 31) + (getSigningProfileName() == null ? 0 : getSigningProfileName().hashCode())) * 31) + (getDestination() != null ? getDestination().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StartSigningJobParameter)) {
            return false;
        }
        StartSigningJobParameter startSigningJobParameter = (StartSigningJobParameter) obj;
        if ((startSigningJobParameter.getSigningProfileParameter() == null) ^ (getSigningProfileParameter() == null)) {
            return false;
        }
        if (startSigningJobParameter.getSigningProfileParameter() != null && !startSigningJobParameter.getSigningProfileParameter().equals(getSigningProfileParameter())) {
            return false;
        }
        if ((startSigningJobParameter.getSigningProfileName() == null) ^ (getSigningProfileName() == null)) {
            return false;
        }
        if (startSigningJobParameter.getSigningProfileName() != null && !startSigningJobParameter.getSigningProfileName().equals(getSigningProfileName())) {
            return false;
        }
        if ((startSigningJobParameter.getDestination() == null) ^ (getDestination() == null)) {
            return false;
        }
        return startSigningJobParameter.getDestination() == null || startSigningJobParameter.getDestination().equals(getDestination());
    }
}
