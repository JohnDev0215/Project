package android.permission;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import com.android.internal.infra.AndroidFuture;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IPermissionController extends IInterface {

    public static class Default implements IPermissionController {
        @Override // android.permission.IPermissionController
        public void applyStagedRuntimePermissionBackup(String str, UserHandle userHandle, AndroidFuture androidFuture) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.permission.IPermissionController
        public void countPermissionApps(List<String> list, int i, AndroidFuture androidFuture) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void getAppPermissions(String str, AndroidFuture androidFuture) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void getPermissionUsages(boolean z, long j, AndroidFuture androidFuture) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void getRuntimePermissionBackup(UserHandle userHandle, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void grantOrUpgradeDefaultRuntimePermissions(AndroidFuture androidFuture) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void notifyOneTimePermissionSessionTimeout(String str) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void revokeRuntimePermission(String str, String str2) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void revokeRuntimePermissions(Bundle bundle, boolean z, int i, String str, AndroidFuture androidFuture) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void setRuntimePermissionGrantStateByDeviceAdmin(String str, String str2, String str3, int i, AndroidFuture androidFuture) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void stageAndApplyRuntimePermissionsBackup(UserHandle userHandle, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        }

        @Override // android.permission.IPermissionController
        public void updateUserSensitiveForApp(int i, AndroidFuture androidFuture) throws RemoteException {
        }
    }

    void applyStagedRuntimePermissionBackup(String str, UserHandle userHandle, AndroidFuture androidFuture) throws RemoteException;

    void countPermissionApps(List<String> list, int i, AndroidFuture androidFuture) throws RemoteException;

    void getAppPermissions(String str, AndroidFuture androidFuture) throws RemoteException;

    void getPermissionUsages(boolean z, long j, AndroidFuture androidFuture) throws RemoteException;

    void getRuntimePermissionBackup(UserHandle userHandle, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void grantOrUpgradeDefaultRuntimePermissions(AndroidFuture androidFuture) throws RemoteException;

    void notifyOneTimePermissionSessionTimeout(String str) throws RemoteException;

    void revokeRuntimePermission(String str, String str2) throws RemoteException;

    void revokeRuntimePermissions(Bundle bundle, boolean z, int i, String str, AndroidFuture androidFuture) throws RemoteException;

    void setRuntimePermissionGrantStateByDeviceAdmin(String str, String str2, String str3, int i, AndroidFuture androidFuture) throws RemoteException;

    void stageAndApplyRuntimePermissionsBackup(UserHandle userHandle, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void updateUserSensitiveForApp(int i, AndroidFuture androidFuture) throws RemoteException;

    public static abstract class Stub extends Binder implements IPermissionController {
        private static final String DESCRIPTOR = "android.permission.IPermissionController";
        static final int TRANSACTION_applyStagedRuntimePermissionBackup = 4;
        static final int TRANSACTION_countPermissionApps = 7;
        static final int TRANSACTION_getAppPermissions = 5;
        static final int TRANSACTION_getPermissionUsages = 8;
        static final int TRANSACTION_getRuntimePermissionBackup = 2;
        static final int TRANSACTION_grantOrUpgradeDefaultRuntimePermissions = 10;
        static final int TRANSACTION_notifyOneTimePermissionSessionTimeout = 11;
        static final int TRANSACTION_revokeRuntimePermission = 6;
        static final int TRANSACTION_revokeRuntimePermissions = 1;
        static final int TRANSACTION_setRuntimePermissionGrantStateByDeviceAdmin = 9;
        static final int TRANSACTION_stageAndApplyRuntimePermissionsBackup = 3;
        static final int TRANSACTION_updateUserSensitiveForApp = 12;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "revokeRuntimePermissions";
                case 2:
                    return "getRuntimePermissionBackup";
                case 3:
                    return "stageAndApplyRuntimePermissionsBackup";
                case 4:
                    return "applyStagedRuntimePermissionBackup";
                case 5:
                    return "getAppPermissions";
                case 6:
                    return "revokeRuntimePermission";
                case 7:
                    return "countPermissionApps";
                case 8:
                    return "getPermissionUsages";
                case 9:
                    return "setRuntimePermissionGrantStateByDeviceAdmin";
                case 10:
                    return "grantOrUpgradeDefaultRuntimePermissions";
                case 11:
                    return "notifyOneTimePermissionSessionTimeout";
                case 12:
                    return "updateUserSensitiveForApp";
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

        public static IPermissionController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPermissionController)) {
                return (IPermissionController) iInterfaceQueryLocalInterface;
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
                    revokeRuntimePermissions(parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0, parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? AndroidFuture.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    getRuntimePermissionBackup(parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    stageAndApplyRuntimePermissionsBackup(parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    applyStagedRuntimePermissionBackup(parcel.readString(), parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? AndroidFuture.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    getAppPermissions(parcel.readString(), parcel.readInt() != 0 ? AndroidFuture.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    revokeRuntimePermission(parcel.readString(), parcel.readString());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    countPermissionApps(parcel.createStringArrayList(), parcel.readInt(), parcel.readInt() != 0 ? AndroidFuture.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    getPermissionUsages(parcel.readInt() != 0, parcel.readLong(), parcel.readInt() != 0 ? AndroidFuture.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRuntimePermissionGrantStateByDeviceAdmin(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt() != 0 ? AndroidFuture.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    grantOrUpgradeDefaultRuntimePermissions(parcel.readInt() != 0 ? AndroidFuture.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyOneTimePermissionSessionTimeout(parcel.readString());
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateUserSensitiveForApp(parcel.readInt(), parcel.readInt() != 0 ? AndroidFuture.CREATOR.createFromParcel(parcel) : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IPermissionController {
            public static IPermissionController sDefaultImpl;
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

            @Override // android.permission.IPermissionController
            public void revokeRuntimePermissions(Bundle bundle, boolean z, int i, String str, AndroidFuture androidFuture) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (androidFuture != null) {
                        parcelObtain.writeInt(1);
                        androidFuture.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().revokeRuntimePermissions(bundle, z, i, str, androidFuture);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void getRuntimePermissionBackup(UserHandle userHandle, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getRuntimePermissionBackup(userHandle, parcelFileDescriptor);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void stageAndApplyRuntimePermissionsBackup(UserHandle userHandle, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().stageAndApplyRuntimePermissionsBackup(userHandle, parcelFileDescriptor);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void applyStagedRuntimePermissionBackup(String str, UserHandle userHandle, AndroidFuture androidFuture) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (androidFuture != null) {
                        parcelObtain.writeInt(1);
                        androidFuture.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().applyStagedRuntimePermissionBackup(str, userHandle, androidFuture);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void getAppPermissions(String str, AndroidFuture androidFuture) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (androidFuture != null) {
                        parcelObtain.writeInt(1);
                        androidFuture.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getAppPermissions(str, androidFuture);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void revokeRuntimePermission(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().revokeRuntimePermission(str, str2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void countPermissionApps(List<String> list, int i, AndroidFuture androidFuture) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    parcelObtain.writeInt(i);
                    if (androidFuture != null) {
                        parcelObtain.writeInt(1);
                        androidFuture.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().countPermissionApps(list, i, androidFuture);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void getPermissionUsages(boolean z, long j, AndroidFuture androidFuture) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeLong(j);
                    if (androidFuture != null) {
                        parcelObtain.writeInt(1);
                        androidFuture.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().getPermissionUsages(z, j, androidFuture);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void setRuntimePermissionGrantStateByDeviceAdmin(String str, String str2, String str3, int i, AndroidFuture androidFuture) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i);
                    if (androidFuture != null) {
                        parcelObtain.writeInt(1);
                        androidFuture.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setRuntimePermissionGrantStateByDeviceAdmin(str, str2, str3, i, androidFuture);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void grantOrUpgradeDefaultRuntimePermissions(AndroidFuture androidFuture) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (androidFuture != null) {
                        parcelObtain.writeInt(1);
                        androidFuture.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().grantOrUpgradeDefaultRuntimePermissions(androidFuture);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void notifyOneTimePermissionSessionTimeout(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().notifyOneTimePermissionSessionTimeout(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.permission.IPermissionController
            public void updateUserSensitiveForApp(int i, AndroidFuture androidFuture) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (androidFuture != null) {
                        parcelObtain.writeInt(1);
                        androidFuture.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().updateUserSensitiveForApp(i, androidFuture);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPermissionController iPermissionController) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iPermissionController == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPermissionController;
            return true;
        }

        public static IPermissionController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
