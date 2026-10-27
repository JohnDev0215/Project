package L3;

import A7.m;

/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static final g c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final com.bumptech.glide.d f4005a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final com.bumptech.glide.d f4006b;

    static {
        b bVar = b.d;
        c = new g(bVar, bVar);
    }

    public g(com.bumptech.glide.d dVar, com.bumptech.glide.d dVar2) {
        this.f4005a = dVar;
        this.f4006b = dVar2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return m.a(this.f4005a, gVar.f4005a) && m.a(this.f4006b, gVar.f4006b);
    }

    public final int hashCode() {
        return this.f4006b.hashCode() + (this.f4005a.hashCode() * 31);
    }

    public final String toString() {
        return "Size(width=" + this.f4005a + ", height=" + this.f4006b + ')';
    }
}
