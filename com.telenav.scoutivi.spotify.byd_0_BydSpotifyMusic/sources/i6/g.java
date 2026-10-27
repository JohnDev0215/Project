package i6;

import c6.b0;
import c6.v;
import c6.z;
import java.util.List;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: RealInterceptorChain.kt */
/* JADX INFO: loaded from: classes.dex */
public final class g implements v.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f7052a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final h6.e f7053b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<v> f7054c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f7055d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final h6.c f7056e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final z f7057f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f7058g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int f7059h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final int f7060i;

    /* JADX WARN: Multi-variable type inference failed */
    public g(h6.e call, List<? extends v> interceptors, int i7, h6.c cVar, z request, int i8, int i9, int i10) {
        l.e(call, "call");
        l.e(interceptors, "interceptors");
        l.e(request, "request");
        this.f7053b = call;
        this.f7054c = interceptors;
        this.f7055d = i7;
        this.f7056e = cVar;
        this.f7057f = request;
        this.f7058g = i8;
        this.f7059h = i9;
        this.f7060i = i10;
    }

    public static /* synthetic */ g d(g gVar, int i7, h6.c cVar, z zVar, int i8, int i9, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i7 = gVar.f7055d;
        }
        if ((i11 & 2) != 0) {
            cVar = gVar.f7056e;
        }
        h6.c cVar2 = cVar;
        if ((i11 & 4) != 0) {
            zVar = gVar.f7057f;
        }
        z zVar2 = zVar;
        if ((i11 & 8) != 0) {
            i8 = gVar.f7058g;
        }
        int i12 = i8;
        if ((i11 & 16) != 0) {
            i9 = gVar.f7059h;
        }
        int i13 = i9;
        if ((i11 & 32) != 0) {
            i10 = gVar.f7060i;
        }
        return gVar.c(i7, cVar2, zVar2, i12, i13, i10);
    }

    @Override // c6.v.a
    public z a() {
        return this.f7057f;
    }

    @Override // c6.v.a
    public b0 b(z request) {
        l.e(request, "request");
        if (!(this.f7055d < this.f7054c.size())) {
            throw new IllegalStateException("Check failed.".toString());
        }
        this.f7052a++;
        h6.c cVar = this.f7056e;
        if (cVar != null) {
            if (!cVar.j().g(request.i())) {
                throw new IllegalStateException(("network interceptor " + this.f7054c.get(this.f7055d - 1) + " must retain the same host and port").toString());
            }
            if (!(this.f7052a == 1)) {
                throw new IllegalStateException(("network interceptor " + this.f7054c.get(this.f7055d - 1) + " must call proceed() exactly once").toString());
            }
        }
        g gVarD = d(this, this.f7055d + 1, null, request, 0, 0, 0, 58, null);
        v vVar = this.f7054c.get(this.f7055d);
        b0 b0VarA = vVar.a(gVarD);
        if (b0VarA == null) {
            throw new NullPointerException("interceptor " + vVar + " returned null");
        }
        if (this.f7056e != null) {
            if (!(this.f7055d + 1 >= this.f7054c.size() || gVarD.f7052a == 1)) {
                throw new IllegalStateException(("network interceptor " + vVar + " must call proceed() exactly once").toString());
            }
        }
        if (b0VarA.m() != null) {
            return b0VarA;
        }
        throw new IllegalStateException(("interceptor " + vVar + " returned a response with no body").toString());
    }

    public final g c(int i7, h6.c cVar, z request, int i8, int i9, int i10) {
        l.e(request, "request");
        return new g(this.f7053b, this.f7054c, i7, cVar, request, i8, i9, i10);
    }

    @Override // c6.v.a
    public c6.e call() {
        return this.f7053b;
    }

    public final h6.e e() {
        return this.f7053b;
    }

    public final int f() {
        return this.f7058g;
    }

    public final h6.c g() {
        return this.f7056e;
    }

    public final int h() {
        return this.f7059h;
    }

    public final z i() {
        return this.f7057f;
    }

    public final int j() {
        return this.f7060i;
    }

    public int k() {
        return this.f7059h;
    }
}
