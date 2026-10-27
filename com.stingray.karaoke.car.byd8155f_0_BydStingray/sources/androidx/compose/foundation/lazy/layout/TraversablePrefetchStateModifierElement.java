package androidx.compose.foundation.lazy.layout;

import A7.m;
import E0.V;
import G.l0;
import g0.n;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0083\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/lazy/layout/TraversablePrefetchStateModifierElement;", "LE0/V;", "LG/l0;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class TraversablePrefetchStateModifierElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final G.V f11487a;

    public TraversablePrefetchStateModifierElement(G.V v10) {
        this.f11487a = v10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof TraversablePrefetchStateModifierElement) && m.a(this.f11487a, ((TraversablePrefetchStateModifierElement) obj).f11487a);
    }

    public final int hashCode() {
        return this.f11487a.hashCode();
    }

    @Override // E0.V
    public final n i() {
        l0 l0Var = new l0();
        l0Var.f2600y = this.f11487a;
        return l0Var;
    }

    @Override // E0.V
    public final void l(n nVar) {
        ((l0) nVar).f2600y = this.f11487a;
    }

    public final String toString() {
        return "TraversablePrefetchStateModifierElement(prefetchState=" + this.f11487a + ')';
    }
}
