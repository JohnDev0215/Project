package kotlinx.coroutines.flow;

import g5.m;
import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import kotlin.jvm.internal.o;
import r5.p;

/* JADX INFO: compiled from: SharingStarted.kt */
/* JADX INFO: loaded from: classes.dex */
final class StartedLazily implements SharingStarted {

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.StartedLazily$command$1, reason: invalid class name */
    /* JADX INFO: compiled from: SharingStarted.kt */
    @f(c = "kotlinx.coroutines.flow.StartedLazily$command$1", f = "SharingStarted.kt", l = {212}, m = "invokeSuspend")
    static final class AnonymousClass1 extends l implements p<FlowCollector<? super SharingCommand>, d<? super r>, Object> {
        final /* synthetic */ StateFlow $subscriptionCount;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        private FlowCollector p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(StateFlow stateFlow, d dVar) {
            super(2, dVar);
            this.$subscriptionCount = stateFlow;
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final d<r> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$subscriptionCount, dVar);
            anonymousClass1.p$ = (FlowCollector) obj;
            return anonymousClass1;
        }

        @Override // r5.p
        public final Object invoke(FlowCollector<? super SharingCommand> flowCollector, d<? super r> dVar) {
            return ((AnonymousClass1) create(flowCollector, dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objD = l5.d.d();
            int i7 = this.label;
            if (i7 == 0) {
                m.b(obj);
                FlowCollector flowCollector = this.p$;
                o oVar = new o();
                oVar.f7505e = false;
                StateFlow stateFlow = this.$subscriptionCount;
                StartedLazily$command$1$invokeSuspend$$inlined$collect$1 startedLazily$command$1$invokeSuspend$$inlined$collect$1 = new StartedLazily$command$1$invokeSuspend$$inlined$collect$1(flowCollector, oVar);
                this.L$0 = flowCollector;
                this.L$1 = oVar;
                this.L$2 = stateFlow;
                this.label = 1;
                if (stateFlow.collect(startedLazily$command$1$invokeSuspend$$inlined$collect$1, this) == objD) {
                    return objD;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                m.b(obj);
            }
            return r.f6563a;
        }
    }

    @Override // kotlinx.coroutines.flow.SharingStarted
    public Flow<SharingCommand> command(StateFlow<Integer> stateFlow) {
        return FlowKt.flow(new AnonymousClass1(stateFlow, null));
    }

    public String toString() {
        return "SharingStarted.Lazily";
    }
}
