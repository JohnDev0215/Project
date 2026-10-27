package com.neusoft.na.ocservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
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
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface ISmartNaviNotificationInterface extends IInterface {

    public static class Default implements ISmartNaviNotificationInterface {
        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void adareaChange(String str, String str2, String str3, String str4, String str5, String str6, String str7) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void addItemToFavorites(int i, FavoriteItem favoriteItem, IAddItemToFavoritesCallBack iAddItemToFavoritesCallBack) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void arrivedDest() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void availableRestoreGuidance(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void cloudRegistrationStatus(boolean z) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void commutePush(int i, int i2, int i3, List<TrafficInfo> list) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void dayNightStatusUpdate(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void favoritesInfoChanged(List<FavoritePoiInfo> list) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void getFavoriteList(int i, IGetFavoriteListCallBack iGetFavoriteListCallBack) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void getLoggedInState(ILoggedInStateCallback iLoggedInStateCallback) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void guidanceInfoUpdate(int i, int i2, int i3, int i4, int i5, String str, String str2, float f, int i6, int i7, int i8, String str3, int i9, int i10, int i11, int i12) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void highwayInfoUpdate(int i, String str, String str2, long j, long j2) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void lanesInfo(boolean z, List<LaneInfo> list) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void mapStatusUpdate(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void muteModeUpdate(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void naviStatusUpdate(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void onArrivedLastMile(PoiInfo poiInfo) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void onDRPositionUpdate(DRPosition dRPosition) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void onGuidanceViewChanged(boolean z) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void onMapStatusChanged(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void onSpeedLimitInfoUpdate(boolean z, int i, boolean z2) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void removeFavoriteItems(int i, String str, int i2, IFavoritesCallBack iFavoritesCallBack) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public boolean reqPhoneCallByNumber(String str) throws RemoteException {
            return false;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void reqSetelServiceStatus(IBooleanCallback iBooleanCallback) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void requestExpirationDate(IExpirationDateCallBack iExpirationDateCallBack) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void requestForecastWeather(int i, GeoPos geoPos, IForecastWeatherCallBack iForecastWeatherCallBack) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void requestObserveWeather(int i, GeoPos geoPos, IObserveWeatherCallBack iObserveWeatherCallBack) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void requestSetelData(SetelData setelData, ISetelDataCallBack iSetelDataCallBack) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void requestSetelDetail(int i, String str, IRequestSetelDetailCallBack iRequestSetelDetailCallBack) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void requestVRCommand(int i, int i2) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void routeInfoUpdate(int i, List<RouteInfo> list) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void routeSelectedChaned(int i, List<RouteInfo> list) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void routeViewStatusUpdate(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void serviceStatusChanged(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void trafficCondition(int i, int i2, List<TrafficInfo> list) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void trafficStatusUpdate(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void updateFavoriteItem(int i, FavoriteItem favoriteItem, IFavoritesCallBack iFavoritesCallBack) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void viewModeUpdate(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
        public void zoomStatusUpdate(int i) throws RemoteException {
        }
    }

    void adareaChange(String str, String str2, String str3, String str4, String str5, String str6, String str7) throws RemoteException;

    void addItemToFavorites(int i, FavoriteItem favoriteItem, IAddItemToFavoritesCallBack iAddItemToFavoritesCallBack) throws RemoteException;

    void arrivedDest() throws RemoteException;

    void availableRestoreGuidance(int i) throws RemoteException;

    void cloudRegistrationStatus(boolean z) throws RemoteException;

    void commutePush(int i, int i2, int i3, List<TrafficInfo> list) throws RemoteException;

    void dayNightStatusUpdate(int i) throws RemoteException;

    void favoritesInfoChanged(List<FavoritePoiInfo> list) throws RemoteException;

    void getFavoriteList(int i, IGetFavoriteListCallBack iGetFavoriteListCallBack) throws RemoteException;

    void getLoggedInState(ILoggedInStateCallback iLoggedInStateCallback) throws RemoteException;

    void guidanceInfoUpdate(int i, int i2, int i3, int i4, int i5, String str, String str2, float f, int i6, int i7, int i8, String str3, int i9, int i10, int i11, int i12) throws RemoteException;

    void highwayInfoUpdate(int i, String str, String str2, long j, long j2) throws RemoteException;

    void lanesInfo(boolean z, List<LaneInfo> list) throws RemoteException;

    void mapStatusUpdate(int i) throws RemoteException;

    void muteModeUpdate(int i) throws RemoteException;

    void naviStatusUpdate(int i) throws RemoteException;

    void onArrivedLastMile(PoiInfo poiInfo) throws RemoteException;

    void onDRPositionUpdate(DRPosition dRPosition) throws RemoteException;

    void onGuidanceViewChanged(boolean z) throws RemoteException;

    void onMapStatusChanged(int i) throws RemoteException;

    void onSpeedLimitInfoUpdate(boolean z, int i, boolean z2) throws RemoteException;

    void removeFavoriteItems(int i, String str, int i2, IFavoritesCallBack iFavoritesCallBack) throws RemoteException;

    boolean reqPhoneCallByNumber(String str) throws RemoteException;

    void reqSetelServiceStatus(IBooleanCallback iBooleanCallback) throws RemoteException;

    void requestExpirationDate(IExpirationDateCallBack iExpirationDateCallBack) throws RemoteException;

    void requestForecastWeather(int i, GeoPos geoPos, IForecastWeatherCallBack iForecastWeatherCallBack) throws RemoteException;

    void requestObserveWeather(int i, GeoPos geoPos, IObserveWeatherCallBack iObserveWeatherCallBack) throws RemoteException;

    void requestSetelData(SetelData setelData, ISetelDataCallBack iSetelDataCallBack) throws RemoteException;

    void requestSetelDetail(int i, String str, IRequestSetelDetailCallBack iRequestSetelDetailCallBack) throws RemoteException;

    void requestVRCommand(int i, int i2) throws RemoteException;

    void routeInfoUpdate(int i, List<RouteInfo> list) throws RemoteException;

    void routeSelectedChaned(int i, List<RouteInfo> list) throws RemoteException;

    void routeViewStatusUpdate(int i) throws RemoteException;

    void serviceStatusChanged(int i) throws RemoteException;

    void trafficCondition(int i, int i2, List<TrafficInfo> list) throws RemoteException;

    void trafficStatusUpdate(int i) throws RemoteException;

    void updateFavoriteItem(int i, FavoriteItem favoriteItem, IFavoritesCallBack iFavoritesCallBack) throws RemoteException;

    void viewModeUpdate(int i) throws RemoteException;

    void zoomStatusUpdate(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ISmartNaviNotificationInterface {
        private static final String DESCRIPTOR = "com.neusoft.na.ocservice.ISmartNaviNotificationInterface";
        static final int TRANSACTION_adareaChange = 17;
        static final int TRANSACTION_addItemToFavorites = 29;
        static final int TRANSACTION_arrivedDest = 15;
        static final int TRANSACTION_availableRestoreGuidance = 25;
        static final int TRANSACTION_cloudRegistrationStatus = 24;
        static final int TRANSACTION_commutePush = 19;
        static final int TRANSACTION_dayNightStatusUpdate = 8;
        static final int TRANSACTION_favoritesInfoChanged = 11;
        static final int TRANSACTION_getFavoriteList = 32;
        static final int TRANSACTION_getLoggedInState = 33;
        static final int TRANSACTION_guidanceInfoUpdate = 3;
        static final int TRANSACTION_highwayInfoUpdate = 14;
        static final int TRANSACTION_lanesInfo = 18;
        static final int TRANSACTION_mapStatusUpdate = 4;
        static final int TRANSACTION_muteModeUpdate = 10;
        static final int TRANSACTION_naviStatusUpdate = 2;
        static final int TRANSACTION_onArrivedLastMile = 39;
        static final int TRANSACTION_onDRPositionUpdate = 36;
        static final int TRANSACTION_onGuidanceViewChanged = 37;
        static final int TRANSACTION_onMapStatusChanged = 35;
        static final int TRANSACTION_onSpeedLimitInfoUpdate = 38;
        static final int TRANSACTION_removeFavoriteItems = 31;
        static final int TRANSACTION_reqPhoneCallByNumber = 20;
        static final int TRANSACTION_reqSetelServiceStatus = 26;
        static final int TRANSACTION_requestExpirationDate = 23;
        static final int TRANSACTION_requestForecastWeather = 22;
        static final int TRANSACTION_requestObserveWeather = 21;
        static final int TRANSACTION_requestSetelData = 27;
        static final int TRANSACTION_requestSetelDetail = 34;
        static final int TRANSACTION_requestVRCommand = 28;
        static final int TRANSACTION_routeInfoUpdate = 12;
        static final int TRANSACTION_routeSelectedChaned = 13;
        static final int TRANSACTION_routeViewStatusUpdate = 5;
        static final int TRANSACTION_serviceStatusChanged = 1;
        static final int TRANSACTION_trafficCondition = 16;
        static final int TRANSACTION_trafficStatusUpdate = 6;
        static final int TRANSACTION_updateFavoriteItem = 30;
        static final int TRANSACTION_viewModeUpdate = 9;
        static final int TRANSACTION_zoomStatusUpdate = 7;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISmartNaviNotificationInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISmartNaviNotificationInterface)) {
                return (ISmartNaviNotificationInterface) iInterfaceQueryLocalInterface;
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
                    serviceStatusChanged(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    naviStatusUpdate(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    guidanceInfoUpdate(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readFloat(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    mapStatusUpdate(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    routeViewStatusUpdate(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    trafficStatusUpdate(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    zoomStatusUpdate(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    dayNightStatusUpdate(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    viewModeUpdate(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    muteModeUpdate(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    favoritesInfoChanged(parcel.createTypedArrayList(FavoritePoiInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    routeInfoUpdate(parcel.readInt(), parcel.createTypedArrayList(RouteInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    routeSelectedChaned(parcel.readInt(), parcel.createTypedArrayList(RouteInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    highwayInfoUpdate(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    arrivedDest();
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    trafficCondition(parcel.readInt(), parcel.readInt(), parcel.createTypedArrayList(TrafficInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    adareaChange(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    lanesInfo(parcel.readInt() != 0, parcel.createTypedArrayList(LaneInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    commutePush(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createTypedArrayList(TrafficInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqPhoneCallByNumber = reqPhoneCallByNumber(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqPhoneCallByNumber ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestObserveWeather(parcel.readInt(), parcel.readInt() != 0 ? GeoPos.CREATOR.createFromParcel(parcel) : null, IObserveWeatherCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestForecastWeather(parcel.readInt(), parcel.readInt() != 0 ? GeoPos.CREATOR.createFromParcel(parcel) : null, IForecastWeatherCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestExpirationDate(IExpirationDateCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    cloudRegistrationStatus(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    availableRestoreGuidance(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    reqSetelServiceStatus(IBooleanCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestSetelData(parcel.readInt() != 0 ? SetelData.CREATOR.createFromParcel(parcel) : null, ISetelDataCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestVRCommand(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    addItemToFavorites(parcel.readInt(), parcel.readInt() != 0 ? FavoriteItem.CREATOR.createFromParcel(parcel) : null, IAddItemToFavoritesCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateFavoriteItem(parcel.readInt(), parcel.readInt() != 0 ? FavoriteItem.CREATOR.createFromParcel(parcel) : null, IFavoritesCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeFavoriteItems(parcel.readInt(), parcel.readString(), parcel.readInt(), IFavoritesCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    getFavoriteList(parcel.readInt(), IGetFavoriteListCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    getLoggedInState(ILoggedInStateCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestSetelDetail(parcel.readInt(), parcel.readString(), IRequestSetelDetailCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    onMapStatusChanged(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDRPositionUpdate(parcel.readInt() != 0 ? DRPosition.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    onGuidanceViewChanged(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSpeedLimitInfoUpdate(parcel.readInt() != 0, parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    onArrivedLastMile(parcel.readInt() != 0 ? PoiInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ISmartNaviNotificationInterface {
            public static ISmartNaviNotificationInterface sDefaultImpl;
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

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void serviceStatusChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().serviceStatusChanged(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void naviStatusUpdate(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().naviStatusUpdate(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void guidanceInfoUpdate(int i, int i2, int i3, int i4, int i5, String str, String str2, float f, int i6, int i7, int i8, String str3, int i9, int i10, int i11, int i12) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i5);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeFloat(f);
                    parcelObtain.writeInt(i6);
                    parcelObtain.writeInt(i7);
                    parcelObtain.writeInt(i8);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i9);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    parcelObtain.writeInt(i12);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().guidanceInfoUpdate(i, i2, i3, i4, i5, str, str2, f, i6, i7, i8, str3, i9, i10, i11, i12);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void mapStatusUpdate(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().mapStatusUpdate(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void routeViewStatusUpdate(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().routeViewStatusUpdate(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void trafficStatusUpdate(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().trafficStatusUpdate(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void zoomStatusUpdate(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().zoomStatusUpdate(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void dayNightStatusUpdate(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dayNightStatusUpdate(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void viewModeUpdate(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().viewModeUpdate(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void muteModeUpdate(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().muteModeUpdate(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void favoritesInfoChanged(List<FavoritePoiInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().favoritesInfoChanged(list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void routeInfoUpdate(int i, List<RouteInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().routeInfoUpdate(i, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void routeSelectedChaned(int i, List<RouteInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().routeSelectedChaned(i, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void highwayInfoUpdate(int i, String str, String str2, long j, long j2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().highwayInfoUpdate(i, str, str2, j, j2);
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

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void arrivedDest() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().arrivedDest();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void trafficCondition(int i, int i2, List<TrafficInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().trafficCondition(i, i2, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void adareaChange(String str, String str2, String str3, String str4, String str5, String str6, String str7) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeString(str4);
                    parcelObtain.writeString(str5);
                    parcelObtain.writeString(str6);
                    parcelObtain.writeString(str7);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().adareaChange(str, str2, str3, str4, str5, str6, str7);
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

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void lanesInfo(boolean z, List<LaneInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().lanesInfo(z, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void commutePush(int i, int i2, int i3, List<TrafficInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().commutePush(i, i2, i3, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public boolean reqPhoneCallByNumber(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqPhoneCallByNumber(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void requestObserveWeather(int i, GeoPos geoPos, IObserveWeatherCallBack iObserveWeatherCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (geoPos != null) {
                        parcelObtain.writeInt(1);
                        geoPos.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iObserveWeatherCallBack != null ? iObserveWeatherCallBack.asBinder() : null);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestObserveWeather(i, geoPos, iObserveWeatherCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void requestForecastWeather(int i, GeoPos geoPos, IForecastWeatherCallBack iForecastWeatherCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (geoPos != null) {
                        parcelObtain.writeInt(1);
                        geoPos.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iForecastWeatherCallBack != null ? iForecastWeatherCallBack.asBinder() : null);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestForecastWeather(i, geoPos, iForecastWeatherCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void requestExpirationDate(IExpirationDateCallBack iExpirationDateCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iExpirationDateCallBack != null ? iExpirationDateCallBack.asBinder() : null);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestExpirationDate(iExpirationDateCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void cloudRegistrationStatus(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cloudRegistrationStatus(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void availableRestoreGuidance(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().availableRestoreGuidance(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void reqSetelServiceStatus(IBooleanCallback iBooleanCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBooleanCallback != null ? iBooleanCallback.asBinder() : null);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reqSetelServiceStatus(iBooleanCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void requestSetelData(SetelData setelData, ISetelDataCallBack iSetelDataCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (setelData != null) {
                        parcelObtain.writeInt(1);
                        setelData.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iSetelDataCallBack != null ? iSetelDataCallBack.asBinder() : null);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestSetelData(setelData, iSetelDataCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void requestVRCommand(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestVRCommand(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void addItemToFavorites(int i, FavoriteItem favoriteItem, IAddItemToFavoritesCallBack iAddItemToFavoritesCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (favoriteItem != null) {
                        parcelObtain.writeInt(1);
                        favoriteItem.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iAddItemToFavoritesCallBack != null ? iAddItemToFavoritesCallBack.asBinder() : null);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addItemToFavorites(i, favoriteItem, iAddItemToFavoritesCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void updateFavoriteItem(int i, FavoriteItem favoriteItem, IFavoritesCallBack iFavoritesCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (favoriteItem != null) {
                        parcelObtain.writeInt(1);
                        favoriteItem.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iFavoritesCallBack != null ? iFavoritesCallBack.asBinder() : null);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateFavoriteItem(i, favoriteItem, iFavoritesCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void removeFavoriteItems(int i, String str, int i2, IFavoritesCallBack iFavoritesCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iFavoritesCallBack != null ? iFavoritesCallBack.asBinder() : null);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeFavoriteItems(i, str, i2, iFavoritesCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void getFavoriteList(int i, IGetFavoriteListCallBack iGetFavoriteListCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iGetFavoriteListCallBack != null ? iGetFavoriteListCallBack.asBinder() : null);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getFavoriteList(i, iGetFavoriteListCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void getLoggedInState(ILoggedInStateCallback iLoggedInStateCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iLoggedInStateCallback != null ? iLoggedInStateCallback.asBinder() : null);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getLoggedInState(iLoggedInStateCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void requestSetelDetail(int i, String str, IRequestSetelDetailCallBack iRequestSetelDetailCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iRequestSetelDetailCallBack != null ? iRequestSetelDetailCallBack.asBinder() : null);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestSetelDetail(i, str, iRequestSetelDetailCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void onMapStatusChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMapStatusChanged(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void onDRPositionUpdate(DRPosition dRPosition) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (dRPosition != null) {
                        parcelObtain.writeInt(1);
                        dRPosition.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDRPositionUpdate(dRPosition);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void onGuidanceViewChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGuidanceViewChanged(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void onSpeedLimitInfoUpdate(boolean z, int i, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i2 = 1;
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!z2) {
                        i2 = 0;
                    }
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSpeedLimitInfoUpdate(z, i, z2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviNotificationInterface
            public void onArrivedLastMile(PoiInfo poiInfo) throws RemoteException {
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
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onArrivedLastMile(poiInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISmartNaviNotificationInterface iSmartNaviNotificationInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSmartNaviNotificationInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSmartNaviNotificationInterface;
            return true;
        }

        public static ISmartNaviNotificationInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
