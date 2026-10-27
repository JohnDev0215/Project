package N6;

import B2.O;
import androidx.lifecycle.f0;
import androidx.lifecycle.h0;
import b2.C0993c;
import g1.AbstractC1274a;
import h.n;
import io.sentry.hints.i;

/* JADX INFO: loaded from: classes.dex */
public final class f implements h0 {
    public static final i d = new i(5);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final R6.b f4783a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h0 f4784b;
    public final c c;

    public f(R6.b bVar, h0 h0Var, O o) {
        this.f4783a = bVar;
        this.f4784b = h0Var;
        this.c = new c(o, 0);
    }

    public static f d(n nVar, h0 h0Var) {
        I5.g gVar = (I5.g) ((d) AbstractC1274a.I(d.class, nVar));
        return new f(gVar.a(), h0Var, new O(gVar.f2952a, 22, gVar.f2953b));
    }

    @Override // androidx.lifecycle.h0
    public final f0 a(Class cls) {
        if (this.f4783a.containsKey(cls)) {
            throw new UnsupportedOperationException("`Factory.create(String, CreationExtras)` is not implemented. You may need to override the method and provide a custom implementation. Note that using `Factory.create(String)` is not supported and considered an error.");
        }
        return this.f4784b.a(cls);
    }

    @Override // androidx.lifecycle.h0
    public final f0 c(Class cls, C0993c c0993c) {
        return this.f4783a.containsKey(cls) ? this.c.c(cls, c0993c) : this.f4784b.c(cls, c0993c);
    }
}
