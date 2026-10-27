package android.telephony.mbms;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IDownloadStatusListener extends IInterface {

    public static class Default implements IDownloadStatusListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.telephony.mbms.IDownloadStatusListener
        public void onStatusUpdated(DownloadRequest downloadRequest, FileInfo fileInfo, int i) throws RemoteException {
        }
    }

    void onStatusUpdated(DownloadRequest downloadRequest, FileInfo fileInfo, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IDownloadStatusListener {
        private static final String DESCRIPTOR = "android.telephony.mbms.IDownloadStatusListener";
        static final int TRANSACTION_onStatusUpdated = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onStatusUpdated";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadStatusListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDownloadStatusListener)) {
                return (IDownloadStatusListener) iInterfaceQueryLocalInterface;
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
            onStatusUpdated(parcel.readInt() != 0 ? DownloadRequest.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? FileInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IDownloadStatusListener {
            public static IDownloadStatusListener sDefaultImpl;
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

            @Override // android.telephony.mbms.IDownloadStatusListener
            public void onStatusUpdated(DownloadRequest downloadRequest, FileInfo fileInfo, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (downloadRequest != null) {
                        parcelObtain.writeInt(1);
                        downloadRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (fileInfo != null) {
                        parcelObtain.writeInt(1);
                        fileInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStatusUpdated(downloadRequest, fileInfo, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadStatusListener iDownloadStatusListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDownloadStatusListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDownloadStatusListener;
            return true;
        }

        public static IDownloadStatusListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
