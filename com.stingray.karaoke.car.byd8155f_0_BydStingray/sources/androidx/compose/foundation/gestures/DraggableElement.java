package androidx.compose.foundation.gestures;

import A.C0005b0;
import A.C0014g;
import A.EnumC0017h0;
import A.InterfaceC0007c0;
import A7.m;
import C.k;
import E0.V;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/DraggableElement;", "LE0/V;", "LA/b0;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DraggableElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0007c0 f11438a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f11439b;
    public final k c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final InterfaceC2786o f11440e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final InterfaceC2786o f11441f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f11442g;

    public DraggableElement(InterfaceC0007c0 interfaceC0007c0, boolean z6, k kVar, boolean z10, InterfaceC2786o interfaceC2786o, InterfaceC2786o interfaceC2786o2, boolean z11) {
        this.f11438a = interfaceC0007c0;
        this.f11439b = z6;
        this.c = kVar;
        this.d = z10;
        this.f11440e = interfaceC2786o;
        this.f11441f = interfaceC2786o2;
        this.f11442g = z11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DraggableElement.class != obj.getClass()) {
            return false;
        }
        DraggableElement draggableElement = (DraggableElement) obj;
        return m.a(this.f11438a, draggableElement.f11438a) && this.f11439b == draggableElement.f11439b && m.a(this.c, draggableElement.c) && this.d == draggableElement.d && m.a(this.f11440e, draggableElement.f11440e) && m.a(this.f11441f, draggableElement.f11441f) && this.f11442g == draggableElement.f11442g;
    }

    public final int hashCode() {
        int iD = AbstractC1347d.d((EnumC0017h0.f181m.hashCode() + (this.f11438a.hashCode() * 31)) * 31, 31, this.f11439b);
        k kVar = this.c;
        return Boolean.hashCode(this.f11442g) + ((this.f11441f.hashCode() + ((this.f11440e.hashCode() + AbstractC1347d.d((iD + (kVar != null ? kVar.hashCode() : 0)) * 31, 31, this.d)) * 31)) * 31);
    }

    @Override // E0.V
    public final n i() {
        C0014g c0014g = C0014g.o;
        EnumC0017h0 enumC0017h0 = EnumC0017h0.f181m;
        C0005b0 c0005b0 = new C0005b0(c0014g, this.f11439b, this.c, enumC0017h0);
        c0005b0.f148I = this.f11438a;
        c0005b0.f149J = enumC0017h0;
        c0005b0.f150K = this.d;
        c0005b0.f151L = this.f11440e;
        c0005b0.f152M = this.f11441f;
        c0005b0.f153N = this.f11442g;
        return c0005b0;
    }

    @Override // E0.V
    public final void l(n nVar) {
        boolean z6;
        boolean z10;
        C0005b0 c0005b0 = (C0005b0) nVar;
        C0014g c0014g = C0014g.o;
        InterfaceC0007c0 interfaceC0007c0 = c0005b0.f148I;
        InterfaceC0007c0 interfaceC0007c02 = this.f11438a;
        if (m.a(interfaceC0007c0, interfaceC0007c02)) {
            z6 = false;
        } else {
            c0005b0.f148I = interfaceC0007c02;
            z6 = true;
        }
        EnumC0017h0 enumC0017h0 = c0005b0.f149J;
        EnumC0017h0 enumC0017h02 = EnumC0017h0.f181m;
        if (enumC0017h0 != enumC0017h02) {
            c0005b0.f149J = enumC0017h02;
            z6 = true;
        }
        boolean z11 = c0005b0.f153N;
        boolean z12 = this.f11442g;
        if (z11 != z12) {
            c0005b0.f153N = z12;
            z10 = true;
        } else {
            z10 = z6;
        }
        c0005b0.f151L = this.f11440e;
        c0005b0.f152M = this.f11441f;
        c0005b0.f150K = this.d;
        c0005b0.S0(c0014g, this.f11439b, this.c, enumC0017h02, z10);
    }
}
