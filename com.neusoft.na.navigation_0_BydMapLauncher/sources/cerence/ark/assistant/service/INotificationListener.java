package cerence.ark.assistant.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface INotificationListener extends IInterface {
    public static final String DESCRIPTOR = "cerence.ark.assistant.service.INotificationListener";

    public static class Default implements INotificationListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cerence.ark.assistant.service.INotificationListener
        public void onNotificationReceived(String str, String str2, int i, int i2) throws RemoteException {
        }
    }

    void onNotificationReceived(String str, String str2, int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements INotificationListener {
        static final int TRANSACTION_onNotificationReceived = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, INotificationListener.DESCRIPTOR);
        }

        public static INotificationListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(INotificationListener.DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INotificationListener)) {
                return (INotificationListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(INotificationListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(INotificationListener.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onNotificationReceived(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements INotificationListener {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return INotificationListener.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // cerence.ark.assistant.service.INotificationListener
            public void onNotificationReceived(String str, String str2, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(INotificationListener.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
