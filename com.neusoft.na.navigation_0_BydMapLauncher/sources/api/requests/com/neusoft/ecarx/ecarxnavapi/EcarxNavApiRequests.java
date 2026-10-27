package api.requests.com.neusoft.ecarx.ecarxnavapi;

import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqAddFavorite;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqAlongTheWaySearch;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqChangeThemeResource;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqEditFavoritePois;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqGetFavoritePois;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqGetFrontTraffic;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqGetHighwayExitInfo;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqGetHistoryPois;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqGetTrafficSummaryInfo;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqGoFavorite;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqHasLogin;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqMapZoomInOut;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqModifyNaviVia;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqRefuelState;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqRemoveFavorite;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqRemoveWaypoint;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqRequestGeoDecoder;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqRequestRoutePlanOrNavi;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqResumeGuidance;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqSearchAround;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqSearchByKeyword;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqSearchBySETELCategory;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqSelectRoute;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqSetFavoriteList;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqSetMapViewMode;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqSetNaviMuteMode;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqSetRoutePrefer;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqSetSETELData;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqSetSETELDetailData;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqSetSETELServiceStatus;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqShowMyLocation;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqSpecialPoiNavi;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqSwitchTraffic;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqUpdateFavorite;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqVRMsgCommand;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResAlongTheWaySearch;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResBackToMap;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResCancelNavi;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResChangeThemeResource;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResEditFavoritePois;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResExitMap;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetADAreaUpdate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetArrivedDest;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetAvailableRestoreGuidance;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetCommutePush;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetDayNightStatusUpdate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetFavoritePois;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetFavoritesInfoUpdate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetFrontTraffic;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetGetFavoriteList;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetGetLoginStatus;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetGuidanceInfoUpdate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetGuideInfo;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetHighwayExitInfo;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetHighwayInfoUpdate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetHistoryPois;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetLanesInfoUpdate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetMapStatusUpdate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetMuteModeUpdate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetNaviMuteMode;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetNaviStatusUpdate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetRequestAddFavorite;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetRequestRemoveFavorite;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetRequestSETELData;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetRequestSETELDetail;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetRequestSETELServiceStatus;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetRequestUpdateFavorite;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetRouteInfo;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetRouteInfoUpdate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetRouteSelectedUpdate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetRouteViewStatusUpdate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetScaleStatus;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetServiceStatusUpdate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetSwitchVRCommand;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetTrafficConditionUpdate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetTrafficStatusUpdate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetTrafficSummaryInfo;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetVRMsgCommandUpdate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetViewModeUpdate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetZoomStatusUpdate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGoFavorite;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResLaunchMap;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResMapZoomInOut;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResModifyNaviVia;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResNaviFullView;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResOpenDestinationSearch;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResRemoveWaypoint;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResRequestGeoDecoder;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResRequestRoutePlanOrNavi;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResResumeGuidance;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResSearchAround;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResSearchByKeyword;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResSearchBySETELCategory;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResSelectRoute;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResSetMapViewMode;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResSetNaviMuteMode;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResSetRoutePrefer;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResShowMyLocation;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResSpecialPoiNavi;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResSwitchTraffic;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResVRMsgCommand;
import com.neusoft.de.nts.OneCoreTest.communication.Communication;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.utils.CommonUtil;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java9.util.concurrent.CompletableFuture;
import java9.util.concurrent.CompletionStage;
import java9.util.function.Function;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes.dex */
public class EcarxNavApiRequests implements IEcarxNavApiRequests {
    private final String component;
    private final ICommunicationContextUtil<?, ?, ?> contextUtil;
    private static final Logger logger = Logger.getLogger((Class<?>) EcarxNavApiRequests.class);
    private static final CompletableFuture<Void> completedVoidFuture = CompletableFuture.completedFuture(null);

