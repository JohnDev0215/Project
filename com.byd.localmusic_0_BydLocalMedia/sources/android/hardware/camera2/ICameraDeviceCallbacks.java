package android.hardware.camera2;

import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.impl.CaptureResultExtras;
import android.hardware.camera2.impl.PhysicalCaptureResultInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ICameraDeviceCallbacks extends IInterface {
    public static final int ERROR_CAMERA_BUFFER = 5;
    public static final int ERROR_CAMERA_DEVICE = 1;
    public static final int ERROR_CAMERA_DISABLED = 6;
    public static final int ERROR_CAMERA_DISCONNECTED = 0;
    public static final int ERROR_CAMERA_INVALID_ERROR = -1;
    public static final int ERROR_CAMERA_REQUEST = 3;
    public static final int ERROR_CAMERA_RESULT = 4;
    public static final int ERROR_CAMERA_SERVICE = 2;

    public static class Default implements ICameraDeviceCallbacks {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onCaptureStarted(CaptureResultExtras captureResultExtras, long j) throws RemoteException {
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onDeviceError(int i, CaptureResultExtras captureResultExtras) throws RemoteException {
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onDeviceIdle() throws RemoteException {
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onPrepared(int i) throws RemoteException {
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onRepeatingRequestError(long j, int i) throws RemoteException {
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onRequestQueueEmpty() throws RemoteException {
        }

        @Override // android.hardware.camera2.ICameraDeviceCallbacks
        public void onResultReceived(CameraMetadataNative cameraMetadataNative, CaptureResultExtras captureResultExtras, PhysicalCaptureResultInfo[] physicalCaptureResultInfoArr) throws RemoteException {
        }
    }

    void onCaptureStarted(CaptureResultExtras captureResultExtras, long j) throws RemoteException;

    void onDeviceError(int i, CaptureResultExtras captureResultExtras) throws RemoteException;

    void onDeviceIdle() throws RemoteException;

    void onPrepared(int i) throws RemoteException;

    void onRepeatingRequestError(long j, int i) throws RemoteException;

    void onRequestQueueEmpty() throws RemoteException;

    void onResultReceived(CameraMetadataNative cameraMetadataNative, CaptureResultExtras captureResultExtras, PhysicalCaptureResultInfo[] physicalCaptureResultInfoArr) throws RemoteException;

    public static abstract class Stub extends Binder implements ICameraDeviceCallbacks {
        private static final String DESCRIPTOR = "android.hardware.camera2.ICameraDeviceCallbacks";
        static final int TRANSACTION_onCaptureStarted = 3;
        static final int TRANSACTION_onDeviceError = 1;
        static final int TRANSACTION_onDeviceIdle = 2;
        static final int TRANSACTION_onPrepared = 5;
        static final int TRANSACTION_onRepeatingRequestError = 6;
        static final int TRANSACTION_onRequestQueueEmpty = 7;
        static final int TRANSACTION_onResultReceived = 4;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onDeviceError";
                case 2:
                    return "onDeviceIdle";
                case 3:
                    return "onCaptureStarted";
                case 4:
                    return "onResultReceived";
                case 5:
                    return "onPrepared";
                case 6:
                    return "onRepeatingRequestError";
                case 7:
                    return "onRequestQueueEmpty";
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

        public static ICameraDeviceCallbacks asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICameraDeviceCallbacks)) {
                return (ICameraDeviceCallbacks) iInterfaceQueryLocalInterface;
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
                    onDeviceError(parcel.readInt(), parcel.readInt() != 0 ? CaptureResultExtras.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDeviceIdle();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCaptureStarted(parcel.readInt() != 0 ? CaptureResultExtras.CREATOR.createFromParcel(parcel) : null, parcel.readLong());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onResultReceived(parcel.readInt() != 0 ? CameraMetadataNative.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? CaptureResultExtras.CREATOR.createFromParcel(parcel) : null, (PhysicalCaptureResultInfo[]) parcel.createTypedArray(PhysicalCaptureResultInfo.CREATOR));
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPrepared(parcel.readInt());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRepeatingRequestError(parcel.readLong(), parcel.readInt());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRequestQueueEmpty();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ICameraDeviceCallbacks {
            public static ICameraDeviceCallbacks sDefaultImpl;
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

            @Override // android.hardware.camera2.ICameraDeviceCallbacks
            public void onDeviceError(int i, CaptureResultExtras captureResultExtras) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (captureResultExtras != null) {
                        parcelObtain.writeInt(1);
                        captureResultExtras.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDeviceError(i, captureResultExtras);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceCallbacks
            public void onDeviceIdle() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDeviceIdle();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceCallbacks
            public void onCaptureStarted(CaptureResultExtras captureResultExtras, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (captureResultExtras != null) {
                        parcelObtain.writeInt(1);
                        captureResultExtras.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCaptureStarted(captureResultExtras, j);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceCallbacks
            public void onResultReceived(CameraMetadataNative cameraMetadataNative, CaptureResultExtras captureResultExtras, PhysicalCaptureResultInfo[] physicalCaptureResultInfoArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cameraMetadataNative != null) {
                        parcelObtain.writeInt(1);
                        cameraMetadataNative.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (captureResultExtras != null) {
                        parcelObtain.writeInt(1);
                        captureResultExtras.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedArray(physicalCaptureResultInfoArr, 0);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onResultReceived(cameraMetadataNative, captureResultExtras, physicalCaptureResultInfoArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceCallbacks
            public void onPrepared(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPrepared(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceCallbacks
            public void onRepeatingRequestError(long j, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRepeatingRequestError(j, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceCallbacks
            public void onRequestQueueEmpty() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRequestQueueEmpty();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICameraDeviceCallbacks iCameraDeviceCallbacks) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCameraDeviceCallbacks == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCameraDeviceCallbacks;
            return true;
        }

        public static ICameraDeviceCallbacks getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
