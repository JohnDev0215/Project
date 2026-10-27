package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class CodeSigning implements Serializable {
    private String awsSignerJobId;
    private CustomCodeSigning customCodeSigning;
    private StartSigningJobParameter startSigningJobParameter;

    public String getAwsSignerJobId() {
        return this.awsSignerJobId;
    }

    public void setAwsSignerJobId(String str) {
        this.awsSignerJobId = str;
    }

    public CodeSigning withAwsSignerJobId(String str) {
        this.awsSignerJobId = str;
        return this;
    }

    public StartSigningJobParameter getStartSigningJobParameter() {
        return this.startSigningJobParameter;
    }

    public void setStartSigningJobParameter(StartSigningJobParameter startSigningJobParameter) {
        this.startSigningJobParameter = startSigningJobParameter;
    }

    public CodeSigning withStartSigningJobParameter(StartSigningJobParameter startSigningJobParameter) {
        this.startSigningJobParameter = startSigningJobParameter;
        return this;
    }

    public CustomCodeSigning getCustomCodeSigning() {
        return this.customCodeSigning;
    }

    public void setCustomCodeSigning(CustomCodeSigning customCodeSigning) {
        this.customCodeSigning = customCodeSigning;
    }

    public CodeSigning withCustomCodeSigning(CustomCodeSigning customCodeSigning) {
        this.customCodeSigning = customCodeSigning;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAwsSignerJobId() != null) {
            sb.append("awsSignerJobId: " + getAwsSignerJobId() + ",");
        }
        if (getStartSigningJobParameter() != null) {
            sb.append("startSigningJobParameter: " + getStartSigningJobParameter() + ",");
        }
        if (getCustomCodeSigning() != null) {
            sb.append("customCodeSigning: " + getCustomCodeSigning());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getAwsSignerJobId() == null ? 0 : getAwsSignerJobId().hashCode()) + 31) * 31) + (getStartSigningJobParameter() == null ? 0 : getStartSigningJobParameter().hashCode())) * 31) + (getCustomCodeSigning() != null ? getCustomCodeSigning().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CodeSigning)) {
            return false;
        }
        CodeSigning codeSigning = (CodeSigning) obj;
        if ((codeSigning.getAwsSignerJobId() == null) ^ (getAwsSignerJobId() == null)) {
            return false;
        }
        if (codeSigning.getAwsSignerJobId() != null && !codeSigning.getAwsSignerJobId().equals(getAwsSignerJobId())) {
            return false;
        }
        if ((codeSigning.getStartSigningJobParameter() == null) ^ (getStartSigningJobParameter() == null)) {
            return false;
        }
        if (codeSigning.getStartSigningJobParameter() != null && !codeSigning.getStartSigningJobParameter().equals(getStartSigningJobParameter())) {
            return false;
        }
        if ((codeSigning.getCustomCodeSigning() == null) ^ (getCustomCodeSigning() == null)) {
            return false;
        }
        return codeSigning.getCustomCodeSigning() == null || codeSigning.getCustomCodeSigning().equals(getCustomCodeSigning());
    }
}
