package androidx.datastore.preferences.protobuf;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class W extends AbstractC0939b implements RandomAccess {
    public static final W o;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object[] f11819m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f11820n;

    static {
        W w10 = new W(0, new Object[0]);
        o = w10;
        w10.l = false;
    }

    public W(int i7, Object[] objArr) {
        this.f11819m = objArr;
        this.f11820n = i7;
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0939b, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        a();
        int i7 = this.f11820n;
        Object[] objArr = this.f11819m;
        if (i7 == objArr.length) {
            this.f11819m = Arrays.copyOf(objArr, ((i7 * 3) / 2) + 1);
        }
        Object[] objArr2 = this.f11819m;
        int i10 = this.f11820n;
        this.f11820n = i10 + 1;
        objArr2[i10] = obj;
        ((AbstractList) this).modCount++;
        return true;
    }

    public final void d(int i7) {
        if (i7 < 0 || i7 >= this.f11820n) {
            StringBuilder sbJ = M5.b.j("Index:", i7, ", Size:");
            sbJ.append(this.f11820n);
            throw new IndexOutOfBoundsException(sbJ.toString());
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i7) {
        d(i7);
        return this.f11819m[i7];
    }

    @Override // androidx.datastore.preferences.protobuf.InterfaceC0958v
    public final InterfaceC0958v h(int i7) {
        if (i7 < this.f11820n) {
            throw new IllegalArgumentException();
        }
        return new W(this.f11820n, Arrays.copyOf(this.f11819m, i7));
    }

    @Override // androidx.datastore.preferences.protobuf.AbstractC0939b, java.util.AbstractList, java.util.List
    public final Object remove(int i7) {
        a();
        d(i7);
        Object[] objArr = this.f11819m;
        Object obj = objArr[i7];
        if (i7 < this.f11820n - 1) {
            System.arraycopy(objArr, i7 + 1, objArr, i7, (r2 - i7) - 1);
        }
        this.f11820n--;
        ((AbstractList) this).modCount++;
        return obj;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i7, Object obj) {
        a();
        d(i7);
        Object[] objArr = this.f11819m;
        Object obj2 = objArr[i7];
        objArr[i7] = obj;
        ((AbstractList) this).modCount++;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f11820n;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i7, Object obj) {
        int i10;
        a();
        if (i7 >= 0 && i7 <= (i10 = this.f11820n)) {
            Object[] objArr = this.f11819m;
            if (i10 < objArr.length) {
                System.arraycopy(objArr, i7, objArr, i7 + 1, i10 - i7);
            } else {
                Object[] objArr2 = new Object[((i10 * 3) / 2) + 1];
                System.arraycopy(objArr, 0, objArr2, 0, i7);
                System.arraycopy(this.f11819m, i7, objArr2, i7 + 1, this.f11820n - i7);
                this.f11819m = objArr2;
            }
            this.f11819m[i7] = obj;
            this.f11820n++;
            ((AbstractList) this).modCount++;
            return;
        }
        StringBuilder sbJ = M5.b.j("Index:", i7, ", Size:");
        sbJ.append(this.f11820n);
        throw new IndexOutOfBoundsException(sbJ.toString());
    }
}
