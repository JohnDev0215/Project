package org.apache.shiro.authc.credential;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.crypto.hash.Hash;

/* JADX INFO: loaded from: classes.dex */
public class PasswordMatcher implements CredentialsMatcher {
    private PasswordService passwordService = new DefaultPasswordService();

    @Override // org.apache.shiro.authc.credential.CredentialsMatcher
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        PasswordService passwordServiceEnsurePasswordService = ensurePasswordService();
        Object submittedPassword = getSubmittedPassword(authenticationToken);
        Object storedPassword = getStoredPassword(authenticationInfo);
        assertStoredCredentialsType(storedPassword);
        if (storedPassword instanceof Hash) {
            return assertHashingPasswordService(passwordServiceEnsurePasswordService).passwordsMatch(submittedPassword, (Hash) storedPassword);
        }
        return this.passwordService.passwordsMatch(submittedPassword, (String) storedPassword);
    }

    private HashingPasswordService assertHashingPasswordService(PasswordService passwordService) {
        if (passwordService instanceof HashingPasswordService) {
            return (HashingPasswordService) passwordService;
        }
        throw new IllegalStateException("AuthenticationInfo's stored credentials are a Hash instance, but the configured passwordService is not a " + HashingPasswordService.class.getName() + " instance.  This is required to perform Hash object password comparisons.");
    }

    private PasswordService ensurePasswordService() {
        PasswordService passwordService = getPasswordService();
        if (passwordService != null) {
            return passwordService;
        }
        throw new IllegalStateException("Required PasswordService has not been configured.");
    }

    protected Object getSubmittedPassword(AuthenticationToken authenticationToken) {
        if (authenticationToken != null) {
            return authenticationToken.getCredentials();
        }
        return null;
    }

    private void assertStoredCredentialsType(Object obj) {
        if (!(obj instanceof String) && !(obj instanceof Hash)) {
            throw new IllegalArgumentException("Stored account credentials are expected to be either a " + Hash.class.getName() + " instance or a formatted hash String.");
        }
    }

    protected Object getStoredPassword(AuthenticationInfo authenticationInfo) {
        Object credentials = authenticationInfo != null ? authenticationInfo.getCredentials() : null;
        return credentials instanceof char[] ? new String((char[]) credentials) : credentials;
    }

    public PasswordService getPasswordService() {
        return this.passwordService;
    }

    public void setPasswordService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }
}
