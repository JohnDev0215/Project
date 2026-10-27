package android.view.contentcapture;

import android.content.ContentCaptureOptions;
import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IContentCaptureDirectManager extends IInterface {

    public static class Default implements IContentCaptureDirectManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.contentcapture.IContentCaptureDirectManager
        public void sendEvents(ParceledListSlice parceledListSlice, int i, ContentCaptureOptions contentCaptureOptions) throws RemoteException {
        }
    }

    void sendEvents(ParceledListSlice parceledListSlice, int i, ContentCaptureOptions contentCaptureOptions) throws RemoteException;

    public static abstract class Stub extends Binder implements IContentCaptureDirectManager {
        private static final String DESCRIPTOR = "android.view.contentcapture.IContentCaptureDirectManager";
        static final int TRANSACTION_sendEvents = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "sendEvents";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IContentCaptureDirectManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IContentCaptureDirectManager)) {
                return (IContentCaptureDirectManager) iInterfaceQueryLocalInterface;
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
                sendEvents(parcel.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0 ? ContentCaptureOptions.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IContentCaptureDirectManager {
            public static IContentCaptureDirectManager sDefaultImpl;
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

            @Override // android.view.contentcapture.IContentCaptureDirectManager
            public void sendEvents(ParceledListSlice parceledListSlice, int i, ContentCaptureOptions contentCaptureOptions) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parceledListSlice != null) {
                        parcelObtain.writeInt(1);
                        parceledListSlice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (contentCaptureOptions != null) {
                        parcelObtain.writeInt(1);
                        contentCaptureOptions.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendEvents(parceledListSlice, i, contentCaptureOptions);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IContentCaptureDirectManager iContentCaptureDirectManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iContentCaptureDirectManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iContentCaptureDirectManager;
            return true;
        }

        public static IContentCaptureDirectManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
