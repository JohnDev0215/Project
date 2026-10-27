package D;

import E0.l0;

/* JADX INFO: loaded from: classes.dex */
public final class E extends g0.n implements l0 {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public g0.e f1024y;

    @Override // E0.l0
    public final Object j(a1.b bVar, Object obj) {
        O o = obj instanceof O ? (O) obj : null;
        if (o == null) {
            o = new O();
        }
        o.c = new A(this.f1024y);
        return o;
    }
}
