package K;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import l0.EnumC1726q;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: K.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0367z extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0331b0 f3464m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f3465n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ T0.z f3466p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ T0.y f3467q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ T0.m f3468r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ T0.s f3469s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ O.K f3470t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0725w f3471u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ H.c f3472v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0367z(C0331b0 c0331b0, boolean z6, boolean z10, T0.z zVar, T0.y yVar, T0.m mVar, T0.s sVar, O.K k10, InterfaceC0725w interfaceC0725w, H.c cVar) {
        super(1);
        this.f3464m = c0331b0;
        this.f3465n = z6;
        this.o = z10;
        this.f3466p = zVar;
        this.f3467q = yVar;
        this.f3468r = mVar;
        this.f3469s = sVar;
        this.f3470t = k10;
        this.f3471u = interfaceC0725w;
        this.f3472v = cVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        G0 g0D;
        EnumC1726q enumC1726q = (EnumC1726q) obj;
        C0331b0 c0331b0 = this.f3464m;
        if (c0331b0.b() != enumC1726q.a()) {
            c0331b0.f3223f.setValue(Boolean.valueOf(enumC1726q.a()));
            boolean zB = c0331b0.b();
            T0.s sVar = this.f3469s;
            T0.y yVar = this.f3467q;
            if (zB && this.f3465n && !this.o) {
                X.m(this.f3466p, c0331b0, yVar, this.f3468r, sVar);
            } else {
                X.i(c0331b0);
            }
            if (enumC1726q.a() && (g0D = c0331b0.d()) != null) {
                AbstractC0727y.w(this.f3471u, null, null, new C0366y(this.f3472v, yVar, c0331b0, g0D, sVar, null), 3);
            }
            if (!enumC1726q.a()) {
                this.f3470t.e(null);
            }
        }
        return m7.q.f19267a;
    }
}
