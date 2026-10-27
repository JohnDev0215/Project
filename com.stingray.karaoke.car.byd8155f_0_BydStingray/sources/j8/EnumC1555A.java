package j8;

import p8.InterfaceC2140o;

/* JADX INFO: renamed from: j8.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC1555A implements InterfaceC2140o {
    /* JADX INFO: Fake field, exist only in values array */
    FINAL(0),
    /* JADX INFO: Fake field, exist only in values array */
    OPEN(1),
    /* JADX INFO: Fake field, exist only in values array */
    ABSTRACT(2),
    /* JADX INFO: Fake field, exist only in values array */
    SEALED(3);

    public final int l;

    EnumC1555A(int i7) {
        this.l = i7;
    }

    @Override // p8.InterfaceC2140o
    public final int a() {
        return this.l;
    }
}
