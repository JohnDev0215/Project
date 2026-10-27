package com.byd.autoiot.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.autoiot.service.IAutoiotEventListener;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IAutoiotInterface extends IInterface {
    public static final String DESCRIPTOR = "com.byd.autoiot.service.IAutoiotInterface";

    public static class Default implements IAutoiotInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public boolean autoiotConnected(String str) throws RemoteException {
            return false;
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public boolean autoiotRead() throws RemoteException {
            return false;
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public boolean autoiotReadChr(String str, String str2, String str3) throws RemoteException {
            return false;
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public boolean autoiotReadDev(String str) throws RemoteException {
            return false;
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public boolean autoiotReadSrv(String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public boolean autoiotWrite(AutoiotProfile autoiotProfile) throws RemoteException {
            return false;
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public boolean autoiotWriteChr(AutoiotProfile autoiotProfile, String str, String str2, String str3) throws RemoteException {
            return false;
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public boolean autoiotWriteDev(AutoiotProfile autoiotProfile, String str) throws RemoteException {
            return false;
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public boolean autoiotWriteSrv(AutoiotProfile autoiotProfile, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public boolean connectTo(String str) throws RemoteException {
            return false;
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public boolean deleteDevice(String str) throws RemoteException {
            return false;
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public List<String> getDeviceList() throws RemoteException {
            return null;
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public String getGatewayInfo(String str) throws RemoteException {
            return null;
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public int getMTU(String str) throws RemoteException {
            return 0;
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public String readBasicInformation() throws RemoteException {
            return null;
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public void registerListener(IAutoiotEventListener iAutoiotEventListener) throws RemoteException {
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public boolean startScan() throws RemoteException {
            return false;
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public boolean stopScan() throws RemoteException {
            return false;
        }

        @Override // com.byd.autoiot.service.IAutoiotInterface
        public void unregisterListener(IAutoiotEventListener iAutoiotEventListener) throws RemoteException {
        }
    }

    boolean autoiotConnected(String str) throws RemoteException;

    boolean autoiotRead() throws RemoteException;

    boolean autoiotReadChr(String str, String str2, String str3) throws RemoteException;

    boolean autoiotReadDev(String str) throws RemoteException;

    boolean autoiotReadSrv(String str, String str2) throws RemoteException;

    boolean autoiotWrite(AutoiotProfile autoiotProfile) throws RemoteException;

    boolean autoiotWriteChr(AutoiotProfile autoiotProfile, String str, String str2, String str3) throws RemoteException;

    boolean autoiotWriteDev(AutoiotProfile autoiotProfile, String str) throws RemoteException;

    boolean autoiotWriteSrv(AutoiotProfile autoiotProfile, String str, String str2) throws RemoteException;

    boolean connectTo(String str) throws RemoteException;

    boolean deleteDevice(String str) throws RemoteException;

    List<String> getDeviceList() throws RemoteException;

    String getGatewayInfo(String str) throws RemoteException;

    int getMTU(String str) throws RemoteException;

    String readBasicInformation() throws RemoteException;

    void registerListener(IAutoiotEventListener iAutoiotEventListener) throws RemoteException;

    boolean startScan() throws RemoteException;

    boolean stopScan() throws RemoteException;

    void unregisterListener(IAutoiotEventListener iAutoiotEventListener) throws RemoteException;

    public static abstract class Stub extends Binder implements IAutoiotInterface {
        static final int TRANSACTION_autoiotConnected = 13;
        static final int TRANSACTION_autoiotRead = 9;
        static final int TRANSACTION_autoiotReadChr = 12;
        static final int TRANSACTION_autoiotReadDev = 10;
        static final int TRANSACTION_autoiotReadSrv = 11;
        static final int TRANSACTION_autoiotWrite = 5;
        static final int TRANSACTION_autoiotWriteChr = 8;
        static final int TRANSACTION_autoiotWriteDev = 6;
        static final int TRANSACTION_autoiotWriteSrv = 7;
        static final int TRANSACTION_connectTo = 4;
        static final int TRANSACTION_deleteDevice = 14;
        static final int TRANSACTION_getDeviceList = 19;
        static final int TRANSACTION_getGatewayInfo = 17;
        static final int TRANSACTION_getMTU = 18;
        static final int TRANSACTION_readBasicInformation = 3;
        static final int TRANSACTION_registerListener = 15;
        static final int TRANSACTION_startScan = 1;
        static final int TRANSACTION_stopScan = 2;
        static final int TRANSACTION_unregisterListener = 16;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IAutoiotInterface.DESCRIPTOR);
        }

        public static IAutoiotInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IAutoiotInterface.DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAutoiotInterface)) {
                return (IAutoiotInterface) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(IAutoiotInterface.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    boolean zStartScan = startScan();
                    parcel2.writeNoException();
                    parcel2.writeInt(zStartScan ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    boolean zStopScan = stopScan();
                    parcel2.writeNoException();
                    parcel2.writeInt(zStopScan ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    String basicInformation = readBasicInformation();
                    parcel2.writeNoException();
                    parcel2.writeString(basicInformation);
                    return true;
                case 4:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    boolean zConnectTo = connectTo(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zConnectTo ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    boolean zAutoiotWrite = autoiotWrite(parcel.readInt() != 0 ? AutoiotProfile.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zAutoiotWrite ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    boolean zAutoiotWriteDev = autoiotWriteDev(parcel.readInt() != 0 ? AutoiotProfile.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAutoiotWriteDev ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    boolean zAutoiotWriteSrv = autoiotWriteSrv(parcel.readInt() != 0 ? AutoiotProfile.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAutoiotWriteSrv ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    boolean zAutoiotWriteChr = autoiotWriteChr(parcel.readInt() != 0 ? AutoiotProfile.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAutoiotWriteChr ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    boolean zAutoiotRead = autoiotRead();
                    parcel2.writeNoException();
                    parcel2.writeInt(zAutoiotRead ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    boolean zAutoiotReadDev = autoiotReadDev(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAutoiotReadDev ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    boolean zAutoiotReadSrv = autoiotReadSrv(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAutoiotReadSrv ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    boolean zAutoiotReadChr = autoiotReadChr(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAutoiotReadChr ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    boolean zAutoiotConnected = autoiotConnected(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAutoiotConnected ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    boolean zDeleteDevice = deleteDevice(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zDeleteDevice ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    registerListener(IAutoiotEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    unregisterListener(IAutoiotEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    String gatewayInfo = getGatewayInfo(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(gatewayInfo);
                    return true;
                case 18:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    int mtu = getMTU(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(mtu);
                    return true;
                case 19:
                    parcel.enforceInterface(IAutoiotInterface.DESCRIPTOR);
                    List<String> deviceList = getDeviceList();
                    parcel2.writeNoException();
                    parcel2.writeStringList(deviceList);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAutoiotInterface {
            public static IAutoiotInterface sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return IAutoiotInterface.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public boolean startScan() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startScan();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public boolean stopScan() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopScan();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public String readBasicInformation() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().readBasicInformation();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public boolean connectTo(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connectTo(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public boolean autoiotWrite(AutoiotProfile autoiotProfile) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    if (autoiotProfile != null) {
                        parcelObtain.writeInt(1);
                        autoiotProfile.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().autoiotWrite(autoiotProfile);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public boolean autoiotWriteDev(AutoiotProfile autoiotProfile, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    if (autoiotProfile != null) {
                        parcelObtain.writeInt(1);
                        autoiotProfile.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().autoiotWriteDev(autoiotProfile, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public boolean autoiotWriteSrv(AutoiotProfile autoiotProfile, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    if (autoiotProfile != null) {
                        parcelObtain.writeInt(1);
                        autoiotProfile.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().autoiotWriteSrv(autoiotProfile, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public boolean autoiotWriteChr(AutoiotProfile autoiotProfile, String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    if (autoiotProfile != null) {
                        parcelObtain.writeInt(1);
                        autoiotProfile.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().autoiotWriteChr(autoiotProfile, str, str2, str3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public boolean autoiotRead() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().autoiotRead();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public boolean autoiotReadDev(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().autoiotReadDev(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public boolean autoiotReadSrv(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().autoiotReadSrv(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public boolean autoiotReadChr(String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().autoiotReadChr(str, str2, str3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public boolean autoiotConnected(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().autoiotConnected(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public boolean deleteDevice(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteDevice(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public void registerListener(IAutoiotEventListener iAutoiotEventListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAutoiotEventListener != null ? iAutoiotEventListener.asBinder() : null);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(iAutoiotEventListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public void unregisterListener(IAutoiotEventListener iAutoiotEventListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAutoiotEventListener != null ? iAutoiotEventListener.asBinder() : null);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(iAutoiotEventListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public String getGatewayInfo(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGatewayInfo(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public int getMTU(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMTU(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotInterface
            public List<String> getDeviceList() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotInterface.DESCRIPTOR);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceList();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAutoiotInterface iAutoiotInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAutoiotInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAutoiotInterface;
            return true;
        }

        public static IAutoiotInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
