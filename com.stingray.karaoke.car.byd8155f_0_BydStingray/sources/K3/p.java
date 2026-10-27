package K3;

import android.graphics.drawable.Drawable;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class p extends j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Drawable f3578a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i f3579b;
    public final B3.f c;
    public final I3.a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f3580e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f3581f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f3582g;

    public p(Drawable drawable, i iVar, B3.f fVar, I3.a aVar, String str, boolean z6, boolean z10) {
        this.f3578a = drawable;
        this.f3579b = iVar;
        this.c = fVar;
        this.d = aVar;
        this.f3580e = str;
        this.f3581f = z6;
        this.f3582g = z10;
    }

    @Override // K3.j
    public final Drawable a() {
        return this.f3578a;
    }

    @Override // K3.j
    public final i b() {
        return this.f3579b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof p) {
            p pVar = (p) obj;
            if (A7.m.a(this.f3578a, pVar.f3578a)) {
                if (A7.m.a(this.f3579b, pVar.f3579b) && this.c == pVar.c && A7.m.a(this.d, pVar.d) && A7.m.a(this.f3580e, pVar.f3580e) && this.f3581f == pVar.f3581f && this.f3582g == pVar.f3582g) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.c.hashCode() + ((this.f3579b.hashCode() + (this.f3578a.hashCode() * 31)) * 31)) * 31;
        I3.a aVar = this.d;
        int iHashCode2 = (iHashCode + (aVar != null ? aVar.hashCode() : 0)) * 31;
        String str = this.f3580e;
        return Boolean.hashCode(this.f3582g) + AbstractC1347d.d((iHashCode2 + (str != null ? str.hashCode() : 0)) * 31, 31, this.f3581f);
    }
}
