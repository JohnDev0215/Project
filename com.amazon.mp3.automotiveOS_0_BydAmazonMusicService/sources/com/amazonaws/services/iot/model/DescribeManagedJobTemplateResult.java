package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeManagedJobTemplateResult implements Serializable {
    private String description;
    private String document;
    private List<DocumentParameter> documentParameters;
    private List<String> environments;
    private String templateArn;
    private String templateName;
    private String templateVersion;

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    public DescribeManagedJobTemplateResult withTemplateName(String str) {
        this.templateName = str;
        return this;
    }

    public String getTemplateArn() {
        return this.templateArn;
    }

    public void setTemplateArn(String str) {
        this.templateArn = str;
    }

    public DescribeManagedJobTemplateResult withTemplateArn(String str) {
        this.templateArn = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public DescribeManagedJobTemplateResult withDescription(String str) {
        this.description = str;
        return this;
    }

    public String getTemplateVersion() {
        return this.templateVersion;
    }

    public void setTemplateVersion(String str) {
        this.templateVersion = str;
    }

    public DescribeManagedJobTemplateResult withTemplateVersion(String str) {
        this.templateVersion = str;
        return this;
    }

    public List<String> getEnvironments() {
        return this.environments;
    }

    public void setEnvironments(Collection<String> collection) {
        if (collection == null) {
            this.environments = null;
        } else {
            this.environments = new ArrayList(collection);
        }
    }

    public DescribeManagedJobTemplateResult withEnvironments(String... strArr) {
        if (getEnvironments() == null) {
            this.environments = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.environments.add(str);
        }
        return this;
    }

    public DescribeManagedJobTemplateResult withEnvironments(Collection<String> collection) {
        setEnvironments(collection);
        return this;
    }

    public List<DocumentParameter> getDocumentParameters() {
        return this.documentParameters;
    }

    public void setDocumentParameters(Collection<DocumentParameter> collection) {
        if (collection == null) {
            this.documentParameters = null;
        } else {
            this.documentParameters = new ArrayList(collection);
        }
    }

    public DescribeManagedJobTemplateResult withDocumentParameters(DocumentParameter... documentParameterArr) {
        if (getDocumentParameters() == null) {
            this.documentParameters = new ArrayList(documentParameterArr.length);
        }
        for (DocumentParameter documentParameter : documentParameterArr) {
            this.documentParameters.add(documentParameter);
        }
        return this;
    }

    public DescribeManagedJobTemplateResult withDocumentParameters(Collection<DocumentParameter> collection) {
        setDocumentParameters(collection);
        return this;
    }

    public String getDocument() {
        return this.document;
    }

    public void setDocument(String str) {
        this.document = str;
    }

    public DescribeManagedJobTemplateResult withDocument(String str) {
        this.document = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTemplateName() != null) {
            sb.append("templateName: " + getTemplateName() + ",");
        }
        if (getTemplateArn() != null) {
            sb.append("templateArn: " + getTemplateArn() + ",");
        }
        if (getDescription() != null) {
            sb.append("description: " + getDescription() + ",");
        }
        if (getTemplateVersion() != null) {
            sb.append("templateVersion: " + getTemplateVersion() + ",");
        }
        if (getEnvironments() != null) {
            sb.append("environments: " + getEnvironments() + ",");
        }
        if (getDocumentParameters() != null) {
            sb.append("documentParameters: " + getDocumentParameters() + ",");
        }
        if (getDocument() != null) {
            sb.append("document: " + getDocument());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((((getTemplateName() == null ? 0 : getTemplateName().hashCode()) + 31) * 31) + (getTemplateArn() == null ? 0 : getTemplateArn().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getTemplateVersion() == null ? 0 : getTemplateVersion().hashCode())) * 31) + (getEnvironments() == null ? 0 : getEnvironments().hashCode())) * 31) + (getDocumentParameters() == null ? 0 : getDocumentParameters().hashCode())) * 31) + (getDocument() != null ? getDocument().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeManagedJobTemplateResult)) {
            return false;
        }
        DescribeManagedJobTemplateResult describeManagedJobTemplateResult = (DescribeManagedJobTemplateResult) obj;
        if ((describeManagedJobTemplateResult.getTemplateName() == null) ^ (getTemplateName() == null)) {
            return false;
        }
        if (describeManagedJobTemplateResult.getTemplateName() != null && !describeManagedJobTemplateResult.getTemplateName().equals(getTemplateName())) {
            return false;
        }
        if ((describeManagedJobTemplateResult.getTemplateArn() == null) ^ (getTemplateArn() == null)) {
            return false;
        }
        if (describeManagedJobTemplateResult.getTemplateArn() != null && !describeManagedJobTemplateResult.getTemplateArn().equals(getTemplateArn())) {
            return false;
        }
        if ((describeManagedJobTemplateResult.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (describeManagedJobTemplateResult.getDescription() != null && !describeManagedJobTemplateResult.getDescription().equals(getDescription())) {
            return false;
        }
        if ((describeManagedJobTemplateResult.getTemplateVersion() == null) ^ (getTemplateVersion() == null)) {
            return false;
        }
        if (describeManagedJobTemplateResult.getTemplateVersion() != null && !describeManagedJobTemplateResult.getTemplateVersion().equals(getTemplateVersion())) {
            return false;
        }
        if ((describeManagedJobTemplateResult.getEnvironments() == null) ^ (getEnvironments() == null)) {
            return false;
        }
        if (describeManagedJobTemplateResult.getEnvironments() != null && !describeManagedJobTemplateResult.getEnvironments().equals(getEnvironments())) {
            return false;
        }
        if ((describeManagedJobTemplateResult.getDocumentParameters() == null) ^ (getDocumentParameters() == null)) {
            return false;
        }
        if (describeManagedJobTemplateResult.getDocumentParameters() != null && !describeManagedJobTemplateResult.getDocumentParameters().equals(getDocumentParameters())) {
            return false;
        }
        if ((describeManagedJobTemplateResult.getDocument() == null) ^ (getDocument() == null)) {
            return false;
        }
        return describeManagedJobTemplateResult.getDocument() == null || describeManagedJobTemplateResult.getDocument().equals(getDocument());
    }
}
