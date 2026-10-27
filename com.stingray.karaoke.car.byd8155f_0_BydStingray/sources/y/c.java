package Y;

import n7.AbstractC1990e;

/* JADX INFO: loaded from: classes.dex */
public class c extends AbstractC1990e implements W.d {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final c f10586n = new c(m.f10600e, 0);
    public final m l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f10587m;

    public c(m mVar, int i7) {
        this.l = mVar;
        this.f10587m = i7;
    }

    @Override // W.d
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public e m() {
        return new e(this);
    }

    public final c c(Object obj, Z.a aVar) {
        C1.j jVarU = this.l.u(obj, obj != null ? obj.hashCode() : 0, aVar, 0);
        return jVarU == null ? this : new c((m) jVarU.f944m, this.f10587m + jVarU.l);
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.l.d(obj != null ? obj.hashCode() : 0, 0, obj);
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        return this.l.g(obj != null ? obj.hashCode() : 0, 0, obj);
    }
}
