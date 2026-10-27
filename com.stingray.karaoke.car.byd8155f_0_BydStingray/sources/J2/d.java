package J2;

import A.s1;
import B2.E;
import B2.F;
import B2.G;
import B2.y;

/* JADX INFO: loaded from: classes.dex */
public final class d extends y {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ F f3061b;
    public final /* synthetic */ s1 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(s1 s1Var, F f10, F f11) {
        super(f10);
        this.c = s1Var;
        this.f3061b = f11;
    }

    @Override // B2.y, B2.F
    public final E j(long j3) {
        E eJ = this.f3061b.j(j3);
        G g7 = eJ.f508a;
        long j10 = g7.f510a;
        long j11 = this.c.f266m;
        G g10 = new G(j10, g7.f511b + j11);
        G g11 = eJ.f509b;
        return new E(g10, new G(g11.f510a, g11.f511b + j11));
    }
}
