package n7;

import h8.AbstractC1386c;
import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: n7.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1996k extends AbstractC1386c {
    public static boolean A(Object[] objArr, Object[] objArr2) {
        if (objArr == objArr2) {
            return true;
        }
        if (objArr == null || objArr2 == null || objArr.length != objArr2.length) {
            return false;
        }
        int length = objArr.length;
        for (int i7 = 0; i7 < length; i7++) {
            Object obj = objArr[i7];
            Object obj2 = objArr2[i7];
            if (obj != obj2) {
                if (obj == null || obj2 == null) {
                    return false;
                }
                if ((obj instanceof Object[]) && (obj2 instanceof Object[])) {
                    if (!A((Object[]) obj, (Object[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof byte[]) && (obj2 instanceof byte[])) {
                    if (!Arrays.equals((byte[]) obj, (byte[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof short[]) && (obj2 instanceof short[])) {
                    if (!Arrays.equals((short[]) obj, (short[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof int[]) && (obj2 instanceof int[])) {
                    if (!Arrays.equals((int[]) obj, (int[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof long[]) && (obj2 instanceof long[])) {
                    if (!Arrays.equals((long[]) obj, (long[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof float[]) && (obj2 instanceof float[])) {
                    if (!Arrays.equals((float[]) obj, (float[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof double[]) && (obj2 instanceof double[])) {
                    if (!Arrays.equals((double[]) obj, (double[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof char[]) && (obj2 instanceof char[])) {
                    if (!Arrays.equals((char[]) obj, (char[]) obj2)) {
                        return false;
                    }
                } else if ((obj instanceof boolean[]) && (obj2 instanceof boolean[])) {
                    if (!Arrays.equals((boolean[]) obj, (boolean[]) obj2)) {
                        return false;
                    }
                } else if (!obj.equals(obj2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void B(int i7, int i10, int i11, byte[] bArr, byte[] bArr2) {
        A7.m.f(bArr, "<this>");
        A7.m.f(bArr2, "destination");
        System.arraycopy(bArr, i10, bArr2, i7, i11 - i10);
    }

    public static void C(int i7, int i10, int i11, int[] iArr, int[] iArr2) {
        A7.m.f(iArr, "<this>");
        A7.m.f(iArr2, "destination");
        System.arraycopy(iArr, i10, iArr2, i7, i11 - i10);
    }

    public static void D(int i7, int i10, int i11, Object[] objArr, Object[] objArr2) {
        A7.m.f(objArr, "<this>");
        A7.m.f(objArr2, "destination");
        System.arraycopy(objArr, i10, objArr2, i7, i11 - i10);
    }

    public static void E(char[] cArr, char[] cArr2, int i7, int i10, int i11) {
        A7.m.f(cArr, "<this>");
        A7.m.f(cArr2, "destination");
        System.arraycopy(cArr, i10, cArr2, i7, i11 - i10);
    }

    public static /* synthetic */ void F(int i7, int i10, int i11, int[] iArr, int[] iArr2) {
        if ((i11 & 2) != 0) {
            i7 = 0;
        }
        if ((i11 & 8) != 0) {
            i10 = iArr.length;
        }
        C(i7, 0, i10, iArr, iArr2);
    }

    public static /* synthetic */ void G(int i7, int i10, int i11, Object[] objArr, Object[] objArr2) {
        if ((i11 & 4) != 0) {
            i7 = 0;
        }
        if ((i11 & 8) != 0) {
            i10 = objArr.length;
        }
        D(0, i7, i10, objArr, objArr2);
    }

    public static byte[] H(int i7, int i10, byte[] bArr) {
        A7.m.f(bArr, "<this>");
        AbstractC1386c.d(i10, bArr.length);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i7, i10);
        A7.m.e(bArrCopyOfRange, "copyOfRange(...)");
        return bArrCopyOfRange;
    }

    public static Object[] I(Object[] objArr, int i7, int i10) {
        A7.m.f(objArr, "<this>");
        AbstractC1386c.d(i10, objArr.length);
        Object[] objArrCopyOfRange = Arrays.copyOfRange(objArr, i7, i10);
        A7.m.e(objArrCopyOfRange, "copyOfRange(...)");
        return objArrCopyOfRange;
    }

    public static void J(Object[] objArr, B2.q qVar, int i7, int i10) {
        A7.m.f(objArr, "<this>");
        Arrays.fill(objArr, i7, i10, qVar);
    }

    public static void K(long[] jArr) {
        int length = jArr.length;
        A7.m.f(jArr, "<this>");
        Arrays.fill(jArr, 0, length, -9187201950435737472L);
    }

    public static ArrayList M(Object[] objArr) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : objArr) {
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static Object N(Object[] objArr) {
        A7.m.f(objArr, "<this>");
        if (objArr.length != 0) {
            return objArr[0];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static Object O(Object[] objArr) {
        if (objArr.length == 0) {
            return null;
        }
        return objArr[0];
    }

    public static G7.d P(int[] iArr) {
        return new G7.d(0, iArr.length - 1, 1);
    }

    public static int Q(Object[] objArr) {
        A7.m.f(objArr, "<this>");
        return objArr.length - 1;
    }

    public static Integer R(int[] iArr, int i7) {
        A7.m.f(iArr, "<this>");
        if (i7 < 0 || i7 >= iArr.length) {
            return null;
        }
        return Integer.valueOf(iArr[i7]);
    }

    public static Object S(int i7, Object[] objArr) {
        A7.m.f(objArr, "<this>");
        if (i7 < 0 || i7 >= objArr.length) {
            return null;
        }
        return objArr[i7];
    }

    public static int T(Object obj, Object[] objArr) {
        A7.m.f(objArr, "<this>");
        int i7 = 0;
        if (obj == null) {
            int length = objArr.length;
            while (i7 < length) {
                if (objArr[i7] == null) {
                    return i7;
                }
                i7++;
            }
            return -1;
        }
        int length2 = objArr.length;
        while (i7 < length2) {
            if (obj.equals(objArr[i7])) {
                return i7;
            }
            i7++;
        }
        return -1;
    }

    public static final void U(Object[] objArr, StringBuilder sb, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i7, CharSequence charSequence4, InterfaceC2782k interfaceC2782k) {
        A7.m.f(objArr, "<this>");
        A7.m.f(charSequence, "separator");
        A7.m.f(charSequence2, "prefix");
        A7.m.f(charSequence3, "postfix");
        A7.m.f(charSequence4, "truncated");
        sb.append(charSequence2);
        int i10 = 0;
        for (Object obj : objArr) {
            i10++;
            if (i10 > 1) {
                sb.append(charSequence);
            }
            if (i7 >= 0 && i10 > i7) {
                break;
            }
            io.sentry.config.a.K(sb, obj, interfaceC2782k);
        }
        if (i7 >= 0 && i10 > i7) {
            sb.append(charSequence4);
        }
        sb.append(charSequence3);
    }

    public static String V(Object[] objArr, String str, String str2, InterfaceC2782k interfaceC2782k) {
        StringBuilder sb = new StringBuilder();
        U(objArr, sb, "", str, str2, -1, "...", interfaceC2782k);
        return sb.toString();
    }

    public static Object W(Object[] objArr) {
        if (objArr.length != 0) {
            return objArr[objArr.length - 1];
        }
        throw new NoSuchElementException("Array is empty.");
    }

    public static char X(char[] cArr) {
        int length = cArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return cArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    public static Object Y(Object[] objArr) {
        A7.m.f(objArr, "<this>");
        int length = objArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return objArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    public static final void Z(Object[] objArr, LinkedHashSet linkedHashSet) {
        A7.m.f(objArr, "<this>");
        for (Object obj : objArr) {
            linkedHashSet.add(obj);
        }
    }

    public static List a0(int[] iArr) {
        A7.m.f(iArr, "<this>");
        int length = iArr.length;
        if (length == 0) {
            return u.l;
        }
        if (length == 1) {
            return AbstractC1387d.j(Integer.valueOf(iArr[0]));
        }
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i7 : iArr) {
            arrayList.add(Integer.valueOf(i7));
        }
        return arrayList;
    }

    public static List b0(Object[] objArr) {
        A7.m.f(objArr, "<this>");
        int length = objArr.length;
        return length != 0 ? length != 1 ? new ArrayList(new C1994i(objArr, false)) : AbstractC1387d.j(objArr[0]) : u.l;
    }

    public static Set c0(Object[] objArr) {
        A7.m.f(objArr, "<this>");
        int length = objArr.length;
        if (length == 0) {
            return w.l;
        }
        if (length == 1) {
            return AbstractC1985B.t(objArr[0]);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(z.q(objArr.length));
        Z(objArr, linkedHashSet);
        return linkedHashSet;
    }

    public static Iterable w(Object[] objArr) {
        A7.m.f(objArr, "<this>");
        return objArr.length == 0 ? u.l : new P8.p(objArr, 1);
    }

    public static List x(Object[] objArr) {
        A7.m.f(objArr, "<this>");
        List listAsList = Arrays.asList(objArr);
        A7.m.e(listAsList, "asList(...)");
        return listAsList;
    }

    public static P8.k y(Object[] objArr) {
        return objArr.length == 0 ? P8.e.f5463a : new P8.o(objArr, 1);
    }

    public static boolean z(Object obj, Object[] objArr) {
        A7.m.f(objArr, "<this>");
        return T(obj, objArr) >= 0;
    }
}
