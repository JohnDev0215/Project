package android.window;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.SurfaceControl;

/* JADX INFO: loaded from: classes3.dex */
public interface IDisplayAreaOrganizer extends IInterface {

    public static class Default implements IDisplayAreaOrganizer {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.window.IDisplayAreaOrganizer
        public void onDisplayAreaAppeared(DisplayAreaInfo displayAreaInfo, SurfaceControl surfaceControl) throws RemoteException {
        }

        @Override // android.window.IDisplayAreaOrganizer
        public void onDisplayAreaInfoChanged(DisplayAreaInfo displayAreaInfo) throws RemoteException {
        }

        @Override // android.window.IDisplayAreaOrganizer
        public void onDisplayAreaVanished(DisplayAreaInfo displayAreaInfo) throws RemoteException {
        }
    }

    void onDisplayAreaAppeared(DisplayAreaInfo displayAreaInfo, SurfaceControl surfaceControl) throws RemoteException;

    void onDisplayAreaInfoChanged(DisplayAreaInfo displayAreaInfo) throws RemoteException;

    void onDisplayAreaVanished(DisplayAreaInfo displayAreaInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements IDisplayAreaOrganizer {
        private static final String DESCRIPTOR = "android.window.IDisplayAreaOrganizer";
        static final int TRANSACTION_onDisplayAreaAppeared = 1;
        static final int TRANSACTION_onDisplayAreaInfoChanged = 3;
        static final int TRANSACTION_onDisplayAreaVanished = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onDisplayAreaAppeared";
            }
            if (i == 2) {
                return "onDisplayAreaVanished";
            }
            if (i != 3) {
                return null;
            }
            return "onDisplayAreaInfoChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDisplayAreaOrganizer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDisplayAreaOrganizer)) {
                return (IDisplayAreaOrganizer) iInterfaceQueryLocalInterface;
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
                onDisplayAreaAppeared(parcel.readInt() != 0 ? DisplayAreaInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? SurfaceControl.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onDisplayAreaVanished(parcel.readInt() != 0 ? DisplayAreaInfo.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onDisplayAreaInfoChanged(parcel.readInt() != 0 ? DisplayAreaInfo.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IDisplayAreaOrganizer {
            public static IDisplayAreaOrganizer sDefaultImpl;
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

            @Override // android.window.IDisplayAreaOrganizer
            public void onDisplayAreaAppeared(DisplayAreaInfo displayAreaInfo, SurfaceControl surfaceControl) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (displayAreaInfo != null) {
                        parcelObtain.writeInt(1);
                        displayAreaInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (surfaceControl != null) {
                        parcelObtain.writeInt(1);
                        surfaceControl.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDisplayAreaAppeared(displayAreaInfo, surfaceControl);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.window.IDisplayAreaOrganizer
            public void onDisplayAreaVanished(DisplayAreaInfo displayAreaInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (displayAreaInfo != null) {
                        parcelObtain.writeInt(1);
                        displayAreaInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDisplayAreaVanished(displayAreaInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.window.IDisplayAreaOrganizer
            public void onDisplayAreaInfoChanged(DisplayAreaInfo displayAreaInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (displayAreaInfo != null) {
                        parcelObtain.writeInt(1);
                        displayAreaInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDisplayAreaInfoChanged(displayAreaInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDisplayAreaOrganizer iDisplayAreaOrganizer) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDisplayAreaOrganizer == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDisplayAreaOrganizer;
            return true;
        }

        public static IDisplayAreaOrganizer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
