package android.app.trust;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IStrongAuthTracker extends IInterface {

    public static class Default implements IStrongAuthTracker {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.trust.IStrongAuthTracker
        public void onIsNonStrongBiometricAllowedChanged(boolean z, int i) throws RemoteException {
        }

        @Override // android.app.trust.IStrongAuthTracker
        public void onStrongAuthRequiredChanged(int i, int i2) throws RemoteException {
        }
    }

    void onIsNonStrongBiometricAllowedChanged(boolean z, int i) throws RemoteException;

    void onStrongAuthRequiredChanged(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IStrongAuthTracker {
        private static final String DESCRIPTOR = "android.app.trust.IStrongAuthTracker";
        static final int TRANSACTION_onIsNonStrongBiometricAllowedChanged = 2;
        static final int TRANSACTION_onStrongAuthRequiredChanged = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onStrongAuthRequiredChanged";
            }
            if (i != 2) {
                return null;
            }
            return "onIsNonStrongBiometricAllowedChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IStrongAuthTracker asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IStrongAuthTracker)) {
                return (IStrongAuthTracker) iInterfaceQueryLocalInterface;
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
                onStrongAuthRequiredChanged(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onIsNonStrongBiometricAllowedChanged(parcel.readInt() != 0, parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IStrongAuthTracker {
            public static IStrongAuthTracker sDefaultImpl;
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

            @Override // android.app.trust.IStrongAuthTracker
            public void onStrongAuthRequiredChanged(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStrongAuthRequiredChanged(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.trust.IStrongAuthTracker
            public void onIsNonStrongBiometricAllowedChanged(boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onIsNonStrongBiometricAllowedChanged(z, i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IStrongAuthTracker iStrongAuthTracker) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iStrongAuthTracker == null) {
                return false;
            }
            Proxy.sDefaultImpl = iStrongAuthTracker;
            return true;
        }

        public static IStrongAuthTracker getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
