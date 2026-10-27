package com.byd.autoiot.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IAutoiotEventListener extends IInterface {
    public static final String DESCRIPTOR = "com.byd.autoiot.service.IAutoiotEventListener";

    public static class Default implements IAutoiotEventListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.autoiot.service.IAutoiotEventListener
        public void onDeviceChanged(String str, String str2, String str3) throws RemoteException {
        }

        @Override // com.byd.autoiot.service.IAutoiotEventListener
        public void onDeviceChangedWithData(String str, String str2, String str3, AutoiotProfile autoiotProfile) throws RemoteException {
        }

        @Override // com.byd.autoiot.service.IAutoiotEventListener
        public void onDeviceConnected(String str) throws RemoteException {
        }

        @Override // com.byd.autoiot.service.IAutoiotEventListener
        public void onDeviceDeleted(String str) throws RemoteException {
        }

        @Override // com.byd.autoiot.service.IAutoiotEventListener
        public void onDeviceDisconnected(String str) throws RemoteException {
        }

        @Override // com.byd.autoiot.service.IAutoiotEventListener
        public void onDeviceLeave(String str) throws RemoteException {
        }

        @Override // com.byd.autoiot.service.IAutoiotEventListener
        public void onNewDeviceDiscovered(List<String> list) throws RemoteException {
        }

        @Override // com.byd.autoiot.service.IAutoiotEventListener
        public void onReadResult(String str, String str2, String str3, AutoiotProfile autoiotProfile) throws RemoteException {
        }

        @Override // com.byd.autoiot.service.IAutoiotEventListener
        public void onWriteResult(String str, String str2, String str3, boolean z) throws RemoteException {
        }
    }

    void onDeviceChanged(String str, String str2, String str3) throws RemoteException;

    void onDeviceChangedWithData(String str, String str2, String str3, AutoiotProfile autoiotProfile) throws RemoteException;

    void onDeviceConnected(String str) throws RemoteException;

    void onDeviceDeleted(String str) throws RemoteException;

    void onDeviceDisconnected(String str) throws RemoteException;

    void onDeviceLeave(String str) throws RemoteException;

    void onNewDeviceDiscovered(List<String> list) throws RemoteException;

    void onReadResult(String str, String str2, String str3, AutoiotProfile autoiotProfile) throws RemoteException;

    void onWriteResult(String str, String str2, String str3, boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IAutoiotEventListener {
        static final int TRANSACTION_onDeviceChanged = 6;
        static final int TRANSACTION_onDeviceChangedWithData = 9;
        static final int TRANSACTION_onDeviceConnected = 4;
        static final int TRANSACTION_onDeviceDeleted = 3;
        static final int TRANSACTION_onDeviceDisconnected = 5;
        static final int TRANSACTION_onDeviceLeave = 2;
        static final int TRANSACTION_onNewDeviceDiscovered = 1;
        static final int TRANSACTION_onReadResult = 7;
        static final int TRANSACTION_onWriteResult = 8;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IAutoiotEventListener.DESCRIPTOR);
        }

        public static IAutoiotEventListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IAutoiotEventListener.DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAutoiotEventListener)) {
                return (IAutoiotEventListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(IAutoiotEventListener.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(IAutoiotEventListener.DESCRIPTOR);
                    onNewDeviceDiscovered(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(IAutoiotEventListener.DESCRIPTOR);
                    onDeviceLeave(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(IAutoiotEventListener.DESCRIPTOR);
                    onDeviceDeleted(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(IAutoiotEventListener.DESCRIPTOR);
                    onDeviceConnected(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(IAutoiotEventListener.DESCRIPTOR);
                    onDeviceDisconnected(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(IAutoiotEventListener.DESCRIPTOR);
                    onDeviceChanged(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(IAutoiotEventListener.DESCRIPTOR);
                    onReadResult(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? AutoiotProfile.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(IAutoiotEventListener.DESCRIPTOR);
                    onWriteResult(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(IAutoiotEventListener.DESCRIPTOR);
                    onDeviceChangedWithData(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? AutoiotProfile.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAutoiotEventListener {
            public static IAutoiotEventListener sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return IAutoiotEventListener.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.byd.autoiot.service.IAutoiotEventListener
            public void onNewDeviceDiscovered(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotEventListener.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNewDeviceDiscovered(list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotEventListener
            public void onDeviceLeave(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotEventListener.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceLeave(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotEventListener
            public void onDeviceDeleted(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotEventListener.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceDeleted(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotEventListener
            public void onDeviceConnected(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotEventListener.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceConnected(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotEventListener
            public void onDeviceDisconnected(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotEventListener.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceDisconnected(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotEventListener
            public void onDeviceChanged(String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotEventListener.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceChanged(str, str2, str3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotEventListener
            public void onReadResult(String str, String str2, String str3, AutoiotProfile autoiotProfile) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotEventListener.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (autoiotProfile != null) {
                        parcelObtain.writeInt(1);
                        autoiotProfile.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onReadResult(str, str2, str3, autoiotProfile);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotEventListener
            public void onWriteResult(String str, String str2, String str3, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotEventListener.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onWriteResult(str, str2, str3, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.autoiot.service.IAutoiotEventListener
            public void onDeviceChangedWithData(String str, String str2, String str3, AutoiotProfile autoiotProfile) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IAutoiotEventListener.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (autoiotProfile != null) {
                        parcelObtain.writeInt(1);
                        autoiotProfile.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceChangedWithData(str, str2, str3, autoiotProfile);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAutoiotEventListener iAutoiotEventListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAutoiotEventListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAutoiotEventListener;
            return true;
        }

        public static IAutoiotEventListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
