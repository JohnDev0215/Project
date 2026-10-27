package org.chromium.components.payments;

import J.N;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes3.dex */
public class PaymentManifestWebDataService {
    public long a;

    public interface PaymentManifestWebDataServiceCallback {
        @CalledByNative
        void onPaymentMethodManifestFetched(String[] strArr);

        @CalledByNative
        void onPaymentWebAppManifestFetched(WebAppManifestSection[] webAppManifestSectionArr);
    }

    public PaymentManifestWebDataService(WebContents webContents) {
        if (webContents == null || webContents.isDestroyed()) {
            return;
        }
        this.a = N.MIc9v664(this, webContents);
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
    public static WebAppManifestSection[] createManifest(int i) {
        return new WebAppManifestSection[i];
    }

    @CalledByNative
    public static byte[][] getFingerprintsFromSection(WebAppManifestSection webAppManifestSection) {
        return webAppManifestSection.c;
    }

    @CalledByNative
    public static String getIdFromSection(WebAppManifestSection webAppManifestSection) {
        return webAppManifestSection.a;
    }

    @CalledByNative
    public static long getMinVersionFromSection(WebAppManifestSection webAppManifestSection) {
        return webAppManifestSection.b;
    }
}
