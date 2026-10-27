package p;

import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class km extends a2 implements l51, RandomAccess, dx1 {
    public static final km l;
    public boolean[] e;
    public int k;

    static {
        km kmVar = new km(new boolean[0], 0);
        l = kmVar;
        kmVar.c = false;
    }

    public km(boolean[] zArr, int i) {
        this.e = zArr;
        this.k = i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int i2;
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        a();
        if (i < 0 || i > (i2 = this.k)) {
            StringBuilder sbO = u4.o("Index:", i, ", Size:");
            sbO.append(this.k);
            throw new IndexOutOfBoundsException(sbO.toString());
        }
        boolean[] zArr = this.e;
        if (i2 < zArr.length) {
            System.arraycopy(zArr, i, zArr, i + 1, i2 - i);
        } else {
            boolean[] zArr2 = new boolean[((i2 * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            System.arraycopy(this.e, i, zArr2, i + 1, this.k - i);
            this.e = zArr2;
        }
        this.e[i] = zBooleanValue;
        this.k++;
        ((AbstractList) this).modCount++;
    }

    @Override // p.a2, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        a();
        Charset charset = w51.a;
        collection.getClass();
        if (!(collection instanceof km)) {
            return super.addAll(collection);
        }
        km kmVar = (km) collection;
        int i = kmVar.k;
        if (i == 0) {
            return false;
        }
        int i2 = this.k;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        boolean[] zArr = this.e;
        if (i3 > zArr.length) {
            this.e = Arrays.copyOf(zArr, i3);
        }
        System.arraycopy(kmVar.e, 0, this.e, this.k, kmVar.k);
        this.k = i3;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void c(boolean z) {
        a();
        int i = this.k;
        boolean[] zArr = this.e;
        if (i == zArr.length) {
            boolean[] zArr2 = new boolean[((i * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, i);
            this.e = zArr2;
        }
        boolean[] zArr3 = this.e;
        int i2 = this.k;
        this.k = i2 + 1;
        zArr3[i2] = z;
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
        if (!(obj instanceof km)) {
            return super.equals(obj);
        }
        km kmVar = (km) obj;
        if (this.k != kmVar.k) {
            return false;
        }
        boolean[] zArr = kmVar.e;
        for (int i = 0; i < this.k; i++) {
            if (this.e[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // p.v51
    public final v51 f(int i) {
        if (i >= this.k) {
            return new km(Arrays.copyOf(this.e, i), this.k);
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        d(i);
        return Boolean.valueOf(this.e[i]);
    }

    @Override // p.a2, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.k; i2++) {
            int i3 = i * 31;
            boolean z = this.e[i2];
            Charset charset = w51.a;
            i = i3 + (z ? 1231 : 1237);
        }
        return i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Boolean)) {
            return -1;
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        int i = this.k;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.e[i2] == zBooleanValue) {
                return i2;
            }
        }
        return -1;
    }

    @Override // p.a2, java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        a();
        d(i);
        boolean[] zArr = this.e;
        boolean z = zArr[i];
        if (i < this.k - 1) {
            System.arraycopy(zArr, i + 1, zArr, i, (r2 - i) - 1);
        }
        this.k--;
        ((AbstractList) this).modCount++;
        return Boolean.valueOf(z);
    }

    @Override // java.util.AbstractList
    public final void removeRange(int i, int i2) {
        a();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        boolean[] zArr = this.e;
        System.arraycopy(zArr, i2, zArr, i, this.k - i2);
        this.k -= i2 - i;
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        a();
        d(i);
        boolean[] zArr = this.e;
        boolean z = zArr[i];
        zArr[i] = zBooleanValue;
        return Boolean.valueOf(z);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.k;
    }

    @Override // p.a2, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        c(((Boolean) obj).booleanValue());
        return true;
    }
}
