package j8;

import p8.InterfaceC2140o;

/* JADX INFO: loaded from: classes.dex */
public enum V implements InterfaceC2140o {
    IN(0),
    OUT(1),
    INV(2);

    public final int l;

    V(int i7) {
        this.l = i7;
    }

    @Override // p8.InterfaceC2140o
    public final int a() {
        return this.l;
    }
}
