package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AuthResult implements Serializable {
    private Allowed allowed;
    private String authDecision;
    private AuthInfo authInfo;
    private Denied denied;
    private List<String> missingContextValues;

    public AuthInfo getAuthInfo() {
        return this.authInfo;
    }

    public void setAuthInfo(AuthInfo authInfo) {
        this.authInfo = authInfo;
    }

    public AuthResult withAuthInfo(AuthInfo authInfo) {
        this.authInfo = authInfo;
        return this;
    }

    public Allowed getAllowed() {
        return this.allowed;
    }

    public void setAllowed(Allowed allowed) {
        this.allowed = allowed;
    }

    public AuthResult withAllowed(Allowed allowed) {
        this.allowed = allowed;
        return this;
    }

    public Denied getDenied() {
        return this.denied;
    }

    public void setDenied(Denied denied) {
        this.denied = denied;
    }

    public AuthResult withDenied(Denied denied) {
        this.denied = denied;
        return this;
    }

    public String getAuthDecision() {
        return this.authDecision;
    }

    public void setAuthDecision(String str) {
        this.authDecision = str;
    }

    public AuthResult withAuthDecision(String str) {
        this.authDecision = str;
        return this;
    }

    public void setAuthDecision(AuthDecision authDecision) {
        this.authDecision = authDecision.toString();
    }

    public AuthResult withAuthDecision(AuthDecision authDecision) {
        this.authDecision = authDecision.toString();
        return this;
    }

    public List<String> getMissingContextValues() {
        return this.missingContextValues;
    }

    public void setMissingContextValues(Collection<String> collection) {
        if (collection == null) {
            this.missingContextValues = null;
        } else {
            this.missingContextValues = new ArrayList(collection);
        }
    }

    public AuthResult withMissingContextValues(String... strArr) {
        if (getMissingContextValues() == null) {
            this.missingContextValues = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.missingContextValues.add(str);
        }
        return this;
    }

    public AuthResult withMissingContextValues(Collection<String> collection) {
        setMissingContextValues(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAuthInfo() != null) {
            sb.append("authInfo: " + getAuthInfo() + ",");
        }
        if (getAllowed() != null) {
            sb.append("allowed: " + getAllowed() + ",");
        }
        if (getDenied() != null) {
            sb.append("denied: " + getDenied() + ",");
        }
        if (getAuthDecision() != null) {
            sb.append("authDecision: " + getAuthDecision() + ",");
        }
        if (getMissingContextValues() != null) {
            sb.append("missingContextValues: " + getMissingContextValues());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getAuthInfo() == null ? 0 : getAuthInfo().hashCode()) + 31) * 31) + (getAllowed() == null ? 0 : getAllowed().hashCode())) * 31) + (getDenied() == null ? 0 : getDenied().hashCode())) * 31) + (getAuthDecision() == null ? 0 : getAuthDecision().hashCode())) * 31) + (getMissingContextValues() != null ? getMissingContextValues().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AuthResult)) {
            return false;
        }
        AuthResult authResult = (AuthResult) obj;
        if ((authResult.getAuthInfo() == null) ^ (getAuthInfo() == null)) {
            return false;
        }
        if (authResult.getAuthInfo() != null && !authResult.getAuthInfo().equals(getAuthInfo())) {
            return false;
        }
        if ((authResult.getAllowed() == null) ^ (getAllowed() == null)) {
            return false;
        }
        if (authResult.getAllowed() != null && !authResult.getAllowed().equals(getAllowed())) {
            return false;
        }
        if ((authResult.getDenied() == null) ^ (getDenied() == null)) {
            return false;
        }
        if (authResult.getDenied() != null && !authResult.getDenied().equals(getDenied())) {
            return false;
        }
        if ((authResult.getAuthDecision() == null) ^ (getAuthDecision() == null)) {
            return false;
        }
        if (authResult.getAuthDecision() != null && !authResult.getAuthDecision().equals(getAuthDecision())) {
            return false;
        }
        if ((authResult.getMissingContextValues() == null) ^ (getMissingContextValues() == null)) {
            return false;
        }
        return authResult.getMissingContextValues() == null || authResult.getMissingContextValues().equals(getMissingContextValues());
    }
}
