package org.chromium.net;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AndroidCertVerifyResult {
    public final int a;
    public final boolean b;
    public final List c;

    public AndroidCertVerifyResult(int i, boolean z, List list) {
        this.a = i;
        this.b = z;
        this.c = new ArrayList(list);
    }

    public byte[][] getCertificateChainEncoded() {
        byte[][] bArr = new byte[this.c.size()][];
        for (int i = 0; i < this.c.size(); i++) {
            try {
                bArr[i] = ((X509Certificate) this.c.get(i)).getEncoded();
            } catch (CertificateEncodingException unused) {
                return new byte[0][];
            }
        }
        return bArr;
    }

    public int getStatus() {
        return this.a;
    }

    public boolean isIssuedByKnownRoot() {
        return this.b;
    }

    public AndroidCertVerifyResult(int i) {
        this.a = i;
        this.b = false;
        this.c = Collections.emptyList();
    }
}
