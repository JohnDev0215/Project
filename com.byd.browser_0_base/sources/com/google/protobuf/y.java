package com.google.protobuf;

import com.google.protobuf.c0;
import defpackage.C0175b;
import defpackage.l42;
import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: compiled from: FloatArrayList.java */
/* JADX INFO: loaded from: classes.dex */
public final class y extends d<Float> implements RandomAccess, l42 {
    public float[] f;
    public int g;

    static {
        new y(0, new float[0]).e = false;
    }

    public y() {
        this(0, new float[10]);
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int i2;
        float fFloatValue = ((Float) obj).floatValue();
        a();
        if (i < 0 || i > (i2 = this.g)) {
            StringBuilder sbM = C0175b.m("Index:", i, ", Size:");
            sbM.append(this.g);
            throw new IndexOutOfBoundsException(sbM.toString());
        }
        float[] fArr = this.f;
        if (i2 < fArr.length) {
            System.arraycopy(fArr, i, fArr, i + 1, i2 - i);
        } else {
            float[] fArr2 = new float[((i2 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            System.arraycopy(this.f, i, fArr2, i + 1, this.g - i);
            this.f = fArr2;
        }
        this.f[i] = fFloatValue;
        this.g++;
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.protobuf.d, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Float> collection) {
        a();
        Charset charset = c0.a;
        collection.getClass();
        if (!(collection instanceof y)) {
            return super.addAll(collection);
        }
        y yVar = (y) collection;
        int i = yVar.g;
        if (i == 0) {
            return false;
        }
        int i2 = this.g;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        float[] fArr = this.f;
        if (i3 > fArr.length) {
            this.f = Arrays.copyOf(fArr, i3);
        }
        System.arraycopy(yVar.f, 0, this.f, this.g, yVar.g);
        this.g = i3;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void b(float f) {
        a();
        int i = this.g;
        float[] fArr = this.f;
        if (i == fArr.length) {
            float[] fArr2 = new float[((i * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            this.f = fArr2;
        }
        float[] fArr3 = this.f;
        int i2 = this.g;
        this.g = i2 + 1;
        fArr3[i2] = f;
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
        if (!(obj instanceof y)) {
            return super.equals(obj);
        }
        y yVar = (y) obj;
        if (this.g != yVar.g) {
            return false;
        }
        float[] fArr = yVar.f;
        for (int i = 0; i < this.g; i++) {
            if (Float.floatToIntBits(this.f[i]) != Float.floatToIntBits(fArr[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        c(i);
        return Float.valueOf(this.f[i]);
    }

    @Override // com.google.protobuf.c0.e
    public final c0.e h(int i) {
        if (i < this.g) {
            throw new IllegalArgumentException();
        }
        return new y(this.g, Arrays.copyOf(this.f, i));
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iFloatToIntBits = 1;
        for (int i = 0; i < this.g; i++) {
            iFloatToIntBits = (iFloatToIntBits * 31) + Float.floatToIntBits(this.f[i]);
        }
        return iFloatToIntBits;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Float)) {
            return -1;
        }
        float fFloatValue = ((Float) obj).floatValue();
        int i = this.g;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.f[i2] == fFloatValue) {
                return i2;
            }
        }
        return -1;
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        a();
        c(i);
        float[] fArr = this.f;
        float f = fArr[i];
        if (i < this.g - 1) {
            System.arraycopy(fArr, i + 1, fArr, i, (r2 - i) - 1);
        }
        this.g--;
        ((AbstractList) this).modCount++;
        return Float.valueOf(f);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        a();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        float[] fArr = this.f;
        System.arraycopy(fArr, i2, fArr, i, this.g - i2);
        this.g -= i2 - i;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        float fFloatValue = ((Float) obj).floatValue();
        a();
        c(i);
        float[] fArr = this.f;
        float f = fArr[i];
        fArr[i] = fFloatValue;
        return Float.valueOf(f);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.g;
    }

    public y(int i, float[] fArr) {
        this.f = fArr;
        this.g = i;
    }

    @Override // com.google.protobuf.d, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        b(((Float) obj).floatValue());
        return true;
    }
}
