package b.g.d;

import android.graphics.Insets;

/* JADX INFO: compiled from: Insets.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f1825a = new b(0, 0, 0, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1826b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1827c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1828d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1829e;

    private b(int i, int i2, int i3, int i4) {
        this.f1826b = i;
        this.f1827c = i2;
        this.f1828d = i3;
        this.f1829e = i4;
    }

    public static b a(int i, int i2, int i3, int i4) {
        return (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? f1825a : new b(i, i2, i3, i4);
    }

    public static b b(Insets insets) {
        return a(insets.left, insets.top, insets.right, insets.bottom);
    }

    public Insets c() {
        return Insets.of(this.f1826b, this.f1827c, this.f1828d, this.f1829e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f1829e == bVar.f1829e && this.f1826b == bVar.f1826b && this.f1828d == bVar.f1828d && this.f1827c == bVar.f1827c;
    }

    public int hashCode() {
        return (((((this.f1826b * 31) + this.f1827c) * 31) + this.f1828d) * 31) + this.f1829e;
    }

    public String toString() {
        return "Insets{left=" + this.f1826b + ", top=" + this.f1827c + ", right=" + this.f1828d + ", bottom=" + this.f1829e + '}';
    }
}
