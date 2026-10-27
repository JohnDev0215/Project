package N0;

import x.AbstractC2641j;

/* JADX INFO: renamed from: N0.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0409d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f4371a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4372b;
    public final int c;
    public final String d;

    public C0409d(Object obj, int i7, int i10, String str) {
        this.f4371a = obj;
        this.f4372b = i7;
        this.c = i10;
        this.d = str;
        if (i7 > i10) {
            throw new IllegalArgumentException("Reversed range is not supported");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0409d)) {
            return false;
        }
        C0409d c0409d = (C0409d) obj;
        return A7.m.a(this.f4371a, c0409d.f4371a) && this.f4372b == c0409d.f4372b && this.c == c0409d.c && A7.m.a(this.d, c0409d.d);
    }

    public final int hashCode() {
        Object obj = this.f4371a;
        return this.d.hashCode() + AbstractC2641j.b(this.c, AbstractC2641j.b(this.f4372b, (obj == null ? 0 : obj.hashCode()) * 31, 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Range(item=");
        sb.append(this.f4371a);
        sb.append(", start=");
        sb.append(this.f4372b);
        sb.append(", end=");
        sb.append(this.c);
        sb.append(", tag=");
        return android.hardware.bydauto.ac.tsimpl.a.k(sb, this.d, ')');
    }

    public C0409d(int i7, int i10, Object obj) {
        this(obj, i7, i10, "");
    }
}
