package android.service.carrier;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.carrier.ICarrierMessagingCallback;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface ICarrierMessagingService extends IInterface {

    public static class Default implements ICarrierMessagingService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.carrier.ICarrierMessagingService
        public void downloadMms(Uri uri, int i, Uri uri2, ICarrierMessagingCallback iCarrierMessagingCallback) throws RemoteException {
        }

        @Override // android.service.carrier.ICarrierMessagingService
        public void filterSms(MessagePdu messagePdu, String str, int i, int i2, ICarrierMessagingCallback iCarrierMessagingCallback) throws RemoteException {
        }

        @Override // android.service.carrier.ICarrierMessagingService
        public void sendDataSms(byte[] bArr, int i, String str, int i2, int i3, ICarrierMessagingCallback iCarrierMessagingCallback) throws RemoteException {
        }

        @Override // android.service.carrier.ICarrierMessagingService
        public void sendMms(Uri uri, int i, Uri uri2, ICarrierMessagingCallback iCarrierMessagingCallback) throws RemoteException {
        }

        @Override // android.service.carrier.ICarrierMessagingService
        public void sendMultipartTextSms(List<String> list, int i, String str, int i2, ICarrierMessagingCallback iCarrierMessagingCallback) throws RemoteException {
        }

        @Override // android.service.carrier.ICarrierMessagingService
        public void sendTextSms(String str, int i, String str2, int i2, ICarrierMessagingCallback iCarrierMessagingCallback) throws RemoteException {
        }
    }

    void downloadMms(Uri uri, int i, Uri uri2, ICarrierMessagingCallback iCarrierMessagingCallback) throws RemoteException;

    void filterSms(MessagePdu messagePdu, String str, int i, int i2, ICarrierMessagingCallback iCarrierMessagingCallback) throws RemoteException;

    void sendDataSms(byte[] bArr, int i, String str, int i2, int i3, ICarrierMessagingCallback iCarrierMessagingCallback) throws RemoteException;

    void sendMms(Uri uri, int i, Uri uri2, ICarrierMessagingCallback iCarrierMessagingCallback) throws RemoteException;

    void sendMultipartTextSms(List<String> list, int i, String str, int i2, ICarrierMessagingCallback iCarrierMessagingCallback) throws RemoteException;

    void sendTextSms(String str, int i, String str2, int i2, ICarrierMessagingCallback iCarrierMessagingCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements ICarrierMessagingService {
        private static final String DESCRIPTOR = "android.service.carrier.ICarrierMessagingService";
        static final int TRANSACTION_downloadMms = 6;
        static final int TRANSACTION_filterSms = 1;
        static final int TRANSACTION_sendDataSms = 3;
        static final int TRANSACTION_sendMms = 5;
        static final int TRANSACTION_sendMultipartTextSms = 4;
        static final int TRANSACTION_sendTextSms = 2;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "filterSms";
                case 2:
                    return "sendTextSms";
                case 3:
                    return "sendDataSms";
                case 4:
                    return "sendMultipartTextSms";
                case 5:
                    return "sendMms";
                case 6:
                    return "downloadMms";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarrierMessagingService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICarrierMessagingService)) {
                return (ICarrierMessagingService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    filterSms(parcel.readInt() != 0 ? MessagePdu.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt(), parcel.readInt(), ICarrierMessagingCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendTextSms(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt(), ICarrierMessagingCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendDataSms(parcel.createByteArray(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), ICarrierMessagingCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendMultipartTextSms(parcel.createStringArrayList(), parcel.readInt(), parcel.readString(), parcel.readInt(), ICarrierMessagingCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendMms(parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, ICarrierMessagingCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    downloadMms(parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, ICarrierMessagingCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ICarrierMessagingService {
            public static ICarrierMessagingService sDefaultImpl;
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

            @Override // android.service.carrier.ICarrierMessagingService
            public void filterSms(MessagePdu messagePdu, String str, int i, int i2, ICarrierMessagingCallback iCarrierMessagingCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (messagePdu != null) {
                        parcelObtain.writeInt(1);
                        messagePdu.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iCarrierMessagingCallback != null ? iCarrierMessagingCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().filterSms(messagePdu, str, i, i2, iCarrierMessagingCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.carrier.ICarrierMessagingService
            public void sendTextSms(String str, int i, String str2, int i2, ICarrierMessagingCallback iCarrierMessagingCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iCarrierMessagingCallback != null ? iCarrierMessagingCallback.asBinder() : null);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendTextSms(str, i, str2, i2, iCarrierMessagingCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.carrier.ICarrierMessagingService
            public void sendDataSms(byte[] bArr, int i, String str, int i2, int i3, ICarrierMessagingCallback iCarrierMessagingCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeStrongBinder(iCarrierMessagingCallback != null ? iCarrierMessagingCallback.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendDataSms(bArr, i, str, i2, i3, iCarrierMessagingCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.carrier.ICarrierMessagingService
            public void sendMultipartTextSms(List<String> list, int i, String str, int i2, ICarrierMessagingCallback iCarrierMessagingCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iCarrierMessagingCallback != null ? iCarrierMessagingCallback.asBinder() : null);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendMultipartTextSms(list, i, str, i2, iCarrierMessagingCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.carrier.ICarrierMessagingService
            public void sendMms(Uri uri, int i, Uri uri2, ICarrierMessagingCallback iCarrierMessagingCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (uri2 != null) {
                        parcelObtain.writeInt(1);
                        uri2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iCarrierMessagingCallback != null ? iCarrierMessagingCallback.asBinder() : null);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().sendMms(uri, i, uri2, iCarrierMessagingCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.carrier.ICarrierMessagingService
            public void downloadMms(Uri uri, int i, Uri uri2, ICarrierMessagingCallback iCarrierMessagingCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (uri2 != null) {
                        parcelObtain.writeInt(1);
                        uri2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iCarrierMessagingCallback != null ? iCarrierMessagingCallback.asBinder() : null);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().downloadMms(uri, i, uri2, iCarrierMessagingCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarrierMessagingService iCarrierMessagingService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCarrierMessagingService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCarrierMessagingService;
            return true;
        }

        public static ICarrierMessagingService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
