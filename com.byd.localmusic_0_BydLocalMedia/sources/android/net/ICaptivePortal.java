package android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ICaptivePortal extends IInterface {

    public static class Default implements ICaptivePortal {
        @Override // android.net.ICaptivePortal
        public void appRequest(int i) throws RemoteException {
        }

        @Override // android.net.ICaptivePortal
        public void appResponse(int i) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.ICaptivePortal
        public void logEvent(int i, String str) throws RemoteException {
        }
    }

    void appRequest(int i) throws RemoteException;

    void appResponse(int i) throws RemoteException;

    void logEvent(int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements ICaptivePortal {
        private static final String DESCRIPTOR = "android.net.ICaptivePortal";
        static final int TRANSACTION_appRequest = 1;
        static final int TRANSACTION_appResponse = 2;
        static final int TRANSACTION_logEvent = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "appRequest";
            }
            if (i == 2) {
                return "appResponse";
            }
            if (i != 3) {
                return null;
            }
            return "logEvent";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICaptivePortal asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICaptivePortal)) {
                return (ICaptivePortal) iInterfaceQueryLocalInterface;
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
                appRequest(parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                appResponse(parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                logEvent(parcel.readInt(), parcel.readString());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ICaptivePortal {
            public static ICaptivePortal sDefaultImpl;
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

            @Override // android.net.ICaptivePortal
            public void appRequest(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().appRequest(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ICaptivePortal
            public void appResponse(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().appResponse(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ICaptivePortal
            public void logEvent(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().logEvent(i, str);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICaptivePortal iCaptivePortal) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCaptivePortal == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCaptivePortal;
            return true;
        }

        public static ICaptivePortal getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
