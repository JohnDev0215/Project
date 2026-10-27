package p;

import android.os.SystemClock;

/* JADX INFO: loaded from: classes.dex */
public final class lr2 implements yt {
    public final /* synthetic */ int a;

    public final long a() {
        switch (this.a) {
            case 0:
                return SystemClock.elapsedRealtime();
            default:
                return System.currentTimeMillis();
        }
    }
}
