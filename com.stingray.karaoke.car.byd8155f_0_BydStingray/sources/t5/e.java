package T5;

import C0.K;
import D.AbstractC0133o;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import H7.H;
import T.C0735d;
import T.C0761q;
import T.InterfaceC0744h0;
import com.stingray.karaoke.car.player.PlayerStatus;
import g0.o;
import h2.AbstractC1347d;
import m7.q;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class e implements InterfaceC2785n {
    public final /* synthetic */ o l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ float f8687m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f8688n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8689p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8690q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ float f8691r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ PlayerStatus f8692s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8693t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ boolean f8694u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8695v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ boolean f8696w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final /* synthetic */ int f8697x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ int f8698y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8699z;

    public e(o oVar, float f10, boolean z6, boolean z10, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, float f11, PlayerStatus playerStatus, InterfaceC2772a interfaceC2772a3, boolean z11, InterfaceC2772a interfaceC2772a4, boolean z12, int i7, int i10, InterfaceC2772a interfaceC2772a5) {
        this.l = oVar;
        this.f8687m = f10;
        this.f8688n = z6;
        this.o = z10;
        this.f8689p = interfaceC2772a;
        this.f8690q = interfaceC2772a2;
        this.f8691r = f11;
        this.f8692s = playerStatus;
        this.f8693t = interfaceC2772a3;
        this.f8694u = z11;
        this.f8695v = interfaceC2772a4;
        this.f8696w = z12;
        this.f8697x = i7;
        this.f8698y = i10;
        this.f8699z = interfaceC2772a5;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            K kE = AbstractC0133o.e(g0.b.f14664p, false);
            int i7 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            o oVarD = g0.a.d(c0761q, this.l);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i7))) {
                AbstractC1347d.l(i7, c0761q, i7, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            H.a(null, m6.c.f19252a.F(c0761q), b0.g.b(-316695762, new d(this.f8687m, this.f8688n, this.o, this.f8689p, this.f8690q, this.f8691r, this.f8692s, this.f8693t, this.f8694u, this.f8695v, this.f8696w, this.f8697x, this.f8698y, this.f8699z), c0761q), c0761q, 3456);
            c0761q.p(true);
        }
        return q.f19267a;
    }
}
