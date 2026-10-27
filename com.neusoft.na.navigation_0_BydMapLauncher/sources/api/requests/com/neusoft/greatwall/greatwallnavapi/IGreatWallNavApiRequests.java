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
import java9.util.concurrent.CompletableFuture;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes.dex */
public interface IGreatWallNavApiRequests {
    CompletableFuture<Void> fireClearFavorite(ReqClearFavorite.Arguments arguments);

    CompletableFuture<Void> fireClearFavorite(ReqClearFavorite.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireClearFavorite(ReqClearFavorite reqClearFavorite);

    CompletableFuture<Void> fireClearFavorite(ReqClearFavorite reqClearFavorite, Duration duration);

    CompletableFuture<Void> fireExitNavigation();

    CompletableFuture<Void> fireExitNavigation(ReqExitNavigation reqExitNavigation);

    CompletableFuture<Void> fireExitNavigation(ReqExitNavigation reqExitNavigation, Duration duration);

    CompletableFuture<Void> fireExitNavigation(Duration duration);

    CompletableFuture<Void> fireGetCalcPlanningRouteStatus();

    CompletableFuture<Void> fireGetCalcPlanningRouteStatus(ReqGetCalcPlanningRouteStatus reqGetCalcPlanningRouteStatus);

    CompletableFuture<Void> fireGetCalcPlanningRouteStatus(ReqGetCalcPlanningRouteStatus reqGetCalcPlanningRouteStatus, Duration duration);

    CompletableFuture<Void> fireGetCalcPlanningRouteStatus(Duration duration);

    CompletableFuture<Void> fireGetCountryChangedStatus();

    CompletableFuture<Void> fireGetCountryChangedStatus(ReqGetCountryChangedStatus reqGetCountryChangedStatus);

    CompletableFuture<Void> fireGetCountryChangedStatus(ReqGetCountryChangedStatus reqGetCountryChangedStatus, Duration duration);

    CompletableFuture<Void> fireGetCountryChangedStatus(Duration duration);

    CompletableFuture<Void> fireGetCountryInfo();

    CompletableFuture<Void> fireGetCountryInfo(ReqGetCountryInfo reqGetCountryInfo);

    CompletableFuture<Void> fireGetCountryInfo(ReqGetCountryInfo reqGetCountryInfo, Duration duration);

    CompletableFuture<Void> fireGetCountryInfo(Duration duration);

    CompletableFuture<Void> fireGetCurrentViewInfoStatus();

    CompletableFuture<Void> fireGetCurrentViewInfoStatus(ReqGetCurrentViewInfoStatus reqGetCurrentViewInfoStatus);

    CompletableFuture<Void> fireGetCurrentViewInfoStatus(ReqGetCurrentViewInfoStatus reqGetCurrentViewInfoStatus, Duration duration);

    CompletableFuture<Void> fireGetCurrentViewInfoStatus(Duration duration);

    CompletableFuture<Void> fireGetDeadReckoningExtendStatus();

    CompletableFuture<Void> fireGetDeadReckoningExtendStatus(ReqGetDeadReckoningExtendStatus reqGetDeadReckoningExtendStatus);

    CompletableFuture<Void> fireGetDeadReckoningExtendStatus(ReqGetDeadReckoningExtendStatus reqGetDeadReckoningExtendStatus, Duration duration);

    CompletableFuture<Void> fireGetDeadReckoningExtendStatus(Duration duration);

    CompletableFuture<Void> fireGetDestinationHistoryListStatus();

    CompletableFuture<Void> fireGetDestinationHistoryListStatus(ReqGetDestinationHistoryListStatus reqGetDestinationHistoryListStatus);

    CompletableFuture<Void> fireGetDestinationHistoryListStatus(ReqGetDestinationHistoryListStatus reqGetDestinationHistoryListStatus, Duration duration);

    CompletableFuture<Void> fireGetDestinationHistoryListStatus(Duration duration);

    CompletableFuture<Void> fireGetFavoriteList(ReqGetFavoriteList.Arguments arguments);

    CompletableFuture<Void> fireGetFavoriteList(ReqGetFavoriteList.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireGetFavoriteList(ReqGetFavoriteList reqGetFavoriteList);

    CompletableFuture<Void> fireGetFavoriteList(ReqGetFavoriteList reqGetFavoriteList, Duration duration);

    CompletableFuture<Void> fireGetFrontTraffic();

    CompletableFuture<Void> fireGetFrontTraffic(ReqGetFrontTraffic reqGetFrontTraffic);

    CompletableFuture<Void> fireGetFrontTraffic(ReqGetFrontTraffic reqGetFrontTraffic, Duration duration);

    CompletableFuture<Void> fireGetFrontTraffic(Duration duration);

    CompletableFuture<Void> fireGetGuidanceInfoStatus();

    CompletableFuture<Void> fireGetGuidanceInfoStatus(ReqGetGuidanceInfoStatus reqGetGuidanceInfoStatus);

    CompletableFuture<Void> fireGetGuidanceInfoStatus(ReqGetGuidanceInfoStatus reqGetGuidanceInfoStatus, Duration duration);

    CompletableFuture<Void> fireGetGuidanceInfoStatus(Duration duration);

    CompletableFuture<Void> fireGetGuidanceInfoStatusForGreatWall();

    CompletableFuture<Void> fireGetGuidanceInfoStatusForGreatWall(ReqGetGuidanceInfoStatusForGreatWall reqGetGuidanceInfoStatusForGreatWall);

    CompletableFuture<Void> fireGetGuidanceInfoStatusForGreatWall(ReqGetGuidanceInfoStatusForGreatWall reqGetGuidanceInfoStatusForGreatWall, Duration duration);

    CompletableFuture<Void> fireGetGuidanceInfoStatusForGreatWall(Duration duration);

    CompletableFuture<Void> fireGetGuidanceStatus();

    CompletableFuture<Void> fireGetGuidanceStatus(ReqGetGuidanceStatus reqGetGuidanceStatus);

    CompletableFuture<Void> fireGetGuidanceStatus(ReqGetGuidanceStatus reqGetGuidanceStatus, Duration duration);

    CompletableFuture<Void> fireGetGuidanceStatus(Duration duration);

    CompletableFuture<Void> fireGetGuidedStatus();

    CompletableFuture<Void> fireGetGuidedStatus(ReqGetGuidedStatus reqGetGuidedStatus);

    CompletableFuture<Void> fireGetGuidedStatus(ReqGetGuidedStatus reqGetGuidedStatus, Duration duration);

    CompletableFuture<Void> fireGetGuidedStatus(Duration duration);

    CompletableFuture<Void> fireGetHeadingStatus();

    CompletableFuture<Void> fireGetHeadingStatus(ReqGetHeadingStatus reqGetHeadingStatus);

    CompletableFuture<Void> fireGetHeadingStatus(ReqGetHeadingStatus reqGetHeadingStatus, Duration duration);

    CompletableFuture<Void> fireGetHeadingStatus(Duration duration);

    CompletableFuture<Void> fireGetLaneInfoStatus();

    CompletableFuture<Void> fireGetLaneInfoStatus(ReqGetLaneInfoStatus reqGetLaneInfoStatus);

    CompletableFuture<Void> fireGetLaneInfoStatus(ReqGetLaneInfoStatus reqGetLaneInfoStatus, Duration duration);

    CompletableFuture<Void> fireGetLaneInfoStatus(Duration duration);

    CompletableFuture<Void> fireGetLocationInfo(ReqGetLocationInfo.Arguments arguments);

    CompletableFuture<Void> fireGetLocationInfo(ReqGetLocationInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireGetLocationInfo(ReqGetLocationInfo reqGetLocationInfo);

    CompletableFuture<Void> fireGetLocationInfo(ReqGetLocationInfo reqGetLocationInfo, Duration duration);

    CompletableFuture<Void> fireGetNaviJumpToSystemPage();

    CompletableFuture<Void> fireGetNaviJumpToSystemPage(ReqGetNaviJumpToSystemPage reqGetNaviJumpToSystemPage);

    CompletableFuture<Void> fireGetNaviJumpToSystemPage(ReqGetNaviJumpToSystemPage reqGetNaviJumpToSystemPage, Duration duration);

    CompletableFuture<Void> fireGetNaviJumpToSystemPage(Duration duration);

    CompletableFuture<Void> fireGetNaviMuteMode();

    CompletableFuture<Void> fireGetNaviMuteMode(ReqGetNaviMuteMode reqGetNaviMuteMode);

    CompletableFuture<Void> fireGetNaviMuteMode(ReqGetNaviMuteMode reqGetNaviMuteMode, Duration duration);

    CompletableFuture<Void> fireGetNaviMuteMode(Duration duration);

    CompletableFuture<Void> fireGetNaviMuteStatus();

    CompletableFuture<Void> fireGetNaviMuteStatus(ReqGetNaviMuteStatus reqGetNaviMuteStatus);

    CompletableFuture<Void> fireGetNaviMuteStatus(ReqGetNaviMuteStatus reqGetNaviMuteStatus, Duration duration);

    CompletableFuture<Void> fireGetNaviMuteStatus(Duration duration);

    CompletableFuture<Void> fireGetNaviSettingStateStatus();

    CompletableFuture<Void> fireGetNaviSettingStateStatus(ReqGetNaviSettingStateStatus reqGetNaviSettingStateStatus);

    CompletableFuture<Void> fireGetNaviSettingStateStatus(ReqGetNaviSettingStateStatus reqGetNaviSettingStateStatus, Duration duration);

    CompletableFuture<Void> fireGetNaviSettingStateStatus(Duration duration);

    CompletableFuture<Void> fireGetNotifyHeadingStatus();

    CompletableFuture<Void> fireGetNotifyHeadingStatus(ReqGetNotifyHeadingStatus reqGetNotifyHeadingStatus);

    CompletableFuture<Void> fireGetNotifyHeadingStatus(ReqGetNotifyHeadingStatus reqGetNotifyHeadingStatus, Duration duration);

    CompletableFuture<Void> fireGetNotifyHeadingStatus(Duration duration);

    CompletableFuture<Void> fireGetPostCustomEvent();

    CompletableFuture<Void> fireGetPostCustomEvent(ReqGetPostCustomEvent reqGetPostCustomEvent);

    CompletableFuture<Void> fireGetPostCustomEvent(ReqGetPostCustomEvent reqGetPostCustomEvent, Duration duration);

    CompletableFuture<Void> fireGetPostCustomEvent(Duration duration);

    CompletableFuture<Void> fireGetPostPageTransitionEvent();

    CompletableFuture<Void> fireGetPostPageTransitionEvent(ReqGetPostPageTransitionEvent reqGetPostPageTransitionEvent);

    CompletableFuture<Void> fireGetPostPageTransitionEvent(ReqGetPostPageTransitionEvent reqGetPostPageTransitionEvent, Duration duration);

    CompletableFuture<Void> fireGetPostPageTransitionEvent(Duration duration);

    CompletableFuture<Void> fireGetPostServiceEvent();

    CompletableFuture<Void> fireGetPostServiceEvent(ReqGetPostServiceEvent reqGetPostServiceEvent);

    CompletableFuture<Void> fireGetPostServiceEvent(ReqGetPostServiceEvent reqGetPostServiceEvent, Duration duration);

    CompletableFuture<Void> fireGetPostServiceEvent(Duration duration);

    CompletableFuture<Void> fireGetPostUITouchEvent();

    CompletableFuture<Void> fireGetPostUITouchEvent(ReqGetPostUITouchEvent reqGetPostUITouchEvent);

    CompletableFuture<Void> fireGetPostUITouchEvent(ReqGetPostUITouchEvent reqGetPostUITouchEvent, Duration duration);

    CompletableFuture<Void> fireGetPostUITouchEvent(Duration duration);

    CompletableFuture<Void> fireGetReportLocationInfo();

    CompletableFuture<Void> fireGetReportLocationInfo(ReqGetReportLocationInfo reqGetReportLocationInfo);

    CompletableFuture<Void> fireGetReportLocationInfo(ReqGetReportLocationInfo reqGetReportLocationInfo, Duration duration);

    CompletableFuture<Void> fireGetReportLocationInfo(Duration duration);

    CompletableFuture<Void> fireGetRequestPrivacyMode();

    CompletableFuture<Void> fireGetRequestPrivacyMode(ReqGetRequestPrivacyMode reqGetRequestPrivacyMode);

    CompletableFuture<Void> fireGetRequestPrivacyMode(ReqGetRequestPrivacyMode reqGetRequestPrivacyMode, Duration duration);

    CompletableFuture<Void> fireGetRequestPrivacyMode(Duration duration);

    CompletableFuture<Void> fireGetResponseDirectNavigation();

    CompletableFuture<Void> fireGetResponseDirectNavigation(ReqGetResponseDirectNavigation reqGetResponseDirectNavigation);

    CompletableFuture<Void> fireGetResponseDirectNavigation(ReqGetResponseDirectNavigation reqGetResponseDirectNavigation, Duration duration);

    CompletableFuture<Void> fireGetResponseDirectNavigation(Duration duration);

    CompletableFuture<Void> fireGetRouteOverviewState();

    CompletableFuture<Void> fireGetRouteOverviewState(ReqGetRouteOverviewState reqGetRouteOverviewState);

    CompletableFuture<Void> fireGetRouteOverviewState(ReqGetRouteOverviewState reqGetRouteOverviewState, Duration duration);

    CompletableFuture<Void> fireGetRouteOverviewState(Duration duration);

    CompletableFuture<Void> fireGetRoutePreference();

    CompletableFuture<Void> fireGetRoutePreference(ReqGetRoutePreference reqGetRoutePreference);

    CompletableFuture<Void> fireGetRoutePreference(ReqGetRoutePreference reqGetRoutePreference, Duration duration);

    CompletableFuture<Void> fireGetRoutePreference(Duration duration);

    CompletableFuture<Void> fireGetSpeedLimitStatus();

    CompletableFuture<Void> fireGetSpeedLimitStatus(ReqGetSpeedLimitStatus reqGetSpeedLimitStatus);

    CompletableFuture<Void> fireGetSpeedLimitStatus(ReqGetSpeedLimitStatus reqGetSpeedLimitStatus, Duration duration);

    CompletableFuture<Void> fireGetSpeedLimitStatus(Duration duration);

    CompletableFuture<Void> fireGetSwitchNavigationLanguageSetting();

    CompletableFuture<Void> fireGetSwitchNavigationLanguageSetting(ReqGetSwitchNavigationLanguageSetting reqGetSwitchNavigationLanguageSetting);

    CompletableFuture<Void> fireGetSwitchNavigationLanguageSetting(ReqGetSwitchNavigationLanguageSetting reqGetSwitchNavigationLanguageSetting, Duration duration);

    CompletableFuture<Void> fireGetSwitchNavigationLanguageSetting(Duration duration);

    CompletableFuture<Void> fireGetWaypointList();

    CompletableFuture<Void> fireGetWaypointList(ReqGetWaypointList reqGetWaypointList);

    CompletableFuture<Void> fireGetWaypointList(ReqGetWaypointList reqGetWaypointList, Duration duration);

    CompletableFuture<Void> fireGetWaypointList(Duration duration);

    CompletableFuture<Void> firePowerSavingModeChanged(ReqPowerSavingModeChanged.Arguments arguments);

    CompletableFuture<Void> firePowerSavingModeChanged(ReqPowerSavingModeChanged.Arguments arguments, Duration duration);

    CompletableFuture<Void> firePowerSavingModeChanged(ReqPowerSavingModeChanged reqPowerSavingModeChanged);

    CompletableFuture<Void> firePowerSavingModeChanged(ReqPowerSavingModeChanged reqPowerSavingModeChanged, Duration duration);

    CompletableFuture<Void> fireRemoveWaypoint(ReqRemoveWaypoint.Arguments arguments);

    CompletableFuture<Void> fireRemoveWaypoint(ReqRemoveWaypoint.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRemoveWaypoint(ReqRemoveWaypoint reqRemoveWaypoint);

    CompletableFuture<Void> fireRemoveWaypoint(ReqRemoveWaypoint reqRemoveWaypoint, Duration duration);

    CompletableFuture<Void> fireRequestAlongRouteSearch(ReqRequestAlongRouteSearch.Arguments arguments);

    CompletableFuture<Void> fireRequestAlongRouteSearch(ReqRequestAlongRouteSearch.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestAlongRouteSearch(ReqRequestAlongRouteSearch reqRequestAlongRouteSearch);

    CompletableFuture<Void> fireRequestAlongRouteSearch(ReqRequestAlongRouteSearch reqRequestAlongRouteSearch, Duration duration);

    CompletableFuture<Void> fireRequestCalcPlanningRoute(ReqRequestCalcPlanningRoute.Arguments arguments);

    CompletableFuture<Void> fireRequestCalcPlanningRoute(ReqRequestCalcPlanningRoute.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestCalcPlanningRoute(ReqRequestCalcPlanningRoute reqRequestCalcPlanningRoute);

    CompletableFuture<Void> fireRequestCalcPlanningRoute(ReqRequestCalcPlanningRoute reqRequestCalcPlanningRoute, Duration duration);

    CompletableFuture<Void> fireRequestDTDAndETA();

    CompletableFuture<Void> fireRequestDTDAndETA(ReqRequestDTDAndETA reqRequestDTDAndETA);

    CompletableFuture<Void> fireRequestDTDAndETA(ReqRequestDTDAndETA reqRequestDTDAndETA, Duration duration);

    CompletableFuture<Void> fireRequestDTDAndETA(Duration duration);

    CompletableFuture<Void> fireRequestDestinationHistoryList();

    CompletableFuture<Void> fireRequestDestinationHistoryList(ReqRequestDestinationHistoryList reqRequestDestinationHistoryList);

    CompletableFuture<Void> fireRequestDestinationHistoryList(ReqRequestDestinationHistoryList reqRequestDestinationHistoryList, Duration duration);

    CompletableFuture<Void> fireRequestDestinationHistoryList(Duration duration);

    CompletableFuture<Void> fireRequestDirectNavigation(ReqRequestDirectNavigation.Arguments arguments);

    CompletableFuture<Void> fireRequestDirectNavigation(ReqRequestDirectNavigation.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestDirectNavigation(ReqRequestDirectNavigation reqRequestDirectNavigation);

    CompletableFuture<Void> fireRequestDirectNavigation(ReqRequestDirectNavigation reqRequestDirectNavigation, Duration duration);

    CompletableFuture<Void> fireRequestGetDestinationHistoryList();

    CompletableFuture<Void> fireRequestGetDestinationHistoryList(ReqRequestGetDestinationHistoryList reqRequestGetDestinationHistoryList);

    CompletableFuture<Void> fireRequestGetDestinationHistoryList(ReqRequestGetDestinationHistoryList reqRequestGetDestinationHistoryList, Duration duration);

    CompletableFuture<Void> fireRequestGetDestinationHistoryList(Duration duration);

    CompletableFuture<Void> fireRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany.Arguments arguments);

    CompletableFuture<Void> fireRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany reqRequestGoHomeOrCompany);

    CompletableFuture<Void> fireRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany reqRequestGoHomeOrCompany, Duration duration);

    CompletableFuture<Void> fireRequestHighlightedRoute(ReqRequestHighlightedRoute.Arguments arguments);

    CompletableFuture<Void> fireRequestHighlightedRoute(ReqRequestHighlightedRoute.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestHighlightedRoute(ReqRequestHighlightedRoute reqRequestHighlightedRoute);

    CompletableFuture<Void> fireRequestHighlightedRoute(ReqRequestHighlightedRoute reqRequestHighlightedRoute, Duration duration);

    CompletableFuture<Void> fireRequestIntoSearchPage();

    CompletableFuture<Void> fireRequestIntoSearchPage(ReqRequestIntoSearchPage reqRequestIntoSearchPage);

    CompletableFuture<Void> fireRequestIntoSearchPage(ReqRequestIntoSearchPage reqRequestIntoSearchPage, Duration duration);

    CompletableFuture<Void> fireRequestIntoSearchPage(Duration duration);

    CompletableFuture<Void> fireRequestJumpToSetHomeCompany(ReqRequestJumpToSetHomeCompany.Arguments arguments);

    CompletableFuture<Void> fireRequestJumpToSetHomeCompany(ReqRequestJumpToSetHomeCompany.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestJumpToSetHomeCompany(ReqRequestJumpToSetHomeCompany reqRequestJumpToSetHomeCompany);

    CompletableFuture<Void> fireRequestJumpToSetHomeCompany(ReqRequestJumpToSetHomeCompany reqRequestJumpToSetHomeCompany, Duration duration);

    CompletableFuture<Void> fireRequestJumpToViewPage(ReqRequestJumpToViewPage.Arguments arguments);

    CompletableFuture<Void> fireRequestJumpToViewPage(ReqRequestJumpToViewPage.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestJumpToViewPage(ReqRequestJumpToViewPage reqRequestJumpToViewPage);

    CompletableFuture<Void> fireRequestJumpToViewPage(ReqRequestJumpToViewPage reqRequestJumpToViewPage, Duration duration);

    CompletableFuture<Void> fireRequestNaviSettingState();

    CompletableFuture<Void> fireRequestNaviSettingState(ReqRequestNaviSettingState reqRequestNaviSettingState);

    CompletableFuture<Void> fireRequestNaviSettingState(ReqRequestNaviSettingState reqRequestNaviSettingState, Duration duration);

    CompletableFuture<Void> fireRequestNaviSettingState(Duration duration);

    CompletableFuture<Void> fireRequestOpenFavorite();

    CompletableFuture<Void> fireRequestOpenFavorite(ReqRequestOpenFavorite reqRequestOpenFavorite);

    CompletableFuture<Void> fireRequestOpenFavorite(ReqRequestOpenFavorite reqRequestOpenFavorite, Duration duration);

    CompletableFuture<Void> fireRequestOpenFavorite(Duration duration);

    CompletableFuture<Void> fireRequestOpenMap(ReqRequestOpenMap.Arguments arguments);

    CompletableFuture<Void> fireRequestOpenMap(ReqRequestOpenMap.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestOpenMap(ReqRequestOpenMap reqRequestOpenMap);

    CompletableFuture<Void> fireRequestOpenMap(ReqRequestOpenMap reqRequestOpenMap, Duration duration);

    CompletableFuture<Void> fireRequestSearchAround(ReqRequestSearchAround.Arguments arguments);

    CompletableFuture<Void> fireRequestSearchAround(ReqRequestSearchAround.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestSearchAround(ReqRequestSearchAround reqRequestSearchAround);

    CompletableFuture<Void> fireRequestSearchAround(ReqRequestSearchAround reqRequestSearchAround, Duration duration);

    CompletableFuture<Void> fireRequestSearchKeyword(ReqRequestSearchKeyword.Arguments arguments);

    CompletableFuture<Void> fireRequestSearchKeyword(ReqRequestSearchKeyword.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestSearchKeyword(ReqRequestSearchKeyword reqRequestSearchKeyword);

    CompletableFuture<Void> fireRequestSearchKeyword(ReqRequestSearchKeyword reqRequestSearchKeyword, Duration duration);

    CompletableFuture<Void> fireRequestSetFavorite(ReqRequestSetFavorite.Arguments arguments);

    CompletableFuture<Void> fireRequestSetFavorite(ReqRequestSetFavorite.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestSetFavorite(ReqRequestSetFavorite reqRequestSetFavorite);

    CompletableFuture<Void> fireRequestSetFavorite(ReqRequestSetFavorite reqRequestSetFavorite, Duration duration);

    CompletableFuture<Void> fireRequestSetRoutePreference(ReqRequestSetRoutePreference.Arguments arguments);

    CompletableFuture<Void> fireRequestSetRoutePreference(ReqRequestSetRoutePreference.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestSetRoutePreference(ReqRequestSetRoutePreference reqRequestSetRoutePreference);

    CompletableFuture<Void> fireRequestSetRoutePreference(ReqRequestSetRoutePreference reqRequestSetRoutePreference, Duration duration);

    CompletableFuture<Void> fireRequestSetWaypoint(ReqRequestSetWaypoint.Arguments arguments);

    CompletableFuture<Void> fireRequestSetWaypoint(ReqRequestSetWaypoint.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestSetWaypoint(ReqRequestSetWaypoint reqRequestSetWaypoint);

    CompletableFuture<Void> fireRequestSetWaypoint(ReqRequestSetWaypoint reqRequestSetWaypoint, Duration duration);

    CompletableFuture<Void> fireRequestSimulation(ReqRequestSimulation.Arguments arguments);

    CompletableFuture<Void> fireRequestSimulation(ReqRequestSimulation.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireRequestSimulation(ReqRequestSimulation reqRequestSimulation);

    CompletableFuture<Void> fireRequestSimulation(ReqRequestSimulation reqRequestSimulation, Duration duration);

    CompletableFuture<Void> fireRequestStartGuidance();

    CompletableFuture<Void> fireRequestStartGuidance(ReqRequestStartGuidance reqRequestStartGuidance);

    CompletableFuture<Void> fireRequestStartGuidance(ReqRequestStartGuidance reqRequestStartGuidance, Duration duration);

    CompletableFuture<Void> fireRequestStartGuidance(Duration duration);

    CompletableFuture<Void> fireRequestStopGuidance();

    CompletableFuture<Void> fireRequestStopGuidance(ReqRequestStopGuidance reqRequestStopGuidance);

    CompletableFuture<Void> fireRequestStopGuidance(ReqRequestStopGuidance reqRequestStopGuidance, Duration duration);

    CompletableFuture<Void> fireRequestStopGuidance(Duration duration);

    CompletableFuture<Void> fireSetDataTrackEnable(ReqSetDataTrackEnable.Arguments arguments);

    CompletableFuture<Void> fireSetDataTrackEnable(ReqSetDataTrackEnable.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetDataTrackEnable(ReqSetDataTrackEnable reqSetDataTrackEnable);

    CompletableFuture<Void> fireSetDataTrackEnable(ReqSetDataTrackEnable reqSetDataTrackEnable, Duration duration);

    CompletableFuture<Void> fireSetIVIConfiguration(ReqSetIVIConfiguration.Arguments arguments);

    CompletableFuture<Void> fireSetIVIConfiguration(ReqSetIVIConfiguration.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetIVIConfiguration(ReqSetIVIConfiguration reqSetIVIConfiguration);

    CompletableFuture<Void> fireSetIVIConfiguration(ReqSetIVIConfiguration reqSetIVIConfiguration, Duration duration);

    CompletableFuture<Void> fireSetNaviMuteMode(ReqSetNaviMuteMode.Arguments arguments);

    CompletableFuture<Void> fireSetNaviMuteMode(ReqSetNaviMuteMode.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetNaviMuteMode(ReqSetNaviMuteMode reqSetNaviMuteMode);

    CompletableFuture<Void> fireSetNaviMuteMode(ReqSetNaviMuteMode reqSetNaviMuteMode, Duration duration);

    CompletableFuture<Void> fireSetPrivacyMode(ReqSetPrivacyMode.Arguments arguments);

    CompletableFuture<Void> fireSetPrivacyMode(ReqSetPrivacyMode.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetPrivacyMode(ReqSetPrivacyMode reqSetPrivacyMode);

    CompletableFuture<Void> fireSetPrivacyMode(ReqSetPrivacyMode reqSetPrivacyMode, Duration duration);

    CompletableFuture<Void> fireSetShareLocationStatus(ReqSetShareLocationStatus.Arguments arguments);

    CompletableFuture<Void> fireSetShareLocationStatus(ReqSetShareLocationStatus.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetShareLocationStatus(ReqSetShareLocationStatus reqSetShareLocationStatus);

    CompletableFuture<Void> fireSetShareLocationStatus(ReqSetShareLocationStatus reqSetShareLocationStatus, Duration duration);

    CompletableFuture<Void> fireShowModeChanged(ReqShowModeChanged.Arguments arguments);

    CompletableFuture<Void> fireShowModeChanged(ReqShowModeChanged.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireShowModeChanged(ReqShowModeChanged reqShowModeChanged);

    CompletableFuture<Void> fireShowModeChanged(ReqShowModeChanged reqShowModeChanged, Duration duration);

    CompletableFuture<Void> fireSwitchRouteOverviewInfo(ReqSwitchRouteOverviewInfo.Arguments arguments);

    CompletableFuture<Void> fireSwitchRouteOverviewInfo(ReqSwitchRouteOverviewInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSwitchRouteOverviewInfo(ReqSwitchRouteOverviewInfo reqSwitchRouteOverviewInfo);

    CompletableFuture<Void> fireSwitchRouteOverviewInfo(ReqSwitchRouteOverviewInfo reqSwitchRouteOverviewInfo, Duration duration);

    CompletableFuture<ResClearFavorite.Arguments> requestClearFavorite(ReqClearFavorite.Arguments arguments);

    CompletableFuture<ResClearFavorite.Arguments> requestClearFavorite(ReqClearFavorite.Arguments arguments, Duration duration);

    CompletableFuture<ResClearFavorite> requestClearFavorite(ReqClearFavorite reqClearFavorite);

    CompletableFuture<ResClearFavorite> requestClearFavorite(ReqClearFavorite reqClearFavorite, Duration duration);

    CompletableFuture<ResExitNavigation> requestExitNavigation();

    CompletableFuture<ResExitNavigation> requestExitNavigation(ReqExitNavigation reqExitNavigation);

    CompletableFuture<ResExitNavigation> requestExitNavigation(ReqExitNavigation reqExitNavigation, Duration duration);

    CompletableFuture<ResExitNavigation> requestExitNavigation(Duration duration);

    CompletableFuture<ResGetCalcPlanningRouteStatus> requestGetCalcPlanningRouteStatus();

    CompletableFuture<ResGetCalcPlanningRouteStatus> requestGetCalcPlanningRouteStatus(ReqGetCalcPlanningRouteStatus reqGetCalcPlanningRouteStatus);

    CompletableFuture<ResGetCalcPlanningRouteStatus> requestGetCalcPlanningRouteStatus(ReqGetCalcPlanningRouteStatus reqGetCalcPlanningRouteStatus, Duration duration);

    CompletableFuture<ResGetCalcPlanningRouteStatus> requestGetCalcPlanningRouteStatus(Duration duration);

    CompletableFuture<ResGetCountryChangedStatus> requestGetCountryChangedStatus();

    CompletableFuture<ResGetCountryChangedStatus> requestGetCountryChangedStatus(ReqGetCountryChangedStatus reqGetCountryChangedStatus);

    CompletableFuture<ResGetCountryChangedStatus> requestGetCountryChangedStatus(ReqGetCountryChangedStatus reqGetCountryChangedStatus, Duration duration);

    CompletableFuture<ResGetCountryChangedStatus> requestGetCountryChangedStatus(Duration duration);

    CompletableFuture<ResGetCountryInfo> requestGetCountryInfo();

    CompletableFuture<ResGetCountryInfo> requestGetCountryInfo(ReqGetCountryInfo reqGetCountryInfo);

    CompletableFuture<ResGetCountryInfo> requestGetCountryInfo(ReqGetCountryInfo reqGetCountryInfo, Duration duration);

    CompletableFuture<ResGetCountryInfo> requestGetCountryInfo(Duration duration);

    CompletableFuture<ResGetCurrentViewInfoStatus> requestGetCurrentViewInfoStatus();

    CompletableFuture<ResGetCurrentViewInfoStatus> requestGetCurrentViewInfoStatus(ReqGetCurrentViewInfoStatus reqGetCurrentViewInfoStatus);

    CompletableFuture<ResGetCurrentViewInfoStatus> requestGetCurrentViewInfoStatus(ReqGetCurrentViewInfoStatus reqGetCurrentViewInfoStatus, Duration duration);

    CompletableFuture<ResGetCurrentViewInfoStatus> requestGetCurrentViewInfoStatus(Duration duration);

    CompletableFuture<ResGetDeadReckoningExtendStatus> requestGetDeadReckoningExtendStatus();

    CompletableFuture<ResGetDeadReckoningExtendStatus> requestGetDeadReckoningExtendStatus(ReqGetDeadReckoningExtendStatus reqGetDeadReckoningExtendStatus);

    CompletableFuture<ResGetDeadReckoningExtendStatus> requestGetDeadReckoningExtendStatus(ReqGetDeadReckoningExtendStatus reqGetDeadReckoningExtendStatus, Duration duration);

    CompletableFuture<ResGetDeadReckoningExtendStatus> requestGetDeadReckoningExtendStatus(Duration duration);

    CompletableFuture<ResGetDestinationHistoryListStatus> requestGetDestinationHistoryListStatus();

    CompletableFuture<ResGetDestinationHistoryListStatus> requestGetDestinationHistoryListStatus(ReqGetDestinationHistoryListStatus reqGetDestinationHistoryListStatus);

    CompletableFuture<ResGetDestinationHistoryListStatus> requestGetDestinationHistoryListStatus(ReqGetDestinationHistoryListStatus reqGetDestinationHistoryListStatus, Duration duration);

    CompletableFuture<ResGetDestinationHistoryListStatus> requestGetDestinationHistoryListStatus(Duration duration);

    CompletableFuture<ResGetFavoriteList.Arguments> requestGetFavoriteList(ReqGetFavoriteList.Arguments arguments);

    CompletableFuture<ResGetFavoriteList.Arguments> requestGetFavoriteList(ReqGetFavoriteList.Arguments arguments, Duration duration);

    CompletableFuture<ResGetFavoriteList> requestGetFavoriteList(ReqGetFavoriteList reqGetFavoriteList);

    CompletableFuture<ResGetFavoriteList> requestGetFavoriteList(ReqGetFavoriteList reqGetFavoriteList, Duration duration);

    CompletableFuture<ResGetFrontTraffic> requestGetFrontTraffic();

    CompletableFuture<ResGetFrontTraffic> requestGetFrontTraffic(ReqGetFrontTraffic reqGetFrontTraffic);

    CompletableFuture<ResGetFrontTraffic> requestGetFrontTraffic(ReqGetFrontTraffic reqGetFrontTraffic, Duration duration);

    CompletableFuture<ResGetFrontTraffic> requestGetFrontTraffic(Duration duration);

    CompletableFuture<ResGetGuidanceInfoStatus> requestGetGuidanceInfoStatus();

    CompletableFuture<ResGetGuidanceInfoStatus> requestGetGuidanceInfoStatus(ReqGetGuidanceInfoStatus reqGetGuidanceInfoStatus);

    CompletableFuture<ResGetGuidanceInfoStatus> requestGetGuidanceInfoStatus(ReqGetGuidanceInfoStatus reqGetGuidanceInfoStatus, Duration duration);

    CompletableFuture<ResGetGuidanceInfoStatus> requestGetGuidanceInfoStatus(Duration duration);

    CompletableFuture<ResGetGuidanceInfoStatusForGreatWall> requestGetGuidanceInfoStatusForGreatWall();

    CompletableFuture<ResGetGuidanceInfoStatusForGreatWall> requestGetGuidanceInfoStatusForGreatWall(ReqGetGuidanceInfoStatusForGreatWall reqGetGuidanceInfoStatusForGreatWall);

    CompletableFuture<ResGetGuidanceInfoStatusForGreatWall> requestGetGuidanceInfoStatusForGreatWall(ReqGetGuidanceInfoStatusForGreatWall reqGetGuidanceInfoStatusForGreatWall, Duration duration);

    CompletableFuture<ResGetGuidanceInfoStatusForGreatWall> requestGetGuidanceInfoStatusForGreatWall(Duration duration);

    CompletableFuture<ResGetGuidanceStatus> requestGetGuidanceStatus();

    CompletableFuture<ResGetGuidanceStatus> requestGetGuidanceStatus(ReqGetGuidanceStatus reqGetGuidanceStatus);

    CompletableFuture<ResGetGuidanceStatus> requestGetGuidanceStatus(ReqGetGuidanceStatus reqGetGuidanceStatus, Duration duration);

    CompletableFuture<ResGetGuidanceStatus> requestGetGuidanceStatus(Duration duration);

    CompletableFuture<ResGetGuidedStatus> requestGetGuidedStatus();

    CompletableFuture<ResGetGuidedStatus> requestGetGuidedStatus(ReqGetGuidedStatus reqGetGuidedStatus);

    CompletableFuture<ResGetGuidedStatus> requestGetGuidedStatus(ReqGetGuidedStatus reqGetGuidedStatus, Duration duration);

    CompletableFuture<ResGetGuidedStatus> requestGetGuidedStatus(Duration duration);

    CompletableFuture<ResGetHeadingStatus> requestGetHeadingStatus();

    CompletableFuture<ResGetHeadingStatus> requestGetHeadingStatus(ReqGetHeadingStatus reqGetHeadingStatus);

    CompletableFuture<ResGetHeadingStatus> requestGetHeadingStatus(ReqGetHeadingStatus reqGetHeadingStatus, Duration duration);

    CompletableFuture<ResGetHeadingStatus> requestGetHeadingStatus(Duration duration);

    CompletableFuture<ResGetLaneInfoStatus> requestGetLaneInfoStatus();

    CompletableFuture<ResGetLaneInfoStatus> requestGetLaneInfoStatus(ReqGetLaneInfoStatus reqGetLaneInfoStatus);

    CompletableFuture<ResGetLaneInfoStatus> requestGetLaneInfoStatus(ReqGetLaneInfoStatus reqGetLaneInfoStatus, Duration duration);

    CompletableFuture<ResGetLaneInfoStatus> requestGetLaneInfoStatus(Duration duration);

    CompletableFuture<ResGetLocationInfo.Arguments> requestGetLocationInfo(ReqGetLocationInfo.Arguments arguments);

    CompletableFuture<ResGetLocationInfo.Arguments> requestGetLocationInfo(ReqGetLocationInfo.Arguments arguments, Duration duration);

    CompletableFuture<ResGetLocationInfo> requestGetLocationInfo(ReqGetLocationInfo reqGetLocationInfo);

    CompletableFuture<ResGetLocationInfo> requestGetLocationInfo(ReqGetLocationInfo reqGetLocationInfo, Duration duration);

    CompletableFuture<ResGetNaviJumpToSystemPage> requestGetNaviJumpToSystemPage();

    CompletableFuture<ResGetNaviJumpToSystemPage> requestGetNaviJumpToSystemPage(ReqGetNaviJumpToSystemPage reqGetNaviJumpToSystemPage);

    CompletableFuture<ResGetNaviJumpToSystemPage> requestGetNaviJumpToSystemPage(ReqGetNaviJumpToSystemPage reqGetNaviJumpToSystemPage, Duration duration);

    CompletableFuture<ResGetNaviJumpToSystemPage> requestGetNaviJumpToSystemPage(Duration duration);

    CompletableFuture<ResGetNaviMuteMode> requestGetNaviMuteMode();

    CompletableFuture<ResGetNaviMuteMode> requestGetNaviMuteMode(ReqGetNaviMuteMode reqGetNaviMuteMode);

    CompletableFuture<ResGetNaviMuteMode> requestGetNaviMuteMode(ReqGetNaviMuteMode reqGetNaviMuteMode, Duration duration);

    CompletableFuture<ResGetNaviMuteMode> requestGetNaviMuteMode(Duration duration);

    CompletableFuture<ResGetNaviMuteStatus> requestGetNaviMuteStatus();

    CompletableFuture<ResGetNaviMuteStatus> requestGetNaviMuteStatus(ReqGetNaviMuteStatus reqGetNaviMuteStatus);

    CompletableFuture<ResGetNaviMuteStatus> requestGetNaviMuteStatus(ReqGetNaviMuteStatus reqGetNaviMuteStatus, Duration duration);

    CompletableFuture<ResGetNaviMuteStatus> requestGetNaviMuteStatus(Duration duration);

    CompletableFuture<ResGetNaviSettingStateStatus> requestGetNaviSettingStateStatus();

    CompletableFuture<ResGetNaviSettingStateStatus> requestGetNaviSettingStateStatus(ReqGetNaviSettingStateStatus reqGetNaviSettingStateStatus);

    CompletableFuture<ResGetNaviSettingStateStatus> requestGetNaviSettingStateStatus(ReqGetNaviSettingStateStatus reqGetNaviSettingStateStatus, Duration duration);

    CompletableFuture<ResGetNaviSettingStateStatus> requestGetNaviSettingStateStatus(Duration duration);

    CompletableFuture<ResGetNotifyHeadingStatus> requestGetNotifyHeadingStatus();

    CompletableFuture<ResGetNotifyHeadingStatus> requestGetNotifyHeadingStatus(ReqGetNotifyHeadingStatus reqGetNotifyHeadingStatus);

    CompletableFuture<ResGetNotifyHeadingStatus> requestGetNotifyHeadingStatus(ReqGetNotifyHeadingStatus reqGetNotifyHeadingStatus, Duration duration);

    CompletableFuture<ResGetNotifyHeadingStatus> requestGetNotifyHeadingStatus(Duration duration);

    CompletableFuture<ResGetPostCustomEvent> requestGetPostCustomEvent();

    CompletableFuture<ResGetPostCustomEvent> requestGetPostCustomEvent(ReqGetPostCustomEvent reqGetPostCustomEvent);

    CompletableFuture<ResGetPostCustomEvent> requestGetPostCustomEvent(ReqGetPostCustomEvent reqGetPostCustomEvent, Duration duration);

    CompletableFuture<ResGetPostCustomEvent> requestGetPostCustomEvent(Duration duration);

    CompletableFuture<ResGetPostPageTransitionEvent> requestGetPostPageTransitionEvent();

    CompletableFuture<ResGetPostPageTransitionEvent> requestGetPostPageTransitionEvent(ReqGetPostPageTransitionEvent reqGetPostPageTransitionEvent);

    CompletableFuture<ResGetPostPageTransitionEvent> requestGetPostPageTransitionEvent(ReqGetPostPageTransitionEvent reqGetPostPageTransitionEvent, Duration duration);

    CompletableFuture<ResGetPostPageTransitionEvent> requestGetPostPageTransitionEvent(Duration duration);

    CompletableFuture<ResGetPostServiceEvent> requestGetPostServiceEvent();

    CompletableFuture<ResGetPostServiceEvent> requestGetPostServiceEvent(ReqGetPostServiceEvent reqGetPostServiceEvent);

    CompletableFuture<ResGetPostServiceEvent> requestGetPostServiceEvent(ReqGetPostServiceEvent reqGetPostServiceEvent, Duration duration);

    CompletableFuture<ResGetPostServiceEvent> requestGetPostServiceEvent(Duration duration);

    CompletableFuture<ResGetPostUITouchEvent> requestGetPostUITouchEvent();

    CompletableFuture<ResGetPostUITouchEvent> requestGetPostUITouchEvent(ReqGetPostUITouchEvent reqGetPostUITouchEvent);

    CompletableFuture<ResGetPostUITouchEvent> requestGetPostUITouchEvent(ReqGetPostUITouchEvent reqGetPostUITouchEvent, Duration duration);

    CompletableFuture<ResGetPostUITouchEvent> requestGetPostUITouchEvent(Duration duration);

    CompletableFuture<ResGetReportLocationInfo> requestGetReportLocationInfo();

    CompletableFuture<ResGetReportLocationInfo> requestGetReportLocationInfo(ReqGetReportLocationInfo reqGetReportLocationInfo);

    CompletableFuture<ResGetReportLocationInfo> requestGetReportLocationInfo(ReqGetReportLocationInfo reqGetReportLocationInfo, Duration duration);

    CompletableFuture<ResGetReportLocationInfo> requestGetReportLocationInfo(Duration duration);

    CompletableFuture<ResGetRequestPrivacyMode> requestGetRequestPrivacyMode();

    CompletableFuture<ResGetRequestPrivacyMode> requestGetRequestPrivacyMode(ReqGetRequestPrivacyMode reqGetRequestPrivacyMode);

    CompletableFuture<ResGetRequestPrivacyMode> requestGetRequestPrivacyMode(ReqGetRequestPrivacyMode reqGetRequestPrivacyMode, Duration duration);

    CompletableFuture<ResGetRequestPrivacyMode> requestGetRequestPrivacyMode(Duration duration);

    CompletableFuture<ResGetResponseDirectNavigation> requestGetResponseDirectNavigation();

    CompletableFuture<ResGetResponseDirectNavigation> requestGetResponseDirectNavigation(ReqGetResponseDirectNavigation reqGetResponseDirectNavigation);

    CompletableFuture<ResGetResponseDirectNavigation> requestGetResponseDirectNavigation(ReqGetResponseDirectNavigation reqGetResponseDirectNavigation, Duration duration);

    CompletableFuture<ResGetResponseDirectNavigation> requestGetResponseDirectNavigation(Duration duration);

    CompletableFuture<ResGetRouteOverviewState> requestGetRouteOverviewState();

    CompletableFuture<ResGetRouteOverviewState> requestGetRouteOverviewState(ReqGetRouteOverviewState reqGetRouteOverviewState);

    CompletableFuture<ResGetRouteOverviewState> requestGetRouteOverviewState(ReqGetRouteOverviewState reqGetRouteOverviewState, Duration duration);

    CompletableFuture<ResGetRouteOverviewState> requestGetRouteOverviewState(Duration duration);

    CompletableFuture<ResGetRoutePreference> requestGetRoutePreference();

    CompletableFuture<ResGetRoutePreference> requestGetRoutePreference(ReqGetRoutePreference reqGetRoutePreference);

    CompletableFuture<ResGetRoutePreference> requestGetRoutePreference(ReqGetRoutePreference reqGetRoutePreference, Duration duration);

    CompletableFuture<ResGetRoutePreference> requestGetRoutePreference(Duration duration);

    CompletableFuture<ResGetSpeedLimitStatus> requestGetSpeedLimitStatus();

    CompletableFuture<ResGetSpeedLimitStatus> requestGetSpeedLimitStatus(ReqGetSpeedLimitStatus reqGetSpeedLimitStatus);

    CompletableFuture<ResGetSpeedLimitStatus> requestGetSpeedLimitStatus(ReqGetSpeedLimitStatus reqGetSpeedLimitStatus, Duration duration);

    CompletableFuture<ResGetSpeedLimitStatus> requestGetSpeedLimitStatus(Duration duration);

    CompletableFuture<ResGetSwitchNavigationLanguageSetting> requestGetSwitchNavigationLanguageSetting();

    CompletableFuture<ResGetSwitchNavigationLanguageSetting> requestGetSwitchNavigationLanguageSetting(ReqGetSwitchNavigationLanguageSetting reqGetSwitchNavigationLanguageSetting);

    CompletableFuture<ResGetSwitchNavigationLanguageSetting> requestGetSwitchNavigationLanguageSetting(ReqGetSwitchNavigationLanguageSetting reqGetSwitchNavigationLanguageSetting, Duration duration);

    CompletableFuture<ResGetSwitchNavigationLanguageSetting> requestGetSwitchNavigationLanguageSetting(Duration duration);

    CompletableFuture<ResGetWaypointList> requestGetWaypointList();

    CompletableFuture<ResGetWaypointList> requestGetWaypointList(ReqGetWaypointList reqGetWaypointList);

    CompletableFuture<ResGetWaypointList> requestGetWaypointList(ReqGetWaypointList reqGetWaypointList, Duration duration);

    CompletableFuture<ResGetWaypointList> requestGetWaypointList(Duration duration);

    CompletableFuture<ResRequestAlongRouteSearch.Arguments> requestRequestAlongRouteSearch(ReqRequestAlongRouteSearch.Arguments arguments);

    CompletableFuture<ResRequestAlongRouteSearch.Arguments> requestRequestAlongRouteSearch(ReqRequestAlongRouteSearch.Arguments arguments, Duration duration);

    CompletableFuture<ResRequestAlongRouteSearch> requestRequestAlongRouteSearch(ReqRequestAlongRouteSearch reqRequestAlongRouteSearch);

    CompletableFuture<ResRequestAlongRouteSearch> requestRequestAlongRouteSearch(ReqRequestAlongRouteSearch reqRequestAlongRouteSearch, Duration duration);

    CompletableFuture<ResRequestCalcPlanningRoute.Arguments> requestRequestCalcPlanningRoute(ReqRequestCalcPlanningRoute.Arguments arguments);

    CompletableFuture<ResRequestCalcPlanningRoute.Arguments> requestRequestCalcPlanningRoute(ReqRequestCalcPlanningRoute.Arguments arguments, Duration duration);

    CompletableFuture<ResRequestCalcPlanningRoute> requestRequestCalcPlanningRoute(ReqRequestCalcPlanningRoute reqRequestCalcPlanningRoute);

    CompletableFuture<ResRequestCalcPlanningRoute> requestRequestCalcPlanningRoute(ReqRequestCalcPlanningRoute reqRequestCalcPlanningRoute, Duration duration);

    CompletableFuture<ResRequestDTDAndETA> requestRequestDTDAndETA();

    CompletableFuture<ResRequestDTDAndETA> requestRequestDTDAndETA(ReqRequestDTDAndETA reqRequestDTDAndETA);

    CompletableFuture<ResRequestDTDAndETA> requestRequestDTDAndETA(ReqRequestDTDAndETA reqRequestDTDAndETA, Duration duration);

    CompletableFuture<ResRequestDTDAndETA> requestRequestDTDAndETA(Duration duration);

    CompletableFuture<ResRequestDestinationHistoryList> requestRequestDestinationHistoryList();

    CompletableFuture<ResRequestDestinationHistoryList> requestRequestDestinationHistoryList(ReqRequestDestinationHistoryList reqRequestDestinationHistoryList);

    CompletableFuture<ResRequestDestinationHistoryList> requestRequestDestinationHistoryList(ReqRequestDestinationHistoryList reqRequestDestinationHistoryList, Duration duration);

    CompletableFuture<ResRequestDestinationHistoryList> requestRequestDestinationHistoryList(Duration duration);

    CompletableFuture<ResRequestGetDestinationHistoryList> requestRequestGetDestinationHistoryList();

    CompletableFuture<ResRequestGetDestinationHistoryList> requestRequestGetDestinationHistoryList(ReqRequestGetDestinationHistoryList reqRequestGetDestinationHistoryList);

    CompletableFuture<ResRequestGetDestinationHistoryList> requestRequestGetDestinationHistoryList(ReqRequestGetDestinationHistoryList reqRequestGetDestinationHistoryList, Duration duration);

    CompletableFuture<ResRequestGetDestinationHistoryList> requestRequestGetDestinationHistoryList(Duration duration);

    CompletableFuture<ResRequestGoHomeOrCompany.Arguments> requestRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany.Arguments arguments);

    CompletableFuture<ResRequestGoHomeOrCompany.Arguments> requestRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany.Arguments arguments, Duration duration);

    CompletableFuture<ResRequestGoHomeOrCompany> requestRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany reqRequestGoHomeOrCompany);

    CompletableFuture<ResRequestGoHomeOrCompany> requestRequestGoHomeOrCompany(ReqRequestGoHomeOrCompany reqRequestGoHomeOrCompany, Duration duration);

    CompletableFuture<ResRequestHighlightedRoute.Arguments> requestRequestHighlightedRoute(ReqRequestHighlightedRoute.Arguments arguments);

    CompletableFuture<ResRequestHighlightedRoute.Arguments> requestRequestHighlightedRoute(ReqRequestHighlightedRoute.Arguments arguments, Duration duration);

    CompletableFuture<ResRequestHighlightedRoute> requestRequestHighlightedRoute(ReqRequestHighlightedRoute reqRequestHighlightedRoute);

    CompletableFuture<ResRequestHighlightedRoute> requestRequestHighlightedRoute(ReqRequestHighlightedRoute reqRequestHighlightedRoute, Duration duration);

    CompletableFuture<ResRequestIntoSearchPage> requestRequestIntoSearchPage();

    CompletableFuture<ResRequestIntoSearchPage> requestRequestIntoSearchPage(ReqRequestIntoSearchPage reqRequestIntoSearchPage);

    CompletableFuture<ResRequestIntoSearchPage> requestRequestIntoSearchPage(ReqRequestIntoSearchPage reqRequestIntoSearchPage, Duration duration);

    CompletableFuture<ResRequestIntoSearchPage> requestRequestIntoSearchPage(Duration duration);

    CompletableFuture<ResRequestJumpToSetHomeCompany.Arguments> requestRequestJumpToSetHomeCompany(ReqRequestJumpToSetHomeCompany.Arguments arguments);

    CompletableFuture<ResRequestJumpToSetHomeCompany.Arguments> requestRequestJumpToSetHomeCompany(ReqRequestJumpToSetHomeCompany.Arguments arguments, Duration duration);

    CompletableFuture<ResRequestJumpToSetHomeCompany> requestRequestJumpToSetHomeCompany(ReqRequestJumpToSetHomeCompany reqRequestJumpToSetHomeCompany);

    CompletableFuture<ResRequestJumpToSetHomeCompany> requestRequestJumpToSetHomeCompany(ReqRequestJumpToSetHomeCompany reqRequestJumpToSetHomeCompany, Duration duration);

    CompletableFuture<ResRequestJumpToViewPage.Arguments> requestRequestJumpToViewPage(ReqRequestJumpToViewPage.Arguments arguments);

    CompletableFuture<ResRequestJumpToViewPage.Arguments> requestRequestJumpToViewPage(ReqRequestJumpToViewPage.Arguments arguments, Duration duration);

    CompletableFuture<ResRequestJumpToViewPage> requestRequestJumpToViewPage(ReqRequestJumpToViewPage reqRequestJumpToViewPage);

    CompletableFuture<ResRequestJumpToViewPage> requestRequestJumpToViewPage(ReqRequestJumpToViewPage reqRequestJumpToViewPage, Duration duration);

    CompletableFuture<ResRequestOpenFavorite> requestRequestOpenFavorite();

    CompletableFuture<ResRequestOpenFavorite> requestRequestOpenFavorite(ReqRequestOpenFavorite reqRequestOpenFavorite);

    CompletableFuture<ResRequestOpenFavorite> requestRequestOpenFavorite(ReqRequestOpenFavorite reqRequestOpenFavorite, Duration duration);

    CompletableFuture<ResRequestOpenFavorite> requestRequestOpenFavorite(Duration duration);

    CompletableFuture<ResRequestOpenMap.Arguments> requestRequestOpenMap(ReqRequestOpenMap.Arguments arguments);

    CompletableFuture<ResRequestOpenMap.Arguments> requestRequestOpenMap(ReqRequestOpenMap.Arguments arguments, Duration duration);

    CompletableFuture<ResRequestOpenMap> requestRequestOpenMap(ReqRequestOpenMap reqRequestOpenMap);

    CompletableFuture<ResRequestOpenMap> requestRequestOpenMap(ReqRequestOpenMap reqRequestOpenMap, Duration duration);

    CompletableFuture<ResRequestSearchAround.Arguments> requestRequestSearchAround(ReqRequestSearchAround.Arguments arguments);

    CompletableFuture<ResRequestSearchAround.Arguments> requestRequestSearchAround(ReqRequestSearchAround.Arguments arguments, Duration duration);

    CompletableFuture<ResRequestSearchAround> requestRequestSearchAround(ReqRequestSearchAround reqRequestSearchAround);

    CompletableFuture<ResRequestSearchAround> requestRequestSearchAround(ReqRequestSearchAround reqRequestSearchAround, Duration duration);

    CompletableFuture<ResRequestSearchKeyword.Arguments> requestRequestSearchKeyword(ReqRequestSearchKeyword.Arguments arguments);

    CompletableFuture<ResRequestSearchKeyword.Arguments> requestRequestSearchKeyword(ReqRequestSearchKeyword.Arguments arguments, Duration duration);

    CompletableFuture<ResRequestSearchKeyword> requestRequestSearchKeyword(ReqRequestSearchKeyword reqRequestSearchKeyword);

    CompletableFuture<ResRequestSearchKeyword> requestRequestSearchKeyword(ReqRequestSearchKeyword reqRequestSearchKeyword, Duration duration);

    CompletableFuture<ResRequestSetFavorite.Arguments> requestRequestSetFavorite(ReqRequestSetFavorite.Arguments arguments);

    CompletableFuture<ResRequestSetFavorite.Arguments> requestRequestSetFavorite(ReqRequestSetFavorite.Arguments arguments, Duration duration);

    CompletableFuture<ResRequestSetFavorite> requestRequestSetFavorite(ReqRequestSetFavorite reqRequestSetFavorite);

    CompletableFuture<ResRequestSetFavorite> requestRequestSetFavorite(ReqRequestSetFavorite reqRequestSetFavorite, Duration duration);

    CompletableFuture<ResRequestSetRoutePreference.Arguments> requestRequestSetRoutePreference(ReqRequestSetRoutePreference.Arguments arguments);

    CompletableFuture<ResRequestSetRoutePreference.Arguments> requestRequestSetRoutePreference(ReqRequestSetRoutePreference.Arguments arguments, Duration duration);

    CompletableFuture<ResRequestSetRoutePreference> requestRequestSetRoutePreference(ReqRequestSetRoutePreference reqRequestSetRoutePreference);

    CompletableFuture<ResRequestSetRoutePreference> requestRequestSetRoutePreference(ReqRequestSetRoutePreference reqRequestSetRoutePreference, Duration duration);

    CompletableFuture<ResRequestSetWaypoint.Arguments> requestRequestSetWaypoint(ReqRequestSetWaypoint.Arguments arguments);

    CompletableFuture<ResRequestSetWaypoint.Arguments> requestRequestSetWaypoint(ReqRequestSetWaypoint.Arguments arguments, Duration duration);

    CompletableFuture<ResRequestSetWaypoint> requestRequestSetWaypoint(ReqRequestSetWaypoint reqRequestSetWaypoint);

    CompletableFuture<ResRequestSetWaypoint> requestRequestSetWaypoint(ReqRequestSetWaypoint reqRequestSetWaypoint, Duration duration);

    CompletableFuture<ResRequestSimulation.Arguments> requestRequestSimulation(ReqRequestSimulation.Arguments arguments);

    CompletableFuture<ResRequestSimulation.Arguments> requestRequestSimulation(ReqRequestSimulation.Arguments arguments, Duration duration);

    CompletableFuture<ResRequestSimulation> requestRequestSimulation(ReqRequestSimulation reqRequestSimulation);

    CompletableFuture<ResRequestSimulation> requestRequestSimulation(ReqRequestSimulation reqRequestSimulation, Duration duration);

    CompletableFuture<ResRequestStartGuidance> requestRequestStartGuidance();

    CompletableFuture<ResRequestStartGuidance> requestRequestStartGuidance(ReqRequestStartGuidance reqRequestStartGuidance);

    CompletableFuture<ResRequestStartGuidance> requestRequestStartGuidance(ReqRequestStartGuidance reqRequestStartGuidance, Duration duration);

    CompletableFuture<ResRequestStartGuidance> requestRequestStartGuidance(Duration duration);

    CompletableFuture<ResRequestStopGuidance> requestRequestStopGuidance();

    CompletableFuture<ResRequestStopGuidance> requestRequestStopGuidance(ReqRequestStopGuidance reqRequestStopGuidance);

    CompletableFuture<ResRequestStopGuidance> requestRequestStopGuidance(ReqRequestStopGuidance reqRequestStopGuidance, Duration duration);

    CompletableFuture<ResRequestStopGuidance> requestRequestStopGuidance(Duration duration);

    CompletableFuture<ResSetIVIConfiguration.Arguments> requestSetIVIConfiguration(ReqSetIVIConfiguration.Arguments arguments);

    CompletableFuture<ResSetIVIConfiguration.Arguments> requestSetIVIConfiguration(ReqSetIVIConfiguration.Arguments arguments, Duration duration);

    CompletableFuture<ResSetIVIConfiguration> requestSetIVIConfiguration(ReqSetIVIConfiguration reqSetIVIConfiguration);

    CompletableFuture<ResSetIVIConfiguration> requestSetIVIConfiguration(ReqSetIVIConfiguration reqSetIVIConfiguration, Duration duration);

    CompletableFuture<ResSetNaviMuteMode.Arguments> requestSetNaviMuteMode(ReqSetNaviMuteMode.Arguments arguments);

    CompletableFuture<ResSetNaviMuteMode.Arguments> requestSetNaviMuteMode(ReqSetNaviMuteMode.Arguments arguments, Duration duration);

    CompletableFuture<ResSetNaviMuteMode> requestSetNaviMuteMode(ReqSetNaviMuteMode reqSetNaviMuteMode);

    CompletableFuture<ResSetNaviMuteMode> requestSetNaviMuteMode(ReqSetNaviMuteMode reqSetNaviMuteMode, Duration duration);

    CompletableFuture<ResSwitchRouteOverviewInfo.Arguments> requestSwitchRouteOverviewInfo(ReqSwitchRouteOverviewInfo.Arguments arguments);

    CompletableFuture<ResSwitchRouteOverviewInfo.Arguments> requestSwitchRouteOverviewInfo(ReqSwitchRouteOverviewInfo.Arguments arguments, Duration duration);

    CompletableFuture<ResSwitchRouteOverviewInfo> requestSwitchRouteOverviewInfo(ReqSwitchRouteOverviewInfo reqSwitchRouteOverviewInfo);

    CompletableFuture<ResSwitchRouteOverviewInfo> requestSwitchRouteOverviewInfo(ReqSwitchRouteOverviewInfo reqSwitchRouteOverviewInfo, Duration duration);
}
