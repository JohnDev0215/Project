package A2;

import B5.C0083g;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.hardware.bydauto.audio.tsimpl.LocalListenerHelper;
import android.media.session.MediaController;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.util.LongSparseArray;
import android.view.Choreographer;
import android.view.Surface;
import androidx.media3.ui.PlayerView;
import h.C1335g;
import h.x;
import h2.AbstractC1346c;
import h2.s;
import h5.C1373E;
import h5.C1375a;
import h5.y;
import i0.C1398b;
import i0.ViewOnAttachStateChangeListenerC1400d;
import io.sentry.AbstractC1453b1;
import io.sentry.C1478k;
import io.sentry.C1489n1;
import io.sentry.D;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.P1;
import io.sentry.S;
import io.sentry.SpotlightIntegration;
import io.sentry.U;
import io.sentry.Y1;
import io.sentry.android.core.P;
import io.sentry.protocol.C1498c;
import io.sentry.protocol.t;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.zip.GZIPOutputStream;
import k3.A0;
import k3.C1;
import k3.C1628b0;
import k3.C1646h0;
import k3.C1650j0;
import k3.C1671u0;
import k3.C1672v;
import k3.C1679y0;
import k3.G0;
import k3.I0;
import k3.InterfaceC1651k;
import k3.InterfaceC1656m0;
import k3.Q0;
import k3.m1;
import k3.u1;
import l3.BinderC1769z;
import l3.C1736A;
import l3.C1767x;
import l3.InterfaceC1750f;
import l3.L;
import l3.O;
import l3.T;
import n2.SurfaceHolderCallbackC1920u;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class m implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f345m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f346n;

    public /* synthetic */ m(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f345m = obj;
        this.f346n = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C1 c1;
        switch (this.l) {
            case 0:
                o oVar = (o) this.f345m;
                SurfaceTexture surfaceTexture = oVar.f360r;
                Surface surface = oVar.f361s;
                SurfaceTexture surfaceTexture2 = (SurfaceTexture) this.f346n;
                Surface surface2 = new Surface(surfaceTexture2);
                oVar.f360r = surfaceTexture2;
                oVar.f361s = surface2;
                Iterator it = oVar.l.iterator();
                while (it.hasNext()) {
                    ((SurfaceHolderCallbackC1920u) it.next()).l.u1(surface2);
                }
                if (surfaceTexture != null) {
                    surfaceTexture.release();
                }
                if (surface != null) {
                    surface.release();
                    return;
                }
                return;
            case 1:
                ((A9.b) this.f346n).M0(((C0083g) this.f345m).o);
                return;
            case 2:
                ((LocalListenerHelper) this.f345m).lambda$asyncNotifyListener$0((Consumer) this.f346n);
                return;
            case 3:
                h.n nVar = (h.n) this.f345m;
                x xVar = (x) this.f346n;
                int i7 = h.n.f15112E;
                nVar.l.M0(new C1335g(xVar, nVar));
                return;
            case 4:
                ((x2.f) this.f346n).a(((s) this.f345m).d());
                return;
            case 5:
                C1373E c1373e = (C1373E) this.f345m;
                y yVar = (y) this.f346n;
                if (c1373e.l instanceof C1375a) {
                    yVar.cancel(false);
                    return;
                }
                return;
            case 6:
                C1398b.a((ViewOnAttachStateChangeListenerC1400d) this.f345m, (LongSparseArray) this.f346n);
                return;
            case 7:
                ((C1478k) this.f345m).i((P1) this.f346n);
                return;
            case 8:
                ((S) this.f346n).l(((D) this.f345m).f15825a.getShutdownTimeoutMillis());
                return;
            case 9:
                C1489n1 c1489n1 = (C1489n1) this.f346n;
                SpotlightIntegration spotlightIntegration = (SpotlightIntegration) this.f345m;
                try {
                    F1 f12 = spotlightIntegration.l;
                    if (f12 == null) {
                        throw new IllegalArgumentException("SentryOptions are required to send envelopes.");
                    }
                    HttpURLConnection httpURLConnectionG = SpotlightIntegration.g(f12.getSpotlightConnectionUrl() != null ? spotlightIntegration.l.getSpotlightConnectionUrl() : io.sentry.util.f.f16921a ? "http://10.0.2.2:8969/stream" : "http://localhost:8969/stream");
                    try {
                        OutputStream outputStream = httpURLConnectionG.getOutputStream();
                        try {
                            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
                            try {
                                spotlightIntegration.l.getSerializer().e(c1489n1, gZIPOutputStream);
                                gZIPOutputStream.close();
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                                spotlightIntegration.f15954m.k(EnumC1495p1.DEBUG, "Envelope sent to spotlight: %d", Integer.valueOf(httpURLConnectionG.getResponseCode()));
                            } finally {
                                try {
                                    break;
                                } catch (Throwable th) {
                                }
                            }
                        } finally {
                            if (outputStream != null) {
                                try {
                                    break;
                                } catch (Throwable th2) {
                                }
                            }
                        }
                    } catch (Throwable th3) {
                        try {
                            spotlightIntegration.f15954m.s(EnumC1495p1.ERROR, "An exception occurred while submitting the envelope to the Sentry server.", th3);
                            spotlightIntegration.f15954m.k(EnumC1495p1.DEBUG, "Envelope sent to spotlight: %d", Integer.valueOf(httpURLConnectionG.getResponseCode()));
                        } catch (Throwable th4) {
                            spotlightIntegration.f15954m.k(EnumC1495p1.DEBUG, "Envelope sent to spotlight: %d", Integer.valueOf(httpURLConnectionG.getResponseCode()));
                            SpotlightIntegration.b(httpURLConnectionG);
                            throw th4;
                        }
                    }
                    SpotlightIntegration.b(httpURLConnectionG);
                    return;
                } catch (Exception e10) {
                    spotlightIntegration.f15954m.s(EnumC1495p1.ERROR, "An exception occurred while creating the connection to spotlight.", e10);
                    return;
                }
            case 10:
                io.sentry.android.core.internal.util.l lVar = (io.sentry.android.core.internal.util.l) this.f345m;
                lVar.getClass();
                try {
                    lVar.f16238u = Choreographer.getInstance();
                    return;
                } catch (Throwable th5) {
                    ((P) this.f346n).s(EnumC1495p1.ERROR, "Error retrieving Choreographer instance. Slow and frozen frames will not be reported.", th5);
                    return;
                }
            case 11:
                l lVar2 = (l) this.f345m;
                F1 f13 = (F1) this.f346n;
                try {
                    lVar2.run();
                    return;
                } catch (Throwable th6) {
                    f13.getLogger().s(EnumC1495p1.ERROR, "Failed to execute task ".concat("WindowRecorder.capture"), th6);
                    return;
                }
            case 12:
                l lVar3 = (l) this.f345m;
                F1 f14 = (F1) this.f346n;
                A7.m.f(f14, "$options");
                try {
                    lVar3.run();
                    return;
                } catch (Throwable th7) {
                    f14.getLogger().s(EnumC1495p1.ERROR, "Failed to execute task ".concat("ReplayIntegration.finalize_previous_replay"), th7);
                    return;
                }
            case 13:
                ((io.sentry.cache.f) this.f345m).k((ConcurrentHashMap) this.f346n, "tags.json");
                return;
            case 14:
                ((io.sentry.cache.f) this.f345m).k((C1498c) this.f346n, "contexts.json");
                return;
            case 15:
                ((io.sentry.cache.f) this.f345m).k((Y1) this.f346n, "breadcrumbs.json");
                return;
            case 16:
                ((io.sentry.cache.f) this.f345m).k((t) this.f346n, "replay.json");
                return;
            case 17:
                io.sentry.cache.f fVar = (io.sentry.cache.f) this.f345m;
                String str = (String) this.f346n;
                if (str == null) {
                    io.sentry.cache.a.a(fVar.f16495a, ".scope-cache", "transaction.json");
                    return;
                } else {
                    fVar.k(str, "transaction.json");
                    return;
                }
            case 18:
                Runnable runnable = (Runnable) this.f346n;
                io.sentry.cache.f fVar2 = (io.sentry.cache.f) this.f345m;
                fVar2.getClass();
                try {
                    runnable.run();
                    return;
                } catch (Throwable th8) {
                    fVar2.f16495a.getLogger().s(EnumC1495p1.ERROR, "Serialization task failed", th8);
                    return;
                }
            case 19:
                io.sentry.okhttp.a aVar = (io.sentry.okhttp.a) this.f345m;
                AbstractC1453b1 abstractC1453b1 = (AbstractC1453b1) this.f346n;
                if (aVar.f16632g.get()) {
                    return;
                }
                Collection collectionValues = aVar.f16629b.values();
                if (!(collectionValues instanceof Collection) || !collectionValues.isEmpty()) {
                    Iterator it2 = collectionValues.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            if (!((U) it2.next()).g()) {
                                U u4 = aVar.d;
                                if (u4 != null && u4.g()) {
                                    return;
                                }
                            }
                        }
                    }
                }
                io.sentry.okhttp.a.b(aVar, abstractC1453b1, null, 2);
                return;
            case 20:
                C1650j0 c1650j0 = (C1650j0) this.f345m;
                io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(c1650j0.f17999a, (O) this.f346n);
                c1650j0.f18004h = cVar;
                C1646h0 c1646h0 = c1650j0.f18001e;
                Handler handler = c1650j0.f18000b.f18137e;
                if (c1646h0 == null) {
                    throw new IllegalArgumentException("callback must not be null");
                }
                if (!((Set) cVar.f16570n).add(c1646h0)) {
                    h6.n.t("MediaControllerCompat", "the callback has already been registered");
                    return;
                }
                if (handler == null) {
                    handler = new Handler();
                }
                c1646h0.j(handler);
                C1736A c1736a = (C1736A) cVar.f16569m;
                MediaController mediaController = c1736a.f18506a;
                C1767x c1767x = c1646h0.f17963a;
                c1767x.getClass();
                mediaController.registerCallback(c1767x, handler);
                synchronized (c1736a.f18507b) {
                    InterfaceC1750f interfaceC1750fA = c1736a.f18508e.a();
                    if (interfaceC1750fA != null) {
                        BinderC1769z binderC1769z = new BinderC1769z(c1646h0);
                        c1736a.d.put(c1646h0, binderC1769z);
                        c1646h0.c = binderC1769z;
                        try {
                            interfaceC1750fA.h1(binderC1769z);
                            c1646h0.i(13, null, null);
                        } catch (RemoteException e11) {
                            h6.n.i("MediaControllerCompat", "Dead object in registerCallback.", e11);
                        }
                    } else {
                        c1646h0.c = null;
                        c1736a.c.add(c1646h0);
                    }
                    break;
                }
                return;
            case 21:
                C1628b0 c1628b0 = (C1628b0) this.f345m;
                if (c1628b0.f17914n) {
                    return;
                }
                ((InterfaceC1656m0) this.f346n).e(c1628b0);
                return;
            case 22:
                ((C1373E) this.f346n).l(Boolean.valueOf(((I0) this.f345m).n()));
                return;
            case 23:
                I0 i02 = (I0) this.f345m;
                Q0 q02 = i02.f17801h;
                u1 u1Var = (u1) this.f346n;
                i02.f17811t = u1Var;
                G0 g02 = new G0(i02, u1Var);
                u1Var.D0(g02);
                i02.f17813v = g02;
                try {
                    q02.f17845h.q(0, u1Var);
                    break;
                } catch (RemoteException e12) {
                    AbstractC1346c.q("MediaSessionImpl", "Exception in using media1 API", e12);
                }
                l3.P p10 = q02.f17847j;
                ((L) p10.f18537m).f18523a.setActive(true);
                Iterator it3 = ((ArrayList) p10.o).iterator();
                if (it3.hasNext()) {
                    it3.next().getClass();
                    throw new ClassCastException();
                }
                i02.f17810s = u1Var.a1();
                i02.e(u1Var.A());
                return;
            case 24:
                try {
                    c1 = (C1) ((y) this.f345m).get();
                    AbstractC1346c.h(c1, "SessionResult must not be null");
                    break;
                } catch (InterruptedException | ExecutionException e13) {
                    AbstractC1346c.A("MediaSessionLegacyStub", "Custom command failed", e13);
                    c1 = new C1(-1);
                } catch (CancellationException e14) {
                    AbstractC1346c.A("MediaSessionLegacyStub", "Custom command cancelled", e14);
                    c1 = new C1(1);
                }
                ((ResultReceiver) this.f346n).send(c1.f17747a, c1.f17748b);
                return;
            case 25:
                I0 i03 = (I0) this.f345m;
                C1679y0 c1679y0D = i03.d();
                Intent intent = (Intent) this.f346n;
                if (c1679y0D == null) {
                    ComponentName component = intent.getComponent();
                    c1679y0D = new C1679y0(new T(component != null ? component.getPackageName() : "androidx.media3.session.MediaSessionService", -1, -1), 1005000300, 7, false, null, Bundle.EMPTY);
                }
                if (i03.m(c1679y0D, intent)) {
                    return;
                }
                AbstractC1346c.n("MSessionService", "Ignored unrecognized media button intent.");
                return;
            case 26:
                HashMap map = ((C1671u0) this.f345m).f18128g;
                A0 a02 = (A0) this.f346n;
                y yVar2 = (y) map.remove(a02);
                if (yVar2 != null) {
                    C1672v.W0(yVar2);
                }
                a02.f17714a.f17814w = null;
                return;
            case 27:
                m1 m1Var = (m1) this.f345m;
                m1Var.getClass();
                IBinder iBinderAsBinder = ((InterfaceC1651k) this.f346n).asBinder();
                B8.t tVar = m1Var.f18014e;
                C1679y0 c1679y0X = tVar.X(iBinderAsBinder);
                if (c1679y0X != null) {
                    tVar.l0(c1679y0X);
                    return;
                }
                return;
            case 28:
                ((m1) this.f345m).f18014e.Q((C1679y0) this.f346n);
                return;
            default:
                PlayerView.a((PlayerView) this.f345m, (Bitmap) this.f346n);
                return;
        }
    }
}
