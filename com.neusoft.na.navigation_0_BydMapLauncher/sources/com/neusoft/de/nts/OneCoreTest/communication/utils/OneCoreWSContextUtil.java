package com.neusoft.de.nts.OneCoreTest.communication.utils;

import com.neusoft.de.nts.OneCoreTest.communication.attribute.ESubjectType;
import com.neusoft.de.nts.OneCoreTest.communication.attribute.OneCoreWSNotification;
import com.neusoft.de.nts.OneCoreTest.communication.context.WSCommClassContext;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public class OneCoreWSContextUtil extends WSContextUtil {
    public OneCoreWSContextUtil(EProjectInterfaceType eProjectInterfaceType, ITransport<String, String> iTransport, Executor executor) {
        super(eProjectInterfaceType, iTransport, executor);
    }

    public OneCoreWSContextUtil(EProjectInterfaceType eProjectInterfaceType, ITransport<String, String> iTransport, Executor executor, Executor executor2) {
        super(eProjectInterfaceType, iTransport, executor, executor2);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.utils.WSContextUtil, com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public <T extends BaseNotification> IAttribute<T> createNotification(ESubjectType eSubjectType, Class<T> cls) {
        return new OneCoreWSNotification(this.project, this.defaultTransport, cls, eSubjectType, null, this.defaultExecutor);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.utils.WSContextUtil, com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil
    public WSCommClassContext<?> createFromClass(BaseRequest baseRequest) {
        return createFromClass(baseRequest, true);
    }
}
