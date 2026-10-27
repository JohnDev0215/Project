package d8;

import F8.K;
import Q7.AbstractC0662o;
import Q7.C0661n;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.M;
import Q7.S;
import Q7.f0;
import T7.AbstractC0791j;
import T7.C0790i;
import a.AbstractC0904a;
import b8.InterfaceC1008c;
import c8.C1038c;
import e8.C1236a;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import l3.P;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import v8.AbstractC2564d;

/* JADX INFO: renamed from: d8.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1122i extends AbstractC0791j implements InterfaceC1008c {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final D8.i f13579A;
    public final C1127n B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final M f13580C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final y8.i f13581D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final C1111C f13582E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final C1038c f13583F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final E8.i f13584G;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final N0.q f13585r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final W7.n f13586s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final InterfaceC0652e f13587t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final N0.q f13588u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final m7.n f13589v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f13590w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f13591x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final f0 f13592y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final boolean f13593z;

    static {
        AbstractC1996k.c0(new String[]{"equals", "hashCode", "getClass", "wait", "notify", "notifyAll", "toString"});
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public C1122i(N0.q r10, Q7.InterfaceC0657j r11, W7.n r12, Q7.InterfaceC0652e r13) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instruction units count: 336
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: d8.C1122i.<init>(N0.q, Q7.j, W7.n, Q7.e):void");
    }

    @Override // T7.y
    public final y8.n A(G8.f fVar) {
        M m10 = this.f13580C;
        AbstractC2564d.j(m10.f7178a);
        return (C1127n) ((y8.n) C7.a.N(m10.c, M.f7177e[0]));
    }

    @Override // Q7.InterfaceC0654g
    public final K C() {
        return this.f13579A;
    }

    @Override // Q7.InterfaceC0652e
    public final int E() {
        return this.f13590w;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean F() {
        return false;
    }

    @Override // Q7.InterfaceC0652e
    public final Collection H() {
        return (List) this.B.f13607q.a();
    }

    @Override // Q7.InterfaceC0652e
    public final boolean H0() {
        return false;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean L() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.Collection, n7.u] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.ArrayList] */
    @Override // Q7.InterfaceC0652e
    public final Collection X() throws IllegalAccessException, InvocationTargetException {
        Class[] clsArr;
        ?? arrayList = n7.u.l;
        if (this.f13591x != 2) {
            return arrayList;
        }
        Object obj = null;
        C1236a c1236aS = AbstractC0904a.S(2, false, false, null, 7);
        Class cls = this.f13586s.f9891a;
        A7.m.f(cls, "clazz");
        B8.t tVar = com.bumptech.glide.d.f12673a;
        if (tVar == null) {
            try {
                tVar = new B8.t(Class.class.getMethod("isSealed", null), Class.class.getMethod("getPermittedSubclasses", null), Class.class.getMethod("isRecord", null), Class.class.getMethod("getRecordComponents", null), 8);
            } catch (NoSuchMethodException unused) {
                tVar = new B8.t(obj, obj, obj, obj, 8);
            }
            com.bumptech.glide.d.f12673a = tVar;
        }
        Method method = (Method) tVar.o;
        if (method == null) {
            clsArr = null;
        } else {
            Object objInvoke = method.invoke(cls, null);
            A7.m.d(objInvoke, "null cannot be cast to non-null type kotlin.Array<java.lang.Class<*>>");
            clsArr = (Class[]) objInvoke;
        }
        if (clsArr != null) {
            arrayList = new ArrayList(clsArr.length);
            for (Class cls2 : clsArr) {
                arrayList.add(new W7.p(cls2));
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            InterfaceC0654g interfaceC0654gN = ((P) this.f13588u.l).G((W7.p) it.next(), c1236aS).B0().n();
            InterfaceC0652e interfaceC0652e = interfaceC0654gN instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0654gN : null;
            if (interfaceC0652e != null) {
                arrayList2.add(interfaceC0652e);
            }
        }
        return AbstractC1998m.u0(arrayList2, new C1121h());
    }

    @Override // Q7.InterfaceC0652e
    public final boolean b0() {
        return false;
    }

    @Override // Q7.InterfaceC0670x
    public final boolean c0() {
        return false;
    }

    @Override // Q7.InterfaceC0655h
    public final boolean d0() {
        return this.f13593z;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0660m, Q7.InterfaceC0670x
    public final C0661n e() {
        C0661n c0661n = AbstractC0662o.f7189a;
        f0 f0Var = this.f13592y;
        if (A7.m.a(f0Var, c0661n)) {
            Class<?> declaringClass = this.f13586s.f9891a.getDeclaringClass();
            if ((declaringClass != null ? new W7.n(declaringClass) : null) == null) {
                C0661n c0661n2 = Z7.o.f10949a;
                A7.m.e(c0661n2, "{\n            JavaDescri…KAGE_VISIBILITY\n        }");
                return c0661n2;
            }
        }
        return com.bumptech.glide.c.D0(f0Var);
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0670x
    public final int h() {
        return this.f13591x;
    }

    @Override // R7.a
    public final R7.h k() {
        return this.f13583F;
    }

    @Override // T7.AbstractC0783b, Q7.InterfaceC0652e
    public final y8.n l0() {
        return this.f13581D;
    }

    @Override // Q7.InterfaceC0652e
    public final S m0() {
        return null;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean n() {
        return false;
    }

    public final C1127n o0() {
        return (C1127n) super.w0();
    }

    @Override // Q7.InterfaceC0652e
    public final C0790i p0() {
        return null;
    }

    @Override // Q7.InterfaceC0652e
    public final y8.n q0() {
        return this.f13582E;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0655h
    public final List s() {
        return (List) this.f13584G.a();
    }

    public final String toString() {
        return "Lazy Java class " + AbstractC2564d.h(this);
    }

    @Override // T7.AbstractC0783b, Q7.InterfaceC0652e
    public final y8.n w0() {
        return (C1127n) super.w0();
    }

    @Override // Q7.InterfaceC0670x
    public final boolean y0() {
        return false;
    }
}
