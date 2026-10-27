package defpackage;

import com.google.common.collect.b;
import java.util.Objects;

/* JADX INFO: compiled from: RegularImmutableList.java */
/* JADX INFO: loaded from: classes.dex */
public final class ja2<E> extends b<E> {
    public static final ja2 i = new ja2(0, new Object[0]);
    public final transient Object[] g;
    public final transient int h;

    public ja2(int i2, Object[] objArr) {
        this.g = objArr;
        this.h = i2;
    }

    @Override // com.google.common.collect.b, com.google.common.collect.a
    public final int a(int i2, Object[] objArr) {
        Object[] objArr2 = this.g;
        int i3 = this.h;
        System.arraycopy(objArr2, 0, objArr, i2, i3);
        return i2 + i3;
    }

    @Override // com.google.common.collect.a
    public final Object[] b() {
        return this.g;
    }

    @Override // com.google.common.collect.a
    public final int c() {
        return this.h;
    }

    @Override // com.google.common.collect.a
    public final int d() {
        return 0;
    }

    @Override // java.util.List
    public final E get(int i2) {
        E e = (E) this.g[i2];
        Objects.requireNonNull(e);
        return e;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.h;
    }
}
