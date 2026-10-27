package org.chromium.net;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class AndroidCertVerifyResult {
    public final int a;
    public final boolean b;
    public final List<X509Certificate> c;

    public AndroidCertVerifyResult(List list, boolean z) {
        this.a = 0;
        this.b = z;
        this.c = new ArrayList(list);
    }

    @CalledByNative
    public byte[][] getCertificateChainEncoded() {
        List<X509Certificate> list = this.c;
        byte[][] bArr = new byte[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            try {
                bArr[i] = list.get(i).getEncoded();
            } catch (CertificateEncodingException unused) {
                return new byte[0][];
            }
        }
        return bArr;
    }

    @CalledByNative
    public int getStatus() {
        return this.a;
    }

    @CalledByNative
    public boolean isIssuedByKnownRoot() {
        return this.b;
    }

    public AndroidCertVerifyResult(int i) {
        this.a = i;
        this.b = false;
        this.c = Collections.emptyList();
    }
}
