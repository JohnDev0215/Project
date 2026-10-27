package g1;

import C0.S;
import F0.T;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: g1.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1282i extends T implements S {
    public final C1278e c;
    public final InterfaceC2782k d;

    public C1282i(C1278e c1278e, InterfaceC2782k interfaceC2782k) {
        this.c = c1278e;
        this.d = interfaceC2782k;
    }

    public final boolean equals(Object obj) {
        C1282i c1282i = obj instanceof C1282i ? (C1282i) obj : null;
        return this.d == (c1282i != null ? c1282i.d : null);
    }

    public final int hashCode() {
        return this.d.hashCode();
    }

    @Override // C0.S
    public final Object j() {
        return new C1281h(this.c, this.d);
    }
}
