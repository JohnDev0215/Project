package H4;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Drawable f2794a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final F4.a f2795b;
    public final i c;

    public a(f fVar, int i7) {
        F4.a aVar = F4.a.f2409a;
        i iVar = (i7 & 4) != 0 ? g.f2805a : fVar;
        A7.m.f(iVar, "color");
        this.f2794a = null;
        this.f2795b = aVar;
        this.c = iVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return A7.m.a(this.f2794a, aVar.f2794a) && A7.m.a(this.f2795b, aVar.f2795b) && A7.m.a(this.c, aVar.c);
    }

    public final int hashCode() {
        Drawable drawable = this.f2794a;
        int iHashCode = drawable == null ? 0 : drawable.hashCode();
        return this.c.hashCode() + ((this.f2795b.hashCode() + (iHashCode * 31)) * 31);
    }

    public final String toString() {
        return "QrVectorBackground(drawable=" + this.f2794a + ", scale=" + this.f2795b + ", color=" + this.c + ')';
    }
}
