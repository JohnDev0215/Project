package H4;

import android.graphics.Paint;

/* JADX INFO: loaded from: classes.dex */
public final class f implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2804a;

    public f(int i7) {
        this.f2804a = i7;
    }

    @Override // H4.i
    public final Paint a() {
        Paint paint = new Paint();
        paint.setColor(this.f2804a);
        return paint;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof f) && this.f2804a == ((f) obj).f2804a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f2804a);
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.j(new StringBuilder("Solid(color="), this.f2804a, ')');
    }
}
