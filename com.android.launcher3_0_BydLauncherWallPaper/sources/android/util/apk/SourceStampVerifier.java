package android.util.apk;

import android.util.Pair;
import android.util.Slog;
import android.util.jar.StrictJarFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.zip.ZipEntry;
import libcore.io.Streams;

/* JADX INFO: loaded from: classes3.dex */
public abstract class SourceStampVerifier {
    private static final int APK_SIGNATURE_SCHEME_V2_BLOCK_ID = 1896449818;
    private static final int APK_SIGNATURE_SCHEME_V3_BLOCK_ID = -262969152;
    private static final int SOURCE_STAMP_BLOCK_ID = 1845461005;
    private static final String SOURCE_STAMP_CERTIFICATE_HASH_ZIP_ENTRY_NAME = "stamp-cert-sha256";
    private static final String TAG = "SourceStampVerifier";
    private static final int VERSION_APK_SIGNATURE_SCHEME_V2 = 2;
    private static final int VERSION_APK_SIGNATURE_SCHEME_V3 = 3;
    private static final int VERSION_JAR_SIGNATURE_SCHEME = 1;

    private SourceStampVerifier() {
    }

    public static SourceStampVerificationResult verify(List<String> list) throws Throwable {
        Iterator<String> it = list.iterator();
        Certificate certificate = null;
        while (it.hasNext()) {
            SourceStampVerificationResult sourceStampVerificationResultVerify = verify(it.next());
            if (!sourceStampVerificationResultVerify.isPresent() || !sourceStampVerificationResultVerify.isVerified()) {
                return sourceStampVerificationResultVerify;
            }
            if (certificate != null && !certificate.equals(sourceStampVerificationResultVerify.getCertificate())) {
                return SourceStampVerificationResult.notVerified();
            }
            certificate = sourceStampVerificationResultVerify.getCertificate();
        }
        return SourceStampVerificationResult.verified(certificate);
    }

