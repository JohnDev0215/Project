package com.neusoft.de.nts.OneCoreTest.communication.utils;

import com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType;
import com.neusoft.de.nts.OneCoreTest.communication.attribute.POJONotification;
import com.neusoft.de.nts.OneCoreTest.communication.context.POJOCommClassContext;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public class PojoContextUtil implements ICommunicationContextUtil<POJOCommClassContext<?>, BaseCommand, BaseCommand> {
    private final Executor defaultExecutor;
    private final ITransport<BaseCommand, BaseCommand> transport;

    public PojoContextUtil(ITransport<BaseCommand, BaseCommand> iTransport, Executor executor) {
        this.transport = iTransport;
        this.defaultExecutor = executor;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public ITransport<BaseCommand, BaseCommand> getDefaultTransport() {
        return this.transport;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public Executor getDefaultExecutor() {
        return this.defaultExecutor;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public POJOCommClassContext<?> createFromClass(BaseRequest baseRequest) {
        if (baseRequest.getResponse() == null) {
            return new POJOCommClassContext<>(getDefaultTransport(), baseRequest, null, BaseResponse.class, getDefaultExecutor());
        }
        return new POJOCommClassContext<>(getDefaultTransport(), baseRequest, baseRequest.getResponse(), baseRequest.getResponse().getClass(), getDefaultExecutor());
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public <T extends BaseNotification> POJONotification<T> createNotification(ESubjectType eSubjectType, Class<T> cls) {
        return new POJONotification<>(getDefaultTransport(), cls, eSubjectType, null, getDefaultExecutor());
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public <T extends BaseNotification> IAttribute<T> createNotification(Class<T> cls) {
        return createNotification(ESubjectType.Behaviour, (Class) cls);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public <T extends BaseNotification> IBroadcast<T> createBroadcast(Class<T> cls) {
        return createNotification(ESubjectType.Publish, (Class) cls);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public <T extends BaseNotification> IAttribute<T> createAttribute(Class<T> cls) {
        return createNotification(cls);
    }
}
