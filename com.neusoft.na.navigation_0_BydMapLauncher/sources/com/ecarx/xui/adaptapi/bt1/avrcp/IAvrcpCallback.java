package com.ecarx.xui.adaptapi.bt1.avrcp;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IAvrcpCallback extends IInterface {
    void onAvrcp13EventBatteryStatusChanged(byte b) throws RemoteException;

    void onAvrcp13EventPlaybackPosChanged(long j) throws RemoteException;

    void onAvrcp13EventPlaybackStatusChanged(byte b) throws RemoteException;

    void onAvrcp13EventPlayerSettingChanged(byte[] bArr, byte[] bArr2) throws RemoteException;

    void onAvrcp13EventSystemStatusChanged(byte b) throws RemoteException;

    void onAvrcp13EventTrackChanged(long j) throws RemoteException;

    void onAvrcp13EventTrackReachedEnd() throws RemoteException;

    void onAvrcp13EventTrackReachedStart() throws RemoteException;

    void onAvrcp13RegisterEventWatcherFail(byte b) throws RemoteException;

    void onAvrcp13RegisterEventWatcherSuccess(byte b) throws RemoteException;

    void onAvrcp14EventAddressedPlayerChanged(int i, int i2) throws RemoteException;

    void onAvrcp14EventAvailablePlayerChanged() throws RemoteException;

    void onAvrcp14EventNowPlayingContentChanged() throws RemoteException;

    void onAvrcp14EventUidsChanged(int i) throws RemoteException;

    void onAvrcp14EventVolumeChanged(byte b) throws RemoteException;

    void onAvrcpErrorResponse(int i, int i2, byte b) throws RemoteException;

    void onAvrcpServiceReady() throws RemoteException;

    void onAvrcpStateChanged(String str, int i, int i2) throws RemoteException;

    void retAvrcp13CapabilitiesSupportEvent(byte[] bArr) throws RemoteException;

    void retAvrcp13ElementAttributesPlaying(int[] iArr, String[] strArr) throws RemoteException;

    void retAvrcp13PlayStatus(long j, long j2, byte b) throws RemoteException;

    void retAvrcp13PlayerSettingAttributesList(byte[] bArr) throws RemoteException;

    void retAvrcp13PlayerSettingCurrentValues(byte[] bArr, byte[] bArr2) throws RemoteException;

    void retAvrcp13PlayerSettingValuesList(byte b, byte[] bArr) throws RemoteException;

    void retAvrcp13SetPlayerSettingValueSuccess() throws RemoteException;

    void retAvrcp14AddToNowPlayingSuccess() throws RemoteException;

    void retAvrcp14ChangePathSuccess(long j) throws RemoteException;

    void retAvrcp14FolderItems(int i, long j) throws RemoteException;

    void retAvrcp14ItemAttributes(int[] iArr, String[] strArr) throws RemoteException;

    void retAvrcp14MediaItems(int i, long j) throws RemoteException;

    void retAvrcp14PlaySelectedItemSuccess() throws RemoteException;

    void retAvrcp14SearchResult(int i, long j) throws RemoteException;

    void retAvrcp14SetAbsoluteVolumeSuccess(byte b) throws RemoteException;

    void retAvrcp14SetAddressedPlayerSuccess() throws RemoteException;

    void retAvrcp14SetBrowsedPlayerSuccess(String[] strArr, int i, long j) throws RemoteException;

    public static abstract class Stub extends Binder implements IAvrcpCallback {
        private static final String DESCRIPTOR = "com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback";
        static final int TRANSACTION_onAvrcp13EventBatteryStatusChanged = 17;
        static final int TRANSACTION_onAvrcp13EventPlaybackPosChanged = 16;
        static final int TRANSACTION_onAvrcp13EventPlaybackStatusChanged = 12;
        static final int TRANSACTION_onAvrcp13EventPlayerSettingChanged = 19;
        static final int TRANSACTION_onAvrcp13EventSystemStatusChanged = 18;
        static final int TRANSACTION_onAvrcp13EventTrackChanged = 13;
        static final int TRANSACTION_onAvrcp13EventTrackReachedEnd = 14;
        static final int TRANSACTION_onAvrcp13EventTrackReachedStart = 15;
        static final int TRANSACTION_onAvrcp13RegisterEventWatcherFail = 11;
        static final int TRANSACTION_onAvrcp13RegisterEventWatcherSuccess = 10;
        static final int TRANSACTION_onAvrcp14EventAddressedPlayerChanged = 22;
        static final int TRANSACTION_onAvrcp14EventAvailablePlayerChanged = 21;
        static final int TRANSACTION_onAvrcp14EventNowPlayingContentChanged = 20;
        static final int TRANSACTION_onAvrcp14EventUidsChanged = 23;
        static final int TRANSACTION_onAvrcp14EventVolumeChanged = 24;
        static final int TRANSACTION_onAvrcpErrorResponse = 35;
        static final int TRANSACTION_onAvrcpServiceReady = 1;
        static final int TRANSACTION_onAvrcpStateChanged = 2;
        static final int TRANSACTION_retAvrcp13CapabilitiesSupportEvent = 3;
        static final int TRANSACTION_retAvrcp13ElementAttributesPlaying = 8;
        static final int TRANSACTION_retAvrcp13PlayStatus = 9;
        static final int TRANSACTION_retAvrcp13PlayerSettingAttributesList = 4;
        static final int TRANSACTION_retAvrcp13PlayerSettingCurrentValues = 6;
        static final int TRANSACTION_retAvrcp13PlayerSettingValuesList = 5;
        static final int TRANSACTION_retAvrcp13SetPlayerSettingValueSuccess = 7;
        static final int TRANSACTION_retAvrcp14AddToNowPlayingSuccess = 33;
        static final int TRANSACTION_retAvrcp14ChangePathSuccess = 29;
        static final int TRANSACTION_retAvrcp14FolderItems = 27;
        static final int TRANSACTION_retAvrcp14ItemAttributes = 30;
        static final int TRANSACTION_retAvrcp14MediaItems = 28;
        static final int TRANSACTION_retAvrcp14PlaySelectedItemSuccess = 31;
        static final int TRANSACTION_retAvrcp14SearchResult = 32;
        static final int TRANSACTION_retAvrcp14SetAbsoluteVolumeSuccess = 34;
        static final int TRANSACTION_retAvrcp14SetAddressedPlayerSuccess = 25;
        static final int TRANSACTION_retAvrcp14SetBrowsedPlayerSuccess = 26;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAvrcpCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAvrcpCallback)) {
                return (IAvrcpCallback) iInterfaceQueryLocalInterface;
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
                    onAvrcpServiceReady();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAvrcpStateChanged(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] bArrCreateByteArray = parcel.createByteArray();
                    retAvrcp13CapabilitiesSupportEvent(bArrCreateByteArray);
                    parcel2.writeNoException();
                    parcel2.writeByteArray(bArrCreateByteArray);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] bArrCreateByteArray2 = parcel.createByteArray();
                    retAvrcp13PlayerSettingAttributesList(bArrCreateByteArray2);
                    parcel2.writeNoException();
                    parcel2.writeByteArray(bArrCreateByteArray2);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte b = parcel.readByte();
                    byte[] bArrCreateByteArray3 = parcel.createByteArray();
                    retAvrcp13PlayerSettingValuesList(b, bArrCreateByteArray3);
                    parcel2.writeNoException();
                    parcel2.writeByteArray(bArrCreateByteArray3);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] bArrCreateByteArray4 = parcel.createByteArray();
                    byte[] bArrCreateByteArray5 = parcel.createByteArray();
                    retAvrcp13PlayerSettingCurrentValues(bArrCreateByteArray4, bArrCreateByteArray5);
                    parcel2.writeNoException();
                    parcel2.writeByteArray(bArrCreateByteArray4);
                    parcel2.writeByteArray(bArrCreateByteArray5);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    retAvrcp13SetPlayerSettingValueSuccess();
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] iArrCreateIntArray = parcel.createIntArray();
                    String[] strArrCreateStringArray = parcel.createStringArray();
                    retAvrcp13ElementAttributesPlaying(iArrCreateIntArray, strArrCreateStringArray);
                    parcel2.writeNoException();
                    parcel2.writeIntArray(iArrCreateIntArray);
                    parcel2.writeStringArray(strArrCreateStringArray);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    retAvrcp13PlayStatus(parcel.readLong(), parcel.readLong(), parcel.readByte());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAvrcp13RegisterEventWatcherSuccess(parcel.readByte());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAvrcp13RegisterEventWatcherFail(parcel.readByte());
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAvrcp13EventPlaybackStatusChanged(parcel.readByte());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAvrcp13EventTrackChanged(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAvrcp13EventTrackReachedEnd();
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAvrcp13EventTrackReachedStart();
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAvrcp13EventPlaybackPosChanged(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAvrcp13EventBatteryStatusChanged(parcel.readByte());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAvrcp13EventSystemStatusChanged(parcel.readByte());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] bArrCreateByteArray6 = parcel.createByteArray();
                    byte[] bArrCreateByteArray7 = parcel.createByteArray();
                    onAvrcp13EventPlayerSettingChanged(bArrCreateByteArray6, bArrCreateByteArray7);
                    parcel2.writeNoException();
                    parcel2.writeByteArray(bArrCreateByteArray6);
                    parcel2.writeByteArray(bArrCreateByteArray7);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAvrcp14EventNowPlayingContentChanged();
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAvrcp14EventAvailablePlayerChanged();
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAvrcp14EventAddressedPlayerChanged(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAvrcp14EventUidsChanged(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAvrcp14EventVolumeChanged(parcel.readByte());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    retAvrcp14SetAddressedPlayerSuccess();
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    String[] strArrCreateStringArray2 = parcel.createStringArray();
                    retAvrcp14SetBrowsedPlayerSuccess(strArrCreateStringArray2, parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(strArrCreateStringArray2);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    retAvrcp14FolderItems(parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    retAvrcp14MediaItems(parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    retAvrcp14ChangePathSuccess(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] iArrCreateIntArray2 = parcel.createIntArray();
                    String[] strArrCreateStringArray3 = parcel.createStringArray();
                    retAvrcp14ItemAttributes(iArrCreateIntArray2, strArrCreateStringArray3);
                    parcel2.writeNoException();
                    parcel2.writeIntArray(iArrCreateIntArray2);
                    parcel2.writeStringArray(strArrCreateStringArray3);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    retAvrcp14PlaySelectedItemSuccess();
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    retAvrcp14SearchResult(parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    retAvrcp14AddToNowPlayingSuccess();
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    retAvrcp14SetAbsoluteVolumeSuccess(parcel.readByte());
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAvrcpErrorResponse(parcel.readInt(), parcel.readInt(), parcel.readByte());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAvrcpCallback {
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

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void onAvrcpServiceReady() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void onAvrcpStateChanged(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void retAvrcp13CapabilitiesSupportEvent(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    parcelObtain2.readByteArray(bArr);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void retAvrcp13PlayerSettingAttributesList(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    parcelObtain2.readByteArray(bArr);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void retAvrcp13PlayerSettingValuesList(byte b, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByte(b);
                    parcelObtain.writeByteArray(bArr);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    parcelObtain2.readByteArray(bArr);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void retAvrcp13PlayerSettingCurrentValues(byte[] bArr, byte[] bArr2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeByteArray(bArr2);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    parcelObtain2.readByteArray(bArr);
                    parcelObtain2.readByteArray(bArr2);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void retAvrcp13SetPlayerSettingValueSuccess() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void retAvrcp13ElementAttributesPlaying(int[] iArr, String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    parcelObtain.writeStringArray(strArr);
                    this.mRemote.transact(8, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    parcelObtain2.readIntArray(iArr);
                    parcelObtain2.readStringArray(strArr);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void retAvrcp13PlayStatus(long j, long j2, byte b) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    parcelObtain.writeByte(b);
                    this.mRemote.transact(9, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void onAvrcp13RegisterEventWatcherSuccess(byte b) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByte(b);
                    this.mRemote.transact(10, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void onAvrcp13RegisterEventWatcherFail(byte b) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByte(b);
                    this.mRemote.transact(11, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void onAvrcp13EventPlaybackStatusChanged(byte b) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByte(b);
                    this.mRemote.transact(12, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void onAvrcp13EventTrackChanged(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    this.mRemote.transact(13, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void onAvrcp13EventTrackReachedEnd() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(14, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void onAvrcp13EventTrackReachedStart() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(15, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void onAvrcp13EventPlaybackPosChanged(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    this.mRemote.transact(16, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void onAvrcp13EventBatteryStatusChanged(byte b) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByte(b);
                    this.mRemote.transact(17, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void onAvrcp13EventSystemStatusChanged(byte b) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByte(b);
                    this.mRemote.transact(18, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void onAvrcp13EventPlayerSettingChanged(byte[] bArr, byte[] bArr2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeByteArray(bArr2);
                    this.mRemote.transact(19, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    parcelObtain2.readByteArray(bArr);
                    parcelObtain2.readByteArray(bArr2);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void onAvrcp14EventNowPlayingContentChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(20, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void onAvrcp14EventAvailablePlayerChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(21, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void onAvrcp14EventAddressedPlayerChanged(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    this.mRemote.transact(22, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void onAvrcp14EventUidsChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(23, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void onAvrcp14EventVolumeChanged(byte b) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByte(b);
                    this.mRemote.transact(24, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void retAvrcp14SetAddressedPlayerSuccess() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(25, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void retAvrcp14SetBrowsedPlayerSuccess(String[] strArr, int i, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    this.mRemote.transact(26, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    parcelObtain2.readStringArray(strArr);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void retAvrcp14FolderItems(int i, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    this.mRemote.transact(27, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void retAvrcp14MediaItems(int i, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    this.mRemote.transact(28, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void retAvrcp14ChangePathSuccess(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    this.mRemote.transact(29, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void retAvrcp14ItemAttributes(int[] iArr, String[] strArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    parcelObtain.writeStringArray(strArr);
                    this.mRemote.transact(30, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    parcelObtain2.readIntArray(iArr);
                    parcelObtain2.readStringArray(strArr);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void retAvrcp14PlaySelectedItemSuccess() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(31, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void retAvrcp14SearchResult(int i, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    this.mRemote.transact(32, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void retAvrcp14AddToNowPlayingSuccess() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(33, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void retAvrcp14SetAbsoluteVolumeSuccess(byte b) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByte(b);
                    this.mRemote.transact(34, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.avrcp.IAvrcpCallback
            public void onAvrcpErrorResponse(int i, int i2, byte b) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeByte(b);
                    this.mRemote.transact(35, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
