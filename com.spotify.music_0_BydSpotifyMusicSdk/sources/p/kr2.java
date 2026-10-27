package p;

import android.content.Context;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class kr2 implements vl0 {
    public final xy1 a;
    public final xy1 b;
    public final xy1 c;
    public final xy1 d;
    public final xy1 e;
    public final xy1 f;
    public final xy1 g;
    public final xy1 h;
    public final xy1 i;

    public kr2(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3, j72 j72Var, xy1 xy1Var4, xy1 xy1Var5, xy1 xy1Var6) {
        vk0 vk0Var = l13.e;
        vk0 vk0Var2 = zu.h;
        this.a = xy1Var;
        this.b = xy1Var2;
        this.c = xy1Var3;
        this.d = j72Var;
        this.e = xy1Var4;
        this.f = xy1Var5;
        this.g = vk0Var;
        this.h = vk0Var2;
        this.i = xy1Var6;
    }

    @Override // p.xy1
    public final Object get() {
        return new jr2((Context) this.a.get(), (bk1) this.b.get(), (jk0) this.c.get(), (hy2) this.d.get(), (Executor) this.e.get(), (wh2) this.f.get(), (yt) this.g.get(), (yt) this.h.get(), (rt) this.i.get());
    }
}
