package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeThingRegistrationTaskResult implements Serializable {
    private Date creationDate;
    private Integer failureCount;
    private String inputFileBucket;
    private String inputFileKey;
    private Date lastModifiedDate;
    private String message;
    private Integer percentageProgress;
    private String roleArn;
    private String status;
    private Integer successCount;
    private String taskId;
    private String templateBody;

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public DescribeThingRegistrationTaskResult withTaskId(String str) {
        this.taskId = str;
        return this;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public DescribeThingRegistrationTaskResult withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public DescribeThingRegistrationTaskResult withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public String getTemplateBody() {
        return this.templateBody;
    }

    public void setTemplateBody(String str) {
        this.templateBody = str;
    }

    public DescribeThingRegistrationTaskResult withTemplateBody(String str) {
        this.templateBody = str;
        return this;
    }

    public String getInputFileBucket() {
        return this.inputFileBucket;
    }

    public void setInputFileBucket(String str) {
        this.inputFileBucket = str;
    }

    public DescribeThingRegistrationTaskResult withInputFileBucket(String str) {
        this.inputFileBucket = str;
        return this;
    }

    public String getInputFileKey() {
        return this.inputFileKey;
    }

    public void setInputFileKey(String str) {
        this.inputFileKey = str;
    }

    public DescribeThingRegistrationTaskResult withInputFileKey(String str) {
        this.inputFileKey = str;
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public DescribeThingRegistrationTaskResult withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public DescribeThingRegistrationTaskResult withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status.toString();
    }

    public DescribeThingRegistrationTaskResult withStatus(Status status) {
        this.status = status.toString();
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public DescribeThingRegistrationTaskResult withMessage(String str) {
        this.message = str;
        return this;
    }

    public Integer getSuccessCount() {
        return this.successCount;
    }

    public void setSuccessCount(Integer num) {
        this.successCount = num;
    }

    public DescribeThingRegistrationTaskResult withSuccessCount(Integer num) {
        this.successCount = num;
        return this;
    }

    public Integer getFailureCount() {
        return this.failureCount;
    }

    public void setFailureCount(Integer num) {
        this.failureCount = num;
    }

    public DescribeThingRegistrationTaskResult withFailureCount(Integer num) {
        this.failureCount = num;
        return this;
    }

    public Integer getPercentageProgress() {
        return this.percentageProgress;
    }

    public void setPercentageProgress(Integer num) {
        this.percentageProgress = num;
    }

    public DescribeThingRegistrationTaskResult withPercentageProgress(Integer num) {
        this.percentageProgress = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTaskId() != null) {
            sb.append("taskId: " + getTaskId() + ",");
        }
        if (getCreationDate() != null) {
            sb.append("creationDate: " + getCreationDate() + ",");
        }
        if (getLastModifiedDate() != null) {
            sb.append("lastModifiedDate: " + getLastModifiedDate() + ",");
        }
        if (getTemplateBody() != null) {
            sb.append("templateBody: " + getTemplateBody() + ",");
        }
        if (getInputFileBucket() != null) {
            sb.append("inputFileBucket: " + getInputFileBucket() + ",");
        }
        if (getInputFileKey() != null) {
            sb.append("inputFileKey: " + getInputFileKey() + ",");
        }
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getStatus() != null) {
            sb.append("status: " + getStatus() + ",");
        }
        if (getMessage() != null) {
            sb.append("message: " + getMessage() + ",");
        }
        if (getSuccessCount() != null) {
            sb.append("successCount: " + getSuccessCount() + ",");
        }
        if (getFailureCount() != null) {
            sb.append("failureCount: " + getFailureCount() + ",");
        }
        if (getPercentageProgress() != null) {
            sb.append("percentageProgress: " + getPercentageProgress());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((((((((((((getTaskId() == null ? 0 : getTaskId().hashCode()) + 31) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() == null ? 0 : getLastModifiedDate().hashCode())) * 31) + (getTemplateBody() == null ? 0 : getTemplateBody().hashCode())) * 31) + (getInputFileBucket() == null ? 0 : getInputFileBucket().hashCode())) * 31) + (getInputFileKey() == null ? 0 : getInputFileKey().hashCode())) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getMessage() == null ? 0 : getMessage().hashCode())) * 31) + (getSuccessCount() == null ? 0 : getSuccessCount().hashCode())) * 31) + (getFailureCount() == null ? 0 : getFailureCount().hashCode())) * 31) + (getPercentageProgress() != null ? getPercentageProgress().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeThingRegistrationTaskResult)) {
            return false;
        }
        DescribeThingRegistrationTaskResult describeThingRegistrationTaskResult = (DescribeThingRegistrationTaskResult) obj;
        if ((describeThingRegistrationTaskResult.getTaskId() == null) ^ (getTaskId() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getTaskId() != null && !describeThingRegistrationTaskResult.getTaskId().equals(getTaskId())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getCreationDate() != null && !describeThingRegistrationTaskResult.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getLastModifiedDate() != null && !describeThingRegistrationTaskResult.getLastModifiedDate().equals(getLastModifiedDate())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getTemplateBody() == null) ^ (getTemplateBody() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getTemplateBody() != null && !describeThingRegistrationTaskResult.getTemplateBody().equals(getTemplateBody())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getInputFileBucket() == null) ^ (getInputFileBucket() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getInputFileBucket() != null && !describeThingRegistrationTaskResult.getInputFileBucket().equals(getInputFileBucket())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getInputFileKey() == null) ^ (getInputFileKey() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getInputFileKey() != null && !describeThingRegistrationTaskResult.getInputFileKey().equals(getInputFileKey())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getRoleArn() != null && !describeThingRegistrationTaskResult.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getStatus() != null && !describeThingRegistrationTaskResult.getStatus().equals(getStatus())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getMessage() == null) ^ (getMessage() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getMessage() != null && !describeThingRegistrationTaskResult.getMessage().equals(getMessage())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getSuccessCount() == null) ^ (getSuccessCount() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getSuccessCount() != null && !describeThingRegistrationTaskResult.getSuccessCount().equals(getSuccessCount())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getFailureCount() == null) ^ (getFailureCount() == null)) {
            return false;
        }
        if (describeThingRegistrationTaskResult.getFailureCount() != null && !describeThingRegistrationTaskResult.getFailureCount().equals(getFailureCount())) {
            return false;
        }
        if ((describeThingRegistrationTaskResult.getPercentageProgress() == null) ^ (getPercentageProgress() == null)) {
            return false;
        }
        return describeThingRegistrationTaskResult.getPercentageProgress() == null || describeThingRegistrationTaskResult.getPercentageProgress().equals(getPercentageProgress());
    }
}
