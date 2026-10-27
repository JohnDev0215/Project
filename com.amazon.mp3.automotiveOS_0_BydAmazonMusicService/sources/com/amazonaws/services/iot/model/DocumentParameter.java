package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class DocumentParameter implements Serializable {
    private String description;
    private String example;
    private String key;
    private Boolean optional;
    private String regex;

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public DocumentParameter withKey(String str) {
        this.key = str;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public DocumentParameter withDescription(String str) {
        this.description = str;
        return this;
    }

    public String getRegex() {
        return this.regex;
    }

    public void setRegex(String str) {
        this.regex = str;
    }

    public DocumentParameter withRegex(String str) {
        this.regex = str;
        return this;
    }

    public String getExample() {
        return this.example;
    }

    public void setExample(String str) {
        this.example = str;
    }

    public DocumentParameter withExample(String str) {
        this.example = str;
        return this;
    }

    public Boolean isOptional() {
        return this.optional;
    }

    public Boolean getOptional() {
        return this.optional;
    }

    public void setOptional(Boolean bool) {
        this.optional = bool;
    }

    public DocumentParameter withOptional(Boolean bool) {
        this.optional = bool;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getKey() != null) {
            sb.append("key: " + getKey() + ",");
        }
        if (getDescription() != null) {
            sb.append("description: " + getDescription() + ",");
        }
        if (getRegex() != null) {
            sb.append("regex: " + getRegex() + ",");
        }
        if (getExample() != null) {
            sb.append("example: " + getExample() + ",");
        }
        if (getOptional() != null) {
            sb.append("optional: " + getOptional());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getKey() == null ? 0 : getKey().hashCode()) + 31) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getRegex() == null ? 0 : getRegex().hashCode())) * 31) + (getExample() == null ? 0 : getExample().hashCode())) * 31) + (getOptional() != null ? getOptional().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DocumentParameter)) {
            return false;
        }
        DocumentParameter documentParameter = (DocumentParameter) obj;
        if ((documentParameter.getKey() == null) ^ (getKey() == null)) {
            return false;
        }
        if (documentParameter.getKey() != null && !documentParameter.getKey().equals(getKey())) {
            return false;
        }
        if ((documentParameter.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (documentParameter.getDescription() != null && !documentParameter.getDescription().equals(getDescription())) {
            return false;
        }
        if ((documentParameter.getRegex() == null) ^ (getRegex() == null)) {
            return false;
        }
        if (documentParameter.getRegex() != null && !documentParameter.getRegex().equals(getRegex())) {
            return false;
        }
        if ((documentParameter.getExample() == null) ^ (getExample() == null)) {
            return false;
        }
        if (documentParameter.getExample() != null && !documentParameter.getExample().equals(getExample())) {
            return false;
        }
        if ((documentParameter.getOptional() == null) ^ (getOptional() == null)) {
            return false;
        }
        return documentParameter.getOptional() == null || documentParameter.getOptional().equals(getOptional());
    }
}
