package com.neusoft.de.nts.OneCoreTest.communication.transports.AmqpClient;

import cerence.ark.assistant.core.dcc.DccConstant;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;
import com.neusoft.de.nts.OneCoreTest.interfaces.IProtocol;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java.util.regex.Pattern;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes2.dex */
public class RxAmqpClient implements ITransport<BaseCommand, String> {
    private static final Logger logger = Logger.getLogger((Class<?>) RxAmqpClient.class);
    private static final PublishSubject<String> subject = PublishSubject.create();
    private final Observable<String> observable;
    private final IProtocol<BaseCommand, String> protocol;
    private final Pattern routingPattern;

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ITransport
    public String receive() {
        return null;
    }

    public RxAmqpClient(String str) {
        this(EProjectInterfaceType.OCTA.getProtocol(), str);
    }

    public RxAmqpClient(IProtocol<BaseCommand, String> iProtocol, String str) {
        this.protocol = iProtocol;
        this.routingPattern = makeRoutingPattern(str);
        Observable<String> observableFilter = subject.filter(new Func1<String, Boolean>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.transports.AmqpClient.RxAmqpClient.1
            @Override // rx.functions.Func1
            public Boolean call(String str2) {
                return Boolean.valueOf(RxAmqpClient.this.compareRoutingKey(str2));
            }
        });
        this.observable = observableFilter;
        observableFilter.subscribe(new Action1<String>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.transports.AmqpClient.RxAmqpClient.2
            @Override // rx.functions.Action1
            public void call(String str2) {
                RxAmqpClient.logger.debug("Message: %s", str2);
            }
        });
    }

    private static void appendRegExp(StringBuilder sb, String str) {
        str.hashCode();
        if (str.equals(DccConstant.DCC_SEPARATOR)) {
            sb.append("[a-zA-Z.]*");
        } else if (str.equals("*")) {
            sb.append("[a-zA-Z]*");
        } else {
            sb.append(str);
        }
    }

    private static Pattern makeRoutingPattern(String str) {
        String[] strArrSplit = str.split("\\.");
        StringBuilder sb = new StringBuilder();
        int length = strArrSplit.length - 1;
        for (int i = 0; i < length; i++) {
            appendRegExp(sb, strArrSplit[i]);
            sb.append("\\.");
        }
        appendRegExp(sb, strArrSplit[length]);
        return Pattern.compile(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean compareRoutingKey(String str) {
        return this.routingPattern.matcher(this.protocol.from(str, BaseCommand.class).getRoutingKey()).matches();
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ITransport
    public void send(BaseCommand baseCommand) {
        subject.onNext(this.protocol.to(baseCommand));
    }

    public void sendJson(String str) {
        subject.onNext(str);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ITransport
    public Observable<String> getObservable() {
        return this.observable;
    }
}
