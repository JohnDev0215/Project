package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ErrorInfo implements Serializable {
    private String code;
    private String message;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public ErrorInfo withCode(String str) {
        this.code = str;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public ErrorInfo withMessage(String str) {
        this.message = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCode() != null) {
            sb.append("code: " + getCode() + ",");
        }
        if (getMessage() != null) {
            sb.append("message: " + getMessage());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getCode() == null ? 0 : getCode().hashCode()) + 31) * 31) + (getMessage() != null ? getMessage().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ErrorInfo)) {
            return false;
        }
        ErrorInfo errorInfo = (ErrorInfo) obj;
        if ((errorInfo.getCode() == null) ^ (getCode() == null)) {
            return false;
        }
        if (errorInfo.getCode() != null && !errorInfo.getCode().equals(getCode())) {
            return false;
        }
        if ((errorInfo.getMessage() == null) ^ (getMessage() == null)) {
            return false;
        }
        return errorInfo.getMessage() == null || errorInfo.getMessage().equals(getMessage());
    }
}
