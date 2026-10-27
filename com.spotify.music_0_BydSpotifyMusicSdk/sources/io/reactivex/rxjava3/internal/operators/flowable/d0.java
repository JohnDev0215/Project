package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import java.util.Objects;
import p.az1;
import p.he1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final class d0 extends a {
    public final /* synthetic */ int k;
    public final io.reactivex.rxjava3.functions.o l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d0(Flowable flowable, io.reactivex.rxjava3.functions.o oVar, int i) {
        super(flowable);
        this.k = i;
        this.l = oVar;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        tg2 tg2Var = EmptySubscription.c;
        int i = this.k;
        Flowable flowable = this.e;
        io.reactivex.rxjava3.functions.o oVar = this.l;
        switch (i) {
            case 0:
                FlowableOnErrorNext$OnErrorNextSubscriber flowableOnErrorNext$OnErrorNextSubscriber = new FlowableOnErrorNext$OnErrorNextSubscriber(rg2Var, oVar);
                rg2Var.onSubscribe(flowableOnErrorNext$OnErrorNextSubscriber);
                flowable.subscribe((FlowableSubscriber) flowableOnErrorNext$OnErrorNextSubscriber);
                break;
            case 1:
                io.reactivex.rxjava3.subscribers.a aVar = new io.reactivex.rxjava3.subscribers.a(rg2Var);
                io.reactivex.rxjava3.internal.functions.k.c(8, "capacityHint");
                io.reactivex.rxjava3.processors.d dVar = new io.reactivex.rxjava3.processors.d();
                boolean z = dVar instanceof io.reactivex.rxjava3.processors.c;
                io.reactivex.rxjava3.processors.a cVar = dVar;
                if (!z) {
                    cVar = new io.reactivex.rxjava3.processors.c(dVar);
                }
                try {
                    Object objApply = oVar.apply(cVar);
                    Objects.requireNonNull(objApply, "handler returned a null Publisher");
                    az1 az1Var = (az1) objApply;
                    FlowableRepeatWhen$WhenReceiver flowableRepeatWhen$WhenReceiver = new FlowableRepeatWhen$WhenReceiver(flowable);
                    FlowableRepeatWhen$RepeatWhenSubscriber flowableRepeatWhen$RepeatWhenSubscriber = new FlowableRepeatWhen$RepeatWhenSubscriber(aVar, cVar, flowableRepeatWhen$WhenReceiver);
                    flowableRepeatWhen$WhenReceiver.subscriber = flowableRepeatWhen$RepeatWhenSubscriber;
                    rg2Var.onSubscribe(flowableRepeatWhen$RepeatWhenSubscriber);
                    az1Var.subscribe(flowableRepeatWhen$WhenReceiver);
                    flowableRepeatWhen$WhenReceiver.onNext(0);
                } catch (Throwable th) {
                    he1.R(th);
                    rg2Var.onSubscribe(tg2Var);
                    rg2Var.onError(th);
                    return;
                }
                break;
            case 2:
                io.reactivex.rxjava3.subscribers.a aVar2 = new io.reactivex.rxjava3.subscribers.a(rg2Var);
                io.reactivex.rxjava3.internal.functions.k.c(8, "capacityHint");
                io.reactivex.rxjava3.processors.d dVar2 = new io.reactivex.rxjava3.processors.d();
                boolean z2 = dVar2 instanceof io.reactivex.rxjava3.processors.c;
                io.reactivex.rxjava3.processors.a cVar2 = dVar2;
                if (!z2) {
                    cVar2 = new io.reactivex.rxjava3.processors.c(dVar2);
                }
                try {
                    Object objApply2 = oVar.apply(cVar2);
                    Objects.requireNonNull(objApply2, "handler returned a null Publisher");
                    az1 az1Var2 = (az1) objApply2;
                    FlowableRepeatWhen$WhenReceiver flowableRepeatWhen$WhenReceiver2 = new FlowableRepeatWhen$WhenReceiver(flowable);
                    FlowableRetryWhen$RetryWhenSubscriber flowableRetryWhen$RetryWhenSubscriber = new FlowableRetryWhen$RetryWhenSubscriber(aVar2, cVar2, flowableRepeatWhen$WhenReceiver2);
                    flowableRepeatWhen$WhenReceiver2.subscriber = flowableRetryWhen$RetryWhenSubscriber;
                    rg2Var.onSubscribe(flowableRetryWhen$RetryWhenSubscriber);
                    az1Var2.subscribe(flowableRepeatWhen$WhenReceiver2);
                    flowableRepeatWhen$WhenReceiver2.onNext(0);
                } catch (Throwable th2) {
                    he1.R(th2);
                    rg2Var.onSubscribe(tg2Var);
                    rg2Var.onError(th2);
                    return;
                }
                break;
            default:
                if (!(rg2Var instanceof io.reactivex.rxjava3.operators.a)) {
                    flowable.subscribe((FlowableSubscriber) new y(rg2Var, oVar));
                } else {
                    flowable.subscribe((FlowableSubscriber) new m((io.reactivex.rxjava3.operators.a) rg2Var, oVar, 1));
                }
                break;
        }
    }
}
