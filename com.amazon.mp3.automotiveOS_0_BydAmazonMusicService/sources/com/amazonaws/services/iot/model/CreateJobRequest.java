package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class CreateJobRequest extends AmazonWebServiceRequest implements Serializable {
    private AbortConfig abortConfig;
    private String description;
    private String document;
    private Map<String, String> documentParameters;
    private String documentSource;
    private JobExecutionsRetryConfig jobExecutionsRetryConfig;
    private JobExecutionsRolloutConfig jobExecutionsRolloutConfig;
    private String jobId;
    private String jobTemplateArn;
    private String namespaceId;
    private PresignedUrlConfig presignedUrlConfig;
    private List<Tag> tags;
    private String targetSelection;
    private List<String> targets;
    private TimeoutConfig timeoutConfig;

    public String getJobId() {
        return this.jobId;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public CreateJobRequest withJobId(String str) {
        this.jobId = str;
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

    public CreateJobRequest withTargets(String... strArr) {
        if (getTargets() == null) {
            this.targets = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.targets.add(str);
        }
        return this;
    }

    public CreateJobRequest withTargets(Collection<String> collection) {
        setTargets(collection);
        return this;
    }

    public String getDocumentSource() {
        return this.documentSource;
    }

    public void setDocumentSource(String str) {
        this.documentSource = str;
    }

    public CreateJobRequest withDocumentSource(String str) {
        this.documentSource = str;
        return this;
    }

    public String getDocument() {
        return this.document;
    }

    public void setDocument(String str) {
        this.document = str;
    }

    public CreateJobRequest withDocument(String str) {
        this.document = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public CreateJobRequest withDescription(String str) {
        this.description = str;
        return this;
    }

    public PresignedUrlConfig getPresignedUrlConfig() {
        return this.presignedUrlConfig;
    }

    public void setPresignedUrlConfig(PresignedUrlConfig presignedUrlConfig) {
        this.presignedUrlConfig = presignedUrlConfig;
    }

    public CreateJobRequest withPresignedUrlConfig(PresignedUrlConfig presignedUrlConfig) {
        this.presignedUrlConfig = presignedUrlConfig;
        return this;
    }

    public String getTargetSelection() {
        return this.targetSelection;
    }

    public void setTargetSelection(String str) {
        this.targetSelection = str;
    }

    public CreateJobRequest withTargetSelection(String str) {
        this.targetSelection = str;
        return this;
    }

    public void setTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection.toString();
    }

    public CreateJobRequest withTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection.toString();
        return this;
    }

    public JobExecutionsRolloutConfig getJobExecutionsRolloutConfig() {
        return this.jobExecutionsRolloutConfig;
    }

    public void setJobExecutionsRolloutConfig(JobExecutionsRolloutConfig jobExecutionsRolloutConfig) {
        this.jobExecutionsRolloutConfig = jobExecutionsRolloutConfig;
    }

    public CreateJobRequest withJobExecutionsRolloutConfig(JobExecutionsRolloutConfig jobExecutionsRolloutConfig) {
        this.jobExecutionsRolloutConfig = jobExecutionsRolloutConfig;
        return this;
    }

    public AbortConfig getAbortConfig() {
        return this.abortConfig;
    }

    public void setAbortConfig(AbortConfig abortConfig) {
        this.abortConfig = abortConfig;
    }

    public CreateJobRequest withAbortConfig(AbortConfig abortConfig) {
        this.abortConfig = abortConfig;
        return this;
    }

    public TimeoutConfig getTimeoutConfig() {
        return this.timeoutConfig;
    }

    public void setTimeoutConfig(TimeoutConfig timeoutConfig) {
        this.timeoutConfig = timeoutConfig;
    }

    public CreateJobRequest withTimeoutConfig(TimeoutConfig timeoutConfig) {
        this.timeoutConfig = timeoutConfig;
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

    public CreateJobRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateJobRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }

    public String getNamespaceId() {
        return this.namespaceId;
    }

    public void setNamespaceId(String str) {
        this.namespaceId = str;
    }

    public CreateJobRequest withNamespaceId(String str) {
        this.namespaceId = str;
        return this;
    }

    public String getJobTemplateArn() {
        return this.jobTemplateArn;
    }

    public void setJobTemplateArn(String str) {
        this.jobTemplateArn = str;
    }

    public CreateJobRequest withJobTemplateArn(String str) {
        this.jobTemplateArn = str;
        return this;
    }

    public JobExecutionsRetryConfig getJobExecutionsRetryConfig() {
        return this.jobExecutionsRetryConfig;
    }

    public void setJobExecutionsRetryConfig(JobExecutionsRetryConfig jobExecutionsRetryConfig) {
        this.jobExecutionsRetryConfig = jobExecutionsRetryConfig;
    }

    public CreateJobRequest withJobExecutionsRetryConfig(JobExecutionsRetryConfig jobExecutionsRetryConfig) {
        this.jobExecutionsRetryConfig = jobExecutionsRetryConfig;
        return this;
    }

    public Map<String, String> getDocumentParameters() {
        return this.documentParameters;
    }

    public void setDocumentParameters(Map<String, String> map) {
        this.documentParameters = map;
    }

    public CreateJobRequest withDocumentParameters(Map<String, String> map) {
        this.documentParameters = map;
        return this;
    }

    public CreateJobRequest adddocumentParametersEntry(String str, String str2) {
        if (this.documentParameters == null) {
            this.documentParameters = new HashMap();
        }
        if (this.documentParameters.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.documentParameters.put(str, str2);
        return this;
    }

    public CreateJobRequest cleardocumentParametersEntries() {
        this.documentParameters = null;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getJobId() != null) {
            sb.append("jobId: " + getJobId() + ",");
        }
        if (getTargets() != null) {
            sb.append("targets: " + getTargets() + ",");
        }
        if (getDocumentSource() != null) {
            sb.append("documentSource: " + getDocumentSource() + ",");
        }
        if (getDocument() != null) {
            sb.append("document: " + getDocument() + ",");
        }
        if (getDescription() != null) {
            sb.append("description: " + getDescription() + ",");
        }
        if (getPresignedUrlConfig() != null) {
            sb.append("presignedUrlConfig: " + getPresignedUrlConfig() + ",");
        }
        if (getTargetSelection() != null) {
            sb.append("targetSelection: " + getTargetSelection() + ",");
        }
        if (getJobExecutionsRolloutConfig() != null) {
            sb.append("jobExecutionsRolloutConfig: " + getJobExecutionsRolloutConfig() + ",");
        }
        if (getAbortConfig() != null) {
            sb.append("abortConfig: " + getAbortConfig() + ",");
        }
        if (getTimeoutConfig() != null) {
            sb.append("timeoutConfig: " + getTimeoutConfig() + ",");
        }
        if (getTags() != null) {
            sb.append("tags: " + getTags() + ",");
        }
        if (getNamespaceId() != null) {
            sb.append("namespaceId: " + getNamespaceId() + ",");
        }
        if (getJobTemplateArn() != null) {
            sb.append("jobTemplateArn: " + getJobTemplateArn() + ",");
        }
        if (getJobExecutionsRetryConfig() != null) {
            sb.append("jobExecutionsRetryConfig: " + getJobExecutionsRetryConfig() + ",");
        }
        if (getDocumentParameters() != null) {
            sb.append("documentParameters: " + getDocumentParameters());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((getJobId() == null ? 0 : getJobId().hashCode()) + 31) * 31) + (getTargets() == null ? 0 : getTargets().hashCode())) * 31) + (getDocumentSource() == null ? 0 : getDocumentSource().hashCode())) * 31) + (getDocument() == null ? 0 : getDocument().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getPresignedUrlConfig() == null ? 0 : getPresignedUrlConfig().hashCode())) * 31) + (getTargetSelection() == null ? 0 : getTargetSelection().hashCode())) * 31) + (getJobExecutionsRolloutConfig() == null ? 0 : getJobExecutionsRolloutConfig().hashCode())) * 31) + (getAbortConfig() == null ? 0 : getAbortConfig().hashCode())) * 31) + (getTimeoutConfig() == null ? 0 : getTimeoutConfig().hashCode())) * 31) + (getTags() == null ? 0 : getTags().hashCode())) * 31) + (getNamespaceId() == null ? 0 : getNamespaceId().hashCode())) * 31) + (getJobTemplateArn() == null ? 0 : getJobTemplateArn().hashCode())) * 31) + (getJobExecutionsRetryConfig() == null ? 0 : getJobExecutionsRetryConfig().hashCode())) * 31) + (getDocumentParameters() != null ? getDocumentParameters().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateJobRequest)) {
            return false;
        }
        CreateJobRequest createJobRequest = (CreateJobRequest) obj;
        if ((createJobRequest.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (createJobRequest.getJobId() != null && !createJobRequest.getJobId().equals(getJobId())) {
            return false;
        }
        if ((createJobRequest.getTargets() == null) ^ (getTargets() == null)) {
            return false;
        }
        if (createJobRequest.getTargets() != null && !createJobRequest.getTargets().equals(getTargets())) {
            return false;
        }
        if ((createJobRequest.getDocumentSource() == null) ^ (getDocumentSource() == null)) {
            return false;
        }
        if (createJobRequest.getDocumentSource() != null && !createJobRequest.getDocumentSource().equals(getDocumentSource())) {
            return false;
        }
        if ((createJobRequest.getDocument() == null) ^ (getDocument() == null)) {
            return false;
        }
        if (createJobRequest.getDocument() != null && !createJobRequest.getDocument().equals(getDocument())) {
            return false;
        }
        if ((createJobRequest.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (createJobRequest.getDescription() != null && !createJobRequest.getDescription().equals(getDescription())) {
            return false;
        }
        if ((createJobRequest.getPresignedUrlConfig() == null) ^ (getPresignedUrlConfig() == null)) {
            return false;
        }
        if (createJobRequest.getPresignedUrlConfig() != null && !createJobRequest.getPresignedUrlConfig().equals(getPresignedUrlConfig())) {
            return false;
        }
        if ((createJobRequest.getTargetSelection() == null) ^ (getTargetSelection() == null)) {
            return false;
        }
        if (createJobRequest.getTargetSelection() != null && !createJobRequest.getTargetSelection().equals(getTargetSelection())) {
            return false;
        }
        if ((createJobRequest.getJobExecutionsRolloutConfig() == null) ^ (getJobExecutionsRolloutConfig() == null)) {
            return false;
        }
        if (createJobRequest.getJobExecutionsRolloutConfig() != null && !createJobRequest.getJobExecutionsRolloutConfig().equals(getJobExecutionsRolloutConfig())) {
            return false;
        }
        if ((createJobRequest.getAbortConfig() == null) ^ (getAbortConfig() == null)) {
            return false;
        }
        if (createJobRequest.getAbortConfig() != null && !createJobRequest.getAbortConfig().equals(getAbortConfig())) {
            return false;
        }
        if ((createJobRequest.getTimeoutConfig() == null) ^ (getTimeoutConfig() == null)) {
            return false;
        }
        if (createJobRequest.getTimeoutConfig() != null && !createJobRequest.getTimeoutConfig().equals(getTimeoutConfig())) {
            return false;
        }
        if ((createJobRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        if (createJobRequest.getTags() != null && !createJobRequest.getTags().equals(getTags())) {
            return false;
        }
        if ((createJobRequest.getNamespaceId() == null) ^ (getNamespaceId() == null)) {
            return false;
        }
        if (createJobRequest.getNamespaceId() != null && !createJobRequest.getNamespaceId().equals(getNamespaceId())) {
            return false;
        }
        if ((createJobRequest.getJobTemplateArn() == null) ^ (getJobTemplateArn() == null)) {
            return false;
        }
        if (createJobRequest.getJobTemplateArn() != null && !createJobRequest.getJobTemplateArn().equals(getJobTemplateArn())) {
            return false;
        }
        if ((createJobRequest.getJobExecutionsRetryConfig() == null) ^ (getJobExecutionsRetryConfig() == null)) {
            return false;
        }
        if (createJobRequest.getJobExecutionsRetryConfig() != null && !createJobRequest.getJobExecutionsRetryConfig().equals(getJobExecutionsRetryConfig())) {
            return false;
        }
        if ((createJobRequest.getDocumentParameters() == null) ^ (getDocumentParameters() == null)) {
            return false;
        }
        return createJobRequest.getDocumentParameters() == null || createJobRequest.getDocumentParameters().equals(getDocumentParameters());
    }
}
