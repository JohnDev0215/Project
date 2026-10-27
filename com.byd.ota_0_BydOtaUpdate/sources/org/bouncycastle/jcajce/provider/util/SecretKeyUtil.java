package org.bouncycastle.jcajce.provider.util;

import g.a.a.l2.b;
import g.a.a.m;
import g.a.a.n2.a;
import g.a.a.p2.n;
import g.a.f.d;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class SecretKeyUtil {
    private static Map keySizes;

    static {
        HashMap map = new HashMap();
        keySizes = map;
        map.put(n.J.r(), d.b(192));
        keySizes.put(b.u, d.b(128));
        keySizes.put(b.C, d.b(192));
        keySizes.put(b.K, d.b(256));
        keySizes.put(a.f3931a, d.b(128));
        keySizes.put(a.f3932b, d.b(192));
        keySizes.put(a.f3933c, d.b(256));
    }

    public static int getKeySize(m mVar) {
        Integer num = (Integer) keySizes.get(mVar);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }
}
