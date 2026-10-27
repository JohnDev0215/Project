package f.b0.g;

import f.r;
import f.w;
import f.y;
import java.io.IOException;
import java.util.List;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: RealInterceptorChain.java */
/* JADX INFO: loaded from: classes.dex */
public final class g implements r.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<r> f5079a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f.b0.f.f f5080b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c f5081c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f.i f5082d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5083e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final w f5084f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f5085g;

    public g(List<r> list, f.b0.f.f fVar, c cVar, f.i iVar, int i2, w wVar) {
        this.f5079a = list;
        this.f5082d = iVar;
        this.f5080b = fVar;
        this.f5081c = cVar;
        this.f5083e = i2;
        this.f5084f = wVar;
    }

    @Override // f.r.a
    public y a(w wVar) throws IOException {
        return d(wVar, this.f5080b, this.f5081c, this.f5082d);
    }

    @Override // f.r.a
    public w b() {
        return this.f5084f;
    }

    public c c() {
        return this.f5081c;
    }

    public y d(w wVar, f.b0.f.f fVar, c cVar, f.i iVar) throws IOException {
        if (this.f5083e >= this.f5079a.size()) {
            throw new AssertionError();
        }
        this.f5085g++;
        if (this.f5081c != null && !e(wVar.h())) {
            throw new IllegalStateException("network interceptor " + this.f5079a.get(this.f5083e - 1) + " must retain the same host and port");
        }
        if (this.f5081c != null && this.f5085g > 1) {
            throw new IllegalStateException("network interceptor " + this.f5079a.get(this.f5083e - 1) + " must call proceed() exactly once");
        }
        g gVar = new g(this.f5079a, fVar, cVar, iVar, this.f5083e + 1, wVar);
        r rVar = this.f5079a.get(this.f5083e);
        y yVarA = rVar.a(gVar);
        if (cVar != null && this.f5083e + 1 < this.f5079a.size() && gVar.f5085g != 1) {
            throw new IllegalStateException("network interceptor " + rVar + " must call proceed() exactly once");
        }
        if (yVarA != null) {
            return yVarA;
        }
        throw new NullPointerException("interceptor " + rVar + " returned null");
    }

    public final boolean e(HttpUrl httpUrl) {
        return httpUrl.l().equals(this.f5082d.a().a().k().l()) && httpUrl.x() == this.f5082d.a().a().k().x();
    }

    public f.b0.f.f f() {
        return this.f5080b;
    }
}
