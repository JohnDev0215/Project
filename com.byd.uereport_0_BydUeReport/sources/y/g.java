package y;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class g<T> implements f<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f9138a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f9139b;

    public g(int i8) {
        if (i8 <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f9138a = new Object[i8];
    }

    @Override // y.f
    public boolean a(T t7) {
        int i8 = this.f9139b;
        Object[] objArr = this.f9138a;
        if (i8 >= objArr.length) {
            return false;
        }
        objArr[i8] = t7;
        this.f9139b = i8 + 1;
        return true;
    }

    @Override // y.f
    public T b() {
        int i8 = this.f9139b;
        if (i8 <= 0) {
            return null;
        }
        int i9 = i8 - 1;
        Object[] objArr = this.f9138a;
        T t7 = (T) objArr[i9];
        objArr[i9] = null;
        this.f9139b = i8 - 1;
        return t7;
    }

    @Override // y.f
    public void c(T[] tArr, int i8) {
        if (i8 > tArr.length) {
            i8 = tArr.length;
        }
        for (int i9 = 0; i9 < i8; i9++) {
            T t7 = tArr[i9];
            int i10 = this.f9139b;
            Object[] objArr = this.f9138a;
            if (i10 < objArr.length) {
                objArr[i10] = t7;
                this.f9139b = i10 + 1;
            }
        }
    }
}
