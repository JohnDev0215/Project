package com.google.protobuf;

import com.google.protobuf.c0;
import defpackage.dj3;
import defpackage.lk;
import defpackage.nu;
import defpackage.q61;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: SchemaUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class c1 {
    public static final Class<?> a = a0.class;
    public static final i1<?, ?> b = B(false);
    public static final i1<?, ?> c = B(true);
    public static final l1 d = new l1();

    public static Object A(Class<?> cls, String str) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(cls.getName());
            sb.append("$");
            StringBuilder sb2 = new StringBuilder();
            boolean z = true;
            for (int i = 0; i < str.length(); i++) {
                char cCharAt = str.charAt(i);
                if ('a' > cCharAt || cCharAt > 'z') {
                    if ('A' > cCharAt || cCharAt > 'Z') {
                        if ('0' <= cCharAt && cCharAt <= '9') {
                            sb2.append(cCharAt);
                        }
                        z = true;
                    } else if (i != 0 || z) {
                        sb2.append(cCharAt);
                    } else {
                        sb2.append((char) (cCharAt + ' '));
                    }
                } else if (z) {
                    sb2.append((char) (cCharAt - ' '));
                } else {
                    sb2.append(cCharAt);
                }
                z = false;
            }
            sb.append(sb2.toString());
            sb.append("DefaultEntryHolder");
            Field[] declaredFields = Class.forName(sb.toString()).getDeclaredFields();
            if (declaredFields.length == 1) {
                return dj3.c.k(declaredFields[0]);
            }
            throw new IllegalStateException("Unable to look up map field default entry holder class for " + str + " in " + cls.getName());
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    public static i1<?, ?> B(boolean z) {
        if (m1.class == 0) {
            return null;
        }
        try {
            return (i1) m1.class.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean C(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static <UT, UB> UB D(int i, int i2, UB ub, i1<UT, UB> i1Var) {
        if (ub == null) {
            ub = i1Var.m();
        }
        i1Var.e(i2, i, ub);
        return ub;
    }

    public static void E(int i, List list, i iVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        nu nuVar = iVar.a;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                nuVar.p0(i, ((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
            return;
        }
        nuVar.M0(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Boolean) list.get(i4)).booleanValue();
            boolean z2 = nu.l;
            i3++;
        }
        nuVar.O0(i3);
        while (i2 < list.size()) {
            nuVar.o0(((Boolean) list.get(i2)).booleanValue() ? (byte) 1 : (byte) 0);
            i2++;
        }
    }

    public static void F(int i, List list, i iVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        iVar.getClass();
        for (int i2 = 0; i2 < list.size(); i2++) {
            iVar.a.r0(i, (lk) list.get(i2));
        }
    }

    public static void G(int i, List list, i iVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        nu nuVar = iVar.a;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                double dDoubleValue = ((Double) list.get(i2)).doubleValue();
                nuVar.getClass();
                nuVar.w0(i, Double.doubleToRawLongBits(dDoubleValue));
                i2++;
            }
            return;
        }
        nuVar.M0(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Double) list.get(i4)).doubleValue();
            boolean z2 = nu.l;
            i3 += 8;
        }
        nuVar.O0(i3);
        while (i2 < list.size()) {
            nuVar.x0(Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
            i2++;
        }
    }

    public static void H(int i, List list, i iVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        nu nuVar = iVar.a;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                nuVar.z0(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        nuVar.M0(i, 2);
        int iT = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iT += nu.T(((Integer) list.get(i3)).intValue());
        }
        nuVar.O0(iT);
        while (i2 < list.size()) {
            nuVar.C0(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void I(int i, List list, i iVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        iVar.f(i, list, z);
    }

    public static void J(int i, List list, i iVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        iVar.h(i, list, z);
    }

    public static void K(int i, List list, i iVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        nu nuVar = iVar.a;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                float fFloatValue = ((Float) list.get(i2)).floatValue();
                nuVar.getClass();
                nuVar.u0(i, Float.floatToRawIntBits(fFloatValue));
                i2++;
            }
            return;
        }
        nuVar.M0(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Float) list.get(i4)).floatValue();
            boolean z2 = nu.l;
            i3 += 4;
        }
        nuVar.O0(i3);
        while (i2 < list.size()) {
            nuVar.v0(Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
            i2++;
        }
    }

    public static void L(int i, List list, i iVar, b1 b1Var) {
        if (list == null || list.isEmpty()) {
            return;
        }
        iVar.getClass();
        for (int i2 = 0; i2 < list.size(); i2++) {
            iVar.j(i, b1Var, list.get(i2));
        }
    }

    public static void M(int i, List list, i iVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        nu nuVar = iVar.a;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                nuVar.z0(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        nuVar.M0(i, 2);
        int iT = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iT += nu.T(((Integer) list.get(i3)).intValue());
        }
        nuVar.O0(iT);
        while (i2 < list.size()) {
            nuVar.C0(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void N(int i, List list, i iVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        iVar.m(i, list, z);
    }

    public static void O(int i, List list, i iVar, b1 b1Var) {
        if (list == null || list.isEmpty()) {
            return;
        }
        iVar.getClass();
        for (int i2 = 0; i2 < list.size(); i2++) {
            iVar.n(i, b1Var, list.get(i2));
        }
    }

    public static void P(int i, List list, i iVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        nu nuVar = iVar.a;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                nuVar.u0(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        nuVar.M0(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Integer) list.get(i4)).intValue();
            boolean z2 = nu.l;
            i3 += 4;
        }
        nuVar.O0(i3);
        while (i2 < list.size()) {
            nuVar.v0(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void Q(int i, List list, i iVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        nu nuVar = iVar.a;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                nuVar.w0(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        nuVar.M0(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Long) list.get(i4)).longValue();
            boolean z2 = nu.l;
            i3 += 8;
        }
        nuVar.O0(i3);
        while (i2 < list.size()) {
            nuVar.x0(((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public static void R(int i, List list, i iVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        nu nuVar = iVar.a;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                int iIntValue = ((Integer) list.get(i2)).intValue();
                nuVar.N0(i, (iIntValue >> 31) ^ (iIntValue << 1));
                i2++;
            }
            return;
        }
        nuVar.M0(i, 2);
        int iK0 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            int iIntValue2 = ((Integer) list.get(i3)).intValue();
            iK0 += nu.k0((iIntValue2 >> 31) ^ (iIntValue2 << 1));
        }
        nuVar.O0(iK0);
        while (i2 < list.size()) {
            int iIntValue3 = ((Integer) list.get(i2)).intValue();
            nuVar.O0((iIntValue3 >> 31) ^ (iIntValue3 << 1));
            i2++;
        }
    }

    public static void S(int i, List list, i iVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        nu nuVar = iVar.a;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                long jLongValue = ((Long) list.get(i2)).longValue();
                nuVar.P0(i, (jLongValue >> 63) ^ (jLongValue << 1));
                i2++;
            }
            return;
        }
        nuVar.M0(i, 2);
        int iN0 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            long jLongValue2 = ((Long) list.get(i3)).longValue();
            iN0 += nu.n0((jLongValue2 >> 63) ^ (jLongValue2 << 1));
        }
        nuVar.O0(iN0);
        while (i2 < list.size()) {
            long jLongValue3 = ((Long) list.get(i2)).longValue();
            nuVar.Q0((jLongValue3 >> 63) ^ (jLongValue3 << 1));
            i2++;
        }
    }

    public static void T(int i, List list, i iVar) {
        if (list == null || list.isEmpty()) {
            return;
        }
        iVar.getClass();
        boolean z = list instanceof q61;
        nu nuVar = iVar.a;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                nuVar.K0(i, (String) list.get(i2));
                i2++;
            }
            return;
        }
        q61 q61Var = (q61) list;
        while (i2 < list.size()) {
            Object objN = q61Var.n(i2);
            if (objN instanceof String) {
                nuVar.K0(i, (String) objN);
            } else {
                nuVar.r0(i, (lk) objN);
            }
            i2++;
        }
    }

    public static void U(int i, List list, i iVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        nu nuVar = iVar.a;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                nuVar.N0(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        nuVar.M0(i, 2);
        int iK0 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iK0 += nu.k0(((Integer) list.get(i3)).intValue());
        }
        nuVar.O0(iK0);
        while (i2 < list.size()) {
            nuVar.O0(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void V(int i, List list, i iVar, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        nu nuVar = iVar.a;
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                nuVar.P0(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        nuVar.M0(i, 2);
        int iN0 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iN0 += nu.n0(((Long) list.get(i3)).longValue());
        }
        nuVar.O0(iN0);
        while (i2 < list.size()) {
            nuVar.Q0(((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public static int a(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return nu.J(i) * size;
    }

    public static int b(List<?> list) {
        return list.size();
    }

    public static int c(int i, List<lk> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iI0 = nu.i0(i) * size;
        for (int i2 = 0; i2 < list.size(); i2++) {
            iI0 += nu.L(list.get(i2));
        }
        return iI0;
    }

    public static int d(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (nu.i0(i) * size) + e(list);
    }

    public static int e(List<Integer> list) {
        int iT;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof b0) {
            b0 b0Var = (b0) list;
            iT = 0;
            while (i < size) {
                iT += nu.T(b0Var.d(i));
                i++;
            }
        } else {
            iT = 0;
            while (i < size) {
                iT += nu.T(list.get(i).intValue());
                i++;
            }
        }
        return iT;
    }

    public static int f(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return nu.O(i) * size;
    }

    public static int g(List<?> list) {
        return list.size() * 4;
    }

    public static int h(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return nu.P(i) * size;
    }

    public static int i(List<?> list) {
        return list.size() * 8;
    }

    public static int j(int i, List<r0> list, b1 b1Var) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iR = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iR += nu.R(i, list.get(i2), b1Var);
        }
        return iR;
    }

    public static int k(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (nu.i0(i) * size) + l(list);
    }

    public static int l(List<Integer> list) {
        int iT;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof b0) {
            b0 b0Var = (b0) list;
            iT = 0;
            while (i < size) {
                iT += nu.T(b0Var.d(i));
                i++;
            }
        } else {
            iT = 0;
            while (i < size) {
                iT += nu.T(list.get(i).intValue());
                i++;
            }
        }
        return iT;
    }

    public static int m(int i, List list) {
        if (list.size() == 0) {
            return 0;
        }
        return (nu.i0(i) * list.size()) + n(list);
    }

    public static int n(List<Long> list) {
        int iN0;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof i0) {
            i0 i0Var = (i0) list;
            iN0 = 0;
            while (i < size) {
                i0Var.c(i);
                iN0 += nu.n0(i0Var.f[i]);
                i++;
            }
        } else {
            iN0 = 0;
            while (i < size) {
                iN0 += nu.n0(list.get(i).longValue());
                i++;
            }
        }
        return iN0;
    }

    public static int o(int i, b1 b1Var, Object obj) {
        if (obj instanceof f0) {
            return nu.V(i, (f0) obj);
        }
        int iI0 = nu.i0(i);
        b bVar = (b) ((r0) obj);
        int iX = bVar.x();
        if (iX == -1) {
            iX = b1Var.h(bVar);
            bVar.A(iX);
        }
        return nu.k0(iX) + iX + iI0;
    }

    public static int p(int i, List<?> list, b1 b1Var) {
        int iK0;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iI0 = nu.i0(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof f0) {
                iK0 = nu.W((f0) obj);
            } else {
                b bVar = (b) ((r0) obj);
                int iX = bVar.x();
                if (iX == -1) {
                    iX = b1Var.h(bVar);
                    bVar.A(iX);
                }
                iK0 = nu.k0(iX) + iX;
            }
            iI0 += iK0;
        }
        return iI0;
    }

    public static int q(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (nu.i0(i) * size) + r(list);
    }

    public static int r(List<Integer> list) {
        int iK0;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof b0) {
            b0 b0Var = (b0) list;
            iK0 = 0;
            while (i < size) {
                int iD = b0Var.d(i);
                iK0 += nu.k0((iD >> 31) ^ (iD << 1));
                i++;
            }
        } else {
            iK0 = 0;
            while (i < size) {
                int iIntValue = list.get(i).intValue();
                iK0 += nu.k0((iIntValue >> 31) ^ (iIntValue << 1));
                i++;
            }
        }
        return iK0;
    }

    public static int s(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (nu.i0(i) * size) + t(list);
    }

    public static int t(List<Long> list) {
        int iN0;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof i0) {
            i0 i0Var = (i0) list;
            iN0 = 0;
            while (i < size) {
                i0Var.c(i);
                long j = i0Var.f[i];
                iN0 += nu.n0((j >> 63) ^ (j << 1));
                i++;
            }
        } else {
            iN0 = 0;
            while (i < size) {
                long jLongValue = list.get(i).longValue();
                iN0 += nu.n0((jLongValue >> 63) ^ (jLongValue << 1));
                i++;
            }
        }
        return iN0;
    }

    public static int u(int i, List<?> list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int iI0 = nu.i0(i) * size;
        if (list instanceof q61) {
            q61 q61Var = (q61) list;
            while (i2 < size) {
                Object objN = q61Var.n(i2);
                iI0 = (objN instanceof lk ? nu.L((lk) objN) : nu.g0((String) objN)) + iI0;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                iI0 = (obj instanceof lk ? nu.L((lk) obj) : nu.g0((String) obj)) + iI0;
                i2++;
            }
        }
        return iI0;
    }

    public static int v(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (nu.i0(i) * size) + w(list);
    }

    public static int w(List<Integer> list) {
        int iK0;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof b0) {
            b0 b0Var = (b0) list;
            iK0 = 0;
            while (i < size) {
                iK0 += nu.k0(b0Var.d(i));
                i++;
            }
        } else {
            iK0 = 0;
            while (i < size) {
                iK0 += nu.k0(list.get(i).intValue());
                i++;
            }
        }
        return iK0;
    }

    public static int x(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (nu.i0(i) * size) + y(list);
    }

    public static int y(List<Long> list) {
        int iN0;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof i0) {
            i0 i0Var = (i0) list;
            iN0 = 0;
            while (i < size) {
                i0Var.c(i);
                iN0 += nu.n0(i0Var.f[i]);
                i++;
            }
        } else {
            iN0 = 0;
            while (i < size) {
                iN0 += nu.n0(list.get(i).longValue());
                i++;
            }
        }
        return iN0;
    }

    public static <UT, UB> UB z(int i, List<Integer> list, c0.b bVar, UB ub, i1<UT, UB> i1Var) {
        if (bVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = list.get(i3).intValue();
                if (bVar.a(iIntValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    ub = (UB) D(i, iIntValue, ub, i1Var);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int iIntValue2 = it.next().intValue();
                if (!bVar.a(iIntValue2)) {
                    ub = (UB) D(i, iIntValue2, ub, i1Var);
                    it.remove();
                }
            }
        }
        return ub;
    }
}
