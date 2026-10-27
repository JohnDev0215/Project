package j8;

import p8.InterfaceC2140o;

/* JADX INFO: loaded from: classes.dex */
public enum b0 implements InterfaceC2140o {
    WARNING(0),
    ERROR(1),
    HIDDEN(2);

    public final int l;

    b0(int i7) {
        this.l = i7;
    }

    @Override // p8.InterfaceC2140o
    public final int a() {
        return this.l;
    }
}
