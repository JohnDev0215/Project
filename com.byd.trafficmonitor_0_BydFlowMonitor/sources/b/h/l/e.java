package b.h.l;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class e<T> implements d<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f2577a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2578b;

    public e(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f2577a = new Object[i2];
    }

    @Override // b.h.l.d
    public boolean a(T t) {
        if (c(t)) {
            throw new IllegalStateException("Already in the pool!");
        }
        int i2 = this.f2578b;
        Object[] objArr = this.f2577a;
        if (i2 >= objArr.length) {
            return false;
        }
        objArr[i2] = t;
        this.f2578b = i2 + 1;
        return true;
    }

    @Override // b.h.l.d
    public T b() {
        int i2 = this.f2578b;
        if (i2 <= 0) {
            return null;
        }
        int i3 = i2 - 1;
        Object[] objArr = this.f2577a;
        T t = (T) objArr[i3];
        objArr[i3] = null;
        this.f2578b = i2 - 1;
        return t;
    }

    public final boolean c(T t) {
        for (int i2 = 0; i2 < this.f2578b; i2++) {
            if (this.f2577a[i2] == t) {
                return true;
            }
        }
        return false;
    }
}
