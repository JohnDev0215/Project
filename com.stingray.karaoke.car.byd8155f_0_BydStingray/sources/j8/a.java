package J8;

import A7.m;
import A7.o;
import D8.v;
import F8.a0;
import Q7.InterfaceC0654g;
import Q7.Q;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class a extends o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final a f3111n = new a(1, 0);
    public static final a o = new a(1, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3112m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(int i7, int i10) {
        super(i7);
        this.f3112m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f3112m) {
            case 0:
                a0 a0Var = (a0) obj;
                m.f(a0Var, "it");
                InterfaceC0654g interfaceC0654gN = a0Var.B0().n();
                boolean z6 = false;
                if (interfaceC0654gN != null && (interfaceC0654gN instanceof Q) && (((Q) interfaceC0654gN).o() instanceof v)) {
                    z6 = true;
                }
                return Boolean.valueOf(z6);
            default:
                a0 a0Var2 = (a0) obj;
                m.f(a0Var2, "it");
                InterfaceC0654g interfaceC0654gN2 = a0Var2.B0().n();
                boolean z10 = false;
                if (interfaceC0654gN2 != null && ((interfaceC0654gN2 instanceof v) || (interfaceC0654gN2 instanceof Q))) {
                    z10 = true;
                }
                return Boolean.valueOf(z10);
        }
    }
}
