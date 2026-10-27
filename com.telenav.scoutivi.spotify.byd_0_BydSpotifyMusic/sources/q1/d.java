package q1;

import java.security.MessageDigest;

/* JADX INFO: compiled from: DataCacheKey.java */
/* JADX INFO: loaded from: classes.dex */
final class d implements n1.f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final n1.f f8959b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final n1.f f8960c;

    d(n1.f fVar, n1.f fVar2) {
        this.f8959b = fVar;
        this.f8960c = fVar2;
    }

    @Override // n1.f
    public void a(MessageDigest messageDigest) {
        this.f8959b.a(messageDigest);
        this.f8960c.a(messageDigest);
    }

    @Override // n1.f
    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return this.f8959b.equals(dVar.f8959b) && this.f8960c.equals(dVar.f8960c);
    }

    @Override // n1.f
    public int hashCode() {
        return (this.f8959b.hashCode() * 31) + this.f8960c.hashCode();
    }

    public String toString() {
        return "DataCacheKey{sourceKey=" + this.f8959b + ", signature=" + this.f8960c + '}';
    }
}
