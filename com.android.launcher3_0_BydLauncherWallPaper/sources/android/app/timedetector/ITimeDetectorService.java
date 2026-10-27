package android.app.timedetector;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ITimeDetectorService extends IInterface {

    public static class Default implements ITimeDetectorService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.timedetector.ITimeDetectorService
        public void suggestManualTime(ManualTimeSuggestion manualTimeSuggestion) throws RemoteException {
        }

        @Override // android.app.timedetector.ITimeDetectorService
        public void suggestNetworkTime(NetworkTimeSuggestion networkTimeSuggestion) throws RemoteException {
        }

        @Override // android.app.timedetector.ITimeDetectorService
        public void suggestTelephonyTime(TelephonyTimeSuggestion telephonyTimeSuggestion) throws RemoteException {
        }
    }

    void suggestManualTime(ManualTimeSuggestion manualTimeSuggestion) throws RemoteException;

    void suggestNetworkTime(NetworkTimeSuggestion networkTimeSuggestion) throws RemoteException;

    void suggestTelephonyTime(TelephonyTimeSuggestion telephonyTimeSuggestion) throws RemoteException;

    public static abstract class Stub extends Binder implements ITimeDetectorService {
        private static final String DESCRIPTOR = "android.app.timedetector.ITimeDetectorService";
        static final int TRANSACTION_suggestManualTime = 1;
        static final int TRANSACTION_suggestNetworkTime = 2;
        static final int TRANSACTION_suggestTelephonyTime = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "suggestManualTime";
            }
            if (i == 2) {
                return "suggestNetworkTime";
            }
            if (i != 3) {
                return null;
            }
            return "suggestTelephonyTime";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITimeDetectorService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITimeDetectorService)) {
                return (ITimeDetectorService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                suggestManualTime(parcel.readInt() != 0 ? ManualTimeSuggestion.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                suggestNetworkTime(parcel.readInt() != 0 ? NetworkTimeSuggestion.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            suggestTelephonyTime(parcel.readInt() != 0 ? TelephonyTimeSuggestion.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ITimeDetectorService {
            public static ITimeDetectorService sDefaultImpl;
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

            @Override // android.app.timedetector.ITimeDetectorService
            public void suggestManualTime(ManualTimeSuggestion manualTimeSuggestion) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (manualTimeSuggestion != null) {
                        parcelObtain.writeInt(1);
                        manualTimeSuggestion.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().suggestManualTime(manualTimeSuggestion);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.timedetector.ITimeDetectorService
            public void suggestNetworkTime(NetworkTimeSuggestion networkTimeSuggestion) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (networkTimeSuggestion != null) {
                        parcelObtain.writeInt(1);
                        networkTimeSuggestion.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().suggestNetworkTime(networkTimeSuggestion);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.timedetector.ITimeDetectorService
            public void suggestTelephonyTime(TelephonyTimeSuggestion telephonyTimeSuggestion) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (telephonyTimeSuggestion != null) {
                        parcelObtain.writeInt(1);
                        telephonyTimeSuggestion.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().suggestTelephonyTime(telephonyTimeSuggestion);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITimeDetectorService iTimeDetectorService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTimeDetectorService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTimeDetectorService;
            return true;
        }

        public static ITimeDetectorService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
