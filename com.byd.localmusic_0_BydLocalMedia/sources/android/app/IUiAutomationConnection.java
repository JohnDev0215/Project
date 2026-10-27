package android.app;

import android.accessibilityservice.IAccessibilityServiceClient;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaMetrics;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.view.InputEvent;
import android.view.WindowAnimationFrameStats;
import android.view.WindowContentFrameStats;

/* JADX INFO: loaded from: classes.dex */
public interface IUiAutomationConnection extends IInterface {

    public static class Default implements IUiAutomationConnection {
        @Override // android.app.IUiAutomationConnection
        public void adoptShellPermissionIdentity(int i, String[] strArr) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.IUiAutomationConnection
        public void clearWindowAnimationFrameStats() throws RemoteException {
        }

        @Override // android.app.IUiAutomationConnection
        public boolean clearWindowContentFrameStats(int i) throws RemoteException {
            return false;
        }

        @Override // android.app.IUiAutomationConnection
        public void connect(IAccessibilityServiceClient iAccessibilityServiceClient, int i) throws RemoteException {
        }

        @Override // android.app.IUiAutomationConnection
        public void disconnect() throws RemoteException {
        }

        @Override // android.app.IUiAutomationConnection
        public void dropShellPermissionIdentity() throws RemoteException {
        }

        @Override // android.app.IUiAutomationConnection
        public void executeShellCommand(String str, ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2) throws RemoteException {
        }

        @Override // android.app.IUiAutomationConnection
        public WindowAnimationFrameStats getWindowAnimationFrameStats() throws RemoteException {
            return null;
        }

        @Override // android.app.IUiAutomationConnection
        public WindowContentFrameStats getWindowContentFrameStats(int i) throws RemoteException {
            return null;
        }

        @Override // android.app.IUiAutomationConnection
        public void grantRuntimePermission(String str, String str2, int i) throws RemoteException {
        }

        @Override // android.app.IUiAutomationConnection
        public boolean injectInputEvent(InputEvent inputEvent, boolean z) throws RemoteException {
            return false;
        }

        @Override // android.app.IUiAutomationConnection
        public void revokeRuntimePermission(String str, String str2, int i) throws RemoteException {
        }

        @Override // android.app.IUiAutomationConnection
        public boolean setRotation(int i) throws RemoteException {
            return false;
        }

        @Override // android.app.IUiAutomationConnection
        public void shutdown() throws RemoteException {
        }

        @Override // android.app.IUiAutomationConnection
        public void syncInputTransactions() throws RemoteException {
        }

        @Override // android.app.IUiAutomationConnection
        public Bitmap takeScreenshot(Rect rect, int i) throws RemoteException {
            return null;
        }
    }

    void adoptShellPermissionIdentity(int i, String[] strArr) throws RemoteException;

    void clearWindowAnimationFrameStats() throws RemoteException;

    boolean clearWindowContentFrameStats(int i) throws RemoteException;

    void connect(IAccessibilityServiceClient iAccessibilityServiceClient, int i) throws RemoteException;

    void disconnect() throws RemoteException;

    void dropShellPermissionIdentity() throws RemoteException;

    void executeShellCommand(String str, ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2) throws RemoteException;

    WindowAnimationFrameStats getWindowAnimationFrameStats() throws RemoteException;

    WindowContentFrameStats getWindowContentFrameStats(int i) throws RemoteException;

    void grantRuntimePermission(String str, String str2, int i) throws RemoteException;

    boolean injectInputEvent(InputEvent inputEvent, boolean z) throws RemoteException;

    void revokeRuntimePermission(String str, String str2, int i) throws RemoteException;

    boolean setRotation(int i) throws RemoteException;

    void shutdown() throws RemoteException;

    void syncInputTransactions() throws RemoteException;

