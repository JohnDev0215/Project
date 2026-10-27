package android.net.wifi.nl80211;

import android.net.wifi.nl80211.IPnoScanEvent;
import android.net.wifi.nl80211.IScanEvent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IWifiScannerImpl extends IInterface {
    public static final int SCAN_TYPE_DEFAULT = -1;
    public static final int SCAN_TYPE_HIGH_ACCURACY = 2;
    public static final int SCAN_TYPE_LOW_POWER = 1;
    public static final int SCAN_TYPE_LOW_SPAN = 0;

    public static class Default implements IWifiScannerImpl {
        @Override // android.net.wifi.nl80211.IWifiScannerImpl
        public void abortScan() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWifiScannerImpl
        public NativeScanResult[] getPnoScanResults() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWifiScannerImpl
        public NativeScanResult[] getScanResults() throws RemoteException {
            return null;
        }

        @Override // android.net.wifi.nl80211.IWifiScannerImpl
        public boolean scan(SingleScanSettings singleScanSettings) throws RemoteException {
            return false;
        }

        @Override // android.net.wifi.nl80211.IWifiScannerImpl
        public boolean startPnoScan(PnoSettings pnoSettings) throws RemoteException {
            return false;
        }

        @Override // android.net.wifi.nl80211.IWifiScannerImpl
        public boolean stopPnoScan() throws RemoteException {
            return false;
        }

        @Override // android.net.wifi.nl80211.IWifiScannerImpl
        public void subscribePnoScanEvents(IPnoScanEvent iPnoScanEvent) throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.IWifiScannerImpl
        public void subscribeScanEvents(IScanEvent iScanEvent) throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.IWifiScannerImpl
        public void unsubscribePnoScanEvents() throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.IWifiScannerImpl
        public void unsubscribeScanEvents() throws RemoteException {
        }
    }

    void abortScan() throws RemoteException;

    NativeScanResult[] getPnoScanResults() throws RemoteException;

    NativeScanResult[] getScanResults() throws RemoteException;

    boolean scan(SingleScanSettings singleScanSettings) throws RemoteException;

    boolean startPnoScan(PnoSettings pnoSettings) throws RemoteException;

    boolean stopPnoScan() throws RemoteException;

    void subscribePnoScanEvents(IPnoScanEvent iPnoScanEvent) throws RemoteException;

    void subscribeScanEvents(IScanEvent iScanEvent) throws RemoteException;

    void unsubscribePnoScanEvents() throws RemoteException;

    void unsubscribeScanEvents() throws RemoteException;

    public static abstract class Stub extends Binder implements IWifiScannerImpl {
        private static final String DESCRIPTOR = "android.net.wifi.nl80211.IWifiScannerImpl";
        static final int TRANSACTION_abortScan = 10;
        static final int TRANSACTION_getPnoScanResults = 2;
        static final int TRANSACTION_getScanResults = 1;
        static final int TRANSACTION_scan = 3;
        static final int TRANSACTION_startPnoScan = 8;
        static final int TRANSACTION_stopPnoScan = 9;
        static final int TRANSACTION_subscribePnoScanEvents = 6;
        static final int TRANSACTION_subscribeScanEvents = 4;
        static final int TRANSACTION_unsubscribePnoScanEvents = 7;
        static final int TRANSACTION_unsubscribeScanEvents = 5;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getScanResults";
                case 2:
                    return "getPnoScanResults";
                case 3:
                    return "scan";
                case 4:
                    return "subscribeScanEvents";
                case 5:
                    return "unsubscribeScanEvents";
                case 6:
                    return "subscribePnoScanEvents";
                case 7:
                    return "unsubscribePnoScanEvents";
                case 8:
                    return "startPnoScan";
                case 9:
                    return "stopPnoScan";
                case 10:
                    return "abortScan";
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

        public static IWifiScannerImpl asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IWifiScannerImpl)) {
                return (IWifiScannerImpl) iInterfaceQueryLocalInterface;
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
                    NativeScanResult[] scanResults = getScanResults();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(scanResults, 1);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    NativeScanResult[] pnoScanResults = getPnoScanResults();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(pnoScanResults, 1);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zScan = scan(parcel.readInt() != 0 ? SingleScanSettings.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zScan ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    subscribeScanEvents(IScanEvent.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    unsubscribeScanEvents();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    subscribePnoScanEvents(IPnoScanEvent.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    unsubscribePnoScanEvents();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zStartPnoScan = startPnoScan(parcel.readInt() != 0 ? PnoSettings.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zStartPnoScan ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zStopPnoScan = stopPnoScan();
                    parcel2.writeNoException();
                    parcel2.writeInt(zStopPnoScan ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    abortScan();
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IWifiScannerImpl {
            public static IWifiScannerImpl sDefaultImpl;
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

            @Override // android.net.wifi.nl80211.IWifiScannerImpl
            public NativeScanResult[] getScanResults() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getScanResults();
                    }
                    parcelObtain2.readException();
                    return (NativeScanResult[]) parcelObtain2.createTypedArray(NativeScanResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWifiScannerImpl
            public NativeScanResult[] getPnoScanResults() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPnoScanResults();
                    }
                    parcelObtain2.readException();
                    return (NativeScanResult[]) parcelObtain2.createTypedArray(NativeScanResult.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWifiScannerImpl
            public boolean scan(SingleScanSettings singleScanSettings) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (singleScanSettings != null) {
                        parcelObtain.writeInt(1);
                        singleScanSettings.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().scan(singleScanSettings);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWifiScannerImpl
            public void subscribeScanEvents(IScanEvent iScanEvent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iScanEvent != null ? iScanEvent.asBinder() : null);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().subscribeScanEvents(iScanEvent);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWifiScannerImpl
            public void unsubscribeScanEvents() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unsubscribeScanEvents();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWifiScannerImpl
            public void subscribePnoScanEvents(IPnoScanEvent iPnoScanEvent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iPnoScanEvent != null ? iPnoScanEvent.asBinder() : null);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().subscribePnoScanEvents(iPnoScanEvent);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWifiScannerImpl
            public void unsubscribePnoScanEvents() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unsubscribePnoScanEvents();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWifiScannerImpl
            public boolean startPnoScan(PnoSettings pnoSettings) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (pnoSettings != null) {
                        parcelObtain.writeInt(1);
                        pnoSettings.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startPnoScan(pnoSettings);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWifiScannerImpl
            public boolean stopPnoScan() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopPnoScan();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.IWifiScannerImpl
            public void abortScan() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().abortScan();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWifiScannerImpl iWifiScannerImpl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iWifiScannerImpl == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWifiScannerImpl;
            return true;
        }

        public static IWifiScannerImpl getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
