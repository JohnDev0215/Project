package com.google.protobuf;

import com.google.protobuf.c0;
import defpackage.C0175b;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;

/* JADX INFO: compiled from: ProtobufArrayList.java */
/* JADX INFO: loaded from: classes.dex */
public final class y0<E> extends d<E> implements RandomAccess {
    public static final y0<Object> h;
    public E[] f;
    public int g;

    static {
        y0<Object> y0Var = new y0<>(0, new Object[0]);
        h = y0Var;
        y0Var.e = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public y0(int i, Object[] objArr) {
        this.f = objArr;
        this.g = i;
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(E e) {
        a();
        int i = this.g;
        E[] eArr = this.f;
        if (i == eArr.length) {
            this.f = (E[]) Arrays.copyOf(eArr, ((i * 3) / 2) + 1);
        }
        E[] eArr2 = this.f;
        int i2 = this.g;
        this.g = i2 + 1;
        eArr2[i2] = e;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void b(int i) {
        if (i < 0 || i >= this.g) {
            StringBuilder sbM = C0175b.m("Index:", i, ", Size:");
            sbM.append(this.g);
            throw new IndexOutOfBoundsException(sbM.toString());
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final E get(int i) {
        b(i);
        return this.f[i];
    }

    @Override // com.google.protobuf.c0.e
    public final c0.e h(int i) {
        if (i < this.g) {
            throw new IllegalArgumentException();
        }
        return new y0(this.g, Arrays.copyOf(this.f, i));
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.List
    public final E remove(int i) {
        a();
        b(i);
        E[] eArr = this.f;
        E e = eArr[i];
        if (i < this.g - 1) {
            System.arraycopy(eArr, i + 1, eArr, i, (r2 - i) - 1);
        }
        this.g--;
        ((AbstractList) this).modCount++;
        return e;
    }

    @Override // java.util.AbstractList, java.util.List
    public final E set(int i, E e) {
        a();
        b(i);
        E[] eArr = this.f;
        E e2 = eArr[i];
        eArr[i] = e;
        ((AbstractList) this).modCount++;
        return e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.g;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, E e) {
        int i2;
        a();
        if (i >= 0 && i <= (i2 = this.g)) {
            E[] eArr = this.f;
            if (i2 < eArr.length) {
                System.arraycopy(eArr, i, eArr, i + 1, i2 - i);
            } else {
                E[] eArr2 = (E[]) new Object[((i2 * 3) / 2) + 1];
                System.arraycopy(eArr, 0, eArr2, 0, i);
                System.arraycopy(this.f, i, eArr2, i + 1, this.g - i);
                this.f = eArr2;
            }
            this.f[i] = e;
            this.g++;
            ((AbstractList) this).modCount++;
            return;
        }
        StringBuilder sbM = C0175b.m("Index:", i, ", Size:");
        sbM.append(this.g);
        throw new IndexOutOfBoundsException(sbM.toString());
    }
}
