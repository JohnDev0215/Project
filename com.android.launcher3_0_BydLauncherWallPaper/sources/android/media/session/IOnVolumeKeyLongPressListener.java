package android.media.session;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.KeyEvent;

/* JADX INFO: loaded from: classes2.dex */
public interface IOnVolumeKeyLongPressListener extends IInterface {

    public static class Default implements IOnVolumeKeyLongPressListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.session.IOnVolumeKeyLongPressListener
        public void onVolumeKeyLongPress(KeyEvent keyEvent) throws RemoteException {
        }
    }

    void onVolumeKeyLongPress(KeyEvent keyEvent) throws RemoteException;

    public static abstract class Stub extends Binder implements IOnVolumeKeyLongPressListener {
        private static final String DESCRIPTOR = "android.media.session.IOnVolumeKeyLongPressListener";
        static final int TRANSACTION_onVolumeKeyLongPress = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onVolumeKeyLongPress";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOnVolumeKeyLongPressListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IOnVolumeKeyLongPressListener)) {
                return (IOnVolumeKeyLongPressListener) iInterfaceQueryLocalInterface;
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
                onVolumeKeyLongPress(parcel.readInt() != 0 ? KeyEvent.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IOnVolumeKeyLongPressListener {
            public static IOnVolumeKeyLongPressListener sDefaultImpl;
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

            @Override // android.media.session.IOnVolumeKeyLongPressListener
            public void onVolumeKeyLongPress(KeyEvent keyEvent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (keyEvent != null) {
                        parcelObtain.writeInt(1);
                        keyEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onVolumeKeyLongPress(keyEvent);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOnVolumeKeyLongPressListener iOnVolumeKeyLongPressListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iOnVolumeKeyLongPressListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iOnVolumeKeyLongPressListener;
            return true;
        }

        public static IOnVolumeKeyLongPressListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
