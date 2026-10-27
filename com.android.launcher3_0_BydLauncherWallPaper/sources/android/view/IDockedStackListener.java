package android.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IDockedStackListener extends IInterface {

    public static class Default implements IDockedStackListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.IDockedStackListener
        public void onAdjustedForImeChanged(boolean z, long j) throws RemoteException {
        }

        @Override // android.view.IDockedStackListener
        public void onDividerVisibilityChanged(boolean z) throws RemoteException {
        }

        @Override // android.view.IDockedStackListener
        public void onDockSideChanged(int i) throws RemoteException {
        }

        @Override // android.view.IDockedStackListener
        public void onDockedStackExistsChanged(boolean z) throws RemoteException {
        }

        @Override // android.view.IDockedStackListener
        public void onDockedStackMinimizedChanged(boolean z, long j, boolean z2) throws RemoteException {
        }
    }

    void onAdjustedForImeChanged(boolean z, long j) throws RemoteException;

    void onDividerVisibilityChanged(boolean z) throws RemoteException;

    void onDockSideChanged(int i) throws RemoteException;

    void onDockedStackExistsChanged(boolean z) throws RemoteException;

    void onDockedStackMinimizedChanged(boolean z, long j, boolean z2) throws RemoteException;

    public static abstract class Stub extends Binder implements IDockedStackListener {
        private static final String DESCRIPTOR = "android.view.IDockedStackListener";
        static final int TRANSACTION_onAdjustedForImeChanged = 4;
        static final int TRANSACTION_onDividerVisibilityChanged = 1;
        static final int TRANSACTION_onDockSideChanged = 5;
        static final int TRANSACTION_onDockedStackExistsChanged = 2;
        static final int TRANSACTION_onDockedStackMinimizedChanged = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onDividerVisibilityChanged";
            }
            if (i == 2) {
                return "onDockedStackExistsChanged";
            }
            if (i == 3) {
                return "onDockedStackMinimizedChanged";
            }
            if (i == 4) {
                return "onAdjustedForImeChanged";
            }
            if (i != 5) {
                return null;
            }
            return "onDockSideChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDockedStackListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDockedStackListener)) {
                return (IDockedStackListener) iInterfaceQueryLocalInterface;
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
                onDividerVisibilityChanged(parcel.readInt() != 0);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onDockedStackExistsChanged(parcel.readInt() != 0);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onDockedStackMinimizedChanged(parcel.readInt() != 0, parcel.readLong(), parcel.readInt() != 0);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onAdjustedForImeChanged(parcel.readInt() != 0, parcel.readLong());
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                onDockSideChanged(parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IDockedStackListener {
            public static IDockedStackListener sDefaultImpl;
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

            @Override // android.view.IDockedStackListener
            public void onDividerVisibilityChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDividerVisibilityChanged(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IDockedStackListener
            public void onDockedStackExistsChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDockedStackExistsChanged(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IDockedStackListener
            public void onDockedStackMinimizedChanged(boolean z, long j, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDockedStackMinimizedChanged(z, j, z2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IDockedStackListener
            public void onAdjustedForImeChanged(boolean z, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeLong(j);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAdjustedForImeChanged(z, j);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IDockedStackListener
            public void onDockSideChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDockSideChanged(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDockedStackListener iDockedStackListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDockedStackListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDockedStackListener;
            return true;
        }

        public static IDockedStackListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
