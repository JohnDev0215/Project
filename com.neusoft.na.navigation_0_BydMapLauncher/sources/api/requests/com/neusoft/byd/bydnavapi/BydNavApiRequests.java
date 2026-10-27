package api.requests.com.neusoft.byd.bydnavapi;

import api.requests.com.neusoft.byd.bydnavapi.ReqClusterViewModeChanged;
import api.requests.com.neusoft.byd.bydnavapi.ReqOnAuthInfo;
import api.requests.com.neusoft.byd.bydnavapi.ReqOnDelPrivacyPolicyInfo;
import api.requests.com.neusoft.byd.bydnavapi.ReqOnGPSInfo;
import api.requests.com.neusoft.byd.bydnavapi.ReqOnPrivacyPolicySyncStatus;
import api.requests.com.neusoft.byd.bydnavapi.ReqOnRevokePrivacyPolicy;
import api.requests.com.neusoft.byd.bydnavapi.ReqOnVerifyInfo;
import api.requests.com.neusoft.byd.bydnavapi.ReqPowerSavingModeChanged;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqAddViaPosition;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqAppendVia;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqGuideStart;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqModifyDestination;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqNaviAlongTheWaySearch;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqNaviRouteFullView;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqNaviSetRouteOption;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqOpenPoiDetail;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqPOISearchByCategories;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqPOISearchByKeyword;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqRoutePlanning;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqSetAddress;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqSmartNaviStart;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqSmartRoutePlanning;
import api.requests.com.neusoft.byd.bydnavapi.ReqReqSwitchFavorite;
import api.requests.com.neusoft.byd.bydnavapi.ReqRequestGoHomeOrCompany;
import api.requests.com.neusoft.byd.bydnavapi.ReqRequestShowHomeOrCompanySetPage;
import api.requests.com.neusoft.byd.bydnavapi.ReqSetClusterMapFocus;
import api.requests.com.neusoft.byd.bydnavapi.ReqSetDesktopMode;
import api.requests.com.neusoft.byd.bydnavapi.ReqSetMobileEquipmentID;
import api.requests.com.neusoft.byd.bydnavapi.ReqSetPlatformVersion;
import api.requests.com.neusoft.byd.bydnavapi.ReqSetPrivacyPolicyFullPath;
import api.requests.com.neusoft.byd.bydnavapi.ReqShowPoiIconsFromVR;
import api.responses.com.neusoft.byd.bydnavapi.ResCheckDestinationHistoryEmpty;
import api.responses.com.neusoft.byd.bydnavapi.ResCheckFavoriteEmpty;
import api.responses.com.neusoft.byd.bydnavapi.ResCheckFavoriteFull;
import api.responses.com.neusoft.byd.bydnavapi.ResGetAuthInfo;
import api.responses.com.neusoft.byd.bydnavapi.ResGetClusterViewMode;
import api.responses.com.neusoft.byd.bydnavapi.ResGetCompany;
import api.responses.com.neusoft.byd.bydnavapi.ResGetCurrentPlace;
import api.responses.com.neusoft.byd.bydnavapi.ResGetDelPrivacyPolicyInfo;
import api.responses.com.neusoft.byd.bydnavapi.ResGetFavoriteStatus;
import api.responses.com.neusoft.byd.bydnavapi.ResGetGuidanceInfoUpdate;
import api.responses.com.neusoft.byd.bydnavapi.ResGetGuidanceStatus;
import api.responses.com.neusoft.byd.bydnavapi.ResGetHome;
import api.responses.com.neusoft.byd.bydnavapi.ResGetIsNaviHMIFullScreenPage;
import api.responses.com.neusoft.byd.bydnavapi.ResGetNaviMaxViaCount;
import api.responses.com.neusoft.byd.bydnavapi.ResGetPoiDetailStatus;
import api.responses.com.neusoft.byd.bydnavapi.ResGetReportLocationInfo;
import api.responses.com.neusoft.byd.bydnavapi.ResGetReqGPSInfo;
import api.responses.com.neusoft.byd.bydnavapi.ResGetRevokePrivacyPolicy;
import api.responses.com.neusoft.byd.bydnavapi.ResGetSupportedRouteOptions;
import api.responses.com.neusoft.byd.bydnavapi.ResGetSwitchDesktopMode;
import api.responses.com.neusoft.byd.bydnavapi.ResGetUnfinishedRoute;
import api.responses.com.neusoft.byd.bydnavapi.ResGetUpdatePrivacyPolicySyncStatus;
import api.responses.com.neusoft.byd.bydnavapi.ResGetVerifyInfo;
import api.responses.com.neusoft.byd.bydnavapi.ResHidePoiIconsFromVR;
import api.responses.com.neusoft.byd.bydnavapi.ResReqAddViaPosition;
import api.responses.com.neusoft.byd.bydnavapi.ResReqAppendVia;
import api.responses.com.neusoft.byd.bydnavapi.ResReqClosePoiDetail;
import api.responses.com.neusoft.byd.bydnavapi.ResReqCloseRouteOption;
import api.responses.com.neusoft.byd.bydnavapi.ResReqGuideStart;
import api.responses.com.neusoft.byd.bydnavapi.ResReqModifyDestination;
import api.responses.com.neusoft.byd.bydnavapi.ResReqNaviAddFavorite;
import api.responses.com.neusoft.byd.bydnavapi.ResReqNaviAlongTheWaySearch;
import api.responses.com.neusoft.byd.bydnavapi.ResReqNaviGetRouteOption;
import api.responses.com.neusoft.byd.bydnavapi.ResReqNaviInfo;
import api.responses.com.neusoft.byd.bydnavapi.ResReqNaviNextViaTimeDistance;
import api.responses.com.neusoft.byd.bydnavapi.ResReqNaviRouteFullView;
import api.responses.com.neusoft.byd.bydnavapi.ResReqNaviSetRouteOption;
import api.responses.com.neusoft.byd.bydnavapi.ResReqNaviViaPosition;
import api.responses.com.neusoft.byd.bydnavapi.ResReqOpenHistoryDestination;
import api.responses.com.neusoft.byd.bydnavapi.ResReqOpenPoiDetail;
import api.responses.com.neusoft.byd.bydnavapi.ResReqOpenRouteOption;
import api.responses.com.neusoft.byd.bydnavapi.ResReqPOISearchByCategories;
import api.responses.com.neusoft.byd.bydnavapi.ResReqPOISearchByKeyword;
import api.responses.com.neusoft.byd.bydnavapi.ResReqRoutePlanning;
import api.responses.com.neusoft.byd.bydnavapi.ResReqSetAddress;
import api.responses.com.neusoft.byd.bydnavapi.ResReqSmartNaviStart;
import api.responses.com.neusoft.byd.bydnavapi.ResReqSmartRoutePlanning;
import api.responses.com.neusoft.byd.bydnavapi.ResReqSwitchFavorite;
import api.responses.com.neusoft.byd.bydnavapi.ResRequestGoHomeOrCompany;
import api.responses.com.neusoft.byd.bydnavapi.ResRequestShowHomeOrCompanySetPage;
import api.responses.com.neusoft.byd.bydnavapi.ResRequestShowSearchAlongRoutePage;
import api.responses.com.neusoft.byd.bydnavapi.ResRequestShowSearchResultPage;
import api.responses.com.neusoft.byd.bydnavapi.ResResumeNavigation;
import api.responses.com.neusoft.byd.bydnavapi.ResShowPoiIconsFromVR;
import com.neusoft.de.nts.OneCoreTest.communication.Communication;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.utils.CommonUtil;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java9.util.concurrent.CompletableFuture;
import java9.util.concurrent.CompletionStage;
import java9.util.function.Function;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes.dex */
public class BydNavApiRequests implements IBydNavApiRequests {
    private final String component;
    private final ICommunicationContextUtil<?, ?, ?> contextUtil;
    private static final Logger logger = Logger.getLogger((Class<?>) BydNavApiRequests.class);
    private static final CompletableFuture<Void> completedVoidFuture = CompletableFuture.completedFuture(null);

