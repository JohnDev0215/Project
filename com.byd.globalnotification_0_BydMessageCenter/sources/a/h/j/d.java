package a.h.j;

/* JADX WARN: Unexpected interfaces in signature: [a.h.j.d<T>] */
/* JADX INFO: loaded from: classes.dex */
public class d<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f803a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f804b;

    public d(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f803a = new Object[i];
    }

    public T a() {
        int i = this.f804b;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.f803a;
        T t = (T) objArr[i2];
        objArr[i2] = null;
        this.f804b = i - 1;
        return t;
    }

    public boolean b(T t) {
        boolean z;
        int i = 0;
        while (true) {
            if (i >= this.f804b) {
                z = false;
                break;
            }
            if (this.f803a[i] == t) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            throw new IllegalStateException("Already in the pool!");
        }
        int i2 = this.f804b;
        Object[] objArr = this.f803a;
        if (i2 >= objArr.length) {
            return false;
        }
        objArr[i2] = t;
        this.f804b = i2 + 1;
        return true;
    }
}
