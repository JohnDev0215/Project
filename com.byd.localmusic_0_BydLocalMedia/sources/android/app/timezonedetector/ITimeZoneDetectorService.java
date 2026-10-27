package android.app.timezonedetector;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ITimeZoneDetectorService extends IInterface {

    public static class Default implements ITimeZoneDetectorService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.timezonedetector.ITimeZoneDetectorService
        public void suggestManualTimeZone(ManualTimeZoneSuggestion manualTimeZoneSuggestion) throws RemoteException {
        }

        @Override // android.app.timezonedetector.ITimeZoneDetectorService
        public void suggestTelephonyTimeZone(TelephonyTimeZoneSuggestion telephonyTimeZoneSuggestion) throws RemoteException {
        }
    }

    void suggestManualTimeZone(ManualTimeZoneSuggestion manualTimeZoneSuggestion) throws RemoteException;

    void suggestTelephonyTimeZone(TelephonyTimeZoneSuggestion telephonyTimeZoneSuggestion) throws RemoteException;

    public static abstract class Stub extends Binder implements ITimeZoneDetectorService {
        private static final String DESCRIPTOR = "android.app.timezonedetector.ITimeZoneDetectorService";
        static final int TRANSACTION_suggestManualTimeZone = 1;
        static final int TRANSACTION_suggestTelephonyTimeZone = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "suggestManualTimeZone";
            }
            if (i != 2) {
                return null;
            }
            return "suggestTelephonyTimeZone";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITimeZoneDetectorService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITimeZoneDetectorService)) {
                return (ITimeZoneDetectorService) iInterfaceQueryLocalInterface;
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
                suggestManualTimeZone(parcel.readInt() != 0 ? ManualTimeZoneSuggestion.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            suggestTelephonyTimeZone(parcel.readInt() != 0 ? TelephonyTimeZoneSuggestion.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ITimeZoneDetectorService {
            public static ITimeZoneDetectorService sDefaultImpl;
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

            @Override // android.app.timezonedetector.ITimeZoneDetectorService
            public void suggestManualTimeZone(ManualTimeZoneSuggestion manualTimeZoneSuggestion) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (manualTimeZoneSuggestion != null) {
                        parcelObtain.writeInt(1);
                        manualTimeZoneSuggestion.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().suggestManualTimeZone(manualTimeZoneSuggestion);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.timezonedetector.ITimeZoneDetectorService
            public void suggestTelephonyTimeZone(TelephonyTimeZoneSuggestion telephonyTimeZoneSuggestion) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (telephonyTimeZoneSuggestion != null) {
                        parcelObtain.writeInt(1);
                        telephonyTimeZoneSuggestion.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().suggestTelephonyTimeZone(telephonyTimeZoneSuggestion);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITimeZoneDetectorService iTimeZoneDetectorService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTimeZoneDetectorService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTimeZoneDetectorService;
            return true;
        }

        public static ITimeZoneDetectorService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
