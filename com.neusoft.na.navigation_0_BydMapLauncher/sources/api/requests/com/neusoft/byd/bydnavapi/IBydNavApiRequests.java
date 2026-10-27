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
import java9.util.concurrent.CompletableFuture;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes.dex */
public interface IBydNavApiRequests {
    CompletableFuture<Void> fireCheckDestinationHistoryEmpty();

    CompletableFuture<Void> fireCheckDestinationHistoryEmpty(ReqCheckDestinationHistoryEmpty reqCheckDestinationHistoryEmpty);

    CompletableFuture<Void> fireCheckDestinationHistoryEmpty(ReqCheckDestinationHistoryEmpty reqCheckDestinationHistoryEmpty, Duration duration);

    CompletableFuture<Void> fireCheckDestinationHistoryEmpty(Duration duration);

    CompletableFuture<Void> fireCheckFavoriteEmpty();

    CompletableFuture<Void> fireCheckFavoriteEmpty(ReqCheckFavoriteEmpty reqCheckFavoriteEmpty);

    CompletableFuture<Void> fireCheckFavoriteEmpty(ReqCheckFavoriteEmpty reqCheckFavoriteEmpty, Duration duration);

    CompletableFuture<Void> fireCheckFavoriteEmpty(Duration duration);

    CompletableFuture<Void> fireCheckFavoriteFull();

    CompletableFuture<Void> fireCheckFavoriteFull(ReqCheckFavoriteFull reqCheckFavoriteFull);

    CompletableFuture<Void> fireCheckFavoriteFull(ReqCheckFavoriteFull reqCheckFavoriteFull, Duration duration);

    CompletableFuture<Void> fireCheckFavoriteFull(Duration duration);

    CompletableFuture<Void> fireClusterViewModeChanged(ReqClusterViewModeChanged.Arguments arguments);

    CompletableFuture<Void> fireClusterViewModeChanged(ReqClusterViewModeChanged.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireClusterViewModeChanged(ReqClusterViewModeChanged reqClusterViewModeChanged);

    CompletableFuture<Void> fireClusterViewModeChanged(ReqClusterViewModeChanged reqClusterViewModeChanged, Duration duration);

    CompletableFuture<Void> fireGetAuthInfo();

    CompletableFuture<Void> fireGetAuthInfo(ReqGetAuthInfo reqGetAuthInfo);

    CompletableFuture<Void> fireGetAuthInfo(ReqGetAuthInfo reqGetAuthInfo, Duration duration);

    CompletableFuture<Void> fireGetAuthInfo(Duration duration);

    CompletableFuture<Void> fireGetClusterViewMode();

    CompletableFuture<Void> fireGetClusterViewMode(ReqGetClusterViewMode reqGetClusterViewMode);

    CompletableFuture<Void> fireGetClusterViewMode(ReqGetClusterViewMode reqGetClusterViewMode, Duration duration);

    CompletableFuture<Void> fireGetClusterViewMode(Duration duration);

    CompletableFuture<Void> fireGetCompany();

    CompletableFuture<Void> fireGetCompany(ReqGetCompany reqGetCompany);

    CompletableFuture<Void> fireGetCompany(ReqGetCompany reqGetCompany, Duration duration);

    CompletableFuture<Void> fireGetCompany(Duration duration);

    CompletableFuture<Void> fireGetCurrentPlace();

    CompletableFuture<Void> fireGetCurrentPlace(ReqGetCurrentPlace reqGetCurrentPlace);

    CompletableFuture<Void> fireGetCurrentPlace(ReqGetCurrentPlace reqGetCurrentPlace, Duration duration);

    CompletableFuture<Void> fireGetCurrentPlace(Duration duration);

    CompletableFuture<Void> fireGetDelPrivacyPolicyInfo();

    CompletableFuture<Void> fireGetDelPrivacyPolicyInfo(ReqGetDelPrivacyPolicyInfo reqGetDelPrivacyPolicyInfo);

    CompletableFuture<Void> fireGetDelPrivacyPolicyInfo(ReqGetDelPrivacyPolicyInfo reqGetDelPrivacyPolicyInfo, Duration duration);

    CompletableFuture<Void> fireGetDelPrivacyPolicyInfo(Duration duration);

    CompletableFuture<Void> fireGetFavoriteStatus();

    CompletableFuture<Void> fireGetFavoriteStatus(ReqGetFavoriteStatus reqGetFavoriteStatus);

    CompletableFuture<Void> fireGetFavoriteStatus(ReqGetFavoriteStatus reqGetFavoriteStatus, Duration duration);

    CompletableFuture<Void> fireGetFavoriteStatus(Duration duration);

    CompletableFuture<Void> fireGetGuidanceInfoUpdate();

    CompletableFuture<Void> fireGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate);

