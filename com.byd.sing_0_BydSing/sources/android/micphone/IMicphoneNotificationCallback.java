package android.micphone;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IMicphoneNotificationCallback extends IInterface {

    public static class Default implements IMicphoneNotificationCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.micphone.IMicphoneNotificationCallback
        public void onMicphoneDongleEvent(int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.micphone.IMicphoneNotificationCallback
        public void onMicphoneNotification(int i, int i2) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IMicphoneNotificationCallback {
        private static final String DESCRIPTOR = "android.micphone.IMicphoneNotificationCallback";
        static final int TRANSACTION_onMicphoneDongleEvent = 2;
        static final int TRANSACTION_onMicphoneNotification = 1;

        private static class Proxy implements IMicphoneNotificationCallback {
            public static IMicphoneNotificationCallback sDefaultImpl;
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

            @Override // android.micphone.IMicphoneNotificationCallback
            public void onMicphoneDongleEvent(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onMicphoneDongleEvent(i, i2, i3);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.micphone.IMicphoneNotificationCallback
            public void onMicphoneNotification(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onMicphoneNotification(i, i2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMicphoneNotificationCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IMicphoneNotificationCallback)) ? new Proxy(iBinder) : (IMicphoneNotificationCallback) iInterfaceQueryLocalInterface;
        }

        public static IMicphoneNotificationCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IMicphoneNotificationCallback iMicphoneNotificationCallback) {
            if (Proxy.sDefaultImpl != null || iMicphoneNotificationCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMicphoneNotificationCallback;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onMicphoneNotification(parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel.enforceInterface(DESCRIPTOR);
            onMicphoneDongleEvent(parcel.readInt(), parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            return true;
        }
    }

    void onMicphoneDongleEvent(int i, int i2, int i3) throws RemoteException;

    void onMicphoneNotification(int i, int i2) throws RemoteException;
}
