package h0;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class f<T> implements e<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object[] f6591a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f6592b;

    public f(int i7) {
        if (i7 <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f6591a = new Object[i7];
    }

    private boolean c(T t6) {
        for (int i7 = 0; i7 < this.f6592b; i7++) {
            if (this.f6591a[i7] == t6) {
                return true;
            }
        }
        return false;
    }

    @Override // h0.e
    public boolean a(T t6) {
        if (c(t6)) {
            throw new IllegalStateException("Already in the pool!");
        }
        int i7 = this.f6592b;
        Object[] objArr = this.f6591a;
        if (i7 >= objArr.length) {
            return false;
        }
        objArr[i7] = t6;
        this.f6592b = i7 + 1;
        return true;
    }

    @Override // h0.e
    public T b() {
        int i7 = this.f6592b;
        if (i7 <= 0) {
            return null;
        }
        int i8 = i7 - 1;
        Object[] objArr = this.f6591a;
        T t6 = (T) objArr[i8];
        objArr[i8] = null;
        this.f6592b = i7 - 1;
        return t6;
    }
}
