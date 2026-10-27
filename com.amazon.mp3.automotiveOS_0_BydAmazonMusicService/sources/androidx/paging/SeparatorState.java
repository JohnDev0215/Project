package androidx.paging;

import androidx.core.app.NotificationCompat;
import androidx.paging.LoadState;
import androidx.paging.PageEvent;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: Separators.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u0002H\u00012\u00020\u0002B^\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012L\u0010\u0006\u001aH\b\u0001\u0012\u0015\u0012\u0013\u0018\u00018\u0001¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00018\u0001¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0007ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u001a\u0010:\u001a\b\u0012\u0004\u0012\u00028\u00000;2\f\u0010<\u001a\b\u0012\u0004\u0012\u00028\u00010;J%\u0010=\u001a\b\u0012\u0004\u0012\u00028\u00000>2\f\u0010<\u001a\b\u0012\u0004\u0012\u00028\u00010>H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010?J%\u0010@\u001a\b\u0012\u0004\u0012\u00028\u00000A2\f\u0010<\u001a\b\u0012\u0004\u0012\u00028\u00010AH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010BJ%\u0010C\u001a\b\u0012\u0004\u0012\u00028\u00000>2\f\u0010<\u001a\b\u0012\u0004\u0012\u00028\u00010DH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010EJ&\u0010F\u001a\b\u0012\u0004\u0012\u0002H\u00030%\"\b\b\u0002\u0010\u0003*\u00020\u00022\f\u0010G\u001a\b\u0012\u0004\u0012\u0002H\u00030%H\u0002J\u0016\u0010H\u001a\b\u0012\u0004\u0012\u00028\u00000A*\b\u0012\u0004\u0012\u00028\u00010AJ\"\u0010I\u001a\u00020\u000f\"\b\b\u0002\u0010\u0003*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00030A2\u0006\u0010\u0004\u001a\u00020\u0005J\"\u0010J\u001a\u00020\u000f\"\b\b\u0002\u0010\u0003*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00030A2\u0006\u0010\u0004\u001a\u00020\u0005R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\\\u0010\u0006\u001aH\b\u0001\u0012\u0015\u0012\u0013\u0018\u00018\u0001¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00018\u0001¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0007ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001a\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0011\"\u0004\b\u001c\u0010\u0013R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010%0$¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010+\"\u0004\b0\u0010-R\u0011\u00101\u001a\u000202¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u001a\u00105\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0011\"\u0004\b7\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b8\u00109\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006K"}, d2 = {"Landroidx/paging/SeparatorState;", "R", "", "T", "terminalSeparatorType", "Landroidx/paging/TerminalSeparatorType;", "generator", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "before", "after", "Lkotlin/coroutines/Continuation;", "(Landroidx/paging/TerminalSeparatorType;Lkotlin/jvm/functions/Function3;)V", "endTerminalSeparatorDeferred", "", "getEndTerminalSeparatorDeferred", "()Z", "setEndTerminalSeparatorDeferred", "(Z)V", "footerAdded", "getFooterAdded", "setFooterAdded", "getGenerator", "()Lkotlin/jvm/functions/Function3;", "Lkotlin/jvm/functions/Function3;", "headerAdded", "getHeaderAdded", "setHeaderAdded", "mediatorStates", "Landroidx/paging/LoadStates;", "getMediatorStates", "()Landroidx/paging/LoadStates;", "setMediatorStates", "(Landroidx/paging/LoadStates;)V", "pageStash", "", "Landroidx/paging/TransformablePage;", "getPageStash", "()Ljava/util/List;", "placeholdersAfter", "", "getPlaceholdersAfter", "()I", "setPlaceholdersAfter", "(I)V", "placeholdersBefore", "getPlaceholdersBefore", "setPlaceholdersBefore", "sourceStates", "Landroidx/paging/MutableLoadStateCollection;", "getSourceStates", "()Landroidx/paging/MutableLoadStateCollection;", "startTerminalSeparatorDeferred", "getStartTerminalSeparatorDeferred", "setStartTerminalSeparatorDeferred", "getTerminalSeparatorType", "()Landroidx/paging/TerminalSeparatorType;", "onDrop", "Landroidx/paging/PageEvent$Drop;", NotificationCompat.CATEGORY_EVENT, "onEvent", "Landroidx/paging/PageEvent;", "(Landroidx/paging/PageEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onInsert", "Landroidx/paging/PageEvent$Insert;", "(Landroidx/paging/PageEvent$Insert;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onLoadStateUpdate", "Landroidx/paging/PageEvent$LoadStateUpdate;", "(Landroidx/paging/PageEvent$LoadStateUpdate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "transformablePageToStash", "originalPage", "asRType", "terminatesEnd", "terminatesStart", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
final class SeparatorState<R, T extends R> {
    private boolean endTerminalSeparatorDeferred;
    private boolean footerAdded;
    private final Function3<T, T, Continuation<? super R>, Object> generator;
    private boolean headerAdded;
    private LoadStates mediatorStates;
    private final List<TransformablePage<T>> pageStash;
    private int placeholdersAfter;
    private int placeholdersBefore;
    private final MutableLoadStateCollection sourceStates;
    private boolean startTerminalSeparatorDeferred;
    private final TerminalSeparatorType terminalSeparatorType;

    /* JADX INFO: compiled from: Separators.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TerminalSeparatorType.values().length];
            iArr[TerminalSeparatorType.FULLY_COMPLETE.ordinal()] = 1;
            iArr[TerminalSeparatorType.SOURCE_COMPLETE.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: androidx.paging.SeparatorState$onEvent$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Separators.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.paging.SeparatorState", f = "Separators.kt", i = {0, 1}, l = {213, 215}, m = "onEvent", n = {"this", "this"}, s = {"L$0", "L$0"})
    static final class C02501 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SeparatorState<R, T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02501(SeparatorState<R, T> separatorState, Continuation<? super C02501> continuation) {
            super(continuation);
            this.this$0 = separatorState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.onEvent(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.paging.SeparatorState$onInsert$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Separators.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.paging.SeparatorState", f = "Separators.kt", i = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9}, l = {305, 368, 380, 386, 398, 407, 429, 438, 451, 462}, m = "onInsert", n = {"this", NotificationCompat.CATEGORY_EVENT, "this", NotificationCompat.CATEGORY_EVENT, "outList", "stashOutList", "firstNonEmptyPage", "firstNonEmptyPageIndex", "lastNonEmptyPage", "lastNonEmptyPageIndex", "pageAfter", "eventTerminatesEnd", "eventEmpty", "this", NotificationCompat.CATEGORY_EVENT, "outList", "stashOutList", "firstNonEmptyPage", "firstNonEmptyPageIndex", "lastNonEmptyPage", "lastNonEmptyPageIndex", "eventTerminatesEnd", "eventEmpty", "this", NotificationCompat.CATEGORY_EVENT, "outList", "stashOutList", "firstNonEmptyPage", "firstNonEmptyPageIndex", "lastNonEmptyPage", "lastNonEmptyPageIndex", "lastStash", "eventTerminatesEnd", "eventEmpty", "this", NotificationCompat.CATEGORY_EVENT, "outList", "stashOutList", "firstNonEmptyPageIndex", "lastNonEmptyPage", "lastNonEmptyPageIndex", "eventTerminatesEnd", "eventEmpty", "this", NotificationCompat.CATEGORY_EVENT, "outList", "stashOutList", "lastNonEmptyPage", "lastNonEmptyPageIndex", "iterator$iv", "page", "pageBefore", "eventTerminatesEnd", "eventEmpty", "this", NotificationCompat.CATEGORY_EVENT, "outList", "stashOutList", "lastNonEmptyPage", "lastNonEmptyPageIndex", "iterator$iv", "page", "pageBefore", "eventTerminatesEnd", "eventEmpty", "this", NotificationCompat.CATEGORY_EVENT, "outList", "stashOutList", "lastNonEmptyPage", "lastNonEmptyPageIndex", "pageAfter", "eventTerminatesEnd", "eventEmpty", "this", NotificationCompat.CATEGORY_EVENT, "outList", "stashOutList", "lastNonEmptyPage", "eventTerminatesEnd", "eventEmpty", "pageIndex", "this", NotificationCompat.CATEGORY_EVENT, "outList", "stashOutList", "pageBefore"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "Z$0", "I$0", "I$3", "L$0", "L$1", "L$2", "L$3", "L$4"})
    static final class C02511 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ SeparatorState<R, T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02511(SeparatorState<R, T> separatorState, Continuation<? super C02511> continuation) {
            super(continuation);
            this.this$0 = separatorState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.onInsert(null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final PageEvent.Insert<R> asRType(PageEvent.Insert<T> insert) {
        Intrinsics.checkNotNullParameter(insert, "<this>");
        return insert;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SeparatorState(TerminalSeparatorType terminalSeparatorType, Function3<? super T, ? super T, ? super Continuation<? super R>, ? extends Object> generator) {
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        Intrinsics.checkNotNullParameter(generator, "generator");
        this.terminalSeparatorType = terminalSeparatorType;
        this.generator = generator;
        this.pageStash = new ArrayList();
        this.sourceStates = new MutableLoadStateCollection();
    }

    public final TerminalSeparatorType getTerminalSeparatorType() {
        return this.terminalSeparatorType;
    }

    public final Function3<T, T, Continuation<? super R>, Object> getGenerator() {
        return this.generator;
    }

    public final List<TransformablePage<T>> getPageStash() {
        return this.pageStash;
    }

    public final boolean getEndTerminalSeparatorDeferred() {
        return this.endTerminalSeparatorDeferred;
    }

    public final void setEndTerminalSeparatorDeferred(boolean z) {
        this.endTerminalSeparatorDeferred = z;
    }

    public final boolean getStartTerminalSeparatorDeferred() {
        return this.startTerminalSeparatorDeferred;
    }

    public final void setStartTerminalSeparatorDeferred(boolean z) {
        this.startTerminalSeparatorDeferred = z;
    }

    public final MutableLoadStateCollection getSourceStates() {
        return this.sourceStates;
    }

    public final LoadStates getMediatorStates() {
        return this.mediatorStates;
    }

    public final void setMediatorStates(LoadStates loadStates) {
        this.mediatorStates = loadStates;
    }

    public final int getPlaceholdersBefore() {
        return this.placeholdersBefore;
    }

    public final void setPlaceholdersBefore(int i) {
        this.placeholdersBefore = i;
    }

    public final int getPlaceholdersAfter() {
        return this.placeholdersAfter;
    }

    public final void setPlaceholdersAfter(int i) {
        this.placeholdersAfter = i;
    }

    public final boolean getFooterAdded() {
        return this.footerAdded;
    }

    public final void setFooterAdded(boolean z) {
        this.footerAdded = z;
    }

    public final boolean getHeaderAdded() {
        return this.headerAdded;
    }

    public final void setHeaderAdded(boolean z) {
        this.headerAdded = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r5v0, types: [androidx.paging.SeparatorState, androidx.paging.SeparatorState<R, T extends R>, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v22 */
    /* JADX WARN: Type inference failed for: r6v23 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9, types: [androidx.paging.SeparatorState] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object onEvent(androidx.paging.PageEvent<T> r6, kotlin.coroutines.Continuation<? super androidx.paging.PageEvent<R>> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof androidx.paging.SeparatorState.C02501
            if (r0 == 0) goto L13
            r0 = r7
            androidx.paging.SeparatorState$onEvent$1 r0 = (androidx.paging.SeparatorState.C02501) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.paging.SeparatorState$onEvent$1 r0 = new androidx.paging.SeparatorState$onEvent$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L40
            if (r2 == r4) goto L38
            if (r2 != r3) goto L30
            java.lang.Object r6 = r0.L$0
            androidx.paging.SeparatorState r6 = (androidx.paging.SeparatorState) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L76
        L30:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L38:
            java.lang.Object r6 = r0.L$0
            androidx.paging.SeparatorState r6 = (androidx.paging.SeparatorState) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L55
        L40:
            kotlin.ResultKt.throwOnFailure(r7)
            boolean r7 = r6 instanceof androidx.paging.PageEvent.Insert
            if (r7 == 0) goto L58
            androidx.paging.PageEvent$Insert r6 = (androidx.paging.PageEvent.Insert) r6
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r7 = r5.onInsert(r6, r0)
            if (r7 != r1) goto L54
            return r1
        L54:
            r6 = r5
        L55:
            androidx.paging.PageEvent r7 = (androidx.paging.PageEvent) r7
            goto L78
        L58:
            boolean r7 = r6 instanceof androidx.paging.PageEvent.Drop
            if (r7 == 0) goto L64
            androidx.paging.PageEvent$Drop r6 = (androidx.paging.PageEvent.Drop) r6
            androidx.paging.PageEvent$Drop r7 = r5.onDrop(r6)
            r6 = r5
            goto L78
        L64:
            boolean r7 = r6 instanceof androidx.paging.PageEvent.LoadStateUpdate
            if (r7 == 0) goto Lb3
            androidx.paging.PageEvent$LoadStateUpdate r6 = (androidx.paging.PageEvent.LoadStateUpdate) r6
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r7 = r5.onLoadStateUpdate(r6, r0)
            if (r7 != r1) goto L75
            return r1
        L75:
            r6 = r5
        L76:
            androidx.paging.PageEvent r7 = (androidx.paging.PageEvent) r7
        L78:
            boolean r0 = r6.getEndTerminalSeparatorDeferred()
            if (r0 == 0) goto L95
            java.util.List r0 = r6.getPageStash()
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L89
            goto L95
        L89:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "deferred endTerm, page stash should be empty"
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L95:
            boolean r0 = r6.getStartTerminalSeparatorDeferred()
            if (r0 == 0) goto Lb2
            java.util.List r6 = r6.getPageStash()
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto La6
            goto Lb2
        La6:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "deferred startTerm, page stash should be empty"
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        Lb2:
            return r7
        Lb3:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException
            r6.<init>()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SeparatorState.onEvent(androidx.paging.PageEvent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final <T> boolean terminatesStart(PageEvent.Insert<T> insert, TerminalSeparatorType terminalSeparatorType) {
        LoadState prepend;
        Intrinsics.checkNotNullParameter(insert, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        if (insert.getLoadType() == LoadType.APPEND) {
            return this.startTerminalSeparatorDeferred;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[terminalSeparatorType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return insert.getSourceLoadStates().getPrepend().getEndOfPaginationReached();
            }
            throw new NoWhenBranchMatchedException();
        }
        if (insert.getSourceLoadStates().getPrepend().getEndOfPaginationReached()) {
            LoadStates mediatorLoadStates = insert.getMediatorLoadStates();
            if (!((mediatorLoadStates == null || (prepend = mediatorLoadStates.getPrepend()) == null || prepend.getEndOfPaginationReached()) ? false : true)) {
                return true;
            }
        }
        return false;
    }

    public final <T> boolean terminatesEnd(PageEvent.Insert<T> insert, TerminalSeparatorType terminalSeparatorType) {
        LoadState append;
        Intrinsics.checkNotNullParameter(insert, "<this>");
        Intrinsics.checkNotNullParameter(terminalSeparatorType, "terminalSeparatorType");
        if (insert.getLoadType() == LoadType.PREPEND) {
            return this.endTerminalSeparatorDeferred;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[terminalSeparatorType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return insert.getSourceLoadStates().getAppend().getEndOfPaginationReached();
            }
            throw new NoWhenBranchMatchedException();
        }
        if (insert.getSourceLoadStates().getAppend().getEndOfPaginationReached()) {
            LoadStates mediatorLoadStates = insert.getMediatorLoadStates();
            if (!((mediatorLoadStates == null || (append = mediatorLoadStates.getAppend()) == null || append.getEndOfPaginationReached()) ? false : true)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Path cross not found for [B:125:0x0435, B:126:0x0437], limit reached: 277 */
    /* JADX WARN: Path cross not found for [B:155:0x054a, B:156:0x054c], limit reached: 277 */
    /* JADX WARN: Path cross not found for [B:28:0x0230, B:27:0x022e], limit reached: 277 */
    /* JADX WARN: Path cross not found for [B:36:0x0251, B:41:0x025e], limit reached: 277 */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0456  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x04a6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x04a7  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x04b5  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x04c1  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x05a0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x05a1  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x05c5  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x05d1  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0638  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x063d  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0648  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0666  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x06ae  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x06da A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:206:0x06db  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x06ed  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x06ef  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x06f7  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0766  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0768  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0770  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0779  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x079f  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x07e1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:235:0x07e2  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x07e8  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x07f0  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x07f3  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x07fa  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0802  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x080a  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0863  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0865  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x086e  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0877  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0895  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x089d  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x08d1  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0305  */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:139:0x04a7 -> B:140:0x04b0). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:206:0x06db -> B:207:0x06de). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:235:0x07e2 -> B:236:0x07e3). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object onInsert(androidx.paging.PageEvent.Insert<T> r32, kotlin.coroutines.Continuation<? super androidx.paging.PageEvent.Insert<R>> r33) {
        /*
            Method dump skipped, instruction units count: 2296
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SeparatorState.onInsert(androidx.paging.PageEvent$Insert, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final PageEvent.Drop<R> onDrop(PageEvent.Drop<T> event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.sourceStates.set(event.getLoadType(), LoadState.NotLoading.INSTANCE.getIncomplete$paging_common());
        LoadType loadType = event.getLoadType();
        LoadType loadType2 = LoadType.PREPEND;
        if (loadType == loadType2) {
            this.placeholdersBefore = event.getPlaceholdersRemaining();
            this.headerAdded = false;
        } else if (event.getLoadType() == LoadType.APPEND) {
            this.placeholdersAfter = event.getPlaceholdersRemaining();
            this.footerAdded = false;
        }
        if (this.pageStash.isEmpty()) {
            if (event.getLoadType() == loadType2) {
                this.startTerminalSeparatorDeferred = false;
            } else {
                this.endTerminalSeparatorDeferred = false;
            }
        }
        final IntRange intRange = new IntRange(event.getMinPageOffset(), event.getMaxPageOffset());
        CollectionsKt__MutableCollectionsKt.removeAll((List) this.pageStash, (Function1) new Function1<TransformablePage<T>, Boolean>() { // from class: androidx.paging.SeparatorState.onDrop.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(TransformablePage<T> stash) {
                Intrinsics.checkNotNullParameter(stash, "stash");
                int[] originalPageOffsets = stash.getOriginalPageOffsets();
                IntRange intRange2 = intRange;
                int length = originalPageOffsets.length;
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    if (intRange2.contains(originalPageOffsets[i])) {
                        z = true;
                        break;
                    }
                    i++;
                }
                return Boolean.valueOf(z);
            }
        });
        return event;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object onLoadStateUpdate(PageEvent.LoadStateUpdate<T> loadStateUpdate, Continuation<? super PageEvent<R>> continuation) {
        LoadStates mediatorStates = getMediatorStates();
        if (Intrinsics.areEqual(getSourceStates().snapshot(), loadStateUpdate.getSource()) && Intrinsics.areEqual(mediatorStates, loadStateUpdate.getMediator())) {
            return loadStateUpdate;
        }
        getSourceStates().set(loadStateUpdate.getSource());
        setMediatorStates(loadStateUpdate.getMediator());
        if (loadStateUpdate.getMediator() != null && loadStateUpdate.getMediator().getPrepend().getEndOfPaginationReached()) {
            if (!Intrinsics.areEqual(mediatorStates == null ? null : mediatorStates.getPrepend(), loadStateUpdate.getMediator().getPrepend())) {
                return onInsert(PageEvent.Insert.INSTANCE.Prepend(CollectionsKt__CollectionsKt.emptyList(), getPlaceholdersBefore(), loadStateUpdate.getSource(), loadStateUpdate.getMediator()), continuation);
            }
        }
        if (loadStateUpdate.getMediator() == null || !loadStateUpdate.getMediator().getAppend().getEndOfPaginationReached()) {
            return loadStateUpdate;
        }
        return !Intrinsics.areEqual(mediatorStates != null ? mediatorStates.getAppend() : null, loadStateUpdate.getMediator().getAppend()) ? onInsert(PageEvent.Insert.INSTANCE.Append(CollectionsKt__CollectionsKt.emptyList(), getPlaceholdersAfter(), loadStateUpdate.getSource(), loadStateUpdate.getMediator()), continuation) : loadStateUpdate;
    }

    private final <T> TransformablePage<T> transformablePageToStash(TransformablePage<T> originalPage) {
        Integer num;
        int[] originalPageOffsets = originalPage.getOriginalPageOffsets();
        List listListOf = CollectionsKt__CollectionsKt.listOf(CollectionsKt___CollectionsKt.first((List) originalPage.getData()), CollectionsKt___CollectionsKt.last((List) originalPage.getData()));
        int hintOriginalPageOffset = originalPage.getHintOriginalPageOffset();
        Integer[] numArr = new Integer[2];
        List<Integer> hintOriginalIndices = originalPage.getHintOriginalIndices();
        numArr[0] = Integer.valueOf((hintOriginalIndices == null || (num = (Integer) CollectionsKt___CollectionsKt.first((List) hintOriginalIndices)) == null) ? 0 : num.intValue());
        List<Integer> hintOriginalIndices2 = originalPage.getHintOriginalIndices();
        Integer num2 = hintOriginalIndices2 == null ? null : (Integer) CollectionsKt___CollectionsKt.last((List) hintOriginalIndices2);
        numArr[1] = Integer.valueOf(num2 == null ? CollectionsKt__CollectionsKt.getLastIndex(originalPage.getData()) : num2.intValue());
        return new TransformablePage<>(originalPageOffsets, listListOf, hintOriginalPageOffset, CollectionsKt__CollectionsKt.listOf((Object[]) numArr));
    }
}
