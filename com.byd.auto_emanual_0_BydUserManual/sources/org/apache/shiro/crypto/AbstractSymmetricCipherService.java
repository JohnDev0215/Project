package org.apache.shiro.crypto;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractSymmetricCipherService extends JcaCipherService {
    protected AbstractSymmetricCipherService(String str) {
        super(str);
    }

    public Key generateNewKey() {
        return generateNewKey(getKeySize());
    }

    public Key generateNewKey(int i) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(getAlgorithmName());
            keyGenerator.init(i);
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Unable to acquire " + getAlgorithmName() + " algorithm.  This is required to function.", e);
        }
    }
}
