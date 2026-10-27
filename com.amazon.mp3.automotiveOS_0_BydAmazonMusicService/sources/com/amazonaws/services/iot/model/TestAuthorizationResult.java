package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class TestAuthorizationResult implements Serializable {
    private List<AuthResult> authResults;

    public List<AuthResult> getAuthResults() {
        return this.authResults;
    }

    public void setAuthResults(Collection<AuthResult> collection) {
        if (collection == null) {
            this.authResults = null;
        } else {
            this.authResults = new ArrayList(collection);
        }
    }

    public TestAuthorizationResult withAuthResults(AuthResult... authResultArr) {
        if (getAuthResults() == null) {
            this.authResults = new ArrayList(authResultArr.length);
        }
        for (AuthResult authResult : authResultArr) {
            this.authResults.add(authResult);
        }
        return this;
    }

    public TestAuthorizationResult withAuthResults(Collection<AuthResult> collection) {
        setAuthResults(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAuthResults() != null) {
            sb.append("authResults: " + getAuthResults());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 31 + (getAuthResults() == null ? 0 : getAuthResults().hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TestAuthorizationResult)) {
            return false;
        }
        TestAuthorizationResult testAuthorizationResult = (TestAuthorizationResult) obj;
        if ((testAuthorizationResult.getAuthResults() == null) ^ (getAuthResults() == null)) {
            return false;
        }
        return testAuthorizationResult.getAuthResults() == null || testAuthorizationResult.getAuthResults().equals(getAuthResults());
    }
}
