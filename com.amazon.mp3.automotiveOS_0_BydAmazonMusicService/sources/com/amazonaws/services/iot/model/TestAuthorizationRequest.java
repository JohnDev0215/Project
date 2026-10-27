package com.amazonaws.services.iot.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class TestAuthorizationRequest extends AmazonWebServiceRequest implements Serializable {
    private List<AuthInfo> authInfos;
    private String clientId;
    private String cognitoIdentityPoolId;
    private List<String> policyNamesToAdd;
    private List<String> policyNamesToSkip;
    private String principal;

    public String getPrincipal() {
        return this.principal;
    }

    public void setPrincipal(String str) {
        this.principal = str;
    }

    public TestAuthorizationRequest withPrincipal(String str) {
        this.principal = str;
        return this;
    }

    public String getCognitoIdentityPoolId() {
        return this.cognitoIdentityPoolId;
    }

    public void setCognitoIdentityPoolId(String str) {
        this.cognitoIdentityPoolId = str;
    }

    public TestAuthorizationRequest withCognitoIdentityPoolId(String str) {
        this.cognitoIdentityPoolId = str;
        return this;
    }

    public List<AuthInfo> getAuthInfos() {
        return this.authInfos;
    }

    public void setAuthInfos(Collection<AuthInfo> collection) {
        if (collection == null) {
            this.authInfos = null;
        } else {
            this.authInfos = new ArrayList(collection);
        }
    }

    public TestAuthorizationRequest withAuthInfos(AuthInfo... authInfoArr) {
        if (getAuthInfos() == null) {
            this.authInfos = new ArrayList(authInfoArr.length);
        }
        for (AuthInfo authInfo : authInfoArr) {
            this.authInfos.add(authInfo);
        }
        return this;
    }

    public TestAuthorizationRequest withAuthInfos(Collection<AuthInfo> collection) {
        setAuthInfos(collection);
        return this;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public TestAuthorizationRequest withClientId(String str) {
        this.clientId = str;
        return this;
    }

    public List<String> getPolicyNamesToAdd() {
        return this.policyNamesToAdd;
    }

    public void setPolicyNamesToAdd(Collection<String> collection) {
        if (collection == null) {
            this.policyNamesToAdd = null;
        } else {
            this.policyNamesToAdd = new ArrayList(collection);
        }
    }

    public TestAuthorizationRequest withPolicyNamesToAdd(String... strArr) {
        if (getPolicyNamesToAdd() == null) {
            this.policyNamesToAdd = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.policyNamesToAdd.add(str);
        }
        return this;
    }

    public TestAuthorizationRequest withPolicyNamesToAdd(Collection<String> collection) {
        setPolicyNamesToAdd(collection);
        return this;
    }

    public List<String> getPolicyNamesToSkip() {
        return this.policyNamesToSkip;
    }

    public void setPolicyNamesToSkip(Collection<String> collection) {
        if (collection == null) {
            this.policyNamesToSkip = null;
        } else {
            this.policyNamesToSkip = new ArrayList(collection);
        }
    }

    public TestAuthorizationRequest withPolicyNamesToSkip(String... strArr) {
        if (getPolicyNamesToSkip() == null) {
            this.policyNamesToSkip = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.policyNamesToSkip.add(str);
        }
        return this;
    }

    public TestAuthorizationRequest withPolicyNamesToSkip(Collection<String> collection) {
        setPolicyNamesToSkip(collection);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPrincipal() != null) {
            sb.append("principal: " + getPrincipal() + ",");
        }
        if (getCognitoIdentityPoolId() != null) {
            sb.append("cognitoIdentityPoolId: " + getCognitoIdentityPoolId() + ",");
        }
        if (getAuthInfos() != null) {
            sb.append("authInfos: " + getAuthInfos() + ",");
        }
        if (getClientId() != null) {
            sb.append("clientId: " + getClientId() + ",");
        }
        if (getPolicyNamesToAdd() != null) {
            sb.append("policyNamesToAdd: " + getPolicyNamesToAdd() + ",");
        }
        if (getPolicyNamesToSkip() != null) {
            sb.append("policyNamesToSkip: " + getPolicyNamesToSkip());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (((((((((((getPrincipal() == null ? 0 : getPrincipal().hashCode()) + 31) * 31) + (getCognitoIdentityPoolId() == null ? 0 : getCognitoIdentityPoolId().hashCode())) * 31) + (getAuthInfos() == null ? 0 : getAuthInfos().hashCode())) * 31) + (getClientId() == null ? 0 : getClientId().hashCode())) * 31) + (getPolicyNamesToAdd() == null ? 0 : getPolicyNamesToAdd().hashCode())) * 31) + (getPolicyNamesToSkip() != null ? getPolicyNamesToSkip().hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TestAuthorizationRequest)) {
            return false;
        }
        TestAuthorizationRequest testAuthorizationRequest = (TestAuthorizationRequest) obj;
        if ((testAuthorizationRequest.getPrincipal() == null) ^ (getPrincipal() == null)) {
            return false;
        }
        if (testAuthorizationRequest.getPrincipal() != null && !testAuthorizationRequest.getPrincipal().equals(getPrincipal())) {
            return false;
        }
        if ((testAuthorizationRequest.getCognitoIdentityPoolId() == null) ^ (getCognitoIdentityPoolId() == null)) {
            return false;
        }
        if (testAuthorizationRequest.getCognitoIdentityPoolId() != null && !testAuthorizationRequest.getCognitoIdentityPoolId().equals(getCognitoIdentityPoolId())) {
            return false;
        }
        if ((testAuthorizationRequest.getAuthInfos() == null) ^ (getAuthInfos() == null)) {
            return false;
        }
        if (testAuthorizationRequest.getAuthInfos() != null && !testAuthorizationRequest.getAuthInfos().equals(getAuthInfos())) {
            return false;
        }
        if ((testAuthorizationRequest.getClientId() == null) ^ (getClientId() == null)) {
            return false;
        }
        if (testAuthorizationRequest.getClientId() != null && !testAuthorizationRequest.getClientId().equals(getClientId())) {
            return false;
        }
        if ((testAuthorizationRequest.getPolicyNamesToAdd() == null) ^ (getPolicyNamesToAdd() == null)) {
            return false;
        }
        if (testAuthorizationRequest.getPolicyNamesToAdd() != null && !testAuthorizationRequest.getPolicyNamesToAdd().equals(getPolicyNamesToAdd())) {
            return false;
        }
        if ((testAuthorizationRequest.getPolicyNamesToSkip() == null) ^ (getPolicyNamesToSkip() == null)) {
            return false;
        }
        return testAuthorizationRequest.getPolicyNamesToSkip() == null || testAuthorizationRequest.getPolicyNamesToSkip().equals(getPolicyNamesToSkip());
    }
}
