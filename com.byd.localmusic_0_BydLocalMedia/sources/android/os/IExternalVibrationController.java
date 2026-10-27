package android.os;

import android.media.MediaMetrics;

/* JADX INFO: loaded from: classes2.dex */
public interface IExternalVibrationController extends IInterface {

    public static class Default implements IExternalVibrationController {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IExternalVibrationController
        public boolean mute() throws RemoteException {
            return false;
        }

        @Override // android.os.IExternalVibrationController
        public boolean unmute() throws RemoteException {
            return false;
        }
    }

    boolean mute() throws RemoteException;

    boolean unmute() throws RemoteException;

    public static abstract class Stub extends Binder implements IExternalVibrationController {
        private static final String DESCRIPTOR = "android.os.IExternalVibrationController";
        static final int TRANSACTION_mute = 1;
        static final int TRANSACTION_unmute = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return MediaMetrics.Value.MUTE;
            }
            if (i != 2) {
                return null;
            }
            return MediaMetrics.Value.UNMUTE;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IExternalVibrationController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IExternalVibrationController)) {
                return (IExternalVibrationController) iInterfaceQueryLocalInterface;
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
                boolean zMute = mute();
                parcel2.writeNoException();
                parcel2.writeInt(zMute ? 1 : 0);
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
            boolean zUnmute = unmute();
            parcel2.writeNoException();
            parcel2.writeInt(zUnmute ? 1 : 0);
            return true;
        }

        private static class Proxy implements IExternalVibrationController {
            public static IExternalVibrationController sDefaultImpl;
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

            @Override // android.os.IExternalVibrationController
            public boolean mute() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().mute();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IExternalVibrationController
            public boolean unmute() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unmute();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IExternalVibrationController iExternalVibrationController) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iExternalVibrationController == null) {
                return false;
            }
            Proxy.sDefaultImpl = iExternalVibrationController;
            return true;
        }

        public static IExternalVibrationController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
