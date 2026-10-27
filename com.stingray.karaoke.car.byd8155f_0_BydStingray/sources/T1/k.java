package T1;

import A.C0022k;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.bydauto.audio.tsimpl.LocalListenerHelper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.AttachedSurfaceControl;
import android.view.KeyEvent;
import android.view.SurfaceControl;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.window.SurfaceSyncGroup;
import com.stingray.karaoke.car.player.PlayerService;
import d5.C1077F;
import d5.I;
import d5.d0;
import d5.s0;
import e2.W;
import e2.c0;
import g1.AbstractC1274a;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h5.C1373E;
import h5.C1375a;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.L0;
import io.sentry.N0;
import io.sentry.T1;
import io.sentry.V1;
import io.sentry.android.core.B;
import io.sentry.android.replay.ReplayIntegration;
import java.io.BufferedWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;
import k3.A0;
import k3.C0;
import k3.C1;
import k3.C1669t0;
import k3.C1671u0;
import k3.C1672v;
import k3.C1674w;
import k3.C1679y0;
import k3.C1681z0;
import k3.E0;
import k3.E1;
import k3.I0;
import k3.InterfaceC1670u;
import k3.J0;
import k3.Q0;
import k3.RunnableC1666s;
import k3.X0;
import k3.n1;
import k3.u1;
import k3.y1;
import k3.z1;
import l3.C1764u;
import l3.T;
import n2.K;
import n2.Q;
import u2.C2492C;
import u2.C2521y;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class k implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8504m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f8505n;
    public final /* synthetic */ Object o;

    public /* synthetic */ k(Window window, Window.Callback callback, io.sentry.android.core.r rVar, B b10) {
        this.l = 4;
        this.f8504m = window;
        this.f8505n = callback;
        this.o = rVar;
    }

    /* JADX WARN: Type inference failed for: r2v40, types: [java.lang.Object, q2.l] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Object, m7.f] */
    @Override // java.lang.Runnable
    public final void run() {
        String str;
        y1 y1Var;
        switch (this.l) {
            case 0:
                P6.a aVar = (P6.a) this.f8504m;
                AbstractC1274a abstractC1274a = (AbstractC1274a) this.f8505n;
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) this.o;
                aVar.getClass();
                try {
                    s sVarV = com.bumptech.glide.d.v(aVar.f5412m);
                    if (sVarV == null) {
                        throw new RuntimeException("EmojiCompat font provider not available on this device.");
                    }
                    r rVar = (r) ((h) sVarV.f8493b);
                    synchronized (rVar.o) {
                        rVar.f8517q = threadPoolExecutor;
                        break;
                    }
                    ((h) sVarV.f8493b).a(new l(abstractC1274a, threadPoolExecutor));
                    return;
                } catch (Throwable th) {
                    abstractC1274a.V(th);
                    threadPoolExecutor.shutdown();
                    return;
                }
            case 1:
                ((LocalListenerHelper) this.f8504m).lambda$asyncNotifyListener$1((Consumer) this.f8505n, (LocalListenerHelper.ErrorPolicy) this.o);
                return;
            case 2:
                h5.y yVar = (h5.y) this.f8504m;
                C1373E c1373e = (C1373E) this.f8505n;
                try {
                    try {
                        c1373e.n(((h5.q) this.o).apply(l9.l.N(yVar)));
                        return;
                    } catch (Throwable th2) {
                        c1373e.m(th2);
                        return;
                    }
                } catch (Error | RuntimeException e10) {
                    c1373e.m(e10);
                    return;
                } catch (CancellationException unused) {
                    c1373e.cancel(false);
                    return;
                } catch (ExecutionException e11) {
                    e = e11;
                    Throwable cause = e.getCause();
                    if (cause != null) {
                        e = cause;
                    }
                    c1373e.m(e);
                    return;
                }
            case 3:
                C1373E c1373e2 = (C1373E) this.f8504m;
                C0 c02 = (C0) this.f8505n;
                C1 c1 = (C1) this.o;
                try {
                    if (c1373e2.l instanceof C1375a) {
                        return;
                    }
                    c02.run();
                    c1373e2.l(c1);
                    return;
                } catch (Throwable th3) {
                    c1373e2.m(th3);
                    return;
                }
            case 4:
                Window window = (Window) this.f8504m;
                View viewPeekDecorView = window.peekDecorView();
                if (viewPeekDecorView != null) {
                    window.setCallback((Window.Callback) this.f8505n);
                    viewPeekDecorView.getViewTreeObserver().addOnDrawListener(new io.sentry.android.core.internal.util.f(viewPeekDecorView, (io.sentry.android.core.r) this.o));
                    return;
                }
                return;
            case 5:
                Bitmap bitmap = (Bitmap) this.f8504m;
                io.sentry.android.replay.s sVar = (io.sentry.android.replay.s) this.f8505n;
                A7.m.f(sVar, "this$0");
                io.sentry.android.replay.viewhierarchy.d dVar = (io.sentry.android.replay.viewhierarchy.d) this.o;
                Canvas canvas = new Canvas(bitmap);
                canvas.setMatrix((Matrix) sVar.f16431u.getValue());
                dVar.a(new C0022k(sVar, bitmap, canvas, 15));
                Bitmap bitmapCopy = bitmap.copy(Bitmap.Config.ARGB_8888, false);
                ReplayIntegration replayIntegration = sVar.o;
                A7.m.e(bitmapCopy, "screenshot");
                replayIntegration.o(bitmapCopy);
                Bitmap bitmap2 = sVar.f16434x;
                if (bitmap2 != null) {
                    bitmap2.recycle();
                }
                sVar.f16434x = bitmapCopy;
                sVar.f16432v.set(false);
                bitmap.recycle();
                return;
            case 6:
                Runnable runnable = (Runnable) this.f8504m;
                A7.m.f(runnable, "$task");
                F1 f12 = (F1) this.f8505n;
                A7.m.f(f12, "$options");
                String str2 = (String) this.o;
                A7.m.f(str2, "$taskName");
                try {
                    runnable.run();
                    return;
                } catch (Throwable th4) {
                    f12.getLogger().s(EnumC1495p1.ERROR, "Failed to execute task ".concat(str2), th4);
                    return;
                }
            case 7:
                io.sentry.android.replay.util.a aVar2 = (io.sentry.android.replay.util.a) this.f8504m;
                A7.m.f(aVar2, "this$0");
                L0 l02 = (L0) this.f8505n;
                io.sentry.android.replay.i iVar = (io.sentry.android.replay.i) this.o;
                StringWriter stringWriter = new StringWriter();
                aVar2.f16439m.getSerializer().f(l02, new BufferedWriter(stringWriter));
                iVar.i(aVar2.l, stringWriter.toString());
                return;
            case 8:
                io.sentry.cache.f fVar = (io.sentry.cache.f) this.f8504m;
                T1 t12 = (T1) this.f8505n;
                if (t12 != null) {
                    fVar.k(t12, "trace.json");
                    return;
                }
                fVar.getClass();
                h3.y yVar2 = ((N0) this.o).f15905q;
                T1 t13 = new T1((io.sentry.protocol.t) yVar2.f15499m, (V1) yVar2.f15500n, "default", null, null);
                t13.f15963t = "auto";
                fVar.k(t13, "trace.json");
                return;
            case 9:
                C1672v c1672v = (C1672v) this.f8504m;
                c1672v.Z0();
                InterfaceC1670u interfaceC1670u = c1672v.c;
                s0 s0VarS = (!interfaceC1670u.v() ? z1.f18172b : interfaceC1670u.P0()).f18173a.iterator();
                while (true) {
                    boolean zHasNext = s0VarS.hasNext();
                    str = (String) this.f8505n;
                    if (zHasNext) {
                        y1Var = (y1) s0VarS.next();
                        if (y1Var.f18167a != 0 || !y1Var.f18168b.equals(str)) {
                        }
                    } else {
                        y1Var = null;
                    }
                }
                if (y1Var != null) {
                    c1672v.Z0();
                    z1 z1VarP0 = !interfaceC1670u.v() ? z1.f18172b : interfaceC1670u.P0();
                    z1VarP0.getClass();
                    if (z1VarP0.f18173a.contains(y1Var)) {
                        h5.y yVarY0 = c1672v.Y0(new y1(str, (Bundle) this.o), Bundle.EMPTY);
                        yVarY0.b(new h5.t(yVarY0, 0, new B2.q(str, 6)), h5.r.l);
                        return;
                    }
                    return;
                }
                return;
            case 10:
                E0 e02 = (E0) this.f8504m;
                I0 i02 = (I0) e02.c;
                C1679y0 c1679y0 = (C1679y0) this.f8505n;
                if (i02.g(c1679y0)) {
                    i02.a((KeyEvent) this.o, false);
                } else {
                    T t10 = c1679y0.f18160a;
                    t10.getClass();
                    Q0 q02 = i02.f17801h;
                    q02.getClass();
                    q02.G(1, new J0(q02, 7), t10, true);
                }
                e02.f17756b = null;
                return;
            case 11:
                PlayerService playerService = (PlayerService) this.f8504m;
                playerService.getClass();
                C1671u0 c1671u0 = (C1671u0) this.f8505n;
                HashMap map = c1671u0.f18128g;
                A0 a02 = (A0) this.o;
                if (!map.containsKey(a02)) {
                    PlayerService playerService2 = c1671u0.f18124a;
                    C1669t0 c1669t0 = new C1669t0(playerService2, a02);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("androidx.media3.session.MediaNotificationManager", true);
                    E1 e12 = a02.f17714a.f17803j;
                    playerService2.getClass();
                    e12.getClass();
                    Bundle bundle2 = Bundle.EMPTY;
                    AbstractC1343A.q();
                    Bundle bundle3 = new Bundle(bundle);
                    Looper mainLooper = Looper.getMainLooper();
                    mainLooper.getClass();
                    C1674w c1674w = new C1674w(mainLooper);
                    AbstractC1343A.K(new Handler(mainLooper), new RunnableC1666s(c1674w, new C1672v(playerService2, e12, bundle3, c1669t0, mainLooper, c1674w, e12.f17758a.n() ? new io.sentry.internal.debugmeta.c(new k2.i(playerService2)) : null), 0));
                    map.put(a02, c1674w);
                    c1674w.b(new io.sentry.android.replay.p(c1671u0, c1674w, c1669t0, a02, 3), c1671u0.f18126e);
                }
                a02.f17714a.f17814w = new e7.f(playerService, 23);
                return;
            case 12:
                I0 i03 = (I0) this.f8504m;
                if (i03.h()) {
                    return;
                }
                u1 u1Var = i03.f17811t;
                X0 x02 = (X0) this.f8505n;
                C1681z0 c1681z0 = (C1681z0) this.o;
                x02.getClass();
                n1.i(u1Var, c1681z0);
                return;
            case 13:
                C1764u c1764u = (C1764u) this.f8504m;
                c1764u.getClass();
                AttachedSurfaceControl rootSurfaceControl = ((SurfaceView) this.f8505n).getRootSurfaceControl();
                if (rootSurfaceControl == null) {
                    return;
                }
                SurfaceSyncGroup surfaceSyncGroupK = M.p.k();
                c1764u.f18608m = surfaceSyncGroupK;
                AbstractC1346c.j(surfaceSyncGroupK.add(rootSurfaceControl, new io.sentry.android.ndk.e(2)));
                ((A2.l) this.o).run();
                rootSurfaceControl.applyTransactionOnDraw(new SurfaceControl.Transaction());
                return;
            case 14:
                K k10 = (K) this.f8504m;
                k10.getClass();
                d0 d0VarH = ((C1077F) this.f8505n).h();
                o2.e eVar = k10.c;
                W w10 = eVar.f20409r;
                w10.getClass();
                H0.b bVar = eVar.o;
                bVar.getClass();
                bVar.f2766n = I.w(d0VarH);
                if (!d0VarH.isEmpty()) {
                    bVar.f2768q = (C2492C) d0VarH.get(0);
                    C2492C c2492c = (C2492C) this.o;
                    c2492c.getClass();
                    bVar.f2769r = c2492c;
                }
                if (((C2492C) bVar.f2767p) == null) {
                    bVar.f2767p = H0.b.v(w10, (I) bVar.f2766n, (C2492C) bVar.f2768q, (c0) bVar.f2765m);
                }
                bVar.P(w10.t0());
                return;
            case 15:
                o2.e eVar2 = (o2.e) ((Q) this.f8504m).f19579m.f10764i;
                Pair pair = (Pair) this.f8505n;
                eVar2.s(((Integer) pair.first).intValue(), (C2492C) pair.second, (C2521y) this.o);
                return;
            case 16:
                o2.e eVar3 = (o2.e) ((Q) this.f8504m).f19579m.f10764i;
                Pair pair2 = (Pair) this.f8505n;
                eVar3.c(((Integer) pair2.first).intValue(), (C2492C) pair2.second, (Exception) this.o);
                return;
            default:
                q2.k kVar = (q2.k) this.f8504m;
                this.f8505n.c(kVar.f21209a, kVar.f21210b, (Exception) this.o);
                return;
        }
    }

    public /* synthetic */ k(Object obj, Object obj2, Object obj3, int i7) {
        this.l = i7;
        this.f8504m = obj;
        this.f8505n = obj2;
        this.o = obj3;
    }

    public /* synthetic */ k(C1671u0 c1671u0, C1672v c1672v, String str, Bundle bundle) {
        this.l = 9;
        this.f8504m = c1672v;
        this.f8505n = str;
        this.o = bundle;
    }
}