    Bitmap takeScreenshot(Rect rect, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IUiAutomationConnection {
        private static final String DESCRIPTOR = "android.app.IUiAutomationConnection";
        static final int TRANSACTION_adoptShellPermissionIdentity = 14;
        static final int TRANSACTION_clearWindowAnimationFrameStats = 9;
        static final int TRANSACTION_clearWindowContentFrameStats = 7;
        static final int TRANSACTION_connect = 1;
        static final int TRANSACTION_disconnect = 2;
        static final int TRANSACTION_dropShellPermissionIdentity = 15;
        static final int TRANSACTION_executeShellCommand = 11;
        static final int TRANSACTION_getWindowAnimationFrameStats = 10;
        static final int TRANSACTION_getWindowContentFrameStats = 8;
        static final int TRANSACTION_grantRuntimePermission = 12;
        static final int TRANSACTION_injectInputEvent = 3;
        static final int TRANSACTION_revokeRuntimePermission = 13;
        static final int TRANSACTION_setRotation = 5;
        static final int TRANSACTION_shutdown = 16;
        static final int TRANSACTION_syncInputTransactions = 4;
        static final int TRANSACTION_takeScreenshot = 6;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return MediaMetrics.Value.CONNECT;
                case 2:
                    return MediaMetrics.Value.DISCONNECT;
                case 3:
                    return "injectInputEvent";
                case 4:
                    return "syncInputTransactions";
                case 5:
                    return "setRotation";
                case 6:
                    return "takeScreenshot";
                case 7:
                    return "clearWindowContentFrameStats";
                case 8:
                    return "getWindowContentFrameStats";
                case 9:
                    return "clearWindowAnimationFrameStats";
                case 10:
                    return "getWindowAnimationFrameStats";
                case 11:
                    return "executeShellCommand";
                case 12:
                    return "grantRuntimePermission";
                case 13:
                    return "revokeRuntimePermission";
                case 14:
                    return "adoptShellPermissionIdentity";
                case 15:
                    return "dropShellPermissionIdentity";
                case 16:
                    return "shutdown";
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

        public static IUiAutomationConnection asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IUiAutomationConnection)) {
                return (IUiAutomationConnection) iInterfaceQueryLocalInterface;
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
                    connect(IAccessibilityServiceClient.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    disconnect();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zInjectInputEvent = injectInputEvent(parcel.readInt() != 0 ? InputEvent.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zInjectInputEvent ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    syncInputTransactions();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean rotation = setRotation(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(rotation ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bitmap bitmapTakeScreenshot = takeScreenshot(parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    if (bitmapTakeScreenshot != null) {
                        parcel2.writeInt(1);
                        bitmapTakeScreenshot.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zClearWindowContentFrameStats = clearWindowContentFrameStats(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zClearWindowContentFrameStats ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    WindowContentFrameStats windowContentFrameStats = getWindowContentFrameStats(parcel.readInt());
                    parcel2.writeNoException();
                    if (windowContentFrameStats != null) {
                        parcel2.writeInt(1);
                        windowContentFrameStats.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearWindowAnimationFrameStats();
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    WindowAnimationFrameStats windowAnimationFrameStats = getWindowAnimationFrameStats();
                    parcel2.writeNoException();
                    if (windowAnimationFrameStats != null) {
                        parcel2.writeInt(1);
                        windowAnimationFrameStats.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    executeShellCommand(parcel.readString(), parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    grantRuntimePermission(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    revokeRuntimePermission(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    adoptShellPermissionIdentity(parcel.readInt(), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    dropShellPermissionIdentity();
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    shutdown();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IUiAutomationConnection {
            public static IUiAutomationConnection sDefaultImpl;
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

            @Override // android.app.IUiAutomationConnection
            public void connect(IAccessibilityServiceClient iAccessibilityServiceClient, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAccessibilityServiceClient != null ? iAccessibilityServiceClient.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().connect(iAccessibilityServiceClient, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiAutomationConnection
            public void disconnect() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disconnect();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiAutomationConnection
            public boolean injectInputEvent(InputEvent inputEvent, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (inputEvent != null) {
                        parcelObtain.writeInt(1);
                        inputEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().injectInputEvent(inputEvent, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiAutomationConnection
            public void syncInputTransactions() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().syncInputTransactions();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiAutomationConnection
            public boolean setRotation(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRotation(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiAutomationConnection
            public Bitmap takeScreenshot(Rect rect, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().takeScreenshot(rect, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Bitmap.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiAutomationConnection
            public boolean clearWindowContentFrameStats(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearWindowContentFrameStats(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiAutomationConnection
            public WindowContentFrameStats getWindowContentFrameStats(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWindowContentFrameStats(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? WindowContentFrameStats.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiAutomationConnection
            public void clearWindowAnimationFrameStats() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearWindowAnimationFrameStats();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiAutomationConnection
            public WindowAnimationFrameStats getWindowAnimationFrameStats() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWindowAnimationFrameStats();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? WindowAnimationFrameStats.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiAutomationConnection
            public void executeShellCommand(String str, ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (parcelFileDescriptor2 != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().executeShellCommand(str, parcelFileDescriptor, parcelFileDescriptor2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiAutomationConnection
            public void grantRuntimePermission(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().grantRuntimePermission(str, str2, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiAutomationConnection
            public void revokeRuntimePermission(String str, String str2, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().revokeRuntimePermission(str, str2, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiAutomationConnection
            public void adoptShellPermissionIdentity(int i, String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().adoptShellPermissionIdentity(i, strArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiAutomationConnection
            public void dropShellPermissionIdentity() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dropShellPermissionIdentity();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUiAutomationConnection
            public void shutdown() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(16, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().shutdown();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUiAutomationConnection iUiAutomationConnection) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iUiAutomationConnection == null) {
                return false;
            }
            Proxy.sDefaultImpl = iUiAutomationConnection;
            return true;
        }

        public static IUiAutomationConnection getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
