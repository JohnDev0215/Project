package com.android.internal.compat;

import android.content.pm.ApplicationInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.compat.IOverrideValidator;

/* JADX INFO: loaded from: classes4.dex */
public interface IPlatformCompat extends IInterface {

    public static class Default implements IPlatformCompat {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public boolean clearOverride(long j, String str) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public void clearOverrides(String str) throws RemoteException {
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public void clearOverridesForTest(String str) throws RemoteException {
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public int disableTargetSdkChanges(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public int enableTargetSdkChanges(String str, int i) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public CompatibilityChangeConfig getAppConfig(ApplicationInfo applicationInfo) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public IOverrideValidator getOverrideValidator() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public boolean isChangeEnabled(long j, ApplicationInfo applicationInfo) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public boolean isChangeEnabledByPackageName(long j, String str, int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public boolean isChangeEnabledByUid(long j, int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public CompatibilityChangeInfo[] listAllChanges() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public CompatibilityChangeInfo[] listUIChanges() throws RemoteException {
            return null;
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public void reportChange(long j, ApplicationInfo applicationInfo) throws RemoteException {
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public void reportChangeByPackageName(long j, String str, int i) throws RemoteException {
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public void reportChangeByUid(long j, int i) throws RemoteException {
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public void setOverrides(CompatibilityChangeConfig compatibilityChangeConfig, String str) throws RemoteException {
        }

        @Override // com.android.internal.compat.IPlatformCompat
        public void setOverridesForTest(CompatibilityChangeConfig compatibilityChangeConfig, String str) throws RemoteException {
        }
    }

    boolean clearOverride(long j, String str) throws RemoteException;

    void clearOverrides(String str) throws RemoteException;

    void clearOverridesForTest(String str) throws RemoteException;

    int disableTargetSdkChanges(String str, int i) throws RemoteException;

    int enableTargetSdkChanges(String str, int i) throws RemoteException;

    CompatibilityChangeConfig getAppConfig(ApplicationInfo applicationInfo) throws RemoteException;

    IOverrideValidator getOverrideValidator() throws RemoteException;

    boolean isChangeEnabled(long j, ApplicationInfo applicationInfo) throws RemoteException;

    boolean isChangeEnabledByPackageName(long j, String str, int i) throws RemoteException;

    boolean isChangeEnabledByUid(long j, int i) throws RemoteException;

    CompatibilityChangeInfo[] listAllChanges() throws RemoteException;

    CompatibilityChangeInfo[] listUIChanges() throws RemoteException;

    void reportChange(long j, ApplicationInfo applicationInfo) throws RemoteException;

    void reportChangeByPackageName(long j, String str, int i) throws RemoteException;

    void reportChangeByUid(long j, int i) throws RemoteException;

    void setOverrides(CompatibilityChangeConfig compatibilityChangeConfig, String str) throws RemoteException;

    void setOverridesForTest(CompatibilityChangeConfig compatibilityChangeConfig, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IPlatformCompat {
        private static final String DESCRIPTOR = "com.android.internal.compat.IPlatformCompat";
        static final int TRANSACTION_clearOverride = 9;
        static final int TRANSACTION_clearOverrides = 12;
        static final int TRANSACTION_clearOverridesForTest = 13;
        static final int TRANSACTION_disableTargetSdkChanges = 11;
        static final int TRANSACTION_enableTargetSdkChanges = 10;
        static final int TRANSACTION_getAppConfig = 14;
        static final int TRANSACTION_getOverrideValidator = 17;
        static final int TRANSACTION_isChangeEnabled = 4;
        static final int TRANSACTION_isChangeEnabledByPackageName = 5;
        static final int TRANSACTION_isChangeEnabledByUid = 6;
        static final int TRANSACTION_listAllChanges = 15;
        static final int TRANSACTION_listUIChanges = 16;
        static final int TRANSACTION_reportChange = 1;
        static final int TRANSACTION_reportChangeByPackageName = 2;
        static final int TRANSACTION_reportChangeByUid = 3;
        static final int TRANSACTION_setOverrides = 7;
        static final int TRANSACTION_setOverridesForTest = 8;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "reportChange";
                case 2:
                    return "reportChangeByPackageName";
                case 3:
                    return "reportChangeByUid";
                case 4:
                    return "isChangeEnabled";
                case 5:
                    return "isChangeEnabledByPackageName";
                case 6:
                    return "isChangeEnabledByUid";
                case 7:
                    return "setOverrides";
                case 8:
                    return "setOverridesForTest";
                case 9:
                    return "clearOverride";
                case 10:
                    return "enableTargetSdkChanges";
                case 11:
                    return "disableTargetSdkChanges";
                case 12:
                    return "clearOverrides";
                case 13:
                    return "clearOverridesForTest";
                case 14:
                    return "getAppConfig";
                case 15:
                    return "listAllChanges";
                case 16:
                    return "listUIChanges";
                case 17:
                    return "getOverrideValidator";
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

        public static IPlatformCompat asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPlatformCompat)) {
                return (IPlatformCompat) iInterfaceQueryLocalInterface;
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
                    reportChange(parcel.readLong(), parcel.readInt() != 0 ? ApplicationInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportChangeByPackageName(parcel.readLong(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    reportChangeByUid(parcel.readLong(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsChangeEnabled = isChangeEnabled(parcel.readLong(), parcel.readInt() != 0 ? ApplicationInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsChangeEnabled ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsChangeEnabledByPackageName = isChangeEnabledByPackageName(parcel.readLong(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsChangeEnabledByPackageName ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsChangeEnabledByUid = isChangeEnabledByUid(parcel.readLong(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsChangeEnabledByUid ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setOverrides(parcel.readInt() != 0 ? CompatibilityChangeConfig.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setOverridesForTest(parcel.readInt() != 0 ? CompatibilityChangeConfig.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zClearOverride = clearOverride(parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zClearOverride ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iEnableTargetSdkChanges = enableTargetSdkChanges(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iEnableTargetSdkChanges);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iDisableTargetSdkChanges = disableTargetSdkChanges(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iDisableTargetSdkChanges);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearOverrides(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearOverridesForTest(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    CompatibilityChangeConfig appConfig = getAppConfig(parcel.readInt() != 0 ? ApplicationInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (appConfig != null) {
                        parcel2.writeInt(1);
                        appConfig.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    CompatibilityChangeInfo[] compatibilityChangeInfoArrListAllChanges = listAllChanges();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(compatibilityChangeInfoArrListAllChanges, 1);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    CompatibilityChangeInfo[] compatibilityChangeInfoArrListUIChanges = listUIChanges();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(compatibilityChangeInfoArrListUIChanges, 1);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    IOverrideValidator overrideValidator = getOverrideValidator();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(overrideValidator != null ? overrideValidator.asBinder() : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IPlatformCompat {
            public static IPlatformCompat sDefaultImpl;
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

            @Override // com.android.internal.compat.IPlatformCompat
            public void reportChange(long j, ApplicationInfo applicationInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (applicationInfo != null) {
                        parcelObtain.writeInt(1);
                        applicationInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportChange(j, applicationInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public void reportChangeByPackageName(long j, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportChangeByPackageName(j, str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public void reportChangeByUid(long j, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportChangeByUid(j, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public boolean isChangeEnabled(long j, ApplicationInfo applicationInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (applicationInfo != null) {
                        parcelObtain.writeInt(1);
                        applicationInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isChangeEnabled(j, applicationInfo);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public boolean isChangeEnabledByPackageName(long j, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isChangeEnabledByPackageName(j, str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public boolean isChangeEnabledByUid(long j, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isChangeEnabledByUid(j, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public void setOverrides(CompatibilityChangeConfig compatibilityChangeConfig, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (compatibilityChangeConfig != null) {
                        parcelObtain.writeInt(1);
                        compatibilityChangeConfig.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setOverrides(compatibilityChangeConfig, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public void setOverridesForTest(CompatibilityChangeConfig compatibilityChangeConfig, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (compatibilityChangeConfig != null) {
                        parcelObtain.writeInt(1);
                        compatibilityChangeConfig.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setOverridesForTest(compatibilityChangeConfig, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public boolean clearOverride(long j, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearOverride(j, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public int enableTargetSdkChanges(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableTargetSdkChanges(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public int disableTargetSdkChanges(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disableTargetSdkChanges(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public void clearOverrides(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearOverrides(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public void clearOverridesForTest(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearOverridesForTest(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public CompatibilityChangeConfig getAppConfig(ApplicationInfo applicationInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (applicationInfo != null) {
                        parcelObtain.writeInt(1);
                        applicationInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppConfig(applicationInfo);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? CompatibilityChangeConfig.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public CompatibilityChangeInfo[] listAllChanges() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listAllChanges();
                    }
                    parcelObtain2.readException();
                    return (CompatibilityChangeInfo[]) parcelObtain2.createTypedArray(CompatibilityChangeInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public CompatibilityChangeInfo[] listUIChanges() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listUIChanges();
                    }
                    parcelObtain2.readException();
                    return (CompatibilityChangeInfo[]) parcelObtain2.createTypedArray(CompatibilityChangeInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompat
            public IOverrideValidator getOverrideValidator() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOverrideValidator();
                    }
                    parcelObtain2.readException();
                    return IOverrideValidator.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPlatformCompat iPlatformCompat) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iPlatformCompat == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPlatformCompat;
            return true;
        }

        public static IPlatformCompat getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
