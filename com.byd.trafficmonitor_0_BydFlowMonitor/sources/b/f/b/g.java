package b.f.b;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class g<T> implements f<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f2196a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2197b;

    public g(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f2196a = new Object[i2];
    }

    @Override // b.f.b.f
    public boolean a(T t) {
        int i2 = this.f2197b;
        Object[] objArr = this.f2196a;
        if (i2 >= objArr.length) {
            return false;
        }
        objArr[i2] = t;
        this.f2197b = i2 + 1;
        return true;
    }

    @Override // b.f.b.f
    public T b() {
        int i2 = this.f2197b;
        if (i2 <= 0) {
            return null;
        }
        int i3 = i2 - 1;
        Object[] objArr = this.f2196a;
        T t = (T) objArr[i3];
        objArr[i3] = null;
        this.f2197b = i2 - 1;
        return t;
    }

    @Override // b.f.b.f
    public void c(T[] tArr, int i2) {
        if (i2 > tArr.length) {
            i2 = tArr.length;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            T t = tArr[i3];
            int i4 = this.f2197b;
            Object[] objArr = this.f2196a;
            if (i4 < objArr.length) {
                objArr[i4] = t;
                this.f2197b = i4 + 1;
            }
        }
    }
}
