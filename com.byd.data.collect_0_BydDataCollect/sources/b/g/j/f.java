package b.g.j;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class f<T> implements e<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object[] f2661a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2662b;

    public f(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f2661a = new Object[i2];
    }

    private boolean c(T t) {
        for (int i2 = 0; i2 < this.f2662b; i2++) {
            if (this.f2661a[i2] == t) {
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
        int i2 = this.f2662b;
        Object[] objArr = this.f2661a;
        if (i2 >= objArr.length) {
            return false;
        }
        objArr[i2] = t;
        this.f2662b = i2 + 1;
        return true;
    }

    @Override // b.g.j.e
    public T b() {
        int i2 = this.f2662b;
        if (i2 <= 0) {
            return null;
        }
        int i3 = i2 - 1;
        Object[] objArr = this.f2661a;
        T t = (T) objArr[i3];
        objArr[i3] = null;
        this.f2662b = i2 - 1;
        return t;
    }
}
