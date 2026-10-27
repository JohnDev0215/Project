package n;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArraySet.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<E> implements Collection<E>, Set<E> {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final int[] f8437j = new int[0];

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final Object[] f8438k = new Object[0];

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static Object[] f8439l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static int f8440m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static Object[] f8441n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static int f8442o;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int[] f8443e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    Object[] f8444g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f8445h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private f<E, E> f8446i;

    /* JADX INFO: compiled from: ArraySet.java */
    class a extends f<E, E> {
        a() {
        }

        @Override // n.f
        protected void a() {
            b.this.clear();
        }

        @Override // n.f
        protected Object b(int i7, int i8) {
            return b.this.f8444g[i7];
        }

        @Override // n.f
        protected Map<E, E> c() {
            throw new UnsupportedOperationException("not a map");
        }

        @Override // n.f
        protected int d() {
            return b.this.f8445h;
        }

        @Override // n.f
        protected int e(Object obj) {
            return b.this.indexOf(obj);
        }

        @Override // n.f
        protected int f(Object obj) {
            return b.this.indexOf(obj);
        }

        @Override // n.f
        protected void g(E e7, E e8) {
            b.this.add(e7);
        }

        @Override // n.f
        protected void h(int i7) {
            b.this.i(i7);
        }

        @Override // n.f
        protected E i(int i7, E e7) {
            throw new UnsupportedOperationException("not a map");
        }
    }

    public b() {
        this(0);
    }

    private void c(int i7) {
        if (i7 == 8) {
            synchronized (b.class) {
                Object[] objArr = f8441n;
                if (objArr != null) {
                    this.f8444g = objArr;
                    f8441n = (Object[]) objArr[0];
                    this.f8443e = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f8442o--;
                    return;
                }
            }
        } else if (i7 == 4) {
            synchronized (b.class) {
                Object[] objArr2 = f8439l;
                if (objArr2 != null) {
                    this.f8444g = objArr2;
                    f8439l = (Object[]) objArr2[0];
                    this.f8443e = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f8440m--;
                    return;
                }
            }
        }
        this.f8443e = new int[i7];
        this.f8444g = new Object[i7];
    }

    private static void e(int[] iArr, Object[] objArr, int i7) {
        if (iArr.length == 8) {
            synchronized (b.class) {
                if (f8442o < 10) {
                    objArr[0] = f8441n;
                    objArr[1] = iArr;
                    for (int i8 = i7 - 1; i8 >= 2; i8--) {
                        objArr[i8] = null;
                    }
                    f8441n = objArr;
                    f8442o++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (b.class) {
                if (f8440m < 10) {
                    objArr[0] = f8439l;
                    objArr[1] = iArr;
                    for (int i9 = i7 - 1; i9 >= 2; i9--) {
                        objArr[i9] = null;
                    }
                    f8439l = objArr;
                    f8440m++;
                }
            }
        }
    }

    private f<E, E> f() {
        if (this.f8446i == null) {
            this.f8446i = new a();
        }
        return this.f8446i;
    }

    private int g(Object obj, int i7) {
        int i8 = this.f8445h;
        if (i8 == 0) {
            return -1;
        }
        int iA = c.a(this.f8443e, i8, i7);
        if (iA < 0 || obj.equals(this.f8444g[iA])) {
            return iA;
        }
        int i9 = iA + 1;
        while (i9 < i8 && this.f8443e[i9] == i7) {
            if (obj.equals(this.f8444g[i9])) {
                return i9;
            }
            i9++;
        }
        for (int i10 = iA - 1; i10 >= 0 && this.f8443e[i10] == i7; i10--) {
            if (obj.equals(this.f8444g[i10])) {
                return i10;
            }
        }
        return ~i9;
    }

    private int h() {
        int i7 = this.f8445h;
        if (i7 == 0) {
            return -1;
        }
        int iA = c.a(this.f8443e, i7, 0);
        if (iA < 0 || this.f8444g[iA] == null) {
            return iA;
        }
        int i8 = iA + 1;
        while (i8 < i7 && this.f8443e[i8] == 0) {
            if (this.f8444g[i8] == null) {
                return i8;
            }
            i8++;
        }
        for (int i9 = iA - 1; i9 >= 0 && this.f8443e[i9] == 0; i9--) {
            if (this.f8444g[i9] == null) {
                return i9;
            }
        }
        return ~i8;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(E e7) {
        int i7;
        int iG;
        if (e7 == null) {
            iG = h();
            i7 = 0;
        } else {
            int iHashCode = e7.hashCode();
            i7 = iHashCode;
            iG = g(e7, iHashCode);
        }
        if (iG >= 0) {
            return false;
        }
        int i8 = ~iG;
        int i9 = this.f8445h;
        int[] iArr = this.f8443e;
        if (i9 >= iArr.length) {
            int i10 = 4;
            if (i9 >= 8) {
                i10 = (i9 >> 1) + i9;
            } else if (i9 >= 4) {
                i10 = 8;
            }
            Object[] objArr = this.f8444g;
            c(i10);
            int[] iArr2 = this.f8443e;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.f8444g, 0, objArr.length);
            }
            e(iArr, objArr, this.f8445h);
        }
        int i11 = this.f8445h;
        if (i8 < i11) {
            int[] iArr3 = this.f8443e;
            int i12 = i8 + 1;
            System.arraycopy(iArr3, i8, iArr3, i12, i11 - i8);
            Object[] objArr2 = this.f8444g;
            System.arraycopy(objArr2, i8, objArr2, i12, this.f8445h - i8);
        }
        this.f8443e[i8] = i7;
        this.f8444g[i8] = e7;
        this.f8445h++;
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> collection) {
        d(this.f8445h + collection.size());
        Iterator<? extends E> it = collection.iterator();
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int i7 = this.f8445h;
        if (i7 != 0) {
            e(this.f8443e, this.f8444g, i7);
            this.f8443e = f8437j;
            this.f8444g = f8438k;
            this.f8445h = 0;
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

    public void d(int i7) {
        int[] iArr = this.f8443e;
        if (iArr.length < i7) {
            Object[] objArr = this.f8444g;
            c(i7);
            int i8 = this.f8445h;
            if (i8 > 0) {
                System.arraycopy(iArr, 0, this.f8443e, 0, i8);
                System.arraycopy(objArr, 0, this.f8444g, 0, this.f8445h);
            }
            e(iArr, objArr, this.f8445h);
        }
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
            for (int i7 = 0; i7 < this.f8445h; i7++) {
                try {
                    if (!set.contains(j(i7))) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.f8443e;
        int i7 = this.f8445h;
        int i8 = 0;
        for (int i9 = 0; i9 < i7; i9++) {
            i8 += iArr[i9];
        }
        return i8;
    }

    public E i(int i7) {
        Object[] objArr = this.f8444g;
        E e7 = (E) objArr[i7];
        int i8 = this.f8445h;
        if (i8 <= 1) {
            e(this.f8443e, objArr, i8);
            this.f8443e = f8437j;
            this.f8444g = f8438k;
            this.f8445h = 0;
        } else {
            int[] iArr = this.f8443e;
            if (iArr.length <= 8 || i8 >= iArr.length / 3) {
                int i9 = i8 - 1;
                this.f8445h = i9;
                if (i7 < i9) {
                    int i10 = i7 + 1;
                    System.arraycopy(iArr, i10, iArr, i7, i9 - i7);
                    Object[] objArr2 = this.f8444g;
                    System.arraycopy(objArr2, i10, objArr2, i7, this.f8445h - i7);
                }
                this.f8444g[this.f8445h] = null;
            } else {
                c(i8 > 8 ? i8 + (i8 >> 1) : 8);
                this.f8445h--;
                if (i7 > 0) {
                    System.arraycopy(iArr, 0, this.f8443e, 0, i7);
                    System.arraycopy(objArr, 0, this.f8444g, 0, i7);
                }
                int i11 = this.f8445h;
                if (i7 < i11) {
                    int i12 = i7 + 1;
                    System.arraycopy(iArr, i12, this.f8443e, i7, i11 - i7);
                    System.arraycopy(objArr, i12, this.f8444g, i7, this.f8445h - i7);
                }
            }
        }
        return e7;
    }

    public int indexOf(Object obj) {
        return obj == null ? h() : g(obj, obj.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.f8445h <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return f().m().iterator();
    }

    public E j(int i7) {
        return (E) this.f8444g[i7];
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf < 0) {
            return false;
        }
        i(iIndexOf);
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
        boolean z6 = false;
        for (int i7 = this.f8445h - 1; i7 >= 0; i7--) {
            if (!collection.contains(this.f8444g[i7])) {
                i(i7);
                z6 = true;
            }
        }
        return z6;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.f8445h;
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray() {
        int i7 = this.f8445h;
        Object[] objArr = new Object[i7];
        System.arraycopy(this.f8444g, 0, objArr, 0, i7);
        return objArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f8445h * 14);
        sb.append('{');
        for (int i7 = 0; i7 < this.f8445h; i7++) {
            if (i7 > 0) {
                sb.append(", ");
            }
            E eJ = j(i7);
            if (eJ != this) {
                sb.append(eJ);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public b(int i7) {
        if (i7 == 0) {
            this.f8443e = f8437j;
            this.f8444g = f8438k;
        } else {
            c(i7);
        }
        this.f8445h = 0;
    }

    @Override // java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        if (tArr.length < this.f8445h) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.f8445h));
        }
        System.arraycopy(this.f8444g, 0, tArr, 0, this.f8445h);
        int length = tArr.length;
        int i7 = this.f8445h;
        if (length > i7) {
            tArr[i7] = null;
        }
        return tArr;
    }
}
