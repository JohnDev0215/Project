package api.requests.com.neusoft.greatwall.greatwallsysapiinverse;

import api.requests.com.neusoft.greatwall.greatwallsysapiinverse.ReqClearUserProfileData;
import api.requests.com.neusoft.greatwall.greatwallsysapiinverse.ReqGetUserProfileData;
import api.requests.com.neusoft.greatwall.greatwallsysapiinverse.ReqOnDayNightChanged;
import api.requests.com.neusoft.greatwall.greatwallsysapiinverse.ReqResponseGwCurrentWTInfoState;
import api.requests.com.neusoft.greatwall.greatwallsysapiinverse.ReqSetUserProfileData;
import api.responses.com.neusoft.greatwall.greatwallsysapiinverse.ResClearUserProfileData;
import api.responses.com.neusoft.greatwall.greatwallsysapiinverse.ResGetCongestedRoadStatus;
import api.responses.com.neusoft.greatwall.greatwallsysapiinverse.ResGetDayNightStatusUpdate;
import api.responses.com.neusoft.greatwall.greatwallsysapiinverse.ResGetGwCurrentWTInfo;
import api.responses.com.neusoft.greatwall.greatwallsysapiinverse.ResGetTunnelStatus;
import api.responses.com.neusoft.greatwall.greatwallsysapiinverse.ResGetUserProfileData;
import api.responses.com.neusoft.greatwall.greatwallsysapiinverse.ResSetUserProfileData;
import com.neusoft.de.nts.OneCoreTest.communication.Communication;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.utils.CommonUtil;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java9.util.concurrent.CompletableFuture;
import java9.util.concurrent.CompletionStage;
import java9.util.function.Function;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes.dex */
public class GreatWallSysApiInverseRequests implements IGreatWallSysApiInverseRequests {
    private final String component;
    private final ICommunicationContextUtil<?, ?, ?> contextUtil;
    private static final Logger logger = Logger.getLogger((Class<?>) GreatWallSysApiInverseRequests.class);
    private static final CompletableFuture<Void> completedVoidFuture = CompletableFuture.completedFuture(null);

    public GreatWallSysApiInverseRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = null;
    }

    public GreatWallSysApiInverseRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil, String str) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = str;
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged reqOnDayNightChanged) {
        reqOnDayNightChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnDayNightChanged.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnDayNightChanged)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.1
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged reqOnDayNightChanged, final Duration duration) {
        return fireOnDayNightChanged(reqOnDayNightChanged).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.2
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
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

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged.Arguments arguments, final Duration duration) {
        return fireOnDayNightChanged(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.3
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetUserProfileData> requestGetUserProfileData(ReqGetUserProfileData reqGetUserProfileData) {
        if (reqGetUserProfileData.getResponse() == null) {
            reqGetUserProfileData.setResponse(new ResGetUserProfileData());
        }
        String str = this.component;
        if (str != null) {
            reqGetUserProfileData.setComponent(str);
            reqGetUserProfileData.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetUserProfileData));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetUserProfileData> requestGetUserProfileData(ReqGetUserProfileData reqGetUserProfileData, final Duration duration) {
        return requestGetUserProfileData(reqGetUserProfileData).thenCompose((Function<? super ResGetUserProfileData, ? extends CompletionStage<U>>) new Function<ResGetUserProfileData, CompletableFuture<ResGetUserProfileData>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.4
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetUserProfileData> apply(ResGetUserProfileData resGetUserProfileData) {
                return CommonUtil.delayAsync(resGetUserProfileData, duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetUserProfileData.Arguments> requestGetUserProfileData(ReqGetUserProfileData.Arguments arguments) {
        ReqGetUserProfileData reqGetUserProfileData = new ReqGetUserProfileData();
        reqGetUserProfileData.setResponse(new ResGetUserProfileData());
        reqGetUserProfileData.setArguments(arguments);
        return requestGetUserProfileData(reqGetUserProfileData).thenApply((Function<? super ResGetUserProfileData, ? extends U>) new Function<ResGetUserProfileData, ResGetUserProfileData.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.5
            @Override // java9.util.function.Function
            public ResGetUserProfileData.Arguments apply(ResGetUserProfileData resGetUserProfileData) {
                return resGetUserProfileData.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetUserProfileData.Arguments> requestGetUserProfileData(ReqGetUserProfileData.Arguments arguments, final Duration duration) {
        return requestGetUserProfileData(arguments).thenCompose((Function<? super ResGetUserProfileData.Arguments, ? extends CompletionStage<U>>) new Function<ResGetUserProfileData.Arguments, CompletableFuture<ResGetUserProfileData.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.6
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetUserProfileData.Arguments> apply(ResGetUserProfileData.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetUserProfileData(ReqGetUserProfileData reqGetUserProfileData) {
        reqGetUserProfileData.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetUserProfileData.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetUserProfileData)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.7
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetUserProfileData(ReqGetUserProfileData reqGetUserProfileData, final Duration duration) {
        return fireGetUserProfileData(reqGetUserProfileData).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.8
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetUserProfileData(ReqGetUserProfileData.Arguments arguments) {
        ReqGetUserProfileData reqGetUserProfileData = new ReqGetUserProfileData();
        reqGetUserProfileData.setArguments(arguments);
        reqGetUserProfileData.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetUserProfileData.setComponent(str);
        }
        return fireGetUserProfileData(reqGetUserProfileData);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetUserProfileData(ReqGetUserProfileData.Arguments arguments, final Duration duration) {
        return fireGetUserProfileData(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.9
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResSetUserProfileData> requestSetUserProfileData(ReqSetUserProfileData reqSetUserProfileData) {
        if (reqSetUserProfileData.getResponse() == null) {
            reqSetUserProfileData.setResponse(new ResSetUserProfileData());
        }
        String str = this.component;
        if (str != null) {
            reqSetUserProfileData.setComponent(str);
            reqSetUserProfileData.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetUserProfileData));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResSetUserProfileData> requestSetUserProfileData(ReqSetUserProfileData reqSetUserProfileData, final Duration duration) {
        return requestSetUserProfileData(reqSetUserProfileData).thenCompose((Function<? super ResSetUserProfileData, ? extends CompletionStage<U>>) new Function<ResSetUserProfileData, CompletableFuture<ResSetUserProfileData>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.10
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetUserProfileData> apply(ResSetUserProfileData resSetUserProfileData) {
                return CommonUtil.delayAsync(resSetUserProfileData, duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResSetUserProfileData.Arguments> requestSetUserProfileData(ReqSetUserProfileData.Arguments arguments) {
        ReqSetUserProfileData reqSetUserProfileData = new ReqSetUserProfileData();
        reqSetUserProfileData.setResponse(new ResSetUserProfileData());
        reqSetUserProfileData.setArguments(arguments);
        return requestSetUserProfileData(reqSetUserProfileData).thenApply((Function<? super ResSetUserProfileData, ? extends U>) new Function<ResSetUserProfileData, ResSetUserProfileData.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.11
            @Override // java9.util.function.Function
            public ResSetUserProfileData.Arguments apply(ResSetUserProfileData resSetUserProfileData) {
                return resSetUserProfileData.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResSetUserProfileData.Arguments> requestSetUserProfileData(ReqSetUserProfileData.Arguments arguments, final Duration duration) {
        return requestSetUserProfileData(arguments).thenCompose((Function<? super ResSetUserProfileData.Arguments, ? extends CompletionStage<U>>) new Function<ResSetUserProfileData.Arguments, CompletableFuture<ResSetUserProfileData.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.12
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetUserProfileData.Arguments> apply(ResSetUserProfileData.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireSetUserProfileData(ReqSetUserProfileData reqSetUserProfileData) {
        reqSetUserProfileData.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetUserProfileData.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetUserProfileData)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.13
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireSetUserProfileData(ReqSetUserProfileData reqSetUserProfileData, final Duration duration) {
        return fireSetUserProfileData(reqSetUserProfileData).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.14
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireSetUserProfileData(ReqSetUserProfileData.Arguments arguments) {
        ReqSetUserProfileData reqSetUserProfileData = new ReqSetUserProfileData();
        reqSetUserProfileData.setArguments(arguments);
        reqSetUserProfileData.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetUserProfileData.setComponent(str);
        }
        return fireSetUserProfileData(reqSetUserProfileData);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireSetUserProfileData(ReqSetUserProfileData.Arguments arguments, final Duration duration) {
        return fireSetUserProfileData(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.15
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResClearUserProfileData> requestClearUserProfileData(ReqClearUserProfileData reqClearUserProfileData) {
        if (reqClearUserProfileData.getResponse() == null) {
            reqClearUserProfileData.setResponse(new ResClearUserProfileData());
        }
        String str = this.component;
        if (str != null) {
            reqClearUserProfileData.setComponent(str);
            reqClearUserProfileData.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqClearUserProfileData));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResClearUserProfileData> requestClearUserProfileData(ReqClearUserProfileData reqClearUserProfileData, final Duration duration) {
        return requestClearUserProfileData(reqClearUserProfileData).thenCompose((Function<? super ResClearUserProfileData, ? extends CompletionStage<U>>) new Function<ResClearUserProfileData, CompletableFuture<ResClearUserProfileData>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.16
            @Override // java9.util.function.Function
            public CompletableFuture<ResClearUserProfileData> apply(ResClearUserProfileData resClearUserProfileData) {
                return CommonUtil.delayAsync(resClearUserProfileData, duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResClearUserProfileData.Arguments> requestClearUserProfileData(ReqClearUserProfileData.Arguments arguments) {
        ReqClearUserProfileData reqClearUserProfileData = new ReqClearUserProfileData();
        reqClearUserProfileData.setResponse(new ResClearUserProfileData());
        reqClearUserProfileData.setArguments(arguments);
        return requestClearUserProfileData(reqClearUserProfileData).thenApply((Function<? super ResClearUserProfileData, ? extends U>) new Function<ResClearUserProfileData, ResClearUserProfileData.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.17
            @Override // java9.util.function.Function
            public ResClearUserProfileData.Arguments apply(ResClearUserProfileData resClearUserProfileData) {
                return resClearUserProfileData.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResClearUserProfileData.Arguments> requestClearUserProfileData(ReqClearUserProfileData.Arguments arguments, final Duration duration) {
        return requestClearUserProfileData(arguments).thenCompose((Function<? super ResClearUserProfileData.Arguments, ? extends CompletionStage<U>>) new Function<ResClearUserProfileData.Arguments, CompletableFuture<ResClearUserProfileData.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.18
            @Override // java9.util.function.Function
            public CompletableFuture<ResClearUserProfileData.Arguments> apply(ResClearUserProfileData.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireClearUserProfileData(ReqClearUserProfileData reqClearUserProfileData) {
        reqClearUserProfileData.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqClearUserProfileData.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqClearUserProfileData)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.19
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireClearUserProfileData(ReqClearUserProfileData reqClearUserProfileData, final Duration duration) {
        return fireClearUserProfileData(reqClearUserProfileData).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.20
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireClearUserProfileData(ReqClearUserProfileData.Arguments arguments) {
        ReqClearUserProfileData reqClearUserProfileData = new ReqClearUserProfileData();
        reqClearUserProfileData.setArguments(arguments);
        reqClearUserProfileData.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqClearUserProfileData.setComponent(str);
        }
        return fireClearUserProfileData(reqClearUserProfileData);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireClearUserProfileData(ReqClearUserProfileData.Arguments arguments, final Duration duration) {
        return fireClearUserProfileData(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.21
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireResponseGwCurrentWTInfoState(ReqResponseGwCurrentWTInfoState reqResponseGwCurrentWTInfoState) {
        reqResponseGwCurrentWTInfoState.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqResponseGwCurrentWTInfoState.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqResponseGwCurrentWTInfoState)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.22
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireResponseGwCurrentWTInfoState(ReqResponseGwCurrentWTInfoState reqResponseGwCurrentWTInfoState, final Duration duration) {
        return fireResponseGwCurrentWTInfoState(reqResponseGwCurrentWTInfoState).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.23
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireResponseGwCurrentWTInfoState(ReqResponseGwCurrentWTInfoState.Arguments arguments) {
        ReqResponseGwCurrentWTInfoState reqResponseGwCurrentWTInfoState = new ReqResponseGwCurrentWTInfoState();
        reqResponseGwCurrentWTInfoState.setArguments(arguments);
        reqResponseGwCurrentWTInfoState.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqResponseGwCurrentWTInfoState.setComponent(str);
        }
        return fireResponseGwCurrentWTInfoState(reqResponseGwCurrentWTInfoState);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireResponseGwCurrentWTInfoState(ReqResponseGwCurrentWTInfoState.Arguments arguments, final Duration duration) {
        return fireResponseGwCurrentWTInfoState(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.24
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetDayNightStatusUpdate> requestGetDayNightStatusUpdate(ReqGetDayNightStatusUpdate reqGetDayNightStatusUpdate) {
        if (reqGetDayNightStatusUpdate.getResponse() == null) {
            reqGetDayNightStatusUpdate.setResponse(new ResGetDayNightStatusUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetDayNightStatusUpdate.setComponent(str);
            reqGetDayNightStatusUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDayNightStatusUpdate));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetDayNightStatusUpdate> requestGetDayNightStatusUpdate(ReqGetDayNightStatusUpdate reqGetDayNightStatusUpdate, final Duration duration) {
        return requestGetDayNightStatusUpdate(reqGetDayNightStatusUpdate).thenCompose((Function<? super ResGetDayNightStatusUpdate, ? extends CompletionStage<U>>) new Function<ResGetDayNightStatusUpdate, CompletableFuture<ResGetDayNightStatusUpdate>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.25
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetDayNightStatusUpdate> apply(ResGetDayNightStatusUpdate resGetDayNightStatusUpdate) {
                return CommonUtil.delayAsync(resGetDayNightStatusUpdate, duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetDayNightStatusUpdate> requestGetDayNightStatusUpdate() {
        return requestGetDayNightStatusUpdate(new ReqGetDayNightStatusUpdate());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetDayNightStatusUpdate> requestGetDayNightStatusUpdate(Duration duration) {
        return requestGetDayNightStatusUpdate(new ReqGetDayNightStatusUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetDayNightStatusUpdate(ReqGetDayNightStatusUpdate reqGetDayNightStatusUpdate) {
        reqGetDayNightStatusUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetDayNightStatusUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDayNightStatusUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.26
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetDayNightStatusUpdate(ReqGetDayNightStatusUpdate reqGetDayNightStatusUpdate, final Duration duration) {
        return fireGetDayNightStatusUpdate(reqGetDayNightStatusUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.27
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetDayNightStatusUpdate() {
        return fireGetDayNightStatusUpdate(new ReqGetDayNightStatusUpdate());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetDayNightStatusUpdate(final Duration duration) {
        return fireGetDayNightStatusUpdate(new ReqGetDayNightStatusUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.28
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetTunnelStatus> requestGetTunnelStatus(ReqGetTunnelStatus reqGetTunnelStatus) {
        if (reqGetTunnelStatus.getResponse() == null) {
            reqGetTunnelStatus.setResponse(new ResGetTunnelStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetTunnelStatus.setComponent(str);
            reqGetTunnelStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetTunnelStatus));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetTunnelStatus> requestGetTunnelStatus(ReqGetTunnelStatus reqGetTunnelStatus, final Duration duration) {
        return requestGetTunnelStatus(reqGetTunnelStatus).thenCompose((Function<? super ResGetTunnelStatus, ? extends CompletionStage<U>>) new Function<ResGetTunnelStatus, CompletableFuture<ResGetTunnelStatus>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.29
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetTunnelStatus> apply(ResGetTunnelStatus resGetTunnelStatus) {
                return CommonUtil.delayAsync(resGetTunnelStatus, duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetTunnelStatus> requestGetTunnelStatus() {
        return requestGetTunnelStatus(new ReqGetTunnelStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetTunnelStatus> requestGetTunnelStatus(Duration duration) {
        return requestGetTunnelStatus(new ReqGetTunnelStatus(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetTunnelStatus(ReqGetTunnelStatus reqGetTunnelStatus) {
        reqGetTunnelStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetTunnelStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetTunnelStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.30
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetTunnelStatus(ReqGetTunnelStatus reqGetTunnelStatus, final Duration duration) {
        return fireGetTunnelStatus(reqGetTunnelStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.31
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetTunnelStatus() {
        return fireGetTunnelStatus(new ReqGetTunnelStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetTunnelStatus(final Duration duration) {
        return fireGetTunnelStatus(new ReqGetTunnelStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.32
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetCongestedRoadStatus> requestGetCongestedRoadStatus(ReqGetCongestedRoadStatus reqGetCongestedRoadStatus) {
        if (reqGetCongestedRoadStatus.getResponse() == null) {
            reqGetCongestedRoadStatus.setResponse(new ResGetCongestedRoadStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetCongestedRoadStatus.setComponent(str);
            reqGetCongestedRoadStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCongestedRoadStatus));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetCongestedRoadStatus> requestGetCongestedRoadStatus(ReqGetCongestedRoadStatus reqGetCongestedRoadStatus, final Duration duration) {
        return requestGetCongestedRoadStatus(reqGetCongestedRoadStatus).thenCompose((Function<? super ResGetCongestedRoadStatus, ? extends CompletionStage<U>>) new Function<ResGetCongestedRoadStatus, CompletableFuture<ResGetCongestedRoadStatus>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.33
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetCongestedRoadStatus> apply(ResGetCongestedRoadStatus resGetCongestedRoadStatus) {
                return CommonUtil.delayAsync(resGetCongestedRoadStatus, duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetCongestedRoadStatus> requestGetCongestedRoadStatus() {
        return requestGetCongestedRoadStatus(new ReqGetCongestedRoadStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetCongestedRoadStatus> requestGetCongestedRoadStatus(Duration duration) {
        return requestGetCongestedRoadStatus(new ReqGetCongestedRoadStatus(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetCongestedRoadStatus(ReqGetCongestedRoadStatus reqGetCongestedRoadStatus) {
        reqGetCongestedRoadStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetCongestedRoadStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCongestedRoadStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.34
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetCongestedRoadStatus(ReqGetCongestedRoadStatus reqGetCongestedRoadStatus, final Duration duration) {
        return fireGetCongestedRoadStatus(reqGetCongestedRoadStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.35
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetCongestedRoadStatus() {
        return fireGetCongestedRoadStatus(new ReqGetCongestedRoadStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetCongestedRoadStatus(final Duration duration) {
        return fireGetCongestedRoadStatus(new ReqGetCongestedRoadStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.36
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetGwCurrentWTInfo> requestGetGwCurrentWTInfo(ReqGetGwCurrentWTInfo reqGetGwCurrentWTInfo) {
        if (reqGetGwCurrentWTInfo.getResponse() == null) {
            reqGetGwCurrentWTInfo.setResponse(new ResGetGwCurrentWTInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetGwCurrentWTInfo.setComponent(str);
            reqGetGwCurrentWTInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGwCurrentWTInfo));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetGwCurrentWTInfo> requestGetGwCurrentWTInfo(ReqGetGwCurrentWTInfo reqGetGwCurrentWTInfo, final Duration duration) {
        return requestGetGwCurrentWTInfo(reqGetGwCurrentWTInfo).thenCompose((Function<? super ResGetGwCurrentWTInfo, ? extends CompletionStage<U>>) new Function<ResGetGwCurrentWTInfo, CompletableFuture<ResGetGwCurrentWTInfo>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.37
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetGwCurrentWTInfo> apply(ResGetGwCurrentWTInfo resGetGwCurrentWTInfo) {
                return CommonUtil.delayAsync(resGetGwCurrentWTInfo, duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetGwCurrentWTInfo> requestGetGwCurrentWTInfo() {
        return requestGetGwCurrentWTInfo(new ReqGetGwCurrentWTInfo());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<ResGetGwCurrentWTInfo> requestGetGwCurrentWTInfo(Duration duration) {
        return requestGetGwCurrentWTInfo(new ReqGetGwCurrentWTInfo(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetGwCurrentWTInfo(ReqGetGwCurrentWTInfo reqGetGwCurrentWTInfo) {
        reqGetGwCurrentWTInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetGwCurrentWTInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGwCurrentWTInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.38
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetGwCurrentWTInfo(ReqGetGwCurrentWTInfo reqGetGwCurrentWTInfo, final Duration duration) {
        return fireGetGwCurrentWTInfo(reqGetGwCurrentWTInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.39
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetGwCurrentWTInfo() {
        return fireGetGwCurrentWTInfo(new ReqGetGwCurrentWTInfo());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallsysapiinverse.IGreatWallSysApiInverseRequests
    public CompletableFuture<Void> fireGetGwCurrentWTInfo(final Duration duration) {
        return fireGetGwCurrentWTInfo(new ReqGetGwCurrentWTInfo()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests.40
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }
}
