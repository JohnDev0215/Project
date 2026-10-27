package android.util.apk;

import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.Signature;
import android.os.Trace;
import android.util.apk.ApkSignatureSchemeV2Verifier;
import android.util.apk.ApkSignatureSchemeV3Verifier;
import android.util.apk.ApkSignatureSchemeV4Verifier;
import android.util.jar.StrictJarFile;
import com.android.internal.util.ArrayUtils;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.ZipEntry;
import libcore.io.IoUtils;

/* JADX INFO: loaded from: classes3.dex */
public class ApkSignatureVerifier {
    private static final AtomicReference<byte[]> sBuffer = new AtomicReference<>();

    public static int getMinimumSignatureSchemeVersionForTargetSdk(int i) {
        return i >= 30 ? 2 : 1;
    }

    public static PackageParser.SigningDetails verify(String str, int i) throws PackageParser.PackageParserException {
        return verifySignatures(str, i, true);
    }

    public static PackageParser.SigningDetails unsafeGetCertsWithoutVerification(String str, int i) throws PackageParser.PackageParserException {
        return verifySignatures(str, i, false);
    }

    private static PackageParser.SigningDetails verifySignatures(String str, int i, boolean z) throws PackageParser.PackageParserException {
        if (i > 4) {
            throw new PackageParser.PackageParserException(-103, "No signature found in package of version " + i + " or newer for package " + str);
        }
        try {
            return verifyV4Signature(str, i, z);
        } catch (SignatureNotFoundException e) {
            if (i >= 4) {
                throw new PackageParser.PackageParserException(-103, "No APK Signature Scheme v4 signature in package " + str, e);
            }
            if (i > 3) {
                throw new PackageParser.PackageParserException(-103, "No signature found in package of version " + i + " or newer for package " + str);
            }
            return verifyV3AndBelowSignatures(str, i, z);
        }
    }

    private static PackageParser.SigningDetails verifyV3AndBelowSignatures(String str, int i, boolean z) throws PackageParser.PackageParserException {
        try {
            return verifyV3Signature(str, z);
        } catch (SignatureNotFoundException e) {
            if (i >= 3) {
                throw new PackageParser.PackageParserException(-103, "No APK Signature Scheme v3 signature in package " + str, e);
            }
            if (i > 2) {
                throw new PackageParser.PackageParserException(-103, "No signature found in package of version " + i + " or newer for package " + str);
            }
            try {
                return verifyV2Signature(str, z);
            } catch (SignatureNotFoundException e2) {
                if (i >= 2) {
                    throw new PackageParser.PackageParserException(-103, "No APK Signature Scheme v2 signature in package " + str, e2);
                }
                if (i > 1) {
                    throw new PackageParser.PackageParserException(-103, "No signature found in package of version " + i + " or newer for package " + str);
                }
                return verifyV1Signature(str, z);
            }
        }
    }

