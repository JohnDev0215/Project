package g0;

import android.graphics.Insets;

/* JADX INFO: compiled from: Insets.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f4981a = new b(0, 0, 0, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4982b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f4983c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f4984d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f4985e;

    public b(int i8, int i9, int i10, int i11) {
        this.f4982b = i8;
        this.f4983c = i9;
        this.f4984d = i10;
        this.f4985e = i11;
    }

    public static b a(int i8, int i9, int i10, int i11) {
        return (i8 == 0 && i9 == 0 && i10 == 0 && i11 == 0) ? f4981a : new b(i8, i9, i10, i11);
    }

    public static b b(Insets insets) {
        return a(insets.left, insets.top, insets.right, insets.bottom);
    }

    public Insets c() {
        return Insets.of(this.f4982b, this.f4983c, this.f4984d, this.f4985e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f4985e == bVar.f4985e && this.f4982b == bVar.f4982b && this.f4984d == bVar.f4984d && this.f4983c == bVar.f4983c;
    }

    public int hashCode() {
        return (((((this.f4982b * 31) + this.f4983c) * 31) + this.f4984d) * 31) + this.f4985e;
    }

    public String toString() {
        return "Insets{left=" + this.f4982b + ", top=" + this.f4983c + ", right=" + this.f4984d + ", bottom=" + this.f4985e + '}';
    }
}
