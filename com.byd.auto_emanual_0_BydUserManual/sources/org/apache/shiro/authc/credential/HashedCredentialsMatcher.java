package org.apache.shiro.authc.credential;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.AbstractHash;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public class HashedCredentialsMatcher extends SimpleCredentialsMatcher {
    private String hashAlgorithm;
    private int hashIterations;
    private boolean hashSalted;
    private boolean storedCredentialsHexEncoded;

    public HashedCredentialsMatcher() {
        this.hashAlgorithm = null;
        this.hashSalted = false;
        this.hashIterations = 1;
        this.storedCredentialsHexEncoded = true;
    }

    public HashedCredentialsMatcher(String str) {
        this();
        if (!StringUtils.hasText(str)) {
            throw new IllegalArgumentException("hashAlgorithmName cannot be null or empty.");
        }
        this.hashAlgorithm = str;
    }

    public String getHashAlgorithmName() {
        return this.hashAlgorithm;
    }

    public void setHashAlgorithmName(String str) {
        this.hashAlgorithm = str;
    }

    public boolean isStoredCredentialsHexEncoded() {
        return this.storedCredentialsHexEncoded;
    }

    public void setStoredCredentialsHexEncoded(boolean z) {
        this.storedCredentialsHexEncoded = z;
    }

    @Deprecated
    public boolean isHashSalted() {
        return this.hashSalted;
    }

    @Deprecated
    public void setHashSalted(boolean z) {
        this.hashSalted = z;
    }

    public int getHashIterations() {
        return this.hashIterations;
    }

    public void setHashIterations(int i) {
        if (i < 1) {
            this.hashIterations = 1;
        } else {
            this.hashIterations = i;
        }
    }

    @Deprecated
    protected Object getSalt(AuthenticationToken authenticationToken) {
        return authenticationToken.getPrincipal();
    }

    @Override // org.apache.shiro.authc.credential.SimpleCredentialsMatcher
    protected Object getCredentials(AuthenticationInfo authenticationInfo) {
        Object credentials = authenticationInfo.getCredentials();
        byte[] bytes = toBytes(credentials);
        if ((credentials instanceof String) || (credentials instanceof char[])) {
            if (isStoredCredentialsHexEncoded()) {
                bytes = Hex.decode(bytes);
            } else {
                bytes = Base64.decode(bytes);
            }
        }
        AbstractHash abstractHashNewHashInstance = newHashInstance();
        abstractHashNewHashInstance.setBytes(bytes);
        return abstractHashNewHashInstance;
    }

    @Override // org.apache.shiro.authc.credential.SimpleCredentialsMatcher, org.apache.shiro.authc.credential.CredentialsMatcher
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        return equals(hashProvidedCredentials(authenticationToken, authenticationInfo), getCredentials(authenticationInfo));
    }

    protected Object hashProvidedCredentials(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        Object salt;
        if (authenticationInfo instanceof SaltedAuthenticationInfo) {
            salt = ((SaltedAuthenticationInfo) authenticationInfo).getCredentialsSalt();
        } else {
            salt = isHashSalted() ? getSalt(authenticationToken) : null;
        }
        return hashProvidedCredentials(authenticationToken.getCredentials(), salt, getHashIterations());
    }

    private String assertHashAlgorithmName() throws IllegalStateException {
        String hashAlgorithmName = getHashAlgorithmName();
        if (hashAlgorithmName != null) {
            return hashAlgorithmName;
        }
        throw new IllegalStateException("Required 'hashAlgorithmName' property has not been set.  This is required to execute the hashing algorithm.");
    }

    protected Hash hashProvidedCredentials(Object obj, Object obj2, int i) {
        return new SimpleHash(assertHashAlgorithmName(), obj, obj2, i);
    }

    protected AbstractHash newHashInstance() {
        return new SimpleHash(assertHashAlgorithmName());
    }
}
