package com.neusoft.de.nts.OneCoreTest.communication.attribute;

import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.interfaces.IValueUpdater;
import com.neusoft.de.nts.OneCoreTest.utils.FutureUtil;
import java.util.concurrent.Executor;
import java9.util.concurrent.CompletableFuture;
import java9.util.function.Consumer;
import java9.util.function.Function;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.Subject;

/* JADX INFO: loaded from: classes2.dex */
public class GenericAttributeObservable<V, P, S, R> implements IAttribute<V> {
    private Subject<V, V> attributeObservable;
    private Executor executor;
    private boolean isActive;
    private final Class<V> notifyClass;
    private Subscription oneCoreSubscription;
    private Func1<R, P> responseProtocolAdapter;
    private final ESubjectType subjectType;
    private ITransport<S, R> transport;
    private IValueUpdater<V, P> updateInterface;

    public GenericAttributeObservable(Class<V> cls) {
        this(cls, ESubjectType.getDefaultSubjectType(), null);
    }

    public GenericAttributeObservable(Class<V> cls, Executor executor) {
        this(cls, ESubjectType.getDefaultSubjectType(), executor);
    }

    public GenericAttributeObservable(Class<V> cls, ESubjectType eSubjectType, Executor executor) {
        this.isActive = false;
        this.executor = executor;
        this.notifyClass = cls;
        this.subjectType = eSubjectType == null ? ESubjectType.getDefaultSubjectType() : eSubjectType;
    }

    public Executor getExecutor() {
        return this.executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast
    public synchronized CompletableFuture<V> signIn() {
        final CompletableFuture<V> completableFuture;
        completableFuture = new CompletableFuture<>();
        if (!this.isActive) {
            this.attributeObservable = this.subjectType.create();
            CompletableFuture<V> initialValue = this.updateInterface.getInitialValue();
            if (initialValue != null) {
                initialValue.thenAccept((Consumer<? super V>) new Consumer<V>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.attribute.GenericAttributeObservable.2
                    @Override // java9.util.function.Consumer
                    public void accept(V v) {
                        if (v != null) {
                            GenericAttributeObservable.this.attributeObservable.onNext(v);
                        }
                        GenericAttributeObservable genericAttributeObservable = GenericAttributeObservable.this;
                        genericAttributeObservable.oneCoreSubscription = genericAttributeObservable.oneCoreSubscription();
                        completableFuture.complete(v);
                    }
                }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.attribute.GenericAttributeObservable.1
                    @Override // java9.util.function.Function
                    public Void apply(Throwable th) {
                        return (Void) FutureUtil.handleException(th);
                    }
                });
            } else {
                this.oneCoreSubscription = oneCoreSubscription();
                completableFuture.complete(null);
            }
            this.isActive = true;
        } else {
            completableFuture.complete(getValue());
        }
        return completableFuture;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast
    public synchronized CompletableFuture<V> signOut() {
        CompletableFuture<V> completableFutureCompletedFuture;
        completableFutureCompletedFuture = CompletableFuture.completedFuture(getValue());
        if (this.isActive) {
            Subscription subscription = this.oneCoreSubscription;
            if (subscription != null) {
                subscription.unsubscribe();
            }
            this.attributeObservable = null;
            this.isActive = false;
        }
        return completableFutureCompletedFuture;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Subscription oneCoreSubscription() {
        Observable<R> observable = this.transport.getObservable();
        Executor executor = this.executor;
        if (executor != null) {
            observable = observable.observeOn(Schedulers.from(executor));
        }
        return observable.map(new Func1<R, Object>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.attribute.GenericAttributeObservable.6
            @Override // rx.functions.Func1
            public Object call(R r) {
                return GenericAttributeObservable.this.responseProtocolAdapter.call(r);
            }
        }).filter(new Func1<Object, Boolean>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.attribute.GenericAttributeObservable.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // rx.functions.Func1
            public Boolean call(Object obj) {
                return Boolean.valueOf(GenericAttributeObservable.this.updateInterface.match(obj));
            }
        }).map(new Func1<Object, Object>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.attribute.GenericAttributeObservable.4
            @Override // rx.functions.Func1
            public Object call(Object obj) {
                return GenericAttributeObservable.this.updateInterface.fromProtocol(obj);
            }
        }).subscribe(new Action1<Object>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.attribute.GenericAttributeObservable.3
            @Override // rx.functions.Action1
            public void call(Object obj) {
                if (GenericAttributeObservable.this.attributeObservable != null) {
                    GenericAttributeObservable.this.attributeObservable.onNext(obj);
                }
            }
        });
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast
    public Observable<V> getObservable() {
        Subject<V, V> subject = this.attributeObservable;
        if (subject == null) {
            throw new IllegalStateException("Observable not available. No SignIn done");
        }
        return subject.onBackpressureBuffer();
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IGettableAttribute
    public V getValue() {
        Subject<V, V> subject = this.attributeObservable;
        if (subject instanceof BehaviorSubject) {
            return (V) ((BehaviorSubject) subject).getValue();
        }
        return null;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ISettableAttribute
    public void setValue(V v) {
        this.attributeObservable.onNext(v);
    }

    public ITransport<S, R> getTransport() {
        return this.transport;
    }

    public void setTransport(ITransport<S, R> iTransport) {
        this.transport = iTransport;
    }

    public IValueUpdater<V, P> getUpdateInterface() {
        return this.updateInterface;
    }

    public void setUpdateInterface(IValueUpdater<V, P> iValueUpdater) {
        this.updateInterface = iValueUpdater;
    }

    public void setResponseProtocolAdapter(Func1<R, P> func1) {
        this.responseProtocolAdapter = func1;
    }

    public ESubjectType getSubjectType() {
        return this.subjectType;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast
    public Class<V> getNotifyClass() {
        return this.notifyClass;
    }
}
