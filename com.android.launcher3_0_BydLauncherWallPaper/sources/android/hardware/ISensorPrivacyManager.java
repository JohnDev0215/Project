package android.hardware;

import android.hardware.ISensorPrivacyListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ISensorPrivacyManager extends IInterface {

    public static class Default implements ISensorPrivacyManager {
        @Override // android.hardware.ISensorPrivacyManager
        public void addSensorPrivacyListener(ISensorPrivacyListener iSensorPrivacyListener) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.ISensorPrivacyManager
        public boolean isSensorPrivacyEnabled() throws RemoteException {
            return false;
        }

        @Override // android.hardware.ISensorPrivacyManager
        public void removeSensorPrivacyListener(ISensorPrivacyListener iSensorPrivacyListener) throws RemoteException {
        }

        @Override // android.hardware.ISensorPrivacyManager
        public void setSensorPrivacy(boolean z) throws RemoteException {
        }
    }

    void addSensorPrivacyListener(ISensorPrivacyListener iSensorPrivacyListener) throws RemoteException;

    boolean isSensorPrivacyEnabled() throws RemoteException;

    void removeSensorPrivacyListener(ISensorPrivacyListener iSensorPrivacyListener) throws RemoteException;

    void setSensorPrivacy(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements ISensorPrivacyManager {
        private static final String DESCRIPTOR = "android.hardware.ISensorPrivacyManager";
        static final int TRANSACTION_addSensorPrivacyListener = 1;
        static final int TRANSACTION_isSensorPrivacyEnabled = 3;
        static final int TRANSACTION_removeSensorPrivacyListener = 2;
        static final int TRANSACTION_setSensorPrivacy = 4;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "addSensorPrivacyListener";
            }
            if (i == 2) {
                return "removeSensorPrivacyListener";
            }
            if (i == 3) {
                return "isSensorPrivacyEnabled";
            }
            if (i != 4) {
                return null;
            }
            return "setSensorPrivacy";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISensorPrivacyManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISensorPrivacyManager)) {
                return (ISensorPrivacyManager) iInterfaceQueryLocalInterface;
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
                addSensorPrivacyListener(ISensorPrivacyListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                removeSensorPrivacyListener(ISensorPrivacyListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zIsSensorPrivacyEnabled = isSensorPrivacyEnabled();
                parcel2.writeNoException();
                parcel2.writeInt(zIsSensorPrivacyEnabled ? 1 : 0);
                return true;
            }
            if (i != 4) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            setSensorPrivacy(parcel.readInt() != 0);
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ISensorPrivacyManager {
            public static ISensorPrivacyManager sDefaultImpl;
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

            @Override // android.hardware.ISensorPrivacyManager
            public void addSensorPrivacyListener(ISensorPrivacyListener iSensorPrivacyListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iSensorPrivacyListener != null ? iSensorPrivacyListener.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addSensorPrivacyListener(iSensorPrivacyListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ISensorPrivacyManager
            public void removeSensorPrivacyListener(ISensorPrivacyListener iSensorPrivacyListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iSensorPrivacyListener != null ? iSensorPrivacyListener.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeSensorPrivacyListener(iSensorPrivacyListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ISensorPrivacyManager
            public boolean isSensorPrivacyEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSensorPrivacyEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ISensorPrivacyManager
            public void setSensorPrivacy(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSensorPrivacy(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISensorPrivacyManager iSensorPrivacyManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSensorPrivacyManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSensorPrivacyManager;
            return true;
        }

        public static ISensorPrivacyManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
