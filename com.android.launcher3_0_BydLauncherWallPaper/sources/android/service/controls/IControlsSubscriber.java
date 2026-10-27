package android.service.controls;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.controls.IControlsSubscription;

/* JADX INFO: loaded from: classes3.dex */
public interface IControlsSubscriber extends IInterface {

    public static class Default implements IControlsSubscriber {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.controls.IControlsSubscriber
        public void onComplete(IBinder iBinder) throws RemoteException {
        }

        @Override // android.service.controls.IControlsSubscriber
        public void onError(IBinder iBinder, String str) throws RemoteException {
        }

        @Override // android.service.controls.IControlsSubscriber
        public void onNext(IBinder iBinder, Control control) throws RemoteException {
        }

        @Override // android.service.controls.IControlsSubscriber
        public void onSubscribe(IBinder iBinder, IControlsSubscription iControlsSubscription) throws RemoteException {
        }
    }

    void onComplete(IBinder iBinder) throws RemoteException;

    void onError(IBinder iBinder, String str) throws RemoteException;

    void onNext(IBinder iBinder, Control control) throws RemoteException;

    void onSubscribe(IBinder iBinder, IControlsSubscription iControlsSubscription) throws RemoteException;

    public static abstract class Stub extends Binder implements IControlsSubscriber {
        private static final String DESCRIPTOR = "android.service.controls.IControlsSubscriber";
        static final int TRANSACTION_onComplete = 4;
        static final int TRANSACTION_onError = 3;
        static final int TRANSACTION_onNext = 2;
        static final int TRANSACTION_onSubscribe = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onSubscribe";
            }
            if (i == 2) {
                return "onNext";
            }
            if (i == 3) {
                return "onError";
            }
            if (i != 4) {
                return null;
            }
            return "onComplete";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IControlsSubscriber asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IControlsSubscriber)) {
                return (IControlsSubscriber) iInterfaceQueryLocalInterface;
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
                onSubscribe(parcel.readStrongBinder(), IControlsSubscription.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onNext(parcel.readStrongBinder(), parcel.readInt() != 0 ? Control.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onError(parcel.readStrongBinder(), parcel.readString());
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onComplete(parcel.readStrongBinder());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IControlsSubscriber {
            public static IControlsSubscriber sDefaultImpl;
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

            @Override // android.service.controls.IControlsSubscriber
            public void onSubscribe(IBinder iBinder, IControlsSubscription iControlsSubscription) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeStrongBinder(iControlsSubscription != null ? iControlsSubscription.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSubscribe(iBinder, iControlsSubscription);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.controls.IControlsSubscriber
            public void onNext(IBinder iBinder, Control control) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (control != null) {
                        parcelObtain.writeInt(1);
                        control.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNext(iBinder, control);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.controls.IControlsSubscriber
            public void onError(IBinder iBinder, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(iBinder, str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.controls.IControlsSubscriber
            public void onComplete(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onComplete(iBinder);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IControlsSubscriber iControlsSubscriber) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iControlsSubscriber == null) {
                return false;
            }
            Proxy.sDefaultImpl = iControlsSubscriber;
            return true;
        }

        public static IControlsSubscriber getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
