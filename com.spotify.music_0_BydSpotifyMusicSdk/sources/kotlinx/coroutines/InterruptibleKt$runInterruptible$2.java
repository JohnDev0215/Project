package kotlinx.coroutines;

import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.c00;
import p.e20;
import p.e60;
import p.fz0;
import p.lq2;
import p.py0;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "kotlinx.coroutines.InterruptibleKt$runInterruptible$2", f = "Interruptible.kt", l = {}, m = "invokeSuspend")
final class InterruptibleKt$runInterruptible$2 extends SuspendLambda implements fz0 {
    final /* synthetic */ py0 $block;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InterruptibleKt$runInterruptible$2(py0 py0Var, c00 c00Var) {
        super(2, c00Var);
        this.$block = py0Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        InterruptibleKt$runInterruptible$2 interruptibleKt$runInterruptible$2 = new InterruptibleKt$runInterruptible$2(this.$block, c00Var);
        interruptibleKt$runInterruptible$2.L$0 = obj;
        return interruptibleKt$runInterruptible$2;
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((InterruptibleKt$runInterruptible$2) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0045, code lost:
    
        return r0.invoke();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0048, code lost:
    
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0049, code lost:
    
        r1.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004c, code lost:
    
        throw r5;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object i(java.lang.Object r5) throws java.lang.Throwable {
        /*
            r4 = this;
            int r0 = r4.label
            if (r0 != 0) goto L59
            kotlin.a.d(r5)
            java.lang.Object r5 = r4.L$0
            p.e20 r5 = (p.e20) r5
            p.y10 r5 = r5.j()
            p.py0 r0 = r4.$block
            p.nk2 r1 = new p.nk2     // Catch: java.lang.InterruptedException -> L46
            p.p61 r5 = p.zu.h0(r5)     // Catch: java.lang.InterruptedException -> L46
            r1.<init>(r5)     // Catch: java.lang.InterruptedException -> L46
            kotlinx.coroutines.g r5 = (kotlinx.coroutines.g) r5     // Catch: java.lang.InterruptedException -> L46
            r2 = 1
            p.oa0 r5 = r5.M(r2, r2, r1)     // Catch: java.lang.InterruptedException -> L46
            r1.e = r5     // Catch: java.lang.InterruptedException -> L46
        L23:
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r5 = p.nk2.k     // Catch: java.lang.InterruptedException -> L46
            int r2 = r5.get(r1)     // Catch: java.lang.InterruptedException -> L46
            if (r2 == 0) goto L37
            r5 = 2
            if (r2 == r5) goto L3e
            r5 = 3
            if (r2 != r5) goto L32
            goto L3e
        L32:
            p.nk2.b(r2)     // Catch: java.lang.InterruptedException -> L46
            r5 = 0
            throw r5     // Catch: java.lang.InterruptedException -> L46
        L37:
            r3 = 0
            boolean r5 = r5.compareAndSet(r1, r2, r3)     // Catch: java.lang.InterruptedException -> L46
            if (r5 == 0) goto L23
        L3e:
            java.lang.Object r5 = r0.invoke()     // Catch: java.lang.Throwable -> L48
            r1.a()     // Catch: java.lang.InterruptedException -> L46
            return r5
        L46:
            r5 = move-exception
            goto L4d
        L48:
            r5 = move-exception
            r1.a()     // Catch: java.lang.InterruptedException -> L46
            throw r5     // Catch: java.lang.InterruptedException -> L46
        L4d:
            java.util.concurrent.CancellationException r0 = new java.util.concurrent.CancellationException
            java.lang.String r1 = "Blocking call was interrupted due to parent cancellation"
            r0.<init>(r1)
            java.lang.Throwable r5 = r0.initCause(r5)
            throw r5
        L59:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.InterruptibleKt$runInterruptible$2.i(java.lang.Object):java.lang.Object");
    }
}
