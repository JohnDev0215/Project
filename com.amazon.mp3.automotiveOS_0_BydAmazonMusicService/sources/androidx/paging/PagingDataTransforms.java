package androidx.paging;

import androidx.annotation.CheckResult;
import androidx.mediarouter.media.MediaRouter;
import com.amazon.music.find.utils.PageUriUtils;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: PagingDataTransforms.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a>\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\b0\u0007H\u0007\u001aN\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001aN\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\r*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u000f0\u0007H\u0007\u001a^\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\r*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012(\u0010\u000e\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u000f0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a9\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u0002H\u0002H\u0007¢\u0006\u0002\u0010\u0014\u001a9\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u0002H\u0002H\u0007¢\u0006\u0002\u0010\u0014\u001a^\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\r0\u0001\"\b\b\u0000\u0010\r*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u0002H\r*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00052\u001e\u0010\u0017\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\r0\tH\u0007\u001an\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\r0\u0001\"\b\b\u0000\u0010\u0002*\u0002H\r\"\b\b\u0001\u0010\r*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u0011\u001a\u00020\u00122.\u0010\u0017\u001a*\b\u0001\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H\r0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0018H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001aH\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\r0\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\r*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\r0\u0007H\u0007\u001aX\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\r0\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\r*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\"\u0010\u000e\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001ag\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\r*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u000120\b\u0004\u0010\u000e\u001a*\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u001b\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u001b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\tH\u0082\bø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {PageUriUtils.FILTER_QUERY_PARAM, "Landroidx/paging/PagingData;", "T", "", "executor", "Ljava/util/concurrent/Executor;", "predicate", "Lkotlin/Function1;", "", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Landroidx/paging/PagingData;Lkotlin/jvm/functions/Function2;)Landroidx/paging/PagingData;", "flatMap", "R", "transform", "", "insertFooterItem", "terminalSeparatorType", "Landroidx/paging/TerminalSeparatorType;", "item", "(Landroidx/paging/PagingData;Landroidx/paging/TerminalSeparatorType;Ljava/lang/Object;)Landroidx/paging/PagingData;", "insertHeaderItem", "insertSeparators", "generator", "Lkotlin/Function3;", "(Landroidx/paging/PagingData;Landroidx/paging/TerminalSeparatorType;Lkotlin/jvm/functions/Function3;)Landroidx/paging/PagingData;", "map", "Landroidx/paging/PageEvent;", "paging-common"}, k = 2, mv = {1, 5, 1}, xi = 48)
@JvmName(name = "PagingDataTransforms")
public final class PagingDataTransforms {
    @CheckResult
    @JvmOverloads
    public static final <T> PagingData<T> insertFooterItem(PagingData<T> pagingData, T item) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(item, "item");
        return insertFooterItem$default(pagingData, null, item, 1, null);
    }

    @CheckResult
    @JvmOverloads
    public static final <T> PagingData<T> insertHeaderItem(PagingData<T> pagingData, T item) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(item, "item");
        return insertHeaderItem$default(pagingData, null, item, 1, null);
    }

    @CheckResult
    @JvmOverloads
    public static final <R, T extends R> PagingData<R> insertSeparators(PagingData<T> pagingData, Executor executor, Function2<? super T, ? super T, ? extends R> generator) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(generator, "generator");
        return insertSeparators$default(pagingData, null, executor, generator, 1, null);
    }

    @CheckResult
    @JvmName(name = PageUriUtils.FILTER_QUERY_PARAM)
    public static final <T> PagingData<T> filter(PagingData<T> pagingData, final Executor executor, final Function1<? super T, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        final Flow<PageEvent<T>> flow$paging_common = pagingData.getFlow$paging_common();
        return new PagingData<>(new Flow<PageEvent<T>>() { // from class: androidx.paging.PagingDataTransforms$filter$$inlined$transform$2

            /* JADX INFO: renamed from: androidx.paging.PagingDataTransforms$filter$$inlined$transform$2$2, reason: invalid class name */
            /* JADX INFO: compiled from: Collect.kt */
            @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\t"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$lambda-1$$inlined$collect$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2", "androidx/paging/PagingDataTransforms$transform$$inlined$map$1$2"}, k = 1, mv = {1, 5, 1}, xi = 48)
            public static final class AnonymousClass2 implements FlowCollector<PageEvent<T>> {
                final /* synthetic */ Executor $executor$inlined;
                final /* synthetic */ Function1 $predicate$inlined;
                final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;

                /* JADX INFO: renamed from: androidx.paging.PagingDataTransforms$filter$$inlined$transform$2$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
                @DebugMetadata(c = "androidx.paging.PagingDataTransforms$filter$$inlined$transform$2$2", f = "PagingDataTransforms.kt", i = {}, l = {138, 138}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public java.lang.Object emit(java.lang.Object r10, kotlin.coroutines.Continuation r11) throws java.lang.Throwable {
                    /*
                        r9 = this;
                        boolean r0 = r11 instanceof androidx.paging.PagingDataTransforms$filter$$inlined$transform$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r11
                        androidx.paging.PagingDataTransforms$filter$$inlined$transform$2$2$1 r0 = (androidx.paging.PagingDataTransforms$filter$$inlined$transform$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        androidx.paging.PagingDataTransforms$filter$$inlined$transform$2$2$1 r0 = new androidx.paging.PagingDataTransforms$filter$$inlined$transform$2$2$1
                        r0.<init>(r11)
                    L18:
                        java.lang.Object r11 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 0
                        r4 = 2
                        r5 = 1
                        if (r2 == 0) goto L3d
                        if (r2 == r5) goto L35
                        if (r2 != r4) goto L2d
                        kotlin.ResultKt.throwOnFailure(r11)
                        goto L6a
                    L2d:
                        java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                        java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                        r10.<init>(r11)
                        throw r10
                    L35:
                        java.lang.Object r10 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                        kotlin.ResultKt.throwOnFailure(r11)
                        goto L5f
                    L3d:
                        kotlin.ResultKt.throwOnFailure(r11)
                        kotlinx.coroutines.flow.FlowCollector r11 = r9.$this_unsafeFlow$inlined
                        androidx.paging.PageEvent r10 = (androidx.paging.PageEvent) r10
                        java.util.concurrent.Executor r2 = r9.$executor$inlined
                        kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.ExecutorsKt.from(r2)
                        androidx.paging.PagingDataTransforms$filter$2$1 r6 = new androidx.paging.PagingDataTransforms$filter$2$1
                        kotlin.jvm.functions.Function1 r7 = r9.$predicate$inlined
                        r6.<init>(r10, r7, r3)
                        r0.L$0 = r11
                        r0.label = r5
                        java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r2, r6, r0)
                        if (r10 != r1) goto L5c
                        return r1
                    L5c:
                        r8 = r11
                        r11 = r10
                        r10 = r8
                    L5f:
                        r0.L$0 = r3
                        r0.label = r4
                        java.lang.Object r10 = r10.emit(r11, r0)
                        if (r10 != r1) goto L6a
                        return r1
                    L6a:
                        kotlin.Unit r10 = kotlin.Unit.INSTANCE
                        return r10
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataTransforms$filter$$inlined$transform$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }

                public AnonymousClass2(FlowCollector flowCollector, Executor executor, Function1 function1) {
                    this.$this_unsafeFlow$inlined = flowCollector;
                    this.$executor$inlined = executor;
                    this.$predicate$inlined = function1;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object objCollect = flow$paging_common.collect(new AnonymousClass2(flowCollector, executor, predicate), continuation);
                return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, pagingData.getReceiver());
    }

    @CheckResult
    public static final /* synthetic */ PagingData filter(PagingData pagingData, final Function2 predicate) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        final Flow flow$paging_common = pagingData.getFlow$paging_common();
        return new PagingData(new Flow() { // from class: androidx.paging.PagingDataTransforms$filter$$inlined$transform$1

            /* JADX INFO: renamed from: androidx.paging.PagingDataTransforms$filter$$inlined$transform$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: Collect.kt */
            @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\t"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$lambda-1$$inlined$collect$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2", "androidx/paging/PagingDataTransforms$transform$$inlined$map$1$2"}, k = 1, mv = {1, 5, 1}, xi = 48)
            public static final class AnonymousClass2 implements FlowCollector {
                final /* synthetic */ Function2 $predicate$inlined;
                final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;

                /* JADX INFO: renamed from: androidx.paging.PagingDataTransforms$filter$$inlined$transform$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
                @DebugMetadata(c = "androidx.paging.PagingDataTransforms$filter$$inlined$transform$1$2", f = "PagingDataTransforms.kt", i = {}, l = {138, 138}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof androidx.paging.PagingDataTransforms$filter$$inlined$transform$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        androidx.paging.PagingDataTransforms$filter$$inlined$transform$1$2$1 r0 = (androidx.paging.PagingDataTransforms$filter$$inlined$transform$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        androidx.paging.PagingDataTransforms$filter$$inlined$transform$1$2$1 r0 = new androidx.paging.PagingDataTransforms$filter$$inlined$transform$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L3c
                        if (r2 == r4) goto L34
                        if (r2 != r3) goto L2c
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L5f
                    L2c:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L34:
                        java.lang.Object r7 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L53
                    L3c:
                        kotlin.ResultKt.throwOnFailure(r8)
                        kotlinx.coroutines.flow.FlowCollector r8 = r6.$this_unsafeFlow$inlined
                        androidx.paging.PageEvent r7 = (androidx.paging.PageEvent) r7
                        kotlin.jvm.functions.Function2 r2 = r6.$predicate$inlined
                        r0.L$0 = r8
                        r0.label = r4
                        java.lang.Object r7 = r7.filter(r2, r0)
                        if (r7 != r1) goto L50
                        return r1
                    L50:
                        r5 = r8
                        r8 = r7
                        r7 = r5
                    L53:
                        r2 = 0
                        r0.L$0 = r2
                        r0.label = r3
                        java.lang.Object r7 = r7.emit(r8, r0)
                        if (r7 != r1) goto L5f
                        return r1
                    L5f:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataTransforms$filter$$inlined$transform$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }

                public AnonymousClass2(FlowCollector flowCollector, Function2 function2) {
                    this.$this_unsafeFlow$inlined = flowCollector;
                    this.$predicate$inlined = function2;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object objCollect = flow$paging_common.collect(new AnonymousClass2(flowCollector, predicate), continuation);
                return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, pagingData.getReceiver());
    }

    @CheckResult
    public static final <T, R> PagingData<R> flatMap(PagingData<T> pagingData, final Executor executor, final Function1<? super T, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(transform, "transform");
        final Flow<PageEvent<T>> flow$paging_common = pagingData.getFlow$paging_common();
        return new PagingData<>(new Flow<PageEvent<R>>() { // from class: androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2

            /* JADX INFO: Add missing generic type declarations: [T] */
            /* JADX INFO: renamed from: androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2$2, reason: invalid class name */
            /* JADX INFO: compiled from: Collect.kt */
            @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\t"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$lambda-1$$inlined$collect$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2", "androidx/paging/PagingDataTransforms$transform$$inlined$map$1$2"}, k = 1, mv = {1, 5, 1}, xi = 48)
            public static final class AnonymousClass2<T> implements FlowCollector<PageEvent<T>> {
                final /* synthetic */ Executor $executor$inlined;
                final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
                final /* synthetic */ Function1 $transform$inlined$1;

                /* JADX INFO: renamed from: androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
                @DebugMetadata(c = "androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2$2", f = "PagingDataTransforms.kt", i = {}, l = {138, 138}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public java.lang.Object emit(java.lang.Object r10, kotlin.coroutines.Continuation r11) throws java.lang.Throwable {
                    /*
                        r9 = this;
                        boolean r0 = r11 instanceof androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r11
                        androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2$2$1 r0 = (androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2$2$1 r0 = new androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2$2$1
                        r0.<init>(r11)
                    L18:
                        java.lang.Object r11 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 0
                        r4 = 2
                        r5 = 1
                        if (r2 == 0) goto L3d
                        if (r2 == r5) goto L35
                        if (r2 != r4) goto L2d
                        kotlin.ResultKt.throwOnFailure(r11)
                        goto L6a
                    L2d:
                        java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                        java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                        r10.<init>(r11)
                        throw r10
                    L35:
                        java.lang.Object r10 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                        kotlin.ResultKt.throwOnFailure(r11)
                        goto L5f
                    L3d:
                        kotlin.ResultKt.throwOnFailure(r11)
                        kotlinx.coroutines.flow.FlowCollector r11 = r9.$this_unsafeFlow$inlined
                        androidx.paging.PageEvent r10 = (androidx.paging.PageEvent) r10
                        java.util.concurrent.Executor r2 = r9.$executor$inlined
                        kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.ExecutorsKt.from(r2)
                        androidx.paging.PagingDataTransforms$flatMap$2$1 r6 = new androidx.paging.PagingDataTransforms$flatMap$2$1
                        kotlin.jvm.functions.Function1 r7 = r9.$transform$inlined$1
                        r6.<init>(r10, r7, r3)
                        r0.L$0 = r11
                        r0.label = r5
                        java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r2, r6, r0)
                        if (r10 != r1) goto L5c
                        return r1
                    L5c:
                        r8 = r11
                        r11 = r10
                        r10 = r8
                    L5f:
                        r0.L$0 = r3
                        r0.label = r4
                        java.lang.Object r10 = r10.emit(r11, r0)
                        if (r10 != r1) goto L6a
                        return r1
                    L6a:
                        kotlin.Unit r10 = kotlin.Unit.INSTANCE
                        return r10
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }

                public AnonymousClass2(FlowCollector flowCollector, Executor executor, Function1 function1) {
                    this.$this_unsafeFlow$inlined = flowCollector;
                    this.$executor$inlined = executor;
                    this.$transform$inlined$1 = function1;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object objCollect = flow$paging_common.collect(new AnonymousClass2(flowCollector, executor, transform), continuation);
                return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, pagingData.getReceiver());
    }

    @CheckResult
    public static final /* synthetic */ PagingData flatMap(PagingData pagingData, final Function2 transform) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        final Flow flow$paging_common = pagingData.getFlow$paging_common();
        return new PagingData(new Flow() { // from class: androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1

            /* JADX INFO: renamed from: androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: Collect.kt */
            @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\t"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$lambda-1$$inlined$collect$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2", "androidx/paging/PagingDataTransforms$transform$$inlined$map$1$2"}, k = 1, mv = {1, 5, 1}, xi = 48)
            public static final class AnonymousClass2 implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
                final /* synthetic */ Function2 $transform$inlined$1;

                /* JADX INFO: renamed from: androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
                @DebugMetadata(c = "androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1$2", f = "PagingDataTransforms.kt", i = {}, l = {138, 138}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1$2$1 r0 = (androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1$2$1 r0 = new androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L3c
                        if (r2 == r4) goto L34
                        if (r2 != r3) goto L2c
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L5f
                    L2c:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L34:
                        java.lang.Object r7 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L53
                    L3c:
                        kotlin.ResultKt.throwOnFailure(r8)
                        kotlinx.coroutines.flow.FlowCollector r8 = r6.$this_unsafeFlow$inlined
                        androidx.paging.PageEvent r7 = (androidx.paging.PageEvent) r7
                        kotlin.jvm.functions.Function2 r2 = r6.$transform$inlined$1
                        r0.L$0 = r8
                        r0.label = r4
                        java.lang.Object r7 = r7.flatMap(r2, r0)
                        if (r7 != r1) goto L50
                        return r1
                    L50:
                        r5 = r8
                        r8 = r7
                        r7 = r5
                    L53:
                        r2 = 0
                        r0.L$0 = r2
                        r0.label = r3
                        java.lang.Object r7 = r7.emit(r8, r0)
                        if (r7 != r1) goto L5f
                        return r1
                    L5f:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataTransforms$flatMap$$inlined$transform$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }

                public AnonymousClass2(FlowCollector flowCollector, Function2 function2) {
                    this.$this_unsafeFlow$inlined = flowCollector;
                    this.$transform$inlined$1 = function2;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object objCollect = flow$paging_common.collect(new AnonymousClass2(flowCollector, transform), continuation);
                return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, pagingData.getReceiver());
    }

    @CheckResult
    public static final <T, R> PagingData<R> map(PagingData<T> pagingData, final Executor executor, final Function1<? super T, ? extends R> transform) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(transform, "transform");
        final Flow<PageEvent<T>> flow$paging_common = pagingData.getFlow$paging_common();
        return new PagingData<>(new Flow<PageEvent<R>>() { // from class: androidx.paging.PagingDataTransforms$map$$inlined$transform$2

            /* JADX INFO: Add missing generic type declarations: [T] */
            /* JADX INFO: renamed from: androidx.paging.PagingDataTransforms$map$$inlined$transform$2$2, reason: invalid class name */
            /* JADX INFO: compiled from: Collect.kt */
            @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\t"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$lambda-1$$inlined$collect$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2", "androidx/paging/PagingDataTransforms$transform$$inlined$map$1$2"}, k = 1, mv = {1, 5, 1}, xi = 48)
            public static final class AnonymousClass2<T> implements FlowCollector<PageEvent<T>> {
                final /* synthetic */ Executor $executor$inlined;
                final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
                final /* synthetic */ Function1 $transform$inlined$1;

                /* JADX INFO: renamed from: androidx.paging.PagingDataTransforms$map$$inlined$transform$2$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
                @DebugMetadata(c = "androidx.paging.PagingDataTransforms$map$$inlined$transform$2$2", f = "PagingDataTransforms.kt", i = {}, l = {138, 138}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public java.lang.Object emit(java.lang.Object r10, kotlin.coroutines.Continuation r11) throws java.lang.Throwable {
                    /*
                        r9 = this;
                        boolean r0 = r11 instanceof androidx.paging.PagingDataTransforms$map$$inlined$transform$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r11
                        androidx.paging.PagingDataTransforms$map$$inlined$transform$2$2$1 r0 = (androidx.paging.PagingDataTransforms$map$$inlined$transform$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        androidx.paging.PagingDataTransforms$map$$inlined$transform$2$2$1 r0 = new androidx.paging.PagingDataTransforms$map$$inlined$transform$2$2$1
                        r0.<init>(r11)
                    L18:
                        java.lang.Object r11 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 0
                        r4 = 2
                        r5 = 1
                        if (r2 == 0) goto L3d
                        if (r2 == r5) goto L35
                        if (r2 != r4) goto L2d
                        kotlin.ResultKt.throwOnFailure(r11)
                        goto L6a
                    L2d:
                        java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                        java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                        r10.<init>(r11)
                        throw r10
                    L35:
                        java.lang.Object r10 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                        kotlin.ResultKt.throwOnFailure(r11)
                        goto L5f
                    L3d:
                        kotlin.ResultKt.throwOnFailure(r11)
                        kotlinx.coroutines.flow.FlowCollector r11 = r9.$this_unsafeFlow$inlined
                        androidx.paging.PageEvent r10 = (androidx.paging.PageEvent) r10
                        java.util.concurrent.Executor r2 = r9.$executor$inlined
                        kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.ExecutorsKt.from(r2)
                        androidx.paging.PagingDataTransforms$map$2$1 r6 = new androidx.paging.PagingDataTransforms$map$2$1
                        kotlin.jvm.functions.Function1 r7 = r9.$transform$inlined$1
                        r6.<init>(r10, r7, r3)
                        r0.L$0 = r11
                        r0.label = r5
                        java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r2, r6, r0)
                        if (r10 != r1) goto L5c
                        return r1
                    L5c:
                        r8 = r11
                        r11 = r10
                        r10 = r8
                    L5f:
                        r0.L$0 = r3
                        r0.label = r4
                        java.lang.Object r10 = r10.emit(r11, r0)
                        if (r10 != r1) goto L6a
                        return r1
                    L6a:
                        kotlin.Unit r10 = kotlin.Unit.INSTANCE
                        return r10
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataTransforms$map$$inlined$transform$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }

                public AnonymousClass2(FlowCollector flowCollector, Executor executor, Function1 function1) {
                    this.$this_unsafeFlow$inlined = flowCollector;
                    this.$executor$inlined = executor;
                    this.$transform$inlined$1 = function1;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object objCollect = flow$paging_common.collect(new AnonymousClass2(flowCollector, executor, transform), continuation);
                return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, pagingData.getReceiver());
    }

    @CheckResult
    public static final /* synthetic */ PagingData map(PagingData pagingData, final Function2 transform) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        final Flow flow$paging_common = pagingData.getFlow$paging_common();
        return new PagingData(new Flow() { // from class: androidx.paging.PagingDataTransforms$map$$inlined$transform$1

            /* JADX INFO: renamed from: androidx.paging.PagingDataTransforms$map$$inlined$transform$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: Collect.kt */
            @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\t"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$lambda-1$$inlined$collect$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2", "androidx/paging/PagingDataTransforms$transform$$inlined$map$1$2"}, k = 1, mv = {1, 5, 1}, xi = 48)
            public static final class AnonymousClass2 implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
                final /* synthetic */ Function2 $transform$inlined$1;

                /* JADX INFO: renamed from: androidx.paging.PagingDataTransforms$map$$inlined$transform$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
                @DebugMetadata(c = "androidx.paging.PagingDataTransforms$map$$inlined$transform$1$2", f = "PagingDataTransforms.kt", i = {}, l = {138, 138}, m = "emit", n = {}, s = {})
                public static final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof androidx.paging.PagingDataTransforms$map$$inlined$transform$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        androidx.paging.PagingDataTransforms$map$$inlined$transform$1$2$1 r0 = (androidx.paging.PagingDataTransforms$map$$inlined$transform$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        androidx.paging.PagingDataTransforms$map$$inlined$transform$1$2$1 r0 = new androidx.paging.PagingDataTransforms$map$$inlined$transform$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L3c
                        if (r2 == r4) goto L34
                        if (r2 != r3) goto L2c
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L5f
                    L2c:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L34:
                        java.lang.Object r7 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L53
                    L3c:
                        kotlin.ResultKt.throwOnFailure(r8)
                        kotlinx.coroutines.flow.FlowCollector r8 = r6.$this_unsafeFlow$inlined
                        androidx.paging.PageEvent r7 = (androidx.paging.PageEvent) r7
                        kotlin.jvm.functions.Function2 r2 = r6.$transform$inlined$1
                        r0.L$0 = r8
                        r0.label = r4
                        java.lang.Object r7 = r7.map(r2, r0)
                        if (r7 != r1) goto L50
                        return r1
                    L50:
                        r5 = r8
                        r8 = r7
                        r7 = r5
                    L53:
                        r2 = 0
                        r0.L$0 = r2
                        r0.label = r3
                        java.lang.Object r7 = r7.emit(r8, r0)
                        if (r7 != r1) goto L5f
                        return r1
                    L5f:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataTransforms$map$$inlined$transform$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }

                public AnonymousClass2(FlowCollector flowCollector, Function2 function2) {
                    this.$this_unsafeFlow$inlined = flowCollector;
                    this.$transform$inlined$1 = function2;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object objCollect = flow$paging_common.collect(new AnonymousClass2(flowCollector, transform), continuation);
                return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, pagingData.getReceiver());
    }

    private static final <T, R> PagingData<R> transform(PagingData<T> pagingData, Function2<? super PageEvent<T>, ? super Continuation<? super PageEvent<R>>, ? extends Object> function2) {
        return new PagingData<>(new PagingDataTransforms$transform$$inlined$map$1(pagingData.getFlow$paging_common(), function2), pagingData.getReceiver());
    }

    public static /* synthetic */ PagingData insertSeparators$default(PagingData pagingData, TerminalSeparatorType terminalSeparatorType, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            terminalSeparatorType = TerminalSeparatorType.FULLY_COMPLETE;
        }
        return insertSeparators(pagingData, terminalSeparatorType, function3);
    }

    @CheckResult
    public static final /* synthetic */ PagingData insertSeparators(PagingData pagingData, TerminalSeparatorType terminalSeparatorType, Function3 generator) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(generator, "generator");
        return new PagingData(SeparatorsKt.insertEventSeparators(pagingData.getFlow$paging_common(), terminalSeparatorType, generator), pagingData.getReceiver());
    }

    public static /* synthetic */ PagingData insertSeparators$default(PagingData pagingData, TerminalSeparatorType terminalSeparatorType, Executor executor, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            terminalSeparatorType = TerminalSeparatorType.FULLY_COMPLETE;
        }
        return insertSeparators(pagingData, terminalSeparatorType, executor, function2);
    }

    /* JADX INFO: Add missing generic type declarations: [R, T] */
    /* JADX INFO: renamed from: androidx.paging.PagingDataTransforms$insertSeparators$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PagingDataTransforms.kt */
    @Metadata(bv = {}, d1 = {"\u0000\b\n\u0002\u0010\u0000\n\u0002\b\u0005\u0010\u0005\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00028\u00002\b\u0010\u0003\u001a\u0004\u0018\u00018\u00012\b\u0010\u0004\u001a\u0004\u0018\u00018\u0001H\u008a@"}, d2 = {"", "R", "T", "before", "after", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.PagingDataTransforms$insertSeparators$1", f = "PagingDataTransforms.kt", i = {}, l = {MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED}, m = "invokeSuspend", n = {}, s = {})
    static final class C02451<R, T> extends SuspendLambda implements Function3<T, T, Continuation<? super R>, Object> {
        final /* synthetic */ Executor $executor;
        final /* synthetic */ Function2<T, T, R> $generator;
        /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C02451(Executor executor, Function2<? super T, ? super T, ? extends R> function2, Continuation<? super C02451> continuation) {
            super(3, continuation);
            this.$executor = executor;
            this.$generator = function2;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(T t, T t2, Continuation<? super R> continuation) {
            C02451 c02451 = new C02451(this.$executor, this.$generator, continuation);
            c02451.L$0 = t;
            c02451.L$1 = t2;
            return c02451.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.paging.PagingDataTransforms$insertSeparators$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: PagingDataTransforms.kt */
        @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0004\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00028\u0000*\u00020\u0003H\u008a@"}, d2 = {"", "R", "T", "Lkotlinx/coroutines/CoroutineScope;", "<anonymous>"}, k = 3, mv = {1, 5, 1})
        @DebugMetadata(c = "androidx.paging.PagingDataTransforms$insertSeparators$1$1", f = "PagingDataTransforms.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00351 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
            final /* synthetic */ T $after;
            final /* synthetic */ T $before;
            final /* synthetic */ Function2<T, T, R> $generator;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00351(Function2<? super T, ? super T, ? extends R> function2, T t, T t2, Continuation<? super C00351> continuation) {
                super(2, continuation);
                this.$generator = function2;
                this.$before = t;
                this.$after = t2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00351(this.$generator, this.$before, this.$after, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super R> continuation) {
                return ((C00351) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return this.$generator.mo4invoke(this.$before, this.$after);
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Object obj2 = this.L$0;
                Object obj3 = this.L$1;
                CoroutineDispatcher coroutineDispatcherFrom = ExecutorsKt.from(this.$executor);
                C00351 c00351 = new C00351(this.$generator, obj2, obj3, null);
                this.L$0 = null;
                this.label = 1;
                obj = BuildersKt.withContext(coroutineDispatcherFrom, c00351, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    @CheckResult
    @JvmOverloads
    public static final <R, T extends R> PagingData<R> insertSeparators(PagingData<T> pagingData, TerminalSeparatorType terminalSeparatorType, Executor executor, Function2<? super T, ? super T, ? extends R> generator) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(generator, "generator");
        return insertSeparators(pagingData, terminalSeparatorType, new C02451(executor, generator, null));
    }

    public static /* synthetic */ PagingData insertHeaderItem$default(PagingData pagingData, TerminalSeparatorType terminalSeparatorType, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            terminalSeparatorType = TerminalSeparatorType.FULLY_COMPLETE;
        }
        return insertHeaderItem(pagingData, terminalSeparatorType, obj);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.paging.PagingDataTransforms$insertHeaderItem$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PagingDataTransforms.kt */
    @Metadata(bv = {}, d1 = {"\u0000\b\n\u0002\u0010\u0000\n\u0002\b\u0004\u0010\u0004\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0001*\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00018\u00002\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000H\u008a@"}, d2 = {"", "T", "before", "<anonymous parameter 1>", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.PagingDataTransforms$insertHeaderItem$1", f = "PagingDataTransforms.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02441<T> extends SuspendLambda implements Function3<T, T, Continuation<? super T>, Object> {
        final /* synthetic */ T $item;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02441(T t, Continuation<? super C02441> continuation) {
            super(3, continuation);
            this.$item = t;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(T t, T t2, Continuation<? super T> continuation) {
            C02441 c02441 = new C02441(this.$item, continuation);
            c02441.L$0 = t;
            return c02441.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (this.L$0 == null) {
                return this.$item;
            }
            return null;
        }
    }

    @CheckResult
    @JvmOverloads
    public static final <T> PagingData<T> insertHeaderItem(PagingData<T> pagingData, TerminalSeparatorType terminalSeparatorType, T item) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(item, "item");
        return insertSeparators(pagingData, terminalSeparatorType, new C02441(item, null));
    }

    public static /* synthetic */ PagingData insertFooterItem$default(PagingData pagingData, TerminalSeparatorType terminalSeparatorType, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            terminalSeparatorType = TerminalSeparatorType.FULLY_COMPLETE;
        }
        return insertFooterItem(pagingData, terminalSeparatorType, obj);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.paging.PagingDataTransforms$insertFooterItem$1, reason: invalid class name */
    /* JADX INFO: compiled from: PagingDataTransforms.kt */
    @Metadata(bv = {}, d1 = {"\u0000\b\n\u0002\u0010\u0000\n\u0002\b\u0004\u0010\u0004\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0001*\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00018\u00002\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000H\u008a@"}, d2 = {"", "T", "<anonymous parameter 0>", "after", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.PagingDataTransforms$insertFooterItem$1", f = "PagingDataTransforms.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1<T> extends SuspendLambda implements Function3<T, T, Continuation<? super T>, Object> {
        final /* synthetic */ T $item;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(T t, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$item = t;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(T t, T t2, Continuation<? super T> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$item, continuation);
            anonymousClass1.L$0 = t2;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (this.L$0 == null) {
                return this.$item;
            }
            return null;
        }
    }

    @CheckResult
    @JvmOverloads
    public static final <T> PagingData<T> insertFooterItem(PagingData<T> pagingData, TerminalSeparatorType terminalSeparatorType, T item) {
        Intrinsics.checkNotNullParameter(pagingData, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(item, "item");
        return insertSeparators(pagingData, terminalSeparatorType, new AnonymousClass1(item, null));
    }
}
