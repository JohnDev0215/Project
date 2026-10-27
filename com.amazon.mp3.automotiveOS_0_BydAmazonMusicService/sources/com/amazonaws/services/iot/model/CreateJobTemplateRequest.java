package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CreateJobTemplateRequest extends AmazonWebServiceRequest implements Serializable {
    private AbortConfig abortConfig;
    private String description;
    private String document;
    private String documentSource;
    private String jobArn;
    private JobExecutionsRetryConfig jobExecutionsRetryConfig;
    private JobExecutionsRolloutConfig jobExecutionsRolloutConfig;
    private String jobTemplateId;
    private PresignedUrlConfig presignedUrlConfig;
    private List<Tag> tags;
    private TimeoutConfig timeoutConfig;

    public String getJobTemplateId() {
        return this.jobTemplateId;
    }

    public void setJobTemplateId(String str) {
        this.jobTemplateId = str;
    }

    public CreateJobTemplateRequest withJobTemplateId(String str) {
        this.jobTemplateId = str;
        return this;
    }

    public String getJobArn() {
        return this.jobArn;
    }

    public void setJobArn(String str) {
        this.jobArn = str;
    }

    public CreateJobTemplateRequest withJobArn(String str) {
        this.jobArn = str;
        return this;
    }

    public String getDocumentSource() {
        return this.documentSource;
    }

    public void setDocumentSource(String str) {
        this.documentSource = str;
    }

    public CreateJobTemplateRequest withDocumentSource(String str) {
        this.documentSource = str;
        return this;
    }

    public String getDocument() {
        return this.document;
    }

    public void setDocument(String str) {
        this.document = str;
    }

    public CreateJobTemplateRequest withDocument(String str) {
        this.document = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public CreateJobTemplateRequest withDescription(String str) {
        this.description = str;
        return this;
    }

    public PresignedUrlConfig getPresignedUrlConfig() {
        return this.presignedUrlConfig;
    }

    public void setPresignedUrlConfig(PresignedUrlConfig presignedUrlConfig) {
        this.presignedUrlConfig = presignedUrlConfig;
    }

    public CreateJobTemplateRequest withPresignedUrlConfig(PresignedUrlConfig presignedUrlConfig) {
        this.presignedUrlConfig = presignedUrlConfig;
        return this;
    }

    public JobExecutionsRolloutConfig getJobExecutionsRolloutConfig() {
        return this.jobExecutionsRolloutConfig;
    }

    public void setJobExecutionsRolloutConfig(JobExecutionsRolloutConfig jobExecutionsRolloutConfig) {
        this.jobExecutionsRolloutConfig = jobExecutionsRolloutConfig;
    }

    public CreateJobTemplateRequest withJobExecutionsRolloutConfig(JobExecutionsRolloutConfig jobExecutionsRolloutConfig) {
        this.jobExecutionsRolloutConfig = jobExecutionsRolloutConfig;
        return this;
    }

    public AbortConfig getAbortConfig() {
        return this.abortConfig;
    }

    public void setAbortConfig(AbortConfig abortConfig) {
        this.abortConfig = abortConfig;
    }

    public CreateJobTemplateRequest withAbortConfig(AbortConfig abortConfig) {
        this.abortConfig = abortConfig;
        return this;
    }

    public TimeoutConfig getTimeoutConfig() {
        return this.timeoutConfig;
    }

    public void setTimeoutConfig(TimeoutConfig timeoutConfig) {
        this.timeoutConfig = timeoutConfig;
    }

    public CreateJobTemplateRequest withTimeoutConfig(TimeoutConfig timeoutConfig) {
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

    public CreateJobTemplateRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateJobTemplateRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }

    public JobExecutionsRetryConfig getJobExecutionsRetryConfig() {
        return this.jobExecutionsRetryConfig;
    }

    public void setJobExecutionsRetryConfig(JobExecutionsRetryConfig jobExecutionsRetryConfig) {
        this.jobExecutionsRetryConfig = jobExecutionsRetryConfig;
    }

    public CreateJobTemplateRequest withJobExecutionsRetryConfig(JobExecutionsRetryConfig jobExecutionsRetryConfig) {
        this.jobExecutionsRetryConfig = jobExecutionsRetryConfig;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getJobTemplateId() != null) {
            sb.append("jobTemplateId: " + getJobTemplateId() + ",");
        }
        if (getJobArn() != null) {
            sb.append("jobArn: " + getJobArn() + ",");
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
        if (getJobExecutionsRetryConfig() != null) {
            sb.append("jobExecutionsRetryConfig: " + getJobExecutionsRetryConfig());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((((getJobTemplateId() == null ? 0 : getJobTemplateId().hashCode()) + 31) * 31) + (getJobArn() == null ? 0 : getJobArn().hashCode())) * 31) + (getDocumentSource() == null ? 0 : getDocumentSource().hashCode())) * 31) + (getDocument() == null ? 0 : getDocument().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getPresignedUrlConfig() == null ? 0 : getPresignedUrlConfig().hashCode())) * 31) + (getJobExecutionsRolloutConfig() == null ? 0 : getJobExecutionsRolloutConfig().hashCode())) * 31) + (getAbortConfig() == null ? 0 : getAbortConfig().hashCode())) * 31) + (getTimeoutConfig() == null ? 0 : getTimeoutConfig().hashCode())) * 31) + (getTags() == null ? 0 : getTags().hashCode())) * 31) + (getJobExecutionsRetryConfig() != null ? getJobExecutionsRetryConfig().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateJobTemplateRequest)) {
            return false;
        }
        CreateJobTemplateRequest createJobTemplateRequest = (CreateJobTemplateRequest) obj;
        if ((createJobTemplateRequest.getJobTemplateId() == null) ^ (getJobTemplateId() == null)) {
            return false;
        }
        if (createJobTemplateRequest.getJobTemplateId() != null && !createJobTemplateRequest.getJobTemplateId().equals(getJobTemplateId())) {
            return false;
        }
        if ((createJobTemplateRequest.getJobArn() == null) ^ (getJobArn() == null)) {
            return false;
        }
        if (createJobTemplateRequest.getJobArn() != null && !createJobTemplateRequest.getJobArn().equals(getJobArn())) {
            return false;
        }
        if ((createJobTemplateRequest.getDocumentSource() == null) ^ (getDocumentSource() == null)) {
            return false;
        }
        if (createJobTemplateRequest.getDocumentSource() != null && !createJobTemplateRequest.getDocumentSource().equals(getDocumentSource())) {
            return false;
        }
        if ((createJobTemplateRequest.getDocument() == null) ^ (getDocument() == null)) {
            return false;
        }
        if (createJobTemplateRequest.getDocument() != null && !createJobTemplateRequest.getDocument().equals(getDocument())) {
            return false;
        }
        if ((createJobTemplateRequest.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (createJobTemplateRequest.getDescription() != null && !createJobTemplateRequest.getDescription().equals(getDescription())) {
            return false;
        }
        if ((createJobTemplateRequest.getPresignedUrlConfig() == null) ^ (getPresignedUrlConfig() == null)) {
            return false;
        }
        if (createJobTemplateRequest.getPresignedUrlConfig() != null && !createJobTemplateRequest.getPresignedUrlConfig().equals(getPresignedUrlConfig())) {
            return false;
        }
        if ((createJobTemplateRequest.getJobExecutionsRolloutConfig() == null) ^ (getJobExecutionsRolloutConfig() == null)) {
            return false;
        }
        if (createJobTemplateRequest.getJobExecutionsRolloutConfig() != null && !createJobTemplateRequest.getJobExecutionsRolloutConfig().equals(getJobExecutionsRolloutConfig())) {
            return false;
        }
        if ((createJobTemplateRequest.getAbortConfig() == null) ^ (getAbortConfig() == null)) {
            return false;
        }
        if (createJobTemplateRequest.getAbortConfig() != null && !createJobTemplateRequest.getAbortConfig().equals(getAbortConfig())) {
            return false;
        }
        if ((createJobTemplateRequest.getTimeoutConfig() == null) ^ (getTimeoutConfig() == null)) {
            return false;
        }
        if (createJobTemplateRequest.getTimeoutConfig() != null && !createJobTemplateRequest.getTimeoutConfig().equals(getTimeoutConfig())) {
            return false;
        }
        if ((createJobTemplateRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        if (createJobTemplateRequest.getTags() != null && !createJobTemplateRequest.getTags().equals(getTags())) {
            return false;
        }
        if ((createJobTemplateRequest.getJobExecutionsRetryConfig() == null) ^ (getJobExecutionsRetryConfig() == null)) {
            return false;
        }
        return createJobTemplateRequest.getJobExecutionsRetryConfig() == null || createJobTemplateRequest.getJobExecutionsRetryConfig().equals(getJobExecutionsRetryConfig());
    }
}
