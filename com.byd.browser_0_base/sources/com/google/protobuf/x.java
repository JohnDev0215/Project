package com.google.protobuf;

import com.google.protobuf.c0;
import com.google.protobuf.e0;
import com.google.protobuf.n1;
import com.google.protobuf.r0;
import com.google.protobuf.x.b;
import defpackage.lk;
import defpackage.nu;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: FieldSet.java */
/* JADX INFO: loaded from: classes.dex */
public final class x<T extends b<T>> {
    public static final x d;
    public final e1<T, Object> a;
    public boolean b;
    public boolean c;

    /* JADX INFO: compiled from: FieldSet.java */
    public static final class a<T extends b<T>> {
        public e1<T, Object> a;
        public boolean b;
        public boolean c;
        public boolean d;

        public a(int i) {
            int i2 = e1.k;
            this.a = new d1(16);
            this.c = true;
        }

        public static <T extends b<T>> Object c(T t, Object obj) {
            if (obj == null || t.w() != n1.b.n) {
                return obj;
            }
            if (!t.c()) {
                return obj instanceof r0.a ? ((r0.a) obj).build() : obj;
            }
            if (!(obj instanceof List)) {
                throw new IllegalStateException("Repeated field should contains a List but actually contains type: " + obj.getClass());
            }
            List arrayList = (List) obj;
            for (int i = 0; i < arrayList.size(); i++) {
                Object obj2 = arrayList.get(i);
                Object objBuild = obj2 instanceof r0.a ? ((r0.a) obj2).build() : obj2;
                if (objBuild != obj2) {
                    if (arrayList == obj) {
                        arrayList = new ArrayList(arrayList);
                    }
                    arrayList.set(i, objBuild);
                }
            }
            return arrayList;
        }

        public static void d(d1 d1Var) {
            for (int i = 0; i < d1Var.d(); i++) {
                Map.Entry<Object, Object> entryC = d1Var.c(i);
                entryC.setValue(c((b) entryC.getKey(), entryC.getValue()));
            }
            for (Map.Entry<Object, Object> entry : d1Var.e()) {
                entry.setValue(c((b) entry.getKey(), entry.getValue()));
            }
        }

