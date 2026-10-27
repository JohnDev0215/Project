package D;

import E0.l0;

/* JADX INFO: loaded from: classes.dex */
public final class I extends g0.n implements l0 {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f1029y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f1030z;

    @Override // E0.l0
    public final Object j(a1.b bVar, Object obj) {
        O o = obj instanceof O ? (O) obj : null;
        if (o == null) {
            o = new O();
        }
        o.f1041a = this.f1029y;
        o.f1042b = this.f1030z;
        return o;
    }
}
