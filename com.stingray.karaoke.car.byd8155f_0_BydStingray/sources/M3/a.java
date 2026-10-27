package M3;

import A3.o;
import K3.j;
import K3.p;

/* JADX INFO: loaded from: classes.dex */
public final class a implements e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4190b;
    public final boolean c = false;

    public a(int i7) {
        this.f4190b = i7;
        if (i7 <= 0) {
            throw new IllegalArgumentException("durationMillis must be > 0.");
        }
    }

    @Override // M3.e
    public final f a(o oVar, j jVar) {
        if ((jVar instanceof p) && ((p) jVar).c != B3.f.l) {
            return new b(oVar, jVar, this.f4190b, this.c);
        }
        return new d(oVar, jVar);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof a) {
            a aVar = (a) obj;
            if (this.f4190b == aVar.f4190b && this.c == aVar.c) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.c) + (this.f4190b * 31);
    }
}
