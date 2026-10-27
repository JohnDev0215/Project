package com.neusoft.de.nts.OneCoreTest.communication.utils;

import com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType;
import com.neusoft.de.nts.OneCoreTest.communication.attribute.WSNotification;
import com.neusoft.de.nts.OneCoreTest.communication.context.WSCommClassContext;
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
public class WSContextUtil implements ICommunicationContextUtil<WSCommClassContext<?>, String, String> {
    protected final Executor defaultExecutor;
    protected final ITransport<String, String> defaultTransport;
    protected final EProjectInterfaceType project;
    protected final Executor transportExecutor;

    public WSContextUtil(EProjectInterfaceType eProjectInterfaceType, ITransport<String, String> iTransport, Executor executor) {
        this.project = eProjectInterfaceType;
        this.defaultTransport = iTransport;
        this.defaultExecutor = executor;
        this.transportExecutor = null;
    }

    public WSContextUtil(EProjectInterfaceType eProjectInterfaceType, ITransport<String, String> iTransport, Executor executor, Executor executor2) {
        this.project = eProjectInterfaceType;
        this.defaultTransport = iTransport;
        this.defaultExecutor = executor;
        this.transportExecutor = executor2;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public ITransport<String, String> getDefaultTransport() {
        return this.defaultTransport;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public Executor getDefaultExecutor() {
        return this.defaultExecutor;
    }

    public Executor getTransportExecutor() {
        return this.transportExecutor;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public WSCommClassContext<?> createFromClass(BaseRequest baseRequest) {
        return createFromClass(baseRequest, false);
    }

    protected WSCommClassContext<?> createFromClass(BaseRequest baseRequest, boolean z) {
        if (baseRequest.getResponse() == null) {
            return new WSCommClassContext<>(this.project, getDefaultTransport(), baseRequest, null, BaseResponse.class, z, getDefaultExecutor(), getTransportExecutor());
        }
        return new WSCommClassContext<>(this.project, getDefaultTransport(), baseRequest, baseRequest.getResponse(), baseRequest.getResponse().getClass(), z, getDefaultExecutor(), getTransportExecutor());
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public <T extends BaseNotification> IAttribute<T> createNotification(ESubjectType eSubjectType, Class<T> cls) {
        return new WSNotification(this.project, this.defaultTransport, cls, eSubjectType, null, this.defaultExecutor);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public <T extends BaseNotification> IAttribute<T> createNotification(Class<T> cls) {
        return createNotification(ESubjectType.Behaviour, cls);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public <T extends BaseNotification> IBroadcast<T> createBroadcast(Class<T> cls) {
        return createNotification(ESubjectType.Publish, cls);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public <T extends BaseNotification> IAttribute<T> createAttribute(Class<T> cls) {
        return createNotification(cls);
    }
}
