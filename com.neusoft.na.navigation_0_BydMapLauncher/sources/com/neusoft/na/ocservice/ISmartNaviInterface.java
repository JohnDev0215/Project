package com.neusoft.na.ocservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;
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
import com.neusoft.na.ocservice.ISmartNaviNotificationInterface;
import com.neusoft.na.ocservice.ISpecialPoiNaviCallback;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface ISmartNaviInterface extends IInterface {

    public static class Default implements ISmartNaviInterface {
        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public void addSurface(Surface surface, int i, int i2, int i3, int i4, int i5, int i6, int i7) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean alongTheWaySearch(int i, int i2, IAlongTheWaySearchCallBack iAlongTheWaySearchCallBack) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean backToMap(IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean cancelNavi(IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean editFavoritePois(int i, String str, String str2, double d, double d2, String str3, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean exitMap(IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean getCloudRegistrationStatus(IGetCloudRegistrationStatusCallBack iGetCloudRegistrationStatusCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public void getDetailInfo(PoiInfo poiInfo, int i, IDetailInfoCallback iDetailInfoCallback) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public void getEVDetailInfo(EVChargingStation eVChargingStation, int i, IDetailInfoCallback iDetailInfoCallback) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean getFavoritePois(int i, IGetFavoritePoisCallBack iGetFavoritePoisCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean getFrontTraffic(int i, IGetFrontTrafficCallBack iGetFrontTrafficCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean getGuideInfo(IGetGuideInfoCallBack iGetGuideInfoCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean getHighwayExitInfo(int i, IGetHighwayExitInfo iGetHighwayExitInfo) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean getHistoryPois(int i, IGetHistoryPoisCallBack iGetHistoryPoisCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean getMapLevelInfo(IGetMapLevelInfoCallBack iGetMapLevelInfoCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean getNaviMuteMode(IGetNaviMuteModeCallBack iGetNaviMuteModeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean getRouteInfo(IGetRouteInfoCallBack iGetRouteInfoCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean getTrafficSummaryInfo(String str, IGetTrafficSummaryInfocCallBack iGetTrafficSummaryInfocCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean goFavorite(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean launchMap(IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean mapZoomInOut(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean modifyNaviVia(int i, PoiInfo poiInfo, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean naviFullView(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean openDestinationSearch(IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean removeWayPoint(int i, int i2, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public void removedSurface(Surface surface, int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean requestGeoDecoder(double d, double d2, IRequestGeoDecoderCallBack iRequestGeoDecoderCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public void requestJumpToViewPage(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean requestRoutePlanOrNavi(int i, int i2, PoiInfo poiInfo, List<PoiInfo> list, IRequestRoutePlanOrNavi iRequestRoutePlanOrNavi) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean requestlRecommendedGasStations(int i, IResultGasStationCallBack iResultGasStationCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean resumeGuidance(int i, int i2, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean searchAround(int i, int i2, String str, int i3, double d, double d2, ISearchAroundCallBack iSearchAroundCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean searchByKeyword(int i, int i2, String str, String str2, ISearchByKeywordCallBack iSearchByKeywordCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public void searchEVChargingStation(int i, String str, int i2, LatLng latLng, IEVChargingStationCallback iEVChargingStationCallback) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean selectRoute(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean setListener(ISmartNaviNotificationInterface iSmartNaviNotificationInterface) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean setLoggedInState(boolean z, String str, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean setMapViewMode(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public void setMuteStatus(boolean z, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean setNaviMuteMode(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean setRoutePrefer(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean showMyLocation(int i, IShowMyLocationCallBack iShowMyLocationCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean specialPoiNavi(int i, int i2, ISpecialPoiNaviCallback iSpecialPoiNaviCallback) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean switchTraffic(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean unSetListener(ISmartNaviNotificationInterface iSmartNaviNotificationInterface) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviInterface
        public boolean vrMsgCommand(int i, int i2, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
            return false;
        }
    }

    void addSurface(Surface surface, int i, int i2, int i3, int i4, int i5, int i6, int i7) throws RemoteException;

    boolean alongTheWaySearch(int i, int i2, IAlongTheWaySearchCallBack iAlongTheWaySearchCallBack) throws RemoteException;

    boolean backToMap(IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean cancelNavi(IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean editFavoritePois(int i, String str, String str2, double d, double d2, String str3, IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean exitMap(IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean getCloudRegistrationStatus(IGetCloudRegistrationStatusCallBack iGetCloudRegistrationStatusCallBack) throws RemoteException;

    void getDetailInfo(PoiInfo poiInfo, int i, IDetailInfoCallback iDetailInfoCallback) throws RemoteException;

    void getEVDetailInfo(EVChargingStation eVChargingStation, int i, IDetailInfoCallback iDetailInfoCallback) throws RemoteException;

    boolean getFavoritePois(int i, IGetFavoritePoisCallBack iGetFavoritePoisCallBack) throws RemoteException;

    boolean getFrontTraffic(int i, IGetFrontTrafficCallBack iGetFrontTrafficCallBack) throws RemoteException;

    boolean getGuideInfo(IGetGuideInfoCallBack iGetGuideInfoCallBack) throws RemoteException;

    boolean getHighwayExitInfo(int i, IGetHighwayExitInfo iGetHighwayExitInfo) throws RemoteException;

    boolean getHistoryPois(int i, IGetHistoryPoisCallBack iGetHistoryPoisCallBack) throws RemoteException;

    boolean getMapLevelInfo(IGetMapLevelInfoCallBack iGetMapLevelInfoCallBack) throws RemoteException;

    boolean getNaviMuteMode(IGetNaviMuteModeCallBack iGetNaviMuteModeCallBack) throws RemoteException;

    boolean getRouteInfo(IGetRouteInfoCallBack iGetRouteInfoCallBack) throws RemoteException;

    boolean getTrafficSummaryInfo(String str, IGetTrafficSummaryInfocCallBack iGetTrafficSummaryInfocCallBack) throws RemoteException;

    boolean goFavorite(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean launchMap(IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean mapZoomInOut(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean modifyNaviVia(int i, PoiInfo poiInfo, IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean naviFullView(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean openDestinationSearch(IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean removeWayPoint(int i, int i2, IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    void removedSurface(Surface surface, int i) throws RemoteException;

    boolean requestGeoDecoder(double d, double d2, IRequestGeoDecoderCallBack iRequestGeoDecoderCallBack) throws RemoteException;

    void requestJumpToViewPage(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean requestRoutePlanOrNavi(int i, int i2, PoiInfo poiInfo, List<PoiInfo> list, IRequestRoutePlanOrNavi iRequestRoutePlanOrNavi) throws RemoteException;

    boolean requestlRecommendedGasStations(int i, IResultGasStationCallBack iResultGasStationCallBack) throws RemoteException;

    boolean resumeGuidance(int i, int i2, IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean searchAround(int i, int i2, String str, int i3, double d, double d2, ISearchAroundCallBack iSearchAroundCallBack) throws RemoteException;

    boolean searchByKeyword(int i, int i2, String str, String str2, ISearchByKeywordCallBack iSearchByKeywordCallBack) throws RemoteException;

    void searchEVChargingStation(int i, String str, int i2, LatLng latLng, IEVChargingStationCallback iEVChargingStationCallback) throws RemoteException;

    boolean selectRoute(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean setListener(ISmartNaviNotificationInterface iSmartNaviNotificationInterface) throws RemoteException;

    boolean setLoggedInState(boolean z, String str, IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean setMapViewMode(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    void setMuteStatus(boolean z, IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean setNaviMuteMode(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean setRoutePrefer(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean showMyLocation(int i, IShowMyLocationCallBack iShowMyLocationCallBack) throws RemoteException;

    boolean specialPoiNavi(int i, int i2, ISpecialPoiNaviCallback iSpecialPoiNaviCallback) throws RemoteException;

    boolean switchTraffic(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    boolean unSetListener(ISmartNaviNotificationInterface iSmartNaviNotificationInterface) throws RemoteException;

    boolean vrMsgCommand(int i, int i2, IResultCodeCallBack iResultCodeCallBack) throws RemoteException;

    public static abstract class Stub extends Binder implements ISmartNaviInterface {
        private static final String DESCRIPTOR = "com.neusoft.na.ocservice.ISmartNaviInterface";
        static final int TRANSACTION_addSurface = 41;
        static final int TRANSACTION_alongTheWaySearch = 19;
        static final int TRANSACTION_backToMap = 4;
        static final int TRANSACTION_cancelNavi = 6;
        static final int TRANSACTION_editFavoritePois = 22;
        static final int TRANSACTION_exitMap = 5;
        static final int TRANSACTION_getCloudRegistrationStatus = 35;
        static final int TRANSACTION_getDetailInfo = 44;
        static final int TRANSACTION_getEVDetailInfo = 46;
        static final int TRANSACTION_getFavoritePois = 15;
        static final int TRANSACTION_getFrontTraffic = 28;
        static final int TRANSACTION_getGuideInfo = 30;
        static final int TRANSACTION_getHighwayExitInfo = 16;
        static final int TRANSACTION_getHistoryPois = 26;
        static final int TRANSACTION_getMapLevelInfo = 33;
        static final int TRANSACTION_getNaviMuteMode = 29;
        static final int TRANSACTION_getRouteInfo = 27;
        static final int TRANSACTION_getTrafficSummaryInfo = 18;
        static final int TRANSACTION_goFavorite = 31;
        static final int TRANSACTION_launchMap = 3;
        static final int TRANSACTION_mapZoomInOut = 8;
        static final int TRANSACTION_modifyNaviVia = 21;
        static final int TRANSACTION_naviFullView = 11;
        static final int TRANSACTION_openDestinationSearch = 7;
        static final int TRANSACTION_removeWayPoint = 34;
        static final int TRANSACTION_removedSurface = 42;
        static final int TRANSACTION_requestGeoDecoder = 32;
        static final int TRANSACTION_requestJumpToViewPage = 40;
        static final int TRANSACTION_requestRoutePlanOrNavi = 24;
        static final int TRANSACTION_requestlRecommendedGasStations = 37;
        static final int TRANSACTION_resumeGuidance = 36;
        static final int TRANSACTION_searchAround = 25;
        static final int TRANSACTION_searchByKeyword = 23;
        static final int TRANSACTION_searchEVChargingStation = 45;
        static final int TRANSACTION_selectRoute = 14;
        static final int TRANSACTION_setListener = 1;
        static final int TRANSACTION_setLoggedInState = 39;
        static final int TRANSACTION_setMapViewMode = 10;
        static final int TRANSACTION_setMuteStatus = 43;
        static final int TRANSACTION_setNaviMuteMode = 17;
        static final int TRANSACTION_setRoutePrefer = 13;
        static final int TRANSACTION_showMyLocation = 12;
        static final int TRANSACTION_specialPoiNavi = 20;
        static final int TRANSACTION_switchTraffic = 9;
        static final int TRANSACTION_unSetListener = 2;
        static final int TRANSACTION_vrMsgCommand = 38;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISmartNaviInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISmartNaviInterface)) {
                return (ISmartNaviInterface) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
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
                    boolean listener = setListener(ISmartNaviNotificationInterface.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(listener ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUnSetListener = unSetListener(ISmartNaviNotificationInterface.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUnSetListener ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zLaunchMap = launchMap(IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zLaunchMap ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zBackToMap = backToMap(IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zBackToMap ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zExitMap = exitMap(IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zExitMap ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCancelNavi = cancelNavi(IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zCancelNavi ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zOpenDestinationSearch = openDestinationSearch(IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zOpenDestinationSearch ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zMapZoomInOut = mapZoomInOut(parcel.readInt(), IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zMapZoomInOut ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSwitchTraffic = switchTraffic(parcel.readInt(), IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zSwitchTraffic ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean mapViewMode = setMapViewMode(parcel.readInt(), IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(mapViewMode ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zNaviFullView = naviFullView(parcel.readInt(), IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zNaviFullView ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zShowMyLocation = showMyLocation(parcel.readInt(), IShowMyLocationCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zShowMyLocation ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean routePrefer = setRoutePrefer(parcel.readInt(), IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(routePrefer ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSelectRoute = selectRoute(parcel.readInt(), IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zSelectRoute ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean favoritePois = getFavoritePois(parcel.readInt(), IGetFavoritePoisCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(favoritePois ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean highwayExitInfo = getHighwayExitInfo(parcel.readInt(), IGetHighwayExitInfo.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(highwayExitInfo ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean naviMuteMode = setNaviMuteMode(parcel.readInt(), IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(naviMuteMode ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean trafficSummaryInfo = getTrafficSummaryInfo(parcel.readString(), IGetTrafficSummaryInfocCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(trafficSummaryInfo ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAlongTheWaySearch = alongTheWaySearch(parcel.readInt(), parcel.readInt(), IAlongTheWaySearchCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zAlongTheWaySearch ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSpecialPoiNavi = specialPoiNavi(parcel.readInt(), parcel.readInt(), ISpecialPoiNaviCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zSpecialPoiNavi ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zModifyNaviVia = modifyNaviVia(parcel.readInt(), parcel.readInt() != 0 ? PoiInfo.CREATOR.createFromParcel(parcel) : null, IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zModifyNaviVia ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zEditFavoritePois = editFavoritePois(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readDouble(), parcel.readDouble(), parcel.readString(), IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zEditFavoritePois ? 1 : 0);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSearchByKeyword = searchByKeyword(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), ISearchByKeywordCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zSearchByKeyword ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRequestRoutePlanOrNavi = requestRoutePlanOrNavi(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? PoiInfo.CREATOR.createFromParcel(parcel) : null, parcel.createTypedArrayList(PoiInfo.CREATOR), IRequestRoutePlanOrNavi.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRequestRoutePlanOrNavi ? 1 : 0);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSearchAround = searchAround(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readDouble(), parcel.readDouble(), ISearchAroundCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zSearchAround ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean historyPois = getHistoryPois(parcel.readInt(), IGetHistoryPoisCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(historyPois ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean routeInfo = getRouteInfo(IGetRouteInfoCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(routeInfo ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean frontTraffic = getFrontTraffic(parcel.readInt(), IGetFrontTrafficCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(frontTraffic ? 1 : 0);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean naviMuteMode2 = getNaviMuteMode(IGetNaviMuteModeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(naviMuteMode2 ? 1 : 0);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean guideInfo = getGuideInfo(IGetGuideInfoCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(guideInfo ? 1 : 0);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zGoFavorite = goFavorite(parcel.readInt(), IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zGoFavorite ? 1 : 0);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRequestGeoDecoder = requestGeoDecoder(parcel.readDouble(), parcel.readDouble(), IRequestGeoDecoderCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRequestGeoDecoder ? 1 : 0);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean mapLevelInfo = getMapLevelInfo(IGetMapLevelInfoCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(mapLevelInfo ? 1 : 0);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRemoveWayPoint = removeWayPoint(parcel.readInt(), parcel.readInt(), IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveWayPoint ? 1 : 0);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean cloudRegistrationStatus = getCloudRegistrationStatus(IGetCloudRegistrationStatusCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(cloudRegistrationStatus ? 1 : 0);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zResumeGuidance = resumeGuidance(parcel.readInt(), parcel.readInt(), IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zResumeGuidance ? 1 : 0);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRequestlRecommendedGasStations = requestlRecommendedGasStations(parcel.readInt(), IResultGasStationCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRequestlRecommendedGasStations ? 1 : 0);
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zVrMsgCommand = vrMsgCommand(parcel.readInt(), parcel.readInt(), IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zVrMsgCommand ? 1 : 0);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean loggedInState = setLoggedInState(parcel.readInt() != 0, parcel.readString(), IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(loggedInState ? 1 : 0);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestJumpToViewPage(parcel.readInt(), IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    addSurface(parcel.readInt() != 0 ? (Surface) Surface.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    removedSurface(parcel.readInt() != 0 ? (Surface) Surface.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMuteStatus(parcel.readInt() != 0, IResultCodeCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    getDetailInfo(parcel.readInt() != 0 ? PoiInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), IDetailInfoCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    searchEVChargingStation(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? LatLng.CREATOR.createFromParcel(parcel) : null, IEVChargingStationCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    getEVDetailInfo(parcel.readInt() != 0 ? EVChargingStation.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), IDetailInfoCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ISmartNaviInterface {
            public static ISmartNaviInterface sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean setListener(ISmartNaviNotificationInterface iSmartNaviNotificationInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iSmartNaviNotificationInterface != null ? iSmartNaviNotificationInterface.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setListener(iSmartNaviNotificationInterface);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean unSetListener(ISmartNaviNotificationInterface iSmartNaviNotificationInterface) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iSmartNaviNotificationInterface != null ? iSmartNaviNotificationInterface.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unSetListener(iSmartNaviNotificationInterface);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean launchMap(IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().launchMap(iResultCodeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean backToMap(IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().backToMap(iResultCodeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean exitMap(IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().exitMap(iResultCodeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean cancelNavi(IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelNavi(iResultCodeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean openDestinationSearch(IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openDestinationSearch(iResultCodeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean mapZoomInOut(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().mapZoomInOut(i, iResultCodeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean switchTraffic(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().switchTraffic(i, iResultCodeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean setMapViewMode(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setMapViewMode(i, iResultCodeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean naviFullView(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().naviFullView(i, iResultCodeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean showMyLocation(int i, IShowMyLocationCallBack iShowMyLocationCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iShowMyLocationCallBack != null ? iShowMyLocationCallBack.asBinder() : null);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().showMyLocation(i, iShowMyLocationCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean setRoutePrefer(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRoutePrefer(i, iResultCodeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean selectRoute(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().selectRoute(i, iResultCodeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean getFavoritePois(int i, IGetFavoritePoisCallBack iGetFavoritePoisCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iGetFavoritePoisCallBack != null ? iGetFavoritePoisCallBack.asBinder() : null);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFavoritePois(i, iGetFavoritePoisCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean getHighwayExitInfo(int i, IGetHighwayExitInfo iGetHighwayExitInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iGetHighwayExitInfo != null ? iGetHighwayExitInfo.asBinder() : null);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHighwayExitInfo(i, iGetHighwayExitInfo);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean setNaviMuteMode(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNaviMuteMode(i, iResultCodeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean getTrafficSummaryInfo(String str, IGetTrafficSummaryInfocCallBack iGetTrafficSummaryInfocCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iGetTrafficSummaryInfocCallBack != null ? iGetTrafficSummaryInfocCallBack.asBinder() : null);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTrafficSummaryInfo(str, iGetTrafficSummaryInfocCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean alongTheWaySearch(int i, int i2, IAlongTheWaySearchCallBack iAlongTheWaySearchCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iAlongTheWaySearchCallBack != null ? iAlongTheWaySearchCallBack.asBinder() : null);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().alongTheWaySearch(i, i2, iAlongTheWaySearchCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean specialPoiNavi(int i, int i2, ISpecialPoiNaviCallback iSpecialPoiNaviCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iSpecialPoiNaviCallback != null ? iSpecialPoiNaviCallback.asBinder() : null);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().specialPoiNavi(i, i2, iSpecialPoiNaviCallback);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean modifyNaviVia(int i, PoiInfo poiInfo, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (poiInfo != null) {
                        parcelObtain.writeInt(1);
                        poiInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().modifyNaviVia(i, poiInfo, iResultCodeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean editFavoritePois(int i, String str, String str2, double d, double d2, String str3, IResultCodeCallBack iResultCodeCallBack) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeDouble(d);
                    parcelObtain.writeDouble(d2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        boolean zEditFavoritePois = Stub.getDefaultImpl().editFavoritePois(i, str, str2, d, d2, str3, iResultCodeCallBack);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return zEditFavoritePois;
                    }
                    parcelObtain2.readException();
                    boolean z = parcelObtain2.readInt() != 0;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean searchByKeyword(int i, int i2, String str, String str2, ISearchByKeywordCallBack iSearchByKeywordCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iSearchByKeywordCallBack != null ? iSearchByKeywordCallBack.asBinder() : null);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().searchByKeyword(i, i2, str, str2, iSearchByKeywordCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean requestRoutePlanOrNavi(int i, int i2, PoiInfo poiInfo, List<PoiInfo> list, IRequestRoutePlanOrNavi iRequestRoutePlanOrNavi) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (poiInfo != null) {
                        parcelObtain.writeInt(1);
                        poiInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeStrongBinder(iRequestRoutePlanOrNavi != null ? iRequestRoutePlanOrNavi.asBinder() : null);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        boolean zRequestRoutePlanOrNavi = Stub.getDefaultImpl().requestRoutePlanOrNavi(i, i2, poiInfo, list, iRequestRoutePlanOrNavi);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return zRequestRoutePlanOrNavi;
                    }
                    parcelObtain2.readException();
                    boolean z = parcelObtain2.readInt() != 0;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean searchAround(int i, int i2, String str, int i3, double d, double d2, ISearchAroundCallBack iSearchAroundCallBack) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeDouble(d);
                    parcelObtain.writeDouble(d2);
                    parcelObtain.writeStrongBinder(iSearchAroundCallBack != null ? iSearchAroundCallBack.asBinder() : null);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        boolean zSearchAround = Stub.getDefaultImpl().searchAround(i, i2, str, i3, d, d2, iSearchAroundCallBack);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return zSearchAround;
                    }
                    parcelObtain2.readException();
                    boolean z = parcelObtain2.readInt() != 0;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean getHistoryPois(int i, IGetHistoryPoisCallBack iGetHistoryPoisCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iGetHistoryPoisCallBack != null ? iGetHistoryPoisCallBack.asBinder() : null);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHistoryPois(i, iGetHistoryPoisCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean getRouteInfo(IGetRouteInfoCallBack iGetRouteInfoCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGetRouteInfoCallBack != null ? iGetRouteInfoCallBack.asBinder() : null);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRouteInfo(iGetRouteInfoCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean getFrontTraffic(int i, IGetFrontTrafficCallBack iGetFrontTrafficCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iGetFrontTrafficCallBack != null ? iGetFrontTrafficCallBack.asBinder() : null);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFrontTraffic(i, iGetFrontTrafficCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean getNaviMuteMode(IGetNaviMuteModeCallBack iGetNaviMuteModeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGetNaviMuteModeCallBack != null ? iGetNaviMuteModeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNaviMuteMode(iGetNaviMuteModeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean getGuideInfo(IGetGuideInfoCallBack iGetGuideInfoCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGetGuideInfoCallBack != null ? iGetGuideInfoCallBack.asBinder() : null);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGuideInfo(iGetGuideInfoCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean goFavorite(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().goFavorite(i, iResultCodeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean requestGeoDecoder(double d, double d2, IRequestGeoDecoderCallBack iRequestGeoDecoderCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeDouble(d);
                    parcelObtain.writeDouble(d2);
                    parcelObtain.writeStrongBinder(iRequestGeoDecoderCallBack != null ? iRequestGeoDecoderCallBack.asBinder() : null);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestGeoDecoder(d, d2, iRequestGeoDecoderCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean getMapLevelInfo(IGetMapLevelInfoCallBack iGetMapLevelInfoCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGetMapLevelInfoCallBack != null ? iGetMapLevelInfoCallBack.asBinder() : null);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMapLevelInfo(iGetMapLevelInfoCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean removeWayPoint(int i, int i2, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeWayPoint(i, i2, iResultCodeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean getCloudRegistrationStatus(IGetCloudRegistrationStatusCallBack iGetCloudRegistrationStatusCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGetCloudRegistrationStatusCallBack != null ? iGetCloudRegistrationStatusCallBack.asBinder() : null);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCloudRegistrationStatus(iGetCloudRegistrationStatusCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean resumeGuidance(int i, int i2, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resumeGuidance(i, i2, iResultCodeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean requestlRecommendedGasStations(int i, IResultGasStationCallBack iResultGasStationCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iResultGasStationCallBack != null ? iResultGasStationCallBack.asBinder() : null);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestlRecommendedGasStations(i, iResultGasStationCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean vrMsgCommand(int i, int i2, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().vrMsgCommand(i, i2, iResultCodeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public boolean setLoggedInState(boolean z, String str, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setLoggedInState(z, str, iResultCodeCallBack);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public void requestJumpToViewPage(int i, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestJumpToViewPage(i, iResultCodeCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public void addSurface(Surface surface, int i, int i2, int i3, int i4, int i5, int i6, int i7) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (surface != null) {
                        parcelObtain.writeInt(1);
                        surface.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i5);
                    parcelObtain.writeInt(i6);
                    parcelObtain.writeInt(i7);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addSurface(surface, i, i2, i3, i4, i5, i6, i7);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public void removedSurface(Surface surface, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (surface != null) {
                        parcelObtain.writeInt(1);
                        surface.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removedSurface(surface, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public void setMuteStatus(boolean z, IResultCodeCallBack iResultCodeCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStrongBinder(iResultCodeCallBack != null ? iResultCodeCallBack.asBinder() : null);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMuteStatus(z, iResultCodeCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public void getDetailInfo(PoiInfo poiInfo, int i, IDetailInfoCallback iDetailInfoCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (poiInfo != null) {
                        parcelObtain.writeInt(1);
                        poiInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iDetailInfoCallback != null ? iDetailInfoCallback.asBinder() : null);
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getDetailInfo(poiInfo, i, iDetailInfoCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public void searchEVChargingStation(int i, String str, int i2, LatLng latLng, IEVChargingStationCallback iEVChargingStationCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    if (latLng != null) {
                        parcelObtain.writeInt(1);
                        latLng.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iEVChargingStationCallback != null ? iEVChargingStationCallback.asBinder() : null);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().searchEVChargingStation(i, str, i2, latLng, iEVChargingStationCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviInterface
            public void getEVDetailInfo(EVChargingStation eVChargingStation, int i, IDetailInfoCallback iDetailInfoCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (eVChargingStation != null) {
                        parcelObtain.writeInt(1);
                        eVChargingStation.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iDetailInfoCallback != null ? iDetailInfoCallback.asBinder() : null);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getEVDetailInfo(eVChargingStation, i, iDetailInfoCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISmartNaviInterface iSmartNaviInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSmartNaviInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSmartNaviInterface;
            return true;
        }

        public static ISmartNaviInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
