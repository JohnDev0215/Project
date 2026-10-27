package Q7;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import n7.AbstractC1996k;

/* JADX INFO: renamed from: Q7.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0662o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0661n f7189a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0661n f7190b;
    public static final C0661n c;
    public static final C0661n d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C0661n f7191e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C0661n f7192f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C0661n f7193g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final C0661n f7194h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final C0661n f7195i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final C0661n f7196j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final O f7197k;
    public static final O l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final O f7198m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final L8.o f7199n;
    public static final HashMap o;

    static {
        Z z6 = Z.c;
        C0661n c0661n = new C0661n(z6, 0);
        f7189a = c0661n;
        a0 a0Var = a0.c;
        C0661n c0661n2 = new C0661n(a0Var, 1);
        f7190b = c0661n2;
        b0 b0Var = b0.c;
        C0661n c0661n3 = new C0661n(b0Var, 2);
        c = c0661n3;
        W w10 = W.c;
        C0661n c0661n4 = new C0661n(w10, 3);
        d = c0661n4;
        c0 c0Var = c0.c;
        C0661n c0661n5 = new C0661n(c0Var, 4);
        f7191e = c0661n5;
        Y y10 = Y.c;
        C0661n c0661n6 = new C0661n(y10, 5);
        f7192f = c0661n6;
        V v10 = V.c;
        C0661n c0661n7 = new C0661n(v10, 6);
        f7193g = c0661n7;
        X x8 = X.c;
        C0661n c0661n8 = new C0661n(x8, 7);
        f7194h = c0661n8;
        d0 d0Var = d0.c;
        C0661n c0661n9 = new C0661n(d0Var, 8);
        f7195i = c0661n9;
        Collections.unmodifiableSet(AbstractC1996k.c0(new C0661n[]{c0661n, c0661n2, c0661n4, c0661n6}));
        HashMap map = new HashMap(6);
        map.put(c0661n2, 0);
        map.put(c0661n, 0);
        map.put(c0661n4, 1);
        map.put(c0661n3, 1);
        map.put(c0661n5, 2);
        Collections.unmodifiableMap(map);
        f7196j = c0661n5;
        f7197k = new O(2);
        l = new O(3);
        f7198m = new O(4);
        try {
            Iterator it = Arrays.asList(new L8.o[0]).iterator();
            f7199n = it.hasNext() ? (L8.o) it.next() : L8.o.f4091a;
            HashMap map2 = new HashMap();
            o = map2;
            map2.put(z6, c0661n);
            map2.put(a0Var, c0661n2);
            map2.put(b0Var, c0661n3);
            map2.put(w10, c0661n4);
            map2.put(c0Var, c0661n5);
            map2.put(y10, c0661n6);
            map2.put(v10, c0661n7);
            map2.put(x8, c0661n8);
            map2.put(d0Var, c0661n9);
        } catch (Throwable th) {
            throw new ServiceConfigurationError(th.getMessage(), th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void a(int r8) {
        /*
            r0 = 16
            if (r8 == r0) goto L7
            java.lang.String r1 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            goto L9
        L7:
            java.lang.String r1 = "@NotNull method %s.%s must not return null"
        L9:
            r2 = 3
            r3 = 2
            if (r8 == r0) goto Lf
            r4 = r2
            goto L10
        Lf:
            r4 = r3
        L10:
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.String r5 = "kotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibilities"
            r6 = 1
            r7 = 0
            if (r8 == r6) goto L3a
            if (r8 == r2) goto L3a
            r2 = 5
            if (r8 == r2) goto L3a
            r2 = 7
            if (r8 == r2) goto L3a
            switch(r8) {
                case 9: goto L3a;
                case 10: goto L35;
                case 11: goto L30;
                case 12: goto L35;
                case 13: goto L30;
                case 14: goto L2b;
                case 15: goto L2b;
                case 16: goto L28;
                default: goto L23;
            }
        L23:
            java.lang.String r2 = "what"
            r4[r7] = r2
            goto L3e
        L28:
            r4[r7] = r5
            goto L3e
        L2b:
            java.lang.String r2 = "visibility"
            r4[r7] = r2
            goto L3e
        L30:
            java.lang.String r2 = "second"
            r4[r7] = r2
            goto L3e
        L35:
            java.lang.String r2 = "first"
            r4[r7] = r2
            goto L3e
        L3a:
            java.lang.String r2 = "from"
            r4[r7] = r2
        L3e:
            java.lang.String r2 = "toDescriptorVisibility"
            if (r8 == r0) goto L45
            r4[r6] = r5
            goto L47
        L45:
            r4[r6] = r2
        L47:
            switch(r8) {
                case 2: goto L70;
                case 3: goto L70;
                case 4: goto L6b;
                case 5: goto L6b;
                case 6: goto L66;
                case 7: goto L66;
                case 8: goto L61;
                case 9: goto L61;
                case 10: goto L5c;
                case 11: goto L5c;
                case 12: goto L57;
                case 13: goto L57;
                case 14: goto L52;
                case 15: goto L4f;
                case 16: goto L74;
                default: goto L4a;
            }
        L4a:
            java.lang.String r2 = "isVisible"
            r4[r3] = r2
            goto L74
        L4f:
            r4[r3] = r2
            goto L74
        L52:
            java.lang.String r2 = "isPrivate"
            r4[r3] = r2
            goto L74
        L57:
            java.lang.String r2 = "compare"
            r4[r3] = r2
            goto L74
        L5c:
            java.lang.String r2 = "compareLocal"
            r4[r3] = r2
            goto L74
        L61:
            java.lang.String r2 = "findInvisibleMember"
            r4[r3] = r2
            goto L74
        L66:
            java.lang.String r2 = "inSameFile"
            r4[r3] = r2
            goto L74
        L6b:
            java.lang.String r2 = "isVisibleWithAnyReceiver"
            r4[r3] = r2
            goto L74
        L70:
            java.lang.String r2 = "isVisibleIgnoringReceiver"
            r4[r3] = r2
        L74:
            java.lang.String r1 = java.lang.String.format(r1, r4)
            if (r8 == r0) goto L80
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            r8.<init>(r1)
            goto L85
        L80:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            r8.<init>(r1)
        L85:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: Q7.AbstractC0662o.a(int):void");
    }

    public static Integer b(C0661n c0661n, C0661n c0661n2) {
        if (c0661n == null) {
            a(12);
            throw null;
        }
        if (c0661n2 == null) {
            a(13);
            throw null;
        }
        f0 f0Var = c0661n.f7187a;
        f0 f0Var2 = c0661n2.f7187a;
        Integer numA = f0Var.a(f0Var2);
        if (numA != null) {
            return numA;
        }
        Integer numA2 = f0Var2.a(f0Var);
        if (numA2 != null) {
            return Integer.valueOf(-numA2.intValue());
        }
        return null;
    }

    public static InterfaceC0660m c(O o4, InterfaceC0650c interfaceC0650c, InterfaceC0657j interfaceC0657j) {
        InterfaceC0660m interfaceC0660mC;
        if (interfaceC0650c == null) {
            a(8);
            throw null;
        }
        if (interfaceC0657j == null) {
            a(9);
            throw null;
        }
        for (InterfaceC0660m interfaceC0660m = (InterfaceC0660m) interfaceC0650c.b1(); interfaceC0660m != null && interfaceC0660m.e() != f7192f; interfaceC0660m = (InterfaceC0660m) r8.d.i(interfaceC0660m, InterfaceC0660m.class, true)) {
            if (!interfaceC0660m.e().a(o4, interfaceC0660m, interfaceC0657j)) {
                return interfaceC0660m;
            }
        }
        if (!(interfaceC0650c instanceof T7.M) || (interfaceC0660mC = c(o4, ((T7.N) ((T7.M) interfaceC0650c)).f8818R, interfaceC0657j)) == null) {
            return null;
        }
        return interfaceC0660mC;
    }

    public static boolean d(InterfaceC0660m interfaceC0660m, InterfaceC0657j interfaceC0657j) {
        if (interfaceC0657j == null) {
            a(7);
            throw null;
        }
        O oF = r8.d.f(interfaceC0657j);
        if (oF != O.f7180m) {
            return oF.equals(r8.d.f(interfaceC0660m));
        }
        return false;
    }

    public static boolean e(C0661n c0661n) {
        if (c0661n != null) {
            return c0661n == f7189a || c0661n == f7190b;
        }
        a(14);
        throw null;
    }

    public static C0661n f(f0 f0Var) {
        if (f0Var == null) {
            a(15);
            throw null;
        }
        C0661n c0661n = (C0661n) o.get(f0Var);
        if (c0661n != null) {
            return c0661n;
        }
        throw new IllegalArgumentException("Inapplicable visibility: " + f0Var);
    }
}
