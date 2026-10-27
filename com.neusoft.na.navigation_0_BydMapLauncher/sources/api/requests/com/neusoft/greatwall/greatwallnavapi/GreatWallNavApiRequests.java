package api.requests.com.neusoft.greatwall.greatwallnavapi;

import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqClearFavorite;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqGetFavoriteList;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqGetLocationInfo;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqPowerSavingModeChanged;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRemoveWaypoint;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestAlongRouteSearch;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestCalcPlanningRoute;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestDirectNavigation;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestGoHomeOrCompany;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestHighlightedRoute;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestJumpToSetHomeCompany;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestJumpToViewPage;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestOpenMap;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestSearchAround;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestSearchKeyword;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestSetFavorite;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestSetRoutePreference;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestSetWaypoint;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestSimulation;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqSetDataTrackEnable;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqSetIVIConfiguration;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqSetNaviMuteMode;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqSetPrivacyMode;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqSetShareLocationStatus;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqShowModeChanged;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqSwitchRouteOverviewInfo;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResClearFavorite;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResExitNavigation;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetCalcPlanningRouteStatus;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetCountryChangedStatus;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetCountryInfo;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetCurrentViewInfoStatus;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetDeadReckoningExtendStatus;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetDestinationHistoryListStatus;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetFavoriteList;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetFrontTraffic;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetGuidanceInfoStatus;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetGuidanceInfoStatusForGreatWall;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetGuidanceStatus;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetGuidedStatus;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetHeadingStatus;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetLaneInfoStatus;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetLocationInfo;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetNaviJumpToSystemPage;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetNaviMuteMode;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetNaviMuteStatus;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetNaviSettingStateStatus;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetNotifyHeadingStatus;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetPostCustomEvent;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetPostPageTransitionEvent;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetPostServiceEvent;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetPostUITouchEvent;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetReportLocationInfo;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetRequestPrivacyMode;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetResponseDirectNavigation;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetRouteOverviewState;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetRoutePreference;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetSpeedLimitStatus;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetSwitchNavigationLanguageSetting;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetWaypointList;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestAlongRouteSearch;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestCalcPlanningRoute;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestDTDAndETA;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestDestinationHistoryList;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestGetDestinationHistoryList;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestGoHomeOrCompany;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestHighlightedRoute;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestIntoSearchPage;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestJumpToSetHomeCompany;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestJumpToViewPage;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestOpenFavorite;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestOpenMap;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestSearchAround;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestSearchKeyword;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestSetFavorite;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestSetRoutePreference;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestSetWaypoint;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestSimulation;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestStartGuidance;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestStopGuidance;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResSetIVIConfiguration;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResSetNaviMuteMode;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResSwitchRouteOverviewInfo;
import com.neusoft.de.nts.OneCoreTest.communication.Communication;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.utils.CommonUtil;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java9.util.concurrent.CompletableFuture;
import java9.util.concurrent.CompletionStage;
import java9.util.function.Function;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes.dex */
public class GreatWallNavApiRequests implements IGreatWallNavApiRequests {
    private final String component;
    private final ICommunicationContextUtil<?, ?, ?> contextUtil;
    private static final Logger logger = Logger.getLogger((Class<?>) GreatWallNavApiRequests.class);
    private static final CompletableFuture<Void> completedVoidFuture = CompletableFuture.completedFuture(null);

