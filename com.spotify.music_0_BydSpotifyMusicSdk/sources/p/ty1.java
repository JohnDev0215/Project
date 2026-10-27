package p;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class ty1 extends a2 implements RandomAccess {
    public static final ty1 l;
    public Object[] e;
    public int k;

    static {
        ty1 ty1Var = new ty1(0, new Object[0]);
        l = ty1Var;
        ty1Var.c = false;
    }

    public ty1(int i, Object[] objArr) {
        this.e = objArr;
        this.k = i;
    }

    @Override // p.a2, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        a();
        int i = this.k;
        Object[] objArr = this.e;
        if (i == objArr.length) {
            this.e = Arrays.copyOf(objArr, ((i * 3) / 2) + 1);
        }
        Object[] objArr2 = this.e;
        int i2 = this.k;
        this.k = i2 + 1;
        objArr2[i2] = obj;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void c(int i) {
        if (i < 0 || i >= this.k) {
            StringBuilder sbO = u4.o("Index:", i, ", Size:");
            sbO.append(this.k);
            throw new IndexOutOfBoundsException(sbO.toString());
        }
    }

    @Override // p.v51
    public final v51 f(int i) {
        if (i < this.k) {
            throw new IllegalArgumentException();
        }
        return new ty1(this.k, Arrays.copyOf(this.e, i));
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        c(i);
        return this.e[i];
    }

    @Override // p.a2, java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        a();
        c(i);
        Object[] objArr = this.e;
        Object obj = objArr[i];
        if (i < this.k - 1) {
            System.arraycopy(objArr, i + 1, objArr, i, (r2 - i) - 1);
        }
        this.k--;
        ((AbstractList) this).modCount++;
        return obj;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        a();
        c(i);
        Object[] objArr = this.e;
        Object obj2 = objArr[i];
        objArr[i] = obj;
        ((AbstractList) this).modCount++;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.k;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int i2;
        a();
        if (i >= 0 && i <= (i2 = this.k)) {
            Object[] objArr = this.e;
            if (i2 < objArr.length) {
                System.arraycopy(objArr, i, objArr, i + 1, i2 - i);
            } else {
                Object[] objArr2 = new Object[((i2 * 3) / 2) + 1];
                System.arraycopy(objArr, 0, objArr2, 0, i);
                System.arraycopy(this.e, i, objArr2, i + 1, this.k - i);
                this.e = objArr2;
            }
            this.e[i] = obj;
            this.k++;
            ((AbstractList) this).modCount++;
            return;
        }
        StringBuilder sbO = u4.o("Index:", i, ", Size:");
        sbO.append(this.k);
        throw new IndexOutOfBoundsException(sbO.toString());
    }
}
