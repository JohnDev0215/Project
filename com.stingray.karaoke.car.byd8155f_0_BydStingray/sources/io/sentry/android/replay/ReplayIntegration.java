package io.sentry.android.replay;

import A.C0044v0;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewTreeObserver;
import h8.AbstractC1386c;
import h8.AbstractC1387d;
import io.sentry.C1489n1;
import io.sentry.C1524x0;
import io.sentry.E;
import io.sentry.EnumC1495p1;
import io.sentry.F;
import io.sentry.F1;
import io.sentry.G;
import io.sentry.I0;
import io.sentry.J0;
import io.sentry.J1;
import io.sentry.S;
import io.sentry.Z;
import java.io.Closeable;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import n7.AbstractC1985B;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0001\u0007¨\u0006\b"}, d2 = {"Lio/sentry/android/replay/ReplayIntegration;", "Lio/sentry/Z;", "Ljava/io/Closeable;", "", "Lio/sentry/J0;", "Landroid/content/ComponentCallbacks;", "Lio/sentry/G;", "io/sentry/android/replay/l", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ReplayIntegration implements Z, Closeable, J0, ComponentCallbacks, G {
    public final Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.transport.d f16316m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public F1 f16317n;
    public E o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public z f16318p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public io.sentry.android.replay.gestures.b f16319q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final m7.n f16320r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Object f16321s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final AtomicBoolean f16322t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final AtomicBoolean f16323u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public io.sentry.android.replay.capture.l f16324v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public I0 f16325w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final e7.f f16326x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public u f16327y;

    public ReplayIntegration(Context context) {
        io.sentry.transport.d dVar = io.sentry.transport.d.f16897a;
        Context applicationContext = context.getApplicationContext();
        this.l = applicationContext != null ? applicationContext : context;
        this.f16316m = dVar;
        this.f16320r = h6.r.o(C1450a.o);
        this.f16321s = h6.r.n(m7.g.f19257m, C1450a.f16329p);
        this.f16322t = new AtomicBoolean(false);
        this.f16323u = new AtomicBoolean(false);
        this.f16325w = C1524x0.f16986m;
        this.f16326x = new e7.f(16, (byte) 0);
    }

    @Override // io.sentry.J0
    public final void b() {
        s sVar;
        if (this.f16322t.get() && this.f16323u.get()) {
            z zVar = this.f16318p;
            if (zVar != null && (sVar = zVar.f16467q) != null) {
                sVar.f16433w.set(false);
                WeakReference weakReference = sVar.f16427q;
                sVar.b(weakReference != null ? (View) weakReference.get() : null);
            }
            io.sentry.android.replay.capture.l lVar = this.f16324v;
            if (lVar != null) {
                lVar.b();
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        io.sentry.transport.m mVarD;
        if (this.f16322t.get()) {
            F1 f12 = this.f16317n;
            if (f12 == null) {
                A7.m.j("options");
                throw null;
            }
            f12.getConnectionStatusProvider().p(this);
            E e10 = this.o;
            if (e10 != null && (mVarD = e10.d()) != null) {
                mVarD.o.remove(this);
            }
            try {
                this.l.unregisterComponentCallbacks(this);
            } catch (Throwable unused) {
            }
            stop();
            z zVar = this.f16318p;
            if (zVar != null) {
                zVar.close();
            }
            this.f16318p = null;
        }
    }

    @Override // io.sentry.J0
    public final void g(Boolean bool) {
        if (this.f16322t.get() && this.f16323u.get()) {
            io.sentry.protocol.t tVar = io.sentry.protocol.t.f16774m;
            io.sentry.android.replay.capture.l lVar = this.f16324v;
            if (tVar.equals(lVar != null ? ((io.sentry.android.replay.capture.c) lVar).i() : null)) {
                F1 f12 = this.f16317n;
                if (f12 != null) {
                    f12.getLogger().k(EnumC1495p1.DEBUG, "Replay id is not set, not capturing for event", new Object[0]);
                    return;
                } else {
                    A7.m.j("options");
                    throw null;
                }
            }
            io.sentry.android.replay.capture.l lVar2 = this.f16324v;
            if (lVar2 != null) {
                lVar2.a(bool.equals(Boolean.TRUE), new S0.p(this, 22));
            }
            io.sentry.android.replay.capture.l lVar3 = this.f16324v;
            this.f16324v = lVar3 != null ? lVar3.g() : null;
        }
    }

    @Override // io.sentry.G
    public final void i(F f10) {
        A7.m.f(f10, "status");
        if (this.f16324v instanceof io.sentry.android.replay.capture.o) {
            if (f10 == F.DISCONNECTED) {
                b();
            } else {
                j();
            }
        }
    }

    @Override // io.sentry.J0
    public final void j() {
        s sVar;
        View view;
        ViewTreeObserver viewTreeObserver;
        if (this.f16322t.get() && this.f16323u.get()) {
            io.sentry.android.replay.capture.l lVar = this.f16324v;
            if (lVar != null) {
                ((io.sentry.android.replay.capture.c) lVar).p(AbstractC1985B.f());
            }
            z zVar = this.f16318p;
            if (zVar == null || (sVar = zVar.f16467q) == null) {
                return;
            }
            WeakReference weakReference = sVar.f16427q;
            if (weakReference != null && (view = (View) weakReference.get()) != null && (viewTreeObserver = view.getViewTreeObserver()) != null) {
                viewTreeObserver.addOnDrawListener(sVar);
            }
            sVar.f16433w.set(true);
        }
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        Double d;
        E e10 = E.f15834a;
        this.f16317n = f12;
        Double d10 = f12.getExperimental().f16913a.f15880a;
        if ((d10 == null || d10.doubleValue() <= 0.0d) && ((d = f12.getExperimental().f16913a.f15881b) == null || d.doubleValue() <= 0.0d)) {
            f12.getLogger().k(EnumC1495p1.INFO, "Session replay is disabled, no sample rate specified", new Object[0]);
            return;
        }
        this.o = e10;
        this.f16318p = new z(f12, this, this.f16326x);
        this.f16319q = new io.sentry.android.replay.gestures.b(f12, this);
        this.f16322t.set(true);
        f12.getConnectionStatusProvider().f(this);
        io.sentry.transport.m mVarD = e10.d();
        if (mVarD != null) {
            mVarD.o.add(this);
        }
        try {
            this.l.registerComponentCallbacks(this);
        } catch (Throwable th) {
            f12.getLogger().s(EnumC1495p1.INFO, "ComponentCallbacks is not available, orientation changes won't be handled by Session replay", th);
        }
        n7.z.c("Replay");
        C1489n1.s().g("maven:io.sentry:sentry-android-replay");
        F1 f13 = this.f16317n;
        if (f13 == null) {
            A7.m.j("options");
            throw null;
        }
        S executorService = f13.getExecutorService();
        A7.m.e(executorService, "options.executorService");
        F1 f14 = this.f16317n;
        if (f14 == null) {
            A7.m.j("options");
            throw null;
        }
        try {
            executorService.submit(new A2.m(new A2.l(this, 19), 12, f14));
        } catch (Throwable th2) {
            f14.getLogger().s(EnumC1495p1.ERROR, "Failed to submit task ReplayIntegration.finalize_previous_replay to executor", th2);
        }
    }

    public final void m(String str) {
        File[] fileArrListFiles;
        io.sentry.protocol.t tVarI;
        F1 f12 = this.f16317n;
        if (f12 == null) {
            A7.m.j("options");
            throw null;
        }
        String cacheDirPath = f12.getCacheDirPath();
        if (cacheDirPath == null || (fileArrListFiles = new File(cacheDirPath).listFiles()) == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            String name = file.getName();
            A7.m.e(name, "name");
            if (Q8.o.z0(name, "replay_", false)) {
                io.sentry.android.replay.capture.l lVar = this.f16324v;
                if (lVar == null || (tVarI = ((io.sentry.android.replay.capture.c) lVar).i()) == null) {
                    tVarI = io.sentry.protocol.t.f16774m;
                    A7.m.e(tVarI, "EMPTY_ID");
                }
                String string = tVarI.toString();
                A7.m.e(string, "replayId.toString()");
                if (!Q8.h.C0(name, string, false) && (Q8.h.M0(str) || !Q8.h.C0(name, str, false))) {
                    AbstractC1386c.g(file);
                }
            }
        }
    }

    public final void o(Bitmap bitmap) {
        A7.z zVar = new A7.z();
        E e10 = this.o;
        if (e10 != null) {
            e10.l(new k(zVar, 0));
        }
        io.sentry.android.replay.capture.l lVar = this.f16324v;
        if (lVar != null) {
            lVar.c(new C0044v0(bitmap, zVar, this, 5));
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        A7.m.f(configuration, "newConfig");
        if (this.f16322t.get() && this.f16323u.get()) {
            z zVar = this.f16318p;
            if (zVar != null) {
                zVar.i();
            }
            F1 f12 = this.f16317n;
            if (f12 == null) {
                A7.m.j("options");
                throw null;
            }
            J1 j12 = f12.getExperimental().f16913a;
            A7.m.e(j12, "options.experimental.sessionReplay");
            u uVarF = AbstractC1387d.f(this.l, j12);
            this.f16327y = uVarF;
            io.sentry.android.replay.capture.l lVar = this.f16324v;
            if (lVar != null) {
                lVar.e(uVarF);
            }
            z zVar2 = this.f16318p;
            if (zVar2 != null) {
                u uVar = this.f16327y;
                if (uVar != null) {
                    zVar2.g(uVar);
                } else {
                    A7.m.j("recorderConfig");
                    throw null;
                }
            }
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override // io.sentry.J0
    /* JADX INFO: renamed from: q, reason: from getter */
    public final I0 getF16325w() {
        return this.f16325w;
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.Object, m7.f] */
    @Override // io.sentry.J0
    public final void start() {
        io.sentry.android.replay.capture.l fVar;
        if (this.f16322t.get()) {
            if (this.f16323u.getAndSet(true)) {
                F1 f12 = this.f16317n;
                if (f12 != null) {
                    f12.getLogger().k(EnumC1495p1.DEBUG, "Session replay is already being recorded, not starting a new one", new Object[0]);
                    return;
                } else {
                    A7.m.j("options");
                    throw null;
                }
            }
            m7.n nVar = this.f16320r;
            io.sentry.util.g gVar = (io.sentry.util.g) nVar.getValue();
            F1 f13 = this.f16317n;
            if (f13 == null) {
                A7.m.j("options");
                throw null;
            }
            Double d = f13.getExperimental().f16913a.f15880a;
            A7.m.f(gVar, "<this>");
            boolean z6 = d != null && d.doubleValue() >= gVar.b();
            if (!z6) {
                F1 f14 = this.f16317n;
                if (f14 == null) {
                    A7.m.j("options");
                    throw null;
                }
                Double d10 = f14.getExperimental().f16913a.f15881b;
                if (d10 == null || d10.doubleValue() <= 0.0d) {
                    F1 f15 = this.f16317n;
                    if (f15 != null) {
                        f15.getLogger().k(EnumC1495p1.INFO, "Session replay is not started, full session was not sampled and onErrorSampleRate is not specified", new Object[0]);
                        return;
                    } else {
                        A7.m.j("options");
                        throw null;
                    }
                }
            }
            F1 f16 = this.f16317n;
            if (f16 == null) {
                A7.m.j("options");
                throw null;
            }
            J1 j12 = f16.getExperimental().f16913a;
            A7.m.e(j12, "options.experimental.sessionReplay");
            this.f16327y = AbstractC1387d.f(this.l, j12);
            if (z6) {
                F1 f17 = this.f16317n;
                if (f17 == null) {
                    A7.m.j("options");
                    throw null;
                }
                fVar = new io.sentry.android.replay.capture.o(f17, this.o, this.f16316m, null, 8);
            } else {
                F1 f18 = this.f16317n;
                if (f18 == null) {
                    A7.m.j("options");
                    throw null;
                }
                fVar = new io.sentry.android.replay.capture.f(f18, this.o, this.f16316m, (io.sentry.util.g) nVar.getValue());
            }
            this.f16324v = fVar;
            u uVar = this.f16327y;
            if (uVar == null) {
                A7.m.j("recorderConfig");
                throw null;
            }
            fVar.f(uVar, 0, new io.sentry.protocol.t((UUID) null), null);
            z zVar = this.f16318p;
            if (zVar != null) {
                u uVar2 = this.f16327y;
                if (uVar2 == null) {
                    A7.m.j("recorderConfig");
                    throw null;
                }
                zVar.g(uVar2);
            }
            z zVar2 = this.f16318p;
            ?? r12 = this.f16321s;
            if (zVar2 != null) {
                ((o) r12.getValue()).getClass();
                n nVar2 = o.f16416b;
                z zVar3 = this.f16318p;
                A7.m.d(zVar3, "null cannot be cast to non-null type io.sentry.android.replay.OnRootViewsChangedListener");
                nVar2.add(zVar3);
            }
            ((o) r12.getValue()).getClass();
            o.f16416b.add(this.f16319q);
        }
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, m7.f] */
    @Override // io.sentry.J0
    public final void stop() {
        if (this.f16322t.get()) {
            AtomicBoolean atomicBoolean = this.f16323u;
            if (atomicBoolean.get()) {
                z zVar = this.f16318p;
                ?? r22 = this.f16321s;
                if (zVar != null) {
                    ((o) r22.getValue()).getClass();
                    n nVar = o.f16416b;
                    z zVar2 = this.f16318p;
                    A7.m.d(zVar2, "null cannot be cast to non-null type io.sentry.android.replay.OnRootViewsChangedListener");
                    nVar.remove(zVar2);
                }
                ((o) r22.getValue()).getClass();
                o.f16416b.remove(this.f16319q);
                z zVar3 = this.f16318p;
                if (zVar3 != null) {
                    zVar3.i();
                }
                io.sentry.android.replay.gestures.b bVar = this.f16319q;
                if (bVar != null) {
                    ArrayList arrayList = bVar.f16400n;
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        View view = (View) ((WeakReference) it.next()).get();
                        if (view != null) {
                            bVar.a(view);
                        }
                    }
                    arrayList.clear();
                }
                io.sentry.android.replay.capture.l lVar = this.f16324v;
                if (lVar != null) {
                    lVar.stop();
                }
                atomicBoolean.set(false);
                io.sentry.android.replay.capture.l lVar2 = this.f16324v;
                if (lVar2 != null) {
                    io.sentry.android.replay.capture.c cVar = (io.sentry.android.replay.capture.c) lVar2;
                    AbstractC1985B.n(cVar.m(), cVar.f16348a);
                }
                this.f16324v = null;
            }
        }
    }
}
