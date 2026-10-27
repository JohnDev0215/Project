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
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$3", f = "ExecutorIuuWorkMulti.kt", i = {}, l = {40}, m = "invokeSuspend", n = {}, s = {})
public final class ExecutorIuuWorkMulti$install$$inlined$transform$3 extends SuspendLambda implements Function2<FlowCollector<? super SoftwareFileUnzipInfo>, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Context $context$inlined;
    public final /* synthetic */ Flow $this_transform;
    private /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ ia this$0;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$3$1, reason: invalid class name */
    public static final class AnonymousClass1<T> implements FlowCollector, SuspendFunction {
        public final /* synthetic */ FlowCollector<SoftwareFileUnzipInfo> a;
        public final /* synthetic */ ia b;
        public final /* synthetic */ Context c;

        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$3$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$3$1", f = "ExecutorIuuWorkMulti.kt", i = {0, 0}, l = {229, 230}, m = "emit", n = {"$this$install_u24lambda_u2d5", "retSoftwareFileUnzipInfo"}, s = {"L$0", "L$1"})
        public static final class C00511 extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public C00511(Continuation continuation) {
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

        public AnonymousClass1(FlowCollector flowCollector, ia iaVar, Context context) {
            this.b = iaVar;
            this.c = context;
            this.a = flowCollector;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:101:0x02c3 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
        /* JADX WARN: Removed duplicated region for block: B:82:0x021b  */
        /* JADX WARN: Removed duplicated region for block: B:83:0x023d  */
        /* JADX WARN: Removed duplicated region for block: B:88:0x0248  */
        /* JADX WARN: Removed duplicated region for block: B:89:0x0275  */
        /* JADX WARN: Type inference failed for: r0v13 */
        /* JADX WARN: Type inference failed for: r0v15, types: [com.byd.overseaappstore.module.ability.util.download.data.SoftwareFileUnzipInfo, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v48 */
        /* JADX WARN: Type inference failed for: r0v56 */
        /* JADX WARN: Type inference failed for: r26v0, types: [T] */
        /* JADX WARN: Type inference failed for: r26v1 */
        /* JADX WARN: Type inference failed for: r26v10 */
        /* JADX WARN: Type inference failed for: r26v11 */
        /* JADX WARN: Type inference failed for: r26v2 */
        /* JADX WARN: Type inference failed for: r26v3 */
        /* JADX WARN: Type inference failed for: r26v4, types: [com.byd.overseaappstore.module.ability.util.download.data.SoftwareFileUnzipInfo] */
        /* JADX WARN: Type inference failed for: r26v5 */
        /* JADX WARN: Type inference failed for: r26v6 */
        /* JADX WARN: Type inference failed for: r26v7 */
        /* JADX WARN: Type inference failed for: r26v9 */
        /* JADX WARN: Type inference failed for: r2v10 */
        /* JADX WARN: Type inference failed for: r2v12, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v15 */
        /* JADX WARN: Type inference failed for: r3v6, types: [com.byd.overseaappstore.module.ability.data.generator.DataGenerator] */
        /* JADX WARN: Type inference failed for: r5v20, types: [kotlinx.coroutines.flow.FlowCollector] */
        /* JADX WARN: Type inference failed for: r5v23 */
        /* JADX WARN: Type inference failed for: r5v24 */
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
        @Override // kotlinx.coroutines.flow.FlowCollector
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object emit(T r26, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r27) {
            /*
                Method dump skipped, instruction units count: 711
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.iuu.ExecutorIuuWorkMulti$install$$inlined$transform$3.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExecutorIuuWorkMulti$install$$inlined$transform$3(Flow flow, Continuation continuation, ia iaVar, Context context) {
        super(2, continuation);
        this.$this_transform = flow;
        this.this$0 = iaVar;
        this.$context$inlined = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ExecutorIuuWorkMulti$install$$inlined$transform$3 executorIuuWorkMulti$install$$inlined$transform$3 = new ExecutorIuuWorkMulti$install$$inlined$transform$3(this.$this_transform, continuation, this.this$0, this.$context$inlined);
        executorIuuWorkMulti$install$$inlined$transform$3.L$0 = obj;
        return executorIuuWorkMulti$install$$inlined$transform$3;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo6invoke(@NotNull FlowCollector<? super SoftwareFileUnzipInfo> flowCollector, @Nullable Continuation<? super Unit> continuation) {
        return ((ExecutorIuuWorkMulti$install$$inlined$transform$3) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(flowCollector, this.this$0, this.$context$inlined);
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
