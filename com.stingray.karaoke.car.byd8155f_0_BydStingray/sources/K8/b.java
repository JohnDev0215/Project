package K8;

import A7.m;
import A7.o;
import F8.a0;
import s8.InterfaceC2414b;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class b extends o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final b f3860m = new b(1);

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        a0 a0Var = (a0) obj;
        m.e(a0Var, "it");
        return Boolean.valueOf(a0Var.B0() instanceof InterfaceC2414b);
    }
}
