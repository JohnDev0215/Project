package android.car.input;

import android.car.input.ICarInputCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ICarInput extends IInterface {

    public static class Default implements ICarInput {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.input.ICarInput
        public void releaseInputEventCapture(ICarInputCallback iCarInputCallback, int i) throws RemoteException {
        }

        @Override // android.car.input.ICarInput
        public int requestInputEventCapture(ICarInputCallback iCarInputCallback, int i, int[] iArr, int i2) throws RemoteException {
            return 0;
        }
    }

    void releaseInputEventCapture(ICarInputCallback iCarInputCallback, int i) throws RemoteException;

    int requestInputEventCapture(ICarInputCallback iCarInputCallback, int i, int[] iArr, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements ICarInput {
        private static final String DESCRIPTOR = "android.car.input.ICarInput";
        static final int TRANSACTION_releaseInputEventCapture = 3;
        static final int TRANSACTION_requestInputEventCapture = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarInput asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICarInput)) {
                return (ICarInput) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                int iRequestInputEventCapture = requestInputEventCapture(ICarInputCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.createIntArray(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(iRequestInputEventCapture);
                return true;
            }
            if (i != 3) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            releaseInputEventCapture(ICarInputCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ICarInput {
            public static ICarInput sDefaultImpl;
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

            @Override // android.car.input.ICarInput
            public int requestInputEventCapture(ICarInputCallback iCarInputCallback, int i, int[] iArr, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iCarInputCallback != null ? iCarInputCallback.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeIntArray(iArr);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestInputEventCapture(iCarInputCallback, i, iArr, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.input.ICarInput
            public void releaseInputEventCapture(ICarInputCallback iCarInputCallback, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iCarInputCallback != null ? iCarInputCallback.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseInputEventCapture(iCarInputCallback, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarInput iCarInput) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarInput == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarInput;
            return true;
        }

        public static ICarInput getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
