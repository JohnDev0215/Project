package w;

import x.C0;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final A7.o f23080a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0 f23081b;

    /* JADX WARN: Multi-variable type inference failed */
    public N(C0 c02, InterfaceC2782k interfaceC2782k) {
        this.f23080a = (A7.o) interfaceC2782k;
        this.f23081b = c02;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof N)) {
            return false;
        }
        N n10 = (N) obj;
        return this.f23080a.equals(n10.f23080a) && this.f23081b.equals(n10.f23081b);
    }

    public final int hashCode() {
        return this.f23081b.hashCode() + (this.f23080a.hashCode() * 31);
    }

    public final String toString() {
        return "Slide(slideOffset=" + this.f23080a + ", animationSpec=" + this.f23081b + ')';
    }
}
