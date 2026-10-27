package f.k0.i;

import f.e0;
import f.g0;
import f.m;
import f.y;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: RealInterceptorChain.java */
/* JADX INFO: loaded from: classes.dex */
public final class g implements y.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<y> f5233a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f.k0.h.k f5234b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    private final f.k0.h.d f5235c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f5236d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final e0 f5237e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final f.i f5238f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f5239g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int f5240h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final int f5241i;
    private int j;

    public g(List<y> list, f.k0.h.k kVar, @Nullable f.k0.h.d dVar, int i2, e0 e0Var, f.i iVar, int i3, int i4, int i5) {
        this.f5233a = list;
        this.f5234b = kVar;
        this.f5235c = dVar;
        this.f5236d = i2;
        this.f5237e = e0Var;
        this.f5238f = iVar;
        this.f5239g = i3;
        this.f5240h = i4;
        this.f5241i = i5;
    }

    @Override // f.y.a
    public e0 a() {
        return this.f5237e;
    }

    @Override // f.y.a
    @Nullable
    public m b() {
        f.k0.h.d dVar = this.f5235c;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // f.y.a
    public int c() {
        return this.f5241i;
    }

    @Override // f.y.a
    public g0 d(e0 e0Var) {
        return h(e0Var, this.f5234b, this.f5235c);
    }

    @Override // f.y.a
    public int e() {
        return this.f5239g;
    }

    @Override // f.y.a
    public int f() {
        return this.f5240h;
    }

    public f.k0.h.d g() {
        f.k0.h.d dVar = this.f5235c;
        if (dVar != null) {
            return dVar;
        }
        throw new IllegalStateException();
    }

    public g0 h(e0 e0Var, f.k0.h.k kVar, @Nullable f.k0.h.d dVar) {
        if (this.f5236d >= this.f5233a.size()) {
            throw new AssertionError();
        }
        this.j++;
        f.k0.h.d dVar2 = this.f5235c;
        if (dVar2 != null && !dVar2.c().v(e0Var.i())) {
            throw new IllegalStateException("network interceptor " + this.f5233a.get(this.f5236d - 1) + " must retain the same host and port");
        }
        if (this.f5235c != null && this.j > 1) {
            throw new IllegalStateException("network interceptor " + this.f5233a.get(this.f5236d - 1) + " must call proceed() exactly once");
        }
        g gVar = new g(this.f5233a, kVar, dVar, this.f5236d + 1, e0Var, this.f5238f, this.f5239g, this.f5240h, this.f5241i);
        y yVar = this.f5233a.get(this.f5236d);
        g0 g0VarA = yVar.a(gVar);
        if (dVar != null && this.f5236d + 1 < this.f5233a.size() && gVar.j != 1) {
            throw new IllegalStateException("network interceptor " + yVar + " must call proceed() exactly once");
        }
        if (g0VarA == null) {
            throw new NullPointerException("interceptor " + yVar + " returned null");
        }
        if (g0VarA.a() != null) {
            return g0VarA;
        }
        throw new IllegalStateException("interceptor " + yVar + " returned a response with no body");
    }

    public f.k0.h.k i() {
        return this.f5234b;
    }
}
