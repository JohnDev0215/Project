package v;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArraySet.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<E> implements Collection<E>, Set<E> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f8300d = new int[0];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object[] f8301e = new Object[0];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Object[] f8302f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static int f8303g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static Object[] f8304h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static int f8305i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int[] f8306j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Object[] f8307k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f8308l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public f<E, E> f8309m;

    /* JADX INFO: compiled from: ArraySet.java */
    public class a extends f<E, E> {
        public a() {
        }

        @Override // v.f
        public void a() {
            b.this.clear();
        }

        @Override // v.f
        public Object b(int i8, int i9) {
            return b.this.f8307k[i8];
        }

        @Override // v.f
        public Map<E, E> c() {
            throw new UnsupportedOperationException("not a map");
        }

        @Override // v.f
        public int d() {
            return b.this.f8308l;
        }

        @Override // v.f
        public int e(Object obj) {
            return b.this.indexOf(obj);
        }

        @Override // v.f
        public int f(Object obj) {
            return b.this.indexOf(obj);
        }

        @Override // v.f
        public void g(E e8, E e9) {
            b.this.add(e8);
        }

        @Override // v.f
        public void h(int i8) {
            b.this.g(i8);
        }

        @Override // v.f
        public E i(int i8, E e8) {
            throw new UnsupportedOperationException("not a map");
        }
    }

    public b() {
        this(0);
    }

    public static void c(int[] iArr, Object[] objArr, int i8) {
        if (iArr.length == 8) {
            synchronized (b.class) {
                if (f8305i < 10) {
                    objArr[0] = f8304h;
                    objArr[1] = iArr;
                    for (int i9 = i8 - 1; i9 >= 2; i9--) {
                        objArr[i9] = null;
                    }
                    f8304h = objArr;
                    f8305i++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (b.class) {
                if (f8303g < 10) {
                    objArr[0] = f8302f;
                    objArr[1] = iArr;
                    for (int i10 = i8 - 1; i10 >= 2; i10--) {
                        objArr[i10] = null;
                    }
                    f8302f = objArr;
                    f8303g++;
                }
            }
        }
    }

    public final void a(int i8) {
        if (i8 == 8) {
            synchronized (b.class) {
                Object[] objArr = f8304h;
                if (objArr != null) {
                    this.f8307k = objArr;
                    f8304h = (Object[]) objArr[0];
                    this.f8306j = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f8305i--;
                    return;
                }
            }
        } else if (i8 == 4) {
            synchronized (b.class) {
                Object[] objArr2 = f8302f;
                if (objArr2 != null) {
                    this.f8307k = objArr2;
                    f8302f = (Object[]) objArr2[0];
                    this.f8306j = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f8303g--;
                    return;
                }
            }
        }
        this.f8306j = new int[i8];
        this.f8307k = new Object[i8];
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(E e8) {
        int i8;
        int iE;
        if (e8 == null) {
            iE = f();
            i8 = 0;
        } else {
            int iHashCode = e8.hashCode();
            i8 = iHashCode;
            iE = e(e8, iHashCode);
        }
        if (iE >= 0) {
            return false;
        }
        int i9 = ~iE;
        int i10 = this.f8308l;
        int[] iArr = this.f8306j;
        if (i10 >= iArr.length) {
            int i11 = 4;
            if (i10 >= 8) {
                i11 = (i10 >> 1) + i10;
            } else if (i10 >= 4) {
                i11 = 8;
            }
            Object[] objArr = this.f8307k;
            a(i11);
            int[] iArr2 = this.f8306j;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.f8307k, 0, objArr.length);
            }
            c(iArr, objArr, this.f8308l);
        }
        int i12 = this.f8308l;
        if (i9 < i12) {
            int[] iArr3 = this.f8306j;
            int i13 = i9 + 1;
            System.arraycopy(iArr3, i9, iArr3, i13, i12 - i9);
            Object[] objArr2 = this.f8307k;
            System.arraycopy(objArr2, i9, objArr2, i13, this.f8308l - i9);
        }
        this.f8306j[i9] = i8;
        this.f8307k[i9] = e8;
        this.f8308l++;
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> collection) {
        b(this.f8308l + collection.size());
        Iterator<? extends E> it = collection.iterator();
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    public void b(int i8) {
        int[] iArr = this.f8306j;
        if (iArr.length < i8) {
            Object[] objArr = this.f8307k;
            a(i8);
            int i9 = this.f8308l;
            if (i9 > 0) {
                System.arraycopy(iArr, 0, this.f8306j, 0, i9);
                System.arraycopy(objArr, 0, this.f8307k, 0, this.f8308l);
            }
            c(iArr, objArr, this.f8308l);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int i8 = this.f8308l;
        if (i8 != 0) {
            c(this.f8306j, this.f8307k, i8);
            this.f8306j = f8300d;
            this.f8307k = f8301e;
            this.f8308l = 0;
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

    public final f<E, E> d() {
        if (this.f8309m == null) {
            this.f8309m = new a();
        }
        return this.f8309m;
    }

    public final int e(Object obj, int i8) {
        int i9 = this.f8308l;
        if (i9 == 0) {
            return -1;
        }
        int iA = c.a(this.f8306j, i9, i8);
        if (iA < 0 || obj.equals(this.f8307k[iA])) {
            return iA;
        }
        int i10 = iA + 1;
        while (i10 < i9 && this.f8306j[i10] == i8) {
            if (obj.equals(this.f8307k[i10])) {
                return i10;
            }
            i10++;
        }
        for (int i11 = iA - 1; i11 >= 0 && this.f8306j[i11] == i8; i11--) {
            if (obj.equals(this.f8307k[i11])) {
                return i11;
            }
        }
        return ~i10;
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
            for (int i8 = 0; i8 < this.f8308l; i8++) {
                try {
                    if (!set.contains(h(i8))) {
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
        int i8 = this.f8308l;
        if (i8 == 0) {
            return -1;
        }
        int iA = c.a(this.f8306j, i8, 0);
        if (iA < 0 || this.f8307k[iA] == null) {
            return iA;
        }
        int i9 = iA + 1;
        while (i9 < i8 && this.f8306j[i9] == 0) {
            if (this.f8307k[i9] == null) {
                return i9;
            }
            i9++;
        }
        for (int i10 = iA - 1; i10 >= 0 && this.f8306j[i10] == 0; i10--) {
            if (this.f8307k[i10] == null) {
                return i10;
            }
        }
        return ~i9;
    }

    public E g(int i8) {
        Object[] objArr = this.f8307k;
        E e8 = (E) objArr[i8];
        int i9 = this.f8308l;
        if (i9 <= 1) {
            c(this.f8306j, objArr, i9);
            this.f8306j = f8300d;
            this.f8307k = f8301e;
            this.f8308l = 0;
        } else {
            int[] iArr = this.f8306j;
            if (iArr.length <= 8 || i9 >= iArr.length / 3) {
                int i10 = i9 - 1;
                this.f8308l = i10;
                if (i8 < i10) {
                    int i11 = i8 + 1;
                    System.arraycopy(iArr, i11, iArr, i8, i10 - i8);
                    Object[] objArr2 = this.f8307k;
                    System.arraycopy(objArr2, i11, objArr2, i8, this.f8308l - i8);
                }
                this.f8307k[this.f8308l] = null;
            } else {
                a(i9 > 8 ? i9 + (i9 >> 1) : 8);
                this.f8308l--;
                if (i8 > 0) {
                    System.arraycopy(iArr, 0, this.f8306j, 0, i8);
                    System.arraycopy(objArr, 0, this.f8307k, 0, i8);
                }
                int i12 = this.f8308l;
                if (i8 < i12) {
                    int i13 = i8 + 1;
                    System.arraycopy(iArr, i13, this.f8306j, i8, i12 - i8);
                    System.arraycopy(objArr, i13, this.f8307k, i8, this.f8308l - i8);
                }
            }
        }
        return e8;
    }

    public E h(int i8) {
        return (E) this.f8307k[i8];
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.f8306j;
        int i8 = this.f8308l;
        int i9 = 0;
        for (int i10 = 0; i10 < i8; i10++) {
            i9 += iArr[i10];
        }
        return i9;
    }

    public int indexOf(Object obj) {
        return obj == null ? f() : e(obj, obj.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.f8308l <= 0;
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
        boolean z7 = false;
        for (int i8 = this.f8308l - 1; i8 >= 0; i8--) {
            if (!collection.contains(this.f8307k[i8])) {
                g(i8);
                z7 = true;
            }
        }
        return z7;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.f8308l;
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray() {
        int i8 = this.f8308l;
        Object[] objArr = new Object[i8];
        System.arraycopy(this.f8307k, 0, objArr, 0, i8);
        return objArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f8308l * 14);
        sb.append('{');
        for (int i8 = 0; i8 < this.f8308l; i8++) {
            if (i8 > 0) {
                sb.append(", ");
            }
            E eH = h(i8);
            if (eH != this) {
                sb.append(eH);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public b(int i8) {
        if (i8 == 0) {
            this.f8306j = f8300d;
            this.f8307k = f8301e;
        } else {
            a(i8);
        }
        this.f8308l = 0;
    }

    @Override // java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        if (tArr.length < this.f8308l) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.f8308l));
        }
        System.arraycopy(this.f8307k, 0, tArr, 0, this.f8308l);
        int length = tArr.length;
        int i8 = this.f8308l;
        if (length > i8) {
            tArr[i8] = null;
        }
        return tArr;
    }
}
