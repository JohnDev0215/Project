package k2;

/* JADX INFO: compiled from: MultiClassKey.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Class<?> f7255a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Class<?> f7256b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Class<?> f7257c;

    public i() {
    }

    public void a(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        this.f7255a = cls;
        this.f7256b = cls2;
        this.f7257c = cls3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        i iVar = (i) obj;
        return this.f7255a.equals(iVar.f7255a) && this.f7256b.equals(iVar.f7256b) && k.c(this.f7257c, iVar.f7257c);
    }

    public int hashCode() {
        int iHashCode = ((this.f7255a.hashCode() * 31) + this.f7256b.hashCode()) * 31;
        Class<?> cls = this.f7257c;
        return iHashCode + (cls != null ? cls.hashCode() : 0);
    }

    public String toString() {
        return "MultiClassKey{first=" + this.f7255a + ", second=" + this.f7256b + '}';
    }

    public i(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        a(cls, cls2, cls3);
    }
}
