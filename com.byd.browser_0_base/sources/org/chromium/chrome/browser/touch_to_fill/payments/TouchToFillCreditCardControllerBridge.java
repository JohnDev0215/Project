package org.chromium.chrome.browser.touch_to_fill.payments;

import J.N;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class TouchToFillCreditCardControllerBridge implements TouchToFillCreditCardComponent$Delegate {
    public long a;

    public TouchToFillCreditCardControllerBridge(long j) {
        this.a = j;
    }

    @CalledByNative
    public static TouchToFillCreditCardControllerBridge create(long j) {
        return new TouchToFillCreditCardControllerBridge(j);
    }

    @Override // org.chromium.chrome.browser.touch_to_fill.payments.TouchToFillCreditCardComponent$Delegate
    public final void a(boolean z) {
        long j = this.a;
        if (j != 0) {
            N.MII5Oyfk(j, z);
        }
    }

    @Override // org.chromium.chrome.browser.touch_to_fill.payments.TouchToFillCreditCardComponent$Delegate
    public final void b(String str, boolean z) {
        long j = this.a;
        if (j != 0) {
            N.MaRNRQ4B(j, str, z);
        }
    }

    @Override // org.chromium.chrome.browser.touch_to_fill.payments.TouchToFillCreditCardComponent$Delegate
    public final void c() {
        long j = this.a;
        if (j != 0) {
            N.MXKb8OZq(j);
        }
    }

    @Override // org.chromium.chrome.browser.touch_to_fill.payments.TouchToFillCreditCardComponent$Delegate
    public final void d() {
        long j = this.a;
        if (j != 0) {
            N.MyVN_G2B(j);
        }
    }

    @CalledByNative
    public final void onNativeDestroyed() {
        this.a = 0L;
    }
}
