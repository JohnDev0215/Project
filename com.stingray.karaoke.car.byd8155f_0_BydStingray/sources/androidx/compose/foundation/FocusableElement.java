package androidx.compose.foundation;

import A7.m;
import C.k;
import E0.V;
import g0.n;
import kotlin.Metadata;
import y.C2692M;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/FocusableElement;", "LE0/V;", "Ly/M;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class FocusableElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f11412a;

    public FocusableElement(k kVar) {
        this.f11412a = kVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FocusableElement) {
            return m.a(this.f11412a, ((FocusableElement) obj).f11412a);
        }
        return false;
    }

    public final int hashCode() {
        k kVar = this.f11412a;
        if (kVar != null) {
            return kVar.hashCode();
        }
        return 0;
    }

    @Override // E0.V
    public final n i() {
        return new C2692M(this.f11412a);
    }

    @Override // E0.V
    public final void l(n nVar) {
        ((C2692M) nVar).K0(this.f11412a);
    }
}
