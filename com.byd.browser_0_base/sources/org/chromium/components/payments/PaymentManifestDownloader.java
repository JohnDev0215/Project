package org.chromium.components.payments;

import J.N;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.url.GURL;
import org.chromium.url.Origin;

/* JADX INFO: loaded from: classes3.dex */
public final class PaymentManifestDownloader {
    public long a;
    public CSPCheckerBridge b;

    public interface ManifestDownloadCallback {
        @CalledByNative
        void onManifestDownloadFailure(String str);

        @CalledByNative
        void onPaymentMethodManifestDownloadSuccess(GURL gurl, Origin origin, String str);

        @CalledByNative
        void onWebAppManifestDownloadSuccess(String str);
    }

    public final void a(Origin origin, GURL gurl, ManifestDownloadCallback manifestDownloadCallback) {
        Object obj = ThreadUtils.a;
        N.MCkwfWHm(this.a, this, origin, gurl, manifestDownloadCallback);
    }
}
