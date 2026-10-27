package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ValidateSecurityProfileBehaviorsResult implements Serializable {
    private Boolean valid;
    private List<ValidationError> validationErrors;

    public Boolean isValid() {
        return this.valid;
    }

    public Boolean getValid() {
        return this.valid;
    }

    public void setValid(Boolean bool) {
        this.valid = bool;
    }

    public ValidateSecurityProfileBehaviorsResult withValid(Boolean bool) {
        this.valid = bool;
        return this;
    }

    public List<ValidationError> getValidationErrors() {
        return this.validationErrors;
    }

    public void setValidationErrors(Collection<ValidationError> collection) {
        if (collection == null) {
            this.validationErrors = null;
        } else {
            this.validationErrors = new ArrayList(collection);
        }
    }

    public ValidateSecurityProfileBehaviorsResult withValidationErrors(ValidationError... validationErrorArr) {
        if (getValidationErrors() == null) {
            this.validationErrors = new ArrayList(validationErrorArr.length);
        }
        for (ValidationError validationError : validationErrorArr) {
            this.validationErrors.add(validationError);
        }
        return this;
    }

    public ValidateSecurityProfileBehaviorsResult withValidationErrors(Collection<ValidationError> collection) {
        setValidationErrors(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getValid() != null) {
            sb.append("valid: " + getValid() + ",");
        }
        if (getValidationErrors() != null) {
            sb.append("validationErrors: " + getValidationErrors());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((getValid() == null ? 0 : getValid().hashCode()) + 31) * 31) + (getValidationErrors() != null ? getValidationErrors().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ValidateSecurityProfileBehaviorsResult)) {
            return false;
        }
        ValidateSecurityProfileBehaviorsResult validateSecurityProfileBehaviorsResult = (ValidateSecurityProfileBehaviorsResult) obj;
        if ((validateSecurityProfileBehaviorsResult.getValid() == null) ^ (getValid() == null)) {
            return false;
        }
        if (validateSecurityProfileBehaviorsResult.getValid() != null && !validateSecurityProfileBehaviorsResult.getValid().equals(getValid())) {
            return false;
        }
        if ((validateSecurityProfileBehaviorsResult.getValidationErrors() == null) ^ (getValidationErrors() == null)) {
            return false;
        }
        return validateSecurityProfileBehaviorsResult.getValidationErrors() == null || validateSecurityProfileBehaviorsResult.getValidationErrors().equals(getValidationErrors());
    }
}