    public BydNavApiRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = null;
    }

    public BydNavApiRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil, String str) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = str;
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqOpenHistoryDestination> requestReqOpenHistoryDestination(ReqReqOpenHistoryDestination reqReqOpenHistoryDestination) {
        if (reqReqOpenHistoryDestination.getResponse() == null) {
            reqReqOpenHistoryDestination.setResponse(new ResReqOpenHistoryDestination());
        }
        String str = this.component;
        if (str != null) {
            reqReqOpenHistoryDestination.setComponent(str);
            reqReqOpenHistoryDestination.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqOpenHistoryDestination));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqOpenHistoryDestination> requestReqOpenHistoryDestination(ReqReqOpenHistoryDestination reqReqOpenHistoryDestination, final Duration duration) {
        return requestReqOpenHistoryDestination(reqReqOpenHistoryDestination).thenCompose((Function<? super ResReqOpenHistoryDestination, ? extends CompletionStage<U>>) new Function<ResReqOpenHistoryDestination, CompletableFuture<ResReqOpenHistoryDestination>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.1
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqOpenHistoryDestination> apply(ResReqOpenHistoryDestination resReqOpenHistoryDestination) {
                return CommonUtil.delayAsync(resReqOpenHistoryDestination, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqOpenHistoryDestination> requestReqOpenHistoryDestination() {
        return requestReqOpenHistoryDestination(new ReqReqOpenHistoryDestination());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqOpenHistoryDestination> requestReqOpenHistoryDestination(Duration duration) {
        return requestReqOpenHistoryDestination(new ReqReqOpenHistoryDestination(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqOpenHistoryDestination(ReqReqOpenHistoryDestination reqReqOpenHistoryDestination) {
        reqReqOpenHistoryDestination.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqOpenHistoryDestination.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqOpenHistoryDestination)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.2
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqOpenHistoryDestination(ReqReqOpenHistoryDestination reqReqOpenHistoryDestination, final Duration duration) {
        return fireReqOpenHistoryDestination(reqReqOpenHistoryDestination).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.3
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqOpenHistoryDestination() {
        return fireReqOpenHistoryDestination(new ReqReqOpenHistoryDestination());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqOpenHistoryDestination(final Duration duration) {
        return fireReqOpenHistoryDestination(new ReqReqOpenHistoryDestination()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.4
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetHome> requestGetHome(ReqGetHome reqGetHome) {
        if (reqGetHome.getResponse() == null) {
            reqGetHome.setResponse(new ResGetHome());
        }
        String str = this.component;
        if (str != null) {
            reqGetHome.setComponent(str);
            reqGetHome.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetHome));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetHome> requestGetHome(ReqGetHome reqGetHome, final Duration duration) {
        return requestGetHome(reqGetHome).thenCompose((Function<? super ResGetHome, ? extends CompletionStage<U>>) new Function<ResGetHome, CompletableFuture<ResGetHome>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.5
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetHome> apply(ResGetHome resGetHome) {
                return CommonUtil.delayAsync(resGetHome, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetHome> requestGetHome() {
        return requestGetHome(new ReqGetHome());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetHome> requestGetHome(Duration duration) {
        return requestGetHome(new ReqGetHome(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetHome(ReqGetHome reqGetHome) {
        reqGetHome.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetHome.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetHome)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.6
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetHome(ReqGetHome reqGetHome, final Duration duration) {
        return fireGetHome(reqGetHome).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.7
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetHome() {
        return fireGetHome(new ReqGetHome());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetHome(final Duration duration) {
        return fireGetHome(new ReqGetHome()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.8
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetCompany> requestGetCompany(ReqGetCompany reqGetCompany) {
        if (reqGetCompany.getResponse() == null) {
            reqGetCompany.setResponse(new ResGetCompany());
        }
        String str = this.component;
        if (str != null) {
            reqGetCompany.setComponent(str);
            reqGetCompany.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCompany));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetCompany> requestGetCompany(ReqGetCompany reqGetCompany, final Duration duration) {
        return requestGetCompany(reqGetCompany).thenCompose((Function<? super ResGetCompany, ? extends CompletionStage<U>>) new Function<ResGetCompany, CompletableFuture<ResGetCompany>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.9
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetCompany> apply(ResGetCompany resGetCompany) {
                return CommonUtil.delayAsync(resGetCompany, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetCompany> requestGetCompany() {
        return requestGetCompany(new ReqGetCompany());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetCompany> requestGetCompany(Duration duration) {
        return requestGetCompany(new ReqGetCompany(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetCompany(ReqGetCompany reqGetCompany) {
        reqGetCompany.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetCompany.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCompany)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.10
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetCompany(ReqGetCompany reqGetCompany, final Duration duration) {
        return fireGetCompany(reqGetCompany).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.11
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetCompany() {
        return fireGetCompany(new ReqGetCompany());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetCompany(final Duration duration) {
        return fireGetCompany(new ReqGetCompany()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.12
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetUnfinishedRoute> requestGetUnfinishedRoute(ReqGetUnfinishedRoute reqGetUnfinishedRoute) {
        if (reqGetUnfinishedRoute.getResponse() == null) {
            reqGetUnfinishedRoute.setResponse(new ResGetUnfinishedRoute());
        }
        String str = this.component;
        if (str != null) {
            reqGetUnfinishedRoute.setComponent(str);
            reqGetUnfinishedRoute.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetUnfinishedRoute));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetUnfinishedRoute> requestGetUnfinishedRoute(ReqGetUnfinishedRoute reqGetUnfinishedRoute, final Duration duration) {
        return requestGetUnfinishedRoute(reqGetUnfinishedRoute).thenCompose((Function<? super ResGetUnfinishedRoute, ? extends CompletionStage<U>>) new Function<ResGetUnfinishedRoute, CompletableFuture<ResGetUnfinishedRoute>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.13
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetUnfinishedRoute> apply(ResGetUnfinishedRoute resGetUnfinishedRoute) {
                return CommonUtil.delayAsync(resGetUnfinishedRoute, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetUnfinishedRoute> requestGetUnfinishedRoute() {
        return requestGetUnfinishedRoute(new ReqGetUnfinishedRoute());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetUnfinishedRoute> requestGetUnfinishedRoute(Duration duration) {
        return requestGetUnfinishedRoute(new ReqGetUnfinishedRoute(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetUnfinishedRoute(ReqGetUnfinishedRoute reqGetUnfinishedRoute) {
        reqGetUnfinishedRoute.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetUnfinishedRoute.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetUnfinishedRoute)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.14
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetUnfinishedRoute(ReqGetUnfinishedRoute reqGetUnfinishedRoute, final Duration duration) {
        return fireGetUnfinishedRoute(reqGetUnfinishedRoute).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.15
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetUnfinishedRoute() {
        return fireGetUnfinishedRoute(new ReqGetUnfinishedRoute());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetUnfinishedRoute(final Duration duration) {
        return fireGetUnfinishedRoute(new ReqGetUnfinishedRoute()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.16
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviNextViaTimeDistance> requestReqNaviNextViaTimeDistance(ReqReqNaviNextViaTimeDistance reqReqNaviNextViaTimeDistance) {
        if (reqReqNaviNextViaTimeDistance.getResponse() == null) {
            reqReqNaviNextViaTimeDistance.setResponse(new ResReqNaviNextViaTimeDistance());
        }
        String str = this.component;
        if (str != null) {
            reqReqNaviNextViaTimeDistance.setComponent(str);
            reqReqNaviNextViaTimeDistance.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviNextViaTimeDistance));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviNextViaTimeDistance> requestReqNaviNextViaTimeDistance(ReqReqNaviNextViaTimeDistance reqReqNaviNextViaTimeDistance, final Duration duration) {
        return requestReqNaviNextViaTimeDistance(reqReqNaviNextViaTimeDistance).thenCompose((Function<? super ResReqNaviNextViaTimeDistance, ? extends CompletionStage<U>>) new Function<ResReqNaviNextViaTimeDistance, CompletableFuture<ResReqNaviNextViaTimeDistance>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.17
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviNextViaTimeDistance> apply(ResReqNaviNextViaTimeDistance resReqNaviNextViaTimeDistance) {
                return CommonUtil.delayAsync(resReqNaviNextViaTimeDistance, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviNextViaTimeDistance> requestReqNaviNextViaTimeDistance() {
        return requestReqNaviNextViaTimeDistance(new ReqReqNaviNextViaTimeDistance());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviNextViaTimeDistance> requestReqNaviNextViaTimeDistance(Duration duration) {
        return requestReqNaviNextViaTimeDistance(new ReqReqNaviNextViaTimeDistance(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviNextViaTimeDistance(ReqReqNaviNextViaTimeDistance reqReqNaviNextViaTimeDistance) {
        reqReqNaviNextViaTimeDistance.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviNextViaTimeDistance.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviNextViaTimeDistance)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.18
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviNextViaTimeDistance(ReqReqNaviNextViaTimeDistance reqReqNaviNextViaTimeDistance, final Duration duration) {
        return fireReqNaviNextViaTimeDistance(reqReqNaviNextViaTimeDistance).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.19
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviNextViaTimeDistance() {
        return fireReqNaviNextViaTimeDistance(new ReqReqNaviNextViaTimeDistance());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviNextViaTimeDistance(final Duration duration) {
        return fireReqNaviNextViaTimeDistance(new ReqReqNaviNextViaTimeDistance()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.20
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResResumeNavigation> requestResumeNavigation(ReqResumeNavigation reqResumeNavigation) {
        if (reqResumeNavigation.getResponse() == null) {
            reqResumeNavigation.setResponse(new ResResumeNavigation());
        }
        String str = this.component;
        if (str != null) {
            reqResumeNavigation.setComponent(str);
            reqResumeNavigation.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqResumeNavigation));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResResumeNavigation> requestResumeNavigation(ReqResumeNavigation reqResumeNavigation, final Duration duration) {
        return requestResumeNavigation(reqResumeNavigation).thenCompose((Function<? super ResResumeNavigation, ? extends CompletionStage<U>>) new Function<ResResumeNavigation, CompletableFuture<ResResumeNavigation>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.21
            @Override // java9.util.function.Function
            public CompletableFuture<ResResumeNavigation> apply(ResResumeNavigation resResumeNavigation) {
                return CommonUtil.delayAsync(resResumeNavigation, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResResumeNavigation> requestResumeNavigation() {
        return requestResumeNavigation(new ReqResumeNavigation());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResResumeNavigation> requestResumeNavigation(Duration duration) {
        return requestResumeNavigation(new ReqResumeNavigation(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireResumeNavigation(ReqResumeNavigation reqResumeNavigation) {
        reqResumeNavigation.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqResumeNavigation.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqResumeNavigation)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.22
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireResumeNavigation(ReqResumeNavigation reqResumeNavigation, final Duration duration) {
        return fireResumeNavigation(reqResumeNavigation).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.23
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireResumeNavigation() {
        return fireResumeNavigation(new ReqResumeNavigation());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireResumeNavigation(final Duration duration) {
        return fireResumeNavigation(new ReqResumeNavigation()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.24
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqSetAddress> requestReqSetAddress(ReqReqSetAddress reqReqSetAddress) {
        if (reqReqSetAddress.getResponse() == null) {
            reqReqSetAddress.setResponse(new ResReqSetAddress());
        }
        String str = this.component;
        if (str != null) {
            reqReqSetAddress.setComponent(str);
            reqReqSetAddress.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqSetAddress));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqSetAddress> requestReqSetAddress(ReqReqSetAddress reqReqSetAddress, final Duration duration) {
        return requestReqSetAddress(reqReqSetAddress).thenCompose((Function<? super ResReqSetAddress, ? extends CompletionStage<U>>) new Function<ResReqSetAddress, CompletableFuture<ResReqSetAddress>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.25
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqSetAddress> apply(ResReqSetAddress resReqSetAddress) {
                return CommonUtil.delayAsync(resReqSetAddress, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqSetAddress.Arguments> requestReqSetAddress(ReqReqSetAddress.Arguments arguments) {
        ReqReqSetAddress reqReqSetAddress = new ReqReqSetAddress();
        reqReqSetAddress.setResponse(new ResReqSetAddress());
        reqReqSetAddress.setArguments(arguments);
        return requestReqSetAddress(reqReqSetAddress).thenApply((Function<? super ResReqSetAddress, ? extends U>) new Function<ResReqSetAddress, ResReqSetAddress.Arguments>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.26
            @Override // java9.util.function.Function
            public ResReqSetAddress.Arguments apply(ResReqSetAddress resReqSetAddress) {
                return resReqSetAddress.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqSetAddress.Arguments> requestReqSetAddress(ReqReqSetAddress.Arguments arguments, final Duration duration) {
        return requestReqSetAddress(arguments).thenCompose((Function<? super ResReqSetAddress.Arguments, ? extends CompletionStage<U>>) new Function<ResReqSetAddress.Arguments, CompletableFuture<ResReqSetAddress.Arguments>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.27
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqSetAddress.Arguments> apply(ResReqSetAddress.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqSetAddress(ReqReqSetAddress reqReqSetAddress) {
        reqReqSetAddress.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqSetAddress.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqSetAddress)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.28
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqSetAddress(ReqReqSetAddress reqReqSetAddress, final Duration duration) {
        return fireReqSetAddress(reqReqSetAddress).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.29
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqSetAddress(ReqReqSetAddress.Arguments arguments) {
        ReqReqSetAddress reqReqSetAddress = new ReqReqSetAddress();
        reqReqSetAddress.setArguments(arguments);
        reqReqSetAddress.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqSetAddress.setComponent(str);
        }
        return fireReqSetAddress(reqReqSetAddress);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqSetAddress(ReqReqSetAddress.Arguments arguments, final Duration duration) {
        return fireReqSetAddress(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.30
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqSwitchFavorite> requestReqSwitchFavorite(ReqReqSwitchFavorite reqReqSwitchFavorite) {
        if (reqReqSwitchFavorite.getResponse() == null) {
            reqReqSwitchFavorite.setResponse(new ResReqSwitchFavorite());
        }
        String str = this.component;
        if (str != null) {
            reqReqSwitchFavorite.setComponent(str);
            reqReqSwitchFavorite.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqSwitchFavorite));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqSwitchFavorite> requestReqSwitchFavorite(ReqReqSwitchFavorite reqReqSwitchFavorite, final Duration duration) {
        return requestReqSwitchFavorite(reqReqSwitchFavorite).thenCompose((Function<? super ResReqSwitchFavorite, ? extends CompletionStage<U>>) new Function<ResReqSwitchFavorite, CompletableFuture<ResReqSwitchFavorite>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.31
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqSwitchFavorite> apply(ResReqSwitchFavorite resReqSwitchFavorite) {
                return CommonUtil.delayAsync(resReqSwitchFavorite, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqSwitchFavorite.Arguments> requestReqSwitchFavorite(ReqReqSwitchFavorite.Arguments arguments) {
        ReqReqSwitchFavorite reqReqSwitchFavorite = new ReqReqSwitchFavorite();
        reqReqSwitchFavorite.setResponse(new ResReqSwitchFavorite());
        reqReqSwitchFavorite.setArguments(arguments);
        return requestReqSwitchFavorite(reqReqSwitchFavorite).thenApply((Function<? super ResReqSwitchFavorite, ? extends U>) new Function<ResReqSwitchFavorite, ResReqSwitchFavorite.Arguments>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.32
            @Override // java9.util.function.Function
            public ResReqSwitchFavorite.Arguments apply(ResReqSwitchFavorite resReqSwitchFavorite) {
                return resReqSwitchFavorite.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqSwitchFavorite.Arguments> requestReqSwitchFavorite(ReqReqSwitchFavorite.Arguments arguments, final Duration duration) {
        return requestReqSwitchFavorite(arguments).thenCompose((Function<? super ResReqSwitchFavorite.Arguments, ? extends CompletionStage<U>>) new Function<ResReqSwitchFavorite.Arguments, CompletableFuture<ResReqSwitchFavorite.Arguments>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.33
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqSwitchFavorite.Arguments> apply(ResReqSwitchFavorite.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqSwitchFavorite(ReqReqSwitchFavorite reqReqSwitchFavorite) {
        reqReqSwitchFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqSwitchFavorite.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqSwitchFavorite)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.34
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqSwitchFavorite(ReqReqSwitchFavorite reqReqSwitchFavorite, final Duration duration) {
        return fireReqSwitchFavorite(reqReqSwitchFavorite).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.35
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqSwitchFavorite(ReqReqSwitchFavorite.Arguments arguments) {
        ReqReqSwitchFavorite reqReqSwitchFavorite = new ReqReqSwitchFavorite();
        reqReqSwitchFavorite.setArguments(arguments);
        reqReqSwitchFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqSwitchFavorite.setComponent(str);
        }
        return fireReqSwitchFavorite(reqReqSwitchFavorite);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqSwitchFavorite(ReqReqSwitchFavorite.Arguments arguments, final Duration duration) {
        return fireReqSwitchFavorite(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.36
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetFavoriteStatus> requestGetFavoriteStatus(ReqGetFavoriteStatus reqGetFavoriteStatus) {
        if (reqGetFavoriteStatus.getResponse() == null) {
            reqGetFavoriteStatus.setResponse(new ResGetFavoriteStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetFavoriteStatus.setComponent(str);
            reqGetFavoriteStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetFavoriteStatus));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetFavoriteStatus> requestGetFavoriteStatus(ReqGetFavoriteStatus reqGetFavoriteStatus, final Duration duration) {
        return requestGetFavoriteStatus(reqGetFavoriteStatus).thenCompose((Function<? super ResGetFavoriteStatus, ? extends CompletionStage<U>>) new Function<ResGetFavoriteStatus, CompletableFuture<ResGetFavoriteStatus>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.37
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetFavoriteStatus> apply(ResGetFavoriteStatus resGetFavoriteStatus) {
                return CommonUtil.delayAsync(resGetFavoriteStatus, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetFavoriteStatus> requestGetFavoriteStatus() {
        return requestGetFavoriteStatus(new ReqGetFavoriteStatus());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetFavoriteStatus> requestGetFavoriteStatus(Duration duration) {
        return requestGetFavoriteStatus(new ReqGetFavoriteStatus(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetFavoriteStatus(ReqGetFavoriteStatus reqGetFavoriteStatus) {
        reqGetFavoriteStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetFavoriteStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetFavoriteStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.38
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetFavoriteStatus(ReqGetFavoriteStatus reqGetFavoriteStatus, final Duration duration) {
        return fireGetFavoriteStatus(reqGetFavoriteStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.39
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetFavoriteStatus() {
        return fireGetFavoriteStatus(new ReqGetFavoriteStatus());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetFavoriteStatus(final Duration duration) {
        return fireGetFavoriteStatus(new ReqGetFavoriteStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.40
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviAddFavorite> requestReqNaviAddFavorite(ReqReqNaviAddFavorite reqReqNaviAddFavorite) {
        if (reqReqNaviAddFavorite.getResponse() == null) {
            reqReqNaviAddFavorite.setResponse(new ResReqNaviAddFavorite());
        }
        String str = this.component;
        if (str != null) {
            reqReqNaviAddFavorite.setComponent(str);
            reqReqNaviAddFavorite.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviAddFavorite));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviAddFavorite> requestReqNaviAddFavorite(ReqReqNaviAddFavorite reqReqNaviAddFavorite, final Duration duration) {
        return requestReqNaviAddFavorite(reqReqNaviAddFavorite).thenCompose((Function<? super ResReqNaviAddFavorite, ? extends CompletionStage<U>>) new Function<ResReqNaviAddFavorite, CompletableFuture<ResReqNaviAddFavorite>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.41
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviAddFavorite> apply(ResReqNaviAddFavorite resReqNaviAddFavorite) {
                return CommonUtil.delayAsync(resReqNaviAddFavorite, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviAddFavorite> requestReqNaviAddFavorite() {
        return requestReqNaviAddFavorite(new ReqReqNaviAddFavorite());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviAddFavorite> requestReqNaviAddFavorite(Duration duration) {
        return requestReqNaviAddFavorite(new ReqReqNaviAddFavorite(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviAddFavorite(ReqReqNaviAddFavorite reqReqNaviAddFavorite) {
        reqReqNaviAddFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviAddFavorite.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviAddFavorite)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.42
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviAddFavorite(ReqReqNaviAddFavorite reqReqNaviAddFavorite, final Duration duration) {
        return fireReqNaviAddFavorite(reqReqNaviAddFavorite).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.43
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviAddFavorite() {
        return fireReqNaviAddFavorite(new ReqReqNaviAddFavorite());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviAddFavorite(final Duration duration) {
        return fireReqNaviAddFavorite(new ReqReqNaviAddFavorite()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.44
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResCheckFavoriteEmpty> requestCheckFavoriteEmpty(ReqCheckFavoriteEmpty reqCheckFavoriteEmpty) {
        if (reqCheckFavoriteEmpty.getResponse() == null) {
            reqCheckFavoriteEmpty.setResponse(new ResCheckFavoriteEmpty());
        }
        String str = this.component;
        if (str != null) {
            reqCheckFavoriteEmpty.setComponent(str);
            reqCheckFavoriteEmpty.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqCheckFavoriteEmpty));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResCheckFavoriteEmpty> requestCheckFavoriteEmpty(ReqCheckFavoriteEmpty reqCheckFavoriteEmpty, final Duration duration) {
        return requestCheckFavoriteEmpty(reqCheckFavoriteEmpty).thenCompose((Function<? super ResCheckFavoriteEmpty, ? extends CompletionStage<U>>) new Function<ResCheckFavoriteEmpty, CompletableFuture<ResCheckFavoriteEmpty>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.45
            @Override // java9.util.function.Function
            public CompletableFuture<ResCheckFavoriteEmpty> apply(ResCheckFavoriteEmpty resCheckFavoriteEmpty) {
                return CommonUtil.delayAsync(resCheckFavoriteEmpty, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResCheckFavoriteEmpty> requestCheckFavoriteEmpty() {
        return requestCheckFavoriteEmpty(new ReqCheckFavoriteEmpty());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResCheckFavoriteEmpty> requestCheckFavoriteEmpty(Duration duration) {
        return requestCheckFavoriteEmpty(new ReqCheckFavoriteEmpty(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireCheckFavoriteEmpty(ReqCheckFavoriteEmpty reqCheckFavoriteEmpty) {
        reqCheckFavoriteEmpty.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqCheckFavoriteEmpty.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqCheckFavoriteEmpty)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.46
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireCheckFavoriteEmpty(ReqCheckFavoriteEmpty reqCheckFavoriteEmpty, final Duration duration) {
        return fireCheckFavoriteEmpty(reqCheckFavoriteEmpty).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.47
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireCheckFavoriteEmpty() {
        return fireCheckFavoriteEmpty(new ReqCheckFavoriteEmpty());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireCheckFavoriteEmpty(final Duration duration) {
        return fireCheckFavoriteEmpty(new ReqCheckFavoriteEmpty()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.48
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResCheckFavoriteFull> requestCheckFavoriteFull(ReqCheckFavoriteFull reqCheckFavoriteFull) {
        if (reqCheckFavoriteFull.getResponse() == null) {
            reqCheckFavoriteFull.setResponse(new ResCheckFavoriteFull());
        }
        String str = this.component;
        if (str != null) {
            reqCheckFavoriteFull.setComponent(str);
            reqCheckFavoriteFull.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqCheckFavoriteFull));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResCheckFavoriteFull> requestCheckFavoriteFull(ReqCheckFavoriteFull reqCheckFavoriteFull, final Duration duration) {
        return requestCheckFavoriteFull(reqCheckFavoriteFull).thenCompose((Function<? super ResCheckFavoriteFull, ? extends CompletionStage<U>>) new Function<ResCheckFavoriteFull, CompletableFuture<ResCheckFavoriteFull>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.49
            @Override // java9.util.function.Function
            public CompletableFuture<ResCheckFavoriteFull> apply(ResCheckFavoriteFull resCheckFavoriteFull) {
                return CommonUtil.delayAsync(resCheckFavoriteFull, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResCheckFavoriteFull> requestCheckFavoriteFull() {
        return requestCheckFavoriteFull(new ReqCheckFavoriteFull());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResCheckFavoriteFull> requestCheckFavoriteFull(Duration duration) {
        return requestCheckFavoriteFull(new ReqCheckFavoriteFull(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireCheckFavoriteFull(ReqCheckFavoriteFull reqCheckFavoriteFull) {
        reqCheckFavoriteFull.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqCheckFavoriteFull.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqCheckFavoriteFull)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.50
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireCheckFavoriteFull(ReqCheckFavoriteFull reqCheckFavoriteFull, final Duration duration) {
        return fireCheckFavoriteFull(reqCheckFavoriteFull).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.51
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireCheckFavoriteFull() {
        return fireCheckFavoriteFull(new ReqCheckFavoriteFull());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireCheckFavoriteFull(final Duration duration) {
        return fireCheckFavoriteFull(new ReqCheckFavoriteFull()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.52
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResCheckDestinationHistoryEmpty> requestCheckDestinationHistoryEmpty(ReqCheckDestinationHistoryEmpty reqCheckDestinationHistoryEmpty) {
        if (reqCheckDestinationHistoryEmpty.getResponse() == null) {
            reqCheckDestinationHistoryEmpty.setResponse(new ResCheckDestinationHistoryEmpty());
        }
        String str = this.component;
        if (str != null) {
            reqCheckDestinationHistoryEmpty.setComponent(str);
            reqCheckDestinationHistoryEmpty.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqCheckDestinationHistoryEmpty));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResCheckDestinationHistoryEmpty> requestCheckDestinationHistoryEmpty(ReqCheckDestinationHistoryEmpty reqCheckDestinationHistoryEmpty, final Duration duration) {
        return requestCheckDestinationHistoryEmpty(reqCheckDestinationHistoryEmpty).thenCompose((Function<? super ResCheckDestinationHistoryEmpty, ? extends CompletionStage<U>>) new Function<ResCheckDestinationHistoryEmpty, CompletableFuture<ResCheckDestinationHistoryEmpty>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.53
            @Override // java9.util.function.Function
            public CompletableFuture<ResCheckDestinationHistoryEmpty> apply(ResCheckDestinationHistoryEmpty resCheckDestinationHistoryEmpty) {
                return CommonUtil.delayAsync(resCheckDestinationHistoryEmpty, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResCheckDestinationHistoryEmpty> requestCheckDestinationHistoryEmpty() {
        return requestCheckDestinationHistoryEmpty(new ReqCheckDestinationHistoryEmpty());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResCheckDestinationHistoryEmpty> requestCheckDestinationHistoryEmpty(Duration duration) {
        return requestCheckDestinationHistoryEmpty(new ReqCheckDestinationHistoryEmpty(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireCheckDestinationHistoryEmpty(ReqCheckDestinationHistoryEmpty reqCheckDestinationHistoryEmpty) {
        reqCheckDestinationHistoryEmpty.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqCheckDestinationHistoryEmpty.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqCheckDestinationHistoryEmpty)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.54
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireCheckDestinationHistoryEmpty(ReqCheckDestinationHistoryEmpty reqCheckDestinationHistoryEmpty, final Duration duration) {
        return fireCheckDestinationHistoryEmpty(reqCheckDestinationHistoryEmpty).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.55
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireCheckDestinationHistoryEmpty() {
        return fireCheckDestinationHistoryEmpty(new ReqCheckDestinationHistoryEmpty());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireCheckDestinationHistoryEmpty(final Duration duration) {
        return fireCheckDestinationHistoryEmpty(new ReqCheckDestinationHistoryEmpty()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.56
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResRequestShowSearchAlongRoutePage> requestRequestShowSearchAlongRoutePage(ReqRequestShowSearchAlongRoutePage reqRequestShowSearchAlongRoutePage) {
        if (reqRequestShowSearchAlongRoutePage.getResponse() == null) {
            reqRequestShowSearchAlongRoutePage.setResponse(new ResRequestShowSearchAlongRoutePage());
        }
        String str = this.component;
        if (str != null) {
            reqRequestShowSearchAlongRoutePage.setComponent(str);
            reqRequestShowSearchAlongRoutePage.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestShowSearchAlongRoutePage));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResRequestShowSearchAlongRoutePage> requestRequestShowSearchAlongRoutePage(ReqRequestShowSearchAlongRoutePage reqRequestShowSearchAlongRoutePage, final Duration duration) {
        return requestRequestShowSearchAlongRoutePage(reqRequestShowSearchAlongRoutePage).thenCompose((Function<? super ResRequestShowSearchAlongRoutePage, ? extends CompletionStage<U>>) new Function<ResRequestShowSearchAlongRoutePage, CompletableFuture<ResRequestShowSearchAlongRoutePage>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.57
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestShowSearchAlongRoutePage> apply(ResRequestShowSearchAlongRoutePage resRequestShowSearchAlongRoutePage) {
                return CommonUtil.delayAsync(resRequestShowSearchAlongRoutePage, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResRequestShowSearchAlongRoutePage> requestRequestShowSearchAlongRoutePage() {
        return requestRequestShowSearchAlongRoutePage(new ReqRequestShowSearchAlongRoutePage());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResRequestShowSearchAlongRoutePage> requestRequestShowSearchAlongRoutePage(Duration duration) {
        return requestRequestShowSearchAlongRoutePage(new ReqRequestShowSearchAlongRoutePage(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireRequestShowSearchAlongRoutePage(ReqRequestShowSearchAlongRoutePage reqRequestShowSearchAlongRoutePage) {
        reqRequestShowSearchAlongRoutePage.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestShowSearchAlongRoutePage.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestShowSearchAlongRoutePage)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.58
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireRequestShowSearchAlongRoutePage(ReqRequestShowSearchAlongRoutePage reqRequestShowSearchAlongRoutePage, final Duration duration) {
        return fireRequestShowSearchAlongRoutePage(reqRequestShowSearchAlongRoutePage).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.59
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireRequestShowSearchAlongRoutePage() {
        return fireRequestShowSearchAlongRoutePage(new ReqRequestShowSearchAlongRoutePage());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireRequestShowSearchAlongRoutePage(final Duration duration) {
        return fireRequestShowSearchAlongRoutePage(new ReqRequestShowSearchAlongRoutePage()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.60
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResRequestShowSearchResultPage> requestRequestShowSearchResultPage(ReqRequestShowSearchResultPage reqRequestShowSearchResultPage) {
        if (reqRequestShowSearchResultPage.getResponse() == null) {
            reqRequestShowSearchResultPage.setResponse(new ResRequestShowSearchResultPage());
        }
        String str = this.component;
        if (str != null) {
            reqRequestShowSearchResultPage.setComponent(str);
            reqRequestShowSearchResultPage.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestShowSearchResultPage));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResRequestShowSearchResultPage> requestRequestShowSearchResultPage(ReqRequestShowSearchResultPage reqRequestShowSearchResultPage, final Duration duration) {
        return requestRequestShowSearchResultPage(reqRequestShowSearchResultPage).thenCompose((Function<? super ResRequestShowSearchResultPage, ? extends CompletionStage<U>>) new Function<ResRequestShowSearchResultPage, CompletableFuture<ResRequestShowSearchResultPage>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.61
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestShowSearchResultPage> apply(ResRequestShowSearchResultPage resRequestShowSearchResultPage) {
                return CommonUtil.delayAsync(resRequestShowSearchResultPage, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResRequestShowSearchResultPage> requestRequestShowSearchResultPage() {
        return requestRequestShowSearchResultPage(new ReqRequestShowSearchResultPage());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResRequestShowSearchResultPage> requestRequestShowSearchResultPage(Duration duration) {
        return requestRequestShowSearchResultPage(new ReqRequestShowSearchResultPage(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireRequestShowSearchResultPage(ReqRequestShowSearchResultPage reqRequestShowSearchResultPage) {
        reqRequestShowSearchResultPage.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestShowSearchResultPage.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestShowSearchResultPage)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.62
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireRequestShowSearchResultPage(ReqRequestShowSearchResultPage reqRequestShowSearchResultPage, final Duration duration) {
        return fireRequestShowSearchResultPage(reqRequestShowSearchResultPage).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.63
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireRequestShowSearchResultPage() {
        return fireRequestShowSearchResultPage(new ReqRequestShowSearchResultPage());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireRequestShowSearchResultPage(final Duration duration) {
        return fireRequestShowSearchResultPage(new ReqRequestShowSearchResultPage()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.64
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqPOISearchByKeyword> requestReqPOISearchByKeyword(ReqReqPOISearchByKeyword reqReqPOISearchByKeyword) {
        if (reqReqPOISearchByKeyword.getResponse() == null) {
            reqReqPOISearchByKeyword.setResponse(new ResReqPOISearchByKeyword());
        }
        String str = this.component;
        if (str != null) {
            reqReqPOISearchByKeyword.setComponent(str);
            reqReqPOISearchByKeyword.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqPOISearchByKeyword));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqPOISearchByKeyword> requestReqPOISearchByKeyword(ReqReqPOISearchByKeyword reqReqPOISearchByKeyword, final Duration duration) {
        return requestReqPOISearchByKeyword(reqReqPOISearchByKeyword).thenCompose((Function<? super ResReqPOISearchByKeyword, ? extends CompletionStage<U>>) new Function<ResReqPOISearchByKeyword, CompletableFuture<ResReqPOISearchByKeyword>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.65
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqPOISearchByKeyword> apply(ResReqPOISearchByKeyword resReqPOISearchByKeyword) {
                return CommonUtil.delayAsync(resReqPOISearchByKeyword, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqPOISearchByKeyword.Arguments> requestReqPOISearchByKeyword(ReqReqPOISearchByKeyword.Arguments arguments) {
        ReqReqPOISearchByKeyword reqReqPOISearchByKeyword = new ReqReqPOISearchByKeyword();
        reqReqPOISearchByKeyword.setResponse(new ResReqPOISearchByKeyword());
        reqReqPOISearchByKeyword.setArguments(arguments);
        return requestReqPOISearchByKeyword(reqReqPOISearchByKeyword).thenApply((Function<? super ResReqPOISearchByKeyword, ? extends U>) new Function<ResReqPOISearchByKeyword, ResReqPOISearchByKeyword.Arguments>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.66
            @Override // java9.util.function.Function
            public ResReqPOISearchByKeyword.Arguments apply(ResReqPOISearchByKeyword resReqPOISearchByKeyword) {
                return resReqPOISearchByKeyword.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqPOISearchByKeyword.Arguments> requestReqPOISearchByKeyword(ReqReqPOISearchByKeyword.Arguments arguments, final Duration duration) {
        return requestReqPOISearchByKeyword(arguments).thenCompose((Function<? super ResReqPOISearchByKeyword.Arguments, ? extends CompletionStage<U>>) new Function<ResReqPOISearchByKeyword.Arguments, CompletableFuture<ResReqPOISearchByKeyword.Arguments>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.67
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqPOISearchByKeyword.Arguments> apply(ResReqPOISearchByKeyword.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqPOISearchByKeyword(ReqReqPOISearchByKeyword reqReqPOISearchByKeyword) {
        reqReqPOISearchByKeyword.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqPOISearchByKeyword.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqPOISearchByKeyword)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.68
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqPOISearchByKeyword(ReqReqPOISearchByKeyword reqReqPOISearchByKeyword, final Duration duration) {
        return fireReqPOISearchByKeyword(reqReqPOISearchByKeyword).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.69
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqPOISearchByKeyword(ReqReqPOISearchByKeyword.Arguments arguments) {
        ReqReqPOISearchByKeyword reqReqPOISearchByKeyword = new ReqReqPOISearchByKeyword();
        reqReqPOISearchByKeyword.setArguments(arguments);
        reqReqPOISearchByKeyword.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqPOISearchByKeyword.setComponent(str);
        }
        return fireReqPOISearchByKeyword(reqReqPOISearchByKeyword);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqPOISearchByKeyword(ReqReqPOISearchByKeyword.Arguments arguments, final Duration duration) {
        return fireReqPOISearchByKeyword(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.70
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqPOISearchByCategories> requestReqPOISearchByCategories(ReqReqPOISearchByCategories reqReqPOISearchByCategories) {
        if (reqReqPOISearchByCategories.getResponse() == null) {
            reqReqPOISearchByCategories.setResponse(new ResReqPOISearchByCategories());
        }
        String str = this.component;
        if (str != null) {
            reqReqPOISearchByCategories.setComponent(str);
            reqReqPOISearchByCategories.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqPOISearchByCategories));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqPOISearchByCategories> requestReqPOISearchByCategories(ReqReqPOISearchByCategories reqReqPOISearchByCategories, final Duration duration) {
        return requestReqPOISearchByCategories(reqReqPOISearchByCategories).thenCompose((Function<? super ResReqPOISearchByCategories, ? extends CompletionStage<U>>) new Function<ResReqPOISearchByCategories, CompletableFuture<ResReqPOISearchByCategories>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.71
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqPOISearchByCategories> apply(ResReqPOISearchByCategories resReqPOISearchByCategories) {
                return CommonUtil.delayAsync(resReqPOISearchByCategories, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqPOISearchByCategories.Arguments> requestReqPOISearchByCategories(ReqReqPOISearchByCategories.Arguments arguments) {
        ReqReqPOISearchByCategories reqReqPOISearchByCategories = new ReqReqPOISearchByCategories();
        reqReqPOISearchByCategories.setResponse(new ResReqPOISearchByCategories());
        reqReqPOISearchByCategories.setArguments(arguments);
        return requestReqPOISearchByCategories(reqReqPOISearchByCategories).thenApply((Function<? super ResReqPOISearchByCategories, ? extends U>) new Function<ResReqPOISearchByCategories, ResReqPOISearchByCategories.Arguments>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.72
            @Override // java9.util.function.Function
            public ResReqPOISearchByCategories.Arguments apply(ResReqPOISearchByCategories resReqPOISearchByCategories) {
                return resReqPOISearchByCategories.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqPOISearchByCategories.Arguments> requestReqPOISearchByCategories(ReqReqPOISearchByCategories.Arguments arguments, final Duration duration) {
        return requestReqPOISearchByCategories(arguments).thenCompose((Function<? super ResReqPOISearchByCategories.Arguments, ? extends CompletionStage<U>>) new Function<ResReqPOISearchByCategories.Arguments, CompletableFuture<ResReqPOISearchByCategories.Arguments>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.73
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqPOISearchByCategories.Arguments> apply(ResReqPOISearchByCategories.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqPOISearchByCategories(ReqReqPOISearchByCategories reqReqPOISearchByCategories) {
        reqReqPOISearchByCategories.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqPOISearchByCategories.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqPOISearchByCategories)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.74
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqPOISearchByCategories(ReqReqPOISearchByCategories reqReqPOISearchByCategories, final Duration duration) {
        return fireReqPOISearchByCategories(reqReqPOISearchByCategories).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.75
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqPOISearchByCategories(ReqReqPOISearchByCategories.Arguments arguments) {
        ReqReqPOISearchByCategories reqReqPOISearchByCategories = new ReqReqPOISearchByCategories();
        reqReqPOISearchByCategories.setArguments(arguments);
        reqReqPOISearchByCategories.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqPOISearchByCategories.setComponent(str);
        }
        return fireReqPOISearchByCategories(reqReqPOISearchByCategories);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqPOISearchByCategories(ReqReqPOISearchByCategories.Arguments arguments, final Duration duration) {
        return fireReqPOISearchByCategories(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.76
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviAlongTheWaySearch> requestReqNaviAlongTheWaySearch(ReqReqNaviAlongTheWaySearch reqReqNaviAlongTheWaySearch) {
        if (reqReqNaviAlongTheWaySearch.getResponse() == null) {
            reqReqNaviAlongTheWaySearch.setResponse(new ResReqNaviAlongTheWaySearch());
        }
        String str = this.component;
        if (str != null) {
            reqReqNaviAlongTheWaySearch.setComponent(str);
            reqReqNaviAlongTheWaySearch.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviAlongTheWaySearch));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviAlongTheWaySearch> requestReqNaviAlongTheWaySearch(ReqReqNaviAlongTheWaySearch reqReqNaviAlongTheWaySearch, final Duration duration) {
        return requestReqNaviAlongTheWaySearch(reqReqNaviAlongTheWaySearch).thenCompose((Function<? super ResReqNaviAlongTheWaySearch, ? extends CompletionStage<U>>) new Function<ResReqNaviAlongTheWaySearch, CompletableFuture<ResReqNaviAlongTheWaySearch>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.77
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviAlongTheWaySearch> apply(ResReqNaviAlongTheWaySearch resReqNaviAlongTheWaySearch) {
                return CommonUtil.delayAsync(resReqNaviAlongTheWaySearch, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviAlongTheWaySearch.Arguments> requestReqNaviAlongTheWaySearch(ReqReqNaviAlongTheWaySearch.Arguments arguments) {
        ReqReqNaviAlongTheWaySearch reqReqNaviAlongTheWaySearch = new ReqReqNaviAlongTheWaySearch();
        reqReqNaviAlongTheWaySearch.setResponse(new ResReqNaviAlongTheWaySearch());
        reqReqNaviAlongTheWaySearch.setArguments(arguments);
        return requestReqNaviAlongTheWaySearch(reqReqNaviAlongTheWaySearch).thenApply((Function<? super ResReqNaviAlongTheWaySearch, ? extends U>) new Function<ResReqNaviAlongTheWaySearch, ResReqNaviAlongTheWaySearch.Arguments>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.78
            @Override // java9.util.function.Function
            public ResReqNaviAlongTheWaySearch.Arguments apply(ResReqNaviAlongTheWaySearch resReqNaviAlongTheWaySearch) {
                return resReqNaviAlongTheWaySearch.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviAlongTheWaySearch.Arguments> requestReqNaviAlongTheWaySearch(ReqReqNaviAlongTheWaySearch.Arguments arguments, final Duration duration) {
        return requestReqNaviAlongTheWaySearch(arguments).thenCompose((Function<? super ResReqNaviAlongTheWaySearch.Arguments, ? extends CompletionStage<U>>) new Function<ResReqNaviAlongTheWaySearch.Arguments, CompletableFuture<ResReqNaviAlongTheWaySearch.Arguments>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.79
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviAlongTheWaySearch.Arguments> apply(ResReqNaviAlongTheWaySearch.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviAlongTheWaySearch(ReqReqNaviAlongTheWaySearch reqReqNaviAlongTheWaySearch) {
        reqReqNaviAlongTheWaySearch.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviAlongTheWaySearch.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviAlongTheWaySearch)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.80
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviAlongTheWaySearch(ReqReqNaviAlongTheWaySearch reqReqNaviAlongTheWaySearch, final Duration duration) {
        return fireReqNaviAlongTheWaySearch(reqReqNaviAlongTheWaySearch).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.81
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviAlongTheWaySearch(ReqReqNaviAlongTheWaySearch.Arguments arguments) {
        ReqReqNaviAlongTheWaySearch reqReqNaviAlongTheWaySearch = new ReqReqNaviAlongTheWaySearch();
        reqReqNaviAlongTheWaySearch.setArguments(arguments);
        reqReqNaviAlongTheWaySearch.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviAlongTheWaySearch.setComponent(str);
        }
        return fireReqNaviAlongTheWaySearch(reqReqNaviAlongTheWaySearch);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviAlongTheWaySearch(ReqReqNaviAlongTheWaySearch.Arguments arguments, final Duration duration) {
        return fireReqNaviAlongTheWaySearch(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.82
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
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

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResRequestGoHomeOrCompany> requestRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany reqRequestGoHomeOrCompany, final Duration duration) {
        return requestRequestGoHomeOrCompany(reqRequestGoHomeOrCompany).thenCompose((Function<? super ResRequestGoHomeOrCompany, ? extends CompletionStage<U>>) new Function<ResRequestGoHomeOrCompany, CompletableFuture<ResRequestGoHomeOrCompany>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.83
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestGoHomeOrCompany> apply(ResRequestGoHomeOrCompany resRequestGoHomeOrCompany) {
                return CommonUtil.delayAsync(resRequestGoHomeOrCompany, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResRequestGoHomeOrCompany.Arguments> requestRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany.Arguments arguments) {
        ReqRequestGoHomeOrCompany reqRequestGoHomeOrCompany = new ReqRequestGoHomeOrCompany();
        reqRequestGoHomeOrCompany.setResponse(new ResRequestGoHomeOrCompany());
        reqRequestGoHomeOrCompany.setArguments(arguments);
        return requestRequestGoHomeOrCompany(reqRequestGoHomeOrCompany).thenApply((Function<? super ResRequestGoHomeOrCompany, ? extends U>) new Function<ResRequestGoHomeOrCompany, ResRequestGoHomeOrCompany.Arguments>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.84
            @Override // java9.util.function.Function
            public ResRequestGoHomeOrCompany.Arguments apply(ResRequestGoHomeOrCompany resRequestGoHomeOrCompany) {
                return resRequestGoHomeOrCompany.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResRequestGoHomeOrCompany.Arguments> requestRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany.Arguments arguments, final Duration duration) {
        return requestRequestGoHomeOrCompany(arguments).thenCompose((Function<? super ResRequestGoHomeOrCompany.Arguments, ? extends CompletionStage<U>>) new Function<ResRequestGoHomeOrCompany.Arguments, CompletableFuture<ResRequestGoHomeOrCompany.Arguments>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.85
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestGoHomeOrCompany.Arguments> apply(ResRequestGoHomeOrCompany.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany reqRequestGoHomeOrCompany) {
        reqRequestGoHomeOrCompany.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestGoHomeOrCompany.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestGoHomeOrCompany)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.86
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany reqRequestGoHomeOrCompany, final Duration duration) {
        return fireRequestGoHomeOrCompany(reqRequestGoHomeOrCompany).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.87
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
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

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany.Arguments arguments, final Duration duration) {
        return fireRequestGoHomeOrCompany(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.88
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResRequestShowHomeOrCompanySetPage> requestRequestShowHomeOrCompanySetPage(ReqRequestShowHomeOrCompanySetPage reqRequestShowHomeOrCompanySetPage) {
        if (reqRequestShowHomeOrCompanySetPage.getResponse() == null) {
            reqRequestShowHomeOrCompanySetPage.setResponse(new ResRequestShowHomeOrCompanySetPage());
        }
        String str = this.component;
        if (str != null) {
            reqRequestShowHomeOrCompanySetPage.setComponent(str);
            reqRequestShowHomeOrCompanySetPage.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestShowHomeOrCompanySetPage));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResRequestShowHomeOrCompanySetPage> requestRequestShowHomeOrCompanySetPage(ReqRequestShowHomeOrCompanySetPage reqRequestShowHomeOrCompanySetPage, final Duration duration) {
        return requestRequestShowHomeOrCompanySetPage(reqRequestShowHomeOrCompanySetPage).thenCompose((Function<? super ResRequestShowHomeOrCompanySetPage, ? extends CompletionStage<U>>) new Function<ResRequestShowHomeOrCompanySetPage, CompletableFuture<ResRequestShowHomeOrCompanySetPage>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.89
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestShowHomeOrCompanySetPage> apply(ResRequestShowHomeOrCompanySetPage resRequestShowHomeOrCompanySetPage) {
                return CommonUtil.delayAsync(resRequestShowHomeOrCompanySetPage, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResRequestShowHomeOrCompanySetPage.Arguments> requestRequestShowHomeOrCompanySetPage(ReqRequestShowHomeOrCompanySetPage.Arguments arguments) {
        ReqRequestShowHomeOrCompanySetPage reqRequestShowHomeOrCompanySetPage = new ReqRequestShowHomeOrCompanySetPage();
        reqRequestShowHomeOrCompanySetPage.setResponse(new ResRequestShowHomeOrCompanySetPage());
        reqRequestShowHomeOrCompanySetPage.setArguments(arguments);
        return requestRequestShowHomeOrCompanySetPage(reqRequestShowHomeOrCompanySetPage).thenApply((Function<? super ResRequestShowHomeOrCompanySetPage, ? extends U>) new Function<ResRequestShowHomeOrCompanySetPage, ResRequestShowHomeOrCompanySetPage.Arguments>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.90
            @Override // java9.util.function.Function
            public ResRequestShowHomeOrCompanySetPage.Arguments apply(ResRequestShowHomeOrCompanySetPage resRequestShowHomeOrCompanySetPage) {
                return resRequestShowHomeOrCompanySetPage.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResRequestShowHomeOrCompanySetPage.Arguments> requestRequestShowHomeOrCompanySetPage(ReqRequestShowHomeOrCompanySetPage.Arguments arguments, final Duration duration) {
        return requestRequestShowHomeOrCompanySetPage(arguments).thenCompose((Function<? super ResRequestShowHomeOrCompanySetPage.Arguments, ? extends CompletionStage<U>>) new Function<ResRequestShowHomeOrCompanySetPage.Arguments, CompletableFuture<ResRequestShowHomeOrCompanySetPage.Arguments>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.91
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestShowHomeOrCompanySetPage.Arguments> apply(ResRequestShowHomeOrCompanySetPage.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireRequestShowHomeOrCompanySetPage(ReqRequestShowHomeOrCompanySetPage reqRequestShowHomeOrCompanySetPage) {
        reqRequestShowHomeOrCompanySetPage.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestShowHomeOrCompanySetPage.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestShowHomeOrCompanySetPage)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.92
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireRequestShowHomeOrCompanySetPage(ReqRequestShowHomeOrCompanySetPage reqRequestShowHomeOrCompanySetPage, final Duration duration) {
        return fireRequestShowHomeOrCompanySetPage(reqRequestShowHomeOrCompanySetPage).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.93
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireRequestShowHomeOrCompanySetPage(ReqRequestShowHomeOrCompanySetPage.Arguments arguments) {
        ReqRequestShowHomeOrCompanySetPage reqRequestShowHomeOrCompanySetPage = new ReqRequestShowHomeOrCompanySetPage();
        reqRequestShowHomeOrCompanySetPage.setArguments(arguments);
        reqRequestShowHomeOrCompanySetPage.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestShowHomeOrCompanySetPage.setComponent(str);
        }
        return fireRequestShowHomeOrCompanySetPage(reqRequestShowHomeOrCompanySetPage);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireRequestShowHomeOrCompanySetPage(ReqRequestShowHomeOrCompanySetPage.Arguments arguments, final Duration duration) {
        return fireRequestShowHomeOrCompanySetPage(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.94
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqOpenPoiDetail> requestReqOpenPoiDetail(ReqReqOpenPoiDetail reqReqOpenPoiDetail) {
        if (reqReqOpenPoiDetail.getResponse() == null) {
            reqReqOpenPoiDetail.setResponse(new ResReqOpenPoiDetail());
        }
        String str = this.component;
        if (str != null) {
            reqReqOpenPoiDetail.setComponent(str);
            reqReqOpenPoiDetail.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqOpenPoiDetail));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqOpenPoiDetail> requestReqOpenPoiDetail(ReqReqOpenPoiDetail reqReqOpenPoiDetail, final Duration duration) {
        return requestReqOpenPoiDetail(reqReqOpenPoiDetail).thenCompose((Function<? super ResReqOpenPoiDetail, ? extends CompletionStage<U>>) new Function<ResReqOpenPoiDetail, CompletableFuture<ResReqOpenPoiDetail>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.95
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqOpenPoiDetail> apply(ResReqOpenPoiDetail resReqOpenPoiDetail) {
                return CommonUtil.delayAsync(resReqOpenPoiDetail, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqOpenPoiDetail.Arguments> requestReqOpenPoiDetail(ReqReqOpenPoiDetail.Arguments arguments) {
        ReqReqOpenPoiDetail reqReqOpenPoiDetail = new ReqReqOpenPoiDetail();
        reqReqOpenPoiDetail.setResponse(new ResReqOpenPoiDetail());
        reqReqOpenPoiDetail.setArguments(arguments);
        return requestReqOpenPoiDetail(reqReqOpenPoiDetail).thenApply((Function<? super ResReqOpenPoiDetail, ? extends U>) new Function<ResReqOpenPoiDetail, ResReqOpenPoiDetail.Arguments>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.96
            @Override // java9.util.function.Function
            public ResReqOpenPoiDetail.Arguments apply(ResReqOpenPoiDetail resReqOpenPoiDetail) {
                return resReqOpenPoiDetail.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqOpenPoiDetail.Arguments> requestReqOpenPoiDetail(ReqReqOpenPoiDetail.Arguments arguments, final Duration duration) {
        return requestReqOpenPoiDetail(arguments).thenCompose((Function<? super ResReqOpenPoiDetail.Arguments, ? extends CompletionStage<U>>) new Function<ResReqOpenPoiDetail.Arguments, CompletableFuture<ResReqOpenPoiDetail.Arguments>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.97
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqOpenPoiDetail.Arguments> apply(ResReqOpenPoiDetail.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqOpenPoiDetail(ReqReqOpenPoiDetail reqReqOpenPoiDetail) {
        reqReqOpenPoiDetail.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqOpenPoiDetail.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqOpenPoiDetail)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.98
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqOpenPoiDetail(ReqReqOpenPoiDetail reqReqOpenPoiDetail, final Duration duration) {
        return fireReqOpenPoiDetail(reqReqOpenPoiDetail).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.99
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqOpenPoiDetail(ReqReqOpenPoiDetail.Arguments arguments) {
        ReqReqOpenPoiDetail reqReqOpenPoiDetail = new ReqReqOpenPoiDetail();
        reqReqOpenPoiDetail.setArguments(arguments);
        reqReqOpenPoiDetail.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqOpenPoiDetail.setComponent(str);
        }
        return fireReqOpenPoiDetail(reqReqOpenPoiDetail);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqOpenPoiDetail(ReqReqOpenPoiDetail.Arguments arguments, final Duration duration) {
        return fireReqOpenPoiDetail(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.100
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqClosePoiDetail> requestReqClosePoiDetail(ReqReqClosePoiDetail reqReqClosePoiDetail) {
        if (reqReqClosePoiDetail.getResponse() == null) {
            reqReqClosePoiDetail.setResponse(new ResReqClosePoiDetail());
        }
        String str = this.component;
        if (str != null) {
            reqReqClosePoiDetail.setComponent(str);
            reqReqClosePoiDetail.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqClosePoiDetail));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqClosePoiDetail> requestReqClosePoiDetail(ReqReqClosePoiDetail reqReqClosePoiDetail, final Duration duration) {
        return requestReqClosePoiDetail(reqReqClosePoiDetail).thenCompose((Function<? super ResReqClosePoiDetail, ? extends CompletionStage<U>>) new Function<ResReqClosePoiDetail, CompletableFuture<ResReqClosePoiDetail>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.101
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqClosePoiDetail> apply(ResReqClosePoiDetail resReqClosePoiDetail) {
                return CommonUtil.delayAsync(resReqClosePoiDetail, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqClosePoiDetail> requestReqClosePoiDetail() {
        return requestReqClosePoiDetail(new ReqReqClosePoiDetail());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqClosePoiDetail> requestReqClosePoiDetail(Duration duration) {
        return requestReqClosePoiDetail(new ReqReqClosePoiDetail(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqClosePoiDetail(ReqReqClosePoiDetail reqReqClosePoiDetail) {
        reqReqClosePoiDetail.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqClosePoiDetail.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqClosePoiDetail)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.102
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqClosePoiDetail(ReqReqClosePoiDetail reqReqClosePoiDetail, final Duration duration) {
        return fireReqClosePoiDetail(reqReqClosePoiDetail).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.103
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqClosePoiDetail() {
        return fireReqClosePoiDetail(new ReqReqClosePoiDetail());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqClosePoiDetail(final Duration duration) {
        return fireReqClosePoiDetail(new ReqReqClosePoiDetail()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.104
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetPoiDetailStatus> requestGetPoiDetailStatus(ReqGetPoiDetailStatus reqGetPoiDetailStatus) {
        if (reqGetPoiDetailStatus.getResponse() == null) {
            reqGetPoiDetailStatus.setResponse(new ResGetPoiDetailStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetPoiDetailStatus.setComponent(str);
            reqGetPoiDetailStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetPoiDetailStatus));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetPoiDetailStatus> requestGetPoiDetailStatus(ReqGetPoiDetailStatus reqGetPoiDetailStatus, final Duration duration) {
        return requestGetPoiDetailStatus(reqGetPoiDetailStatus).thenCompose((Function<? super ResGetPoiDetailStatus, ? extends CompletionStage<U>>) new Function<ResGetPoiDetailStatus, CompletableFuture<ResGetPoiDetailStatus>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.105
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetPoiDetailStatus> apply(ResGetPoiDetailStatus resGetPoiDetailStatus) {
                return CommonUtil.delayAsync(resGetPoiDetailStatus, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetPoiDetailStatus> requestGetPoiDetailStatus() {
        return requestGetPoiDetailStatus(new ReqGetPoiDetailStatus());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetPoiDetailStatus> requestGetPoiDetailStatus(Duration duration) {
        return requestGetPoiDetailStatus(new ReqGetPoiDetailStatus(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetPoiDetailStatus(ReqGetPoiDetailStatus reqGetPoiDetailStatus) {
        reqGetPoiDetailStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetPoiDetailStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetPoiDetailStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.106
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetPoiDetailStatus(ReqGetPoiDetailStatus reqGetPoiDetailStatus, final Duration duration) {
        return fireGetPoiDetailStatus(reqGetPoiDetailStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.107
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetPoiDetailStatus() {
        return fireGetPoiDetailStatus(new ReqGetPoiDetailStatus());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetPoiDetailStatus(final Duration duration) {
        return fireGetPoiDetailStatus(new ReqGetPoiDetailStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.108
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetNaviMaxViaCount> requestGetNaviMaxViaCount(ReqGetNaviMaxViaCount reqGetNaviMaxViaCount) {
        if (reqGetNaviMaxViaCount.getResponse() == null) {
            reqGetNaviMaxViaCount.setResponse(new ResGetNaviMaxViaCount());
        }
        String str = this.component;
        if (str != null) {
            reqGetNaviMaxViaCount.setComponent(str);
            reqGetNaviMaxViaCount.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviMaxViaCount));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetNaviMaxViaCount> requestGetNaviMaxViaCount(ReqGetNaviMaxViaCount reqGetNaviMaxViaCount, final Duration duration) {
        return requestGetNaviMaxViaCount(reqGetNaviMaxViaCount).thenCompose((Function<? super ResGetNaviMaxViaCount, ? extends CompletionStage<U>>) new Function<ResGetNaviMaxViaCount, CompletableFuture<ResGetNaviMaxViaCount>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.109
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNaviMaxViaCount> apply(ResGetNaviMaxViaCount resGetNaviMaxViaCount) {
                return CommonUtil.delayAsync(resGetNaviMaxViaCount, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetNaviMaxViaCount> requestGetNaviMaxViaCount() {
        return requestGetNaviMaxViaCount(new ReqGetNaviMaxViaCount());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetNaviMaxViaCount> requestGetNaviMaxViaCount(Duration duration) {
        return requestGetNaviMaxViaCount(new ReqGetNaviMaxViaCount(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetNaviMaxViaCount(ReqGetNaviMaxViaCount reqGetNaviMaxViaCount) {
        reqGetNaviMaxViaCount.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNaviMaxViaCount.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviMaxViaCount)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.110
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetNaviMaxViaCount(ReqGetNaviMaxViaCount reqGetNaviMaxViaCount, final Duration duration) {
        return fireGetNaviMaxViaCount(reqGetNaviMaxViaCount).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.111
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetNaviMaxViaCount() {
        return fireGetNaviMaxViaCount(new ReqGetNaviMaxViaCount());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetNaviMaxViaCount(final Duration duration) {
        return fireGetNaviMaxViaCount(new ReqGetNaviMaxViaCount()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.112
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviViaPosition> requestReqNaviViaPosition(ReqReqNaviViaPosition reqReqNaviViaPosition) {
        if (reqReqNaviViaPosition.getResponse() == null) {
            reqReqNaviViaPosition.setResponse(new ResReqNaviViaPosition());
        }
        String str = this.component;
        if (str != null) {
            reqReqNaviViaPosition.setComponent(str);
            reqReqNaviViaPosition.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviViaPosition));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviViaPosition> requestReqNaviViaPosition(ReqReqNaviViaPosition reqReqNaviViaPosition, final Duration duration) {
        return requestReqNaviViaPosition(reqReqNaviViaPosition).thenCompose((Function<? super ResReqNaviViaPosition, ? extends CompletionStage<U>>) new Function<ResReqNaviViaPosition, CompletableFuture<ResReqNaviViaPosition>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.113
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviViaPosition> apply(ResReqNaviViaPosition resReqNaviViaPosition) {
                return CommonUtil.delayAsync(resReqNaviViaPosition, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviViaPosition> requestReqNaviViaPosition() {
        return requestReqNaviViaPosition(new ReqReqNaviViaPosition());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviViaPosition> requestReqNaviViaPosition(Duration duration) {
        return requestReqNaviViaPosition(new ReqReqNaviViaPosition(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviViaPosition(ReqReqNaviViaPosition reqReqNaviViaPosition) {
        reqReqNaviViaPosition.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviViaPosition.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviViaPosition)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.114
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviViaPosition(ReqReqNaviViaPosition reqReqNaviViaPosition, final Duration duration) {
        return fireReqNaviViaPosition(reqReqNaviViaPosition).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.115
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviViaPosition() {
        return fireReqNaviViaPosition(new ReqReqNaviViaPosition());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviViaPosition(final Duration duration) {
        return fireReqNaviViaPosition(new ReqReqNaviViaPosition()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.116
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetSupportedRouteOptions> requestGetSupportedRouteOptions(ReqGetSupportedRouteOptions reqGetSupportedRouteOptions) {
        if (reqGetSupportedRouteOptions.getResponse() == null) {
            reqGetSupportedRouteOptions.setResponse(new ResGetSupportedRouteOptions());
        }
        String str = this.component;
        if (str != null) {
            reqGetSupportedRouteOptions.setComponent(str);
            reqGetSupportedRouteOptions.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSupportedRouteOptions));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetSupportedRouteOptions> requestGetSupportedRouteOptions(ReqGetSupportedRouteOptions reqGetSupportedRouteOptions, final Duration duration) {
        return requestGetSupportedRouteOptions(reqGetSupportedRouteOptions).thenCompose((Function<? super ResGetSupportedRouteOptions, ? extends CompletionStage<U>>) new Function<ResGetSupportedRouteOptions, CompletableFuture<ResGetSupportedRouteOptions>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.117
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetSupportedRouteOptions> apply(ResGetSupportedRouteOptions resGetSupportedRouteOptions) {
                return CommonUtil.delayAsync(resGetSupportedRouteOptions, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetSupportedRouteOptions> requestGetSupportedRouteOptions() {
        return requestGetSupportedRouteOptions(new ReqGetSupportedRouteOptions());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetSupportedRouteOptions> requestGetSupportedRouteOptions(Duration duration) {
        return requestGetSupportedRouteOptions(new ReqGetSupportedRouteOptions(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetSupportedRouteOptions(ReqGetSupportedRouteOptions reqGetSupportedRouteOptions) {
        reqGetSupportedRouteOptions.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetSupportedRouteOptions.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSupportedRouteOptions)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.118
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetSupportedRouteOptions(ReqGetSupportedRouteOptions reqGetSupportedRouteOptions, final Duration duration) {
        return fireGetSupportedRouteOptions(reqGetSupportedRouteOptions).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.119
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetSupportedRouteOptions() {
        return fireGetSupportedRouteOptions(new ReqGetSupportedRouteOptions());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetSupportedRouteOptions(final Duration duration) {
        return fireGetSupportedRouteOptions(new ReqGetSupportedRouteOptions()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.120
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviGetRouteOption> requestReqNaviGetRouteOption(ReqReqNaviGetRouteOption reqReqNaviGetRouteOption) {
        if (reqReqNaviGetRouteOption.getResponse() == null) {
            reqReqNaviGetRouteOption.setResponse(new ResReqNaviGetRouteOption());
        }
        String str = this.component;
        if (str != null) {
            reqReqNaviGetRouteOption.setComponent(str);
            reqReqNaviGetRouteOption.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviGetRouteOption));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviGetRouteOption> requestReqNaviGetRouteOption(ReqReqNaviGetRouteOption reqReqNaviGetRouteOption, final Duration duration) {
        return requestReqNaviGetRouteOption(reqReqNaviGetRouteOption).thenCompose((Function<? super ResReqNaviGetRouteOption, ? extends CompletionStage<U>>) new Function<ResReqNaviGetRouteOption, CompletableFuture<ResReqNaviGetRouteOption>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.121
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviGetRouteOption> apply(ResReqNaviGetRouteOption resReqNaviGetRouteOption) {
                return CommonUtil.delayAsync(resReqNaviGetRouteOption, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviGetRouteOption> requestReqNaviGetRouteOption() {
        return requestReqNaviGetRouteOption(new ReqReqNaviGetRouteOption());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviGetRouteOption> requestReqNaviGetRouteOption(Duration duration) {
        return requestReqNaviGetRouteOption(new ReqReqNaviGetRouteOption(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviGetRouteOption(ReqReqNaviGetRouteOption reqReqNaviGetRouteOption) {
        reqReqNaviGetRouteOption.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviGetRouteOption.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviGetRouteOption)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.122
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviGetRouteOption(ReqReqNaviGetRouteOption reqReqNaviGetRouteOption, final Duration duration) {
        return fireReqNaviGetRouteOption(reqReqNaviGetRouteOption).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.123
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviGetRouteOption() {
        return fireReqNaviGetRouteOption(new ReqReqNaviGetRouteOption());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviGetRouteOption(final Duration duration) {
        return fireReqNaviGetRouteOption(new ReqReqNaviGetRouteOption()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.124
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqOpenRouteOption> requestReqOpenRouteOption(ReqReqOpenRouteOption reqReqOpenRouteOption) {
        if (reqReqOpenRouteOption.getResponse() == null) {
            reqReqOpenRouteOption.setResponse(new ResReqOpenRouteOption());
        }
        String str = this.component;
        if (str != null) {
            reqReqOpenRouteOption.setComponent(str);
            reqReqOpenRouteOption.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqOpenRouteOption));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqOpenRouteOption> requestReqOpenRouteOption(ReqReqOpenRouteOption reqReqOpenRouteOption, final Duration duration) {
        return requestReqOpenRouteOption(reqReqOpenRouteOption).thenCompose((Function<? super ResReqOpenRouteOption, ? extends CompletionStage<U>>) new Function<ResReqOpenRouteOption, CompletableFuture<ResReqOpenRouteOption>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.125
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqOpenRouteOption> apply(ResReqOpenRouteOption resReqOpenRouteOption) {
                return CommonUtil.delayAsync(resReqOpenRouteOption, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqOpenRouteOption> requestReqOpenRouteOption() {
        return requestReqOpenRouteOption(new ReqReqOpenRouteOption());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqOpenRouteOption> requestReqOpenRouteOption(Duration duration) {
        return requestReqOpenRouteOption(new ReqReqOpenRouteOption(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqOpenRouteOption(ReqReqOpenRouteOption reqReqOpenRouteOption) {
        reqReqOpenRouteOption.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqOpenRouteOption.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqOpenRouteOption)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.126
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqOpenRouteOption(ReqReqOpenRouteOption reqReqOpenRouteOption, final Duration duration) {
        return fireReqOpenRouteOption(reqReqOpenRouteOption).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.127
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqOpenRouteOption() {
        return fireReqOpenRouteOption(new ReqReqOpenRouteOption());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqOpenRouteOption(final Duration duration) {
        return fireReqOpenRouteOption(new ReqReqOpenRouteOption()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.128
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqCloseRouteOption> requestReqCloseRouteOption(ReqReqCloseRouteOption reqReqCloseRouteOption) {
        if (reqReqCloseRouteOption.getResponse() == null) {
            reqReqCloseRouteOption.setResponse(new ResReqCloseRouteOption());
        }
        String str = this.component;
        if (str != null) {
            reqReqCloseRouteOption.setComponent(str);
            reqReqCloseRouteOption.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqCloseRouteOption));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqCloseRouteOption> requestReqCloseRouteOption(ReqReqCloseRouteOption reqReqCloseRouteOption, final Duration duration) {
        return requestReqCloseRouteOption(reqReqCloseRouteOption).thenCompose((Function<? super ResReqCloseRouteOption, ? extends CompletionStage<U>>) new Function<ResReqCloseRouteOption, CompletableFuture<ResReqCloseRouteOption>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.129
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqCloseRouteOption> apply(ResReqCloseRouteOption resReqCloseRouteOption) {
                return CommonUtil.delayAsync(resReqCloseRouteOption, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqCloseRouteOption> requestReqCloseRouteOption() {
        return requestReqCloseRouteOption(new ReqReqCloseRouteOption());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqCloseRouteOption> requestReqCloseRouteOption(Duration duration) {
        return requestReqCloseRouteOption(new ReqReqCloseRouteOption(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqCloseRouteOption(ReqReqCloseRouteOption reqReqCloseRouteOption) {
        reqReqCloseRouteOption.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqCloseRouteOption.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqCloseRouteOption)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.130
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqCloseRouteOption(ReqReqCloseRouteOption reqReqCloseRouteOption, final Duration duration) {
        return fireReqCloseRouteOption(reqReqCloseRouteOption).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.131
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqCloseRouteOption() {
        return fireReqCloseRouteOption(new ReqReqCloseRouteOption());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqCloseRouteOption(final Duration duration) {
        return fireReqCloseRouteOption(new ReqReqCloseRouteOption()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.132
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviSetRouteOption> requestReqNaviSetRouteOption(ReqReqNaviSetRouteOption reqReqNaviSetRouteOption) {
        if (reqReqNaviSetRouteOption.getResponse() == null) {
            reqReqNaviSetRouteOption.setResponse(new ResReqNaviSetRouteOption());
        }
        String str = this.component;
        if (str != null) {
            reqReqNaviSetRouteOption.setComponent(str);
            reqReqNaviSetRouteOption.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviSetRouteOption));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviSetRouteOption> requestReqNaviSetRouteOption(ReqReqNaviSetRouteOption reqReqNaviSetRouteOption, final Duration duration) {
        return requestReqNaviSetRouteOption(reqReqNaviSetRouteOption).thenCompose((Function<? super ResReqNaviSetRouteOption, ? extends CompletionStage<U>>) new Function<ResReqNaviSetRouteOption, CompletableFuture<ResReqNaviSetRouteOption>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.133
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviSetRouteOption> apply(ResReqNaviSetRouteOption resReqNaviSetRouteOption) {
                return CommonUtil.delayAsync(resReqNaviSetRouteOption, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviSetRouteOption.Arguments> requestReqNaviSetRouteOption(ReqReqNaviSetRouteOption.Arguments arguments) {
        ReqReqNaviSetRouteOption reqReqNaviSetRouteOption = new ReqReqNaviSetRouteOption();
        reqReqNaviSetRouteOption.setResponse(new ResReqNaviSetRouteOption());
        reqReqNaviSetRouteOption.setArguments(arguments);
        return requestReqNaviSetRouteOption(reqReqNaviSetRouteOption).thenApply((Function<? super ResReqNaviSetRouteOption, ? extends U>) new Function<ResReqNaviSetRouteOption, ResReqNaviSetRouteOption.Arguments>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.134
            @Override // java9.util.function.Function
            public ResReqNaviSetRouteOption.Arguments apply(ResReqNaviSetRouteOption resReqNaviSetRouteOption) {
                return resReqNaviSetRouteOption.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviSetRouteOption.Arguments> requestReqNaviSetRouteOption(ReqReqNaviSetRouteOption.Arguments arguments, final Duration duration) {
        return requestReqNaviSetRouteOption(arguments).thenCompose((Function<? super ResReqNaviSetRouteOption.Arguments, ? extends CompletionStage<U>>) new Function<ResReqNaviSetRouteOption.Arguments, CompletableFuture<ResReqNaviSetRouteOption.Arguments>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.135
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviSetRouteOption.Arguments> apply(ResReqNaviSetRouteOption.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviSetRouteOption(ReqReqNaviSetRouteOption reqReqNaviSetRouteOption) {
        reqReqNaviSetRouteOption.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviSetRouteOption.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviSetRouteOption)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.136
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviSetRouteOption(ReqReqNaviSetRouteOption reqReqNaviSetRouteOption, final Duration duration) {
        return fireReqNaviSetRouteOption(reqReqNaviSetRouteOption).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.137
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviSetRouteOption(ReqReqNaviSetRouteOption.Arguments arguments) {
        ReqReqNaviSetRouteOption reqReqNaviSetRouteOption = new ReqReqNaviSetRouteOption();
        reqReqNaviSetRouteOption.setArguments(arguments);
        reqReqNaviSetRouteOption.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviSetRouteOption.setComponent(str);
        }
        return fireReqNaviSetRouteOption(reqReqNaviSetRouteOption);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviSetRouteOption(ReqReqNaviSetRouteOption.Arguments arguments, final Duration duration) {
        return fireReqNaviSetRouteOption(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.138
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviInfo> requestReqNaviInfo(ReqReqNaviInfo reqReqNaviInfo) {
        if (reqReqNaviInfo.getResponse() == null) {
            reqReqNaviInfo.setResponse(new ResReqNaviInfo());
        }
        String str = this.component;
        if (str != null) {
            reqReqNaviInfo.setComponent(str);
            reqReqNaviInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviInfo));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviInfo> requestReqNaviInfo(ReqReqNaviInfo reqReqNaviInfo, final Duration duration) {
        return requestReqNaviInfo(reqReqNaviInfo).thenCompose((Function<? super ResReqNaviInfo, ? extends CompletionStage<U>>) new Function<ResReqNaviInfo, CompletableFuture<ResReqNaviInfo>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.139
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviInfo> apply(ResReqNaviInfo resReqNaviInfo) {
                return CommonUtil.delayAsync(resReqNaviInfo, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviInfo> requestReqNaviInfo() {
        return requestReqNaviInfo(new ReqReqNaviInfo());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviInfo> requestReqNaviInfo(Duration duration) {
        return requestReqNaviInfo(new ReqReqNaviInfo(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviInfo(ReqReqNaviInfo reqReqNaviInfo) {
        reqReqNaviInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.140
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviInfo(ReqReqNaviInfo reqReqNaviInfo, final Duration duration) {
        return fireReqNaviInfo(reqReqNaviInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.141
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviInfo() {
        return fireReqNaviInfo(new ReqReqNaviInfo());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviInfo(final Duration duration) {
        return fireReqNaviInfo(new ReqReqNaviInfo()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.142
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqRoutePlanning> requestReqRoutePlanning(ReqReqRoutePlanning reqReqRoutePlanning) {
        if (reqReqRoutePlanning.getResponse() == null) {
            reqReqRoutePlanning.setResponse(new ResReqRoutePlanning());
        }
        String str = this.component;
        if (str != null) {
            reqReqRoutePlanning.setComponent(str);
            reqReqRoutePlanning.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqRoutePlanning));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqRoutePlanning> requestReqRoutePlanning(ReqReqRoutePlanning reqReqRoutePlanning, final Duration duration) {
        return requestReqRoutePlanning(reqReqRoutePlanning).thenCompose((Function<? super ResReqRoutePlanning, ? extends CompletionStage<U>>) new Function<ResReqRoutePlanning, CompletableFuture<ResReqRoutePlanning>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.143
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqRoutePlanning> apply(ResReqRoutePlanning resReqRoutePlanning) {
                return CommonUtil.delayAsync(resReqRoutePlanning, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqRoutePlanning.Arguments> requestReqRoutePlanning(ReqReqRoutePlanning.Arguments arguments) {
        ReqReqRoutePlanning reqReqRoutePlanning = new ReqReqRoutePlanning();
        reqReqRoutePlanning.setResponse(new ResReqRoutePlanning());
        reqReqRoutePlanning.setArguments(arguments);
        return requestReqRoutePlanning(reqReqRoutePlanning).thenApply((Function<? super ResReqRoutePlanning, ? extends U>) new Function<ResReqRoutePlanning, ResReqRoutePlanning.Arguments>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.144
            @Override // java9.util.function.Function
            public ResReqRoutePlanning.Arguments apply(ResReqRoutePlanning resReqRoutePlanning) {
                return resReqRoutePlanning.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqRoutePlanning.Arguments> requestReqRoutePlanning(ReqReqRoutePlanning.Arguments arguments, final Duration duration) {
        return requestReqRoutePlanning(arguments).thenCompose((Function<? super ResReqRoutePlanning.Arguments, ? extends CompletionStage<U>>) new Function<ResReqRoutePlanning.Arguments, CompletableFuture<ResReqRoutePlanning.Arguments>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.145
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqRoutePlanning.Arguments> apply(ResReqRoutePlanning.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqRoutePlanning(ReqReqRoutePlanning reqReqRoutePlanning) {
        reqReqRoutePlanning.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqRoutePlanning.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqRoutePlanning)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.146
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqRoutePlanning(ReqReqRoutePlanning reqReqRoutePlanning, final Duration duration) {
        return fireReqRoutePlanning(reqReqRoutePlanning).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.147
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqRoutePlanning(ReqReqRoutePlanning.Arguments arguments) {
        ReqReqRoutePlanning reqReqRoutePlanning = new ReqReqRoutePlanning();
        reqReqRoutePlanning.setArguments(arguments);
        reqReqRoutePlanning.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqRoutePlanning.setComponent(str);
        }
        return fireReqRoutePlanning(reqReqRoutePlanning);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqRoutePlanning(ReqReqRoutePlanning.Arguments arguments, final Duration duration) {
        return fireReqRoutePlanning(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.148
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqSmartRoutePlanning> requestReqSmartRoutePlanning(ReqReqSmartRoutePlanning reqReqSmartRoutePlanning) {
        if (reqReqSmartRoutePlanning.getResponse() == null) {
            reqReqSmartRoutePlanning.setResponse(new ResReqSmartRoutePlanning());
        }
        String str = this.component;
        if (str != null) {
            reqReqSmartRoutePlanning.setComponent(str);
            reqReqSmartRoutePlanning.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqSmartRoutePlanning));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqSmartRoutePlanning> requestReqSmartRoutePlanning(ReqReqSmartRoutePlanning reqReqSmartRoutePlanning, final Duration duration) {
        return requestReqSmartRoutePlanning(reqReqSmartRoutePlanning).thenCompose((Function<? super ResReqSmartRoutePlanning, ? extends CompletionStage<U>>) new Function<ResReqSmartRoutePlanning, CompletableFuture<ResReqSmartRoutePlanning>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.149
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqSmartRoutePlanning> apply(ResReqSmartRoutePlanning resReqSmartRoutePlanning) {
                return CommonUtil.delayAsync(resReqSmartRoutePlanning, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqSmartRoutePlanning.Arguments> requestReqSmartRoutePlanning(ReqReqSmartRoutePlanning.Arguments arguments) {
        ReqReqSmartRoutePlanning reqReqSmartRoutePlanning = new ReqReqSmartRoutePlanning();
        reqReqSmartRoutePlanning.setResponse(new ResReqSmartRoutePlanning());
        reqReqSmartRoutePlanning.setArguments(arguments);
        return requestReqSmartRoutePlanning(reqReqSmartRoutePlanning).thenApply((Function<? super ResReqSmartRoutePlanning, ? extends U>) new Function<ResReqSmartRoutePlanning, ResReqSmartRoutePlanning.Arguments>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.150
            @Override // java9.util.function.Function
            public ResReqSmartRoutePlanning.Arguments apply(ResReqSmartRoutePlanning resReqSmartRoutePlanning) {
                return resReqSmartRoutePlanning.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqSmartRoutePlanning.Arguments> requestReqSmartRoutePlanning(ReqReqSmartRoutePlanning.Arguments arguments, final Duration duration) {
        return requestReqSmartRoutePlanning(arguments).thenCompose((Function<? super ResReqSmartRoutePlanning.Arguments, ? extends CompletionStage<U>>) new Function<ResReqSmartRoutePlanning.Arguments, CompletableFuture<ResReqSmartRoutePlanning.Arguments>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.151
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqSmartRoutePlanning.Arguments> apply(ResReqSmartRoutePlanning.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqSmartRoutePlanning(ReqReqSmartRoutePlanning reqReqSmartRoutePlanning) {
        reqReqSmartRoutePlanning.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqSmartRoutePlanning.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqSmartRoutePlanning)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.152
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqSmartRoutePlanning(ReqReqSmartRoutePlanning reqReqSmartRoutePlanning, final Duration duration) {
        return fireReqSmartRoutePlanning(reqReqSmartRoutePlanning).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.153
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqSmartRoutePlanning(ReqReqSmartRoutePlanning.Arguments arguments) {
        ReqReqSmartRoutePlanning reqReqSmartRoutePlanning = new ReqReqSmartRoutePlanning();
        reqReqSmartRoutePlanning.setArguments(arguments);
        reqReqSmartRoutePlanning.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqSmartRoutePlanning.setComponent(str);
        }
        return fireReqSmartRoutePlanning(reqReqSmartRoutePlanning);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqSmartRoutePlanning(ReqReqSmartRoutePlanning.Arguments arguments, final Duration duration) {
        return fireReqSmartRoutePlanning(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.154
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqGuideStart> requestReqGuideStart(ReqReqGuideStart reqReqGuideStart) {
        if (reqReqGuideStart.getResponse() == null) {
            reqReqGuideStart.setResponse(new ResReqGuideStart());
        }
        String str = this.component;
        if (str != null) {
            reqReqGuideStart.setComponent(str);
            reqReqGuideStart.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqGuideStart));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqGuideStart> requestReqGuideStart(ReqReqGuideStart reqReqGuideStart, final Duration duration) {
        return requestReqGuideStart(reqReqGuideStart).thenCompose((Function<? super ResReqGuideStart, ? extends CompletionStage<U>>) new Function<ResReqGuideStart, CompletableFuture<ResReqGuideStart>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.155
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqGuideStart> apply(ResReqGuideStart resReqGuideStart) {
                return CommonUtil.delayAsync(resReqGuideStart, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqGuideStart.Arguments> requestReqGuideStart(ReqReqGuideStart.Arguments arguments) {
        ReqReqGuideStart reqReqGuideStart = new ReqReqGuideStart();
        reqReqGuideStart.setResponse(new ResReqGuideStart());
        reqReqGuideStart.setArguments(arguments);
        return requestReqGuideStart(reqReqGuideStart).thenApply((Function<? super ResReqGuideStart, ? extends U>) new Function<ResReqGuideStart, ResReqGuideStart.Arguments>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.156
            @Override // java9.util.function.Function
            public ResReqGuideStart.Arguments apply(ResReqGuideStart resReqGuideStart) {
                return resReqGuideStart.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqGuideStart.Arguments> requestReqGuideStart(ReqReqGuideStart.Arguments arguments, final Duration duration) {
        return requestReqGuideStart(arguments).thenCompose((Function<? super ResReqGuideStart.Arguments, ? extends CompletionStage<U>>) new Function<ResReqGuideStart.Arguments, CompletableFuture<ResReqGuideStart.Arguments>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.157
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqGuideStart.Arguments> apply(ResReqGuideStart.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqGuideStart(ReqReqGuideStart reqReqGuideStart) {
        reqReqGuideStart.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqGuideStart.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqGuideStart)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.158
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqGuideStart(ReqReqGuideStart reqReqGuideStart, final Duration duration) {
        return fireReqGuideStart(reqReqGuideStart).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.159
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqGuideStart(ReqReqGuideStart.Arguments arguments) {
        ReqReqGuideStart reqReqGuideStart = new ReqReqGuideStart();
        reqReqGuideStart.setArguments(arguments);
        reqReqGuideStart.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqGuideStart.setComponent(str);
        }
        return fireReqGuideStart(reqReqGuideStart);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqGuideStart(ReqReqGuideStart.Arguments arguments, final Duration duration) {
        return fireReqGuideStart(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.160
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviRouteFullView> requestReqNaviRouteFullView(ReqReqNaviRouteFullView reqReqNaviRouteFullView) {
        if (reqReqNaviRouteFullView.getResponse() == null) {
            reqReqNaviRouteFullView.setResponse(new ResReqNaviRouteFullView());
        }
        String str = this.component;
        if (str != null) {
            reqReqNaviRouteFullView.setComponent(str);
            reqReqNaviRouteFullView.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviRouteFullView));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviRouteFullView> requestReqNaviRouteFullView(ReqReqNaviRouteFullView reqReqNaviRouteFullView, final Duration duration) {
        return requestReqNaviRouteFullView(reqReqNaviRouteFullView).thenCompose((Function<? super ResReqNaviRouteFullView, ? extends CompletionStage<U>>) new Function<ResReqNaviRouteFullView, CompletableFuture<ResReqNaviRouteFullView>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.161
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviRouteFullView> apply(ResReqNaviRouteFullView resReqNaviRouteFullView) {
                return CommonUtil.delayAsync(resReqNaviRouteFullView, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviRouteFullView.Arguments> requestReqNaviRouteFullView(ReqReqNaviRouteFullView.Arguments arguments) {
        ReqReqNaviRouteFullView reqReqNaviRouteFullView = new ReqReqNaviRouteFullView();
        reqReqNaviRouteFullView.setResponse(new ResReqNaviRouteFullView());
        reqReqNaviRouteFullView.setArguments(arguments);
        return requestReqNaviRouteFullView(reqReqNaviRouteFullView).thenApply((Function<? super ResReqNaviRouteFullView, ? extends U>) new Function<ResReqNaviRouteFullView, ResReqNaviRouteFullView.Arguments>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.162
            @Override // java9.util.function.Function
            public ResReqNaviRouteFullView.Arguments apply(ResReqNaviRouteFullView resReqNaviRouteFullView) {
                return resReqNaviRouteFullView.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqNaviRouteFullView.Arguments> requestReqNaviRouteFullView(ReqReqNaviRouteFullView.Arguments arguments, final Duration duration) {
        return requestReqNaviRouteFullView(arguments).thenCompose((Function<? super ResReqNaviRouteFullView.Arguments, ? extends CompletionStage<U>>) new Function<ResReqNaviRouteFullView.Arguments, CompletableFuture<ResReqNaviRouteFullView.Arguments>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.163
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqNaviRouteFullView.Arguments> apply(ResReqNaviRouteFullView.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviRouteFullView(ReqReqNaviRouteFullView reqReqNaviRouteFullView) {
        reqReqNaviRouteFullView.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviRouteFullView.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqNaviRouteFullView)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.164
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviRouteFullView(ReqReqNaviRouteFullView reqReqNaviRouteFullView, final Duration duration) {
        return fireReqNaviRouteFullView(reqReqNaviRouteFullView).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.165
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviRouteFullView(ReqReqNaviRouteFullView.Arguments arguments) {
        ReqReqNaviRouteFullView reqReqNaviRouteFullView = new ReqReqNaviRouteFullView();
        reqReqNaviRouteFullView.setArguments(arguments);
        reqReqNaviRouteFullView.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqNaviRouteFullView.setComponent(str);
        }
        return fireReqNaviRouteFullView(reqReqNaviRouteFullView);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqNaviRouteFullView(ReqReqNaviRouteFullView.Arguments arguments, final Duration duration) {
        return fireReqNaviRouteFullView(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.166
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqAddViaPosition> requestReqAddViaPosition(ReqReqAddViaPosition reqReqAddViaPosition) {
        if (reqReqAddViaPosition.getResponse() == null) {
            reqReqAddViaPosition.setResponse(new ResReqAddViaPosition());
        }
        String str = this.component;
        if (str != null) {
            reqReqAddViaPosition.setComponent(str);
            reqReqAddViaPosition.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqAddViaPosition));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqAddViaPosition> requestReqAddViaPosition(ReqReqAddViaPosition reqReqAddViaPosition, final Duration duration) {
        return requestReqAddViaPosition(reqReqAddViaPosition).thenCompose((Function<? super ResReqAddViaPosition, ? extends CompletionStage<U>>) new Function<ResReqAddViaPosition, CompletableFuture<ResReqAddViaPosition>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.167
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqAddViaPosition> apply(ResReqAddViaPosition resReqAddViaPosition) {
                return CommonUtil.delayAsync(resReqAddViaPosition, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqAddViaPosition.Arguments> requestReqAddViaPosition(ReqReqAddViaPosition.Arguments arguments) {
        ReqReqAddViaPosition reqReqAddViaPosition = new ReqReqAddViaPosition();
        reqReqAddViaPosition.setResponse(new ResReqAddViaPosition());
        reqReqAddViaPosition.setArguments(arguments);
        return requestReqAddViaPosition(reqReqAddViaPosition).thenApply((Function<? super ResReqAddViaPosition, ? extends U>) new Function<ResReqAddViaPosition, ResReqAddViaPosition.Arguments>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.168
            @Override // java9.util.function.Function
            public ResReqAddViaPosition.Arguments apply(ResReqAddViaPosition resReqAddViaPosition) {
                return resReqAddViaPosition.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqAddViaPosition.Arguments> requestReqAddViaPosition(ReqReqAddViaPosition.Arguments arguments, final Duration duration) {
        return requestReqAddViaPosition(arguments).thenCompose((Function<? super ResReqAddViaPosition.Arguments, ? extends CompletionStage<U>>) new Function<ResReqAddViaPosition.Arguments, CompletableFuture<ResReqAddViaPosition.Arguments>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.169
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqAddViaPosition.Arguments> apply(ResReqAddViaPosition.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqAddViaPosition(ReqReqAddViaPosition reqReqAddViaPosition) {
        reqReqAddViaPosition.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqAddViaPosition.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqAddViaPosition)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.170
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqAddViaPosition(ReqReqAddViaPosition reqReqAddViaPosition, final Duration duration) {
        return fireReqAddViaPosition(reqReqAddViaPosition).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.171
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqAddViaPosition(ReqReqAddViaPosition.Arguments arguments) {
        ReqReqAddViaPosition reqReqAddViaPosition = new ReqReqAddViaPosition();
        reqReqAddViaPosition.setArguments(arguments);
        reqReqAddViaPosition.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqAddViaPosition.setComponent(str);
        }
        return fireReqAddViaPosition(reqReqAddViaPosition);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqAddViaPosition(ReqReqAddViaPosition.Arguments arguments, final Duration duration) {
        return fireReqAddViaPosition(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.172
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqAppendVia> requestReqAppendVia(ReqReqAppendVia reqReqAppendVia) {
        if (reqReqAppendVia.getResponse() == null) {
            reqReqAppendVia.setResponse(new ResReqAppendVia());
        }
        String str = this.component;
        if (str != null) {
            reqReqAppendVia.setComponent(str);
            reqReqAppendVia.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqAppendVia));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqAppendVia> requestReqAppendVia(ReqReqAppendVia reqReqAppendVia, final Duration duration) {
        return requestReqAppendVia(reqReqAppendVia).thenCompose((Function<? super ResReqAppendVia, ? extends CompletionStage<U>>) new Function<ResReqAppendVia, CompletableFuture<ResReqAppendVia>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.173
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqAppendVia> apply(ResReqAppendVia resReqAppendVia) {
                return CommonUtil.delayAsync(resReqAppendVia, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqAppendVia.Arguments> requestReqAppendVia(ReqReqAppendVia.Arguments arguments) {
        ReqReqAppendVia reqReqAppendVia = new ReqReqAppendVia();
        reqReqAppendVia.setResponse(new ResReqAppendVia());
        reqReqAppendVia.setArguments(arguments);
        return requestReqAppendVia(reqReqAppendVia).thenApply((Function<? super ResReqAppendVia, ? extends U>) new Function<ResReqAppendVia, ResReqAppendVia.Arguments>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.174
            @Override // java9.util.function.Function
            public ResReqAppendVia.Arguments apply(ResReqAppendVia resReqAppendVia) {
                return resReqAppendVia.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqAppendVia.Arguments> requestReqAppendVia(ReqReqAppendVia.Arguments arguments, final Duration duration) {
        return requestReqAppendVia(arguments).thenCompose((Function<? super ResReqAppendVia.Arguments, ? extends CompletionStage<U>>) new Function<ResReqAppendVia.Arguments, CompletableFuture<ResReqAppendVia.Arguments>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.175
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqAppendVia.Arguments> apply(ResReqAppendVia.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqAppendVia(ReqReqAppendVia reqReqAppendVia) {
        reqReqAppendVia.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqAppendVia.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqAppendVia)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.176
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqAppendVia(ReqReqAppendVia reqReqAppendVia, final Duration duration) {
        return fireReqAppendVia(reqReqAppendVia).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.177
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqAppendVia(ReqReqAppendVia.Arguments arguments) {
        ReqReqAppendVia reqReqAppendVia = new ReqReqAppendVia();
        reqReqAppendVia.setArguments(arguments);
        reqReqAppendVia.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqAppendVia.setComponent(str);
        }
        return fireReqAppendVia(reqReqAppendVia);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqAppendVia(ReqReqAppendVia.Arguments arguments, final Duration duration) {
        return fireReqAppendVia(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.178
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqModifyDestination> requestReqModifyDestination(ReqReqModifyDestination reqReqModifyDestination) {
        if (reqReqModifyDestination.getResponse() == null) {
            reqReqModifyDestination.setResponse(new ResReqModifyDestination());
        }
        String str = this.component;
        if (str != null) {
            reqReqModifyDestination.setComponent(str);
            reqReqModifyDestination.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqModifyDestination));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqModifyDestination> requestReqModifyDestination(ReqReqModifyDestination reqReqModifyDestination, final Duration duration) {
        return requestReqModifyDestination(reqReqModifyDestination).thenCompose((Function<? super ResReqModifyDestination, ? extends CompletionStage<U>>) new Function<ResReqModifyDestination, CompletableFuture<ResReqModifyDestination>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.179
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqModifyDestination> apply(ResReqModifyDestination resReqModifyDestination) {
                return CommonUtil.delayAsync(resReqModifyDestination, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqModifyDestination.Arguments> requestReqModifyDestination(ReqReqModifyDestination.Arguments arguments) {
        ReqReqModifyDestination reqReqModifyDestination = new ReqReqModifyDestination();
        reqReqModifyDestination.setResponse(new ResReqModifyDestination());
        reqReqModifyDestination.setArguments(arguments);
        return requestReqModifyDestination(reqReqModifyDestination).thenApply((Function<? super ResReqModifyDestination, ? extends U>) new Function<ResReqModifyDestination, ResReqModifyDestination.Arguments>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.180
            @Override // java9.util.function.Function
            public ResReqModifyDestination.Arguments apply(ResReqModifyDestination resReqModifyDestination) {
                return resReqModifyDestination.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqModifyDestination.Arguments> requestReqModifyDestination(ReqReqModifyDestination.Arguments arguments, final Duration duration) {
        return requestReqModifyDestination(arguments).thenCompose((Function<? super ResReqModifyDestination.Arguments, ? extends CompletionStage<U>>) new Function<ResReqModifyDestination.Arguments, CompletableFuture<ResReqModifyDestination.Arguments>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.181
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqModifyDestination.Arguments> apply(ResReqModifyDestination.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqModifyDestination(ReqReqModifyDestination reqReqModifyDestination) {
        reqReqModifyDestination.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqModifyDestination.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqModifyDestination)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.182
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqModifyDestination(ReqReqModifyDestination reqReqModifyDestination, final Duration duration) {
        return fireReqModifyDestination(reqReqModifyDestination).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.183
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqModifyDestination(ReqReqModifyDestination.Arguments arguments) {
        ReqReqModifyDestination reqReqModifyDestination = new ReqReqModifyDestination();
        reqReqModifyDestination.setArguments(arguments);
        reqReqModifyDestination.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqModifyDestination.setComponent(str);
        }
        return fireReqModifyDestination(reqReqModifyDestination);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqModifyDestination(ReqReqModifyDestination.Arguments arguments, final Duration duration) {
        return fireReqModifyDestination(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.184
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqSmartNaviStart> requestReqSmartNaviStart(ReqReqSmartNaviStart reqReqSmartNaviStart) {
        if (reqReqSmartNaviStart.getResponse() == null) {
            reqReqSmartNaviStart.setResponse(new ResReqSmartNaviStart());
        }
        String str = this.component;
        if (str != null) {
            reqReqSmartNaviStart.setComponent(str);
            reqReqSmartNaviStart.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqSmartNaviStart));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqSmartNaviStart> requestReqSmartNaviStart(ReqReqSmartNaviStart reqReqSmartNaviStart, final Duration duration) {
        return requestReqSmartNaviStart(reqReqSmartNaviStart).thenCompose((Function<? super ResReqSmartNaviStart, ? extends CompletionStage<U>>) new Function<ResReqSmartNaviStart, CompletableFuture<ResReqSmartNaviStart>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.185
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqSmartNaviStart> apply(ResReqSmartNaviStart resReqSmartNaviStart) {
                return CommonUtil.delayAsync(resReqSmartNaviStart, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqSmartNaviStart.Arguments> requestReqSmartNaviStart(ReqReqSmartNaviStart.Arguments arguments) {
        ReqReqSmartNaviStart reqReqSmartNaviStart = new ReqReqSmartNaviStart();
        reqReqSmartNaviStart.setResponse(new ResReqSmartNaviStart());
        reqReqSmartNaviStart.setArguments(arguments);
        return requestReqSmartNaviStart(reqReqSmartNaviStart).thenApply((Function<? super ResReqSmartNaviStart, ? extends U>) new Function<ResReqSmartNaviStart, ResReqSmartNaviStart.Arguments>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.186
            @Override // java9.util.function.Function
            public ResReqSmartNaviStart.Arguments apply(ResReqSmartNaviStart resReqSmartNaviStart) {
                return resReqSmartNaviStart.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResReqSmartNaviStart.Arguments> requestReqSmartNaviStart(ReqReqSmartNaviStart.Arguments arguments, final Duration duration) {
        return requestReqSmartNaviStart(arguments).thenCompose((Function<? super ResReqSmartNaviStart.Arguments, ? extends CompletionStage<U>>) new Function<ResReqSmartNaviStart.Arguments, CompletableFuture<ResReqSmartNaviStart.Arguments>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.187
            @Override // java9.util.function.Function
            public CompletableFuture<ResReqSmartNaviStart.Arguments> apply(ResReqSmartNaviStart.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqSmartNaviStart(ReqReqSmartNaviStart reqReqSmartNaviStart) {
        reqReqSmartNaviStart.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqSmartNaviStart.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqReqSmartNaviStart)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.188
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqSmartNaviStart(ReqReqSmartNaviStart reqReqSmartNaviStart, final Duration duration) {
        return fireReqSmartNaviStart(reqReqSmartNaviStart).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.189
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqSmartNaviStart(ReqReqSmartNaviStart.Arguments arguments) {
        ReqReqSmartNaviStart reqReqSmartNaviStart = new ReqReqSmartNaviStart();
        reqReqSmartNaviStart.setArguments(arguments);
        reqReqSmartNaviStart.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqReqSmartNaviStart.setComponent(str);
        }
        return fireReqSmartNaviStart(reqReqSmartNaviStart);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireReqSmartNaviStart(ReqReqSmartNaviStart.Arguments arguments, final Duration duration) {
        return fireReqSmartNaviStart(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.190
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetCurrentPlace> requestGetCurrentPlace(ReqGetCurrentPlace reqGetCurrentPlace) {
        if (reqGetCurrentPlace.getResponse() == null) {
            reqGetCurrentPlace.setResponse(new ResGetCurrentPlace());
        }
        String str = this.component;
        if (str != null) {
            reqGetCurrentPlace.setComponent(str);
            reqGetCurrentPlace.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCurrentPlace));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetCurrentPlace> requestGetCurrentPlace(ReqGetCurrentPlace reqGetCurrentPlace, final Duration duration) {
        return requestGetCurrentPlace(reqGetCurrentPlace).thenCompose((Function<? super ResGetCurrentPlace, ? extends CompletionStage<U>>) new Function<ResGetCurrentPlace, CompletableFuture<ResGetCurrentPlace>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.191
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetCurrentPlace> apply(ResGetCurrentPlace resGetCurrentPlace) {
                return CommonUtil.delayAsync(resGetCurrentPlace, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetCurrentPlace> requestGetCurrentPlace() {
        return requestGetCurrentPlace(new ReqGetCurrentPlace());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetCurrentPlace> requestGetCurrentPlace(Duration duration) {
        return requestGetCurrentPlace(new ReqGetCurrentPlace(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetCurrentPlace(ReqGetCurrentPlace reqGetCurrentPlace) {
        reqGetCurrentPlace.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetCurrentPlace.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCurrentPlace)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.192
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetCurrentPlace(ReqGetCurrentPlace reqGetCurrentPlace, final Duration duration) {
        return fireGetCurrentPlace(reqGetCurrentPlace).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.193
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetCurrentPlace() {
        return fireGetCurrentPlace(new ReqGetCurrentPlace());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetCurrentPlace(final Duration duration) {
        return fireGetCurrentPlace(new ReqGetCurrentPlace()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.194
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnGPSInfo(ReqOnGPSInfo reqOnGPSInfo) {
        reqOnGPSInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnGPSInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnGPSInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.195
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnGPSInfo(ReqOnGPSInfo reqOnGPSInfo, final Duration duration) {
        return fireOnGPSInfo(reqOnGPSInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.196
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnGPSInfo(ReqOnGPSInfo.Arguments arguments) {
        ReqOnGPSInfo reqOnGPSInfo = new ReqOnGPSInfo();
        reqOnGPSInfo.setArguments(arguments);
        reqOnGPSInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnGPSInfo.setComponent(str);
        }
        return fireOnGPSInfo(reqOnGPSInfo);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnGPSInfo(ReqOnGPSInfo.Arguments arguments, final Duration duration) {
        return fireOnGPSInfo(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.197
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnDelPrivacyPolicyInfo(ReqOnDelPrivacyPolicyInfo reqOnDelPrivacyPolicyInfo) {
        reqOnDelPrivacyPolicyInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnDelPrivacyPolicyInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnDelPrivacyPolicyInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.198
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnDelPrivacyPolicyInfo(ReqOnDelPrivacyPolicyInfo reqOnDelPrivacyPolicyInfo, final Duration duration) {
        return fireOnDelPrivacyPolicyInfo(reqOnDelPrivacyPolicyInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.199
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnDelPrivacyPolicyInfo(ReqOnDelPrivacyPolicyInfo.Arguments arguments) {
        ReqOnDelPrivacyPolicyInfo reqOnDelPrivacyPolicyInfo = new ReqOnDelPrivacyPolicyInfo();
        reqOnDelPrivacyPolicyInfo.setArguments(arguments);
        reqOnDelPrivacyPolicyInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnDelPrivacyPolicyInfo.setComponent(str);
        }
        return fireOnDelPrivacyPolicyInfo(reqOnDelPrivacyPolicyInfo);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnDelPrivacyPolicyInfo(ReqOnDelPrivacyPolicyInfo.Arguments arguments, final Duration duration) {
        return fireOnDelPrivacyPolicyInfo(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.200
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnPrivacyPolicySyncStatus(ReqOnPrivacyPolicySyncStatus reqOnPrivacyPolicySyncStatus) {
        reqOnPrivacyPolicySyncStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnPrivacyPolicySyncStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnPrivacyPolicySyncStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.201
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnPrivacyPolicySyncStatus(ReqOnPrivacyPolicySyncStatus reqOnPrivacyPolicySyncStatus, final Duration duration) {
        return fireOnPrivacyPolicySyncStatus(reqOnPrivacyPolicySyncStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.202
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnPrivacyPolicySyncStatus(ReqOnPrivacyPolicySyncStatus.Arguments arguments) {
        ReqOnPrivacyPolicySyncStatus reqOnPrivacyPolicySyncStatus = new ReqOnPrivacyPolicySyncStatus();
        reqOnPrivacyPolicySyncStatus.setArguments(arguments);
        reqOnPrivacyPolicySyncStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnPrivacyPolicySyncStatus.setComponent(str);
        }
        return fireOnPrivacyPolicySyncStatus(reqOnPrivacyPolicySyncStatus);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnPrivacyPolicySyncStatus(ReqOnPrivacyPolicySyncStatus.Arguments arguments, final Duration duration) {
        return fireOnPrivacyPolicySyncStatus(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.203
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnRevokePrivacyPolicy(ReqOnRevokePrivacyPolicy reqOnRevokePrivacyPolicy) {
        reqOnRevokePrivacyPolicy.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnRevokePrivacyPolicy.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnRevokePrivacyPolicy)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.204
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnRevokePrivacyPolicy(ReqOnRevokePrivacyPolicy reqOnRevokePrivacyPolicy, final Duration duration) {
        return fireOnRevokePrivacyPolicy(reqOnRevokePrivacyPolicy).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.205
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnRevokePrivacyPolicy(ReqOnRevokePrivacyPolicy.Arguments arguments) {
        ReqOnRevokePrivacyPolicy reqOnRevokePrivacyPolicy = new ReqOnRevokePrivacyPolicy();
        reqOnRevokePrivacyPolicy.setArguments(arguments);
        reqOnRevokePrivacyPolicy.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnRevokePrivacyPolicy.setComponent(str);
        }
        return fireOnRevokePrivacyPolicy(reqOnRevokePrivacyPolicy);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnRevokePrivacyPolicy(ReqOnRevokePrivacyPolicy.Arguments arguments, final Duration duration) {
        return fireOnRevokePrivacyPolicy(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.206
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> firePowerSavingModeChanged(ReqPowerSavingModeChanged reqPowerSavingModeChanged) {
        reqPowerSavingModeChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqPowerSavingModeChanged.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqPowerSavingModeChanged)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.207
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> firePowerSavingModeChanged(ReqPowerSavingModeChanged reqPowerSavingModeChanged, final Duration duration) {
        return firePowerSavingModeChanged(reqPowerSavingModeChanged).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.208
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
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

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> firePowerSavingModeChanged(ReqPowerSavingModeChanged.Arguments arguments, final Duration duration) {
        return firePowerSavingModeChanged(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.209
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetClusterMapFocus(ReqSetClusterMapFocus reqSetClusterMapFocus) {
        reqSetClusterMapFocus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetClusterMapFocus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetClusterMapFocus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.210
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetClusterMapFocus(ReqSetClusterMapFocus reqSetClusterMapFocus, final Duration duration) {
        return fireSetClusterMapFocus(reqSetClusterMapFocus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.211
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetClusterMapFocus(ReqSetClusterMapFocus.Arguments arguments) {
        ReqSetClusterMapFocus reqSetClusterMapFocus = new ReqSetClusterMapFocus();
        reqSetClusterMapFocus.setArguments(arguments);
        reqSetClusterMapFocus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetClusterMapFocus.setComponent(str);
        }
        return fireSetClusterMapFocus(reqSetClusterMapFocus);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetClusterMapFocus(ReqSetClusterMapFocus.Arguments arguments, final Duration duration) {
        return fireSetClusterMapFocus(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.212
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireClusterViewModeChanged(ReqClusterViewModeChanged reqClusterViewModeChanged) {
        reqClusterViewModeChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqClusterViewModeChanged.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqClusterViewModeChanged)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.213
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireClusterViewModeChanged(ReqClusterViewModeChanged reqClusterViewModeChanged, final Duration duration) {
        return fireClusterViewModeChanged(reqClusterViewModeChanged).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.214
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireClusterViewModeChanged(ReqClusterViewModeChanged.Arguments arguments) {
        ReqClusterViewModeChanged reqClusterViewModeChanged = new ReqClusterViewModeChanged();
        reqClusterViewModeChanged.setArguments(arguments);
        reqClusterViewModeChanged.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqClusterViewModeChanged.setComponent(str);
        }
        return fireClusterViewModeChanged(reqClusterViewModeChanged);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireClusterViewModeChanged(ReqClusterViewModeChanged.Arguments arguments, final Duration duration) {
        return fireClusterViewModeChanged(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.215
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResShowPoiIconsFromVR> requestShowPoiIconsFromVR(ReqShowPoiIconsFromVR reqShowPoiIconsFromVR) {
        if (reqShowPoiIconsFromVR.getResponse() == null) {
            reqShowPoiIconsFromVR.setResponse(new ResShowPoiIconsFromVR());
        }
        String str = this.component;
        if (str != null) {
            reqShowPoiIconsFromVR.setComponent(str);
            reqShowPoiIconsFromVR.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqShowPoiIconsFromVR));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResShowPoiIconsFromVR> requestShowPoiIconsFromVR(ReqShowPoiIconsFromVR reqShowPoiIconsFromVR, final Duration duration) {
        return requestShowPoiIconsFromVR(reqShowPoiIconsFromVR).thenCompose((Function<? super ResShowPoiIconsFromVR, ? extends CompletionStage<U>>) new Function<ResShowPoiIconsFromVR, CompletableFuture<ResShowPoiIconsFromVR>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.216
            @Override // java9.util.function.Function
            public CompletableFuture<ResShowPoiIconsFromVR> apply(ResShowPoiIconsFromVR resShowPoiIconsFromVR) {
                return CommonUtil.delayAsync(resShowPoiIconsFromVR, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResShowPoiIconsFromVR.Arguments> requestShowPoiIconsFromVR(ReqShowPoiIconsFromVR.Arguments arguments) {
        ReqShowPoiIconsFromVR reqShowPoiIconsFromVR = new ReqShowPoiIconsFromVR();
        reqShowPoiIconsFromVR.setResponse(new ResShowPoiIconsFromVR());
        reqShowPoiIconsFromVR.setArguments(arguments);
        return requestShowPoiIconsFromVR(reqShowPoiIconsFromVR).thenApply((Function<? super ResShowPoiIconsFromVR, ? extends U>) new Function<ResShowPoiIconsFromVR, ResShowPoiIconsFromVR.Arguments>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.217
            @Override // java9.util.function.Function
            public ResShowPoiIconsFromVR.Arguments apply(ResShowPoiIconsFromVR resShowPoiIconsFromVR) {
                return resShowPoiIconsFromVR.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResShowPoiIconsFromVR.Arguments> requestShowPoiIconsFromVR(ReqShowPoiIconsFromVR.Arguments arguments, final Duration duration) {
        return requestShowPoiIconsFromVR(arguments).thenCompose((Function<? super ResShowPoiIconsFromVR.Arguments, ? extends CompletionStage<U>>) new Function<ResShowPoiIconsFromVR.Arguments, CompletableFuture<ResShowPoiIconsFromVR.Arguments>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.218
            @Override // java9.util.function.Function
            public CompletableFuture<ResShowPoiIconsFromVR.Arguments> apply(ResShowPoiIconsFromVR.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireShowPoiIconsFromVR(ReqShowPoiIconsFromVR reqShowPoiIconsFromVR) {
        reqShowPoiIconsFromVR.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqShowPoiIconsFromVR.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqShowPoiIconsFromVR)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.219
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireShowPoiIconsFromVR(ReqShowPoiIconsFromVR reqShowPoiIconsFromVR, final Duration duration) {
        return fireShowPoiIconsFromVR(reqShowPoiIconsFromVR).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.220
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireShowPoiIconsFromVR(ReqShowPoiIconsFromVR.Arguments arguments) {
        ReqShowPoiIconsFromVR reqShowPoiIconsFromVR = new ReqShowPoiIconsFromVR();
        reqShowPoiIconsFromVR.setArguments(arguments);
        reqShowPoiIconsFromVR.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqShowPoiIconsFromVR.setComponent(str);
        }
        return fireShowPoiIconsFromVR(reqShowPoiIconsFromVR);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireShowPoiIconsFromVR(ReqShowPoiIconsFromVR.Arguments arguments, final Duration duration) {
        return fireShowPoiIconsFromVR(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.221
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResHidePoiIconsFromVR> requestHidePoiIconsFromVR(ReqHidePoiIconsFromVR reqHidePoiIconsFromVR) {
        if (reqHidePoiIconsFromVR.getResponse() == null) {
            reqHidePoiIconsFromVR.setResponse(new ResHidePoiIconsFromVR());
        }
        String str = this.component;
        if (str != null) {
            reqHidePoiIconsFromVR.setComponent(str);
            reqHidePoiIconsFromVR.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqHidePoiIconsFromVR));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResHidePoiIconsFromVR> requestHidePoiIconsFromVR(ReqHidePoiIconsFromVR reqHidePoiIconsFromVR, final Duration duration) {
        return requestHidePoiIconsFromVR(reqHidePoiIconsFromVR).thenCompose((Function<? super ResHidePoiIconsFromVR, ? extends CompletionStage<U>>) new Function<ResHidePoiIconsFromVR, CompletableFuture<ResHidePoiIconsFromVR>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.222
            @Override // java9.util.function.Function
            public CompletableFuture<ResHidePoiIconsFromVR> apply(ResHidePoiIconsFromVR resHidePoiIconsFromVR) {
                return CommonUtil.delayAsync(resHidePoiIconsFromVR, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResHidePoiIconsFromVR> requestHidePoiIconsFromVR() {
        return requestHidePoiIconsFromVR(new ReqHidePoiIconsFromVR());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResHidePoiIconsFromVR> requestHidePoiIconsFromVR(Duration duration) {
        return requestHidePoiIconsFromVR(new ReqHidePoiIconsFromVR(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireHidePoiIconsFromVR(ReqHidePoiIconsFromVR reqHidePoiIconsFromVR) {
        reqHidePoiIconsFromVR.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqHidePoiIconsFromVR.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqHidePoiIconsFromVR)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.223
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireHidePoiIconsFromVR(ReqHidePoiIconsFromVR reqHidePoiIconsFromVR, final Duration duration) {
        return fireHidePoiIconsFromVR(reqHidePoiIconsFromVR).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.224
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireHidePoiIconsFromVR() {
        return fireHidePoiIconsFromVR(new ReqHidePoiIconsFromVR());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireHidePoiIconsFromVR(final Duration duration) {
        return fireHidePoiIconsFromVR(new ReqHidePoiIconsFromVR()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.225
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetPrivacyPolicyFullPath(ReqSetPrivacyPolicyFullPath reqSetPrivacyPolicyFullPath) {
        reqSetPrivacyPolicyFullPath.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetPrivacyPolicyFullPath.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetPrivacyPolicyFullPath)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.226
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetPrivacyPolicyFullPath(ReqSetPrivacyPolicyFullPath reqSetPrivacyPolicyFullPath, final Duration duration) {
        return fireSetPrivacyPolicyFullPath(reqSetPrivacyPolicyFullPath).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.227
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetPrivacyPolicyFullPath(ReqSetPrivacyPolicyFullPath.Arguments arguments) {
        ReqSetPrivacyPolicyFullPath reqSetPrivacyPolicyFullPath = new ReqSetPrivacyPolicyFullPath();
        reqSetPrivacyPolicyFullPath.setArguments(arguments);
        reqSetPrivacyPolicyFullPath.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetPrivacyPolicyFullPath.setComponent(str);
        }
        return fireSetPrivacyPolicyFullPath(reqSetPrivacyPolicyFullPath);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetPrivacyPolicyFullPath(ReqSetPrivacyPolicyFullPath.Arguments arguments, final Duration duration) {
        return fireSetPrivacyPolicyFullPath(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.228
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetDesktopMode(ReqSetDesktopMode reqSetDesktopMode) {
        reqSetDesktopMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetDesktopMode.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetDesktopMode)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.229
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetDesktopMode(ReqSetDesktopMode reqSetDesktopMode, final Duration duration) {
        return fireSetDesktopMode(reqSetDesktopMode).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.230
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetDesktopMode(ReqSetDesktopMode.Arguments arguments) {
        ReqSetDesktopMode reqSetDesktopMode = new ReqSetDesktopMode();
        reqSetDesktopMode.setArguments(arguments);
        reqSetDesktopMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetDesktopMode.setComponent(str);
        }
        return fireSetDesktopMode(reqSetDesktopMode);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetDesktopMode(ReqSetDesktopMode.Arguments arguments, final Duration duration) {
        return fireSetDesktopMode(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.231
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnAuthInfo(ReqOnAuthInfo reqOnAuthInfo) {
        reqOnAuthInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnAuthInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnAuthInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.232
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnAuthInfo(ReqOnAuthInfo reqOnAuthInfo, final Duration duration) {
        return fireOnAuthInfo(reqOnAuthInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.233
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnAuthInfo(ReqOnAuthInfo.Arguments arguments) {
        ReqOnAuthInfo reqOnAuthInfo = new ReqOnAuthInfo();
        reqOnAuthInfo.setArguments(arguments);
        reqOnAuthInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnAuthInfo.setComponent(str);
        }
        return fireOnAuthInfo(reqOnAuthInfo);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnAuthInfo(ReqOnAuthInfo.Arguments arguments, final Duration duration) {
        return fireOnAuthInfo(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.234
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnVerifyInfo(ReqOnVerifyInfo reqOnVerifyInfo) {
        reqOnVerifyInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnVerifyInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOnVerifyInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.235
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnVerifyInfo(ReqOnVerifyInfo reqOnVerifyInfo, final Duration duration) {
        return fireOnVerifyInfo(reqOnVerifyInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.236
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnVerifyInfo(ReqOnVerifyInfo.Arguments arguments) {
        ReqOnVerifyInfo reqOnVerifyInfo = new ReqOnVerifyInfo();
        reqOnVerifyInfo.setArguments(arguments);
        reqOnVerifyInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOnVerifyInfo.setComponent(str);
        }
        return fireOnVerifyInfo(reqOnVerifyInfo);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireOnVerifyInfo(ReqOnVerifyInfo.Arguments arguments, final Duration duration) {
        return fireOnVerifyInfo(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.237
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetPlatformVersion(ReqSetPlatformVersion reqSetPlatformVersion) {
        reqSetPlatformVersion.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetPlatformVersion.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetPlatformVersion)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.238
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetPlatformVersion(ReqSetPlatformVersion reqSetPlatformVersion, final Duration duration) {
        return fireSetPlatformVersion(reqSetPlatformVersion).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.239
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetPlatformVersion(ReqSetPlatformVersion.Arguments arguments) {
        ReqSetPlatformVersion reqSetPlatformVersion = new ReqSetPlatformVersion();
        reqSetPlatformVersion.setArguments(arguments);
        reqSetPlatformVersion.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetPlatformVersion.setComponent(str);
        }
        return fireSetPlatformVersion(reqSetPlatformVersion);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetPlatformVersion(ReqSetPlatformVersion.Arguments arguments, final Duration duration) {
        return fireSetPlatformVersion(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.240
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetMobileEquipmentID(ReqSetMobileEquipmentID reqSetMobileEquipmentID) {
        reqSetMobileEquipmentID.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetMobileEquipmentID.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetMobileEquipmentID)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.241
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetMobileEquipmentID(ReqSetMobileEquipmentID reqSetMobileEquipmentID, final Duration duration) {
        return fireSetMobileEquipmentID(reqSetMobileEquipmentID).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.242
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetMobileEquipmentID(ReqSetMobileEquipmentID.Arguments arguments) {
        ReqSetMobileEquipmentID reqSetMobileEquipmentID = new ReqSetMobileEquipmentID();
        reqSetMobileEquipmentID.setArguments(arguments);
        reqSetMobileEquipmentID.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetMobileEquipmentID.setComponent(str);
        }
        return fireSetMobileEquipmentID(reqSetMobileEquipmentID);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireSetMobileEquipmentID(ReqSetMobileEquipmentID.Arguments arguments, final Duration duration) {
        return fireSetMobileEquipmentID(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.243
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate) {
        if (reqGetGuidanceInfoUpdate.getResponse() == null) {
            reqGetGuidanceInfoUpdate.setResponse(new ResGetGuidanceInfoUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetGuidanceInfoUpdate.setComponent(str);
            reqGetGuidanceInfoUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGuidanceInfoUpdate));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate, final Duration duration) {
        return requestGetGuidanceInfoUpdate(reqGetGuidanceInfoUpdate).thenCompose((Function<? super ResGetGuidanceInfoUpdate, ? extends CompletionStage<U>>) new Function<ResGetGuidanceInfoUpdate, CompletableFuture<ResGetGuidanceInfoUpdate>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.244
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetGuidanceInfoUpdate> apply(ResGetGuidanceInfoUpdate resGetGuidanceInfoUpdate) {
                return CommonUtil.delayAsync(resGetGuidanceInfoUpdate, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate() {
        return requestGetGuidanceInfoUpdate(new ReqGetGuidanceInfoUpdate());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate(Duration duration) {
        return requestGetGuidanceInfoUpdate(new ReqGetGuidanceInfoUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate) {
        reqGetGuidanceInfoUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetGuidanceInfoUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGuidanceInfoUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.245
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate, final Duration duration) {
        return fireGetGuidanceInfoUpdate(reqGetGuidanceInfoUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.246
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceInfoUpdate() {
        return fireGetGuidanceInfoUpdate(new ReqGetGuidanceInfoUpdate());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceInfoUpdate(final Duration duration) {
        return fireGetGuidanceInfoUpdate(new ReqGetGuidanceInfoUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.247
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
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

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetGuidanceStatus> requestGetGuidanceStatus(ReqGetGuidanceStatus reqGetGuidanceStatus, final Duration duration) {
        return requestGetGuidanceStatus(reqGetGuidanceStatus).thenCompose((Function<? super ResGetGuidanceStatus, ? extends CompletionStage<U>>) new Function<ResGetGuidanceStatus, CompletableFuture<ResGetGuidanceStatus>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.248
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetGuidanceStatus> apply(ResGetGuidanceStatus resGetGuidanceStatus) {
                return CommonUtil.delayAsync(resGetGuidanceStatus, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetGuidanceStatus> requestGetGuidanceStatus() {
        return requestGetGuidanceStatus(new ReqGetGuidanceStatus());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetGuidanceStatus> requestGetGuidanceStatus(Duration duration) {
        return requestGetGuidanceStatus(new ReqGetGuidanceStatus(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceStatus(ReqGetGuidanceStatus reqGetGuidanceStatus) {
        reqGetGuidanceStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetGuidanceStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGuidanceStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.249
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceStatus(ReqGetGuidanceStatus reqGetGuidanceStatus, final Duration duration) {
        return fireGetGuidanceStatus(reqGetGuidanceStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.250
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceStatus() {
        return fireGetGuidanceStatus(new ReqGetGuidanceStatus());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceStatus(final Duration duration) {
        return fireGetGuidanceStatus(new ReqGetGuidanceStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.251
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
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

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetReportLocationInfo> requestGetReportLocationInfo(ReqGetReportLocationInfo reqGetReportLocationInfo, final Duration duration) {
        return requestGetReportLocationInfo(reqGetReportLocationInfo).thenCompose((Function<? super ResGetReportLocationInfo, ? extends CompletionStage<U>>) new Function<ResGetReportLocationInfo, CompletableFuture<ResGetReportLocationInfo>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.252
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetReportLocationInfo> apply(ResGetReportLocationInfo resGetReportLocationInfo) {
                return CommonUtil.delayAsync(resGetReportLocationInfo, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetReportLocationInfo> requestGetReportLocationInfo() {
        return requestGetReportLocationInfo(new ReqGetReportLocationInfo());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetReportLocationInfo> requestGetReportLocationInfo(Duration duration) {
        return requestGetReportLocationInfo(new ReqGetReportLocationInfo(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetReportLocationInfo(ReqGetReportLocationInfo reqGetReportLocationInfo) {
        reqGetReportLocationInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetReportLocationInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetReportLocationInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.253
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetReportLocationInfo(ReqGetReportLocationInfo reqGetReportLocationInfo, final Duration duration) {
        return fireGetReportLocationInfo(reqGetReportLocationInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.254
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetReportLocationInfo() {
        return fireGetReportLocationInfo(new ReqGetReportLocationInfo());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetReportLocationInfo(final Duration duration) {
        return fireGetReportLocationInfo(new ReqGetReportLocationInfo()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.255
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetReqGPSInfo> requestGetReqGPSInfo(ReqGetReqGPSInfo reqGetReqGPSInfo) {
        if (reqGetReqGPSInfo.getResponse() == null) {
            reqGetReqGPSInfo.setResponse(new ResGetReqGPSInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetReqGPSInfo.setComponent(str);
            reqGetReqGPSInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetReqGPSInfo));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetReqGPSInfo> requestGetReqGPSInfo(ReqGetReqGPSInfo reqGetReqGPSInfo, final Duration duration) {
        return requestGetReqGPSInfo(reqGetReqGPSInfo).thenCompose((Function<? super ResGetReqGPSInfo, ? extends CompletionStage<U>>) new Function<ResGetReqGPSInfo, CompletableFuture<ResGetReqGPSInfo>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.256
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetReqGPSInfo> apply(ResGetReqGPSInfo resGetReqGPSInfo) {
                return CommonUtil.delayAsync(resGetReqGPSInfo, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetReqGPSInfo> requestGetReqGPSInfo() {
        return requestGetReqGPSInfo(new ReqGetReqGPSInfo());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetReqGPSInfo> requestGetReqGPSInfo(Duration duration) {
        return requestGetReqGPSInfo(new ReqGetReqGPSInfo(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetReqGPSInfo(ReqGetReqGPSInfo reqGetReqGPSInfo) {
        reqGetReqGPSInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetReqGPSInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetReqGPSInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.257
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetReqGPSInfo(ReqGetReqGPSInfo reqGetReqGPSInfo, final Duration duration) {
        return fireGetReqGPSInfo(reqGetReqGPSInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.258
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetReqGPSInfo() {
        return fireGetReqGPSInfo(new ReqGetReqGPSInfo());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetReqGPSInfo(final Duration duration) {
        return fireGetReqGPSInfo(new ReqGetReqGPSInfo()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.259
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetDelPrivacyPolicyInfo> requestGetDelPrivacyPolicyInfo(ReqGetDelPrivacyPolicyInfo reqGetDelPrivacyPolicyInfo) {
        if (reqGetDelPrivacyPolicyInfo.getResponse() == null) {
            reqGetDelPrivacyPolicyInfo.setResponse(new ResGetDelPrivacyPolicyInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetDelPrivacyPolicyInfo.setComponent(str);
            reqGetDelPrivacyPolicyInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDelPrivacyPolicyInfo));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetDelPrivacyPolicyInfo> requestGetDelPrivacyPolicyInfo(ReqGetDelPrivacyPolicyInfo reqGetDelPrivacyPolicyInfo, final Duration duration) {
        return requestGetDelPrivacyPolicyInfo(reqGetDelPrivacyPolicyInfo).thenCompose((Function<? super ResGetDelPrivacyPolicyInfo, ? extends CompletionStage<U>>) new Function<ResGetDelPrivacyPolicyInfo, CompletableFuture<ResGetDelPrivacyPolicyInfo>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.260
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetDelPrivacyPolicyInfo> apply(ResGetDelPrivacyPolicyInfo resGetDelPrivacyPolicyInfo) {
                return CommonUtil.delayAsync(resGetDelPrivacyPolicyInfo, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetDelPrivacyPolicyInfo> requestGetDelPrivacyPolicyInfo() {
        return requestGetDelPrivacyPolicyInfo(new ReqGetDelPrivacyPolicyInfo());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetDelPrivacyPolicyInfo> requestGetDelPrivacyPolicyInfo(Duration duration) {
        return requestGetDelPrivacyPolicyInfo(new ReqGetDelPrivacyPolicyInfo(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetDelPrivacyPolicyInfo(ReqGetDelPrivacyPolicyInfo reqGetDelPrivacyPolicyInfo) {
        reqGetDelPrivacyPolicyInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetDelPrivacyPolicyInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDelPrivacyPolicyInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.261
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetDelPrivacyPolicyInfo(ReqGetDelPrivacyPolicyInfo reqGetDelPrivacyPolicyInfo, final Duration duration) {
        return fireGetDelPrivacyPolicyInfo(reqGetDelPrivacyPolicyInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.262
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetDelPrivacyPolicyInfo() {
        return fireGetDelPrivacyPolicyInfo(new ReqGetDelPrivacyPolicyInfo());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetDelPrivacyPolicyInfo(final Duration duration) {
        return fireGetDelPrivacyPolicyInfo(new ReqGetDelPrivacyPolicyInfo()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.263
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetUpdatePrivacyPolicySyncStatus> requestGetUpdatePrivacyPolicySyncStatus(ReqGetUpdatePrivacyPolicySyncStatus reqGetUpdatePrivacyPolicySyncStatus) {
        if (reqGetUpdatePrivacyPolicySyncStatus.getResponse() == null) {
            reqGetUpdatePrivacyPolicySyncStatus.setResponse(new ResGetUpdatePrivacyPolicySyncStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetUpdatePrivacyPolicySyncStatus.setComponent(str);
            reqGetUpdatePrivacyPolicySyncStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetUpdatePrivacyPolicySyncStatus));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetUpdatePrivacyPolicySyncStatus> requestGetUpdatePrivacyPolicySyncStatus(ReqGetUpdatePrivacyPolicySyncStatus reqGetUpdatePrivacyPolicySyncStatus, final Duration duration) {
        return requestGetUpdatePrivacyPolicySyncStatus(reqGetUpdatePrivacyPolicySyncStatus).thenCompose((Function<? super ResGetUpdatePrivacyPolicySyncStatus, ? extends CompletionStage<U>>) new Function<ResGetUpdatePrivacyPolicySyncStatus, CompletableFuture<ResGetUpdatePrivacyPolicySyncStatus>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.264
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetUpdatePrivacyPolicySyncStatus> apply(ResGetUpdatePrivacyPolicySyncStatus resGetUpdatePrivacyPolicySyncStatus) {
                return CommonUtil.delayAsync(resGetUpdatePrivacyPolicySyncStatus, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetUpdatePrivacyPolicySyncStatus> requestGetUpdatePrivacyPolicySyncStatus() {
        return requestGetUpdatePrivacyPolicySyncStatus(new ReqGetUpdatePrivacyPolicySyncStatus());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetUpdatePrivacyPolicySyncStatus> requestGetUpdatePrivacyPolicySyncStatus(Duration duration) {
        return requestGetUpdatePrivacyPolicySyncStatus(new ReqGetUpdatePrivacyPolicySyncStatus(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetUpdatePrivacyPolicySyncStatus(ReqGetUpdatePrivacyPolicySyncStatus reqGetUpdatePrivacyPolicySyncStatus) {
        reqGetUpdatePrivacyPolicySyncStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetUpdatePrivacyPolicySyncStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetUpdatePrivacyPolicySyncStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.265
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetUpdatePrivacyPolicySyncStatus(ReqGetUpdatePrivacyPolicySyncStatus reqGetUpdatePrivacyPolicySyncStatus, final Duration duration) {
        return fireGetUpdatePrivacyPolicySyncStatus(reqGetUpdatePrivacyPolicySyncStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.266
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetUpdatePrivacyPolicySyncStatus() {
        return fireGetUpdatePrivacyPolicySyncStatus(new ReqGetUpdatePrivacyPolicySyncStatus());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetUpdatePrivacyPolicySyncStatus(final Duration duration) {
        return fireGetUpdatePrivacyPolicySyncStatus(new ReqGetUpdatePrivacyPolicySyncStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.267
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetRevokePrivacyPolicy> requestGetRevokePrivacyPolicy(ReqGetRevokePrivacyPolicy reqGetRevokePrivacyPolicy) {
        if (reqGetRevokePrivacyPolicy.getResponse() == null) {
            reqGetRevokePrivacyPolicy.setResponse(new ResGetRevokePrivacyPolicy());
        }
        String str = this.component;
        if (str != null) {
            reqGetRevokePrivacyPolicy.setComponent(str);
            reqGetRevokePrivacyPolicy.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRevokePrivacyPolicy));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetRevokePrivacyPolicy> requestGetRevokePrivacyPolicy(ReqGetRevokePrivacyPolicy reqGetRevokePrivacyPolicy, final Duration duration) {
        return requestGetRevokePrivacyPolicy(reqGetRevokePrivacyPolicy).thenCompose((Function<? super ResGetRevokePrivacyPolicy, ? extends CompletionStage<U>>) new Function<ResGetRevokePrivacyPolicy, CompletableFuture<ResGetRevokePrivacyPolicy>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.268
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRevokePrivacyPolicy> apply(ResGetRevokePrivacyPolicy resGetRevokePrivacyPolicy) {
                return CommonUtil.delayAsync(resGetRevokePrivacyPolicy, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetRevokePrivacyPolicy> requestGetRevokePrivacyPolicy() {
        return requestGetRevokePrivacyPolicy(new ReqGetRevokePrivacyPolicy());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetRevokePrivacyPolicy> requestGetRevokePrivacyPolicy(Duration duration) {
        return requestGetRevokePrivacyPolicy(new ReqGetRevokePrivacyPolicy(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetRevokePrivacyPolicy(ReqGetRevokePrivacyPolicy reqGetRevokePrivacyPolicy) {
        reqGetRevokePrivacyPolicy.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRevokePrivacyPolicy.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRevokePrivacyPolicy)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.269
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetRevokePrivacyPolicy(ReqGetRevokePrivacyPolicy reqGetRevokePrivacyPolicy, final Duration duration) {
        return fireGetRevokePrivacyPolicy(reqGetRevokePrivacyPolicy).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.270
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetRevokePrivacyPolicy() {
        return fireGetRevokePrivacyPolicy(new ReqGetRevokePrivacyPolicy());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetRevokePrivacyPolicy(final Duration duration) {
        return fireGetRevokePrivacyPolicy(new ReqGetRevokePrivacyPolicy()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.271
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetClusterViewMode> requestGetClusterViewMode(ReqGetClusterViewMode reqGetClusterViewMode) {
        if (reqGetClusterViewMode.getResponse() == null) {
            reqGetClusterViewMode.setResponse(new ResGetClusterViewMode());
        }
        String str = this.component;
        if (str != null) {
            reqGetClusterViewMode.setComponent(str);
            reqGetClusterViewMode.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetClusterViewMode));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetClusterViewMode> requestGetClusterViewMode(ReqGetClusterViewMode reqGetClusterViewMode, final Duration duration) {
        return requestGetClusterViewMode(reqGetClusterViewMode).thenCompose((Function<? super ResGetClusterViewMode, ? extends CompletionStage<U>>) new Function<ResGetClusterViewMode, CompletableFuture<ResGetClusterViewMode>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.272
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetClusterViewMode> apply(ResGetClusterViewMode resGetClusterViewMode) {
                return CommonUtil.delayAsync(resGetClusterViewMode, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetClusterViewMode> requestGetClusterViewMode() {
        return requestGetClusterViewMode(new ReqGetClusterViewMode());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetClusterViewMode> requestGetClusterViewMode(Duration duration) {
        return requestGetClusterViewMode(new ReqGetClusterViewMode(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetClusterViewMode(ReqGetClusterViewMode reqGetClusterViewMode) {
        reqGetClusterViewMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetClusterViewMode.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetClusterViewMode)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.273
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetClusterViewMode(ReqGetClusterViewMode reqGetClusterViewMode, final Duration duration) {
        return fireGetClusterViewMode(reqGetClusterViewMode).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.274
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetClusterViewMode() {
        return fireGetClusterViewMode(new ReqGetClusterViewMode());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetClusterViewMode(final Duration duration) {
        return fireGetClusterViewMode(new ReqGetClusterViewMode()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.275
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetSwitchDesktopMode> requestGetSwitchDesktopMode(ReqGetSwitchDesktopMode reqGetSwitchDesktopMode) {
        if (reqGetSwitchDesktopMode.getResponse() == null) {
            reqGetSwitchDesktopMode.setResponse(new ResGetSwitchDesktopMode());
        }
        String str = this.component;
        if (str != null) {
            reqGetSwitchDesktopMode.setComponent(str);
            reqGetSwitchDesktopMode.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSwitchDesktopMode));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetSwitchDesktopMode> requestGetSwitchDesktopMode(ReqGetSwitchDesktopMode reqGetSwitchDesktopMode, final Duration duration) {
        return requestGetSwitchDesktopMode(reqGetSwitchDesktopMode).thenCompose((Function<? super ResGetSwitchDesktopMode, ? extends CompletionStage<U>>) new Function<ResGetSwitchDesktopMode, CompletableFuture<ResGetSwitchDesktopMode>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.276
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetSwitchDesktopMode> apply(ResGetSwitchDesktopMode resGetSwitchDesktopMode) {
                return CommonUtil.delayAsync(resGetSwitchDesktopMode, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetSwitchDesktopMode> requestGetSwitchDesktopMode() {
        return requestGetSwitchDesktopMode(new ReqGetSwitchDesktopMode());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetSwitchDesktopMode> requestGetSwitchDesktopMode(Duration duration) {
        return requestGetSwitchDesktopMode(new ReqGetSwitchDesktopMode(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetSwitchDesktopMode(ReqGetSwitchDesktopMode reqGetSwitchDesktopMode) {
        reqGetSwitchDesktopMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetSwitchDesktopMode.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSwitchDesktopMode)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.277
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetSwitchDesktopMode(ReqGetSwitchDesktopMode reqGetSwitchDesktopMode, final Duration duration) {
        return fireGetSwitchDesktopMode(reqGetSwitchDesktopMode).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.278
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetSwitchDesktopMode() {
        return fireGetSwitchDesktopMode(new ReqGetSwitchDesktopMode());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetSwitchDesktopMode(final Duration duration) {
        return fireGetSwitchDesktopMode(new ReqGetSwitchDesktopMode()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.279
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetIsNaviHMIFullScreenPage> requestGetIsNaviHMIFullScreenPage(ReqGetIsNaviHMIFullScreenPage reqGetIsNaviHMIFullScreenPage) {
        if (reqGetIsNaviHMIFullScreenPage.getResponse() == null) {
            reqGetIsNaviHMIFullScreenPage.setResponse(new ResGetIsNaviHMIFullScreenPage());
        }
        String str = this.component;
        if (str != null) {
            reqGetIsNaviHMIFullScreenPage.setComponent(str);
            reqGetIsNaviHMIFullScreenPage.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetIsNaviHMIFullScreenPage));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetIsNaviHMIFullScreenPage> requestGetIsNaviHMIFullScreenPage(ReqGetIsNaviHMIFullScreenPage reqGetIsNaviHMIFullScreenPage, final Duration duration) {
        return requestGetIsNaviHMIFullScreenPage(reqGetIsNaviHMIFullScreenPage).thenCompose((Function<? super ResGetIsNaviHMIFullScreenPage, ? extends CompletionStage<U>>) new Function<ResGetIsNaviHMIFullScreenPage, CompletableFuture<ResGetIsNaviHMIFullScreenPage>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.280
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetIsNaviHMIFullScreenPage> apply(ResGetIsNaviHMIFullScreenPage resGetIsNaviHMIFullScreenPage) {
                return CommonUtil.delayAsync(resGetIsNaviHMIFullScreenPage, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetIsNaviHMIFullScreenPage> requestGetIsNaviHMIFullScreenPage() {
        return requestGetIsNaviHMIFullScreenPage(new ReqGetIsNaviHMIFullScreenPage());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetIsNaviHMIFullScreenPage> requestGetIsNaviHMIFullScreenPage(Duration duration) {
        return requestGetIsNaviHMIFullScreenPage(new ReqGetIsNaviHMIFullScreenPage(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetIsNaviHMIFullScreenPage(ReqGetIsNaviHMIFullScreenPage reqGetIsNaviHMIFullScreenPage) {
        reqGetIsNaviHMIFullScreenPage.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetIsNaviHMIFullScreenPage.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetIsNaviHMIFullScreenPage)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.281
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetIsNaviHMIFullScreenPage(ReqGetIsNaviHMIFullScreenPage reqGetIsNaviHMIFullScreenPage, final Duration duration) {
        return fireGetIsNaviHMIFullScreenPage(reqGetIsNaviHMIFullScreenPage).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.282
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetIsNaviHMIFullScreenPage() {
        return fireGetIsNaviHMIFullScreenPage(new ReqGetIsNaviHMIFullScreenPage());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetIsNaviHMIFullScreenPage(final Duration duration) {
        return fireGetIsNaviHMIFullScreenPage(new ReqGetIsNaviHMIFullScreenPage()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.283
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetAuthInfo> requestGetAuthInfo(ReqGetAuthInfo reqGetAuthInfo) {
        if (reqGetAuthInfo.getResponse() == null) {
            reqGetAuthInfo.setResponse(new ResGetAuthInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetAuthInfo.setComponent(str);
            reqGetAuthInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetAuthInfo));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetAuthInfo> requestGetAuthInfo(ReqGetAuthInfo reqGetAuthInfo, final Duration duration) {
        return requestGetAuthInfo(reqGetAuthInfo).thenCompose((Function<? super ResGetAuthInfo, ? extends CompletionStage<U>>) new Function<ResGetAuthInfo, CompletableFuture<ResGetAuthInfo>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.284
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetAuthInfo> apply(ResGetAuthInfo resGetAuthInfo) {
                return CommonUtil.delayAsync(resGetAuthInfo, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetAuthInfo> requestGetAuthInfo() {
        return requestGetAuthInfo(new ReqGetAuthInfo());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetAuthInfo> requestGetAuthInfo(Duration duration) {
        return requestGetAuthInfo(new ReqGetAuthInfo(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetAuthInfo(ReqGetAuthInfo reqGetAuthInfo) {
        reqGetAuthInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetAuthInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetAuthInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.285
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetAuthInfo(ReqGetAuthInfo reqGetAuthInfo, final Duration duration) {
        return fireGetAuthInfo(reqGetAuthInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.286
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetAuthInfo() {
        return fireGetAuthInfo(new ReqGetAuthInfo());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetAuthInfo(final Duration duration) {
        return fireGetAuthInfo(new ReqGetAuthInfo()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.287
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetVerifyInfo> requestGetVerifyInfo(ReqGetVerifyInfo reqGetVerifyInfo) {
        if (reqGetVerifyInfo.getResponse() == null) {
            reqGetVerifyInfo.setResponse(new ResGetVerifyInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetVerifyInfo.setComponent(str);
            reqGetVerifyInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetVerifyInfo));
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetVerifyInfo> requestGetVerifyInfo(ReqGetVerifyInfo reqGetVerifyInfo, final Duration duration) {
        return requestGetVerifyInfo(reqGetVerifyInfo).thenCompose((Function<? super ResGetVerifyInfo, ? extends CompletionStage<U>>) new Function<ResGetVerifyInfo, CompletableFuture<ResGetVerifyInfo>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.288
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetVerifyInfo> apply(ResGetVerifyInfo resGetVerifyInfo) {
                return CommonUtil.delayAsync(resGetVerifyInfo, duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetVerifyInfo> requestGetVerifyInfo() {
        return requestGetVerifyInfo(new ReqGetVerifyInfo());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<ResGetVerifyInfo> requestGetVerifyInfo(Duration duration) {
        return requestGetVerifyInfo(new ReqGetVerifyInfo(), duration);
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetVerifyInfo(ReqGetVerifyInfo reqGetVerifyInfo) {
        reqGetVerifyInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetVerifyInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetVerifyInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.289
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return BydNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetVerifyInfo(ReqGetVerifyInfo reqGetVerifyInfo, final Duration duration) {
        return fireGetVerifyInfo(reqGetVerifyInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.290
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetVerifyInfo() {
        return fireGetVerifyInfo(new ReqGetVerifyInfo());
    }

    @Override // api.requests.com.neusoft.byd.bydnavapi.IBydNavApiRequests
    public CompletableFuture<Void> fireGetVerifyInfo(final Duration duration) {
        return fireGetVerifyInfo(new ReqGetVerifyInfo()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.byd.bydnavapi.BydNavApiRequests.291
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, BydNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }
}
