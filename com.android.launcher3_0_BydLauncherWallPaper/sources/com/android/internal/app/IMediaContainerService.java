package com.android.internal.app;

import android.content.pm.PackageInfoLite;
import android.content.res.ObbInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.os.IParcelFileDescriptorFactory;

/* JADX INFO: loaded from: classes4.dex */
public interface IMediaContainerService extends IInterface {

    public static class Default implements IMediaContainerService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.app.IMediaContainerService
        public long calculateInstalledSize(String str, String str2) throws RemoteException {
            return 0L;
        }

        @Override // com.android.internal.app.IMediaContainerService
        public int copyPackage(String str, IParcelFileDescriptorFactory iParcelFileDescriptorFactory) throws RemoteException {
            return 0;
        }

        @Override // com.android.internal.app.IMediaContainerService
        public PackageInfoLite getMinimalPackageInfo(String str, int i, String str2) throws RemoteException {
            return null;
        }

        @Override // com.android.internal.app.IMediaContainerService
        public ObbInfo getObbInfo(String str) throws RemoteException {
            return null;
        }
    }

    long calculateInstalledSize(String str, String str2) throws RemoteException;

    int copyPackage(String str, IParcelFileDescriptorFactory iParcelFileDescriptorFactory) throws RemoteException;

    PackageInfoLite getMinimalPackageInfo(String str, int i, String str2) throws RemoteException;

    ObbInfo getObbInfo(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaContainerService {
        private static final String DESCRIPTOR = "com.android.internal.app.IMediaContainerService";
        static final int TRANSACTION_calculateInstalledSize = 4;
        static final int TRANSACTION_copyPackage = 1;
        static final int TRANSACTION_getMinimalPackageInfo = 2;
        static final int TRANSACTION_getObbInfo = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "copyPackage";
            }
            if (i == 2) {
                return "getMinimalPackageInfo";
            }
            if (i == 3) {
                return "getObbInfo";
            }
            if (i != 4) {
                return null;
            }
            return "calculateInstalledSize";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaContainerService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMediaContainerService)) {
                return (IMediaContainerService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int iCopyPackage = copyPackage(parcel.readString(), IParcelFileDescriptorFactory.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeInt(iCopyPackage);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                PackageInfoLite minimalPackageInfo = getMinimalPackageInfo(parcel.readString(), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                if (minimalPackageInfo != null) {
                    parcel2.writeInt(1);
                    minimalPackageInfo.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                ObbInfo obbInfo = getObbInfo(parcel.readString());
                parcel2.writeNoException();
                if (obbInfo != null) {
                    parcel2.writeInt(1);
                    obbInfo.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i != 4) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            long jCalculateInstalledSize = calculateInstalledSize(parcel.readString(), parcel.readString());
            parcel2.writeNoException();
            parcel2.writeLong(jCalculateInstalledSize);
            return true;
        }

        private static class Proxy implements IMediaContainerService {
            public static IMediaContainerService sDefaultImpl;
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

            @Override // com.android.internal.app.IMediaContainerService
            public int copyPackage(String str, IParcelFileDescriptorFactory iParcelFileDescriptorFactory) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iParcelFileDescriptorFactory != null ? iParcelFileDescriptorFactory.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().copyPackage(str, iParcelFileDescriptorFactory);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IMediaContainerService
            public PackageInfoLite getMinimalPackageInfo(String str, int i, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMinimalPackageInfo(str, i, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? PackageInfoLite.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IMediaContainerService
            public ObbInfo getObbInfo(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getObbInfo(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ObbInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.app.IMediaContainerService
            public long calculateInstalledSize(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().calculateInstalledSize(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaContainerService iMediaContainerService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMediaContainerService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMediaContainerService;
            return true;
        }

        public static IMediaContainerService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
