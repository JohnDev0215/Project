package E;

import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class h implements G.r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final A7.o f1354a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final A7.o f1355b;
    public final b0.b c;

    /* JADX WARN: Multi-variable type inference failed */
    public h(InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, b0.b bVar) {
        this.f1354a = (A7.o) interfaceC2782k;
        this.f1355b = (A7.o) interfaceC2782k2;
        this.c = bVar;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [A7.o, z7.k] */
    @Override // G.r
    public final InterfaceC2782k b() {
        return this.f1355b;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [A7.o, z7.k] */
    @Override // G.r
    public final InterfaceC2782k getKey() {
        return this.f1354a;
    }
}
