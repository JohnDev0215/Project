package org.bouncycastle.jcajce.util;

import g.a.a.i2.a;
import g.a.a.m;
import g.a.a.o2.b;
import g.a.a.p2.n;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class MessageDigestUtils {
    private static Map<m, String> digestOidMap;

    static {
        HashMap map = new HashMap();
        digestOidMap = map;
        map.put(n.N, "MD2");
        digestOidMap.put(n.O, "MD4");
        digestOidMap.put(n.P, "MD5");
        digestOidMap.put(b.f3948i, "SHA-1");
        digestOidMap.put(g.a.a.l2.b.f3912f, "SHA-224");
        digestOidMap.put(g.a.a.l2.b.f3909c, "SHA-256");
        digestOidMap.put(g.a.a.l2.b.f3910d, "SHA-384");
        digestOidMap.put(g.a.a.l2.b.f3911e, "SHA-512");
        digestOidMap.put(g.a.a.s2.b.f4045c, "RIPEMD-128");
        digestOidMap.put(g.a.a.s2.b.f4044b, "RIPEMD-160");
        digestOidMap.put(g.a.a.s2.b.f4046d, "RIPEMD-128");
        digestOidMap.put(a.f3872d, "RIPEMD-128");
        digestOidMap.put(a.f3871c, "RIPEMD-160");
        digestOidMap.put(g.a.a.d2.a.f3794b, "GOST3411");
        digestOidMap.put(g.a.a.g2.a.f3852g, "Tiger");
        digestOidMap.put(a.f3873e, "Whirlpool");
        digestOidMap.put(g.a.a.l2.b.f3915i, "SHA3-224");
        digestOidMap.put(g.a.a.l2.b.j, "SHA3-256");
        digestOidMap.put(g.a.a.l2.b.k, "SHA3-384");
        digestOidMap.put(g.a.a.l2.b.l, "SHA3-512");
        digestOidMap.put(g.a.a.f2.b.b0, "SM3");
    }

    public static String getDigestName(m mVar) {
        String str = digestOidMap.get(mVar);
        return str != null ? str : mVar.r();
    }
}
