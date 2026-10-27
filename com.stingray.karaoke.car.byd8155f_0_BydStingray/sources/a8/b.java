package a8;

import A7.A;
import A7.B;
import A7.m;
import A7.s;
import E8.l;
import F8.AbstractC0287w;
import H7.w;
import N0.q;
import Q7.N;
import W7.C0840d;
import b8.InterfaceC1013h;
import c8.C1036a;
import f8.InterfaceC1265a;
import java.util.Map;
import n7.AbstractC1998m;
import n7.v;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public class b implements R7.b, InterfaceC1013h {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ w[] f11202e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2058c f11203a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final N f11204b;
    public final E8.i c;
    public final InterfaceC1265a d;

    static {
        B b10 = A.f443a;
        f11202e = new w[]{b10.h(new s(b10.b(b.class), "type", "getType()Lorg/jetbrains/kotlin/types/SimpleType;"))};
    }

    public b(q qVar, C0840d c0840d, C2058c c2058c) {
        m.f(qVar, "c");
        m.f(c2058c, "fqName");
        this.f11203a = c2058c;
        C1036a c1036a = (C1036a) qVar.f4399m;
        this.f11204b = c0840d != null ? c1036a.f12418j.b(c0840d) : N.c;
        l lVar = c1036a.f12411a;
        B8.A a10 = new B8.A(qVar, 26, this);
        lVar.getClass();
        this.c = new E8.i(lVar, a10);
        this.d = c0840d != null ? (InterfaceC1265a) AbstractC1998m.Z(c0840d.b()) : null;
    }

    @Override // R7.b
    public final C2058c a() {
        return this.f11203a;
    }

    @Override // R7.b
    public final AbstractC0287w b() {
        return (F8.A) C7.a.N(this.c, f11202e[0]);
    }

    @Override // R7.b
    public Map c() {
        return v.l;
    }

    @Override // R7.b
    public final N g() {
        return this.f11204b;
    }
}
