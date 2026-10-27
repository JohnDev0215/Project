package api.requests.com.neusoft.onoff.onoffmaster;

import api.requests.com.neusoft.onoff.onoffmaster.ReqStart;
import api.requests.com.neusoft.onoff.onoffmaster.ReqStop;
import api.responses.com.neusoft.onoff.onoffmaster.ResGetStatus;
import com.neusoft.de.nts.OneCoreTest.communication.Communication;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.utils.CommonUtil;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java9.util.concurrent.CompletableFuture;
import java9.util.concurrent.CompletionStage;
import java9.util.function.Function;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes.dex */
public class OnOffMasterRequests implements IOnOffMasterRequests {
    private final String component;
    private final ICommunicationContextUtil<?, ?, ?> contextUtil;
    private static final Logger logger = Logger.getLogger((Class<?>) OnOffMasterRequests.class);
    private static final CompletableFuture<Void> completedVoidFuture = CompletableFuture.completedFuture(null);

    public OnOffMasterRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = null;
    }

    public OnOffMasterRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil, String str) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = str;
    }

    @Override // api.requests.com.neusoft.onoff.onoffmaster.IOnOffMasterRequests
    public CompletableFuture<Void> fireStart(ReqStart reqStart) {
        reqStart.setResponse(new BaseResponse(EMessageType.Result, reqStart.getComponent(), reqStart.getCommand()));
        String str = this.component;
        if (str != null) {
            reqStart.setComponent(str);
            reqStart.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqStart)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onoff.onoffmaster.OnOffMasterRequests.1
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return OnOffMasterRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.onoff.onoffmaster.IOnOffMasterRequests
    public CompletableFuture<Void> fireStart(ReqStart reqStart, final Duration duration) {
        return fireStart(reqStart).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onoff.onoffmaster.OnOffMasterRequests.2
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, OnOffMasterRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onoff.onoffmaster.IOnOffMasterRequests
    public CompletableFuture<Void> fireStart(ReqStart.Arguments arguments) {
        ReqStart reqStart = new ReqStart();
        reqStart.setArguments(arguments);
        reqStart.setResponse(new BaseResponse(EMessageType.Result, reqStart.getComponent(), reqStart.getCommand()));
        String str = this.component;
        if (str != null) {
            reqStart.setComponent(str);
            reqStart.getResponse().setComponent(this.component);
        }
        return fireStart(reqStart);
    }

    @Override // api.requests.com.neusoft.onoff.onoffmaster.IOnOffMasterRequests
    public CompletableFuture<Void> fireStart(ReqStart.Arguments arguments, final Duration duration) {
        return fireStart(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onoff.onoffmaster.OnOffMasterRequests.3
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, OnOffMasterRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onoff.onoffmaster.IOnOffMasterRequests
    public CompletableFuture<Void> fireStop(ReqStop reqStop) {
        reqStop.setResponse(new BaseResponse(EMessageType.Result, reqStop.getComponent(), reqStop.getCommand()));
        String str = this.component;
        if (str != null) {
            reqStop.setComponent(str);
            reqStop.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqStop)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onoff.onoffmaster.OnOffMasterRequests.4
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return OnOffMasterRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.onoff.onoffmaster.IOnOffMasterRequests
    public CompletableFuture<Void> fireStop(ReqStop reqStop, final Duration duration) {
        return fireStop(reqStop).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onoff.onoffmaster.OnOffMasterRequests.5
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, OnOffMasterRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onoff.onoffmaster.IOnOffMasterRequests
    public CompletableFuture<Void> fireStop(ReqStop.Arguments arguments) {
        ReqStop reqStop = new ReqStop();
        reqStop.setArguments(arguments);
        reqStop.setResponse(new BaseResponse(EMessageType.Result, reqStop.getComponent(), reqStop.getCommand()));
        String str = this.component;
        if (str != null) {
            reqStop.setComponent(str);
            reqStop.getResponse().setComponent(this.component);
        }
        return fireStop(reqStop);
    }

    @Override // api.requests.com.neusoft.onoff.onoffmaster.IOnOffMasterRequests
    public CompletableFuture<Void> fireStop(ReqStop.Arguments arguments, final Duration duration) {
        return fireStop(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onoff.onoffmaster.OnOffMasterRequests.6
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, OnOffMasterRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onoff.onoffmaster.IOnOffMasterRequests
    public CompletableFuture<ResGetStatus> requestGetStatus(ReqGetStatus reqGetStatus) {
        if (reqGetStatus.getResponse() == null) {
            reqGetStatus.setResponse(new ResGetStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetStatus.setComponent(str);
            reqGetStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetStatus));
    }

    @Override // api.requests.com.neusoft.onoff.onoffmaster.IOnOffMasterRequests
    public CompletableFuture<ResGetStatus> requestGetStatus(ReqGetStatus reqGetStatus, final Duration duration) {
        return requestGetStatus(reqGetStatus).thenCompose((Function<? super ResGetStatus, ? extends CompletionStage<U>>) new Function<ResGetStatus, CompletableFuture<ResGetStatus>>() { // from class: api.requests.com.neusoft.onoff.onoffmaster.OnOffMasterRequests.7
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetStatus> apply(ResGetStatus resGetStatus) {
                return CommonUtil.delayAsync(resGetStatus, duration, OnOffMasterRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onoff.onoffmaster.IOnOffMasterRequests
    public CompletableFuture<ResGetStatus> requestGetStatus() {
        return requestGetStatus(new ReqGetStatus());
    }

    @Override // api.requests.com.neusoft.onoff.onoffmaster.IOnOffMasterRequests
    public CompletableFuture<ResGetStatus> requestGetStatus(Duration duration) {
        return requestGetStatus(new ReqGetStatus(), duration);
    }

    @Override // api.requests.com.neusoft.onoff.onoffmaster.IOnOffMasterRequests
    public CompletableFuture<Void> fireGetStatus(ReqGetStatus reqGetStatus) {
        reqGetStatus.setResponse(new BaseResponse(EMessageType.Result, reqGetStatus.getComponent(), reqGetStatus.getCommand()));
        String str = this.component;
        if (str != null) {
            reqGetStatus.setComponent(str);
            reqGetStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onoff.onoffmaster.OnOffMasterRequests.8
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return OnOffMasterRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.onoff.onoffmaster.IOnOffMasterRequests
    public CompletableFuture<Void> fireGetStatus(ReqGetStatus reqGetStatus, final Duration duration) {
        return fireGetStatus(reqGetStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onoff.onoffmaster.OnOffMasterRequests.9
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, OnOffMasterRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onoff.onoffmaster.IOnOffMasterRequests
    public CompletableFuture<Void> fireGetStatus() {
        return fireGetStatus(new ReqGetStatus());
    }

    @Override // api.requests.com.neusoft.onoff.onoffmaster.IOnOffMasterRequests
    public CompletableFuture<Void> fireGetStatus(final Duration duration) {
        return fireGetStatus(new ReqGetStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onoff.onoffmaster.OnOffMasterRequests.10
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, OnOffMasterRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }
}
