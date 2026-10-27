package g4;

import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class e implements e4.e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e4.e f14861b;
    public final e4.e c;

    public e(e4.e eVar, e4.e eVar2) {
        this.f14861b = eVar;
        this.c = eVar2;
    }

    @Override // e4.e
    public final void b(MessageDigest messageDigest) {
        this.f14861b.b(messageDigest);
        this.c.b(messageDigest);
    }

    @Override // e4.e
    public final boolean equals(Object obj) {
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return this.f14861b.equals(eVar.f14861b) && this.c.equals(eVar.c);
    }

    @Override // e4.e
    public final int hashCode() {
        return this.c.hashCode() + (this.f14861b.hashCode() * 31);
    }

    public final String toString() {
        return "DataCacheKey{sourceKey=" + this.f14861b + ", signature=" + this.c + '}';
    }
}
