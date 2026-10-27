package defpackage;

import android.os.Handler;
import android.os.SystemClock;
import defpackage.jx;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: OfflineDetector.java */
/* JADX INFO: loaded from: classes2.dex */
public final class iq1 implements jx.c, ApplicationStatus.d {
    public jx f;
    public boolean g;
    public boolean h;
    public boolean i;
    public final Callback<Boolean> l;
    public final Callback<Boolean> m;
    public long o;
    public long p;
    public boolean q;
    public long r;
    public int n = ApplicationStatus.getStateForApplication();
    public final Handler j = new Handler();
    public final long e = 10000;
    public final ql0 k = new ql0(18, this);

    public iq1(ko0 ko0Var, jq1 jq1Var) {
        this.l = ko0Var;
        this.m = jq1Var;
        ApplicationStatus.e(this);
        if (this.n == 1) {
            this.o = SystemClock.elapsedRealtime();
        }
        this.f = new jx(this);
    }

    public final void a() {
        Handler handler = this.j;
        ql0 ql0Var = this.k;
        handler.removeCallbacks(ql0Var);
        if (this.n != 1) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.o;
        long jElapsedRealtime2 = SystemClock.elapsedRealtime() - this.p;
        long jElapsedRealtime3 = SystemClock.elapsedRealtime();
        long j = this.r;
        long j2 = jElapsedRealtime3 - j;
        long j3 = 2000 - jElapsedRealtime;
        long j4 = 2000 - jElapsedRealtime2;
        long j5 = j > 0 ? this.e - j2 : 0L;
        fa1.i("OfflineDetector", "Running updateState mConnectivityDetectorInitialized: %b, mTimeWhenLastForegrounded: %d, getElapsedTime: %d, mTimeWhenLastOfflineNotificationReceived: %d, mTimeWhenLastOnline: %d, mApplicationState: %d, mIsOfflineLastReportedByConnectivityDetector: %b, mIsEffectivelyOffline: %b", Boolean.valueOf(this.q), Long.valueOf(this.o), Long.valueOf(SystemClock.elapsedRealtime()), Long.valueOf(this.p), Long.valueOf(this.r), Integer.valueOf(this.n), Boolean.valueOf(this.i), Boolean.valueOf(this.g));
        fa1.i("OfflineDetector", "updateState(): timeSinceLastForeground: %d, timeSinceOfflineNotificationReceived: %d, timeSinceLastOnline: %d, timeNeededForForeground: %d, timeNeededForOffline: %d", Long.valueOf(jElapsedRealtime), Long.valueOf(jElapsedRealtime2), Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(j4));
        if (!this.i || (j3 <= 0 && j4 <= 0 && j5 <= 0)) {
            ql0Var.run();
        } else {
            handler.postDelayed(ql0Var, Math.max(Math.max(j3, j4), j5));
        }
    }

    @Override // org.chromium.base.ApplicationStatus.d
    public final void v(int i) {
        if (this.n == i) {
            return;
        }
        this.n = i;
        this.m.onResult(Boolean.valueOf(i == 1));
        if (this.n == 1) {
            this.o = SystemClock.elapsedRealtime();
        }
        a();
    }
}
