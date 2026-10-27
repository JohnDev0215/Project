package org.apache.shiro.mgt;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.CipherService;
import org.apache.shiro.io.DefaultSerializer;
import org.apache.shiro.io.Serializer;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractRememberMeManager implements RememberMeManager {
    private static final Logger log = LoggerFactory.getLogger(AbstractRememberMeManager.class);
    private CipherService cipherService;
    private byte[] decryptionCipherKey;
    private byte[] encryptionCipherKey;
    private Serializer<PrincipalCollection> serializer = new DefaultSerializer();

    protected abstract void forgetIdentity(Subject subject);

    protected abstract byte[] getRememberedSerializedIdentity(SubjectContext subjectContext);

    protected abstract void rememberSerializedIdentity(Subject subject, byte[] bArr);

    public AbstractRememberMeManager() {
        AesCipherService aesCipherService = new AesCipherService();
        this.cipherService = aesCipherService;
        setCipherKey(aesCipherService.generateNewKey().getEncoded());
    }

    public Serializer<PrincipalCollection> getSerializer() {
        return this.serializer;
    }

    public void setSerializer(Serializer<PrincipalCollection> serializer) {
        this.serializer = serializer;
    }

    public CipherService getCipherService() {
        return this.cipherService;
    }

    public void setCipherService(CipherService cipherService) {
        this.cipherService = cipherService;
    }

    public byte[] getEncryptionCipherKey() {
        return this.encryptionCipherKey;
    }

    public void setEncryptionCipherKey(byte[] bArr) {
        this.encryptionCipherKey = bArr;
    }

    public byte[] getDecryptionCipherKey() {
        return this.decryptionCipherKey;
    }

    public void setDecryptionCipherKey(byte[] bArr) {
        this.decryptionCipherKey = bArr;
    }

    public byte[] getCipherKey() {
        return getEncryptionCipherKey();
    }

    public void setCipherKey(byte[] bArr) {
        setEncryptionCipherKey(bArr);
        setDecryptionCipherKey(bArr);
    }

    protected boolean isRememberMe(AuthenticationToken authenticationToken) {
        return authenticationToken != null && (authenticationToken instanceof RememberMeAuthenticationToken) && ((RememberMeAuthenticationToken) authenticationToken).isRememberMe();
    }

    @Override // org.apache.shiro.mgt.RememberMeManager
    public void onSuccessfulLogin(Subject subject, AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        forgetIdentity(subject);
        if (isRememberMe(authenticationToken)) {
            rememberIdentity(subject, authenticationToken, authenticationInfo);
            return;
        }
        Logger logger = log;
        if (logger.isDebugEnabled()) {
            logger.debug("AuthenticationToken did not indicate RememberMe is requested.  RememberMe functionality will not be executed for corresponding account.");
        }
    }

    public void rememberIdentity(Subject subject, AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        rememberIdentity(subject, getIdentityToRemember(subject, authenticationInfo));
    }

    protected PrincipalCollection getIdentityToRemember(Subject subject, AuthenticationInfo authenticationInfo) {
        return authenticationInfo.getPrincipals();
    }

    protected void rememberIdentity(Subject subject, PrincipalCollection principalCollection) {
        rememberSerializedIdentity(subject, convertPrincipalsToBytes(principalCollection));
    }

    protected byte[] convertPrincipalsToBytes(PrincipalCollection principalCollection) {
        byte[] bArrSerialize = serialize(principalCollection);
        return getCipherService() != null ? encrypt(bArrSerialize) : bArrSerialize;
    }

    @Override // org.apache.shiro.mgt.RememberMeManager
    public PrincipalCollection getRememberedPrincipals(SubjectContext subjectContext) {
        try {
            byte[] rememberedSerializedIdentity = getRememberedSerializedIdentity(subjectContext);
            if (rememberedSerializedIdentity == null || rememberedSerializedIdentity.length <= 0) {
                return null;
            }
            return convertBytesToPrincipals(rememberedSerializedIdentity, subjectContext);
        } catch (RuntimeException e) {
            return onRememberedPrincipalFailure(e, subjectContext);
        }
    }

    protected PrincipalCollection convertBytesToPrincipals(byte[] bArr, SubjectContext subjectContext) {
        if (getCipherService() != null) {
            bArr = decrypt(bArr);
        }
        return deserialize(bArr);
    }

    protected PrincipalCollection onRememberedPrincipalFailure(RuntimeException runtimeException, SubjectContext subjectContext) {
        Logger logger = log;
        if (logger.isWarnEnabled()) {
            logger.warn("There was a failure while trying to retrieve remembered principals.  This could be due to a configuration problem or corrupted principals.  This could also be due to a recently changed encryption key, if you are using a shiro.ini file, this property would be 'securityManager.rememberMeManager.cipherKey' see: http://shiro.apache.org/web.html#Web-RememberMeServices. The remembered identity will be forgotten and not used for this request.");
        }
        forgetIdentity(subjectContext);
        throw runtimeException;
    }

    protected byte[] encrypt(byte[] bArr) {
        CipherService cipherService = getCipherService();
        return cipherService != null ? cipherService.encrypt(bArr, getEncryptionCipherKey()).getBytes() : bArr;
    }

    protected byte[] decrypt(byte[] bArr) {
        CipherService cipherService = getCipherService();
        return cipherService != null ? cipherService.decrypt(bArr, getDecryptionCipherKey()).getBytes() : bArr;
    }

    protected byte[] serialize(PrincipalCollection principalCollection) {
        return getSerializer().serialize(principalCollection);
    }

    protected PrincipalCollection deserialize(byte[] bArr) {
        return getSerializer().deserialize(bArr);
    }

    @Override // org.apache.shiro.mgt.RememberMeManager
    public void onFailedLogin(Subject subject, AuthenticationToken authenticationToken, AuthenticationException authenticationException) {
        forgetIdentity(subject);
    }

    @Override // org.apache.shiro.mgt.RememberMeManager
    public void onLogout(Subject subject) {
        forgetIdentity(subject);
    }
}
