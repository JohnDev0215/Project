package com.neusoft.de.nts.OneCoreTest.communication.future;

import com.neusoft.de.nts.OneCoreTest.communication.context.POJOCommClassContext;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseToken;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import java.util.concurrent.atomic.AtomicLong;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes2.dex */
public class POJOClassFuture<RS extends BaseResponse> extends GenericOneCoreFuture<BaseRequest, RS, BaseCommand, BaseCommand> {
    private static final AtomicLong sequenceID = new AtomicLong(0);
    private Class<RS> responseClass;

    public POJOClassFuture(POJOCommClassContext<RS> pOJOCommClassContext, boolean z) {
        setCommunicationConfig(pOJOCommClassContext, z);
        create();
    }

    public POJOClassFuture(POJOCommClassContext<RS> pOJOCommClassContext, boolean z, Func1<RS, Boolean> func1) {
        setResponseFilterFunction(func1);
        setCommunicationConfig(pOJOCommClassContext, z);
        create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean compareResponseForClass(BaseCommand baseCommand) {
        return baseCommand.getMessageType() == EMessageType.Result && getRequest().getToken().equals(baseCommand.getToken());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RS from(BaseCommand baseCommand) {
        return this.responseClass.cast(baseCommand);
    }

    private void setCommunicationConfig(POJOCommClassContext<RS> pOJOCommClassContext, boolean z) {
        this.responseClass = pOJOCommClassContext.getResponseClass();
        pOJOCommClassContext.getRequest().setToken(new BaseToken(Long.valueOf(sequenceID.getAndIncrement())));
        setTransport(pOJOCommClassContext.getTransport());
        setRequest(pOJOCommClassContext.getRequest());
        setEmptyResponse(null);
        setExpectedResponse(pOJOCommClassContext.getExpectedResponse());
        setTransportRequestAdaptor(new Func1<BaseRequest, BaseCommand>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.POJOClassFuture.1
            @Override // rx.functions.Func1
            public BaseCommand call(BaseRequest baseRequest) {
                return baseRequest;
            }
        });
        setFilterFunction(new Func1<BaseCommand, Boolean>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.POJOClassFuture.2
            @Override // rx.functions.Func1
            public Boolean call(BaseCommand baseCommand) {
                return Boolean.valueOf(POJOClassFuture.this.compareResponseForClass(baseCommand));
            }
        });
        setTransportResponseAdaptor(new Func1<BaseCommand, RS>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.POJOClassFuture.3
            @Override // rx.functions.Func1
            public RS call(BaseCommand baseCommand) {
                return (RS) POJOClassFuture.this.from(baseCommand);
            }
        });
        if (z) {
            setExecutor(pOJOCommClassContext.getTransportExecutor());
        } else {
            setExecutor(pOJOCommClassContext.getExecutor());
        }
    }
}
