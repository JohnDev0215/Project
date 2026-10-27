package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IBluetoothPbapCallBack extends IInterface {

    public static class Default implements IBluetoothPbapCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.bluetooth.IBluetoothPbapCallBack
        public void onGetCalllogComplete(String str, List<BluetoothCardEntry> list, String str2) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothPbapCallBack
        public void onGetCalllogState(String str, int i, String str2) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothPbapCallBack
        public void onGetPhoneBookComplete(String str, List<BluetoothCardEntry> list, String str2) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothPbapCallBack
        public void onGetPhoneBookSize(String str, int i, String str2) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothPbapCallBack
        public void onGetPhoneBookState(String str, int i, String str2) throws RemoteException {
        }
    }

    void onGetCalllogComplete(String str, List<BluetoothCardEntry> list, String str2) throws RemoteException;

    void onGetCalllogState(String str, int i, String str2) throws RemoteException;

    void onGetPhoneBookComplete(String str, List<BluetoothCardEntry> list, String str2) throws RemoteException;

    void onGetPhoneBookSize(String str, int i, String str2) throws RemoteException;

    void onGetPhoneBookState(String str, int i, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IBluetoothPbapCallBack {
        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothPbapCallBack";
        static final int TRANSACTION_onGetCalllogComplete = 5;
        static final int TRANSACTION_onGetCalllogState = 4;
        static final int TRANSACTION_onGetPhoneBookComplete = 3;
        static final int TRANSACTION_onGetPhoneBookSize = 2;
        static final int TRANSACTION_onGetPhoneBookState = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onGetPhoneBookState";
            }
            if (i == 2) {
                return "onGetPhoneBookSize";
            }
            if (i == 3) {
                return "onGetPhoneBookComplete";
            }
            if (i == 4) {
                return "onGetCalllogState";
            }
            if (i != 5) {
                return null;
            }
            return "onGetCalllogComplete";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBluetoothPbapCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBluetoothPbapCallBack)) {
                return (IBluetoothPbapCallBack) iInterfaceQueryLocalInterface;
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
                onGetPhoneBookState(parcel.readString(), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onGetPhoneBookSize(parcel.readString(), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onGetPhoneBookComplete(parcel.readString(), parcel.createTypedArrayList(BluetoothCardEntry.CREATOR), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onGetCalllogState(parcel.readString(), parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onGetCalllogComplete(parcel.readString(), parcel.createTypedArrayList(BluetoothCardEntry.CREATOR), parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IBluetoothPbapCallBack {
            public static IBluetoothPbapCallBack sDefaultImpl;
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

            @Override // android.bluetooth.IBluetoothPbapCallBack
            public void onGetPhoneBookState(String str, int i, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGetPhoneBookState(str, i, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothPbapCallBack
            public void onGetPhoneBookSize(String str, int i, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGetPhoneBookSize(str, i, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothPbapCallBack
            public void onGetPhoneBookComplete(String str, List<BluetoothCardEntry> list, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGetPhoneBookComplete(str, list, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothPbapCallBack
            public void onGetCalllogState(String str, int i, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGetCalllogState(str, i, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothPbapCallBack
            public void onGetCalllogComplete(String str, List<BluetoothCardEntry> list, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onGetCalllogComplete(str, list, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothPbapCallBack iBluetoothPbapCallBack) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iBluetoothPbapCallBack == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBluetoothPbapCallBack;
            return true;
        }

        public static IBluetoothPbapCallBack getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
