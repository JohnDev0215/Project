package com.neusoft.na.ocservice.ecarxnavsdk.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import api.notifications.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseNotifications;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyCallUpStatus;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyRequestExpiredDate;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyResponseExpiredDate;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiNotifications;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyADAreaUpdate;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyArrivedDest;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyAvailableRestoreGuidance;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyCommutePush;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyDayNightStatusUpdate;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyFavoritesInfoUpdate;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyGetFavoriteList;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyGetLoginStatus;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyGuidanceInfoUpdate;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyHighwayInfoUpdate;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyLanesInfoUpdate;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyMapStatusUpdate;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyMuteModeUpdate;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyNaviStatusUpdate;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyRequestAddFavorite;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyRequestRemoveFavorite;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyRequestSETELData;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyRequestSETELDetail;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyRequestSETELServiceStatus;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyRequestUpdateFavorite;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyRouteInfoUpdate;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyRouteSelectedUpdate;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyRouteViewStatusUpdate;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyServiceStatusUpdate;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifySwitchVRCommand;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyTrafficConditionUpdate;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyTrafficStatusUpdate;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyVRMsgCommandUpdate;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyViewModeUpdate;
import api.notifications.com.neusoft.ecarx.ecarxnavapi.NotifyZoomStatusUpdate;
import api.notifications.com.neusoft.ecarx.ecarxsysapiinverse.EcarxSysApiInverseNotifications;
import api.notifications.com.neusoft.ecarx.ecarxsysapiinverse.NotifyQueryCurrentWeatherInfo;
import api.notifications.com.neusoft.ecarx.ecarxsysapiinverse.NotifyQueryFutureWeatherInfo;
import api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqGetResponseExpiredDate;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetExpiredDate;
import api.requests.com.neusoft.ecarx.ecarxnavapi.EcarxNavApiRequests;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqAddFavorite;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqAlongTheWaySearch;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqEditFavoritePois;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqGetFavoritePois;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqGetFrontTraffic;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqGetGuideInfo;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqGetHighwayExitInfo;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqGetHistoryPois;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqGetRouteInfo;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqGetScaleStatus;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqGetTrafficSummaryInfo;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqGoFavorite;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqHasLogin;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqMapZoomInOut;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqModifyNaviVia;
import api.requests.com.neusoft.ecarx.ecarxnavapi.ReqNaviFullView;
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
import api.requests.com.neusoft.ecarx.ecarxsysapiinverse.EcarxSysApiInverseRequests;
import api.requests.com.neusoft.ecarx.ecarxsysapiinverse.ReqUpdateCurrentWeatherInfo;
import api.requests.com.neusoft.ecarx.ecarxsysapiinverse.ReqUpdateFutureWeatherInfo;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetResponseExpiredDate;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResAlongTheWaySearch;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResCancelNavi;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResEditFavoritePois;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetFavoritePois;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetFrontTraffic;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetGuideInfo;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetHighwayExitInfo;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetHistoryPois;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetNaviMuteMode;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetRouteInfo;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetScaleStatus;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGetTrafficSummaryInfo;
import api.responses.com.neusoft.ecarx.ecarxnavapi.ResGoFavorite;
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
import api.structs.com.neusoft.common.ActivateStatus;
import api.structs.com.neusoft.common.ExpiredDateStatus;
import api.structs.com.neusoft.ecarx.CategoryInfo;
import api.structs.com.neusoft.ecarx.CurrentWeatherForecastInfo;
import api.structs.com.neusoft.ecarx.CurrentWeatherInfoState;
import api.structs.com.neusoft.ecarx.DataType;
import api.structs.com.neusoft.ecarx.ExtPoiInfo;
import api.structs.com.neusoft.ecarx.FavoriteItem;
import api.structs.com.neusoft.ecarx.FavoritePoiInfo;
import api.structs.com.neusoft.ecarx.FavoriteType;
import api.structs.com.neusoft.ecarx.FunctionType;
import api.structs.com.neusoft.ecarx.FutureWeatherForecastInfo;
import api.structs.com.neusoft.ecarx.FutureWeatherInfoState;
import api.structs.com.neusoft.ecarx.GeoAddressInfo;
import api.structs.com.neusoft.ecarx.GeoPosition;
import api.structs.com.neusoft.ecarx.GuidanceInfo;
import api.structs.com.neusoft.ecarx.HighwayExitInfo;
import api.structs.com.neusoft.ecarx.HighwayExitType;
import api.structs.com.neusoft.ecarx.LatLng;
import api.structs.com.neusoft.ecarx.LocationInfo;
import api.structs.com.neusoft.ecarx.MuteType;
import api.structs.com.neusoft.ecarx.NameInfo;
import api.structs.com.neusoft.ecarx.OpeningHours;
import api.structs.com.neusoft.ecarx.PoiInfo;
import api.structs.com.neusoft.ecarx.ProcessedSETELDataInfo;
import api.structs.com.neusoft.ecarx.ProcessedSETELDetailDataInfo;
import api.structs.com.neusoft.ecarx.RemoveFavoriteItemCommand;
import api.structs.com.neusoft.ecarx.RequestRoutePlanOrNaviType;
import api.structs.com.neusoft.ecarx.ResultCode;
import api.structs.com.neusoft.ecarx.ResumeGuidanceAction;
import api.structs.com.neusoft.ecarx.RoadTrafficStatus;
import api.structs.com.neusoft.ecarx.RouteDestActionType;
import api.structs.com.neusoft.ecarx.RouteInfo;
import api.structs.com.neusoft.ecarx.SETELDetailInfo;
import api.structs.com.neusoft.ecarx.SETELPoiInfo;
import api.structs.com.neusoft.ecarx.SETELServiceStatus;
import api.structs.com.neusoft.ecarx.SearchActionType;
import api.structs.com.neusoft.ecarx.SearchType;
import api.structs.com.neusoft.ecarx.ServiceStatus;
import api.structs.com.neusoft.ecarx.ShowLocationActionType;
import api.structs.com.neusoft.ecarx.SpecialPoiNaviType;
import api.structs.com.neusoft.ecarx.SpecialPoiType;
import api.structs.com.neusoft.ecarx.StrategyType;
import api.structs.com.neusoft.ecarx.SuggestionCity;
import api.structs.com.neusoft.ecarx.TimePeriod;
import api.structs.com.neusoft.ecarx.TrafficType;
import api.structs.com.neusoft.ecarx.VRCommand;
import api.structs.com.neusoft.ecarx.ViewModeType;
import api.structs.com.neusoft.ecarx.ZoomInOutType;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.communication.transports.websocketclient.OneCoreClientEndpoint;
import com.neusoft.de.nts.OneCoreTest.communication.utils.OneCoreWSContextUtil;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.na.common.ClientEndpointManger;
import com.neusoft.na.common.ClientEndpointMangerCallback;
import com.neusoft.na.ocservice.Forecast;
import com.neusoft.na.ocservice.ForecastWeatherInfo;
import com.neusoft.na.ocservice.GasStationsInfo;
import com.neusoft.na.ocservice.GeoPos;
import com.neusoft.na.ocservice.IAddItemToFavoritesCallBack;
import com.neusoft.na.ocservice.IAlongTheWaySearchCallBack;
import com.neusoft.na.ocservice.IBooleanCallback;
import com.neusoft.na.ocservice.IExpirationDateCallBack;
import com.neusoft.na.ocservice.IFavoritesCallBack;
import com.neusoft.na.ocservice.IForecastWeatherCallBack;
import com.neusoft.na.ocservice.IGetCloudRegistrationStatusCallBack;
import com.neusoft.na.ocservice.IGetFavoriteListCallBack;
import com.neusoft.na.ocservice.IGetFavoritePoisCallBack;
import com.neusoft.na.ocservice.IGetFrontTrafficCallBack;
import com.neusoft.na.ocservice.IGetGuideInfoCallBack;
import com.neusoft.na.ocservice.IGetHighwayExitInfo;
import com.neusoft.na.ocservice.IGetHistoryPoisCallBack;
import com.neusoft.na.ocservice.IGetMapLevelInfoCallBack;
import com.neusoft.na.ocservice.IGetNaviMuteModeCallBack;
import com.neusoft.na.ocservice.IGetRouteInfoCallBack;
import com.neusoft.na.ocservice.IGetTrafficSummaryInfocCallBack;
import com.neusoft.na.ocservice.ILoggedInStateCallback;
import com.neusoft.na.ocservice.IObserveWeatherCallBack;
import com.neusoft.na.ocservice.IRequestGeoDecoderCallBack;
import com.neusoft.na.ocservice.IRequestRoutePlanOrNavi;
import com.neusoft.na.ocservice.IRequestSetelDetailCallBack;
import com.neusoft.na.ocservice.IResultCodeCallBack;
import com.neusoft.na.ocservice.IResultGasStationCallBack;
import com.neusoft.na.ocservice.ISearchAroundCallBack;
import com.neusoft.na.ocservice.ISearchByKeywordCallBack;
import com.neusoft.na.ocservice.ISetelDataCallBack;
import com.neusoft.na.ocservice.IShowMyLocationCallBack;
import com.neusoft.na.ocservice.ObserveWeatherInfo;
import com.neusoft.na.ocservice.OpeningHour;
import com.neusoft.na.ocservice.PoiType;
import com.neusoft.na.ocservice.RecommendedGasStationsData;
import com.neusoft.na.ocservice.SetelData;
import com.neusoft.na.ocservice.SetelDataCallBackInfo;
import com.neusoft.na.ocservice.SetelDetailData;
import com.neusoft.na.ocservice.SetelResultData;
import com.neusoft.na.ocservice.SetelSearchData;
import com.neusoft.na.ocservice.TimeSlot;
import com.neusoft.na.ocservice.TrafficInfo;
import com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi;
import com.neusoft.na.ocservice.ecarxnavsdk.INotificationListener;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java9.util.function.Consumer;
import java9.util.function.Function;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes2.dex */
public class NavigationApiOctaImpl implements INavigationApi, ClientEndpointMangerCallback {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String CLOSE_MAINACTIVITY = "com.neusoft.na.mots.closeMainActivity";
    private static String TAG = "NavigationApiOctaImpl";
    private ClientEndpointManger cEManger;
    private CommonSysApiInverseNotifications commonNotifications;
    private Context context;
    private Handler handler;
    private OneCoreWSContextUtil motsWsContext;
    private INotificationListener notificationListener;
    private EcarxNavApiNotifications notifications;
    private EcarxNavApiRequests requests;
    private CommonSysApiInverseRequests requestsCommon;
    private EcarxSysApiInverseNotifications sysNotifications;
    private EcarxSysApiInverseRequests sysRequests;
    private List<IAttribute> remoteAttributes = new ArrayList();
    private List<Subscription> remoteSubscriptions = new ArrayList();
    private boolean hasUnsentMSG = false;
    private UnsentMethod unsentMsg = UnsentMethod.No;
    private boolean isServerConnect = false;

    enum UnsentMethod {
        No,
        openDestinationSearch,
        showMyLocation,
        searchByKeyword,
        searchAround,
        specialPoiNavi,
        requestRoutePlanOrNavi,
        ResumeGuidance
    }

