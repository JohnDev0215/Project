package androidx.compose.foundation.lazy.layout;

import A7.m;
import E0.V;
import G.C0302l;
import g0.n;
import kotlin.Metadata;
import x.InterfaceC2615D;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimateItemElement;", "LE0/V;", "LG/l;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class LazyLayoutAnimateItemElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2615D f11482a;

    public LazyLayoutAnimateItemElement(InterfaceC2615D interfaceC2615D) {
        this.f11482a = interfaceC2615D;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LazyLayoutAnimateItemElement)) {
            return false;
        }
        LazyLayoutAnimateItemElement lazyLayoutAnimateItemElement = (LazyLayoutAnimateItemElement) obj;
        lazyLayoutAnimateItemElement.getClass();
        return m.a(null, null) && this.f11482a.equals(lazyLayoutAnimateItemElement.f11482a) && m.a(null, null);
    }

    public final int hashCode() {
        return this.f11482a.hashCode() * 31;
    }

    @Override // E0.V
    public final n i() {
        C0302l c0302l = new C0302l();
        c0302l.f2599y = this.f11482a;
        return c0302l;
    }

    @Override // E0.V
    public final void l(n nVar) {
        C0302l c0302l = (C0302l) nVar;
        c0302l.getClass();
        c0302l.f2599y = this.f11482a;
    }

    public final String toString() {
        return "LazyLayoutAnimateItemElement(fadeInSpec=null, placementSpec=" + this.f11482a + ", fadeOutSpec=null)";
    }
}
