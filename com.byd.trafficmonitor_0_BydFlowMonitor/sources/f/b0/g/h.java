package f.b0.g;

import f.q;
import f.s;
import f.z;

/* JADX INFO: compiled from: RealResponseBody.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends z {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f5086b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g.e f5087c;

    public h(q qVar, g.e eVar) {
        this.f5086b = qVar;
        this.f5087c = eVar;
    }

    @Override // f.z
    public long O() {
        return e.a(this.f5086b);
    }

    @Override // f.z
    public s P() {
        String strA = this.f5086b.a("Content-Type");
        if (strA != null) {
            return s.c(strA);
        }
        return null;
    }

    @Override // f.z
    public g.e S() {
        return this.f5087c;
    }
}
