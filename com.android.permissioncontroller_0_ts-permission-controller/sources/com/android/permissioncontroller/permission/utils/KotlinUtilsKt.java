package com.android.permissioncontroller.permission.utils;

import android.os.Bundle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: KotlinUtils.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001ad\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072/\b\u0004\u0010\b\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\t¢\u0006\u0002\b\fH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\r\u001am\u0010\u000e\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002\"\u000e\b\u0001\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u00020\u0010*\u0002H\u000f2%\b\u0002\u0010\u0011\u001a\u001f\u0012\u0004\u0012\u0002H\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\f2\u0019\b\u0002\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\u0002\b\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001ap\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u001a*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072/\b\u0004\u0010\u001b\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001a0\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\t¢\u0006\u0002\b\fH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\r\u001a\u001e\u0010\u001c\u001a\u00020\u0001*\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"forEachInParallel", "", "T", "", "context", "Lkotlin/coroutines/CoroutineContext;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "action", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Iterable;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getInitializedValue", "LD", "Landroidx/lifecycle/LiveData;", "observe", "Lkotlin/Function2;", "Landroidx/lifecycle/Observer;", "isInitialized", "Lkotlin/Function1;", "", "(Landroidx/lifecycle/LiveData;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mapInParallel", "", "R", "transform", "navigateSafe", "Landroidx/navigation/NavController;", "destResId", "", "args", "Landroid/os/Bundle;", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 2, mv = {1, 1, 16})
public final class KotlinUtilsKt {

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.utils.KotlinUtilsKt$forEachInParallel$1, reason: invalid class name */
    /* JADX INFO: compiled from: KotlinUtils.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072/\b\u0004\u0010\b\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t¢\u0006\u0002\b\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0086H"}, d2 = {"forEachInParallel", "", "T", "", "context", "Lkotlin/coroutines/CoroutineContext;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "action", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "continuation"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.utils.KotlinUtilsKt", f = "KotlinUtils.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {956}, m = "forEachInParallel", n = {"$this$forEachInParallel", "context", "scope", "action", "$this$mapInParallel$iv", "$this$map$iv$iv", "$this$mapTo$iv$iv$iv", "destination$iv$iv$iv", "item$iv$iv$iv", "it$iv"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$9", "L$10"})
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KotlinUtilsKt.forEachInParallel(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.utils.KotlinUtilsKt$mapInParallel$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: KotlinUtils.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2/\b\u0004\u0010\t\u001a)\b\u0001\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\n¢\u0006\u0002\b\f2\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u000e0\u000bH\u0086H"}, d2 = {"mapInParallel", "", "T", "R", "", "context", "Lkotlin/coroutines/CoroutineContext;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "transform", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "continuation", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.android.permissioncontroller.permission.utils.KotlinUtilsKt", f = "KotlinUtils.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {901}, m = "mapInParallel", n = {"$this$mapInParallel", "context", "scope", "transform", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "it"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "L$9"})
    public static final class C00581 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public C00581(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KotlinUtilsKt.mapInParallel(null, null, null, null, this);
        }
    }

    public static /* synthetic */ Object getInitializedValue$default(LiveData liveData, Function2 function2, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function2 = new Function2<LD, Observer<T>, Unit>() { // from class: com.android.permissioncontroller.permission.utils.KotlinUtilsKt.getInitializedValue.2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object obj2, Object obj3) {
                    invoke((LiveData) obj2, (Observer) obj3);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Incorrect types in method signature: (TLD;Landroidx/lifecycle/Observer<TT;>;)V */
                public final void invoke(LiveData receiver, Observer it) {
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    receiver.observeForever(it);
                }
            };
        }
        if ((i & 2) != 0) {
            function1 = new Function1<LD, Boolean>() { // from class: com.android.permissioncontroller.permission.utils.KotlinUtilsKt.getInitializedValue.3
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(Object obj2) {
                    return Boolean.valueOf(invoke((LiveData) obj2));
                }

                /* JADX WARN: Incorrect types in method signature: (TLD;)Z */
                public final boolean invoke(LiveData receiver) {
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    return receiver.getValue() != null;
                }
            };
        }
        return getInitializedValue(liveData, function2, function1, continuation);
    }

    public static final <T, LD extends LiveData<T>> Object getInitializedValue(final LD ld, final Function2<? super LD, ? super Observer<T>, Unit> function2, final Function1<? super LD, Boolean> function1, Continuation<? super T> continuation) {
        if (function1.invoke(ld).booleanValue()) {
            return ld.getValue();
        }
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
        final SafeContinuation continuation2 = safeContinuation;
        final AtomicReference observer = new AtomicReference();
        observer.set(new Observer<T>() { // from class: com.android.permissioncontroller.permission.utils.KotlinUtilsKt$getInitializedValue$$inlined$suspendCoroutine$lambda$1

            /* JADX INFO: renamed from: com.android.permissioncontroller.permission.utils.KotlinUtilsKt$getInitializedValue$$inlined$suspendCoroutine$lambda$1$1, reason: invalid class name */
            /* JADX INFO: compiled from: KotlinUtils.kt */
            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004*\u00020\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "LD", "Landroidx/lifecycle/LiveData;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/android/permissioncontroller/permission/utils/KotlinUtilsKt$getInitializedValue$4$1$1"}, k = 3, mv = {1, 1, 16})
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Object $newValue;
                int label;
                private CoroutineScope p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(Object obj, Continuation continuation) {
                    super(2, continuation);
                    this.$newValue = obj;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$newValue, completion);
                    anonymousClass1.p$ = (CoroutineScope) obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure($result);
                    CoroutineScope coroutineScope = this.p$;
                    Observer<? super T> observer = (Observer) observer.getAndSet(null);
                    if (observer != null) {
                        ld.removeObserver(observer);
                        Continuation continuation = continuation2;
                        Object obj = this.$newValue;
                        Result.Companion companion = Result.INSTANCE;
                        continuation.resumeWith(Result.m21constructorimpl(obj));
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                if (((Boolean) function1.invoke(ld)).booleanValue()) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(t, null), 2, null);
                }
            }
        });
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new KotlinUtilsKt$getInitializedValue$$inlined$suspendCoroutine$lambda$2(observer, null, ld, function1, function2), 2, null);
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
            return orThrow;
        }
        return orThrow;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x012d -> B:25:0x013f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T, R> java.lang.Object mapInParallel(java.lang.Iterable<? extends T> r24, kotlin.coroutines.CoroutineContext r25, kotlinx.coroutines.CoroutineScope r26, kotlin.jvm.functions.Function3<? super kotlinx.coroutines.CoroutineScope, ? super T, ? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object> r27, kotlin.coroutines.Continuation<? super java.util.List<? extends R>> r28) {
        /*
            Method dump skipped, instruction units count: 339
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.utils.KotlinUtilsKt.mapInParallel(java.lang.Iterable, kotlin.coroutines.CoroutineContext, kotlinx.coroutines.CoroutineScope, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object mapInParallel$default(Iterable $this$mapInParallel, CoroutineContext context, CoroutineScope scope, Function3 transform, Continuation continuation, int i, Object obj) {
        GlobalScope scope2 = (i & 2) != 0 ? GlobalScope.INSTANCE : scope;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapInParallel, 10));
        for (Object item$iv$iv : $this$mapInParallel) {
            destination$iv$iv.add(BuildersKt__Builders_commonKt.async$default(scope2, context, null, new KotlinUtilsKt$mapInParallel$$inlined$map$lambda$3(item$iv$iv, null, scope2, context, transform), 2, null));
        }
        Iterable $this$map$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv2 : $this$map$iv) {
            Deferred it = (Deferred) item$iv$iv2;
            InlineMarker.mark(0);
            Object objAwait = it.await(continuation);
            InlineMarker.mark(1);
            destination$iv$iv2.add(objAwait);
        }
        return (List) destination$iv$iv2;
    }

    private static final Object mapInParallel$$forInline(Iterable $this$mapInParallel, CoroutineContext context, CoroutineScope scope, Function3 transform, Continuation continuation) {
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapInParallel, 10));
        for (Object item$iv$iv : $this$mapInParallel) {
            destination$iv$iv.add(BuildersKt__Builders_commonKt.async$default(scope, context, null, new KotlinUtilsKt$mapInParallel$$inlined$map$lambda$1(item$iv$iv, null, scope, context, transform), 2, null));
        }
        Iterable $this$map$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv2 : $this$map$iv) {
            Deferred it = (Deferred) item$iv$iv2;
            InlineMarker.mark(0);
            Object objAwait = it.await(continuation);
            InlineMarker.mark(1);
            destination$iv$iv2.add(objAwait);
        }
        return (List) destination$iv$iv2;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0140 -> B:25:0x015d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object forEachInParallel(java.lang.Iterable<? extends T> r25, kotlin.coroutines.CoroutineContext r26, kotlinx.coroutines.CoroutineScope r27, kotlin.jvm.functions.Function3<? super kotlinx.coroutines.CoroutineScope, ? super T, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r28, kotlin.coroutines.Continuation<? super kotlin.Unit> r29) {
        /*
            Method dump skipped, instruction units count: 380
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.utils.KotlinUtilsKt.forEachInParallel(java.lang.Iterable, kotlin.coroutines.CoroutineContext, kotlinx.coroutines.CoroutineScope, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object forEachInParallel$default(Iterable $this$forEachInParallel, CoroutineContext context, CoroutineScope scope, Function3 action, Continuation continuation, int i, Object obj) {
        GlobalScope scope2 = (i & 2) != 0 ? GlobalScope.INSTANCE : scope;
        Collection destination$iv$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$forEachInParallel, 10));
        for (Object item$iv$iv$iv : $this$forEachInParallel) {
            destination$iv$iv$iv.add(BuildersKt__Builders_commonKt.async$default(scope2, context, null, new KotlinUtilsKt$forEachInParallel$$inlined$mapInParallel$3(item$iv$iv$iv, null, scope2, context, action), 2, null));
        }
        Iterable $this$map$iv$iv = (List) destination$iv$iv$iv;
        Collection destination$iv$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv$iv, 10));
        for (Object item$iv$iv$iv2 : $this$map$iv$iv) {
            Deferred it$iv = (Deferred) item$iv$iv$iv2;
            InlineMarker.mark(0);
            Object objAwait = it$iv.await(continuation);
            InlineMarker.mark(1);
            destination$iv$iv$iv2.add(objAwait);
        }
        return Unit.INSTANCE;
    }

    private static final Object forEachInParallel$$forInline(Iterable $this$forEachInParallel, CoroutineContext context, CoroutineScope scope, Function3 action, Continuation continuation) {
        Collection destination$iv$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$forEachInParallel, 10));
        for (Object item$iv$iv$iv : $this$forEachInParallel) {
            destination$iv$iv$iv.add(BuildersKt__Builders_commonKt.async$default(scope, context, null, new KotlinUtilsKt$forEachInParallel$$inlined$mapInParallel$1(item$iv$iv$iv, null, scope, context, action), 2, null));
        }
        Iterable $this$map$iv$iv = (List) destination$iv$iv$iv;
        Collection destination$iv$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv$iv, 10));
        for (Object item$iv$iv$iv2 : $this$map$iv$iv) {
            Deferred it$iv = (Deferred) item$iv$iv$iv2;
            InlineMarker.mark(0);
            Object objAwait = it$iv.await(continuation);
            InlineMarker.mark(1);
            destination$iv$iv$iv2.add(objAwait);
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void navigateSafe$default(NavController navController, int i, Bundle bundle, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            bundle = (Bundle) null;
        }
        navigateSafe(navController, i, bundle);
    }

    public static final void navigateSafe(NavController navigateSafe, int destResId, Bundle args) {
        NavAction navAction;
        Intrinsics.checkParameterIsNotNull(navigateSafe, "$this$navigateSafe");
        NavDestination currentDestination = navigateSafe.getCurrentDestination();
        if (currentDestination == null || (navAction = currentDestination.getAction(destResId)) == null) {
            navAction = navigateSafe.getGraph().getAction(destResId);
        }
        if (navAction != null) {
            NavAction action = navAction;
            NavDestination currentDestination2 = navigateSafe.getCurrentDestination();
            if (currentDestination2 == null || currentDestination2.getId() != action.getDestinationId()) {
                navigateSafe.navigate(destResId, args);
            }
        }
    }
}
