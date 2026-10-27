package com.neusoft.de.nts.OneCoreTest.communication.future;

import com.neusoft.de.nts.OneCoreTest.communication.context.WSCommClassContext;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseToken;
import com.neusoft.de.nts.OneCoreTest.interfaces.IProtocol;
import java.util.concurrent.atomic.AtomicLong;
import java9.util.function.BiFunction;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes2.dex */
public class OneCoreClassFuture<RS extends BaseResponse> extends GenericOneCoreFuture<BaseRequest, RS, String, String> {
    private static final String emptyResponse = "{ \"command\":\"empty\",\"component\":\"nav\" }";
    private static final AtomicLong sequenceID = new AtomicLong(0);
    private BiFunction<BaseCommand, BaseCommand, Boolean> commandMatchFunction;
    private IProtocol<BaseCommand, String> protocol;
    private Class<RS> responseClass;
    private boolean useToken;

    public OneCoreClassFuture(WSCommClassContext<RS> wSCommClassContext, boolean z) {
        setCommunicationConfig(wSCommClassContext, z);
        create();
    }

    public OneCoreClassFuture(WSCommClassContext<RS> wSCommClassContext, boolean z, Func1<RS, Boolean> func1) {
        setResponseFilterFunction(func1);
        setCommunicationConfig(wSCommClassContext, z);
        create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean compareResponseForClass(String str) {
        BaseResponse baseResponse = (BaseResponse) this.protocol.from(str, BaseResponse.class);
        if (this.useToken) {
            return this.commandMatchFunction.apply(baseResponse, getExpectedResponse()).booleanValue() && getRequest().getToken().equals(baseResponse.getToken());
        }
        return this.commandMatchFunction.apply(baseResponse, getExpectedResponse()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RS from(String str) {
        RS rs = (RS) this.protocol.from(str, this.responseClass);
        rs.setOriginalRequest(getRequest());
        return rs;
    }

    private void setCommunicationConfig(WSCommClassContext<RS> wSCommClassContext, boolean z) {
        this.responseClass = wSCommClassContext.getResponseClass();
        this.protocol = wSCommClassContext.getProjectInterfaceType().getProtocol();
        this.commandMatchFunction = wSCommClassContext.getProjectInterfaceType().getMatchFunction();
        boolean zIsUseToken = wSCommClassContext.isUseToken();
        this.useToken = zIsUseToken;
        if (zIsUseToken) {
            wSCommClassContext.getRequest().setToken(new BaseToken(Long.valueOf(sequenceID.getAndIncrement())));
        }
        setTransport(wSCommClassContext.getTransport());
        setRequest(wSCommClassContext.getRequest());
        setEmptyResponse(emptyResponse);
        setExpectedResponse(wSCommClassContext.getExpectedResponse());
        setTransportRequestAdaptor(new Func1<BaseRequest, String>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.OneCoreClassFuture.1
            @Override // rx.functions.Func1
            public String call(BaseRequest baseRequest) {
                return (String) OneCoreClassFuture.this.protocol.to(baseRequest);
            }
        });
        setFilterFunction(new Func1<String, Boolean>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.OneCoreClassFuture.2
            @Override // rx.functions.Func1
            public Boolean call(String str) {
                return Boolean.valueOf(OneCoreClassFuture.this.compareResponseForClass(str));
            }
        });
        setTransportResponseAdaptor(new Func1<String, RS>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.OneCoreClassFuture.3
            @Override // rx.functions.Func1
            public RS call(String str) {
                return (RS) OneCoreClassFuture.this.from(str);
            }
        });
        if (z) {
            setExecutor(wSCommClassContext.getTransportExecutor());
        } else {
            setExecutor(wSCommClassContext.getExecutor());
        }
    }
}
