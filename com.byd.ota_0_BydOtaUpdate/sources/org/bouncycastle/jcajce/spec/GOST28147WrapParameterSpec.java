package org.bouncycastle.jcajce.spec;

import g.a.a.d2.a;
import g.a.a.m;
import g.a.b.d0.u;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class GOST28147WrapParameterSpec implements AlgorithmParameterSpec {
    private static Map oidMappings;
    private byte[] sBox;
    private byte[] ukm;

    static {
        HashMap map = new HashMap();
        oidMappings = map;
        map.put(a.f3800h, "E-A");
        oidMappings.put(a.f3801i, "E-B");
        oidMappings.put(a.j, "E-C");
        oidMappings.put(a.k, "E-D");
        oidMappings.put(g.a.a.q2.a.t, "Param-Z");
    }

    public GOST28147WrapParameterSpec(m mVar, byte[] bArr) {
        this(getName(mVar));
        this.ukm = g.a.f.a.f(bArr);
    }

    public GOST28147WrapParameterSpec(String str) {
        this.ukm = null;
        this.sBox = null;
        this.sBox = u.h(str);
    }

    public GOST28147WrapParameterSpec(String str, byte[] bArr) {
        this(str);
        byte[] bArr2 = new byte[bArr.length];
        this.ukm = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
    }

    public GOST28147WrapParameterSpec(byte[] bArr) {
        this.ukm = null;
        this.sBox = null;
        byte[] bArr2 = new byte[bArr.length];
        this.sBox = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
    }

    public GOST28147WrapParameterSpec(byte[] bArr, byte[] bArr2) {
        this(bArr);
        byte[] bArr3 = new byte[bArr2.length];
        this.ukm = bArr3;
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
    }

    private static String getName(m mVar) {
        String str = (String) oidMappings.get(mVar);
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("unknown OID: " + mVar);
    }

    public byte[] getSBox() {
        return g.a.f.a.f(this.sBox);
    }

    public byte[] getUKM() {
        return g.a.f.a.f(this.ukm);
    }
}
