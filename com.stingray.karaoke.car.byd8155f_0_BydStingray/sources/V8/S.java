package V8;

import S8.C0708e;
import S8.C0712i;
import U8.EnumC0810a;
import W8.AbstractC0842b;
import W8.AbstractC0843c;
import W8.AbstractC0844d;
import java.util.Arrays;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;

/* JADX INFO: loaded from: classes.dex */
public class S extends AbstractC0842b implements K, InterfaceC0817f, W8.w {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f9585p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f9586q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final EnumC0810a f9587r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Object[] f9588s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f9589t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public long f9590u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f9591v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f9592w;

    public S(int i7, int i10, EnumC0810a enumC0810a) {
        this.f9585p = i7;
        this.f9586q = i10;
        this.f9587r = enumC0810a;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:0|2|(2:4|(1:6)(1:7))(0)|8|(1:52)|(2:10|(1:(1:(7:14|15|16|31|58|(4:32|(9:56|(2:41|42)|43|(1:45)|16|31|58|32|(0)(1:34))(0)|48|49)|36)(2:19|20))(5:21|22|58|(4:32|(0)(0)|48|49)|36))(4:24|54|25|26))(1:29)|50|30|31|58|(4:32|(0)(0)|48|49)|36) */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00b1, code lost:
    
        r10 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00b2, code lost:
    
