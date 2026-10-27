package kotlinx.coroutines.channels;

import java.util.concurrent.locks.LockSupport;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.CoroutineStart;
import p.cr;
import p.dr;
import p.e41;
import p.fm;
import p.fz0;
import p.ha0;
import p.hv;
import p.kk2;
import p.lq2;
import p.pi0;
import p.qo;
import p.rx1;
import p.s70;
import p.sx1;
import p.y10;
import p.y61;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class b {
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object a(p.sx1 r4, p.py0 r5, p.c00 r6) {
        /*
            boolean r0 = r6 instanceof kotlinx.coroutines.channels.ProduceKt$awaitClose$1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.channels.ProduceKt$awaitClose$1 r0 = (kotlinx.coroutines.channels.ProduceKt$awaitClose$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.channels.ProduceKt$awaitClose$1 r0 = new kotlinx.coroutines.channels.ProduceKt$awaitClose$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r4 = r0.L$1
            r5 = r4
            p.py0 r5 = (p.py0) r5
            java.lang.Object r4 = r0.L$0
            p.sx1 r4 = (p.sx1) r4
            kotlin.a.d(r6)     // Catch: java.lang.Throwable -> L30
            goto L6c
        L30:
            r4 = move-exception
            goto L72
        L32:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3a:
            kotlin.a.d(r6)
            p.y10 r6 = r0.getContext()
            p.qo r2 = p.qo.o
            p.w10 r6 = r6.E0(r2)
            if (r6 != r4) goto L76
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L30
            r0.L$1 = r5     // Catch: java.lang.Throwable -> L30
            r0.label = r3     // Catch: java.lang.Throwable -> L30
            p.zp r6 = new p.zp     // Catch: java.lang.Throwable -> L30
            p.c00 r0 = p.vr2.k(r0)     // Catch: java.lang.Throwable -> L30
            r6.<init>(r3, r0)     // Catch: java.lang.Throwable -> L30
            r6.r()     // Catch: java.lang.Throwable -> L30
            kotlinx.coroutines.channels.ProduceKt$awaitClose$4$1 r0 = new kotlinx.coroutines.channels.ProduceKt$awaitClose$4$1     // Catch: java.lang.Throwable -> L30
            r0.<init>(r6)     // Catch: java.lang.Throwable -> L30
            p.rx1 r4 = (p.rx1) r4     // Catch: java.lang.Throwable -> L30
            r4.d(r0)     // Catch: java.lang.Throwable -> L30
            java.lang.Object r4 = r6.q()     // Catch: java.lang.Throwable -> L30
            if (r4 != r1) goto L6c
            return r1
        L6c:
            r5.invoke()
            p.lq2 r4 = p.lq2.a
            return r4
        L72:
            r5.invoke()
            throw r4
        L76:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "awaitClose() can only be invoked from the producer context"
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.b.a(p.sx1, p.py0, p.c00):java.lang.Object");
    }

    public static final Object b(sx1 sx1Var, Object obj) throws Throwable {
        Object objN = ((rx1) sx1Var).l.n(obj);
        if (!(objN instanceof cr)) {
            return lq2.a;
        }
        fz0 channelsKt__ChannelsKt$trySendBlocking$2 = new ChannelsKt__ChannelsKt$trySendBlocking$2(sx1Var, obj, null);
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.c;
        Thread threadCurrentThread = Thread.currentThread();
        qo qoVar = qo.l;
        emptyCoroutineContext.getClass();
        pi0 pi0VarA = kk2.a();
        y10 y10VarA = kotlinx.coroutines.a.a(emptyCoroutineContext, pi0VarA, true);
        s70 s70Var = ha0.a;
        if (y10VarA != s70Var && y10VarA.E0(qoVar) == null) {
            y10VarA = y10VarA.o0(s70Var);
        }
        fm fmVar = new fm(y10VarA, threadCurrentThread, pi0VarA);
        fmVar.a0(CoroutineStart.c, fmVar, channelsKt__ChannelsKt$trySendBlocking$2);
        pi0 pi0Var = fmVar.m;
        if (pi0Var != null) {
            int i = pi0.n;
            pi0Var.N0(false);
        }
        while (!Thread.interrupted()) {
            try {
                long jP0 = pi0Var != null ? pi0Var.P0() : Long.MAX_VALUE;
                if (!(fmVar.I() instanceof e41)) {
                    if (pi0Var != null) {
                        int i2 = pi0.n;
                        pi0Var.K0(false);
                    }
                    Object objA = y61.a(fmVar.I());
                    hv hvVar = objA instanceof hv ? (hv) objA : null;
                    if (hvVar == null) {
                        return ((dr) objA).a;
                    }
                    throw hvVar.a;
                }
                LockSupport.parkNanos(fmVar, jP0);
            } catch (Throwable th) {
                if (pi0Var != null) {
                    int i3 = pi0.n;
                    pi0Var.K0(false);
                }
                throw th;
            }
        }
        InterruptedException interruptedException = new InterruptedException();
        fmVar.t(interruptedException);
        throw interruptedException;
    }
}
