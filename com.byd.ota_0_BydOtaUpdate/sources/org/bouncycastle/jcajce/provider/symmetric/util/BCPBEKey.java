package org.bouncycastle.jcajce.provider.symmetric.util;

import g.a.a.m;
import g.a.b.i;
import g.a.b.j0.s0;
import g.a.b.j0.w0;
import g.a.b.u;
import java.security.spec.KeySpec;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class BCPBEKey implements PBEKey {
    public String algorithm;
    public int digest;
    public int ivSize;
    public int keySize;
    public m oid;
    public i param;
    public PBEKeySpec pbeKeySpec;
    public boolean tryWrong = false;
    public int type;

    public BCPBEKey(String str, m mVar, int i2, int i3, int i4, int i5, PBEKeySpec pBEKeySpec, i iVar) {
        this.algorithm = str;
        this.oid = mVar;
        this.type = i2;
        this.digest = i3;
        this.keySize = i4;
        this.ivSize = i5;
        this.pbeKeySpec = pBEKeySpec;
        this.param = iVar;
    }

    public BCPBEKey(String str, KeySpec keySpec, i iVar) {
        this.algorithm = str;
        this.param = iVar;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.algorithm;
    }

    public int getDigest() {
        return this.digest;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        i iVarB = this.param;
        if (iVarB == null) {
            int i2 = this.type;
            return i2 == 2 ? u.a(this.pbeKeySpec.getPassword()) : i2 == 5 ? u.c(this.pbeKeySpec.getPassword()) : u.b(this.pbeKeySpec.getPassword());
        }
        if (iVarB instanceof w0) {
            iVarB = ((w0) iVarB).b();
        }
        return ((s0) iVarB).a();
    }

    @Override // java.security.Key
    public String getFormat() {
        return "RAW";
    }

    @Override // javax.crypto.interfaces.PBEKey
    public int getIterationCount() {
        return this.pbeKeySpec.getIterationCount();
    }

    public int getIvSize() {
        return this.ivSize;
    }

    public int getKeySize() {
        return this.keySize;
    }

    public m getOID() {
        return this.oid;
    }

    public i getParam() {
        return this.param;
    }

    @Override // javax.crypto.interfaces.PBEKey
    public char[] getPassword() {
        return this.pbeKeySpec.getPassword();
    }

    @Override // javax.crypto.interfaces.PBEKey
    public byte[] getSalt() {
        return this.pbeKeySpec.getSalt();
    }

    public int getType() {
        return this.type;
    }

    public void setTryWrongPKCS12Zero(boolean z) {
        this.tryWrong = z;
    }

    public boolean shouldTryWrongPKCS12() {
        return this.tryWrong;
    }
}
