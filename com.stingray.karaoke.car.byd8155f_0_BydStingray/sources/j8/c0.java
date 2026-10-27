package j8;

import p8.InterfaceC2140o;

/* JADX INFO: loaded from: classes.dex */
public enum c0 implements InterfaceC2140o {
    LANGUAGE_VERSION(0),
    COMPILER_VERSION(1),
    API_VERSION(2);

    public final int l;

    c0(int i7) {
        this.l = i7;
    }

    @Override // p8.InterfaceC2140o
    public final int a() {
        return this.l;
    }
}
