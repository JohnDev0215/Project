package X8;

import Q5.C0576h2;
import S8.AbstractC0727y;
import S8.InterfaceC0723u;
import S8.m0;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import q7.InterfaceC2194h;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final B2.q f10548b;
    public static final B2.q c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final B2.q f10547a = new B2.q("CLOSED", 3);
    public static final B2.q d = new B2.q("NO_THREAD_ELEMENTS", 3);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C0576h2 f10549e = new C0576h2(7);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C0576h2 f10550f = new C0576h2(8);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C0576h2 f10551g = new C0576h2(9);

    static {
        int i7 = 3;
        f10548b = new B2.q("UNDEFINED", i7);
        c = new B2.q("REUSABLE_CLAIMED", i7);
    }

    public static final void a(InterfaceC2782k interfaceC2782k, Object obj, InterfaceC2194h interfaceC2194h) throws IllegalAccessException, InvocationTargetException {
        A9.k kVarB = b(interfaceC2782k, obj, null);
        if (kVarB != null) {
            AbstractC0727y.q(kVarB, interfaceC2194h);
        }
    }

    public static final A9.k b(InterfaceC2782k interfaceC2782k, Object obj, A9.k kVar) throws IllegalAccessException, InvocationTargetException {
        try {
            interfaceC2782k.b(obj);
        } catch (Throwable th) {
            if (kVar == null || kVar.getCause() == th) {
                return new A9.k("Exception in undelivered element handler for " + obj, th);
            }
            h6.q.l(kVar, th);
        }
        return kVar;
    }

    public static final void c(int i7) {
        if (i7 < 1) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "Expected positive parallelism level, but got ").toString());
        }
    }

    public static final Object d(r rVar, long j3, InterfaceC2785n interfaceC2785n) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        while (true) {
            if (rVar.f10577n >= j3 && !rVar.d()) {
                return rVar;
            }
            Object obj = b.l.get(rVar);
            B2.q qVar = f10547a;
            if (obj == qVar) {
                return qVar;
            }
            r rVar2 = (r) ((b) obj);
            if (rVar2 == null) {
                rVar2 = (r) interfaceC2785n.invoke(Long.valueOf(rVar.f10577n + 1), rVar);
                do {
                    atomicReferenceFieldUpdater = b.l;
                    if (atomicReferenceFieldUpdater.compareAndSet(rVar, null, rVar2)) {
                        if (rVar.d()) {
                            rVar.e();
                        }
                    }
                } while (atomicReferenceFieldUpdater.get(rVar) == null);
            }
            rVar = rVar2;
        }
    }

    public static final r e(Object obj) {
        if (obj != f10547a) {
            return (r) obj;
        }
        throw new IllegalStateException("Does not contain segment");
    }

    public static final void f(Throwable th, InterfaceC2194h interfaceC2194h) {
        Throwable runtimeException;
        Iterator it = d.f10553a.iterator();
        while (it.hasNext()) {
            try {
                ((InterfaceC0723u) it.next()).G(th, interfaceC2194h);
            } catch (Throwable th2) {
                if (th == th2) {
                    runtimeException = th;
                } else {
                    runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                    h6.q.l(runtimeException, th);
                }
                Thread threadCurrentThread = Thread.currentThread();
                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, runtimeException);
            }
        }
        try {
            h6.q.l(th, new e(interfaceC2194h));
        } catch (Throwable unused) {
        }
        Thread threadCurrentThread2 = Thread.currentThread();
        threadCurrentThread2.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread2, th);
    }

    public static final boolean g(Object obj) {
        return obj == f10547a;
    }

    public static final Object h(Object obj, Object obj2) {
        if (obj == null) {
            return obj2;
        }
        if (obj instanceof ArrayList) {
            ((ArrayList) obj).add(obj2);
            return obj;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(obj2);
        return arrayList;
    }

    public static final void i(InterfaceC2194h interfaceC2194h, Object obj) {
        if (obj == d) {
            return;
        }
        if (!(obj instanceof v)) {
            Object objJ = interfaceC2194h.j(null, f10550f);
            A7.m.d(objJ, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
            A7.k.q(objJ);
            throw null;
        }
        v vVar = (v) obj;
        m0[] m0VarArr = vVar.f10583b;
        int length = m0VarArr.length - 1;
        if (length < 0) {
            return;
        }
        m0 m0Var = m0VarArr[length];
        A7.m.c(null);
        Object obj2 = vVar.f10582a[length];
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0086 A[Catch: all -> 0x0065, DONT_GENERATE, TryCatch #2 {all -> 0x0065, blocks: (B:15:0x0045, B:17:0x0053, B:19:0x0059, B:32:0x0089, B:22:0x0067, B:24:0x0075, B:29:0x0080, B:31:0x0086, B:37:0x0096, B:40:0x009f, B:39:0x009c, B:27:0x007b), top: B:52:0x0045, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void j(java.lang.Object r6, q7.InterfaceC2189c r7) {
        /*
            boolean r0 = r7 instanceof X8.f
            if (r0 == 0) goto La9
            X8.f r7 = (X8.f) r7
            java.lang.Throwable r0 = m7.l.a(r6)
            if (r0 != 0) goto Le
            r1 = r6
            goto L14
        Le:
            S8.q r1 = new S8.q
            r2 = 0
            r1.<init>(r0, r2)
        L14:
            S8.s r0 = r7.o
            s7.c r2 = r7.f10555p
            q7.h r3 = r2.getContext()
            boolean r3 = r0.N(r3)
            r4 = 1
            if (r3 == 0) goto L30
            r7.f10556q = r1
            r7.f8083n = r4
            q7.h r6 = r2.getContext()
            r0.L(r6, r7)
            goto Lac
        L30:
            S8.N r0 = S8.n0.a()
            boolean r3 = r0.T()
            if (r3 == 0) goto L42
            r7.f10556q = r1
            r7.f8083n = r4
            r0.Q(r7)
            goto Lac
        L42:
            r0.S(r4)
            q7.h r1 = r2.getContext()     // Catch: java.lang.Throwable -> L65
            S8.t r3 = S8.C0722t.f8137m     // Catch: java.lang.Throwable -> L65
            q7.f r1 = r1.F(r3)     // Catch: java.lang.Throwable -> L65
            S8.W r1 = (S8.W) r1     // Catch: java.lang.Throwable -> L65
            if (r1 == 0) goto L67
            boolean r3 = r1.b()     // Catch: java.lang.Throwable -> L65
            if (r3 != 0) goto L67
            java.util.concurrent.CancellationException r6 = r1.y()     // Catch: java.lang.Throwable -> L65
            m7.k r6 = h8.AbstractC1386c.f(r6)     // Catch: java.lang.Throwable -> L65
            r7.resumeWith(r6)     // Catch: java.lang.Throwable -> L65
            goto L89
        L65:
            r6 = move-exception
            goto La0
        L67:
            java.lang.Object r1 = r7.f10557r     // Catch: java.lang.Throwable -> L65
            q7.h r3 = r2.getContext()     // Catch: java.lang.Throwable -> L65
            java.lang.Object r1 = n(r3, r1)     // Catch: java.lang.Throwable -> L65
            B2.q r5 = X8.a.d     // Catch: java.lang.Throwable -> L65
            if (r1 == r5) goto L7a
            S8.t0 r5 = S8.AbstractC0727y.E(r2, r3, r1)     // Catch: java.lang.Throwable -> L65
            goto L7b
        L7a:
            r5 = 0
        L7b:
            r2.resumeWith(r6)     // Catch: java.lang.Throwable -> L93
            if (r5 == 0) goto L86
            boolean r6 = r5.j0()     // Catch: java.lang.Throwable -> L65
            if (r6 == 0) goto L89
        L86:
            i(r3, r1)     // Catch: java.lang.Throwable -> L65
        L89:
            boolean r6 = r0.V()     // Catch: java.lang.Throwable -> L65
            if (r6 != 0) goto L89
        L8f:
            r0.P(r4)
            goto Lac
        L93:
            r6 = move-exception
            if (r5 == 0) goto L9c
            boolean r2 = r5.j0()     // Catch: java.lang.Throwable -> L65
            if (r2 == 0) goto L9f
        L9c:
            i(r3, r1)     // Catch: java.lang.Throwable -> L65
        L9f:
            throw r6     // Catch: java.lang.Throwable -> L65
        La0:
            r7.f(r6)     // Catch: java.lang.Throwable -> La4
            goto L8f
        La4:
            r6 = move-exception
            r0.P(r4)
            throw r6
        La9:
            r7.resumeWith(r6)
        Lac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X8.a.j(java.lang.Object, q7.c):void");
    }

    public static final long k(String str, long j3, long j10, long j11) {
        String property;
        int i7 = t.f10579a;
        try {
            property = System.getProperty(str);
        } catch (SecurityException unused) {
            property = null;
        }
        if (property == null) {
            return j3;
        }
        Long lB0 = Q8.o.B0(property);
        if (lB0 == null) {
            throw new IllegalStateException(("System property '" + str + "' has unrecognized value '" + property + '\'').toString());
        }
        long jLongValue = lB0.longValue();
        if (j10 <= jLongValue && jLongValue <= j11) {
            return jLongValue;
        }
        throw new IllegalStateException(("System property '" + str + "' should be in range " + j10 + ".." + j11 + ", but is '" + jLongValue + '\'').toString());
    }

    public static int l(String str, int i7, int i10, int i11, int i12) {
        if ((i12 & 4) != 0) {
            i10 = 1;
        }
        if ((i12 & 8) != 0) {
            i11 = Integer.MAX_VALUE;
        }
        return (int) k(str, i7, i10, i11);
    }

    public static final Object m(InterfaceC2194h interfaceC2194h) {
        Object objJ = interfaceC2194h.j(0, f10549e);
        A7.m.c(objJ);
        return objJ;
    }

    public static final Object n(InterfaceC2194h interfaceC2194h, Object obj) {
        if (obj == null) {
            obj = m(interfaceC2194h);
        }
        if (obj == 0) {
            return d;
        }
        if (obj instanceof Integer) {
            return interfaceC2194h.j(new v(((Number) obj).intValue(), interfaceC2194h), f10551g);
        }
        A7.k.q(obj);
        throw null;
    }
}
