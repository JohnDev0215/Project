package android.media.session;

import android.media.session.MediaSession;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.KeyEvent;

/* JADX INFO: loaded from: classes2.dex */
public interface IOnMediaKeyEventDispatchedListener extends IInterface {

    public static class Default implements IOnMediaKeyEventDispatchedListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.session.IOnMediaKeyEventDispatchedListener
        public void onMediaKeyEventDispatched(KeyEvent keyEvent, String str, MediaSession.Token token) throws RemoteException {
        }
    }

    void onMediaKeyEventDispatched(KeyEvent keyEvent, String str, MediaSession.Token token) throws RemoteException;

    public static abstract class Stub extends Binder implements IOnMediaKeyEventDispatchedListener {
        private static final String DESCRIPTOR = "android.media.session.IOnMediaKeyEventDispatchedListener";
        static final int TRANSACTION_onMediaKeyEventDispatched = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onMediaKeyEventDispatched";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOnMediaKeyEventDispatchedListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IOnMediaKeyEventDispatchedListener)) {
                return (IOnMediaKeyEventDispatchedListener) iInterfaceQueryLocalInterface;
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
                onMediaKeyEventDispatched(parcel.readInt() != 0 ? KeyEvent.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0 ? MediaSession.Token.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IOnMediaKeyEventDispatchedListener {
            public static IOnMediaKeyEventDispatchedListener sDefaultImpl;
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

            @Override // android.media.session.IOnMediaKeyEventDispatchedListener
            public void onMediaKeyEventDispatched(KeyEvent keyEvent, String str, MediaSession.Token token) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (keyEvent != null) {
                        parcelObtain.writeInt(1);
                        keyEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (token != null) {
                        parcelObtain.writeInt(1);
                        token.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onMediaKeyEventDispatched(keyEvent, str, token);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOnMediaKeyEventDispatchedListener iOnMediaKeyEventDispatchedListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iOnMediaKeyEventDispatchedListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iOnMediaKeyEventDispatchedListener;
            return true;
        }

        public static IOnMediaKeyEventDispatchedListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
