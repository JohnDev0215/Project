package org.bouncycastle.jcajce.spec;

import g.a.f.a;
import javax.crypto.spec.IvParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class AEADParameterSpec extends IvParameterSpec {
    private final byte[] associatedData;
    private final int macSizeInBits;

    public AEADParameterSpec(byte[] bArr, int i2) {
        this(bArr, i2, null);
    }

    public AEADParameterSpec(byte[] bArr, int i2, byte[] bArr2) {
        super(bArr);
        this.macSizeInBits = i2;
        this.associatedData = a.f(bArr2);
    }

    public byte[] getAssociatedData() {
        return a.f(this.associatedData);
    }

    public int getMacSizeInBits() {
        return this.macSizeInBits;
    }

    public byte[] getNonce() {
        return getIV();
    }
}
