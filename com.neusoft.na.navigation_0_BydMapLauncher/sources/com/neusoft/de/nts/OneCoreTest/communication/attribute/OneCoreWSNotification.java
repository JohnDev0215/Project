package com.neusoft.de.nts.OneCoreTest.communication.attribute;

import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java.util.concurrent.Executor;
import java9.util.concurrent.CompletableFuture;
import rx.Observable;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes2.dex */
public class OneCoreWSNotification<V extends BaseNotification> extends OneCoreWSNotificationWithoutInitialFuture<V> {
    private static final Logger logger = Logger.getLogger((Class<?>) OneCoreWSNotification.class);

    @Override // com.neusoft.de.nts.OneCoreTest.communication.attribute.OneCoreWSNotificationWithoutInitialFuture, com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast
    public /* bridge */ /* synthetic */ Class getNotifyClass() {
        return super.getNotifyClass();
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.attribute.OneCoreWSNotificationWithoutInitialFuture, com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast
    public /* bridge */ /* synthetic */ Observable getObservable() {
        return super.getObservable();
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.attribute.OneCoreWSNotificationWithoutInitialFuture, com.neusoft.de.nts.OneCoreTest.interfaces.IGettableAttribute
    public /* bridge */ /* synthetic */ BaseNotification getValue() {
        return super.getValue();
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.attribute.OneCoreWSNotificationWithoutInitialFuture
    public /* bridge */ /* synthetic */ void setValue(BaseNotification baseNotification) {
        super.setValue(baseNotification);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.attribute.OneCoreWSNotificationWithoutInitialFuture, com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast
    public /* bridge */ /* synthetic */ CompletableFuture signIn() {
        return super.signIn();
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.attribute.OneCoreWSNotificationWithoutInitialFuture, com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast
    public /* bridge */ /* synthetic */ CompletableFuture signOut() {
        return super.signOut();
    }

    public OneCoreWSNotification(EProjectInterfaceType eProjectInterfaceType, ITransport<String, String> iTransport, Class<V> cls, ESubjectType eSubjectType, Func1<BaseResponse, V> func1, Executor executor) {
        super(eProjectInterfaceType, iTransport, cls, eSubjectType, func1, executor);
    }

    public OneCoreWSNotification(EProjectInterfaceType eProjectInterfaceType, ITransport<String, String> iTransport, Class<V> cls) {
        this(eProjectInterfaceType, iTransport, cls, null, null);
    }

    public OneCoreWSNotification(EProjectInterfaceType eProjectInterfaceType, ITransport<String, String> iTransport, Class<V> cls, Executor executor) {
        this(eProjectInterfaceType, iTransport, cls, null, executor);
    }

    public OneCoreWSNotification(EProjectInterfaceType eProjectInterfaceType, ITransport<String, String> iTransport, Class<V> cls, Func1<BaseResponse, V> func1) {
        this(eProjectInterfaceType, iTransport, cls, null, func1, null);
    }

    public OneCoreWSNotification(EProjectInterfaceType eProjectInterfaceType, ITransport<String, String> iTransport, Class<V> cls, Func1<BaseResponse, V> func1, Executor executor) {
        this(eProjectInterfaceType, iTransport, cls, null, func1, executor);
    }
}
