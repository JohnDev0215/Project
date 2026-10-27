package e4;

import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class h implements e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final A4.d f14434b = new A4.d(0);

    @Override // e4.e
    public final void b(MessageDigest messageDigest) {
        int i7 = 0;
        while (true) {
            A4.d dVar = this.f14434b;
            if (i7 >= dVar.f22587n) {
                return;
            }
            g gVar = (g) dVar.g(i7);
            Object objK = this.f14434b.k(i7);
            f fVar = gVar.f14433b;
            if (gVar.d == null) {
                gVar.d = gVar.c.getBytes(e.f14430a);
            }
            fVar.e(gVar.d, objK, messageDigest);
            i7++;
        }
    }

    public final Object c(g gVar) {
        A4.d dVar = this.f14434b;
        return dVar.containsKey(gVar) ? dVar.get(gVar) : gVar.f14432a;
    }

    @Override // e4.e
    public final boolean equals(Object obj) {
        if (obj instanceof h) {
            return this.f14434b.equals(((h) obj).f14434b);
        }
        return false;
    }

    @Override // e4.e
    public final int hashCode() {
        return this.f14434b.hashCode();
    }

    public final String toString() {
        return "Options{values=" + this.f14434b + '}';
    }
}
