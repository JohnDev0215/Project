package a.e.b;

/* JADX INFO: loaded from: classes.dex */
class g<T> implements f<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object[] f183a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f184b;

    g(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f183a = new Object[i];
    }

    @Override // a.e.b.f
    public boolean a(T t) {
        int i = this.f184b;
        Object[] objArr = this.f183a;
        if (i >= objArr.length) {
            return false;
        }
        objArr[i] = t;
        this.f184b = i + 1;
        return true;
    }

    @Override // a.e.b.f
    public T b() {
        int i = this.f184b;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.f183a;
        T t = (T) objArr[i2];
        objArr[i2] = null;
        this.f184b = i - 1;
        return t;
    }

    @Override // a.e.b.f
    public void c(T[] tArr, int i) {
        if (i > tArr.length) {
            i = tArr.length;
        }
        for (int i2 = 0; i2 < i; i2++) {
            T t = tArr[i2];
            int i3 = this.f184b;
            Object[] objArr = this.f183a;
            if (i3 < objArr.length) {
                objArr[i3] = t;
                this.f184b = i3 + 1;
            }
        }
    }
}
