package j8;

import p8.InterfaceC2140o;

/* JADX INFO: loaded from: classes.dex */
public enum I implements InterfaceC2140o {
    CLASS(0),
    PACKAGE(1),
    LOCAL(2);

    public final int l;

    I(int i7) {
        this.l = i7;
    }

    @Override // p8.InterfaceC2140o
    public final int a() {
        return this.l;
    }
}
