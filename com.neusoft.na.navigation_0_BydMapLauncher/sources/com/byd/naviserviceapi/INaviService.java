package com.byd.naviserviceapi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.naviserviceapi.INaviSearchAlongCallback;
import com.byd.naviserviceapi.INaviSearchPOICallback;
import com.byd.naviserviceapi.INaviServiceCallback;
import com.byd.naviserviceapi.INaviStateCallback;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface INaviService extends IInterface {
    public static final int MAP_MODE_2D_FRONT_UP = 0;
    public static final int MAP_MODE_2D_NORTHWARD = 1;
    public static final int MAP_MODE_3D_FRONT_UP = 2;
    public static final int MAP_STATUS_BACKGROUND = 2;
    public static final int MAP_STATUS_FRONT = 1;
    public static final int MAP_STATUS_NO_LAUNCH = 0;
    public static final int MAP_STATUS_UNKNOWN = -1;
    public static final int NAVI_AUTH_INVALID = -4;
    public static final int NAVI_CODE_ALREADY_EXPECTED = 2;
    public static final int NAVI_CODE_ALREADY_MAX = 3;
    public static final int NAVI_CODE_ALREADY_MIN = 4;
    public static final int NAVI_CODE_FAVORITE_LIST_FULL = 7;
    public static final int NAVI_CODE_NOT_SUPPORT_MAP = -2;
    public static final int NAVI_CODE_NO_ROUTE_GUIDANCE = 6;
    public static final int NAVI_CODE_NO_UNFINISHEDROUTE = 8;
    public static final int NAVI_CODE_OK = 1;
    public static final int NAVI_CODE_OK_NOTROUTE = 11;
    public static final int NAVI_CODE_OUT_OF_RANGE = 5;
    public static final int NAVI_CODE_RESUME_NEEDROUTE = 9;
    public static final int NAVI_CODE_RESUME_NOTROUTE = 10;
    public static final int NAVI_CODE_TIMEOUT = -3;
    public static final int NAVI_ERROR = -1;
    public static final int NAVI_STAT_APP_BACKGROUND = 11;
    public static final int NAVI_STAT_APP_FINISH = 9;
    public static final int NAVI_STAT_APP_FOREGROUND = 10;
    public static final int NAVI_STAT_APP_START_FINISH = 8;
    public static final int NAVI_STAT_DAYNIGHT_STATUS_DAY = 30;
    public static final int NAVI_STAT_DAYNIGHT_STATUS_NIGHT = 32;
    public static final int NAVI_STAT_FAVORITE_CLOSE = 47;
    public static final int NAVI_STAT_FAVORITE_OPEN = 46;
    public static final int NAVI_STAT_GUIDE_START = 16;
    public static final int NAVI_STAT_GUIDE_STOP = 17;
    public static final int NAVI_STAT_OPEN_HISTORYDESTINATION = 49;
    public static final int NAVI_STAT_OPEN_ROUTEOPTION = 50;
    public static final int NAVI_STAT_OVERVIEW_IN = 2;
    public static final int NAVI_STAT_OVERVIEW_OUT = 1;
    public static final int NAVI_STAT_POIDETAIL_CLOSE = 57;
    public static final int NAVI_STAT_POIDETAIL_ERROR = 58;
    public static final int NAVI_STAT_POIDETAIL_ROUTE_FINISH = 56;
    public static final int NAVI_STAT_POIDETAIL_ROUTING = 55;
    public static final int NAVI_STAT_SETTING_CLOSE = 45;
    public static final int NAVI_STAT_SETTING_OPEN = 44;
    public static final int NAVI_STAT_TRAFFIC_STATUS_OFF = 24;
    public static final int NAVI_STAT_TRAFFIC_STATUS_ON = 23;
    public static final int NAVI_STAT_VIEW_MODE_2D_FRONT_UP = 27;
    public static final int NAVI_STAT_VIEW_MODE_2D_NORTHWARD = 26;
    public static final int NAVI_STAT_VIEW_MODE_3D_FRONT_UP = 25;
    public static final int NAVI_STAT_ZOOM_IN = 18;
    public static final int NAVI_STAT_ZOOM_OUT = 19;
    public static final int NAVI_STAT_ZOOM_STATUS_MAX = 21;
    public static final int NAVI_STAT_ZOOM_STATUS_MIN = 22;
    public static final int RouteOption_Avoid_Highway = 3;
    public static final int RouteOption_Avoid_Tolls = 4;
    public static final int RouteOption_Avoid_Tunnel = 6;
    public static final int RouteOption_Avoid_Unpaved = 5;
    public static final int RouteOption_Avoid_carpool = 7;
    public static final int RouteOption_Avoid_congestion = 10;
    public static final int RouteOption_Avoid_country_border = 8;
    public static final int RouteOption_Avoid_ferries = 9;
    public static final int RouteOption_Cheap = 2;
    public static final int RouteOption_Fastest = 1;
    public static final int RouteOption_Highway = 13;
    public static final int RouteOption_No_Traffic = 11;
    public static final int RouteOption_Recommended = 12;
    public static final int RouteOption_Shortest = 14;
    public static final int RouteOption_Unspecified = 0;
    public static final String SupportedOption_2D_HEAD = "MODE_2D_HEAD_UP";
    public static final String SupportedOption_2D_NORTH = "MODE_2D_NORTH_UP";
    public static final String SupportedOption_3D_HEAD = "MODE_3D_HEAD_UP";
    public static final String SupportedOption_DAY_MODE = "MODE_DAY_MODE";
    public static final String SupportedOption_NIGHT_MODE = "MODE_NIGHT_MODE";
    public static final String SupportedOption_OPENFAVORITE = "MODE_OPENFAVORITE";
    public static final String SupportedOption_OPENSETTING = "MODE_OPENSETTING";
    public static final String SupportedOption_OVERVIEW = "MODE_OVERVIEW";
    public static final String SupportedOption_TRAFFIC = "MODE_TRAFFIC";

    public static class Default implements INaviService {
        @Override // com.byd.naviserviceapi.INaviService
        public int addViaPosition(List<Place> places) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public boolean checkFavoriteEmpty() throws RemoteException {
            return false;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public boolean checkFavoriteFull() throws RemoteException {
            return false;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public boolean checkHistoryDestinationEmpty() throws RemoteException {
            return false;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public boolean checkNaviGuide() throws RemoteException {
            return false;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public boolean checkTTSPlaying() throws RemoteException {
            return false;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public Place getCompany() throws RemoteException {
            return null;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public Place getCurrentPlace() throws RemoteException {
            return null;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int getDayNightMode() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int getFavoriteStatus() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public Place getHome() throws RemoteException {
            return null;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int getMapAppStatus() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int getMapSettingStatus() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int getNaviMapMode() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int getNaviMaxViaCount() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public boolean getNaviSwitchFullView() throws RemoteException {
            return false;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int getPOIDetailStatus() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int getRemoteVersionCode() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public List<String> getSupportedRouteOptions() throws RemoteException {
            return null;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int getTrafficStatus() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int getUnfinishedRoute() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int hidePlaceMark() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int highlightPlaceMark(List<Place> places) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public boolean registerCallback(INaviServiceCallback cb) throws RemoteException {
            return false;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public boolean registerStateCallback(INaviStateCallback cb) throws RemoteException {
            return false;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqAppendVia(Place poi) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqClosePOIDetail() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqModifyDestination(Place poi) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviAddFavorite() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviAddFavoritePlace(Place place) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviAlongTheWaySearch(int categoryId, INaviSearchAlongCallback cb) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviBackToMap() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviGetRouteOption() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviGuideStart(String routeId) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviGuideStop() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviInfo() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviLaunchMap() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviMapMode(int mode) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviNextViaTimeDistance() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviRemainTimeDistance() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviRouteFullView(List<RouteInfo> routes, int highlightIndex) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviSetRouteOption(int routeOption) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviSupportedOption() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviSwitchFullView(boolean fullMode) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public List<Place> reqNaviViaPosition() throws RemoteException {
            return null;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviZoomIn(boolean toMax) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviZoomOut(boolean toMin) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqOpenHistoryDestination() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqOpenPOIDetail(Place poi) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqOpenRouteOption() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqOpenSetAddress(int type) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqPOISearchByCategories(int categoryId, int locationType, String placename, INaviSearchPOICallback cb) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqPOISearchByKeyword(String keywords, INaviSearchPOICallback cb) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqRoutePlanning(Place destination, List<Place> via, int routeOption) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqSetAddress(int addressType, Place place) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqSmartNaviStart(Place poi) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqSmartRoutePlanning(Place destination, List<Place> via, int routeOption) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqSwitchDayNightMode(int status) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqSwitchFavorite(int status) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqSwitchMapSetting(int status) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqSwitchTraffic(int status) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int resumeNavigation() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int showPlaceMark(List<Place> places) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public boolean unregisterCallback(INaviServiceCallback cb) throws RemoteException {
            return false;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public boolean unregisterStateCallback(INaviStateCallback cb) throws RemoteException {
            return false;
        }
    }

    int addViaPosition(List<Place> places) throws RemoteException;

    boolean checkFavoriteEmpty() throws RemoteException;

    boolean checkFavoriteFull() throws RemoteException;

    boolean checkHistoryDestinationEmpty() throws RemoteException;

    boolean checkNaviGuide() throws RemoteException;

    boolean checkTTSPlaying() throws RemoteException;

    Place getCompany() throws RemoteException;

    Place getCurrentPlace() throws RemoteException;

    int getDayNightMode() throws RemoteException;

    int getFavoriteStatus() throws RemoteException;

    Place getHome() throws RemoteException;

    int getMapAppStatus() throws RemoteException;

    int getMapSettingStatus() throws RemoteException;

    int getNaviMapMode() throws RemoteException;

    int getNaviMaxViaCount() throws RemoteException;

    boolean getNaviSwitchFullView() throws RemoteException;

    int getPOIDetailStatus() throws RemoteException;

    int getRemoteVersionCode() throws RemoteException;

    List<String> getSupportedRouteOptions() throws RemoteException;

    int getTrafficStatus() throws RemoteException;

    int getUnfinishedRoute() throws RemoteException;

    int hidePlaceMark() throws RemoteException;

    int highlightPlaceMark(List<Place> places) throws RemoteException;

    boolean registerCallback(INaviServiceCallback cb) throws RemoteException;

    boolean registerStateCallback(INaviStateCallback cb) throws RemoteException;

    int reqAppendVia(Place poi) throws RemoteException;

    int reqClosePOIDetail() throws RemoteException;

    int reqModifyDestination(Place poi) throws RemoteException;

    int reqNaviAddFavorite() throws RemoteException;

    int reqNaviAddFavoritePlace(Place place) throws RemoteException;

    int reqNaviAlongTheWaySearch(int categoryId, INaviSearchAlongCallback cb) throws RemoteException;

    int reqNaviBackToMap() throws RemoteException;

    int reqNaviGetRouteOption() throws RemoteException;

    int reqNaviGuideStart(String routeId) throws RemoteException;

    int reqNaviGuideStop() throws RemoteException;

    int reqNaviInfo() throws RemoteException;

    int reqNaviLaunchMap() throws RemoteException;

    int reqNaviMapMode(int mode) throws RemoteException;

    int reqNaviNextViaTimeDistance() throws RemoteException;

    int reqNaviRemainTimeDistance() throws RemoteException;

    int reqNaviRouteFullView(List<RouteInfo> routes, int highlightIndex) throws RemoteException;

    int reqNaviSetRouteOption(int routeOption) throws RemoteException;

    int reqNaviSupportedOption() throws RemoteException;

    int reqNaviSwitchFullView(boolean fullMode) throws RemoteException;

    List<Place> reqNaviViaPosition() throws RemoteException;

    int reqNaviZoomIn(boolean toMax) throws RemoteException;

    int reqNaviZoomOut(boolean toMin) throws RemoteException;

    int reqOpenHistoryDestination() throws RemoteException;

    int reqOpenPOIDetail(Place poi) throws RemoteException;

    int reqOpenRouteOption() throws RemoteException;

    int reqOpenSetAddress(int type) throws RemoteException;

    int reqPOISearchByCategories(int categoryId, int locationType, String placename, INaviSearchPOICallback cb) throws RemoteException;

    int reqPOISearchByKeyword(String keywords, INaviSearchPOICallback cb) throws RemoteException;

    int reqRoutePlanning(Place destination, List<Place> via, int routeOption) throws RemoteException;

    int reqSetAddress(int addressType, Place place) throws RemoteException;

    int reqSmartNaviStart(Place poi) throws RemoteException;

    int reqSmartRoutePlanning(Place destination, List<Place> via, int routeOption) throws RemoteException;

    int reqSwitchDayNightMode(int status) throws RemoteException;

    int reqSwitchFavorite(int status) throws RemoteException;

    int reqSwitchMapSetting(int status) throws RemoteException;

    int reqSwitchTraffic(int status) throws RemoteException;

    int resumeNavigation() throws RemoteException;

    int showPlaceMark(List<Place> places) throws RemoteException;

    boolean unregisterCallback(INaviServiceCallback cb) throws RemoteException;

    boolean unregisterStateCallback(INaviStateCallback cb) throws RemoteException;

    public static abstract class Stub extends Binder implements INaviService {
        private static final String DESCRIPTOR = "com.byd.naviserviceapi.INaviService";
        static final int TRANSACTION_addViaPosition = 40;
        static final int TRANSACTION_checkFavoriteEmpty = 34;
        static final int TRANSACTION_checkFavoriteFull = 35;
        static final int TRANSACTION_checkHistoryDestinationEmpty = 46;
        static final int TRANSACTION_checkNaviGuide = 24;
        static final int TRANSACTION_checkTTSPlaying = 53;
        static final int TRANSACTION_getCompany = 26;
        static final int TRANSACTION_getCurrentPlace = 27;
        static final int TRANSACTION_getDayNightMode = 18;
        static final int TRANSACTION_getFavoriteStatus = 32;
        static final int TRANSACTION_getHome = 25;
        static final int TRANSACTION_getMapAppStatus = 8;
        static final int TRANSACTION_getMapSettingStatus = 16;
        static final int TRANSACTION_getNaviMapMode = 10;
        static final int TRANSACTION_getNaviMaxViaCount = 29;
        static final int TRANSACTION_getNaviSwitchFullView = 14;
        static final int TRANSACTION_getPOIDetailStatus = 60;
        static final int TRANSACTION_getRemoteVersionCode = 65;
        static final int TRANSACTION_getSupportedRouteOptions = 37;
        static final int TRANSACTION_getTrafficStatus = 20;
        static final int TRANSACTION_getUnfinishedRoute = 51;
        static final int TRANSACTION_hidePlaceMark = 56;
        static final int TRANSACTION_highlightPlaceMark = 55;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_registerStateCallback = 3;
        static final int TRANSACTION_reqAppendVia = 64;
        static final int TRANSACTION_reqClosePOIDetail = 59;
        static final int TRANSACTION_reqModifyDestination = 63;
        static final int TRANSACTION_reqNaviAddFavorite = 33;
        static final int TRANSACTION_reqNaviAddFavoritePlace = 50;
        static final int TRANSACTION_reqNaviAlongTheWaySearch = 45;
        static final int TRANSACTION_reqNaviBackToMap = 7;
        static final int TRANSACTION_reqNaviGetRouteOption = 48;
        static final int TRANSACTION_reqNaviGuideStart = 22;
        static final int TRANSACTION_reqNaviGuideStop = 23;
        static final int TRANSACTION_reqNaviInfo = 39;
        static final int TRANSACTION_reqNaviLaunchMap = 6;
        static final int TRANSACTION_reqNaviMapMode = 9;
        static final int TRANSACTION_reqNaviNextViaTimeDistance = 57;
        static final int TRANSACTION_reqNaviRemainTimeDistance = 36;
        static final int TRANSACTION_reqNaviRouteFullView = 42;
        static final int TRANSACTION_reqNaviSetRouteOption = 38;
        static final int TRANSACTION_reqNaviSupportedOption = 5;
        static final int TRANSACTION_reqNaviSwitchFullView = 13;
        static final int TRANSACTION_reqNaviViaPosition = 30;
        static final int TRANSACTION_reqNaviZoomIn = 11;
        static final int TRANSACTION_reqNaviZoomOut = 12;
        static final int TRANSACTION_reqOpenHistoryDestination = 21;
        static final int TRANSACTION_reqOpenPOIDetail = 58;
        static final int TRANSACTION_reqOpenRouteOption = 47;
        static final int TRANSACTION_reqOpenSetAddress = 49;
        static final int TRANSACTION_reqPOISearchByCategories = 44;
        static final int TRANSACTION_reqPOISearchByKeyword = 43;
        static final int TRANSACTION_reqRoutePlanning = 41;
        static final int TRANSACTION_reqSetAddress = 28;
        static final int TRANSACTION_reqSmartNaviStart = 61;
        static final int TRANSACTION_reqSmartRoutePlanning = 62;
        static final int TRANSACTION_reqSwitchDayNightMode = 17;
        static final int TRANSACTION_reqSwitchFavorite = 31;
        static final int TRANSACTION_reqSwitchMapSetting = 15;
        static final int TRANSACTION_reqSwitchTraffic = 19;
        static final int TRANSACTION_resumeNavigation = 52;
        static final int TRANSACTION_showPlaceMark = 54;
        static final int TRANSACTION_unregisterCallback = 2;
        static final int TRANSACTION_unregisterStateCallback = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INaviService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INaviService)) {
                return (INaviService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRegisterCallback = registerCallback(INaviServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterCallback ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUnregisterCallback = unregisterCallback(INaviServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUnregisterCallback ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRegisterStateCallback = registerStateCallback(INaviStateCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterStateCallback ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUnregisterStateCallback = unregisterStateCallback(INaviStateCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUnregisterStateCallback ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqNaviSupportedOption = reqNaviSupportedOption();
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqNaviSupportedOption);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqNaviLaunchMap = reqNaviLaunchMap();
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqNaviLaunchMap);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqNaviBackToMap = reqNaviBackToMap();
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqNaviBackToMap);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int mapAppStatus = getMapAppStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(mapAppStatus);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqNaviMapMode = reqNaviMapMode(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqNaviMapMode);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int naviMapMode = getNaviMapMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(naviMapMode);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqNaviZoomIn = reqNaviZoomIn(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqNaviZoomIn);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqNaviZoomOut = reqNaviZoomOut(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqNaviZoomOut);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqNaviSwitchFullView = reqNaviSwitchFullView(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqNaviSwitchFullView);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean naviSwitchFullView = getNaviSwitchFullView();
                    parcel2.writeNoException();
                    parcel2.writeInt(naviSwitchFullView ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqSwitchMapSetting = reqSwitchMapSetting(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqSwitchMapSetting);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    int mapSettingStatus = getMapSettingStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(mapSettingStatus);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqSwitchDayNightMode = reqSwitchDayNightMode(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqSwitchDayNightMode);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dayNightMode = getDayNightMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(dayNightMode);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqSwitchTraffic = reqSwitchTraffic(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqSwitchTraffic);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    int trafficStatus = getTrafficStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(trafficStatus);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqOpenHistoryDestination = reqOpenHistoryDestination();
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqOpenHistoryDestination);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqNaviGuideStart = reqNaviGuideStart(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqNaviGuideStart);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqNaviGuideStop = reqNaviGuideStop();
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqNaviGuideStop);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCheckNaviGuide = checkNaviGuide();
                    parcel2.writeNoException();
                    parcel2.writeInt(zCheckNaviGuide ? 1 : 0);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    Place home = getHome();
                    parcel2.writeNoException();
                    if (home != null) {
                        parcel2.writeInt(1);
                        home.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    Place company = getCompany();
                    parcel2.writeNoException();
                    if (company != null) {
                        parcel2.writeInt(1);
                        company.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    Place currentPlace = getCurrentPlace();
                    parcel2.writeNoException();
                    if (currentPlace != null) {
                        parcel2.writeInt(1);
                        currentPlace.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqSetAddress = reqSetAddress(parcel.readInt(), parcel.readInt() != 0 ? Place.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqSetAddress);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    int naviMaxViaCount = getNaviMaxViaCount();
                    parcel2.writeNoException();
                    parcel2.writeInt(naviMaxViaCount);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<Place> listReqNaviViaPosition = reqNaviViaPosition();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(listReqNaviViaPosition);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqSwitchFavorite = reqSwitchFavorite(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqSwitchFavorite);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    int favoriteStatus = getFavoriteStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(favoriteStatus);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqNaviAddFavorite = reqNaviAddFavorite();
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqNaviAddFavorite);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCheckFavoriteEmpty = checkFavoriteEmpty();
                    parcel2.writeNoException();
                    parcel2.writeInt(zCheckFavoriteEmpty ? 1 : 0);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCheckFavoriteFull = checkFavoriteFull();
                    parcel2.writeNoException();
                    parcel2.writeInt(zCheckFavoriteFull ? 1 : 0);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqNaviRemainTimeDistance = reqNaviRemainTimeDistance();
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqNaviRemainTimeDistance);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> supportedRouteOptions = getSupportedRouteOptions();
                    parcel2.writeNoException();
                    parcel2.writeStringList(supportedRouteOptions);
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqNaviSetRouteOption = reqNaviSetRouteOption(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqNaviSetRouteOption);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqNaviInfo = reqNaviInfo();
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqNaviInfo);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iAddViaPosition = addViaPosition(parcel.createTypedArrayList(Place.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(iAddViaPosition);
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqRoutePlanning = reqRoutePlanning(parcel.readInt() != 0 ? Place.CREATOR.createFromParcel(parcel) : null, parcel.createTypedArrayList(Place.CREATOR), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqRoutePlanning);
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqNaviRouteFullView = reqNaviRouteFullView(parcel.createTypedArrayList(RouteInfo.CREATOR), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqNaviRouteFullView);
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqPOISearchByKeyword = reqPOISearchByKeyword(parcel.readString(), INaviSearchPOICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqPOISearchByKeyword);
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqPOISearchByCategories = reqPOISearchByCategories(parcel.readInt(), parcel.readInt(), parcel.readString(), INaviSearchPOICallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqPOISearchByCategories);
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqNaviAlongTheWaySearch = reqNaviAlongTheWaySearch(parcel.readInt(), INaviSearchAlongCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqNaviAlongTheWaySearch);
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCheckHistoryDestinationEmpty = checkHistoryDestinationEmpty();
                    parcel2.writeNoException();
                    parcel2.writeInt(zCheckHistoryDestinationEmpty ? 1 : 0);
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqOpenRouteOption = reqOpenRouteOption();
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqOpenRouteOption);
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqNaviGetRouteOption = reqNaviGetRouteOption();
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqNaviGetRouteOption);
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqOpenSetAddress = reqOpenSetAddress(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqOpenSetAddress);
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqNaviAddFavoritePlace = reqNaviAddFavoritePlace(parcel.readInt() != 0 ? Place.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqNaviAddFavoritePlace);
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    int unfinishedRoute = getUnfinishedRoute();
                    parcel2.writeNoException();
                    parcel2.writeInt(unfinishedRoute);
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iResumeNavigation = resumeNavigation();
                    parcel2.writeNoException();
                    parcel2.writeInt(iResumeNavigation);
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCheckTTSPlaying = checkTTSPlaying();
                    parcel2.writeNoException();
                    parcel2.writeInt(zCheckTTSPlaying ? 1 : 0);
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iShowPlaceMark = showPlaceMark(parcel.createTypedArrayList(Place.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(iShowPlaceMark);
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iHighlightPlaceMark = highlightPlaceMark(parcel.createTypedArrayList(Place.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(iHighlightPlaceMark);
                    return true;
                case 56:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iHidePlaceMark = hidePlaceMark();
                    parcel2.writeNoException();
                    parcel2.writeInt(iHidePlaceMark);
                    return true;
                case 57:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqNaviNextViaTimeDistance = reqNaviNextViaTimeDistance();
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqNaviNextViaTimeDistance);
                    return true;
                case 58:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqOpenPOIDetail = reqOpenPOIDetail(parcel.readInt() != 0 ? Place.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqOpenPOIDetail);
                    return true;
                case 59:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqClosePOIDetail = reqClosePOIDetail();
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqClosePOIDetail);
                    return true;
                case 60:
                    parcel.enforceInterface(DESCRIPTOR);
                    int pOIDetailStatus = getPOIDetailStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(pOIDetailStatus);
                    return true;
                case 61:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqSmartNaviStart = reqSmartNaviStart(parcel.readInt() != 0 ? Place.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqSmartNaviStart);
                    return true;
                case 62:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqSmartRoutePlanning = reqSmartRoutePlanning(parcel.readInt() != 0 ? Place.CREATOR.createFromParcel(parcel) : null, parcel.createTypedArrayList(Place.CREATOR), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqSmartRoutePlanning);
                    return true;
                case 63:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqModifyDestination = reqModifyDestination(parcel.readInt() != 0 ? Place.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqModifyDestination);
                    return true;
                case 64:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iReqAppendVia = reqAppendVia(parcel.readInt() != 0 ? Place.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iReqAppendVia);
                    return true;
                case 65:
                    parcel.enforceInterface(DESCRIPTOR);
                    int remoteVersionCode = getRemoteVersionCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(remoteVersionCode);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements INaviService {
            public static INaviService sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.byd.naviserviceapi.INaviService
            public boolean registerCallback(INaviServiceCallback cb) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCallback(cb);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public boolean unregisterCallback(INaviServiceCallback cb) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterCallback(cb);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public boolean registerStateCallback(INaviStateCallback cb) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerStateCallback(cb);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public boolean unregisterStateCallback(INaviStateCallback cb) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterStateCallback(cb);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqNaviSupportedOption() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviSupportedOption();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqNaviLaunchMap() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviLaunchMap();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqNaviBackToMap() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviBackToMap();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int getMapAppStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMapAppStatus();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqNaviMapMode(int mode) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(mode);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviMapMode(mode);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int getNaviMapMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNaviMapMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqNaviZoomIn(boolean toMax) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(toMax ? 1 : 0);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviZoomIn(toMax);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqNaviZoomOut(boolean toMin) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(toMin ? 1 : 0);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviZoomOut(toMin);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqNaviSwitchFullView(boolean fullMode) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(fullMode ? 1 : 0);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviSwitchFullView(fullMode);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public boolean getNaviSwitchFullView() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNaviSwitchFullView();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqSwitchMapSetting(int status) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(status);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqSwitchMapSetting(status);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int getMapSettingStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMapSettingStatus();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqSwitchDayNightMode(int status) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(status);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqSwitchDayNightMode(status);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int getDayNightMode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDayNightMode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqSwitchTraffic(int status) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(status);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqSwitchTraffic(status);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int getTrafficStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTrafficStatus();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqOpenHistoryDestination() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqOpenHistoryDestination();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqNaviGuideStart(String routeId) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(routeId);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviGuideStart(routeId);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqNaviGuideStop() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviGuideStop();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public boolean checkNaviGuide() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkNaviGuide();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public Place getHome() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHome();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Place.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public Place getCompany() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCompany();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Place.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public Place getCurrentPlace() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentPlace();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Place.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqSetAddress(int addressType, Place place) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(addressType);
                    if (place != null) {
                        parcelObtain.writeInt(1);
                        place.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqSetAddress(addressType, place);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int getNaviMaxViaCount() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNaviMaxViaCount();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public List<Place> reqNaviViaPosition() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviViaPosition();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(Place.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqSwitchFavorite(int status) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(status);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqSwitchFavorite(status);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int getFavoriteStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFavoriteStatus();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqNaviAddFavorite() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviAddFavorite();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public boolean checkFavoriteEmpty() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkFavoriteEmpty();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public boolean checkFavoriteFull() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkFavoriteFull();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqNaviRemainTimeDistance() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviRemainTimeDistance();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public List<String> getSupportedRouteOptions() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSupportedRouteOptions();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqNaviSetRouteOption(int routeOption) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(routeOption);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviSetRouteOption(routeOption);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqNaviInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviInfo();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int addViaPosition(List<Place> places) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(places);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addViaPosition(places);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqRoutePlanning(Place destination, List<Place> via, int routeOption) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (destination != null) {
                        parcelObtain.writeInt(1);
                        destination.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedList(via);
                    parcelObtain.writeInt(routeOption);
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqRoutePlanning(destination, via, routeOption);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqNaviRouteFullView(List<RouteInfo> routes, int highlightIndex) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(routes);
                    parcelObtain.writeInt(highlightIndex);
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviRouteFullView(routes, highlightIndex);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqPOISearchByKeyword(String keywords, INaviSearchPOICallback cb) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(keywords);
                    parcelObtain.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqPOISearchByKeyword(keywords, cb);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqPOISearchByCategories(int categoryId, int locationType, String placename, INaviSearchPOICallback cb) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(categoryId);
                    parcelObtain.writeInt(locationType);
                    parcelObtain.writeString(placename);
                    parcelObtain.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqPOISearchByCategories(categoryId, locationType, placename, cb);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqNaviAlongTheWaySearch(int categoryId, INaviSearchAlongCallback cb) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(categoryId);
                    parcelObtain.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviAlongTheWaySearch(categoryId, cb);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public boolean checkHistoryDestinationEmpty() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkHistoryDestinationEmpty();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqOpenRouteOption() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqOpenRouteOption();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqNaviGetRouteOption() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(48, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviGetRouteOption();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqOpenSetAddress(int type) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(type);
                    if (!this.mRemote.transact(49, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqOpenSetAddress(type);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqNaviAddFavoritePlace(Place place) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (place != null) {
                        parcelObtain.writeInt(1);
                        place.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(50, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviAddFavoritePlace(place);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int getUnfinishedRoute() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(51, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUnfinishedRoute();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int resumeNavigation() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(52, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resumeNavigation();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public boolean checkTTSPlaying() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(53, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkTTSPlaying();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int showPlaceMark(List<Place> places) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(places);
                    if (!this.mRemote.transact(54, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().showPlaceMark(places);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int highlightPlaceMark(List<Place> places) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(places);
                    if (!this.mRemote.transact(55, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().highlightPlaceMark(places);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int hidePlaceMark() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(56, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hidePlaceMark();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqNaviNextViaTimeDistance() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(57, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNaviNextViaTimeDistance();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqOpenPOIDetail(Place poi) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (poi != null) {
                        parcelObtain.writeInt(1);
                        poi.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(58, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqOpenPOIDetail(poi);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqClosePOIDetail() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(59, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqClosePOIDetail();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int getPOIDetailStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(60, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPOIDetailStatus();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqSmartNaviStart(Place poi) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (poi != null) {
                        parcelObtain.writeInt(1);
                        poi.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(61, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqSmartNaviStart(poi);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqSmartRoutePlanning(Place destination, List<Place> via, int routeOption) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (destination != null) {
                        parcelObtain.writeInt(1);
                        destination.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedList(via);
                    parcelObtain.writeInt(routeOption);
                    if (!this.mRemote.transact(62, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqSmartRoutePlanning(destination, via, routeOption);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqModifyDestination(Place poi) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (poi != null) {
                        parcelObtain.writeInt(1);
                        poi.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(63, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqModifyDestination(poi);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int reqAppendVia(Place poi) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (poi != null) {
                        parcelObtain.writeInt(1);
                        poi.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(64, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqAppendVia(poi);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviService
            public int getRemoteVersionCode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(65, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemoteVersionCode();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INaviService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INaviService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
