package androidx.paging;

import com.amazon.weblab.mobile.experimental.PlatformWeblabs;
import com.visualon.OSMPUtils.voOSType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* JADX INFO: Add missing generic type declarations: [T1, T2] */
/* JADX INFO: compiled from: FlowExt.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\b\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u00022\u0006\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00020\u0005H\u008a@"}, d2 = {PlatformWeblabs.T1, PlatformWeblabs.T2, "R", "t1", "t2", "Landroidx/paging/CombineSource;", "updateFrom", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
@DebugMetadata(c = "androidx.paging.FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1", f = "FlowExt.kt", i = {}, l = {voOSType.VOOSMP_PID_ANALYTICS_EXPORT, voOSType.VOOSMP_PID_ANALYTICS_EXPORT}, m = "invokeSuspend", n = {}, s = {})
public final class FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1<T1, T2> extends SuspendLambda implements Function4<T1, T2, CombineSource, Continuation<? super Unit>, Object> {
    final /* synthetic */ SimpleProducerScope<R> $$this$simpleChannelFlow;
    final /* synthetic */ Function4<T1, T2, CombineSource, Continuation<? super R>, Object> $transform;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1(SimpleProducerScope<R> simpleProducerScope, Function4<? super T1, ? super T2, ? super CombineSource, ? super Continuation<? super R>, ? extends Object> function4, Continuation<? super FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1> continuation) {
        super(4, continuation);
        this.$$this$simpleChannelFlow = simpleProducerScope;
        this.$transform = function4;
    }

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(T1 t1, T2 t2, CombineSource combineSource, Continuation<? super Unit> continuation) {
        FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1 flowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1 = new FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1(this.$$this$simpleChannelFlow, this.$transform, continuation);
        flowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1.L$0 = t1;
        flowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1.L$1 = t2;
        flowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1.L$2 = combineSource;
        return flowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to androidx.paging.FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1<T1, T2> for r8v1 'this'  java.lang.Object
        	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
        	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
        	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 0
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L23
            if (r1 == r4) goto L1b
            if (r1 != r3) goto L13
            kotlin.ResultKt.throwOnFailure(r9)
            goto L4b
        L13:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L1b:
            java.lang.Object r1 = r8.L$0
            androidx.paging.SimpleProducerScope r1 = (androidx.paging.SimpleProducerScope) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L40
        L23:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Object r9 = r8.L$0
            java.lang.Object r1 = r8.L$1
            java.lang.Object r5 = r8.L$2
            androidx.paging.CombineSource r5 = (androidx.paging.CombineSource) r5
            androidx.paging.SimpleProducerScope<R> r6 = r8.$$this$simpleChannelFlow
            kotlin.jvm.functions.Function4<T1, T2, androidx.paging.CombineSource, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r7 = r8.$transform
            r8.L$0 = r6
            r8.L$1 = r2
            r8.label = r4
            java.lang.Object r9 = r7.invoke(r9, r1, r5, r8)
            if (r9 != r0) goto L3f
            return r0
        L3f:
            r1 = r6
        L40:
            r8.L$0 = r2
            r8.label = r3
            java.lang.Object r9 = r1.send(r9, r8)
            if (r9 != r0) goto L4b
            return r0
        L4b:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to androidx.paging.FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1<T1, T2> for r4v2 'this'  java.lang.Object
        	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
        	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
        	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    public final java.lang.Object invokeSuspend$$forInline(java.lang.Object r5) {
        /*
            r4 = this;
            java.lang.Object r5 = r4.L$0
            java.lang.Object r0 = r4.L$1
            java.lang.Object r1 = r4.L$2
            androidx.paging.CombineSource r1 = (androidx.paging.CombineSource) r1
            androidx.paging.SimpleProducerScope<R> r2 = r4.$$this$simpleChannelFlow
            kotlin.jvm.functions.Function4<T1, T2, androidx.paging.CombineSource, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r3 = r4.$transform
            java.lang.Object r5 = r3.invoke(r5, r0, r1, r4)
            r0 = 0
            kotlin.jvm.internal.InlineMarker.mark(r0)
            r2.send(r5, r4)
            r5 = 1
            kotlin.jvm.internal.InlineMarker.mark(r5)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.FlowExtKt$combineWithoutBatching$2$unbatchedFlowCombiner$1.invokeSuspend$$forInline(java.lang.Object):java.lang.Object");
    }
}
