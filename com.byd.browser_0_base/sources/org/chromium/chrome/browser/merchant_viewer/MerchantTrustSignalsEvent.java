package org.chromium.chrome.browser.merchant_viewer;

import java.util.ArrayList;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class MerchantTrustSignalsEvent {
    public final String a;
    public final long b;

    @CalledByNative
    public MerchantTrustSignalsEvent(String str, long j) {
        this.a = str;
        this.b = j;
    }

    @CalledByNative
    public static MerchantTrustSignalsEvent createEventAndAddToList(List<MerchantTrustSignalsEvent> list, String str, long j) {
        MerchantTrustSignalsEvent merchantTrustSignalsEvent = new MerchantTrustSignalsEvent(str, j);
        list.add(merchantTrustSignalsEvent);
        return merchantTrustSignalsEvent;
    }

    @CalledByNative
    public static List<MerchantTrustSignalsEvent> createEventList() {
        return new ArrayList();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof MerchantTrustSignalsEvent)) {
            return false;
        }
        MerchantTrustSignalsEvent merchantTrustSignalsEvent = (MerchantTrustSignalsEvent) obj;
        return this.a.equals(merchantTrustSignalsEvent.a) && this.b == merchantTrustSignalsEvent.b;
    }

    public final int hashCode() {
        String str = this.a;
        return ((527 + (str == null ? 0 : str.hashCode())) * 31) + ((int) this.b);
    }
}
