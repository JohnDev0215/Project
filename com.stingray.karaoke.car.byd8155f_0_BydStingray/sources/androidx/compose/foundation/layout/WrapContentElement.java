package androidx.compose.foundation.layout;

import A7.m;
import A7.o;
import D.a0;
import E0.V;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;
import x.AbstractC2641j;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/layout/WrapContentElement;", "LE0/V;", "LD/a0;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class WrapContentElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f11472a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f11473b;
    public final o c;
    public final Object d;

    /* JADX WARN: Multi-variable type inference failed */
    public WrapContentElement(int i7, boolean z6, InterfaceC2785n interfaceC2785n, Object obj) {
        this.f11472a = i7;
        this.f11473b = z6;
        this.c = (o) interfaceC2785n;
        this.d = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || WrapContentElement.class != obj.getClass()) {
            return false;
        }
        WrapContentElement wrapContentElement = (WrapContentElement) obj;
        return this.f11472a == wrapContentElement.f11472a && this.f11473b == wrapContentElement.f11473b && m.a(this.d, wrapContentElement.d);
    }

    public final int hashCode() {
        return this.d.hashCode() + AbstractC1347d.d(AbstractC2641j.c(this.f11472a) * 31, 31, this.f11473b);
    }

    @Override // E0.V
    public final n i() {
        a0 a0Var = new a0();
        a0Var.f1066y = this.f11472a;
        a0Var.f1067z = this.f11473b;
        a0Var.f1065A = this.c;
        return a0Var;
    }

    @Override // E0.V
    public final void l(n nVar) {
        a0 a0Var = (a0) nVar;
        a0Var.f1066y = this.f11472a;
        a0Var.f1067z = this.f11473b;
        a0Var.f1065A = this.c;
    }
}
