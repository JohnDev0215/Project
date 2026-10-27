package android.car.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface ICarBalanceCallback extends IInterface {

    public static class Default implements ICarBalanceCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.car.media.ICarBalanceCallback
        public void onBalanceTowardRightLevelChanged() {
        }

        @Override // android.car.media.ICarBalanceCallback
        public void onFadeTowardFrontLevelChanged() {
        }
    }

    public static abstract class Stub extends Binder implements ICarBalanceCallback {
        private static final String DESCRIPTOR = "android.car.media.ICarBalanceCallback";
        static final int TRANSACTION_onBalanceTowardRightLevelChanged = 1;
        static final int TRANSACTION_onFadeTowardFrontLevelChanged = 2;

        private static class Proxy implements ICarBalanceCallback {
            public static ICarBalanceCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.car.media.ICarBalanceCallback
            public void onBalanceTowardRightLevelChanged() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onBalanceTowardRightLevelChanged();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.car.media.ICarBalanceCallback
            public void onFadeTowardFrontLevelChanged() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFadeTowardFrontLevelChanged();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarBalanceCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ICarBalanceCallback)) ? new Proxy(iBinder) : (ICarBalanceCallback) iInterfaceQueryLocalInterface;
        }

        public static ICarBalanceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ICarBalanceCallback iCarBalanceCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarBalanceCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarBalanceCallback;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onBalanceTowardRightLevelChanged();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onFadeTowardFrontLevelChanged();
                return true;
            }
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    void onBalanceTowardRightLevelChanged();

    void onFadeTowardFrontLevelChanged();
}
