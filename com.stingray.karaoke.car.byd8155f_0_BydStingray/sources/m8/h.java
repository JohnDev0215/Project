package m8;

import p8.InterfaceC2140o;

/* JADX INFO: loaded from: classes.dex */
public enum h implements InterfaceC2140o {
    NONE(0),
    INTERNAL_TO_CLASS_ID(1),
    DESC_TO_CLASS_ID(2);

    public final int l;

    h(int i7) {
        this.l = i7;
    }

    @Override // p8.InterfaceC2140o
    public final int a() {
        return this.l;
    }
}
