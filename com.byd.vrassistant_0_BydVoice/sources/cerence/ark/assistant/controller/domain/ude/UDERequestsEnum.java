package cerence.ark.assistant.controller.domain.ude;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface UDERequestsEnum {
    public static final String ADD_TO_FAVORITES = "addToFavorites";
    public static final String CHANGE_ROUTE_OPTION = "changeRouteOption";
    public static final String CHANGE_WAYPOINTS = "changeWaypoints";
    public static final String CHECK_DUPLICATE_ROUTE_PLACE = "checkDuplicateRoutePlace";
    public static final String CHECK_NAVI_FUNCTION_AVAILABILITY = "checkNaviFunctionAvailability";
    public static final String CLOSE_FAVORITES_LIST = "closeFavoritesList";
    public static final String GET_ADDRESS_BY_TYPE = "getAddressByType";
    public static final String GET_DISTANCE = "getDistance";
    public static final String GET_ETA = "getETA";
    public static final String GET_FAVORITES_LIST_STATUS = "getFavoritesListStatus";
    public static final String GET_MAX_WAYPOINTS = "getMaxWaypoints";
    public static final String GET_RECENT_LIST = "getRecentList";
    public static final String GET_ROUTES = "getRoutes";
    public static final String GET_ROUTE_OPTION = "getRouteOption";
    public static final String GET_SUPPORTED_ROUTE_OPTIONS = "getSupportedRouteOptions";
    public static final String GET_UNFINISHED_ROUTE = "getUnfinishedRoute";
    public static final String GET_WAYPOINTS = "getWaypoints";
    public static final String IS_DETAIL_PAGE_SUPPORTED = "isDetailPageSupported";
    public static final String IS_NAVIGATING = "isNavigating";
    public static final String OPEN_FAVORITES_LIST = "openFavoritesList";
    public static final String OPEN_MAP = "openMap";
    public static final String REPLACE_DESTINATION = "replaceDestination";
    public static final String RESUME_NAVIGATION = "resumeNavigation";
    public static final String ROUTE_OVERVIEW = "routeOverview";
    public static final String SEARCH_PLACE_BY_CATEGORY = "searchPlaceByCategory";
    public static final String SEARCH_PLACE_BY_NAME = "searchPlaceByName";
    public static final String SET_ADDRESS_BY_TYPE = "setAddressByType";
    public static final String SET_DEFAULT_ROUTE_OPTION = "setDefaultRouteOption";
    public static final String SET_MAP_OPTION = "setMapOption";
    public static final String START_GUIDANCE = "startGuidance";
    public static final String STOP_GUIDANCE = "stopGuidance";
    public static final String ZOOM_MAP = "zoomMap";
}
