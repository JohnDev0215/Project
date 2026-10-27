package b.g.d;

import android.graphics.Insets;

/* JADX INFO: compiled from: Insets.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f2529a = new b(0, 0, 0, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2530b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2531c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2532d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2533e;

    private b(int i2, int i3, int i4, int i5) {
        this.f2530b = i2;
        this.f2531c = i3;
        this.f2532d = i4;
        this.f2533e = i5;
    }

    public static b a(int i2, int i3, int i4, int i5) {
        return (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) ? f2529a : new b(i2, i3, i4, i5);
    }

    public static b b(Insets insets) {
        return a(insets.left, insets.top, insets.right, insets.bottom);
    }

    public Insets c() {
        return Insets.of(this.f2530b, this.f2531c, this.f2532d, this.f2533e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f2533e == bVar.f2533e && this.f2530b == bVar.f2530b && this.f2532d == bVar.f2532d && this.f2531c == bVar.f2531c;
    }

    public int hashCode() {
        return (((((this.f2530b * 31) + this.f2531c) * 31) + this.f2532d) * 31) + this.f2533e;
    }

    public String toString() {
        return "Insets{left=" + this.f2530b + ", top=" + this.f2531c + ", right=" + this.f2532d + ", bottom=" + this.f2533e + '}';
    }
}