    public GreatWallNavApiRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = null;
    }

    public GreatWallNavApiRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil, String str) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = str;
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResSetIVIConfiguration> requestSetIVIConfiguration(ReqSetIVIConfiguration reqSetIVIConfiguration) {
        if (reqSetIVIConfiguration.getResponse() == null) {
            reqSetIVIConfiguration.setResponse(new ResSetIVIConfiguration());
        }
        String str = this.component;
        if (str != null) {
            reqSetIVIConfiguration.setComponent(str);
            reqSetIVIConfiguration.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetIVIConfiguration));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResSetIVIConfiguration> requestSetIVIConfiguration(ReqSetIVIConfiguration reqSetIVIConfiguration, final Duration duration) {
        return requestSetIVIConfiguration(reqSetIVIConfiguration).thenCompose((Function<? super ResSetIVIConfiguration, ? extends CompletionStage<U>>) new Function<ResSetIVIConfiguration, CompletableFuture<ResSetIVIConfiguration>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.1
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetIVIConfiguration> apply(ResSetIVIConfiguration resSetIVIConfiguration) {
                return CommonUtil.delayAsync(resSetIVIConfiguration, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResSetIVIConfiguration.Arguments> requestSetIVIConfiguration(ReqSetIVIConfiguration.Arguments arguments) {
        ReqSetIVIConfiguration reqSetIVIConfiguration = new ReqSetIVIConfiguration();
        reqSetIVIConfiguration.setResponse(new ResSetIVIConfiguration());
        reqSetIVIConfiguration.setArguments(arguments);
        return requestSetIVIConfiguration(reqSetIVIConfiguration).thenApply((Function<? super ResSetIVIConfiguration, ? extends U>) new Function<ResSetIVIConfiguration, ResSetIVIConfiguration.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.2
            @Override // java9.util.function.Function
            public ResSetIVIConfiguration.Arguments apply(ResSetIVIConfiguration resSetIVIConfiguration) {
                return resSetIVIConfiguration.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResSetIVIConfiguration.Arguments> requestSetIVIConfiguration(ReqSetIVIConfiguration.Arguments arguments, final Duration duration) {
        return requestSetIVIConfiguration(arguments).thenCompose((Function<? super ResSetIVIConfiguration.Arguments, ? extends CompletionStage<U>>) new Function<ResSetIVIConfiguration.Arguments, CompletableFuture<ResSetIVIConfiguration.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.3
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetIVIConfiguration.Arguments> apply(ResSetIVIConfiguration.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetIVIConfiguration(ReqSetIVIConfiguration reqSetIVIConfiguration) {
        reqSetIVIConfiguration.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetIVIConfiguration.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetIVIConfiguration)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.4
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetIVIConfiguration(ReqSetIVIConfiguration reqSetIVIConfiguration, final Duration duration) {
        return fireSetIVIConfiguration(reqSetIVIConfiguration).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.5
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetIVIConfiguration(ReqSetIVIConfiguration.Arguments arguments) {
        ReqSetIVIConfiguration reqSetIVIConfiguration = new ReqSetIVIConfiguration();
        reqSetIVIConfiguration.setArguments(arguments);
        reqSetIVIConfiguration.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetIVIConfiguration.setComponent(str);
        }
        return fireSetIVIConfiguration(reqSetIVIConfiguration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetIVIConfiguration(ReqSetIVIConfiguration.Arguments arguments, final Duration duration) {
        return fireSetIVIConfiguration(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.6
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNaviMuteMode> requestGetNaviMuteMode(ReqGetNaviMuteMode reqGetNaviMuteMode) {
        if (reqGetNaviMuteMode.getResponse() == null) {
            reqGetNaviMuteMode.setResponse(new ResGetNaviMuteMode());
        }
        String str = this.component;
        if (str != null) {
            reqGetNaviMuteMode.setComponent(str);
            reqGetNaviMuteMode.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviMuteMode));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNaviMuteMode> requestGetNaviMuteMode(ReqGetNaviMuteMode reqGetNaviMuteMode, final Duration duration) {
        return requestGetNaviMuteMode(reqGetNaviMuteMode).thenCompose((Function<? super ResGetNaviMuteMode, ? extends CompletionStage<U>>) new Function<ResGetNaviMuteMode, CompletableFuture<ResGetNaviMuteMode>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.7
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNaviMuteMode> apply(ResGetNaviMuteMode resGetNaviMuteMode) {
                return CommonUtil.delayAsync(resGetNaviMuteMode, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNaviMuteMode> requestGetNaviMuteMode() {
        return requestGetNaviMuteMode(new ReqGetNaviMuteMode());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNaviMuteMode> requestGetNaviMuteMode(Duration duration) {
        return requestGetNaviMuteMode(new ReqGetNaviMuteMode(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNaviMuteMode(ReqGetNaviMuteMode reqGetNaviMuteMode) {
        reqGetNaviMuteMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNaviMuteMode.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviMuteMode)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.8
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNaviMuteMode(ReqGetNaviMuteMode reqGetNaviMuteMode, final Duration duration) {
        return fireGetNaviMuteMode(reqGetNaviMuteMode).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.9
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNaviMuteMode() {
        return fireGetNaviMuteMode(new ReqGetNaviMuteMode());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNaviMuteMode(final Duration duration) {
        return fireGetNaviMuteMode(new ReqGetNaviMuteMode()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.10
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResSetNaviMuteMode> requestSetNaviMuteMode(ReqSetNaviMuteMode reqSetNaviMuteMode) {
        if (reqSetNaviMuteMode.getResponse() == null) {
            reqSetNaviMuteMode.setResponse(new ResSetNaviMuteMode());
        }
        String str = this.component;
        if (str != null) {
            reqSetNaviMuteMode.setComponent(str);
            reqSetNaviMuteMode.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetNaviMuteMode));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResSetNaviMuteMode> requestSetNaviMuteMode(ReqSetNaviMuteMode reqSetNaviMuteMode, final Duration duration) {
        return requestSetNaviMuteMode(reqSetNaviMuteMode).thenCompose((Function<? super ResSetNaviMuteMode, ? extends CompletionStage<U>>) new Function<ResSetNaviMuteMode, CompletableFuture<ResSetNaviMuteMode>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.11
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetNaviMuteMode> apply(ResSetNaviMuteMode resSetNaviMuteMode) {
                return CommonUtil.delayAsync(resSetNaviMuteMode, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResSetNaviMuteMode.Arguments> requestSetNaviMuteMode(ReqSetNaviMuteMode.Arguments arguments) {
        ReqSetNaviMuteMode reqSetNaviMuteMode = new ReqSetNaviMuteMode();
        reqSetNaviMuteMode.setResponse(new ResSetNaviMuteMode());
        reqSetNaviMuteMode.setArguments(arguments);
        return requestSetNaviMuteMode(reqSetNaviMuteMode).thenApply((Function<? super ResSetNaviMuteMode, ? extends U>) new Function<ResSetNaviMuteMode, ResSetNaviMuteMode.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.12
            @Override // java9.util.function.Function
            public ResSetNaviMuteMode.Arguments apply(ResSetNaviMuteMode resSetNaviMuteMode) {
                return resSetNaviMuteMode.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResSetNaviMuteMode.Arguments> requestSetNaviMuteMode(ReqSetNaviMuteMode.Arguments arguments, final Duration duration) {
        return requestSetNaviMuteMode(arguments).thenCompose((Function<? super ResSetNaviMuteMode.Arguments, ? extends CompletionStage<U>>) new Function<ResSetNaviMuteMode.Arguments, CompletableFuture<ResSetNaviMuteMode.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.13
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetNaviMuteMode.Arguments> apply(ResSetNaviMuteMode.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetNaviMuteMode(ReqSetNaviMuteMode reqSetNaviMuteMode) {
        reqSetNaviMuteMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetNaviMuteMode.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetNaviMuteMode)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.14
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetNaviMuteMode(ReqSetNaviMuteMode reqSetNaviMuteMode, final Duration duration) {
        return fireSetNaviMuteMode(reqSetNaviMuteMode).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.15
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetNaviMuteMode(ReqSetNaviMuteMode.Arguments arguments) {
        ReqSetNaviMuteMode reqSetNaviMuteMode = new ReqSetNaviMuteMode();
        reqSetNaviMuteMode.setArguments(arguments);
        reqSetNaviMuteMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetNaviMuteMode.setComponent(str);
        }
        return fireSetNaviMuteMode(reqSetNaviMuteMode);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetNaviMuteMode(ReqSetNaviMuteMode.Arguments arguments, final Duration duration) {
        return fireSetNaviMuteMode(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.16
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestOpenMap> requestRequestOpenMap(ReqRequestOpenMap reqRequestOpenMap) {
        if (reqRequestOpenMap.getResponse() == null) {
            reqRequestOpenMap.setResponse(new ResRequestOpenMap());
        }
        String str = this.component;
        if (str != null) {
            reqRequestOpenMap.setComponent(str);
            reqRequestOpenMap.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestOpenMap));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestOpenMap> requestRequestOpenMap(ReqRequestOpenMap reqRequestOpenMap, final Duration duration) {
        return requestRequestOpenMap(reqRequestOpenMap).thenCompose((Function<? super ResRequestOpenMap, ? extends CompletionStage<U>>) new Function<ResRequestOpenMap, CompletableFuture<ResRequestOpenMap>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.17
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestOpenMap> apply(ResRequestOpenMap resRequestOpenMap) {
                return CommonUtil.delayAsync(resRequestOpenMap, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestOpenMap.Arguments> requestRequestOpenMap(ReqRequestOpenMap.Arguments arguments) {
        ReqRequestOpenMap reqRequestOpenMap = new ReqRequestOpenMap();
        reqRequestOpenMap.setResponse(new ResRequestOpenMap());
        reqRequestOpenMap.setArguments(arguments);
        return requestRequestOpenMap(reqRequestOpenMap).thenApply((Function<? super ResRequestOpenMap, ? extends U>) new Function<ResRequestOpenMap, ResRequestOpenMap.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.18
            @Override // java9.util.function.Function
            public ResRequestOpenMap.Arguments apply(ResRequestOpenMap resRequestOpenMap) {
                return resRequestOpenMap.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestOpenMap.Arguments> requestRequestOpenMap(ReqRequestOpenMap.Arguments arguments, final Duration duration) {
        return requestRequestOpenMap(arguments).thenCompose((Function<? super ResRequestOpenMap.Arguments, ? extends CompletionStage<U>>) new Function<ResRequestOpenMap.Arguments, CompletableFuture<ResRequestOpenMap.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.19
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestOpenMap.Arguments> apply(ResRequestOpenMap.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestOpenMap(ReqRequestOpenMap reqRequestOpenMap) {
        reqRequestOpenMap.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestOpenMap.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestOpenMap)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.20
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestOpenMap(ReqRequestOpenMap reqRequestOpenMap, final Duration duration) {
        return fireRequestOpenMap(reqRequestOpenMap).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.21
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestOpenMap(ReqRequestOpenMap.Arguments arguments) {
        ReqRequestOpenMap reqRequestOpenMap = new ReqRequestOpenMap();
        reqRequestOpenMap.setArguments(arguments);
        reqRequestOpenMap.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestOpenMap.setComponent(str);
        }
        return fireRequestOpenMap(reqRequestOpenMap);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestOpenMap(ReqRequestOpenMap.Arguments arguments, final Duration duration) {
        return fireRequestOpenMap(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.22
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetLocationInfo> requestGetLocationInfo(ReqGetLocationInfo reqGetLocationInfo) {
        if (reqGetLocationInfo.getResponse() == null) {
            reqGetLocationInfo.setResponse(new ResGetLocationInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetLocationInfo.setComponent(str);
            reqGetLocationInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetLocationInfo));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetLocationInfo> requestGetLocationInfo(ReqGetLocationInfo reqGetLocationInfo, final Duration duration) {
        return requestGetLocationInfo(reqGetLocationInfo).thenCompose((Function<? super ResGetLocationInfo, ? extends CompletionStage<U>>) new Function<ResGetLocationInfo, CompletableFuture<ResGetLocationInfo>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.23
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetLocationInfo> apply(ResGetLocationInfo resGetLocationInfo) {
                return CommonUtil.delayAsync(resGetLocationInfo, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetLocationInfo.Arguments> requestGetLocationInfo(ReqGetLocationInfo.Arguments arguments) {
        ReqGetLocationInfo reqGetLocationInfo = new ReqGetLocationInfo();
        reqGetLocationInfo.setResponse(new ResGetLocationInfo());
        reqGetLocationInfo.setArguments(arguments);
        return requestGetLocationInfo(reqGetLocationInfo).thenApply((Function<? super ResGetLocationInfo, ? extends U>) new Function<ResGetLocationInfo, ResGetLocationInfo.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.24
            @Override // java9.util.function.Function
            public ResGetLocationInfo.Arguments apply(ResGetLocationInfo resGetLocationInfo) {
                return resGetLocationInfo.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetLocationInfo.Arguments> requestGetLocationInfo(ReqGetLocationInfo.Arguments arguments, final Duration duration) {
        return requestGetLocationInfo(arguments).thenCompose((Function<? super ResGetLocationInfo.Arguments, ? extends CompletionStage<U>>) new Function<ResGetLocationInfo.Arguments, CompletableFuture<ResGetLocationInfo.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.25
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetLocationInfo.Arguments> apply(ResGetLocationInfo.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetLocationInfo(ReqGetLocationInfo reqGetLocationInfo) {
        reqGetLocationInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetLocationInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetLocationInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.26
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetLocationInfo(ReqGetLocationInfo reqGetLocationInfo, final Duration duration) {
        return fireGetLocationInfo(reqGetLocationInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.27
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetLocationInfo(ReqGetLocationInfo.Arguments arguments) {
        ReqGetLocationInfo reqGetLocationInfo = new ReqGetLocationInfo();
        reqGetLocationInfo.setArguments(arguments);
        reqGetLocationInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetLocationInfo.setComponent(str);
        }
        return fireGetLocationInfo(reqGetLocationInfo);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetLocationInfo(ReqGetLocationInfo.Arguments arguments, final Duration duration) {
        return fireGetLocationInfo(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.28
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResExitNavigation> requestExitNavigation(ReqExitNavigation reqExitNavigation) {
        if (reqExitNavigation.getResponse() == null) {
            reqExitNavigation.setResponse(new ResExitNavigation());
        }
        String str = this.component;
        if (str != null) {
            reqExitNavigation.setComponent(str);
            reqExitNavigation.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqExitNavigation));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResExitNavigation> requestExitNavigation(ReqExitNavigation reqExitNavigation, final Duration duration) {
        return requestExitNavigation(reqExitNavigation).thenCompose((Function<? super ResExitNavigation, ? extends CompletionStage<U>>) new Function<ResExitNavigation, CompletableFuture<ResExitNavigation>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.29
            @Override // java9.util.function.Function
            public CompletableFuture<ResExitNavigation> apply(ResExitNavigation resExitNavigation) {
                return CommonUtil.delayAsync(resExitNavigation, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResExitNavigation> requestExitNavigation() {
        return requestExitNavigation(new ReqExitNavigation());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResExitNavigation> requestExitNavigation(Duration duration) {
        return requestExitNavigation(new ReqExitNavigation(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireExitNavigation(ReqExitNavigation reqExitNavigation) {
        reqExitNavigation.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqExitNavigation.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqExitNavigation)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.30
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireExitNavigation(ReqExitNavigation reqExitNavigation, final Duration duration) {
        return fireExitNavigation(reqExitNavigation).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.31
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireExitNavigation() {
        return fireExitNavigation(new ReqExitNavigation());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireExitNavigation(final Duration duration) {
        return fireExitNavigation(new ReqExitNavigation()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.32
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSimulation> requestRequestSimulation(ReqRequestSimulation reqRequestSimulation) {
        if (reqRequestSimulation.getResponse() == null) {
            reqRequestSimulation.setResponse(new ResRequestSimulation());
        }
        String str = this.component;
        if (str != null) {
            reqRequestSimulation.setComponent(str);
            reqRequestSimulation.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestSimulation));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSimulation> requestRequestSimulation(ReqRequestSimulation reqRequestSimulation, final Duration duration) {
        return requestRequestSimulation(reqRequestSimulation).thenCompose((Function<? super ResRequestSimulation, ? extends CompletionStage<U>>) new Function<ResRequestSimulation, CompletableFuture<ResRequestSimulation>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.33
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestSimulation> apply(ResRequestSimulation resRequestSimulation) {
                return CommonUtil.delayAsync(resRequestSimulation, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSimulation.Arguments> requestRequestSimulation(ReqRequestSimulation.Arguments arguments) {
        ReqRequestSimulation reqRequestSimulation = new ReqRequestSimulation();
        reqRequestSimulation.setResponse(new ResRequestSimulation());
        reqRequestSimulation.setArguments(arguments);
        return requestRequestSimulation(reqRequestSimulation).thenApply((Function<? super ResRequestSimulation, ? extends U>) new Function<ResRequestSimulation, ResRequestSimulation.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.34
            @Override // java9.util.function.Function
            public ResRequestSimulation.Arguments apply(ResRequestSimulation resRequestSimulation) {
                return resRequestSimulation.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSimulation.Arguments> requestRequestSimulation(ReqRequestSimulation.Arguments arguments, final Duration duration) {
        return requestRequestSimulation(arguments).thenCompose((Function<? super ResRequestSimulation.Arguments, ? extends CompletionStage<U>>) new Function<ResRequestSimulation.Arguments, CompletableFuture<ResRequestSimulation.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.35
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestSimulation.Arguments> apply(ResRequestSimulation.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSimulation(ReqRequestSimulation reqRequestSimulation) {
        reqRequestSimulation.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestSimulation.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestSimulation)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.36
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSimulation(ReqRequestSimulation reqRequestSimulation, final Duration duration) {
        return fireRequestSimulation(reqRequestSimulation).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.37
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSimulation(ReqRequestSimulation.Arguments arguments) {
        ReqRequestSimulation reqRequestSimulation = new ReqRequestSimulation();
        reqRequestSimulation.setArguments(arguments);
        reqRequestSimulation.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestSimulation.setComponent(str);
        }
        return fireRequestSimulation(reqRequestSimulation);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSimulation(ReqRequestSimulation.Arguments arguments, final Duration duration) {
        return fireRequestSimulation(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.38
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestDirectNavigation(ReqRequestDirectNavigation reqRequestDirectNavigation) {
        reqRequestDirectNavigation.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestDirectNavigation.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestDirectNavigation)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.39
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestDirectNavigation(ReqRequestDirectNavigation reqRequestDirectNavigation, final Duration duration) {
        return fireRequestDirectNavigation(reqRequestDirectNavigation).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.40
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestDirectNavigation(ReqRequestDirectNavigation.Arguments arguments) {
        ReqRequestDirectNavigation reqRequestDirectNavigation = new ReqRequestDirectNavigation();
        reqRequestDirectNavigation.setArguments(arguments);
        reqRequestDirectNavigation.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestDirectNavigation.setComponent(str);
        }
        return fireRequestDirectNavigation(reqRequestDirectNavigation);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestDirectNavigation(ReqRequestDirectNavigation.Arguments arguments, final Duration duration) {
        return fireRequestDirectNavigation(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.41
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestOpenFavorite> requestRequestOpenFavorite(ReqRequestOpenFavorite reqRequestOpenFavorite) {
        if (reqRequestOpenFavorite.getResponse() == null) {
            reqRequestOpenFavorite.setResponse(new ResRequestOpenFavorite());
        }
        String str = this.component;
        if (str != null) {
            reqRequestOpenFavorite.setComponent(str);
            reqRequestOpenFavorite.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestOpenFavorite));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestOpenFavorite> requestRequestOpenFavorite(ReqRequestOpenFavorite reqRequestOpenFavorite, final Duration duration) {
        return requestRequestOpenFavorite(reqRequestOpenFavorite).thenCompose((Function<? super ResRequestOpenFavorite, ? extends CompletionStage<U>>) new Function<ResRequestOpenFavorite, CompletableFuture<ResRequestOpenFavorite>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.42
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestOpenFavorite> apply(ResRequestOpenFavorite resRequestOpenFavorite) {
                return CommonUtil.delayAsync(resRequestOpenFavorite, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestOpenFavorite> requestRequestOpenFavorite() {
        return requestRequestOpenFavorite(new ReqRequestOpenFavorite());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestOpenFavorite> requestRequestOpenFavorite(Duration duration) {
        return requestRequestOpenFavorite(new ReqRequestOpenFavorite(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestOpenFavorite(ReqRequestOpenFavorite reqRequestOpenFavorite) {
        reqRequestOpenFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestOpenFavorite.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestOpenFavorite)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.43
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestOpenFavorite(ReqRequestOpenFavorite reqRequestOpenFavorite, final Duration duration) {
        return fireRequestOpenFavorite(reqRequestOpenFavorite).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.44
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestOpenFavorite() {
        return fireRequestOpenFavorite(new ReqRequestOpenFavorite());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestOpenFavorite(final Duration duration) {
        return fireRequestOpenFavorite(new ReqRequestOpenFavorite()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.45
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestJumpToViewPage> requestRequestJumpToViewPage(ReqRequestJumpToViewPage reqRequestJumpToViewPage) {
        if (reqRequestJumpToViewPage.getResponse() == null) {
            reqRequestJumpToViewPage.setResponse(new ResRequestJumpToViewPage());
        }
        String str = this.component;
        if (str != null) {
            reqRequestJumpToViewPage.setComponent(str);
            reqRequestJumpToViewPage.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestJumpToViewPage));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestJumpToViewPage> requestRequestJumpToViewPage(ReqRequestJumpToViewPage reqRequestJumpToViewPage, final Duration duration) {
        return requestRequestJumpToViewPage(reqRequestJumpToViewPage).thenCompose((Function<? super ResRequestJumpToViewPage, ? extends CompletionStage<U>>) new Function<ResRequestJumpToViewPage, CompletableFuture<ResRequestJumpToViewPage>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.46
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestJumpToViewPage> apply(ResRequestJumpToViewPage resRequestJumpToViewPage) {
                return CommonUtil.delayAsync(resRequestJumpToViewPage, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestJumpToViewPage.Arguments> requestRequestJumpToViewPage(ReqRequestJumpToViewPage.Arguments arguments) {
        ReqRequestJumpToViewPage reqRequestJumpToViewPage = new ReqRequestJumpToViewPage();
        reqRequestJumpToViewPage.setResponse(new ResRequestJumpToViewPage());
        reqRequestJumpToViewPage.setArguments(arguments);
        return requestRequestJumpToViewPage(reqRequestJumpToViewPage).thenApply((Function<? super ResRequestJumpToViewPage, ? extends U>) new Function<ResRequestJumpToViewPage, ResRequestJumpToViewPage.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.47
            @Override // java9.util.function.Function
            public ResRequestJumpToViewPage.Arguments apply(ResRequestJumpToViewPage resRequestJumpToViewPage) {
                return resRequestJumpToViewPage.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestJumpToViewPage.Arguments> requestRequestJumpToViewPage(ReqRequestJumpToViewPage.Arguments arguments, final Duration duration) {
        return requestRequestJumpToViewPage(arguments).thenCompose((Function<? super ResRequestJumpToViewPage.Arguments, ? extends CompletionStage<U>>) new Function<ResRequestJumpToViewPage.Arguments, CompletableFuture<ResRequestJumpToViewPage.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.48
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestJumpToViewPage.Arguments> apply(ResRequestJumpToViewPage.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestJumpToViewPage(ReqRequestJumpToViewPage reqRequestJumpToViewPage) {
        reqRequestJumpToViewPage.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestJumpToViewPage.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestJumpToViewPage)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.49
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestJumpToViewPage(ReqRequestJumpToViewPage reqRequestJumpToViewPage, final Duration duration) {
        return fireRequestJumpToViewPage(reqRequestJumpToViewPage).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.50
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestJumpToViewPage(ReqRequestJumpToViewPage.Arguments arguments) {
        ReqRequestJumpToViewPage reqRequestJumpToViewPage = new ReqRequestJumpToViewPage();
        reqRequestJumpToViewPage.setArguments(arguments);
        reqRequestJumpToViewPage.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestJumpToViewPage.setComponent(str);
        }
        return fireRequestJumpToViewPage(reqRequestJumpToViewPage);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestJumpToViewPage(ReqRequestJumpToViewPage.Arguments arguments, final Duration duration) {
        return fireRequestJumpToViewPage(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.51
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestNaviSettingState(ReqRequestNaviSettingState reqRequestNaviSettingState) {
        reqRequestNaviSettingState.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestNaviSettingState.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestNaviSettingState)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.52
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestNaviSettingState(ReqRequestNaviSettingState reqRequestNaviSettingState, final Duration duration) {
        return fireRequestNaviSettingState(reqRequestNaviSettingState).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.53
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestNaviSettingState() {
        return fireRequestNaviSettingState(new ReqRequestNaviSettingState());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestNaviSettingState(final Duration duration) {
        return fireRequestNaviSettingState(new ReqRequestNaviSettingState()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.54
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestJumpToSetHomeCompany> requestRequestJumpToSetHomeCompany(ReqRequestJumpToSetHomeCompany reqRequestJumpToSetHomeCompany) {
        if (reqRequestJumpToSetHomeCompany.getResponse() == null) {
            reqRequestJumpToSetHomeCompany.setResponse(new ResRequestJumpToSetHomeCompany());
        }
        String str = this.component;
        if (str != null) {
            reqRequestJumpToSetHomeCompany.setComponent(str);
            reqRequestJumpToSetHomeCompany.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestJumpToSetHomeCompany));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestJumpToSetHomeCompany> requestRequestJumpToSetHomeCompany(ReqRequestJumpToSetHomeCompany reqRequestJumpToSetHomeCompany, final Duration duration) {
        return requestRequestJumpToSetHomeCompany(reqRequestJumpToSetHomeCompany).thenCompose((Function<? super ResRequestJumpToSetHomeCompany, ? extends CompletionStage<U>>) new Function<ResRequestJumpToSetHomeCompany, CompletableFuture<ResRequestJumpToSetHomeCompany>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.55
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestJumpToSetHomeCompany> apply(ResRequestJumpToSetHomeCompany resRequestJumpToSetHomeCompany) {
                return CommonUtil.delayAsync(resRequestJumpToSetHomeCompany, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestJumpToSetHomeCompany.Arguments> requestRequestJumpToSetHomeCompany(ReqRequestJumpToSetHomeCompany.Arguments arguments) {
        ReqRequestJumpToSetHomeCompany reqRequestJumpToSetHomeCompany = new ReqRequestJumpToSetHomeCompany();
        reqRequestJumpToSetHomeCompany.setResponse(new ResRequestJumpToSetHomeCompany());
        reqRequestJumpToSetHomeCompany.setArguments(arguments);
        return requestRequestJumpToSetHomeCompany(reqRequestJumpToSetHomeCompany).thenApply((Function<? super ResRequestJumpToSetHomeCompany, ? extends U>) new Function<ResRequestJumpToSetHomeCompany, ResRequestJumpToSetHomeCompany.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.56
            @Override // java9.util.function.Function
            public ResRequestJumpToSetHomeCompany.Arguments apply(ResRequestJumpToSetHomeCompany resRequestJumpToSetHomeCompany) {
                return resRequestJumpToSetHomeCompany.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestJumpToSetHomeCompany.Arguments> requestRequestJumpToSetHomeCompany(ReqRequestJumpToSetHomeCompany.Arguments arguments, final Duration duration) {
        return requestRequestJumpToSetHomeCompany(arguments).thenCompose((Function<? super ResRequestJumpToSetHomeCompany.Arguments, ? extends CompletionStage<U>>) new Function<ResRequestJumpToSetHomeCompany.Arguments, CompletableFuture<ResRequestJumpToSetHomeCompany.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.57
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestJumpToSetHomeCompany.Arguments> apply(ResRequestJumpToSetHomeCompany.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestJumpToSetHomeCompany(ReqRequestJumpToSetHomeCompany reqRequestJumpToSetHomeCompany) {
        reqRequestJumpToSetHomeCompany.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestJumpToSetHomeCompany.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestJumpToSetHomeCompany)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.58
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestJumpToSetHomeCompany(ReqRequestJumpToSetHomeCompany reqRequestJumpToSetHomeCompany, final Duration duration) {
        return fireRequestJumpToSetHomeCompany(reqRequestJumpToSetHomeCompany).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.59
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestJumpToSetHomeCompany(ReqRequestJumpToSetHomeCompany.Arguments arguments) {
        ReqRequestJumpToSetHomeCompany reqRequestJumpToSetHomeCompany = new ReqRequestJumpToSetHomeCompany();
        reqRequestJumpToSetHomeCompany.setArguments(arguments);
        reqRequestJumpToSetHomeCompany.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestJumpToSetHomeCompany.setComponent(str);
        }
        return fireRequestJumpToSetHomeCompany(reqRequestJumpToSetHomeCompany);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestJumpToSetHomeCompany(ReqRequestJumpToSetHomeCompany.Arguments arguments, final Duration duration) {
        return fireRequestJumpToSetHomeCompany(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.60
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestGoHomeOrCompany> requestRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany reqRequestGoHomeOrCompany) {
        if (reqRequestGoHomeOrCompany.getResponse() == null) {
            reqRequestGoHomeOrCompany.setResponse(new ResRequestGoHomeOrCompany());
        }
        String str = this.component;
        if (str != null) {
            reqRequestGoHomeOrCompany.setComponent(str);
            reqRequestGoHomeOrCompany.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestGoHomeOrCompany));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestGoHomeOrCompany> requestRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany reqRequestGoHomeOrCompany, final Duration duration) {
        return requestRequestGoHomeOrCompany(reqRequestGoHomeOrCompany).thenCompose((Function<? super ResRequestGoHomeOrCompany, ? extends CompletionStage<U>>) new Function<ResRequestGoHomeOrCompany, CompletableFuture<ResRequestGoHomeOrCompany>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.61
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestGoHomeOrCompany> apply(ResRequestGoHomeOrCompany resRequestGoHomeOrCompany) {
                return CommonUtil.delayAsync(resRequestGoHomeOrCompany, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestGoHomeOrCompany.Arguments> requestRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany.Arguments arguments) {
        ReqRequestGoHomeOrCompany reqRequestGoHomeOrCompany = new ReqRequestGoHomeOrCompany();
        reqRequestGoHomeOrCompany.setResponse(new ResRequestGoHomeOrCompany());
        reqRequestGoHomeOrCompany.setArguments(arguments);
        return requestRequestGoHomeOrCompany(reqRequestGoHomeOrCompany).thenApply((Function<? super ResRequestGoHomeOrCompany, ? extends U>) new Function<ResRequestGoHomeOrCompany, ResRequestGoHomeOrCompany.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.62
            @Override // java9.util.function.Function
            public ResRequestGoHomeOrCompany.Arguments apply(ResRequestGoHomeOrCompany resRequestGoHomeOrCompany) {
                return resRequestGoHomeOrCompany.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestGoHomeOrCompany.Arguments> requestRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany.Arguments arguments, final Duration duration) {
        return requestRequestGoHomeOrCompany(arguments).thenCompose((Function<? super ResRequestGoHomeOrCompany.Arguments, ? extends CompletionStage<U>>) new Function<ResRequestGoHomeOrCompany.Arguments, CompletableFuture<ResRequestGoHomeOrCompany.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.63
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestGoHomeOrCompany.Arguments> apply(ResRequestGoHomeOrCompany.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany reqRequestGoHomeOrCompany) {
        reqRequestGoHomeOrCompany.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestGoHomeOrCompany.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestGoHomeOrCompany)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.64
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany reqRequestGoHomeOrCompany, final Duration duration) {
        return fireRequestGoHomeOrCompany(reqRequestGoHomeOrCompany).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.65
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany.Arguments arguments) {
        ReqRequestGoHomeOrCompany reqRequestGoHomeOrCompany = new ReqRequestGoHomeOrCompany();
        reqRequestGoHomeOrCompany.setArguments(arguments);
        reqRequestGoHomeOrCompany.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestGoHomeOrCompany.setComponent(str);
        }
        return fireRequestGoHomeOrCompany(reqRequestGoHomeOrCompany);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany.Arguments arguments, final Duration duration) {
        return fireRequestGoHomeOrCompany(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.66
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSearchKeyword> requestRequestSearchKeyword(ReqRequestSearchKeyword reqRequestSearchKeyword) {
        if (reqRequestSearchKeyword.getResponse() == null) {
            reqRequestSearchKeyword.setResponse(new ResRequestSearchKeyword());
        }
        String str = this.component;
        if (str != null) {
            reqRequestSearchKeyword.setComponent(str);
            reqRequestSearchKeyword.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestSearchKeyword));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSearchKeyword> requestRequestSearchKeyword(ReqRequestSearchKeyword reqRequestSearchKeyword, final Duration duration) {
        return requestRequestSearchKeyword(reqRequestSearchKeyword).thenCompose((Function<? super ResRequestSearchKeyword, ? extends CompletionStage<U>>) new Function<ResRequestSearchKeyword, CompletableFuture<ResRequestSearchKeyword>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.67
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestSearchKeyword> apply(ResRequestSearchKeyword resRequestSearchKeyword) {
                return CommonUtil.delayAsync(resRequestSearchKeyword, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSearchKeyword.Arguments> requestRequestSearchKeyword(ReqRequestSearchKeyword.Arguments arguments) {
        ReqRequestSearchKeyword reqRequestSearchKeyword = new ReqRequestSearchKeyword();
        reqRequestSearchKeyword.setResponse(new ResRequestSearchKeyword());
        reqRequestSearchKeyword.setArguments(arguments);
        return requestRequestSearchKeyword(reqRequestSearchKeyword).thenApply((Function<? super ResRequestSearchKeyword, ? extends U>) new Function<ResRequestSearchKeyword, ResRequestSearchKeyword.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.68
            @Override // java9.util.function.Function
            public ResRequestSearchKeyword.Arguments apply(ResRequestSearchKeyword resRequestSearchKeyword) {
                return resRequestSearchKeyword.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSearchKeyword.Arguments> requestRequestSearchKeyword(ReqRequestSearchKeyword.Arguments arguments, final Duration duration) {
        return requestRequestSearchKeyword(arguments).thenCompose((Function<? super ResRequestSearchKeyword.Arguments, ? extends CompletionStage<U>>) new Function<ResRequestSearchKeyword.Arguments, CompletableFuture<ResRequestSearchKeyword.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.69
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestSearchKeyword.Arguments> apply(ResRequestSearchKeyword.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSearchKeyword(ReqRequestSearchKeyword reqRequestSearchKeyword) {
        reqRequestSearchKeyword.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestSearchKeyword.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestSearchKeyword)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.70
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSearchKeyword(ReqRequestSearchKeyword reqRequestSearchKeyword, final Duration duration) {
        return fireRequestSearchKeyword(reqRequestSearchKeyword).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.71
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSearchKeyword(ReqRequestSearchKeyword.Arguments arguments) {
        ReqRequestSearchKeyword reqRequestSearchKeyword = new ReqRequestSearchKeyword();
        reqRequestSearchKeyword.setArguments(arguments);
        reqRequestSearchKeyword.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestSearchKeyword.setComponent(str);
        }
        return fireRequestSearchKeyword(reqRequestSearchKeyword);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSearchKeyword(ReqRequestSearchKeyword.Arguments arguments, final Duration duration) {
        return fireRequestSearchKeyword(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.72
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSearchAround> requestRequestSearchAround(ReqRequestSearchAround reqRequestSearchAround) {
        if (reqRequestSearchAround.getResponse() == null) {
            reqRequestSearchAround.setResponse(new ResRequestSearchAround());
        }
        String str = this.component;
        if (str != null) {
            reqRequestSearchAround.setComponent(str);
            reqRequestSearchAround.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestSearchAround));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSearchAround> requestRequestSearchAround(ReqRequestSearchAround reqRequestSearchAround, final Duration duration) {
        return requestRequestSearchAround(reqRequestSearchAround).thenCompose((Function<? super ResRequestSearchAround, ? extends CompletionStage<U>>) new Function<ResRequestSearchAround, CompletableFuture<ResRequestSearchAround>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.73
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestSearchAround> apply(ResRequestSearchAround resRequestSearchAround) {
                return CommonUtil.delayAsync(resRequestSearchAround, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSearchAround.Arguments> requestRequestSearchAround(ReqRequestSearchAround.Arguments arguments) {
        ReqRequestSearchAround reqRequestSearchAround = new ReqRequestSearchAround();
        reqRequestSearchAround.setResponse(new ResRequestSearchAround());
        reqRequestSearchAround.setArguments(arguments);
        return requestRequestSearchAround(reqRequestSearchAround).thenApply((Function<? super ResRequestSearchAround, ? extends U>) new Function<ResRequestSearchAround, ResRequestSearchAround.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.74
            @Override // java9.util.function.Function
            public ResRequestSearchAround.Arguments apply(ResRequestSearchAround resRequestSearchAround) {
                return resRequestSearchAround.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSearchAround.Arguments> requestRequestSearchAround(ReqRequestSearchAround.Arguments arguments, final Duration duration) {
        return requestRequestSearchAround(arguments).thenCompose((Function<? super ResRequestSearchAround.Arguments, ? extends CompletionStage<U>>) new Function<ResRequestSearchAround.Arguments, CompletableFuture<ResRequestSearchAround.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.75
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestSearchAround.Arguments> apply(ResRequestSearchAround.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSearchAround(ReqRequestSearchAround reqRequestSearchAround) {
        reqRequestSearchAround.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestSearchAround.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestSearchAround)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.76
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSearchAround(ReqRequestSearchAround reqRequestSearchAround, final Duration duration) {
        return fireRequestSearchAround(reqRequestSearchAround).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.77
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSearchAround(ReqRequestSearchAround.Arguments arguments) {
        ReqRequestSearchAround reqRequestSearchAround = new ReqRequestSearchAround();
        reqRequestSearchAround.setArguments(arguments);
        reqRequestSearchAround.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestSearchAround.setComponent(str);
        }
        return fireRequestSearchAround(reqRequestSearchAround);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSearchAround(ReqRequestSearchAround.Arguments arguments, final Duration duration) {
        return fireRequestSearchAround(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.78
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestAlongRouteSearch> requestRequestAlongRouteSearch(ReqRequestAlongRouteSearch reqRequestAlongRouteSearch) {
        if (reqRequestAlongRouteSearch.getResponse() == null) {
            reqRequestAlongRouteSearch.setResponse(new ResRequestAlongRouteSearch());
        }
        String str = this.component;
        if (str != null) {
            reqRequestAlongRouteSearch.setComponent(str);
            reqRequestAlongRouteSearch.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestAlongRouteSearch));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestAlongRouteSearch> requestRequestAlongRouteSearch(ReqRequestAlongRouteSearch reqRequestAlongRouteSearch, final Duration duration) {
        return requestRequestAlongRouteSearch(reqRequestAlongRouteSearch).thenCompose((Function<? super ResRequestAlongRouteSearch, ? extends CompletionStage<U>>) new Function<ResRequestAlongRouteSearch, CompletableFuture<ResRequestAlongRouteSearch>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.79
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestAlongRouteSearch> apply(ResRequestAlongRouteSearch resRequestAlongRouteSearch) {
                return CommonUtil.delayAsync(resRequestAlongRouteSearch, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestAlongRouteSearch.Arguments> requestRequestAlongRouteSearch(ReqRequestAlongRouteSearch.Arguments arguments) {
        ReqRequestAlongRouteSearch reqRequestAlongRouteSearch = new ReqRequestAlongRouteSearch();
        reqRequestAlongRouteSearch.setResponse(new ResRequestAlongRouteSearch());
        reqRequestAlongRouteSearch.setArguments(arguments);
        return requestRequestAlongRouteSearch(reqRequestAlongRouteSearch).thenApply((Function<? super ResRequestAlongRouteSearch, ? extends U>) new Function<ResRequestAlongRouteSearch, ResRequestAlongRouteSearch.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.80
            @Override // java9.util.function.Function
            public ResRequestAlongRouteSearch.Arguments apply(ResRequestAlongRouteSearch resRequestAlongRouteSearch) {
                return resRequestAlongRouteSearch.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestAlongRouteSearch.Arguments> requestRequestAlongRouteSearch(ReqRequestAlongRouteSearch.Arguments arguments, final Duration duration) {
        return requestRequestAlongRouteSearch(arguments).thenCompose((Function<? super ResRequestAlongRouteSearch.Arguments, ? extends CompletionStage<U>>) new Function<ResRequestAlongRouteSearch.Arguments, CompletableFuture<ResRequestAlongRouteSearch.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.81
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestAlongRouteSearch.Arguments> apply(ResRequestAlongRouteSearch.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestAlongRouteSearch(ReqRequestAlongRouteSearch reqRequestAlongRouteSearch) {
        reqRequestAlongRouteSearch.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestAlongRouteSearch.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestAlongRouteSearch)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.82
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestAlongRouteSearch(ReqRequestAlongRouteSearch reqRequestAlongRouteSearch, final Duration duration) {
        return fireRequestAlongRouteSearch(reqRequestAlongRouteSearch).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.83
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestAlongRouteSearch(ReqRequestAlongRouteSearch.Arguments arguments) {
        ReqRequestAlongRouteSearch reqRequestAlongRouteSearch = new ReqRequestAlongRouteSearch();
        reqRequestAlongRouteSearch.setArguments(arguments);
        reqRequestAlongRouteSearch.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestAlongRouteSearch.setComponent(str);
        }
        return fireRequestAlongRouteSearch(reqRequestAlongRouteSearch);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestAlongRouteSearch(ReqRequestAlongRouteSearch.Arguments arguments, final Duration duration) {
        return fireRequestAlongRouteSearch(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.84
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestStartGuidance> requestRequestStartGuidance(ReqRequestStartGuidance reqRequestStartGuidance) {
        if (reqRequestStartGuidance.getResponse() == null) {
            reqRequestStartGuidance.setResponse(new ResRequestStartGuidance());
        }
        String str = this.component;
        if (str != null) {
            reqRequestStartGuidance.setComponent(str);
            reqRequestStartGuidance.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestStartGuidance));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestStartGuidance> requestRequestStartGuidance(ReqRequestStartGuidance reqRequestStartGuidance, final Duration duration) {
        return requestRequestStartGuidance(reqRequestStartGuidance).thenCompose((Function<? super ResRequestStartGuidance, ? extends CompletionStage<U>>) new Function<ResRequestStartGuidance, CompletableFuture<ResRequestStartGuidance>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.85
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestStartGuidance> apply(ResRequestStartGuidance resRequestStartGuidance) {
                return CommonUtil.delayAsync(resRequestStartGuidance, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestStartGuidance> requestRequestStartGuidance() {
        return requestRequestStartGuidance(new ReqRequestStartGuidance());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestStartGuidance> requestRequestStartGuidance(Duration duration) {
        return requestRequestStartGuidance(new ReqRequestStartGuidance(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestStartGuidance(ReqRequestStartGuidance reqRequestStartGuidance) {
        reqRequestStartGuidance.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestStartGuidance.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestStartGuidance)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.86
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestStartGuidance(ReqRequestStartGuidance reqRequestStartGuidance, final Duration duration) {
        return fireRequestStartGuidance(reqRequestStartGuidance).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.87
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestStartGuidance() {
        return fireRequestStartGuidance(new ReqRequestStartGuidance());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestStartGuidance(final Duration duration) {
        return fireRequestStartGuidance(new ReqRequestStartGuidance()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.88
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestStopGuidance> requestRequestStopGuidance(ReqRequestStopGuidance reqRequestStopGuidance) {
        if (reqRequestStopGuidance.getResponse() == null) {
            reqRequestStopGuidance.setResponse(new ResRequestStopGuidance());
        }
        String str = this.component;
        if (str != null) {
            reqRequestStopGuidance.setComponent(str);
            reqRequestStopGuidance.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestStopGuidance));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestStopGuidance> requestRequestStopGuidance(ReqRequestStopGuidance reqRequestStopGuidance, final Duration duration) {
        return requestRequestStopGuidance(reqRequestStopGuidance).thenCompose((Function<? super ResRequestStopGuidance, ? extends CompletionStage<U>>) new Function<ResRequestStopGuidance, CompletableFuture<ResRequestStopGuidance>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.89
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestStopGuidance> apply(ResRequestStopGuidance resRequestStopGuidance) {
                return CommonUtil.delayAsync(resRequestStopGuidance, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestStopGuidance> requestRequestStopGuidance() {
        return requestRequestStopGuidance(new ReqRequestStopGuidance());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestStopGuidance> requestRequestStopGuidance(Duration duration) {
        return requestRequestStopGuidance(new ReqRequestStopGuidance(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestStopGuidance(ReqRequestStopGuidance reqRequestStopGuidance) {
        reqRequestStopGuidance.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestStopGuidance.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestStopGuidance)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.90
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestStopGuidance(ReqRequestStopGuidance reqRequestStopGuidance, final Duration duration) {
        return fireRequestStopGuidance(reqRequestStopGuidance).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.91
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestStopGuidance() {
        return fireRequestStopGuidance(new ReqRequestStopGuidance());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestStopGuidance(final Duration duration) {
        return fireRequestStopGuidance(new ReqRequestStopGuidance()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.92
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestHighlightedRoute> requestRequestHighlightedRoute(ReqRequestHighlightedRoute reqRequestHighlightedRoute) {
        if (reqRequestHighlightedRoute.getResponse() == null) {
            reqRequestHighlightedRoute.setResponse(new ResRequestHighlightedRoute());
        }
        String str = this.component;
        if (str != null) {
            reqRequestHighlightedRoute.setComponent(str);
            reqRequestHighlightedRoute.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestHighlightedRoute));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestHighlightedRoute> requestRequestHighlightedRoute(ReqRequestHighlightedRoute reqRequestHighlightedRoute, final Duration duration) {
        return requestRequestHighlightedRoute(reqRequestHighlightedRoute).thenCompose((Function<? super ResRequestHighlightedRoute, ? extends CompletionStage<U>>) new Function<ResRequestHighlightedRoute, CompletableFuture<ResRequestHighlightedRoute>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.93
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestHighlightedRoute> apply(ResRequestHighlightedRoute resRequestHighlightedRoute) {
                return CommonUtil.delayAsync(resRequestHighlightedRoute, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestHighlightedRoute.Arguments> requestRequestHighlightedRoute(ReqRequestHighlightedRoute.Arguments arguments) {
        ReqRequestHighlightedRoute reqRequestHighlightedRoute = new ReqRequestHighlightedRoute();
        reqRequestHighlightedRoute.setResponse(new ResRequestHighlightedRoute());
        reqRequestHighlightedRoute.setArguments(arguments);
        return requestRequestHighlightedRoute(reqRequestHighlightedRoute).thenApply((Function<? super ResRequestHighlightedRoute, ? extends U>) new Function<ResRequestHighlightedRoute, ResRequestHighlightedRoute.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.94
            @Override // java9.util.function.Function
            public ResRequestHighlightedRoute.Arguments apply(ResRequestHighlightedRoute resRequestHighlightedRoute) {
                return resRequestHighlightedRoute.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestHighlightedRoute.Arguments> requestRequestHighlightedRoute(ReqRequestHighlightedRoute.Arguments arguments, final Duration duration) {
        return requestRequestHighlightedRoute(arguments).thenCompose((Function<? super ResRequestHighlightedRoute.Arguments, ? extends CompletionStage<U>>) new Function<ResRequestHighlightedRoute.Arguments, CompletableFuture<ResRequestHighlightedRoute.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.95
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestHighlightedRoute.Arguments> apply(ResRequestHighlightedRoute.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestHighlightedRoute(ReqRequestHighlightedRoute reqRequestHighlightedRoute) {
        reqRequestHighlightedRoute.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestHighlightedRoute.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestHighlightedRoute)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.96
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestHighlightedRoute(ReqRequestHighlightedRoute reqRequestHighlightedRoute, final Duration duration) {
        return fireRequestHighlightedRoute(reqRequestHighlightedRoute).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.97
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestHighlightedRoute(ReqRequestHighlightedRoute.Arguments arguments) {
        ReqRequestHighlightedRoute reqRequestHighlightedRoute = new ReqRequestHighlightedRoute();
        reqRequestHighlightedRoute.setArguments(arguments);
        reqRequestHighlightedRoute.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestHighlightedRoute.setComponent(str);
        }
        return fireRequestHighlightedRoute(reqRequestHighlightedRoute);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestHighlightedRoute(ReqRequestHighlightedRoute.Arguments arguments, final Duration duration) {
        return fireRequestHighlightedRoute(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.98
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResSwitchRouteOverviewInfo> requestSwitchRouteOverviewInfo(ReqSwitchRouteOverviewInfo reqSwitchRouteOverviewInfo) {
        if (reqSwitchRouteOverviewInfo.getResponse() == null) {
            reqSwitchRouteOverviewInfo.setResponse(new ResSwitchRouteOverviewInfo());
        }
        String str = this.component;
        if (str != null) {
            reqSwitchRouteOverviewInfo.setComponent(str);
            reqSwitchRouteOverviewInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSwitchRouteOverviewInfo));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResSwitchRouteOverviewInfo> requestSwitchRouteOverviewInfo(ReqSwitchRouteOverviewInfo reqSwitchRouteOverviewInfo, final Duration duration) {
        return requestSwitchRouteOverviewInfo(reqSwitchRouteOverviewInfo).thenCompose((Function<? super ResSwitchRouteOverviewInfo, ? extends CompletionStage<U>>) new Function<ResSwitchRouteOverviewInfo, CompletableFuture<ResSwitchRouteOverviewInfo>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.99
            @Override // java9.util.function.Function
            public CompletableFuture<ResSwitchRouteOverviewInfo> apply(ResSwitchRouteOverviewInfo resSwitchRouteOverviewInfo) {
                return CommonUtil.delayAsync(resSwitchRouteOverviewInfo, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResSwitchRouteOverviewInfo.Arguments> requestSwitchRouteOverviewInfo(ReqSwitchRouteOverviewInfo.Arguments arguments) {
        ReqSwitchRouteOverviewInfo reqSwitchRouteOverviewInfo = new ReqSwitchRouteOverviewInfo();
        reqSwitchRouteOverviewInfo.setResponse(new ResSwitchRouteOverviewInfo());
        reqSwitchRouteOverviewInfo.setArguments(arguments);
        return requestSwitchRouteOverviewInfo(reqSwitchRouteOverviewInfo).thenApply((Function<? super ResSwitchRouteOverviewInfo, ? extends U>) new Function<ResSwitchRouteOverviewInfo, ResSwitchRouteOverviewInfo.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.100
            @Override // java9.util.function.Function
            public ResSwitchRouteOverviewInfo.Arguments apply(ResSwitchRouteOverviewInfo resSwitchRouteOverviewInfo) {
                return resSwitchRouteOverviewInfo.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResSwitchRouteOverviewInfo.Arguments> requestSwitchRouteOverviewInfo(ReqSwitchRouteOverviewInfo.Arguments arguments, final Duration duration) {
        return requestSwitchRouteOverviewInfo(arguments).thenCompose((Function<? super ResSwitchRouteOverviewInfo.Arguments, ? extends CompletionStage<U>>) new Function<ResSwitchRouteOverviewInfo.Arguments, CompletableFuture<ResSwitchRouteOverviewInfo.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.101
            @Override // java9.util.function.Function
            public CompletableFuture<ResSwitchRouteOverviewInfo.Arguments> apply(ResSwitchRouteOverviewInfo.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSwitchRouteOverviewInfo(ReqSwitchRouteOverviewInfo reqSwitchRouteOverviewInfo) {
        reqSwitchRouteOverviewInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSwitchRouteOverviewInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSwitchRouteOverviewInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.102
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSwitchRouteOverviewInfo(ReqSwitchRouteOverviewInfo reqSwitchRouteOverviewInfo, final Duration duration) {
        return fireSwitchRouteOverviewInfo(reqSwitchRouteOverviewInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.103
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSwitchRouteOverviewInfo(ReqSwitchRouteOverviewInfo.Arguments arguments) {
        ReqSwitchRouteOverviewInfo reqSwitchRouteOverviewInfo = new ReqSwitchRouteOverviewInfo();
        reqSwitchRouteOverviewInfo.setArguments(arguments);
        reqSwitchRouteOverviewInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSwitchRouteOverviewInfo.setComponent(str);
        }
        return fireSwitchRouteOverviewInfo(reqSwitchRouteOverviewInfo);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSwitchRouteOverviewInfo(ReqSwitchRouteOverviewInfo.Arguments arguments, final Duration duration) {
        return fireSwitchRouteOverviewInfo(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.104
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetRouteOverviewState> requestGetRouteOverviewState(ReqGetRouteOverviewState reqGetRouteOverviewState) {
        if (reqGetRouteOverviewState.getResponse() == null) {
            reqGetRouteOverviewState.setResponse(new ResGetRouteOverviewState());
        }
        String str = this.component;
        if (str != null) {
            reqGetRouteOverviewState.setComponent(str);
            reqGetRouteOverviewState.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRouteOverviewState));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetRouteOverviewState> requestGetRouteOverviewState(ReqGetRouteOverviewState reqGetRouteOverviewState, final Duration duration) {
        return requestGetRouteOverviewState(reqGetRouteOverviewState).thenCompose((Function<? super ResGetRouteOverviewState, ? extends CompletionStage<U>>) new Function<ResGetRouteOverviewState, CompletableFuture<ResGetRouteOverviewState>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.105
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRouteOverviewState> apply(ResGetRouteOverviewState resGetRouteOverviewState) {
                return CommonUtil.delayAsync(resGetRouteOverviewState, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetRouteOverviewState> requestGetRouteOverviewState() {
        return requestGetRouteOverviewState(new ReqGetRouteOverviewState());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetRouteOverviewState> requestGetRouteOverviewState(Duration duration) {
        return requestGetRouteOverviewState(new ReqGetRouteOverviewState(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetRouteOverviewState(ReqGetRouteOverviewState reqGetRouteOverviewState) {
        reqGetRouteOverviewState.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRouteOverviewState.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRouteOverviewState)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.106
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetRouteOverviewState(ReqGetRouteOverviewState reqGetRouteOverviewState, final Duration duration) {
        return fireGetRouteOverviewState(reqGetRouteOverviewState).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.107
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetRouteOverviewState() {
        return fireGetRouteOverviewState(new ReqGetRouteOverviewState());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetRouteOverviewState(final Duration duration) {
        return fireGetRouteOverviewState(new ReqGetRouteOverviewState()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.108
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSetFavorite> requestRequestSetFavorite(ReqRequestSetFavorite reqRequestSetFavorite) {
        if (reqRequestSetFavorite.getResponse() == null) {
            reqRequestSetFavorite.setResponse(new ResRequestSetFavorite());
        }
        String str = this.component;
        if (str != null) {
            reqRequestSetFavorite.setComponent(str);
            reqRequestSetFavorite.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestSetFavorite));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSetFavorite> requestRequestSetFavorite(ReqRequestSetFavorite reqRequestSetFavorite, final Duration duration) {
        return requestRequestSetFavorite(reqRequestSetFavorite).thenCompose((Function<? super ResRequestSetFavorite, ? extends CompletionStage<U>>) new Function<ResRequestSetFavorite, CompletableFuture<ResRequestSetFavorite>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.109
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestSetFavorite> apply(ResRequestSetFavorite resRequestSetFavorite) {
                return CommonUtil.delayAsync(resRequestSetFavorite, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSetFavorite.Arguments> requestRequestSetFavorite(ReqRequestSetFavorite.Arguments arguments) {
        ReqRequestSetFavorite reqRequestSetFavorite = new ReqRequestSetFavorite();
        reqRequestSetFavorite.setResponse(new ResRequestSetFavorite());
        reqRequestSetFavorite.setArguments(arguments);
        return requestRequestSetFavorite(reqRequestSetFavorite).thenApply((Function<? super ResRequestSetFavorite, ? extends U>) new Function<ResRequestSetFavorite, ResRequestSetFavorite.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.110
            @Override // java9.util.function.Function
            public ResRequestSetFavorite.Arguments apply(ResRequestSetFavorite resRequestSetFavorite) {
                return resRequestSetFavorite.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSetFavorite.Arguments> requestRequestSetFavorite(ReqRequestSetFavorite.Arguments arguments, final Duration duration) {
        return requestRequestSetFavorite(arguments).thenCompose((Function<? super ResRequestSetFavorite.Arguments, ? extends CompletionStage<U>>) new Function<ResRequestSetFavorite.Arguments, CompletableFuture<ResRequestSetFavorite.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.111
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestSetFavorite.Arguments> apply(ResRequestSetFavorite.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSetFavorite(ReqRequestSetFavorite reqRequestSetFavorite) {
        reqRequestSetFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestSetFavorite.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestSetFavorite)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.112
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSetFavorite(ReqRequestSetFavorite reqRequestSetFavorite, final Duration duration) {
        return fireRequestSetFavorite(reqRequestSetFavorite).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.113
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSetFavorite(ReqRequestSetFavorite.Arguments arguments) {
        ReqRequestSetFavorite reqRequestSetFavorite = new ReqRequestSetFavorite();
        reqRequestSetFavorite.setArguments(arguments);
        reqRequestSetFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestSetFavorite.setComponent(str);
        }
        return fireRequestSetFavorite(reqRequestSetFavorite);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSetFavorite(ReqRequestSetFavorite.Arguments arguments, final Duration duration) {
        return fireRequestSetFavorite(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.114
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetFavoriteList> requestGetFavoriteList(ReqGetFavoriteList reqGetFavoriteList) {
        if (reqGetFavoriteList.getResponse() == null) {
            reqGetFavoriteList.setResponse(new ResGetFavoriteList());
        }
        String str = this.component;
        if (str != null) {
            reqGetFavoriteList.setComponent(str);
            reqGetFavoriteList.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetFavoriteList));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetFavoriteList> requestGetFavoriteList(ReqGetFavoriteList reqGetFavoriteList, final Duration duration) {
        return requestGetFavoriteList(reqGetFavoriteList).thenCompose((Function<? super ResGetFavoriteList, ? extends CompletionStage<U>>) new Function<ResGetFavoriteList, CompletableFuture<ResGetFavoriteList>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.115
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetFavoriteList> apply(ResGetFavoriteList resGetFavoriteList) {
                return CommonUtil.delayAsync(resGetFavoriteList, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetFavoriteList.Arguments> requestGetFavoriteList(ReqGetFavoriteList.Arguments arguments) {
        ReqGetFavoriteList reqGetFavoriteList = new ReqGetFavoriteList();
        reqGetFavoriteList.setResponse(new ResGetFavoriteList());
        reqGetFavoriteList.setArguments(arguments);
        return requestGetFavoriteList(reqGetFavoriteList).thenApply((Function<? super ResGetFavoriteList, ? extends U>) new Function<ResGetFavoriteList, ResGetFavoriteList.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.116
            @Override // java9.util.function.Function
            public ResGetFavoriteList.Arguments apply(ResGetFavoriteList resGetFavoriteList) {
                return resGetFavoriteList.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetFavoriteList.Arguments> requestGetFavoriteList(ReqGetFavoriteList.Arguments arguments, final Duration duration) {
        return requestGetFavoriteList(arguments).thenCompose((Function<? super ResGetFavoriteList.Arguments, ? extends CompletionStage<U>>) new Function<ResGetFavoriteList.Arguments, CompletableFuture<ResGetFavoriteList.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.117
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetFavoriteList.Arguments> apply(ResGetFavoriteList.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetFavoriteList(ReqGetFavoriteList reqGetFavoriteList) {
        reqGetFavoriteList.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetFavoriteList.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetFavoriteList)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.118
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetFavoriteList(ReqGetFavoriteList reqGetFavoriteList, final Duration duration) {
        return fireGetFavoriteList(reqGetFavoriteList).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.119
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetFavoriteList(ReqGetFavoriteList.Arguments arguments) {
        ReqGetFavoriteList reqGetFavoriteList = new ReqGetFavoriteList();
        reqGetFavoriteList.setArguments(arguments);
        reqGetFavoriteList.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetFavoriteList.setComponent(str);
        }
        return fireGetFavoriteList(reqGetFavoriteList);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetFavoriteList(ReqGetFavoriteList.Arguments arguments, final Duration duration) {
        return fireGetFavoriteList(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.120
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResClearFavorite> requestClearFavorite(ReqClearFavorite reqClearFavorite) {
        if (reqClearFavorite.getResponse() == null) {
            reqClearFavorite.setResponse(new ResClearFavorite());
        }
        String str = this.component;
        if (str != null) {
            reqClearFavorite.setComponent(str);
            reqClearFavorite.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqClearFavorite));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResClearFavorite> requestClearFavorite(ReqClearFavorite reqClearFavorite, final Duration duration) {
        return requestClearFavorite(reqClearFavorite).thenCompose((Function<? super ResClearFavorite, ? extends CompletionStage<U>>) new Function<ResClearFavorite, CompletableFuture<ResClearFavorite>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.121
            @Override // java9.util.function.Function
            public CompletableFuture<ResClearFavorite> apply(ResClearFavorite resClearFavorite) {
                return CommonUtil.delayAsync(resClearFavorite, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResClearFavorite.Arguments> requestClearFavorite(ReqClearFavorite.Arguments arguments) {
        ReqClearFavorite reqClearFavorite = new ReqClearFavorite();
        reqClearFavorite.setResponse(new ResClearFavorite());
        reqClearFavorite.setArguments(arguments);
        return requestClearFavorite(reqClearFavorite).thenApply((Function<? super ResClearFavorite, ? extends U>) new Function<ResClearFavorite, ResClearFavorite.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.122
            @Override // java9.util.function.Function
            public ResClearFavorite.Arguments apply(ResClearFavorite resClearFavorite) {
                return resClearFavorite.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResClearFavorite.Arguments> requestClearFavorite(ReqClearFavorite.Arguments arguments, final Duration duration) {
        return requestClearFavorite(arguments).thenCompose((Function<? super ResClearFavorite.Arguments, ? extends CompletionStage<U>>) new Function<ResClearFavorite.Arguments, CompletableFuture<ResClearFavorite.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.123
            @Override // java9.util.function.Function
            public CompletableFuture<ResClearFavorite.Arguments> apply(ResClearFavorite.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireClearFavorite(ReqClearFavorite reqClearFavorite) {
        reqClearFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqClearFavorite.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqClearFavorite)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.124
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireClearFavorite(ReqClearFavorite reqClearFavorite, final Duration duration) {
        return fireClearFavorite(reqClearFavorite).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.125
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireClearFavorite(ReqClearFavorite.Arguments arguments) {
        ReqClearFavorite reqClearFavorite = new ReqClearFavorite();
        reqClearFavorite.setArguments(arguments);
        reqClearFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqClearFavorite.setComponent(str);
        }
        return fireClearFavorite(reqClearFavorite);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireClearFavorite(ReqClearFavorite.Arguments arguments, final Duration duration) {
        return fireClearFavorite(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.126
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestIntoSearchPage> requestRequestIntoSearchPage(ReqRequestIntoSearchPage reqRequestIntoSearchPage) {
        if (reqRequestIntoSearchPage.getResponse() == null) {
            reqRequestIntoSearchPage.setResponse(new ResRequestIntoSearchPage());
        }
        String str = this.component;
        if (str != null) {
            reqRequestIntoSearchPage.setComponent(str);
            reqRequestIntoSearchPage.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestIntoSearchPage));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestIntoSearchPage> requestRequestIntoSearchPage(ReqRequestIntoSearchPage reqRequestIntoSearchPage, final Duration duration) {
        return requestRequestIntoSearchPage(reqRequestIntoSearchPage).thenCompose((Function<? super ResRequestIntoSearchPage, ? extends CompletionStage<U>>) new Function<ResRequestIntoSearchPage, CompletableFuture<ResRequestIntoSearchPage>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.127
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestIntoSearchPage> apply(ResRequestIntoSearchPage resRequestIntoSearchPage) {
                return CommonUtil.delayAsync(resRequestIntoSearchPage, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestIntoSearchPage> requestRequestIntoSearchPage() {
        return requestRequestIntoSearchPage(new ReqRequestIntoSearchPage());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestIntoSearchPage> requestRequestIntoSearchPage(Duration duration) {
        return requestRequestIntoSearchPage(new ReqRequestIntoSearchPage(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestIntoSearchPage(ReqRequestIntoSearchPage reqRequestIntoSearchPage) {
        reqRequestIntoSearchPage.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestIntoSearchPage.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestIntoSearchPage)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.128
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestIntoSearchPage(ReqRequestIntoSearchPage reqRequestIntoSearchPage, final Duration duration) {
        return fireRequestIntoSearchPage(reqRequestIntoSearchPage).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.129
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestIntoSearchPage() {
        return fireRequestIntoSearchPage(new ReqRequestIntoSearchPage());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestIntoSearchPage(final Duration duration) {
        return fireRequestIntoSearchPage(new ReqRequestIntoSearchPage()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.130
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSetWaypoint> requestRequestSetWaypoint(ReqRequestSetWaypoint reqRequestSetWaypoint) {
        if (reqRequestSetWaypoint.getResponse() == null) {
            reqRequestSetWaypoint.setResponse(new ResRequestSetWaypoint());
        }
        String str = this.component;
        if (str != null) {
            reqRequestSetWaypoint.setComponent(str);
            reqRequestSetWaypoint.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestSetWaypoint));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSetWaypoint> requestRequestSetWaypoint(ReqRequestSetWaypoint reqRequestSetWaypoint, final Duration duration) {
        return requestRequestSetWaypoint(reqRequestSetWaypoint).thenCompose((Function<? super ResRequestSetWaypoint, ? extends CompletionStage<U>>) new Function<ResRequestSetWaypoint, CompletableFuture<ResRequestSetWaypoint>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.131
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestSetWaypoint> apply(ResRequestSetWaypoint resRequestSetWaypoint) {
                return CommonUtil.delayAsync(resRequestSetWaypoint, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSetWaypoint.Arguments> requestRequestSetWaypoint(ReqRequestSetWaypoint.Arguments arguments) {
        ReqRequestSetWaypoint reqRequestSetWaypoint = new ReqRequestSetWaypoint();
        reqRequestSetWaypoint.setResponse(new ResRequestSetWaypoint());
        reqRequestSetWaypoint.setArguments(arguments);
        return requestRequestSetWaypoint(reqRequestSetWaypoint).thenApply((Function<? super ResRequestSetWaypoint, ? extends U>) new Function<ResRequestSetWaypoint, ResRequestSetWaypoint.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.132
            @Override // java9.util.function.Function
            public ResRequestSetWaypoint.Arguments apply(ResRequestSetWaypoint resRequestSetWaypoint) {
                return resRequestSetWaypoint.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSetWaypoint.Arguments> requestRequestSetWaypoint(ReqRequestSetWaypoint.Arguments arguments, final Duration duration) {
        return requestRequestSetWaypoint(arguments).thenCompose((Function<? super ResRequestSetWaypoint.Arguments, ? extends CompletionStage<U>>) new Function<ResRequestSetWaypoint.Arguments, CompletableFuture<ResRequestSetWaypoint.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.133
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestSetWaypoint.Arguments> apply(ResRequestSetWaypoint.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSetWaypoint(ReqRequestSetWaypoint reqRequestSetWaypoint) {
        reqRequestSetWaypoint.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestSetWaypoint.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestSetWaypoint)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.134
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSetWaypoint(ReqRequestSetWaypoint reqRequestSetWaypoint, final Duration duration) {
        return fireRequestSetWaypoint(reqRequestSetWaypoint).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.135
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSetWaypoint(ReqRequestSetWaypoint.Arguments arguments) {
        ReqRequestSetWaypoint reqRequestSetWaypoint = new ReqRequestSetWaypoint();
        reqRequestSetWaypoint.setArguments(arguments);
        reqRequestSetWaypoint.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestSetWaypoint.setComponent(str);
        }
        return fireRequestSetWaypoint(reqRequestSetWaypoint);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSetWaypoint(ReqRequestSetWaypoint.Arguments arguments, final Duration duration) {
        return fireRequestSetWaypoint(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.136
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetWaypointList> requestGetWaypointList(ReqGetWaypointList reqGetWaypointList) {
        if (reqGetWaypointList.getResponse() == null) {
            reqGetWaypointList.setResponse(new ResGetWaypointList());
        }
        String str = this.component;
        if (str != null) {
            reqGetWaypointList.setComponent(str);
            reqGetWaypointList.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetWaypointList));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetWaypointList> requestGetWaypointList(ReqGetWaypointList reqGetWaypointList, final Duration duration) {
        return requestGetWaypointList(reqGetWaypointList).thenCompose((Function<? super ResGetWaypointList, ? extends CompletionStage<U>>) new Function<ResGetWaypointList, CompletableFuture<ResGetWaypointList>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.137
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetWaypointList> apply(ResGetWaypointList resGetWaypointList) {
                return CommonUtil.delayAsync(resGetWaypointList, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetWaypointList> requestGetWaypointList() {
        return requestGetWaypointList(new ReqGetWaypointList());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetWaypointList> requestGetWaypointList(Duration duration) {
        return requestGetWaypointList(new ReqGetWaypointList(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetWaypointList(ReqGetWaypointList reqGetWaypointList) {
        reqGetWaypointList.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetWaypointList.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetWaypointList)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.138
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetWaypointList(ReqGetWaypointList reqGetWaypointList, final Duration duration) {
        return fireGetWaypointList(reqGetWaypointList).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.139
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetWaypointList() {
        return fireGetWaypointList(new ReqGetWaypointList());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetWaypointList(final Duration duration) {
        return fireGetWaypointList(new ReqGetWaypointList()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.140
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRemoveWaypoint(ReqRemoveWaypoint reqRemoveWaypoint) {
        reqRemoveWaypoint.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRemoveWaypoint.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRemoveWaypoint)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.141
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRemoveWaypoint(ReqRemoveWaypoint reqRemoveWaypoint, final Duration duration) {
        return fireRemoveWaypoint(reqRemoveWaypoint).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.142
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRemoveWaypoint(ReqRemoveWaypoint.Arguments arguments) {
        ReqRemoveWaypoint reqRemoveWaypoint = new ReqRemoveWaypoint();
        reqRemoveWaypoint.setArguments(arguments);
        reqRemoveWaypoint.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRemoveWaypoint.setComponent(str);
        }
        return fireRemoveWaypoint(reqRemoveWaypoint);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRemoveWaypoint(ReqRemoveWaypoint.Arguments arguments, final Duration duration) {
        return fireRemoveWaypoint(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.143
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSetRoutePreference> requestRequestSetRoutePreference(ReqRequestSetRoutePreference reqRequestSetRoutePreference) {
        if (reqRequestSetRoutePreference.getResponse() == null) {
            reqRequestSetRoutePreference.setResponse(new ResRequestSetRoutePreference());
        }
        String str = this.component;
        if (str != null) {
            reqRequestSetRoutePreference.setComponent(str);
            reqRequestSetRoutePreference.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestSetRoutePreference));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSetRoutePreference> requestRequestSetRoutePreference(ReqRequestSetRoutePreference reqRequestSetRoutePreference, final Duration duration) {
        return requestRequestSetRoutePreference(reqRequestSetRoutePreference).thenCompose((Function<? super ResRequestSetRoutePreference, ? extends CompletionStage<U>>) new Function<ResRequestSetRoutePreference, CompletableFuture<ResRequestSetRoutePreference>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.144
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestSetRoutePreference> apply(ResRequestSetRoutePreference resRequestSetRoutePreference) {
                return CommonUtil.delayAsync(resRequestSetRoutePreference, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSetRoutePreference.Arguments> requestRequestSetRoutePreference(ReqRequestSetRoutePreference.Arguments arguments) {
        ReqRequestSetRoutePreference reqRequestSetRoutePreference = new ReqRequestSetRoutePreference();
        reqRequestSetRoutePreference.setResponse(new ResRequestSetRoutePreference());
        reqRequestSetRoutePreference.setArguments(arguments);
        return requestRequestSetRoutePreference(reqRequestSetRoutePreference).thenApply((Function<? super ResRequestSetRoutePreference, ? extends U>) new Function<ResRequestSetRoutePreference, ResRequestSetRoutePreference.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.145
            @Override // java9.util.function.Function
            public ResRequestSetRoutePreference.Arguments apply(ResRequestSetRoutePreference resRequestSetRoutePreference) {
                return resRequestSetRoutePreference.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestSetRoutePreference.Arguments> requestRequestSetRoutePreference(ReqRequestSetRoutePreference.Arguments arguments, final Duration duration) {
        return requestRequestSetRoutePreference(arguments).thenCompose((Function<? super ResRequestSetRoutePreference.Arguments, ? extends CompletionStage<U>>) new Function<ResRequestSetRoutePreference.Arguments, CompletableFuture<ResRequestSetRoutePreference.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.146
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestSetRoutePreference.Arguments> apply(ResRequestSetRoutePreference.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSetRoutePreference(ReqRequestSetRoutePreference reqRequestSetRoutePreference) {
        reqRequestSetRoutePreference.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestSetRoutePreference.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestSetRoutePreference)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.147
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSetRoutePreference(ReqRequestSetRoutePreference reqRequestSetRoutePreference, final Duration duration) {
        return fireRequestSetRoutePreference(reqRequestSetRoutePreference).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.148
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSetRoutePreference(ReqRequestSetRoutePreference.Arguments arguments) {
        ReqRequestSetRoutePreference reqRequestSetRoutePreference = new ReqRequestSetRoutePreference();
        reqRequestSetRoutePreference.setArguments(arguments);
        reqRequestSetRoutePreference.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestSetRoutePreference.setComponent(str);
        }
        return fireRequestSetRoutePreference(reqRequestSetRoutePreference);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestSetRoutePreference(ReqRequestSetRoutePreference.Arguments arguments, final Duration duration) {
        return fireRequestSetRoutePreference(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.149
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetRoutePreference> requestGetRoutePreference(ReqGetRoutePreference reqGetRoutePreference) {
        if (reqGetRoutePreference.getResponse() == null) {
            reqGetRoutePreference.setResponse(new ResGetRoutePreference());
        }
        String str = this.component;
        if (str != null) {
            reqGetRoutePreference.setComponent(str);
            reqGetRoutePreference.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRoutePreference));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetRoutePreference> requestGetRoutePreference(ReqGetRoutePreference reqGetRoutePreference, final Duration duration) {
        return requestGetRoutePreference(reqGetRoutePreference).thenCompose((Function<? super ResGetRoutePreference, ? extends CompletionStage<U>>) new Function<ResGetRoutePreference, CompletableFuture<ResGetRoutePreference>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.150
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRoutePreference> apply(ResGetRoutePreference resGetRoutePreference) {
                return CommonUtil.delayAsync(resGetRoutePreference, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetRoutePreference> requestGetRoutePreference() {
        return requestGetRoutePreference(new ReqGetRoutePreference());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetRoutePreference> requestGetRoutePreference(Duration duration) {
        return requestGetRoutePreference(new ReqGetRoutePreference(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetRoutePreference(ReqGetRoutePreference reqGetRoutePreference) {
        reqGetRoutePreference.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRoutePreference.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRoutePreference)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.151
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetRoutePreference(ReqGetRoutePreference reqGetRoutePreference, final Duration duration) {
        return fireGetRoutePreference(reqGetRoutePreference).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.152
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetRoutePreference() {
        return fireGetRoutePreference(new ReqGetRoutePreference());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetRoutePreference(final Duration duration) {
        return fireGetRoutePreference(new ReqGetRoutePreference()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.153
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestDTDAndETA> requestRequestDTDAndETA(ReqRequestDTDAndETA reqRequestDTDAndETA) {
        if (reqRequestDTDAndETA.getResponse() == null) {
            reqRequestDTDAndETA.setResponse(new ResRequestDTDAndETA());
        }
        String str = this.component;
        if (str != null) {
            reqRequestDTDAndETA.setComponent(str);
            reqRequestDTDAndETA.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestDTDAndETA));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestDTDAndETA> requestRequestDTDAndETA(ReqRequestDTDAndETA reqRequestDTDAndETA, final Duration duration) {
        return requestRequestDTDAndETA(reqRequestDTDAndETA).thenCompose((Function<? super ResRequestDTDAndETA, ? extends CompletionStage<U>>) new Function<ResRequestDTDAndETA, CompletableFuture<ResRequestDTDAndETA>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.154
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestDTDAndETA> apply(ResRequestDTDAndETA resRequestDTDAndETA) {
                return CommonUtil.delayAsync(resRequestDTDAndETA, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestDTDAndETA> requestRequestDTDAndETA() {
        return requestRequestDTDAndETA(new ReqRequestDTDAndETA());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestDTDAndETA> requestRequestDTDAndETA(Duration duration) {
        return requestRequestDTDAndETA(new ReqRequestDTDAndETA(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestDTDAndETA(ReqRequestDTDAndETA reqRequestDTDAndETA) {
        reqRequestDTDAndETA.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestDTDAndETA.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestDTDAndETA)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.155
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestDTDAndETA(ReqRequestDTDAndETA reqRequestDTDAndETA, final Duration duration) {
        return fireRequestDTDAndETA(reqRequestDTDAndETA).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.156
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestDTDAndETA() {
        return fireRequestDTDAndETA(new ReqRequestDTDAndETA());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestDTDAndETA(final Duration duration) {
        return fireRequestDTDAndETA(new ReqRequestDTDAndETA()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.157
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetGuidedStatus> requestGetGuidedStatus(ReqGetGuidedStatus reqGetGuidedStatus) {
        if (reqGetGuidedStatus.getResponse() == null) {
            reqGetGuidedStatus.setResponse(new ResGetGuidedStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetGuidedStatus.setComponent(str);
            reqGetGuidedStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGuidedStatus));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetGuidedStatus> requestGetGuidedStatus(ReqGetGuidedStatus reqGetGuidedStatus, final Duration duration) {
        return requestGetGuidedStatus(reqGetGuidedStatus).thenCompose((Function<? super ResGetGuidedStatus, ? extends CompletionStage<U>>) new Function<ResGetGuidedStatus, CompletableFuture<ResGetGuidedStatus>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.158
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetGuidedStatus> apply(ResGetGuidedStatus resGetGuidedStatus) {
                return CommonUtil.delayAsync(resGetGuidedStatus, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetGuidedStatus> requestGetGuidedStatus() {
        return requestGetGuidedStatus(new ReqGetGuidedStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetGuidedStatus> requestGetGuidedStatus(Duration duration) {
        return requestGetGuidedStatus(new ReqGetGuidedStatus(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetGuidedStatus(ReqGetGuidedStatus reqGetGuidedStatus) {
        reqGetGuidedStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetGuidedStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGuidedStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.159
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetGuidedStatus(ReqGetGuidedStatus reqGetGuidedStatus, final Duration duration) {
        return fireGetGuidedStatus(reqGetGuidedStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.160
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetGuidedStatus() {
        return fireGetGuidedStatus(new ReqGetGuidedStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetGuidedStatus(final Duration duration) {
        return fireGetGuidedStatus(new ReqGetGuidedStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.161
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetGuidanceInfoStatusForGreatWall> requestGetGuidanceInfoStatusForGreatWall(ReqGetGuidanceInfoStatusForGreatWall reqGetGuidanceInfoStatusForGreatWall) {
        if (reqGetGuidanceInfoStatusForGreatWall.getResponse() == null) {
            reqGetGuidanceInfoStatusForGreatWall.setResponse(new ResGetGuidanceInfoStatusForGreatWall());
        }
        String str = this.component;
        if (str != null) {
            reqGetGuidanceInfoStatusForGreatWall.setComponent(str);
            reqGetGuidanceInfoStatusForGreatWall.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGuidanceInfoStatusForGreatWall));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetGuidanceInfoStatusForGreatWall> requestGetGuidanceInfoStatusForGreatWall(ReqGetGuidanceInfoStatusForGreatWall reqGetGuidanceInfoStatusForGreatWall, final Duration duration) {
        return requestGetGuidanceInfoStatusForGreatWall(reqGetGuidanceInfoStatusForGreatWall).thenCompose((Function<? super ResGetGuidanceInfoStatusForGreatWall, ? extends CompletionStage<U>>) new Function<ResGetGuidanceInfoStatusForGreatWall, CompletableFuture<ResGetGuidanceInfoStatusForGreatWall>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.162
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetGuidanceInfoStatusForGreatWall> apply(ResGetGuidanceInfoStatusForGreatWall resGetGuidanceInfoStatusForGreatWall) {
                return CommonUtil.delayAsync(resGetGuidanceInfoStatusForGreatWall, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetGuidanceInfoStatusForGreatWall> requestGetGuidanceInfoStatusForGreatWall() {
        return requestGetGuidanceInfoStatusForGreatWall(new ReqGetGuidanceInfoStatusForGreatWall());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetGuidanceInfoStatusForGreatWall> requestGetGuidanceInfoStatusForGreatWall(Duration duration) {
        return requestGetGuidanceInfoStatusForGreatWall(new ReqGetGuidanceInfoStatusForGreatWall(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceInfoStatusForGreatWall(ReqGetGuidanceInfoStatusForGreatWall reqGetGuidanceInfoStatusForGreatWall) {
        reqGetGuidanceInfoStatusForGreatWall.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetGuidanceInfoStatusForGreatWall.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGuidanceInfoStatusForGreatWall)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.163
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceInfoStatusForGreatWall(ReqGetGuidanceInfoStatusForGreatWall reqGetGuidanceInfoStatusForGreatWall, final Duration duration) {
        return fireGetGuidanceInfoStatusForGreatWall(reqGetGuidanceInfoStatusForGreatWall).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.164
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceInfoStatusForGreatWall() {
        return fireGetGuidanceInfoStatusForGreatWall(new ReqGetGuidanceInfoStatusForGreatWall());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceInfoStatusForGreatWall(final Duration duration) {
        return fireGetGuidanceInfoStatusForGreatWall(new ReqGetGuidanceInfoStatusForGreatWall()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.165
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetHeadingStatus> requestGetHeadingStatus(ReqGetHeadingStatus reqGetHeadingStatus) {
        if (reqGetHeadingStatus.getResponse() == null) {
            reqGetHeadingStatus.setResponse(new ResGetHeadingStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetHeadingStatus.setComponent(str);
            reqGetHeadingStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetHeadingStatus));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetHeadingStatus> requestGetHeadingStatus(ReqGetHeadingStatus reqGetHeadingStatus, final Duration duration) {
        return requestGetHeadingStatus(reqGetHeadingStatus).thenCompose((Function<? super ResGetHeadingStatus, ? extends CompletionStage<U>>) new Function<ResGetHeadingStatus, CompletableFuture<ResGetHeadingStatus>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.166
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetHeadingStatus> apply(ResGetHeadingStatus resGetHeadingStatus) {
                return CommonUtil.delayAsync(resGetHeadingStatus, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetHeadingStatus> requestGetHeadingStatus() {
        return requestGetHeadingStatus(new ReqGetHeadingStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetHeadingStatus> requestGetHeadingStatus(Duration duration) {
        return requestGetHeadingStatus(new ReqGetHeadingStatus(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetHeadingStatus(ReqGetHeadingStatus reqGetHeadingStatus) {
        reqGetHeadingStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetHeadingStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetHeadingStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.167
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetHeadingStatus(ReqGetHeadingStatus reqGetHeadingStatus, final Duration duration) {
        return fireGetHeadingStatus(reqGetHeadingStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.168
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetHeadingStatus() {
        return fireGetHeadingStatus(new ReqGetHeadingStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetHeadingStatus(final Duration duration) {
        return fireGetHeadingStatus(new ReqGetHeadingStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.169
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestDestinationHistoryList> requestRequestDestinationHistoryList(ReqRequestDestinationHistoryList reqRequestDestinationHistoryList) {
        if (reqRequestDestinationHistoryList.getResponse() == null) {
            reqRequestDestinationHistoryList.setResponse(new ResRequestDestinationHistoryList());
        }
        String str = this.component;
        if (str != null) {
            reqRequestDestinationHistoryList.setComponent(str);
            reqRequestDestinationHistoryList.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestDestinationHistoryList));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestDestinationHistoryList> requestRequestDestinationHistoryList(ReqRequestDestinationHistoryList reqRequestDestinationHistoryList, final Duration duration) {
        return requestRequestDestinationHistoryList(reqRequestDestinationHistoryList).thenCompose((Function<? super ResRequestDestinationHistoryList, ? extends CompletionStage<U>>) new Function<ResRequestDestinationHistoryList, CompletableFuture<ResRequestDestinationHistoryList>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.170
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestDestinationHistoryList> apply(ResRequestDestinationHistoryList resRequestDestinationHistoryList) {
                return CommonUtil.delayAsync(resRequestDestinationHistoryList, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestDestinationHistoryList> requestRequestDestinationHistoryList() {
        return requestRequestDestinationHistoryList(new ReqRequestDestinationHistoryList());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestDestinationHistoryList> requestRequestDestinationHistoryList(Duration duration) {
        return requestRequestDestinationHistoryList(new ReqRequestDestinationHistoryList(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestDestinationHistoryList(ReqRequestDestinationHistoryList reqRequestDestinationHistoryList) {
        reqRequestDestinationHistoryList.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestDestinationHistoryList.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestDestinationHistoryList)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.171
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestDestinationHistoryList(ReqRequestDestinationHistoryList reqRequestDestinationHistoryList, final Duration duration) {
        return fireRequestDestinationHistoryList(reqRequestDestinationHistoryList).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.172
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestDestinationHistoryList() {
        return fireRequestDestinationHistoryList(new ReqRequestDestinationHistoryList());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestDestinationHistoryList(final Duration duration) {
        return fireRequestDestinationHistoryList(new ReqRequestDestinationHistoryList()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.173
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestGetDestinationHistoryList> requestRequestGetDestinationHistoryList(ReqRequestGetDestinationHistoryList reqRequestGetDestinationHistoryList) {
        if (reqRequestGetDestinationHistoryList.getResponse() == null) {
            reqRequestGetDestinationHistoryList.setResponse(new ResRequestGetDestinationHistoryList());
        }
        String str = this.component;
        if (str != null) {
            reqRequestGetDestinationHistoryList.setComponent(str);
            reqRequestGetDestinationHistoryList.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestGetDestinationHistoryList));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestGetDestinationHistoryList> requestRequestGetDestinationHistoryList(ReqRequestGetDestinationHistoryList reqRequestGetDestinationHistoryList, final Duration duration) {
        return requestRequestGetDestinationHistoryList(reqRequestGetDestinationHistoryList).thenCompose((Function<? super ResRequestGetDestinationHistoryList, ? extends CompletionStage<U>>) new Function<ResRequestGetDestinationHistoryList, CompletableFuture<ResRequestGetDestinationHistoryList>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.174
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestGetDestinationHistoryList> apply(ResRequestGetDestinationHistoryList resRequestGetDestinationHistoryList) {
                return CommonUtil.delayAsync(resRequestGetDestinationHistoryList, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestGetDestinationHistoryList> requestRequestGetDestinationHistoryList() {
        return requestRequestGetDestinationHistoryList(new ReqRequestGetDestinationHistoryList());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestGetDestinationHistoryList> requestRequestGetDestinationHistoryList(Duration duration) {
        return requestRequestGetDestinationHistoryList(new ReqRequestGetDestinationHistoryList(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestGetDestinationHistoryList(ReqRequestGetDestinationHistoryList reqRequestGetDestinationHistoryList) {
        reqRequestGetDestinationHistoryList.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestGetDestinationHistoryList.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestGetDestinationHistoryList)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.175
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestGetDestinationHistoryList(ReqRequestGetDestinationHistoryList reqRequestGetDestinationHistoryList, final Duration duration) {
        return fireRequestGetDestinationHistoryList(reqRequestGetDestinationHistoryList).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.176
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestGetDestinationHistoryList() {
        return fireRequestGetDestinationHistoryList(new ReqRequestGetDestinationHistoryList());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestGetDestinationHistoryList(final Duration duration) {
        return fireRequestGetDestinationHistoryList(new ReqRequestGetDestinationHistoryList()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.177
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestCalcPlanningRoute> requestRequestCalcPlanningRoute(ReqRequestCalcPlanningRoute reqRequestCalcPlanningRoute) {
        if (reqRequestCalcPlanningRoute.getResponse() == null) {
            reqRequestCalcPlanningRoute.setResponse(new ResRequestCalcPlanningRoute());
        }
        String str = this.component;
        if (str != null) {
            reqRequestCalcPlanningRoute.setComponent(str);
            reqRequestCalcPlanningRoute.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestCalcPlanningRoute));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestCalcPlanningRoute> requestRequestCalcPlanningRoute(ReqRequestCalcPlanningRoute reqRequestCalcPlanningRoute, final Duration duration) {
        return requestRequestCalcPlanningRoute(reqRequestCalcPlanningRoute).thenCompose((Function<? super ResRequestCalcPlanningRoute, ? extends CompletionStage<U>>) new Function<ResRequestCalcPlanningRoute, CompletableFuture<ResRequestCalcPlanningRoute>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.178
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestCalcPlanningRoute> apply(ResRequestCalcPlanningRoute resRequestCalcPlanningRoute) {
                return CommonUtil.delayAsync(resRequestCalcPlanningRoute, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestCalcPlanningRoute.Arguments> requestRequestCalcPlanningRoute(ReqRequestCalcPlanningRoute.Arguments arguments) {
        ReqRequestCalcPlanningRoute reqRequestCalcPlanningRoute = new ReqRequestCalcPlanningRoute();
        reqRequestCalcPlanningRoute.setResponse(new ResRequestCalcPlanningRoute());
        reqRequestCalcPlanningRoute.setArguments(arguments);
        return requestRequestCalcPlanningRoute(reqRequestCalcPlanningRoute).thenApply((Function<? super ResRequestCalcPlanningRoute, ? extends U>) new Function<ResRequestCalcPlanningRoute, ResRequestCalcPlanningRoute.Arguments>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.179
            @Override // java9.util.function.Function
            public ResRequestCalcPlanningRoute.Arguments apply(ResRequestCalcPlanningRoute resRequestCalcPlanningRoute) {
                return resRequestCalcPlanningRoute.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResRequestCalcPlanningRoute.Arguments> requestRequestCalcPlanningRoute(ReqRequestCalcPlanningRoute.Arguments arguments, final Duration duration) {
        return requestRequestCalcPlanningRoute(arguments).thenCompose((Function<? super ResRequestCalcPlanningRoute.Arguments, ? extends CompletionStage<U>>) new Function<ResRequestCalcPlanningRoute.Arguments, CompletableFuture<ResRequestCalcPlanningRoute.Arguments>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.180
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestCalcPlanningRoute.Arguments> apply(ResRequestCalcPlanningRoute.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestCalcPlanningRoute(ReqRequestCalcPlanningRoute reqRequestCalcPlanningRoute) {
        reqRequestCalcPlanningRoute.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestCalcPlanningRoute.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestCalcPlanningRoute)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.181
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestCalcPlanningRoute(ReqRequestCalcPlanningRoute reqRequestCalcPlanningRoute, final Duration duration) {
        return fireRequestCalcPlanningRoute(reqRequestCalcPlanningRoute).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.182
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestCalcPlanningRoute(ReqRequestCalcPlanningRoute.Arguments arguments) {
        ReqRequestCalcPlanningRoute reqRequestCalcPlanningRoute = new ReqRequestCalcPlanningRoute();
        reqRequestCalcPlanningRoute.setArguments(arguments);
        reqRequestCalcPlanningRoute.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestCalcPlanningRoute.setComponent(str);
        }
        return fireRequestCalcPlanningRoute(reqRequestCalcPlanningRoute);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireRequestCalcPlanningRoute(ReqRequestCalcPlanningRoute.Arguments arguments, final Duration duration) {
        return fireRequestCalcPlanningRoute(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.183
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetPrivacyMode(ReqSetPrivacyMode reqSetPrivacyMode) {
        reqSetPrivacyMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetPrivacyMode.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetPrivacyMode)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.184
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetPrivacyMode(ReqSetPrivacyMode reqSetPrivacyMode, final Duration duration) {
        return fireSetPrivacyMode(reqSetPrivacyMode).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.185
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetPrivacyMode(ReqSetPrivacyMode.Arguments arguments) {
        ReqSetPrivacyMode reqSetPrivacyMode = new ReqSetPrivacyMode();
        reqSetPrivacyMode.setArguments(arguments);
        reqSetPrivacyMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetPrivacyMode.setComponent(str);
        }
        return fireSetPrivacyMode(reqSetPrivacyMode);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetPrivacyMode(ReqSetPrivacyMode.Arguments arguments, final Duration duration) {
        return fireSetPrivacyMode(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.186
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetCountryInfo> requestGetCountryInfo(ReqGetCountryInfo reqGetCountryInfo) {
        if (reqGetCountryInfo.getResponse() == null) {
            reqGetCountryInfo.setResponse(new ResGetCountryInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetCountryInfo.setComponent(str);
            reqGetCountryInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCountryInfo));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetCountryInfo> requestGetCountryInfo(ReqGetCountryInfo reqGetCountryInfo, final Duration duration) {
        return requestGetCountryInfo(reqGetCountryInfo).thenCompose((Function<? super ResGetCountryInfo, ? extends CompletionStage<U>>) new Function<ResGetCountryInfo, CompletableFuture<ResGetCountryInfo>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.187
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetCountryInfo> apply(ResGetCountryInfo resGetCountryInfo) {
                return CommonUtil.delayAsync(resGetCountryInfo, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetCountryInfo> requestGetCountryInfo() {
        return requestGetCountryInfo(new ReqGetCountryInfo());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetCountryInfo> requestGetCountryInfo(Duration duration) {
        return requestGetCountryInfo(new ReqGetCountryInfo(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetCountryInfo(ReqGetCountryInfo reqGetCountryInfo) {
        reqGetCountryInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetCountryInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCountryInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.188
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetCountryInfo(ReqGetCountryInfo reqGetCountryInfo, final Duration duration) {
        return fireGetCountryInfo(reqGetCountryInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.189
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetCountryInfo() {
        return fireGetCountryInfo(new ReqGetCountryInfo());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetCountryInfo(final Duration duration) {
        return fireGetCountryInfo(new ReqGetCountryInfo()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.190
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetShareLocationStatus(ReqSetShareLocationStatus reqSetShareLocationStatus) {
        reqSetShareLocationStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetShareLocationStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetShareLocationStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.191
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetShareLocationStatus(ReqSetShareLocationStatus reqSetShareLocationStatus, final Duration duration) {
        return fireSetShareLocationStatus(reqSetShareLocationStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.192
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetShareLocationStatus(ReqSetShareLocationStatus.Arguments arguments) {
        ReqSetShareLocationStatus reqSetShareLocationStatus = new ReqSetShareLocationStatus();
        reqSetShareLocationStatus.setArguments(arguments);
        reqSetShareLocationStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetShareLocationStatus.setComponent(str);
        }
        return fireSetShareLocationStatus(reqSetShareLocationStatus);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetShareLocationStatus(ReqSetShareLocationStatus.Arguments arguments, final Duration duration) {
        return fireSetShareLocationStatus(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.193
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetDataTrackEnable(ReqSetDataTrackEnable reqSetDataTrackEnable) {
        reqSetDataTrackEnable.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetDataTrackEnable.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetDataTrackEnable)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.194
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetDataTrackEnable(ReqSetDataTrackEnable reqSetDataTrackEnable, final Duration duration) {
        return fireSetDataTrackEnable(reqSetDataTrackEnable).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.195
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetDataTrackEnable(ReqSetDataTrackEnable.Arguments arguments) {
        ReqSetDataTrackEnable reqSetDataTrackEnable = new ReqSetDataTrackEnable();
        reqSetDataTrackEnable.setArguments(arguments);
        reqSetDataTrackEnable.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetDataTrackEnable.setComponent(str);
        }
        return fireSetDataTrackEnable(reqSetDataTrackEnable);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireSetDataTrackEnable(ReqSetDataTrackEnable.Arguments arguments, final Duration duration) {
        return fireSetDataTrackEnable(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.196
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetFrontTraffic> requestGetFrontTraffic(ReqGetFrontTraffic reqGetFrontTraffic) {
        if (reqGetFrontTraffic.getResponse() == null) {
            reqGetFrontTraffic.setResponse(new ResGetFrontTraffic());
        }
        String str = this.component;
        if (str != null) {
            reqGetFrontTraffic.setComponent(str);
            reqGetFrontTraffic.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetFrontTraffic));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetFrontTraffic> requestGetFrontTraffic(ReqGetFrontTraffic reqGetFrontTraffic, final Duration duration) {
        return requestGetFrontTraffic(reqGetFrontTraffic).thenCompose((Function<? super ResGetFrontTraffic, ? extends CompletionStage<U>>) new Function<ResGetFrontTraffic, CompletableFuture<ResGetFrontTraffic>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.197
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetFrontTraffic> apply(ResGetFrontTraffic resGetFrontTraffic) {
                return CommonUtil.delayAsync(resGetFrontTraffic, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetFrontTraffic> requestGetFrontTraffic() {
        return requestGetFrontTraffic(new ReqGetFrontTraffic());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetFrontTraffic> requestGetFrontTraffic(Duration duration) {
        return requestGetFrontTraffic(new ReqGetFrontTraffic(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetFrontTraffic(ReqGetFrontTraffic reqGetFrontTraffic) {
        reqGetFrontTraffic.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetFrontTraffic.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetFrontTraffic)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.198
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetFrontTraffic(ReqGetFrontTraffic reqGetFrontTraffic, final Duration duration) {
        return fireGetFrontTraffic(reqGetFrontTraffic).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.199
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetFrontTraffic() {
        return fireGetFrontTraffic(new ReqGetFrontTraffic());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetFrontTraffic(final Duration duration) {
        return fireGetFrontTraffic(new ReqGetFrontTraffic()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.200
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireShowModeChanged(ReqShowModeChanged reqShowModeChanged) {
        reqShowModeChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqShowModeChanged.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqShowModeChanged)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.201
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireShowModeChanged(ReqShowModeChanged reqShowModeChanged, final Duration duration) {
        return fireShowModeChanged(reqShowModeChanged).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.202
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireShowModeChanged(ReqShowModeChanged.Arguments arguments) {
        ReqShowModeChanged reqShowModeChanged = new ReqShowModeChanged();
        reqShowModeChanged.setArguments(arguments);
        reqShowModeChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqShowModeChanged.setComponent(str);
        }
        return fireShowModeChanged(reqShowModeChanged);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireShowModeChanged(ReqShowModeChanged.Arguments arguments, final Duration duration) {
        return fireShowModeChanged(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.203
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> firePowerSavingModeChanged(ReqPowerSavingModeChanged reqPowerSavingModeChanged) {
        reqPowerSavingModeChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqPowerSavingModeChanged.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqPowerSavingModeChanged)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.204
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> firePowerSavingModeChanged(ReqPowerSavingModeChanged reqPowerSavingModeChanged, final Duration duration) {
        return firePowerSavingModeChanged(reqPowerSavingModeChanged).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.205
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> firePowerSavingModeChanged(ReqPowerSavingModeChanged.Arguments arguments) {
        ReqPowerSavingModeChanged reqPowerSavingModeChanged = new ReqPowerSavingModeChanged();
        reqPowerSavingModeChanged.setArguments(arguments);
        reqPowerSavingModeChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqPowerSavingModeChanged.setComponent(str);
        }
        return firePowerSavingModeChanged(reqPowerSavingModeChanged);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> firePowerSavingModeChanged(ReqPowerSavingModeChanged.Arguments arguments, final Duration duration) {
        return firePowerSavingModeChanged(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.206
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetSwitchNavigationLanguageSetting> requestGetSwitchNavigationLanguageSetting(ReqGetSwitchNavigationLanguageSetting reqGetSwitchNavigationLanguageSetting) {
        if (reqGetSwitchNavigationLanguageSetting.getResponse() == null) {
            reqGetSwitchNavigationLanguageSetting.setResponse(new ResGetSwitchNavigationLanguageSetting());
        }
        String str = this.component;
        if (str != null) {
            reqGetSwitchNavigationLanguageSetting.setComponent(str);
            reqGetSwitchNavigationLanguageSetting.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSwitchNavigationLanguageSetting));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetSwitchNavigationLanguageSetting> requestGetSwitchNavigationLanguageSetting(ReqGetSwitchNavigationLanguageSetting reqGetSwitchNavigationLanguageSetting, final Duration duration) {
        return requestGetSwitchNavigationLanguageSetting(reqGetSwitchNavigationLanguageSetting).thenCompose((Function<? super ResGetSwitchNavigationLanguageSetting, ? extends CompletionStage<U>>) new Function<ResGetSwitchNavigationLanguageSetting, CompletableFuture<ResGetSwitchNavigationLanguageSetting>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.207
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetSwitchNavigationLanguageSetting> apply(ResGetSwitchNavigationLanguageSetting resGetSwitchNavigationLanguageSetting) {
                return CommonUtil.delayAsync(resGetSwitchNavigationLanguageSetting, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetSwitchNavigationLanguageSetting> requestGetSwitchNavigationLanguageSetting() {
        return requestGetSwitchNavigationLanguageSetting(new ReqGetSwitchNavigationLanguageSetting());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetSwitchNavigationLanguageSetting> requestGetSwitchNavigationLanguageSetting(Duration duration) {
        return requestGetSwitchNavigationLanguageSetting(new ReqGetSwitchNavigationLanguageSetting(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetSwitchNavigationLanguageSetting(ReqGetSwitchNavigationLanguageSetting reqGetSwitchNavigationLanguageSetting) {
        reqGetSwitchNavigationLanguageSetting.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetSwitchNavigationLanguageSetting.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSwitchNavigationLanguageSetting)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.208
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetSwitchNavigationLanguageSetting(ReqGetSwitchNavigationLanguageSetting reqGetSwitchNavigationLanguageSetting, final Duration duration) {
        return fireGetSwitchNavigationLanguageSetting(reqGetSwitchNavigationLanguageSetting).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.209
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetSwitchNavigationLanguageSetting() {
        return fireGetSwitchNavigationLanguageSetting(new ReqGetSwitchNavigationLanguageSetting());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetSwitchNavigationLanguageSetting(final Duration duration) {
        return fireGetSwitchNavigationLanguageSetting(new ReqGetSwitchNavigationLanguageSetting()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.210
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNaviMuteStatus> requestGetNaviMuteStatus(ReqGetNaviMuteStatus reqGetNaviMuteStatus) {
        if (reqGetNaviMuteStatus.getResponse() == null) {
            reqGetNaviMuteStatus.setResponse(new ResGetNaviMuteStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetNaviMuteStatus.setComponent(str);
            reqGetNaviMuteStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviMuteStatus));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNaviMuteStatus> requestGetNaviMuteStatus(ReqGetNaviMuteStatus reqGetNaviMuteStatus, final Duration duration) {
        return requestGetNaviMuteStatus(reqGetNaviMuteStatus).thenCompose((Function<? super ResGetNaviMuteStatus, ? extends CompletionStage<U>>) new Function<ResGetNaviMuteStatus, CompletableFuture<ResGetNaviMuteStatus>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.211
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNaviMuteStatus> apply(ResGetNaviMuteStatus resGetNaviMuteStatus) {
                return CommonUtil.delayAsync(resGetNaviMuteStatus, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNaviMuteStatus> requestGetNaviMuteStatus() {
        return requestGetNaviMuteStatus(new ReqGetNaviMuteStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNaviMuteStatus> requestGetNaviMuteStatus(Duration duration) {
        return requestGetNaviMuteStatus(new ReqGetNaviMuteStatus(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNaviMuteStatus(ReqGetNaviMuteStatus reqGetNaviMuteStatus) {
        reqGetNaviMuteStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNaviMuteStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviMuteStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.212
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNaviMuteStatus(ReqGetNaviMuteStatus reqGetNaviMuteStatus, final Duration duration) {
        return fireGetNaviMuteStatus(reqGetNaviMuteStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.213
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNaviMuteStatus() {
        return fireGetNaviMuteStatus(new ReqGetNaviMuteStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNaviMuteStatus(final Duration duration) {
        return fireGetNaviMuteStatus(new ReqGetNaviMuteStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.214
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetReportLocationInfo> requestGetReportLocationInfo(ReqGetReportLocationInfo reqGetReportLocationInfo) {
        if (reqGetReportLocationInfo.getResponse() == null) {
            reqGetReportLocationInfo.setResponse(new ResGetReportLocationInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetReportLocationInfo.setComponent(str);
            reqGetReportLocationInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetReportLocationInfo));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetReportLocationInfo> requestGetReportLocationInfo(ReqGetReportLocationInfo reqGetReportLocationInfo, final Duration duration) {
        return requestGetReportLocationInfo(reqGetReportLocationInfo).thenCompose((Function<? super ResGetReportLocationInfo, ? extends CompletionStage<U>>) new Function<ResGetReportLocationInfo, CompletableFuture<ResGetReportLocationInfo>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.215
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetReportLocationInfo> apply(ResGetReportLocationInfo resGetReportLocationInfo) {
                return CommonUtil.delayAsync(resGetReportLocationInfo, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetReportLocationInfo> requestGetReportLocationInfo() {
        return requestGetReportLocationInfo(new ReqGetReportLocationInfo());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetReportLocationInfo> requestGetReportLocationInfo(Duration duration) {
        return requestGetReportLocationInfo(new ReqGetReportLocationInfo(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetReportLocationInfo(ReqGetReportLocationInfo reqGetReportLocationInfo) {
        reqGetReportLocationInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetReportLocationInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetReportLocationInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.216
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetReportLocationInfo(ReqGetReportLocationInfo reqGetReportLocationInfo, final Duration duration) {
        return fireGetReportLocationInfo(reqGetReportLocationInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.217
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetReportLocationInfo() {
        return fireGetReportLocationInfo(new ReqGetReportLocationInfo());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetReportLocationInfo(final Duration duration) {
        return fireGetReportLocationInfo(new ReqGetReportLocationInfo()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.218
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetResponseDirectNavigation> requestGetResponseDirectNavigation(ReqGetResponseDirectNavigation reqGetResponseDirectNavigation) {
        if (reqGetResponseDirectNavigation.getResponse() == null) {
            reqGetResponseDirectNavigation.setResponse(new ResGetResponseDirectNavigation());
        }
        String str = this.component;
        if (str != null) {
            reqGetResponseDirectNavigation.setComponent(str);
            reqGetResponseDirectNavigation.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetResponseDirectNavigation));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetResponseDirectNavigation> requestGetResponseDirectNavigation(ReqGetResponseDirectNavigation reqGetResponseDirectNavigation, final Duration duration) {
        return requestGetResponseDirectNavigation(reqGetResponseDirectNavigation).thenCompose((Function<? super ResGetResponseDirectNavigation, ? extends CompletionStage<U>>) new Function<ResGetResponseDirectNavigation, CompletableFuture<ResGetResponseDirectNavigation>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.219
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetResponseDirectNavigation> apply(ResGetResponseDirectNavigation resGetResponseDirectNavigation) {
                return CommonUtil.delayAsync(resGetResponseDirectNavigation, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetResponseDirectNavigation> requestGetResponseDirectNavigation() {
        return requestGetResponseDirectNavigation(new ReqGetResponseDirectNavigation());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetResponseDirectNavigation> requestGetResponseDirectNavigation(Duration duration) {
        return requestGetResponseDirectNavigation(new ReqGetResponseDirectNavigation(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetResponseDirectNavigation(ReqGetResponseDirectNavigation reqGetResponseDirectNavigation) {
        reqGetResponseDirectNavigation.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetResponseDirectNavigation.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetResponseDirectNavigation)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.220
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetResponseDirectNavigation(ReqGetResponseDirectNavigation reqGetResponseDirectNavigation, final Duration duration) {
        return fireGetResponseDirectNavigation(reqGetResponseDirectNavigation).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.221
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetResponseDirectNavigation() {
        return fireGetResponseDirectNavigation(new ReqGetResponseDirectNavigation());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetResponseDirectNavigation(final Duration duration) {
        return fireGetResponseDirectNavigation(new ReqGetResponseDirectNavigation()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.222
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNaviSettingStateStatus> requestGetNaviSettingStateStatus(ReqGetNaviSettingStateStatus reqGetNaviSettingStateStatus) {
        if (reqGetNaviSettingStateStatus.getResponse() == null) {
            reqGetNaviSettingStateStatus.setResponse(new ResGetNaviSettingStateStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetNaviSettingStateStatus.setComponent(str);
            reqGetNaviSettingStateStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviSettingStateStatus));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNaviSettingStateStatus> requestGetNaviSettingStateStatus(ReqGetNaviSettingStateStatus reqGetNaviSettingStateStatus, final Duration duration) {
        return requestGetNaviSettingStateStatus(reqGetNaviSettingStateStatus).thenCompose((Function<? super ResGetNaviSettingStateStatus, ? extends CompletionStage<U>>) new Function<ResGetNaviSettingStateStatus, CompletableFuture<ResGetNaviSettingStateStatus>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.223
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNaviSettingStateStatus> apply(ResGetNaviSettingStateStatus resGetNaviSettingStateStatus) {
                return CommonUtil.delayAsync(resGetNaviSettingStateStatus, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNaviSettingStateStatus> requestGetNaviSettingStateStatus() {
        return requestGetNaviSettingStateStatus(new ReqGetNaviSettingStateStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNaviSettingStateStatus> requestGetNaviSettingStateStatus(Duration duration) {
        return requestGetNaviSettingStateStatus(new ReqGetNaviSettingStateStatus(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNaviSettingStateStatus(ReqGetNaviSettingStateStatus reqGetNaviSettingStateStatus) {
        reqGetNaviSettingStateStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNaviSettingStateStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviSettingStateStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.224
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNaviSettingStateStatus(ReqGetNaviSettingStateStatus reqGetNaviSettingStateStatus, final Duration duration) {
        return fireGetNaviSettingStateStatus(reqGetNaviSettingStateStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.225
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNaviSettingStateStatus() {
        return fireGetNaviSettingStateStatus(new ReqGetNaviSettingStateStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNaviSettingStateStatus(final Duration duration) {
        return fireGetNaviSettingStateStatus(new ReqGetNaviSettingStateStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.226
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetGuidanceStatus> requestGetGuidanceStatus(ReqGetGuidanceStatus reqGetGuidanceStatus) {
        if (reqGetGuidanceStatus.getResponse() == null) {
            reqGetGuidanceStatus.setResponse(new ResGetGuidanceStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetGuidanceStatus.setComponent(str);
            reqGetGuidanceStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGuidanceStatus));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetGuidanceStatus> requestGetGuidanceStatus(ReqGetGuidanceStatus reqGetGuidanceStatus, final Duration duration) {
        return requestGetGuidanceStatus(reqGetGuidanceStatus).thenCompose((Function<? super ResGetGuidanceStatus, ? extends CompletionStage<U>>) new Function<ResGetGuidanceStatus, CompletableFuture<ResGetGuidanceStatus>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.227
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetGuidanceStatus> apply(ResGetGuidanceStatus resGetGuidanceStatus) {
                return CommonUtil.delayAsync(resGetGuidanceStatus, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetGuidanceStatus> requestGetGuidanceStatus() {
        return requestGetGuidanceStatus(new ReqGetGuidanceStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetGuidanceStatus> requestGetGuidanceStatus(Duration duration) {
        return requestGetGuidanceStatus(new ReqGetGuidanceStatus(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceStatus(ReqGetGuidanceStatus reqGetGuidanceStatus) {
        reqGetGuidanceStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetGuidanceStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGuidanceStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.228
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceStatus(ReqGetGuidanceStatus reqGetGuidanceStatus, final Duration duration) {
        return fireGetGuidanceStatus(reqGetGuidanceStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.229
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceStatus() {
        return fireGetGuidanceStatus(new ReqGetGuidanceStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceStatus(final Duration duration) {
        return fireGetGuidanceStatus(new ReqGetGuidanceStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.230
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetGuidanceInfoStatus> requestGetGuidanceInfoStatus(ReqGetGuidanceInfoStatus reqGetGuidanceInfoStatus) {
        if (reqGetGuidanceInfoStatus.getResponse() == null) {
            reqGetGuidanceInfoStatus.setResponse(new ResGetGuidanceInfoStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetGuidanceInfoStatus.setComponent(str);
            reqGetGuidanceInfoStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGuidanceInfoStatus));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetGuidanceInfoStatus> requestGetGuidanceInfoStatus(ReqGetGuidanceInfoStatus reqGetGuidanceInfoStatus, final Duration duration) {
        return requestGetGuidanceInfoStatus(reqGetGuidanceInfoStatus).thenCompose((Function<? super ResGetGuidanceInfoStatus, ? extends CompletionStage<U>>) new Function<ResGetGuidanceInfoStatus, CompletableFuture<ResGetGuidanceInfoStatus>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.231
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetGuidanceInfoStatus> apply(ResGetGuidanceInfoStatus resGetGuidanceInfoStatus) {
                return CommonUtil.delayAsync(resGetGuidanceInfoStatus, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetGuidanceInfoStatus> requestGetGuidanceInfoStatus() {
        return requestGetGuidanceInfoStatus(new ReqGetGuidanceInfoStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetGuidanceInfoStatus> requestGetGuidanceInfoStatus(Duration duration) {
        return requestGetGuidanceInfoStatus(new ReqGetGuidanceInfoStatus(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceInfoStatus(ReqGetGuidanceInfoStatus reqGetGuidanceInfoStatus) {
        reqGetGuidanceInfoStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetGuidanceInfoStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGuidanceInfoStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.232
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceInfoStatus(ReqGetGuidanceInfoStatus reqGetGuidanceInfoStatus, final Duration duration) {
        return fireGetGuidanceInfoStatus(reqGetGuidanceInfoStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.233
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceInfoStatus() {
        return fireGetGuidanceInfoStatus(new ReqGetGuidanceInfoStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceInfoStatus(final Duration duration) {
        return fireGetGuidanceInfoStatus(new ReqGetGuidanceInfoStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.234
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNotifyHeadingStatus> requestGetNotifyHeadingStatus(ReqGetNotifyHeadingStatus reqGetNotifyHeadingStatus) {
        if (reqGetNotifyHeadingStatus.getResponse() == null) {
            reqGetNotifyHeadingStatus.setResponse(new ResGetNotifyHeadingStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetNotifyHeadingStatus.setComponent(str);
            reqGetNotifyHeadingStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNotifyHeadingStatus));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNotifyHeadingStatus> requestGetNotifyHeadingStatus(ReqGetNotifyHeadingStatus reqGetNotifyHeadingStatus, final Duration duration) {
        return requestGetNotifyHeadingStatus(reqGetNotifyHeadingStatus).thenCompose((Function<? super ResGetNotifyHeadingStatus, ? extends CompletionStage<U>>) new Function<ResGetNotifyHeadingStatus, CompletableFuture<ResGetNotifyHeadingStatus>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.235
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNotifyHeadingStatus> apply(ResGetNotifyHeadingStatus resGetNotifyHeadingStatus) {
                return CommonUtil.delayAsync(resGetNotifyHeadingStatus, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNotifyHeadingStatus> requestGetNotifyHeadingStatus() {
        return requestGetNotifyHeadingStatus(new ReqGetNotifyHeadingStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNotifyHeadingStatus> requestGetNotifyHeadingStatus(Duration duration) {
        return requestGetNotifyHeadingStatus(new ReqGetNotifyHeadingStatus(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNotifyHeadingStatus(ReqGetNotifyHeadingStatus reqGetNotifyHeadingStatus) {
        reqGetNotifyHeadingStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNotifyHeadingStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNotifyHeadingStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.236
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNotifyHeadingStatus(ReqGetNotifyHeadingStatus reqGetNotifyHeadingStatus, final Duration duration) {
        return fireGetNotifyHeadingStatus(reqGetNotifyHeadingStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.237
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNotifyHeadingStatus() {
        return fireGetNotifyHeadingStatus(new ReqGetNotifyHeadingStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNotifyHeadingStatus(final Duration duration) {
        return fireGetNotifyHeadingStatus(new ReqGetNotifyHeadingStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.238
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetDestinationHistoryListStatus> requestGetDestinationHistoryListStatus(ReqGetDestinationHistoryListStatus reqGetDestinationHistoryListStatus) {
        if (reqGetDestinationHistoryListStatus.getResponse() == null) {
            reqGetDestinationHistoryListStatus.setResponse(new ResGetDestinationHistoryListStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetDestinationHistoryListStatus.setComponent(str);
            reqGetDestinationHistoryListStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDestinationHistoryListStatus));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetDestinationHistoryListStatus> requestGetDestinationHistoryListStatus(ReqGetDestinationHistoryListStatus reqGetDestinationHistoryListStatus, final Duration duration) {
        return requestGetDestinationHistoryListStatus(reqGetDestinationHistoryListStatus).thenCompose((Function<? super ResGetDestinationHistoryListStatus, ? extends CompletionStage<U>>) new Function<ResGetDestinationHistoryListStatus, CompletableFuture<ResGetDestinationHistoryListStatus>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.239
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetDestinationHistoryListStatus> apply(ResGetDestinationHistoryListStatus resGetDestinationHistoryListStatus) {
                return CommonUtil.delayAsync(resGetDestinationHistoryListStatus, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetDestinationHistoryListStatus> requestGetDestinationHistoryListStatus() {
        return requestGetDestinationHistoryListStatus(new ReqGetDestinationHistoryListStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetDestinationHistoryListStatus> requestGetDestinationHistoryListStatus(Duration duration) {
        return requestGetDestinationHistoryListStatus(new ReqGetDestinationHistoryListStatus(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetDestinationHistoryListStatus(ReqGetDestinationHistoryListStatus reqGetDestinationHistoryListStatus) {
        reqGetDestinationHistoryListStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetDestinationHistoryListStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDestinationHistoryListStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.240
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetDestinationHistoryListStatus(ReqGetDestinationHistoryListStatus reqGetDestinationHistoryListStatus, final Duration duration) {
        return fireGetDestinationHistoryListStatus(reqGetDestinationHistoryListStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.241
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetDestinationHistoryListStatus() {
        return fireGetDestinationHistoryListStatus(new ReqGetDestinationHistoryListStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetDestinationHistoryListStatus(final Duration duration) {
        return fireGetDestinationHistoryListStatus(new ReqGetDestinationHistoryListStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.242
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetRequestPrivacyMode> requestGetRequestPrivacyMode(ReqGetRequestPrivacyMode reqGetRequestPrivacyMode) {
        if (reqGetRequestPrivacyMode.getResponse() == null) {
            reqGetRequestPrivacyMode.setResponse(new ResGetRequestPrivacyMode());
        }
        String str = this.component;
        if (str != null) {
            reqGetRequestPrivacyMode.setComponent(str);
            reqGetRequestPrivacyMode.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestPrivacyMode));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetRequestPrivacyMode> requestGetRequestPrivacyMode(ReqGetRequestPrivacyMode reqGetRequestPrivacyMode, final Duration duration) {
        return requestGetRequestPrivacyMode(reqGetRequestPrivacyMode).thenCompose((Function<? super ResGetRequestPrivacyMode, ? extends CompletionStage<U>>) new Function<ResGetRequestPrivacyMode, CompletableFuture<ResGetRequestPrivacyMode>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.243
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRequestPrivacyMode> apply(ResGetRequestPrivacyMode resGetRequestPrivacyMode) {
                return CommonUtil.delayAsync(resGetRequestPrivacyMode, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetRequestPrivacyMode> requestGetRequestPrivacyMode() {
        return requestGetRequestPrivacyMode(new ReqGetRequestPrivacyMode());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetRequestPrivacyMode> requestGetRequestPrivacyMode(Duration duration) {
        return requestGetRequestPrivacyMode(new ReqGetRequestPrivacyMode(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetRequestPrivacyMode(ReqGetRequestPrivacyMode reqGetRequestPrivacyMode) {
        reqGetRequestPrivacyMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRequestPrivacyMode.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestPrivacyMode)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.244
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetRequestPrivacyMode(ReqGetRequestPrivacyMode reqGetRequestPrivacyMode, final Duration duration) {
        return fireGetRequestPrivacyMode(reqGetRequestPrivacyMode).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.245
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetRequestPrivacyMode() {
        return fireGetRequestPrivacyMode(new ReqGetRequestPrivacyMode());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetRequestPrivacyMode(final Duration duration) {
        return fireGetRequestPrivacyMode(new ReqGetRequestPrivacyMode()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.246
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetCalcPlanningRouteStatus> requestGetCalcPlanningRouteStatus(ReqGetCalcPlanningRouteStatus reqGetCalcPlanningRouteStatus) {
        if (reqGetCalcPlanningRouteStatus.getResponse() == null) {
            reqGetCalcPlanningRouteStatus.setResponse(new ResGetCalcPlanningRouteStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetCalcPlanningRouteStatus.setComponent(str);
            reqGetCalcPlanningRouteStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCalcPlanningRouteStatus));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetCalcPlanningRouteStatus> requestGetCalcPlanningRouteStatus(ReqGetCalcPlanningRouteStatus reqGetCalcPlanningRouteStatus, final Duration duration) {
        return requestGetCalcPlanningRouteStatus(reqGetCalcPlanningRouteStatus).thenCompose((Function<? super ResGetCalcPlanningRouteStatus, ? extends CompletionStage<U>>) new Function<ResGetCalcPlanningRouteStatus, CompletableFuture<ResGetCalcPlanningRouteStatus>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.247
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetCalcPlanningRouteStatus> apply(ResGetCalcPlanningRouteStatus resGetCalcPlanningRouteStatus) {
                return CommonUtil.delayAsync(resGetCalcPlanningRouteStatus, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetCalcPlanningRouteStatus> requestGetCalcPlanningRouteStatus() {
        return requestGetCalcPlanningRouteStatus(new ReqGetCalcPlanningRouteStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetCalcPlanningRouteStatus> requestGetCalcPlanningRouteStatus(Duration duration) {
        return requestGetCalcPlanningRouteStatus(new ReqGetCalcPlanningRouteStatus(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetCalcPlanningRouteStatus(ReqGetCalcPlanningRouteStatus reqGetCalcPlanningRouteStatus) {
        reqGetCalcPlanningRouteStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetCalcPlanningRouteStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCalcPlanningRouteStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.248
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetCalcPlanningRouteStatus(ReqGetCalcPlanningRouteStatus reqGetCalcPlanningRouteStatus, final Duration duration) {
        return fireGetCalcPlanningRouteStatus(reqGetCalcPlanningRouteStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.249
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetCalcPlanningRouteStatus() {
        return fireGetCalcPlanningRouteStatus(new ReqGetCalcPlanningRouteStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetCalcPlanningRouteStatus(final Duration duration) {
        return fireGetCalcPlanningRouteStatus(new ReqGetCalcPlanningRouteStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.250
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetCurrentViewInfoStatus> requestGetCurrentViewInfoStatus(ReqGetCurrentViewInfoStatus reqGetCurrentViewInfoStatus) {
        if (reqGetCurrentViewInfoStatus.getResponse() == null) {
            reqGetCurrentViewInfoStatus.setResponse(new ResGetCurrentViewInfoStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetCurrentViewInfoStatus.setComponent(str);
            reqGetCurrentViewInfoStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCurrentViewInfoStatus));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetCurrentViewInfoStatus> requestGetCurrentViewInfoStatus(ReqGetCurrentViewInfoStatus reqGetCurrentViewInfoStatus, final Duration duration) {
        return requestGetCurrentViewInfoStatus(reqGetCurrentViewInfoStatus).thenCompose((Function<? super ResGetCurrentViewInfoStatus, ? extends CompletionStage<U>>) new Function<ResGetCurrentViewInfoStatus, CompletableFuture<ResGetCurrentViewInfoStatus>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.251
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetCurrentViewInfoStatus> apply(ResGetCurrentViewInfoStatus resGetCurrentViewInfoStatus) {
                return CommonUtil.delayAsync(resGetCurrentViewInfoStatus, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetCurrentViewInfoStatus> requestGetCurrentViewInfoStatus() {
        return requestGetCurrentViewInfoStatus(new ReqGetCurrentViewInfoStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetCurrentViewInfoStatus> requestGetCurrentViewInfoStatus(Duration duration) {
        return requestGetCurrentViewInfoStatus(new ReqGetCurrentViewInfoStatus(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetCurrentViewInfoStatus(ReqGetCurrentViewInfoStatus reqGetCurrentViewInfoStatus) {
        reqGetCurrentViewInfoStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetCurrentViewInfoStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCurrentViewInfoStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.252
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetCurrentViewInfoStatus(ReqGetCurrentViewInfoStatus reqGetCurrentViewInfoStatus, final Duration duration) {
        return fireGetCurrentViewInfoStatus(reqGetCurrentViewInfoStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.253
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetCurrentViewInfoStatus() {
        return fireGetCurrentViewInfoStatus(new ReqGetCurrentViewInfoStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetCurrentViewInfoStatus(final Duration duration) {
        return fireGetCurrentViewInfoStatus(new ReqGetCurrentViewInfoStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.254
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetLaneInfoStatus> requestGetLaneInfoStatus(ReqGetLaneInfoStatus reqGetLaneInfoStatus) {
        if (reqGetLaneInfoStatus.getResponse() == null) {
            reqGetLaneInfoStatus.setResponse(new ResGetLaneInfoStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetLaneInfoStatus.setComponent(str);
            reqGetLaneInfoStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetLaneInfoStatus));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetLaneInfoStatus> requestGetLaneInfoStatus(ReqGetLaneInfoStatus reqGetLaneInfoStatus, final Duration duration) {
        return requestGetLaneInfoStatus(reqGetLaneInfoStatus).thenCompose((Function<? super ResGetLaneInfoStatus, ? extends CompletionStage<U>>) new Function<ResGetLaneInfoStatus, CompletableFuture<ResGetLaneInfoStatus>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.255
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetLaneInfoStatus> apply(ResGetLaneInfoStatus resGetLaneInfoStatus) {
                return CommonUtil.delayAsync(resGetLaneInfoStatus, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetLaneInfoStatus> requestGetLaneInfoStatus() {
        return requestGetLaneInfoStatus(new ReqGetLaneInfoStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetLaneInfoStatus> requestGetLaneInfoStatus(Duration duration) {
        return requestGetLaneInfoStatus(new ReqGetLaneInfoStatus(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetLaneInfoStatus(ReqGetLaneInfoStatus reqGetLaneInfoStatus) {
        reqGetLaneInfoStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetLaneInfoStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetLaneInfoStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.256
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetLaneInfoStatus(ReqGetLaneInfoStatus reqGetLaneInfoStatus, final Duration duration) {
        return fireGetLaneInfoStatus(reqGetLaneInfoStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.257
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetLaneInfoStatus() {
        return fireGetLaneInfoStatus(new ReqGetLaneInfoStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetLaneInfoStatus(final Duration duration) {
        return fireGetLaneInfoStatus(new ReqGetLaneInfoStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.258
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetSpeedLimitStatus> requestGetSpeedLimitStatus(ReqGetSpeedLimitStatus reqGetSpeedLimitStatus) {
        if (reqGetSpeedLimitStatus.getResponse() == null) {
            reqGetSpeedLimitStatus.setResponse(new ResGetSpeedLimitStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetSpeedLimitStatus.setComponent(str);
            reqGetSpeedLimitStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSpeedLimitStatus));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetSpeedLimitStatus> requestGetSpeedLimitStatus(ReqGetSpeedLimitStatus reqGetSpeedLimitStatus, final Duration duration) {
        return requestGetSpeedLimitStatus(reqGetSpeedLimitStatus).thenCompose((Function<? super ResGetSpeedLimitStatus, ? extends CompletionStage<U>>) new Function<ResGetSpeedLimitStatus, CompletableFuture<ResGetSpeedLimitStatus>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.259
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetSpeedLimitStatus> apply(ResGetSpeedLimitStatus resGetSpeedLimitStatus) {
                return CommonUtil.delayAsync(resGetSpeedLimitStatus, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetSpeedLimitStatus> requestGetSpeedLimitStatus() {
        return requestGetSpeedLimitStatus(new ReqGetSpeedLimitStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetSpeedLimitStatus> requestGetSpeedLimitStatus(Duration duration) {
        return requestGetSpeedLimitStatus(new ReqGetSpeedLimitStatus(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetSpeedLimitStatus(ReqGetSpeedLimitStatus reqGetSpeedLimitStatus) {
        reqGetSpeedLimitStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetSpeedLimitStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSpeedLimitStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.260
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetSpeedLimitStatus(ReqGetSpeedLimitStatus reqGetSpeedLimitStatus, final Duration duration) {
        return fireGetSpeedLimitStatus(reqGetSpeedLimitStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.261
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetSpeedLimitStatus() {
        return fireGetSpeedLimitStatus(new ReqGetSpeedLimitStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetSpeedLimitStatus(final Duration duration) {
        return fireGetSpeedLimitStatus(new ReqGetSpeedLimitStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.262
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNaviJumpToSystemPage> requestGetNaviJumpToSystemPage(ReqGetNaviJumpToSystemPage reqGetNaviJumpToSystemPage) {
        if (reqGetNaviJumpToSystemPage.getResponse() == null) {
            reqGetNaviJumpToSystemPage.setResponse(new ResGetNaviJumpToSystemPage());
        }
        String str = this.component;
        if (str != null) {
            reqGetNaviJumpToSystemPage.setComponent(str);
            reqGetNaviJumpToSystemPage.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviJumpToSystemPage));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNaviJumpToSystemPage> requestGetNaviJumpToSystemPage(ReqGetNaviJumpToSystemPage reqGetNaviJumpToSystemPage, final Duration duration) {
        return requestGetNaviJumpToSystemPage(reqGetNaviJumpToSystemPage).thenCompose((Function<? super ResGetNaviJumpToSystemPage, ? extends CompletionStage<U>>) new Function<ResGetNaviJumpToSystemPage, CompletableFuture<ResGetNaviJumpToSystemPage>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.263
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNaviJumpToSystemPage> apply(ResGetNaviJumpToSystemPage resGetNaviJumpToSystemPage) {
                return CommonUtil.delayAsync(resGetNaviJumpToSystemPage, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNaviJumpToSystemPage> requestGetNaviJumpToSystemPage() {
        return requestGetNaviJumpToSystemPage(new ReqGetNaviJumpToSystemPage());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetNaviJumpToSystemPage> requestGetNaviJumpToSystemPage(Duration duration) {
        return requestGetNaviJumpToSystemPage(new ReqGetNaviJumpToSystemPage(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNaviJumpToSystemPage(ReqGetNaviJumpToSystemPage reqGetNaviJumpToSystemPage) {
        reqGetNaviJumpToSystemPage.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNaviJumpToSystemPage.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviJumpToSystemPage)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.264
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNaviJumpToSystemPage(ReqGetNaviJumpToSystemPage reqGetNaviJumpToSystemPage, final Duration duration) {
        return fireGetNaviJumpToSystemPage(reqGetNaviJumpToSystemPage).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.265
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNaviJumpToSystemPage() {
        return fireGetNaviJumpToSystemPage(new ReqGetNaviJumpToSystemPage());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetNaviJumpToSystemPage(final Duration duration) {
        return fireGetNaviJumpToSystemPage(new ReqGetNaviJumpToSystemPage()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.266
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetCountryChangedStatus> requestGetCountryChangedStatus(ReqGetCountryChangedStatus reqGetCountryChangedStatus) {
        if (reqGetCountryChangedStatus.getResponse() == null) {
            reqGetCountryChangedStatus.setResponse(new ResGetCountryChangedStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetCountryChangedStatus.setComponent(str);
            reqGetCountryChangedStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCountryChangedStatus));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetCountryChangedStatus> requestGetCountryChangedStatus(ReqGetCountryChangedStatus reqGetCountryChangedStatus, final Duration duration) {
        return requestGetCountryChangedStatus(reqGetCountryChangedStatus).thenCompose((Function<? super ResGetCountryChangedStatus, ? extends CompletionStage<U>>) new Function<ResGetCountryChangedStatus, CompletableFuture<ResGetCountryChangedStatus>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.267
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetCountryChangedStatus> apply(ResGetCountryChangedStatus resGetCountryChangedStatus) {
                return CommonUtil.delayAsync(resGetCountryChangedStatus, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetCountryChangedStatus> requestGetCountryChangedStatus() {
        return requestGetCountryChangedStatus(new ReqGetCountryChangedStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetCountryChangedStatus> requestGetCountryChangedStatus(Duration duration) {
        return requestGetCountryChangedStatus(new ReqGetCountryChangedStatus(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetCountryChangedStatus(ReqGetCountryChangedStatus reqGetCountryChangedStatus) {
        reqGetCountryChangedStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetCountryChangedStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCountryChangedStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.268
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetCountryChangedStatus(ReqGetCountryChangedStatus reqGetCountryChangedStatus, final Duration duration) {
        return fireGetCountryChangedStatus(reqGetCountryChangedStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.269
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetCountryChangedStatus() {
        return fireGetCountryChangedStatus(new ReqGetCountryChangedStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetCountryChangedStatus(final Duration duration) {
        return fireGetCountryChangedStatus(new ReqGetCountryChangedStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.270
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetPostPageTransitionEvent> requestGetPostPageTransitionEvent(ReqGetPostPageTransitionEvent reqGetPostPageTransitionEvent) {
        if (reqGetPostPageTransitionEvent.getResponse() == null) {
            reqGetPostPageTransitionEvent.setResponse(new ResGetPostPageTransitionEvent());
        }
        String str = this.component;
        if (str != null) {
            reqGetPostPageTransitionEvent.setComponent(str);
            reqGetPostPageTransitionEvent.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetPostPageTransitionEvent));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetPostPageTransitionEvent> requestGetPostPageTransitionEvent(ReqGetPostPageTransitionEvent reqGetPostPageTransitionEvent, final Duration duration) {
        return requestGetPostPageTransitionEvent(reqGetPostPageTransitionEvent).thenCompose((Function<? super ResGetPostPageTransitionEvent, ? extends CompletionStage<U>>) new Function<ResGetPostPageTransitionEvent, CompletableFuture<ResGetPostPageTransitionEvent>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.271
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetPostPageTransitionEvent> apply(ResGetPostPageTransitionEvent resGetPostPageTransitionEvent) {
                return CommonUtil.delayAsync(resGetPostPageTransitionEvent, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetPostPageTransitionEvent> requestGetPostPageTransitionEvent() {
        return requestGetPostPageTransitionEvent(new ReqGetPostPageTransitionEvent());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetPostPageTransitionEvent> requestGetPostPageTransitionEvent(Duration duration) {
        return requestGetPostPageTransitionEvent(new ReqGetPostPageTransitionEvent(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetPostPageTransitionEvent(ReqGetPostPageTransitionEvent reqGetPostPageTransitionEvent) {
        reqGetPostPageTransitionEvent.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetPostPageTransitionEvent.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetPostPageTransitionEvent)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.272
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetPostPageTransitionEvent(ReqGetPostPageTransitionEvent reqGetPostPageTransitionEvent, final Duration duration) {
        return fireGetPostPageTransitionEvent(reqGetPostPageTransitionEvent).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.273
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetPostPageTransitionEvent() {
        return fireGetPostPageTransitionEvent(new ReqGetPostPageTransitionEvent());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetPostPageTransitionEvent(final Duration duration) {
        return fireGetPostPageTransitionEvent(new ReqGetPostPageTransitionEvent()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.274
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetPostUITouchEvent> requestGetPostUITouchEvent(ReqGetPostUITouchEvent reqGetPostUITouchEvent) {
        if (reqGetPostUITouchEvent.getResponse() == null) {
            reqGetPostUITouchEvent.setResponse(new ResGetPostUITouchEvent());
        }
        String str = this.component;
        if (str != null) {
            reqGetPostUITouchEvent.setComponent(str);
            reqGetPostUITouchEvent.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetPostUITouchEvent));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetPostUITouchEvent> requestGetPostUITouchEvent(ReqGetPostUITouchEvent reqGetPostUITouchEvent, final Duration duration) {
        return requestGetPostUITouchEvent(reqGetPostUITouchEvent).thenCompose((Function<? super ResGetPostUITouchEvent, ? extends CompletionStage<U>>) new Function<ResGetPostUITouchEvent, CompletableFuture<ResGetPostUITouchEvent>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.275
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetPostUITouchEvent> apply(ResGetPostUITouchEvent resGetPostUITouchEvent) {
                return CommonUtil.delayAsync(resGetPostUITouchEvent, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetPostUITouchEvent> requestGetPostUITouchEvent() {
        return requestGetPostUITouchEvent(new ReqGetPostUITouchEvent());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetPostUITouchEvent> requestGetPostUITouchEvent(Duration duration) {
        return requestGetPostUITouchEvent(new ReqGetPostUITouchEvent(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetPostUITouchEvent(ReqGetPostUITouchEvent reqGetPostUITouchEvent) {
        reqGetPostUITouchEvent.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetPostUITouchEvent.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetPostUITouchEvent)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.276
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetPostUITouchEvent(ReqGetPostUITouchEvent reqGetPostUITouchEvent, final Duration duration) {
        return fireGetPostUITouchEvent(reqGetPostUITouchEvent).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.277
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetPostUITouchEvent() {
        return fireGetPostUITouchEvent(new ReqGetPostUITouchEvent());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetPostUITouchEvent(final Duration duration) {
        return fireGetPostUITouchEvent(new ReqGetPostUITouchEvent()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.278
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetPostServiceEvent> requestGetPostServiceEvent(ReqGetPostServiceEvent reqGetPostServiceEvent) {
        if (reqGetPostServiceEvent.getResponse() == null) {
            reqGetPostServiceEvent.setResponse(new ResGetPostServiceEvent());
        }
        String str = this.component;
        if (str != null) {
            reqGetPostServiceEvent.setComponent(str);
            reqGetPostServiceEvent.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetPostServiceEvent));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetPostServiceEvent> requestGetPostServiceEvent(ReqGetPostServiceEvent reqGetPostServiceEvent, final Duration duration) {
        return requestGetPostServiceEvent(reqGetPostServiceEvent).thenCompose((Function<? super ResGetPostServiceEvent, ? extends CompletionStage<U>>) new Function<ResGetPostServiceEvent, CompletableFuture<ResGetPostServiceEvent>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.279
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetPostServiceEvent> apply(ResGetPostServiceEvent resGetPostServiceEvent) {
                return CommonUtil.delayAsync(resGetPostServiceEvent, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetPostServiceEvent> requestGetPostServiceEvent() {
        return requestGetPostServiceEvent(new ReqGetPostServiceEvent());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetPostServiceEvent> requestGetPostServiceEvent(Duration duration) {
        return requestGetPostServiceEvent(new ReqGetPostServiceEvent(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetPostServiceEvent(ReqGetPostServiceEvent reqGetPostServiceEvent) {
        reqGetPostServiceEvent.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetPostServiceEvent.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetPostServiceEvent)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.280
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetPostServiceEvent(ReqGetPostServiceEvent reqGetPostServiceEvent, final Duration duration) {
        return fireGetPostServiceEvent(reqGetPostServiceEvent).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.281
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetPostServiceEvent() {
        return fireGetPostServiceEvent(new ReqGetPostServiceEvent());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetPostServiceEvent(final Duration duration) {
        return fireGetPostServiceEvent(new ReqGetPostServiceEvent()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.282
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetPostCustomEvent> requestGetPostCustomEvent(ReqGetPostCustomEvent reqGetPostCustomEvent) {
        if (reqGetPostCustomEvent.getResponse() == null) {
            reqGetPostCustomEvent.setResponse(new ResGetPostCustomEvent());
        }
        String str = this.component;
        if (str != null) {
            reqGetPostCustomEvent.setComponent(str);
            reqGetPostCustomEvent.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetPostCustomEvent));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetPostCustomEvent> requestGetPostCustomEvent(ReqGetPostCustomEvent reqGetPostCustomEvent, final Duration duration) {
        return requestGetPostCustomEvent(reqGetPostCustomEvent).thenCompose((Function<? super ResGetPostCustomEvent, ? extends CompletionStage<U>>) new Function<ResGetPostCustomEvent, CompletableFuture<ResGetPostCustomEvent>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.283
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetPostCustomEvent> apply(ResGetPostCustomEvent resGetPostCustomEvent) {
                return CommonUtil.delayAsync(resGetPostCustomEvent, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetPostCustomEvent> requestGetPostCustomEvent() {
        return requestGetPostCustomEvent(new ReqGetPostCustomEvent());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetPostCustomEvent> requestGetPostCustomEvent(Duration duration) {
        return requestGetPostCustomEvent(new ReqGetPostCustomEvent(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetPostCustomEvent(ReqGetPostCustomEvent reqGetPostCustomEvent) {
        reqGetPostCustomEvent.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetPostCustomEvent.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetPostCustomEvent)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.284
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetPostCustomEvent(ReqGetPostCustomEvent reqGetPostCustomEvent, final Duration duration) {
        return fireGetPostCustomEvent(reqGetPostCustomEvent).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.285
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetPostCustomEvent() {
        return fireGetPostCustomEvent(new ReqGetPostCustomEvent());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetPostCustomEvent(final Duration duration) {
        return fireGetPostCustomEvent(new ReqGetPostCustomEvent()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.286
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetDeadReckoningExtendStatus> requestGetDeadReckoningExtendStatus(ReqGetDeadReckoningExtendStatus reqGetDeadReckoningExtendStatus) {
        if (reqGetDeadReckoningExtendStatus.getResponse() == null) {
            reqGetDeadReckoningExtendStatus.setResponse(new ResGetDeadReckoningExtendStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetDeadReckoningExtendStatus.setComponent(str);
            reqGetDeadReckoningExtendStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDeadReckoningExtendStatus));
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetDeadReckoningExtendStatus> requestGetDeadReckoningExtendStatus(ReqGetDeadReckoningExtendStatus reqGetDeadReckoningExtendStatus, final Duration duration) {
        return requestGetDeadReckoningExtendStatus(reqGetDeadReckoningExtendStatus).thenCompose((Function<? super ResGetDeadReckoningExtendStatus, ? extends CompletionStage<U>>) new Function<ResGetDeadReckoningExtendStatus, CompletableFuture<ResGetDeadReckoningExtendStatus>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.287
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetDeadReckoningExtendStatus> apply(ResGetDeadReckoningExtendStatus resGetDeadReckoningExtendStatus) {
                return CommonUtil.delayAsync(resGetDeadReckoningExtendStatus, duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetDeadReckoningExtendStatus> requestGetDeadReckoningExtendStatus() {
        return requestGetDeadReckoningExtendStatus(new ReqGetDeadReckoningExtendStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<ResGetDeadReckoningExtendStatus> requestGetDeadReckoningExtendStatus(Duration duration) {
        return requestGetDeadReckoningExtendStatus(new ReqGetDeadReckoningExtendStatus(), duration);
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetDeadReckoningExtendStatus(ReqGetDeadReckoningExtendStatus reqGetDeadReckoningExtendStatus) {
        reqGetDeadReckoningExtendStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetDeadReckoningExtendStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDeadReckoningExtendStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.288
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return GreatWallNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetDeadReckoningExtendStatus(ReqGetDeadReckoningExtendStatus reqGetDeadReckoningExtendStatus, final Duration duration) {
        return fireGetDeadReckoningExtendStatus(reqGetDeadReckoningExtendStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.289
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetDeadReckoningExtendStatus() {
        return fireGetDeadReckoningExtendStatus(new ReqGetDeadReckoningExtendStatus());
    }

    @Override // api.requests.com.neusoft.greatwall.greatwallnavapi.IGreatWallNavApiRequests
    public CompletableFuture<Void> fireGetDeadReckoningExtendStatus(final Duration duration) {
        return fireGetDeadReckoningExtendStatus(new ReqGetDeadReckoningExtendStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests.290
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, GreatWallNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }
}
