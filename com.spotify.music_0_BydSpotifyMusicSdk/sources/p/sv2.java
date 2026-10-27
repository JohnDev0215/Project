package p;

import android.content.Context;
import android.os.PowerManager;

/* JADX INFO: loaded from: classes.dex */
public abstract class sv2 {
    public static final /* synthetic */ int a = 0;

    static {
        v03.i(vc1.d("WakeLocks"), "tagWithPrefix(\"WakeLocks\")");
    }

    public static final PowerManager.WakeLock a(Context context, String str) {
        v03.j(context, "context");
        v03.j(str, "tag");
        Object systemService = context.getApplicationContext().getSystemService("power");
        v03.g(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        String strConcat = "WorkManager: ".concat(str);
        PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) systemService).newWakeLock(1, strConcat);
        synchronized (tv2.a) {
        }
        v03.i(wakeLockNewWakeLock, "wakeLock");
        return wakeLockNewWakeLock;
    }
}
