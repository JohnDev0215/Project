package l0;

import z7.InterfaceC2782k;

/* JADX INFO: renamed from: l0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1710a extends g0.n implements InterfaceC1712c {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public InterfaceC2782k f18323y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public EnumC1726q f18324z;

    @Override // l0.InterfaceC1712c
    public final void D(EnumC1726q enumC1726q) {
        if (A7.m.a(this.f18324z, enumC1726q)) {
            return;
        }
        this.f18324z = enumC1726q;
        this.f18323y.b(enumC1726q);
    }
}
