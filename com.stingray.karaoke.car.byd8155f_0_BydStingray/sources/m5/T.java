package m5;

import q5.C2185a;

/* JADX INFO: loaded from: classes.dex */
public final class T implements j5.z {
    public final /* synthetic */ Class l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Class f19190m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ j5.y f19191n;

    public T(Class cls, Class cls2, j5.y yVar) {
        this.l = cls;
        this.f19190m = cls2;
        this.f19191n = yVar;
    }

    @Override // j5.z
    public final j5.y a(j5.m mVar, C2185a c2185a) {
        Class cls = this.l;
        Class cls2 = c2185a.f21253a;
        if (cls2 == cls || cls2 == this.f19190m) {
            return this.f19191n;
        }
        return null;
    }

    public final String toString() {
        return "Factory[type=" + this.f19190m.getName() + "+" + this.l.getName() + ",adapter=" + this.f19191n + "]";
    }
}