    public static SourceStampVerificationResult verify(String str) throws Throwable {
        StrictJarFile strictJarFile;
        StrictJarFile strictJarFile2 = null;
        try {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
                try {
                    strictJarFile = new StrictJarFile(str, false, false);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    byte[] sourceStampCertificateDigest = getSourceStampCertificateDigest(strictJarFile);
                    try {
                        if (sourceStampCertificateDigest == null) {
                            SourceStampVerificationResult sourceStampVerificationResultNotPresent = SourceStampVerificationResult.notPresent();
                            randomAccessFile.close();
                            closeApkJar(strictJarFile);
                            return sourceStampVerificationResultNotPresent;
                        }
                        SourceStampVerificationResult sourceStampVerificationResultVerify = verify(randomAccessFile, sourceStampCertificateDigest, getManifestBytes(strictJarFile));
                        randomAccessFile.close();
                        closeApkJar(strictJarFile);
                        return sourceStampVerificationResultVerify;
                    } catch (IOException unused) {
                        strictJarFile2 = strictJarFile;
                        SourceStampVerificationResult sourceStampVerificationResultNotPresent2 = SourceStampVerificationResult.notPresent();
                        closeApkJar(strictJarFile2);
                        return sourceStampVerificationResultNotPresent2;
                    } catch (Throwable th2) {
                        th = th2;
                        strictJarFile2 = strictJarFile;
                        closeApkJar(strictJarFile2);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    strictJarFile2 = strictJarFile;
                    try {
                        randomAccessFile.close();
                    } catch (Throwable th4) {
                        th.addSuppressed(th4);
                    }
                    throw th;
                }
            } catch (IOException unused2) {
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }

    private static SourceStampVerificationResult verify(RandomAccessFile randomAccessFile, byte[] bArr, byte[] bArr2) {
        try {
            return verify(ApkSigningBlockUtils.findSignature(randomAccessFile, SOURCE_STAMP_BLOCK_ID), getSignatureSchemeDigests(getSignatureSchemeApkContentDigests(randomAccessFile, bArr2)), bArr);
        } catch (SignatureNotFoundException | IOException | RuntimeException unused) {
            return SourceStampVerificationResult.notVerified();
        }
    }

    private static SourceStampVerificationResult verify(SignatureInfo signatureInfo, Map<Integer, byte[]> map, byte[] bArr) throws IOException, SecurityException {
        ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtils.getLengthPrefixedSlice(signatureInfo.signatureBlock);
        X509Certificate x509CertificateVerifySourceStampCertificate = verifySourceStampCertificate(lengthPrefixedSlice, bArr);
        ByteBuffer lengthPrefixedSlice2 = ApkSigningBlockUtils.getLengthPrefixedSlice(lengthPrefixedSlice);
        HashMap map2 = new HashMap();
        while (lengthPrefixedSlice2.hasRemaining()) {
            ByteBuffer lengthPrefixedSlice3 = ApkSigningBlockUtils.getLengthPrefixedSlice(lengthPrefixedSlice2);
            map2.put(Integer.valueOf(lengthPrefixedSlice3.getInt()), lengthPrefixedSlice3);
        }
        for (Map.Entry<Integer, byte[]> entry : map.entrySet()) {
            if (!map2.containsKey(entry.getKey())) {
                throw new SecurityException(String.format("No signatures found for signature scheme %d", entry.getKey()));
            }
            verifySourceStampSignature((ByteBuffer) map2.get(entry.getKey()), x509CertificateVerifySourceStampCertificate, entry.getValue());
        }
        return SourceStampVerificationResult.verified(x509CertificateVerifySourceStampCertificate);
    }

    private static X509Certificate verifySourceStampCertificate(ByteBuffer byteBuffer, byte[] bArr) throws IOException {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            byte[] lengthPrefixedByteArray = ApkSigningBlockUtils.readLengthPrefixedByteArray(byteBuffer);
            try {
                X509Certificate x509Certificate = (X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(lengthPrefixedByteArray));
                if (!Arrays.equals(bArr, computeSha256Digest(lengthPrefixedByteArray))) {
                    throw new SecurityException("Certificate mismatch between APK and signature block");
                }
                return new VerbatimX509Certificate(x509Certificate, lengthPrefixedByteArray);
            } catch (CertificateException e) {
                throw new SecurityException("Failed to decode certificate", e);
            }
        } catch (CertificateException e2) {
            throw new RuntimeException("Failed to obtain X.509 CertificateFactory", e2);
        }
    }

    private static void verifySourceStampSignature(ByteBuffer byteBuffer, X509Certificate x509Certificate, byte[] bArr) throws IOException {
        ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtils.getLengthPrefixedSlice(byteBuffer);
        int i = 0;
        byte[] lengthPrefixedByteArray = null;
        int i2 = -1;
        while (lengthPrefixedSlice.hasRemaining()) {
            i++;
            try {
                ByteBuffer lengthPrefixedSlice2 = ApkSigningBlockUtils.getLengthPrefixedSlice(lengthPrefixedSlice);
                if (lengthPrefixedSlice2.remaining() < 8) {
                    throw new SecurityException("Signature record too short");
                }
                int i3 = lengthPrefixedSlice2.getInt();
                if (ApkSigningBlockUtils.isSupportedSignatureAlgorithm(i3) && (i2 == -1 || ApkSigningBlockUtils.compareSignatureAlgorithm(i3, i2) > 0)) {
                    lengthPrefixedByteArray = ApkSigningBlockUtils.readLengthPrefixedByteArray(lengthPrefixedSlice2);
                    i2 = i3;
                }
            } catch (IOException | BufferUnderflowException e) {
                throw new SecurityException("Failed to parse signature record #" + i, e);
            }
        }
        if (i2 == -1) {
            if (i == 0) {
                throw new SecurityException("No signatures found");
            }
            throw new SecurityException("No supported signatures found");
        }
        Pair<String, ? extends AlgorithmParameterSpec> signatureAlgorithmJcaSignatureAlgorithm = ApkSigningBlockUtils.getSignatureAlgorithmJcaSignatureAlgorithm(i2);
        String str = signatureAlgorithmJcaSignatureAlgorithm.first;
        AlgorithmParameterSpec algorithmParameterSpec = (AlgorithmParameterSpec) signatureAlgorithmJcaSignatureAlgorithm.second;
        PublicKey publicKey = x509Certificate.getPublicKey();
        try {
            Signature signature = Signature.getInstance(str);
            signature.initVerify(publicKey);
            if (algorithmParameterSpec != null) {
                signature.setParameter(algorithmParameterSpec);
            }
            signature.update(bArr);
            if (!signature.verify(lengthPrefixedByteArray)) {
                throw new SecurityException(str + " signature did not verify");
            }
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException e2) {
            throw new SecurityException("Failed to verify " + str + " signature", e2);
        }
    }

    private static Map<Integer, Map<Integer, byte[]>> getSignatureSchemeApkContentDigests(RandomAccessFile randomAccessFile, byte[] bArr) throws IOException {
        HashMap map = new HashMap();
        try {
            map.put(3, getApkContentDigestsFromSignatureBlock(ApkSigningBlockUtils.findSignature(randomAccessFile, APK_SIGNATURE_SCHEME_V3_BLOCK_ID).signatureBlock));
        } catch (SignatureNotFoundException unused) {
        }
        try {
            map.put(2, getApkContentDigestsFromSignatureBlock(ApkSigningBlockUtils.findSignature(randomAccessFile, APK_SIGNATURE_SCHEME_V2_BLOCK_ID).signatureBlock));
        } catch (SignatureNotFoundException unused2) {
        }
        if (bArr != null) {
            HashMap map2 = new HashMap();
            map2.put(4, computeSha256Digest(bArr));
            map.put(1, map2);
        }
        return map;
    }

    private static Map<Integer, byte[]> getApkContentDigestsFromSignatureBlock(ByteBuffer byteBuffer) throws IOException {
        HashMap map = new HashMap();
        ByteBuffer lengthPrefixedSlice = ApkSigningBlockUtils.getLengthPrefixedSlice(byteBuffer);
        while (lengthPrefixedSlice.hasRemaining()) {
            ByteBuffer lengthPrefixedSlice2 = ApkSigningBlockUtils.getLengthPrefixedSlice(ApkSigningBlockUtils.getLengthPrefixedSlice(ApkSigningBlockUtils.getLengthPrefixedSlice(lengthPrefixedSlice)));
            while (lengthPrefixedSlice2.hasRemaining()) {
                ByteBuffer lengthPrefixedSlice3 = ApkSigningBlockUtils.getLengthPrefixedSlice(lengthPrefixedSlice2);
                int i = lengthPrefixedSlice3.getInt();
                map.put(Integer.valueOf(ApkSigningBlockUtils.getSignatureAlgorithmContentDigestAlgorithm(i)), ApkSigningBlockUtils.readLengthPrefixedByteArray(lengthPrefixedSlice3));
            }
        }
        return map;
    }

    private static Map<Integer, byte[]> getSignatureSchemeDigests(Map<Integer, Map<Integer, byte[]>> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<Integer, Map<Integer, byte[]>> entry : map.entrySet()) {
            map2.put(entry.getKey(), encodeApkContentDigests(getApkDigests(entry.getValue())));
        }
        return map2;
    }

