package com.byd.bluetoothservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.info.BtCallInfo;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public interface IBTServiceCallBack extends IInterface {

    public static class Default implements IBTServiceCallBack {
        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void AppWidgetKeyBoardShow(boolean z) throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void BlueSwitch(int i) throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void CallState_Change(List<BtCallInfo> list) throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void Call_Activity_is_Top(boolean z) throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void SyncContactsState(int i) throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void Update_Call_Channel(int i) throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void Update_Call_Time(BtCallInfo btCallInfo) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void updateA2dpConnectState(int i) throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void updateBattery(int i) throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void updateCurrentTime(long j) throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void updateHFPConnectState(int i) throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void updatePlayState(int i) throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void updateTitle(String str, String str2, String str3, String str4) throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBTServiceCallBack
        public void updateTotalTime(long j) throws RemoteException {
        }
    }

    void AppWidgetKeyBoardShow(boolean z) throws RemoteException;

    void BlueSwitch(int i) throws RemoteException;

    void CallState_Change(List<BtCallInfo> list) throws RemoteException;

    void Call_Activity_is_Top(boolean z) throws RemoteException;

    void SyncContactsState(int i) throws RemoteException;

    void Update_Call_Channel(int i) throws RemoteException;

    void Update_Call_Time(BtCallInfo btCallInfo) throws RemoteException;

    void updateA2dpConnectState(int i) throws RemoteException;

    void updateBattery(int i) throws RemoteException;

    void updateCurrentTime(long j) throws RemoteException;

    void updateHFPConnectState(int i) throws RemoteException;

    void updatePlayState(int i) throws RemoteException;

    void updateTitle(String str, String str2, String str3, String str4) throws RemoteException;

    void updateTotalTime(long j) throws RemoteException;

    public static abstract class Stub extends Binder implements IBTServiceCallBack {
        private static final String DESCRIPTOR = "com.byd.bluetoothservice.IBTServiceCallBack";
        static final int TRANSACTION_AppWidgetKeyBoardShow = 13;
        static final int TRANSACTION_BlueSwitch = 12;
        static final int TRANSACTION_CallState_Change = 8;
        static final int TRANSACTION_Call_Activity_is_Top = 11;
        static final int TRANSACTION_SyncContactsState = 14;
        static final int TRANSACTION_Update_Call_Channel = 10;
        static final int TRANSACTION_Update_Call_Time = 9;
        static final int TRANSACTION_updateA2dpConnectState = 1;
        static final int TRANSACTION_updateBattery = 3;
        static final int TRANSACTION_updateCurrentTime = 6;
        static final int TRANSACTION_updateHFPConnectState = 2;
        static final int TRANSACTION_updatePlayState = 7;
        static final int TRANSACTION_updateTitle = 4;
        static final int TRANSACTION_updateTotalTime = 5;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBTServiceCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBTServiceCallBack)) {
                return (IBTServiceCallBack) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
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
                    updateA2dpConnectState(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateHFPConnectState(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateBattery(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateTitle(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateTotalTime(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateCurrentTime(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    updatePlayState(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    CallState_Change(parcel.createTypedArrayList(BtCallInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    Update_Call_Time(parcel.readInt() != 0 ? BtCallInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    Update_Call_Channel(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    Call_Activity_is_Top(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    BlueSwitch(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    AppWidgetKeyBoardShow(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    SyncContactsState(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBTServiceCallBack {
            public static IBTServiceCallBack sDefaultImpl;
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

            @Override // com.byd.bluetoothservice.IBTServiceCallBack
            public void updateA2dpConnectState(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateA2dpConnectState(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBTServiceCallBack
            public void updateHFPConnectState(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateHFPConnectState(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBTServiceCallBack
            public void updateBattery(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateBattery(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBTServiceCallBack
            public void updateTitle(String str, String str2, String str3, String str4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeString(str4);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateTitle(str, str2, str3, str4);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBTServiceCallBack
            public void updateTotalTime(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateTotalTime(j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBTServiceCallBack
            public void updateCurrentTime(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateCurrentTime(j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBTServiceCallBack
            public void updatePlayState(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updatePlayState(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBTServiceCallBack
            public void CallState_Change(List<BtCallInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().CallState_Change(list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBTServiceCallBack
            public void Update_Call_Time(BtCallInfo btCallInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (btCallInfo != null) {
                        parcelObtain.writeInt(1);
                        btCallInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().Update_Call_Time(btCallInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBTServiceCallBack
            public void Update_Call_Channel(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().Update_Call_Channel(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBTServiceCallBack
            public void Call_Activity_is_Top(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().Call_Activity_is_Top(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBTServiceCallBack
            public void BlueSwitch(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().BlueSwitch(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBTServiceCallBack
            public void AppWidgetKeyBoardShow(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().AppWidgetKeyBoardShow(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBTServiceCallBack
            public void SyncContactsState(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().SyncContactsState(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBTServiceCallBack iBTServiceCallBack) {
            if (Proxy.sDefaultImpl != null || iBTServiceCallBack == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBTServiceCallBack;
            return true;
        }

        public static IBTServiceCallBack getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
