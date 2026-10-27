package android.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ICameraServiceListener extends IInterface {
    public static final int STATUS_ENUMERATING = 2;
    public static final int STATUS_NOT_AVAILABLE = -2;
    public static final int STATUS_NOT_PRESENT = 0;
    public static final int STATUS_PRESENT = 1;
    public static final int STATUS_UNKNOWN = -1;
    public static final int TORCH_STATUS_AVAILABLE_OFF = 1;
    public static final int TORCH_STATUS_AVAILABLE_ON = 2;
    public static final int TORCH_STATUS_NOT_AVAILABLE = 0;
    public static final int TORCH_STATUS_UNKNOWN = -1;

    public static class Default implements ICameraServiceListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.ICameraServiceListener
        public void onCameraAccessPrioritiesChanged() throws RemoteException {
        }

        @Override // android.hardware.ICameraServiceListener
        public void onCameraClosed(String str) throws RemoteException {
        }

        @Override // android.hardware.ICameraServiceListener
        public void onCameraOpened(String str, String str2) throws RemoteException {
        }

        @Override // android.hardware.ICameraServiceListener
        public void onPhysicalCameraStatusChanged(int i, String str, String str2) throws RemoteException {
        }

        @Override // android.hardware.ICameraServiceListener
        public void onStatusChanged(int i, String str) throws RemoteException {
        }

        @Override // android.hardware.ICameraServiceListener
        public void onTorchStatusChanged(int i, String str) throws RemoteException {
        }
    }

    void onCameraAccessPrioritiesChanged() throws RemoteException;

    void onCameraClosed(String str) throws RemoteException;

    void onCameraOpened(String str, String str2) throws RemoteException;

    void onPhysicalCameraStatusChanged(int i, String str, String str2) throws RemoteException;

    void onStatusChanged(int i, String str) throws RemoteException;

    void onTorchStatusChanged(int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements ICameraServiceListener {
        private static final String DESCRIPTOR = "android.hardware.ICameraServiceListener";
        static final int TRANSACTION_onCameraAccessPrioritiesChanged = 4;
        static final int TRANSACTION_onCameraClosed = 6;
        static final int TRANSACTION_onCameraOpened = 5;
        static final int TRANSACTION_onPhysicalCameraStatusChanged = 2;
        static final int TRANSACTION_onStatusChanged = 1;
        static final int TRANSACTION_onTorchStatusChanged = 3;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onStatusChanged";
                case 2:
                    return "onPhysicalCameraStatusChanged";
                case 3:
                    return "onTorchStatusChanged";
                case 4:
                    return "onCameraAccessPrioritiesChanged";
                case 5:
                    return "onCameraOpened";
                case 6:
                    return "onCameraClosed";
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

        public static ICameraServiceListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICameraServiceListener)) {
                return (ICameraServiceListener) iInterfaceQueryLocalInterface;
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
                    onStatusChanged(parcel.readInt(), parcel.readString());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPhysicalCameraStatusChanged(parcel.readInt(), parcel.readString(), parcel.readString());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTorchStatusChanged(parcel.readInt(), parcel.readString());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCameraAccessPrioritiesChanged();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCameraOpened(parcel.readString(), parcel.readString());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCameraClosed(parcel.readString());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ICameraServiceListener {
            public static ICameraServiceListener sDefaultImpl;
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

            @Override // android.hardware.ICameraServiceListener
            public void onStatusChanged(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStatusChanged(i, str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraServiceListener
            public void onPhysicalCameraStatusChanged(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPhysicalCameraStatusChanged(i, str, str2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraServiceListener
            public void onTorchStatusChanged(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTorchStatusChanged(i, str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraServiceListener
            public void onCameraAccessPrioritiesChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCameraAccessPrioritiesChanged();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraServiceListener
            public void onCameraOpened(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCameraOpened(str, str2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraServiceListener
            public void onCameraClosed(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCameraClosed(str);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICameraServiceListener iCameraServiceListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCameraServiceListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCameraServiceListener;
            return true;
        }

        public static ICameraServiceListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
