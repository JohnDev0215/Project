package androidx.compose.foundation.text.handwriting;

import A7.m;
import E0.V;
import L.d;
import g0.n;
import kotlin.Metadata;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/text/handwriting/StylusHandwritingElementWithNegativePadding;", "LE0/V;", "LL/d;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class StylusHandwritingElementWithNegativePadding extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2772a f11504a;

    public StylusHandwritingElementWithNegativePadding(InterfaceC2772a interfaceC2772a) {
        this.f11504a = interfaceC2772a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof StylusHandwritingElementWithNegativePadding) && m.a(this.f11504a, ((StylusHandwritingElementWithNegativePadding) obj).f11504a);
    }

    public final int hashCode() {
        return this.f11504a.hashCode();
    }

    @Override // E0.V
    public final n i() {
        return new d(this.f11504a);
    }

    @Override // E0.V
    public final void l(n nVar) {
        ((d) nVar).f3872A = this.f11504a;
    }

    public final String toString() {
        return "StylusHandwritingElementWithNegativePadding(onHandwritingSlopExceeded=" + this.f11504a + ')';
    }
}
