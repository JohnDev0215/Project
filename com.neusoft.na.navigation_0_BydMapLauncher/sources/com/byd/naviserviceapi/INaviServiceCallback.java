package com.byd.naviserviceapi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface INaviServiceCallback extends IInterface {

    public static class Default implements INaviServiceCallback {
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
        public void onReqNaviInfo(int code, int routeOption, Place startPosition, Place destinationPosition, List<Place> viaPositions) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onReqNaviSetRouteOption(int code) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onReqUnfinishedRoute(int code, int routeOption, Place destinationPosition, List<Place> viaPositions) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onRouteFullView(int code) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onRoutePlanning(int code, List<RouteInfo> routes) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onRoutePlanningUpdate(int code) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onSetAddress(int code, int locationType, Place place) throws RemoteException {
        }

        @Override // com.byd.naviserviceapi.INaviServiceCallback
        public void onSmartRoutePlanning(int code, RouteInfo route) throws RemoteException {
        }
    }

    void onNaviAddViaPosition(int code) throws RemoteException;

    void onNaviNextViaTimeDistance(int code, long seconds, double distance) throws RemoteException;

    void onNaviRemainTimeDistance(int code, long seconds, double distance) throws RemoteException;

    void onNaviSupportedOptionResult(int code, List<String> options) throws RemoteException;

    void onNaviViaPositionResult(int code, List<Place> vias) throws RemoteException;

    void onReqNaviAddFavorite(int code, Place place) throws RemoteException;

    void onReqNaviGetRouteOption(int code, int routeOption) throws RemoteException;

    void onReqNaviInfo(int code, int routeOption, Place startPosition, Place destinationPosition, List<Place> viaPositions) throws RemoteException;

    void onReqNaviSetRouteOption(int code) throws RemoteException;

    void onReqUnfinishedRoute(int code, int routeOption, Place destinationPosition, List<Place> viaPositions) throws RemoteException;

    void onRouteFullView(int code) throws RemoteException;

    void onRoutePlanning(int code, List<RouteInfo> routes) throws RemoteException;

    void onRoutePlanningUpdate(int code) throws RemoteException;

    void onSetAddress(int code, int locationType, Place place) throws RemoteException;

    void onSmartRoutePlanning(int code, RouteInfo route) throws RemoteException;

    public static abstract class Stub extends Binder implements INaviServiceCallback {
        private static final String DESCRIPTOR = "com.byd.naviserviceapi.INaviServiceCallback";
        static final int TRANSACTION_onNaviAddViaPosition = 8;
        static final int TRANSACTION_onNaviNextViaTimeDistance = 13;
        static final int TRANSACTION_onNaviRemainTimeDistance = 5;
        static final int TRANSACTION_onNaviSupportedOptionResult = 1;
        static final int TRANSACTION_onNaviViaPositionResult = 3;
        static final int TRANSACTION_onReqNaviAddFavorite = 4;
        static final int TRANSACTION_onReqNaviGetRouteOption = 11;
        static final int TRANSACTION_onReqNaviInfo = 7;
        static final int TRANSACTION_onReqNaviSetRouteOption = 6;
        static final int TRANSACTION_onReqUnfinishedRoute = 12;
        static final int TRANSACTION_onRouteFullView = 10;
        static final int TRANSACTION_onRoutePlanning = 9;
        static final int TRANSACTION_onRoutePlanningUpdate = 15;
        static final int TRANSACTION_onSetAddress = 2;
        static final int TRANSACTION_onSmartRoutePlanning = 14;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INaviServiceCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INaviServiceCallback)) {
                return (INaviServiceCallback) iInterfaceQueryLocalInterface;
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
                    onNaviSupportedOptionResult(parcel.readInt(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSetAddress(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? Place.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNaviViaPositionResult(parcel.readInt(), parcel.createTypedArrayList(Place.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onReqNaviAddFavorite(parcel.readInt(), parcel.readInt() != 0 ? Place.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNaviRemainTimeDistance(parcel.readInt(), parcel.readLong(), parcel.readDouble());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onReqNaviSetRouteOption(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onReqNaviInfo(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? Place.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Place.CREATOR.createFromParcel(parcel) : null, parcel.createTypedArrayList(Place.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNaviAddViaPosition(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRoutePlanning(parcel.readInt(), parcel.createTypedArrayList(RouteInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRouteFullView(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    onReqNaviGetRouteOption(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    onReqUnfinishedRoute(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? Place.CREATOR.createFromParcel(parcel) : null, parcel.createTypedArrayList(Place.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNaviNextViaTimeDistance(parcel.readInt(), parcel.readLong(), parcel.readDouble());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSmartRoutePlanning(parcel.readInt(), parcel.readInt() != 0 ? RouteInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRoutePlanningUpdate(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements INaviServiceCallback {
            public static INaviServiceCallback sDefaultImpl;
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

            @Override // com.byd.naviserviceapi.INaviServiceCallback
            public void onNaviSupportedOptionResult(int code, List<String> options) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(code);
                    parcelObtain.writeStringList(options);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNaviSupportedOptionResult(code, options);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviServiceCallback
            public void onSetAddress(int code, int locationType, Place place) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(code);
                    parcelObtain.writeInt(locationType);
                    if (place != null) {
                        parcelObtain.writeInt(1);
                        place.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSetAddress(code, locationType, place);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviServiceCallback
            public void onNaviViaPositionResult(int code, List<Place> vias) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(code);
                    parcelObtain.writeTypedList(vias);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNaviViaPositionResult(code, vias);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviServiceCallback
            public void onReqNaviAddFavorite(int code, Place place) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(code);
                    if (place != null) {
                        parcelObtain.writeInt(1);
                        place.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onReqNaviAddFavorite(code, place);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviServiceCallback
            public void onNaviRemainTimeDistance(int code, long seconds, double distance) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(code);
                    parcelObtain.writeLong(seconds);
                    parcelObtain.writeDouble(distance);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNaviRemainTimeDistance(code, seconds, distance);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviServiceCallback
            public void onReqNaviSetRouteOption(int code) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(code);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onReqNaviSetRouteOption(code);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviServiceCallback
            public void onReqNaviInfo(int code, int routeOption, Place startPosition, Place destinationPosition, List<Place> viaPositions) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(code);
                    parcelObtain.writeInt(routeOption);
                    if (startPosition != null) {
                        parcelObtain.writeInt(1);
                        startPosition.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (destinationPosition != null) {
                        parcelObtain.writeInt(1);
                        destinationPosition.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedList(viaPositions);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onReqNaviInfo(code, routeOption, startPosition, destinationPosition, viaPositions);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviServiceCallback
            public void onNaviAddViaPosition(int code) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(code);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNaviAddViaPosition(code);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviServiceCallback
            public void onRoutePlanning(int code, List<RouteInfo> routes) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(code);
                    parcelObtain.writeTypedList(routes);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRoutePlanning(code, routes);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviServiceCallback
            public void onRouteFullView(int code) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(code);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRouteFullView(code);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviServiceCallback
            public void onReqNaviGetRouteOption(int code, int routeOption) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(code);
                    parcelObtain.writeInt(routeOption);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onReqNaviGetRouteOption(code, routeOption);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviServiceCallback
            public void onReqUnfinishedRoute(int code, int routeOption, Place destinationPosition, List<Place> viaPositions) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(code);
                    parcelObtain.writeInt(routeOption);
                    if (destinationPosition != null) {
                        parcelObtain.writeInt(1);
                        destinationPosition.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedList(viaPositions);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onReqUnfinishedRoute(code, routeOption, destinationPosition, viaPositions);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviServiceCallback
            public void onNaviNextViaTimeDistance(int code, long seconds, double distance) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(code);
                    parcelObtain.writeLong(seconds);
                    parcelObtain.writeDouble(distance);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNaviNextViaTimeDistance(code, seconds, distance);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviServiceCallback
            public void onSmartRoutePlanning(int code, RouteInfo route) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(code);
                    if (route != null) {
                        parcelObtain.writeInt(1);
                        route.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSmartRoutePlanning(code, route);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.naviserviceapi.INaviServiceCallback
            public void onRoutePlanningUpdate(int code) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(code);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRoutePlanningUpdate(code);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INaviServiceCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static INaviServiceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
