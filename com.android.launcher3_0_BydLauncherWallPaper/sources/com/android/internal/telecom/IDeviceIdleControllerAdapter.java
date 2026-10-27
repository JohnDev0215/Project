package com.android.internal.telecom;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public interface IDeviceIdleControllerAdapter extends IInterface {

    public static class Default implements IDeviceIdleControllerAdapter {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telecom.IDeviceIdleControllerAdapter
        public void exemptAppTemporarilyForEvent(String str, long j, int i, String str2) throws RemoteException {
        }
    }

    void exemptAppTemporarilyForEvent(String str, long j, int i, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IDeviceIdleControllerAdapter {
        private static final String DESCRIPTOR = "com.android.internal.telecom.IDeviceIdleControllerAdapter";
        static final int TRANSACTION_exemptAppTemporarilyForEvent = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "exemptAppTemporarilyForEvent";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDeviceIdleControllerAdapter asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDeviceIdleControllerAdapter)) {
                return (IDeviceIdleControllerAdapter) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            exemptAppTemporarilyForEvent(parcel.readString(), parcel.readLong(), parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IDeviceIdleControllerAdapter {
            public static IDeviceIdleControllerAdapter sDefaultImpl;
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

            @Override // com.android.internal.telecom.IDeviceIdleControllerAdapter
            public void exemptAppTemporarilyForEvent(String str, long j, int i, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().exemptAppTemporarilyForEvent(str, j, i, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDeviceIdleControllerAdapter iDeviceIdleControllerAdapter) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDeviceIdleControllerAdapter == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDeviceIdleControllerAdapter;
            return true;
        }

        public static IDeviceIdleControllerAdapter getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
