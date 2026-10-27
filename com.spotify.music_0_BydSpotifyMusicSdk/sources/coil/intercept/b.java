package coil.intercept;

import android.content.Context;
import java.util.List;
import p.ii0;
import p.j51;
import p.k51;
import p.oo1;
import p.q31;
import p.qd2;

/* JADX INFO: loaded from: classes.dex */
public final class b implements j51 {
    public final q31 a;
    public final List b;
    public final int c;
    public final q31 d;
    public final qd2 e;
    public final ii0 f;
    public final boolean g;

    public b(q31 q31Var, List list, int i, q31 q31Var2, qd2 qd2Var, ii0 ii0Var, boolean z) {
        this.a = q31Var;
        this.b = list;
        this.c = i;
        this.d = q31Var2;
        this.e = qd2Var;
        this.f = ii0Var;
        this.g = z;
    }

    public final void a(q31 q31Var, k51 k51Var) {
        Context context = q31Var.a;
        q31 q31Var2 = this.a;
        if (context != q31Var2.a) {
            throw new IllegalStateException(("Interceptor '" + k51Var + "' cannot modify the request's context.").toString());
        }
        if (q31Var.b == oo1.a) {
            throw new IllegalStateException(("Interceptor '" + k51Var + "' cannot set the request's data to null.").toString());
        }
        if (q31Var.c != q31Var2.c) {
            throw new IllegalStateException(("Interceptor '" + k51Var + "' cannot modify the request's target.").toString());
        }
        if (q31Var.A != q31Var2.A) {
            throw new IllegalStateException(("Interceptor '" + k51Var + "' cannot modify the request's lifecycle.").toString());
        }
        if (q31Var.B == q31Var2.B) {
            return;
        }
        throw new IllegalStateException(("Interceptor '" + k51Var + "' cannot modify the request's size resolver. Use `Interceptor.Chain.withSize` instead.").toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(p.q31 r14, p.c00 r15) {
        /*
            r13 = this;
            boolean r0 = r15 instanceof coil.intercept.RealInterceptorChain$proceed$1
            if (r0 == 0) goto L13
            r0 = r15
            coil.intercept.RealInterceptorChain$proceed$1 r0 = (coil.intercept.RealInterceptorChain$proceed$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            coil.intercept.RealInterceptorChain$proceed$1 r0 = new coil.intercept.RealInterceptorChain$proceed$1
            r0.<init>(r13, r15)
        L18:
            java.lang.Object r15 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r14 = r0.L$1
            p.k51 r14 = (p.k51) r14
            java.lang.Object r0 = r0.L$0
            coil.intercept.b r0 = (coil.intercept.b) r0
            kotlin.a.d(r15)
            goto L75
        L2f:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L37:
            kotlin.a.d(r15)
            java.util.List r15 = r13.b
            int r2 = r13.c
            if (r2 <= 0) goto L4b
            int r4 = r2 + (-1)
            java.lang.Object r4 = r15.get(r4)
            p.k51 r4 = (p.k51) r4
            r13.a(r14, r4)
        L4b:
            java.lang.Object r15 = r15.get(r2)
            p.k51 r15 = (p.k51) r15
            int r7 = r2 + 1
            p.qd2 r9 = r13.e
            coil.intercept.b r2 = new coil.intercept.b
            p.q31 r5 = r13.a
            java.util.List r6 = r13.b
            p.ii0 r10 = r13.f
            boolean r11 = r13.g
            r4 = r2
            r8 = r14
            r4.<init>(r5, r6, r7, r8, r9, r10, r11)
            r0.L$0 = r13
            r0.L$1 = r15
            r0.label = r3
            java.lang.Object r14 = r15.a(r2, r0)
            if (r14 != r1) goto L71
            return r1
        L71:
            r0 = r13
            r12 = r15
            r15 = r14
            r14 = r12
        L75:
            p.r31 r15 = (p.r31) r15
            p.q31 r1 = r15.a()
            r0.a(r1, r14)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.b.b(p.q31, p.c00):java.lang.Object");
    }
}
