package androidx.compose.foundation.layout;

import D.C;
import E0.V;
import g0.n;
import kotlin.Metadata;
import x.AbstractC2641j;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/layout/FillElement;", "LE0/V;", "LD/C;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FillElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f11455a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f11456b;

    public FillElement(float f10, int i7) {
        this.f11455a = i7;
        this.f11456b = f10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FillElement)) {
            return false;
        }
        FillElement fillElement = (FillElement) obj;
        return this.f11455a == fillElement.f11455a && this.f11456b == fillElement.f11456b;
    }

    public final int hashCode() {
        return Float.hashCode(this.f11456b) + (AbstractC2641j.c(this.f11455a) * 31);
    }

    @Override // E0.V
    public final n i() {
        C c = new C();
        c.f1022y = this.f11455a;
        c.f1023z = this.f11456b;
        return c;
    }

    @Override // E0.V
    public final void l(n nVar) {
        C c = (C) nVar;
        c.f1022y = this.f11455a;
        c.f1023z = this.f11456b;
    }
}
