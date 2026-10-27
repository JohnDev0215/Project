package kotlinx.coroutines.rx3;

import com.google.firebase.perf.plugin.util.AsmString;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.exceptions.UndeliverableException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: RxObservable.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00020\u00120?2\b\u0012\u0004\u0012\u00028\u00000@2\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000#09B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u000f\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J!\u0010\u0013\u001a\u00020\u00122\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J%\u0010\u0018\u001a\u00020\u00172\u0014\u0010\u0016\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\u00120\u0015H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001a\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u000bH\u0014¢\u0006\u0004\b\u001a\u0010\u0014J\u0017\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u0012H\u0014¢\u0006\u0004\b\u001c\u0010\u001dJX\u0010&\u001a\u00020\u0012\"\u0004\b\u0001\u0010\u001e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00010\u001f2\u0006\u0010!\u001a\u00028\u00002(\u0010%\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000#\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010$\u0012\u0006\u0012\u0004\u0018\u00010\u00010\"H\u0016ø\u0001\u0000¢\u0006\u0004\b&\u0010'J\u001b\u0010(\u001a\u00020\u00122\u0006\u0010!\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0004\b(\u0010)J!\u0010*\u001a\u00020\u00122\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\u000bH\u0002¢\u0006\u0004\b*\u0010\u0014J&\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00120+2\u0006\u0010!\u001a\u00028\u0000H\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b,\u0010-J\u000f\u0010/\u001a\u00020\u0012H\u0002¢\u0006\u0004\b/\u00100R\u001a\u00103\u001a\b\u0012\u0004\u0012\u00028\u00000#8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u0014\u00104\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b4\u00105R\u0014\u00107\u001a\u0002068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R&\u0010<\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000#098VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010=\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006>"}, d2 = {"Lkotlinx/coroutines/rx3/RxObservableCoroutine;", "", "T", "Lkotlin/coroutines/CoroutineContext;", "parentContext", "Lio/reactivex/rxjava3/core/ObservableEmitter;", "subscriber", "<init>", "(Lkotlin/coroutines/CoroutineContext;Lio/reactivex/rxjava3/core/ObservableEmitter;)V", "", "cause", "", "close", "(Ljava/lang/Throwable;)Z", "elem", "doLockedNext", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "handled", "", "doLockedSignalCompleted", "(Ljava/lang/Throwable;Z)V", "Lkotlin/Function1;", "handler", "", "invokeOnClose", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Void;", "onCancelled", "value", "onCompleted", "(Lkotlin/Unit;)V", "R", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "element", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/SendChannel;", "Lkotlin/coroutines/Continuation;", "block", "registerSelectClause2", "(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signalCompleted", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", AsmString.METHOD_DESC_FIREBASE_PERF_URL_CONNECTION_INSTRUMENT_WITH_OBJECT, "trySend", "unlockAndCheckCompleted", "()V", "getChannel", "()Lkotlinx/coroutines/channels/SendChannel;", "channel", "isClosedForSend", "()Z", "Lkotlinx/coroutines/sync/Mutex;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "onSend", "Lio/reactivex/rxjava3/core/ObservableEmitter;", "kotlinx-coroutines-rx3", "Lkotlinx/coroutines/AbstractCoroutine;", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 1, mv = {1, 6, 0}, xi = 48)
final class RxObservableCoroutine<T> extends AbstractCoroutine<Unit> implements ProducerScope<T>, SelectClause2<T, SendChannel<? super T>> {
    private static final /* synthetic */ AtomicIntegerFieldUpdater _signal$FU = AtomicIntegerFieldUpdater.newUpdater(RxObservableCoroutine.class, "_signal");
    private volatile /* synthetic */ int _signal;
    private final Mutex mutex;
    private final ObservableEmitter<T> subscriber;