    private static PackageParser.SigningDetails verifyV4Signature(String str, int i, boolean z) throws SignatureNotFoundException, PackageParser.PackageParserException {
        byte[] bArr;
        Certificate[][] certificateArr;
        Trace.traceBegin(262144L, z ? "verifyV4" : "certsOnlyV4");
        try {
            try {
                try {
                    ApkSignatureSchemeV4Verifier.VerifiedSigner verifiedSignerExtractCertificates = ApkSignatureSchemeV4Verifier.extractCertificates(str);
                    Signature[] signatureArrConvertToSignatures = convertToSignatures(new Certificate[][]{verifiedSignerExtractCertificates.certs});
                    if (z) {
                        try {
                            try {
                                ApkSignatureSchemeV3Verifier.VerifiedSigner verifiedSignerUnsafeGetCertsWithoutVerification = ApkSignatureSchemeV3Verifier.unsafeGetCertsWithoutVerification(str);
                                bArr = verifiedSignerUnsafeGetCertsWithoutVerification.digest;
                                certificateArr = new Certificate[][]{verifiedSignerUnsafeGetCertsWithoutVerification.certs};
                            } catch (SignatureNotFoundException e) {
                                throw new SecurityException("V4 verification failed to collect V2/V3 certificates from : " + str, e);
                            }
                        } catch (SignatureNotFoundException unused) {
                            ApkSignatureSchemeV2Verifier.VerifiedSigner verifiedSignerVerify = ApkSignatureSchemeV2Verifier.verify(str, false);
                            bArr = verifiedSignerVerify.digest;
                            certificateArr = verifiedSignerVerify.certs;
                        }
                        Signature[] signatureArrConvertToSignatures2 = convertToSignatures(certificateArr);
                        if (signatureArrConvertToSignatures2.length != signatureArrConvertToSignatures.length) {
                            throw new SecurityException("Invalid number of certificates: " + signatureArrConvertToSignatures2.length);
                        }
                        int length = signatureArrConvertToSignatures.length;
                        for (int i2 = 0; i2 < length; i2++) {
                            if (!signatureArrConvertToSignatures2[i2].equals(signatureArrConvertToSignatures[i2])) {
                                throw new SecurityException("V4 signature certificate does not match V2/V3");
                            }
                        }
                        if (!ArrayUtils.equals(verifiedSignerExtractCertificates.apkDigest, bArr, verifiedSignerExtractCertificates.apkDigest.length)) {
                            throw new SecurityException("APK digest in V4 signature does not match V2/V3");
                        }
                    }
                    return new PackageParser.SigningDetails(signatureArrConvertToSignatures, 4);
                } catch (SignatureNotFoundException e2) {
                    throw e2;
                }
            } catch (Exception e3) {
                throw new PackageParser.PackageParserException(-103, "Failed to collect certificates from " + str + " using APK Signature Scheme v4", e3);
            }
        } finally {
            Trace.traceEnd(262144L);
        }
    }

    private static PackageParser.SigningDetails verifyV3Signature(String str, boolean z) throws SignatureNotFoundException, PackageParser.PackageParserException {
        ApkSignatureSchemeV3Verifier.VerifiedSigner verifiedSignerUnsafeGetCertsWithoutVerification;
        Trace.traceBegin(262144L, z ? "verifyV3" : "certsOnlyV3");
        try {
            try {
                if (z) {
                    verifiedSignerUnsafeGetCertsWithoutVerification = ApkSignatureSchemeV3Verifier.verify(str);
                } else {
                    verifiedSignerUnsafeGetCertsWithoutVerification = ApkSignatureSchemeV3Verifier.unsafeGetCertsWithoutVerification(str);
                }
                Signature[] signatureArrConvertToSignatures = convertToSignatures(new Certificate[][]{verifiedSignerUnsafeGetCertsWithoutVerification.certs});
                Signature[] signatureArr = null;
                if (verifiedSignerUnsafeGetCertsWithoutVerification.por != null) {
                    int size = verifiedSignerUnsafeGetCertsWithoutVerification.por.certs.size();
                    Signature[] signatureArr2 = new Signature[size];
                    for (int i = 0; i < size; i++) {
                        signatureArr2[i] = new Signature(verifiedSignerUnsafeGetCertsWithoutVerification.por.certs.get(i).getEncoded());
                        signatureArr2[i].setFlags(verifiedSignerUnsafeGetCertsWithoutVerification.por.flagsList.get(i).intValue());
                    }
                    signatureArr = signatureArr2;
                }
                return new PackageParser.SigningDetails(signatureArrConvertToSignatures, 3, signatureArr);
            } catch (SignatureNotFoundException e) {
                throw e;
            } catch (Exception e2) {
                throw new PackageParser.PackageParserException(-103, "Failed to collect certificates from " + str + " using APK Signature Scheme v3", e2);
            }
        } finally {
            Trace.traceEnd(262144L);
        }
    }

