package z;

import android.graphics.Insets;
import android.graphics.Rect;

/* JADX INFO: compiled from: Insets.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b f10021e = new b(0, 0, 0, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f10022a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f10023b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f10024c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f10025d;

    private b(int i7, int i8, int i9, int i10) {
        this.f10022a = i7;
        this.f10023b = i8;
        this.f10024c = i9;
        this.f10025d = i10;
    }

    public static b a(b bVar, b bVar2) {
        return b(Math.max(bVar.f10022a, bVar2.f10022a), Math.max(bVar.f10023b, bVar2.f10023b), Math.max(bVar.f10024c, bVar2.f10024c), Math.max(bVar.f10025d, bVar2.f10025d));
    }

    public static b b(int i7, int i8, int i9, int i10) {
        return (i7 == 0 && i8 == 0 && i9 == 0 && i10 == 0) ? f10021e : new b(i7, i8, i9, i10);
    }

    public static b c(Rect rect) {
        return b(rect.left, rect.top, rect.right, rect.bottom);
    }

    public static b d(Insets insets) {
        return b(insets.left, insets.top, insets.right, insets.bottom);
    }

    public Insets e() {
        return Insets.of(this.f10022a, this.f10023b, this.f10024c, this.f10025d);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f10025d == bVar.f10025d && this.f10022a == bVar.f10022a && this.f10024c == bVar.f10024c && this.f10023b == bVar.f10023b;
    }

    public int hashCode() {
        return (((((this.f10022a * 31) + this.f10023b) * 31) + this.f10024c) * 31) + this.f10025d;
    }

    public String toString() {
        return "Insets{left=" + this.f10022a + ", top=" + this.f10023b + ", right=" + this.f10024c + ", bottom=" + this.f10025d + '}';
    }
}
