package com.ecarx.xui.adaptapi.bt1.hfp;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ecarx.xui.adaptapi.bt1.hfp.IHfpCallback;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IHfp extends IInterface {
    int getHfpAudioConnectionState() throws RemoteException;

    List<HfpClientCall> getHfpCallList() throws RemoteException;

    String getHfpConnectedAddress() throws RemoteException;

    int getHfpConnectionState() throws RemoteException;

    int getHfpRemoteBatteryIndicator() throws RemoteException;

    String getHfpRemoteNetworkOperator() throws RemoteException;

    int getHfpRemoteSignalStrength() throws RemoteException;

    String getHfpRemoteSubscriberNumber() throws RemoteException;

    boolean isHfpConnected() throws RemoteException;

    boolean isHfpInBandRingtoneSupport() throws RemoteException;

    boolean isHfpMicMute() throws RemoteException;

    boolean isHfpRemoteOnRoaming() throws RemoteException;

    boolean isHfpRemoteTelecomServiceOn() throws RemoteException;

    boolean isHfpRemoteVoiceDialOn() throws RemoteException;

    boolean isHfpServiceReady() throws RemoteException;

    void muteHfpMic(boolean z) throws RemoteException;

    void pauseHfpRender() throws RemoteException;

    boolean registerHfpCallback(IHfpCallback iHfpCallback) throws RemoteException;

    boolean reqHfpAnswerCall(int i) throws RemoteException;

    boolean reqHfpAudioTransferToCarkit() throws RemoteException;

    boolean reqHfpAudioTransferToPhone() throws RemoteException;

    boolean reqHfpConnect(String str) throws RemoteException;

    boolean reqHfpDialCall(String str) throws RemoteException;

    boolean reqHfpDisconnect(String str) throws RemoteException;

    boolean reqHfpMemoryDial(String str) throws RemoteException;

    boolean reqHfpReDial() throws RemoteException;

    boolean reqHfpRejectIncomingCall() throws RemoteException;

    boolean reqHfpSendDtmf(String str) throws RemoteException;

    boolean reqHfpTerminateCurrentCall() throws RemoteException;

    boolean reqHfpVoiceDial(boolean z) throws RemoteException;

    void startHfpRender() throws RemoteException;

    boolean unregisterHfpCallback(IHfpCallback iHfpCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IHfp {
        private static final String DESCRIPTOR = "com.ecarx.xui.adaptapi.bt1.hfp.IHfp";
        static final int TRANSACTION_getHfpAudioConnectionState = 7;
        static final int TRANSACTION_getHfpCallList = 11;
        static final int TRANSACTION_getHfpConnectedAddress = 6;
        static final int TRANSACTION_getHfpConnectionState = 4;
        static final int TRANSACTION_getHfpRemoteBatteryIndicator = 13;
        static final int TRANSACTION_getHfpRemoteNetworkOperator = 25;
        static final int TRANSACTION_getHfpRemoteSignalStrength = 10;
        static final int TRANSACTION_getHfpRemoteSubscriberNumber = 26;
        static final int TRANSACTION_isHfpConnected = 5;
        static final int TRANSACTION_isHfpInBandRingtoneSupport = 32;
        static final int TRANSACTION_isHfpMicMute = 30;
        static final int TRANSACTION_isHfpRemoteOnRoaming = 12;
        static final int TRANSACTION_isHfpRemoteTelecomServiceOn = 14;
        static final int TRANSACTION_isHfpRemoteVoiceDialOn = 15;
        static final int TRANSACTION_isHfpServiceReady = 1;
        static final int TRANSACTION_muteHfpMic = 31;
        static final int TRANSACTION_pauseHfpRender = 28;
        static final int TRANSACTION_registerHfpCallback = 2;
        static final int TRANSACTION_reqHfpAnswerCall = 19;
        static final int TRANSACTION_reqHfpAudioTransferToCarkit = 23;
        static final int TRANSACTION_reqHfpAudioTransferToPhone = 24;
        static final int TRANSACTION_reqHfpConnect = 8;
        static final int TRANSACTION_reqHfpDialCall = 16;
        static final int TRANSACTION_reqHfpDisconnect = 9;
        static final int TRANSACTION_reqHfpMemoryDial = 18;
        static final int TRANSACTION_reqHfpReDial = 17;
        static final int TRANSACTION_reqHfpRejectIncomingCall = 20;
        static final int TRANSACTION_reqHfpSendDtmf = 22;
        static final int TRANSACTION_reqHfpTerminateCurrentCall = 21;
        static final int TRANSACTION_reqHfpVoiceDial = 27;
        static final int TRANSACTION_startHfpRender = 29;
        static final int TRANSACTION_unregisterHfpCallback = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IHfp asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IHfp)) {
                return (IHfp) iInterfaceQueryLocalInterface;
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
                    boolean zIsHfpServiceReady = isHfpServiceReady();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsHfpServiceReady ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRegisterHfpCallback = registerHfpCallback(IHfpCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterHfpCallback ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUnregisterHfpCallback = unregisterHfpCallback(IHfpCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zUnregisterHfpCallback ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int hfpConnectionState = getHfpConnectionState();
                    parcel2.writeNoException();
                    parcel2.writeInt(hfpConnectionState);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsHfpConnected = isHfpConnected();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsHfpConnected ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String hfpConnectedAddress = getHfpConnectedAddress();
                    parcel2.writeNoException();
                    parcel2.writeString(hfpConnectedAddress);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int hfpAudioConnectionState = getHfpAudioConnectionState();
                    parcel2.writeNoException();
                    parcel2.writeInt(hfpAudioConnectionState);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqHfpConnect = reqHfpConnect(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqHfpConnect ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqHfpDisconnect = reqHfpDisconnect(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqHfpDisconnect ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int hfpRemoteSignalStrength = getHfpRemoteSignalStrength();
                    parcel2.writeNoException();
                    parcel2.writeInt(hfpRemoteSignalStrength);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<HfpClientCall> hfpCallList = getHfpCallList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(hfpCallList);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsHfpRemoteOnRoaming = isHfpRemoteOnRoaming();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsHfpRemoteOnRoaming ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    int hfpRemoteBatteryIndicator = getHfpRemoteBatteryIndicator();
                    parcel2.writeNoException();
                    parcel2.writeInt(hfpRemoteBatteryIndicator);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsHfpRemoteTelecomServiceOn = isHfpRemoteTelecomServiceOn();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsHfpRemoteTelecomServiceOn ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsHfpRemoteVoiceDialOn = isHfpRemoteVoiceDialOn();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsHfpRemoteVoiceDialOn ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqHfpDialCall = reqHfpDialCall(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqHfpDialCall ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqHfpReDial = reqHfpReDial();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqHfpReDial ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqHfpMemoryDial = reqHfpMemoryDial(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqHfpMemoryDial ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqHfpAnswerCall = reqHfpAnswerCall(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqHfpAnswerCall ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqHfpRejectIncomingCall = reqHfpRejectIncomingCall();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqHfpRejectIncomingCall ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqHfpTerminateCurrentCall = reqHfpTerminateCurrentCall();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqHfpTerminateCurrentCall ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqHfpSendDtmf = reqHfpSendDtmf(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqHfpSendDtmf ? 1 : 0);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqHfpAudioTransferToCarkit = reqHfpAudioTransferToCarkit();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqHfpAudioTransferToCarkit ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqHfpAudioTransferToPhone = reqHfpAudioTransferToPhone();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqHfpAudioTransferToPhone ? 1 : 0);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    String hfpRemoteNetworkOperator = getHfpRemoteNetworkOperator();
                    parcel2.writeNoException();
                    parcel2.writeString(hfpRemoteNetworkOperator);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    String hfpRemoteSubscriberNumber = getHfpRemoteSubscriberNumber();
                    parcel2.writeNoException();
                    parcel2.writeString(hfpRemoteSubscriberNumber);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqHfpVoiceDial = reqHfpVoiceDial(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqHfpVoiceDial ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    pauseHfpRender();
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    startHfpRender();
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsHfpMicMute = isHfpMicMute();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsHfpMicMute ? 1 : 0);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    muteHfpMic(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsHfpInBandRingtoneSupport = isHfpInBandRingtoneSupport();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsHfpInBandRingtoneSupport ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IHfp {
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

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean isHfpServiceReady() throws RemoteException {
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

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean registerHfpCallback(IHfpCallback iHfpCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iHfpCallback != null ? iHfpCallback.asBinder() : null);
                    this.mRemote.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean unregisterHfpCallback(IHfpCallback iHfpCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iHfpCallback != null ? iHfpCallback.asBinder() : null);
                    this.mRemote.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public int getHfpConnectionState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean isHfpConnected() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public String getHfpConnectedAddress() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public int getHfpAudioConnectionState() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean reqHfpConnect(String str) throws RemoteException {
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

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean reqHfpDisconnect(String str) throws RemoteException {
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

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public int getHfpRemoteSignalStrength() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public List<HfpClientCall> getHfpCallList() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(HfpClientCall.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean isHfpRemoteOnRoaming() throws RemoteException {
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

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public int getHfpRemoteBatteryIndicator() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean isHfpRemoteTelecomServiceOn() throws RemoteException {
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

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean isHfpRemoteVoiceDialOn() throws RemoteException {
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

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean reqHfpDialCall(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(16, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean reqHfpReDial() throws RemoteException {
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

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean reqHfpMemoryDial(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(18, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean reqHfpAnswerCall(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(19, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean reqHfpRejectIncomingCall() throws RemoteException {
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

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean reqHfpTerminateCurrentCall() throws RemoteException {
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

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean reqHfpSendDtmf(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    this.mRemote.transact(22, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean reqHfpAudioTransferToCarkit() throws RemoteException {
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

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean reqHfpAudioTransferToPhone() throws RemoteException {
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

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public String getHfpRemoteNetworkOperator() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(25, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public String getHfpRemoteSubscriberNumber() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(26, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean reqHfpVoiceDial(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(27, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public void pauseHfpRender() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(28, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public void startHfpRender() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(29, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean isHfpMicMute() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(30, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public void muteHfpMic(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(31, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ecarx.xui.adaptapi.bt1.hfp.IHfp
            public boolean isHfpInBandRingtoneSupport() throws RemoteException {
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
        }
    }
}
