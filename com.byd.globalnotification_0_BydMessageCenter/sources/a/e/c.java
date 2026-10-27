package a.e;

import a.e.g;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class c<E> implements Collection<E>, Set<E> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f450e = new int[0];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Object[] f451f = new Object[0];
    public static Object[] g;
    public static int h;
    public static Object[] i;
    public static int j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f452a = f450e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object[] f453b = f451f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f454c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g<E, E> f455d;

    public static void d(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (c.class) {
                if (j < 10) {
                    objArr[0] = i;
                    objArr[1] = iArr;
                    for (int i3 = i2 - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    i = objArr;
                    j++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (c.class) {
                if (h < 10) {
                    objArr[0] = g;
                    objArr[1] = iArr;
                    for (int i4 = i2 - 1; i4 >= 2; i4--) {
                        objArr[i4] = null;
                    }
                    g = objArr;
                    h++;
                }
            }
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(E e2) {
        int i2;
        int iE;
        if (e2 == null) {
            iE = f();
            i2 = 0;
        } else {
            int iHashCode = e2.hashCode();
            i2 = iHashCode;
            iE = e(e2, iHashCode);
        }
        if (iE >= 0) {
            return false;
        }
        int i3 = ~iE;
        int i4 = this.f454c;
        if (i4 >= this.f452a.length) {
            int i5 = 4;
            if (i4 >= 8) {
                i5 = (i4 >> 1) + i4;
            } else if (i4 >= 4) {
                i5 = 8;
            }
            int[] iArr = this.f452a;
            Object[] objArr = this.f453b;
            c(i5);
            int[] iArr2 = this.f452a;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.f453b, 0, objArr.length);
            }
            d(iArr, objArr, this.f454c);
        }
        int i6 = this.f454c;
        if (i3 < i6) {
            int[] iArr3 = this.f452a;
            int i7 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i7, i6 - i3);
            Object[] objArr2 = this.f453b;
            System.arraycopy(objArr2, i3, objArr2, i7, this.f454c - i3);
        }
        this.f452a[i3] = i2;
        this.f453b[i3] = e2;
        this.f454c++;
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> collection) {
        int size = collection.size() + this.f454c;
        int[] iArr = this.f452a;
        boolean zAdd = false;
        if (iArr.length < size) {
            Object[] objArr = this.f453b;
            c(size);
            int i2 = this.f454c;
            if (i2 > 0) {
                System.arraycopy(iArr, 0, this.f452a, 0, i2);
                System.arraycopy(objArr, 0, this.f453b, 0, this.f454c);
            }
            d(iArr, objArr, this.f454c);
        }
        Iterator<? extends E> it = collection.iterator();
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    public final void c(int i2) {
        if (i2 == 8) {
            synchronized (c.class) {
                if (i != null) {
                    Object[] objArr = i;
                    this.f453b = objArr;
                    i = (Object[]) objArr[0];
                    this.f452a = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    j--;
                    return;
                }
            }
        } else if (i2 == 4) {
            synchronized (c.class) {
                if (g != null) {
                    Object[] objArr2 = g;
                    this.f453b = objArr2;
                    g = (Object[]) objArr2[0];
                    this.f452a = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    h--;
                    return;
                }
            }
        }
        this.f452a = new int[i2];
        this.f453b = new Object[i2];
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int i2 = this.f454c;
        if (i2 != 0) {
            d(this.f452a, this.f453b, i2);
            this.f452a = f450e;
            this.f453b = f451f;
            this.f454c = 0;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final int e(Object obj, int i2) {
        int i3 = this.f454c;
        if (i3 == 0) {
            return -1;
        }
        int iA = d.a(this.f452a, i3, i2);
        if (iA < 0 || obj.equals(this.f453b[iA])) {
            return iA;
        }
        int i4 = iA + 1;
        while (i4 < i3 && this.f452a[i4] == i2) {
            if (obj.equals(this.f453b[i4])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = iA - 1; i5 >= 0 && this.f452a[i5] == i2; i5--) {
            if (obj.equals(this.f453b[i5])) {
                return i5;
            }
        }
        return ~i4;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (this.f454c != set.size()) {
                return false;
            }
            for (int i2 = 0; i2 < this.f454c; i2++) {
                try {
                    if (!set.contains(this.f453b[i2])) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    public final int f() {
        int i2 = this.f454c;
        if (i2 == 0) {
            return -1;
        }
        int iA = d.a(this.f452a, i2, 0);
        if (iA < 0 || this.f453b[iA] == null) {
            return iA;
        }
        int i3 = iA + 1;
        while (i3 < i2 && this.f452a[i3] == 0) {
            if (this.f453b[i3] == null) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iA - 1; i4 >= 0 && this.f452a[i4] == 0; i4--) {
            if (this.f453b[i4] == null) {
                return i4;
            }
        }
        return ~i3;
    }

    public E g(int i2) {
        Object[] objArr = this.f453b;
        E e2 = (E) objArr[i2];
        int i3 = this.f454c;
        if (i3 <= 1) {
            d(this.f452a, objArr, i3);
            this.f452a = f450e;
            this.f453b = f451f;
            this.f454c = 0;
        } else {
            int[] iArr = this.f452a;
            if (iArr.length <= 8 || i3 >= iArr.length / 3) {
                int i4 = this.f454c - 1;
                this.f454c = i4;
                if (i2 < i4) {
                    int[] iArr2 = this.f452a;
                    int i5 = i2 + 1;
                    System.arraycopy(iArr2, i5, iArr2, i2, i4 - i2);
                    Object[] objArr2 = this.f453b;
                    System.arraycopy(objArr2, i5, objArr2, i2, this.f454c - i2);
                }
                this.f453b[this.f454c] = null;
            } else {
                int i6 = i3 > 8 ? i3 + (i3 >> 1) : 8;
                int[] iArr3 = this.f452a;
                Object[] objArr3 = this.f453b;
                c(i6);
                this.f454c--;
                if (i2 > 0) {
                    System.arraycopy(iArr3, 0, this.f452a, 0, i2);
                    System.arraycopy(objArr3, 0, this.f453b, 0, i2);
                }
                int i7 = this.f454c;
                if (i2 < i7) {
                    int i8 = i2 + 1;
                    System.arraycopy(iArr3, i8, this.f452a, i2, i7 - i2);
                    System.arraycopy(objArr3, i8, this.f453b, i2, this.f454c - i2);
                }
            }
        }
        return e2;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.f452a;
        int i2 = this.f454c;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += iArr[i4];
        }
        return i3;
    }

    public int indexOf(Object obj) {
        return obj == null ? f() : e(obj, obj.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.f454c <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        if (this.f455d == null) {
            this.f455d = new b(this);
        }
        g<E, E> gVar = this.f455d;
        if (gVar.f471b == null) {
            gVar.f471b = new g.c();
        }
        return gVar.f471b.iterator();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf < 0) {
            return false;
        }
        g(iIndexOf);
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean removeAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection<?> collection) {
        boolean z = false;
        for (int i2 = this.f454c - 1; i2 >= 0; i2--) {
            if (!collection.contains(this.f453b[i2])) {
                g(i2);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.f454c;
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray() {
        int i2 = this.f454c;
        Object[] objArr = new Object[i2];
        System.arraycopy(this.f453b, 0, objArr, 0, i2);
        return objArr;
    }

    @Override // java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        if (tArr.length < this.f454c) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.f454c));
        }
        System.arraycopy(this.f453b, 0, tArr, 0, this.f454c);
        int length = tArr.length;
        int i2 = this.f454c;
        if (length > i2) {
            tArr[i2] = null;
        }
        return tArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f454c * 14);
        sb.append('{');
        for (int i2 = 0; i2 < this.f454c; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            Object obj = this.f453b[i2];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
