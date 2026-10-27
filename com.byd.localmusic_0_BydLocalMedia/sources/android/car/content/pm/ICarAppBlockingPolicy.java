package android.car.content.pm;

import android.car.content.pm.ICarAppBlockingPolicySetter;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ICarAppBlockingPolicy extends IInterface {

    public static class Default implements ICarAppBlockingPolicy {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.content.pm.ICarAppBlockingPolicy
        public void setAppBlockingPolicySetter(ICarAppBlockingPolicySetter iCarAppBlockingPolicySetter) throws RemoteException {
        }
    }

    void setAppBlockingPolicySetter(ICarAppBlockingPolicySetter iCarAppBlockingPolicySetter) throws RemoteException;

    public static abstract class Stub extends Binder implements ICarAppBlockingPolicy {
        private static final String DESCRIPTOR = "android.car.content.pm.ICarAppBlockingPolicy";
        static final int TRANSACTION_setAppBlockingPolicySetter = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarAppBlockingPolicy asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICarAppBlockingPolicy)) {
                return (ICarAppBlockingPolicy) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                setAppBlockingPolicySetter(ICarAppBlockingPolicySetter.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ICarAppBlockingPolicy {
            public static ICarAppBlockingPolicy sDefaultImpl;
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

            @Override // android.car.content.pm.ICarAppBlockingPolicy
            public void setAppBlockingPolicySetter(ICarAppBlockingPolicySetter iCarAppBlockingPolicySetter) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iCarAppBlockingPolicySetter != null ? iCarAppBlockingPolicySetter.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setAppBlockingPolicySetter(iCarAppBlockingPolicySetter);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarAppBlockingPolicy iCarAppBlockingPolicy) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarAppBlockingPolicy == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarAppBlockingPolicy;
            return true;
        }

        public static ICarAppBlockingPolicy getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
