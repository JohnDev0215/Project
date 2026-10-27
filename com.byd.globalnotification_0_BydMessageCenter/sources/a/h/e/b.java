package a.h.e;

import android.graphics.Insets;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b f719e = new b(0, 0, 0, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f720a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f721b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f722c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f723d;

    public b(int i, int i2, int i3, int i4) {
        this.f720a = i;
        this.f721b = i2;
        this.f722c = i3;
        this.f723d = i4;
    }

    public static b a(int i, int i2, int i3, int i4) {
        return (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? f719e : new b(i, i2, i3, i4);
    }

    public Insets b() {
        return Insets.of(this.f720a, this.f721b, this.f722c, this.f723d);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f723d == bVar.f723d && this.f720a == bVar.f720a && this.f722c == bVar.f722c && this.f721b == bVar.f721b;
    }

    public int hashCode() {
        return (((((this.f720a * 31) + this.f721b) * 31) + this.f722c) * 31) + this.f723d;
    }

    public String toString() {
        StringBuilder sbD = b.a.a.a.a.d("Insets{left=");
        sbD.append(this.f720a);
        sbD.append(", top=");
        sbD.append(this.f721b);
        sbD.append(", right=");
        sbD.append(this.f722c);
        sbD.append(", bottom=");
        sbD.append(this.f723d);
        sbD.append('}');
        return sbD.toString();
    }
}
