package b.g.d;

import android.graphics.Insets;

/* JADX INFO: compiled from: Insets.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f1606a = new b(0, 0, 0, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1607b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1608c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1609d;
    public final int e;

    private b(int i, int i2, int i3, int i4) {
        this.f1607b = i;
        this.f1608c = i2;
        this.f1609d = i3;
        this.e = i4;
    }

    public static b a(int i, int i2, int i3, int i4) {
        return (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? f1606a : new b(i, i2, i3, i4);
    }

    public static b b(Insets insets) {
        return a(insets.left, insets.top, insets.right, insets.bottom);
    }

    public Insets c() {
        return Insets.of(this.f1607b, this.f1608c, this.f1609d, this.e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.e == bVar.e && this.f1607b == bVar.f1607b && this.f1609d == bVar.f1609d && this.f1608c == bVar.f1608c;
    }

    public int hashCode() {
        return (((((this.f1607b * 31) + this.f1608c) * 31) + this.f1609d) * 31) + this.e;
    }

    public String toString() {
        return "Insets{left=" + this.f1607b + ", top=" + this.f1608c + ", right=" + this.f1609d + ", bottom=" + this.e + '}';
    }
}
