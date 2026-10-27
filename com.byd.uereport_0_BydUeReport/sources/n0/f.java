package n0;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class f<T> implements e<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f6508a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6509b;

    public f(int i8) {
        if (i8 <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f6508a = new Object[i8];
    }

    @Override // n0.e
    public boolean a(T t7) {
        if (c(t7)) {
            throw new IllegalStateException("Already in the pool!");
        }
        int i8 = this.f6509b;
        Object[] objArr = this.f6508a;
        if (i8 >= objArr.length) {
            return false;
        }
        objArr[i8] = t7;
        this.f6509b = i8 + 1;
        return true;
    }

    @Override // n0.e
    public T b() {
        int i8 = this.f6509b;
        if (i8 <= 0) {
            return null;
        }
        int i9 = i8 - 1;
        Object[] objArr = this.f6508a;
        T t7 = (T) objArr[i9];
        objArr[i9] = null;
        this.f6509b = i8 - 1;
        return t7;
    }

    public final boolean c(T t7) {
        for (int i8 = 0; i8 < this.f6509b; i8++) {
            if (this.f6508a[i8] == t7) {
                return true;
            }
        }
        return false;
    }
}
