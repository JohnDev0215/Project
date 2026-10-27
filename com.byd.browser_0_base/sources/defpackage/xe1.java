package defpackage;

import android.os.SystemClock;
import androidx.appcompat.widget.a;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: MemoryPurgeManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class xe1 implements ApplicationStatus.d {
    public static final xe1 i = new xe1();
    public boolean e;
    public long f = -1;
    public boolean g;
    public boolean h;

    @Override // org.chromium.base.ApplicationStatus.d
    public final void v(int i2) {
        if (i2 == 0 || i2 == 1 || i2 == 2) {
            if (this.f != -1 && this.h) {
                oj2.X(SystemClock.elapsedRealtime() - this.f, "Android.ApplicationState.TimeInBackgroundBeforeForegroundedAgain");
            }
            this.h = true;
            this.f = -1L;
            return;
        }
        if (i2 == 3 && this.f == -1) {
            this.f = SystemClock.elapsedRealtime();
            Object obj = ThreadUtils.a;
            if (this.g) {
                return;
            }
            ThreadUtils.c().postDelayed(new a(6, this), 240000L);
            this.g = true;
        }
    }
}
