package e.d0.g;

import e.a0;
import e.t;
import e.y;
import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: RealInterceptorChain.java */
/* JADX INFO: loaded from: classes.dex */
public final class g implements t.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<t> f3400a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e.d0.f.f f3401b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f3402c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e.d0.f.c f3403d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f3404e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final y f3405f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3406g;

    public g(List<t> list, e.d0.f.f fVar, c cVar, e.d0.f.c cVar2, int i2, y yVar) {
        this.f3400a = list;
        this.f3403d = cVar2;
        this.f3401b = fVar;
        this.f3402c = cVar;
        this.f3404e = i2;
        this.f3405f = yVar;
    }

    @Override // e.t.a
    public a0 a(y yVar) throws IOException {
        return e(yVar, this.f3401b, this.f3402c, this.f3403d);
    }

    @Override // e.t.a
    public y b() {
        return this.f3405f;
    }

    @Override // e.t.a
    public e.i c() {
        return this.f3403d;
    }

    public c d() {
        return this.f3402c;
    }

    public a0 e(y yVar, e.d0.f.f fVar, c cVar, e.d0.f.c cVar2) throws IOException {
        if (this.f3404e >= this.f3400a.size()) {
            throw new AssertionError();
        }
        this.f3406g++;
        if (this.f3402c != null && !this.f3403d.s(yVar.h())) {
            throw new IllegalStateException("network interceptor " + this.f3400a.get(this.f3404e - 1) + " must retain the same host and port");
        }
        if (this.f3402c != null && this.f3406g > 1) {
            throw new IllegalStateException("network interceptor " + this.f3400a.get(this.f3404e - 1) + " must call proceed() exactly once");
        }
        g gVar = new g(this.f3400a, fVar, cVar, cVar2, this.f3404e + 1, yVar);
        t tVar = this.f3400a.get(this.f3404e);
        a0 a0VarA = tVar.a(gVar);
        if (cVar != null && this.f3404e + 1 < this.f3400a.size() && gVar.f3406g != 1) {
            throw new IllegalStateException("network interceptor " + tVar + " must call proceed() exactly once");
        }
        if (a0VarA != null) {
            return a0VarA;
        }
        throw new NullPointerException("interceptor " + tVar + " returned null");
    }

    public e.d0.f.f f() {
        return this.f3401b;
    }
}
