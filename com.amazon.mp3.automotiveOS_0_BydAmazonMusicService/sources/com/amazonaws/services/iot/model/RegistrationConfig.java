package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class RegistrationConfig implements Serializable {
    private String roleArn;
    private String templateBody;
    private String templateName;

    public String getTemplateBody() {
        return this.templateBody;
    }

    public void setTemplateBody(String str) {
        this.templateBody = str;
    }

    public RegistrationConfig withTemplateBody(String str) {
        this.templateBody = str;
        return this;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public RegistrationConfig withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    public RegistrationConfig withTemplateName(String str) {
        this.templateName = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTemplateBody() != null) {
            sb.append("templateBody: " + getTemplateBody() + ",");
        }
        if (getRoleArn() != null) {
            sb.append("roleArn: " + getRoleArn() + ",");
        }
        if (getTemplateName() != null) {
            sb.append("templateName: " + getTemplateName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((getTemplateBody() == null ? 0 : getTemplateBody().hashCode()) + 31) * 31) + (getRoleArn() == null ? 0 : getRoleArn().hashCode())) * 31) + (getTemplateName() != null ? getTemplateName().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RegistrationConfig)) {
            return false;
        }
        RegistrationConfig registrationConfig = (RegistrationConfig) obj;
        if ((registrationConfig.getTemplateBody() == null) ^ (getTemplateBody() == null)) {
            return false;
        }
        if (registrationConfig.getTemplateBody() != null && !registrationConfig.getTemplateBody().equals(getTemplateBody())) {
            return false;
        }
        if ((registrationConfig.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (registrationConfig.getRoleArn() != null && !registrationConfig.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((registrationConfig.getTemplateName() == null) ^ (getTemplateName() == null)) {
            return false;
        }
        return registrationConfig.getTemplateName() == null || registrationConfig.getTemplateName().equals(getTemplateName());
    }
}
