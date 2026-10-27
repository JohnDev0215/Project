package android.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IPlaybackConfigDispatcher extends IInterface {

    public static class Default implements IPlaybackConfigDispatcher {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.IPlaybackConfigDispatcher
        public void dispatchPlaybackConfigChange(List<AudioPlaybackConfiguration> list, boolean z) throws RemoteException {
        }
    }

    void dispatchPlaybackConfigChange(List<AudioPlaybackConfiguration> list, boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IPlaybackConfigDispatcher {
        private static final String DESCRIPTOR = "android.media.IPlaybackConfigDispatcher";
        static final int TRANSACTION_dispatchPlaybackConfigChange = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "dispatchPlaybackConfigChange";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPlaybackConfigDispatcher asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPlaybackConfigDispatcher)) {
                return (IPlaybackConfigDispatcher) iInterfaceQueryLocalInterface;
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
                dispatchPlaybackConfigChange(parcel.createTypedArrayList(AudioPlaybackConfiguration.CREATOR), parcel.readInt() != 0);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IPlaybackConfigDispatcher {
            public static IPlaybackConfigDispatcher sDefaultImpl;
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

            @Override // android.media.IPlaybackConfigDispatcher
            public void dispatchPlaybackConfigChange(List<AudioPlaybackConfiguration> list, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().dispatchPlaybackConfigChange(list, z);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPlaybackConfigDispatcher iPlaybackConfigDispatcher) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iPlaybackConfigDispatcher == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPlaybackConfigDispatcher;
            return true;
        }

        public static IPlaybackConfigDispatcher getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
