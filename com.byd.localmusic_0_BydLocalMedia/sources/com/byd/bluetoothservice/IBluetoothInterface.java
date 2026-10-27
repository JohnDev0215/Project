package com.byd.bluetoothservice;

import android.bluetooth.BluetoothDevice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.bluetoothservice.IBTServiceCallBack;

/* JADX INFO: loaded from: classes5.dex */
public interface IBluetoothInterface extends IInterface {

    public static class Default implements IBluetoothInterface {
        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public void BT_Card_Slides_up() throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public void Next_Music() throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public void Pause_Music() throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public void Play_Music() throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public void Play_or_Pause() throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public void Pre_Music() throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public void StartSyncContacts() throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public void WidgetCardShow(boolean z) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public BluetoothDevice getA2dpConnectDevice() throws RemoteException {
            return null;
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public String getA2dpConnectDeviceName() throws RemoteException {
            return null;
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public String getArtist() throws RemoteException {
            return null;
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public int getBattery() throws RemoteException {
            return 0;
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public boolean getBluetoothEnable() throws RemoteException {
            return false;
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public String getBluetoothName() throws RemoteException {
            return null;
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public int getCallState() throws RemoteException {
            return 0;
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public long getCurrentTime() throws RemoteException {
            return 0L;
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public BluetoothDevice getHFPConnectDevice() throws RemoteException {
            return null;
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public String getHFPConnectDeviceName() throws RemoteException {
            return null;
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public String getIconUrl() throws RemoteException {
            return null;
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public String getMusicTile() throws RemoteException {
            return null;
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public int getPlay_state() throws RemoteException {
            return 0;
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public int getSyncContactsState() throws RemoteException {
            return 0;
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public long getTotal_time() throws RemoteException {
            return 0L;
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public boolean isA2DPConnect() throws RemoteException {
            return false;
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public boolean isHFPConnect() throws RemoteException {
            return false;
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public void registerBTServiceCallBack(IBTServiceCallBack iBTServiceCallBack) throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public void setBluetoothEnable(boolean z) throws RemoteException {
        }

        @Override // com.byd.bluetoothservice.IBluetoothInterface
        public void unRegisterBTServiceCallBack(IBTServiceCallBack iBTServiceCallBack) throws RemoteException {
        }
    }

    void BT_Card_Slides_up() throws RemoteException;

    void Next_Music() throws RemoteException;

    void Pause_Music() throws RemoteException;

    void Play_Music() throws RemoteException;

    void Play_or_Pause() throws RemoteException;

    void Pre_Music() throws RemoteException;

    void StartSyncContacts() throws RemoteException;

    void WidgetCardShow(boolean z) throws RemoteException;

    BluetoothDevice getA2dpConnectDevice() throws RemoteException;

    String getA2dpConnectDeviceName() throws RemoteException;

    String getArtist() throws RemoteException;

    int getBattery() throws RemoteException;

    boolean getBluetoothEnable() throws RemoteException;

    String getBluetoothName() throws RemoteException;

    int getCallState() throws RemoteException;

    long getCurrentTime() throws RemoteException;

    BluetoothDevice getHFPConnectDevice() throws RemoteException;

    String getHFPConnectDeviceName() throws RemoteException;

    String getIconUrl() throws RemoteException;

    String getMusicTile() throws RemoteException;

    int getPlay_state() throws RemoteException;

    int getSyncContactsState() throws RemoteException;

    long getTotal_time() throws RemoteException;

    boolean isA2DPConnect() throws RemoteException;

    boolean isHFPConnect() throws RemoteException;

    void registerBTServiceCallBack(IBTServiceCallBack iBTServiceCallBack) throws RemoteException;

    void setBluetoothEnable(boolean z) throws RemoteException;

    void unRegisterBTServiceCallBack(IBTServiceCallBack iBTServiceCallBack) throws RemoteException;

    public static abstract class Stub extends Binder implements IBluetoothInterface {
        private static final String DESCRIPTOR = "com.byd.bluetoothservice.IBluetoothInterface";
        static final int TRANSACTION_BT_Card_Slides_up = 24;
        static final int TRANSACTION_Next_Music = 22;
        static final int TRANSACTION_Pause_Music = 19;
        static final int TRANSACTION_Play_Music = 18;
        static final int TRANSACTION_Play_or_Pause = 20;
        static final int TRANSACTION_Pre_Music = 21;
        static final int TRANSACTION_StartSyncContacts = 26;
        static final int TRANSACTION_WidgetCardShow = 23;
        static final int TRANSACTION_getA2dpConnectDevice = 7;
        static final int TRANSACTION_getA2dpConnectDeviceName = 6;
        static final int TRANSACTION_getArtist = 13;
        static final int TRANSACTION_getBattery = 10;
        static final int TRANSACTION_getBluetoothEnable = 3;
        static final int TRANSACTION_getBluetoothName = 5;
        static final int TRANSACTION_getCallState = 11;
        static final int TRANSACTION_getCurrentTime = 17;
        static final int TRANSACTION_getHFPConnectDevice = 9;
        static final int TRANSACTION_getHFPConnectDeviceName = 8;
        static final int TRANSACTION_getIconUrl = 14;
        static final int TRANSACTION_getMusicTile = 12;
        static final int TRANSACTION_getPlay_state = 15;
        static final int TRANSACTION_getSyncContactsState = 25;
        static final int TRANSACTION_getTotal_time = 16;
        static final int TRANSACTION_isA2DPConnect = 1;
        static final int TRANSACTION_isHFPConnect = 2;
        static final int TRANSACTION_registerBTServiceCallBack = 27;
        static final int TRANSACTION_setBluetoothEnable = 4;
        static final int TRANSACTION_unRegisterBTServiceCallBack = 28;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBluetoothInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBluetoothInterface)) {
                return (IBluetoothInterface) iInterfaceQueryLocalInterface;
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
                    boolean zIsA2DPConnect = isA2DPConnect();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsA2DPConnect ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsHFPConnect = isHFPConnect();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsHFPConnect ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean bluetoothEnable = getBluetoothEnable();
                    parcel2.writeNoException();
                    parcel2.writeInt(bluetoothEnable ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBluetoothEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    String bluetoothName = getBluetoothName();
                    parcel2.writeNoException();
                    parcel2.writeString(bluetoothName);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String a2dpConnectDeviceName = getA2dpConnectDeviceName();
                    parcel2.writeNoException();
                    parcel2.writeString(a2dpConnectDeviceName);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    BluetoothDevice a2dpConnectDevice = getA2dpConnectDevice();
                    parcel2.writeNoException();
                    if (a2dpConnectDevice != null) {
                        parcel2.writeInt(1);
                        a2dpConnectDevice.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    String hFPConnectDeviceName = getHFPConnectDeviceName();
                    parcel2.writeNoException();
                    parcel2.writeString(hFPConnectDeviceName);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    BluetoothDevice hFPConnectDevice = getHFPConnectDevice();
                    parcel2.writeNoException();
                    if (hFPConnectDevice != null) {
                        parcel2.writeInt(1);
                        hFPConnectDevice.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int battery = getBattery();
                    parcel2.writeNoException();
                    parcel2.writeInt(battery);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int callState = getCallState();
                    parcel2.writeNoException();
                    parcel2.writeInt(callState);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    String musicTile = getMusicTile();
                    parcel2.writeNoException();
                    parcel2.writeString(musicTile);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    String artist = getArtist();
                    parcel2.writeNoException();
                    parcel2.writeString(artist);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    String iconUrl = getIconUrl();
                    parcel2.writeNoException();
                    parcel2.writeString(iconUrl);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    int play_state = getPlay_state();
                    parcel2.writeNoException();
                    parcel2.writeInt(play_state);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    long total_time = getTotal_time();
                    parcel2.writeNoException();
                    parcel2.writeLong(total_time);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    long currentTime = getCurrentTime();
                    parcel2.writeNoException();
                    parcel2.writeLong(currentTime);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    Play_Music();
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    Pause_Music();
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    Play_or_Pause();
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    Pre_Music();
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    Next_Music();
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    WidgetCardShow(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    BT_Card_Slides_up();
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    int syncContactsState = getSyncContactsState();
                    parcel2.writeNoException();
                    parcel2.writeInt(syncContactsState);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    StartSyncContacts();
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerBTServiceCallBack(IBTServiceCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterBTServiceCallBack(IBTServiceCallBack.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBluetoothInterface {
            public static IBluetoothInterface sDefaultImpl;
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

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public boolean isA2DPConnect() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isA2DPConnect();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public boolean isHFPConnect() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isHFPConnect();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public boolean getBluetoothEnable() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBluetoothEnable();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public void setBluetoothEnable(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBluetoothEnable(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public String getBluetoothName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBluetoothName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public String getA2dpConnectDeviceName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getA2dpConnectDeviceName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public BluetoothDevice getA2dpConnectDevice() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getA2dpConnectDevice();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public String getHFPConnectDeviceName() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHFPConnectDeviceName();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public BluetoothDevice getHFPConnectDevice() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHFPConnectDevice();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public int getBattery() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBattery();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public int getCallState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCallState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public String getMusicTile() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMusicTile();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public String getArtist() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getArtist();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public String getIconUrl() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIconUrl();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public int getPlay_state() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPlay_state();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public long getTotal_time() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTotal_time();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public long getCurrentTime() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentTime();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public void Play_Music() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().Play_Music();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public void Pause_Music() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().Pause_Music();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public void Play_or_Pause() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().Play_or_Pause();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public void Pre_Music() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().Pre_Music();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public void Next_Music() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().Next_Music();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public void WidgetCardShow(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().WidgetCardShow(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public void BT_Card_Slides_up() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().BT_Card_Slides_up();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public int getSyncContactsState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSyncContactsState();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public void StartSyncContacts() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().StartSyncContacts();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public void registerBTServiceCallBack(IBTServiceCallBack iBTServiceCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBTServiceCallBack != null ? iBTServiceCallBack.asBinder() : null);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerBTServiceCallBack(iBTServiceCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.IBluetoothInterface
            public void unRegisterBTServiceCallBack(IBTServiceCallBack iBTServiceCallBack) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBTServiceCallBack != null ? iBTServiceCallBack.asBinder() : null);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterBTServiceCallBack(iBTServiceCallBack);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothInterface iBluetoothInterface) {
            if (Proxy.sDefaultImpl != null || iBluetoothInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBluetoothInterface;
            return true;
        }

        public static IBluetoothInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