        r5 = r8;
        r8 = r10;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0081 A[Catch: all -> 0x0038, TryCatch #1 {all -> 0x0038, blocks: (B:15:0x0031, B:32:0x0079, B:34:0x0081, B:38:0x0094, B:41:0x009b, B:42:0x009f, B:43:0x00a0, B:22:0x004b), top: B:52:0x0020 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0092 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v4, types: [V8.g] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r5v1, types: [W8.b] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v4, types: [V8.S] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r9v0, types: [V8.g] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v2, types: [W8.d] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5, types: [V8.T] */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8, types: [V8.T] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x00ae -> B:16:0x0034). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m(V8.S r8, V8.InterfaceC0818g r9, q7.InterfaceC2189c r10) throws java.lang.Throwable {
        /*
            boolean r0 = r10 instanceof V8.Q
            if (r0 == 0) goto L13
            r0 = r10
            V8.Q r0 = (V8.Q) r0
            int r1 = r0.f9584r
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f9584r = r1
            goto L18
        L13:
            V8.Q r0 = new V8.Q
            r0.<init>(r8, r10)
        L18:
            java.lang.Object r10 = r0.f9582p
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f9584r
            r3 = 3
            r4 = 2
            if (r2 == 0) goto L5e
            r8 = 1
            if (r2 == r8) goto L4f
            if (r2 == r4) goto L43
            if (r2 != r3) goto L3b
            S8.W r8 = r0.o
            V8.T r9 = r0.f9581n
            V8.g r2 = r0.f9580m
            V8.S r5 = r0.l
            h8.AbstractC1386c.u(r10)     // Catch: java.lang.Throwable -> L38
        L34:
            r10 = r2
            r2 = r8
            r8 = r5
            goto L76
        L38:
            r8 = move-exception
            goto Lb4
        L3b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L43:
            S8.W r8 = r0.o
            V8.T r9 = r0.f9581n
            V8.g r2 = r0.f9580m
            V8.S r5 = r0.l
            h8.AbstractC1386c.u(r10)     // Catch: java.lang.Throwable -> L38
            goto L79
        L4f:
            V8.T r9 = r0.f9581n
            V8.g r8 = r0.f9580m
            V8.S r2 = r0.l
            h8.AbstractC1386c.u(r10)     // Catch: java.lang.Throwable -> L5b
            r10 = r8
            r8 = r2
            goto L6a
        L5b:
            r8 = move-exception
            r5 = r2
            goto Lb4
        L5e:
            h8.AbstractC1386c.u(r10)
            W8.d r10 = r8.c()
            V8.T r10 = (V8.T) r10
            r7 = r10
            r10 = r9
            r9 = r7
        L6a:
            q7.h r2 = r0.getContext()     // Catch: java.lang.Throwable -> Lb1
            S8.t r5 = S8.C0722t.f8137m     // Catch: java.lang.Throwable -> Lb1
            q7.f r2 = r2.F(r5)     // Catch: java.lang.Throwable -> Lb1
            S8.W r2 = (S8.W) r2     // Catch: java.lang.Throwable -> Lb1
        L76:
            r5 = r8
            r8 = r2
            r2 = r10
        L79:
            java.lang.Object r10 = r5.u(r9)     // Catch: java.lang.Throwable -> L38
            B2.q r6 = V8.AbstractC0828q.f9646b     // Catch: java.lang.Throwable -> L38
            if (r10 != r6) goto L92
            r0.l = r5     // Catch: java.lang.Throwable -> L38
            r0.f9580m = r2     // Catch: java.lang.Throwable -> L38
            r0.f9581n = r9     // Catch: java.lang.Throwable -> L38
            r0.o = r8     // Catch: java.lang.Throwable -> L38
            r0.f9584r = r4     // Catch: java.lang.Throwable -> L38
            java.lang.Object r10 = r5.k(r9, r0)     // Catch: java.lang.Throwable -> L38
            if (r10 != r1) goto L79
            return
        L92:
            if (r8 == 0) goto La0
            boolean r6 = r8.b()     // Catch: java.lang.Throwable -> L38
            if (r6 == 0) goto L9b
            goto La0
        L9b:
            java.util.concurrent.CancellationException r8 = r8.y()     // Catch: java.lang.Throwable -> L38
            throw r8     // Catch: java.lang.Throwable -> L38
        La0:
            r0.l = r5     // Catch: java.lang.Throwable -> L38
            r0.f9580m = r2     // Catch: java.lang.Throwable -> L38
            r0.f9581n = r9     // Catch: java.lang.Throwable -> L38
            r0.o = r8     // Catch: java.lang.Throwable -> L38
            r0.f9584r = r3     // Catch: java.lang.Throwable -> L38
            java.lang.Object r10 = r2.f(r10, r0)     // Catch: java.lang.Throwable -> L38
            if (r10 != r1) goto L34
            return
        Lb1:
            r10 = move-exception
            r5 = r8
            r8 = r10
        Lb4:
            r5.g(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: V8.S.m(V8.S, V8.g, q7.c):void");
    }

    @Override // W8.w
    public final InterfaceC0817f a(InterfaceC2194h interfaceC2194h, int i7, EnumC0810a enumC0810a) {
        return AbstractC0828q.l(this, interfaceC2194h, i7, enumC0810a);
    }

    @Override // V8.InterfaceC0817f
    public final Object b(InterfaceC0818g interfaceC0818g, InterfaceC2189c interfaceC2189c) throws Throwable {
        m(this, interfaceC0818g, interfaceC2189c);
        return EnumC2303a.l;
    }

    @Override // W8.AbstractC0842b
    public final AbstractC0844d d() {
        T t10 = new T();
        t10.f9593a = -1L;
        return t10;
    }

    @Override // W8.AbstractC0842b
    public final AbstractC0844d[] e() {
        return new T[2];
    }

    @Override // V8.InterfaceC0818g
    public final Object f(Object obj, InterfaceC2189c interfaceC2189c) {
        InterfaceC2189c[] interfaceC2189cArrP;
        P p10;
        if (j(obj)) {
            return m7.q.f19267a;
        }
        C0712i c0712i = new C0712i(1, h6.e.k(interfaceC2189c));
        c0712i.s();
        InterfaceC2189c[] interfaceC2189cArrP2 = AbstractC0843c.f9910a;
        synchronized (this) {
            try {
                if (s(obj)) {
                    c0712i.resumeWith(m7.q.f19267a);
                    interfaceC2189cArrP = p(interfaceC2189cArrP2);
                    p10 = null;
                } else {
                    P p11 = new P(this, ((long) (this.f9591v + this.f9592w)) + q(), obj, c0712i);
                    o(p11);
                    this.f9592w++;
                    if (this.f9586q == 0) {
                        interfaceC2189cArrP2 = p(interfaceC2189cArrP2);
                    }
                    interfaceC2189cArrP = interfaceC2189cArrP2;
                    p10 = p11;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (p10 != null) {
            c0712i.v(new C0708e(p10, 1));
        }
        for (InterfaceC2189c interfaceC2189c2 : interfaceC2189cArrP) {
            if (interfaceC2189c2 != null) {
                interfaceC2189c2.resumeWith(m7.q.f19267a);
            }
        }
        Object objQ = c0712i.q();
        EnumC2303a enumC2303a = EnumC2303a.l;
        if (objQ != enumC2303a) {
            objQ = m7.q.f19267a;
        }
        return objQ == enumC2303a ? objQ : m7.q.f19267a;
    }

    @Override // V8.K
    public final void h() {
        synchronized (this) {
            v(q() + ((long) this.f9591v), this.f9590u, q() + ((long) this.f9591v), q() + ((long) this.f9591v) + ((long) this.f9592w));
        }
    }

    @Override // V8.K
    public final boolean j(Object obj) {
        int i7;
        boolean z6;
        InterfaceC2189c[] interfaceC2189cArrP = AbstractC0843c.f9910a;
        synchronized (this) {
            if (s(obj)) {
                interfaceC2189cArrP = p(interfaceC2189cArrP);
                z6 = true;
            } else {
                z6 = false;
            }
        }
        for (InterfaceC2189c interfaceC2189c : interfaceC2189cArrP) {
            if (interfaceC2189c != null) {
                interfaceC2189c.resumeWith(m7.q.f19267a);
            }
        }
        return z6;
    }

    public final Object k(T t10, Q q4) {
        C0712i c0712i = new C0712i(1, h6.e.k(q4));
        c0712i.s();
        synchronized (this) {
            if (t(t10) < 0) {
                t10.f9594b = c0712i;
            } else {
                c0712i.resumeWith(m7.q.f19267a);
            }
        }
        Object objQ = c0712i.q();
        return objQ == EnumC2303a.l ? objQ : m7.q.f19267a;
    }

    public final void l() {
        if (this.f9586q != 0 || this.f9592w > 1) {
            Object[] objArr = this.f9588s;
            A7.m.c(objArr);
            while (this.f9592w > 0) {
                long jQ = q();
                int i7 = this.f9591v;
                int i10 = this.f9592w;
                if (objArr[((int) ((jQ + ((long) (i7 + i10))) - 1)) & (objArr.length - 1)] != AbstractC0828q.f9646b) {
                    return;
                }
                this.f9592w = i10 - 1;
                AbstractC0828q.e(objArr, q() + ((long) (this.f9591v + this.f9592w)), null);
            }
        }
    }

    public final void n() {
        AbstractC0844d[] abstractC0844dArr;
        Object[] objArr = this.f9588s;
        A7.m.c(objArr);
        AbstractC0828q.e(objArr, q(), null);
        this.f9591v--;
        long jQ = q() + 1;
        if (this.f9589t < jQ) {
            this.f9589t = jQ;
        }
        if (this.f9590u < jQ) {
            if (this.f9908m != 0 && (abstractC0844dArr = this.l) != null) {
                for (AbstractC0844d abstractC0844d : abstractC0844dArr) {
                    if (abstractC0844d != null) {
                        T t10 = (T) abstractC0844d;
                        long j3 = t10.f9593a;
                        if (j3 >= 0 && j3 < jQ) {
                            t10.f9593a = jQ;
                        }
                    }
                }
            }
            this.f9590u = jQ;
        }
    }

    public final void o(Object obj) {
        int i7 = this.f9591v + this.f9592w;
        Object[] objArrR = this.f9588s;
        if (objArrR == null) {
            objArrR = r(null, 0, 2);
        } else if (i7 >= objArrR.length) {
            objArrR = r(objArrR, i7, objArrR.length * 2);
        }
        AbstractC0828q.e(objArrR, q() + ((long) i7), obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [q7.c[]] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r6v3 */
    public final InterfaceC2189c[] p(InterfaceC2189c[] interfaceC2189cArr) {
        AbstractC0844d[] abstractC0844dArr;
        T t10;
        C0712i c0712i;
        int length = interfaceC2189cArr.length;
        if (this.f9908m != 0 && (abstractC0844dArr = this.l) != null) {
            int length2 = abstractC0844dArr.length;
            int i7 = 0;
            interfaceC2189cArr = interfaceC2189cArr;
            while (i7 < length2) {
                AbstractC0844d abstractC0844d = abstractC0844dArr[i7];
                if (abstractC0844d != null && (c0712i = (t10 = (T) abstractC0844d).f9594b) != null && t(t10) >= 0) {
                    int length3 = interfaceC2189cArr.length;
                    interfaceC2189cArr = interfaceC2189cArr;
                    if (length >= length3) {
                        Object[] objArrCopyOf = Arrays.copyOf((Object[]) interfaceC2189cArr, Math.max(2, interfaceC2189cArr.length * 2));
                        A7.m.e(objArrCopyOf, "copyOf(...)");
                        interfaceC2189cArr = objArrCopyOf;
                    }
                    ((InterfaceC2189c[]) interfaceC2189cArr)[length] = c0712i;
                    t10.f9594b = null;
                    length++;
                }
                i7++;
                interfaceC2189cArr = interfaceC2189cArr;
            }
        }
        return (InterfaceC2189c[]) interfaceC2189cArr;
    }

    public final long q() {
        return Math.min(this.f9590u, this.f9589t);
    }

    public final Object[] r(Object[] objArr, int i7, int i10) {
        if (i10 <= 0) {
            throw new IllegalStateException("Buffer size overflow");
        }
        Object[] objArr2 = new Object[i10];
        this.f9588s = objArr2;
        if (objArr == null) {
            return objArr2;
        }
        long jQ = q();
        for (int i11 = 0; i11 < i7; i11++) {
            long j3 = ((long) i11) + jQ;
            AbstractC0828q.e(objArr2, j3, objArr[((int) j3) & (objArr.length - 1)]);
        }
        return objArr2;
    }

    public final boolean s(Object obj) {
        int i7 = this.f9908m;
        int i10 = this.f9585p;
        if (i7 == 0) {
            if (i10 != 0) {
                o(obj);
                int i11 = this.f9591v + 1;
                this.f9591v = i11;
                if (i11 > i10) {
                    n();
                }
                this.f9590u = q() + ((long) this.f9591v);
            }
            return true;
        }
        int i12 = this.f9591v;
        int i13 = this.f9586q;
        if (i12 >= i13 && this.f9590u <= this.f9589t) {
            int iOrdinal = this.f9587r.ordinal();
            if (iOrdinal == 0) {
                return false;
            }
            if (iOrdinal != 1) {
                if (iOrdinal == 2) {
                    return true;
                }
                throw new A9.k();
            }
        }
        o(obj);
        int i14 = this.f9591v + 1;
        this.f9591v = i14;
        if (i14 > i13) {
            n();
        }
        long jQ = q() + ((long) this.f9591v);
        long j3 = this.f9589t;
        if (((int) (jQ - j3)) > i10) {
            v(1 + j3, this.f9590u, q() + ((long) this.f9591v), q() + ((long) this.f9591v) + ((long) this.f9592w));
        }
        return true;
    }

    public final long t(T t10) {
        long j3 = t10.f9593a;
        if (j3 < q() + ((long) this.f9591v)) {
            return j3;
        }
        if (this.f9586q <= 0 && j3 <= q() && this.f9592w != 0) {
            return j3;
        }
        return -1L;
    }

    public final Object u(T t10) {
        Object obj;
        InterfaceC2189c[] interfaceC2189cArrW = AbstractC0843c.f9910a;
        synchronized (this) {
            try {
                long jT = t(t10);
                if (jT < 0) {
                    obj = AbstractC0828q.f9646b;
                } else {
                    long j3 = t10.f9593a;
                    Object[] objArr = this.f9588s;
                    A7.m.c(objArr);
                    Object obj2 = objArr[((int) jT) & (objArr.length - 1)];
                    if (obj2 instanceof P) {
                        obj2 = ((P) obj2).f9579n;
                    }
                    t10.f9593a = jT + 1;
                    Object obj3 = obj2;
                    interfaceC2189cArrW = w(j3);
                    obj = obj3;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        for (InterfaceC2189c interfaceC2189c : interfaceC2189cArrW) {
            if (interfaceC2189c != null) {
                interfaceC2189c.resumeWith(m7.q.f19267a);
            }
        }
        return obj;
    }

    public final void v(long j3, long j10, long j11, long j12) {
        long jMin = Math.min(j10, j3);
        for (long jQ = q(); jQ < jMin; jQ++) {
            Object[] objArr = this.f9588s;
            A7.m.c(objArr);
            AbstractC0828q.e(objArr, jQ, null);
        }
        this.f9589t = j3;
        this.f9590u = j10;
        this.f9591v = (int) (j11 - jMin);
        this.f9592w = (int) (j12 - j11);
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final q7.InterfaceC2189c[] w(long r23) {
        /*
            Method dump skipped, instruction units count: 255
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: V8.S.w(long):q7.c[]");
    }
}
