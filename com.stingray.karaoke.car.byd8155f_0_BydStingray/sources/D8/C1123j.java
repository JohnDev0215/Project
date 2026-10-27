package d8;

import F8.AbstractC0287w;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import T7.K;
import java.lang.reflect.Modifier;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: d8.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1123j extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C1123j f13594n = new C1123j(1, 0);
    public static final C1123j o = new C1123j(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C1123j f13595p = new C1123j(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C1123j f13596q = new C1123j(1, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C1123j f13597r = new C1123j(1, 4);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13598m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1123j(int i7, int i10) {
        super(i7);
        this.f13598m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f13598m) {
            case 0:
                A7.m.f((W7.v) obj, "it");
                return Boolean.valueOf(!Modifier.isStatic(r1.b().getModifiers()));
            case 1:
                K k10 = (K) obj;
                A7.m.f(k10, "$this$selectMostSpecificInEachOverridableGroup");
                return k10;
            case 2:
                W7.v vVar = (W7.v) obj;
                A7.m.f(vVar, "it");
                return Boolean.valueOf(Modifier.isStatic(vVar.b().getModifiers()));
            case 3:
                y8.n nVar = (y8.n) obj;
                A7.m.f(nVar, "it");
                return nVar.f();
            default:
                InterfaceC0654g interfaceC0654gN = ((AbstractC0287w) obj).B0().n();
                if (interfaceC0654gN instanceof InterfaceC0652e) {
                    return (InterfaceC0652e) interfaceC0654gN;
                }
                return null;
        }
    }
}
