package kotlinx.coroutines.reactive;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.intrinsics.CancellableKt;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* JADX INFO: compiled from: ReactiveFlow.kt */
/* JADX INFO: loaded from: classes7.dex */
@InternalCoroutinesApi
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u001d2\b\u0012\u0004\u0012\u00020\n0\u001eB-\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u000fH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0013\u0010\u0012\u001a\u00020\nH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u000eJ\u0017\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001aR\u001c\u0010\u0005\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lkotlinx/coroutines/reactive/FlowSubscription;", "T", "Lkotlinx/coroutines/flow/Flow;", "flow", "Lorg/reactivestreams/Subscriber;", "subscriber", "Lkotlin/coroutines/CoroutineContext;", "context", "<init>", "(Lkotlinx/coroutines/flow/Flow;Lorg/reactivestreams/Subscriber;Lkotlin/coroutines/CoroutineContext;)V", "", "cancel", "()V", "consumeFlow", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/coroutines/Continuation;", "createInitialContinuation", "()Lkotlin/coroutines/Continuation;", "flowProcessing", "", "n", "request", "(J)V", "", "cancellationRequested", "Z", "Lkotlinx/coroutines/flow/Flow;", "Lorg/reactivestreams/Subscriber;", "kotlinx-coroutines-reactive", "Lorg/reactivestreams/Subscription;", "Lkotlinx/coroutines/AbstractCoroutine;"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FlowSubscription<T> extends AbstractCoroutine<Unit> implements Subscription {
    private volatile boolean cancellationRequested;

    @JvmField
    public final Flow<T> flow;
    volatile /* synthetic */ Object producer;
    volatile /* synthetic */ long requested;

    @JvmField
    public final Subscriber<? super T> subscriber;
    static final /* synthetic */ AtomicLongFieldUpdater requested$FU = AtomicLongFieldUpdater.newUpdater(FlowSubscription.class, "requested");
    static final /* synthetic */ AtomicReferenceFieldUpdater producer$FU = AtomicReferenceFieldUpdater.newUpdater(FlowSubscription.class, Object.class, "producer");

    /* JADX INFO: renamed from: kotlinx.coroutines.reactive.FlowSubscription$flowProcessing$1, reason: invalid class name */
    /* JADX INFO: compiled from: ReactiveFlow.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.reactive.FlowSubscription", f = "ReactiveFlow.kt", i = {0}, l = {209}, m = "flowProcessing", n = {"this"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ FlowSubscription<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(FlowSubscription<T> flowSubscription, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = flowSubscription;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.flowProcessing(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FlowSubscription(Flow<? extends T> flow, Subscriber<? super T> subscriber, CoroutineContext coroutineContext) {
        super(coroutineContext, false, true);
        this.flow = flow;
        this.subscriber = subscriber;
        this.requested = 0L;
        this.producer = createInitialContinuation();
    }

    private final Continuation<Unit> createInitialContinuation() {
        final CoroutineContext coroutineContext = getCoroutineContext();
        return new Continuation<Unit>() { // from class: kotlinx.coroutines.reactive.FlowSubscription$createInitialContinuation$$inlined$Continuation$1
            @Override // kotlin.coroutines.Continuation
            /* JADX INFO: renamed from: getContext, reason: from getter */
            public CoroutineContext get$context() {
                return coroutineContext;
            }

            @Override // kotlin.coroutines.Continuation
            public void resumeWith(Object result) throws Throwable {
                CancellableKt.startCoroutineCancellable(new FlowSubscription$createInitialContinuation$1$1(this), this);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object flowProcessing(kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof kotlinx.coroutines.reactive.FlowSubscription.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            kotlinx.coroutines.reactive.FlowSubscription$flowProcessing$1 r0 = (kotlinx.coroutines.reactive.FlowSubscription.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.reactive.FlowSubscription$flowProcessing$1 r0 = new kotlinx.coroutines.reactive.FlowSubscription$flowProcessing$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.reactive.FlowSubscription r0 = (kotlinx.coroutines.reactive.FlowSubscription) r0
            kotlin.ResultKt.throwOnFailure(r5)     // Catch: java.lang.Throwable -> L2d
            goto L46
        L2d:
            r5 = move-exception
            goto L59
        L2f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L37:
            kotlin.ResultKt.throwOnFailure(r5)
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L57
            r0.label = r3     // Catch: java.lang.Throwable -> L57
            java.lang.Object r5 = r4.consumeFlow(r0)     // Catch: java.lang.Throwable -> L57
            if (r5 != r1) goto L45
            return r1
        L45:
            r0 = r4
        L46:
            org.reactivestreams.Subscriber<? super T> r5 = r0.subscriber     // Catch: java.lang.Throwable -> L4c
            r5.onComplete()     // Catch: java.lang.Throwable -> L4c
            goto L54
        L4c:
            r5 = move-exception
            kotlin.coroutines.CoroutineContext r0 = r0.getCoroutineContext()
            kotlinx.coroutines.CoroutineExceptionHandlerKt.handleCoroutineException(r0, r5)
        L54:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L57:
            r5 = move-exception
            r0 = r4
        L59:
            boolean r1 = r0.cancellationRequested
            if (r1 == 0) goto L69
            boolean r1 = r0.isActive()
            if (r1 != 0) goto L69
            java.util.concurrent.CancellationException r1 = r0.getCancellationException()
            if (r5 == r1) goto L7a
        L69:
            org.reactivestreams.Subscriber<? super T> r1 = r0.subscriber     // Catch: java.lang.Throwable -> L6f
            r1.onError(r5)     // Catch: java.lang.Throwable -> L6f
            goto L7a
        L6f:
            r1 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r5, r1)
            kotlin.coroutines.CoroutineContext r0 = r0.getCoroutineContext()
            kotlinx.coroutines.CoroutineExceptionHandlerKt.handleCoroutineException(r0, r5)
        L7a:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.reactive.FlowSubscription.flowProcessing(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object consumeFlow(Continuation<? super Unit> continuation) {
        Object objCollect = this.flow.collect(new FlowCollector(this) { // from class: kotlinx.coroutines.reactive.FlowSubscription.consumeFlow.2
            final /* synthetic */ FlowSubscription<T> this$0;

            {
                this.this$0 = this;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(T t, Continuation<? super Unit> continuation2) {
                this.this$0.subscriber.onNext(t);
                if (FlowSubscription.requested$FU.decrementAndGet(this.this$0) <= 0) {
                    FlowSubscription<T> flowSubscription = this.this$0;
                    CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation2), 1);
                    cancellableContinuationImpl.initCancellability();
                    flowSubscription.producer = cancellableContinuationImpl;
                    Object result = cancellableContinuationImpl.getResult();
                    if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        DebugProbesKt.probeCoroutineSuspended(continuation2);
                    }
                    return result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
                }
                JobKt.ensureActive(this.this$0.getCoroutineContext());
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public void cancel() {
        this.cancellationRequested = true;
        cancel((CancellationException) null);
    }

    @Override // org.reactivestreams.Subscription
    public void request(long n) {
        long j;
        long j2;
        Continuation continuation;
        if (n <= 0) {
            return;
        }
        do {
            j = this.requested;
            j2 = j + n;
            if (j2 <= 0) {
                j2 = Long.MAX_VALUE;
            }
        } while (!requested$FU.compareAndSet(this, j, j2));
        if (j <= 0) {
            do {
                continuation = (Continuation) producer$FU.getAndSet(this, null);
            } while (continuation == null);
            Result.Companion companion = Result.INSTANCE;
            continuation.resumeWith(Result.m2810constructorimpl(Unit.INSTANCE));
        }
    }
}
