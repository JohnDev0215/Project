package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeProvisioningTemplateVersionRequest extends AmazonWebServiceRequest implements Serializable {
    private String templateName;
    private Integer versionId;

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    public DescribeProvisioningTemplateVersionRequest withTemplateName(String str) {
        this.templateName = str;
        return this;
    }

    public Integer getVersionId() {
        return this.versionId;
    }

    public void setVersionId(Integer num) {
        this.versionId = num;
    }

    public DescribeProvisioningTemplateVersionRequest withVersionId(Integer num) {
        this.versionId = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTemplateName() != null) {
            sb.append("templateName: " + getTemplateName() + ",");
        }
        if (getVersionId() != null) {
            sb.append("versionId: " + getVersionId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getTemplateName() == null ? 0 : getTemplateName().hashCode()) + 31) * 31) + (getVersionId() != null ? getVersionId().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeProvisioningTemplateVersionRequest)) {
            return false;
        }
        DescribeProvisioningTemplateVersionRequest describeProvisioningTemplateVersionRequest = (DescribeProvisioningTemplateVersionRequest) obj;
        if ((describeProvisioningTemplateVersionRequest.getTemplateName() == null) ^ (getTemplateName() == null)) {
            return false;
        }
        if (describeProvisioningTemplateVersionRequest.getTemplateName() != null && !describeProvisioningTemplateVersionRequest.getTemplateName().equals(getTemplateName())) {
            return false;
        }
        if ((describeProvisioningTemplateVersionRequest.getVersionId() == null) ^ (getVersionId() == null)) {
            return false;
        }
        return describeProvisioningTemplateVersionRequest.getVersionId() == null || describeProvisioningTemplateVersionRequest.getVersionId().equals(getVersionId());
    }
}
