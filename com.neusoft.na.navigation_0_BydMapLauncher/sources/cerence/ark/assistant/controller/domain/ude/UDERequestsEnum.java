package cerence.ark.assistant.controller.domain.ude;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface UDERequestsEnum {
    public static final String CLOSE_MAP = "closeMap";
    public static final String CONTROL_TRAFFIC_INFO_VIEW = "controlTrafficInfoView";
    public static final String EXIT_NAVIGATION = "exitNavigation";
    public static final String GET_ADDRESS_BY_TYPE = "getAddressByType";
    public static final String GET_AVOID_POINTS = "getAvoidPoints";
    public static final String GET_DISTANCE = "getDistance";
    public static final String GET_ETA = "getETA";
    public static final String GET_MAP_DISPLAY_MODE = "getMapDisplayMode";
    public static final String GET_MAX_WAY_POINTS = "getMaxWayPoints";
    public static final String GET_RECENT_LIST = "getRecentList";
    public static final String GET_ROUTE_OPTION = "getRouteOption";
    public static final String GET_ROUTE_OPTION_LIST = "getRouteOptionList";
    public static final String GET_WAY_POINTS = "getWayPoints";
    public static final String IS_MAP_VISIBLE = "isMapVisible";
    public static final String IS_NAVIGATING = "isNavigating";
    public static final String NAVIGATE_TO_DESTINATION = "navigateToDestination";
    public static final String OPEN_MAP = "openMap";
    public static final String SEARCH_PLACE_FOR_ROUTE_PLANNING = "searchPlaceForRoutePlanning";
    public static final String SEARCH_POI_BY_NAME = "searchPoiByName";
    public static final String SEARCH_POI_CATEGORY_BY_ADDRESS = "searchPoiCategoryByAddress";
    public static final String SET_DEFAULT_ADDRESS_BY_TYPE = "setDefaultAddressByType";
    public static final String SET_DEFAULT_ROUTE_OPTION = "setDefaultRouteOption";
    public static final String SET_MAP_DISPLAY_MODE = "setMapDisplayMode";
    public static final String ZOOM_MAP = "zoomMap";
}
