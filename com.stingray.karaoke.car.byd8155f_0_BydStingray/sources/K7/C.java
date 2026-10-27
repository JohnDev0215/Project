package K7;

import Q7.AbstractC0662o;
import Q7.C0661n;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class C extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C f3723m = new C(2);

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        Integer numB = AbstractC0662o.b((C0661n) obj, (C0661n) obj2);
        return Integer.valueOf(numB == null ? 0 : numB.intValue());
    }
}
