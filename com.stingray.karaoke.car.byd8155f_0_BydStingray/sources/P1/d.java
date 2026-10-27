package P1;

import A7.m;
import A7.o;
import N1.b0;
import n0.C1886o;
import r9.z;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class d extends o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final d f5272m = new d(2);

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        z zVar = (z) obj;
        m.f(zVar, "path");
        m.f((r9.o) obj2, "<anonymous parameter 1>");
        return new b0(C1886o.i(zVar.l.r(), true).l.r());
    }
}
