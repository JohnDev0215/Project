package com.neusoft.na.ocservice.ecarxnavsdk;

import api.structs.com.neusoft.ecarx.DayNightStatus;
import api.structs.com.neusoft.ecarx.FavoritePoiInfo;
import api.structs.com.neusoft.ecarx.GuidanceInfo;
import api.structs.com.neusoft.ecarx.HighwayExitInfo;
import api.structs.com.neusoft.ecarx.LaneInfo;
import api.structs.com.neusoft.ecarx.MapStatus;
import api.structs.com.neusoft.ecarx.MuteStatus;
import api.structs.com.neusoft.ecarx.NaviStatus;
import api.structs.com.neusoft.ecarx.RouteUpdateInfo;
import api.structs.com.neusoft.ecarx.RouteViewStatus;
import api.structs.com.neusoft.ecarx.ServiceStatus;
import api.structs.com.neusoft.ecarx.TrafficInfo;
import api.structs.com.neusoft.ecarx.TrafficStatus;
import api.structs.com.neusoft.ecarx.ViewModeStatus;
import api.structs.com.neusoft.ecarx.ZoomStatus;
import com.neusoft.na.ocservice.FavoriteItem;
import com.neusoft.na.ocservice.GeoPos;
import com.neusoft.na.ocservice.IAddItemToFavoritesCallBack;
import com.neusoft.na.ocservice.IBooleanCallback;
import com.neusoft.na.ocservice.IExpirationDateCallBack;
import com.neusoft.na.ocservice.IFavoritesCallBack;
import com.neusoft.na.ocservice.IForecastWeatherCallBack;
import com.neusoft.na.ocservice.IGetFavoriteListCallBack;
import com.neusoft.na.ocservice.ILoggedInStateCallback;
import com.neusoft.na.ocservice.IObserveWeatherCallBack;
import com.neusoft.na.ocservice.IRequestSetelDetailCallBack;
import com.neusoft.na.ocservice.ISetelDataCallBack;
import com.neusoft.na.ocservice.SetelData;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface INotificationListener {
    void adareaChange(String str, String str2, String str3, String str4, String str5, String str6, String str7);

    void addItemToFavorites(int i, FavoriteItem favoriteItem, IAddItemToFavoritesCallBack iAddItemToFavoritesCallBack);

    void arrivedDest();

    void availableRestoreGuidance(int i);

    void cloudRegistrationStatus(boolean z);

    void commutePush(int i, int i2, int i3, List<TrafficInfo> list);

    void dayNightStatusUpdate(DayNightStatus dayNightStatus);

    void favoritesInfoChanged(List<FavoritePoiInfo> list);

    void getFavoriteList(int i, IGetFavoriteListCallBack iGetFavoriteListCallBack);

    void getLoggedInState(ILoggedInStateCallback iLoggedInStateCallback);

    void guidanceInfoUpdate(GuidanceInfo guidanceInfo);

    void highwayInfoUpdate(HighwayExitInfo highwayExitInfo);

    void lanesInfo(boolean z, List<LaneInfo> list);

    void mapStatusUpdate(MapStatus mapStatus);

    void muteModeUpdate(MuteStatus muteStatus);

    void naviStatusUpdate(NaviStatus naviStatus);

    void removeFavoriteItems(int i, String str, int i2, IFavoritesCallBack iFavoritesCallBack);

    boolean reqPhoneCallByNumber(String str);

    void reqSetelServiceStatus(IBooleanCallback iBooleanCallback);

    void requestExpirationDate(IExpirationDateCallBack iExpirationDateCallBack);

    void requestForecastWeather(int i, GeoPos geoPos, IForecastWeatherCallBack iForecastWeatherCallBack);

    void requestObserveWeather(int i, GeoPos geoPos, IObserveWeatherCallBack iObserveWeatherCallBack);

    void requestSetelData(SetelData setelData, ISetelDataCallBack iSetelDataCallBack);

    void requestSetelDetail(int i, String str, IRequestSetelDetailCallBack iRequestSetelDetailCallBack);

    void requestVRCommand(int i, int i2);

    void routeInfoUpdate(RouteUpdateInfo routeUpdateInfo);

    void routeSelectedChaned(RouteUpdateInfo routeUpdateInfo);

    void routeViewStatusUpdate(RouteViewStatus routeViewStatus);

    void serviceStatusChanged(ServiceStatus serviceStatus);

    void trafficCondition(int i, int i2, List<TrafficInfo> list);

    void trafficStatusUpdate(TrafficStatus trafficStatus);

    void updateFavoriteItem(int i, FavoriteItem favoriteItem, IFavoritesCallBack iFavoritesCallBack);

    void viewModeUpdate(ViewModeStatus viewModeStatus);

    void zoomStatusUpdate(ZoomStatus zoomStatus);
}
