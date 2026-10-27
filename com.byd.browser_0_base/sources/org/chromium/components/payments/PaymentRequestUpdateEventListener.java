package org.chromium.components.payments;

import defpackage.e70;
import defpackage.gq0;
import defpackage.ky1;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public interface PaymentRequestUpdateEventListener {
    boolean a(ky1 ky1Var);

    @CalledByNative
    boolean changePaymentMethodFromInvokedApp(String str, String str2);

    @CalledByNative
    default boolean changeShippingAddress(ByteBuffer byteBuffer) {
        return a(ky1.d(new e70(new gq0(byteBuffer, new ArrayList()))));
    }

    @CalledByNative
    boolean changeShippingOptionFromInvokedApp(String str);
}
