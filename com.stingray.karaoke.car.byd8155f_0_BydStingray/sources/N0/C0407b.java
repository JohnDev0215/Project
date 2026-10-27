package N0;

import x.AbstractC2641j;

/* JADX INFO: renamed from: N0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0407b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f4366a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4367b;
    public int c;
    public final String d;

    public /* synthetic */ C0407b(int i7, int i10, int i11, Object obj) {
        this(obj, i7, (i11 & 4) != 0 ? Integer.MIN_VALUE : i10, "");
    }

    public final C0409d a(int i7) {
        int i10 = this.c;
        if (i10 != Integer.MIN_VALUE) {
            i7 = i10;
        }
        if (i7 == Integer.MIN_VALUE) {
            throw new IllegalStateException("Item.end should be set first");
        }
        return new C0409d(this.f4366a, this.f4367b, i7, this.d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0407b)) {
            return false;
        }
        C0407b c0407b = (C0407b) obj;
        return A7.m.a(this.f4366a, c0407b.f4366a) && this.f4367b == c0407b.f4367b && this.c == c0407b.c && A7.m.a(this.d, c0407b.d);
    }

    public final int hashCode() {
        Object obj = this.f4366a;
        return this.d.hashCode() + AbstractC2641j.b(this.c, AbstractC2641j.b(this.f4367b, (obj == null ? 0 : obj.hashCode()) * 31, 31), 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("MutableRange(item=");
        sb.append(this.f4366a);
        sb.append(", start=");
        sb.append(this.f4367b);
        sb.append(", end=");
        sb.append(this.c);
        sb.append(", tag=");
        return android.hardware.bydauto.ac.tsimpl.a.k(sb, this.d, ')');
    }

    public C0407b(Object obj, int i7, int i10, String str) {
        this.f4366a = obj;
        this.f4367b = i7;
        this.c = i10;
        this.d = str;
    }
}
