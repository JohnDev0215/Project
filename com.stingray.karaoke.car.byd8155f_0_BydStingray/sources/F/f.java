package F;

import Q5.C0558d0;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class f implements G.r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2785n f1878a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0558d0 f1879b;
    public final b0.b c;

    public f(InterfaceC2785n interfaceC2785n, C0558d0 c0558d0, b0.b bVar) {
        this.f1878a = interfaceC2785n;
        this.f1879b = c0558d0;
        this.c = bVar;
    }

    @Override // G.r
    public final InterfaceC2782k b() {
        return this.f1879b;
    }

    @Override // G.r
    public final InterfaceC2782k getKey() {
        return null;
    }
}
