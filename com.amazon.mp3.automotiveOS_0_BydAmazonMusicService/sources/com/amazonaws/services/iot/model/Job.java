package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class Job implements Serializable {
    private AbortConfig abortConfig;
    private String comment;
    private Date completedAt;
    private Date createdAt;
    private String description;
    private Map<String, String> documentParameters;
    private Boolean forceCanceled;
    private Boolean isConcurrent;
    private String jobArn;
    private JobExecutionsRetryConfig jobExecutionsRetryConfig;
    private JobExecutionsRolloutConfig jobExecutionsRolloutConfig;
    private String jobId;
    private JobProcessDetails jobProcessDetails;
    private String jobTemplateArn;
    private Date lastUpdatedAt;
    private String namespaceId;
    private PresignedUrlConfig presignedUrlConfig;
    private String reasonCode;
    private String status;
    private String targetSelection;
    private List<String> targets;
    private TimeoutConfig timeoutConfig;

    public String getJobArn() {
        return this.jobArn;
    }

    public void setJobArn(String str) {
        this.jobArn = str;
    }

    public Job withJobArn(String str) {
        this.jobArn = str;
        return this;
    }

    public String getJobId() {
        return this.jobId;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public Job withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public String getTargetSelection() {
        return this.targetSelection;
    }

    public void setTargetSelection(String str) {
        this.targetSelection = str;
    }

    public Job withTargetSelection(String str) {
        this.targetSelection = str;
        return this;
    }

    public void setTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection.toString();
    }

    public Job withTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection.toString();
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public Job withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(JobStatus jobStatus) {
        this.status = jobStatus.toString();
    }

    public Job withStatus(JobStatus jobStatus) {
        this.status = jobStatus.toString();
        return this;
    }

    public Boolean isForceCanceled() {
        return this.forceCanceled;
    }

    public Boolean getForceCanceled() {
        return this.forceCanceled;
    }

    public void setForceCanceled(Boolean bool) {
        this.forceCanceled = bool;
    }

    public Job withForceCanceled(Boolean bool) {
        this.forceCanceled = bool;
        return this;
    }

    public String getReasonCode() {
        return this.reasonCode;
    }

    public void setReasonCode(String str) {
        this.reasonCode = str;
    }

    public Job withReasonCode(String str) {
        this.reasonCode = str;
        return this;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public Job withComment(String str) {
        this.comment = str;
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

    public Job withTargets(String... strArr) {
        if (getTargets() == null) {
            this.targets = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.targets.add(str);
        }
        return this;
    }

    public Job withTargets(Collection<String> collection) {
        setTargets(collection);
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public Job withDescription(String str) {
        this.description = str;
        return this;
    }

    public PresignedUrlConfig getPresignedUrlConfig() {
        return this.presignedUrlConfig;
    }

    public void setPresignedUrlConfig(PresignedUrlConfig presignedUrlConfig) {
        this.presignedUrlConfig = presignedUrlConfig;
    }

    public Job withPresignedUrlConfig(PresignedUrlConfig presignedUrlConfig) {
        this.presignedUrlConfig = presignedUrlConfig;
        return this;
    }

    public JobExecutionsRolloutConfig getJobExecutionsRolloutConfig() {
        return this.jobExecutionsRolloutConfig;
    }

    public void setJobExecutionsRolloutConfig(JobExecutionsRolloutConfig jobExecutionsRolloutConfig) {
        this.jobExecutionsRolloutConfig = jobExecutionsRolloutConfig;
    }

    public Job withJobExecutionsRolloutConfig(JobExecutionsRolloutConfig jobExecutionsRolloutConfig) {
        this.jobExecutionsRolloutConfig = jobExecutionsRolloutConfig;
        return this;
    }

    public AbortConfig getAbortConfig() {
        return this.abortConfig;
    }

    public void setAbortConfig(AbortConfig abortConfig) {
        this.abortConfig = abortConfig;
    }

    public Job withAbortConfig(AbortConfig abortConfig) {
        this.abortConfig = abortConfig;
        return this;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    public Job withCreatedAt(Date date) {
        this.createdAt = date;
        return this;
    }

    public Date getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
    }

    public Job withLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
        return this;
    }

    public Date getCompletedAt() {
        return this.completedAt;
    }

    public void setCompletedAt(Date date) {
        this.completedAt = date;
    }

    public Job withCompletedAt(Date date) {
        this.completedAt = date;
        return this;
    }

    public JobProcessDetails getJobProcessDetails() {
        return this.jobProcessDetails;
    }

    public void setJobProcessDetails(JobProcessDetails jobProcessDetails) {
        this.jobProcessDetails = jobProcessDetails;
    }

    public Job withJobProcessDetails(JobProcessDetails jobProcessDetails) {
        this.jobProcessDetails = jobProcessDetails;
        return this;
    }

    public TimeoutConfig getTimeoutConfig() {
        return this.timeoutConfig;
    }

    public void setTimeoutConfig(TimeoutConfig timeoutConfig) {
        this.timeoutConfig = timeoutConfig;
    }

    public Job withTimeoutConfig(TimeoutConfig timeoutConfig) {
        this.timeoutConfig = timeoutConfig;
        return this;
    }

    public String getNamespaceId() {
        return this.namespaceId;
    }

    public void setNamespaceId(String str) {
        this.namespaceId = str;
    }

    public Job withNamespaceId(String str) {
        this.namespaceId = str;
        return this;
    }

    public String getJobTemplateArn() {
        return this.jobTemplateArn;
    }

    public void setJobTemplateArn(String str) {
        this.jobTemplateArn = str;
    }

    public Job withJobTemplateArn(String str) {
        this.jobTemplateArn = str;
        return this;
    }

    public JobExecutionsRetryConfig getJobExecutionsRetryConfig() {
        return this.jobExecutionsRetryConfig;
    }

    public void setJobExecutionsRetryConfig(JobExecutionsRetryConfig jobExecutionsRetryConfig) {
        this.jobExecutionsRetryConfig = jobExecutionsRetryConfig;
    }

    public Job withJobExecutionsRetryConfig(JobExecutionsRetryConfig jobExecutionsRetryConfig) {
        this.jobExecutionsRetryConfig = jobExecutionsRetryConfig;
        return this;
    }

    public Map<String, String> getDocumentParameters() {
        return this.documentParameters;
    }

    public void setDocumentParameters(Map<String, String> map) {
        this.documentParameters = map;
    }

    public Job withDocumentParameters(Map<String, String> map) {
        this.documentParameters = map;
        return this;
    }

    public Job adddocumentParametersEntry(String str, String str2) {
        if (this.documentParameters == null) {
            this.documentParameters = new HashMap();
        }
        if (this.documentParameters.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.documentParameters.put(str, str2);
        return this;
    }

    public Job cleardocumentParametersEntries() {
        this.documentParameters = null;
        return this;
    }

    public Boolean isIsConcurrent() {
        return this.isConcurrent;
    }

    public Boolean getIsConcurrent() {
        return this.isConcurrent;
    }

    public void setIsConcurrent(Boolean bool) {
        this.isConcurrent = bool;
    }

    public Job withIsConcurrent(Boolean bool) {
        this.isConcurrent = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getJobArn() != null) {
            sb.append("jobArn: " + getJobArn() + ",");
        }
        if (getJobId() != null) {
            sb.append("jobId: " + getJobId() + ",");
        }
        if (getTargetSelection() != null) {
            sb.append("targetSelection: " + getTargetSelection() + ",");
        }
        if (getStatus() != null) {
            sb.append("status: " + getStatus() + ",");
        }
        if (getForceCanceled() != null) {
            sb.append("forceCanceled: " + getForceCanceled() + ",");
        }
        if (getReasonCode() != null) {
            sb.append("reasonCode: " + getReasonCode() + ",");
        }
        if (getComment() != null) {
            sb.append("comment: " + getComment() + ",");
        }
        if (getTargets() != null) {
            sb.append("targets: " + getTargets() + ",");
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
        if (getCreatedAt() != null) {
            sb.append("createdAt: " + getCreatedAt() + ",");
        }
        if (getLastUpdatedAt() != null) {
            sb.append("lastUpdatedAt: " + getLastUpdatedAt() + ",");
        }
        if (getCompletedAt() != null) {
            sb.append("completedAt: " + getCompletedAt() + ",");
        }
        if (getJobProcessDetails() != null) {
            sb.append("jobProcessDetails: " + getJobProcessDetails() + ",");
        }
        if (getTimeoutConfig() != null) {
            sb.append("timeoutConfig: " + getTimeoutConfig() + ",");
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
            sb.append("documentParameters: " + getDocumentParameters() + ",");
        }
        if (getIsConcurrent() != null) {
            sb.append("isConcurrent: " + getIsConcurrent());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((getJobArn() == null ? 0 : getJobArn().hashCode()) + 31) * 31) + (getJobId() == null ? 0 : getJobId().hashCode())) * 31) + (getTargetSelection() == null ? 0 : getTargetSelection().hashCode())) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getForceCanceled() == null ? 0 : getForceCanceled().hashCode())) * 31) + (getReasonCode() == null ? 0 : getReasonCode().hashCode())) * 31) + (getComment() == null ? 0 : getComment().hashCode())) * 31) + (getTargets() == null ? 0 : getTargets().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getPresignedUrlConfig() == null ? 0 : getPresignedUrlConfig().hashCode())) * 31) + (getJobExecutionsRolloutConfig() == null ? 0 : getJobExecutionsRolloutConfig().hashCode())) * 31) + (getAbortConfig() == null ? 0 : getAbortConfig().hashCode())) * 31) + (getCreatedAt() == null ? 0 : getCreatedAt().hashCode())) * 31) + (getLastUpdatedAt() == null ? 0 : getLastUpdatedAt().hashCode())) * 31) + (getCompletedAt() == null ? 0 : getCompletedAt().hashCode())) * 31) + (getJobProcessDetails() == null ? 0 : getJobProcessDetails().hashCode())) * 31) + (getTimeoutConfig() == null ? 0 : getTimeoutConfig().hashCode())) * 31) + (getNamespaceId() == null ? 0 : getNamespaceId().hashCode())) * 31) + (getJobTemplateArn() == null ? 0 : getJobTemplateArn().hashCode())) * 31) + (getJobExecutionsRetryConfig() == null ? 0 : getJobExecutionsRetryConfig().hashCode())) * 31) + (getDocumentParameters() == null ? 0 : getDocumentParameters().hashCode())) * 31) + (getIsConcurrent() != null ? getIsConcurrent().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Job)) {
            return false;
        }
        Job job = (Job) obj;
        if ((job.getJobArn() == null) ^ (getJobArn() == null)) {
            return false;
        }
        if (job.getJobArn() != null && !job.getJobArn().equals(getJobArn())) {
            return false;
        }
        if ((job.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (job.getJobId() != null && !job.getJobId().equals(getJobId())) {
            return false;
        }
        if ((job.getTargetSelection() == null) ^ (getTargetSelection() == null)) {
            return false;
        }
        if (job.getTargetSelection() != null && !job.getTargetSelection().equals(getTargetSelection())) {
            return false;
        }
        if ((job.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (job.getStatus() != null && !job.getStatus().equals(getStatus())) {
            return false;
        }
        if ((job.getForceCanceled() == null) ^ (getForceCanceled() == null)) {
            return false;
        }
        if (job.getForceCanceled() != null && !job.getForceCanceled().equals(getForceCanceled())) {
            return false;
        }
        if ((job.getReasonCode() == null) ^ (getReasonCode() == null)) {
            return false;
        }
        if (job.getReasonCode() != null && !job.getReasonCode().equals(getReasonCode())) {
            return false;
        }
        if ((job.getComment() == null) ^ (getComment() == null)) {
            return false;
        }
        if (job.getComment() != null && !job.getComment().equals(getComment())) {
            return false;
        }
        if ((job.getTargets() == null) ^ (getTargets() == null)) {
            return false;
        }
        if (job.getTargets() != null && !job.getTargets().equals(getTargets())) {
            return false;
        }
        if ((job.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (job.getDescription() != null && !job.getDescription().equals(getDescription())) {
            return false;
        }
        if ((job.getPresignedUrlConfig() == null) ^ (getPresignedUrlConfig() == null)) {
            return false;
        }
        if (job.getPresignedUrlConfig() != null && !job.getPresignedUrlConfig().equals(getPresignedUrlConfig())) {
            return false;
        }
        if ((job.getJobExecutionsRolloutConfig() == null) ^ (getJobExecutionsRolloutConfig() == null)) {
            return false;
        }
        if (job.getJobExecutionsRolloutConfig() != null && !job.getJobExecutionsRolloutConfig().equals(getJobExecutionsRolloutConfig())) {
            return false;
        }
        if ((job.getAbortConfig() == null) ^ (getAbortConfig() == null)) {
            return false;
        }
        if (job.getAbortConfig() != null && !job.getAbortConfig().equals(getAbortConfig())) {
            return false;
        }
        if ((job.getCreatedAt() == null) ^ (getCreatedAt() == null)) {
            return false;
        }
        if (job.getCreatedAt() != null && !job.getCreatedAt().equals(getCreatedAt())) {
            return false;
        }
        if ((job.getLastUpdatedAt() == null) ^ (getLastUpdatedAt() == null)) {
            return false;
        }
        if (job.getLastUpdatedAt() != null && !job.getLastUpdatedAt().equals(getLastUpdatedAt())) {
            return false;
        }
        if ((job.getCompletedAt() == null) ^ (getCompletedAt() == null)) {
            return false;
        }
        if (job.getCompletedAt() != null && !job.getCompletedAt().equals(getCompletedAt())) {
            return false;
        }
        if ((job.getJobProcessDetails() == null) ^ (getJobProcessDetails() == null)) {
            return false;
        }
        if (job.getJobProcessDetails() != null && !job.getJobProcessDetails().equals(getJobProcessDetails())) {
            return false;
        }
        if ((job.getTimeoutConfig() == null) ^ (getTimeoutConfig() == null)) {
            return false;
        }
        if (job.getTimeoutConfig() != null && !job.getTimeoutConfig().equals(getTimeoutConfig())) {
            return false;
        }
        if ((job.getNamespaceId() == null) ^ (getNamespaceId() == null)) {
            return false;
        }
        if (job.getNamespaceId() != null && !job.getNamespaceId().equals(getNamespaceId())) {
            return false;
        }
        if ((job.getJobTemplateArn() == null) ^ (getJobTemplateArn() == null)) {
            return false;
        }
        if (job.getJobTemplateArn() != null && !job.getJobTemplateArn().equals(getJobTemplateArn())) {
            return false;
        }
        if ((job.getJobExecutionsRetryConfig() == null) ^ (getJobExecutionsRetryConfig() == null)) {
            return false;
        }
        if (job.getJobExecutionsRetryConfig() != null && !job.getJobExecutionsRetryConfig().equals(getJobExecutionsRetryConfig())) {
            return false;
        }
        if ((job.getDocumentParameters() == null) ^ (getDocumentParameters() == null)) {
            return false;
        }
        if (job.getDocumentParameters() != null && !job.getDocumentParameters().equals(getDocumentParameters())) {
            return false;
        }
        if ((job.getIsConcurrent() == null) ^ (getIsConcurrent() == null)) {
            return false;
        }
        return job.getIsConcurrent() == null || job.getIsConcurrent().equals(getIsConcurrent());
    }
}
