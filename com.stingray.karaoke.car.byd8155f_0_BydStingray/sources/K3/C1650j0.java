package k3;

import android.content.Context;
import android.media.MediaMetadata;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;
import d5.C1077F;
import e2.C1195c;
import e2.C1202j;
import g2.C1290c;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.AbstractC1347d;
import h5.C1373E;
import io.sentry.C1489n1;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import l3.C1736A;
import l3.C1737B;
import l3.C1739D;
import l3.C1741F;
import l3.C1752h;
import l3.C1754j;
import l3.InterfaceC1750f;
import u.C2465e;

/* JADX INFO: renamed from: k3.j0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1650j0 implements InterfaceC1670u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f17999a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1672v f18000b;
    public final E1 c;
    public final h2.o d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C1646h0 f18001e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f18002f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Bundle f18003g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public io.sentry.internal.debugmeta.c f18004h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public C1754j f18005i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f18006j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f18007k;
    public C1648i0 l = new C1648i0();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C1648i0 f18008m = new C1648i0();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public H0.b f18009n = new H0.b(4);
    public long o = -9223372036854775807L;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f18010p = -9223372036854775807L;

    public C1650j0(Context context, C1672v c1672v, E1 e12, Bundle bundle, Looper looper, io.sentry.internal.debugmeta.c cVar) {
        this.d = new h2.o(looper, h2.v.f15245a, new C1634d0(this));
        this.f17999a = context;
        this.f18000b = c1672v;
        this.f18001e = new C1646h0(this, looper);
        this.c = e12;
        this.f18003g = bundle;
        this.f18002f = cVar;
        d5.d0 d0Var = d5.d0.f13415p;
    }

    public static List V0(List list) {
        if (list == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static l3.X W0(l3.X x8) {
        if (x8 == null) {
            return null;
        }
        if (x8.o > 0.0f) {
            return x8;
        }
        AbstractC1346c.z("MCImplLegacy", "Adjusting playback speed to 1.0f because negative playback speed isn't supported.");
        ArrayList arrayList = new ArrayList();
        AbstractCollection abstractCollection = x8.f18558t;
        if (abstractCollection != null) {
            arrayList.addAll(abstractCollection);
        }
        return new l3.X(x8.l, x8.f18552m, x8.f18553n, 1.0f, x8.f18554p, x8.f18555q, x8.f18556r, x8.f18557s, arrayList, x8.f18559u, x8.f18560v);
    }

    public static e2.V X0(int i7, e2.F f10, long j3, boolean z6) {
        return new e2.V(null, i7, f10, null, i7, j3, j3, z6 ? 0 : -1, z6 ? 0 : -1);
    }

    @Override // k3.InterfaceC1670u
    public final e2.S A() {
        return (e2.S) this.f18009n.o;
    }

    @Override // k3.InterfaceC1670u
    public final void A0(int i7, int i10) {
        AbstractC1346c.c(i7 >= 0 && i10 >= i7);
        int iO = t0().o();
        int iMin = Math.min(i10, iO);
        if (i7 >= iO || i7 == iMin) {
            return;
        }
        w1 w1Var = (w1) ((q1) this.f18009n.f2765m).f18078j;
        w1Var.getClass();
        C1077F c1077f = new C1077F(4);
        d5.I i11 = w1Var.f18153e;
        c1077f.e(i11.subList(0, i7));
        c1077f.e(i11.subList(iMin, i11.size()));
        w1 w1Var2 = new w1(c1077f.h(), w1Var.f18154f);
        int iB0 = b0();
        int i12 = iMin - i7;
        if (iB0 >= i7) {
            iB0 = iB0 < iMin ? -1 : iB0 - i12;
        }
        if (iB0 == -1) {
            iB0 = AbstractC1343A.i(i7, 0, w1Var2.o() - 1);
            AbstractC1346c.z("MCImplLegacy", "Currently playing item is removed. Assumes item at " + iB0 + " is the new current item");
        }
        q1 q1VarM = ((q1) this.f18009n.f2765m).m(w1Var2, iB0);
        H0.b bVar = this.f18009n;
        e1(new H0.b(q1VarM, (z1) bVar.f2766n, (e2.S) bVar.o, (d5.I) bVar.f2767p, (Bundle) bVar.f2768q, (A1) null), null, null);
        if (a1()) {
            while (i7 < iMin && i7 < this.l.f17992a.size()) {
                this.f18004h.S(((l3.M) this.l.f17992a.get(i7)).l);
                i7++;
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final long B() {
        return ((q1) this.f18009n.f2765m).c.f17734e;
    }

    @Override // k3.InterfaceC1670u
    public final void B0(int i7) {
        g0(i7, 1);
    }

    @Override // k3.InterfaceC1670u
    public final boolean C() {
        return ((q1) this.f18009n.f2765m).f18086t;
    }

    @Override // k3.InterfaceC1670u
    public final void C0() {
        this.f18004h.M().f18512b.skipToNext();
    }

    @Override // k3.InterfaceC1670u
    public final void D() {
        A0(0, Integer.MAX_VALUE);
    }

    @Override // k3.InterfaceC1670u
    public final void D0(e2.U u4) {
        this.d.a(u4);
    }

    @Override // k3.InterfaceC1670u
    public final void E(e2.F f10, long j3) {
        m(d5.I.B(f10), 0, j3);
    }

    @Override // k3.InterfaceC1670u
    public final void E0() {
        this.f18004h.M().f18512b.fastForward();
    }

    @Override // k3.InterfaceC1670u
    public final void F(boolean z6) {
        if (z6 != x0()) {
            q1 q1VarK = ((q1) this.f18009n.f2765m).k(z6);
            H0.b bVar = this.f18009n;
            e1(new H0.b(q1VarK, (z1) bVar.f2766n, (e2.S) bVar.o, (d5.I) bVar.f2767p, (Bundle) bVar.f2768q, (A1) null), null, null);
        }
        C1739D c1739dM = this.f18004h.M();
        d5.M m10 = AbstractC1663q.f18029a;
        Bundle bundle = new Bundle();
        bundle.putInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE", z6 ? 1 : 0);
        c1739dM.A("android.support.v4.media.session.action.SET_SHUFFLE_MODE", bundle);
    }

    @Override // k3.InterfaceC1670u
    public final void F0(TextureView textureView) {
        AbstractC1346c.z("MCImplLegacy", "Session doesn't support setting TextureView");
    }

    @Override // k3.InterfaceC1670u
    public final void G() {
        this.f18004h.M().f18512b.skipToNext();
    }

    @Override // k3.InterfaceC1670u
    public final void G0(e2.I i7) {
        AbstractC1346c.z("MCImplLegacy", "Session doesn't support setting playlist metadata");
    }

    @Override // k3.InterfaceC1670u
    public final void H(e2.U u4) {
        this.d.e(u4);
    }

    @Override // k3.InterfaceC1670u
    public final void H0() {
        this.f18004h.M().f18512b.rewind();
    }

    @Override // k3.InterfaceC1670u
    public final void I(int i7) {
        int iO = o() - 1;
        if (iO >= d0().f14128b) {
            q1 q1VarC = ((q1) this.f18009n.f2765m).c(iO, u0());
            H0.b bVar = this.f18009n;
            e1(new H0.b(q1VarC, (z1) bVar.f2766n, (e2.S) bVar.o, (d5.I) bVar.f2767p, (Bundle) bVar.f2768q, (A1) null), null, null);
        }
        ((C1736A) this.f18004h.f16569m).f18506a.adjustVolume(-1, i7);
    }

    @Override // k3.InterfaceC1670u
    public final void I0(long j3, int i7) {
        c1(j3, i7);
    }

    @Override // k3.InterfaceC1670u
    public final e2.n0 J() {
        return e2.n0.f14225b;
    }

    @Override // k3.InterfaceC1670u
    public final void J0(float f10) {
        AbstractC1346c.z("MCImplLegacy", "Session doesn't support setting player volume");
    }

    @Override // k3.InterfaceC1670u
    public final int K() {
        return ((q1) this.f18009n.f2765m).c.f17735f;
    }

    @Override // k3.InterfaceC1670u
    public final e2.I K0() {
        e2.F fS = ((q1) this.f18009n.f2765m).s();
        return fS == null ? e2.I.f13914K : fS.d;
    }

    @Override // k3.InterfaceC1670u
    public final long L() {
        return ((q1) this.f18009n.f2765m).f18068C;
    }

    @Override // k3.InterfaceC1670u
    public final void L0(List list) {
        m(list, 0, -9223372036854775807L);
    }

    @Override // k3.InterfaceC1670u
    public final boolean M() {
        return this.f18007k;
    }

    @Override // k3.InterfaceC1670u
    public final void M0() {
        this.f18004h.M().f18512b.skipToPrevious();
    }

    @Override // k3.InterfaceC1670u
    public final e2.I N() {
        return ((q1) this.f18009n.f2765m).f18080m;
    }

    @Override // k3.InterfaceC1670u
    public final long N0() {
        long jF = n1.f((q1) this.f18009n.f2765m, this.o, this.f18010p, this.f18000b.f18138f);
        this.o = jF;
        return jF;
    }

    @Override // k3.InterfaceC1670u
    public final boolean O() {
        return ((q1) this.f18009n.f2765m).f18088v;
    }

    @Override // k3.InterfaceC1670u
    public final long O0() {
        return ((q1) this.f18009n.f2765m).f18067A;
    }

    @Override // k3.InterfaceC1670u
    public final long P() {
        return s0();
    }

    @Override // k3.InterfaceC1670u
    public final z1 P0() {
        return (z1) this.f18009n.f2766n;
    }

    @Override // k3.InterfaceC1670u
    public final int Q() {
        return b0();
    }

    @Override // k3.InterfaceC1670u
    public final Bundle Q0() {
        return this.f18003g;
    }

    @Override // k3.InterfaceC1670u
    public final C1290c R() {
        AbstractC1346c.z("MCImplLegacy", "Session doesn't support getting Cue");
        return C1290c.c;
    }

    @Override // k3.InterfaceC1670u
    public final d5.I R0() {
        return (d5.I) this.f18009n.f2767p;
    }

    @Override // k3.InterfaceC1670u
    public final void S(TextureView textureView) {
        AbstractC1346c.z("MCImplLegacy", "Session doesn't support clearing TextureView");
    }

    @Override // k3.InterfaceC1670u
    public final void S0() {
        E1 e12 = this.c;
        int iB = e12.f17758a.b();
        C1672v c1672v = this.f18000b;
        if (iB != 0) {
            c1672v.X0(new RunnableC1637e0(this, 1));
            return;
        }
        Object objP = e12.f17758a.p();
        AbstractC1346c.k(objP);
        c1672v.X0(new A2.m(this, 20, (l3.O) objP));
        c1672v.f18137e.post(new RunnableC1637e0(this, 0));
    }

    @Override // k3.InterfaceC1670u
    public final e2.q0 T() {
        AbstractC1346c.z("MCImplLegacy", "Session doesn't support getting VideoSize");
        return e2.q0.d;
    }

    @Override // k3.InterfaceC1670u
    public final h5.y T0(y1 y1Var, Bundle bundle) {
        z1 z1Var = (z1) this.f18009n.f2766n;
        z1Var.getClass();
        boolean zContains = z1Var.f18173a.contains(y1Var);
        String str = y1Var.f18168b;
        if (zContains) {
            this.f18004h.M().A(str, bundle);
            return l9.l.T(new C1(0));
        }
        C1373E c1373e = new C1373E();
        ResultReceiverC1643g0 resultReceiverC1643g0 = new ResultReceiverC1643g0(this.f18000b.f18137e, c1373e);
        io.sentry.internal.debugmeta.c cVar = this.f18004h;
        cVar.getClass();
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("command must neither be null nor empty");
        }
        ((C1736A) cVar.f16569m).f18506a.sendCommand(str, bundle, resultReceiverC1643g0);
        return c1373e;
    }

    @Override // k3.InterfaceC1670u
    public final void U(C1195c c1195c, boolean z6) {
        AbstractC1346c.z("MCImplLegacy", "Legacy session doesn't support setting audio attributes remotely");
    }

    public final void U0(int i7, List list) {
        ArrayList arrayList = new ArrayList();
        RunnableC1640f0 runnableC1640f0 = new RunnableC1640f0(this, new AtomicInteger(0), list, arrayList, i7);
        for (int i10 = 0; i10 < list.size(); i10++) {
            byte[] bArr = ((e2.F) list.get(i10)).d.f13967k;
            if (bArr == null) {
                arrayList.add(null);
                runnableC1640f0.run();
            } else {
                h5.y yVarP = this.f18002f.p(bArr);
                arrayList.add(yVarP);
                Handler handler = this.f18000b.f18137e;
                Objects.requireNonNull(handler);
                yVarP.b(runnableC1640f0, new ExecutorC1662p0(handler, 1));
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final void V(e2.F f10) {
        E(f10, -9223372036854775807L);
    }

    @Override // k3.InterfaceC1670u
    public final void W() {
        this.f18004h.M().f18512b.skipToPrevious();
    }

    @Override // k3.InterfaceC1670u
    public final float X() {
        return 1.0f;
    }

    @Override // k3.InterfaceC1670u
    public final void Y() {
        c1(0L, b0());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0655  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0662  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x07f6  */
    /* JADX WARN: Removed duplicated region for block: B:396:0x0801  */
    /* JADX WARN: Type inference failed for: r5v40, types: [java.util.AbstractCollection, java.util.List] */
    /* JADX WARN: Type inference failed for: r6v66, types: [java.util.AbstractCollection, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void Y0(boolean r86, k3.C1648i0 r87) {
        /*
            Method dump skipped, instruction units count: 2246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: k3.C1650j0.Y0(boolean, k3.i0):void");
    }

    @Override // k3.InterfaceC1670u
    public final C1195c Z() {
        return ((q1) this.f18009n.f2765m).o;
    }

    public final void Z0() {
        e2.e0 e0Var = new e2.e0();
        AbstractC1346c.j(a1() && !((q1) this.f18009n.f2765m).f18078j.p());
        q1 q1Var = (q1) this.f18009n.f2765m;
        w1 w1Var = (w1) q1Var.f18078j;
        int i7 = q1Var.c.f17732a.f14008b;
        w1Var.m(i7, e0Var, 0L);
        e2.F f10 = e0Var.c;
        if (w1Var.r(i7) == -1) {
            e2.B b10 = f10.f13881f;
            if (b10.f13854a != null) {
                if (((q1) this.f18009n.f2765m).f18086t) {
                    C1739D c1739dM = this.f18004h.M();
                    Uri uri = b10.f13854a;
                    Bundle bundle = b10.c;
                    if (bundle == null) {
                        bundle = Bundle.EMPTY;
                    }
                    c1739dM.f18512b.playFromUri(uri, bundle);
                } else {
                    C1739D c1739dM2 = this.f18004h.M();
                    Uri uri2 = b10.f13854a;
                    Bundle bundle2 = b10.c;
                    if (bundle2 == null) {
                        bundle2 = Bundle.EMPTY;
                    }
                    c1739dM2.f18512b.prepareFromUri(uri2, bundle2);
                }
            } else if (b10.f13855b == null) {
                boolean z6 = ((q1) this.f18009n.f2765m).f18086t;
                String str = f10.f13878a;
                if (z6) {
                    C1739D c1739dM3 = this.f18004h.M();
                    Bundle bundle3 = b10.c;
                    if (bundle3 == null) {
                        bundle3 = Bundle.EMPTY;
                    }
                    c1739dM3.f18512b.playFromMediaId(str, bundle3);
                } else {
                    C1739D c1739dM4 = this.f18004h.M();
                    Bundle bundle4 = b10.c;
                    if (bundle4 == null) {
                        bundle4 = Bundle.EMPTY;
                    }
                    c1739dM4.f18512b.prepareFromMediaId(str, bundle4);
                }
            } else if (((q1) this.f18009n.f2765m).f18086t) {
                C1739D c1739dM5 = this.f18004h.M();
                String str2 = b10.f13855b;
                Bundle bundle5 = b10.c;
                if (bundle5 == null) {
                    bundle5 = Bundle.EMPTY;
                }
                c1739dM5.f18512b.playFromSearch(str2, bundle5);
            } else {
                C1739D c1739dM6 = this.f18004h.M();
                String str3 = b10.f13855b;
                Bundle bundle6 = b10.c;
                if (bundle6 == null) {
                    bundle6 = Bundle.EMPTY;
                }
                c1739dM6.f18512b.prepareFromSearch(str3, bundle6);
            }
        } else if (((q1) this.f18009n.f2765m).f18086t) {
            this.f18004h.M().f18512b.play();
        } else {
            this.f18004h.M().f18512b.prepare();
        }
        if (((q1) this.f18009n.f2765m).c.f17732a.f14010f != 0) {
            this.f18004h.M().f18512b.seekTo(((q1) this.f18009n.f2765m).c.f17732a.f14010f);
        }
        if (((e2.S) this.f18009n.o).a(20)) {
            ArrayList arrayList = new ArrayList();
            for (int i10 = 0; i10 < w1Var.o(); i10++) {
                if (i10 != i7 && w1Var.r(i10) == -1) {
                    w1Var.m(i10, e0Var, 0L);
                    arrayList.add(e0Var.c);
                }
            }
            U0(0, arrayList);
        }
    }

    @Override // k3.InterfaceC1670u
    public final void a() {
        Messenger messenger;
        if (this.f18006j) {
            return;
        }
        this.f18006j = true;
        C1754j c1754j = this.f18005i;
        if (c1754j != null) {
            C1752h c1752h = c1754j.f18578a;
            C1489n1 c1489n1 = c1752h.f18573f;
            if (c1489n1 != null && (messenger = c1752h.f18574g) != null) {
                try {
                    Message messageObtain = Message.obtain();
                    messageObtain.what = 7;
                    messageObtain.arg1 = 1;
                    messageObtain.replyTo = messenger;
                    ((Messenger) c1489n1.f16624m).send(messageObtain);
                } catch (RemoteException unused) {
                    Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
                }
            }
            c1752h.f18571b.disconnect();
            this.f18005i = null;
        }
        io.sentry.internal.debugmeta.c cVar = this.f18004h;
        if (cVar != null) {
            C1646h0 c1646h0 = this.f18001e;
            if (c1646h0 == null) {
                throw new IllegalArgumentException("callback must not be null");
            }
            if (((Set) cVar.f16570n).remove(c1646h0)) {
                try {
                    ((C1736A) cVar.f16569m).b(c1646h0);
                } finally {
                    c1646h0.j(null);
                }
            } else {
                h6.n.t("MediaControllerCompat", "the callback has never been registered");
            }
            c1646h0.d.removeCallbacksAndMessages(null);
            this.f18004h = null;
        }
        this.f18007k = false;
        this.d.d();
    }

    @Override // k3.InterfaceC1670u
    public final int a0() {
        return -1;
    }

    public final boolean a1() {
        return ((q1) this.f18009n.f2765m).f18091y != 1;
    }

    @Override // k3.InterfaceC1670u
    public final void b() {
        q(false);
    }

    @Override // k3.InterfaceC1670u
    public final int b0() {
        return ((q1) this.f18009n.f2765m).c.f17732a.f14008b;
    }

    public final void b1() {
        C1741F c1741fCreateFromParcel;
        int i7;
        int iN;
        if (this.f18006j || this.f18007k) {
            return;
        }
        this.f18007k = true;
        C1737B c1737bJ = this.f18004h.J();
        l3.X xW0 = W0(this.f18004h.K());
        MediaMetadata metadata = ((C1736A) this.f18004h.f16569m).f18506a.getMetadata();
        if (metadata != null) {
            C2465e c2465e = C1741F.f18520n;
            Parcel parcelObtain = Parcel.obtain();
            metadata.writeToParcel(parcelObtain, 0);
            parcelObtain.setDataPosition(0);
            c1741fCreateFromParcel = C1741F.CREATOR.createFromParcel(parcelObtain);
            parcelObtain.recycle();
            c1741fCreateFromParcel.f18521m = metadata;
        } else {
            c1741fCreateFromParcel = null;
        }
        List<MediaSession.QueueItem> queue = ((C1736A) this.f18004h.f16569m).f18506a.getQueue();
        List listV0 = V0(queue != null ? l3.M.a(queue) : null);
        CharSequence queueTitle = ((C1736A) this.f18004h.f16569m).f18506a.getQueueTitle();
        InterfaceC1750f interfaceC1750fA = ((C1736A) this.f18004h.f16569m).f18508e.a();
        if (interfaceC1750fA != null) {
            try {
                i7 = interfaceC1750fA.i();
            } catch (RemoteException e10) {
                h6.n.i("MediaControllerCompat", "Dead object in getRepeatMode.", e10);
                i7 = -1;
            }
        } else {
            i7 = -1;
        }
        InterfaceC1750f interfaceC1750fA2 = ((C1736A) this.f18004h.f16569m).f18508e.a();
        if (interfaceC1750fA2 != null) {
            try {
                iN = interfaceC1750fA2.N();
            } catch (RemoteException e11) {
                h6.n.i("MediaControllerCompat", "Dead object in getShuffleMode.", e11);
                iN = -1;
            }
        } else {
            iN = -1;
        }
        Y0(true, new C1648i0(c1737bJ, xW0, c1741fCreateFromParcel, listV0, queueTitle, i7, iN, ((C1736A) this.f18004h.f16569m).f18506a.getExtras()));
    }

    @Override // k3.InterfaceC1670u
    public final void c() {
        q1 q1Var = (q1) this.f18009n.f2765m;
        if (q1Var.f18091y != 1) {
            return;
        }
        q1 q1VarF = q1Var.f(q1Var.f18078j.p() ? 4 : 2, null);
        H0.b bVar = this.f18009n;
        e1(new H0.b(q1VarF, (z1) bVar.f2766n, (e2.S) bVar.o, (d5.I) bVar.f2767p, (Bundle) bVar.f2768q, (A1) null), null, null);
        if (((q1) this.f18009n.f2765m).f18078j.p()) {
            return;
        }
        Z0();
    }

    @Override // k3.InterfaceC1670u
    public final void c0(int i7, boolean z6) {
        if (AbstractC1343A.f15195a < 23) {
            AbstractC1346c.z("MCImplLegacy", "Session doesn't support setting mute state at API level less than 23");
            return;
        }
        if (z6 != u0()) {
            q1 q1VarC = ((q1) this.f18009n.f2765m).c(o(), z6);
            H0.b bVar = this.f18009n;
            e1(new H0.b(q1VarC, (z1) bVar.f2766n, (e2.S) bVar.o, (d5.I) bVar.f2767p, (Bundle) bVar.f2768q, (A1) null), null, null);
        }
        ((C1736A) this.f18004h.f16569m).f18506a.adjustVolume(z6 ? -100 : 100, i7);
    }

    public final void c1(long j3, int i7) {
        Integer num;
        Integer num2;
        long j10;
        long j11;
        long j12;
        int i10;
        long j13 = j3;
        int i11 = i7;
        AbstractC1346c.c(i11 >= 0);
        int iB0 = b0();
        e2.f0 f0Var = ((q1) this.f18009n.f2765m).f18078j;
        if ((f0Var.p() || i11 < f0Var.o()) && !s()) {
            if (i11 != iB0) {
                long jR = ((w1) ((q1) this.f18009n.f2765m).f18078j).r(i11);
                if (jR != -1) {
                    this.f18004h.M().f18512b.skipToQueueItem(jR);
                    num = 2;
                } else {
                    AbstractC1347d.p("Cannot seek to new media item due to the missing queue Id at media item, mediaItemIndex=", i11, "MCImplLegacy");
                    i11 = iB0;
                    num = null;
                }
            } else {
                i11 = iB0;
                num = null;
            }
            long jN0 = N0();
            if (j13 == -9223372036854775807L) {
                j13 = jN0;
                num2 = null;
            } else {
                this.f18004h.M().f18512b.seekTo(j13);
                num2 = 1;
            }
            if (num == null) {
                long jB = B();
                long jS0 = s0();
                long jMax = j13 < jN0 ? j13 : Math.max(j13, jB);
                j11 = jMax;
                i10 = jS0 == -9223372036854775807L ? 0 : (int) ((100 * jMax) / jS0);
                j10 = jMax - j13;
                j12 = jS0;
            } else {
                j10 = 0;
                j11 = 0;
                j12 = -9223372036854775807L;
                i10 = 0;
            }
            q1 q1VarJ = ((q1) this.f18009n.f2765m).j(new B1(X0(i11, !f0Var.p() ? f0Var.m(i11, new e2.e0(), 0L).c : null, j13, false), false, SystemClock.elapsedRealtime(), j12, j11, i10, j10, -9223372036854775807L, j12, j11));
            if (q1VarJ.f18091y != 1) {
                q1VarJ = q1VarJ.f(2, null);
            }
            H0.b bVar = this.f18009n;
            e1(new H0.b(q1VarJ, (z1) bVar.f2766n, (e2.S) bVar.o, (d5.I) bVar.f2767p, (Bundle) bVar.f2768q, (A1) null), num2, num);
        }
    }

    @Override // k3.InterfaceC1670u
    public final boolean d() {
        return false;
    }

    @Override // k3.InterfaceC1670u
    public final C1202j d0() {
        return ((q1) this.f18009n.f2765m).f18083q;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0206  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d1(boolean r18, k3.C1648i0 r19, final H0.b r20, java.lang.Integer r21, java.lang.Integer r22) {
        /*
            Method dump skipped, instruction units count: 591
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: k3.C1650j0.d1(boolean, k3.i0, H0.b, java.lang.Integer, java.lang.Integer):void");
    }

    @Override // k3.InterfaceC1670u
    public final int e() {
        return ((q1) this.f18009n.f2765m).f18091y;
    }

    @Override // k3.InterfaceC1670u
    public final void e0() {
        I(1);
    }

    public final void e1(H0.b bVar, Integer num, Integer num2) {
        d1(false, this.l, bVar, num, num2);
    }

    @Override // k3.InterfaceC1670u
    public final void f(e2.P p10) {
        if (!p10.equals(j())) {
            q1 q1VarE = ((q1) this.f18009n.f2765m).e(p10);
            H0.b bVar = this.f18009n;
            e1(new H0.b(q1VarE, (z1) bVar.f2766n, (e2.S) bVar.o, (d5.I) bVar.f2767p, (Bundle) bVar.f2768q, (A1) null), null, null);
        }
        C1739D c1739dM = this.f18004h.M();
        float f10 = p10.f13995a;
        if (f10 == 0.0f) {
            throw new IllegalArgumentException("speed must not be zero");
        }
        c1739dM.f18512b.setPlaybackSpeed(f10);
    }

    @Override // k3.InterfaceC1670u
    public final void f0(e2.l0 l0Var) {
    }

    @Override // k3.InterfaceC1670u
    public final void g() {
        q(true);
    }

    @Override // k3.InterfaceC1670u
    public final void g0(int i7, int i10) {
        int i11;
        C1202j c1202jD0 = d0();
        if (c1202jD0.f14128b <= i7 && ((i11 = c1202jD0.c) == 0 || i7 <= i11)) {
            q1 q1VarC = ((q1) this.f18009n.f2765m).c(i7, u0());
            H0.b bVar = this.f18009n;
            e1(new H0.b(q1VarC, (z1) bVar.f2766n, (e2.S) bVar.o, (d5.I) bVar.f2767p, (Bundle) bVar.f2768q, (A1) null), null, null);
        }
        ((C1736A) this.f18004h.f16569m).f18506a.setVolumeTo(i7, i10);
    }

    @Override // k3.InterfaceC1670u
    public final void h(int i7) {
        if (i7 != i()) {
            q1 q1VarI = ((q1) this.f18009n.f2765m).i(i7);
            H0.b bVar = this.f18009n;
            e1(new H0.b(q1VarI, (z1) bVar.f2766n, (e2.S) bVar.o, (d5.I) bVar.f2767p, (Bundle) bVar.f2768q, (A1) null), null, null);
        }
        C1739D c1739dM = this.f18004h.M();
        int iN = AbstractC1663q.n(i7);
        Bundle bundle = new Bundle();
        bundle.putInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE", iN);
        c1739dM.A("android.support.v4.media.session.action.SET_REPEAT_MODE", bundle);
    }

    @Override // k3.InterfaceC1670u
    public final void h0(boolean z6) {
        c0(1, z6);
    }

    @Override // k3.InterfaceC1670u
    public final int i() {
        return ((q1) this.f18009n.f2765m).f18076h;
    }

    @Override // k3.InterfaceC1670u
    public final boolean i0() {
        return this.f18007k;
    }

    @Override // k3.InterfaceC1670u
    public final e2.P j() {
        return ((q1) this.f18009n.f2765m).f18075g;
    }

    @Override // k3.InterfaceC1670u
    public final void j0(int i7) {
        int iO = o();
        int i10 = d0().c;
        if (i10 == 0 || iO + 1 <= i10) {
            q1 q1VarC = ((q1) this.f18009n.f2765m).c(iO + 1, u0());
            H0.b bVar = this.f18009n;
            e1(new H0.b(q1VarC, (z1) bVar.f2766n, (e2.S) bVar.o, (d5.I) bVar.f2767p, (Bundle) bVar.f2768q, (A1) null), null, null);
        }
        ((C1736A) this.f18004h.f16569m).f18506a.adjustVolume(1, i7);
    }

    @Override // k3.InterfaceC1670u
    public final void k(long j3) {
        c1(j3, b0());
    }

    @Override // k3.InterfaceC1670u
    public final int k0() {
        return -1;
    }

    @Override // k3.InterfaceC1670u
    public final void l(float f10) {
        if (f10 != j().f13995a) {
            q1 q1VarE = ((q1) this.f18009n.f2765m).e(new e2.P(f10));
            H0.b bVar = this.f18009n;
            e1(new H0.b(q1VarE, (z1) bVar.f2766n, (e2.S) bVar.o, (d5.I) bVar.f2767p, (Bundle) bVar.f2768q, (A1) null), null, null);
        }
        C1739D c1739dM = this.f18004h.M();
        if (f10 == 0.0f) {
            throw new IllegalArgumentException("speed must not be zero");
        }
        c1739dM.f18512b.setPlaybackSpeed(f10);
    }

    @Override // k3.InterfaceC1670u
    public final void l0(SurfaceView surfaceView) {
        AbstractC1346c.z("MCImplLegacy", "Session doesn't support setting SurfaceView");
    }

    @Override // k3.InterfaceC1670u
    public final void m(List list, int i7, long j3) {
        if (list.isEmpty()) {
            D();
            return;
        }
        q1 q1VarN = ((q1) this.f18009n.f2765m).n(w1.f18151g.q(0, list), new B1(X0(i7, (e2.F) list.get(i7), j3 == -9223372036854775807L ? 0L : j3, false), false, SystemClock.elapsedRealtime(), -9223372036854775807L, 0L, 0, 0L, -9223372036854775807L, -9223372036854775807L, 0L), 0);
        H0.b bVar = this.f18009n;
        e1(new H0.b(q1VarN, (z1) bVar.f2766n, (e2.S) bVar.o, (d5.I) bVar.f2767p, (Bundle) bVar.f2768q, (A1) null), null, null);
        if (a1()) {
            Z0();
        }
    }

    @Override // k3.InterfaceC1670u
    public final void m0(SurfaceView surfaceView) {
        AbstractC1346c.z("MCImplLegacy", "Session doesn't support clearing SurfaceView");
    }

    @Override // k3.InterfaceC1670u
    public final e2.O n() {
        return ((q1) this.f18009n.f2765m).f18071a;
    }

    @Override // k3.InterfaceC1670u
    public final void n0(int i7, int i10) {
        o0(i7, i7 + 1, i10);
    }

    @Override // k3.InterfaceC1670u
    public final int o() {
        q1 q1Var = (q1) this.f18009n.f2765m;
        if (q1Var.f18083q.f14127a == 1) {
            return q1Var.f18084r;
        }
        io.sentry.internal.debugmeta.c cVar = this.f18004h;
        if (cVar == null) {
            return 0;
        }
        C1737B c1737bJ = cVar.J();
        d5.M m10 = AbstractC1663q.f18029a;
        if (c1737bJ == null) {
            return 0;
        }
        return c1737bJ.f18511e;
    }

    @Override // k3.InterfaceC1670u
    public final void o0(int i7, int i10, int i11) {
        AbstractC1346c.c(i7 >= 0 && i7 <= i10 && i11 >= 0);
        w1 w1Var = (w1) ((q1) this.f18009n.f2765m).f18078j;
        int iO = w1Var.o();
        int iMin = Math.min(i10, iO);
        int i12 = iMin - i7;
        int i13 = iO - i12;
        int i14 = i13 - 1;
        int iMin2 = Math.min(i11, i13);
        if (i7 >= iO || i7 == iMin || i7 == iMin2) {
            return;
        }
        int iB0 = b0();
        if (iB0 >= i7) {
            iB0 = iB0 < iMin ? -1 : iB0 - i12;
        }
        if (iB0 == -1) {
            iB0 = AbstractC1343A.i(i7, 0, i14);
            AbstractC1346c.z("MCImplLegacy", "Currently playing item will be removed and added back to mimic move. Assumes item at " + iB0 + " would be the new current item");
        }
        if (iB0 >= iMin2) {
            iB0 += i12;
        }
        ArrayList arrayList = new ArrayList(w1Var.f18153e);
        AbstractC1343A.G(arrayList, i7, iMin, iMin2);
        q1 q1VarM = ((q1) this.f18009n.f2765m).m(new w1(d5.I.w(arrayList), w1Var.f18154f), iB0);
        H0.b bVar = this.f18009n;
        e1(new H0.b(q1VarM, (z1) bVar.f2766n, (e2.S) bVar.o, (d5.I) bVar.f2767p, (Bundle) bVar.f2768q, (A1) null), null, null);
        if (a1()) {
            ArrayList arrayList2 = new ArrayList();
            for (int i15 = 0; i15 < i12; i15++) {
                arrayList2.add((l3.M) this.l.f17992a.get(i7));
                this.f18004h.S(((l3.M) this.l.f17992a.get(i7)).l);
            }
            for (int i16 = 0; i16 < arrayList2.size(); i16++) {
                this.f18004h.E(((l3.M) arrayList2.get(i16)).l, i16 + iMin2);
            }
        }
    }

    @Override // k3.InterfaceC1670u
    public final void p(int i7, e2.F f10) {
        q0(i7, i7 + 1, d5.I.B(f10));
    }

    @Override // k3.InterfaceC1670u
    public final int p0() {
        return 0;
    }

    @Override // k3.InterfaceC1670u
    public final void q(boolean z6) {
        q1 q1Var = (q1) this.f18009n.f2765m;
        if (q1Var.f18086t == z6) {
            return;
        }
        this.o = n1.f(q1Var, this.o, this.f18010p, this.f18000b.f18138f);
        this.f18010p = SystemClock.elapsedRealtime();
        q1 q1VarD = ((q1) this.f18009n.f2765m).d(1, 0, z6);
        H0.b bVar = this.f18009n;
        e1(new H0.b(q1VarD, (z1) bVar.f2766n, (e2.S) bVar.o, (d5.I) bVar.f2767p, (Bundle) bVar.f2768q, (A1) null), null, null);
        if (!a1() || ((q1) this.f18009n.f2765m).f18078j.p()) {
            return;
        }
        if (z6) {
            this.f18004h.M().f18512b.play();
        } else {
            this.f18004h.M().f18512b.pause();
        }
    }

    @Override // k3.InterfaceC1670u
    public final void q0(int i7, int i10, List list) {
        AbstractC1346c.c(i7 >= 0 && i7 <= i10);
        int iO = ((w1) ((q1) this.f18009n.f2765m).f18078j).o();
        if (i7 > iO) {
            return;
        }
        int iMin = Math.min(i10, iO);
        z(iMin, list);
        A0(i7, iMin);
    }

    @Override // k3.InterfaceC1670u
    public final void r(Surface surface) {
        AbstractC1346c.z("MCImplLegacy", "Session doesn't support setting Surface");
    }

    @Override // k3.InterfaceC1670u
    public final void r0(List list) {
        z(Integer.MAX_VALUE, list);
    }

    @Override // k3.InterfaceC1670u
    public final boolean s() {
        return ((q1) this.f18009n.f2765m).c.f17733b;
    }

    @Override // k3.InterfaceC1670u
    public final long s0() {
        return ((q1) this.f18009n.f2765m).c.d;
    }

    @Override // k3.InterfaceC1670u
    public final void stop() {
        q1 q1Var = (q1) this.f18009n.f2765m;
        if (q1Var.f18091y == 1) {
            return;
        }
        B1 b12 = q1Var.c;
        e2.V v10 = b12.f17732a;
        long j3 = v10.f14010f;
        long j10 = b12.d;
        q1 q1VarJ = q1Var.j(new B1(v10, false, SystemClock.elapsedRealtime(), j10, j3, n1.e(j3, j10), 0L, -9223372036854775807L, j10, j3));
        q1 q1Var2 = (q1) this.f18009n.f2765m;
        if (q1Var2.f18091y != 1) {
            q1VarJ = q1VarJ.f(1, q1Var2.f18071a);
        }
        H0.b bVar = this.f18009n;
        e1(new H0.b(q1VarJ, (z1) bVar.f2766n, (e2.S) bVar.o, (d5.I) bVar.f2767p, (Bundle) bVar.f2768q, (A1) null), null, null);
        this.f18004h.M().f18512b.stop();
    }

    @Override // k3.InterfaceC1670u
    public final void t(int i7) {
        c1(0L, i7);
    }

    @Override // k3.InterfaceC1670u
    public final e2.f0 t0() {
        return ((q1) this.f18009n.f2765m).f18078j;
    }

    @Override // k3.InterfaceC1670u
    public final long u() {
        return ((q1) this.f18009n.f2765m).B;
    }

    @Override // k3.InterfaceC1670u
    public final boolean u0() {
        q1 q1Var = (q1) this.f18009n.f2765m;
        if (q1Var.f18083q.f14127a == 1) {
            return q1Var.f18085s;
        }
        io.sentry.internal.debugmeta.c cVar = this.f18004h;
        if (cVar != null) {
            C1737B c1737bJ = cVar.J();
            d5.M m10 = AbstractC1663q.f18029a;
            if (c1737bJ != null && c1737bJ.f18511e == 0) {
                return true;
            }
        }
        return false;
    }

    @Override // k3.InterfaceC1670u
    public final boolean v() {
        return this.f18007k;
    }

    @Override // k3.InterfaceC1670u
    public final void v0(int i7) {
        A0(i7, i7 + 1);
    }

    @Override // k3.InterfaceC1670u
    public final long w() {
        return -9223372036854775807L;
    }

    @Override // k3.InterfaceC1670u
    public final void w0() {
        j0(1);
    }

    @Override // k3.InterfaceC1670u
    public final long x() {
        return N0();
    }

    @Override // k3.InterfaceC1670u
    public final boolean x0() {
        return ((q1) this.f18009n.f2765m).f18077i;
    }

    @Override // k3.InterfaceC1670u
    public final long y() {
        return ((q1) this.f18009n.f2765m).c.f17736g;
    }

    @Override // k3.InterfaceC1670u
    public final e2.l0 y0() {
        return e2.l0.f14162C;
    }

    @Override // k3.InterfaceC1670u
    public final void z(int i7, List list) {
        AbstractC1346c.c(i7 >= 0);
        if (list.isEmpty()) {
            return;
        }
        w1 w1Var = (w1) ((q1) this.f18009n.f2765m).f18078j;
        if (w1Var.p()) {
            m(list, 0, -9223372036854775807L);
            return;
        }
        int iMin = Math.min(i7, t0().o());
        w1 w1VarQ = w1Var.q(iMin, list);
        int iB0 = b0();
        int size = list.size();
        if (iB0 >= iMin) {
            iB0 += size;
        }
        q1 q1VarM = ((q1) this.f18009n.f2765m).m(w1VarQ, iB0);
        H0.b bVar = this.f18009n;
        e1(new H0.b(q1VarM, (z1) bVar.f2766n, (e2.S) bVar.o, (d5.I) bVar.f2767p, (Bundle) bVar.f2768q, (A1) null), null, null);
        if (a1()) {
            U0(iMin, list);
        }
    }

    @Override // k3.InterfaceC1670u
    public final long z0() {
        return B();
    }
}
