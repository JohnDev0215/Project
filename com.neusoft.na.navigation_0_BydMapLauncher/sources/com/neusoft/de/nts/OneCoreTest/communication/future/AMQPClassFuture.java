package com.neusoft.de.nts.OneCoreTest.communication.future;

import com.neusoft.de.nts.OneCoreTest.communication.context.AMQPCommClassContext;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseToken;
import com.neusoft.de.nts.OneCoreTest.interfaces.IProtocol;
import java.util.concurrent.atomic.AtomicLong;
import java9.util.function.BiFunction;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes2.dex */
public class AMQPClassFuture<RS extends BaseResponse> extends GenericOneCoreFuture<BaseRequest, RS, BaseCommand, String> {
    private static final String emptyResponse = "{ \"command\":\"empty\",\"component\":\"nav\" }";
    private static final AtomicLong sequenceID = new AtomicLong(0);
    private final BiFunction<BaseCommand, BaseCommand, Boolean> commandMatchFunction;
    private final IProtocol<BaseCommand, String> protocol;
    private Class<RS> responseClass;

    public AMQPClassFuture(AMQPCommClassContext<RS> aMQPCommClassContext) {
        this.protocol = aMQPCommClassContext.getProjectInterfaceType().getProtocol();
        this.responseClass = aMQPCommClassContext.getResponseClass();
        this.commandMatchFunction = aMQPCommClassContext.getProjectInterfaceType().getMatchFunction();
        setTransport(aMQPCommClassContext.getTransport());
        setRequest(aMQPCommClassContext.getRequest());
        setEmptyResponse(emptyResponse);
        setExpectedResponse(aMQPCommClassContext.getExpectedResponse());
        getRequest().setToken(new BaseToken(Long.valueOf(sequenceID.getAndIncrement())));
        setTransportRequestAdaptor(new Func1<BaseRequest, BaseCommand>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.AMQPClassFuture.1
            @Override // rx.functions.Func1
            public BaseCommand call(BaseRequest baseRequest) {
                return baseRequest;
            }
        });
        setFilterFunction(new Func1<String, Boolean>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.AMQPClassFuture.2
            @Override // rx.functions.Func1
            public Boolean call(String str) {
                return Boolean.valueOf(AMQPClassFuture.this.compareResponseForClass(str));
            }
        });
        setTransportResponseAdaptor(new Func1<String, RS>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.future.AMQPClassFuture.3
            @Override // rx.functions.Func1
            public RS call(String str) {
                return (RS) AMQPClassFuture.this.fromProtocol(str);
            }
        });
        if (aMQPCommClassContext.getExecutor() != null) {
            setExecutor(aMQPCommClassContext.getExecutor());
        }
        create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean compareResponseForClass(String str) {
        BaseResponse baseResponse = (BaseResponse) this.protocol.from(str, BaseResponse.class);
        return baseResponse.getRoutingKey().equals(getRequest().getSenderRoutingKey()) & (this.commandMatchFunction.apply(baseResponse, getExpectedResponse()).booleanValue() && getRequest().getToken().equals(baseResponse.getToken()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RS fromProtocol(String str) {
        RS rs = (RS) this.protocol.from(str, this.responseClass);
        rs.setOriginalRequest(getRequest());
        return rs;
    }
}
