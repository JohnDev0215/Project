package P7;

import A.F;
import T7.z;
import h8.AbstractC1387d;
import java.util.List;
import n7.u;
import o8.C2057b;
import o8.C2061f;
import t8.C2448a;
import t8.C2449b;
import t8.v;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class l extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5443m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ n f5444n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l(n nVar, int i7) {
        super(0);
        this.f5443m = i7;
        this.f5444n = nVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        n nVar = this.f5444n;
        switch (this.f5443m) {
            case 0:
                return nVar.f5448a.f8921p.e();
            default:
                z zVar = nVar.f5448a;
                C2061f c2061f = R7.e.f7532a;
                N7.i iVar = zVar.f8921p;
                A7.m.f(iVar, "<this>");
                List listJ = AbstractC1387d.j(new R7.j(iVar, N7.n.f4855m, n7.z.s(new m7.i(R7.e.f7532a, new v("This member is not fully supported by Kotlin compiler, so it may be absent or have different signature in next major version")), new m7.i(R7.e.f7533b, new C2448a((Object) new R7.j(iVar, N7.n.o, n7.z.s(new m7.i(R7.e.d, new v("")), new m7.i(R7.e.f7534e, new C2449b(u.l, new F(iVar, 29))))))), new m7.i(R7.e.c, new t8.i(C2057b.j(N7.n.f4856n), C2061f.e("WARNING"))))));
                return listJ.isEmpty() ? R7.g.f7535a : new R7.i(0, listJ);
        }
    }
}
