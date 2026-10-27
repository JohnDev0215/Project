package com.byd.systemsettings;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.systemsettings.ISystemSettingsCallback;

/* JADX INFO: loaded from: classes.dex */
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
        public void doAction(String str, String str2) throws RemoteException {
        }

        @Override // com.byd.systemsettings.ISystemSettings
        public void finishScreensaver() throws RemoteException {
        }

        @Override // com.byd.systemsettings.ISystemSettings
        public boolean hasDialogShowing() throws RemoteException {
            return false;
        }

        @Override // com.byd.systemsettings.ISystemSettings
        public void registerSystemSettingsCallback(ISystemSettingsCallback iSystemSettingsCallback) throws RemoteException {
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
        public void unregisterSystemSettingsCallback(ISystemSettingsCallback iSystemSettingsCallback) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements ISystemSettings {
        private static final String DESCRIPTOR = "com.byd.systemsettings.ISystemSettings";
        public static final int TRANSACTION_dismissAllDialog = 12;
        public static final int TRANSACTION_dismissBtSettings = 8;
        public static final int TRANSACTION_dismissCpaaSettings = 11;
        public static final int TRANSACTION_dismissHotspotSettings = 10;
        public static final int TRANSACTION_dismissWifiSettings = 9;
        public static final int TRANSACTION_doAction = 1;
        public static final int TRANSACTION_finishScreensaver = 7;
        public static final int TRANSACTION_hasDialogShowing = 13;
        public static final int TRANSACTION_registerSystemSettingsCallback = 14;
        public static final int TRANSACTION_showBtSettings = 3;
        public static final int TRANSACTION_showCpaaSettings = 6;
        public static final int TRANSACTION_showHotspotSettings = 5;
        public static final int TRANSACTION_showScreensaver = 2;
        public static final int TRANSACTION_showWifiSettings = 4;
        public static final int TRANSACTION_unregisterSystemSettingsCallback = 15;

        public static class Proxy implements ISystemSettings {
            public static ISystemSettings sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void dismissAllDialog() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().dismissAllDialog();
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
                    if (this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().dismissBtSettings();
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
                    if (this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().dismissCpaaSettings();
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
                    if (this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().dismissHotspotSettings();
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
                    if (this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().dismissWifiSettings();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void doAction(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().doAction(str, str2);
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
                    if (this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().finishScreensaver();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
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
            public void registerSystemSettingsCallback(ISystemSettingsCallback iSystemSettingsCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iSystemSettingsCallback != null ? iSystemSettingsCallback.asBinder() : null);
                    if (this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().registerSystemSettingsCallback(iSystemSettingsCallback);
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
                    if (this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().showBtSettings();
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
                    if (this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().showCpaaSettings();
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
                    if (this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().showHotspotSettings();
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
                    if (this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().showScreensaver();
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
                    if (this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().showWifiSettings();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void unregisterSystemSettingsCallback(ISystemSettingsCallback iSystemSettingsCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iSystemSettingsCallback != null ? iSystemSettingsCallback.asBinder() : null);
                    if (this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterSystemSettingsCallback(iSystemSettingsCallback);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISystemSettings asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ISystemSettings)) ? new Proxy(iBinder) : (ISystemSettings) iInterfaceQueryLocalInterface;
        }

        public static ISystemSettings getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ISystemSettings iSystemSettings) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSystemSettings == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSystemSettings;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
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
    }

    void dismissAllDialog() throws RemoteException;

    void dismissBtSettings() throws RemoteException;

    void dismissCpaaSettings() throws RemoteException;

    void dismissHotspotSettings() throws RemoteException;

    void dismissWifiSettings() throws RemoteException;

    void doAction(String str, String str2) throws RemoteException;

    void finishScreensaver() throws RemoteException;

    boolean hasDialogShowing() throws RemoteException;

    void registerSystemSettingsCallback(ISystemSettingsCallback iSystemSettingsCallback) throws RemoteException;

    void showBtSettings() throws RemoteException;

    void showCpaaSettings() throws RemoteException;

    void showHotspotSettings() throws RemoteException;

    void showScreensaver() throws RemoteException;

    void showWifiSettings() throws RemoteException;

    void unregisterSystemSettingsCallback(ISystemSettingsCallback iSystemSettingsCallback) throws RemoteException;
}
