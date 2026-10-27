package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeProvisioningTemplateRequest extends AmazonWebServiceRequest implements Serializable {
    private String templateName;

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    public DescribeProvisioningTemplateRequest withTemplateName(String str) {
        this.templateName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTemplateName() != null) {
            sb.append("templateName: " + getTemplateName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getTemplateName() == null ? 0 : getTemplateName().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeProvisioningTemplateRequest)) {
            return false;
        }
        DescribeProvisioningTemplateRequest describeProvisioningTemplateRequest = (DescribeProvisioningTemplateRequest) obj;
        if ((describeProvisioningTemplateRequest.getTemplateName() == null) ^ (getTemplateName() == null)) {
            return false;
        }
        return describeProvisioningTemplateRequest.getTemplateName() == null || describeProvisioningTemplateRequest.getTemplateName().equals(getTemplateName());
    }
}
