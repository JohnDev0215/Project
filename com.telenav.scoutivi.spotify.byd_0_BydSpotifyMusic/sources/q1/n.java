package q1;

import java.security.MessageDigest;
import java.util.Map;

/* JADX INFO: compiled from: EngineKey.java */
/* JADX INFO: loaded from: classes.dex */
class n implements n1.f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f9080b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f9081c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f9082d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Class<?> f9083e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Class<?> f9084f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final n1.f f9085g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Map<Class<?>, n1.m<?>> f9086h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final n1.i f9087i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f9088j;

    n(Object obj, n1.f fVar, int i7, int i8, Map<Class<?>, n1.m<?>> map, Class<?> cls, Class<?> cls2, n1.i iVar) {
        this.f9080b = k2.j.d(obj);
        this.f9085g = (n1.f) k2.j.e(fVar, "Signature must not be null");
        this.f9081c = i7;
        this.f9082d = i8;
        this.f9086h = (Map) k2.j.d(map);
        this.f9083e = (Class) k2.j.e(cls, "Resource class must not be null");
        this.f9084f = (Class) k2.j.e(cls2, "Transcode class must not be null");
        this.f9087i = (n1.i) k2.j.d(iVar);
    }

    @Override // n1.f
    public void a(MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }

    @Override // n1.f
    public boolean equals(Object obj) {
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return this.f9080b.equals(nVar.f9080b) && this.f9085g.equals(nVar.f9085g) && this.f9082d == nVar.f9082d && this.f9081c == nVar.f9081c && this.f9086h.equals(nVar.f9086h) && this.f9083e.equals(nVar.f9083e) && this.f9084f.equals(nVar.f9084f) && this.f9087i.equals(nVar.f9087i);
    }

    @Override // n1.f
    public int hashCode() {
        if (this.f9088j == 0) {
            int iHashCode = this.f9080b.hashCode();
            this.f9088j = iHashCode;
            int iHashCode2 = (((((iHashCode * 31) + this.f9085g.hashCode()) * 31) + this.f9081c) * 31) + this.f9082d;
            this.f9088j = iHashCode2;
            int iHashCode3 = (iHashCode2 * 31) + this.f9086h.hashCode();
            this.f9088j = iHashCode3;
            int iHashCode4 = (iHashCode3 * 31) + this.f9083e.hashCode();
            this.f9088j = iHashCode4;
            int iHashCode5 = (iHashCode4 * 31) + this.f9084f.hashCode();
            this.f9088j = iHashCode5;
            this.f9088j = (iHashCode5 * 31) + this.f9087i.hashCode();
        }
        return this.f9088j;
    }

    public String toString() {
        return "EngineKey{model=" + this.f9080b + ", width=" + this.f9081c + ", height=" + this.f9082d + ", resourceClass=" + this.f9083e + ", transcodeClass=" + this.f9084f + ", signature=" + this.f9085g + ", hashCode=" + this.f9088j + ", transformations=" + this.f9086h + ", options=" + this.f9087i + '}';
    }
}
