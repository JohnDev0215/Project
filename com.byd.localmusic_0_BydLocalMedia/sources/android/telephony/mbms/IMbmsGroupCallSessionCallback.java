package android.telephony.mbms;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IMbmsGroupCallSessionCallback extends IInterface {

    public static class Default implements IMbmsGroupCallSessionCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.telephony.mbms.IMbmsGroupCallSessionCallback
        public void onAvailableSaisUpdated(List list, List list2) throws RemoteException {
        }

        @Override // android.telephony.mbms.IMbmsGroupCallSessionCallback
        public void onError(int i, String str) throws RemoteException {
        }

        @Override // android.telephony.mbms.IMbmsGroupCallSessionCallback
        public void onMiddlewareReady() throws RemoteException {
        }

        @Override // android.telephony.mbms.IMbmsGroupCallSessionCallback
        public void onServiceInterfaceAvailable(String str, int i) throws RemoteException {
        }
    }

    void onAvailableSaisUpdated(List list, List list2) throws RemoteException;

    void onError(int i, String str) throws RemoteException;

    void onMiddlewareReady() throws RemoteException;

    void onServiceInterfaceAvailable(String str, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IMbmsGroupCallSessionCallback {
        private static final String DESCRIPTOR = "android.telephony.mbms.IMbmsGroupCallSessionCallback";
        static final int TRANSACTION_onAvailableSaisUpdated = 2;
        static final int TRANSACTION_onError = 1;
        static final int TRANSACTION_onMiddlewareReady = 4;
        static final int TRANSACTION_onServiceInterfaceAvailable = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onError";
            }
            if (i == 2) {
                return "onAvailableSaisUpdated";
            }
            if (i == 3) {
                return "onServiceInterfaceAvailable";
            }
            if (i != 4) {
                return null;
            }
            return "onMiddlewareReady";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMbmsGroupCallSessionCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMbmsGroupCallSessionCallback)) {
                return (IMbmsGroupCallSessionCallback) iInterfaceQueryLocalInterface;
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
                onError(parcel.readInt(), parcel.readString());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                ClassLoader classLoader = getClass().getClassLoader();
                onAvailableSaisUpdated(parcel.readArrayList(classLoader), parcel.readArrayList(classLoader));
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onServiceInterfaceAvailable(parcel.readString(), parcel.readInt());
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onMiddlewareReady();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IMbmsGroupCallSessionCallback {
            public static IMbmsGroupCallSessionCallback sDefaultImpl;
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

            @Override // android.telephony.mbms.IMbmsGroupCallSessionCallback
            public void onError(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(i, str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.mbms.IMbmsGroupCallSessionCallback
            public void onAvailableSaisUpdated(List list, List list2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeList(list);
                    parcelObtain.writeList(list2);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAvailableSaisUpdated(list, list2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.mbms.IMbmsGroupCallSessionCallback
            public void onServiceInterfaceAvailable(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onServiceInterfaceAvailable(str, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.mbms.IMbmsGroupCallSessionCallback
            public void onMiddlewareReady() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onMiddlewareReady();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMbmsGroupCallSessionCallback iMbmsGroupCallSessionCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMbmsGroupCallSessionCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMbmsGroupCallSessionCallback;
            return true;
        }

        public static IMbmsGroupCallSessionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
