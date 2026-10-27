package d8;

import Q7.InterfaceC0652e;
import java.util.Collection;
import java.util.Set;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: d8.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1110B extends O8.l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C1122i f13556b;
    public final /* synthetic */ Set c;
    public final /* synthetic */ A7.o d;

    /* JADX WARN: Multi-variable type inference failed */
    public C1110B(C1122i c1122i, Set set, InterfaceC2782k interfaceC2782k) {
        this.f13556b = c1122i;
        this.c = set;
        this.d = (A7.o) interfaceC2782k;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [A7.o, z7.k] */
    @Override // O8.l
    public final boolean c(Object obj) {
        InterfaceC0652e interfaceC0652e = (InterfaceC0652e) obj;
        A7.m.f(interfaceC0652e, "current");
        if (interfaceC0652e == this.f13556b) {
            return true;
        }
        y8.n nVarQ0 = interfaceC0652e.q0();
        A7.m.e(nVarQ0, "current.staticScope");
        if (!(nVarQ0 instanceof AbstractC1112D)) {
            return true;
        }
        this.c.addAll((Collection) this.d.b(nVarQ0));
        return false;
    }

    @Override // O8.l
    public final /* bridge */ /* synthetic */ Object i() {
        return m7.q.f19267a;
    }
}
