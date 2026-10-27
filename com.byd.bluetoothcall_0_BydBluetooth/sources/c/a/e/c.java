package c.a.e;

import android.text.TextUtils;
import com.byd.constant.StateConstant$CONNECT_STATE;
import com.byd.type_enum.Sync$SyncState;
import com.byd.type_enum.Sync$SyncType;

/* JADX INFO: compiled from: Util.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: compiled from: Util.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2635a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f2636b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        static final /* synthetic */ int[] f2637c;

        static {
            int[] iArr = new int[Sync$SyncType.values().length];
            f2637c = iArr;
            try {
                iArr[Sync$SyncType.WHOLE_OF_THEM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2637c[Sync$SyncType.PHONE_BOOK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2637c[Sync$SyncType.ALL_OF_CALL_LOG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2637c[Sync$SyncType.DIALED_LOG.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2637c[Sync$SyncType.MISSED_LOG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2637c[Sync$SyncType.RECEIVED_LOG.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[Sync$SyncState.values().length];
            f2636b = iArr2;
            try {
                iArr2[Sync$SyncState.SYNC_START.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f2636b[Sync$SyncState.SYNC_ING.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2636b[Sync$SyncState.SYNC_END.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr3 = new int[StateConstant$CONNECT_STATE.values().length];
            f2635a = iArr3;
            try {
                iArr3[StateConstant$CONNECT_STATE.DISCONNECTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f2635a[StateConstant$CONNECT_STATE.NOT_PAIRD.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f2635a[StateConstant$CONNECT_STATE.NOT_CONNECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f2635a[StateConstant$CONNECT_STATE.NO_PHONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f2635a[StateConstant$CONNECT_STATE.NO_MEDIA.ordinal()] = 5;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f2635a[StateConstant$CONNECT_STATE.CONNECTING.ordinal()] = 6;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f2635a[StateConstant$CONNECT_STATE.CONNECTED.ordinal()] = 7;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    public static int a(Sync$SyncType sync$SyncType) {
        int i = a.f2637c[sync$SyncType.ordinal()];
        if (i == 2) {
            return 1;
        }
        if (i == 3) {
            return 2;
        }
        if (i == 4) {
            return 3;
        }
        if (i != 5) {
            return i != 6 ? 0 : 5;
        }
        return 4;
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(str);
        int i = 0;
        while (i < sb.length() && i < sb.length() - 4) {
            int i2 = i + 1;
            sb = sb.replace(i, i2, "*");
            i = i2;
        }
        return sb.toString();
    }

    public static String c(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (i <= 0 || str.length() <= i) {
            return str;
        }
        try {
            return str.substring(str.offsetByCodePoints(0, 0), str.offsetByCodePoints(0, i));
        } catch (Exception unused) {
            return "";
        }
    }

    public static String d(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }
}
