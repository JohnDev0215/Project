package com.byd.systemsettings;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ISystemSettingsCallback extends IInterface {

    public static class Default implements ISystemSettingsCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.systemsettings.ISystemSettingsCallback
        public void onDismissAllDialogCompleted() throws RemoteException {
        }

        @Override // com.byd.systemsettings.ISystemSettingsCallback
        public void onStateChanged(String str, int i) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements ISystemSettingsCallback {
        private static final String DESCRIPTOR = "com.byd.systemsettings.ISystemSettingsCallback";
        public static final int TRANSACTION_onDismissAllDialogCompleted = 2;
        public static final int TRANSACTION_onStateChanged = 1;

        public static class Proxy implements ISystemSettingsCallback {
            public static ISystemSettingsCallback sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.byd.systemsettings.ISystemSettingsCallback
            public void onDismissAllDialogCompleted() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onDismissAllDialogCompleted();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.systemsettings.ISystemSettingsCallback
            public void onStateChanged(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onStateChanged(str, i);
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

        public static ISystemSettingsCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof ISystemSettingsCallback)) ? new Proxy(iBinder) : (ISystemSettingsCallback) iInterfaceQueryLocalInterface;
        }

        public static ISystemSettingsCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(ISystemSettingsCallback iSystemSettingsCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSystemSettingsCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSystemSettingsCallback;
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
                onStateChanged(parcel.readString(), parcel.readInt());
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
            onDismissAllDialogCompleted();
            parcel2.writeNoException();
            return true;
        }
    }

    void onDismissAllDialogCompleted() throws RemoteException;

    void onStateChanged(String str, int i) throws RemoteException;
}
