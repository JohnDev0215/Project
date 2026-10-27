package org.bouncycastle.jcajce.spec;

import g.a.f.a;
import java.security.spec.KeySpec;

/* JADX INFO: loaded from: classes.dex */
public class ScryptKeySpec implements KeySpec {
    private final int blockSize;
    private final int costParameter;
    private final int keySize;
    private final int parallelizationParameter;
    private final char[] password;
    private final byte[] salt;

    public ScryptKeySpec(char[] cArr, byte[] bArr, int i2, int i3, int i4, int i5) {
        this.password = cArr;
        this.salt = a.f(bArr);
        this.costParameter = i2;
        this.blockSize = i3;
        this.parallelizationParameter = i4;
        this.keySize = i5;
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public int getCostParameter() {
        return this.costParameter;
    }

    public int getKeyLength() {
        return this.keySize;
    }

    public int getParallelizationParameter() {
        return this.parallelizationParameter;
    }

    public char[] getPassword() {
        return this.password;
    }

    public byte[] getSalt() {
        return a.f(this.salt);
    }
}
