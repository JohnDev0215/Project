package android.media.tv;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ITvInputHardwareCallback extends IInterface {

    public static class Default implements ITvInputHardwareCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.tv.ITvInputHardwareCallback
        public void onReleased() throws RemoteException {
        }

        @Override // android.media.tv.ITvInputHardwareCallback
        public void onStreamConfigChanged(TvStreamConfig[] tvStreamConfigArr) throws RemoteException {
        }
    }

    void onReleased() throws RemoteException;

    void onStreamConfigChanged(TvStreamConfig[] tvStreamConfigArr) throws RemoteException;

    public static abstract class Stub extends Binder implements ITvInputHardwareCallback {
        private static final String DESCRIPTOR = "android.media.tv.ITvInputHardwareCallback";
        static final int TRANSACTION_onReleased = 1;
        static final int TRANSACTION_onStreamConfigChanged = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onReleased";
            }
            if (i != 2) {
                return null;
            }
            return "onStreamConfigChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITvInputHardwareCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITvInputHardwareCallback)) {
                return (ITvInputHardwareCallback) iInterfaceQueryLocalInterface;
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
                onReleased();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onStreamConfigChanged((TvStreamConfig[]) parcel.createTypedArray(TvStreamConfig.CREATOR));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ITvInputHardwareCallback {
            public static ITvInputHardwareCallback sDefaultImpl;
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

            @Override // android.media.tv.ITvInputHardwareCallback
            public void onReleased() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onReleased();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputHardwareCallback
            public void onStreamConfigChanged(TvStreamConfig[] tvStreamConfigArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(tvStreamConfigArr, 0);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStreamConfigChanged(tvStreamConfigArr);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITvInputHardwareCallback iTvInputHardwareCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTvInputHardwareCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTvInputHardwareCallback;
            return true;
        }

        public static ITvInputHardwareCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
