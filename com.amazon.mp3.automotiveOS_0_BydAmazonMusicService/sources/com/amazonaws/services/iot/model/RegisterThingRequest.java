package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class RegisterThingRequest extends AmazonWebServiceRequest implements Serializable {
    private Map<String, String> parameters;
    private String templateBody;

    public String getTemplateBody() {
        return this.templateBody;
    }

    public void setTemplateBody(String str) {
        this.templateBody = str;
    }

    public RegisterThingRequest withTemplateBody(String str) {
        this.templateBody = str;
        return this;
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public void setParameters(Map<String, String> map) {
        this.parameters = map;
    }

    public RegisterThingRequest withParameters(Map<String, String> map) {
        this.parameters = map;
        return this;
    }

    public RegisterThingRequest addparametersEntry(String str, String str2) {
        if (this.parameters == null) {
            this.parameters = new HashMap();
        }
        if (this.parameters.containsKey(str)) {
            throw new IllegalArgumentException("Duplicated keys (" + str.toString() + ") are provided.");
        }
        this.parameters.put(str, str2);
        return this;
    }

    public RegisterThingRequest clearparametersEntries() {
        this.parameters = null;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTemplateBody() != null) {
            sb.append("templateBody: " + getTemplateBody() + ",");
        }
        if (getParameters() != null) {
            sb.append("parameters: " + getParameters());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getTemplateBody() == null ? 0 : getTemplateBody().hashCode()) + 31) * 31) + (getParameters() != null ? getParameters().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RegisterThingRequest)) {
            return false;
        }
        RegisterThingRequest registerThingRequest = (RegisterThingRequest) obj;
        if ((registerThingRequest.getTemplateBody() == null) ^ (getTemplateBody() == null)) {
            return false;
        }
        if (registerThingRequest.getTemplateBody() != null && !registerThingRequest.getTemplateBody().equals(getTemplateBody())) {
            return false;
        }
        if ((registerThingRequest.getParameters() == null) ^ (getParameters() == null)) {
            return false;
        }
        return registerThingRequest.getParameters() == null || registerThingRequest.getParameters().equals(getParameters());
    }
}
