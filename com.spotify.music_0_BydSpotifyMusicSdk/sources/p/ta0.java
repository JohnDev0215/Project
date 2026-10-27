package p;

import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class ta0 extends a2 implements m51, RandomAccess, dx1 {
    public static final ta0 l;
    public double[] e;
    public int k;

    static {
        ta0 ta0Var = new ta0(new double[0], 0);
        l = ta0Var;
        ta0Var.c = false;
    }

    public ta0(double[] dArr, int i) {
        this.e = dArr;
        this.k = i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int i2;
        double dDoubleValue = ((Double) obj).doubleValue();
        a();
        if (i < 0 || i > (i2 = this.k)) {
            StringBuilder sbO = u4.o("Index:", i, ", Size:");
            sbO.append(this.k);
            throw new IndexOutOfBoundsException(sbO.toString());
        }
        double[] dArr = this.e;
        if (i2 < dArr.length) {
            System.arraycopy(dArr, i, dArr, i + 1, i2 - i);
        } else {
            double[] dArr2 = new double[((i2 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            System.arraycopy(this.e, i, dArr2, i + 1, this.k - i);
            this.e = dArr2;
        }
        this.e[i] = dDoubleValue;
        this.k++;
        ((AbstractList) this).modCount++;
    }

    @Override // p.a2, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        a();
        Charset charset = w51.a;
        collection.getClass();
        if (!(collection instanceof ta0)) {
            return super.addAll(collection);
        }
        ta0 ta0Var = (ta0) collection;
        int i = ta0Var.k;
        if (i == 0) {
            return false;
        }
        int i2 = this.k;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        double[] dArr = this.e;
        if (i3 > dArr.length) {
            this.e = Arrays.copyOf(dArr, i3);
        }
        System.arraycopy(ta0Var.e, 0, this.e, this.k, ta0Var.k);
        this.k = i3;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void c(double d) {
        a();
        int i = this.k;
        double[] dArr = this.e;
        if (i == dArr.length) {
            double[] dArr2 = new double[((i * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            this.e = dArr2;
        }
        double[] dArr3 = this.e;
        int i2 = this.k;
        this.k = i2 + 1;
        dArr3[i2] = d;
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
        if (!(obj instanceof ta0)) {
            return super.equals(obj);
        }
        ta0 ta0Var = (ta0) obj;
        if (this.k != ta0Var.k) {
            return false;
        }
        double[] dArr = ta0Var.e;
        for (int i = 0; i < this.k; i++) {
            if (Double.doubleToLongBits(this.e[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // p.v51
    public final v51 f(int i) {
        if (i >= this.k) {
            return new ta0(Arrays.copyOf(this.e, i), this.k);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        d(i);
        return Double.valueOf(this.e[i]);
    }

    @Override // p.a2, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iA = 1;
        for (int i = 0; i < this.k; i++) {
            iA = (iA * 31) + w51.a(Double.doubleToLongBits(this.e[i]));
        }
        return iA;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Double)) {
            return -1;
        }
        double dDoubleValue = ((Double) obj).doubleValue();
        int i = this.k;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.e[i2] == dDoubleValue) {
                return i2;
            }
        }
        return -1;
    }

    @Override // p.a2, java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        a();
        d(i);
        double[] dArr = this.e;
        double d = dArr[i];
        if (i < this.k - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, (r3 - i) - 1);
        }
        this.k--;
        ((AbstractList) this).modCount++;
        return Double.valueOf(d);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        a();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        double[] dArr = this.e;
        System.arraycopy(dArr, i2, dArr, i, this.k - i2);
        this.k -= i2 - i;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        double dDoubleValue = ((Double) obj).doubleValue();
        a();
        d(i);
        double[] dArr = this.e;
        double d = dArr[i];
        dArr[i] = dDoubleValue;
        return Double.valueOf(d);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.k;
    }

    @Override // p.a2, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        c(((Double) obj).doubleValue());
        return true;
    }
}
