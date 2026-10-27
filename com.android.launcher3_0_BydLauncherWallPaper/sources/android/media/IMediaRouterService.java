package android.media;

import android.media.IMediaRouter2;
import android.media.IMediaRouter2Manager;
import android.media.IMediaRouterClient;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IMediaRouterService extends IInterface {

    public static class Default implements IMediaRouterService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.IMediaRouterService
        public void deselectRouteWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void deselectRouteWithRouter2(IMediaRouter2 iMediaRouter2, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public List<RoutingSessionInfo> getActiveSessions(IMediaRouter2Manager iMediaRouter2Manager) throws RemoteException {
            return null;
        }

        @Override // android.media.IMediaRouterService
        public MediaRouterClientState getState(IMediaRouterClient iMediaRouterClient) throws RemoteException {
            return null;
        }

        @Override // android.media.IMediaRouterService
        public List<MediaRoute2Info> getSystemRoutes() throws RemoteException {
            return null;
        }

        @Override // android.media.IMediaRouterService
        public RoutingSessionInfo getSystemSessionInfo() throws RemoteException {
            return null;
        }

        @Override // android.media.IMediaRouterService
        public boolean isPlaybackActive(IMediaRouterClient iMediaRouterClient) throws RemoteException {
            return false;
        }

        @Override // android.media.IMediaRouterService
        public void registerClientAsUser(IMediaRouterClient iMediaRouterClient, String str, int i) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void registerClientGroupId(IMediaRouterClient iMediaRouterClient, String str) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void registerManager(IMediaRouter2Manager iMediaRouter2Manager, String str) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void registerRouter2(IMediaRouter2 iMediaRouter2, String str) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void releaseSessionWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void releaseSessionWithRouter2(IMediaRouter2 iMediaRouter2, String str) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void requestCreateSessionWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void requestCreateSessionWithRouter2(IMediaRouter2 iMediaRouter2, int i, long j, RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info, Bundle bundle) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void requestSetVolume(IMediaRouterClient iMediaRouterClient, String str, int i) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void requestUpdateVolume(IMediaRouterClient iMediaRouterClient, String str, int i) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void selectRouteWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void selectRouteWithRouter2(IMediaRouter2 iMediaRouter2, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void setDiscoveryRequest(IMediaRouterClient iMediaRouterClient, int i, boolean z) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void setDiscoveryRequestWithRouter2(IMediaRouter2 iMediaRouter2, RouteDiscoveryPreference routeDiscoveryPreference) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void setRouteVolumeWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, MediaRoute2Info mediaRoute2Info, int i2) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void setRouteVolumeWithRouter2(IMediaRouter2 iMediaRouter2, MediaRoute2Info mediaRoute2Info, int i) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void setSelectedRoute(IMediaRouterClient iMediaRouterClient, String str, boolean z) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void setSessionVolumeWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str, int i2) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void setSessionVolumeWithRouter2(IMediaRouter2 iMediaRouter2, String str, int i) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void startScan(IMediaRouter2Manager iMediaRouter2Manager) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void stopScan(IMediaRouter2Manager iMediaRouter2Manager) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void transferToRouteWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void transferToRouteWithRouter2(IMediaRouter2 iMediaRouter2, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void unregisterClient(IMediaRouterClient iMediaRouterClient) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void unregisterManager(IMediaRouter2Manager iMediaRouter2Manager) throws RemoteException {
        }

        @Override // android.media.IMediaRouterService
        public void unregisterRouter2(IMediaRouter2 iMediaRouter2) throws RemoteException {
        }
    }

    void deselectRouteWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException;

    void deselectRouteWithRouter2(IMediaRouter2 iMediaRouter2, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException;

    List<RoutingSessionInfo> getActiveSessions(IMediaRouter2Manager iMediaRouter2Manager) throws RemoteException;

    MediaRouterClientState getState(IMediaRouterClient iMediaRouterClient) throws RemoteException;

    List<MediaRoute2Info> getSystemRoutes() throws RemoteException;

    RoutingSessionInfo getSystemSessionInfo() throws RemoteException;

    boolean isPlaybackActive(IMediaRouterClient iMediaRouterClient) throws RemoteException;

    void registerClientAsUser(IMediaRouterClient iMediaRouterClient, String str, int i) throws RemoteException;

    void registerClientGroupId(IMediaRouterClient iMediaRouterClient, String str) throws RemoteException;

    void registerManager(IMediaRouter2Manager iMediaRouter2Manager, String str) throws RemoteException;

    void registerRouter2(IMediaRouter2 iMediaRouter2, String str) throws RemoteException;

    void releaseSessionWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str) throws RemoteException;

    void releaseSessionWithRouter2(IMediaRouter2 iMediaRouter2, String str) throws RemoteException;

    void requestCreateSessionWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) throws RemoteException;

    void requestCreateSessionWithRouter2(IMediaRouter2 iMediaRouter2, int i, long j, RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info, Bundle bundle) throws RemoteException;

    void requestSetVolume(IMediaRouterClient iMediaRouterClient, String str, int i) throws RemoteException;

    void requestUpdateVolume(IMediaRouterClient iMediaRouterClient, String str, int i) throws RemoteException;

    void selectRouteWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException;

    void selectRouteWithRouter2(IMediaRouter2 iMediaRouter2, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException;

    void setDiscoveryRequest(IMediaRouterClient iMediaRouterClient, int i, boolean z) throws RemoteException;

    void setDiscoveryRequestWithRouter2(IMediaRouter2 iMediaRouter2, RouteDiscoveryPreference routeDiscoveryPreference) throws RemoteException;

    void setRouteVolumeWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, MediaRoute2Info mediaRoute2Info, int i2) throws RemoteException;

    void setRouteVolumeWithRouter2(IMediaRouter2 iMediaRouter2, MediaRoute2Info mediaRoute2Info, int i) throws RemoteException;

    void setSelectedRoute(IMediaRouterClient iMediaRouterClient, String str, boolean z) throws RemoteException;

    void setSessionVolumeWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str, int i2) throws RemoteException;

    void setSessionVolumeWithRouter2(IMediaRouter2 iMediaRouter2, String str, int i) throws RemoteException;

    void startScan(IMediaRouter2Manager iMediaRouter2Manager) throws RemoteException;

    void stopScan(IMediaRouter2Manager iMediaRouter2Manager) throws RemoteException;

    void transferToRouteWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException;

    void transferToRouteWithRouter2(IMediaRouter2 iMediaRouter2, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException;

    void unregisterClient(IMediaRouterClient iMediaRouterClient) throws RemoteException;

    void unregisterManager(IMediaRouter2Manager iMediaRouter2Manager) throws RemoteException;

    void unregisterRouter2(IMediaRouter2 iMediaRouter2) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaRouterService {
        private static final String DESCRIPTOR = "android.media.IMediaRouterService";
        static final int TRANSACTION_deselectRouteWithManager = 30;
        static final int TRANSACTION_deselectRouteWithRouter2 = 18;
        static final int TRANSACTION_getActiveSessions = 22;
        static final int TRANSACTION_getState = 4;
        static final int TRANSACTION_getSystemRoutes = 10;
        static final int TRANSACTION_getSystemSessionInfo = 11;
        static final int TRANSACTION_isPlaybackActive = 5;
        static final int TRANSACTION_registerClientAsUser = 1;
        static final int TRANSACTION_registerClientGroupId = 3;
        static final int TRANSACTION_registerManager = 23;
        static final int TRANSACTION_registerRouter2 = 12;
        static final int TRANSACTION_releaseSessionWithManager = 33;
        static final int TRANSACTION_releaseSessionWithRouter2 = 21;
        static final int TRANSACTION_requestCreateSessionWithManager = 28;
        static final int TRANSACTION_requestCreateSessionWithRouter2 = 16;
        static final int TRANSACTION_requestSetVolume = 8;
        static final int TRANSACTION_requestUpdateVolume = 9;
        static final int TRANSACTION_selectRouteWithManager = 29;
        static final int TRANSACTION_selectRouteWithRouter2 = 17;
        static final int TRANSACTION_setDiscoveryRequest = 6;
        static final int TRANSACTION_setDiscoveryRequestWithRouter2 = 14;
        static final int TRANSACTION_setRouteVolumeWithManager = 25;
        static final int TRANSACTION_setRouteVolumeWithRouter2 = 15;
        static final int TRANSACTION_setSelectedRoute = 7;
        static final int TRANSACTION_setSessionVolumeWithManager = 32;
        static final int TRANSACTION_setSessionVolumeWithRouter2 = 20;
        static final int TRANSACTION_startScan = 26;
        static final int TRANSACTION_stopScan = 27;
        static final int TRANSACTION_transferToRouteWithManager = 31;
        static final int TRANSACTION_transferToRouteWithRouter2 = 19;
        static final int TRANSACTION_unregisterClient = 2;
        static final int TRANSACTION_unregisterManager = 24;
        static final int TRANSACTION_unregisterRouter2 = 13;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "registerClientAsUser";
                case 2:
                    return "unregisterClient";
                case 3:
                    return "registerClientGroupId";
                case 4:
                    return "getState";
                case 5:
                    return "isPlaybackActive";
                case 6:
                    return "setDiscoveryRequest";
                case 7:
                    return "setSelectedRoute";
                case 8:
                    return "requestSetVolume";
                case 9:
                    return "requestUpdateVolume";
                case 10:
                    return "getSystemRoutes";
                case 11:
                    return "getSystemSessionInfo";
                case 12:
                    return "registerRouter2";
                case 13:
                    return "unregisterRouter2";
                case 14:
                    return "setDiscoveryRequestWithRouter2";
                case 15:
                    return "setRouteVolumeWithRouter2";
                case 16:
                    return "requestCreateSessionWithRouter2";
                case 17:
                    return "selectRouteWithRouter2";
                case 18:
                    return "deselectRouteWithRouter2";
                case 19:
                    return "transferToRouteWithRouter2";
                case 20:
                    return "setSessionVolumeWithRouter2";
                case 21:
                    return "releaseSessionWithRouter2";
                case 22:
                    return "getActiveSessions";
                case 23:
                    return "registerManager";
                case 24:
                    return "unregisterManager";
                case 25:
                    return "setRouteVolumeWithManager";
                case 26:
                    return "startScan";
                case 27:
                    return "stopScan";
                case 28:
                    return "requestCreateSessionWithManager";
                case 29:
                    return "selectRouteWithManager";
                case 30:
                    return "deselectRouteWithManager";
                case 31:
                    return "transferToRouteWithManager";
                case 32:
                    return "setSessionVolumeWithManager";
                case 33:
                    return "releaseSessionWithManager";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaRouterService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaRouterService)) {
                return (IMediaRouterService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
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
                    registerClientAsUser(IMediaRouterClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterClient(IMediaRouterClient.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerClientGroupId(IMediaRouterClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    MediaRouterClientState state = getState(IMediaRouterClient.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (state != null) {
                        parcel2.writeInt(1);
                        state.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPlaybackActive = isPlaybackActive(IMediaRouterClient.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPlaybackActive ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDiscoveryRequest(IMediaRouterClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSelectedRoute(IMediaRouterClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestSetVolume(IMediaRouterClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestUpdateVolume(IMediaRouterClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<MediaRoute2Info> systemRoutes = getSystemRoutes();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(systemRoutes);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    RoutingSessionInfo systemSessionInfo = getSystemSessionInfo();
                    parcel2.writeNoException();
                    if (systemSessionInfo != null) {
                        parcel2.writeInt(1);
                        systemSessionInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerRouter2(IMediaRouter2.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterRouter2(IMediaRouter2.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDiscoveryRequestWithRouter2(IMediaRouter2.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? RouteDiscoveryPreference.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRouteVolumeWithRouter2(IMediaRouter2.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? MediaRoute2Info.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestCreateSessionWithRouter2(IMediaRouter2.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readLong(), parcel.readInt() != 0 ? RoutingSessionInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? MediaRoute2Info.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    selectRouteWithRouter2(IMediaRouter2.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 ? MediaRoute2Info.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    deselectRouteWithRouter2(IMediaRouter2.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 ? MediaRoute2Info.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    transferToRouteWithRouter2(IMediaRouter2.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt() != 0 ? MediaRoute2Info.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSessionVolumeWithRouter2(IMediaRouter2.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    releaseSessionWithRouter2(IMediaRouter2.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<RoutingSessionInfo> activeSessions = getActiveSessions(IMediaRouter2Manager.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeTypedList(activeSessions);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerManager(IMediaRouter2Manager.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterManager(IMediaRouter2Manager.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRouteVolumeWithManager(IMediaRouter2Manager.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0 ? MediaRoute2Info.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    startScan(IMediaRouter2Manager.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopScan(IMediaRouter2Manager.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestCreateSessionWithManager(IMediaRouter2Manager.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0 ? RoutingSessionInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? MediaRoute2Info.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    selectRouteWithManager(IMediaRouter2Manager.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? MediaRoute2Info.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    deselectRouteWithManager(IMediaRouter2Manager.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? MediaRoute2Info.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    transferToRouteWithManager(IMediaRouter2Manager.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? MediaRoute2Info.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSessionVolumeWithManager(IMediaRouter2Manager.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    releaseSessionWithManager(IMediaRouter2Manager.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IMediaRouterService {
            public static IMediaRouterService sDefaultImpl;
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

            @Override // android.media.IMediaRouterService
            public void registerClientAsUser(IMediaRouterClient iMediaRouterClient, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouterClient != null ? iMediaRouterClient.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerClientAsUser(iMediaRouterClient, str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void unregisterClient(IMediaRouterClient iMediaRouterClient) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouterClient != null ? iMediaRouterClient.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterClient(iMediaRouterClient);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void registerClientGroupId(IMediaRouterClient iMediaRouterClient, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouterClient != null ? iMediaRouterClient.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerClientGroupId(iMediaRouterClient, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public MediaRouterClientState getState(IMediaRouterClient iMediaRouterClient) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouterClient != null ? iMediaRouterClient.asBinder() : null);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getState(iMediaRouterClient);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? MediaRouterClientState.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public boolean isPlaybackActive(IMediaRouterClient iMediaRouterClient) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouterClient != null ? iMediaRouterClient.asBinder() : null);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPlaybackActive(iMediaRouterClient);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void setDiscoveryRequest(IMediaRouterClient iMediaRouterClient, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouterClient != null ? iMediaRouterClient.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDiscoveryRequest(iMediaRouterClient, i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void setSelectedRoute(IMediaRouterClient iMediaRouterClient, String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouterClient != null ? iMediaRouterClient.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSelectedRoute(iMediaRouterClient, str, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void requestSetVolume(IMediaRouterClient iMediaRouterClient, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouterClient != null ? iMediaRouterClient.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestSetVolume(iMediaRouterClient, str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void requestUpdateVolume(IMediaRouterClient iMediaRouterClient, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouterClient != null ? iMediaRouterClient.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestUpdateVolume(iMediaRouterClient, str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public List<MediaRoute2Info> getSystemRoutes() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemRoutes();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(MediaRoute2Info.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public RoutingSessionInfo getSystemSessionInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSystemSessionInfo();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? RoutingSessionInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void registerRouter2(IMediaRouter2 iMediaRouter2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2 != null ? iMediaRouter2.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerRouter2(iMediaRouter2, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void unregisterRouter2(IMediaRouter2 iMediaRouter2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2 != null ? iMediaRouter2.asBinder() : null);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterRouter2(iMediaRouter2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void setDiscoveryRequestWithRouter2(IMediaRouter2 iMediaRouter2, RouteDiscoveryPreference routeDiscoveryPreference) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2 != null ? iMediaRouter2.asBinder() : null);
                    if (routeDiscoveryPreference != null) {
                        parcelObtain.writeInt(1);
                        routeDiscoveryPreference.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDiscoveryRequestWithRouter2(iMediaRouter2, routeDiscoveryPreference);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void setRouteVolumeWithRouter2(IMediaRouter2 iMediaRouter2, MediaRoute2Info mediaRoute2Info, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2 != null ? iMediaRouter2.asBinder() : null);
                    if (mediaRoute2Info != null) {
                        parcelObtain.writeInt(1);
                        mediaRoute2Info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRouteVolumeWithRouter2(iMediaRouter2, mediaRoute2Info, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void requestCreateSessionWithRouter2(IMediaRouter2 iMediaRouter2, int i, long j, RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info, Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2 != null ? iMediaRouter2.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    if (routingSessionInfo != null) {
                        parcelObtain.writeInt(1);
                        routingSessionInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (mediaRoute2Info != null) {
                        parcelObtain.writeInt(1);
                        mediaRoute2Info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestCreateSessionWithRouter2(iMediaRouter2, i, j, routingSessionInfo, mediaRoute2Info, bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void selectRouteWithRouter2(IMediaRouter2 iMediaRouter2, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2 != null ? iMediaRouter2.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (mediaRoute2Info != null) {
                        parcelObtain.writeInt(1);
                        mediaRoute2Info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().selectRouteWithRouter2(iMediaRouter2, str, mediaRoute2Info);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void deselectRouteWithRouter2(IMediaRouter2 iMediaRouter2, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2 != null ? iMediaRouter2.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (mediaRoute2Info != null) {
                        parcelObtain.writeInt(1);
                        mediaRoute2Info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deselectRouteWithRouter2(iMediaRouter2, str, mediaRoute2Info);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void transferToRouteWithRouter2(IMediaRouter2 iMediaRouter2, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2 != null ? iMediaRouter2.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (mediaRoute2Info != null) {
                        parcelObtain.writeInt(1);
                        mediaRoute2Info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().transferToRouteWithRouter2(iMediaRouter2, str, mediaRoute2Info);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void setSessionVolumeWithRouter2(IMediaRouter2 iMediaRouter2, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2 != null ? iMediaRouter2.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSessionVolumeWithRouter2(iMediaRouter2, str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void releaseSessionWithRouter2(IMediaRouter2 iMediaRouter2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2 != null ? iMediaRouter2.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseSessionWithRouter2(iMediaRouter2, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public List<RoutingSessionInfo> getActiveSessions(IMediaRouter2Manager iMediaRouter2Manager) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2Manager != null ? iMediaRouter2Manager.asBinder() : null);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveSessions(iMediaRouter2Manager);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(RoutingSessionInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void registerManager(IMediaRouter2Manager iMediaRouter2Manager, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2Manager != null ? iMediaRouter2Manager.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerManager(iMediaRouter2Manager, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void unregisterManager(IMediaRouter2Manager iMediaRouter2Manager) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2Manager != null ? iMediaRouter2Manager.asBinder() : null);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterManager(iMediaRouter2Manager);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void setRouteVolumeWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, MediaRoute2Info mediaRoute2Info, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2Manager != null ? iMediaRouter2Manager.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (mediaRoute2Info != null) {
                        parcelObtain.writeInt(1);
                        mediaRoute2Info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRouteVolumeWithManager(iMediaRouter2Manager, i, mediaRoute2Info, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void startScan(IMediaRouter2Manager iMediaRouter2Manager) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2Manager != null ? iMediaRouter2Manager.asBinder() : null);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startScan(iMediaRouter2Manager);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void stopScan(IMediaRouter2Manager iMediaRouter2Manager) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2Manager != null ? iMediaRouter2Manager.asBinder() : null);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopScan(iMediaRouter2Manager);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void requestCreateSessionWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, RoutingSessionInfo routingSessionInfo, MediaRoute2Info mediaRoute2Info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2Manager != null ? iMediaRouter2Manager.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (routingSessionInfo != null) {
                        parcelObtain.writeInt(1);
                        routingSessionInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (mediaRoute2Info != null) {
                        parcelObtain.writeInt(1);
                        mediaRoute2Info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestCreateSessionWithManager(iMediaRouter2Manager, i, routingSessionInfo, mediaRoute2Info);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void selectRouteWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2Manager != null ? iMediaRouter2Manager.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (mediaRoute2Info != null) {
                        parcelObtain.writeInt(1);
                        mediaRoute2Info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().selectRouteWithManager(iMediaRouter2Manager, i, str, mediaRoute2Info);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void deselectRouteWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2Manager != null ? iMediaRouter2Manager.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (mediaRoute2Info != null) {
                        parcelObtain.writeInt(1);
                        mediaRoute2Info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deselectRouteWithManager(iMediaRouter2Manager, i, str, mediaRoute2Info);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void transferToRouteWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str, MediaRoute2Info mediaRoute2Info) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2Manager != null ? iMediaRouter2Manager.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (mediaRoute2Info != null) {
                        parcelObtain.writeInt(1);
                        mediaRoute2Info.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().transferToRouteWithManager(iMediaRouter2Manager, i, str, mediaRoute2Info);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void setSessionVolumeWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2Manager != null ? iMediaRouter2Manager.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSessionVolumeWithManager(iMediaRouter2Manager, i, str, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.IMediaRouterService
            public void releaseSessionWithManager(IMediaRouter2Manager iMediaRouter2Manager, int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iMediaRouter2Manager != null ? iMediaRouter2Manager.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseSessionWithManager(iMediaRouter2Manager, i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaRouterService iMediaRouterService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMediaRouterService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMediaRouterService;
            return true;
        }

        public static IMediaRouterService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
