package androidx.compose.material3;

import A7.m;
import C.k;
import E0.InterfaceC0163m;
import Q.N;
import a1.e;
import h2.AbstractC1347d;
import n0.C1891t;
import y.e0;

/* JADX INFO: loaded from: classes.dex */
public final class c implements e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f11539a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f11540b;
    public final long c;

    public c(boolean z6, float f10, long j3) {
        this.f11539a = z6;
        this.f11540b = f10;
        this.c = j3;
    }

    @Override // y.e0
    public final InterfaceC0163m b(k kVar) {
        N n10 = new N(this);
        return new DelegatingThemeAwareRippleNode(kVar, this.f11539a, this.f11540b, n10);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (this.f11539a == cVar.f11539a && e.a(this.f11540b, cVar.f11540b) && m.a(null, null)) {
            return C1891t.c(this.c, cVar.c);
        }
        return false;
    }

    public final int hashCode() {
        int iB = AbstractC1347d.b(Boolean.hashCode(this.f11539a) * 31, 961, this.f11540b);
        int i7 = C1891t.l;
        return Long.hashCode(this.c) + iB;
    }
}
