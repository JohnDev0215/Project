package h8;

import z7.InterfaceC2785n;

/* JADX INFO: renamed from: h8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1385b extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C1385b f15602n = new C1385b(2, 0);
    public static final C1385b o = new C1385b(2, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f15603m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1385b(int i7, int i10) {
        super(i7);
        this.f15603m = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f15603m) {
            case 0:
                C1384a c1384a = (C1384a) obj;
                p pVar = (p) obj2;
                A7.m.f(c1384a, "$this$loadConstantFromProperty");
                A7.m.f(pVar, "it");
                return c1384a.c.get(pVar);
            default:
                C1384a c1384a2 = (C1384a) obj;
                p pVar2 = (p) obj2;
                A7.m.f(c1384a2, "$this$loadConstantFromProperty");
                A7.m.f(pVar2, "it");
                return c1384a2.f15601b.get(pVar2);
        }
    }
}
