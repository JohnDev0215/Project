package b.a;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class b<E> implements Collection<E>, Set<E> {

    @Nullable
    private static Object[] f;
    private static int g;

    @Nullable
    private static Object[] h;
    private static int i;
    private static final Object j = new Object();
    private static final Object k = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int[] f1267c;
    Object[] d;
    int e;

    /* JADX INFO: compiled from: caradapter-bug */
    private class a extends d<E> {
        a() {
            super(b.this.e);
        }

        @Override // b.a.d
        protected E a(int i) {
            return (E) b.this.h(i);
        }

        @Override // b.a.d
        protected void b(int i) {
            b.this.g(i);
        }
    }

    public b() {
        this(0);
    }

    private void a(int i2) {
        if (i2 == 8) {
            synchronized (k) {
                if (h != null) {
                    Object[] objArr = h;
                    try {
                        this.d = objArr;
                        h = (Object[]) objArr[0];
                        int[] iArr = (int[]) objArr[1];
                        this.f1267c = iArr;
                        if (iArr != null) {
                            objArr[1] = null;
                            objArr[0] = null;
                            i--;
                            return;
                        }
                    } catch (ClassCastException unused) {
                    }
                    System.out.println("ArraySet Found corrupt ArraySet cache: [0]=" + objArr[0] + " [1]=" + objArr[1]);
                    h = null;
                    i = 0;
                }
            }
        } else if (i2 == 4) {
            synchronized (j) {
                if (f != null) {
                    Object[] objArr2 = f;
                    try {
                        this.d = objArr2;
                        f = (Object[]) objArr2[0];
                        int[] iArr2 = (int[]) objArr2[1];
                        this.f1267c = iArr2;
                        if (iArr2 != null) {
                            objArr2[1] = null;
                            objArr2[0] = null;
                            g--;
                            return;
                        }
                    } catch (ClassCastException unused2) {
                    }
                    System.out.println("ArraySet Found corrupt ArraySet cache: [0]=" + objArr2[0] + " [1]=" + objArr2[1]);
                    f = null;
                    g = 0;
                }
            }
        }
        this.f1267c = new int[i2];
        this.d = new Object[i2];
    }

    private int b(int i2) {
        try {
            return c.a(this.f1267c, this.e, i2);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    private static void d(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (k) {
                if (i < 10) {
                    objArr[0] = h;
                    objArr[1] = iArr;
                    for (int i3 = i2 - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    h = objArr;
                    i++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (j) {
                if (g < 10) {
                    objArr[0] = f;
                    objArr[1] = iArr;
                    for (int i4 = i2 - 1; i4 >= 2; i4--) {
                        objArr[i4] = null;
                    }
                    f = objArr;
                    g++;
                }
            }
        }
    }

    private int e(Object obj, int i2) {
        int i3 = this.e;
        if (i3 == 0) {
            return -1;
        }
        int iB = b(i2);
        if (iB < 0 || obj.equals(this.d[iB])) {
            return iB;
        }
        int i4 = iB + 1;
        while (i4 < i3 && this.f1267c[i4] == i2) {
            if (obj.equals(this.d[i4])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = iB - 1; i5 >= 0 && this.f1267c[i5] == i2; i5--) {
            if (obj.equals(this.d[i5])) {
                return i5;
            }
        }
        return ~i4;
    }

    private int f() {
        int i2 = this.e;
        if (i2 == 0) {
            return -1;
        }
        int iB = b(0);
        if (iB < 0 || this.d[iB] == null) {
            return iB;
        }
        int i3 = iB + 1;
        while (i3 < i2 && this.f1267c[i3] == 0) {
            if (this.d[i3] == null) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iB - 1; i4 >= 0 && this.f1267c[i4] == 0; i4--) {
            if (this.d[i4] == null) {
                return i4;
            }
        }
        return ~i3;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(@Nullable E e) {
        int i2;
        int iE;
        int i3 = this.e;
        if (e == null) {
            iE = f();
            i2 = 0;
        } else {
            int iHashCode = e.hashCode();
            i2 = iHashCode;
            iE = e(e, iHashCode);
        }
        if (iE >= 0) {
            return false;
        }
        int i4 = ~iE;
        if (i3 >= this.f1267c.length) {
            int i5 = 4;
            if (i3 >= 8) {
                i5 = (i3 >> 1) + i3;
            } else if (i3 >= 4) {
                i5 = 8;
            }
            int[] iArr = this.f1267c;
            Object[] objArr = this.d;
            a(i5);
            if (i3 != this.e) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.f1267c;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.d, 0, objArr.length);
            }
            d(iArr, objArr, i3);
        }
        if (i4 < i3) {
            int[] iArr3 = this.f1267c;
            int i6 = i4 + 1;
            int i7 = i3 - i4;
            System.arraycopy(iArr3, i4, iArr3, i6, i7);
            Object[] objArr2 = this.d;
            System.arraycopy(objArr2, i4, objArr2, i6, i7);
        }
        int i8 = this.e;
        if (i3 == i8) {
            int[] iArr4 = this.f1267c;
            if (i4 < iArr4.length) {
                iArr4[i4] = i2;
                this.d[i4] = e;
                this.e = i8 + 1;
                return true;
            }
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(@NonNull Collection<? extends E> collection) {
        c(this.e + collection.size());
        Iterator<? extends E> it = collection.iterator();
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    public void c(int i2) {
        int i3 = this.e;
        int[] iArr = this.f1267c;
        if (iArr.length < i2) {
            Object[] objArr = this.d;
            a(i2);
            int i4 = this.e;
            if (i4 > 0) {
                System.arraycopy(iArr, 0, this.f1267c, 0, i4);
                System.arraycopy(objArr, 0, this.d, 0, this.e);
            }
            d(iArr, objArr, this.e);
        }
        if (this.e != i3) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int i2 = this.e;
        if (i2 != 0) {
            int[] iArr = this.f1267c;
            Object[] objArr = this.d;
            this.f1267c = c.f1268a;
            this.d = c.f1270c;
            this.e = 0;
            d(iArr, objArr, i2);
        }
        if (this.e != 0) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(@Nullable Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(@NonNull Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (size() != set.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.e; i2++) {
                try {
                    if (!set.contains(h(i2))) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    public E g(int i2) {
        int i3 = this.e;
        E e = (E) this.d[i2];
        if (i3 <= 1) {
            clear();
        } else {
            int i4 = i3 - 1;
            int[] iArr = this.f1267c;
            if (iArr.length <= 8 || i3 >= iArr.length / 3) {
                if (i2 < i4) {
                    int[] iArr2 = this.f1267c;
                    int i5 = i2 + 1;
                    int i6 = i4 - i2;
                    System.arraycopy(iArr2, i5, iArr2, i2, i6);
                    Object[] objArr = this.d;
                    System.arraycopy(objArr, i5, objArr, i2, i6);
                }
                this.d[i4] = null;
            } else {
                int i7 = i3 > 8 ? i3 + (i3 >> 1) : 8;
                int[] iArr3 = this.f1267c;
                Object[] objArr2 = this.d;
                a(i7);
                if (i2 > 0) {
                    System.arraycopy(iArr3, 0, this.f1267c, 0, i2);
                    System.arraycopy(objArr2, 0, this.d, 0, i2);
                }
                if (i2 < i4) {
                    int i8 = i2 + 1;
                    int i9 = i4 - i2;
                    System.arraycopy(iArr3, i8, this.f1267c, i2, i9);
                    System.arraycopy(objArr2, i8, this.d, i2, i9);
                }
            }
            if (i3 != this.e) {
                throw new ConcurrentModificationException();
            }
            this.e = i4;
        }
        return e;
    }

    public E h(int i2) {
        return (E) this.d[i2];
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.f1267c;
        int i2 = this.e;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += iArr[i4];
        }
        return i3;
    }

    public int indexOf(@Nullable Object obj) {
        return obj == null ? f() : e(obj, obj.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.e <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    @NonNull
    public Iterator<E> iterator() {
        return new a();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(@Nullable Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf < 0) {
            return false;
        }
        g(iIndexOf);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(@NonNull Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(@NonNull Collection<?> collection) {
        boolean z = false;
        for (int i2 = this.e - 1; i2 >= 0; i2--) {
            if (!collection.contains(this.d[i2])) {
                g(i2);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.e;
    }

    @Override // java.util.Collection, java.util.Set
    @NonNull
    public Object[] toArray() {
        int i2 = this.e;
        Object[] objArr = new Object[i2];
        System.arraycopy(this.d, 0, objArr, 0, i2);
        return objArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.e * 14);
        sb.append('{');
        for (int i2 = 0; i2 < this.e; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            E eH = h(i2);
            if (eH != this) {
                sb.append(eH);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public b(int i2) {
        if (i2 == 0) {
            this.f1267c = c.f1268a;
            this.d = c.f1270c;
        } else {
            a(i2);
        }
        this.e = 0;
    }

    @Override // java.util.Collection, java.util.Set
    @NonNull
    public <T> T[] toArray(@NonNull T[] tArr) {
        if (tArr.length < this.e) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.e));
        }
        System.arraycopy(this.d, 0, tArr, 0, this.e);
        int length = tArr.length;
        int i2 = this.e;
        if (length > i2) {
            tArr[i2] = null;
        }
        return tArr;
    }
}
