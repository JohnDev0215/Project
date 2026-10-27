package O;

import a.AbstractC0904a;
import k0.C1591c;
import n0.C1877f;
import n0.C1883l;
import n0.C1884m;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: O.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0476g extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f4964m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f4965n;
    public final /* synthetic */ boolean o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0476g(long j3, InterfaceC2772a interfaceC2772a, boolean z6) {
        super(1);
        this.f4964m = j3;
        this.f4965n = interfaceC2772a;
        this.o = z6;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        C1591c c1591c = (C1591c) obj;
        C1877f c1877fY = AbstractC0904a.y(c1591c, m0.f.d(c1591c.l.e()) / 2.0f);
        C1884m c1884m = C1884m.f19441a;
        long j3 = this.f4964m;
        return c1591c.a(new E.s(this.f4965n, this.o, c1877fY, new C1883l(j3, 5, c1884m.a(j3, 5))));
    }
}
