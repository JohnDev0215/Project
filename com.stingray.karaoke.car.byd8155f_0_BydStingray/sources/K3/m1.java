package k3;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.Surface;
import d5.C1072A;
import d5.C1077F;
import e2.BinderC1198f;
import e2.C1195c;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h3.C1350a;
import h5.C1373E;
import io.sentry.C1458d0;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import u.C2465e;

/* JADX INFO: loaded from: classes.dex */
public final class m1 extends Binder implements InterfaceC1655m {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ int f18013i = 0;
    public final WeakReference c;
    public final l3.U d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final B8.t f18014e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Set f18015f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public d5.c0 f18016g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f18017h;

    public m1(I0 i02) {
        attachInterface(this, "androidx.media3.session.IMediaSession");
        this.c = new WeakReference(i02);
        this.d = l3.U.a(i02.f17799f);
        this.f18014e = new B8.t(i02);
        this.f18015f = Collections.synchronizedSet(new HashSet());
        this.f18016g = d5.c0.f13408t;
    }

    public static h5.y q1(I0 i02, C1679y0 c1679y0, int i7, l1 l1Var, h2.g gVar) {
        if (i02.h()) {
            return h5.v.f15564m;
        }
        h5.y yVar = (h5.y) l1Var.a(i02, c1679y0, i7);
        C1373E c1373e = new C1373E();
        yVar.b(new io.sentry.android.replay.p(i02, c1373e, gVar, yVar, 7), h5.r.l);
        return c1373e;
    }

    public static void u1(C1679y0 c1679y0, int i7, C1 c1) {
        try {
            InterfaceC1677x0 interfaceC1677x0 = c1679y0.d;
            AbstractC1346c.k(interfaceC1677x0);
            interfaceC1677x0.g(i7, c1);
        } catch (RemoteException e10) {
            AbstractC1346c.A("MediaSessionStub", "Failed to send result to controller " + c1679y0, e10);
        }
    }

    public static G2.a v1(h2.g gVar) {
        return new G2.a(new G2.a(gVar, 24), 23);
    }

