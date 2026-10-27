package cerence.ark.assistant.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import cerence.ark.assistant.framework.data.ErrorInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface IErrorListener extends IInterface {
    public static final String DESCRIPTOR = "cerence.ark.assistant.service.IErrorListener";

    public static class Default implements IErrorListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // cerence.ark.assistant.service.IErrorListener
        public void onError(ErrorInfo errorInfo) throws RemoteException {
        }
    }

    void onError(ErrorInfo errorInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements IErrorListener {
        static final int TRANSACTION_onError = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IErrorListener.DESCRIPTOR);
        }

        public static IErrorListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IErrorListener.DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IErrorListener)) {
                return (IErrorListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IErrorListener.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IErrorListener.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onError((ErrorInfo) _Parcel.readTypedObject(parcel, ErrorInfo.CREATOR));
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IErrorListener {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return IErrorListener.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // cerence.ark.assistant.service.IErrorListener
            public void onError(ErrorInfo errorInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IErrorListener.DESCRIPTOR);
                    _Parcel.writeTypedObject(parcelObtain, errorInfo, 0);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }

    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}
