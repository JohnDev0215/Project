package g4;

import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class q implements e4.e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f14939b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Class f14940e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Class f14941f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final e4.e f14942g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final A4.d f14943h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final e4.h f14944i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f14945j;

    public q(Object obj, e4.e eVar, int i7, int i10, A4.d dVar, Class cls, Class cls2, e4.h hVar) {
        A4.h.c(obj, "Argument must not be null");
        this.f14939b = obj;
        this.f14942g = eVar;
        this.c = i7;
        this.d = i10;
        A4.h.c(dVar, "Argument must not be null");
        this.f14943h = dVar;
        A4.h.c(cls, "Resource class must not be null");
        this.f14940e = cls;
        A4.h.c(cls2, "Transcode class must not be null");
        this.f14941f = cls2;
        A4.h.c(hVar, "Argument must not be null");
        this.f14944i = hVar;
    }

    @Override // e4.e
    public final void b(MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }

    @Override // e4.e
    public final boolean equals(Object obj) {
        if (!(obj instanceof q)) {
            return false;
        }
        q qVar = (q) obj;
        return this.f14939b.equals(qVar.f14939b) && this.f14942g.equals(qVar.f14942g) && this.d == qVar.d && this.c == qVar.c && this.f14943h.equals(qVar.f14943h) && this.f14940e.equals(qVar.f14940e) && this.f14941f.equals(qVar.f14941f) && this.f14944i.equals(qVar.f14944i);
    }

    @Override // e4.e
    public final int hashCode() {
        if (this.f14945j == 0) {
            int iHashCode = this.f14939b.hashCode();
            this.f14945j = iHashCode;
            int iHashCode2 = ((((this.f14942g.hashCode() + (iHashCode * 31)) * 31) + this.c) * 31) + this.d;
            this.f14945j = iHashCode2;
            int iHashCode3 = this.f14943h.hashCode() + (iHashCode2 * 31);
            this.f14945j = iHashCode3;
            int iHashCode4 = this.f14940e.hashCode() + (iHashCode3 * 31);
            this.f14945j = iHashCode4;
            int iHashCode5 = this.f14941f.hashCode() + (iHashCode4 * 31);
            this.f14945j = iHashCode5;
            this.f14945j = this.f14944i.f14434b.hashCode() + (iHashCode5 * 31);
        }
        return this.f14945j;
    }

    public final String toString() {
        return "EngineKey{model=" + this.f14939b + ", width=" + this.c + ", height=" + this.d + ", resourceClass=" + this.f14940e + ", transcodeClass=" + this.f14941f + ", signature=" + this.f14942g + ", hashCode=" + this.f14945j + ", transformations=" + this.f14943h + ", options=" + this.f14944i + '}';
    }
}
