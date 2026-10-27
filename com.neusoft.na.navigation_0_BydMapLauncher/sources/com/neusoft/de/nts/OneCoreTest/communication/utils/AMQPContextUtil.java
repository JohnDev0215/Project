package com.neusoft.de.nts.OneCoreTest.communication.utils;

import com.neusoft.de.nts.OneCoreTest.communication.attribute.AMQPNotification;
import com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType;
import com.neusoft.de.nts.OneCoreTest.communication.context.AMQPCommClassContext;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public class AMQPContextUtil implements ICommunicationContextUtil<AMQPCommClassContext<?>, BaseCommand, String> {
    private final ITransport<BaseCommand, String> amqpTransport;
    private final Executor defaultExecutor;
    private final EProjectInterfaceType project;
    private final String senderRoutingKey;

    public AMQPContextUtil(EProjectInterfaceType eProjectInterfaceType, ITransport<BaseCommand, String> iTransport, String str, Executor executor) {
        this.project = eProjectInterfaceType;
        this.amqpTransport = iTransport;
        this.senderRoutingKey = str;
        this.defaultExecutor = executor;
    }

    public EProjectInterfaceType getProject() {
        return this.project;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public ITransport<BaseCommand, String> getDefaultTransport() {
        return this.amqpTransport;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public Executor getDefaultExecutor() {
        return this.defaultExecutor;
    }

    public String getSenderRoutingKey() {
        return this.senderRoutingKey;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public AMQPCommClassContext<?> createFromClass(BaseRequest baseRequest) {
        baseRequest.setSenderRoutingKey(getSenderRoutingKey());
        if (baseRequest.getResponse() == null) {
            return new AMQPCommClassContext<>(this.project, getDefaultTransport(), baseRequest, null, BaseResponse.class, getDefaultExecutor());
        }
        return new AMQPCommClassContext<>(this.project, getDefaultTransport(), baseRequest, baseRequest.getResponse(), baseRequest.getResponse().getClass(), getDefaultExecutor());
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public <T extends BaseNotification> AMQPNotification<T> createNotification(ESubjectType eSubjectType, Class<T> cls) {
        return new AMQPNotification<>(this.project, getDefaultTransport(), cls, eSubjectType, null, getDefaultExecutor());
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
