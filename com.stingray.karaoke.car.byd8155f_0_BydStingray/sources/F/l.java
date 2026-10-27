package F;

import android.util.Log;
import h4.C1360b;
import h4.C1362d;
import h4.C1363e;
import h4.InterfaceC1365g;
import io.sentry.C1489n1;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.NavigableMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1902a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1903b;
    public final Object c;
    public final Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f1904e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f1905f;

    public l(r rVar, int i7, int i10, k kVar, u uVar) {
        this.d = rVar;
        this.c = rVar;
        this.f1902a = i7;
        this.f1903b = i10;
        this.f1904e = kVar;
        this.f1905f = uVar;
    }

    public long a(int i7, int i10) {
        int i11;
        r rVar = (r) this.c;
        int[] iArr = rVar.f1952a;
        if (i10 == 1) {
            i11 = iArr[i7];
        } else {
            int i12 = (i10 + i7) - 1;
            int[] iArr2 = rVar.f1953b;
            i11 = (iArr2[i12] + iArr[i12]) - iArr2[i7];
        }
        if (i11 < 0) {
            i11 = 0;
        }
        if (i11 >= 0) {
            return io.sentry.config.a.U(i11, i11, 0, Integer.MAX_VALUE);
        }
        w0.c.e0("width(" + i11 + ") must be >= 0");
        throw null;
    }

    public void b(int i7, Class cls) {
        NavigableMap navigableMapH = h(cls);
        Integer num = (Integer) navigableMapH.get(Integer.valueOf(i7));
        if (num != null) {
            if (num.intValue() == 1) {
                navigableMapH.remove(Integer.valueOf(i7));
                return;
            } else {
                navigableMapH.put(Integer.valueOf(i7), Integer.valueOf(num.intValue() - 1));
                return;
            }
        }
        throw new NullPointerException("Tried to decrement empty size, size: " + i7 + ", this: " + this);
    }

    public void c(int i7) {
        while (this.f1903b > i7) {
            Object objF = ((C1489n1) this.c).F();
            A4.h.b(objF);
            C1360b c1360bE = e(objF.getClass());
            this.f1903b -= c1360bE.b() * c1360bE.a(objF);
            b(c1360bE.a(objF), objF.getClass());
            if (Log.isLoggable(c1360bE.c(), 2)) {
                Log.v(c1360bE.c(), "evicted: " + c1360bE.a(objF));
            }
        }
    }

    public synchronized Object d(int i7, Class cls) {
        C1362d c1362d;
        int i10;
        try {
            Integer num = (Integer) h(cls).ceilingKey(Integer.valueOf(i7));
            if (num == null || ((i10 = this.f1903b) != 0 && this.f1902a / i10 < 2 && num.intValue() > i7 * 8)) {
                C1363e c1363e = (C1363e) this.d;
                InterfaceC1365g interfaceC1365gA1 = (InterfaceC1365g) ((ArrayDeque) c1363e.f476m).poll();
                if (interfaceC1365gA1 == null) {
                    interfaceC1365gA1 = c1363e.a1();
                }
                c1362d = (C1362d) interfaceC1365gA1;
                c1362d.f15514b = i7;
                c1362d.c = cls;
            } else {
                C1363e c1363e2 = (C1363e) this.d;
                int iIntValue = num.intValue();
                InterfaceC1365g interfaceC1365gA12 = (InterfaceC1365g) ((ArrayDeque) c1363e2.f476m).poll();
                if (interfaceC1365gA12 == null) {
                    interfaceC1365gA12 = c1363e2.a1();
                }
                c1362d = (C1362d) interfaceC1365gA12;
                c1362d.f15514b = iIntValue;
                c1362d.c = cls;
            }
        } catch (Throwable th) {
            throw th;
        }
        return g(c1362d, cls);
    }

    public C1360b e(Class cls) {
        HashMap map = (HashMap) this.f1905f;
        C1360b c1360b = (C1360b) map.get(cls);
        if (c1360b == null) {
            if (cls.equals(int[].class)) {
                c1360b = new C1360b(1);
            } else {
                if (!cls.equals(byte[].class)) {
                    throw new IllegalArgumentException("No array pool found for: ".concat(cls.getSimpleName()));
                }
                c1360b = new C1360b(0);
            }
            map.put(cls, c1360b);
        }
        return c1360b;
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.Object, java.util.List] */
    public q f(int i7) {
        C1.j jVarC = ((u) this.f1905f).c(i7);
        ?? r62 = jVarC.f944m;
        int size = r62.size();
        int i10 = jVarC.l;
        int i11 = (size == 0 || i10 + size == this.f1902a) ? 0 : this.f1903b;
        p[] pVarArr = new p[size];
        int i12 = 0;
        for (int i13 = 0; i13 < size; i13++) {
            int i14 = (int) ((b) r62.get(i13)).f1864a;
            p pVarB = ((k) this.f1904e).b(i10 + i13, a(i12, i14), i12, i14, i11);
            i12 += i14;
            pVarArr[i13] = pVarB;
        }
        return new q(i7, pVarArr, (r) this.d, r62, i11);
    }

    public Object g(C1362d c1362d, Class cls) {
        Object obj;
        C1360b c1360bE = e(cls);
        Object objP = ((C1489n1) this.c).p(c1362d);
        if (objP != null) {
            this.f1903b -= c1360bE.b() * c1360bE.a(objP);
            b(c1360bE.a(objP), cls);
        }
        if (objP != null) {
            return objP;
        }
        if (Log.isLoggable(c1360bE.c(), 2)) {
            Log.v(c1360bE.c(), "Allocated " + c1362d.f15514b + " bytes");
        }
        int i7 = c1362d.f15514b;
        switch (c1360bE.f15510a) {
            case 0:
                obj = new byte[i7];
                break;
            default:
                obj = new int[i7];
                break;
        }
        return obj;
    }

    public NavigableMap h(Class cls) {
        HashMap map = (HashMap) this.f1904e;
        NavigableMap navigableMap = (NavigableMap) map.get(cls);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        map.put(cls, treeMap);
        return treeMap;
    }

    public synchronized void i(Object obj) {
        Class<?> cls = obj.getClass();
        C1360b c1360bE = e(cls);
        int iA = c1360bE.a(obj);
        int iB = c1360bE.b() * iA;
        if (iB <= this.f1902a / 2) {
            C1363e c1363e = (C1363e) this.d;
            InterfaceC1365g interfaceC1365gA1 = (InterfaceC1365g) ((ArrayDeque) c1363e.f476m).poll();
            if (interfaceC1365gA1 == null) {
                interfaceC1365gA1 = c1363e.a1();
            }
            C1362d c1362d = (C1362d) interfaceC1365gA1;
            c1362d.f15514b = iA;
            c1362d.c = cls;
            ((C1489n1) this.c).D(c1362d, obj);
            NavigableMap navigableMapH = h(cls);
            Integer num = (Integer) navigableMapH.get(Integer.valueOf(c1362d.f15514b));
            Integer numValueOf = Integer.valueOf(c1362d.f15514b);
            int iIntValue = 1;
            if (num != null) {
                iIntValue = 1 + num.intValue();
            }
            navigableMapH.put(numValueOf, Integer.valueOf(iIntValue));
            this.f1903b += iB;
            c(this.f1902a);
        }
    }

    public l(int i7) {
        this.c = new C1489n1(20);
        this.d = new C1363e(0);
        this.f1904e = new HashMap();
        this.f1905f = new HashMap();
        this.f1902a = i7;
    }
}
