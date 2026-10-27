package p;

import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class e51 extends a2 implements r51, RandomAccess, dx1 {
    public static final e51 l;
    public int[] e;
    public int k;

    static {
        e51 e51Var = new e51(new int[0], 0);
        l = e51Var;
        e51Var.c = false;
    }

    public e51(int[] iArr, int i) {
        this.e = iArr;
        this.k = i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int i2;
        int iIntValue = ((Integer) obj).intValue();
        a();
        if (i < 0 || i > (i2 = this.k)) {
            StringBuilder sbO = u4.o("Index:", i, ", Size:");
            sbO.append(this.k);
            throw new IndexOutOfBoundsException(sbO.toString());
        }
        int[] iArr = this.e;
        if (i2 < iArr.length) {
            System.arraycopy(iArr, i, iArr, i + 1, i2 - i);
        } else {
            int[] iArr2 = new int[((i2 * 3) / 2) + 1];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            System.arraycopy(this.e, i, iArr2, i + 1, this.k - i);
            this.e = iArr2;
        }
        this.e[i] = iIntValue;
        this.k++;
        ((AbstractList) this).modCount++;
    }

    @Override // p.a2, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        a();
        Charset charset = w51.a;
        collection.getClass();
        if (!(collection instanceof e51)) {
            return super.addAll(collection);
        }
        e51 e51Var = (e51) collection;
        int i = e51Var.k;
        if (i == 0) {
            return false;
        }
        int i2 = this.k;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        int[] iArr = this.e;
        if (i3 > iArr.length) {
            this.e = Arrays.copyOf(iArr, i3);
        }
        System.arraycopy(e51Var.e, 0, this.e, this.k, e51Var.k);
        this.k = i3;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void c(int i) {
        a();
        int i2 = this.k;
        int[] iArr = this.e;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[((i2 * 3) / 2) + 1];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.e = iArr2;
        }
        int[] iArr3 = this.e;
        int i3 = this.k;
        this.k = i3 + 1;
        iArr3[i3] = i;
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

    public final int e(int i) {
        d(i);
        return this.e[i];
    }

    @Override // p.a2, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e51)) {
            return super.equals(obj);
        }
        e51 e51Var = (e51) obj;
        if (this.k != e51Var.k) {
            return false;
        }
        int[] iArr = e51Var.e;
        for (int i = 0; i < this.k; i++) {
            if (this.e[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // p.v51
    public final v51 f(int i) {
        if (i >= this.k) {
            return new e51(Arrays.copyOf(this.e, i), this.k);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        return Integer.valueOf(e(i));
    }

    public final int h(int i, int i2) {
        a();
        d(i);
        int[] iArr = this.e;
        int i3 = iArr[i];
        iArr[i] = i2;
        return i3;
    }

    @Override // p.a2, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.k; i2++) {
            i = (i * 31) + this.e[i2];
        }
        return i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int iIntValue = ((Integer) obj).intValue();
        int i = this.k;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.e[i2] == iIntValue) {
                return i2;
            }
        }
        return -1;
    }

    @Override // p.a2, java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        a();
        d(i);
        int[] iArr = this.e;
        int i2 = iArr[i];
        if (i < this.k - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, (r2 - i) - 1);
        }
        this.k--;
        ((AbstractList) this).modCount++;
        return Integer.valueOf(i2);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        a();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        int[] iArr = this.e;
        System.arraycopy(iArr, i2, iArr, i, this.k - i2);
        this.k -= i2 - i;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        return Integer.valueOf(h(i, ((Integer) obj).intValue()));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.k;
    }

    @Override // p.a2, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        c(((Integer) obj).intValue());
        return true;
    }
}
