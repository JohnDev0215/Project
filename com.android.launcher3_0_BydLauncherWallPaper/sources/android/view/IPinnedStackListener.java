package android.view;

import android.content.ComponentName;
import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.IPinnedStackController;

/* JADX INFO: loaded from: classes3.dex */
public interface IPinnedStackListener extends IInterface {

    public static class Default implements IPinnedStackListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.IPinnedStackListener
        public void onActionsChanged(ParceledListSlice parceledListSlice) throws RemoteException {
        }

        @Override // android.view.IPinnedStackListener
        public void onActivityHidden(ComponentName componentName) throws RemoteException {
        }

        @Override // android.view.IPinnedStackListener
        public void onAspectRatioChanged(float f) throws RemoteException {
        }

        @Override // android.view.IPinnedStackListener
        public void onConfigurationChanged() throws RemoteException {
        }

        @Override // android.view.IPinnedStackListener
        public void onDisplayInfoChanged(DisplayInfo displayInfo) throws RemoteException {
        }

        @Override // android.view.IPinnedStackListener
        public void onImeVisibilityChanged(boolean z, int i) throws RemoteException {
        }

        @Override // android.view.IPinnedStackListener
        public void onListenerRegistered(IPinnedStackController iPinnedStackController) throws RemoteException {
        }

        @Override // android.view.IPinnedStackListener
        public void onMovementBoundsChanged(boolean z) throws RemoteException {
        }
    }

    void onActionsChanged(ParceledListSlice parceledListSlice) throws RemoteException;

    void onActivityHidden(ComponentName componentName) throws RemoteException;

    void onAspectRatioChanged(float f) throws RemoteException;

    void onConfigurationChanged() throws RemoteException;

    void onDisplayInfoChanged(DisplayInfo displayInfo) throws RemoteException;

    void onImeVisibilityChanged(boolean z, int i) throws RemoteException;

    void onListenerRegistered(IPinnedStackController iPinnedStackController) throws RemoteException;

    void onMovementBoundsChanged(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IPinnedStackListener {
        private static final String DESCRIPTOR = "android.view.IPinnedStackListener";
        static final int TRANSACTION_onActionsChanged = 4;
        static final int TRANSACTION_onActivityHidden = 5;
        static final int TRANSACTION_onAspectRatioChanged = 8;
        static final int TRANSACTION_onConfigurationChanged = 7;
        static final int TRANSACTION_onDisplayInfoChanged = 6;
        static final int TRANSACTION_onImeVisibilityChanged = 3;
        static final int TRANSACTION_onListenerRegistered = 1;
        static final int TRANSACTION_onMovementBoundsChanged = 2;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onListenerRegistered";
                case 2:
                    return "onMovementBoundsChanged";
                case 3:
                    return "onImeVisibilityChanged";
                case 4:
                    return "onActionsChanged";
                case 5:
                    return "onActivityHidden";
                case 6:
                    return "onDisplayInfoChanged";
                case 7:
                    return "onConfigurationChanged";
                case 8:
                    return "onAspectRatioChanged";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPinnedStackListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPinnedStackListener)) {
                return (IPinnedStackListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    onListenerRegistered(IPinnedStackController.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onMovementBoundsChanged(parcel.readInt() != 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onImeVisibilityChanged(parcel.readInt() != 0, parcel.readInt());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onActionsChanged(parcel.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onActivityHidden(parcel.readInt() != 0 ? ComponentName.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDisplayInfoChanged(parcel.readInt() != 0 ? DisplayInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onConfigurationChanged();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAspectRatioChanged(parcel.readFloat());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IPinnedStackListener {
            public static IPinnedStackListener sDefaultImpl;
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

            @Override // android.view.IPinnedStackListener
            public void onListenerRegistered(IPinnedStackController iPinnedStackController) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iPinnedStackController != null ? iPinnedStackController.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onListenerRegistered(iPinnedStackController);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IPinnedStackListener
            public void onMovementBoundsChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onMovementBoundsChanged(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IPinnedStackListener
            public void onImeVisibilityChanged(boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onImeVisibilityChanged(z, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IPinnedStackListener
            public void onActionsChanged(ParceledListSlice parceledListSlice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parceledListSlice != null) {
                        parcelObtain.writeInt(1);
                        parceledListSlice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onActionsChanged(parceledListSlice);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IPinnedStackListener
            public void onActivityHidden(ComponentName componentName) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (componentName != null) {
                        parcelObtain.writeInt(1);
                        componentName.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onActivityHidden(componentName);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IPinnedStackListener
            public void onDisplayInfoChanged(DisplayInfo displayInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (displayInfo != null) {
                        parcelObtain.writeInt(1);
                        displayInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDisplayInfoChanged(displayInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IPinnedStackListener
            public void onConfigurationChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConfigurationChanged();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IPinnedStackListener
            public void onAspectRatioChanged(float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeFloat(f);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAspectRatioChanged(f);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPinnedStackListener iPinnedStackListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iPinnedStackListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPinnedStackListener;
            return true;
        }

        public static IPinnedStackListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
