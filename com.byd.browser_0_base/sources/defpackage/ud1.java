package defpackage;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: compiled from: MediaRouterActiveScanThrottlingHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class ud1 {
    public final Handler a = new Handler(Looper.getMainLooper());
    public final Runnable b;
    public long c;
    public long d;
    public boolean e;

    public ud1(Runnable runnable) {
        this.b = runnable;
    }

    public final void a(long j, boolean z) {
        if (z) {
            long j2 = this.d;
            if (j2 - j >= 30000) {
                return;
            }
            this.c = Math.max(this.c, (j + 30000) - j2);
            this.e = true;
        }
    }
}
