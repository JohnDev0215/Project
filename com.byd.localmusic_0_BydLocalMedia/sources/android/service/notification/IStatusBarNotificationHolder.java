package android.service.notification;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IStatusBarNotificationHolder extends IInterface {

    public static class Default implements IStatusBarNotificationHolder {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.notification.IStatusBarNotificationHolder
        public StatusBarNotification get() throws RemoteException {
            return null;
        }
    }

    StatusBarNotification get() throws RemoteException;

    public static abstract class Stub extends Binder implements IStatusBarNotificationHolder {
        private static final String DESCRIPTOR = "android.service.notification.IStatusBarNotificationHolder";
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

        public static IStatusBarNotificationHolder asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IStatusBarNotificationHolder)) {
                return (IStatusBarNotificationHolder) iInterfaceQueryLocalInterface;
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
            StatusBarNotification statusBarNotification = get();
            parcel2.writeNoException();
            if (statusBarNotification != null) {
                parcel2.writeInt(1);
                statusBarNotification.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }

        private static class Proxy implements IStatusBarNotificationHolder {
            public static IStatusBarNotificationHolder sDefaultImpl;
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

            @Override // android.service.notification.IStatusBarNotificationHolder
            public StatusBarNotification get() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().get();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? StatusBarNotification.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IStatusBarNotificationHolder iStatusBarNotificationHolder) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iStatusBarNotificationHolder == null) {
                return false;
            }
            Proxy.sDefaultImpl = iStatusBarNotificationHolder;
            return true;
        }

        public static IStatusBarNotificationHolder getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
