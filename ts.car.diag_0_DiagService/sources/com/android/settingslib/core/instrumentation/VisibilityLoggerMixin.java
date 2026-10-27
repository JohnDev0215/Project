package com.android.settingslib.core.instrumentation;

import android.os.SystemClock;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class VisibilityLoggerMixin implements com.android.settingslib.core.lifecycle.a, com.android.settingslib.core.lifecycle.b.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f1343c;
    private a d;
    private int e;
    private long f;
    private long g;

    @Override // com.android.settingslib.core.lifecycle.b.a
    public void a() {
        this.f = SystemClock.elapsedRealtime();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        this.f = 0L;
        if (this.d == null || this.f1343c == 0) {
            return;
        }
        this.d.a(null, this.f1343c, (int) (SystemClock.elapsedRealtime() - this.g));
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        if (this.d == null || this.f1343c == 0) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        this.g = jElapsedRealtime;
        long j = this.f;
        if (j == 0) {
            this.d.b(null, this.e, this.f1343c, 0);
        } else {
            this.d.b(null, this.e, this.f1343c, (int) (jElapsedRealtime - j));
        }
    }
}