        public static void e(b bVar, Object obj) {
            if (x.r(bVar.d(), obj)) {
                return;
            }
            if (bVar.d().e != n1.b.n || !(obj instanceof r0.a)) {
                throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(bVar.b()), bVar.d().e, obj.getClass().getName()));
            }
        }

        public final Object a(T t) {
            Object objC = this.a.get(t);
            if (objC instanceof e0) {
                objC = ((e0) objC).c();
            }
            return c(t, objC);
        }

        public final void b(Map.Entry<T, Object> entry) {
            T key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof e0) {
                value = ((e0) value).c();
            }
            if (key.c()) {
                Object objA = a(key);
                if (objA == null) {
                    objA = new ArrayList();
                }
                Iterator it = ((List) value).iterator();
                while (it.hasNext()) {
                    ((List) objA).add(x.e(it.next()));
                }
                this.a.put(key, objA);
                return;
            }
            if (key.w() != n1.b.n) {
                this.a.put(key, x.e(value));
                return;
            }
            Object objA2 = a(key);
            if (objA2 == null) {
                this.a.put(key, x.e(value));
            } else if (objA2 instanceof r0.a) {
                key.t((r0.a) objA2, (r0) value);
            } else {
                this.a.put(key, key.t(((r0) objA2).h(), (r0) value).build());
            }
        }
    }

    /* JADX INFO: compiled from: FieldSet.java */
    public interface b<T extends b<T>> extends Comparable<T> {
        boolean G();

        int b();

        boolean c();

        n1.a d();

        r0.a t(r0.a aVar, r0 r0Var);

        n1.b w();
    }

    static {
        int i = e1.k;
        x xVar = new x(new d1(0));
        xVar.t();
        d = xVar;
    }

    public x() {
        int i = e1.k;
        this.a = new d1(16);
    }

    public static void A(b<?> bVar, Object obj, nu nuVar) {
        n1.a aVarD = bVar.d();
        int iB = bVar.b();
        if (!bVar.c()) {
            if (obj instanceof e0) {
                y(nuVar, aVarD, iB, ((e0) obj).c());
                return;
            } else {
                y(nuVar, aVarD, iB, obj);
                return;
            }
        }
        List list = (List) obj;
        if (!bVar.G()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                y(nuVar, aVarD, iB, it.next());
            }
            return;
        }
        nuVar.M0(iB, 2);
        Iterator it2 = list.iterator();
        int iG = 0;
        while (it2.hasNext()) {
            iG += g(aVarD, it2.next());
        }
        nuVar.O0(iG);
        Iterator it3 = list.iterator();
        while (it3.hasNext()) {
            z(nuVar, aVarD, it3.next());
        }
    }

    public static void B(Map.Entry entry, nu nuVar) {
        b bVar = (b) entry.getKey();
        if (bVar.w() != n1.b.n || bVar.c() || bVar.G()) {
            A(bVar, entry.getValue(), nuVar);
            return;
        }
        Object value = entry.getValue();
        if (value instanceof e0) {
            value = ((e0) value).c();
        }
        nuVar.I0(((b) entry.getKey()).b(), (r0) value);
    }

    public static d1 c(e1 e1Var, boolean z) {
        int i = e1.k;
        d1 d1Var = new d1(16);
        for (int i2 = 0; i2 < e1Var.d(); i2++) {
            d(d1Var, e1Var.c(i2), z);
        }
        Iterator it = e1Var.e().iterator();
        while (it.hasNext()) {
            d(d1Var, (Map.Entry) it.next(), z);
        }
        return d1Var;
    }

    public static void d(d1 d1Var, Map.Entry entry, boolean z) {
        b bVar = (b) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof e0) {
            d1Var.put(bVar, ((e0) value).c());
        } else if (z && (value instanceof List)) {
            d1Var.put(bVar, new ArrayList((List) value));
        } else {
            d1Var.put(bVar, value);
        }
    }

    public static Object e(Object obj) {
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public static int f(n1.a aVar, int i, Object obj) {
        int iI0 = nu.i0(i);
        if (aVar == n1.a.g) {
            iI0 *= 2;
        }
        return g(aVar, obj) + iI0;
    }

    public static int g(n1.a aVar, Object obj) {
        switch (aVar.ordinal()) {
            case 0:
                ((Double) obj).doubleValue();
                boolean z = nu.l;
                return 8;
            case 1:
                ((Float) obj).floatValue();
                boolean z2 = nu.l;
                return 4;
            case 2:
                return nu.n0(((Long) obj).longValue());
            case 3:
                return nu.n0(((Long) obj).longValue());
            case 4:
                return nu.T(((Integer) obj).intValue());
            case 5:
                ((Long) obj).longValue();
                boolean z3 = nu.l;
                return 8;
            case 6:
                ((Integer) obj).intValue();
                boolean z4 = nu.l;
                return 4;
            case 7:
                ((Boolean) obj).booleanValue();
                boolean z5 = nu.l;
                return 1;
            case 8:
                return obj instanceof lk ? nu.L((lk) obj) : nu.g0((String) obj);
            case 9:
                boolean z6 = nu.l;
                return ((r0) obj).m();
            case 10:
                if (obj instanceof e0) {
                    return nu.W((e0) obj);
                }
                boolean z7 = nu.l;
                int iM = ((r0) obj).m();
                return nu.k0(iM) + iM;
            case 11:
                if (obj instanceof lk) {
                    return nu.L((lk) obj);
                }
                boolean z8 = nu.l;
                int length = ((byte[]) obj).length;
                return nu.k0(length) + length;
            case 12:
                return nu.k0(((Integer) obj).intValue());
            case 13:
                return obj instanceof c0.a ? nu.T(((c0.a) obj).b()) : nu.T(((Integer) obj).intValue());
            case 14:
                ((Integer) obj).intValue();
                boolean z9 = nu.l;
                return 4;
            case 15:
                ((Long) obj).longValue();
                boolean z10 = nu.l;
                return 8;
            case 16:
                int iIntValue = ((Integer) obj).intValue();
                return nu.k0((iIntValue >> 31) ^ (iIntValue << 1));
            case 17:
                long jLongValue = ((Long) obj).longValue();
                return nu.n0((jLongValue >> 63) ^ (jLongValue << 1));
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int h(b<?> bVar, Object obj) {
        n1.a aVarD = bVar.d();
        int iB = bVar.b();
        if (!bVar.c()) {
            return f(aVarD, iB, obj);
        }
        int iF = 0;
        if (!bVar.G()) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                iF += f(aVarD, iB, it.next());
            }
            return iF;
        }
        Iterator it2 = ((List) obj).iterator();
        while (it2.hasNext()) {
            iF += g(aVarD, it2.next());
        }
        return nu.k0(iF) + nu.i0(iB) + iF;
    }

    public static int l(Map.Entry entry) {
        b bVar = (b) entry.getKey();
        Object value = entry.getValue();
        if (bVar.w() != n1.b.n || bVar.c() || bVar.G()) {
            return h(bVar, value);
        }
        if (!(value instanceof e0)) {
            return nu.Z(((b) entry.getKey()).b(), (r0) value);
        }
        int iB = ((b) entry.getKey()).b();
        return nu.W((e0) value) + nu.i0(3) + nu.j0(2, iB) + (nu.i0(1) * 2);
    }

    public static <T extends b<T>> boolean q(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        if (key.w() == n1.b.n) {
            if (key.c()) {
                Iterator it = ((List) entry.getValue()).iterator();
                while (it.hasNext()) {
                    if (!((r0) it.next()).isInitialized()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (!(value instanceof r0)) {
                    if (value instanceof e0) {
                        return true;
                    }
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
                if (!((r0) value).isInitialized()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean r(n1.a aVar, Object obj) {
        Charset charset = c0.a;
        obj.getClass();
        switch (aVar.e) {
            case l:
                if (!(obj instanceof lk) && !(obj instanceof byte[])) {
                    break;
                }
                break;
            case m:
                if (!(obj instanceof Integer) && !(obj instanceof c0.a)) {
                    break;
                }
                break;
            case n:
                if (!(obj instanceof r0) && !(obj instanceof e0)) {
                    break;
                }
                break;
        }
        return false;
    }

    public static void x(b bVar, Object obj) {
        if (!r(bVar.d(), obj)) {
            throw new IllegalArgumentException(String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", Integer.valueOf(bVar.b()), bVar.d().e, obj.getClass().getName()));
        }
    }

    public static void y(nu nuVar, n1.a aVar, int i, Object obj) {
        if (aVar == n1.a.g) {
            nuVar.y0(i, (r0) obj);
        } else {
            nuVar.M0(i, aVar.f);
            z(nuVar, aVar, obj);
        }
    }

    public static void z(nu nuVar, n1.a aVar, Object obj) {
        switch (aVar.ordinal()) {
            case 0:
                double dDoubleValue = ((Double) obj).doubleValue();
                nuVar.getClass();
                nuVar.x0(Double.doubleToRawLongBits(dDoubleValue));
                break;
            case 1:
                float fFloatValue = ((Float) obj).floatValue();
                nuVar.getClass();
                nuVar.v0(Float.floatToRawIntBits(fFloatValue));
                break;
            case 2:
                nuVar.Q0(((Long) obj).longValue());
                break;
            case 3:
                nuVar.Q0(((Long) obj).longValue());
                break;
            case 4:
                nuVar.C0(((Integer) obj).intValue());
                break;
            case 5:
                nuVar.x0(((Long) obj).longValue());
                break;
            case 6:
                nuVar.v0(((Integer) obj).intValue());
                break;
            case 7:
                nuVar.o0(((Boolean) obj).booleanValue() ? (byte) 1 : (byte) 0);
                break;
            case 8:
                if (!(obj instanceof lk)) {
                    nuVar.L0((String) obj);
                } else {
                    nuVar.t0((lk) obj);
                }
                break;
            case 9:
                nuVar.getClass();
                ((r0) obj).p(nuVar);
                break;
            case 10:
                nuVar.G0((r0) obj);
                break;
            case 11:
                if (!(obj instanceof lk)) {
                    byte[] bArr = (byte[]) obj;
                    nuVar.getClass();
                    nuVar.q0(bArr, bArr.length);
                } else {
                    nuVar.t0((lk) obj);
                }
                break;
            case 12:
                nuVar.O0(((Integer) obj).intValue());
                break;
            case 13:
                if (!(obj instanceof c0.a)) {
                    nuVar.C0(((Integer) obj).intValue());
                } else {
                    nuVar.C0(((c0.a) obj).b());
                }
                break;
            case 14:
                nuVar.v0(((Integer) obj).intValue());
                break;
            case 15:
                nuVar.x0(((Long) obj).longValue());
                break;
            case 16:
                int iIntValue = ((Integer) obj).intValue();
                nuVar.O0((iIntValue >> 31) ^ (iIntValue << 1));
                break;
            case 17:
                long jLongValue = ((Long) obj).longValue();
                nuVar.Q0((jLongValue >> 63) ^ (jLongValue << 1));
                break;
        }
    }

    public final void a(T t, Object obj) {
        List arrayList;
        if (!t.c()) {
            throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
        }
        x(t, obj);
        Object objJ = j(t);
        if (objJ == null) {
            arrayList = new ArrayList();
            this.a.put(t, arrayList);
        } else {
            arrayList = (List) objJ;
        }
        arrayList.add(obj);
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final x<T> clone() {
        e1<T, Object> e1Var;
        x<T> xVar = new x<>();
        int i = 0;
        while (true) {
            e1Var = this.a;
            if (i >= e1Var.d()) {
                break;
            }
            Map.Entry<K, Object> entryC = e1Var.c(i);
            xVar.w((b) entryC.getKey(), entryC.getValue());
            i++;
        }
        Iterator it = e1Var.e().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            xVar.w((b) entry.getKey(), entry.getValue());
        }
        xVar.c = this.c;
        return xVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof x) {
            return this.a.equals(((x) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final Map<T, Object> i() {
        boolean z = this.c;
        e1<T, Object> e1Var = this.a;
        if (!z) {
            return e1Var.h ? e1Var : Collections.unmodifiableMap(e1Var);
        }
        d1 d1VarC = c(e1Var, false);
        if (e1Var.h) {
            d1VarC.g();
        }
        return d1VarC;
    }

    public final Object j(T t) {
        Object obj = this.a.get(t);
        return obj instanceof e0 ? ((e0) obj).c() : obj;
    }

    public final int k() {
        e1<T, Object> e1Var;
        int i = 0;
        int iL = 0;
        while (true) {
            e1Var = this.a;
            if (i >= e1Var.d()) {
                break;
            }
            iL += l(e1Var.c(i));
            i++;
        }
        Iterator it = e1Var.e().iterator();
        while (it.hasNext()) {
            iL += l((Map.Entry) it.next());
        }
        return iL;
    }

    public final int m() {
        e1<T, Object> e1Var;
        int i = 0;
        int iH = 0;
        while (true) {
            e1Var = this.a;
            if (i >= e1Var.d()) {
                break;
            }
            Map.Entry<K, Object> entryC = e1Var.c(i);
            iH += h((b) entryC.getKey(), entryC.getValue());
            i++;
        }
        Iterator it = e1Var.e().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            iH += h((b) entry.getKey(), entry.getValue());
        }
        return iH;
    }

    public final boolean n(T t) {
        if (t.c()) {
            throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
        }
        return this.a.get(t) != null;
    }

    public final boolean o() {
        return this.a.isEmpty();
    }

    public final boolean p() {
        int i = 0;
        while (true) {
            e1<T, Object> e1Var = this.a;
            if (i >= e1Var.d()) {
                Iterator it = e1Var.e().iterator();
                while (it.hasNext()) {
                    if (!q((Map.Entry) it.next())) {
                        return false;
                    }
                }
                return true;
            }
            if (!q(e1Var.c(i))) {
                return false;
            }
            i++;
        }
    }

    public final Iterator<Map.Entry<T, Object>> s() {
        boolean z = this.c;
        e1<T, Object> e1Var = this.a;
        return z ? new e0.b(e1Var.entrySet().iterator()) : e1Var.entrySet().iterator();
    }

    public final void t() {
        if (this.b) {
            return;
        }
        this.a.g();
        this.b = true;
    }

    public final void u(x<T> xVar) {
        e1<T, Object> e1Var;
        int i = 0;
        while (true) {
            int iD = xVar.a.d();
            e1Var = xVar.a;
            if (i >= iD) {
                break;
            }
            v(e1Var.c(i));
            i++;
        }
        Iterator it = e1Var.e().iterator();
        while (it.hasNext()) {
            v((Map.Entry) it.next());
        }
    }

    public final void v(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof e0) {
            value = ((e0) value).c();
        }
        boolean zC = key.c();
        e1<T, Object> e1Var = this.a;
        if (zC) {
            Object objJ = j(key);
            if (objJ == null) {
                objJ = new ArrayList();
            }
            Iterator it = ((List) value).iterator();
            while (it.hasNext()) {
                ((List) objJ).add(e(it.next()));
            }
            e1Var.put(key, objJ);
            return;
        }
        if (key.w() != n1.b.n) {
            e1Var.put(key, e(value));
            return;
        }
        Object objJ2 = j(key);
        if (objJ2 == null) {
            e1Var.put(key, e(value));
        } else {
            e1Var.put(key, key.t(((r0) objJ2).h(), (r0) value).build());
        }
    }

    public final void w(T t, Object obj) {
        if (!t.c()) {
            x(t, obj);
        } else {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                x(t, it.next());
            }
            obj = arrayList;
        }
        if (obj instanceof e0) {
            this.c = true;
        }
        this.a.put(t, obj);
    }

    public x(e1<T, Object> e1Var) {
        this.a = e1Var;
        t();
    }
}
