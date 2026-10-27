package Q7;

import T7.AbstractC0783b;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class M {
    public static final O d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ H7.w[] f7177e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC0783b f7178a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f7179b;
    public final E8.i c;

    static {
        A7.B b10 = A7.A.f443a;
        f7177e = new H7.w[]{b10.h(new A7.s(b10.b(M.class), "scopeForOwnerModule", "getScopeForOwnerModule()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;"))};
        d = new O(5);
    }

    public M(AbstractC0783b abstractC0783b, E8.o oVar, InterfaceC2782k interfaceC2782k) {
        this.f7178a = abstractC0783b;
        this.f7179b = interfaceC2782k;
        K7.K k10 = new K7.K(this, 14);
        E8.l lVar = (E8.l) oVar;
        lVar.getClass();
        this.c = new E8.i(lVar, k10);
    }
}