    public EcarxNavApiRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = null;
    }

    public EcarxNavApiRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil, String str) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = str;
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResLaunchMap> requestLaunchMap(ReqLaunchMap reqLaunchMap) {
        if (reqLaunchMap.getResponse() == null) {
            reqLaunchMap.setResponse(new ResLaunchMap());
        }
        String str = this.component;
        if (str != null) {
            reqLaunchMap.setComponent(str);
            reqLaunchMap.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqLaunchMap));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResLaunchMap> requestLaunchMap(ReqLaunchMap reqLaunchMap, final Duration duration) {
        return requestLaunchMap(reqLaunchMap).thenCompose((Function<? super ResLaunchMap, ? extends CompletionStage<U>>) new Function<ResLaunchMap, CompletableFuture<ResLaunchMap>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.1
            @Override // java9.util.function.Function
            public CompletableFuture<ResLaunchMap> apply(ResLaunchMap resLaunchMap) {
                return CommonUtil.delayAsync(resLaunchMap, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResLaunchMap> requestLaunchMap() {
        return requestLaunchMap(new ReqLaunchMap());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResLaunchMap> requestLaunchMap(Duration duration) {
        return requestLaunchMap(new ReqLaunchMap(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireLaunchMap(ReqLaunchMap reqLaunchMap) {
        reqLaunchMap.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqLaunchMap.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqLaunchMap)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.2
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireLaunchMap(ReqLaunchMap reqLaunchMap, final Duration duration) {
        return fireLaunchMap(reqLaunchMap).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.3
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireLaunchMap() {
        return fireLaunchMap(new ReqLaunchMap());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireLaunchMap(final Duration duration) {
        return fireLaunchMap(new ReqLaunchMap()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.4
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResBackToMap> requestBackToMap(ReqBackToMap reqBackToMap) {
        if (reqBackToMap.getResponse() == null) {
            reqBackToMap.setResponse(new ResBackToMap());
        }
        String str = this.component;
        if (str != null) {
            reqBackToMap.setComponent(str);
            reqBackToMap.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqBackToMap));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResBackToMap> requestBackToMap(ReqBackToMap reqBackToMap, final Duration duration) {
        return requestBackToMap(reqBackToMap).thenCompose((Function<? super ResBackToMap, ? extends CompletionStage<U>>) new Function<ResBackToMap, CompletableFuture<ResBackToMap>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.5
            @Override // java9.util.function.Function
            public CompletableFuture<ResBackToMap> apply(ResBackToMap resBackToMap) {
                return CommonUtil.delayAsync(resBackToMap, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResBackToMap> requestBackToMap() {
        return requestBackToMap(new ReqBackToMap());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResBackToMap> requestBackToMap(Duration duration) {
        return requestBackToMap(new ReqBackToMap(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireBackToMap(ReqBackToMap reqBackToMap) {
        reqBackToMap.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqBackToMap.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqBackToMap)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.6
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireBackToMap(ReqBackToMap reqBackToMap, final Duration duration) {
        return fireBackToMap(reqBackToMap).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.7
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireBackToMap() {
        return fireBackToMap(new ReqBackToMap());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireBackToMap(final Duration duration) {
        return fireBackToMap(new ReqBackToMap()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.8
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResExitMap> requestExitMap(ReqExitMap reqExitMap) {
        if (reqExitMap.getResponse() == null) {
            reqExitMap.setResponse(new ResExitMap());
        }
        String str = this.component;
        if (str != null) {
            reqExitMap.setComponent(str);
            reqExitMap.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqExitMap));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResExitMap> requestExitMap(ReqExitMap reqExitMap, final Duration duration) {
        return requestExitMap(reqExitMap).thenCompose((Function<? super ResExitMap, ? extends CompletionStage<U>>) new Function<ResExitMap, CompletableFuture<ResExitMap>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.9
            @Override // java9.util.function.Function
            public CompletableFuture<ResExitMap> apply(ResExitMap resExitMap) {
                return CommonUtil.delayAsync(resExitMap, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResExitMap> requestExitMap() {
        return requestExitMap(new ReqExitMap());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResExitMap> requestExitMap(Duration duration) {
        return requestExitMap(new ReqExitMap(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireExitMap(ReqExitMap reqExitMap) {
        reqExitMap.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqExitMap.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqExitMap)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.10
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireExitMap(ReqExitMap reqExitMap, final Duration duration) {
        return fireExitMap(reqExitMap).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.11
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireExitMap() {
        return fireExitMap(new ReqExitMap());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireExitMap(final Duration duration) {
        return fireExitMap(new ReqExitMap()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.12
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResCancelNavi> requestCancelNavi(ReqCancelNavi reqCancelNavi) {
        if (reqCancelNavi.getResponse() == null) {
            reqCancelNavi.setResponse(new ResCancelNavi());
        }
        String str = this.component;
        if (str != null) {
            reqCancelNavi.setComponent(str);
            reqCancelNavi.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqCancelNavi));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResCancelNavi> requestCancelNavi(ReqCancelNavi reqCancelNavi, final Duration duration) {
        return requestCancelNavi(reqCancelNavi).thenCompose((Function<? super ResCancelNavi, ? extends CompletionStage<U>>) new Function<ResCancelNavi, CompletableFuture<ResCancelNavi>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.13
            @Override // java9.util.function.Function
            public CompletableFuture<ResCancelNavi> apply(ResCancelNavi resCancelNavi) {
                return CommonUtil.delayAsync(resCancelNavi, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResCancelNavi> requestCancelNavi() {
        return requestCancelNavi(new ReqCancelNavi());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResCancelNavi> requestCancelNavi(Duration duration) {
        return requestCancelNavi(new ReqCancelNavi(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireCancelNavi(ReqCancelNavi reqCancelNavi) {
        reqCancelNavi.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqCancelNavi.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqCancelNavi)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.14
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireCancelNavi(ReqCancelNavi reqCancelNavi, final Duration duration) {
        return fireCancelNavi(reqCancelNavi).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.15
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireCancelNavi() {
        return fireCancelNavi(new ReqCancelNavi());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireCancelNavi(final Duration duration) {
        return fireCancelNavi(new ReqCancelNavi()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.16
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResOpenDestinationSearch> requestOpenDestinationSearch(ReqOpenDestinationSearch reqOpenDestinationSearch) {
        if (reqOpenDestinationSearch.getResponse() == null) {
            reqOpenDestinationSearch.setResponse(new ResOpenDestinationSearch());
        }
        String str = this.component;
        if (str != null) {
            reqOpenDestinationSearch.setComponent(str);
            reqOpenDestinationSearch.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOpenDestinationSearch));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResOpenDestinationSearch> requestOpenDestinationSearch(ReqOpenDestinationSearch reqOpenDestinationSearch, final Duration duration) {
        return requestOpenDestinationSearch(reqOpenDestinationSearch).thenCompose((Function<? super ResOpenDestinationSearch, ? extends CompletionStage<U>>) new Function<ResOpenDestinationSearch, CompletableFuture<ResOpenDestinationSearch>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.17
            @Override // java9.util.function.Function
            public CompletableFuture<ResOpenDestinationSearch> apply(ResOpenDestinationSearch resOpenDestinationSearch) {
                return CommonUtil.delayAsync(resOpenDestinationSearch, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResOpenDestinationSearch> requestOpenDestinationSearch() {
        return requestOpenDestinationSearch(new ReqOpenDestinationSearch());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResOpenDestinationSearch> requestOpenDestinationSearch(Duration duration) {
        return requestOpenDestinationSearch(new ReqOpenDestinationSearch(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireOpenDestinationSearch(ReqOpenDestinationSearch reqOpenDestinationSearch) {
        reqOpenDestinationSearch.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqOpenDestinationSearch.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqOpenDestinationSearch)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.18
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireOpenDestinationSearch(ReqOpenDestinationSearch reqOpenDestinationSearch, final Duration duration) {
        return fireOpenDestinationSearch(reqOpenDestinationSearch).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.19
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireOpenDestinationSearch() {
        return fireOpenDestinationSearch(new ReqOpenDestinationSearch());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireOpenDestinationSearch(final Duration duration) {
        return fireOpenDestinationSearch(new ReqOpenDestinationSearch()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.20
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResMapZoomInOut> requestMapZoomInOut(ReqMapZoomInOut reqMapZoomInOut) {
        if (reqMapZoomInOut.getResponse() == null) {
            reqMapZoomInOut.setResponse(new ResMapZoomInOut());
        }
        String str = this.component;
        if (str != null) {
            reqMapZoomInOut.setComponent(str);
            reqMapZoomInOut.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqMapZoomInOut));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResMapZoomInOut> requestMapZoomInOut(ReqMapZoomInOut reqMapZoomInOut, final Duration duration) {
        return requestMapZoomInOut(reqMapZoomInOut).thenCompose((Function<? super ResMapZoomInOut, ? extends CompletionStage<U>>) new Function<ResMapZoomInOut, CompletableFuture<ResMapZoomInOut>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.21
            @Override // java9.util.function.Function
            public CompletableFuture<ResMapZoomInOut> apply(ResMapZoomInOut resMapZoomInOut) {
                return CommonUtil.delayAsync(resMapZoomInOut, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResMapZoomInOut.Arguments> requestMapZoomInOut(ReqMapZoomInOut.Arguments arguments) {
        ReqMapZoomInOut reqMapZoomInOut = new ReqMapZoomInOut();
        reqMapZoomInOut.setResponse(new ResMapZoomInOut());
        reqMapZoomInOut.setArguments(arguments);
        return requestMapZoomInOut(reqMapZoomInOut).thenApply((Function<? super ResMapZoomInOut, ? extends U>) new Function<ResMapZoomInOut, ResMapZoomInOut.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.22
            @Override // java9.util.function.Function
            public ResMapZoomInOut.Arguments apply(ResMapZoomInOut resMapZoomInOut) {
                return resMapZoomInOut.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResMapZoomInOut.Arguments> requestMapZoomInOut(ReqMapZoomInOut.Arguments arguments, final Duration duration) {
        return requestMapZoomInOut(arguments).thenCompose((Function<? super ResMapZoomInOut.Arguments, ? extends CompletionStage<U>>) new Function<ResMapZoomInOut.Arguments, CompletableFuture<ResMapZoomInOut.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.23
            @Override // java9.util.function.Function
            public CompletableFuture<ResMapZoomInOut.Arguments> apply(ResMapZoomInOut.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireMapZoomInOut(ReqMapZoomInOut reqMapZoomInOut) {
        reqMapZoomInOut.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqMapZoomInOut.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqMapZoomInOut)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.24
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireMapZoomInOut(ReqMapZoomInOut reqMapZoomInOut, final Duration duration) {
        return fireMapZoomInOut(reqMapZoomInOut).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.25
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireMapZoomInOut(ReqMapZoomInOut.Arguments arguments) {
        ReqMapZoomInOut reqMapZoomInOut = new ReqMapZoomInOut();
        reqMapZoomInOut.setArguments(arguments);
        reqMapZoomInOut.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqMapZoomInOut.setComponent(str);
        }
        return fireMapZoomInOut(reqMapZoomInOut);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireMapZoomInOut(ReqMapZoomInOut.Arguments arguments, final Duration duration) {
        return fireMapZoomInOut(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.26
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSwitchTraffic> requestSwitchTraffic(ReqSwitchTraffic reqSwitchTraffic) {
        if (reqSwitchTraffic.getResponse() == null) {
            reqSwitchTraffic.setResponse(new ResSwitchTraffic());
        }
        String str = this.component;
        if (str != null) {
            reqSwitchTraffic.setComponent(str);
            reqSwitchTraffic.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSwitchTraffic));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSwitchTraffic> requestSwitchTraffic(ReqSwitchTraffic reqSwitchTraffic, final Duration duration) {
        return requestSwitchTraffic(reqSwitchTraffic).thenCompose((Function<? super ResSwitchTraffic, ? extends CompletionStage<U>>) new Function<ResSwitchTraffic, CompletableFuture<ResSwitchTraffic>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.27
            @Override // java9.util.function.Function
            public CompletableFuture<ResSwitchTraffic> apply(ResSwitchTraffic resSwitchTraffic) {
                return CommonUtil.delayAsync(resSwitchTraffic, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSwitchTraffic.Arguments> requestSwitchTraffic(ReqSwitchTraffic.Arguments arguments) {
        ReqSwitchTraffic reqSwitchTraffic = new ReqSwitchTraffic();
        reqSwitchTraffic.setResponse(new ResSwitchTraffic());
        reqSwitchTraffic.setArguments(arguments);
        return requestSwitchTraffic(reqSwitchTraffic).thenApply((Function<? super ResSwitchTraffic, ? extends U>) new Function<ResSwitchTraffic, ResSwitchTraffic.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.28
            @Override // java9.util.function.Function
            public ResSwitchTraffic.Arguments apply(ResSwitchTraffic resSwitchTraffic) {
                return resSwitchTraffic.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSwitchTraffic.Arguments> requestSwitchTraffic(ReqSwitchTraffic.Arguments arguments, final Duration duration) {
        return requestSwitchTraffic(arguments).thenCompose((Function<? super ResSwitchTraffic.Arguments, ? extends CompletionStage<U>>) new Function<ResSwitchTraffic.Arguments, CompletableFuture<ResSwitchTraffic.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.29
            @Override // java9.util.function.Function
            public CompletableFuture<ResSwitchTraffic.Arguments> apply(ResSwitchTraffic.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSwitchTraffic(ReqSwitchTraffic reqSwitchTraffic) {
        reqSwitchTraffic.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSwitchTraffic.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSwitchTraffic)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.30
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSwitchTraffic(ReqSwitchTraffic reqSwitchTraffic, final Duration duration) {
        return fireSwitchTraffic(reqSwitchTraffic).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.31
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSwitchTraffic(ReqSwitchTraffic.Arguments arguments) {
        ReqSwitchTraffic reqSwitchTraffic = new ReqSwitchTraffic();
        reqSwitchTraffic.setArguments(arguments);
        reqSwitchTraffic.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSwitchTraffic.setComponent(str);
        }
        return fireSwitchTraffic(reqSwitchTraffic);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSwitchTraffic(ReqSwitchTraffic.Arguments arguments, final Duration duration) {
        return fireSwitchTraffic(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.32
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSetMapViewMode> requestSetMapViewMode(ReqSetMapViewMode reqSetMapViewMode) {
        if (reqSetMapViewMode.getResponse() == null) {
            reqSetMapViewMode.setResponse(new ResSetMapViewMode());
        }
        String str = this.component;
        if (str != null) {
            reqSetMapViewMode.setComponent(str);
            reqSetMapViewMode.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetMapViewMode));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSetMapViewMode> requestSetMapViewMode(ReqSetMapViewMode reqSetMapViewMode, final Duration duration) {
        return requestSetMapViewMode(reqSetMapViewMode).thenCompose((Function<? super ResSetMapViewMode, ? extends CompletionStage<U>>) new Function<ResSetMapViewMode, CompletableFuture<ResSetMapViewMode>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.33
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetMapViewMode> apply(ResSetMapViewMode resSetMapViewMode) {
                return CommonUtil.delayAsync(resSetMapViewMode, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSetMapViewMode.Arguments> requestSetMapViewMode(ReqSetMapViewMode.Arguments arguments) {
        ReqSetMapViewMode reqSetMapViewMode = new ReqSetMapViewMode();
        reqSetMapViewMode.setResponse(new ResSetMapViewMode());
        reqSetMapViewMode.setArguments(arguments);
        return requestSetMapViewMode(reqSetMapViewMode).thenApply((Function<? super ResSetMapViewMode, ? extends U>) new Function<ResSetMapViewMode, ResSetMapViewMode.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.34
            @Override // java9.util.function.Function
            public ResSetMapViewMode.Arguments apply(ResSetMapViewMode resSetMapViewMode) {
                return resSetMapViewMode.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSetMapViewMode.Arguments> requestSetMapViewMode(ReqSetMapViewMode.Arguments arguments, final Duration duration) {
        return requestSetMapViewMode(arguments).thenCompose((Function<? super ResSetMapViewMode.Arguments, ? extends CompletionStage<U>>) new Function<ResSetMapViewMode.Arguments, CompletableFuture<ResSetMapViewMode.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.35
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetMapViewMode.Arguments> apply(ResSetMapViewMode.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetMapViewMode(ReqSetMapViewMode reqSetMapViewMode) {
        reqSetMapViewMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetMapViewMode.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetMapViewMode)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.36
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetMapViewMode(ReqSetMapViewMode reqSetMapViewMode, final Duration duration) {
        return fireSetMapViewMode(reqSetMapViewMode).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.37
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetMapViewMode(ReqSetMapViewMode.Arguments arguments) {
        ReqSetMapViewMode reqSetMapViewMode = new ReqSetMapViewMode();
        reqSetMapViewMode.setArguments(arguments);
        reqSetMapViewMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetMapViewMode.setComponent(str);
        }
        return fireSetMapViewMode(reqSetMapViewMode);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetMapViewMode(ReqSetMapViewMode.Arguments arguments, final Duration duration) {
        return fireSetMapViewMode(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.38
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResNaviFullView> requestNaviFullView(ReqNaviFullView reqNaviFullView) {
        if (reqNaviFullView.getResponse() == null) {
            reqNaviFullView.setResponse(new ResNaviFullView());
        }
        String str = this.component;
        if (str != null) {
            reqNaviFullView.setComponent(str);
            reqNaviFullView.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqNaviFullView));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResNaviFullView> requestNaviFullView(ReqNaviFullView reqNaviFullView, final Duration duration) {
        return requestNaviFullView(reqNaviFullView).thenCompose((Function<? super ResNaviFullView, ? extends CompletionStage<U>>) new Function<ResNaviFullView, CompletableFuture<ResNaviFullView>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.39
            @Override // java9.util.function.Function
            public CompletableFuture<ResNaviFullView> apply(ResNaviFullView resNaviFullView) {
                return CommonUtil.delayAsync(resNaviFullView, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResNaviFullView> requestNaviFullView() {
        return requestNaviFullView(new ReqNaviFullView());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResNaviFullView> requestNaviFullView(Duration duration) {
        return requestNaviFullView(new ReqNaviFullView(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireNaviFullView(ReqNaviFullView reqNaviFullView) {
        reqNaviFullView.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqNaviFullView.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqNaviFullView)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.40
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireNaviFullView(ReqNaviFullView reqNaviFullView, final Duration duration) {
        return fireNaviFullView(reqNaviFullView).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.41
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireNaviFullView() {
        return fireNaviFullView(new ReqNaviFullView());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireNaviFullView(final Duration duration) {
        return fireNaviFullView(new ReqNaviFullView()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.42
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResShowMyLocation> requestShowMyLocation(ReqShowMyLocation reqShowMyLocation) {
        if (reqShowMyLocation.getResponse() == null) {
            reqShowMyLocation.setResponse(new ResShowMyLocation());
        }
        String str = this.component;
        if (str != null) {
            reqShowMyLocation.setComponent(str);
            reqShowMyLocation.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqShowMyLocation));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResShowMyLocation> requestShowMyLocation(ReqShowMyLocation reqShowMyLocation, final Duration duration) {
        return requestShowMyLocation(reqShowMyLocation).thenCompose((Function<? super ResShowMyLocation, ? extends CompletionStage<U>>) new Function<ResShowMyLocation, CompletableFuture<ResShowMyLocation>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.43
            @Override // java9.util.function.Function
            public CompletableFuture<ResShowMyLocation> apply(ResShowMyLocation resShowMyLocation) {
                return CommonUtil.delayAsync(resShowMyLocation, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResShowMyLocation.Arguments> requestShowMyLocation(ReqShowMyLocation.Arguments arguments) {
        ReqShowMyLocation reqShowMyLocation = new ReqShowMyLocation();
        reqShowMyLocation.setResponse(new ResShowMyLocation());
        reqShowMyLocation.setArguments(arguments);
        return requestShowMyLocation(reqShowMyLocation).thenApply((Function<? super ResShowMyLocation, ? extends U>) new Function<ResShowMyLocation, ResShowMyLocation.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.44
            @Override // java9.util.function.Function
            public ResShowMyLocation.Arguments apply(ResShowMyLocation resShowMyLocation) {
                return resShowMyLocation.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResShowMyLocation.Arguments> requestShowMyLocation(ReqShowMyLocation.Arguments arguments, final Duration duration) {
        return requestShowMyLocation(arguments).thenCompose((Function<? super ResShowMyLocation.Arguments, ? extends CompletionStage<U>>) new Function<ResShowMyLocation.Arguments, CompletableFuture<ResShowMyLocation.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.45
            @Override // java9.util.function.Function
            public CompletableFuture<ResShowMyLocation.Arguments> apply(ResShowMyLocation.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireShowMyLocation(ReqShowMyLocation reqShowMyLocation) {
        reqShowMyLocation.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqShowMyLocation.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqShowMyLocation)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.46
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireShowMyLocation(ReqShowMyLocation reqShowMyLocation, final Duration duration) {
        return fireShowMyLocation(reqShowMyLocation).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.47
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireShowMyLocation(ReqShowMyLocation.Arguments arguments) {
        ReqShowMyLocation reqShowMyLocation = new ReqShowMyLocation();
        reqShowMyLocation.setArguments(arguments);
        reqShowMyLocation.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqShowMyLocation.setComponent(str);
        }
        return fireShowMyLocation(reqShowMyLocation);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireShowMyLocation(ReqShowMyLocation.Arguments arguments, final Duration duration) {
        return fireShowMyLocation(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.48
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSetRoutePrefer> requestSetRoutePrefer(ReqSetRoutePrefer reqSetRoutePrefer) {
        if (reqSetRoutePrefer.getResponse() == null) {
            reqSetRoutePrefer.setResponse(new ResSetRoutePrefer());
        }
        String str = this.component;
        if (str != null) {
            reqSetRoutePrefer.setComponent(str);
            reqSetRoutePrefer.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetRoutePrefer));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSetRoutePrefer> requestSetRoutePrefer(ReqSetRoutePrefer reqSetRoutePrefer, final Duration duration) {
        return requestSetRoutePrefer(reqSetRoutePrefer).thenCompose((Function<? super ResSetRoutePrefer, ? extends CompletionStage<U>>) new Function<ResSetRoutePrefer, CompletableFuture<ResSetRoutePrefer>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.49
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetRoutePrefer> apply(ResSetRoutePrefer resSetRoutePrefer) {
                return CommonUtil.delayAsync(resSetRoutePrefer, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSetRoutePrefer.Arguments> requestSetRoutePrefer(ReqSetRoutePrefer.Arguments arguments) {
        ReqSetRoutePrefer reqSetRoutePrefer = new ReqSetRoutePrefer();
        reqSetRoutePrefer.setResponse(new ResSetRoutePrefer());
        reqSetRoutePrefer.setArguments(arguments);
        return requestSetRoutePrefer(reqSetRoutePrefer).thenApply((Function<? super ResSetRoutePrefer, ? extends U>) new Function<ResSetRoutePrefer, ResSetRoutePrefer.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.50
            @Override // java9.util.function.Function
            public ResSetRoutePrefer.Arguments apply(ResSetRoutePrefer resSetRoutePrefer) {
                return resSetRoutePrefer.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSetRoutePrefer.Arguments> requestSetRoutePrefer(ReqSetRoutePrefer.Arguments arguments, final Duration duration) {
        return requestSetRoutePrefer(arguments).thenCompose((Function<? super ResSetRoutePrefer.Arguments, ? extends CompletionStage<U>>) new Function<ResSetRoutePrefer.Arguments, CompletableFuture<ResSetRoutePrefer.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.51
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetRoutePrefer.Arguments> apply(ResSetRoutePrefer.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetRoutePrefer(ReqSetRoutePrefer reqSetRoutePrefer) {
        reqSetRoutePrefer.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetRoutePrefer.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetRoutePrefer)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.52
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetRoutePrefer(ReqSetRoutePrefer reqSetRoutePrefer, final Duration duration) {
        return fireSetRoutePrefer(reqSetRoutePrefer).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.53
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetRoutePrefer(ReqSetRoutePrefer.Arguments arguments) {
        ReqSetRoutePrefer reqSetRoutePrefer = new ReqSetRoutePrefer();
        reqSetRoutePrefer.setArguments(arguments);
        reqSetRoutePrefer.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetRoutePrefer.setComponent(str);
        }
        return fireSetRoutePrefer(reqSetRoutePrefer);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetRoutePrefer(ReqSetRoutePrefer.Arguments arguments, final Duration duration) {
        return fireSetRoutePrefer(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.54
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSelectRoute> requestSelectRoute(ReqSelectRoute reqSelectRoute) {
        if (reqSelectRoute.getResponse() == null) {
            reqSelectRoute.setResponse(new ResSelectRoute());
        }
        String str = this.component;
        if (str != null) {
            reqSelectRoute.setComponent(str);
            reqSelectRoute.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSelectRoute));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSelectRoute> requestSelectRoute(ReqSelectRoute reqSelectRoute, final Duration duration) {
        return requestSelectRoute(reqSelectRoute).thenCompose((Function<? super ResSelectRoute, ? extends CompletionStage<U>>) new Function<ResSelectRoute, CompletableFuture<ResSelectRoute>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.55
            @Override // java9.util.function.Function
            public CompletableFuture<ResSelectRoute> apply(ResSelectRoute resSelectRoute) {
                return CommonUtil.delayAsync(resSelectRoute, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSelectRoute.Arguments> requestSelectRoute(ReqSelectRoute.Arguments arguments) {
        ReqSelectRoute reqSelectRoute = new ReqSelectRoute();
        reqSelectRoute.setResponse(new ResSelectRoute());
        reqSelectRoute.setArguments(arguments);
        return requestSelectRoute(reqSelectRoute).thenApply((Function<? super ResSelectRoute, ? extends U>) new Function<ResSelectRoute, ResSelectRoute.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.56
            @Override // java9.util.function.Function
            public ResSelectRoute.Arguments apply(ResSelectRoute resSelectRoute) {
                return resSelectRoute.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSelectRoute.Arguments> requestSelectRoute(ReqSelectRoute.Arguments arguments, final Duration duration) {
        return requestSelectRoute(arguments).thenCompose((Function<? super ResSelectRoute.Arguments, ? extends CompletionStage<U>>) new Function<ResSelectRoute.Arguments, CompletableFuture<ResSelectRoute.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.57
            @Override // java9.util.function.Function
            public CompletableFuture<ResSelectRoute.Arguments> apply(ResSelectRoute.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSelectRoute(ReqSelectRoute reqSelectRoute) {
        reqSelectRoute.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSelectRoute.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSelectRoute)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.58
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSelectRoute(ReqSelectRoute reqSelectRoute, final Duration duration) {
        return fireSelectRoute(reqSelectRoute).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.59
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSelectRoute(ReqSelectRoute.Arguments arguments) {
        ReqSelectRoute reqSelectRoute = new ReqSelectRoute();
        reqSelectRoute.setArguments(arguments);
        reqSelectRoute.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSelectRoute.setComponent(str);
        }
        return fireSelectRoute(reqSelectRoute);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSelectRoute(ReqSelectRoute.Arguments arguments, final Duration duration) {
        return fireSelectRoute(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.60
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResEditFavoritePois> requestEditFavoritePois(ReqEditFavoritePois reqEditFavoritePois) {
        if (reqEditFavoritePois.getResponse() == null) {
            reqEditFavoritePois.setResponse(new ResEditFavoritePois());
        }
        String str = this.component;
        if (str != null) {
            reqEditFavoritePois.setComponent(str);
            reqEditFavoritePois.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqEditFavoritePois));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResEditFavoritePois> requestEditFavoritePois(ReqEditFavoritePois reqEditFavoritePois, final Duration duration) {
        return requestEditFavoritePois(reqEditFavoritePois).thenCompose((Function<? super ResEditFavoritePois, ? extends CompletionStage<U>>) new Function<ResEditFavoritePois, CompletableFuture<ResEditFavoritePois>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.61
            @Override // java9.util.function.Function
            public CompletableFuture<ResEditFavoritePois> apply(ResEditFavoritePois resEditFavoritePois) {
                return CommonUtil.delayAsync(resEditFavoritePois, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResEditFavoritePois.Arguments> requestEditFavoritePois(ReqEditFavoritePois.Arguments arguments) {
        ReqEditFavoritePois reqEditFavoritePois = new ReqEditFavoritePois();
        reqEditFavoritePois.setResponse(new ResEditFavoritePois());
        reqEditFavoritePois.setArguments(arguments);
        return requestEditFavoritePois(reqEditFavoritePois).thenApply((Function<? super ResEditFavoritePois, ? extends U>) new Function<ResEditFavoritePois, ResEditFavoritePois.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.62
            @Override // java9.util.function.Function
            public ResEditFavoritePois.Arguments apply(ResEditFavoritePois resEditFavoritePois) {
                return resEditFavoritePois.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResEditFavoritePois.Arguments> requestEditFavoritePois(ReqEditFavoritePois.Arguments arguments, final Duration duration) {
        return requestEditFavoritePois(arguments).thenCompose((Function<? super ResEditFavoritePois.Arguments, ? extends CompletionStage<U>>) new Function<ResEditFavoritePois.Arguments, CompletableFuture<ResEditFavoritePois.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.63
            @Override // java9.util.function.Function
            public CompletableFuture<ResEditFavoritePois.Arguments> apply(ResEditFavoritePois.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireEditFavoritePois(ReqEditFavoritePois reqEditFavoritePois) {
        reqEditFavoritePois.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqEditFavoritePois.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqEditFavoritePois)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.64
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireEditFavoritePois(ReqEditFavoritePois reqEditFavoritePois, final Duration duration) {
        return fireEditFavoritePois(reqEditFavoritePois).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.65
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireEditFavoritePois(ReqEditFavoritePois.Arguments arguments) {
        ReqEditFavoritePois reqEditFavoritePois = new ReqEditFavoritePois();
        reqEditFavoritePois.setArguments(arguments);
        reqEditFavoritePois.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqEditFavoritePois.setComponent(str);
        }
        return fireEditFavoritePois(reqEditFavoritePois);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireEditFavoritePois(ReqEditFavoritePois.Arguments arguments, final Duration duration) {
        return fireEditFavoritePois(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.66
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetFavoritePois> requestGetFavoritePois(ReqGetFavoritePois reqGetFavoritePois) {
        if (reqGetFavoritePois.getResponse() == null) {
            reqGetFavoritePois.setResponse(new ResGetFavoritePois());
        }
        String str = this.component;
        if (str != null) {
            reqGetFavoritePois.setComponent(str);
            reqGetFavoritePois.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetFavoritePois));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetFavoritePois> requestGetFavoritePois(ReqGetFavoritePois reqGetFavoritePois, final Duration duration) {
        return requestGetFavoritePois(reqGetFavoritePois).thenCompose((Function<? super ResGetFavoritePois, ? extends CompletionStage<U>>) new Function<ResGetFavoritePois, CompletableFuture<ResGetFavoritePois>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.67
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetFavoritePois> apply(ResGetFavoritePois resGetFavoritePois) {
                return CommonUtil.delayAsync(resGetFavoritePois, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetFavoritePois.Arguments> requestGetFavoritePois(ReqGetFavoritePois.Arguments arguments) {
        ReqGetFavoritePois reqGetFavoritePois = new ReqGetFavoritePois();
        reqGetFavoritePois.setResponse(new ResGetFavoritePois());
        reqGetFavoritePois.setArguments(arguments);
        return requestGetFavoritePois(reqGetFavoritePois).thenApply((Function<? super ResGetFavoritePois, ? extends U>) new Function<ResGetFavoritePois, ResGetFavoritePois.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.68
            @Override // java9.util.function.Function
            public ResGetFavoritePois.Arguments apply(ResGetFavoritePois resGetFavoritePois) {
                return resGetFavoritePois.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetFavoritePois.Arguments> requestGetFavoritePois(ReqGetFavoritePois.Arguments arguments, final Duration duration) {
        return requestGetFavoritePois(arguments).thenCompose((Function<? super ResGetFavoritePois.Arguments, ? extends CompletionStage<U>>) new Function<ResGetFavoritePois.Arguments, CompletableFuture<ResGetFavoritePois.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.69
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetFavoritePois.Arguments> apply(ResGetFavoritePois.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetFavoritePois(ReqGetFavoritePois reqGetFavoritePois) {
        reqGetFavoritePois.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetFavoritePois.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetFavoritePois)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.70
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetFavoritePois(ReqGetFavoritePois reqGetFavoritePois, final Duration duration) {
        return fireGetFavoritePois(reqGetFavoritePois).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.71
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetFavoritePois(ReqGetFavoritePois.Arguments arguments) {
        ReqGetFavoritePois reqGetFavoritePois = new ReqGetFavoritePois();
        reqGetFavoritePois.setArguments(arguments);
        reqGetFavoritePois.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetFavoritePois.setComponent(str);
        }
        return fireGetFavoritePois(reqGetFavoritePois);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetFavoritePois(ReqGetFavoritePois.Arguments arguments, final Duration duration) {
        return fireGetFavoritePois(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.72
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
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

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetFrontTraffic> requestGetFrontTraffic(ReqGetFrontTraffic reqGetFrontTraffic, final Duration duration) {
        return requestGetFrontTraffic(reqGetFrontTraffic).thenCompose((Function<? super ResGetFrontTraffic, ? extends CompletionStage<U>>) new Function<ResGetFrontTraffic, CompletableFuture<ResGetFrontTraffic>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.73
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetFrontTraffic> apply(ResGetFrontTraffic resGetFrontTraffic) {
                return CommonUtil.delayAsync(resGetFrontTraffic, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetFrontTraffic.Arguments> requestGetFrontTraffic(ReqGetFrontTraffic.Arguments arguments) {
        ReqGetFrontTraffic reqGetFrontTraffic = new ReqGetFrontTraffic();
        reqGetFrontTraffic.setResponse(new ResGetFrontTraffic());
        reqGetFrontTraffic.setArguments(arguments);
        return requestGetFrontTraffic(reqGetFrontTraffic).thenApply((Function<? super ResGetFrontTraffic, ? extends U>) new Function<ResGetFrontTraffic, ResGetFrontTraffic.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.74
            @Override // java9.util.function.Function
            public ResGetFrontTraffic.Arguments apply(ResGetFrontTraffic resGetFrontTraffic) {
                return resGetFrontTraffic.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetFrontTraffic.Arguments> requestGetFrontTraffic(ReqGetFrontTraffic.Arguments arguments, final Duration duration) {
        return requestGetFrontTraffic(arguments).thenCompose((Function<? super ResGetFrontTraffic.Arguments, ? extends CompletionStage<U>>) new Function<ResGetFrontTraffic.Arguments, CompletableFuture<ResGetFrontTraffic.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.75
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetFrontTraffic.Arguments> apply(ResGetFrontTraffic.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetFrontTraffic(ReqGetFrontTraffic reqGetFrontTraffic) {
        reqGetFrontTraffic.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetFrontTraffic.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetFrontTraffic)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.76
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetFrontTraffic(ReqGetFrontTraffic reqGetFrontTraffic, final Duration duration) {
        return fireGetFrontTraffic(reqGetFrontTraffic).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.77
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetFrontTraffic(ReqGetFrontTraffic.Arguments arguments) {
        ReqGetFrontTraffic reqGetFrontTraffic = new ReqGetFrontTraffic();
        reqGetFrontTraffic.setArguments(arguments);
        reqGetFrontTraffic.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetFrontTraffic.setComponent(str);
        }
        return fireGetFrontTraffic(reqGetFrontTraffic);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetFrontTraffic(ReqGetFrontTraffic.Arguments arguments, final Duration duration) {
        return fireGetFrontTraffic(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.78
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetTrafficSummaryInfo> requestGetTrafficSummaryInfo(ReqGetTrafficSummaryInfo reqGetTrafficSummaryInfo) {
        if (reqGetTrafficSummaryInfo.getResponse() == null) {
            reqGetTrafficSummaryInfo.setResponse(new ResGetTrafficSummaryInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetTrafficSummaryInfo.setComponent(str);
            reqGetTrafficSummaryInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetTrafficSummaryInfo));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetTrafficSummaryInfo> requestGetTrafficSummaryInfo(ReqGetTrafficSummaryInfo reqGetTrafficSummaryInfo, final Duration duration) {
        return requestGetTrafficSummaryInfo(reqGetTrafficSummaryInfo).thenCompose((Function<? super ResGetTrafficSummaryInfo, ? extends CompletionStage<U>>) new Function<ResGetTrafficSummaryInfo, CompletableFuture<ResGetTrafficSummaryInfo>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.79
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetTrafficSummaryInfo> apply(ResGetTrafficSummaryInfo resGetTrafficSummaryInfo) {
                return CommonUtil.delayAsync(resGetTrafficSummaryInfo, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetTrafficSummaryInfo.Arguments> requestGetTrafficSummaryInfo(ReqGetTrafficSummaryInfo.Arguments arguments) {
        ReqGetTrafficSummaryInfo reqGetTrafficSummaryInfo = new ReqGetTrafficSummaryInfo();
        reqGetTrafficSummaryInfo.setResponse(new ResGetTrafficSummaryInfo());
        reqGetTrafficSummaryInfo.setArguments(arguments);
        return requestGetTrafficSummaryInfo(reqGetTrafficSummaryInfo).thenApply((Function<? super ResGetTrafficSummaryInfo, ? extends U>) new Function<ResGetTrafficSummaryInfo, ResGetTrafficSummaryInfo.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.80
            @Override // java9.util.function.Function
            public ResGetTrafficSummaryInfo.Arguments apply(ResGetTrafficSummaryInfo resGetTrafficSummaryInfo) {
                return resGetTrafficSummaryInfo.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetTrafficSummaryInfo.Arguments> requestGetTrafficSummaryInfo(ReqGetTrafficSummaryInfo.Arguments arguments, final Duration duration) {
        return requestGetTrafficSummaryInfo(arguments).thenCompose((Function<? super ResGetTrafficSummaryInfo.Arguments, ? extends CompletionStage<U>>) new Function<ResGetTrafficSummaryInfo.Arguments, CompletableFuture<ResGetTrafficSummaryInfo.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.81
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetTrafficSummaryInfo.Arguments> apply(ResGetTrafficSummaryInfo.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetTrafficSummaryInfo(ReqGetTrafficSummaryInfo reqGetTrafficSummaryInfo) {
        reqGetTrafficSummaryInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetTrafficSummaryInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetTrafficSummaryInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.82
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetTrafficSummaryInfo(ReqGetTrafficSummaryInfo reqGetTrafficSummaryInfo, final Duration duration) {
        return fireGetTrafficSummaryInfo(reqGetTrafficSummaryInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.83
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetTrafficSummaryInfo(ReqGetTrafficSummaryInfo.Arguments arguments) {
        ReqGetTrafficSummaryInfo reqGetTrafficSummaryInfo = new ReqGetTrafficSummaryInfo();
        reqGetTrafficSummaryInfo.setArguments(arguments);
        reqGetTrafficSummaryInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetTrafficSummaryInfo.setComponent(str);
        }
        return fireGetTrafficSummaryInfo(reqGetTrafficSummaryInfo);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetTrafficSummaryInfo(ReqGetTrafficSummaryInfo.Arguments arguments, final Duration duration) {
        return fireGetTrafficSummaryInfo(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.84
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetGuideInfo> requestGetGuideInfo(ReqGetGuideInfo reqGetGuideInfo) {
        if (reqGetGuideInfo.getResponse() == null) {
            reqGetGuideInfo.setResponse(new ResGetGuideInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetGuideInfo.setComponent(str);
            reqGetGuideInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGuideInfo));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetGuideInfo> requestGetGuideInfo(ReqGetGuideInfo reqGetGuideInfo, final Duration duration) {
        return requestGetGuideInfo(reqGetGuideInfo).thenCompose((Function<? super ResGetGuideInfo, ? extends CompletionStage<U>>) new Function<ResGetGuideInfo, CompletableFuture<ResGetGuideInfo>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.85
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetGuideInfo> apply(ResGetGuideInfo resGetGuideInfo) {
                return CommonUtil.delayAsync(resGetGuideInfo, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetGuideInfo> requestGetGuideInfo() {
        return requestGetGuideInfo(new ReqGetGuideInfo());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetGuideInfo> requestGetGuideInfo(Duration duration) {
        return requestGetGuideInfo(new ReqGetGuideInfo(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetGuideInfo(ReqGetGuideInfo reqGetGuideInfo) {
        reqGetGuideInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetGuideInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGuideInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.86
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetGuideInfo(ReqGetGuideInfo reqGetGuideInfo, final Duration duration) {
        return fireGetGuideInfo(reqGetGuideInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.87
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetGuideInfo() {
        return fireGetGuideInfo(new ReqGetGuideInfo());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetGuideInfo(final Duration duration) {
        return fireGetGuideInfo(new ReqGetGuideInfo()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.88
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetHighwayExitInfo> requestGetHighwayExitInfo(ReqGetHighwayExitInfo reqGetHighwayExitInfo) {
        if (reqGetHighwayExitInfo.getResponse() == null) {
            reqGetHighwayExitInfo.setResponse(new ResGetHighwayExitInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetHighwayExitInfo.setComponent(str);
            reqGetHighwayExitInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetHighwayExitInfo));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetHighwayExitInfo> requestGetHighwayExitInfo(ReqGetHighwayExitInfo reqGetHighwayExitInfo, final Duration duration) {
        return requestGetHighwayExitInfo(reqGetHighwayExitInfo).thenCompose((Function<? super ResGetHighwayExitInfo, ? extends CompletionStage<U>>) new Function<ResGetHighwayExitInfo, CompletableFuture<ResGetHighwayExitInfo>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.89
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetHighwayExitInfo> apply(ResGetHighwayExitInfo resGetHighwayExitInfo) {
                return CommonUtil.delayAsync(resGetHighwayExitInfo, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetHighwayExitInfo.Arguments> requestGetHighwayExitInfo(ReqGetHighwayExitInfo.Arguments arguments) {
        ReqGetHighwayExitInfo reqGetHighwayExitInfo = new ReqGetHighwayExitInfo();
        reqGetHighwayExitInfo.setResponse(new ResGetHighwayExitInfo());
        reqGetHighwayExitInfo.setArguments(arguments);
        return requestGetHighwayExitInfo(reqGetHighwayExitInfo).thenApply((Function<? super ResGetHighwayExitInfo, ? extends U>) new Function<ResGetHighwayExitInfo, ResGetHighwayExitInfo.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.90
            @Override // java9.util.function.Function
            public ResGetHighwayExitInfo.Arguments apply(ResGetHighwayExitInfo resGetHighwayExitInfo) {
                return resGetHighwayExitInfo.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetHighwayExitInfo.Arguments> requestGetHighwayExitInfo(ReqGetHighwayExitInfo.Arguments arguments, final Duration duration) {
        return requestGetHighwayExitInfo(arguments).thenCompose((Function<? super ResGetHighwayExitInfo.Arguments, ? extends CompletionStage<U>>) new Function<ResGetHighwayExitInfo.Arguments, CompletableFuture<ResGetHighwayExitInfo.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.91
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetHighwayExitInfo.Arguments> apply(ResGetHighwayExitInfo.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetHighwayExitInfo(ReqGetHighwayExitInfo reqGetHighwayExitInfo) {
        reqGetHighwayExitInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetHighwayExitInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetHighwayExitInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.92
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetHighwayExitInfo(ReqGetHighwayExitInfo reqGetHighwayExitInfo, final Duration duration) {
        return fireGetHighwayExitInfo(reqGetHighwayExitInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.93
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetHighwayExitInfo(ReqGetHighwayExitInfo.Arguments arguments) {
        ReqGetHighwayExitInfo reqGetHighwayExitInfo = new ReqGetHighwayExitInfo();
        reqGetHighwayExitInfo.setArguments(arguments);
        reqGetHighwayExitInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetHighwayExitInfo.setComponent(str);
        }
        return fireGetHighwayExitInfo(reqGetHighwayExitInfo);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetHighwayExitInfo(ReqGetHighwayExitInfo.Arguments arguments, final Duration duration) {
        return fireGetHighwayExitInfo(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.94
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetHistoryPois> requestGetHistoryPois(ReqGetHistoryPois reqGetHistoryPois) {
        if (reqGetHistoryPois.getResponse() == null) {
            reqGetHistoryPois.setResponse(new ResGetHistoryPois());
        }
        String str = this.component;
        if (str != null) {
            reqGetHistoryPois.setComponent(str);
            reqGetHistoryPois.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetHistoryPois));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetHistoryPois> requestGetHistoryPois(ReqGetHistoryPois reqGetHistoryPois, final Duration duration) {
        return requestGetHistoryPois(reqGetHistoryPois).thenCompose((Function<? super ResGetHistoryPois, ? extends CompletionStage<U>>) new Function<ResGetHistoryPois, CompletableFuture<ResGetHistoryPois>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.95
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetHistoryPois> apply(ResGetHistoryPois resGetHistoryPois) {
                return CommonUtil.delayAsync(resGetHistoryPois, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetHistoryPois.Arguments> requestGetHistoryPois(ReqGetHistoryPois.Arguments arguments) {
        ReqGetHistoryPois reqGetHistoryPois = new ReqGetHistoryPois();
        reqGetHistoryPois.setResponse(new ResGetHistoryPois());
        reqGetHistoryPois.setArguments(arguments);
        return requestGetHistoryPois(reqGetHistoryPois).thenApply((Function<? super ResGetHistoryPois, ? extends U>) new Function<ResGetHistoryPois, ResGetHistoryPois.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.96
            @Override // java9.util.function.Function
            public ResGetHistoryPois.Arguments apply(ResGetHistoryPois resGetHistoryPois) {
                return resGetHistoryPois.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetHistoryPois.Arguments> requestGetHistoryPois(ReqGetHistoryPois.Arguments arguments, final Duration duration) {
        return requestGetHistoryPois(arguments).thenCompose((Function<? super ResGetHistoryPois.Arguments, ? extends CompletionStage<U>>) new Function<ResGetHistoryPois.Arguments, CompletableFuture<ResGetHistoryPois.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.97
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetHistoryPois.Arguments> apply(ResGetHistoryPois.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetHistoryPois(ReqGetHistoryPois reqGetHistoryPois) {
        reqGetHistoryPois.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetHistoryPois.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetHistoryPois)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.98
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetHistoryPois(ReqGetHistoryPois reqGetHistoryPois, final Duration duration) {
        return fireGetHistoryPois(reqGetHistoryPois).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.99
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetHistoryPois(ReqGetHistoryPois.Arguments arguments) {
        ReqGetHistoryPois reqGetHistoryPois = new ReqGetHistoryPois();
        reqGetHistoryPois.setArguments(arguments);
        reqGetHistoryPois.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetHistoryPois.setComponent(str);
        }
        return fireGetHistoryPois(reqGetHistoryPois);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetHistoryPois(ReqGetHistoryPois.Arguments arguments, final Duration duration) {
        return fireGetHistoryPois(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.100
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRouteInfo> requestGetRouteInfo(ReqGetRouteInfo reqGetRouteInfo) {
        if (reqGetRouteInfo.getResponse() == null) {
            reqGetRouteInfo.setResponse(new ResGetRouteInfo());
        }
        String str = this.component;
        if (str != null) {
            reqGetRouteInfo.setComponent(str);
            reqGetRouteInfo.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRouteInfo));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRouteInfo> requestGetRouteInfo(ReqGetRouteInfo reqGetRouteInfo, final Duration duration) {
        return requestGetRouteInfo(reqGetRouteInfo).thenCompose((Function<? super ResGetRouteInfo, ? extends CompletionStage<U>>) new Function<ResGetRouteInfo, CompletableFuture<ResGetRouteInfo>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.101
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRouteInfo> apply(ResGetRouteInfo resGetRouteInfo) {
                return CommonUtil.delayAsync(resGetRouteInfo, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRouteInfo> requestGetRouteInfo() {
        return requestGetRouteInfo(new ReqGetRouteInfo());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRouteInfo> requestGetRouteInfo(Duration duration) {
        return requestGetRouteInfo(new ReqGetRouteInfo(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRouteInfo(ReqGetRouteInfo reqGetRouteInfo) {
        reqGetRouteInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRouteInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRouteInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.102
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRouteInfo(ReqGetRouteInfo reqGetRouteInfo, final Duration duration) {
        return fireGetRouteInfo(reqGetRouteInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.103
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRouteInfo() {
        return fireGetRouteInfo(new ReqGetRouteInfo());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRouteInfo(final Duration duration) {
        return fireGetRouteInfo(new ReqGetRouteInfo()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.104
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSearchBySETELCategory> requestSearchBySETELCategory(ReqSearchBySETELCategory reqSearchBySETELCategory) {
        if (reqSearchBySETELCategory.getResponse() == null) {
            reqSearchBySETELCategory.setResponse(new ResSearchBySETELCategory());
        }
        String str = this.component;
        if (str != null) {
            reqSearchBySETELCategory.setComponent(str);
            reqSearchBySETELCategory.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSearchBySETELCategory));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSearchBySETELCategory> requestSearchBySETELCategory(ReqSearchBySETELCategory reqSearchBySETELCategory, final Duration duration) {
        return requestSearchBySETELCategory(reqSearchBySETELCategory).thenCompose((Function<? super ResSearchBySETELCategory, ? extends CompletionStage<U>>) new Function<ResSearchBySETELCategory, CompletableFuture<ResSearchBySETELCategory>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.105
            @Override // java9.util.function.Function
            public CompletableFuture<ResSearchBySETELCategory> apply(ResSearchBySETELCategory resSearchBySETELCategory) {
                return CommonUtil.delayAsync(resSearchBySETELCategory, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSearchBySETELCategory.Arguments> requestSearchBySETELCategory(ReqSearchBySETELCategory.Arguments arguments) {
        ReqSearchBySETELCategory reqSearchBySETELCategory = new ReqSearchBySETELCategory();
        reqSearchBySETELCategory.setResponse(new ResSearchBySETELCategory());
        reqSearchBySETELCategory.setArguments(arguments);
        return requestSearchBySETELCategory(reqSearchBySETELCategory).thenApply((Function<? super ResSearchBySETELCategory, ? extends U>) new Function<ResSearchBySETELCategory, ResSearchBySETELCategory.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.106
            @Override // java9.util.function.Function
            public ResSearchBySETELCategory.Arguments apply(ResSearchBySETELCategory resSearchBySETELCategory) {
                return resSearchBySETELCategory.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSearchBySETELCategory.Arguments> requestSearchBySETELCategory(ReqSearchBySETELCategory.Arguments arguments, final Duration duration) {
        return requestSearchBySETELCategory(arguments).thenCompose((Function<? super ResSearchBySETELCategory.Arguments, ? extends CompletionStage<U>>) new Function<ResSearchBySETELCategory.Arguments, CompletableFuture<ResSearchBySETELCategory.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.107
            @Override // java9.util.function.Function
            public CompletableFuture<ResSearchBySETELCategory.Arguments> apply(ResSearchBySETELCategory.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSearchBySETELCategory(ReqSearchBySETELCategory reqSearchBySETELCategory) {
        reqSearchBySETELCategory.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSearchBySETELCategory.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSearchBySETELCategory)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.108
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSearchBySETELCategory(ReqSearchBySETELCategory reqSearchBySETELCategory, final Duration duration) {
        return fireSearchBySETELCategory(reqSearchBySETELCategory).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.109
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSearchBySETELCategory(ReqSearchBySETELCategory.Arguments arguments) {
        ReqSearchBySETELCategory reqSearchBySETELCategory = new ReqSearchBySETELCategory();
        reqSearchBySETELCategory.setArguments(arguments);
        reqSearchBySETELCategory.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSearchBySETELCategory.setComponent(str);
        }
        return fireSearchBySETELCategory(reqSearchBySETELCategory);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSearchBySETELCategory(ReqSearchBySETELCategory.Arguments arguments, final Duration duration) {
        return fireSearchBySETELCategory(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.110
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSearchByKeyword> requestSearchByKeyword(ReqSearchByKeyword reqSearchByKeyword) {
        if (reqSearchByKeyword.getResponse() == null) {
            reqSearchByKeyword.setResponse(new ResSearchByKeyword());
        }
        String str = this.component;
        if (str != null) {
            reqSearchByKeyword.setComponent(str);
            reqSearchByKeyword.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSearchByKeyword));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSearchByKeyword> requestSearchByKeyword(ReqSearchByKeyword reqSearchByKeyword, final Duration duration) {
        return requestSearchByKeyword(reqSearchByKeyword).thenCompose((Function<? super ResSearchByKeyword, ? extends CompletionStage<U>>) new Function<ResSearchByKeyword, CompletableFuture<ResSearchByKeyword>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.111
            @Override // java9.util.function.Function
            public CompletableFuture<ResSearchByKeyword> apply(ResSearchByKeyword resSearchByKeyword) {
                return CommonUtil.delayAsync(resSearchByKeyword, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSearchByKeyword.Arguments> requestSearchByKeyword(ReqSearchByKeyword.Arguments arguments) {
        ReqSearchByKeyword reqSearchByKeyword = new ReqSearchByKeyword();
        reqSearchByKeyword.setResponse(new ResSearchByKeyword());
        reqSearchByKeyword.setArguments(arguments);
        return requestSearchByKeyword(reqSearchByKeyword).thenApply((Function<? super ResSearchByKeyword, ? extends U>) new Function<ResSearchByKeyword, ResSearchByKeyword.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.112
            @Override // java9.util.function.Function
            public ResSearchByKeyword.Arguments apply(ResSearchByKeyword resSearchByKeyword) {
                return resSearchByKeyword.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSearchByKeyword.Arguments> requestSearchByKeyword(ReqSearchByKeyword.Arguments arguments, final Duration duration) {
        return requestSearchByKeyword(arguments).thenCompose((Function<? super ResSearchByKeyword.Arguments, ? extends CompletionStage<U>>) new Function<ResSearchByKeyword.Arguments, CompletableFuture<ResSearchByKeyword.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.113
            @Override // java9.util.function.Function
            public CompletableFuture<ResSearchByKeyword.Arguments> apply(ResSearchByKeyword.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSearchByKeyword(ReqSearchByKeyword reqSearchByKeyword) {
        reqSearchByKeyword.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSearchByKeyword.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSearchByKeyword)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.114
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSearchByKeyword(ReqSearchByKeyword reqSearchByKeyword, final Duration duration) {
        return fireSearchByKeyword(reqSearchByKeyword).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.115
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSearchByKeyword(ReqSearchByKeyword.Arguments arguments) {
        ReqSearchByKeyword reqSearchByKeyword = new ReqSearchByKeyword();
        reqSearchByKeyword.setArguments(arguments);
        reqSearchByKeyword.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSearchByKeyword.setComponent(str);
        }
        return fireSearchByKeyword(reqSearchByKeyword);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSearchByKeyword(ReqSearchByKeyword.Arguments arguments, final Duration duration) {
        return fireSearchByKeyword(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.116
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSearchAround> requestSearchAround(ReqSearchAround reqSearchAround) {
        if (reqSearchAround.getResponse() == null) {
            reqSearchAround.setResponse(new ResSearchAround());
        }
        String str = this.component;
        if (str != null) {
            reqSearchAround.setComponent(str);
            reqSearchAround.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSearchAround));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSearchAround> requestSearchAround(ReqSearchAround reqSearchAround, final Duration duration) {
        return requestSearchAround(reqSearchAround).thenCompose((Function<? super ResSearchAround, ? extends CompletionStage<U>>) new Function<ResSearchAround, CompletableFuture<ResSearchAround>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.117
            @Override // java9.util.function.Function
            public CompletableFuture<ResSearchAround> apply(ResSearchAround resSearchAround) {
                return CommonUtil.delayAsync(resSearchAround, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSearchAround.Arguments> requestSearchAround(ReqSearchAround.Arguments arguments) {
        ReqSearchAround reqSearchAround = new ReqSearchAround();
        reqSearchAround.setResponse(new ResSearchAround());
        reqSearchAround.setArguments(arguments);
        return requestSearchAround(reqSearchAround).thenApply((Function<? super ResSearchAround, ? extends U>) new Function<ResSearchAround, ResSearchAround.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.118
            @Override // java9.util.function.Function
            public ResSearchAround.Arguments apply(ResSearchAround resSearchAround) {
                return resSearchAround.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSearchAround.Arguments> requestSearchAround(ReqSearchAround.Arguments arguments, final Duration duration) {
        return requestSearchAround(arguments).thenCompose((Function<? super ResSearchAround.Arguments, ? extends CompletionStage<U>>) new Function<ResSearchAround.Arguments, CompletableFuture<ResSearchAround.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.119
            @Override // java9.util.function.Function
            public CompletableFuture<ResSearchAround.Arguments> apply(ResSearchAround.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSearchAround(ReqSearchAround reqSearchAround) {
        reqSearchAround.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSearchAround.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSearchAround)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.120
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSearchAround(ReqSearchAround reqSearchAround, final Duration duration) {
        return fireSearchAround(reqSearchAround).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.121
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSearchAround(ReqSearchAround.Arguments arguments) {
        ReqSearchAround reqSearchAround = new ReqSearchAround();
        reqSearchAround.setArguments(arguments);
        reqSearchAround.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSearchAround.setComponent(str);
        }
        return fireSearchAround(reqSearchAround);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSearchAround(ReqSearchAround.Arguments arguments, final Duration duration) {
        return fireSearchAround(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.122
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResAlongTheWaySearch> requestAlongTheWaySearch(ReqAlongTheWaySearch reqAlongTheWaySearch) {
        if (reqAlongTheWaySearch.getResponse() == null) {
            reqAlongTheWaySearch.setResponse(new ResAlongTheWaySearch());
        }
        String str = this.component;
        if (str != null) {
            reqAlongTheWaySearch.setComponent(str);
            reqAlongTheWaySearch.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqAlongTheWaySearch));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResAlongTheWaySearch> requestAlongTheWaySearch(ReqAlongTheWaySearch reqAlongTheWaySearch, final Duration duration) {
        return requestAlongTheWaySearch(reqAlongTheWaySearch).thenCompose((Function<? super ResAlongTheWaySearch, ? extends CompletionStage<U>>) new Function<ResAlongTheWaySearch, CompletableFuture<ResAlongTheWaySearch>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.123
            @Override // java9.util.function.Function
            public CompletableFuture<ResAlongTheWaySearch> apply(ResAlongTheWaySearch resAlongTheWaySearch) {
                return CommonUtil.delayAsync(resAlongTheWaySearch, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResAlongTheWaySearch.Arguments> requestAlongTheWaySearch(ReqAlongTheWaySearch.Arguments arguments) {
        ReqAlongTheWaySearch reqAlongTheWaySearch = new ReqAlongTheWaySearch();
        reqAlongTheWaySearch.setResponse(new ResAlongTheWaySearch());
        reqAlongTheWaySearch.setArguments(arguments);
        return requestAlongTheWaySearch(reqAlongTheWaySearch).thenApply((Function<? super ResAlongTheWaySearch, ? extends U>) new Function<ResAlongTheWaySearch, ResAlongTheWaySearch.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.124
            @Override // java9.util.function.Function
            public ResAlongTheWaySearch.Arguments apply(ResAlongTheWaySearch resAlongTheWaySearch) {
                return resAlongTheWaySearch.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResAlongTheWaySearch.Arguments> requestAlongTheWaySearch(ReqAlongTheWaySearch.Arguments arguments, final Duration duration) {
        return requestAlongTheWaySearch(arguments).thenCompose((Function<? super ResAlongTheWaySearch.Arguments, ? extends CompletionStage<U>>) new Function<ResAlongTheWaySearch.Arguments, CompletableFuture<ResAlongTheWaySearch.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.125
            @Override // java9.util.function.Function
            public CompletableFuture<ResAlongTheWaySearch.Arguments> apply(ResAlongTheWaySearch.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireAlongTheWaySearch(ReqAlongTheWaySearch reqAlongTheWaySearch) {
        reqAlongTheWaySearch.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqAlongTheWaySearch.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqAlongTheWaySearch)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.126
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireAlongTheWaySearch(ReqAlongTheWaySearch reqAlongTheWaySearch, final Duration duration) {
        return fireAlongTheWaySearch(reqAlongTheWaySearch).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.127
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireAlongTheWaySearch(ReqAlongTheWaySearch.Arguments arguments) {
        ReqAlongTheWaySearch reqAlongTheWaySearch = new ReqAlongTheWaySearch();
        reqAlongTheWaySearch.setArguments(arguments);
        reqAlongTheWaySearch.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqAlongTheWaySearch.setComponent(str);
        }
        return fireAlongTheWaySearch(reqAlongTheWaySearch);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireAlongTheWaySearch(ReqAlongTheWaySearch.Arguments arguments, final Duration duration) {
        return fireAlongTheWaySearch(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.128
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSpecialPoiNavi> requestSpecialPoiNavi(ReqSpecialPoiNavi reqSpecialPoiNavi) {
        if (reqSpecialPoiNavi.getResponse() == null) {
            reqSpecialPoiNavi.setResponse(new ResSpecialPoiNavi());
        }
        String str = this.component;
        if (str != null) {
            reqSpecialPoiNavi.setComponent(str);
            reqSpecialPoiNavi.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSpecialPoiNavi));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSpecialPoiNavi> requestSpecialPoiNavi(ReqSpecialPoiNavi reqSpecialPoiNavi, final Duration duration) {
        return requestSpecialPoiNavi(reqSpecialPoiNavi).thenCompose((Function<? super ResSpecialPoiNavi, ? extends CompletionStage<U>>) new Function<ResSpecialPoiNavi, CompletableFuture<ResSpecialPoiNavi>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.129
            @Override // java9.util.function.Function
            public CompletableFuture<ResSpecialPoiNavi> apply(ResSpecialPoiNavi resSpecialPoiNavi) {
                return CommonUtil.delayAsync(resSpecialPoiNavi, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSpecialPoiNavi.Arguments> requestSpecialPoiNavi(ReqSpecialPoiNavi.Arguments arguments) {
        ReqSpecialPoiNavi reqSpecialPoiNavi = new ReqSpecialPoiNavi();
        reqSpecialPoiNavi.setResponse(new ResSpecialPoiNavi());
        reqSpecialPoiNavi.setArguments(arguments);
        return requestSpecialPoiNavi(reqSpecialPoiNavi).thenApply((Function<? super ResSpecialPoiNavi, ? extends U>) new Function<ResSpecialPoiNavi, ResSpecialPoiNavi.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.130
            @Override // java9.util.function.Function
            public ResSpecialPoiNavi.Arguments apply(ResSpecialPoiNavi resSpecialPoiNavi) {
                return resSpecialPoiNavi.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSpecialPoiNavi.Arguments> requestSpecialPoiNavi(ReqSpecialPoiNavi.Arguments arguments, final Duration duration) {
        return requestSpecialPoiNavi(arguments).thenCompose((Function<? super ResSpecialPoiNavi.Arguments, ? extends CompletionStage<U>>) new Function<ResSpecialPoiNavi.Arguments, CompletableFuture<ResSpecialPoiNavi.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.131
            @Override // java9.util.function.Function
            public CompletableFuture<ResSpecialPoiNavi.Arguments> apply(ResSpecialPoiNavi.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSpecialPoiNavi(ReqSpecialPoiNavi reqSpecialPoiNavi) {
        reqSpecialPoiNavi.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSpecialPoiNavi.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSpecialPoiNavi)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.132
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSpecialPoiNavi(ReqSpecialPoiNavi reqSpecialPoiNavi, final Duration duration) {
        return fireSpecialPoiNavi(reqSpecialPoiNavi).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.133
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSpecialPoiNavi(ReqSpecialPoiNavi.Arguments arguments) {
        ReqSpecialPoiNavi reqSpecialPoiNavi = new ReqSpecialPoiNavi();
        reqSpecialPoiNavi.setArguments(arguments);
        reqSpecialPoiNavi.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSpecialPoiNavi.setComponent(str);
        }
        return fireSpecialPoiNavi(reqSpecialPoiNavi);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSpecialPoiNavi(ReqSpecialPoiNavi.Arguments arguments, final Duration duration) {
        return fireSpecialPoiNavi(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.134
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGoFavorite> requestGoFavorite(ReqGoFavorite reqGoFavorite) {
        if (reqGoFavorite.getResponse() == null) {
            reqGoFavorite.setResponse(new ResGoFavorite());
        }
        String str = this.component;
        if (str != null) {
            reqGoFavorite.setComponent(str);
            reqGoFavorite.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGoFavorite));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGoFavorite> requestGoFavorite(ReqGoFavorite reqGoFavorite, final Duration duration) {
        return requestGoFavorite(reqGoFavorite).thenCompose((Function<? super ResGoFavorite, ? extends CompletionStage<U>>) new Function<ResGoFavorite, CompletableFuture<ResGoFavorite>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.135
            @Override // java9.util.function.Function
            public CompletableFuture<ResGoFavorite> apply(ResGoFavorite resGoFavorite) {
                return CommonUtil.delayAsync(resGoFavorite, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGoFavorite.Arguments> requestGoFavorite(ReqGoFavorite.Arguments arguments) {
        ReqGoFavorite reqGoFavorite = new ReqGoFavorite();
        reqGoFavorite.setResponse(new ResGoFavorite());
        reqGoFavorite.setArguments(arguments);
        return requestGoFavorite(reqGoFavorite).thenApply((Function<? super ResGoFavorite, ? extends U>) new Function<ResGoFavorite, ResGoFavorite.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.136
            @Override // java9.util.function.Function
            public ResGoFavorite.Arguments apply(ResGoFavorite resGoFavorite) {
                return resGoFavorite.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGoFavorite.Arguments> requestGoFavorite(ReqGoFavorite.Arguments arguments, final Duration duration) {
        return requestGoFavorite(arguments).thenCompose((Function<? super ResGoFavorite.Arguments, ? extends CompletionStage<U>>) new Function<ResGoFavorite.Arguments, CompletableFuture<ResGoFavorite.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.137
            @Override // java9.util.function.Function
            public CompletableFuture<ResGoFavorite.Arguments> apply(ResGoFavorite.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGoFavorite(ReqGoFavorite reqGoFavorite) {
        reqGoFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGoFavorite.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGoFavorite)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.138
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGoFavorite(ReqGoFavorite reqGoFavorite, final Duration duration) {
        return fireGoFavorite(reqGoFavorite).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.139
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGoFavorite(ReqGoFavorite.Arguments arguments) {
        ReqGoFavorite reqGoFavorite = new ReqGoFavorite();
        reqGoFavorite.setArguments(arguments);
        reqGoFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGoFavorite.setComponent(str);
        }
        return fireGoFavorite(reqGoFavorite);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGoFavorite(ReqGoFavorite.Arguments arguments, final Duration duration) {
        return fireGoFavorite(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.140
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResRequestRoutePlanOrNavi> requestRequestRoutePlanOrNavi(ReqRequestRoutePlanOrNavi reqRequestRoutePlanOrNavi) {
        if (reqRequestRoutePlanOrNavi.getResponse() == null) {
            reqRequestRoutePlanOrNavi.setResponse(new ResRequestRoutePlanOrNavi());
        }
        String str = this.component;
        if (str != null) {
            reqRequestRoutePlanOrNavi.setComponent(str);
            reqRequestRoutePlanOrNavi.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestRoutePlanOrNavi));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResRequestRoutePlanOrNavi> requestRequestRoutePlanOrNavi(ReqRequestRoutePlanOrNavi reqRequestRoutePlanOrNavi, final Duration duration) {
        return requestRequestRoutePlanOrNavi(reqRequestRoutePlanOrNavi).thenCompose((Function<? super ResRequestRoutePlanOrNavi, ? extends CompletionStage<U>>) new Function<ResRequestRoutePlanOrNavi, CompletableFuture<ResRequestRoutePlanOrNavi>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.141
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestRoutePlanOrNavi> apply(ResRequestRoutePlanOrNavi resRequestRoutePlanOrNavi) {
                return CommonUtil.delayAsync(resRequestRoutePlanOrNavi, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResRequestRoutePlanOrNavi.Arguments> requestRequestRoutePlanOrNavi(ReqRequestRoutePlanOrNavi.Arguments arguments) {
        ReqRequestRoutePlanOrNavi reqRequestRoutePlanOrNavi = new ReqRequestRoutePlanOrNavi();
        reqRequestRoutePlanOrNavi.setResponse(new ResRequestRoutePlanOrNavi());
        reqRequestRoutePlanOrNavi.setArguments(arguments);
        return requestRequestRoutePlanOrNavi(reqRequestRoutePlanOrNavi).thenApply((Function<? super ResRequestRoutePlanOrNavi, ? extends U>) new Function<ResRequestRoutePlanOrNavi, ResRequestRoutePlanOrNavi.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.142
            @Override // java9.util.function.Function
            public ResRequestRoutePlanOrNavi.Arguments apply(ResRequestRoutePlanOrNavi resRequestRoutePlanOrNavi) {
                return resRequestRoutePlanOrNavi.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResRequestRoutePlanOrNavi.Arguments> requestRequestRoutePlanOrNavi(ReqRequestRoutePlanOrNavi.Arguments arguments, final Duration duration) {
        return requestRequestRoutePlanOrNavi(arguments).thenCompose((Function<? super ResRequestRoutePlanOrNavi.Arguments, ? extends CompletionStage<U>>) new Function<ResRequestRoutePlanOrNavi.Arguments, CompletableFuture<ResRequestRoutePlanOrNavi.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.143
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestRoutePlanOrNavi.Arguments> apply(ResRequestRoutePlanOrNavi.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRequestRoutePlanOrNavi(ReqRequestRoutePlanOrNavi reqRequestRoutePlanOrNavi) {
        reqRequestRoutePlanOrNavi.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestRoutePlanOrNavi.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestRoutePlanOrNavi)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.144
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRequestRoutePlanOrNavi(ReqRequestRoutePlanOrNavi reqRequestRoutePlanOrNavi, final Duration duration) {
        return fireRequestRoutePlanOrNavi(reqRequestRoutePlanOrNavi).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.145
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRequestRoutePlanOrNavi(ReqRequestRoutePlanOrNavi.Arguments arguments) {
        ReqRequestRoutePlanOrNavi reqRequestRoutePlanOrNavi = new ReqRequestRoutePlanOrNavi();
        reqRequestRoutePlanOrNavi.setArguments(arguments);
        reqRequestRoutePlanOrNavi.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestRoutePlanOrNavi.setComponent(str);
        }
        return fireRequestRoutePlanOrNavi(reqRequestRoutePlanOrNavi);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRequestRoutePlanOrNavi(ReqRequestRoutePlanOrNavi.Arguments arguments, final Duration duration) {
        return fireRequestRoutePlanOrNavi(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.146
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResModifyNaviVia> requestModifyNaviVia(ReqModifyNaviVia reqModifyNaviVia) {
        if (reqModifyNaviVia.getResponse() == null) {
            reqModifyNaviVia.setResponse(new ResModifyNaviVia());
        }
        String str = this.component;
        if (str != null) {
            reqModifyNaviVia.setComponent(str);
            reqModifyNaviVia.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqModifyNaviVia));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResModifyNaviVia> requestModifyNaviVia(ReqModifyNaviVia reqModifyNaviVia, final Duration duration) {
        return requestModifyNaviVia(reqModifyNaviVia).thenCompose((Function<? super ResModifyNaviVia, ? extends CompletionStage<U>>) new Function<ResModifyNaviVia, CompletableFuture<ResModifyNaviVia>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.147
            @Override // java9.util.function.Function
            public CompletableFuture<ResModifyNaviVia> apply(ResModifyNaviVia resModifyNaviVia) {
                return CommonUtil.delayAsync(resModifyNaviVia, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResModifyNaviVia.Arguments> requestModifyNaviVia(ReqModifyNaviVia.Arguments arguments) {
        ReqModifyNaviVia reqModifyNaviVia = new ReqModifyNaviVia();
        reqModifyNaviVia.setResponse(new ResModifyNaviVia());
        reqModifyNaviVia.setArguments(arguments);
        return requestModifyNaviVia(reqModifyNaviVia).thenApply((Function<? super ResModifyNaviVia, ? extends U>) new Function<ResModifyNaviVia, ResModifyNaviVia.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.148
            @Override // java9.util.function.Function
            public ResModifyNaviVia.Arguments apply(ResModifyNaviVia resModifyNaviVia) {
                return resModifyNaviVia.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResModifyNaviVia.Arguments> requestModifyNaviVia(ReqModifyNaviVia.Arguments arguments, final Duration duration) {
        return requestModifyNaviVia(arguments).thenCompose((Function<? super ResModifyNaviVia.Arguments, ? extends CompletionStage<U>>) new Function<ResModifyNaviVia.Arguments, CompletableFuture<ResModifyNaviVia.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.149
            @Override // java9.util.function.Function
            public CompletableFuture<ResModifyNaviVia.Arguments> apply(ResModifyNaviVia.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireModifyNaviVia(ReqModifyNaviVia reqModifyNaviVia) {
        reqModifyNaviVia.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqModifyNaviVia.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqModifyNaviVia)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.150
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireModifyNaviVia(ReqModifyNaviVia reqModifyNaviVia, final Duration duration) {
        return fireModifyNaviVia(reqModifyNaviVia).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.151
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireModifyNaviVia(ReqModifyNaviVia.Arguments arguments) {
        ReqModifyNaviVia reqModifyNaviVia = new ReqModifyNaviVia();
        reqModifyNaviVia.setArguments(arguments);
        reqModifyNaviVia.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqModifyNaviVia.setComponent(str);
        }
        return fireModifyNaviVia(reqModifyNaviVia);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireModifyNaviVia(ReqModifyNaviVia.Arguments arguments, final Duration duration) {
        return fireModifyNaviVia(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.152
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResRemoveWaypoint> requestRemoveWaypoint(ReqRemoveWaypoint reqRemoveWaypoint) {
        if (reqRemoveWaypoint.getResponse() == null) {
            reqRemoveWaypoint.setResponse(new ResRemoveWaypoint());
        }
        String str = this.component;
        if (str != null) {
            reqRemoveWaypoint.setComponent(str);
            reqRemoveWaypoint.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRemoveWaypoint));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResRemoveWaypoint> requestRemoveWaypoint(ReqRemoveWaypoint reqRemoveWaypoint, final Duration duration) {
        return requestRemoveWaypoint(reqRemoveWaypoint).thenCompose((Function<? super ResRemoveWaypoint, ? extends CompletionStage<U>>) new Function<ResRemoveWaypoint, CompletableFuture<ResRemoveWaypoint>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.153
            @Override // java9.util.function.Function
            public CompletableFuture<ResRemoveWaypoint> apply(ResRemoveWaypoint resRemoveWaypoint) {
                return CommonUtil.delayAsync(resRemoveWaypoint, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResRemoveWaypoint.Arguments> requestRemoveWaypoint(ReqRemoveWaypoint.Arguments arguments) {
        ReqRemoveWaypoint reqRemoveWaypoint = new ReqRemoveWaypoint();
        reqRemoveWaypoint.setResponse(new ResRemoveWaypoint());
        reqRemoveWaypoint.setArguments(arguments);
        return requestRemoveWaypoint(reqRemoveWaypoint).thenApply((Function<? super ResRemoveWaypoint, ? extends U>) new Function<ResRemoveWaypoint, ResRemoveWaypoint.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.154
            @Override // java9.util.function.Function
            public ResRemoveWaypoint.Arguments apply(ResRemoveWaypoint resRemoveWaypoint) {
                return resRemoveWaypoint.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResRemoveWaypoint.Arguments> requestRemoveWaypoint(ReqRemoveWaypoint.Arguments arguments, final Duration duration) {
        return requestRemoveWaypoint(arguments).thenCompose((Function<? super ResRemoveWaypoint.Arguments, ? extends CompletionStage<U>>) new Function<ResRemoveWaypoint.Arguments, CompletableFuture<ResRemoveWaypoint.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.155
            @Override // java9.util.function.Function
            public CompletableFuture<ResRemoveWaypoint.Arguments> apply(ResRemoveWaypoint.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRemoveWaypoint(ReqRemoveWaypoint reqRemoveWaypoint) {
        reqRemoveWaypoint.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRemoveWaypoint.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRemoveWaypoint)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.156
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRemoveWaypoint(ReqRemoveWaypoint reqRemoveWaypoint, final Duration duration) {
        return fireRemoveWaypoint(reqRemoveWaypoint).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.157
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
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

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRemoveWaypoint(ReqRemoveWaypoint.Arguments arguments, final Duration duration) {
        return fireRemoveWaypoint(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.158
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
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

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetNaviMuteMode> requestGetNaviMuteMode(ReqGetNaviMuteMode reqGetNaviMuteMode, final Duration duration) {
        return requestGetNaviMuteMode(reqGetNaviMuteMode).thenCompose((Function<? super ResGetNaviMuteMode, ? extends CompletionStage<U>>) new Function<ResGetNaviMuteMode, CompletableFuture<ResGetNaviMuteMode>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.159
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNaviMuteMode> apply(ResGetNaviMuteMode resGetNaviMuteMode) {
                return CommonUtil.delayAsync(resGetNaviMuteMode, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetNaviMuteMode> requestGetNaviMuteMode() {
        return requestGetNaviMuteMode(new ReqGetNaviMuteMode());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetNaviMuteMode> requestGetNaviMuteMode(Duration duration) {
        return requestGetNaviMuteMode(new ReqGetNaviMuteMode(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetNaviMuteMode(ReqGetNaviMuteMode reqGetNaviMuteMode) {
        reqGetNaviMuteMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNaviMuteMode.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviMuteMode)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.160
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetNaviMuteMode(ReqGetNaviMuteMode reqGetNaviMuteMode, final Duration duration) {
        return fireGetNaviMuteMode(reqGetNaviMuteMode).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.161
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetNaviMuteMode() {
        return fireGetNaviMuteMode(new ReqGetNaviMuteMode());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetNaviMuteMode(final Duration duration) {
        return fireGetNaviMuteMode(new ReqGetNaviMuteMode()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.162
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
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

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSetNaviMuteMode> requestSetNaviMuteMode(ReqSetNaviMuteMode reqSetNaviMuteMode, final Duration duration) {
        return requestSetNaviMuteMode(reqSetNaviMuteMode).thenCompose((Function<? super ResSetNaviMuteMode, ? extends CompletionStage<U>>) new Function<ResSetNaviMuteMode, CompletableFuture<ResSetNaviMuteMode>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.163
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetNaviMuteMode> apply(ResSetNaviMuteMode resSetNaviMuteMode) {
                return CommonUtil.delayAsync(resSetNaviMuteMode, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSetNaviMuteMode.Arguments> requestSetNaviMuteMode(ReqSetNaviMuteMode.Arguments arguments) {
        ReqSetNaviMuteMode reqSetNaviMuteMode = new ReqSetNaviMuteMode();
        reqSetNaviMuteMode.setResponse(new ResSetNaviMuteMode());
        reqSetNaviMuteMode.setArguments(arguments);
        return requestSetNaviMuteMode(reqSetNaviMuteMode).thenApply((Function<? super ResSetNaviMuteMode, ? extends U>) new Function<ResSetNaviMuteMode, ResSetNaviMuteMode.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.164
            @Override // java9.util.function.Function
            public ResSetNaviMuteMode.Arguments apply(ResSetNaviMuteMode resSetNaviMuteMode) {
                return resSetNaviMuteMode.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResSetNaviMuteMode.Arguments> requestSetNaviMuteMode(ReqSetNaviMuteMode.Arguments arguments, final Duration duration) {
        return requestSetNaviMuteMode(arguments).thenCompose((Function<? super ResSetNaviMuteMode.Arguments, ? extends CompletionStage<U>>) new Function<ResSetNaviMuteMode.Arguments, CompletableFuture<ResSetNaviMuteMode.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.165
            @Override // java9.util.function.Function
            public CompletableFuture<ResSetNaviMuteMode.Arguments> apply(ResSetNaviMuteMode.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetNaviMuteMode(ReqSetNaviMuteMode reqSetNaviMuteMode) {
        reqSetNaviMuteMode.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetNaviMuteMode.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetNaviMuteMode)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.166
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetNaviMuteMode(ReqSetNaviMuteMode reqSetNaviMuteMode, final Duration duration) {
        return fireSetNaviMuteMode(reqSetNaviMuteMode).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.167
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
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

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetNaviMuteMode(ReqSetNaviMuteMode.Arguments arguments, final Duration duration) {
        return fireSetNaviMuteMode(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.168
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResRequestGeoDecoder> requestRequestGeoDecoder(ReqRequestGeoDecoder reqRequestGeoDecoder) {
        if (reqRequestGeoDecoder.getResponse() == null) {
            reqRequestGeoDecoder.setResponse(new ResRequestGeoDecoder());
        }
        String str = this.component;
        if (str != null) {
            reqRequestGeoDecoder.setComponent(str);
            reqRequestGeoDecoder.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestGeoDecoder));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResRequestGeoDecoder> requestRequestGeoDecoder(ReqRequestGeoDecoder reqRequestGeoDecoder, final Duration duration) {
        return requestRequestGeoDecoder(reqRequestGeoDecoder).thenCompose((Function<? super ResRequestGeoDecoder, ? extends CompletionStage<U>>) new Function<ResRequestGeoDecoder, CompletableFuture<ResRequestGeoDecoder>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.169
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestGeoDecoder> apply(ResRequestGeoDecoder resRequestGeoDecoder) {
                return CommonUtil.delayAsync(resRequestGeoDecoder, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResRequestGeoDecoder.Arguments> requestRequestGeoDecoder(ReqRequestGeoDecoder.Arguments arguments) {
        ReqRequestGeoDecoder reqRequestGeoDecoder = new ReqRequestGeoDecoder();
        reqRequestGeoDecoder.setResponse(new ResRequestGeoDecoder());
        reqRequestGeoDecoder.setArguments(arguments);
        return requestRequestGeoDecoder(reqRequestGeoDecoder).thenApply((Function<? super ResRequestGeoDecoder, ? extends U>) new Function<ResRequestGeoDecoder, ResRequestGeoDecoder.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.170
            @Override // java9.util.function.Function
            public ResRequestGeoDecoder.Arguments apply(ResRequestGeoDecoder resRequestGeoDecoder) {
                return resRequestGeoDecoder.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResRequestGeoDecoder.Arguments> requestRequestGeoDecoder(ReqRequestGeoDecoder.Arguments arguments, final Duration duration) {
        return requestRequestGeoDecoder(arguments).thenCompose((Function<? super ResRequestGeoDecoder.Arguments, ? extends CompletionStage<U>>) new Function<ResRequestGeoDecoder.Arguments, CompletableFuture<ResRequestGeoDecoder.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.171
            @Override // java9.util.function.Function
            public CompletableFuture<ResRequestGeoDecoder.Arguments> apply(ResRequestGeoDecoder.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRequestGeoDecoder(ReqRequestGeoDecoder reqRequestGeoDecoder) {
        reqRequestGeoDecoder.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestGeoDecoder.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRequestGeoDecoder)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.172
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRequestGeoDecoder(ReqRequestGeoDecoder reqRequestGeoDecoder, final Duration duration) {
        return fireRequestGeoDecoder(reqRequestGeoDecoder).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.173
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRequestGeoDecoder(ReqRequestGeoDecoder.Arguments arguments) {
        ReqRequestGeoDecoder reqRequestGeoDecoder = new ReqRequestGeoDecoder();
        reqRequestGeoDecoder.setArguments(arguments);
        reqRequestGeoDecoder.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRequestGeoDecoder.setComponent(str);
        }
        return fireRequestGeoDecoder(reqRequestGeoDecoder);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRequestGeoDecoder(ReqRequestGeoDecoder.Arguments arguments, final Duration duration) {
        return fireRequestGeoDecoder(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.174
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetScaleStatus> requestGetScaleStatus(ReqGetScaleStatus reqGetScaleStatus) {
        if (reqGetScaleStatus.getResponse() == null) {
            reqGetScaleStatus.setResponse(new ResGetScaleStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetScaleStatus.setComponent(str);
            reqGetScaleStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetScaleStatus));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetScaleStatus> requestGetScaleStatus(ReqGetScaleStatus reqGetScaleStatus, final Duration duration) {
        return requestGetScaleStatus(reqGetScaleStatus).thenCompose((Function<? super ResGetScaleStatus, ? extends CompletionStage<U>>) new Function<ResGetScaleStatus, CompletableFuture<ResGetScaleStatus>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.175
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetScaleStatus> apply(ResGetScaleStatus resGetScaleStatus) {
                return CommonUtil.delayAsync(resGetScaleStatus, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetScaleStatus> requestGetScaleStatus() {
        return requestGetScaleStatus(new ReqGetScaleStatus());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetScaleStatus> requestGetScaleStatus(Duration duration) {
        return requestGetScaleStatus(new ReqGetScaleStatus(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetScaleStatus(ReqGetScaleStatus reqGetScaleStatus) {
        reqGetScaleStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetScaleStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetScaleStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.176
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetScaleStatus(ReqGetScaleStatus reqGetScaleStatus, final Duration duration) {
        return fireGetScaleStatus(reqGetScaleStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.177
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetScaleStatus() {
        return fireGetScaleStatus(new ReqGetScaleStatus());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetScaleStatus(final Duration duration) {
        return fireGetScaleStatus(new ReqGetScaleStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.178
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResChangeThemeResource> requestChangeThemeResource(ReqChangeThemeResource reqChangeThemeResource) {
        if (reqChangeThemeResource.getResponse() == null) {
            reqChangeThemeResource.setResponse(new ResChangeThemeResource());
        }
        String str = this.component;
        if (str != null) {
            reqChangeThemeResource.setComponent(str);
            reqChangeThemeResource.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqChangeThemeResource));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResChangeThemeResource> requestChangeThemeResource(ReqChangeThemeResource reqChangeThemeResource, final Duration duration) {
        return requestChangeThemeResource(reqChangeThemeResource).thenCompose((Function<? super ResChangeThemeResource, ? extends CompletionStage<U>>) new Function<ResChangeThemeResource, CompletableFuture<ResChangeThemeResource>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.179
            @Override // java9.util.function.Function
            public CompletableFuture<ResChangeThemeResource> apply(ResChangeThemeResource resChangeThemeResource) {
                return CommonUtil.delayAsync(resChangeThemeResource, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResChangeThemeResource.Arguments> requestChangeThemeResource(ReqChangeThemeResource.Arguments arguments) {
        ReqChangeThemeResource reqChangeThemeResource = new ReqChangeThemeResource();
        reqChangeThemeResource.setResponse(new ResChangeThemeResource());
        reqChangeThemeResource.setArguments(arguments);
        return requestChangeThemeResource(reqChangeThemeResource).thenApply((Function<? super ResChangeThemeResource, ? extends U>) new Function<ResChangeThemeResource, ResChangeThemeResource.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.180
            @Override // java9.util.function.Function
            public ResChangeThemeResource.Arguments apply(ResChangeThemeResource resChangeThemeResource) {
                return resChangeThemeResource.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResChangeThemeResource.Arguments> requestChangeThemeResource(ReqChangeThemeResource.Arguments arguments, final Duration duration) {
        return requestChangeThemeResource(arguments).thenCompose((Function<? super ResChangeThemeResource.Arguments, ? extends CompletionStage<U>>) new Function<ResChangeThemeResource.Arguments, CompletableFuture<ResChangeThemeResource.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.181
            @Override // java9.util.function.Function
            public CompletableFuture<ResChangeThemeResource.Arguments> apply(ResChangeThemeResource.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireChangeThemeResource(ReqChangeThemeResource reqChangeThemeResource) {
        reqChangeThemeResource.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqChangeThemeResource.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqChangeThemeResource)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.182
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireChangeThemeResource(ReqChangeThemeResource reqChangeThemeResource, final Duration duration) {
        return fireChangeThemeResource(reqChangeThemeResource).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.183
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireChangeThemeResource(ReqChangeThemeResource.Arguments arguments) {
        ReqChangeThemeResource reqChangeThemeResource = new ReqChangeThemeResource();
        reqChangeThemeResource.setArguments(arguments);
        reqChangeThemeResource.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqChangeThemeResource.setComponent(str);
        }
        return fireChangeThemeResource(reqChangeThemeResource);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireChangeThemeResource(ReqChangeThemeResource.Arguments arguments, final Duration duration) {
        return fireChangeThemeResource(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.184
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResResumeGuidance> requestResumeGuidance(ReqResumeGuidance reqResumeGuidance) {
        if (reqResumeGuidance.getResponse() == null) {
            reqResumeGuidance.setResponse(new ResResumeGuidance());
        }
        String str = this.component;
        if (str != null) {
            reqResumeGuidance.setComponent(str);
            reqResumeGuidance.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqResumeGuidance));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResResumeGuidance> requestResumeGuidance(ReqResumeGuidance reqResumeGuidance, final Duration duration) {
        return requestResumeGuidance(reqResumeGuidance).thenCompose((Function<? super ResResumeGuidance, ? extends CompletionStage<U>>) new Function<ResResumeGuidance, CompletableFuture<ResResumeGuidance>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.185
            @Override // java9.util.function.Function
            public CompletableFuture<ResResumeGuidance> apply(ResResumeGuidance resResumeGuidance) {
                return CommonUtil.delayAsync(resResumeGuidance, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResResumeGuidance.Arguments> requestResumeGuidance(ReqResumeGuidance.Arguments arguments) {
        ReqResumeGuidance reqResumeGuidance = new ReqResumeGuidance();
        reqResumeGuidance.setResponse(new ResResumeGuidance());
        reqResumeGuidance.setArguments(arguments);
        return requestResumeGuidance(reqResumeGuidance).thenApply((Function<? super ResResumeGuidance, ? extends U>) new Function<ResResumeGuidance, ResResumeGuidance.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.186
            @Override // java9.util.function.Function
            public ResResumeGuidance.Arguments apply(ResResumeGuidance resResumeGuidance) {
                return resResumeGuidance.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResResumeGuidance.Arguments> requestResumeGuidance(ReqResumeGuidance.Arguments arguments, final Duration duration) {
        return requestResumeGuidance(arguments).thenCompose((Function<? super ResResumeGuidance.Arguments, ? extends CompletionStage<U>>) new Function<ResResumeGuidance.Arguments, CompletableFuture<ResResumeGuidance.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.187
            @Override // java9.util.function.Function
            public CompletableFuture<ResResumeGuidance.Arguments> apply(ResResumeGuidance.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireResumeGuidance(ReqResumeGuidance reqResumeGuidance) {
        reqResumeGuidance.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqResumeGuidance.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqResumeGuidance)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.188
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireResumeGuidance(ReqResumeGuidance reqResumeGuidance, final Duration duration) {
        return fireResumeGuidance(reqResumeGuidance).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.189
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireResumeGuidance(ReqResumeGuidance.Arguments arguments) {
        ReqResumeGuidance reqResumeGuidance = new ReqResumeGuidance();
        reqResumeGuidance.setArguments(arguments);
        reqResumeGuidance.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqResumeGuidance.setComponent(str);
        }
        return fireResumeGuidance(reqResumeGuidance);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireResumeGuidance(ReqResumeGuidance.Arguments arguments, final Duration duration) {
        return fireResumeGuidance(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.190
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetSETELServiceStatus(ReqSetSETELServiceStatus reqSetSETELServiceStatus) {
        reqSetSETELServiceStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSETELServiceStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetSETELServiceStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.191
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetSETELServiceStatus(ReqSetSETELServiceStatus reqSetSETELServiceStatus, final Duration duration) {
        return fireSetSETELServiceStatus(reqSetSETELServiceStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.192
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetSETELServiceStatus(ReqSetSETELServiceStatus.Arguments arguments) {
        ReqSetSETELServiceStatus reqSetSETELServiceStatus = new ReqSetSETELServiceStatus();
        reqSetSETELServiceStatus.setArguments(arguments);
        reqSetSETELServiceStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSETELServiceStatus.setComponent(str);
        }
        return fireSetSETELServiceStatus(reqSetSETELServiceStatus);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetSETELServiceStatus(ReqSetSETELServiceStatus.Arguments arguments, final Duration duration) {
        return fireSetSETELServiceStatus(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.193
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetSETELData(ReqSetSETELData reqSetSETELData) {
        reqSetSETELData.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSETELData.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetSETELData)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.194
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetSETELData(ReqSetSETELData reqSetSETELData, final Duration duration) {
        return fireSetSETELData(reqSetSETELData).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.195
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetSETELData(ReqSetSETELData.Arguments arguments) {
        ReqSetSETELData reqSetSETELData = new ReqSetSETELData();
        reqSetSETELData.setArguments(arguments);
        reqSetSETELData.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSETELData.setComponent(str);
        }
        return fireSetSETELData(reqSetSETELData);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetSETELData(ReqSetSETELData.Arguments arguments, final Duration duration) {
        return fireSetSETELData(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.196
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetSETELDetailData(ReqSetSETELDetailData reqSetSETELDetailData) {
        reqSetSETELDetailData.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSETELDetailData.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetSETELDetailData)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.197
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetSETELDetailData(ReqSetSETELDetailData reqSetSETELDetailData, final Duration duration) {
        return fireSetSETELDetailData(reqSetSETELDetailData).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.198
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetSETELDetailData(ReqSetSETELDetailData.Arguments arguments) {
        ReqSetSETELDetailData reqSetSETELDetailData = new ReqSetSETELDetailData();
        reqSetSETELDetailData.setArguments(arguments);
        reqSetSETELDetailData.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSETELDetailData.setComponent(str);
        }
        return fireSetSETELDetailData(reqSetSETELDetailData);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetSETELDetailData(ReqSetSETELDetailData.Arguments arguments, final Duration duration) {
        return fireSetSETELDetailData(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.199
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResVRMsgCommand> requestVRMsgCommand(ReqVRMsgCommand reqVRMsgCommand) {
        if (reqVRMsgCommand.getResponse() == null) {
            reqVRMsgCommand.setResponse(new ResVRMsgCommand());
        }
        String str = this.component;
        if (str != null) {
            reqVRMsgCommand.setComponent(str);
            reqVRMsgCommand.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqVRMsgCommand));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResVRMsgCommand> requestVRMsgCommand(ReqVRMsgCommand reqVRMsgCommand, final Duration duration) {
        return requestVRMsgCommand(reqVRMsgCommand).thenCompose((Function<? super ResVRMsgCommand, ? extends CompletionStage<U>>) new Function<ResVRMsgCommand, CompletableFuture<ResVRMsgCommand>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.200
            @Override // java9.util.function.Function
            public CompletableFuture<ResVRMsgCommand> apply(ResVRMsgCommand resVRMsgCommand) {
                return CommonUtil.delayAsync(resVRMsgCommand, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResVRMsgCommand.Arguments> requestVRMsgCommand(ReqVRMsgCommand.Arguments arguments) {
        ReqVRMsgCommand reqVRMsgCommand = new ReqVRMsgCommand();
        reqVRMsgCommand.setResponse(new ResVRMsgCommand());
        reqVRMsgCommand.setArguments(arguments);
        return requestVRMsgCommand(reqVRMsgCommand).thenApply((Function<? super ResVRMsgCommand, ? extends U>) new Function<ResVRMsgCommand, ResVRMsgCommand.Arguments>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.201
            @Override // java9.util.function.Function
            public ResVRMsgCommand.Arguments apply(ResVRMsgCommand resVRMsgCommand) {
                return resVRMsgCommand.getArguments();
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResVRMsgCommand.Arguments> requestVRMsgCommand(ReqVRMsgCommand.Arguments arguments, final Duration duration) {
        return requestVRMsgCommand(arguments).thenCompose((Function<? super ResVRMsgCommand.Arguments, ? extends CompletionStage<U>>) new Function<ResVRMsgCommand.Arguments, CompletableFuture<ResVRMsgCommand.Arguments>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.202
            @Override // java9.util.function.Function
            public CompletableFuture<ResVRMsgCommand.Arguments> apply(ResVRMsgCommand.Arguments arguments2) {
                return CommonUtil.delayAsync(arguments2, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireVRMsgCommand(ReqVRMsgCommand reqVRMsgCommand) {
        reqVRMsgCommand.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqVRMsgCommand.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqVRMsgCommand)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.203
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireVRMsgCommand(ReqVRMsgCommand reqVRMsgCommand, final Duration duration) {
        return fireVRMsgCommand(reqVRMsgCommand).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.204
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireVRMsgCommand(ReqVRMsgCommand.Arguments arguments) {
        ReqVRMsgCommand reqVRMsgCommand = new ReqVRMsgCommand();
        reqVRMsgCommand.setArguments(arguments);
        reqVRMsgCommand.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqVRMsgCommand.setComponent(str);
        }
        return fireVRMsgCommand(reqVRMsgCommand);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireVRMsgCommand(ReqVRMsgCommand.Arguments arguments, final Duration duration) {
        return fireVRMsgCommand(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.205
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRefuelState(ReqRefuelState reqRefuelState) {
        reqRefuelState.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRefuelState.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRefuelState)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.206
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRefuelState(ReqRefuelState reqRefuelState, final Duration duration) {
        return fireRefuelState(reqRefuelState).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.207
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRefuelState(ReqRefuelState.Arguments arguments) {
        ReqRefuelState reqRefuelState = new ReqRefuelState();
        reqRefuelState.setArguments(arguments);
        reqRefuelState.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRefuelState.setComponent(str);
        }
        return fireRefuelState(reqRefuelState);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRefuelState(ReqRefuelState.Arguments arguments, final Duration duration) {
        return fireRefuelState(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.208
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireHasLogin(ReqHasLogin reqHasLogin) {
        reqHasLogin.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqHasLogin.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqHasLogin)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.209
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireHasLogin(ReqHasLogin reqHasLogin, final Duration duration) {
        return fireHasLogin(reqHasLogin).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.210
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireHasLogin(ReqHasLogin.Arguments arguments) {
        ReqHasLogin reqHasLogin = new ReqHasLogin();
        reqHasLogin.setArguments(arguments);
        reqHasLogin.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqHasLogin.setComponent(str);
        }
        return fireHasLogin(reqHasLogin);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireHasLogin(ReqHasLogin.Arguments arguments, final Duration duration) {
        return fireHasLogin(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.211
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetFavoriteList(ReqSetFavoriteList reqSetFavoriteList) {
        reqSetFavoriteList.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetFavoriteList.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetFavoriteList)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.212
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetFavoriteList(ReqSetFavoriteList reqSetFavoriteList, final Duration duration) {
        return fireSetFavoriteList(reqSetFavoriteList).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.213
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetFavoriteList(ReqSetFavoriteList.Arguments arguments) {
        ReqSetFavoriteList reqSetFavoriteList = new ReqSetFavoriteList();
        reqSetFavoriteList.setArguments(arguments);
        reqSetFavoriteList.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetFavoriteList.setComponent(str);
        }
        return fireSetFavoriteList(reqSetFavoriteList);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireSetFavoriteList(ReqSetFavoriteList.Arguments arguments, final Duration duration) {
        return fireSetFavoriteList(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.214
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireAddFavorite(ReqAddFavorite reqAddFavorite) {
        reqAddFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqAddFavorite.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqAddFavorite)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.215
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireAddFavorite(ReqAddFavorite reqAddFavorite, final Duration duration) {
        return fireAddFavorite(reqAddFavorite).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.216
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireAddFavorite(ReqAddFavorite.Arguments arguments) {
        ReqAddFavorite reqAddFavorite = new ReqAddFavorite();
        reqAddFavorite.setArguments(arguments);
        reqAddFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqAddFavorite.setComponent(str);
        }
        return fireAddFavorite(reqAddFavorite);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireAddFavorite(ReqAddFavorite.Arguments arguments, final Duration duration) {
        return fireAddFavorite(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.217
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireUpdateFavorite(ReqUpdateFavorite reqUpdateFavorite) {
        reqUpdateFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqUpdateFavorite.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqUpdateFavorite)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.218
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireUpdateFavorite(ReqUpdateFavorite reqUpdateFavorite, final Duration duration) {
        return fireUpdateFavorite(reqUpdateFavorite).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.219
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireUpdateFavorite(ReqUpdateFavorite.Arguments arguments) {
        ReqUpdateFavorite reqUpdateFavorite = new ReqUpdateFavorite();
        reqUpdateFavorite.setArguments(arguments);
        reqUpdateFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqUpdateFavorite.setComponent(str);
        }
        return fireUpdateFavorite(reqUpdateFavorite);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireUpdateFavorite(ReqUpdateFavorite.Arguments arguments, final Duration duration) {
        return fireUpdateFavorite(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.220
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRemoveFavorite(ReqRemoveFavorite reqRemoveFavorite) {
        reqRemoveFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRemoveFavorite.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqRemoveFavorite)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.221
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRemoveFavorite(ReqRemoveFavorite reqRemoveFavorite, final Duration duration) {
        return fireRemoveFavorite(reqRemoveFavorite).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.222
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRemoveFavorite(ReqRemoveFavorite.Arguments arguments) {
        ReqRemoveFavorite reqRemoveFavorite = new ReqRemoveFavorite();
        reqRemoveFavorite.setArguments(arguments);
        reqRemoveFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqRemoveFavorite.setComponent(str);
        }
        return fireRemoveFavorite(reqRemoveFavorite);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireRemoveFavorite(ReqRemoveFavorite.Arguments arguments, final Duration duration) {
        return fireRemoveFavorite(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.223
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
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

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate, final Duration duration) {
        return requestGetGuidanceInfoUpdate(reqGetGuidanceInfoUpdate).thenCompose((Function<? super ResGetGuidanceInfoUpdate, ? extends CompletionStage<U>>) new Function<ResGetGuidanceInfoUpdate, CompletableFuture<ResGetGuidanceInfoUpdate>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.224
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetGuidanceInfoUpdate> apply(ResGetGuidanceInfoUpdate resGetGuidanceInfoUpdate) {
                return CommonUtil.delayAsync(resGetGuidanceInfoUpdate, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate() {
        return requestGetGuidanceInfoUpdate(new ReqGetGuidanceInfoUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate(Duration duration) {
        return requestGetGuidanceInfoUpdate(new ReqGetGuidanceInfoUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate) {
        reqGetGuidanceInfoUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetGuidanceInfoUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGuidanceInfoUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.225
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate, final Duration duration) {
        return fireGetGuidanceInfoUpdate(reqGetGuidanceInfoUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.226
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceInfoUpdate() {
        return fireGetGuidanceInfoUpdate(new ReqGetGuidanceInfoUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetGuidanceInfoUpdate(final Duration duration) {
        return fireGetGuidanceInfoUpdate(new ReqGetGuidanceInfoUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.227
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetNaviStatusUpdate> requestGetNaviStatusUpdate(ReqGetNaviStatusUpdate reqGetNaviStatusUpdate) {
        if (reqGetNaviStatusUpdate.getResponse() == null) {
            reqGetNaviStatusUpdate.setResponse(new ResGetNaviStatusUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetNaviStatusUpdate.setComponent(str);
            reqGetNaviStatusUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviStatusUpdate));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetNaviStatusUpdate> requestGetNaviStatusUpdate(ReqGetNaviStatusUpdate reqGetNaviStatusUpdate, final Duration duration) {
        return requestGetNaviStatusUpdate(reqGetNaviStatusUpdate).thenCompose((Function<? super ResGetNaviStatusUpdate, ? extends CompletionStage<U>>) new Function<ResGetNaviStatusUpdate, CompletableFuture<ResGetNaviStatusUpdate>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.228
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetNaviStatusUpdate> apply(ResGetNaviStatusUpdate resGetNaviStatusUpdate) {
                return CommonUtil.delayAsync(resGetNaviStatusUpdate, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetNaviStatusUpdate> requestGetNaviStatusUpdate() {
        return requestGetNaviStatusUpdate(new ReqGetNaviStatusUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetNaviStatusUpdate> requestGetNaviStatusUpdate(Duration duration) {
        return requestGetNaviStatusUpdate(new ReqGetNaviStatusUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetNaviStatusUpdate(ReqGetNaviStatusUpdate reqGetNaviStatusUpdate) {
        reqGetNaviStatusUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetNaviStatusUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetNaviStatusUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.229
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetNaviStatusUpdate(ReqGetNaviStatusUpdate reqGetNaviStatusUpdate, final Duration duration) {
        return fireGetNaviStatusUpdate(reqGetNaviStatusUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.230
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetNaviStatusUpdate() {
        return fireGetNaviStatusUpdate(new ReqGetNaviStatusUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetNaviStatusUpdate(final Duration duration) {
        return fireGetNaviStatusUpdate(new ReqGetNaviStatusUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.231
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetMapStatusUpdate> requestGetMapStatusUpdate(ReqGetMapStatusUpdate reqGetMapStatusUpdate) {
        if (reqGetMapStatusUpdate.getResponse() == null) {
            reqGetMapStatusUpdate.setResponse(new ResGetMapStatusUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetMapStatusUpdate.setComponent(str);
            reqGetMapStatusUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetMapStatusUpdate));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetMapStatusUpdate> requestGetMapStatusUpdate(ReqGetMapStatusUpdate reqGetMapStatusUpdate, final Duration duration) {
        return requestGetMapStatusUpdate(reqGetMapStatusUpdate).thenCompose((Function<? super ResGetMapStatusUpdate, ? extends CompletionStage<U>>) new Function<ResGetMapStatusUpdate, CompletableFuture<ResGetMapStatusUpdate>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.232
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetMapStatusUpdate> apply(ResGetMapStatusUpdate resGetMapStatusUpdate) {
                return CommonUtil.delayAsync(resGetMapStatusUpdate, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetMapStatusUpdate> requestGetMapStatusUpdate() {
        return requestGetMapStatusUpdate(new ReqGetMapStatusUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetMapStatusUpdate> requestGetMapStatusUpdate(Duration duration) {
        return requestGetMapStatusUpdate(new ReqGetMapStatusUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetMapStatusUpdate(ReqGetMapStatusUpdate reqGetMapStatusUpdate) {
        reqGetMapStatusUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetMapStatusUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetMapStatusUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.233
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetMapStatusUpdate(ReqGetMapStatusUpdate reqGetMapStatusUpdate, final Duration duration) {
        return fireGetMapStatusUpdate(reqGetMapStatusUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.234
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetMapStatusUpdate() {
        return fireGetMapStatusUpdate(new ReqGetMapStatusUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetMapStatusUpdate(final Duration duration) {
        return fireGetMapStatusUpdate(new ReqGetMapStatusUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.235
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRouteViewStatusUpdate> requestGetRouteViewStatusUpdate(ReqGetRouteViewStatusUpdate reqGetRouteViewStatusUpdate) {
        if (reqGetRouteViewStatusUpdate.getResponse() == null) {
            reqGetRouteViewStatusUpdate.setResponse(new ResGetRouteViewStatusUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetRouteViewStatusUpdate.setComponent(str);
            reqGetRouteViewStatusUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRouteViewStatusUpdate));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRouteViewStatusUpdate> requestGetRouteViewStatusUpdate(ReqGetRouteViewStatusUpdate reqGetRouteViewStatusUpdate, final Duration duration) {
        return requestGetRouteViewStatusUpdate(reqGetRouteViewStatusUpdate).thenCompose((Function<? super ResGetRouteViewStatusUpdate, ? extends CompletionStage<U>>) new Function<ResGetRouteViewStatusUpdate, CompletableFuture<ResGetRouteViewStatusUpdate>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.236
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRouteViewStatusUpdate> apply(ResGetRouteViewStatusUpdate resGetRouteViewStatusUpdate) {
                return CommonUtil.delayAsync(resGetRouteViewStatusUpdate, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRouteViewStatusUpdate> requestGetRouteViewStatusUpdate() {
        return requestGetRouteViewStatusUpdate(new ReqGetRouteViewStatusUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRouteViewStatusUpdate> requestGetRouteViewStatusUpdate(Duration duration) {
        return requestGetRouteViewStatusUpdate(new ReqGetRouteViewStatusUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRouteViewStatusUpdate(ReqGetRouteViewStatusUpdate reqGetRouteViewStatusUpdate) {
        reqGetRouteViewStatusUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRouteViewStatusUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRouteViewStatusUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.237
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRouteViewStatusUpdate(ReqGetRouteViewStatusUpdate reqGetRouteViewStatusUpdate, final Duration duration) {
        return fireGetRouteViewStatusUpdate(reqGetRouteViewStatusUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.238
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRouteViewStatusUpdate() {
        return fireGetRouteViewStatusUpdate(new ReqGetRouteViewStatusUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRouteViewStatusUpdate(final Duration duration) {
        return fireGetRouteViewStatusUpdate(new ReqGetRouteViewStatusUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.239
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetTrafficStatusUpdate> requestGetTrafficStatusUpdate(ReqGetTrafficStatusUpdate reqGetTrafficStatusUpdate) {
        if (reqGetTrafficStatusUpdate.getResponse() == null) {
            reqGetTrafficStatusUpdate.setResponse(new ResGetTrafficStatusUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetTrafficStatusUpdate.setComponent(str);
            reqGetTrafficStatusUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetTrafficStatusUpdate));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetTrafficStatusUpdate> requestGetTrafficStatusUpdate(ReqGetTrafficStatusUpdate reqGetTrafficStatusUpdate, final Duration duration) {
        return requestGetTrafficStatusUpdate(reqGetTrafficStatusUpdate).thenCompose((Function<? super ResGetTrafficStatusUpdate, ? extends CompletionStage<U>>) new Function<ResGetTrafficStatusUpdate, CompletableFuture<ResGetTrafficStatusUpdate>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.240
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetTrafficStatusUpdate> apply(ResGetTrafficStatusUpdate resGetTrafficStatusUpdate) {
                return CommonUtil.delayAsync(resGetTrafficStatusUpdate, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetTrafficStatusUpdate> requestGetTrafficStatusUpdate() {
        return requestGetTrafficStatusUpdate(new ReqGetTrafficStatusUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetTrafficStatusUpdate> requestGetTrafficStatusUpdate(Duration duration) {
        return requestGetTrafficStatusUpdate(new ReqGetTrafficStatusUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetTrafficStatusUpdate(ReqGetTrafficStatusUpdate reqGetTrafficStatusUpdate) {
        reqGetTrafficStatusUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetTrafficStatusUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetTrafficStatusUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.241
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetTrafficStatusUpdate(ReqGetTrafficStatusUpdate reqGetTrafficStatusUpdate, final Duration duration) {
        return fireGetTrafficStatusUpdate(reqGetTrafficStatusUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.242
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetTrafficStatusUpdate() {
        return fireGetTrafficStatusUpdate(new ReqGetTrafficStatusUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetTrafficStatusUpdate(final Duration duration) {
        return fireGetTrafficStatusUpdate(new ReqGetTrafficStatusUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.243
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetZoomStatusUpdate> requestGetZoomStatusUpdate(ReqGetZoomStatusUpdate reqGetZoomStatusUpdate) {
        if (reqGetZoomStatusUpdate.getResponse() == null) {
            reqGetZoomStatusUpdate.setResponse(new ResGetZoomStatusUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetZoomStatusUpdate.setComponent(str);
            reqGetZoomStatusUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetZoomStatusUpdate));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetZoomStatusUpdate> requestGetZoomStatusUpdate(ReqGetZoomStatusUpdate reqGetZoomStatusUpdate, final Duration duration) {
        return requestGetZoomStatusUpdate(reqGetZoomStatusUpdate).thenCompose((Function<? super ResGetZoomStatusUpdate, ? extends CompletionStage<U>>) new Function<ResGetZoomStatusUpdate, CompletableFuture<ResGetZoomStatusUpdate>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.244
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetZoomStatusUpdate> apply(ResGetZoomStatusUpdate resGetZoomStatusUpdate) {
                return CommonUtil.delayAsync(resGetZoomStatusUpdate, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetZoomStatusUpdate> requestGetZoomStatusUpdate() {
        return requestGetZoomStatusUpdate(new ReqGetZoomStatusUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetZoomStatusUpdate> requestGetZoomStatusUpdate(Duration duration) {
        return requestGetZoomStatusUpdate(new ReqGetZoomStatusUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetZoomStatusUpdate(ReqGetZoomStatusUpdate reqGetZoomStatusUpdate) {
        reqGetZoomStatusUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetZoomStatusUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetZoomStatusUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.245
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetZoomStatusUpdate(ReqGetZoomStatusUpdate reqGetZoomStatusUpdate, final Duration duration) {
        return fireGetZoomStatusUpdate(reqGetZoomStatusUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.246
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetZoomStatusUpdate() {
        return fireGetZoomStatusUpdate(new ReqGetZoomStatusUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetZoomStatusUpdate(final Duration duration) {
        return fireGetZoomStatusUpdate(new ReqGetZoomStatusUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.247
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
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

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetDayNightStatusUpdate> requestGetDayNightStatusUpdate(ReqGetDayNightStatusUpdate reqGetDayNightStatusUpdate, final Duration duration) {
        return requestGetDayNightStatusUpdate(reqGetDayNightStatusUpdate).thenCompose((Function<? super ResGetDayNightStatusUpdate, ? extends CompletionStage<U>>) new Function<ResGetDayNightStatusUpdate, CompletableFuture<ResGetDayNightStatusUpdate>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.248
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetDayNightStatusUpdate> apply(ResGetDayNightStatusUpdate resGetDayNightStatusUpdate) {
                return CommonUtil.delayAsync(resGetDayNightStatusUpdate, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetDayNightStatusUpdate> requestGetDayNightStatusUpdate() {
        return requestGetDayNightStatusUpdate(new ReqGetDayNightStatusUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetDayNightStatusUpdate> requestGetDayNightStatusUpdate(Duration duration) {
        return requestGetDayNightStatusUpdate(new ReqGetDayNightStatusUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetDayNightStatusUpdate(ReqGetDayNightStatusUpdate reqGetDayNightStatusUpdate) {
        reqGetDayNightStatusUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetDayNightStatusUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetDayNightStatusUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.249
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetDayNightStatusUpdate(ReqGetDayNightStatusUpdate reqGetDayNightStatusUpdate, final Duration duration) {
        return fireGetDayNightStatusUpdate(reqGetDayNightStatusUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.250
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetDayNightStatusUpdate() {
        return fireGetDayNightStatusUpdate(new ReqGetDayNightStatusUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetDayNightStatusUpdate(final Duration duration) {
        return fireGetDayNightStatusUpdate(new ReqGetDayNightStatusUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.251
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetViewModeUpdate> requestGetViewModeUpdate(ReqGetViewModeUpdate reqGetViewModeUpdate) {
        if (reqGetViewModeUpdate.getResponse() == null) {
            reqGetViewModeUpdate.setResponse(new ResGetViewModeUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetViewModeUpdate.setComponent(str);
            reqGetViewModeUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetViewModeUpdate));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetViewModeUpdate> requestGetViewModeUpdate(ReqGetViewModeUpdate reqGetViewModeUpdate, final Duration duration) {
        return requestGetViewModeUpdate(reqGetViewModeUpdate).thenCompose((Function<? super ResGetViewModeUpdate, ? extends CompletionStage<U>>) new Function<ResGetViewModeUpdate, CompletableFuture<ResGetViewModeUpdate>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.252
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetViewModeUpdate> apply(ResGetViewModeUpdate resGetViewModeUpdate) {
                return CommonUtil.delayAsync(resGetViewModeUpdate, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetViewModeUpdate> requestGetViewModeUpdate() {
        return requestGetViewModeUpdate(new ReqGetViewModeUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetViewModeUpdate> requestGetViewModeUpdate(Duration duration) {
        return requestGetViewModeUpdate(new ReqGetViewModeUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetViewModeUpdate(ReqGetViewModeUpdate reqGetViewModeUpdate) {
        reqGetViewModeUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetViewModeUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetViewModeUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.253
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetViewModeUpdate(ReqGetViewModeUpdate reqGetViewModeUpdate, final Duration duration) {
        return fireGetViewModeUpdate(reqGetViewModeUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.254
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetViewModeUpdate() {
        return fireGetViewModeUpdate(new ReqGetViewModeUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetViewModeUpdate(final Duration duration) {
        return fireGetViewModeUpdate(new ReqGetViewModeUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.255
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetMuteModeUpdate> requestGetMuteModeUpdate(ReqGetMuteModeUpdate reqGetMuteModeUpdate) {
        if (reqGetMuteModeUpdate.getResponse() == null) {
            reqGetMuteModeUpdate.setResponse(new ResGetMuteModeUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetMuteModeUpdate.setComponent(str);
            reqGetMuteModeUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetMuteModeUpdate));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetMuteModeUpdate> requestGetMuteModeUpdate(ReqGetMuteModeUpdate reqGetMuteModeUpdate, final Duration duration) {
        return requestGetMuteModeUpdate(reqGetMuteModeUpdate).thenCompose((Function<? super ResGetMuteModeUpdate, ? extends CompletionStage<U>>) new Function<ResGetMuteModeUpdate, CompletableFuture<ResGetMuteModeUpdate>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.256
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetMuteModeUpdate> apply(ResGetMuteModeUpdate resGetMuteModeUpdate) {
                return CommonUtil.delayAsync(resGetMuteModeUpdate, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetMuteModeUpdate> requestGetMuteModeUpdate() {
        return requestGetMuteModeUpdate(new ReqGetMuteModeUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetMuteModeUpdate> requestGetMuteModeUpdate(Duration duration) {
        return requestGetMuteModeUpdate(new ReqGetMuteModeUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetMuteModeUpdate(ReqGetMuteModeUpdate reqGetMuteModeUpdate) {
        reqGetMuteModeUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetMuteModeUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetMuteModeUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.257
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetMuteModeUpdate(ReqGetMuteModeUpdate reqGetMuteModeUpdate, final Duration duration) {
        return fireGetMuteModeUpdate(reqGetMuteModeUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.258
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetMuteModeUpdate() {
        return fireGetMuteModeUpdate(new ReqGetMuteModeUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetMuteModeUpdate(final Duration duration) {
        return fireGetMuteModeUpdate(new ReqGetMuteModeUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.259
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetFavoritesInfoUpdate> requestGetFavoritesInfoUpdate(ReqGetFavoritesInfoUpdate reqGetFavoritesInfoUpdate) {
        if (reqGetFavoritesInfoUpdate.getResponse() == null) {
            reqGetFavoritesInfoUpdate.setResponse(new ResGetFavoritesInfoUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetFavoritesInfoUpdate.setComponent(str);
            reqGetFavoritesInfoUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetFavoritesInfoUpdate));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetFavoritesInfoUpdate> requestGetFavoritesInfoUpdate(ReqGetFavoritesInfoUpdate reqGetFavoritesInfoUpdate, final Duration duration) {
        return requestGetFavoritesInfoUpdate(reqGetFavoritesInfoUpdate).thenCompose((Function<? super ResGetFavoritesInfoUpdate, ? extends CompletionStage<U>>) new Function<ResGetFavoritesInfoUpdate, CompletableFuture<ResGetFavoritesInfoUpdate>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.260
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetFavoritesInfoUpdate> apply(ResGetFavoritesInfoUpdate resGetFavoritesInfoUpdate) {
                return CommonUtil.delayAsync(resGetFavoritesInfoUpdate, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetFavoritesInfoUpdate> requestGetFavoritesInfoUpdate() {
        return requestGetFavoritesInfoUpdate(new ReqGetFavoritesInfoUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetFavoritesInfoUpdate> requestGetFavoritesInfoUpdate(Duration duration) {
        return requestGetFavoritesInfoUpdate(new ReqGetFavoritesInfoUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetFavoritesInfoUpdate(ReqGetFavoritesInfoUpdate reqGetFavoritesInfoUpdate) {
        reqGetFavoritesInfoUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetFavoritesInfoUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetFavoritesInfoUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.261
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetFavoritesInfoUpdate(ReqGetFavoritesInfoUpdate reqGetFavoritesInfoUpdate, final Duration duration) {
        return fireGetFavoritesInfoUpdate(reqGetFavoritesInfoUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.262
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetFavoritesInfoUpdate() {
        return fireGetFavoritesInfoUpdate(new ReqGetFavoritesInfoUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetFavoritesInfoUpdate(final Duration duration) {
        return fireGetFavoritesInfoUpdate(new ReqGetFavoritesInfoUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.263
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRouteInfoUpdate> requestGetRouteInfoUpdate(ReqGetRouteInfoUpdate reqGetRouteInfoUpdate) {
        if (reqGetRouteInfoUpdate.getResponse() == null) {
            reqGetRouteInfoUpdate.setResponse(new ResGetRouteInfoUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetRouteInfoUpdate.setComponent(str);
            reqGetRouteInfoUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRouteInfoUpdate));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRouteInfoUpdate> requestGetRouteInfoUpdate(ReqGetRouteInfoUpdate reqGetRouteInfoUpdate, final Duration duration) {
        return requestGetRouteInfoUpdate(reqGetRouteInfoUpdate).thenCompose((Function<? super ResGetRouteInfoUpdate, ? extends CompletionStage<U>>) new Function<ResGetRouteInfoUpdate, CompletableFuture<ResGetRouteInfoUpdate>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.264
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRouteInfoUpdate> apply(ResGetRouteInfoUpdate resGetRouteInfoUpdate) {
                return CommonUtil.delayAsync(resGetRouteInfoUpdate, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRouteInfoUpdate> requestGetRouteInfoUpdate() {
        return requestGetRouteInfoUpdate(new ReqGetRouteInfoUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRouteInfoUpdate> requestGetRouteInfoUpdate(Duration duration) {
        return requestGetRouteInfoUpdate(new ReqGetRouteInfoUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRouteInfoUpdate(ReqGetRouteInfoUpdate reqGetRouteInfoUpdate) {
        reqGetRouteInfoUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRouteInfoUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRouteInfoUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.265
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRouteInfoUpdate(ReqGetRouteInfoUpdate reqGetRouteInfoUpdate, final Duration duration) {
        return fireGetRouteInfoUpdate(reqGetRouteInfoUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.266
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRouteInfoUpdate() {
        return fireGetRouteInfoUpdate(new ReqGetRouteInfoUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRouteInfoUpdate(final Duration duration) {
        return fireGetRouteInfoUpdate(new ReqGetRouteInfoUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.267
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRouteSelectedUpdate> requestGetRouteSelectedUpdate(ReqGetRouteSelectedUpdate reqGetRouteSelectedUpdate) {
        if (reqGetRouteSelectedUpdate.getResponse() == null) {
            reqGetRouteSelectedUpdate.setResponse(new ResGetRouteSelectedUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetRouteSelectedUpdate.setComponent(str);
            reqGetRouteSelectedUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRouteSelectedUpdate));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRouteSelectedUpdate> requestGetRouteSelectedUpdate(ReqGetRouteSelectedUpdate reqGetRouteSelectedUpdate, final Duration duration) {
        return requestGetRouteSelectedUpdate(reqGetRouteSelectedUpdate).thenCompose((Function<? super ResGetRouteSelectedUpdate, ? extends CompletionStage<U>>) new Function<ResGetRouteSelectedUpdate, CompletableFuture<ResGetRouteSelectedUpdate>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.268
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRouteSelectedUpdate> apply(ResGetRouteSelectedUpdate resGetRouteSelectedUpdate) {
                return CommonUtil.delayAsync(resGetRouteSelectedUpdate, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRouteSelectedUpdate> requestGetRouteSelectedUpdate() {
        return requestGetRouteSelectedUpdate(new ReqGetRouteSelectedUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRouteSelectedUpdate> requestGetRouteSelectedUpdate(Duration duration) {
        return requestGetRouteSelectedUpdate(new ReqGetRouteSelectedUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRouteSelectedUpdate(ReqGetRouteSelectedUpdate reqGetRouteSelectedUpdate) {
        reqGetRouteSelectedUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRouteSelectedUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRouteSelectedUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.269
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRouteSelectedUpdate(ReqGetRouteSelectedUpdate reqGetRouteSelectedUpdate, final Duration duration) {
        return fireGetRouteSelectedUpdate(reqGetRouteSelectedUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.270
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRouteSelectedUpdate() {
        return fireGetRouteSelectedUpdate(new ReqGetRouteSelectedUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRouteSelectedUpdate(final Duration duration) {
        return fireGetRouteSelectedUpdate(new ReqGetRouteSelectedUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.271
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetHighwayInfoUpdate> requestGetHighwayInfoUpdate(ReqGetHighwayInfoUpdate reqGetHighwayInfoUpdate) {
        if (reqGetHighwayInfoUpdate.getResponse() == null) {
            reqGetHighwayInfoUpdate.setResponse(new ResGetHighwayInfoUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetHighwayInfoUpdate.setComponent(str);
            reqGetHighwayInfoUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetHighwayInfoUpdate));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetHighwayInfoUpdate> requestGetHighwayInfoUpdate(ReqGetHighwayInfoUpdate reqGetHighwayInfoUpdate, final Duration duration) {
        return requestGetHighwayInfoUpdate(reqGetHighwayInfoUpdate).thenCompose((Function<? super ResGetHighwayInfoUpdate, ? extends CompletionStage<U>>) new Function<ResGetHighwayInfoUpdate, CompletableFuture<ResGetHighwayInfoUpdate>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.272
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetHighwayInfoUpdate> apply(ResGetHighwayInfoUpdate resGetHighwayInfoUpdate) {
                return CommonUtil.delayAsync(resGetHighwayInfoUpdate, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetHighwayInfoUpdate> requestGetHighwayInfoUpdate() {
        return requestGetHighwayInfoUpdate(new ReqGetHighwayInfoUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetHighwayInfoUpdate> requestGetHighwayInfoUpdate(Duration duration) {
        return requestGetHighwayInfoUpdate(new ReqGetHighwayInfoUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetHighwayInfoUpdate(ReqGetHighwayInfoUpdate reqGetHighwayInfoUpdate) {
        reqGetHighwayInfoUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetHighwayInfoUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetHighwayInfoUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.273
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetHighwayInfoUpdate(ReqGetHighwayInfoUpdate reqGetHighwayInfoUpdate, final Duration duration) {
        return fireGetHighwayInfoUpdate(reqGetHighwayInfoUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.274
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetHighwayInfoUpdate() {
        return fireGetHighwayInfoUpdate(new ReqGetHighwayInfoUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetHighwayInfoUpdate(final Duration duration) {
        return fireGetHighwayInfoUpdate(new ReqGetHighwayInfoUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.275
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetServiceStatusUpdate> requestGetServiceStatusUpdate(ReqGetServiceStatusUpdate reqGetServiceStatusUpdate) {
        if (reqGetServiceStatusUpdate.getResponse() == null) {
            reqGetServiceStatusUpdate.setResponse(new ResGetServiceStatusUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetServiceStatusUpdate.setComponent(str);
            reqGetServiceStatusUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetServiceStatusUpdate));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetServiceStatusUpdate> requestGetServiceStatusUpdate(ReqGetServiceStatusUpdate reqGetServiceStatusUpdate, final Duration duration) {
        return requestGetServiceStatusUpdate(reqGetServiceStatusUpdate).thenCompose((Function<? super ResGetServiceStatusUpdate, ? extends CompletionStage<U>>) new Function<ResGetServiceStatusUpdate, CompletableFuture<ResGetServiceStatusUpdate>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.276
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetServiceStatusUpdate> apply(ResGetServiceStatusUpdate resGetServiceStatusUpdate) {
                return CommonUtil.delayAsync(resGetServiceStatusUpdate, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetServiceStatusUpdate> requestGetServiceStatusUpdate() {
        return requestGetServiceStatusUpdate(new ReqGetServiceStatusUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetServiceStatusUpdate> requestGetServiceStatusUpdate(Duration duration) {
        return requestGetServiceStatusUpdate(new ReqGetServiceStatusUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetServiceStatusUpdate(ReqGetServiceStatusUpdate reqGetServiceStatusUpdate) {
        reqGetServiceStatusUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetServiceStatusUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetServiceStatusUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.277
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetServiceStatusUpdate(ReqGetServiceStatusUpdate reqGetServiceStatusUpdate, final Duration duration) {
        return fireGetServiceStatusUpdate(reqGetServiceStatusUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.278
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetServiceStatusUpdate() {
        return fireGetServiceStatusUpdate(new ReqGetServiceStatusUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetServiceStatusUpdate(final Duration duration) {
        return fireGetServiceStatusUpdate(new ReqGetServiceStatusUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.279
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetArrivedDest> requestGetArrivedDest(ReqGetArrivedDest reqGetArrivedDest) {
        if (reqGetArrivedDest.getResponse() == null) {
            reqGetArrivedDest.setResponse(new ResGetArrivedDest());
        }
        String str = this.component;
        if (str != null) {
            reqGetArrivedDest.setComponent(str);
            reqGetArrivedDest.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetArrivedDest));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetArrivedDest> requestGetArrivedDest(ReqGetArrivedDest reqGetArrivedDest, final Duration duration) {
        return requestGetArrivedDest(reqGetArrivedDest).thenCompose((Function<? super ResGetArrivedDest, ? extends CompletionStage<U>>) new Function<ResGetArrivedDest, CompletableFuture<ResGetArrivedDest>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.280
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetArrivedDest> apply(ResGetArrivedDest resGetArrivedDest) {
                return CommonUtil.delayAsync(resGetArrivedDest, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetArrivedDest> requestGetArrivedDest() {
        return requestGetArrivedDest(new ReqGetArrivedDest());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetArrivedDest> requestGetArrivedDest(Duration duration) {
        return requestGetArrivedDest(new ReqGetArrivedDest(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetArrivedDest(ReqGetArrivedDest reqGetArrivedDest) {
        reqGetArrivedDest.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetArrivedDest.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetArrivedDest)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.281
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetArrivedDest(ReqGetArrivedDest reqGetArrivedDest, final Duration duration) {
        return fireGetArrivedDest(reqGetArrivedDest).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.282
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetArrivedDest() {
        return fireGetArrivedDest(new ReqGetArrivedDest());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetArrivedDest(final Duration duration) {
        return fireGetArrivedDest(new ReqGetArrivedDest()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.283
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetADAreaUpdate> requestGetADAreaUpdate(ReqGetADAreaUpdate reqGetADAreaUpdate) {
        if (reqGetADAreaUpdate.getResponse() == null) {
            reqGetADAreaUpdate.setResponse(new ResGetADAreaUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetADAreaUpdate.setComponent(str);
            reqGetADAreaUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetADAreaUpdate));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetADAreaUpdate> requestGetADAreaUpdate(ReqGetADAreaUpdate reqGetADAreaUpdate, final Duration duration) {
        return requestGetADAreaUpdate(reqGetADAreaUpdate).thenCompose((Function<? super ResGetADAreaUpdate, ? extends CompletionStage<U>>) new Function<ResGetADAreaUpdate, CompletableFuture<ResGetADAreaUpdate>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.284
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetADAreaUpdate> apply(ResGetADAreaUpdate resGetADAreaUpdate) {
                return CommonUtil.delayAsync(resGetADAreaUpdate, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetADAreaUpdate> requestGetADAreaUpdate() {
        return requestGetADAreaUpdate(new ReqGetADAreaUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetADAreaUpdate> requestGetADAreaUpdate(Duration duration) {
        return requestGetADAreaUpdate(new ReqGetADAreaUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetADAreaUpdate(ReqGetADAreaUpdate reqGetADAreaUpdate) {
        reqGetADAreaUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetADAreaUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetADAreaUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.285
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetADAreaUpdate(ReqGetADAreaUpdate reqGetADAreaUpdate, final Duration duration) {
        return fireGetADAreaUpdate(reqGetADAreaUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.286
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetADAreaUpdate() {
        return fireGetADAreaUpdate(new ReqGetADAreaUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetADAreaUpdate(final Duration duration) {
        return fireGetADAreaUpdate(new ReqGetADAreaUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.287
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetTrafficConditionUpdate> requestGetTrafficConditionUpdate(ReqGetTrafficConditionUpdate reqGetTrafficConditionUpdate) {
        if (reqGetTrafficConditionUpdate.getResponse() == null) {
            reqGetTrafficConditionUpdate.setResponse(new ResGetTrafficConditionUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetTrafficConditionUpdate.setComponent(str);
            reqGetTrafficConditionUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetTrafficConditionUpdate));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetTrafficConditionUpdate> requestGetTrafficConditionUpdate(ReqGetTrafficConditionUpdate reqGetTrafficConditionUpdate, final Duration duration) {
        return requestGetTrafficConditionUpdate(reqGetTrafficConditionUpdate).thenCompose((Function<? super ResGetTrafficConditionUpdate, ? extends CompletionStage<U>>) new Function<ResGetTrafficConditionUpdate, CompletableFuture<ResGetTrafficConditionUpdate>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.288
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetTrafficConditionUpdate> apply(ResGetTrafficConditionUpdate resGetTrafficConditionUpdate) {
                return CommonUtil.delayAsync(resGetTrafficConditionUpdate, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetTrafficConditionUpdate> requestGetTrafficConditionUpdate() {
        return requestGetTrafficConditionUpdate(new ReqGetTrafficConditionUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetTrafficConditionUpdate> requestGetTrafficConditionUpdate(Duration duration) {
        return requestGetTrafficConditionUpdate(new ReqGetTrafficConditionUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetTrafficConditionUpdate(ReqGetTrafficConditionUpdate reqGetTrafficConditionUpdate) {
        reqGetTrafficConditionUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetTrafficConditionUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetTrafficConditionUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.289
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetTrafficConditionUpdate(ReqGetTrafficConditionUpdate reqGetTrafficConditionUpdate, final Duration duration) {
        return fireGetTrafficConditionUpdate(reqGetTrafficConditionUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.290
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetTrafficConditionUpdate() {
        return fireGetTrafficConditionUpdate(new ReqGetTrafficConditionUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetTrafficConditionUpdate(final Duration duration) {
        return fireGetTrafficConditionUpdate(new ReqGetTrafficConditionUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.291
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetLanesInfoUpdate> requestGetLanesInfoUpdate(ReqGetLanesInfoUpdate reqGetLanesInfoUpdate) {
        if (reqGetLanesInfoUpdate.getResponse() == null) {
            reqGetLanesInfoUpdate.setResponse(new ResGetLanesInfoUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetLanesInfoUpdate.setComponent(str);
            reqGetLanesInfoUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetLanesInfoUpdate));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetLanesInfoUpdate> requestGetLanesInfoUpdate(ReqGetLanesInfoUpdate reqGetLanesInfoUpdate, final Duration duration) {
        return requestGetLanesInfoUpdate(reqGetLanesInfoUpdate).thenCompose((Function<? super ResGetLanesInfoUpdate, ? extends CompletionStage<U>>) new Function<ResGetLanesInfoUpdate, CompletableFuture<ResGetLanesInfoUpdate>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.292
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetLanesInfoUpdate> apply(ResGetLanesInfoUpdate resGetLanesInfoUpdate) {
                return CommonUtil.delayAsync(resGetLanesInfoUpdate, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetLanesInfoUpdate> requestGetLanesInfoUpdate() {
        return requestGetLanesInfoUpdate(new ReqGetLanesInfoUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetLanesInfoUpdate> requestGetLanesInfoUpdate(Duration duration) {
        return requestGetLanesInfoUpdate(new ReqGetLanesInfoUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetLanesInfoUpdate(ReqGetLanesInfoUpdate reqGetLanesInfoUpdate) {
        reqGetLanesInfoUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetLanesInfoUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetLanesInfoUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.293
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetLanesInfoUpdate(ReqGetLanesInfoUpdate reqGetLanesInfoUpdate, final Duration duration) {
        return fireGetLanesInfoUpdate(reqGetLanesInfoUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.294
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetLanesInfoUpdate() {
        return fireGetLanesInfoUpdate(new ReqGetLanesInfoUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetLanesInfoUpdate(final Duration duration) {
        return fireGetLanesInfoUpdate(new ReqGetLanesInfoUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.295
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetCommutePush> requestGetCommutePush(ReqGetCommutePush reqGetCommutePush) {
        if (reqGetCommutePush.getResponse() == null) {
            reqGetCommutePush.setResponse(new ResGetCommutePush());
        }
        String str = this.component;
        if (str != null) {
            reqGetCommutePush.setComponent(str);
            reqGetCommutePush.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCommutePush));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetCommutePush> requestGetCommutePush(ReqGetCommutePush reqGetCommutePush, final Duration duration) {
        return requestGetCommutePush(reqGetCommutePush).thenCompose((Function<? super ResGetCommutePush, ? extends CompletionStage<U>>) new Function<ResGetCommutePush, CompletableFuture<ResGetCommutePush>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.296
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetCommutePush> apply(ResGetCommutePush resGetCommutePush) {
                return CommonUtil.delayAsync(resGetCommutePush, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetCommutePush> requestGetCommutePush() {
        return requestGetCommutePush(new ReqGetCommutePush());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetCommutePush> requestGetCommutePush(Duration duration) {
        return requestGetCommutePush(new ReqGetCommutePush(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetCommutePush(ReqGetCommutePush reqGetCommutePush) {
        reqGetCommutePush.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetCommutePush.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetCommutePush)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.297
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetCommutePush(ReqGetCommutePush reqGetCommutePush, final Duration duration) {
        return fireGetCommutePush(reqGetCommutePush).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.298
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetCommutePush() {
        return fireGetCommutePush(new ReqGetCommutePush());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetCommutePush(final Duration duration) {
        return fireGetCommutePush(new ReqGetCommutePush()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.299
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetAvailableRestoreGuidance> requestGetAvailableRestoreGuidance(ReqGetAvailableRestoreGuidance reqGetAvailableRestoreGuidance) {
        if (reqGetAvailableRestoreGuidance.getResponse() == null) {
            reqGetAvailableRestoreGuidance.setResponse(new ResGetAvailableRestoreGuidance());
        }
        String str = this.component;
        if (str != null) {
            reqGetAvailableRestoreGuidance.setComponent(str);
            reqGetAvailableRestoreGuidance.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetAvailableRestoreGuidance));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetAvailableRestoreGuidance> requestGetAvailableRestoreGuidance(ReqGetAvailableRestoreGuidance reqGetAvailableRestoreGuidance, final Duration duration) {
        return requestGetAvailableRestoreGuidance(reqGetAvailableRestoreGuidance).thenCompose((Function<? super ResGetAvailableRestoreGuidance, ? extends CompletionStage<U>>) new Function<ResGetAvailableRestoreGuidance, CompletableFuture<ResGetAvailableRestoreGuidance>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.300
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetAvailableRestoreGuidance> apply(ResGetAvailableRestoreGuidance resGetAvailableRestoreGuidance) {
                return CommonUtil.delayAsync(resGetAvailableRestoreGuidance, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetAvailableRestoreGuidance> requestGetAvailableRestoreGuidance() {
        return requestGetAvailableRestoreGuidance(new ReqGetAvailableRestoreGuidance());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetAvailableRestoreGuidance> requestGetAvailableRestoreGuidance(Duration duration) {
        return requestGetAvailableRestoreGuidance(new ReqGetAvailableRestoreGuidance(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetAvailableRestoreGuidance(ReqGetAvailableRestoreGuidance reqGetAvailableRestoreGuidance) {
        reqGetAvailableRestoreGuidance.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetAvailableRestoreGuidance.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetAvailableRestoreGuidance)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.301
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetAvailableRestoreGuidance(ReqGetAvailableRestoreGuidance reqGetAvailableRestoreGuidance, final Duration duration) {
        return fireGetAvailableRestoreGuidance(reqGetAvailableRestoreGuidance).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.302
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetAvailableRestoreGuidance() {
        return fireGetAvailableRestoreGuidance(new ReqGetAvailableRestoreGuidance());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetAvailableRestoreGuidance(final Duration duration) {
        return fireGetAvailableRestoreGuidance(new ReqGetAvailableRestoreGuidance()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.303
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestSETELServiceStatus> requestGetRequestSETELServiceStatus(ReqGetRequestSETELServiceStatus reqGetRequestSETELServiceStatus) {
        if (reqGetRequestSETELServiceStatus.getResponse() == null) {
            reqGetRequestSETELServiceStatus.setResponse(new ResGetRequestSETELServiceStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetRequestSETELServiceStatus.setComponent(str);
            reqGetRequestSETELServiceStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestSETELServiceStatus));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestSETELServiceStatus> requestGetRequestSETELServiceStatus(ReqGetRequestSETELServiceStatus reqGetRequestSETELServiceStatus, final Duration duration) {
        return requestGetRequestSETELServiceStatus(reqGetRequestSETELServiceStatus).thenCompose((Function<? super ResGetRequestSETELServiceStatus, ? extends CompletionStage<U>>) new Function<ResGetRequestSETELServiceStatus, CompletableFuture<ResGetRequestSETELServiceStatus>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.304
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRequestSETELServiceStatus> apply(ResGetRequestSETELServiceStatus resGetRequestSETELServiceStatus) {
                return CommonUtil.delayAsync(resGetRequestSETELServiceStatus, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestSETELServiceStatus> requestGetRequestSETELServiceStatus() {
        return requestGetRequestSETELServiceStatus(new ReqGetRequestSETELServiceStatus());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestSETELServiceStatus> requestGetRequestSETELServiceStatus(Duration duration) {
        return requestGetRequestSETELServiceStatus(new ReqGetRequestSETELServiceStatus(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestSETELServiceStatus(ReqGetRequestSETELServiceStatus reqGetRequestSETELServiceStatus) {
        reqGetRequestSETELServiceStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRequestSETELServiceStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestSETELServiceStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.305
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestSETELServiceStatus(ReqGetRequestSETELServiceStatus reqGetRequestSETELServiceStatus, final Duration duration) {
        return fireGetRequestSETELServiceStatus(reqGetRequestSETELServiceStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.306
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestSETELServiceStatus() {
        return fireGetRequestSETELServiceStatus(new ReqGetRequestSETELServiceStatus());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestSETELServiceStatus(final Duration duration) {
        return fireGetRequestSETELServiceStatus(new ReqGetRequestSETELServiceStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.307
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestSETELData> requestGetRequestSETELData(ReqGetRequestSETELData reqGetRequestSETELData) {
        if (reqGetRequestSETELData.getResponse() == null) {
            reqGetRequestSETELData.setResponse(new ResGetRequestSETELData());
        }
        String str = this.component;
        if (str != null) {
            reqGetRequestSETELData.setComponent(str);
            reqGetRequestSETELData.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestSETELData));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestSETELData> requestGetRequestSETELData(ReqGetRequestSETELData reqGetRequestSETELData, final Duration duration) {
        return requestGetRequestSETELData(reqGetRequestSETELData).thenCompose((Function<? super ResGetRequestSETELData, ? extends CompletionStage<U>>) new Function<ResGetRequestSETELData, CompletableFuture<ResGetRequestSETELData>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.308
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRequestSETELData> apply(ResGetRequestSETELData resGetRequestSETELData) {
                return CommonUtil.delayAsync(resGetRequestSETELData, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestSETELData> requestGetRequestSETELData() {
        return requestGetRequestSETELData(new ReqGetRequestSETELData());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestSETELData> requestGetRequestSETELData(Duration duration) {
        return requestGetRequestSETELData(new ReqGetRequestSETELData(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestSETELData(ReqGetRequestSETELData reqGetRequestSETELData) {
        reqGetRequestSETELData.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRequestSETELData.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestSETELData)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.309
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestSETELData(ReqGetRequestSETELData reqGetRequestSETELData, final Duration duration) {
        return fireGetRequestSETELData(reqGetRequestSETELData).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.310
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestSETELData() {
        return fireGetRequestSETELData(new ReqGetRequestSETELData());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestSETELData(final Duration duration) {
        return fireGetRequestSETELData(new ReqGetRequestSETELData()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.311
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestSETELDetail> requestGetRequestSETELDetail(ReqGetRequestSETELDetail reqGetRequestSETELDetail) {
        if (reqGetRequestSETELDetail.getResponse() == null) {
            reqGetRequestSETELDetail.setResponse(new ResGetRequestSETELDetail());
        }
        String str = this.component;
        if (str != null) {
            reqGetRequestSETELDetail.setComponent(str);
            reqGetRequestSETELDetail.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestSETELDetail));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestSETELDetail> requestGetRequestSETELDetail(ReqGetRequestSETELDetail reqGetRequestSETELDetail, final Duration duration) {
        return requestGetRequestSETELDetail(reqGetRequestSETELDetail).thenCompose((Function<? super ResGetRequestSETELDetail, ? extends CompletionStage<U>>) new Function<ResGetRequestSETELDetail, CompletableFuture<ResGetRequestSETELDetail>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.312
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRequestSETELDetail> apply(ResGetRequestSETELDetail resGetRequestSETELDetail) {
                return CommonUtil.delayAsync(resGetRequestSETELDetail, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestSETELDetail> requestGetRequestSETELDetail() {
        return requestGetRequestSETELDetail(new ReqGetRequestSETELDetail());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestSETELDetail> requestGetRequestSETELDetail(Duration duration) {
        return requestGetRequestSETELDetail(new ReqGetRequestSETELDetail(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestSETELDetail(ReqGetRequestSETELDetail reqGetRequestSETELDetail) {
        reqGetRequestSETELDetail.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRequestSETELDetail.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestSETELDetail)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.313
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestSETELDetail(ReqGetRequestSETELDetail reqGetRequestSETELDetail, final Duration duration) {
        return fireGetRequestSETELDetail(reqGetRequestSETELDetail).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.314
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestSETELDetail() {
        return fireGetRequestSETELDetail(new ReqGetRequestSETELDetail());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestSETELDetail(final Duration duration) {
        return fireGetRequestSETELDetail(new ReqGetRequestSETELDetail()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.315
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetSwitchVRCommand> requestGetSwitchVRCommand(ReqGetSwitchVRCommand reqGetSwitchVRCommand) {
        if (reqGetSwitchVRCommand.getResponse() == null) {
            reqGetSwitchVRCommand.setResponse(new ResGetSwitchVRCommand());
        }
        String str = this.component;
        if (str != null) {
            reqGetSwitchVRCommand.setComponent(str);
            reqGetSwitchVRCommand.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSwitchVRCommand));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetSwitchVRCommand> requestGetSwitchVRCommand(ReqGetSwitchVRCommand reqGetSwitchVRCommand, final Duration duration) {
        return requestGetSwitchVRCommand(reqGetSwitchVRCommand).thenCompose((Function<? super ResGetSwitchVRCommand, ? extends CompletionStage<U>>) new Function<ResGetSwitchVRCommand, CompletableFuture<ResGetSwitchVRCommand>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.316
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetSwitchVRCommand> apply(ResGetSwitchVRCommand resGetSwitchVRCommand) {
                return CommonUtil.delayAsync(resGetSwitchVRCommand, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetSwitchVRCommand> requestGetSwitchVRCommand() {
        return requestGetSwitchVRCommand(new ReqGetSwitchVRCommand());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetSwitchVRCommand> requestGetSwitchVRCommand(Duration duration) {
        return requestGetSwitchVRCommand(new ReqGetSwitchVRCommand(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetSwitchVRCommand(ReqGetSwitchVRCommand reqGetSwitchVRCommand) {
        reqGetSwitchVRCommand.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetSwitchVRCommand.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetSwitchVRCommand)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.317
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetSwitchVRCommand(ReqGetSwitchVRCommand reqGetSwitchVRCommand, final Duration duration) {
        return fireGetSwitchVRCommand(reqGetSwitchVRCommand).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.318
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetSwitchVRCommand() {
        return fireGetSwitchVRCommand(new ReqGetSwitchVRCommand());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetSwitchVRCommand(final Duration duration) {
        return fireGetSwitchVRCommand(new ReqGetSwitchVRCommand()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.319
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetVRMsgCommandUpdate> requestGetVRMsgCommandUpdate(ReqGetVRMsgCommandUpdate reqGetVRMsgCommandUpdate) {
        if (reqGetVRMsgCommandUpdate.getResponse() == null) {
            reqGetVRMsgCommandUpdate.setResponse(new ResGetVRMsgCommandUpdate());
        }
        String str = this.component;
        if (str != null) {
            reqGetVRMsgCommandUpdate.setComponent(str);
            reqGetVRMsgCommandUpdate.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetVRMsgCommandUpdate));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetVRMsgCommandUpdate> requestGetVRMsgCommandUpdate(ReqGetVRMsgCommandUpdate reqGetVRMsgCommandUpdate, final Duration duration) {
        return requestGetVRMsgCommandUpdate(reqGetVRMsgCommandUpdate).thenCompose((Function<? super ResGetVRMsgCommandUpdate, ? extends CompletionStage<U>>) new Function<ResGetVRMsgCommandUpdate, CompletableFuture<ResGetVRMsgCommandUpdate>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.320
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetVRMsgCommandUpdate> apply(ResGetVRMsgCommandUpdate resGetVRMsgCommandUpdate) {
                return CommonUtil.delayAsync(resGetVRMsgCommandUpdate, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetVRMsgCommandUpdate> requestGetVRMsgCommandUpdate() {
        return requestGetVRMsgCommandUpdate(new ReqGetVRMsgCommandUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetVRMsgCommandUpdate> requestGetVRMsgCommandUpdate(Duration duration) {
        return requestGetVRMsgCommandUpdate(new ReqGetVRMsgCommandUpdate(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetVRMsgCommandUpdate(ReqGetVRMsgCommandUpdate reqGetVRMsgCommandUpdate) {
        reqGetVRMsgCommandUpdate.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetVRMsgCommandUpdate.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetVRMsgCommandUpdate)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.321
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetVRMsgCommandUpdate(ReqGetVRMsgCommandUpdate reqGetVRMsgCommandUpdate, final Duration duration) {
        return fireGetVRMsgCommandUpdate(reqGetVRMsgCommandUpdate).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.322
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetVRMsgCommandUpdate() {
        return fireGetVRMsgCommandUpdate(new ReqGetVRMsgCommandUpdate());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetVRMsgCommandUpdate(final Duration duration) {
        return fireGetVRMsgCommandUpdate(new ReqGetVRMsgCommandUpdate()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.323
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetGetLoginStatus> requestGetGetLoginStatus(ReqGetGetLoginStatus reqGetGetLoginStatus) {
        if (reqGetGetLoginStatus.getResponse() == null) {
            reqGetGetLoginStatus.setResponse(new ResGetGetLoginStatus());
        }
        String str = this.component;
        if (str != null) {
            reqGetGetLoginStatus.setComponent(str);
            reqGetGetLoginStatus.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGetLoginStatus));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetGetLoginStatus> requestGetGetLoginStatus(ReqGetGetLoginStatus reqGetGetLoginStatus, final Duration duration) {
        return requestGetGetLoginStatus(reqGetGetLoginStatus).thenCompose((Function<? super ResGetGetLoginStatus, ? extends CompletionStage<U>>) new Function<ResGetGetLoginStatus, CompletableFuture<ResGetGetLoginStatus>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.324
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetGetLoginStatus> apply(ResGetGetLoginStatus resGetGetLoginStatus) {
                return CommonUtil.delayAsync(resGetGetLoginStatus, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetGetLoginStatus> requestGetGetLoginStatus() {
        return requestGetGetLoginStatus(new ReqGetGetLoginStatus());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetGetLoginStatus> requestGetGetLoginStatus(Duration duration) {
        return requestGetGetLoginStatus(new ReqGetGetLoginStatus(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetGetLoginStatus(ReqGetGetLoginStatus reqGetGetLoginStatus) {
        reqGetGetLoginStatus.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetGetLoginStatus.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGetLoginStatus)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.325
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetGetLoginStatus(ReqGetGetLoginStatus reqGetGetLoginStatus, final Duration duration) {
        return fireGetGetLoginStatus(reqGetGetLoginStatus).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.326
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetGetLoginStatus() {
        return fireGetGetLoginStatus(new ReqGetGetLoginStatus());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetGetLoginStatus(final Duration duration) {
        return fireGetGetLoginStatus(new ReqGetGetLoginStatus()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.327
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetGetFavoriteList> requestGetGetFavoriteList(ReqGetGetFavoriteList reqGetGetFavoriteList) {
        if (reqGetGetFavoriteList.getResponse() == null) {
            reqGetGetFavoriteList.setResponse(new ResGetGetFavoriteList());
        }
        String str = this.component;
        if (str != null) {
            reqGetGetFavoriteList.setComponent(str);
            reqGetGetFavoriteList.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGetFavoriteList));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetGetFavoriteList> requestGetGetFavoriteList(ReqGetGetFavoriteList reqGetGetFavoriteList, final Duration duration) {
        return requestGetGetFavoriteList(reqGetGetFavoriteList).thenCompose((Function<? super ResGetGetFavoriteList, ? extends CompletionStage<U>>) new Function<ResGetGetFavoriteList, CompletableFuture<ResGetGetFavoriteList>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.328
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetGetFavoriteList> apply(ResGetGetFavoriteList resGetGetFavoriteList) {
                return CommonUtil.delayAsync(resGetGetFavoriteList, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetGetFavoriteList> requestGetGetFavoriteList() {
        return requestGetGetFavoriteList(new ReqGetGetFavoriteList());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetGetFavoriteList> requestGetGetFavoriteList(Duration duration) {
        return requestGetGetFavoriteList(new ReqGetGetFavoriteList(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetGetFavoriteList(ReqGetGetFavoriteList reqGetGetFavoriteList) {
        reqGetGetFavoriteList.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetGetFavoriteList.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetGetFavoriteList)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.329
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetGetFavoriteList(ReqGetGetFavoriteList reqGetGetFavoriteList, final Duration duration) {
        return fireGetGetFavoriteList(reqGetGetFavoriteList).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.330
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetGetFavoriteList() {
        return fireGetGetFavoriteList(new ReqGetGetFavoriteList());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetGetFavoriteList(final Duration duration) {
        return fireGetGetFavoriteList(new ReqGetGetFavoriteList()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.331
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestAddFavorite> requestGetRequestAddFavorite(ReqGetRequestAddFavorite reqGetRequestAddFavorite) {
        if (reqGetRequestAddFavorite.getResponse() == null) {
            reqGetRequestAddFavorite.setResponse(new ResGetRequestAddFavorite());
        }
        String str = this.component;
        if (str != null) {
            reqGetRequestAddFavorite.setComponent(str);
            reqGetRequestAddFavorite.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestAddFavorite));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestAddFavorite> requestGetRequestAddFavorite(ReqGetRequestAddFavorite reqGetRequestAddFavorite, final Duration duration) {
        return requestGetRequestAddFavorite(reqGetRequestAddFavorite).thenCompose((Function<? super ResGetRequestAddFavorite, ? extends CompletionStage<U>>) new Function<ResGetRequestAddFavorite, CompletableFuture<ResGetRequestAddFavorite>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.332
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRequestAddFavorite> apply(ResGetRequestAddFavorite resGetRequestAddFavorite) {
                return CommonUtil.delayAsync(resGetRequestAddFavorite, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestAddFavorite> requestGetRequestAddFavorite() {
        return requestGetRequestAddFavorite(new ReqGetRequestAddFavorite());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestAddFavorite> requestGetRequestAddFavorite(Duration duration) {
        return requestGetRequestAddFavorite(new ReqGetRequestAddFavorite(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestAddFavorite(ReqGetRequestAddFavorite reqGetRequestAddFavorite) {
        reqGetRequestAddFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRequestAddFavorite.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestAddFavorite)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.333
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestAddFavorite(ReqGetRequestAddFavorite reqGetRequestAddFavorite, final Duration duration) {
        return fireGetRequestAddFavorite(reqGetRequestAddFavorite).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.334
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestAddFavorite() {
        return fireGetRequestAddFavorite(new ReqGetRequestAddFavorite());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestAddFavorite(final Duration duration) {
        return fireGetRequestAddFavorite(new ReqGetRequestAddFavorite()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.335
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestUpdateFavorite> requestGetRequestUpdateFavorite(ReqGetRequestUpdateFavorite reqGetRequestUpdateFavorite) {
        if (reqGetRequestUpdateFavorite.getResponse() == null) {
            reqGetRequestUpdateFavorite.setResponse(new ResGetRequestUpdateFavorite());
        }
        String str = this.component;
        if (str != null) {
            reqGetRequestUpdateFavorite.setComponent(str);
            reqGetRequestUpdateFavorite.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestUpdateFavorite));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestUpdateFavorite> requestGetRequestUpdateFavorite(ReqGetRequestUpdateFavorite reqGetRequestUpdateFavorite, final Duration duration) {
        return requestGetRequestUpdateFavorite(reqGetRequestUpdateFavorite).thenCompose((Function<? super ResGetRequestUpdateFavorite, ? extends CompletionStage<U>>) new Function<ResGetRequestUpdateFavorite, CompletableFuture<ResGetRequestUpdateFavorite>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.336
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRequestUpdateFavorite> apply(ResGetRequestUpdateFavorite resGetRequestUpdateFavorite) {
                return CommonUtil.delayAsync(resGetRequestUpdateFavorite, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestUpdateFavorite> requestGetRequestUpdateFavorite() {
        return requestGetRequestUpdateFavorite(new ReqGetRequestUpdateFavorite());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestUpdateFavorite> requestGetRequestUpdateFavorite(Duration duration) {
        return requestGetRequestUpdateFavorite(new ReqGetRequestUpdateFavorite(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestUpdateFavorite(ReqGetRequestUpdateFavorite reqGetRequestUpdateFavorite) {
        reqGetRequestUpdateFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRequestUpdateFavorite.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestUpdateFavorite)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.337
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestUpdateFavorite(ReqGetRequestUpdateFavorite reqGetRequestUpdateFavorite, final Duration duration) {
        return fireGetRequestUpdateFavorite(reqGetRequestUpdateFavorite).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.338
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestUpdateFavorite() {
        return fireGetRequestUpdateFavorite(new ReqGetRequestUpdateFavorite());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestUpdateFavorite(final Duration duration) {
        return fireGetRequestUpdateFavorite(new ReqGetRequestUpdateFavorite()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.339
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestRemoveFavorite> requestGetRequestRemoveFavorite(ReqGetRequestRemoveFavorite reqGetRequestRemoveFavorite) {
        if (reqGetRequestRemoveFavorite.getResponse() == null) {
            reqGetRequestRemoveFavorite.setResponse(new ResGetRequestRemoveFavorite());
        }
        String str = this.component;
        if (str != null) {
            reqGetRequestRemoveFavorite.setComponent(str);
            reqGetRequestRemoveFavorite.getResponse().setComponent(this.component);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestRemoveFavorite));
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestRemoveFavorite> requestGetRequestRemoveFavorite(ReqGetRequestRemoveFavorite reqGetRequestRemoveFavorite, final Duration duration) {
        return requestGetRequestRemoveFavorite(reqGetRequestRemoveFavorite).thenCompose((Function<? super ResGetRequestRemoveFavorite, ? extends CompletionStage<U>>) new Function<ResGetRequestRemoveFavorite, CompletableFuture<ResGetRequestRemoveFavorite>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.340
            @Override // java9.util.function.Function
            public CompletableFuture<ResGetRequestRemoveFavorite> apply(ResGetRequestRemoveFavorite resGetRequestRemoveFavorite) {
                return CommonUtil.delayAsync(resGetRequestRemoveFavorite, duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestRemoveFavorite> requestGetRequestRemoveFavorite() {
        return requestGetRequestRemoveFavorite(new ReqGetRequestRemoveFavorite());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<ResGetRequestRemoveFavorite> requestGetRequestRemoveFavorite(Duration duration) {
        return requestGetRequestRemoveFavorite(new ReqGetRequestRemoveFavorite(), duration);
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestRemoveFavorite(ReqGetRequestRemoveFavorite reqGetRequestRemoveFavorite) {
        reqGetRequestRemoveFavorite.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqGetRequestRemoveFavorite.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqGetRequestRemoveFavorite)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.341
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return EcarxNavApiRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestRemoveFavorite(ReqGetRequestRemoveFavorite reqGetRequestRemoveFavorite, final Duration duration) {
        return fireGetRequestRemoveFavorite(reqGetRequestRemoveFavorite).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.342
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestRemoveFavorite() {
        return fireGetRequestRemoveFavorite(new ReqGetRequestRemoveFavorite());
    }

    @Override // api.requests.com.neusoft.ecarx.ecarxnavapi.IEcarxNavApiRequests
    public CompletableFuture<Void> fireGetRequestRemoveFavorite(final Duration duration) {
        return fireGetRequestRemoveFavorite(new ReqGetRequestRemoveFavorite()).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests.343
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, EcarxNavApiRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }
}
