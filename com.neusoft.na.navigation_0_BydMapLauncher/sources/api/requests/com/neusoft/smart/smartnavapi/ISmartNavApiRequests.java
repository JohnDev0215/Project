package api.requests.com.neusoft.smart.smartnavapi;

import api.requests.com.neusoft.smart.smartnavapi.ReqAddFavorite;
import api.requests.com.neusoft.smart.smartnavapi.ReqAlongTheWaySearch;
import api.requests.com.neusoft.smart.smartnavapi.ReqAuthorizePrivacyAgreement;
import api.requests.com.neusoft.smart.smartnavapi.ReqChangeThemeResource;
import api.requests.com.neusoft.smart.smartnavapi.ReqEditFavoritePois;
import api.requests.com.neusoft.smart.smartnavapi.ReqGetDetailInfo;
import api.requests.com.neusoft.smart.smartnavapi.ReqGetEVDetailInfo;
import api.requests.com.neusoft.smart.smartnavapi.ReqGetFavoritePois;
import api.requests.com.neusoft.smart.smartnavapi.ReqGetFrontTraffic;
import api.requests.com.neusoft.smart.smartnavapi.ReqGetHighwayExitInfo;
import api.requests.com.neusoft.smart.smartnavapi.ReqGetHistoryPois;
import api.requests.com.neusoft.smart.smartnavapi.ReqGetTrafficSummaryInfo;
import api.requests.com.neusoft.smart.smartnavapi.ReqGoFavorite;
import api.requests.com.neusoft.smart.smartnavapi.ReqHasLogin;
import api.requests.com.neusoft.smart.smartnavapi.ReqMapZoomInOut;
import api.requests.com.neusoft.smart.smartnavapi.ReqModifyNaviVia;
import api.requests.com.neusoft.smart.smartnavapi.ReqNaviFullView;
import api.requests.com.neusoft.smart.smartnavapi.ReqRefuelState;
import api.requests.com.neusoft.smart.smartnavapi.ReqRemoveFavorite;
import api.requests.com.neusoft.smart.smartnavapi.ReqRemoveWaypoint;
import api.requests.com.neusoft.smart.smartnavapi.ReqRequestGeoDecoder;
import api.requests.com.neusoft.smart.smartnavapi.ReqRequestOpenMap;
import api.requests.com.neusoft.smart.smartnavapi.ReqRequestRoutePlanOrNavi;
import api.requests.com.neusoft.smart.smartnavapi.ReqResumeGuidance;
import api.requests.com.neusoft.smart.smartnavapi.ReqSearchAround;
import api.requests.com.neusoft.smart.smartnavapi.ReqSearchAroundByCatId;
import api.requests.com.neusoft.smart.smartnavapi.ReqSearchByKeyword;
import api.requests.com.neusoft.smart.smartnavapi.ReqSearchByKeywordWithLatLng;
import api.requests.com.neusoft.smart.smartnavapi.ReqSearchBySETELCategory;
import api.requests.com.neusoft.smart.smartnavapi.ReqSearchEVChargingStation;
import api.requests.com.neusoft.smart.smartnavapi.ReqSelectRoute;
import api.requests.com.neusoft.smart.smartnavapi.ReqSetCarLocation;
import api.requests.com.neusoft.smart.smartnavapi.ReqSetFavoriteList;
import api.requests.com.neusoft.smart.smartnavapi.ReqSetMapViewMode;
import api.requests.com.neusoft.smart.smartnavapi.ReqSetNaviMuteMode;
import api.requests.com.neusoft.smart.smartnavapi.ReqSetRoutePrefer;
import api.requests.com.neusoft.smart.smartnavapi.ReqSetSETELData;
import api.requests.com.neusoft.smart.smartnavapi.ReqSetSETELDetailData;
import api.requests.com.neusoft.smart.smartnavapi.ReqSetSETELServiceStatus;
import api.requests.com.neusoft.smart.smartnavapi.ReqSetUserDestinationData;
import api.requests.com.neusoft.smart.smartnavapi.ReqShowMyLocation;
import api.requests.com.neusoft.smart.smartnavapi.ReqSpecialPoiNavi;
import api.requests.com.neusoft.smart.smartnavapi.ReqSwitchPrivacyMode;
import api.requests.com.neusoft.smart.smartnavapi.ReqSwitchTraffic;
import api.requests.com.neusoft.smart.smartnavapi.ReqUpdateFavorite;
import api.requests.com.neusoft.smart.smartnavapi.ReqVRMsgCommand;
import api.responses.com.neusoft.smart.smartnavapi.ResAlongTheWaySearch;
import api.responses.com.neusoft.smart.smartnavapi.ResBackToMap;
import api.responses.com.neusoft.smart.smartnavapi.ResCancelNavi;
import api.responses.com.neusoft.smart.smartnavapi.ResChangeThemeResource;
import api.responses.com.neusoft.smart.smartnavapi.ResClearUserDestinationData;
import api.responses.com.neusoft.smart.smartnavapi.ResEditFavoritePois;
import api.responses.com.neusoft.smart.smartnavapi.ResExitMap;
import api.responses.com.neusoft.smart.smartnavapi.ResGetADAreaUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetArrivedDest;
import api.responses.com.neusoft.smart.smartnavapi.ResGetArrivedLastMileUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetAvailableRestoreGuidance;
import api.responses.com.neusoft.smart.smartnavapi.ResGetBatteryPreheatingInfoUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetCommuteInfo;
import api.responses.com.neusoft.smart.smartnavapi.ResGetCommutePush;
import api.responses.com.neusoft.smart.smartnavapi.ResGetDayNightStatusUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetDeadReckoningStatus;
import api.responses.com.neusoft.smart.smartnavapi.ResGetDetailInfo;
import api.responses.com.neusoft.smart.smartnavapi.ResGetEVDetailInfo;
import api.responses.com.neusoft.smart.smartnavapi.ResGetFavoritePois;
import api.responses.com.neusoft.smart.smartnavapi.ResGetFavoritesInfoUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetFrontTraffic;
import api.responses.com.neusoft.smart.smartnavapi.ResGetGetFavoriteList;
import api.responses.com.neusoft.smart.smartnavapi.ResGetGetLoginStatus;
import api.responses.com.neusoft.smart.smartnavapi.ResGetGoToSystemSettingPage;
import api.responses.com.neusoft.smart.smartnavapi.ResGetGuidanceInfoUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetGuidanceInfoZeekrUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetGuideInfo;
import api.responses.com.neusoft.smart.smartnavapi.ResGetGuideInfoZeekr;
import api.responses.com.neusoft.smart.smartnavapi.ResGetHighwayExitInfo;
import api.responses.com.neusoft.smart.smartnavapi.ResGetHighwayInfoUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetHistoryPois;
import api.responses.com.neusoft.smart.smartnavapi.ResGetLanesInfoUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetMapStatusUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetMuteModeUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetNaviMapStatusChanged;
import api.responses.com.neusoft.smart.smartnavapi.ResGetNaviMuteMode;
import api.responses.com.neusoft.smart.smartnavapi.ResGetNaviStatusUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetRequestAddFavorite;
import api.responses.com.neusoft.smart.smartnavapi.ResGetRequestRemoveFavorite;
import api.responses.com.neusoft.smart.smartnavapi.ResGetRequestSETELData;
import api.responses.com.neusoft.smart.smartnavapi.ResGetRequestSETELDetail;
import api.responses.com.neusoft.smart.smartnavapi.ResGetRequestSETELServiceStatus;
import api.responses.com.neusoft.smart.smartnavapi.ResGetRequestUpdateFavorite;
import api.responses.com.neusoft.smart.smartnavapi.ResGetRespAuthorizePrivacyAgreement;
import api.responses.com.neusoft.smart.smartnavapi.ResGetRoundAboutInfoUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetRouteInfo;
import api.responses.com.neusoft.smart.smartnavapi.ResGetRouteInfoUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetRouteSelectedUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetRouteViewStatusUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetScaleStatus;
import api.responses.com.neusoft.smart.smartnavapi.ResGetServiceStatusUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetSpeedLimitInfoUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetSwitchVRCommand;
import api.responses.com.neusoft.smart.smartnavapi.ResGetTimeZoneInfoUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetTrafficConditionUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetTrafficStatusUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetTrafficSummaryInfo;
import api.responses.com.neusoft.smart.smartnavapi.ResGetUserDestinationInfoUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetVRMsgCommandUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetViewModeUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGetViewPrivacyDetails;
import api.responses.com.neusoft.smart.smartnavapi.ResGetZoomStatusUpdate;
import api.responses.com.neusoft.smart.smartnavapi.ResGoFavorite;
import api.responses.com.neusoft.smart.smartnavapi.ResLaunchMap;
import api.responses.com.neusoft.smart.smartnavapi.ResMapZoomInOut;
import api.responses.com.neusoft.smart.smartnavapi.ResModifyNaviVia;
import api.responses.com.neusoft.smart.smartnavapi.ResNaviFullView;
import api.responses.com.neusoft.smart.smartnavapi.ResOpenDestinationSearch;
import api.responses.com.neusoft.smart.smartnavapi.ResRemoveWaypoint;
import api.responses.com.neusoft.smart.smartnavapi.ResRequestGeoDecoder;
import api.responses.com.neusoft.smart.smartnavapi.ResRequestOpenMap;
import api.responses.com.neusoft.smart.smartnavapi.ResRequestRoutePlanOrNavi;
import api.responses.com.neusoft.smart.smartnavapi.ResResumeGuidance;
import api.responses.com.neusoft.smart.smartnavapi.ResRouteOptionView;
import api.responses.com.neusoft.smart.smartnavapi.ResSearchAround;
import api.responses.com.neusoft.smart.smartnavapi.ResSearchAroundByCatId;
import api.responses.com.neusoft.smart.smartnavapi.ResSearchByKeyword;
import api.responses.com.neusoft.smart.smartnavapi.ResSearchByKeywordWithLatLng;
import api.responses.com.neusoft.smart.smartnavapi.ResSearchBySETELCategory;
import api.responses.com.neusoft.smart.smartnavapi.ResSearchEVChargingStation;
import api.responses.com.neusoft.smart.smartnavapi.ResSelectRoute;
import api.responses.com.neusoft.smart.smartnavapi.ResSetMapViewMode;
import api.responses.com.neusoft.smart.smartnavapi.ResSetNaviMuteMode;
import api.responses.com.neusoft.smart.smartnavapi.ResSetRoutePrefer;
import api.responses.com.neusoft.smart.smartnavapi.ResShowMyLocation;
import api.responses.com.neusoft.smart.smartnavapi.ResSpecialPoiNavi;
import api.responses.com.neusoft.smart.smartnavapi.ResSwitchTraffic;
import api.responses.com.neusoft.smart.smartnavapi.ResVRMsgCommand;
import java9.util.concurrent.CompletableFuture;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes2.dex */
public interface ISmartNavApiRequests {
    CompletableFuture<Void> fireAddFavorite(ReqAddFavorite.Arguments arguments);

