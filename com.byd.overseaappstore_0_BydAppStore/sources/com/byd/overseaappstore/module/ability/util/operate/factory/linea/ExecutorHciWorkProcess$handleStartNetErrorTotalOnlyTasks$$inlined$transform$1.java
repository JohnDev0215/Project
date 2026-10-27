package com.byd.overseaappstore.module.ability.util.operate.factory.linea;

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
import spi.a2;
import spi.mv;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u0002H\u008a@¨\u0006\u0004"}, d2 = {ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "", "kotlinx/coroutines/flow/FlowKt__EmittersKt$transform$1", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleStartNetErrorTotalOnlyTasks$$inlined$transform$1", f = "ExecutorHciWorkProcess.kt", i = {}, l = {40}, m = "invokeSuspend", n = {}, s = {})
public final class ExecutorHciWorkProcess$handleStartNetErrorTotalOnlyTasks$$inlined$transform$1 extends SuspendLambda implements Function2<FlowCollector<? super mv>, Continuation<? super Unit>, Object> {
    public final /* synthetic */ a2 $bizSrc$inlined;
    public final /* synthetic */ Flow $this_transform;
    private /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ ExecutorHciWorkProcess this$0;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleStartNetErrorTotalOnlyTasks$$inlined$transform$1$1, reason: invalid class name */
    public static final class AnonymousClass1<T> implements FlowCollector, SuspendFunction {
        public final /* synthetic */ FlowCollector<mv> a;
        public final /* synthetic */ ExecutorHciWorkProcess b;
        public final /* synthetic */ a2 c;

        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleStartNetErrorTotalOnlyTasks$$inlined$transform$1$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleStartNetErrorTotalOnlyTasks$$inlined$transform$1$1", f = "ExecutorHciWorkProcess.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3}, l = {232, 248, 250, 272, 322}, m = "emit", n = {"this", "carrier", "$this$handleStartNetErrorTotalOnlyTasks_u24lambda_u2d11", "optParams", "this", "carrier", "optParams", "pairStateListRet", "toRefreshedOptParams", "$this$handleStartNetErrorTotalOnlyTasks_u24lambda_u2d11_u24lambda_u2d9", "this", "pairStateListRet", "toRefreshedOptParams", "this", "carrier", "optParams", "pairStateListRet", "toRefreshedOptParams", "$this$handleStartNetErrorTotalOnlyTasks_u24lambda_u2d11_u24lambda_u2d9", "index$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0"})
        public static final class C00451 extends ContinuationImpl {
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public Object L$4;
            public Object L$5;
            public Object L$6;
            public int label;
            public /* synthetic */ Object result;

            public C00451(Continuation continuation) {
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

        public AnonymousClass1(FlowCollector flowCollector, ExecutorHciWorkProcess executorHciWorkProcess, a2 a2Var) {
            this.b = executorHciWorkProcess;
            this.c = a2Var;
            this.a = flowCollector;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 11, insn: 0x03d9: MOVE (r9 I:??[OBJECT, ARRAY]) = (r11 I:??[OBJECT, ARRAY]), block:B:145:0x03d7 */
        /* JADX WARN: Removed duplicated region for block: B:112:0x027e  */
        /* JADX WARN: Removed duplicated region for block: B:114:0x0281 A[Catch: all -> 0x03ce, TryCatch #5 {all -> 0x03ce, blocks: (B:97:0x022e, B:99:0x0234, B:101:0x023c, B:102:0x023f, B:106:0x026e, B:108:0x0275, B:114:0x0281, B:115:0x028b, B:116:0x028f, B:118:0x0295, B:120:0x029f, B:125:0x02b4, B:127:0x02f4, B:129:0x02f8), top: B:165:0x022e }] */
        /* JADX WARN: Removed duplicated region for block: B:115:0x028b A[Catch: all -> 0x03ce, TryCatch #5 {all -> 0x03ce, blocks: (B:97:0x022e, B:99:0x0234, B:101:0x023c, B:102:0x023f, B:106:0x026e, B:108:0x0275, B:114:0x0281, B:115:0x028b, B:116:0x028f, B:118:0x0295, B:120:0x029f, B:125:0x02b4, B:127:0x02f4, B:129:0x02f8), top: B:165:0x022e }] */
        /* JADX WARN: Removed duplicated region for block: B:151:0x03f2  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x0104  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x010b  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
        /* JADX WARN: Removed duplicated region for block: B:89:0x01f1  */
        /* JADX WARN: Removed duplicated region for block: B:91:0x01f4 A[Catch: all -> 0x007c, TryCatch #4 {all -> 0x007c, blocks: (B:19:0x0061, B:22:0x0077, B:94:0x0210, B:83:0x01e4, B:85:0x01e8, B:91:0x01f4, B:95:0x021a), top: B:164:0x002a }] */
        /* JADX WARN: Removed duplicated region for block: B:95:0x021a A[Catch: all -> 0x007c, TRY_LEAVE, TryCatch #4 {all -> 0x007c, blocks: (B:19:0x0061, B:22:0x0077, B:94:0x0210, B:83:0x01e4, B:85:0x01e8, B:91:0x01f4, B:95:0x021a), top: B:164:0x002a }] */
        /* JADX WARN: Removed duplicated region for block: B:99:0x0234 A[Catch: all -> 0x03ce, TryCatch #5 {all -> 0x03ce, blocks: (B:97:0x022e, B:99:0x0234, B:101:0x023c, B:102:0x023f, B:106:0x026e, B:108:0x0275, B:114:0x0281, B:115:0x028b, B:116:0x028f, B:118:0x0295, B:120:0x029f, B:125:0x02b4, B:127:0x02f4, B:129:0x02f8), top: B:165:0x022e }] */
        /* JADX WARN: Type inference failed for: r0v32, types: [spi.g9] */
        /* JADX WARN: Type inference failed for: r16v0 */
        /* JADX WARN: Type inference failed for: r16v1 */
        /* JADX WARN: Type inference failed for: r16v2 */
        /* JADX WARN: Type inference failed for: r16v3 */
        /* JADX WARN: Type inference failed for: r16v4 */
        /* JADX WARN: Type inference failed for: r16v5 */
        /* JADX WARN: Type inference failed for: r16v7 */
        /* JADX WARN: Type inference failed for: r16v8 */
        /* JADX WARN: Type inference failed for: r19v0, types: [T] */
        /* JADX WARN: Type inference failed for: r1v3, types: [spi.g9] */
        /* JADX WARN: Type inference failed for: r20v1 */
        /* JADX WARN: Type inference failed for: r20v2 */
        /* JADX WARN: Type inference failed for: r20v3 */
        /* JADX WARN: Type inference failed for: r20v4 */
        /* JADX WARN: Type inference failed for: r20v5 */
        /* JADX WARN: Type inference failed for: r20v6 */
        /* JADX WARN: Type inference failed for: r20v7 */
        /* JADX WARN: Type inference failed for: r20v8 */
        /* JADX WARN: Type inference failed for: r5v1 */
        /* JADX WARN: Type inference failed for: r5v10 */
        /* JADX WARN: Type inference failed for: r5v2 */
        /* JADX WARN: Type inference failed for: r5v27, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r5v5 */
        /* JADX WARN: Type inference failed for: r6v1 */
        /* JADX WARN: Type inference failed for: r6v10 */
        /* JADX WARN: Type inference failed for: r6v2 */
        /* JADX WARN: Type inference failed for: r6v21, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r6v6 */
        /* JADX WARN: Type inference failed for: r7v1, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r7v17, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r7v18, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r7v19 */
        /* JADX WARN: Type inference failed for: r7v2 */
        /* JADX WARN: Type inference failed for: r7v20 */
        /* JADX WARN: Type inference failed for: r7v21 */
        /* JADX WARN: Type inference failed for: r7v24 */
        /* JADX WARN: Type inference failed for: r7v29 */
        /* JADX WARN: Type inference failed for: r7v34 */
        /* JADX WARN: Type inference failed for: r7v35 */
        /* JADX WARN: Type inference failed for: r7v36 */
        /* JADX WARN: Type inference failed for: r7v37 */
        /* JADX WARN: Type inference failed for: r8v1, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r8v11 */
        /* JADX WARN: Type inference failed for: r8v13 */
        /* JADX WARN: Type inference failed for: r8v17 */
        /* JADX WARN: Type inference failed for: r8v2 */
        /* JADX WARN: Type inference failed for: r8v24 */
        /* JADX WARN: Type inference failed for: r8v29 */
        /* JADX WARN: Type inference failed for: r8v30 */
        /* JADX WARN: Type inference failed for: r8v31 */
        /* JADX WARN: Type inference failed for: r8v5 */
        /* JADX WARN: Type inference failed for: r8v6, types: [java.lang.Object, java.util.List] */
        /* JADX WARN: Type inference failed for: r8v7, types: [java.lang.Object, java.util.List] */
        /* JADX WARN: Type inference failed for: r8v8 */
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
        public final java.lang.Object emit(T r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
            /*
                Method dump skipped, instruction units count: 1043
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess$handleStartNetErrorTotalOnlyTasks$$inlined$transform$1.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExecutorHciWorkProcess$handleStartNetErrorTotalOnlyTasks$$inlined$transform$1(Flow flow, Continuation continuation, ExecutorHciWorkProcess executorHciWorkProcess, a2 a2Var) {
        super(2, continuation);
        this.$this_transform = flow;
        this.this$0 = executorHciWorkProcess;
        this.$bizSrc$inlined = a2Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ExecutorHciWorkProcess$handleStartNetErrorTotalOnlyTasks$$inlined$transform$1 executorHciWorkProcess$handleStartNetErrorTotalOnlyTasks$$inlined$transform$1 = new ExecutorHciWorkProcess$handleStartNetErrorTotalOnlyTasks$$inlined$transform$1(this.$this_transform, continuation, this.this$0, this.$bizSrc$inlined);
        executorHciWorkProcess$handleStartNetErrorTotalOnlyTasks$$inlined$transform$1.L$0 = obj;
        return executorHciWorkProcess$handleStartNetErrorTotalOnlyTasks$$inlined$transform$1;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo6invoke(@NotNull FlowCollector<? super mv> flowCollector, @Nullable Continuation<? super Unit> continuation) {
        return ((ExecutorHciWorkProcess$handleStartNetErrorTotalOnlyTasks$$inlined$transform$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
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
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(flowCollector, this.this$0, this.$bizSrc$inlined);
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
