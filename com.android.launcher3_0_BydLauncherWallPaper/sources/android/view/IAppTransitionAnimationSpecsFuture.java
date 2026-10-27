package android.view;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IAppTransitionAnimationSpecsFuture extends IInterface {

    public static class Default implements IAppTransitionAnimationSpecsFuture {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.IAppTransitionAnimationSpecsFuture
        public AppTransitionAnimationSpec[] get() throws RemoteException {
            return null;
        }
    }

    AppTransitionAnimationSpec[] get() throws RemoteException;

    public static abstract class Stub extends Binder implements IAppTransitionAnimationSpecsFuture {
        private static final String DESCRIPTOR = "android.view.IAppTransitionAnimationSpecsFuture";
        static final int TRANSACTION_get = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "get";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAppTransitionAnimationSpecsFuture asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAppTransitionAnimationSpecsFuture)) {
                return (IAppTransitionAnimationSpecsFuture) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            AppTransitionAnimationSpec[] appTransitionAnimationSpecArr = get();
            parcel2.writeNoException();
            parcel2.writeTypedArray(appTransitionAnimationSpecArr, 1);
            return true;
        }

        private static class Proxy implements IAppTransitionAnimationSpecsFuture {
            public static IAppTransitionAnimationSpecsFuture sDefaultImpl;
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

            @Override // android.view.IAppTransitionAnimationSpecsFuture
            public AppTransitionAnimationSpec[] get() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().get();
                    }
                    parcelObtain2.readException();
                    return (AppTransitionAnimationSpec[]) parcelObtain2.createTypedArray(AppTransitionAnimationSpec.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAppTransitionAnimationSpecsFuture iAppTransitionAnimationSpecsFuture) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAppTransitionAnimationSpecsFuture == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAppTransitionAnimationSpecsFuture;
            return true;
        }

        public static IAppTransitionAnimationSpecsFuture getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
