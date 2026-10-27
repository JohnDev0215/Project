package X5;

import K5.C0372e;
import N1.C0420a;
import N1.InterfaceC0428i;
import N5.C0451g;
import N5.C0463t;
import Q5.C0618s1;
import S8.AbstractC0727y;
import T.C0735d;
import T.C0738e0;
import V8.AbstractC0828q;
import V8.C0819h;
import V8.C0827p;
import V8.C0832v;
import V8.InterfaceC0817f;
import androidx.media3.exoplayer.ExoPlayer;
import com.stingray.companion.CompanionManager;
import com.stingray.karaoke.car.player.PlayerActionType;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import com.stingray.karaoke.car.ui.player.PlaybackType;
import com.stingray.karaoke.car.ui.player.SideMenuType;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.karaoke.car.utils.ToastType;
import com.stingray.shared.logs.segment.ActionOwner;
import d2.C1063a;
import h2.AbstractC1346c;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import k3.C1672v;
import k6.C1691C;
import k6.C1696d;
import k6.C1704l;
import k6.C1705m;
import kotlin.Metadata;
import n2.C1914n;
import n2.C1915o;
import n2.C1923x;
import n6.C1967H;
import n6.C1972e;
import n7.AbstractC1998m;
import q7.InterfaceC2189c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"LX5/D0;", "Lb6/i;", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class D0 extends b6.i {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final n6.u f10101A;

    /* JADX INFO: renamed from: A0, reason: collision with root package name */
    public final V8.f0 f10102A0;
    public final C1967H B;
    public final V8.N B0;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final n6.z f10103C;

    /* JADX INFO: renamed from: C0, reason: collision with root package name */
    public final V8.N f10104C0;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final I6.d f10105D;

    /* JADX INFO: renamed from: D0, reason: collision with root package name */
    public final V8.N f10106D0;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final G0 f10107E;

    /* JADX INFO: renamed from: E0, reason: collision with root package name */
    public final V8.N f10108E0;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final C0738e0 f10109F;

    /* JADX INFO: renamed from: F0, reason: collision with root package name */
    public final V8.N f10110F0;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final x6.e f10111G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public S8.k0 f10112H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final V8.f0 f10113I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final V8.N f10114J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final V8.f0 f10115K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final V8.N f10116L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public final V8.f0 f10117M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public final V8.f0 f10118N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public final V8.f0 f10119O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final V8.N f10120P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public final V8.N f10121Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public final V8.N f10122R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public final V8.N f10123S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public final V8.N f10124T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public final V8.N f10125U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public final V8.N f10126V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public final V8.N f10127W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public final V8.N f10128X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public final V8.N f10129Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public final V8.N f10130Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public final V8.d0 f10131a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public final V8.N f10132b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public final V8.N f10133c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public final V8.f0 f10134d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final V8.N f10135e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public final V8.N f10136f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final V8.N f10137g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public final V8.N f10138h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public final V8.N f10139i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public final V8.N f10140j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public final V8.N f10141k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public final V8.N f10142l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public final V8.N f10143m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public final V8.N f10144n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public final V8.N f10145o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public final V8.N f10146p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public final V8.N f10147q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public final V8.N f10148r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public final V8.N f10149s0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final W5.e f10150t;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public final V8.N f10151t0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final V0 f10152u;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public final V8.N f10153u0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Y5.d f10154v;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public final V8.N f10155v0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final C6.b f10156w;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public final V8.N f10157w0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final N5.Z f10158x;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public final V8.N f10159x0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final C0886u f10160y;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public final V8.N f10161y0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final C1972e f10162z;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public final V8.N f10163z0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public D0(C1705m c1705m, W5.e eVar, V0 v02, C1704l c1704l, Y5.d dVar, N5.q0 q0Var, C6.b bVar, N5.Z z6, C0886u c0886u, K5.u uVar, j6.n nVar, C1972e c1972e, n6.u uVar2, C1967H c1967h, n6.z zVar, C1696d c1696d, L5.i iVar, C1691C c1691c, K5.t tVar, I6.d dVar2, G0 g02) {
        super(c1705m, eVar, uVar, iVar, nVar);
        A7.m.f(c1705m, "languageManager");
        A7.m.f(eVar, "appNavigationManager");
        A7.m.f(v02, "songResourceManager");
        A7.m.f(c1704l, "driveModeManager");
        A7.m.f(dVar, "playerBackgroundManager");
        A7.m.f(q0Var, "songQueueManager");
        A7.m.f(bVar, "navigationLogger");
        A7.m.f(z6, "playerManager");
        A7.m.f(c0886u, "playerProgressManager");
        A7.m.f(uVar, "karaokeItemRepository");
        A7.m.f(nVar, "profileManager");
        A7.m.f(c1972e, "gameLoopManager");
        A7.m.f(uVar2, "microphoneManager");
        A7.m.f(c1967h, "scoreManager");
        A7.m.f(zVar, "midiManager");
        A7.m.f(c1696d, "devSettingsManager");
        A7.m.f(iVar, "deepLinkManager");
        A7.m.f(c1691c, "uxRestrictionManager");
        A7.m.f(tVar, "dataStoreManager");
        A7.m.f(dVar2, "scoringSoundFxManager");
        A7.m.f(g02, "scoringCountdownManager");
        this.f10150t = eVar;
        this.f10152u = v02;
        this.f10154v = dVar;
        this.f10156w = bVar;
        this.f10158x = z6;
        this.f10160y = c0886u;
        this.f10162z = c1972e;
        this.f10101A = uVar2;
        this.B = c1967h;
        this.f10103C = zVar;
        this.f10105D = dVar2;
        this.f10107E = g02;
        Boolean bool = Boolean.FALSE;
        this.f10109F = C0735d.O(bool, T.S.f8224q);
        this.f10111G = new x6.e();
        V8.f0 f0VarC = AbstractC0828q.c(bool);
        this.f10113I = f0VarC;
        this.f10114J = new V8.N(f0VarC);
        V8.f0 f0VarC2 = AbstractC0828q.c(null);
        this.f10115K = f0VarC2;
        this.f10116L = new V8.N(f0VarC2);
        this.f10117M = AbstractC0828q.c(null);
        V8.f0 f0VarC3 = AbstractC0828q.c(n7.u.l);
        this.f10118N = f0VarC3;
        V8.f0 f0VarC4 = AbstractC0828q.c(bool);
        this.f10119O = f0VarC4;
        this.f10120P = new V8.N(f0VarC4);
        this.f10121Q = dVar.f10788f;
        this.f10122R = new V8.N(eVar.f9820e);
        V8.N n10 = c1704l.d;
        this.f10123S = n10;
        this.f10124T = c1691c.f18231e;
        this.f10125U = c1691c.f18232f;
        this.f10126V = c1691c.f18236j;
        V8.N n11 = v02.f10211h;
        this.f10127W = n11;
        this.f10128X = z6.f4661h0;
        this.f10129Y = z6.f4659g0;
        V8.N n12 = z6.f4635N;
        this.f10130Z = n12;
        CompanionManager.Companion.getClass();
        this.f10131a0 = CompanionManager.companionUrl;
        V8.N n13 = q0Var.f4748r;
        this.f10132b0 = n13;
        V8.N n14 = z6.f4639R;
        this.f10133c0 = n14;
        this.f10134d0 = c1696d.f18259r;
        this.f10135e0 = z6.f4633L;
        V8.N n15 = c0886u.f10390i;
        this.f10136f0 = n15;
        V8.N n16 = z6.f4631J;
        this.f10137g0 = n16;
        this.f10138h0 = g02.f10172b;
        this.f10139i0 = z6.f4647Z;
        this.f10140j0 = z6.f4651b0;
        this.f10141k0 = AbstractC0828q.m(new C0881r0(n14, 4), androidx.lifecycle.a0.i(this), V8.V.a(), bool);
        this.f10142l0 = AbstractC0828q.m(new A3.n(f0VarC3, 2), androidx.lifecycle.a0.i(this), V8.V.a(), bool);
        this.f10143m0 = AbstractC0828q.m(new A3.n(f0VarC3, 3), androidx.lifecycle.a0.i(this), V8.V.a(), null);
        this.f10144n0 = c1691c.f18235i;
        V8.N n17 = z6.f4637P;
        this.f10145o0 = AbstractC0828q.m(new C0881r0(n17, 5), androidx.lifecycle.a0.i(this), V8.V.a(), null);
        this.f10146p0 = AbstractC0828q.m(new C0881r0(n17, 6), androidx.lifecycle.a0.i(this), V8.V.a(), null);
        this.f10147q0 = AbstractC0828q.m(new C0881r0(n17, 7), androidx.lifecycle.a0.i(this), V8.V.a(), null);
        V8.N nM = AbstractC0828q.m(new C0881r0(n17, 8), androidx.lifecycle.a0.i(this), V8.V.a(), bool);
        this.f10148r0 = nM;
        this.f10149s0 = AbstractC0828q.m(new V8.J(new C0372e(((InterfaceC0428i) ((C0420a) tVar.a(tVar.f3657a)).l).d(), 4), nM, new C0864i0(3, null, 0)), androidx.lifecycle.a0.i(this), V8.V.a(), bool);
        this.f10151t0 = AbstractC0828q.m(new C0881r0(z6.f4643V, 9), androidx.lifecycle.a0.i(this), V8.V.a(), null);
        this.f10153u0 = AbstractC0828q.m(new C0881r0(z6.f4641T, 10), androidx.lifecycle.a0.i(this), V8.V.a(), 1);
        C0862h0 c0862h0 = new C0862h0(4, null);
        V8.N n18 = q0Var.f4752v;
        this.f10155v0 = AbstractC0828q.m(new C0832v(new InterfaceC0817f[]{n18, n13, n17}, c0862h0), androidx.lifecycle.a0.i(this), V8.V.a(), bool);
        C0881r0 c0881r0 = new C0881r0(n18, 0);
        C1063a c1063aI = androidx.lifecycle.a0.i(this);
        V8.c0 c0VarA = V8.V.a();
        Boolean bool2 = Boolean.TRUE;
        this.f10157w0 = AbstractC0828q.m(c0881r0, c1063aI, c0VarA, bool2);
        V8.N nM2 = AbstractC0828q.m(new C0881r0(n10, 1), androidx.lifecycle.a0.i(this), V8.V.a(), bool);
        this.f10159x0 = nM2;
        this.f10161y0 = AbstractC0828q.m(new V8.J(n12, nM2, new C0876o0(3, null)), androidx.lifecycle.a0.i(this), V8.V.a(), bool);
        V8.N nM3 = AbstractC0828q.m(new C0881r0(n12, 2), androidx.lifecycle.a0.i(this), V8.V.a(), bool);
        this.f10163z0 = nM3;
        V8.f0 f0VarC5 = AbstractC0828q.c(bool2);
        this.f10102A0 = f0VarC5;
        int i7 = 3;
        this.B0 = AbstractC0828q.m(new C0832v(new InterfaceC0817f[]{f0VarC5, n10, nM3, n12}, 5, new C0870l0(5, null)), androidx.lifecycle.a0.i(this), V8.V.a(), bool2);
        V8.N nM4 = AbstractC0828q.m(new C0881r0(n10, i7), androidx.lifecycle.a0.i(this), V8.V.a(), bool);
        this.f10104C0 = nM4;
        this.f10106D0 = AbstractC0828q.m(new C0819h(new C0827p(250L, new V8.J(n15, n16, new C0866j0(3, null)), null), i7), androidx.lifecycle.a0.i(this), V8.V.a(), Float.valueOf(0.0f));
        this.f10108E0 = AbstractC0828q.m(new V8.J(new C0819h(new C0827p(1000L, n15, null), 3), nM4, new C0874n0(3, null)), androidx.lifecycle.a0.i(this), V8.V.a(), bool2);
        this.f10110F0 = AbstractC0828q.m(new V8.J(n15, n11, new C0872m0(this, null)), androidx.lifecycle.a0.i(this), V8.V.a(), bool);
        m(NavigationType.PLAYER);
        Y5.d dVar3 = this.f10154v;
        V8.f0 f0Var = dVar3.f10787e;
        C1915o c1915o = new C1915o(dVar3.f10785a);
        AbstractC1346c.j(!c1915o.f19715t);
        w2.o oVar = dVar3.d;
        oVar.getClass();
        c1915o.f19702e = new C1914n(oVar, 1);
        C1923x c1923xA = c1915o.a();
        c1923xA.h(1);
        c1923xA.q(true);
        c1923xA.f19771m.a(dVar3.c);
        f0Var.getClass();
        f0Var.m(null, c1923xA);
        N5.Z z10 = this.f10158x;
        z10.getClass();
        z10.f4677z.add(this);
        AbstractC0727y.w(androidx.lifecycle.a0.i(this), null, null, new U(this, null), 3);
        AbstractC0727y.w(androidx.lifecycle.a0.i(this), null, null, new V(this, null), 3);
        AbstractC0727y.w(androidx.lifecycle.a0.i(this), null, null, new Y(this, null), 3);
        AbstractC0727y.w(androidx.lifecycle.a0.i(this), null, null, new C0848a0(this, null), 3);
        AbstractC0727y.w(androidx.lifecycle.a0.i(this), null, null, new C0852c0(this, nVar, null), 3);
        AbstractC0727y.w(androidx.lifecycle.a0.i(this), null, null, new C0854d0(this, null), 3);
        AbstractC0727y.w(androidx.lifecycle.a0.i(this), null, null, new C0858f0(this, nVar, null), 3);
        AbstractC0727y.w(androidx.lifecycle.a0.i(this), null, null, new T(this, null), 3);
        this.f12245m = new C0888v(this, 2);
    }

    @Override // b6.f, androidx.lifecycle.f0
    public final void d() {
        super.d();
        Y5.d dVar = this.f10154v;
        V8.f0 f0Var = dVar.f10787e;
        ((C1923x) ((ExoPlayer) f0Var.getValue())).H(dVar.c);
        ((C1923x) ((ExoPlayer) f0Var.getValue())).a();
        N5.Z z6 = this.f10158x;
        z6.getClass();
        z6.f4677z.remove(this);
    }

    @Override // b6.f
    public final Object f(InterfaceC2189c interfaceC2189c) {
        l(LoadingInterface$LoadingState.Done);
        return m7.q.f19267a;
    }

    @Override // b6.f
    public final void j() throws IllegalAccessException, InvocationTargetException {
        super.j();
        Boolean bool = Boolean.FALSE;
        V8.f0 f0Var = this.f10119O;
        f0Var.getClass();
        f0Var.m(null, bool);
        this.f10115K.l(null);
        this.f10117M.l(null);
        V8.f0 f0Var2 = this.f10113I;
        f0Var2.getClass();
        f0Var2.m(null, bool);
        n7.u uVar = n7.u.l;
        V8.f0 f0Var3 = this.f10118N;
        f0Var3.getClass();
        f0Var3.m(null, uVar);
        V8.f0 f0Var4 = this.f10102A0;
        f0Var4.getClass();
        f0Var4.m(null, bool);
        S8.k0 k0Var = this.f10112H;
        if (k0Var != null) {
            k0Var.g(null);
        }
    }

    public final void o() {
        io.sentry.hints.i iVar = k6.o.f18284a;
        io.sentry.hints.i.g();
        n7.u uVar = n7.u.l;
        V8.f0 f0Var = this.f10118N;
        f0Var.getClass();
        f0Var.m(null, uVar);
        if (((Boolean) ((V8.f0) this.f10133c0.l).getValue()).booleanValue()) {
            if (((V8.f0) this.f10130Z.l).getValue() == PlayerStatus.PLAYING) {
                this.f10158x.s(ActionOwner.HOST);
                ToastType toastType = ToastType.SCORING_ON_HOLD;
                A7.m.f(toastType, "toastType");
                h6.e.c(h6.e.d(toastType), new b0.b(-1064300120, new C0618s1(toastType, 2), true));
            }
            this.f10107E.b();
            if (((Boolean) ((V8.f0) this.f10139i0.l).getValue()).booleanValue()) {
                q();
            }
        }
        W5.e eVar = this.f10150t;
        V8.f0 f0Var2 = eVar.f9820e;
        if (((Boolean) f0Var2.getValue()).booleanValue()) {
            f0Var2.m(null, Boolean.FALSE);
            eVar.e();
        }
    }

    public final void p(SideMenuType sideMenuType) {
        A7.m.f(sideMenuType, "type");
        V8.f0 f0Var = this.f10118N;
        ArrayList arrayListA0 = AbstractC1998m.A0((Collection) f0Var.getValue());
        arrayListA0.remove(sideMenuType);
        f0Var.getClass();
        f0Var.m(null, arrayListA0);
    }

    public final void q() {
        N5.Z z6 = this.f10158x;
        z6.q();
        C1672v c1672v = z6.f4667p;
        if (c1672v != null) {
            c1672v.k(0L);
        } else {
            A7.m.j("mediaController");
            throw null;
        }
    }

    public final void r(PlaybackType playbackType) {
        A7.m.f(playbackType, "type");
        s();
        int i7 = AbstractC0860g0.f10255a[playbackType.ordinal()];
        N5.Z z6 = this.f10158x;
        if (i7 == 1) {
            ActionOwner actionOwner = ActionOwner.HOST;
            z6.getClass();
            A7.m.f(actionOwner, "actionOwner");
            if (((PlayerStatus) z6.f4634M.getValue()).getIsActive()) {
                z6.s(actionOwner);
                return;
            } else {
                z6.t(actionOwner);
                return;
            }
        }
        if (i7 == 2) {
            z6.E(true, ActionOwner.HOST);
            return;
        }
        if (i7 != 3) {
            throw new A9.k();
        }
        ActionOwner actionOwner2 = ActionOwner.HOST;
        z6.getClass();
        A7.m.f(actionOwner2, "actionOwner");
        z6.d(new C0451g(PlayerActionType.SkipPrevious, new C0463t(z6, actionOwner2, null, false)));
    }

    public final void s() {
        Boolean bool = Boolean.TRUE;
        V8.f0 f0Var = this.f10102A0;
        f0Var.getClass();
        f0Var.m(null, bool);
        t();
    }

    public final void t() throws IllegalAccessException, InvocationTargetException {
        S8.k0 k0Var = this.f10112H;
        if (k0Var != null) {
            k0Var.g(null);
        }
        this.f10112H = AbstractC0727y.w(androidx.lifecycle.a0.i(this), null, null, new C0868k0(this, null), 3);
    }
}
