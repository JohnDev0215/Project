package com.bosch.tee.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public interface ITEEService extends IInterface {

    public static class Default implements ITEEService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.bosch.tee.service.ITEEService
        public int get_errno() throws RemoteException {
            return 0;
        }

        @Override // com.bosch.tee.service.ITEEService
        public String getcaversion() throws RemoteException {
            return null;
        }

        @Override // com.bosch.tee.service.ITEEService
        public String gettaversion() throws RemoteException {
            return null;
        }

        @Override // com.bosch.tee.service.ITEEService
        public byte[] read_pem_crt(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // com.bosch.tee.service.ITEEService
        public byte[] sign_hash(byte[] bArr, int i, int i2, int i3, int i4, int i5) throws RemoteException {
            return null;
        }
    }

    int get_errno() throws RemoteException;

    String getcaversion() throws RemoteException;

    String gettaversion() throws RemoteException;

    byte[] read_pem_crt(int i, int i2) throws RemoteException;

    byte[] sign_hash(byte[] bArr, int i, int i2, int i3, int i4, int i5) throws RemoteException;

    public static abstract class Stub extends Binder implements ITEEService {
        private static final String DESCRIPTOR = "com.bosch.tee.service.ITEEService";
        static final int TRANSACTION_get_errno = 1;
        static final int TRANSACTION_getcaversion = 2;
        static final int TRANSACTION_gettaversion = 3;
        static final int TRANSACTION_read_pem_crt = 5;
        static final int TRANSACTION_sign_hash = 4;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "get_errno";
            }
            if (i == 2) {
                return "getcaversion";
            }
            if (i == 3) {
                return "gettaversion";
            }
            if (i == 4) {
                return "sign_hash";
            }
            if (i != 5) {
                return null;
            }
            return "read_pem_crt";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITEEService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITEEService)) {
                return (ITEEService) iInterfaceQueryLocalInterface;
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
                int i3 = get_errno();
                parcel2.writeNoException();
                parcel2.writeInt(i3);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                String str = getcaversion();
                parcel2.writeNoException();
                parcel2.writeString(str);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                String str2 = gettaversion();
                parcel2.writeNoException();
                parcel2.writeString(str2);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                byte[] bArrSign_hash = sign_hash(parcel.createByteArray(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeByteArray(bArrSign_hash);
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            byte[] bArr = read_pem_crt(parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeByteArray(bArr);
            return true;
        }

        private static class Proxy implements ITEEService {
            public static ITEEService sDefaultImpl;
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

            @Override // com.bosch.tee.service.ITEEService
            public int get_errno() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().get_errno();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.bosch.tee.service.ITEEService
            public String getcaversion() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getcaversion();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.bosch.tee.service.ITEEService
            public String gettaversion() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().gettaversion();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.bosch.tee.service.ITEEService
            public byte[] sign_hash(byte[] bArr, int i, int i2, int i3, int i4, int i5) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i5);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sign_hash(bArr, i, i2, i3, i4, i5);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.bosch.tee.service.ITEEService
            public byte[] read_pem_crt(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().read_pem_crt(i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITEEService iTEEService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTEEService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTEEService;
            return true;
        }

        public static ITEEService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
