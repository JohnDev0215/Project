package com.neusoft.na.ocservice.smartnavisdk;

import android.os.RemoteException;
import android.view.Surface;
import api.structs.com.neusoft.smart.PoiInfo;
import com.neusoft.na.ocservice.EVChargingStation;
import com.neusoft.na.ocservice.IAlongTheWaySearchCallBack;
import com.neusoft.na.ocservice.IDetailInfoCallback;
import com.neusoft.na.ocservice.IEVChargingStationCallback;
import com.neusoft.na.ocservice.IGetCloudRegistrationStatusCallBack;
import com.neusoft.na.ocservice.IGetFavoritePoisCallBack;
import com.neusoft.na.ocservice.IGetFrontTrafficCallBack;
import com.neusoft.na.ocservice.IGetGuideInfoCallBack;
import com.neusoft.na.ocservice.IGetHighwayExitInfo;
import com.neusoft.na.ocservice.IGetHistoryPoisCallBack;
import com.neusoft.na.ocservice.IGetMapLevelInfoCallBack;
import com.neusoft.na.ocservice.IGetNaviMuteModeCallBack;
import com.neusoft.na.ocservice.IGetRouteInfoCallBack;
import com.neusoft.na.ocservice.IGetTrafficSummaryInfocCallBack;
import com.neusoft.na.ocservice.IRequestGeoDecoderCallBack;
import com.neusoft.na.ocservice.IRequestRoutePlanOrNavi;
import com.neusoft.na.ocservice.IResultCodeCallBack;
import com.neusoft.na.ocservice.IResultGasStationCallBack;
import com.neusoft.na.ocservice.ISearchAroundCallBack;
import com.neusoft.na.ocservice.ISearchByKeywordCallBack;
import com.neusoft.na.ocservice.IShowMyLocationCallBack;
import com.neusoft.na.ocservice.ISpecialPoiNaviCallback;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface INavigationApi {
    boolean alongTheWaySearch(int i, int i2, IAlongTheWaySearchCallBack iAlongTheWaySearchCallBack);

    boolean backToMap(IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean cancelNavi(IResultCodeCallBack iResultCodeCallBack);

    boolean editFavoritePois(int i, String str, String str2, double d, double d2, String str3, IResultCodeCallBack iResultCodeCallBack);

    boolean exitMap(IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean getCloudRegistrationStatus(IGetCloudRegistrationStatusCallBack iGetCloudRegistrationStatusCallBack);

    void getDetailInfo(PoiInfo poiInfo, int i, IDetailInfoCallback iDetailInfoCallback);

    void getEVDetailInfo(EVChargingStation eVChargingStation, int i, IDetailInfoCallback iDetailInfoCallback);

    boolean getFavoritePois(int i, IGetFavoritePoisCallBack iGetFavoritePoisCallBack);

    boolean getFrontTraffic(int i, IGetFrontTrafficCallBack iGetFrontTrafficCallBack);

    boolean getGuideInfo(IGetGuideInfoCallBack iGetGuideInfoCallBack);

    boolean getHighwayExitInfo(int i, IGetHighwayExitInfo iGetHighwayExitInfo);

    boolean getHistoryPois(int i, IGetHistoryPoisCallBack iGetHistoryPoisCallBack);

    boolean getMapLevelInfo(IGetMapLevelInfoCallBack iGetMapLevelInfoCallBack);

    boolean getNaviMuteMode(IGetNaviMuteModeCallBack iGetNaviMuteModeCallBack);

    boolean getRouteInfo(IGetRouteInfoCallBack iGetRouteInfoCallBack);

    boolean getTrafficSummaryInfo(String str, IGetTrafficSummaryInfocCallBack iGetTrafficSummaryInfocCallBack);

    boolean goFavorite(int i, IResultCodeCallBack iResultCodeCallBack);

    boolean launchMap(IResultCodeCallBack iResultCodeCallBack);

    boolean mapZoomInOut(int i, IResultCodeCallBack iResultCodeCallBack);

    boolean modifyNaviVia(int i, PoiInfo poiInfo, IResultCodeCallBack iResultCodeCallBack);

    boolean naviFullView(int i, IResultCodeCallBack iResultCodeCallBack);

    boolean openDestinationSearch(IResultCodeCallBack iResultCodeCallBack);

    boolean removeWayPoint(int i, int i2, IResultCodeCallBack iResultCodeCallBack);

    boolean requestGeoDecoder(double d, double d2, IRequestGeoDecoderCallBack iRequestGeoDecoderCallBack);

    void requestJumpToViewPage(int i, IResultCodeCallBack iResultCodeCallBack);

    boolean requestRoutePlanOrNavi(int i, int i2, PoiInfo poiInfo, List<PoiInfo> list, IRequestRoutePlanOrNavi iRequestRoutePlanOrNavi);

    boolean requestlRecommendedGasStations(int i, IResultGasStationCallBack iResultGasStationCallBack);

    boolean resumeGuidance(int i, int i2, IResultCodeCallBack iResultCodeCallBack);

    boolean searchAround(int i, int i2, String str, int i3, double d, double d2, ISearchAroundCallBack iSearchAroundCallBack);

    boolean searchByKeyword(int i, int i2, String str, String str2, ISearchByKeywordCallBack iSearchByKeywordCallBack);

    boolean searchEVChargingStation(int i, String str, int i2, double d, double d2, IEVChargingStationCallback iEVChargingStationCallback);

    boolean selectRoute(int i, IResultCodeCallBack iResultCodeCallBack);

    boolean setLoggedInState(boolean z, String str, IResultCodeCallBack iResultCodeCallBack);

    boolean setMapViewMode(int i, IResultCodeCallBack iResultCodeCallBack);

    void setMuteStatus(boolean z, IResultCodeCallBack iResultCodeCallBack);

    boolean setNaviMuteMode(int i, IResultCodeCallBack iResultCodeCallBack);

    boolean setRoutePrefer(int i, IResultCodeCallBack iResultCodeCallBack);

    void setSurface(Surface surface);

    boolean showMyLocation(int i, IShowMyLocationCallBack iShowMyLocationCallBack);

    boolean specialPoiNavi(int i, int i2, ISpecialPoiNaviCallback iSpecialPoiNaviCallback);

    boolean switchTraffic(int i, IResultCodeCallBack iResultCodeCallBack);

    boolean vrMsgCommand(int i, int i2, IResultCodeCallBack iResultCodeCallBack);
}
