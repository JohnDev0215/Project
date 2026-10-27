package p;

import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class vd1 extends a2 implements u51, RandomAccess, dx1 {
    public static final vd1 l;
    public long[] e;
    public int k;

    static {
        vd1 vd1Var = new vd1(new long[0], 0);
        l = vd1Var;
        vd1Var.c = false;
    }

    public vd1(long[] jArr, int i) {
        this.e = jArr;
        this.k = i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int i2;
        long jLongValue = ((Long) obj).longValue();
        a();
        if (i < 0 || i > (i2 = this.k)) {
            StringBuilder sbO = u4.o("Index:", i, ", Size:");
            sbO.append(this.k);
            throw new IndexOutOfBoundsException(sbO.toString());
        }
        long[] jArr = this.e;
        if (i2 < jArr.length) {
            System.arraycopy(jArr, i, jArr, i + 1, i2 - i);
        } else {
            long[] jArr2 = new long[((i2 * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            System.arraycopy(this.e, i, jArr2, i + 1, this.k - i);
            this.e = jArr2;
        }
        this.e[i] = jLongValue;
        this.k++;
        ((AbstractList) this).modCount++;
    }

    @Override // p.a2, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        a();
        Charset charset = w51.a;
        collection.getClass();
        if (!(collection instanceof vd1)) {
            return super.addAll(collection);
        }
        vd1 vd1Var = (vd1) collection;
        int i = vd1Var.k;
        if (i == 0) {
            return false;
        }
        int i2 = this.k;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        long[] jArr = this.e;
        if (i3 > jArr.length) {
            this.e = Arrays.copyOf(jArr, i3);
        }
        System.arraycopy(vd1Var.e, 0, this.e, this.k, vd1Var.k);
        this.k = i3;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void c(long j) {
        a();
        int i = this.k;
        long[] jArr = this.e;
        if (i == jArr.length) {
            long[] jArr2 = new long[((i * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, i);
            this.e = jArr2;
        }
        long[] jArr3 = this.e;
        int i2 = this.k;
        this.k = i2 + 1;
        jArr3[i2] = j;
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
        if (!(obj instanceof vd1)) {
            return super.equals(obj);
        }
        vd1 vd1Var = (vd1) obj;
        if (this.k != vd1Var.k) {
            return false;
        }
        long[] jArr = vd1Var.e;
        for (int i = 0; i < this.k; i++) {
            if (this.e[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // p.v51
    public final v51 f(int i) {
        if (i >= this.k) {
            return new vd1(Arrays.copyOf(this.e, i), this.k);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        d(i);
        return Long.valueOf(this.e[i]);
    }

    @Override // p.a2, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iA = 1;
        for (int i = 0; i < this.k; i++) {
            iA = (iA * 31) + w51.a(this.e[i]);
        }
        return iA;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Long)) {
            return -1;
        }
        long jLongValue = ((Long) obj).longValue();
        int i = this.k;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.e[i2] == jLongValue) {
                return i2;
            }
        }
        return -1;
    }

    @Override // p.a2, java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        a();
        d(i);
        long[] jArr = this.e;
        long j = jArr[i];
        if (i < this.k - 1) {
            System.arraycopy(jArr, i + 1, jArr, i, (r3 - i) - 1);
        }
        this.k--;
        ((AbstractList) this).modCount++;
        return Long.valueOf(j);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        a();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        long[] jArr = this.e;
        System.arraycopy(jArr, i2, jArr, i, this.k - i2);
        this.k -= i2 - i;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        long jLongValue = ((Long) obj).longValue();
        a();
        d(i);
        long[] jArr = this.e;
        long j = jArr[i];
        jArr[i] = jLongValue;
        return Long.valueOf(j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.k;
    }

    @Override // p.a2, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        c(((Long) obj).longValue());
        return true;
    }
}
