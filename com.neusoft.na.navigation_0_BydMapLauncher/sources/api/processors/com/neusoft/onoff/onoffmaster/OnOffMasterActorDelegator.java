package api.processors.com.neusoft.onoff.onoffmaster;

import api.notifications.com.neusoft.onoff.onoffmaster.NotifyStatusChanged;
import api.requests.com.neusoft.onoff.onoffmaster.ReqGetStatus;
import api.requests.com.neusoft.onoff.onoffmaster.ReqStart;
import api.requests.com.neusoft.onoff.onoffmaster.ReqStop;
import api.responses.com.neusoft.onoff.onoffmaster.ResGetStatus;
import api.responses.com.neusoft.onoff.onoffmaster.ResStart;
import api.responses.com.neusoft.onoff.onoffmaster.ResStop;
import api.structs.com.neusoft.onoff.onoffmaster.ProcessInfo;
import com.neusoft.de.nts.OneCoreTest.communication.attribute.ObservableAttribute;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.processor.AbstractDelegator;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java.util.List;
import java.util.concurrent.Executor;
import java9.util.function.Consumer;
import java9.util.function.Function;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes.dex */
public class OnOffMasterActorDelegator extends AbstractDelegator {
    private IAttribute<NotifyStatusChanged> notifyStatusChanged;
    private final IOnOffMasterProcessor requests;
    private static final Logger logger = Logger.getLogger((Class<?>) OnOffMasterActorDelegator.class);
    private static Func1 IDENTITY = new Func1<BaseCommand, BaseCommand>() { // from class: api.processors.com.neusoft.onoff.onoffmaster.OnOffMasterActorDelegator.1
        @Override // rx.functions.Func1
        public BaseCommand call(BaseCommand baseCommand) {
            return baseCommand;
        }
    };

    static {
        mapCommandClass.put(new ReqStart(), ReqStart.class);
        mapCommandClass.put(new ReqStop(), ReqStop.class);
        mapCommandClass.put(new ReqGetStatus(), ReqGetStatus.class);
    }

    public OnOffMasterActorDelegator(ITransport<BaseCommand, String> iTransport, Executor executor, IOnOffMasterProcessor iOnOffMasterProcessor) {
        super(iTransport, executor);
        this.requests = iOnOffMasterProcessor;
        this.notifyStatusChanged = new ObservableAttribute(iTransport, IDENTITY);
        this.mapClassMethods.put(ReqStart.class, new Consumer<BaseRequest>() { // from class: api.processors.com.neusoft.onoff.onoffmaster.OnOffMasterActorDelegator.2
            @Override // java9.util.function.Consumer
            public void accept(BaseRequest baseRequest) {
                OnOffMasterActorDelegator.this.processReqStart(baseRequest);
            }
        });
        this.mapClassMethods.put(ReqStop.class, new Consumer<BaseRequest>() { // from class: api.processors.com.neusoft.onoff.onoffmaster.OnOffMasterActorDelegator.3
            @Override // java9.util.function.Consumer
            public void accept(BaseRequest baseRequest) {
                OnOffMasterActorDelegator.this.processReqStop(baseRequest);
            }
        });
        this.mapClassMethods.put(ReqGetStatus.class, new Consumer<BaseRequest>() { // from class: api.processors.com.neusoft.onoff.onoffmaster.OnOffMasterActorDelegator.4
            @Override // java9.util.function.Consumer
            public void accept(BaseRequest baseRequest) {
                OnOffMasterActorDelegator.this.processReqGetStatus(baseRequest);
            }
        });
    }

    public void setNotifyStatusChanged(List<ProcessInfo> list) {
        this.notifyStatusChanged.setValue(new NotifyStatusChanged(list));
    }

    public IAttribute<NotifyStatusChanged> getNotifyStatusChanged() {
        return this.notifyStatusChanged;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processReqStart(BaseCommand baseCommand) {
        try {
            final ReqStart reqStart = (ReqStart) baseCommand;
            this.requests.processReqStart(reqStart).thenAccept((Consumer<? super ResStart>) new Consumer<ResStart>() { // from class: api.processors.com.neusoft.onoff.onoffmaster.OnOffMasterActorDelegator.6
                @Override // java9.util.function.Consumer
                public void accept(ResStart resStart) {
                    OnOffMasterActorDelegator.this.sendResponse(reqStart, resStart);
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: api.processors.com.neusoft.onoff.onoffmaster.OnOffMasterActorDelegator.5
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    OnOffMasterActorDelegator.logger.error(th, "In lambda processReqStart", new Object[0]);
                    return null;
                }
            });
        } catch (ClassCastException e) {
            logger.error(e, "Cast failed", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processReqStop(BaseCommand baseCommand) {
        try {
            final ReqStop reqStop = (ReqStop) baseCommand;
            this.requests.processReqStop(reqStop).thenAccept((Consumer<? super ResStop>) new Consumer<ResStop>() { // from class: api.processors.com.neusoft.onoff.onoffmaster.OnOffMasterActorDelegator.8
                @Override // java9.util.function.Consumer
                public void accept(ResStop resStop) {
                    OnOffMasterActorDelegator.this.sendResponse(reqStop, resStop);
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: api.processors.com.neusoft.onoff.onoffmaster.OnOffMasterActorDelegator.7
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    OnOffMasterActorDelegator.logger.error(th, "In lambda processReqStop", new Object[0]);
                    return null;
                }
            });
        } catch (ClassCastException e) {
            logger.error(e, "Cast failed", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processReqGetStatus(BaseCommand baseCommand) {
        try {
            final ReqGetStatus reqGetStatus = (ReqGetStatus) baseCommand;
            this.requests.processReqGetStatus(reqGetStatus).thenAccept((Consumer<? super ResGetStatus>) new Consumer<ResGetStatus>() { // from class: api.processors.com.neusoft.onoff.onoffmaster.OnOffMasterActorDelegator.10
                @Override // java9.util.function.Consumer
                public void accept(ResGetStatus resGetStatus) {
                    OnOffMasterActorDelegator.this.sendResponse(reqGetStatus, resGetStatus);
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: api.processors.com.neusoft.onoff.onoffmaster.OnOffMasterActorDelegator.9
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    OnOffMasterActorDelegator.logger.error(th, "In lambda processReqGetStatus", new Object[0]);
                    return null;
                }
            });
        } catch (ClassCastException e) {
            logger.error(e, "Cast failed", new Object[0]);
        }
    }
}
