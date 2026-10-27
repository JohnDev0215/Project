package android.telephony.ims.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IImsSmsListener extends IInterface {

    public static class Default implements IImsSmsListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.telephony.ims.aidl.IImsSmsListener
        public void onSendSmsResult(int i, int i2, int i3, int i4, int i5) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsSmsListener
        public void onSmsReceived(int i, String str, byte[] bArr) throws RemoteException {
        }

        @Override // android.telephony.ims.aidl.IImsSmsListener
        public void onSmsStatusReportReceived(int i, String str, byte[] bArr) throws RemoteException {
        }
    }

    void onSendSmsResult(int i, int i2, int i3, int i4, int i5) throws RemoteException;

    void onSmsReceived(int i, String str, byte[] bArr) throws RemoteException;

    void onSmsStatusReportReceived(int i, String str, byte[] bArr) throws RemoteException;

    public static abstract class Stub extends Binder implements IImsSmsListener {
        private static final String DESCRIPTOR = "android.telephony.ims.aidl.IImsSmsListener";
        static final int TRANSACTION_onSendSmsResult = 1;
        static final int TRANSACTION_onSmsReceived = 3;
        static final int TRANSACTION_onSmsStatusReportReceived = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onSendSmsResult";
            }
            if (i == 2) {
                return "onSmsStatusReportReceived";
            }
            if (i != 3) {
                return null;
            }
            return "onSmsReceived";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IImsSmsListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IImsSmsListener)) {
                return (IImsSmsListener) iInterfaceQueryLocalInterface;
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
                onSendSmsResult(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onSmsStatusReportReceived(parcel.readInt(), parcel.readString(), parcel.createByteArray());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onSmsReceived(parcel.readInt(), parcel.readString(), parcel.createByteArray());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IImsSmsListener {
            public static IImsSmsListener sDefaultImpl;
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

            @Override // android.telephony.ims.aidl.IImsSmsListener
            public void onSendSmsResult(int i, int i2, int i3, int i4, int i5) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i5);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSendSmsResult(i, i2, i3, i4, i5);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsSmsListener
            public void onSmsStatusReportReceived(int i, String str, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeByteArray(bArr);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSmsStatusReportReceived(i, str, bArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.ims.aidl.IImsSmsListener
            public void onSmsReceived(int i, String str, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeByteArray(bArr);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSmsReceived(i, str, bArr);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImsSmsListener iImsSmsListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iImsSmsListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iImsSmsListener;
            return true;
        }

        public static IImsSmsListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