    /* JADX INFO: renamed from: kotlinx.coroutines.rx3.RxObservableCoroutine$send$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RxObservable.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.rx3.RxObservableCoroutine", f = "RxObservable.kt", i = {0, 0}, l = {85}, m = "send", n = {"this", "element"}, s = {"L$0", "L$1"})
    static final class C06171 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ RxObservableCoroutine<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06171(RxObservableCoroutine<T> rxObservableCoroutine, Continuation<? super C06171> continuation) {
            super(continuation);
            this.this$0 = rxObservableCoroutine;
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
    /* JADX INFO: renamed from: invokeOnClose */
    public /* bridge */ /* synthetic */ void mo4341invokeOnClose(Function1 function1) {
        invokeOnClose((Function1<? super Throwable, Unit>) function1);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    public boolean offer(T t) {
        return ProducerScope.DefaultImpls.offer(this, t);
    }

    public RxObservableCoroutine(CoroutineContext coroutineContext, ObservableEmitter<T> observableEmitter) {
        super(coroutineContext, false, true);
        this.subscriber = observableEmitter;
        this.mutex = MutexKt.Mutex$default(false, 1, null);
        this._signal = 0;
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
        throw new UnsupportedOperationException("RxObservableCoroutine doesn't support invokeOnClose");
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
            boolean r0 = r6 instanceof kotlinx.coroutines.rx3.RxObservableCoroutine.C06171
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.rx3.RxObservableCoroutine$send$1 r0 = (kotlinx.coroutines.rx3.RxObservableCoroutine.C06171) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.rx3.RxObservableCoroutine$send$1 r0 = new kotlinx.coroutines.rx3.RxObservableCoroutine$send$1
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
            kotlinx.coroutines.rx3.RxObservableCoroutine r0 = (kotlinx.coroutines.rx3.RxObservableCoroutine) r0
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
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.rx3.RxObservableCoroutine.send(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.selects.SelectClause2
    public <R> void registerSelectClause2(SelectInstance<? super R> select, T element, Function2<? super SendChannel<? super T>, ? super Continuation<? super R>, ? extends Object> block) {
        BuildersKt__Builders_commonKt.launch$default(this, null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(this, select, new RxObservableCoroutine$registerSelectClause2$clause$1(this, element, block, null), null), 1, null);
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.rx3.RxObservableCoroutine$registerSelectClause2$1, reason: invalid class name */
    /* JADX INFO: compiled from: RxObservable.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"R", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.rx3.RxObservableCoroutine$registerSelectClause2$1", f = "RxObservable.kt", i = {}, l = {106}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Continuation<? super R>, Object> $clause;
        final /* synthetic */ SelectInstance<R> $select;
        int label;
        final /* synthetic */ RxObservableCoroutine<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(RxObservableCoroutine<T> rxObservableCoroutine, SelectInstance<? super R> selectInstance, Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = rxObservableCoroutine;
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
                Mutex mutex = ((RxObservableCoroutine) this.this$0).mutex;
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
                Mutex.DefaultImpls.unlock$default(((RxObservableCoroutine) this.this$0).mutex, null, 1, null);
                return Unit.INSTANCE;
            }
            CancellableKt.startCoroutineCancellable(this.$clause, this.$select.getCompletion());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Throwable doLockedNext(T elem) {
        if (!isActive()) {
            doLockedSignalCompleted(getCompletionCause(), getCompletionCauseHandled());
            return getCancellationException();
        }
        try {
            this.subscriber.onNext(elem);
            unlockAndCheckCompleted();
            return null;
        } catch (Throwable th) {
            UndeliverableException undeliverableException = new UndeliverableException(th);
            boolean zCancel = cancel(undeliverableException);
            unlockAndCheckCompleted();
            if (zCancel) {
                return undeliverableException;
            }
            RxCancellableKt.handleUndeliverableException(undeliverableException, getContext());
            return getCancellationException();
        }
    }

    private final void unlockAndCheckCompleted() {
        Mutex.DefaultImpls.unlock$default(this.mutex, null, 1, null);
        if (isActive() || !Mutex.DefaultImpls.tryLock$default(this.mutex, null, 1, null)) {
            return;
        }
        doLockedSignalCompleted(getCompletionCause(), getCompletionCauseHandled());
    }

    private final void doLockedSignalCompleted(Throwable cause, boolean handled) {
        try {
            if (this._signal == -2) {
                return;
            }
            this._signal = -2;
            Throwable th = cause != null ? cause : null;
            if (th == null) {
                try {
                    this.subscriber.onComplete();
                } catch (Exception e) {
                    RxCancellableKt.handleUndeliverableException(e, getContext());
                }
            } else if ((th instanceof UndeliverableException) && !handled) {
                RxCancellableKt.handleUndeliverableException(cause, getContext());
            } else if (th != getCancellationException() || !this.subscriber.isDisposed()) {
                try {
                    this.subscriber.onError(cause);
                } catch (Exception e2) {
                    ExceptionsKt__ExceptionsKt.addSuppressed(cause, e2);
                    RxCancellableKt.handleUndeliverableException(cause, getContext());
                }
            }
        } finally {
            Mutex.DefaultImpls.unlock$default(this.mutex, null, 1, null);
        }
    }

    private final void signalCompleted(Throwable cause, boolean handled) {
        if (_signal$FU.compareAndSet(this, 0, -1) && Mutex.DefaultImpls.tryLock$default(this.mutex, null, 1, null)) {
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
}
