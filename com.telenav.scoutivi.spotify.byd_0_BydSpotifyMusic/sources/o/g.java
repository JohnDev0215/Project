package o;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
class g<T> implements f<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object[] f8595a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f8596b;

    g(int i7) {
        if (i7 <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f8595a = new Object[i7];
    }

    @Override // o.f
    public boolean a(T t6) {
        int i7 = this.f8596b;
        Object[] objArr = this.f8595a;
        if (i7 >= objArr.length) {
            return false;
        }
        objArr[i7] = t6;
        this.f8596b = i7 + 1;
        return true;
    }

    @Override // o.f
    public T b() {
        int i7 = this.f8596b;
        if (i7 <= 0) {
            return null;
        }
        int i8 = i7 - 1;
        Object[] objArr = this.f8595a;
        T t6 = (T) objArr[i8];
        objArr[i8] = null;
        this.f8596b = i7 - 1;
        return t6;
    }

    @Override // o.f
    public void c(T[] tArr, int i7) {
        if (i7 > tArr.length) {
            i7 = tArr.length;
        }
        for (int i8 = 0; i8 < i7; i8++) {
            T t6 = tArr[i8];
            int i9 = this.f8596b;
            Object[] objArr = this.f8595a;
            if (i9 < objArr.length) {
                objArr[i9] = t6;
                this.f8596b = i9 + 1;
            }
        }
    }
}
