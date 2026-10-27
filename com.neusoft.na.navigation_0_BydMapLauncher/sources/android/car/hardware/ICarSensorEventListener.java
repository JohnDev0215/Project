package android.car.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ICarSensorEventListener extends IInterface {

    public static class Default implements ICarSensorEventListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.hardware.ICarSensorEventListener
        public void onSensorChanged(List<CarSensorEvent> list) throws RemoteException {
        }
    }

    void onSensorChanged(List<CarSensorEvent> list) throws RemoteException;

    public static abstract class Stub extends Binder implements ICarSensorEventListener {
        private static final String DESCRIPTOR = "android.car.hardware.ICarSensorEventListener";
        static final int TRANSACTION_onSensorChanged = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarSensorEventListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICarSensorEventListener)) {
                return (ICarSensorEventListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onSensorChanged(parcel.createTypedArrayList(CarSensorEvent.CREATOR));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ICarSensorEventListener {
            public static ICarSensorEventListener sDefaultImpl;
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

            @Override // android.car.hardware.ICarSensorEventListener
            public void onSensorChanged(List<CarSensorEvent> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSensorChanged(list);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarSensorEventListener iCarSensorEventListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarSensorEventListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarSensorEventListener;
            return true;
        }

        public static ICarSensorEventListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
