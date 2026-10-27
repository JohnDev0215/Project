package a8;

import A7.A;
import A7.B;
import A7.m;
import A7.s;
import E8.l;
import H7.w;
import N0.q;
import N7.n;
import W7.C0840d;
import c8.C1036a;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class g extends b {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ w[] f11211g;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final E8.i f11212f;

    static {
        B b10 = A.f443a;
        f11211g = new w[]{b10.h(new s(b10.b(g.class), "allValueArguments", "getAllValueArguments()Ljava/util/Map;"))};
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(C0840d c0840d, q qVar) {
        super(qVar, c0840d, n.f4855m);
        m.f(qVar, "c");
        l lVar = ((C1036a) qVar.f4399m).f12411a;
        f fVar = f.f11210m;
        lVar.getClass();
        this.f11212f = new E8.i(lVar, fVar);
    }

    @Override // a8.b, R7.b
    public final Map c() {
        return (Map) C7.a.N(this.f11212f, f11211g[0]);
    }
}
