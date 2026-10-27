package com.google.protobuf;

import com.google.protobuf.c0;
import defpackage.C0175b;
import defpackage.l42;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: compiled from: IntArrayList.java */
/* JADX INFO: loaded from: classes.dex */
public final class b0 extends d<Integer> implements c0.c, RandomAccess, l42 {
    public static final b0 h;
    public int[] f;
    public int g;

    static {
        b0 b0Var = new b0(0, new int[0]);
        h = b0Var;
        b0Var.e = false;
    }

    public b0() {
        this(0, new int[10]);
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int i2;
        int iIntValue = ((Integer) obj).intValue();
        a();
        if (i < 0 || i > (i2 = this.g)) {
            StringBuilder sbM = C0175b.m("Index:", i, ", Size:");
            sbM.append(this.g);
            throw new IndexOutOfBoundsException(sbM.toString());
        }
        int[] iArr = this.f;
        if (i2 < iArr.length) {
            System.arraycopy(iArr, i, iArr, i + 1, i2 - i);
        } else {
            int[] iArr2 = new int[((i2 * 3) / 2) + 1];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            System.arraycopy(this.f, i, iArr2, i + 1, this.g - i);
            this.f = iArr2;
        }
        this.f[i] = iIntValue;
        this.g++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.protobuf.d, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Integer> collection) {
        a();
        Charset charset = c0.a;
        collection.getClass();
        if (!(collection instanceof b0)) {
            return super.addAll(collection);
        }
        b0 b0Var = (b0) collection;
        int i = b0Var.g;
        if (i == 0) {
            return false;
        }
        int i2 = this.g;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        int[] iArr = this.f;
        if (i3 > iArr.length) {
            this.f = Arrays.copyOf(iArr, i3);
        }
        System.arraycopy(b0Var.f, 0, this.f, this.g, b0Var.g);
        this.g = i3;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void b(int i) {
        a();
        int i2 = this.g;
        int[] iArr = this.f;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[((i2 * 3) / 2) + 1];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.f = iArr2;
        }
        int[] iArr3 = this.f;
        int i3 = this.g;
        this.g = i3 + 1;
        iArr3[i3] = i;
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

    public final int d(int i) {
        c(i);
        return this.f[i];
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b0)) {
            return super.equals(obj);
        }
        b0 b0Var = (b0) obj;
        if (this.g != b0Var.g) {
            return false;
        }
        int[] iArr = b0Var.f;
        for (int i = 0; i < this.g; i++) {
            if (this.f[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        return Integer.valueOf(d(i));
    }

    @Override // com.google.protobuf.c0.e
    public final c0.e<Integer> h(int i) {
        if (i < this.g) {
            throw new IllegalArgumentException();
        }
        return new b0(this.g, Arrays.copyOf(this.f, i));
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.g; i2++) {
            i = (i * 31) + this.f[i2];
        }
        return i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int iIntValue = ((Integer) obj).intValue();
        int i = this.g;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.f[i2] == iIntValue) {
                return i2;
            }
        }
        return -1;
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        a();
        c(i);
        int[] iArr = this.f;
        int i2 = iArr[i];
        if (i < this.g - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, (r2 - i) - 1);
        }
        this.g--;
        ((AbstractList) this).modCount++;
        return Integer.valueOf(i2);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        a();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        int[] iArr = this.f;
        System.arraycopy(iArr, i2, iArr, i, this.g - i2);
        this.g -= i2 - i;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        int iIntValue = ((Integer) obj).intValue();
        a();
        c(i);
        int[] iArr = this.f;
        int i2 = iArr[i];
        iArr[i] = iIntValue;
        return Integer.valueOf(i2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.g;
    }

    public b0(int i, int[] iArr) {
        this.f = iArr;
        this.g = i;
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        b(((Integer) obj).intValue());
        return true;
    }
}
