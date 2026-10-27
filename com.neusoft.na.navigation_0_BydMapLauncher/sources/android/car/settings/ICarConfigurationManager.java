package android.car.settings;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ICarConfigurationManager extends IInterface {

    public static class Default implements ICarConfigurationManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.settings.ICarConfigurationManager
        public SpeedBumpConfiguration getSpeedBumpConfiguration() throws RemoteException {
            return null;
        }
    }

    SpeedBumpConfiguration getSpeedBumpConfiguration() throws RemoteException;

    public static abstract class Stub extends Binder implements ICarConfigurationManager {
        private static final String DESCRIPTOR = "android.car.settings.ICarConfigurationManager";
        static final int TRANSACTION_getSpeedBumpConfiguration = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarConfigurationManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICarConfigurationManager)) {
                return (ICarConfigurationManager) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            SpeedBumpConfiguration speedBumpConfiguration = getSpeedBumpConfiguration();
            parcel2.writeNoException();
            if (speedBumpConfiguration != null) {
                parcel2.writeInt(1);
                speedBumpConfiguration.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }

        private static class Proxy implements ICarConfigurationManager {
            public static ICarConfigurationManager sDefaultImpl;
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

            @Override // android.car.settings.ICarConfigurationManager
            public SpeedBumpConfiguration getSpeedBumpConfiguration() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSpeedBumpConfiguration();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SpeedBumpConfiguration.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarConfigurationManager iCarConfigurationManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarConfigurationManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarConfigurationManager;
            return true;
        }

        public static ICarConfigurationManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
