package com.ecarx.xui.adaptapi.bt1.avrcp;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback;

/* JADX INFO: loaded from: classes2.dex */
public interface IAvrcp extends IInterface {
    String getAvrcpConnectedAddress() throws RemoteException;

    int getAvrcpConnectionState() throws RemoteException;

    String getCurrentAttribute(int i) throws RemoteException;

    boolean isAvrcp13Supported(String str) throws RemoteException;

    boolean isAvrcp14BrowsingChannelEstablished() throws RemoteException;

    boolean isAvrcp14Supported(String str) throws RemoteException;

    boolean isAvrcpConnected() throws RemoteException;

    boolean isAvrcpServiceReady() throws RemoteException;

    boolean registerAvrcpCallback(IAvrcpCallback iAvrcpCallback) throws RemoteException;

    boolean reqAvrcp13GetCapabilitiesSupportEvent() throws RemoteException;

    boolean reqAvrcp13GetElementAttributesPlaying() throws RemoteException;

    boolean reqAvrcp13GetPlayStatus() throws RemoteException;

    boolean reqAvrcp13GetPlayerSettingAttributesList() throws RemoteException;

    boolean reqAvrcp13GetPlayerSettingCurrentValues() throws RemoteException;

    boolean reqAvrcp13GetPlayerSettingValuesList(byte b) throws RemoteException;

    boolean reqAvrcp13NextGroup() throws RemoteException;

    boolean reqAvrcp13PreviousGroup() throws RemoteException;

    boolean reqAvrcp13SetPlayerSettingValue(byte b, byte b2) throws RemoteException;

    boolean reqAvrcp14AddToNowPlaying(byte b, int i, long j) throws RemoteException;

    boolean reqAvrcp14ChangePath(int i, long j, byte b) throws RemoteException;

    boolean reqAvrcp14GetFolderItems(byte b) throws RemoteException;

    boolean reqAvrcp14GetItemAttributes(byte b, int i, long j) throws RemoteException;

    boolean reqAvrcp14PlaySelectedItem(byte b, int i, long j) throws RemoteException;

    boolean reqAvrcp14Search(String str) throws RemoteException;

    boolean reqAvrcp14SetAbsoluteVolume(byte b) throws RemoteException;

    boolean reqAvrcp14SetAddressedPlayer(int i) throws RemoteException;

    boolean reqAvrcp14SetBrowsedPlayer(int i) throws RemoteException;

    boolean reqAvrcpBackward() throws RemoteException;

    boolean reqAvrcpConnect(String str) throws RemoteException;

    boolean reqAvrcpDisconnect(String str) throws RemoteException;

    boolean reqAvrcpForward() throws RemoteException;

    boolean reqAvrcpPause() throws RemoteException;

    boolean reqAvrcpPlay() throws RemoteException;

    boolean reqAvrcpQueryVersion(String str) throws RemoteException;

    boolean reqAvrcpRegisterEventWatcher(byte b, long j) throws RemoteException;

    boolean reqAvrcpStartFastForward() throws RemoteException;

    boolean reqAvrcpStartRewind() throws RemoteException;

    boolean reqAvrcpStop() throws RemoteException;

    boolean reqAvrcpStopFastForward() throws RemoteException;

    boolean reqAvrcpStopRewind() throws RemoteException;

    boolean reqAvrcpUnregisterEventWatcher(byte b) throws RemoteException;

    boolean reqAvrcpVolumeDown() throws RemoteException;

    boolean reqAvrcpVolumeUp() throws RemoteException;

