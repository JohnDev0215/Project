package androidx.constraintlayout.solver;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
class g<T> implements f<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object[] f640a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f641b;

    g(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f640a = new Object[i];
    }

    @Override // androidx.constraintlayout.solver.f
    public boolean a(T t) {
        int i = this.f641b;
        Object[] objArr = this.f640a;
        if (i >= objArr.length) {
            return false;
        }
        objArr[i] = t;
        this.f641b = i + 1;
        return true;
    }

    @Override // androidx.constraintlayout.solver.f
    public T b() {
        int i = this.f641b;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.f640a;
        T t = (T) objArr[i2];
        objArr[i2] = null;
        this.f641b = i - 1;
        return t;
    }

    @Override // androidx.constraintlayout.solver.f
    public void c(T[] tArr, int i) {
        if (i > tArr.length) {
            i = tArr.length;
        }
        for (int i2 = 0; i2 < i; i2++) {
            T t = tArr[i2];
            int i3 = this.f641b;
            Object[] objArr = this.f640a;
            if (i3 < objArr.length) {
                objArr[i3] = t;
                this.f641b = i3 + 1;
            }
        }
    }
}
