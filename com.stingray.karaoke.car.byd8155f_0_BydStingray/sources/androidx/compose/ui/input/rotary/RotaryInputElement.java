package androidx.compose.ui.input.rotary;

import A7.m;
import E0.V;
import F0.C0219o;
import g0.n;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/input/rotary/RotaryInputElement;", "LE0/V;", "LA0/a;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class RotaryInputElement extends V {
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RotaryInputElement)) {
            return false;
        }
        ((RotaryInputElement) obj).getClass();
        Object obj2 = C0219o.f2236p;
        return obj2.equals(obj2) && m.a(null, null);
    }

    public final int hashCode() {
        return C0219o.f2236p.hashCode() * 31;
    }

    @Override // E0.V
    public final n i() {
        A0.a aVar = new A0.a();
        aVar.f305y = C0219o.f2236p;
        return aVar;
    }

    @Override // E0.V
    public final void l(n nVar) {
        ((A0.a) nVar).f305y = C0219o.f2236p;
    }

    public final String toString() {
        return "RotaryInputElement(onRotaryScrollEvent=" + C0219o.f2236p + ", onPreRotaryScrollEvent=null)";
    }
}
