package android.service.controls;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.controls.IControlsActionCallback;
import android.service.controls.IControlsSubscriber;
import android.service.controls.actions.ControlActionWrapper;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface IControlsProvider extends IInterface {

    public static class Default implements IControlsProvider {
        @Override // android.service.controls.IControlsProvider
        public void action(String str, ControlActionWrapper controlActionWrapper, IControlsActionCallback iControlsActionCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.controls.IControlsProvider
        public void load(IControlsSubscriber iControlsSubscriber) throws RemoteException {
        }

        @Override // android.service.controls.IControlsProvider
        public void loadSuggested(IControlsSubscriber iControlsSubscriber) throws RemoteException {
        }

        @Override // android.service.controls.IControlsProvider
        public void subscribe(List<String> list, IControlsSubscriber iControlsSubscriber) throws RemoteException {
        }
    }

    void action(String str, ControlActionWrapper controlActionWrapper, IControlsActionCallback iControlsActionCallback) throws RemoteException;

    void load(IControlsSubscriber iControlsSubscriber) throws RemoteException;

    void loadSuggested(IControlsSubscriber iControlsSubscriber) throws RemoteException;

    void subscribe(List<String> list, IControlsSubscriber iControlsSubscriber) throws RemoteException;

    public static abstract class Stub extends Binder implements IControlsProvider {
        private static final String DESCRIPTOR = "android.service.controls.IControlsProvider";
        static final int TRANSACTION_action = 4;
        static final int TRANSACTION_load = 1;
        static final int TRANSACTION_loadSuggested = 2;
        static final int TRANSACTION_subscribe = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "load";
            }
            if (i == 2) {
                return "loadSuggested";
            }
            if (i == 3) {
                return "subscribe";
            }
            if (i != 4) {
                return null;
            }
            return "action";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IControlsProvider asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IControlsProvider)) {
                return (IControlsProvider) iInterfaceQueryLocalInterface;
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
                load(IControlsSubscriber.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                loadSuggested(IControlsSubscriber.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                subscribe(parcel.createStringArrayList(), IControlsSubscriber.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                action(parcel.readString(), parcel.readInt() != 0 ? ControlActionWrapper.CREATOR.createFromParcel(parcel) : null, IControlsActionCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IControlsProvider {
            public static IControlsProvider sDefaultImpl;
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

            @Override // android.service.controls.IControlsProvider
            public void load(IControlsSubscriber iControlsSubscriber) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iControlsSubscriber != null ? iControlsSubscriber.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().load(iControlsSubscriber);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.controls.IControlsProvider
            public void loadSuggested(IControlsSubscriber iControlsSubscriber) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iControlsSubscriber != null ? iControlsSubscriber.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().loadSuggested(iControlsSubscriber);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.controls.IControlsProvider
            public void subscribe(List<String> list, IControlsSubscriber iControlsSubscriber) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    parcelObtain.writeStrongBinder(iControlsSubscriber != null ? iControlsSubscriber.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().subscribe(list, iControlsSubscriber);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.controls.IControlsProvider
            public void action(String str, ControlActionWrapper controlActionWrapper, IControlsActionCallback iControlsActionCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (controlActionWrapper != null) {
                        parcelObtain.writeInt(1);
                        controlActionWrapper.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iControlsActionCallback != null ? iControlsActionCallback.asBinder() : null);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().action(str, controlActionWrapper, iControlsActionCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IControlsProvider iControlsProvider) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iControlsProvider == null) {
                return false;
            }
            Proxy.sDefaultImpl = iControlsProvider;
            return true;
        }

        public static IControlsProvider getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
