package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class MitigationActionParams implements Serializable {
    private AddThingsToThingGroupParams addThingsToThingGroupParams;
    private EnableIoTLoggingParams enableIoTLoggingParams;
    private PublishFindingToSnsParams publishFindingToSnsParams;
    private ReplaceDefaultPolicyVersionParams replaceDefaultPolicyVersionParams;
    private UpdateCACertificateParams updateCACertificateParams;
    private UpdateDeviceCertificateParams updateDeviceCertificateParams;

    public UpdateDeviceCertificateParams getUpdateDeviceCertificateParams() {
        return this.updateDeviceCertificateParams;
    }

    public void setUpdateDeviceCertificateParams(UpdateDeviceCertificateParams updateDeviceCertificateParams) {
        this.updateDeviceCertificateParams = updateDeviceCertificateParams;
    }

    public MitigationActionParams withUpdateDeviceCertificateParams(UpdateDeviceCertificateParams updateDeviceCertificateParams) {
        this.updateDeviceCertificateParams = updateDeviceCertificateParams;
        return this;
    }

    public UpdateCACertificateParams getUpdateCACertificateParams() {
        return this.updateCACertificateParams;
    }

    public void setUpdateCACertificateParams(UpdateCACertificateParams updateCACertificateParams) {
        this.updateCACertificateParams = updateCACertificateParams;
    }

    public MitigationActionParams withUpdateCACertificateParams(UpdateCACertificateParams updateCACertificateParams) {
        this.updateCACertificateParams = updateCACertificateParams;
        return this;
    }

    public AddThingsToThingGroupParams getAddThingsToThingGroupParams() {
        return this.addThingsToThingGroupParams;
    }

    public void setAddThingsToThingGroupParams(AddThingsToThingGroupParams addThingsToThingGroupParams) {
        this.addThingsToThingGroupParams = addThingsToThingGroupParams;
    }

    public MitigationActionParams withAddThingsToThingGroupParams(AddThingsToThingGroupParams addThingsToThingGroupParams) {
        this.addThingsToThingGroupParams = addThingsToThingGroupParams;
        return this;
    }

    public ReplaceDefaultPolicyVersionParams getReplaceDefaultPolicyVersionParams() {
        return this.replaceDefaultPolicyVersionParams;
    }

    public void setReplaceDefaultPolicyVersionParams(ReplaceDefaultPolicyVersionParams replaceDefaultPolicyVersionParams) {
        this.replaceDefaultPolicyVersionParams = replaceDefaultPolicyVersionParams;
    }

    public MitigationActionParams withReplaceDefaultPolicyVersionParams(ReplaceDefaultPolicyVersionParams replaceDefaultPolicyVersionParams) {
        this.replaceDefaultPolicyVersionParams = replaceDefaultPolicyVersionParams;
        return this;
    }

    public EnableIoTLoggingParams getEnableIoTLoggingParams() {
        return this.enableIoTLoggingParams;
    }

    public void setEnableIoTLoggingParams(EnableIoTLoggingParams enableIoTLoggingParams) {
        this.enableIoTLoggingParams = enableIoTLoggingParams;
    }

    public MitigationActionParams withEnableIoTLoggingParams(EnableIoTLoggingParams enableIoTLoggingParams) {
        this.enableIoTLoggingParams = enableIoTLoggingParams;
        return this;
    }

    public PublishFindingToSnsParams getPublishFindingToSnsParams() {
        return this.publishFindingToSnsParams;
    }

    public void setPublishFindingToSnsParams(PublishFindingToSnsParams publishFindingToSnsParams) {
        this.publishFindingToSnsParams = publishFindingToSnsParams;
    }

    public MitigationActionParams withPublishFindingToSnsParams(PublishFindingToSnsParams publishFindingToSnsParams) {
        this.publishFindingToSnsParams = publishFindingToSnsParams;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getUpdateDeviceCertificateParams() != null) {
            sb.append("updateDeviceCertificateParams: " + getUpdateDeviceCertificateParams() + ",");
        }
        if (getUpdateCACertificateParams() != null) {
            sb.append("updateCACertificateParams: " + getUpdateCACertificateParams() + ",");
        }
        if (getAddThingsToThingGroupParams() != null) {
            sb.append("addThingsToThingGroupParams: " + getAddThingsToThingGroupParams() + ",");
        }
        if (getReplaceDefaultPolicyVersionParams() != null) {
            sb.append("replaceDefaultPolicyVersionParams: " + getReplaceDefaultPolicyVersionParams() + ",");
        }
        if (getEnableIoTLoggingParams() != null) {
            sb.append("enableIoTLoggingParams: " + getEnableIoTLoggingParams() + ",");
        }
        if (getPublishFindingToSnsParams() != null) {
            sb.append("publishFindingToSnsParams: " + getPublishFindingToSnsParams());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getUpdateDeviceCertificateParams() == null ? 0 : getUpdateDeviceCertificateParams().hashCode()) + 31) * 31) + (getUpdateCACertificateParams() == null ? 0 : getUpdateCACertificateParams().hashCode())) * 31) + (getAddThingsToThingGroupParams() == null ? 0 : getAddThingsToThingGroupParams().hashCode())) * 31) + (getReplaceDefaultPolicyVersionParams() == null ? 0 : getReplaceDefaultPolicyVersionParams().hashCode())) * 31) + (getEnableIoTLoggingParams() == null ? 0 : getEnableIoTLoggingParams().hashCode())) * 31) + (getPublishFindingToSnsParams() != null ? getPublishFindingToSnsParams().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MitigationActionParams)) {
            return false;
        }
        MitigationActionParams mitigationActionParams = (MitigationActionParams) obj;
        if ((mitigationActionParams.getUpdateDeviceCertificateParams() == null) ^ (getUpdateDeviceCertificateParams() == null)) {
            return false;
        }
        if (mitigationActionParams.getUpdateDeviceCertificateParams() != null && !mitigationActionParams.getUpdateDeviceCertificateParams().equals(getUpdateDeviceCertificateParams())) {
            return false;
        }
        if ((mitigationActionParams.getUpdateCACertificateParams() == null) ^ (getUpdateCACertificateParams() == null)) {
            return false;
        }
        if (mitigationActionParams.getUpdateCACertificateParams() != null && !mitigationActionParams.getUpdateCACertificateParams().equals(getUpdateCACertificateParams())) {
            return false;
        }
        if ((mitigationActionParams.getAddThingsToThingGroupParams() == null) ^ (getAddThingsToThingGroupParams() == null)) {
            return false;
        }
        if (mitigationActionParams.getAddThingsToThingGroupParams() != null && !mitigationActionParams.getAddThingsToThingGroupParams().equals(getAddThingsToThingGroupParams())) {
            return false;
        }
        if ((mitigationActionParams.getReplaceDefaultPolicyVersionParams() == null) ^ (getReplaceDefaultPolicyVersionParams() == null)) {
            return false;
        }
        if (mitigationActionParams.getReplaceDefaultPolicyVersionParams() != null && !mitigationActionParams.getReplaceDefaultPolicyVersionParams().equals(getReplaceDefaultPolicyVersionParams())) {
            return false;
        }
        if ((mitigationActionParams.getEnableIoTLoggingParams() == null) ^ (getEnableIoTLoggingParams() == null)) {
            return false;
        }
        if (mitigationActionParams.getEnableIoTLoggingParams() != null && !mitigationActionParams.getEnableIoTLoggingParams().equals(getEnableIoTLoggingParams())) {
            return false;
        }
        if ((mitigationActionParams.getPublishFindingToSnsParams() == null) ^ (getPublishFindingToSnsParams() == null)) {
            return false;
        }
        return mitigationActionParams.getPublishFindingToSnsParams() == null || mitigationActionParams.getPublishFindingToSnsParams().equals(getPublishFindingToSnsParams());
    }
}
