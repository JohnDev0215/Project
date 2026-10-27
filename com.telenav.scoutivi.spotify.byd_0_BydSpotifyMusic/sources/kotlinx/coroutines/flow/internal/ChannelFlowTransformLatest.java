package kotlinx.coroutines.flow.internal;

import g5.r;
import k5.d;
import k5.g;
import k5.h;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import kotlinx.coroutines.channels.k;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.h0;
import r5.p;
import r5.q;

/* JADX INFO: compiled from: Merge.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ChannelFlowTransformLatest<T, R> extends ChannelFlowOperator<T, R> {
    private final q<FlowCollector<? super R>, T, d<? super r>, Object> transform;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3, reason: invalid class name */
    /* JADX INFO: compiled from: Merge.kt */
    @f(c = "kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3", f = "Merge.kt", l = {101}, m = "invokeSuspend")
    static final class AnonymousClass3 extends l implements p<h0, d<? super r>, Object> {
        final /* synthetic */ FlowCollector $collector;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        private h0 p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(FlowCollector flowCollector, d dVar) {
            super(2, dVar);
            this.$collector = flowCollector;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final d<r> create(Object obj, d<?> dVar) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$collector, dVar);
            anonymousClass3.p$ = (h0) obj;
            return anonymousClass3;
        }

        @Override // r5.p
        public final Object invoke(h0 h0Var, d<? super r> dVar) {
            return ((AnonymousClass3) create(h0Var, dVar)).invokeSuspend(r.f6563a);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type k5.d to kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3 for r5v1 'this'  k5.d
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        @Override // kotlin.coroutines.jvm.internal.a
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = l5.b.d()
                int r1 = r5.label
                r2 = 1
                if (r1 == 0) goto L23
                if (r1 != r2) goto L1b
                java.lang.Object r0 = r5.L$2
                kotlinx.coroutines.flow.Flow r0 = (kotlinx.coroutines.flow.Flow) r0
                java.lang.Object r0 = r5.L$1
                kotlin.jvm.internal.r r0 = (kotlin.jvm.internal.r) r0
                java.lang.Object r0 = r5.L$0
                kotlinx.coroutines.h0 r0 = (kotlinx.coroutines.h0) r0
                g5.m.b(r6)
                goto L48
            L1b:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L23:
                g5.m.b(r6)
                kotlinx.coroutines.h0 r6 = r5.p$
                kotlin.jvm.internal.r r1 = new kotlin.jvm.internal.r
                r1.<init>()
                r3 = 0
                r1.f7508e = r3
                kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest r3 = kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest.this
                kotlinx.coroutines.flow.Flow<S> r3 = r3.flow
                kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1 r4 = new kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1
                r4.<init>(r5, r6, r1)
                r5.L$0 = r6
                r5.L$1 = r1
                r5.L$2 = r3
                r5.label = r2
                java.lang.Object r6 = r3.collect(r4, r5)
                if (r6 != r0) goto L48
                return r0
            L48:
                g5.r r6 = g5.r.f6563a
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public /* synthetic */ ChannelFlowTransformLatest(q qVar, Flow flow, g gVar, int i7, k kVar, int i8, kotlin.jvm.internal.g gVar2) {
        this(qVar, flow, (i8 & 4) != 0 ? h.f7276e : gVar, (i8 & 8) != 0 ? -2 : i7, (i8 & 16) != 0 ? k.SUSPEND : kVar);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected ChannelFlow<R> create(g gVar, int i7, k kVar) {
        return new ChannelFlowTransformLatest(this.transform, this.flow, gVar, i7, kVar);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlowOperator
    protected Object flowCollect(FlowCollector<? super R> flowCollector, d<? super r> dVar) {
        Object objFlowScope = FlowCoroutineKt.flowScope(new AnonymousClass3(flowCollector, null), dVar);
        return objFlowScope == l5.d.d() ? objFlowScope : r.f6563a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ChannelFlowTransformLatest(q<? super FlowCollector<? super R>, ? super T, ? super d<? super r>, ? extends Object> qVar, Flow<? extends T> flow, g gVar, int i7, k kVar) {
        super(flow, gVar, i7, kVar);
        this.transform = qVar;
    }
}
