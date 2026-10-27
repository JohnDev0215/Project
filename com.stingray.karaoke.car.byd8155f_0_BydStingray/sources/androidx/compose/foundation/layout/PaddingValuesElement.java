package androidx.compose.foundation.layout;

import A7.m;
import D.L;
import D.M;
import E0.V;
import g0.n;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/layout/PaddingValuesElement;", "LE0/V;", "LD/M;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class PaddingValuesElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L f11465a;

    public PaddingValuesElement(L l) {
        this.f11465a = l;
    }

    public final boolean equals(Object obj) {
        PaddingValuesElement paddingValuesElement = obj instanceof PaddingValuesElement ? (PaddingValuesElement) obj : null;
        if (paddingValuesElement == null) {
            return false;
        }
        return m.a(this.f11465a, paddingValuesElement.f11465a);
    }

    public final int hashCode() {
        return this.f11465a.hashCode();
    }

    @Override // E0.V
    public final n i() {
        M m10 = new M();
        m10.f1040y = this.f11465a;
        return m10;
    }

    @Override // E0.V
    public final void l(n nVar) {
        ((M) nVar).f1040y = this.f11465a;
    }
}
