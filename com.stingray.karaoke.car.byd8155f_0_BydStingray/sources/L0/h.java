package L0;

import h2.AbstractC1347d;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final A7.o f3884a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final A7.o f3885b;
    public final boolean c;

    /* JADX WARN: Multi-variable type inference failed */
    public h(InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, boolean z6) {
        this.f3884a = (A7.o) interfaceC2772a;
        this.f3885b = (A7.o) interfaceC2772a2;
        this.c = z6;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [A7.o, z7.a] */
    public final InterfaceC2772a a() {
        return this.f3885b;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [A7.o, z7.a] */
    /* JADX WARN: Type inference failed for: r1v6, types: [A7.o, z7.a] */
    public final String toString() {
        StringBuilder sb = new StringBuilder("ScrollAxisRange(value=");
        sb.append(((Number) this.f3884a.a()).floatValue());
        sb.append(", maxValue=");
        sb.append(((Number) this.f3885b.a()).floatValue());
        sb.append(", reverseScrolling=");
        return AbstractC1347d.i(sb, this.c, ')');
    }
}
