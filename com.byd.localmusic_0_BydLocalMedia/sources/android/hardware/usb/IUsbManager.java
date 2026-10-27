package android.hardware.usb;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IUsbManager extends IInterface {

    public static class Default implements IUsbManager {
        @Override // android.hardware.usb.IUsbManager
        public void addAccessoryPackagesToPreferenceDenied(UsbAccessory usbAccessory, String[] strArr, UserHandle userHandle) throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public void addDevicePackagesToPreferenceDenied(UsbDevice usbDevice, String[] strArr, UserHandle userHandle) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.hardware.usb.IUsbManager
        public void clearDefaults(String str, int i) throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public void enableContaminantDetection(String str, boolean z) throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public ParcelFileDescriptor getControlFd(long j) throws RemoteException {
            return null;
        }

        @Override // android.hardware.usb.IUsbManager
        public UsbAccessory getCurrentAccessory() throws RemoteException {
            return null;
        }

        @Override // android.hardware.usb.IUsbManager
        public long getCurrentFunctions() throws RemoteException {
            return 0L;
        }

        @Override // android.hardware.usb.IUsbManager
        public void getDeviceList(Bundle bundle) throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public UsbPortStatus getPortStatus(String str) throws RemoteException {
            return null;
        }

        @Override // android.hardware.usb.IUsbManager
        public List<ParcelableUsbPort> getPorts() throws RemoteException {
            return null;
        }

        @Override // android.hardware.usb.IUsbManager
        public long getScreenUnlockedFunctions() throws RemoteException {
            return 0L;
        }

        @Override // android.hardware.usb.IUsbManager
        public void grantAccessoryPermission(UsbAccessory usbAccessory, int i) throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public void grantDevicePermission(UsbDevice usbDevice, int i) throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public boolean hasAccessoryPermission(UsbAccessory usbAccessory) throws RemoteException {
            return false;
        }

        @Override // android.hardware.usb.IUsbManager
        public boolean hasDefaults(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.hardware.usb.IUsbManager
        public boolean hasDevicePermission(UsbDevice usbDevice, String str) throws RemoteException {
            return false;
        }

        @Override // android.hardware.usb.IUsbManager
        public boolean isFunctionEnabled(String str) throws RemoteException {
            return false;
        }

        @Override // android.hardware.usb.IUsbManager
        public ParcelFileDescriptor openAccessory(UsbAccessory usbAccessory) throws RemoteException {
            return null;
        }

        @Override // android.hardware.usb.IUsbManager
        public ParcelFileDescriptor openDevice(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // android.hardware.usb.IUsbManager
        public void removeAccessoryPackagesFromPreferenceDenied(UsbAccessory usbAccessory, String[] strArr, UserHandle userHandle) throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public void removeDevicePackagesFromPreferenceDenied(UsbDevice usbDevice, String[] strArr, UserHandle userHandle) throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public void requestAccessoryPermission(UsbAccessory usbAccessory, String str, PendingIntent pendingIntent) throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public void requestDevicePermission(UsbDevice usbDevice, String str, PendingIntent pendingIntent) throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public void resetUsbGadget() throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public void setAccessoryPackage(UsbAccessory usbAccessory, String str, int i) throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public void setAccessoryPersistentPermission(UsbAccessory usbAccessory, int i, UserHandle userHandle, boolean z) throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public void setCurrentFunction(String str, boolean z) throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public void setCurrentFunctions(long j) throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public void setDevicePackage(UsbDevice usbDevice, String str, int i) throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public void setDevicePersistentPermission(UsbDevice usbDevice, int i, UserHandle userHandle, boolean z) throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public void setPortRoles(String str, int i, int i2) throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public void setScreenUnlockedFunctions(long j) throws RemoteException {
        }

        @Override // android.hardware.usb.IUsbManager
        public void setUsbDeviceConnectionHandler(ComponentName componentName) throws RemoteException {
        }
    }

    void addAccessoryPackagesToPreferenceDenied(UsbAccessory usbAccessory, String[] strArr, UserHandle userHandle) throws RemoteException;

    void addDevicePackagesToPreferenceDenied(UsbDevice usbDevice, String[] strArr, UserHandle userHandle) throws RemoteException;

    void clearDefaults(String str, int i) throws RemoteException;

    void enableContaminantDetection(String str, boolean z) throws RemoteException;

    ParcelFileDescriptor getControlFd(long j) throws RemoteException;

    UsbAccessory getCurrentAccessory() throws RemoteException;

    long getCurrentFunctions() throws RemoteException;

    void getDeviceList(Bundle bundle) throws RemoteException;

    UsbPortStatus getPortStatus(String str) throws RemoteException;

    List<ParcelableUsbPort> getPorts() throws RemoteException;

    long getScreenUnlockedFunctions() throws RemoteException;

    void grantAccessoryPermission(UsbAccessory usbAccessory, int i) throws RemoteException;

    void grantDevicePermission(UsbDevice usbDevice, int i) throws RemoteException;

    boolean hasAccessoryPermission(UsbAccessory usbAccessory) throws RemoteException;

    boolean hasDefaults(String str, int i) throws RemoteException;

    boolean hasDevicePermission(UsbDevice usbDevice, String str) throws RemoteException;

    boolean isFunctionEnabled(String str) throws RemoteException;

    ParcelFileDescriptor openAccessory(UsbAccessory usbAccessory) throws RemoteException;

    ParcelFileDescriptor openDevice(String str, String str2) throws RemoteException;

    void removeAccessoryPackagesFromPreferenceDenied(UsbAccessory usbAccessory, String[] strArr, UserHandle userHandle) throws RemoteException;

    void removeDevicePackagesFromPreferenceDenied(UsbDevice usbDevice, String[] strArr, UserHandle userHandle) throws RemoteException;

    void requestAccessoryPermission(UsbAccessory usbAccessory, String str, PendingIntent pendingIntent) throws RemoteException;

    void requestDevicePermission(UsbDevice usbDevice, String str, PendingIntent pendingIntent) throws RemoteException;

    void resetUsbGadget() throws RemoteException;

    void setAccessoryPackage(UsbAccessory usbAccessory, String str, int i) throws RemoteException;

    void setAccessoryPersistentPermission(UsbAccessory usbAccessory, int i, UserHandle userHandle, boolean z) throws RemoteException;

    void setCurrentFunction(String str, boolean z) throws RemoteException;

    void setCurrentFunctions(long j) throws RemoteException;

    void setDevicePackage(UsbDevice usbDevice, String str, int i) throws RemoteException;

    void setDevicePersistentPermission(UsbDevice usbDevice, int i, UserHandle userHandle, boolean z) throws RemoteException;

    void setPortRoles(String str, int i, int i2) throws RemoteException;

    void setScreenUnlockedFunctions(long j) throws RemoteException;

    void setUsbDeviceConnectionHandler(ComponentName componentName) throws RemoteException;

    public static abstract class Stub extends Binder implements IUsbManager {
        private static final String DESCRIPTOR = "android.hardware.usb.IUsbManager";
        static final int TRANSACTION_addAccessoryPackagesToPreferenceDenied = 8;
        static final int TRANSACTION_addDevicePackagesToPreferenceDenied = 7;
        static final int TRANSACTION_clearDefaults = 20;
        static final int TRANSACTION_enableContaminantDetection = 32;
        static final int TRANSACTION_getControlFd = 28;
        static final int TRANSACTION_getCurrentAccessory = 3;
        static final int TRANSACTION_getCurrentFunctions = 24;
        static final int TRANSACTION_getDeviceList = 1;
        static final int TRANSACTION_getPortStatus = 30;
        static final int TRANSACTION_getPorts = 29;
        static final int TRANSACTION_getScreenUnlockedFunctions = 26;
        static final int TRANSACTION_grantAccessoryPermission = 18;
        static final int TRANSACTION_grantDevicePermission = 17;
        static final int TRANSACTION_hasAccessoryPermission = 14;
        static final int TRANSACTION_hasDefaults = 19;
        static final int TRANSACTION_hasDevicePermission = 13;
        static final int TRANSACTION_isFunctionEnabled = 21;
        static final int TRANSACTION_openAccessory = 4;
        static final int TRANSACTION_openDevice = 2;
        static final int TRANSACTION_removeAccessoryPackagesFromPreferenceDenied = 10;
        static final int TRANSACTION_removeDevicePackagesFromPreferenceDenied = 9;
        static final int TRANSACTION_requestAccessoryPermission = 16;
        static final int TRANSACTION_requestDevicePermission = 15;
        static final int TRANSACTION_resetUsbGadget = 27;
        static final int TRANSACTION_setAccessoryPackage = 6;
        static final int TRANSACTION_setAccessoryPersistentPermission = 12;
        static final int TRANSACTION_setCurrentFunction = 23;
        static final int TRANSACTION_setCurrentFunctions = 22;
        static final int TRANSACTION_setDevicePackage = 5;
        static final int TRANSACTION_setDevicePersistentPermission = 11;
        static final int TRANSACTION_setPortRoles = 31;
        static final int TRANSACTION_setScreenUnlockedFunctions = 25;
        static final int TRANSACTION_setUsbDeviceConnectionHandler = 33;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getDeviceList";
                case 2:
                    return "openDevice";
                case 3:
                    return "getCurrentAccessory";
                case 4:
                    return "openAccessory";
                case 5:
                    return "setDevicePackage";
                case 6:
                    return "setAccessoryPackage";
                case 7:
                    return "addDevicePackagesToPreferenceDenied";
                case 8:
                    return "addAccessoryPackagesToPreferenceDenied";
                case 9:
                    return "removeDevicePackagesFromPreferenceDenied";
                case 10:
                    return "removeAccessoryPackagesFromPreferenceDenied";
                case 11:
                    return "setDevicePersistentPermission";
                case 12:
                    return "setAccessoryPersistentPermission";
                case 13:
                    return "hasDevicePermission";
                case 14:
                    return "hasAccessoryPermission";
                case 15:
                    return "requestDevicePermission";
                case 16:
                    return "requestAccessoryPermission";
                case 17:
                    return "grantDevicePermission";
                case 18:
                    return "grantAccessoryPermission";
                case 19:
                    return "hasDefaults";
                case 20:
                    return "clearDefaults";
                case 21:
                    return "isFunctionEnabled";
                case 22:
                    return "setCurrentFunctions";
                case 23:
                    return "setCurrentFunction";
                case 24:
                    return "getCurrentFunctions";
                case 25:
                    return "setScreenUnlockedFunctions";
                case 26:
                    return "getScreenUnlockedFunctions";
                case 27:
                    return "resetUsbGadget";
                case 28:
                    return "getControlFd";
                case 29:
                    return "getPorts";
                case 30:
                    return "getPortStatus";
                case 31:
                    return "setPortRoles";
                case 32:
                    return "enableContaminantDetection";
                case 33:
                    return "setUsbDeviceConnectionHandler";
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

        public static IUsbManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IUsbManager)) {
                return (IUsbManager) iInterfaceQueryLocalInterface;
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
                    Bundle bundle = new Bundle();
                    getDeviceList(bundle);
                    parcel2.writeNoException();
                    parcel2.writeInt(1);
                    bundle.writeToParcel(parcel2, 1);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParcelFileDescriptor parcelFileDescriptorOpenDevice = openDevice(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (parcelFileDescriptorOpenDevice != null) {
                        parcel2.writeInt(1);
                        parcelFileDescriptorOpenDevice.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    UsbAccessory currentAccessory = getCurrentAccessory();
                    parcel2.writeNoException();
                    if (currentAccessory != null) {
                        parcel2.writeInt(1);
                        currentAccessory.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParcelFileDescriptor parcelFileDescriptorOpenAccessory = openAccessory(parcel.readInt() != 0 ? UsbAccessory.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (parcelFileDescriptorOpenAccessory != null) {
                        parcel2.writeInt(1);
                        parcelFileDescriptorOpenAccessory.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDevicePackage(parcel.readInt() != 0 ? UsbDevice.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAccessoryPackage(parcel.readInt() != 0 ? UsbAccessory.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    addDevicePackagesToPreferenceDenied(parcel.readInt() != 0 ? UsbDevice.CREATOR.createFromParcel(parcel) : null, parcel.createStringArray(), parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    addAccessoryPackagesToPreferenceDenied(parcel.readInt() != 0 ? UsbAccessory.CREATOR.createFromParcel(parcel) : null, parcel.createStringArray(), parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeDevicePackagesFromPreferenceDenied(parcel.readInt() != 0 ? UsbDevice.CREATOR.createFromParcel(parcel) : null, parcel.createStringArray(), parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeAccessoryPackagesFromPreferenceDenied(parcel.readInt() != 0 ? UsbAccessory.CREATOR.createFromParcel(parcel) : null, parcel.createStringArray(), parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDevicePersistentPermission(parcel.readInt() != 0 ? UsbDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAccessoryPersistentPermission(parcel.readInt() != 0 ? UsbAccessory.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasDevicePermission = hasDevicePermission(parcel.readInt() != 0 ? UsbDevice.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasDevicePermission ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasAccessoryPermission = hasAccessoryPermission(parcel.readInt() != 0 ? UsbAccessory.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasAccessoryPermission ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestDevicePermission(parcel.readInt() != 0 ? UsbDevice.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestAccessoryPermission(parcel.readInt() != 0 ? UsbAccessory.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    grantDevicePermission(parcel.readInt() != 0 ? UsbDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    grantAccessoryPermission(parcel.readInt() != 0 ? UsbAccessory.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasDefaults = hasDefaults(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasDefaults ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearDefaults(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsFunctionEnabled = isFunctionEnabled(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsFunctionEnabled ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCurrentFunctions(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCurrentFunction(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    long currentFunctions = getCurrentFunctions();
                    parcel2.writeNoException();
                    parcel2.writeLong(currentFunctions);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    setScreenUnlockedFunctions(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    long screenUnlockedFunctions = getScreenUnlockedFunctions();
                    parcel2.writeNoException();
                    parcel2.writeLong(screenUnlockedFunctions);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetUsbGadget();
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParcelFileDescriptor controlFd = getControlFd(parcel.readLong());
                    parcel2.writeNoException();
                    if (controlFd != null) {
                        parcel2.writeInt(1);
                        controlFd.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<ParcelableUsbPort> ports = getPorts();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(ports);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    UsbPortStatus portStatus = getPortStatus(parcel.readString());
                    parcel2.writeNoException();
                    if (portStatus != null) {
                        parcel2.writeInt(1);
                        portStatus.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPortRoles(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableContaminantDetection(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUsbDeviceConnectionHandler(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IUsbManager {
            public static IUsbManager sDefaultImpl;
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

            @Override // android.hardware.usb.IUsbManager
            public void getDeviceList(Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getDeviceList(bundle);
                        return;
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        bundle.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public ParcelFileDescriptor openDevice(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openDevice(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public UsbAccessory getCurrentAccessory() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentAccessory();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? UsbAccessory.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public ParcelFileDescriptor openAccessory(UsbAccessory usbAccessory) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (usbAccessory != null) {
                        parcelObtain.writeInt(1);
                        usbAccessory.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openAccessory(usbAccessory);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void setDevicePackage(UsbDevice usbDevice, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (usbDevice != null) {
                        parcelObtain.writeInt(1);
                        usbDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDevicePackage(usbDevice, str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void setAccessoryPackage(UsbAccessory usbAccessory, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (usbAccessory != null) {
                        parcelObtain.writeInt(1);
                        usbAccessory.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAccessoryPackage(usbAccessory, str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void addDevicePackagesToPreferenceDenied(UsbDevice usbDevice, String[] strArr, UserHandle userHandle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (usbDevice != null) {
                        parcelObtain.writeInt(1);
                        usbDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStringArray(strArr);
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addDevicePackagesToPreferenceDenied(usbDevice, strArr, userHandle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void addAccessoryPackagesToPreferenceDenied(UsbAccessory usbAccessory, String[] strArr, UserHandle userHandle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (usbAccessory != null) {
                        parcelObtain.writeInt(1);
                        usbAccessory.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStringArray(strArr);
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addAccessoryPackagesToPreferenceDenied(usbAccessory, strArr, userHandle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void removeDevicePackagesFromPreferenceDenied(UsbDevice usbDevice, String[] strArr, UserHandle userHandle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (usbDevice != null) {
                        parcelObtain.writeInt(1);
                        usbDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStringArray(strArr);
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeDevicePackagesFromPreferenceDenied(usbDevice, strArr, userHandle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void removeAccessoryPackagesFromPreferenceDenied(UsbAccessory usbAccessory, String[] strArr, UserHandle userHandle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (usbAccessory != null) {
                        parcelObtain.writeInt(1);
                        usbAccessory.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStringArray(strArr);
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeAccessoryPackagesFromPreferenceDenied(usbAccessory, strArr, userHandle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void setDevicePersistentPermission(UsbDevice usbDevice, int i, UserHandle userHandle, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i2 = 1;
                    if (usbDevice != null) {
                        parcelObtain.writeInt(1);
                        usbDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!z) {
                        i2 = 0;
                    }
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDevicePersistentPermission(usbDevice, i, userHandle, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void setAccessoryPersistentPermission(UsbAccessory usbAccessory, int i, UserHandle userHandle, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i2 = 1;
                    if (usbAccessory != null) {
                        parcelObtain.writeInt(1);
                        usbAccessory.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!z) {
                        i2 = 0;
                    }
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAccessoryPersistentPermission(usbAccessory, i, userHandle, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public boolean hasDevicePermission(UsbDevice usbDevice, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (usbDevice != null) {
                        parcelObtain.writeInt(1);
                        usbDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasDevicePermission(usbDevice, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public boolean hasAccessoryPermission(UsbAccessory usbAccessory) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (usbAccessory != null) {
                        parcelObtain.writeInt(1);
                        usbAccessory.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasAccessoryPermission(usbAccessory);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void requestDevicePermission(UsbDevice usbDevice, String str, PendingIntent pendingIntent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (usbDevice != null) {
                        parcelObtain.writeInt(1);
                        usbDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestDevicePermission(usbDevice, str, pendingIntent);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void requestAccessoryPermission(UsbAccessory usbAccessory, String str, PendingIntent pendingIntent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (usbAccessory != null) {
                        parcelObtain.writeInt(1);
                        usbAccessory.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestAccessoryPermission(usbAccessory, str, pendingIntent);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void grantDevicePermission(UsbDevice usbDevice, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (usbDevice != null) {
                        parcelObtain.writeInt(1);
                        usbDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().grantDevicePermission(usbDevice, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void grantAccessoryPermission(UsbAccessory usbAccessory, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (usbAccessory != null) {
                        parcelObtain.writeInt(1);
                        usbAccessory.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().grantAccessoryPermission(usbAccessory, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public boolean hasDefaults(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasDefaults(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void clearDefaults(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearDefaults(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public boolean isFunctionEnabled(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isFunctionEnabled(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void setCurrentFunctions(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCurrentFunctions(j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void setCurrentFunction(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCurrentFunction(str, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public long getCurrentFunctions() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentFunctions();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void setScreenUnlockedFunctions(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setScreenUnlockedFunctions(j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public long getScreenUnlockedFunctions() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getScreenUnlockedFunctions();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void resetUsbGadget() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetUsbGadget();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public ParcelFileDescriptor getControlFd(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getControlFd(j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public List<ParcelableUsbPort> getPorts() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPorts();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(ParcelableUsbPort.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public UsbPortStatus getPortStatus(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPortStatus(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? UsbPortStatus.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void setPortRoles(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPortRoles(str, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void enableContaminantDetection(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableContaminantDetection(str, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.hardware.usb.IUsbManager
            public void setUsbDeviceConnectionHandler(ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUsbDeviceConnectionHandler(componentName);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUsbManager iUsbManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iUsbManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iUsbManager;
            return true;
        }

        public static IUsbManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
