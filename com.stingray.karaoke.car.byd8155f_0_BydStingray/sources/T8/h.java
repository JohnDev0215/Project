package t8;

import Q7.InterfaceC0671y;
import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import q0.C2162a;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f22500a = new h();

    public final C2449b a(List list, InterfaceC0671y interfaceC0671y, N7.k kVar) {
        List listZ0 = AbstractC1998m.z0(list);
        ArrayList arrayList = new ArrayList();
        Iterator it = listZ0.iterator();
        while (it.hasNext()) {
            g gVarB = b(it.next(), null);
            if (gVarB != null) {
                arrayList.add(gVarB);
            }
        }
        return interfaceC0671y != null ? new w(arrayList, interfaceC0671y.l().p(kVar)) : new C2449b(arrayList, new C2162a(kVar, 3));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v19, types: [n7.u] */
    /* JADX WARN: Type inference failed for: r0v20, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v21, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v22, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r0v29, types: [n7.u] */
    /* JADX WARN: Type inference failed for: r0v30, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v31, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v32, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r0v38, types: [n7.u] */
    /* JADX WARN: Type inference failed for: r0v39, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v41, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v42, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v0, types: [n7.u] */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v12, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v16, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v17, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v18, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v19, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v20, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v21, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r5v0, types: [t8.h] */
    public final g b(Object obj, InterfaceC0671y interfaceC0671y) {
        ?? J6;
        ?? J9;
        ?? J10;
        if (obj instanceof Byte) {
            return new C2451d(((Number) obj).byteValue());
        }
        if (obj instanceof Short) {
            return new u(((Number) obj).shortValue());
        }
        if (obj instanceof Integer) {
            return new k(((Number) obj).intValue());
        }
        if (obj instanceof Long) {
            return new s(((Number) obj).longValue());
        }
        if (obj instanceof Character) {
            Character ch = (Character) obj;
            ch.getClass();
            return new C2452e(ch);
        }
        if (obj instanceof Float) {
            return new C2450c(((Number) obj).floatValue());
        }
        if (obj instanceof Double) {
            return new C2450c(((Number) obj).doubleValue());
        }
        if (obj instanceof Boolean) {
            Boolean bool = (Boolean) obj;
            bool.getClass();
            return new C2450c(bool);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            A7.m.f(str, "value");
            return new v(str);
        }
        boolean z6 = obj instanceof byte[];
        ?? J11 = n7.u.l;
        int i7 = 0;
        if (z6) {
            byte[] bArr = (byte[]) obj;
            A7.m.f(bArr, "<this>");
            int length = bArr.length;
            if (length != 0) {
                if (length != 1) {
                    J11 = new ArrayList(bArr.length);
                    int length2 = bArr.length;
                    while (i7 < length2) {
                        J11.add(Byte.valueOf(bArr[i7]));
                        i7++;
                    }
                } else {
                    J11 = AbstractC1387d.j(Byte.valueOf(bArr[0]));
                }
            }
            return a(J11, interfaceC0671y, N7.k.BYTE);
        }
        if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            A7.m.f(sArr, "<this>");
            int length3 = sArr.length;
            if (length3 != 0) {
                if (length3 != 1) {
                    J11 = new ArrayList(sArr.length);
                    int length4 = sArr.length;
                    while (i7 < length4) {
                        J11.add(Short.valueOf(sArr[i7]));
                        i7++;
                    }
                } else {
                    J11 = AbstractC1387d.j(Short.valueOf(sArr[0]));
                }
            }
            return a(J11, interfaceC0671y, N7.k.SHORT);
        }
        if (obj instanceof int[]) {
            return a(AbstractC1996k.a0((int[]) obj), interfaceC0671y, N7.k.INT);
        }
        if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            A7.m.f(jArr, "<this>");
            int length5 = jArr.length;
            if (length5 != 0) {
                if (length5 != 1) {
                    J10 = new ArrayList(jArr.length);
                    for (long j3 : jArr) {
                        J10.add(Long.valueOf(j3));
                    }
                } else {
                    J10 = AbstractC1387d.j(Long.valueOf(jArr[0]));
                }
            } else {
                J10 = n7.u.l;
            }
            return a(J10, interfaceC0671y, N7.k.LONG);
        }
        if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            A7.m.f(cArr, "<this>");
            int length6 = cArr.length;
            if (length6 != 0) {
                if (length6 != 1) {
                    J11 = new ArrayList(cArr.length);
                    int length7 = cArr.length;
                    while (i7 < length7) {
                        J11.add(Character.valueOf(cArr[i7]));
                        i7++;
                    }
                } else {
                    J11 = AbstractC1387d.j(Character.valueOf(cArr[0]));
                }
            }
            return a(J11, interfaceC0671y, N7.k.CHAR);
        }
        if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            A7.m.f(fArr, "<this>");
            int length8 = fArr.length;
            if (length8 != 0) {
                if (length8 != 1) {
                    J9 = new ArrayList(fArr.length);
                    for (float f10 : fArr) {
                        J9.add(Float.valueOf(f10));
                    }
                } else {
                    J9 = AbstractC1387d.j(Float.valueOf(fArr[0]));
                }
            } else {
                J9 = n7.u.l;
            }
            return a(J9, interfaceC0671y, N7.k.FLOAT);
        }
        if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            A7.m.f(dArr, "<this>");
            int length9 = dArr.length;
            if (length9 != 0) {
                if (length9 != 1) {
                    J11 = new ArrayList(dArr.length);
                    int length10 = dArr.length;
                    while (i7 < length10) {
                        J11.add(Double.valueOf(dArr[i7]));
                        i7++;
                    }
                } else {
                    J11 = AbstractC1387d.j(Double.valueOf(dArr[0]));
                }
            }
            return a(J11, interfaceC0671y, N7.k.DOUBLE);
        }
        if (!(obj instanceof boolean[])) {
            if (obj == null) {
                return new t(null);
            }
            return null;
        }
        boolean[] zArr = (boolean[]) obj;
        A7.m.f(zArr, "<this>");
        int length11 = zArr.length;
        if (length11 != 0) {
            if (length11 != 1) {
                J6 = new ArrayList(zArr.length);
                for (boolean z10 : zArr) {
                    J6.add(Boolean.valueOf(z10));
                }
            } else {
                J6 = AbstractC1387d.j(Boolean.valueOf(zArr[0]));
            }
        } else {
            J6 = n7.u.l;
        }
        return a(J6, interfaceC0671y, N7.k.BOOLEAN);
    }
}
