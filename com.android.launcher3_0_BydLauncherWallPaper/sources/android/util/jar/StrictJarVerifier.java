package android.util.jar;

import android.security.keystore.KeyProperties;
import android.telephony.SmsManager;
import android.util.jar.StrictJarManifest;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.jar.Attributes;
import sun.security.jca.Providers;
import sun.security.pkcs.PKCS7;
import sun.security.pkcs.SignerInfo;

/* JADX INFO: loaded from: classes3.dex */
class StrictJarVerifier {
    private static final String[] DIGEST_ALGORITHMS = {KeyProperties.DIGEST_SHA512, KeyProperties.DIGEST_SHA384, "SHA-256", "SHA1"};
    private static final String SF_ATTRIBUTE_ANDROID_APK_SIGNED_NAME = "X-Android-APK-Signed";
    private final String jarName;
    private final int mainAttributesEnd;
    private final StrictJarManifest manifest;
    private final HashMap<String, byte[]> metaEntries;
    private final boolean signatureSchemeRollbackProtectionsEnforced;
    private final Hashtable<String, HashMap<String, Attributes>> signatures = new Hashtable<>(5);
    private final Hashtable<String, Certificate[]> certificates = new Hashtable<>(5);
    private final Hashtable<String, Certificate[][]> verifiedEntries = new Hashtable<>();

    static class VerifierEntry extends OutputStream {
        private final Certificate[][] certChains;
        private final MessageDigest digest;
        private final byte[] hash;
        private final String name;
        private final Hashtable<String, Certificate[][]> verifiedEntries;

        VerifierEntry(String str, MessageDigest messageDigest, byte[] bArr, Certificate[][] certificateArr, Hashtable<String, Certificate[][]> hashtable) {
            this.name = str;
            this.digest = messageDigest;
            this.hash = bArr;
            this.certChains = certificateArr;
            this.verifiedEntries = hashtable;
        }

        @Override // java.io.OutputStream
        public void write(int i) {
            this.digest.update((byte) i);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) {
            this.digest.update(bArr, i, i2);
        }

