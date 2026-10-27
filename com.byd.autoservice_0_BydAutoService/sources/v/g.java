package v;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
public class g<T> implements f<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f5393a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5394b;

    public g(int i3) {
        if (i3 <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f5393a = new Object[i3];
    }

    @Override // v.f
    public boolean a(T t3) {
        int i3 = this.f5394b;
        Object[] objArr = this.f5393a;
        if (i3 >= objArr.length) {
            return false;
        }
        objArr[i3] = t3;
        this.f5394b = i3 + 1;
        return true;
    }

    @Override // v.f
    public T b() {
        int i3 = this.f5394b;
        if (i3 <= 0) {
            return null;
        }
        int i4 = i3 - 1;
        Object[] objArr = this.f5393a;
        T t3 = (T) objArr[i4];
        objArr[i4] = null;
        this.f5394b = i3 - 1;
        return t3;
    }

    @Override // v.f
    public void c(T[] tArr, int i3) {
        if (i3 > tArr.length) {
            i3 = tArr.length;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            T t3 = tArr[i4];
            int i5 = this.f5394b;
            Object[] objArr = this.f5393a;
            if (i5 < objArr.length) {
                objArr[i5] = t3;
                this.f5394b = i5 + 1;
            }
        }
    }
}
