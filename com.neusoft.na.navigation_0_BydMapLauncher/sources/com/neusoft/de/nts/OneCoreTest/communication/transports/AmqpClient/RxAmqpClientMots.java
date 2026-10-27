package com.neusoft.de.nts.OneCoreTest.communication.transports.AmqpClient;

import cerence.ark.assistant.core.dcc.DccConstant;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;
import com.neusoft.de.nts.OneCoreTest.communication.protocol.JsonProtocol;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java.util.regex.Pattern;
import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes2.dex */
public class RxAmqpClientMots implements ITransport<BaseCommand, String> {
    private static final String ROUTING_KEY_JSON_NAME = "routingKey";
    private static final Logger logger = Logger.getLogger((Class<?>) RxAmqpClientMots.class);
    private static final PublishSubject<JsonElement> subject = PublishSubject.create();
    private final JsonProtocol protocol;
    private final Pattern routingPattern;
    private final JsonParser parser = new JsonParser();
    private final Observable<JsonElement> observable = subject.filter(new Func1<JsonElement, Boolean>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.transports.AmqpClient.RxAmqpClientMots.1
        @Override // rx.functions.Func1
        public Boolean call(JsonElement jsonElement) {
            return Boolean.valueOf(RxAmqpClientMots.this.compareRoutingKey(jsonElement));
        }
    });

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ITransport
    public String receive() {
        return null;
    }

    public RxAmqpClientMots(JsonProtocol jsonProtocol, String str) {
        this.protocol = jsonProtocol;
        this.routingPattern = makeRoutingPattern(str);
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
    public boolean compareRoutingKey(JsonElement jsonElement) {
        try {
            if (jsonElement.getAsJsonObject().has(ROUTING_KEY_JSON_NAME)) {
                return this.routingPattern.matcher(jsonElement.getAsJsonObject().get(ROUTING_KEY_JSON_NAME).getAsString()).matches();
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ITransport
    public void send(BaseCommand baseCommand) {
        subject.onNext(this.protocol.toTree(baseCommand));
    }

    public void sendJson(JsonElement jsonElement) {
        subject.onNext(jsonElement);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ITransport
    public Observable<String> getObservable() {
        return this.observable.map(new Func1<JsonElement, String>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.transports.AmqpClient.RxAmqpClientMots.2
            @Override // rx.functions.Func1
            public String call(JsonElement jsonElement) {
                return jsonElement.toString();
            }
        });
    }

    public Observable<JsonElement> getJsonElementObservable() {
        return this.observable;
    }
}
