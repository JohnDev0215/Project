package com.amazonaws.services.iot.model;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public class ValidationError implements Serializable {
    private String errorMessage;

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public ValidationError withErrorMessage(String str) {
        this.errorMessage = str;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getErrorMessage() != null) {
            sb.append("errorMessage: " + getErrorMessage());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getErrorMessage() == null ? 0 : getErrorMessage().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ValidationError)) {
            return false;
        }
        ValidationError validationError = (ValidationError) obj;
        if ((validationError.getErrorMessage() == null) ^ (getErrorMessage() == null)) {
            return false;
        }
        return validationError.getErrorMessage() == null || validationError.getErrorMessage().equals(getErrorMessage());
    }
}
