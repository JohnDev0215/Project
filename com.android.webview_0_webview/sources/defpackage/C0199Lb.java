package defpackage;

import android.util.Log;
import android.webkit.ClientCertRequest;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Objects;
import org.chromium.base.task.PostTask;

/* JADX INFO: renamed from: Lb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0199Lb extends ClientCertRequest {
    public final C1235no a;
    public final String[] b;
    public final Principal[] c;
    public final String d;
    public final int e;

    public C0199Lb(C1235no c1235no, String[] strArr, Principal[] principalArr, String str, int i) {
        this.a = c1235no;
        this.b = strArr;
        this.c = principalArr;
        this.d = str;
        this.e = i;
    }

    @Override // android.webkit.ClientCertRequest
    public void cancel() {
        final C1235no c1235no = this.a;
        Objects.requireNonNull(c1235no);
        PostTask.b(AbstractC0538bE.a, new Runnable(c1235no) { // from class: mo
            public final C1235no s;

            {
                this.s = c1235no;
            }

            @Override // java.lang.Runnable
            public void run() {
                C1235no c1235no2 = this.s;
                c1235no2.a();
                C0284Pp c0284Pp = c1235no2.e.d;
                String str = c1235no2.b;
                int i = c1235no2.c;
                Objects.requireNonNull(c0284Pp);
                String strA = C0284Pp.a(str, i);
                c0284Pp.a.remove(strA);
                c0284Pp.b.add(strA);
                c1235no2.b(null, null);
            }
        });
    }

    @Override // android.webkit.ClientCertRequest
    public String getHost() {
        return this.d;
    }

    @Override // android.webkit.ClientCertRequest
    public String[] getKeyTypes() {
        return this.b;
    }

    @Override // android.webkit.ClientCertRequest
    public int getPort() {
        return this.e;
    }

    @Override // android.webkit.ClientCertRequest
    public Principal[] getPrincipals() {
        return this.c;
    }

    @Override // android.webkit.ClientCertRequest
    public void ignore() {
        final C1235no c1235no = this.a;
        Objects.requireNonNull(c1235no);
        PostTask.b(AbstractC0538bE.a, new Runnable(c1235no) { // from class: lo
            public final C1235no s;

            {
                this.s = c1235no;
            }

            @Override // java.lang.Runnable
            public void run() {
                C1235no c1235no2 = this.s;
                c1235no2.a();
                c1235no2.b(null, null);
            }
        });
    }

    @Override // android.webkit.ClientCertRequest
    public void proceed(final PrivateKey privateKey, final X509Certificate[] x509CertificateArr) {
        final C1235no c1235no = this.a;
        Objects.requireNonNull(c1235no);
        PostTask.b(AbstractC0538bE.a, new Runnable(c1235no, privateKey, x509CertificateArr) { // from class: ko
            public final C1235no s;
            public final PrivateKey t;
            public final X509Certificate[] u;

            {
                this.s = c1235no;
                this.t = privateKey;
                this.u = x509CertificateArr;
            }

            @Override // java.lang.Runnable
            public void run() {
                C1235no c1235no2 = this.s;
                PrivateKey privateKey2 = this.t;
                X509Certificate[] x509CertificateArr2 = this.u;
                c1235no2.a();
                if (privateKey2 == null || x509CertificateArr2 == null || x509CertificateArr2.length == 0) {
                    Log.w("AwContentsClientBridge", "Empty client certificate chain?");
                    c1235no2.b(null, null);
                    return;
                }
                byte[][] bArr = new byte[x509CertificateArr2.length][];
                for (int i = 0; i < x509CertificateArr2.length; i++) {
                    try {
                        bArr[i] = x509CertificateArr2[i].getEncoded();
                    } catch (CertificateEncodingException e) {
                        Log.w("AwContentsClientBridge", "Could not retrieve encoded certificate chain: " + e);
                        c1235no2.b(null, null);
                        return;
                    }
                }
                C0284Pp c0284Pp = c1235no2.e.d;
                String str = c1235no2.b;
                int i2 = c1235no2.c;
                Objects.requireNonNull(c0284Pp);
                String strA = C0284Pp.a(str, i2);
                c0284Pp.a.put(strA, new C0266Op(privateKey2, bArr));
                c0284Pp.b.remove(strA);
                c1235no2.b(privateKey2, bArr);
            }
        });
    }
}
