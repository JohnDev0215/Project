package b.f.g;

import android.graphics.Insets;

/* JADX INFO: compiled from: Insets.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f1635a = new b(0, 0, 0, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1636b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1637c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1638d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1639e;

    public b(int i2, int i3, int i4, int i5) {
        this.f1636b = i2;
        this.f1637c = i3;
        this.f1638d = i4;
        this.f1639e = i5;
    }

    public static b a(int i2, int i3, int i4, int i5) {
        return (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) ? f1635a : new b(i2, i3, i4, i5);
    }

    public Insets b() {
        return Insets.of(this.f1636b, this.f1637c, this.f1638d, this.f1639e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f1639e == bVar.f1639e && this.f1636b == bVar.f1636b && this.f1638d == bVar.f1638d && this.f1637c == bVar.f1637c;
    }

    public int hashCode() {
        return (((((this.f1636b * 31) + this.f1637c) * 31) + this.f1638d) * 31) + this.f1639e;
    }

    public String toString() {
        return "Insets{left=" + this.f1636b + ", top=" + this.f1637c + ", right=" + this.f1638d + ", bottom=" + this.f1639e + '}';
    }
}
