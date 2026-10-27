package b.g.j;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class f<T> implements e<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object[] f1784a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1785b;

    public f(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f1784a = new Object[i];
    }

    private boolean c(T t) {
        for (int i = 0; i < this.f1785b; i++) {
            if (this.f1784a[i] == t) {
                return true;
            }
        }
        return false;
    }

    @Override // b.g.j.e
    public boolean a(T t) {
        if (c(t)) {
            throw new IllegalStateException("Already in the pool!");
        }
        int i = this.f1785b;
        Object[] objArr = this.f1784a;
        if (i >= objArr.length) {
            return false;
        }
        objArr[i] = t;
        this.f1785b = i + 1;
        return true;
    }

    @Override // b.g.j.e
    public T b() {
        int i = this.f1785b;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.f1784a;
        T t = (T) objArr[i2];
        objArr[i2] = null;
        this.f1785b = i - 1;
        return t;
    }
}
