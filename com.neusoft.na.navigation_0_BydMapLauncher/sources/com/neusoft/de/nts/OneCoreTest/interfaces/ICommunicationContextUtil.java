package com.neusoft.de.nts.OneCoreTest.interfaces;

import com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType;
import com.neusoft.de.nts.OneCoreTest.communication.context.GenericCommContext;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public interface ICommunicationContextUtil<T extends GenericCommContext<?, ?, S, R>, S, R> {
    <V extends BaseNotification> IAttribute<V> createAttribute(Class<V> cls);

    <V extends BaseNotification> IBroadcast<V> createBroadcast(Class<V> cls);

    T createFromClass(BaseRequest baseRequest);

    <V extends BaseNotification> IAttribute<V> createNotification(ESubjectType eSubjectType, Class<V> cls);

    <V extends BaseNotification> IAttribute<V> createNotification(Class<V> cls);

    Executor getDefaultExecutor();

    ITransport<S, R> getDefaultTransport();
}
