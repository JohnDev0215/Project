package androidx.compose.foundation.relocation;

import A7.m;
import E0.V;
import H.c;
import H.d;
import g0.n;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/relocation/BringIntoViewRequesterElement;", "LE0/V;", "LH/d;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class BringIntoViewRequesterElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f11500a;

    public BringIntoViewRequesterElement(c cVar) {
        this.f11500a = cVar;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof BringIntoViewRequesterElement) {
                if (m.a(this.f11500a, ((BringIntoViewRequesterElement) obj).f11500a)) {
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.f11500a.hashCode();
    }

    @Override // E0.V
    public final n i() {
        d dVar = new d();
        dVar.f2750y = this.f11500a;
        return dVar;
    }

    @Override // E0.V
    public final void l(n nVar) {
        d dVar = (d) nVar;
        c cVar = dVar.f2750y;
        if (cVar != null) {
            cVar.f2749a.n(dVar);
        }
        c cVar2 = this.f11500a;
        if (cVar2 != null) {
            cVar2.f2749a.b(dVar);
        }
        dVar.f2750y = cVar2;
    }
}
