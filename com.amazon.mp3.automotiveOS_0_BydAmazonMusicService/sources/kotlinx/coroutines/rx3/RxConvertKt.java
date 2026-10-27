package kotlinx.coroutines.rx3;

import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.reactive.ReactiveFlowKt;

/* JADX INFO: compiled from: RxConvert.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a1\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\u0007\u001a1\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\t\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\u0007\u001a\u0012\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006\u001a \u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u000e\u001a*\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u001a&\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0006\u001a*\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00020\t\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u001a(\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0015\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0016"}, d2 = {"_asFlowable", "Lio/reactivex/rxjava3/core/Flowable;", "T", "", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "from", "_asObservable", "Lio/reactivex/rxjava3/core/Observable;", "asCompletable", "Lio/reactivex/rxjava3/core/Completable;", "Lkotlinx/coroutines/Job;", "asFlow", "Lio/reactivex/rxjava3/core/ObservableSource;", "asFlowable", "asMaybe", "Lio/reactivex/rxjava3/core/Maybe;", "Lkotlinx/coroutines/Deferred;", "asObservable", "asSingle", "Lio/reactivex/rxjava3/core/Single;", "kotlinx-coroutines-rx3"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class RxConvertKt {

    /* JADX INFO: renamed from: kotlinx.coroutines.rx3.RxConvertKt$asCompletable$1, reason: invalid class name */
    /* JADX INFO: compiled from: RxConvert.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.rx3.RxConvertKt$asCompletable$1", f = "RxConvert.kt", i = {}, l = {30}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Job $this_asCompletable;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Job job, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_asCompletable = job;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$this_asCompletable, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Job job = this.$this_asCompletable;
                this.label = 1;
                if (job.join(this) == coroutine_suspended) {
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

    public static final Completable asCompletable(Job job, CoroutineContext coroutineContext) {
        return RxCompletableKt.rxCompletable(coroutineContext, new AnonymousClass1(job, null));
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.rx3.RxConvertKt$asMaybe$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RxConvert.kt */
    @Metadata(bv = {}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@"}, d2 = {"T", "Lkotlinx/coroutines/CoroutineScope;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.rx3.RxConvertKt$asMaybe$1", f = "RxConvert.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
    static final class C06151<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ Deferred<T> $this_asMaybe;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C06151(Deferred<? extends T> deferred, Continuation<? super C06151> continuation) {
            super(2, continuation);
            this.$this_asMaybe = deferred;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C06151(this.$this_asMaybe, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
            return ((C06151) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Deferred<T> deferred = this.$this_asMaybe;
                this.label = 1;
                obj = deferred.await(this);
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

    public static final <T> Maybe<T> asMaybe(Deferred<? extends T> deferred, CoroutineContext coroutineContext) {
        return RxMaybeKt.rxMaybe(coroutineContext, new C06151(deferred, null));
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.rx3.RxConvertKt$asSingle$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RxConvert.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0003\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u0000*\u00020\u0002H\u008a@"}, d2 = {"", "T", "Lkotlinx/coroutines/CoroutineScope;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.rx3.RxConvertKt$asSingle$1", f = "RxConvert.kt", i = {}, l = {62}, m = "invokeSuspend", n = {}, s = {})
    static final class C06161<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ Deferred<T> $this_asSingle;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C06161(Deferred<? extends T> deferred, Continuation<? super C06161> continuation) {
            super(2, continuation);
            this.$this_asSingle = deferred;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C06161(this.$this_asSingle, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
            return ((C06161) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Deferred<T> deferred = this.$this_asSingle;
                this.label = 1;
                obj = deferred.await(this);
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

    public static final <T> Single<T> asSingle(Deferred<? extends T> deferred, CoroutineContext coroutineContext) {
        return RxSingleKt.rxSingle(coroutineContext, new C06161(deferred, null));
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.rx3.RxConvertKt$asFlow$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RxConvert.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0002H\u008a@"}, d2 = {"", "T", "Lkotlinx/coroutines/channels/ProducerScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.rx3.RxConvertKt$asFlow$1", f = "RxConvert.kt", i = {}, l = {95}, m = "invokeSuspend", n = {}, s = {})
    static final class C06141<T> extends SuspendLambda implements Function2<ProducerScope<? super T>, Continuation<? super Unit>, Object> {
        final /* synthetic */ ObservableSource<T> $this_asFlow;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06141(ObservableSource<T> observableSource, Continuation<? super C06141> continuation) {
            super(2, continuation);
            this.$this_asFlow = observableSource;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06141 c06141 = new C06141(this.$this_asFlow, continuation);
            c06141.L$0 = obj;
            return c06141;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(ProducerScope<? super T> producerScope, Continuation<? super Unit> continuation) {
            return ((C06141) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final ProducerScope producerScope = (ProducerScope) this.L$0;
                final AtomicReference atomicReference = new AtomicReference();
                this.$this_asFlow.subscribe(new Observer<T>() { // from class: kotlinx.coroutines.rx3.RxConvertKt$asFlow$1$observer$1
                    @Override // io.reactivex.rxjava3.core.Observer
                    public void onComplete() {
                        SendChannel.DefaultImpls.close$default(producerScope, null, 1, null);
                    }

                    @Override // io.reactivex.rxjava3.core.Observer
                    public void onSubscribe(Disposable d) {
                        if (CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, null, d)) {
                            return;
                        }
                        d.dispose();
                    }

                    @Override // io.reactivex.rxjava3.core.Observer
                    public void onNext(T t) {
                        try {
                            ChannelsKt.trySendBlocking(producerScope, t);
                        } catch (InterruptedException unused) {
                        }
                    }

                    @Override // io.reactivex.rxjava3.core.Observer
                    public void onError(Throwable e) {
                        producerScope.cancel(e);
                    }
                });
                Function0<Unit> function0 = new Function0<Unit>() { // from class: kotlinx.coroutines.rx3.RxConvertKt.asFlow.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        Disposable andSet = atomicReference.getAndSet(Disposable.disposed());
                        if (andSet != null) {
                            andSet.dispose();
                        }
                    }
                };
                this.label = 1;
                if (ProduceKt.awaitClose(producerScope, function0, this) == coroutine_suspended) {
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

    public static final <T> Flow<T> asFlow(ObservableSource<T> observableSource) {
        return FlowKt.callbackFlow(new C06141(observableSource, null));
    }

    public static final <T> Observable<T> asObservable(final Flow<? extends T> flow, final CoroutineContext coroutineContext) {
        return Observable.create(new ObservableOnSubscribe() { // from class: kotlinx.coroutines.rx3.RxConvertKt$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                RxConvertKt.m4344asObservable$lambda0(coroutineContext, flow, observableEmitter);
            }
        });
    }

    public static /* synthetic */ Observable asObservable$default(Flow flow, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return asObservable(flow, coroutineContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: asObservable$lambda-0, reason: not valid java name */
    public static final void m4344asObservable$lambda0(CoroutineContext coroutineContext, Flow flow, ObservableEmitter observableEmitter) {
        observableEmitter.setCancellable(new RxCancellable(BuildersKt.launch(GlobalScope.INSTANCE, Dispatchers.getUnconfined().plus(coroutineContext), CoroutineStart.ATOMIC, new RxConvertKt$asObservable$1$job$1(flow, observableEmitter, null))));
    }

    public static /* synthetic */ Flowable asFlowable$default(Flow flow, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return asFlowable(flow, coroutineContext);
    }

    public static final <T> Flowable<T> asFlowable(Flow<? extends T> flow, CoroutineContext coroutineContext) {
        return Flowable.fromPublisher(ReactiveFlowKt.asPublisher(flow, coroutineContext));
    }

    public static /* synthetic */ Flowable from$default(Flow flow, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return asFlowable(flow, coroutineContext);
    }

    /* JADX INFO: renamed from: from$default, reason: collision with other method in class */
    public static /* synthetic */ Observable m4347from$default(Flow flow, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return asObservable(flow, coroutineContext);
    }
}
