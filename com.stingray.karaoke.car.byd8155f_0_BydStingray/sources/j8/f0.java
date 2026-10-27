package j8;

import p8.InterfaceC2140o;

/* JADX INFO: loaded from: classes.dex */
public enum f0 implements InterfaceC2140o {
    /* JADX INFO: Fake field, exist only in values array */
    INTERNAL(0),
    /* JADX INFO: Fake field, exist only in values array */
    PRIVATE(1),
    /* JADX INFO: Fake field, exist only in values array */
    PROTECTED(2),
    /* JADX INFO: Fake field, exist only in values array */
    PUBLIC(3),
    /* JADX INFO: Fake field, exist only in values array */
    PRIVATE_TO_THIS(4),
    /* JADX INFO: Fake field, exist only in values array */
    LOCAL(5);

    public final int l;

    f0(int i7) {
        this.l = i7;
    }

    @Override // p8.InterfaceC2140o
    public final int a() {
        return this.l;
    }
}