    private static List<Pair<Integer, byte[]>> getApkDigests(Map<Integer, byte[]> map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<Integer, byte[]> entry : map.entrySet()) {
            arrayList.add(Pair.create(entry.getKey(), entry.getValue()));
        }
        arrayList.sort(Comparator.comparing(new Function() { // from class: android.util.apk.-$$Lambda$SourceStampVerifier$BMDJKLGY9DHUWLFuev5Pw-uZfrY
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SourceStampVerifier.lambda$getApkDigests$0((Pair) obj);
            }
        }));
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Integer lambda$getApkDigests$0(Pair pair) {
        return (Integer) pair.first;
    }

    private static byte[] getSourceStampCertificateDigest(StrictJarFile strictJarFile) throws IOException {
        ZipEntry zipEntryFindEntry = strictJarFile.findEntry(SOURCE_STAMP_CERTIFICATE_HASH_ZIP_ENTRY_NAME);
        if (zipEntryFindEntry == null) {
            return null;
        }
        return Streams.readFully(strictJarFile.getInputStream(zipEntryFindEntry));
    }

    private static byte[] getManifestBytes(StrictJarFile strictJarFile) throws IOException {
        ZipEntry zipEntryFindEntry = strictJarFile.findEntry("META-INF/MANIFEST.MF");
        if (zipEntryFindEntry == null) {
            return null;
        }
        return Streams.readFully(strictJarFile.getInputStream(zipEntryFindEntry));
    }

    private static byte[] encodeApkContentDigests(List<Pair<Integer, byte[]>> list) {
        Iterator<Pair<Integer, byte[]>> it = list.iterator();
        int length = 0;
        while (it.hasNext()) {
            length += it.next().second.length + 12;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        for (Pair<Integer, byte[]> pair : list) {
            byte[] bArr = pair.second;
            byteBufferAllocate.putInt(bArr.length + 8);
            byteBufferAllocate.putInt(pair.first.intValue());
            byteBufferAllocate.putInt(bArr.length);
            byteBufferAllocate.put(bArr);
        }
        return byteBufferAllocate.array();
    }

    private static byte[] computeSha256Digest(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to find SHA-256", e);
        }
    }

    private static void closeApkJar(StrictJarFile strictJarFile) {
        if (strictJarFile == null) {
            return;
        }
        try {
            strictJarFile.close();
        } catch (IOException e) {
            Slog.e(TAG, "Could not close APK jar", e);
        }
    }
}
