package api.requests.com.neusoft.smart.smartsysapiinverse;

import api.requests.com.neusoft.smart.smartsysapiinverse.ReqOnDayNightChanged;
import api.requests.com.neusoft.smart.smartsysapiinverse.ReqOnDayNightModeChanged;
import api.requests.com.neusoft.smart.smartsysapiinverse.ReqUpdateCurrentWeatherInfo;
import api.requests.com.neusoft.smart.smartsysapiinverse.ReqUpdateFutureWeatherInfo;
import api.responses.com.neusoft.smart.smartsysapiinverse.ResGetAuthStatus;
import api.responses.com.neusoft.smart.smartsysapiinverse.ResGetQueryCurrentWeatherInfo;
import api.responses.com.neusoft.smart.smartsysapiinverse.ResGetQueryFutureWeatherInfo;
import api.responses.com.neusoft.smart.smartsysapiinverse.ResGetTunnelStatus;
import com.neusoft.de.nts.OneCoreTest.communication.Communication;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.utils.CommonUtil;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java9.util.concurrent.CompletableFuture;
import java9.util.concurrent.CompletionStage;
import java9.util.function.Function;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes2.dex */
public class SmartSysApiInverseRequests implements ISmartSysApiInverseRequests {
    private final String component;
    private final ICommunicationContextUtil<?, ?, ?> contextUtil;
    private static final Logger logger = Logger.getLogger((Class<?>) SmartSysApiInverseRequests.class);
    private static final CompletableFuture<Void> completedVoidFuture = CompletableFuture.completedFuture(null);

