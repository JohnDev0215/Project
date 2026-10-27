package defpackage;

import android.app.ActivityManager;
import android.os.SystemClock;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.chromium.base.MemoryPressureListener;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Ct {
    public static final Ct i = new Ct(60000);
    public final int a;
    public Integer c;
    public boolean d;
    public boolean e;
    public int b = 0;
    public Nu f = new Nu() { // from class: yt
        @Override // defpackage.Nu
        public Object get() {
            long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            try {
                ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
                ActivityManager.getMyMemoryState(runningAppProcessInfo);
                It.b("Android.MemoryPressureMonitor.GetMyMemoryState.Succeeded.Time", Ct.a(jElapsedRealtimeNanos), 1, 1000000, 50);
                return Ct.b(runningAppProcessInfo.lastTrimLevel);
            } catch (Exception unused) {
                It.b("Android.MemoryPressureMonitor.GetMyMemoryState.Failed.Time", Ct.a(jElapsedRealtimeNanos), 1, 1000000, 50);
                return null;
            }
        }
    };
    public InterfaceC1789xt g = new InterfaceC1789xt() { // from class: zt
        @Override // defpackage.InterfaceC1789xt
        public void a(int i2) {
            Iterator it = MemoryPressureListener.a.iterator();
            while (true) {
                Ws ws = (Ws) it;
                if (!ws.hasNext()) {
                    return;
                } else {
                    ((InterfaceC1789xt) ws.next()).a(i2);
                }
            }
        }
    };
    public final Runnable h = new Runnable(this) { // from class: At
        public final Ct s;

        {
            this.s = this;
        }

        @Override // java.lang.Runnable
        public void run() {
            Integer num;
            Ct ct = this.s;
            ct.d = false;
            Integer num2 = ct.c;
            if (num2 != null && ct.b != num2.intValue()) {
                int iIntValue = ct.c.intValue();
                ct.c = null;
                ct.d(iIntValue);
            } else if (ct.e && ct.b == 2 && (num = (Integer) ct.f.get()) != null) {
                ct.d(num.intValue());
            }
        }
    };

    public Ct(int i2) {
        this.a = i2;
    }

    public static int a(long j) {
        return (int) Math.min(TimeUnit.NANOSECONDS.toMicros(SystemClock.elapsedRealtimeNanos() - j), 2147483647L);
    }

    public static Integer b(int i2) {
        if (i2 >= 80 || i2 == 15) {
            return 2;
        }
        return i2 >= 40 ? 1 : null;
    }

    public void c(int i2) {
        ThreadUtils.b();
        if (this.d) {
            this.c = Integer.valueOf(i2);
        } else {
            d(i2);
        }
    }

    public final void d(int i2) {
        ThreadUtils.c().postDelayed(this.h, 60000);
        this.d = true;
        this.b = i2;
        this.g.a(i2);
    }
}
