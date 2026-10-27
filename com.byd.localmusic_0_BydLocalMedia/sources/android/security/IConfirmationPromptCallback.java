package android.security;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IConfirmationPromptCallback extends IInterface {

    public static class Default implements IConfirmationPromptCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.security.IConfirmationPromptCallback
        public void onConfirmationPromptCompleted(int i, byte[] bArr) throws RemoteException {
        }
    }

    void onConfirmationPromptCompleted(int i, byte[] bArr) throws RemoteException;

    public static abstract class Stub extends Binder implements IConfirmationPromptCallback {
        private static final String DESCRIPTOR = "android.security.IConfirmationPromptCallback";
        static final int TRANSACTION_onConfirmationPromptCompleted = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onConfirmationPromptCompleted";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IConfirmationPromptCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IConfirmationPromptCallback)) {
                return (IConfirmationPromptCallback) iInterfaceQueryLocalInterface;
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
                onConfirmationPromptCompleted(parcel.readInt(), parcel.createByteArray());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IConfirmationPromptCallback {
            public static IConfirmationPromptCallback sDefaultImpl;
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

            @Override // android.security.IConfirmationPromptCallback
            public void onConfirmationPromptCompleted(int i, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeByteArray(bArr);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConfirmationPromptCompleted(i, bArr);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IConfirmationPromptCallback iConfirmationPromptCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iConfirmationPromptCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iConfirmationPromptCallback;
            return true;
        }

        public static IConfirmationPromptCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
