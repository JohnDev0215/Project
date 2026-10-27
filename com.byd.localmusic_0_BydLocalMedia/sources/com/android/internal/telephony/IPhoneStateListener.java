package com.android.internal.telephony;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.BarringInfo;
import android.telephony.CallAttributes;
import android.telephony.CellIdentity;
import android.telephony.CellInfo;
import android.telephony.DataConnectionRealTimeInfo;
import android.telephony.PhoneCapability;
import android.telephony.PreciseCallState;
import android.telephony.PreciseDataConnectionState;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyDisplayInfo;
import android.telephony.emergency.EmergencyNumber;
import android.telephony.ims.ImsReasonInfo;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public interface IPhoneStateListener extends IInterface {

    public static class Default implements IPhoneStateListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onActiveDataSubIdChanged(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onBarringInfoChanged(BarringInfo barringInfo) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onCallAttributesChanged(CallAttributes callAttributes) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onCallDisconnectCauseChanged(int i, int i2) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onCallForwardingIndicatorChanged(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onCallStateChanged(int i, String str) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onCarrierNetworkChange(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onCellInfoChanged(List<CellInfo> list) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onCellLocationChanged(CellIdentity cellIdentity) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onDataActivationStateChanged(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onDataActivity(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onDataConnectionRealTimeInfoChanged(DataConnectionRealTimeInfo dataConnectionRealTimeInfo) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onDataConnectionStateChanged(int i, int i2) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onDisplayInfoChanged(TelephonyDisplayInfo telephonyDisplayInfo) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onEmergencyNumberListChanged(Map map) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onImsCallDisconnectCauseChanged(ImsReasonInfo imsReasonInfo) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onMessageWaitingIndicatorChanged(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onOemHookRawEvent(byte[] bArr) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onOutgoingEmergencyCall(EmergencyNumber emergencyNumber) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onOutgoingEmergencySms(EmergencyNumber emergencyNumber) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onPhoneCapabilityChanged(PhoneCapability phoneCapability) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onPreciseCallStateChanged(PreciseCallState preciseCallState) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onPreciseDataConnectionStateChanged(PreciseDataConnectionState preciseDataConnectionState) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onRadioPowerStateChanged(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onRegistrationFailed(CellIdentity cellIdentity, String str, int i, int i2, int i3) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onServiceStateChanged(ServiceState serviceState) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onSignalStrengthChanged(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onSrvccStateChanged(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onUserMobileDataStateChanged(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.IPhoneStateListener
        public void onVoiceActivationStateChanged(int i) throws RemoteException {
        }
    }

    void onActiveDataSubIdChanged(int i) throws RemoteException;

    void onBarringInfoChanged(BarringInfo barringInfo) throws RemoteException;

    void onCallAttributesChanged(CallAttributes callAttributes) throws RemoteException;

    void onCallDisconnectCauseChanged(int i, int i2) throws RemoteException;

    void onCallForwardingIndicatorChanged(boolean z) throws RemoteException;

    void onCallStateChanged(int i, String str) throws RemoteException;

    void onCarrierNetworkChange(boolean z) throws RemoteException;

    void onCellInfoChanged(List<CellInfo> list) throws RemoteException;

    void onCellLocationChanged(CellIdentity cellIdentity) throws RemoteException;

    void onDataActivationStateChanged(int i) throws RemoteException;

    void onDataActivity(int i) throws RemoteException;

    void onDataConnectionRealTimeInfoChanged(DataConnectionRealTimeInfo dataConnectionRealTimeInfo) throws RemoteException;

    void onDataConnectionStateChanged(int i, int i2) throws RemoteException;

    void onDisplayInfoChanged(TelephonyDisplayInfo telephonyDisplayInfo) throws RemoteException;

    void onEmergencyNumberListChanged(Map map) throws RemoteException;

    void onImsCallDisconnectCauseChanged(ImsReasonInfo imsReasonInfo) throws RemoteException;

    void onMessageWaitingIndicatorChanged(boolean z) throws RemoteException;

    void onOemHookRawEvent(byte[] bArr) throws RemoteException;

    void onOutgoingEmergencyCall(EmergencyNumber emergencyNumber) throws RemoteException;

    void onOutgoingEmergencySms(EmergencyNumber emergencyNumber) throws RemoteException;

    void onPhoneCapabilityChanged(PhoneCapability phoneCapability) throws RemoteException;

    void onPreciseCallStateChanged(PreciseCallState preciseCallState) throws RemoteException;

    void onPreciseDataConnectionStateChanged(PreciseDataConnectionState preciseDataConnectionState) throws RemoteException;

    void onRadioPowerStateChanged(int i) throws RemoteException;

    void onRegistrationFailed(CellIdentity cellIdentity, String str, int i, int i2, int i3) throws RemoteException;

    void onServiceStateChanged(ServiceState serviceState) throws RemoteException;

    void onSignalStrengthChanged(int i) throws RemoteException;

    void onSignalStrengthsChanged(SignalStrength signalStrength) throws RemoteException;

    void onSrvccStateChanged(int i) throws RemoteException;

    void onUserMobileDataStateChanged(boolean z) throws RemoteException;

    void onVoiceActivationStateChanged(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IPhoneStateListener {
        private static final String DESCRIPTOR = "com.android.internal.telephony.IPhoneStateListener";
        static final int TRANSACTION_onActiveDataSubIdChanged = 22;
        static final int TRANSACTION_onBarringInfoChanged = 31;
        static final int TRANSACTION_onCallAttributesChanged = 24;
        static final int TRANSACTION_onCallDisconnectCauseChanged = 28;
        static final int TRANSACTION_onCallForwardingIndicatorChanged = 4;
        static final int TRANSACTION_onCallStateChanged = 6;
        static final int TRANSACTION_onCarrierNetworkChange = 18;
        static final int TRANSACTION_onCellInfoChanged = 10;
        static final int TRANSACTION_onCellLocationChanged = 5;
        static final int TRANSACTION_onDataActivationStateChanged = 16;
        static final int TRANSACTION_onDataActivity = 8;
        static final int TRANSACTION_onDataConnectionRealTimeInfoChanged = 13;
        static final int TRANSACTION_onDataConnectionStateChanged = 7;
        static final int TRANSACTION_onDisplayInfoChanged = 20;
        static final int TRANSACTION_onEmergencyNumberListChanged = 25;
        static final int TRANSACTION_onImsCallDisconnectCauseChanged = 29;
        static final int TRANSACTION_onMessageWaitingIndicatorChanged = 3;
        static final int TRANSACTION_onOemHookRawEvent = 17;
        static final int TRANSACTION_onOutgoingEmergencyCall = 26;
        static final int TRANSACTION_onOutgoingEmergencySms = 27;
        static final int TRANSACTION_onPhoneCapabilityChanged = 21;
        static final int TRANSACTION_onPreciseCallStateChanged = 11;
        static final int TRANSACTION_onPreciseDataConnectionStateChanged = 12;
        static final int TRANSACTION_onRadioPowerStateChanged = 23;
        static final int TRANSACTION_onRegistrationFailed = 30;
        static final int TRANSACTION_onServiceStateChanged = 1;
        static final int TRANSACTION_onSignalStrengthChanged = 2;
        static final int TRANSACTION_onSignalStrengthsChanged = 9;
        static final int TRANSACTION_onSrvccStateChanged = 14;
        static final int TRANSACTION_onUserMobileDataStateChanged = 19;
        static final int TRANSACTION_onVoiceActivationStateChanged = 15;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onServiceStateChanged";
                case 2:
                    return "onSignalStrengthChanged";
                case 3:
                    return "onMessageWaitingIndicatorChanged";
                case 4:
                    return "onCallForwardingIndicatorChanged";
                case 5:
                    return "onCellLocationChanged";
                case 6:
                    return "onCallStateChanged";
                case 7:
                    return "onDataConnectionStateChanged";
                case 8:
                    return "onDataActivity";
                case 9:
                    return "onSignalStrengthsChanged";
                case 10:
                    return "onCellInfoChanged";
                case 11:
                    return "onPreciseCallStateChanged";
                case 12:
                    return "onPreciseDataConnectionStateChanged";
                case 13:
                    return "onDataConnectionRealTimeInfoChanged";
                case 14:
                    return "onSrvccStateChanged";
                case 15:
                    return "onVoiceActivationStateChanged";
                case 16:
                    return "onDataActivationStateChanged";
                case 17:
                    return "onOemHookRawEvent";
                case 18:
                    return "onCarrierNetworkChange";
                case 19:
                    return "onUserMobileDataStateChanged";
                case 20:
                    return "onDisplayInfoChanged";
                case 21:
                    return "onPhoneCapabilityChanged";
                case 22:
                    return "onActiveDataSubIdChanged";
                case 23:
                    return "onRadioPowerStateChanged";
                case 24:
                    return "onCallAttributesChanged";
                case 25:
                    return "onEmergencyNumberListChanged";
                case 26:
                    return "onOutgoingEmergencyCall";
                case 27:
                    return "onOutgoingEmergencySms";
                case 28:
                    return "onCallDisconnectCauseChanged";
                case 29:
                    return "onImsCallDisconnectCauseChanged";
                case 30:
                    return "onRegistrationFailed";
                case 31:
                    return "onBarringInfoChanged";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPhoneStateListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPhoneStateListener)) {
                return (IPhoneStateListener) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
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
                    onServiceStateChanged(parcel.readInt() != 0 ? ServiceState.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSignalStrengthChanged(parcel.readInt());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onMessageWaitingIndicatorChanged(parcel.readInt() != 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCallForwardingIndicatorChanged(parcel.readInt() != 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCellLocationChanged(parcel.readInt() != 0 ? CellIdentity.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCallStateChanged(parcel.readInt(), parcel.readString());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDataConnectionStateChanged(parcel.readInt(), parcel.readInt());
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDataActivity(parcel.readInt());
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSignalStrengthsChanged(parcel.readInt() != 0 ? SignalStrength.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCellInfoChanged(parcel.createTypedArrayList(CellInfo.CREATOR));
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPreciseCallStateChanged(parcel.readInt() != 0 ? PreciseCallState.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPreciseDataConnectionStateChanged(parcel.readInt() != 0 ? PreciseDataConnectionState.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDataConnectionRealTimeInfoChanged(parcel.readInt() != 0 ? DataConnectionRealTimeInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSrvccStateChanged(parcel.readInt());
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    onVoiceActivationStateChanged(parcel.readInt());
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDataActivationStateChanged(parcel.readInt());
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    onOemHookRawEvent(parcel.createByteArray());
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCarrierNetworkChange(parcel.readInt() != 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    onUserMobileDataStateChanged(parcel.readInt() != 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDisplayInfoChanged(parcel.readInt() != 0 ? TelephonyDisplayInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPhoneCapabilityChanged(parcel.readInt() != 0 ? PhoneCapability.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    onActiveDataSubIdChanged(parcel.readInt());
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRadioPowerStateChanged(parcel.readInt());
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCallAttributesChanged(parcel.readInt() != 0 ? CallAttributes.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    onEmergencyNumberListChanged(parcel.readHashMap(getClass().getClassLoader()));
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    onOutgoingEmergencyCall(parcel.readInt() != 0 ? EmergencyNumber.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    onOutgoingEmergencySms(parcel.readInt() != 0 ? EmergencyNumber.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCallDisconnectCauseChanged(parcel.readInt(), parcel.readInt());
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    onImsCallDisconnectCauseChanged(parcel.readInt() != 0 ? ImsReasonInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRegistrationFailed(parcel.readInt() != 0 ? CellIdentity.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    onBarringInfoChanged(parcel.readInt() != 0 ? BarringInfo.CREATOR.createFromParcel(parcel) : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IPhoneStateListener {
            public static IPhoneStateListener sDefaultImpl;
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

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onServiceStateChanged(ServiceState serviceState) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (serviceState != null) {
                        parcelObtain.writeInt(1);
                        serviceState.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onServiceStateChanged(serviceState);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onSignalStrengthChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSignalStrengthChanged(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onMessageWaitingIndicatorChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onMessageWaitingIndicatorChanged(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onCallForwardingIndicatorChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCallForwardingIndicatorChanged(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onCellLocationChanged(CellIdentity cellIdentity) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cellIdentity != null) {
                        parcelObtain.writeInt(1);
                        cellIdentity.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCellLocationChanged(cellIdentity);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onCallStateChanged(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCallStateChanged(i, str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onDataConnectionStateChanged(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDataConnectionStateChanged(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onDataActivity(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDataActivity(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onSignalStrengthsChanged(SignalStrength signalStrength) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (signalStrength != null) {
                        parcelObtain.writeInt(1);
                        signalStrength.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSignalStrengthsChanged(signalStrength);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onCellInfoChanged(List<CellInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCellInfoChanged(list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onPreciseCallStateChanged(PreciseCallState preciseCallState) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (preciseCallState != null) {
                        parcelObtain.writeInt(1);
                        preciseCallState.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPreciseCallStateChanged(preciseCallState);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onPreciseDataConnectionStateChanged(PreciseDataConnectionState preciseDataConnectionState) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (preciseDataConnectionState != null) {
                        parcelObtain.writeInt(1);
                        preciseDataConnectionState.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPreciseDataConnectionStateChanged(preciseDataConnectionState);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onDataConnectionRealTimeInfoChanged(DataConnectionRealTimeInfo dataConnectionRealTimeInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (dataConnectionRealTimeInfo != null) {
                        parcelObtain.writeInt(1);
                        dataConnectionRealTimeInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDataConnectionRealTimeInfoChanged(dataConnectionRealTimeInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onSrvccStateChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(14, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSrvccStateChanged(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onVoiceActivationStateChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(15, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onVoiceActivationStateChanged(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onDataActivationStateChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(16, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDataActivationStateChanged(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onOemHookRawEvent(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (this.mRemote.transact(17, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onOemHookRawEvent(bArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onCarrierNetworkChange(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(18, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCarrierNetworkChange(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onUserMobileDataStateChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(19, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUserMobileDataStateChanged(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onDisplayInfoChanged(TelephonyDisplayInfo telephonyDisplayInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (telephonyDisplayInfo != null) {
                        parcelObtain.writeInt(1);
                        telephonyDisplayInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(20, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDisplayInfoChanged(telephonyDisplayInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onPhoneCapabilityChanged(PhoneCapability phoneCapability) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneCapability != null) {
                        parcelObtain.writeInt(1);
                        phoneCapability.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(21, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPhoneCapabilityChanged(phoneCapability);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onActiveDataSubIdChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(22, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onActiveDataSubIdChanged(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onRadioPowerStateChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(23, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRadioPowerStateChanged(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onCallAttributesChanged(CallAttributes callAttributes) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (callAttributes != null) {
                        parcelObtain.writeInt(1);
                        callAttributes.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(24, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCallAttributesChanged(callAttributes);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onEmergencyNumberListChanged(Map map) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeMap(map);
                    if (this.mRemote.transact(25, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onEmergencyNumberListChanged(map);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onOutgoingEmergencyCall(EmergencyNumber emergencyNumber) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (emergencyNumber != null) {
                        parcelObtain.writeInt(1);
                        emergencyNumber.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(26, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onOutgoingEmergencyCall(emergencyNumber);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onOutgoingEmergencySms(EmergencyNumber emergencyNumber) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (emergencyNumber != null) {
                        parcelObtain.writeInt(1);
                        emergencyNumber.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(27, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onOutgoingEmergencySms(emergencyNumber);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onCallDisconnectCauseChanged(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(28, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCallDisconnectCauseChanged(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onImsCallDisconnectCauseChanged(ImsReasonInfo imsReasonInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (imsReasonInfo != null) {
                        parcelObtain.writeInt(1);
                        imsReasonInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(29, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onImsCallDisconnectCauseChanged(imsReasonInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onRegistrationFailed(CellIdentity cellIdentity, String str, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cellIdentity != null) {
                        parcelObtain.writeInt(1);
                        cellIdentity.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(30, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onRegistrationFailed(cellIdentity, str, i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.IPhoneStateListener
            public void onBarringInfoChanged(BarringInfo barringInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (barringInfo != null) {
                        parcelObtain.writeInt(1);
                        barringInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(31, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onBarringInfoChanged(barringInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPhoneStateListener iPhoneStateListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iPhoneStateListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iPhoneStateListener;
            return true;
        }

        public static IPhoneStateListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
