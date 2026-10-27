package p;

import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class cv0 extends a2 implements q51, RandomAccess, dx1 {
    public static final cv0 l;
    public float[] e;
    public int k;

    static {
        cv0 cv0Var = new cv0(0, new float[0]);
        l = cv0Var;
        cv0Var.c = false;
    }

    public cv0(int i, float[] fArr) {
        this.e = fArr;
        this.k = i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int i2;
        float fFloatValue = ((Float) obj).floatValue();
        a();
        if (i < 0 || i > (i2 = this.k)) {
            StringBuilder sbO = u4.o("Index:", i, ", Size:");
            sbO.append(this.k);
            throw new IndexOutOfBoundsException(sbO.toString());
        }
        float[] fArr = this.e;
        if (i2 < fArr.length) {
            System.arraycopy(fArr, i, fArr, i + 1, i2 - i);
        } else {
            float[] fArr2 = new float[((i2 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            System.arraycopy(this.e, i, fArr2, i + 1, this.k - i);
            this.e = fArr2;
        }
        this.e[i] = fFloatValue;
        this.k++;
        ((AbstractList) this).modCount++;
    }

    @Override // p.a2, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        a();
        Charset charset = w51.a;
        collection.getClass();
        if (!(collection instanceof cv0)) {
            return super.addAll(collection);
        }
        cv0 cv0Var = (cv0) collection;
        int i = cv0Var.k;
        if (i == 0) {
            return false;
        }
        int i2 = this.k;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        float[] fArr = this.e;
        if (i3 > fArr.length) {
            this.e = Arrays.copyOf(fArr, i3);
        }
        System.arraycopy(cv0Var.e, 0, this.e, this.k, cv0Var.k);
        this.k = i3;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void c(float f) {
        a();
        int i = this.k;
        float[] fArr = this.e;
        if (i == fArr.length) {
            float[] fArr2 = new float[((i * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            this.e = fArr2;
        }
        float[] fArr3 = this.e;
        int i2 = this.k;
        this.k = i2 + 1;
        fArr3[i2] = f;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final void d(int i) {
        if (i < 0 || i >= this.k) {
            StringBuilder sbO = u4.o("Index:", i, ", Size:");
            sbO.append(this.k);
            throw new IndexOutOfBoundsException(sbO.toString());
        }
    }

    @Override // p.a2, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof cv0)) {
            return super.equals(obj);
        }
        cv0 cv0Var = (cv0) obj;
        if (this.k != cv0Var.k) {
            return false;
        }
        float[] fArr = cv0Var.e;
        for (int i = 0; i < this.k; i++) {
            if (Float.floatToIntBits(this.e[i]) != Float.floatToIntBits(fArr[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // p.v51
    public final v51 f(int i) {
        if (i < this.k) {
            throw new IllegalArgumentException();
        }
        return new cv0(this.k, Arrays.copyOf(this.e, i));
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        d(i);
        return Float.valueOf(this.e[i]);
    }

    @Override // p.a2, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iFloatToIntBits = 1;
        for (int i = 0; i < this.k; i++) {
            iFloatToIntBits = (iFloatToIntBits * 31) + Float.floatToIntBits(this.e[i]);
        }
        return iFloatToIntBits;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Float)) {
            return -1;
        }
        float fFloatValue = ((Float) obj).floatValue();
        int i = this.k;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.e[i2] == fFloatValue) {
                return i2;
            }
        }
        return -1;
    }

    @Override // p.a2, java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        a();
        d(i);
        float[] fArr = this.e;
        float f = fArr[i];
        if (i < this.k - 1) {
            System.arraycopy(fArr, i + 1, fArr, i, (r2 - i) - 1);
        }
        this.k--;
        ((AbstractList) this).modCount++;
        return Float.valueOf(f);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        a();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        float[] fArr = this.e;
        System.arraycopy(fArr, i2, fArr, i, this.k - i2);
        this.k -= i2 - i;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        float fFloatValue = ((Float) obj).floatValue();
        a();
        d(i);
        float[] fArr = this.e;
        float f = fArr[i];
        fArr[i] = fFloatValue;
        return Float.valueOf(f);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.k;
    }

    @Override // p.a2, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        c(((Float) obj).floatValue());
        return true;
    }
}
