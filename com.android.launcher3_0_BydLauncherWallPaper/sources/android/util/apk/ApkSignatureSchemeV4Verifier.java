package android.util.apk;

import android.os.incremental.IncrementalManager;
import android.os.incremental.V4Signature;
import android.util.Pair;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class ApkSignatureSchemeV4Verifier {
    public static VerifiedSigner extractCertificates(String str) throws SignatureNotFoundException, SecurityException {
        File file = new File(str);
        byte[] bArrUnsafeGetFileSignature = IncrementalManager.unsafeGetFileSignature(file.getAbsolutePath());
        if (bArrUnsafeGetFileSignature == null || bArrUnsafeGetFileSignature.length == 0) {
            throw new SignatureNotFoundException("Failed to obtain signature bytes from IncFS.");
        }
        try {
            V4Signature from = V4Signature.readFrom(bArrUnsafeGetFileSignature);
            if (!from.isVersionSupported()) {
                throw new SecurityException("v4 signature version " + from.version + " is not supported");
            }
            V4Signature.HashingInfo hashingInfoFromByteArray = V4Signature.HashingInfo.fromByteArray(from.hashingInfo);
            V4Signature.SigningInfo signingInfoFromByteArray = V4Signature.SigningInfo.fromByteArray(from.signingInfo);
            return verifySigner(signingInfoFromByteArray, V4Signature.getSigningData(file.length(), hashingInfoFromByteArray, signingInfoFromByteArray));
        } catch (IOException e) {
            throw new SignatureNotFoundException("Failed to read V4 signature.", e);
        }
    }

    private static VerifiedSigner verifySigner(V4Signature.SigningInfo signingInfo, byte[] bArr) throws SecurityException {
        if (!ApkSigningBlockUtils.isSupportedSignatureAlgorithm(signingInfo.signatureAlgorithmId)) {
            throw new SecurityException("No supported signatures found");
        }
        int i = signingInfo.signatureAlgorithmId;
        byte[] bArr2 = signingInfo.signature;
        byte[] bArr3 = signingInfo.publicKey;
        byte[] bArr4 = signingInfo.certificate;
        String signatureAlgorithmJcaKeyAlgorithm = ApkSigningBlockUtils.getSignatureAlgorithmJcaKeyAlgorithm(i);
        Pair<String, ? extends AlgorithmParameterSpec> signatureAlgorithmJcaSignatureAlgorithm = ApkSigningBlockUtils.getSignatureAlgorithmJcaSignatureAlgorithm(i);
        String str = signatureAlgorithmJcaSignatureAlgorithm.first;
        AlgorithmParameterSpec algorithmParameterSpec = (AlgorithmParameterSpec) signatureAlgorithmJcaSignatureAlgorithm.second;
        try {
            PublicKey publicKeyGeneratePublic = KeyFactory.getInstance(signatureAlgorithmJcaKeyAlgorithm).generatePublic(new X509EncodedKeySpec(bArr3));
            Signature signature = Signature.getInstance(str);
            signature.initVerify(publicKeyGeneratePublic);
            if (algorithmParameterSpec != null) {
                signature.setParameter(algorithmParameterSpec);
            }
            signature.update(bArr);
            if (!signature.verify(bArr2)) {
                throw new SecurityException(str + " signature did not verify");
            }
            try {
                try {
                    VerbatimX509Certificate verbatimX509Certificate = new VerbatimX509Certificate((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr4)), bArr4);
                    if (!Arrays.equals(bArr3, verbatimX509Certificate.getPublicKey().getEncoded())) {
                        throw new SecurityException("Public key mismatch between certificate and signature record");
                    }
                    return new VerifiedSigner(new Certificate[]{verbatimX509Certificate}, signingInfo.apkDigest);
                } catch (CertificateException e) {
                    throw new SecurityException("Failed to decode certificate", e);
                }
            } catch (CertificateException e2) {
                throw new RuntimeException("Failed to obtain X.509 CertificateFactory", e2);
            }
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException e3) {
            throw new SecurityException("Failed to verify " + str + " signature", e3);
        }
    }

    public static class VerifiedSigner {
        public byte[] apkDigest;
        public final Certificate[] certs;

        public VerifiedSigner(Certificate[] certificateArr, byte[] bArr) {
            this.certs = certificateArr;
            this.apkDigest = bArr;
        }
    }
}
