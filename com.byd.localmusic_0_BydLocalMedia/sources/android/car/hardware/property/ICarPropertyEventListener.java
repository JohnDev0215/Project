package android.car.hardware.property;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ICarPropertyEventListener extends IInterface {

    public static class Default implements ICarPropertyEventListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.hardware.property.ICarPropertyEventListener
        public void onEvent(List<CarPropertyEvent> list) throws RemoteException {
        }
    }

    void onEvent(List<CarPropertyEvent> list) throws RemoteException;

    public static abstract class Stub extends Binder implements ICarPropertyEventListener {
        private static final String DESCRIPTOR = "android.car.hardware.property.ICarPropertyEventListener";
        static final int TRANSACTION_onEvent = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarPropertyEventListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICarPropertyEventListener)) {
                return (ICarPropertyEventListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onEvent(parcel.createTypedArrayList(CarPropertyEvent.CREATOR));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ICarPropertyEventListener {
            public static ICarPropertyEventListener sDefaultImpl;
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

            @Override // android.car.hardware.property.ICarPropertyEventListener
            public void onEvent(List<CarPropertyEvent> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onEvent(list);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarPropertyEventListener iCarPropertyEventListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarPropertyEventListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarPropertyEventListener;
            return true;
        }

        public static ICarPropertyEventListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
