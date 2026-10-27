package cerence.ark.assistant.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IRequestListener extends IInterface {
    public static final String DESCRIPTOR = "cerence.ark.assistant.service.IRequestListener";

    public static class Default implements IRequestListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cerence.ark.assistant.service.IRequestListener
        public boolean onAbortRequest(String str) throws RemoteException {
            return false;
        }

        @Override // cerence.ark.assistant.service.IRequestListener
        public void onRequestReceived(String str, String str2, String str3, int i, int i2) throws RemoteException {
        }
    }

    boolean onAbortRequest(String str) throws RemoteException;

    void onRequestReceived(String str, String str2, String str3, int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IRequestListener {
        static final int TRANSACTION_onAbortRequest = 2;
        static final int TRANSACTION_onRequestReceived = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IRequestListener.DESCRIPTOR);
        }

        public static IRequestListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IRequestListener.DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRequestListener)) {
                return (IRequestListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IRequestListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IRequestListener.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onRequestReceived(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
            } else if (i == 2) {
                boolean zOnAbortRequest = onAbortRequest(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(zOnAbortRequest ? 1 : 0);
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        private static class Proxy implements IRequestListener {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return IRequestListener.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // cerence.ark.assistant.service.IRequestListener
            public void onRequestReceived(String str, String str2, String str3, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IRequestListener.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // cerence.ark.assistant.service.IRequestListener
            public boolean onAbortRequest(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IRequestListener.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
