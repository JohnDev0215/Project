package g5;

import P8.n;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: renamed from: g5.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1301b extends AbstractList implements RandomAccess, Serializable {
    public final int[] l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f14978m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f14979n;

    public C1301b(int i7, int i10, int[] iArr) {
        this.l = iArr;
        this.f14978m = i7;
        this.f14979n = i10;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (obj instanceof Integer) {
            int iIntValue = ((Integer) obj).intValue();
            int i7 = this.f14978m;
            while (true) {
                if (i7 >= this.f14979n) {
                    i7 = -1;
                    break;
                }
                if (this.l[i7] == iIntValue) {
                    break;
                }
                i7++;
            }
            if (i7 != -1) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C1301b)) {
            return super.equals(obj);
        }
        C1301b c1301b = (C1301b) obj;
        int size = size();
        if (c1301b.size() != size) {
            return false;
        }
        for (int i7 = 0; i7 < size; i7++) {
            if (this.l[this.f14978m + i7] != c1301b.l[c1301b.f14978m + i7]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i7) {
        n.p(i7, size());
        return Integer.valueOf(this.l[this.f14978m + i7]);
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i7 = 1;
        for (int i10 = this.f14978m; i10 < this.f14979n; i10++) {
            i7 = (i7 * 31) + this.l[i10];
        }
        return i7;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof Integer) {
            int iIntValue = ((Integer) obj).intValue();
            int i7 = this.f14978m;
            int i10 = i7;
            while (true) {
                if (i10 >= this.f14979n) {
                    i10 = -1;
                    break;
                }
                if (this.l[i10] == iIntValue) {
                    break;
                }
                i10++;
            }
            if (i10 >= 0) {
                return i10 - i7;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return false;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        int i7;
        if (obj instanceof Integer) {
            int iIntValue = ((Integer) obj).intValue();
            int i10 = this.f14979n;
            while (true) {
                i10--;
                i7 = this.f14978m;
                if (i10 < i7) {
                    i10 = -1;
                    break;
                }
                if (this.l[i10] == iIntValue) {
                    break;
                }
            }
            if (i10 >= 0) {
                return i10 - i7;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i7, Object obj) {
        Integer num = (Integer) obj;
        n.p(i7, size());
        int i10 = this.f14978m + i7;
        int[] iArr = this.l;
        int i11 = iArr[i10];
        num.getClass();
        iArr[i10] = num.intValue();
        return Integer.valueOf(i11);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f14979n - this.f14978m;
    }

    @Override // java.util.AbstractList, java.util.List
    public final List subList(int i7, int i10) {
        n.s(i7, i10, size());
        if (i7 == i10) {
            return Collections.emptyList();
        }
        int i11 = this.f14978m;
        return new C1301b(i7 + i11, i11 + i10, this.l);
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        StringBuilder sb = new StringBuilder(size() * 5);
        sb.append('[');
        int[] iArr = this.l;
        int i7 = this.f14978m;
        sb.append(iArr[i7]);
        while (true) {
            i7++;
            if (i7 >= this.f14979n) {
                sb.append(']');
                return sb.toString();
            }
            sb.append(", ");
            sb.append(iArr[i7]);
        }
    }
}
