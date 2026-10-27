package com.neusoft.na.ocservice.greatwallsdk;

import android.car.AoapService;
import android.view.Surface;
import com.neusoft.na.ocservice.OneCoreService;
import com.neusoft.na.ocservice.greatwallsdk.bean.GeoPosition;
import com.neusoft.na.ocservice.greatwallsdk.bean.PeripheralData;
import com.neusoft.na.ocservice.greatwallsdk.bean.PoiData;
import com.neusoft.na.ocservice.greatwallsdk.bean.PreferencesData;
import com.neusoft.na.ocservice.greatwallsdk.bean.SelectRouteData;
import java.util.List;
import kotlin.Metadata;
import org.json.JSONObject;

/* JADX INFO: compiled from: IGreatWallNavigationApi.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J:\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&J\b\u0010\u0010\u001a\u00020\u0007H&J\b\u0010\u0011\u001a\u00020\u0003H&J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003H&J\b\u0010\u0014\u001a\u00020\u0003H&J\b\u0010\u0015\u001a\u00020\u0003H&J\b\u0010\u0016\u001a\u00020\u0003H&J\b\u0010\u0017\u001a\u00020\u0003H&J\b\u0010\u0018\u001a\u00020\u0003H&J\b\u0010\u0019\u001a\u00020\u0003H&J\b\u0010\u001a\u001a\u00020\u0003H&J\b\u0010\u001b\u001a\u00020\u0003H&J\b\u0010\u001c\u001a\u00020\u0003H&J\n\u0010\u001d\u001a\u0004\u0018\u00010\u001eH&J\b\u0010\u001f\u001a\u00020\u0003H&J\b\u0010 \u001a\u00020\u0003H&J\b\u0010!\u001a\u00020\tH&J\b\u0010\"\u001a\u00020\u0003H&J\b\u0010#\u001a\u00020\u0003H&J\u0010\u0010$\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003H&J\b\u0010%\u001a\u00020\u0003H&J\b\u0010&\u001a\u00020\u0003H&J\b\u0010'\u001a\u00020\u0003H&J\b\u0010(\u001a\u00020\u0003H&J\b\u0010)\u001a\u00020\u0003H&J\b\u0010*\u001a\u00020\u0003H&J\b\u0010+\u001a\u00020\u0003H&J\b\u0010,\u001a\u00020\u0003H&J\b\u0010-\u001a\u00020\u0003H&J\b\u0010.\u001a\u00020\u0003H&J\b\u0010/\u001a\u00020\u0003H&J\u0010\u00100\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u00101\u001a\u00020\u0003H&J\b\u00102\u001a\u00020\u0003H&J\b\u00103\u001a\u00020\u0003H&J\b\u00104\u001a\u00020\u0003H&J\u0010\u00105\u001a\u00020\u00032\u0006\u00106\u001a\u000207H&J\b\u00108\u001a\u00020\u0003H&J\b\u00109\u001a\u00020\u0003H&J*\u0010:\u001a\u00020\u00072\u0006\u0010;\u001a\u00020\t2\u0006\u0010<\u001a\u00020=2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010>\u001a\u00020\tH&J \u0010?\u001a\u00020\u00072\u0006\u0010;\u001a\u00020\t2\u0006\u0010@\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH&J\b\u0010A\u001a\u00020\u0003H&J\u0010\u0010B\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\"\u0010C\u001a\u00020\u00072\u0006\u0010;\u001a\u00020\t2\u0006\u0010@\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&J\u0010\u0010D\u001a\u00020\u00032\u0006\u0010E\u001a\u00020FH&J\u0010\u0010G\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010H\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010I\u001a\u00020\u00072\u0006\u0010J\u001a\u00020\tH&J\u0018\u0010K\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010L\u001a\u00020\u0003H&J\u001a\u0010M\u001a\u00020\u00072\u0006\u0010N\u001a\u00020O2\b\u0010P\u001a\u0004\u0018\u00010QH&J\b\u0010R\u001a\u00020\u0003H&J\b\u0010S\u001a\u00020\u0003H&J\b\u0010T\u001a\u00020\u0003H&J\u0010\u0010U\u001a\u00020\u00032\u0006\u0010V\u001a\u00020WH&J\b\u0010X\u001a\u00020\u0003H&J\b\u0010Y\u001a\u00020\u0003H&J\b\u0010Z\u001a\u00020\u0003H&J\b\u0010[\u001a\u00020\u0003H&¨\u0006\\"}, d2 = {"Lcom/neusoft/na/ocservice/greatwallsdk/IGreatWallNavigationApi;", "", "addPointsToFavorites", "", "poiData", "Lcom/neusoft/na/ocservice/greatwallsdk/bean/PoiData;", "calculateRoad", "", "actionType", "", "endPoi", "startPoi", "wayPoints", "", "callback", "Lcom/neusoft/na/ocservice/greatwallsdk/IGWRequestCallback;", "clearLog", "closeTraffic", "deleteNavigationPersonalizationData", "BeanId", "destinationInformation", "displayFavorites", "displayHistoricalDestination", "enterOverview", "exitOverview", "getCompanyAddress", "getCountryCode", "getDayNightMode", "getFavoriteList", "getFrontTraffic", "Lorg/json/JSONObject;", "getHomeAddress", "getLocation", "getLogOnOffStatus", "getMapStatus", "getNavigationLanguageSetting", "getNavigationPersonalizationData", "getNavigationStatus", "getOverviewStatus", "getScaleSize", "getTBTInfo", "getTrafficStatus", "getViewMode", "getWayPointList", "getdestinationInfo", "goCompany", "goHome", "historicalDestinations", "increaseRoutePoints", "mapToBackstage", "mapToFrontDesk", "mapToMaximum", "mapToMinimize", "openMapWithLocation", "position", "Lcom/neusoft/na/ocservice/greatwallsdk/bean/GeoPosition;", "openSettingsMenu", "openTraffic", "peripheralSearch", "reqId", "peripheralData", "Lcom/neusoft/na/ocservice/greatwallsdk/bean/PeripheralData;", "operaType", "poiSearch", "keyWord", "queryPreferences", "removeFavoritePoints", "searchAlong", "selectRoute", "selectRouteData", "Lcom/neusoft/na/ocservice/greatwallsdk/bean/SelectRouteData;", "setCompanyAddress", "setHomeAddress", "setLogOnOffStatus", "status", "setNavigationPersonalizationData", AoapService.KEY_RESULT, "setSurface", OneCoreService.PAYLOAD_SURFACE, "Landroid/view/Surface;", "onSetSurfaceCallback", "Lcom/neusoft/na/ocservice/greatwallsdk/IGWSetSurfaceCallback;", "setViewMode2DDueNorthUp", "setViewMode2DHeadUp", "setViewMode3DHeadUp", "settingPreferences", "preferencesData", "Lcom/neusoft/na/ocservice/greatwallsdk/bean/PreferencesData;", "startNavigation", "stopNavigation", "zoomIn", "zoomOut", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface IGreatWallNavigationApi {
    String addPointsToFavorites(PoiData poiData);

    void calculateRoad(int actionType, PoiData endPoi, PoiData startPoi, List<? extends PoiData> wayPoints, IGWRequestCallback callback);

    void clearLog();

    String closeTraffic();

    String deleteNavigationPersonalizationData(String BeanId);

    String destinationInformation();

    String displayFavorites();

    String displayHistoricalDestination();

    String enterOverview();

    String exitOverview();

    String getCompanyAddress();

    String getCountryCode();

    String getDayNightMode();

    String getFavoriteList();

    JSONObject getFrontTraffic();

    String getHomeAddress();

    String getLocation();

    int getLogOnOffStatus();

    String getMapStatus();

    String getNavigationLanguageSetting();

    String getNavigationPersonalizationData(String BeanId);

    String getNavigationStatus();

    String getOverviewStatus();

    String getScaleSize();

    String getTBTInfo();

    String getTrafficStatus();

    String getViewMode();

    String getWayPointList();

    String getdestinationInfo();

    String goCompany();

    String goHome();

    String historicalDestinations();

    String increaseRoutePoints(PoiData poiData);

    String mapToBackstage();

    String mapToFrontDesk();

    String mapToMaximum();

    String mapToMinimize();

    String openMapWithLocation(GeoPosition position);

    String openSettingsMenu();

    String openTraffic();

    void peripheralSearch(int reqId, PeripheralData peripheralData, IGWRequestCallback callback, int operaType);

    void poiSearch(int reqId, String keyWord, IGWRequestCallback callback);

    String queryPreferences();

    String removeFavoritePoints(PoiData poiData);

    void searchAlong(int reqId, String keyWord, IGWRequestCallback callback);

    String selectRoute(SelectRouteData selectRouteData);

    String setCompanyAddress(PoiData poiData);

    String setHomeAddress(PoiData poiData);

    void setLogOnOffStatus(int status);

    String setNavigationPersonalizationData(String BeanId, String result);

    void setSurface(Surface surface, IGWSetSurfaceCallback onSetSurfaceCallback);

    String setViewMode2DDueNorthUp();

    String setViewMode2DHeadUp();

    String setViewMode3DHeadUp();

    String settingPreferences(PreferencesData preferencesData);

    String startNavigation();

    String stopNavigation();

    String zoomIn();

    String zoomOut();
}
