package org.apache.shiro.authc;

import java.util.Collection;
import java.util.HashSet;
import org.apache.shiro.subject.MutablePrincipalCollection;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

/* JADX INFO: loaded from: classes.dex */
public class SimpleAuthenticationInfo implements MergableAuthenticationInfo, SaltedAuthenticationInfo {
    protected Object credentials;
    protected ByteSource credentialsSalt;
    protected PrincipalCollection principals;

    public SimpleAuthenticationInfo() {
    }

    public SimpleAuthenticationInfo(Object obj, Object obj2, String str) {
        this.principals = new SimplePrincipalCollection(obj, str);
        this.credentials = obj2;
    }

    public SimpleAuthenticationInfo(Object obj, Object obj2, ByteSource byteSource, String str) {
        this.principals = new SimplePrincipalCollection(obj, str);
        this.credentials = obj2;
        this.credentialsSalt = byteSource;
    }

    public SimpleAuthenticationInfo(PrincipalCollection principalCollection, Object obj) {
        this.principals = new SimplePrincipalCollection(principalCollection);
        this.credentials = obj;
    }

    public SimpleAuthenticationInfo(PrincipalCollection principalCollection, Object obj, ByteSource byteSource) {
        this.principals = new SimplePrincipalCollection(principalCollection);
        this.credentials = obj;
        this.credentialsSalt = byteSource;
    }

    @Override // org.apache.shiro.authc.AuthenticationInfo
    public PrincipalCollection getPrincipals() {
        return this.principals;
    }

    public void setPrincipals(PrincipalCollection principalCollection) {
        this.principals = principalCollection;
    }

    @Override // org.apache.shiro.authc.AuthenticationInfo
    public Object getCredentials() {
        return this.credentials;
    }

    public void setCredentials(Object obj) {
        this.credentials = obj;
    }

    @Override // org.apache.shiro.authc.SaltedAuthenticationInfo
    public ByteSource getCredentialsSalt() {
        return this.credentialsSalt;
    }

    public void setCredentialsSalt(ByteSource byteSource) {
        this.credentialsSalt = byteSource;
    }

    @Override // org.apache.shiro.authc.MergableAuthenticationInfo
    public void merge(AuthenticationInfo authenticationInfo) {
        if (authenticationInfo == null || authenticationInfo.getPrincipals() == null || authenticationInfo.getPrincipals().isEmpty()) {
            return;
        }
        PrincipalCollection principalCollection = this.principals;
        if (principalCollection == null) {
            this.principals = authenticationInfo.getPrincipals();
        } else {
            if (!(principalCollection instanceof MutablePrincipalCollection)) {
                this.principals = new SimplePrincipalCollection(this.principals);
            }
            ((MutablePrincipalCollection) this.principals).addAll(authenticationInfo.getPrincipals());
        }
        if (this.credentialsSalt == null && (authenticationInfo instanceof SaltedAuthenticationInfo)) {
            this.credentialsSalt = ((SaltedAuthenticationInfo) authenticationInfo).getCredentialsSalt();
        }
        Object credentials = getCredentials();
        Object credentials2 = authenticationInfo.getCredentials();
        if (credentials2 == null) {
            return;
        }
        if (credentials == null) {
            this.credentials = credentials2;
            return;
        }
        if (!(credentials instanceof Collection)) {
            HashSet hashSet = new HashSet();
            hashSet.add(credentials);
            setCredentials(hashSet);
        }
        Collection collection = (Collection) getCredentials();
        if (credentials2 instanceof Collection) {
            collection.addAll((Collection) credentials2);
        } else {
            collection.add(credentials2);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SimpleAuthenticationInfo)) {
            return false;
        }
        PrincipalCollection principalCollection = this.principals;
        PrincipalCollection principalCollection2 = ((SimpleAuthenticationInfo) obj).principals;
        return principalCollection == null ? principalCollection2 == null : principalCollection.equals(principalCollection2);
    }

    public int hashCode() {
        PrincipalCollection principalCollection = this.principals;
        if (principalCollection != null) {
            return principalCollection.hashCode();
        }
        return 0;
    }

    public String toString() {
        return this.principals.toString();
    }
}
