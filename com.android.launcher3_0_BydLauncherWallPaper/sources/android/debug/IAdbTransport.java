package android.debug;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IAdbTransport extends IInterface {

    public static class Default implements IAdbTransport {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.debug.IAdbTransport
        public void onAdbEnabled(boolean z, byte b) throws RemoteException {
        }
    }

    void onAdbEnabled(boolean z, byte b) throws RemoteException;

    public static abstract class Stub extends Binder implements IAdbTransport {
        private static final String DESCRIPTOR = "android.debug.IAdbTransport";
        static final int TRANSACTION_onAdbEnabled = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onAdbEnabled";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAdbTransport asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAdbTransport)) {
                return (IAdbTransport) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onAdbEnabled(parcel.readInt() != 0, parcel.readByte());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IAdbTransport {
            public static IAdbTransport sDefaultImpl;
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

            @Override // android.debug.IAdbTransport
            public void onAdbEnabled(boolean z, byte b) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeByte(b);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onAdbEnabled(z, b);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAdbTransport iAdbTransport) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAdbTransport == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAdbTransport;
            return true;
        }

        public static IAdbTransport getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
