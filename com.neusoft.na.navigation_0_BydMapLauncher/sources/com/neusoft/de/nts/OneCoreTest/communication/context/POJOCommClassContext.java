package com.neusoft.de.nts.OneCoreTest.communication.context;

import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public class POJOCommClassContext<RS extends BaseResponse> extends GenericCommContext<BaseRequest, RS, BaseCommand, BaseCommand> {
    private Class<RS> responseClass;

    public POJOCommClassContext(ITransport<BaseCommand, BaseCommand> iTransport, BaseRequest baseRequest, RS rs, Class<RS> cls, Executor executor) {
        super(iTransport, baseRequest, rs, executor);
        this.responseClass = cls;
    }

    public Class<RS> getResponseClass() {
        return this.responseClass;
    }
}
