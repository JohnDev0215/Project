package F8;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class L extends M {
    public final /* synthetic */ Map c;
    public final /* synthetic */ boolean d;

    public L(Map map, boolean z6) {
        this.c = map;
        this.d = z6;
    }

    @Override // F8.T
    public final boolean a() {
        return this.d;
    }

    @Override // F8.T
    public final boolean e() {
        return this.c.isEmpty();
    }

    @Override // F8.M
    public final O g(K k10) {
        A7.m.f(k10, "key");
        return (O) this.c.get(k10);
    }
}