    public SmartSysApiInverseRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = null;
    }

    public SmartSysApiInverseRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil, String str) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = str;
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged reqOnDayNightChanged) {
        reqOnDayNightChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnDayNightChanged.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnDayNightChanged)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.1
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return SmartSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged reqOnDayNightChanged, final Duration duration) {
        return fireOnDayNightChanged(reqOnDayNightChanged).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.2
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
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

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged.Arguments arguments, final Duration duration) {
        return fireOnDayNightChanged(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.3
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightModeChanged(ReqOnDayNightModeChanged reqOnDayNightModeChanged) {
        reqOnDayNightModeChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnDayNightModeChanged.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnDayNightModeChanged)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.4
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return SmartSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightModeChanged(ReqOnDayNightModeChanged reqOnDayNightModeChanged, final Duration duration) {
        return fireOnDayNightModeChanged(reqOnDayNightModeChanged).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.5
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightModeChanged(ReqOnDayNightModeChanged.Arguments arguments) {
        ReqOnDayNightModeChanged reqOnDayNightModeChanged = new ReqOnDayNightModeChanged();
        reqOnDayNightModeChanged.setArguments(arguments);
        reqOnDayNightModeChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnDayNightModeChanged.setComponent(str);
        }
        return fireOnDayNightModeChanged(reqOnDayNightModeChanged);
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireOnDayNightModeChanged(ReqOnDayNightModeChanged.Arguments arguments, final Duration duration) {
        return fireOnDayNightModeChanged(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.6
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireUpdateCurrentWeatherInfo(ReqUpdateCurrentWeatherInfo reqUpdateCurrentWeatherInfo) {
        reqUpdateCurrentWeatherInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqUpdateCurrentWeatherInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqUpdateCurrentWeatherInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.7
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return SmartSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireUpdateCurrentWeatherInfo(ReqUpdateCurrentWeatherInfo reqUpdateCurrentWeatherInfo, final Duration duration) {
        return fireUpdateCurrentWeatherInfo(reqUpdateCurrentWeatherInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.8
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireUpdateCurrentWeatherInfo(ReqUpdateCurrentWeatherInfo.Arguments arguments) {
        ReqUpdateCurrentWeatherInfo reqUpdateCurrentWeatherInfo = new ReqUpdateCurrentWeatherInfo();
        reqUpdateCurrentWeatherInfo.setArguments(arguments);
        reqUpdateCurrentWeatherInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqUpdateCurrentWeatherInfo.setComponent(str);
        }
        return fireUpdateCurrentWeatherInfo(reqUpdateCurrentWeatherInfo);
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireUpdateCurrentWeatherInfo(ReqUpdateCurrentWeatherInfo.Arguments arguments, final Duration duration) {
        return fireUpdateCurrentWeatherInfo(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.9
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireUpdateFutureWeatherInfo(ReqUpdateFutureWeatherInfo reqUpdateFutureWeatherInfo) {
        reqUpdateFutureWeatherInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqUpdateFutureWeatherInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqUpdateFutureWeatherInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.10
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return SmartSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireUpdateFutureWeatherInfo(ReqUpdateFutureWeatherInfo reqUpdateFutureWeatherInfo, final Duration duration) {
        return fireUpdateFutureWeatherInfo(reqUpdateFutureWeatherInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.11
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireUpdateFutureWeatherInfo(ReqUpdateFutureWeatherInfo.Arguments arguments) {
        ReqUpdateFutureWeatherInfo reqUpdateFutureWeatherInfo = new ReqUpdateFutureWeatherInfo();
        reqUpdateFutureWeatherInfo.setArguments(arguments);
        reqUpdateFutureWeatherInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqUpdateFutureWeatherInfo.setComponent(str);
        }
        return fireUpdateFutureWeatherInfo(reqUpdateFutureWeatherInfo);
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireUpdateFutureWeatherInfo(ReqUpdateFutureWeatherInfo.Arguments arguments, final Duration duration) {
        return fireUpdateFutureWeatherInfo(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.12
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
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

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<ResGetTunnelStatus> requestGetTunnelStatus(ReqGetTunnelStatus reqGetTunnelStatus, final Duration duration) {
        return requestGetTunnelStatus(reqGetTunnelStatus).thenCompose((Function<? super ResGetTunnelStatus, ? extends CompletionStage<U>>) new Function<ResGetTunnelStatus, CompletableFuture<ResGetTunnelStatus>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.13
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetTunnelStatus> apply(ResGetTunnelStatus resGetTunnelStatus) {
                return CommonUtil.delayAsync(resGetTunnelStatus, duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<ResGetTunnelStatus> requestGetTunnelStatus() {
        return requestGetTunnelStatus(new ReqGetTunnelStatus());
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<ResGetTunnelStatus> requestGetTunnelStatus(Duration duration) {
        return requestGetTunnelStatus(new ReqGetTunnelStatus(), duration);
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireGetTunnelStatus(ReqGetTunnelStatus reqGetTunnelStatus) {
        reqGetTunnelStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetTunnelStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetTunnelStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.14
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return SmartSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireGetTunnelStatus(ReqGetTunnelStatus reqGetTunnelStatus, final Duration duration) {
        return fireGetTunnelStatus(reqGetTunnelStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.15
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireGetTunnelStatus() {
        return fireGetTunnelStatus(new ReqGetTunnelStatus());
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireGetTunnelStatus(final Duration duration) {
        return fireGetTunnelStatus(new ReqGetTunnelStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.16
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<ResGetAuthStatus> requestGetAuthStatus(ReqGetAuthStatus reqGetAuthStatus) {
        if (reqGetAuthStatus.getResponse() == null) {
            reqGetAuthStatus.setResponse(new ResGetAuthStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetAuthStatus.setComponent(str);
            reqGetAuthStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetAuthStatus));
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<ResGetAuthStatus> requestGetAuthStatus(ReqGetAuthStatus reqGetAuthStatus, final Duration duration) {
        return requestGetAuthStatus(reqGetAuthStatus).thenCompose((Function<? super ResGetAuthStatus, ? extends CompletionStage<U>>) new Function<ResGetAuthStatus, CompletableFuture<ResGetAuthStatus>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.17
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetAuthStatus> apply(ResGetAuthStatus resGetAuthStatus) {
                return CommonUtil.delayAsync(resGetAuthStatus, duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<ResGetAuthStatus> requestGetAuthStatus() {
        return requestGetAuthStatus(new ReqGetAuthStatus());
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<ResGetAuthStatus> requestGetAuthStatus(Duration duration) {
        return requestGetAuthStatus(new ReqGetAuthStatus(), duration);
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireGetAuthStatus(ReqGetAuthStatus reqGetAuthStatus) {
        reqGetAuthStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetAuthStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetAuthStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.18
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return SmartSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireGetAuthStatus(ReqGetAuthStatus reqGetAuthStatus, final Duration duration) {
        return fireGetAuthStatus(reqGetAuthStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.19
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireGetAuthStatus() {
        return fireGetAuthStatus(new ReqGetAuthStatus());
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireGetAuthStatus(final Duration duration) {
        return fireGetAuthStatus(new ReqGetAuthStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.20
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<ResGetQueryCurrentWeatherInfo> requestGetQueryCurrentWeatherInfo(ReqGetQueryCurrentWeatherInfo reqGetQueryCurrentWeatherInfo) {
        if (reqGetQueryCurrentWeatherInfo.getResponse() == null) {
            reqGetQueryCurrentWeatherInfo.setResponse(new ResGetQueryCurrentWeatherInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetQueryCurrentWeatherInfo.setComponent(str);
            reqGetQueryCurrentWeatherInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetQueryCurrentWeatherInfo));
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<ResGetQueryCurrentWeatherInfo> requestGetQueryCurrentWeatherInfo(ReqGetQueryCurrentWeatherInfo reqGetQueryCurrentWeatherInfo, final Duration duration) {
        return requestGetQueryCurrentWeatherInfo(reqGetQueryCurrentWeatherInfo).thenCompose((Function<? super ResGetQueryCurrentWeatherInfo, ? extends CompletionStage<U>>) new Function<ResGetQueryCurrentWeatherInfo, CompletableFuture<ResGetQueryCurrentWeatherInfo>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.21
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetQueryCurrentWeatherInfo> apply(ResGetQueryCurrentWeatherInfo resGetQueryCurrentWeatherInfo) {
                return CommonUtil.delayAsync(resGetQueryCurrentWeatherInfo, duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<ResGetQueryCurrentWeatherInfo> requestGetQueryCurrentWeatherInfo() {
        return requestGetQueryCurrentWeatherInfo(new ReqGetQueryCurrentWeatherInfo());
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<ResGetQueryCurrentWeatherInfo> requestGetQueryCurrentWeatherInfo(Duration duration) {
        return requestGetQueryCurrentWeatherInfo(new ReqGetQueryCurrentWeatherInfo(), duration);
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireGetQueryCurrentWeatherInfo(ReqGetQueryCurrentWeatherInfo reqGetQueryCurrentWeatherInfo) {
        reqGetQueryCurrentWeatherInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetQueryCurrentWeatherInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetQueryCurrentWeatherInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.22
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return SmartSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireGetQueryCurrentWeatherInfo(ReqGetQueryCurrentWeatherInfo reqGetQueryCurrentWeatherInfo, final Duration duration) {
        return fireGetQueryCurrentWeatherInfo(reqGetQueryCurrentWeatherInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.23
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireGetQueryCurrentWeatherInfo() {
        return fireGetQueryCurrentWeatherInfo(new ReqGetQueryCurrentWeatherInfo());
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireGetQueryCurrentWeatherInfo(final Duration duration) {
        return fireGetQueryCurrentWeatherInfo(new ReqGetQueryCurrentWeatherInfo()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.24
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<ResGetQueryFutureWeatherInfo> requestGetQueryFutureWeatherInfo(ReqGetQueryFutureWeatherInfo reqGetQueryFutureWeatherInfo) {
        if (reqGetQueryFutureWeatherInfo.getResponse() == null) {
            reqGetQueryFutureWeatherInfo.setResponse(new ResGetQueryFutureWeatherInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetQueryFutureWeatherInfo.setComponent(str);
            reqGetQueryFutureWeatherInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetQueryFutureWeatherInfo));
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<ResGetQueryFutureWeatherInfo> requestGetQueryFutureWeatherInfo(ReqGetQueryFutureWeatherInfo reqGetQueryFutureWeatherInfo, final Duration duration) {
        return requestGetQueryFutureWeatherInfo(reqGetQueryFutureWeatherInfo).thenCompose((Function<? super ResGetQueryFutureWeatherInfo, ? extends CompletionStage<U>>) new Function<ResGetQueryFutureWeatherInfo, CompletableFuture<ResGetQueryFutureWeatherInfo>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.25
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetQueryFutureWeatherInfo> apply(ResGetQueryFutureWeatherInfo resGetQueryFutureWeatherInfo) {
                return CommonUtil.delayAsync(resGetQueryFutureWeatherInfo, duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<ResGetQueryFutureWeatherInfo> requestGetQueryFutureWeatherInfo() {
        return requestGetQueryFutureWeatherInfo(new ReqGetQueryFutureWeatherInfo());
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<ResGetQueryFutureWeatherInfo> requestGetQueryFutureWeatherInfo(Duration duration) {
        return requestGetQueryFutureWeatherInfo(new ReqGetQueryFutureWeatherInfo(), duration);
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireGetQueryFutureWeatherInfo(ReqGetQueryFutureWeatherInfo reqGetQueryFutureWeatherInfo) {
        reqGetQueryFutureWeatherInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetQueryFutureWeatherInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetQueryFutureWeatherInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.26
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return SmartSysApiInverseRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireGetQueryFutureWeatherInfo(ReqGetQueryFutureWeatherInfo reqGetQueryFutureWeatherInfo, final Duration duration) {
        return fireGetQueryFutureWeatherInfo(reqGetQueryFutureWeatherInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.27
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireGetQueryFutureWeatherInfo() {
        return fireGetQueryFutureWeatherInfo(new ReqGetQueryFutureWeatherInfo());
    }

    @Override // api.requests.com.neusoft.smart.smartsysapiinverse.ISmartSysApiInverseRequests
    public CompletableFuture<Void> fireGetQueryFutureWeatherInfo(final Duration duration) {
        return fireGetQueryFutureWeatherInfo(new ReqGetQueryFutureWeatherInfo()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.smart.smartsysapiinverse.SmartSysApiInverseRequests.28
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SmartSysApiInverseRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }
}
