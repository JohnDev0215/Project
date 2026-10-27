package com.google.android.datatransport.cct.internal;

import android.util.SparseArray;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r13v0 com.google.android.datatransport.cct.internal.NetworkConnectionInfo$MobileSubtype, still in use, count: 1, list:
  (r13v0 com.google.android.datatransport.cct.internal.NetworkConnectionInfo$MobileSubtype) from 0x0147: INVOKE 
  (r0v19 android.util.SparseArray)
  (1 int)
  (r13v0 com.google.android.datatransport.cct.internal.NetworkConnectionInfo$MobileSubtype)
 VIRTUAL call: android.util.SparseArray.put(int, java.lang.Object):void A[MD:(int, E):void (c)]
	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:252)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:180)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes.dex */
public final class NetworkConnectionInfo$MobileSubtype {
    UNKNOWN_MOBILE_SUBTYPE(0),
    /* JADX INFO: Fake field, exist only in values array */
    GPRS(1),
    /* JADX INFO: Fake field, exist only in values array */
    EDGE(2),
    /* JADX INFO: Fake field, exist only in values array */
    UMTS(3),
    /* JADX INFO: Fake field, exist only in values array */
    CDMA(4),
    /* JADX INFO: Fake field, exist only in values array */
    EVDO_0(5),
    /* JADX INFO: Fake field, exist only in values array */
    EVDO_A(6),
    /* JADX INFO: Fake field, exist only in values array */
    LTE_CA(7),
    /* JADX INFO: Fake field, exist only in values array */
    TD_SCDMA(8),
    /* JADX INFO: Fake field, exist only in values array */
    IWLAN(9),
    /* JADX INFO: Fake field, exist only in values array */
    LTE_CA(10),
    /* JADX INFO: Fake field, exist only in values array */
    TD_SCDMA(11),
    /* JADX INFO: Fake field, exist only in values array */
    IWLAN(12),
    /* JADX INFO: Fake field, exist only in values array */
    LTE_CA(13),
    /* JADX INFO: Fake field, exist only in values array */
    TD_SCDMA(14),
    /* JADX INFO: Fake field, exist only in values array */
    IWLAN(15),
    /* JADX INFO: Fake field, exist only in values array */
    LTE_CA(16),
    /* JADX INFO: Fake field, exist only in values array */
    TD_SCDMA(17),
    /* JADX INFO: Fake field, exist only in values array */
    IWLAN(18),
    /* JADX INFO: Fake field, exist only in values array */
    LTE_CA(19),
    COMBINED(100);

    public static final SparseArray k;
    private final int value;

    static {
        NetworkConnectionInfo$MobileSubtype networkConnectionInfo$MobileSubtype = UNKNOWN_MOBILE_SUBTYPE;
        SparseArray sparseArray = new SparseArray();
        k = sparseArray;
        sparseArray.put(0, networkConnectionInfo$MobileSubtype);
        sparseArray.put(1, networkConnectionInfo$MobileSubtype);
        sparseArray.put(2, networkConnectionInfo$MobileSubtype);
        sparseArray.put(3, networkConnectionInfo$MobileSubtype);
        sparseArray.put(4, networkConnectionInfo$MobileSubtype);
        sparseArray.put(5, networkConnectionInfo$MobileSubtype);
        sparseArray.put(6, networkConnectionInfo$MobileSubtype);
        sparseArray.put(7, networkConnectionInfo$MobileSubtype);
        sparseArray.put(8, networkConnectionInfo$MobileSubtype);
        sparseArray.put(9, networkConnectionInfo$MobileSubtype);
        sparseArray.put(10, networkConnectionInfo$MobileSubtype);
        sparseArray.put(11, networkConnectionInfo$MobileSubtype);
        sparseArray.put(12, networkConnectionInfo$MobileSubtype);
        sparseArray.put(13, networkConnectionInfo$MobileSubtype);
        sparseArray.put(14, networkConnectionInfo$MobileSubtype);
        sparseArray.put(15, networkConnectionInfo$MobileSubtype);
        sparseArray.put(16, networkConnectionInfo$MobileSubtype);
        sparseArray.put(17, networkConnectionInfo$MobileSubtype);
        sparseArray.put(18, networkConnectionInfo$MobileSubtype);
        sparseArray.put(19, networkConnectionInfo$MobileSubtype);
    }

    public NetworkConnectionInfo$MobileSubtype(int i) {
        this.value = i;
    }

    public static NetworkConnectionInfo$MobileSubtype valueOf(String str) {
        return (NetworkConnectionInfo$MobileSubtype) Enum.valueOf(NetworkConnectionInfo$MobileSubtype.class, str);
    }

    public static NetworkConnectionInfo$MobileSubtype[] values() {
        return (NetworkConnectionInfo$MobileSubtype[]) l.clone();
    }

    public final int a() {
        return this.value;
    }
}
