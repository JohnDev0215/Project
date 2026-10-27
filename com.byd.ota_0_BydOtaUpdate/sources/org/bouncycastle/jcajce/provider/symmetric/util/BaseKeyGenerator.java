package org.bouncycastle.jcajce.provider.symmetric.util;

import g.a.b.h;
import g.a.b.j;
import g.a.b.r;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.KeyGeneratorSpi;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class BaseKeyGenerator extends KeyGeneratorSpi {
    public String algName;
    public int defaultKeySize;
    public h engine;
    public int keySize;
    public boolean uninitialised = true;

    public BaseKeyGenerator(String str, int i2, h hVar) {
        this.algName = str;
        this.defaultKeySize = i2;
        this.keySize = i2;
        this.engine = hVar;
    }

    @Override // javax.crypto.KeyGeneratorSpi
    public SecretKey engineGenerateKey() {
        if (this.uninitialised) {
            this.engine.b(new r(j.b(), this.defaultKeySize));
            this.uninitialised = false;
        }
        return new SecretKeySpec(this.engine.a(), this.algName);
    }

    @Override // javax.crypto.KeyGeneratorSpi
    public void engineInit(int i2, SecureRandom secureRandom) {
        if (secureRandom == null) {
            try {
                secureRandom = j.b();
            } catch (IllegalArgumentException e2) {
                throw new InvalidParameterException(e2.getMessage());
            }
        }
        this.engine.b(new r(secureRandom, i2));
        this.uninitialised = false;
    }

    @Override // javax.crypto.KeyGeneratorSpi
    public void engineInit(SecureRandom secureRandom) {
        if (secureRandom != null) {
            this.engine.b(new r(secureRandom, this.defaultKeySize));
            this.uninitialised = false;
        }
    }

    @Override // javax.crypto.KeyGeneratorSpi
    public void engineInit(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException("Not Implemented");
    }
}
