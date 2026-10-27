package j8;

import p8.InterfaceC2140o;

/* JADX INFO: renamed from: j8.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC1576v implements InterfaceC2140o {
    TRUE(0),
    FALSE(1),
    NULL(2);

    public final int l;

    EnumC1576v(int i7) {
        this.l = i7;
    }

    @Override // p8.InterfaceC2140o
    public final int a() {
        return this.l;
    }
}
