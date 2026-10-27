package com.byd.sdkupdate.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IOtaSdkCallback extends IInterface {

    public static class Default implements IOtaSdkCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.sdkupdate.aidl.IOtaSdkCallback
        public void basicTypes(int i, long j, boolean z, float f, double d2, String str) throws RemoteException {
        }

        @Override // com.byd.sdkupdate.aidl.IOtaSdkCallback
        public void onUpdateFlag(boolean z) throws RemoteException {
        }

        @Override // com.byd.sdkupdate.aidl.IOtaSdkCallback
        public void onUpdateTime(long j) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IOtaSdkCallback {
        private static final String DESCRIPTOR = "com.byd.sdkupdate.aidl.IOtaSdkCallback";
        public static final int TRANSACTION_basicTypes = 3;
        public static final int TRANSACTION_onUpdateFlag = 1;
        public static final int TRANSACTION_onUpdateTime = 2;

        public static class Proxy implements IOtaSdkCallback {
            public static IOtaSdkCallback sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.byd.sdkupdate.aidl.IOtaSdkCallback
            public void basicTypes(int i, long j, boolean z, float f, double d2, String str) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeFloat(f);
                    parcelObtain.writeDouble(d2);
                    parcelObtain.writeString(str);
                    try {
                        if (this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                            parcelObtain2.readException();
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        } else {
                            Stub.getDefaultImpl().basicTypes(i, j, z, f, d2, str);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        }
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.byd.sdkupdate.aidl.IOtaSdkCallback
            public void onUpdateFlag(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onUpdateFlag(z);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.sdkupdate.aidl.IOtaSdkCallback
            public void onUpdateTime(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onUpdateTime(j);
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

        public static IOtaSdkCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IOtaSdkCallback)) ? new Proxy(iBinder) : (IOtaSdkCallback) iInterfaceQueryLocalInterface;
        }

        public static IOtaSdkCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IOtaSdkCallback iOtaSdkCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iOtaSdkCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iOtaSdkCallback;
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
                onUpdateFlag(parcel.readInt() != 0);
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onUpdateTime(parcel.readLong());
                parcel2.writeNoException();
                return true;
            }
            if (i != 3) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel.enforceInterface(DESCRIPTOR);
            basicTypes(parcel.readInt(), parcel.readLong(), parcel.readInt() != 0, parcel.readFloat(), parcel.readDouble(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }
    }

    void basicTypes(int i, long j, boolean z, float f, double d2, String str) throws RemoteException;

    void onUpdateFlag(boolean z) throws RemoteException;

    void onUpdateTime(long j) throws RemoteException;
}
