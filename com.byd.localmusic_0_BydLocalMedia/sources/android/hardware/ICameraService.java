package android.hardware;

import android.hardware.ICamera;
import android.hardware.ICameraClient;
import android.hardware.ICameraServiceListener;
import android.hardware.camera2.ICameraDeviceCallbacks;
import android.hardware.camera2.ICameraDeviceUser;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.params.VendorTagDescriptor;
import android.hardware.camera2.params.VendorTagDescriptorCache;
import android.hardware.camera2.utils.CameraIdAndSessionConfiguration;
import android.hardware.camera2.utils.ConcurrentCameraIdCombination;
import android.media.MediaMetrics;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ICameraService extends IInterface {
    public static final int API_VERSION_1 = 1;
    public static final int API_VERSION_2 = 2;
    public static final int CAMERA_HAL_API_VERSION_UNSPECIFIED = -1;
    public static final int CAMERA_TYPE_ALL = 1;
    public static final int CAMERA_TYPE_BACKWARD_COMPATIBLE = 0;
    public static final int DEVICE_STATE_BACK_COVERED = 1;
    public static final int DEVICE_STATE_FOLDED = 4;
    public static final int DEVICE_STATE_FRONT_COVERED = 2;
    public static final int DEVICE_STATE_LAST_FRAMEWORK_BIT = Integer.MIN_VALUE;
    public static final int DEVICE_STATE_NORMAL = 0;
    public static final int ERROR_ALREADY_EXISTS = 2;
    public static final int ERROR_CAMERA_IN_USE = 7;
    public static final int ERROR_DEPRECATED_HAL = 9;
    public static final int ERROR_DISABLED = 6;
    public static final int ERROR_DISCONNECTED = 4;
    public static final int ERROR_ILLEGAL_ARGUMENT = 3;
    public static final int ERROR_INVALID_OPERATION = 10;
    public static final int ERROR_MAX_CAMERAS_IN_USE = 8;
    public static final int ERROR_PERMISSION_DENIED = 1;
    public static final int ERROR_TIMED_OUT = 5;
    public static final int EVENT_NONE = 0;
    public static final int EVENT_USER_SWITCHED = 1;
    public static final int USE_CALLING_PID = -1;
    public static final int USE_CALLING_UID = -1;

    public static class Default implements ICameraService {
        @Override // android.hardware.ICameraService
        public CameraStatus[] addListener(ICameraServiceListener iCameraServiceListener) throws RemoteException {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.ICameraService
        public ICamera connect(ICameraClient iCameraClient, int i, String str, int i2, int i3) throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public ICameraDeviceUser connectDevice(ICameraDeviceCallbacks iCameraDeviceCallbacks, String str, String str2, String str3, int i) throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public ICamera connectLegacy(ICameraClient iCameraClient, int i, int i2, String str, int i3) throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public CameraMetadataNative getCameraCharacteristics(String str) throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public CameraInfo getCameraInfo(int i) throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public VendorTagDescriptorCache getCameraVendorTagCache() throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public VendorTagDescriptor getCameraVendorTagDescriptor() throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public ConcurrentCameraIdCombination[] getConcurrentCameraIds() throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public String getLegacyParameters(int i) throws RemoteException {
            return null;
        }

        @Override // android.hardware.ICameraService
        public int getNumberOfCameras(int i) throws RemoteException {
            return 0;
        }

        @Override // android.hardware.ICameraService
        public boolean isConcurrentSessionConfigurationSupported(CameraIdAndSessionConfiguration[] cameraIdAndSessionConfigurationArr) throws RemoteException {
            return false;
        }

        @Override // android.hardware.ICameraService
        public boolean isHiddenPhysicalCamera(String str) throws RemoteException {
            return false;
        }

        @Override // android.hardware.ICameraService
        public void notifyDeviceStateChange(long j) throws RemoteException {
        }

        @Override // android.hardware.ICameraService
        public void notifySystemEvent(int i, int[] iArr) throws RemoteException {
        }

        @Override // android.hardware.ICameraService
        public void removeListener(ICameraServiceListener iCameraServiceListener) throws RemoteException {
        }

        @Override // android.hardware.ICameraService
        public void setTorchMode(String str, boolean z, IBinder iBinder) throws RemoteException {
        }

        @Override // android.hardware.ICameraService
        public boolean supportsCameraApi(String str, int i) throws RemoteException {
            return false;
        }
    }

    CameraStatus[] addListener(ICameraServiceListener iCameraServiceListener) throws RemoteException;

    ICamera connect(ICameraClient iCameraClient, int i, String str, int i2, int i3) throws RemoteException;

    ICameraDeviceUser connectDevice(ICameraDeviceCallbacks iCameraDeviceCallbacks, String str, String str2, String str3, int i) throws RemoteException;

    ICamera connectLegacy(ICameraClient iCameraClient, int i, int i2, String str, int i3) throws RemoteException;

    CameraMetadataNative getCameraCharacteristics(String str) throws RemoteException;

    CameraInfo getCameraInfo(int i) throws RemoteException;

    VendorTagDescriptorCache getCameraVendorTagCache() throws RemoteException;

    VendorTagDescriptor getCameraVendorTagDescriptor() throws RemoteException;

    ConcurrentCameraIdCombination[] getConcurrentCameraIds() throws RemoteException;

    String getLegacyParameters(int i) throws RemoteException;

    int getNumberOfCameras(int i) throws RemoteException;

    boolean isConcurrentSessionConfigurationSupported(CameraIdAndSessionConfiguration[] cameraIdAndSessionConfigurationArr) throws RemoteException;

    boolean isHiddenPhysicalCamera(String str) throws RemoteException;

    void notifyDeviceStateChange(long j) throws RemoteException;

    void notifySystemEvent(int i, int[] iArr) throws RemoteException;

    void removeListener(ICameraServiceListener iCameraServiceListener) throws RemoteException;

    void setTorchMode(String str, boolean z, IBinder iBinder) throws RemoteException;

    boolean supportsCameraApi(String str, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ICameraService {
        private static final String DESCRIPTOR = "android.hardware.ICameraService";
        static final int TRANSACTION_addListener = 6;
        static final int TRANSACTION_connect = 3;
        static final int TRANSACTION_connectDevice = 4;
        static final int TRANSACTION_connectLegacy = 5;
        static final int TRANSACTION_getCameraCharacteristics = 10;
        static final int TRANSACTION_getCameraInfo = 2;
        static final int TRANSACTION_getCameraVendorTagCache = 12;
        static final int TRANSACTION_getCameraVendorTagDescriptor = 11;
        static final int TRANSACTION_getConcurrentCameraIds = 7;
        static final int TRANSACTION_getLegacyParameters = 13;
        static final int TRANSACTION_getNumberOfCameras = 1;
        static final int TRANSACTION_isConcurrentSessionConfigurationSupported = 8;
        static final int TRANSACTION_isHiddenPhysicalCamera = 15;
        static final int TRANSACTION_notifyDeviceStateChange = 18;
        static final int TRANSACTION_notifySystemEvent = 17;
        static final int TRANSACTION_removeListener = 9;
        static final int TRANSACTION_setTorchMode = 16;
        static final int TRANSACTION_supportsCameraApi = 14;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getNumberOfCameras";
                case 2:
                    return "getCameraInfo";
                case 3:
                    return MediaMetrics.Value.CONNECT;
                case 4:
                    return "connectDevice";
                case 5:
                    return "connectLegacy";
                case 6:
                    return "addListener";
                case 7:
                    return "getConcurrentCameraIds";
                case 8:
                    return "isConcurrentSessionConfigurationSupported";
                case 9:
                    return "removeListener";
                case 10:
                    return "getCameraCharacteristics";
                case 11:
                    return "getCameraVendorTagDescriptor";
                case 12:
                    return "getCameraVendorTagCache";
                case 13:
                    return "getLegacyParameters";
                case 14:
                    return "supportsCameraApi";
                case 15:
                    return "isHiddenPhysicalCamera";
                case 16:
                    return "setTorchMode";
                case 17:
                    return "notifySystemEvent";
                case 18:
                    return "notifyDeviceStateChange";
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

        public static ICameraService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICameraService)) {
                return (ICameraService) iInterfaceQueryLocalInterface;
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
                    int numberOfCameras = getNumberOfCameras(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(numberOfCameras);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    CameraInfo cameraInfo = getCameraInfo(parcel.readInt());
                    parcel2.writeNoException();
                    if (cameraInfo != null) {
                        parcel2.writeInt(1);
                        cameraInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    ICamera iCameraConnect = connect(ICameraClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iCameraConnect != null ? iCameraConnect.asBinder() : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    ICameraDeviceUser iCameraDeviceUserConnectDevice = connectDevice(ICameraDeviceCallbacks.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iCameraDeviceUserConnectDevice != null ? iCameraDeviceUserConnectDevice.asBinder() : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    ICamera iCameraConnectLegacy = connectLegacy(ICameraClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iCameraConnectLegacy != null ? iCameraConnectLegacy.asBinder() : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    CameraStatus[] cameraStatusArrAddListener = addListener(ICameraServiceListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(cameraStatusArrAddListener, 1);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    ConcurrentCameraIdCombination[] concurrentCameraIds = getConcurrentCameraIds();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(concurrentCameraIds, 1);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsConcurrentSessionConfigurationSupported = isConcurrentSessionConfigurationSupported((CameraIdAndSessionConfiguration[]) parcel.createTypedArray(CameraIdAndSessionConfiguration.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsConcurrentSessionConfigurationSupported ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeListener(ICameraServiceListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    CameraMetadataNative cameraCharacteristics = getCameraCharacteristics(parcel.readString());
                    parcel2.writeNoException();
                    if (cameraCharacteristics != null) {
                        parcel2.writeInt(1);
                        cameraCharacteristics.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    VendorTagDescriptor cameraVendorTagDescriptor = getCameraVendorTagDescriptor();
                    parcel2.writeNoException();
                    if (cameraVendorTagDescriptor != null) {
                        parcel2.writeInt(1);
                        cameraVendorTagDescriptor.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    VendorTagDescriptorCache cameraVendorTagCache = getCameraVendorTagCache();
                    parcel2.writeNoException();
                    if (cameraVendorTagCache != null) {
                        parcel2.writeInt(1);
                        cameraVendorTagCache.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    String legacyParameters = getLegacyParameters(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(legacyParameters);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSupportsCameraApi = supportsCameraApi(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zSupportsCameraApi ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsHiddenPhysicalCamera = isHiddenPhysicalCamera(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsHiddenPhysicalCamera ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    setTorchMode(parcel.readString(), parcel.readInt() != 0, parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifySystemEvent(parcel.readInt(), parcel.createIntArray());
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyDeviceStateChange(parcel.readLong());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ICameraService {
            public static ICameraService sDefaultImpl;
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

            @Override // android.hardware.ICameraService
            public int getNumberOfCameras(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNumberOfCameras(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public CameraInfo getCameraInfo(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCameraInfo(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? CameraInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public ICamera connect(ICameraClient iCameraClient, int i, String str, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iCameraClient != null ? iCameraClient.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connect(iCameraClient, i, str, i2, i3);
                    }
                    parcelObtain2.readException();
                    return ICamera.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public ICameraDeviceUser connectDevice(ICameraDeviceCallbacks iCameraDeviceCallbacks, String str, String str2, String str3, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iCameraDeviceCallbacks != null ? iCameraDeviceCallbacks.asBinder() : null);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connectDevice(iCameraDeviceCallbacks, str, str2, str3, i);
                    }
                    parcelObtain2.readException();
                    return ICameraDeviceUser.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public ICamera connectLegacy(ICameraClient iCameraClient, int i, int i2, String str, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iCameraClient != null ? iCameraClient.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connectLegacy(iCameraClient, i, i2, str, i3);
                    }
                    parcelObtain2.readException();
                    return ICamera.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public CameraStatus[] addListener(ICameraServiceListener iCameraServiceListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iCameraServiceListener != null ? iCameraServiceListener.asBinder() : null);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addListener(iCameraServiceListener);
                    }
                    parcelObtain2.readException();
                    return (CameraStatus[]) parcelObtain2.createTypedArray(CameraStatus.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public ConcurrentCameraIdCombination[] getConcurrentCameraIds() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConcurrentCameraIds();
                    }
                    parcelObtain2.readException();
                    return (ConcurrentCameraIdCombination[]) parcelObtain2.createTypedArray(ConcurrentCameraIdCombination.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public boolean isConcurrentSessionConfigurationSupported(CameraIdAndSessionConfiguration[] cameraIdAndSessionConfigurationArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(cameraIdAndSessionConfigurationArr, 0);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isConcurrentSessionConfigurationSupported(cameraIdAndSessionConfigurationArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public void removeListener(ICameraServiceListener iCameraServiceListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iCameraServiceListener != null ? iCameraServiceListener.asBinder() : null);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeListener(iCameraServiceListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public CameraMetadataNative getCameraCharacteristics(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCameraCharacteristics(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? CameraMetadataNative.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public VendorTagDescriptor getCameraVendorTagDescriptor() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCameraVendorTagDescriptor();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? VendorTagDescriptor.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public VendorTagDescriptorCache getCameraVendorTagCache() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCameraVendorTagCache();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? VendorTagDescriptorCache.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public String getLegacyParameters(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLegacyParameters(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public boolean supportsCameraApi(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().supportsCameraApi(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public boolean isHiddenPhysicalCamera(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isHiddenPhysicalCamera(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public void setTorchMode(String str, boolean z, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTorchMode(str, z, iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public void notifySystemEvent(int i, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeIntArray(iArr);
                    if (this.mRemote.transact(17, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifySystemEvent(i, iArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.ICameraService
            public void notifyDeviceStateChange(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (this.mRemote.transact(18, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyDeviceStateChange(j);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICameraService iCameraService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCameraService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCameraService;
            return true;
        }

        public static ICameraService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
