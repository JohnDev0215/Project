package T7;

import Q7.InterfaceC0657j;
import Q7.InterfaceC0659l;
import Q7.InterfaceC0671y;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public abstract class B extends AbstractC0795n implements Q7.D {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C2058c f8767q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final String f8768r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public B(InterfaceC0671y interfaceC0671y, C2058c c2058c) {
        super(interfaceC0671y, R7.g.f7535a, c2058c.g(), Q7.N.c);
        A7.m.f(interfaceC0671y, "module");
        A7.m.f(c2058c, "fqName");
        this.f8767q = c2058c;
        this.f8768r = "package " + c2058c + " of " + interfaceC0671y;
    }

    @Override // Q7.InterfaceC0657j
    public final Object S(InterfaceC0659l interfaceC0659l, Object obj) {
        return interfaceC0659l.l(this, obj);
    }

    @Override // T7.AbstractC0795n, Q7.InterfaceC0657j
    /* JADX INFO: renamed from: c1, reason: merged with bridge method [inline-methods] */
    public final InterfaceC0671y o() {
        InterfaceC0657j interfaceC0657jO = super.o();
        A7.m.d(interfaceC0657jO, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ModuleDescriptor");
        return (InterfaceC0671y) interfaceC0657jO;
    }

    @Override // T7.AbstractC0795n, Q7.InterfaceC0658k
    public Q7.N g() {
        return Q7.N.c;
    }

    @Override // T7.AbstractC0794m, A9.b
    public String toString() {
        return this.f8768r;
    }
}
