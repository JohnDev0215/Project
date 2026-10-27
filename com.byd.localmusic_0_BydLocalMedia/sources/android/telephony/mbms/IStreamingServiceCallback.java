package android.telephony.mbms;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IStreamingServiceCallback extends IInterface {

    public static class Default implements IStreamingServiceCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.telephony.mbms.IStreamingServiceCallback
        public void onBroadcastSignalStrengthUpdated(int i) throws RemoteException {
        }

        @Override // android.telephony.mbms.IStreamingServiceCallback
        public void onError(int i, String str) throws RemoteException {
        }

        @Override // android.telephony.mbms.IStreamingServiceCallback
        public void onMediaDescriptionUpdated() throws RemoteException {
        }

        @Override // android.telephony.mbms.IStreamingServiceCallback
        public void onStreamMethodUpdated(int i) throws RemoteException {
        }

        @Override // android.telephony.mbms.IStreamingServiceCallback
        public void onStreamStateUpdated(int i, int i2) throws RemoteException {
        }
    }

    void onBroadcastSignalStrengthUpdated(int i) throws RemoteException;

    void onError(int i, String str) throws RemoteException;

    void onMediaDescriptionUpdated() throws RemoteException;

    void onStreamMethodUpdated(int i) throws RemoteException;

    void onStreamStateUpdated(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IStreamingServiceCallback {
        private static final String DESCRIPTOR = "android.telephony.mbms.IStreamingServiceCallback";
        static final int TRANSACTION_onBroadcastSignalStrengthUpdated = 4;
        static final int TRANSACTION_onError = 1;
        static final int TRANSACTION_onMediaDescriptionUpdated = 3;
        static final int TRANSACTION_onStreamMethodUpdated = 5;
        static final int TRANSACTION_onStreamStateUpdated = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onError";
            }
            if (i == 2) {
                return "onStreamStateUpdated";
            }
            if (i == 3) {
                return "onMediaDescriptionUpdated";
            }
            if (i == 4) {
                return "onBroadcastSignalStrengthUpdated";
            }
            if (i != 5) {
                return null;
            }
            return "onStreamMethodUpdated";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IStreamingServiceCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IStreamingServiceCallback)) {
                return (IStreamingServiceCallback) iInterfaceQueryLocalInterface;
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
                onStreamStateUpdated(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onMediaDescriptionUpdated();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onBroadcastSignalStrengthUpdated(parcel.readInt());
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                onStreamMethodUpdated(parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IStreamingServiceCallback {
            public static IStreamingServiceCallback sDefaultImpl;
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

            @Override // android.telephony.mbms.IStreamingServiceCallback
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

            @Override // android.telephony.mbms.IStreamingServiceCallback
            public void onStreamStateUpdated(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStreamStateUpdated(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.mbms.IStreamingServiceCallback
            public void onMediaDescriptionUpdated() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onMediaDescriptionUpdated();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.mbms.IStreamingServiceCallback
            public void onBroadcastSignalStrengthUpdated(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onBroadcastSignalStrengthUpdated(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.mbms.IStreamingServiceCallback
            public void onStreamMethodUpdated(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStreamMethodUpdated(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IStreamingServiceCallback iStreamingServiceCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iStreamingServiceCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iStreamingServiceCallback;
            return true;
        }

        public static IStreamingServiceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
