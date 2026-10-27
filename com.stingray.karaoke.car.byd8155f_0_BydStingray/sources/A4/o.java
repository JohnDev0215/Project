package A4;

/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Class f429a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Class f430b;
    public Class c;

    public o(Class cls, Class cls2, Class cls3) {
        this.f429a = cls;
        this.f430b = cls2;
        this.c = cls3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || o.class != obj.getClass()) {
            return false;
        }
        o oVar = (o) obj;
        return this.f429a.equals(oVar.f429a) && this.f430b.equals(oVar.f430b) && q.b(this.c, oVar.c);
    }

    public final int hashCode() {
        int iHashCode = (this.f430b.hashCode() + (this.f429a.hashCode() * 31)) * 31;
        Class cls = this.c;
        return iHashCode + (cls != null ? cls.hashCode() : 0);
    }

    public final String toString() {
        return "MultiClassKey{first=" + this.f429a + ", second=" + this.f430b + '}';
    }
}