    CompletableFuture<Void> fireAddFavorite(ReqAddFavorite.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireAddFavorite(ReqAddFavorite reqAddFavorite);

    CompletableFuture<Void> fireAddFavorite(ReqAddFavorite reqAddFavorite, Duration duration);

    CompletableFuture<Void> fireAlongTheWaySearch(ReqAlongTheWaySearch.Arguments arguments);

    CompletableFuture<Void> fireAlongTheWaySearch(ReqAlongTheWaySearch.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireAlongTheWaySearch(ReqAlongTheWaySearch reqAlongTheWaySearch);

    CompletableFuture<Void> fireAlongTheWaySearch(ReqAlongTheWaySearch reqAlongTheWaySearch, Duration duration);

    CompletableFuture<Void> fireAuthorizePrivacyAgreement(ReqAuthorizePrivacyAgreement.Arguments arguments);

    CompletableFuture<Void> fireAuthorizePrivacyAgreement(ReqAuthorizePrivacyAgreement.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireAuthorizePrivacyAgreement(ReqAuthorizePrivacyAgreement reqAuthorizePrivacyAgreement);

    CompletableFuture<Void> fireAuthorizePrivacyAgreement(ReqAuthorizePrivacyAgreement reqAuthorizePrivacyAgreement, Duration duration);

    CompletableFuture<Void> fireBackToMap();

    CompletableFuture<Void> fireBackToMap(ReqBackToMap reqBackToMap);

    CompletableFuture<Void> fireBackToMap(ReqBackToMap reqBackToMap, Duration duration);

    CompletableFuture<Void> fireBackToMap(Duration duration);

    CompletableFuture<Void> fireCancelNavi();

    CompletableFuture<Void> fireCancelNavi(ReqCancelNavi reqCancelNavi);

    CompletableFuture<Void> fireCancelNavi(ReqCancelNavi reqCancelNavi, Duration duration);

    CompletableFuture<Void> fireCancelNavi(Duration duration);

    CompletableFuture<Void> fireChangeThemeResource(ReqChangeThemeResource.Arguments arguments);

    CompletableFuture<Void> fireChangeThemeResource(ReqChangeThemeResource.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireChangeThemeResource(ReqChangeThemeResource reqChangeThemeResource);

    CompletableFuture<Void> fireChangeThemeResource(ReqChangeThemeResource reqChangeThemeResource, Duration duration);

    CompletableFuture<Void> fireClearUserDestinationData();

    CompletableFuture<Void> fireClearUserDestinationData(ReqClearUserDestinationData reqClearUserDestinationData);

    CompletableFuture<Void> fireClearUserDestinationData(ReqClearUserDestinationData reqClearUserDestinationData, Duration duration);

    CompletableFuture<Void> fireClearUserDestinationData(Duration duration);

    CompletableFuture<Void> fireEditFavoritePois(ReqEditFavoritePois.Arguments arguments);

    CompletableFuture<Void> fireEditFavoritePois(ReqEditFavoritePois.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireEditFavoritePois(ReqEditFavoritePois reqEditFavoritePois);

    CompletableFuture<Void> fireEditFavoritePois(ReqEditFavoritePois reqEditFavoritePois, Duration duration);

    CompletableFuture<Void> fireExitMap();

    CompletableFuture<Void> fireExitMap(ReqExitMap reqExitMap);

    CompletableFuture<Void> fireExitMap(ReqExitMap reqExitMap, Duration duration);

    CompletableFuture<Void> fireExitMap(Duration duration);

    CompletableFuture<Void> fireGetADAreaUpdate();

    CompletableFuture<Void> fireGetADAreaUpdate(ReqGetADAreaUpdate reqGetADAreaUpdate);

    CompletableFuture<Void> fireGetADAreaUpdate(ReqGetADAreaUpdate reqGetADAreaUpdate, Duration duration);

    CompletableFuture<Void> fireGetADAreaUpdate(Duration duration);

    CompletableFuture<Void> fireGetArrivedDest();

    CompletableFuture<Void> fireGetArrivedDest(ReqGetArrivedDest reqGetArrivedDest);

    CompletableFuture<Void> fireGetArrivedDest(ReqGetArrivedDest reqGetArrivedDest, Duration duration);

    CompletableFuture<Void> fireGetArrivedDest(Duration duration);

    CompletableFuture<Void> fireGetArrivedLastMileUpdate();

    CompletableFuture<Void> fireGetArrivedLastMileUpdate(ReqGetArrivedLastMileUpdate reqGetArrivedLastMileUpdate);

    CompletableFuture<Void> fireGetArrivedLastMileUpdate(ReqGetArrivedLastMileUpdate reqGetArrivedLastMileUpdate, Duration duration);

    CompletableFuture<Void> fireGetArrivedLastMileUpdate(Duration duration);

    CompletableFuture<Void> fireGetAvailableRestoreGuidance();

    CompletableFuture<Void> fireGetAvailableRestoreGuidance(ReqGetAvailableRestoreGuidance reqGetAvailableRestoreGuidance);

    CompletableFuture<Void> fireGetAvailableRestoreGuidance(ReqGetAvailableRestoreGuidance reqGetAvailableRestoreGuidance, Duration duration);

    CompletableFuture<Void> fireGetAvailableRestoreGuidance(Duration duration);

    CompletableFuture<Void> fireGetBatteryPreheatingInfoUpdate();

    CompletableFuture<Void> fireGetBatteryPreheatingInfoUpdate(ReqGetBatteryPreheatingInfoUpdate reqGetBatteryPreheatingInfoUpdate);

    CompletableFuture<Void> fireGetBatteryPreheatingInfoUpdate(ReqGetBatteryPreheatingInfoUpdate reqGetBatteryPreheatingInfoUpdate, Duration duration);

    CompletableFuture<Void> fireGetBatteryPreheatingInfoUpdate(Duration duration);

    CompletableFuture<Void> fireGetCommuteInfo();

    CompletableFuture<Void> fireGetCommuteInfo(ReqGetCommuteInfo reqGetCommuteInfo);

    CompletableFuture<Void> fireGetCommuteInfo(ReqGetCommuteInfo reqGetCommuteInfo, Duration duration);

    CompletableFuture<Void> fireGetCommuteInfo(Duration duration);

    CompletableFuture<Void> fireGetCommutePush();

    CompletableFuture<Void> fireGetCommutePush(ReqGetCommutePush reqGetCommutePush);

    CompletableFuture<Void> fireGetCommutePush(ReqGetCommutePush reqGetCommutePush, Duration duration);

    CompletableFuture<Void> fireGetCommutePush(Duration duration);

    CompletableFuture<Void> fireGetDayNightStatusUpdate();

    CompletableFuture<Void> fireGetDayNightStatusUpdate(ReqGetDayNightStatusUpdate reqGetDayNightStatusUpdate);

    CompletableFuture<Void> fireGetDayNightStatusUpdate(ReqGetDayNightStatusUpdate reqGetDayNightStatusUpdate, Duration duration);

    CompletableFuture<Void> fireGetDayNightStatusUpdate(Duration duration);

    CompletableFuture<Void> fireGetDeadReckoningStatus();

    CompletableFuture<Void> fireGetDeadReckoningStatus(ReqGetDeadReckoningStatus reqGetDeadReckoningStatus);

    CompletableFuture<Void> fireGetDeadReckoningStatus(ReqGetDeadReckoningStatus reqGetDeadReckoningStatus, Duration duration);

    CompletableFuture<Void> fireGetDeadReckoningStatus(Duration duration);

    CompletableFuture<Void> fireGetDetailInfo(ReqGetDetailInfo.Arguments arguments);

    CompletableFuture<Void> fireGetDetailInfo(ReqGetDetailInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireGetDetailInfo(ReqGetDetailInfo reqGetDetailInfo);

    CompletableFuture<Void> fireGetDetailInfo(ReqGetDetailInfo reqGetDetailInfo, Duration duration);

    CompletableFuture<Void> fireGetEVDetailInfo(ReqGetEVDetailInfo.Arguments arguments);

    CompletableFuture<Void> fireGetEVDetailInfo(ReqGetEVDetailInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireGetEVDetailInfo(ReqGetEVDetailInfo reqGetEVDetailInfo);

    CompletableFuture<Void> fireGetEVDetailInfo(ReqGetEVDetailInfo reqGetEVDetailInfo, Duration duration);

    CompletableFuture<Void> fireGetFavoritePois(ReqGetFavoritePois.Arguments arguments);

    CompletableFuture<Void> fireGetFavoritePois(ReqGetFavoritePois.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireGetFavoritePois(ReqGetFavoritePois reqGetFavoritePois);

    CompletableFuture<Void> fireGetFavoritePois(ReqGetFavoritePois reqGetFavoritePois, Duration duration);

    CompletableFuture<Void> fireGetFavoritesInfoUpdate();

    CompletableFuture<Void> fireGetFavoritesInfoUpdate(ReqGetFavoritesInfoUpdate reqGetFavoritesInfoUpdate);

    CompletableFuture<Void> fireGetFavoritesInfoUpdate(ReqGetFavoritesInfoUpdate reqGetFavoritesInfoUpdate, Duration duration);

    CompletableFuture<Void> fireGetFavoritesInfoUpdate(Duration duration);

    CompletableFuture<Void> fireGetFrontTraffic(ReqGetFrontTraffic.Arguments arguments);

    CompletableFuture<Void> fireGetFrontTraffic(ReqGetFrontTraffic.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireGetFrontTraffic(ReqGetFrontTraffic reqGetFrontTraffic);

    CompletableFuture<Void> fireGetFrontTraffic(ReqGetFrontTraffic reqGetFrontTraffic, Duration duration);

    CompletableFuture<Void> fireGetGetFavoriteList();

    CompletableFuture<Void> fireGetGetFavoriteList(ReqGetGetFavoriteList reqGetGetFavoriteList);

    CompletableFuture<Void> fireGetGetFavoriteList(ReqGetGetFavoriteList reqGetGetFavoriteList, Duration duration);

    CompletableFuture<Void> fireGetGetFavoriteList(Duration duration);

    CompletableFuture<Void> fireGetGetLoginStatus();

    CompletableFuture<Void> fireGetGetLoginStatus(ReqGetGetLoginStatus reqGetGetLoginStatus);

    CompletableFuture<Void> fireGetGetLoginStatus(ReqGetGetLoginStatus reqGetGetLoginStatus, Duration duration);

    CompletableFuture<Void> fireGetGetLoginStatus(Duration duration);

    CompletableFuture<Void> fireGetGoToSystemSettingPage();

    CompletableFuture<Void> fireGetGoToSystemSettingPage(ReqGetGoToSystemSettingPage reqGetGoToSystemSettingPage);

    CompletableFuture<Void> fireGetGoToSystemSettingPage(ReqGetGoToSystemSettingPage reqGetGoToSystemSettingPage, Duration duration);

    CompletableFuture<Void> fireGetGoToSystemSettingPage(Duration duration);

    CompletableFuture<Void> fireGetGuidanceInfoUpdate();

    CompletableFuture<Void> fireGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate);

    CompletableFuture<Void> fireGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate, Duration duration);

    CompletableFuture<Void> fireGetGuidanceInfoUpdate(Duration duration);

    CompletableFuture<Void> fireGetGuidanceInfoZeekrUpdate();

    CompletableFuture<Void> fireGetGuidanceInfoZeekrUpdate(ReqGetGuidanceInfoZeekrUpdate reqGetGuidanceInfoZeekrUpdate);

    CompletableFuture<Void> fireGetGuidanceInfoZeekrUpdate(ReqGetGuidanceInfoZeekrUpdate reqGetGuidanceInfoZeekrUpdate, Duration duration);

    CompletableFuture<Void> fireGetGuidanceInfoZeekrUpdate(Duration duration);

    CompletableFuture<Void> fireGetGuideInfo();

    CompletableFuture<Void> fireGetGuideInfo(ReqGetGuideInfo reqGetGuideInfo);

    CompletableFuture<Void> fireGetGuideInfo(ReqGetGuideInfo reqGetGuideInfo, Duration duration);

    CompletableFuture<Void> fireGetGuideInfo(Duration duration);

    CompletableFuture<Void> fireGetGuideInfoZeekr();

    CompletableFuture<Void> fireGetGuideInfoZeekr(ReqGetGuideInfoZeekr reqGetGuideInfoZeekr);

    CompletableFuture<Void> fireGetGuideInfoZeekr(ReqGetGuideInfoZeekr reqGetGuideInfoZeekr, Duration duration);

    CompletableFuture<Void> fireGetGuideInfoZeekr(Duration duration);

    CompletableFuture<Void> fireGetHighwayExitInfo(ReqGetHighwayExitInfo.Arguments arguments);

    CompletableFuture<Void> fireGetHighwayExitInfo(ReqGetHighwayExitInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireGetHighwayExitInfo(ReqGetHighwayExitInfo reqGetHighwayExitInfo);

    CompletableFuture<Void> fireGetHighwayExitInfo(ReqGetHighwayExitInfo reqGetHighwayExitInfo, Duration duration);

    CompletableFuture<Void> fireGetHighwayInfoUpdate();

    CompletableFuture<Void> fireGetHighwayInfoUpdate(ReqGetHighwayInfoUpdate reqGetHighwayInfoUpdate);

    CompletableFuture<Void> fireGetHighwayInfoUpdate(ReqGetHighwayInfoUpdate reqGetHighwayInfoUpdate, Duration duration);

    CompletableFuture<Void> fireGetHighwayInfoUpdate(Duration duration);

    CompletableFuture<Void> fireGetHistoryPois(ReqGetHistoryPois.Arguments arguments);

    CompletableFuture<Void> fireGetHistoryPois(ReqGetHistoryPois.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireGetHistoryPois(ReqGetHistoryPois reqGetHistoryPois);

    CompletableFuture<Void> fireGetHistoryPois(ReqGetHistoryPois reqGetHistoryPois, Duration duration);

    CompletableFuture<Void> fireGetLanesInfoUpdate();

    CompletableFuture<Void> fireGetLanesInfoUpdate(ReqGetLanesInfoUpdate reqGetLanesInfoUpdate);

    CompletableFuture<Void> fireGetLanesInfoUpdate(ReqGetLanesInfoUpdate reqGetLanesInfoUpdate, Duration duration);

    CompletableFuture<Void> fireGetLanesInfoUpdate(Duration duration);

    CompletableFuture<Void> fireGetMapStatusUpdate();

    CompletableFuture<Void> fireGetMapStatusUpdate(ReqGetMapStatusUpdate reqGetMapStatusUpdate);

    CompletableFuture<Void> fireGetMapStatusUpdate(ReqGetMapStatusUpdate reqGetMapStatusUpdate, Duration duration);

    CompletableFuture<Void> fireGetMapStatusUpdate(Duration duration);

    CompletableFuture<Void> fireGetMuteModeUpdate();

    CompletableFuture<Void> fireGetMuteModeUpdate(ReqGetMuteModeUpdate reqGetMuteModeUpdate);

    CompletableFuture<Void> fireGetMuteModeUpdate(ReqGetMuteModeUpdate reqGetMuteModeUpdate, Duration duration);

    CompletableFuture<Void> fireGetMuteModeUpdate(Duration duration);

    CompletableFuture<Void> fireGetNaviMapStatusChanged();

    CompletableFuture<Void> fireGetNaviMapStatusChanged(ReqGetNaviMapStatusChanged reqGetNaviMapStatusChanged);

    CompletableFuture<Void> fireGetNaviMapStatusChanged(ReqGetNaviMapStatusChanged reqGetNaviMapStatusChanged, Duration duration);

    CompletableFuture<Void> fireGetNaviMapStatusChanged(Duration duration);

    CompletableFuture<Void> fireGetNaviMuteMode();

    CompletableFuture<Void> fireGetNaviMuteMode(ReqGetNaviMuteMode reqGetNaviMuteMode);

    CompletableFuture<Void> fireGetNaviMuteMode(ReqGetNaviMuteMode reqGetNaviMuteMode, Duration duration);

    CompletableFuture<Void> fireGetNaviMuteMode(Duration duration);

    CompletableFuture<Void> fireGetNaviStatusUpdate();

    CompletableFuture<Void> fireGetNaviStatusUpdate(ReqGetNaviStatusUpdate reqGetNaviStatusUpdate);

    CompletableFuture<Void> fireGetNaviStatusUpdate(ReqGetNaviStatusUpdate reqGetNaviStatusUpdate, Duration duration);

    CompletableFuture<Void> fireGetNaviStatusUpdate(Duration duration);

    CompletableFuture<Void> fireGetRequestAddFavorite();

    CompletableFuture<Void> fireGetRequestAddFavorite(ReqGetRequestAddFavorite reqGetRequestAddFavorite);

    CompletableFuture<Void> fireGetRequestAddFavorite(ReqGetRequestAddFavorite reqGetRequestAddFavorite, Duration duration);

    CompletableFuture<Void> fireGetRequestAddFavorite(Duration duration);

    CompletableFuture<Void> fireGetRequestRemoveFavorite();

    CompletableFuture<Void> fireGetRequestRemoveFavorite(ReqGetRequestRemoveFavorite reqGetRequestRemoveFavorite);

    CompletableFuture<Void> fireGetRequestRemoveFavorite(ReqGetRequestRemoveFavorite reqGetRequestRemoveFavorite, Duration duration);

    CompletableFuture<Void> fireGetRequestRemoveFavorite(Duration duration);

    CompletableFuture<Void> fireGetRequestSETELData();

    CompletableFuture<Void> fireGetRequestSETELData(ReqGetRequestSETELData reqGetRequestSETELData);

    CompletableFuture<Void> fireGetRequestSETELData(ReqGetRequestSETELData reqGetRequestSETELData, Duration duration);

    CompletableFuture<Void> fireGetRequestSETELData(Duration duration);

    CompletableFuture<Void> fireGetRequestSETELDetail();

    CompletableFuture<Void> fireGetRequestSETELDetail(ReqGetRequestSETELDetail reqGetRequestSETELDetail);

    CompletableFuture<Void> fireGetRequestSETELDetail(ReqGetRequestSETELDetail reqGetRequestSETELDetail, Duration duration);

    CompletableFuture<Void> fireGetRequestSETELDetail(Duration duration);

    CompletableFuture<Void> fireGetRequestSETELServiceStatus();

    CompletableFuture<Void> fireGetRequestSETELServiceStatus(ReqGetRequestSETELServiceStatus reqGetRequestSETELServiceStatus);

    CompletableFuture<Void> fireGetRequestSETELServiceStatus(ReqGetRequestSETELServiceStatus reqGetRequestSETELServiceStatus, Duration duration);

    CompletableFuture<Void> fireGetRequestSETELServiceStatus(Duration duration);

    CompletableFuture<Void> fireGetRequestUpdateFavorite();

    CompletableFuture<Void> fireGetRequestUpdateFavorite(ReqGetRequestUpdateFavorite reqGetRequestUpdateFavorite);

    CompletableFuture<Void> fireGetRequestUpdateFavorite(ReqGetRequestUpdateFavorite reqGetRequestUpdateFavorite, Duration duration);

    CompletableFuture<Void> fireGetRequestUpdateFavorite(Duration duration);

    CompletableFuture<Void> fireGetRespAuthorizePrivacyAgreement();

    CompletableFuture<Void> fireGetRespAuthorizePrivacyAgreement(ReqGetRespAuthorizePrivacyAgreement reqGetRespAuthorizePrivacyAgreement);

    CompletableFuture<Void> fireGetRespAuthorizePrivacyAgreement(ReqGetRespAuthorizePrivacyAgreement reqGetRespAuthorizePrivacyAgreement, Duration duration);

    CompletableFuture<Void> fireGetRespAuthorizePrivacyAgreement(Duration duration);

    CompletableFuture<Void> fireGetRoundAboutInfoUpdate();

    CompletableFuture<Void> fireGetRoundAboutInfoUpdate(ReqGetRoundAboutInfoUpdate reqGetRoundAboutInfoUpdate);

    CompletableFuture<Void> fireGetRoundAboutInfoUpdate(ReqGetRoundAboutInfoUpdate reqGetRoundAboutInfoUpdate, Duration duration);

    CompletableFuture<Void> fireGetRoundAboutInfoUpdate(Duration duration);

    CompletableFuture<Void> fireGetRouteInfo();

    CompletableFuture<Void> fireGetRouteInfo(ReqGetRouteInfo reqGetRouteInfo);

    CompletableFuture<Void> fireGetRouteInfo(ReqGetRouteInfo reqGetRouteInfo, Duration duration);

    CompletableFuture<Void> fireGetRouteInfo(Duration duration);

    CompletableFuture<Void> fireGetRouteInfoUpdate();

    CompletableFuture<Void> fireGetRouteInfoUpdate(ReqGetRouteInfoUpdate reqGetRouteInfoUpdate);

    CompletableFuture<Void> fireGetRouteInfoUpdate(ReqGetRouteInfoUpdate reqGetRouteInfoUpdate, Duration duration);

    CompletableFuture<Void> fireGetRouteInfoUpdate(Duration duration);

    CompletableFuture<Void> fireGetRouteSelectedUpdate();

    CompletableFuture<Void> fireGetRouteSelectedUpdate(ReqGetRouteSelectedUpdate reqGetRouteSelectedUpdate);

    CompletableFuture<Void> fireGetRouteSelectedUpdate(ReqGetRouteSelectedUpdate reqGetRouteSelectedUpdate, Duration duration);

    CompletableFuture<Void> fireGetRouteSelectedUpdate(Duration duration);

    CompletableFuture<Void> fireGetRouteViewStatusUpdate();

    CompletableFuture<Void> fireGetRouteViewStatusUpdate(ReqGetRouteViewStatusUpdate reqGetRouteViewStatusUpdate);

    CompletableFuture<Void> fireGetRouteViewStatusUpdate(ReqGetRouteViewStatusUpdate reqGetRouteViewStatusUpdate, Duration duration);

    CompletableFuture<Void> fireGetRouteViewStatusUpdate(Duration duration);

    CompletableFuture<Void> fireGetScaleStatus();

    CompletableFuture<Void> fireGetScaleStatus(ReqGetScaleStatus reqGetScaleStatus);

    CompletableFuture<Void> fireGetScaleStatus(ReqGetScaleStatus reqGetScaleStatus, Duration duration);

    CompletableFuture<Void> fireGetScaleStatus(Duration duration);

    CompletableFuture<Void> fireGetServiceStatusUpdate();

    CompletableFuture<Void> fireGetServiceStatusUpdate(ReqGetServiceStatusUpdate reqGetServiceStatusUpdate);

    CompletableFuture<Void> fireGetServiceStatusUpdate(ReqGetServiceStatusUpdate reqGetServiceStatusUpdate, Duration duration);

    CompletableFuture<Void> fireGetServiceStatusUpdate(Duration duration);

    CompletableFuture<Void> fireGetSpeedLimitInfoUpdate();

    CompletableFuture<Void> fireGetSpeedLimitInfoUpdate(ReqGetSpeedLimitInfoUpdate reqGetSpeedLimitInfoUpdate);

    CompletableFuture<Void> fireGetSpeedLimitInfoUpdate(ReqGetSpeedLimitInfoUpdate reqGetSpeedLimitInfoUpdate, Duration duration);

    CompletableFuture<Void> fireGetSpeedLimitInfoUpdate(Duration duration);

    CompletableFuture<Void> fireGetSwitchVRCommand();

    CompletableFuture<Void> fireGetSwitchVRCommand(ReqGetSwitchVRCommand reqGetSwitchVRCommand);

    CompletableFuture<Void> fireGetSwitchVRCommand(ReqGetSwitchVRCommand reqGetSwitchVRCommand, Duration duration);

    CompletableFuture<Void> fireGetSwitchVRCommand(Duration duration);

    CompletableFuture<Void> fireGetTimeZoneInfoUpdate();

    CompletableFuture<Void> fireGetTimeZoneInfoUpdate(ReqGetTimeZoneInfoUpdate reqGetTimeZoneInfoUpdate);

    CompletableFuture<Void> fireGetTimeZoneInfoUpdate(ReqGetTimeZoneInfoUpdate reqGetTimeZoneInfoUpdate, Duration duration);

    CompletableFuture<Void> fireGetTimeZoneInfoUpdate(Duration duration);

    CompletableFuture<Void> fireGetTrafficConditionUpdate();

    CompletableFuture<Void> fireGetTrafficConditionUpdate(ReqGetTrafficConditionUpdate reqGetTrafficConditionUpdate);

    CompletableFuture<Void> fireGetTrafficConditionUpdate(ReqGetTrafficConditionUpdate reqGetTrafficConditionUpdate, Duration duration);

    CompletableFuture<Void> fireGetTrafficConditionUpdate(Duration duration);

    CompletableFuture<Void> fireGetTrafficStatusUpdate();

    CompletableFuture<Void> fireGetTrafficStatusUpdate(ReqGetTrafficStatusUpdate reqGetTrafficStatusUpdate);

    CompletableFuture<Void> fireGetTrafficStatusUpdate(ReqGetTrafficStatusUpdate reqGetTrafficStatusUpdate, Duration duration);

    CompletableFuture<Void> fireGetTrafficStatusUpdate(Duration duration);

    CompletableFuture<Void> fireGetTrafficSummaryInfo(ReqGetTrafficSummaryInfo.Arguments arguments);

    CompletableFuture<Void> fireGetTrafficSummaryInfo(ReqGetTrafficSummaryInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireGetTrafficSummaryInfo(ReqGetTrafficSummaryInfo reqGetTrafficSummaryInfo);

    CompletableFuture<Void> fireGetTrafficSummaryInfo(ReqGetTrafficSummaryInfo reqGetTrafficSummaryInfo, Duration duration);

    CompletableFuture<Void> fireGetUserDestinationInfoUpdate();

    CompletableFuture<Void> fireGetUserDestinationInfoUpdate(ReqGetUserDestinationInfoUpdate reqGetUserDestinationInfoUpdate);

    CompletableFuture<Void> fireGetUserDestinationInfoUpdate(ReqGetUserDestinationInfoUpdate reqGetUserDestinationInfoUpdate, Duration duration);

    CompletableFuture<Void> fireGetUserDestinationInfoUpdate(Duration duration);

    CompletableFuture<Void> fireGetVRMsgCommandUpdate();

    CompletableFuture<Void> fireGetVRMsgCommandUpdate(ReqGetVRMsgCommandUpdate reqGetVRMsgCommandUpdate);

    CompletableFuture<Void> fireGetVRMsgCommandUpdate(ReqGetVRMsgCommandUpdate reqGetVRMsgCommandUpdate, Duration duration);

    CompletableFuture<Void> fireGetVRMsgCommandUpdate(Duration duration);

    CompletableFuture<Void> fireGetViewModeUpdate();

    CompletableFuture<Void> fireGetViewModeUpdate(ReqGetViewModeUpdate reqGetViewModeUpdate);

    CompletableFuture<Void> fireGetViewModeUpdate(ReqGetViewModeUpdate reqGetViewModeUpdate, Duration duration);

    CompletableFuture<Void> fireGetViewModeUpdate(Duration duration);

    CompletableFuture<Void> fireGetViewPrivacyDetails();

    CompletableFuture<Void> fireGetViewPrivacyDetails(ReqGetViewPrivacyDetails reqGetViewPrivacyDetails);

    CompletableFuture<Void> fireGetViewPrivacyDetails(ReqGetViewPrivacyDetails reqGetViewPrivacyDetails, Duration duration);

    CompletableFuture<Void> fireGetViewPrivacyDetails(Duration duration);

    CompletableFuture<Void> fireGetZoomStatusUpdate();

    CompletableFuture<Void> fireGetZoomStatusUpdate(ReqGetZoomStatusUpdate reqGetZoomStatusUpdate);

    CompletableFuture<Void> fireGetZoomStatusUpdate(ReqGetZoomStatusUpdate reqGetZoomStatusUpdate, Duration duration);

    CompletableFuture<Void> fireGetZoomStatusUpdate(Duration duration);

    CompletableFuture<Void> fireGoFavorite(ReqGoFavorite.Arguments arguments);

    CompletableFuture<Void> fireGoFavorite(ReqGoFavorite.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireGoFavorite(ReqGoFavorite reqGoFavorite);

    CompletableFuture<Void> fireGoFavorite(ReqGoFavorite reqGoFavorite, Duration duration);

    CompletableFuture<Void> fireHasLogin(ReqHasLogin.Arguments arguments);

    CompletableFuture<Void> fireHasLogin(ReqHasLogin.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireHasLogin(ReqHasLogin reqHasLogin);

    CompletableFuture<Void> fireHasLogin(ReqHasLogin reqHasLogin, Duration duration);

    CompletableFuture<Void> fireLaunchMap();

    CompletableFuture<Void> fireLaunchMap(ReqLaunchMap reqLaunchMap);

    CompletableFuture<Void> fireLaunchMap(ReqLaunchMap reqLaunchMap, Duration duration);

    CompletableFuture<Void> fireLaunchMap(Duration duration);

    CompletableFuture<Void> fireMapZoomInOut(ReqMapZoomInOut.Arguments arguments);

    CompletableFuture<Void> fireMapZoomInOut(ReqMapZoomInOut.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireMapZoomInOut(ReqMapZoomInOut reqMapZoomInOut);

    CompletableFuture<Void> fireMapZoomInOut(ReqMapZoomInOut reqMapZoomInOut, Duration duration);

    CompletableFuture<Void> fireModifyNaviVia(ReqModifyNaviVia.Arguments arguments);

    CompletableFuture<Void> fireModifyNaviVia(ReqModifyNaviVia.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireModifyNaviVia(ReqModifyNaviVia reqModifyNaviVia);

    CompletableFuture<Void> fireModifyNaviVia(ReqModifyNaviVia reqModifyNaviVia, Duration duration);

    CompletableFuture<Void> fireNaviFullView(ReqNaviFullView.Arguments arguments);

    CompletableFuture<Void> fireNaviFullView(ReqNaviFullView.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireNaviFullView(ReqNaviFullView reqNaviFullView);

    CompletableFuture<Void> fireNaviFullView(ReqNaviFullView reqNaviFullView, Duration duration);

    CompletableFuture<Void> fireOpenDestinationSearch();

    CompletableFuture<Void> fireOpenDestinationSearch(ReqOpenDestinationSearch reqOpenDestinationSearch);

    CompletableFuture<Void> fireOpenDestinationSearch(ReqOpenDestinationSearch reqOpenDestinationSearch, Duration duration);

    CompletableFuture<Void> fireOpenDestinationSearch(Duration duration);

    CompletableFuture<Void> fireRefuelState(ReqRefuelState.Arguments arguments);

    CompletableFuture<Void> fireRefuelState(ReqRefuelState.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRefuelState(ReqRefuelState reqRefuelState);

    CompletableFuture<Void> fireRefuelState(ReqRefuelState reqRefuelState, Duration duration);

    CompletableFuture<Void> fireRemoveFavorite(ReqRemoveFavorite.Arguments arguments);

    CompletableFuture<Void> fireRemoveFavorite(ReqRemoveFavorite.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRemoveFavorite(ReqRemoveFavorite reqRemoveFavorite);

    CompletableFuture<Void> fireRemoveFavorite(ReqRemoveFavorite reqRemoveFavorite, Duration duration);

    CompletableFuture<Void> fireRemoveWaypoint(ReqRemoveWaypoint.Arguments arguments);

    CompletableFuture<Void> fireRemoveWaypoint(ReqRemoveWaypoint.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRemoveWaypoint(ReqRemoveWaypoint reqRemoveWaypoint);

    CompletableFuture<Void> fireRemoveWaypoint(ReqRemoveWaypoint reqRemoveWaypoint, Duration duration);

    CompletableFuture<Void> fireRequestGeoDecoder(ReqRequestGeoDecoder.Arguments arguments);

    CompletableFuture<Void> fireRequestGeoDecoder(ReqRequestGeoDecoder.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestGeoDecoder(ReqRequestGeoDecoder reqRequestGeoDecoder);

    CompletableFuture<Void> fireRequestGeoDecoder(ReqRequestGeoDecoder reqRequestGeoDecoder, Duration duration);

    CompletableFuture<Void> fireRequestOpenMap(ReqRequestOpenMap.Arguments arguments);

    CompletableFuture<Void> fireRequestOpenMap(ReqRequestOpenMap.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestOpenMap(ReqRequestOpenMap reqRequestOpenMap);

    CompletableFuture<Void> fireRequestOpenMap(ReqRequestOpenMap reqRequestOpenMap, Duration duration);

    CompletableFuture<Void> fireRequestRoutePlanOrNavi(ReqRequestRoutePlanOrNavi.Arguments arguments);

    CompletableFuture<Void> fireRequestRoutePlanOrNavi(ReqRequestRoutePlanOrNavi.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestRoutePlanOrNavi(ReqRequestRoutePlanOrNavi reqRequestRoutePlanOrNavi);

    CompletableFuture<Void> fireRequestRoutePlanOrNavi(ReqRequestRoutePlanOrNavi reqRequestRoutePlanOrNavi, Duration duration);

    CompletableFuture<Void> fireResumeGuidance(ReqResumeGuidance.Arguments arguments);

    CompletableFuture<Void> fireResumeGuidance(ReqResumeGuidance.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireResumeGuidance(ReqResumeGuidance reqResumeGuidance);

    CompletableFuture<Void> fireResumeGuidance(ReqResumeGuidance reqResumeGuidance, Duration duration);

    CompletableFuture<Void> fireRouteOptionView();

    CompletableFuture<Void> fireRouteOptionView(ReqRouteOptionView reqRouteOptionView);

    CompletableFuture<Void> fireRouteOptionView(ReqRouteOptionView reqRouteOptionView, Duration duration);

    CompletableFuture<Void> fireRouteOptionView(Duration duration);

    CompletableFuture<Void> fireSearchAround(ReqSearchAround.Arguments arguments);

    CompletableFuture<Void> fireSearchAround(ReqSearchAround.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSearchAround(ReqSearchAround reqSearchAround);

    CompletableFuture<Void> fireSearchAround(ReqSearchAround reqSearchAround, Duration duration);

    CompletableFuture<Void> fireSearchAroundByCatId(ReqSearchAroundByCatId.Arguments arguments);

    CompletableFuture<Void> fireSearchAroundByCatId(ReqSearchAroundByCatId.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSearchAroundByCatId(ReqSearchAroundByCatId reqSearchAroundByCatId);

    CompletableFuture<Void> fireSearchAroundByCatId(ReqSearchAroundByCatId reqSearchAroundByCatId, Duration duration);

    CompletableFuture<Void> fireSearchByKeyword(ReqSearchByKeyword.Arguments arguments);

    CompletableFuture<Void> fireSearchByKeyword(ReqSearchByKeyword.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSearchByKeyword(ReqSearchByKeyword reqSearchByKeyword);

    CompletableFuture<Void> fireSearchByKeyword(ReqSearchByKeyword reqSearchByKeyword, Duration duration);

    CompletableFuture<Void> fireSearchByKeywordWithLatLng(ReqSearchByKeywordWithLatLng.Arguments arguments);

    CompletableFuture<Void> fireSearchByKeywordWithLatLng(ReqSearchByKeywordWithLatLng.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSearchByKeywordWithLatLng(ReqSearchByKeywordWithLatLng reqSearchByKeywordWithLatLng);

    CompletableFuture<Void> fireSearchByKeywordWithLatLng(ReqSearchByKeywordWithLatLng reqSearchByKeywordWithLatLng, Duration duration);

    CompletableFuture<Void> fireSearchBySETELCategory(ReqSearchBySETELCategory.Arguments arguments);

    CompletableFuture<Void> fireSearchBySETELCategory(ReqSearchBySETELCategory.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSearchBySETELCategory(ReqSearchBySETELCategory reqSearchBySETELCategory);

    CompletableFuture<Void> fireSearchBySETELCategory(ReqSearchBySETELCategory reqSearchBySETELCategory, Duration duration);

    CompletableFuture<Void> fireSearchEVChargingStation(ReqSearchEVChargingStation.Arguments arguments);

    CompletableFuture<Void> fireSearchEVChargingStation(ReqSearchEVChargingStation.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSearchEVChargingStation(ReqSearchEVChargingStation reqSearchEVChargingStation);

    CompletableFuture<Void> fireSearchEVChargingStation(ReqSearchEVChargingStation reqSearchEVChargingStation, Duration duration);

    CompletableFuture<Void> fireSelectRoute(ReqSelectRoute.Arguments arguments);

    CompletableFuture<Void> fireSelectRoute(ReqSelectRoute.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSelectRoute(ReqSelectRoute reqSelectRoute);

    CompletableFuture<Void> fireSelectRoute(ReqSelectRoute reqSelectRoute, Duration duration);

    CompletableFuture<Void> fireSetCarLocation(ReqSetCarLocation.Arguments arguments);

    CompletableFuture<Void> fireSetCarLocation(ReqSetCarLocation.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetCarLocation(ReqSetCarLocation reqSetCarLocation);

    CompletableFuture<Void> fireSetCarLocation(ReqSetCarLocation reqSetCarLocation, Duration duration);

    CompletableFuture<Void> fireSetFavoriteList(ReqSetFavoriteList.Arguments arguments);

    CompletableFuture<Void> fireSetFavoriteList(ReqSetFavoriteList.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetFavoriteList(ReqSetFavoriteList reqSetFavoriteList);

    CompletableFuture<Void> fireSetFavoriteList(ReqSetFavoriteList reqSetFavoriteList, Duration duration);

    CompletableFuture<Void> fireSetMapViewMode(ReqSetMapViewMode.Arguments arguments);

    CompletableFuture<Void> fireSetMapViewMode(ReqSetMapViewMode.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetMapViewMode(ReqSetMapViewMode reqSetMapViewMode);

    CompletableFuture<Void> fireSetMapViewMode(ReqSetMapViewMode reqSetMapViewMode, Duration duration);

    CompletableFuture<Void> fireSetNaviMuteMode(ReqSetNaviMuteMode.Arguments arguments);

    CompletableFuture<Void> fireSetNaviMuteMode(ReqSetNaviMuteMode.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetNaviMuteMode(ReqSetNaviMuteMode reqSetNaviMuteMode);

    CompletableFuture<Void> fireSetNaviMuteMode(ReqSetNaviMuteMode reqSetNaviMuteMode, Duration duration);

    CompletableFuture<Void> fireSetRoutePrefer(ReqSetRoutePrefer.Arguments arguments);

    CompletableFuture<Void> fireSetRoutePrefer(ReqSetRoutePrefer.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetRoutePrefer(ReqSetRoutePrefer reqSetRoutePrefer);

    CompletableFuture<Void> fireSetRoutePrefer(ReqSetRoutePrefer reqSetRoutePrefer, Duration duration);

    CompletableFuture<Void> fireSetSETELData(ReqSetSETELData.Arguments arguments);

    CompletableFuture<Void> fireSetSETELData(ReqSetSETELData.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetSETELData(ReqSetSETELData reqSetSETELData);

    CompletableFuture<Void> fireSetSETELData(ReqSetSETELData reqSetSETELData, Duration duration);

    CompletableFuture<Void> fireSetSETELDetailData(ReqSetSETELDetailData.Arguments arguments);

    CompletableFuture<Void> fireSetSETELDetailData(ReqSetSETELDetailData.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetSETELDetailData(ReqSetSETELDetailData reqSetSETELDetailData);

    CompletableFuture<Void> fireSetSETELDetailData(ReqSetSETELDetailData reqSetSETELDetailData, Duration duration);

    CompletableFuture<Void> fireSetSETELServiceStatus(ReqSetSETELServiceStatus.Arguments arguments);

    CompletableFuture<Void> fireSetSETELServiceStatus(ReqSetSETELServiceStatus.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetSETELServiceStatus(ReqSetSETELServiceStatus reqSetSETELServiceStatus);

    CompletableFuture<Void> fireSetSETELServiceStatus(ReqSetSETELServiceStatus reqSetSETELServiceStatus, Duration duration);

    CompletableFuture<Void> fireSetUserDestinationData(ReqSetUserDestinationData.Arguments arguments);

    CompletableFuture<Void> fireSetUserDestinationData(ReqSetUserDestinationData.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetUserDestinationData(ReqSetUserDestinationData reqSetUserDestinationData);

    CompletableFuture<Void> fireSetUserDestinationData(ReqSetUserDestinationData reqSetUserDestinationData, Duration duration);

    CompletableFuture<Void> fireShowMyLocation(ReqShowMyLocation.Arguments arguments);

    CompletableFuture<Void> fireShowMyLocation(ReqShowMyLocation.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireShowMyLocation(ReqShowMyLocation reqShowMyLocation);

    CompletableFuture<Void> fireShowMyLocation(ReqShowMyLocation reqShowMyLocation, Duration duration);

    CompletableFuture<Void> fireSpecialPoiNavi(ReqSpecialPoiNavi.Arguments arguments);

    CompletableFuture<Void> fireSpecialPoiNavi(ReqSpecialPoiNavi.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSpecialPoiNavi(ReqSpecialPoiNavi reqSpecialPoiNavi);

    CompletableFuture<Void> fireSpecialPoiNavi(ReqSpecialPoiNavi reqSpecialPoiNavi, Duration duration);

    CompletableFuture<Void> fireSwitchPrivacyMode(ReqSwitchPrivacyMode.Arguments arguments);

    CompletableFuture<Void> fireSwitchPrivacyMode(ReqSwitchPrivacyMode.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSwitchPrivacyMode(ReqSwitchPrivacyMode reqSwitchPrivacyMode);

    CompletableFuture<Void> fireSwitchPrivacyMode(ReqSwitchPrivacyMode reqSwitchPrivacyMode, Duration duration);

    CompletableFuture<Void> fireSwitchTraffic(ReqSwitchTraffic.Arguments arguments);

    CompletableFuture<Void> fireSwitchTraffic(ReqSwitchTraffic.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSwitchTraffic(ReqSwitchTraffic reqSwitchTraffic);

    CompletableFuture<Void> fireSwitchTraffic(ReqSwitchTraffic reqSwitchTraffic, Duration duration);

    CompletableFuture<Void> fireUpdateFavorite(ReqUpdateFavorite.Arguments arguments);

    CompletableFuture<Void> fireUpdateFavorite(ReqUpdateFavorite.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireUpdateFavorite(ReqUpdateFavorite reqUpdateFavorite);

    CompletableFuture<Void> fireUpdateFavorite(ReqUpdateFavorite reqUpdateFavorite, Duration duration);

    CompletableFuture<Void> fireVRMsgCommand(ReqVRMsgCommand.Arguments arguments);

    CompletableFuture<Void> fireVRMsgCommand(ReqVRMsgCommand.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireVRMsgCommand(ReqVRMsgCommand reqVRMsgCommand);

    CompletableFuture<Void> fireVRMsgCommand(ReqVRMsgCommand reqVRMsgCommand, Duration duration);

    CompletableFuture<ResAlongTheWaySearch.Arguments> requestAlongTheWaySearch(ReqAlongTheWaySearch.Arguments arguments);

    CompletableFuture<ResAlongTheWaySearch.Arguments> requestAlongTheWaySearch(ReqAlongTheWaySearch.Arguments arguments, Duration duration);

    CompletableFuture<ResAlongTheWaySearch> requestAlongTheWaySearch(ReqAlongTheWaySearch reqAlongTheWaySearch);

    CompletableFuture<ResAlongTheWaySearch> requestAlongTheWaySearch(ReqAlongTheWaySearch reqAlongTheWaySearch, Duration duration);

    CompletableFuture<ResBackToMap> requestBackToMap();

    CompletableFuture<ResBackToMap> requestBackToMap(ReqBackToMap reqBackToMap);

    CompletableFuture<ResBackToMap> requestBackToMap(ReqBackToMap reqBackToMap, Duration duration);

    CompletableFuture<ResBackToMap> requestBackToMap(Duration duration);

    CompletableFuture<ResCancelNavi> requestCancelNavi();

    CompletableFuture<ResCancelNavi> requestCancelNavi(ReqCancelNavi reqCancelNavi);

    CompletableFuture<ResCancelNavi> requestCancelNavi(ReqCancelNavi reqCancelNavi, Duration duration);

    CompletableFuture<ResCancelNavi> requestCancelNavi(Duration duration);

    CompletableFuture<ResChangeThemeResource.Arguments> requestChangeThemeResource(ReqChangeThemeResource.Arguments arguments);

    CompletableFuture<ResChangeThemeResource.Arguments> requestChangeThemeResource(ReqChangeThemeResource.Arguments arguments, Duration duration);

    CompletableFuture<ResChangeThemeResource> requestChangeThemeResource(ReqChangeThemeResource reqChangeThemeResource);

    CompletableFuture<ResChangeThemeResource> requestChangeThemeResource(ReqChangeThemeResource reqChangeThemeResource, Duration duration);

    CompletableFuture<ResClearUserDestinationData> requestClearUserDestinationData();

    CompletableFuture<ResClearUserDestinationData> requestClearUserDestinationData(ReqClearUserDestinationData reqClearUserDestinationData);

    CompletableFuture<ResClearUserDestinationData> requestClearUserDestinationData(ReqClearUserDestinationData reqClearUserDestinationData, Duration duration);

    CompletableFuture<ResClearUserDestinationData> requestClearUserDestinationData(Duration duration);

    CompletableFuture<ResEditFavoritePois.Arguments> requestEditFavoritePois(ReqEditFavoritePois.Arguments arguments);

    CompletableFuture<ResEditFavoritePois.Arguments> requestEditFavoritePois(ReqEditFavoritePois.Arguments arguments, Duration duration);

    CompletableFuture<ResEditFavoritePois> requestEditFavoritePois(ReqEditFavoritePois reqEditFavoritePois);

    CompletableFuture<ResEditFavoritePois> requestEditFavoritePois(ReqEditFavoritePois reqEditFavoritePois, Duration duration);

    CompletableFuture<ResExitMap> requestExitMap();

    CompletableFuture<ResExitMap> requestExitMap(ReqExitMap reqExitMap);

    CompletableFuture<ResExitMap> requestExitMap(ReqExitMap reqExitMap, Duration duration);

    CompletableFuture<ResExitMap> requestExitMap(Duration duration);

    CompletableFuture<ResGetADAreaUpdate> requestGetADAreaUpdate();

    CompletableFuture<ResGetADAreaUpdate> requestGetADAreaUpdate(ReqGetADAreaUpdate reqGetADAreaUpdate);

    CompletableFuture<ResGetADAreaUpdate> requestGetADAreaUpdate(ReqGetADAreaUpdate reqGetADAreaUpdate, Duration duration);

    CompletableFuture<ResGetADAreaUpdate> requestGetADAreaUpdate(Duration duration);

    CompletableFuture<ResGetArrivedDest> requestGetArrivedDest();

    CompletableFuture<ResGetArrivedDest> requestGetArrivedDest(ReqGetArrivedDest reqGetArrivedDest);

    CompletableFuture<ResGetArrivedDest> requestGetArrivedDest(ReqGetArrivedDest reqGetArrivedDest, Duration duration);

    CompletableFuture<ResGetArrivedDest> requestGetArrivedDest(Duration duration);

    CompletableFuture<ResGetArrivedLastMileUpdate> requestGetArrivedLastMileUpdate();

    CompletableFuture<ResGetArrivedLastMileUpdate> requestGetArrivedLastMileUpdate(ReqGetArrivedLastMileUpdate reqGetArrivedLastMileUpdate);

    CompletableFuture<ResGetArrivedLastMileUpdate> requestGetArrivedLastMileUpdate(ReqGetArrivedLastMileUpdate reqGetArrivedLastMileUpdate, Duration duration);

    CompletableFuture<ResGetArrivedLastMileUpdate> requestGetArrivedLastMileUpdate(Duration duration);

    CompletableFuture<ResGetAvailableRestoreGuidance> requestGetAvailableRestoreGuidance();

    CompletableFuture<ResGetAvailableRestoreGuidance> requestGetAvailableRestoreGuidance(ReqGetAvailableRestoreGuidance reqGetAvailableRestoreGuidance);

    CompletableFuture<ResGetAvailableRestoreGuidance> requestGetAvailableRestoreGuidance(ReqGetAvailableRestoreGuidance reqGetAvailableRestoreGuidance, Duration duration);

    CompletableFuture<ResGetAvailableRestoreGuidance> requestGetAvailableRestoreGuidance(Duration duration);

    CompletableFuture<ResGetBatteryPreheatingInfoUpdate> requestGetBatteryPreheatingInfoUpdate();

    CompletableFuture<ResGetBatteryPreheatingInfoUpdate> requestGetBatteryPreheatingInfoUpdate(ReqGetBatteryPreheatingInfoUpdate reqGetBatteryPreheatingInfoUpdate);

    CompletableFuture<ResGetBatteryPreheatingInfoUpdate> requestGetBatteryPreheatingInfoUpdate(ReqGetBatteryPreheatingInfoUpdate reqGetBatteryPreheatingInfoUpdate, Duration duration);

    CompletableFuture<ResGetBatteryPreheatingInfoUpdate> requestGetBatteryPreheatingInfoUpdate(Duration duration);

    CompletableFuture<ResGetCommuteInfo> requestGetCommuteInfo();

    CompletableFuture<ResGetCommuteInfo> requestGetCommuteInfo(ReqGetCommuteInfo reqGetCommuteInfo);

    CompletableFuture<ResGetCommuteInfo> requestGetCommuteInfo(ReqGetCommuteInfo reqGetCommuteInfo, Duration duration);

    CompletableFuture<ResGetCommuteInfo> requestGetCommuteInfo(Duration duration);

    CompletableFuture<ResGetCommutePush> requestGetCommutePush();

    CompletableFuture<ResGetCommutePush> requestGetCommutePush(ReqGetCommutePush reqGetCommutePush);

    CompletableFuture<ResGetCommutePush> requestGetCommutePush(ReqGetCommutePush reqGetCommutePush, Duration duration);

    CompletableFuture<ResGetCommutePush> requestGetCommutePush(Duration duration);

    CompletableFuture<ResGetDayNightStatusUpdate> requestGetDayNightStatusUpdate();

    CompletableFuture<ResGetDayNightStatusUpdate> requestGetDayNightStatusUpdate(ReqGetDayNightStatusUpdate reqGetDayNightStatusUpdate);

    CompletableFuture<ResGetDayNightStatusUpdate> requestGetDayNightStatusUpdate(ReqGetDayNightStatusUpdate reqGetDayNightStatusUpdate, Duration duration);

    CompletableFuture<ResGetDayNightStatusUpdate> requestGetDayNightStatusUpdate(Duration duration);

    CompletableFuture<ResGetDeadReckoningStatus> requestGetDeadReckoningStatus();

    CompletableFuture<ResGetDeadReckoningStatus> requestGetDeadReckoningStatus(ReqGetDeadReckoningStatus reqGetDeadReckoningStatus);

    CompletableFuture<ResGetDeadReckoningStatus> requestGetDeadReckoningStatus(ReqGetDeadReckoningStatus reqGetDeadReckoningStatus, Duration duration);

    CompletableFuture<ResGetDeadReckoningStatus> requestGetDeadReckoningStatus(Duration duration);

    CompletableFuture<ResGetDetailInfo.Arguments> requestGetDetailInfo(ReqGetDetailInfo.Arguments arguments);

    CompletableFuture<ResGetDetailInfo.Arguments> requestGetDetailInfo(ReqGetDetailInfo.Arguments arguments, Duration duration);

    CompletableFuture<ResGetDetailInfo> requestGetDetailInfo(ReqGetDetailInfo reqGetDetailInfo);

    CompletableFuture<ResGetDetailInfo> requestGetDetailInfo(ReqGetDetailInfo reqGetDetailInfo, Duration duration);

    CompletableFuture<ResGetEVDetailInfo.Arguments> requestGetEVDetailInfo(ReqGetEVDetailInfo.Arguments arguments);

    CompletableFuture<ResGetEVDetailInfo.Arguments> requestGetEVDetailInfo(ReqGetEVDetailInfo.Arguments arguments, Duration duration);

    CompletableFuture<ResGetEVDetailInfo> requestGetEVDetailInfo(ReqGetEVDetailInfo reqGetEVDetailInfo);

    CompletableFuture<ResGetEVDetailInfo> requestGetEVDetailInfo(ReqGetEVDetailInfo reqGetEVDetailInfo, Duration duration);

    CompletableFuture<ResGetFavoritePois.Arguments> requestGetFavoritePois(ReqGetFavoritePois.Arguments arguments);

    CompletableFuture<ResGetFavoritePois.Arguments> requestGetFavoritePois(ReqGetFavoritePois.Arguments arguments, Duration duration);

    CompletableFuture<ResGetFavoritePois> requestGetFavoritePois(ReqGetFavoritePois reqGetFavoritePois);

    CompletableFuture<ResGetFavoritePois> requestGetFavoritePois(ReqGetFavoritePois reqGetFavoritePois, Duration duration);

    CompletableFuture<ResGetFavoritesInfoUpdate> requestGetFavoritesInfoUpdate();

    CompletableFuture<ResGetFavoritesInfoUpdate> requestGetFavoritesInfoUpdate(ReqGetFavoritesInfoUpdate reqGetFavoritesInfoUpdate);

    CompletableFuture<ResGetFavoritesInfoUpdate> requestGetFavoritesInfoUpdate(ReqGetFavoritesInfoUpdate reqGetFavoritesInfoUpdate, Duration duration);

    CompletableFuture<ResGetFavoritesInfoUpdate> requestGetFavoritesInfoUpdate(Duration duration);

    CompletableFuture<ResGetFrontTraffic.Arguments> requestGetFrontTraffic(ReqGetFrontTraffic.Arguments arguments);

    CompletableFuture<ResGetFrontTraffic.Arguments> requestGetFrontTraffic(ReqGetFrontTraffic.Arguments arguments, Duration duration);

    CompletableFuture<ResGetFrontTraffic> requestGetFrontTraffic(ReqGetFrontTraffic reqGetFrontTraffic);

    CompletableFuture<ResGetFrontTraffic> requestGetFrontTraffic(ReqGetFrontTraffic reqGetFrontTraffic, Duration duration);

    CompletableFuture<ResGetGetFavoriteList> requestGetGetFavoriteList();

    CompletableFuture<ResGetGetFavoriteList> requestGetGetFavoriteList(ReqGetGetFavoriteList reqGetGetFavoriteList);

    CompletableFuture<ResGetGetFavoriteList> requestGetGetFavoriteList(ReqGetGetFavoriteList reqGetGetFavoriteList, Duration duration);

    CompletableFuture<ResGetGetFavoriteList> requestGetGetFavoriteList(Duration duration);

    CompletableFuture<ResGetGetLoginStatus> requestGetGetLoginStatus();

    CompletableFuture<ResGetGetLoginStatus> requestGetGetLoginStatus(ReqGetGetLoginStatus reqGetGetLoginStatus);

    CompletableFuture<ResGetGetLoginStatus> requestGetGetLoginStatus(ReqGetGetLoginStatus reqGetGetLoginStatus, Duration duration);

    CompletableFuture<ResGetGetLoginStatus> requestGetGetLoginStatus(Duration duration);

    CompletableFuture<ResGetGoToSystemSettingPage> requestGetGoToSystemSettingPage();

    CompletableFuture<ResGetGoToSystemSettingPage> requestGetGoToSystemSettingPage(ReqGetGoToSystemSettingPage reqGetGoToSystemSettingPage);

    CompletableFuture<ResGetGoToSystemSettingPage> requestGetGoToSystemSettingPage(ReqGetGoToSystemSettingPage reqGetGoToSystemSettingPage, Duration duration);

    CompletableFuture<ResGetGoToSystemSettingPage> requestGetGoToSystemSettingPage(Duration duration);

    CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate();

    CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate);

    CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate, Duration duration);

    CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate(Duration duration);

    CompletableFuture<ResGetGuidanceInfoZeekrUpdate> requestGetGuidanceInfoZeekrUpdate();

    CompletableFuture<ResGetGuidanceInfoZeekrUpdate> requestGetGuidanceInfoZeekrUpdate(ReqGetGuidanceInfoZeekrUpdate reqGetGuidanceInfoZeekrUpdate);

    CompletableFuture<ResGetGuidanceInfoZeekrUpdate> requestGetGuidanceInfoZeekrUpdate(ReqGetGuidanceInfoZeekrUpdate reqGetGuidanceInfoZeekrUpdate, Duration duration);

    CompletableFuture<ResGetGuidanceInfoZeekrUpdate> requestGetGuidanceInfoZeekrUpdate(Duration duration);

    CompletableFuture<ResGetGuideInfo> requestGetGuideInfo();

    CompletableFuture<ResGetGuideInfo> requestGetGuideInfo(ReqGetGuideInfo reqGetGuideInfo);

    CompletableFuture<ResGetGuideInfo> requestGetGuideInfo(ReqGetGuideInfo reqGetGuideInfo, Duration duration);

    CompletableFuture<ResGetGuideInfo> requestGetGuideInfo(Duration duration);

    CompletableFuture<ResGetGuideInfoZeekr> requestGetGuideInfoZeekr();

    CompletableFuture<ResGetGuideInfoZeekr> requestGetGuideInfoZeekr(ReqGetGuideInfoZeekr reqGetGuideInfoZeekr);

    CompletableFuture<ResGetGuideInfoZeekr> requestGetGuideInfoZeekr(ReqGetGuideInfoZeekr reqGetGuideInfoZeekr, Duration duration);

    CompletableFuture<ResGetGuideInfoZeekr> requestGetGuideInfoZeekr(Duration duration);

    CompletableFuture<ResGetHighwayExitInfo.Arguments> requestGetHighwayExitInfo(ReqGetHighwayExitInfo.Arguments arguments);

    CompletableFuture<ResGetHighwayExitInfo.Arguments> requestGetHighwayExitInfo(ReqGetHighwayExitInfo.Arguments arguments, Duration duration);

    CompletableFuture<ResGetHighwayExitInfo> requestGetHighwayExitInfo(ReqGetHighwayExitInfo reqGetHighwayExitInfo);

    CompletableFuture<ResGetHighwayExitInfo> requestGetHighwayExitInfo(ReqGetHighwayExitInfo reqGetHighwayExitInfo, Duration duration);

    CompletableFuture<ResGetHighwayInfoUpdate> requestGetHighwayInfoUpdate();

    CompletableFuture<ResGetHighwayInfoUpdate> requestGetHighwayInfoUpdate(ReqGetHighwayInfoUpdate reqGetHighwayInfoUpdate);

    CompletableFuture<ResGetHighwayInfoUpdate> requestGetHighwayInfoUpdate(ReqGetHighwayInfoUpdate reqGetHighwayInfoUpdate, Duration duration);

    CompletableFuture<ResGetHighwayInfoUpdate> requestGetHighwayInfoUpdate(Duration duration);

    CompletableFuture<ResGetHistoryPois.Arguments> requestGetHistoryPois(ReqGetHistoryPois.Arguments arguments);

    CompletableFuture<ResGetHistoryPois.Arguments> requestGetHistoryPois(ReqGetHistoryPois.Arguments arguments, Duration duration);

    CompletableFuture<ResGetHistoryPois> requestGetHistoryPois(ReqGetHistoryPois reqGetHistoryPois);

    CompletableFuture<ResGetHistoryPois> requestGetHistoryPois(ReqGetHistoryPois reqGetHistoryPois, Duration duration);

    CompletableFuture<ResGetLanesInfoUpdate> requestGetLanesInfoUpdate();

    CompletableFuture<ResGetLanesInfoUpdate> requestGetLanesInfoUpdate(ReqGetLanesInfoUpdate reqGetLanesInfoUpdate);

    CompletableFuture<ResGetLanesInfoUpdate> requestGetLanesInfoUpdate(ReqGetLanesInfoUpdate reqGetLanesInfoUpdate, Duration duration);

    CompletableFuture<ResGetLanesInfoUpdate> requestGetLanesInfoUpdate(Duration duration);

    CompletableFuture<ResGetMapStatusUpdate> requestGetMapStatusUpdate();

    CompletableFuture<ResGetMapStatusUpdate> requestGetMapStatusUpdate(ReqGetMapStatusUpdate reqGetMapStatusUpdate);

    CompletableFuture<ResGetMapStatusUpdate> requestGetMapStatusUpdate(ReqGetMapStatusUpdate reqGetMapStatusUpdate, Duration duration);

    CompletableFuture<ResGetMapStatusUpdate> requestGetMapStatusUpdate(Duration duration);

    CompletableFuture<ResGetMuteModeUpdate> requestGetMuteModeUpdate();

    CompletableFuture<ResGetMuteModeUpdate> requestGetMuteModeUpdate(ReqGetMuteModeUpdate reqGetMuteModeUpdate);

    CompletableFuture<ResGetMuteModeUpdate> requestGetMuteModeUpdate(ReqGetMuteModeUpdate reqGetMuteModeUpdate, Duration duration);

    CompletableFuture<ResGetMuteModeUpdate> requestGetMuteModeUpdate(Duration duration);

    CompletableFuture<ResGetNaviMapStatusChanged> requestGetNaviMapStatusChanged();

    CompletableFuture<ResGetNaviMapStatusChanged> requestGetNaviMapStatusChanged(ReqGetNaviMapStatusChanged reqGetNaviMapStatusChanged);

    CompletableFuture<ResGetNaviMapStatusChanged> requestGetNaviMapStatusChanged(ReqGetNaviMapStatusChanged reqGetNaviMapStatusChanged, Duration duration);

    CompletableFuture<ResGetNaviMapStatusChanged> requestGetNaviMapStatusChanged(Duration duration);

    CompletableFuture<ResGetNaviMuteMode> requestGetNaviMuteMode();

    CompletableFuture<ResGetNaviMuteMode> requestGetNaviMuteMode(ReqGetNaviMuteMode reqGetNaviMuteMode);

    CompletableFuture<ResGetNaviMuteMode> requestGetNaviMuteMode(ReqGetNaviMuteMode reqGetNaviMuteMode, Duration duration);

    CompletableFuture<ResGetNaviMuteMode> requestGetNaviMuteMode(Duration duration);

    CompletableFuture<ResGetNaviStatusUpdate> requestGetNaviStatusUpdate();

    CompletableFuture<ResGetNaviStatusUpdate> requestGetNaviStatusUpdate(ReqGetNaviStatusUpdate reqGetNaviStatusUpdate);

    CompletableFuture<ResGetNaviStatusUpdate> requestGetNaviStatusUpdate(ReqGetNaviStatusUpdate reqGetNaviStatusUpdate, Duration duration);

    CompletableFuture<ResGetNaviStatusUpdate> requestGetNaviStatusUpdate(Duration duration);

    CompletableFuture<ResGetRequestAddFavorite> requestGetRequestAddFavorite();

    CompletableFuture<ResGetRequestAddFavorite> requestGetRequestAddFavorite(ReqGetRequestAddFavorite reqGetRequestAddFavorite);

    CompletableFuture<ResGetRequestAddFavorite> requestGetRequestAddFavorite(ReqGetRequestAddFavorite reqGetRequestAddFavorite, Duration duration);

    CompletableFuture<ResGetRequestAddFavorite> requestGetRequestAddFavorite(Duration duration);

    CompletableFuture<ResGetRequestRemoveFavorite> requestGetRequestRemoveFavorite();

    CompletableFuture<ResGetRequestRemoveFavorite> requestGetRequestRemoveFavorite(ReqGetRequestRemoveFavorite reqGetRequestRemoveFavorite);

    CompletableFuture<ResGetRequestRemoveFavorite> requestGetRequestRemoveFavorite(ReqGetRequestRemoveFavorite reqGetRequestRemoveFavorite, Duration duration);

    CompletableFuture<ResGetRequestRemoveFavorite> requestGetRequestRemoveFavorite(Duration duration);

    CompletableFuture<ResGetRequestSETELData> requestGetRequestSETELData();

    CompletableFuture<ResGetRequestSETELData> requestGetRequestSETELData(ReqGetRequestSETELData reqGetRequestSETELData);

    CompletableFuture<ResGetRequestSETELData> requestGetRequestSETELData(ReqGetRequestSETELData reqGetRequestSETELData, Duration duration);

    CompletableFuture<ResGetRequestSETELData> requestGetRequestSETELData(Duration duration);

    CompletableFuture<ResGetRequestSETELDetail> requestGetRequestSETELDetail();

    CompletableFuture<ResGetRequestSETELDetail> requestGetRequestSETELDetail(ReqGetRequestSETELDetail reqGetRequestSETELDetail);

    CompletableFuture<ResGetRequestSETELDetail> requestGetRequestSETELDetail(ReqGetRequestSETELDetail reqGetRequestSETELDetail, Duration duration);

    CompletableFuture<ResGetRequestSETELDetail> requestGetRequestSETELDetail(Duration duration);

    CompletableFuture<ResGetRequestSETELServiceStatus> requestGetRequestSETELServiceStatus();

    CompletableFuture<ResGetRequestSETELServiceStatus> requestGetRequestSETELServiceStatus(ReqGetRequestSETELServiceStatus reqGetRequestSETELServiceStatus);

    CompletableFuture<ResGetRequestSETELServiceStatus> requestGetRequestSETELServiceStatus(ReqGetRequestSETELServiceStatus reqGetRequestSETELServiceStatus, Duration duration);

    CompletableFuture<ResGetRequestSETELServiceStatus> requestGetRequestSETELServiceStatus(Duration duration);

    CompletableFuture<ResGetRequestUpdateFavorite> requestGetRequestUpdateFavorite();

    CompletableFuture<ResGetRequestUpdateFavorite> requestGetRequestUpdateFavorite(ReqGetRequestUpdateFavorite reqGetRequestUpdateFavorite);

    CompletableFuture<ResGetRequestUpdateFavorite> requestGetRequestUpdateFavorite(ReqGetRequestUpdateFavorite reqGetRequestUpdateFavorite, Duration duration);

    CompletableFuture<ResGetRequestUpdateFavorite> requestGetRequestUpdateFavorite(Duration duration);

    CompletableFuture<ResGetRespAuthorizePrivacyAgreement> requestGetRespAuthorizePrivacyAgreement();

    CompletableFuture<ResGetRespAuthorizePrivacyAgreement> requestGetRespAuthorizePrivacyAgreement(ReqGetRespAuthorizePrivacyAgreement reqGetRespAuthorizePrivacyAgreement);

    CompletableFuture<ResGetRespAuthorizePrivacyAgreement> requestGetRespAuthorizePrivacyAgreement(ReqGetRespAuthorizePrivacyAgreement reqGetRespAuthorizePrivacyAgreement, Duration duration);

    CompletableFuture<ResGetRespAuthorizePrivacyAgreement> requestGetRespAuthorizePrivacyAgreement(Duration duration);

    CompletableFuture<ResGetRoundAboutInfoUpdate> requestGetRoundAboutInfoUpdate();

    CompletableFuture<ResGetRoundAboutInfoUpdate> requestGetRoundAboutInfoUpdate(ReqGetRoundAboutInfoUpdate reqGetRoundAboutInfoUpdate);

    CompletableFuture<ResGetRoundAboutInfoUpdate> requestGetRoundAboutInfoUpdate(ReqGetRoundAboutInfoUpdate reqGetRoundAboutInfoUpdate, Duration duration);

    CompletableFuture<ResGetRoundAboutInfoUpdate> requestGetRoundAboutInfoUpdate(Duration duration);

    CompletableFuture<ResGetRouteInfo> requestGetRouteInfo();

    CompletableFuture<ResGetRouteInfo> requestGetRouteInfo(ReqGetRouteInfo reqGetRouteInfo);

    CompletableFuture<ResGetRouteInfo> requestGetRouteInfo(ReqGetRouteInfo reqGetRouteInfo, Duration duration);

    CompletableFuture<ResGetRouteInfo> requestGetRouteInfo(Duration duration);

    CompletableFuture<ResGetRouteInfoUpdate> requestGetRouteInfoUpdate();

    CompletableFuture<ResGetRouteInfoUpdate> requestGetRouteInfoUpdate(ReqGetRouteInfoUpdate reqGetRouteInfoUpdate);

    CompletableFuture<ResGetRouteInfoUpdate> requestGetRouteInfoUpdate(ReqGetRouteInfoUpdate reqGetRouteInfoUpdate, Duration duration);

    CompletableFuture<ResGetRouteInfoUpdate> requestGetRouteInfoUpdate(Duration duration);

    CompletableFuture<ResGetRouteSelectedUpdate> requestGetRouteSelectedUpdate();

    CompletableFuture<ResGetRouteSelectedUpdate> requestGetRouteSelectedUpdate(ReqGetRouteSelectedUpdate reqGetRouteSelectedUpdate);

    CompletableFuture<ResGetRouteSelectedUpdate> requestGetRouteSelectedUpdate(ReqGetRouteSelectedUpdate reqGetRouteSelectedUpdate, Duration duration);

    CompletableFuture<ResGetRouteSelectedUpdate> requestGetRouteSelectedUpdate(Duration duration);

    CompletableFuture<ResGetRouteViewStatusUpdate> requestGetRouteViewStatusUpdate();

    CompletableFuture<ResGetRouteViewStatusUpdate> requestGetRouteViewStatusUpdate(ReqGetRouteViewStatusUpdate reqGetRouteViewStatusUpdate);

    CompletableFuture<ResGetRouteViewStatusUpdate> requestGetRouteViewStatusUpdate(ReqGetRouteViewStatusUpdate reqGetRouteViewStatusUpdate, Duration duration);

    CompletableFuture<ResGetRouteViewStatusUpdate> requestGetRouteViewStatusUpdate(Duration duration);

    CompletableFuture<ResGetScaleStatus> requestGetScaleStatus();

    CompletableFuture<ResGetScaleStatus> requestGetScaleStatus(ReqGetScaleStatus reqGetScaleStatus);

    CompletableFuture<ResGetScaleStatus> requestGetScaleStatus(ReqGetScaleStatus reqGetScaleStatus, Duration duration);

    CompletableFuture<ResGetScaleStatus> requestGetScaleStatus(Duration duration);

    CompletableFuture<ResGetServiceStatusUpdate> requestGetServiceStatusUpdate();

    CompletableFuture<ResGetServiceStatusUpdate> requestGetServiceStatusUpdate(ReqGetServiceStatusUpdate reqGetServiceStatusUpdate);

    CompletableFuture<ResGetServiceStatusUpdate> requestGetServiceStatusUpdate(ReqGetServiceStatusUpdate reqGetServiceStatusUpdate, Duration duration);

    CompletableFuture<ResGetServiceStatusUpdate> requestGetServiceStatusUpdate(Duration duration);

    CompletableFuture<ResGetSpeedLimitInfoUpdate> requestGetSpeedLimitInfoUpdate();

    CompletableFuture<ResGetSpeedLimitInfoUpdate> requestGetSpeedLimitInfoUpdate(ReqGetSpeedLimitInfoUpdate reqGetSpeedLimitInfoUpdate);

    CompletableFuture<ResGetSpeedLimitInfoUpdate> requestGetSpeedLimitInfoUpdate(ReqGetSpeedLimitInfoUpdate reqGetSpeedLimitInfoUpdate, Duration duration);

    CompletableFuture<ResGetSpeedLimitInfoUpdate> requestGetSpeedLimitInfoUpdate(Duration duration);

    CompletableFuture<ResGetSwitchVRCommand> requestGetSwitchVRCommand();

    CompletableFuture<ResGetSwitchVRCommand> requestGetSwitchVRCommand(ReqGetSwitchVRCommand reqGetSwitchVRCommand);

    CompletableFuture<ResGetSwitchVRCommand> requestGetSwitchVRCommand(ReqGetSwitchVRCommand reqGetSwitchVRCommand, Duration duration);

    CompletableFuture<ResGetSwitchVRCommand> requestGetSwitchVRCommand(Duration duration);

    CompletableFuture<ResGetTimeZoneInfoUpdate> requestGetTimeZoneInfoUpdate();

    CompletableFuture<ResGetTimeZoneInfoUpdate> requestGetTimeZoneInfoUpdate(ReqGetTimeZoneInfoUpdate reqGetTimeZoneInfoUpdate);

    CompletableFuture<ResGetTimeZoneInfoUpdate> requestGetTimeZoneInfoUpdate(ReqGetTimeZoneInfoUpdate reqGetTimeZoneInfoUpdate, Duration duration);

    CompletableFuture<ResGetTimeZoneInfoUpdate> requestGetTimeZoneInfoUpdate(Duration duration);

    CompletableFuture<ResGetTrafficConditionUpdate> requestGetTrafficConditionUpdate();

    CompletableFuture<ResGetTrafficConditionUpdate> requestGetTrafficConditionUpdate(ReqGetTrafficConditionUpdate reqGetTrafficConditionUpdate);

    CompletableFuture<ResGetTrafficConditionUpdate> requestGetTrafficConditionUpdate(ReqGetTrafficConditionUpdate reqGetTrafficConditionUpdate, Duration duration);

    CompletableFuture<ResGetTrafficConditionUpdate> requestGetTrafficConditionUpdate(Duration duration);

    CompletableFuture<ResGetTrafficStatusUpdate> requestGetTrafficStatusUpdate();

    CompletableFuture<ResGetTrafficStatusUpdate> requestGetTrafficStatusUpdate(ReqGetTrafficStatusUpdate reqGetTrafficStatusUpdate);

    CompletableFuture<ResGetTrafficStatusUpdate> requestGetTrafficStatusUpdate(ReqGetTrafficStatusUpdate reqGetTrafficStatusUpdate, Duration duration);

    CompletableFuture<ResGetTrafficStatusUpdate> requestGetTrafficStatusUpdate(Duration duration);

    CompletableFuture<ResGetTrafficSummaryInfo.Arguments> requestGetTrafficSummaryInfo(ReqGetTrafficSummaryInfo.Arguments arguments);

    CompletableFuture<ResGetTrafficSummaryInfo.Arguments> requestGetTrafficSummaryInfo(ReqGetTrafficSummaryInfo.Arguments arguments, Duration duration);

    CompletableFuture<ResGetTrafficSummaryInfo> requestGetTrafficSummaryInfo(ReqGetTrafficSummaryInfo reqGetTrafficSummaryInfo);

    CompletableFuture<ResGetTrafficSummaryInfo> requestGetTrafficSummaryInfo(ReqGetTrafficSummaryInfo reqGetTrafficSummaryInfo, Duration duration);

    CompletableFuture<ResGetUserDestinationInfoUpdate> requestGetUserDestinationInfoUpdate();

    CompletableFuture<ResGetUserDestinationInfoUpdate> requestGetUserDestinationInfoUpdate(ReqGetUserDestinationInfoUpdate reqGetUserDestinationInfoUpdate);

    CompletableFuture<ResGetUserDestinationInfoUpdate> requestGetUserDestinationInfoUpdate(ReqGetUserDestinationInfoUpdate reqGetUserDestinationInfoUpdate, Duration duration);

    CompletableFuture<ResGetUserDestinationInfoUpdate> requestGetUserDestinationInfoUpdate(Duration duration);

    CompletableFuture<ResGetVRMsgCommandUpdate> requestGetVRMsgCommandUpdate();

    CompletableFuture<ResGetVRMsgCommandUpdate> requestGetVRMsgCommandUpdate(ReqGetVRMsgCommandUpdate reqGetVRMsgCommandUpdate);

    CompletableFuture<ResGetVRMsgCommandUpdate> requestGetVRMsgCommandUpdate(ReqGetVRMsgCommandUpdate reqGetVRMsgCommandUpdate, Duration duration);

    CompletableFuture<ResGetVRMsgCommandUpdate> requestGetVRMsgCommandUpdate(Duration duration);

    CompletableFuture<ResGetViewModeUpdate> requestGetViewModeUpdate();

    CompletableFuture<ResGetViewModeUpdate> requestGetViewModeUpdate(ReqGetViewModeUpdate reqGetViewModeUpdate);

    CompletableFuture<ResGetViewModeUpdate> requestGetViewModeUpdate(ReqGetViewModeUpdate reqGetViewModeUpdate, Duration duration);

    CompletableFuture<ResGetViewModeUpdate> requestGetViewModeUpdate(Duration duration);

    CompletableFuture<ResGetViewPrivacyDetails> requestGetViewPrivacyDetails();

    CompletableFuture<ResGetViewPrivacyDetails> requestGetViewPrivacyDetails(ReqGetViewPrivacyDetails reqGetViewPrivacyDetails);

    CompletableFuture<ResGetViewPrivacyDetails> requestGetViewPrivacyDetails(ReqGetViewPrivacyDetails reqGetViewPrivacyDetails, Duration duration);

    CompletableFuture<ResGetViewPrivacyDetails> requestGetViewPrivacyDetails(Duration duration);

    CompletableFuture<ResGetZoomStatusUpdate> requestGetZoomStatusUpdate();

    CompletableFuture<ResGetZoomStatusUpdate> requestGetZoomStatusUpdate(ReqGetZoomStatusUpdate reqGetZoomStatusUpdate);

    CompletableFuture<ResGetZoomStatusUpdate> requestGetZoomStatusUpdate(ReqGetZoomStatusUpdate reqGetZoomStatusUpdate, Duration duration);

    CompletableFuture<ResGetZoomStatusUpdate> requestGetZoomStatusUpdate(Duration duration);

    CompletableFuture<ResGoFavorite.Arguments> requestGoFavorite(ReqGoFavorite.Arguments arguments);

    CompletableFuture<ResGoFavorite.Arguments> requestGoFavorite(ReqGoFavorite.Arguments arguments, Duration duration);

    CompletableFuture<ResGoFavorite> requestGoFavorite(ReqGoFavorite reqGoFavorite);

    CompletableFuture<ResGoFavorite> requestGoFavorite(ReqGoFavorite reqGoFavorite, Duration duration);

    CompletableFuture<ResLaunchMap> requestLaunchMap();

    CompletableFuture<ResLaunchMap> requestLaunchMap(ReqLaunchMap reqLaunchMap);

    CompletableFuture<ResLaunchMap> requestLaunchMap(ReqLaunchMap reqLaunchMap, Duration duration);

    CompletableFuture<ResLaunchMap> requestLaunchMap(Duration duration);

    CompletableFuture<ResMapZoomInOut.Arguments> requestMapZoomInOut(ReqMapZoomInOut.Arguments arguments);

    CompletableFuture<ResMapZoomInOut.Arguments> requestMapZoomInOut(ReqMapZoomInOut.Arguments arguments, Duration duration);

    CompletableFuture<ResMapZoomInOut> requestMapZoomInOut(ReqMapZoomInOut reqMapZoomInOut);

    CompletableFuture<ResMapZoomInOut> requestMapZoomInOut(ReqMapZoomInOut reqMapZoomInOut, Duration duration);

    CompletableFuture<ResModifyNaviVia.Arguments> requestModifyNaviVia(ReqModifyNaviVia.Arguments arguments);

    CompletableFuture<ResModifyNaviVia.Arguments> requestModifyNaviVia(ReqModifyNaviVia.Arguments arguments, Duration duration);

    CompletableFuture<ResModifyNaviVia> requestModifyNaviVia(ReqModifyNaviVia reqModifyNaviVia);

    CompletableFuture<ResModifyNaviVia> requestModifyNaviVia(ReqModifyNaviVia reqModifyNaviVia, Duration duration);

    CompletableFuture<ResNaviFullView.Arguments> requestNaviFullView(ReqNaviFullView.Arguments arguments);

    CompletableFuture<ResNaviFullView.Arguments> requestNaviFullView(ReqNaviFullView.Arguments arguments, Duration duration);

    CompletableFuture<ResNaviFullView> requestNaviFullView(ReqNaviFullView reqNaviFullView);

    CompletableFuture<ResNaviFullView> requestNaviFullView(ReqNaviFullView reqNaviFullView, Duration duration);

    CompletableFuture<ResOpenDestinationSearch> requestOpenDestinationSearch();

    CompletableFuture<ResOpenDestinationSearch> requestOpenDestinationSearch(ReqOpenDestinationSearch reqOpenDestinationSearch);

    CompletableFuture<ResOpenDestinationSearch> requestOpenDestinationSearch(ReqOpenDestinationSearch reqOpenDestinationSearch, Duration duration);

    CompletableFuture<ResOpenDestinationSearch> requestOpenDestinationSearch(Duration duration);

    CompletableFuture<ResRemoveWaypoint.Arguments> requestRemoveWaypoint(ReqRemoveWaypoint.Arguments arguments);

    CompletableFuture<ResRemoveWaypoint.Arguments> requestRemoveWaypoint(ReqRemoveWaypoint.Arguments arguments, Duration duration);

    CompletableFuture<ResRemoveWaypoint> requestRemoveWaypoint(ReqRemoveWaypoint reqRemoveWaypoint);

    CompletableFuture<ResRemoveWaypoint> requestRemoveWaypoint(ReqRemoveWaypoint reqRemoveWaypoint, Duration duration);

    CompletableFuture<ResRequestGeoDecoder.Arguments> requestRequestGeoDecoder(ReqRequestGeoDecoder.Arguments arguments);

    CompletableFuture<ResRequestGeoDecoder.Arguments> requestRequestGeoDecoder(ReqRequestGeoDecoder.Arguments arguments, Duration duration);

    CompletableFuture<ResRequestGeoDecoder> requestRequestGeoDecoder(ReqRequestGeoDecoder reqRequestGeoDecoder);

    CompletableFuture<ResRequestGeoDecoder> requestRequestGeoDecoder(ReqRequestGeoDecoder reqRequestGeoDecoder, Duration duration);

    CompletableFuture<ResRequestOpenMap.Arguments> requestRequestOpenMap(ReqRequestOpenMap.Arguments arguments);

    CompletableFuture<ResRequestOpenMap.Arguments> requestRequestOpenMap(ReqRequestOpenMap.Arguments arguments, Duration duration);

    CompletableFuture<ResRequestOpenMap> requestRequestOpenMap(ReqRequestOpenMap reqRequestOpenMap);

    CompletableFuture<ResRequestOpenMap> requestRequestOpenMap(ReqRequestOpenMap reqRequestOpenMap, Duration duration);

    CompletableFuture<ResRequestRoutePlanOrNavi.Arguments> requestRequestRoutePlanOrNavi(ReqRequestRoutePlanOrNavi.Arguments arguments);

    CompletableFuture<ResRequestRoutePlanOrNavi.Arguments> requestRequestRoutePlanOrNavi(ReqRequestRoutePlanOrNavi.Arguments arguments, Duration duration);

    CompletableFuture<ResRequestRoutePlanOrNavi> requestRequestRoutePlanOrNavi(ReqRequestRoutePlanOrNavi reqRequestRoutePlanOrNavi);

    CompletableFuture<ResRequestRoutePlanOrNavi> requestRequestRoutePlanOrNavi(ReqRequestRoutePlanOrNavi reqRequestRoutePlanOrNavi, Duration duration);

    CompletableFuture<ResResumeGuidance.Arguments> requestResumeGuidance(ReqResumeGuidance.Arguments arguments);

    CompletableFuture<ResResumeGuidance.Arguments> requestResumeGuidance(ReqResumeGuidance.Arguments arguments, Duration duration);

    CompletableFuture<ResResumeGuidance> requestResumeGuidance(ReqResumeGuidance reqResumeGuidance);

    CompletableFuture<ResResumeGuidance> requestResumeGuidance(ReqResumeGuidance reqResumeGuidance, Duration duration);

    CompletableFuture<ResRouteOptionView> requestRouteOptionView();

    CompletableFuture<ResRouteOptionView> requestRouteOptionView(ReqRouteOptionView reqRouteOptionView);

    CompletableFuture<ResRouteOptionView> requestRouteOptionView(ReqRouteOptionView reqRouteOptionView, Duration duration);

    CompletableFuture<ResRouteOptionView> requestRouteOptionView(Duration duration);

    CompletableFuture<ResSearchAround.Arguments> requestSearchAround(ReqSearchAround.Arguments arguments);

    CompletableFuture<ResSearchAround.Arguments> requestSearchAround(ReqSearchAround.Arguments arguments, Duration duration);

    CompletableFuture<ResSearchAround> requestSearchAround(ReqSearchAround reqSearchAround);

    CompletableFuture<ResSearchAround> requestSearchAround(ReqSearchAround reqSearchAround, Duration duration);

    CompletableFuture<ResSearchAroundByCatId.Arguments> requestSearchAroundByCatId(ReqSearchAroundByCatId.Arguments arguments);

    CompletableFuture<ResSearchAroundByCatId.Arguments> requestSearchAroundByCatId(ReqSearchAroundByCatId.Arguments arguments, Duration duration);

    CompletableFuture<ResSearchAroundByCatId> requestSearchAroundByCatId(ReqSearchAroundByCatId reqSearchAroundByCatId);

    CompletableFuture<ResSearchAroundByCatId> requestSearchAroundByCatId(ReqSearchAroundByCatId reqSearchAroundByCatId, Duration duration);

    CompletableFuture<ResSearchByKeyword.Arguments> requestSearchByKeyword(ReqSearchByKeyword.Arguments arguments);

    CompletableFuture<ResSearchByKeyword.Arguments> requestSearchByKeyword(ReqSearchByKeyword.Arguments arguments, Duration duration);

    CompletableFuture<ResSearchByKeyword> requestSearchByKeyword(ReqSearchByKeyword reqSearchByKeyword);

    CompletableFuture<ResSearchByKeyword> requestSearchByKeyword(ReqSearchByKeyword reqSearchByKeyword, Duration duration);

    CompletableFuture<ResSearchByKeywordWithLatLng.Arguments> requestSearchByKeywordWithLatLng(ReqSearchByKeywordWithLatLng.Arguments arguments);

    CompletableFuture<ResSearchByKeywordWithLatLng.Arguments> requestSearchByKeywordWithLatLng(ReqSearchByKeywordWithLatLng.Arguments arguments, Duration duration);

    CompletableFuture<ResSearchByKeywordWithLatLng> requestSearchByKeywordWithLatLng(ReqSearchByKeywordWithLatLng reqSearchByKeywordWithLatLng);

    CompletableFuture<ResSearchByKeywordWithLatLng> requestSearchByKeywordWithLatLng(ReqSearchByKeywordWithLatLng reqSearchByKeywordWithLatLng, Duration duration);

    CompletableFuture<ResSearchBySETELCategory.Arguments> requestSearchBySETELCategory(ReqSearchBySETELCategory.Arguments arguments);

    CompletableFuture<ResSearchBySETELCategory.Arguments> requestSearchBySETELCategory(ReqSearchBySETELCategory.Arguments arguments, Duration duration);

    CompletableFuture<ResSearchBySETELCategory> requestSearchBySETELCategory(ReqSearchBySETELCategory reqSearchBySETELCategory);

    CompletableFuture<ResSearchBySETELCategory> requestSearchBySETELCategory(ReqSearchBySETELCategory reqSearchBySETELCategory, Duration duration);

    CompletableFuture<ResSearchEVChargingStation.Arguments> requestSearchEVChargingStation(ReqSearchEVChargingStation.Arguments arguments);

    CompletableFuture<ResSearchEVChargingStation.Arguments> requestSearchEVChargingStation(ReqSearchEVChargingStation.Arguments arguments, Duration duration);

    CompletableFuture<ResSearchEVChargingStation> requestSearchEVChargingStation(ReqSearchEVChargingStation reqSearchEVChargingStation);

    CompletableFuture<ResSearchEVChargingStation> requestSearchEVChargingStation(ReqSearchEVChargingStation reqSearchEVChargingStation, Duration duration);

    CompletableFuture<ResSelectRoute.Arguments> requestSelectRoute(ReqSelectRoute.Arguments arguments);

    CompletableFuture<ResSelectRoute.Arguments> requestSelectRoute(ReqSelectRoute.Arguments arguments, Duration duration);

    CompletableFuture<ResSelectRoute> requestSelectRoute(ReqSelectRoute reqSelectRoute);

    CompletableFuture<ResSelectRoute> requestSelectRoute(ReqSelectRoute reqSelectRoute, Duration duration);

    CompletableFuture<ResSetMapViewMode.Arguments> requestSetMapViewMode(ReqSetMapViewMode.Arguments arguments);

    CompletableFuture<ResSetMapViewMode.Arguments> requestSetMapViewMode(ReqSetMapViewMode.Arguments arguments, Duration duration);

    CompletableFuture<ResSetMapViewMode> requestSetMapViewMode(ReqSetMapViewMode reqSetMapViewMode);

    CompletableFuture<ResSetMapViewMode> requestSetMapViewMode(ReqSetMapViewMode reqSetMapViewMode, Duration duration);

    CompletableFuture<ResSetNaviMuteMode.Arguments> requestSetNaviMuteMode(ReqSetNaviMuteMode.Arguments arguments);

    CompletableFuture<ResSetNaviMuteMode.Arguments> requestSetNaviMuteMode(ReqSetNaviMuteMode.Arguments arguments, Duration duration);

    CompletableFuture<ResSetNaviMuteMode> requestSetNaviMuteMode(ReqSetNaviMuteMode reqSetNaviMuteMode);

    CompletableFuture<ResSetNaviMuteMode> requestSetNaviMuteMode(ReqSetNaviMuteMode reqSetNaviMuteMode, Duration duration);

    CompletableFuture<ResSetRoutePrefer.Arguments> requestSetRoutePrefer(ReqSetRoutePrefer.Arguments arguments);

    CompletableFuture<ResSetRoutePrefer.Arguments> requestSetRoutePrefer(ReqSetRoutePrefer.Arguments arguments, Duration duration);

    CompletableFuture<ResSetRoutePrefer> requestSetRoutePrefer(ReqSetRoutePrefer reqSetRoutePrefer);

    CompletableFuture<ResSetRoutePrefer> requestSetRoutePrefer(ReqSetRoutePrefer reqSetRoutePrefer, Duration duration);

    CompletableFuture<ResShowMyLocation.Arguments> requestShowMyLocation(ReqShowMyLocation.Arguments arguments);

    CompletableFuture<ResShowMyLocation.Arguments> requestShowMyLocation(ReqShowMyLocation.Arguments arguments, Duration duration);

    CompletableFuture<ResShowMyLocation> requestShowMyLocation(ReqShowMyLocation reqShowMyLocation);

    CompletableFuture<ResShowMyLocation> requestShowMyLocation(ReqShowMyLocation reqShowMyLocation, Duration duration);

    CompletableFuture<ResSpecialPoiNavi.Arguments> requestSpecialPoiNavi(ReqSpecialPoiNavi.Arguments arguments);

    CompletableFuture<ResSpecialPoiNavi.Arguments> requestSpecialPoiNavi(ReqSpecialPoiNavi.Arguments arguments, Duration duration);

    CompletableFuture<ResSpecialPoiNavi> requestSpecialPoiNavi(ReqSpecialPoiNavi reqSpecialPoiNavi);

    CompletableFuture<ResSpecialPoiNavi> requestSpecialPoiNavi(ReqSpecialPoiNavi reqSpecialPoiNavi, Duration duration);

    CompletableFuture<ResSwitchTraffic.Arguments> requestSwitchTraffic(ReqSwitchTraffic.Arguments arguments);

    CompletableFuture<ResSwitchTraffic.Arguments> requestSwitchTraffic(ReqSwitchTraffic.Arguments arguments, Duration duration);

    CompletableFuture<ResSwitchTraffic> requestSwitchTraffic(ReqSwitchTraffic reqSwitchTraffic);

    CompletableFuture<ResSwitchTraffic> requestSwitchTraffic(ReqSwitchTraffic reqSwitchTraffic, Duration duration);

    CompletableFuture<ResVRMsgCommand.Arguments> requestVRMsgCommand(ReqVRMsgCommand.Arguments arguments);

    CompletableFuture<ResVRMsgCommand.Arguments> requestVRMsgCommand(ReqVRMsgCommand.Arguments arguments, Duration duration);

    CompletableFuture<ResVRMsgCommand> requestVRMsgCommand(ReqVRMsgCommand reqVRMsgCommand);

    CompletableFuture<ResVRMsgCommand> requestVRMsgCommand(ReqVRMsgCommand reqVRMsgCommand, Duration duration);
}
