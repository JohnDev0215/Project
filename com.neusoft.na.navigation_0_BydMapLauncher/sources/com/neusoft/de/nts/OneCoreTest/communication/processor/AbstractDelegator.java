package com.neusoft.de.nts.OneCoreTest.communication.processor;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.interfaces.IProtocol;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java9.util.function.BiFunction;
import java9.util.function.Consumer;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractDelegator {
    private final String className;
    protected final ITransport<BaseCommand, String> client;
    protected final Executor executor;
    protected final Map<Class<? extends BaseRequest>, Consumer<BaseRequest>> mapClassMethods;
    protected final BiFunction<BaseCommand, BaseCommand, Boolean> matchFunction;
    private Subscription processCommand;
    protected final IProtocol<BaseCommand, String> protocol;
    protected static final Map<BaseRequest, Class<? extends BaseRequest>> mapCommandClass = new HashMap();
    private static final Logger logger = Logger.getLogger((Class<?>) AbstractDelegator.class);

    public AbstractDelegator(IProtocol<BaseCommand, String> iProtocol, BiFunction<BaseCommand, BaseCommand, Boolean> biFunction, ITransport<BaseCommand, String> iTransport, Executor executor) {
        this.mapClassMethods = new HashMap();
        this.className = getClass().getSimpleName();
        this.protocol = iProtocol;
        this.matchFunction = biFunction;
        this.client = iTransport;
        this.executor = executor;
    }

    @Deprecated
    public AbstractDelegator(Gson gson, ITransport<BaseCommand, String> iTransport, Executor executor) {
        this(EProjectInterfaceType.OCTA.getProtocol(), EProjectInterfaceType.OCTA.getMatchFunction(), iTransport, executor);
    }

    @Deprecated
    public AbstractDelegator(ITransport<BaseCommand, String> iTransport, Executor executor) {
        this(EProjectInterfaceType.OCTA.getProtocol(), EProjectInterfaceType.OCTA.getMatchFunction(), iTransport, executor);
    }

    public synchronized void startProcess() {
        stopProcess();
        this.processCommand = this.client.getObservable().observeOn(Schedulers.from(this.executor)).subscribe(new Action1<String>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.processor.AbstractDelegator.1
            @Override // rx.functions.Action1
            public void call(String str) {
                AbstractDelegator.this.processCommand(str);
            }
        });
    }

    public synchronized void stopProcess() {
        Subscription subscription = this.processCommand;
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processCommand(String str) {
        try {
            BaseRequest baseRequest = (BaseRequest) this.protocol.from(str, BaseRequest.class);
            if (baseRequest.getMessageType() == EMessageType.Request) {
                Class<? extends BaseRequest> value = null;
                for (Map.Entry<BaseRequest, Class<? extends BaseRequest>> entry : mapCommandClass.entrySet()) {
                    if (this.matchFunction.apply(baseRequest, entry.getKey()).booleanValue()) {
                        value = entry.getValue();
                    }
                }
                if (value != null) {
                    if (baseRequest.getMessageType() == EMessageType.Request) {
                        this.mapClassMethods.get(value).accept((BaseRequest) this.protocol.from(str, value));
                        return;
                    }
                    return;
                }
                logger.warn("%s: Cannot find process method for command: [%s::%s]", this.className, baseRequest.getClass().getName(), baseRequest.getCommand());
            }
        } catch (JsonSyntaxException e) {
            logger.error(e, "%s: Error parsing ", this.className, str);
        } catch (Exception e2) {
            logger.error(e2, "Error during processCommand", new Object[0]);
        }
    }

    protected void sendResponse(BaseRequest baseRequest, BaseResponse baseResponse) {
        baseResponse.setRoutingKey(baseRequest.getSenderRoutingKey());
        baseResponse.setToken(baseRequest.getToken());
        logger.debug("%s.sendResponse: %s", this.className, baseResponse);
        this.client.send(baseResponse);
    }
}
