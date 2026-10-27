package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class StartThingRegistrationTaskRequest extends AmazonWebServiceRequest implements Serializable {
    private String inputFileBucket;
    private String inputFileKey;
    private String roleArn;
    private String templateBody;

    public String getTemplateBody() {
        return this.templateBody;
    }

    public void setTemplateBody(String str) {
        this.templateBody = str;
    }

    public StartThingRegistrationTaskRequest withTemplateBody(String str) {
        this.templateBody = str;
        return this;
    }

    public String getInputFileBucket() {
        return this.inputFileBucket;
    }

    public void setInputFileBucket(String str) {
        this.inputFileBucket = str;
    }

    public StartThingRegistrationTaskRequest withInputFileBucket(String str) {
        this.inputFileBucket = str;
        return this;
    }

    public String getInputFileKey() {
        return this.inputFileKey;
    }

    public void setInputFileKey(String str) {
        this.inputFileKey = str;
    }

    public StartThingRegistrationTaskRequest withInputFileKey(String str) {
        this.inputFileKey = str;
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public StartThingRegistrationTaskRequest withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
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
            sb.append("roleArn: " + getRoleArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((getTemplateBody() == null ? 0 : getTemplateBody().hashCode()) + 31) * 31) + (getInputFileBucket() == null ? 0 : getInputFileBucket().hashCode())) * 31) + (getInputFileKey() == null ? 0 : getInputFileKey().hashCode())) * 31) + (getRoleArn() != null ? getRoleArn().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StartThingRegistrationTaskRequest)) {
            return false;
        }
        StartThingRegistrationTaskRequest startThingRegistrationTaskRequest = (StartThingRegistrationTaskRequest) obj;
        if ((startThingRegistrationTaskRequest.getTemplateBody() == null) ^ (getTemplateBody() == null)) {
            return false;
        }
        if (startThingRegistrationTaskRequest.getTemplateBody() != null && !startThingRegistrationTaskRequest.getTemplateBody().equals(getTemplateBody())) {
            return false;
        }
        if ((startThingRegistrationTaskRequest.getInputFileBucket() == null) ^ (getInputFileBucket() == null)) {
            return false;
        }
        if (startThingRegistrationTaskRequest.getInputFileBucket() != null && !startThingRegistrationTaskRequest.getInputFileBucket().equals(getInputFileBucket())) {
            return false;
        }
        if ((startThingRegistrationTaskRequest.getInputFileKey() == null) ^ (getInputFileKey() == null)) {
            return false;
        }
        if (startThingRegistrationTaskRequest.getInputFileKey() != null && !startThingRegistrationTaskRequest.getInputFileKey().equals(getInputFileKey())) {
            return false;
        }
        if ((startThingRegistrationTaskRequest.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        return startThingRegistrationTaskRequest.getRoleArn() == null || startThingRegistrationTaskRequest.getRoleArn().equals(getRoleArn());
    }
}
