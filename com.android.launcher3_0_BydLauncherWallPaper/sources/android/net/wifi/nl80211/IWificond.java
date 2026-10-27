package android.net.wifi.nl80211;

import android.net.wifi.nl80211.IApInterface;
import android.net.wifi.nl80211.IClientInterface;
import android.net.wifi.nl80211.IInterfaceEventCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IWificond extends IInterface {

    public static class Default implements IWificond {
        @Override // android.net.wifi.nl80211.IWificond
        public List<IBinder> GetApInterfaces() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public List<IBinder> GetClientInterfaces() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public void RegisterCallback(IInterfaceEventCallback iInterfaceEventCallback) throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.IWificond
        public void UnregisterCallback(IInterfaceEventCallback iInterfaceEventCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public IApInterface createApInterface(String str) throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public IClientInterface createClientInterface(String str) throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public int[] getAvailable2gChannels() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public int[] getAvailable5gNonDFSChannels() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public int[] getAvailable6gChannels() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public int[] getAvailableDFSChannels() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public DeviceWiphyCapabilities getDeviceWiphyCapabilities(String str) throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public boolean tearDownApInterface(String str) throws RemoteException {
            return false;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public boolean tearDownClientInterface(String str) throws RemoteException {
            return false;
        }

        @Override // android.net.wifi.nl80211.IWificond
        public void tearDownInterfaces() throws RemoteException {
        }
    }

    List<IBinder> GetApInterfaces() throws RemoteException;

    List<IBinder> GetClientInterfaces() throws RemoteException;

    void RegisterCallback(IInterfaceEventCallback iInterfaceEventCallback) throws RemoteException;

    void UnregisterCallback(IInterfaceEventCallback iInterfaceEventCallback) throws RemoteException;

    IApInterface createApInterface(String str) throws RemoteException;

    IClientInterface createClientInterface(String str) throws RemoteException;

    int[] getAvailable2gChannels() throws RemoteException;

    int[] getAvailable5gNonDFSChannels() throws RemoteException;

    int[] getAvailable6gChannels() throws RemoteException;

    int[] getAvailableDFSChannels() throws RemoteException;

    DeviceWiphyCapabilities getDeviceWiphyCapabilities(String str) throws RemoteException;

    boolean tearDownApInterface(String str) throws RemoteException;

    boolean tearDownClientInterface(String str) throws RemoteException;

    void tearDownInterfaces() throws RemoteException;

    public static abstract class Stub extends Binder implements IWificond {
        private static final String DESCRIPTOR = "android.net.wifi.nl80211.IWificond";
        static final int TRANSACTION_GetApInterfaces = 7;
        static final int TRANSACTION_GetClientInterfaces = 6;
        static final int TRANSACTION_RegisterCallback = 12;
        static final int TRANSACTION_UnregisterCallback = 13;
        static final int TRANSACTION_createApInterface = 1;
        static final int TRANSACTION_createClientInterface = 2;
        static final int TRANSACTION_getAvailable2gChannels = 8;
        static final int TRANSACTION_getAvailable5gNonDFSChannels = 9;
        static final int TRANSACTION_getAvailable6gChannels = 11;
        static final int TRANSACTION_getAvailableDFSChannels = 10;
        static final int TRANSACTION_getDeviceWiphyCapabilities = 14;
        static final int TRANSACTION_tearDownApInterface = 3;
        static final int TRANSACTION_tearDownClientInterface = 4;
        static final int TRANSACTION_tearDownInterfaces = 5;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "createApInterface";
                case 2:
                    return "createClientInterface";
                case 3:
                    return "tearDownApInterface";
                case 4:
                    return "tearDownClientInterface";
                case 5:
                    return "tearDownInterfaces";
                case 6:
                    return "GetClientInterfaces";
                case 7:
                    return "GetApInterfaces";
                case 8:
                    return "getAvailable2gChannels";
                case 9:
                    return "getAvailable5gNonDFSChannels";
                case 10:
                    return "getAvailableDFSChannels";
                case 11:
                    return "getAvailable6gChannels";
                case 12:
                    return "RegisterCallback";
                case 13:
                    return "UnregisterCallback";
                case 14:
                    return "getDeviceWiphyCapabilities";
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

        public static IWificond asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IWificond)) {
                return (IWificond) iInterfaceQueryLocalInterface;
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
                    IApInterface iApInterfaceCreateApInterface = createApInterface(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iApInterfaceCreateApInterface != null ? iApInterfaceCreateApInterface.asBinder() : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    IClientInterface iClientInterfaceCreateClientInterface = createClientInterface(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iClientInterfaceCreateClientInterface != null ? iClientInterfaceCreateClientInterface.asBinder() : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zTearDownApInterface = tearDownApInterface(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zTearDownApInterface ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zTearDownClientInterface = tearDownClientInterface(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zTearDownClientInterface ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    tearDownInterfaces();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<IBinder> listGetClientInterfaces = GetClientInterfaces();
                    parcel2.writeNoException();
                    parcel2.writeBinderList(listGetClientInterfaces);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<IBinder> listGetApInterfaces = GetApInterfaces();
                    parcel2.writeNoException();
                    parcel2.writeBinderList(listGetApInterfaces);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] available2gChannels = getAvailable2gChannels();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(available2gChannels);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] available5gNonDFSChannels = getAvailable5gNonDFSChannels();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(available5gNonDFSChannels);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] availableDFSChannels = getAvailableDFSChannels();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(availableDFSChannels);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] available6gChannels = getAvailable6gChannels();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(available6gChannels);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    RegisterCallback(IInterfaceEventCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    UnregisterCallback(IInterfaceEventCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    DeviceWiphyCapabilities deviceWiphyCapabilities = getDeviceWiphyCapabilities(parcel.readString());
                    parcel2.writeNoException();
                    if (deviceWiphyCapabilities != null) {
                        parcel2.writeInt(1);
                        deviceWiphyCapabilities.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IWificond {
            public static IWificond sDefaultImpl;
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

            @Override // android.net.wifi.nl80211.IWificond
            public IApInterface createApInterface(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createApInterface(str);
                    }
                    parcelObtain2.readException();
                    return IApInterface.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public IClientInterface createClientInterface(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createClientInterface(str);
                    }
                    parcelObtain2.readException();
                    return IClientInterface.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public boolean tearDownApInterface(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().tearDownApInterface(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public boolean tearDownClientInterface(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().tearDownClientInterface(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public void tearDownInterfaces() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tearDownInterfaces();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public List<IBinder> GetClientInterfaces() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().GetClientInterfaces();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createBinderArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public List<IBinder> GetApInterfaces() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().GetApInterfaces();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createBinderArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public int[] getAvailable2gChannels() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailable2gChannels();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public int[] getAvailable5gNonDFSChannels() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailable5gNonDFSChannels();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public int[] getAvailableDFSChannels() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailableDFSChannels();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public int[] getAvailable6gChannels() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailable6gChannels();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public void RegisterCallback(IInterfaceEventCallback iInterfaceEventCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iInterfaceEventCallback != null ? iInterfaceEventCallback.asBinder() : null);
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().RegisterCallback(iInterfaceEventCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public void UnregisterCallback(IInterfaceEventCallback iInterfaceEventCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iInterfaceEventCallback != null ? iInterfaceEventCallback.asBinder() : null);
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().UnregisterCallback(iInterfaceEventCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWificond
            public DeviceWiphyCapabilities getDeviceWiphyCapabilities(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceWiphyCapabilities(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? DeviceWiphyCapabilities.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWificond iWificond) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iWificond == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWificond;
            return true;
        }

        public static IWificond getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
