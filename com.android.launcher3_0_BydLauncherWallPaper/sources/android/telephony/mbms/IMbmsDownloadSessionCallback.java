package android.telephony.mbms;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IMbmsDownloadSessionCallback extends IInterface {

    public static class Default implements IMbmsDownloadSessionCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.telephony.mbms.IMbmsDownloadSessionCallback
        public void onError(int i, String str) throws RemoteException {
        }

        @Override // android.telephony.mbms.IMbmsDownloadSessionCallback
        public void onFileServicesUpdated(List<FileServiceInfo> list) throws RemoteException {
        }

        @Override // android.telephony.mbms.IMbmsDownloadSessionCallback
        public void onMiddlewareReady() throws RemoteException {
        }
    }

    void onError(int i, String str) throws RemoteException;

    void onFileServicesUpdated(List<FileServiceInfo> list) throws RemoteException;

    void onMiddlewareReady() throws RemoteException;

    public static abstract class Stub extends Binder implements IMbmsDownloadSessionCallback {
        private static final String DESCRIPTOR = "android.telephony.mbms.IMbmsDownloadSessionCallback";
        static final int TRANSACTION_onError = 1;
        static final int TRANSACTION_onFileServicesUpdated = 2;
        static final int TRANSACTION_onMiddlewareReady = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onError";
            }
            if (i == 2) {
                return "onFileServicesUpdated";
            }
            if (i != 3) {
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

        public static IMbmsDownloadSessionCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMbmsDownloadSessionCallback)) {
                return (IMbmsDownloadSessionCallback) iInterfaceQueryLocalInterface;
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
                onFileServicesUpdated(parcel.createTypedArrayList(FileServiceInfo.CREATOR));
                return true;
            }
            if (i == 3) {
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

        private static class Proxy implements IMbmsDownloadSessionCallback {
            public static IMbmsDownloadSessionCallback sDefaultImpl;
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

            @Override // android.telephony.mbms.IMbmsDownloadSessionCallback
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

            @Override // android.telephony.mbms.IMbmsDownloadSessionCallback
            public void onFileServicesUpdated(List<FileServiceInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFileServicesUpdated(list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.mbms.IMbmsDownloadSessionCallback
            public void onMiddlewareReady() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onMiddlewareReady();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMbmsDownloadSessionCallback iMbmsDownloadSessionCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iMbmsDownloadSessionCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMbmsDownloadSessionCallback;
            return true;
        }

        public static IMbmsDownloadSessionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
