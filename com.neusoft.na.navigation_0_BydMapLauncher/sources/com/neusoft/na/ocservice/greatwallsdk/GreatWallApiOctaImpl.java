package com.neusoft.na.ocservice.greatwallsdk;

import android.car.AoapService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Surface;
import api.notifications.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseNotifications;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyNaviDayNightStatusChange;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyNavigationActivityStatus;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyNavigationRunningStatus;
import api.notifications.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiNotifications;
import api.notifications.com.neusoft.greatwall.greatwallnavapi.NotifyCalcPlanningRouteStatus;
import api.notifications.com.neusoft.greatwall.greatwallnavapi.NotifyCountryChangedStatus;
import api.notifications.com.neusoft.greatwall.greatwallnavapi.NotifyCurrentViewInfoStatus;
import api.notifications.com.neusoft.greatwall.greatwallnavapi.NotifyDeadReckoningExtendStatus;
import api.notifications.com.neusoft.greatwall.greatwallnavapi.NotifyGuidanceInfoStatus;
import api.notifications.com.neusoft.greatwall.greatwallnavapi.NotifyGuidanceStatus;
import api.notifications.com.neusoft.greatwall.greatwallnavapi.NotifyLaneInfoStatus;
import api.notifications.com.neusoft.greatwall.greatwallnavapi.NotifyNotifyHeadingStatus;
import api.notifications.com.neusoft.greatwall.greatwallnavapi.NotifyReportLocationInfo;
import api.notifications.com.neusoft.greatwall.greatwallnavapi.NotifySpeedLimitStatus;
import api.notifications.com.neusoft.greatwall.greatwallnavapi.NotifySwitchNavigationLanguageSetting;
import api.notifications.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseNotifications;
import api.notifications.com.neusoft.greatwall.greatwallsysapiinverse.NotifyCongestedRoadStatus;
import api.notifications.com.neusoft.greatwall.greatwallsysapiinverse.NotifyTunnelStatus;
import api.requests.com.neusoft.common.commonsysapiinverse.CommonSysApiInverseRequests;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqGetMapScaleLevel;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqGetMapViewMode;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqGetTrafficInfoState;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqMapZoomInOut;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetMapViewMode;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSetTraceLog;
import api.requests.com.neusoft.common.commonsysapiinverse.ReqSwitchTrafficInfo;
import api.requests.com.neusoft.greatwall.greatwallnavapi.GreatWallNavApiRequests;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqGetFavoriteList;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqGetGuidedStatus;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqGetLocationInfo;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqGetRouteOverviewState;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqGetRoutePreference;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqGetWaypointList;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestAlongRouteSearch;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestCalcPlanningRoute;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestDTDAndETA;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestGetDestinationHistoryList;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestGoHomeOrCompany;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestHighlightedRoute;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestJumpToViewPage;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestOpenMap;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestSearchAround;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestSearchKeyword;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestSetFavorite;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestSetRoutePreference;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestSetWaypoint;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestStartGuidance;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqRequestStopGuidance;
import api.requests.com.neusoft.greatwall.greatwallnavapi.ReqSwitchRouteOverviewInfo;
import api.requests.com.neusoft.greatwall.greatwallsysapiinverse.GreatWallSysApiInverseRequests;
import api.requests.com.neusoft.greatwall.greatwallsysapiinverse.ReqClearUserProfileData;
import api.requests.com.neusoft.greatwall.greatwallsysapiinverse.ReqGetUserProfileData;
import api.requests.com.neusoft.greatwall.greatwallsysapiinverse.ReqSetUserProfileData;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetMapScaleLevel;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetMapViewMode;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetNavigationActivityStatus;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetTraceLog;
import api.responses.com.neusoft.common.commonsysapiinverse.ResGetTrafficInfoState;
import api.responses.com.neusoft.common.commonsysapiinverse.ResMapZoomInOut;
import api.responses.com.neusoft.common.commonsysapiinverse.ResSetMapViewMode;
import api.responses.com.neusoft.common.commonsysapiinverse.ResSwitchTrafficInfo;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetCountryInfo;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetFavoriteList;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetFrontTraffic;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetGuidanceInfoStatusForGreatWall;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetGuidedStatus;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetLocationInfo;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetRouteOverviewState;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetRoutePreference;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResGetWaypointList;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestAlongRouteSearch;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestCalcPlanningRoute;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestDTDAndETA;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestGetDestinationHistoryList;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestGoHomeOrCompany;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestHighlightedRoute;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestJumpToViewPage;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestOpenMap;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestSearchAround;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestSearchKeyword;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestSetFavorite;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestSetRoutePreference;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestSetWaypoint;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestStartGuidance;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResRequestStopGuidance;
import api.responses.com.neusoft.greatwall.greatwallnavapi.ResSwitchRouteOverviewInfo;
import api.responses.com.neusoft.greatwall.greatwallsysapiinverse.ResClearUserProfileData;
import api.responses.com.neusoft.greatwall.greatwallsysapiinverse.ResGetUserProfileData;
import api.responses.com.neusoft.greatwall.greatwallsysapiinverse.ResSetUserProfileData;
import api.structs.com.neusoft.common.LogOnOff;
import api.structs.com.neusoft.common.NavAPPRunningStatus;
import api.structs.com.neusoft.common.NaviDayNightStatus;
import api.structs.com.neusoft.common.TrafficMode;
import api.structs.com.neusoft.common.ViewModeType;
import api.structs.com.neusoft.common.ZoomInOutType;
import api.structs.com.neusoft.greatwall.CalcRouteActionType;
import api.structs.com.neusoft.greatwall.CalcRouteCondition;
import api.structs.com.neusoft.greatwall.DestHistoryInfo;
import api.structs.com.neusoft.greatwall.FavoriteData;
import api.structs.com.neusoft.greatwall.FavoriteType;
import api.structs.com.neusoft.greatwall.FrontTrafficInfo;
import api.structs.com.neusoft.greatwall.LanguageOnOff;
import api.structs.com.neusoft.greatwall.LocationInfo;
import api.structs.com.neusoft.greatwall.RouteOverviewMode;
import api.structs.com.neusoft.greatwall.RoutePreference;
import api.structs.com.neusoft.greatwall.SearchActionType;
import api.structs.com.neusoft.greatwall.SearchCategory;
import api.structs.com.neusoft.greatwall.SearchResult;
import api.structs.com.neusoft.greatwall.UserProfileData;
import api.structs.com.neusoft.greatwall.ViewPage;
import api.structs.com.neusoft.greatwall.WaypointInfo;
import cerence.ark.assistant.library.JsonKey;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.communication.transports.websocketclient.OneCoreClientEndpoint;
import com.neusoft.de.nts.OneCoreTest.communication.utils.OneCoreWSContextUtil;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.IGettableAttribute;
import com.neusoft.na.common.ClientEndpointManger;
import com.neusoft.na.common.ClientEndpointMangerCallback;
import com.neusoft.na.navigation.IMotsServiceInterface;
import com.neusoft.na.ocservice.OneCoreService;
import com.neusoft.na.ocservice.greatwallsdk.bean.DestinationInformationData;
import com.neusoft.na.ocservice.greatwallsdk.bean.ETAData;
import com.neusoft.na.ocservice.greatwallsdk.bean.GeoPosition;
import com.neusoft.na.ocservice.greatwallsdk.bean.GuidanceInfoStatusData;
import com.neusoft.na.ocservice.greatwallsdk.bean.LaneIconIdData;
import com.neusoft.na.ocservice.greatwallsdk.bean.LaneInfoStatusData;
import com.neusoft.na.ocservice.greatwallsdk.bean.ListPoiData;
import com.neusoft.na.ocservice.greatwallsdk.bean.MapFrontStateData;
import com.neusoft.na.ocservice.greatwallsdk.bean.MapLevelData;
import com.neusoft.na.ocservice.greatwallsdk.bean.MapModeData;
import com.neusoft.na.ocservice.greatwallsdk.bean.ModeData;
import com.neusoft.na.ocservice.greatwallsdk.bean.PeripheralData;
import com.neusoft.na.ocservice.greatwallsdk.bean.PoiData;
import com.neusoft.na.ocservice.greatwallsdk.bean.PreferencesData;
import com.neusoft.na.ocservice.greatwallsdk.bean.ResultData;
import com.neusoft.na.ocservice.greatwallsdk.bean.ResultPoiListData;
import com.neusoft.na.ocservice.greatwallsdk.bean.RoutePreferenceData;
import com.neusoft.na.ocservice.greatwallsdk.bean.SecondTBTData;
import com.neusoft.na.ocservice.greatwallsdk.bean.SelectRouteData;
import com.neusoft.na.ocservice.greatwallsdk.bean.SpeedLimitData;
import com.neusoft.na.ocservice.greatwallsdk.bean.StateData;
import com.neusoft.na.ocservice.greatwallsdk.bean.StatusData;
import com.neusoft.na.ocservice.greatwallsdk.bean.TBTData;
import com.neusoft.na.ocservice.greatwallsdk.bean.TrafficeStateData;
import com.neusoft.na.ocservice.greatwallsdk.bean.UserProfileRecordData;
import com.neusoft.na.ocservice.greatwallsdk.bean.ViewIdData;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java9.util.concurrent.CompletableFuture;
import java9.util.function.BiConsumer;
import java9.util.function.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: GreatWallApiOctaImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000ì\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u0094\u00012\u00020\u00012\u00020\u0002:\u0002\u0094\u0001B\u0019\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010:\u001a\u00020\u001c2\u0006\u0010;\u001a\u00020<H\u0016J\b\u0010=\u001a\u00020>H\u0002J:\u0010?\u001a\u00020>2\u0006\u0010@\u001a\u0002092\u0006\u0010A\u001a\u00020<2\u0006\u0010B\u001a\u00020<2\u000e\u0010C\u001a\n\u0012\u0004\u0012\u00020<\u0018\u00010D2\b\u0010E\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010F\u001a\u00020>H\u0016J\b\u0010G\u001a\u00020\u001cH\u0016J\u0010\u0010H\u001a\u00020\u001c2\u0006\u0010I\u001a\u00020\u001cH\u0016J\b\u0010J\u001a\u00020\u001cH\u0016J\b\u0010K\u001a\u00020\u001cH\u0016J\b\u0010L\u001a\u00020\u001cH\u0016J\b\u0010M\u001a\u00020\u001cH\u0016J\b\u0010N\u001a\u00020\u001cH\u0016J\b\u0010O\u001a\u00020\u001cH\u0016J\b\u0010P\u001a\u00020\u001cH\u0016J\b\u0010Q\u001a\u00020\u001cH\u0016J\b\u0010R\u001a\u00020\u001cH\u0016J\n\u0010S\u001a\u0004\u0018\u00010TH\u0016J\b\u0010U\u001a\u00020\u001cH\u0016J\b\u0010V\u001a\u00020\u001cH\u0016J\b\u0010W\u001a\u000209H\u0016J\b\u0010X\u001a\u00020\u001cH\u0016J\b\u0010Y\u001a\u00020\u001cH\u0016J\u0010\u0010Z\u001a\u00020\u001c2\u0006\u0010I\u001a\u00020\u001cH\u0016J\b\u0010[\u001a\u00020\u001cH\u0016J\b\u0010\\\u001a\u00020\u001cH\u0016J\b\u0010]\u001a\u00020\u001cH\u0016J\b\u0010^\u001a\u00020\u001cH\u0016J\b\u0010_\u001a\u00020\u001cH\u0016J\b\u0010`\u001a\u00020\u001cH\u0016J\b\u0010a\u001a\u00020\u001cH\u0016J\b\u0010b\u001a\u00020\u001cH\u0016J\b\u0010c\u001a\u00020\u001cH\u0016J\b\u0010d\u001a\u00020\u001cH\u0016J\b\u0010e\u001a\u00020\u001cH\u0016J\u0010\u0010f\u001a\u00020\u001c2\u0006\u0010;\u001a\u00020<H\u0016J\b\u0010g\u001a\u00020\u001cH\u0016J\b\u0010h\u001a\u00020\u001cH\u0016J\b\u0010i\u001a\u00020>H\u0002J\b\u0010j\u001a\u00020\u001cH\u0016J\b\u0010k\u001a\u00020\u001cH\u0016J\u0010\u0010l\u001a\u00020>2\u0006\u0010\u0003\u001a\u00020\u001eH\u0016J\b\u0010m\u001a\u00020>H\u0016J\u0010\u0010n\u001a\u00020\u001c2\u0006\u0010o\u001a\u00020pH\u0016J\b\u0010q\u001a\u00020\u001cH\u0016J\b\u0010r\u001a\u00020\u001cH\u0016J*\u0010s\u001a\u00020>2\u0006\u0010t\u001a\u0002092\u0006\u0010u\u001a\u00020v2\b\u0010E\u001a\u0004\u0018\u00010\u000b2\u0006\u0010w\u001a\u000209H\u0016J \u0010x\u001a\u00020>2\u0006\u0010t\u001a\u0002092\u0006\u0010y\u001a\u00020\u001c2\u0006\u0010E\u001a\u00020\u000bH\u0016J\b\u0010z\u001a\u00020\u001cH\u0016J\b\u0010{\u001a\u00020>H\u0002J\u0010\u0010|\u001a\u00020\u001c2\u0006\u0010;\u001a\u00020<H\u0016J\"\u0010}\u001a\u00020>2\u0006\u0010t\u001a\u0002092\u0006\u0010y\u001a\u00020\u001c2\b\u0010E\u001a\u0004\u0018\u00010\u000bH\u0016J\u0011\u0010~\u001a\u00020\u001c2\u0007\u0010\u007f\u001a\u00030\u0080\u0001H\u0016J\u0011\u0010\u0081\u0001\u001a\u00020\u001c2\u0006\u0010;\u001a\u00020<H\u0016J\u0011\u0010\u0082\u0001\u001a\u00020\u001c2\u0006\u0010;\u001a\u00020<H\u0016J\u0012\u0010\u0083\u0001\u001a\u00020>2\u0007\u0010\u0084\u0001\u001a\u000209H\u0016J\u001a\u0010\u0085\u0001\u001a\u00020\u001c2\u0006\u0010I\u001a\u00020\u001c2\u0007\u0010\u0086\u0001\u001a\u00020\u001cH\u0016J\u001d\u0010\u0087\u0001\u001a\u00020>2\u0007\u0010\u0088\u0001\u001a\u00020\r2\t\u0010\u0089\u0001\u001a\u0004\u0018\u000105H\u0016J\t\u0010\u008a\u0001\u001a\u00020\u001cH\u0016J\t\u0010\u008b\u0001\u001a\u00020\u001cH\u0016J\t\u0010\u008c\u0001\u001a\u00020\u001cH\u0016J\u0013\u0010\u008d\u0001\u001a\u00020\u001c2\b\u0010\u008e\u0001\u001a\u00030\u008f\u0001H\u0016J\t\u0010\u0090\u0001\u001a\u00020\u001cH\u0016J\t\u0010\u0091\u0001\u001a\u00020\u001cH\u0016J\t\u0010\u0092\u0001\u001a\u00020\u001cH\u0016J\t\u0010\u0093\u0001\u001a\u00020\u001cH\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010%\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030'0&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020)0&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u001cX\u0082D¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u001cX\u0082D¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082D¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0095\u0001"}, d2 = {"Lcom/neusoft/na/ocservice/greatwallsdk/GreatWallApiOctaImpl;", "Lcom/neusoft/na/ocservice/greatwallsdk/IGreatWallNavigationApi;", "Lcom/neusoft/na/common/ClientEndpointMangerCallback;", "context", "Landroid/content/Context;", "notificationListener", "Lcom/neusoft/na/ocservice/greatwallsdk/IGreateWallNotificationListener;", "(Landroid/content/Context;Lcom/neusoft/na/ocservice/greatwallsdk/IGreateWallNotificationListener;)V", "cEManger", "Lcom/neusoft/na/common/ClientEndpointManger;", "calcPlanningRouteCallBack", "Lcom/neusoft/na/ocservice/greatwallsdk/IGWRequestCallback;", "clusterSurface", "Landroid/view/Surface;", "commonNotifications", "Lapi/notifications/com/neusoft/common/commonsysapiinverse/CommonSysApiInverseNotifications;", "dayNightStatusAttr", "Lcom/neusoft/de/nts/OneCoreTest/interfaces/IGettableAttribute;", "Lapi/notifications/com/neusoft/common/commonsysapiinverse/NotifyNaviDayNightStatusChange;", "handler", "Landroid/os/Handler;", "hasUnsentMSG", "", "isServerConnect", "mServiceStatus", "motsMainBinder", "Lcom/neusoft/na/navigation/IMotsServiceInterface;", "motsMainService", "", "motsWsContext", "Lcom/neusoft/de/nts/OneCoreTest/communication/utils/OneCoreWSContextUtil;", "navigationLanguageStatus", "Lapi/structs/com/neusoft/greatwall/LanguageOnOff;", "notificationGreatWallSysApi", "Lapi/notifications/com/neusoft/greatwall/greatwallsysapiinverse/GreatWallSysApiInverseNotifications;", "notifications", "Lapi/notifications/com/neusoft/greatwall/greatwallnavapi/GreatWallNavApiNotifications;", "remoteAttributes", "", "Lcom/neusoft/de/nts/OneCoreTest/interfaces/IAttribute;", "remoteSubscriptions", "Lrx/Subscription;", "requests", "Lapi/requests/com/neusoft/greatwall/greatwallnavapi/GreatWallNavApiRequests;", "requestsCommonSysApi", "Lapi/requests/com/neusoft/common/commonsysapiinverse/CommonSysApiInverseRequests;", "requestsGreatWallSysApi", "Lapi/requests/com/neusoft/greatwall/greatwallsysapiinverse/GreatWallSysApiInverseRequests;", "responseAuthOfAsyncCall", "responseAuthOfNotifications", "serviceConnection", "Landroid/content/ServiceConnection;", "setSurfaceCallback", "Lcom/neusoft/na/ocservice/greatwallsdk/IGWSetSurfaceCallback;", "timeOutValue", "", "unknownErrorCode", "", "addPointsToFavorites", "poiData", "Lcom/neusoft/na/ocservice/greatwallsdk/bean/PoiData;", "binderMotsMainService", "", "calculateRoad", "atcionType", "endPoi", "startPoi", "wayPoints", "", "callback", "clearLog", "closeTraffic", "deleteNavigationPersonalizationData", "BeanId", "destinationInformation", "displayFavorites", "displayHistoricalDestination", "enterOverview", "exitOverview", "getCompanyAddress", "getCountryCode", "getDayNightMode", "getFavoriteList", "getFrontTraffic", "Lorg/json/JSONObject;", "getHomeAddress", "getLocation", "getLogOnOffStatus", "getMapStatus", "getNavigationLanguageSetting", "getNavigationPersonalizationData", "getNavigationStatus", "getOverviewStatus", "getScaleSize", "getTBTInfo", "getTrafficStatus", "getViewMode", "getWayPointList", "getdestinationInfo", "goCompany", "goHome", "historicalDestinations", "increaseRoutePoints", "mapToBackstage", "mapToFrontDesk", "mapToFrontDeskPrivate", "mapToMaximum", "mapToMinimize", "onServerConnect", "onServerDisconnect", "openMapWithLocation", "position", "Lcom/neusoft/na/ocservice/greatwallsdk/bean/GeoPosition;", "openSettingsMenu", "openTraffic", "peripheralSearch", "reqId", "peripheralData", "Lcom/neusoft/na/ocservice/greatwallsdk/bean/PeripheralData;", "operaType", "poiSearch", "keyWord", "queryPreferences", "reconnectionService", "removeFavoritePoints", "searchAlong", "selectRoute", "selectRouteData", "Lcom/neusoft/na/ocservice/greatwallsdk/bean/SelectRouteData;", "setCompanyAddress", "setHomeAddress", "setLogOnOffStatus", "status", "setNavigationPersonalizationData", AoapService.KEY_RESULT, "setSurface", OneCoreService.PAYLOAD_SURFACE, "onSetSurfaceCallback", "setViewMode2DDueNorthUp", "setViewMode2DHeadUp", "setViewMode3DHeadUp", "settingPreferences", "preferencesData", "Lcom/neusoft/na/ocservice/greatwallsdk/bean/PreferencesData;", "startNavigation", "stopNavigation", "zoomIn", "zoomOut", "Companion", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class GreatWallApiOctaImpl implements IGreatWallNavigationApi, ClientEndpointMangerCallback {
    private static final String CLOSE_MAINACTIVITY = "com.neusoft.na.mots.closeMainActivity";
    private static final String TAG = "NavigationApiOctaImpl";
    private ClientEndpointManger cEManger;
    private IGWRequestCallback calcPlanningRouteCallBack;
    private Surface clusterSurface;
    private CommonSysApiInverseNotifications commonNotifications;
    private final Context context;
    private IGettableAttribute<NotifyNaviDayNightStatusChange> dayNightStatusAttr;
    private final Handler handler;
    private boolean hasUnsentMSG;
    private boolean isServerConnect;
    private boolean mServiceStatus;
    private IMotsServiceInterface motsMainBinder;
    private OneCoreWSContextUtil motsWsContext;
    private GreatWallSysApiInverseNotifications notificationGreatWallSysApi;
    private final IGreateWallNotificationListener notificationListener;
    private GreatWallNavApiNotifications notifications;
    private GreatWallNavApiRequests requests;
    private CommonSysApiInverseRequests requestsCommonSysApi;
    private GreatWallSysApiInverseRequests requestsGreatWallSysApi;
    private IGWSetSurfaceCallback setSurfaceCallback;
    private final List<IAttribute<?>> remoteAttributes = new ArrayList();
    private final List<Subscription> remoteSubscriptions = new ArrayList();
    private final String responseAuthOfNotifications = "com.neusoft.navigation";
    private final String responseAuthOfAsyncCall = "com.beantechs.voice";
    private final long timeOutValue = 2000;
    private int unknownErrorCode = 10020;
    private LanguageOnOff navigationLanguageStatus = LanguageOnOff.DISABLE_NAV_LANGUAGE_SETTING;
    private final String motsMainService = "com.neusoft.na.mots.MotsMainService";
    private final ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$serviceConnection$1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) throws RemoteException {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(service, "service");
            Log.i("NavigationApiOctaImpl", "onServiceConnected");
            this.this$0.mServiceStatus = true;
            this.this$0.motsMainBinder = IMotsServiceInterface.Stub.asInterface(service);
            IMotsServiceInterface iMotsServiceInterface = this.this$0.motsMainBinder;
            if (iMotsServiceInterface != null) {
                iMotsServiceInterface.setClusterSurface(this.this$0.clusterSurface);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            Intrinsics.checkNotNullParameter(name, "name");
            Log.i("NavigationApiOctaImpl", "onServiceDisconnected");
            this.this$0.mServiceStatus = false;
            this.this$0.motsMainBinder = null;
            this.this$0.reconnectionService();
        }
    };

    /* JADX INFO: compiled from: GreatWallApiOctaImpl.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[TrafficMode.values().length];
            try {
                iArr[TrafficMode.TRAFFIC_ON.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TrafficMode.TRAFFIC_OFF.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ViewModeType.values().length];
            try {
                iArr2[ViewModeType.NORTH_UP.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[ViewModeType.HEAD_UP.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public GreatWallApiOctaImpl(Context context, IGreateWallNotificationListener iGreateWallNotificationListener) {
        Log.i(TAG, "NavigationApiOctaImpl: ");
        this.context = context;
        Intrinsics.checkNotNull(context);
        this.handler = new Handler(context.getMainLooper());
        this.notificationListener = iGreateWallNotificationListener;
        ClientEndpointManger companion = ClientEndpointManger.INSTANCE.getInstance(context);
        this.cEManger = companion;
        Intrinsics.checkNotNull(companion);
        companion.setClientEndpointMangerCallback(this);
        ClientEndpointManger clientEndpointManger = this.cEManger;
        Intrinsics.checkNotNull(clientEndpointManger);
        if (clientEndpointManger.getConnectionState() == OneCoreClientEndpoint.ConnectionState.CONNECTED) {
            ClientEndpointManger clientEndpointManger2 = this.cEManger;
            Intrinsics.checkNotNull(clientEndpointManger2);
            OneCoreWSContextUtil cWSContextUtil = clientEndpointManger2.getWsContext();
            Intrinsics.checkNotNull(cWSContextUtil);
            onServerConnect(cWSContextUtil);
        }
        binderMotsMainService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reconnectionService() {
        Log.i(TAG, "reconnectionService");
        new Thread(new Runnable() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda56
            @Override // java.lang.Runnable
            public final void run() {
                GreatWallApiOctaImpl.reconnectionService$lambda$0(this.f$0);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void reconnectionService$lambda$0(GreatWallApiOctaImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        while (!this$0.mServiceStatus) {
            Log.i(TAG, "reconnecting");
            this$0.binderMotsMainService();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private final void binderMotsMainService() {
        Log.i(TAG, "binderMotsMainService");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.neusoft.na.navigation", this.motsMainService));
        Context context = this.context;
        if (context != null) {
            context.startService(intent);
        }
        Context context2 = this.context;
        if (context2 != null) {
            context2.bindService(intent, this.serviceConnection, 1);
        }
    }

    @Override // com.neusoft.na.common.ClientEndpointMangerCallback
    public void onServerConnect(OneCoreWSContextUtil context) {
        Observable<NotifyNavigationRunningStatus> observable;
        Intrinsics.checkNotNullParameter(context, "context");
        Log.i(TAG, "onMotsConnected: isServerConnect:" + this.isServerConnect);
        if (this.isServerConnect) {
            return;
        }
        this.isServerConnect = true;
        this.motsWsContext = context;
        this.requests = new GreatWallNavApiRequests(this.motsWsContext);
        this.requestsCommonSysApi = new CommonSysApiInverseRequests(this.motsWsContext);
        this.notifications = new GreatWallNavApiNotifications(this.motsWsContext);
        this.commonNotifications = new CommonSysApiInverseNotifications(this.motsWsContext);
        this.notificationGreatWallSysApi = new GreatWallSysApiInverseNotifications(this.motsWsContext);
        this.requestsGreatWallSysApi = new GreatWallSysApiInverseRequests(this.motsWsContext);
        CommonSysApiInverseNotifications commonSysApiInverseNotifications = this.commonNotifications;
        if (commonSysApiInverseNotifications != null) {
            IAttribute<NotifyNavigationRunningStatus> iAttributeNavigationRunningStatusAttribute = commonSysApiInverseNotifications.navigationRunningStatusAttribute();
            if (iAttributeNavigationRunningStatusAttribute != null) {
                iAttributeNavigationRunningStatusAttribute.signIn();
            }
            if (iAttributeNavigationRunningStatusAttribute != null) {
                this.remoteAttributes.add(iAttributeNavigationRunningStatusAttribute);
            }
            List<Subscription> list = this.remoteSubscriptions;
            Observable<NotifyNavigationRunningStatus> observableObserveOn = (iAttributeNavigationRunningStatusAttribute == null || (observable = iAttributeNavigationRunningStatusAttribute.getObservable()) == null) ? null : observable.observeOn(Schedulers.newThread());
            Intrinsics.checkNotNull(observableObserveOn);
            final Function1<NotifyNavigationRunningStatus, Unit> function1 = new Function1<NotifyNavigationRunningStatus, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$onServerConnect$1$1

                /* JADX INFO: compiled from: GreatWallApiOctaImpl.kt */
                @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[NavAPPRunningStatus.values().length];
                        try {
                            iArr[NavAPPRunningStatus.NAV_MAINAPP_EXIT.ordinal()] = 1;
                        } catch (NoSuchFieldError unused) {
                        }
                        try {
                            iArr[NavAPPRunningStatus.NAV_MAINAPP_OPERABLE.ordinal()] = 2;
                        } catch (NoSuchFieldError unused2) {
                        }
                        try {
                            iArr[NavAPPRunningStatus.NAV_MAINAPP_STARTING.ordinal()] = 3;
                        } catch (NoSuchFieldError unused3) {
                        }
                        try {
                            iArr[NavAPPRunningStatus.NAV_SLAVEAPP_EXIT.ordinal()] = 4;
                        } catch (NoSuchFieldError unused4) {
                        }
                        try {
                            iArr[NavAPPRunningStatus.NAV_SLAVEAPP_OPERABLE.ordinal()] = 5;
                        } catch (NoSuchFieldError unused5) {
                        }
                        try {
                            iArr[NavAPPRunningStatus.NAV_SLAVEAPP_STARTING.ordinal()] = 6;
                        } catch (NoSuchFieldError unused6) {
                        }
                        try {
                            iArr[NavAPPRunningStatus.NAV_CLUSTER_STARTING.ordinal()] = 7;
                        } catch (NoSuchFieldError unused7) {
                        }
                        try {
                            iArr[NavAPPRunningStatus.NAV_CLUSTER_OPERABLE.ordinal()] = 8;
                        } catch (NoSuchFieldError unused8) {
                        }
                        try {
                            iArr[NavAPPRunningStatus.NAV_CLUSTER_EXIT.ordinal()] = 9;
                        } catch (NoSuchFieldError unused9) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyNavigationRunningStatus notifyNavigationRunningStatus) {
                    invoke2(notifyNavigationRunningStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyNavigationRunningStatus notifyNavigationRunningStatus) {
                    Log.i("NavigationApiOctaImpl", "NotifyNavigationRunningStatus:" + notifyNavigationRunningStatus.getNavigationRunningStatus());
                    if (this.this$0.setSurfaceCallback != null) {
                        NavAPPRunningStatus navigationRunningStatus = notifyNavigationRunningStatus.getNavigationRunningStatus();
                        if ((navigationRunningStatus == null ? -1 : WhenMappings.$EnumSwitchMapping$0[navigationRunningStatus.ordinal()]) != 8) {
                            return;
                        }
                        Log.i("NavigationApiOctaImpl", "NavAPPRunningStatus.NAV_CLUSTER_OPERABLE");
                        if (this.this$0.setSurfaceCallback != null) {
                            Log.i("NavigationApiOctaImpl", "NavAPPRunningStatus:onSetSurface");
                            IGWSetSurfaceCallback iGWSetSurfaceCallback = this.this$0.setSurfaceCallback;
                            if (iGWSetSurfaceCallback != null) {
                                iGWSetSurfaceCallback.onSetSurface(0);
                            }
                        }
                    }
                }
            };
            Subscription subscriptionSubscribe = observableObserveOn.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    GreatWallApiOctaImpl.onServerConnect$lambda$2$lambda$1(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe, "override fun onServerCon…)\n         }\n      }\n   }");
            list.add(subscriptionSubscribe);
        }
        GreatWallNavApiNotifications greatWallNavApiNotifications = this.notifications;
        if (greatWallNavApiNotifications != null) {
            IAttribute<NotifyGuidanceStatus> iAttributeGuidanceStatusAttribute = greatWallNavApiNotifications.guidanceStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeGuidanceStatusAttribute, "it.guidanceStatusAttribute()");
            iAttributeGuidanceStatusAttribute.signIn();
            this.remoteAttributes.add(iAttributeGuidanceStatusAttribute);
            List<Subscription> list2 = this.remoteSubscriptions;
            Observable<NotifyGuidanceStatus> observableObserveOn2 = iAttributeGuidanceStatusAttribute.getObservable().observeOn(Schedulers.newThread());
            final Function1<NotifyGuidanceStatus, Unit> function12 = new Function1<NotifyGuidanceStatus, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$onServerConnect$2$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyGuidanceStatus notifyGuidanceStatus) throws JSONException {
                    invoke2(notifyGuidanceStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyGuidanceStatus notifyGuidanceStatus) throws JSONException {
                    Log.i("NavigationApiOctaImpl", "NotifyGuidanceStatus");
                    StatusData statusData = new StatusData();
                    Integer value = notifyGuidanceStatus.getGuidanceStatus().getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "it.guidanceStatus.value");
                    statusData.setStatus(value.intValue());
                    String strJsonEncoding = GreatWallApiTool.jsonEncoding(1, this.this$0.responseAuthOfNotifications, GreatWallApiTool.jsonEncoding(statusData), 20000, 0, 0);
                    IGreateWallNotificationListener iGreateWallNotificationListener = this.this$0.notificationListener;
                    Intrinsics.checkNotNull(iGreateWallNotificationListener);
                    iGreateWallNotificationListener.onResult(strJsonEncoding);
                }
            };
            Subscription subscriptionSubscribe2 = observableObserveOn2.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda8
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    GreatWallApiOctaImpl.onServerConnect$lambda$4$lambda$3(function12, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe2, "override fun onServerCon…)\n         }\n      }\n   }");
            list2.add(subscriptionSubscribe2);
        }
        GreatWallNavApiNotifications greatWallNavApiNotifications2 = this.notifications;
        if (greatWallNavApiNotifications2 != null) {
            IAttribute<NotifyLaneInfoStatus> iAttributeLaneInfoStatusAttribute = greatWallNavApiNotifications2.laneInfoStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeLaneInfoStatusAttribute, "it.laneInfoStatusAttribute()");
            iAttributeLaneInfoStatusAttribute.signIn();
            this.remoteAttributes.add(iAttributeLaneInfoStatusAttribute);
            List<Subscription> list3 = this.remoteSubscriptions;
            Observable<NotifyLaneInfoStatus> observableObserveOn3 = iAttributeLaneInfoStatusAttribute.getObservable().observeOn(Schedulers.newThread());
            final Function1<NotifyLaneInfoStatus, Unit> function13 = new Function1<NotifyLaneInfoStatus, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$onServerConnect$3$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyLaneInfoStatus notifyLaneInfoStatus) throws JSONException {
                    invoke2(notifyLaneInfoStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyLaneInfoStatus notifyLaneInfoStatus) throws JSONException {
                    Log.i("NavigationApiOctaImpl", "NotifyLaneInfoStatus");
                    LaneInfoStatusData laneInfoStatusData = new LaneInfoStatusData();
                    Boolean display = notifyLaneInfoStatus.getLaneInfoStatus().getDisplay();
                    Intrinsics.checkNotNullExpressionValue(display, "it.laneInfoStatus.display");
                    if (display.booleanValue()) {
                        laneInfoStatusData.setDisplay(1);
                    } else {
                        laneInfoStatusData.setDisplay(0);
                    }
                    int size = notifyLaneInfoStatus.getLaneInfoStatus().getLaneIcon().size();
                    for (int i = 0; i < size; i++) {
                        LaneIconIdData laneIconIdData = new LaneIconIdData();
                        Integer num = notifyLaneInfoStatus.getLaneInfoStatus().getLaneIcon().get(i);
                        Intrinsics.checkNotNullExpressionValue(num, "it.laneInfoStatus.laneIcon[i]");
                        laneIconIdData.setLaneIconIdData(num.intValue());
                        laneInfoStatusData.getLanes().add(laneIconIdData);
                    }
                    String strJsonEncoding = GreatWallApiTool.jsonEncoding(1, this.this$0.responseAuthOfNotifications, GreatWallApiTool.jsonEncoding(laneInfoStatusData), 30000, 0, 0);
                    IGreateWallNotificationListener iGreateWallNotificationListener = this.this$0.notificationListener;
                    Intrinsics.checkNotNull(iGreateWallNotificationListener);
                    iGreateWallNotificationListener.onResult(strJsonEncoding);
                }
            };
            Subscription subscriptionSubscribe3 = observableObserveOn3.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda9
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    GreatWallApiOctaImpl.onServerConnect$lambda$6$lambda$5(function13, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe3, "override fun onServerCon…)\n         }\n      }\n   }");
            list3.add(subscriptionSubscribe3);
        }
        GreatWallNavApiNotifications greatWallNavApiNotifications3 = this.notifications;
        if (greatWallNavApiNotifications3 != null) {
            IAttribute<NotifySpeedLimitStatus> iAttributeSpeedLimitStatusAttribute = greatWallNavApiNotifications3.speedLimitStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeSpeedLimitStatusAttribute, "it.speedLimitStatusAttribute()");
            iAttributeSpeedLimitStatusAttribute.signIn();
            this.remoteAttributes.add(iAttributeSpeedLimitStatusAttribute);
            List<Subscription> list4 = this.remoteSubscriptions;
            Observable<NotifySpeedLimitStatus> observableObserveOn4 = iAttributeSpeedLimitStatusAttribute.getObservable().observeOn(Schedulers.newThread());
            final Function1<NotifySpeedLimitStatus, Unit> function14 = new Function1<NotifySpeedLimitStatus, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$onServerConnect$4$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifySpeedLimitStatus notifySpeedLimitStatus) throws JSONException {
                    invoke2(notifySpeedLimitStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifySpeedLimitStatus notifySpeedLimitStatus) throws JSONException {
                    Log.i("NavigationApiOctaImpl", "NotifySpeedLimitStatus");
                    SpeedLimitData speedLimitData = new SpeedLimitData();
                    Integer speedLimit = notifySpeedLimitStatus.getSpeedLimitStatus().getSpeedLimit();
                    Intrinsics.checkNotNullExpressionValue(speedLimit, "it.speedLimitStatus.speedLimit");
                    speedLimitData.setSpeedlimit(speedLimit.intValue());
                    String strJsonEncoding = GreatWallApiTool.jsonEncoding(1, this.this$0.responseAuthOfNotifications, GreatWallApiTool.jsonEncoding(speedLimitData), 40000, 0, 0);
                    IGreateWallNotificationListener iGreateWallNotificationListener = this.this$0.notificationListener;
                    Intrinsics.checkNotNull(iGreateWallNotificationListener);
                    iGreateWallNotificationListener.onResult(strJsonEncoding);
                }
            };
            Subscription subscriptionSubscribe4 = observableObserveOn4.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda10
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    GreatWallApiOctaImpl.onServerConnect$lambda$8$lambda$7(function14, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe4, "override fun onServerCon…)\n         }\n      }\n   }");
            list4.add(subscriptionSubscribe4);
        }
        GreatWallNavApiNotifications greatWallNavApiNotifications4 = this.notifications;
        if (greatWallNavApiNotifications4 != null) {
            IAttribute<NotifyGuidanceInfoStatus> iAttributeGuidanceInfoStatusAttribute = greatWallNavApiNotifications4.guidanceInfoStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeGuidanceInfoStatusAttribute, "it.guidanceInfoStatusAttribute()");
            iAttributeGuidanceInfoStatusAttribute.signIn();
            this.remoteAttributes.add(iAttributeGuidanceInfoStatusAttribute);
            List<Subscription> list5 = this.remoteSubscriptions;
            Observable<NotifyGuidanceInfoStatus> observableObserveOn5 = iAttributeGuidanceInfoStatusAttribute.getObservable().observeOn(Schedulers.newThread());
            final Function1<NotifyGuidanceInfoStatus, Unit> function15 = new Function1<NotifyGuidanceInfoStatus, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$onServerConnect$5$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyGuidanceInfoStatus notifyGuidanceInfoStatus) {
                    invoke2(notifyGuidanceInfoStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyGuidanceInfoStatus notifyGuidanceInfoStatus) {
                    Log.i("NavigationApiOctaImpl", "NotifyGuidanceInfoStatus");
                    GuidanceInfoStatusData guidanceInfoStatusData = new GuidanceInfoStatusData();
                    TBTData tBTData = new TBTData();
                    SecondTBTData secondTBTData = new SecondTBTData();
                    ETAData eTAData = new ETAData();
                    tBTData.setRoadName(notifyGuidanceInfoStatus.getGuidanceInfoStatus().getNextRoadName());
                    Integer turnId = notifyGuidanceInfoStatus.getGuidanceInfoStatus().getTurnId();
                    Intrinsics.checkNotNullExpressionValue(turnId, "it.guidanceInfoStatus.turnId");
                    tBTData.setTurnId(turnId.intValue());
                    Integer turnDistance = notifyGuidanceInfoStatus.getGuidanceInfoStatus().getTurnDistance();
                    Intrinsics.checkNotNullExpressionValue(turnDistance, "it.guidanceInfoStatus.turnDistance");
                    tBTData.setDistance(turnDistance.intValue());
                    tBTData.setDistanceString(notifyGuidanceInfoStatus.getGuidanceInfoStatus().getTurnDistanceStr());
                    tBTData.setExit(notifyGuidanceInfoStatus.getGuidanceInfoStatus().getExitNumOfHighway());
                    guidanceInfoStatusData.setFirstTBT(tBTData);
                    secondTBTData.setRoadName("");
                    Integer nextTurnId = notifyGuidanceInfoStatus.getGuidanceInfoStatus().getNextTurnId();
                    Intrinsics.checkNotNullExpressionValue(nextTurnId, "it.guidanceInfoStatus.nextTurnId");
                    secondTBTData.setTurnId(nextTurnId.intValue());
                    Integer nextTurnDistance = notifyGuidanceInfoStatus.getGuidanceInfoStatus().getNextTurnDistance();
                    Intrinsics.checkNotNullExpressionValue(nextTurnDistance, "it.guidanceInfoStatus.nextTurnDistance");
                    secondTBTData.setDistance(nextTurnDistance.intValue());
                    secondTBTData.setDistanceString(notifyGuidanceInfoStatus.getGuidanceInfoStatus().getNextTurnDistanceStr());
                    guidanceInfoStatusData.setSecondTBT(secondTBTData);
                    Integer remainingTime = notifyGuidanceInfoStatus.getGuidanceInfoStatus().getRemainingTime();
                    Intrinsics.checkNotNullExpressionValue(remainingTime, "it.guidanceInfoStatus.remainingTime");
                    eTAData.setRemainTime(remainingTime.intValue());
                    Integer remainingDistance = notifyGuidanceInfoStatus.getGuidanceInfoStatus().getRemainingDistance();
                    Intrinsics.checkNotNullExpressionValue(remainingDistance, "it.guidanceInfoStatus.remainingDistance");
                    eTAData.setRemainDistance(remainingDistance.intValue());
                    Integer remainingBatteryPower = notifyGuidanceInfoStatus.getGuidanceInfoStatus().getRemainingBatteryPower();
                    Intrinsics.checkNotNullExpressionValue(remainingBatteryPower, "it.guidanceInfoStatus.remainingBatteryPower");
                    eTAData.setRemianBatteryPower(remainingBatteryPower.intValue());
                    eTAData.setRemainTimeString(notifyGuidanceInfoStatus.getGuidanceInfoStatus().getRemainingTimeStr());
                    eTAData.setArrivalTimeString(notifyGuidanceInfoStatus.getGuidanceInfoStatus().getArrivalTimeStr());
                    eTAData.setRemainDistanceString(notifyGuidanceInfoStatus.getGuidanceInfoStatus().getRemainingDistanceStr());
                    eTAData.setRemianBatteryPowerString(notifyGuidanceInfoStatus.getGuidanceInfoStatus().getRemainingBatteryPowerStr());
                    guidanceInfoStatusData.setETA(eTAData);
                    IGreateWallNotificationListener iGreateWallNotificationListener = this.this$0.notificationListener;
                    Intrinsics.checkNotNull(iGreateWallNotificationListener);
                    iGreateWallNotificationListener.onResult(GreatWallApiTool.jsonEncoding(2, this.this$0.responseAuthOfNotifications, GreatWallApiTool.jsonEncoding(guidanceInfoStatusData), 20000, 0, 0));
                }
            };
            Subscription subscriptionSubscribe5 = observableObserveOn5.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda12
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    GreatWallApiOctaImpl.onServerConnect$lambda$10$lambda$9(function15, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe5, "override fun onServerCon…)\n         }\n      }\n   }");
            list5.add(subscriptionSubscribe5);
        }
        GreatWallNavApiNotifications greatWallNavApiNotifications5 = this.notifications;
        if (greatWallNavApiNotifications5 != null) {
            IAttribute<NotifyNotifyHeadingStatus> iAttributeNotifyHeadingStatusAttribute = greatWallNavApiNotifications5.notifyHeadingStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeNotifyHeadingStatusAttribute, "it.notifyHeadingStatusAttribute()");
            iAttributeNotifyHeadingStatusAttribute.signIn();
            this.remoteAttributes.add(iAttributeNotifyHeadingStatusAttribute);
            List<Subscription> list6 = this.remoteSubscriptions;
            Observable<NotifyNotifyHeadingStatus> observableObserveOn6 = iAttributeNotifyHeadingStatusAttribute.getObservable().observeOn(Schedulers.newThread());
            final Function1<NotifyNotifyHeadingStatus, Unit> function16 = new Function1<NotifyNotifyHeadingStatus, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$onServerConnect$6$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyNotifyHeadingStatus notifyNotifyHeadingStatus) throws JSONException {
                    invoke2(notifyNotifyHeadingStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyNotifyHeadingStatus notifyNotifyHeadingStatus) throws JSONException {
                    Log.i("NavigationApiOctaImpl", "NotifyHeadingStatus");
                    StatusData statusData = new StatusData();
                    Integer value = notifyNotifyHeadingStatus.getNotifyHeadingStatus().getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "it.notifyHeadingStatus.value");
                    statusData.setStatus(value.intValue());
                    String strJsonEncoding = GreatWallApiTool.jsonEncoding(1, this.this$0.responseAuthOfNotifications, GreatWallApiTool.jsonEncoding(statusData), 10000, 0, 0);
                    IGreateWallNotificationListener iGreateWallNotificationListener = this.this$0.notificationListener;
                    Intrinsics.checkNotNull(iGreateWallNotificationListener);
                    iGreateWallNotificationListener.onResult(strJsonEncoding);
                }
            };
            Subscription subscriptionSubscribe6 = observableObserveOn6.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda13
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    GreatWallApiOctaImpl.onServerConnect$lambda$12$lambda$11(function16, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe6, "override fun onServerCon…)\n         }\n      }\n   }");
            list6.add(subscriptionSubscribe6);
        }
        GreatWallNavApiNotifications greatWallNavApiNotifications6 = this.notifications;
        if (greatWallNavApiNotifications6 != null) {
            IAttribute<NotifySwitchNavigationLanguageSetting> iAttributeSwitchNavigationLanguageSettingAttribute = greatWallNavApiNotifications6.switchNavigationLanguageSettingAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeSwitchNavigationLanguageSettingAttribute, "it.switchNavigationLanguageSettingAttribute()");
            iAttributeSwitchNavigationLanguageSettingAttribute.signIn();
            this.remoteAttributes.add(iAttributeSwitchNavigationLanguageSettingAttribute);
            List<Subscription> list7 = this.remoteSubscriptions;
            Observable<NotifySwitchNavigationLanguageSetting> observableObserveOn7 = iAttributeSwitchNavigationLanguageSettingAttribute.getObservable().observeOn(Schedulers.newThread());
            final Function1<NotifySwitchNavigationLanguageSetting, Unit> function17 = new Function1<NotifySwitchNavigationLanguageSetting, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$onServerConnect$7$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifySwitchNavigationLanguageSetting notifySwitchNavigationLanguageSetting) throws JSONException {
                    invoke2(notifySwitchNavigationLanguageSetting);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifySwitchNavigationLanguageSetting notifySwitchNavigationLanguageSetting) throws JSONException {
                    Log.i("NavigationApiOctaImpl", "NotifySwitchNavigationLanguageSetting");
                    StatusData statusData = new StatusData();
                    GreatWallApiOctaImpl greatWallApiOctaImpl = this.this$0;
                    LanguageOnOff switchNavigationLanguageSetting = notifySwitchNavigationLanguageSetting.getSwitchNavigationLanguageSetting();
                    Intrinsics.checkNotNullExpressionValue(switchNavigationLanguageSetting, "it.switchNavigationLanguageSetting");
                    greatWallApiOctaImpl.navigationLanguageStatus = switchNavigationLanguageSetting;
                    Integer value = notifySwitchNavigationLanguageSetting.getSwitchNavigationLanguageSetting().getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "it.switchNavigationLanguageSetting.value");
                    statusData.setStatus(value.intValue());
                    String strJsonEncoding = GreatWallApiTool.jsonEncoding(2, this.this$0.responseAuthOfNotifications, GreatWallApiTool.jsonEncoding(statusData), 90000, 0, 0);
                    IGreateWallNotificationListener iGreateWallNotificationListener = this.this$0.notificationListener;
                    Intrinsics.checkNotNull(iGreateWallNotificationListener);
                    iGreateWallNotificationListener.onResult(strJsonEncoding);
                }
            };
            Subscription subscriptionSubscribe7 = observableObserveOn7.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda14
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    GreatWallApiOctaImpl.onServerConnect$lambda$14$lambda$13(function17, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe7, "override fun onServerCon…)\n         }\n      }\n   }");
            list7.add(subscriptionSubscribe7);
        }
        GreatWallNavApiNotifications greatWallNavApiNotifications7 = this.notifications;
        if (greatWallNavApiNotifications7 != null) {
            IAttribute<NotifyReportLocationInfo> iAttributeReportLocationInfoAttribute = greatWallNavApiNotifications7.reportLocationInfoAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeReportLocationInfoAttribute, "it.reportLocationInfoAttribute()");
            iAttributeReportLocationInfoAttribute.signIn();
            this.remoteAttributes.add(iAttributeReportLocationInfoAttribute);
            List<Subscription> list8 = this.remoteSubscriptions;
            Observable<NotifyReportLocationInfo> observableObserveOn8 = iAttributeReportLocationInfoAttribute.getObservable().observeOn(Schedulers.newThread());
            final Function1<NotifyReportLocationInfo, Unit> function18 = new Function1<NotifyReportLocationInfo, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$onServerConnect$8$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyReportLocationInfo notifyReportLocationInfo) {
                    invoke2(notifyReportLocationInfo);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyReportLocationInfo notifyReportLocationInfo) {
                    Log.i("NavigationApiOctaImpl", "NotifyReportLocationInfo,headingangle=" + ((float) notifyReportLocationInfo.getReportLocationInfo().getHeading().doubleValue()));
                    PoiData poiData = new PoiData();
                    Float accuracy = notifyReportLocationInfo.getReportLocationInfo().getAccuracy();
                    Intrinsics.checkNotNullExpressionValue(accuracy, "it.reportLocationInfo.accuracy");
                    poiData.setAccuracy(accuracy.floatValue());
                    poiData.setAdcode(notifyReportLocationInfo.getReportLocationInfo().getAdCode());
                    poiData.setAddress(notifyReportLocationInfo.getReportLocationInfo().getAddress());
                    poiData.setAreacode(notifyReportLocationInfo.getReportLocationInfo().getCityCode());
                    poiData.setCity(notifyReportLocationInfo.getReportLocationInfo().getCity());
                    poiData.setCityadcode(notifyReportLocationInfo.getReportLocationInfo().getCityCode());
                    poiData.setDistrict(notifyReportLocationInfo.getReportLocationInfo().getDistrict());
                    poiData.setDistrictadcode(notifyReportLocationInfo.getReportLocationInfo().getDistrictAdCode());
                    Double latitude = notifyReportLocationInfo.getReportLocationInfo().getGeoPosition().getLatitude();
                    Intrinsics.checkNotNullExpressionValue(latitude, "it.reportLocationInfo.geoPosition.latitude");
                    poiData.setLatitude(latitude.doubleValue());
                    Double longitude = notifyReportLocationInfo.getReportLocationInfo().getGeoPosition().getLongitude();
                    Intrinsics.checkNotNullExpressionValue(longitude, "it.reportLocationInfo.geoPosition.longitude");
                    poiData.setLongitude(longitude.doubleValue());
                    poiData.setHeadingangle((float) notifyReportLocationInfo.getReportLocationInfo().getHeading().doubleValue());
                    poiData.setName(notifyReportLocationInfo.getReportLocationInfo().getName());
                    poiData.setPoiId(notifyReportLocationInfo.getReportLocationInfo().getPoiId());
                    poiData.setProvince(notifyReportLocationInfo.getReportLocationInfo().getProvince());
                    poiData.setProvinceadcode(notifyReportLocationInfo.getReportLocationInfo().getProvinceAdCode());
                    IGreateWallNotificationListener iGreateWallNotificationListener = this.this$0.notificationListener;
                    Intrinsics.checkNotNull(iGreateWallNotificationListener);
                    iGreateWallNotificationListener.onResult(GreatWallApiTool.jsonEncoding(1, this.this$0.responseAuthOfNotifications, GreatWallApiTool.jsonEncoding(poiData), 60000, 0, 0));
                }
            };
            Subscription subscriptionSubscribe8 = observableObserveOn8.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda15
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    GreatWallApiOctaImpl.onServerConnect$lambda$16$lambda$15(function18, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe8, "override fun onServerCon…)\n         }\n      }\n   }");
            list8.add(subscriptionSubscribe8);
        }
        GreatWallSysApiInverseNotifications greatWallSysApiInverseNotifications = this.notificationGreatWallSysApi;
        if (greatWallSysApiInverseNotifications != null) {
            IAttribute<NotifyTunnelStatus> iAttributeTunnelStatusAttribute = greatWallSysApiInverseNotifications.tunnelStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeTunnelStatusAttribute, "it.tunnelStatusAttribute()");
            iAttributeTunnelStatusAttribute.signIn();
            this.remoteAttributes.add(iAttributeTunnelStatusAttribute);
            List<Subscription> list9 = this.remoteSubscriptions;
            Observable<NotifyTunnelStatus> observableObserveOn9 = iAttributeTunnelStatusAttribute.getObservable().observeOn(Schedulers.newThread());
            final Function1<NotifyTunnelStatus, Unit> function19 = new Function1<NotifyTunnelStatus, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$onServerConnect$9$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyTunnelStatus notifyTunnelStatus) throws JSONException {
                    invoke2(notifyTunnelStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyTunnelStatus notifyTunnelStatus) throws JSONException {
                    Log.i("NavigationApiOctaImpl", "NotifyTunnelStatus");
                    StatusData statusData = new StatusData();
                    Integer value = notifyTunnelStatus.getTunnelStatus().getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "it.tunnelStatus.value");
                    statusData.setStatus(value.intValue());
                    String strJsonEncoding = GreatWallApiTool.jsonEncoding(statusData);
                    IGreateWallNotificationListener iGreateWallNotificationListener = this.this$0.notificationListener;
                    Intrinsics.checkNotNull(iGreateWallNotificationListener);
                    iGreateWallNotificationListener.onResult(GreatWallApiTool.jsonEncoding(3, this.this$0.responseAuthOfNotifications, strJsonEncoding, 20000, 0, 0));
                }
            };
            Subscription subscriptionSubscribe9 = observableObserveOn9.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda16
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    GreatWallApiOctaImpl.onServerConnect$lambda$18$lambda$17(function19, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe9, "override fun onServerCon…)\n         }\n      }\n   }");
            list9.add(subscriptionSubscribe9);
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications2 = this.commonNotifications;
        if (commonSysApiInverseNotifications2 != null) {
            IAttribute<NotifyNaviDayNightStatusChange> iAttributeNaviDayNightStatusChangeAttribute = commonSysApiInverseNotifications2.naviDayNightStatusChangeAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeNaviDayNightStatusChangeAttribute, "it.naviDayNightStatusChangeAttribute()");
            IAttribute<NotifyNaviDayNightStatusChange> iAttribute = iAttributeNaviDayNightStatusChangeAttribute;
            iAttribute.signIn();
            this.dayNightStatusAttr = iAttribute;
            this.remoteAttributes.add(iAttribute);
            List<Subscription> list10 = this.remoteSubscriptions;
            Intrinsics.checkNotNull(iAttribute);
            Observable<NotifyNaviDayNightStatusChange> observable2 = iAttribute.getObservable();
            final Function1<NotifyNaviDayNightStatusChange, Unit> function110 = new Function1<NotifyNaviDayNightStatusChange, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$onServerConnect$10$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyNaviDayNightStatusChange notifyNaviDayNightStatusChange) {
                    invoke2(notifyNaviDayNightStatusChange);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyNaviDayNightStatusChange notifyNaviDayNightStatusChange) {
                    ModeData modeData = new ModeData();
                    if (notifyNaviDayNightStatusChange.getNaviDayNightStatusChange() == NaviDayNightStatus.DAYNIGHT_STATUS_DAY) {
                        modeData.setMode(1);
                    } else {
                        modeData.setMode(0);
                    }
                    Log.i("NavigationApiOctaImpl", "naviDayNightStatusChangeAttribute:" + notifyNaviDayNightStatusChange.getNaviDayNightStatusChange().getValue());
                    IGreateWallNotificationListener iGreateWallNotificationListener = this.this$0.notificationListener;
                    Intrinsics.checkNotNull(iGreateWallNotificationListener);
                    iGreateWallNotificationListener.onResult(GreatWallApiTool.jsonEncoding(3, this.this$0.responseAuthOfNotifications, GreatWallApiTool.jsonEncoding(modeData), 10000, 1, 0));
                    StringBuilder sb = new StringBuilder("dayNightStatusAttr!!.value = ");
                    IGettableAttribute iGettableAttribute = this.this$0.dayNightStatusAttr;
                    Intrinsics.checkNotNull(iGettableAttribute);
                    Log.i("NavigationApiOctaImpl", sb.append(((NotifyNaviDayNightStatusChange) iGettableAttribute.getValue()).getNaviDayNightStatusChange().getValue()).toString());
                }
            };
            Subscription subscriptionSubscribe10 = observable2.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda17
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    GreatWallApiOctaImpl.onServerConnect$lambda$20$lambda$19(function110, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe10, "override fun onServerCon…)\n         }\n      }\n   }");
            list10.add(subscriptionSubscribe10);
        }
        CommonSysApiInverseNotifications commonSysApiInverseNotifications3 = this.commonNotifications;
        if (commonSysApiInverseNotifications3 != null) {
            IAttribute<NotifyNavigationActivityStatus> iAttributeNavigationActivityStatusAttribute = commonSysApiInverseNotifications3.navigationActivityStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeNavigationActivityStatusAttribute, "it.navigationActivityStatusAttribute()");
            iAttributeNavigationActivityStatusAttribute.signIn();
            this.remoteAttributes.add(iAttributeNavigationActivityStatusAttribute);
            List<Subscription> list11 = this.remoteSubscriptions;
            Observable<NotifyNavigationActivityStatus> observableObserveOn10 = iAttributeNavigationActivityStatusAttribute.getObservable().observeOn(Schedulers.newThread());
            final GreatWallApiOctaImpl$onServerConnect$11$1 greatWallApiOctaImpl$onServerConnect$11$1 = new Function1<NotifyNavigationActivityStatus, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$onServerConnect$11$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyNavigationActivityStatus notifyNavigationActivityStatus) {
                    invoke2(notifyNavigationActivityStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyNavigationActivityStatus notifyNavigationActivityStatus) {
                    Log.i("NavigationApiOctaImpl", "NotifyNavigationRunningStatus:" + notifyNavigationActivityStatus.getNavigationActivityStatus().getValue());
                }
            };
            Subscription subscriptionSubscribe11 = observableObserveOn10.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda2
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    GreatWallApiOctaImpl.onServerConnect$lambda$22$lambda$21(greatWallApiOctaImpl$onServerConnect$11$1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe11, "attr.observable.observeO…atus.value)\n            }");
            list11.add(subscriptionSubscribe11);
        }
        GreatWallSysApiInverseNotifications greatWallSysApiInverseNotifications2 = this.notificationGreatWallSysApi;
        if (greatWallSysApiInverseNotifications2 != null) {
            IAttribute<NotifyCongestedRoadStatus> iAttributeCongestedRoadStatusAttribute = greatWallSysApiInverseNotifications2.congestedRoadStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeCongestedRoadStatusAttribute, "it.congestedRoadStatusAttribute()");
            iAttributeCongestedRoadStatusAttribute.signIn();
            this.remoteAttributes.add(iAttributeCongestedRoadStatusAttribute);
            List<Subscription> list12 = this.remoteSubscriptions;
            Observable<NotifyCongestedRoadStatus> observableObserveOn11 = iAttributeCongestedRoadStatusAttribute.getObservable().observeOn(Schedulers.newThread());
            final Function1<NotifyCongestedRoadStatus, Unit> function111 = new Function1<NotifyCongestedRoadStatus, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$onServerConnect$12$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyCongestedRoadStatus notifyCongestedRoadStatus) throws JSONException {
                    invoke2(notifyCongestedRoadStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyCongestedRoadStatus notifyCongestedRoadStatus) throws JSONException {
                    Log.i("NavigationApiOctaImpl", "NotifyCongestedRoadStatus");
                    StatusData statusData = new StatusData();
                    Integer value = notifyCongestedRoadStatus.getCongestedRoadStatus().getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "it.congestedRoadStatus.value");
                    statusData.setStatus(value.intValue());
                    String strJsonEncoding = GreatWallApiTool.jsonEncoding(statusData);
                    IGreateWallNotificationListener iGreateWallNotificationListener = this.this$0.notificationListener;
                    Intrinsics.checkNotNull(iGreateWallNotificationListener);
                    iGreateWallNotificationListener.onResult(GreatWallApiTool.jsonEncoding(4, this.this$0.responseAuthOfNotifications, strJsonEncoding, 20000, 0, 0));
                }
            };
            Subscription subscriptionSubscribe12 = observableObserveOn11.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda3
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    GreatWallApiOctaImpl.onServerConnect$lambda$24$lambda$23(function111, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe12, "override fun onServerCon…)\n         }\n      }\n   }");
            list12.add(subscriptionSubscribe12);
        }
        GreatWallNavApiNotifications greatWallNavApiNotifications8 = this.notifications;
        if (greatWallNavApiNotifications8 != null) {
            IAttribute<NotifyCalcPlanningRouteStatus> iAttributeCalcPlanningRouteStatusAttribute = greatWallNavApiNotifications8.calcPlanningRouteStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeCalcPlanningRouteStatusAttribute, "it.calcPlanningRouteStatusAttribute()");
            iAttributeCalcPlanningRouteStatusAttribute.signIn();
            this.remoteAttributes.add(iAttributeCalcPlanningRouteStatusAttribute);
            List<Subscription> list13 = this.remoteSubscriptions;
            Observable<NotifyCalcPlanningRouteStatus> observableObserveOn12 = iAttributeCalcPlanningRouteStatusAttribute.getObservable().observeOn(Schedulers.newThread());
            final Function1<NotifyCalcPlanningRouteStatus, Unit> function112 = new Function1<NotifyCalcPlanningRouteStatus, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$onServerConnect$13$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyCalcPlanningRouteStatus notifyCalcPlanningRouteStatus) throws JSONException {
                    invoke2(notifyCalcPlanningRouteStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyCalcPlanningRouteStatus notifyCalcPlanningRouteStatus) throws JSONException {
                    Log.i("NavigationApiOctaImpl", "NotifyCalcPlanningRouteStatus");
                    ResultData resultData = new ResultData();
                    Integer numOfRoute = notifyCalcPlanningRouteStatus.getCalcPlanningRouteStatus().getNumOfRoute();
                    Intrinsics.checkNotNullExpressionValue(numOfRoute, "it.calcPlanningRouteStatus.numOfRoute");
                    resultData.setResult(numOfRoute.intValue());
                    if (this.this$0.calcPlanningRouteCallBack != null) {
                        IGWRequestCallback iGWRequestCallback = this.this$0.calcPlanningRouteCallBack;
                        Intrinsics.checkNotNull(iGWRequestCallback);
                        String strJsonEncoding = GreatWallApiTool.jsonEncoding(0, this.this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(resultData), 3000, 0, 0);
                        Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "jsonEncoding(0, response…(resultData), 3000, 0, 0)");
                        iGWRequestCallback.onCallBack(strJsonEncoding);
                    }
                    this.this$0.calcPlanningRouteCallBack = null;
                }
            };
            Subscription subscriptionSubscribe13 = observableObserveOn12.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda4
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    GreatWallApiOctaImpl.onServerConnect$lambda$26$lambda$25(function112, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe13, "override fun onServerCon…)\n         }\n      }\n   }");
            list13.add(subscriptionSubscribe13);
        }
        GreatWallNavApiNotifications greatWallNavApiNotifications9 = this.notifications;
        if (greatWallNavApiNotifications9 != null) {
            IAttribute<NotifyCurrentViewInfoStatus> iAttributeCurrentViewInfoStatusAttribute = greatWallNavApiNotifications9.currentViewInfoStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeCurrentViewInfoStatusAttribute, "it.currentViewInfoStatusAttribute()");
            iAttributeCurrentViewInfoStatusAttribute.signIn();
            this.remoteAttributes.add(iAttributeCurrentViewInfoStatusAttribute);
            List<Subscription> list14 = this.remoteSubscriptions;
            Observable<NotifyCurrentViewInfoStatus> observableObserveOn13 = iAttributeCurrentViewInfoStatusAttribute.getObservable().observeOn(Schedulers.newThread());
            final Function1<NotifyCurrentViewInfoStatus, Unit> function113 = new Function1<NotifyCurrentViewInfoStatus, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$onServerConnect$14$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyCurrentViewInfoStatus notifyCurrentViewInfoStatus) {
                    invoke2(notifyCurrentViewInfoStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyCurrentViewInfoStatus notifyCurrentViewInfoStatus) {
                    Log.i("NavigationApiOctaImpl", "NotifyCurrentViewInfoStatus");
                    ViewIdData viewIdData = new ViewIdData();
                    viewIdData.setViewId(notifyCurrentViewInfoStatus.getCurrentViewInfoStatus().getViewInfo());
                    IGreateWallNotificationListener iGreateWallNotificationListener = this.this$0.notificationListener;
                    Intrinsics.checkNotNull(iGreateWallNotificationListener);
                    iGreateWallNotificationListener.onResult(GreatWallApiTool.jsonEncoding(1, this.this$0.responseAuthOfNotifications, GreatWallApiTool.jsonEncoding(viewIdData), 100000, 0, 0));
                }
            };
            Subscription subscriptionSubscribe14 = observableObserveOn13.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda5
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    GreatWallApiOctaImpl.onServerConnect$lambda$28$lambda$27(function113, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe14, "override fun onServerCon…)\n         }\n      }\n   }");
            list14.add(subscriptionSubscribe14);
        }
        GreatWallNavApiNotifications greatWallNavApiNotifications10 = this.notifications;
        if (greatWallNavApiNotifications10 != null) {
            IAttribute<NotifyCountryChangedStatus> iAttributeCountryChangedStatusAttribute = greatWallNavApiNotifications10.countryChangedStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeCountryChangedStatusAttribute, "it.countryChangedStatusAttribute()");
            iAttributeCountryChangedStatusAttribute.signIn();
            this.remoteAttributes.add(iAttributeCountryChangedStatusAttribute);
            List<Subscription> list15 = this.remoteSubscriptions;
            Observable<NotifyCountryChangedStatus> observableObserveOn14 = iAttributeCountryChangedStatusAttribute.getObservable().observeOn(Schedulers.newThread());
            final Function1<NotifyCountryChangedStatus, Unit> function114 = new Function1<NotifyCountryChangedStatus, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$onServerConnect$15$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyCountryChangedStatus notifyCountryChangedStatus) throws JSONException {
                    invoke2(notifyCountryChangedStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyCountryChangedStatus notifyCountryChangedStatus) throws JSONException {
                    Log.i("NavigationApiOctaImpl", "NotifyCountryChangedStatus it.countryChangedStatus.country_ISO3166_1_alpha3 == " + notifyCountryChangedStatus.getCountryChangedStatus().getCountry_ISO3166_1_alpha3());
                    JSONObject jSONObject = new JSONObject(GreatWallApiTool.jsonEncoding(5, this.this$0.responseAuthOfNotifications, 20000, 0, 0));
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("countryCode", notifyCountryChangedStatus.getCountryChangedStatus().getCountry_ISO3166_1_alpha3());
                    jSONObject.put(JsonKey.RESULTS_DATA, jSONObject2);
                    IGreateWallNotificationListener iGreateWallNotificationListener = this.this$0.notificationListener;
                    if (iGreateWallNotificationListener != null) {
                        iGreateWallNotificationListener.onResult(jSONObject.toString());
                    }
                }
            };
            Subscription subscriptionSubscribe15 = observableObserveOn14.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda6
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    GreatWallApiOctaImpl.onServerConnect$lambda$30$lambda$29(function114, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe15, "override fun onServerCon…)\n         }\n      }\n   }");
            list15.add(subscriptionSubscribe15);
        }
        GreatWallNavApiNotifications greatWallNavApiNotifications11 = this.notifications;
        if (greatWallNavApiNotifications11 != null) {
            IAttribute<NotifyDeadReckoningExtendStatus> iAttributeDeadReckoningExtendStatusAttribute = greatWallNavApiNotifications11.deadReckoningExtendStatusAttribute();
            Intrinsics.checkNotNullExpressionValue(iAttributeDeadReckoningExtendStatusAttribute, "it.deadReckoningExtendStatusAttribute()");
            iAttributeDeadReckoningExtendStatusAttribute.signIn();
            this.remoteAttributes.add(iAttributeDeadReckoningExtendStatusAttribute);
            List<Subscription> list16 = this.remoteSubscriptions;
            Observable<NotifyDeadReckoningExtendStatus> observableObserveOn15 = iAttributeDeadReckoningExtendStatusAttribute.getObservable().observeOn(Schedulers.newThread());
            final Function1<NotifyDeadReckoningExtendStatus, Unit> function115 = new Function1<NotifyDeadReckoningExtendStatus, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$onServerConnect$16$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(NotifyDeadReckoningExtendStatus notifyDeadReckoningExtendStatus) throws JSONException {
                    invoke2(notifyDeadReckoningExtendStatus);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(NotifyDeadReckoningExtendStatus notifyDeadReckoningExtendStatus) throws JSONException {
                    Log.i("NavigationApiOctaImpl", "NotifyDeadReckoningExtendStatus");
                    JSONObject jSONObject = new JSONObject(GreatWallApiTool.jsonEncoding(1, this.this$0.responseAuthOfNotifications, 70000, 0, 0));
                    JSONObject jSONObject2 = new JSONObject();
                    Double altitude = notifyDeadReckoningExtendStatus.getDeadReckoningExtendStatus().getAltitude();
                    Intrinsics.checkNotNullExpressionValue(altitude, "it.deadReckoningExtendStatus.altitude");
                    jSONObject2.put("altitude", altitude.doubleValue());
                    jSONObject2.put("slope", notifyDeadReckoningExtendStatus.getDeadReckoningExtendStatus().getSlope());
                    Double heading = notifyDeadReckoningExtendStatus.getDeadReckoningExtendStatus().getHeading();
                    Intrinsics.checkNotNullExpressionValue(heading, "it.deadReckoningExtendStatus.heading");
                    jSONObject2.put("heading", heading.doubleValue());
                    jSONObject.put(JsonKey.RESULTS_DATA, jSONObject2);
                    IGreateWallNotificationListener iGreateWallNotificationListener = this.this$0.notificationListener;
                    if (iGreateWallNotificationListener != null) {
                        iGreateWallNotificationListener.onResult(jSONObject.toString());
                    }
                }
            };
            Subscription subscriptionSubscribe16 = observableObserveOn15.subscribe(new Action1() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda7
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    GreatWallApiOctaImpl.onServerConnect$lambda$32$lambda$31(function115, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe16, "override fun onServerCon…)\n         }\n      }\n   }");
            list16.add(subscriptionSubscribe16);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$2$lambda$1(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$4$lambda$3(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$6$lambda$5(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$8$lambda$7(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$10$lambda$9(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$12$lambda$11(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$14$lambda$13(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$16$lambda$15(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$18$lambda$17(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$20$lambda$19(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$22$lambda$21(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$24$lambda$23(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$26$lambda$25(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$28$lambda$27(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$30$lambda$29(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServerConnect$lambda$32$lambda$31(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // com.neusoft.na.common.ClientEndpointMangerCallback
    public void onServerDisconnect() {
        Log.i(TAG, "onMotsDisconnect: ");
        this.isServerConnect = false;
        if (this.motsWsContext != null) {
            this.motsWsContext = null;
            this.requests = null;
            this.requestsCommonSysApi = null;
            this.notifications = null;
            Iterator<IAttribute<?>> it = this.remoteAttributes.iterator();
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v8, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r9v2, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String selectRoute(SelectRouteData selectRouteData) throws JSONException {
        Intrinsics.checkNotNullParameter(selectRouteData, "selectRouteData");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        int isStartNavi = selectRouteData.getIsStartNavi();
        boolean z = isStartNavi != 0 && isStartNavi == 1;
        if (this.requests != null) {
            mapToFrontDeskPrivate();
            GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestHighlightedRoute(new ReqRequestHighlightedRoute(Integer.valueOf(selectRouteData.getSelectRoute()), Boolean.valueOf(z))).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda38
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.selectRoute$lambda$33(objectRef, this, objectRef2, (ResRequestHighlightedRoute) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                Log.i(TAG, "mLatch await timeout.");
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, 3000, 0, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response…000, 0, unknownErrorCode)");
                objectRef.element = JsonEncoding;
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, 3000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…000, 0, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void selectRoute$lambda$33(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResRequestHighlightedRoute resRequestHighlightedRoute) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resRequestHighlightedRoute.getMessageType() == EMessageType.Result) {
            String str = "{totalRouteCount:" + resRequestHighlightedRoute.getArguments().getTotalRouteCount() + '}';
            String str2 = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resRequestHighlightedRoute.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(1, str2, str, 3000, 0, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               val cou…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this$0.responseAuthOfAsyncCall, 3000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r9v11, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String settingPreferences(PreferencesData preferencesData) throws JSONException {
        RoutePreference routePreference;
        Intrinsics.checkNotNullParameter(preferencesData, "preferencesData");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        switch (preferencesData.getRoutePreference()) {
            case 10:
                routePreference = RoutePreference.RECOMMENDATION;
                break;
            case 11:
                routePreference = RoutePreference.TIME_PRIORITY;
                break;
            case 12:
                routePreference = RoutePreference.DISTANCE_PRIORITY;
                break;
            case 13:
                routePreference = RoutePreference.AVOID_CONGESTION;
                break;
            case 14:
                routePreference = RoutePreference.HIGHWAY_PRIORITY;
                break;
            case 15:
                routePreference = RoutePreference.AVOID_HIGHWAY;
                break;
            case 16:
                routePreference = RoutePreference.ECONOMIC;
                break;
            default:
                routePreference = RoutePreference.RECOMMENDATION;
                break;
        }
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestSetRoutePreference(new ReqRequestSetRoutePreference(routePreference)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda29
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.settingPreferences$lambda$34(objectRef, this, objectRef2, (ResRequestSetRoutePreference) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, 3000, 0, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(2, response…000, 0, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, 3000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(2, response…000, 0, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void settingPreferences$lambda$34(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResRequestSetRoutePreference resRequestSetRoutePreference) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resRequestSetRoutePreference.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resRequestSetRoutePreference.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(2, str, 3000, 0, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this$0.responseAuthOfAsyncCall, 3000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v6, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String queryPreferences() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        RoutePreferenceData routePreferenceData = new RoutePreferenceData();
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestGetRoutePreference(new ReqGetRoutePreference()).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda35
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.queryPreferences$lambda$35(objectRef, this, objectRef2, (ResGetRoutePreference) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(routePreferenceData), 3000, 0, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(2, response…000, 0, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(routePreferenceData), 3000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(2, response…000, 0, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v1, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v2, types: [T, java.lang.Object, java.lang.String] */
    public static final void queryPreferences$lambda$35(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResGetRoutePreference resGetRoutePreference) throws JSONException {
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        RoutePreferenceData routePreferenceData = new RoutePreferenceData();
        if (resGetRoutePreference.getMessageType() == EMessageType.Result) {
            Integer value = resGetRoutePreference.getArguments().getRouteCriteria().getValue();
            if (Intrinsics.areEqual(value, RoutePreference.RECOMMENDATION.getValue())) {
                routePreferenceData.setRoutePreference(10);
            } else if (Intrinsics.areEqual(value, RoutePreference.TIME_PRIORITY.getValue())) {
                routePreferenceData.setRoutePreference(11);
            } else if (Intrinsics.areEqual(value, RoutePreference.DISTANCE_PRIORITY.getValue())) {
                routePreferenceData.setRoutePreference(12);
            } else if (Intrinsics.areEqual(value, RoutePreference.AVOID_CONGESTION.getValue())) {
                routePreferenceData.setRoutePreference(13);
            } else if (Intrinsics.areEqual(value, RoutePreference.HIGHWAY_PRIORITY.getValue())) {
                routePreferenceData.setRoutePreference(14);
            } else if (Intrinsics.areEqual(value, RoutePreference.AVOID_HIGHWAY.getValue())) {
                routePreferenceData.setRoutePreference(15);
            } else if (Intrinsics.areEqual(value, RoutePreference.ECONOMIC.getValue())) {
                routePreferenceData.setRoutePreference(16);
            } else {
                routePreferenceData.setRoutePreference(10);
            }
            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(2, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(routePreferenceData), 3000, 0, 0);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(2, response…ePreference), 3000, 0, 0)");
            mJsStr.element = JsonEncoding;
        } else {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(routePreferenceData), 3000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(2, response…000, 0, unknownErrorCode)");
            mJsStr.element = JsonEncoding2;
        }
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v6, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String getWayPointList() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        final ListPoiData listPoiData = new ListPoiData();
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestGetWaypointList(new ReqGetWaypointList()).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda40
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.getWayPointList$lambda$36(listPoiData, objectRef, this, objectRef2, (ResGetWaypointList) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(listPoiData), 3000, 0, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(3, response…000, 0, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(listPoiData), 3000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(3, response…000, 0, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v4, types: [T, java.lang.Object, java.lang.String] */
    public static final void getWayPointList$lambda$36(ListPoiData resultList, Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResGetWaypointList resGetWaypointList) throws JSONException {
        Intrinsics.checkNotNullParameter(resultList, "$resultList");
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        ArrayList arrayList = new ArrayList();
        if (resGetWaypointList.getMessageType() == EMessageType.Result) {
            Intrinsics.checkNotNullExpressionValue(resGetWaypointList.getArguments().getWayPointList(), "it.arguments.wayPointList");
            if (!r1.isEmpty()) {
                List<WaypointInfo> wayPointList = resGetWaypointList.getArguments().getWayPointList();
                Intrinsics.checkNotNullExpressionValue(wayPointList, "it.arguments.wayPointList");
                Iterator it = CollectionsKt.minus(CollectionsKt.getIndices(wayPointList), 1).iterator();
                while (it.hasNext()) {
                    int iIntValue = ((Number) it.next()).intValue();
                    PoiData poiData = new PoiData();
                    Float accuracy = resGetWaypointList.getArguments().getWayPointList().get(iIntValue).getLocationInfo().getAccuracy();
                    Intrinsics.checkNotNullExpressionValue(accuracy, "it.arguments.wayPointList[i].locationInfo.accuracy");
                    poiData.setAccuracy(accuracy.floatValue());
                    poiData.setAdcode(resGetWaypointList.getArguments().getWayPointList().get(iIntValue).getLocationInfo().getAdCode());
                    poiData.setAddress(resGetWaypointList.getArguments().getWayPointList().get(iIntValue).getLocationInfo().getAddress());
                    poiData.setAreacode(resGetWaypointList.getArguments().getWayPointList().get(iIntValue).getLocationInfo().getAreaCode());
                    poiData.setCity(resGetWaypointList.getArguments().getWayPointList().get(iIntValue).getLocationInfo().getCity());
                    poiData.setCityadcode(resGetWaypointList.getArguments().getWayPointList().get(iIntValue).getLocationInfo().getCityCode());
                    poiData.setDistance(resGetWaypointList.getArguments().getWayPointList().get(iIntValue).getLocationInfo().getDistance().intValue());
                    poiData.setDistrict(resGetWaypointList.getArguments().getWayPointList().get(iIntValue).getLocationInfo().getDistrict());
                    poiData.setDistrictadcode(resGetWaypointList.getArguments().getWayPointList().get(iIntValue).getLocationInfo().getDistrictAdCode());
                    Double latitude = resGetWaypointList.getArguments().getWayPointList().get(iIntValue).getLocationInfo().getGeoPosition().getLatitude();
                    Intrinsics.checkNotNullExpressionValue(latitude, "it.arguments.wayPointLis…Info.geoPosition.latitude");
                    poiData.setLatitude(latitude.doubleValue());
                    Double longitude = resGetWaypointList.getArguments().getWayPointList().get(iIntValue).getLocationInfo().getGeoPosition().getLongitude();
                    Intrinsics.checkNotNullExpressionValue(longitude, "it.arguments.wayPointLis…nfo.geoPosition.longitude");
                    poiData.setLongitude(longitude.doubleValue());
                    poiData.setName(resGetWaypointList.getArguments().getWayPointList().get(iIntValue).getLocationInfo().getName());
                    poiData.setPoiId(resGetWaypointList.getArguments().getWayPointList().get(iIntValue).getLocationInfo().getPoiId());
                    poiData.setProvince(resGetWaypointList.getArguments().getWayPointList().get(iIntValue).getLocationInfo().getProvince());
                    poiData.setProvinceadcode(resGetWaypointList.getArguments().getWayPointList().get(iIntValue).getLocationInfo().getProvinceAdCode());
                    arrayList.add(poiData);
                }
                resultList.setWayPoints(arrayList);
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(3, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(resultList), 3000, 0, 0);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(3, response…(resultList), 3000, 0, 0)");
                mJsStr.element = JsonEncoding;
            }
        } else {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(3, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(resultList), 3000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(3, response…000, 0, unknownErrorCode)");
            mJsStr.element = JsonEncoding2;
        }
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v5, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v4, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String increaseRoutePoints(PoiData poiData) throws JSONException {
        Intrinsics.checkNotNullParameter(poiData, "poiData");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        WaypointInfo waypointInfo = new WaypointInfo();
        waypointInfo.setName(poiData.getName());
        waypointInfo.getLocationInfo().setAccuracy(Float.valueOf(poiData.getAccuracy()));
        waypointInfo.getLocationInfo().setAdCode(poiData.getAdcode());
        waypointInfo.getLocationInfo().setAddress(poiData.getAddress());
        waypointInfo.getLocationInfo().setAreaCode(poiData.getAreacode());
        waypointInfo.getLocationInfo().setCity(poiData.getCity());
        waypointInfo.getLocationInfo().setCityCode(poiData.getCityadcode());
        waypointInfo.getLocationInfo().setDistance(Integer.valueOf((int) poiData.getDistance()));
        waypointInfo.getLocationInfo().setDistrict(poiData.getDistrict());
        waypointInfo.getLocationInfo().setDistrictAdCode(poiData.getDistrictadcode());
        waypointInfo.getLocationInfo().getGeoPosition().setLatitude(Double.valueOf(poiData.getLatitude()));
        waypointInfo.getLocationInfo().getGeoPosition().setLongitude(Double.valueOf(poiData.getLongitude()));
        waypointInfo.getLocationInfo().setName(poiData.getName());
        waypointInfo.getLocationInfo().setPoiId(poiData.getPoiId());
        waypointInfo.getLocationInfo().setProvince(poiData.getProvince());
        waypointInfo.getLocationInfo().setProvinceAdCode(poiData.getProvinceadcode());
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            CompletableFuture<ResRequestSetWaypoint> completableFutureRequestRequestSetWaypoint = greatWallNavApiRequests.requestRequestSetWaypoint(new ReqRequestSetWaypoint(waypointInfo));
            final Function1<ResRequestSetWaypoint, Unit> function1 = new Function1<ResRequestSetWaypoint, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl.increaseRoutePoints.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ResRequestSetWaypoint resRequestSetWaypoint) throws JSONException {
                    invoke2(resRequestSetWaypoint);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ResRequestSetWaypoint resRequestSetWaypoint) throws JSONException {
                    T t;
                    Ref.ObjectRef<String> objectRef2 = objectRef;
                    if (resRequestSetWaypoint.getMessageType() == EMessageType.Result) {
                        String str = this.responseAuthOfAsyncCall;
                        Integer resultCode = resRequestSetWaypoint.getArguments().getResultCode();
                        Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
                        String strJsonEncoding = GreatWallApiTool.jsonEncoding(3, str, 3000, 1, resultCode.intValue());
                        Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
                        t = strJsonEncoding;
                    } else {
                        String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfAsyncCall, 3000, 1, this.unknownErrorCode);
                        Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
                        t = strJsonEncoding2;
                    }
                    objectRef2.element = t;
                    countDownLatch.countDown();
                }
            };
            completableFutureRequestRequestSetWaypoint.thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda27
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) {
                    GreatWallApiOctaImpl.increaseRoutePoints$lambda$37(function1, obj);
                }
            });
        }
        try {
            if (!countDownLatch.await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfAsyncCall, 3000, 1, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(3, response…000, 1, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfAsyncCall, 3000, 1, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(3, response…000, 1, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void increaseRoutePoints$lambda$37(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v6, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String getdestinationInfo() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        final PoiData poiData = new PoiData();
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestGetWaypointList(new ReqGetWaypointList()).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda47
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.getdestinationInfo$lambda$38(poiData, objectRef, this, objectRef2, (ResGetWaypointList) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(4, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(poiData), 3000, 0, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(4, response…000, 0, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(4, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(poiData), 3000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(4, response…000, 0, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v4, types: [T, java.lang.Object, java.lang.String] */
    public static final void getdestinationInfo$lambda$38(PoiData result, Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResGetWaypointList resGetWaypointList) throws JSONException {
        Intrinsics.checkNotNullParameter(result, "$result");
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        new WaypointInfo();
        if (resGetWaypointList.getMessageType() == EMessageType.Result) {
            Intrinsics.checkNotNullExpressionValue(resGetWaypointList.getArguments().getWayPointList(), "it.arguments.wayPointList");
            if (!r0.isEmpty()) {
                WaypointInfo waypointInfo = resGetWaypointList.getArguments().getWayPointList().get(resGetWaypointList.getArguments().getWayPointList().size() - 1);
                Intrinsics.checkNotNullExpressionValue(waypointInfo, "it.arguments.wayPointLis…ts.wayPointList.size - 1]");
                WaypointInfo waypointInfo2 = waypointInfo;
                Float accuracy = waypointInfo2.getLocationInfo().getAccuracy();
                Intrinsics.checkNotNullExpressionValue(accuracy, "destinationInfo.locationInfo.accuracy");
                result.setAccuracy(accuracy.floatValue());
                result.setAdcode(waypointInfo2.getLocationInfo().getAdCode());
                result.setAddress(waypointInfo2.getLocationInfo().getAddress());
                result.setAreacode(waypointInfo2.getLocationInfo().getAreaCode());
                result.setCity(waypointInfo2.getLocationInfo().getCity());
                result.setCityadcode(waypointInfo2.getLocationInfo().getCityCode());
                result.setDistance(waypointInfo2.getLocationInfo().getDistance().intValue());
                result.setDistrict(waypointInfo2.getLocationInfo().getDistrict());
                result.setDistrictadcode(waypointInfo2.getLocationInfo().getDistrictAdCode());
                Double latitude = waypointInfo2.getLocationInfo().getGeoPosition().getLatitude();
                Intrinsics.checkNotNullExpressionValue(latitude, "destinationInfo.locationInfo.geoPosition.latitude");
                result.setLatitude(latitude.doubleValue());
                Double longitude = waypointInfo2.getLocationInfo().getGeoPosition().getLongitude();
                Intrinsics.checkNotNullExpressionValue(longitude, "destinationInfo.locationInfo.geoPosition.longitude");
                result.setLongitude(longitude.doubleValue());
                result.setName(waypointInfo2.getLocationInfo().getName());
                result.setPoiId(waypointInfo2.getLocationInfo().getPoiId());
                result.setProvince(waypointInfo2.getLocationInfo().getProvince());
                result.setProvinceadcode(waypointInfo2.getLocationInfo().getProvinceAdCode());
            }
            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(4, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(result), 3000, 0, 0);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(4, response…ding(result), 3000, 0, 0)");
            mJsStr.element = JsonEncoding;
        } else {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(4, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(result), 3000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(4, response…000, 0, unknownErrorCode)");
            mJsStr.element = JsonEncoding2;
        }
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v4, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v2, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String getNavigationStatus() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final StateData stateData = new StateData();
        stateData.setState(1);
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestGetGuidedStatus(new ReqGetGuidedStatus()).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda41
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.getNavigationStatus$lambda$39(stateData, objectRef, this, countDownLatch, (ResGetGuidedStatus) obj);
                }
            });
        }
        try {
            if (!countDownLatch.await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(stateData), 4000, 0, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(0, response…000, 0, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(stateData), 4000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(0, response…000, 0, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r6v1, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v2, types: [T, java.lang.Object, java.lang.String] */
    public static final void getNavigationStatus$lambda$39(StateData stateData, Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, CountDownLatch mLatch, ResGetGuidedStatus resGetGuidedStatus) throws JSONException {
        Intrinsics.checkNotNullParameter(stateData, "$stateData");
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resGetGuidedStatus.getMessageType() == EMessageType.Result) {
            Integer value = resGetGuidedStatus.getArguments().getGuidedStatus().getValue();
            Intrinsics.checkNotNullExpressionValue(value, "it.arguments.guidedStatus.value");
            stateData.setState(value.intValue());
            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(0, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(stateData), 4000, 0, 0);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(0, response…g(stateData), 4000, 0, 0)");
            mJsStr.element = JsonEncoding;
        } else {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(stateData), 4000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(0, response…000, 0, unknownErrorCode)");
            mJsStr.element = JsonEncoding2;
        }
        mLatch.countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v6, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String getTBTInfo() throws JSONException {
        Log.i(TAG, "[getTBTInfo]");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            CompletableFuture<ResGetGuidanceInfoStatusForGreatWall> completableFutureRequestGetGuidanceInfoStatusForGreatWall = greatWallNavApiRequests.requestGetGuidanceInfoStatusForGreatWall();
            final Function1<ResGetGuidanceInfoStatusForGreatWall, Unit> function1 = new Function1<ResGetGuidanceInfoStatusForGreatWall, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl.getTBTInfo.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ResGetGuidanceInfoStatusForGreatWall resGetGuidanceInfoStatusForGreatWall) throws JSONException {
                    invoke2(resGetGuidanceInfoStatusForGreatWall);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object, java.lang.String] */
                /* JADX WARN: Type inference failed for: r0v4, types: [T, java.lang.Object, java.lang.String] */
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ResGetGuidanceInfoStatusForGreatWall resGetGuidanceInfoStatusForGreatWall) throws JSONException {
                    if (resGetGuidanceInfoStatusForGreatWall.getMessageType() == EMessageType.Result) {
                        GuidanceInfoStatusData guidanceInfoStatusData = new GuidanceInfoStatusData();
                        TBTData tBTData = new TBTData();
                        SecondTBTData secondTBTData = new SecondTBTData();
                        ETAData eTAData = new ETAData();
                        tBTData.setRoadName(resGetGuidanceInfoStatusForGreatWall.getArguments().getGuidanceInfoStatus().getNextRoadName());
                        Integer turnId = resGetGuidanceInfoStatusForGreatWall.getArguments().getGuidanceInfoStatus().getTurnId();
                        Intrinsics.checkNotNullExpressionValue(turnId, "it.arguments.guidanceInfoStatus.turnId");
                        tBTData.setTurnId(turnId.intValue());
                        Integer turnDistance = resGetGuidanceInfoStatusForGreatWall.getArguments().getGuidanceInfoStatus().getTurnDistance();
                        Intrinsics.checkNotNullExpressionValue(turnDistance, "it.arguments.guidanceInfoStatus.turnDistance");
                        tBTData.setDistance(turnDistance.intValue());
                        tBTData.setDistanceString(resGetGuidanceInfoStatusForGreatWall.getArguments().getGuidanceInfoStatus().getTurnDistanceStr());
                        tBTData.setExit(resGetGuidanceInfoStatusForGreatWall.getArguments().getGuidanceInfoStatus().getExitNumOfHighway());
                        guidanceInfoStatusData.setFirstTBT(tBTData);
                        secondTBTData.setRoadName("");
                        Integer nextTurnId = resGetGuidanceInfoStatusForGreatWall.getArguments().getGuidanceInfoStatus().getNextTurnId();
                        Intrinsics.checkNotNullExpressionValue(nextTurnId, "it.arguments.guidanceInfoStatus.nextTurnId");
                        secondTBTData.setTurnId(nextTurnId.intValue());
                        Integer nextTurnDistance = resGetGuidanceInfoStatusForGreatWall.getArguments().getGuidanceInfoStatus().getNextTurnDistance();
                        Intrinsics.checkNotNullExpressionValue(nextTurnDistance, "it.arguments.guidanceInfoStatus.nextTurnDistance");
                        secondTBTData.setDistance(nextTurnDistance.intValue());
                        secondTBTData.setDistanceString(resGetGuidanceInfoStatusForGreatWall.getArguments().getGuidanceInfoStatus().getNextTurnDistanceStr());
                        guidanceInfoStatusData.setSecondTBT(secondTBTData);
                        Integer remainingTime = resGetGuidanceInfoStatusForGreatWall.getArguments().getGuidanceInfoStatus().getRemainingTime();
                        Intrinsics.checkNotNullExpressionValue(remainingTime, "it.arguments.guidanceInfoStatus.remainingTime");
                        eTAData.setRemainTime(remainingTime.intValue());
                        Integer remainingDistance = resGetGuidanceInfoStatusForGreatWall.getArguments().getGuidanceInfoStatus().getRemainingDistance();
                        Intrinsics.checkNotNullExpressionValue(remainingDistance, "it.arguments.guidanceInfoStatus.remainingDistance");
                        eTAData.setRemainDistance(remainingDistance.intValue());
                        Integer remainingBatteryPower = resGetGuidanceInfoStatusForGreatWall.getArguments().getGuidanceInfoStatus().getRemainingBatteryPower();
                        Intrinsics.checkNotNullExpressionValue(remainingBatteryPower, "it.arguments.guidanceInf…tus.remainingBatteryPower");
                        eTAData.setRemianBatteryPower(remainingBatteryPower.intValue());
                        eTAData.setRemainTimeString(resGetGuidanceInfoStatusForGreatWall.getArguments().getGuidanceInfoStatus().getRemainingTimeStr());
                        eTAData.setArrivalTimeString(resGetGuidanceInfoStatusForGreatWall.getArguments().getGuidanceInfoStatus().getArrivalTimeStr());
                        eTAData.setRemainDistanceString(resGetGuidanceInfoStatusForGreatWall.getArguments().getGuidanceInfoStatus().getRemainingDistanceStr());
                        eTAData.setRemianBatteryPowerString(resGetGuidanceInfoStatusForGreatWall.getArguments().getGuidanceInfoStatus().getRemainingBatteryPowerStr());
                        guidanceInfoStatusData.setETA(eTAData);
                        Ref.ObjectRef<String> objectRef2 = objectRef;
                        ?? JsonEncoding = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(guidanceInfoStatusData), 20000, 1, 0);
                        Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(2, response…InfoStatus), 20000, 1, 0)");
                        objectRef2.element = JsonEncoding;
                    } else {
                        Ref.ObjectRef<String> objectRef3 = objectRef;
                        ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, JSONObject.NULL.toString(), 20000, 1, this.unknownErrorCode);
                        Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(2, response…000, 1, unknownErrorCode)");
                        objectRef3.element = JsonEncoding2;
                    }
                    countDownLatch.countDown();
                }
            };
            completableFutureRequestGetGuidanceInfoStatusForGreatWall.thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda43
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) {
                    GreatWallApiOctaImpl.getTBTInfo$lambda$40(function1, obj);
                }
            });
        }
        try {
            if (!countDownLatch.await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, JSONObject.NULL.toString(), 20000, 1, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(2, response…000, 1, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (Exception e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, JSONObject.NULL.toString(), 20000, 1, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(2, response…000, 1, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getTBTInfo$lambda$40(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String startNavigation() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        new StateData();
        if (this.requests != null) {
            mapToFrontDeskPrivate();
            GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestStartGuidance(new ReqRequestStartGuidance()).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda48
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.startNavigation$lambda$41(objectRef, this, objectRef2, (ResRequestStartGuidance) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, 4000, 1, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(0, response…000, 1, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, 4000, 1, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(0, response…000, 1, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void startNavigation$lambda$41(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResRequestStartGuidance resRequestStartGuidance) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resRequestStartGuidance.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resRequestStartGuidance.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(0, str, 4000, 1, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this$0.responseAuthOfAsyncCall, 4000, 1, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String stopNavigation() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        new StateData();
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestStopGuidance(new ReqRequestStopGuidance()).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda0
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.stopNavigation$lambda$42(objectRef, this, objectRef2, (ResRequestStopGuidance) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, 4000, 2, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(0, response…000, 2, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, 4000, 2, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(0, response…000, 2, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void stopNavigation$lambda$42(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResRequestStopGuidance resRequestStopGuidance) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resRequestStopGuidance.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resRequestStopGuidance.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(0, str, 4000, 2, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this$0.responseAuthOfAsyncCall, 4000, 2, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v8, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1, types: [T, com.neusoft.na.ocservice.greatwallsdk.bean.DestinationInformationData] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String destinationInformation() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = new DestinationInformationData();
        new StateData();
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestDTDAndETA(new ReqRequestDTDAndETA()).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda30
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.destinationInformation$lambda$43(objectRef3, objectRef, this, objectRef2, (ResRequestDTDAndETA) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(4, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((DestinationInformationData) objectRef3.element), 4000, 0, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(4, response…000, 0, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(4, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((DestinationInformationData) objectRef3.element), 4000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(4, response…000, 0, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v8, types: [T, java.lang.Object, java.lang.String] */
    public static final void destinationInformation$lambda$43(Ref.ObjectRef destinationInformationData, Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResRequestDTDAndETA resRequestDTDAndETA) throws JSONException {
        Intrinsics.checkNotNullParameter(destinationInformationData, "$destinationInformationData");
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resRequestDTDAndETA.getMessageType() == EMessageType.Result) {
            DestinationInformationData destinationInformationData2 = (DestinationInformationData) destinationInformationData.element;
            Integer distanceToDestination = resRequestDTDAndETA.getArguments().getRouteOverview().getDistanceToDestination();
            Intrinsics.checkNotNullExpressionValue(distanceToDestination, "it.arguments.routeOverview.distanceToDestination");
            destinationInformationData2.setRemainDist(distanceToDestination.intValue());
            DestinationInformationData destinationInformationData3 = (DestinationInformationData) destinationInformationData.element;
            Integer eta = resRequestDTDAndETA.getArguments().getRouteOverview().getETA();
            Intrinsics.checkNotNullExpressionValue(eta, "it.arguments.routeOverview.eta");
            destinationInformationData3.setRemainTime(eta.intValue());
            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(4, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((DestinationInformationData) destinationInformationData.element), 4000, 0, 0);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(4, response…rmationData), 4000, 0, 0)");
            mJsStr.element = JsonEncoding;
        } else {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(4, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((DestinationInformationData) destinationInformationData.element), 4000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(4, response…000, 0, unknownErrorCode)");
            mJsStr.element = JsonEncoding2;
        }
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v8, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1, types: [T, com.neusoft.na.ocservice.greatwallsdk.bean.ResultPoiListData] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String getFavoriteList() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = new ResultPoiListData();
        if (this.requests != null) {
            mapToFrontDeskPrivate();
            GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestGetFavoriteList(new ReqGetFavoriteList(FavoriteType.FAVORITE)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda21
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.getFavoriteList$lambda$44(objectRef3, objectRef, this, objectRef2, (ResGetFavoriteList) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((ResultPoiListData) objectRef3.element), 5000, 0, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(0, response…000, 0, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((ResultPoiListData) objectRef3.element), 5000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(0, response…000, 0, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v8, types: [T, java.lang.Object, java.lang.String] */
    public static final void getFavoriteList$lambda$44(Ref.ObjectRef resultDataList, Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResGetFavoriteList resGetFavoriteList) throws JSONException {
        Intrinsics.checkNotNullParameter(resultDataList, "$resultDataList");
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        ArrayList arrayList = new ArrayList();
        PoiData poiData = new PoiData();
        if (resGetFavoriteList.getMessageType() == EMessageType.Result) {
            Intrinsics.checkNotNullExpressionValue(resGetFavoriteList.getArguments().getResultList(), "it.arguments.resultList");
            if (!r2.isEmpty()) {
                for (FavoriteData favoriteData : resGetFavoriteList.getArguments().getResultList()) {
                    Float accuracy = favoriteData.getLocationInfo().getAccuracy();
                    Intrinsics.checkNotNullExpressionValue(accuracy, "iFavoriteData.locationInfo.accuracy");
                    poiData.setAccuracy(accuracy.floatValue());
                    poiData.setAdcode(favoriteData.getLocationInfo().getAdCode());
                    poiData.setAddress(favoriteData.getLocationInfo().getAddress());
                    poiData.setAreacode(favoriteData.getLocationInfo().getAreaCode());
                    poiData.setCity(favoriteData.getLocationInfo().getCity());
                    poiData.setCityadcode(favoriteData.getLocationInfo().getCityCode());
                    poiData.setDistance(favoriteData.getLocationInfo().getDistance().intValue());
                    poiData.setDistrict(favoriteData.getLocationInfo().getDistrict());
                    poiData.setDistrictadcode(favoriteData.getLocationInfo().getDistrictAdCode());
                    Double latitude = favoriteData.getLocationInfo().getGeoPosition().getLatitude();
                    Intrinsics.checkNotNullExpressionValue(latitude, "iFavoriteData.locationInfo.geoPosition.latitude");
                    poiData.setLatitude(latitude.doubleValue());
                    Double longitude = favoriteData.getLocationInfo().getGeoPosition().getLongitude();
                    Intrinsics.checkNotNullExpressionValue(longitude, "iFavoriteData.locationInfo.geoPosition.longitude");
                    poiData.setLongitude(longitude.doubleValue());
                    poiData.setName(favoriteData.getLocationInfo().getName());
                    poiData.setPoiId(favoriteData.getLocationInfo().getPoiId());
                    poiData.setProvince(favoriteData.getLocationInfo().getProvince());
                    poiData.setProvinceadcode(favoriteData.getLocationInfo().getProvinceAdCode());
                    arrayList.add(poiData);
                }
            }
            ((ResultPoiListData) resultDataList.element).setResult(arrayList);
            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(0, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((ResultPoiListData) resultDataList.element), 5000, 0, 0);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(0, response…ultDataList), 5000, 0, 0)");
            mJsStr.element = JsonEncoding;
        } else {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((ResultPoiListData) resultDataList.element), 5000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(0, response…000, 0, unknownErrorCode)");
            mJsStr.element = JsonEncoding2;
        }
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r9v5, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String addPointsToFavorites(PoiData poiData) throws JSONException {
        Intrinsics.checkNotNullParameter(poiData, "poiData");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        FavoriteData favoriteData = new FavoriteData();
        favoriteData.getLocationInfo().setAccuracy(Float.valueOf(poiData.getAccuracy()));
        favoriteData.getLocationInfo().setAdCode(poiData.getAdcode());
        favoriteData.getLocationInfo().setAddress(poiData.getAddress());
        favoriteData.getLocationInfo().setAreaCode(poiData.getAreacode());
        favoriteData.getLocationInfo().setCity(poiData.getCity());
        favoriteData.getLocationInfo().setCityCode(poiData.getCityadcode());
        favoriteData.getLocationInfo().setDistance(Integer.valueOf((int) poiData.getDistance()));
        favoriteData.getLocationInfo().setDistrict(poiData.getDistrict());
        favoriteData.getLocationInfo().setDistrictAdCode(poiData.getDistrictadcode());
        favoriteData.getLocationInfo().getGeoPosition().setLatitude(Double.valueOf(poiData.getLatitude()));
        favoriteData.getLocationInfo().getGeoPosition().setLongitude(Double.valueOf(poiData.getLongitude()));
        favoriteData.getLocationInfo().setName(poiData.getName());
        favoriteData.getLocationInfo().setPoiId(poiData.getPoiId());
        favoriteData.getLocationInfo().setProvince(poiData.getProvince());
        favoriteData.getLocationInfo().setProvinceAdCode(poiData.getProvinceadcode());
        favoriteData.setType(FavoriteType.FAVORITE);
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestSetFavorite(new ReqRequestSetFavorite(favoriteData)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda11
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.addPointsToFavorites$lambda$45(objectRef, this, objectRef2, (ResRequestSetFavorite) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, 5000, 1, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(0, response…000, 1, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, 5000, 1, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(0, response…000, 1, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void addPointsToFavorites$lambda$45(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResRequestSetFavorite resRequestSetFavorite) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resRequestSetFavorite.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resRequestSetFavorite.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(0, str, 5000, 1, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this$0.responseAuthOfAsyncCall, 5000, 1, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String removeFavoritePoints(PoiData poiData) throws JSONException {
        Intrinsics.checkNotNullParameter(poiData, "poiData");
        String strJsonEncoding = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, 5000, 2, this.unknownErrorCode);
        Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "jsonEncoding(0, response…000, 2, unknownErrorCode)");
        return strJsonEncoding;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String displayFavorites() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        if (this.requests != null) {
            mapToFrontDeskPrivate();
            GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestJumpToViewPage(new ReqRequestJumpToViewPage(ViewPage.NAV_FAVORITE_LIST_PAGE)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda57
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.displayFavorites$lambda$46(objectRef, this, objectRef2, (ResRequestJumpToViewPage) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, 5000, 3, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(0, response…000, 3, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, 5000, 3, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(0, response…000, 3, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void displayFavorites$lambda$46(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResRequestJumpToViewPage resRequestJumpToViewPage) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resRequestJumpToViewPage.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resRequestJumpToViewPage.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(0, str, 5000, 3, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this$0.responseAuthOfAsyncCall, 5000, 3, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v8, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1, types: [T, com.neusoft.na.ocservice.greatwallsdk.bean.PoiData] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String getHomeAddress() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = new PoiData();
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestGetFavoriteList(new ReqGetFavoriteList(FavoriteType.HOME)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda59
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.getHomeAddress$lambda$47(objectRef3, objectRef, this, objectRef2, (ResGetFavoriteList) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((PoiData) objectRef3.element), 5000, 0, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response…000, 0, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((PoiData) objectRef3.element), 5000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…000, 0, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v8, types: [T, java.lang.Object, java.lang.String] */
    public static final void getHomeAddress$lambda$47(Ref.ObjectRef mHomePoiData, Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResGetFavoriteList resGetFavoriteList) throws JSONException {
        Intrinsics.checkNotNullParameter(mHomePoiData, "$mHomePoiData");
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        new LocationInfo();
        if (resGetFavoriteList.getMessageType() == EMessageType.Result) {
            Intrinsics.checkNotNullExpressionValue(resGetFavoriteList.getArguments().getResultList(), "it.arguments.resultList");
            if (!r0.isEmpty()) {
                LocationInfo locationInfo = resGetFavoriteList.getArguments().getResultList().get(0).getLocationInfo();
                Intrinsics.checkNotNullExpressionValue(locationInfo, "it.arguments.resultList[0].locationInfo");
                PoiData poiData = (PoiData) mHomePoiData.element;
                Float accuracy = locationInfo.getAccuracy();
                Intrinsics.checkNotNullExpressionValue(accuracy, "mLocationInfo.accuracy");
                poiData.setAccuracy(accuracy.floatValue());
                ((PoiData) mHomePoiData.element).setAdcode(locationInfo.getAdCode());
                ((PoiData) mHomePoiData.element).setAddress(locationInfo.getAddress());
                ((PoiData) mHomePoiData.element).setAreacode(locationInfo.getAreaCode());
                ((PoiData) mHomePoiData.element).setCity(locationInfo.getCity());
                ((PoiData) mHomePoiData.element).setCityadcode(locationInfo.getCityCode());
                ((PoiData) mHomePoiData.element).setDistance(locationInfo.getDistance().intValue());
                ((PoiData) mHomePoiData.element).setDistrict(locationInfo.getDistrict());
                ((PoiData) mHomePoiData.element).setDistrictadcode(locationInfo.getDistrictAdCode());
                PoiData poiData2 = (PoiData) mHomePoiData.element;
                Double latitude = locationInfo.getGeoPosition().getLatitude();
                Intrinsics.checkNotNullExpressionValue(latitude, "mLocationInfo.geoPosition.latitude");
                poiData2.setLatitude(latitude.doubleValue());
                PoiData poiData3 = (PoiData) mHomePoiData.element;
                Double longitude = locationInfo.getGeoPosition().getLongitude();
                Intrinsics.checkNotNullExpressionValue(longitude, "mLocationInfo.geoPosition.longitude");
                poiData3.setLongitude(longitude.doubleValue());
                ((PoiData) mHomePoiData.element).setName(locationInfo.getName());
                ((PoiData) mHomePoiData.element).setPoiId(locationInfo.getPoiId());
                ((PoiData) mHomePoiData.element).setProvince(locationInfo.getProvince());
                ((PoiData) mHomePoiData.element).setProvinceadcode(locationInfo.getProvinceAdCode());
            }
            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((PoiData) mHomePoiData.element), 5000, 0, 0);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response…HomePoiData), 5000, 0, 0)");
            mJsStr.element = JsonEncoding;
        } else {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((PoiData) mHomePoiData.element), 5000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…000, 0, unknownErrorCode)");
            mJsStr.element = JsonEncoding2;
        }
        ((CountDownLatch) mLatch.element).countDown();
    }

    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String getDayNightMode() throws JSONException {
        String strJsonEncoding;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ModeData modeData = new ModeData();
        if (this.dayNightStatusAttr != null) {
            StringBuilder sb = new StringBuilder("getDayNightMode():dayNightStatusAttr!!.value = ");
            IGettableAttribute<NotifyNaviDayNightStatusChange> iGettableAttribute = this.dayNightStatusAttr;
            Intrinsics.checkNotNull(iGettableAttribute);
            Log.i(TAG, sb.append(iGettableAttribute.getValue().getNaviDayNightStatusChange().getValue()).toString());
            IGettableAttribute<NotifyNaviDayNightStatusChange> iGettableAttribute2 = this.dayNightStatusAttr;
            Intrinsics.checkNotNull(iGettableAttribute2);
            if (iGettableAttribute2.getValue().getNaviDayNightStatusChange() == NaviDayNightStatus.DAYNIGHT_STATUS_DAY) {
                modeData.setMode(1);
            } else {
                modeData.setMode(0);
            }
            strJsonEncoding = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfNotifications, GreatWallApiTool.jsonEncoding(modeData), 10000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "jsonEncoding(3, response…000, 0, unknownErrorCode)");
        } else {
            strJsonEncoding = "";
        }
        try {
            if (countDownLatch.await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                return strJsonEncoding;
            }
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfNotifications, GreatWallApiTool.jsonEncoding(modeData), 10000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "jsonEncoding(3, response…000, 0, unknownErrorCode)");
            Log.i(TAG, "mLatch await timeout.");
            return strJsonEncoding2;
        } catch (InterruptedException e) {
            String strJsonEncoding3 = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfNotifications, GreatWallApiTool.jsonEncoding(modeData), 10000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding3, "jsonEncoding(3, response…000, 0, unknownErrorCode)");
            e.printStackTrace();
            return strJsonEncoding3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r9v4, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String setHomeAddress(PoiData poiData) throws JSONException {
        Intrinsics.checkNotNullParameter(poiData, "poiData");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        LocationInfo locationInfo = new LocationInfo();
        locationInfo.setAccuracy(Float.valueOf(poiData.getAccuracy()));
        locationInfo.setAdCode(poiData.getAdcode());
        locationInfo.setAddress(poiData.getAddress());
        locationInfo.setAreaCode(poiData.getAreacode());
        locationInfo.setCity(poiData.getCity());
        locationInfo.setCityCode(poiData.getCityadcode());
        locationInfo.setDistance(Integer.valueOf((int) poiData.getDistance()));
        locationInfo.setDistrict(poiData.getDistrict());
        locationInfo.setDistrictAdCode(poiData.getDistrictadcode());
        locationInfo.getGeoPosition().setLatitude(Double.valueOf(poiData.getLatitude()));
        locationInfo.getGeoPosition().setLongitude(Double.valueOf(poiData.getLongitude()));
        locationInfo.setName(poiData.getName());
        locationInfo.setPoiId(poiData.getPoiId());
        locationInfo.setProvince(poiData.getProvince());
        locationInfo.setProvinceAdCode(poiData.getProvinceadcode());
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestSetFavorite(new ReqRequestSetFavorite(new FavoriteData(FavoriteType.HOME, locationInfo))).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda20
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.setHomeAddress$lambda$48(objectRef, this, objectRef2, (ResRequestSetFavorite) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, 5000, 1, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response…000, 1, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, 5000, 1, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…000, 1, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void setHomeAddress$lambda$48(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResRequestSetFavorite resRequestSetFavorite) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resRequestSetFavorite.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resRequestSetFavorite.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(1, str, 5000, 1, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this$0.responseAuthOfAsyncCall, 5000, 1, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v8, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1, types: [T, com.neusoft.na.ocservice.greatwallsdk.bean.PoiData] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String getCompanyAddress() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = new PoiData();
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestGetFavoriteList(new ReqGetFavoriteList(FavoriteType.COMPANY)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda25
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.getCompanyAddress$lambda$49(objectRef3, objectRef, this, objectRef2, (ResGetFavoriteList) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((PoiData) objectRef3.element), 5000, 2, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response…000, 2, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((PoiData) objectRef3.element), 5000, 2, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…000, 2, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v8, types: [T, java.lang.Object, java.lang.String] */
    public static final void getCompanyAddress$lambda$49(Ref.ObjectRef mCompanyAddress, Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResGetFavoriteList resGetFavoriteList) throws JSONException {
        Intrinsics.checkNotNullParameter(mCompanyAddress, "$mCompanyAddress");
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        new LocationInfo();
        if (resGetFavoriteList.getMessageType() == EMessageType.Result) {
            Intrinsics.checkNotNullExpressionValue(resGetFavoriteList.getArguments().getResultList(), "it.arguments.resultList");
            if (!r0.isEmpty()) {
                LocationInfo locationInfo = resGetFavoriteList.getArguments().getResultList().get(0).getLocationInfo();
                Intrinsics.checkNotNullExpressionValue(locationInfo, "it.arguments.resultList[0].locationInfo");
                PoiData poiData = (PoiData) mCompanyAddress.element;
                Float accuracy = locationInfo.getAccuracy();
                Intrinsics.checkNotNullExpressionValue(accuracy, "mLocationInfo.accuracy");
                poiData.setAccuracy(accuracy.floatValue());
                ((PoiData) mCompanyAddress.element).setAdcode(locationInfo.getAdCode());
                ((PoiData) mCompanyAddress.element).setAddress(locationInfo.getAddress());
                ((PoiData) mCompanyAddress.element).setAreacode(locationInfo.getAreaCode());
                ((PoiData) mCompanyAddress.element).setCity(locationInfo.getCity());
                ((PoiData) mCompanyAddress.element).setCityadcode(locationInfo.getCityCode());
                ((PoiData) mCompanyAddress.element).setDistance(locationInfo.getDistance().intValue());
                ((PoiData) mCompanyAddress.element).setDistrict(locationInfo.getDistrict());
                ((PoiData) mCompanyAddress.element).setDistrictadcode(locationInfo.getDistrictAdCode());
                PoiData poiData2 = (PoiData) mCompanyAddress.element;
                Double latitude = locationInfo.getGeoPosition().getLatitude();
                Intrinsics.checkNotNullExpressionValue(latitude, "mLocationInfo.geoPosition.latitude");
                poiData2.setLatitude(latitude.doubleValue());
                PoiData poiData3 = (PoiData) mCompanyAddress.element;
                Double longitude = locationInfo.getGeoPosition().getLongitude();
                Intrinsics.checkNotNullExpressionValue(longitude, "mLocationInfo.geoPosition.longitude");
                poiData3.setLongitude(longitude.doubleValue());
                ((PoiData) mCompanyAddress.element).setName(locationInfo.getName());
                ((PoiData) mCompanyAddress.element).setPoiId(locationInfo.getPoiId());
                ((PoiData) mCompanyAddress.element).setProvince(locationInfo.getProvince());
                ((PoiData) mCompanyAddress.element).setProvinceadcode(locationInfo.getProvinceAdCode());
            }
            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((PoiData) mCompanyAddress.element), 5000, 2, 0);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response…panyAddress), 5000, 2, 0)");
            mJsStr.element = JsonEncoding;
        } else {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((PoiData) mCompanyAddress.element), 5000, 2, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…000, 2, unknownErrorCode)");
            mJsStr.element = JsonEncoding2;
        }
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r9v4, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String setCompanyAddress(PoiData poiData) throws JSONException {
        Intrinsics.checkNotNullParameter(poiData, "poiData");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        LocationInfo locationInfo = new LocationInfo();
        locationInfo.setAccuracy(Float.valueOf(poiData.getAccuracy()));
        locationInfo.setAdCode(poiData.getAdcode());
        locationInfo.setAddress(poiData.getAddress());
        locationInfo.setAreaCode(poiData.getAreacode());
        locationInfo.setCity(poiData.getCity());
        locationInfo.setCityCode(poiData.getCityadcode());
        locationInfo.setDistance(Integer.valueOf((int) poiData.getDistance()));
        locationInfo.setDistrict(poiData.getDistrict());
        locationInfo.setDistrictAdCode(poiData.getDistrictadcode());
        locationInfo.getGeoPosition().setLatitude(Double.valueOf(poiData.getLatitude()));
        locationInfo.getGeoPosition().setLongitude(Double.valueOf(poiData.getLongitude()));
        locationInfo.setName(poiData.getName());
        locationInfo.setPoiId(poiData.getPoiId());
        locationInfo.setProvince(poiData.getProvince());
        locationInfo.setProvinceAdCode(poiData.getProvinceadcode());
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestSetFavorite(new ReqRequestSetFavorite(new FavoriteData(FavoriteType.COMPANY, locationInfo))).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda51
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.setCompanyAddress$lambda$50(objectRef, this, objectRef2, (ResRequestSetFavorite) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, 5000, 3, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response…000, 3, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, 5000, 3, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…000, 3, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void setCompanyAddress$lambda$50(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResRequestSetFavorite resRequestSetFavorite) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resRequestSetFavorite.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resRequestSetFavorite.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(1, str, 5000, 3, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this$0.responseAuthOfAsyncCall, 5000, 3, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v8, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1, types: [T, com.neusoft.na.ocservice.greatwallsdk.bean.PoiData] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String getLocation() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = new PoiData();
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestGetLocationInfo(new ReqGetLocationInfo()).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda18
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.getLocation$lambda$51(objectRef3, objectRef, this, objectRef2, (ResGetLocationInfo) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((PoiData) objectRef3.element), 2000, 0, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(3, response…000, 0, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((PoiData) objectRef3.element), 2000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(3, response…000, 0, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v8, types: [T, java.lang.Object, java.lang.String] */
    public static final void getLocation$lambda$51(Ref.ObjectRef poiData, Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResGetLocationInfo resGetLocationInfo) throws JSONException {
        Intrinsics.checkNotNullParameter(poiData, "$poiData");
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resGetLocationInfo.getMessageType() == EMessageType.Result) {
            PoiData poiData2 = (PoiData) poiData.element;
            Float accuracy = resGetLocationInfo.getArguments().getLocationInfo().getAccuracy();
            Intrinsics.checkNotNullExpressionValue(accuracy, "it.arguments.locationInfo.accuracy");
            poiData2.setAccuracy(accuracy.floatValue());
            ((PoiData) poiData.element).setAdcode(resGetLocationInfo.getArguments().getLocationInfo().getAdCode());
            ((PoiData) poiData.element).setAddress(resGetLocationInfo.getArguments().getLocationInfo().getAddress());
            ((PoiData) poiData.element).setAreacode(resGetLocationInfo.getArguments().getLocationInfo().getAreaCode());
            ((PoiData) poiData.element).setCity(resGetLocationInfo.getArguments().getLocationInfo().getCity());
            ((PoiData) poiData.element).setCityadcode(resGetLocationInfo.getArguments().getLocationInfo().getCityCode());
            ((PoiData) poiData.element).setDistance(resGetLocationInfo.getArguments().getLocationInfo().getDistance().intValue());
            ((PoiData) poiData.element).setDistrict(resGetLocationInfo.getArguments().getLocationInfo().getDistrict());
            ((PoiData) poiData.element).setDistrictadcode(resGetLocationInfo.getArguments().getLocationInfo().getDistrictAdCode());
            PoiData poiData3 = (PoiData) poiData.element;
            Double latitude = resGetLocationInfo.getArguments().getLocationInfo().getGeoPosition().getLatitude();
            Intrinsics.checkNotNullExpressionValue(latitude, "it.arguments.locationInfo.geoPosition.latitude");
            poiData3.setLatitude(latitude.doubleValue());
            PoiData poiData4 = (PoiData) poiData.element;
            Double longitude = resGetLocationInfo.getArguments().getLocationInfo().getGeoPosition().getLongitude();
            Intrinsics.checkNotNullExpressionValue(longitude, "it.arguments.locationInfo.geoPosition.longitude");
            poiData4.setLongitude(longitude.doubleValue());
            ((PoiData) poiData.element).setName(resGetLocationInfo.getArguments().getLocationInfo().getName());
            ((PoiData) poiData.element).setPoiId(resGetLocationInfo.getArguments().getLocationInfo().getPoiId());
            ((PoiData) poiData.element).setProvince(resGetLocationInfo.getArguments().getLocationInfo().getProvince());
            ((PoiData) poiData.element).setProvinceadcode(resGetLocationInfo.getArguments().getLocationInfo().getProvinceAdCode());
            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(3, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((PoiData) poiData.element), 2000, 0, 0);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(3, response…ing(poiData), 2000, 0, 0)");
            mJsStr.element = JsonEncoding;
        } else {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(3, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((PoiData) poiData.element), 2000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(3, response…000, 0, unknownErrorCode)");
            mJsStr.element = JsonEncoding2;
        }
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v8, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1, types: [T, com.neusoft.na.ocservice.greatwallsdk.bean.StateData] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String getOverviewStatus() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = new StateData();
        if (this.requests != null) {
            mapToFrontDeskPrivate();
            GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestGetRouteOverviewState(new ReqGetRouteOverviewState()).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda58
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.getOverviewStatus$lambda$52(objectRef3, objectRef, this, objectRef2, (ResGetRouteOverviewState) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((StateData) objectRef3.element), 4000, 0, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(3, response…000, 0, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((StateData) objectRef3.element), 4000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(3, response…000, 0, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v8, types: [T, java.lang.Object, java.lang.String] */
    public static final void getOverviewStatus$lambda$52(Ref.ObjectRef stateData, Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResGetRouteOverviewState resGetRouteOverviewState) throws JSONException {
        Intrinsics.checkNotNullParameter(stateData, "$stateData");
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resGetRouteOverviewState.getMessageType() == EMessageType.Result) {
            StateData stateData2 = (StateData) stateData.element;
            Integer value = resGetRouteOverviewState.getArguments().getMode().getValue();
            Intrinsics.checkNotNullExpressionValue(value, "it.arguments.mode.value");
            stateData2.setState(value.intValue());
            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(3, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((StateData) stateData.element), 4000, 0, 0);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(3, response…g(stateData), 4000, 0, 0)");
            mJsStr.element = JsonEncoding;
        } else {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(3, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((StateData) stateData.element), 4000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(3, response…000, 0, unknownErrorCode)");
            mJsStr.element = JsonEncoding2;
        }
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String enterOverview() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        RouteOverviewMode routeOverviewMode = RouteOverviewMode.ROUTE_OVERVIEW_ON;
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestSwitchRouteOverviewInfo(new ReqSwitchRouteOverviewInfo(routeOverviewMode)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda66
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.enterOverview$lambda$53(objectRef, this, objectRef2, (ResSwitchRouteOverviewInfo) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfAsyncCall, 4000, 1, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(3, response…000, 1, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfAsyncCall, 4000, 1, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(3, response…000, 1, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void enterOverview$lambda$53(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResSwitchRouteOverviewInfo resSwitchRouteOverviewInfo) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resSwitchRouteOverviewInfo.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resSwitchRouteOverviewInfo.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(3, str, 4000, 1, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(3, this$0.responseAuthOfAsyncCall, 4000, 1, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String exitOverview() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        RouteOverviewMode routeOverviewMode = RouteOverviewMode.ROUTE_OVERVIEW_OFF;
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestSwitchRouteOverviewInfo(new ReqSwitchRouteOverviewInfo(routeOverviewMode)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda32
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.exitOverview$lambda$54(objectRef, this, objectRef2, (ResSwitchRouteOverviewInfo) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfAsyncCall, 4000, 2, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(3, response…000, 2, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfAsyncCall, 4000, 2, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(3, response…000, 2, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void exitOverview$lambda$54(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResSwitchRouteOverviewInfo resSwitchRouteOverviewInfo) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resSwitchRouteOverviewInfo.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resSwitchRouteOverviewInfo.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(3, str, 4000, 2, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(3, this$0.responseAuthOfAsyncCall, 4000, 2, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v8, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1, types: [T, com.neusoft.na.ocservice.greatwallsdk.bean.TrafficeStateData] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String getTrafficStatus() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = new TrafficeStateData();
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requestsCommonSysApi;
        if (commonSysApiInverseRequests != null) {
            Intrinsics.checkNotNull(commonSysApiInverseRequests);
            commonSysApiInverseRequests.requestGetTrafficInfoState(new ReqGetTrafficInfoState()).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda23
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.getTrafficStatus$lambda$55(objectRef3, objectRef, this, objectRef2, (ResGetTrafficInfoState) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((TrafficeStateData) objectRef3.element), 1000, 0, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response…000, 0, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((TrafficeStateData) objectRef3.element), 1000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…000, 0, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v3, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v8, types: [T, java.lang.Object, java.lang.String] */
    public static final void getTrafficStatus$lambda$55(Ref.ObjectRef trafficState, Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResGetTrafficInfoState resGetTrafficInfoState) throws JSONException {
        Intrinsics.checkNotNullParameter(trafficState, "$trafficState");
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resGetTrafficInfoState.getMessageType() == EMessageType.Result) {
            TrafficMode trafficMode = resGetTrafficInfoState.getArguments().getTrafficMode();
            int i = trafficMode == null ? -1 : WhenMappings.$EnumSwitchMapping$0[trafficMode.ordinal()];
            if (i == 1) {
                ((TrafficeStateData) trafficState.element).setTrafficeState(1);
            } else if (i == 2) {
                ((TrafficeStateData) trafficState.element).setTrafficeState(0);
            } else {
                TrafficeStateData trafficeStateData = (TrafficeStateData) trafficState.element;
                Integer value = resGetTrafficInfoState.getArguments().getTrafficMode().getValue();
                Intrinsics.checkNotNullExpressionValue(value, "it.arguments.trafficMode.value");
                trafficeStateData.setTrafficeState(value.intValue());
            }
            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((TrafficeStateData) trafficState.element), 1000, 0, 0);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response…rafficState), 1000, 0, 0)");
            mJsStr.element = JsonEncoding;
        } else {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((TrafficeStateData) trafficState.element), 1000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…000, 0, unknownErrorCode)");
            mJsStr.element = JsonEncoding2;
        }
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String openTraffic() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requestsCommonSysApi;
        if (commonSysApiInverseRequests != null) {
            Intrinsics.checkNotNull(commonSysApiInverseRequests);
            commonSysApiInverseRequests.requestSwitchTrafficInfo(new ReqSwitchTrafficInfo(TrafficMode.TRAFFIC_ON)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda63
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.openTraffic$lambda$56(objectRef, this, objectRef2, (ResSwitchTrafficInfo) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, 1000, 1, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response…000, 1, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, 1000, 1, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…000, 1, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void openTraffic$lambda$56(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResSwitchTrafficInfo resSwitchTrafficInfo) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resSwitchTrafficInfo.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resSwitchTrafficInfo.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(1, str, 1000, 1, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this$0.responseAuthOfAsyncCall, 1000, 1, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String closeTraffic() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requestsCommonSysApi;
        if (commonSysApiInverseRequests != null) {
            Intrinsics.checkNotNull(commonSysApiInverseRequests);
            commonSysApiInverseRequests.requestSwitchTrafficInfo(new ReqSwitchTrafficInfo(TrafficMode.TRAFFIC_OFF)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda62
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.closeTraffic$lambda$57(objectRef, this, objectRef2, (ResSwitchTrafficInfo) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, 1000, 2, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response…000, 2, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, 1000, 2, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…000, 2, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void closeTraffic$lambda$57(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResSwitchTrafficInfo resSwitchTrafficInfo) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resSwitchTrafficInfo.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resSwitchTrafficInfo.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(1, str, 1000, 2, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this$0.responseAuthOfAsyncCall, 1000, 2, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v8, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1, types: [T, com.neusoft.na.ocservice.greatwallsdk.bean.MapLevelData] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String getScaleSize() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = new MapLevelData();
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requestsCommonSysApi;
        if (commonSysApiInverseRequests != null) {
            Intrinsics.checkNotNull(commonSysApiInverseRequests);
            commonSysApiInverseRequests.requestGetMapScaleLevel(new ReqGetMapScaleLevel()).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda60
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.getScaleSize$lambda$58(objectRef3, objectRef, this, objectRef2, (ResGetMapScaleLevel) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((MapLevelData) objectRef3.element), 1000, 0, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(0, response…000, 0, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((MapLevelData) objectRef3.element), 1000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(0, response…000, 0, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v8, types: [T, java.lang.Object, java.lang.String] */
    public static final void getScaleSize$lambda$58(Ref.ObjectRef mapLevelData, Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResGetMapScaleLevel resGetMapScaleLevel) throws JSONException {
        Intrinsics.checkNotNullParameter(mapLevelData, "$mapLevelData");
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resGetMapScaleLevel.getMessageType() == EMessageType.Result) {
            MapLevelData mapLevelData2 = (MapLevelData) mapLevelData.element;
            Integer scaleId = resGetMapScaleLevel.getArguments().getScaleId();
            Intrinsics.checkNotNullExpressionValue(scaleId, "it.arguments.scaleId");
            mapLevelData2.setMapLevel(scaleId.intValue());
            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(0, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((MapLevelData) mapLevelData.element), 1000, 0, 0);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(0, response…apLevelData), 1000, 0, 0)");
            mJsStr.element = JsonEncoding;
        } else {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((MapLevelData) mapLevelData.element), 1000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(0, response…000, 0, unknownErrorCode)");
            mJsStr.element = JsonEncoding2;
        }
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String zoomIn() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        if (this.requestsCommonSysApi != null) {
            mapToFrontDeskPrivate();
            CommonSysApiInverseRequests commonSysApiInverseRequests = this.requestsCommonSysApi;
            Intrinsics.checkNotNull(commonSysApiInverseRequests);
            commonSysApiInverseRequests.requestMapZoomInOut(new ReqMapZoomInOut(ZoomInOutType.ZOOM_IN)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda31
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.zoomIn$lambda$59(objectRef, this, objectRef2, (ResMapZoomInOut) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, 1000, 1, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(0, response…000, 1, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, 1000, 1, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(0, response…000, 1, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void zoomIn$lambda$59(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResMapZoomInOut resMapZoomInOut) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resMapZoomInOut.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resMapZoomInOut.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(0, str, 1000, 1, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this$0.responseAuthOfAsyncCall, 1000, 1, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String zoomOut() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        if (this.requestsCommonSysApi != null) {
            mapToFrontDeskPrivate();
            CommonSysApiInverseRequests commonSysApiInverseRequests = this.requestsCommonSysApi;
            Intrinsics.checkNotNull(commonSysApiInverseRequests);
            commonSysApiInverseRequests.requestMapZoomInOut(new ReqMapZoomInOut(ZoomInOutType.ZOOM_OUT)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda34
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.zoomOut$lambda$60(objectRef, this, objectRef2, (ResMapZoomInOut) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, 1000, 2, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(0, response…000, 2, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, 1000, 2, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(0, response…000, 2, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void zoomOut$lambda$60(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResMapZoomInOut resMapZoomInOut) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resMapZoomInOut.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resMapZoomInOut.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(0, str, 1000, 2, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this$0.responseAuthOfAsyncCall, 1000, 2, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String mapToMaximum() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        if (this.requestsCommonSysApi != null) {
            mapToFrontDeskPrivate();
            CommonSysApiInverseRequests commonSysApiInverseRequests = this.requestsCommonSysApi;
            Intrinsics.checkNotNull(commonSysApiInverseRequests);
            commonSysApiInverseRequests.requestMapZoomInOut(new ReqMapZoomInOut(ZoomInOutType.ZOOM_IN_TO_MAX)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda22
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.mapToMaximum$lambda$61(objectRef, this, objectRef2, (ResMapZoomInOut) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, 1000, 3, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(0, response…000, 3, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, 1000, 3, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(0, response…000, 3, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void mapToMaximum$lambda$61(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResMapZoomInOut resMapZoomInOut) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resMapZoomInOut.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resMapZoomInOut.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(0, str, 1000, 3, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this$0.responseAuthOfAsyncCall, 1000, 3, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String mapToMinimize() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        if (this.requestsCommonSysApi != null) {
            mapToFrontDeskPrivate();
            CommonSysApiInverseRequests commonSysApiInverseRequests = this.requestsCommonSysApi;
            Intrinsics.checkNotNull(commonSysApiInverseRequests);
            commonSysApiInverseRequests.requestMapZoomInOut(new ReqMapZoomInOut(ZoomInOutType.ZOOM_OUT_TO_MIN)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda39
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.mapToMinimize$lambda$62(objectRef, this, objectRef2, (ResMapZoomInOut) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, 1000, 4, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(0, response…000, 4, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, 1000, 4, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(0, response…000, 4, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void mapToMinimize$lambda$62(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResMapZoomInOut resMapZoomInOut) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resMapZoomInOut.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resMapZoomInOut.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(0, str, 1000, 4, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this$0.responseAuthOfAsyncCall, 1000, 4, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v8, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1, types: [T, com.neusoft.na.ocservice.greatwallsdk.bean.MapModeData] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String getViewMode() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = new MapModeData();
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requestsCommonSysApi;
        if (commonSysApiInverseRequests != null) {
            Intrinsics.checkNotNull(commonSysApiInverseRequests);
            commonSysApiInverseRequests.requestGetMapViewMode(new ReqGetMapViewMode()).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda50
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.getViewMode$lambda$63(objectRef3, objectRef, this, objectRef2, (ResGetMapViewMode) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((MapModeData) objectRef3.element), 1000, 0, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(2, response…000, 0, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((MapModeData) objectRef3.element), 1000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(2, response…000, 0, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v8, types: [T, java.lang.Object, java.lang.String] */
    public static final void getViewMode$lambda$63(Ref.ObjectRef mapModeData, Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResGetMapViewMode resGetMapViewMode) throws JSONException {
        Intrinsics.checkNotNullParameter(mapModeData, "$mapModeData");
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resGetMapViewMode.getMessageType() == EMessageType.Result) {
            ViewModeType viewMode = resGetMapViewMode.getArguments().getViewMode();
            int i = viewMode == null ? -1 : WhenMappings.$EnumSwitchMapping$1[viewMode.ordinal()];
            if (i == 1) {
                ((MapModeData) mapModeData.element).setMapMode(0);
            } else if (i == 2) {
                ((MapModeData) mapModeData.element).setMapMode(1);
            } else {
                MapModeData mapModeData2 = (MapModeData) mapModeData.element;
                Integer value = resGetMapViewMode.getArguments().getViewMode().getValue();
                Intrinsics.checkNotNullExpressionValue(value, "it.arguments.viewMode.value");
                mapModeData2.setMapMode(value.intValue());
            }
            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(2, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((MapModeData) mapModeData.element), 1000, 0, 0);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(2, response…mapModeData), 1000, 0, 0)");
            mJsStr.element = JsonEncoding;
        } else {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((MapModeData) mapModeData.element), 1000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(2, response…000, 0, unknownErrorCode)");
            mJsStr.element = JsonEncoding2;
        }
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String setViewMode2DDueNorthUp() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        if (this.requestsCommonSysApi != null) {
            mapToFrontDeskPrivate();
            CommonSysApiInverseRequests commonSysApiInverseRequests = this.requestsCommonSysApi;
            Intrinsics.checkNotNull(commonSysApiInverseRequests);
            commonSysApiInverseRequests.requestSetMapViewMode(new ReqSetMapViewMode(ViewModeType.NORTH_UP)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda65
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.setViewMode2DDueNorthUp$lambda$64(objectRef, this, objectRef2, (ResSetMapViewMode) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, 1000, 1, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(2, response…000, 1, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, 1000, 1, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(2, response…000, 1, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void setViewMode2DDueNorthUp$lambda$64(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResSetMapViewMode resSetMapViewMode) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resSetMapViewMode.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resSetMapViewMode.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(2, str, 1000, 1, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this$0.responseAuthOfAsyncCall, 1000, 1, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String setViewMode2DHeadUp() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requestsCommonSysApi;
        if (commonSysApiInverseRequests != null) {
            Intrinsics.checkNotNull(commonSysApiInverseRequests);
            commonSysApiInverseRequests.requestSetMapViewMode(new ReqSetMapViewMode(ViewModeType.HEAD_UP)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda52
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.setViewMode2DHeadUp$lambda$65(objectRef, this, objectRef2, (ResSetMapViewMode) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, 1000, 2, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(2, response…000, 2, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, 1000, 2, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(2, response…000, 2, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void setViewMode2DHeadUp$lambda$65(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResSetMapViewMode resSetMapViewMode) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resSetMapViewMode.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resSetMapViewMode.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(2, str, 1000, 2, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this$0.responseAuthOfAsyncCall, 1000, 2, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String setViewMode3DHeadUp() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requestsCommonSysApi;
        if (commonSysApiInverseRequests != null) {
            Intrinsics.checkNotNull(commonSysApiInverseRequests);
            commonSysApiInverseRequests.requestSetMapViewMode(new ReqSetMapViewMode(ViewModeType.PERSPECTIVE_VIEW)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda67
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.setViewMode3DHeadUp$lambda$66(objectRef, this, objectRef2, (ResSetMapViewMode) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, 1000, 3, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(2, response…000, 3, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, 1000, 3, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(2, response…000, 3, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void setViewMode3DHeadUp$lambda$66(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResSetMapViewMode resSetMapViewMode) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resSetMapViewMode.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resSetMapViewMode.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(2, str, 1000, 3, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this$0.responseAuthOfAsyncCall, 1000, 3, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String mapToFrontDesk() throws JSONException {
        Log.i(TAG, "launchMap: ");
        Context context = this.context;
        Intrinsics.checkNotNull(context);
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.neusoft.na.navigation");
        if (launchIntentForPackage != null) {
            Context context2 = this.context;
            Intrinsics.checkNotNull(context2);
            context2.startActivity(launchIntentForPackage);
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(4, this.responseAuthOfAsyncCall, 1000, 0, 0);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n         context!!.sta…Call, 1000, 0, 0)\n      }");
            return strJsonEncoding;
        }
        String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(4, this.responseAuthOfAsyncCall, 1000, 0, 1);
        Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n         jsonEncoding(…Call, 1000, 0, 1)\n      }");
        return strJsonEncoding2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String mapToBackstage() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestStopGuidance(new ReqRequestStopGuidance()).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda36
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.mapToBackstage$lambda$67(objectRef, this, objectRef2, (ResRequestStopGuidance) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(4, this.responseAuthOfAsyncCall, 1000, 1, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(4, response…000, 1, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(4, this.responseAuthOfAsyncCall, 1000, 1, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(4, response…000, 1, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void mapToBackstage$lambda$67(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResRequestStopGuidance resRequestStopGuidance) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resRequestStopGuidance.getMessageType() == EMessageType.Result) {
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(4, this$0.responseAuthOfAsyncCall, 1000, 1, 0);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…1000, 1, 0)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(4, this$0.responseAuthOfAsyncCall, 1000, 1, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v8, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1, types: [T, com.neusoft.na.ocservice.greatwallsdk.bean.MapFrontStateData] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String getMapStatus() throws JSONException {
        CompletableFuture<ResGetNavigationActivityStatus> completableFutureRequestGetNavigationActivityStatus;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = new MapFrontStateData();
        if (this.requestsGreatWallSysApi != null) {
            CommonSysApiInverseRequests commonSysApiInverseRequests = this.requestsCommonSysApi;
            if (commonSysApiInverseRequests != null && (completableFutureRequestGetNavigationActivityStatus = commonSysApiInverseRequests.requestGetNavigationActivityStatus()) != null) {
                final Function2<ResGetNavigationActivityStatus, Throwable, Unit> function2 = new Function2<ResGetNavigationActivityStatus, Throwable, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl.getMapStatus.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ResGetNavigationActivityStatus resGetNavigationActivityStatus, Throwable th) throws JSONException {
                        invoke2(resGetNavigationActivityStatus, th);
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object, java.lang.String] */
                    /* JADX WARN: Type inference failed for: r8v12, types: [T, java.lang.Object, java.lang.String] */
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ResGetNavigationActivityStatus resGetNavigationActivityStatus, Throwable th) throws JSONException {
                        try {
                            Integer navigationActivityStatus = resGetNavigationActivityStatus.getNavigationActivityStatus().getValue();
                            MapFrontStateData mapFrontStateData = objectRef3.element;
                            Intrinsics.checkNotNullExpressionValue(navigationActivityStatus, "navigationActivityStatus");
                            mapFrontStateData.setMapFrontState(navigationActivityStatus.intValue());
                            Ref.ObjectRef<String> objectRef4 = objectRef;
                            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(4, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(objectRef3.element), 1000, 2, 0);
                            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(4, response…tStatusData), 1000, 2, 0)");
                            objectRef4.element = JsonEncoding;
                            objectRef2.element.countDown();
                        } catch (Exception e) {
                            Ref.ObjectRef<String> objectRef5 = objectRef;
                            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(4, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(objectRef3.element), 1000, 2, this.unknownErrorCode);
                            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(4, response…000, 2, unknownErrorCode)");
                            objectRef5.element = JsonEncoding2;
                            objectRef2.element.countDown();
                            e.printStackTrace();
                        }
                    }
                };
                completableFutureRequestGetNavigationActivityStatus.whenComplete(new BiConsumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda37
                    @Override // java9.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        GreatWallApiOctaImpl.getMapStatus$lambda$68(function2, obj, obj2);
                    }
                });
            }
            try {
                if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                    ?? JsonEncoding = GreatWallApiTool.jsonEncoding(4, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((MapFrontStateData) objectRef3.element), 1000, 2, this.unknownErrorCode);
                    Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(4, response…000, 2, unknownErrorCode)");
                    objectRef.element = JsonEncoding;
                    Log.i(TAG, "mLatch await timeout.");
                }
            } catch (InterruptedException e) {
                ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(4, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((MapFrontStateData) objectRef3.element), 1000, 2, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(4, response…000, 2, unknownErrorCode)");
                objectRef.element = JsonEncoding2;
                e.printStackTrace();
            }
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getMapStatus$lambda$68(Function2 tmp0, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj, obj2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String openSettingsMenu() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        if (this.requests != null) {
            mapToFrontDeskPrivate();
            GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestJumpToViewPage(new ReqRequestJumpToViewPage(ViewPage.NAV_SETTING_PAGE)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda53
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.openSettingsMenu$lambda$69(objectRef, this, objectRef2, (ResRequestJumpToViewPage) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, 8000, 0, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(2, response…000, 0, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, 8000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(2, response…000, 0, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void openSettingsMenu$lambda$69(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResRequestJumpToViewPage resRequestJumpToViewPage) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resRequestJumpToViewPage.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resRequestJumpToViewPage.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(2, str, 8000, 0, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this$0.responseAuthOfAsyncCall, 8000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v8, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1, types: [T, com.neusoft.na.ocservice.greatwallsdk.bean.ResultPoiListData] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String historicalDestinations() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = new ResultPoiListData();
        if (this.requests != null) {
            mapToFrontDeskPrivate();
            GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestGetDestinationHistoryList(new ReqRequestGetDestinationHistoryList()).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda49
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.historicalDestinations$lambda$70(objectRef3, objectRef, this, objectRef2, (ResRequestGetDestinationHistoryList) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((ResultPoiListData) objectRef3.element), 8000, 0, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(3, response…000, 0, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((ResultPoiListData) objectRef3.element), 8000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(3, response…000, 0, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v8, types: [T, java.lang.Object, java.lang.String] */
    public static final void historicalDestinations$lambda$70(Ref.ObjectRef resultList, Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResRequestGetDestinationHistoryList resRequestGetDestinationHistoryList) throws JSONException {
        Intrinsics.checkNotNullParameter(resultList, "$resultList");
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        ArrayList arrayList = new ArrayList();
        if (resRequestGetDestinationHistoryList.getMessageType() == EMessageType.Result) {
            Intrinsics.checkNotNullExpressionValue(resRequestGetDestinationHistoryList.getArguments().getDestHistoryList(), "it.arguments.destHistoryList");
            if (!r1.isEmpty()) {
                for (DestHistoryInfo destHistoryInfo : resRequestGetDestinationHistoryList.getArguments().getDestHistoryList()) {
                    Intrinsics.checkNotNullExpressionValue(destHistoryInfo, "it.arguments.destHistoryList");
                    DestHistoryInfo destHistoryInfo2 = destHistoryInfo;
                    PoiData poiData = new PoiData();
                    Float accuracy = destHistoryInfo2.getLocationInfo().getAccuracy();
                    Intrinsics.checkNotNullExpressionValue(accuracy, "iDestHistoryInfo.locationInfo.accuracy");
                    poiData.setAccuracy(accuracy.floatValue());
                    Float accuracy2 = destHistoryInfo2.getLocationInfo().getAccuracy();
                    Intrinsics.checkNotNullExpressionValue(accuracy2, "iDestHistoryInfo.locationInfo.accuracy");
                    poiData.setAccuracy(accuracy2.floatValue());
                    poiData.setAdcode(destHistoryInfo2.getLocationInfo().getAdCode());
                    poiData.setAddress(destHistoryInfo2.getLocationInfo().getAddress());
                    poiData.setAreacode(destHistoryInfo2.getLocationInfo().getAreaCode());
                    poiData.setCity(destHistoryInfo2.getLocationInfo().getCity());
                    poiData.setCityadcode(destHistoryInfo2.getLocationInfo().getCityCode());
                    poiData.setDistance(destHistoryInfo2.getLocationInfo().getDistance().intValue());
                    poiData.setDistrict(destHistoryInfo2.getLocationInfo().getDistrict());
                    poiData.setDistrictadcode(destHistoryInfo2.getLocationInfo().getDistrictAdCode());
                    Double latitude = destHistoryInfo2.getLocationInfo().getGeoPosition().getLatitude();
                    Intrinsics.checkNotNullExpressionValue(latitude, "iDestHistoryInfo.locationInfo.geoPosition.latitude");
                    poiData.setLatitude(latitude.doubleValue());
                    Double longitude = destHistoryInfo2.getLocationInfo().getGeoPosition().getLongitude();
                    Intrinsics.checkNotNullExpressionValue(longitude, "iDestHistoryInfo.locatio…nfo.geoPosition.longitude");
                    poiData.setLongitude(longitude.doubleValue());
                    poiData.setName(destHistoryInfo2.getLocationInfo().getName());
                    poiData.setPoiId(destHistoryInfo2.getLocationInfo().getPoiId());
                    poiData.setProvince(destHistoryInfo2.getLocationInfo().getProvince());
                    poiData.setProvinceadcode(destHistoryInfo2.getLocationInfo().getProvinceAdCode());
                    arrayList.add(poiData);
                }
            }
            ((ResultPoiListData) resultList.element).setResult(arrayList);
            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(3, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((ResultPoiListData) resultList.element), 8000, 0, 0);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(3, response…(resultList), 8000, 0, 0)");
            mJsStr.element = JsonEncoding;
        } else {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(3, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((ResultPoiListData) resultList.element), 8000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(3, response…000, 0, unknownErrorCode)");
            mJsStr.element = JsonEncoding2;
        }
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String displayHistoricalDestination() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        if (this.requests != null) {
            mapToFrontDeskPrivate();
            GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestJumpToViewPage(new ReqRequestJumpToViewPage(ViewPage.NAV_HISTORY_LIST_PAGE)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda28
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.displayHistoricalDestination$lambda$71(objectRef, this, objectRef2, (ResRequestJumpToViewPage) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfAsyncCall, 8000, 1, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(3, response…000, 1, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(3, this.responseAuthOfAsyncCall, 8000, 1, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(3, response…000, 1, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void displayHistoricalDestination$lambda$71(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResRequestJumpToViewPage resRequestJumpToViewPage) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resRequestJumpToViewPage.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resRequestJumpToViewPage.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(3, str, 8000, 1, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(3, this$0.responseAuthOfAsyncCall, 8000, 1, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r9v2, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String setNavigationPersonalizationData(String BeanId, String result) throws JSONException {
        Intrinsics.checkNotNullParameter(BeanId, "BeanId");
        Intrinsics.checkNotNullParameter(result, "result");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        GreatWallSysApiInverseRequests greatWallSysApiInverseRequests = this.requestsGreatWallSysApi;
        if (greatWallSysApiInverseRequests != null) {
            Intrinsics.checkNotNull(greatWallSysApiInverseRequests);
            greatWallSysApiInverseRequests.requestSetUserProfileData(new ReqSetUserProfileData(new UserProfileData(BeanId, result))).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda61
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.setNavigationPersonalizationData$lambda$72(objectRef, this, objectRef2, (ResSetUserProfileData) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfNotifications, 50000, 0, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response…000, 0, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfNotifications, 50000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…000, 0, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        Log.i(TAG, "setNavigationPersonalizationData:" + ((String) objectRef.element));
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void setNavigationPersonalizationData$lambda$72(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResSetUserProfileData resSetUserProfileData) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resSetUserProfileData.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfNotifications;
            Integer resultCode = resSetUserProfileData.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(1, str, 50000, 0, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this$0.responseAuthOfNotifications, 50000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v12, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r5v1, types: [T, com.neusoft.na.ocservice.greatwallsdk.bean.UserProfileRecordData] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String getNavigationPersonalizationData(String BeanId) throws JSONException {
        Intrinsics.checkNotNullParameter(BeanId, "BeanId");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = new UserProfileRecordData();
        GreatWallSysApiInverseRequests greatWallSysApiInverseRequests = this.requestsGreatWallSysApi;
        if (greatWallSysApiInverseRequests != null) {
            Intrinsics.checkNotNull(greatWallSysApiInverseRequests);
            greatWallSysApiInverseRequests.requestGetUserProfileData(new ReqGetUserProfileData(BeanId)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda26
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.getNavigationPersonalizationData$lambda$73(objectRef3, objectRef, this, objectRef2, (ResGetUserProfileData) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfNotifications, GreatWallApiTool.jsonEncoding((UserProfileRecordData) objectRef3.element), 50000, 1, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response…000, 1, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfNotifications, GreatWallApiTool.jsonEncoding((UserProfileRecordData) objectRef3.element), 50000, 1, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…000, 1, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        Log.i(TAG, "getNavigationPersonalizationData:" + ((String) objectRef.element));
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v8, types: [T, java.lang.Object, java.lang.String] */
    public static final void getNavigationPersonalizationData$lambda$73(Ref.ObjectRef userProfileRecordData, Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResGetUserProfileData resGetUserProfileData) throws JSONException {
        Intrinsics.checkNotNullParameter(userProfileRecordData, "$userProfileRecordData");
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resGetUserProfileData.getMessageType() == EMessageType.Result) {
            ((UserProfileRecordData) userProfileRecordData.element).setBeanId(resGetUserProfileData.getArguments().getUserProfileData().getUserId());
            ((UserProfileRecordData) userProfileRecordData.element).setResult(resGetUserProfileData.getArguments().getUserProfileData().getData());
            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, this$0.responseAuthOfNotifications, GreatWallApiTool.jsonEncoding((UserProfileRecordData) userProfileRecordData.element), 50000, 1, 0);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response…RecordData), 50000, 1, 0)");
            mJsStr.element = JsonEncoding;
        } else {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this$0.responseAuthOfNotifications, GreatWallApiTool.jsonEncoding((UserProfileRecordData) userProfileRecordData.element), 50000, 1, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…000, 1, unknownErrorCode)");
            mJsStr.element = JsonEncoding2;
        }
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v2, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v0, types: [T, java.util.concurrent.CountDownLatch] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String deleteNavigationPersonalizationData(String BeanId) throws JSONException {
        Intrinsics.checkNotNullParameter(BeanId, "BeanId");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        GreatWallSysApiInverseRequests greatWallSysApiInverseRequests = this.requestsGreatWallSysApi;
        if (greatWallSysApiInverseRequests != null) {
            Intrinsics.checkNotNull(greatWallSysApiInverseRequests);
            greatWallSysApiInverseRequests.requestClearUserProfileData(new ReqClearUserProfileData(BeanId)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda68
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.deleteNavigationPersonalizationData$lambda$74(objectRef, this, objectRef2, (ResClearUserProfileData) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfNotifications, 50000, 2, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response…000, 2, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfNotifications, 50000, 2, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…000, 2, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        Log.i(TAG, "deleteNavigationPersonalizationData:" + ((String) objectRef.element));
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v2, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v4, types: [T, java.lang.Object, java.lang.String] */
    public static final void deleteNavigationPersonalizationData$lambda$74(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResClearUserProfileData resClearUserProfileData) throws JSONException {
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resClearUserProfileData.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfNotifications;
            Integer resultCode = resClearUserProfileData.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, str, 50000, 2, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response… it.arguments.resultCode)");
            mJsStr.element = JsonEncoding;
        } else {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this$0.responseAuthOfNotifications, 50000, 2, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…000, 2, unknownErrorCode)");
            mJsStr.element = JsonEncoding2;
        }
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v3, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, com.neusoft.na.ocservice.greatwallsdk.bean.ResultPoiListData] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public void poiSearch(int reqId, String keyWord, final IGWRequestCallback callback) throws JSONException {
        Intrinsics.checkNotNullParameter(keyWord, "keyWord");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Log.i(TAG, "getFavoritePois:reqId" + reqId + " favType:" + keyWord);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new ResultPoiListData();
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestSearchKeyword(new ReqRequestSearchKeyword(Integer.valueOf(reqId), SearchActionType.SEARCH_ONLY_IN_THROUGH_INFORMATION, SearchCategory.KEY_WORD_SEARCH, keyWord)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda44
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.poiSearch$lambda$75(objectRef2, objectRef, this, callback, (ResRequestSearchKeyword) obj);
                }
            });
            return;
        }
        Log.e(TAG, "poiSearch:requests is NULL");
        ((ResultPoiListData) objectRef2.element).setRequestID(reqId);
        ?? JsonEncoding = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((ResultPoiListData) objectRef2.element), 2000, 0, this.unknownErrorCode);
        Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(0, response…000, 0, unknownErrorCode)");
        objectRef.element = JsonEncoding;
        callback.onCallBack((String) objectRef.element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v14, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v3, types: [T, java.lang.Object, java.lang.String] */
    public static final void poiSearch$lambda$75(Ref.ObjectRef resultList, Ref.ObjectRef callbackStr, GreatWallApiOctaImpl this$0, IGWRequestCallback callback, ResRequestSearchKeyword resRequestSearchKeyword) throws JSONException {
        Intrinsics.checkNotNullParameter(resultList, "$resultList");
        Intrinsics.checkNotNullParameter(callbackStr, "$callbackStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        ArrayList arrayList = new ArrayList();
        if (resRequestSearchKeyword.getMessageType() == EMessageType.Result) {
            List<SearchResult> result = resRequestSearchKeyword.getArguments().getResult();
            Intrinsics.checkNotNullExpressionValue(result, "it.arguments.result");
            if (!result.isEmpty()) {
                int size = result.size();
                for (int i = 0; i < size; i++) {
                    PoiData poiData = new PoiData();
                    Float accuracy = result.get(i).getLocationInfo().getAccuracy();
                    Intrinsics.checkNotNullExpressionValue(accuracy, "poiSearchList[i].locationInfo.accuracy");
                    poiData.setAccuracy(accuracy.floatValue());
                    poiData.setAdcode(result.get(i).getLocationInfo().getAdCode());
                    poiData.setAddress(result.get(i).getLocationInfo().getAddress());
                    poiData.setAreacode(result.get(i).getLocationInfo().getAreaCode());
                    poiData.setCity(result.get(i).getLocationInfo().getCity());
                    poiData.setCityadcode(result.get(i).getLocationInfo().getCityCode());
                    poiData.setDistance(result.get(i).getLocationInfo().getDistance().intValue());
                    poiData.setDistrict(result.get(i).getLocationInfo().getDistrict());
                    poiData.setDistrictadcode(result.get(i).getLocationInfo().getDistrictAdCode());
                    Double latitude = result.get(i).getLocationInfo().getGeoPosition().getLatitude();
                    Intrinsics.checkNotNullExpressionValue(latitude, "poiSearchList[i].locationInfo.geoPosition.latitude");
                    poiData.setLatitude(latitude.doubleValue());
                    Double longitude = result.get(i).getLocationInfo().getGeoPosition().getLongitude();
                    Intrinsics.checkNotNullExpressionValue(longitude, "poiSearchList[i].locatio…nfo.geoPosition.longitude");
                    poiData.setLongitude(longitude.doubleValue());
                    poiData.setName(result.get(i).getLocationInfo().getName());
                    poiData.setPoiId(result.get(i).getLocationInfo().getPoiId());
                    poiData.setProvince(result.get(i).getLocationInfo().getProvince());
                    poiData.setProvinceadcode(result.get(i).getLocationInfo().getProvinceAdCode());
                    arrayList.add(poiData);
                }
            }
            ((ResultPoiListData) resultList.element).setResult(arrayList);
            ResultPoiListData resultPoiListData = (ResultPoiListData) resultList.element;
            Integer reqId_Out = resRequestSearchKeyword.getArguments().getReqId_Out();
            Intrinsics.checkNotNullExpressionValue(reqId_Out, "it.arguments.reqId_Out");
            resultPoiListData.setRequestID(reqId_Out.intValue());
            String str = this$0.responseAuthOfAsyncCall;
            String strJsonEncoding = GreatWallApiTool.jsonEncoding((ResultPoiListData) resultList.element);
            Integer resultCode = resRequestSearchKeyword.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(0, str, strJsonEncoding, 2000, 0, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(0, response… it.arguments.resultCode)");
            callbackStr.element = JsonEncoding;
        } else {
            ResultPoiListData resultPoiListData2 = (ResultPoiListData) resultList.element;
            Integer reqId_Out2 = resRequestSearchKeyword.getArguments().getReqId_Out();
            Intrinsics.checkNotNullExpressionValue(reqId_Out2, "it.arguments.reqId_Out");
            resultPoiListData2.setRequestID(reqId_Out2.intValue());
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(0, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding((ResultPoiListData) resultList.element), 2000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(0, response…000, 0, unknownErrorCode)");
            callbackStr.element = JsonEncoding2;
        }
        Log.i(TAG, "poiSearch:" + ((String) callbackStr.element));
        callback.onCallBack((String) callbackStr.element);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r9v2, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String openMapWithLocation(GeoPosition position) throws JSONException {
        Intrinsics.checkNotNullParameter(position, "position");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        LocationInfo locationInfo = new LocationInfo();
        if (this.requests != null) {
            mapToFrontDeskPrivate();
            locationInfo.getGeoPosition().setLatitude(Double.valueOf(position.getLatitude()));
            locationInfo.getGeoPosition().setLongitude(Double.valueOf(position.getLongitude()));
            GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestOpenMap(new ReqRequestOpenMap(locationInfo)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda42
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.openMapWithLocation$lambda$76(objectRef, this, objectRef2, (ResRequestOpenMap) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfNotifications, 10000, 0, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(2, response…000, 0, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfNotifications, 10000, 0, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(2, response…000, 0, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void openMapWithLocation$lambda$76(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResRequestOpenMap resRequestOpenMap) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resRequestOpenMap.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfNotifications;
            Integer resultCode = resRequestOpenMap.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(2, str, 10000, 0, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this$0.responseAuthOfNotifications, 10000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v3, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public void calculateRoad(int atcionType, PoiData endPoi, PoiData startPoi, List<? extends PoiData> wayPoints, final IGWRequestCallback callback) throws JSONException {
        Intrinsics.checkNotNullParameter(endPoi, "endPoi");
        Intrinsics.checkNotNullParameter(startPoi, "startPoi");
        Log.i(TAG, "calculateRoad");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final ResultData resultData = new ResultData();
        CalcRouteCondition calcRouteCondition = new CalcRouteCondition();
        ArrayList arrayList = new ArrayList();
        if (this.requests != null) {
            mapToFrontDeskPrivate();
            if (wayPoints != null) {
                for (PoiData poiData : wayPoints) {
                    WaypointInfo waypointInfo = new WaypointInfo();
                    waypointInfo.getLocationInfo().setName(poiData.getName());
                    waypointInfo.getLocationInfo().setAccuracy(Float.valueOf(poiData.getAccuracy()));
                    waypointInfo.getLocationInfo().setAdCode(poiData.getAdcode());
                    waypointInfo.getLocationInfo().setAddress(poiData.getAddress());
                    waypointInfo.getLocationInfo().setAreaCode(poiData.getAreacode());
                    waypointInfo.getLocationInfo().setCity(poiData.getCity());
                    waypointInfo.getLocationInfo().setCityCode(poiData.getCityadcode());
                    waypointInfo.getLocationInfo().setDistance(Integer.valueOf((int) poiData.getDistance()));
                    waypointInfo.getLocationInfo().setDistrict(poiData.getDistrict());
                    waypointInfo.getLocationInfo().setDistrictAdCode(poiData.getDistrictadcode());
                    waypointInfo.getLocationInfo().getGeoPosition().setLatitude(Double.valueOf(poiData.getLatitude()));
                    waypointInfo.getLocationInfo().getGeoPosition().setLongitude(Double.valueOf(poiData.getLongitude()));
                    waypointInfo.setName(poiData.getName());
                    waypointInfo.setIndex(-1);
                    waypointInfo.getLocationInfo().setPoiId(poiData.getPoiId());
                    waypointInfo.getLocationInfo().setProvince(poiData.getProvince());
                    waypointInfo.getLocationInfo().setProvinceAdCode(poiData.getProvinceadcode());
                    arrayList.add(waypointInfo);
                }
            }
            WaypointInfo waypointInfo2 = new WaypointInfo();
            waypointInfo2.getLocationInfo().setName(endPoi.getName());
            waypointInfo2.getLocationInfo().setAccuracy(Float.valueOf(endPoi.getAccuracy()));
            waypointInfo2.getLocationInfo().setAdCode(endPoi.getAdcode());
            waypointInfo2.getLocationInfo().setAddress(endPoi.getAddress());
            waypointInfo2.getLocationInfo().setAreaCode(endPoi.getAreacode());
            waypointInfo2.getLocationInfo().setCity(endPoi.getCity());
            waypointInfo2.getLocationInfo().setCityCode(endPoi.getCityadcode());
            waypointInfo2.getLocationInfo().setDistance(Integer.valueOf((int) endPoi.getDistance()));
            waypointInfo2.getLocationInfo().setDistrict(endPoi.getDistrict());
            waypointInfo2.getLocationInfo().setDistrictAdCode(endPoi.getDistrictadcode());
            waypointInfo2.getLocationInfo().getGeoPosition().setLatitude(Double.valueOf(endPoi.getLatitude()));
            waypointInfo2.getLocationInfo().getGeoPosition().setLongitude(Double.valueOf(endPoi.getLongitude()));
            waypointInfo2.setName(endPoi.getName());
            waypointInfo2.setIndex(-1);
            waypointInfo2.getLocationInfo().setPoiId(endPoi.getPoiId());
            waypointInfo2.getLocationInfo().setProvince(endPoi.getProvince());
            waypointInfo2.getLocationInfo().setProvinceAdCode(endPoi.getProvinceadcode());
            arrayList.add(waypointInfo2);
            calcRouteCondition.setWaypointList(arrayList);
            if (atcionType == 0) {
                calcRouteCondition.setActionType(CalcRouteActionType.CALC_PLANNING_ROUTE);
            } else {
                calcRouteCondition.setActionType(CalcRouteActionType.CALC_PLANNING_ROUTE_AND_START_GUIDANCE);
            }
            GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestCalcPlanningRoute(new ReqRequestCalcPlanningRoute(calcRouteCondition)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda24
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.calculateRoad$lambda$77(this.f$0, callback, objectRef, resultData, (ResRequestCalcPlanningRoute) obj);
                }
            });
            return;
        }
        Log.e(TAG, "calculateRoad:rqeuests is NULL");
        this.calcPlanningRouteCallBack = null;
        ?? JsonEncoding = GreatWallApiTool.jsonEncoding(0, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(resultData), 3000, 0, this.unknownErrorCode);
        Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(0, response…000, 0, unknownErrorCode)");
        objectRef.element = JsonEncoding;
        if (callback != null) {
            callback.onCallBack((String) objectRef.element);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v1, types: [T, java.lang.Object, java.lang.String] */
    public static final void calculateRoad$lambda$77(GreatWallApiOctaImpl this$0, IGWRequestCallback iGWRequestCallback, Ref.ObjectRef callbackStr, ResultData result, ResRequestCalcPlanningRoute resRequestCalcPlanningRoute) throws JSONException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callbackStr, "$callbackStr");
        Intrinsics.checkNotNullParameter(result, "$result");
        if (resRequestCalcPlanningRoute.getMessageType() == EMessageType.Result) {
            this$0.calcPlanningRouteCallBack = iGWRequestCallback;
            Log.i(TAG, "calculateRoad:" + ((String) callbackStr.element));
            return;
        }
        this$0.calcPlanningRouteCallBack = null;
        ?? JsonEncoding = GreatWallApiTool.jsonEncoding(0, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(result), 3000, 0, this$0.unknownErrorCode);
        Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(0, response…000, 0, unknownErrorCode)");
        callbackStr.element = JsonEncoding;
        if (iGWRequestCallback != null) {
            iGWRequestCallback.onCallBack((String) callbackStr.element);
        }
        Log.i(TAG, "calculateRoad:unknownError");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public void peripheralSearch(int reqId, PeripheralData peripheralData, final IGWRequestCallback callback, final int operaType) throws JSONException {
        SearchActionType searchActionType;
        Intrinsics.checkNotNullParameter(peripheralData, "peripheralData");
        Log.i(TAG, "peripheralSearch:reqID" + reqId);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final ResultPoiListData resultPoiListData = new ResultPoiListData();
        final ArrayList arrayList = new ArrayList();
        if (operaType != 0 && operaType == 1) {
            mapToFrontDeskPrivate();
            searchActionType = SearchActionType.SEARCH_IN_MAP;
        } else {
            searchActionType = SearchActionType.SEARCH_ONLY_IN_THROUGH_INFORMATION;
        }
        SearchActionType searchActionType2 = searchActionType;
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestSearchAround(new ReqRequestSearchAround(Integer.valueOf(reqId), searchActionType2, SearchCategory.getByValue(Integer.valueOf(peripheralData.getCategory())), peripheralData.getKeyWord(), 0, new api.structs.com.neusoft.greatwall.GeoPosition(Double.valueOf(peripheralData.getPoi().getLatitude()), Double.valueOf(peripheralData.getPoi().getLongitude())))).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda55
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.peripheralSearch$lambda$78(arrayList, resultPoiListData, objectRef, this, operaType, callback, (ResRequestSearchAround) obj);
                }
            });
            return;
        }
        Log.e(TAG, "searchAlong:requests is NULL");
        resultPoiListData.setRequestID(reqId);
        ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(resultPoiListData), 2000, operaType, this.unknownErrorCode);
        Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response…raType, unknownErrorCode)");
        objectRef.element = JsonEncoding;
        if (callback != null) {
            callback.onCallBack((String) objectRef.element);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v19, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v5, types: [T, java.lang.Object, java.lang.String] */
    public static final void peripheralSearch$lambda$78(ArrayList poiResultList, ResultPoiListData resultList, Ref.ObjectRef callbackStr, GreatWallApiOctaImpl this$0, int i, IGWRequestCallback iGWRequestCallback, ResRequestSearchAround resRequestSearchAround) throws JSONException {
        Intrinsics.checkNotNullParameter(poiResultList, "$poiResultList");
        Intrinsics.checkNotNullParameter(resultList, "$resultList");
        Intrinsics.checkNotNullParameter(callbackStr, "$callbackStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        List<SearchResult> result = resRequestSearchAround.getArguments().getResult();
        Intrinsics.checkNotNullExpressionValue(result, "it.arguments.result");
        if (resRequestSearchAround.getMessageType() == EMessageType.Result) {
            if (!result.isEmpty()) {
                int size = result.size();
                for (int i2 = 0; i2 < size; i2++) {
                    PoiData poiData = new PoiData();
                    Float accuracy = result.get(i2).getLocationInfo().getAccuracy();
                    Intrinsics.checkNotNullExpressionValue(accuracy, "peripheralSearchList[i].locationInfo.accuracy");
                    poiData.setAccuracy(accuracy.floatValue());
                    poiData.setAdcode(result.get(i2).getLocationInfo().getAdCode());
                    poiData.setAddress(result.get(i2).getLocationInfo().getAddress());
                    poiData.setAreacode(result.get(i2).getLocationInfo().getAreaCode());
                    poiData.setCity(result.get(i2).getLocationInfo().getCity());
                    poiData.setCityadcode(result.get(i2).getLocationInfo().getCityCode());
                    poiData.setDistance(result.get(i2).getLocationInfo().getDistance().intValue());
                    poiData.setDistrict(result.get(i2).getLocationInfo().getDistrict());
                    poiData.setDistrictadcode(result.get(i2).getLocationInfo().getDistrictAdCode());
                    Double latitude = result.get(i2).getLocationInfo().getGeoPosition().getLatitude();
                    Intrinsics.checkNotNullExpressionValue(latitude, "peripheralSearchList[i].…Info.geoPosition.latitude");
                    poiData.setLatitude(latitude.doubleValue());
                    Double longitude = result.get(i2).getLocationInfo().getGeoPosition().getLongitude();
                    Intrinsics.checkNotNullExpressionValue(longitude, "peripheralSearchList[i].…nfo.geoPosition.longitude");
                    poiData.setLongitude(longitude.doubleValue());
                    poiData.setName(result.get(i2).getLocationInfo().getName());
                    poiData.setPoiId(result.get(i2).getLocationInfo().getPoiId());
                    poiData.setProvince(result.get(i2).getLocationInfo().getProvince());
                    poiData.setProvinceadcode(result.get(i2).getLocationInfo().getProvinceAdCode());
                    poiResultList.add(poiData);
                }
            }
            resultList.setResult(poiResultList);
            Integer reqId_Out = resRequestSearchAround.getArguments().getReqId_Out();
            Intrinsics.checkNotNullExpressionValue(reqId_Out, "it.arguments.reqId_Out");
            resultList.setRequestID(reqId_Out.intValue());
            String str = this$0.responseAuthOfAsyncCall;
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(resultList);
            Integer resultCode = resRequestSearchAround.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, str, strJsonEncoding, 2000, i, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response… it.arguments.resultCode)");
            callbackStr.element = JsonEncoding;
        } else {
            Integer reqId_Out2 = resRequestSearchAround.getArguments().getReqId_Out();
            Intrinsics.checkNotNullExpressionValue(reqId_Out2, "it.arguments.reqId_Out");
            resultList.setRequestID(reqId_Out2.intValue());
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(resultList), 2000, i, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…raType, unknownErrorCode)");
            callbackStr.element = JsonEncoding2;
        }
        Log.i(TAG, "searchAlong:" + ((String) callbackStr.element));
        if (iGWRequestCallback != null) {
            iGWRequestCallback.onCallBack((String) callbackStr.element);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v1, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public void searchAlong(int reqId, String keyWord, final IGWRequestCallback callback) throws JSONException {
        Intrinsics.checkNotNullParameter(keyWord, "keyWord");
        Log.i(TAG, "searchAlong:reqid:" + reqId + " keyWord" + keyWord);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final ResultPoiListData resultPoiListData = new ResultPoiListData();
        final ArrayList arrayList = new ArrayList();
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null) {
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestAlongRouteSearch(new ReqRequestAlongRouteSearch(Integer.valueOf(reqId), keyWord)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda33
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.searchAlong$lambda$79(arrayList, resultPoiListData, objectRef, this, callback, (ResRequestAlongRouteSearch) obj);
                }
            });
            return;
        }
        Log.e(TAG, "searchAlong:requests is NULL");
        resultPoiListData.setRequestID(reqId);
        ?? JsonEncoding = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(resultPoiListData), 2000, 0, this.unknownErrorCode);
        Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(2, response…000, 0, unknownErrorCode)");
        objectRef.element = JsonEncoding;
        if (callback != null) {
            callback.onCallBack((String) objectRef.element);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v19, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v5, types: [T, java.lang.Object, java.lang.String] */
    public static final void searchAlong$lambda$79(ArrayList poiResultList, ResultPoiListData resultList, Ref.ObjectRef callbackStr, GreatWallApiOctaImpl this$0, IGWRequestCallback iGWRequestCallback, ResRequestAlongRouteSearch resRequestAlongRouteSearch) throws JSONException {
        Intrinsics.checkNotNullParameter(poiResultList, "$poiResultList");
        Intrinsics.checkNotNullParameter(resultList, "$resultList");
        Intrinsics.checkNotNullParameter(callbackStr, "$callbackStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        List<SearchResult> result = resRequestAlongRouteSearch.getArguments().getResult();
        Intrinsics.checkNotNullExpressionValue(result, "it.arguments.result");
        if (resRequestAlongRouteSearch.getMessageType() == EMessageType.Result) {
            if (!result.isEmpty()) {
                int size = result.size();
                for (int i = 0; i < size; i++) {
                    PoiData poiData = new PoiData();
                    Float accuracy = result.get(i).getLocationInfo().getAccuracy();
                    Intrinsics.checkNotNullExpressionValue(accuracy, "searchList[i].locationInfo.accuracy");
                    poiData.setAccuracy(accuracy.floatValue());
                    poiData.setAdcode(result.get(i).getLocationInfo().getAdCode());
                    poiData.setAddress(result.get(i).getLocationInfo().getAddress());
                    poiData.setAreacode(result.get(i).getLocationInfo().getAreaCode());
                    poiData.setCity(result.get(i).getLocationInfo().getCity());
                    poiData.setCityadcode(result.get(i).getLocationInfo().getCityCode());
                    poiData.setDistance(result.get(i).getLocationInfo().getDistance().intValue());
                    poiData.setDistrict(result.get(i).getLocationInfo().getDistrict());
                    poiData.setDistrictadcode(result.get(i).getLocationInfo().getDistrictAdCode());
                    Double latitude = result.get(i).getLocationInfo().getGeoPosition().getLatitude();
                    Intrinsics.checkNotNullExpressionValue(latitude, "searchList[i].locationInfo.geoPosition.latitude");
                    poiData.setLatitude(latitude.doubleValue());
                    Double longitude = result.get(i).getLocationInfo().getGeoPosition().getLongitude();
                    Intrinsics.checkNotNullExpressionValue(longitude, "searchList[i].locationInfo.geoPosition.longitude");
                    poiData.setLongitude(longitude.doubleValue());
                    poiData.setName(result.get(i).getLocationInfo().getName());
                    poiData.setPoiId(result.get(i).getLocationInfo().getPoiId());
                    poiData.setProvince(result.get(i).getLocationInfo().getProvince());
                    poiData.setProvinceadcode(result.get(i).getLocationInfo().getProvinceAdCode());
                    poiResultList.add(poiData);
                }
            }
            resultList.setResult(poiResultList);
            Integer reqId_Out = resRequestAlongRouteSearch.getArguments().getReqId_Out();
            Intrinsics.checkNotNullExpressionValue(reqId_Out, "it.arguments.reqId_Out");
            resultList.setRequestID(reqId_Out.intValue());
            String str = this$0.responseAuthOfAsyncCall;
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(resultList);
            Integer resultCode = resRequestAlongRouteSearch.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            ?? JsonEncoding = GreatWallApiTool.jsonEncoding(2, str, strJsonEncoding, 2000, 0, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(2, response… it.arguments.resultCode)");
            callbackStr.element = JsonEncoding;
        } else {
            Integer reqId_Out2 = resRequestAlongRouteSearch.getArguments().getReqId_Out();
            Intrinsics.checkNotNullExpressionValue(reqId_Out2, "it.arguments.reqId_Out");
            resultList.setRequestID(reqId_Out2.intValue());
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(2, this$0.responseAuthOfAsyncCall, GreatWallApiTool.jsonEncoding(resultList), 2000, 0, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(2, response…000, 0, unknownErrorCode)");
            callbackStr.element = JsonEncoding2;
        }
        Log.i(TAG, "searchAlong:" + ((String) callbackStr.element));
        if (iGWRequestCallback != null) {
            iGWRequestCallback.onCallBack((String) callbackStr.element);
        }
    }

    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String getNavigationLanguageSetting() throws JSONException {
        StatusData statusData = new StatusData();
        if (this.navigationLanguageStatus == null) {
            return "";
        }
        Log.i(TAG, "getNavigationLanguageSetting():" + this.navigationLanguageStatus);
        Integer value = this.navigationLanguageStatus.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "navigationLanguageStatus.value");
        statusData.setStatus(value.intValue());
        String strJsonEncoding = GreatWallApiTool.jsonEncoding(2, this.responseAuthOfNotifications, GreatWallApiTool.jsonEncoding(statusData), 90000, 1, 0);
        Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "jsonEncoding(2, response…StatusData), 90000, 1, 0)");
        return strJsonEncoding;
    }

    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public void setSurface(Surface surface, IGWSetSurfaceCallback onSetSurfaceCallback) throws RemoteException {
        Intrinsics.checkNotNullParameter(surface, "surface");
        Log.i(TAG, "setSurface:" + surface);
        this.clusterSurface = surface;
        IMotsServiceInterface iMotsServiceInterface = this.motsMainBinder;
        if (iMotsServiceInterface != null) {
            iMotsServiceInterface.setClusterSurface(surface);
        }
        this.setSurfaceCallback = onSetSurfaceCallback;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String goHome() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        if (this.requests != null) {
            mapToFrontDeskPrivate();
            GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestGoHomeOrCompany(new ReqRequestGoHomeOrCompany(FavoriteType.HOME)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda46
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.goHome$lambda$80(objectRef, this, objectRef2, (ResRequestGoHomeOrCompany) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, 5000, 4, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response…000, 4, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, 5000, 4, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…000, 4, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void goHome$lambda$80(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResRequestGoHomeOrCompany resRequestGoHomeOrCompany) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resRequestGoHomeOrCompany.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resRequestGoHomeOrCompany.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(1, str, 5000, 4, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this$0.responseAuthOfAsyncCall, 5000, 4, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.concurrent.CountDownLatch] */
    /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String goCompany() throws JSONException {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new CountDownLatch(1);
        if (this.requests != null) {
            mapToFrontDeskPrivate();
            GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
            Intrinsics.checkNotNull(greatWallNavApiRequests);
            greatWallNavApiRequests.requestRequestGoHomeOrCompany(new ReqRequestGoHomeOrCompany(FavoriteType.COMPANY)).thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda54
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) throws JSONException {
                    GreatWallApiOctaImpl.goCompany$lambda$81(objectRef, this, objectRef2, (ResRequestGoHomeOrCompany) obj);
                }
            });
        }
        try {
            if (!((CountDownLatch) objectRef2.element).await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                ?? JsonEncoding = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, 5000, 5, this.unknownErrorCode);
                Intrinsics.checkNotNullExpressionValue(JsonEncoding, "jsonEncoding(1, response…000, 5, unknownErrorCode)");
                objectRef.element = JsonEncoding;
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            ?? JsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this.responseAuthOfAsyncCall, 5000, 5, this.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(JsonEncoding2, "jsonEncoding(1, response…000, 5, unknownErrorCode)");
            objectRef.element = JsonEncoding2;
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void goCompany$lambda$81(Ref.ObjectRef mJsStr, GreatWallApiOctaImpl this$0, Ref.ObjectRef mLatch, ResRequestGoHomeOrCompany resRequestGoHomeOrCompany) throws JSONException {
        T t;
        Intrinsics.checkNotNullParameter(mJsStr, "$mJsStr");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mLatch, "$mLatch");
        if (resRequestGoHomeOrCompany.getMessageType() == EMessageType.Result) {
            String str = this$0.responseAuthOfAsyncCall;
            Integer resultCode = resRequestGoHomeOrCompany.getArguments().getResultCode();
            Intrinsics.checkNotNullExpressionValue(resultCode, "it.arguments.resultCode");
            String strJsonEncoding = GreatWallApiTool.jsonEncoding(1, str, 5000, 5, resultCode.intValue());
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding, "{\n               jsonEnc…resultCode)\n            }");
            t = strJsonEncoding;
        } else {
            String strJsonEncoding2 = GreatWallApiTool.jsonEncoding(1, this$0.responseAuthOfAsyncCall, 5000, 5, this$0.unknownErrorCode);
            Intrinsics.checkNotNullExpressionValue(strJsonEncoding2, "{\n               jsonEnc…nErrorCode)\n            }");
            t = strJsonEncoding2;
        }
        mJsStr.element = t;
        ((CountDownLatch) mLatch.element).countDown();
    }

    private final void mapToFrontDeskPrivate() {
        Log.i(TAG, "launchMap private: ");
        Context context = this.context;
        Intrinsics.checkNotNull(context);
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.neusoft.na.navigation");
        if (launchIntentForPackage != null) {
            Context context2 = this.context;
            Intrinsics.checkNotNull(context2);
            context2.startActivity(launchIntentForPackage);
        }
    }

    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public int getLogOnOffStatus() {
        CompletableFuture<ResGetTraceLog> completableFutureRequestGetTraceLog;
        Log.i(TAG, "getLogOnOffStatus");
        final Ref.IntRef intRef = new Ref.IntRef();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requestsCommonSysApi;
        if (commonSysApiInverseRequests != null && (completableFutureRequestGetTraceLog = commonSysApiInverseRequests.requestGetTraceLog()) != null) {
            final Function1<ResGetTraceLog, Unit> function1 = new Function1<ResGetTraceLog, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl.getLogOnOffStatus.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ResGetTraceLog resGetTraceLog) {
                    invoke2(resGetTraceLog);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ResGetTraceLog resGetTraceLog) {
                    Log.i(GreatWallApiOctaImpl.TAG, "getLogOnOffStatus resGetTraceLog.arguments.logOnOff.value == " + resGetTraceLog.getArguments().getLogOnOff().getValue());
                    Ref.IntRef intRef2 = intRef;
                    Integer value = resGetTraceLog.getArguments().getLogOnOff().getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "it.arguments.logOnOff.value");
                    intRef2.element = value.intValue();
                    countDownLatch.countDown();
                }
            };
            completableFutureRequestGetTraceLog.thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda64
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) {
                    GreatWallApiOctaImpl.getLogOnOffStatus$lambda$82(function1, obj);
                }
            });
        }
        try {
            if (!countDownLatch.await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return intRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getLogOnOffStatus$lambda$82(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public void setLogOnOffStatus(int status) {
        Log.i(TAG, "setLogOnOffStatus status == " + status);
        ReqSetTraceLog reqSetTraceLog = new ReqSetTraceLog();
        reqSetTraceLog.getArguments().setLogOnOff(status == 0 ? LogOnOff.ENABLE_TRACE_LOG : LogOnOff.DISABLE_TRACE_LOG);
        CommonSysApiInverseRequests commonSysApiInverseRequests = this.requestsCommonSysApi;
        if (commonSysApiInverseRequests != null) {
            commonSysApiInverseRequests.fireSetTraceLog(reqSetTraceLog);
        }
    }

    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public void clearLog() throws IOException {
        Log.i(TAG, "clearLog");
        Runtime.getRuntime().exec("rm -rf " + File.separator + "resources" + File.separator + "navilog");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public String getCountryCode() {
        CompletableFuture<ResGetCountryInfo> completableFutureRequestGetCountryInfo;
        Log.i(TAG, "getCountryCode");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null && (completableFutureRequestGetCountryInfo = greatWallNavApiRequests.requestGetCountryInfo()) != null) {
            final Function1<ResGetCountryInfo, Unit> function1 = new Function1<ResGetCountryInfo, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl.getCountryCode.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ResGetCountryInfo resGetCountryInfo) {
                    invoke2(resGetCountryInfo);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Type inference failed for: r3v3, types: [T, java.lang.Object, java.lang.String] */
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ResGetCountryInfo resGetCountryInfo) {
                    Ref.ObjectRef<String> objectRef2 = objectRef;
                    ?? country_ISO3166_1_alpha3 = resGetCountryInfo.getArguments().getCountryInfo().getCountry_ISO3166_1_alpha3();
                    Intrinsics.checkNotNullExpressionValue(country_ISO3166_1_alpha3, "it.arguments.countryInfo.country_ISO3166_1_alpha3");
                    objectRef2.element = country_ISO3166_1_alpha3;
                    Log.i(GreatWallApiOctaImpl.TAG, "getCountryCode it.arguments.countryInfo.country_ISO3166_1_alpha3 == " + objectRef.element);
                    countDownLatch.countDown();
                }
            };
            completableFutureRequestGetCountryInfo.thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda45
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) {
                    GreatWallApiOctaImpl.getCountryCode$lambda$83(function1, obj);
                }
            });
        }
        try {
            if (!countDownLatch.await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (String) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getCountryCode$lambda$83(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.neusoft.na.ocservice.greatwallsdk.IGreatWallNavigationApi
    public JSONObject getFrontTraffic() {
        CompletableFuture<ResGetFrontTraffic> completableFutureRequestGetFrontTraffic;
        Log.i(TAG, "getFrontTraffic");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        GreatWallNavApiRequests greatWallNavApiRequests = this.requests;
        if (greatWallNavApiRequests != null && (completableFutureRequestGetFrontTraffic = greatWallNavApiRequests.requestGetFrontTraffic()) != null) {
            final Function1<ResGetFrontTraffic, Unit> function1 = new Function1<ResGetFrontTraffic, Unit>() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl.getFrontTraffic.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ResGetFrontTraffic resGetFrontTraffic) throws JSONException {
                    invoke2(resGetFrontTraffic);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Type inference failed for: r1v0, types: [T, org.json.JSONObject] */
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ResGetFrontTraffic resGetFrontTraffic) throws JSONException {
                    FrontTrafficInfo frontTrafficInfo = resGetFrontTraffic.getArguments().getFrontTrafficInfo();
                    objectRef.element = new JSONObject();
                    JSONObject jSONObject = objectRef.element;
                    Intrinsics.checkNotNull(jSONObject);
                    jSONObject.put("isDelay", frontTrafficInfo != null ? frontTrafficInfo.getIsDelay() : null);
                    JSONObject jSONObject2 = objectRef.element;
                    Intrinsics.checkNotNull(jSONObject2);
                    jSONObject2.put("toDelayDist", frontTrafficInfo != null ? frontTrafficInfo.getToDelayDist() : null);
                    JSONObject jSONObject3 = objectRef.element;
                    Intrinsics.checkNotNull(jSONObject3);
                    jSONObject3.put("delayDist", frontTrafficInfo != null ? frontTrafficInfo.getDelayDist() : null);
                    JSONObject jSONObject4 = objectRef.element;
                    Intrinsics.checkNotNull(jSONObject4);
                    jSONObject4.put("delayTime", frontTrafficInfo != null ? frontTrafficInfo.getDelayTime() : null);
                    Log.i(GreatWallApiOctaImpl.TAG, String.valueOf(objectRef.element));
                    countDownLatch.countDown();
                }
            };
            completableFutureRequestGetFrontTraffic.thenAccept(new Consumer() { // from class: com.neusoft.na.ocservice.greatwallsdk.GreatWallApiOctaImpl$$ExternalSyntheticLambda19
                @Override // java9.util.function.Consumer
                public final void accept(Object obj) {
                    GreatWallApiOctaImpl.getFrontTraffic$lambda$84(function1, obj);
                }
            });
        }
        try {
            if (!countDownLatch.await(this.timeOutValue, TimeUnit.MILLISECONDS)) {
                Log.i(TAG, "mLatch await timeout.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (JSONObject) objectRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getFrontTraffic$lambda$84(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }
}
