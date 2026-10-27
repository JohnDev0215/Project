package android.view;

import android.graphics.Region;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface ISystemGestureExclusionListener extends IInterface {

    public static class Default implements ISystemGestureExclusionListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.ISystemGestureExclusionListener
        public void onSystemGestureExclusionChanged(int i, Region region, Region region2) throws RemoteException {
        }
    }

    void onSystemGestureExclusionChanged(int i, Region region, Region region2) throws RemoteException;

    public static abstract class Stub extends Binder implements ISystemGestureExclusionListener {
        private static final String DESCRIPTOR = "android.view.ISystemGestureExclusionListener";
        static final int TRANSACTION_onSystemGestureExclusionChanged = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onSystemGestureExclusionChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISystemGestureExclusionListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISystemGestureExclusionListener)) {
                return (ISystemGestureExclusionListener) iInterfaceQueryLocalInterface;
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
                onSystemGestureExclusionChanged(parcel.readInt(), parcel.readInt() != 0 ? Region.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Region.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ISystemGestureExclusionListener {
            public static ISystemGestureExclusionListener sDefaultImpl;
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

            @Override // android.view.ISystemGestureExclusionListener
            public void onSystemGestureExclusionChanged(int i, Region region, Region region2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (region != null) {
                        parcelObtain.writeInt(1);
                        region.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (region2 != null) {
                        parcelObtain.writeInt(1);
                        region2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSystemGestureExclusionChanged(i, region, region2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISystemGestureExclusionListener iSystemGestureExclusionListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSystemGestureExclusionListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSystemGestureExclusionListener;
            return true;
        }

        public static ISystemGestureExclusionListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
