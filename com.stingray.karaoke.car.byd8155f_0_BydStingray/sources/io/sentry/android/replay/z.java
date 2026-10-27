package io.sentry.android.replay;

import android.graphics.Bitmap;
import android.view.View;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import java.io.Closeable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import n7.AbstractC1985B;
import n7.AbstractC1998m;
import n7.AbstractC2004s;

/* JADX INFO: loaded from: classes.dex */
public final class z implements Closeable, f {
    public final F1 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ReplayIntegration f16464m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final e7.f f16465n;
    public final AtomicBoolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ArrayList f16466p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public s f16467q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ScheduledFuture f16468r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final m7.n f16469s;

    public z(F1 f12, ReplayIntegration replayIntegration, e7.f fVar) {
        A7.m.f(fVar, "mainLooperHandler");
        this.l = f12;
        this.f16464m = replayIntegration;
        this.f16465n = fVar;
        this.o = new AtomicBoolean(false);
        this.f16466p = new ArrayList();
        this.f16469s = h6.r.o(C1450a.f16336w);
    }

    @Override // io.sentry.android.replay.f
    public final void b(View view, boolean z6) {
        s sVar;
        A7.m.f(view, "root");
        ArrayList arrayList = this.f16466p;
        if (z6) {
            arrayList.add(new WeakReference(view));
            s sVar2 = this.f16467q;
            if (sVar2 != null) {
                sVar2.a(view);
                return;
            }
            return;
        }
        s sVar3 = this.f16467q;
        if (sVar3 != null) {
            sVar3.b(view);
        }
        AbstractC2004s.M(arrayList, new y(view, 0));
        WeakReference weakReference = (WeakReference) AbstractC1998m.i0(arrayList);
        View view2 = weakReference != null ? (View) weakReference.get() : null;
        if (view2 == null || view.equals(view2) || (sVar = this.f16467q) == null) {
            return;
        }
        sVar.a(view2);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        i();
        ScheduledExecutorService scheduledExecutorService = (ScheduledExecutorService) this.f16469s.getValue();
        A7.m.e(scheduledExecutorService, "capturer");
        AbstractC1985B.n(scheduledExecutorService, this.l);
    }

    public final void g(u uVar) {
        ScheduledFuture<?> scheduledFutureScheduleAtFixedRate;
        A7.m.f(uVar, "recorderConfig");
        if (this.o.getAndSet(true)) {
            return;
        }
        ReplayIntegration replayIntegration = this.f16464m;
        F1 f12 = this.l;
        this.f16467q = new s(uVar, f12, this.f16465n, replayIntegration);
        ScheduledExecutorService scheduledExecutorService = (ScheduledExecutorService) this.f16469s.getValue();
        A7.m.e(scheduledExecutorService, "capturer");
        long j3 = 1000 / ((long) uVar.f16437e);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        A2.l lVar = new A2.l(this, 20);
        A7.m.f(timeUnit, "unit");
        try {
            scheduledFutureScheduleAtFixedRate = scheduledExecutorService.scheduleAtFixedRate(new A2.m(lVar, 11, f12), 100L, j3, timeUnit);
        } catch (Throwable th) {
            f12.getLogger().s(EnumC1495p1.ERROR, "Failed to submit task WindowRecorder.capture to executor", th);
            scheduledFutureScheduleAtFixedRate = null;
        }
        this.f16468r = scheduledFutureScheduleAtFixedRate;
    }

    public final void i() {
        ArrayList<WeakReference> arrayList = this.f16466p;
        for (WeakReference weakReference : arrayList) {
            s sVar = this.f16467q;
            if (sVar != null) {
                sVar.b((View) weakReference.get());
            }
        }
        s sVar2 = this.f16467q;
        if (sVar2 != null) {
            WeakReference weakReference2 = sVar2.f16427q;
            sVar2.b(weakReference2 != null ? (View) weakReference2.get() : null);
            WeakReference weakReference3 = sVar2.f16427q;
            if (weakReference3 != null) {
                weakReference3.clear();
            }
            Bitmap bitmap = sVar2.f16434x;
            if (bitmap != null) {
                bitmap.recycle();
            }
            sVar2.f16433w.set(false);
            ScheduledExecutorService scheduledExecutorService = (ScheduledExecutorService) sVar2.f16426p.getValue();
            A7.m.e(scheduledExecutorService, "recorder");
            AbstractC1985B.n(scheduledExecutorService, sVar2.f16424m);
        }
        arrayList.clear();
        this.f16467q = null;
        ScheduledFuture scheduledFuture = this.f16468r;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.f16468r = null;
        this.o.set(false);
    }
}
