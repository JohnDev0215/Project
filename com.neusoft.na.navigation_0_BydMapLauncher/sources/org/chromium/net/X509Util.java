package org.chromium.net;

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
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.chromium.base.ContextUtils;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("net")
public class X509Util {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String OID_ANY_EKU = "2.5.29.37.0";
    private static final String OID_SERVER_GATED_MICROSOFT = "1.3.6.1.4.1.311.10.3.3";
    private static final String OID_SERVER_GATED_NETSCAPE = "2.16.840.1.113730.4.1";
    private static final String OID_TLS_SERVER_AUTH = "1.3.6.1.5.5.7.3.1";
    private static final String TAG = "X509Util";
    private static CertificateFactory sCertificateFactory = null;
    private static X509TrustManagerImplementation sDefaultTrustManager = null;
    private static boolean sDisableNativeCodeForTest = false;
    private static boolean sLoadedSystemKeyStore;
    private static File sSystemCertificateDirectory;
    private static KeyStore sSystemKeyStore;
    private static Set<Pair<X500Principal, PublicKey>> sSystemTrustAnchorCache;
    private static KeyStore sTestKeyStore;
    private static X509TrustManagerImplementation sTestTrustManager;
    private static TrustStorageListener sTrustStorageListener;
    private static final Object sLock = new Object();
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private interface X509TrustManagerImplementation {
        List<X509Certificate> checkServerTrusted(X509Certificate[] x509CertificateArr, String str, String str2) throws CertificateException;
    }

    private static native void nativeNotifyKeyChainChanged();

    private static native void nativeRecordCertVerifyCapabilitiesHistogram(boolean z);

