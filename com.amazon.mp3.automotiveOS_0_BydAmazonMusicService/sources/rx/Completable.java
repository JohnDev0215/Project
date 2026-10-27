package rx;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.internal.util.SubscriptionList;
import rx.internal.util.UtilityFunctions;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.BooleanSubscription;
import rx.subscriptions.MultipleAssignmentSubscription;
import rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes6.dex */
public class Completable {
    static final Completable COMPLETE = new Completable(new OnSubscribe() { // from class: rx.Completable.1
        @Override // rx.functions.Action1
        public void call(CompletableSubscriber completableSubscriber) {
            completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
            completableSubscriber.onCompleted();
        }
    }, false);
    static final Completable NEVER = new Completable(new OnSubscribe() { // from class: rx.Completable.2
        @Override // rx.functions.Action1
        public void call(CompletableSubscriber completableSubscriber) {
            completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
        }
    }, false);
    private final OnSubscribe onSubscribe;

    public interface OnSubscribe extends Action1<CompletableSubscriber> {
    }

    public interface Operator extends Func1<CompletableSubscriber, CompletableSubscriber> {
    }

    public static Completable create(OnSubscribe onSubscribe) {
        requireNonNull(onSubscribe);
        try {
            return new Completable(onSubscribe);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            RxJavaHooks.onError(th);
            throw toNpe(th);
        }
    }

    public static Completable defer(final Func0<? extends Completable> func0) {
        requireNonNull(func0);
        return create(new OnSubscribe() { // from class: rx.Completable.5
            @Override // rx.functions.Action1
            public void call(CompletableSubscriber completableSubscriber) {
                try {
                    Completable completable = (Completable) func0.call();
                    if (completable == null) {
                        completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                        completableSubscriber.onError(new NullPointerException("The completable returned is null"));
                    } else {
                        completable.unsafeSubscribe(completableSubscriber);
                    }
                } catch (Throwable th) {
                    completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                    completableSubscriber.onError(th);
                }
            }
        });
    }

    public static Completable fromAction(final Action0 action0) {
        requireNonNull(action0);
        return create(new OnSubscribe() { // from class: rx.Completable.8
            @Override // rx.functions.Action1
            public void call(CompletableSubscriber completableSubscriber) {
                BooleanSubscription booleanSubscription = new BooleanSubscription();
                completableSubscriber.onSubscribe(booleanSubscription);
                try {
                    action0.call();
                    if (booleanSubscription.isUnsubscribed()) {
                        return;
                    }
                    completableSubscriber.onCompleted();
                } catch (Throwable th) {
                    if (booleanSubscription.isUnsubscribed()) {
                        return;
                    }
                    completableSubscriber.onError(th);
                }
            }
        });
    }

    public static Completable fromObservable(final Observable<?> observable) {
        requireNonNull(observable);
        return create(new OnSubscribe() { // from class: rx.Completable.10
            @Override // rx.functions.Action1
            public void call(final CompletableSubscriber completableSubscriber) {
                Subscriber<Object> subscriber = new Subscriber<Object>() { // from class: rx.Completable.10.1
                    @Override // rx.Observer
                    public void onNext(Object obj) {
                    }

                    @Override // rx.Observer
                    public void onCompleted() {
                        completableSubscriber.onCompleted();
                    }

                    @Override // rx.Observer
                    public void onError(Throwable th) {
                        completableSubscriber.onError(th);
                    }
                };
                completableSubscriber.onSubscribe(subscriber);
                observable.unsafeSubscribe(subscriber);
            }
        });
    }

    static <T> T requireNonNull(T t) {
        t.getClass();
        return t;
    }

    public static Completable timer(final long j, final TimeUnit timeUnit, final Scheduler scheduler) {
        requireNonNull(timeUnit);
        requireNonNull(scheduler);
        return create(new OnSubscribe() { // from class: rx.Completable.12
            @Override // rx.functions.Action1
            public void call(final CompletableSubscriber completableSubscriber) {
                MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
                completableSubscriber.onSubscribe(multipleAssignmentSubscription);
                if (multipleAssignmentSubscription.isUnsubscribed()) {
                    return;
                }
                final Scheduler.Worker workerCreateWorker = scheduler.createWorker();
                multipleAssignmentSubscription.set(workerCreateWorker);
                workerCreateWorker.schedule(new Action0() { // from class: rx.Completable.12.1
                    @Override // rx.functions.Action0
                    public void call() {
                        try {
                            completableSubscriber.onCompleted();
                        } finally {
                            workerCreateWorker.unsubscribe();
                        }
                    }
                }, j, timeUnit);
            }
        });
    }

    static NullPointerException toNpe(Throwable th) {
        NullPointerException nullPointerException = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
        nullPointerException.initCause(th);
        return nullPointerException;
    }

    protected Completable(OnSubscribe onSubscribe) {
        this.onSubscribe = RxJavaHooks.onCreate(onSubscribe);
    }

