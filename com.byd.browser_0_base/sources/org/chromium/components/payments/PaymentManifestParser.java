package org.chromium.components.payments;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public class PaymentManifestParser {
    public long a;

    public interface ManifestParseCallback {
        @CalledByNative
        void onManifestParseFailure();

        @CalledByNative
        void onPaymentMethodManifestParseSuccess(GURL[] gurlArr, GURL[] gurlArr2);

        @CalledByNative
        void onWebAppManifestParseSuccess(WebAppManifestSection[] webAppManifestSectionArr);
    }

    @CalledByNative
    public static void addFingerprintToSection(WebAppManifestSection[] webAppManifestSectionArr, int i, int i2, byte[] bArr) {
        webAppManifestSectionArr[i].c[i2] = bArr;
    }

    @CalledByNative
    public static void addSectionToManifest(WebAppManifestSection[] webAppManifestSectionArr, int i, String str, long j, int i2) {
        webAppManifestSectionArr[i] = new WebAppManifestSection(i2, str, j);
    }

    @CalledByNative
    public static boolean addUrl(GURL[] gurlArr, int i, String str) {
        if (!new GURL(str).b) {
            return false;
        }
        gurlArr[i] = new GURL(str);
        return true;
    }

    @CalledByNative
    public static WebAppManifestSection[] createManifest(int i) {
        return new WebAppManifestSection[i];
    }

    @CalledByNative
    public static GURL[] createUrlArray(int i) {
        return new GURL[i];
    }
}
