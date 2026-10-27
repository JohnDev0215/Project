package defpackage;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.chromium.support_lib_boundary.WebSettingsBoundaryInterface;
import sun.misc.Unsafe;

/* JADX INFO: renamed from: cm, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0621cm implements InterfaceC1178mm {
    public static final int[] r = new int[0];
    public static final Unsafe s = AbstractC0066Dm.o();
    public final int[] a;
    public final Object[] b;
    public final int c;
    public final int d;
    public final InterfaceC0509am e;
    public final boolean f = false;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public final int[] j;
    public final int k;
    public final int l;
    public final InterfaceC0732em m;
    public final AbstractC0280Pl n;
    public final AbstractC1507sm o;
    public final AbstractC1561tl p;
    public final InterfaceC0388Vl q;

    public C0621cm(int[] iArr, Object[] objArr, int i, int i2, InterfaceC0509am interfaceC0509am, boolean z, boolean z2, int[] iArr2, int i3, int i4, InterfaceC0732em interfaceC0732em, AbstractC0280Pl abstractC0280Pl, AbstractC1507sm abstractC1507sm, AbstractC1561tl abstractC1561tl, InterfaceC0388Vl interfaceC0388Vl) {
        this.a = iArr;
        this.b = objArr;
        this.c = i;
        this.d = i2;
        this.g = interfaceC0509am instanceof AbstractC0065Dl;
        this.h = z;
        this.i = z2;
        this.j = iArr2;
        this.k = i3;
        this.l = i4;
        this.m = interfaceC0732em;
        this.n = abstractC0280Pl;
        this.o = abstractC1507sm;
        this.p = abstractC1561tl;
        this.e = interfaceC0509am;
        this.q = interfaceC0388Vl;
    }

    public static boolean A(Object obj, long j) {
        return ((Boolean) AbstractC0066Dm.n(obj, j)).booleanValue();
    }

    public static double B(Object obj, long j) {
        return ((Double) AbstractC0066Dm.n(obj, j)).doubleValue();
    }

    public static float C(Object obj, long j) {
        return ((Float) AbstractC0066Dm.n(obj, j)).floatValue();
    }

    public static int D(Object obj, long j) {
        return ((Integer) AbstractC0066Dm.n(obj, j)).intValue();
    }

    public static long E(Object obj, long j) {
        return ((Long) AbstractC0066Dm.n(obj, j)).longValue();
    }

    public static Field K(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    public static int N(int i) {
        return (i & 267386880) >>> 20;
    }

    public static List t(Object obj, long j) {
        return (List) AbstractC0066Dm.n(obj, j);
    }

    public static C0621cm x(InterfaceC0442Yl interfaceC0442Yl, InterfaceC0732em interfaceC0732em, AbstractC0280Pl abstractC0280Pl, AbstractC1507sm abstractC1507sm, AbstractC1561tl abstractC1561tl, InterfaceC0388Vl interfaceC0388Vl) {
        if (interfaceC0442Yl instanceof C1067km) {
            return y((C1067km) interfaceC0442Yl, interfaceC0732em, abstractC0280Pl, abstractC1507sm, abstractC1561tl, interfaceC0388Vl);
        }
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:130:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x03b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.C0621cm y(defpackage.C1067km r35, defpackage.InterfaceC0732em r36, defpackage.AbstractC0280Pl r37, defpackage.AbstractC1507sm r38, defpackage.AbstractC1561tl r39, defpackage.InterfaceC0388Vl r40) {
        /*
            Method dump skipped, instruction units count: 1067
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0621cm.y(km, em, Pl, sm, tl, Vl):cm");
    }

    public static long z(int i) {
        return i & 1048575;
    }

    public final int F(int i) {
        if (i < this.c || i > this.d) {
            return -1;
        }
        int i2 = 0;
        int length = (this.a.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.a[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    public final void G(Object obj, long j, InterfaceC1123lm interfaceC1123lm, InterfaceC1178mm interfaceC1178mm, C1506sl c1506sl) {
        interfaceC1123lm.r(this.n.c(obj, j), interfaceC1178mm, c1506sl);
    }

    public final void H(Object obj, int i, InterfaceC1123lm interfaceC1123lm, InterfaceC1178mm interfaceC1178mm, C1506sl c1506sl) {
        interfaceC1123lm.I(this.n.c(obj, z(i)), interfaceC1178mm, c1506sl);
    }

    public final void I(Object obj, int i, InterfaceC1123lm interfaceC1123lm) {
        if ((536870912 & i) != 0) {
            AbstractC0066Dm.f.q(obj, z(i), interfaceC1123lm.D());
        } else if (this.g) {
            AbstractC0066Dm.f.q(obj, z(i), interfaceC1123lm.B());
        } else {
            AbstractC0066Dm.f.q(obj, z(i), interfaceC1123lm.d());
        }
    }

    public final void J(Object obj, int i, InterfaceC1123lm interfaceC1123lm) {
        if ((536870912 & i) != 0) {
            interfaceC1123lm.b(this.n.c(obj, z(i)));
        } else {
            interfaceC1123lm.L(this.n.c(obj, z(i)));
        }
    }

    public final void L(Object obj, int i) {
        if (this.h) {
            return;
        }
        int i2 = this.a[i + 2];
        long j = i2 & 1048575;
        AbstractC0066Dm.f.o(obj, j, AbstractC0066Dm.l(obj, j) | (1 << (i2 >>> 20)));
    }

    public final void M(Object obj, int i, int i2) {
        AbstractC0066Dm.f.o(obj, this.a[i2 + 2] & 1048575, i);
    }

    public final int O(int i) {
        return this.a[i + 1];
    }

    public final void P(Object obj, InterfaceC0192Km interfaceC0192Km) {
        int i;
        int length = this.a.length;
        Unsafe unsafe = s;
        int i2 = -1;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4 += 3) {
            int iO = O(i4);
            int i5 = this.a[i4];
            int iN = N(iO);
            if (this.h || iN > 17) {
                i = 0;
            } else {
                int i6 = this.a[i4 + 2];
                int i7 = 1048575 & i6;
                if (i7 != i2) {
                    i3 = unsafe.getInt(obj, i7);
                    i2 = i7;
                }
                i = 1 << (i6 >>> 20);
            }
            long jZ = z(iO);
            switch (iN) {
                case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                    if ((i & i3) != 0) {
                        ((C1397ql) interfaceC0192Km).a(i5, AbstractC0066Dm.j(obj, jZ));
                        continue;
                    }
                    break;
                case 1:
                    if ((i & i3) != 0) {
                        ((C1397ql) interfaceC0192Km).b(i5, AbstractC0066Dm.k(obj, jZ));
                    } else {
                        continue;
                    }
                    break;
                case 2:
                    if ((i & i3) != 0) {
                        ((C1397ql) interfaceC0192Km).a.T(i5, unsafe.getLong(obj, jZ));
                    } else {
                        continue;
                    }
                    break;
                case 3:
                    if ((i & i3) != 0) {
                        ((C1397ql) interfaceC0192Km).a.T(i5, unsafe.getLong(obj, jZ));
                    } else {
                        continue;
                    }
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    if ((i & i3) != 0) {
                        ((C1397ql) interfaceC0192Km).a.M(i5, unsafe.getInt(obj, jZ));
                    } else {
                        continue;
                    }
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    if ((i & i3) != 0) {
                        ((C1397ql) interfaceC0192Km).a.K(i5, unsafe.getLong(obj, jZ));
                    } else {
                        continue;
                    }
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    if ((i & i3) != 0) {
                        ((C1397ql) interfaceC0192Km).a.I(i5, unsafe.getInt(obj, jZ));
                    } else {
                        continue;
                    }
                    break;
                case 7:
                    if ((i & i3) != 0) {
                        ((C1397ql) interfaceC0192Km).a.G(i5, AbstractC0066Dm.f(obj, jZ));
                    } else {
                        continue;
                    }
                    break;
                case 8:
                    if ((i & i3) != 0) {
                        R(i5, unsafe.getObject(obj, jZ), interfaceC0192Km);
                    } else {
                        continue;
                    }
                    break;
                case 9:
                    if ((i & i3) != 0) {
                        ((C1397ql) interfaceC0192Km).d(i5, unsafe.getObject(obj, jZ), n(i4));
                    } else {
                        continue;
                    }
                    break;
                case 10:
                    if ((i & i3) != 0) {
                        ((C1397ql) interfaceC0192Km).a.H(i5, (AbstractC0731el) unsafe.getObject(obj, jZ));
                    } else {
                        continue;
                    }
                    break;
                case 11:
                    if ((i & i3) != 0) {
                        ((C1397ql) interfaceC0192Km).a.R(i5, unsafe.getInt(obj, jZ));
                    } else {
                        continue;
                    }
                    break;
                case 12:
                    if ((i & i3) != 0) {
                        ((C1397ql) interfaceC0192Km).a.M(i5, unsafe.getInt(obj, jZ));
                    } else {
                        continue;
                    }
                    break;
                case 13:
                    if ((i & i3) != 0) {
                        ((C1397ql) interfaceC0192Km).a.I(i5, unsafe.getInt(obj, jZ));
                    } else {
                        continue;
                    }
                    break;
                case 14:
                    if ((i & i3) != 0) {
                        ((C1397ql) interfaceC0192Km).a.K(i5, unsafe.getLong(obj, jZ));
                    } else {
                        continue;
                    }
                    break;
                case 15:
                    if ((i & i3) != 0) {
                        ((C1397ql) interfaceC0192Km).e(i5, unsafe.getInt(obj, jZ));
                    } else {
                        continue;
                    }
                    break;
                case 16:
                    if ((i & i3) != 0) {
                        ((C1397ql) interfaceC0192Km).f(i5, unsafe.getLong(obj, jZ));
                    } else {
                        continue;
                    }
                    break;
                case 17:
                    if ((i & i3) != 0) {
                        ((C1397ql) interfaceC0192Km).c(i5, unsafe.getObject(obj, jZ), n(i4));
                    } else {
                        continue;
                    }
                    break;
                case 18:
                    AbstractC1288om.I(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, false);
                    continue;
                    break;
                case 19:
                    AbstractC1288om.M(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, false);
                    continue;
                    break;
                case 20:
                    AbstractC1288om.P(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, false);
                    continue;
                    break;
                case 21:
                    AbstractC1288om.X(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, false);
                    continue;
                    break;
                case 22:
                    AbstractC1288om.O(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, false);
                    continue;
                    break;
                case 23:
                    AbstractC1288om.L(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, false);
                    continue;
                    break;
                case 24:
                    AbstractC1288om.K(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, false);
                    continue;
                    break;
                case 25:
                    AbstractC1288om.G(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, false);
                    continue;
                    break;
                case 26:
                    AbstractC1288om.V(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km);
                    break;
                case 27:
                    AbstractC1288om.Q(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, n(i4));
                    break;
                case 28:
                    AbstractC1288om.H(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km);
                    break;
                case 29:
                    AbstractC1288om.W(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, false);
                    break;
                case 30:
                    AbstractC1288om.J(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, false);
                    break;
                case 31:
                    AbstractC1288om.R(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, false);
                    break;
                case 32:
                    AbstractC1288om.S(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, false);
                    break;
                case 33:
                    AbstractC1288om.T(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, false);
                    break;
                case 34:
                    AbstractC1288om.U(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, false);
                    break;
                case 35:
                    AbstractC1288om.I(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, true);
                    break;
                case 36:
                    AbstractC1288om.M(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, true);
                    break;
                case 37:
                    AbstractC1288om.P(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, true);
                    break;
                case 38:
                    AbstractC1288om.X(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, true);
                    break;
                case 39:
                    AbstractC1288om.O(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, true);
                    break;
                case 40:
                    AbstractC1288om.L(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, true);
                    break;
                case 41:
                    AbstractC1288om.K(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, true);
                    break;
                case 42:
                    AbstractC1288om.G(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, true);
                    break;
                case 43:
                    AbstractC1288om.W(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, true);
                    break;
                case 44:
                    AbstractC1288om.J(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, true);
                    break;
                case 45:
                    AbstractC1288om.R(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, true);
                    break;
                case 46:
                    AbstractC1288om.S(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, true);
                    break;
                case 47:
                    AbstractC1288om.T(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, true);
                    break;
                case 48:
                    AbstractC1288om.U(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, true);
                    break;
                case 49:
                    AbstractC1288om.N(this.a[i4], (List) unsafe.getObject(obj, jZ), interfaceC0192Km, n(i4));
                    break;
                case 50:
                    Q(interfaceC0192Km, i5, unsafe.getObject(obj, jZ), i4);
                    break;
                case 51:
                    if (s(obj, i5, i4)) {
                        ((C1397ql) interfaceC0192Km).a(i5, B(obj, jZ));
                    }
                    break;
                case 52:
                    if (s(obj, i5, i4)) {
                        ((C1397ql) interfaceC0192Km).b(i5, C(obj, jZ));
                    }
                    break;
                case 53:
                    if (s(obj, i5, i4)) {
                        ((C1397ql) interfaceC0192Km).a.T(i5, E(obj, jZ));
                    }
                    break;
                case 54:
                    if (s(obj, i5, i4)) {
                        ((C1397ql) interfaceC0192Km).a.T(i5, E(obj, jZ));
                    }
                    break;
                case 55:
                    if (s(obj, i5, i4)) {
                        ((C1397ql) interfaceC0192Km).a.M(i5, D(obj, jZ));
                    }
                    break;
                case 56:
                    if (s(obj, i5, i4)) {
                        ((C1397ql) interfaceC0192Km).a.K(i5, E(obj, jZ));
                    }
                    break;
                case 57:
                    if (s(obj, i5, i4)) {
                        ((C1397ql) interfaceC0192Km).a.I(i5, D(obj, jZ));
                    }
                    break;
                case 58:
                    if (s(obj, i5, i4)) {
                        ((C1397ql) interfaceC0192Km).a.G(i5, A(obj, jZ));
                    }
                    break;
                case 59:
                    if (s(obj, i5, i4)) {
                        R(i5, unsafe.getObject(obj, jZ), interfaceC0192Km);
                    }
                    break;
                case 60:
                    if (s(obj, i5, i4)) {
                        ((C1397ql) interfaceC0192Km).d(i5, unsafe.getObject(obj, jZ), n(i4));
                    }
                    break;
                case 61:
                    if (s(obj, i5, i4)) {
                        ((C1397ql) interfaceC0192Km).a.H(i5, (AbstractC0731el) unsafe.getObject(obj, jZ));
                    }
                    break;
                case 62:
                    if (s(obj, i5, i4)) {
                        ((C1397ql) interfaceC0192Km).a.R(i5, D(obj, jZ));
                    }
                    break;
                case 63:
                    if (s(obj, i5, i4)) {
                        ((C1397ql) interfaceC0192Km).a.M(i5, D(obj, jZ));
                    }
                    break;
                case 64:
                    if (s(obj, i5, i4)) {
                        ((C1397ql) interfaceC0192Km).a.I(i5, D(obj, jZ));
                    }
                    break;
                case 65:
                    if (s(obj, i5, i4)) {
                        ((C1397ql) interfaceC0192Km).a.K(i5, E(obj, jZ));
                    }
                    break;
                case 66:
                    if (s(obj, i5, i4)) {
                        ((C1397ql) interfaceC0192Km).e(i5, D(obj, jZ));
                    }
                    break;
                case 67:
                    if (s(obj, i5, i4)) {
                        ((C1397ql) interfaceC0192Km).f(i5, E(obj, jZ));
                    }
                    break;
                case 68:
                    if (s(obj, i5, i4)) {
                        ((C1397ql) interfaceC0192Km).c(i5, unsafe.getObject(obj, jZ), n(i4));
                    }
                    break;
            }
        }
        S(this.o, obj, interfaceC0192Km);
    }

    public final void Q(InterfaceC0192Km interfaceC0192Km, int i, Object obj, int i2) {
        if (obj == null) {
            return;
        }
        InterfaceC0388Vl interfaceC0388Vl = this.q;
        Object obj2 = this.b[(i2 / 3) * 2];
        Objects.requireNonNull((C0406Wl) interfaceC0388Vl);
        throw null;
    }

    public final void R(int i, Object obj, InterfaceC0192Km interfaceC0192Km) {
        if (obj instanceof String) {
            ((C1397ql) interfaceC0192Km).a.P(i, (String) obj);
        } else {
            ((C1397ql) interfaceC0192Km).a.H(i, (AbstractC0731el) obj);
        }
    }

    public final void S(AbstractC1507sm abstractC1507sm, Object obj, InterfaceC0192Km interfaceC0192Km) {
        Objects.requireNonNull((C1617um) abstractC1507sm);
        ((AbstractC0065Dl) obj).unknownFields.d(interfaceC0192Km);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00ab  */
    @Override // defpackage.InterfaceC1178mm
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(java.lang.Object r13) {
        /*
            Method dump skipped, instruction units count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0621cm.a(java.lang.Object):boolean");
    }

    @Override // defpackage.InterfaceC1178mm
    public void b(Object obj, Object obj2) {
        if (obj2 == null) {
            throw null;
        }
        int i = 0;
        while (true) {
            int[] iArr = this.a;
            if (i >= iArr.length) {
                AbstractC1288om.C(this.o, obj, obj2);
                return;
            }
            int i2 = iArr[i + 1];
            long jZ = z(i2);
            int i3 = this.a[i];
            switch (N(i2)) {
                case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                    if (q(obj2, i)) {
                        AbstractC0066Dm.f.m(obj, jZ, AbstractC0066Dm.j(obj2, jZ));
                        L(obj, i);
                    }
                    break;
                case 1:
                    if (q(obj2, i)) {
                        AbstractC0066Dm.f.n(obj, jZ, AbstractC0066Dm.k(obj2, jZ));
                        L(obj, i);
                    }
                    break;
                case 2:
                    if (q(obj2, i)) {
                        AbstractC0066Dm.w(obj, jZ, AbstractC0066Dm.m(obj2, jZ));
                        L(obj, i);
                    }
                    break;
                case 3:
                    if (q(obj2, i)) {
                        AbstractC0066Dm.w(obj, jZ, AbstractC0066Dm.m(obj2, jZ));
                        L(obj, i);
                    }
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    if (q(obj2, i)) {
                        AbstractC0066Dm.f.o(obj, jZ, AbstractC0066Dm.l(obj2, jZ));
                        L(obj, i);
                    }
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    if (q(obj2, i)) {
                        AbstractC0066Dm.w(obj, jZ, AbstractC0066Dm.m(obj2, jZ));
                        L(obj, i);
                    }
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    if (q(obj2, i)) {
                        AbstractC0066Dm.f.o(obj, jZ, AbstractC0066Dm.l(obj2, jZ));
                        L(obj, i);
                    }
                    break;
                case 7:
                    if (q(obj2, i)) {
                        AbstractC0066Dm.f.k(obj, jZ, AbstractC0066Dm.f(obj2, jZ));
                        L(obj, i);
                    }
                    break;
                case 8:
                    if (q(obj2, i)) {
                        AbstractC0066Dm.f.q(obj, jZ, AbstractC0066Dm.n(obj2, jZ));
                        L(obj, i);
                    }
                    break;
                case 9:
                    v(obj, obj2, i);
                    break;
                case 10:
                    if (q(obj2, i)) {
                        AbstractC0066Dm.f.q(obj, jZ, AbstractC0066Dm.n(obj2, jZ));
                        L(obj, i);
                    }
                    break;
                case 11:
                    if (q(obj2, i)) {
                        AbstractC0066Dm.f.o(obj, jZ, AbstractC0066Dm.l(obj2, jZ));
                        L(obj, i);
                    }
                    break;
                case 12:
                    if (q(obj2, i)) {
                        AbstractC0066Dm.f.o(obj, jZ, AbstractC0066Dm.l(obj2, jZ));
                        L(obj, i);
                    }
                    break;
                case 13:
                    if (q(obj2, i)) {
                        AbstractC0066Dm.f.o(obj, jZ, AbstractC0066Dm.l(obj2, jZ));
                        L(obj, i);
                    }
                    break;
                case 14:
                    if (q(obj2, i)) {
                        AbstractC0066Dm.w(obj, jZ, AbstractC0066Dm.m(obj2, jZ));
                        L(obj, i);
                    }
                    break;
                case 15:
                    if (q(obj2, i)) {
                        AbstractC0066Dm.f.o(obj, jZ, AbstractC0066Dm.l(obj2, jZ));
                        L(obj, i);
                    }
                    break;
                case 16:
                    if (q(obj2, i)) {
                        AbstractC0066Dm.w(obj, jZ, AbstractC0066Dm.m(obj2, jZ));
                        L(obj, i);
                    }
                    break;
                case 17:
                    v(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.n.b(obj, obj2, jZ);
                    break;
                case 50:
                    AbstractC1288om.B(this.q, obj, obj2, jZ);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (s(obj2, i3, i)) {
                        AbstractC0066Dm.f.q(obj, jZ, AbstractC0066Dm.n(obj2, jZ));
                        M(obj, i3, i);
                    }
                    break;
                case 60:
                    w(obj, obj2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (s(obj2, i3, i)) {
                        AbstractC0066Dm.f.q(obj, jZ, AbstractC0066Dm.n(obj2, jZ));
                        M(obj, i3, i);
                    }
                    break;
                case 68:
                    w(obj, obj2, i);
                    break;
            }
            i += 3;
        }
    }

    @Override // defpackage.InterfaceC1178mm
    public void c(Object obj, InterfaceC1123lm interfaceC1123lm, C1506sl c1506sl) {
        if (c1506sl == null) {
            throw null;
        }
        AbstractC1507sm abstractC1507sm = this.o;
        Object objB = null;
        while (true) {
            try {
                int iK = interfaceC1123lm.K();
                int iF = F(iK);
                if (iF >= 0) {
                    int iO = O(iF);
                    try {
                    } catch (C0137Hl unused) {
                        abstractC1507sm.f(interfaceC1123lm);
                        if (objB == null) {
                            objB = abstractC1507sm.b(obj);
                        }
                        if (!abstractC1507sm.c(objB, interfaceC1123lm)) {
                            for (int i = this.k; i < this.l; i++) {
                                k(obj, this.j[i], objB, abstractC1507sm);
                            }
                            if (objB == null) {
                                return;
                            }
                            abstractC1507sm.e(obj, objB);
                        }
                    }
                    switch (N(iO)) {
                        case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                            AbstractC0066Dm.t(obj, z(iO), interfaceC1123lm.n());
                            L(obj, iF);
                            break;
                        case 1:
                            AbstractC0066Dm.u(obj, z(iO), interfaceC1123lm.s());
                            L(obj, iF);
                            break;
                        case 2:
                            AbstractC0066Dm.w(obj, z(iO), interfaceC1123lm.C());
                            L(obj, iF);
                            break;
                        case 3:
                            AbstractC0066Dm.w(obj, z(iO), interfaceC1123lm.k());
                            L(obj, iF);
                            break;
                        case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                            AbstractC0066Dm.v(obj, z(iO), interfaceC1123lm.i());
                            L(obj, iF);
                            break;
                        case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                            AbstractC0066Dm.w(obj, z(iO), interfaceC1123lm.m());
                            L(obj, iF);
                            break;
                        case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                            AbstractC0066Dm.v(obj, z(iO), interfaceC1123lm.E());
                            L(obj, iF);
                            break;
                        case 7:
                            AbstractC0066Dm.p(obj, z(iO), interfaceC1123lm.H());
                            L(obj, iF);
                            break;
                        case 8:
                            I(obj, iO, interfaceC1123lm);
                            L(obj, iF);
                            break;
                        case 9:
                            if (q(obj, iF)) {
                                AbstractC0066Dm.x(obj, z(iO), AbstractC0119Gl.f(AbstractC0066Dm.n(obj, z(iO)), interfaceC1123lm.N(n(iF), c1506sl)));
                            } else {
                                AbstractC0066Dm.x(obj, z(iO), interfaceC1123lm.N(n(iF), c1506sl));
                                L(obj, iF);
                            }
                            break;
                        case 10:
                            AbstractC0066Dm.x(obj, z(iO), interfaceC1123lm.d());
                            L(obj, iF);
                            break;
                        case 11:
                            AbstractC0066Dm.v(obj, z(iO), interfaceC1123lm.h());
                            L(obj, iF);
                            break;
                        case 12:
                            int iF2 = interfaceC1123lm.f();
                            InterfaceC0083El interfaceC0083ElL = l(iF);
                            if (interfaceC0083ElL == null || interfaceC0083ElL.a(iF2)) {
                                AbstractC0066Dm.v(obj, z(iO), iF2);
                                L(obj, iF);
                            } else {
                                objB = AbstractC1288om.F(iK, iF2, objB, abstractC1507sm);
                            }
                            break;
                        case 13:
                            AbstractC0066Dm.v(obj, z(iO), interfaceC1123lm.u());
                            L(obj, iF);
                            break;
                        case 14:
                            AbstractC0066Dm.w(obj, z(iO), interfaceC1123lm.M());
                            L(obj, iF);
                            break;
                        case 15:
                            AbstractC0066Dm.v(obj, z(iO), interfaceC1123lm.p());
                            L(obj, iF);
                            break;
                        case 16:
                            AbstractC0066Dm.w(obj, z(iO), interfaceC1123lm.t());
                            L(obj, iF);
                            break;
                        case 17:
                            if (q(obj, iF)) {
                                AbstractC0066Dm.x(obj, z(iO), AbstractC0119Gl.f(AbstractC0066Dm.n(obj, z(iO)), interfaceC1123lm.c(n(iF), c1506sl)));
                            } else {
                                AbstractC0066Dm.x(obj, z(iO), interfaceC1123lm.c(n(iF), c1506sl));
                                L(obj, iF);
                            }
                            break;
                        case 18:
                            interfaceC1123lm.x(this.n.c(obj, z(iO)));
                            break;
                        case 19:
                            interfaceC1123lm.g(this.n.c(obj, z(iO)));
                            break;
                        case 20:
                            interfaceC1123lm.y(this.n.c(obj, z(iO)));
                            break;
                        case 21:
                            interfaceC1123lm.a(this.n.c(obj, z(iO)));
                            break;
                        case 22:
                            interfaceC1123lm.G(this.n.c(obj, z(iO)));
                            break;
                        case 23:
                            interfaceC1123lm.F(this.n.c(obj, z(iO)));
                            break;
                        case 24:
                            interfaceC1123lm.l(this.n.c(obj, z(iO)));
                            break;
                        case 25:
                            interfaceC1123lm.z(this.n.c(obj, z(iO)));
                            break;
                        case 26:
                            J(obj, iO, interfaceC1123lm);
                            break;
                        case 27:
                            H(obj, iO, interfaceC1123lm, n(iF), c1506sl);
                            break;
                        case 28:
                            interfaceC1123lm.v(this.n.c(obj, z(iO)));
                            break;
                        case 29:
                            interfaceC1123lm.w(this.n.c(obj, z(iO)));
                            break;
                        case 30:
                            List listC = this.n.c(obj, z(iO));
                            interfaceC1123lm.J(listC);
                            objB = AbstractC1288om.z(iK, listC, l(iF), objB, abstractC1507sm);
                            break;
                        case 31:
                            interfaceC1123lm.o(this.n.c(obj, z(iO)));
                            break;
                        case 32:
                            interfaceC1123lm.A(this.n.c(obj, z(iO)));
                            break;
                        case 33:
                            interfaceC1123lm.e(this.n.c(obj, z(iO)));
                            break;
                        case 34:
                            interfaceC1123lm.q(this.n.c(obj, z(iO)));
                            break;
                        case 35:
                            interfaceC1123lm.x(this.n.c(obj, z(iO)));
                            break;
                        case 36:
                            interfaceC1123lm.g(this.n.c(obj, z(iO)));
                            break;
                        case 37:
                            interfaceC1123lm.y(this.n.c(obj, z(iO)));
                            break;
                        case 38:
                            interfaceC1123lm.a(this.n.c(obj, z(iO)));
                            break;
                        case 39:
                            interfaceC1123lm.G(this.n.c(obj, z(iO)));
                            break;
                        case 40:
                            interfaceC1123lm.F(this.n.c(obj, z(iO)));
                            break;
                        case 41:
                            interfaceC1123lm.l(this.n.c(obj, z(iO)));
                            break;
                        case 42:
                            interfaceC1123lm.z(this.n.c(obj, z(iO)));
                            break;
                        case 43:
                            interfaceC1123lm.w(this.n.c(obj, z(iO)));
                            break;
                        case 44:
                            List listC2 = this.n.c(obj, z(iO));
                            interfaceC1123lm.J(listC2);
                            objB = AbstractC1288om.z(iK, listC2, l(iF), objB, abstractC1507sm);
                            break;
                        case 45:
                            interfaceC1123lm.o(this.n.c(obj, z(iO)));
                            break;
                        case 46:
                            interfaceC1123lm.A(this.n.c(obj, z(iO)));
                            break;
                        case 47:
                            interfaceC1123lm.e(this.n.c(obj, z(iO)));
                            break;
                        case 48:
                            interfaceC1123lm.q(this.n.c(obj, z(iO)));
                            break;
                        case 49:
                            G(obj, z(iO), interfaceC1123lm, n(iF), c1506sl);
                            break;
                        case 50:
                            u(obj, iF, m(iF), c1506sl, interfaceC1123lm);
                            throw null;
                        case 51:
                            AbstractC0066Dm.x(obj, z(iO), Double.valueOf(interfaceC1123lm.n()));
                            M(obj, iK, iF);
                            break;
                        case 52:
                            AbstractC0066Dm.x(obj, z(iO), Float.valueOf(interfaceC1123lm.s()));
                            M(obj, iK, iF);
                            break;
                        case 53:
                            AbstractC0066Dm.x(obj, z(iO), Long.valueOf(interfaceC1123lm.C()));
                            M(obj, iK, iF);
                            break;
                        case 54:
                            AbstractC0066Dm.x(obj, z(iO), Long.valueOf(interfaceC1123lm.k()));
                            M(obj, iK, iF);
                            break;
                        case 55:
                            AbstractC0066Dm.x(obj, z(iO), Integer.valueOf(interfaceC1123lm.i()));
                            M(obj, iK, iF);
                            break;
                        case 56:
                            AbstractC0066Dm.x(obj, z(iO), Long.valueOf(interfaceC1123lm.m()));
                            M(obj, iK, iF);
                            break;
                        case 57:
                            AbstractC0066Dm.x(obj, z(iO), Integer.valueOf(interfaceC1123lm.E()));
                            M(obj, iK, iF);
                            break;
                        case 58:
                            AbstractC0066Dm.x(obj, z(iO), Boolean.valueOf(interfaceC1123lm.H()));
                            M(obj, iK, iF);
                            break;
                        case 59:
                            I(obj, iO, interfaceC1123lm);
                            M(obj, iK, iF);
                            break;
                        case 60:
                            if (s(obj, iK, iF)) {
                                AbstractC0066Dm.x(obj, z(iO), AbstractC0119Gl.f(AbstractC0066Dm.n(obj, z(iO)), interfaceC1123lm.N(n(iF), c1506sl)));
                            } else {
                                AbstractC0066Dm.x(obj, z(iO), interfaceC1123lm.N(n(iF), c1506sl));
                                L(obj, iF);
                            }
                            M(obj, iK, iF);
                            break;
                        case 61:
                            AbstractC0066Dm.x(obj, z(iO), interfaceC1123lm.d());
                            M(obj, iK, iF);
                            break;
                        case 62:
                            AbstractC0066Dm.x(obj, z(iO), Integer.valueOf(interfaceC1123lm.h()));
                            M(obj, iK, iF);
                            break;
                        case 63:
                            int iF3 = interfaceC1123lm.f();
                            InterfaceC0083El interfaceC0083ElL2 = l(iF);
                            if (interfaceC0083ElL2 == null || interfaceC0083ElL2.a(iF3)) {
                                AbstractC0066Dm.x(obj, z(iO), Integer.valueOf(iF3));
                                M(obj, iK, iF);
                            } else {
                                objB = AbstractC1288om.F(iK, iF3, objB, abstractC1507sm);
                            }
                            break;
                        case 64:
                            AbstractC0066Dm.x(obj, z(iO), Integer.valueOf(interfaceC1123lm.u()));
                            M(obj, iK, iF);
                            break;
                        case 65:
                            AbstractC0066Dm.x(obj, z(iO), Long.valueOf(interfaceC1123lm.M()));
                            M(obj, iK, iF);
                            break;
                        case 66:
                            AbstractC0066Dm.x(obj, z(iO), Integer.valueOf(interfaceC1123lm.p()));
                            M(obj, iK, iF);
                            break;
                        case 67:
                            AbstractC0066Dm.x(obj, z(iO), Long.valueOf(interfaceC1123lm.t()));
                            M(obj, iK, iF);
                            break;
                        case 68:
                            AbstractC0066Dm.x(obj, z(iO), interfaceC1123lm.c(n(iF), c1506sl));
                            M(obj, iK, iF);
                            break;
                        default:
                            if (objB == null) {
                                objB = abstractC1507sm.d();
                            }
                            if (!abstractC1507sm.c(objB, interfaceC1123lm)) {
                                for (int i2 = this.k; i2 < this.l; i2++) {
                                    k(obj, this.j[i2], objB, abstractC1507sm);
                                }
                            }
                            break;
                    }
                } else if (iK == Integer.MAX_VALUE) {
                    for (int i3 = this.k; i3 < this.l; i3++) {
                        k(obj, this.j[i3], objB, abstractC1507sm);
                    }
                    if (objB == null) {
                        return;
                    }
                } else {
                    abstractC1507sm.f(interfaceC1123lm);
                    if (objB == null) {
                        objB = abstractC1507sm.b(obj);
                    }
                    if (!abstractC1507sm.c(objB, interfaceC1123lm)) {
                        for (int i4 = this.k; i4 < this.l; i4++) {
                            k(obj, this.j[i4], objB, abstractC1507sm);
                        }
                        if (objB == null) {
                            return;
                        }
                    }
                }
            } catch (Throwable th) {
                for (int i5 = this.k; i5 < this.l; i5++) {
                    k(obj, this.j[i5], objB, abstractC1507sm);
                }
                if (objB != null) {
                    abstractC1507sm.e(obj, objB);
                }
                throw th;
            }
        }
        abstractC1507sm.e(obj, objB);
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x01cc  */
    @Override // defpackage.InterfaceC1178mm
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean d(java.lang.Object r10, java.lang.Object r11) {
        /*
            Method dump skipped, instruction units count: 640
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0621cm.d(java.lang.Object, java.lang.Object):boolean");
    }

    @Override // defpackage.InterfaceC1178mm
    public int e(Object obj) {
        return this.h ? p(obj) : o(obj);
    }

    @Override // defpackage.InterfaceC1178mm
    public Object f() {
        InterfaceC0732em interfaceC0732em = this.m;
        InterfaceC0509am interfaceC0509am = this.e;
        Objects.requireNonNull((C0788fm) interfaceC0732em);
        return ((AbstractC0065Dl) interfaceC0509am).c(EnumC0047Cl.NEW_MUTABLE_INSTANCE, null, null);
    }

    @Override // defpackage.InterfaceC1178mm
    public void g(Object obj, InterfaceC0192Km interfaceC0192Km) {
        C1397ql c1397ql = (C1397ql) interfaceC0192Km;
        Objects.requireNonNull(c1397ql);
        if (!this.h) {
            P(obj, interfaceC0192Km);
            return;
        }
        int length = this.a.length;
        for (int i = 0; i < length; i += 3) {
            int iO = O(i);
            int i2 = this.a[i];
            switch (N(iO)) {
                case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                    if (q(obj, i)) {
                        c1397ql.a(i2, AbstractC0066Dm.j(obj, z(iO)));
                    }
                    break;
                case 1:
                    if (q(obj, i)) {
                        c1397ql.b(i2, AbstractC0066Dm.k(obj, z(iO)));
                    }
                    break;
                case 2:
                    if (q(obj, i)) {
                        c1397ql.a.T(i2, AbstractC0066Dm.m(obj, z(iO)));
                    }
                    break;
                case 3:
                    if (q(obj, i)) {
                        c1397ql.a.T(i2, AbstractC0066Dm.m(obj, z(iO)));
                    }
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    if (q(obj, i)) {
                        c1397ql.a.M(i2, AbstractC0066Dm.l(obj, z(iO)));
                    }
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    if (q(obj, i)) {
                        c1397ql.a.K(i2, AbstractC0066Dm.m(obj, z(iO)));
                    }
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    if (q(obj, i)) {
                        c1397ql.a.I(i2, AbstractC0066Dm.l(obj, z(iO)));
                    }
                    break;
                case 7:
                    if (q(obj, i)) {
                        c1397ql.a.G(i2, AbstractC0066Dm.f(obj, z(iO)));
                    }
                    break;
                case 8:
                    if (q(obj, i)) {
                        R(i2, AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km);
                    }
                    break;
                case 9:
                    if (q(obj, i)) {
                        c1397ql.d(i2, AbstractC0066Dm.n(obj, z(iO)), n(i));
                    }
                    break;
                case 10:
                    if (q(obj, i)) {
                        c1397ql.a.H(i2, (AbstractC0731el) AbstractC0066Dm.n(obj, z(iO)));
                    }
                    break;
                case 11:
                    if (q(obj, i)) {
                        c1397ql.a.R(i2, AbstractC0066Dm.l(obj, z(iO)));
                    }
                    break;
                case 12:
                    if (q(obj, i)) {
                        c1397ql.a.M(i2, AbstractC0066Dm.l(obj, z(iO)));
                    }
                    break;
                case 13:
                    if (q(obj, i)) {
                        c1397ql.a.I(i2, AbstractC0066Dm.l(obj, z(iO)));
                    }
                    break;
                case 14:
                    if (q(obj, i)) {
                        c1397ql.a.K(i2, AbstractC0066Dm.m(obj, z(iO)));
                    }
                    break;
                case 15:
                    if (q(obj, i)) {
                        c1397ql.e(i2, AbstractC0066Dm.l(obj, z(iO)));
                    }
                    break;
                case 16:
                    if (q(obj, i)) {
                        c1397ql.f(i2, AbstractC0066Dm.m(obj, z(iO)));
                    }
                    break;
                case 17:
                    if (q(obj, i)) {
                        c1397ql.c(i2, AbstractC0066Dm.n(obj, z(iO)), n(i));
                    }
                    break;
                case 18:
                    AbstractC1288om.I(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, false);
                    break;
                case 19:
                    AbstractC1288om.M(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, false);
                    break;
                case 20:
                    AbstractC1288om.P(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, false);
                    break;
                case 21:
                    AbstractC1288om.X(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, false);
                    break;
                case 22:
                    AbstractC1288om.O(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, false);
                    break;
                case 23:
                    AbstractC1288om.L(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, false);
                    break;
                case 24:
                    AbstractC1288om.K(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, false);
                    break;
                case 25:
                    AbstractC1288om.G(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, false);
                    break;
                case 26:
                    AbstractC1288om.V(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km);
                    break;
                case 27:
                    AbstractC1288om.Q(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, n(i));
                    break;
                case 28:
                    AbstractC1288om.H(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km);
                    break;
                case 29:
                    AbstractC1288om.W(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, false);
                    break;
                case 30:
                    AbstractC1288om.J(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, false);
                    break;
                case 31:
                    AbstractC1288om.R(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, false);
                    break;
                case 32:
                    AbstractC1288om.S(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, false);
                    break;
                case 33:
                    AbstractC1288om.T(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, false);
                    break;
                case 34:
                    AbstractC1288om.U(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, false);
                    break;
                case 35:
                    AbstractC1288om.I(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, true);
                    break;
                case 36:
                    AbstractC1288om.M(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, true);
                    break;
                case 37:
                    AbstractC1288om.P(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, true);
                    break;
                case 38:
                    AbstractC1288om.X(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, true);
                    break;
                case 39:
                    AbstractC1288om.O(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, true);
                    break;
                case 40:
                    AbstractC1288om.L(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, true);
                    break;
                case 41:
                    AbstractC1288om.K(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, true);
                    break;
                case 42:
                    AbstractC1288om.G(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, true);
                    break;
                case 43:
                    AbstractC1288om.W(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, true);
                    break;
                case 44:
                    AbstractC1288om.J(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, true);
                    break;
                case 45:
                    AbstractC1288om.R(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, true);
                    break;
                case 46:
                    AbstractC1288om.S(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, true);
                    break;
                case 47:
                    AbstractC1288om.T(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, true);
                    break;
                case 48:
                    AbstractC1288om.U(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, true);
                    break;
                case 49:
                    AbstractC1288om.N(this.a[i], (List) AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km, n(i));
                    break;
                case 50:
                    Q(interfaceC0192Km, i2, AbstractC0066Dm.n(obj, z(iO)), i);
                    break;
                case 51:
                    if (s(obj, i2, i)) {
                        c1397ql.a(i2, B(obj, z(iO)));
                    }
                    break;
                case 52:
                    if (s(obj, i2, i)) {
                        c1397ql.b(i2, C(obj, z(iO)));
                    }
                    break;
                case 53:
                    if (s(obj, i2, i)) {
                        c1397ql.a.T(i2, E(obj, z(iO)));
                    }
                    break;
                case 54:
                    if (s(obj, i2, i)) {
                        c1397ql.a.T(i2, E(obj, z(iO)));
                    }
                    break;
                case 55:
                    if (s(obj, i2, i)) {
                        c1397ql.a.M(i2, D(obj, z(iO)));
                    }
                    break;
                case 56:
                    if (s(obj, i2, i)) {
                        c1397ql.a.K(i2, E(obj, z(iO)));
                    }
                    break;
                case 57:
                    if (s(obj, i2, i)) {
                        c1397ql.a.I(i2, D(obj, z(iO)));
                    }
                    break;
                case 58:
                    if (s(obj, i2, i)) {
                        c1397ql.a.G(i2, A(obj, z(iO)));
                    }
                    break;
                case 59:
                    if (s(obj, i2, i)) {
                        R(i2, AbstractC0066Dm.n(obj, z(iO)), interfaceC0192Km);
                    }
                    break;
                case 60:
                    if (s(obj, i2, i)) {
                        c1397ql.d(i2, AbstractC0066Dm.n(obj, z(iO)), n(i));
                    }
                    break;
                case 61:
                    if (s(obj, i2, i)) {
                        c1397ql.a.H(i2, (AbstractC0731el) AbstractC0066Dm.n(obj, z(iO)));
                    }
                    break;
                case 62:
                    if (s(obj, i2, i)) {
                        c1397ql.a.R(i2, D(obj, z(iO)));
                    }
                    break;
                case 63:
                    if (s(obj, i2, i)) {
                        c1397ql.a.M(i2, D(obj, z(iO)));
                    }
                    break;
                case 64:
                    if (s(obj, i2, i)) {
                        c1397ql.a.I(i2, D(obj, z(iO)));
                    }
                    break;
                case 65:
                    if (s(obj, i2, i)) {
                        c1397ql.a.K(i2, E(obj, z(iO)));
                    }
                    break;
                case 66:
                    if (s(obj, i2, i)) {
                        c1397ql.e(i2, D(obj, z(iO)));
                    }
                    break;
                case 67:
                    if (s(obj, i2, i)) {
                        c1397ql.f(i2, E(obj, z(iO)));
                    }
                    break;
                case 68:
                    if (s(obj, i2, i)) {
                        c1397ql.c(i2, AbstractC0066Dm.n(obj, z(iO)), n(i));
                    }
                    break;
            }
        }
        S(this.o, obj, interfaceC0192Km);
    }

    @Override // defpackage.InterfaceC1178mm
    public void h(Object obj) {
        int i;
        int i2 = this.k;
        while (true) {
            i = this.l;
            if (i2 >= i) {
                break;
            }
            long jZ = z(O(this.j[i2]));
            Object objN = AbstractC0066Dm.n(obj, jZ);
            if (objN != null) {
                Objects.requireNonNull((C0406Wl) this.q);
                ((C0370Ul) objN).s = false;
                AbstractC0066Dm.f.q(obj, jZ, objN);
            }
            i2++;
        }
        int length = this.j.length;
        while (i < length) {
            this.n.a(obj, this.j[i]);
            i++;
        }
        Objects.requireNonNull((C1617um) this.o);
        ((AbstractC0065Dl) obj).unknownFields.e = false;
    }

    @Override // defpackage.InterfaceC1178mm
    public int i(Object obj) {
        int i;
        int iE;
        int length = this.a.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int iO = O(i3);
            int i4 = this.a[i3];
            long jZ = z(iO);
            int iHashCode = 37;
            switch (N(iO)) {
                case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                    i = i2 * 53;
                    iE = AbstractC0119Gl.e(Double.doubleToLongBits(AbstractC0066Dm.j(obj, jZ)));
                    i2 = iE + i;
                    break;
                case 1:
                    i = i2 * 53;
                    iE = Float.floatToIntBits(AbstractC0066Dm.k(obj, jZ));
                    i2 = iE + i;
                    break;
                case 2:
                    i = i2 * 53;
                    iE = AbstractC0119Gl.e(AbstractC0066Dm.m(obj, jZ));
                    i2 = iE + i;
                    break;
                case 3:
                    i = i2 * 53;
                    iE = AbstractC0119Gl.e(AbstractC0066Dm.m(obj, jZ));
                    i2 = iE + i;
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    i = i2 * 53;
                    iE = AbstractC0066Dm.l(obj, jZ);
                    i2 = iE + i;
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    i = i2 * 53;
                    iE = AbstractC0119Gl.e(AbstractC0066Dm.m(obj, jZ));
                    i2 = iE + i;
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    i = i2 * 53;
                    iE = AbstractC0066Dm.l(obj, jZ);
                    i2 = iE + i;
                    break;
                case 7:
                    i = i2 * 53;
                    iE = AbstractC0119Gl.c(AbstractC0066Dm.f(obj, jZ));
                    i2 = iE + i;
                    break;
                case 8:
                    i = i2 * 53;
                    iE = ((String) AbstractC0066Dm.n(obj, jZ)).hashCode();
                    i2 = iE + i;
                    break;
                case 9:
                    Object objN = AbstractC0066Dm.n(obj, jZ);
                    if (objN != null) {
                        iHashCode = objN.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 10:
                    i = i2 * 53;
                    iE = AbstractC0066Dm.n(obj, jZ).hashCode();
                    i2 = iE + i;
                    break;
                case 11:
                    i = i2 * 53;
                    iE = AbstractC0066Dm.l(obj, jZ);
                    i2 = iE + i;
                    break;
                case 12:
                    i = i2 * 53;
                    iE = AbstractC0066Dm.l(obj, jZ);
                    i2 = iE + i;
                    break;
                case 13:
                    i = i2 * 53;
                    iE = AbstractC0066Dm.l(obj, jZ);
                    i2 = iE + i;
                    break;
                case 14:
                    i = i2 * 53;
                    iE = AbstractC0119Gl.e(AbstractC0066Dm.m(obj, jZ));
                    i2 = iE + i;
                    break;
                case 15:
                    i = i2 * 53;
                    iE = AbstractC0066Dm.l(obj, jZ);
                    i2 = iE + i;
                    break;
                case 16:
                    i = i2 * 53;
                    iE = AbstractC0119Gl.e(AbstractC0066Dm.m(obj, jZ));
                    i2 = iE + i;
                    break;
                case 17:
                    Object objN2 = AbstractC0066Dm.n(obj, jZ);
                    if (objN2 != null) {
                        iHashCode = objN2.hashCode();
                    }
                    i2 = (i2 * 53) + iHashCode;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = i2 * 53;
                    iE = AbstractC0066Dm.n(obj, jZ).hashCode();
                    i2 = iE + i;
                    break;
                case 50:
                    i = i2 * 53;
                    iE = AbstractC0066Dm.n(obj, jZ).hashCode();
                    i2 = iE + i;
                    break;
                case 51:
                    if (s(obj, i4, i3)) {
                        i = i2 * 53;
                        iE = AbstractC0119Gl.e(Double.doubleToLongBits(B(obj, jZ)));
                        i2 = iE + i;
                    }
                    break;
                case 52:
                    if (s(obj, i4, i3)) {
                        i = i2 * 53;
                        iE = Float.floatToIntBits(C(obj, jZ));
                        i2 = iE + i;
                    }
                    break;
                case 53:
                    if (s(obj, i4, i3)) {
                        i = i2 * 53;
                        iE = AbstractC0119Gl.e(E(obj, jZ));
                        i2 = iE + i;
                    }
                    break;
                case 54:
                    if (s(obj, i4, i3)) {
                        i = i2 * 53;
                        iE = AbstractC0119Gl.e(E(obj, jZ));
                        i2 = iE + i;
                    }
                    break;
                case 55:
                    if (s(obj, i4, i3)) {
                        i = i2 * 53;
                        iE = D(obj, jZ);
                        i2 = iE + i;
                    }
                    break;
                case 56:
                    if (s(obj, i4, i3)) {
                        i = i2 * 53;
                        iE = AbstractC0119Gl.e(E(obj, jZ));
                        i2 = iE + i;
                    }
                    break;
                case 57:
                    if (s(obj, i4, i3)) {
                        i = i2 * 53;
                        iE = D(obj, jZ);
                        i2 = iE + i;
                    }
                    break;
                case 58:
                    if (s(obj, i4, i3)) {
                        i = i2 * 53;
                        iE = AbstractC0119Gl.c(A(obj, jZ));
                        i2 = iE + i;
                    }
                    break;
                case 59:
                    if (s(obj, i4, i3)) {
                        i = i2 * 53;
                        iE = ((String) AbstractC0066Dm.n(obj, jZ)).hashCode();
                        i2 = iE + i;
                    }
                    break;
                case 60:
                    if (s(obj, i4, i3)) {
                        i = i2 * 53;
                        iE = AbstractC0066Dm.n(obj, jZ).hashCode();
                        i2 = iE + i;
                    }
                    break;
                case 61:
                    if (s(obj, i4, i3)) {
                        i = i2 * 53;
                        iE = AbstractC0066Dm.n(obj, jZ).hashCode();
                        i2 = iE + i;
                    }
                    break;
                case 62:
                    if (s(obj, i4, i3)) {
                        i = i2 * 53;
                        iE = D(obj, jZ);
                        i2 = iE + i;
                    }
                    break;
                case 63:
                    if (s(obj, i4, i3)) {
                        i = i2 * 53;
                        iE = D(obj, jZ);
                        i2 = iE + i;
                    }
                    break;
                case 64:
                    if (s(obj, i4, i3)) {
                        i = i2 * 53;
                        iE = D(obj, jZ);
                        i2 = iE + i;
                    }
                    break;
                case 65:
                    if (s(obj, i4, i3)) {
                        i = i2 * 53;
                        iE = AbstractC0119Gl.e(E(obj, jZ));
                        i2 = iE + i;
                    }
                    break;
                case 66:
                    if (s(obj, i4, i3)) {
                        i = i2 * 53;
                        iE = D(obj, jZ);
                        i2 = iE + i;
                    }
                    break;
                case 67:
                    if (s(obj, i4, i3)) {
                        i = i2 * 53;
                        iE = AbstractC0119Gl.e(E(obj, jZ));
                        i2 = iE + i;
                    }
                    break;
                case 68:
                    if (s(obj, i4, i3)) {
                        i = i2 * 53;
                        iE = AbstractC0066Dm.n(obj, jZ).hashCode();
                        i2 = iE + i;
                    }
                    break;
            }
        }
        Objects.requireNonNull((C1617um) this.o);
        return ((AbstractC0065Dl) obj).unknownFields.hashCode() + (i2 * 53);
    }

    public final boolean j(Object obj, Object obj2, int i) {
        return q(obj, i) == q(obj2, i);
    }

    public final Object k(Object obj, int i, Object obj2, AbstractC1507sm abstractC1507sm) {
        int[] iArr = this.a;
        int i2 = iArr[i];
        Object objN = AbstractC0066Dm.n(obj, z(iArr[i + 1]));
        if (objN == null) {
            return obj2;
        }
        int i3 = (i / 3) * 2;
        if (((InterfaceC0083El) this.b[i3 + 1]) == null) {
            return obj2;
        }
        Objects.requireNonNull((C0406Wl) this.q);
        InterfaceC0388Vl interfaceC0388Vl = this.q;
        Object obj3 = this.b[i3];
        Objects.requireNonNull((C0406Wl) interfaceC0388Vl);
        throw null;
    }

    public final InterfaceC0083El l(int i) {
        return (InterfaceC0083El) this.b[((i / 3) * 2) + 1];
    }

    public final Object m(int i) {
        return this.b[(i / 3) * 2];
    }

    public final InterfaceC1178mm n(int i) {
        int i2 = (i / 3) * 2;
        Object[] objArr = this.b;
        InterfaceC1178mm interfaceC1178mm = (InterfaceC1178mm) objArr[i2];
        if (interfaceC1178mm != null) {
            return interfaceC1178mm;
        }
        InterfaceC1178mm interfaceC1178mmA = C1011jm.c.a((Class) objArr[i2 + 1]);
        this.b[i2] = interfaceC1178mmA;
        return interfaceC1178mmA;
    }

    public final int o(Object obj) {
        int i;
        int iE;
        int iC;
        int i2;
        int iX;
        int iZ;
        Unsafe unsafe = s;
        int i3 = -1;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < this.a.length; i6 += 3) {
            int iO = O(i6);
            int i7 = this.a[i6];
            int iN = N(iO);
            if (iN <= 17) {
                int i8 = this.a[i6 + 2];
                int i9 = 1048575 & i8;
                i = 1 << (i8 >>> 20);
                if (i9 != i3) {
                    i5 = unsafe.getInt(obj, i9);
                    i3 = i9;
                }
            } else {
                i = 0;
            }
            long jZ = z(iO);
            switch (iN) {
                case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                    if ((i5 & i) != 0) {
                        iE = AbstractC1342pl.e(i7, 0.0d);
                        i4 += iE;
                    }
                    break;
                case 1:
                    if ((i5 & i) != 0) {
                        iE = AbstractC1342pl.i(i7, 0.0f);
                        i4 += iE;
                    }
                    break;
                case 2:
                    if ((i5 & i) != 0) {
                        iE = AbstractC1342pl.m(i7, unsafe.getLong(obj, jZ));
                        i4 += iE;
                    }
                    break;
                case 3:
                    if ((i5 & i) != 0) {
                        iE = AbstractC1342pl.A(i7, unsafe.getLong(obj, jZ));
                        i4 += iE;
                    }
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    if ((i5 & i) != 0) {
                        iE = AbstractC1342pl.k(i7, unsafe.getInt(obj, jZ));
                        i4 += iE;
                    }
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    if ((i5 & i) != 0) {
                        iE = AbstractC1342pl.h(i7, 0L);
                        i4 += iE;
                    }
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    if ((i5 & i) != 0) {
                        iE = AbstractC1342pl.g(i7, 0);
                        i4 += iE;
                    }
                    break;
                case 7:
                    if ((i5 & i) != 0) {
                        iE = AbstractC1342pl.b(i7, true);
                        i4 += iE;
                    }
                    break;
                case 8:
                    if ((i5 & i) != 0) {
                        Object object = unsafe.getObject(obj, jZ);
                        iC = object instanceof AbstractC0731el ? AbstractC1342pl.c(i7, (AbstractC0731el) object) : AbstractC1342pl.v(i7, (String) object);
                        i4 += iC;
                    }
                    break;
                case 9:
                    if ((i5 & i) != 0) {
                        iE = AbstractC1288om.o(i7, unsafe.getObject(obj, jZ), n(i6));
                        i4 += iE;
                    }
                    break;
                case 10:
                    if ((i5 & i) != 0) {
                        iE = AbstractC1342pl.c(i7, (AbstractC0731el) unsafe.getObject(obj, jZ));
                        i4 += iE;
                    }
                    break;
                case 11:
                    if ((i5 & i) != 0) {
                        iE = AbstractC1342pl.y(i7, unsafe.getInt(obj, jZ));
                        i4 += iE;
                    }
                    break;
                case 12:
                    if ((i5 & i) != 0) {
                        iE = AbstractC1342pl.f(i7, unsafe.getInt(obj, jZ));
                        i4 += iE;
                    }
                    break;
                case 13:
                    if ((i5 & i) != 0) {
                        iE = AbstractC1342pl.p(i7, 0);
                        i4 += iE;
                    }
                    break;
                case 14:
                    if ((i5 & i) != 0) {
                        iE = AbstractC1342pl.q(i7, 0L);
                        i4 += iE;
                    }
                    break;
                case 15:
                    if ((i5 & i) != 0) {
                        iE = AbstractC1342pl.r(i7, unsafe.getInt(obj, jZ));
                        i4 += iE;
                    }
                    break;
                case 16:
                    if ((i5 & i) != 0) {
                        iE = AbstractC1342pl.t(i7, unsafe.getLong(obj, jZ));
                        i4 += iE;
                    }
                    break;
                case 17:
                    if ((i5 & i) != 0) {
                        iE = AbstractC1342pl.j(i7, (InterfaceC0509am) unsafe.getObject(obj, jZ), n(i6));
                        i4 += iE;
                    }
                    break;
                case 18:
                    iE = AbstractC1288om.h(i7, (List) unsafe.getObject(obj, jZ), false);
                    i4 += iE;
                    break;
                case 19:
                    iE = AbstractC1288om.f(i7, (List) unsafe.getObject(obj, jZ), false);
                    i4 += iE;
                    break;
                case 20:
                    iE = AbstractC1288om.m(i7, (List) unsafe.getObject(obj, jZ), false);
                    i4 += iE;
                    break;
                case 21:
                    iE = AbstractC1288om.x(i7, (List) unsafe.getObject(obj, jZ), false);
                    i4 += iE;
                    break;
                case 22:
                    iE = AbstractC1288om.k(i7, (List) unsafe.getObject(obj, jZ), false);
                    i4 += iE;
                    break;
                case 23:
                    iE = AbstractC1288om.h(i7, (List) unsafe.getObject(obj, jZ), false);
                    i4 += iE;
                    break;
                case 24:
                    iE = AbstractC1288om.f(i7, (List) unsafe.getObject(obj, jZ), false);
                    i4 += iE;
                    break;
                case 25:
                    iE = AbstractC1288om.a(i7, (List) unsafe.getObject(obj, jZ), false);
                    i4 += iE;
                    break;
                case 26:
                    iE = AbstractC1288om.u(i7, (List) unsafe.getObject(obj, jZ));
                    i4 += iE;
                    break;
                case 27:
                    iE = AbstractC1288om.p(i7, (List) unsafe.getObject(obj, jZ), n(i6));
                    i4 += iE;
                    break;
                case 28:
                    iE = AbstractC1288om.c(i7, (List) unsafe.getObject(obj, jZ));
                    i4 += iE;
                    break;
                case 29:
                    iE = AbstractC1288om.v(i7, (List) unsafe.getObject(obj, jZ), false);
                    i4 += iE;
                    break;
                case 30:
                    iE = AbstractC1288om.d(i7, (List) unsafe.getObject(obj, jZ), false);
                    i4 += iE;
                    break;
                case 31:
                    iE = AbstractC1288om.f(i7, (List) unsafe.getObject(obj, jZ), false);
                    i4 += iE;
                    break;
                case 32:
                    iE = AbstractC1288om.h(i7, (List) unsafe.getObject(obj, jZ), false);
                    i4 += iE;
                    break;
                case 33:
                    iE = AbstractC1288om.q(i7, (List) unsafe.getObject(obj, jZ), false);
                    i4 += iE;
                    break;
                case 34:
                    iE = AbstractC1288om.s(i7, (List) unsafe.getObject(obj, jZ), false);
                    i4 += iE;
                    break;
                case 35:
                    i2 = AbstractC1288om.i((List) unsafe.getObject(obj, jZ));
                    if (i2 > 0) {
                        iX = AbstractC1342pl.x(i7);
                        iZ = AbstractC1342pl.z(i2);
                        i4 += iZ + iX + i2;
                    }
                    break;
                case 36:
                    i2 = AbstractC1288om.g((List) unsafe.getObject(obj, jZ));
                    if (i2 > 0) {
                        iX = AbstractC1342pl.x(i7);
                        iZ = AbstractC1342pl.z(i2);
                        i4 += iZ + iX + i2;
                    }
                    break;
                case 37:
                    i2 = AbstractC1288om.n((List) unsafe.getObject(obj, jZ));
                    if (i2 > 0) {
                        iX = AbstractC1342pl.x(i7);
                        iZ = AbstractC1342pl.z(i2);
                        i4 += iZ + iX + i2;
                    }
                    break;
                case 38:
                    i2 = AbstractC1288om.y((List) unsafe.getObject(obj, jZ));
                    if (i2 > 0) {
                        iX = AbstractC1342pl.x(i7);
                        iZ = AbstractC1342pl.z(i2);
                        i4 += iZ + iX + i2;
                    }
                    break;
                case 39:
                    i2 = AbstractC1288om.l((List) unsafe.getObject(obj, jZ));
                    if (i2 > 0) {
                        iX = AbstractC1342pl.x(i7);
                        iZ = AbstractC1342pl.z(i2);
                        i4 += iZ + iX + i2;
                    }
                    break;
                case 40:
                    i2 = AbstractC1288om.i((List) unsafe.getObject(obj, jZ));
                    if (i2 > 0) {
                        iX = AbstractC1342pl.x(i7);
                        iZ = AbstractC1342pl.z(i2);
                        i4 += iZ + iX + i2;
                    }
                    break;
                case 41:
                    i2 = AbstractC1288om.g((List) unsafe.getObject(obj, jZ));
                    if (i2 > 0) {
                        iX = AbstractC1342pl.x(i7);
                        iZ = AbstractC1342pl.z(i2);
                        i4 += iZ + iX + i2;
                    }
                    break;
                case 42:
                    i2 = AbstractC1288om.b((List) unsafe.getObject(obj, jZ));
                    if (i2 > 0) {
                        iX = AbstractC1342pl.x(i7);
                        iZ = AbstractC1342pl.z(i2);
                        i4 += iZ + iX + i2;
                    }
                    break;
                case 43:
                    i2 = AbstractC1288om.w((List) unsafe.getObject(obj, jZ));
                    if (i2 > 0) {
                        iX = AbstractC1342pl.x(i7);
                        iZ = AbstractC1342pl.z(i2);
                        i4 += iZ + iX + i2;
                    }
                    break;
                case 44:
                    i2 = AbstractC1288om.e((List) unsafe.getObject(obj, jZ));
                    if (i2 > 0) {
                        iX = AbstractC1342pl.x(i7);
                        iZ = AbstractC1342pl.z(i2);
                        i4 += iZ + iX + i2;
                    }
                    break;
                case 45:
                    i2 = AbstractC1288om.g((List) unsafe.getObject(obj, jZ));
                    if (i2 > 0) {
                        iX = AbstractC1342pl.x(i7);
                        iZ = AbstractC1342pl.z(i2);
                        i4 += iZ + iX + i2;
                    }
                    break;
                case 46:
                    i2 = AbstractC1288om.i((List) unsafe.getObject(obj, jZ));
                    if (i2 > 0) {
                        iX = AbstractC1342pl.x(i7);
                        iZ = AbstractC1342pl.z(i2);
                        i4 += iZ + iX + i2;
                    }
                    break;
                case 47:
                    i2 = AbstractC1288om.r((List) unsafe.getObject(obj, jZ));
                    if (i2 > 0) {
                        iX = AbstractC1342pl.x(i7);
                        iZ = AbstractC1342pl.z(i2);
                        i4 += iZ + iX + i2;
                    }
                    break;
                case 48:
                    i2 = AbstractC1288om.t((List) unsafe.getObject(obj, jZ));
                    if (i2 > 0) {
                        iX = AbstractC1342pl.x(i7);
                        iZ = AbstractC1342pl.z(i2);
                        i4 += iZ + iX + i2;
                    }
                    break;
                case 49:
                    iE = AbstractC1288om.j(i7, (List) unsafe.getObject(obj, jZ), n(i6));
                    i4 += iE;
                    break;
                case 50:
                    ((C0406Wl) this.q).a(i7, unsafe.getObject(obj, jZ), m(i6));
                    i4 += 0;
                    break;
                case 51:
                    if (s(obj, i7, i6)) {
                        iE = AbstractC1342pl.e(i7, 0.0d);
                        i4 += iE;
                    }
                    break;
                case 52:
                    if (s(obj, i7, i6)) {
                        iE = AbstractC1342pl.i(i7, 0.0f);
                        i4 += iE;
                    }
                    break;
                case 53:
                    if (s(obj, i7, i6)) {
                        iE = AbstractC1342pl.m(i7, E(obj, jZ));
                        i4 += iE;
                    }
                    break;
                case 54:
                    if (s(obj, i7, i6)) {
                        iE = AbstractC1342pl.A(i7, E(obj, jZ));
                        i4 += iE;
                    }
                    break;
                case 55:
                    if (s(obj, i7, i6)) {
                        iE = AbstractC1342pl.k(i7, D(obj, jZ));
                        i4 += iE;
                    }
                    break;
                case 56:
                    if (s(obj, i7, i6)) {
                        iE = AbstractC1342pl.h(i7, 0L);
                        i4 += iE;
                    }
                    break;
                case 57:
                    if (s(obj, i7, i6)) {
                        iE = AbstractC1342pl.g(i7, 0);
                        i4 += iE;
                    }
                    break;
                case 58:
                    if (s(obj, i7, i6)) {
                        iE = AbstractC1342pl.b(i7, true);
                        i4 += iE;
                    }
                    break;
                case 59:
                    if (s(obj, i7, i6)) {
                        Object object2 = unsafe.getObject(obj, jZ);
                        iC = object2 instanceof AbstractC0731el ? AbstractC1342pl.c(i7, (AbstractC0731el) object2) : AbstractC1342pl.v(i7, (String) object2);
                        i4 += iC;
                    }
                    break;
                case 60:
                    if (s(obj, i7, i6)) {
                        iE = AbstractC1288om.o(i7, unsafe.getObject(obj, jZ), n(i6));
                        i4 += iE;
                    }
                    break;
                case 61:
                    if (s(obj, i7, i6)) {
                        iE = AbstractC1342pl.c(i7, (AbstractC0731el) unsafe.getObject(obj, jZ));
                        i4 += iE;
                    }
                    break;
                case 62:
                    if (s(obj, i7, i6)) {
                        iE = AbstractC1342pl.y(i7, D(obj, jZ));
                        i4 += iE;
                    }
                    break;
                case 63:
                    if (s(obj, i7, i6)) {
                        iE = AbstractC1342pl.f(i7, D(obj, jZ));
                        i4 += iE;
                    }
                    break;
                case 64:
                    if (s(obj, i7, i6)) {
                        iE = AbstractC1342pl.p(i7, 0);
                        i4 += iE;
                    }
                    break;
                case 65:
                    if (s(obj, i7, i6)) {
                        iE = AbstractC1342pl.q(i7, 0L);
                        i4 += iE;
                    }
                    break;
                case 66:
                    if (s(obj, i7, i6)) {
                        iE = AbstractC1342pl.r(i7, D(obj, jZ));
                        i4 += iE;
                    }
                    break;
                case 67:
                    if (s(obj, i7, i6)) {
                        iE = AbstractC1342pl.t(i7, E(obj, jZ));
                        i4 += iE;
                    }
                    break;
                case 68:
                    if (s(obj, i7, i6)) {
                        iE = AbstractC1342pl.j(i7, (InterfaceC0509am) unsafe.getObject(obj, jZ), n(i6));
                        i4 += iE;
                    }
                    break;
            }
        }
        Objects.requireNonNull((C1617um) this.o);
        return ((AbstractC0065Dl) obj).unknownFields.a() + i4;
    }

    public final int p(Object obj) {
        int iE;
        int i;
        int iX;
        int iZ;
        Unsafe unsafe = s;
        int i2 = 0;
        for (int i3 = 0; i3 < this.a.length; i3 += 3) {
            int iO = O(i3);
            int iN = N(iO);
            int i4 = this.a[i3];
            long jZ = z(iO);
            if (iN >= EnumC1781xl.d0.s && iN <= EnumC1781xl.q0.s) {
                int i5 = this.a[i3 + 2];
            }
            switch (iN) {
                case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                    if (q(obj, i3)) {
                        iE = AbstractC1342pl.e(i4, 0.0d);
                        i2 += iE;
                    }
                    break;
                case 1:
                    if (q(obj, i3)) {
                        iE = AbstractC1342pl.i(i4, 0.0f);
                        i2 += iE;
                    }
                    break;
                case 2:
                    if (q(obj, i3)) {
                        iE = AbstractC1342pl.m(i4, AbstractC0066Dm.m(obj, jZ));
                        i2 += iE;
                    }
                    break;
                case 3:
                    if (q(obj, i3)) {
                        iE = AbstractC1342pl.A(i4, AbstractC0066Dm.m(obj, jZ));
                        i2 += iE;
                    }
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    if (q(obj, i3)) {
                        iE = AbstractC1342pl.k(i4, AbstractC0066Dm.l(obj, jZ));
                        i2 += iE;
                    }
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    if (q(obj, i3)) {
                        iE = AbstractC1342pl.h(i4, 0L);
                        i2 += iE;
                    }
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    if (q(obj, i3)) {
                        iE = AbstractC1342pl.g(i4, 0);
                        i2 += iE;
                    }
                    break;
                case 7:
                    if (q(obj, i3)) {
                        iE = AbstractC1342pl.b(i4, true);
                        i2 += iE;
                    }
                    break;
                case 8:
                    if (q(obj, i3)) {
                        Object objN = AbstractC0066Dm.n(obj, jZ);
                        iE = objN instanceof AbstractC0731el ? AbstractC1342pl.c(i4, (AbstractC0731el) objN) : AbstractC1342pl.v(i4, (String) objN);
                        i2 += iE;
                    }
                    break;
                case 9:
                    if (q(obj, i3)) {
                        iE = AbstractC1288om.o(i4, AbstractC0066Dm.n(obj, jZ), n(i3));
                        i2 += iE;
                    }
                    break;
                case 10:
                    if (q(obj, i3)) {
                        iE = AbstractC1342pl.c(i4, (AbstractC0731el) AbstractC0066Dm.n(obj, jZ));
                        i2 += iE;
                    }
                    break;
                case 11:
                    if (q(obj, i3)) {
                        iE = AbstractC1342pl.y(i4, AbstractC0066Dm.l(obj, jZ));
                        i2 += iE;
                    }
                    break;
                case 12:
                    if (q(obj, i3)) {
                        iE = AbstractC1342pl.f(i4, AbstractC0066Dm.l(obj, jZ));
                        i2 += iE;
                    }
                    break;
                case 13:
                    if (q(obj, i3)) {
                        iE = AbstractC1342pl.p(i4, 0);
                        i2 += iE;
                    }
                    break;
                case 14:
                    if (q(obj, i3)) {
                        iE = AbstractC1342pl.q(i4, 0L);
                        i2 += iE;
                    }
                    break;
                case 15:
                    if (q(obj, i3)) {
                        iE = AbstractC1342pl.r(i4, AbstractC0066Dm.l(obj, jZ));
                        i2 += iE;
                    }
                    break;
                case 16:
                    if (q(obj, i3)) {
                        iE = AbstractC1342pl.t(i4, AbstractC0066Dm.m(obj, jZ));
                        i2 += iE;
                    }
                    break;
                case 17:
                    if (q(obj, i3)) {
                        iE = AbstractC1342pl.j(i4, (InterfaceC0509am) AbstractC0066Dm.n(obj, jZ), n(i3));
                        i2 += iE;
                    }
                    break;
                case 18:
                    iE = AbstractC1288om.h(i4, t(obj, jZ), false);
                    i2 += iE;
                    break;
                case 19:
                    iE = AbstractC1288om.f(i4, t(obj, jZ), false);
                    i2 += iE;
                    break;
                case 20:
                    iE = AbstractC1288om.m(i4, t(obj, jZ), false);
                    i2 += iE;
                    break;
                case 21:
                    iE = AbstractC1288om.x(i4, t(obj, jZ), false);
                    i2 += iE;
                    break;
                case 22:
                    iE = AbstractC1288om.k(i4, t(obj, jZ), false);
                    i2 += iE;
                    break;
                case 23:
                    iE = AbstractC1288om.h(i4, t(obj, jZ), false);
                    i2 += iE;
                    break;
                case 24:
                    iE = AbstractC1288om.f(i4, t(obj, jZ), false);
                    i2 += iE;
                    break;
                case 25:
                    iE = AbstractC1288om.a(i4, t(obj, jZ), false);
                    i2 += iE;
                    break;
                case 26:
                    iE = AbstractC1288om.u(i4, t(obj, jZ));
                    i2 += iE;
                    break;
                case 27:
                    iE = AbstractC1288om.p(i4, t(obj, jZ), n(i3));
                    i2 += iE;
                    break;
                case 28:
                    iE = AbstractC1288om.c(i4, t(obj, jZ));
                    i2 += iE;
                    break;
                case 29:
                    iE = AbstractC1288om.v(i4, t(obj, jZ), false);
                    i2 += iE;
                    break;
                case 30:
                    iE = AbstractC1288om.d(i4, t(obj, jZ), false);
                    i2 += iE;
                    break;
                case 31:
                    iE = AbstractC1288om.f(i4, t(obj, jZ), false);
                    i2 += iE;
                    break;
                case 32:
                    iE = AbstractC1288om.h(i4, t(obj, jZ), false);
                    i2 += iE;
                    break;
                case 33:
                    iE = AbstractC1288om.q(i4, t(obj, jZ), false);
                    i2 += iE;
                    break;
                case 34:
                    iE = AbstractC1288om.s(i4, t(obj, jZ), false);
                    i2 += iE;
                    break;
                case 35:
                    i = AbstractC1288om.i((List) unsafe.getObject(obj, jZ));
                    if (i > 0) {
                        iX = AbstractC1342pl.x(i4);
                        iZ = AbstractC1342pl.z(i);
                        i2 = iZ + iX + i + i2;
                    }
                    break;
                case 36:
                    i = AbstractC1288om.g((List) unsafe.getObject(obj, jZ));
                    if (i > 0) {
                        iX = AbstractC1342pl.x(i4);
                        iZ = AbstractC1342pl.z(i);
                        i2 = iZ + iX + i + i2;
                    }
                    break;
                case 37:
                    i = AbstractC1288om.n((List) unsafe.getObject(obj, jZ));
                    if (i > 0) {
                        iX = AbstractC1342pl.x(i4);
                        iZ = AbstractC1342pl.z(i);
                        i2 = iZ + iX + i + i2;
                    }
                    break;
                case 38:
                    i = AbstractC1288om.y((List) unsafe.getObject(obj, jZ));
                    if (i > 0) {
                        iX = AbstractC1342pl.x(i4);
                        iZ = AbstractC1342pl.z(i);
                        i2 = iZ + iX + i + i2;
                    }
                    break;
                case 39:
                    i = AbstractC1288om.l((List) unsafe.getObject(obj, jZ));
                    if (i > 0) {
                        iX = AbstractC1342pl.x(i4);
                        iZ = AbstractC1342pl.z(i);
                        i2 = iZ + iX + i + i2;
                    }
                    break;
                case 40:
                    i = AbstractC1288om.i((List) unsafe.getObject(obj, jZ));
                    if (i > 0) {
                        iX = AbstractC1342pl.x(i4);
                        iZ = AbstractC1342pl.z(i);
                        i2 = iZ + iX + i + i2;
                    }
                    break;
                case 41:
                    i = AbstractC1288om.g((List) unsafe.getObject(obj, jZ));
                    if (i > 0) {
                        iX = AbstractC1342pl.x(i4);
                        iZ = AbstractC1342pl.z(i);
                        i2 = iZ + iX + i + i2;
                    }
                    break;
                case 42:
                    i = AbstractC1288om.b((List) unsafe.getObject(obj, jZ));
                    if (i > 0) {
                        iX = AbstractC1342pl.x(i4);
                        iZ = AbstractC1342pl.z(i);
                        i2 = iZ + iX + i + i2;
                    }
                    break;
                case 43:
                    i = AbstractC1288om.w((List) unsafe.getObject(obj, jZ));
                    if (i > 0) {
                        iX = AbstractC1342pl.x(i4);
                        iZ = AbstractC1342pl.z(i);
                        i2 = iZ + iX + i + i2;
                    }
                    break;
                case 44:
                    i = AbstractC1288om.e((List) unsafe.getObject(obj, jZ));
                    if (i > 0) {
                        iX = AbstractC1342pl.x(i4);
                        iZ = AbstractC1342pl.z(i);
                        i2 = iZ + iX + i + i2;
                    }
                    break;
                case 45:
                    i = AbstractC1288om.g((List) unsafe.getObject(obj, jZ));
                    if (i > 0) {
                        iX = AbstractC1342pl.x(i4);
                        iZ = AbstractC1342pl.z(i);
                        i2 = iZ + iX + i + i2;
                    }
                    break;
                case 46:
                    i = AbstractC1288om.i((List) unsafe.getObject(obj, jZ));
                    if (i > 0) {
                        iX = AbstractC1342pl.x(i4);
                        iZ = AbstractC1342pl.z(i);
                        i2 = iZ + iX + i + i2;
                    }
                    break;
                case 47:
                    i = AbstractC1288om.r((List) unsafe.getObject(obj, jZ));
                    if (i > 0) {
                        iX = AbstractC1342pl.x(i4);
                        iZ = AbstractC1342pl.z(i);
                        i2 = iZ + iX + i + i2;
                    }
                    break;
                case 48:
                    i = AbstractC1288om.t((List) unsafe.getObject(obj, jZ));
                    if (i > 0) {
                        iX = AbstractC1342pl.x(i4);
                        iZ = AbstractC1342pl.z(i);
                        i2 = iZ + iX + i + i2;
                    }
                    break;
                case 49:
                    iE = AbstractC1288om.j(i4, t(obj, jZ), n(i3));
                    i2 += iE;
                    break;
                case 50:
                    ((C0406Wl) this.q).a(i4, AbstractC0066Dm.n(obj, jZ), m(i3));
                    i2 += 0;
                    break;
                case 51:
                    if (s(obj, i4, i3)) {
                        iE = AbstractC1342pl.e(i4, 0.0d);
                        i2 += iE;
                    }
                    break;
                case 52:
                    if (s(obj, i4, i3)) {
                        iE = AbstractC1342pl.i(i4, 0.0f);
                        i2 += iE;
                    }
                    break;
                case 53:
                    if (s(obj, i4, i3)) {
                        iE = AbstractC1342pl.m(i4, E(obj, jZ));
                        i2 += iE;
                    }
                    break;
                case 54:
                    if (s(obj, i4, i3)) {
                        iE = AbstractC1342pl.A(i4, E(obj, jZ));
                        i2 += iE;
                    }
                    break;
                case 55:
                    if (s(obj, i4, i3)) {
                        iE = AbstractC1342pl.k(i4, D(obj, jZ));
                        i2 += iE;
                    }
                    break;
                case 56:
                    if (s(obj, i4, i3)) {
                        iE = AbstractC1342pl.h(i4, 0L);
                        i2 += iE;
                    }
                    break;
                case 57:
                    if (s(obj, i4, i3)) {
                        iE = AbstractC1342pl.g(i4, 0);
                        i2 += iE;
                    }
                    break;
                case 58:
                    if (s(obj, i4, i3)) {
                        iE = AbstractC1342pl.b(i4, true);
                        i2 += iE;
                    }
                    break;
                case 59:
                    if (s(obj, i4, i3)) {
                        Object objN2 = AbstractC0066Dm.n(obj, jZ);
                        iE = objN2 instanceof AbstractC0731el ? AbstractC1342pl.c(i4, (AbstractC0731el) objN2) : AbstractC1342pl.v(i4, (String) objN2);
                        i2 += iE;
                    }
                    break;
                case 60:
                    if (s(obj, i4, i3)) {
                        iE = AbstractC1288om.o(i4, AbstractC0066Dm.n(obj, jZ), n(i3));
                        i2 += iE;
                    }
                    break;
                case 61:
                    if (s(obj, i4, i3)) {
                        iE = AbstractC1342pl.c(i4, (AbstractC0731el) AbstractC0066Dm.n(obj, jZ));
                        i2 += iE;
                    }
                    break;
                case 62:
                    if (s(obj, i4, i3)) {
                        iE = AbstractC1342pl.y(i4, D(obj, jZ));
                        i2 += iE;
                    }
                    break;
                case 63:
                    if (s(obj, i4, i3)) {
                        iE = AbstractC1342pl.f(i4, D(obj, jZ));
                        i2 += iE;
                    }
                    break;
                case 64:
                    if (s(obj, i4, i3)) {
                        iE = AbstractC1342pl.p(i4, 0);
                        i2 += iE;
                    }
                    break;
                case 65:
                    if (s(obj, i4, i3)) {
                        iE = AbstractC1342pl.q(i4, 0L);
                        i2 += iE;
                    }
                    break;
                case 66:
                    if (s(obj, i4, i3)) {
                        iE = AbstractC1342pl.r(i4, D(obj, jZ));
                        i2 += iE;
                    }
                    break;
                case 67:
                    if (s(obj, i4, i3)) {
                        iE = AbstractC1342pl.t(i4, E(obj, jZ));
                        i2 += iE;
                    }
                    break;
                case 68:
                    if (s(obj, i4, i3)) {
                        iE = AbstractC1342pl.j(i4, (InterfaceC0509am) AbstractC0066Dm.n(obj, jZ), n(i3));
                        i2 += iE;
                    }
                    break;
            }
        }
        Objects.requireNonNull((C1617um) this.o);
        return ((AbstractC0065Dl) obj).unknownFields.a() + i2;
    }

    public final boolean q(Object obj, int i) {
        if (!this.h) {
            int i2 = this.a[i + 2];
            return (AbstractC0066Dm.l(obj, (long) (i2 & 1048575)) & (1 << (i2 >>> 20))) != 0;
        }
        int i3 = this.a[i + 1];
        long jZ = z(i3);
        switch (N(i3)) {
            case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                return AbstractC0066Dm.j(obj, jZ) != 0.0d;
            case 1:
                return AbstractC0066Dm.k(obj, jZ) != 0.0f;
            case 2:
                return AbstractC0066Dm.m(obj, jZ) != 0;
            case 3:
                return AbstractC0066Dm.m(obj, jZ) != 0;
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                return AbstractC0066Dm.l(obj, jZ) != 0;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                return AbstractC0066Dm.m(obj, jZ) != 0;
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                return AbstractC0066Dm.l(obj, jZ) != 0;
            case 7:
                return AbstractC0066Dm.f(obj, jZ);
            case 8:
                Object objN = AbstractC0066Dm.n(obj, jZ);
                if (objN instanceof String) {
                    return !((String) objN).isEmpty();
                }
                if (objN instanceof AbstractC0731el) {
                    return !AbstractC0731el.t.equals(objN);
                }
                throw new IllegalArgumentException();
            case 9:
                return AbstractC0066Dm.n(obj, jZ) != null;
            case 10:
                return !AbstractC0731el.t.equals(AbstractC0066Dm.n(obj, jZ));
            case 11:
                return AbstractC0066Dm.l(obj, jZ) != 0;
            case 12:
                return AbstractC0066Dm.l(obj, jZ) != 0;
            case 13:
                return AbstractC0066Dm.l(obj, jZ) != 0;
            case 14:
                return AbstractC0066Dm.m(obj, jZ) != 0;
            case 15:
                return AbstractC0066Dm.l(obj, jZ) != 0;
            case 16:
                return AbstractC0066Dm.m(obj, jZ) != 0;
            case 17:
                return AbstractC0066Dm.n(obj, jZ) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    public final boolean r(Object obj, int i, int i2, int i3) {
        return this.h ? q(obj, i) : (i2 & i3) != 0;
    }

    public final boolean s(Object obj, int i, int i2) {
        return AbstractC0066Dm.l(obj, (long) (this.a[i2 + 2] & 1048575)) == i;
    }

    public final void u(Object obj, int i, Object obj2, C1506sl c1506sl, InterfaceC1123lm interfaceC1123lm) {
        long jZ = z(this.a[i + 1]);
        Object objN = AbstractC0066Dm.n(obj, jZ);
        if (objN != null) {
            Objects.requireNonNull((C0406Wl) this.q);
            if (!((C0370Ul) objN).s) {
                Object objC = ((C0406Wl) this.q).c(obj2);
                ((C0406Wl) this.q).b(objC, objN);
                AbstractC0066Dm.f.q(obj, jZ, objC);
                objN = objC;
            }
        } else {
            objN = ((C0406Wl) this.q).c(obj2);
            AbstractC0066Dm.f.q(obj, jZ, objN);
        }
        Objects.requireNonNull((C0406Wl) this.q);
        Objects.requireNonNull((C0406Wl) this.q);
        throw null;
    }

    public final void v(Object obj, Object obj2, int i) {
        long jZ = z(this.a[i + 1]);
        if (q(obj2, i)) {
            Object objN = AbstractC0066Dm.n(obj, jZ);
            Object objN2 = AbstractC0066Dm.n(obj2, jZ);
            if (objN != null && objN2 != null) {
                AbstractC0066Dm.f.q(obj, jZ, AbstractC0119Gl.f(objN, objN2));
                L(obj, i);
            } else if (objN2 != null) {
                AbstractC0066Dm.f.q(obj, jZ, objN2);
                L(obj, i);
            }
        }
    }

    public final void w(Object obj, Object obj2, int i) {
        int[] iArr = this.a;
        int i2 = iArr[i + 1];
        int i3 = iArr[i];
        long jZ = z(i2);
        if (s(obj2, i3, i)) {
            Object objN = AbstractC0066Dm.n(obj, jZ);
            Object objN2 = AbstractC0066Dm.n(obj2, jZ);
            if (objN != null && objN2 != null) {
                AbstractC0066Dm.f.q(obj, jZ, AbstractC0119Gl.f(objN, objN2));
                M(obj, i3, i);
            } else if (objN2 != null) {
                AbstractC0066Dm.f.q(obj, jZ, objN2);
                M(obj, i3, i);
            }
        }
    }
}
