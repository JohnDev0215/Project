package e.d0.g;

import e.b0;
import e.s;
import e.u;

/* JADX INFO: compiled from: RealResponseBody.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends b0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final s f3407d;
    public final f.e s;

    public h(s sVar, f.e eVar) {
        this.f3407d = sVar;
        this.s = eVar;
    }

    @Override // e.b0
    public u H() {
        String strA = this.f3407d.a("Content-Type");
        if (strA != null) {
            return u.c(strA);
        }
        return null;
    }

    @Override // e.b0
    public f.e K() {
        return this.s;
    }

    @Override // e.b0
    public long v() {
        return e.a(this.f3407d);
    }
}
