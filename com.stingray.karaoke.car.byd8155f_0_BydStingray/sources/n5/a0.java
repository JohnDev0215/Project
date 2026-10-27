package N5;

import A7.InterfaceC0059g;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a0 implements androidx.lifecycle.K, InterfaceC0059g {
    public final /* synthetic */ C0452h l;

    public a0(C0452h c0452h) {
        this.l = c0452h;
    }

    @Override // androidx.lifecycle.K
    public final /* synthetic */ void a(Object obj) {
        this.l.b(obj);
    }

    @Override // A7.InterfaceC0059g
    public final m7.c b() {
        return this.l;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof androidx.lifecycle.K) && (obj instanceof InterfaceC0059g)) {
            return this.l.equals(((InterfaceC0059g) obj).b());
        }
        return false;
    }

    public final int hashCode() {
        return this.l.hashCode();
    }
}
