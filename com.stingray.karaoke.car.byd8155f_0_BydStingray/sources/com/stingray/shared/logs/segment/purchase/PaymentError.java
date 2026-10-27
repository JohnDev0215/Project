package com.stingray.shared.logs.segment.purchase;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/stingray/shared/logs/segment/purchase/PaymentError;", "", "<init>", "(Ljava/lang/String;I)V", "None", "Unknown", "ClientInvalid", "PaymentCanceled", "PaymentInvalid", "ProductNotAvailable", "AndroidStoreNotAvailable", "AndroidBillingAccountDisconnected", "ProductAlreadyOwn", "ReceiptExpired", "AcknowledgeFailed", "shared_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PaymentError {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ PaymentError[] $VALUES;
    public static final PaymentError None = new PaymentError("None", 0);
    public static final PaymentError Unknown = new PaymentError("Unknown", 1);
    public static final PaymentError ClientInvalid = new PaymentError("ClientInvalid", 2);
    public static final PaymentError PaymentCanceled = new PaymentError("PaymentCanceled", 3);
    public static final PaymentError PaymentInvalid = new PaymentError("PaymentInvalid", 4);
    public static final PaymentError ProductNotAvailable = new PaymentError("ProductNotAvailable", 5);
    public static final PaymentError AndroidStoreNotAvailable = new PaymentError("AndroidStoreNotAvailable", 6);
    public static final PaymentError AndroidBillingAccountDisconnected = new PaymentError("AndroidBillingAccountDisconnected", 7);
    public static final PaymentError ProductAlreadyOwn = new PaymentError("ProductAlreadyOwn", 8);
    public static final PaymentError ReceiptExpired = new PaymentError("ReceiptExpired", 9);
    public static final PaymentError AcknowledgeFailed = new PaymentError("AcknowledgeFailed", 10);

    private static final /* synthetic */ PaymentError[] $values() {
        return new PaymentError[]{None, Unknown, ClientInvalid, PaymentCanceled, PaymentInvalid, ProductNotAvailable, AndroidStoreNotAvailable, AndroidBillingAccountDisconnected, ProductAlreadyOwn, ReceiptExpired, AcknowledgeFailed};
    }

    static {
        PaymentError[] paymentErrorArr$values = $values();
        $VALUES = paymentErrorArr$values;
        $ENTRIES = r.e(paymentErrorArr$values);
    }

    private PaymentError(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static PaymentError valueOf(String str) {
        return (PaymentError) Enum.valueOf(PaymentError.class, str);
    }

    public static PaymentError[] values() {
        return (PaymentError[]) $VALUES.clone();
    }
}
