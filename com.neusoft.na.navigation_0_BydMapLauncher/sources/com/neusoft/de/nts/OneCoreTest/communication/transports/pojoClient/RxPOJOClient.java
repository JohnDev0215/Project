package com.neusoft.de.nts.OneCoreTest.communication.transports.pojoClient;

import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import rx.Observable;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes2.dex */
public enum RxPOJOClient implements ITransport<BaseCommand, BaseCommand> {
    INSTANCE;

    public static final ExecutorService DEFAULT_EXECUTOR = Executors.newSingleThreadExecutor();
    private static final PublishSubject<BaseCommand> SUBJECT = PublishSubject.create();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ITransport
    public BaseCommand receive() {
        return null;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ITransport
    public void send(BaseCommand baseCommand) {
        SUBJECT.onNext(baseCommand);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.interfaces.ITransport
    public Observable<BaseCommand> getObservable() {
        return SUBJECT;
    }
}
