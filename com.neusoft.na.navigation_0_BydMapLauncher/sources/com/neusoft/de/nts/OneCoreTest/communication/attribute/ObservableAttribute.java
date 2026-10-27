package com.neusoft.de.nts.OneCoreTest.communication.attribute;

import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import java9.util.concurrent.CompletableFuture;
import rx.Observable;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;

/* JADX INFO: loaded from: classes2.dex */
public class ObservableAttribute<V, S> implements IAttribute<V> {
    private final BehaviorSubject<V> attribute;
    private final ITransport<S, ?> client;
    private final Class<V> notifyClass;
    private final Func1<V, S> transportAdaptor;

    @Deprecated
    public ObservableAttribute(ITransport<S, ?> iTransport, Func1<V, S> func1) {
        this(null, iTransport, func1);
    }

    public ObservableAttribute(Class<V> cls, ITransport<S, ?> iTransport, Func1<V, S> func1) {
        this.attribute = BehaviorSubject.create();
        this.notifyClass = cls;
        this.client = iTransport;
        this.transportAdaptor = func1;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast
    public CompletableFuture<V> signIn() {
        return CompletableFuture.completedFuture(null);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast
    public CompletableFuture<V> signOut() {
        return CompletableFuture.completedFuture(getValue());
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast
    public Observable<V> getObservable() {
        return this.attribute.onBackpressureBuffer();
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IGettableAttribute
    public V getValue() {
        return this.attribute.getValue();
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ISettableAttribute
    public void setValue(V v) {
        this.attribute.onNext(v);
        this.client.send(this.transportAdaptor.call(v));
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast
    public Class<V> getNotifyClass() {
        return this.notifyClass;
    }
}
