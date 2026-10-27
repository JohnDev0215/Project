package com.byd.overseaappstore.module.ability.util.operate.iuu;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareFileUnzipInfo;
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
import spi.ia;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u0002H\u008a@¨\u0006\u0004"}, d2 = {ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "", "kotlinx/coroutines/flow/FlowKt__EmittersKt$transform$1", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$2", f = "ExecutorIuuWorkMulti.kt", i = {}, l = {40}, m = "invokeSuspend", n = {}, s = {})
public final class ExecutorIuuWorkMulti$install$$inlined$transform$2 extends SuspendLambda implements Function2<FlowCollector<? super SoftwareFileUnzipInfo>, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Context $context$inlined;
    public final /* synthetic */ String $packageName$inlined;
    public final /* synthetic */ Flow $this_transform;
    private /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ ia this$0;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$2$1, reason: invalid class name */
    public static final class AnonymousClass1<T> implements FlowCollector, SuspendFunction {
        public final /* synthetic */ FlowCollector<SoftwareFileUnzipInfo> a;
        public final /* synthetic */ ia b;
        public final /* synthetic */ Context c;
        public final /* synthetic */ String d;

        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$2$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$2$1", f = "ExecutorIuuWorkMulti.kt", i = {0, 0}, l = {231}, m = "emit", n = {"this", "$this$install_u24lambda_u2d4"}, s = {"L$0", "L$1"})
        public static final class C00501 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public int label;
            public /* synthetic */ Object result;

            public C00501(Continuation continuation) {
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

        public AnonymousClass1(FlowCollector flowCollector, ia iaVar, Context context, String str) {
            this.b = iaVar;
            this.c = context;
            this.d = str;
            this.a = flowCollector;
        }

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
                boolean r0 = r10 instanceof com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$2.AnonymousClass1.C00501
                if (r0 == 0) goto L13
                r0 = r10
                com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$2$1$1 r0 = (com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$2.AnonymousClass1.C00501) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$2$1$1 r0 = new com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$2$1$1
                r0.<init>(r10)
            L18:
                java.lang.Object r10 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L3d
                if (r2 != r3) goto L35
                java.lang.Object r9 = r0.L$2
                java.util.Iterator r9 = (java.util.Iterator) r9
                java.lang.Object r2 = r0.L$1
                kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                java.lang.Object r4 = r0.L$0
                com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$2$1 r4 = (com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$2.AnonymousClass1) r4
                kotlin.ResultKt.throwOnFailure(r10)
                goto L4a
            L35:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r10)
                throw r9
            L3d:
                kotlin.ResultKt.throwOnFailure(r10)
                kotlinx.coroutines.flow.FlowCollector<com.byd.overseaappstore.module.ability.util.download.data.SoftwareFileUnzipInfo> r10 = r8.a
                java.util.List r9 = (java.util.List) r9
                java.util.Iterator r9 = r9.iterator()
                r4 = r8
                r2 = r10
            L4a:
                boolean r10 = r9.hasNext()
                if (r10 == 0) goto L9a
                java.lang.Object r10 = r9.next()
                java.lang.String r10 = (java.lang.String) r10
                spi.ia r5 = r4.b
                com.byd.overseaappstore.module.ability.data.generator.DataGenerator r5 = r5.c
                android.content.Context r6 = r4.c
                java.lang.String r7 = r4.d
                com.byd.overseaappstore.module.ability.util.download.data.SoftwareFileUnzipInfo r5 = r5.parseToSoftwareFileUnzipInfo(r6, r10, r7)
                if (r5 != 0) goto L8b
                java.lang.String r5 = r4.d
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r6.<init>()
                java.lang.String r7 = "executor install error softwareFileUnzipInfo is null pkn:"
                r6.append(r7)
                r6.append(r5)
                java.lang.String r5 = " path:"
                r6.append(r5)
                r6.append(r10)
                java.lang.String r10 = " multiWork"
                r6.append(r10)
                java.lang.String r10 = r6.toString()
                r5 = 0
                java.lang.Object[] r5 = new java.lang.Object[r5]
                spi.ct.b(r10, r5)
                goto L4a
            L8b:
                r0.L$0 = r4
                r0.L$1 = r2
                r0.L$2 = r9
                r0.label = r3
                java.lang.Object r10 = r2.emit(r5, r0)
                if (r10 != r1) goto L4a
                return r1
            L9a:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$2.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExecutorIuuWorkMulti$install$$inlined$transform$2(Flow flow, Continuation continuation, ia iaVar, Context context, String str) {
        super(2, continuation);
        this.$this_transform = flow;
        this.this$0 = iaVar;
        this.$context$inlined = context;
        this.$packageName$inlined = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ExecutorIuuWorkMulti$install$$inlined$transform$2 executorIuuWorkMulti$install$$inlined$transform$2 = new ExecutorIuuWorkMulti$install$$inlined$transform$2(this.$this_transform, continuation, this.this$0, this.$context$inlined, this.$packageName$inlined);
        executorIuuWorkMulti$install$$inlined$transform$2.L$0 = obj;
        return executorIuuWorkMulti$install$$inlined$transform$2;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo6invoke(@NotNull FlowCollector<? super SoftwareFileUnzipInfo> flowCollector, @Nullable Continuation<? super Unit> continuation) {
        return ((ExecutorIuuWorkMulti$install$$inlined$transform$2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(flowCollector, this.this$0, this.$context$inlined, this.$packageName$inlined);
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
