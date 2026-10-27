package m5;

import q5.C2185a;

/* JADX INFO: loaded from: classes.dex */
public final class S implements j5.z {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Class f19188m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ j5.y f19189n;

    public /* synthetic */ S(Class cls, j5.y yVar, int i7) {
        this.l = i7;
        this.f19188m = cls;
        this.f19189n = yVar;
    }

    @Override // j5.z
    public final j5.y a(j5.m mVar, C2185a c2185a) {
        switch (this.l) {
            case 0:
                if (c2185a.f21253a == this.f19188m) {
                    return this.f19189n;
                }
                return null;
            default:
                Class cls = this.f19188m;
                Class<?> cls2 = c2185a.f21253a;
                if (cls.isAssignableFrom(cls2)) {
                    return new C1826c(this, cls2);
                }
                return null;
        }
    }

    public final String toString() {
        switch (this.l) {
            case 0:
                return "Factory[type=" + this.f19188m.getName() + ",adapter=" + this.f19189n + "]";
            default:
                return "Factory[typeHierarchy=" + this.f19188m.getName() + ",adapter=" + this.f19189n + "]";
        }
    }
}
