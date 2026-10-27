package androidx.core.d;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class d<T> implements c<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object[] f477a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f478b;

    public d(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f477a = new Object[i];
    }

    private boolean c(@NonNull T t) {
        for (int i = 0; i < this.f478b; i++) {
            if (this.f477a[i] == t) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.core.d.c
    public boolean a(@NonNull T t) {
        if (c(t)) {
            throw new IllegalStateException("Already in the pool!");
        }
        int i = this.f478b;
        Object[] objArr = this.f477a;
        if (i >= objArr.length) {
            return false;
        }
        objArr[i] = t;
        this.f478b = i + 1;
        return true;
    }

    @Override // androidx.core.d.c
    public T b() {
        int i = this.f478b;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.f477a;
        T t = (T) objArr[i2];
        objArr[i2] = null;
        this.f478b = i - 1;
        return t;
    }
}
