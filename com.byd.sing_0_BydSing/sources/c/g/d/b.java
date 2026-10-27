package c.g.d;

import android.graphics.Insets;

/* JADX INFO: compiled from: Insets.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f2173a = new b(0, 0, 0, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2174b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2175c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2176d;
    public final int e;

    private b(int i, int i2, int i3, int i4) {
        this.f2174b = i;
        this.f2175c = i2;
        this.f2176d = i3;
        this.e = i4;
    }

    public static b a(int i, int i2, int i3, int i4) {
        return (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? f2173a : new b(i, i2, i3, i4);
    }

    public static b b(Insets insets) {
        return a(insets.left, insets.top, insets.right, insets.bottom);
    }

    public Insets c() {
        return Insets.of(this.f2174b, this.f2175c, this.f2176d, this.e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.e == bVar.e && this.f2174b == bVar.f2174b && this.f2176d == bVar.f2176d && this.f2175c == bVar.f2175c;
    }

    public int hashCode() {
        return (((((this.f2174b * 31) + this.f2175c) * 31) + this.f2176d) * 31) + this.e;
    }

    public String toString() {
        return "Insets{left=" + this.f2174b + ", top=" + this.f2175c + ", right=" + this.f2176d + ", bottom=" + this.e + '}';
    }
}
