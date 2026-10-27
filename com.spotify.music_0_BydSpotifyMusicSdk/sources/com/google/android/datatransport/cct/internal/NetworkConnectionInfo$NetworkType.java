package com.google.android.datatransport.cct.internal;

import android.util.SparseArray;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r15v0 com.google.android.datatransport.cct.internal.NetworkConnectionInfo$NetworkType, still in use, count: 1, list:
  (r15v0 com.google.android.datatransport.cct.internal.NetworkConnectionInfo$NetworkType) from 0x0122: INVOKE 
  (r0v17 android.util.SparseArray)
  (0 int)
  (r15v0 com.google.android.datatransport.cct.internal.NetworkConnectionInfo$NetworkType)
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
public final class NetworkConnectionInfo$NetworkType {
    /* JADX INFO: Fake field, exist only in values array */
    MOBILE(0),
    /* JADX INFO: Fake field, exist only in values array */
    WIFI(1),
    /* JADX INFO: Fake field, exist only in values array */
    MOBILE_MMS(2),
    /* JADX INFO: Fake field, exist only in values array */
    MOBILE_SUPL(3),
    /* JADX INFO: Fake field, exist only in values array */
    MOBILE_DUN(4),
    /* JADX INFO: Fake field, exist only in values array */
    MOBILE_HIPRI(5),
    /* JADX INFO: Fake field, exist only in values array */
    WIMAX(6),
    /* JADX INFO: Fake field, exist only in values array */
    PROXY(7),
    /* JADX INFO: Fake field, exist only in values array */
    VPN(8),
    /* JADX INFO: Fake field, exist only in values array */
    MOBILE_EMERGENCY(9),
    /* JADX INFO: Fake field, exist only in values array */
    PROXY(10),
    /* JADX INFO: Fake field, exist only in values array */
    VPN(11),
    /* JADX INFO: Fake field, exist only in values array */
    MOBILE_EMERGENCY(12),
    /* JADX INFO: Fake field, exist only in values array */
    PROXY(13),
    /* JADX INFO: Fake field, exist only in values array */
    VPN(14),
    /* JADX INFO: Fake field, exist only in values array */
    MOBILE_EMERGENCY(15),
    /* JADX INFO: Fake field, exist only in values array */
    PROXY(16),
    /* JADX INFO: Fake field, exist only in values array */
    VPN(17),
    NONE(-1);

    public static final SparseArray e;
    private final int value;

    static {
        NetworkConnectionInfo$NetworkType networkConnectionInfo$NetworkType = NONE;
        SparseArray sparseArray = new SparseArray();
        e = sparseArray;
        sparseArray.put(0, networkConnectionInfo$NetworkType);
        sparseArray.put(1, networkConnectionInfo$NetworkType);
        sparseArray.put(2, networkConnectionInfo$NetworkType);
        sparseArray.put(3, networkConnectionInfo$NetworkType);
        sparseArray.put(4, networkConnectionInfo$NetworkType);
        sparseArray.put(5, networkConnectionInfo$NetworkType);
        sparseArray.put(6, networkConnectionInfo$NetworkType);
        sparseArray.put(7, networkConnectionInfo$NetworkType);
        sparseArray.put(8, networkConnectionInfo$NetworkType);
        sparseArray.put(9, networkConnectionInfo$NetworkType);
        sparseArray.put(10, networkConnectionInfo$NetworkType);
        sparseArray.put(11, networkConnectionInfo$NetworkType);
        sparseArray.put(12, networkConnectionInfo$NetworkType);
        sparseArray.put(13, networkConnectionInfo$NetworkType);
        sparseArray.put(14, networkConnectionInfo$NetworkType);
        sparseArray.put(15, networkConnectionInfo$NetworkType);
        sparseArray.put(16, networkConnectionInfo$NetworkType);
        sparseArray.put(17, networkConnectionInfo$NetworkType);
        sparseArray.put(-1, networkConnectionInfo$NetworkType);
    }

    public NetworkConnectionInfo$NetworkType(int i) {
        this.value = i;
    }

    public static NetworkConnectionInfo$NetworkType valueOf(String str) {
        return (NetworkConnectionInfo$NetworkType) Enum.valueOf(NetworkConnectionInfo$NetworkType.class, str);
    }

    public static NetworkConnectionInfo$NetworkType[] values() {
        return (NetworkConnectionInfo$NetworkType[]) k.clone();
    }

    public final int a() {
        return this.value;
    }
}
