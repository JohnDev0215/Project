package defpackage;

import android.net.TrafficStats;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ThreadStatsUid.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a93 {
    public static final Method a;
    public static final Method b;

    static {
        try {
            a = TrafficStats.class.getMethod("setThreadStatsUid", Integer.TYPE);
            b = TrafficStats.class.getMethod("clearThreadStatsUid", new Class[0]);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new uv("Unable to get TrafficStats methods", e);
        }
    }
}
