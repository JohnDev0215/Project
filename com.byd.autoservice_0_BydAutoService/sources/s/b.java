package s;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: ArraySet.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<E> implements Collection<E>, Set<E> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f4842b = new int[0];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object[] f4843c = new Object[0];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Object[] f4844d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f4845e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Object[] f4846f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static int f4847g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f4848h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object[] f4849i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4850j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public f<E, E> f4851k;

    /* JADX INFO: compiled from: ArraySet.java */
    public class a extends f<E, E> {
        public a() {
        }

        @Override // s.f
        public void a() {
            b.this.clear();
        }

        @Override // s.f
        public Object b(int i3, int i4) {
            return b.this.f4849i[i3];
        }

        @Override // s.f
        public Map<E, E> c() {
            throw new UnsupportedOperationException("not a map");
        }

        @Override // s.f
        public int d() {
            return b.this.f4850j;
        }

        @Override // s.f
        public int e(Object obj) {
            return b.this.e(obj);
        }

        @Override // s.f
        public int f(Object obj) {
            return b.this.e(obj);
        }

        @Override // s.f
        public void g(E e3, E e4) {
            b.this.add(e3);
        }

        @Override // s.f
        public void h(int i3) {
            b.this.h(i3);
        }

        @Override // s.f
        public E i(int i3, E e3) {
            throw new UnsupportedOperationException("not a map");
        }
    }

    public b() {
        this(0);
    }

    public static void c(int[] iArr, Object[] objArr, int i3) {
        if (iArr.length == 8) {
            synchronized (b.class) {
                if (f4847g < 10) {
                    objArr[0] = f4846f;
                    objArr[1] = iArr;
                    for (int i4 = i3 - 1; i4 >= 2; i4--) {
                        objArr[i4] = null;
                    }
                    f4846f = objArr;
                    f4847g++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (b.class) {
                if (f4845e < 10) {
                    objArr[0] = f4844d;
                    objArr[1] = iArr;
                    for (int i5 = i3 - 1; i5 >= 2; i5--) {
                        objArr[i5] = null;
                    }
                    f4844d = objArr;
                    f4845e++;
                }
            }
        }
    }

    public final void a(int i3) {
        if (i3 == 8) {
            synchronized (b.class) {
                Object[] objArr = f4846f;
                if (objArr != null) {
                    this.f4849i = objArr;
                    f4846f = (Object[]) objArr[0];
                    this.f4848h = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f4847g--;
                    return;
                }
            }
        } else if (i3 == 4) {
            synchronized (b.class) {
                Object[] objArr2 = f4844d;
                if (objArr2 != null) {
                    this.f4849i = objArr2;
                    f4844d = (Object[]) objArr2[0];
                    this.f4848h = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f4845e--;
                    return;
                }
            }
        }
        this.f4848h = new int[i3];
        this.f4849i = new Object[i3];
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(E e3) {
        int i3;
        int iF;
        if (e3 == null) {
            iF = g();
            i3 = 0;
        } else {
            int iHashCode = e3.hashCode();
            i3 = iHashCode;
            iF = f(e3, iHashCode);
        }
        if (iF >= 0) {
            return false;
        }
        int i4 = ~iF;
        int i5 = this.f4850j;
        int[] iArr = this.f4848h;
        if (i5 >= iArr.length) {
            int i6 = 4;
            if (i5 >= 8) {
                i6 = (i5 >> 1) + i5;
            } else if (i5 >= 4) {
                i6 = 8;
            }
            Object[] objArr = this.f4849i;
            a(i6);
            int[] iArr2 = this.f4848h;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.f4849i, 0, objArr.length);
            }
            c(iArr, objArr, this.f4850j);
        }
        int i7 = this.f4850j;
        if (i4 < i7) {
            int[] iArr3 = this.f4848h;
            int i8 = i4 + 1;
            System.arraycopy(iArr3, i4, iArr3, i8, i7 - i4);
            Object[] objArr2 = this.f4849i;
            System.arraycopy(objArr2, i4, objArr2, i8, this.f4850j - i4);
        }
        this.f4848h[i4] = i3;
        this.f4849i[i4] = e3;
        this.f4850j++;
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> collection) {
        b(this.f4850j + collection.size());
        Iterator<? extends E> it = collection.iterator();
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    public void b(int i3) {
        int[] iArr = this.f4848h;
        if (iArr.length < i3) {
            Object[] objArr = this.f4849i;
            a(i3);
            int i4 = this.f4850j;
            if (i4 > 0) {
                System.arraycopy(iArr, 0, this.f4848h, 0, i4);
                System.arraycopy(objArr, 0, this.f4849i, 0, this.f4850j);
            }
            c(iArr, objArr, this.f4850j);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int i3 = this.f4850j;
        if (i3 != 0) {
            c(this.f4848h, this.f4849i, i3);
            this.f4848h = f4842b;
            this.f4849i = f4843c;
            this.f4850j = 0;
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
        if (this.f4851k == null) {
            this.f4851k = new a();
        }
        return this.f4851k;
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
            for (int i3 = 0; i3 < this.f4850j; i3++) {
                try {
                    if (!set.contains(i(i3))) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    public final int f(Object obj, int i3) {
        int i4 = this.f4850j;
        if (i4 == 0) {
            return -1;
        }
        int iA = c.a(this.f4848h, i4, i3);
        if (iA < 0 || obj.equals(this.f4849i[iA])) {
            return iA;
        }
        int i5 = iA + 1;
        while (i5 < i4 && this.f4848h[i5] == i3) {
            if (obj.equals(this.f4849i[i5])) {
                return i5;
            }
            i5++;
        }
        for (int i6 = iA - 1; i6 >= 0 && this.f4848h[i6] == i3; i6--) {
            if (obj.equals(this.f4849i[i6])) {
                return i6;
            }
        }
        return ~i5;
    }

    public final int g() {
        int i3 = this.f4850j;
        if (i3 == 0) {
            return -1;
        }
        int iA = c.a(this.f4848h, i3, 0);
        if (iA < 0 || this.f4849i[iA] == null) {
            return iA;
        }
        int i4 = iA + 1;
        while (i4 < i3 && this.f4848h[i4] == 0) {
            if (this.f4849i[i4] == null) {
                return i4;
            }
            i4++;
        }
        for (int i5 = iA - 1; i5 >= 0 && this.f4848h[i5] == 0; i5--) {
            if (this.f4849i[i5] == null) {
                return i5;
            }
        }
        return ~i4;
    }

    public E h(int i3) {
        Object[] objArr = this.f4849i;
        E e3 = (E) objArr[i3];
        int i4 = this.f4850j;
        if (i4 <= 1) {
            c(this.f4848h, objArr, i4);
            this.f4848h = f4842b;
            this.f4849i = f4843c;
            this.f4850j = 0;
        } else {
            int[] iArr = this.f4848h;
            if (iArr.length <= 8 || i4 >= iArr.length / 3) {
                int i5 = i4 - 1;
                this.f4850j = i5;
                if (i3 < i5) {
                    int i6 = i3 + 1;
                    System.arraycopy(iArr, i6, iArr, i3, i5 - i3);
                    Object[] objArr2 = this.f4849i;
                    System.arraycopy(objArr2, i6, objArr2, i3, this.f4850j - i3);
                }
                this.f4849i[this.f4850j] = null;
            } else {
                a(i4 > 8 ? i4 + (i4 >> 1) : 8);
                this.f4850j--;
                if (i3 > 0) {
                    System.arraycopy(iArr, 0, this.f4848h, 0, i3);
                    System.arraycopy(objArr, 0, this.f4849i, 0, i3);
                }
                int i7 = this.f4850j;
                if (i3 < i7) {
                    int i8 = i3 + 1;
                    System.arraycopy(iArr, i8, this.f4848h, i3, i7 - i3);
                    System.arraycopy(objArr, i8, this.f4849i, i3, this.f4850j - i3);
                }
            }
        }
        return e3;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.f4848h;
        int i3 = this.f4850j;
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            i4 += iArr[i5];
        }
        return i4;
    }

    public E i(int i3) {
        return (E) this.f4849i[i3];
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.f4850j <= 0;
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
        boolean z3 = false;
        for (int i3 = this.f4850j - 1; i3 >= 0; i3--) {
            if (!collection.contains(this.f4849i[i3])) {
                h(i3);
                z3 = true;
            }
        }
        return z3;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.f4850j;
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray() {
        int i3 = this.f4850j;
        Object[] objArr = new Object[i3];
        System.arraycopy(this.f4849i, 0, objArr, 0, i3);
        return objArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f4850j * 14);
        sb.append('{');
        for (int i3 = 0; i3 < this.f4850j; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            E eI = i(i3);
            if (eI != this) {
                sb.append(eI);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public b(int i3) {
        if (i3 == 0) {
            this.f4848h = f4842b;
            this.f4849i = f4843c;
        } else {
            a(i3);
        }
        this.f4850j = 0;
    }

    @Override // java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        if (tArr.length < this.f4850j) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.f4850j));
        }
        System.arraycopy(this.f4849i, 0, tArr, 0, this.f4850j);
        int length = tArr.length;
        int i3 = this.f4850j;
        if (length > i3) {
            tArr[i3] = null;
        }
        return tArr;
    }
}
