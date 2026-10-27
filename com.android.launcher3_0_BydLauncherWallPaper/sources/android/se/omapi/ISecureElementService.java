package android.se.omapi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.se.omapi.ISecureElementReader;

/* JADX INFO: loaded from: classes2.dex */
public interface ISecureElementService extends IInterface {

    public static class Default implements ISecureElementService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.se.omapi.ISecureElementService
        public ISecureElementReader getReader(String str) throws RemoteException {
            return null;
        }

        @Override // android.se.omapi.ISecureElementService
        public String[] getReaders() throws RemoteException {
            return null;
        }

        @Override // android.se.omapi.ISecureElementService
        public boolean[] isNFCEventAllowed(String str, byte[] bArr, String[] strArr) throws RemoteException {
            return null;
        }
    }

    ISecureElementReader getReader(String str) throws RemoteException;

    String[] getReaders() throws RemoteException;

    boolean[] isNFCEventAllowed(String str, byte[] bArr, String[] strArr) throws RemoteException;

    public static abstract class Stub extends Binder implements ISecureElementService {
        private static final String DESCRIPTOR = "android.se.omapi.ISecureElementService";
        static final int TRANSACTION_getReader = 2;
        static final int TRANSACTION_getReaders = 1;
        static final int TRANSACTION_isNFCEventAllowed = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "getReaders";
            }
            if (i == 2) {
                return "getReader";
            }
            if (i != 3) {
                return null;
            }
            return "isNFCEventAllowed";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISecureElementService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISecureElementService)) {
                return (ISecureElementService) iInterfaceQueryLocalInterface;
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
                String[] readers = getReaders();
                parcel2.writeNoException();
                parcel2.writeStringArray(readers);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                ISecureElementReader reader = getReader(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(reader != null ? reader.asBinder() : null);
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            boolean[] zArrIsNFCEventAllowed = isNFCEventAllowed(parcel.readString(), parcel.createByteArray(), parcel.createStringArray());
            parcel2.writeNoException();
            parcel2.writeBooleanArray(zArrIsNFCEventAllowed);
            return true;
        }

        private static class Proxy implements ISecureElementService {
            public static ISecureElementService sDefaultImpl;
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

            @Override // android.se.omapi.ISecureElementService
            public String[] getReaders() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getReaders();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.se.omapi.ISecureElementService
            public ISecureElementReader getReader(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getReader(str);
                    }
                    parcelObtain2.readException();
                    return ISecureElementReader.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.se.omapi.ISecureElementService
            public boolean[] isNFCEventAllowed(String str, byte[] bArr, String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeStringArray(strArr);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isNFCEventAllowed(str, bArr, strArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createBooleanArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISecureElementService iSecureElementService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSecureElementService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSecureElementService;
            return true;
        }

        public static ISecureElementService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
