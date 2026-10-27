package org.bouncycastle.jcajce.provider.asymmetric.util;

import g.a.b.e;
import g.a.b.f;
import g.a.c.e.p;

/* JADX INFO: loaded from: classes.dex */
public class IESUtil {
    public static p guessParameterSpec(f fVar, byte[] bArr) {
        if (fVar == null) {
            return new p(null, null, 128);
        }
        e eVarD = fVar.d();
        return (eVarD.getAlgorithmName().equals("DES") || eVarD.getAlgorithmName().equals("RC2") || eVarD.getAlgorithmName().equals("RC5-32") || eVarD.getAlgorithmName().equals("RC5-64")) ? new p(null, null, 64, 64, bArr) : eVarD.getAlgorithmName().equals("SKIPJACK") ? new p(null, null, 80, 80, bArr) : eVarD.getAlgorithmName().equals("GOST28147") ? new p(null, null, 256, 256, bArr) : new p(null, null, 128, 128, bArr);
    }
}
