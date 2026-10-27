package android.database;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IContentObserver extends IInterface {

    public static class Default implements IContentObserver {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.database.IContentObserver
        public void onChange(boolean z, Uri uri, int i) throws RemoteException {
        }

        @Override // android.database.IContentObserver
        public void onChangeEtc(boolean z, Uri[] uriArr, int i, int i2) throws RemoteException {
        }

        @Override // android.database.IContentObserver
        public void onChangeEtcAsUser(boolean z, Uri[] uriArr, int i, int i2, String str, int i3) throws RemoteException {
        }
    }

    void onChange(boolean z, Uri uri, int i) throws RemoteException;

    void onChangeEtc(boolean z, Uri[] uriArr, int i, int i2) throws RemoteException;

    void onChangeEtcAsUser(boolean z, Uri[] uriArr, int i, int i2, String str, int i3) throws RemoteException;

    public static abstract class Stub extends Binder implements IContentObserver {
        private static final String DESCRIPTOR = "android.database.IContentObserver";
        static final int TRANSACTION_onChange = 1;
        static final int TRANSACTION_onChangeEtc = 2;
        static final int TRANSACTION_onChangeEtcAsUser = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onChange";
            }
            if (i == 2) {
                return "onChangeEtc";
            }
            if (i != 3) {
                return null;
            }
            return "onChangeEtcAsUser";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IContentObserver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IContentObserver)) {
                return (IContentObserver) iInterfaceQueryLocalInterface;
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
                onChange(parcel.readInt() != 0, parcel.readInt() != 0 ? Uri.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onChangeEtc(parcel.readInt() != 0, (Uri[]) parcel.createTypedArray(Uri.CREATOR), parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onChangeEtcAsUser(parcel.readInt() != 0, (Uri[]) parcel.createTypedArray(Uri.CREATOR), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IContentObserver {
            public static IContentObserver sDefaultImpl;
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

            @Override // android.database.IContentObserver
            public void onChange(boolean z, Uri uri, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onChange(z, uri, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.database.IContentObserver
            public void onChangeEtc(boolean z, Uri[] uriArr, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeTypedArray(uriArr, 0);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onChangeEtc(z, uriArr, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.database.IContentObserver
            public void onChangeEtcAsUser(boolean z, Uri[] uriArr, int i, int i2, String str, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeTypedArray(uriArr, 0);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onChangeEtcAsUser(z, uriArr, i, i2, str, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IContentObserver iContentObserver) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iContentObserver == null) {
                return false;
            }
            Proxy.sDefaultImpl = iContentObserver;
            return true;
        }

        public static IContentObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
