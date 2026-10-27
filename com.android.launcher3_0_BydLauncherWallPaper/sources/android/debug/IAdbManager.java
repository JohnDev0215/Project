package android.debug;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface IAdbManager extends IInterface {

    public static class Default implements IAdbManager {
        @Override // android.debug.IAdbManager
        public void allowDebugging(boolean z, String str) throws RemoteException {
        }

        @Override // android.debug.IAdbManager
        public void allowWirelessDebugging(boolean z, String str) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.debug.IAdbManager
        public void clearDebuggingKeys() throws RemoteException {
        }

        @Override // android.debug.IAdbManager
        public void denyDebugging() throws RemoteException {
        }

        @Override // android.debug.IAdbManager
        public void denyWirelessDebugging() throws RemoteException {
        }

        @Override // android.debug.IAdbManager
        public void disablePairing() throws RemoteException {
        }

        @Override // android.debug.IAdbManager
        public void enablePairingByPairingCode() throws RemoteException {
        }

        @Override // android.debug.IAdbManager
        public void enablePairingByQrCode(String str, String str2) throws RemoteException {
        }

        @Override // android.debug.IAdbManager
        public int getAdbWirelessPort() throws RemoteException {
            return 0;
        }

        @Override // android.debug.IAdbManager
        public Map getPairedDevices() throws RemoteException {
            return null;
        }

        @Override // android.debug.IAdbManager
        public boolean isAdbWifiQrSupported() throws RemoteException {
            return false;
        }

        @Override // android.debug.IAdbManager
        public boolean isAdbWifiSupported() throws RemoteException {
            return false;
        }

        @Override // android.debug.IAdbManager
        public void unpairDevice(String str) throws RemoteException {
        }
    }

    void allowDebugging(boolean z, String str) throws RemoteException;

    void allowWirelessDebugging(boolean z, String str) throws RemoteException;

    void clearDebuggingKeys() throws RemoteException;

    void denyDebugging() throws RemoteException;

    void denyWirelessDebugging() throws RemoteException;

    void disablePairing() throws RemoteException;

    void enablePairingByPairingCode() throws RemoteException;

    void enablePairingByQrCode(String str, String str2) throws RemoteException;

    int getAdbWirelessPort() throws RemoteException;

    Map getPairedDevices() throws RemoteException;

    boolean isAdbWifiQrSupported() throws RemoteException;

    boolean isAdbWifiSupported() throws RemoteException;

    void unpairDevice(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IAdbManager {
        private static final String DESCRIPTOR = "android.debug.IAdbManager";
        static final int TRANSACTION_allowDebugging = 1;
        static final int TRANSACTION_allowWirelessDebugging = 4;
        static final int TRANSACTION_clearDebuggingKeys = 3;
        static final int TRANSACTION_denyDebugging = 2;
        static final int TRANSACTION_denyWirelessDebugging = 5;
        static final int TRANSACTION_disablePairing = 11;
        static final int TRANSACTION_enablePairingByPairingCode = 8;
        static final int TRANSACTION_enablePairingByQrCode = 9;
        static final int TRANSACTION_getAdbWirelessPort = 10;
        static final int TRANSACTION_getPairedDevices = 6;
        static final int TRANSACTION_isAdbWifiQrSupported = 13;
        static final int TRANSACTION_isAdbWifiSupported = 12;
        static final int TRANSACTION_unpairDevice = 7;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "allowDebugging";
                case 2:
                    return "denyDebugging";
                case 3:
                    return "clearDebuggingKeys";
                case 4:
                    return "allowWirelessDebugging";
                case 5:
                    return "denyWirelessDebugging";
                case 6:
                    return "getPairedDevices";
                case 7:
                    return "unpairDevice";
                case 8:
                    return "enablePairingByPairingCode";
                case 9:
                    return "enablePairingByQrCode";
                case 10:
                    return "getAdbWirelessPort";
                case 11:
                    return "disablePairing";
                case 12:
                    return "isAdbWifiSupported";
                case 13:
                    return "isAdbWifiQrSupported";
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

        public static IAdbManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAdbManager)) {
                return (IAdbManager) iInterfaceQueryLocalInterface;
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
                    allowDebugging(parcel.readInt() != 0, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    denyDebugging();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearDebuggingKeys();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    allowWirelessDebugging(parcel.readInt() != 0, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    denyWirelessDebugging();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    Map pairedDevices = getPairedDevices();
                    parcel2.writeNoException();
                    parcel2.writeMap(pairedDevices);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    unpairDevice(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    enablePairingByPairingCode();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    enablePairingByQrCode(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int adbWirelessPort = getAdbWirelessPort();
                    parcel2.writeNoException();
                    parcel2.writeInt(adbWirelessPort);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    disablePairing();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAdbWifiSupported = isAdbWifiSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAdbWifiSupported ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAdbWifiQrSupported = isAdbWifiQrSupported();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAdbWifiQrSupported ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAdbManager {
            public static IAdbManager sDefaultImpl;
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

            @Override // android.debug.IAdbManager
            public void allowDebugging(boolean z, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().allowDebugging(z, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public void denyDebugging() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().denyDebugging();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public void clearDebuggingKeys() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearDebuggingKeys();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public void allowWirelessDebugging(boolean z, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().allowWirelessDebugging(z, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public void denyWirelessDebugging() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().denyWirelessDebugging();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public Map getPairedDevices() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPairedDevices();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public void unpairDevice(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unpairDevice(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public void enablePairingByPairingCode() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enablePairingByPairingCode();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public void enablePairingByQrCode(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enablePairingByQrCode(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public int getAdbWirelessPort() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAdbWirelessPort();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public void disablePairing() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disablePairing();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public boolean isAdbWifiSupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAdbWifiSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.debug.IAdbManager
            public boolean isAdbWifiQrSupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAdbWifiQrSupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAdbManager iAdbManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAdbManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAdbManager;
            return true;
        }

        public static IAdbManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
