package a.g.d;

import android.graphics.Insets;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f271a = new b(0, 0, 0, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f272b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f273c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f274d;
    public final int e;

    private b(int i, int i2, int i3, int i4) {
        this.f272b = i;
        this.f273c = i2;
        this.f274d = i3;
        this.e = i4;
    }

    public static b a(int i, int i2, int i3, int i4) {
        return (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? f271a : new b(i, i2, i3, i4);
    }

    public static b b(Insets insets) {
        return a(insets.left, insets.top, insets.right, insets.bottom);
    }

    public Insets c() {
        return Insets.of(this.f272b, this.f273c, this.f274d, this.e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.e == bVar.e && this.f272b == bVar.f272b && this.f274d == bVar.f274d && this.f273c == bVar.f273c;
    }

    public int hashCode() {
        return (((((this.f272b * 31) + this.f273c) * 31) + this.f274d) * 31) + this.e;
    }

    public String toString() {
        return "Insets{left=" + this.f272b + ", top=" + this.f273c + ", right=" + this.f274d + ", bottom=" + this.e + '}';
    }
}
