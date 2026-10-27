package A6;

import S8.AbstractC0727y;
import S8.E;
import V8.AbstractC0828q;
import V8.N;
import V8.f0;
import X8.m;
import java.util.HashMap;
import n7.u;
import n7.v;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final X8.c f436a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f0 f437b;
    public final N c;
    public final f0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final N f438e;

    public c() {
        Z8.e eVar = E.f8084a;
        this.f436a = AbstractC0727y.a(m.f10573a);
        f0 f0VarC = AbstractC0828q.c(u.l);
        this.f437b = f0VarC;
        this.c = new N(f0VarC);
        f0 f0VarC2 = AbstractC0828q.c(v.l);
        this.d = f0VarC2;
        this.f438e = new N(f0VarC2);
    }

    public final void a(String str, HashMap map) {
        A7.m.f(str, "message");
        AbstractC0727y.w(this.f436a, null, null, new b(this, str, map, null), 3);
    }
}
