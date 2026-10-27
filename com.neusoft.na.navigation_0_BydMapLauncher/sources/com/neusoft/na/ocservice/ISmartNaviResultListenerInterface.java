package com.neusoft.na.ocservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface ISmartNaviResultListenerInterface extends IInterface {

    public static class Default implements ISmartNaviResultListenerInterface {
        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void alongTheWaySearchResult(int i, List<SuggestionCity> list, List<PoiInfo> list2) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void backToMapResult(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void cancelNaviResult(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void editFavoritePoisResult(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void exitMapResult(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void getFavoritePoisResult(int i, List<FavoritePoiInfo> list) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void getFrontTrafficResult(int i, String str) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void getGuideInfoResult(int i, int i2, int i3, int i4, int i5, int i6, String str, String str2, float f, int i7, int i8, int i9, String str3, int i10, int i11, int i12, int i13) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void getHighwayExitInfoResult(int i, int i2, String str, String str2, long j, long j2) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void getHistoryPoisResult(int i, List<PoiInfo> list) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void getNaviMuteModeResult(int i, int i2) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void getRouteInfoResult(int i, PoiInfo poiInfo, PoiInfo poiInfo2, List<RouteInfo> list) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void getTrafficSummaryInfoResult(int i, String str) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void launchMapResult(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void mapZoomInOutResult(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void modifyNaviViaResult(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void naviFullViewResult(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void openDestinationSearchResult(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void requestRoutePlanOrNaviResult(int i, List<RouteInfo> list) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void searchAroundResult(int i, List<SuggestionCity> list, List<PoiInfo> list2) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void searchByKeywordResult(int i, List<SuggestionCity> list, List<PoiInfo> list2) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void selectRouteResult(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void setMapViewModeResult(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void setNaviMuteModeResult(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void setRoutePreferResult(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void showMyLocationResult(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, double d, double d2) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void specialPoiNaviResult(int i) throws RemoteException {
        }

        @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
        public void switchTrafficResult(int i) throws RemoteException {
        }
    }

    void alongTheWaySearchResult(int i, List<SuggestionCity> list, List<PoiInfo> list2) throws RemoteException;

    void backToMapResult(int i) throws RemoteException;

    void cancelNaviResult(int i) throws RemoteException;

    void editFavoritePoisResult(int i) throws RemoteException;

    void exitMapResult(int i) throws RemoteException;

    void getFavoritePoisResult(int i, List<FavoritePoiInfo> list) throws RemoteException;

    void getFrontTrafficResult(int i, String str) throws RemoteException;

    void getGuideInfoResult(int i, int i2, int i3, int i4, int i5, int i6, String str, String str2, float f, int i7, int i8, int i9, String str3, int i10, int i11, int i12, int i13) throws RemoteException;

    void getHighwayExitInfoResult(int i, int i2, String str, String str2, long j, long j2) throws RemoteException;

    void getHistoryPoisResult(int i, List<PoiInfo> list) throws RemoteException;

    void getNaviMuteModeResult(int i, int i2) throws RemoteException;

    void getRouteInfoResult(int i, PoiInfo poiInfo, PoiInfo poiInfo2, List<RouteInfo> list) throws RemoteException;

    void getTrafficSummaryInfoResult(int i, String str) throws RemoteException;

    void launchMapResult(int i) throws RemoteException;

    void mapZoomInOutResult(int i) throws RemoteException;

    void modifyNaviViaResult(int i) throws RemoteException;

    void naviFullViewResult(int i) throws RemoteException;

    void openDestinationSearchResult(int i) throws RemoteException;

    void requestRoutePlanOrNaviResult(int i, List<RouteInfo> list) throws RemoteException;

    void searchAroundResult(int i, List<SuggestionCity> list, List<PoiInfo> list2) throws RemoteException;

    void searchByKeywordResult(int i, List<SuggestionCity> list, List<PoiInfo> list2) throws RemoteException;

    void selectRouteResult(int i) throws RemoteException;

    void setMapViewModeResult(int i) throws RemoteException;

    void setNaviMuteModeResult(int i) throws RemoteException;

    void setRoutePreferResult(int i) throws RemoteException;

    void showMyLocationResult(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, double d, double d2) throws RemoteException;

    void specialPoiNaviResult(int i) throws RemoteException;

    void switchTrafficResult(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ISmartNaviResultListenerInterface {
        private static final String DESCRIPTOR = "com.neusoft.na.ocservice.ISmartNaviResultListenerInterface";
        static final int TRANSACTION_alongTheWaySearchResult = 23;
        static final int TRANSACTION_backToMapResult = 2;
        static final int TRANSACTION_cancelNaviResult = 4;
        static final int TRANSACTION_editFavoritePoisResult = 13;
        static final int TRANSACTION_exitMapResult = 3;
        static final int TRANSACTION_getFavoritePoisResult = 14;
        static final int TRANSACTION_getFrontTrafficResult = 15;
        static final int TRANSACTION_getGuideInfoResult = 17;
        static final int TRANSACTION_getHighwayExitInfoResult = 18;
        static final int TRANSACTION_getHistoryPoisResult = 19;
        static final int TRANSACTION_getNaviMuteModeResult = 27;
        static final int TRANSACTION_getRouteInfoResult = 20;
        static final int TRANSACTION_getTrafficSummaryInfoResult = 16;
        static final int TRANSACTION_launchMapResult = 1;
        static final int TRANSACTION_mapZoomInOutResult = 6;
        static final int TRANSACTION_modifyNaviViaResult = 26;
        static final int TRANSACTION_naviFullViewResult = 8;
        static final int TRANSACTION_openDestinationSearchResult = 5;
        static final int TRANSACTION_requestRoutePlanOrNaviResult = 25;
        static final int TRANSACTION_searchAroundResult = 22;
        static final int TRANSACTION_searchByKeywordResult = 21;
        static final int TRANSACTION_selectRouteResult = 12;
        static final int TRANSACTION_setMapViewModeResult = 7;
        static final int TRANSACTION_setNaviMuteModeResult = 28;
        static final int TRANSACTION_setRoutePreferResult = 11;
        static final int TRANSACTION_showMyLocationResult = 10;
        static final int TRANSACTION_specialPoiNaviResult = 24;
        static final int TRANSACTION_switchTrafficResult = 9;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISmartNaviResultListenerInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISmartNaviResultListenerInterface)) {
                return (ISmartNaviResultListenerInterface) iInterfaceQueryLocalInterface;
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
                    launchMapResult(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    backToMapResult(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    exitMapResult(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelNaviResult(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    openDestinationSearchResult(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    mapZoomInOutResult(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMapViewModeResult(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    naviFullViewResult(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    switchTrafficResult(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    showMyLocationResult(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readDouble(), parcel.readDouble());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRoutePreferResult(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    selectRouteResult(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    editFavoritePoisResult(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    getFavoritePoisResult(parcel.readInt(), parcel.createTypedArrayList(FavoritePoiInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    getFrontTrafficResult(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    getTrafficSummaryInfoResult(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    getGuideInfoResult(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readFloat(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    getHighwayExitInfoResult(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    getHistoryPoisResult(parcel.readInt(), parcel.createTypedArrayList(PoiInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    getRouteInfoResult(parcel.readInt(), parcel.readInt() != 0 ? PoiInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? PoiInfo.CREATOR.createFromParcel(parcel) : null, parcel.createTypedArrayList(RouteInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    searchByKeywordResult(parcel.readInt(), parcel.createTypedArrayList(SuggestionCity.CREATOR), parcel.createTypedArrayList(PoiInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    searchAroundResult(parcel.readInt(), parcel.createTypedArrayList(SuggestionCity.CREATOR), parcel.createTypedArrayList(PoiInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    alongTheWaySearchResult(parcel.readInt(), parcel.createTypedArrayList(SuggestionCity.CREATOR), parcel.createTypedArrayList(PoiInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    specialPoiNaviResult(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestRoutePlanOrNaviResult(parcel.readInt(), parcel.createTypedArrayList(RouteInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    modifyNaviViaResult(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    getNaviMuteModeResult(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNaviMuteModeResult(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ISmartNaviResultListenerInterface {
            public static ISmartNaviResultListenerInterface sDefaultImpl;
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

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void launchMapResult(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().launchMapResult(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void backToMapResult(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().backToMapResult(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void exitMapResult(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().exitMapResult(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void cancelNaviResult(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelNaviResult(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void openDestinationSearchResult(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openDestinationSearchResult(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void mapZoomInOutResult(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().mapZoomInOutResult(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void setMapViewModeResult(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMapViewModeResult(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void naviFullViewResult(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().naviFullViewResult(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void switchTrafficResult(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().switchTrafficResult(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void showMyLocationResult(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, double d, double d2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeString(str4);
                    parcelObtain.writeString(str5);
                    parcelObtain.writeString(str6);
                    parcelObtain.writeString(str7);
                    parcelObtain.writeString(str8);
                    parcelObtain.writeDouble(d);
                    parcelObtain.writeDouble(d2);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showMyLocationResult(i, str, str2, str3, str4, str5, str6, str7, str8, d, d2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void setRoutePreferResult(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRoutePreferResult(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void selectRouteResult(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().selectRouteResult(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void editFavoritePoisResult(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().editFavoritePoisResult(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void getFavoritePoisResult(int i, List<FavoritePoiInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getFavoritePoisResult(i, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void getFrontTrafficResult(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getFrontTrafficResult(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void getTrafficSummaryInfoResult(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getTrafficSummaryInfoResult(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void getGuideInfoResult(int i, int i2, int i3, int i4, int i5, int i6, String str, String str2, float f, int i7, int i8, int i9, String str3, int i10, int i11, int i12, int i13) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i5);
                    parcelObtain.writeInt(i6);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeFloat(f);
                    parcelObtain.writeInt(i7);
                    parcelObtain.writeInt(i8);
                    parcelObtain.writeInt(i9);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i10);
                    parcelObtain.writeInt(i11);
                    parcelObtain.writeInt(i12);
                    parcelObtain.writeInt(i13);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getGuideInfoResult(i, i2, i3, i4, i5, i6, str, str2, f, i7, i8, i9, str3, i10, i11, i12, i13);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void getHighwayExitInfoResult(int i, int i2, String str, String str2, long j, long j2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    try {
                        if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().getHighwayExitInfoResult(i, i2, str, str2, j, j2);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        } else {
                            parcelObtain2.readException();
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        }
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void getHistoryPoisResult(int i, List<PoiInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getHistoryPoisResult(i, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void getRouteInfoResult(int i, PoiInfo poiInfo, PoiInfo poiInfo2, List<RouteInfo> list) throws RemoteException {
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
                    if (poiInfo2 != null) {
                        parcelObtain.writeInt(1);
                        poiInfo2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getRouteInfoResult(i, poiInfo, poiInfo2, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void searchByKeywordResult(int i, List<SuggestionCity> list, List<PoiInfo> list2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeTypedList(list2);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().searchByKeywordResult(i, list, list2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void searchAroundResult(int i, List<SuggestionCity> list, List<PoiInfo> list2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeTypedList(list2);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().searchAroundResult(i, list, list2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void alongTheWaySearchResult(int i, List<SuggestionCity> list, List<PoiInfo> list2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeTypedList(list2);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().alongTheWaySearchResult(i, list, list2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void specialPoiNaviResult(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().specialPoiNaviResult(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void requestRoutePlanOrNaviResult(int i, List<RouteInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestRoutePlanOrNaviResult(i, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void modifyNaviViaResult(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().modifyNaviViaResult(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void getNaviMuteModeResult(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getNaviMuteModeResult(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.neusoft.na.ocservice.ISmartNaviResultListenerInterface
            public void setNaviMuteModeResult(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNaviMuteModeResult(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISmartNaviResultListenerInterface iSmartNaviResultListenerInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSmartNaviResultListenerInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSmartNaviResultListenerInterface;
            return true;
        }

        public static ISmartNaviResultListenerInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
