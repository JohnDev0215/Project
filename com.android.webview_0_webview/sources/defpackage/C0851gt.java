package defpackage;

import android.os.Looper;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.util.Log;
import org.chromium.base.TraceEvent;

/* JADX INFO: renamed from: gt, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0851gt extends C0795ft implements MessageQueue.IdleHandler {
    public long c;
    public long d;
    public int e;
    public int f;
    public int g;
    public boolean h;

    public C0851gt(AbstractC0739et abstractC0739et) {
        super(null);
    }

    @Override // defpackage.C0795ft
    public final void a(String str) {
        if (this.g == 0) {
            TraceEvent.A("Looper.queueIdle");
        }
        this.d = SystemClock.elapsedRealtime();
        c();
        super.a(str);
    }

    @Override // defpackage.C0795ft
    public final void b(String str) {
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.d;
        if (jElapsedRealtime > 16) {
            String str2 = "observed a task that took " + jElapsedRealtime + "ms: " + str;
            TraceEvent.I("TraceEvent.LooperMonitor:IdleStats", str2);
            Log.println(5, "TraceEvent_LooperMonitor", str2);
        }
        super.b(str);
        c();
        this.e++;
        this.g++;
    }

    public final void c() {
        if (TraceEvent.t && !this.h) {
            this.c = SystemClock.elapsedRealtime();
            Looper.myQueue().addIdleHandler(this);
            this.h = true;
        } else {
            if (!this.h || TraceEvent.t) {
                return;
            }
            Looper.myQueue().removeIdleHandler(this);
            this.h = false;
        }
    }

    @Override // android.os.MessageQueue.IdleHandler
    public final boolean queueIdle() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (this.c == 0) {
            this.c = jElapsedRealtime;
        }
        long j = jElapsedRealtime - this.c;
        this.f++;
        TraceEvent.x("Looper.queueIdle", this.g + " tasks since last idle.");
        if (j > 48) {
            String str = this.e + " tasks and " + this.f + " idles processed so far, " + this.g + " tasks bursted and " + j + "ms elapsed since last idle";
            TraceEvent.I("TraceEvent.LooperMonitor:IdleStats", str);
            Log.println(3, "TraceEvent_LooperMonitor", str);
        }
        this.c = jElapsedRealtime;
        this.g = 0;
        return true;
    }
}
