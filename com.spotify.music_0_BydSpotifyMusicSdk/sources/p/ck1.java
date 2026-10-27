package p;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class ck1 implements vl0 {
    public final xy1 a;
    public final xy1 b;

    public ck1(c51 c51Var, o40 o40Var) {
        this.a = c51Var;
        this.b = o40Var;
    }

    @Override // p.xy1
    public final Object get() {
        return new bk1((Context) this.a.get(), (n40) this.b.get());
    }
}
