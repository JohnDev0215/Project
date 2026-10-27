package u2;

/* JADX INFO: renamed from: u2.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2492C {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f22663a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f22664b;
    public final int c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f22665e;

    public C2492C(Object obj) {
        this(-1L, obj);
    }

    public final C2492C a(Object obj) {
        if (this.f22663a.equals(obj)) {
            return this;
        }
        return new C2492C(obj, this.f22664b, this.c, this.d, this.f22665e);
    }

    public final C2492C b(long j3) {
        if (this.d == j3) {
            return this;
        }
        return new C2492C(this.f22663a, this.f22664b, this.c, j3, this.f22665e);
    }

    public final boolean c() {
        return this.f22664b != -1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2492C)) {
            return false;
        }
        C2492C c2492c = (C2492C) obj;
        return this.f22663a.equals(c2492c.f22663a) && this.f22664b == c2492c.f22664b && this.c == c2492c.c && this.d == c2492c.d && this.f22665e == c2492c.f22665e;
    }

    public final int hashCode() {
        return ((((((((this.f22663a.hashCode() + 527) * 31) + this.f22664b) * 31) + this.c) * 31) + ((int) this.d)) * 31) + this.f22665e;
    }

    public C2492C(long j3, Object obj) {
        this(obj, -1, -1, j3, -1);
    }

    public C2492C(int i7, long j3, Object obj) {
        this(obj, -1, -1, j3, i7);
    }

    public C2492C(Object obj, int i7, int i10, long j3, int i11) {
        this.f22663a = obj;
        this.f22664b = i7;
        this.c = i10;
        this.d = j3;
        this.f22665e = i11;
    }
}
