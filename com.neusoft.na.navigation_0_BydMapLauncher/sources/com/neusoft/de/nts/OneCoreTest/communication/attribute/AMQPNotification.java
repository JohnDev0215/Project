package com.neusoft.de.nts.OneCoreTest.communication.attribute;

import com.neusoft.de.nts.OneCoreTest.communication.Communication;
import com.neusoft.de.nts.OneCoreTest.communication.context.AMQPCommClassContext;
import com.neusoft.de.nts.OneCoreTest.communication.context.GenericCommContext;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.interfaces.IValueUpdater;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executor;
import java9.util.concurrent.CompletableFuture;
import java9.util.concurrent.CompletionStage;
import java9.util.function.Function;
import rx.Observable;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes2.dex */
public class AMQPNotification<V extends BaseNotification> implements IValueUpdater<V, String>, IAttribute<V> {
    private static final Logger logger = Logger.getLogger((Class<?>) AMQPNotification.class);
    private final GenericAttributeObservable<V, String, BaseCommand, String> attribute;
    private final Executor executor;
    private final Class<V> notifyClass;
    private final EProjectInterfaceType project;
    private final Func1<BaseResponse, V> responseValueAdaptor;
    private V value;

    public AMQPNotification(EProjectInterfaceType eProjectInterfaceType, ITransport<BaseCommand, String> iTransport, Class<V> cls) {
        this(eProjectInterfaceType, iTransport, cls, null, null);
    }

    public AMQPNotification(EProjectInterfaceType eProjectInterfaceType, ITransport<BaseCommand, String> iTransport, Class<V> cls, Executor executor) {
        this(eProjectInterfaceType, iTransport, cls, null, executor);
    }

    public AMQPNotification(EProjectInterfaceType eProjectInterfaceType, ITransport<BaseCommand, String> iTransport, Class<V> cls, Func1<BaseResponse, V> func1) {
        this(eProjectInterfaceType, iTransport, cls, null, func1, null);
    }

    public AMQPNotification(EProjectInterfaceType eProjectInterfaceType, ITransport<BaseCommand, String> iTransport, Class<V> cls, Func1<BaseResponse, V> func1, Executor executor) {
        this(eProjectInterfaceType, iTransport, cls, null, func1, executor);
    }

    public AMQPNotification(EProjectInterfaceType eProjectInterfaceType, ITransport<BaseCommand, String> iTransport, Class<V> cls, ESubjectType eSubjectType, Func1<BaseResponse, V> func1, Executor executor) {
        this.project = eProjectInterfaceType;
        this.responseValueAdaptor = func1;
        this.executor = executor;
        this.notifyClass = cls;
        this.value = (V) create();
        if (eSubjectType == null) {
            this.attribute = new GenericAttributeObservable<>(cls, executor);
        } else {
            this.attribute = new GenericAttributeObservable<>(cls, eSubjectType, executor);
        }
        this.attribute.setTransport(iTransport);
        this.attribute.setResponseProtocolAdapter(new Func1<String, String>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.attribute.AMQPNotification.1
            @Override // rx.functions.Func1
            public String call(String str) {
                return str;
            }
        });
        this.attribute.setUpdateInterface(this);
    }

    protected ITransport<BaseCommand, String> getTransport() {
        return this.attribute.getTransport();
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast
    public Observable<V> getObservable() {
        return this.attribute.getObservable();
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IGettableAttribute
    public V getValue() {
        return this.attribute.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public V updateValueFromResponse(BaseResponse baseResponse) {
        Func1<BaseResponse, V> func1 = this.responseValueAdaptor;
        if (func1 != null) {
            this.value = func1.call(baseResponse);
        }
        return this.value;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IValueUpdater
    public V create() {
        V vNewInstance;
        try {
            vNewInstance = this.notifyClass.getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            logger.error(e, "Could not create a new class instance %s", this.notifyClass.getName());
            vNewInstance = null;
        }
        this.value = vNewInstance;
        return vNewInstance;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IValueUpdater
    public V fromProtocol(String str) {
        V v = (V) this.project.getProtocol().from(str, this.notifyClass);
        this.value = v;
        return v;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IValueUpdater
    public boolean match(String str) {
        return this.project.getMatchFunction().apply(this.value, (BaseNotification) this.project.getProtocol().from(str, BaseNotification.class)).booleanValue();
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IValueUpdater
    public CompletableFuture<V> getInitialValue() {
        if (this.value.getInitialRequest() != null) {
            return (CompletableFuture<V>) Communication.request((GenericCommContext) new AMQPCommClassContext(this.project, getTransport(), this.value.getInitialRequest(), this.value.getInitialRequest().getResponse(), this.notifyClass, this.executor)).thenCompose((Function<? super Object, ? extends CompletionStage<U>>) new Function<BaseResponse, CompletionStage<V>>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.attribute.AMQPNotification.2
                @Override // java9.util.function.Function
                public CompletionStage<V> apply(BaseResponse baseResponse) {
                    return CompletableFuture.completedFuture(AMQPNotification.this.updateValueFromResponse(baseResponse));
                }
            });
        }
        return null;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast
    public synchronized CompletableFuture<V> signIn() {
        return this.attribute.signIn();
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast
    public synchronized CompletableFuture<V> signOut() {
        return this.attribute.signOut();
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ISettableAttribute
    public void setValue(V v) {
        this.attribute.setValue(v);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast
    public Class<V> getNotifyClass() {
        return this.notifyClass;
    }
}
