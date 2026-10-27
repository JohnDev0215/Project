package android.app.admin;

import android.app.admin.IKeyguardCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IKeyguardClient extends IInterface {

    public static class Default implements IKeyguardClient {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.admin.IKeyguardClient
        public void onCreateKeyguardSurface(IBinder iBinder, IKeyguardCallback iKeyguardCallback) throws RemoteException {
        }
    }

    void onCreateKeyguardSurface(IBinder iBinder, IKeyguardCallback iKeyguardCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IKeyguardClient {
        private static final String DESCRIPTOR = "android.app.admin.IKeyguardClient";
        static final int TRANSACTION_onCreateKeyguardSurface = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onCreateKeyguardSurface";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IKeyguardClient asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IKeyguardClient)) {
                return (IKeyguardClient) iInterfaceQueryLocalInterface;
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
                onCreateKeyguardSurface(parcel.readStrongBinder(), IKeyguardCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IKeyguardClient {
            public static IKeyguardClient sDefaultImpl;
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

            @Override // android.app.admin.IKeyguardClient
            public void onCreateKeyguardSurface(IBinder iBinder, IKeyguardCallback iKeyguardCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeStrongBinder(iKeyguardCallback != null ? iKeyguardCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCreateKeyguardSurface(iBinder, iKeyguardCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeyguardClient iKeyguardClient) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iKeyguardClient == null) {
                return false;
            }
            Proxy.sDefaultImpl = iKeyguardClient;
            return true;
        }

        public static IKeyguardClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
