package com.neusoft.de.nts.OneCoreTest.communication.context;

import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public class WSCommClassContext<RS extends BaseResponse> extends GenericCommContext<BaseRequest, RS, String, String> {
    private final EProjectInterfaceType projectInterfaceType;
    private final Class<RS> responseClass;
    private final boolean useToken;

    public WSCommClassContext(EProjectInterfaceType eProjectInterfaceType, ITransport<String, String> iTransport, BaseRequest baseRequest, RS rs, Class<RS> cls, boolean z, Executor executor) {
        super(iTransport, baseRequest, rs, executor);
        this.projectInterfaceType = eProjectInterfaceType;
        this.responseClass = cls;
        this.useToken = z;
    }

    public WSCommClassContext(EProjectInterfaceType eProjectInterfaceType, ITransport<String, String> iTransport, BaseRequest baseRequest, RS rs, Class<RS> cls, boolean z, Executor executor, Executor executor2) {
        super(iTransport, baseRequest, rs, executor, executor2);
        this.projectInterfaceType = eProjectInterfaceType;
        this.responseClass = cls;
        this.useToken = z;
    }

    public Class<RS> getResponseClass() {
        return this.responseClass;
    }

    public EProjectInterfaceType getProjectInterfaceType() {
        return this.projectInterfaceType;
    }

    public boolean isUseToken() {
        return this.useToken;
    }
}
