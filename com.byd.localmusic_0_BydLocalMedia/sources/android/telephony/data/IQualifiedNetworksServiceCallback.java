package android.telephony.data;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IQualifiedNetworksServiceCallback extends IInterface {

    public static class Default implements IQualifiedNetworksServiceCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.telephony.data.IQualifiedNetworksServiceCallback
        public void onQualifiedNetworkTypesChanged(int i, int[] iArr) throws RemoteException {
        }
    }

    void onQualifiedNetworkTypesChanged(int i, int[] iArr) throws RemoteException;

    public static abstract class Stub extends Binder implements IQualifiedNetworksServiceCallback {
        private static final String DESCRIPTOR = "android.telephony.data.IQualifiedNetworksServiceCallback";
        static final int TRANSACTION_onQualifiedNetworkTypesChanged = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onQualifiedNetworkTypesChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IQualifiedNetworksServiceCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IQualifiedNetworksServiceCallback)) {
                return (IQualifiedNetworksServiceCallback) iInterfaceQueryLocalInterface;
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
                onQualifiedNetworkTypesChanged(parcel.readInt(), parcel.createIntArray());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IQualifiedNetworksServiceCallback {
            public static IQualifiedNetworksServiceCallback sDefaultImpl;
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

            @Override // android.telephony.data.IQualifiedNetworksServiceCallback
            public void onQualifiedNetworkTypesChanged(int i, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeIntArray(iArr);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onQualifiedNetworkTypesChanged(i, iArr);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IQualifiedNetworksServiceCallback iQualifiedNetworksServiceCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iQualifiedNetworksServiceCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iQualifiedNetworksServiceCallback;
            return true;
        }

        public static IQualifiedNetworksServiceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
