package com.byd.overseaappstore.module.ability.util.download.task;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u0002H\u008a@¨\u0006\u0004"}, d2 = {ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "", "kotlinx/coroutines/flow/FlowKt__EmittersKt$transform$1", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskPauseToResume$cancel$1$invokeSuspend$$inlined$transform$1", f = "DownloadTaskPauseToResume.kt", i = {}, l = {40}, m = "invokeSuspend", n = {}, s = {})
public final class DownloadTaskPauseToResume$cancel$1$invokeSuspend$$inlined$transform$1 extends SuspendLambda implements Function2<FlowCollector<? super Integer>, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Flow $this_transform;
    private /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ DownloadTaskPauseToResume this$0;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskPauseToResume$cancel$1$invokeSuspend$$inlined$transform$1$1, reason: invalid class name */
    public static final class AnonymousClass1<T> implements FlowCollector, SuspendFunction {
        public final /* synthetic */ FlowCollector<Integer> a;
        public final /* synthetic */ DownloadTaskPauseToResume b;

        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskPauseToResume$cancel$1$invokeSuspend$$inlined$transform$1$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskPauseToResume$cancel$1$invokeSuspend$$inlined$transform$1$1", f = "DownloadTaskPauseToResume.kt", i = {0, 0, 0, 0}, l = {230, 239}, m = "emit", n = {"this", "$this$invokeSuspend_u24lambda_u2d0", "percentCount", "state"}, s = {"L$0", "L$1", "I$0", "I$1"})
        public static final class C00211 extends ContinuationImpl {
            public int I$0;
            public int I$1;
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public C00211(Continuation continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass1.this.emit(null, this);
            }
        }

        public AnonymousClass1(FlowCollector flowCollector, DownloadTaskPauseToResume downloadTaskPauseToResume) {
            this.b = downloadTaskPauseToResume;
            this.a = flowCollector;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object emit(T r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
            /*
                Method dump skipped, instruction units count: 244
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskPauseToResume$cancel$1$invokeSuspend$$inlined$transform$1.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadTaskPauseToResume$cancel$1$invokeSuspend$$inlined$transform$1(Flow flow, Continuation continuation, DownloadTaskPauseToResume downloadTaskPauseToResume) {
        super(2, continuation);
        this.$this_transform = flow;
        this.this$0 = downloadTaskPauseToResume;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        DownloadTaskPauseToResume$cancel$1$invokeSuspend$$inlined$transform$1 downloadTaskPauseToResume$cancel$1$invokeSuspend$$inlined$transform$1 = new DownloadTaskPauseToResume$cancel$1$invokeSuspend$$inlined$transform$1(this.$this_transform, continuation, this.this$0);
        downloadTaskPauseToResume$cancel$1$invokeSuspend$$inlined$transform$1.L$0 = obj;
        return downloadTaskPauseToResume$cancel$1$invokeSuspend$$inlined$transform$1;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo6invoke(@NotNull FlowCollector<? super Integer> flowCollector, @Nullable Continuation<? super Unit> continuation) {
        return ((DownloadTaskPauseToResume$cancel$1$invokeSuspend$$inlined$transform$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Flow flow = this.$this_transform;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(flowCollector, this.this$0);
            this.label = 1;
            if (flow.collect(anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
