package E8;

import o8.C2058c;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2058c f1851a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final A7.o f1852b;

    /* JADX WARN: Multi-variable type inference failed */
    public g(C2058c c2058c, InterfaceC2772a interfaceC2772a) {
        this.f1851a = c2058c;
        this.f1852b = (A7.o) interfaceC2772a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && g.class == obj.getClass() && this.f1851a.equals(((g) obj).f1851a);
    }

    public final int hashCode() {
        return this.f1851a.hashCode();
    }
}
