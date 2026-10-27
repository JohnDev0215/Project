package org.apache.shiro.authc.credential;

import java.security.MessageDigest;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.HashService;
import org.apache.shiro.crypto.hash.format.DefaultHashFormatFactory;
import org.apache.shiro.crypto.hash.format.HashFormat;
import org.apache.shiro.crypto.hash.format.HashFormatFactory;
import org.apache.shiro.crypto.hash.format.ParsableHashFormat;
import org.apache.shiro.crypto.hash.format.Shiro1CryptFormat;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class DefaultPasswordService implements HashingPasswordService {
    public static final String DEFAULT_HASH_ALGORITHM = "SHA-256";
    public static final int DEFAULT_HASH_ITERATIONS = 500000;
    private static final Logger log = LoggerFactory.getLogger(DefaultPasswordService.class);
    private HashFormat hashFormat;
    private HashFormatFactory hashFormatFactory;
    private volatile boolean hashFormatWarned = false;
    private HashService hashService;

    public DefaultPasswordService() {
        DefaultHashService defaultHashService = new DefaultHashService();
        defaultHashService.setHashAlgorithmName("SHA-256");
        defaultHashService.setHashIterations(DEFAULT_HASH_ITERATIONS);
        defaultHashService.setGeneratePublicSalt(true);
        this.hashService = defaultHashService;
        this.hashFormat = new Shiro1CryptFormat();
        this.hashFormatFactory = new DefaultHashFormatFactory();
    }

    @Override // org.apache.shiro.authc.credential.PasswordService
    public String encryptPassword(Object obj) {
        Hash hashHashPassword = hashPassword(obj);
        checkHashFormatDurability();
        return this.hashFormat.format(hashHashPassword);
    }

    @Override // org.apache.shiro.authc.credential.HashingPasswordService
    public Hash hashPassword(Object obj) {
        ByteSource byteSourceCreateByteSource = createByteSource(obj);
        if (byteSourceCreateByteSource == null || byteSourceCreateByteSource.isEmpty()) {
            return null;
        }
        return this.hashService.computeHash(createHashRequest(byteSourceCreateByteSource));
    }

    @Override // org.apache.shiro.authc.credential.HashingPasswordService
    public boolean passwordsMatch(Object obj, Hash hash) {
        ByteSource byteSourceCreateByteSource = createByteSource(obj);
        if (hash == null || hash.isEmpty()) {
            return byteSourceCreateByteSource == null || byteSourceCreateByteSource.isEmpty();
        }
        if (byteSourceCreateByteSource == null || byteSourceCreateByteSource.isEmpty()) {
            return false;
        }
        return constantEquals(hash.toString(), this.hashService.computeHash(buildHashRequest(byteSourceCreateByteSource, hash)).toString());
    }

    private boolean constantEquals(String str, String str2) {
        return MessageDigest.isEqual(str.getBytes(), str2.getBytes());
    }

    protected void checkHashFormatDurability() {
        if (this.hashFormatWarned) {
            return;
        }
        HashFormat hashFormat = this.hashFormat;
        if (hashFormat instanceof ParsableHashFormat) {
            return;
        }
        Logger logger = log;
        if (logger.isWarnEnabled()) {
            logger.warn("The configured hashFormat instance [" + hashFormat.getClass().getName() + "] is not a " + ParsableHashFormat.class.getName() + " implementation.  This is required if you wish to support backwards compatibility for saved password checking (almost always desirable).  Without a " + ParsableHashFormat.class.getSimpleName() + " instance, any hashService configuration changes will break previously hashed/saved passwords.");
            this.hashFormatWarned = true;
        }
    }

    protected HashRequest createHashRequest(ByteSource byteSource) {
        return new HashRequest.Builder().setSource(byteSource).build();
    }

    protected ByteSource createByteSource(Object obj) {
        return ByteSource.Util.bytes(obj);
    }

    @Override // org.apache.shiro.authc.credential.PasswordService
    public boolean passwordsMatch(Object obj, String str) {
        ByteSource byteSourceCreateByteSource = createByteSource(obj);
        if (str == null || str.length() == 0) {
            return byteSourceCreateByteSource == null || byteSourceCreateByteSource.isEmpty();
        }
        if (byteSourceCreateByteSource == null || byteSourceCreateByteSource.isEmpty()) {
            return false;
        }
        HashFormat hashFormatFactory = this.hashFormatFactory.getInstance(str);
        if (hashFormatFactory != null && (hashFormatFactory instanceof ParsableHashFormat)) {
            return passwordsMatch(obj, ((ParsableHashFormat) hashFormatFactory).parse(str));
        }
        return constantEquals(str, this.hashFormat.format(this.hashService.computeHash(createHashRequest(byteSourceCreateByteSource))));
    }

    protected HashRequest buildHashRequest(ByteSource byteSource, Hash hash) {
        return new HashRequest.Builder().setSource(byteSource).setAlgorithmName(hash.getAlgorithmName()).setSalt(hash.getSalt()).setIterations(hash.getIterations()).build();
    }

    public HashService getHashService() {
        return this.hashService;
    }

    public void setHashService(HashService hashService) {
        this.hashService = hashService;
    }

    public HashFormat getHashFormat() {
        return this.hashFormat;
    }

    public void setHashFormat(HashFormat hashFormat) {
        this.hashFormat = hashFormat;
    }

    public HashFormatFactory getHashFormatFactory() {
        return this.hashFormatFactory;
    }

    public void setHashFormatFactory(HashFormatFactory hashFormatFactory) {
        this.hashFormatFactory = hashFormatFactory;
    }
}
