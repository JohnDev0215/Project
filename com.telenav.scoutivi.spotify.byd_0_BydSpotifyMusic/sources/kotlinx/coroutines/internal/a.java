package kotlinx.coroutines.internal;

import java.util.Objects;

/* JADX INFO: compiled from: ArrayQueue.kt */
/* JADX INFO: loaded from: classes.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Object[] f7616a = new Object[16];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f7617b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f7618c;

    private final void b() {
        Object[] objArr = this.f7616a;
        int length = objArr.length;
        Object[] objArr2 = new Object[length << 1];
        h5.k.f(objArr, objArr2, 0, this.f7617b, 0, 10, null);
        Object[] objArr3 = this.f7616a;
        int length2 = objArr3.length;
        int i7 = this.f7617b;
        h5.k.f(objArr3, objArr2, length2 - i7, 0, i7, 4, null);
        this.f7616a = objArr2;
        this.f7617b = 0;
        this.f7618c = length;
    }

    public final void a(T t6) {
        Object[] objArr = this.f7616a;
        int i7 = this.f7618c;
        objArr[i7] = t6;
        int length = (objArr.length - 1) & (i7 + 1);
        this.f7618c = length;
        if (length == this.f7617b) {
            b();
        }
    }

    public final boolean c() {
        return this.f7617b == this.f7618c;
    }

    public final T d() {
        int i7 = this.f7617b;
        if (i7 == this.f7618c) {
            return null;
        }
        Object[] objArr = this.f7616a;
        T t6 = (T) objArr[i7];
        objArr[i7] = null;
        this.f7617b = (i7 + 1) & (objArr.length - 1);
        Objects.requireNonNull(t6, "null cannot be cast to non-null type T");
        return t6;
    }
}
