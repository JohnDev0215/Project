package coil.request;

import p.l91;
import p.o32;
import p.p61;
import p.s91;

/* JADX INFO: loaded from: classes.dex */
public final class BaseRequestDelegate implements o32 {
    public final l91 c;
    public final p61 e;

    public BaseRequestDelegate(l91 l91Var, p61 p61Var) {
        this.c = l91Var;
        this.e = p61Var;
    }

    @Override // p.o32
    public final void a() {
        this.c.c(this);
    }

    @Override // p.o32
    public final void b() {
        this.c.a(this);
    }

    @Override // p.n70
    public final void onDestroy(s91 s91Var) {
        this.e.i(null);
    }
}
