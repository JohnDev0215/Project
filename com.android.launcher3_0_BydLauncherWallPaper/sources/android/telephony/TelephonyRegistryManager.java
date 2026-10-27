package android.telephony;

import android.compat.Compatibility;
import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.telephony.SubscriptionManager;
import android.telephony.emergency.EmergencyNumber;
import android.telephony.ims.ImsReasonInfo;
import android.util.Log;
import com.android.internal.telephony.IOnSubscriptionsChangedListener;
import com.android.internal.telephony.ITelephonyRegistry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public class TelephonyRegistryManager {
    private static final long LISTEN_CODE_CHANGE = 147600208;
    public static final int SIM_ACTIVATION_TYPE_DATA = 1;
    public static final int SIM_ACTIVATION_TYPE_VOICE = 0;
    private static final String TAG = "TelephonyRegistryManager";
    private static ITelephonyRegistry sRegistry;
    private final Context mContext;
    private final Map<SubscriptionManager.OnSubscriptionsChangedListener, IOnSubscriptionsChangedListener> mSubscriptionChangedListenerMap = new HashMap();
    private final Map<SubscriptionManager.OnOpportunisticSubscriptionsChangedListener, IOnSubscriptionsChangedListener> mOpportunisticSubscriptionChangedListenerMap = new HashMap();

    public TelephonyRegistryManager(Context context) {
        this.mContext = context;
        if (sRegistry == null) {
            sRegistry = ITelephonyRegistry.Stub.asInterface(ServiceManager.getService("telephony.registry"));
        }
    }

    /* JADX INFO: renamed from: android.telephony.TelephonyRegistryManager$1, reason: invalid class name */
    class AnonymousClass1 extends IOnSubscriptionsChangedListener.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ SubscriptionManager.OnSubscriptionsChangedListener val$listener;

        AnonymousClass1(Executor executor, SubscriptionManager.OnSubscriptionsChangedListener onSubscriptionsChangedListener) {
            this.val$executor = executor;
            this.val$listener = onSubscriptionsChangedListener;
        }

        @Override // com.android.internal.telephony.IOnSubscriptionsChangedListener
        public void onSubscriptionsChanged() {
            Log.d(TelephonyRegistryManager.TAG, "onSubscriptionsChangedListener callback received.");
            Executor executor = this.val$executor;
            final SubscriptionManager.OnSubscriptionsChangedListener onSubscriptionsChangedListener = this.val$listener;
            executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$TelephonyRegistryManager$1$RkxDiu-MbbCQ42CqBL4-9CK3xyk
                @Override // java.lang.Runnable
                public final void run() {
                    onSubscriptionsChangedListener.onSubscriptionsChanged();
                }
            });
        }
    }

    public void addOnSubscriptionsChangedListener(SubscriptionManager.OnSubscriptionsChangedListener onSubscriptionsChangedListener, Executor executor) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(executor, onSubscriptionsChangedListener);
        this.mSubscriptionChangedListenerMap.put(onSubscriptionsChangedListener, anonymousClass1);
        try {
            sRegistry.addOnSubscriptionsChangedListener(this.mContext.getOpPackageName(), this.mContext.getAttributionTag(), anonymousClass1);
        } catch (RemoteException unused) {
        }
    }

    public void removeOnSubscriptionsChangedListener(SubscriptionManager.OnSubscriptionsChangedListener onSubscriptionsChangedListener) {
        if (this.mSubscriptionChangedListenerMap.get(onSubscriptionsChangedListener) == null) {
            return;
        }
        try {
            sRegistry.removeOnSubscriptionsChangedListener(this.mContext.getOpPackageName(), this.mSubscriptionChangedListenerMap.get(onSubscriptionsChangedListener));
            this.mSubscriptionChangedListenerMap.remove(onSubscriptionsChangedListener);
        } catch (RemoteException unused) {
        }
    }

    /* JADX INFO: renamed from: android.telephony.TelephonyRegistryManager$2, reason: invalid class name */
    class AnonymousClass2 extends IOnSubscriptionsChangedListener.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ SubscriptionManager.OnOpportunisticSubscriptionsChangedListener val$listener;

        AnonymousClass2(Executor executor, SubscriptionManager.OnOpportunisticSubscriptionsChangedListener onOpportunisticSubscriptionsChangedListener) {
            this.val$executor = executor;
            this.val$listener = onOpportunisticSubscriptionsChangedListener;
        }

        @Override // com.android.internal.telephony.IOnSubscriptionsChangedListener
        public void onSubscriptionsChanged() {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Log.d(TelephonyRegistryManager.TAG, "onOpportunisticSubscriptionsChanged callback received.");
                Executor executor = this.val$executor;
                final SubscriptionManager.OnOpportunisticSubscriptionsChangedListener onOpportunisticSubscriptionsChangedListener = this.val$listener;
                executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$TelephonyRegistryManager$2$SWHxVkNK2NMfm46nEnvuQ0J8kEM
                    @Override // java.lang.Runnable
                    public final void run() {
                        onOpportunisticSubscriptionsChangedListener.onOpportunisticSubscriptionsChanged();
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void addOnOpportunisticSubscriptionsChangedListener(SubscriptionManager.OnOpportunisticSubscriptionsChangedListener onOpportunisticSubscriptionsChangedListener, Executor executor) {
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(executor, onOpportunisticSubscriptionsChangedListener);
        this.mOpportunisticSubscriptionChangedListenerMap.put(onOpportunisticSubscriptionsChangedListener, anonymousClass2);
        try {
            sRegistry.addOnOpportunisticSubscriptionsChangedListener(this.mContext.getOpPackageName(), this.mContext.getAttributionTag(), anonymousClass2);
        } catch (RemoteException unused) {
        }
    }

    public void removeOnOpportunisticSubscriptionsChangedListener(SubscriptionManager.OnOpportunisticSubscriptionsChangedListener onOpportunisticSubscriptionsChangedListener) {
        try {
            sRegistry.removeOnSubscriptionsChangedListener(this.mContext.getOpPackageName(), this.mOpportunisticSubscriptionChangedListenerMap.get(onOpportunisticSubscriptionsChangedListener));
            this.mOpportunisticSubscriptionChangedListenerMap.remove(onOpportunisticSubscriptionsChangedListener);
        } catch (RemoteException unused) {
        }
    }

    public void listenForSubscriber(int i, String str, String str2, PhoneStateListener phoneStateListener, int i2, boolean z) {
        try {
            if (Compatibility.isChangeEnabled(LISTEN_CODE_CHANGE)) {
                phoneStateListener.mSubId = Integer.valueOf(i2 == 0 ? -1 : i);
            } else if (phoneStateListener.mSubId != null) {
                i = phoneStateListener.mSubId.intValue();
            }
            sRegistry.listenForSubscriber(i, str, str2, phoneStateListener.callback, i2, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void notifyCarrierNetworkChange(boolean z) {
        try {
            sRegistry.notifyCarrierNetworkChange(z);
        } catch (RemoteException unused) {
        }
    }

    public void notifyCallStateChanged(int i, int i2, int i3, String str) {
        try {
            sRegistry.notifyCallState(i2, i, i3, str);
        } catch (RemoteException unused) {
        }
    }

    public void notifyCallStateChangedForAllSubscriptions(int i, String str) {
        try {
            sRegistry.notifyCallStateForAllSubs(i, str);
        } catch (RemoteException unused) {
        }
    }

    public void notifySubscriptionInfoChanged() {
        try {
            sRegistry.notifySubscriptionInfoChanged();
        } catch (RemoteException unused) {
        }
    }

    public void notifyOpportunisticSubscriptionInfoChanged() {
        try {
            sRegistry.notifyOpportunisticSubscriptionInfoChanged();
        } catch (RemoteException unused) {
        }
    }

    public void notifyServiceStateChanged(int i, int i2, ServiceState serviceState) {
        try {
            sRegistry.notifyServiceStateForPhoneId(i2, i, serviceState);
        } catch (RemoteException unused) {
        }
    }

    public void notifySignalStrengthChanged(int i, int i2, SignalStrength signalStrength) {
        try {
            sRegistry.notifySignalStrengthForPhoneId(i2, i, signalStrength);
        } catch (RemoteException unused) {
        }
    }

    public void notifyMessageWaitingChanged(int i, int i2, boolean z) {
        try {
            sRegistry.notifyMessageWaitingChangedForPhoneId(i2, i, z);
        } catch (RemoteException unused) {
        }
    }

    public void notifyCallForwardingChanged(int i, boolean z) {
        try {
            sRegistry.notifyCallForwardingChangedForSubscriber(i, z);
        } catch (RemoteException unused) {
        }
    }

    public void notifyDataActivityChanged(int i, int i2) {
        try {
            sRegistry.notifyDataActivityForSubscriber(i, i2);
        } catch (RemoteException unused) {
        }
    }

    public void notifyDataConnectionForSubscriber(int i, int i2, int i3, PreciseDataConnectionState preciseDataConnectionState) {
        try {
            sRegistry.notifyDataConnectionForSubscriber(i, i2, i3, preciseDataConnectionState);
        } catch (RemoteException unused) {
        }
    }

    public void notifyCallQualityChanged(int i, int i2, CallQuality callQuality, int i3) {
        try {
            sRegistry.notifyCallQualityChanged(callQuality, i2, i, i3);
        } catch (RemoteException unused) {
        }
    }

    public void notifyEmergencyNumberList(int i, int i2) {
        try {
            sRegistry.notifyEmergencyNumberList(i2, i);
        } catch (RemoteException unused) {
        }
    }

    public void notifyOutgoingEmergencyCall(int i, int i2, EmergencyNumber emergencyNumber) {
        try {
            sRegistry.notifyOutgoingEmergencyCall(i, i2, emergencyNumber);
        } catch (RemoteException unused) {
        }
    }

    public void notifyOutgoingEmergencySms(int i, int i2, EmergencyNumber emergencyNumber) {
        try {
            sRegistry.notifyOutgoingEmergencySms(i, i2, emergencyNumber);
        } catch (RemoteException unused) {
        }
    }

    public void notifyRadioPowerStateChanged(int i, int i2, int i3) {
        try {
            sRegistry.notifyRadioPowerStateChanged(i2, i, i3);
        } catch (RemoteException unused) {
        }
    }

    public void notifyPhoneCapabilityChanged(PhoneCapability phoneCapability) {
        try {
            sRegistry.notifyPhoneCapabilityChanged(phoneCapability);
        } catch (RemoteException unused) {
        }
    }

    public void notifyDataActivationStateChanged(int i, int i2, int i3) {
        try {
            sRegistry.notifySimActivationStateChangedForPhoneId(i2, i, 1, i3);
        } catch (RemoteException unused) {
        }
    }

    public void notifyVoiceActivationStateChanged(int i, int i2, int i3) {
        try {
            sRegistry.notifySimActivationStateChangedForPhoneId(i2, i, 0, i3);
        } catch (RemoteException unused) {
        }
    }

    public void notifyUserMobileDataStateChanged(int i, int i2, boolean z) {
        try {
            sRegistry.notifyUserMobileDataStateChangedForPhoneId(i, i2, z);
        } catch (RemoteException unused) {
        }
    }

    public void notifyDisplayInfoChanged(int i, int i2, TelephonyDisplayInfo telephonyDisplayInfo) {
        try {
            sRegistry.notifyDisplayInfoChanged(i, i2, telephonyDisplayInfo);
        } catch (RemoteException unused) {
        }
    }

    public void notifyImsDisconnectCause(int i, ImsReasonInfo imsReasonInfo) {
        try {
            sRegistry.notifyImsDisconnectCause(i, imsReasonInfo);
        } catch (RemoteException unused) {
        }
    }

    public void notifyPreciseDataConnectionFailed(int i, int i2, int i3, String str, int i4) {
        try {
            sRegistry.notifyPreciseDataConnectionFailed(i2, i, i3, str, i4);
        } catch (RemoteException unused) {
        }
    }

    public void notifySrvccStateChanged(int i, int i2) {
        try {
            sRegistry.notifySrvccStateChanged(i, i2);
        } catch (RemoteException unused) {
        }
    }

    public void notifyPreciseCallState(int i, int i2, int i3, int i4, int i5) {
        try {
            sRegistry.notifyPreciseCallState(i2, i, i3, i4, i5);
        } catch (RemoteException unused) {
        }
    }

    public void notifyDisconnectCause(int i, int i2, int i3, int i4) {
        try {
            sRegistry.notifyDisconnectCause(i, i2, i3, i4);
        } catch (RemoteException unused) {
        }
    }

    public void notifyCellLocation(int i, CellIdentity cellIdentity) {
        try {
            sRegistry.notifyCellLocationForSubscriber(i, cellIdentity);
        } catch (RemoteException unused) {
        }
    }

    public void notifyCellInfoChanged(int i, List<CellInfo> list) {
        try {
            sRegistry.notifyCellInfoForSubscriber(i, list);
        } catch (RemoteException unused) {
        }
    }

    public void notifyActiveDataSubIdChanged(int i) {
        try {
            sRegistry.notifyActiveDataSubIdChanged(i);
        } catch (RemoteException unused) {
        }
    }

    public void notifyRegistrationFailed(int i, int i2, CellIdentity cellIdentity, String str, int i3, int i4, int i5) {
        try {
            sRegistry.notifyRegistrationFailed(i, i2, cellIdentity, str, i3, i4, i5);
        } catch (RemoteException unused) {
        }
    }

    public void notifyBarringInfoChanged(int i, int i2, BarringInfo barringInfo) {
        try {
            sRegistry.notifyBarringInfoChanged(i, i2, barringInfo);
        } catch (RemoteException unused) {
        }
    }
}
