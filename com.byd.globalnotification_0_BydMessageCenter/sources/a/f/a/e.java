package a.f.a;

/* JADX WARN: Unexpected interfaces in signature: [a.f.a.e<T>] */
/* JADX INFO: loaded from: classes.dex */
public class e<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f518a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f519b;

    public e(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f518a = new Object[i];
    }

    public T a() {
        int i = this.f519b;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.f518a;
        T t = (T) objArr[i2];
        objArr[i2] = null;
        this.f519b = i - 1;
        return t;
    }

    public boolean b(T t) {
        int i = this.f519b;
        Object[] objArr = this.f518a;
        if (i >= objArr.length) {
            return false;
        }
        objArr[i] = t;
        this.f519b = i + 1;
        return true;
    }
}
