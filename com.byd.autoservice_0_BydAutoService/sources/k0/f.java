package k0;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class f<T> implements e<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f3581a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3582b;

    public f(int i3) {
        if (i3 <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f3581a = new Object[i3];
    }

    @Override // k0.e
    public boolean a(T t3) {
        if (c(t3)) {
            throw new IllegalStateException("Already in the pool!");
        }
        int i3 = this.f3582b;
        Object[] objArr = this.f3581a;
        if (i3 >= objArr.length) {
            return false;
        }
        objArr[i3] = t3;
        this.f3582b = i3 + 1;
        return true;
    }

    @Override // k0.e
    public T b() {
        int i3 = this.f3582b;
        if (i3 <= 0) {
            return null;
        }
        int i4 = i3 - 1;
        Object[] objArr = this.f3581a;
        T t3 = (T) objArr[i4];
        objArr[i4] = null;
        this.f3582b = i3 - 1;
        return t3;
    }

    public final boolean c(T t3) {
        for (int i3 = 0; i3 < this.f3582b; i3++) {
            if (this.f3581a[i3] == t3) {
                return true;
            }
        }
        return false;
    }
}
