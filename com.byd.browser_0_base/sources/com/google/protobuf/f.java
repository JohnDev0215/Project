package com.google.protobuf;

import com.google.protobuf.c0;
import defpackage.C0175b;
import defpackage.l42;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: compiled from: BooleanArrayList.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends d<Boolean> implements RandomAccess, l42 {
    public boolean[] f;
    public int g;

    static {
        new f(new boolean[0], 0).e = false;
    }

    public f() {
        this(new boolean[10], 0);
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int i2;
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        a();
        if (i < 0 || i > (i2 = this.g)) {
            StringBuilder sbM = C0175b.m("Index:", i, ", Size:");
            sbM.append(this.g);
            throw new IndexOutOfBoundsException(sbM.toString());
        }
        boolean[] zArr = this.f;
        if (i2 < zArr.length) {
            System.arraycopy(zArr, i, zArr, i + 1, i2 - i);
        } else {
            boolean[] zArr2 = new boolean[((i2 * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            System.arraycopy(this.f, i, zArr2, i + 1, this.g - i);
            this.f = zArr2;
        }
        this.f[i] = zBooleanValue;
        this.g++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.protobuf.d, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Boolean> collection) {
        a();
        Charset charset = c0.a;
        collection.getClass();
        if (!(collection instanceof f)) {
            return super.addAll(collection);
        }
        f fVar = (f) collection;
        int i = fVar.g;
        if (i == 0) {
            return false;
        }
        int i2 = this.g;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        boolean[] zArr = this.f;
        if (i3 > zArr.length) {
            this.f = Arrays.copyOf(zArr, i3);
        }
        System.arraycopy(fVar.f, 0, this.f, this.g, fVar.g);
        this.g = i3;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void b(boolean z) {
        a();
        int i = this.g;
        boolean[] zArr = this.f;
        if (i == zArr.length) {
            boolean[] zArr2 = new boolean[((i * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            this.f = zArr2;
        }
        boolean[] zArr3 = this.f;
        int i2 = this.g;
        this.g = i2 + 1;
        zArr3[i2] = z;
    }

    public final void c(int i) {
        if (i < 0 || i >= this.g) {
            StringBuilder sbM = C0175b.m("Index:", i, ", Size:");
            sbM.append(this.g);
            throw new IndexOutOfBoundsException(sbM.toString());
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return super.equals(obj);
        }
        f fVar = (f) obj;
        if (this.g != fVar.g) {
            return false;
        }
        boolean[] zArr = fVar.f;
        for (int i = 0; i < this.g; i++) {
            if (this.f[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        c(i);
        return Boolean.valueOf(this.f[i]);
    }

    @Override // com.google.protobuf.c0.e
    public final c0.e h(int i) {
        if (i >= this.g) {
            return new f(Arrays.copyOf(this.f, i), this.g);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iA = 1;
        for (int i = 0; i < this.g; i++) {
            iA = (iA * 31) + c0.a(this.f[i]);
        }
        return iA;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Boolean)) {
            return -1;
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        int i = this.g;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.f[i2] == zBooleanValue) {
                return i2;
            }
        }
        return -1;
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        a();
        c(i);
        boolean[] zArr = this.f;
        boolean z = zArr[i];
        if (i < this.g - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, (r2 - i) - 1);
        }
        this.g--;
        ((AbstractList) this).modCount++;
        return Boolean.valueOf(z);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        a();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        boolean[] zArr = this.f;
        System.arraycopy(zArr, i2, zArr, i, this.g - i2);
        this.g -= i2 - i;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        a();
        c(i);
        boolean[] zArr = this.f;
        boolean z = zArr[i];
        zArr[i] = zBooleanValue;
        return Boolean.valueOf(z);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.g;
    }

    public f(boolean[] zArr, int i) {
        this.f = zArr;
        this.g = i;
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        b(((Boolean) obj).booleanValue());
        return true;
    }
}
