package j8;

import p8.InterfaceC2140o;

/* JADX INFO: renamed from: j8.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC1572q implements InterfaceC2140o {
    AT_MOST_ONCE(0),
    EXACTLY_ONCE(1),
    AT_LEAST_ONCE(2);

    public final int l;

    EnumC1572q(int i7) {
        this.l = i7;
    }

    @Override // p8.InterfaceC2140o
    public final int a() {
        return this.l;
    }
}
