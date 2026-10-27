package w;

import T.C0735d;
import T.C0738e0;
import T.S;
import u.AbstractC2456D;
import u.C2484x;
import x.s0;
import x.x0;

/* JADX INFO: renamed from: w.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2579j implements s0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final x0 f23105a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g0.c f23106b;
    public final C0738e0 c = C0735d.O(new a1.j(0), S.f8224q);
    public final C2484x d;

    public C2579j(x0 x0Var, g0.c cVar) {
        this.f23105a = x0Var;
        this.f23106b = cVar;
        long[] jArr = AbstractC2456D.f22584a;
        this.d = new C2484x();
    }

    @Override // x.s0
    public final Object a() {
        return this.f23105a.f().a();
    }

    @Override // x.s0
    public final Object c() {
        return this.f23105a.f().c();
    }
}