    CompletableFuture<Void> fireGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate, Duration duration);

    CompletableFuture<Void> fireGetGuidanceInfoUpdate(Duration duration);

    CompletableFuture<Void> fireGetGuidanceStatus();

    CompletableFuture<Void> fireGetGuidanceStatus(ReqGetGuidanceStatus reqGetGuidanceStatus);

    CompletableFuture<Void> fireGetGuidanceStatus(ReqGetGuidanceStatus reqGetGuidanceStatus, Duration duration);

    CompletableFuture<Void> fireGetGuidanceStatus(Duration duration);

    CompletableFuture<Void> fireGetHome();

    CompletableFuture<Void> fireGetHome(ReqGetHome reqGetHome);

    CompletableFuture<Void> fireGetHome(ReqGetHome reqGetHome, Duration duration);

    CompletableFuture<Void> fireGetHome(Duration duration);

    CompletableFuture<Void> fireGetIsNaviHMIFullScreenPage();

    CompletableFuture<Void> fireGetIsNaviHMIFullScreenPage(ReqGetIsNaviHMIFullScreenPage reqGetIsNaviHMIFullScreenPage);

    CompletableFuture<Void> fireGetIsNaviHMIFullScreenPage(ReqGetIsNaviHMIFullScreenPage reqGetIsNaviHMIFullScreenPage, Duration duration);

    CompletableFuture<Void> fireGetIsNaviHMIFullScreenPage(Duration duration);

    CompletableFuture<Void> fireGetNaviMaxViaCount();

    CompletableFuture<Void> fireGetNaviMaxViaCount(ReqGetNaviMaxViaCount reqGetNaviMaxViaCount);

    CompletableFuture<Void> fireGetNaviMaxViaCount(ReqGetNaviMaxViaCount reqGetNaviMaxViaCount, Duration duration);

    CompletableFuture<Void> fireGetNaviMaxViaCount(Duration duration);

    CompletableFuture<Void> fireGetPoiDetailStatus();

    CompletableFuture<Void> fireGetPoiDetailStatus(ReqGetPoiDetailStatus reqGetPoiDetailStatus);

    CompletableFuture<Void> fireGetPoiDetailStatus(ReqGetPoiDetailStatus reqGetPoiDetailStatus, Duration duration);

    CompletableFuture<Void> fireGetPoiDetailStatus(Duration duration);

    CompletableFuture<Void> fireGetReportLocationInfo();

    CompletableFuture<Void> fireGetReportLocationInfo(ReqGetReportLocationInfo reqGetReportLocationInfo);

    CompletableFuture<Void> fireGetReportLocationInfo(ReqGetReportLocationInfo reqGetReportLocationInfo, Duration duration);

    CompletableFuture<Void> fireGetReportLocationInfo(Duration duration);

    CompletableFuture<Void> fireGetReqGPSInfo();

    CompletableFuture<Void> fireGetReqGPSInfo(ReqGetReqGPSInfo reqGetReqGPSInfo);

    CompletableFuture<Void> fireGetReqGPSInfo(ReqGetReqGPSInfo reqGetReqGPSInfo, Duration duration);

    CompletableFuture<Void> fireGetReqGPSInfo(Duration duration);

    CompletableFuture<Void> fireGetRevokePrivacyPolicy();

    CompletableFuture<Void> fireGetRevokePrivacyPolicy(ReqGetRevokePrivacyPolicy reqGetRevokePrivacyPolicy);

    CompletableFuture<Void> fireGetRevokePrivacyPolicy(ReqGetRevokePrivacyPolicy reqGetRevokePrivacyPolicy, Duration duration);

    CompletableFuture<Void> fireGetRevokePrivacyPolicy(Duration duration);

    CompletableFuture<Void> fireGetSupportedRouteOptions();

    CompletableFuture<Void> fireGetSupportedRouteOptions(ReqGetSupportedRouteOptions reqGetSupportedRouteOptions);

    CompletableFuture<Void> fireGetSupportedRouteOptions(ReqGetSupportedRouteOptions reqGetSupportedRouteOptions, Duration duration);

    CompletableFuture<Void> fireGetSupportedRouteOptions(Duration duration);

    CompletableFuture<Void> fireGetSwitchDesktopMode();

    CompletableFuture<Void> fireGetSwitchDesktopMode(ReqGetSwitchDesktopMode reqGetSwitchDesktopMode);

    CompletableFuture<Void> fireGetSwitchDesktopMode(ReqGetSwitchDesktopMode reqGetSwitchDesktopMode, Duration duration);

    CompletableFuture<Void> fireGetSwitchDesktopMode(Duration duration);

    CompletableFuture<Void> fireGetUnfinishedRoute();

    CompletableFuture<Void> fireGetUnfinishedRoute(ReqGetUnfinishedRoute reqGetUnfinishedRoute);

    CompletableFuture<Void> fireGetUnfinishedRoute(ReqGetUnfinishedRoute reqGetUnfinishedRoute, Duration duration);

    CompletableFuture<Void> fireGetUnfinishedRoute(Duration duration);

    CompletableFuture<Void> fireGetUpdatePrivacyPolicySyncStatus();

    CompletableFuture<Void> fireGetUpdatePrivacyPolicySyncStatus(ReqGetUpdatePrivacyPolicySyncStatus reqGetUpdatePrivacyPolicySyncStatus);

    CompletableFuture<Void> fireGetUpdatePrivacyPolicySyncStatus(ReqGetUpdatePrivacyPolicySyncStatus reqGetUpdatePrivacyPolicySyncStatus, Duration duration);

    CompletableFuture<Void> fireGetUpdatePrivacyPolicySyncStatus(Duration duration);

    CompletableFuture<Void> fireGetVerifyInfo();

    CompletableFuture<Void> fireGetVerifyInfo(ReqGetVerifyInfo reqGetVerifyInfo);

    CompletableFuture<Void> fireGetVerifyInfo(ReqGetVerifyInfo reqGetVerifyInfo, Duration duration);

    CompletableFuture<Void> fireGetVerifyInfo(Duration duration);

    CompletableFuture<Void> fireHidePoiIconsFromVR();

    CompletableFuture<Void> fireHidePoiIconsFromVR(ReqHidePoiIconsFromVR reqHidePoiIconsFromVR);

    CompletableFuture<Void> fireHidePoiIconsFromVR(ReqHidePoiIconsFromVR reqHidePoiIconsFromVR, Duration duration);

    CompletableFuture<Void> fireHidePoiIconsFromVR(Duration duration);

    CompletableFuture<Void> fireOnAuthInfo(ReqOnAuthInfo.Arguments arguments);

    CompletableFuture<Void> fireOnAuthInfo(ReqOnAuthInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnAuthInfo(ReqOnAuthInfo reqOnAuthInfo);

    CompletableFuture<Void> fireOnAuthInfo(ReqOnAuthInfo reqOnAuthInfo, Duration duration);

    CompletableFuture<Void> fireOnDelPrivacyPolicyInfo(ReqOnDelPrivacyPolicyInfo.Arguments arguments);

    CompletableFuture<Void> fireOnDelPrivacyPolicyInfo(ReqOnDelPrivacyPolicyInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnDelPrivacyPolicyInfo(ReqOnDelPrivacyPolicyInfo reqOnDelPrivacyPolicyInfo);

    CompletableFuture<Void> fireOnDelPrivacyPolicyInfo(ReqOnDelPrivacyPolicyInfo reqOnDelPrivacyPolicyInfo, Duration duration);

    CompletableFuture<Void> fireOnGPSInfo(ReqOnGPSInfo.Arguments arguments);

    CompletableFuture<Void> fireOnGPSInfo(ReqOnGPSInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnGPSInfo(ReqOnGPSInfo reqOnGPSInfo);

    CompletableFuture<Void> fireOnGPSInfo(ReqOnGPSInfo reqOnGPSInfo, Duration duration);

    CompletableFuture<Void> fireOnPrivacyPolicySyncStatus(ReqOnPrivacyPolicySyncStatus.Arguments arguments);

    CompletableFuture<Void> fireOnPrivacyPolicySyncStatus(ReqOnPrivacyPolicySyncStatus.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnPrivacyPolicySyncStatus(ReqOnPrivacyPolicySyncStatus reqOnPrivacyPolicySyncStatus);

    CompletableFuture<Void> fireOnPrivacyPolicySyncStatus(ReqOnPrivacyPolicySyncStatus reqOnPrivacyPolicySyncStatus, Duration duration);

    CompletableFuture<Void> fireOnRevokePrivacyPolicy(ReqOnRevokePrivacyPolicy.Arguments arguments);

    CompletableFuture<Void> fireOnRevokePrivacyPolicy(ReqOnRevokePrivacyPolicy.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnRevokePrivacyPolicy(ReqOnRevokePrivacyPolicy reqOnRevokePrivacyPolicy);

    CompletableFuture<Void> fireOnRevokePrivacyPolicy(ReqOnRevokePrivacyPolicy reqOnRevokePrivacyPolicy, Duration duration);

    CompletableFuture<Void> fireOnVerifyInfo(ReqOnVerifyInfo.Arguments arguments);

    CompletableFuture<Void> fireOnVerifyInfo(ReqOnVerifyInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnVerifyInfo(ReqOnVerifyInfo reqOnVerifyInfo);

    CompletableFuture<Void> fireOnVerifyInfo(ReqOnVerifyInfo reqOnVerifyInfo, Duration duration);

    CompletableFuture<Void> firePowerSavingModeChanged(ReqPowerSavingModeChanged.Arguments arguments);

    CompletableFuture<Void> firePowerSavingModeChanged(ReqPowerSavingModeChanged.Arguments arguments, Duration duration);

    CompletableFuture<Void> firePowerSavingModeChanged(ReqPowerSavingModeChanged reqPowerSavingModeChanged);

    CompletableFuture<Void> firePowerSavingModeChanged(ReqPowerSavingModeChanged reqPowerSavingModeChanged, Duration duration);

    CompletableFuture<Void> fireReqAddViaPosition(ReqReqAddViaPosition.Arguments arguments);

    CompletableFuture<Void> fireReqAddViaPosition(ReqReqAddViaPosition.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqAddViaPosition(ReqReqAddViaPosition reqReqAddViaPosition);

    CompletableFuture<Void> fireReqAddViaPosition(ReqReqAddViaPosition reqReqAddViaPosition, Duration duration);

    CompletableFuture<Void> fireReqAppendVia(ReqReqAppendVia.Arguments arguments);

    CompletableFuture<Void> fireReqAppendVia(ReqReqAppendVia.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqAppendVia(ReqReqAppendVia reqReqAppendVia);

    CompletableFuture<Void> fireReqAppendVia(ReqReqAppendVia reqReqAppendVia, Duration duration);

    CompletableFuture<Void> fireReqClosePoiDetail();

    CompletableFuture<Void> fireReqClosePoiDetail(ReqReqClosePoiDetail reqReqClosePoiDetail);

    CompletableFuture<Void> fireReqClosePoiDetail(ReqReqClosePoiDetail reqReqClosePoiDetail, Duration duration);

    CompletableFuture<Void> fireReqClosePoiDetail(Duration duration);

    CompletableFuture<Void> fireReqCloseRouteOption();

    CompletableFuture<Void> fireReqCloseRouteOption(ReqReqCloseRouteOption reqReqCloseRouteOption);

    CompletableFuture<Void> fireReqCloseRouteOption(ReqReqCloseRouteOption reqReqCloseRouteOption, Duration duration);

    CompletableFuture<Void> fireReqCloseRouteOption(Duration duration);

    CompletableFuture<Void> fireReqGuideStart(ReqReqGuideStart.Arguments arguments);

    CompletableFuture<Void> fireReqGuideStart(ReqReqGuideStart.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqGuideStart(ReqReqGuideStart reqReqGuideStart);

    CompletableFuture<Void> fireReqGuideStart(ReqReqGuideStart reqReqGuideStart, Duration duration);

    CompletableFuture<Void> fireReqModifyDestination(ReqReqModifyDestination.Arguments arguments);

    CompletableFuture<Void> fireReqModifyDestination(ReqReqModifyDestination.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqModifyDestination(ReqReqModifyDestination reqReqModifyDestination);

    CompletableFuture<Void> fireReqModifyDestination(ReqReqModifyDestination reqReqModifyDestination, Duration duration);

    CompletableFuture<Void> fireReqNaviAddFavorite();

    CompletableFuture<Void> fireReqNaviAddFavorite(ReqReqNaviAddFavorite reqReqNaviAddFavorite);

    CompletableFuture<Void> fireReqNaviAddFavorite(ReqReqNaviAddFavorite reqReqNaviAddFavorite, Duration duration);

    CompletableFuture<Void> fireReqNaviAddFavorite(Duration duration);

    CompletableFuture<Void> fireReqNaviAlongTheWaySearch(ReqReqNaviAlongTheWaySearch.Arguments arguments);

    CompletableFuture<Void> fireReqNaviAlongTheWaySearch(ReqReqNaviAlongTheWaySearch.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqNaviAlongTheWaySearch(ReqReqNaviAlongTheWaySearch reqReqNaviAlongTheWaySearch);

    CompletableFuture<Void> fireReqNaviAlongTheWaySearch(ReqReqNaviAlongTheWaySearch reqReqNaviAlongTheWaySearch, Duration duration);

    CompletableFuture<Void> fireReqNaviGetRouteOption();

    CompletableFuture<Void> fireReqNaviGetRouteOption(ReqReqNaviGetRouteOption reqReqNaviGetRouteOption);

    CompletableFuture<Void> fireReqNaviGetRouteOption(ReqReqNaviGetRouteOption reqReqNaviGetRouteOption, Duration duration);

    CompletableFuture<Void> fireReqNaviGetRouteOption(Duration duration);

    CompletableFuture<Void> fireReqNaviInfo();

    CompletableFuture<Void> fireReqNaviInfo(ReqReqNaviInfo reqReqNaviInfo);

    CompletableFuture<Void> fireReqNaviInfo(ReqReqNaviInfo reqReqNaviInfo, Duration duration);

    CompletableFuture<Void> fireReqNaviInfo(Duration duration);

    CompletableFuture<Void> fireReqNaviNextViaTimeDistance();

    CompletableFuture<Void> fireReqNaviNextViaTimeDistance(ReqReqNaviNextViaTimeDistance reqReqNaviNextViaTimeDistance);

    CompletableFuture<Void> fireReqNaviNextViaTimeDistance(ReqReqNaviNextViaTimeDistance reqReqNaviNextViaTimeDistance, Duration duration);

    CompletableFuture<Void> fireReqNaviNextViaTimeDistance(Duration duration);

    CompletableFuture<Void> fireReqNaviRouteFullView(ReqReqNaviRouteFullView.Arguments arguments);

    CompletableFuture<Void> fireReqNaviRouteFullView(ReqReqNaviRouteFullView.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqNaviRouteFullView(ReqReqNaviRouteFullView reqReqNaviRouteFullView);

    CompletableFuture<Void> fireReqNaviRouteFullView(ReqReqNaviRouteFullView reqReqNaviRouteFullView, Duration duration);

    CompletableFuture<Void> fireReqNaviSetRouteOption(ReqReqNaviSetRouteOption.Arguments arguments);

    CompletableFuture<Void> fireReqNaviSetRouteOption(ReqReqNaviSetRouteOption.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqNaviSetRouteOption(ReqReqNaviSetRouteOption reqReqNaviSetRouteOption);

    CompletableFuture<Void> fireReqNaviSetRouteOption(ReqReqNaviSetRouteOption reqReqNaviSetRouteOption, Duration duration);

    CompletableFuture<Void> fireReqNaviViaPosition();

    CompletableFuture<Void> fireReqNaviViaPosition(ReqReqNaviViaPosition reqReqNaviViaPosition);

    CompletableFuture<Void> fireReqNaviViaPosition(ReqReqNaviViaPosition reqReqNaviViaPosition, Duration duration);

    CompletableFuture<Void> fireReqNaviViaPosition(Duration duration);

    CompletableFuture<Void> fireReqOpenHistoryDestination();

    CompletableFuture<Void> fireReqOpenHistoryDestination(ReqReqOpenHistoryDestination reqReqOpenHistoryDestination);

    CompletableFuture<Void> fireReqOpenHistoryDestination(ReqReqOpenHistoryDestination reqReqOpenHistoryDestination, Duration duration);

    CompletableFuture<Void> fireReqOpenHistoryDestination(Duration duration);

    CompletableFuture<Void> fireReqOpenPoiDetail(ReqReqOpenPoiDetail.Arguments arguments);

    CompletableFuture<Void> fireReqOpenPoiDetail(ReqReqOpenPoiDetail.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqOpenPoiDetail(ReqReqOpenPoiDetail reqReqOpenPoiDetail);

    CompletableFuture<Void> fireReqOpenPoiDetail(ReqReqOpenPoiDetail reqReqOpenPoiDetail, Duration duration);

    CompletableFuture<Void> fireReqOpenRouteOption();

    CompletableFuture<Void> fireReqOpenRouteOption(ReqReqOpenRouteOption reqReqOpenRouteOption);

    CompletableFuture<Void> fireReqOpenRouteOption(ReqReqOpenRouteOption reqReqOpenRouteOption, Duration duration);

    CompletableFuture<Void> fireReqOpenRouteOption(Duration duration);

    CompletableFuture<Void> fireReqPOISearchByCategories(ReqReqPOISearchByCategories.Arguments arguments);

    CompletableFuture<Void> fireReqPOISearchByCategories(ReqReqPOISearchByCategories.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqPOISearchByCategories(ReqReqPOISearchByCategories reqReqPOISearchByCategories);

    CompletableFuture<Void> fireReqPOISearchByCategories(ReqReqPOISearchByCategories reqReqPOISearchByCategories, Duration duration);

    CompletableFuture<Void> fireReqPOISearchByKeyword(ReqReqPOISearchByKeyword.Arguments arguments);

    CompletableFuture<Void> fireReqPOISearchByKeyword(ReqReqPOISearchByKeyword.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqPOISearchByKeyword(ReqReqPOISearchByKeyword reqReqPOISearchByKeyword);

    CompletableFuture<Void> fireReqPOISearchByKeyword(ReqReqPOISearchByKeyword reqReqPOISearchByKeyword, Duration duration);

    CompletableFuture<Void> fireReqRoutePlanning(ReqReqRoutePlanning.Arguments arguments);

    CompletableFuture<Void> fireReqRoutePlanning(ReqReqRoutePlanning.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqRoutePlanning(ReqReqRoutePlanning reqReqRoutePlanning);

    CompletableFuture<Void> fireReqRoutePlanning(ReqReqRoutePlanning reqReqRoutePlanning, Duration duration);

    CompletableFuture<Void> fireReqSetAddress(ReqReqSetAddress.Arguments arguments);

    CompletableFuture<Void> fireReqSetAddress(ReqReqSetAddress.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqSetAddress(ReqReqSetAddress reqReqSetAddress);

    CompletableFuture<Void> fireReqSetAddress(ReqReqSetAddress reqReqSetAddress, Duration duration);

    CompletableFuture<Void> fireReqSmartNaviStart(ReqReqSmartNaviStart.Arguments arguments);

    CompletableFuture<Void> fireReqSmartNaviStart(ReqReqSmartNaviStart.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqSmartNaviStart(ReqReqSmartNaviStart reqReqSmartNaviStart);

    CompletableFuture<Void> fireReqSmartNaviStart(ReqReqSmartNaviStart reqReqSmartNaviStart, Duration duration);

    CompletableFuture<Void> fireReqSmartRoutePlanning(ReqReqSmartRoutePlanning.Arguments arguments);

    CompletableFuture<Void> fireReqSmartRoutePlanning(ReqReqSmartRoutePlanning.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqSmartRoutePlanning(ReqReqSmartRoutePlanning reqReqSmartRoutePlanning);

    CompletableFuture<Void> fireReqSmartRoutePlanning(ReqReqSmartRoutePlanning reqReqSmartRoutePlanning, Duration duration);

    CompletableFuture<Void> fireReqSwitchFavorite(ReqReqSwitchFavorite.Arguments arguments);

    CompletableFuture<Void> fireReqSwitchFavorite(ReqReqSwitchFavorite.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireReqSwitchFavorite(ReqReqSwitchFavorite reqReqSwitchFavorite);

    CompletableFuture<Void> fireReqSwitchFavorite(ReqReqSwitchFavorite reqReqSwitchFavorite, Duration duration);

    CompletableFuture<Void> fireRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany.Arguments arguments);

    CompletableFuture<Void> fireRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany reqRequestGoHomeOrCompany);

    CompletableFuture<Void> fireRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany reqRequestGoHomeOrCompany, Duration duration);

    CompletableFuture<Void> fireRequestShowHomeOrCompanySetPage(ReqRequestShowHomeOrCompanySetPage.Arguments arguments);

    CompletableFuture<Void> fireRequestShowHomeOrCompanySetPage(ReqRequestShowHomeOrCompanySetPage.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestShowHomeOrCompanySetPage(ReqRequestShowHomeOrCompanySetPage reqRequestShowHomeOrCompanySetPage);

    CompletableFuture<Void> fireRequestShowHomeOrCompanySetPage(ReqRequestShowHomeOrCompanySetPage reqRequestShowHomeOrCompanySetPage, Duration duration);

    CompletableFuture<Void> fireRequestShowSearchAlongRoutePage();

    CompletableFuture<Void> fireRequestShowSearchAlongRoutePage(ReqRequestShowSearchAlongRoutePage reqRequestShowSearchAlongRoutePage);

    CompletableFuture<Void> fireRequestShowSearchAlongRoutePage(ReqRequestShowSearchAlongRoutePage reqRequestShowSearchAlongRoutePage, Duration duration);

    CompletableFuture<Void> fireRequestShowSearchAlongRoutePage(Duration duration);

    CompletableFuture<Void> fireRequestShowSearchResultPage();

    CompletableFuture<Void> fireRequestShowSearchResultPage(ReqRequestShowSearchResultPage reqRequestShowSearchResultPage);

    CompletableFuture<Void> fireRequestShowSearchResultPage(ReqRequestShowSearchResultPage reqRequestShowSearchResultPage, Duration duration);

    CompletableFuture<Void> fireRequestShowSearchResultPage(Duration duration);

    CompletableFuture<Void> fireResumeNavigation();

    CompletableFuture<Void> fireResumeNavigation(ReqResumeNavigation reqResumeNavigation);

    CompletableFuture<Void> fireResumeNavigation(ReqResumeNavigation reqResumeNavigation, Duration duration);

    CompletableFuture<Void> fireResumeNavigation(Duration duration);

    CompletableFuture<Void> fireSetClusterMapFocus(ReqSetClusterMapFocus.Arguments arguments);

    CompletableFuture<Void> fireSetClusterMapFocus(ReqSetClusterMapFocus.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetClusterMapFocus(ReqSetClusterMapFocus reqSetClusterMapFocus);

    CompletableFuture<Void> fireSetClusterMapFocus(ReqSetClusterMapFocus reqSetClusterMapFocus, Duration duration);

    CompletableFuture<Void> fireSetDesktopMode(ReqSetDesktopMode.Arguments arguments);

    CompletableFuture<Void> fireSetDesktopMode(ReqSetDesktopMode.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetDesktopMode(ReqSetDesktopMode reqSetDesktopMode);

    CompletableFuture<Void> fireSetDesktopMode(ReqSetDesktopMode reqSetDesktopMode, Duration duration);

    CompletableFuture<Void> fireSetMobileEquipmentID(ReqSetMobileEquipmentID.Arguments arguments);

    CompletableFuture<Void> fireSetMobileEquipmentID(ReqSetMobileEquipmentID.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetMobileEquipmentID(ReqSetMobileEquipmentID reqSetMobileEquipmentID);

    CompletableFuture<Void> fireSetMobileEquipmentID(ReqSetMobileEquipmentID reqSetMobileEquipmentID, Duration duration);

    CompletableFuture<Void> fireSetPlatformVersion(ReqSetPlatformVersion.Arguments arguments);

    CompletableFuture<Void> fireSetPlatformVersion(ReqSetPlatformVersion.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetPlatformVersion(ReqSetPlatformVersion reqSetPlatformVersion);

    CompletableFuture<Void> fireSetPlatformVersion(ReqSetPlatformVersion reqSetPlatformVersion, Duration duration);

    CompletableFuture<Void> fireSetPrivacyPolicyFullPath(ReqSetPrivacyPolicyFullPath.Arguments arguments);

    CompletableFuture<Void> fireSetPrivacyPolicyFullPath(ReqSetPrivacyPolicyFullPath.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetPrivacyPolicyFullPath(ReqSetPrivacyPolicyFullPath reqSetPrivacyPolicyFullPath);

    CompletableFuture<Void> fireSetPrivacyPolicyFullPath(ReqSetPrivacyPolicyFullPath reqSetPrivacyPolicyFullPath, Duration duration);

    CompletableFuture<Void> fireShowPoiIconsFromVR(ReqShowPoiIconsFromVR.Arguments arguments);

    CompletableFuture<Void> fireShowPoiIconsFromVR(ReqShowPoiIconsFromVR.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireShowPoiIconsFromVR(ReqShowPoiIconsFromVR reqShowPoiIconsFromVR);

    CompletableFuture<Void> fireShowPoiIconsFromVR(ReqShowPoiIconsFromVR reqShowPoiIconsFromVR, Duration duration);

    CompletableFuture<ResCheckDestinationHistoryEmpty> requestCheckDestinationHistoryEmpty();

    CompletableFuture<ResCheckDestinationHistoryEmpty> requestCheckDestinationHistoryEmpty(ReqCheckDestinationHistoryEmpty reqCheckDestinationHistoryEmpty);

    CompletableFuture<ResCheckDestinationHistoryEmpty> requestCheckDestinationHistoryEmpty(ReqCheckDestinationHistoryEmpty reqCheckDestinationHistoryEmpty, Duration duration);

    CompletableFuture<ResCheckDestinationHistoryEmpty> requestCheckDestinationHistoryEmpty(Duration duration);

    CompletableFuture<ResCheckFavoriteEmpty> requestCheckFavoriteEmpty();

    CompletableFuture<ResCheckFavoriteEmpty> requestCheckFavoriteEmpty(ReqCheckFavoriteEmpty reqCheckFavoriteEmpty);

    CompletableFuture<ResCheckFavoriteEmpty> requestCheckFavoriteEmpty(ReqCheckFavoriteEmpty reqCheckFavoriteEmpty, Duration duration);

    CompletableFuture<ResCheckFavoriteEmpty> requestCheckFavoriteEmpty(Duration duration);

    CompletableFuture<ResCheckFavoriteFull> requestCheckFavoriteFull();

    CompletableFuture<ResCheckFavoriteFull> requestCheckFavoriteFull(ReqCheckFavoriteFull reqCheckFavoriteFull);

    CompletableFuture<ResCheckFavoriteFull> requestCheckFavoriteFull(ReqCheckFavoriteFull reqCheckFavoriteFull, Duration duration);

    CompletableFuture<ResCheckFavoriteFull> requestCheckFavoriteFull(Duration duration);

    CompletableFuture<ResGetAuthInfo> requestGetAuthInfo();

    CompletableFuture<ResGetAuthInfo> requestGetAuthInfo(ReqGetAuthInfo reqGetAuthInfo);

    CompletableFuture<ResGetAuthInfo> requestGetAuthInfo(ReqGetAuthInfo reqGetAuthInfo, Duration duration);

    CompletableFuture<ResGetAuthInfo> requestGetAuthInfo(Duration duration);

    CompletableFuture<ResGetClusterViewMode> requestGetClusterViewMode();

    CompletableFuture<ResGetClusterViewMode> requestGetClusterViewMode(ReqGetClusterViewMode reqGetClusterViewMode);

    CompletableFuture<ResGetClusterViewMode> requestGetClusterViewMode(ReqGetClusterViewMode reqGetClusterViewMode, Duration duration);

    CompletableFuture<ResGetClusterViewMode> requestGetClusterViewMode(Duration duration);

    CompletableFuture<ResGetCompany> requestGetCompany();

    CompletableFuture<ResGetCompany> requestGetCompany(ReqGetCompany reqGetCompany);

    CompletableFuture<ResGetCompany> requestGetCompany(ReqGetCompany reqGetCompany, Duration duration);

    CompletableFuture<ResGetCompany> requestGetCompany(Duration duration);

    CompletableFuture<ResGetCurrentPlace> requestGetCurrentPlace();

    CompletableFuture<ResGetCurrentPlace> requestGetCurrentPlace(ReqGetCurrentPlace reqGetCurrentPlace);

    CompletableFuture<ResGetCurrentPlace> requestGetCurrentPlace(ReqGetCurrentPlace reqGetCurrentPlace, Duration duration);

    CompletableFuture<ResGetCurrentPlace> requestGetCurrentPlace(Duration duration);

    CompletableFuture<ResGetDelPrivacyPolicyInfo> requestGetDelPrivacyPolicyInfo();

    CompletableFuture<ResGetDelPrivacyPolicyInfo> requestGetDelPrivacyPolicyInfo(ReqGetDelPrivacyPolicyInfo reqGetDelPrivacyPolicyInfo);

    CompletableFuture<ResGetDelPrivacyPolicyInfo> requestGetDelPrivacyPolicyInfo(ReqGetDelPrivacyPolicyInfo reqGetDelPrivacyPolicyInfo, Duration duration);

    CompletableFuture<ResGetDelPrivacyPolicyInfo> requestGetDelPrivacyPolicyInfo(Duration duration);

    CompletableFuture<ResGetFavoriteStatus> requestGetFavoriteStatus();

    CompletableFuture<ResGetFavoriteStatus> requestGetFavoriteStatus(ReqGetFavoriteStatus reqGetFavoriteStatus);

    CompletableFuture<ResGetFavoriteStatus> requestGetFavoriteStatus(ReqGetFavoriteStatus reqGetFavoriteStatus, Duration duration);

    CompletableFuture<ResGetFavoriteStatus> requestGetFavoriteStatus(Duration duration);

    CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate();

    CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate);

    CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate(ReqGetGuidanceInfoUpdate reqGetGuidanceInfoUpdate, Duration duration);

    CompletableFuture<ResGetGuidanceInfoUpdate> requestGetGuidanceInfoUpdate(Duration duration);

    CompletableFuture<ResGetGuidanceStatus> requestGetGuidanceStatus();

    CompletableFuture<ResGetGuidanceStatus> requestGetGuidanceStatus(ReqGetGuidanceStatus reqGetGuidanceStatus);

    CompletableFuture<ResGetGuidanceStatus> requestGetGuidanceStatus(ReqGetGuidanceStatus reqGetGuidanceStatus, Duration duration);

    CompletableFuture<ResGetGuidanceStatus> requestGetGuidanceStatus(Duration duration);

    CompletableFuture<ResGetHome> requestGetHome();

    CompletableFuture<ResGetHome> requestGetHome(ReqGetHome reqGetHome);

    CompletableFuture<ResGetHome> requestGetHome(ReqGetHome reqGetHome, Duration duration);

    CompletableFuture<ResGetHome> requestGetHome(Duration duration);

    CompletableFuture<ResGetIsNaviHMIFullScreenPage> requestGetIsNaviHMIFullScreenPage();

    CompletableFuture<ResGetIsNaviHMIFullScreenPage> requestGetIsNaviHMIFullScreenPage(ReqGetIsNaviHMIFullScreenPage reqGetIsNaviHMIFullScreenPage);

    CompletableFuture<ResGetIsNaviHMIFullScreenPage> requestGetIsNaviHMIFullScreenPage(ReqGetIsNaviHMIFullScreenPage reqGetIsNaviHMIFullScreenPage, Duration duration);

    CompletableFuture<ResGetIsNaviHMIFullScreenPage> requestGetIsNaviHMIFullScreenPage(Duration duration);

    CompletableFuture<ResGetNaviMaxViaCount> requestGetNaviMaxViaCount();

    CompletableFuture<ResGetNaviMaxViaCount> requestGetNaviMaxViaCount(ReqGetNaviMaxViaCount reqGetNaviMaxViaCount);

    CompletableFuture<ResGetNaviMaxViaCount> requestGetNaviMaxViaCount(ReqGetNaviMaxViaCount reqGetNaviMaxViaCount, Duration duration);

    CompletableFuture<ResGetNaviMaxViaCount> requestGetNaviMaxViaCount(Duration duration);

    CompletableFuture<ResGetPoiDetailStatus> requestGetPoiDetailStatus();

    CompletableFuture<ResGetPoiDetailStatus> requestGetPoiDetailStatus(ReqGetPoiDetailStatus reqGetPoiDetailStatus);

    CompletableFuture<ResGetPoiDetailStatus> requestGetPoiDetailStatus(ReqGetPoiDetailStatus reqGetPoiDetailStatus, Duration duration);

    CompletableFuture<ResGetPoiDetailStatus> requestGetPoiDetailStatus(Duration duration);

    CompletableFuture<ResGetReportLocationInfo> requestGetReportLocationInfo();

    CompletableFuture<ResGetReportLocationInfo> requestGetReportLocationInfo(ReqGetReportLocationInfo reqGetReportLocationInfo);

    CompletableFuture<ResGetReportLocationInfo> requestGetReportLocationInfo(ReqGetReportLocationInfo reqGetReportLocationInfo, Duration duration);

    CompletableFuture<ResGetReportLocationInfo> requestGetReportLocationInfo(Duration duration);

    CompletableFuture<ResGetReqGPSInfo> requestGetReqGPSInfo();

    CompletableFuture<ResGetReqGPSInfo> requestGetReqGPSInfo(ReqGetReqGPSInfo reqGetReqGPSInfo);

    CompletableFuture<ResGetReqGPSInfo> requestGetReqGPSInfo(ReqGetReqGPSInfo reqGetReqGPSInfo, Duration duration);

    CompletableFuture<ResGetReqGPSInfo> requestGetReqGPSInfo(Duration duration);

    CompletableFuture<ResGetRevokePrivacyPolicy> requestGetRevokePrivacyPolicy();

    CompletableFuture<ResGetRevokePrivacyPolicy> requestGetRevokePrivacyPolicy(ReqGetRevokePrivacyPolicy reqGetRevokePrivacyPolicy);

    CompletableFuture<ResGetRevokePrivacyPolicy> requestGetRevokePrivacyPolicy(ReqGetRevokePrivacyPolicy reqGetRevokePrivacyPolicy, Duration duration);

    CompletableFuture<ResGetRevokePrivacyPolicy> requestGetRevokePrivacyPolicy(Duration duration);

    CompletableFuture<ResGetSupportedRouteOptions> requestGetSupportedRouteOptions();

    CompletableFuture<ResGetSupportedRouteOptions> requestGetSupportedRouteOptions(ReqGetSupportedRouteOptions reqGetSupportedRouteOptions);

    CompletableFuture<ResGetSupportedRouteOptions> requestGetSupportedRouteOptions(ReqGetSupportedRouteOptions reqGetSupportedRouteOptions, Duration duration);

    CompletableFuture<ResGetSupportedRouteOptions> requestGetSupportedRouteOptions(Duration duration);

    CompletableFuture<ResGetSwitchDesktopMode> requestGetSwitchDesktopMode();

    CompletableFuture<ResGetSwitchDesktopMode> requestGetSwitchDesktopMode(ReqGetSwitchDesktopMode reqGetSwitchDesktopMode);

    CompletableFuture<ResGetSwitchDesktopMode> requestGetSwitchDesktopMode(ReqGetSwitchDesktopMode reqGetSwitchDesktopMode, Duration duration);

    CompletableFuture<ResGetSwitchDesktopMode> requestGetSwitchDesktopMode(Duration duration);

    CompletableFuture<ResGetUnfinishedRoute> requestGetUnfinishedRoute();

    CompletableFuture<ResGetUnfinishedRoute> requestGetUnfinishedRoute(ReqGetUnfinishedRoute reqGetUnfinishedRoute);

    CompletableFuture<ResGetUnfinishedRoute> requestGetUnfinishedRoute(ReqGetUnfinishedRoute reqGetUnfinishedRoute, Duration duration);

    CompletableFuture<ResGetUnfinishedRoute> requestGetUnfinishedRoute(Duration duration);

    CompletableFuture<ResGetUpdatePrivacyPolicySyncStatus> requestGetUpdatePrivacyPolicySyncStatus();

    CompletableFuture<ResGetUpdatePrivacyPolicySyncStatus> requestGetUpdatePrivacyPolicySyncStatus(ReqGetUpdatePrivacyPolicySyncStatus reqGetUpdatePrivacyPolicySyncStatus);

    CompletableFuture<ResGetUpdatePrivacyPolicySyncStatus> requestGetUpdatePrivacyPolicySyncStatus(ReqGetUpdatePrivacyPolicySyncStatus reqGetUpdatePrivacyPolicySyncStatus, Duration duration);

    CompletableFuture<ResGetUpdatePrivacyPolicySyncStatus> requestGetUpdatePrivacyPolicySyncStatus(Duration duration);

    CompletableFuture<ResGetVerifyInfo> requestGetVerifyInfo();

    CompletableFuture<ResGetVerifyInfo> requestGetVerifyInfo(ReqGetVerifyInfo reqGetVerifyInfo);

    CompletableFuture<ResGetVerifyInfo> requestGetVerifyInfo(ReqGetVerifyInfo reqGetVerifyInfo, Duration duration);

    CompletableFuture<ResGetVerifyInfo> requestGetVerifyInfo(Duration duration);

    CompletableFuture<ResHidePoiIconsFromVR> requestHidePoiIconsFromVR();

    CompletableFuture<ResHidePoiIconsFromVR> requestHidePoiIconsFromVR(ReqHidePoiIconsFromVR reqHidePoiIconsFromVR);

    CompletableFuture<ResHidePoiIconsFromVR> requestHidePoiIconsFromVR(ReqHidePoiIconsFromVR reqHidePoiIconsFromVR, Duration duration);

    CompletableFuture<ResHidePoiIconsFromVR> requestHidePoiIconsFromVR(Duration duration);

    CompletableFuture<ResReqAddViaPosition.Arguments> requestReqAddViaPosition(ReqReqAddViaPosition.Arguments arguments);

    CompletableFuture<ResReqAddViaPosition.Arguments> requestReqAddViaPosition(ReqReqAddViaPosition.Arguments arguments, Duration duration);

    CompletableFuture<ResReqAddViaPosition> requestReqAddViaPosition(ReqReqAddViaPosition reqReqAddViaPosition);

    CompletableFuture<ResReqAddViaPosition> requestReqAddViaPosition(ReqReqAddViaPosition reqReqAddViaPosition, Duration duration);

    CompletableFuture<ResReqAppendVia.Arguments> requestReqAppendVia(ReqReqAppendVia.Arguments arguments);

    CompletableFuture<ResReqAppendVia.Arguments> requestReqAppendVia(ReqReqAppendVia.Arguments arguments, Duration duration);

    CompletableFuture<ResReqAppendVia> requestReqAppendVia(ReqReqAppendVia reqReqAppendVia);

    CompletableFuture<ResReqAppendVia> requestReqAppendVia(ReqReqAppendVia reqReqAppendVia, Duration duration);

    CompletableFuture<ResReqClosePoiDetail> requestReqClosePoiDetail();

    CompletableFuture<ResReqClosePoiDetail> requestReqClosePoiDetail(ReqReqClosePoiDetail reqReqClosePoiDetail);

    CompletableFuture<ResReqClosePoiDetail> requestReqClosePoiDetail(ReqReqClosePoiDetail reqReqClosePoiDetail, Duration duration);

    CompletableFuture<ResReqClosePoiDetail> requestReqClosePoiDetail(Duration duration);

    CompletableFuture<ResReqCloseRouteOption> requestReqCloseRouteOption();

    CompletableFuture<ResReqCloseRouteOption> requestReqCloseRouteOption(ReqReqCloseRouteOption reqReqCloseRouteOption);

    CompletableFuture<ResReqCloseRouteOption> requestReqCloseRouteOption(ReqReqCloseRouteOption reqReqCloseRouteOption, Duration duration);

    CompletableFuture<ResReqCloseRouteOption> requestReqCloseRouteOption(Duration duration);

    CompletableFuture<ResReqGuideStart.Arguments> requestReqGuideStart(ReqReqGuideStart.Arguments arguments);

    CompletableFuture<ResReqGuideStart.Arguments> requestReqGuideStart(ReqReqGuideStart.Arguments arguments, Duration duration);

    CompletableFuture<ResReqGuideStart> requestReqGuideStart(ReqReqGuideStart reqReqGuideStart);

    CompletableFuture<ResReqGuideStart> requestReqGuideStart(ReqReqGuideStart reqReqGuideStart, Duration duration);

    CompletableFuture<ResReqModifyDestination.Arguments> requestReqModifyDestination(ReqReqModifyDestination.Arguments arguments);

    CompletableFuture<ResReqModifyDestination.Arguments> requestReqModifyDestination(ReqReqModifyDestination.Arguments arguments, Duration duration);

    CompletableFuture<ResReqModifyDestination> requestReqModifyDestination(ReqReqModifyDestination reqReqModifyDestination);

    CompletableFuture<ResReqModifyDestination> requestReqModifyDestination(ReqReqModifyDestination reqReqModifyDestination, Duration duration);

    CompletableFuture<ResReqNaviAddFavorite> requestReqNaviAddFavorite();

    CompletableFuture<ResReqNaviAddFavorite> requestReqNaviAddFavorite(ReqReqNaviAddFavorite reqReqNaviAddFavorite);

    CompletableFuture<ResReqNaviAddFavorite> requestReqNaviAddFavorite(ReqReqNaviAddFavorite reqReqNaviAddFavorite, Duration duration);

    CompletableFuture<ResReqNaviAddFavorite> requestReqNaviAddFavorite(Duration duration);

    CompletableFuture<ResReqNaviAlongTheWaySearch.Arguments> requestReqNaviAlongTheWaySearch(ReqReqNaviAlongTheWaySearch.Arguments arguments);

    CompletableFuture<ResReqNaviAlongTheWaySearch.Arguments> requestReqNaviAlongTheWaySearch(ReqReqNaviAlongTheWaySearch.Arguments arguments, Duration duration);

    CompletableFuture<ResReqNaviAlongTheWaySearch> requestReqNaviAlongTheWaySearch(ReqReqNaviAlongTheWaySearch reqReqNaviAlongTheWaySearch);

    CompletableFuture<ResReqNaviAlongTheWaySearch> requestReqNaviAlongTheWaySearch(ReqReqNaviAlongTheWaySearch reqReqNaviAlongTheWaySearch, Duration duration);

    CompletableFuture<ResReqNaviGetRouteOption> requestReqNaviGetRouteOption();

    CompletableFuture<ResReqNaviGetRouteOption> requestReqNaviGetRouteOption(ReqReqNaviGetRouteOption reqReqNaviGetRouteOption);

    CompletableFuture<ResReqNaviGetRouteOption> requestReqNaviGetRouteOption(ReqReqNaviGetRouteOption reqReqNaviGetRouteOption, Duration duration);

    CompletableFuture<ResReqNaviGetRouteOption> requestReqNaviGetRouteOption(Duration duration);

    CompletableFuture<ResReqNaviInfo> requestReqNaviInfo();

    CompletableFuture<ResReqNaviInfo> requestReqNaviInfo(ReqReqNaviInfo reqReqNaviInfo);

    CompletableFuture<ResReqNaviInfo> requestReqNaviInfo(ReqReqNaviInfo reqReqNaviInfo, Duration duration);

    CompletableFuture<ResReqNaviInfo> requestReqNaviInfo(Duration duration);

    CompletableFuture<ResReqNaviNextViaTimeDistance> requestReqNaviNextViaTimeDistance();

    CompletableFuture<ResReqNaviNextViaTimeDistance> requestReqNaviNextViaTimeDistance(ReqReqNaviNextViaTimeDistance reqReqNaviNextViaTimeDistance);

    CompletableFuture<ResReqNaviNextViaTimeDistance> requestReqNaviNextViaTimeDistance(ReqReqNaviNextViaTimeDistance reqReqNaviNextViaTimeDistance, Duration duration);

    CompletableFuture<ResReqNaviNextViaTimeDistance> requestReqNaviNextViaTimeDistance(Duration duration);

    CompletableFuture<ResReqNaviRouteFullView.Arguments> requestReqNaviRouteFullView(ReqReqNaviRouteFullView.Arguments arguments);

    CompletableFuture<ResReqNaviRouteFullView.Arguments> requestReqNaviRouteFullView(ReqReqNaviRouteFullView.Arguments arguments, Duration duration);

    CompletableFuture<ResReqNaviRouteFullView> requestReqNaviRouteFullView(ReqReqNaviRouteFullView reqReqNaviRouteFullView);

    CompletableFuture<ResReqNaviRouteFullView> requestReqNaviRouteFullView(ReqReqNaviRouteFullView reqReqNaviRouteFullView, Duration duration);

    CompletableFuture<ResReqNaviSetRouteOption.Arguments> requestReqNaviSetRouteOption(ReqReqNaviSetRouteOption.Arguments arguments);

    CompletableFuture<ResReqNaviSetRouteOption.Arguments> requestReqNaviSetRouteOption(ReqReqNaviSetRouteOption.Arguments arguments, Duration duration);

    CompletableFuture<ResReqNaviSetRouteOption> requestReqNaviSetRouteOption(ReqReqNaviSetRouteOption reqReqNaviSetRouteOption);

    CompletableFuture<ResReqNaviSetRouteOption> requestReqNaviSetRouteOption(ReqReqNaviSetRouteOption reqReqNaviSetRouteOption, Duration duration);

    CompletableFuture<ResReqNaviViaPosition> requestReqNaviViaPosition();

    CompletableFuture<ResReqNaviViaPosition> requestReqNaviViaPosition(ReqReqNaviViaPosition reqReqNaviViaPosition);

    CompletableFuture<ResReqNaviViaPosition> requestReqNaviViaPosition(ReqReqNaviViaPosition reqReqNaviViaPosition, Duration duration);

    CompletableFuture<ResReqNaviViaPosition> requestReqNaviViaPosition(Duration duration);

    CompletableFuture<ResReqOpenHistoryDestination> requestReqOpenHistoryDestination();

    CompletableFuture<ResReqOpenHistoryDestination> requestReqOpenHistoryDestination(ReqReqOpenHistoryDestination reqReqOpenHistoryDestination);

    CompletableFuture<ResReqOpenHistoryDestination> requestReqOpenHistoryDestination(ReqReqOpenHistoryDestination reqReqOpenHistoryDestination, Duration duration);

    CompletableFuture<ResReqOpenHistoryDestination> requestReqOpenHistoryDestination(Duration duration);

    CompletableFuture<ResReqOpenPoiDetail.Arguments> requestReqOpenPoiDetail(ReqReqOpenPoiDetail.Arguments arguments);

    CompletableFuture<ResReqOpenPoiDetail.Arguments> requestReqOpenPoiDetail(ReqReqOpenPoiDetail.Arguments arguments, Duration duration);

    CompletableFuture<ResReqOpenPoiDetail> requestReqOpenPoiDetail(ReqReqOpenPoiDetail reqReqOpenPoiDetail);

    CompletableFuture<ResReqOpenPoiDetail> requestReqOpenPoiDetail(ReqReqOpenPoiDetail reqReqOpenPoiDetail, Duration duration);

    CompletableFuture<ResReqOpenRouteOption> requestReqOpenRouteOption();

    CompletableFuture<ResReqOpenRouteOption> requestReqOpenRouteOption(ReqReqOpenRouteOption reqReqOpenRouteOption);

    CompletableFuture<ResReqOpenRouteOption> requestReqOpenRouteOption(ReqReqOpenRouteOption reqReqOpenRouteOption, Duration duration);

    CompletableFuture<ResReqOpenRouteOption> requestReqOpenRouteOption(Duration duration);

    CompletableFuture<ResReqPOISearchByCategories.Arguments> requestReqPOISearchByCategories(ReqReqPOISearchByCategories.Arguments arguments);

    CompletableFuture<ResReqPOISearchByCategories.Arguments> requestReqPOISearchByCategories(ReqReqPOISearchByCategories.Arguments arguments, Duration duration);

    CompletableFuture<ResReqPOISearchByCategories> requestReqPOISearchByCategories(ReqReqPOISearchByCategories reqReqPOISearchByCategories);

    CompletableFuture<ResReqPOISearchByCategories> requestReqPOISearchByCategories(ReqReqPOISearchByCategories reqReqPOISearchByCategories, Duration duration);

    CompletableFuture<ResReqPOISearchByKeyword.Arguments> requestReqPOISearchByKeyword(ReqReqPOISearchByKeyword.Arguments arguments);

    CompletableFuture<ResReqPOISearchByKeyword.Arguments> requestReqPOISearchByKeyword(ReqReqPOISearchByKeyword.Arguments arguments, Duration duration);

    CompletableFuture<ResReqPOISearchByKeyword> requestReqPOISearchByKeyword(ReqReqPOISearchByKeyword reqReqPOISearchByKeyword);

    CompletableFuture<ResReqPOISearchByKeyword> requestReqPOISearchByKeyword(ReqReqPOISearchByKeyword reqReqPOISearchByKeyword, Duration duration);

    CompletableFuture<ResReqRoutePlanning.Arguments> requestReqRoutePlanning(ReqReqRoutePlanning.Arguments arguments);

    CompletableFuture<ResReqRoutePlanning.Arguments> requestReqRoutePlanning(ReqReqRoutePlanning.Arguments arguments, Duration duration);

    CompletableFuture<ResReqRoutePlanning> requestReqRoutePlanning(ReqReqRoutePlanning reqReqRoutePlanning);

    CompletableFuture<ResReqRoutePlanning> requestReqRoutePlanning(ReqReqRoutePlanning reqReqRoutePlanning, Duration duration);

    CompletableFuture<ResReqSetAddress.Arguments> requestReqSetAddress(ReqReqSetAddress.Arguments arguments);

    CompletableFuture<ResReqSetAddress.Arguments> requestReqSetAddress(ReqReqSetAddress.Arguments arguments, Duration duration);

    CompletableFuture<ResReqSetAddress> requestReqSetAddress(ReqReqSetAddress reqReqSetAddress);

    CompletableFuture<ResReqSetAddress> requestReqSetAddress(ReqReqSetAddress reqReqSetAddress, Duration duration);

    CompletableFuture<ResReqSmartNaviStart.Arguments> requestReqSmartNaviStart(ReqReqSmartNaviStart.Arguments arguments);

    CompletableFuture<ResReqSmartNaviStart.Arguments> requestReqSmartNaviStart(ReqReqSmartNaviStart.Arguments arguments, Duration duration);

    CompletableFuture<ResReqSmartNaviStart> requestReqSmartNaviStart(ReqReqSmartNaviStart reqReqSmartNaviStart);

    CompletableFuture<ResReqSmartNaviStart> requestReqSmartNaviStart(ReqReqSmartNaviStart reqReqSmartNaviStart, Duration duration);

    CompletableFuture<ResReqSmartRoutePlanning.Arguments> requestReqSmartRoutePlanning(ReqReqSmartRoutePlanning.Arguments arguments);

    CompletableFuture<ResReqSmartRoutePlanning.Arguments> requestReqSmartRoutePlanning(ReqReqSmartRoutePlanning.Arguments arguments, Duration duration);

    CompletableFuture<ResReqSmartRoutePlanning> requestReqSmartRoutePlanning(ReqReqSmartRoutePlanning reqReqSmartRoutePlanning);

    CompletableFuture<ResReqSmartRoutePlanning> requestReqSmartRoutePlanning(ReqReqSmartRoutePlanning reqReqSmartRoutePlanning, Duration duration);

    CompletableFuture<ResReqSwitchFavorite.Arguments> requestReqSwitchFavorite(ReqReqSwitchFavorite.Arguments arguments);

    CompletableFuture<ResReqSwitchFavorite.Arguments> requestReqSwitchFavorite(ReqReqSwitchFavorite.Arguments arguments, Duration duration);

    CompletableFuture<ResReqSwitchFavorite> requestReqSwitchFavorite(ReqReqSwitchFavorite reqReqSwitchFavorite);

    CompletableFuture<ResReqSwitchFavorite> requestReqSwitchFavorite(ReqReqSwitchFavorite reqReqSwitchFavorite, Duration duration);

    CompletableFuture<ResRequestGoHomeOrCompany.Arguments> requestRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany.Arguments arguments);

    CompletableFuture<ResRequestGoHomeOrCompany.Arguments> requestRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany.Arguments arguments, Duration duration);

    CompletableFuture<ResRequestGoHomeOrCompany> requestRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany reqRequestGoHomeOrCompany);

    CompletableFuture<ResRequestGoHomeOrCompany> requestRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany reqRequestGoHomeOrCompany, Duration duration);

    CompletableFuture<ResRequestShowHomeOrCompanySetPage.Arguments> requestRequestShowHomeOrCompanySetPage(ReqRequestShowHomeOrCompanySetPage.Arguments arguments);

    CompletableFuture<ResRequestShowHomeOrCompanySetPage.Arguments> requestRequestShowHomeOrCompanySetPage(ReqRequestShowHomeOrCompanySetPage.Arguments arguments, Duration duration);

    CompletableFuture<ResRequestShowHomeOrCompanySetPage> requestRequestShowHomeOrCompanySetPage(ReqRequestShowHomeOrCompanySetPage reqRequestShowHomeOrCompanySetPage);

    CompletableFuture<ResRequestShowHomeOrCompanySetPage> requestRequestShowHomeOrCompanySetPage(ReqRequestShowHomeOrCompanySetPage reqRequestShowHomeOrCompanySetPage, Duration duration);

    CompletableFuture<ResRequestShowSearchAlongRoutePage> requestRequestShowSearchAlongRoutePage();

    CompletableFuture<ResRequestShowSearchAlongRoutePage> requestRequestShowSearchAlongRoutePage(ReqRequestShowSearchAlongRoutePage reqRequestShowSearchAlongRoutePage);

    CompletableFuture<ResRequestShowSearchAlongRoutePage> requestRequestShowSearchAlongRoutePage(ReqRequestShowSearchAlongRoutePage reqRequestShowSearchAlongRoutePage, Duration duration);

    CompletableFuture<ResRequestShowSearchAlongRoutePage> requestRequestShowSearchAlongRoutePage(Duration duration);

    CompletableFuture<ResRequestShowSearchResultPage> requestRequestShowSearchResultPage();

    CompletableFuture<ResRequestShowSearchResultPage> requestRequestShowSearchResultPage(ReqRequestShowSearchResultPage reqRequestShowSearchResultPage);

    CompletableFuture<ResRequestShowSearchResultPage> requestRequestShowSearchResultPage(ReqRequestShowSearchResultPage reqRequestShowSearchResultPage, Duration duration);

    CompletableFuture<ResRequestShowSearchResultPage> requestRequestShowSearchResultPage(Duration duration);

    CompletableFuture<ResResumeNavigation> requestResumeNavigation();

    CompletableFuture<ResResumeNavigation> requestResumeNavigation(ReqResumeNavigation reqResumeNavigation);

    CompletableFuture<ResResumeNavigation> requestResumeNavigation(ReqResumeNavigation reqResumeNavigation, Duration duration);

    CompletableFuture<ResResumeNavigation> requestResumeNavigation(Duration duration);

    CompletableFuture<ResShowPoiIconsFromVR.Arguments> requestShowPoiIconsFromVR(ReqShowPoiIconsFromVR.Arguments arguments);

    CompletableFuture<ResShowPoiIconsFromVR.Arguments> requestShowPoiIconsFromVR(ReqShowPoiIconsFromVR.Arguments arguments, Duration duration);

    CompletableFuture<ResShowPoiIconsFromVR> requestShowPoiIconsFromVR(ReqShowPoiIconsFromVR reqShowPoiIconsFromVR);

    CompletableFuture<ResShowPoiIconsFromVR> requestShowPoiIconsFromVR(ReqShowPoiIconsFromVR reqShowPoiIconsFromVR, Duration duration);
}
