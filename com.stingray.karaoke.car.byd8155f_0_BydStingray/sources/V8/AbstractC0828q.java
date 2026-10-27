package V8;

import Q5.C0576h2;
import S8.AbstractC0727y;
import S8.EnumC0726x;
import S8.InterfaceC0725w;
import U8.EnumC0810a;
import W8.AbstractC0843c;
import java.io.Serializable;
import q7.C2195i;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: V8.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class AbstractC0828q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0576h2 f9645a = new C0576h2(5);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final B2.q f9646b = new B2.q("NO_VALUE", 3);
    public static final B2.q c;
    public static final B2.q d;

    static {
        int i7 = 3;
        c = new B2.q("NONE", i7);
        d = new B2.q("PENDING", i7);
    }

    public static final S a(int i7, int i10, EnumC0810a enumC0810a) {
        if (i7 < 0) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "replay cannot be negative, but was ").toString());
        }
        if (i10 < 0) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i10, "extraBufferCapacity cannot be negative, but was ").toString());
        }
        if (i7 <= 0 && i10 <= 0 && enumC0810a != EnumC0810a.l) {
            throw new IllegalArgumentException(("replay or extraBufferCapacity must be positive with non-default onBufferOverflow strategy " + enumC0810a).toString());
        }
        int i11 = i10 + i7;
        if (i11 < 0) {
            i11 = Integer.MAX_VALUE;
        }
        return new S(i7, i11, enumC0810a);
    }

    public static /* synthetic */ S b(int i7, int i10, EnumC0810a enumC0810a, int i11) {
        if ((i11 & 1) != 0) {
            i7 = 0;
        }
        if ((i11 & 2) != 0) {
            i10 = 0;
        }
        if ((i11 & 4) != 0) {
            enumC0810a = EnumC0810a.l;
        }
        return a(i7, i10, enumC0810a);
    }

    public static final f0 c(Object obj) {
        if (obj == null) {
            obj = AbstractC0843c.f9911b;
        }
        return new f0(obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object d(V8.h0 r4, z7.InterfaceC2786o r5, java.lang.Throwable r6, s7.AbstractC2410c r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof V8.r
            if (r0 == 0) goto L13
            r0 = r7
            V8.r r0 = (V8.r) r0
            int r1 = r0.f9648n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f9648n = r1
            goto L18
        L13:
            V8.r r0 = new V8.r
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.f9647m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f9648n
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            java.lang.Throwable r6 = r0.l
            h8.AbstractC1386c.u(r7)     // Catch: java.lang.Throwable -> L29
            goto L41
        L29:
            r4 = move-exception
            goto L44
        L2b:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L33:
            h8.AbstractC1386c.u(r7)
            r0.l = r6     // Catch: java.lang.Throwable -> L29
            r0.f9648n = r3     // Catch: java.lang.Throwable -> L29
            java.lang.Object r4 = r5.g(r4, r6, r0)     // Catch: java.lang.Throwable -> L29
            if (r4 != r1) goto L41
            goto L43
        L41:
            m7.q r1 = m7.q.f19267a
        L43:
            return r1
        L44:
            if (r6 == 0) goto L4b
            if (r6 == r4) goto L4b
            h6.q.l(r4, r6)
        L4b:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: V8.AbstractC0828q.d(V8.h0, z7.o, java.lang.Throwable, s7.c):java.lang.Object");
    }

    public static final void e(Object[] objArr, long j3, Object obj) {
        objArr[((int) j3) & (objArr.length - 1)] = obj;
    }

    public static InterfaceC0817f f(InterfaceC0817f interfaceC0817f, int i7) {
        EnumC0810a enumC0810a = EnumC0810a.l;
        if (i7 < 0 && i7 != -2 && i7 != -1) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "Buffer size should be non-negative, BUFFERED, or CONFLATED, but was ").toString());
        }
        if (i7 == -1) {
            enumC0810a = EnumC0810a.f9223m;
            i7 = 0;
        }
        boolean z6 = interfaceC0817f instanceof W8.w;
        C2195i c2195i = C2195i.l;
        return z6 ? ((W8.w) interfaceC0817f).a(c2195i, i7, enumC0810a) : new W8.j(i7, enumC0810a, interfaceC0817f, c2195i);
    }

    public static final Object g(InterfaceC0817f interfaceC0817f, InterfaceC2785n interfaceC2785n, InterfaceC2189c interfaceC2189c) {
        int i7 = AbstractC0836z.f9667a;
        Object objB = f(new W8.n(new P5.v(interfaceC2785n, null), interfaceC0817f, C2195i.l, -2, EnumC0810a.l), 0).b(W8.y.l, interfaceC2189c);
        EnumC2303a enumC2303a = EnumC2303a.l;
        m7.q qVar = m7.q.f19267a;
        if (objB != enumC2303a) {
            objB = qVar;
        }
        return objB == enumC2303a ? objB : qVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final h2.s h(V8.InterfaceC0817f r7, int r8) {
        /*
            U8.o r0 = U8.p.f9268e
            r0.getClass()
            int r0 = U8.o.f9267b
            if (r8 >= r0) goto La
            goto Lb
        La:
            r0 = r8
        Lb:
            int r0 = r0 - r8
            boolean r1 = r7 instanceof W8.g
            if (r1 == 0) goto L3b
            r1 = r7
            W8.g r1 = (W8.g) r1
            V8.f r2 = r1.f()
            if (r2 == 0) goto L3b
            h2.s r7 = new h2.s
            r3 = -3
            U8.a r4 = r1.f9917n
            int r5 = r1.f9916m
            if (r5 == r3) goto L29
            r3 = -2
            if (r5 == r3) goto L29
            if (r5 == 0) goto L29
            r0 = r5
            goto L35
        L29:
            U8.a r3 = U8.EnumC0810a.l
            r6 = 0
            if (r4 != r3) goto L32
            if (r5 != 0) goto L35
        L30:
            r0 = r6
            goto L35
        L32:
            if (r8 != 0) goto L30
            r0 = 1
        L35:
            q7.h r8 = r1.l
            r7.<init>(r0, r4, r2, r8)
            return r7
        L3b:
            h2.s r8 = new h2.s
            U8.a r1 = U8.EnumC0810a.l
            q7.i r2 = q7.C2195i.l
            r8.<init>(r0, r1, r7, r2)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: V8.AbstractC0828q.h(V8.f, int):h2.s");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0066 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0073 A[Catch: all -> 0x0035, TRY_LEAVE, TryCatch #1 {all -> 0x0035, blocks: (B:13:0x002f, B:25:0x0056, B:29:0x006b, B:31:0x0073, B:20:0x0047, B:24:0x0052), top: B:52:0x0021 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0085 -> B:14:0x0032). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object i(V8.InterfaceC0818g r7, U8.z r8, boolean r9, q7.InterfaceC2189c r10) throws java.lang.Throwable {
        /*
            boolean r0 = r10 instanceof V8.C0820i
            if (r0 == 0) goto L13
            r0 = r10
            V8.i r0 = (V8.C0820i) r0
            int r1 = r0.f9628q
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f9628q = r1
            goto L18
        L13:
            V8.i r0 = new V8.i
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.f9627p
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f9628q
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L4b
            if (r2 == r5) goto L3f
            if (r2 != r4) goto L37
            boolean r9 = r0.o
            U8.d r7 = r0.f9626n
            U8.z r8 = r0.f9625m
            V8.g r2 = r0.l
            h8.AbstractC1386c.u(r10)     // Catch: java.lang.Throwable -> L35
        L32:
            r10 = r7
            r7 = r2
            goto L56
        L35:
            r7 = move-exception
            goto L90
        L37:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3f:
            boolean r9 = r0.o
            U8.d r7 = r0.f9626n
            U8.z r8 = r0.f9625m
            V8.g r2 = r0.l
            h8.AbstractC1386c.u(r10)     // Catch: java.lang.Throwable -> L35
            goto L6b
        L4b:
            h8.AbstractC1386c.u(r10)
            boolean r10 = r7 instanceof V8.h0
            if (r10 != 0) goto Lab
            U8.d r10 = r8.iterator()     // Catch: java.lang.Throwable -> L35
        L56:
            r0.l = r7     // Catch: java.lang.Throwable -> L35
            r0.f9625m = r8     // Catch: java.lang.Throwable -> L35
            r0.f9626n = r10     // Catch: java.lang.Throwable -> L35
            r0.o = r9     // Catch: java.lang.Throwable -> L35
            r0.f9628q = r5     // Catch: java.lang.Throwable -> L35
            java.lang.Object r2 = r10.a(r0)     // Catch: java.lang.Throwable -> L35
            if (r2 != r1) goto L67
            return r1
        L67:
            r6 = r2
            r2 = r7
            r7 = r10
            r10 = r6
        L6b:
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch: java.lang.Throwable -> L35
            boolean r10 = r10.booleanValue()     // Catch: java.lang.Throwable -> L35
            if (r10 == 0) goto L88
            java.lang.Object r10 = r7.c()     // Catch: java.lang.Throwable -> L35
            r0.l = r2     // Catch: java.lang.Throwable -> L35
            r0.f9625m = r8     // Catch: java.lang.Throwable -> L35
            r0.f9626n = r7     // Catch: java.lang.Throwable -> L35
            r0.o = r9     // Catch: java.lang.Throwable -> L35
            r0.f9628q = r4     // Catch: java.lang.Throwable -> L35
            java.lang.Object r10 = r2.f(r10, r0)     // Catch: java.lang.Throwable -> L35
            if (r10 != r1) goto L32
            return r1
        L88:
            if (r9 == 0) goto L8d
            r8.g(r3)
        L8d:
            m7.q r7 = m7.q.f19267a
            return r7
        L90:
            throw r7     // Catch: java.lang.Throwable -> L91
        L91:
            r10 = move-exception
            if (r9 == 0) goto Laa
            boolean r9 = r7 instanceof java.util.concurrent.CancellationException
            if (r9 == 0) goto L9b
            r3 = r7
            java.util.concurrent.CancellationException r3 = (java.util.concurrent.CancellationException) r3
        L9b:
            if (r3 != 0) goto La7
            java.util.concurrent.CancellationException r3 = new java.util.concurrent.CancellationException
            java.lang.String r9 = "Channel was consumed, consumer had failed"
            r3.<init>(r9)
            r3.initCause(r7)
        La7:
            r8.g(r3)
        Laa:
            throw r10
        Lab:
            V8.h0 r7 = (V8.h0) r7
            java.lang.Throwable r7 = r7.l
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: V8.AbstractC0828q.i(V8.g, U8.z, boolean, q7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object j(V8.InterfaceC0817f r6, s7.AbstractC2410c r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof V8.B
            if (r0 == 0) goto L13
            r0 = r7
            V8.B r0 = (V8.B) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.o = r1
            goto L18
        L13:
            V8.B r0 = new V8.B
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.f9558n
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            B2.q r3 = W8.AbstractC0843c.f9911b
            r4 = 1
            if (r2 == 0) goto L37
            if (r2 != r4) goto L2f
            F0.k1 r6 = r0.f9557m
            A7.z r0 = r0.l
            h8.AbstractC1386c.u(r7)     // Catch: W8.C0841a -> L2d
            goto L5e
        L2d:
            r7 = move-exception
            goto L5a
        L2f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L37:
            h8.AbstractC1386c.u(r7)
            A7.z r7 = new A7.z
            r7.<init>()
            r7.l = r3
            F0.k1 r2 = new F0.k1
            r5 = 6
            r2.<init>(r7, r5)
            r0.l = r7     // Catch: W8.C0841a -> L56
            r0.f9557m = r2     // Catch: W8.C0841a -> L56
            r0.o = r4     // Catch: W8.C0841a -> L56
            java.lang.Object r6 = r6.b(r2, r0)     // Catch: W8.C0841a -> L56
            if (r6 != r1) goto L54
            goto L62
        L54:
            r0 = r7
            goto L5e
        L56:
            r6 = move-exception
            r0 = r7
            r7 = r6
            r6 = r2
        L5a:
            V8.g r1 = r7.l
            if (r1 != r6) goto L6b
        L5e:
            java.lang.Object r1 = r0.l
            if (r1 == r3) goto L63
        L62:
            return r1
        L63:
            java.util.NoSuchElementException r6 = new java.util.NoSuchElementException
            java.lang.String r7 = "Expected at least one element"
            r6.<init>(r7)
            throw r6
        L6b:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: V8.AbstractC0828q.j(V8.f, s7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object k(V8.InterfaceC0817f r5, z7.InterfaceC2785n r6, s7.AbstractC2410c r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof V8.C
            if (r0 == 0) goto L13
            r0 = r7
            V8.C r0 = (V8.C) r0
            int r1 = r0.f9561p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f9561p = r1
            goto L18
        L13:
            V8.C r0 = new V8.C
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.o
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f9561p
            B2.q r3 = W8.AbstractC0843c.f9911b
            r4 = 1
            if (r2 == 0) goto L39
            if (r2 != r4) goto L31
            C.f r5 = r0.f9560n
            A7.z r6 = r0.f9559m
            z7.n r0 = r0.l
            h8.AbstractC1386c.u(r7)     // Catch: W8.C0841a -> L2f
            goto L63
        L2f:
            r7 = move-exception
            goto L5f
        L31:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L39:
            h8.AbstractC1386c.u(r7)
            A7.z r7 = new A7.z
            r7.<init>()
            r7.l = r3
            C.f r2 = new C.f
            r2.<init>(r6, r7)
            r0.l = r6     // Catch: W8.C0841a -> L5a
            r0.f9559m = r7     // Catch: W8.C0841a -> L5a
            r0.f9560n = r2     // Catch: W8.C0841a -> L5a
            r0.f9561p = r4     // Catch: W8.C0841a -> L5a
            java.lang.Object r5 = r5.b(r2, r0)     // Catch: W8.C0841a -> L5a
            if (r5 != r1) goto L57
            goto L67
        L57:
            r0 = r6
            r6 = r7
            goto L63
        L5a:
            r5 = move-exception
            r0 = r6
            r6 = r7
            r7 = r5
            r5 = r2
        L5f:
            V8.g r1 = r7.l
            if (r1 != r5) goto L7c
        L63:
            java.lang.Object r1 = r6.l
            if (r1 == r3) goto L68
        L67:
            return r1
        L68:
            java.util.NoSuchElementException r5 = new java.util.NoSuchElementException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Expected at least one element matching the predicate "
            r6.<init>(r7)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L7c:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: V8.AbstractC0828q.k(V8.f, z7.n, s7.c):java.lang.Object");
    }

    public static final InterfaceC0817f l(O o, InterfaceC2194h interfaceC2194h, int i7, EnumC0810a enumC0810a) {
        return ((i7 == 0 || i7 == -3) && enumC0810a == EnumC0810a.l) ? o : new W8.j(i7, enumC0810a, o, interfaceC2194h);
    }

    public static final N m(InterfaceC0817f interfaceC0817f, InterfaceC0725w interfaceC0725w, W w10, Serializable serializable) {
        h2.s sVarH = h(interfaceC0817f, 1);
        f0 f0VarC = c(serializable);
        InterfaceC0817f interfaceC0817f2 = (InterfaceC0817f) sVarH.f15238b;
        AbstractC0727y.v(interfaceC0725w, (InterfaceC2194h) sVarH.d, w10.equals(V.f9597a) ? EnumC0726x.l : EnumC0726x.o, new F(w10, interfaceC0817f2, f0VarC, serializable, null));
        return new N(f0VarC);
    }
}
