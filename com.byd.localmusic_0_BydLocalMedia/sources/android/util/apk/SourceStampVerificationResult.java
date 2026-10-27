package android.util.apk;

import java.security.cert.Certificate;

/* JADX INFO: loaded from: classes3.dex */
public final class SourceStampVerificationResult {
    private final Certificate mCertificate;
    private final boolean mPresent;
    private final boolean mVerified;

    private SourceStampVerificationResult(boolean z, boolean z2, Certificate certificate) {
        this.mPresent = z;
        this.mVerified = z2;
        this.mCertificate = certificate;
    }

    public boolean isPresent() {
        return this.mPresent;
    }

    public boolean isVerified() {
        return this.mVerified;
    }

    public Certificate getCertificate() {
        return this.mCertificate;
    }

    public static SourceStampVerificationResult notPresent() {
        return new SourceStampVerificationResult(false, false, null);
    }

    public static SourceStampVerificationResult verified(Certificate certificate) {
        return new SourceStampVerificationResult(true, true, certificate);
    }

    public static SourceStampVerificationResult notVerified() {
        return new SourceStampVerificationResult(true, false, null);
    }
}
