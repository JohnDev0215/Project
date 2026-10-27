package android.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.IGestureCallback;

/* JADX INFO: loaded from: classes3.dex */
public interface IGestureManager extends IInterface {

    public static class Default implements IGestureManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.IGestureManager
        public void sendMonitorStateToClients(int i) throws RemoteException {
        }

        @Override // android.view.IGestureManager
        public void sendMotionEventToClients(MotionEvent motionEvent) throws RemoteException {
        }

        @Override // android.view.IGestureManager
        public void setMonitorMode(boolean z) throws RemoteException {
        }

        @Override // android.view.IGestureManager
        public void subscribeGestureCallback(IGestureCallback iGestureCallback) throws RemoteException {
        }

        @Override // android.view.IGestureManager
        public void unsubscribeGestureCallback(IGestureCallback iGestureCallback) throws RemoteException {
        }
    }

    void sendMonitorStateToClients(int i) throws RemoteException;

    void sendMotionEventToClients(MotionEvent motionEvent) throws RemoteException;

    void setMonitorMode(boolean z) throws RemoteException;

    void subscribeGestureCallback(IGestureCallback iGestureCallback) throws RemoteException;

    void unsubscribeGestureCallback(IGestureCallback iGestureCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IGestureManager {
        private static final String DESCRIPTOR = "android.view.IGestureManager";
        static final int TRANSACTION_sendMonitorStateToClients = 4;
        static final int TRANSACTION_sendMotionEventToClients = 3;
        static final int TRANSACTION_setMonitorMode = 5;
        static final int TRANSACTION_subscribeGestureCallback = 1;
        static final int TRANSACTION_unsubscribeGestureCallback = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "subscribeGestureCallback";
            }
            if (i == 2) {
                return "unsubscribeGestureCallback";
            }
            if (i == 3) {
                return "sendMotionEventToClients";
            }
            if (i == 4) {
                return "sendMonitorStateToClients";
            }
            if (i != 5) {
                return null;
            }
            return "setMonitorMode";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGestureManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IGestureManager)) {
                return (IGestureManager) iInterfaceQueryLocalInterface;
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
                subscribeGestureCallback(IGestureCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                unsubscribeGestureCallback(IGestureCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                sendMotionEventToClients(parcel.readInt() != 0 ? MotionEvent.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                sendMonitorStateToClients(parcel.readInt());
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                setMonitorMode(parcel.readInt() != 0);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IGestureManager {
            public static IGestureManager sDefaultImpl;
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

            @Override // android.view.IGestureManager
            public void subscribeGestureCallback(IGestureCallback iGestureCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGestureCallback != null ? iGestureCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().subscribeGestureCallback(iGestureCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IGestureManager
            public void unsubscribeGestureCallback(IGestureCallback iGestureCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGestureCallback != null ? iGestureCallback.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unsubscribeGestureCallback(iGestureCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IGestureManager
            public void sendMotionEventToClients(MotionEvent motionEvent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (motionEvent != null) {
                        parcelObtain.writeInt(1);
                        motionEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendMotionEventToClients(motionEvent);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IGestureManager
            public void sendMonitorStateToClients(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendMonitorStateToClients(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IGestureManager
            public void setMonitorMode(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setMonitorMode(z);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGestureManager iGestureManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iGestureManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iGestureManager;
            return true;
        }

        public static IGestureManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
