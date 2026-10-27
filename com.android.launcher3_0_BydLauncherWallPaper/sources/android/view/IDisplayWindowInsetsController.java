package android.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IDisplayWindowInsetsController extends IInterface {

    public static class Default implements IDisplayWindowInsetsController {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.IDisplayWindowInsetsController
        public void hideInsets(int i, boolean z) throws RemoteException {
        }

        @Override // android.view.IDisplayWindowInsetsController
        public void insetsChanged(InsetsState insetsState) throws RemoteException {
        }

        @Override // android.view.IDisplayWindowInsetsController
        public void insetsControlChanged(InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) throws RemoteException {
        }

        @Override // android.view.IDisplayWindowInsetsController
        public void showInsets(int i, boolean z) throws RemoteException {
        }

        @Override // android.view.IDisplayWindowInsetsController
        public void topFocusedWindowChanged(String str) throws RemoteException {
        }
    }

    void hideInsets(int i, boolean z) throws RemoteException;

    void insetsChanged(InsetsState insetsState) throws RemoteException;

    void insetsControlChanged(InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) throws RemoteException;

    void showInsets(int i, boolean z) throws RemoteException;

    void topFocusedWindowChanged(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IDisplayWindowInsetsController {
        private static final String DESCRIPTOR = "android.view.IDisplayWindowInsetsController";
        static final int TRANSACTION_hideInsets = 5;
        static final int TRANSACTION_insetsChanged = 2;
        static final int TRANSACTION_insetsControlChanged = 3;
        static final int TRANSACTION_showInsets = 4;
        static final int TRANSACTION_topFocusedWindowChanged = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "topFocusedWindowChanged";
            }
            if (i == 2) {
                return "insetsChanged";
            }
            if (i == 3) {
                return "insetsControlChanged";
            }
            if (i == 4) {
                return "showInsets";
            }
            if (i != 5) {
                return null;
            }
            return "hideInsets";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDisplayWindowInsetsController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDisplayWindowInsetsController)) {
                return (IDisplayWindowInsetsController) iInterfaceQueryLocalInterface;
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
                topFocusedWindowChanged(parcel.readString());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                insetsChanged(parcel.readInt() != 0 ? InsetsState.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                insetsControlChanged(parcel.readInt() != 0 ? InsetsState.CREATOR.createFromParcel(parcel) : null, (InsetsSourceControl[]) parcel.createTypedArray(InsetsSourceControl.CREATOR));
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                showInsets(parcel.readInt(), parcel.readInt() != 0);
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                hideInsets(parcel.readInt(), parcel.readInt() != 0);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IDisplayWindowInsetsController {
            public static IDisplayWindowInsetsController sDefaultImpl;
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

            @Override // android.view.IDisplayWindowInsetsController
            public void topFocusedWindowChanged(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().topFocusedWindowChanged(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IDisplayWindowInsetsController
            public void insetsChanged(InsetsState insetsState) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (insetsState != null) {
                        parcelObtain.writeInt(1);
                        insetsState.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().insetsChanged(insetsState);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IDisplayWindowInsetsController
            public void insetsControlChanged(InsetsState insetsState, InsetsSourceControl[] insetsSourceControlArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (insetsState != null) {
                        parcelObtain.writeInt(1);
                        insetsState.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedArray(insetsSourceControlArr, 0);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().insetsControlChanged(insetsState, insetsSourceControlArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IDisplayWindowInsetsController
            public void showInsets(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().showInsets(i, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IDisplayWindowInsetsController
            public void hideInsets(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().hideInsets(i, z);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDisplayWindowInsetsController iDisplayWindowInsetsController) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDisplayWindowInsetsController == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDisplayWindowInsetsController;
            return true;
        }

        public static IDisplayWindowInsetsController getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
