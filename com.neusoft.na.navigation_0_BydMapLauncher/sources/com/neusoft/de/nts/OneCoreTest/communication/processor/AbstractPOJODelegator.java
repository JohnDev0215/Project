package com.neusoft.de.nts.OneCoreTest.communication.processor;

import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java9.util.function.Consumer;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractPOJODelegator {
    private static final Logger logger = Logger.getLogger((Class<?>) AbstractPOJODelegator.class);
    protected final ITransport<BaseCommand, BaseCommand> client;
    protected final Executor executor;
    protected final Map<Class<? extends BaseRequest>, Consumer<BaseRequest>> mapClassMethods = new HashMap();
    private Subscription processCommand;

    public AbstractPOJODelegator(ITransport<BaseCommand, BaseCommand> iTransport, Executor executor) {
        this.client = iTransport;
        this.executor = executor;
    }

    public synchronized void startProcess() {
        stopProcess();
        this.processCommand = this.client.getObservable().observeOn(Schedulers.from(this.executor)).subscribe(new Action1<BaseCommand>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.processor.AbstractPOJODelegator.1
            @Override // rx.functions.Action1
            public void call(BaseCommand baseCommand) {
                AbstractPOJODelegator.this.processCommand(baseCommand);
            }
        });
    }

    public synchronized void stopProcess() {
        Subscription subscription = this.processCommand;
        if (subscription != null) {
            subscription.unsubscribe();
            this.processCommand = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processCommand(BaseCommand baseCommand) {
        if (baseCommand.getMessageType() == EMessageType.Request) {
            try {
                BaseRequest baseRequest = (BaseRequest) baseCommand;
                Consumer<BaseRequest> consumer = this.mapClassMethods.get(baseRequest.getClass());
                if (consumer != null) {
                    consumer.accept(baseRequest);
                }
            } catch (Exception e) {
                logger.error(e, "Cannot process command " + baseCommand, new Object[0]);
            }
        }
    }

    protected void sendResponse(BaseRequest baseRequest, BaseResponse baseResponse) {
        baseResponse.setToken(baseRequest.getToken());
        this.client.send(baseResponse);
    }
}
