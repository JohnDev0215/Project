package com.neusoft.de.nts.OneCoreTest.communication.context;

import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public class AMQPCommClassContext<RS extends BaseResponse> extends GenericCommContext<BaseRequest, RS, BaseCommand, String> {
    private final EProjectInterfaceType projectInterfaceType;
    private Class<RS> responseClass;

    public AMQPCommClassContext(EProjectInterfaceType eProjectInterfaceType, ITransport<BaseCommand, String> iTransport, BaseRequest baseRequest, RS rs, Class<RS> cls, Executor executor) {
        super(iTransport, baseRequest, rs, executor);
        this.projectInterfaceType = eProjectInterfaceType;
        this.responseClass = cls;
    }

    public EProjectInterfaceType getProjectInterfaceType() {
        return this.projectInterfaceType;
    }

    public Class<RS> getResponseClass() {
        return this.responseClass;
    }
}
