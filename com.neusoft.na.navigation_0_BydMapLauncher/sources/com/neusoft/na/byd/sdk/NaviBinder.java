package com.neusoft.na.byd.sdk;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.byd.naviserviceapi.INaviSearchAlongCallback;
import com.byd.naviserviceapi.INaviSearchPOICallback;
import com.byd.naviserviceapi.INaviService;
import com.byd.naviserviceapi.INaviServiceCallback;
import com.byd.naviserviceapi.INaviStateCallback;
import com.byd.naviserviceapi.Place;
import com.byd.naviserviceapi.RouteInfo;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NaviBinder extends INaviService.Stub {
    private static final String TAG = "XJNaviBinder";
    private static NaviBinder mNaviBinder;
    private NavigationApi mNavigationApi;

    public static synchronized NaviBinder getInstance(Context context) {
        if (mNaviBinder == null) {
            mNaviBinder = new NaviBinder(context);
        }
        return mNaviBinder;
    }

    private NaviBinder(Context context) {
        this.mNavigationApi = new NavigationApi(context);
        Log.i(TAG, "NaviBinder: " + context + ListUtils.DEFAULT_JOIN_SEPARATOR + this.mNavigationApi);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public boolean registerCallback(INaviServiceCallback iNaviServiceCallback) throws RemoteException {
        return this.mNavigationApi.registerCallback(iNaviServiceCallback);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public boolean unregisterCallback(INaviServiceCallback iNaviServiceCallback) throws RemoteException {
        return this.mNavigationApi.unregisterCallback(iNaviServiceCallback);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public boolean registerStateCallback(INaviStateCallback iNaviStateCallback) throws RemoteException {
        return this.mNavigationApi.registerStateCallback(iNaviStateCallback);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public boolean unregisterStateCallback(INaviStateCallback iNaviStateCallback) throws RemoteException {
        return this.mNavigationApi.unregisterStateCallback(iNaviStateCallback);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqNaviSupportedOption() throws RemoteException {
        return this.mNavigationApi.reqNaviSupportedOption();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqNaviLaunchMap() throws RemoteException {
        return this.mNavigationApi.reqNaviLaunchMap();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqNaviBackToMap() throws RemoteException {
        return this.mNavigationApi.reqNaviBackToMap();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int getMapAppStatus() throws RemoteException {
        return this.mNavigationApi.getMapAppStatus();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqNaviMapMode(int i) throws RemoteException {
        return this.mNavigationApi.reqNaviMapMode(i);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int getNaviMapMode() throws RemoteException {
        return this.mNavigationApi.getNaviMapMode();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqNaviZoomIn(boolean z) throws RemoteException {
        return this.mNavigationApi.reqNaviZoomIn(z);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqNaviZoomOut(boolean z) throws RemoteException {
        return this.mNavigationApi.reqNaviZoomOut(z);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqNaviSwitchFullView(boolean z) throws RemoteException {
        return this.mNavigationApi.reqNaviSwitchFullView(z);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public boolean getNaviSwitchFullView() throws RemoteException {
        return this.mNavigationApi.getNaviSwitchFullView();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqSwitchMapSetting(int i) throws RemoteException {
        return this.mNavigationApi.reqSwitchMapSetting(i);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int getMapSettingStatus() throws RemoteException {
        return this.mNavigationApi.getMapSettingStatus();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqSwitchDayNightMode(int i) throws RemoteException {
        return this.mNavigationApi.reqSwitchDayNightMode(i);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int getDayNightMode() throws RemoteException {
        return this.mNavigationApi.getDayNightMode();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqSwitchTraffic(int i) throws RemoteException {
        return this.mNavigationApi.reqSwitchTraffic(i);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int getTrafficStatus() throws RemoteException {
        return this.mNavigationApi.getTrafficStatus();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqOpenHistoryDestination() throws RemoteException {
        return this.mNavigationApi.reqOpenHistoryDestination();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqNaviGuideStart(String str) throws RemoteException {
        return this.mNavigationApi.reqNaviGuideStart(str);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqNaviGuideStop() throws RemoteException {
        return this.mNavigationApi.reqNaviGuideStop();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public boolean checkNaviGuide() throws RemoteException {
        return this.mNavigationApi.checkNaviGuide();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public Place getHome() throws RemoteException {
        return this.mNavigationApi.getHome();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public Place getCompany() throws RemoteException {
        return this.mNavigationApi.getCompany();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public Place getCurrentPlace() throws RemoteException {
        return this.mNavigationApi.getCurrentPlace();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqSetAddress(int i, Place place) throws RemoteException {
        return this.mNavigationApi.reqSetAddress(i, place);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int getNaviMaxViaCount() throws RemoteException {
        return this.mNavigationApi.getNaviMaxViaCount();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public List<Place> reqNaviViaPosition() throws RemoteException {
        return this.mNavigationApi.reqNaviViaPosition();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqSwitchFavorite(int i) throws RemoteException {
        return this.mNavigationApi.reqSwitchFavorite(i);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int getFavoriteStatus() throws RemoteException {
        return this.mNavigationApi.getFavoriteStatus();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqNaviAddFavorite() throws RemoteException {
        return this.mNavigationApi.reqNaviAddFavorite();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public boolean checkFavoriteEmpty() throws RemoteException {
        return this.mNavigationApi.checkFavoriteEmpty();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public boolean checkFavoriteFull() throws RemoteException {
        return this.mNavigationApi.checkFavoriteFull();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqNaviRemainTimeDistance() throws RemoteException {
        return this.mNavigationApi.reqNaviRemainTimeDistance();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public List<String> getSupportedRouteOptions() throws RemoteException {
        return this.mNavigationApi.getSupportedRouteOptions();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqNaviSetRouteOption(int i) throws RemoteException {
        return this.mNavigationApi.reqNaviSetRouteOption(i);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqNaviInfo() throws RemoteException {
        return this.mNavigationApi.reqNaviInfo();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int addViaPosition(List<Place> list) throws RemoteException {
        return this.mNavigationApi.addViaPosition(list);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqRoutePlanning(Place place, List<Place> list, int i) throws RemoteException {
        return this.mNavigationApi.reqRoutePlanning(place, list, i);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqNaviRouteFullView(List<RouteInfo> list, int i) throws RemoteException {
        return this.mNavigationApi.reqNaviRouteFullView(list, i);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqPOISearchByKeyword(String str, INaviSearchPOICallback iNaviSearchPOICallback) throws RemoteException {
        return this.mNavigationApi.reqPOISearchByKeyword(str, iNaviSearchPOICallback);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqPOISearchByCategories(int i, int i2, String str, INaviSearchPOICallback iNaviSearchPOICallback) throws RemoteException {
        return this.mNavigationApi.reqPOISearchByCategories(i, i2, str, iNaviSearchPOICallback);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqNaviAlongTheWaySearch(int i, INaviSearchAlongCallback iNaviSearchAlongCallback) throws RemoteException {
        return this.mNavigationApi.reqNaviAlongTheWaySearch(i, iNaviSearchAlongCallback);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public boolean checkHistoryDestinationEmpty() throws RemoteException {
        return this.mNavigationApi.checkHistoryDestinationEmpty();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqOpenRouteOption() throws RemoteException {
        return this.mNavigationApi.reqOpenRouteOption();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqNaviGetRouteOption() throws RemoteException {
        return this.mNavigationApi.reqNaviGetRouteOption();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqOpenSetAddress(int i) throws RemoteException {
        return this.mNavigationApi.reqOpenSetAddress(i);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqNaviAddFavoritePlace(Place place) throws RemoteException {
        return this.mNavigationApi.reqNaviAddFavoritePlace(place);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int getUnfinishedRoute() throws RemoteException {
        return this.mNavigationApi.getUnfinishedRoute();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int resumeNavigation() throws RemoteException {
        return this.mNavigationApi.resumeNavigation();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public boolean checkTTSPlaying() throws RemoteException {
        return this.mNavigationApi.checkTTSPlaying();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int showPlaceMark(List<Place> list) throws RemoteException {
        return this.mNavigationApi.showPlaceMark(list);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int highlightPlaceMark(List<Place> list) throws RemoteException {
        return this.mNavigationApi.highlightPlaceMark(list);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int hidePlaceMark() throws RemoteException {
        return this.mNavigationApi.hidePlaceMark();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqNaviNextViaTimeDistance() throws RemoteException {
        return this.mNavigationApi.reqNaviNextViaTimeDistance();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqOpenPOIDetail(Place place) throws RemoteException {
        return this.mNavigationApi.reqOpenPOIDetail(place);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqClosePOIDetail() throws RemoteException {
        return this.mNavigationApi.reqClosePOIDetail();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int getPOIDetailStatus() throws RemoteException {
        return this.mNavigationApi.getPOIDetailStatus();
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqSmartNaviStart(Place place) throws RemoteException {
        return this.mNavigationApi.reqSmartNaviStart(place);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqSmartRoutePlanning(Place place, List<Place> list, int i) throws RemoteException {
        return this.mNavigationApi.reqSmartRoutePlanning(place, list, i);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqModifyDestination(Place place) throws RemoteException {
        return this.mNavigationApi.reqModifyDestination(place);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int reqAppendVia(Place place) throws RemoteException {
        return this.mNavigationApi.reqAppendVia(place);
    }

    @Override // com.byd.naviserviceapi.INaviService
    public int getRemoteVersionCode() throws RemoteException {
        return this.mNavigationApi.getRemoteVersionCode();
    }

    public void shutdown() {
        this.mNavigationApi.shutdown();
    }
}
