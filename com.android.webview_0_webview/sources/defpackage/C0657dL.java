package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/* JADX INFO: renamed from: dL, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0657dL extends BroadcastReceiver {
    public C0657dL(AbstractC0601cL abstractC0601cL) {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        boolean zEquals = true;
        if (Build.VERSION.SDK_INT < 26) {
            zEquals = "android.security.STORAGE_CHANGED".equals(intent.getAction());
        } else if (!"android.security.action.KEYCHAIN_CHANGED".equals(intent.getAction()) && !"android.security.action.TRUST_STORE_CHANGED".equals(intent.getAction()) && (!"android.security.action.KEY_ACCESS_CHANGED".equals(intent.getAction()) || intent.getBooleanExtra("android.security.extra.KEY_ACCESSIBLE", false))) {
            zEquals = false;
        }
        if (zEquals) {
            try {
                AbstractC0824gL.a();
            } catch (KeyStoreException e) {
                Log.e("X509Util", "Unable to reload the default TrustManager", e);
            } catch (NoSuchAlgorithmException e2) {
                Log.e("X509Util", "Unable to reload the default TrustManager", e2);
            } catch (CertificateException e3) {
                Log.e("X509Util", "Unable to reload the default TrustManager", e3);
            }
        }
    }
}