    boolean unregisterAvrcpCallback(IAvrcpCallback iAvrcpCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IAvrcp {
        private static final String DESCRIPTOR = "com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp";
        static final int TRANSACTION_getAvrcpConnectedAddress = 7;
        static final int TRANSACTION_getAvrcpConnectionState = 5;
        static final int TRANSACTION_getCurrentAttribute = 2;
        static final int TRANSACTION_isAvrcp13Supported = 10;
        static final int TRANSACTION_isAvrcp14BrowsingChannelEstablished = 34;
        static final int TRANSACTION_isAvrcp14Supported = 11;
        static final int TRANSACTION_isAvrcpConnected = 6;
        static final int TRANSACTION_isAvrcpServiceReady = 1;
        static final int TRANSACTION_registerAvrcpCallback = 3;
        static final int TRANSACTION_reqAvrcp13GetCapabilitiesSupportEvent = 23;
        static final int TRANSACTION_reqAvrcp13GetElementAttributesPlaying = 28;
        static final int TRANSACTION_reqAvrcp13GetPlayStatus = 29;
        static final int TRANSACTION_reqAvrcp13GetPlayerSettingAttributesList = 24;
        static final int TRANSACTION_reqAvrcp13GetPlayerSettingCurrentValues = 26;
        static final int TRANSACTION_reqAvrcp13GetPlayerSettingValuesList = 25;
        static final int TRANSACTION_reqAvrcp13NextGroup = 32;
        static final int TRANSACTION_reqAvrcp13PreviousGroup = 33;
        static final int TRANSACTION_reqAvrcp13SetPlayerSettingValue = 27;
        static final int TRANSACTION_reqAvrcp14AddToNowPlaying = 42;
        static final int TRANSACTION_reqAvrcp14ChangePath = 38;
        static final int TRANSACTION_reqAvrcp14GetFolderItems = 37;
        static final int TRANSACTION_reqAvrcp14GetItemAttributes = 39;
        static final int TRANSACTION_reqAvrcp14PlaySelectedItem = 40;
        static final int TRANSACTION_reqAvrcp14Search = 41;
        static final int TRANSACTION_reqAvrcp14SetAbsoluteVolume = 43;
        static final int TRANSACTION_reqAvrcp14SetAddressedPlayer = 35;
        static final int TRANSACTION_reqAvrcp14SetBrowsedPlayer = 36;
        static final int TRANSACTION_reqAvrcpBackward = 16;
        static final int TRANSACTION_reqAvrcpConnect = 8;
        static final int TRANSACTION_reqAvrcpDisconnect = 9;
        static final int TRANSACTION_reqAvrcpForward = 15;
        static final int TRANSACTION_reqAvrcpPause = 14;
        static final int TRANSACTION_reqAvrcpPlay = 12;
        static final int TRANSACTION_reqAvrcpQueryVersion = 44;
        static final int TRANSACTION_reqAvrcpRegisterEventWatcher = 30;
        static final int TRANSACTION_reqAvrcpStartFastForward = 19;
        static final int TRANSACTION_reqAvrcpStartRewind = 21;
        static final int TRANSACTION_reqAvrcpStop = 13;
        static final int TRANSACTION_reqAvrcpStopFastForward = 20;
        static final int TRANSACTION_reqAvrcpStopRewind = 22;
        static final int TRANSACTION_reqAvrcpUnregisterEventWatcher = 31;
        static final int TRANSACTION_reqAvrcpVolumeDown = 18;
        static final int TRANSACTION_reqAvrcpVolumeUp = 17;
        static final int TRANSACTION_unregisterAvrcpCallback = 4;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAvrcp asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAvrcp)) {
                return (IAvrcp) iInterfaceQueryLocalInterface;
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
                    boolean zIsAvrcpServiceReady = isAvrcpServiceReady();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAvrcpServiceReady ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String currentAttribute = getCurrentAttribute(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(currentAttribute);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRegisterAvrcpCallback = registerAvrcpCallback(IAvrcpCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterAvrcpCallback ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUnregisterAvrcpCallback = unregisterAvrcpCallback(IAvrcpCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUnregisterAvrcpCallback ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int avrcpConnectionState = getAvrcpConnectionState();
                    parcel2.writeNoException();
                    parcel2.writeInt(avrcpConnectionState);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAvrcpConnected = isAvrcpConnected();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAvrcpConnected ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    String avrcpConnectedAddress = getAvrcpConnectedAddress();
                    parcel2.writeNoException();
                    parcel2.writeString(avrcpConnectedAddress);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcpConnect = reqAvrcpConnect(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcpConnect ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcpDisconnect = reqAvrcpDisconnect(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcpDisconnect ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAvrcp13Supported = isAvrcp13Supported(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAvrcp13Supported ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAvrcp14Supported = isAvrcp14Supported(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAvrcp14Supported ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcpPlay = reqAvrcpPlay();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcpPlay ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcpStop = reqAvrcpStop();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcpStop ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcpPause = reqAvrcpPause();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcpPause ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcpForward = reqAvrcpForward();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcpForward ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcpBackward = reqAvrcpBackward();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcpBackward ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcpVolumeUp = reqAvrcpVolumeUp();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcpVolumeUp ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcpVolumeDown = reqAvrcpVolumeDown();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcpVolumeDown ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcpStartFastForward = reqAvrcpStartFastForward();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcpStartFastForward ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcpStopFastForward = reqAvrcpStopFastForward();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcpStopFastForward ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcpStartRewind = reqAvrcpStartRewind();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcpStartRewind ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcpStopRewind = reqAvrcpStopRewind();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcpStopRewind ? 1 : 0);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcp13GetCapabilitiesSupportEvent = reqAvrcp13GetCapabilitiesSupportEvent();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcp13GetCapabilitiesSupportEvent ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcp13GetPlayerSettingAttributesList = reqAvrcp13GetPlayerSettingAttributesList();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcp13GetPlayerSettingAttributesList ? 1 : 0);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcp13GetPlayerSettingValuesList = reqAvrcp13GetPlayerSettingValuesList(parcel.readByte());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcp13GetPlayerSettingValuesList ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcp13GetPlayerSettingCurrentValues = reqAvrcp13GetPlayerSettingCurrentValues();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcp13GetPlayerSettingCurrentValues ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcp13SetPlayerSettingValue = reqAvrcp13SetPlayerSettingValue(parcel.readByte(), parcel.readByte());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcp13SetPlayerSettingValue ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcp13GetElementAttributesPlaying = reqAvrcp13GetElementAttributesPlaying();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcp13GetElementAttributesPlaying ? 1 : 0);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcp13GetPlayStatus = reqAvrcp13GetPlayStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcp13GetPlayStatus ? 1 : 0);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcpRegisterEventWatcher = reqAvrcpRegisterEventWatcher(parcel.readByte(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcpRegisterEventWatcher ? 1 : 0);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcpUnregisterEventWatcher = reqAvrcpUnregisterEventWatcher(parcel.readByte());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcpUnregisterEventWatcher ? 1 : 0);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcp13NextGroup = reqAvrcp13NextGroup();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcp13NextGroup ? 1 : 0);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcp13PreviousGroup = reqAvrcp13PreviousGroup();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcp13PreviousGroup ? 1 : 0);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAvrcp14BrowsingChannelEstablished = isAvrcp14BrowsingChannelEstablished();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAvrcp14BrowsingChannelEstablished ? 1 : 0);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcp14SetAddressedPlayer = reqAvrcp14SetAddressedPlayer(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcp14SetAddressedPlayer ? 1 : 0);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcp14SetBrowsedPlayer = reqAvrcp14SetBrowsedPlayer(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcp14SetBrowsedPlayer ? 1 : 0);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcp14GetFolderItems = reqAvrcp14GetFolderItems(parcel.readByte());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcp14GetFolderItems ? 1 : 0);
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcp14ChangePath = reqAvrcp14ChangePath(parcel.readInt(), parcel.readLong(), parcel.readByte());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcp14ChangePath ? 1 : 0);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcp14GetItemAttributes = reqAvrcp14GetItemAttributes(parcel.readByte(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcp14GetItemAttributes ? 1 : 0);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcp14PlaySelectedItem = reqAvrcp14PlaySelectedItem(parcel.readByte(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcp14PlaySelectedItem ? 1 : 0);
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcp14Search = reqAvrcp14Search(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcp14Search ? 1 : 0);
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcp14AddToNowPlaying = reqAvrcp14AddToNowPlaying(parcel.readByte(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcp14AddToNowPlaying ? 1 : 0);
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcp14SetAbsoluteVolume = reqAvrcp14SetAbsoluteVolume(parcel.readByte());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcp14SetAbsoluteVolume ? 1 : 0);
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqAvrcpQueryVersion = reqAvrcpQueryVersion(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqAvrcpQueryVersion ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAvrcp {
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

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean isAvrcpServiceReady() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public String getCurrentAttribute(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean registerAvrcpCallback(IAvrcpCallback iAvrcpCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAvrcpCallback != null ? iAvrcpCallback.asBinder() : null);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean unregisterAvrcpCallback(IAvrcpCallback iAvrcpCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAvrcpCallback != null ? iAvrcpCallback.asBinder() : null);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public int getAvrcpConnectionState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean isAvrcpConnected() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public String getAvrcpConnectedAddress() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcpConnect(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcpDisconnect(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(9, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean isAvrcp13Supported(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(10, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean isAvrcp14Supported(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(11, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcpPlay() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(12, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcpStop() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcpPause() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(14, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcpForward() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(15, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcpBackward() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcpVolumeUp() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(17, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcpVolumeDown() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcpStartFastForward() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(19, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcpStopFastForward() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(20, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcpStartRewind() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(21, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcpStopRewind() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(22, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcp13GetCapabilitiesSupportEvent() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(23, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcp13GetPlayerSettingAttributesList() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(24, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcp13GetPlayerSettingValuesList(byte b) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByte(b);
                    this.mRemote.transact(25, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcp13GetPlayerSettingCurrentValues() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(26, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcp13SetPlayerSettingValue(byte b, byte b2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByte(b);
                    parcelObtain.writeByte(b2);
                    this.mRemote.transact(27, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcp13GetElementAttributesPlaying() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(28, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcp13GetPlayStatus() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(29, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcpRegisterEventWatcher(byte b, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByte(b);
                    parcelObtain.writeLong(j);
                    this.mRemote.transact(30, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcpUnregisterEventWatcher(byte b) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByte(b);
                    this.mRemote.transact(31, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcp13NextGroup() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(32, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcp13PreviousGroup() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(33, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean isAvrcp14BrowsingChannelEstablished() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(34, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcp14SetAddressedPlayer(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(35, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcp14SetBrowsedPlayer(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(36, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcp14GetFolderItems(byte b) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByte(b);
                    this.mRemote.transact(37, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcp14ChangePath(int i, long j, byte b) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeByte(b);
                    this.mRemote.transact(38, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcp14GetItemAttributes(byte b, int i, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByte(b);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    this.mRemote.transact(39, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcp14PlaySelectedItem(byte b, int i, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByte(b);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    this.mRemote.transact(40, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcp14Search(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(41, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcp14AddToNowPlaying(byte b, int i, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByte(b);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    this.mRemote.transact(42, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcp14SetAbsoluteVolume(byte b) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByte(b);
                    this.mRemote.transact(43, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcp
            public boolean reqAvrcpQueryVersion(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(44, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
