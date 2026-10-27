package y1;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b f24112e = new b(0, 0, 0, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f24113a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f24114b;
    public final int c;
    public final int d;

    public b(int i7, int i10, int i11, int i12) {
        this.f24113a = i7;
        this.f24114b = i10;
        this.c = i11;
        this.d = i12;
    }

    public static b a(int i7, int i10, int i11, int i12) {
        return (i7 == 0 && i10 == 0 && i11 == 0 && i12 == 0) ? f24112e : new b(i7, i10, i11, i12);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.d == bVar.d && this.f24113a == bVar.f24113a && this.c == bVar.c && this.f24114b == bVar.f24114b;
    }

    public final int hashCode() {
        return (((((this.f24113a * 31) + this.f24114b) * 31) + this.c) * 31) + this.d;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Insets{left=");
        sb.append(this.f24113a);
        sb.append(", top=");
        sb.append(this.f24114b);
        sb.append(", right=");
        sb.append(this.c);
        sb.append(", bottom=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.d, '}');
    }
}
