package android.telephony.ims.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IImsConfigCallback extends IInterface {

    public static class Default implements IImsConfigCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsConfigCallback
        public void onIntConfigChanged(int i, int i2) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsConfigCallback
        public void onStringConfigChanged(int i, String str) throws RemoteException {
        }
    }

    void onIntConfigChanged(int i, int i2) throws RemoteException;

    void onStringConfigChanged(int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IImsConfigCallback {
        private static final String DESCRIPTOR = "android.telephony.ims.aidl.IImsConfigCallback";
        static final int TRANSACTION_onIntConfigChanged = 1;
        static final int TRANSACTION_onStringConfigChanged = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onIntConfigChanged";
            }
            if (i != 2) {
                return null;
            }
            return "onStringConfigChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IImsConfigCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IImsConfigCallback)) {
                return (IImsConfigCallback) iInterfaceQueryLocalInterface;
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
                onIntConfigChanged(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onStringConfigChanged(parcel.readInt(), parcel.readString());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IImsConfigCallback {
            public static IImsConfigCallback sDefaultImpl;
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

            @Override // android.telephony.ims.aidl.IImsConfigCallback
            public void onIntConfigChanged(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onIntConfigChanged(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsConfigCallback
            public void onStringConfigChanged(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStringConfigChanged(i, str);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImsConfigCallback iImsConfigCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iImsConfigCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iImsConfigCallback;
            return true;
        }

        public static IImsConfigCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