    @Override // k3.InterfaceC1655m
    public final void A0(InterfaceC1651k interfaceC1651k, int i7, int i10, IBinder iBinder) {
        if (interfaceC1651k == null || iBinder == null || i10 < 0) {
            return;
        }
        try {
            d5.I iA = BinderC1198f.a(iBinder);
            C1077F c1077fU = d5.I.u();
            for (int i11 = 0; i11 < iA.size(); i11++) {
                Bundle bundle = (Bundle) iA.get(i11);
                bundle.getClass();
                c1077fU.a(e2.F.a(bundle));
            }
            s1(interfaceC1651k, i7, 20, new C1638e1(new C1458d0(new U0(1, c1077fU.h()), 22, new V0(this, i10, 3)), 1));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e10);
        }
    }

    @Override // k3.InterfaceC1655m
    public final void B(InterfaceC1651k interfaceC1651k, int i7) {
        if (interfaceC1651k == null) {
            return;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            I0 i02 = (I0) this.c.get();
            if (i02 != null && !i02.h()) {
                AbstractC1343A.K(i02.l, new A2.m(this, 27, interfaceC1651k));
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // k3.InterfaceC1655m
    public final void B0(InterfaceC1651k interfaceC1651k, int i7, int i10, long j3) {
        if (interfaceC1651k == null || i10 < 0) {
            return;
        }
        s1(interfaceC1651k, i7, 10, new G2.a(new Y2.m(i10, j3, this), 23));
    }

    @Override // k3.InterfaceC1655m
    public final void C(InterfaceC1651k interfaceC1651k, int i7, int i10) {
        if (interfaceC1651k == null || i10 < 0) {
            return;
        }
        s1(interfaceC1651k, i7, 25, v1(new C1616J(i10, 5)));
    }

    @Override // k3.InterfaceC1655m
    public final void C0(InterfaceC1651k interfaceC1651k, int i7, Bundle bundle) {
        C1639f c1639f;
        if (interfaceC1651k == null || bundle == null) {
            return;
        }
        try {
            C1 c1A = C1.a(bundle);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                B8.t tVar = this.f18014e;
                IBinder iBinderAsBinder = interfaceC1651k.asBinder();
                synchronized (tVar.f808n) {
                    try {
                        C1679y0 c1679y0X = tVar.X(iBinderAsBinder);
                        c1639f = c1679y0X != null ? (C1639f) ((C2465e) tVar.f809p).get(c1679y0X) : null;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                E0.X x8 = c1639f != null ? c1639f.f17943b : null;
                if (x8 == null) {
                    return;
                }
                x8.v(i7, c1A);
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for SessionResult", e10);
        }
    }

    @Override // k3.InterfaceC1655m
    public final void D(InterfaceC1651k interfaceC1651k, int i7, Bundle bundle) {
        if (interfaceC1651k == null || bundle == null) {
            return;
        }
        try {
            C1642g c1642gA = C1642g.a(bundle);
            int callingUid = Binder.getCallingUid();
            int callingPid = Binder.getCallingPid();
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            if (callingPid == 0) {
                callingPid = c1642gA.d;
            }
            try {
                l3.T t10 = new l3.T(c1642gA.c, callingPid, callingUid);
                n1(interfaceC1651k, new C1679y0(t10, c1642gA.f17958a, c1642gA.f17959b, this.d.b(t10), new i1(interfaceC1651k, c1642gA.f17959b), c1642gA.f17960e));
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for ConnectionRequest", e10);
        }
    }

    @Override // k3.InterfaceC1655m
    public final void F(InterfaceC1651k interfaceC1651k, int i7, int i10, Bundle bundle) {
        if (interfaceC1651k == null || bundle == null || i10 < 0) {
            return;
        }
        try {
            s1(interfaceC1651k, i7, 20, new C1638e1(new C1458d0(new W0(1, e2.F.a(bundle)), 22, new V0(this, i10, 2)), 1));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e10);
        }
    }

    @Override // k3.InterfaceC1655m
    public final void F0(InterfaceC1651k interfaceC1651k, int i7, Bundle bundle, Bundle bundle2) {
        if (interfaceC1651k == null || bundle == null || bundle2 == null) {
            return;
        }
        try {
            y1 y1VarA = y1.a(bundle);
            o1(interfaceC1651k, i7, y1VarA, 0, new C1638e1(new C1458d0(y1VarA, 19, bundle2), 1));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for SessionCommand", e10);
        }
    }

    @Override // k3.InterfaceC1655m
    public final void H(InterfaceC1651k interfaceC1651k, int i7, int i10, int i11, IBinder iBinder) {
        if (interfaceC1651k == null || iBinder == null || i10 < 0 || i11 < i10) {
            return;
        }
        try {
            d5.I iA = BinderC1198f.a(iBinder);
            C1077F c1077fU = d5.I.u();
            for (int i12 = 0; i12 < iA.size(); i12++) {
                Bundle bundle = (Bundle) iA.get(i12);
                bundle.getClass();
                c1077fU.a(e2.F.a(bundle));
            }
            s1(interfaceC1651k, i7, 20, new C1638e1(new C1458d0(new U0(0, c1077fU.h()), 22, new T0(i10, i11, this)), 1));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e10);
        }
    }

    @Override // k3.InterfaceC1655m
    public final void H0(InterfaceC1651k interfaceC1651k, int i7) {
        C1679y0 c1679y0X;
        if (interfaceC1651k == null || (c1679y0X = this.f18014e.X(interfaceC1651k.asBinder())) == null) {
            return;
        }
        t1(c1679y0X, i7, 9, v1(new X0(6)));
    }

    @Override // k3.InterfaceC1655m
    public final void I(InterfaceC1651k interfaceC1651k, int i7, float f10) {
        if (interfaceC1651k == null || f10 < 0.0f || f10 > 1.0f) {
            return;
        }
        s1(interfaceC1651k, i7, 24, v1(new C1615I(f10, 2)));
    }

    @Override // k3.InterfaceC1655m
    public final void J(InterfaceC1651k interfaceC1651k, int i7, float f10) {
        if (interfaceC1651k == null || f10 <= 0.0f) {
            return;
        }
        s1(interfaceC1651k, i7, 13, v1(new C1615I(f10, 1)));
    }

    @Override // k3.InterfaceC1655m
    public final void K(InterfaceC1651k interfaceC1651k, int i7, Bundle bundle) {
        if (interfaceC1651k == null || bundle == null) {
            return;
        }
        try {
            s1(interfaceC1651k, i7, 13, v1(new C1609C(new e2.P(bundle.getFloat(e2.P.f13993e, 1.0f), bundle.getFloat(e2.P.f13994f, 1.0f)), 2)));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for PlaybackParameters", e10);
        }
    }

    @Override // k3.InterfaceC1655m
    public final void K0(InterfaceC1651k interfaceC1651k, int i7) {
        C1679y0 c1679y0X;
        if (interfaceC1651k == null || (c1679y0X = this.f18014e.X(interfaceC1651k.asBinder())) == null) {
            return;
        }
        t1(c1679y0X, i7, 1, v1(new C1350a(28)));
    }

    @Override // k3.InterfaceC1655m
    public final void L0(InterfaceC1651k interfaceC1651k, int i7, IBinder iBinder) {
        if (interfaceC1651k == null || iBinder == null) {
            return;
        }
        try {
            d5.I iA = BinderC1198f.a(iBinder);
            C1077F c1077fU = d5.I.u();
            for (int i10 = 0; i10 < iA.size(); i10++) {
                Bundle bundle = (Bundle) iA.get(i10);
                bundle.getClass();
                c1077fU.a(e2.F.a(bundle));
            }
            s1(interfaceC1651k, i7, 20, new C1638e1(new C1458d0(new U0(2, c1077fU.h()), 22, new X0(8)), 1));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e10);
        }
    }

    @Override // k3.InterfaceC1655m
    public final void O(InterfaceC1651k interfaceC1651k, int i7, Bundle bundle) {
        if (interfaceC1651k == null || bundle == null) {
            return;
        }
        try {
            s1(interfaceC1651k, i7, 29, v1(new C1458d0(this, 18, e2.l0.b(bundle))));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for TrackSelectionParameters", e10);
        }
    }

    @Override // k3.InterfaceC1655m
    public final void O0(InterfaceC1651k interfaceC1651k, int i7, final long j3) {
        if (interfaceC1651k == null) {
            return;
        }
        s1(interfaceC1651k, i7, 5, v1(new h2.g() { // from class: k3.b1
            @Override // h2.g
            public final void accept(Object obj) {
                ((u1) obj).k(j3);
            }
        }));
    }

    @Override // k3.InterfaceC1655m
    public final void P0(InterfaceC1651k interfaceC1651k, int i7, final int i10, final int i11, final int i12) {
        if (interfaceC1651k == null || i10 < 0 || i11 < i10 || i12 < 0) {
            return;
        }
        s1(interfaceC1651k, i7, 20, v1(new h2.g() { // from class: k3.Z0
            @Override // h2.g
            public final void accept(Object obj) {
                ((u1) obj).o0(i10, i11, i12);
            }
        }));
    }

    @Override // k3.InterfaceC1655m
    public final void S(InterfaceC1651k interfaceC1651k, int i7, Bundle bundle, long j3) {
        if (interfaceC1651k == null || bundle == null) {
            return;
        }
        try {
            s1(interfaceC1651k, i7, 31, new C1638e1(new C1458d0(new io.sentry.instrumentation.file.c(j3, e2.F.a(bundle)), 21, new X0(19)), 1));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e10);
        }
    }

    @Override // k3.InterfaceC1655m
    public final void U(InterfaceC1651k interfaceC1651k, int i7) {
        if (interfaceC1651k == null) {
            return;
        }
        s1(interfaceC1651k, i7, 8, v1(new C1350a(29)));
    }

    @Override // k3.InterfaceC1655m
    public final void U0(InterfaceC1651k interfaceC1651k, int i7, int i10, int i11) {
        if (interfaceC1651k == null || i10 < 0 || i11 < i10) {
            return;
        }
        s1(interfaceC1651k, i7, 20, new G2.a(new T0(i10, i11, this), 23));
    }

    @Override // k3.InterfaceC1655m
    public final void V(InterfaceC1651k interfaceC1651k, int i7, Bundle bundle) {
        if (interfaceC1651k == null || bundle == null) {
            return;
        }
        try {
            s1(interfaceC1651k, i7, 19, v1(new C1680z(e2.I.b(bundle), 1)));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for MediaMetadata", e10);
        }
    }

    @Override // k3.InterfaceC1655m
    public final void V0(InterfaceC1651k interfaceC1651k, int i7, IBinder iBinder, int i10, long j3) {
        if (interfaceC1651k == null || iBinder == null) {
            return;
        }
        if (i10 == -1 || i10 >= 0) {
            try {
                d5.I iA = BinderC1198f.a(iBinder);
                C1077F c1077fU = d5.I.u();
                for (int i11 = 0; i11 < iA.size(); i11++) {
                    Bundle bundle = (Bundle) iA.get(i11);
                    bundle.getClass();
                    c1077fU.a(e2.F.a(bundle));
                }
                s1(interfaceC1651k, i7, 20, new C1638e1(new C1458d0(new Y2.m(i10, j3, c1077fU.h()), 21, new X0(19)), 1));
            } catch (RuntimeException e10) {
                AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e10);
            }
        }
    }

    @Override // k3.InterfaceC1655m
    public final void W0(InterfaceC1651k interfaceC1651k, int i7, int i10, int i11) {
        if (interfaceC1651k == null || i10 < 0 || i11 < 0) {
            return;
        }
        s1(interfaceC1651k, i7, 20, v1(new W(i10, i11, 2)));
    }

    @Override // k3.InterfaceC1655m
    public final void X0(InterfaceC1651k interfaceC1651k, int i7) {
        if (interfaceC1651k == null) {
            return;
        }
        s1(interfaceC1651k, i7, 20, v1(new X0(17)));
    }

    @Override // k3.InterfaceC1655m
    public final void Y(InterfaceC1651k interfaceC1651k, int i7, int i10) {
        if (interfaceC1651k == null) {
            return;
        }
        s1(interfaceC1651k, i7, 34, v1(new C1616J(i10, 3)));
    }

    @Override // k3.InterfaceC1655m
    public final void Z0(InterfaceC1651k interfaceC1651k, int i7) {
        if (interfaceC1651k == null) {
            return;
        }
        s1(interfaceC1651k, i7, 26, v1(new X0(0)));
    }

    @Override // k3.InterfaceC1655m
    public final void a0(InterfaceC1651k interfaceC1651k, int i7, boolean z6) {
        if (interfaceC1651k == null) {
            return;
        }
        s1(interfaceC1651k, i7, 26, v1(new C1613G(2, z6)));
    }

    @Override // k3.InterfaceC1655m
    public final void a1(InterfaceC1651k interfaceC1651k, int i7, int i10, int i11) {
        if (interfaceC1651k == null || i10 < 0) {
            return;
        }
        s1(interfaceC1651k, i7, 33, v1(new W(i10, i11, 1)));
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override // k3.InterfaceC1655m
    public final void b0(InterfaceC1651k interfaceC1651k, int i7) {
        if (interfaceC1651k == null) {
            return;
        }
        s1(interfaceC1651k, i7, 26, v1(new X0(9)));
    }

    @Override // k3.InterfaceC1655m
    public final void b1(InterfaceC1651k interfaceC1651k, int i7, final boolean z6, final int i10) {
        if (interfaceC1651k == null) {
            return;
        }
        s1(interfaceC1651k, i7, 34, v1(new h2.g() { // from class: k3.a1
            @Override // h2.g
            public final void accept(Object obj) {
                ((u1) obj).c0(i10, z6);
            }
        }));
    }

    @Override // k3.InterfaceC1655m
    public final void c1(InterfaceC1651k interfaceC1651k, int i7) {
        C1679y0 c1679y0X;
        if (interfaceC1651k == null || (c1679y0X = this.f18014e.X(interfaceC1651k.asBinder())) == null) {
            return;
        }
        t1(c1679y0X, i7, 12, v1(new X0(5)));
    }

    @Override // k3.InterfaceC1655m
    public final void d0(InterfaceC1651k interfaceC1651k, int i7, int i10) {
        if (interfaceC1651k == null) {
            return;
        }
        if (i10 == 2 || i10 == 0 || i10 == 1) {
            s1(interfaceC1651k, i7, 15, v1(new C1616J(i10, 4)));
        }
    }

    @Override // k3.InterfaceC1655m
    public final void e0(InterfaceC1651k interfaceC1651k, int i7) {
        if (interfaceC1651k == null) {
            return;
        }
        s1(interfaceC1651k, i7, 2, v1(new X0(14)));
    }

    @Override // k3.InterfaceC1655m
    public final void e1(InterfaceC1651k interfaceC1651k, int i7, int i10, Bundle bundle) {
        if (interfaceC1651k == null || bundle == null || i10 < 0) {
            return;
        }
        try {
            s1(interfaceC1651k, i7, 20, new C1638e1(new C1458d0(new W0(0, e2.F.a(bundle)), 22, new V0(this, i10, 1)), 1));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e10);
        }
    }

    @Override // k3.InterfaceC1655m
    public final void f0(InterfaceC1651k interfaceC1651k, int i7) {
        C1679y0 c1679y0X;
        if (interfaceC1651k == null || (c1679y0X = this.f18014e.X(interfaceC1651k.asBinder())) == null) {
            return;
        }
        t1(c1679y0X, i7, 1, v1(new C1458d0(this, 20, c1679y0X)));
    }

    @Override // k3.InterfaceC1655m
    public final void f1(InterfaceC1651k interfaceC1651k, int i7) {
        C1679y0 c1679y0X;
        if (interfaceC1651k == null || (c1679y0X = this.f18014e.X(interfaceC1651k.asBinder())) == null) {
            return;
        }
        t1(c1679y0X, i7, 3, v1(new X0(12)));
    }

    @Override // k3.InterfaceC1655m
    public final void g0(InterfaceC1651k interfaceC1651k, int i7, Bundle bundle, boolean z6) {
        if (interfaceC1651k == null || bundle == null) {
            return;
        }
        try {
            s1(interfaceC1651k, i7, 31, new C1638e1(new C1458d0(new Y0(0, e2.F.a(bundle), z6), 21, new X0(19)), 1));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e10);
        }
    }

    @Override // k3.InterfaceC1655m
    public final void g1(InterfaceC1651k interfaceC1651k, int i7, boolean z6) {
        if (interfaceC1651k == null) {
            return;
        }
        s1(interfaceC1651k, i7, 1, v1(new C1613G(1, z6)));
    }

    @Override // k3.InterfaceC1655m
    public final void i0(InterfaceC1651k interfaceC1651k, int i7) {
        if (interfaceC1651k == null) {
            return;
        }
        s1(interfaceC1651k, i7, 6, v1(new X0(3)));
    }

    @Override // k3.InterfaceC1655m
    public final void j1(InterfaceC1651k interfaceC1651k, int i7) {
        C1679y0 c1679y0X;
        if (interfaceC1651k == null || (c1679y0X = this.f18014e.X(interfaceC1651k.asBinder())) == null) {
            return;
        }
        t1(c1679y0X, i7, 7, v1(new X0(2)));
    }

    @Override // k3.InterfaceC1655m
    public final void k0(InterfaceC1651k interfaceC1651k, int i7, int i10) {
        if (interfaceC1651k == null || i10 < 0) {
            return;
        }
        s1(interfaceC1651k, i7, 20, new G2.a(new V0(this, i10, 4), 23));
    }

    @Override // k3.InterfaceC1655m
    public final void l0(InterfaceC1651k interfaceC1651k, int i7, int i10) {
        if (interfaceC1651k == null) {
            return;
        }
        s1(interfaceC1651k, i7, 34, v1(new C1616J(i10, 2)));
    }

    @Override // k3.InterfaceC1655m
    public final void l1(InterfaceC1651k interfaceC1651k, int i7, int i10) {
        if (interfaceC1651k == null || i10 < 0) {
            return;
        }
        s1(interfaceC1651k, i7, 10, new G2.a(new V0(this, i10, 0), 23));
    }

    public final void n1(InterfaceC1651k interfaceC1651k, C1679y0 c1679y0) {
        if (interfaceC1651k != null) {
            I0 i02 = (I0) this.c.get();
            if (i02 == null || i02.h()) {
                try {
                    interfaceC1651k.a();
                } catch (RemoteException unused) {
                }
            } else {
                this.f18015f.add(c1679y0);
                AbstractC1343A.K(i02.l, new io.sentry.android.replay.p(this, c1679y0, i02, interfaceC1651k, 6));
            }
        }
    }

    @Override // k3.InterfaceC1655m
    public final void o(InterfaceC1651k interfaceC1651k, int i7, boolean z6) {
        if (interfaceC1651k == null) {
            return;
        }
        s1(interfaceC1651k, i7, 14, v1(new C1613G(3, z6)));
    }

    public final void o1(InterfaceC1651k interfaceC1651k, final int i7, final y1 y1Var, final int i10, final l1 l1Var) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            final I0 i02 = (I0) this.c.get();
            if (i02 != null && !i02.h()) {
                final C1679y0 c1679y0X = this.f18014e.X(interfaceC1651k.asBinder());
                if (c1679y0X == null) {
                    return;
                }
                AbstractC1343A.K(i02.l, new Runnable() { // from class: k3.c1
                    @Override // java.lang.Runnable
                    public final void run() {
                        B8.t tVar = this.l.f18014e;
                        C1679y0 c1679y0 = c1679y0X;
                        if (tVar.a0(c1679y0)) {
                            y1 y1Var2 = y1Var;
                            int i11 = i7;
                            if (y1Var2 != null) {
                                if (!tVar.e0(c1679y0, y1Var2)) {
                                    m1.u1(c1679y0, i11, new C1(-4));
                                    return;
                                }
                            } else if (!tVar.d0(c1679y0, i10)) {
                                m1.u1(c1679y0, i11, new C1(-4));
                                return;
                            }
                            l1Var.a(i02, c1679y0, i11);
                        }
                    }
                });
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i7, Parcel parcel, Parcel parcel2, int i10) {
        if (i7 >= 1 && i7 <= 16777215) {
            parcel.enforceInterface("androidx.media3.session.IMediaSession");
        }
        if (i7 == 1598968902) {
            parcel2.writeString("androidx.media3.session.IMediaSession");
            return true;
        }
        switch (i7) {
            case 3002:
                I(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readFloat());
                return true;
            case 3003:
                C(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                return true;
            case 3004:
                Z0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt());
                return true;
            case 3005:
                b0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt());
                return true;
            case 3006:
                a0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0);
                return true;
            case 3007:
                g0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), (Bundle) n1.b(parcel, Bundle.CREATOR), true);
                return true;
            case 3008:
                S(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), (Bundle) n1.b(parcel, Bundle.CREATOR), parcel.readLong());
                return true;
            case 3009:
                g0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), (Bundle) n1.b(parcel, Bundle.CREATOR), parcel.readInt() != 0);
                return true;
            case 3010:
                u0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readStrongBinder(), true);
                return true;
            case 3011:
                u0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readStrongBinder(), parcel.readInt() != 0);
                return true;
            case 3012:
                V0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readStrongBinder(), parcel.readInt(), parcel.readLong());
                return true;
            case 3013:
                g1(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0);
                return true;
            case 3014:
                C0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), (Bundle) n1.b(parcel, Bundle.CREATOR));
                return true;
            case 3015:
                D(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), (Bundle) n1.b(parcel, Bundle.CREATOR));
                return true;
            case 3016:
                InterfaceC1651k interfaceC1651kN1 = BinderC1658n0.n1(parcel.readStrongBinder());
                int i11 = parcel.readInt();
                Parcelable.Creator creator = Bundle.CREATOR;
                F0(interfaceC1651kN1, i11, (Bundle) n1.b(parcel, creator), (Bundle) n1.b(parcel, creator));
                return true;
            case 3017:
                d0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                return true;
            case 3018:
                o(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0);
                return true;
            case 3019:
                k0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                return true;
            case 3020:
                U0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;
            case 3021:
                X0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt());
                return true;
            case 3022:
                W0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;
            case 3023:
                P0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;
            case 3024:
                f0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt());
                return true;
            case 3025:
                K0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt());
                return true;
            case 3026:
                e0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt());
                return true;
            case 3027:
                K(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), (Bundle) n1.b(parcel, Bundle.CREATOR));
                return true;
            case 3028:
                J(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readFloat());
                return true;
            case 3029:
                InterfaceC1651k interfaceC1651kN12 = BinderC1658n0.n1(parcel.readStrongBinder());
                int i12 = parcel.readInt();
                Bundle bundle = (Bundle) n1.b(parcel, Bundle.CREATOR);
                if (interfaceC1651kN12 != null && bundle != null) {
                    try {
                        s1(interfaceC1651kN12, i12, 20, new C1638e1(new C1458d0(new W0(2, e2.F.a(bundle)), 22, new X0(10)), 1));
                    } catch (RuntimeException e10) {
                        AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e10);
                    }
                }
                return true;
            case 3030:
                e1(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), (Bundle) n1.b(parcel, Bundle.CREATOR));
                return true;
            case 3031:
                L0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readStrongBinder());
                return true;
            case 3032:
                A0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readStrongBinder());
                return true;
            case 3033:
                V(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), (Bundle) n1.b(parcel, Bundle.CREATOR));
                return true;
            case 3034:
                f1(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt());
                return true;
            case 3035:
                B(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt());
                return true;
            case 3036:
                w(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt());
                return true;
            case 3037:
                l1(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                return true;
            case 3038:
                O0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readLong());
                return true;
            case 3039:
                B0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readLong());
                return true;
            case 3040:
                v(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt());
                return true;
            case 3041:
                c1(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt());
                return true;
            case 3042:
                i0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt());
                return true;
            case 3043:
                U(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt());
                return true;
            case 3044:
                s(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), (Surface) n1.b(parcel, Surface.CREATOR));
                return true;
            case 3045:
                p(BinderC1658n0.n1(parcel.readStrongBinder()));
                return true;
            case 3046:
                j1(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt());
                return true;
            case 3047:
                H0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt());
                return true;
            case 3048:
                O(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), (Bundle) n1.b(parcel, Bundle.CREATOR));
                return true;
            case 3049:
                InterfaceC1651k interfaceC1651kN13 = BinderC1658n0.n1(parcel.readStrongBinder());
                int i13 = parcel.readInt();
                String string = parcel.readString();
                Bundle bundle2 = (Bundle) n1.b(parcel, Bundle.CREATOR);
                if (interfaceC1651kN13 != null && string != null && bundle2 != null) {
                    if (TextUtils.isEmpty(string)) {
                        AbstractC1346c.z("MediaSessionStub", "setRatingWithMediaId(): Ignoring empty mediaId");
                    } else {
                        try {
                            o1(interfaceC1651kN13, i13, null, 40010, new C1638e1(new X0(string, e2.X.a(bundle2)), 1));
                        } catch (RuntimeException e11) {
                            AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for Rating", e11);
                        }
                    }
                }
                return true;
            case 3050:
                InterfaceC1651k interfaceC1651kN14 = BinderC1658n0.n1(parcel.readStrongBinder());
                int i14 = parcel.readInt();
                Bundle bundle3 = (Bundle) n1.b(parcel, Bundle.CREATOR);
                if (interfaceC1651kN14 != null && bundle3 != null) {
                    try {
                        e2.X.a(bundle3);
                        o1(interfaceC1651kN14, i14, null, 40010, new C1638e1(new X0(20), 1));
                    } catch (RuntimeException e12) {
                        AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for Rating", e12);
                    }
                }
                return true;
            case 3051:
                a1(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;
            case 3052:
                Y(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                return true;
            case 3053:
                l0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                return true;
            case 3054:
                b1(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0, parcel.readInt());
                return true;
            case 3055:
                F(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), (Bundle) n1.b(parcel, Bundle.CREATOR));
                return true;
            case 3056:
                H(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readStrongBinder());
                return true;
            case 3057:
                t0(BinderC1658n0.n1(parcel.readStrongBinder()), parcel.readInt(), (Bundle) n1.b(parcel, Bundle.CREATOR), parcel.readInt() != 0);
                return true;
            default:
                switch (i7) {
                    case 4001:
                        InterfaceC1651k interfaceC1651kN15 = BinderC1658n0.n1(parcel.readStrongBinder());
                        int i15 = parcel.readInt();
                        Bundle bundle4 = (Bundle) n1.b(parcel, Bundle.CREATOR);
                        if (interfaceC1651kN15 != null) {
                            if (bundle4 == null) {
                                o1(interfaceC1651kN15, i15, null, 50000, new C1638e1(new X0(13), 0));
                            } else {
                                try {
                                    C1660o0.a(bundle4);
                                    o1(interfaceC1651kN15, i15, null, 50000, new C1638e1(new X0(13), 0));
                                } catch (RuntimeException e13) {
                                    AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for LibraryParams", e13);
                                }
                            }
                        }
                        return true;
                    case 4002:
                        InterfaceC1651k interfaceC1651kN16 = BinderC1658n0.n1(parcel.readStrongBinder());
                        int i16 = parcel.readInt();
                        String string2 = parcel.readString();
                        if (interfaceC1651kN16 != null) {
                            if (TextUtils.isEmpty(string2)) {
                                AbstractC1346c.z("MediaSessionStub", "getItem(): Ignoring empty mediaId");
                            } else {
                                o1(interfaceC1651kN16, i16, null, 50004, new C1638e1(new X0(4), 0));
                            }
                        }
                        return true;
                    case 4003:
                        InterfaceC1651k interfaceC1651kN17 = BinderC1658n0.n1(parcel.readStrongBinder());
                        int i17 = parcel.readInt();
                        String string3 = parcel.readString();
                        int i18 = parcel.readInt();
                        int i19 = parcel.readInt();
                        Bundle bundle5 = (Bundle) n1.b(parcel, Bundle.CREATOR);
                        if (interfaceC1651kN17 != null) {
                            if (TextUtils.isEmpty(string3)) {
                                AbstractC1346c.z("MediaSessionStub", "getChildren(): Ignoring empty parentId");
                            } else if (i18 < 0) {
                                AbstractC1346c.z("MediaSessionStub", "getChildren(): Ignoring negative page");
                            } else if (i19 < 1) {
                                AbstractC1346c.z("MediaSessionStub", "getChildren(): Ignoring pageSize less than 1");
                            } else if (bundle5 == null) {
                                o1(interfaceC1651kN17, i17, null, 50003, new C1638e1(new C1350a(27), 0));
                            } else {
                                try {
                                    C1660o0.a(bundle5);
                                    o1(interfaceC1651kN17, i17, null, 50003, new C1638e1(new C1350a(27), 0));
                                } catch (RuntimeException e14) {
                                    AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for LibraryParams", e14);
                                }
                            }
                        }
                        return true;
                    case 4004:
                        InterfaceC1651k interfaceC1651kN18 = BinderC1658n0.n1(parcel.readStrongBinder());
                        int i20 = parcel.readInt();
                        String string4 = parcel.readString();
                        Bundle bundle6 = (Bundle) n1.b(parcel, Bundle.CREATOR);
                        if (interfaceC1651kN18 != null) {
                            if (TextUtils.isEmpty(string4)) {
                                AbstractC1346c.z("MediaSessionStub", "search(): Ignoring empty query");
                            } else if (bundle6 == null) {
                                o1(interfaceC1651kN18, i20, null, 50005, new C1638e1(new X0(18), 0));
                            } else {
                                try {
                                    C1660o0.a(bundle6);
                                    o1(interfaceC1651kN18, i20, null, 50005, new C1638e1(new X0(18), 0));
                                } catch (RuntimeException e15) {
                                    AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for LibraryParams", e15);
                                }
                            }
                        }
                        return true;
                    case 4005:
                        InterfaceC1651k interfaceC1651kN19 = BinderC1658n0.n1(parcel.readStrongBinder());
                        int i21 = parcel.readInt();
                        String string5 = parcel.readString();
                        int i22 = parcel.readInt();
                        int i23 = parcel.readInt();
                        Bundle bundle7 = (Bundle) n1.b(parcel, Bundle.CREATOR);
                        if (interfaceC1651kN19 != null) {
                            if (TextUtils.isEmpty(string5)) {
                                AbstractC1346c.z("MediaSessionStub", "getSearchResult(): Ignoring empty query");
                            } else if (i22 < 0) {
                                AbstractC1346c.z("MediaSessionStub", "getSearchResult(): Ignoring negative page");
                            } else if (i23 < 1) {
                                AbstractC1346c.z("MediaSessionStub", "getSearchResult(): Ignoring pageSize less than 1");
                            } else if (bundle7 == null) {
                                o1(interfaceC1651kN19, i21, null, 50006, new C1638e1(new X0(11), 0));
                            } else {
                                try {
                                    C1660o0.a(bundle7);
                                    o1(interfaceC1651kN19, i21, null, 50006, new C1638e1(new X0(11), 0));
                                } catch (RuntimeException e16) {
                                    AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for LibraryParams", e16);
                                }
                            }
                        }
                        return true;
                    case 4006:
                        InterfaceC1651k interfaceC1651kN110 = BinderC1658n0.n1(parcel.readStrongBinder());
                        int i24 = parcel.readInt();
                        String string6 = parcel.readString();
                        Bundle bundle8 = (Bundle) n1.b(parcel, Bundle.CREATOR);
                        if (interfaceC1651kN110 != null) {
                            if (TextUtils.isEmpty(string6)) {
                                AbstractC1346c.z("MediaSessionStub", "subscribe(): Ignoring empty parentId");
                            } else if (bundle8 == null) {
                                o1(interfaceC1651kN110, i24, null, 50001, new C1638e1(new X0(16), 0));
                            } else {
                                try {
                                    C1660o0.a(bundle8);
                                    o1(interfaceC1651kN110, i24, null, 50001, new C1638e1(new X0(16), 0));
                                } catch (RuntimeException e17) {
                                    AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for LibraryParams", e17);
                                }
                            }
                        }
                        return true;
                    case 4007:
                        InterfaceC1651k interfaceC1651kN111 = BinderC1658n0.n1(parcel.readStrongBinder());
                        int i25 = parcel.readInt();
                        String string7 = parcel.readString();
                        if (interfaceC1651kN111 != null) {
                            if (TextUtils.isEmpty(string7)) {
                                AbstractC1346c.z("MediaSessionStub", "unsubscribe(): Ignoring empty parentId");
                            } else {
                                o1(interfaceC1651kN111, i25, null, 50002, new C1638e1(new C1350a(26), 0));
                            }
                        }
                        return true;
                    default:
                        return super.onTransact(i7, parcel, parcel2, i10);
                }
        }
    }

    @Override // k3.InterfaceC1655m
    public final void p(InterfaceC1651k interfaceC1651k) {
        if (interfaceC1651k == null) {
            return;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            I0 i02 = (I0) this.c.get();
            if (i02 != null && !i02.h()) {
                C1679y0 c1679y0X = this.f18014e.X(interfaceC1651k.asBinder());
                if (c1679y0X != null) {
                    AbstractC1343A.K(i02.l, new A2.m(this, 28, c1679y0X));
                }
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public final q1 p1(q1 q1Var) {
        d5.I iA = q1Var.f18069D.a();
        C1077F c1077fU = d5.I.u();
        C1072A c1072a = new C1072A(4);
        for (int i7 = 0; i7 < iA.size(); i7++) {
            e2.m0 m0Var = (e2.m0) iA.get(i7);
            e2.g0 g0Var = m0Var.f14222b;
            String string = (String) this.f18016g.get(g0Var);
            if (string == null) {
                StringBuilder sb = new StringBuilder();
                int i10 = this.f18017h;
                this.f18017h = i10 + 1;
                int i11 = AbstractC1343A.f15195a;
                sb.append(Integer.toString(i10, 36));
                sb.append("-");
                sb.append(g0Var.f14105b);
                string = sb.toString();
            }
            c1072a.v(g0Var, string);
            c1077fU.c(new e2.m0(m0Var.f14222b.a(string), m0Var.c, m0Var.d, m0Var.f14223e));
        }
        this.f18016g = c1072a.g();
        q1 q1VarB = q1Var.b(new e2.n0(c1077fU.h()));
        e2.l0 l0Var = q1VarB.f18070E;
        if (l0Var.f14194A.isEmpty()) {
            return q1VarB;
        }
        e2.k0 k0VarC = l0Var.a().c();
        d5.s0 it = l0Var.f14194A.values().iterator();
        while (it.hasNext()) {
            e2.h0 h0Var = (e2.h0) it.next();
            e2.g0 g0Var2 = h0Var.f14118a;
            String str = (String) this.f18016g.get(g0Var2);
            if (str != null) {
                k0VarC.a(new e2.h0(g0Var2.a(str), h0Var.f14119b));
            } else {
                k0VarC.a(h0Var);
            }
        }
        return q1VarB.o(k0VarC.b());
    }

    public final int r1(C1679y0 c1679y0, u1 u1Var, int i7) {
        if (u1Var.R0(17)) {
            B8.t tVar = this.f18014e;
            if (!tVar.b0(c1679y0, 17) && tVar.b0(c1679y0, 16)) {
                return u1Var.b0() + i7;
            }
        }
        return i7;
    }

    @Override // k3.InterfaceC1655m
    public final void s(InterfaceC1651k interfaceC1651k, int i7, Surface surface) {
        if (interfaceC1651k == null) {
            return;
        }
        s1(interfaceC1651k, i7, 27, v1(new G2.a(surface, 22)));
    }

    public final void s1(InterfaceC1651k interfaceC1651k, int i7, int i10, l1 l1Var) {
        C1679y0 c1679y0X = this.f18014e.X(interfaceC1651k.asBinder());
        if (c1679y0X != null) {
            t1(c1679y0X, i7, i10, l1Var);
        }
    }

    @Override // k3.InterfaceC1655m
    public final void t0(InterfaceC1651k interfaceC1651k, int i7, Bundle bundle, boolean z6) {
        if (interfaceC1651k == null || bundle == null) {
            return;
        }
        try {
            s1(interfaceC1651k, i7, 35, v1(new Y0(1, C1195c.a(bundle), z6)));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for AudioAttributes", e10);
        }
    }

    public final void t1(final C1679y0 c1679y0, final int i7, final int i10, final l1 l1Var) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            final I0 i02 = (I0) this.c.get();
            if (i02 != null && !i02.h()) {
                AbstractC1343A.K(i02.l, new Runnable() { // from class: k3.d1
                    @Override // java.lang.Runnable
                    public final void run() {
                        B8.t tVar = this.l.f18014e;
                        final C1679y0 c1679y02 = c1679y0;
                        int i11 = i10;
                        boolean zB0 = tVar.b0(c1679y02, i11);
                        final int i12 = i7;
                        if (!zB0) {
                            m1.u1(c1679y02, i12, new C1(-4));
                            return;
                        }
                        final I0 i03 = i02;
                        i03.r(c1679y02);
                        i03.f17798e.getClass();
                        final l1 l1Var2 = l1Var;
                        if (i11 != 27) {
                            tVar.y(c1679y02, i11, new InterfaceC1636e() { // from class: k3.h1
                                @Override // k3.InterfaceC1636e
                                public final h5.y run() {
                                    return (h5.y) l1Var2.a(i03, c1679y02, i12);
                                }
                            });
                            return;
                        }
                        i03.f17815x = c1679y02;
                        l1Var2.a(i03, c1679y02, i12);
                        i03.f17815x = null;
                        tVar.y(c1679y02, i11, new C1644g1());
                    }
                });
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // k3.InterfaceC1655m
    public final void u0(InterfaceC1651k interfaceC1651k, int i7, IBinder iBinder, boolean z6) {
        if (interfaceC1651k == null || iBinder == null) {
            return;
        }
        try {
            d5.I iA = BinderC1198f.a(iBinder);
            C1077F c1077fU = d5.I.u();
            for (int i10 = 0; i10 < iA.size(); i10++) {
                Bundle bundle = (Bundle) iA.get(i10);
                bundle.getClass();
                c1077fU.a(e2.F.a(bundle));
            }
            s1(interfaceC1651k, i7, 20, new C1638e1(new C1458d0(new Y0(2, c1077fU.h(), z6), 21, new X0(19)), 1));
        } catch (RuntimeException e10) {
            AbstractC1346c.A("MediaSessionStub", "Ignoring malformed Bundle for MediaItem", e10);
        }
    }

    @Override // k3.InterfaceC1655m
    public final void v(InterfaceC1651k interfaceC1651k, int i7) {
        C1679y0 c1679y0X;
        if (interfaceC1651k == null || (c1679y0X = this.f18014e.X(interfaceC1651k.asBinder())) == null) {
            return;
        }
        t1(c1679y0X, i7, 11, v1(new X0(1)));
    }

    @Override // k3.InterfaceC1655m
    public final void w(InterfaceC1651k interfaceC1651k, int i7) {
        if (interfaceC1651k == null) {
            return;
        }
        s1(interfaceC1651k, i7, 4, v1(new X0(15)));
    }
}
