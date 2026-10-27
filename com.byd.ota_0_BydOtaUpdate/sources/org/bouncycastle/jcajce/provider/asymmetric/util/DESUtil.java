package org.bouncycastle.jcajce.provider.asymmetric.util;

import g.a.a.m;
import g.a.a.o2.b;
import g.a.a.p2.n;
import g.a.f.i;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class DESUtil {
    private static final Set<String> des;

    static {
        HashSet hashSet = new HashSet();
        des = hashSet;
        hashSet.add("DES");
        hashSet.add("DESEDE");
        hashSet.add(b.f3944e.r());
        m mVar = n.J;
        hashSet.add(mVar.r());
        hashSet.add(mVar.r());
        hashSet.add(n.Q1.r());
    }

    public static boolean isDES(String str) {
        return des.contains(i.k(str));
    }

    public static void setOddParity(byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b2 = bArr[i2];
            bArr[i2] = (byte) (((((b2 >> 7) ^ ((((((b2 >> 1) ^ (b2 >> 2)) ^ (b2 >> 3)) ^ (b2 >> 4)) ^ (b2 >> 5)) ^ (b2 >> 6))) ^ 1) & 1) | (b2 & 254));
        }
    }
}
