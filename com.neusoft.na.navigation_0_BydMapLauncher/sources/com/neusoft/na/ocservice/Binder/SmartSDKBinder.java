package com.neusoft.na.ocservice.Binder;

import android.car.Car;
import android.car.user.CarUserManager;
import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.view.Surface;
import androidx.core.app.NotificationCompat;
import api.structs.com.neusoft.smart.DayNightStatus;
import api.structs.com.neusoft.smart.FavoritePoiInfo;
import api.structs.com.neusoft.smart.GuidanceInfo;
import api.structs.com.neusoft.smart.HighwayExitInfo;
import api.structs.com.neusoft.smart.LaneInfo;
import api.structs.com.neusoft.smart.MapStatus;
import api.structs.com.neusoft.smart.MuteStatus;
import api.structs.com.neusoft.smart.NaviStatus;
import api.structs.com.neusoft.smart.PoiInfo;
import api.structs.com.neusoft.smart.RoadTrafficStatus;
import api.structs.com.neusoft.smart.RouteInfo;
import api.structs.com.neusoft.smart.RouteUpdateInfo;
import api.structs.com.neusoft.smart.RouteViewStatus;
import api.structs.com.neusoft.smart.ServiceStatus;
import api.structs.com.neusoft.smart.TrafficInfo;
import api.structs.com.neusoft.smart.TrafficStatus;
import api.structs.com.neusoft.smart.ViewModeStatus;
import api.structs.com.neusoft.smart.ZoomStatus;
import com.neusoft.na.ocservice.DRPosition;
import com.neusoft.na.ocservice.EVChargingStation;
import com.neusoft.na.ocservice.FavoriteItem;
import com.neusoft.na.ocservice.GeoPos;
import com.neusoft.na.ocservice.IAddItemToFavoritesCallBack;
import com.neusoft.na.ocservice.IAlongTheWaySearchCallBack;
import com.neusoft.na.ocservice.IBooleanCallback;
import com.neusoft.na.ocservice.IDetailInfoCallback;
import com.neusoft.na.ocservice.IEVChargingStationCallback;
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
import com.neusoft.na.ocservice.ISmartNaviInterface;
import com.neusoft.na.ocservice.ISmartNaviNotificationInterface;
import com.neusoft.na.ocservice.ISpecialPoiNaviCallback;
import com.neusoft.na.ocservice.LatLng;
import com.neusoft.na.ocservice.OneCoreLifeCycleController;
import com.neusoft.na.ocservice.OneCoreService;
import com.neusoft.na.ocservice.PoiType;
import com.neusoft.na.ocservice.SetelData;
import com.neusoft.na.ocservice.smartnavisdk.Constant;
import com.neusoft.na.ocservice.smartnavisdk.INavigationApi;
import com.neusoft.na.ocservice.smartnavisdk.INotificationListener;
import com.neusoft.na.ocservice.smartnavisdk.Navigation;
import com.neusoft.na.ocservice.smartnavisdk.SmartSDKService;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SmartSDKBinder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000¶\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 ¥\u00012\u00020\u0001:\u0002¥\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JJ\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\nH\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%J\u000e\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)J\"\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\n2\u0006\u0010-\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\u0012\u00100\u001a\u00020+2\b\u0010.\u001a\u0004\u0018\u000101H\u0016J\u0012\u00102\u001a\u00020+2\b\u0010.\u001a\u0004\u0018\u000101H\u0016JH\u00103\u001a\u00020+2\u0006\u00104\u001a\u00020\n2\b\u00105\u001a\u0004\u0018\u00010\u00062\b\u00106\u001a\u0004\u0018\u00010\u00062\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u0002082\b\u0010:\u001a\u0004\u0018\u00010\u00062\b\u0010.\u001a\u0004\u0018\u000101H\u0016J\u0012\u0010;\u001a\u00020+2\b\u0010.\u001a\u0004\u0018\u000101H\u0016J\u0012\u0010<\u001a\u00020+2\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J$\u0010?\u001a\u00020\u00142\b\u0010@\u001a\u0004\u0018\u00010!2\u0006\u0010A\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u00010BH\u0016J$\u0010C\u001a\u00020\u00142\b\u0010@\u001a\u0004\u0018\u00010D2\u0006\u0010A\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u00010BH\u0016J\u001a\u0010E\u001a\u00020+2\u0006\u0010F\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u00010GH\u0016J\u001a\u0010H\u001a\u00020+2\u0006\u0010I\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u00010JH\u0016J\u0012\u0010K\u001a\u00020+2\b\u0010.\u001a\u0004\u0018\u00010LH\u0016J\u001a\u0010M\u001a\u00020+2\u0006\u0010N\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u00010OH\u0016J\u001a\u0010P\u001a\u00020+2\u0006\u0010Q\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u00010RH\u0016J\u0012\u0010S\u001a\u00020+2\b\u0010=\u001a\u0004\u0018\u00010TH\u0016J\u0012\u0010U\u001a\u00020+2\b\u0010.\u001a\u0004\u0018\u00010VH\u0016J\u0012\u0010W\u001a\u00020+2\b\u0010.\u001a\u0004\u0018\u00010XH\u0016J\u001c\u0010Y\u001a\u00020+2\b\u0010Z\u001a\u0004\u0018\u00010\u00062\b\u0010.\u001a\u0004\u0018\u00010[H\u0016J\u001a\u0010\\\u001a\u00020+2\u0006\u0010F\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u000101H\u0016J\u0012\u0010]\u001a\u00020+2\b\u0010.\u001a\u0004\u0018\u000101H\u0016J\u001a\u0010^\u001a\u00020+2\u0006\u0010_\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u000101H\u0016J$\u0010`\u001a\u00020+2\u0006\u0010,\u001a\u00020\n2\b\u0010a\u001a\u0004\u0018\u00010!2\b\u0010.\u001a\u0004\u0018\u000101H\u0016J\u001a\u0010b\u001a\u00020+2\u0006\u0010c\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u000101H\u0016J\u000e\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020gJ\u000e\u0010h\u001a\u00020!2\u0006\u0010 \u001a\u00020\u001fJ\u000e\u0010i\u001a\u00020%2\u0006\u0010$\u001a\u00020#J\u000e\u0010j\u001a\u00020)2\u0006\u0010(\u001a\u00020'J\u0012\u0010k\u001a\u00020+2\b\u0010.\u001a\u0004\u0018\u000101H\u0016J\"\u0010l\u001a\u00020+2\u0006\u0010,\u001a\u00020\n2\u0006\u0010m\u001a\u00020\n2\b\u0010=\u001a\u0004\u0018\u000101H\u0016J\u001a\u0010n\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\nH\u0016J\"\u0010o\u001a\u00020+2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u0002082\b\u0010.\u001a\u0004\u0018\u00010pH\u0016J\u001a\u0010q\u001a\u00020\u00142\u0006\u0010r\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u000101H\u0016J<\u0010s\u001a\u00020+2\u0006\u0010,\u001a\u00020\n2\u0006\u0010t\u001a\u00020\n2\b\u0010u\u001a\u0004\u0018\u00010!2\u000e\u0010v\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010w2\b\u0010.\u001a\u0004\u0018\u00010xH\u0016J\u001a\u0010y\u001a\u00020+2\u0006\u0010z\u001a\u00020\n2\b\u0010=\u001a\u0004\u0018\u00010{H\u0016J\"\u0010|\u001a\u00020+2\u0006\u0010,\u001a\u00020\n2\u0006\u0010}\u001a\u00020\n2\b\u0010=\u001a\u0004\u0018\u000101H\u0016JF\u0010~\u001a\u00020+2\u0006\u0010,\u001a\u00020\n2\u0006\u0010-\u001a\u00020\n2\b\u0010\u007f\u001a\u0004\u0018\u00010\u00062\u0007\u0010\u0080\u0001\u001a\u00020\n2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u0002082\t\u0010.\u001a\u0005\u0018\u00010\u0081\u0001H\u0016J:\u0010\u0082\u0001\u001a\u00020+2\u0006\u0010,\u001a\u00020\n2\u0006\u0010-\u001a\u00020\n2\b\u0010\u007f\u001a\u0004\u0018\u00010\u00062\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u00062\n\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u0001H\u0016J=\u0010\u0086\u0001\u001a\u00020\u00142\u0007\u0010\u0087\u0001\u001a\u00020\n2\t\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u00062\u0007\u0010\u0080\u0001\u001a\u00020\n2\n\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u008a\u00012\t\u0010.\u001a\u0005\u0018\u00010\u008b\u0001H\u0016J\u001c\u0010\u008c\u0001\u001a\u00020+2\u0007\u0010\u008d\u0001\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u000101H\u0016J\u0014\u0010\u008e\u0001\u001a\u00020+2\t\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u0011H\u0016J'\u0010\u0090\u0001\u001a\u00020+2\u0007\u0010\u0091\u0001\u001a\u00020+2\t\u0010\u0092\u0001\u001a\u0004\u0018\u00010\u00062\b\u0010=\u001a\u0004\u0018\u000101H\u0016J\u001c\u0010\u0093\u0001\u001a\u00020+2\u0007\u0010\u0094\u0001\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u000101H\u0016J\u001c\u0010\u0095\u0001\u001a\u00020\u00142\u0007\u0010\u0096\u0001\u001a\u00020+2\b\u0010.\u001a\u0004\u0018\u000101H\u0016J\u001c\u0010\u0097\u0001\u001a\u00020+2\u0007\u0010\u0098\u0001\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u000101H\u0016J\u001b\u0010\u0099\u0001\u001a\u00020+2\u0006\u0010t\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u000101H\u0016J\u001c\u0010\u009a\u0001\u001a\u00020+2\u0006\u0010N\u001a\u00020\n2\t\u0010.\u001a\u0005\u0018\u00010\u009b\u0001H\u0016J%\u0010\u009c\u0001\u001a\u00020+2\u0006\u0010,\u001a\u00020\n2\u0007\u0010\u009d\u0001\u001a\u00020\n2\t\u0010.\u001a\u0005\u0018\u00010\u009e\u0001H\u0016J\u001c\u0010\u009f\u0001\u001a\u00020+2\u0007\u0010 \u0001\u001a\u00020\n2\b\u0010.\u001a\u0004\u0018\u000101H\u0016J\u0014\u0010¡\u0001\u001a\u00020+2\t\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u0011H\u0016J%\u0010¢\u0001\u001a\u00020+2\u0007\u0010£\u0001\u001a\u00020\n2\u0007\u0010¤\u0001\u001a\u00020\n2\b\u0010=\u001a\u0004\u0018\u000101H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006¦\u0001"}, d2 = {"Lcom/neusoft/na/ocservice/Binder/SmartSDKBinder;", "Lcom/neusoft/na/ocservice/ISmartNaviInterface$Stub;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", "isConnect", "", "mMotsLifeCycleController", "Lcom/neusoft/na/ocservice/OneCoreLifeCycleController;", NotificationCompat.CATEGORY_NAVIGATION, "Lcom/neusoft/na/ocservice/smartnavisdk/INavigationApi;", "resultlist", "Ljava/util/ArrayList;", "Lcom/neusoft/na/ocservice/ISmartNaviNotificationInterface;", "Lkotlin/collections/ArrayList;", "addSurface", "", OneCoreService.PAYLOAD_SURFACE, "Landroid/view/Surface;", "type", "height", "width", "roadViewHeight", "roadViewWidth", "offsetX", "offsetY", "aidlPOI2octaPOI", "Lapi/structs/com/neusoft/smart/PoiInfo;", "poi", "Lcom/neusoft/na/ocservice/PoiInfo;", "aidlRouteInfo2OctaRouteInfo", "Lapi/structs/com/neusoft/smart/RouteInfo;", "route", "Lcom/neusoft/na/ocservice/RouteInfo;", "aidlTrafficInfo2OctaTrafficInfo", "Lapi/structs/com/neusoft/smart/TrafficInfo;", "trafficinfo", "Lcom/neusoft/na/ocservice/TrafficInfo;", "alongTheWaySearch", "", CarUserManager.BUNDLE_PARAM_ACTION, "searchType", "callback", "Lcom/neusoft/na/ocservice/IAlongTheWaySearchCallBack;", "backToMap", "Lcom/neusoft/na/ocservice/IResultCodeCallBack;", "cancelNavi", "editFavoritePois", "favoriteType", "poiName", "address", "latitude", "", "longitude", "coordType", "exitMap", "getCloudRegistrationStatus", "callBack", "Lcom/neusoft/na/ocservice/IGetCloudRegistrationStatusCallBack;", "getDetailInfo", Car.INFO_SERVICE, "SearchType", "Lcom/neusoft/na/ocservice/IDetailInfoCallback;", "getEVDetailInfo", "Lcom/neusoft/na/ocservice/EVChargingStation;", "getFavoritePois", "favType", "Lcom/neusoft/na/ocservice/IGetFavoritePoisCallBack;", "getFrontTraffic", "frontDistance", "Lcom/neusoft/na/ocservice/IGetFrontTrafficCallBack;", "getGuideInfo", "Lcom/neusoft/na/ocservice/IGetGuideInfoCallBack;", "getHighwayExitInfo", "actionType", "Lcom/neusoft/na/ocservice/IGetHighwayExitInfo;", "getHistoryPois", "maxCount", "Lcom/neusoft/na/ocservice/IGetHistoryPoisCallBack;", "getMapLevelInfo", "Lcom/neusoft/na/ocservice/IGetMapLevelInfoCallBack;", "getNaviMuteMode", "Lcom/neusoft/na/ocservice/IGetNaviMuteModeCallBack;", "getRouteInfo", "Lcom/neusoft/na/ocservice/IGetRouteInfoCallBack;", "getTrafficSummaryInfo", "trafficText", "Lcom/neusoft/na/ocservice/IGetTrafficSummaryInfocCallBack;", "goFavorite", "launchMap", "mapZoomInOut", "zoomInOutType", "modifyNaviVia", "viaPoi", "naviFullView", "operationType", "octaLaneInfo2AidlLaneInfo", "Lcom/neusoft/na/ocservice/LaneInfo;", "laneinfo", "Lapi/structs/com/neusoft/smart/LaneInfo;", "octaPOI2AidlPOI", "octaRouteInfo2AidlRouteInfo", "octaTrafficInfo2AidlTrafficInfo", "openDestinationSearch", "removeWayPoint", "index", "removedSurface", "requestGeoDecoder", "Lcom/neusoft/na/ocservice/IRequestGeoDecoderCallBack;", "requestJumpToViewPage", "viewPageType", "requestRoutePlanOrNavi", "strategy", "destPoiInfo", "viaPoiInfos", "", "Lcom/neusoft/na/ocservice/IRequestRoutePlanOrNavi;", "requestlRecommendedGasStations", "token", "Lcom/neusoft/na/ocservice/IResultGasStationCallBack;", "resumeGuidance", "resumeGuidanceAction", "searchAround", "keywords", "searchRadius", "Lcom/neusoft/na/ocservice/ISearchAroundCallBack;", "searchByKeyword", "searchCity", "callbak", "Lcom/neusoft/na/ocservice/ISearchByKeywordCallBack;", "searchEVChargingStation", "searchEVType", "keyWord", "geoPosition", "Lcom/neusoft/na/ocservice/LatLng;", "Lcom/neusoft/na/ocservice/IEVChargingStationCallback;", "selectRoute", "selectIndex", "setListener", "notification", "setLoggedInState", "hasLoggedIn", "protonID", "setMapViewMode", "viewMode", "setMuteStatus", "isMute", "setNaviMuteMode", "muteState", "setRoutePrefer", "showMyLocation", "Lcom/neusoft/na/ocservice/IShowMyLocationCallBack;", "specialPoiNavi", "poiType", "Lcom/neusoft/na/ocservice/ISpecialPoiNaviCallback;", "switchTraffic", "trafficType", "unSetListener", "vrMsgCommand", "functype", "command", "Companion", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SmartSDKBinder extends ISmartNaviInterface.Stub {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static SmartSDKBinder instance;
    private final String TAG;
    private final Context context;
    private int isConnect;
    private OneCoreLifeCycleController mMotsLifeCycleController;
    private INavigationApi navigation;
    private ArrayList<ISmartNaviNotificationInterface> resultlist;

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public void removedSurface(Surface surface, int type) {
    }

    public SmartSDKBinder(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.TAG = "XJSmartSDKBinder";
        this.mMotsLifeCycleController = new OneCoreLifeCycleController(context, SmartSDKService.class);
        this.resultlist = new ArrayList<>();
        this.isConnect = Constant.SERVICE_STATUS_CHANGED_DISCONNECTD;
        Log.i("XJSmartSDKBinder", "SmartSDKBinder init()");
        INavigationApi iNavigationApiCreate = Navigation.create(context.getApplicationContext(), new INotificationListener() { // from class: com.neusoft.na.ocservice.Binder.SmartSDKBinder.1
            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public synchronized void trafficCondition(int totalDistance, int remainDistance, List<TrafficInfo> trafficInfo) {
                int size;
                Log.i(SmartSDKBinder.this.getTAG(), "trafficCondition");
                if (trafficInfo != null) {
                    try {
                        ArrayList arrayList = new ArrayList();
                        if (trafficInfo.size() > 0 && trafficInfo.size() - 1 >= 0) {
                            int i = 0;
                            while (true) {
                                arrayList.add(SmartSDKBinder.this.octaTrafficInfo2AidlTrafficInfo(trafficInfo.get(i)));
                                if (i == size) {
                                    break;
                                } else {
                                    i++;
                                }
                            }
                        }
                        int size2 = SmartSDKBinder.this.resultlist.size();
                        for (int i2 = 0; i2 < size2; i2++) {
                            try {
                                ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i2)).trafficCondition(totalDistance, remainDistance, arrayList);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                SmartSDKBinder.this.resultlist.remove(i2);
                                return;
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } else {
                    Log.i(SmartSDKBinder.this.getTAG(), "trafficCondition::trafficInfo =null");
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void requestExpirationDate(IExpirationDateCallBack expirationDate) {
                Log.i(SmartSDKBinder.this.getTAG(), "requestExpirationDate");
                if (expirationDate != null) {
                    int size = SmartSDKBinder.this.resultlist.size();
                    for (int i = 0; i < size; i++) {
                        try {
                            ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).requestExpirationDate(expirationDate);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            SmartSDKBinder.this.resultlist.remove(i);
                            return;
                        }
                    }
                    return;
                }
                Log.i(SmartSDKBinder.this.getTAG(), "requestExpirationDate");
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public synchronized void serviceStatusChanged(ServiceStatus status) {
                if (status != null) {
                    SmartSDKBinder smartSDKBinder = SmartSDKBinder.this;
                    Integer value = status.getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "status.value");
                    smartSDKBinder.isConnect = value.intValue();
                    Log.i(SmartSDKBinder.this.getTAG(), "serviceStatusChanged" + SmartSDKBinder.this.isConnect);
                    int size = SmartSDKBinder.this.resultlist.size();
                    for (int i = 0; i < size; i++) {
                        try {
                            ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).serviceStatusChanged(SmartSDKBinder.this.isConnect);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            SmartSDKBinder.this.resultlist.remove(i);
                            return;
                        }
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void requestObserveWeather(int requestId, GeoPos geoPos, IObserveWeatherCallBack observeWeatherInfo) {
                Log.i(SmartSDKBinder.this.getTAG(), "requestObserveWeather" + requestId);
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).requestObserveWeather(requestId, geoPos, observeWeatherInfo);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void updateFavoriteItem(int Token, FavoriteItem favoriteItem, IFavoritesCallBack callBack) {
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).updateFavoriteItem(Token, favoriteItem, callBack);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public synchronized void zoomStatusUpdate(ZoomStatus status) {
                Log.i(SmartSDKBinder.this.getTAG(), "zoomStatusUpdate");
                if (status != null) {
                    int size = SmartSDKBinder.this.resultlist.size();
                    for (int i = 0; i < size; i++) {
                        try {
                            ISmartNaviNotificationInterface iSmartNaviNotificationInterface = (ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i);
                            Integer value = status.getValue();
                            Intrinsics.checkNotNullExpressionValue(value, "status.value");
                            iSmartNaviNotificationInterface.zoomStatusUpdate(value.intValue());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            SmartSDKBinder.this.resultlist.remove(i);
                            return;
                        }
                    }
                } else {
                    Log.i(SmartSDKBinder.this.getTAG(), "zoomStatusUpdate::status  ==  null");
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public synchronized void routeViewStatusUpdate(RouteViewStatus status) {
                Log.i(SmartSDKBinder.this.getTAG(), "routeViewStatusUpdate");
                if (status != null) {
                    int size = SmartSDKBinder.this.resultlist.size();
                    for (int i = 0; i < size; i++) {
                        try {
                            ISmartNaviNotificationInterface iSmartNaviNotificationInterface = (ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i);
                            Integer value = status.getValue();
                            Intrinsics.checkNotNullExpressionValue(value, "status.value");
                            iSmartNaviNotificationInterface.routeViewStatusUpdate(value.intValue());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            SmartSDKBinder.this.resultlist.remove(i);
                            return;
                        }
                    }
                } else {
                    Log.i(SmartSDKBinder.this.getTAG(), "routeViewStatusUpdate :: status == null");
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public synchronized void trafficStatusUpdate(TrafficStatus status) {
                Log.i(SmartSDKBinder.this.getTAG(), "trafficStatusUpdate");
                if (status != null) {
                    int size = SmartSDKBinder.this.resultlist.size();
                    for (int i = 0; i < size; i++) {
                        try {
                            ISmartNaviNotificationInterface iSmartNaviNotificationInterface = (ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i);
                            Integer value = status.getValue();
                            Intrinsics.checkNotNullExpressionValue(value, "status.value");
                            iSmartNaviNotificationInterface.trafficStatusUpdate(value.intValue());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            SmartSDKBinder.this.resultlist.remove(i);
                            return;
                        }
                    }
                } else {
                    Log.i(SmartSDKBinder.this.getTAG(), "trafficStatusUpdate ::status = null");
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public synchronized void muteModeUpdate(MuteStatus mode) {
                Log.i(SmartSDKBinder.this.getTAG(), "muteModeUpdate");
                if (mode != null) {
                    int size = SmartSDKBinder.this.resultlist.size();
                    for (int i = 0; i < size; i++) {
                        try {
                            ISmartNaviNotificationInterface iSmartNaviNotificationInterface = (ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i);
                            Integer value = mode.getValue();
                            Intrinsics.checkNotNullExpressionValue(value, "mode.value");
                            iSmartNaviNotificationInterface.muteModeUpdate(value.intValue());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            SmartSDKBinder.this.resultlist.remove(i);
                            return;
                        }
                    }
                } else {
                    Log.i(SmartSDKBinder.this.getTAG(), "muteModeUpdate mode == null");
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public synchronized void dayNightStatusUpdate(DayNightStatus status) {
                Log.i(SmartSDKBinder.this.getTAG(), "dayNightStatusUpdate");
                if (status != null) {
                    int size = SmartSDKBinder.this.resultlist.size();
                    for (int i = 0; i < size; i++) {
                        try {
                            ISmartNaviNotificationInterface iSmartNaviNotificationInterface = (ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i);
                            Integer value = status.getValue();
                            Intrinsics.checkNotNullExpressionValue(value, "status.value");
                            iSmartNaviNotificationInterface.dayNightStatusUpdate(value.intValue());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            SmartSDKBinder.this.resultlist.remove(i);
                            return;
                        }
                    }
                } else {
                    Log.i(SmartSDKBinder.this.getTAG(), "dayNightStatusUpdate::status = null");
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void addItemToFavorites(int Token, FavoriteItem favoriteItem, IAddItemToFavoritesCallBack callBack) {
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).addItemToFavorites(Token, favoriteItem, callBack);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public synchronized void naviStatusUpdate(NaviStatus status) {
                Log.i(SmartSDKBinder.this.getTAG(), "naviStatusUpdate");
                if (status != null) {
                    int size = SmartSDKBinder.this.resultlist.size();
                    for (int i = 0; i < size; i++) {
                        try {
                            ISmartNaviNotificationInterface iSmartNaviNotificationInterface = (ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i);
                            Integer value = status.getValue();
                            Intrinsics.checkNotNullExpressionValue(value, "status.value");
                            iSmartNaviNotificationInterface.naviStatusUpdate(value.intValue());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            SmartSDKBinder.this.resultlist.remove(i);
                            return;
                        }
                    }
                } else {
                    Log.i(SmartSDKBinder.this.getTAG(), "naviStatusUpdate :: status = null ");
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void requestForecastWeather(int requestId, GeoPos geoPos, IForecastWeatherCallBack forecastWeatherInfo) {
                Log.i(SmartSDKBinder.this.getTAG(), "requestForecastWeather" + requestId);
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).requestForecastWeather(requestId, geoPos, forecastWeatherInfo);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void requestSetelData(SetelData data, ISetelDataCallBack callBack) {
                Log.i(SmartSDKBinder.this.getTAG(), "requestSetelData");
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).requestSetelData(data, callBack);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void requestVRCommand(int functype, int action) {
                Log.i(SmartSDKBinder.this.getTAG(), "requestVRCommand");
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).requestVRCommand(functype, action);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void availableRestoreGuidance(int status) {
                Log.i(SmartSDKBinder.this.getTAG(), "availableRestoreGuidance" + status);
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).availableRestoreGuidance(status);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public synchronized void guidanceInfoUpdate(GuidanceInfo info) {
                Log.i(SmartSDKBinder.this.getTAG(), "guidanceInfoUpdate");
                if (info != null) {
                    int size = SmartSDKBinder.this.resultlist.size();
                    int i = 0;
                    while (i < size) {
                        try {
                            ISmartNaviNotificationInterface iSmartNaviNotificationInterface = (ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i);
                            Integer turnId = info.getTurnId();
                            Intrinsics.checkNotNullExpressionValue(turnId, "info.turnId");
                            int iIntValue = turnId.intValue();
                            Integer remainDistanceM = info.getRemainDistanceM();
                            Intrinsics.checkNotNullExpressionValue(remainDistanceM, "info.remainDistanceM");
                            int iIntValue2 = remainDistanceM.intValue();
                            Integer nextTurnDistanceM = info.getNextTurnDistanceM();
                            Intrinsics.checkNotNullExpressionValue(nextTurnDistanceM, "info.nextTurnDistanceM");
                            int iIntValue3 = nextTurnDistanceM.intValue();
                            Integer nextTurnTime = info.getNextTurnTime();
                            Intrinsics.checkNotNullExpressionValue(nextTurnTime, "info.nextTurnTime");
                            int iIntValue4 = nextTurnTime.intValue();
                            Integer remainTime = info.getRemainTime();
                            Intrinsics.checkNotNullExpressionValue(remainTime, "info.remainTime");
                            int iIntValue5 = remainTime.intValue();
                            String nextRoadName = info.getNextRoadName();
                            String curRoadName = info.getCurRoadName();
                            Float carDirection = info.getCarDirection();
                            Intrinsics.checkNotNullExpressionValue(carDirection, "info.carDirection");
                            float fFloatValue = carDirection.floatValue();
                            Integer value = info.getGuideType().getValue();
                            Intrinsics.checkNotNullExpressionValue(value, "info.guideType.value");
                            int iIntValue6 = value.intValue();
                            Integer value2 = info.getRoadType().getValue();
                            Intrinsics.checkNotNullExpressionValue(value2, "info.roadType.value");
                            int iIntValue7 = value2.intValue();
                            Integer value3 = info.getSapaType().getValue();
                            Intrinsics.checkNotNullExpressionValue(value3, "info.sapaType.value");
                            int iIntValue8 = value3.intValue();
                            String sapaName = info.getSapaName();
                            Integer sapaDistanceM = info.getSapaDistanceM();
                            int i2 = size;
                            Intrinsics.checkNotNullExpressionValue(sapaDistanceM, "info.sapaDistanceM");
                            int iIntValue9 = sapaDistanceM.intValue();
                            Integer value4 = info.getCameratype().getValue();
                            Intrinsics.checkNotNullExpressionValue(value4, "info.cameratype.value");
                            int iIntValue10 = value4.intValue();
                            Integer cameraDistanceM = info.getCameraDistanceM();
                            Intrinsics.checkNotNullExpressionValue(cameraDistanceM, "info.cameraDistanceM");
                            int iIntValue11 = cameraDistanceM.intValue();
                            Integer cameraSpeed = info.getCameraSpeed();
                            Intrinsics.checkNotNullExpressionValue(cameraSpeed, "info.cameraSpeed");
                            iSmartNaviNotificationInterface.guidanceInfoUpdate(iIntValue, iIntValue2, iIntValue3, iIntValue4, iIntValue5, nextRoadName, curRoadName, fFloatValue, iIntValue6, iIntValue7, iIntValue8, sapaName, iIntValue9, iIntValue10, iIntValue11, cameraSpeed.intValue());
                            i++;
                            size = i2;
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            SmartSDKBinder.this.resultlist.remove(i);
                            return;
                        }
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void reqSetelServiceStatus(IBooleanCallback callback) {
                Log.i(SmartSDKBinder.this.getTAG(), "reqSetelServiceStatus");
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).reqSetelServiceStatus(callback);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void getLoggedInState(ILoggedInStateCallback callback) {
                Log.i(SmartSDKBinder.this.getTAG(), "getLoggedInState");
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).getLoggedInState(callback);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public synchronized void arrivedDest() {
                Log.i(SmartSDKBinder.this.getTAG(), "arrivedDest");
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).arrivedDest();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public synchronized void mapStatusUpdate(MapStatus status) {
                if (status != null) {
                    Log.i(SmartSDKBinder.this.getTAG(), "mapStatusUpdate" + status);
                    int size = SmartSDKBinder.this.resultlist.size();
                    for (int i = 0; i < size; i++) {
                        try {
                            ISmartNaviNotificationInterface iSmartNaviNotificationInterface = (ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i);
                            Integer value = status.getValue();
                            Intrinsics.checkNotNullExpressionValue(value, "status.value");
                            iSmartNaviNotificationInterface.mapStatusUpdate(value.intValue());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            SmartSDKBinder.this.resultlist.remove(i);
                            return;
                        }
                    }
                } else {
                    Log.i(SmartSDKBinder.this.getTAG(), "mapStatusUpdate ::status =null");
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void commutePush(int type, int totalDistance, int eta, List<TrafficInfo> trafficInfo) {
                int size;
                Log.i(SmartSDKBinder.this.getTAG(), "commutePush");
                if (trafficInfo != null) {
                    ArrayList arrayList = new ArrayList();
                    if (trafficInfo.size() > 0 && trafficInfo.size() - 1 >= 0) {
                        int i = 0;
                        while (true) {
                            arrayList.add(SmartSDKBinder.this.octaTrafficInfo2AidlTrafficInfo(trafficInfo.get(i)));
                            if (i == size) {
                                break;
                            } else {
                                i++;
                            }
                        }
                    }
                    int size2 = SmartSDKBinder.this.resultlist.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        try {
                            ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i2)).commutePush(type, totalDistance, eta, arrayList);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            SmartSDKBinder.this.resultlist.remove(i2);
                            return;
                        }
                    }
                    return;
                }
                Log.i(SmartSDKBinder.this.getTAG(), "commutePush ::trafficInfo == null");
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public synchronized void routeInfoUpdate(RouteUpdateInfo info) {
                Log.i(SmartSDKBinder.this.getTAG(), "routeInfoUpdate");
                if (info != null && info.getRouteInfoList() != null && info.getRouteInfoList().size() > 0) {
                    ArrayList arrayList = new ArrayList();
                    int size = info.getRouteInfoList().size() - 1;
                    if (size >= 0) {
                        int i = 0;
                        while (true) {
                            SmartSDKBinder smartSDKBinder = SmartSDKBinder.this;
                            RouteInfo routeInfo = info.getRouteInfoList().get(i);
                            Intrinsics.checkNotNullExpressionValue(routeInfo, "info.routeInfoList[a]");
                            arrayList.add(smartSDKBinder.octaRouteInfo2AidlRouteInfo(routeInfo));
                            if (i == size) {
                                break;
                            } else {
                                i++;
                            }
                        }
                    }
                    int size2 = SmartSDKBinder.this.resultlist.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        try {
                            ISmartNaviNotificationInterface iSmartNaviNotificationInterface = (ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i2);
                            Integer routeSelectedIndex = info.getRouteSelectedIndex();
                            Intrinsics.checkNotNullExpressionValue(routeSelectedIndex, "info.routeSelectedIndex");
                            iSmartNaviNotificationInterface.routeInfoUpdate(routeSelectedIndex.intValue(), arrayList);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            SmartSDKBinder.this.resultlist.remove(i2);
                            return;
                        }
                    }
                } else {
                    Log.e(SmartSDKBinder.this.getTAG(), "routeInfoUpdate :info is null ");
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public synchronized void favoritesInfoChanged(List<FavoritePoiInfo> info) {
                int size;
                Log.i(SmartSDKBinder.this.getTAG(), "favoritesInfoChanged");
                ArrayList arrayList = new ArrayList();
                if (info != null) {
                    if (info.size() > 0 && info.size() - 1 >= 0) {
                        int i = 0;
                        while (true) {
                            com.neusoft.na.ocservice.FavoritePoiInfo favoritePoiInfo = new com.neusoft.na.ocservice.FavoritePoiInfo();
                            Integer value = info.get(i).getFavoriteType().getValue();
                            Intrinsics.checkNotNullExpressionValue(value, "info[a].favoriteType.value");
                            favoritePoiInfo.setFavoriteType(value.intValue());
                            SmartSDKBinder smartSDKBinder = SmartSDKBinder.this;
                            PoiInfo poiInfo = info.get(i).getPoiInfo();
                            Intrinsics.checkNotNullExpressionValue(poiInfo, "info[a].poiInfo");
                            favoritePoiInfo.setPoiInfo(smartSDKBinder.octaPOI2AidlPOI(poiInfo));
                            arrayList.add(favoritePoiInfo);
                            if (i == size) {
                                break;
                            } else {
                                i++;
                            }
                        }
                    }
                    int size2 = SmartSDKBinder.this.resultlist.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        try {
                            ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i2)).favoritesInfoChanged(arrayList);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            SmartSDKBinder.this.resultlist.remove(i2);
                            return;
                        }
                    }
                } else {
                    Log.i(SmartSDKBinder.this.getTAG(), "favoritesInfoChanged::info ==null");
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void getFavoriteList(int Token, IGetFavoriteListCallBack callBack) {
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).getFavoriteList(Token, callBack);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void requestSetelDetail(int Token, String stationId, IRequestSetelDetailCallBack callBack) {
                Intrinsics.checkNotNullParameter(stationId, "stationId");
                Log.i(SmartSDKBinder.this.getTAG(), "requestSetelData");
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).requestSetelDetail(Token, stationId, callBack);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void onMapStatusChanged(int mapStatus) {
                Log.i(SmartSDKBinder.this.getTAG(), "onMapStatusChanged");
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).onMapStatusChanged(mapStatus);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void onDRPositionUpdate(DRPosition position) {
                Intrinsics.checkNotNullParameter(position, "position");
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).onDRPositionUpdate(position);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void onGuidanceViewChanged(boolean guidanceViewStatus) {
                Log.i(SmartSDKBinder.this.getTAG(), "onGuidanceViewChanged");
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).onGuidanceViewChanged(guidanceViewStatus);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void onSpeedLimitInfoUpdate(boolean isOverspeed, int speedLimit, boolean limitSpeedExist) {
                Log.i(SmartSDKBinder.this.getTAG(), "onSpeedLimitInfoUpdate");
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).onSpeedLimitInfoUpdate(isOverspeed, speedLimit, limitSpeedExist);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void onArrivedLastMile(com.neusoft.na.ocservice.PoiInfo destPoi) {
                Log.i(SmartSDKBinder.this.getTAG(), "onArrivedLastMile");
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).onArrivedLastMile(destPoi);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public synchronized void highwayInfoUpdate(HighwayExitInfo info) {
                Log.i(SmartSDKBinder.this.getTAG(), "highwayInfoUpdate");
                if (info != null) {
                    int size = SmartSDKBinder.this.resultlist.size();
                    for (int i = 0; i < size; i++) {
                        try {
                            ISmartNaviNotificationInterface iSmartNaviNotificationInterface = (ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i);
                            Integer exitCount = info.getExitCount();
                            Intrinsics.checkNotNullExpressionValue(exitCount, "info.exitCount");
                            iSmartNaviNotificationInterface.highwayInfoUpdate(exitCount.intValue(), info.getExitNumStr(), info.getExitDirection(), info.getEtaDistanceM().intValue(), info.getEtaTime().intValue());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            SmartSDKBinder.this.resultlist.remove(i);
                            return;
                        }
                    }
                } else {
                    Log.i(SmartSDKBinder.this.getTAG(), "highwayInfoUpdate :: info == null");
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public boolean reqPhoneCallByNumber(String number) {
                Log.i(SmartSDKBinder.this.getTAG(), "reqPhoneCallByNumber");
                if (number != null) {
                    if (SmartSDKBinder.this.resultlist.size() > 0) {
                        try {
                            return ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(0)).reqPhoneCallByNumber(number);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            SmartSDKBinder.this.resultlist.remove(0);
                            return false;
                        }
                    }
                } else {
                    Log.i(SmartSDKBinder.this.getTAG(), "reqPhoneCallByNumber :: info == null");
                }
                return false;
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void removeFavoriteItems(int Token, String id, int type, IFavoritesCallBack callBack) {
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).removeFavoriteItems(Token, id, type, callBack);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void cloudRegistrationStatus(boolean isRegistered) {
                Log.i(SmartSDKBinder.this.getTAG(), "cloudRegistrationStatus");
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).cloudRegistrationStatus(isRegistered);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public synchronized void adareaChange(String city, String cityCode, String province, String country, String countryCode, String adcode, String adname) {
                Log.i(SmartSDKBinder.this.getTAG(), "adareaChange");
                int size = SmartSDKBinder.this.resultlist.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i)).adareaChange(city, cityCode, province, country, countryCode, adcode, adname);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public synchronized void viewModeUpdate(ViewModeStatus mode) {
                Log.i(SmartSDKBinder.this.getTAG(), "viewModeUpdate");
                if (mode != null) {
                    int size = SmartSDKBinder.this.resultlist.size();
                    for (int i = 0; i < size; i++) {
                        try {
                            ISmartNaviNotificationInterface iSmartNaviNotificationInterface = (ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i);
                            Integer value = mode.getValue();
                            Intrinsics.checkNotNullExpressionValue(value, "mode.value");
                            iSmartNaviNotificationInterface.viewModeUpdate(value.intValue());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            SmartSDKBinder.this.resultlist.remove(i);
                            return;
                        }
                    }
                } else {
                    Log.i(SmartSDKBinder.this.getTAG(), "viewModeUpdate :: mode == null");
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public void lanesInfo(boolean lanesEnable, List<LaneInfo> lanesInfo) {
                int size;
                Log.i(SmartSDKBinder.this.getTAG(), "lanesInfo");
                ArrayList arrayList = new ArrayList();
                if (lanesInfo != null && lanesInfo.size() > 0 && lanesInfo.size() - 1 >= 0) {
                    int i = 0;
                    while (true) {
                        arrayList.add(SmartSDKBinder.this.octaLaneInfo2AidlLaneInfo(lanesInfo.get(i)));
                        if (i == size) {
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                int size2 = SmartSDKBinder.this.resultlist.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    try {
                        ((ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i2)).lanesInfo(lanesEnable, arrayList);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        SmartSDKBinder.this.resultlist.remove(i2);
                        return;
                    }
                }
            }

            @Override // com.neusoft.na.ocservice.smartnavisdk.INotificationListener
            public synchronized void routeSelectedChaned(RouteUpdateInfo info) {
                Log.i(SmartSDKBinder.this.getTAG(), "routeSelectedChaned");
                ArrayList arrayList = new ArrayList();
                if (info != null && info.getRouteInfoList() != null && info.getRouteInfoList().size() > 0) {
                    int size = info.getRouteInfoList().size() - 1;
                    if (size >= 0) {
                        int i = 0;
                        while (true) {
                            SmartSDKBinder smartSDKBinder = SmartSDKBinder.this;
                            RouteInfo routeInfo = info.getRouteInfoList().get(i);
                            Intrinsics.checkNotNullExpressionValue(routeInfo, "info.routeInfoList[a]");
                            arrayList.add(smartSDKBinder.octaRouteInfo2AidlRouteInfo(routeInfo));
                            if (i == size) {
                                break;
                            } else {
                                i++;
                            }
                        }
                    }
                    int size2 = SmartSDKBinder.this.resultlist.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        try {
                            ISmartNaviNotificationInterface iSmartNaviNotificationInterface = (ISmartNaviNotificationInterface) SmartSDKBinder.this.resultlist.get(i2);
                            Integer routeSelectedIndex = info.getRouteSelectedIndex();
                            Intrinsics.checkNotNullExpressionValue(routeSelectedIndex, "info.routeSelectedIndex");
                            iSmartNaviNotificationInterface.routeSelectedChaned(routeSelectedIndex.intValue(), arrayList);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            SmartSDKBinder.this.resultlist.remove(i2);
                            return;
                        }
                    }
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(iNavigationApiCreate, "create(context.applicati…  }\n\n         }\n\n      })");
        this.navigation = iNavigationApiCreate;
    }

    public final String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: compiled from: SmartSDKBinder.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/neusoft/na/ocservice/Binder/SmartSDKBinder$Companion;", "", "()V", "instance", "Lcom/neusoft/na/ocservice/Binder/SmartSDKBinder;", "getInstance", "context", "Landroid/content/Context;", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SmartSDKBinder getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (SmartSDKBinder.instance == null) {
                SmartSDKBinder.instance = new SmartSDKBinder(context);
            }
            return SmartSDKBinder.instance;
        }
    }

    public final com.neusoft.na.ocservice.LaneInfo octaLaneInfo2AidlLaneInfo(LaneInfo laneinfo) {
        Intrinsics.checkNotNullParameter(laneinfo, "laneinfo");
        com.neusoft.na.ocservice.LaneInfo laneInfo = new com.neusoft.na.ocservice.LaneInfo();
        Integer laneIconId = laneinfo.getLaneIconId();
        Intrinsics.checkNotNullExpressionValue(laneIconId, "laneinfo.laneIconId");
        laneInfo.setLaneIconId(laneIconId.intValue());
        Integer laneIndex = laneinfo.getLaneIndex();
        Intrinsics.checkNotNullExpressionValue(laneIndex, "laneinfo.laneIndex");
        laneInfo.setLaneIndex(laneIndex.intValue());
        return laneInfo;
    }

    public final com.neusoft.na.ocservice.RouteInfo octaRouteInfo2AidlRouteInfo(RouteInfo route) {
        int size;
        Intrinsics.checkNotNullParameter(route, "route");
        com.neusoft.na.ocservice.RouteInfo routeInfo = new com.neusoft.na.ocservice.RouteInfo();
        routeInfo.setRouteTag(route.getRouteTag());
        Integer strategy = route.getStrategy();
        Intrinsics.checkNotNullExpressionValue(strategy, "route.strategy");
        routeInfo.setStrategy(strategy.intValue());
        Double time = route.getTime();
        Intrinsics.checkNotNullExpressionValue(time, "route.time");
        routeInfo.setTime(time.doubleValue());
        Double distance = route.getDistance();
        Intrinsics.checkNotNullExpressionValue(distance, "route.distance");
        routeInfo.setDistance(distance.doubleValue());
        routeInfo.setTimeAuto(route.getTimeAuto());
        routeInfo.setDistanceAuto(route.getDistanceAutoM());
        Integer trafficLights = route.getTrafficLights();
        Intrinsics.checkNotNullExpressionValue(trafficLights, "route.trafficLights");
        routeInfo.setTrafficLights(trafficLights.intValue());
        Integer tolls = route.getTolls();
        Intrinsics.checkNotNullExpressionValue(tolls, "route.tolls");
        routeInfo.setTolls(tolls.intValue());
        if (route.getTrafficInfos().size() > 0 && route.getTrafficInfos().size() - 1 >= 0) {
            int i = 0;
            while (true) {
                List<com.neusoft.na.ocservice.TrafficInfo> trafficInfos = routeInfo.getTrafficInfos();
                TrafficInfo trafficInfo = route.getTrafficInfos().get(i);
                Intrinsics.checkNotNullExpressionValue(trafficInfo, "route.trafficInfos[i]");
                trafficInfos.add(octaTrafficInfo2AidlTrafficInfo(trafficInfo));
                if (i == size) {
                    break;
                }
                i++;
            }
        }
        return routeInfo;
    }

    public final RouteInfo aidlRouteInfo2OctaRouteInfo(com.neusoft.na.ocservice.RouteInfo route) {
        int size;
        Intrinsics.checkNotNullParameter(route, "route");
        RouteInfo routeInfo = new RouteInfo();
        routeInfo.setRouteTag(route.getRouteTag());
        routeInfo.setStrategy(Integer.valueOf(route.getStrategy()));
        routeInfo.setTime(Double.valueOf(route.getTime()));
        routeInfo.setDistance(Double.valueOf(route.getDistance()));
        routeInfo.setTimeAuto(route.getTimeAuto());
        routeInfo.setDistanceAutoM(route.getDistanceAuto());
        routeInfo.setTrafficLights(Integer.valueOf(route.getTrafficLights()));
        routeInfo.setTolls(Integer.valueOf(route.getTolls()));
        if (route.getTrafficInfos().size() > 0 && route.getTrafficInfos().size() - 1 >= 0) {
            int i = 0;
            while (true) {
                List<TrafficInfo> trafficInfos = routeInfo.getTrafficInfos();
                com.neusoft.na.ocservice.TrafficInfo trafficInfo = route.getTrafficInfos().get(i);
                Intrinsics.checkNotNullExpressionValue(trafficInfo, "route.trafficInfos[i]");
                trafficInfos.add(aidlTrafficInfo2OctaTrafficInfo(trafficInfo));
                if (i == size) {
                    break;
                }
                i++;
            }
        }
        return routeInfo;
    }

    public final com.neusoft.na.ocservice.TrafficInfo octaTrafficInfo2AidlTrafficInfo(TrafficInfo trafficinfo) {
        Intrinsics.checkNotNullParameter(trafficinfo, "trafficinfo");
        com.neusoft.na.ocservice.TrafficInfo trafficInfo = new com.neusoft.na.ocservice.TrafficInfo();
        Integer segIndex = trafficinfo.getSegIndex();
        Intrinsics.checkNotNullExpressionValue(segIndex, "trafficinfo.segIndex");
        trafficInfo.setSegIndex(segIndex.intValue());
        Integer value = trafficinfo.getTmcStatus().getValue();
        Intrinsics.checkNotNullExpressionValue(value, "trafficinfo.tmcStatus.value");
        trafficInfo.setTmcStatus(value.intValue());
        Double percent = trafficinfo.getPercent();
        Intrinsics.checkNotNullExpressionValue(percent, "trafficinfo.percent");
        trafficInfo.setPercent(percent.doubleValue());
        return trafficInfo;
    }

    public final TrafficInfo aidlTrafficInfo2OctaTrafficInfo(com.neusoft.na.ocservice.TrafficInfo trafficinfo) {
        Intrinsics.checkNotNullParameter(trafficinfo, "trafficinfo");
        TrafficInfo trafficInfo = new TrafficInfo();
        trafficInfo.setSegIndex(Integer.valueOf(trafficinfo.getSegIndex()));
        trafficInfo.setTmcStatus(RoadTrafficStatus.getByValue(Integer.valueOf(trafficinfo.getTmcStatus())));
        trafficInfo.setPercent(Double.valueOf(trafficinfo.getPercent()));
        return trafficInfo;
    }

    public final com.neusoft.na.ocservice.PoiInfo octaPOI2AidlPOI(PoiInfo poi) {
        Intrinsics.checkNotNullParameter(poi, "poi");
        com.neusoft.na.ocservice.PoiInfo poiInfo = new com.neusoft.na.ocservice.PoiInfo();
        poiInfo.setName(poi.getName());
        poiInfo.setUid(poi.getUid());
        Integer distance = poi.getDistance();
        Intrinsics.checkNotNullExpressionValue(distance, "poi.distance");
        poiInfo.setDistance(distance.intValue());
        poiInfo.setAddress(poi.getAddress());
        PoiType poiType = new PoiType();
        api.structs.com.neusoft.smart.PoiType type = poi.getType();
        poiType.setTypeCode(type != null ? type.getTypeCode() : null);
        api.structs.com.neusoft.smart.PoiType type2 = poi.getType();
        poiType.setTypeName(type2 != null ? type2.getTypeName() : null);
        poiInfo.setType(poiType);
        LatLng latLng = new LatLng();
        latLng.setCoordType(poi.getLatLng().getCoordType());
        latLng.setLatitude(poi.getLatLng().getLatitude());
        latLng.setLongitude(poi.getLatLng().getLongitude());
        poiInfo.setLatLng(latLng);
        return poiInfo;
    }

    public final PoiInfo aidlPOI2octaPOI(com.neusoft.na.ocservice.PoiInfo poi) {
        PoiInfo poiInfo = new PoiInfo();
        if (poi != null) {
            poiInfo.setName(poi.getName());
            poiInfo.setUid(poi.getUid());
            poiInfo.setDistance(Integer.valueOf(poi.getDistance()));
            poiInfo.setAddress(poi.getAddress());
            api.structs.com.neusoft.smart.PoiType poiType = new api.structs.com.neusoft.smart.PoiType();
            PoiType type = poi.getType();
            poiType.setTypeCode(type != null ? type.getTypeCode() : null);
            PoiType type2 = poi.getType();
            poiType.setTypeName(type2 != null ? type2.getTypeName() : null);
            poiInfo.setType(poiType);
            api.structs.com.neusoft.smart.LatLng latLng = new api.structs.com.neusoft.smart.LatLng();
            LatLng latLng2 = poi.getLatLng();
            latLng.setCoordType(latLng2 != null ? latLng2.getCoordType() : null);
            LatLng latLng3 = poi.getLatLng();
            latLng.setLatitude(latLng3 != null ? latLng3.getLatitude() : null);
            LatLng latLng4 = poi.getLatLng();
            latLng.setLongitude(latLng4 != null ? latLng4.getLongitude() : null);
            poiInfo.setLatLng(latLng);
        }
        return poiInfo;
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean switchTraffic(int trafficType, IResultCodeCallBack callback) {
        return this.navigation.switchTraffic(trafficType, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean getTrafficSummaryInfo(String trafficText, IGetTrafficSummaryInfocCallBack callback) {
        return this.navigation.getTrafficSummaryInfo(trafficText, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean searchAround(int action, int searchType, String keywords, int searchRadius, double latitude, double longitude, ISearchAroundCallBack callback) {
        return this.navigation.searchAround(action, searchType, keywords, searchRadius, latitude, longitude, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean showMyLocation(int actionType, IShowMyLocationCallBack callback) {
        return this.navigation.showMyLocation(actionType, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean getFavoritePois(int favType, IGetFavoritePoisCallBack callback) {
        return this.navigation.getFavoritePois(favType, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean requestlRecommendedGasStations(int token, IResultGasStationCallBack callBack) {
        Log.i(this.TAG, "requestlRecommendedGasStations," + token);
        return this.navigation.requestlRecommendedGasStations(token, callBack);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean setMapViewMode(int viewMode, IResultCodeCallBack callback) {
        return this.navigation.setMapViewMode(viewMode, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean getNaviMuteMode(IGetNaviMuteModeCallBack callback) {
        return this.navigation.getNaviMuteMode(callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean getMapLevelInfo(IGetMapLevelInfoCallBack callBack) {
        return this.navigation.getMapLevelInfo(callBack);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean setRoutePrefer(int strategy, IResultCodeCallBack callback) {
        return this.navigation.setRoutePrefer(strategy, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean setLoggedInState(boolean hasLoggedIn, String protonID, IResultCodeCallBack callBack) {
        return this.navigation.setLoggedInState(hasLoggedIn, protonID, callBack);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public void requestJumpToViewPage(int viewPageType, IResultCodeCallBack callback) {
        this.navigation.requestJumpToViewPage(viewPageType, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public void addSurface(Surface surface, int type, int height, int width, int roadViewHeight, int roadViewWidth, int offsetX, int offsetY) {
        if (surface != null) {
            Log.i(this.TAG, "setSurface: " + Boolean.valueOf(surface.isValid()));
        }
        this.navigation.setSurface(surface);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public void setMuteStatus(boolean isMute, IResultCodeCallBack callback) {
        this.navigation.setMuteStatus(isMute, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public void getDetailInfo(com.neusoft.na.ocservice.PoiInfo info, int SearchType, IDetailInfoCallback callback) {
        this.navigation.getDetailInfo(aidlPOI2octaPOI(info), SearchType, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public void searchEVChargingStation(int searchEVType, String keyWord, int searchRadius, LatLng geoPosition, IEVChargingStationCallback callback) {
        INavigationApi iNavigationApi = this.navigation;
        Double latitude = geoPosition != null ? geoPosition.getLatitude() : null;
        double dDoubleValue = latitude == null ? 0.0d : latitude.doubleValue();
        Double longitude = geoPosition != null ? geoPosition.getLongitude() : null;
        iNavigationApi.searchEVChargingStation(searchEVType, keyWord, searchRadius, dDoubleValue, longitude == null ? 0.0d : longitude.doubleValue(), callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public void getEVDetailInfo(EVChargingStation info, int SearchType, IDetailInfoCallback callback) {
        this.navigation.getEVDetailInfo(info, SearchType, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean searchByKeyword(int action, int searchType, String keywords, String searchCity, ISearchByKeywordCallBack callbak) {
        return this.navigation.searchByKeyword(action, searchType, keywords, searchCity, callbak);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean getHistoryPois(int maxCount, IGetHistoryPoisCallBack callback) {
        return this.navigation.getHistoryPois(maxCount, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean getRouteInfo(IGetRouteInfoCallBack callback) {
        return this.navigation.getRouteInfo(callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean getFrontTraffic(int frontDistance, IGetFrontTrafficCallBack callback) {
        return this.navigation.getFrontTraffic(frontDistance, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean unSetListener(ISmartNaviNotificationInterface notification) {
        Log.i(this.TAG, "unsetListener");
        this.resultlist.clear();
        Log.i(this.TAG, "setListener.size::" + this.resultlist.size());
        return true;
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean exitMap(IResultCodeCallBack callback) {
        return this.navigation.exitMap(callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean setListener(ISmartNaviNotificationInterface notification) {
        Log.i(this.TAG, "setListener");
        if (notification == null) {
            return true;
        }
        this.resultlist.clear();
        ArrayList<ISmartNaviNotificationInterface> arrayList = this.resultlist;
        if (arrayList != null) {
            arrayList.add(notification);
        }
        Log.i(this.TAG, "setListener.size::" + this.resultlist.size());
        return true;
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean selectRoute(int selectIndex, IResultCodeCallBack callback) {
        return this.navigation.selectRoute(selectIndex, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean requestRoutePlanOrNavi(int action, int strategy, com.neusoft.na.ocservice.PoiInfo destPoiInfo, List<com.neusoft.na.ocservice.PoiInfo> viaPoiInfos, IRequestRoutePlanOrNavi callback) {
        int size;
        ArrayList arrayList = new ArrayList();
        if (viaPoiInfos != null && viaPoiInfos.size() > 0 && viaPoiInfos.size() - 1 >= 0) {
            int i = 0;
            while (true) {
                arrayList.add(aidlPOI2octaPOI(viaPoiInfos.get(i)));
                if (i == size) {
                    break;
                }
                i++;
            }
        }
        return this.navigation.requestRoutePlanOrNavi(action, strategy, aidlPOI2octaPOI(destPoiInfo), arrayList, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean getCloudRegistrationStatus(IGetCloudRegistrationStatusCallBack callBack) {
        return this.navigation.getCloudRegistrationStatus(callBack);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean cancelNavi(IResultCodeCallBack callback) {
        return this.navigation.cancelNavi(callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean vrMsgCommand(int functype, int command, IResultCodeCallBack callBack) {
        return this.navigation.vrMsgCommand(functype, command, callBack);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean specialPoiNavi(int action, int poiType, ISpecialPoiNaviCallback callback) {
        return this.navigation.specialPoiNavi(action, poiType, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean getGuideInfo(IGetGuideInfoCallBack callback) {
        return this.navigation.getGuideInfo(callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean modifyNaviVia(int action, com.neusoft.na.ocservice.PoiInfo viaPoi, IResultCodeCallBack callback) {
        return this.navigation.modifyNaviVia(action, aidlPOI2octaPOI(viaPoi), callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean getHighwayExitInfo(int actionType, IGetHighwayExitInfo callback) {
        return this.navigation.getHighwayExitInfo(actionType, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean resumeGuidance(int action, int resumeGuidanceAction, IResultCodeCallBack callBack) {
        return this.navigation.resumeGuidance(action, resumeGuidanceAction, callBack);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean requestGeoDecoder(double latitude, double longitude, IRequestGeoDecoderCallBack callback) {
        return this.navigation.requestGeoDecoder(latitude, longitude, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean setNaviMuteMode(int muteState, IResultCodeCallBack callback) {
        return this.navigation.setNaviMuteMode(muteState, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean alongTheWaySearch(int action, int searchType, IAlongTheWaySearchCallBack callback) {
        return this.navigation.alongTheWaySearch(action, searchType, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean editFavoritePois(int favoriteType, String poiName, String address, double latitude, double longitude, String coordType, IResultCodeCallBack callback) {
        return this.navigation.editFavoritePois(favoriteType, poiName, address, latitude, longitude, coordType, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean openDestinationSearch(IResultCodeCallBack callback) {
        return this.navigation.openDestinationSearch(callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean mapZoomInOut(int zoomInOutType, IResultCodeCallBack callback) {
        return this.navigation.mapZoomInOut(zoomInOutType, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean backToMap(IResultCodeCallBack callback) {
        return this.navigation.backToMap(callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean launchMap(IResultCodeCallBack callback) {
        return this.navigation.launchMap(callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean naviFullView(int operationType, IResultCodeCallBack callback) {
        return this.navigation.naviFullView(operationType, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean goFavorite(int favType, IResultCodeCallBack callback) {
        return this.navigation.goFavorite(favType, callback);
    }

    @Override // com.neusoft.na.ocservice.ISmartNaviInterface
    public boolean removeWayPoint(int action, int index, IResultCodeCallBack callBack) {
        return this.navigation.removeWayPoint(action, index, callBack);
    }
}
