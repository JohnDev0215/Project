package coil.util;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r0v6, types: [T, coil.util.-Lifecycles$awaitStarted$2$1, p.r91] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object a(p.l91 r6, p.c00 r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof coil.util.Lifecycles$awaitStarted$1
            if (r0 == 0) goto L13
            r0 = r7
            coil.util.-Lifecycles$awaitStarted$1 r0 = (coil.util.Lifecycles$awaitStarted$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            coil.util.-Lifecycles$awaitStarted$1 r0 = new coil.util.-Lifecycles$awaitStarted$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            p.lq2 r3 = p.lq2.a
            r4 = 1
            if (r2 == 0) goto L3b
            if (r2 != r4) goto L33
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.internal.Ref$ObjectRef r6 = (kotlin.jvm.internal.Ref$ObjectRef) r6
            java.lang.Object r0 = r0.L$0
            p.l91 r0 = (p.l91) r0
            kotlin.a.d(r7)     // Catch: java.lang.Throwable -> L31
            goto L75
        L31:
            r7 = move-exception
            goto L84
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3b:
            kotlin.a.d(r7)
            androidx.lifecycle.Lifecycle$State r7 = r6.b()
            androidx.lifecycle.Lifecycle$State r2 = androidx.lifecycle.Lifecycle$State.l
            int r7 = r7.compareTo(r2)
            if (r7 < 0) goto L4b
            return r3
        L4b:
            kotlin.jvm.internal.Ref$ObjectRef r7 = new kotlin.jvm.internal.Ref$ObjectRef
            r7.<init>()
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L7f
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L7f
            r0.label = r4     // Catch: java.lang.Throwable -> L7f
            p.zp r2 = new p.zp     // Catch: java.lang.Throwable -> L7f
            p.c00 r0 = p.vr2.k(r0)     // Catch: java.lang.Throwable -> L7f
            r2.<init>(r4, r0)     // Catch: java.lang.Throwable -> L7f
            r2.r()     // Catch: java.lang.Throwable -> L7f
            coil.util.-Lifecycles$awaitStarted$2$1 r0 = new coil.util.-Lifecycles$awaitStarted$2$1     // Catch: java.lang.Throwable -> L7f
            r0.<init>()     // Catch: java.lang.Throwable -> L7f
            r7.element = r0     // Catch: java.lang.Throwable -> L7f
            r6.a(r0)     // Catch: java.lang.Throwable -> L7f
            java.lang.Object r0 = r2.q()     // Catch: java.lang.Throwable -> L7f
            if (r0 != r1) goto L73
            return r1
        L73:
            r0 = r6
            r6 = r7
        L75:
            T r6 = r6.element
            p.r91 r6 = (p.r91) r6
            if (r6 == 0) goto L7e
            r0.c(r6)
        L7e:
            return r3
        L7f:
            r0 = move-exception
            r5 = r0
            r0 = r6
            r6 = r7
            r7 = r5
        L84:
            T r6 = r6.element
            p.r91 r6 = (p.r91) r6
            if (r6 == 0) goto L8d
            r0.c(r6)
        L8d:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.util.a.a(p.l91, p.c00):java.lang.Object");
    }
}
