package androidx.compose.runtime;

import java.util.Arrays;
import kotlin.BuilderInference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.objectweb.asm.commons.GeneratorAdapter;

/* JADX INFO: compiled from: ProduceState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\u001as\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00052/\b\u0001\u0010\b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001ai\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052/\b\u0001\u0010\b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a_\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052/\b\u0001\u0010\b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001am\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\u0016\u0010\u0011\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u0012\"\u0004\u0018\u00010\u00052/\b\u0001\u0010\b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001aU\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022/\b\u0001\u0010\b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"produceState", "Landroidx/compose/runtime/State;", "T", "initialValue", "key1", "", "key2", "key3", "producer", "Lkotlin/Function2;", "Landroidx/compose/runtime/ProduceStateScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "keys", "", "(Ljava/lang/Object;[Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "runtime_release"}, k = 5, mv = {1, 6, 0}, xi = 48, xs = "androidx/compose/runtime/SnapshotStateKt")
final /* synthetic */ class SnapshotStateKt__ProduceStateKt {
    @Composable
    public static final <T> State<T> produceState(T t, @BuilderInference Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> producer, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(producer, "producer");
        composer.startReplaceableGroup(-1870533674);
        composer.startReplaceableGroup(-3687241);
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        MutableState mutableState = (MutableState) objRememberedValue;
        EffectsKt.LaunchedEffect(Unit.INSTANCE, new AnonymousClass1(producer, mutableState, null), composer, 0);
        composer.endReplaceableGroup();
        return mutableState;
    }

    @Composable
    public static final <T> State<T> produceState(T t, Object obj, @BuilderInference Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> producer, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(producer, "producer");
        composer.startReplaceableGroup(-1870532360);
        composer.startReplaceableGroup(-3687241);
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        MutableState mutableState = (MutableState) objRememberedValue;
        EffectsKt.LaunchedEffect(obj, new AnonymousClass2(producer, mutableState, null), composer, 8);
        composer.endReplaceableGroup();
        return mutableState;
    }

    @Composable
    public static final <T> State<T> produceState(T t, Object obj, Object obj2, @BuilderInference Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> producer, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(producer, "producer");
        composer.startReplaceableGroup(-1870531010);
        composer.startReplaceableGroup(-3687241);
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        MutableState mutableState = (MutableState) objRememberedValue;
        EffectsKt.LaunchedEffect(obj, obj2, new AnonymousClass3(producer, mutableState, null), composer, 72);
        composer.endReplaceableGroup();
        return mutableState;
    }

    @Composable
    public static final <T> State<T> produceState(T t, Object obj, Object obj2, Object obj3, @BuilderInference Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> producer, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(producer, "producer");
        composer.startReplaceableGroup(-1870529623);
        composer.startReplaceableGroup(-3687241);
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        MutableState mutableState = (MutableState) objRememberedValue;
        EffectsKt.LaunchedEffect(obj, obj2, obj3, new AnonymousClass4(producer, mutableState, null), composer, 584);
        composer.endReplaceableGroup();
        return mutableState;
    }

    @Composable
    public static final <T> State<T> produceState(T t, Object[] keys, @BuilderInference Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> producer, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        Intrinsics.checkNotNullParameter(producer, "producer");
        composer.startReplaceableGroup(-1870528250);
        composer.startReplaceableGroup(-3687241);
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        MutableState mutableState = (MutableState) objRememberedValue;
        EffectsKt.LaunchedEffect(Arrays.copyOf(keys, keys.length), (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) new AnonymousClass5(producer, mutableState, null), composer, 8);
        composer.endReplaceableGroup();
        return mutableState;
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.SnapshotStateKt__ProduceStateKt$produceState$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProduceState.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.SnapshotStateKt__ProduceStateKt$produceState$1", f = "ProduceState.kt", i = {}, l = {86}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<ProduceStateScope<T>, Continuation<? super Unit>, Object> $producer;
        final /* synthetic */ MutableState<T> $result;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> function2, MutableState<T> mutableState, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$producer = function2;
            this.$result = mutableState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$producer, this.$result, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to androidx.compose.runtime.SnapshotStateKt__ProduceStateKt$produceState$1 for r5v1 'this'  java.lang.Object
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 1
                if (r1 == 0) goto L17
                if (r1 != r2) goto Lf
                kotlin.ResultKt.throwOnFailure(r6)
                goto L34
            Lf:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L17:
                kotlin.ResultKt.throwOnFailure(r6)
                java.lang.Object r6 = r5.L$0
                kotlinx.coroutines.CoroutineScope r6 = (kotlinx.coroutines.CoroutineScope) r6
                kotlin.jvm.functions.Function2<androidx.compose.runtime.ProduceStateScope<T>, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r1 = r5.$producer
                androidx.compose.runtime.ProduceStateScopeImpl r3 = new androidx.compose.runtime.ProduceStateScopeImpl
                androidx.compose.runtime.MutableState<T> r4 = r5.$result
                kotlin.coroutines.CoroutineContext r6 = r6.getCoroutineContext()
                r3.<init>(r4, r6)
                r5.label = r2
                java.lang.Object r6 = r1.mo4invoke(r3, r5)
                if (r6 != r0) goto L34
                return r0
            L34:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SnapshotStateKt__ProduceStateKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.SnapshotStateKt__ProduceStateKt$produceState$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProduceState.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.SnapshotStateKt__ProduceStateKt$produceState$2", f = "ProduceState.kt", i = {}, l = {119}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<ProduceStateScope<T>, Continuation<? super Unit>, Object> $producer;
        final /* synthetic */ MutableState<T> $result;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> function2, MutableState<T> mutableState, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$producer = function2;
            this.$result = mutableState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$producer, this.$result, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to androidx.compose.runtime.SnapshotStateKt__ProduceStateKt$produceState$2 for r5v1 'this'  java.lang.Object
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 1
                if (r1 == 0) goto L17
                if (r1 != r2) goto Lf
                kotlin.ResultKt.throwOnFailure(r6)
                goto L34
            Lf:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L17:
                kotlin.ResultKt.throwOnFailure(r6)
                java.lang.Object r6 = r5.L$0
                kotlinx.coroutines.CoroutineScope r6 = (kotlinx.coroutines.CoroutineScope) r6
                kotlin.jvm.functions.Function2<androidx.compose.runtime.ProduceStateScope<T>, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r1 = r5.$producer
                androidx.compose.runtime.ProduceStateScopeImpl r3 = new androidx.compose.runtime.ProduceStateScopeImpl
                androidx.compose.runtime.MutableState<T> r4 = r5.$result
                kotlin.coroutines.CoroutineContext r6 = r6.getCoroutineContext()
                r3.<init>(r4, r6)
                r5.label = r2
                java.lang.Object r6 = r1.mo4invoke(r3, r5)
                if (r6 != r0) goto L34
                return r0
            L34:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SnapshotStateKt__ProduceStateKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.SnapshotStateKt__ProduceStateKt$produceState$3, reason: invalid class name */
    /* JADX INFO: compiled from: ProduceState.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.SnapshotStateKt__ProduceStateKt$produceState$3", f = "ProduceState.kt", i = {}, l = {GeneratorAdapter.EQ}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<ProduceStateScope<T>, Continuation<? super Unit>, Object> $producer;
        final /* synthetic */ MutableState<T> $result;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass3(Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> function2, MutableState<T> mutableState, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$producer = function2;
            this.$result = mutableState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$producer, this.$result, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to androidx.compose.runtime.SnapshotStateKt__ProduceStateKt$produceState$3 for r5v1 'this'  java.lang.Object
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 1
                if (r1 == 0) goto L17
                if (r1 != r2) goto Lf
                kotlin.ResultKt.throwOnFailure(r6)
                goto L34
            Lf:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L17:
                kotlin.ResultKt.throwOnFailure(r6)
                java.lang.Object r6 = r5.L$0
                kotlinx.coroutines.CoroutineScope r6 = (kotlinx.coroutines.CoroutineScope) r6
                kotlin.jvm.functions.Function2<androidx.compose.runtime.ProduceStateScope<T>, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r1 = r5.$producer
                androidx.compose.runtime.ProduceStateScopeImpl r3 = new androidx.compose.runtime.ProduceStateScopeImpl
                androidx.compose.runtime.MutableState<T> r4 = r5.$result
                kotlin.coroutines.CoroutineContext r6 = r6.getCoroutineContext()
                r3.<init>(r4, r6)
                r5.label = r2
                java.lang.Object r6 = r1.mo4invoke(r3, r5)
                if (r6 != r0) goto L34
                return r0
            L34:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SnapshotStateKt__ProduceStateKt.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.SnapshotStateKt__ProduceStateKt$produceState$4, reason: invalid class name */
    /* JADX INFO: compiled from: ProduceState.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.SnapshotStateKt__ProduceStateKt$produceState$4", f = "ProduceState.kt", i = {}, l = {188}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<ProduceStateScope<T>, Continuation<? super Unit>, Object> $producer;
        final /* synthetic */ MutableState<T> $result;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass4(Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> function2, MutableState<T> mutableState, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$producer = function2;
            this.$result = mutableState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.$producer, this.$result, continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to androidx.compose.runtime.SnapshotStateKt__ProduceStateKt$produceState$4 for r5v1 'this'  java.lang.Object
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 1
                if (r1 == 0) goto L17
                if (r1 != r2) goto Lf
                kotlin.ResultKt.throwOnFailure(r6)
                goto L34
            Lf:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L17:
                kotlin.ResultKt.throwOnFailure(r6)
                java.lang.Object r6 = r5.L$0
                kotlinx.coroutines.CoroutineScope r6 = (kotlinx.coroutines.CoroutineScope) r6
                kotlin.jvm.functions.Function2<androidx.compose.runtime.ProduceStateScope<T>, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r1 = r5.$producer
                androidx.compose.runtime.ProduceStateScopeImpl r3 = new androidx.compose.runtime.ProduceStateScopeImpl
                androidx.compose.runtime.MutableState<T> r4 = r5.$result
                kotlin.coroutines.CoroutineContext r6 = r6.getCoroutineContext()
                r3.<init>(r4, r6)
                r5.label = r2
                java.lang.Object r6 = r1.mo4invoke(r3, r5)
                if (r6 != r0) goto L34
                return r0
            L34:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SnapshotStateKt__ProduceStateKt.AnonymousClass4.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.SnapshotStateKt__ProduceStateKt$produceState$5, reason: invalid class name */
    /* JADX INFO: compiled from: ProduceState.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.SnapshotStateKt__ProduceStateKt$produceState$5", f = "ProduceState.kt", i = {}, l = {222}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<ProduceStateScope<T>, Continuation<? super Unit>, Object> $producer;
        final /* synthetic */ MutableState<T> $result;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass5(Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> function2, MutableState<T> mutableState, Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
            this.$producer = function2;
            this.$result = mutableState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass5 anonymousClass5 = new AnonymousClass5(this.$producer, this.$result, continuation);
            anonymousClass5.L$0 = obj;
            return anonymousClass5;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to androidx.compose.runtime.SnapshotStateKt__ProduceStateKt$produceState$5 for r5v1 'this'  java.lang.Object
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 1
                if (r1 == 0) goto L17
                if (r1 != r2) goto Lf
                kotlin.ResultKt.throwOnFailure(r6)
                goto L34
            Lf:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L17:
                kotlin.ResultKt.throwOnFailure(r6)
                java.lang.Object r6 = r5.L$0
                kotlinx.coroutines.CoroutineScope r6 = (kotlinx.coroutines.CoroutineScope) r6
                kotlin.jvm.functions.Function2<androidx.compose.runtime.ProduceStateScope<T>, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r1 = r5.$producer
                androidx.compose.runtime.ProduceStateScopeImpl r3 = new androidx.compose.runtime.ProduceStateScopeImpl
                androidx.compose.runtime.MutableState<T> r4 = r5.$result
                kotlin.coroutines.CoroutineContext r6 = r6.getCoroutineContext()
                r3.<init>(r4, r6)
                r5.label = r2
                java.lang.Object r6 = r1.mo4invoke(r3, r5)
                if (r6 != r0) goto L34
                return r0
            L34:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SnapshotStateKt__ProduceStateKt.AnonymousClass5.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