    private static PackageParser.SigningDetails verifyV2Signature(String str, boolean z) throws SignatureNotFoundException, PackageParser.PackageParserException {
        X509Certificate[][] x509CertificateArrUnsafeGetCertsWithoutVerification;
        Trace.traceBegin(262144L, z ? "verifyV2" : "certsOnlyV2");
        try {
            try {
                if (z) {
                    x509CertificateArrUnsafeGetCertsWithoutVerification = ApkSignatureSchemeV2Verifier.verify(str);
                } else {
                    x509CertificateArrUnsafeGetCertsWithoutVerification = ApkSignatureSchemeV2Verifier.unsafeGetCertsWithoutVerification(str);
                }
                return new PackageParser.SigningDetails(convertToSignatures(x509CertificateArrUnsafeGetCertsWithoutVerification), 2);
            } catch (SignatureNotFoundException e) {
                throw e;
            } catch (Exception e2) {
                throw new PackageParser.PackageParserException(-103, "Failed to collect certificates from " + str + " using APK Signature Scheme v2", e2);
            }
        } finally {
            Trace.traceEnd(262144L);
        }
    }

    private static PackageParser.SigningDetails verifyV1Signature(String str, boolean z) throws Throwable {
        StrictJarFile strictJarFile = null;
        try {
            try {
                Trace.traceBegin(262144L, "strictJarFileCtor");
                StrictJarFile strictJarFile2 = new StrictJarFile(str, true, z);
                try {
                    ArrayList<ZipEntry> arrayList = new ArrayList();
                    ZipEntry zipEntryFindEntry = strictJarFile2.findEntry(PackageParser.ANDROID_MANIFEST_FILENAME);
                    if (zipEntryFindEntry == null) {
                        throw new PackageParser.PackageParserException(-101, "Package " + str + " has no manifest");
                    }
                    Certificate[][] certificateArrLoadCertificates = loadCertificates(strictJarFile2, zipEntryFindEntry);
                    if (ArrayUtils.isEmpty(certificateArrLoadCertificates)) {
                        throw new PackageParser.PackageParserException(-103, "Package " + str + " has no certificates at entry " + PackageParser.ANDROID_MANIFEST_FILENAME);
                    }
                    Signature[] signatureArrConvertToSignatures = convertToSignatures(certificateArrLoadCertificates);
                    if (z) {
                        for (ZipEntry zipEntry : strictJarFile2) {
                            if (!zipEntry.isDirectory()) {
                                String name = zipEntry.getName();
                                if (!name.startsWith("META-INF/") && !name.equals(PackageParser.ANDROID_MANIFEST_FILENAME)) {
                                    arrayList.add(zipEntry);
                                }
                            }
                        }
                        for (ZipEntry zipEntry2 : arrayList) {
                            Certificate[][] certificateArrLoadCertificates2 = loadCertificates(strictJarFile2, zipEntry2);
                            if (ArrayUtils.isEmpty(certificateArrLoadCertificates2)) {
                                throw new PackageParser.PackageParserException(-103, "Package " + str + " has no certificates at entry " + zipEntry2.getName());
                            }
                            if (!Signature.areExactMatch(signatureArrConvertToSignatures, convertToSignatures(certificateArrLoadCertificates2))) {
                                throw new PackageParser.PackageParserException(PackageManager.INSTALL_PARSE_FAILED_INCONSISTENT_CERTIFICATES, "Package " + str + " has mismatched certificates at entry " + zipEntry2.getName());
                            }
                        }
                    }
                    PackageParser.SigningDetails signingDetails = new PackageParser.SigningDetails(signatureArrConvertToSignatures, 1);
                    Trace.traceEnd(262144L);
                    closeQuietly(strictJarFile2);
                    return signingDetails;
                } catch (IOException e) {
                    e = e;
                    throw new PackageParser.PackageParserException(-103, "Failed to collect certificates from " + str, e);
                } catch (RuntimeException e2) {
                    e = e2;
                    throw new PackageParser.PackageParserException(-103, "Failed to collect certificates from " + str, e);
                } catch (GeneralSecurityException e3) {
                    e = e3;
                    throw new PackageParser.PackageParserException(PackageManager.INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING, "Failed to collect certificates from " + str, e);
                } catch (Throwable th) {
                    th = th;
                    strictJarFile = strictJarFile2;
                    Trace.traceEnd(262144L);
                    closeQuietly(strictJarFile);
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
            } catch (RuntimeException e5) {
                e = e5;
            } catch (GeneralSecurityException e6) {
                e = e6;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static Certificate[][] loadCertificates(StrictJarFile strictJarFile, ZipEntry zipEntry) throws PackageParser.PackageParserException {
        try {
            try {
                InputStream inputStream = strictJarFile.getInputStream(zipEntry);
                readFullyIgnoringContents(inputStream);
                Certificate[][] certificateChains = strictJarFile.getCertificateChains(zipEntry);
                IoUtils.closeQuietly(inputStream);
                return certificateChains;
            } catch (IOException | RuntimeException e) {
                throw new PackageParser.PackageParserException(-102, "Failed reading " + zipEntry.getName() + " in " + strictJarFile, e);
            }
        } catch (Throwable th) {
            IoUtils.closeQuietly((AutoCloseable) null);
            throw th;
        }
    }

    private static void readFullyIgnoringContents(InputStream inputStream) throws IOException {
        byte[] andSet = sBuffer.getAndSet(null);
        if (andSet == null) {
            andSet = new byte[4096];
        }
        while (inputStream.read(andSet, 0, andSet.length) != -1) {
        }
        sBuffer.set(andSet);
    }

    private static Signature[] convertToSignatures(Certificate[][] certificateArr) throws CertificateEncodingException {
        Signature[] signatureArr = new Signature[certificateArr.length];
        for (int i = 0; i < certificateArr.length; i++) {
            signatureArr[i] = new Signature(certificateArr[i]);
        }
        return signatureArr;
    }

    private static void closeQuietly(StrictJarFile strictJarFile) {
        if (strictJarFile != null) {
            try {
                strictJarFile.close();
            } catch (Exception unused) {
            }
        }
    }

    public static class Result {
        public final Certificate[][] certs;
        public final int signatureSchemeVersion;
        public final Signature[] sigs;

        public Result(Certificate[][] certificateArr, Signature[] signatureArr, int i) {
            this.certs = certificateArr;
            this.sigs = signatureArr;
            this.signatureSchemeVersion = i;
        }
    }

    public static byte[] getVerityRootHash(String str) throws IOException, SecurityException {
        try {
            try {
                return ApkSignatureSchemeV3Verifier.getVerityRootHash(str);
            } catch (SignatureNotFoundException unused) {
                return null;
            }
        } catch (SignatureNotFoundException unused2) {
            return ApkSignatureSchemeV2Verifier.getVerityRootHash(str);
        }
    }

    public static byte[] generateApkVerity(String str, ByteBufferFactory byteBufferFactory) throws SignatureNotFoundException, NoSuchAlgorithmException, DigestException, IOException, SecurityException {
        try {
            return ApkSignatureSchemeV3Verifier.generateApkVerity(str, byteBufferFactory);
        } catch (SignatureNotFoundException unused) {
            return ApkSignatureSchemeV2Verifier.generateApkVerity(str, byteBufferFactory);
        }
    }

    public static byte[] generateApkVerityRootHash(String str) throws NoSuchAlgorithmException, DigestException, IOException {
        try {
            try {
                return ApkSignatureSchemeV3Verifier.generateApkVerityRootHash(str);
            } catch (SignatureNotFoundException unused) {
                return null;
            }
        } catch (SignatureNotFoundException unused2) {
            return ApkSignatureSchemeV2Verifier.generateApkVerityRootHash(str);
        }
    }
}
