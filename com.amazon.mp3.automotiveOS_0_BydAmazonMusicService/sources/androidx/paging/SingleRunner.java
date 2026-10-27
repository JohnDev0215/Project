package androidx.paging;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: SingleRunner.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00102\u00020\u0001:\u0003\u000f\u0010\u0011B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J9\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u001c\u0010\u000b\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Landroidx/paging/SingleRunner;", "", "cancelPreviousInEqualPriority", "", "(Z)V", "holder", "Landroidx/paging/SingleRunner$Holder;", "runInIsolation", "", "priority", "", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "(ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "CancelIsolatedRunnerException", "Companion", "Holder", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class SingleRunner {
    public static final int DEFAULT_PRIORITY = 0;
    private final Holder holder;

    /* JADX INFO: renamed from: androidx.paging.SingleRunner$runInIsolation$1, reason: invalid class name */
    /* JADX INFO: compiled from: SingleRunner.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.paging.SingleRunner", f = "SingleRunner.kt", i = {0}, l = {49}, m = "runInIsolation", n = {"this"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SingleRunner.this.runInIsolation(0, null, this);
        }
    }

    public SingleRunner() {
        this(false, 1, null);
    }

    public SingleRunner(boolean z) {
        this.holder = new Holder(this, z);
    }

    public /* synthetic */ SingleRunner(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z);
    }

    public static /* synthetic */ Object runInIsolation$default(SingleRunner singleRunner, int i, Function1 function1, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return singleRunner.runInIsolation(i, function1, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object runInIsolation(int r5, kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof androidx.paging.SingleRunner.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            androidx.paging.SingleRunner$runInIsolation$1 r0 = (androidx.paging.SingleRunner.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.paging.SingleRunner$runInIsolation$1 r0 = new androidx.paging.SingleRunner$runInIsolation$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r5 = r0.L$0
            androidx.paging.SingleRunner r5 = (androidx.paging.SingleRunner) r5
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: androidx.paging.SingleRunner.CancelIsolatedRunnerException -> L2d
            goto L53
        L2d:
            r6 = move-exception
            goto L4d
        L2f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L37:
            kotlin.ResultKt.throwOnFailure(r7)
            androidx.paging.SingleRunner$runInIsolation$2 r7 = new androidx.paging.SingleRunner$runInIsolation$2     // Catch: androidx.paging.SingleRunner.CancelIsolatedRunnerException -> L4b
            r2 = 0
            r7.<init>(r5, r6, r2)     // Catch: androidx.paging.SingleRunner.CancelIsolatedRunnerException -> L4b
            r0.L$0 = r4     // Catch: androidx.paging.SingleRunner.CancelIsolatedRunnerException -> L4b
            r0.label = r3     // Catch: androidx.paging.SingleRunner.CancelIsolatedRunnerException -> L4b
            java.lang.Object r5 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r7, r0)     // Catch: androidx.paging.SingleRunner.CancelIsolatedRunnerException -> L4b
            if (r5 != r1) goto L53
            return r1
        L4b:
            r6 = move-exception
            r5 = r4
        L4d:
            androidx.paging.SingleRunner r7 = r6.getRunner()
            if (r7 != r5) goto L56
        L53:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L56:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SingleRunner.runInIsolation(int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: androidx.paging.SingleRunner$runInIsolation$2, reason: invalid class name */
    /* JADX INFO: compiled from: SingleRunner.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.SingleRunner$runInIsolation$2", f = "SingleRunner.kt", i = {0, 1}, l = {53, 59, 61, 61}, m = "invokeSuspend", n = {"myJob", "myJob"}, s = {"L$0", "L$0"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Continuation<? super Unit>, Object> $block;
        final /* synthetic */ int $priority;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(int i, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$priority = i;
            this.$block = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = SingleRunner.this.new AnonymousClass2(this.$priority, this.$block, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:31:0x008d A[RETURN] */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        /* JADX WARN: Type inference failed for: r1v1, types: [kotlinx.coroutines.Job] */
        /* JADX WARN: Type inference failed for: r1v14 */
        /* JADX WARN: Type inference failed for: r1v15 */
        /* JADX WARN: Type inference failed for: r1v9, types: [kotlinx.coroutines.Job] */
        /* JADX WARN: Type inference failed for: r3v2, types: [androidx.paging.SingleRunner$Holder] */
        /* JADX WARN: Type inference failed for: r9v15, types: [androidx.paging.SingleRunner$Holder] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 4
                r3 = 3
                r4 = 2
                r5 = 1
                if (r1 == 0) goto L3c
                if (r1 == r5) goto L34
                if (r1 == r4) goto L2a
                if (r1 == r3) goto L25
                if (r1 == r2) goto L1c
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L1c:
                java.lang.Object r0 = r8.L$0
                java.lang.Throwable r0 = (java.lang.Throwable) r0
                kotlin.ResultKt.throwOnFailure(r9)
                goto La0
            L25:
                kotlin.ResultKt.throwOnFailure(r9)
                goto La1
            L2a:
                java.lang.Object r1 = r8.L$0
                kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
                kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L32
                goto L7c
            L32:
                r9 = move-exception
                goto L8e
            L34:
                java.lang.Object r1 = r8.L$0
                kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
                kotlin.ResultKt.throwOnFailure(r9)
                goto L67
            L3c:
                kotlin.ResultKt.throwOnFailure(r9)
                java.lang.Object r9 = r8.L$0
                kotlinx.coroutines.CoroutineScope r9 = (kotlinx.coroutines.CoroutineScope) r9
                kotlin.coroutines.CoroutineContext r9 = r9.getCoroutineContext()
                kotlinx.coroutines.Job$Key r1 = kotlinx.coroutines.Job.INSTANCE
                kotlin.coroutines.CoroutineContext$Element r9 = r9.get(r1)
                if (r9 == 0) goto La4
                kotlinx.coroutines.Job r9 = (kotlinx.coroutines.Job) r9
                androidx.paging.SingleRunner r1 = androidx.paging.SingleRunner.this
                androidx.paging.SingleRunner$Holder r1 = androidx.paging.SingleRunner.access$getHolder$p(r1)
                int r6 = r8.$priority
                r8.L$0 = r9
                r8.label = r5
                java.lang.Object r1 = r1.tryEnqueue(r6, r9, r8)
                if (r1 != r0) goto L64
                return r0
            L64:
                r7 = r1
                r1 = r9
                r9 = r7
            L67:
                java.lang.Boolean r9 = (java.lang.Boolean) r9
                boolean r9 = r9.booleanValue()
                if (r9 == 0) goto La1
                kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r9 = r8.$block     // Catch: java.lang.Throwable -> L32
                r8.L$0 = r1     // Catch: java.lang.Throwable -> L32
                r8.label = r4     // Catch: java.lang.Throwable -> L32
                java.lang.Object r9 = r9.invoke(r8)     // Catch: java.lang.Throwable -> L32
                if (r9 != r0) goto L7c
                return r0
            L7c:
                androidx.paging.SingleRunner r9 = androidx.paging.SingleRunner.this
                androidx.paging.SingleRunner$Holder r9 = androidx.paging.SingleRunner.access$getHolder$p(r9)
                r2 = 0
                r8.L$0 = r2
                r8.label = r3
                java.lang.Object r9 = r9.onFinish(r1, r8)
                if (r9 != r0) goto La1
                return r0
            L8e:
                androidx.paging.SingleRunner r3 = androidx.paging.SingleRunner.this
                androidx.paging.SingleRunner$Holder r3 = androidx.paging.SingleRunner.access$getHolder$p(r3)
                r8.L$0 = r9
                r8.label = r2
                java.lang.Object r1 = r3.onFinish(r1, r8)
                if (r1 != r0) goto L9f
                return r0
            L9f:
                r0 = r9
            La0:
                throw r0
            La1:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            La4:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "Internal error. coroutineScope should've created a job."
                java.lang.String r0 = r0.toString()
                r9.<init>(r0)
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SingleRunner.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: compiled from: SingleRunner.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/paging/SingleRunner$CancelIsolatedRunnerException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "runner", "Landroidx/paging/SingleRunner;", "(Landroidx/paging/SingleRunner;)V", "getRunner", "()Landroidx/paging/SingleRunner;", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
    private static final class CancelIsolatedRunnerException extends CancellationException {
        private final SingleRunner runner;

        public CancelIsolatedRunnerException(SingleRunner runner) {
            Intrinsics.checkNotNullParameter(runner, "runner");
            this.runner = runner;
        }

        public final SingleRunner getRunner() {
            return this.runner;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SingleRunner.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J!\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Landroidx/paging/SingleRunner$Holder;", "", "singleRunner", "Landroidx/paging/SingleRunner;", "cancelPreviousInEqualPriority", "", "(Landroidx/paging/SingleRunner;Z)V", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "previous", "Lkotlinx/coroutines/Job;", "previousPriority", "", "onFinish", "", "job", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryEnqueue", "priority", "(ILkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
    static final class Holder {
        private final boolean cancelPreviousInEqualPriority;
        private final Mutex mutex;
        private Job previous;
        private int previousPriority;
        private final SingleRunner singleRunner;

        public Holder(SingleRunner singleRunner, boolean z) {
            Intrinsics.checkNotNullParameter(singleRunner, "singleRunner");
            this.singleRunner = singleRunner;
            this.cancelPreviousInEqualPriority = z;
            this.mutex = MutexKt.Mutex$default(false, 1, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.Object, kotlinx.coroutines.Job] */
        /* JADX WARN: Type inference failed for: r11v1, types: [kotlinx.coroutines.sync.Mutex] */
        /* JADX WARN: Type inference failed for: r11v16 */
        /* JADX WARN: Type inference failed for: r11v17 */
        /* JADX WARN: Type inference failed for: r11v4, types: [kotlinx.coroutines.sync.Mutex] */
        /* JADX WARN: Type inference failed for: r1v1 */
        /* JADX WARN: Type inference failed for: r1v2 */
        /* JADX WARN: Type inference failed for: r1v7 */
        /* JADX WARN: Type inference failed for: r2v2 */
        /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v4, types: [kotlinx.coroutines.Job] */
        /* JADX WARN: Type inference failed for: r2v5 */
        /* JADX WARN: Type inference failed for: r2v8 */
        /* JADX WARN: Type inference failed for: r2v9 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object tryEnqueue(int r10, kotlinx.coroutines.Job r11, kotlin.coroutines.Continuation<? super java.lang.Boolean> r12) {
            /*
                r9 = this;
                boolean r0 = r12 instanceof androidx.paging.SingleRunner$Holder$tryEnqueue$1
                if (r0 == 0) goto L13
                r0 = r12
                androidx.paging.SingleRunner$Holder$tryEnqueue$1 r0 = (androidx.paging.SingleRunner$Holder$tryEnqueue$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                androidx.paging.SingleRunner$Holder$tryEnqueue$1 r0 = new androidx.paging.SingleRunner$Holder$tryEnqueue$1
                r0.<init>(r9, r12)
            L18:
                java.lang.Object r12 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 2
                r4 = 0
                r5 = 1
                if (r2 == 0) goto L59
                if (r2 == r5) goto L47
                if (r2 != r3) goto L3f
                int r10 = r0.I$0
                java.lang.Object r11 = r0.L$2
                kotlinx.coroutines.sync.Mutex r11 = (kotlinx.coroutines.sync.Mutex) r11
                java.lang.Object r1 = r0.L$1
                kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
                java.lang.Object r0 = r0.L$0
                androidx.paging.SingleRunner$Holder r0 = (androidx.paging.SingleRunner.Holder) r0
                kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L3c
                goto Lac
            L3c:
                r10 = move-exception
                goto Lba
            L3f:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r11)
                throw r10
            L47:
                int r10 = r0.I$0
                java.lang.Object r11 = r0.L$2
                kotlinx.coroutines.sync.Mutex r11 = (kotlinx.coroutines.sync.Mutex) r11
                java.lang.Object r2 = r0.L$1
                kotlinx.coroutines.Job r2 = (kotlinx.coroutines.Job) r2
                java.lang.Object r6 = r0.L$0
                androidx.paging.SingleRunner$Holder r6 = (androidx.paging.SingleRunner.Holder) r6
                kotlin.ResultKt.throwOnFailure(r12)
                goto L72
            L59:
                kotlin.ResultKt.throwOnFailure(r12)
                kotlinx.coroutines.sync.Mutex r12 = r9.mutex
                r0.L$0 = r9
                r0.L$1 = r11
                r0.L$2 = r12
                r0.I$0 = r10
                r0.label = r5
                java.lang.Object r2 = r12.lock(r4, r0)
                if (r2 != r1) goto L6f
                return r1
            L6f:
                r6 = r9
                r2 = r11
                r11 = r12
            L72:
                kotlinx.coroutines.Job r12 = r6.previous     // Catch: java.lang.Throwable -> L3c
                if (r12 == 0) goto L89
                boolean r7 = r12.isActive()     // Catch: java.lang.Throwable -> L3c
                if (r7 == 0) goto L89
                int r7 = r6.previousPriority     // Catch: java.lang.Throwable -> L3c
                if (r7 < r10) goto L89
                if (r7 != r10) goto L87
                boolean r7 = r6.cancelPreviousInEqualPriority     // Catch: java.lang.Throwable -> L3c
                if (r7 == 0) goto L87
                goto L89
            L87:
                r5 = 0
                goto Lb2
            L89:
                if (r12 != 0) goto L8c
                goto L96
            L8c:
                androidx.paging.SingleRunner$CancelIsolatedRunnerException r7 = new androidx.paging.SingleRunner$CancelIsolatedRunnerException     // Catch: java.lang.Throwable -> L3c
                androidx.paging.SingleRunner r8 = r6.singleRunner     // Catch: java.lang.Throwable -> L3c
                r7.<init>(r8)     // Catch: java.lang.Throwable -> L3c
                r12.cancel(r7)     // Catch: java.lang.Throwable -> L3c
            L96:
                if (r12 != 0) goto L99
                goto Lae
            L99:
                r0.L$0 = r6     // Catch: java.lang.Throwable -> L3c
                r0.L$1 = r2     // Catch: java.lang.Throwable -> L3c
                r0.L$2 = r11     // Catch: java.lang.Throwable -> L3c
                r0.I$0 = r10     // Catch: java.lang.Throwable -> L3c
                r0.label = r3     // Catch: java.lang.Throwable -> L3c
                java.lang.Object r12 = r12.join(r0)     // Catch: java.lang.Throwable -> L3c
                if (r12 != r1) goto Laa
                return r1
            Laa:
                r1 = r2
                r0 = r6
            Lac:
                r6 = r0
                r2 = r1
            Lae:
                r6.previous = r2     // Catch: java.lang.Throwable -> L3c
                r6.previousPriority = r10     // Catch: java.lang.Throwable -> L3c
            Lb2:
                java.lang.Boolean r10 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)     // Catch: java.lang.Throwable -> L3c
                r11.unlock(r4)
                return r10
            Lba:
                r11.unlock(r4)
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SingleRunner.Holder.tryEnqueue(int, kotlinx.coroutines.Job, kotlin.coroutines.Continuation):java.lang.Object");
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object onFinish(kotlinx.coroutines.Job r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
            /*
                r5 = this;
                boolean r0 = r7 instanceof androidx.paging.SingleRunner$Holder$onFinish$1
                if (r0 == 0) goto L13
                r0 = r7
                androidx.paging.SingleRunner$Holder$onFinish$1 r0 = (androidx.paging.SingleRunner$Holder$onFinish$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                androidx.paging.SingleRunner$Holder$onFinish$1 r0 = new androidx.paging.SingleRunner$Holder$onFinish$1
                r0.<init>(r5, r7)
            L18:
                java.lang.Object r7 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                r4 = 0
                if (r2 == 0) goto L40
                if (r2 != r3) goto L38
                java.lang.Object r6 = r0.L$2
                kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
                java.lang.Object r1 = r0.L$1
                kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
                java.lang.Object r0 = r0.L$0
                androidx.paging.SingleRunner$Holder r0 = (androidx.paging.SingleRunner.Holder) r0
                kotlin.ResultKt.throwOnFailure(r7)
                r7 = r6
                r6 = r1
                goto L55
            L38:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L40:
                kotlin.ResultKt.throwOnFailure(r7)
                kotlinx.coroutines.sync.Mutex r7 = r5.mutex
                r0.L$0 = r5
                r0.L$1 = r6
                r0.L$2 = r7
                r0.label = r3
                java.lang.Object r0 = r7.lock(r4, r0)
                if (r0 != r1) goto L54
                return r1
            L54:
                r0 = r5
            L55:
                kotlinx.coroutines.Job r1 = r0.previous     // Catch: java.lang.Throwable -> L63
                if (r6 != r1) goto L5b
                r0.previous = r4     // Catch: java.lang.Throwable -> L63
            L5b:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L63
                r7.unlock(r4)
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            L63:
                r6 = move-exception
                r7.unlock(r4)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.SingleRunner.Holder.onFinish(kotlinx.coroutines.Job, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }
}
