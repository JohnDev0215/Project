package com.byd.appstore.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IMarketDownloadAidlInterface extends IInterface {

    public static class Default implements IMarketDownloadAidlInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.appstore.aidl.IMarketDownloadAidlInterface
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        }

        @Override // com.byd.appstore.aidl.IMarketDownloadAidlInterface
        public int getDownLoadNum() throws RemoteException {
            return 0;
        }

        @Override // com.byd.appstore.aidl.IMarketDownloadAidlInterface
        public int getPauseNum() throws RemoteException {
            return 0;
        }
    }

    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException;

    int getDownLoadNum() throws RemoteException;

    int getPauseNum() throws RemoteException;

    public static abstract class Stub extends Binder implements IMarketDownloadAidlInterface {
        private static final String DESCRIPTOR = "com.byd.appstore.aidl.IMarketDownloadAidlInterface";
        static final int TRANSACTION_basicTypes = 1;
        static final int TRANSACTION_getDownLoadNum = 2;
        static final int TRANSACTION_getPauseNum = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMarketDownloadAidlInterface asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IMarketDownloadAidlInterface)) {
                return (IMarketDownloadAidlInterface) iInterfaceQueryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                basicTypes(data.readInt(), data.readLong(), data.readInt() != 0, data.readFloat(), data.readDouble(), data.readString());
                reply.writeNoException();
                return true;
            }
            if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int downLoadNum = getDownLoadNum();
                reply.writeNoException();
                reply.writeInt(downLoadNum);
                return true;
            }
            if (code != 3) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            int pauseNum = getPauseNum();
            reply.writeNoException();
            reply.writeInt(pauseNum);
            return true;
        }

        private static class Proxy implements IMarketDownloadAidlInterface {
            public static IMarketDownloadAidlInterface sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.byd.appstore.aidl.IMarketDownloadAidlInterface
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(anInt);
                    parcelObtain.writeLong(aLong);
                    parcelObtain.writeInt(aBoolean ? 1 : 0);
                    parcelObtain.writeFloat(aFloat);
                    parcelObtain.writeDouble(aDouble);
                    parcelObtain.writeString(aString);
                    try {
                        if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().basicTypes(anInt, aLong, aBoolean, aFloat, aDouble, aString);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        } else {
                            parcelObtain2.readException();
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

            @Override // com.byd.appstore.aidl.IMarketDownloadAidlInterface
            public int getDownLoadNum() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownLoadNum();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.appstore.aidl.IMarketDownloadAidlInterface
            public int getPauseNum() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPauseNum();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMarketDownloadAidlInterface impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IMarketDownloadAidlInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
