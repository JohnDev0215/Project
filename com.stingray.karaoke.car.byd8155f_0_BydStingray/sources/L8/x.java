package L8;

import b8.C1011f;
import v8.AbstractC2564d;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class x implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final A7.o f4111a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f4112b;

    /* JADX WARN: Multi-variable type inference failed */
    public x(String str, InterfaceC2782k interfaceC2782k) {
        this.f4111a = (A7.o) interfaceC2782k;
        this.f4112b = "must return ".concat(str);
    }

    @Override // L8.e
    public final String a() {
        return this.f4112b;
    }

    @Override // L8.e
    public final String b(C1011f c1011f) {
        return C7.a.Q(this, c1011f);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [A7.o, z7.k] */
    @Override // L8.e
    public final boolean c(C1011f c1011f) {
        return A7.m.a(c1011f.f8900s, this.f4111a.b(AbstractC2564d.e(c1011f)));
    }
}
