package android.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IAppFocusListener extends IInterface {

    public static class Default implements IAppFocusListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.IAppFocusListener
        public void onAppFocusChanged(int i, boolean z) throws RemoteException {
        }
    }

    void onAppFocusChanged(int i, boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IAppFocusListener {
        private static final String DESCRIPTOR = "android.car.IAppFocusListener";
        static final int TRANSACTION_onAppFocusChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAppFocusListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAppFocusListener)) {
                return (IAppFocusListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onAppFocusChanged(parcel.readInt(), parcel.readInt() != 0);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IAppFocusListener {
            public static IAppFocusListener sDefaultImpl;
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

            @Override // android.car.IAppFocusListener
            public void onAppFocusChanged(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAppFocusChanged(i, z);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAppFocusListener iAppFocusListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAppFocusListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAppFocusListener;
            return true;
        }

        public static IAppFocusListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
