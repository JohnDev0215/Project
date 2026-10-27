package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ReplaceDefaultPolicyVersionParams implements Serializable {
    private String templateName;

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    public ReplaceDefaultPolicyVersionParams withTemplateName(String str) {
        this.templateName = str;
        return this;
    }

    public void setTemplateName(PolicyTemplateName policyTemplateName) {
        this.templateName = policyTemplateName.toString();
    }

    public ReplaceDefaultPolicyVersionParams withTemplateName(PolicyTemplateName policyTemplateName) {
        this.templateName = policyTemplateName.toString();
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
        if (obj == null || !(obj instanceof ReplaceDefaultPolicyVersionParams)) {
            return false;
        }
        ReplaceDefaultPolicyVersionParams replaceDefaultPolicyVersionParams = (ReplaceDefaultPolicyVersionParams) obj;
        if ((replaceDefaultPolicyVersionParams.getTemplateName() == null) ^ (getTemplateName() == null)) {
            return false;
        }
        return replaceDefaultPolicyVersionParams.getTemplateName() == null || replaceDefaultPolicyVersionParams.getTemplateName().equals(getTemplateName());
    }
}
