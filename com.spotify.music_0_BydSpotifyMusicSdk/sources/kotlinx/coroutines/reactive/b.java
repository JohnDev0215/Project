package kotlinx.coroutines.reactive;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import p.a1;
import p.c00;
import p.dv0;
import p.lq2;
import p.rg2;
import p.tg2;
import p.y10;

/* JADX INFO: loaded from: classes.dex */
public final class b extends a1 implements tg2 {
    public static final AtomicLongFieldUpdater n = AtomicLongFieldUpdater.newUpdater(b.class, "requested");
    public static final AtomicReferenceFieldUpdater o = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "producer");
    private volatile boolean cancellationRequested;
    public final dv0 l;
    public final rg2 m;
    private volatile Object producer;
    private volatile long requested;

    public b(dv0 dv0Var, rg2 rg2Var, y10 y10Var) {
        super(y10Var, false, true);
        this.l = dv0Var;
        this.m = rg2Var;
        this.producer = new a(this.k, this);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object b0(kotlinx.coroutines.reactive.b r5, p.c00 r6) {
        /*
            r5.getClass()
            boolean r0 = r6 instanceof kotlinx.coroutines.reactive.FlowSubscription$flowProcessing$1
            if (r0 == 0) goto L16
            r0 = r6
            kotlinx.coroutines.reactive.FlowSubscription$flowProcessing$1 r0 = (kotlinx.coroutines.reactive.FlowSubscription$flowProcessing$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            kotlinx.coroutines.reactive.FlowSubscription$flowProcessing$1 r0 = new kotlinx.coroutines.reactive.FlowSubscription$flowProcessing$1
            r0.<init>(r5, r6)
        L1b:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            p.lq2 r3 = p.lq2.a
            r4 = 1
            if (r2 == 0) goto L3a
            if (r2 != r4) goto L32
            java.lang.Object r5 = r0.L$0
            kotlinx.coroutines.reactive.b r5 = (kotlinx.coroutines.reactive.b) r5
            kotlin.a.d(r6)     // Catch: java.lang.Throwable -> L30
            goto L54
        L30:
            r6 = move-exception
            goto L62
        L32:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3a:
            kotlin.a.d(r6)
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L30
            r0.label = r4     // Catch: java.lang.Throwable -> L30
            p.jv0 r6 = new p.jv0     // Catch: java.lang.Throwable -> L30
            r2 = 2
            r6.<init>(r2, r5)     // Catch: java.lang.Throwable -> L30
            p.dv0 r2 = r5.l     // Catch: java.lang.Throwable -> L30
            java.lang.Object r6 = r2.c(r6, r0)     // Catch: java.lang.Throwable -> L30
            if (r6 != r1) goto L50
            goto L51
        L50:
            r6 = r3
        L51:
            if (r6 != r1) goto L54
            goto L82
        L54:
            p.rg2 r6 = r5.m     // Catch: java.lang.Throwable -> L5a
            r6.onComplete()     // Catch: java.lang.Throwable -> L5a
            goto L60
        L5a:
            r6 = move-exception
            p.y10 r5 = r5.k
            p.ux2.m(r5, r6)
        L60:
            r1 = r3
            goto L82
        L62:
            boolean r0 = r5.cancellationRequested
            if (r0 == 0) goto L72
            boolean r0 = r5.b()
            if (r0 != 0) goto L72
            java.util.concurrent.CancellationException r0 = r5.B()
            if (r6 == r0) goto L60
        L72:
            p.rg2 r0 = r5.m     // Catch: java.lang.Throwable -> L78
            r0.onError(r6)     // Catch: java.lang.Throwable -> L78
            goto L60
        L78:
            r0 = move-exception
            kotlin.a.a(r6, r0)
            p.y10 r5 = r5.k
            p.ux2.m(r5, r6)
            goto L60
        L82:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.reactive.b.b0(kotlinx.coroutines.reactive.b, p.c00):java.lang.Object");
    }

    @Override // p.tg2
    public final void a(long j) {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        long j2;
        long j3;
        c00 c00Var;
        if (j <= 0) {
            return;
        }
        do {
            atomicLongFieldUpdater = n;
            j2 = atomicLongFieldUpdater.get(this);
            j3 = j2 + j;
            if (j3 <= 0) {
                j3 = Long.MAX_VALUE;
            }
        } while (!atomicLongFieldUpdater.compareAndSet(this, j2, j3));
        if (j2 <= 0) {
            do {
                c00Var = (c00) o.getAndSet(this, null);
            } while (c00Var == null);
            c00Var.k(lq2.a);
        }
    }

    @Override // p.tg2
    public final void cancel() {
        this.cancellationRequested = true;
        i(null);
    }
}
