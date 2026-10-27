package android.hardware.camera2;

import android.hardware.camera2.ICameraDeviceCallbacks;
import android.hardware.camera2.ICameraOfflineSession;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.hardware.camera2.utils.SubmitInfo;
import android.media.MediaMetrics;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;

/* JADX INFO: loaded from: classes.dex */
public interface ICameraDeviceUser extends IInterface {
    public static final int AUDIO_RESTRICTION_NONE = 0;
    public static final int AUDIO_RESTRICTION_VIBRATION = 1;
    public static final int AUDIO_RESTRICTION_VIBRATION_SOUND = 3;
    public static final int CONSTRAINED_HIGH_SPEED_MODE = 1;
    public static final int NORMAL_MODE = 0;
    public static final int NO_IN_FLIGHT_REPEATING_FRAMES = -1;
    public static final int TEMPLATE_MANUAL = 6;
    public static final int TEMPLATE_PREVIEW = 1;
    public static final int TEMPLATE_RECORD = 3;
    public static final int TEMPLATE_STILL_CAPTURE = 2;
    public static final int TEMPLATE_VIDEO_SNAPSHOT = 4;
    public static final int TEMPLATE_ZERO_SHUTTER_LAG = 5;
    public static final int VENDOR_MODE_START = 32768;

    public static class Default implements ICameraDeviceUser {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public void beginConfigure() throws RemoteException {
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public long cancelRequest(int i) throws RemoteException {
            return 0L;
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public CameraMetadataNative createDefaultRequest(int i) throws RemoteException {
            return null;
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public int createInputStream(int i, int i2, int i3) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public int createStream(OutputConfiguration outputConfiguration) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public void deleteStream(int i) throws RemoteException {
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public void disconnect() throws RemoteException {
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public int[] endConfigure(int i, CameraMetadataNative cameraMetadataNative) throws RemoteException {
            return null;
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public void finalizeOutputConfigurations(int i, OutputConfiguration outputConfiguration) throws RemoteException {
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public long flush() throws RemoteException {
            return 0L;
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public CameraMetadataNative getCameraInfo() throws RemoteException {
            return null;
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public int getGlobalAudioRestriction() throws RemoteException {
            return 0;
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public Surface getInputSurface() throws RemoteException {
            return null;
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public boolean isSessionConfigurationSupported(SessionConfiguration sessionConfiguration) throws RemoteException {
            return false;
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public void prepare(int i) throws RemoteException {
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public void prepare2(int i, int i2) throws RemoteException {
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public void setCameraAudioRestriction(int i) throws RemoteException {
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public SubmitInfo submitRequest(CaptureRequest captureRequest, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public SubmitInfo submitRequestList(CaptureRequest[] captureRequestArr, boolean z) throws RemoteException {
            return null;
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public ICameraOfflineSession switchToOffline(ICameraDeviceCallbacks iCameraDeviceCallbacks, int[] iArr) throws RemoteException {
            return null;
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public void tearDown(int i) throws RemoteException {
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public void updateOutputConfiguration(int i, OutputConfiguration outputConfiguration) throws RemoteException {
        }

        @Override // android.hardware.camera2.ICameraDeviceUser
        public void waitUntilIdle() throws RemoteException {
        }
    }

    void beginConfigure() throws RemoteException;

    long cancelRequest(int i) throws RemoteException;

    CameraMetadataNative createDefaultRequest(int i) throws RemoteException;

    int createInputStream(int i, int i2, int i3) throws RemoteException;

    int createStream(OutputConfiguration outputConfiguration) throws RemoteException;

    void deleteStream(int i) throws RemoteException;

    void disconnect() throws RemoteException;

    int[] endConfigure(int i, CameraMetadataNative cameraMetadataNative) throws RemoteException;

    void finalizeOutputConfigurations(int i, OutputConfiguration outputConfiguration) throws RemoteException;

    long flush() throws RemoteException;

    CameraMetadataNative getCameraInfo() throws RemoteException;

    int getGlobalAudioRestriction() throws RemoteException;

    Surface getInputSurface() throws RemoteException;

    boolean isSessionConfigurationSupported(SessionConfiguration sessionConfiguration) throws RemoteException;

    void prepare(int i) throws RemoteException;

    void prepare2(int i, int i2) throws RemoteException;

    void setCameraAudioRestriction(int i) throws RemoteException;

    SubmitInfo submitRequest(CaptureRequest captureRequest, boolean z) throws RemoteException;

    SubmitInfo submitRequestList(CaptureRequest[] captureRequestArr, boolean z) throws RemoteException;

    ICameraOfflineSession switchToOffline(ICameraDeviceCallbacks iCameraDeviceCallbacks, int[] iArr) throws RemoteException;

    void tearDown(int i) throws RemoteException;

    void updateOutputConfiguration(int i, OutputConfiguration outputConfiguration) throws RemoteException;

    void waitUntilIdle() throws RemoteException;

    public static abstract class Stub extends Binder implements ICameraDeviceUser {
        private static final String DESCRIPTOR = "android.hardware.camera2.ICameraDeviceUser";
        static final int TRANSACTION_beginConfigure = 5;
        static final int TRANSACTION_cancelRequest = 4;
        static final int TRANSACTION_createDefaultRequest = 12;
        static final int TRANSACTION_createInputStream = 10;
        static final int TRANSACTION_createStream = 9;
        static final int TRANSACTION_deleteStream = 8;
        static final int TRANSACTION_disconnect = 1;
        static final int TRANSACTION_endConfigure = 6;
        static final int TRANSACTION_finalizeOutputConfigurations = 20;
        static final int TRANSACTION_flush = 15;
        static final int TRANSACTION_getCameraInfo = 13;
        static final int TRANSACTION_getGlobalAudioRestriction = 22;
        static final int TRANSACTION_getInputSurface = 11;
        static final int TRANSACTION_isSessionConfigurationSupported = 7;
        static final int TRANSACTION_prepare = 16;
        static final int TRANSACTION_prepare2 = 18;
        static final int TRANSACTION_setCameraAudioRestriction = 21;
        static final int TRANSACTION_submitRequest = 2;
        static final int TRANSACTION_submitRequestList = 3;
        static final int TRANSACTION_switchToOffline = 23;
        static final int TRANSACTION_tearDown = 17;
        static final int TRANSACTION_updateOutputConfiguration = 19;
        static final int TRANSACTION_waitUntilIdle = 14;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return MediaMetrics.Value.DISCONNECT;
                case 2:
                    return "submitRequest";
                case 3:
                    return "submitRequestList";
                case 4:
                    return "cancelRequest";
                case 5:
                    return "beginConfigure";
                case 6:
                    return "endConfigure";
                case 7:
                    return "isSessionConfigurationSupported";
                case 8:
                    return "deleteStream";
                case 9:
                    return "createStream";
                case 10:
                    return "createInputStream";
                case 11:
                    return "getInputSurface";
                case 12:
                    return "createDefaultRequest";
                case 13:
                    return "getCameraInfo";
                case 14:
                    return "waitUntilIdle";
                case 15:
                    return "flush";
                case 16:
                    return "prepare";
                case 17:
                    return "tearDown";
                case 18:
                    return "prepare2";
                case 19:
                    return "updateOutputConfiguration";
                case 20:
                    return "finalizeOutputConfigurations";
                case 21:
                    return "setCameraAudioRestriction";
                case 22:
                    return "getGlobalAudioRestriction";
                case 23:
                    return "switchToOffline";
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

        public static ICameraDeviceUser asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICameraDeviceUser)) {
                return (ICameraDeviceUser) iInterfaceQueryLocalInterface;
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
                    disconnect();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    SubmitInfo submitInfoSubmitRequest = submitRequest(parcel.readInt() != 0 ? CaptureRequest.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (submitInfoSubmitRequest != null) {
                        parcel2.writeInt(1);
                        submitInfoSubmitRequest.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    SubmitInfo submitInfoSubmitRequestList = submitRequestList((CaptureRequest[]) parcel.createTypedArray(CaptureRequest.CREATOR), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    if (submitInfoSubmitRequestList != null) {
                        parcel2.writeInt(1);
                        submitInfoSubmitRequestList.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    long jCancelRequest = cancelRequest(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(jCancelRequest);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    beginConfigure();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] iArrEndConfigure = endConfigure(parcel.readInt(), parcel.readInt() != 0 ? CameraMetadataNative.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeIntArray(iArrEndConfigure);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSessionConfigurationSupported = isSessionConfigurationSupported(parcel.readInt() != 0 ? SessionConfiguration.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSessionConfigurationSupported ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteStream(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCreateStream = createStream(parcel.readInt() != 0 ? OutputConfiguration.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iCreateStream);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCreateInputStream = createInputStream(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCreateInputStream);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    Surface inputSurface = getInputSurface();
                    parcel2.writeNoException();
                    if (inputSurface != null) {
                        parcel2.writeInt(1);
                        inputSurface.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    CameraMetadataNative cameraMetadataNativeCreateDefaultRequest = createDefaultRequest(parcel.readInt());
                    parcel2.writeNoException();
                    if (cameraMetadataNativeCreateDefaultRequest != null) {
                        parcel2.writeInt(1);
                        cameraMetadataNativeCreateDefaultRequest.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    CameraMetadataNative cameraInfo = getCameraInfo();
                    parcel2.writeNoException();
                    if (cameraInfo != null) {
                        parcel2.writeInt(1);
                        cameraInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    waitUntilIdle();
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    long jFlush = flush();
                    parcel2.writeNoException();
                    parcel2.writeLong(jFlush);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    prepare(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    tearDown(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    prepare2(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateOutputConfiguration(parcel.readInt(), parcel.readInt() != 0 ? OutputConfiguration.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    finalizeOutputConfigurations(parcel.readInt(), parcel.readInt() != 0 ? OutputConfiguration.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCameraAudioRestriction(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    int globalAudioRestriction = getGlobalAudioRestriction();
                    parcel2.writeNoException();
                    parcel2.writeInt(globalAudioRestriction);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    ICameraOfflineSession iCameraOfflineSessionSwitchToOffline = switchToOffline(ICameraDeviceCallbacks.Stub.asInterface(parcel.readStrongBinder()), parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iCameraOfflineSessionSwitchToOffline != null ? iCameraOfflineSessionSwitchToOffline.asBinder() : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ICameraDeviceUser {
            public static ICameraDeviceUser sDefaultImpl;
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

            @Override // android.hardware.camera2.ICameraDeviceUser
            public void disconnect() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disconnect();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public SubmitInfo submitRequest(CaptureRequest captureRequest, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (captureRequest != null) {
                        parcelObtain.writeInt(1);
                        captureRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().submitRequest(captureRequest, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SubmitInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public SubmitInfo submitRequestList(CaptureRequest[] captureRequestArr, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(captureRequestArr, 0);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().submitRequestList(captureRequestArr, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SubmitInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public long cancelRequest(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelRequest(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public void beginConfigure() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().beginConfigure();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public int[] endConfigure(int i, CameraMetadataNative cameraMetadataNative) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (cameraMetadataNative != null) {
                        parcelObtain.writeInt(1);
                        cameraMetadataNative.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().endConfigure(i, cameraMetadataNative);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createIntArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public boolean isSessionConfigurationSupported(SessionConfiguration sessionConfiguration) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (sessionConfiguration != null) {
                        parcelObtain.writeInt(1);
                        sessionConfiguration.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSessionConfigurationSupported(sessionConfiguration);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public void deleteStream(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteStream(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public int createStream(OutputConfiguration outputConfiguration) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (outputConfiguration != null) {
                        parcelObtain.writeInt(1);
                        outputConfiguration.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createStream(outputConfiguration);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public int createInputStream(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createInputStream(i, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public Surface getInputSurface() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInputSurface();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Surface.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public CameraMetadataNative createDefaultRequest(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createDefaultRequest(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? CameraMetadataNative.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public CameraMetadataNative getCameraInfo() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCameraInfo();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? CameraMetadataNative.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public void waitUntilIdle() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().waitUntilIdle();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public long flush() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().flush();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public void prepare(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().prepare(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public void tearDown(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tearDown(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public void prepare2(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().prepare2(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public void updateOutputConfiguration(int i, OutputConfiguration outputConfiguration) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (outputConfiguration != null) {
                        parcelObtain.writeInt(1);
                        outputConfiguration.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateOutputConfiguration(i, outputConfiguration);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public void finalizeOutputConfigurations(int i, OutputConfiguration outputConfiguration) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (outputConfiguration != null) {
                        parcelObtain.writeInt(1);
                        outputConfiguration.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finalizeOutputConfigurations(i, outputConfiguration);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public void setCameraAudioRestriction(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCameraAudioRestriction(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public int getGlobalAudioRestriction() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGlobalAudioRestriction();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.camera2.ICameraDeviceUser
            public ICameraOfflineSession switchToOffline(ICameraDeviceCallbacks iCameraDeviceCallbacks, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iCameraDeviceCallbacks != null ? iCameraDeviceCallbacks.asBinder() : null);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().switchToOffline(iCameraDeviceCallbacks, iArr);
                    }
                    parcelObtain2.readException();
                    return ICameraOfflineSession.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICameraDeviceUser iCameraDeviceUser) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCameraDeviceUser == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCameraDeviceUser;
            return true;
        }

        public static ICameraDeviceUser getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
