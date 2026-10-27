package x0;

import A7.o;
import S8.InterfaceC0725w;
import e0.C1183w;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g f23678a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public o f23679b = new C1183w(this, 14);
    public InterfaceC0725w c;

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(long r8, long r10, s7.AbstractC2410c r12) throws java.lang.Throwable {
        /*
            r7 = this;
            boolean r0 = r12 instanceof x0.C2661b
            if (r0 == 0) goto L14
            r0 = r12
            x0.b r0 = (x0.C2661b) r0
            int r1 = r0.f23675n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.f23675n = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            x0.b r0 = new x0.b
            r0.<init>(r7, r12)
            goto L12
        L1a:
            java.lang.Object r12 = r6.l
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r6.f23675n
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            h8.AbstractC1386c.u(r12)
            goto L52
        L29:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L31:
            h8.AbstractC1386c.u(r12)
            x0.g r7 = r7.f23678a
            r12 = 0
            if (r7 == 0) goto L44
            boolean r1 = r7.f14695x
            if (r1 == 0) goto L44
            E0.s0 r7 = E0.AbstractC0156f.k(r7)
            r12 = r7
            x0.g r12 = (x0.g) r12
        L44:
            r1 = r12
            if (r1 == 0) goto L57
            r6.f23675n = r2
            r2 = r8
            r4 = r10
            java.lang.Object r12 = r1.q0(r2, r4, r6)
            if (r12 != r0) goto L52
            return r0
        L52:
            a1.o r12 = (a1.o) r12
            long r7 = r12.f11044a
            goto L59
        L57:
            r7 = 0
        L59:
            a1.o r9 = new a1.o
            r9.<init>(r7)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: x0.d.a(long, long, s7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(long r5, s7.AbstractC2410c r7) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r7 instanceof x0.c
            if (r0 == 0) goto L13
            r0 = r7
            x0.c r0 = (x0.c) r0
            int r1 = r0.f23677n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f23677n = r1
            goto L18
        L13:
            x0.c r0 = new x0.c
            r0.<init>(r4, r7)
        L18:
            java.lang.Object r7 = r0.l
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f23677n
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            h8.AbstractC1386c.u(r7)
            goto L4d
        L27:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2f:
            h8.AbstractC1386c.u(r7)
            x0.g r4 = r4.f23678a
            r7 = 0
            if (r4 == 0) goto L42
            boolean r2 = r4.f14695x
            if (r2 == 0) goto L42
            E0.s0 r4 = E0.AbstractC0156f.k(r4)
            r7 = r4
            x0.g r7 = (x0.g) r7
        L42:
            if (r7 == 0) goto L52
            r0.f23677n = r3
            java.lang.Object r7 = r7.B(r5, r0)
            if (r7 != r1) goto L4d
            return r1
        L4d:
            a1.o r7 = (a1.o) r7
            long r4 = r7.f11044a
            goto L54
        L52:
            r4 = 0
        L54:
            a1.o r6 = new a1.o
            r6.<init>(r4)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: x0.d.b(long, s7.c):java.lang.Object");
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [A7.o, z7.a] */
    public final InterfaceC0725w c() {
        InterfaceC0725w interfaceC0725w = (InterfaceC0725w) this.f23679b.a();
        if (interfaceC0725w != null) {
            return interfaceC0725w;
        }
        throw new IllegalStateException("in order to access nested coroutine scope you need to attach dispatcher to the `Modifier.nestedScroll` first.");
    }
}
