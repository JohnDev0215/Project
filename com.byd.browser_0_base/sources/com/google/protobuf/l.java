package com.google.protobuf;

import com.google.protobuf.c0;
import com.google.protobuf.f1;
import com.google.protobuf.m;
import com.google.protobuf.n;
import defpackage.C0173a;
import defpackage.C0175b;
import defpackage.ah1;
import defpackage.gq2;
import defpackage.hm0;
import defpackage.lk;
import defpackage.ps1;
import defpackage.zg1;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: DescriptorMessageInfoFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class l implements ah1 {
    public static final HashSet a;
    public static final a b;

    /* JADX INFO: compiled from: DescriptorMessageInfoFactory.java */
    public static class a {
        public final ConcurrentHashMap a = new ConcurrentHashMap();
        public int b = 0;
        public final Stack<C0045a> c = new Stack<>();
        public final HashMap d = new HashMap();

        /* JADX INFO: renamed from: com.google.protobuf.l$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DescriptorMessageInfoFactory.java */
        public static class C0045a {
            public final n.a a;
            public final int b;
            public int c;
            public b d = null;

            public C0045a(n.a aVar, int i) {
                this.a = aVar;
                this.b = i;
                this.c = i;
            }
        }

        /* JADX INFO: compiled from: DescriptorMessageInfoFactory.java */
        public static class b {
            public final ArrayList a = new ArrayList();
            public boolean b = false;
        }

        public final C0045a a(n.a aVar) {
            C0045a c0045aPop;
            ArrayList arrayList;
            boolean z;
            b bVar;
            int i = this.b;
            this.b = i + 1;
            C0045a c0045a = new C0045a(aVar, i);
            Stack<C0045a> stack = this.c;
            stack.push(c0045a);
            HashMap map = this.d;
            map.put(aVar, c0045a);
            for (n.f fVar : aVar.k()) {
                if (fVar.k.e == n.f.b.n) {
                    C0045a c0045a2 = (C0045a) map.get(fVar.k());
                    if (c0045a2 == null) {
                        c0045a.c = Math.min(c0045a.c, a(fVar.k()).c);
                    } else if (c0045a2.d == null) {
                        c0045a.c = Math.min(c0045a.c, c0045a2.c);
                    }
                }
            }
            if (c0045a.b == c0045a.c) {
                b bVar2 = new b();
                do {
                    c0045aPop = stack.pop();
                    c0045aPop.d = bVar2;
                    arrayList = bVar2.a;
                    arrayList.add(c0045aPop.a);
                } while (c0045aPop != c0045a);
                Iterator it = arrayList.iterator();
                loop2: while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    n.a aVar2 = (n.a) it.next();
                    z = true;
                    if (!aVar2.e.m.isEmpty()) {
                        break;
                    }
                    for (n.f fVar2 : aVar2.k()) {
                        if (fVar2.p() || (fVar2.k.e == n.f.b.n && (bVar = ((C0045a) map.get(fVar2.k())).d) != bVar2 && bVar.b)) {
                            break loop2;
                        }
                    }
                }
                bVar2.b = z;
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    this.a.put((n.a) it2.next(), Boolean.valueOf(bVar2.b));
                }
            }
            return c0045a;
        }
    }

    /* JADX INFO: compiled from: DescriptorMessageInfoFactory.java */
    public static final class b {
        public ps1[] a = new ps1[2];
    }

    static {
        new l();
        a = new HashSet(Arrays.asList("cached_size", "serialized_size", "class"));
        b = new a();
    }

    public static w c(Class cls, n.f fVar, b bVar, boolean z, j jVar) {
        ps1 ps1Var;
        Class<?> returnType;
        n.j jVar2 = fVar.n;
        int i = jVar2.e;
        ps1[] ps1VarArr = bVar.a;
        if (i >= ps1VarArr.length) {
            bVar.a = (ps1[]) Arrays.copyOf(ps1VarArr, i * 2);
        }
        ps1 ps1Var2 = bVar.a[i];
        if (ps1Var2 == null) {
            String strK = k(jVar2.f());
            ps1 ps1Var3 = new ps1(f(cls, gq2.f(strK, "Case_")), f(cls, gq2.f(strK, "_")));
            bVar.a[i] = ps1Var3;
            ps1Var = ps1Var3;
        } else {
            ps1Var = ps1Var2;
        }
        hm0 hm0VarH = h(fVar);
        switch (hm0VarH.e.ordinal()) {
            case 1:
            case 8:
                returnType = Integer.class;
                break;
            case 2:
                returnType = Long.class;
                break;
            case 3:
                returnType = Float.class;
                break;
            case 4:
                returnType = Double.class;
                break;
            case 5:
                returnType = Boolean.class;
                break;
            case 6:
                returnType = String.class;
                break;
            case 7:
                returnType = lk.class;
                break;
            case 9:
                try {
                    returnType = cls.getDeclaredMethod(j(fVar.k == n.f.c.g ? fVar.k().f() : fVar.f()), new Class[0]).getReturnType();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid type for oneof: " + hm0VarH);
        }
        Class<?> cls2 = returnType;
        int i2 = fVar.f.i;
        w.a(i2);
        Charset charset = c0.a;
        if (cls2 == null) {
            throw new NullPointerException("oneofStoredType");
        }
        if (hm0VarH.g == 1) {
            return new w(null, i2, hm0VarH, null, null, 0, false, z, ps1Var, cls2, null, jVar, null);
        }
        throw new IllegalArgumentException("Oneof is only supported for scalar fields. Field " + i2 + " is of type " + hm0VarH);
    }

    public static Field d(Class<?> cls, n.f fVar) {
        return f(cls, k(fVar.f()) + "MemoizedSerializedSize");
    }

    public static Field e(Class<?> cls, n.f fVar) {
        String strF = fVar.k == n.f.c.g ? fVar.k().f() : fVar.f();
        return f(cls, k(strF) + (a.contains(strF) ? "__" : "_"));
    }

    public static Field f(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Exception unused) {
            StringBuilder sbN = C0175b.n("Unable to find field ", str, " in message class ");
            sbN.append(cls.getName());
            throw new IllegalArgumentException(sbN.toString());
        }
    }

    public static q0 g(Class<?> cls) {
        try {
            return (q0) cls.getDeclaredMethod("getDefaultInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to get default instance for message class ".concat(cls.getName()), e);
        }
    }

    public static hm0 h(n.f fVar) {
        switch (fVar.k.ordinal()) {
            case 0:
                return !fVar.c() ? hm0.h : fVar.G() ? hm0.Q : hm0.z;
            case 1:
                return !fVar.c() ? hm0.i : fVar.G() ? hm0.R : hm0.A;
            case 2:
                return !fVar.c() ? hm0.j : fVar.G() ? hm0.S : hm0.B;
            case 3:
                return !fVar.c() ? hm0.k : fVar.G() ? hm0.T : hm0.C;
            case 4:
                return !fVar.c() ? hm0.l : fVar.G() ? hm0.U : hm0.D;
            case 5:
                return !fVar.c() ? hm0.m : fVar.G() ? hm0.V : hm0.E;
            case 6:
                return !fVar.c() ? hm0.n : fVar.G() ? hm0.W : hm0.F;
            case 7:
                return !fVar.c() ? hm0.o : fVar.G() ? hm0.X : hm0.G;
            case 8:
                return fVar.c() ? hm0.H : hm0.p;
            case 9:
                return fVar.c() ? hm0.e0 : hm0.y;
            case 10:
                return fVar.m() ? hm0.f0 : fVar.c() ? hm0.I : hm0.q;
            case 11:
                return fVar.c() ? hm0.f41J : hm0.r;
            case 12:
                return !fVar.c() ? hm0.s : fVar.G() ? hm0.Y : hm0.K;
            case 13:
                return !fVar.c() ? hm0.t : fVar.G() ? hm0.Z : hm0.L;
            case 14:
                return !fVar.c() ? hm0.u : fVar.G() ? hm0.a0 : hm0.M;
            case 15:
                return !fVar.c() ? hm0.v : fVar.G() ? hm0.b0 : hm0.N;
            case 16:
                return !fVar.c() ? hm0.w : fVar.G() ? hm0.c0 : hm0.O;
            case 17:
                return !fVar.c() ? hm0.x : fVar.G() ? hm0.d0 : hm0.P;
            default:
                StringBuilder sb = new StringBuilder("Unsupported field type: ");
                sb.append(fVar.k);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public static Class<?> i(Class<?> cls, n.f fVar) {
        try {
            return cls.getDeclaredMethod(j(fVar.k == n.f.c.g ? fVar.k().f() : fVar.f()), Integer.TYPE).getReturnType();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String j(String str) {
        String strK = k(str);
        return "get" + Character.toUpperCase(strK.charAt(0)) + strK.substring(1, strK.length());
    }

    public static String k(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 1);
        boolean z = false;
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '_') {
                z = true;
            } else if (Character.isDigit(cCharAt)) {
                sb.append(cCharAt);
                z = true;
            } else if (z) {
                sb.append(Character.toUpperCase(cCharAt));
                z = false;
            } else if (i == 0) {
                sb.append(Character.toLowerCase(cCharAt));
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    @Override // defpackage.ah1
    public final zg1 a(Class<?> cls) {
        boolean zBooleanValue;
        int i;
        int i2;
        if (!a0.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
        }
        n.a aVarC = g(cls).c();
        int iM = gq2.m(aVarC.g.j());
        int i3 = 2;
        j jVar = null;
        if (iM != 1) {
            if (iM != 2) {
                throw new IllegalArgumentException("Unsupported syntax: ".concat(C0173a.r(aVarC.g.j())));
            }
            List<n.f> listK = aVarC.k();
            f1.a aVar = new f1.a(listK.size());
            aVar.f = g(cls);
            Charset charset = c0.a;
            aVar.b = 2;
            b bVar = new b();
            for (int i4 = 0; i4 < listK.size(); i4++) {
                n.f fVar = listK.get(i4);
                if (fVar.n != null) {
                    aVar.b(c(cls, fVar, bVar, true, null));
                } else {
                    boolean zM = fVar.m();
                    m.g gVar = fVar.f;
                    if (zM) {
                        aVar.b(w.f(e(cls, fVar), gVar.i, c1.A(cls, fVar.f()), null));
                    } else if (fVar.c() && fVar.k.e == n.f.b.n) {
                        aVar.b(w.h(e(cls, fVar), gVar.i, h(fVar), i(cls, fVar)));
                    } else if (fVar.G()) {
                        aVar.b(w.g(e(cls, fVar), gVar.i, h(fVar), d(cls, fVar)));
                    } else {
                        aVar.b(w.e(e(cls, fVar), gVar.i, h(fVar), true));
                    }
                }
            }
            return aVar.a();
        }
        List<n.f> listK2 = aVarC.k();
        f1.a aVar2 = new f1.a(listK2.size());
        aVar2.f = g(cls);
        Charset charset2 = c0.a;
        aVar2.b = 1;
        aVar2.d = aVarC.n().i;
        b bVar2 = new b();
        int i5 = 0;
        int i6 = 0;
        int i7 = 1;
        Field fieldF = null;
        while (i5 < listK2.size()) {
            n.f fVar2 = listK2.get(i5);
            boolean z = fVar2.h.e.S().m;
            n.f.b bVar3 = fVar2.k.e;
            n.f.b bVar4 = n.f.b.m;
            j jVar2 = jVar;
            if (bVar3 == bVar4) {
                jVar2 = new j(fVar2);
            }
            if (fVar2.n != null) {
                aVar2.b(c(cls, fVar2, bVar2, z, jVar2));
                i = i6;
                i2 = i5;
            } else {
                Field fieldE = e(cls, fVar2);
                int i8 = fVar2.f.i;
                hm0 hm0VarH = h(fVar2);
                if (fVar2.m()) {
                    n.f fVarJ = fVar2.k().j(i3);
                    c0.b kVar = jVar2;
                    if (fVarJ.k.e == bVar4) {
                        kVar = new k(fVarJ);
                    }
                    aVar2.b(w.f(fieldE, i8, c1.A(cls, fVar2.f()), kVar));
                } else if (!fVar2.c()) {
                    if (fieldF == null) {
                        fieldF = f(cls, "bitField" + i6 + "_");
                    }
                    if (fVar2.p()) {
                        w.a(i8);
                        Charset charset3 = c0.a;
                        if (fieldE == null) {
                            throw new NullPointerException("field");
                        }
                        if (hm0VarH == null) {
                            throw new NullPointerException("fieldType");
                        }
                        if (fieldF == null) {
                            throw new NullPointerException("presenceField");
                        }
                        if (!(i7 != 0 && ((i7 + (-1)) & i7) == 0)) {
                            throw new IllegalArgumentException(gq2.e("presenceMask must have exactly one bit set: ", i7));
                        }
                        i = i6;
                        i2 = i5;
                        aVar2.b(new w(fieldE, i8, hm0VarH, null, fieldF, i7, true, z, null, null, null, jVar2, null));
                    } else {
                        i = i6;
                        i2 = i5;
                        w.a(i8);
                        Charset charset4 = c0.a;
                        if (fieldE == null) {
                            throw new NullPointerException("field");
                        }
                        if (hm0VarH == null) {
                            throw new NullPointerException("fieldType");
                        }
                        if (fieldF == null) {
                            throw new NullPointerException("presenceField");
                        }
                        if (!(i7 != 0 && ((i7 + (-1)) & i7) == 0)) {
                            throw new IllegalArgumentException(gq2.e("presenceMask must have exactly one bit set: ", i7));
                        }
                        aVar2.b(new w(fieldE, i8, hm0VarH, null, fieldF, i7, false, z, null, null, null, jVar2, null));
                    }
                } else if (jVar2 != null) {
                    if (fVar2.G()) {
                        Field fieldD = d(cls, fVar2);
                        w.a(i8);
                        Charset charset5 = c0.a;
                        if (fieldE == null) {
                            throw new NullPointerException("field");
                        }
                        aVar2.b(new w(fieldE, i8, hm0VarH, null, null, 0, false, false, null, null, null, jVar2, fieldD));
                    } else {
                        w.a(i8);
                        Charset charset6 = c0.a;
                        if (fieldE == null) {
                            throw new NullPointerException("field");
                        }
                        aVar2.b(new w(fieldE, i8, hm0VarH, null, null, 0, false, false, null, null, null, jVar2, null));
                    }
                } else if (fVar2.k.e == n.f.b.n) {
                    aVar2.b(w.h(fieldE, i8, hm0VarH, i(cls, fVar2)));
                } else if (fVar2.G()) {
                    aVar2.b(w.g(fieldE, i8, hm0VarH, d(cls, fVar2)));
                } else {
                    aVar2.b(w.e(fieldE, i8, hm0VarH, z));
                }
                i2 = i5;
                i5 = i2 + 1;
                jVar = null;
                i3 = 2;
            }
            int i9 = i7 << 1;
            if (i9 == 0) {
                i6 = i + 1;
                fieldF = null;
                i7 = 1;
            } else {
                i7 = i9;
                i6 = i;
            }
            i5 = i2 + 1;
            jVar = null;
            i3 = 2;
        }
        ArrayList arrayList = new ArrayList();
        for (int i10 = 0; i10 < listK2.size(); i10++) {
            n.f fVar3 = listK2.get(i10);
            if (fVar3.p()) {
                arrayList.add(Integer.valueOf(fVar3.f.i));
            } else if (fVar3.k.e == n.f.b.n) {
                n.a aVarK = fVar3.k();
                a aVar3 = b;
                Boolean bool = (Boolean) aVar3.a.get(aVarK);
                if (bool != null) {
                    zBooleanValue = bool.booleanValue();
                } else {
                    synchronized (aVar3) {
                        Boolean bool2 = (Boolean) aVar3.a.get(aVarK);
                        zBooleanValue = bool2 != null ? bool2.booleanValue() : aVar3.a(aVarK).d.b;
                    }
                }
                if (zBooleanValue) {
                    arrayList.add(Integer.valueOf(fVar3.f.i));
                }
            } else {
                continue;
            }
        }
        int[] iArr = new int[arrayList.size()];
        for (int i11 = 0; i11 < arrayList.size(); i11++) {
            iArr[i11] = ((Integer) arrayList.get(i11)).intValue();
        }
        aVar2.e = iArr;
        return aVar2.a();
    }

    @Override // defpackage.ah1
    public final boolean b(Class<?> cls) {
        return a0.class.isAssignableFrom(cls);
    }
}
