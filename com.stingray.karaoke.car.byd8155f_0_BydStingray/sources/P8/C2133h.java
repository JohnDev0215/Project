package p8;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: p8.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2133h {
    public static final C2133h c = new C2133h(0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z f20980a = new z(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f20981b;

    public C2133h() {
    }

    public static int c(EnumC2124L enumC2124L, Object obj) {
        switch (enumC2124L.ordinal()) {
            case 0:
                ((Double) obj).getClass();
                return 8;
            case 1:
                ((Float) obj).getClass();
                return 4;
            case 2:
                return A2.g.k(((Long) obj).longValue());
            case 3:
                return A2.g.k(((Long) obj).longValue());
            case 4:
                return A2.g.g(((Integer) obj).intValue());
            case 5:
                ((Long) obj).getClass();
                return 8;
            case 6:
                ((Integer) obj).getClass();
                return 4;
            case 7:
                ((Boolean) obj).getClass();
                return 1;
            case 8:
                try {
                    byte[] bytes = ((String) obj).getBytes("UTF-8");
                    return A2.g.j(bytes.length) + bytes.length;
                } catch (UnsupportedEncodingException e10) {
                    throw new RuntimeException("UTF-8 not supported.", e10);
                }
            case 9:
                return ((AbstractC2126a) obj).c();
            case 10:
                return A2.g.i((AbstractC2126a) obj);
            case 11:
                if (obj instanceof AbstractC2129d) {
                    AbstractC2129d abstractC2129d = (AbstractC2129d) obj;
                    return abstractC2129d.size() + A2.g.j(abstractC2129d.size());
                }
                byte[] bArr = (byte[]) obj;
                return A2.g.j(bArr.length) + bArr.length;
            case 12:
                return A2.g.j(((Integer) obj).intValue());
            case 13:
                return obj instanceof InterfaceC2140o ? A2.g.g(((InterfaceC2140o) obj).a()) : A2.g.g(((Integer) obj).intValue());
            case 14:
                ((Integer) obj).getClass();
                return 4;
            case 15:
                ((Long) obj).getClass();
                return 8;
            case 16:
                int iIntValue = ((Integer) obj).intValue();
                return A2.g.j((iIntValue >> 31) ^ (iIntValue << 1));
            case 17:
                long jLongValue = ((Long) obj).longValue();
                return A2.g.k((jLongValue >> 63) ^ (jLongValue << 1));
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int d(C2137l c2137l, Object obj) {
        EnumC2124L enumC2124L = c2137l.f20984m;
        boolean z6 = c2137l.f20985n;
        int i7 = c2137l.l;
        if (!z6) {
            int iL = A2.g.l(i7);
            if (enumC2124L == EnumC2124L.f20951p) {
                iL *= 2;
            }
            return c(enumC2124L, obj) + iL;
        }
        int iC = 0;
        for (Object obj2 : (List) obj) {
            int iL2 = A2.g.l(i7);
            if (enumC2124L == EnumC2124L.f20951p) {
                iL2 *= 2;
            }
            iC += c(enumC2124L, obj2) + iL2;
        }
        return iC;
    }

    public static boolean e(Map.Entry entry) {
        C2137l c2137l = (C2137l) entry.getKey();
        if (c2137l.f20984m.l != EnumC2125M.f20963u) {
            return true;
        }
        if (!c2137l.f20985n) {
            Object value = entry.getValue();
            if (value instanceof AbstractC2126a) {
                return ((AbstractC2126a) value).b();
            }
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        Iterator it = ((List) entry.getValue()).iterator();
        while (it.hasNext()) {
            if (!((AbstractC2126a) it.next()).b()) {
                return false;
            }
        }
        return true;
    }

    public static Object h(C2130e c2130e, EnumC2124L enumC2124L) {
        switch (enumC2124L.ordinal()) {
            case 0:
                return Double.valueOf(Double.longBitsToDouble(c2130e.j()));
            case 1:
                return Float.valueOf(Float.intBitsToFloat(c2130e.i()));
            case 2:
                return Long.valueOf(c2130e.l());
            case 3:
                return Long.valueOf(c2130e.l());
            case 4:
                return Integer.valueOf(c2130e.k());
            case 5:
                return Long.valueOf(c2130e.j());
            case 6:
                return Integer.valueOf(c2130e.i());
            case 7:
                return Boolean.valueOf(c2130e.l() != 0);
            case 8:
                int iK = c2130e.k();
                int i7 = c2130e.f20970b;
                int i10 = c2130e.d;
                if (iK > i7 - i10 || iK <= 0) {
                    return iK == 0 ? "" : new String(c2130e.h(iK), "UTF-8");
                }
                String str = new String(c2130e.f20969a, i10, iK, "UTF-8");
                c2130e.d += iK;
                return str;
            case 9:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
            case 10:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
            case 11:
                return c2130e.e();
            case 12:
                return Integer.valueOf(c2130e.k());
            case 13:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
            case 14:
                return Integer.valueOf(c2130e.i());
            case 15:
                return Long.valueOf(c2130e.j());
            case 16:
                int iK2 = c2130e.k();
                return Integer.valueOf((-(iK2 & 1)) ^ (iK2 >>> 1));
            case 17:
                long jL = c2130e.l();
                return Long.valueOf((-(jL & 1)) ^ (jL >>> 1));
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void j(p8.EnumC2124L r2, java.lang.Object r3) {
        /*
            r3.getClass()
            p8.M r2 = r2.l
            int r2 = r2.ordinal()
            r0 = 1
            r1 = 0
            switch(r2) {
                case 0: goto L36;
                case 1: goto L33;
                case 2: goto L30;
                case 3: goto L2d;
                case 4: goto L2a;
                case 5: goto L27;
                case 6: goto L1e;
                case 7: goto L12;
                case 8: goto Lf;
                default: goto Le;
            }
        Le:
            goto L38
        Lf:
            boolean r1 = r3 instanceof p8.AbstractC2126a
            goto L38
        L12:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L1c
            boolean r2 = r3 instanceof p8.InterfaceC2140o
            if (r2 == 0) goto L1b
            goto L1c
        L1b:
            r0 = r1
        L1c:
            r1 = r0
            goto L38
        L1e:
            boolean r2 = r3 instanceof p8.AbstractC2129d
            if (r2 != 0) goto L1c
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L1b
            goto L1c
        L27:
            boolean r1 = r3 instanceof java.lang.String
            goto L38
        L2a:
            boolean r1 = r3 instanceof java.lang.Boolean
            goto L38
        L2d:
            boolean r1 = r3 instanceof java.lang.Double
            goto L38
        L30:
            boolean r1 = r3 instanceof java.lang.Float
            goto L38
        L33:
            boolean r1 = r3 instanceof java.lang.Long
            goto L38
        L36:
            boolean r1 = r3 instanceof java.lang.Integer
        L38:
            if (r1 == 0) goto L3b
            return
        L3b:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p8.C2133h.j(p8.L, java.lang.Object):void");
    }

    public static void k(A2.g gVar, EnumC2124L enumC2124L, Object obj) throws IOException {
        switch (enumC2124L.ordinal()) {
            case 0:
                double dDoubleValue = ((Double) obj).doubleValue();
                gVar.getClass();
                gVar.K(Double.doubleToRawLongBits(dDoubleValue));
                break;
            case 1:
                float fFloatValue = ((Float) obj).floatValue();
                gVar.getClass();
                gVar.J(Float.floatToRawIntBits(fFloatValue));
                break;
            case 2:
                gVar.M(((Long) obj).longValue());
                break;
            case 3:
                gVar.M(((Long) obj).longValue());
                break;
            case 4:
                gVar.D(((Integer) obj).intValue());
                break;
            case 5:
                gVar.K(((Long) obj).longValue());
                break;
            case 6:
                gVar.J(((Integer) obj).intValue());
                break;
            case 7:
                gVar.G(((Boolean) obj).booleanValue() ? 1 : 0);
                break;
            case 8:
                gVar.getClass();
                byte[] bytes = ((String) obj).getBytes("UTF-8");
                gVar.L(bytes.length);
                gVar.I(bytes);
                break;
            case 9:
                gVar.getClass();
                ((AbstractC2126a) obj).f(gVar);
                break;
            case 10:
                gVar.F((AbstractC2126a) obj);
                break;
            case 11:
                if (!(obj instanceof AbstractC2129d)) {
                    byte[] bArr = (byte[]) obj;
                    gVar.getClass();
                    gVar.L(bArr.length);
                    gVar.I(bArr);
                } else {
                    AbstractC2129d abstractC2129d = (AbstractC2129d) obj;
                    gVar.getClass();
                    gVar.L(abstractC2129d.size());
                    gVar.H(abstractC2129d);
                }
                break;
            case 12:
                gVar.L(((Integer) obj).intValue());
                break;
            case 13:
                if (!(obj instanceof InterfaceC2140o)) {
                    gVar.D(((Integer) obj).intValue());
                } else {
                    gVar.D(((InterfaceC2140o) obj).a());
                }
                break;
            case 14:
                gVar.J(((Integer) obj).intValue());
                break;
            case 15:
                gVar.K(((Long) obj).longValue());
                break;
            case 16:
                int iIntValue = ((Integer) obj).intValue();
                gVar.L((iIntValue >> 31) ^ (iIntValue << 1));
                break;
            case 17:
                long jLongValue = ((Long) obj).longValue();
                gVar.M((jLongValue >> 63) ^ (jLongValue << 1));
                break;
        }
    }

    public final void a(C2137l c2137l, Object obj) {
        List arrayList;
        if (!c2137l.f20985n) {
            throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
        }
        j(c2137l.f20984m, obj);
        z zVar = this.f20980a;
        Object obj2 = zVar.get(c2137l);
        if (obj2 == null) {
            arrayList = new ArrayList();
            zVar.put(c2137l, arrayList);
        } else {
            arrayList = (List) obj2;
        }
        arrayList.add(obj);
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final C2133h clone() {
        z zVar;
        C2133h c2133h = new C2133h();
        int i7 = 0;
        while (true) {
            zVar = this.f20980a;
            if (i7 >= zVar.f21003m.size()) {
                break;
            }
            Map.Entry entry = (Map.Entry) zVar.f21003m.get(i7);
            c2133h.i((C2137l) entry.getKey(), entry.getValue());
            i7++;
        }
        for (Map.Entry entry2 : zVar.d()) {
            c2133h.i((C2137l) entry2.getKey(), entry2.getValue());
        }
        return c2133h;
    }

    public final void f() {
        if (this.f20981b) {
            return;
        }
        z zVar = this.f20980a;
        if (!zVar.o) {
            for (int i7 = 0; i7 < zVar.f21003m.size(); i7++) {
                Map.Entry entry = (Map.Entry) zVar.f21003m.get(i7);
                if (((C2137l) entry.getKey()).f20985n) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
            for (Map.Entry entry2 : zVar.d()) {
                if (((C2137l) entry2.getKey()).f20985n) {
                    entry2.setValue(Collections.unmodifiableList((List) entry2.getValue()));
                }
            }
        }
        if (!zVar.o) {
            zVar.f21004n = zVar.f21004n.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(zVar.f21004n);
            zVar.o = true;
        }
        this.f20981b = true;
    }

    public final void g(Map.Entry entry) {
        C2137l c2137l = (C2137l) entry.getKey();
        Object value = entry.getValue();
        boolean z6 = c2137l.f20985n;
        z zVar = this.f20980a;
        if (z6) {
            Object arrayList = zVar.get(c2137l);
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            for (Object obj : (List) value) {
                List list = (List) arrayList;
                if (obj instanceof byte[]) {
                    byte[] bArr = (byte[]) obj;
                    byte[] bArr2 = new byte[bArr.length];
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    obj = bArr2;
                }
                list.add(obj);
            }
            zVar.put(c2137l, arrayList);
            return;
        }
        if (c2137l.f20984m.l != EnumC2125M.f20963u) {
            if (value instanceof byte[]) {
                byte[] bArr3 = (byte[]) value;
                byte[] bArr4 = new byte[bArr3.length];
                System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
                value = bArr4;
            }
            zVar.put(c2137l, value);
            return;
        }
        Object obj2 = zVar.get(c2137l);
        if (obj2 != null) {
            zVar.put(c2137l, ((AbstractC2126a) obj2).e().e((AbstractC2139n) ((AbstractC2126a) value)).c());
            return;
        }
        if (value instanceof byte[]) {
            byte[] bArr5 = (byte[]) value;
            byte[] bArr6 = new byte[bArr5.length];
            System.arraycopy(bArr5, 0, bArr6, 0, bArr5.length);
            value = bArr6;
        }
        zVar.put(c2137l, value);
    }

    public final void i(C2137l c2137l, Object obj) {
        boolean z6 = c2137l.f20985n;
        EnumC2124L enumC2124L = c2137l.f20984m;
        if (!z6) {
            j(enumC2124L, obj);
        } else {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                j(enumC2124L, it.next());
            }
            obj = arrayList;
        }
        this.f20980a.put(c2137l, obj);
    }

    public C2133h(int i7) {
        f();
    }
}