    protected Completable(OnSubscribe onSubscribe, boolean z) {
        this.onSubscribe = z ? RxJavaHooks.onCreate(onSubscribe) : onSubscribe;
    }

    public final Completable doOnCompleted(Action0 action0) {
        return doOnLifecycle(Actions.empty(), Actions.empty(), action0, Actions.empty(), Actions.empty());
    }

    public final Completable doOnError(Action1<? super Throwable> action1) {
        return doOnLifecycle(Actions.empty(), action1, Actions.empty(), Actions.empty(), Actions.empty());
    }

    protected final Completable doOnLifecycle(Action1<? super Subscription> action1, Action1<? super Throwable> action12, Action0 action0, Action0 action02, Action0 action03) {
        requireNonNull(action1);
        requireNonNull(action12);
        requireNonNull(action0);
        requireNonNull(action02);
        requireNonNull(action03);
        return create(new AnonymousClass19(action0, action02, action12, action1, action03));
    }

    /* JADX INFO: renamed from: rx.Completable$19, reason: invalid class name */
    class AnonymousClass19 implements OnSubscribe {
        final /* synthetic */ Action0 val$onAfterTerminate;
        final /* synthetic */ Action0 val$onComplete;
        final /* synthetic */ Action1 val$onError;
        final /* synthetic */ Action1 val$onSubscribe;
        final /* synthetic */ Action0 val$onUnsubscribe;

        AnonymousClass19(Action0 action0, Action0 action02, Action1 action1, Action1 action12, Action0 action03) {
            this.val$onComplete = action0;
            this.val$onAfterTerminate = action02;
            this.val$onError = action1;
            this.val$onSubscribe = action12;
            this.val$onUnsubscribe = action03;
        }

        @Override // rx.functions.Action1
        public void call(final CompletableSubscriber completableSubscriber) {
            Completable.this.unsafeSubscribe(new CompletableSubscriber() { // from class: rx.Completable.19.1
                @Override // rx.CompletableSubscriber
                public void onCompleted() {
                    try {
                        AnonymousClass19.this.val$onComplete.call();
                        completableSubscriber.onCompleted();
                        try {
                            AnonymousClass19.this.val$onAfterTerminate.call();
                        } catch (Throwable th) {
                            RxJavaHooks.onError(th);
                        }
                    } catch (Throwable th2) {
                        completableSubscriber.onError(th2);
                    }
                }

                @Override // rx.CompletableSubscriber
                public void onError(Throwable th) {
                    try {
                        AnonymousClass19.this.val$onError.call(th);
                    } catch (Throwable th2) {
                        th = new CompositeException(Arrays.asList(th, th2));
                    }
                    completableSubscriber.onError(th);
                    try {
                        AnonymousClass19.this.val$onAfterTerminate.call();
                    } catch (Throwable th3) {
                        RxJavaHooks.onError(th3);
                    }
                }

                @Override // rx.CompletableSubscriber
                public void onSubscribe(final Subscription subscription) {
                    try {
                        AnonymousClass19.this.val$onSubscribe.call(subscription);
                        completableSubscriber.onSubscribe(Subscriptions.create(new Action0() { // from class: rx.Completable.19.1.1
                            @Override // rx.functions.Action0
                            public void call() {
                                try {
                                    AnonymousClass19.this.val$onUnsubscribe.call();
                                } catch (Throwable th) {
                                    RxJavaHooks.onError(th);
                                }
                                subscription.unsubscribe();
                            }
                        }));
                    } catch (Throwable th) {
                        subscription.unsubscribe();
                        completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                        completableSubscriber.onError(th);
                    }
                }
            });
        }
    }

    public final Completable observeOn(final Scheduler scheduler) {
        requireNonNull(scheduler);
        return create(new OnSubscribe() { // from class: rx.Completable.24
            @Override // rx.functions.Action1
            public void call(final CompletableSubscriber completableSubscriber) {
                final SubscriptionList subscriptionList = new SubscriptionList();
                final Scheduler.Worker workerCreateWorker = scheduler.createWorker();
                subscriptionList.add(workerCreateWorker);
                completableSubscriber.onSubscribe(subscriptionList);
                Completable.this.unsafeSubscribe(new CompletableSubscriber() { // from class: rx.Completable.24.1
                    @Override // rx.CompletableSubscriber
                    public void onCompleted() {
                        workerCreateWorker.schedule(new Action0() { // from class: rx.Completable.24.1.1
                            @Override // rx.functions.Action0
                            public void call() {
                                try {
                                    completableSubscriber.onCompleted();
                                } finally {
                                    subscriptionList.unsubscribe();
                                }
                            }
                        });
                    }

                    @Override // rx.CompletableSubscriber
                    public void onError(final Throwable th) {
                        workerCreateWorker.schedule(new Action0() { // from class: rx.Completable.24.1.2
                            @Override // rx.functions.Action0
                            public void call() {
                                try {
                                    completableSubscriber.onError(th);
                                } finally {
                                    subscriptionList.unsubscribe();
                                }
                            }
                        });
                    }

                    @Override // rx.CompletableSubscriber
                    public void onSubscribe(Subscription subscription) {
                        subscriptionList.add(subscription);
                    }
                });
            }
        });
    }

