package com.android.internal.compat;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public interface IOverrideValidator extends IInterface {

    public static class Default implements IOverrideValidator {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.compat.IOverrideValidator
        public OverrideAllowedState getOverrideAllowedState(long j, String str) throws RemoteException {
            return null;
        }
    }

    OverrideAllowedState getOverrideAllowedState(long j, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IOverrideValidator {
        private static final String DESCRIPTOR = "com.android.internal.compat.IOverrideValidator";
        static final int TRANSACTION_getOverrideAllowedState = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "getOverrideAllowedState";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOverrideValidator asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IOverrideValidator)) {
                return (IOverrideValidator) iInterfaceQueryLocalInterface;
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
            OverrideAllowedState overrideAllowedState = getOverrideAllowedState(parcel.readLong(), parcel.readString());
            parcel2.writeNoException();
            if (overrideAllowedState != null) {
                parcel2.writeInt(1);
                overrideAllowedState.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }

        private static class Proxy implements IOverrideValidator {
            public static IOverrideValidator sDefaultImpl;
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

            @Override // com.android.internal.compat.IOverrideValidator
            public OverrideAllowedState getOverrideAllowedState(long j, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOverrideAllowedState(j, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? OverrideAllowedState.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOverrideValidator iOverrideValidator) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iOverrideValidator == null) {
                return false;
            }
            Proxy.sDefaultImpl = iOverrideValidator;
            return true;
        }

        public static IOverrideValidator getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
