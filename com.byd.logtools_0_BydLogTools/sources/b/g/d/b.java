package b.g.d;

import android.graphics.Insets;

/* JADX INFO: compiled from: Insets.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f2379a = new b(0, 0, 0, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2380b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2381c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2382d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2383e;

    private b(int i, int i2, int i3, int i4) {
        this.f2380b = i;
        this.f2381c = i2;
        this.f2382d = i3;
        this.f2383e = i4;
    }

    public static b a(int i, int i2, int i3, int i4) {
        return (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? f2379a : new b(i, i2, i3, i4);
    }

    public static b b(Insets insets) {
        return a(insets.left, insets.top, insets.right, insets.bottom);
    }

    public Insets c() {
        return Insets.of(this.f2380b, this.f2381c, this.f2382d, this.f2383e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f2383e == bVar.f2383e && this.f2380b == bVar.f2380b && this.f2382d == bVar.f2382d && this.f2381c == bVar.f2381c;
    }

    public int hashCode() {
        return (((((this.f2380b * 31) + this.f2381c) * 31) + this.f2382d) * 31) + this.f2383e;
    }

    public String toString() {
        return "Insets{left=" + this.f2380b + ", top=" + this.f2381c + ", right=" + this.f2382d + ", bottom=" + this.f2383e + '}';
    }
}
