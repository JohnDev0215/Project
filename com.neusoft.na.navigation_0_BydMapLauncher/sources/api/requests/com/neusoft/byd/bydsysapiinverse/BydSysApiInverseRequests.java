package api.requests.com.neusoft.byd.bydsysapiinverse;

import api.requests.com.neusoft.byd.bydsysapiinverse.ReqOnDayNightChanged;
import api.requests.com.neusoft.byd.bydsysapiinverse.ReqSetScreenInfo;
import api.requests.com.neusoft.byd.bydsysapiinverse.ReqSkipFunction;
import api.responses.com.neusoft.byd.bydsysapiinverse.ResGetNaviMainMapDayNightStatusChange;
import com.neusoft.de.nts.OneCoreTest.communication.Communication;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.utils.CommonUtil;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java9.util.concurrent.CompletableFuture;
import java9.util.concurrent.CompletionStage;
import java9.util.function.Function;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes.dex */
public class BydSysApiInverseRequests implements IBydSysApiInverseRequests {
    private final String component;
    private final ICommunicationContextUtil<?, ?, ?> contextUtil;
    private static final Logger logger = Logger.getLogger((Class<?>) BydSysApiInverseRequests.class);
    private static final CompletableFuture<Void> completedVoidFuture = CompletableFuture.completedFuture(null);

    public BydSysApiInverseRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = null;
    }

    public BydSysApiInverseRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil, String str) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = str;
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged reqOnDayNightChanged) {
        reqOnDayNightChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnDayNightChanged.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnDayNightChanged)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydsysapiinverse.BydSysApiInverseRequests.1
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged reqOnDayNightChanged, final Duration duration) {
        return fireOnDayNightChanged(reqOnDayNightChanged).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydsysapiinverse.BydSysApiInverseRequests.2
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged.Arguments arguments) {
        ReqOnDayNightChanged reqOnDayNightChanged = new ReqOnDayNightChanged();
        reqOnDayNightChanged.setArguments(arguments);
        reqOnDayNightChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnDayNightChanged.setComponent(str);
        }
        return fireOnDayNightChanged(reqOnDayNightChanged);
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged.Arguments arguments, final Duration duration) {
        return fireOnDayNightChanged(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydsysapiinverse.BydSysApiInverseRequests.3
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<Void> fireSkipFunction(ReqSkipFunction reqSkipFunction) {
        reqSkipFunction.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSkipFunction.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSkipFunction)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydsysapiinverse.BydSysApiInverseRequests.4
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<Void> fireSkipFunction(ReqSkipFunction reqSkipFunction, final Duration duration) {
        return fireSkipFunction(reqSkipFunction).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydsysapiinverse.BydSysApiInverseRequests.5
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<Void> fireSkipFunction(ReqSkipFunction.Arguments arguments) {
        ReqSkipFunction reqSkipFunction = new ReqSkipFunction();
        reqSkipFunction.setArguments(arguments);
        reqSkipFunction.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSkipFunction.setComponent(str);
        }
        return fireSkipFunction(reqSkipFunction);
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<Void> fireSkipFunction(ReqSkipFunction.Arguments arguments, final Duration duration) {
        return fireSkipFunction(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydsysapiinverse.BydSysApiInverseRequests.6
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<Void> fireSetScreenInfo(ReqSetScreenInfo reqSetScreenInfo) {
        reqSetScreenInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetScreenInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetScreenInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydsysapiinverse.BydSysApiInverseRequests.7
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<Void> fireSetScreenInfo(ReqSetScreenInfo reqSetScreenInfo, final Duration duration) {
        return fireSetScreenInfo(reqSetScreenInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydsysapiinverse.BydSysApiInverseRequests.8
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<Void> fireSetScreenInfo(ReqSetScreenInfo.Arguments arguments) {
        ReqSetScreenInfo reqSetScreenInfo = new ReqSetScreenInfo();
        reqSetScreenInfo.setArguments(arguments);
        reqSetScreenInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetScreenInfo.setComponent(str);
        }
        return fireSetScreenInfo(reqSetScreenInfo);
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<Void> fireSetScreenInfo(ReqSetScreenInfo.Arguments arguments, final Duration duration) {
        return fireSetScreenInfo(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydsysapiinverse.BydSysApiInverseRequests.9
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<ResGetNaviMainMapDayNightStatusChange> requestGetNaviMainMapDayNightStatusChange(ReqGetNaviMainMapDayNightStatusChange reqGetNaviMainMapDayNightStatusChange) {
        if (reqGetNaviMainMapDayNightStatusChange.getResponse() == null) {
            reqGetNaviMainMapDayNightStatusChange.setResponse(new ResGetNaviMainMapDayNightStatusChange());
        }
        String str = this.component;
        if (str != null) {
            reqGetNaviMainMapDayNightStatusChange.setComponent(str);
            reqGetNaviMainMapDayNightStatusChange.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviMainMapDayNightStatusChange));
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<ResGetNaviMainMapDayNightStatusChange> requestGetNaviMainMapDayNightStatusChange(ReqGetNaviMainMapDayNightStatusChange reqGetNaviMainMapDayNightStatusChange, final Duration duration) {
        return requestGetNaviMainMapDayNightStatusChange(reqGetNaviMainMapDayNightStatusChange).thenCompose((Function<? super ResGetNaviMainMapDayNightStatusChange, ? extends CompletionStage<U>>) new Function<ResGetNaviMainMapDayNightStatusChange, CompletableFuture<ResGetNaviMainMapDayNightStatusChange>>() { // from class: api.requests.com.neusoft.byd.bydsysapiinverse.BydSysApiInverseRequests.10
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNaviMainMapDayNightStatusChange> apply(ResGetNaviMainMapDayNightStatusChange resGetNaviMainMapDayNightStatusChange) {
                return CommonUtil.delayAsync(resGetNaviMainMapDayNightStatusChange, duration, BydSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<ResGetNaviMainMapDayNightStatusChange> requestGetNaviMainMapDayNightStatusChange() {
        return requestGetNaviMainMapDayNightStatusChange(new ReqGetNaviMainMapDayNightStatusChange());
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<ResGetNaviMainMapDayNightStatusChange> requestGetNaviMainMapDayNightStatusChange(Duration duration) {
        return requestGetNaviMainMapDayNightStatusChange(new ReqGetNaviMainMapDayNightStatusChange(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviMainMapDayNightStatusChange(ReqGetNaviMainMapDayNightStatusChange reqGetNaviMainMapDayNightStatusChange) {
        reqGetNaviMainMapDayNightStatusChange.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNaviMainMapDayNightStatusChange.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviMainMapDayNightStatusChange)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydsysapiinverse.BydSysApiInverseRequests.11
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviMainMapDayNightStatusChange(ReqGetNaviMainMapDayNightStatusChange reqGetNaviMainMapDayNightStatusChange, final Duration duration) {
        return fireGetNaviMainMapDayNightStatusChange(reqGetNaviMainMapDayNightStatusChange).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydsysapiinverse.BydSysApiInverseRequests.12
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviMainMapDayNightStatusChange() {
        return fireGetNaviMainMapDayNightStatusChange(new ReqGetNaviMainMapDayNightStatusChange());
    }

    @Override // api.requests.com.neusoft.byd.bydsysapiinverse.IBydSysApiInverseRequests
    public CompletableFuture<Void> fireGetNaviMainMapDayNightStatusChange(final Duration duration) {
        return fireGetNaviMainMapDayNightStatusChange(new ReqGetNaviMainMapDayNightStatusChange()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydsysapiinverse.BydSysApiInverseRequests.13
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }
}
