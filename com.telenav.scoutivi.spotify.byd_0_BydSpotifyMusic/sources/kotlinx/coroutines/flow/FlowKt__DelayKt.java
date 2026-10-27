package kotlinx.coroutines.flow;

import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.jvm.internal.m;
import kotlinx.coroutines.channels.a0;
import kotlinx.coroutines.channels.c0;
import kotlinx.coroutines.channels.y;
import kotlinx.coroutines.flow.internal.FlowCoroutineKt;
import kotlinx.coroutines.h0;
import kotlinx.coroutines.r0;
import r5.l;
import r5.p;
import r5.q;
import z5.a;

/* JADX INFO: compiled from: Delay.kt */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class FlowKt__DelayKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2, reason: invalid class name */
    /* JADX INFO: compiled from: Delay.kt */
    static final class AnonymousClass2<T> extends m implements l<T, Long> {
        final /* synthetic */ long $timeoutMillis;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(long j7) {
            super(1);
            this.$timeoutMillis = j7;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final long invoke2(T t6) {
            return this.$timeoutMillis;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // r5.l
        public /* bridge */ /* synthetic */ Long invoke(Object obj) {
            return Long.valueOf(invoke2(obj));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$3, reason: invalid class name */
    /* JADX INFO: compiled from: Delay.kt */
    static final class AnonymousClass3<T> extends m implements l<T, Long> {
        final /* synthetic */ l $timeout;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(l lVar) {
            super(1);
            this.$timeout = lVar;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final long invoke2(T t6) {
            return r0.c(((a) this.$timeout.invoke(t6)).unbox-impl());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // r5.l
        public /* bridge */ /* synthetic */ Long invoke(Object obj) {
            return Long.valueOf(invoke2(obj));
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__DelayKt$fixedPeriodTicker$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Delay.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$fixedPeriodTicker$3", f = "Delay.kt", l = {316, 318, 319}, m = "invokeSuspend")
    static final class C01843 extends kotlin.coroutines.jvm.internal.l implements p<a0<? super r>, d<? super r>, Object> {
        final /* synthetic */ long $delayMillis;
        final /* synthetic */ long $initialDelayMillis;
        Object L$0;
        int label;
        private a0 p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01843(long j7, long j8, d dVar) {
            super(2, dVar);
            this.$initialDelayMillis = j7;
            this.$delayMillis = j8;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final d<r> create(Object obj, d<?> dVar) {
            C01843 c01843 = new C01843(this.$initialDelayMillis, this.$delayMillis, dVar);
            c01843.p$ = (a0) obj;
            return c01843;
        }

        @Override // r5.p
        public final Object invoke(a0<? super r> a0Var, d<? super r> dVar) {
            return ((C01843) create(a0Var, dVar)).invokeSuspend(r.f6563a);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x004d A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:20:0x005a A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0058 -> B:15:0x003d). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = l5.b.d()
                int r1 = r7.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L2a
                if (r1 == r4) goto L11
                if (r1 == r3) goto L21
                if (r1 != r2) goto L19
            L11:
                java.lang.Object r1 = r7.L$0
                kotlinx.coroutines.channels.a0 r1 = (kotlinx.coroutines.channels.a0) r1
                g5.m.b(r8)
                goto L3c
            L19:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L21:
                java.lang.Object r1 = r7.L$0
                kotlinx.coroutines.channels.a0 r1 = (kotlinx.coroutines.channels.a0) r1
                g5.m.b(r8)
                r8 = r7
                goto L4e
            L2a:
                g5.m.b(r8)
                kotlinx.coroutines.channels.a0 r1 = r7.p$
                long r5 = r7.$initialDelayMillis
                r7.L$0 = r1
                r7.label = r4
                java.lang.Object r8 = kotlinx.coroutines.r0.a(r5, r7)
                if (r8 != r0) goto L3c
                return r0
            L3c:
                r8 = r7
            L3d:
                kotlinx.coroutines.channels.g0 r4 = r1.getChannel()
                g5.r r5 = g5.r.f6563a
                r8.L$0 = r1
                r8.label = r3
                java.lang.Object r4 = r4.send(r5, r8)
                if (r4 != r0) goto L4e
                return r0
            L4e:
                long r4 = r8.$delayMillis
                r8.L$0 = r1
                r8.label = r2
                java.lang.Object r4 = kotlinx.coroutines.r0.a(r4, r8)
                if (r4 != r0) goto L3d
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt.C01843.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Delay.kt */
    @f(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2", f = "Delay.kt", l = {355}, m = "invokeSuspend")
    static final class C01852<T> extends kotlin.coroutines.jvm.internal.l implements q<h0, FlowCollector<? super T>, d<? super r>, Object> {
        final /* synthetic */ long $periodMillis;
        final /* synthetic */ Flow $this_sample;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        private h0 p$;
        private FlowCollector p$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01852(Flow flow, long j7, d dVar) {
            super(3, dVar);
            this.$this_sample = flow;
            this.$periodMillis = j7;
        }

        public final d<r> create(h0 h0Var, FlowCollector<? super T> flowCollector, d<? super r> dVar) {
            C01852 c01852 = new C01852(this.$this_sample, this.$periodMillis, dVar);
            c01852.p$ = h0Var;
            c01852.p$0 = flowCollector;
            return c01852;
        }

        @Override // r5.q
        public final Object invoke(h0 h0Var, Object obj, d<? super r> dVar) {
            return ((C01852) create(h0Var, (FlowCollector) obj, dVar)).invokeSuspend(r.f6563a);
        }

        /* JADX WARN: Can't wrap try/catch for region: R(11:11|(1:29)|12|13|31|14|21|(2:23|(1:25))(1:(0))|26|9|(2:27|28)(0)) */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x00af, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x00b3, code lost:
        
            r1.U(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x00c6, code lost:
        
            if (r0 != r2) goto L26;
         */
        /* JADX WARN: Removed duplicated region for block: B:11:0x0071  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00c0  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00c3  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x00c5 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00ca  */
        @Override // kotlin.coroutines.jvm.internal.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r17) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 205
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt.C01852.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Flow<T> debounce(Flow<? extends T> flow, long j7) {
        if (j7 >= 0) {
            return j7 == 0 ? flow : debounceInternal$FlowKt__DelayKt(flow, new AnonymousClass2(j7));
        }
        throw new IllegalArgumentException("Debounce timeout should not be negative".toString());
    }

    /* JADX INFO: renamed from: debounce-8GFy2Ro, reason: not valid java name */
    public static final <T> Flow<T> m6debounce8GFy2Ro(Flow<? extends T> flow, double d7) {
        return FlowKt.debounce(flow, r0.c(d7));
    }

    public static final <T> Flow<T> debounceDuration(Flow<? extends T> flow, l<? super T, a> lVar) {
        return debounceInternal$FlowKt__DelayKt(flow, new AnonymousClass3(lVar));
    }

    private static final <T> Flow<T> debounceInternal$FlowKt__DelayKt(Flow<? extends T> flow, l<? super T, Long> lVar) {
        return FlowCoroutineKt.scopedFlow(new FlowKt__DelayKt$debounceInternal$1(flow, lVar, null));
    }

    public static final c0<r> fixedPeriodTicker(h0 h0Var, long j7, long j8) {
        if (!(j7 >= 0)) {
            throw new IllegalArgumentException(("Expected non-negative delay, but has " + j7 + " ms").toString());
        }
        if (j8 >= 0) {
            return y.f(h0Var, null, 0, new C01843(j8, j7, null), 1, null);
        }
        throw new IllegalArgumentException(("Expected non-negative initial delay, but has " + j8 + " ms").toString());
    }

    public static /* synthetic */ c0 fixedPeriodTicker$default(h0 h0Var, long j7, long j8, int i7, Object obj) {
        if ((i7 & 2) != 0) {
            j8 = j7;
        }
        return FlowKt.fixedPeriodTicker(h0Var, j7, j8);
    }

    public static final <T> Flow<T> sample(Flow<? extends T> flow, long j7) {
        if (j7 > 0) {
            return FlowCoroutineKt.scopedFlow(new C01852(flow, j7, null));
        }
        throw new IllegalArgumentException("Sample period should be positive".toString());
    }

    /* JADX INFO: renamed from: sample-8GFy2Ro, reason: not valid java name */
    public static final <T> Flow<T> m7sample8GFy2Ro(Flow<? extends T> flow, double d7) {
        return FlowKt.sample(flow, r0.c(d7));
    }

    public static final <T> Flow<T> debounce(Flow<? extends T> flow, l<? super T, Long> lVar) {
        return debounceInternal$FlowKt__DelayKt(flow, lVar);
    }
}
