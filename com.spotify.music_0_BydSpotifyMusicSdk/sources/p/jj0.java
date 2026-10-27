package p;

import android.content.Context;
import com.google.common.base.Optional;

/* JADX INFO: loaded from: classes.dex */
public final class jj0 implements ul0 {
    public final xy1 a;
    public final xy1 b;
    public final xy1 c;
    public final xy1 d;
    public final xy1 e;
    public final xy1 f;
    public final xy1 g;

    public jj0(b51 b51Var, wy1 wy1Var, wy1 wy1Var2, wy1 wy1Var3, p50 p50Var, b51 b51Var2, b51 b51Var3) {
        this.a = b51Var;
        this.b = wy1Var;
        this.c = wy1Var2;
        this.d = wy1Var3;
        this.e = p50Var;
        this.f = b51Var2;
        this.g = b51Var3;
    }

    @Override // p.xy1
    public final Object get() {
        return new com.spotify.eventsender.eventsender.b((Context) this.a.get(), (s81) this.b.get(), (s72) this.c.get(), (ri0) this.d.get(), (Optional) this.e.get(), (Optional) this.f.get(), (Optional) this.g.get());
    }
}
