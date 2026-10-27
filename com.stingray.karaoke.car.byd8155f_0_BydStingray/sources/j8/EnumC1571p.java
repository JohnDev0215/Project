package j8;

import p8.InterfaceC2140o;

/* JADX INFO: renamed from: j8.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC1571p implements InterfaceC2140o {
    RETURNS_CONSTANT(0),
    CALLS(1),
    RETURNS_NOT_NULL(2);

    public final int l;

    EnumC1571p(int i7) {
        this.l = i7;
    }

    @Override // p8.InterfaceC2140o
    public final int a() {
        return this.l;
    }
}
