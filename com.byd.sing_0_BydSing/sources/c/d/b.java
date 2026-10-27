package c.d;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArraySet.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<E> implements Collection<E>, Set<E> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f2095b = new int[0];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Object[] f2096c = new Object[0];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Object[] f2097d;
    private static int e;
    private static Object[] f;
    private static int g;
    private int[] h;
    Object[] i;
    int j;
    private f<E, E> k;

    /* JADX INFO: compiled from: ArraySet.java */
    class a extends f<E, E> {
        a() {
        }

        @Override // c.d.f
        protected void a() {
            b.this.clear();
        }

        @Override // c.d.f
        protected Object b(int i, int i2) {
            return b.this.i[i];
        }

        @Override // c.d.f
        protected Map<E, E> c() {
            throw new UnsupportedOperationException("not a map");
        }

        @Override // c.d.f
        protected int d() {
            return b.this.j;
        }

        @Override // c.d.f
        protected int e(Object obj) {
            return b.this.indexOf(obj);
        }

        @Override // c.d.f
        protected int f(Object obj) {
            return b.this.indexOf(obj);
        }

        @Override // c.d.f
        protected void g(E e, E e2) {
            b.this.add(e);
        }

        @Override // c.d.f
        protected void h(int i) {
            b.this.g(i);
        }

        @Override // c.d.f
        protected E i(int i, E e) {
            throw new UnsupportedOperationException("not a map");
        }
    }

    public b() {
        this(0);
    }

    private void a(int i) {
        if (i == 8) {
            synchronized (b.class) {
                Object[] objArr = f;
                if (objArr != null) {
                    this.i = objArr;
                    f = (Object[]) objArr[0];
                    this.h = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    g--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (b.class) {
                Object[] objArr2 = f2097d;
                if (objArr2 != null) {
                    this.i = objArr2;
                    f2097d = (Object[]) objArr2[0];
                    this.h = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    e--;
                    return;
                }
            }
        }
        this.h = new int[i];
        this.i = new Object[i];
    }

    private static void c(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (b.class) {
                if (g < 10) {
                    objArr[0] = f;
                    objArr[1] = iArr;
                    for (int i2 = i - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    f = objArr;
                    g++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (b.class) {
                if (e < 10) {
                    objArr[0] = f2097d;
                    objArr[1] = iArr;
                    for (int i3 = i - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    f2097d = objArr;
                    e++;
                }
            }
        }
    }

    private f<E, E> d() {
        if (this.k == null) {
            this.k = new a();
        }
        return this.k;
    }

    private int e(Object obj, int i) {
        int i2 = this.j;
        if (i2 == 0) {
            return -1;
        }
        int iA = c.a(this.h, i2, i);
        if (iA < 0 || obj.equals(this.i[iA])) {
            return iA;
        }
        int i3 = iA + 1;
        while (i3 < i2 && this.h[i3] == i) {
            if (obj.equals(this.i[i3])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iA - 1; i4 >= 0 && this.h[i4] == i; i4--) {
            if (obj.equals(this.i[i4])) {
                return i4;
            }
        }
        return ~i3;
    }

    private int f() {
        int i = this.j;
        if (i == 0) {
            return -1;
        }
        int iA = c.a(this.h, i, 0);
        if (iA < 0 || this.i[iA] == null) {
            return iA;
        }
        int i2 = iA + 1;
        while (i2 < i && this.h[i2] == 0) {
            if (this.i[i2] == null) {
                return i2;
            }
            i2++;
        }
        for (int i3 = iA - 1; i3 >= 0 && this.h[i3] == 0; i3--) {
            if (this.i[i3] == null) {
                return i3;
            }
        }
        return ~i2;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(E e2) {
        int i;
        int iE;
        if (e2 == null) {
            iE = f();
            i = 0;
        } else {
            int iHashCode = e2.hashCode();
            i = iHashCode;
            iE = e(e2, iHashCode);
        }
        if (iE >= 0) {
            return false;
        }
        int i2 = ~iE;
        int i3 = this.j;
        int[] iArr = this.h;
        if (i3 >= iArr.length) {
            int i4 = 4;
            if (i3 >= 8) {
                i4 = (i3 >> 1) + i3;
            } else if (i3 >= 4) {
                i4 = 8;
            }
            Object[] objArr = this.i;
            a(i4);
            int[] iArr2 = this.h;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.i, 0, objArr.length);
            }
            c(iArr, objArr, this.j);
        }
        int i5 = this.j;
        if (i2 < i5) {
            int[] iArr3 = this.h;
            int i6 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i6, i5 - i2);
            Object[] objArr2 = this.i;
            System.arraycopy(objArr2, i2, objArr2, i6, this.j - i2);
        }
        this.h[i2] = i;
        this.i[i2] = e2;
        this.j++;
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> collection) {
        b(this.j + collection.size());
        Iterator<? extends E> it = collection.iterator();
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    public void b(int i) {
        int[] iArr = this.h;
        if (iArr.length < i) {
            Object[] objArr = this.i;
            a(i);
            int i2 = this.j;
            if (i2 > 0) {
                System.arraycopy(iArr, 0, this.h, 0, i2);
                System.arraycopy(objArr, 0, this.i, 0, this.j);
            }
            c(iArr, objArr, this.j);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int i = this.j;
        if (i != 0) {
            c(this.h, this.i, i);
            this.h = f2095b;
            this.i = f2096c;
            this.j = 0;
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
            for (int i = 0; i < this.j; i++) {
                try {
                    if (!set.contains(h(i))) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    public E g(int i) {
        Object[] objArr = this.i;
        E e2 = (E) objArr[i];
        int i2 = this.j;
        if (i2 <= 1) {
            c(this.h, objArr, i2);
            this.h = f2095b;
            this.i = f2096c;
            this.j = 0;
        } else {
            int[] iArr = this.h;
            if (iArr.length <= 8 || i2 >= iArr.length / 3) {
                int i3 = i2 - 1;
                this.j = i3;
                if (i < i3) {
                    int i4 = i + 1;
                    System.arraycopy(iArr, i4, iArr, i, i3 - i);
                    Object[] objArr2 = this.i;
                    System.arraycopy(objArr2, i4, objArr2, i, this.j - i);
                }
                this.i[this.j] = null;
            } else {
                a(i2 > 8 ? i2 + (i2 >> 1) : 8);
                this.j--;
                if (i > 0) {
                    System.arraycopy(iArr, 0, this.h, 0, i);
                    System.arraycopy(objArr, 0, this.i, 0, i);
                }
                int i5 = this.j;
                if (i < i5) {
                    int i6 = i + 1;
                    System.arraycopy(iArr, i6, this.h, i, i5 - i);
                    System.arraycopy(objArr, i6, this.i, i, this.j - i);
                }
            }
        }
        return e2;
    }

    public E h(int i) {
        return (E) this.i[i];
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.h;
        int i = this.j;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += iArr[i3];
        }
        return i2;
    }

    public int indexOf(Object obj) {
        return obj == null ? f() : e(obj, obj.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.j <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return d().m().iterator();
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
        for (int i = this.j - 1; i >= 0; i--) {
            if (!collection.contains(this.i[i])) {
                g(i);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.j;
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray() {
        int i = this.j;
        Object[] objArr = new Object[i];
        System.arraycopy(this.i, 0, objArr, 0, i);
        return objArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.j * 14);
        sb.append('{');
        for (int i = 0; i < this.j; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            E eH = h(i);
            if (eH != this) {
                sb.append(eH);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public b(int i) {
        if (i == 0) {
            this.h = f2095b;
            this.i = f2096c;
        } else {
            a(i);
        }
        this.j = 0;
    }

    @Override // java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        if (tArr.length < this.j) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.j));
        }
        System.arraycopy(this.i, 0, tArr, 0, this.j);
        int length = tArr.length;
        int i = this.j;
        if (length > i) {
            tArr[i] = null;
        }
        return tArr;
    }
}
