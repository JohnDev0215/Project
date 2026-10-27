package defpackage;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.http.X509TrustManagerExtensions;
import android.util.Log;
import android.util.Pair;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: compiled from: X509Util.java */
/* JADX INFO: loaded from: classes3.dex */
public final class cu3 {
    public static CertificateFactory a;
    public static X509TrustManagerExtensions b;
    public static a c;
    public static X509TrustManagerExtensions d;
    public static KeyStore e;
    public static KeyStore f;
    public static File g;
    public static HashSet h;
    public static boolean i;
    public static final Object j = new Object();
    public static final char[] k = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* JADX INFO: compiled from: X509Util.java */
    public static final class a extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            boolean z = true;
            if (!"android.security.action.KEYCHAIN_CHANGED".equals(intent.getAction()) && !"android.security.action.TRUST_STORE_CHANGED".equals(intent.getAction()) && (!"android.security.action.KEY_ACCESS_CHANGED".equals(intent.getAction()) || intent.getBooleanExtra("android.security.extra.KEY_ACCESSIBLE", false))) {
                z = false;
            }
            if (z) {
                try {
                    cu3.a();
                } catch (KeyStoreException e) {
                    Log.e("cr_X509Util", "Unable to reload the default TrustManager", e);
                } catch (NoSuchAlgorithmException e2) {
                    Log.e("cr_X509Util", "Unable to reload the default TrustManager", e2);
                } catch (CertificateException e3) {
                    Log.e("cr_X509Util", "Unable to reload the default TrustManager", e3);
                }
            }
        }
    }

    public static void a() {
        synchronized (j) {
            b = null;
            h = null;
            d();
        }
        N.MGVAvp19();
    }

    public static X509Certificate b(byte[] bArr) {
        synchronized (j) {
            d();
        }
        return (X509Certificate) a.generateCertificate(new ByteArrayInputStream(bArr));
    }

    public static X509TrustManagerExtensions c(KeyStore keyStore) throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        try {
            for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
                if (trustManager instanceof X509TrustManager) {
                    try {
                        return new X509TrustManagerExtensions((X509TrustManager) trustManager);
                    } catch (IllegalArgumentException e2) {
                        Log.e("cr_X509Util", "Error creating trust manager (" + trustManager.getClass().getName() + "): " + e2);
                    }
                }
            }
            Log.e("cr_X509Util", "Could not find suitable trust manager");
            return null;
        } catch (RuntimeException e3) {
            Log.e("cr_X509Util", "TrustManagerFactory.getTrustManagers() unexpectedly threw: %s", e3);
            throw new KeyStoreException(e3);
        }
    }

    public static void d() throws NoSuchAlgorithmException, CertificateException {
        if (a == null) {
            a = CertificateFactory.getInstance("X.509");
        }
        if (b == null) {
            b = c(null);
        }
        if (!i) {
            try {
                KeyStore keyStore = KeyStore.getInstance("AndroidCAStore");
                f = keyStore;
                try {
                    keyStore.load(null);
                } catch (IOException unused) {
                }
                g = new File(System.getenv("ANDROID_ROOT") + "/etc/security/cacerts");
            } catch (KeyStoreException unused2) {
            }
            i = true;
        }
        if (h == null) {
            h = new HashSet();
        }
        if (c == null) {
            c = new a();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.security.action.KEYCHAIN_CHANGED");
            intentFilter.addAction("android.security.action.KEY_ACCESS_CHANGED");
            intentFilter.addAction("android.security.action.TRUST_STORE_CHANGED");
            xz.g(xz.a, c, intentFilter);
        }
    }

    public static void e() throws NoSuchAlgorithmException, KeyStoreException, CertificateException {
        if (e == null) {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            e = keyStore;
            try {
                keyStore.load(null);
            } catch (IOException unused) {
            }
        }
        if (d == null) {
            d = c(e);
        }
    }

    public static boolean f(X509Certificate x509Certificate) throws KeyStoreException {
        if (f == null) {
            return false;
        }
        Pair pair = new Pair(x509Certificate.getSubjectX500Principal(), x509Certificate.getPublicKey());
        if (h.contains(pair)) {
            return true;
        }
        byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(x509Certificate.getSubjectX500Principal().getEncoded());
        char[] cArr = new char[8];
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = i2 * 2;
            char[] cArr2 = k;
            byte b2 = bArrDigest[3 - i2];
            cArr[i3] = cArr2[(b2 >> 4) & 15];
            cArr[i3 + 1] = cArr2[b2 & 15];
        }
        String str = new String(cArr);
        int i4 = 0;
        while (true) {
            String str2 = str + '.' + i4;
            if (!new File(g, str2).exists()) {
                return false;
            }
            Certificate certificate = f.getCertificate("system:" + str2);
            if (certificate != null) {
                if (certificate instanceof X509Certificate) {
                    X509Certificate x509Certificate2 = (X509Certificate) certificate;
                    if (x509Certificate.getSubjectX500Principal().equals(x509Certificate2.getSubjectX500Principal()) && x509Certificate.getPublicKey().equals(x509Certificate2.getPublicKey())) {
                        h.add(pair);
                        return true;
                    }
                } else {
                    Log.e("cr_X509Util", "Anchor " + str2 + " not an X509Certificate: " + certificate.getClass().getName());
                }
            }
            i4++;
        }
    }

    public static boolean g(X509Certificate x509Certificate) throws CertificateParsingException {
        List<String> extendedKeyUsage;
        try {
            extendedKeyUsage = x509Certificate.getExtendedKeyUsage();
        } catch (NullPointerException unused) {
        }
        if (extendedKeyUsage == null) {
            return true;
        }
        for (String str : extendedKeyUsage) {
            if (str.equals("1.3.6.1.5.5.7.3.1") || str.equals("2.5.29.37.0") || str.equals("2.16.840.1.113730.4.1") || str.equals("1.3.6.1.4.1.311.10.3.3")) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00a6 A[Catch: all -> 0x00f2, TRY_ENTER, TryCatch #3 {, blocks: (B:28:0x006d, B:30:0x0071, B:31:0x0076, B:33:0x0078, B:51:0x00d6, B:53:0x00dc, B:54:0x00eb, B:55:0x00f0, B:36:0x007e, B:37:0x008a, B:39:0x008c, B:41:0x0090, B:48:0x00a6, B:49:0x00d4, B:44:0x0096, B:45:0x00a2), top: B:80:0x006d, inners: #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00dc A[Catch: all -> 0x00f2, TryCatch #3 {, blocks: (B:28:0x006d, B:30:0x0071, B:31:0x0076, B:33:0x0078, B:51:0x00d6, B:53:0x00dc, B:54:0x00eb, B:55:0x00f0, B:36:0x007e, B:37:0x008a, B:39:0x008c, B:41:0x0090, B:48:0x00a6, B:49:0x00d4, B:44:0x0096, B:45:0x00a2), top: B:80:0x006d, inners: #6 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.chromium.net.AndroidCertVerifyResult h(byte[][] r7, java.lang.String r8, java.lang.String r9) {
        /*
            Method dump skipped, instruction units count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cu3.h(byte[][], java.lang.String, java.lang.String):org.chromium.net.AndroidCertVerifyResult");
    }
}
