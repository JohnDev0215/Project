package b.e.b;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
class g<T> implements f<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object[] f2380a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2381b;

    g(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f2380a = new Object[i2];
    }

    @Override // b.e.b.f
    public boolean a(T t) {
        int i2 = this.f2381b;
        Object[] objArr = this.f2380a;
        if (i2 >= objArr.length) {
            return false;
        }
        objArr[i2] = t;
        this.f2381b = i2 + 1;
        return true;
    }

    @Override // b.e.b.f
    public T b() {
        int i2 = this.f2381b;
        if (i2 <= 0) {
            return null;
        }
        int i3 = i2 - 1;
        Object[] objArr = this.f2380a;
        T t = (T) objArr[i3];
        objArr[i3] = null;
        this.f2381b = i2 - 1;
        return t;
    }

    @Override // b.e.b.f
    public void c(T[] tArr, int i2) {
        if (i2 > tArr.length) {
            i2 = tArr.length;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            T t = tArr[i3];
            int i4 = this.f2381b;
            Object[] objArr = this.f2380a;
            if (i4 < objArr.length) {
                objArr[i4] = t;
                this.f2381b = i4 + 1;
            }
        }
    }
}
