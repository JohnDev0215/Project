package d5;

import N1.C0420a;
import a.AbstractC0904a;
import c5.C1029f;
import c5.InterfaceC1027d;
import c5.InterfaceC1028e;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedSet;

/* JADX INFO: renamed from: d5.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1095q {
    public final /* synthetic */ int l;

    public static void d(int i7, Object[] objArr) {
        for (int i10 = 0; i10 < i7; i10++) {
            if (objArr[i10] == null) {
                throw new NullPointerException(android.hardware.bydauto.ac.tsimpl.a.e(i10, "at index "));
            }
        }
    }

    public static void e(int i7, String str) {
        if (i7 >= 0) {
            return;
        }
        throw new IllegalArgumentException(str + " cannot be negative but was: " + i7);
    }

    public static Object g(int i7) {
        if (i7 < 2 || i7 > 1073741824 || Integer.highestOneBit(i7) != i7) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "must be power of 2 between 2^1 and 2^30: "));
        }
        return i7 <= 256 ? new byte[i7] : i7 <= 65536 ? new short[i7] : new int[i7];
    }

    public static boolean i(Object obj, Map map) {
        if (map == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return map.entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    public static boolean j(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() == set2.size()) {
                    if (set.containsAll(set2)) {
                        return true;
                    }
                }
                return false;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public static n0 k(Set set, InterfaceC1028e interfaceC1028e) {
        if (!(set instanceof SortedSet)) {
            if (!(set instanceof n0)) {
                set.getClass();
                return new n0(set, interfaceC1028e);
            }
            n0 n0Var = (n0) set;
            InterfaceC1028e interfaceC1028e2 = n0Var.f13445m;
            interfaceC1028e2.getClass();
            return new n0(n0Var.l, new C1029f(Arrays.asList(interfaceC1028e2, interfaceC1028e)));
        }
        Set set2 = (SortedSet) set;
        if (!(set2 instanceof n0)) {
            set2.getClass();
            return new o0(set2, interfaceC1028e);
        }
        n0 n0Var2 = (n0) set2;
        InterfaceC1028e interfaceC1028e3 = n0Var2.f13445m;
        interfaceC1028e3.getClass();
        return new o0((SortedSet) n0Var2.l, new C1029f(Arrays.asList(interfaceC1028e3, interfaceC1028e)));
    }

    public static Object l(Iterable iterable) {
        Object next;
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.isEmpty()) {
                throw new NoSuchElementException();
            }
            return list.get(list.size() - 1);
        }
        Iterator it = iterable.iterator();
        do {
            next = it.next();
        } while (it.hasNext());
        return next;
    }

    public static int n(Set set) {
        Iterator it = set.iterator();
        int i7 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i7 = ~(~(i7 + (next != null ? next.hashCode() : 0)));
        }
        return i7;
    }

    public static m0 o(M m10, M m11) {
        P8.n.q(m10, "set1");
        P8.n.q(m11, "set2");
        return new m0(m10, m11);
    }

    public static int p(int i7, int i10, int i11) {
        return (i7 & (~i11)) | (i10 & i11);
    }

    public static ArrayList q(Iterator it) {
        ArrayList arrayList = new ArrayList();
        it.getClass();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public static int r(Object obj, Object obj2, int i7, Object obj3, int[] iArr, Object[] objArr, Object[] objArr2) {
        int i10;
        int i11;
        int iU = u(obj);
        int i12 = iU & i7;
        int iV = v(i12, obj3);
        if (iV == 0) {
            return -1;
        }
        int i13 = ~i7;
        int i14 = iU & i13;
        int i15 = -1;
        while (true) {
            i10 = iV - 1;
            i11 = iArr[i10];
            if ((i11 & i13) == i14 && AbstractC0904a.A(obj, objArr[i10]) && (objArr2 == null || AbstractC0904a.A(obj2, objArr2[i10]))) {
                break;
            }
            int i16 = i11 & i7;
            if (i16 == 0) {
                return -1;
            }
            i15 = i10;
            iV = i16;
        }
        int i17 = i11 & i7;
        if (i15 == -1) {
            w(i12, i17, obj3);
        } else {
            iArr[i15] = p(iArr[i15], i17, i7);
        }
        return i10;
    }

    public static void s(List list, InterfaceC1028e interfaceC1028e, int i7, int i10) {
        for (int size = list.size() - 1; size > i10; size--) {
            if (interfaceC1028e.apply(list.get(size))) {
                list.remove(size);
            }
        }
        for (int i11 = i10 - 1; i11 >= i7; i11--) {
            list.remove(i11);
        }
    }

    public static int t(int i7) {
        return (int) (((long) Integer.rotateLeft((int) (((long) i7) * (-862048943)), 15)) * 461845907);
    }

    public static int u(Object obj) {
        return t(obj == null ? 0 : obj.hashCode());
    }

    public static int v(int i7, Object obj) {
        return obj instanceof byte[] ? ((byte[]) obj)[i7] & 255 : obj instanceof short[] ? ((short[]) obj)[i7] & 65535 : ((int[]) obj)[i7];
    }

    public static void w(int i7, int i10, Object obj) {
        if (obj instanceof byte[]) {
            ((byte[]) obj)[i7] = (byte) i10;
        } else if (obj instanceof short[]) {
            ((short[]) obj)[i7] = (short) i10;
        } else {
            ((int[]) obj)[i7] = i10;
        }
    }

    public static AbstractList x(InterfaceC1027d interfaceC1027d, List list) {
        return list instanceof RandomAccess ? new S(interfaceC1027d, list) : new T(interfaceC1027d, list);
    }

    public C0420a c() {
        e(2, "expectedValuesPerKey");
        return new C0420a(this);
    }

    public abstract Map f();

    public abstract Object h();

    public String toString() {
        switch (this.l) {
            case 2:
                return h().toString();
            default:
                return super.toString();
        }
    }
}