    private static final class TrustStorageListener extends BroadcastReceiver {
        private TrustStorageListener() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.security.STORAGE_CHANGED")) {
                try {
                    X509Util.reloadDefaultTrustManager();
                } catch (KeyStoreException e) {
                    Log.e(X509Util.TAG, "Unable to reload the default TrustManager", e);
                } catch (NoSuchAlgorithmException e2) {
                    Log.e(X509Util.TAG, "Unable to reload the default TrustManager", e2);
                } catch (CertificateException e3) {
                    Log.e(X509Util.TAG, "Unable to reload the default TrustManager", e3);
                }
            }
        }
    }

    private static final class X509TrustManagerIceCreamSandwich implements X509TrustManagerImplementation {
        private final X509TrustManager mTrustManager;

        public X509TrustManagerIceCreamSandwich(X509TrustManager x509TrustManager) {
            this.mTrustManager = x509TrustManager;
        }

        @Override // org.chromium.net.X509Util.X509TrustManagerImplementation
        public List<X509Certificate> checkServerTrusted(X509Certificate[] x509CertificateArr, String str, String str2) throws CertificateException {
            this.mTrustManager.checkServerTrusted(x509CertificateArr, str);
            return Collections.emptyList();
        }
    }

    private static final class X509TrustManagerJellyBean implements X509TrustManagerImplementation {
        private final X509TrustManagerExtensions mTrustManagerExtensions;

        public X509TrustManagerJellyBean(X509TrustManager x509TrustManager) {
            this.mTrustManagerExtensions = new X509TrustManagerExtensions(x509TrustManager);
        }

        @Override // org.chromium.net.X509Util.X509TrustManagerImplementation
        public List<X509Certificate> checkServerTrusted(X509Certificate[] x509CertificateArr, String str, String str2) throws CertificateException {
            return this.mTrustManagerExtensions.checkServerTrusted(x509CertificateArr, str, str2);
        }
    }

    private static void ensureInitialized() throws NoSuchAlgorithmException, CertificateException, KeyStoreException {
        synchronized (sLock) {
            ensureInitializedLocked();
        }
    }

    private static void ensureInitializedLocked() throws NoSuchAlgorithmException, KeyStoreException, CertificateException {
        if (sCertificateFactory == null) {
            sCertificateFactory = CertificateFactory.getInstance("X.509");
        }
        if (sDefaultTrustManager == null) {
            sDefaultTrustManager = createTrustManager(null);
        }
        if (!sLoadedSystemKeyStore) {
            try {
                KeyStore keyStore = KeyStore.getInstance("AndroidCAStore");
                sSystemKeyStore = keyStore;
                try {
                    keyStore.load(null);
                } catch (IOException unused) {
                }
                sSystemCertificateDirectory = new File(System.getenv("ANDROID_ROOT") + "/etc/security/cacerts");
            } catch (KeyStoreException unused2) {
            }
            if (!sDisableNativeCodeForTest) {
                nativeRecordCertVerifyCapabilitiesHistogram(sSystemKeyStore != null);
            }
            sLoadedSystemKeyStore = true;
        }
        if (sSystemTrustAnchorCache == null) {
            sSystemTrustAnchorCache = new HashSet();
        }
        if (sTestKeyStore == null) {
            KeyStore keyStore2 = KeyStore.getInstance(KeyStore.getDefaultType());
            sTestKeyStore = keyStore2;
            try {
                keyStore2.load(null);
            } catch (IOException unused3) {
            }
        }
        if (sTestTrustManager == null) {
            sTestTrustManager = createTrustManager(sTestKeyStore);
        }
        if (sDisableNativeCodeForTest || sTrustStorageListener != null) {
            return;
        }
        sTrustStorageListener = new TrustStorageListener();
        ContextUtils.getApplicationContext().registerReceiver(sTrustStorageListener, new IntentFilter("android.security.STORAGE_CHANGED"));
    }

    private static X509TrustManagerImplementation createTrustManager(KeyStore keyStore) throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
            if (trustManager instanceof X509TrustManager) {
                try {
                    return new X509TrustManagerJellyBean((X509TrustManager) trustManager);
                } catch (IllegalArgumentException e) {
                    Log.e(TAG, "Error creating trust manager (" + trustManager.getClass().getName() + "): " + e);
                }
            }
        }
        Log.e(TAG, "Could not find suitable trust manager");
        return null;
    }

    private static void reloadTestTrustManager() throws NoSuchAlgorithmException, KeyStoreException {
        sTestTrustManager = createTrustManager(sTestKeyStore);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void reloadDefaultTrustManager() throws NoSuchAlgorithmException, KeyStoreException, CertificateException {
        synchronized (sLock) {
            sDefaultTrustManager = null;
            sSystemTrustAnchorCache = null;
            ensureInitializedLocked();
        }
        nativeNotifyKeyChainChanged();
    }

    public static X509Certificate createCertificateFromBytes(byte[] bArr) throws NoSuchAlgorithmException, CertificateException, KeyStoreException {
        ensureInitialized();
        return (X509Certificate) sCertificateFactory.generateCertificate(new ByteArrayInputStream(bArr));
    }

    public static void addTestRootCertificate(byte[] bArr) throws NoSuchAlgorithmException, CertificateException, KeyStoreException {
        ensureInitialized();
        X509Certificate x509CertificateCreateCertificateFromBytes = createCertificateFromBytes(bArr);
        synchronized (sLock) {
            sTestKeyStore.setCertificateEntry("root_cert_" + Integer.toString(sTestKeyStore.size()), x509CertificateCreateCertificateFromBytes);
            reloadTestTrustManager();
        }
    }

    public static void clearTestRootCertificates() throws NoSuchAlgorithmException, CertificateException, KeyStoreException {
        ensureInitialized();
        synchronized (sLock) {
            try {
                sTestKeyStore.load(null);
                reloadTestTrustManager();
            } catch (IOException unused) {
            }
        }
    }

    private static String hashPrincipal(X500Principal x500Principal) throws NoSuchAlgorithmException {
        byte[] bArrDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(x500Principal.getEncoded());
        char[] cArr = new char[8];
        for (int i = 0; i < 4; i++) {
            int i2 = i * 2;
            char[] cArr2 = HEX_DIGITS;
            byte b = bArrDigest[3 - i];
            cArr[i2] = cArr2[(b >> 4) & 15];
            cArr[i2 + 1] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    private static boolean isKnownRoot(X509Certificate x509Certificate) throws NoSuchAlgorithmException, KeyStoreException {
        if (sSystemKeyStore == null) {
            return false;
        }
        Pair<X500Principal, PublicKey> pair = new Pair<>(x509Certificate.getSubjectX500Principal(), x509Certificate.getPublicKey());
        if (sSystemTrustAnchorCache.contains(pair)) {
            return true;
        }
        String strHashPrincipal = hashPrincipal(x509Certificate.getSubjectX500Principal());
        int i = 0;
        while (true) {
            String str = strHashPrincipal + '.' + i;
            if (!new File(sSystemCertificateDirectory, str).exists()) {
                return false;
            }
            Certificate certificate = sSystemKeyStore.getCertificate("system:" + str);
            if (certificate != null) {
                if (!(certificate instanceof X509Certificate)) {
                    Log.e(TAG, "Anchor " + str + " not an X509Certificate: " + certificate.getClass().getName());
                } else {
                    X509Certificate x509Certificate2 = (X509Certificate) certificate;
                    if (x509Certificate.getSubjectX500Principal().equals(x509Certificate2.getSubjectX500Principal()) && x509Certificate.getPublicKey().equals(x509Certificate2.getPublicKey())) {
                        sSystemTrustAnchorCache.add(pair);
                        return true;
                    }
                }
            }
            i++;
        }
    }

    static boolean verifyKeyUsage(X509Certificate x509Certificate) throws CertificateException {
        List<String> extendedKeyUsage;
        try {
            extendedKeyUsage = x509Certificate.getExtendedKeyUsage();
        } catch (NullPointerException unused) {
        }
        if (extendedKeyUsage == null) {
            return true;
        }
        for (String str : extendedKeyUsage) {
            if (str.equals(OID_TLS_SERVER_AUTH) || str.equals(OID_ANY_EKU) || str.equals(OID_SERVER_GATED_NETSCAPE) || str.equals(OID_SERVER_GATED_MICROSOFT)) {
                return true;
            }
        }
        return false;
    }

    public static AndroidCertVerifyResult verifyServerCertificates(byte[][] bArr, String str, String str2) throws NoSuchAlgorithmException, KeyStoreException {
        List<X509Certificate> listCheckServerTrusted;
        if (bArr == null || bArr.length == 0 || bArr[0] == null) {
            throw new IllegalArgumentException("Expected non-null and non-empty certificate chain passed as |certChain|. |certChain|=" + Arrays.deepToString(bArr));
        }
        try {
            ensureInitialized();
            X509Certificate[] x509CertificateArr = new X509Certificate[bArr.length];
            for (int i = 0; i < bArr.length; i++) {
                try {
                    x509CertificateArr[i] = createCertificateFromBytes(bArr[i]);
                } catch (CertificateException unused) {
                    return new AndroidCertVerifyResult(-5);
                }
            }
            try {
                x509CertificateArr[0].checkValidity();
                if (!verifyKeyUsage(x509CertificateArr[0])) {
                    return new AndroidCertVerifyResult(-6);
                }
                synchronized (sLock) {
                    X509TrustManagerImplementation x509TrustManagerImplementation = sDefaultTrustManager;
                    if (x509TrustManagerImplementation == null) {
                        return new AndroidCertVerifyResult(-1);
                    }
                    try {
                        listCheckServerTrusted = x509TrustManagerImplementation.checkServerTrusted(x509CertificateArr, str, str2);
                    } catch (CertificateException e) {
                        try {
                            listCheckServerTrusted = sTestTrustManager.checkServerTrusted(x509CertificateArr, str, str2);
                        } catch (CertificateException unused2) {
                            Log.i(TAG, "Failed to validate the certificate chain, error: " + e.getMessage());
                            return new AndroidCertVerifyResult(-2);
                        }
                    }
                    return new AndroidCertVerifyResult(0, listCheckServerTrusted.size() > 0 ? isKnownRoot(listCheckServerTrusted.get(listCheckServerTrusted.size() - 1)) : false, listCheckServerTrusted);
                }
            } catch (CertificateExpiredException unused3) {
                return new AndroidCertVerifyResult(-3);
            } catch (CertificateNotYetValidException unused4) {
                return new AndroidCertVerifyResult(-4);
            } catch (CertificateException unused5) {
                return new AndroidCertVerifyResult(-1);
            }
        } catch (CertificateException unused6) {
            return new AndroidCertVerifyResult(-1);
        }
    }

    public static void setDisableNativeCodeForTest(boolean z) {
        sDisableNativeCodeForTest = z;
    }
}
