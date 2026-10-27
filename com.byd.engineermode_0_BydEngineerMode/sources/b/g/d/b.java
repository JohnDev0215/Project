package b.g.d;

import android.graphics.Insets;

/* JADX INFO: compiled from: Insets.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f1686a = new b(0, 0, 0, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1687b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1688c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1689d;
    public final int e;

    private b(int i, int i2, int i3, int i4) {
        this.f1687b = i;
        this.f1688c = i2;
        this.f1689d = i3;
        this.e = i4;
    }

    public static b a(int i, int i2, int i3, int i4) {
        return (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? f1686a : new b(i, i2, i3, i4);
    }

    public static b b(Insets insets) {
        return a(insets.left, insets.top, insets.right, insets.bottom);
    }

    public Insets c() {
        return Insets.of(this.f1687b, this.f1688c, this.f1689d, this.e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.e == bVar.e && this.f1687b == bVar.f1687b && this.f1689d == bVar.f1689d && this.f1688c == bVar.f1688c;
    }

    public int hashCode() {
        return (((((this.f1687b * 31) + this.f1688c) * 31) + this.f1689d) * 31) + this.e;
    }

    public String toString() {
        return "Insets{left=" + this.f1687b + ", top=" + this.f1688c + ", right=" + this.f1689d + ", bottom=" + this.e + '}';
    }
}
