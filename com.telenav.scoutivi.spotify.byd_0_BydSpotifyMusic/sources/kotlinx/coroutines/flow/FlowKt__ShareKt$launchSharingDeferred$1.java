package kotlinx.coroutines.flow;

import g5.m;
import g5.r;
import k5.d;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import kotlinx.coroutines.h0;
import kotlinx.coroutines.t;
import r5.p;

/* JADX INFO: compiled from: Share.kt */
/* JADX INFO: loaded from: classes.dex */
@f(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharingDeferred$1", f = "Share.kt", l = {415}, m = "invokeSuspend")
final class FlowKt__ShareKt$launchSharingDeferred$1 extends l implements p<h0, d<? super r>, Object> {
    final /* synthetic */ t $result;
    final /* synthetic */ Flow $upstream;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private h0 p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FlowKt__ShareKt$launchSharingDeferred$1(Flow flow, t tVar, d dVar) {
        super(2, dVar);
        this.$upstream = flow;
        this.$result = tVar;
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final d<r> create(Object obj, d<?> dVar) {
        FlowKt__ShareKt$launchSharingDeferred$1 flowKt__ShareKt$launchSharingDeferred$1 = new FlowKt__ShareKt$launchSharingDeferred$1(this.$upstream, this.$result, dVar);
        flowKt__ShareKt$launchSharingDeferred$1.p$ = (h0) obj;
        return flowKt__ShareKt$launchSharingDeferred$1;
    }

    @Override // r5.p
    public final Object invoke(h0 h0Var, d<? super r> dVar) {
        return ((FlowKt__ShareKt$launchSharingDeferred$1) create(h0Var, dVar)).invokeSuspend(r.f6563a);
    }

    @Override // kotlin.coroutines.jvm.internal.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objD = l5.d.d();
        int i7 = this.label;
        try {
            if (i7 == 0) {
                m.b(obj);
                final h0 h0Var = this.p$;
                final kotlin.jvm.internal.r rVar = new kotlin.jvm.internal.r();
                rVar.f7508e = null;
                Flow flow = this.$upstream;
                Object obj2 = new FlowCollector<T>() { // from class: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharingDeferred$1$invokeSuspend$$inlined$collect$1
                    /* JADX WARN: Type inference failed for: r3v1, types: [T, kotlinx.coroutines.flow.MutableStateFlow] */
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object obj3, d dVar) {
                        r rVar2;
                        kotlin.jvm.internal.r rVar3 = rVar;
                        MutableStateFlow mutableStateFlow = (MutableStateFlow) rVar3.f7508e;
                        if (mutableStateFlow != null) {
                            mutableStateFlow.setValue(obj3);
                            rVar2 = r.f6563a;
                        } else {
                            ?? r32 = (T) StateFlowKt.MutableStateFlow(obj3);
                            this.this$0.$result.K(FlowKt.asStateFlow(r32));
                            r rVar4 = r.f6563a;
                            rVar3.f7508e = r32;
                            rVar2 = rVar4;
                        }
                        return rVar2 == l5.d.d() ? rVar2 : r.f6563a;
                    }
                };
                this.L$0 = h0Var;
                this.L$1 = rVar;
                this.L$2 = flow;
                this.label = 1;
                if (flow.collect(obj2, this) == objD) {
                    return objD;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                m.b(obj);
            }
            return r.f6563a;
        } catch (Throwable th) {
            this.$result.G(th);
            throw th;
        }
    }
}
