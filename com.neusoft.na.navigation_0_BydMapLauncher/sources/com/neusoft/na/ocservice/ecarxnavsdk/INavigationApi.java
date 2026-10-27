package com.neusoft.na.ocservice.ecarxnavsdk;

import android.os.RemoteException;
import api.structs.com.neusoft.ecarx.PoiInfo;
import com.neusoft.na.ocservice.IAlongTheWaySearchCallBack;
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
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface INavigationApi {
    boolean alongTheWaySearch(int i, int i2, IAlongTheWaySearchCallBack iAlongTheWaySearchCallBack);

    boolean backToMap(IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean cancelNavi(IResultCodeCallBack iResultCodeCallBack);

    boolean editFavoritePois(int i, String str, String str2, double d, double d2, String str3, IResultCodeCallBack iResultCodeCallBack);

    boolean exitMap(IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean getCloudRegistrationStatus(IGetCloudRegistrationStatusCallBack iGetCloudRegistrationStatusCallBack);

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

    boolean naviFullView(IResultCodeCallBack iResultCodeCallBack);

    boolean openDestinationSearch(IResultCodeCallBack iResultCodeCallBack);

    boolean removeWayPoint(int i, int i2, IResultCodeCallBack iResultCodeCallBack);

    boolean requestGeoDecoder(double d, double d2, IRequestGeoDecoderCallBack iRequestGeoDecoderCallBack);

    boolean requestRoutePlanOrNavi(int i, int i2, PoiInfo poiInfo, List<PoiInfo> list, IRequestRoutePlanOrNavi iRequestRoutePlanOrNavi);

    boolean requestlRecommendedGasStations(int i, IResultGasStationCallBack iResultGasStationCallBack);

    boolean resumeGuidance(int i, int i2, IResultCodeCallBack iResultCodeCallBack);

    boolean searchAround(int i, int i2, String str, int i3, double d, double d2, ISearchAroundCallBack iSearchAroundCallBack);

    boolean searchByKeyword(int i, int i2, String str, String str2, ISearchByKeywordCallBack iSearchByKeywordCallBack);

    boolean selectRoute(int i, IResultCodeCallBack iResultCodeCallBack);

    boolean setLoggedInState(boolean z, String str, IResultCodeCallBack iResultCodeCallBack);

    boolean setMapViewMode(int i, IResultCodeCallBack iResultCodeCallBack);

    boolean setNaviMuteMode(int i, IResultCodeCallBack iResultCodeCallBack);

    boolean setRoutePrefer(int i, IResultCodeCallBack iResultCodeCallBack);

    boolean showMyLocation(int i, IShowMyLocationCallBack iShowMyLocationCallBack);

    boolean specialPoiNavi(int i, int i2, IResultCodeCallBack iResultCodeCallBack);

    boolean switchTraffic(int i, IResultCodeCallBack iResultCodeCallBack);

    boolean vrMsgCommand(int i, int i2, IResultCodeCallBack iResultCodeCallBack);
}
