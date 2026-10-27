package androidx.paging;

import com.visualon.OSMPUtils.voOSType;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: Separators.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a=\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0002\u0010\n\u001ak\u0010\u000b\u001a\u00020\f\"\b\b\u0000\u0010\r*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u0002H\r*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u00010\u000e2\b\u0010\u0004\u001a\u0004\u0018\u0001H\r2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u00012\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0002\u0010\u0011\u001aI\u0010\u000b\u001a\u00020\f\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u000e2\b\u0010\u0004\u001a\u0004\u0018\u0001H\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0000¢\u0006\u0002\u0010\u0012\u001ax\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u00150\u0014\"\b\b\u0000\u0010\u0002*\u0002H\r\"\b\b\u0001\u0010\r*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00172.\u0010\u0018\u001a*\b\u0001\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0019H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001ae\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\r0\u0001\"\b\b\u0000\u0010\r*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u0002H\r*\b\u0012\u0004\u0012\u0002H\u00020\u00012.\u0010\u0018\u001a*\b\u0001\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0019H\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"separatorPage", "Landroidx/paging/TransformablePage;", "T", "", "separator", "originalPageOffsets", "", "hintOriginalPageOffset", "", "hintOriginalIndex", "(Ljava/lang/Object;[III)Landroidx/paging/TransformablePage;", "addSeparatorPage", "", "R", "", "adjacentPageBefore", "adjacentPageAfter", "(Ljava/util/List;Ljava/lang/Object;Landroidx/paging/TransformablePage;Landroidx/paging/TransformablePage;II)V", "(Ljava/util/List;Ljava/lang/Object;[III)V", "insertEventSeparators", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PageEvent;", "terminalSeparatorType", "Landroidx/paging/TerminalSeparatorType;", "generator", "Lkotlin/Function3;", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/flow/Flow;Landroidx/paging/TerminalSeparatorType;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "insertInternalSeparators", "(Landroidx/paging/TransformablePage;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "paging-common"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class SeparatorsKt {

    /* JADX INFO: renamed from: androidx.paging.SeparatorsKt$insertInternalSeparators$1, reason: invalid class name */
    /* JADX INFO: compiled from: Separators.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.paging.SeparatorsKt", f = "Separators.kt", i = {0, 0, 0, 0, 0, 0}, l = {81}, m = "insertInternalSeparators", n = {"$this$insertInternalSeparators", "generator", "outputList", "outputIndices", "item", "i"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$2"})
    static final class AnonymousClass1<R, T extends R> extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SeparatorsKt.insertInternalSeparators(null, null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00d9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00da -> B:31:0x00e3). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <R, T extends R> java.lang.Object insertInternalSeparators(androidx.paging.TransformablePage<T> r12, kotlin.jvm.functions.Function3<? super T, ? super T, ? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object> r13, kotlin.coroutines.Continuation<? super androidx.paging.TransformablePage<R>> r14) {
        /*
            Method dump skipped, instruction units count: 291
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SeparatorsKt.insertInternalSeparators(androidx.paging.TransformablePage, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final <T> TransformablePage<T> separatorPage(T separator, int[] originalPageOffsets, int i, int i2) {
        Intrinsics.checkNotNullParameter(separator, "separator");
        Intrinsics.checkNotNullParameter(originalPageOffsets, "originalPageOffsets");
        return new TransformablePage<>(originalPageOffsets, CollectionsKt__CollectionsJVMKt.listOf(separator), i, CollectionsKt__CollectionsJVMKt.listOf(Integer.valueOf(i2)));
    }

    public static final <T> void addSeparatorPage(List<TransformablePage<T>> list, T t, int[] originalPageOffsets, int i, int i2) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(originalPageOffsets, "originalPageOffsets");
        if (t == null) {
            return;
        }
        list.add(separatorPage(t, originalPageOffsets, i, i2));
    }

    public static final <R, T extends R> void addSeparatorPage(List<TransformablePage<R>> list, R r, TransformablePage<T> transformablePage, TransformablePage<T> transformablePage2, int i, int i2) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        int[] originalPageOffsets = transformablePage == null ? null : transformablePage.getOriginalPageOffsets();
        int[] originalPageOffsets2 = transformablePage2 != null ? transformablePage2.getOriginalPageOffsets() : null;
        if (originalPageOffsets != null && originalPageOffsets2 != null) {
            originalPageOffsets = CollectionsKt___CollectionsKt.toIntArray(CollectionsKt___CollectionsKt.sorted(ArraysKt___ArraysKt.distinct(ArraysKt___ArraysJvmKt.plus(originalPageOffsets, originalPageOffsets2))));
        } else if (originalPageOffsets == null && originalPageOffsets2 != null) {
            originalPageOffsets = originalPageOffsets2;
        } else if (originalPageOffsets == null || originalPageOffsets2 != null) {
            throw new IllegalArgumentException("Separator page expected adjacentPageBefore or adjacentPageAfter, but both were null.");
        }
        addSeparatorPage(list, r, originalPageOffsets, i, i2);
    }

    public static final <T extends R, R> Flow<PageEvent<R>> insertEventSeparators(final Flow<? extends PageEvent<T>> flow, TerminalSeparatorType terminalSeparatorType, Function3<? super T, ? super T, ? super Continuation<? super R>, ? extends Object> generator) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(generator, "generator");
        final SeparatorState separatorState = new SeparatorState(terminalSeparatorType, new SeparatorsKt$insertEventSeparators$separatorState$1(generator, null));
        return new Flow<PageEvent<R>>() { // from class: androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1

            /* JADX INFO: Add missing generic type declarations: [T] */
            /* JADX INFO: renamed from: androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: Collect.kt */
            @Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\b"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$lambda-1$$inlined$collect$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 1, mv = {1, 5, 1}, xi = 48)
            public static final class AnonymousClass2<T> implements FlowCollector<PageEvent<T>> {
                final /* synthetic */ SeparatorState $separatorState$inlined;
                final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;

                /* JADX INFO: renamed from: androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
                @DebugMetadata(c = "androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1$2", f = "Separators.kt", i = {}, l = {voOSType.VOOSMP_PID_PREFERENCE, voOSType.VOOSMP_PID_PREFERENCE}, m = "emit", n = {}, s = {})
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

                public AnonymousClass2(FlowCollector flowCollector, SeparatorState separatorState) {
                    this.$this_unsafeFlow$inlined = flowCollector;
                    this.$separatorState$inlined = separatorState;
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
                        boolean r0 = r8 instanceof androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1$2$1 r0 = (androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1$2$1 r0 = new androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1$2$1
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
                        androidx.paging.SeparatorState r2 = r6.$separatorState$inlined
                        r0.L$0 = r8
                        r0.label = r4
                        java.lang.Object r7 = r2.onEvent(r7, r0)
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
                    throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SeparatorsKt$insertEventSeparators$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object objCollect = flow.collect(new AnonymousClass2(flowCollector, separatorState), continuation);
                return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
    }
}
