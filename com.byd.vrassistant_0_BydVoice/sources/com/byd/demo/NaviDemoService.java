package com.byd.demo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.byd.naviserviceapi.INaviFavoriteCallback;
import com.byd.naviserviceapi.INaviSearchAlongCallback;
import com.byd.naviserviceapi.INaviSearchPOICallback;
import com.byd.naviserviceapi.INaviService;
import com.byd.naviserviceapi.INaviServiceCallback;
import com.byd.naviserviceapi.INaviStateCallback;
import com.byd.naviserviceapi.NaviServiceUtils;
import com.byd.naviserviceapi.Place;
import com.byd.naviserviceapi.RouteInfo;
import com.byd.naviserviceapi.RouteInfo2;
import com.byd.vrassistant.Constant;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NaviDemoService extends Service {
    private static final String TAG = "NaviDemoService";
    private INaviServiceCallback naviServiceCallback = new INaviServiceCallback() { // from class: com.byd.demo.NaviDemoService.1
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onNaviAddViaPosition(int code) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onNaviNextViaTimeDistance(int code, long seconds, double distance) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onNaviRemainTimeDistance(int code, long seconds, double distance) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onNaviSupportedOptionResult(int code, List<String> options) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onNaviViaPositionResult(int code, List<Place> vias) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onReqNaviAddFavorite(int code, Place place) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onReqNaviGetRouteOption(int code, int routeOption) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onReqNaviGetRouteOptions(int code, int[] routeOptions) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onReqNaviInfo(int code, int routeOption, Place startPosition, Place destinationPosition, List<Place> viaPositions) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onReqNaviInfo2(int code, int[] routeOptions, Place startPosition, Place destinationPosition, List<Place> viaPositions) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onReqNaviSetRouteOption(int code) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onReqUnfinishedRoute(int code, int routeOption, Place destinationPosition, List<Place> viaPositions) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onReqUnfinishedRoute2(int code, int[] routeOptions, Place destinationPosition, List<Place> viaPositions) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onRouteFullView(int code) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onRoutePlanning(int code, List<RouteInfo> routes) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onRoutePlanning2(int code, List<RouteInfo2> routes) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onRoutePlanningUpdate(int code) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onSmartRoutePlanning(int code, RouteInfo route) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onSmartRoutePlanning2(int code, RouteInfo2 route) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onSetAddress(int code, int locationType, Place place) throws RemoteException {
            Log.d(NaviDemoService.TAG, "onSetAddress");
            new Place().setName("demo1");
        }
    };
    private INaviService.Stub stub = new INaviService.Stub() { // from class: com.byd.demo.NaviDemoService.2
        Runnable handleNaviRemainTimeDistant = new Runnable() { // from class: com.byd.demo.NaviDemoService.2.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    NaviDemoService.this.naviServiceCallback.onNaviRemainTimeDistance(1, 3000L, 500.0d);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        };

        @Override // com.byd.naviserviceapi.INaviService
        public int addViaPosition(List<Place> places) throws RemoteException {
            return 0;
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
        public int reqCloseRouteOption() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqCloseSetAddress() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqFavoritePlaceList(INaviFavoriteCallback cb) throws RemoteException {
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
        public int reqNaviAlongTheWaySearchByKeyword(String keyword, INaviSearchAlongCallback cb) throws RemoteException {
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
        public int reqNaviMapMode(int mode) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviNextViaTimeDistance() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviRouteFullView(List<RouteInfo> routes, int highlightIndex) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviRouteFullView2(List<RouteInfo2> routes, int highlightIndex) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviSetRouteOption(int routeOption) throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviSetRouteOptions(int[] routeOptions) throws RemoteException {
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
        public int reqRecenter() throws RemoteException {
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqRoutePlanning2(Place destination, List<Place> via, int[] routeOptions) throws RemoteException {
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
        public int reqSmartRoutePlanning2(Place destination, List<Place> via, int[] routeOptions) throws RemoteException {
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
        public boolean unregisterStateCallback(INaviStateCallback cb) throws RemoteException {
            return false;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int getRemoteVersionCode() throws RemoteException {
            return NaviServiceUtils.getNaviApiVersionCode();
        }

        @Override // com.byd.naviserviceapi.INaviService
        public boolean registerCallback(INaviServiceCallback cb) throws RemoteException {
            NaviDemoService.this.naviServiceCallback = cb;
            return true;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public boolean unregisterCallback(INaviServiceCallback cb) throws RemoteException {
            NaviDemoService.this.naviServiceCallback = null;
            return true;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviLaunchMap() throws RemoteException {
            Log.d(NaviDemoService.TAG, "reqNaviLaunchMap");
            return 0;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public Place getHome() throws RemoteException {
            Log.d(NaviDemoService.TAG, "getHome");
            Place place = new Place();
            place.setName("home");
            place.setType(1);
            place.setLongitude(133.34d);
            place.setLatitude(41.43d);
            place.setDistance(1000.0d);
            place.setCategoryId(null);
            place.setCity(null);
            place.setAddress(null);
            place.setHouse_number(null);
            place.setCountry(null);
            place.setId(null);
            place.setState(null);
            place.setStreet(null);
            return place;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public Place getCompany() throws RemoteException {
            Log.d(NaviDemoService.TAG, "getCompany");
            Place place = new Place();
            place.setName(Constant.VersionType_Test);
            String[] strArr = {"1", "2", "3"};
            place.setCategoryId(strArr);
            place.setType(1);
            place.setLongitude(131.21d);
            place.setLatitude(42.12d);
            place.setDistance(3000.0d);
            place.setCategoryId(strArr);
            place.setCity("city");
            place.setAddress("address");
            place.setHouse_number("housenumber");
            place.setCountry("country");
            place.setId(null);
            place.setState("state");
            place.setStreet(new String[]{"street"});
            Log.d(NaviDemoService.TAG, "company name = " + place.getName());
            Log.d(NaviDemoService.TAG, "company category size = " + place.getCategoryId().length);
            Log.d(NaviDemoService.TAG, "company categoryId = " + Arrays.toString(place.getCategoryId()));
            return place;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqSetAddress(int addressType, Place place) throws RemoteException {
            Log.d(NaviDemoService.TAG, "reqSetAddress");
            new Place().setName("demo1");
            return 1;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqNaviRemainTimeDistance() throws RemoteException {
            new Thread(this.handleNaviRemainTimeDistant).start();
            return 1;
        }

        @Override // com.byd.naviserviceapi.INaviService
        public int reqRoutePlanning(Place destination, List<Place> via, int routeOption) throws RemoteException {
            Log.d(NaviDemoService.TAG, "enter reqRoutePlanning...");
            Log.d(NaviDemoService.TAG, "destination = " + destination.getName() + "," + destination.getAddress());
            String[] categoryId = destination.getCategoryId();
            if (categoryId != null) {
                Log.d(NaviDemoService.TAG, "categories length=" + categoryId.length);
            } else {
                Log.d(NaviDemoService.TAG, "categories is null");
            }
            String[] street = destination.getStreet();
            if (street != null) {
                Log.d(NaviDemoService.TAG, "streets length=" + street.length);
            } else {
                Log.d(NaviDemoService.TAG, "streets is null");
            }
            Log.d(NaviDemoService.TAG, "via size=" + via.size());
            Log.d(NaviDemoService.TAG, "routeOption=" + routeOption);
            return 0;
        }
    };

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "DemoNaviService onCreate");
        ((NotificationManager) getSystemService("notification")).createNotificationChannel(new NotificationChannel("channel_vrapp", "DemoNaviService", 4));
        startForeground(1, new Notification.Builder(this, "channel_vrapp").build());
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand startId=" + startId);
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.d(TAG, "DemoNaviService onDestroy");
        super.onDestroy();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "DemoNaviService onBind");
        return this.stub;
    }
}
