package defpackage;

import android.net.http.SslCertificate;
import android.util.Log;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/* JADX INFO: renamed from: qq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1402qq {
    public static SslCertificate a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return new SslCertificate(AbstractC0824gL.b(bArr));
        } catch (KeyStoreException e) {
            Log.w("SslUtil", "Could not read certificate: " + e);
            return null;
        } catch (NoSuchAlgorithmException e2) {
            Log.w("SslUtil", "Could not read certificate: " + e2);
            return null;
        } catch (CertificateException e3) {
            Log.w("SslUtil", "Could not read certificate: " + e3);
            return null;
        }
    }
}
