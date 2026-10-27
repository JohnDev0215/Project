package org.bouncycastle.jcajce.util;

import g.a.a.d2.a;
import g.a.a.e;
import g.a.a.m;
import g.a.a.o2.b;
import g.a.a.p2.n;
import g.a.a.q;
import java.io.IOException;
import java.security.AlgorithmParameters;

/* JADX INFO: loaded from: classes.dex */
public class JcaJceUtils {
    private JcaJceUtils() {
    }

    public static e extractParameters(AlgorithmParameters algorithmParameters) throws IOException {
        try {
            return q.j(algorithmParameters.getEncoded("ASN.1"));
        } catch (Exception unused) {
            return q.j(algorithmParameters.getEncoded());
        }
    }

    public static String getDigestAlgName(m mVar) {
        return n.P.equals(mVar) ? "MD5" : b.f3948i.equals(mVar) ? "SHA1" : g.a.a.l2.b.f3912f.equals(mVar) ? "SHA224" : g.a.a.l2.b.f3909c.equals(mVar) ? "SHA256" : g.a.a.l2.b.f3910d.equals(mVar) ? "SHA384" : g.a.a.l2.b.f3911e.equals(mVar) ? "SHA512" : g.a.a.s2.b.f4045c.equals(mVar) ? "RIPEMD128" : g.a.a.s2.b.f4044b.equals(mVar) ? "RIPEMD160" : g.a.a.s2.b.f4046d.equals(mVar) ? "RIPEMD256" : a.f3794b.equals(mVar) ? "GOST3411" : mVar.r();
    }

    public static void loadParameters(AlgorithmParameters algorithmParameters, e eVar) throws IOException {
        try {
            algorithmParameters.init(eVar.c().e(), "ASN.1");
        } catch (Exception unused) {
            algorithmParameters.init(eVar.c().e());
        }
    }
}
