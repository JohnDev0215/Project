package b.g.c;

import android.graphics.Insets;

/* JADX INFO: compiled from: Insets.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f978a = new b(0, 0, 0, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f979b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f980c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f981d;
    public final int e;

    public b(int i, int i2, int i3, int i4) {
        this.f979b = i;
        this.f980c = i2;
        this.f981d = i3;
        this.e = i4;
    }

    public static b a(int i, int i2, int i3, int i4) {
        return (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? f978a : new b(i, i2, i3, i4);
    }

    public static b b(Insets insets) {
        return a(insets.left, insets.top, insets.right, insets.bottom);
    }

    public Insets c() {
        return Insets.of(this.f979b, this.f980c, this.f981d, this.e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.e == bVar.e && this.f979b == bVar.f979b && this.f981d == bVar.f981d && this.f980c == bVar.f980c;
    }

    public int hashCode() {
        return (((((this.f979b * 31) + this.f980c) * 31) + this.f981d) * 31) + this.e;
    }

    public String toString() {
        return "Insets{left=" + this.f979b + ", top=" + this.f980c + ", right=" + this.f981d + ", bottom=" + this.e + '}';
    }
}
