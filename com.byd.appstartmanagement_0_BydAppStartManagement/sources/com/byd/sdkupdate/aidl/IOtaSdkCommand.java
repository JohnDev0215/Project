package com.byd.sdkupdate.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.sdkupdate.aidl.IOtaSdkCallback;

/* JADX INFO: loaded from: classes2.dex */
public interface IOtaSdkCommand extends IInterface {

    public static class Default implements IOtaSdkCommand {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
        public void basicTypes(int i, long j, boolean z, float f, double d2, String str) throws RemoteException {
        }

        @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
        public String getCurVersion() throws RemoteException {
            return null;
        }

        @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
        public String getLatestVersion() throws RemoteException {
            return null;
        }

        @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
        public boolean getUpdateFlag() throws RemoteException {
            return false;
        }

        @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
        public long getUpdateTime() throws RemoteException {
            return 0L;
        }

        @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
        public void registerCallback(IOtaSdkCallback iOtaSdkCallback) throws RemoteException {
        }

        @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
        public void setCancelDelay() throws RemoteException {
        }

        @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
        public void setOtaUpdate() throws RemoteException {
        }

        @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
        public void setUserClickCheckState() throws RemoteException {
        }

        @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
        public void unregisterCallback(IOtaSdkCallback iOtaSdkCallback) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IOtaSdkCommand {
        private static final String DESCRIPTOR = "com.byd.sdkupdate.aidl.IOtaSdkCommand";
        public static final int TRANSACTION_basicTypes = 10;
        public static final int TRANSACTION_getCurVersion = 1;
        public static final int TRANSACTION_getLatestVersion = 2;
        public static final int TRANSACTION_getUpdateFlag = 3;
        public static final int TRANSACTION_getUpdateTime = 4;
        public static final int TRANSACTION_registerCallback = 8;
        public static final int TRANSACTION_setCancelDelay = 7;
        public static final int TRANSACTION_setOtaUpdate = 5;
        public static final int TRANSACTION_setUserClickCheckState = 6;
        public static final int TRANSACTION_unregisterCallback = 9;

        public static class Proxy implements IOtaSdkCommand {
            public static IOtaSdkCommand sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
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
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        Stub.getDefaultImpl().basicTypes(i, j, z, f, d2, str);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
            public String getCurVersion() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurVersion();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
            public String getLatestVersion() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLatestVersion();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
            public boolean getUpdateFlag() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUpdateFlag();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
            public long getUpdateTime() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUpdateTime();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
            public void registerCallback(IOtaSdkCallback iOtaSdkCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iOtaSdkCallback != null ? iOtaSdkCallback.asBinder() : null);
                    if (this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().registerCallback(iOtaSdkCallback);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
            public void setCancelDelay() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().setCancelDelay();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
            public void setOtaUpdate() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().setOtaUpdate();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
            public void setUserClickCheckState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().setUserClickCheckState();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.sdkupdate.aidl.IOtaSdkCommand
            public void unregisterCallback(IOtaSdkCallback iOtaSdkCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iOtaSdkCallback != null ? iOtaSdkCallback.asBinder() : null);
                    if (this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) || Stub.getDefaultImpl() == null) {
                        parcelObtain2.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterCallback(iOtaSdkCallback);
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

        public static IOtaSdkCommand asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IOtaSdkCommand)) ? new Proxy(iBinder) : (IOtaSdkCommand) iInterfaceQueryLocalInterface;
        }

        public static IOtaSdkCommand getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IOtaSdkCommand iOtaSdkCommand) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iOtaSdkCommand == null) {
                return false;
            }
            Proxy.sDefaultImpl = iOtaSdkCommand;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
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
                    String curVersion = getCurVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(curVersion);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String latestVersion = getLatestVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(latestVersion);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean updateFlag = getUpdateFlag();
                    parcel2.writeNoException();
                    parcel2.writeInt(updateFlag ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    long updateTime = getUpdateTime();
                    parcel2.writeNoException();
                    parcel2.writeLong(updateTime);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    setOtaUpdate();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUserClickCheckState();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCancelDelay();
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerCallback(IOtaSdkCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterCallback(IOtaSdkCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    basicTypes(parcel.readInt(), parcel.readLong(), parcel.readInt() != 0, parcel.readFloat(), parcel.readDouble(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void basicTypes(int i, long j, boolean z, float f, double d2, String str) throws RemoteException;

    String getCurVersion() throws RemoteException;

    String getLatestVersion() throws RemoteException;

    boolean getUpdateFlag() throws RemoteException;

    long getUpdateTime() throws RemoteException;

    void registerCallback(IOtaSdkCallback iOtaSdkCallback) throws RemoteException;

    void setCancelDelay() throws RemoteException;

    void setOtaUpdate() throws RemoteException;

    void setUserClickCheckState() throws RemoteException;

    void unregisterCallback(IOtaSdkCallback iOtaSdkCallback) throws RemoteException;
}
