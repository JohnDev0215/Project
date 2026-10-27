package org.bouncycastle.jcajce.util;

import g.a.a.e;
import g.a.a.q;
import java.io.IOException;
import java.security.AlgorithmParameters;

/* JADX INFO: loaded from: classes.dex */
public class AlgorithmParametersUtils {
    private AlgorithmParametersUtils() {
    }

    public static e extractParameters(AlgorithmParameters algorithmParameters) throws IOException {
        try {
            return q.j(algorithmParameters.getEncoded("ASN.1"));
        } catch (Exception unused) {
            return q.j(algorithmParameters.getEncoded());
        }
    }

    public static void loadParameters(AlgorithmParameters algorithmParameters, e eVar) throws IOException {
        try {
            algorithmParameters.init(eVar.c().e(), "ASN.1");
        } catch (Exception unused) {
            algorithmParameters.init(eVar.c().e());
        }
    }
}
