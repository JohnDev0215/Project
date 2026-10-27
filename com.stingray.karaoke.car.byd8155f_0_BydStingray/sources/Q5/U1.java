package Q5;

import D.AbstractC0120b;
import D.AbstractC0127i;
import D.AbstractC0133o;
import D.AbstractC0139v;
import D.C0138u;
import D.C0141x;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import F0.C0221p;
import S8.AbstractC0727y;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.InterfaceC0744h0;
import android.content.Context;
import android.util.Pair;
import androidx.compose.foundation.layout.HorizontalAlignElement;
import com.stingray.drivemodedetector.DriveMode;
import com.stingray.karaoke.car.player.SongQueuePosition;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.karaoke.car.utils.LayoutMode;
import com.stingray.kca.model.KaraokeSongResponseV2;
import h2.AbstractC1347d;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import k6.AbstractC1692D;
import n6.C1966G;
import org.xmlpull.v1.XmlPullParserException;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class U1 implements InterfaceC2786o {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f6579m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ KaraokeSongResponseV2 f6580n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f6581p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Serializable f6582q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f6583r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ m7.c f6584s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Serializable f6585t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ Object f6586u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ Object f6587v;

    public U1(D.L l, DriveMode driveMode, LoadingInterface$LoadingState loadingInterface$LoadingState, KaraokeSongResponseV2 karaokeSongResponseV2, List list, C1966G c1966g, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, InterfaceC2782k interfaceC2782k, InterfaceC2785n interfaceC2785n, boolean z6) {
        this.o = l;
        this.f6580n = karaokeSongResponseV2;
        this.f6581p = interfaceC2782k;
        this.f6582q = driveMode;
        this.f6583r = list;
        this.f6584s = interfaceC2785n;
        this.f6585t = loadingInterface$LoadingState;
        this.f6586u = interfaceC2772a;
        this.f6579m = z6;
        this.f6587v = interfaceC2772a2;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) throws XmlPullParserException, IOException {
        switch (this.l) {
            case 0:
                C0138u c0138u = (C0138u) obj;
                C0761q c0761q = (C0761q) obj2;
                int iIntValue = ((Number) obj3).intValue();
                A7.m.f(c0138u, "$this$BoxWithConstraints");
                if ((iIntValue & 6) == 0) {
                    iIntValue |= c0761q.f(c0138u) ? 4 : 2;
                }
                if ((iIntValue & 19) == 18 && c0761q.x()) {
                    c0761q.K();
                } else {
                    Pair pairN = h6.f.n(c0138u.b(), 0.0f, c0761q, 2);
                    float fB = c0138u.b();
                    m6.b bVar = m6.c.f19252a;
                    bVar.getClass();
                    c0761q.Q(1538789292);
                    LayoutMode layoutMode = (LayoutMode) c0761q.k(AbstractC1692D.f18238a);
                    if (layoutMode != LayoutMode.NARROW_COMPACT && layoutMode != LayoutMode.WIDE_COMPACT && layoutMode != LayoutMode.WIDE_NORMAL && layoutMode != LayoutMode.WIDE_ULTRA && layoutMode != LayoutMode.NARROW_ULTRA && layoutMode != LayoutMode.SQUARE_ULTRA) {
                        if (layoutMode != LayoutMode.SQUARE_COMPACT && layoutMode != LayoutMode.NARROW_NORMAL && layoutMode != LayoutMode.SQUARE_NORMAL) {
                            throw new A9.k();
                        }
                        if (!M5.b.r(c0761q, -609592150, bVar, c0761q, false)) {
                            c0761q.Q(-609591093);
                            m6.b.j(bVar, c0761q);
                            c0761q.p(false);
                        }
                    }
                    c0761q.p(false);
                    float f10 = fB * 0.4f;
                    g0.l lVar = g0.l.f14684a;
                    g0.o oVarJ = androidx.compose.foundation.layout.a.j(androidx.compose.foundation.layout.c.c(h6.r.t(lVar, h6.r.q(c0761q)), 1.0f), (D.L) this.o);
                    g0.e eVar = g0.b.f14673y;
                    C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.c, eVar, c0761q, 48);
                    int i7 = c0761q.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
                    g0.o oVarD = g0.a.d(c0761q, oVarJ);
                    InterfaceC0161k.f1681b.getClass();
                    C0159i c0159i = C0160j.f1678b;
                    c0761q.U();
                    if (c0761q.f8303O) {
                        c0761q.l(c0159i);
                    } else {
                        c0761q.d0();
                    }
                    C0158h c0158h = C0160j.f1679e;
                    C0735d.V(c0761q, c0141xA, c0158h);
                    C0158h c0158h2 = C0160j.d;
                    C0735d.V(c0761q, interfaceC0744h0M, c0158h2);
                    C0158h c0158h3 = C0160j.f1680f;
                    if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i7))) {
                        AbstractC1347d.l(i7, c0761q, i7, c0158h3);
                    }
                    C0158h c0158h4 = C0160j.c;
                    C0735d.V(c0761q, oVarD, c0158h4);
                    g0.o oVarD2 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
                    D.S sB = D.P.b(AbstractC0127i.f1076a, g0.b.f14669u, c0761q, 0);
                    int i10 = c0761q.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
                    g0.o oVarD3 = g0.a.d(c0761q, oVarD2);
                    c0761q.U();
                    if (c0761q.f8303O) {
                        c0761q.l(c0159i);
                    } else {
                        c0761q.d0();
                    }
                    C0735d.V(c0761q, sB, c0158h);
                    C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
                    if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i10))) {
                        AbstractC1347d.l(i10, c0761q, i10, c0158h3);
                    }
                    C0735d.V(c0761q, oVarD3, c0158h4);
                    AbstractC0120b.b(c0761q, D.T.b(lVar, 1.0f));
                    Object obj4 = pairN.second;
                    A7.m.e(obj4, "second");
                    g0.o oVarD4 = androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.p(lVar, ((a1.e) obj4).l, f10), 1.0f);
                    KaraokeSongResponseV2 karaokeSongResponseV2 = this.f6580n;
                    com.bumptech.glide.c.j(oVarD4, karaokeSongResponseV2, null, c0761q, 0);
                    g0.o oVarB = D.T.b(lVar, 1.0f);
                    C0.K kE = AbstractC0133o.e(g0.b.l, false);
                    int i11 = c0761q.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M3 = c0761q.m();
                    g0.o oVarD5 = g0.a.d(c0761q, oVarB);
                    c0761q.U();
                    if (c0761q.f8303O) {
                        c0761q.l(c0159i);
                    } else {
                        c0761q.d0();
                    }
                    C0735d.V(c0761q, kE, c0158h);
                    C0735d.V(c0761q, interfaceC0744h0M3, c0158h2);
                    if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                        AbstractC1347d.l(i11, c0761q, i11, c0158h3);
                    }
                    C0735d.V(c0761q, oVarD5, c0158h4);
                    io.sentry.config.a.k(0, c0761q, androidx.compose.foundation.layout.b.f11474a.a(lVar, g0.b.f14662m), (InterfaceC2772a) this.f6587v, this.f6579m);
                    c0761q.p(true);
                    c0761q.p(true);
                    AbstractC0120b.b(c0761q, androidx.compose.foundation.layout.c.e(lVar, bVar.r0(c0761q) / 4));
                    l9.d.t(androidx.compose.foundation.layout.c.d(new HorizontalAlignElement(eVar), 1.0f), karaokeSongResponseV2 != null ? karaokeSongResponseV2.f13137b : null, karaokeSongResponseV2 != null ? karaokeSongResponseV2.c : null, 3, c0761q, 0);
                    AbstractC0120b.b(c0761q, androidx.compose.foundation.layout.c.e(lVar, bVar.r0(c0761q) / 2));
                    l9.d.u(new HorizontalAlignElement(eVar), karaokeSongResponseV2 != null ? karaokeSongResponseV2.f13141h : null, karaokeSongResponseV2 != null ? karaokeSongResponseV2.f13142i : null, c0761q, 0);
                    c0761q.Q(-1872151819);
                    if (karaokeSongResponseV2 != null) {
                        A7.m.a(karaokeSongResponseV2.f13146n, Boolean.TRUE);
                    }
                    c0761q.p(false);
                    AbstractC0120b.b(c0761q, androidx.compose.foundation.layout.c.e(lVar, bVar.r0(c0761q)));
                    g0.o oVarP = androidx.compose.foundation.layout.a.p(androidx.compose.foundation.layout.c.q(lVar, bVar.A(c0761q), 0.0f, 2));
                    if (karaokeSongResponseV2 != null) {
                        A7.m.a(karaokeSongResponseV2.f13146n, Boolean.TRUE);
                    }
                    io.sentry.config.a.A(oVarP, (InterfaceC2782k) this.f6581p, (DriveMode) this.f6582q, c0761q, 0);
                    AbstractC0120b.b(c0761q, androidx.compose.foundation.layout.c.e(lVar, bVar.r0(c0761q)));
                    g0.o oVarD6 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
                    Object obj5 = pairN.first;
                    A7.m.e(obj5, "first");
                    io.sentry.config.a.w(oVarD6, (List) this.f6583r, ((Number) obj5).intValue(), (InterfaceC2785n) this.f6584s, (LoadingInterface$LoadingState) this.f6585t, (InterfaceC2772a) this.f6586u, karaokeSongResponseV2 != null ? karaokeSongResponseV2.f13136a : null, c0761q, 6);
                    c0761q.p(true);
                }
                return m7.q.f19267a;
            default:
                String str = (String) obj;
                C0761q c0761q2 = (C0761q) obj2;
                int iIntValue2 = ((Number) obj3).intValue();
                A7.m.f(str, "id");
                if ((iIntValue2 & 6) == 0) {
                    iIntValue2 |= c0761q2.f(str) ? 4 : 2;
                }
                if ((iIntValue2 & 19) == 18 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    c0761q2.Q(63932107);
                    boolean zF = c0761q2.f((SongQueuePosition) this.o) | c0761q2.h((Context) this.f6581p) | c0761q2.g(this.f6579m);
                    final C0221p c0221p = (C0221p) this.f6582q;
                    boolean zF2 = zF | c0761q2.f(c0221p);
                    final C0221p c0221p2 = (C0221p) this.f6583r;
                    boolean zF3 = zF2 | c0761q2.f(c0221p2);
                    final C0221p c0221p3 = (C0221p) this.f6584s;
                    boolean zF4 = zF3 | c0761q2.f(c0221p3);
                    final X1 x12 = (X1) this.f6585t;
                    boolean zF5 = zF4 | c0761q2.f(x12) | c0761q2.h(this.f6580n) | c0761q2.h((F6.b) this.f6586u) | c0761q2.h((V8.f0) this.f6587v);
                    Object objG = c0761q2.G();
                    if (zF5 || objG == C0753m.f8270a) {
                        final KaraokeSongResponseV2 karaokeSongResponseV22 = this.f6580n;
                        final SongQueuePosition songQueuePosition = (SongQueuePosition) this.o;
                        final Context context = (Context) this.f6581p;
                        final boolean z6 = this.f6579m;
                        final F6.b bVar2 = (F6.b) this.f6586u;
                        final V8.f0 f0Var = (V8.f0) this.f6587v;
                        objG = new InterfaceC2772a() { // from class: Q5.a2
                            @Override // z7.InterfaceC2772a
                            public final Object a() {
                                Z8.e eVar2 = S8.E.f8084a;
                                AbstractC0727y.w(AbstractC0727y.a(X8.m.f10573a), null, null, new C0552b2(songQueuePosition, context, z6, c0221p, c0221p2, c0221p3, x12, karaokeSongResponseV22, bVar2, f0Var, null), 3);
                                return m7.q.f19267a;
                            }
                        };
                        c0761q2.a0(objG);
                    }
                    c0761q2.p(false);
                    io.sentry.config.a.j(str, null, null, (InterfaceC2772a) objG, c0761q2, iIntValue2 & 14);
                }
                return m7.q.f19267a;
        }
    }

    public U1(SongQueuePosition songQueuePosition, Context context, boolean z6, C0221p c0221p, C0221p c0221p2, C0221p c0221p3, X1 x12, KaraokeSongResponseV2 karaokeSongResponseV2, F6.b bVar, V8.f0 f0Var) {
        this.o = songQueuePosition;
        this.f6581p = context;
        this.f6579m = z6;
        this.f6582q = c0221p;
        this.f6583r = c0221p2;
        this.f6584s = c0221p3;
        this.f6585t = x12;
        this.f6580n = karaokeSongResponseV2;
        this.f6586u = bVar;
        this.f6587v = f0Var;
    }
}
