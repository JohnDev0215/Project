package k0;

import A7.o;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: k0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1591c implements a1.b {
    public InterfaceC1589a l = C1595g.l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public e7.f f17615m;

    /* JADX WARN: Multi-variable type inference failed */
    public final e7.f a(InterfaceC2782k interfaceC2782k) {
        e7.f fVar = new e7.f((char) 0, 21);
        fVar.f14480m = (o) interfaceC2782k;
        this.f17615m = fVar;
        return fVar;
    }

    @Override // a1.b
    public final float getDensity() {
        return this.l.getDensity().getDensity();
    }

    @Override // a1.b
    public final float p() {
        return this.l.getDensity().p();
    }
}
