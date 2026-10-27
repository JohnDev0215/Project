package defpackage;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;

/* JADX INFO: renamed from: om, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1288om {
    public static final Class a;
    public static final AbstractC1507sm b;
    public static final AbstractC1507sm c;
    public static final AbstractC1507sm d;

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
        d = new C1617um();
    }

    public static AbstractC1507sm A(boolean z) {
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
            return (AbstractC1507sm) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused2) {
            return null;
        }
    }

    public static void B(InterfaceC0388Vl interfaceC0388Vl, Object obj, Object obj2, long j) {
        AbstractC0066Dm.f.q(obj, j, ((C0406Wl) interfaceC0388Vl).b(AbstractC0066Dm.n(obj, j), AbstractC0066Dm.n(obj2, j)));
    }

    public static void C(AbstractC1507sm abstractC1507sm, Object obj, Object obj2) {
        Objects.requireNonNull((C1617um) abstractC1507sm);
        AbstractC0065Dl abstractC0065Dl = (AbstractC0065Dl) obj;
        C1562tm c1562tm = abstractC0065Dl.unknownFields;
        C1562tm c1562tm2 = ((AbstractC0065Dl) obj2).unknownFields;
        if (!c1562tm2.equals(C1562tm.f)) {
            int i = c1562tm.a + c1562tm2.a;
            int[] iArrCopyOf = Arrays.copyOf(c1562tm.b, i);
            System.arraycopy(c1562tm2.b, 0, iArrCopyOf, c1562tm.a, c1562tm2.a);
            Object[] objArrCopyOf = Arrays.copyOf(c1562tm.c, i);
            System.arraycopy(c1562tm2.c, 0, objArrCopyOf, c1562tm.a, c1562tm2.a);
            c1562tm = new C1562tm(i, iArrCopyOf, objArrCopyOf, true);
        }
        abstractC0065Dl.unknownFields = c1562tm;
    }

    public static void D(Class cls) {
        Class cls2;
        if (!AbstractC0065Dl.class.isAssignableFrom(cls) && (cls2 = a) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static boolean E(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static Object F(int i, int i2, Object obj, AbstractC1507sm abstractC1507sm) {
        if (obj == null) {
            obj = abstractC1507sm.d();
        }
        Objects.requireNonNull((C1617um) abstractC1507sm);
        ((C1562tm) obj).b((i << 3) | 0, Long.valueOf(i2));
        return obj;
    }

    public static void G(int i, List list, InterfaceC0192Km interfaceC0192Km, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                c1397ql.a.G(i, ((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
            return;
        }
        c1397ql.a.Q(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Boolean) list.get(i4)).booleanValue();
            i3++;
        }
        c1397ql.a.S(i3);
        while (i2 < list.size()) {
            c1397ql.a.F(((Boolean) list.get(i2)).booleanValue() ? (byte) 1 : (byte) 0);
            i2++;
        }
    }

    public static void H(int i, List list, InterfaceC0192Km interfaceC0192Km) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        for (int i2 = 0; i2 < list.size(); i2++) {
            c1397ql.a.H(i, (AbstractC0731el) list.get(i2));
        }
    }

    public static void I(int i, List list, InterfaceC0192Km interfaceC0192Km, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                AbstractC1342pl abstractC1342pl = c1397ql.a;
                double dDoubleValue = ((Double) list.get(i2)).doubleValue();
                Objects.requireNonNull(abstractC1342pl);
                abstractC1342pl.K(i, Double.doubleToRawLongBits(dDoubleValue));
                i2++;
            }
            return;
        }
        c1397ql.a.Q(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Double) list.get(i4)).doubleValue();
            i3 += 8;
        }
        c1397ql.a.S(i3);
        while (i2 < list.size()) {
            AbstractC1342pl abstractC1342pl2 = c1397ql.a;
            double dDoubleValue2 = ((Double) list.get(i2)).doubleValue();
            Objects.requireNonNull(abstractC1342pl2);
            abstractC1342pl2.L(Double.doubleToRawLongBits(dDoubleValue2));
            i2++;
        }
    }

    public static void J(int i, List list, InterfaceC0192Km interfaceC0192Km, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                c1397ql.a.M(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        c1397ql.a.Q(i, 2);
        int iL = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iL += AbstractC1342pl.l(((Integer) list.get(i3)).intValue());
        }
        c1397ql.a.S(iL);
        while (i2 < list.size()) {
            c1397ql.a.N(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void K(int i, List list, InterfaceC0192Km interfaceC0192Km, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                c1397ql.a.I(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        c1397ql.a.Q(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Integer) list.get(i4)).intValue();
            i3 += 4;
        }
        c1397ql.a.S(i3);
        while (i2 < list.size()) {
            c1397ql.a.J(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void L(int i, List list, InterfaceC0192Km interfaceC0192Km, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                c1397ql.a.K(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        c1397ql.a.Q(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Long) list.get(i4)).longValue();
            i3 += 8;
        }
        c1397ql.a.S(i3);
        while (i2 < list.size()) {
            c1397ql.a.L(((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public static void M(int i, List list, InterfaceC0192Km interfaceC0192Km, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                AbstractC1342pl abstractC1342pl = c1397ql.a;
                float fFloatValue = ((Float) list.get(i2)).floatValue();
                Objects.requireNonNull(abstractC1342pl);
                abstractC1342pl.I(i, Float.floatToRawIntBits(fFloatValue));
                i2++;
            }
            return;
        }
        c1397ql.a.Q(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Float) list.get(i4)).floatValue();
            i3 += 4;
        }
        c1397ql.a.S(i3);
        while (i2 < list.size()) {
            AbstractC1342pl abstractC1342pl2 = c1397ql.a;
            float fFloatValue2 = ((Float) list.get(i2)).floatValue();
            Objects.requireNonNull(abstractC1342pl2);
            abstractC1342pl2.J(Float.floatToRawIntBits(fFloatValue2));
            i2++;
        }
    }

    public static void N(int i, List list, InterfaceC0192Km interfaceC0192Km, InterfaceC1178mm interfaceC1178mm) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        for (int i2 = 0; i2 < list.size(); i2++) {
            c1397ql.c(i, list.get(i2), interfaceC1178mm);
        }
    }

    public static void O(int i, List list, InterfaceC0192Km interfaceC0192Km, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                c1397ql.a.M(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        c1397ql.a.Q(i, 2);
        int iL = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iL += AbstractC1342pl.l(((Integer) list.get(i3)).intValue());
        }
        c1397ql.a.S(iL);
        while (i2 < list.size()) {
            c1397ql.a.N(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void P(int i, List list, InterfaceC0192Km interfaceC0192Km, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                c1397ql.a.T(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        c1397ql.a.Q(i, 2);
        int iB = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iB += AbstractC1342pl.B(((Long) list.get(i3)).longValue());
        }
        c1397ql.a.S(iB);
        while (i2 < list.size()) {
            c1397ql.a.U(((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public static void Q(int i, List list, InterfaceC0192Km interfaceC0192Km, InterfaceC1178mm interfaceC1178mm) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        for (int i2 = 0; i2 < list.size(); i2++) {
            c1397ql.a.O(i, (InterfaceC0509am) list.get(i2), interfaceC1178mm);
        }
    }

    public static void R(int i, List list, InterfaceC0192Km interfaceC0192Km, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                c1397ql.a.I(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        c1397ql.a.Q(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Integer) list.get(i4)).intValue();
            i3 += 4;
        }
        c1397ql.a.S(i3);
        while (i2 < list.size()) {
            c1397ql.a.J(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void S(int i, List list, InterfaceC0192Km interfaceC0192Km, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                c1397ql.a.K(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        c1397ql.a.Q(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Long) list.get(i4)).longValue();
            i3 += 8;
        }
        c1397ql.a.S(i3);
        while (i2 < list.size()) {
            c1397ql.a.L(((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public static void T(int i, List list, InterfaceC0192Km interfaceC0192Km, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                c1397ql.a.R(i, AbstractC1342pl.C(((Integer) list.get(i2)).intValue()));
                i2++;
            }
            return;
        }
        c1397ql.a.Q(i, 2);
        int iS = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iS += AbstractC1342pl.s(((Integer) list.get(i3)).intValue());
        }
        c1397ql.a.S(iS);
        while (i2 < list.size()) {
            c1397ql.a.S(AbstractC1342pl.C(((Integer) list.get(i2)).intValue()));
            i2++;
        }
    }

    public static void U(int i, List list, InterfaceC0192Km interfaceC0192Km, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                c1397ql.a.T(i, AbstractC1342pl.D(((Long) list.get(i2)).longValue()));
                i2++;
            }
            return;
        }
        c1397ql.a.Q(i, 2);
        int iU = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iU += AbstractC1342pl.u(((Long) list.get(i3)).longValue());
        }
        c1397ql.a.S(iU);
        while (i2 < list.size()) {
            c1397ql.a.U(AbstractC1342pl.D(((Long) list.get(i2)).longValue()));
            i2++;
        }
    }

    public static void V(int i, List list, InterfaceC0192Km interfaceC0192Km) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        int i2 = 0;
        if (!(list instanceof InterfaceC0209Ll)) {
            while (i2 < list.size()) {
                c1397ql.a.P(i, (String) list.get(i2));
                i2++;
            }
            return;
        }
        InterfaceC0209Ll interfaceC0209Ll = (InterfaceC0209Ll) list;
        while (i2 < list.size()) {
            Object objB = interfaceC0209Ll.b(i2);
            if (objB instanceof String) {
                c1397ql.a.P(i, (String) objB);
            } else {
                c1397ql.a.H(i, (AbstractC0731el) objB);
            }
            i2++;
        }
    }

    public static void W(int i, List list, InterfaceC0192Km interfaceC0192Km, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                c1397ql.a.R(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        c1397ql.a.Q(i, 2);
        int iZ = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iZ += AbstractC1342pl.z(((Integer) list.get(i3)).intValue());
        }
        c1397ql.a.S(iZ);
        while (i2 < list.size()) {
            c1397ql.a.S(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    public static void X(int i, List list, InterfaceC0192Km interfaceC0192Km, boolean z) {
        if (list == null || list.isEmpty()) {
            return;
        }
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                c1397ql.a.T(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        c1397ql.a.Q(i, 2);
        int iB = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iB += AbstractC1342pl.B(((Long) list.get(i3)).longValue());
        }
        c1397ql.a.S(iB);
        while (i2 < list.size()) {
            c1397ql.a.U(((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    public static int a(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (!z) {
            return AbstractC1342pl.b(i, true) * size;
        }
        return AbstractC1342pl.n(size) + AbstractC1342pl.x(i);
    }

    public static int b(List list) {
        return list.size();
    }

    public static int c(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iX = AbstractC1342pl.x(i) * size;
        for (int i2 = 0; i2 < list.size(); i2++) {
            iX += AbstractC1342pl.d((AbstractC0731el) list.get(i2));
        }
        return iX;
    }

    public static int d(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iE = e(list);
        if (!z) {
            return (AbstractC1342pl.x(i) * size) + iE;
        }
        return AbstractC1342pl.n(iE) + AbstractC1342pl.x(i);
    }

    public static int e(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iL = 0;
        for (int i = 0; i < size; i++) {
            iL += AbstractC1342pl.l(((Integer) list.get(i)).intValue());
        }
        return iL;
    }

    public static int f(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (!z) {
            return AbstractC1342pl.g(i, 0) * size;
        }
        return AbstractC1342pl.n(size * 4) + AbstractC1342pl.x(i);
    }

    public static int g(List list) {
        return list.size() * 4;
    }

    public static int h(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (!z) {
            return AbstractC1342pl.h(i, 0L) * size;
        }
        return AbstractC1342pl.n(size * 8) + AbstractC1342pl.x(i);
    }

    public static int i(List list) {
        return list.size() * 8;
    }

    public static int j(int i, List list, InterfaceC1178mm interfaceC1178mm) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iJ = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iJ += AbstractC1342pl.j(i, (InterfaceC0509am) list.get(i2), interfaceC1178mm);
        }
        return iJ;
    }

    public static int k(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iL = l(list);
        if (!z) {
            return (AbstractC1342pl.x(i) * size) + iL;
        }
        return AbstractC1342pl.n(iL) + AbstractC1342pl.x(i);
    }

    public static int l(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iL = 0;
        for (int i = 0; i < size; i++) {
            iL += AbstractC1342pl.l(((Integer) list.get(i)).intValue());
        }
        return iL;
    }

    public static int m(int i, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        int iN = n(list);
        if (z) {
            return AbstractC1342pl.n(iN) + AbstractC1342pl.x(i);
        }
        return (AbstractC1342pl.x(i) * list.size()) + iN;
    }

    public static int n(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iB = 0;
        for (int i = 0; i < size; i++) {
            iB += AbstractC1342pl.B(((Long) list.get(i)).longValue());
        }
        return iB;
    }

    public static int o(int i, Object obj, InterfaceC1178mm interfaceC1178mm) {
        return AbstractC1342pl.n(((AbstractC0315Rk) ((InterfaceC0509am) obj)).a(interfaceC1178mm)) + AbstractC1342pl.x(i);
    }

    public static int p(int i, List list, InterfaceC1178mm interfaceC1178mm) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iX = AbstractC1342pl.x(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            iX += AbstractC1342pl.n(((AbstractC0315Rk) ((InterfaceC0509am) list.get(i2))).a(interfaceC1178mm));
        }
        return iX;
    }

    public static int q(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iR = r(list);
        if (!z) {
            return (AbstractC1342pl.x(i) * size) + iR;
        }
        return AbstractC1342pl.n(iR) + AbstractC1342pl.x(i);
    }

    public static int r(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iS = 0;
        for (int i = 0; i < size; i++) {
            iS += AbstractC1342pl.s(((Integer) list.get(i)).intValue());
        }
        return iS;
    }

    public static int s(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iT = t(list);
        if (!z) {
            return (AbstractC1342pl.x(i) * size) + iT;
        }
        return AbstractC1342pl.n(iT) + AbstractC1342pl.x(i);
    }

    public static int t(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iU = 0;
        for (int i = 0; i < size; i++) {
            iU += AbstractC1342pl.u(((Long) list.get(i)).longValue());
        }
        return iU;
    }

    public static int u(int i, List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int iX = AbstractC1342pl.x(i) * size;
        if (list instanceof InterfaceC0209Ll) {
            InterfaceC0209Ll interfaceC0209Ll = (InterfaceC0209Ll) list;
            while (i2 < size) {
                Object objB = interfaceC0209Ll.b(i2);
                iX = (objB instanceof AbstractC0731el ? AbstractC1342pl.d((AbstractC0731el) objB) : AbstractC1342pl.w((String) objB)) + iX;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                iX = (obj instanceof AbstractC0731el ? AbstractC1342pl.d((AbstractC0731el) obj) : AbstractC1342pl.w((String) obj)) + iX;
                i2++;
            }
        }
        return iX;
    }

    public static int v(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iW = w(list);
        if (!z) {
            return (AbstractC1342pl.x(i) * size) + iW;
        }
        return AbstractC1342pl.n(iW) + AbstractC1342pl.x(i);
    }

    public static int w(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZ = 0;
        for (int i = 0; i < size; i++) {
            iZ += AbstractC1342pl.z(((Integer) list.get(i)).intValue());
        }
        return iZ;
    }

    public static int x(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iY = y(list);
        if (!z) {
            return (AbstractC1342pl.x(i) * size) + iY;
        }
        return AbstractC1342pl.n(iY) + AbstractC1342pl.x(i);
    }

    public static int y(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iB = 0;
        for (int i = 0; i < size; i++) {
            iB += AbstractC1342pl.B(((Long) list.get(i)).longValue());
        }
        return iB;
    }

    public static Object z(int i, List list, InterfaceC0083El interfaceC0083El, Object obj, AbstractC1507sm abstractC1507sm) {
        if (interfaceC0083El == null) {
            return obj;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = ((Integer) list.get(i3)).intValue();
                if (interfaceC0083El.a(iIntValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    obj = F(i, iIntValue, obj, abstractC1507sm);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int iIntValue2 = ((Integer) it.next()).intValue();
                if (!interfaceC0083El.a(iIntValue2)) {
                    obj = F(i, iIntValue2, obj, abstractC1507sm);
                    it.remove();
                }
            }
        }
        return obj;
    }
}
