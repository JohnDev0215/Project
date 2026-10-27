package com.android.internal.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public interface IParcelFileDescriptorFactory extends IInterface {

    public static class Default implements IParcelFileDescriptorFactory {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.os.IParcelFileDescriptorFactory
        public ParcelFileDescriptor open(String str, int i) throws RemoteException {
            return null;
        }
    }

    ParcelFileDescriptor open(String str, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IParcelFileDescriptorFactory {
        private static final String DESCRIPTOR = "com.android.internal.os.IParcelFileDescriptorFactory";
        static final int TRANSACTION_open = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "open";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IParcelFileDescriptorFactory asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IParcelFileDescriptorFactory)) {
                return (IParcelFileDescriptorFactory) iInterfaceQueryLocalInterface;
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
            ParcelFileDescriptor parcelFileDescriptorOpen = open(parcel.readString(), parcel.readInt());
            parcel2.writeNoException();
            if (parcelFileDescriptorOpen != null) {
                parcel2.writeInt(1);
                parcelFileDescriptorOpen.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }

        private static class Proxy implements IParcelFileDescriptorFactory {
            public static IParcelFileDescriptorFactory sDefaultImpl;
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

            @Override // com.android.internal.os.IParcelFileDescriptorFactory
            public ParcelFileDescriptor open(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().open(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IParcelFileDescriptorFactory iParcelFileDescriptorFactory) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iParcelFileDescriptorFactory == null) {
                return false;
            }
            Proxy.sDefaultImpl = iParcelFileDescriptorFactory;
            return true;
        }

        public static IParcelFileDescriptorFactory getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
