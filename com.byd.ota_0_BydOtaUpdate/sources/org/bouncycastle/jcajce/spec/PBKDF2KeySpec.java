package org.bouncycastle.jcajce.spec;

import g.a.a.p2.n;
import g.a.a.w0;
import g.a.a.w2.a;
import javax.crypto.spec.PBEKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class PBKDF2KeySpec extends PBEKeySpec {
    private static final a defaultPRF = new a(n.Q, w0.f4105c);
    private a prf;

    public PBKDF2KeySpec(char[] cArr, byte[] bArr, int i2, int i3, a aVar) {
        super(cArr, bArr, i2, i3);
        this.prf = aVar;
    }

    public a getPrf() {
        return this.prf;
    }

    public boolean isDefaultPrf() {
        return defaultPRF.equals(this.prf);
    }
}
