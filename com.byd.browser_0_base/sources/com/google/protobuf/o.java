package com.google.protobuf;

import com.google.protobuf.c0;
import defpackage.C0175b;
import defpackage.l42;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: compiled from: DoubleArrayList.java */
/* JADX INFO: loaded from: classes.dex */
public final class o extends d<Double> implements RandomAccess, l42 {
    public double[] f;
    public int g;

    static {
        new o(0, new double[0]).e = false;
    }

    public o() {
        this(0, new double[10]);
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int i2;
        double dDoubleValue = ((Double) obj).doubleValue();
        a();
        if (i < 0 || i > (i2 = this.g)) {
            StringBuilder sbM = C0175b.m("Index:", i, ", Size:");
            sbM.append(this.g);
            throw new IndexOutOfBoundsException(sbM.toString());
        }
        double[] dArr = this.f;
        if (i2 < dArr.length) {
            System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
        } else {
            double[] dArr2 = new double[((i2 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            System.arraycopy(this.f, i, dArr2, i + 1, this.g - i);
            this.f = dArr2;
        }
        this.f[i] = dDoubleValue;
        this.g++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.protobuf.d, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Double> collection) {
        a();
        Charset charset = c0.a;
        collection.getClass();
        if (!(collection instanceof o)) {
            return super.addAll(collection);
        }
        o oVar = (o) collection;
        int i = oVar.g;
        if (i == 0) {
            return false;
        }
        int i2 = this.g;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        double[] dArr = this.f;
        if (i3 > dArr.length) {
            this.f = Arrays.copyOf(dArr, i3);
        }
        System.arraycopy(oVar.f, 0, this.f, this.g, oVar.g);
        this.g = i3;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void b(double d) {
        a();
        int i = this.g;
        double[] dArr = this.f;
        if (i == dArr.length) {
            double[] dArr2 = new double[((i * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            this.f = dArr2;
        }
        double[] dArr3 = this.f;
        int i2 = this.g;
        this.g = i2 + 1;
        dArr3[i2] = d;
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
        if (!(obj instanceof o)) {
            return super.equals(obj);
        }
        o oVar = (o) obj;
        if (this.g != oVar.g) {
            return false;
        }
        double[] dArr = oVar.f;
        for (int i = 0; i < this.g; i++) {
            if (Double.doubleToLongBits(this.f[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        c(i);
        return Double.valueOf(this.f[i]);
    }

    @Override // com.google.protobuf.c0.e
    public final c0.e h(int i) {
        if (i < this.g) {
            throw new IllegalArgumentException();
        }
        return new o(this.g, Arrays.copyOf(this.f, i));
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iB = 1;
        for (int i = 0; i < this.g; i++) {
            iB = (iB * 31) + c0.b(Double.doubleToLongBits(this.f[i]));
        }
        return iB;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Double)) {
            return -1;
        }
        double dDoubleValue = ((Double) obj).doubleValue();
        int i = this.g;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.f[i2] == dDoubleValue) {
                return i2;
            }
        }
        return -1;
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        a();
        c(i);
        double[] dArr = this.f;
        double d = dArr[i];
        if (i < this.g - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, (r3 - i) - 1);
        }
        this.g--;
        ((AbstractList) this).modCount++;
        return Double.valueOf(d);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        a();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        double[] dArr = this.f;
        System.arraycopy(dArr, i2, dArr, i, this.g - i2);
        this.g -= i2 - i;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        double dDoubleValue = ((Double) obj).doubleValue();
        a();
        c(i);
        double[] dArr = this.f;
        double d = dArr[i];
        dArr[i] = dDoubleValue;
        return Double.valueOf(d);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.g;
    }

    public o(int i, double[] dArr) {
        this.f = dArr;
        this.g = i;
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        b(((Double) obj).doubleValue());
        return true;
    }
}
