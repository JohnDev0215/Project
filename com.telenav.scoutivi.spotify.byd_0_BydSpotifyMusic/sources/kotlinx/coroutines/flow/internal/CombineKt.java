package kotlinx.coroutines.flow.internal;

import g5.m;
import g5.r;
import java.util.concurrent.atomic.AtomicInteger;
import k5.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import kotlinx.coroutines.channels.g0;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.h0;
import r5.a;
import r5.p;
import r5.q;

/* JADX INFO: compiled from: Combine.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CombineKt {

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2, reason: invalid class name */
    /* JADX INFO: compiled from: Combine.kt */
    @f(c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2", f = "Combine.kt", l = {57, 79, 82}, m = "invokeSuspend")
    static final class AnonymousClass2 extends l implements p<h0, d<? super r>, Object> {
        final /* synthetic */ a $arrayFactory;
        final /* synthetic */ Flow[] $flows;
        final /* synthetic */ FlowCollector $this_combineInternal;
        final /* synthetic */ q $transform;
        byte B$0;
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        private h0 p$;

        /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: Combine.kt */
        @f(c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2$1", f = "Combine.kt", l = {145}, m = "invokeSuspend")
        static final class AnonymousClass1 extends l implements p<h0, d<? super r>, Object> {
            final /* synthetic */ int $i;
            final /* synthetic */ AtomicInteger $nonClosed;
            final /* synthetic */ kotlinx.coroutines.channels.l $resultChannel;
            Object L$0;
            Object L$1;
            int label;
            private h0 p$;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(int i7, kotlinx.coroutines.channels.l lVar, AtomicInteger atomicInteger, d dVar) {
                super(2, dVar);
                this.$i = i7;
                this.$resultChannel = lVar;
                this.$nonClosed = atomicInteger;
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final d<r> create(Object obj, d<?> dVar) {
                AnonymousClass1 anonymousClass1 = AnonymousClass2.this.new AnonymousClass1(this.$i, this.$resultChannel, this.$nonClosed, dVar);
                anonymousClass1.p$ = (h0) obj;
                return anonymousClass1;
            }

            @Override // r5.p
            public final Object invoke(h0 h0Var, d<? super r> dVar) {
                return ((AnonymousClass1) create(h0Var, dVar)).invokeSuspend(r.f6563a);
            }

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                AtomicInteger atomicInteger;
                Object objD = l5.d.d();
                int i7 = this.label;
                try {
                    if (i7 == 0) {
                        m.b(obj);
                        h0 h0Var = this.p$;
                        Flow flow = AnonymousClass2.this.$flows[this.$i];
                        CombineKt$combineInternal$2$1$invokeSuspend$$inlined$collect$1 combineKt$combineInternal$2$1$invokeSuspend$$inlined$collect$1 = new CombineKt$combineInternal$2$1$invokeSuspend$$inlined$collect$1(this);
                        this.L$0 = h0Var;
                        this.L$1 = flow;
                        this.label = 1;
                        if (flow.collect(combineKt$combineInternal$2$1$invokeSuspend$$inlined$collect$1, this) == objD) {
                            return objD;
                        }
                    } else {
                        if (i7 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        m.b(obj);
                    }
                    if (atomicInteger.decrementAndGet() == 0) {
                        g0.a.a(this.$resultChannel, null, 1, null);
                    }
                    return r.f6563a;
                } finally {
                    if (this.$nonClosed.decrementAndGet() == 0) {
                        g0.a.a(this.$resultChannel, null, 1, null);
                    }
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(FlowCollector flowCollector, Flow[] flowArr, a aVar, q qVar, d dVar) {
            super(2, dVar);
            this.$this_combineInternal = flowCollector;
            this.$flows = flowArr;
            this.$arrayFactory = aVar;
            this.$transform = qVar;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final d<r> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_combineInternal, this.$flows, this.$arrayFactory, this.$transform, dVar);
            anonymousClass2.p$ = (h0) obj;
            return anonymousClass2;
        }

        @Override // r5.p
        public final Object invoke(h0 h0Var, d<? super r> dVar) {
            return ((AnonymousClass2) create(h0Var, dVar)).invokeSuspend(r.f6563a);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:22:0x011a A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:23:0x011b  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x012c A[LOOP:0: B:26:0x012c->B:34:0x014d, LOOP_START, PHI: r2 r5
  0x012c: PHI (r2v5 int) = (r2v4 int), (r2v6 int) binds: [B:25:0x012a, B:34:0x014d] A[DONT_GENERATE, DONT_INLINE]
  0x012c: PHI (r5v6 h5.c0) = (r5v5 h5.c0), (r5v12 h5.c0) binds: [B:25:0x012a, B:34:0x014d] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:49:0x01ca  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x01b8 -> B:46:0x01bb). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x01c3 -> B:46:0x01bb). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r25) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 461
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.CombineKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final <R, T> Object combineInternal(FlowCollector<? super R> flowCollector, Flow<? extends T>[] flowArr, a<T[]> aVar, q<? super FlowCollector<? super R>, ? super T[], ? super d<? super r>, ? extends Object> qVar, d<? super r> dVar) {
        Object objFlowScope = FlowCoroutineKt.flowScope(new AnonymousClass2(flowCollector, flowArr, aVar, qVar, null), dVar);
        return objFlowScope == l5.d.d() ? objFlowScope : r.f6563a;
    }

    public static final <T1, T2, R> Flow<R> zipImpl(Flow<? extends T1> flow, Flow<? extends T2> flow2, q<? super T1, ? super T2, ? super d<? super R>, ? extends Object> qVar) {
        return new CombineKt$zipImpl$$inlined$unsafeFlow$1(flow2, flow, qVar);
    }
}
