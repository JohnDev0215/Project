package androidx.lifecycle;

import V8.AbstractC0828q;
import h2.AbstractC1347d;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import s.C2324a;
import t.C2417a;
import t.C2419c;

/* JADX INFO: loaded from: classes.dex */
public final class C extends A9.b {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f11907n;
    public C2417a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public EnumC0981t f11908p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final WeakReference f11909q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f11910r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f11911s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f11912t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final ArrayList f11913u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final V8.f0 f11914v;

    public C(A a10) {
        super(4);
        this.f11907n = true;
        this.o = new C2417a();
        EnumC0981t enumC0981t = EnumC0981t.f12005m;
        this.f11908p = enumC0981t;
        this.f11913u = new ArrayList();
        this.f11909q = new WeakReference(a10);
        this.f11914v = AbstractC0828q.c(enumC0981t);
    }

    @Override // A9.b
    public final void M0(InterfaceC0987z interfaceC0987z) {
        InterfaceC0986y c0973k;
        A a10;
        ArrayList arrayList = this.f11913u;
        Object obj = null;
        int i7 = 1;
        A7.m.f(interfaceC0987z, "observer");
        b1("addObserver");
        EnumC0981t enumC0981t = this.f11908p;
        EnumC0981t enumC0981t2 = EnumC0981t.l;
        if (enumC0981t != enumC0981t2) {
            enumC0981t2 = EnumC0981t.f12005m;
        }
        B b10 = new B();
        HashMap map = D.f11915a;
        boolean z6 = interfaceC0987z instanceof InterfaceC0986y;
        boolean z10 = interfaceC0987z instanceof DefaultLifecycleObserver;
        if (z6 && z10) {
            c0973k = new C0973k((DefaultLifecycleObserver) interfaceC0987z, (InterfaceC0986y) interfaceC0987z);
        } else if (z10) {
            c0973k = new C0973k((DefaultLifecycleObserver) interfaceC0987z, (InterfaceC0986y) null);
        } else if (z6) {
            c0973k = (InterfaceC0986y) interfaceC0987z;
        } else {
            Class<?> cls = interfaceC0987z.getClass();
            if (D.b(cls) == 2) {
                Object obj2 = D.f11916b.get(cls);
                A7.m.c(obj2);
                List list = (List) obj2;
                if (list.size() == 1) {
                    D.a((Constructor) list.get(0), interfaceC0987z);
                    throw null;
                }
                int size = list.size();
                InterfaceC0976n[] interfaceC0976nArr = new InterfaceC0976n[size];
                if (size > 0) {
                    D.a((Constructor) list.get(0), interfaceC0987z);
                    throw null;
                }
                c0973k = new t3.b(interfaceC0976nArr, i7);
            } else {
                c0973k = new C0973k(interfaceC0987z);
            }
        }
        b10.f11906b = c0973k;
        b10.f11905a = enumC0981t2;
        C2417a c2417a = this.o;
        C2419c c2419cA = c2417a.a(interfaceC0987z);
        if (c2419cA != null) {
            obj = c2419cA.f22419m;
        } else {
            HashMap map2 = c2417a.f22416p;
            C2419c c2419c = new C2419c(interfaceC0987z, b10);
            c2417a.o++;
            C2419c c2419c2 = c2417a.f22423m;
            if (c2419c2 == null) {
                c2417a.l = c2419c;
                c2417a.f22423m = c2419c;
            } else {
                c2419c2.f22420n = c2419c;
                c2419c.o = c2419c2;
                c2417a.f22423m = c2419c;
            }
            map2.put(interfaceC0987z, c2419c);
        }
        if (((B) obj) == null && (a10 = (A) this.f11909q.get()) != null) {
            boolean z11 = this.f11910r != 0 || this.f11911s;
            EnumC0981t enumC0981tA1 = a1(interfaceC0987z);
            this.f11910r++;
            while (b10.f11905a.compareTo(enumC0981tA1) < 0 && this.o.f22416p.containsKey(interfaceC0987z)) {
                arrayList.add(b10.f11905a);
                C0979q c0979q = EnumC0980s.Companion;
                EnumC0981t enumC0981t3 = b10.f11905a;
                c0979q.getClass();
                EnumC0980s enumC0980sB = C0979q.b(enumC0981t3);
                if (enumC0980sB == null) {
                    throw new IllegalStateException("no event up from " + b10.f11905a);
                }
                b10.a(a10, enumC0980sB);
                arrayList.remove(arrayList.size() - 1);
                enumC0981tA1 = a1(interfaceC0987z);
            }
            if (!z11) {
                f1();
            }
            this.f11910r--;
        }
    }

    @Override // A9.b
    public final EnumC0981t O0() {
        return this.f11908p;
    }

    @Override // A9.b
    public final void V0(InterfaceC0987z interfaceC0987z) {
        A7.m.f(interfaceC0987z, "observer");
        b1("removeObserver");
        this.o.d(interfaceC0987z);
    }

    public final EnumC0981t a1(InterfaceC0987z interfaceC0987z) {
        B b10;
        HashMap map = this.o.f22416p;
        C2419c c2419c = map.containsKey(interfaceC0987z) ? ((C2419c) map.get(interfaceC0987z)).o : null;
        EnumC0981t enumC0981t = (c2419c == null || (b10 = (B) c2419c.f22419m) == null) ? null : b10.f11905a;
        ArrayList arrayList = this.f11913u;
        EnumC0981t enumC0981t2 = arrayList.isEmpty() ? null : (EnumC0981t) AbstractC1347d.e(1, arrayList);
        EnumC0981t enumC0981t3 = this.f11908p;
        A7.m.f(enumC0981t3, "state1");
        if (enumC0981t == null || enumC0981t.compareTo(enumC0981t3) >= 0) {
            enumC0981t = enumC0981t3;
        }
        return (enumC0981t2 == null || enumC0981t2.compareTo(enumC0981t) >= 0) ? enumC0981t : enumC0981t2;
    }

    public final void b1(String str) {
        if (this.f11907n && !C2324a.w().x()) {
            throw new IllegalStateException(android.hardware.bydauto.ac.tsimpl.a.i("Method ", str, " must be called on the main thread").toString());
        }
    }

    public final void c1(EnumC0980s enumC0980s) {
        A7.m.f(enumC0980s, "event");
        b1("handleLifecycleEvent");
        d1(enumC0980s.a());
    }

    public final void d1(EnumC0981t enumC0981t) {
        EnumC0981t enumC0981t2 = this.f11908p;
        if (enumC0981t2 == enumC0981t) {
            return;
        }
        EnumC0981t enumC0981t3 = EnumC0981t.f12005m;
        EnumC0981t enumC0981t4 = EnumC0981t.l;
        if (enumC0981t2 == enumC0981t3 && enumC0981t == enumC0981t4) {
            throw new IllegalStateException(("State must be at least CREATED to move to " + enumC0981t + ", but was " + this.f11908p + " in component " + this.f11909q.get()).toString());
        }
        this.f11908p = enumC0981t;
        if (this.f11911s || this.f11910r != 0) {
            this.f11912t = true;
            return;
        }
        this.f11911s = true;
        f1();
        this.f11911s = false;
        if (this.f11908p == enumC0981t4) {
            this.o = new C2417a();
        }
    }

    public final void e1(EnumC0981t enumC0981t) {
        A7.m.f(enumC0981t, "state");
        b1("setCurrentState");
        d1(enumC0981t);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0030, code lost:
    
        r7.f11912t = false;
        r7.f11914v.l(r7.f11908p);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0039, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f1() {
        /*
            Method dump skipped, instruction units count: 374
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.C.f1():void");
    }
}
