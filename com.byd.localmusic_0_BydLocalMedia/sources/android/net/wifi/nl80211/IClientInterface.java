package android.net.wifi.nl80211;

import android.net.wifi.nl80211.ISendMgmtFrameEvent;
import android.net.wifi.nl80211.IWifiScannerImpl;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IClientInterface extends IInterface {

    public static class Default implements IClientInterface {
        @Override // android.net.wifi.nl80211.IClientInterface
        public void SendMgmtFrame(byte[] bArr, ISendMgmtFrameEvent iSendMgmtFrameEvent, int i) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.wifi.nl80211.IClientInterface
        public String getInterfaceName() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IClientInterface
        public byte[] getMacAddress() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IClientInterface
        public int[] getPacketCounters() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IClientInterface
        public IWifiScannerImpl getWifiScannerImpl() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IClientInterface
        public int[] signalPoll() throws RemoteException {
            return null;
        }
    }

    void SendMgmtFrame(byte[] bArr, ISendMgmtFrameEvent iSendMgmtFrameEvent, int i) throws RemoteException;

    String getInterfaceName() throws RemoteException;

    byte[] getMacAddress() throws RemoteException;

    int[] getPacketCounters() throws RemoteException;

    IWifiScannerImpl getWifiScannerImpl() throws RemoteException;

    int[] signalPoll() throws RemoteException;

    public static abstract class Stub extends Binder implements IClientInterface {
        private static final String DESCRIPTOR = "android.net.wifi.nl80211.IClientInterface";
        static final int TRANSACTION_SendMgmtFrame = 6;
        static final int TRANSACTION_getInterfaceName = 4;
        static final int TRANSACTION_getMacAddress = 3;
        static final int TRANSACTION_getPacketCounters = 1;
        static final int TRANSACTION_getWifiScannerImpl = 5;
        static final int TRANSACTION_signalPoll = 2;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getPacketCounters";
                case 2:
                    return "signalPoll";
                case 3:
                    return "getMacAddress";
                case 4:
                    return "getInterfaceName";
                case 5:
                    return "getWifiScannerImpl";
                case 6:
                    return "SendMgmtFrame";
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

        public static IClientInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IClientInterface)) {
                return (IClientInterface) iInterfaceQueryLocalInterface;
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
                    int[] packetCounters = getPacketCounters();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(packetCounters);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] iArrSignalPoll = signalPoll();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(iArrSignalPoll);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] macAddress = getMacAddress();
                    parcel2.writeNoException();
                    parcel2.writeByteArray(macAddress);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    String interfaceName = getInterfaceName();
                    parcel2.writeNoException();
                    parcel2.writeString(interfaceName);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    IWifiScannerImpl wifiScannerImpl = getWifiScannerImpl();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(wifiScannerImpl != null ? wifiScannerImpl.asBinder() : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    SendMgmtFrame(parcel.createByteArray(), ISendMgmtFrameEvent.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IClientInterface {
            public static IClientInterface sDefaultImpl;
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

            @Override // android.net.wifi.nl80211.IClientInterface
            public int[] getPacketCounters() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPacketCounters();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IClientInterface
            public int[] signalPoll() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().signalPoll();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IClientInterface
            public byte[] getMacAddress() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMacAddress();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IClientInterface
            public String getInterfaceName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInterfaceName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IClientInterface
            public IWifiScannerImpl getWifiScannerImpl() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWifiScannerImpl();
                    }
                    parcelObtain2.readException();
                    return IWifiScannerImpl.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IClientInterface
            public void SendMgmtFrame(byte[] bArr, ISendMgmtFrameEvent iSendMgmtFrameEvent, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeStrongBinder(iSendMgmtFrameEvent != null ? iSendMgmtFrameEvent.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().SendMgmtFrame(bArr, iSendMgmtFrameEvent, i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IClientInterface iClientInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iClientInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iClientInterface;
            return true;
        }

        public static IClientInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
