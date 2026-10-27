package androidx.compose.foundation.lazy.layout;

import A.EnumC0017h0;
import A7.m;
import E0.AbstractC0156f;
import E0.V;
import G.X;
import G.b0;
import H7.r;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticsModifier;", "LE0/V;", "LG/b0;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class LazyLayoutSemanticsModifier extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r f11484a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final X f11485b;
    public final EnumC0017h0 c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f11486e;

    public LazyLayoutSemanticsModifier(r rVar, X x8, EnumC0017h0 enumC0017h0, boolean z6, boolean z10) {
        this.f11484a = rVar;
        this.f11485b = x8;
        this.c = enumC0017h0;
        this.d = z6;
        this.f11486e = z10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LazyLayoutSemanticsModifier)) {
            return false;
        }
        LazyLayoutSemanticsModifier lazyLayoutSemanticsModifier = (LazyLayoutSemanticsModifier) obj;
        return this.f11484a == lazyLayoutSemanticsModifier.f11484a && m.a(this.f11485b, lazyLayoutSemanticsModifier.f11485b) && this.c == lazyLayoutSemanticsModifier.c && this.d == lazyLayoutSemanticsModifier.d && this.f11486e == lazyLayoutSemanticsModifier.f11486e;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f11486e) + AbstractC1347d.d((this.c.hashCode() + ((this.f11485b.hashCode() + (this.f11484a.hashCode() * 31)) * 31)) * 31, 31, this.d);
    }

    @Override // E0.V
    public final n i() {
        return new b0(this.f11484a, this.f11485b, this.c, this.d, this.f11486e);
    }

    @Override // E0.V
    public final void l(n nVar) {
        b0 b0Var = (b0) nVar;
        b0Var.f2547y = this.f11484a;
        b0Var.f2548z = this.f11485b;
        EnumC0017h0 enumC0017h0 = b0Var.f2542A;
        EnumC0017h0 enumC0017h02 = this.c;
        if (enumC0017h0 != enumC0017h02) {
            b0Var.f2542A = enumC0017h02;
            AbstractC0156f.o(b0Var);
        }
        boolean z6 = b0Var.B;
        boolean z10 = this.d;
        boolean z11 = this.f11486e;
        if (z6 == z10 && b0Var.f2543C == z11) {
            return;
        }
        b0Var.B = z10;
        b0Var.f2543C = z11;
        b0Var.H0();
        AbstractC0156f.o(b0Var);
    }
}
