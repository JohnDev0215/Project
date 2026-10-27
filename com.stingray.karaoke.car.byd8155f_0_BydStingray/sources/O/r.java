package O;

import K.InterfaceC0355n0;
import N1.C0420a;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class r extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4987m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f4988n;
    public final /* synthetic */ C0420a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ B5.u f4989p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0355n0 f4990q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(C0420a c0420a, B5.u uVar, InterfaceC0355n0 interfaceC0355n0, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.o = c0420a;
        this.f4989p = uVar;
        this.f4990q = interfaceC0355n0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        r rVar = new r(this.o, this.f4989p, this.f4990q, interfaceC2189c);
        rVar.f4988n = obj;
        return rVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((r) create((y0.z) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0074  */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.Object, java.util.List] */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.Throwable {
        /*
            r9 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r9.f4987m
            r2 = 1
            r3 = 3
            r4 = 2
            if (r1 == 0) goto L24
            if (r1 == r2) goto L1c
            if (r1 == r4) goto L18
            if (r1 != r3) goto L10
            goto L18
        L10:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L18:
            h8.AbstractC1386c.u(r10)
            goto L81
        L1c:
            java.lang.Object r1 = r9.f4988n
            y0.z r1 = (y0.z) r1
            h8.AbstractC1386c.u(r10)
            goto L37
        L24:
            h8.AbstractC1386c.u(r10)
            java.lang.Object r10 = r9.f4988n
            r1 = r10
            y0.z r1 = (y0.z) r1
            r9.f4988n = r1
            r9.f4987m = r2
            java.lang.Object r10 = com.bumptech.glide.d.m(r1, r9)
            if (r10 != r0) goto L37
            return r0
        L37:
            y0.h r10 = (y0.C2730h) r10
            boolean r2 = com.bumptech.glide.d.G(r10)
            r5 = 0
            if (r2 == 0) goto L6e
            int r2 = r10.c
            r2 = r2 & 33
            if (r2 == 0) goto L6e
            java.lang.Object r2 = r10.f24065a
            int r6 = r2.size()
            r7 = 0
        L4d:
            if (r7 >= r6) goto L5f
            java.lang.Object r8 = r2.get(r7)
            y0.q r8 = (y0.q) r8
            boolean r8 = r8.b()
            if (r8 == 0) goto L5c
            goto L6e
        L5c:
            int r7 = r7 + 1
            goto L4d
        L5f:
            r9.f4988n = r5
            r9.f4987m = r4
            B5.u r2 = r9.f4989p
            N1.a r3 = r9.o
            java.lang.Object r9 = com.bumptech.glide.d.n(r1, r3, r2, r10, r9)
            if (r9 != r0) goto L81
            return r0
        L6e:
            boolean r2 = com.bumptech.glide.d.G(r10)
            if (r2 != 0) goto L81
            r9.f4988n = r5
            r9.f4987m = r3
            K.n0 r2 = r9.f4990q
            java.lang.Object r9 = com.bumptech.glide.d.o(r1, r2, r10, r9)
            if (r9 != r0) goto L81
            return r0
        L81:
            m7.q r9 = m7.q.f19267a
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: O.r.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
