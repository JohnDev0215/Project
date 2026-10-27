package kotlinx.coroutines.reactive;

import com.google.firebase.perf.plugin.util.AsmString;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* JADX INFO: compiled from: Publish.kt */
/* JADX INFO: loaded from: classes7.dex */
@InternalCoroutinesApi
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0007\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u00020\b0H2\b\u0012\u0004\u0012\u00028\u00000I2\u00020J2\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000%0BB7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0007H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0012\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J!\u0010\u0016\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0015\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J%\u0010\u001b\u001a\u00020\u001a2\u0014\u0010\u0019\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b0\u0018H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u001d\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u000fH\u0014¢\u0006\u0004\b\u001d\u0010\u0017J\u0017\u0010\u001f\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\bH\u0014¢\u0006\u0004\b\u001f\u0010 JX\u0010)\u001a\u00020\b\"\u0004\b\u0001\u0010!2\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00010\"2\u0006\u0010$\u001a\u00028\u00002(\u0010(\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000%\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010&\u0012\u0006\u0012\u0004\u0018\u00010'0\u0006H\u0016ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\u0017\u0010-\u001a\u00020\b2\u0006\u0010,\u001a\u00020+H\u0016¢\u0006\u0004\b-\u0010.J\u001b\u0010/\u001a\u00020\b2\u0006\u0010$\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0004\b/\u00100J!\u00101\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0015\u001a\u00020\u000fH\u0002¢\u0006\u0004\b1\u0010\u0017J&\u00105\u001a\b\u0012\u0004\u0012\u00020\b022\u0006\u0010$\u001a\u00028\u0000H\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b3\u00104J\u000f\u00106\u001a\u00020\bH\u0002¢\u0006\u0004\b6\u0010\rR\u0016\u00107\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b7\u00108R\u001a\u0010;\u001a\b\u0012\u0004\u0012\u00028\u00000%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u0010:R&\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010<R\u0014\u0010=\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0014\u0010@\u001a\u00020?8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u0010AR&\u0010E\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000%0B8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bC\u0010DR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\bX\u0088\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010F\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006G"}, d2 = {"Lkotlinx/coroutines/reactive/PublisherCoroutine;", "T", "Lkotlin/coroutines/CoroutineContext;", "parentContext", "Lorg/reactivestreams/Subscriber;", "subscriber", "Lkotlin/Function2;", "", "", "exceptionOnCancelHandler", "<init>", "(Lkotlin/coroutines/CoroutineContext;Lorg/reactivestreams/Subscriber;Lkotlin/jvm/functions/Function2;)V", "cancel", "()V", "cause", "", "close", "(Ljava/lang/Throwable;)Z", "elem", "doLockedNext", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "handled", "doLockedSignalCompleted", "(Ljava/lang/Throwable;Z)V", "Lkotlin/Function1;", "handler", "", "invokeOnClose", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Void;", "onCancelled", "value", "onCompleted", "(Lkotlin/Unit;)V", "R", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "element", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "", "block", "registerSelectClause2", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "", "n", "request", "(J)V", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signalCompleted", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", AsmString.METHOD_DESC_FIREBASE_PERF_URL_CONNECTION_INSTRUMENT_WITH_OBJECT, "trySend", "unlockAndCheckCompleted", "cancelled", "Z", "getChannel", "()Lkotlinx/coroutines/channels/SendChannel;", "channel", "Lkotlin/jvm/functions/Function2;", "isClosedForSend", "()Z", "Lkotlinx/coroutines/sync/Mutex;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "onSend", "Lorg/reactivestreams/Subscriber;", "kotlinx-coroutines-reactive", "Lkotlinx/coroutines/AbstractCoroutine;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lorg/reactivestreams/Subscription;"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PublisherCoroutine<T> extends AbstractCoroutine<Unit> implements ProducerScope<T>, Subscription, SelectClause2<T, SendChannel<? super T>> {
    private static final /* synthetic */ AtomicLongFieldUpdater _nRequested$FU = AtomicLongFieldUpdater.newUpdater(PublisherCoroutine.class, "_nRequested");
    private volatile /* synthetic */ long _nRequested;
    private volatile boolean cancelled;
    private final Function2<Throwable, CoroutineContext, Unit> exceptionOnCancelHandler;
    private final Mutex mutex;
    private final Subscriber<T> subscriber;

    /* JADX INFO: renamed from: kotlinx.coroutines.reactive.PublisherCoroutine$send$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Publish.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.reactive.PublisherCoroutine", f = "Publish.kt", i = {0, 0}, l = {98}, m = "send", n = {"this", "element"}, s = {"L$0", "L$1"})
    static final class C06101 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ PublisherCoroutine<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C06101(PublisherCoroutine<? super T> publisherCoroutine, Continuation<? super C06101> continuation) {
            super(continuation);
            this.this$0 = publisherCoroutine;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.send(null, this);
        }
    }

    @Override // kotlinx.coroutines.channels.ProducerScope
    public SendChannel<T> getChannel() {
        return this;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public SelectClause2<T, SendChannel<T>> getOnSend() {
        return this;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* JADX INFO: renamed from: invokeOnClose, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ void mo4341invokeOnClose(Function1 function1) {
        invokeOnClose((Function1<? super Throwable, Unit>) function1);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    public boolean offer(T t) {
        return ProducerScope.DefaultImpls.offer(this, t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PublisherCoroutine(CoroutineContext coroutineContext, Subscriber<T> subscriber, Function2<? super Throwable, ? super CoroutineContext, Unit> function2) {
        super(coroutineContext, false, true);
        this.subscriber = subscriber;
        this.exceptionOnCancelHandler = function2;
        this.mutex = MutexKt.Mutex(true);
        this._nRequested = 0L;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return !isActive();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* JADX INFO: renamed from: close */
    public boolean cancel(Throwable cause) {
        return cancelCoroutine(cause);
    }

    public Void invokeOnClose(Function1<? super Throwable, Unit> handler) {
        throw new UnsupportedOperationException("PublisherCoroutine doesn't support invokeOnClose");
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* JADX INFO: renamed from: trySend-JP2dKIU */
    public Object mo55trySendJP2dKIU(T element) {
        if (!Mutex.DefaultImpls.tryLock$default(this.mutex, null, 1, null)) {
            return ChannelResult.INSTANCE.m4300failurePtdJZtk();
        }
        Throwable thDoLockedNext = doLockedNext(element);
        if (thDoLockedNext == null) {
            return ChannelResult.INSTANCE.m4301successJP2dKIU(Unit.INSTANCE);
        }
        return ChannelResult.INSTANCE.m4299closedJP2dKIU(thDoLockedNext);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // kotlinx.coroutines.channels.SendChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object send(T r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.reactive.PublisherCoroutine.C06101
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.reactive.PublisherCoroutine$send$1 r0 = (kotlinx.coroutines.reactive.PublisherCoroutine.C06101) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.reactive.PublisherCoroutine$send$1 r0 = new kotlinx.coroutines.reactive.PublisherCoroutine$send$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r5 = r0.L$1
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.reactive.PublisherCoroutine r0 = (kotlinx.coroutines.reactive.PublisherCoroutine) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4b
        L2f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L37:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.sync.Mutex r6 = r4.mutex
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            r2 = 0
            java.lang.Object r6 = kotlinx.coroutines.sync.Mutex.DefaultImpls.lock$default(r6, r2, r0, r3, r2)
            if (r6 != r1) goto L4a
            return r1
        L4a:
            r0 = r4
        L4b:
            java.lang.Throwable r5 = r0.doLockedNext(r5)
            if (r5 != 0) goto L54
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L54:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.reactive.PublisherCoroutine.send(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.selects.SelectClause2
    public <R> void registerSelectClause2(SelectInstance<? super R> select, T element, Function2<? super SendChannel<? super T>, ? super Continuation<? super R>, ? extends Object> block) {
        BuildersKt__Builders_commonKt.launch$default(this, null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(this, select, new PublisherCoroutine$registerSelectClause2$clause$1(this, element, block, null), null), 1, null);
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.reactive.PublisherCoroutine$registerSelectClause2$1, reason: invalid class name */
    /* JADX INFO: compiled from: Publish.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0006\b\u0001\u0010\u0001 \u0000*\u00020\u0002H\u008a@"}, d2 = {"R", "T", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.reactive.PublisherCoroutine$registerSelectClause2$1", f = "Publish.kt", i = {}, l = {114}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Continuation<? super R>, Object> $clause;
        final /* synthetic */ SelectInstance<R> $select;
        int label;
        final /* synthetic */ PublisherCoroutine<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(PublisherCoroutine<? super T> publisherCoroutine, SelectInstance<? super R> selectInstance, Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = publisherCoroutine;
            this.$select = selectInstance;
            this.$clause = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, this.$select, this.$clause, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Mutex mutex = ((PublisherCoroutine) this.this$0).mutex;
                this.label = 1;
                if (Mutex.DefaultImpls.lock$default(mutex, null, this, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            if (!this.$select.trySelect()) {
                Mutex.DefaultImpls.unlock$default(((PublisherCoroutine) this.this$0).mutex, null, 1, null);
                return Unit.INSTANCE;
            }
            CancellableKt.startCoroutineCancellable(this.$clause, this.$select.getCompletion());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Throwable doLockedNext(T elem) {
        if (elem == null) {
            unlockAndCheckCompleted();
            throw new NullPointerException("Attempted to emit `null` inside a reactive publisher");
        }
        if (!isActive()) {
            unlockAndCheckCompleted();
            return getCancellationException();
        }
        try {
            this.subscriber.onNext(elem);
            while (true) {
                long j = this._nRequested;
                if (j < 0 || j == Long.MAX_VALUE) {
                    break;
                }
                long j2 = j - 1;
                if (_nRequested$FU.compareAndSet(this, j, j2)) {
                    if (j2 == 0) {
                        return null;
                    }
                }
            }
            unlockAndCheckCompleted();
            return null;
        } catch (Throwable th) {
            this.cancelled = true;
            boolean zCancel = cancel(th);
            unlockAndCheckCompleted();
            if (zCancel) {
                return th;
            }
            this.exceptionOnCancelHandler.mo4invoke(th, getContext());
            return getCancellationException();
        }
    }

    private final void unlockAndCheckCompleted() {
        Mutex.DefaultImpls.unlock$default(this.mutex, null, 1, null);
        if (isCompleted() && Mutex.DefaultImpls.tryLock$default(this.mutex, null, 1, null)) {
            doLockedSignalCompleted(getCompletionCause(), getCompletionCauseHandled());
        }
    }

    private final void doLockedSignalCompleted(Throwable cause, boolean handled) {
        try {
            if (this._nRequested != -2) {
                this._nRequested = -2L;
                if (!this.cancelled) {
                    if (cause == null) {
                        try {
                            this.subscriber.onComplete();
                        } catch (Throwable th) {
                            CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), th);
                        }
                    } else {
                        try {
                            this.subscriber.onError(cause);
                        } catch (Throwable th2) {
                            if (th2 != cause) {
                                ExceptionsKt__ExceptionsKt.addSuppressed(cause, th2);
                            }
                            CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), cause);
                        }
                    }
                    return;
                }
                if (cause != null && !handled) {
                    this.exceptionOnCancelHandler.mo4invoke(cause, getContext());
                }
            }
        } finally {
            Mutex.DefaultImpls.unlock$default(this.mutex, null, 1, null);
        }
    }

    @Override // org.reactivestreams.Subscription
    public void request(long n) {
        long j;
        long j2;
        if (n <= 0) {
            cancelCoroutine(new IllegalArgumentException("non-positive subscription request " + n));
            return;
        }
        do {
            j = this._nRequested;
            if (j < 0) {
                return;
            }
            long j3 = j + n;
            j2 = (j3 < 0 || n == Long.MAX_VALUE) ? Long.MAX_VALUE : j3;
            if (j == j2) {
                return;
            }
        } while (!_nRequested$FU.compareAndSet(this, j, j2));
        if (j == 0) {
            unlockAndCheckCompleted();
        }
    }

    private final void signalCompleted(Throwable cause, boolean handled) {
        long j;
        do {
            j = this._nRequested;
            if (j == -2) {
                return;
            }
            if (!(j >= 0)) {
                throw new IllegalStateException("Check failed.".toString());
            }
        } while (!_nRequested$FU.compareAndSet(this, j, -1L));
        if (j == 0) {
            doLockedSignalCompleted(cause, handled);
        } else if (Mutex.DefaultImpls.tryLock$default(this.mutex, null, 1, null)) {
            doLockedSignalCompleted(cause, handled);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.AbstractCoroutine
    public void onCompleted(Unit value) {
        signalCompleted(null, false);
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    protected void onCancelled(Throwable cause, boolean handled) {
        signalCompleted(cause, handled);
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public void cancel() {
        this.cancelled = true;
        super.cancel((CancellationException) null);
    }
}
