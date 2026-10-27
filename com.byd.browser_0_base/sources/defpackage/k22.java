package defpackage;

import java.util.HashMap;

/* JADX INFO: compiled from: PlayerUserActionRecorder.java */
/* JADX INFO: loaded from: classes3.dex */
public final class k22 {
    public static final HashMap a = new HashMap();

    public static boolean a(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        HashMap map = a;
        return jCurrentTimeMillis - (map.get(str) != null ? ((Long) map.get(str)).longValue() : 0L) < 500;
    }
}
