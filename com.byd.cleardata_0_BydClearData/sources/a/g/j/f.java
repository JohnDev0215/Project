package a.g.j;

/* JADX INFO: loaded from: classes.dex */
public class f<T> implements e<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object[] f368a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f369b;

    public f(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f368a = new Object[i];
    }

    private boolean c(T t) {
        for (int i = 0; i < this.f369b; i++) {
            if (this.f368a[i] == t) {
                return true;
            }
        }
        return false;
    }

    @Override // a.g.j.e
    public boolean a(T t) {
        if (c(t)) {
            throw new IllegalStateException("Already in the pool!");
        }
        int i = this.f369b;
        Object[] objArr = this.f368a;
        if (i >= objArr.length) {
            return false;
        }
        objArr[i] = t;
        this.f369b = i + 1;
        return true;
    }

    @Override // a.g.j.e
    public T b() {
        int i = this.f369b;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.f368a;
        T t = (T) objArr[i2];
        objArr[i2] = null;
        this.f369b = i - 1;
        return t;
    }
}
