package android.content.pm;

import android.content.pm.IPackageChangeObserver;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IPackageManagerNative extends IInterface {
    public static final int LOCATION_PRODUCT = 4;
    public static final int LOCATION_SYSTEM = 1;
    public static final int LOCATION_VENDOR = 2;

    public static class Default implements IPackageManagerNative {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.content.pm.IPackageManagerNative
        public String[] getAllPackages() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManagerNative
        public String getInstallerForPackage(String str) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManagerNative
        public int getLocationFlags(String str) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManagerNative
        public String getModuleMetadataPackageName() throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManagerNative
        public String[] getNamesForUids(int[] iArr) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManagerNative
        public int getTargetSdkVersionForPackage(String str) throws RemoteException {
            return 0;
        }

        @Override // android.content.pm.IPackageManagerNative
        public long getVersionCodeForPackage(String str) throws RemoteException {
            return 0L;
        }

        @Override // android.content.pm.IPackageManagerNative
        public boolean[] isAudioPlaybackCaptureAllowed(String[] strArr) throws RemoteException {
            return null;
        }

        @Override // android.content.pm.IPackageManagerNative
        public void registerPackageChangeObserver(IPackageChangeObserver iPackageChangeObserver) throws RemoteException {
        }

        @Override // android.content.pm.IPackageManagerNative
        public void unregisterPackageChangeObserver(IPackageChangeObserver iPackageChangeObserver) throws RemoteException {
        }
    }

    String[] getAllPackages() throws RemoteException;

    String getInstallerForPackage(String str) throws RemoteException;

    int getLocationFlags(String str) throws RemoteException;

    String getModuleMetadataPackageName() throws RemoteException;

    String[] getNamesForUids(int[] iArr) throws RemoteException;

    int getTargetSdkVersionForPackage(String str) throws RemoteException;

    long getVersionCodeForPackage(String str) throws RemoteException;

    boolean[] isAudioPlaybackCaptureAllowed(String[] strArr) throws RemoteException;

    void registerPackageChangeObserver(IPackageChangeObserver iPackageChangeObserver) throws RemoteException;

    void unregisterPackageChangeObserver(IPackageChangeObserver iPackageChangeObserver) throws RemoteException;

    public static abstract class Stub extends Binder implements IPackageManagerNative {
        private static final String DESCRIPTOR = "android.content.pm.IPackageManagerNative";
        static final int TRANSACTION_getAllPackages = 8;
        static final int TRANSACTION_getInstallerForPackage = 2;
        static final int TRANSACTION_getLocationFlags = 5;
        static final int TRANSACTION_getModuleMetadataPackageName = 7;
        static final int TRANSACTION_getNamesForUids = 1;
        static final int TRANSACTION_getTargetSdkVersionForPackage = 6;
        static final int TRANSACTION_getVersionCodeForPackage = 3;
        static final int TRANSACTION_isAudioPlaybackCaptureAllowed = 4;
        static final int TRANSACTION_registerPackageChangeObserver = 9;
        static final int TRANSACTION_unregisterPackageChangeObserver = 10;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getNamesForUids";
                case 2:
                    return "getInstallerForPackage";
                case 3:
                    return "getVersionCodeForPackage";
                case 4:
                    return "isAudioPlaybackCaptureAllowed";
                case 5:
                    return "getLocationFlags";
                case 6:
                    return "getTargetSdkVersionForPackage";
                case 7:
                    return "getModuleMetadataPackageName";
                case 8:
                    return "getAllPackages";
                case 9:
                    return "registerPackageChangeObserver";
                case 10:
                    return "unregisterPackageChangeObserver";
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

        public static IPackageManagerNative asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPackageManagerNative)) {
                return (IPackageManagerNative) iInterfaceQueryLocalInterface;
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
                    String[] namesForUids = getNamesForUids(parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(namesForUids);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String installerForPackage = getInstallerForPackage(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(installerForPackage);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    long versionCodeForPackage = getVersionCodeForPackage(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeLong(versionCodeForPackage);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean[] zArrIsAudioPlaybackCaptureAllowed = isAudioPlaybackCaptureAllowed(parcel.createStringArray());
                    parcel2.writeNoException();
                    parcel2.writeBooleanArray(zArrIsAudioPlaybackCaptureAllowed);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int locationFlags = getLocationFlags(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(locationFlags);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int targetSdkVersionForPackage = getTargetSdkVersionForPackage(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(targetSdkVersionForPackage);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    String moduleMetadataPackageName = getModuleMetadataPackageName();
                    parcel2.writeNoException();
                    parcel2.writeString(moduleMetadataPackageName);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] allPackages = getAllPackages();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(allPackages);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerPackageChangeObserver(IPackageChangeObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterPackageChangeObserver(IPackageChangeObserver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IPackageManagerNative {
            public static IPackageManagerNative sDefaultImpl;
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

            @Override // android.content.pm.IPackageManagerNative
            public String[] getNamesForUids(int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNamesForUids(iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManagerNative
            public String getInstallerForPackage(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstallerForPackage(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManagerNative
            public long getVersionCodeForPackage(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVersionCodeForPackage(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManagerNative
            public boolean[] isAudioPlaybackCaptureAllowed(String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAudioPlaybackCaptureAllowed(strArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createBooleanArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManagerNative
            public int getLocationFlags(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLocationFlags(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManagerNative
            public int getTargetSdkVersionForPackage(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTargetSdkVersionForPackage(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManagerNative
            public String getModuleMetadataPackageName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getModuleMetadataPackageName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManagerNative
            public String[] getAllPackages() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllPackages();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManagerNative
            public void registerPackageChangeObserver(IPackageChangeObserver iPackageChangeObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iPackageChangeObserver != null ? iPackageChangeObserver.asBinder() : null);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerPackageChangeObserver(iPackageChangeObserver);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.content.pm.IPackageManagerNative
            public void unregisterPackageChangeObserver(IPackageChangeObserver iPackageChangeObserver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iPackageChangeObserver != null ? iPackageChangeObserver.asBinder() : null);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterPackageChangeObserver(iPackageChangeObserver);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPackageManagerNative iPackageManagerNative) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iPackageManagerNative == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPackageManagerNative;
            return true;
        }

        public static IPackageManagerNative getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
