package androidx.compose.foundation.gestures;

import A.C0036r0;
import A.C0038s0;
import A.C0040t0;
import A.EnumC0017h0;
import A.InterfaceC0012f;
import A.K0;
import A.r;
import C.k;
import g0.o;
import y.r0;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0038s0 f11449a = new C0038s0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0036r0 f11450b = new C0036r0();
    public static final C0040t0 c = new C0040t0();

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object a(A.S0 r11, long r12, s7.AbstractC2410c r14) throws java.lang.Throwable {
        /*
            boolean r0 = r14 instanceof A.C0042u0
            if (r0 == 0) goto L13
            r0 = r14
            A.u0 r0 = (A.C0042u0) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.o = r1
            goto L18
        L13:
            A.u0 r0 = new A.u0
            r0.<init>(r14)
        L18:
            java.lang.Object r14 = r0.f277n
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            A7.w r11 = r0.f276m
            A.S0 r12 = r0.l
            h8.AbstractC1386c.u(r14)
            r14 = r11
            r11 = r12
            goto L56
        L2d:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L35:
            h8.AbstractC1386c.u(r14)
            A7.w r14 = new A7.w
            r14.<init>()
            y.j0 r2 = y.j0.l
            A.w0 r10 = new A.w0
            r9 = 0
            r4 = r10
            r5 = r11
            r6 = r12
            r8 = r14
            r4.<init>(r5, r6, r8, r9)
            r0.l = r11
            r0.f276m = r14
            r0.o = r3
            java.lang.Object r12 = r11.e(r2, r10, r0)
            if (r12 != r1) goto L56
            goto L61
        L56:
            float r12 = r14.l
            long r11 = r11.g(r12)
            m0.c r1 = new m0.c
            r1.<init>(r11)
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.a.a(A.S0, long, s7.c):java.lang.Object");
    }

    public static final o b(o oVar, K0 k02, EnumC0017h0 enumC0017h0, r0 r0Var, boolean z6, boolean z10, r rVar, k kVar, InterfaceC0012f interfaceC0012f) {
        return oVar.k(new ScrollableElement(interfaceC0012f, rVar, enumC0017h0, k02, kVar, r0Var, z6, z10));
    }
}