    @Override // com.neusoft.na.common.ClientEndpointMangerCallback
    public void onServerConnect(OneCoreWSContextUtil oneCoreWSContextUtil) {
        Log.i(TAG, "onMotsConnected: isServerConnect:" + this.isServerConnect);
        if (this.isServerConnect) {
            return;
        }
        this.isServerConnect = true;
        this.motsWsContext = oneCoreWSContextUtil;
        this.requests = new EcarxNavApiRequests(this.motsWsContext);
        this.notifications = new EcarxNavApiNotifications(this.motsWsContext);
        this.sysNotifications = new EcarxSysApiInverseNotifications(this.motsWsContext);
        this.sysRequests = new EcarxSysApiInverseRequests(this.motsWsContext);
        this.requestsCommon = new CommonSysApiInverseRequests(this.motsWsContext);
        CommonSysApiInverseNotifications commonSysApiInverseNotifications = new CommonSysApiInverseNotifications(this.motsWsContext);
        this.commonNotifications = commonSysApiInverseNotifications;
        IAttribute<NotifyCallUpStatus> iAttributeCallUpStatusAttribute = commonSysApiInverseNotifications.callUpStatusAttribute();
        iAttributeCallUpStatusAttribute.signIn();
        this.remoteAttributes.add(iAttributeCallUpStatusAttribute);
        this.remoteSubscriptions.add(iAttributeCallUpStatusAttribute.getObservable().subscribe(new Action1<NotifyCallUpStatus>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.1
            @Override // rx.functions.Action1
            public void call(NotifyCallUpStatus notifyCallUpStatus) {
                Log.i(NavigationApiOctaImpl.TAG, "call phone");
                NavigationApiOctaImpl.this.notificationListener.reqPhoneCallByNumber(notifyCallUpStatus.getCallUpStatus().getPhoneNumber());
            }
        }));
        IAttribute<NotifyRequestExpiredDate> iAttributeRequestExpiredDateAttribute = this.commonNotifications.requestExpiredDateAttribute();
        iAttributeRequestExpiredDateAttribute.signIn();
        this.remoteAttributes.add(iAttributeRequestExpiredDateAttribute);
        this.remoteSubscriptions.add(iAttributeRequestExpiredDateAttribute.getObservable().subscribe(new Action1<NotifyRequestExpiredDate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.2
            @Override // rx.functions.Action1
            public void call(NotifyRequestExpiredDate notifyRequestExpiredDate) {
                Log.i(NavigationApiOctaImpl.TAG, "Request expired date.");
                NavigationApiOctaImpl.this.notificationListener.requestExpirationDate(new IExpirationDateCallBack.Stub() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.2.1
                    @Override // com.neusoft.na.ocservice.IExpirationDateCallBack
                    public void callBack(int i, long j, long j2) throws RemoteException {
                        Log.i(NavigationApiOctaImpl.TAG, "status:" + i + "startDate:" + j + "endDate:" + j2);
                        if (i == 0) {
                            NavigationApiOctaImpl.this.requestsCommon.fireSetExpiredDate(new ReqSetExpiredDate.Arguments(1, ExpiredDateStatus.E_SUCCESS, Long.valueOf(j), Long.valueOf(j2)));
                        } else {
                            NavigationApiOctaImpl.this.requestsCommon.fireSetExpiredDate(new ReqSetExpiredDate.Arguments(1, ExpiredDateStatus.E_FAILURE, Long.valueOf(j), Long.valueOf(j2)));
                        }
                    }
                });
            }
        }));
        IAttribute<NotifyResponseExpiredDate> iAttributeResponseExpiredDateAttribute = this.commonNotifications.responseExpiredDateAttribute();
        iAttributeResponseExpiredDateAttribute.signIn();
        this.remoteAttributes.add(iAttributeResponseExpiredDateAttribute);
        this.remoteSubscriptions.add(iAttributeResponseExpiredDateAttribute.getObservable().subscribe(new Action1<NotifyResponseExpiredDate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.3
            @Override // rx.functions.Action1
            public void call(NotifyResponseExpiredDate notifyResponseExpiredDate) {
                Log.i(NavigationApiOctaImpl.TAG, "Response expired date.");
                if (notifyResponseExpiredDate.getResponseExpiredDate().getActivateStatus() == ActivateStatus.E_SUCCESS) {
                    NavigationApiOctaImpl.this.notificationListener.cloudRegistrationStatus(true);
                } else {
                    NavigationApiOctaImpl.this.notificationListener.cloudRegistrationStatus(false);
                }
            }
        }));
        IAttribute<NotifyNaviStatusUpdate> iAttributeNaviStatusUpdateAttribute = this.notifications.naviStatusUpdateAttribute();
        iAttributeNaviStatusUpdateAttribute.signIn();
        this.remoteAttributes.add(iAttributeNaviStatusUpdateAttribute);
        this.remoteSubscriptions.add(iAttributeNaviStatusUpdateAttribute.getObservable().subscribe(new Action1<NotifyNaviStatusUpdate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.4
            @Override // rx.functions.Action1
            public void call(NotifyNaviStatusUpdate notifyNaviStatusUpdate) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyNaviStatusUpdate");
                NavigationApiOctaImpl.this.notificationListener.naviStatusUpdate(notifyNaviStatusUpdate.getNaviStatusUpdate());
            }
        }));
        IAttribute<NotifyGuidanceInfoUpdate> iAttributeGuidanceInfoUpdateAttribute = this.notifications.guidanceInfoUpdateAttribute();
        iAttributeGuidanceInfoUpdateAttribute.signIn();
        this.remoteAttributes.add(iAttributeGuidanceInfoUpdateAttribute);
        this.remoteSubscriptions.add(iAttributeGuidanceInfoUpdateAttribute.getObservable().subscribe(new Action1<NotifyGuidanceInfoUpdate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.5
            @Override // rx.functions.Action1
            public void call(NotifyGuidanceInfoUpdate notifyGuidanceInfoUpdate) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyGuidanceInfoUpdate");
                NavigationApiOctaImpl.this.notificationListener.guidanceInfoUpdate(notifyGuidanceInfoUpdate.getGuidanceInfoUpdate());
            }
        }));
        IAttribute<NotifyMapStatusUpdate> iAttributeMapStatusUpdateAttribute = this.notifications.mapStatusUpdateAttribute();
        iAttributeMapStatusUpdateAttribute.signIn();
        this.remoteAttributes.add(iAttributeMapStatusUpdateAttribute);
        this.remoteSubscriptions.add(iAttributeMapStatusUpdateAttribute.getObservable().subscribe(new Action1<NotifyMapStatusUpdate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.6
            @Override // rx.functions.Action1
            public void call(NotifyMapStatusUpdate notifyMapStatusUpdate) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyMapStatusUpdate");
                NavigationApiOctaImpl.this.notificationListener.mapStatusUpdate(notifyMapStatusUpdate.getMapStatusUpdate());
            }
        }));
        IAttribute<NotifyRouteViewStatusUpdate> iAttributeRouteViewStatusUpdateAttribute = this.notifications.routeViewStatusUpdateAttribute();
        iAttributeRouteViewStatusUpdateAttribute.signIn();
        this.remoteAttributes.add(iAttributeRouteViewStatusUpdateAttribute);
        this.remoteSubscriptions.add(iAttributeRouteViewStatusUpdateAttribute.getObservable().subscribe(new Action1<NotifyRouteViewStatusUpdate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.7
            @Override // rx.functions.Action1
            public void call(NotifyRouteViewStatusUpdate notifyRouteViewStatusUpdate) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyRouteViewStatusUpdate");
                NavigationApiOctaImpl.this.notificationListener.routeViewStatusUpdate(notifyRouteViewStatusUpdate.getRouteViewStatusUpdate());
            }
        }));
        IAttribute<NotifyTrafficStatusUpdate> iAttributeTrafficStatusUpdateAttribute = this.notifications.trafficStatusUpdateAttribute();
        iAttributeTrafficStatusUpdateAttribute.signIn();
        this.remoteAttributes.add(iAttributeTrafficStatusUpdateAttribute);
        this.remoteSubscriptions.add(iAttributeTrafficStatusUpdateAttribute.getObservable().subscribe(new Action1<NotifyTrafficStatusUpdate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.8
            @Override // rx.functions.Action1
            public void call(NotifyTrafficStatusUpdate notifyTrafficStatusUpdate) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyTrafficStatusUpdate");
                NavigationApiOctaImpl.this.notificationListener.trafficStatusUpdate(notifyTrafficStatusUpdate.getTrafficStatusUpdate());
            }
        }));
        IAttribute<NotifyZoomStatusUpdate> iAttributeZoomStatusUpdateAttribute = this.notifications.zoomStatusUpdateAttribute();
        iAttributeZoomStatusUpdateAttribute.signIn();
        this.remoteAttributes.add(iAttributeZoomStatusUpdateAttribute);
        this.remoteSubscriptions.add(iAttributeZoomStatusUpdateAttribute.getObservable().subscribe(new Action1<NotifyZoomStatusUpdate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.9
            @Override // rx.functions.Action1
            public void call(NotifyZoomStatusUpdate notifyZoomStatusUpdate) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyZoomStatusUpdate");
                NavigationApiOctaImpl.this.notificationListener.zoomStatusUpdate(notifyZoomStatusUpdate.getZoomStatusUpdate());
            }
        }));
        IAttribute<NotifyDayNightStatusUpdate> iAttributeDayNightStatusUpdateAttribute = this.notifications.dayNightStatusUpdateAttribute();
        iAttributeDayNightStatusUpdateAttribute.signIn();
        this.remoteAttributes.add(iAttributeDayNightStatusUpdateAttribute);
        this.remoteSubscriptions.add(iAttributeDayNightStatusUpdateAttribute.getObservable().subscribe(new Action1<NotifyDayNightStatusUpdate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.10
            @Override // rx.functions.Action1
            public void call(NotifyDayNightStatusUpdate notifyDayNightStatusUpdate) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyDayNightStatusUpdate");
                NavigationApiOctaImpl.this.notificationListener.dayNightStatusUpdate(notifyDayNightStatusUpdate.getDayNightStatusUpdate());
            }
        }));
        IAttribute<NotifyViewModeUpdate> iAttributeViewModeUpdateAttribute = this.notifications.viewModeUpdateAttribute();
        iAttributeViewModeUpdateAttribute.signIn();
        this.remoteAttributes.add(iAttributeViewModeUpdateAttribute);
        this.remoteSubscriptions.add(iAttributeViewModeUpdateAttribute.getObservable().subscribe(new Action1<NotifyViewModeUpdate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.11
            @Override // rx.functions.Action1
            public void call(NotifyViewModeUpdate notifyViewModeUpdate) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyViewModeUpdate");
                NavigationApiOctaImpl.this.notificationListener.viewModeUpdate(notifyViewModeUpdate.getViewModeUpdate());
            }
        }));
        IAttribute<NotifyMuteModeUpdate> iAttributeMuteModeUpdateAttribute = this.notifications.muteModeUpdateAttribute();
        iAttributeMuteModeUpdateAttribute.signIn();
        this.remoteAttributes.add(iAttributeMuteModeUpdateAttribute);
        this.remoteSubscriptions.add(iAttributeMuteModeUpdateAttribute.getObservable().subscribe(new Action1<NotifyMuteModeUpdate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.12
            @Override // rx.functions.Action1
            public void call(NotifyMuteModeUpdate notifyMuteModeUpdate) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyMuteModeUpdate");
                NavigationApiOctaImpl.this.notificationListener.muteModeUpdate(notifyMuteModeUpdate.getMuteModeUpdate());
            }
        }));
        IAttribute<NotifyFavoritesInfoUpdate> iAttributeFavoritesInfoUpdateAttribute = this.notifications.favoritesInfoUpdateAttribute();
        iAttributeFavoritesInfoUpdateAttribute.signIn();
        this.remoteAttributes.add(iAttributeFavoritesInfoUpdateAttribute);
        this.remoteSubscriptions.add(iAttributeFavoritesInfoUpdateAttribute.getObservable().subscribe(new Action1<NotifyFavoritesInfoUpdate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.13
            @Override // rx.functions.Action1
            public void call(NotifyFavoritesInfoUpdate notifyFavoritesInfoUpdate) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyFavoritesInfoUpdate");
                NavigationApiOctaImpl.this.notificationListener.favoritesInfoChanged(notifyFavoritesInfoUpdate.getFavoritesInfoUpdate());
            }
        }));
        IAttribute<NotifyRouteInfoUpdate> iAttributeRouteInfoUpdateAttribute = this.notifications.routeInfoUpdateAttribute();
        iAttributeRouteInfoUpdateAttribute.signIn();
        this.remoteAttributes.add(iAttributeRouteInfoUpdateAttribute);
        this.remoteSubscriptions.add(iAttributeRouteInfoUpdateAttribute.getObservable().subscribe(new Action1<NotifyRouteInfoUpdate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.14
            @Override // rx.functions.Action1
            public void call(NotifyRouteInfoUpdate notifyRouteInfoUpdate) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyRouteInfoUpdate");
                NavigationApiOctaImpl.this.notificationListener.routeInfoUpdate(notifyRouteInfoUpdate.getRouteInfoUpdate());
            }
        }));
        IAttribute<NotifyRouteSelectedUpdate> iAttributeRouteSelectedUpdateAttribute = this.notifications.routeSelectedUpdateAttribute();
        iAttributeRouteSelectedUpdateAttribute.signIn();
        this.remoteAttributes.add(iAttributeRouteSelectedUpdateAttribute);
        this.remoteSubscriptions.add(iAttributeRouteSelectedUpdateAttribute.getObservable().subscribe(new Action1<NotifyRouteSelectedUpdate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.15
            @Override // rx.functions.Action1
            public void call(NotifyRouteSelectedUpdate notifyRouteSelectedUpdate) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyRouteSelectedUpdate");
                NavigationApiOctaImpl.this.notificationListener.routeSelectedChaned(notifyRouteSelectedUpdate.getRouteSelectedUpdate());
            }
        }));
        IAttribute<NotifyHighwayInfoUpdate> iAttributeHighwayInfoUpdateAttribute = this.notifications.highwayInfoUpdateAttribute();
        iAttributeHighwayInfoUpdateAttribute.signIn();
        this.remoteAttributes.add(iAttributeHighwayInfoUpdateAttribute);
        this.remoteSubscriptions.add(iAttributeHighwayInfoUpdateAttribute.getObservable().subscribe(new Action1<NotifyHighwayInfoUpdate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.16
            @Override // rx.functions.Action1
            public void call(NotifyHighwayInfoUpdate notifyHighwayInfoUpdate) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyHighwayInfoUpdate");
                NavigationApiOctaImpl.this.notificationListener.highwayInfoUpdate(notifyHighwayInfoUpdate.getHighwayInfoUpdate());
            }
        }));
        IAttribute<NotifyVRMsgCommandUpdate> iAttributeVRMsgCommandUpdateAttribute = this.notifications.vRMsgCommandUpdateAttribute();
        iAttributeVRMsgCommandUpdateAttribute.signIn();
        this.remoteAttributes.add(iAttributeVRMsgCommandUpdateAttribute);
        this.remoteSubscriptions.add(iAttributeVRMsgCommandUpdateAttribute.getObservable().subscribe(new Action1<NotifyVRMsgCommandUpdate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.17
            @Override // rx.functions.Action1
            public void call(NotifyVRMsgCommandUpdate notifyVRMsgCommandUpdate) {
                if (notifyVRMsgCommandUpdate.getVRMsgCommandUpdate().getFunctionType() == FunctionType.GAS_STATION_SERVICE && notifyVRMsgCommandUpdate.getVRMsgCommandUpdate().getCommandState().intValue() == 1) {
                    Log.i(NavigationApiOctaImpl.TAG, "NotifyVRMsgCommandUpdate");
                    Intent intent = new Intent();
                    intent.setAction("com.neusoft.mc2.setel.service.SetelService");
                    intent.setPackage("com.neusoft.mc2.setel");
                    intent.putExtra("from", "navi");
                    NavigationApiOctaImpl.this.context.startForegroundService(intent);
                }
            }
        }));
        IAttribute<NotifySwitchVRCommand> iAttributeSwitchVRCommandAttribute = this.notifications.switchVRCommandAttribute();
        iAttributeSwitchVRCommandAttribute.signIn();
        this.remoteAttributes.add(iAttributeSwitchVRCommandAttribute);
        this.remoteSubscriptions.add(iAttributeSwitchVRCommandAttribute.getObservable().subscribe(new Action1<NotifySwitchVRCommand>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.18
            @Override // rx.functions.Action1
            public void call(NotifySwitchVRCommand notifySwitchVRCommand) {
                NavigationApiOctaImpl.this.notificationListener.requestVRCommand(notifySwitchVRCommand.getSwitchVRCommand().getFunctionType().getValue().intValue(), notifySwitchVRCommand.getSwitchVRCommand().getVRCommandOnOff().getValue().intValue());
            }
        }));
        IAttribute<NotifyRequestSETELServiceStatus> iAttributeRequestSETELServiceStatusAttribute = this.notifications.requestSETELServiceStatusAttribute();
        iAttributeRequestSETELServiceStatusAttribute.signIn();
        this.remoteAttributes.add(iAttributeRequestSETELServiceStatusAttribute);
        this.remoteSubscriptions.add(iAttributeRequestSETELServiceStatusAttribute.getObservable().subscribe(new Action1<NotifyRequestSETELServiceStatus>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.19
            @Override // rx.functions.Action1
            public void call(final NotifyRequestSETELServiceStatus notifyRequestSETELServiceStatus) {
                NavigationApiOctaImpl.this.notificationListener.reqSetelServiceStatus(new IBooleanCallback.Stub() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.19.1
                    @Override // com.neusoft.na.ocservice.IBooleanCallback
                    public void callback(boolean z) throws RemoteException {
                        Log.i(NavigationApiOctaImpl.TAG, "reqSetelServiceStatus:" + z);
                        NavigationApiOctaImpl.this.requests.fireSetSETELServiceStatus(new ReqSetSETELServiceStatus(new SETELServiceStatus(notifyRequestSETELServiceStatus.getRequestSETELServiceStatus().getReqId(), Boolean.valueOf(z))));
                    }
                });
            }
        }));
        IAttribute<NotifyServiceStatusUpdate> iAttributeServiceStatusUpdateAttribute = this.notifications.serviceStatusUpdateAttribute();
        iAttributeServiceStatusUpdateAttribute.signIn();
        this.remoteAttributes.add(iAttributeServiceStatusUpdateAttribute);
        this.remoteSubscriptions.add(iAttributeServiceStatusUpdateAttribute.getObservable().subscribe(new Action1<NotifyServiceStatusUpdate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.20
            @Override // rx.functions.Action1
            public void call(NotifyServiceStatusUpdate notifyServiceStatusUpdate) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyServiceStatusUpdate");
                NavigationApiOctaImpl.this.notificationListener.serviceStatusChanged(notifyServiceStatusUpdate.getServiceStatusUpdate());
            }
        }));
        IAttribute<NotifyArrivedDest> iAttributeArrivedDestAttribute = this.notifications.arrivedDestAttribute();
        iAttributeArrivedDestAttribute.signIn();
        this.remoteAttributes.add(iAttributeArrivedDestAttribute);
        this.remoteSubscriptions.add(iAttributeArrivedDestAttribute.getObservable().subscribe(new Action1<NotifyArrivedDest>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.21
            @Override // rx.functions.Action1
            public void call(NotifyArrivedDest notifyArrivedDest) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyArrivedDest");
                NavigationApiOctaImpl.this.notificationListener.arrivedDest();
            }
        }));
        IAttribute<NotifyTrafficConditionUpdate> iAttributeTrafficConditionUpdateAttribute = this.notifications.trafficConditionUpdateAttribute();
        iAttributeTrafficConditionUpdateAttribute.signIn();
        this.remoteAttributes.add(iAttributeTrafficConditionUpdateAttribute);
        this.remoteSubscriptions.add(iAttributeTrafficConditionUpdateAttribute.getObservable().subscribe(new Action1<NotifyTrafficConditionUpdate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.22
            @Override // rx.functions.Action1
            public void call(NotifyTrafficConditionUpdate notifyTrafficConditionUpdate) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyTrafficConditionUpdate");
                NavigationApiOctaImpl.this.notificationListener.trafficCondition(notifyTrafficConditionUpdate.getTrafficConditionUpdate().getTotalDistanceM().intValue(), notifyTrafficConditionUpdate.getTrafficConditionUpdate().getRemainDistanceM().intValue(), notifyTrafficConditionUpdate.getTrafficConditionUpdate().getTrafficInfo());
            }
        }));
        IAttribute<NotifyADAreaUpdate> iAttributeADAreaUpdateAttribute = this.notifications.aDAreaUpdateAttribute();
        iAttributeADAreaUpdateAttribute.signIn();
        this.remoteAttributes.add(iAttributeADAreaUpdateAttribute);
        this.remoteSubscriptions.add(iAttributeADAreaUpdateAttribute.getObservable().subscribe(new Action1<NotifyADAreaUpdate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.23
            @Override // rx.functions.Action1
            public void call(NotifyADAreaUpdate notifyADAreaUpdate) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyADAreaUpdate");
                NavigationApiOctaImpl.this.notificationListener.adareaChange(notifyADAreaUpdate.getADAreaUpdate().getCity(), notifyADAreaUpdate.getADAreaUpdate().getCityCode(), notifyADAreaUpdate.getADAreaUpdate().getProvince(), notifyADAreaUpdate.getADAreaUpdate().getCountry(), notifyADAreaUpdate.getADAreaUpdate().getCountryCode(), notifyADAreaUpdate.getADAreaUpdate().getAdCode(), notifyADAreaUpdate.getADAreaUpdate().getAdName());
            }
        }));
        IAttribute<NotifyLanesInfoUpdate> iAttributeLanesInfoUpdateAttribute = this.notifications.lanesInfoUpdateAttribute();
        iAttributeLanesInfoUpdateAttribute.signIn();
        this.remoteAttributes.add(iAttributeLanesInfoUpdateAttribute);
        this.remoteSubscriptions.add(iAttributeLanesInfoUpdateAttribute.getObservable().subscribe(new Action1<NotifyLanesInfoUpdate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.24
            @Override // rx.functions.Action1
            public void call(NotifyLanesInfoUpdate notifyLanesInfoUpdate) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyLanesInfoUpdate");
                NavigationApiOctaImpl.this.notificationListener.lanesInfo(notifyLanesInfoUpdate.getLanesInfoUpdate().getLanesEnable().booleanValue(), notifyLanesInfoUpdate.getLanesInfoUpdate().getLanesInfo());
            }
        }));
        IAttribute<NotifyGetLoginStatus> loginStatusAttribute = this.notifications.getLoginStatusAttribute();
        loginStatusAttribute.signIn();
        this.remoteAttributes.add(loginStatusAttribute);
        this.remoteSubscriptions.add(loginStatusAttribute.getObservable().subscribe(new Action1<NotifyGetLoginStatus>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.25
            @Override // rx.functions.Action1
            public void call(NotifyGetLoginStatus notifyGetLoginStatus) {
                NavigationApiOctaImpl.this.notificationListener.getLoggedInState(new ILoggedInStateCallback.Stub() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.25.1
                    @Override // com.neusoft.na.ocservice.ILoggedInStateCallback
                    public void callback(boolean z, String str) throws RemoteException {
                        Log.i(NavigationApiOctaImpl.TAG, "getLoggedInState : " + z + ",protonId");
                        NavigationApiOctaImpl.this.requests.fireHasLogin(new ReqHasLogin(Boolean.valueOf(z), str));
                    }
                });
            }
        }));
        IAttribute<NotifyCommutePush> iAttributeCommutePushAttribute = this.notifications.commutePushAttribute();
        iAttributeCommutePushAttribute.signIn();
        this.remoteAttributes.add(iAttributeCommutePushAttribute);
        this.remoteSubscriptions.add(iAttributeCommutePushAttribute.getObservable().subscribe(new Action1<NotifyCommutePush>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.26
            @Override // rx.functions.Action1
            public void call(NotifyCommutePush notifyCommutePush) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyCommutePush");
                NavigationApiOctaImpl.this.notificationListener.commutePush(notifyCommutePush.getCommutePush().getType().getValue().intValue(), notifyCommutePush.getCommutePush().getTotalDistanceM().intValue(), notifyCommutePush.getCommutePush().getEtaS().intValue(), notifyCommutePush.getCommutePush().getTrafficInfo());
            }
        }));
        IAttribute<NotifyAvailableRestoreGuidance> iAttributeAvailableRestoreGuidanceAttribute = this.notifications.availableRestoreGuidanceAttribute();
        iAttributeAvailableRestoreGuidanceAttribute.signIn();
        this.remoteAttributes.add(iAttributeAvailableRestoreGuidanceAttribute);
        this.remoteSubscriptions.add(iAttributeAvailableRestoreGuidanceAttribute.getObservable().subscribe(new Action1<NotifyAvailableRestoreGuidance>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.27
            @Override // rx.functions.Action1
            public void call(NotifyAvailableRestoreGuidance notifyAvailableRestoreGuidance) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyAvailableRestoreGuidance");
                NavigationApiOctaImpl.this.notificationListener.availableRestoreGuidance(notifyAvailableRestoreGuidance.getAvailableRestoreGuidance().getValue().intValue());
            }
        }));
        IAttribute<NotifyRequestAddFavorite> iAttributeRequestAddFavoriteAttribute = this.notifications.requestAddFavoriteAttribute();
        iAttributeRequestAddFavoriteAttribute.signIn();
        this.remoteAttributes.add(iAttributeRequestAddFavoriteAttribute);
        this.remoteSubscriptions.add(iAttributeRequestAddFavoriteAttribute.getObservable().subscribe(new Action1<NotifyRequestAddFavorite>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.28
            @Override // rx.functions.Action1
            public void call(NotifyRequestAddFavorite notifyRequestAddFavorite) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyRequestAddFavorite");
                int iIntValue = notifyRequestAddFavorite.getRequestAddFavorite().getReqId().intValue();
                FavoriteItem favoriteItem = notifyRequestAddFavorite.getRequestAddFavorite().getFavoriteItemList().get(0);
                Log.i(NavigationApiOctaImpl.TAG, "NotifyRequestAddFavorite FavoriteType: " + favoriteItem.getFavoriteType().getValue() + "Fixed Name: " + favoriteItem.getModifiedName());
                com.neusoft.na.ocservice.FavoriteItem favoriteItem2 = new com.neusoft.na.ocservice.FavoriteItem();
                favoriteItem2.setId(favoriteItem.getFavoriteId());
                favoriteItem2.setFavoriteType(favoriteItem.getFavoriteType().getValue().intValue());
                favoriteItem2.setName(favoriteItem.getPoiInfo().getName());
                favoriteItem2.setUid(favoriteItem.getPoiInfo().getUid());
                favoriteItem2.setAddress(favoriteItem.getPoiInfo().getAddress());
                GeoPos geoPos = new GeoPos();
                geoPos.setLongitude(favoriteItem.getPoiInfo().getLatLng().getLongitude().doubleValue());
                geoPos.setLatitude(favoriteItem.getPoiInfo().getLatLng().getLatitude().doubleValue());
                favoriteItem2.setGeoPos(geoPos);
                List<String> phoneNumbers = favoriteItem.getPhoneNumbers();
                if (phoneNumbers != null && phoneNumbers.size() > 0) {
                    String[] strArr = new String[phoneNumbers.size()];
                    for (int i = 0; i < phoneNumbers.size(); i++) {
                        strArr[i] = phoneNumbers.get(i);
                    }
                    favoriteItem2.setPoiPhoneNumbers(strArr);
                } else {
                    favoriteItem2.setPoiPhoneNumbers(new String[0]);
                }
                favoriteItem2.setPoiWebsite(favoriteItem.getURL());
                favoriteItem2.setPoiEmail(favoriteItem.getE_Mail());
                List<CategoryInfo> categoryInfos = favoriteItem.getCategoryInfos();
                if (categoryInfos != null && categoryInfos.size() > 0) {
                    int size = categoryInfos.size();
                    com.neusoft.na.ocservice.CategoryInfo[] categoryInfoArr = new com.neusoft.na.ocservice.CategoryInfo[size];
                    for (int i2 = 0; i2 < size; i2++) {
                        com.neusoft.na.ocservice.CategoryInfo categoryInfo = new com.neusoft.na.ocservice.CategoryInfo();
                        categoryInfo.setCategoryId(categoryInfos.get(i2).getCategoryId().intValue());
                        categoryInfo.setCategoryLevel(categoryInfos.get(i2).getCategoryLevel().intValue());
                        categoryInfo.setCategoryName(categoryInfos.get(i2).getCategoryName());
                        categoryInfoArr[i2] = categoryInfo;
                    }
                    favoriteItem2.setCategoryInfolist(categoryInfoArr);
                } else {
                    favoriteItem2.setCategoryInfolist(new com.neusoft.na.ocservice.CategoryInfo[0]);
                }
                List<NameInfo> nameInfos = favoriteItem.getNameInfos();
                if (nameInfos != null && nameInfos.size() > 0) {
                    int size2 = nameInfos.size();
                    com.neusoft.na.ocservice.NameInfo[] nameInfoArr = new com.neusoft.na.ocservice.NameInfo[size2];
                    for (int i3 = 0; i3 < size2; i3++) {
                        com.neusoft.na.ocservice.NameInfo nameInfo = new com.neusoft.na.ocservice.NameInfo();
                        nameInfo.setCode(nameInfos.get(i3).getLanguageCode());
                        nameInfo.setName(nameInfos.get(i3).getName());
                        nameInfoArr[i3] = nameInfo;
                    }
                    favoriteItem2.setNameInfoList(nameInfoArr);
                } else {
                    favoriteItem2.setNameInfoList(new com.neusoft.na.ocservice.NameInfo[0]);
                }
                favoriteItem2.setPoiDataSource(favoriteItem.getPoiDataSource());
                List<GeoPosition> accessibleEntries = favoriteItem.getAccessibleEntries();
                if (accessibleEntries != null && accessibleEntries.size() > 0) {
                    int size3 = accessibleEntries.size();
                    GeoPos[] geoPosArr = new GeoPos[size3];
                    for (int i4 = 0; i4 < size3; i4++) {
                        GeoPos geoPos2 = new GeoPos();
                        geoPos2.setLatitude(accessibleEntries.get(i4).getLatitude().doubleValue());
                        geoPos2.setLongitude(accessibleEntries.get(i4).getLongitude().doubleValue());
                        geoPosArr[i4] = geoPos2;
                    }
                    favoriteItem2.setPoiAccessPositions(geoPosArr);
                } else {
                    favoriteItem2.setPoiAccessPositions(new GeoPos[0]);
                }
                List<Integer> locationId = favoriteItem.getLocationId();
                if (locationId != null && locationId.size() > 0) {
                    int[] iArr = new int[locationId.size()];
                    for (int i5 = 0; i5 < locationId.size(); i5++) {
                        iArr[i5] = locationId.get(i5).intValue();
                    }
                    favoriteItem2.setPoiLocationid(iArr);
                } else {
                    favoriteItem2.setPoiLocationid(new int[0]);
                }
                favoriteItem2.setFixName(favoriteItem.getModifiedName());
                favoriteItem2.setMapType(favoriteItem.getDataProvider().intValue());
                NavigationApiOctaImpl.this.notificationListener.addItemToFavorites(iIntValue, favoriteItem2, new IAddItemToFavoritesCallBack.Stub() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.28.1
                    @Override // com.neusoft.na.ocservice.IAddItemToFavoritesCallBack
                    public void callback(int i6, int i7, String str) throws RemoteException {
                        Log.i(NavigationApiOctaImpl.TAG, "notifyRequestAddFavorite: Token: " + i6 + ":code: " + i7 + "id: " + str);
                        NavigationApiOctaImpl.this.requests.fireAddFavorite(new ReqAddFavorite(Integer.valueOf(i6), Integer.valueOf(i7), str));
                    }
                });
            }
        }));
        IAttribute<NotifyRequestUpdateFavorite> iAttributeRequestUpdateFavoriteAttribute = this.notifications.requestUpdateFavoriteAttribute();
        iAttributeRequestUpdateFavoriteAttribute.signIn();
        this.remoteAttributes.add(iAttributeRequestUpdateFavoriteAttribute);
        this.remoteSubscriptions.add(iAttributeRequestUpdateFavoriteAttribute.getObservable().subscribe(new Action1<NotifyRequestUpdateFavorite>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.29
            @Override // rx.functions.Action1
            public void call(NotifyRequestUpdateFavorite notifyRequestUpdateFavorite) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyRequestUpdateFavorite");
                int iIntValue = notifyRequestUpdateFavorite.getRequestUpdateFavorite().getReqId().intValue();
                FavoriteItem favoriteItem = notifyRequestUpdateFavorite.getRequestUpdateFavorite().getFavoriteItem();
                com.neusoft.na.ocservice.FavoriteItem favoriteItem2 = new com.neusoft.na.ocservice.FavoriteItem();
                favoriteItem2.setId(favoriteItem.getFavoriteId());
                favoriteItem2.setFavoriteType(favoriteItem.getFavoriteType().getValue().intValue());
                favoriteItem2.setName(favoriteItem.getPoiInfo().getName());
                favoriteItem2.setUid(favoriteItem.getPoiInfo().getUid());
                favoriteItem2.setAddress(favoriteItem.getPoiInfo().getAddress());
                favoriteItem2.setDataType(favoriteItem.getPoiInfo().getDataType().getValue().intValue());
                GeoPos geoPos = new GeoPos();
                geoPos.setLongitude(favoriteItem.getPoiInfo().getLatLng().getLongitude().doubleValue());
                geoPos.setLatitude(favoriteItem.getPoiInfo().getLatLng().getLatitude().doubleValue());
                favoriteItem2.setGeoPos(geoPos);
                List<String> phoneNumbers = favoriteItem.getPhoneNumbers();
                if (phoneNumbers != null && phoneNumbers.size() > 0) {
                    String[] strArr = new String[phoneNumbers.size()];
                    for (int i = 0; i < phoneNumbers.size(); i++) {
                        strArr[i] = phoneNumbers.get(i);
                    }
                    favoriteItem2.setPoiPhoneNumbers(strArr);
                } else {
                    favoriteItem2.setPoiPhoneNumbers(new String[0]);
                }
                favoriteItem2.setPoiWebsite(favoriteItem.getURL());
                favoriteItem2.setPoiEmail(favoriteItem.getE_Mail());
                List<CategoryInfo> categoryInfos = favoriteItem.getCategoryInfos();
                if (categoryInfos != null && categoryInfos.size() > 0) {
                    int size = categoryInfos.size();
                    com.neusoft.na.ocservice.CategoryInfo[] categoryInfoArr = new com.neusoft.na.ocservice.CategoryInfo[size];
                    for (int i2 = 0; i2 < size; i2++) {
                        com.neusoft.na.ocservice.CategoryInfo categoryInfo = new com.neusoft.na.ocservice.CategoryInfo();
                        categoryInfo.setCategoryId(categoryInfos.get(i2).getCategoryId().intValue());
                        categoryInfo.setCategoryLevel(categoryInfos.get(i2).getCategoryLevel().intValue());
                        categoryInfo.setCategoryName(categoryInfos.get(i2).getCategoryName());
                        categoryInfoArr[i2] = categoryInfo;
                    }
                    favoriteItem2.setCategoryInfolist(categoryInfoArr);
                } else {
                    favoriteItem2.setCategoryInfolist(new com.neusoft.na.ocservice.CategoryInfo[0]);
                }
                List<NameInfo> nameInfos = favoriteItem.getNameInfos();
                if (nameInfos != null && nameInfos.size() > 0) {
                    int size2 = nameInfos.size();
                    com.neusoft.na.ocservice.NameInfo[] nameInfoArr = new com.neusoft.na.ocservice.NameInfo[size2];
                    for (int i3 = 0; i3 < size2; i3++) {
                        com.neusoft.na.ocservice.NameInfo nameInfo = new com.neusoft.na.ocservice.NameInfo();
                        nameInfo.setCode(nameInfos.get(i3).getLanguageCode());
                        nameInfo.setName(nameInfos.get(i3).getName());
                        nameInfoArr[i3] = nameInfo;
                    }
                    favoriteItem2.setNameInfoList(nameInfoArr);
                } else {
                    favoriteItem2.setNameInfoList(new com.neusoft.na.ocservice.NameInfo[0]);
                }
                favoriteItem2.setPoiDataSource(favoriteItem.getPoiDataSource());
                List<GeoPosition> accessibleEntries = favoriteItem.getAccessibleEntries();
                if (accessibleEntries != null && accessibleEntries.size() > 0) {
                    int size3 = accessibleEntries.size();
                    GeoPos[] geoPosArr = new GeoPos[size3];
                    for (int i4 = 0; i4 < size3; i4++) {
                        GeoPos geoPos2 = new GeoPos();
                        geoPos2.setLatitude(accessibleEntries.get(i4).getLatitude().doubleValue());
                        geoPos2.setLongitude(accessibleEntries.get(i4).getLongitude().doubleValue());
                        geoPosArr[i4] = geoPos2;
                    }
                    favoriteItem2.setPoiAccessPositions(geoPosArr);
                } else {
                    favoriteItem2.setPoiAccessPositions(new GeoPos[0]);
                }
                List<Integer> locationId = favoriteItem.getLocationId();
                if (locationId != null && locationId.size() > 0) {
                    int[] iArr = new int[locationId.size()];
                    for (int i5 = 0; i5 < locationId.size(); i5++) {
                        iArr[i5] = locationId.get(i5).intValue();
                    }
                    favoriteItem2.setPoiLocationid(iArr);
                } else {
                    favoriteItem2.setPoiLocationid(new int[0]);
                }
                favoriteItem2.setFixName(favoriteItem.getModifiedName());
                favoriteItem2.setMapType(favoriteItem.getDataProvider().intValue());
                NavigationApiOctaImpl.this.notificationListener.updateFavoriteItem(iIntValue, favoriteItem2, new IFavoritesCallBack.Stub() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.29.1
                    @Override // com.neusoft.na.ocservice.IFavoritesCallBack
                    public void callback(int i6, int i7) throws RemoteException {
                        NavigationApiOctaImpl.this.requests.fireUpdateFavorite(new ReqUpdateFavorite(Integer.valueOf(i6), Integer.valueOf(i7)));
                    }
                });
            }
        }));
        IAttribute<NotifyRequestRemoveFavorite> iAttributeRequestRemoveFavoriteAttribute = this.notifications.requestRemoveFavoriteAttribute();
        iAttributeRequestRemoveFavoriteAttribute.signIn();
        this.remoteAttributes.add(iAttributeRequestRemoveFavoriteAttribute);
        this.remoteSubscriptions.add(iAttributeRequestRemoveFavoriteAttribute.getObservable().subscribe(new Action1<NotifyRequestRemoveFavorite>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.30
            @Override // rx.functions.Action1
            public void call(NotifyRequestRemoveFavorite notifyRequestRemoveFavorite) {
                Log.i(NavigationApiOctaImpl.TAG, "NotifyRequestRemoveFavorite");
                RemoveFavoriteItemCommand requestRemoveFavorite = notifyRequestRemoveFavorite.getRequestRemoveFavorite();
                boolean zBooleanValue = requestRemoveFavorite.getRemoveALL().booleanValue();
                NavigationApiOctaImpl.this.notificationListener.removeFavoriteItems(requestRemoveFavorite.getReqId().intValue(), requestRemoveFavorite.getFavoriteId(), zBooleanValue ? 1 : 0, new IFavoritesCallBack.Stub() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.30.1
                    @Override // com.neusoft.na.ocservice.IFavoritesCallBack
                    public void callback(int i, int i2) throws RemoteException {
                        NavigationApiOctaImpl.this.requests.fireRemoveFavorite(new ReqRemoveFavorite(Integer.valueOf(i), Integer.valueOf(i2)));
                    }
                });
            }
        }));
        IAttribute<NotifyGetFavoriteList> favoriteListAttribute = this.notifications.getFavoriteListAttribute();
        favoriteListAttribute.signIn();
        this.remoteAttributes.add(favoriteListAttribute);
        this.remoteSubscriptions.add(favoriteListAttribute.getObservable().subscribe(new Action1<NotifyGetFavoriteList>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.31
            @Override // rx.functions.Action1
            public void call(NotifyGetFavoriteList notifyGetFavoriteList) {
                NavigationApiOctaImpl.this.notificationListener.getFavoriteList(notifyGetFavoriteList.getGetFavoriteList().getReqId().intValue(), new IGetFavoriteListCallBack.Stub() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.31.1
                    @Override // com.neusoft.na.ocservice.IGetFavoriteListCallBack
                    public void callback(int i, int i2, List<com.neusoft.na.ocservice.FavoriteItem> list) throws RemoteException {
                        Log.i(NavigationApiOctaImpl.TAG, "NotifyGetFavoriteList Toke : " + i + "code : " + i2);
                        ArrayList arrayList = new ArrayList();
                        if (list != null && list.size() > 0) {
                            Log.i(NavigationApiOctaImpl.TAG, "NotifyGetFavoriteList favoriteLis size : " + list.size());
                            for (int i3 = 0; i3 < list.size(); i3++) {
                                FavoriteItem favoriteItem = new FavoriteItem();
                                com.neusoft.na.ocservice.FavoriteItem favoriteItem2 = list.get(i3);
                                favoriteItem.setFavoriteId(favoriteItem2.getId());
                                Log.i(NavigationApiOctaImpl.TAG, "NotifyGetFavoriteList favoriteLis i : " + i3 + "favorite type: " + favoriteItem2.getFavoriteType() + "Fixed Name: " + favoriteItem2.getFixName());
                                PoiInfo poiInfo = new PoiInfo();
                                poiInfo.setName(favoriteItem2.getName());
                                poiInfo.setUid(favoriteItem2.getUid());
                                poiInfo.setAddress(favoriteItem2.getAddress());
                                LatLng latLng = new LatLng();
                                latLng.setLongitude(Double.valueOf(favoriteItem2.getGeoPos().getLongitude()));
                                latLng.setLatitude(Double.valueOf(favoriteItem2.getGeoPos().getLatitude()));
                                poiInfo.setLatLng(latLng);
                                poiInfo.setDataType(DataType.getByValue(Integer.valueOf(favoriteItem2.getDataType())));
                                favoriteItem.setPoiInfo(poiInfo);
                                favoriteItem.setPhoneNumbers(favoriteItem2.getPoiPhoneNumbers());
                                favoriteItem.setURL(favoriteItem2.getPoiWebsite());
                                favoriteItem.setE_Mail(favoriteItem2.getPoiEmail());
                                com.neusoft.na.ocservice.CategoryInfo[] categoryInfolist = favoriteItem2.getCategoryInfolist();
                                ArrayList arrayList2 = new ArrayList();
                                if (categoryInfolist != null && categoryInfolist.length > 0) {
                                    for (int i4 = 0; i4 < categoryInfolist.length; i4++) {
                                        CategoryInfo categoryInfo = new CategoryInfo();
                                        categoryInfo.setCategoryId(Integer.valueOf(categoryInfolist[i3].getCategoryId()));
                                        categoryInfo.setCategoryLevel(Integer.valueOf(categoryInfolist[i3].getCategoryLevel()));
                                        categoryInfo.setCategoryName(categoryInfolist[i3].getCategoryName());
                                        arrayList2.add(categoryInfo);
                                    }
                                }
                                favoriteItem.setCategoryInfos(arrayList2);
                                com.neusoft.na.ocservice.NameInfo[] nameInfoList = favoriteItem2.getNameInfoList();
                                ArrayList arrayList3 = new ArrayList();
                                if (nameInfoList != null && nameInfoList.length > 0) {
                                    for (int i5 = 0; i5 < categoryInfolist.length; i5++) {
                                        NameInfo nameInfo = new NameInfo();
                                        nameInfo.setLanguageCode(nameInfoList[i3].getCode());
                                        nameInfo.setName(nameInfoList[i3].getName());
                                        arrayList3.add(nameInfo);
                                    }
                                }
                                favoriteItem.setNameInfos(arrayList3);
                                favoriteItem.setPoiDataSource(favoriteItem2.getPoiDataSource());
                                GeoPos[] poiAccessPositions = favoriteItem2.getPoiAccessPositions();
                                ArrayList arrayList4 = new ArrayList();
                                if (poiAccessPositions != null && poiAccessPositions.length > 0) {
                                    for (int i6 = 0; i6 < poiAccessPositions.length; i6++) {
                                        GeoPosition geoPosition = new GeoPosition();
                                        geoPosition.setLatitude(Double.valueOf(poiAccessPositions[i6].getLatitude()));
                                        geoPosition.setLongitude(Double.valueOf(poiAccessPositions[i6].getLongitude()));
                                        arrayList4.add(geoPosition);
                                    }
                                }
                                favoriteItem.setAccessibleEntries(arrayList4);
                                ArrayList arrayList5 = new ArrayList();
                                int[] poiLocationid = favoriteItem2.getPoiLocationid();
                                if (poiLocationid != null && poiLocationid.length > 0) {
                                    for (int i7 : poiLocationid) {
                                        arrayList5.add(Integer.valueOf(i7));
                                    }
                                }
                                favoriteItem.setLocationId(arrayList5);
                                favoriteItem.setModifiedName(favoriteItem2.getFixName());
                                favoriteItem.setDataProvider(Integer.valueOf(favoriteItem2.getMapType()));
                                favoriteItem.setFavoriteType(FavoriteType.getByValue(Integer.valueOf(favoriteItem2.getFavoriteType())));
                                arrayList.add(favoriteItem);
                            }
                        }
                        NavigationApiOctaImpl.this.requests.fireSetFavoriteList(new ReqSetFavoriteList(Integer.valueOf(i), Integer.valueOf(i2), arrayList));
                    }
                });
            }
        }));
        IAttribute<NotifyRequestSETELDetail> iAttributeRequestSETELDetailAttribute = this.notifications.requestSETELDetailAttribute();
        iAttributeRequestSETELDetailAttribute.signIn();
        this.remoteAttributes.add(iAttributeRequestSETELDetailAttribute);
        this.remoteSubscriptions.add(iAttributeRequestSETELDetailAttribute.getObservable().subscribe(new Action1<NotifyRequestSETELDetail>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.32
            @Override // rx.functions.Action1
            public void call(NotifyRequestSETELDetail notifyRequestSETELDetail) {
                Log.i(NavigationApiOctaImpl.TAG, "Navigation requestSETELDetailAttribute call in.");
                NavigationApiOctaImpl.this.notificationListener.requestSetelDetail(notifyRequestSETELDetail.getRequestSETELDetail().getReqId().intValue(), notifyRequestSETELDetail.getRequestSETELDetail().getGasStationId(), new IRequestSetelDetailCallBack.Stub() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.32.1
                    @Override // com.neusoft.na.ocservice.IRequestSetelDetailCallBack
                    public void callback(SetelDetailData setelDetailData) throws RemoteException {
                        Log.i(NavigationApiOctaImpl.TAG, "Navigation requestSETELDetailAttribute call back.");
                        ProcessedSETELDetailDataInfo processedSETELDetailDataInfo = new ProcessedSETELDetailDataInfo();
                        processedSETELDetailDataInfo.setCode(Integer.valueOf(setelDetailData.getCode()));
                        processedSETELDetailDataInfo.setMessage(setelDetailData.getMsg());
                        processedSETELDetailDataInfo.setReqId(Integer.valueOf(setelDetailData.getToken()));
                        processedSETELDetailDataInfo.setAvailableGunPumps(Integer.valueOf(setelDetailData.getAvailablePumps()));
                        NavigationApiOctaImpl.this.requests.fireSetSETELDetailData(new ReqSetSETELDetailData.Arguments(processedSETELDetailDataInfo));
                    }
                });
            }
        }));
        IAttribute<NotifyRequestSETELData> iAttributeRequestSETELDataAttribute = this.notifications.requestSETELDataAttribute();
        iAttributeRequestSETELDataAttribute.signIn();
        this.remoteAttributes.add(iAttributeRequestSETELDataAttribute);
        this.remoteSubscriptions.add(iAttributeRequestSETELDataAttribute.getObservable().subscribe(new Action1<NotifyRequestSETELData>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.33
            @Override // rx.functions.Action1
            public void call(NotifyRequestSETELData notifyRequestSETELData) {
                SetelSearchData[] setelSearchDataArr;
                Log.i(NavigationApiOctaImpl.TAG, "NotifyRequestSETELData");
                SetelData setelData = new SetelData();
                setelData.setToken(notifyRequestSETELData.getRequestSETELData().getReqId().intValue());
                setelData.setPoslat(notifyRequestSETELData.getRequestSETELData().getGeoPosition().getLatitude().doubleValue());
                setelData.setPoslon(notifyRequestSETELData.getRequestSETELData().getGeoPosition().getLongitude().doubleValue());
                setelData.setRange(notifyRequestSETELData.getRequestSETELData().getRadius().intValue());
                List<SETELPoiInfo> sETELPoiInfoList = notifyRequestSETELData.getRequestSETELData().getSETELPoiInfoList();
                if (sETELPoiInfoList.size() > 0) {
                    setelSearchDataArr = new SetelSearchData[sETELPoiInfoList.size()];
                    for (int i = 0; i < sETELPoiInfoList.size(); i++) {
                        SETELPoiInfo sETELPoiInfo = sETELPoiInfoList.get(i);
                        SetelSearchData setelSearchData = new SetelSearchData();
                        setelSearchData.setName(sETELPoiInfo.getName());
                        setelSearchData.setAddress(sETELPoiInfo.getAddress());
                        setelSearchData.setLat(sETELPoiInfo.getLatLng().getLatitude().doubleValue());
                        setelSearchData.setLon(sETELPoiInfo.getLatLng().getLongitude().doubleValue());
                        setelSearchData.setDistance(sETELPoiInfo.getDistance().intValue());
                        setelSearchData.setNavigationMap(sETELPoiInfo.getIsNavigationDB().booleanValue());
                        setelSearchData.setNavigationMap_index(sETELPoiInfo.getIndexOfNavigationDB().intValue());
                        setelSearchDataArr[i] = setelSearchData;
                    }
                } else {
                    setelSearchDataArr = new SetelSearchData[0];
                }
                setelData.setSearchResult(setelSearchDataArr);
                NavigationApiOctaImpl.this.notificationListener.requestSetelData(setelData, new ISetelDataCallBack.Stub() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.33.1
                    @Override // com.neusoft.na.ocservice.ISetelDataCallBack
                    public void callback(SetelDataCallBackInfo setelDataCallBackInfo) throws RemoteException {
                        new ReqSetSETELData();
                        ProcessedSETELDataInfo processedSETELDataInfo = new ProcessedSETELDataInfo();
                        processedSETELDataInfo.setReqId(Integer.valueOf(setelDataCallBackInfo.getToken()));
                        processedSETELDataInfo.setCode(Integer.valueOf(setelDataCallBackInfo.getCode()));
                        processedSETELDataInfo.setMessage(setelDataCallBackInfo.getMsg());
                        SetelResultData[] resultList = setelDataCallBackInfo.getResultList();
                        ArrayList arrayList = new ArrayList();
                        Log.i(NavigationApiOctaImpl.TAG, "ReqSetSETELData: requestId: " + setelDataCallBackInfo.getToken() + ":code: " + setelDataCallBackInfo.getCode() + ":ResultList Size: " + resultList.length);
                        if (resultList.length > 0) {
                            for (SetelResultData setelResultData : resultList) {
                                SETELDetailInfo sETELDetailInfo = new SETELDetailInfo();
                                sETELDetailInfo.setIsActive(Boolean.valueOf(setelResultData.isActive()));
                                sETELDetailInfo.setAddress(setelResultData.getAddress());
                                sETELDetailInfo.setAtStation(Boolean.valueOf(setelResultData.isAtStation()));
                                sETELDetailInfo.setDistance(Integer.valueOf((int) setelResultData.getDistance()));
                                sETELDetailInfo.setGeoFencePosition(new GeoPosition(Double.valueOf(setelResultData.getGeofenceLatitude()), Double.valueOf(setelResultData.getGeofenceLongitude())));
                                sETELDetailInfo.setRadiusOfGeoFence(Integer.valueOf(setelResultData.getGeofenceRadius()));
                                sETELDetailInfo.setPoiId(setelResultData.getId());
                                sETELDetailInfo.setGeoPosition(new GeoPosition(Double.valueOf(setelResultData.getLatitude()), Double.valueOf(setelResultData.getLongitude())));
                                sETELDetailInfo.setName(setelResultData.getName());
                                sETELDetailInfo.setIndexOfNavigationDB(Integer.valueOf(setelResultData.getNavigationMapIndex()));
                                OpeningHour[] operatingHours = setelResultData.getOperatingHours();
                                ArrayList arrayList2 = new ArrayList();
                                if (operatingHours != null && operatingHours.length > 0) {
                                    for (int i2 = 0; i2 < operatingHours.length; i2++) {
                                        OpeningHours openingHours = new OpeningHours();
                                        openingHours.setDay(Integer.valueOf(operatingHours[i2].getDay()));
                                        TimeSlot[] timeSlots = operatingHours[i2].getTimeSlots();
                                        ArrayList arrayList3 = new ArrayList();
                                        if (timeSlots.length > 0) {
                                            for (int i3 = 0; i3 < timeSlots.length; i3++) {
                                                TimePeriod timePeriod = new TimePeriod();
                                                timePeriod.setOpeningTime(Integer.valueOf(timeSlots[i3].getFrom()));
                                                timePeriod.setClosingTime(Integer.valueOf(timeSlots[i3].getTo()));
                                                arrayList3.add(timePeriod);
                                            }
                                            openingHours.setTimePeriodList(arrayList3);
                                        }
                                        arrayList2.add(openingHours);
                                    }
                                }
                                sETELDetailInfo.setOpeningHoursList(arrayList2);
                                sETELDetailInfo.setStatus(setelResultData.getStatus());
                                sETELDetailInfo.setAvailableNumOfOilGun(Integer.valueOf(setelResultData.getAvailablePumps()));
                                arrayList.add(sETELDetailInfo);
                            }
                        }
                        processedSETELDataInfo.setSETELDetailInfoList(arrayList);
                        NavigationApiOctaImpl.this.requests.fireSetSETELData(new ReqSetSETELData(processedSETELDataInfo));
                    }
                });
            }
        }));
        IAttribute<NotifyQueryCurrentWeatherInfo> iAttributeQueryCurrentWeatherInfoAttribute = this.sysNotifications.queryCurrentWeatherInfoAttribute();
        iAttributeQueryCurrentWeatherInfoAttribute.signIn();
        this.remoteAttributes.add(iAttributeQueryCurrentWeatherInfoAttribute);
        this.remoteSubscriptions.add(iAttributeQueryCurrentWeatherInfoAttribute.getObservable().subscribe(new Action1<NotifyQueryCurrentWeatherInfo>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.34
            @Override // rx.functions.Action1
            public void call(NotifyQueryCurrentWeatherInfo notifyQueryCurrentWeatherInfo) {
                NavigationApiOctaImpl.this.notificationListener.requestObserveWeather(notifyQueryCurrentWeatherInfo.getQueryCurrentWeatherInfo().getReqId().intValue(), NavigationApiOctaImpl.this.octaGeoPos2AidlGeoPos(notifyQueryCurrentWeatherInfo.getQueryCurrentWeatherInfo().getGeoPosition()), new IObserveWeatherCallBack.Stub() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.34.1
                    @Override // com.neusoft.na.ocservice.IObserveWeatherCallBack
                    public void callBack(int i, int i2, ObserveWeatherInfo observeWeatherInfo) throws RemoteException {
                        Log.i(NavigationApiOctaImpl.TAG, "notifyQueryCurrentWeatherInfo:requestId:" + i + ":status:");
                        if (i2 == 0) {
                            BigInteger bigInteger = BigInteger.ZERO;
                            Integer.valueOf(0);
                            if (observeWeatherInfo.getServertime() != null) {
                                bigInteger = new BigInteger(observeWeatherInfo.getServertime().toString());
                            }
                            BigInteger bigInteger2 = bigInteger;
                            Integer num = new Integer(observeWeatherInfo.getVisibility());
                            String wind_direction_angle = observeWeatherInfo.getWind_direction_angle();
                            String pressure = observeWeatherInfo.getPressure();
                            String sendible_temperature = observeWeatherInfo.getSendible_temperature();
                            String weather_image = observeWeatherInfo.getWeather_image();
                            String wind_speed = observeWeatherInfo.getWind_speed();
                            String weather_code = observeWeatherInfo.getWeather_code();
                            String weather_phenomena = observeWeatherInfo.getWeather_phenomena();
                            String c_temperature = observeWeatherInfo.getC_temperature();
                            String wind_direction = observeWeatherInfo.getWind_direction();
                            String wind_force = observeWeatherInfo.getWind_force();
                            String rh = observeWeatherInfo.getRh();
                            String observe_publish_time = observeWeatherInfo.getObserve_publish_time();
                            String publish_time = observeWeatherInfo.getPublish_time();
                            Log.i(NavigationApiOctaImpl.TAG, "notifyQueryCurrentWeatherInfo:msg :wind_Direction_Angle" + wind_direction_angle + "airPressure" + pressure + "sensible_Temperature" + sendible_temperature + "weather_Image" + weather_image + "wind_Speed" + wind_speed + "weather_Code" + weather_code + "weather_Phenomena" + weather_phenomena + "temperature" + c_temperature + "wind_Direction" + wind_direction + "wind_Force" + wind_force + "relative_Humidity" + rh + "observe_Publish_Time" + observe_publish_time + "publish_Time" + publish_time);
                            NavigationApiOctaImpl.this.sysRequests.fireUpdateCurrentWeatherInfo(new ReqUpdateCurrentWeatherInfo(new CurrentWeatherInfoState(Integer.valueOf(i), Integer.valueOf(i2), new CurrentWeatherForecastInfo(bigInteger2, num, wind_direction_angle, pressure, sendible_temperature, weather_image, wind_speed, weather_code, weather_phenomena, c_temperature, wind_direction, wind_force, rh, observe_publish_time, publish_time))));
                            return;
                        }
                        BigInteger bigInteger3 = BigInteger.ZERO;
                        Log.i(NavigationApiOctaImpl.TAG, "notifyQueryCurrentWeatherInfo:msg :wind_Direction_AngleairPressuresensible_Temperatureweather_Imagewind_Speedweather_Codeweather_Phenomenatemperaturewind_Directionwind_Forcerelative_Humidityobserve_Publish_Timepublish_Time");
                        NavigationApiOctaImpl.this.sysRequests.fireUpdateCurrentWeatherInfo(new ReqUpdateCurrentWeatherInfo(new CurrentWeatherInfoState(Integer.valueOf(i), Integer.valueOf(i2), new CurrentWeatherForecastInfo(bigInteger3, 0, "", "", "", "", "", "", "", "", "", "", "", "", ""))));
                    }
                });
            }
        }));
        IAttribute<NotifyQueryFutureWeatherInfo> iAttributeQueryFutureWeatherInfoAttribute = this.sysNotifications.queryFutureWeatherInfoAttribute();
        iAttributeQueryFutureWeatherInfoAttribute.signIn();
        this.remoteAttributes.add(iAttributeQueryFutureWeatherInfoAttribute);
        this.remoteSubscriptions.add(iAttributeQueryFutureWeatherInfoAttribute.getObservable().subscribe(new Action1<NotifyQueryFutureWeatherInfo>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.35
            @Override // rx.functions.Action1
            public void call(NotifyQueryFutureWeatherInfo notifyQueryFutureWeatherInfo) {
                NavigationApiOctaImpl.this.notificationListener.requestForecastWeather(notifyQueryFutureWeatherInfo.getQueryFutureWeatherInfo().getReqId().intValue(), NavigationApiOctaImpl.this.octaGeoPos2AidlGeoPos(notifyQueryFutureWeatherInfo.getQueryFutureWeatherInfo().getGeoPosition()), new IForecastWeatherCallBack.Stub() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.35.1
                    @Override // com.neusoft.na.ocservice.IForecastWeatherCallBack
                    public void callBack(int i, int i2, ForecastWeatherInfo forecastWeatherInfo) throws RemoteException {
                        Log.i(NavigationApiOctaImpl.TAG, "NotifyQueryFutureWeatherInfo:requestId:" + i + ":status:" + i2);
                        if (i2 == 0) {
                            ArrayList arrayList = new ArrayList();
                            if (forecastWeatherInfo == null) {
                                Log.i(NavigationApiOctaImpl.TAG, "info is null");
                                return;
                            }
                            if (forecastWeatherInfo.getForecasts() != null && forecastWeatherInfo.getForecasts().size() > 0) {
                                int i3 = 0;
                                while (i3 < forecastWeatherInfo.getForecasts().size()) {
                                    Forecast forecast = forecastWeatherInfo.getForecasts().get(i3);
                                    String forecast_date = forecast.getForecast_date();
                                    String day_wind_direction = forecast.getDay_wind_direction();
                                    String day_wind_force = forecast.getDay_wind_force();
                                    String day_weather_phenomena = forecast.getDay_weather_phenomena();
                                    String day_c_temperature = forecast.getDay_c_temperature();
                                    String night_weather_phenomena = forecast.getNight_weather_phenomena();
                                    String night_c_temperature = forecast.getNight_c_temperature();
                                    String night_wind_direction = forecast.getNight_wind_direction();
                                    String night_wind_force = forecast.getNight_wind_force();
                                    String day_weather_code = forecast.getDay_weather_code();
                                    String day_weather_image = forecast.getDay_weather_image();
                                    String night_weather_code = forecast.getNight_weather_code();
                                    String night_weather_image = forecast.getNight_weather_image();
                                    String sunrd = forecast.getSunrd();
                                    ArrayList arrayList2 = arrayList;
                                    Log.i(NavigationApiOctaImpl.TAG, "NotifyQueryFutureWeatherInfo   forecast_Date :" + forecast_date + "day_Wind_Direction :" + day_wind_direction + "day_Wind_Force :" + day_wind_force + "day_Weather_Phenomena :" + day_weather_phenomena + "day_Temperature :" + day_c_temperature + "night_Weather_Phenomen :" + night_weather_phenomena + "night_Temperature :" + night_c_temperature + "night_Wind_Direction :" + night_wind_direction + "night_Wind_Force :" + night_wind_force + "day_Weather_Code :" + day_weather_code + "day_Weather_Image :" + day_weather_image + "night_Weather_Code :" + night_weather_code + "night_Weather_Image :" + night_weather_image + "sunrise_Sundown_Time :" + sunrd);
                                    arrayList2.add(new FutureWeatherForecastInfo(forecast_date, day_wind_direction, day_wind_force, day_weather_phenomena, day_c_temperature, night_weather_phenomena, night_c_temperature, night_wind_direction, night_wind_force, day_weather_code, day_weather_image, night_weather_code, night_weather_image, sunrd));
                                    i3++;
                                    arrayList = arrayList2;
                                }
                            }
                            NavigationApiOctaImpl.this.sysRequests.fireUpdateFutureWeatherInfo(new ReqUpdateFutureWeatherInfo(new FutureWeatherInfoState(Integer.valueOf(i), Integer.valueOf(i2), forecastWeatherInfo.getPublish_time(), arrayList)));
                            return;
                        }
                        NavigationApiOctaImpl.this.sysRequests.fireUpdateFutureWeatherInfo(new ReqUpdateFutureWeatherInfo(new FutureWeatherInfoState(Integer.valueOf(i), Integer.valueOf(i2), "", new ArrayList())));
                    }
                });
            }
        }));
        if (this.hasUnsentMSG) {
            this.hasUnsentMSG = false;
            Log.i(TAG, "hasUnsentMSG::unlock");
            this.unsentMsg = UnsentMethod.No;
        }
    }

    @Override // com.neusoft.na.common.ClientEndpointMangerCallback
    public void onServerDisconnect() {
        Log.i(TAG, "onMotsDisconnect: ");
        this.isServerConnect = false;
        if (this.motsWsContext != null) {
            this.motsWsContext = null;
            this.requests = null;
            this.notifications = null;
            this.notificationListener.serviceStatusChanged(ServiceStatus.SERVICE_DISCONNECTED);
            Iterator<IAttribute> it = this.remoteAttributes.iterator();
            while (it.hasNext()) {
                it.next().signOut();
            }
            this.remoteAttributes.clear();
            Iterator<Subscription> it2 = this.remoteSubscriptions.iterator();
            while (it2.hasNext()) {
                it2.next().unsubscribe();
            }
            this.remoteSubscriptions.clear();
        }
    }

    public NavigationApiOctaImpl(Context context, INotificationListener iNotificationListener) {
        this.cEManger = null;
        Log.i(TAG, "NavigationApiOctaImpl: ");
        this.context = context;
        this.handler = new Handler(context.getMainLooper());
        this.notificationListener = iNotificationListener;
        ClientEndpointManger companion = ClientEndpointManger.INSTANCE.getInstance(context);
        this.cEManger = companion;
        companion.setClientEndpointMangerCallback(this);
        if (this.cEManger.getConnectionState() == OneCoreClientEndpoint.ConnectionState.CONNECTED) {
            onServerConnect(this.cEManger.getWsContext());
        }
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean launchMap(IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "launchMap: ");
        Intent launchIntentForPackage = this.context.getPackageManager().getLaunchIntentForPackage("com.neusoft.na.navigation");
        if (launchIntentForPackage == null) {
            if (iResultCodeCallBack == null) {
                return false;
            }
            try {
                iResultCodeCallBack.callBack(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue());
                return false;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        }
        this.context.startActivity(launchIntentForPackage);
        if (iResultCodeCallBack == null) {
            return true;
        }
        try {
            iResultCodeCallBack.callBack(ResultCode.SUCCESS.getValue().intValue());
            return true;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return true;
        }
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean backToMap(IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
        Log.i(TAG, "backToMap: ");
        if (iResultCodeCallBack != null) {
            iResultCodeCallBack.callBack(ResultCode.SUCCESS.getValue().intValue());
        }
        return launchMap(null);
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean exitMap(IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
        Log.i(TAG, "exitMap: ");
        Intent intent = new Intent();
        intent.setAction("com.neusoft.na.mots.closeMainActivity");
        this.context.sendBroadcast(intent);
        if (iResultCodeCallBack == null) {
            return true;
        }
        iResultCodeCallBack.callBack(ResultCode.SUCCESS.getValue().intValue());
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean cancelNavi(final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "cancelNavi: ");
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestCancelNavi().thenAccept((Consumer<? super ResCancelNavi>) new Consumer<ResCancelNavi>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.37
                @Override // java9.util.function.Consumer
                public void accept(ResCancelNavi resCancelNavi) {
                    if (iResultCodeCallBack != null) {
                        if (resCancelNavi.getMessageType() == EMessageType.Request) {
                            try {
                                iResultCodeCallBack.callBack(ResultCode.SUCCESS.getValue().intValue());
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        try {
                            iResultCodeCallBack.callBack(resCancelNavi.getCustomError().getValue().intValue());
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.36
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iResultCodeCallBack.callBack(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue());
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iResultCodeCallBack.callBack(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue());
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean openDestinationSearch(final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "openDestinationSearch: ");
        try {
            backToMap(null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestOpenDestinationSearch().thenAccept((Consumer<? super ResOpenDestinationSearch>) new Consumer<ResOpenDestinationSearch>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.39
                @Override // java9.util.function.Consumer
                public void accept(ResOpenDestinationSearch resOpenDestinationSearch) {
                    if (iResultCodeCallBack != null) {
                        if (resOpenDestinationSearch.getMessageType() == EMessageType.Result) {
                            try {
                                iResultCodeCallBack.callBack(resOpenDestinationSearch.getArguments().getResult().getValue().intValue());
                                return;
                            } catch (RemoteException e2) {
                                e2.printStackTrace();
                                return;
                            }
                        }
                        try {
                            iResultCodeCallBack.callBack(resOpenDestinationSearch.getArguments().getResult().getValue().intValue());
                        } catch (RemoteException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.38
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iResultCodeCallBack.callBack(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue());
                        return null;
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        if (this.hasUnsentMSG) {
            try {
                iResultCodeCallBack.callBack(ResultCode.FAILURE_CURRENT_MAP_NOT_SUPPORT.getValue().intValue());
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        } else {
            Log.i(TAG, "hasUnsentMSG");
            this.hasUnsentMSG = true;
            this.unsentMsg = UnsentMethod.openDestinationSearch;
            unSentOpenDestinationSearch(iResultCodeCallBack);
        }
        return true;
    }

    private void unSentOpenDestinationSearch(final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "unSentOpenDestinationSearch: ");
        new Thread(new Runnable() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.40
            @Override // java.lang.Runnable
            public void run() {
                Log.i(NavigationApiOctaImpl.TAG, "unSentOpenDestinationSearch:lock ");
                while (NavigationApiOctaImpl.this.hasUnsentMSG) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
                NavigationApiOctaImpl.this.openDestinationSearch(iResultCodeCallBack);
                Log.i(NavigationApiOctaImpl.TAG, "unSentOpenDestinationSearch:unlock ");
            }
        }).start();
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean mapZoomInOut(int i, final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "mapZoomInOut:zoomInOutType:: " + i);
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestMapZoomInOut(new ReqMapZoomInOut(ZoomInOutType.getByValue(Integer.valueOf(i)))).thenAccept((Consumer<? super ResMapZoomInOut>) new Consumer<ResMapZoomInOut>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.42
                @Override // java9.util.function.Consumer
                public void accept(ResMapZoomInOut resMapZoomInOut) {
                    if (iResultCodeCallBack != null) {
                        if (resMapZoomInOut.getMessageType() == EMessageType.Result) {
                            try {
                                iResultCodeCallBack.callBack(ResultCode.SUCCESS.getValue().intValue());
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        try {
                            iResultCodeCallBack.callBack(resMapZoomInOut.getCustomError().getValue().intValue());
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.41
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iResultCodeCallBack.callBack(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue());
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iResultCodeCallBack.callBack(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean switchTraffic(int i, final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "switchTraffic:trafficType:: " + i);
        if (this.requests != null) {
            this.requests.requestSwitchTraffic(new ReqSwitchTraffic(TrafficType.getByValue(Integer.valueOf(i)))).thenAccept((Consumer<? super ResSwitchTraffic>) new Consumer<ResSwitchTraffic>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.44
                @Override // java9.util.function.Consumer
                public void accept(ResSwitchTraffic resSwitchTraffic) {
                    if (iResultCodeCallBack != null) {
                        if (resSwitchTraffic.getMessageType() == EMessageType.Result) {
                            try {
                                iResultCodeCallBack.callBack(ResultCode.SUCCESS.getValue().intValue());
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        try {
                            iResultCodeCallBack.callBack(resSwitchTraffic.getCustomError().getValue().intValue());
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.43
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iResultCodeCallBack.callBack(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue());
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iResultCodeCallBack.callBack(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean setMapViewMode(int i, final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "setMapViewMode: viewMode:" + i);
        if (this.requests != null) {
            this.requests.requestSetMapViewMode(new ReqSetMapViewMode(ViewModeType.getByValue(Integer.valueOf(i)))).thenAccept((Consumer<? super ResSetMapViewMode>) new Consumer<ResSetMapViewMode>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.46
                @Override // java9.util.function.Consumer
                public void accept(ResSetMapViewMode resSetMapViewMode) {
                    if (iResultCodeCallBack != null) {
                        if (resSetMapViewMode.getMessageType() == EMessageType.Result) {
                            try {
                                iResultCodeCallBack.callBack(ResultCode.SUCCESS.getValue().intValue());
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        try {
                            iResultCodeCallBack.callBack(resSetMapViewMode.getCustomError().getValue().intValue());
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.45
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iResultCodeCallBack.callBack(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue());
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iResultCodeCallBack.callBack(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean naviFullView(final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "naviFullView: ");
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestNaviFullView(new ReqNaviFullView()).thenAccept((Consumer<? super ResNaviFullView>) new Consumer<ResNaviFullView>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.48
                @Override // java9.util.function.Consumer
                public void accept(ResNaviFullView resNaviFullView) {
                    if (iResultCodeCallBack != null) {
                        if (resNaviFullView.getMessageType() == EMessageType.Result) {
                            try {
                                iResultCodeCallBack.callBack(ResultCode.SUCCESS.getValue().intValue());
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        try {
                            iResultCodeCallBack.callBack(resNaviFullView.getCustomError().getValue().intValue());
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.47
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iResultCodeCallBack.callBack(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue());
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iResultCodeCallBack.callBack(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean showMyLocation(int i, final IShowMyLocationCallBack iShowMyLocationCallBack) {
        Log.i(TAG, "showMyLocation: actionType:" + i);
        if (i == 0) {
            try {
                backToMap(null);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if (this.requests != null) {
            this.requests.requestShowMyLocation(new ReqShowMyLocation(ShowLocationActionType.getByValue(Integer.valueOf(i)))).thenAccept((Consumer<? super ResShowMyLocation>) new Consumer<ResShowMyLocation>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.50
                @Override // java9.util.function.Consumer
                public void accept(ResShowMyLocation resShowMyLocation) {
                    if (iShowMyLocationCallBack != null) {
                        if (resShowMyLocation.getMessageType() == EMessageType.Result) {
                            LocationInfo locationInfo = resShowMyLocation.getArguments().getLocationInfo();
                            try {
                                iShowMyLocationCallBack.showMyLocationResult(ResultCode.SUCCESS.getValue().intValue(), locationInfo.getAddress(), locationInfo.getFormatedAddress(), locationInfo.getCity(), locationInfo.getCityCode(), locationInfo.getProvince(), locationInfo.getCountry(), locationInfo.getCountryCode(), locationInfo.getAdCode(), locationInfo.getAdName(), locationInfo.getGeoPosition().getLatitude().doubleValue(), locationInfo.getGeoPosition().getLongitude().doubleValue());
                                return;
                            } catch (RemoteException e2) {
                                e2.printStackTrace();
                                return;
                            }
                        }
                        try {
                            iShowMyLocationCallBack.showMyLocationResult(resShowMyLocation.getCustomError().getValue().intValue(), "", "", "", "", "", "", "", "", "", 0.0d, 0.0d);
                        } catch (RemoteException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.49
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iShowMyLocationCallBack.showMyLocationResult(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue(), "", "", "", "", "", "", "", "", "", 0.0d, 0.0d);
                        return null;
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        if (i != 0) {
            try {
                iShowMyLocationCallBack.showMyLocationResult(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue(), "", "", "", "", "", "", "", "", "", 0.0d, 0.0d);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        } else if (this.hasUnsentMSG) {
            try {
                iShowMyLocationCallBack.showMyLocationResult(ResultCode.FAILURE_CURRENT_MAP_NOT_SUPPORT.getValue().intValue(), "", "", "", "", "", "", "", "", "", 0.0d, 0.0d);
            } catch (RemoteException e3) {
                e3.printStackTrace();
            }
        } else {
            this.hasUnsentMSG = true;
            this.unsentMsg = UnsentMethod.showMyLocation;
            unSentshowMyLocation(i, iShowMyLocationCallBack);
            return true;
        }
        return true;
    }

    private void unSentshowMyLocation(final int i, final IShowMyLocationCallBack iShowMyLocationCallBack) {
        Log.i(TAG, "unSentshowMyLocation: ");
        new Thread(new Runnable() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.51
            @Override // java.lang.Runnable
            public void run() {
                Log.i(NavigationApiOctaImpl.TAG, "unSentshowMyLocation:lock ");
                while (NavigationApiOctaImpl.this.hasUnsentMSG) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
                NavigationApiOctaImpl.this.showMyLocation(i, iShowMyLocationCallBack);
                Log.i(NavigationApiOctaImpl.TAG, "unSentshowMyLocation:unlock ");
            }
        }).start();
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean setRoutePrefer(int i, final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "setRoutePrefer: strategy::" + i);
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestSetRoutePrefer(new ReqSetRoutePrefer(StrategyType.getByValue(Integer.valueOf(i)))).thenAccept((Consumer<? super ResSetRoutePrefer>) new Consumer<ResSetRoutePrefer>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.53
                @Override // java9.util.function.Consumer
                public void accept(ResSetRoutePrefer resSetRoutePrefer) {
                    if (iResultCodeCallBack != null) {
                        if (resSetRoutePrefer.getMessageType() == EMessageType.Result) {
                            try {
                                iResultCodeCallBack.callBack(ResultCode.SUCCESS.getValue().intValue());
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        try {
                            iResultCodeCallBack.callBack(resSetRoutePrefer.getCustomError().getValue().intValue());
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.52
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iResultCodeCallBack.callBack(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue());
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iResultCodeCallBack.callBack(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean selectRoute(int i, final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "selectRoute:selectIndex " + i);
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestSelectRoute(new ReqSelectRoute(Integer.valueOf(i))).thenAccept((Consumer<? super ResSelectRoute>) new Consumer<ResSelectRoute>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.55
                @Override // java9.util.function.Consumer
                public void accept(ResSelectRoute resSelectRoute) {
                    if (iResultCodeCallBack != null) {
                        if (resSelectRoute.getMessageType() == EMessageType.Result) {
                            try {
                                iResultCodeCallBack.callBack(ResultCode.SUCCESS.getValue().intValue());
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        try {
                            iResultCodeCallBack.callBack(resSelectRoute.getCustomError().getValue().intValue());
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.54
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iResultCodeCallBack.callBack(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue());
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iResultCodeCallBack.callBack(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean getFavoritePois(int i, final IGetFavoritePoisCallBack iGetFavoritePoisCallBack) {
        Log.i(TAG, "getFavoritePois: favType:" + i);
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestGetFavoritePois(new ReqGetFavoritePois(FavoriteType.getByValue(Integer.valueOf(i)))).thenAccept((Consumer<? super ResGetFavoritePois>) new Consumer<ResGetFavoritePois>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.57
                @Override // java9.util.function.Consumer
                public void accept(ResGetFavoritePois resGetFavoritePois) {
                    if (resGetFavoritePois.getMessageType() == EMessageType.Result) {
                        if (iGetFavoritePoisCallBack != null) {
                            List<FavoritePoiInfo> favoritePoiInfoList = resGetFavoritePois.getArguments().getFavoritePoiInfoList();
                            ArrayList arrayList = new ArrayList();
                            if (favoritePoiInfoList.size() > 0) {
                                for (int i2 = 0; i2 < favoritePoiInfoList.size(); i2++) {
                                    com.neusoft.na.ocservice.FavoritePoiInfo favoritePoiInfo = new com.neusoft.na.ocservice.FavoritePoiInfo();
                                    favoritePoiInfo.setFavoriteType(favoritePoiInfoList.get(i2).getFavoriteType().getValue().intValue());
                                    favoritePoiInfo.setPoiInfo(NavigationApiOctaImpl.this.octaPOI2AidlPOI(favoritePoiInfoList.get(i2).getPoiInfo()));
                                    arrayList.add(favoritePoiInfo);
                                }
                            }
                            try {
                                iGetFavoritePoisCallBack.getFavoritePoisResult(ResultCode.SUCCESS.getValue().intValue(), arrayList);
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        throw new RuntimeException("receive error response");
                    }
                    try {
                        iGetFavoritePoisCallBack.getFavoritePoisResult(resGetFavoritePois.getCustomError().getValue().intValue(), null);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.56
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iGetFavoritePoisCallBack.getFavoritePoisResult(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue(), null);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });
            return true;
        }
        try {
            iGetFavoritePoisCallBack.getFavoritePoisResult(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue(), null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean getHighwayExitInfo(int i, final IGetHighwayExitInfo iGetHighwayExitInfo) {
        Log.i(TAG, "getHighwayExitInfo: actionType:" + i);
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestGetHighwayExitInfo(new ReqGetHighwayExitInfo(HighwayExitType.getByValue(Integer.valueOf(i)))).thenAccept((Consumer<? super ResGetHighwayExitInfo>) new Consumer<ResGetHighwayExitInfo>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.59
                @Override // java9.util.function.Consumer
                public void accept(ResGetHighwayExitInfo resGetHighwayExitInfo) {
                    if (resGetHighwayExitInfo.getMessageType() == EMessageType.Result) {
                        if (iGetHighwayExitInfo != null) {
                            HighwayExitInfo highwayExitInfo = resGetHighwayExitInfo.getArguments().getHighwayExitInfo();
                            new ResGetHighwayExitInfo(highwayExitInfo);
                            try {
                                iGetHighwayExitInfo.getHighwayExitInfoResult(ResultCode.SUCCESS.getValue().intValue(), highwayExitInfo.getExitCount().intValue(), highwayExitInfo.getExitNumStr(), highwayExitInfo.getExitDirection(), highwayExitInfo.getEtaDistanceM().intValue(), highwayExitInfo.getEtaTime().intValue());
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        throw new RuntimeException("receive error response");
                    }
                    if (iGetHighwayExitInfo != null) {
                        new ResGetHighwayExitInfo(resGetHighwayExitInfo.getArguments().getHighwayExitInfo());
                        try {
                            iGetHighwayExitInfo.getHighwayExitInfoResult(resGetHighwayExitInfo.getCustomError().getValue().intValue(), 0, "", "", 0L, 0L);
                            return;
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                    throw new RuntimeException("receive error response");
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.58
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iGetHighwayExitInfo.getHighwayExitInfoResult(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue(), 0, "", "", 0L, 0L);
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iGetHighwayExitInfo.getHighwayExitInfoResult(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue(), 0, "", "", 0L, 0L);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean setNaviMuteMode(int i, final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "setNaviMuteMode: muteState:" + i);
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestSetNaviMuteMode(new ReqSetNaviMuteMode(MuteType.getByValue(Integer.valueOf(i)))).thenAccept((Consumer<? super ResSetNaviMuteMode>) new Consumer<ResSetNaviMuteMode>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.61
                @Override // java9.util.function.Consumer
                public void accept(ResSetNaviMuteMode resSetNaviMuteMode) {
                    if (resSetNaviMuteMode.getMessageType() == EMessageType.Result) {
                        IResultCodeCallBack iResultCodeCallBack2 = iResultCodeCallBack;
                        if (iResultCodeCallBack2 != null) {
                            try {
                                iResultCodeCallBack2.callBack(ResultCode.SUCCESS.getValue().intValue());
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        throw new RuntimeException("receive error response");
                    }
                    IResultCodeCallBack iResultCodeCallBack3 = iResultCodeCallBack;
                    if (iResultCodeCallBack3 != null) {
                        try {
                            iResultCodeCallBack3.callBack(resSetNaviMuteMode.getCustomError().getValue().intValue());
                            return;
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                    throw new RuntimeException("receive error response");
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.60
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iResultCodeCallBack.callBack(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue());
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iResultCodeCallBack.callBack(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean getTrafficSummaryInfo(String str, final IGetTrafficSummaryInfocCallBack iGetTrafficSummaryInfocCallBack) {
        Log.i(TAG, "getTrafficSummaryInfo:trafficText :" + str);
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestGetTrafficSummaryInfo(new ReqGetTrafficSummaryInfo(str)).thenAccept((Consumer<? super ResGetTrafficSummaryInfo>) new Consumer<ResGetTrafficSummaryInfo>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.63
                @Override // java9.util.function.Consumer
                public void accept(ResGetTrafficSummaryInfo resGetTrafficSummaryInfo) {
                    if (resGetTrafficSummaryInfo.getMessageType() == EMessageType.Result) {
                        if (iGetTrafficSummaryInfocCallBack != null) {
                            String string = resGetTrafficSummaryInfo.getArguments().getTrafficSummaryInfo().toString();
                            new ResGetTrafficSummaryInfo(string);
                            try {
                                iGetTrafficSummaryInfocCallBack.getTrafficSummaryInfoResult(ResultCode.SUCCESS.getValue().intValue(), string);
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        throw new RuntimeException("receive error response");
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.62
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iGetTrafficSummaryInfocCallBack.getTrafficSummaryInfoResult(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue(), "");
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iGetTrafficSummaryInfocCallBack.getTrafficSummaryInfoResult(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue(), "");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean alongTheWaySearch(int i, int i2, final IAlongTheWaySearchCallBack iAlongTheWaySearchCallBack) {
        Log.i(TAG, "alongTheWaySearch: action::" + i + "searchType::" + i2);
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestAlongTheWaySearch(new ReqAlongTheWaySearch(SearchActionType.getByValue(Integer.valueOf(i)), SearchType.getByValue(Integer.valueOf(i2)))).thenAccept((Consumer<? super ResAlongTheWaySearch>) new Consumer<ResAlongTheWaySearch>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.65
                @Override // java9.util.function.Consumer
                public void accept(ResAlongTheWaySearch resAlongTheWaySearch) {
                    if (resAlongTheWaySearch.getMessageType() == EMessageType.Result) {
                        if (iAlongTheWaySearchCallBack != null) {
                            List<SuggestionCity> suggestionCityList = resAlongTheWaySearch.getArguments().getSuggestionCityList();
                            List<PoiInfo> poiInfoList = resAlongTheWaySearch.getArguments().getPoiInfoList();
                            ArrayList arrayList = new ArrayList();
                            for (int i3 = 0; i3 < suggestionCityList.size(); i3++) {
                                arrayList.add(NavigationApiOctaImpl.this.octaSuggestionCity2Aidl(suggestionCityList.get(i3)));
                            }
                            ArrayList arrayList2 = new ArrayList();
                            for (int i4 = 0; i4 < poiInfoList.size(); i4++) {
                                arrayList2.add(NavigationApiOctaImpl.this.octaPOI2AidlPOI(poiInfoList.get(i4)));
                            }
                            try {
                                iAlongTheWaySearchCallBack.alongTheWaySearchResult(ResultCode.SUCCESS.getValue().intValue(), arrayList, arrayList2);
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        throw new RuntimeException("receive error response");
                    }
                    IAlongTheWaySearchCallBack iAlongTheWaySearchCallBack2 = iAlongTheWaySearchCallBack;
                    if (iAlongTheWaySearchCallBack2 != null) {
                        try {
                            iAlongTheWaySearchCallBack2.alongTheWaySearchResult(resAlongTheWaySearch.getCustomError().getValue().intValue(), null, null);
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.64
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iAlongTheWaySearchCallBack.alongTheWaySearchResult(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue(), null, null);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });
            return true;
        }
        try {
            iAlongTheWaySearchCallBack.alongTheWaySearchResult(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue(), null, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean specialPoiNavi(int i, int i2, final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "specialPoiNavi: action:" + i + "poiType" + i2);
        try {
            backToMap(null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestSpecialPoiNavi(new ReqSpecialPoiNavi(SpecialPoiNaviType.getByValue(Integer.valueOf(i)), SpecialPoiType.getByValue(Integer.valueOf(i2)))).thenAccept((Consumer<? super ResSpecialPoiNavi>) new Consumer<ResSpecialPoiNavi>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.67
                @Override // java9.util.function.Consumer
                public void accept(ResSpecialPoiNavi resSpecialPoiNavi) {
                    if (resSpecialPoiNavi.getMessageType() == EMessageType.Result) {
                        IResultCodeCallBack iResultCodeCallBack2 = iResultCodeCallBack;
                        if (iResultCodeCallBack2 != null) {
                            try {
                                iResultCodeCallBack2.callBack(ResultCode.SUCCESS.getValue().intValue());
                                return;
                            } catch (RemoteException e2) {
                                e2.printStackTrace();
                                return;
                            }
                        }
                        throw new RuntimeException("receive error response");
                    }
                    IResultCodeCallBack iResultCodeCallBack3 = iResultCodeCallBack;
                    if (iResultCodeCallBack3 != null) {
                        try {
                            iResultCodeCallBack3.callBack(resSpecialPoiNavi.getCustomError().getValue().intValue());
                            return;
                        } catch (RemoteException e3) {
                            e3.printStackTrace();
                            return;
                        }
                    }
                    throw new RuntimeException("receive error response");
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.66
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iResultCodeCallBack.callBack(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue());
                        return null;
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        if (i != 0) {
            try {
                iResultCodeCallBack.callBack(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue());
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        } else if (this.hasUnsentMSG) {
            try {
                iResultCodeCallBack.callBack(ResultCode.FAILURE_CURRENT_MAP_NOT_SUPPORT.getValue().intValue());
            } catch (RemoteException e3) {
                e3.printStackTrace();
            }
        } else {
            this.hasUnsentMSG = true;
            this.unsentMsg = UnsentMethod.specialPoiNavi;
            unSentspecialPoiNavi(i, i2, iResultCodeCallBack);
        }
        return true;
    }

    private void unSentspecialPoiNavi(final int i, final int i2, final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "unSentspecialPoiNavi: ");
        new Thread(new Runnable() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.68
            @Override // java.lang.Runnable
            public void run() {
                Log.i(NavigationApiOctaImpl.TAG, "unSentspecialPoiNavi:lock ");
                while (NavigationApiOctaImpl.this.hasUnsentMSG) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
                NavigationApiOctaImpl.this.specialPoiNavi(i, i2, iResultCodeCallBack);
                Log.i(NavigationApiOctaImpl.TAG, "unSentspecialPoiNavi:unlock ");
            }
        }).start();
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean modifyNaviVia(int i, PoiInfo poiInfo, final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "modifyNaviVia: action" + i);
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestModifyNaviVia(new ReqModifyNaviVia(RouteDestActionType.getByValue(Integer.valueOf(i)), poiInfo)).thenAccept((Consumer<? super ResModifyNaviVia>) new Consumer<ResModifyNaviVia>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.70
                @Override // java9.util.function.Consumer
                public void accept(ResModifyNaviVia resModifyNaviVia) {
                    if (resModifyNaviVia.getMessageType() == EMessageType.Result) {
                        IResultCodeCallBack iResultCodeCallBack2 = iResultCodeCallBack;
                        if (iResultCodeCallBack2 != null) {
                            try {
                                iResultCodeCallBack2.callBack(ResultCode.SUCCESS.getValue().intValue());
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        throw new RuntimeException("receive error response");
                    }
                    try {
                        iResultCodeCallBack.callBack(resModifyNaviVia.getCustomError().getValue().intValue());
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.69
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iResultCodeCallBack.callBack(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue());
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iResultCodeCallBack.callBack(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean editFavoritePois(int i, String str, String str2, double d, double d2, String str3, final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "editFavoritePois: favoriteType:" + i + "poiName::" + str);
        if (this.requests != null) {
            this.requests.requestEditFavoritePois(new ReqEditFavoritePois(FavoriteType.getByValue(Integer.valueOf(i)), str, str2, new GeoPosition(Double.valueOf(d), Double.valueOf(d2)), str3)).thenAccept((Consumer<? super ResEditFavoritePois>) new Consumer<ResEditFavoritePois>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.72
                @Override // java9.util.function.Consumer
                public void accept(ResEditFavoritePois resEditFavoritePois) {
                    if (resEditFavoritePois.getMessageType() == EMessageType.Result) {
                        IResultCodeCallBack iResultCodeCallBack2 = iResultCodeCallBack;
                        if (iResultCodeCallBack2 != null) {
                            try {
                                iResultCodeCallBack2.callBack(ResultCode.SUCCESS.getValue().intValue());
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        throw new RuntimeException("receive error response");
                    }
                    IResultCodeCallBack iResultCodeCallBack3 = iResultCodeCallBack;
                    if (iResultCodeCallBack3 != null) {
                        try {
                            iResultCodeCallBack3.callBack(resEditFavoritePois.getCustomError().getValue().intValue());
                            return;
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                    throw new RuntimeException("receive error response");
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.71
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iResultCodeCallBack.callBack(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue());
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iResultCodeCallBack.callBack(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean searchByKeyword(int i, int i2, String str, String str2, final ISearchByKeywordCallBack iSearchByKeywordCallBack) {
        Log.i(TAG, "searchByKeyword:action " + i + "searchType" + i2 + "keywords::" + str);
        if (i == 0) {
            try {
                backToMap(null);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestSearchByKeyword(new ReqSearchByKeyword(SearchActionType.getByValue(Integer.valueOf(i)), SearchType.getByValue(Integer.valueOf(i2)), str, str2)).thenAccept((Consumer<? super ResSearchByKeyword>) new Consumer<ResSearchByKeyword>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.74
                @Override // java9.util.function.Consumer
                public void accept(ResSearchByKeyword resSearchByKeyword) {
                    if (resSearchByKeyword.getMessageType() == EMessageType.Result) {
                        if (iSearchByKeywordCallBack != null) {
                            List<SuggestionCity> suggestionCityList = resSearchByKeyword.getArguments().getSuggestionCityList();
                            List<PoiInfo> poiInfoList = resSearchByKeyword.getArguments().getPoiInfoList();
                            ArrayList arrayList = new ArrayList();
                            for (int i3 = 0; i3 < suggestionCityList.size(); i3++) {
                                arrayList.add(NavigationApiOctaImpl.this.octaSuggestionCity2Aidl(suggestionCityList.get(i3)));
                            }
                            ArrayList arrayList2 = new ArrayList();
                            for (int i4 = 0; i4 < poiInfoList.size(); i4++) {
                                arrayList2.add(NavigationApiOctaImpl.this.octaPOI2AidlPOI(poiInfoList.get(i4)));
                            }
                            try {
                                iSearchByKeywordCallBack.searchByKeywordResult(ResultCode.SUCCESS.getValue().intValue(), arrayList, arrayList2);
                                return;
                            } catch (RemoteException e2) {
                                e2.printStackTrace();
                                return;
                            }
                        }
                        throw new RuntimeException("receive error response");
                    }
                    ISearchByKeywordCallBack iSearchByKeywordCallBack2 = iSearchByKeywordCallBack;
                    if (iSearchByKeywordCallBack2 != null) {
                        try {
                            iSearchByKeywordCallBack2.searchByKeywordResult(resSearchByKeyword.getCustomError().getValue().intValue(), null, null);
                        } catch (RemoteException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.73
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iSearchByKeywordCallBack.searchByKeywordResult(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue(), null, null);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                    return null;
                }
            });
            return true;
        }
        if (i != 0) {
            try {
                iSearchByKeywordCallBack.searchByKeywordResult(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue(), null, null);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        } else if (this.hasUnsentMSG) {
            try {
                iSearchByKeywordCallBack.searchByKeywordResult(ResultCode.FAILURE_CURRENT_MAP_NOT_SUPPORT.getValue().intValue(), null, null);
            } catch (RemoteException e3) {
                e3.printStackTrace();
            }
        } else {
            this.hasUnsentMSG = true;
            this.unsentMsg = UnsentMethod.searchByKeyword;
            unSentsearchByKeyword(i, i2, str, str2, iSearchByKeywordCallBack);
        }
        return true;
    }

    private void unSentsearchByKeyword(final int i, final int i2, final String str, final String str2, final ISearchByKeywordCallBack iSearchByKeywordCallBack) {
        Log.i(TAG, "unSentsearchByKeyword: ");
        new Thread(new Runnable() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.75
            @Override // java.lang.Runnable
            public void run() {
                Log.i(NavigationApiOctaImpl.TAG, "unSentsearchByKeyword:lock ");
                while (NavigationApiOctaImpl.this.hasUnsentMSG) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
                NavigationApiOctaImpl.this.searchByKeyword(i, i2, str, str2, iSearchByKeywordCallBack);
                Log.i(NavigationApiOctaImpl.TAG, "unSentsearchByKeyword:unlock ");
            }
        }).start();
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean requestRoutePlanOrNavi(int i, int i2, PoiInfo poiInfo, List<PoiInfo> list, final IRequestRoutePlanOrNavi iRequestRoutePlanOrNavi) {
        Log.i(TAG, "requestRoutePlanOrNavi: action::" + i + "strategy::" + i2);
        if (i == 0 || i == 1) {
            try {
                backToMap(null);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestRequestRoutePlanOrNavi(new ReqRequestRoutePlanOrNavi(RequestRoutePlanOrNaviType.getByValue(Integer.valueOf(i)), StrategyType.getByValue(Integer.valueOf(i2)), poiInfo, list)).thenAccept((Consumer<? super ResRequestRoutePlanOrNavi>) new Consumer<ResRequestRoutePlanOrNavi>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.77
                @Override // java9.util.function.Consumer
                public void accept(ResRequestRoutePlanOrNavi resRequestRoutePlanOrNavi) {
                    if (resRequestRoutePlanOrNavi.getMessageType() == EMessageType.Result) {
                        if (iRequestRoutePlanOrNavi != null) {
                            List<RouteInfo> routeInfoList = resRequestRoutePlanOrNavi.getArguments().getRouteInfoList();
                            ArrayList arrayList = new ArrayList();
                            if (routeInfoList.size() > 0) {
                                for (int i3 = 0; i3 < routeInfoList.size(); i3++) {
                                    arrayList.add(NavigationApiOctaImpl.this.octaRouteInfo2AidlRouteInfo(routeInfoList.get(i3)));
                                }
                            }
                            try {
                                iRequestRoutePlanOrNavi.requestRoutePlanOrNaviResult(ResultCode.SUCCESS.getValue().intValue(), arrayList);
                                return;
                            } catch (RemoteException e2) {
                                e2.printStackTrace();
                                return;
                            }
                        }
                        throw new RuntimeException("receive error response");
                    }
                    IRequestRoutePlanOrNavi iRequestRoutePlanOrNavi2 = iRequestRoutePlanOrNavi;
                    if (iRequestRoutePlanOrNavi2 != null) {
                        try {
                            iRequestRoutePlanOrNavi2.requestRoutePlanOrNaviResult(resRequestRoutePlanOrNavi.getCustomError().getValue().intValue(), null);
                        } catch (RemoteException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.76
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iRequestRoutePlanOrNavi.requestRoutePlanOrNaviResult(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue(), null);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                    return null;
                }
            });
            return true;
        }
        if (i != 0 && i != 1) {
            try {
                iRequestRoutePlanOrNavi.requestRoutePlanOrNaviResult(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue(), null);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        } else if (this.hasUnsentMSG) {
            try {
                iRequestRoutePlanOrNavi.requestRoutePlanOrNaviResult(ResultCode.FAILURE_CURRENT_MAP_NOT_SUPPORT.getValue().intValue(), null);
            } catch (RemoteException e3) {
                e3.printStackTrace();
            }
        } else {
            this.hasUnsentMSG = true;
            this.unsentMsg = UnsentMethod.requestRoutePlanOrNavi;
            unSentrequestRoutePlanOrNavi(i, i2, poiInfo, list, iRequestRoutePlanOrNavi);
        }
        return true;
    }

    private void unSentrequestRoutePlanOrNavi(final int i, final int i2, final PoiInfo poiInfo, final List<PoiInfo> list, final IRequestRoutePlanOrNavi iRequestRoutePlanOrNavi) {
        Log.i(TAG, "unSentsearchByKeyword: ");
        new Thread(new Runnable() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.78
            @Override // java.lang.Runnable
            public void run() {
                Log.i(NavigationApiOctaImpl.TAG, "unSentsearchByKeyword:lock ");
                while (NavigationApiOctaImpl.this.hasUnsentMSG) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
                NavigationApiOctaImpl.this.requestRoutePlanOrNavi(i, i2, poiInfo, list, iRequestRoutePlanOrNavi);
                Log.i(NavigationApiOctaImpl.TAG, "unSentsearchByKeyword:unlock ");
            }
        }).start();
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean searchAround(int i, int i2, String str, int i3, double d, double d2, final ISearchAroundCallBack iSearchAroundCallBack) {
        Log.i(TAG, "searchAround: action::" + i + "searchType::" + i2);
        if (i == 0) {
            try {
                backToMap(null);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestSearchAround(new ReqSearchAround(SearchActionType.getByValue(Integer.valueOf(i)), SearchType.getByValue(Integer.valueOf(i2)), str, Integer.valueOf(i3), new GeoPosition(Double.valueOf(d), Double.valueOf(d2)))).thenAccept((Consumer<? super ResSearchAround>) new Consumer<ResSearchAround>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.80
                @Override // java9.util.function.Consumer
                public void accept(ResSearchAround resSearchAround) {
                    if (resSearchAround.getMessageType() == EMessageType.Result) {
                        if (iSearchAroundCallBack != null) {
                            List<SuggestionCity> suggestionCityList = resSearchAround.getArguments().getSuggestionCityList();
                            List<PoiInfo> poiInfoList = resSearchAround.getArguments().getPoiInfoList();
                            ArrayList arrayList = new ArrayList();
                            for (int i4 = 0; i4 < suggestionCityList.size(); i4++) {
                                arrayList.add(NavigationApiOctaImpl.this.octaSuggestionCity2Aidl(suggestionCityList.get(i4)));
                            }
                            ArrayList arrayList2 = new ArrayList();
                            for (int i5 = 0; i5 < poiInfoList.size(); i5++) {
                                arrayList2.add(NavigationApiOctaImpl.this.octaPOI2AidlPOI(poiInfoList.get(i5)));
                            }
                            try {
                                iSearchAroundCallBack.searchAroundResult(ResultCode.SUCCESS.getValue().intValue(), arrayList, arrayList2);
                                return;
                            } catch (RemoteException e2) {
                                e2.printStackTrace();
                                return;
                            }
                        }
                        throw new RuntimeException("receive error response");
                    }
                    ISearchAroundCallBack iSearchAroundCallBack2 = iSearchAroundCallBack;
                    if (iSearchAroundCallBack2 != null) {
                        try {
                            iSearchAroundCallBack2.searchAroundResult(resSearchAround.getCustomError().getValue().intValue(), null, null);
                        } catch (RemoteException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.79
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iSearchAroundCallBack.searchAroundResult(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue(), null, null);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                    return null;
                }
            });
            return true;
        }
        if (i != 0) {
            try {
                iSearchAroundCallBack.searchAroundResult(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue(), null, null);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        } else if (this.hasUnsentMSG) {
            try {
                iSearchAroundCallBack.searchAroundResult(ResultCode.FAILURE_CURRENT_MAP_NOT_SUPPORT.getValue().intValue(), null, null);
            } catch (RemoteException e3) {
                e3.printStackTrace();
            }
        } else {
            this.hasUnsentMSG = true;
            this.unsentMsg = UnsentMethod.searchAround;
            unSentsearchAround(i, i2, str, i3, d, d2, iSearchAroundCallBack);
        }
        return true;
    }

    private void unSentsearchAround(final int i, final int i2, final String str, final int i3, final double d, final double d2, final ISearchAroundCallBack iSearchAroundCallBack) {
        Log.i(TAG, "unSentsearchAround: ");
        new Thread(new Runnable() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.81
            @Override // java.lang.Runnable
            public void run() {
                Log.i(NavigationApiOctaImpl.TAG, "unSentsearchAround:lock ");
                while (NavigationApiOctaImpl.this.hasUnsentMSG) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
                NavigationApiOctaImpl.this.searchAround(i, i2, str, i3, d, d2, iSearchAroundCallBack);
                Log.i(NavigationApiOctaImpl.TAG, "unSentsearchAround:unlock ");
            }
        }).start();
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean getHistoryPois(int i, final IGetHistoryPoisCallBack iGetHistoryPoisCallBack) {
        Log.i(TAG, "getHistoryPois: maxCount" + i);
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestGetHistoryPois(new ReqGetHistoryPois(Integer.valueOf(i))).thenAccept((Consumer<? super ResGetHistoryPois>) new Consumer<ResGetHistoryPois>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.83
                @Override // java9.util.function.Consumer
                public void accept(ResGetHistoryPois resGetHistoryPois) {
                    if (resGetHistoryPois.getMessageType() == EMessageType.Result) {
                        if (iGetHistoryPoisCallBack != null) {
                            List<PoiInfo> poiInfoList = resGetHistoryPois.getArguments().getPoiInfoList();
                            ArrayList arrayList = new ArrayList();
                            if (poiInfoList.size() > 0) {
                                for (int i2 = 0; i2 < poiInfoList.size(); i2++) {
                                    arrayList.add(NavigationApiOctaImpl.this.octaPOI2AidlPOI(poiInfoList.get(i2)));
                                }
                            }
                            try {
                                iGetHistoryPoisCallBack.getHistoryPoisResult(ResultCode.SUCCESS.getValue().intValue(), arrayList);
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        throw new RuntimeException("receive error response");
                    }
                    try {
                        iGetHistoryPoisCallBack.getHistoryPoisResult(resGetHistoryPois.getCustomError().getValue().intValue(), null);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.82
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iGetHistoryPoisCallBack.getHistoryPoisResult(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue(), null);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });
            return true;
        }
        try {
            iGetHistoryPoisCallBack.getHistoryPoisResult(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue(), null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean getRouteInfo(final IGetRouteInfoCallBack iGetRouteInfoCallBack) {
        Log.i(TAG, "getRouteInfo: ");
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestGetRouteInfo(new ReqGetRouteInfo()).thenAccept((Consumer<? super ResGetRouteInfo>) new Consumer<ResGetRouteInfo>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.85
                @Override // java9.util.function.Consumer
                public void accept(ResGetRouteInfo resGetRouteInfo) {
                    if (resGetRouteInfo.getMessageType() == EMessageType.Result) {
                        if (iGetRouteInfoCallBack != null) {
                            PoiInfo startPoi = resGetRouteInfo.getArguments().getStartPoi();
                            PoiInfo endPoi = resGetRouteInfo.getArguments().getEndPoi();
                            List<RouteInfo> routeInfo = resGetRouteInfo.getArguments().getRouteInfo();
                            ArrayList arrayList = new ArrayList();
                            if (routeInfo.size() > 0) {
                                for (int i = 0; i < routeInfo.size(); i++) {
                                    arrayList.add(NavigationApiOctaImpl.this.octaRouteInfo2AidlRouteInfo(routeInfo.get(i)));
                                }
                            }
                            List<PoiInfo> viaPois = resGetRouteInfo.getArguments().getViaPois();
                            ArrayList arrayList2 = new ArrayList();
                            if (viaPois.size() > 0) {
                                for (int i2 = 0; i2 < viaPois.size(); i2++) {
                                    arrayList2.add(NavigationApiOctaImpl.this.octaPOI2AidlPOI(viaPois.get(i2)));
                                }
                            }
                            try {
                                iGetRouteInfoCallBack.getRouteInfoResult(ResultCode.SUCCESS.getValue().intValue(), NavigationApiOctaImpl.this.octaPOI2AidlPOI(startPoi), NavigationApiOctaImpl.this.octaPOI2AidlPOI(endPoi), arrayList, arrayList2);
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        throw new RuntimeException("receive error response");
                    }
                    IGetRouteInfoCallBack iGetRouteInfoCallBack2 = iGetRouteInfoCallBack;
                    if (iGetRouteInfoCallBack2 != null) {
                        try {
                            iGetRouteInfoCallBack2.getRouteInfoResult(resGetRouteInfo.getCustomError().getValue().intValue(), null, null, null, null);
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.84
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iGetRouteInfoCallBack.getRouteInfoResult(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue(), null, null, null, null);
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iGetRouteInfoCallBack.getRouteInfoResult(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue(), null, null, null, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean getFrontTraffic(int i, final IGetFrontTrafficCallBack iGetFrontTrafficCallBack) {
        Log.i(TAG, "getFrontTraffic: frontDistance:" + i);
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestGetFrontTraffic(new ReqGetFrontTraffic(Integer.valueOf(i))).thenAccept((Consumer<? super ResGetFrontTraffic>) new Consumer<ResGetFrontTraffic>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.87
                @Override // java9.util.function.Consumer
                public void accept(ResGetFrontTraffic resGetFrontTraffic) {
                    if (resGetFrontTraffic.getMessageType() == EMessageType.Result) {
                        if (iGetFrontTrafficCallBack != null) {
                            try {
                                iGetFrontTrafficCallBack.getFrontTrafficResult(ResultCode.SUCCESS.getValue().intValue(), resGetFrontTraffic.getArguments().getFrontTrafficInfo().toString());
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        throw new RuntimeException("receive error response");
                    }
                    if (iGetFrontTrafficCallBack != null) {
                        try {
                            iGetFrontTrafficCallBack.getFrontTrafficResult(resGetFrontTraffic.getCustomError().getValue().intValue(), resGetFrontTraffic.getArguments().getFrontTrafficInfo().toString());
                            return;
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                    throw new RuntimeException("receive error response");
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.86
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iGetFrontTrafficCallBack.getFrontTrafficResult(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue(), "");
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iGetFrontTrafficCallBack.getFrontTrafficResult(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue(), "");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean getNaviMuteMode(final IGetNaviMuteModeCallBack iGetNaviMuteModeCallBack) {
        Log.i(TAG, "getNaviMuteMode: ");
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestGetNaviMuteMode().thenAccept((Consumer<? super ResGetNaviMuteMode>) new Consumer<ResGetNaviMuteMode>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.89
                @Override // java9.util.function.Consumer
                public void accept(ResGetNaviMuteMode resGetNaviMuteMode) {
                    if (resGetNaviMuteMode.getMessageType() == EMessageType.Result) {
                        if (iGetNaviMuteModeCallBack != null) {
                            try {
                                iGetNaviMuteModeCallBack.getNaviMuteModeResult(ResultCode.SUCCESS.getValue().intValue(), resGetNaviMuteMode.getArguments().getMuteState().getValue().intValue());
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        throw new RuntimeException("receive error response");
                    }
                    try {
                        iGetNaviMuteModeCallBack.getNaviMuteModeResult(resGetNaviMuteMode.getCustomError().getValue().intValue(), 0);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.88
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iGetNaviMuteModeCallBack.getNaviMuteModeResult(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue(), 0);
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iGetNaviMuteModeCallBack.getNaviMuteModeResult(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue(), 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean getGuideInfo(final IGetGuideInfoCallBack iGetGuideInfoCallBack) {
        Log.i(TAG, "getGuideInfo: ");
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestGetGuideInfo(new ReqGetGuideInfo()).thenAccept((Consumer<? super ResGetGuideInfo>) new Consumer<ResGetGuideInfo>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.91
                @Override // java9.util.function.Consumer
                public void accept(ResGetGuideInfo resGetGuideInfo) {
                    if (resGetGuideInfo.getMessageType() == EMessageType.Result) {
                        if (iGetGuideInfoCallBack != null) {
                            GuidanceInfo guidanceInfo = resGetGuideInfo.getArguments().getGuidanceInfo();
                            try {
                                iGetGuideInfoCallBack.getGuideInfoResult(ResultCode.SUCCESS.getValue().intValue(), guidanceInfo.getTurnId().getValue().intValue(), guidanceInfo.getRemainDistanceM().intValue(), guidanceInfo.getNextTurnDistanceM().intValue(), guidanceInfo.getNextTurnTime().intValue(), guidanceInfo.getRemainTime().intValue(), guidanceInfo.getNextRoadName(), guidanceInfo.getCurRoadName(), guidanceInfo.getCarDirection().floatValue(), guidanceInfo.getGuideType().getValue().intValue(), guidanceInfo.getRoadType().getValue().intValue(), guidanceInfo.getSapaType().getValue().intValue(), guidanceInfo.getSapaName(), guidanceInfo.getSapaDistanceM().intValue(), guidanceInfo.getCameratype().getValue().intValue(), guidanceInfo.getCameraDistanceM().intValue(), guidanceInfo.getCameraSpeed().intValue());
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        throw new RuntimeException("receive error response");
                    }
                    if (iGetGuideInfoCallBack != null) {
                        resGetGuideInfo.getArguments().getGuidanceInfo();
                        try {
                            iGetGuideInfoCallBack.getGuideInfoResult(resGetGuideInfo.getCustomError().getValue().intValue(), 0, 0, 0, 0, 0, "", "", 0.0f, 0, 0, 0, "", 0, 0, 0, 0);
                            return;
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                    throw new RuntimeException("receive error response");
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.90
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iGetGuideInfoCallBack.getGuideInfoResult(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue(), 0, 0, 0, 0, 0, "", "", 0.0f, 0, 0, 0, "", 0, 0, 0, 0);
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iGetGuideInfoCallBack.getGuideInfoResult(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue(), 0, 0, 0, 0, 0, "", "", 0.0f, 0, 0, 0, "", 0, 0, 0, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean goFavorite(int i, final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "goFavorite: favType::" + i);
        try {
            backToMap(null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestGoFavorite(new ReqGoFavorite(FavoriteType.getByValue(Integer.valueOf(i)))).thenAccept((Consumer<? super ResGoFavorite>) new Consumer<ResGoFavorite>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.93
                @Override // java9.util.function.Consumer
                public void accept(ResGoFavorite resGoFavorite) {
                    if (iResultCodeCallBack != null) {
                        if (resGoFavorite.getMessageType() == EMessageType.Result) {
                            try {
                                iResultCodeCallBack.callBack(ResultCode.SUCCESS.getValue().intValue());
                                return;
                            } catch (RemoteException e2) {
                                e2.printStackTrace();
                                return;
                            }
                        }
                        try {
                            iResultCodeCallBack.callBack(resGoFavorite.getCustomError().getValue().intValue());
                        } catch (RemoteException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.92
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iResultCodeCallBack.callBack(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue());
                        return null;
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iResultCodeCallBack.callBack(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue());
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean requestGeoDecoder(double d, double d2, final IRequestGeoDecoderCallBack iRequestGeoDecoderCallBack) {
        Log.i(TAG, "requestGeoDecoder: ");
        if (this.requests != null) {
            GeoPosition geoPosition = new GeoPosition();
            geoPosition.setLatitude(Double.valueOf(d));
            geoPosition.setLongitude(Double.valueOf(d2));
            this.requests.requestRequestGeoDecoder(new ReqRequestGeoDecoder(geoPosition)).thenAccept((Consumer<? super ResRequestGeoDecoder>) new Consumer<ResRequestGeoDecoder>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.95
                @Override // java9.util.function.Consumer
                public void accept(ResRequestGeoDecoder resRequestGeoDecoder) {
                    if (iRequestGeoDecoderCallBack == null) {
                        Log.i(NavigationApiOctaImpl.TAG, "callback pointer is null.");
                        return;
                    }
                    if (resRequestGeoDecoder.getMessageType() == EMessageType.Result) {
                        try {
                            GeoAddressInfo addressInfo = resRequestGeoDecoder.getArguments().getAddressInfo();
                            iRequestGeoDecoderCallBack.requestGeoDecoderResult(ResultCode.SUCCESS.getValue().intValue(), addressInfo.getAddress(), addressInfo.getFormatedAddress(), addressInfo.getCity(), addressInfo.getCityCode(), addressInfo.getProvince(), addressInfo.getCountry(), addressInfo.getCountryCode(), addressInfo.getAdCode(), addressInfo.getAdName(), addressInfo.getLatLng().getLatitude().doubleValue(), addressInfo.getLatLng().getLongitude().doubleValue());
                            return;
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    try {
                        iRequestGeoDecoderCallBack.requestGeoDecoderResult(resRequestGeoDecoder.getCustomError().getValue().intValue(), "", "", "", "", "", "", "", "", "", 0.0d, 0.0d);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                    throw new RuntimeException("received error response");
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.94
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iRequestGeoDecoderCallBack.requestGeoDecoderResult(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue(), "", "", "", "", "", "", "", "", "", 0.0d, 0.0d);
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iRequestGeoDecoderCallBack.requestGeoDecoderResult(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue(), "", "", "", "", "", "", "", "", "", 0.0d, 0.0d);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean getMapLevelInfo(final IGetMapLevelInfoCallBack iGetMapLevelInfoCallBack) {
        Log.i(TAG, "getMapLevelInfo");
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestGetScaleStatus(new ReqGetScaleStatus()).thenAccept((Consumer<? super ResGetScaleStatus>) new Consumer<ResGetScaleStatus>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.97
                @Override // java9.util.function.Consumer
                public void accept(ResGetScaleStatus resGetScaleStatus) {
                    if (iGetMapLevelInfoCallBack != null) {
                        if (resGetScaleStatus.getMessageType() == EMessageType.Result) {
                            try {
                                iGetMapLevelInfoCallBack.getMapLevelInfoResult(ResultCode.SUCCESS.getValue().intValue(), resGetScaleStatus.getArguments().getMaxScale().intValue(), resGetScaleStatus.getArguments().getMinScale().intValue(), resGetScaleStatus.getArguments().getCurScale().intValue());
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        try {
                            iGetMapLevelInfoCallBack.getMapLevelInfoResult(resGetScaleStatus.getCustomError().getValue().intValue(), -1, -1, -1);
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.96
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iGetMapLevelInfoCallBack.getMapLevelInfoResult(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue(), -1, -1, -1);
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iGetMapLevelInfoCallBack.getMapLevelInfoResult(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue(), -1, -1, -1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean removeWayPoint(int i, int i2, final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "removeWayPoint:action" + i + "index" + i2);
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestRemoveWaypoint(new ReqRemoveWaypoint(RequestRoutePlanOrNaviType.getByValue(Integer.valueOf(i)), Integer.valueOf(i2))).thenAccept((Consumer<? super ResRemoveWaypoint>) new Consumer<ResRemoveWaypoint>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.99
                @Override // java9.util.function.Consumer
                public void accept(ResRemoveWaypoint resRemoveWaypoint) {
                    if (iResultCodeCallBack != null) {
                        if (resRemoveWaypoint.getMessageType() == EMessageType.Result) {
                            try {
                                iResultCodeCallBack.callBack(ResultCode.SUCCESS.getValue().intValue());
                                return;
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        try {
                            iResultCodeCallBack.callBack(resRemoveWaypoint.getCustomError().getValue().intValue());
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.98
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iResultCodeCallBack.callBack(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue());
                        return null;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            return true;
        }
        try {
            iResultCodeCallBack.callBack(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue());
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean getCloudRegistrationStatus(final IGetCloudRegistrationStatusCallBack iGetCloudRegistrationStatusCallBack) {
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requestsCommon;
        if (commonSysApiInverseRequests == null) {
            return false;
        }
        commonSysApiInverseRequests.requestGetResponseExpiredDate(new ReqGetResponseExpiredDate()).thenAccept((Consumer<? super ResGetResponseExpiredDate>) new Consumer<ResGetResponseExpiredDate>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.100
            @Override // java9.util.function.Consumer
            public void accept(ResGetResponseExpiredDate resGetResponseExpiredDate) {
                if (resGetResponseExpiredDate.getResponseExpiredDate().getActivateStatus() == ActivateStatus.E_SUCCESS) {
                    try {
                        iGetCloudRegistrationStatusCallBack.getCloudRegistrationStatusResult(0, true);
                        return;
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                try {
                    iGetCloudRegistrationStatusCallBack.getCloudRegistrationStatusResult(0, false);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        });
        return false;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean resumeGuidance(int i, int i2, final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "resumeGuidance: actionType:" + i);
        if (i == 0) {
            try {
                backToMap(null);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests != null) {
            ecarxNavApiRequests.requestResumeGuidance(new ReqResumeGuidance(ResumeGuidanceAction.getByValue(Integer.valueOf(i2)))).thenAccept((Consumer<? super ResResumeGuidance>) new Consumer<ResResumeGuidance>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.102
                @Override // java9.util.function.Consumer
                public void accept(ResResumeGuidance resResumeGuidance) {
                    if (iResultCodeCallBack != null) {
                        if (resResumeGuidance.getMessageType() == EMessageType.Result) {
                            try {
                                iResultCodeCallBack.callBack(ResultCode.SUCCESS.getValue().intValue());
                                return;
                            } catch (RemoteException e2) {
                                e2.printStackTrace();
                                return;
                            }
                        }
                        try {
                            iResultCodeCallBack.callBack(resResumeGuidance.getCustomError().getValue().intValue());
                        } catch (RemoteException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }).exceptionally((Function<Throwable, ? extends Void>) new Function<Throwable, Void>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.101
                @Override // java9.util.function.Function
                public Void apply(Throwable th) {
                    try {
                        iResultCodeCallBack.callBack(ResultCode.FAILURE_REQUEST_FAILURE.getValue().intValue());
                        return null;
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                        return null;
                    }
                }
            });
            return false;
        }
        if (i != 0) {
            try {
                iResultCodeCallBack.callBack(ResultCode.FAILURE_NAVIGATION_NOT_START.getValue().intValue());
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        } else if (this.hasUnsentMSG) {
            try {
                iResultCodeCallBack.callBack(ResultCode.FAILURE_CURRENT_MAP_NOT_SUPPORT.getValue().intValue());
            } catch (RemoteException e3) {
                e3.printStackTrace();
            }
        } else {
            this.hasUnsentMSG = true;
            this.unsentMsg = UnsentMethod.ResumeGuidance;
            unSentResumeGuidance(i, i2, iResultCodeCallBack);
        }
        return true;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean requestlRecommendedGasStations(int i, final IResultGasStationCallBack iResultGasStationCallBack) {
        Log.i(TAG, "requestlRecommendedGasStations  token :" + i);
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests == null) {
            return false;
        }
        ecarxNavApiRequests.requestSearchBySETELCategory(new ReqSearchBySETELCategory(Integer.valueOf(i))).thenAccept((Consumer<? super ResSearchBySETELCategory>) new Consumer<ResSearchBySETELCategory>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.103
            @Override // java9.util.function.Consumer
            public void accept(ResSearchBySETELCategory resSearchBySETELCategory) {
                RecommendedGasStationsData recommendedGasStationsData = new RecommendedGasStationsData();
                if (iResultGasStationCallBack == null) {
                    try {
                        Log.i(NavigationApiOctaImpl.TAG, "callBack is null");
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                if (resSearchBySETELCategory.getMessageType() == EMessageType.Result) {
                    Log.i(NavigationApiOctaImpl.TAG, "resSearchBySETELCategory get result.");
                    try {
                        List<ExtPoiInfo> extPoiInfoList = resSearchBySETELCategory.getArguments().getSETELDataResult().getExtPoiInfoList();
                        new ExtPoiInfo();
                        GasStationsInfo[] gasStationsInfoArr = new GasStationsInfo[extPoiInfoList.size()];
                        recommendedGasStationsData.setCode(resSearchBySETELCategory.getArguments().getSETELDataResult().getCode().intValue());
                        recommendedGasStationsData.setToken(resSearchBySETELCategory.getArguments().getSETELDataResult().getReqId().intValue());
                        recommendedGasStationsData.setMsg(resSearchBySETELCategory.getArguments().getSETELDataResult().getMessage());
                        for (int i2 = 0; i2 < extPoiInfoList.size(); i2++) {
                            ExtPoiInfo extPoiInfo = extPoiInfoList.get(i2);
                            GasStationsInfo gasStationsInfo = new GasStationsInfo();
                            gasStationsInfo.setAddress(extPoiInfo.getAddress());
                            gasStationsInfo.setAvailablePumps(extPoiInfo.getAvailableNumOfOilGun().intValue());
                            gasStationsInfo.setDistance(extPoiInfo.getDistance().intValue());
                            gasStationsInfo.setId(extPoiInfo.getPoiId());
                            gasStationsInfo.setLatitude(extPoiInfo.getLatLng().getLatitude().floatValue());
                            gasStationsInfo.setLongitude(extPoiInfo.getLatLng().getLongitude().floatValue());
                            gasStationsInfo.setName(extPoiInfo.getName());
                            List<OpeningHours> openingHours = extPoiInfo.getOpeningHours();
                            OpeningHour[] openingHourArr = new OpeningHour[openingHours.size()];
                            for (int i3 = 0; i3 < openingHours.size(); i3++) {
                                openingHourArr[i3].setDay(openingHours.get(i3).getDay().intValue());
                                List<TimePeriod> timePeriodList = openingHours.get(i3).getTimePeriodList();
                                TimeSlot[] timeSlotArr = new TimeSlot[openingHours.get(i3).getTimePeriodList().size()];
                                for (int i4 = 0; i4 < openingHours.get(i3).getTimePeriodList().size(); i4++) {
                                    timeSlotArr[i4].setFrom(timePeriodList.get(i4).getOpeningTime().intValue());
                                    timeSlotArr[i4].setTo(timePeriodList.get(i4).getClosingTime().intValue());
                                }
                                openingHourArr[i3].setTimeSlots(timeSlotArr);
                            }
                            gasStationsInfo.setOperatingHours(openingHourArr);
                            gasStationsInfo.setStatus(extPoiInfo.getStatus());
                            gasStationsInfo.setTypeCode(extPoiInfo.getType().getTypeCode());
                            gasStationsInfo.setTypeName(extPoiInfo.getType().getTypeName());
                            gasStationsInfo.setDataType(extPoiInfo.getDataType().getValue().intValue());
                            gasStationsInfoArr[i2] = gasStationsInfo;
                        }
                        recommendedGasStationsData.setResultList(gasStationsInfoArr);
                        Log.i(NavigationApiOctaImpl.TAG, "recommendedGasStationsData result:" + recommendedGasStationsData.toString());
                        iResultGasStationCallBack.callBack(ResultCode.SUCCESS.getValue().intValue(), recommendedGasStationsData);
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                Log.i(NavigationApiOctaImpl.TAG, "recommendedGasStationsData result error");
            }
        });
        return false;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean vrMsgCommand(int i, int i2, final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "vrMsgCommand:functype" + i + ":command" + i2);
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests == null) {
            return false;
        }
        ecarxNavApiRequests.requestVRMsgCommand(new ReqVRMsgCommand(new VRCommand(FunctionType.getByValue(Integer.valueOf(i)), Integer.valueOf(i2)))).thenAccept((Consumer<? super ResVRMsgCommand>) new Consumer<ResVRMsgCommand>() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.104
            @Override // java9.util.function.Consumer
            public void accept(ResVRMsgCommand resVRMsgCommand) {
                if (iResultCodeCallBack != null) {
                    if (resVRMsgCommand.getMessageType() == EMessageType.Result) {
                        try {
                            iResultCodeCallBack.callBack(ResultCode.SUCCESS.getValue().intValue());
                            return;
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                    try {
                        iResultCodeCallBack.callBack(resVRMsgCommand.getCustomError().getValue().intValue());
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
        return false;
    }

    @Override // com.neusoft.na.ocservice.ecarxnavsdk.INavigationApi
    public boolean setLoggedInState(boolean z, String str, IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "setLoggedInState:state" + z);
        EcarxNavApiRequests ecarxNavApiRequests = this.requests;
        if (ecarxNavApiRequests == null) {
            return false;
        }
        ecarxNavApiRequests.fireHasLogin(new ReqHasLogin(Boolean.valueOf(z), str));
        try {
            iResultCodeCallBack.callBack(ResultCode.SUCCESS.getValue().intValue());
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void unSentResumeGuidance(final int i, final int i2, final IResultCodeCallBack iResultCodeCallBack) {
        Log.i(TAG, "unSentResumeGuidance: ");
        new Thread(new Runnable() { // from class: com.neusoft.na.ocservice.ecarxnavsdk.impl.NavigationApiOctaImpl.105
            @Override // java.lang.Runnable
            public void run() {
                Log.i(NavigationApiOctaImpl.TAG, "unSentResumeGuidance:lock ");
                while (NavigationApiOctaImpl.this.hasUnsentMSG) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
                NavigationApiOctaImpl.this.resumeGuidance(i, i2, iResultCodeCallBack);
                Log.i(NavigationApiOctaImpl.TAG, "unSentResumeGuidance:unlock ");
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public GeoPos octaGeoPos2AidlGeoPos(GeoPosition geoPosition) {
        GeoPos geoPos = new GeoPos();
        geoPos.setLatitude(geoPosition.getLatitude().doubleValue());
        geoPos.setLongitude(geoPosition.getLongitude().doubleValue());
        return geoPos;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.neusoft.na.ocservice.RouteInfo octaRouteInfo2AidlRouteInfo(RouteInfo routeInfo) {
        com.neusoft.na.ocservice.RouteInfo routeInfo2 = new com.neusoft.na.ocservice.RouteInfo();
        routeInfo2.setRouteTag(routeInfo.getRouteTag());
        routeInfo2.setStrategy(routeInfo.getStrategy().intValue());
        routeInfo2.setTime(routeInfo.getTime().doubleValue());
        routeInfo2.setDistance(routeInfo.getDistance().doubleValue());
        routeInfo2.setTimeAuto(routeInfo.getTimeAuto());
        routeInfo2.setDistanceAuto(routeInfo.getDistanceAutoM());
        routeInfo2.setTrafficLights(routeInfo.getTrafficLights().intValue());
        routeInfo2.setTolls(routeInfo.getTolls().intValue());
        if (routeInfo.getTrafficInfos().size() > 0) {
            for (int i = 0; i < routeInfo.getTrafficInfos().size(); i++) {
                routeInfo2.getTrafficInfos().add(octaTrafficInfo2AidlTrafficInfo(routeInfo.getTrafficInfos().get(i)));
            }
        }
        return routeInfo2;
    }

    private RouteInfo aidlRouteInfo2OctaRouteInfo(com.neusoft.na.ocservice.RouteInfo routeInfo) {
        RouteInfo routeInfo2 = new RouteInfo();
        routeInfo2.setRouteTag(routeInfo.getRouteTag());
        routeInfo2.setStrategy(Integer.valueOf(routeInfo.getStrategy()));
        routeInfo2.setTime(Double.valueOf(routeInfo.getTime()));
        routeInfo2.setDistance(Double.valueOf(routeInfo.getDistance()));
        routeInfo2.setTimeAuto(routeInfo.getTimeAuto());
        routeInfo2.setDistanceAutoM(routeInfo.getDistanceAuto());
        routeInfo2.setTrafficLights(Integer.valueOf(routeInfo.getTrafficLights()));
        routeInfo2.setTolls(Integer.valueOf(routeInfo.getTolls()));
        if (routeInfo.getTrafficInfos().size() > 0) {
            for (int i = 0; i < routeInfo.getTrafficInfos().size(); i++) {
                routeInfo2.getTrafficInfos().add(aidlTrafficInfo2OctaTrafficInfo(routeInfo.getTrafficInfos().get(i)));
            }
        }
        return routeInfo2;
    }

    private TrafficInfo octaTrafficInfo2AidlTrafficInfo(api.structs.com.neusoft.ecarx.TrafficInfo trafficInfo) {
        TrafficInfo trafficInfo2 = new TrafficInfo();
        trafficInfo2.setSegIndex(trafficInfo.getSegIndex().intValue());
        trafficInfo2.setTmcStatus(trafficInfo.getTmcStatus().getValue().intValue());
        trafficInfo2.setPercent(trafficInfo.getPercent().doubleValue());
        return trafficInfo2;
    }

    private api.structs.com.neusoft.ecarx.TrafficInfo aidlTrafficInfo2OctaTrafficInfo(TrafficInfo trafficInfo) {
        api.structs.com.neusoft.ecarx.TrafficInfo trafficInfo2 = new api.structs.com.neusoft.ecarx.TrafficInfo();
        trafficInfo2.setSegIndex(Integer.valueOf(trafficInfo.getSegIndex()));
        trafficInfo2.setTmcStatus(RoadTrafficStatus.getByValue(Integer.valueOf(trafficInfo.getTmcStatus())));
        trafficInfo2.setPercent(Double.valueOf(trafficInfo.getPercent()));
        return trafficInfo2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.neusoft.na.ocservice.PoiInfo octaPOI2AidlPOI(PoiInfo poiInfo) {
        com.neusoft.na.ocservice.PoiInfo poiInfo2 = new com.neusoft.na.ocservice.PoiInfo();
        poiInfo2.setName(poiInfo.getName());
        poiInfo2.setUid(poiInfo.getUid());
        poiInfo2.setDistance(poiInfo.getDistance().intValue());
        poiInfo2.setAddress(poiInfo.getAddress());
        PoiType poiType = new PoiType();
        poiType.setTypeCode(poiInfo.getType().getTypeCode());
        poiType.setTypeName(poiInfo.getType().getTypeName());
        poiInfo2.setType(poiType);
        com.neusoft.na.ocservice.LatLng latLng = new com.neusoft.na.ocservice.LatLng();
        latLng.setCoordType(poiInfo.getLatLng().getCoordType());
        latLng.setLatitude(poiInfo.getLatLng().getLatitude());
        latLng.setLongitude(poiInfo.getLatLng().getLongitude());
        poiInfo2.setLatLng(latLng);
        poiInfo2.setDataType(poiInfo.getDataType().getValue().intValue());
        return poiInfo2;
    }

    PoiInfo aidlPOI2octaPOI(com.neusoft.na.ocservice.PoiInfo poiInfo) {
        PoiInfo poiInfo2 = new PoiInfo();
        poiInfo2.setName(poiInfo.getName());
        poiInfo2.setUid(poiInfo.getUid());
        poiInfo2.setDistance(Integer.valueOf(poiInfo.getDistance()));
        poiInfo2.setAddress(poiInfo.getAddress());
        api.structs.com.neusoft.ecarx.PoiType poiType = new api.structs.com.neusoft.ecarx.PoiType();
        poiType.setTypeCode(poiInfo.getType().getTypeCode());
        poiType.setTypeName(poiInfo.getType().getTypeName());
        poiInfo2.setType(poiType);
        LatLng latLng = new LatLng();
        latLng.setCoordType(poiInfo.getLatLng().getCoordType());
        latLng.setLatitude(poiInfo.getLatLng().getLatitude());
        latLng.setLongitude(poiInfo.getLatLng().getLongitude());
        poiInfo2.setLatLng(latLng);
        poiInfo2.setDataType(DataType.getByValue(Integer.valueOf(poiInfo.getDataType())));
        return poiInfo2;
    }

    com.neusoft.na.ocservice.SuggestionCity octaSuggestionCity2Aidl(SuggestionCity suggestionCity) {
        com.neusoft.na.ocservice.SuggestionCity suggestionCity2 = new com.neusoft.na.ocservice.SuggestionCity();
        suggestionCity2.setSuggestionNum(suggestionCity.getSuggestionNum().intValue());
        suggestionCity2.setCityName(suggestionCity.getCityName());
        suggestionCity2.setCityCode(suggestionCity.getCityCode());
        return suggestionCity2;
    }
}