        void verify() {
            if (!StrictJarVerifier.verifyMessageDigest(this.digest.digest(), this.hash)) {
                String str = this.name;
                throw StrictJarVerifier.invalidDigest("META-INF/MANIFEST.MF", str, str);
            }
            this.verifiedEntries.put(this.name, this.certChains);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SecurityException invalidDigest(String str, String str2, String str3) {
        throw new SecurityException(str + " has invalid digest for " + str2 + " in " + str3);
    }

    private static SecurityException failedVerification(String str, String str2) {
        throw new SecurityException(str + " failed verification of " + str2);
    }

    private static SecurityException failedVerification(String str, String str2, Throwable th) {
        throw new SecurityException(str + " failed verification of " + str2, th);
    }

    StrictJarVerifier(String str, StrictJarManifest strictJarManifest, HashMap<String, byte[]> map, boolean z) {
        this.jarName = str;
        this.manifest = strictJarManifest;
        this.metaEntries = map;
        this.mainAttributesEnd = strictJarManifest.getMainAttributesEnd();
        this.signatureSchemeRollbackProtectionsEnforced = z;
    }

    VerifierEntry initEntry(String str) {
        Attributes attributes;
        if (this.manifest == null || this.signatures.isEmpty() || (attributes = this.manifest.getAttributes(str)) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, HashMap<String, Attributes>> entry : this.signatures.entrySet()) {
            if (entry.getValue().get(str) != null) {
                Certificate[] certificateArr = this.certificates.get(entry.getKey());
                if (certificateArr != null) {
                    arrayList.add(certificateArr);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        Certificate[][] certificateArr2 = (Certificate[][]) arrayList.toArray(new Certificate[arrayList.size()][]);
        int i = 0;
        while (true) {
            String[] strArr = DIGEST_ALGORITHMS;
            if (i >= strArr.length) {
                break;
            }
            String str2 = strArr[i];
            String value = attributes.getValue(str2 + "-Digest");
            if (value != null) {
                try {
                    return new VerifierEntry(str, MessageDigest.getInstance(str2), value.getBytes(StandardCharsets.ISO_8859_1), certificateArr2, this.verifiedEntries);
                } catch (NoSuchAlgorithmException unused) {
                    continue;
                }
            }
            i++;
        }
    }

    void addMetaEntry(String str, byte[] bArr) {
        this.metaEntries.put(str.toUpperCase(Locale.US), bArr);
    }

    synchronized boolean readCertificates() {
        if (this.metaEntries.isEmpty()) {
            return false;
        }
        Iterator<String> it = this.metaEntries.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next.endsWith(".DSA") || next.endsWith(".RSA") || next.endsWith(".EC")) {
                verifyCertificate(next);
                it.remove();
            }
        }
        return true;
    }

    static Certificate[] verifyBytes(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        try {
            try {
                Object objStartJarVerification = Providers.startJarVerification();
                PKCS7 pkcs7 = new PKCS7(bArr);
                SignerInfo[] signerInfoArrVerify = pkcs7.verify(bArr2);
                if (signerInfoArrVerify == null || signerInfoArrVerify.length == 0) {
                    throw new GeneralSecurityException("Failed to verify signature: no verified SignerInfos");
                }
                ArrayList certificateChain = signerInfoArrVerify[0].getCertificateChain(pkcs7);
                if (certificateChain == null) {
                    throw new GeneralSecurityException("Failed to find verified SignerInfo certificate chain");
                }
                if (certificateChain.isEmpty()) {
                    throw new GeneralSecurityException("Verified SignerInfo certificate chain is emtpy");
                }
                Certificate[] certificateArr = (Certificate[]) certificateChain.toArray(new X509Certificate[certificateChain.size()]);
                Providers.stopJarVerification(objStartJarVerification);
                return certificateArr;
            } catch (IOException e) {
                throw new GeneralSecurityException("IO exception verifying jar cert", e);
            }
        } catch (Throwable th) {
            Providers.stopJarVerification((Object) null);
            throw th;
        }
    }

    private void verifyCertificate(String str) {
        byte[] bArr;
        String value;
        boolean z;
        boolean z2;
        boolean z3 = false;
        String str2 = str.substring(0, str.lastIndexOf(46)) + ".SF";
        byte[] bArr2 = this.metaEntries.get(str2);
        if (bArr2 == null || (bArr = this.metaEntries.get("META-INF/MANIFEST.MF")) == null) {
            return;
        }
        try {
            Certificate[] certificateArrVerifyBytes = verifyBytes(this.metaEntries.get(str), bArr2);
            if (certificateArrVerifyBytes != null) {
                this.certificates.put(str2, certificateArrVerifyBytes);
            }
            Attributes attributes = new Attributes();
            HashMap<String, Attributes> map = new HashMap<>();
            try {
                new StrictJarManifestReader(bArr2, attributes).readEntries(map, null);
                if (this.signatureSchemeRollbackProtectionsEnforced && (value = attributes.getValue(SF_ATTRIBUTE_ANDROID_APK_SIGNED_NAME)) != null) {
                    StringTokenizer stringTokenizer = new StringTokenizer(value, SmsManager.REGEX_PREFIX_DELIMITER);
                    while (true) {
                        if (!stringTokenizer.hasMoreTokens()) {
                            z = false;
                            z2 = false;
                            break;
                        }
                        String strTrim = stringTokenizer.nextToken().trim();
                        if (!strTrim.isEmpty()) {
                            try {
                                int i = Integer.parseInt(strTrim);
                                if (i != 2) {
                                    if (i == 3) {
                                        z = false;
                                        z2 = true;
                                        break;
                                    }
                                } else {
                                    z2 = false;
                                    z = true;
                                    break;
                                }
                            } catch (Exception unused) {
                                continue;
                            }
                        }
                    }
                    if (z) {
                        throw new SecurityException(str2 + " indicates " + this.jarName + " is signed using APK Signature Scheme v2, but no such signature was found. Signature stripped?");
                    }
                    if (z2) {
                        throw new SecurityException(str2 + " indicates " + this.jarName + " is signed using APK Signature Scheme v3, but no such signature was found. Signature stripped?");
                    }
                }
                if (attributes.get(Attributes.Name.SIGNATURE_VERSION) == null) {
                    return;
                }
                String value2 = attributes.getValue("Created-By");
                if (value2 != null && value2.indexOf("signtool") != -1) {
                    z3 = true;
                }
                int i2 = this.mainAttributesEnd;
                if (i2 > 0 && !z3 && !verify(attributes, "-Digest-Manifest-Main-Attributes", bArr, 0, i2, false, true)) {
                    throw failedVerification(this.jarName, str2);
                }
                if (!verify(attributes, z3 ? "-Digest" : "-Digest-Manifest", bArr, 0, bArr.length, false, false)) {
                    for (Map.Entry<String, Attributes> entry : map.entrySet()) {
                        StrictJarManifest.Chunk chunk = this.manifest.getChunk(entry.getKey());
                        if (chunk == null) {
                            return;
                        }
                        if (!verify(entry.getValue(), "-Digest", bArr, chunk.start, chunk.end, z3, false)) {
                            throw invalidDigest(str2, entry.getKey(), this.jarName);
                        }
                    }
                }
                this.metaEntries.put(str2, null);
                this.signatures.put(str2, map);
            } catch (IOException unused2) {
            }
        } catch (GeneralSecurityException e) {
            throw failedVerification(this.jarName, str2, e);
        }
    }

    boolean isSignedJar() {
        return this.certificates.size() > 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean verify(java.util.jar.Attributes r3, java.lang.String r4, byte[] r5, int r6, int r7, boolean r8, boolean r9) {
        /*
            r2 = this;
            r2 = 0
        L1:
            java.lang.String[] r0 = android.util.jar.StrictJarVerifier.DIGEST_ALGORITHMS
            int r1 = r0.length
            if (r2 >= r1) goto L4f
            r0 = r0[r2]
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.StringBuilder r1 = r1.append(r0)
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = r3.getValue(r1)
            if (r1 != 0) goto L20
            goto L4c
        L20:
            java.security.MessageDigest r2 = java.security.MessageDigest.getInstance(r0)     // Catch: java.security.NoSuchAlgorithmException -> L4c
            if (r8 == 0) goto L39
            int r3 = r7 + (-1)
            r4 = r5[r3]
            r8 = 10
            if (r4 != r8) goto L39
            int r4 = r7 + (-2)
            r4 = r5[r4]
            if (r4 != r8) goto L39
            int r3 = r3 - r6
            r2.update(r5, r6, r3)
            goto L3d
        L39:
            int r7 = r7 - r6
            r2.update(r5, r6, r7)
        L3d:
            byte[] r2 = r2.digest()
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.ISO_8859_1
            byte[] r3 = r1.getBytes(r3)
            boolean r2 = verifyMessageDigest(r2, r3)
            return r2
        L4c:
            int r2 = r2 + 1
            goto L1
        L4f:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: android.util.jar.StrictJarVerifier.verify(java.util.jar.Attributes, java.lang.String, byte[], int, int, boolean, boolean):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean verifyMessageDigest(byte[] bArr, byte[] bArr2) {
        try {
            return MessageDigest.isEqual(bArr, Base64.getDecoder().decode(bArr2));
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    Certificate[][] getCertificateChains(String str) {
        return this.verifiedEntries.get(str);
    }

    void removeMetaEntries() {
        this.metaEntries.clear();
    }
}
