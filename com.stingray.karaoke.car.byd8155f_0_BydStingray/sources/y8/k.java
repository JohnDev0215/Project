package y8;

import Q7.InterfaceC0649b;
import Q7.L;
import T7.K;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class k extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final k f24208n = new k(1, 0);
    public static final k o = new k(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final k f24209p = new k(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final k f24210q = new k(1, 3);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f24211m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k(int i7, int i10) {
        super(i7);
        this.f24211m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f24211m) {
            case 0:
                A7.m.f((C2061f) obj, "it");
                return Boolean.TRUE;
            case 1:
                InterfaceC0649b interfaceC0649b = (InterfaceC0649b) obj;
                A7.m.f(interfaceC0649b, "$this$selectMostSpecificInEachOverridableGroup");
                return interfaceC0649b;
            case 2:
                K k10 = (K) obj;
                A7.m.f(k10, "$this$selectMostSpecificInEachOverridableGroup");
                return k10;
            default:
                L l = (L) obj;
                A7.m.f(l, "$this$selectMostSpecificInEachOverridableGroup");
                return l;
        }
    }
}
