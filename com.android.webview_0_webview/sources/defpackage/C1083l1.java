package defpackage;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: renamed from: l1, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1083l1 implements Collection, Set {
    public static Object[] A;
    public static int B;
    public static final int[] w = new int[0];
    public static final Object[] x = new Object[0];
    public static Object[] y;
    public static int z;
    public int[] s = w;
    public Object[] t = x;
    public int u = 0;
    public AbstractC1578u1 v;

    public static void f(int[] iArr, Object[] objArr, int i) {
        if (iArr.length == 8) {
            synchronized (C1083l1.class) {
                if (B < 10) {
                    objArr[0] = A;
                    objArr[1] = iArr;
                    for (int i2 = i - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    A = objArr;
                    B++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (C1083l1.class) {
                if (z < 10) {
                    objArr[0] = y;
                    objArr[1] = iArr;
                    for (int i3 = i - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    y = objArr;
                    z++;
                }
            }
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean add(Object obj) {
        int i;
        int iG;
        if (obj == null) {
            iG = h();
            i = 0;
        } else {
            int iHashCode = obj.hashCode();
            i = iHashCode;
            iG = g(obj, iHashCode);
        }
        if (iG >= 0) {
            return false;
        }
        int i2 = ~iG;
        int i3 = this.u;
        if (i3 >= this.s.length) {
            int i4 = 4;
            if (i3 >= 8) {
                i4 = (i3 >> 1) + i3;
            } else if (i3 >= 4) {
                i4 = 8;
            }
            int[] iArr = this.s;
            Object[] objArr = this.t;
            e(i4);
            int[] iArr2 = this.s;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr, 0, this.t, 0, objArr.length);
            }
            f(iArr, objArr, this.u);
        }
        int i5 = this.u;
        if (i2 < i5) {
            int[] iArr3 = this.s;
            int i6 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i6, i5 - i2);
            Object[] objArr2 = this.t;
            System.arraycopy(objArr2, i2, objArr2, i6, this.u - i2);
        }
        this.s[i2] = i;
        this.t[i2] = obj;
        this.u++;
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean addAll(Collection collection) {
        int size = collection.size() + this.u;
        int[] iArr = this.s;
        boolean zAdd = false;
        if (iArr.length < size) {
            Object[] objArr = this.t;
            e(size);
            int i = this.u;
            if (i > 0) {
                System.arraycopy(iArr, 0, this.s, 0, i);
                System.arraycopy(objArr, 0, this.t, 0, this.u);
            }
            f(iArr, objArr, this.u);
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    @Override // java.util.Collection, java.util.Set
    public void clear() {
        int i = this.u;
        if (i != 0) {
            f(this.s, this.t, i);
            this.s = w;
            this.t = x;
            this.u = 0;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public final void e(int i) {
        if (i == 8) {
            synchronized (C1083l1.class) {
                if (A != null) {
                    Object[] objArr = A;
                    this.t = objArr;
                    A = (Object[]) objArr[0];
                    this.s = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    B--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (C1083l1.class) {
                if (y != null) {
                    Object[] objArr2 = y;
                    this.t = objArr2;
                    y = (Object[]) objArr2[0];
                    this.s = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    z--;
                    return;
                }
            }
        }
        this.s = new int[i];
        this.t = new Object[i];
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (this.u != set.size()) {
                return false;
            }
            for (int i = 0; i < this.u; i++) {
                try {
                    if (!set.contains(this.t[i])) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return true;
        }
        return false;
    }

    public final int g(Object obj, int i) {
        int i2 = this.u;
        if (i2 == 0) {
            return -1;
        }
        int iA = AbstractC1139m1.a(this.s, i2, i);
        if (iA < 0 || obj.equals(this.t[iA])) {
            return iA;
        }
        int i3 = iA + 1;
        while (i3 < i2 && this.s[i3] == i) {
            if (obj.equals(this.t[i3])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = iA - 1; i4 >= 0 && this.s[i4] == i; i4--) {
            if (obj.equals(this.t[i4])) {
                return i4;
            }
        }
        return ~i3;
    }

    public final int h() {
        int i = this.u;
        if (i == 0) {
            return -1;
        }
        int iA = AbstractC1139m1.a(this.s, i, 0);
        if (iA < 0 || this.t[iA] == null) {
            return iA;
        }
        int i2 = iA + 1;
        while (i2 < i && this.s[i2] == 0) {
            if (this.t[i2] == null) {
                return i2;
            }
            i2++;
        }
        for (int i3 = iA - 1; i3 >= 0 && this.s[i3] == 0; i3--) {
            if (this.t[i3] == null) {
                return i3;
            }
        }
        return ~i2;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        int[] iArr = this.s;
        int i = this.u;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += iArr[i3];
        }
        return i2;
    }

    public Object i(int i) {
        Object[] objArr = this.t;
        Object obj = objArr[i];
        int i2 = this.u;
        if (i2 <= 1) {
            f(this.s, objArr, i2);
            this.s = w;
            this.t = x;
            this.u = 0;
        } else {
            int[] iArr = this.s;
            if (iArr.length <= 8 || i2 >= iArr.length / 3) {
                int i3 = this.u - 1;
                this.u = i3;
                if (i < i3) {
                    int[] iArr2 = this.s;
                    int i4 = i + 1;
                    System.arraycopy(iArr2, i4, iArr2, i, i3 - i);
                    Object[] objArr2 = this.t;
                    System.arraycopy(objArr2, i4, objArr2, i, this.u - i);
                }
                this.t[this.u] = null;
            } else {
                int i5 = i2 > 8 ? i2 + (i2 >> 1) : 8;
                int[] iArr3 = this.s;
                Object[] objArr3 = this.t;
                e(i5);
                this.u--;
                if (i > 0) {
                    System.arraycopy(iArr3, 0, this.s, 0, i);
                    System.arraycopy(objArr3, 0, this.t, 0, i);
                }
                int i6 = this.u;
                if (i < i6) {
                    int i7 = i + 1;
                    System.arraycopy(iArr3, i7, this.s, i, i6 - i);
                    System.arraycopy(objArr3, i7, this.t, i, this.u - i);
                }
            }
        }
        return obj;
    }

    public int indexOf(Object obj) {
        return obj == null ? h() : g(obj, obj.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.u <= 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator iterator() {
        if (this.v == null) {
            this.v = new C1027k1(this);
        }
        AbstractC1578u1 abstractC1578u1 = this.v;
        if (abstractC1578u1.b == null) {
            abstractC1578u1.b = new C1413r1(abstractC1578u1);
        }
        return abstractC1578u1.b.iterator();
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
    public boolean removeAll(Collection collection) {
        Iterator it = collection.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean retainAll(Collection collection) {
        boolean z2 = false;
        for (int i = this.u - 1; i >= 0; i--) {
            if (!collection.contains(this.t[i])) {
                i(i);
                z2 = true;
            }
        }
        return z2;
    }

    @Override // java.util.Collection, java.util.Set
    public int size() {
        return this.u;
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray() {
        int i = this.u;
        Object[] objArr = new Object[i];
        System.arraycopy(this.t, 0, objArr, 0, i);
        return objArr;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.u * 14);
        sb.append('{');
        for (int i = 0; i < this.u; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            Object obj = this.t[i];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    @Override // java.util.Collection, java.util.Set
    public Object[] toArray(Object[] objArr) {
        if (objArr.length < this.u) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), this.u);
        }
        System.arraycopy(this.t, 0, objArr, 0, this.u);
        int length = objArr.length;
        int i = this.u;
        if (length > i) {
            objArr[i] = null;
        }
        return objArr;
    }
}
