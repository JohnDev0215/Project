package b.g.d;

import android.graphics.Insets;

/* JADX INFO: compiled from: Insets.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f2181a = new b(0, 0, 0, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2182b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2183c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2184d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2185e;

    private b(int i, int i2, int i3, int i4) {
        this.f2182b = i;
        this.f2183c = i2;
        this.f2184d = i3;
        this.f2185e = i4;
    }

    public static b a(int i, int i2, int i3, int i4) {
        return (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? f2181a : new b(i, i2, i3, i4);
    }

    public static b b(Insets insets) {
        return a(insets.left, insets.top, insets.right, insets.bottom);
    }

    public Insets c() {
        return Insets.of(this.f2182b, this.f2183c, this.f2184d, this.f2185e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f2185e == bVar.f2185e && this.f2182b == bVar.f2182b && this.f2184d == bVar.f2184d && this.f2183c == bVar.f2183c;
    }

    public int hashCode() {
        return (((((this.f2182b * 31) + this.f2183c) * 31) + this.f2184d) * 31) + this.f2185e;
    }

    public String toString() {
        return "Insets{left=" + this.f2182b + ", top=" + this.f2183c + ", right=" + this.f2184d + ", bottom=" + this.f2185e + '}';
    }
}
