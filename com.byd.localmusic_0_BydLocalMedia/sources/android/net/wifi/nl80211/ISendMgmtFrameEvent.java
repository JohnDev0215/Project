package android.net.wifi.nl80211;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ISendMgmtFrameEvent extends IInterface {
    public static final int SEND_MGMT_FRAME_ERROR_ALREADY_STARTED = 5;
    public static final int SEND_MGMT_FRAME_ERROR_MCS_UNSUPPORTED = 2;
    public static final int SEND_MGMT_FRAME_ERROR_NO_ACK = 3;
    public static final int SEND_MGMT_FRAME_ERROR_TIMEOUT = 4;
    public static final int SEND_MGMT_FRAME_ERROR_UNKNOWN = 1;

    public static class Default implements ISendMgmtFrameEvent {
        @Override // android.net.wifi.nl80211.ISendMgmtFrameEvent
        public void OnAck(int i) throws RemoteException {
        }

        @Override // android.net.wifi.nl80211.ISendMgmtFrameEvent
        public void OnFailure(int i) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    void OnAck(int i) throws RemoteException;

    void OnFailure(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ISendMgmtFrameEvent {
        private static final String DESCRIPTOR = "android.net.wifi.nl80211.ISendMgmtFrameEvent";
        static final int TRANSACTION_OnAck = 1;
        static final int TRANSACTION_OnFailure = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "OnAck";
            }
            if (i != 2) {
                return null;
            }
            return "OnFailure";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISendMgmtFrameEvent asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISendMgmtFrameEvent)) {
                return (ISendMgmtFrameEvent) iInterfaceQueryLocalInterface;
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
                OnAck(parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                OnFailure(parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ISendMgmtFrameEvent {
            public static ISendMgmtFrameEvent sDefaultImpl;
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

            @Override // android.net.wifi.nl80211.ISendMgmtFrameEvent
            public void OnAck(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().OnAck(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.wifi.nl80211.ISendMgmtFrameEvent
            public void OnFailure(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().OnFailure(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISendMgmtFrameEvent iSendMgmtFrameEvent) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSendMgmtFrameEvent == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSendMgmtFrameEvent;
            return true;
        }

        public static ISendMgmtFrameEvent getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
