package org.chromium.chrome.browser.merchant_viewer;

import J.N;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes2.dex */
public class MerchantTrustSignalsEventStorage {
    public long a;

    public MerchantTrustSignalsEventStorage(Profile profile) {
        N.MEbfTMCO(this, profile);
    }

    @CalledByNative
    public final void setNativePtr(long j) {
        this.a = j;
    }
}
