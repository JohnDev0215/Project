package A;

/* JADX INFO: loaded from: classes.dex */
public final class N0 implements InterfaceC0033p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ S0 f76a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ P0 f77b;

    public N0(S0 s02, P0 p02) {
        this.f76a = s02;
        this.f77b = p02;
    }

    @Override // A.InterfaceC0033p0
    public final float a(float f10) {
        S0 s02 = this.f76a;
        long jD = s02.d(s02.g(f10));
        S0 s03 = this.f77b.f88a;
        s03.f104g = 2;
        y.r0 r0Var = s03.f101b;
        return s02.c(s02.f((r0Var == null || !(s03.f100a.c() || s03.f100a.a())) ? S0.a(s03, s03.f105h, jD, 2) : r0Var.a(jD, s03.f104g, s03.f107j)));
    }
}
