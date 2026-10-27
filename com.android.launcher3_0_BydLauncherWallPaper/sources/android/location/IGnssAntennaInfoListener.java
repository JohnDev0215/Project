package android.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IGnssAntennaInfoListener extends IInterface {

    public static class Default implements IGnssAntennaInfoListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.location.IGnssAntennaInfoListener
        public void onGnssAntennaInfoReceived(List<GnssAntennaInfo> list) throws RemoteException {
        }
    }

    void onGnssAntennaInfoReceived(List<GnssAntennaInfo> list) throws RemoteException;

    public static abstract class Stub extends Binder implements IGnssAntennaInfoListener {
        private static final String DESCRIPTOR = "android.location.IGnssAntennaInfoListener";
        static final int TRANSACTION_onGnssAntennaInfoReceived = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onGnssAntennaInfoReceived";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGnssAntennaInfoListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IGnssAntennaInfoListener)) {
                return (IGnssAntennaInfoListener) iInterfaceQueryLocalInterface;
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
                onGnssAntennaInfoReceived(parcel.createTypedArrayList(GnssAntennaInfo.CREATOR));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IGnssAntennaInfoListener {
            public static IGnssAntennaInfoListener sDefaultImpl;
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

            @Override // android.location.IGnssAntennaInfoListener
            public void onGnssAntennaInfoReceived(List<GnssAntennaInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGnssAntennaInfoReceived(list);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGnssAntennaInfoListener iGnssAntennaInfoListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iGnssAntennaInfoListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iGnssAntennaInfoListener;
            return true;
        }

        public static IGnssAntennaInfoListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
