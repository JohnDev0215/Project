package com.google.protobuf;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.logging.Logger;
import p.b91;
import p.e51;
import p.k72;
import p.p01;
import p.p51;
import p.vd1;
import p.wj1;

/* JADX INFO: loaded from: classes.dex */
public abstract class m {
    public static final Class a;
    public static final n b;
    public static final n c;
    public static final p d;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessageV3");
        } catch (Throwable unused) {
            cls = null;
        }
        a = cls;
        b = A(false);
        c = A(true);
        d = new p();
    }

    public static n A(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (n) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused2) {
            return null;
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static void B(n nVar, Object obj, Object obj2) {
        ((p) nVar).getClass();
        i iVar = (i) obj;
        o oVarE = iVar.unknownFields;
        o oVar = ((i) obj2).unknownFields;
        o oVar2 = o.f;
        if (!oVar2.equals(oVar)) {
            if (oVar2.equals(oVarE)) {
                oVarE = o.e(oVarE, oVar);
            } else {
                oVarE.getClass();
                if (!oVar.equals(oVar2)) {
                    oVarE.a();
                    int i = oVarE.a + oVar.a;
                    oVarE.b(i);
                    System.arraycopy(oVar.b, 0, oVarE.b, oVarE.a, oVar.a);
                    System.arraycopy(oVar.c, 0, oVarE.c, oVarE.a, oVar.a);
                    oVarE.a = i;
                }
            }
        }
        iVar.unknownFields = oVarE;
    }

    public static boolean C(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static Object D(Object obj, int i, int i2, Object obj2, n nVar) {
        if (obj2 == null) {
            obj2 = nVar.a(obj);
        }
        ((p) nVar).getClass();
        ((o) obj2).f(i << 3, Long.valueOf(i2));
        return obj2;
    }

    public static void E(int i, List list, p01 p01Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i2 = 0;
        if (!z) {
            p01Var.getClass();
            while (i2 < list.size()) {
                ((h) p01Var.e).S(i, ((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
            return;
        }
        ((h) p01Var.e).g0(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Boolean) list.get(i4)).booleanValue();
            Logger logger = h.h;
            i3++;
        }
        ((h) p01Var.e).i0(i3);
        while (i2 < list.size()) {
            ((h) p01Var.e).R(((Boolean) list.get(i2)).booleanValue() ? (byte) 1 : (byte) 0);
            i2++;
        }
    }

    public static void F(int i, List list, p01 p01Var) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p01Var.getClass();
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((h) p01Var.e).U(i, (ByteString) list.get(i2));
        }
    }

    public static void G(int i, List list, p01 p01Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i2 = 0;
        if (!z) {
            p01Var.getClass();
            while (i2 < list.size()) {
                h hVar = (h) p01Var.e;
                double dDoubleValue = ((Double) list.get(i2)).doubleValue();
                hVar.getClass();
                hVar.Y(i, Double.doubleToRawLongBits(dDoubleValue));
                i2++;
            }
            return;
        }
        ((h) p01Var.e).g0(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Double) list.get(i4)).doubleValue();
            Logger logger = h.h;
            i3 += 8;
        }
        ((h) p01Var.e).i0(i3);
        while (i2 < list.size()) {
            h hVar2 = (h) p01Var.e;
            double dDoubleValue2 = ((Double) list.get(i2)).doubleValue();
            hVar2.getClass();
            hVar2.Z(Double.doubleToRawLongBits(dDoubleValue2));
            i2++;
        }
    }

    public static void H(int i, List list, p01 p01Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i2 = 0;
        if (!z) {
            p01Var.getClass();
            while (i2 < list.size()) {
                ((h) p01Var.e).a0(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        ((h) p01Var.e).g0(i, 2);
        int iD = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iD += h.D(((Integer) list.get(i3)).intValue());
        }
        ((h) p01Var.e).i0(iD);
        while (i2 < list.size()) {
            ((h) p01Var.e).b0(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void I(int i, List list, p01 p01Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i2 = 0;
        if (!z) {
            p01Var.getClass();
            while (i2 < list.size()) {
                ((h) p01Var.e).W(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        ((h) p01Var.e).g0(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Integer) list.get(i4)).intValue();
            Logger logger = h.h;
            i3 += 4;
        }
        ((h) p01Var.e).i0(i3);
        while (i2 < list.size()) {
            ((h) p01Var.e).X(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void J(int i, List list, p01 p01Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i2 = 0;
        if (!z) {
            p01Var.getClass();
            while (i2 < list.size()) {
                ((h) p01Var.e).Y(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        ((h) p01Var.e).g0(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Long) list.get(i4)).longValue();
            Logger logger = h.h;
            i3 += 8;
        }
        ((h) p01Var.e).i0(i3);
        while (i2 < list.size()) {
            ((h) p01Var.e).Z(((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public static void K(int i, List list, p01 p01Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i2 = 0;
        if (!z) {
            p01Var.getClass();
            while (i2 < list.size()) {
                h hVar = (h) p01Var.e;
                float fFloatValue = ((Float) list.get(i2)).floatValue();
                hVar.getClass();
                hVar.W(i, Float.floatToRawIntBits(fFloatValue));
                i2++;
            }
            return;
        }
        ((h) p01Var.e).g0(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Float) list.get(i4)).floatValue();
            Logger logger = h.h;
            i3 += 4;
        }
        ((h) p01Var.e).i0(i3);
        while (i2 < list.size()) {
            h hVar2 = (h) p01Var.e;
            float fFloatValue2 = ((Float) list.get(i2)).floatValue();
            hVar2.getClass();
            hVar2.X(Float.floatToRawIntBits(fFloatValue2));
            i2++;
        }
    }

    public static void L(int i, List list, p01 p01Var, k72 k72Var) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p01Var.getClass();
        for (int i2 = 0; i2 < list.size(); i2++) {
            p01Var.R(i, k72Var, list.get(i2));
        }
    }

    public static void M(int i, List list, p01 p01Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i2 = 0;
        if (!z) {
            p01Var.getClass();
            while (i2 < list.size()) {
                ((h) p01Var.e).a0(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        ((h) p01Var.e).g0(i, 2);
        int iD = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iD += h.D(((Integer) list.get(i3)).intValue());
        }
        ((h) p01Var.e).i0(iD);
        while (i2 < list.size()) {
            ((h) p01Var.e).b0(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void N(int i, List list, p01 p01Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i2 = 0;
        if (!z) {
            p01Var.getClass();
            while (i2 < list.size()) {
                ((h) p01Var.e).j0(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        ((h) p01Var.e).g0(i, 2);
        int iP = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iP += h.P(((Long) list.get(i3)).longValue());
        }
        ((h) p01Var.e).i0(iP);
        while (i2 < list.size()) {
            ((h) p01Var.e).k0(((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public static void O(int i, List list, p01 p01Var, k72 k72Var) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p01Var.getClass();
        for (int i2 = 0; i2 < list.size(); i2++) {
            p01Var.U(i, k72Var, list.get(i2));
        }
    }

    public static void P(int i, List list, p01 p01Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i2 = 0;
        if (!z) {
            p01Var.getClass();
            while (i2 < list.size()) {
                ((h) p01Var.e).W(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        ((h) p01Var.e).g0(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Integer) list.get(i4)).intValue();
            Logger logger = h.h;
            i3 += 4;
        }
        ((h) p01Var.e).i0(i3);
        while (i2 < list.size()) {
            ((h) p01Var.e).X(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void Q(int i, List list, p01 p01Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i2 = 0;
        if (!z) {
            p01Var.getClass();
            while (i2 < list.size()) {
                ((h) p01Var.e).Y(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        ((h) p01Var.e).g0(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Long) list.get(i4)).longValue();
            Logger logger = h.h;
            i3 += 8;
        }
        ((h) p01Var.e).i0(i3);
        while (i2 < list.size()) {
            ((h) p01Var.e).Z(((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public static void R(int i, List list, p01 p01Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i2 = 0;
        if (!z) {
            p01Var.getClass();
            while (i2 < list.size()) {
                h hVar = (h) p01Var.e;
                int iIntValue = ((Integer) list.get(i2)).intValue();
                hVar.h0(i, (iIntValue >> 31) ^ (iIntValue << 1));
                i2++;
            }
            return;
        }
        ((h) p01Var.e).g0(i, 2);
        int iN = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            int iIntValue2 = ((Integer) list.get(i3)).intValue();
            iN += h.N((iIntValue2 >> 31) ^ (iIntValue2 << 1));
        }
        ((h) p01Var.e).i0(iN);
        while (i2 < list.size()) {
            h hVar2 = (h) p01Var.e;
            int iIntValue3 = ((Integer) list.get(i2)).intValue();
            hVar2.i0((iIntValue3 >> 31) ^ (iIntValue3 << 1));
            i2++;
        }
    }

    public static void S(int i, List list, p01 p01Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i2 = 0;
        if (!z) {
            p01Var.getClass();
            while (i2 < list.size()) {
                h hVar = (h) p01Var.e;
                long jLongValue = ((Long) list.get(i2)).longValue();
                hVar.j0(i, (jLongValue >> 63) ^ (jLongValue << 1));
                i2++;
            }
            return;
        }
        ((h) p01Var.e).g0(i, 2);
        int iP = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            long jLongValue2 = ((Long) list.get(i3)).longValue();
            iP += h.P((jLongValue2 >> 63) ^ (jLongValue2 << 1));
        }
        ((h) p01Var.e).i0(iP);
        while (i2 < list.size()) {
            h hVar2 = (h) p01Var.e;
            long jLongValue3 = ((Long) list.get(i2)).longValue();
            hVar2.k0((jLongValue3 >> 63) ^ (jLongValue3 << 1));
            i2++;
        }
    }

    public static void T(int i, List list, p01 p01Var) {
        if (list == null || list.isEmpty()) {
            return;
        }
        p01Var.getClass();
        int i2 = 0;
        if (!(list instanceof b91)) {
            while (i2 < list.size()) {
                ((h) p01Var.e).e0(i, (String) list.get(i2));
                i2++;
            }
            return;
        }
        b91 b91Var = (b91) list;
        while (i2 < list.size()) {
            Object objK = b91Var.k(i2);
            if (objK instanceof String) {
                ((h) p01Var.e).e0(i, (String) objK);
            } else {
                ((h) p01Var.e).U(i, (ByteString) objK);
            }
            i2++;
        }
    }

    public static void U(int i, List list, p01 p01Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i2 = 0;
        if (!z) {
            p01Var.getClass();
            while (i2 < list.size()) {
                ((h) p01Var.e).h0(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        ((h) p01Var.e).g0(i, 2);
        int iN = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iN += h.N(((Integer) list.get(i3)).intValue());
        }
        ((h) p01Var.e).i0(iN);
        while (i2 < list.size()) {
            ((h) p01Var.e).i0(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void V(int i, List list, p01 p01Var, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i2 = 0;
        if (!z) {
            p01Var.getClass();
            while (i2 < list.size()) {
                ((h) p01Var.e).j0(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        ((h) p01Var.e).g0(i, 2);
        int iP = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iP += h.P(((Long) list.get(i3)).longValue());
        }
        ((h) p01Var.e).i0(iP);
        while (i2 < list.size()) {
            ((h) p01Var.e).k0(((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public static int a(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return h.u(i) * size;
    }

    public static int b(List list) {
        return list.size();
    }

    public static int c(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iL = h.L(i) * size;
        for (int i2 = 0; i2 < list.size(); i2++) {
            int size2 = ((ByteString) list.get(i2)).size();
            iL += h.N(size2) + size2;
        }
        return iL;
    }

    public static int d(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (h.L(i) * size) + e(list);
    }

    public static int e(List list) {
        int iD;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof e51) {
            e51 e51Var = (e51) list;
            iD = 0;
            while (i < size) {
                iD += h.D(e51Var.e(i));
                i++;
            }
        } else {
            iD = 0;
            while (i < size) {
                iD += h.D(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iD;
    }

    public static int f(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return h.y(i) * size;
    }

    public static int g(List list) {
        return list.size() * 4;
    }

    public static int h(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return h.z(i) * size;
    }

    public static int i(List list) {
        return list.size() * 8;
    }

    public static int j(int i, List list, k72 k72Var) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iB = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iB += h.B(i, (wj1) list.get(i2), k72Var);
        }
        return iB;
    }

    public static int k(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (h.L(i) * size) + l(list);
    }

    public static int l(List list) {
        int iD;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof e51) {
            e51 e51Var = (e51) list;
            iD = 0;
            while (i < size) {
                iD += h.D(e51Var.e(i));
                i++;
            }
        } else {
            iD = 0;
            while (i < size) {
                iD += h.D(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iD;
    }

    public static int m(int i, List list) {
        if (list.size() == 0) {
            return 0;
        }
        return (h.L(i) * list.size()) + n(list);
    }

    public static int n(List list) {
        int iP;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof vd1) {
            vd1 vd1Var = (vd1) list;
            iP = 0;
            while (i < size) {
                vd1Var.d(i);
                iP += h.P(vd1Var.e[i]);
                i++;
            }
        } else {
            iP = 0;
            while (i < size) {
                iP += h.P(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return iP;
    }

    public static int o(int i, k72 k72Var, Object obj) {
        int iL = h.L(i);
        int serializedSize = ((a) ((wj1) obj)).getSerializedSize(k72Var);
        return h.N(serializedSize) + serializedSize + iL;
    }

    public static int p(int i, List list, k72 k72Var) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iL = h.L(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            int serializedSize = ((a) ((wj1) list.get(i2))).getSerializedSize(k72Var);
            iL += h.N(serializedSize) + serializedSize;
        }
        return iL;
    }

    public static int q(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (h.L(i) * size) + r(list);
    }

    public static int r(List list) {
        int iN;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof e51) {
            e51 e51Var = (e51) list;
            iN = 0;
            while (i < size) {
                int iE = e51Var.e(i);
                iN += h.N((iE >> 31) ^ (iE << 1));
                i++;
            }
        } else {
            iN = 0;
            while (i < size) {
                int iIntValue = ((Integer) list.get(i)).intValue();
                iN += h.N((iIntValue >> 31) ^ (iIntValue << 1));
                i++;
            }
        }
        return iN;
    }

    public static int s(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (h.L(i) * size) + t(list);
    }

    public static int t(List list) {
        int iP;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof vd1) {
            vd1 vd1Var = (vd1) list;
            iP = 0;
            while (i < size) {
                vd1Var.d(i);
                long j = vd1Var.e[i];
                iP += h.P((j >> 63) ^ (j << 1));
                i++;
            }
        } else {
            iP = 0;
            while (i < size) {
                long jLongValue = ((Long) list.get(i)).longValue();
                iP += h.P((jLongValue >> 63) ^ (jLongValue << 1));
                i++;
            }
        }
        return iP;
    }

    public static int u(int i, List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int iL = h.L(i) * size;
        if (list instanceof b91) {
            b91 b91Var = (b91) list;
            while (i2 < size) {
                Object objK = b91Var.k(i2);
                if (objK instanceof ByteString) {
                    int size2 = ((ByteString) objK).size();
                    iL = h.N(size2) + size2 + iL;
                } else {
                    iL = h.K((String) objK) + iL;
                }
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof ByteString) {
                    int size3 = ((ByteString) obj).size();
                    iL = h.N(size3) + size3 + iL;
                } else {
                    iL = h.K((String) obj) + iL;
                }
                i2++;
            }
        }
        return iL;
    }

    public static int v(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (h.L(i) * size) + w(list);
    }

    public static int w(List list) {
        int iN;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof e51) {
            e51 e51Var = (e51) list;
            iN = 0;
            while (i < size) {
                iN += h.N(e51Var.e(i));
                i++;
            }
        } else {
            iN = 0;
            while (i < size) {
                iN += h.N(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iN;
    }

    public static int x(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (h.L(i) * size) + y(list);
    }

    public static int y(List list) {
        int iP;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof vd1) {
            vd1 vd1Var = (vd1) list;
            iP = 0;
            while (i < size) {
                vd1Var.d(i);
                iP += h.P(vd1Var.e[i]);
                i++;
            }
        } else {
            iP = 0;
            while (i < size) {
                iP += h.P(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return iP;
    }

    public static Object z(Object obj, int i, List list, p51 p51Var, Object obj2, n nVar) {
        if (p51Var == null) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = ((Integer) list.get(i3)).intValue();
                if (p51Var.isInRange(iIntValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    obj2 = D(obj, i, iIntValue, obj2, nVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int iIntValue2 = ((Integer) it.next()).intValue();
                if (!p51Var.isInRange(iIntValue2)) {
                    obj2 = D(obj, i, iIntValue2, obj2, nVar);
                    it.remove();
                }
            }
        }
        return obj2;
    }
}
