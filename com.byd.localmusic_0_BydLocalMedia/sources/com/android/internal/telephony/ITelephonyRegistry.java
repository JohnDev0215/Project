package com.android.internal.telephony;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.BarringInfo;
import android.telephony.CallQuality;
import android.telephony.CellIdentity;
import android.telephony.CellInfo;
import android.telephony.PhoneCapability;
import android.telephony.PreciseDataConnectionState;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyDisplayInfo;
import android.telephony.emergency.EmergencyNumber;
import android.telephony.ims.ImsReasonInfo;
import com.android.internal.telephony.IOnSubscriptionsChangedListener;
import com.android.internal.telephony.IPhoneStateListener;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface ITelephonyRegistry extends IInterface {

    public static class Default implements ITelephonyRegistry {
        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void addOnOpportunisticSubscriptionsChangedListener(String str, String str2, IOnSubscriptionsChangedListener iOnSubscriptionsChangedListener) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void addOnSubscriptionsChangedListener(String str, String str2, IOnSubscriptionsChangedListener iOnSubscriptionsChangedListener) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void listen(String str, IPhoneStateListener iPhoneStateListener, int i, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void listenForSubscriber(int i, String str, String str2, IPhoneStateListener iPhoneStateListener, int i2, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void listenWithFeature(String str, String str2, IPhoneStateListener iPhoneStateListener, int i, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyActiveDataSubIdChanged(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyBarringInfoChanged(int i, int i2, BarringInfo barringInfo) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyCallForwardingChanged(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyCallForwardingChangedForSubscriber(int i, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyCallQualityChanged(CallQuality callQuality, int i, int i2, int i3) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyCallState(int i, int i2, int i3, String str) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyCallStateForAllSubs(int i, String str) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyCarrierNetworkChange(boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyCellInfo(List<CellInfo> list) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyCellInfoForSubscriber(int i, List<CellInfo> list) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyCellLocation(CellIdentity cellIdentity) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyCellLocationForSubscriber(int i, CellIdentity cellIdentity) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyDataActivity(int i) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyDataActivityForSubscriber(int i, int i2) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyDataConnectionFailed(String str) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyDataConnectionForSubscriber(int i, int i2, int i3, PreciseDataConnectionState preciseDataConnectionState) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyDisconnectCause(int i, int i2, int i3, int i4) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyDisplayInfoChanged(int i, int i2, TelephonyDisplayInfo telephonyDisplayInfo) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyEmergencyNumberList(int i, int i2) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyImsDisconnectCause(int i, ImsReasonInfo imsReasonInfo) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyMessageWaitingChangedForPhoneId(int i, int i2, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyOemHookRawEventForSubscriber(int i, int i2, byte[] bArr) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyOpportunisticSubscriptionInfoChanged() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyOutgoingEmergencyCall(int i, int i2, EmergencyNumber emergencyNumber) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyOutgoingEmergencySms(int i, int i2, EmergencyNumber emergencyNumber) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyPhoneCapabilityChanged(PhoneCapability phoneCapability) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyPreciseCallState(int i, int i2, int i3, int i4, int i5) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyPreciseDataConnectionFailed(int i, int i2, int i3, String str, int i4) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyRadioPowerStateChanged(int i, int i2, int i3) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyRegistrationFailed(int i, int i2, CellIdentity cellIdentity, String str, int i3, int i4, int i5) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyServiceStateForPhoneId(int i, int i2, ServiceState serviceState) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifySignalStrengthForPhoneId(int i, int i2, SignalStrength signalStrength) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifySimActivationStateChangedForPhoneId(int i, int i2, int i3, int i4) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifySrvccStateChanged(int i, int i2) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifySubscriptionInfoChanged() throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void notifyUserMobileDataStateChangedForPhoneId(int i, int i2, boolean z) throws RemoteException {
        }

        @Override // com.android.internal.telephony.ITelephonyRegistry
        public void removeOnSubscriptionsChangedListener(String str, IOnSubscriptionsChangedListener iOnSubscriptionsChangedListener) throws RemoteException {
        }
    }

    void addOnOpportunisticSubscriptionsChangedListener(String str, String str2, IOnSubscriptionsChangedListener iOnSubscriptionsChangedListener) throws RemoteException;

    void addOnSubscriptionsChangedListener(String str, String str2, IOnSubscriptionsChangedListener iOnSubscriptionsChangedListener) throws RemoteException;

    void listen(String str, IPhoneStateListener iPhoneStateListener, int i, boolean z) throws RemoteException;

    void listenForSubscriber(int i, String str, String str2, IPhoneStateListener iPhoneStateListener, int i2, boolean z) throws RemoteException;

    void listenWithFeature(String str, String str2, IPhoneStateListener iPhoneStateListener, int i, boolean z) throws RemoteException;

    void notifyActiveDataSubIdChanged(int i) throws RemoteException;

    void notifyBarringInfoChanged(int i, int i2, BarringInfo barringInfo) throws RemoteException;

    void notifyCallForwardingChanged(boolean z) throws RemoteException;

    void notifyCallForwardingChangedForSubscriber(int i, boolean z) throws RemoteException;

    void notifyCallQualityChanged(CallQuality callQuality, int i, int i2, int i3) throws RemoteException;

    void notifyCallState(int i, int i2, int i3, String str) throws RemoteException;

    void notifyCallStateForAllSubs(int i, String str) throws RemoteException;

    void notifyCarrierNetworkChange(boolean z) throws RemoteException;

    void notifyCellInfo(List<CellInfo> list) throws RemoteException;

    void notifyCellInfoForSubscriber(int i, List<CellInfo> list) throws RemoteException;

    void notifyCellLocation(CellIdentity cellIdentity) throws RemoteException;

    void notifyCellLocationForSubscriber(int i, CellIdentity cellIdentity) throws RemoteException;

    void notifyDataActivity(int i) throws RemoteException;

    void notifyDataActivityForSubscriber(int i, int i2) throws RemoteException;

    void notifyDataConnectionFailed(String str) throws RemoteException;

    void notifyDataConnectionForSubscriber(int i, int i2, int i3, PreciseDataConnectionState preciseDataConnectionState) throws RemoteException;

    void notifyDisconnectCause(int i, int i2, int i3, int i4) throws RemoteException;

    void notifyDisplayInfoChanged(int i, int i2, TelephonyDisplayInfo telephonyDisplayInfo) throws RemoteException;

    void notifyEmergencyNumberList(int i, int i2) throws RemoteException;

    void notifyImsDisconnectCause(int i, ImsReasonInfo imsReasonInfo) throws RemoteException;

    void notifyMessageWaitingChangedForPhoneId(int i, int i2, boolean z) throws RemoteException;

    void notifyOemHookRawEventForSubscriber(int i, int i2, byte[] bArr) throws RemoteException;

    void notifyOpportunisticSubscriptionInfoChanged() throws RemoteException;

    void notifyOutgoingEmergencyCall(int i, int i2, EmergencyNumber emergencyNumber) throws RemoteException;

    void notifyOutgoingEmergencySms(int i, int i2, EmergencyNumber emergencyNumber) throws RemoteException;

    void notifyPhoneCapabilityChanged(PhoneCapability phoneCapability) throws RemoteException;

    void notifyPreciseCallState(int i, int i2, int i3, int i4, int i5) throws RemoteException;

    void notifyPreciseDataConnectionFailed(int i, int i2, int i3, String str, int i4) throws RemoteException;

    void notifyRadioPowerStateChanged(int i, int i2, int i3) throws RemoteException;

    void notifyRegistrationFailed(int i, int i2, CellIdentity cellIdentity, String str, int i3, int i4, int i5) throws RemoteException;

    void notifyServiceStateForPhoneId(int i, int i2, ServiceState serviceState) throws RemoteException;

    void notifySignalStrengthForPhoneId(int i, int i2, SignalStrength signalStrength) throws RemoteException;

    void notifySimActivationStateChangedForPhoneId(int i, int i2, int i3, int i4) throws RemoteException;

    void notifySrvccStateChanged(int i, int i2) throws RemoteException;

    void notifySubscriptionInfoChanged() throws RemoteException;

    void notifyUserMobileDataStateChangedForPhoneId(int i, int i2, boolean z) throws RemoteException;

    void removeOnSubscriptionsChangedListener(String str, IOnSubscriptionsChangedListener iOnSubscriptionsChangedListener) throws RemoteException;

    public static abstract class Stub extends Binder implements ITelephonyRegistry {
        private static final String DESCRIPTOR = "com.android.internal.telephony.ITelephonyRegistry";
        static final int TRANSACTION_addOnOpportunisticSubscriptionsChangedListener = 2;
        static final int TRANSACTION_addOnSubscriptionsChangedListener = 1;
        static final int TRANSACTION_listen = 4;
        static final int TRANSACTION_listenForSubscriber = 6;
        static final int TRANSACTION_listenWithFeature = 5;
        static final int TRANSACTION_notifyActiveDataSubIdChanged = 34;
        static final int TRANSACTION_notifyBarringInfoChanged = 42;
        static final int TRANSACTION_notifyCallForwardingChanged = 12;
        static final int TRANSACTION_notifyCallForwardingChangedForSubscriber = 13;
        static final int TRANSACTION_notifyCallQualityChanged = 39;
        static final int TRANSACTION_notifyCallState = 8;
        static final int TRANSACTION_notifyCallStateForAllSubs = 7;
        static final int TRANSACTION_notifyCarrierNetworkChange = 30;
        static final int TRANSACTION_notifyCellInfo = 20;
        static final int TRANSACTION_notifyCellInfoForSubscriber = 24;
        static final int TRANSACTION_notifyCellLocation = 18;
        static final int TRANSACTION_notifyCellLocationForSubscriber = 19;
        static final int TRANSACTION_notifyDataActivity = 14;
        static final int TRANSACTION_notifyDataActivityForSubscriber = 15;
        static final int TRANSACTION_notifyDataConnectionFailed = 17;
        static final int TRANSACTION_notifyDataConnectionForSubscriber = 16;
        static final int TRANSACTION_notifyDisconnectCause = 22;
        static final int TRANSACTION_notifyDisplayInfoChanged = 32;
        static final int TRANSACTION_notifyEmergencyNumberList = 36;
        static final int TRANSACTION_notifyImsDisconnectCause = 40;
        static final int TRANSACTION_notifyMessageWaitingChangedForPhoneId = 11;
        static final int TRANSACTION_notifyOemHookRawEventForSubscriber = 27;
        static final int TRANSACTION_notifyOpportunisticSubscriptionInfoChanged = 29;
        static final int TRANSACTION_notifyOutgoingEmergencyCall = 37;
        static final int TRANSACTION_notifyOutgoingEmergencySms = 38;
        static final int TRANSACTION_notifyPhoneCapabilityChanged = 33;
        static final int TRANSACTION_notifyPreciseCallState = 21;
        static final int TRANSACTION_notifyPreciseDataConnectionFailed = 23;
        static final int TRANSACTION_notifyRadioPowerStateChanged = 35;
        static final int TRANSACTION_notifyRegistrationFailed = 41;
        static final int TRANSACTION_notifyServiceStateForPhoneId = 9;
        static final int TRANSACTION_notifySignalStrengthForPhoneId = 10;
        static final int TRANSACTION_notifySimActivationStateChangedForPhoneId = 26;
        static final int TRANSACTION_notifySrvccStateChanged = 25;
        static final int TRANSACTION_notifySubscriptionInfoChanged = 28;
        static final int TRANSACTION_notifyUserMobileDataStateChangedForPhoneId = 31;
        static final int TRANSACTION_removeOnSubscriptionsChangedListener = 3;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "addOnSubscriptionsChangedListener";
                case 2:
                    return "addOnOpportunisticSubscriptionsChangedListener";
                case 3:
                    return "removeOnSubscriptionsChangedListener";
                case 4:
                    return "listen";
                case 5:
                    return "listenWithFeature";
                case 6:
                    return "listenForSubscriber";
                case 7:
                    return "notifyCallStateForAllSubs";
                case 8:
                    return "notifyCallState";
                case 9:
                    return "notifyServiceStateForPhoneId";
                case 10:
                    return "notifySignalStrengthForPhoneId";
                case 11:
                    return "notifyMessageWaitingChangedForPhoneId";
                case 12:
                    return "notifyCallForwardingChanged";
                case 13:
                    return "notifyCallForwardingChangedForSubscriber";
                case 14:
                    return "notifyDataActivity";
                case 15:
                    return "notifyDataActivityForSubscriber";
                case 16:
                    return "notifyDataConnectionForSubscriber";
                case 17:
                    return "notifyDataConnectionFailed";
                case 18:
                    return "notifyCellLocation";
                case 19:
                    return "notifyCellLocationForSubscriber";
                case 20:
                    return "notifyCellInfo";
                case 21:
                    return "notifyPreciseCallState";
                case 22:
                    return "notifyDisconnectCause";
                case 23:
                    return "notifyPreciseDataConnectionFailed";
                case 24:
                    return "notifyCellInfoForSubscriber";
                case 25:
                    return "notifySrvccStateChanged";
                case 26:
                    return "notifySimActivationStateChangedForPhoneId";
                case 27:
                    return "notifyOemHookRawEventForSubscriber";
                case 28:
                    return "notifySubscriptionInfoChanged";
                case 29:
                    return "notifyOpportunisticSubscriptionInfoChanged";
                case 30:
                    return "notifyCarrierNetworkChange";
                case 31:
                    return "notifyUserMobileDataStateChangedForPhoneId";
                case 32:
                    return "notifyDisplayInfoChanged";
                case 33:
                    return "notifyPhoneCapabilityChanged";
                case 34:
                    return "notifyActiveDataSubIdChanged";
                case 35:
                    return "notifyRadioPowerStateChanged";
                case 36:
                    return "notifyEmergencyNumberList";
                case 37:
                    return "notifyOutgoingEmergencyCall";
                case 38:
                    return "notifyOutgoingEmergencySms";
                case 39:
                    return "notifyCallQualityChanged";
                case 40:
                    return "notifyImsDisconnectCause";
                case 41:
                    return "notifyRegistrationFailed";
                case 42:
                    return "notifyBarringInfoChanged";
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

        public static ITelephonyRegistry asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITelephonyRegistry)) {
                return (ITelephonyRegistry) iInterfaceQueryLocalInterface;
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
                    addOnSubscriptionsChangedListener(parcel.readString(), parcel.readString(), IOnSubscriptionsChangedListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    addOnOpportunisticSubscriptionsChangedListener(parcel.readString(), parcel.readString(), IOnSubscriptionsChangedListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeOnSubscriptionsChangedListener(parcel.readString(), IOnSubscriptionsChangedListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    listen(parcel.readString(), IPhoneStateListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    listenWithFeature(parcel.readString(), parcel.readString(), IPhoneStateListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    listenForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString(), IPhoneStateListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyCallStateForAllSubs(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyCallState(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyServiceStateForPhoneId(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? ServiceState.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifySignalStrengthForPhoneId(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? SignalStrength.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyMessageWaitingChangedForPhoneId(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyCallForwardingChanged(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyCallForwardingChangedForSubscriber(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyDataActivity(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyDataActivityForSubscriber(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyDataConnectionForSubscriber(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? PreciseDataConnectionState.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyDataConnectionFailed(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyCellLocation(parcel.readInt() != 0 ? CellIdentity.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyCellLocationForSubscriber(parcel.readInt(), parcel.readInt() != 0 ? CellIdentity.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyCellInfo(parcel.createTypedArrayList(CellInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyPreciseCallState(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyDisconnectCause(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyPreciseDataConnectionFailed(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyCellInfoForSubscriber(parcel.readInt(), parcel.createTypedArrayList(CellInfo.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifySrvccStateChanged(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifySimActivationStateChangedForPhoneId(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyOemHookRawEventForSubscriber(parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifySubscriptionInfoChanged();
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyOpportunisticSubscriptionInfoChanged();
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyCarrierNetworkChange(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyUserMobileDataStateChangedForPhoneId(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyDisplayInfoChanged(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? TelephonyDisplayInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyPhoneCapabilityChanged(parcel.readInt() != 0 ? PhoneCapability.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyActiveDataSubIdChanged(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyRadioPowerStateChanged(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyEmergencyNumberList(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyOutgoingEmergencyCall(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? EmergencyNumber.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyOutgoingEmergencySms(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? EmergencyNumber.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyCallQualityChanged(parcel.readInt() != 0 ? CallQuality.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyImsDisconnectCause(parcel.readInt(), parcel.readInt() != 0 ? ImsReasonInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyRegistrationFailed(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? CellIdentity.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyBarringInfoChanged(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? BarringInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ITelephonyRegistry {
            public static ITelephonyRegistry sDefaultImpl;
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

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void addOnSubscriptionsChangedListener(String str, String str2, IOnSubscriptionsChangedListener iOnSubscriptionsChangedListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iOnSubscriptionsChangedListener != null ? iOnSubscriptionsChangedListener.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addOnSubscriptionsChangedListener(str, str2, iOnSubscriptionsChangedListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void addOnOpportunisticSubscriptionsChangedListener(String str, String str2, IOnSubscriptionsChangedListener iOnSubscriptionsChangedListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iOnSubscriptionsChangedListener != null ? iOnSubscriptionsChangedListener.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addOnOpportunisticSubscriptionsChangedListener(str, str2, iOnSubscriptionsChangedListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void removeOnSubscriptionsChangedListener(String str, IOnSubscriptionsChangedListener iOnSubscriptionsChangedListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iOnSubscriptionsChangedListener != null ? iOnSubscriptionsChangedListener.asBinder() : null);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeOnSubscriptionsChangedListener(str, iOnSubscriptionsChangedListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void listen(String str, IPhoneStateListener iPhoneStateListener, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iPhoneStateListener != null ? iPhoneStateListener.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().listen(str, iPhoneStateListener, i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void listenWithFeature(String str, String str2, IPhoneStateListener iPhoneStateListener, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iPhoneStateListener != null ? iPhoneStateListener.asBinder() : null);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().listenWithFeature(str, str2, iPhoneStateListener, i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void listenForSubscriber(int i, String str, String str2, IPhoneStateListener iPhoneStateListener, int i2, boolean z) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeStrongBinder(iPhoneStateListener != null ? iPhoneStateListener.asBinder() : null);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(z ? 1 : 0);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().listenForSubscriber(i, str, str2, iPhoneStateListener, i2, z);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
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

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyCallStateForAllSubs(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyCallStateForAllSubs(i, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyCallState(int i, int i2, int i3, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyCallState(i, i2, i3, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyServiceStateForPhoneId(int i, int i2, ServiceState serviceState) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (serviceState != null) {
                        parcelObtain.writeInt(1);
                        serviceState.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyServiceStateForPhoneId(i, i2, serviceState);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifySignalStrengthForPhoneId(int i, int i2, SignalStrength signalStrength) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (signalStrength != null) {
                        parcelObtain.writeInt(1);
                        signalStrength.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifySignalStrengthForPhoneId(i, i2, signalStrength);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyMessageWaitingChangedForPhoneId(int i, int i2, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyMessageWaitingChangedForPhoneId(i, i2, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyCallForwardingChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyCallForwardingChanged(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyCallForwardingChangedForSubscriber(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyCallForwardingChangedForSubscriber(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyDataActivity(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyDataActivity(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyDataActivityForSubscriber(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyDataActivityForSubscriber(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyDataConnectionForSubscriber(int i, int i2, int i3, PreciseDataConnectionState preciseDataConnectionState) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (preciseDataConnectionState != null) {
                        parcelObtain.writeInt(1);
                        preciseDataConnectionState.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyDataConnectionForSubscriber(i, i2, i3, preciseDataConnectionState);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyDataConnectionFailed(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyDataConnectionFailed(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyCellLocation(CellIdentity cellIdentity) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cellIdentity != null) {
                        parcelObtain.writeInt(1);
                        cellIdentity.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyCellLocation(cellIdentity);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyCellLocationForSubscriber(int i, CellIdentity cellIdentity) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (cellIdentity != null) {
                        parcelObtain.writeInt(1);
                        cellIdentity.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyCellLocationForSubscriber(i, cellIdentity);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyCellInfo(List<CellInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyCellInfo(list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyPreciseCallState(int i, int i2, int i3, int i4, int i5) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i5);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyPreciseCallState(i, i2, i3, i4, i5);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyDisconnectCause(int i, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyDisconnectCause(i, i2, i3, i4);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyPreciseDataConnectionFailed(int i, int i2, int i3, String str, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i4);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyPreciseDataConnectionFailed(i, i2, i3, str, i4);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyCellInfoForSubscriber(int i, List<CellInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeTypedList(list);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyCellInfoForSubscriber(i, list);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifySrvccStateChanged(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifySrvccStateChanged(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifySimActivationStateChangedForPhoneId(int i, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifySimActivationStateChangedForPhoneId(i, i2, i3, i4);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyOemHookRawEventForSubscriber(int i, int i2, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyOemHookRawEventForSubscriber(i, i2, bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifySubscriptionInfoChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifySubscriptionInfoChanged();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyOpportunisticSubscriptionInfoChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyOpportunisticSubscriptionInfoChanged();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyCarrierNetworkChange(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyCarrierNetworkChange(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyUserMobileDataStateChangedForPhoneId(int i, int i2, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyUserMobileDataStateChangedForPhoneId(i, i2, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyDisplayInfoChanged(int i, int i2, TelephonyDisplayInfo telephonyDisplayInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (telephonyDisplayInfo != null) {
                        parcelObtain.writeInt(1);
                        telephonyDisplayInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyDisplayInfoChanged(i, i2, telephonyDisplayInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyPhoneCapabilityChanged(PhoneCapability phoneCapability) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (phoneCapability != null) {
                        parcelObtain.writeInt(1);
                        phoneCapability.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyPhoneCapabilityChanged(phoneCapability);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyActiveDataSubIdChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyActiveDataSubIdChanged(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyRadioPowerStateChanged(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyRadioPowerStateChanged(i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyEmergencyNumberList(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyEmergencyNumberList(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyOutgoingEmergencyCall(int i, int i2, EmergencyNumber emergencyNumber) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (emergencyNumber != null) {
                        parcelObtain.writeInt(1);
                        emergencyNumber.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyOutgoingEmergencyCall(i, i2, emergencyNumber);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyOutgoingEmergencySms(int i, int i2, EmergencyNumber emergencyNumber) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (emergencyNumber != null) {
                        parcelObtain.writeInt(1);
                        emergencyNumber.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyOutgoingEmergencySms(i, i2, emergencyNumber);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyCallQualityChanged(CallQuality callQuality, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (callQuality != null) {
                        parcelObtain.writeInt(1);
                        callQuality.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyCallQualityChanged(callQuality, i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyImsDisconnectCause(int i, ImsReasonInfo imsReasonInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (imsReasonInfo != null) {
                        parcelObtain.writeInt(1);
                        imsReasonInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyImsDisconnectCause(i, imsReasonInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyRegistrationFailed(int i, int i2, CellIdentity cellIdentity, String str, int i3, int i4, int i5) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (cellIdentity != null) {
                        parcelObtain.writeInt(1);
                        cellIdentity.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(i5);
                    try {
                        if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().notifyRegistrationFailed(i, i2, cellIdentity, str, i3, i4, i5);
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

            @Override // com.android.internal.telephony.ITelephonyRegistry
            public void notifyBarringInfoChanged(int i, int i2, BarringInfo barringInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (barringInfo != null) {
                        parcelObtain.writeInt(1);
                        barringInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyBarringInfoChanged(i, i2, barringInfo);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITelephonyRegistry iTelephonyRegistry) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTelephonyRegistry == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTelephonyRegistry;
            return true;
        }

        public static ITelephonyRegistry getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
