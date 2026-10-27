package C8;

import A.F;
import A.I0;
import A7.m;
import B8.i;
import B8.t;
import D8.s;
import E8.o;
import Q7.D;
import Q7.InterfaceC0671y;
import T7.B;
import j8.C;
import j8.E;
import j8.K;
import j8.L;
import k8.C1708a;
import l8.C1789g;
import o8.C2058c;
import v8.AbstractC2564d;
import y8.n;

/* JADX INFO: loaded from: classes.dex */
public final class c extends B implements D {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C1708a f1017s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C1789g f1018t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final t f1019u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public E f1020v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public s f1021w;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(C2058c c2058c, o oVar, InterfaceC0671y interfaceC0671y, E e10, C1708a c1708a) {
        super(interfaceC0671y, c2058c);
        m.f(c2058c, "fqName");
        m.f(oVar, "storageManager");
        m.f(interfaceC0671y, "module");
        this.f1017s = c1708a;
        L l = e10.o;
        m.e(l, "proto.strings");
        K k10 = e10.f17093p;
        m.e(k10, "proto.qualifiedNames");
        C1789g c1789g = new C1789g(l, k10);
        this.f1018t = c1789g;
        this.f1019u = new t(e10, c1789g, c1708a, new F(this, 5));
        this.f1020v = e10;
    }

    public final void d1(i iVar) {
        m.f(iVar, "components");
        E e10 = this.f1020v;
        if (e10 == null) {
            throw new IllegalStateException("Repeated call to DeserializedPackageFragmentImpl::initialize");
        }
        this.f1020v = null;
        C c = e10.f17094q;
        m.e(c, "proto.`package`");
        this.f1021w = new s(this, c, this.f1018t, this.f1017s, null, iVar, "scope of " + this, new I0(this, 2));
    }

    @Override // T7.B, T7.AbstractC0794m, A9.b
    public final String toString() {
        return "builtins package fragment for " + this.f8767q + " from " + AbstractC2564d.j(this);
    }

    @Override // Q7.D
    public final n z0() {
        s sVar = this.f1021w;
        if (sVar != null) {
            return sVar;
        }
        m.j("_memberScope");
        throw null;
    }
}
