package b.h.f;

import android.graphics.Insets;

/* JADX INFO: compiled from: Insets.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f2431a = new b(0, 0, 0, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2432b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2433c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2434d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2435e;

    public b(int i2, int i3, int i4, int i5) {
        this.f2432b = i2;
        this.f2433c = i3;
        this.f2434d = i4;
        this.f2435e = i5;
    }

    public static b a(int i2, int i3, int i4, int i5) {
        return (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) ? f2431a : new b(i2, i3, i4, i5);
    }

    public static b b(Insets insets) {
        return a(insets.left, insets.top, insets.right, insets.bottom);
    }

    public Insets c() {
        return Insets.of(this.f2432b, this.f2433c, this.f2434d, this.f2435e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f2435e == bVar.f2435e && this.f2432b == bVar.f2432b && this.f2434d == bVar.f2434d && this.f2433c == bVar.f2433c;
    }

    public int hashCode() {
        return (((((this.f2432b * 31) + this.f2433c) * 31) + this.f2434d) * 31) + this.f2435e;
    }

    public String toString() {
        return "Insets{left=" + this.f2432b + ", top=" + this.f2433c + ", right=" + this.f2434d + ", bottom=" + this.f2435e + '}';
    }
}
