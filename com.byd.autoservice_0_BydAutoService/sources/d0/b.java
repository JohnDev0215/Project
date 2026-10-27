package d0;

import android.graphics.Insets;

/* JADX INFO: compiled from: Insets.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f2647a = new b(0, 0, 0, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2648b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2649c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2650d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2651e;

    public b(int i3, int i4, int i5, int i6) {
        this.f2648b = i3;
        this.f2649c = i4;
        this.f2650d = i5;
        this.f2651e = i6;
    }

    public static b a(int i3, int i4, int i5, int i6) {
        return (i3 == 0 && i4 == 0 && i5 == 0 && i6 == 0) ? f2647a : new b(i3, i4, i5, i6);
    }

    public static b b(Insets insets) {
        return a(insets.left, insets.top, insets.right, insets.bottom);
    }

    public Insets c() {
        return Insets.of(this.f2648b, this.f2649c, this.f2650d, this.f2651e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f2651e == bVar.f2651e && this.f2648b == bVar.f2648b && this.f2650d == bVar.f2650d && this.f2649c == bVar.f2649c;
    }

    public int hashCode() {
        return (((((this.f2648b * 31) + this.f2649c) * 31) + this.f2650d) * 31) + this.f2651e;
    }

    public String toString() {
        return "Insets{left=" + this.f2648b + ", top=" + this.f2649c + ", right=" + this.f2650d + ", bottom=" + this.f2651e + '}';
    }
}
