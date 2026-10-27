package com.byd.systemsettings;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.systemsettings.ISystemSettingsCallback;

/* JADX INFO: loaded from: classes2.dex */
public interface ISystemSettings extends IInterface {

    public static class Default implements ISystemSettings {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.systemsettings.ISystemSettings
        public void dismissAllDialog() throws RemoteException {
        }

        @Override // com.byd.systemsettings.ISystemSettings
        public void dismissBtSettings() throws RemoteException {
        }

        @Override // com.byd.systemsettings.ISystemSettings
        public void dismissCpaaSettings() throws RemoteException {
        }

        @Override // com.byd.systemsettings.ISystemSettings
        public void dismissHotspotSettings() throws RemoteException {
        }

        @Override // com.byd.systemsettings.ISystemSettings
        public void dismissWifiSettings() throws RemoteException {
        }

        @Override // com.byd.systemsettings.ISystemSettings
        public void doAction(String action, String params) throws RemoteException {
        }

        @Override // com.byd.systemsettings.ISystemSettings
        public void finishScreensaver() throws RemoteException {
        }

        @Override // com.byd.systemsettings.ISystemSettings
        public boolean hasDialogShowing() throws RemoteException {
            return false;
        }

        @Override // com.byd.systemsettings.ISystemSettings
        public void registerSystemSettingsCallback(ISystemSettingsCallback cb) throws RemoteException {
        }

        @Override // com.byd.systemsettings.ISystemSettings
        public void showBtSettings() throws RemoteException {
        }

        @Override // com.byd.systemsettings.ISystemSettings
        public void showCpaaSettings() throws RemoteException {
        }

        @Override // com.byd.systemsettings.ISystemSettings
        public void showHotspotSettings() throws RemoteException {
        }

        @Override // com.byd.systemsettings.ISystemSettings
        public void showScreensaver() throws RemoteException {
        }

        @Override // com.byd.systemsettings.ISystemSettings
        public void showWifiSettings() throws RemoteException {
        }

        @Override // com.byd.systemsettings.ISystemSettings
        public void unregisterSystemSettingsCallback(ISystemSettingsCallback cb) throws RemoteException {
        }
    }

    void dismissAllDialog() throws RemoteException;

    void dismissBtSettings() throws RemoteException;

    void dismissCpaaSettings() throws RemoteException;

    void dismissHotspotSettings() throws RemoteException;

    void dismissWifiSettings() throws RemoteException;

    void doAction(String action, String params) throws RemoteException;

    void finishScreensaver() throws RemoteException;

    boolean hasDialogShowing() throws RemoteException;

    void registerSystemSettingsCallback(ISystemSettingsCallback cb) throws RemoteException;

    void showBtSettings() throws RemoteException;

    void showCpaaSettings() throws RemoteException;

    void showHotspotSettings() throws RemoteException;

    void showScreensaver() throws RemoteException;

    void showWifiSettings() throws RemoteException;

    void unregisterSystemSettingsCallback(ISystemSettingsCallback cb) throws RemoteException;

    public static abstract class Stub extends Binder implements ISystemSettings {
        private static final String DESCRIPTOR = "com.byd.systemsettings.ISystemSettings";
        static final int TRANSACTION_dismissAllDialog = 12;
        static final int TRANSACTION_dismissBtSettings = 8;
        static final int TRANSACTION_dismissCpaaSettings = 11;
        static final int TRANSACTION_dismissHotspotSettings = 10;
        static final int TRANSACTION_dismissWifiSettings = 9;
        static final int TRANSACTION_doAction = 1;
        static final int TRANSACTION_finishScreensaver = 7;
        static final int TRANSACTION_hasDialogShowing = 13;
        static final int TRANSACTION_registerSystemSettingsCallback = 14;
        static final int TRANSACTION_showBtSettings = 3;
        static final int TRANSACTION_showCpaaSettings = 6;
        static final int TRANSACTION_showHotspotSettings = 5;
        static final int TRANSACTION_showScreensaver = 2;
        static final int TRANSACTION_showWifiSettings = 4;
        static final int TRANSACTION_unregisterSystemSettingsCallback = 15;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISystemSettings asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISystemSettings)) {
                return (ISystemSettings) iInterfaceQueryLocalInterface;
            }
            return new Proxy(obj);
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
                    doAction(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    showScreensaver();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    showBtSettings();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    showWifiSettings();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    showHotspotSettings();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    showCpaaSettings();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    finishScreensaver();
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    dismissBtSettings();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    dismissWifiSettings();
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    dismissHotspotSettings();
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    dismissCpaaSettings();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    dismissAllDialog();
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasDialogShowing = hasDialogShowing();
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasDialogShowing ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerSystemSettingsCallback(ISystemSettingsCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterSystemSettingsCallback(ISystemSettingsCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ISystemSettings {
            public static ISystemSettings sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void doAction(String action, String params) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(action);
                    parcelObtain.writeString(params);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().doAction(action, params);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void showScreensaver() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showScreensaver();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void showBtSettings() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showBtSettings();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void showWifiSettings() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showWifiSettings();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void showHotspotSettings() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showHotspotSettings();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void showCpaaSettings() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showCpaaSettings();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void finishScreensaver() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().finishScreensaver();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void dismissBtSettings() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dismissBtSettings();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void dismissWifiSettings() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dismissWifiSettings();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void dismissHotspotSettings() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dismissHotspotSettings();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void dismissCpaaSettings() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dismissCpaaSettings();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void dismissAllDialog() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dismissAllDialog();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public boolean hasDialogShowing() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasDialogShowing();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void registerSystemSettingsCallback(ISystemSettingsCallback cb) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerSystemSettingsCallback(cb);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void unregisterSystemSettingsCallback(ISystemSettingsCallback cb) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterSystemSettingsCallback(cb);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISystemSettings impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ISystemSettings getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
