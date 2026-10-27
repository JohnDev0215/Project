package com.android.internal.compat;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public interface IPlatformCompatNative extends IInterface {

    public static class Default implements IPlatformCompatNative {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.compat.IPlatformCompatNative
        public boolean isChangeEnabledByPackageName(long j, String str, int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.compat.IPlatformCompatNative
        public boolean isChangeEnabledByUid(long j, int i) throws RemoteException {
            return false;
        }

        @Override // com.android.internal.compat.IPlatformCompatNative
        public void reportChangeByPackageName(long j, String str, int i) throws RemoteException {
        }

        @Override // com.android.internal.compat.IPlatformCompatNative
        public void reportChangeByUid(long j, int i) throws RemoteException {
        }
    }

    boolean isChangeEnabledByPackageName(long j, String str, int i) throws RemoteException;

    boolean isChangeEnabledByUid(long j, int i) throws RemoteException;

    void reportChangeByPackageName(long j, String str, int i) throws RemoteException;

    void reportChangeByUid(long j, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IPlatformCompatNative {
        private static final String DESCRIPTOR = "com.android.internal.compat.IPlatformCompatNative";
        static final int TRANSACTION_isChangeEnabledByPackageName = 3;
        static final int TRANSACTION_isChangeEnabledByUid = 4;
        static final int TRANSACTION_reportChangeByPackageName = 1;
        static final int TRANSACTION_reportChangeByUid = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "reportChangeByPackageName";
            }
            if (i == 2) {
                return "reportChangeByUid";
            }
            if (i == 3) {
                return "isChangeEnabledByPackageName";
            }
            if (i != 4) {
                return null;
            }
            return "isChangeEnabledByUid";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPlatformCompatNative asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPlatformCompatNative)) {
                return (IPlatformCompatNative) iInterfaceQueryLocalInterface;
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
                reportChangeByPackageName(parcel.readLong(), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                reportChangeByUid(parcel.readLong(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean zIsChangeEnabledByPackageName = isChangeEnabledByPackageName(parcel.readLong(), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(zIsChangeEnabledByPackageName ? 1 : 0);
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
            boolean zIsChangeEnabledByUid = isChangeEnabledByUid(parcel.readLong(), parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeInt(zIsChangeEnabledByUid ? 1 : 0);
            return true;
        }

        private static class Proxy implements IPlatformCompatNative {
            public static IPlatformCompatNative sDefaultImpl;
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

            @Override // com.android.internal.compat.IPlatformCompatNative
            public void reportChangeByPackageName(long j, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportChangeByPackageName(j, str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompatNative
            public void reportChangeByUid(long j, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reportChangeByUid(j, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompatNative
            public boolean isChangeEnabledByPackageName(long j, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isChangeEnabledByPackageName(j, str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.compat.IPlatformCompatNative
            public boolean isChangeEnabledByUid(long j, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isChangeEnabledByUid(j, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPlatformCompatNative iPlatformCompatNative) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iPlatformCompatNative == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPlatformCompatNative;
            return true;
        }

        public static IPlatformCompatNative getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
