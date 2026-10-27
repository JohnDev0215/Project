package defpackage;

import J.N;
import android.content.IntentFilter;
import android.os.Build;
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
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.chromium.net.AndroidCertVerifyResult;

/* JADX INFO: renamed from: gL, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0824gL {
    public static CertificateFactory a;
    public static InterfaceC0712eL b;
    public static C0657dL c;
    public static InterfaceC0712eL d;
    public static KeyStore e;
    public static KeyStore f;
    public static File g;
    public static Set h;
    public static boolean i;
    public static final Object j = new Object();
    public static final char[] k = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static void a() {
        synchronized (j) {
            b = null;
            h = null;
            e();
        }
        N.MGVAvp19();
    }

    public static X509Certificate b(byte[] bArr) {
        d();
        return (X509Certificate) a.generateCertificate(new ByteArrayInputStream(bArr));
    }

    public static InterfaceC0712eL c(KeyStore keyStore) throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
            if (trustManager instanceof X509TrustManager) {
                try {
                    return new C0768fL((X509TrustManager) trustManager);
                } catch (IllegalArgumentException e2) {
                    Log.e("X509Util", "Error creating trust manager (" + trustManager.getClass().getName() + "): " + e2);
                }
            }
        }
        Log.e("X509Util", "Could not find suitable trust manager");
        return null;
    }

    public static void d() {
        synchronized (j) {
            e();
        }
    }

    public static void e() throws NoSuchAlgorithmException, KeyStoreException, CertificateException {
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
        if (e == null) {
            KeyStore keyStore2 = KeyStore.getInstance(KeyStore.getDefaultType());
            e = keyStore2;
            try {
                keyStore2.load(null);
            } catch (IOException unused3) {
            }
        }
        if (d == null) {
            d = c(e);
        }
        if (c == null) {
            c = new C0657dL(null);
            IntentFilter intentFilter = new IntentFilter();
            if (Build.VERSION.SDK_INT >= 26) {
                intentFilter.addAction("android.security.action.KEYCHAIN_CHANGED");
                intentFilter.addAction("android.security.action.KEY_ACCESS_CHANGED");
                intentFilter.addAction("android.security.action.TRUST_STORE_CHANGED");
            } else {
                intentFilter.addAction("android.security.STORAGE_CHANGED");
            }
            Is.a.registerReceiver(c, intentFilter);
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
            int i4 = 3 - i2;
            cArr[i3] = cArr2[(bArrDigest[i4] >> 4) & 15];
            cArr[i3 + 1] = cArr2[bArrDigest[i4] & 15];
        }
        String str = new String(cArr);
        int i5 = 0;
        while (true) {
            String str2 = str + '.' + i5;
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
                    Log.e("X509Util", "Anchor " + str2 + " not an X509Certificate: " + certificate.getClass().getName());
                }
            }
            i5++;
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

    public static AndroidCertVerifyResult h(byte[][] bArr, String str, String str2) {
        List<X509Certificate> listCheckServerTrusted;
        if (bArr == null || bArr.length == 0 || bArr[0] == null) {
            StringBuilder sbF = Z7.f("Expected non-null and non-empty certificate chain passed as |certChain|. |certChain|=");
            sbF.append(Arrays.deepToString(bArr));
            throw new IllegalArgumentException(sbF.toString());
        }
        try {
            d();
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(b(bArr[0]));
                for (int i2 = 1; i2 < bArr.length; i2++) {
                    try {
                        arrayList.add(b(bArr[i2]));
                    } catch (CertificateException unused) {
                        Log.w("X509Util", "intermediate " + i2 + " failed parsing");
                    }
                }
                X509Certificate[] x509CertificateArr = (X509Certificate[]) arrayList.toArray(new X509Certificate[arrayList.size()]);
                try {
                    x509CertificateArr[0].checkValidity();
                    if (!g(x509CertificateArr[0])) {
                        return new AndroidCertVerifyResult(-6);
                    }
                    synchronized (j) {
                        if (b == null) {
                            return new AndroidCertVerifyResult(-1);
                        }
                        try {
                            listCheckServerTrusted = ((C0768fL) b).a.checkServerTrusted(x509CertificateArr, str, str2);
                        } catch (CertificateException e2) {
                            try {
                                listCheckServerTrusted = ((C0768fL) d).a.checkServerTrusted(x509CertificateArr, str, str2);
                            } catch (CertificateException unused2) {
                                Log.i("X509Util", "Failed to validate the certificate chain, error: " + e2.getMessage());
                                return new AndroidCertVerifyResult(-2);
                            }
                        }
                        return new AndroidCertVerifyResult(0, listCheckServerTrusted.size() > 0 ? f(listCheckServerTrusted.get(listCheckServerTrusted.size() - 1)) : false, listCheckServerTrusted);
                    }
                } catch (CertificateExpiredException unused3) {
                    return new AndroidCertVerifyResult(-3);
                } catch (CertificateNotYetValidException unused4) {
                    return new AndroidCertVerifyResult(-4);
                } catch (CertificateException unused5) {
                    return new AndroidCertVerifyResult(-1);
                }
            } catch (CertificateException unused6) {
                return new AndroidCertVerifyResult(-5);
            }
        } catch (CertificateException unused7) {
            return new AndroidCertVerifyResult(-1);
        }
    }
}
