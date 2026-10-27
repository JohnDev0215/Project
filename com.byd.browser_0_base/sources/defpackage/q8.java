package defpackage;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: ArraySet.jvm.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nArraySet.jvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArraySet.jvm.kt\nandroidx/collection/ArraySet\n+ 2 ArraySet.kt\nandroidx/collection/ArraySetKt\n*L\n1#1,303:1\n304#2,10:304\n317#2,14:314\n334#2:328\n339#2:329\n345#2:330\n350#2:331\n355#2,61:332\n420#2,17:393\n440#2,6:410\n450#2,60:416\n518#2,9:476\n531#2,22:485\n557#2,7:507\n568#2,19:514\n591#2,6:533\n601#2,6:539\n611#2,5:545\n620#2,8:550\n*S KotlinDebug\n*F\n+ 1 ArraySet.jvm.kt\nandroidx/collection/ArraySet\n*L\n101#1:304,10\n111#1:314,14\n121#1:328\n131#1:329\n141#1:330\n148#1:331\n160#1:332,61\n170#1:393,17\n180#1:410,6\n191#1:416,60\n200#1:476,9\n227#1:485,22\n234#1:507,7\n243#1:514,19\n270#1:533,6\n279#1:539,6\n289#1:545,5\n300#1:550,8\n*E\n"})
public final class q8<E> implements Collection<E>, Set<E> {
    public int[] e = a8.k;
    public Object[] f = a8.l;
    public int g;

    /* JADX INFO: compiled from: ArraySet.jvm.kt */
    public final class a extends pz0<E> {
        public a() {
            super(q8.this.g);
        }

        @Override // defpackage.pz0
        public final E b(int i) {
            return (E) q8.this.f[i];
        }

        @Override // defpackage.pz0
        public final void c(int i) {
            q8.this.a(i);
        }
    }

    public q8(int i) {
        if (i > 0) {
            a8.f(this, i);
        }
    }

    public final void a(int i) {
        int i2 = this.g;
        Object[] objArr = this.f;
        Object obj = objArr[i];
        if (i2 <= 1) {
            clear();
            return;
        }
        int i3 = i2 - 1;
        int[] iArr = this.e;
        if (iArr.length <= 8 || i2 >= iArr.length / 3) {
            if (i < i3) {
                int i4 = i + 1;
                int i5 = i3 + 1;
                r8.p0(i, i4, i5, iArr, iArr);
                Object[] objArr2 = this.f;
                r8.q0(i, i4, i5, objArr2, objArr2);
            }
            this.f[i3] = null;
        } else {
            a8.f(this, i2 > 8 ? i2 + (i2 >> 1) : 8);
            if (i > 0) {
                r8.p0(0, 0, i, iArr, this.e);
                r8.r0(objArr, this.f, 0, 0, i, 6);
            }
            if (i < i3) {
                int i6 = i + 1;
                int i7 = i3 + 1;
                r8.p0(i, i6, i7, iArr, this.e);
                r8.q0(i, i6, i7, objArr, this.f);
            }
        }
        if (i2 != this.g) {
            throw new ConcurrentModificationException();
        }
        this.g = i3;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean add(E e) {
        int i;
        int iJ;
        int i2 = this.g;
        if (e == null) {
            iJ = a8.J(this, null, 0);
            i = 0;
        } else {
            int iHashCode = e.hashCode();
            i = iHashCode;
            iJ = a8.J(this, e, iHashCode);
        }
        if (iJ >= 0) {
            return false;
        }
        int i3 = ~iJ;
        int[] iArr = this.e;
        if (i2 >= iArr.length) {
            int i4 = 8;
            if (i2 >= 8) {
                i4 = (i2 >> 1) + i2;
            } else if (i2 < 4) {
                i4 = 4;
            }
            Object[] objArr = this.f;
            a8.f(this, i4);
            if (i2 != this.g) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.e;
            if (!(iArr2.length == 0)) {
                r8.p0(0, 0, iArr.length, iArr, iArr2);
                r8.r0(objArr, this.f, 0, 0, objArr.length, 6);
            }
        }
        if (i3 < i2) {
            int[] iArr3 = this.e;
            int i5 = i3 + 1;
            r8.p0(i5, i3, i2, iArr3, iArr3);
            Object[] objArr2 = this.f;
            r8.q0(i5, i3, i2, objArr2, objArr2);
        }
        int i6 = this.g;
        if (i2 == i6) {
            int[] iArr4 = this.e;
            if (i3 < iArr4.length) {
                iArr4[i3] = i;
                this.f[i3] = e;
                this.g = i6 + 1;
                return true;
            }
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean addAll(Collection<? extends E> collection) {
        i21.e(collection, "elements");
        int size = collection.size() + this.g;
        int i = this.g;
        int[] iArr = this.e;
        boolean zAdd = false;
        if (iArr.length < size) {
            Object[] objArr = this.f;
            a8.f(this, size);
            int i2 = this.g;
            if (i2 > 0) {
                r8.p0(0, 0, i2, iArr, this.e);
                r8.r0(objArr, this.f, 0, 0, this.g, 6);
            }
        }
        if (this.g != i) {
            throw new ConcurrentModificationException();
        }
        Iterator<? extends E> it = collection.iterator();
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    @Override // java.util.Collection, java.util.Set
    public final void clear() {
        if (this.g != 0) {
            this.e = a8.k;
            this.f = a8.l;
            this.g = 0;
        }
        if (this.g != 0) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return (obj == null ? a8.J(this, null, 0) : a8.J(this, obj, obj.hashCode())) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean containsAll(Collection<? extends Object> collection) {
        i21.e(collection, "elements");
        Iterator<? extends Object> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Set) && this.g == ((Set) obj).size()) {
            try {
                int i = this.g;
                for (int i2 = 0; i2 < i; i2++) {
                    if (((Set) obj).contains(this.f[i2])) {
                    }
                }
                return true;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        int[] iArr = this.e;
        int i = this.g;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += iArr[i3];
        }
        return i2;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.g <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator<E> iterator() {
        return new a();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int iJ = obj == null ? a8.J(this, null, 0) : a8.J(this, obj, obj.hashCode());
        if (iJ < 0) {
            return false;
        }
        a(iJ);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean removeAll(Collection<? extends Object> collection) {
        i21.e(collection, "elements");
        Iterator<? extends Object> it = collection.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean retainAll(Collection<? extends Object> collection) {
        i21.e(collection, "elements");
        boolean z = false;
        for (int i = this.g - 1; -1 < i; i--) {
            if (!tu.p0(collection, this.f[i])) {
                a(i);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public final int size() {
        return this.g;
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray() {
        Object[] objArr = this.f;
        int i = this.g;
        i21.e(objArr, "<this>");
        int length = objArr.length;
        if (i <= length) {
            Object[] objArrCopyOfRange = Arrays.copyOfRange(objArr, 0, i);
            i21.d(objArrCopyOfRange, "copyOfRange(this, fromIndex, toIndex)");
            return objArrCopyOfRange;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i + ") is greater than size (" + length + ").");
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.g * 14);
        sb.append('{');
        int i = this.g;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            Object obj = this.f[i2];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        String string = sb.toString();
        i21.d(string, "StringBuilder(capacity).…builderAction).toString()");
        return string;
    }

    @Override // java.util.Collection, java.util.Set
    public final <T> T[] toArray(T[] tArr) {
        i21.e(tArr, "array");
        int i = this.g;
        if (tArr.length < i) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
        } else if (tArr.length > i) {
            tArr[i] = null;
        }
        r8.q0(0, 0, this.g, this.f, tArr);
        return tArr;
    }
}
