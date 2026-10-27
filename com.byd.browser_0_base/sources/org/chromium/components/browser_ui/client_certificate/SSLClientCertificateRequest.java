package org.chromium.components.browser_ui.client_certificate;

import J.N;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.security.KeyChain;
import android.security.KeyChainAliasCallback;
import android.security.KeyChainException;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import defpackage.ed2;
import defpackage.i9;
import defpackage.rh3;
import defpackage.xz;
import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class SSLClientCertificateRequest {

    public static class CertSelectionFailureDialog {
        public final Context a;

        public CertSelectionFailureDialog(Context context) {
            this.a = context;
        }
    }

    public static class a extends i9<Void> {
        public byte[][] h;
        public PrivateKey i;
        public final long j;
        public final Context k;
        public final String l;

        public a(Context context, long j, String str) {
            this.j = j;
            this.k = context;
            this.l = str;
        }

        @Override // defpackage.i9
        public final Void b() {
            PrivateKey privateKey;
            X509Certificate[] certificateChain;
            Context context = this.k;
            String str = this.l;
            if (str != null) {
                try {
                    privateKey = KeyChain.getPrivateKey(context, str);
                } catch (KeyChainException unused) {
                    Log.w("cr_SSLClientCertRequest", "KeyChainException when looking for '" + str + "' certificate");
                    privateKey = null;
                } catch (InterruptedException unused2) {
                    Log.w("cr_SSLClientCertRequest", "InterruptedException when looking for '" + str + "'certificate");
                    privateKey = null;
                }
                try {
                    certificateChain = KeyChain.getCertificateChain(context, str);
                } catch (KeyChainException unused3) {
                    Log.w("cr_SSLClientCertRequest", "KeyChainException when looking for '" + str + "' certificate");
                    certificateChain = null;
                } catch (InterruptedException unused4) {
                    Log.w("cr_SSLClientCertRequest", "InterruptedException when looking for '" + str + "'certificate");
                    certificateChain = null;
                }
                if (privateKey == null || certificateChain == null || certificateChain.length == 0) {
                    Log.w("cr_SSLClientCertRequest", "Empty client certificate chain?");
                } else {
                    byte[][] bArr = new byte[certificateChain.length][];
                    for (int i = 0; i < certificateChain.length; i++) {
                        try {
                            bArr[i] = certificateChain[i].getEncoded();
                        } catch (CertificateEncodingException e) {
                            Log.w("cr_SSLClientCertRequest", "Could not retrieve encoded certificate chain: " + e);
                        }
                    }
                    this.h = bArr;
                    this.i = privateKey;
                }
            }
            return null;
        }

        @Override // defpackage.i9
        public final void l(Void r4) {
            Object obj = ThreadUtils.a;
            N.M8LmNuWo(this.j, this.h, this.i);
        }
    }

    public static class b implements KeyChainAliasCallback {
        public final long a;
        public final Context b;
        public boolean c;

        public b(Context context, long j) {
            this.b = context;
            this.a = j;
        }

        @Override // android.security.KeyChainAliasCallback
        public final void alias(String str) {
            if (!this.c) {
                this.c = true;
                PostTask.d(rh3.a, new ed2(this, 0, str));
            } else {
                Log.w("cr_SSLClientCertRequest", "KeyChainCertSelectionCallback called more than once ('" + str + "')");
            }
        }
    }

    @CalledByNative
    public static boolean selectClientCertificate(long j, WindowAndroid windowAndroid, String[] strArr, byte[][] bArr, String str, int i) {
        X500Principal[] x500PrincipalArr;
        Object obj = ThreadUtils.a;
        Context context = windowAndroid.i.get();
        Activity activityA = xz.a(context);
        if (activityA == null) {
            Log.w("cr_SSLClientCertRequest", "Certificate request on GC'd activity.");
            return false;
        }
        if (bArr.length > 0) {
            x500PrincipalArr = new X500Principal[bArr.length];
            for (int i2 = 0; i2 < bArr.length; i2++) {
                try {
                    x500PrincipalArr[i2] = new X500Principal(bArr[i2]);
                } catch (Exception e) {
                    Log.w("cr_SSLClientCertRequest", "Exception while decoding issuers list: " + e);
                    return false;
                }
            }
        } else {
            x500PrincipalArr = null;
        }
        b bVar = new b(activityA.getApplicationContext(), j);
        CertSelectionFailureDialog certSelectionFailureDialog = new CertSelectionFailureDialog(context);
        try {
            KeyChain.choosePrivateKeyAlias(activityA, bVar, strArr, x500PrincipalArr, str, i, null);
            return true;
        } catch (ActivityNotFoundException unused) {
            bVar.alias(null);
            AlertDialog.Builder builder = new AlertDialog.Builder(certSelectionFailureDialog.a, R$style.ThemeOverlay_BrowserUI_AlertDialog);
            builder.i(R$string.client_cert_unsupported_title);
            builder.b(R$string.client_cert_unsupported_message);
            builder.e(R$string.close, new DialogInterface.OnClickListener() { // from class: dd2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i3) {
                }
            });
            builder.k();
            return true;
        }
    }
}
