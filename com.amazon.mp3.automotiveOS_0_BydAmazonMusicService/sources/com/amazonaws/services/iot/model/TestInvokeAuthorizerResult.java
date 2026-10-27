package com.amazonaws.services.iot.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class TestInvokeAuthorizerResult implements Serializable {
    private Integer disconnectAfterInSeconds;
    private Boolean isAuthenticated;
    private List<String> policyDocuments;
    private String principalId;
    private Integer refreshAfterInSeconds;

    public Boolean isIsAuthenticated() {
        return this.isAuthenticated;
    }

    public Boolean getIsAuthenticated() {
        return this.isAuthenticated;
    }

    public void setIsAuthenticated(Boolean bool) {
        this.isAuthenticated = bool;
    }

    public TestInvokeAuthorizerResult withIsAuthenticated(Boolean bool) {
        this.isAuthenticated = bool;
        return this;
    }

    public String getPrincipalId() {
        return this.principalId;
    }

    public void setPrincipalId(String str) {
        this.principalId = str;
    }

    public TestInvokeAuthorizerResult withPrincipalId(String str) {
        this.principalId = str;
        return this;
    }

    public List<String> getPolicyDocuments() {
        return this.policyDocuments;
    }

    public void setPolicyDocuments(Collection<String> collection) {
        if (collection == null) {
            this.policyDocuments = null;
        } else {
            this.policyDocuments = new ArrayList(collection);
        }
    }

    public TestInvokeAuthorizerResult withPolicyDocuments(String... strArr) {
        if (getPolicyDocuments() == null) {
            this.policyDocuments = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.policyDocuments.add(str);
        }
        return this;
    }

    public TestInvokeAuthorizerResult withPolicyDocuments(Collection<String> collection) {
        setPolicyDocuments(collection);
        return this;
    }

    public Integer getRefreshAfterInSeconds() {
        return this.refreshAfterInSeconds;
    }

    public void setRefreshAfterInSeconds(Integer num) {
        this.refreshAfterInSeconds = num;
    }

    public TestInvokeAuthorizerResult withRefreshAfterInSeconds(Integer num) {
        this.refreshAfterInSeconds = num;
        return this;
    }

    public Integer getDisconnectAfterInSeconds() {
        return this.disconnectAfterInSeconds;
    }

    public void setDisconnectAfterInSeconds(Integer num) {
        this.disconnectAfterInSeconds = num;
    }

    public TestInvokeAuthorizerResult withDisconnectAfterInSeconds(Integer num) {
        this.disconnectAfterInSeconds = num;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getIsAuthenticated() != null) {
            sb.append("isAuthenticated: " + getIsAuthenticated() + ",");
        }
        if (getPrincipalId() != null) {
            sb.append("principalId: " + getPrincipalId() + ",");
        }
        if (getPolicyDocuments() != null) {
            sb.append("policyDocuments: " + getPolicyDocuments() + ",");
        }
        if (getRefreshAfterInSeconds() != null) {
            sb.append("refreshAfterInSeconds: " + getRefreshAfterInSeconds() + ",");
        }
        if (getDisconnectAfterInSeconds() != null) {
            sb.append("disconnectAfterInSeconds: " + getDisconnectAfterInSeconds());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((getIsAuthenticated() == null ? 0 : getIsAuthenticated().hashCode()) + 31) * 31) + (getPrincipalId() == null ? 0 : getPrincipalId().hashCode())) * 31) + (getPolicyDocuments() == null ? 0 : getPolicyDocuments().hashCode())) * 31) + (getRefreshAfterInSeconds() == null ? 0 : getRefreshAfterInSeconds().hashCode())) * 31) + (getDisconnectAfterInSeconds() != null ? getDisconnectAfterInSeconds().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TestInvokeAuthorizerResult)) {
            return false;
        }
        TestInvokeAuthorizerResult testInvokeAuthorizerResult = (TestInvokeAuthorizerResult) obj;
        if ((testInvokeAuthorizerResult.getIsAuthenticated() == null) ^ (getIsAuthenticated() == null)) {
            return false;
        }
        if (testInvokeAuthorizerResult.getIsAuthenticated() != null && !testInvokeAuthorizerResult.getIsAuthenticated().equals(getIsAuthenticated())) {
            return false;
        }
        if ((testInvokeAuthorizerResult.getPrincipalId() == null) ^ (getPrincipalId() == null)) {
            return false;
        }
        if (testInvokeAuthorizerResult.getPrincipalId() != null && !testInvokeAuthorizerResult.getPrincipalId().equals(getPrincipalId())) {
            return false;
        }
        if ((testInvokeAuthorizerResult.getPolicyDocuments() == null) ^ (getPolicyDocuments() == null)) {
            return false;
        }
        if (testInvokeAuthorizerResult.getPolicyDocuments() != null && !testInvokeAuthorizerResult.getPolicyDocuments().equals(getPolicyDocuments())) {
            return false;
        }
        if ((testInvokeAuthorizerResult.getRefreshAfterInSeconds() == null) ^ (getRefreshAfterInSeconds() == null)) {
            return false;
        }
        if (testInvokeAuthorizerResult.getRefreshAfterInSeconds() != null && !testInvokeAuthorizerResult.getRefreshAfterInSeconds().equals(getRefreshAfterInSeconds())) {
            return false;
        }
        if ((testInvokeAuthorizerResult.getDisconnectAfterInSeconds() == null) ^ (getDisconnectAfterInSeconds() == null)) {
            return false;
        }
        return testInvokeAuthorizerResult.getDisconnectAfterInSeconds() == null || testInvokeAuthorizerResult.getDisconnectAfterInSeconds().equals(getDisconnectAfterInSeconds());
    }
}
