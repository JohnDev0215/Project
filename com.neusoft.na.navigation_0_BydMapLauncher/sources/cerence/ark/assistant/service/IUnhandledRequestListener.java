package cerence.ark.assistant.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IUnhandledRequestListener extends IInterface {
    public static final String DESCRIPTOR = "cerence.ark.assistant.service.IUnhandledRequestListener";

    public static class Default implements IUnhandledRequestListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cerence.ark.assistant.service.IUnhandledRequestListener
        public void onUnhandledRequest(String str, String str2, int i, int i2) throws RemoteException {
        }
    }

    void onUnhandledRequest(String str, String str2, int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IUnhandledRequestListener {
        static final int TRANSACTION_onUnhandledRequest = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IUnhandledRequestListener.DESCRIPTOR);
        }

        public static IUnhandledRequestListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IUnhandledRequestListener.DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IUnhandledRequestListener)) {
                return (IUnhandledRequestListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IUnhandledRequestListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IUnhandledRequestListener.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onUnhandledRequest(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IUnhandledRequestListener {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return IUnhandledRequestListener.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // cerence.ark.assistant.service.IUnhandledRequestListener
            public void onUnhandledRequest(String str, String str2, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IUnhandledRequestListener.DESCRIPTOR);
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
