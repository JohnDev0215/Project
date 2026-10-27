package android.app.slice;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ISliceManager extends IInterface {

    public static class Default implements ISliceManager {
        @Override // android.app.slice.ISliceManager
        public void applyRestore(byte[] bArr, int i) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.slice.ISliceManager
        public int checkSlicePermission(Uri uri, String str, String str2, int i, int i2, String[] strArr) throws RemoteException {
            return 0;
        }

        @Override // android.app.slice.ISliceManager
        public byte[] getBackupPayload(int i) throws RemoteException {
            return null;
        }

        @Override // android.app.slice.ISliceManager
        public Uri[] getPinnedSlices(String str) throws RemoteException {
            return null;
        }

        @Override // android.app.slice.ISliceManager
        public SliceSpec[] getPinnedSpecs(Uri uri, String str) throws RemoteException {
            return null;
        }

        @Override // android.app.slice.ISliceManager
        public void grantPermissionFromUser(Uri uri, String str, String str2, boolean z) throws RemoteException {
        }

        @Override // android.app.slice.ISliceManager
        public void grantSlicePermission(String str, String str2, Uri uri) throws RemoteException {
        }

        @Override // android.app.slice.ISliceManager
        public boolean hasSliceAccess(String str) throws RemoteException {
            return false;
        }

        @Override // android.app.slice.ISliceManager
        public void pinSlice(String str, Uri uri, SliceSpec[] sliceSpecArr, IBinder iBinder) throws RemoteException {
        }

        @Override // android.app.slice.ISliceManager
        public void revokeSlicePermission(String str, String str2, Uri uri) throws RemoteException {
        }

        @Override // android.app.slice.ISliceManager
        public void unpinSlice(String str, Uri uri, IBinder iBinder) throws RemoteException {
        }
    }

    void applyRestore(byte[] bArr, int i) throws RemoteException;

    int checkSlicePermission(Uri uri, String str, String str2, int i, int i2, String[] strArr) throws RemoteException;

    byte[] getBackupPayload(int i) throws RemoteException;

    Uri[] getPinnedSlices(String str) throws RemoteException;

    SliceSpec[] getPinnedSpecs(Uri uri, String str) throws RemoteException;

    void grantPermissionFromUser(Uri uri, String str, String str2, boolean z) throws RemoteException;

    void grantSlicePermission(String str, String str2, Uri uri) throws RemoteException;

    boolean hasSliceAccess(String str) throws RemoteException;

    void pinSlice(String str, Uri uri, SliceSpec[] sliceSpecArr, IBinder iBinder) throws RemoteException;

    void revokeSlicePermission(String str, String str2, Uri uri) throws RemoteException;

    void unpinSlice(String str, Uri uri, IBinder iBinder) throws RemoteException;

    public static abstract class Stub extends Binder implements ISliceManager {
        private static final String DESCRIPTOR = "android.app.slice.ISliceManager";
        static final int TRANSACTION_applyRestore = 7;
        static final int TRANSACTION_checkSlicePermission = 10;
        static final int TRANSACTION_getBackupPayload = 6;
        static final int TRANSACTION_getPinnedSlices = 5;
        static final int TRANSACTION_getPinnedSpecs = 4;
        static final int TRANSACTION_grantPermissionFromUser = 11;
        static final int TRANSACTION_grantSlicePermission = 8;
        static final int TRANSACTION_hasSliceAccess = 3;
        static final int TRANSACTION_pinSlice = 1;
        static final int TRANSACTION_revokeSlicePermission = 9;
        static final int TRANSACTION_unpinSlice = 2;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "pinSlice";
                case 2:
                    return "unpinSlice";
                case 3:
                    return "hasSliceAccess";
                case 4:
                    return "getPinnedSpecs";
                case 5:
                    return "getPinnedSlices";
                case 6:
                    return "getBackupPayload";
                case 7:
                    return "applyRestore";
                case 8:
                    return "grantSlicePermission";
                case 9:
                    return "revokeSlicePermission";
                case 10:
                    return "checkSlicePermission";
                case 11:
                    return "grantPermissionFromUser";
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

        public static ISliceManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISliceManager)) {
                return (ISliceManager) iInterfaceQueryLocalInterface;
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
                    pinSlice(parcel.readString(), parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, (SliceSpec[]) parcel.createTypedArray(SliceSpec.CREATOR), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unpinSlice(parcel.readString(), parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasSliceAccess = hasSliceAccess(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasSliceAccess ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    SliceSpec[] pinnedSpecs = getPinnedSpecs(parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(pinnedSpecs, 1);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    Uri[] pinnedSlices = getPinnedSlices(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(pinnedSlices, 1);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] backupPayload = getBackupPayload(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(backupPayload);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    applyRestore(parcel.createByteArray(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    grantSlicePermission(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    revokeSlicePermission(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCheckSlicePermission = checkSlicePermission(parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.createStringArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCheckSlicePermission);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    grantPermissionFromUser(parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ISliceManager {
            public static ISliceManager sDefaultImpl;
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

            @Override // android.app.slice.ISliceManager
            public void pinSlice(String str, Uri uri, SliceSpec[] sliceSpecArr, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedArray(sliceSpecArr, 0);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pinSlice(str, uri, sliceSpecArr, iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.slice.ISliceManager
            public void unpinSlice(String str, Uri uri, IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unpinSlice(str, uri, iBinder);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.slice.ISliceManager
            public boolean hasSliceAccess(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasSliceAccess(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.slice.ISliceManager
            public SliceSpec[] getPinnedSpecs(Uri uri, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPinnedSpecs(uri, str);
                    }
                    parcelObtain2.readException();
                    return (SliceSpec[]) parcelObtain2.createTypedArray(SliceSpec.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.slice.ISliceManager
            public Uri[] getPinnedSlices(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPinnedSlices(str);
                    }
                    parcelObtain2.readException();
                    return (Uri[]) parcelObtain2.createTypedArray(Uri.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.slice.ISliceManager
            public byte[] getBackupPayload(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBackupPayload(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.slice.ISliceManager
            public void applyRestore(byte[] bArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().applyRestore(bArr, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.slice.ISliceManager
            public void grantSlicePermission(String str, String str2, Uri uri) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().grantSlicePermission(str, str2, uri);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.slice.ISliceManager
            public void revokeSlicePermission(String str, String str2, Uri uri) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().revokeSlicePermission(str, str2, uri);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.slice.ISliceManager
            public int checkSlicePermission(Uri uri, String str, String str2, int i, int i2, String[] strArr) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStringArray(strArr);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        int iCheckSlicePermission = Stub.getDefaultImpl().checkSlicePermission(uri, str, str2, i, i2, strArr);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return iCheckSlicePermission;
                    }
                    parcelObtain2.readException();
                    int i3 = parcelObtain2.readInt();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return i3;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.app.slice.ISliceManager
            public void grantPermissionFromUser(Uri uri, String str, String str2, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!z) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().grantPermissionFromUser(uri, str, str2, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISliceManager iSliceManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSliceManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSliceManager;
            return true;
        }

        public static ISliceManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
