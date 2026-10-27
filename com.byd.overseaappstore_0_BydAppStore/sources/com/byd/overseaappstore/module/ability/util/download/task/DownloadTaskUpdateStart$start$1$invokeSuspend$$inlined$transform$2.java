package com.byd.overseaappstore.module.ability.util.download.task;

import androidx.exifinterface.media.ExifInterface;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
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
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2", f = "DownloadTaskUpdateStart.kt", i = {}, l = {40}, m = "invokeSuspend", n = {}, s = {})
public final class DownloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2 extends SuspendLambda implements Function2<FlowCollector<? super Integer>, Continuation<? super Unit>, Object> {
    public final /* synthetic */ SoftwareOptParam $softwareOptParam$inlined;
    public final /* synthetic */ Flow $this_transform;
    private /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ DownloadTaskUpdateStart this$0;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2$1, reason: invalid class name */
    public static final class AnonymousClass1<T> implements FlowCollector, SuspendFunction {
        public final /* synthetic */ FlowCollector<Integer> a;
        public final /* synthetic */ DownloadTaskUpdateStart b;
        public final /* synthetic */ SoftwareOptParam c;

        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2$1", f = "DownloadTaskUpdateStart.kt", i = {0, 0, 0, 0}, l = {229, 237}, m = "emit", n = {"this", "$this$invokeSuspend_u24lambda_u2d4", "percentCount", "state"}, s = {"L$0", "L$1", "I$0", "I$1"})
        public static final class C00321 extends ContinuationImpl {
            public int I$0;
            public int I$1;
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public C00321(Continuation continuation) {
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

        public AnonymousClass1(FlowCollector flowCollector, DownloadTaskUpdateStart downloadTaskUpdateStart, SoftwareOptParam softwareOptParam) {
            this.b = downloadTaskUpdateStart;
            this.c = softwareOptParam;
            this.a = flowCollector;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00a5 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object emit(T r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
            /*
                r8 = this;
                boolean r0 = r10 instanceof com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2.AnonymousClass1.C00321
                if (r0 == 0) goto L13
                r0 = r10
                com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2$1$1 r0 = (com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2.AnonymousClass1.C00321) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2$1$1 r0 = new com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2$1$1
                r0.<init>(r10)
            L18:
                java.lang.Object r10 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L45
                if (r2 == r4) goto L35
                if (r2 != r3) goto L2d
                kotlin.ResultKt.throwOnFailure(r10)
                goto La6
            L2d:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r10)
                throw r9
            L35:
                int r9 = r0.I$1
                int r2 = r0.I$0
                java.lang.Object r4 = r0.L$1
                kotlinx.coroutines.flow.FlowCollector r4 = (kotlinx.coroutines.flow.FlowCollector) r4
                java.lang.Object r5 = r0.L$0
                com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2$1 r5 = (com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2.AnonymousClass1) r5
                kotlin.ResultKt.throwOnFailure(r10)
                goto L7f
            L45:
                kotlin.ResultKt.throwOnFailure(r10)
                kotlinx.coroutines.flow.FlowCollector<java.lang.Integer> r10 = r8.a
                java.lang.Number r9 = (java.lang.Number) r9
                int r2 = r9.intValue()
                r9 = 100
                r5 = 12
                if (r2 != r9) goto L58
                r9 = r5
                goto L5a
            L58:
                r9 = 10
            L5a:
                if (r9 != r5) goto L81
                com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart r5 = r8.b
                spi.i9 r5 = r5.b
                com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam r6 = r8.c
                java.lang.String r6 = r6.getPackageName()
                com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam r7 = r8.c
                int r7 = r7.getVersionCode()
                r0.L$0 = r8
                r0.L$1 = r10
                r0.I$0 = r2
                r0.I$1 = r9
                r0.label = r4
                java.lang.Object r4 = r5.r(r6, r9, r7, r0)
                if (r4 != r1) goto L7d
                return r1
            L7d:
                r5 = r8
                r4 = r10
            L7f:
                r10 = r4
                goto L82
            L81:
                r5 = r8
            L82:
                com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart r4 = r5.b
                spi.oa r4 = r4.e
                com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam r5 = r5.c
                java.lang.String r5 = r5.getPackageName()
                com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData r6 = new com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData
                r6.<init>(r2, r9)
                r4.h(r5, r6)
                java.lang.Integer r9 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r2)
                r2 = 0
                r0.L$0 = r2
                r0.L$1 = r2
                r0.label = r3
                java.lang.Object r9 = r10.emit(r9, r0)
                if (r9 != r1) goto La6
                return r1
            La6:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2(Flow flow, Continuation continuation, DownloadTaskUpdateStart downloadTaskUpdateStart, SoftwareOptParam softwareOptParam) {
        super(2, continuation);
        this.$this_transform = flow;
        this.this$0 = downloadTaskUpdateStart;
        this.$softwareOptParam$inlined = softwareOptParam;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        DownloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2 downloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2 = new DownloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2(this.$this_transform, continuation, this.this$0, this.$softwareOptParam$inlined);
        downloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2.L$0 = obj;
        return downloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo6invoke(@NotNull FlowCollector<? super Integer> flowCollector, @Nullable Continuation<? super Unit> continuation) {
        return ((DownloadTaskUpdateStart$start$1$invokeSuspend$$inlined$transform$2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(flowCollector, this.this$0, this.$softwareOptParam$inlined);
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
