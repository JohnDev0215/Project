package E3;

import android.graphics.drawable.Drawable;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class d extends e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Drawable f1726a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f1727b;
    public final B3.f c;

    public d(Drawable drawable, boolean z6, B3.f fVar) {
        this.f1726a = drawable;
        this.f1727b = z6;
        this.c = fVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof d) {
            d dVar = (d) obj;
            if (A7.m.a(this.f1726a, dVar.f1726a) && this.f1727b == dVar.f1727b && this.c == dVar.c) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.c.hashCode() + AbstractC1347d.d(this.f1726a.hashCode() * 31, 31, this.f1727b);
    }
}
