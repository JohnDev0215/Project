package androidx.compose.ui;

import E0.V;
import g0.n;
import g0.s;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/ZIndexElement;", "LE0/V;", "Lg0/s;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class ZIndexElement extends V {
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ZIndexElement)) {
            return false;
        }
        ((ZIndexElement) obj).getClass();
        return Float.compare(1.0f, 1.0f) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(1.0f);
    }

    @Override // E0.V
    public final n i() {
        s sVar = new s();
        sVar.f14701y = 1.0f;
        return sVar;
    }

    @Override // E0.V
    public final void l(n nVar) {
        ((s) nVar).f14701y = 1.0f;
    }

    public final String toString() {
        return "ZIndexElement(zIndex=1.0)";
    }
}