    public final Completable onErrorComplete() {
        return onErrorComplete(UtilityFunctions.alwaysTrue());
    }

    public final Completable onErrorComplete(final Func1<? super Throwable, Boolean> func1) {
        requireNonNull(func1);
        return create(new OnSubscribe() { // from class: rx.Completable.25
            @Override // rx.functions.Action1
            public void call(final CompletableSubscriber completableSubscriber) {
                Completable.this.unsafeSubscribe(new CompletableSubscriber() { // from class: rx.Completable.25.1
                    @Override // rx.CompletableSubscriber
                    public void onCompleted() {
                        completableSubscriber.onCompleted();
                    }

                    @Override // rx.CompletableSubscriber
                    public void onError(Throwable th) {
                        boolean zBooleanValue = false;
                        try {
                            zBooleanValue = ((Boolean) func1.call(th)).booleanValue();
                        } catch (Throwable th2) {
                            Exceptions.throwIfFatal(th2);
                            th = new CompositeException(Arrays.asList(th, th2));
                        }
                        if (zBooleanValue) {
                            completableSubscriber.onCompleted();
                        } else {
                            completableSubscriber.onError(th);
                        }
                    }

                    @Override // rx.CompletableSubscriber
                    public void onSubscribe(Subscription subscription) {
                        completableSubscriber.onSubscribe(subscription);
                    }
                });
            }
        });
    }

    public final Subscription subscribe() {
        final MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
        unsafeSubscribe(new CompletableSubscriber() { // from class: rx.Completable.27
            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                multipleAssignmentSubscription.unsubscribe();
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                RxJavaHooks.onError(th);
                multipleAssignmentSubscription.unsubscribe();
                Completable.deliverUncaughtException(th);
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                multipleAssignmentSubscription.set(subscription);
            }
        });
        return multipleAssignmentSubscription;
    }

    public final Subscription subscribe(final Action0 action0) {
        requireNonNull(action0);
        final MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
        unsafeSubscribe(new CompletableSubscriber() { // from class: rx.Completable.28
            boolean done;

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                this.done = true;
                try {
                    action0.call();
                } finally {
                    try {
                    } finally {
                    }
                }
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                RxJavaHooks.onError(th);
                multipleAssignmentSubscription.unsubscribe();
                Completable.deliverUncaughtException(th);
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                multipleAssignmentSubscription.set(subscription);
            }
        });
        return multipleAssignmentSubscription;
    }

    public final Subscription subscribe(final Action0 action0, final Action1<? super Throwable> action1) {
        requireNonNull(action0);
        requireNonNull(action1);
        final MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
        unsafeSubscribe(new CompletableSubscriber() { // from class: rx.Completable.29
            boolean done;

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                this.done = true;
                try {
                    action0.call();
                    multipleAssignmentSubscription.unsubscribe();
                } catch (Throwable th) {
                    callOnError(th);
                }
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                if (!this.done) {
                    this.done = true;
                    callOnError(th);
                } else {
                    RxJavaHooks.onError(th);
                    Completable.deliverUncaughtException(th);
                }
            }

            void callOnError(Throwable th) {
                try {
                    action1.call(th);
                } finally {
                    try {
                    } finally {
                    }
                }
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                multipleAssignmentSubscription.set(subscription);
            }
        });
        return multipleAssignmentSubscription;
    }

    static void deliverUncaughtException(Throwable th) {
        Thread threadCurrentThread = Thread.currentThread();
        threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, th);
    }

    public final void unsafeSubscribe(CompletableSubscriber completableSubscriber) {
        requireNonNull(completableSubscriber);
        try {
            RxJavaHooks.onCompletableStart(this, this.onSubscribe).call(completableSubscriber);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            Throwable thOnCompletableError = RxJavaHooks.onCompletableError(th);
            RxJavaHooks.onError(thOnCompletableError);
            throw toNpe(thOnCompletableError);
        }
    }

    public final Completable subscribeOn(final Scheduler scheduler) {
        requireNonNull(scheduler);
        return create(new OnSubscribe() { // from class: rx.Completable.31
            @Override // rx.functions.Action1
            public void call(final CompletableSubscriber completableSubscriber) {
                final Scheduler.Worker workerCreateWorker = scheduler.createWorker();
                workerCreateWorker.schedule(new Action0() { // from class: rx.Completable.31.1
                    @Override // rx.functions.Action0
                    public void call() {
                        try {
                            Completable.this.unsafeSubscribe(completableSubscriber);
                        } finally {
                            workerCreateWorker.unsubscribe();
                        }
                    }
                });
            }
        });
    }
}
