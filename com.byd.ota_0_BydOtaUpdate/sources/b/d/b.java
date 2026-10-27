package b.d;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArraySet.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<E> implements Collection<E>, Set<E> {
    public static int E3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f1154c = new int[0];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object[] f1155d = new Object[0];
    public static Object[] s;
    public static int x;
    public static Object[] y;
    public int[] F3;
    public Object[] G3;
    public int H3;
    public f<E, E> I3;

    /* JADX INFO: compiled from: ArraySet.java */
    public class a extends f<E, E> {
        public a() {
        }

        @Override // b.d.f
        public void a() {
            b.this.clear();
        }

        @Override // b.d.f
        public Object b(int i2, int i3) {
            return b.this.G3[i2];
        }

        @Override // b.d.f
        public Map<E, E> c() {
            throw new UnsupportedOperationException("not a map");
        }

        @Override // b.d.f
        public int d() {
            return b.this.H3;
        }

        @Override // b.d.f
        public int e(Object obj) {
            return b.this.e(obj);
        }

        @Override // b.d.f
        public int f(Object obj) {
            return b.this.e(obj);
        }

        @Override // b.d.f
        public void g(E e2, E e3) {
            b.this.add(e2);
        }

        @Override // b.d.f
        public void h(int i2) {
            b.this.h(i2);
        }

        @Override // b.d.f
        public E i(int i2, E e2) {
            throw new UnsupportedOperationException("not a map");
        }
    }

    public b() {
        this(0);
    }

    public static void c(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (b.class) {
                if (E3 < 10) {
                    objArr[0] = y;
                    objArr[1] = iArr;
                    for (int i3 = i2 - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    y = objArr;
                    E3++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (b.class) {
                if (x < 10) {
                    objArr[0] = s;
                    objArr[1] = iArr;
                    for (int i4 = i2 - 1; i4 >= 2; i4--) {
                        objArr[i4] = null;
                    }
                    s = objArr;
                    x++;
                }
            }
        }
    }

    public final void a(int i2) {
        if (i2 == 8) {
            synchronized (b.class) {
                Object[] objArr = y;
                if (objArr != null) {
                    this.G3 = objArr;
                    y = (Object[]) objArr[0];
                    this.F3 = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    E3--;
                    return;
                }
            }
        } else if (i2 == 4) {
            synchronized (b.class) {
                Object[] objArr2 = s;
                if (objArr2 != null) {
                    this.G3 = objArr2;
                    s = (Object[]) objArr2[0];
                    this.F3 = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    x--;
                    return;
                }
            }
        }
        this.F3 = new int[i2];
        this.G3 = new Object[i2];
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(E e2) {
        int i2;
        int iF;
        if (e2 == null) {
            iF = g();
            i2 = 0;
        } else {
            int iHashCode = e2.hashCode();
            i2 = iHashCode;
            iF = f(e2, iHashCode);
        }
        if (iF >= 0) {
            return false;
        }
        int i3 = ~iF;
        int i4 = this.H3;
        int[] iArr = this.F3;
        if (i4 >= iArr.length) {
            int i5 = 4;
            if (i4 >= 8) {
                i5 = (i4 >> 1) + i4;
            } else if (i4 >= 4) {
                i5 = 8;
            }
            Object[] objArr = this.G3;
            a(i5);
            int[] iArr2 = this.F3;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.G3, 0, objArr.length);
            }
            c(iArr, objArr, this.H3);
        }
        int i6 = this.H3;
        if (i3 < i6) {
            int[] iArr3 = this.F3;
            int i7 = i3 + 1;
            System.arraycopy(iArr3, i3, iArr3, i7, i6 - i3);
            Object[] objArr2 = this.G3;
            System.arraycopy(objArr2, i3, objArr2, i7, this.H3 - i3);
        }
        this.F3[i3] = i2;
        this.G3[i3] = e2;
        this.H3++;
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> collection) {
        b(this.H3 + collection.size());
        Iterator<? extends E> it = collection.iterator();
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    public void b(int i2) {
        int[] iArr = this.F3;
        if (iArr.length < i2) {
            Object[] objArr = this.G3;
            a(i2);
            int i3 = this.H3;
            if (i3 > 0) {
                System.arraycopy(iArr, 0, this.F3, 0, i3);
                System.arraycopy(objArr, 0, this.G3, 0, this.H3);
            }
            c(iArr, objArr, this.H3);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int i2 = this.H3;
        if (i2 != 0) {
            c(this.F3, this.G3, i2);
            this.F3 = f1154c;
            this.G3 = f1155d;
            this.H3 = 0;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return e(obj) >= 0;
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
        if (this.I3 == null) {
            this.I3 = new a();
        }
        return this.I3;
    }

    public int e(Object obj) {
        return obj == null ? g() : f(obj, obj.hashCode());
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
            for (int i2 = 0; i2 < this.H3; i2++) {
                try {
                    if (!set.contains(i(i2))) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    public final int f(Object obj, int i2) {
        int i3 = this.H3;
        if (i3 == 0) {
            return -1;
        }
        int iA = c.a(this.F3, i3, i2);
        if (iA < 0 || obj.equals(this.G3[iA])) {
            return iA;
        }
        int i4 = iA + 1;
        while (i4 < i3 && this.F3[i4] == i2) {
            if (obj.equals(this.G3[i4])) {
                return i4;
            }
            i4++;
        }
        for (int i5 = iA - 1; i5 >= 0 && this.F3[i5] == i2; i5--) {
            if (obj.equals(this.G3[i5])) {
                return i5;
            }
        }
        return ~i4;
    }

    public final int g() {
        int i2 = this.H3;
        if (i2 == 0) {
            return -1;
        }
        int iA = c.a(this.F3, i2, 0);
        if (iA < 0 || this.G3[iA] == null) {
            return iA;
        }
        int i3 = iA + 1;
        while (i3 < i2 && this.F3[i3] == 0) {
            if (this.G3[i3] == null) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iA - 1; i4 >= 0 && this.F3[i4] == 0; i4--) {
            if (this.G3[i4] == null) {
                return i4;
            }
        }
        return ~i3;
    }

    public E h(int i2) {
        Object[] objArr = this.G3;
        E e2 = (E) objArr[i2];
        int i3 = this.H3;
        if (i3 <= 1) {
            c(this.F3, objArr, i3);
            this.F3 = f1154c;
            this.G3 = f1155d;
            this.H3 = 0;
        } else {
            int[] iArr = this.F3;
            if (iArr.length <= 8 || i3 >= iArr.length / 3) {
                int i4 = i3 - 1;
                this.H3 = i4;
                if (i2 < i4) {
                    int i5 = i2 + 1;
                    System.arraycopy(iArr, i5, iArr, i2, i4 - i2);
                    Object[] objArr2 = this.G3;
                    System.arraycopy(objArr2, i5, objArr2, i2, this.H3 - i2);
                }
                this.G3[this.H3] = null;
            } else {
                a(i3 > 8 ? i3 + (i3 >> 1) : 8);
                this.H3--;
                if (i2 > 0) {
                    System.arraycopy(iArr, 0, this.F3, 0, i2);
                    System.arraycopy(objArr, 0, this.G3, 0, i2);
                }
                int i6 = this.H3;
                if (i2 < i6) {
                    int i7 = i2 + 1;
                    System.arraycopy(iArr, i7, this.F3, i2, i6 - i2);
                    System.arraycopy(objArr, i7, this.G3, i2, this.H3 - i2);
                }
            }
        }
        return e2;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.F3;
        int i2 = this.H3;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += iArr[i4];
        }
        return i3;
    }

    public E i(int i2) {
        return (E) this.G3[i2];
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.H3 <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return d().m().iterator();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        int iE = e(obj);
        if (iE < 0) {
            return false;
        }
        h(iE);
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
        for (int i2 = this.H3 - 1; i2 >= 0; i2--) {
            if (!collection.contains(this.G3[i2])) {
                h(i2);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.H3;
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray() {
        int i2 = this.H3;
        Object[] objArr = new Object[i2];
        System.arraycopy(this.G3, 0, objArr, 0, i2);
        return objArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.H3 * 14);
        sb.append('{');
        for (int i2 = 0; i2 < this.H3; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            E eI = i(i2);
            if (eI != this) {
                sb.append(eI);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public b(int i2) {
        if (i2 == 0) {
            this.F3 = f1154c;
            this.G3 = f1155d;
        } else {
            a(i2);
        }
        this.H3 = 0;
    }

    @Override // java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        if (tArr.length < this.H3) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.H3));
        }
        System.arraycopy(this.G3, 0, tArr, 0, this.H3);
        int length = tArr.length;
        int i2 = this.H3;
        if (length > i2) {
            tArr[i2] = null;
        }
        return tArr;
    }
}
