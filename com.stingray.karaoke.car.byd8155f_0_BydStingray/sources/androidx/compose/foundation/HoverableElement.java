package androidx.compose.foundation;

import A7.m;
import C.k;
import E0.V;
import g0.n;
import kotlin.Metadata;
import y.W;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/HoverableElement;", "LE0/V;", "Ly/W;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class HoverableElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f11413a;

    public HoverableElement(k kVar) {
        this.f11413a = kVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof HoverableElement) && m.a(((HoverableElement) obj).f11413a, this.f11413a);
    }

    public final int hashCode() {
        return this.f11413a.hashCode() * 31;
    }

    @Override // E0.V
    public final n i() {
        W w10 = new W();
        w10.f23871y = this.f11413a;
        return w10;
    }

    @Override // E0.V
    public final void l(n nVar) {
        W w10 = (W) nVar;
        k kVar = w10.f23871y;
        k kVar2 = this.f11413a;
        if (m.a(kVar, kVar2)) {
            return;
        }
        w10.J0();
        w10.f23871y = kVar2;
    }
}
