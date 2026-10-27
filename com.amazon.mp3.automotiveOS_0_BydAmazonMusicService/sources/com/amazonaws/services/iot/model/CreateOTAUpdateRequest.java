package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class CreateOTAUpdateRequest extends AmazonWebServiceRequest implements Serializable {
    private Map<String, String> additionalParameters;
    private AwsJobAbortConfig awsJobAbortConfig;
    private AwsJobExecutionsRolloutConfig awsJobExecutionsRolloutConfig;
    private AwsJobPresignedUrlConfig awsJobPresignedUrlConfig;
    private AwsJobTimeoutConfig awsJobTimeoutConfig;
    private String description;
    private List<OTAUpdateFile> files;
    private String otaUpdateId;
    private List<String> protocols;
    private String roleArn;
    private List<Tag> tags;
    private String targetSelection;
    private List<String> targets;

    public String getOtaUpdateId() {
        return this.otaUpdateId;
    }

    public void setOtaUpdateId(String str) {
        this.otaUpdateId = str;
    }

    public CreateOTAUpdateRequest withOtaUpdateId(String str) {
        this.otaUpdateId = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public CreateOTAUpdateRequest withDescription(String str) {
        this.description = str;
        return this;
    }

    public List<String> getTargets() {
        return this.targets;
    }

    public void setTargets(Collection<String> collection) {
        if (collection == null) {
            this.targets = null;
        } else {
            this.targets = new ArrayList(collection);
        }
    }

    public CreateOTAUpdateRequest withTargets(String... strArr) {
        if (getTargets() == null) {
            this.targets = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.targets.add(str);
        }
        return this;
    }

    public CreateOTAUpdateRequest withTargets(Collection<String> collection) {
        setTargets(collection);
        return this;
    }

    public List<String> getProtocols() {
        return this.protocols;
    }

    public void setProtocols(Collection<String> collection) {
        if (collection == null) {
            this.protocols = null;
        } else {
            this.protocols = new ArrayList(collection);
        }
    }

    public CreateOTAUpdateRequest withProtocols(String... strArr) {
        if (getProtocols() == null) {
            this.protocols = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.protocols.add(str);
        }
        return this;
    }

    public CreateOTAUpdateRequest withProtocols(Collection<String> collection) {
        setProtocols(collection);
        return this;
    }

    public String getTargetSelection() {
        return this.targetSelection;
    }

    public void setTargetSelection(String str) {
        this.targetSelection = str;
    }

    public CreateOTAUpdateRequest withTargetSelection(String str) {
        this.targetSelection = str;
        return this;
    }

    public void setTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection.toString();
    }

    public CreateOTAUpdateRequest withTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection.toString();
        return this;
    }

    public AwsJobExecutionsRolloutConfig getAwsJobExecutionsRolloutConfig() {
        return this.awsJobExecutionsRolloutConfig;
    }

    public void setAwsJobExecutionsRolloutConfig(AwsJobExecutionsRolloutConfig awsJobExecutionsRolloutConfig) {
        this.awsJobExecutionsRolloutConfig = awsJobExecutionsRolloutConfig;
    }

    public CreateOTAUpdateRequest withAwsJobExecutionsRolloutConfig(AwsJobExecutionsRolloutConfig awsJobExecutionsRolloutConfig) {
        this.awsJobExecutionsRolloutConfig = awsJobExecutionsRolloutConfig;
        return this;
    }

    public AwsJobPresignedUrlConfig getAwsJobPresignedUrlConfig() {
        return this.awsJobPresignedUrlConfig;
    }

    public void setAwsJobPresignedUrlConfig(AwsJobPresignedUrlConfig awsJobPresignedUrlConfig) {
        this.awsJobPresignedUrlConfig = awsJobPresignedUrlConfig;
    }

    public CreateOTAUpdateRequest withAwsJobPresignedUrlConfig(AwsJobPresignedUrlConfig awsJobPresignedUrlConfig) {
        this.awsJobPresignedUrlConfig = awsJobPresignedUrlConfig;
        return this;
    }

    public AwsJobAbortConfig getAwsJobAbortConfig() {
        return this.awsJobAbortConfig;
    }

    public void setAwsJobAbortConfig(AwsJobAbortConfig awsJobAbortConfig) {
        this.awsJobAbortConfig = awsJobAbortConfig;
    }

    public CreateOTAUpdateRequest withAwsJobAbortConfig(AwsJobAbortConfig awsJobAbortConfig) {
        this.awsJobAbortConfig = awsJobAbortConfig;
        return this;
    }

    public AwsJobTimeoutConfig getAwsJobTimeoutConfig() {
        return this.awsJobTimeoutConfig;
    }

    public void setAwsJobTimeoutConfig(AwsJobTimeoutConfig awsJobTimeoutConfig) {
        this.awsJobTimeoutConfig = awsJobTimeoutConfig;
    }

    public CreateOTAUpdateRequest withAwsJobTimeoutConfig(AwsJobTimeoutConfig awsJobTimeoutConfig) {
        this.awsJobTimeoutConfig = awsJobTimeoutConfig;
        return this;
    }

    public List<OTAUpdateFile> getFiles() {
        return this.files;
    }

    public void setFiles(Collection<OTAUpdateFile> collection) {
        if (collection == null) {
            this.files = null;
        } else {
            this.files = new ArrayList(collection);
        }
    }

    public CreateOTAUpdateRequest withFiles(OTAUpdateFile... oTAUpdateFileArr) {
        if (getFiles() == null) {
            this.files = new ArrayList(oTAUpdateFileArr.length);
        }
        for (OTAUpdateFile oTAUpdateFile : oTAUpdateFileArr) {
            this.files.add(oTAUpdateFile);
        }
        return this;
    }

    public CreateOTAUpdateRequest withFiles(Collection<OTAUpdateFile> collection) {
        setFiles(collection);
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public CreateOTAUpdateRequest withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public Map<String, String> getAdditionalParameters() {
        return this.additionalParameters;
    }

    public void setAdditionalParameters(Map<String, String> map) {
        this.additionalParameters = map;
    }

    public CreateOTAUpdateRequest withAdditionalParameters(Map<String, String> map) {
        this.additionalParameters = map;
        return this;
    }

    public CreateOTAUpdateRequest addadditionalParametersEntry(String str, String str2) {
        if (this.additionalParameters == null) {
            this.additionalParameters = new HashMap();
        }
        if (this.additionalParameters.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.additionalParameters.put(str, str2);
        return this;
    }

    public CreateOTAUpdateRequest clearadditionalParametersEntries() {
        this.additionalParameters = null;
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

    public CreateOTAUpdateRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateOTAUpdateRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getOtaUpdateId() != null) {
            sb.append("otaUpdateId: " + getOtaUpdateId() + ",");
        }
        if (getDescription() != null) {
            sb.append("description: " + getDescription() + ",");
        }
        if (getTargets() != null) {
            sb.append("targets: " + getTargets() + ",");
        }
        if (getProtocols() != null) {
            sb.append("protocols: " + getProtocols() + ",");
        }
        if (getTargetSelection() != null) {
            sb.append("targetSelection: " + getTargetSelection() + ",");
        }
        if (getAwsJobExecutionsRolloutConfig() != null) {
            sb.append("awsJobExecutionsRolloutConfig: " + getAwsJobExecutionsRolloutConfig() + ",");
        }
        if (getAwsJobPresignedUrlConfig() != null) {
            sb.append("awsJobPresignedUrlConfig: " + getAwsJobPresignedUrlConfig() + ",");
        }
        if (getAwsJobAbortConfig() != null) {
            sb.append("awsJobAbortConfig: " + getAwsJobAbortConfig() + ",");
        }
        if (getAwsJobTimeoutConfig() != null) {
            sb.append("awsJobTimeoutConfig: " + getAwsJobTimeoutConfig() + ",");
        }
        if (getFiles() != null) {
            sb.append("files: " + getFiles() + ",");
        }
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getAdditionalParameters() != null) {
            sb.append("additionalParameters: " + getAdditionalParameters() + ",");
        }
        if (getTags() != null) {
            sb.append("tags: " + getTags());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((((((((getOtaUpdateId() == null ? 0 : getOtaUpdateId().hashCode()) + 31) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getTargets() == null ? 0 : getTargets().hashCode())) * 31) + (getProtocols() == null ? 0 : getProtocols().hashCode())) * 31) + (getTargetSelection() == null ? 0 : getTargetSelection().hashCode())) * 31) + (getAwsJobExecutionsRolloutConfig() == null ? 0 : getAwsJobExecutionsRolloutConfig().hashCode())) * 31) + (getAwsJobPresignedUrlConfig() == null ? 0 : getAwsJobPresignedUrlConfig().hashCode())) * 31) + (getAwsJobAbortConfig() == null ? 0 : getAwsJobAbortConfig().hashCode())) * 31) + (getAwsJobTimeoutConfig() == null ? 0 : getAwsJobTimeoutConfig().hashCode())) * 31) + (getFiles() == null ? 0 : getFiles().hashCode())) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getAdditionalParameters() == null ? 0 : getAdditionalParameters().hashCode())) * 31) + (getTags() != null ? getTags().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateOTAUpdateRequest)) {
            return false;
        }
        CreateOTAUpdateRequest createOTAUpdateRequest = (CreateOTAUpdateRequest) obj;
        if ((createOTAUpdateRequest.getOtaUpdateId() == null) ^ (getOtaUpdateId() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getOtaUpdateId() != null && !createOTAUpdateRequest.getOtaUpdateId().equals(getOtaUpdateId())) {
            return false;
        }
        if ((createOTAUpdateRequest.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getDescription() != null && !createOTAUpdateRequest.getDescription().equals(getDescription())) {
            return false;
        }
        if ((createOTAUpdateRequest.getTargets() == null) ^ (getTargets() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getTargets() != null && !createOTAUpdateRequest.getTargets().equals(getTargets())) {
            return false;
        }
        if ((createOTAUpdateRequest.getProtocols() == null) ^ (getProtocols() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getProtocols() != null && !createOTAUpdateRequest.getProtocols().equals(getProtocols())) {
            return false;
        }
        if ((createOTAUpdateRequest.getTargetSelection() == null) ^ (getTargetSelection() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getTargetSelection() != null && !createOTAUpdateRequest.getTargetSelection().equals(getTargetSelection())) {
            return false;
        }
        if ((createOTAUpdateRequest.getAwsJobExecutionsRolloutConfig() == null) ^ (getAwsJobExecutionsRolloutConfig() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getAwsJobExecutionsRolloutConfig() != null && !createOTAUpdateRequest.getAwsJobExecutionsRolloutConfig().equals(getAwsJobExecutionsRolloutConfig())) {
            return false;
        }
        if ((createOTAUpdateRequest.getAwsJobPresignedUrlConfig() == null) ^ (getAwsJobPresignedUrlConfig() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getAwsJobPresignedUrlConfig() != null && !createOTAUpdateRequest.getAwsJobPresignedUrlConfig().equals(getAwsJobPresignedUrlConfig())) {
            return false;
        }
        if ((createOTAUpdateRequest.getAwsJobAbortConfig() == null) ^ (getAwsJobAbortConfig() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getAwsJobAbortConfig() != null && !createOTAUpdateRequest.getAwsJobAbortConfig().equals(getAwsJobAbortConfig())) {
            return false;
        }
        if ((createOTAUpdateRequest.getAwsJobTimeoutConfig() == null) ^ (getAwsJobTimeoutConfig() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getAwsJobTimeoutConfig() != null && !createOTAUpdateRequest.getAwsJobTimeoutConfig().equals(getAwsJobTimeoutConfig())) {
            return false;
        }
        if ((createOTAUpdateRequest.getFiles() == null) ^ (getFiles() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getFiles() != null && !createOTAUpdateRequest.getFiles().equals(getFiles())) {
            return false;
        }
        if ((createOTAUpdateRequest.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getRoleArn() != null && !createOTAUpdateRequest.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((createOTAUpdateRequest.getAdditionalParameters() == null) ^ (getAdditionalParameters() == null)) {
            return false;
        }
        if (createOTAUpdateRequest.getAdditionalParameters() != null && !createOTAUpdateRequest.getAdditionalParameters().equals(getAdditionalParameters())) {
            return false;
        }
        if ((createOTAUpdateRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return createOTAUpdateRequest.getTags() == null || createOTAUpdateRequest.getTags().equals(getTags());
    }
}
