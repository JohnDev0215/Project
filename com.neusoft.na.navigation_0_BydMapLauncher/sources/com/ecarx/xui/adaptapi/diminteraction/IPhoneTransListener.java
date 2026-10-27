package com.ecarx.xui.adaptapi.diminteraction;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.xui.adaptapi.diminteraction.IPhoneCallListener;

/* JADX INFO: loaded from: classes2.dex */
public interface IPhoneTransListener extends IInterface {
    void notifyDBState(int i) throws RemoteException;

    void notifyLineInfo(int i, boolean z, boolean z2, int i2, String str, String str2, int i3, long j, int i4) throws RemoteException;

    void notifyMissCallCount(int i) throws RemoteException;

    void notifyPhoneBasicInfo(String str, int i, int i2) throws RemoteException;

    void registerCallBack(IPhoneCallListener iPhoneCallListener) throws RemoteException;

    public static abstract class Stub extends Binder implements IPhoneTransListener {
        private static final String DESCRIPTOR = "com.ecarx.xui.adaptapi.diminteraction.IPhoneTransListener";
        static final int TRANSACTION_notifyDBState = 5;
        static final int TRANSACTION_notifyLineInfo = 4;
        static final int TRANSACTION_notifyMissCallCount = 2;
        static final int TRANSACTION_notifyPhoneBasicInfo = 1;
        static final int TRANSACTION_registerCallBack = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPhoneTransListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPhoneTransListener)) {
                return (IPhoneTransListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                notifyPhoneBasicInfo(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                notifyMissCallCount(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                registerCallBack(IPhoneCallListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                notifyLineInfo(parcel.readInt(), parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readLong(), parcel.readInt());
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
            notifyDBState(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements IPhoneTransListener {
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

            @Override // com.ecarx.xui.adaptapi.diminteraction.IPhoneTransListener
            public void notifyPhoneBasicInfo(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.diminteraction.IPhoneTransListener
            public void notifyMissCallCount(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.diminteraction.IPhoneTransListener
            public void registerCallBack(IPhoneCallListener iPhoneCallListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iPhoneCallListener != null ? iPhoneCallListener.asBinder() : null);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.diminteraction.IPhoneTransListener
            public void notifyLineInfo(int i, boolean z, boolean z2, int i2, String str, String str2, int i3, long j, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    int i5 = 1;
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i5 = 0;
                    }
                    parcelObtain.writeInt(i5);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i4);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.diminteraction.IPhoneTransListener
            public void notifyDBState(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
