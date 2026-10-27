package g8;

import F8.AbstractC0287w;
import F8.a0;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0654g;
import T7.u;
import e8.C1240e;
import o8.C2058c;
import o8.C2061f;
import v8.AbstractC2564d;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: g8.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1323m extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C1323m f15017n = new C1323m(1, 0);
    public static final C1323m o = new C1323m(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C1323m f15018p = new C1323m(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C1323m f15019q = new C1323m(1, 3);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f15020m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1323m(int i7, int i10) {
        super(i7);
        this.f15020m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f15020m) {
            case 0:
                InterfaceC0654g interfaceC0654gN = ((a0) obj).B0().n();
                if (interfaceC0654gN == null) {
                    return Boolean.FALSE;
                }
                C2061f name = interfaceC0654gN.getName();
                C2058c c2058c = P7.d.f5420f;
                return Boolean.valueOf(A7.m.a(name, c2058c.f()) && A7.m.a(AbstractC2564d.c(interfaceC0654gN), c2058c));
            case 1:
                InterfaceC0650c interfaceC0650c = (InterfaceC0650c) obj;
                A7.m.f(interfaceC0650c, "it");
                u uVarK = interfaceC0650c.K();
                A7.m.c(uVarK);
                return uVarK.b();
            case 2:
                InterfaceC0650c interfaceC0650c2 = (InterfaceC0650c) obj;
                A7.m.f(interfaceC0650c2, "it");
                AbstractC0287w abstractC0287wP = interfaceC0650c2.p();
                A7.m.c(abstractC0287wP);
                return abstractC0287wP;
            case 3:
                a0 a0Var = (a0) obj;
                A7.m.f(a0Var, "it");
                return Boolean.valueOf(a0Var instanceof C1240e);
            default:
                C1324n c1324n = (C1324n) obj;
                A7.m.f(c1324n, "$this$function");
                String strConcat = "java/util/".concat("Spliterator");
                C1315e c1315e = AbstractC1321k.f15014b;
                c1324n.b(strConcat, c1315e, c1315e);
                return m7.q.f19267a;
        }
    }
}
