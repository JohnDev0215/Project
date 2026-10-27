package androidx.datastore.preferences.protobuf;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public abstract class Z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class f11823a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final j0 f11824b;
    public static final j0 c;
    public static final j0 d;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("androidx.datastore.preferences.protobuf.GeneratedMessageV3");
        } catch (Throwable unused) {
            cls = null;
        }
        f11823a = cls;
        f11824b = A(false);
        c = A(true);
        d = new j0();
    }

    public static j0 A(boolean z6) {
        Class<?> cls;
        try {
            cls = Class.forName("androidx.datastore.preferences.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (j0) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z6));
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
    public static void B(j0 j0Var, Object obj, Object obj2) {
        j0Var.getClass();
        AbstractC0957u abstractC0957u = (AbstractC0957u) obj;
        i0 i0Var = abstractC0957u.unknownFields;
        i0 i0Var2 = ((AbstractC0957u) obj2).unknownFields;
        i0 i0Var3 = i0.f11858f;
        if (!i0Var3.equals(i0Var2)) {
            if (i0Var3.equals(i0Var)) {
                int i7 = i0Var.f11859a + i0Var2.f11859a;
                int[] iArrCopyOf = Arrays.copyOf(i0Var.f11860b, i7);
                System.arraycopy(i0Var2.f11860b, 0, iArrCopyOf, i0Var.f11859a, i0Var2.f11859a);
                Object[] objArrCopyOf = Arrays.copyOf(i0Var.c, i7);
                System.arraycopy(i0Var2.c, 0, objArrCopyOf, i0Var.f11859a, i0Var2.f11859a);
                i0Var = new i0(i7, iArrCopyOf, objArrCopyOf, true);
            } else {
                i0Var.getClass();
                if (!i0Var2.equals(i0Var3)) {
                    if (!i0Var.f11861e) {
                        throw new UnsupportedOperationException();
                    }
                    int i10 = i0Var.f11859a + i0Var2.f11859a;
                    i0Var.a(i10);
                    System.arraycopy(i0Var2.f11860b, 0, i0Var.f11860b, i0Var.f11859a, i0Var2.f11859a);
                    System.arraycopy(i0Var2.c, 0, i0Var.c, i0Var.f11859a, i0Var2.f11859a);
                    i0Var.f11859a = i10;
                }
            }
        }
        abstractC0957u.unknownFields = i0Var;
    }

    public static boolean C(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static void D(int i7, List list, G g7, boolean z6) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        C0948k c0948k = (C0948k) g7.f11793a;
        int i10 = 0;
        if (!z6) {
            while (i10 < list.size()) {
                c0948k.M(i7, ((Boolean) list.get(i10)).booleanValue());
                i10++;
            }
            return;
        }
        c0948k.X(i7, 2);
        int i11 = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            ((Boolean) list.get(i12)).getClass();
            Logger logger = C0948k.f11864h;
            i11++;
        }
        c0948k.Z(i11);
        while (i10 < list.size()) {
            c0948k.K(((Boolean) list.get(i10)).booleanValue() ? (byte) 1 : (byte) 0);
            i10++;
        }
    }

    public static void E(int i7, List list, G g7) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        g7.getClass();
        for (int i10 = 0; i10 < list.size(); i10++) {
            ((C0948k) g7.f11793a).N(i7, (C0944g) list.get(i10));
        }
    }

    public static void F(int i7, List list, G g7, boolean z6) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        C0948k c0948k = (C0948k) g7.f11793a;
        int i10 = 0;
        if (!z6) {
            while (i10 < list.size()) {
                double dDoubleValue = ((Double) list.get(i10)).doubleValue();
                c0948k.getClass();
                c0948k.R(Double.doubleToRawLongBits(dDoubleValue), i7);
                i10++;
            }
            return;
        }
        c0948k.X(i7, 2);
        int i11 = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            ((Double) list.get(i12)).getClass();
            Logger logger = C0948k.f11864h;
            i11 += 8;
        }
        c0948k.Z(i11);
        while (i10 < list.size()) {
            c0948k.S(Double.doubleToRawLongBits(((Double) list.get(i10)).doubleValue()));
            i10++;
        }
    }

    public static void G(int i7, List list, G g7, boolean z6) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        C0948k c0948k = (C0948k) g7.f11793a;
        int i10 = 0;
        if (!z6) {
            while (i10 < list.size()) {
                c0948k.T(i7, ((Integer) list.get(i10)).intValue());
                i10++;
            }
            return;
        }
        c0948k.X(i7, 2);
        int iV = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iV += C0948k.v(((Integer) list.get(i11)).intValue());
        }
        c0948k.Z(iV);
        while (i10 < list.size()) {
            c0948k.U(((Integer) list.get(i10)).intValue());
            i10++;
        }
    }

    public static void H(int i7, List list, G g7, boolean z6) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        C0948k c0948k = (C0948k) g7.f11793a;
        int i10 = 0;
        if (!z6) {
            while (i10 < list.size()) {
                c0948k.P(i7, ((Integer) list.get(i10)).intValue());
                i10++;
            }
            return;
        }
        c0948k.X(i7, 2);
        int i11 = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            ((Integer) list.get(i12)).getClass();
            Logger logger = C0948k.f11864h;
            i11 += 4;
        }
        c0948k.Z(i11);
        while (i10 < list.size()) {
            c0948k.Q(((Integer) list.get(i10)).intValue());
            i10++;
        }
    }

    public static void I(int i7, List list, G g7, boolean z6) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        C0948k c0948k = (C0948k) g7.f11793a;
        int i10 = 0;
        if (!z6) {
            while (i10 < list.size()) {
                c0948k.R(((Long) list.get(i10)).longValue(), i7);
                i10++;
            }
            return;
        }
        c0948k.X(i7, 2);
        int i11 = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            ((Long) list.get(i12)).getClass();
            Logger logger = C0948k.f11864h;
            i11 += 8;
        }
        c0948k.Z(i11);
        while (i10 < list.size()) {
            c0948k.S(((Long) list.get(i10)).longValue());
            i10++;
        }
    }

    public static void J(int i7, List list, G g7, boolean z6) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        C0948k c0948k = (C0948k) g7.f11793a;
        int i10 = 0;
        if (!z6) {
            while (i10 < list.size()) {
                float fFloatValue = ((Float) list.get(i10)).floatValue();
                c0948k.getClass();
                c0948k.P(i7, Float.floatToRawIntBits(fFloatValue));
                i10++;
            }
            return;
        }
        c0948k.X(i7, 2);
        int i11 = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            ((Float) list.get(i12)).getClass();
            Logger logger = C0948k.f11864h;
            i11 += 4;
        }
        c0948k.Z(i11);
        while (i10 < list.size()) {
            c0948k.Q(Float.floatToRawIntBits(((Float) list.get(i10)).floatValue()));
            i10++;
        }
    }

    public static void K(int i7, List list, G g7, Y y10) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        g7.getClass();
        for (int i10 = 0; i10 < list.size(); i10++) {
            g7.h(i7, list.get(i10), y10);
        }
    }

    public static void L(int i7, List list, G g7, boolean z6) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        C0948k c0948k = (C0948k) g7.f11793a;
        int i10 = 0;
        if (!z6) {
            while (i10 < list.size()) {
                c0948k.T(i7, ((Integer) list.get(i10)).intValue());
                i10++;
            }
            return;
        }
        c0948k.X(i7, 2);
        int iV = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iV += C0948k.v(((Integer) list.get(i11)).intValue());
        }
        c0948k.Z(iV);
        while (i10 < list.size()) {
            c0948k.U(((Integer) list.get(i10)).intValue());
            i10++;
        }
    }

    public static void M(int i7, List list, G g7, boolean z6) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        C0948k c0948k = (C0948k) g7.f11793a;
        int i10 = 0;
        if (!z6) {
            while (i10 < list.size()) {
                c0948k.a0(((Long) list.get(i10)).longValue(), i7);
                i10++;
            }
            return;
        }
        c0948k.X(i7, 2);
        int iH = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iH += C0948k.H(((Long) list.get(i11)).longValue());
        }
        c0948k.Z(iH);
        while (i10 < list.size()) {
            c0948k.b0(((Long) list.get(i10)).longValue());
            i10++;
        }
    }

    public static void N(int i7, List list, G g7, Y y10) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        g7.getClass();
        for (int i10 = 0; i10 < list.size(); i10++) {
            g7.k(i7, list.get(i10), y10);
        }
    }

    public static void O(int i7, List list, G g7, boolean z6) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        C0948k c0948k = (C0948k) g7.f11793a;
        int i10 = 0;
        if (!z6) {
            while (i10 < list.size()) {
                c0948k.P(i7, ((Integer) list.get(i10)).intValue());
                i10++;
            }
            return;
        }
        c0948k.X(i7, 2);
        int i11 = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            ((Integer) list.get(i12)).getClass();
            Logger logger = C0948k.f11864h;
            i11 += 4;
        }
        c0948k.Z(i11);
        while (i10 < list.size()) {
            c0948k.Q(((Integer) list.get(i10)).intValue());
            i10++;
        }
    }

    public static void P(int i7, List list, G g7, boolean z6) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        C0948k c0948k = (C0948k) g7.f11793a;
        int i10 = 0;
        if (!z6) {
            while (i10 < list.size()) {
                c0948k.R(((Long) list.get(i10)).longValue(), i7);
                i10++;
            }
            return;
        }
        c0948k.X(i7, 2);
        int i11 = 0;
        for (int i12 = 0; i12 < list.size(); i12++) {
            ((Long) list.get(i12)).getClass();
            Logger logger = C0948k.f11864h;
            i11 += 8;
        }
        c0948k.Z(i11);
        while (i10 < list.size()) {
            c0948k.S(((Long) list.get(i10)).longValue());
            i10++;
        }
    }

    public static void Q(int i7, List list, G g7, boolean z6) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        C0948k c0948k = (C0948k) g7.f11793a;
        int i10 = 0;
        if (!z6) {
            while (i10 < list.size()) {
                int iIntValue = ((Integer) list.get(i10)).intValue();
                c0948k.Y(i7, (iIntValue >> 31) ^ (iIntValue << 1));
                i10++;
            }
            return;
        }
        c0948k.X(i7, 2);
        int iF = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            int iIntValue2 = ((Integer) list.get(i11)).intValue();
            iF += C0948k.F((iIntValue2 >> 31) ^ (iIntValue2 << 1));
        }
        c0948k.Z(iF);
        while (i10 < list.size()) {
            int iIntValue3 = ((Integer) list.get(i10)).intValue();
            c0948k.Z((iIntValue3 >> 31) ^ (iIntValue3 << 1));
            i10++;
        }
    }

    public static void R(int i7, List list, G g7, boolean z6) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        C0948k c0948k = (C0948k) g7.f11793a;
        int i10 = 0;
        if (!z6) {
            while (i10 < list.size()) {
                long jLongValue = ((Long) list.get(i10)).longValue();
                c0948k.a0((jLongValue >> 63) ^ (jLongValue << 1), i7);
                i10++;
            }
            return;
        }
        c0948k.X(i7, 2);
        int iH = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            long jLongValue2 = ((Long) list.get(i11)).longValue();
            iH += C0948k.H((jLongValue2 >> 63) ^ (jLongValue2 << 1));
        }
        c0948k.Z(iH);
        while (i10 < list.size()) {
            long jLongValue3 = ((Long) list.get(i10)).longValue();
            c0948k.b0((jLongValue3 >> 63) ^ (jLongValue3 << 1));
            i10++;
        }
    }

    public static void S(int i7, List list, G g7) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        g7.getClass();
        boolean z6 = list instanceof B;
        C0948k c0948k = (C0948k) g7.f11793a;
        int i10 = 0;
        if (!z6) {
            while (i10 < list.size()) {
                c0948k.V(i7, (String) list.get(i10));
                i10++;
            }
            return;
        }
        B b10 = (B) list;
        while (i10 < list.size()) {
            Object objN = b10.n(i10);
            if (objN instanceof String) {
                c0948k.V(i7, (String) objN);
            } else {
                c0948k.N(i7, (C0944g) objN);
            }
            i10++;
        }
    }

    public static void T(int i7, List list, G g7, boolean z6) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        C0948k c0948k = (C0948k) g7.f11793a;
        int i10 = 0;
        if (!z6) {
            while (i10 < list.size()) {
                c0948k.Y(i7, ((Integer) list.get(i10)).intValue());
                i10++;
            }
            return;
        }
        c0948k.X(i7, 2);
        int iF = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iF += C0948k.F(((Integer) list.get(i11)).intValue());
        }
        c0948k.Z(iF);
        while (i10 < list.size()) {
            c0948k.Z(((Integer) list.get(i10)).intValue());
            i10++;
        }
    }

    public static void U(int i7, List list, G g7, boolean z6) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        C0948k c0948k = (C0948k) g7.f11793a;
        int i10 = 0;
        if (!z6) {
            while (i10 < list.size()) {
                c0948k.a0(((Long) list.get(i10)).longValue(), i7);
                i10++;
            }
            return;
        }
        c0948k.X(i7, 2);
        int iH = 0;
        for (int i11 = 0; i11 < list.size(); i11++) {
            iH += C0948k.H(((Long) list.get(i11)).longValue());
        }
        c0948k.Z(iH);
        while (i10 < list.size()) {
            c0948k.b0(((Long) list.get(i10)).longValue());
            i10++;
        }
    }

    public static int a(int i7, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return C0948k.l(i7) * size;
    }

    public static int b(List list) {
        return list.size();
    }

    public static int c(int i7, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iD = C0948k.D(i7) * size;
        for (int i10 = 0; i10 < list.size(); i10++) {
            iD += C0948k.n((C0944g) list.get(i10));
        }
        return iD;
    }

    public static int d(int i7, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (C0948k.D(i7) * size) + e(list);
    }

    public static int e(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iV = 0;
        for (int i7 = 0; i7 < size; i7++) {
            iV += C0948k.v(((Integer) list.get(i7)).intValue());
        }
        return iV;
    }

    public static int f(int i7, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return C0948k.q(i7) * size;
    }

    public static int g(List list) {
        return list.size() * 4;
    }

    public static int h(int i7, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return C0948k.r(i7) * size;
    }

    public static int i(List list) {
        return list.size() * 8;
    }

    public static int j(int i7, List list, Y y10) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iT = 0;
        for (int i10 = 0; i10 < size; i10++) {
            iT += C0948k.t(i7, (AbstractC0937a) list.get(i10), y10);
        }
        return iT;
    }

    public static int k(int i7, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (C0948k.D(i7) * size) + l(list);
    }

    public static int l(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iV = 0;
        for (int i7 = 0; i7 < size; i7++) {
            iV += C0948k.v(((Integer) list.get(i7)).intValue());
        }
        return iV;
    }

    public static int m(int i7, List list) {
        if (list.size() == 0) {
            return 0;
        }
        return (C0948k.D(i7) * list.size()) + n(list);
    }

    public static int n(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iH = 0;
        for (int i7 = 0; i7 < size; i7++) {
            iH += C0948k.H(((Long) list.get(i7)).longValue());
        }
        return iH;
    }

    public static int o(int i7, Object obj, Y y10) {
        int iD = C0948k.D(i7);
        int iA = ((AbstractC0937a) obj).a(y10);
        return C0948k.F(iA) + iA + iD;
    }

    public static int p(int i7, List list, Y y10) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iD = C0948k.D(i7) * size;
        for (int i10 = 0; i10 < size; i10++) {
            int iA = ((AbstractC0937a) list.get(i10)).a(y10);
            iD += C0948k.F(iA) + iA;
        }
        return iD;
    }

    public static int q(int i7, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (C0948k.D(i7) * size) + r(list);
    }

    public static int r(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iF = 0;
        for (int i7 = 0; i7 < size; i7++) {
            int iIntValue = ((Integer) list.get(i7)).intValue();
            iF += C0948k.F((iIntValue >> 31) ^ (iIntValue << 1));
        }
        return iF;
    }

    public static int s(int i7, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (C0948k.D(i7) * size) + t(list);
    }

    public static int t(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iH = 0;
        for (int i7 = 0; i7 < size; i7++) {
            long jLongValue = ((Long) list.get(i7)).longValue();
            iH += C0948k.H((jLongValue >> 63) ^ (jLongValue << 1));
        }
        return iH;
    }

    public static int u(int i7, List list) {
        int size = list.size();
        int i10 = 0;
        if (size == 0) {
            return 0;
        }
        int iD = C0948k.D(i7) * size;
        if (list instanceof B) {
            B b10 = (B) list;
            while (i10 < size) {
                Object objN = b10.n(i10);
                iD = (objN instanceof C0944g ? C0948k.n((C0944g) objN) : C0948k.C((String) objN)) + iD;
                i10++;
            }
        } else {
            while (i10 < size) {
                Object obj = list.get(i10);
                iD = (obj instanceof C0944g ? C0948k.n((C0944g) obj) : C0948k.C((String) obj)) + iD;
                i10++;
            }
        }
        return iD;
    }

    public static int v(int i7, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (C0948k.D(i7) * size) + w(list);
    }

    public static int w(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iF = 0;
        for (int i7 = 0; i7 < size; i7++) {
            iF += C0948k.F(((Integer) list.get(i7)).intValue());
        }
        return iF;
    }

    public static int x(int i7, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (C0948k.D(i7) * size) + y(list);
    }

    public static int y(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iH = 0;
        for (int i7 = 0; i7 < size; i7++) {
            iH += C0948k.H(((Long) list.get(i7)).longValue());
        }
        return iH;
    }

    public static Object z(Object obj, int i7, List list, Object obj2, j0 j0Var) {
        return obj2;
    }
}
