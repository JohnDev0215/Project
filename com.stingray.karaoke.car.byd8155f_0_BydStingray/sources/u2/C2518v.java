package u2;

import h2.AbstractC1343A;
import java.util.Objects;

/* JADX INFO: renamed from: u2.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2518v extends r {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Object f22906h = new Object();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f22907f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f22908g;

    public C2518v(e2.f0 f0Var, Object obj, Object obj2) {
        super(f0Var);
        this.f22907f = obj;
        this.f22908g = obj2;
    }

    @Override // u2.r, e2.f0
    public final int b(Object obj) {
        Object obj2;
        if (f22906h.equals(obj) && (obj2 = this.f22908g) != null) {
            obj = obj2;
        }
        return this.f22896e.b(obj);
    }

    @Override // u2.r, e2.f0
    public final e2.c0 f(int i7, e2.c0 c0Var, boolean z6) {
        this.f22896e.f(i7, c0Var, z6);
        Object obj = c0Var.f14060b;
        int i10 = AbstractC1343A.f15195a;
        if (Objects.equals(obj, this.f22908g) && z6) {
            c0Var.f14060b = f22906h;
        }
        return c0Var;
    }

    @Override // u2.r, e2.f0
    public final Object l(int i7) {
        Object objL = this.f22896e.l(i7);
        int i10 = AbstractC1343A.f15195a;
        return Objects.equals(objL, this.f22908g) ? f22906h : objL;
    }

    @Override // u2.r, e2.f0
    public final e2.e0 m(int i7, e2.e0 e0Var, long j3) {
        this.f22896e.m(i7, e0Var, j3);
        if (Objects.equals(e0Var.f14085a, this.f22907f)) {
            e0Var.f14085a = e2.e0.f14075q;
        }
        return e0Var;
    }
}
