package android.car.media;

import android.media.AudioFocusInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ICarFocusCallback extends IInterface {

    public static class Default implements ICarFocusCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.media.ICarFocusCallback
        public void onFocusChanged(int i, List<AudioFocusInfo> list) throws RemoteException {
        }
    }

    void onFocusChanged(int i, List<AudioFocusInfo> list) throws RemoteException;

    public static abstract class Stub extends Binder implements ICarFocusCallback {
        private static final String DESCRIPTOR = "android.car.media.ICarFocusCallback";
        static final int TRANSACTION_onFocusChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarFocusCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICarFocusCallback)) {
                return (ICarFocusCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onFocusChanged(parcel.readInt(), parcel.createTypedArrayList(AudioFocusInfo.CREATOR));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ICarFocusCallback {
            public static ICarFocusCallback sDefaultImpl;
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

            @Override // android.car.media.ICarFocusCallback
            public void onFocusChanged(int i, List<AudioFocusInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFocusChanged(i, list);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarFocusCallback iCarFocusCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarFocusCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarFocusCallback;
            return true;
        }

        public static ICarFocusCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
