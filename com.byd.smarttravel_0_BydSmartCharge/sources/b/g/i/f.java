package b.g.i;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class f<T> implements e<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f1087a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1088b;

    public f(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f1087a = new Object[i];
    }

    @Override // b.g.i.e
    public boolean a(T t) {
        if (c(t)) {
            throw new IllegalStateException("Already in the pool!");
        }
        int i = this.f1088b;
        Object[] objArr = this.f1087a;
        if (i >= objArr.length) {
            return false;
        }
        objArr[i] = t;
        this.f1088b = i + 1;
        return true;
    }

    @Override // b.g.i.e
    public T b() {
        int i = this.f1088b;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.f1087a;
        T t = (T) objArr[i2];
        objArr[i2] = null;
        this.f1088b = i - 1;
        return t;
    }

    public final boolean c(T t) {
        for (int i = 0; i < this.f1088b; i++) {
            if (this.f1087a[i] == t) {
                return true;
            }
        }
        return false;
    }
}
