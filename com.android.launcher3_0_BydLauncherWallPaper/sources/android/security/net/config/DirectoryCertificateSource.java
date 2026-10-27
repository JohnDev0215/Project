package android.security.net.config;

import android.security.keystore.KeyProperties;
import android.text.format.DateFormat;
import android.util.ArraySet;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import libcore.io.IoUtils;

/* JADX INFO: loaded from: classes2.dex */
abstract class DirectoryCertificateSource implements CertificateSource {
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', DateFormat.AM_PM, 'b', 'c', DateFormat.DATE, 'e', 'f'};
    private static final String LOG_TAG = "DirectoryCertificateSrc";
    private final CertificateFactory mCertFactory;
    private Set<X509Certificate> mCertificates;
    private final File mDir;
    private final Object mLock = new Object();

    private interface CertSelector {
        boolean match(X509Certificate x509Certificate);
    }

    protected abstract boolean isCertMarkedAsRemoved(String str);

    protected DirectoryCertificateSource(File file) {
        this.mDir = file;
        try {
            this.mCertFactory = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            throw new RuntimeException("Failed to obtain X.509 CertificateFactory", e);
        }
    }

    @Override // android.security.net.config.CertificateSource
    public Set<X509Certificate> getCertificates() {
        X509Certificate certificate;
        synchronized (this.mLock) {
            Set<X509Certificate> set = this.mCertificates;
            if (set != null) {
                return set;
            }
            ArraySet arraySet = new ArraySet();
            if (this.mDir.isDirectory()) {
                for (String str : this.mDir.list()) {
                    if (!isCertMarkedAsRemoved(str) && (certificate = readCertificate(str)) != null) {
                        arraySet.add(certificate);
                    }
                }
            }
            this.mCertificates = arraySet;
            return arraySet;
        }
    }

    @Override // android.security.net.config.CertificateSource
    public X509Certificate findBySubjectAndPublicKey(final X509Certificate x509Certificate) {
        return findCert(x509Certificate.getSubjectX500Principal(), new CertSelector() { // from class: android.security.net.config.DirectoryCertificateSource.1
            @Override // android.security.net.config.DirectoryCertificateSource.CertSelector
            public boolean match(X509Certificate x509Certificate2) {
                return x509Certificate2.getPublicKey().equals(x509Certificate.getPublicKey());
            }
        });
    }

    @Override // android.security.net.config.CertificateSource
    public X509Certificate findByIssuerAndSignature(final X509Certificate x509Certificate) {
        return findCert(x509Certificate.getIssuerX500Principal(), new CertSelector() { // from class: android.security.net.config.DirectoryCertificateSource.2
            @Override // android.security.net.config.DirectoryCertificateSource.CertSelector
            public boolean match(X509Certificate x509Certificate2) {
                try {
                    x509Certificate.verify(x509Certificate2.getPublicKey());
                    return true;
                } catch (Exception unused) {
                    return false;
                }
            }
        });
    }

    @Override // android.security.net.config.CertificateSource
    public Set<X509Certificate> findAllByIssuerAndSignature(final X509Certificate x509Certificate) {
        return findCerts(x509Certificate.getIssuerX500Principal(), new CertSelector() { // from class: android.security.net.config.DirectoryCertificateSource.3
            @Override // android.security.net.config.DirectoryCertificateSource.CertSelector
            public boolean match(X509Certificate x509Certificate2) {
                try {
                    x509Certificate.verify(x509Certificate2.getPublicKey());
                    return true;
                } catch (Exception unused) {
                    return false;
                }
            }
        });
    }

    @Override // android.security.net.config.CertificateSource
    public void handleTrustStorageUpdate() {
        synchronized (this.mLock) {
            this.mCertificates = null;
        }
    }

    private Set<X509Certificate> findCerts(X500Principal x500Principal, CertSelector certSelector) {
        X509Certificate certificate;
        String hash = getHash(x500Principal);
        ArraySet arraySet = null;
        for (int i = 0; i >= 0; i++) {
            String str = hash + "." + i;
            if (!new File(this.mDir, str).exists()) {
                break;
            }
            if (!isCertMarkedAsRemoved(str) && (certificate = readCertificate(str)) != null && x500Principal.equals(certificate.getSubjectX500Principal()) && certSelector.match(certificate)) {
                if (arraySet == null) {
                    arraySet = new ArraySet();
                }
                arraySet.add(certificate);
            }
        }
        return arraySet != null ? arraySet : Collections.emptySet();
    }

    private X509Certificate findCert(X500Principal x500Principal, CertSelector certSelector) {
        X509Certificate certificate;
        String hash = getHash(x500Principal);
        for (int i = 0; i >= 0; i++) {
            String str = hash + "." + i;
            if (!new File(this.mDir, str).exists()) {
                return null;
            }
            if (!isCertMarkedAsRemoved(str) && (certificate = readCertificate(str)) != null && x500Principal.equals(certificate.getSubjectX500Principal()) && certSelector.match(certificate)) {
                return certificate;
            }
        }
        return null;
    }

    private String getHash(X500Principal x500Principal) {
        return intToHexString(hashName(x500Principal), 8);
    }

    private static String intToHexString(int i, int i2) {
        int i3;
        int i4 = 8;
        char[] cArr = new char[8];
        while (true) {
            i4--;
            cArr[i4] = DIGITS[i & 15];
            i >>>= 4;
            if (i == 0 && (i3 = 8 - i4) >= i2) {
                return new String(cArr, i4, i3);
            }
        }
    }

    private static int hashName(X500Principal x500Principal) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance(KeyProperties.DIGEST_MD5).digest(x500Principal.getEncoded());
            return ((bArrDigest[3] & 255) << 24) | ((bArrDigest[1] & 255) << 8) | ((bArrDigest[0] & 255) << 0) | ((bArrDigest[2] & 255) << 16);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0041: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:17:0x0041 */
    private X509Certificate readCertificate(String str) throws Throwable {
        BufferedInputStream bufferedInputStream;
        AutoCloseable autoCloseable;
        AutoCloseable autoCloseable2 = null;
        try {
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(this.mDir, str)));
            } catch (IOException | CertificateException e) {
                e = e;
                bufferedInputStream = null;
            } catch (Throwable th) {
                th = th;
                IoUtils.closeQuietly(autoCloseable2);
                throw th;
            }
            try {
                X509Certificate x509Certificate = (X509Certificate) this.mCertFactory.generateCertificate(bufferedInputStream);
                IoUtils.closeQuietly(bufferedInputStream);
                return x509Certificate;
            } catch (IOException | CertificateException e2) {
                e = e2;
                Log.e(LOG_TAG, "Failed to read certificate from " + str, e);
                IoUtils.closeQuietly(bufferedInputStream);
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            autoCloseable2 = autoCloseable;
            IoUtils.closeQuietly(autoCloseable2);
            throw th;
        }
    }
}
