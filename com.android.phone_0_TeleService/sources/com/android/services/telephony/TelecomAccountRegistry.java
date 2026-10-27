package com.android.services.telephony;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.provider.Settings;
import android.provider.Telephony;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.ims.ImsException;
import android.telephony.ims.ImsManager;
import android.telephony.ims.ImsMmTelManager;
import android.telephony.ims.ImsReasonInfo;
import android.telephony.ims.RegistrationManager;
import android.telephony.ims.feature.MmTelFeature;
import android.text.TextUtils;
import com.android.internal.telephony.ExponentialBackoff;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;
import com.android.internal.telephony.SubscriptionController;
import com.android.phone.PhoneGlobals;
import com.android.phone.R;
import com.android.services.telephony.PstnPhoneCapabilitiesNotifier;
import com.android.services.telephony.TelecomAccountRegistry;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class TelecomAccountRegistry {
    private static TelecomAccountRegistry sInstance;
    private final Context mContext;
    private final ImsManager mImsManager;
    private ExponentialBackoff mRegisterSubscriptionListenerBackoff;
    private final SubscriptionManager mSubscriptionManager;
    private final TelecomManager mTelecomManager;
    private TelephonyConnectionService mTelephonyConnectionService;
    private final TelephonyManager mTelephonyManager;
    private SubscriptionManager.OnSubscriptionsChangedListener mOnSubscriptionsChangedListener = new SubscriptionManager.OnSubscriptionsChangedListener() { // from class: com.android.services.telephony.TelecomAccountRegistry.1
        @Override // android.telephony.SubscriptionManager.OnSubscriptionsChangedListener
        public void onSubscriptionsChanged() {
            if (TelecomAccountRegistry.this.mSubscriptionListenerState != 3) {
                TelecomAccountRegistry.this.mRegisterSubscriptionListenerBackoff.stop();
                TelecomAccountRegistry.this.mHandlerThread.quitSafely();
            }
            TelecomAccountRegistry.this.mSubscriptionListenerState = 3;
            Log.i(this, "TelecomAccountRegistry: onSubscriptionsChanged - update accounts", new Object[0]);
            TelecomAccountRegistry.this.tearDownAccounts();
            TelecomAccountRegistry.this.setupAccounts();
        }

        public void onAddListenerFailed() {
            Log.w(this, "TelecomAccountRegistry: onAddListenerFailed - failed to register OnSubscriptionsChangedListener", new Object[0]);
            TelecomAccountRegistry.this.tearDownAccounts();
            TelecomAccountRegistry.this.setupAccounts();
            if (TelecomAccountRegistry.this.mSubscriptionListenerState == 0) {
                TelecomAccountRegistry.this.mSubscriptionListenerState = 2;
                TelecomAccountRegistry.this.mRegisterSubscriptionListenerBackoff.start();
            } else {
                TelecomAccountRegistry.this.mRegisterSubscriptionListenerBackoff.notifyFailed();
            }
        }
    };
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.services.telephony.TelecomAccountRegistry.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.USER_SWITCHED".equals(intent.getAction())) {
                Log.i(this, "TelecomAccountRegistry: User changed, re-registering phone accounts.", new Object[0]);
                UserHandle userHandle = (UserHandle) intent.getParcelableExtra("android.intent.extra.USER");
                TelecomAccountRegistry.this.mIsPrimaryUser = userHandle == null ? true : userHandle.isSystem();
                TelecomAccountRegistry.this.tearDownAccounts();
                TelecomAccountRegistry.this.setupAccounts();
                return;
            }
            if ("android.telephony.action.CARRIER_CONFIG_CHANGED".equals(intent.getAction())) {
                Log.i(this, "Carrier-config changed, checking for phone account updates.", new Object[0]);
                TelecomAccountRegistry.this.handleCarrierConfigChange(intent.getIntExtra("android.telephony.extra.SUBSCRIPTION_INDEX", -1));
            }
        }
    };
    private BroadcastReceiver mLocaleChangeReceiver = new BroadcastReceiver() { // from class: com.android.services.telephony.TelecomAccountRegistry.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.i(this, "Locale change; re-registering phone accounts.", new Object[0]);
            TelecomAccountRegistry.this.tearDownAccounts();
            TelecomAccountRegistry.this.setupAccounts();
        }
    };
    private final PhoneStateListener mPhoneStateListener = new PhoneStateListener() { // from class: com.android.services.telephony.TelecomAccountRegistry.4
        @Override // android.telephony.PhoneStateListener
        public void onServiceStateChanged(ServiceState serviceState) {
            int state = serviceState.getState();
            if (state != 0 || TelecomAccountRegistry.this.mServiceState == state) {
                synchronized (TelecomAccountRegistry.this.mAccountsLock) {
                    Iterator it = TelecomAccountRegistry.this.mAccounts.iterator();
                    while (it.hasNext()) {
                        ((AccountEntry) it.next()).updateRttCapability();
                    }
                }
            } else {
                TelecomAccountRegistry.this.tearDownAccounts();
                TelecomAccountRegistry.this.setupAccounts();
            }
            TelecomAccountRegistry.this.mServiceState = state;
        }

        @Override // android.telephony.PhoneStateListener
        public void onActiveDataSubscriptionIdChanged(int i) {
            TelecomAccountRegistry.this.mActiveDataSubscriptionId = i;
            synchronized (TelecomAccountRegistry.this.mAccountsLock) {
                Iterator it = TelecomAccountRegistry.this.mAccounts.iterator();
                while (it.hasNext()) {
                    ((AccountEntry) it.next()).updateDefaultDataSubId(TelecomAccountRegistry.this.mActiveDataSubscriptionId);
                }
            }
        }
    };
    private List<AccountEntry> mAccounts = new LinkedList();
    private final Object mAccountsLock = new Object();
    private int mSubscriptionListenerState = 0;
    private int mServiceState = 3;
    private int mActiveDataSubscriptionId = -1;
    private boolean mIsPrimaryUser = true;
    private final HandlerThread mHandlerThread = new HandlerThread("TelecomAccountRegistry");
    private Runnable mRegisterOnSubscriptionsChangedListenerRunnable = new Runnable() { // from class: com.android.services.telephony.TelecomAccountRegistry.5
        @Override // java.lang.Runnable
        public void run() {
            if (TelecomAccountRegistry.this.mSubscriptionListenerState != 3) {
                Log.i(this, "TelecomAccountRegistry: performing delayed register.", new Object[0]);
                SubscriptionManager.from(TelecomAccountRegistry.this.mContext).addOnSubscriptionsChangedListener(TelecomAccountRegistry.this.mOnSubscriptionsChangedListener);
            }
        }
    };

    final class AccountEntry implements PstnPhoneCapabilitiesNotifier.Listener {
        private PhoneAccount mAccount;
        private RegistrationManager.RegistrationCallback mImsRegistrationCallback;
        private final PstnIncomingCallNotifier mIncomingCallNotifier;
        private boolean mIsAdhocConfCapable;
        private final boolean mIsDummy;
        private boolean mIsEmergency;
        private boolean mIsEmergencyPreferred;
        private boolean mIsManageImsConferenceCallSupported;
        private boolean mIsMergeCallSupported;
        private boolean mIsMergeImsCallSupported;
        private boolean mIsMergeOfWifiCallsAllowedWhenVoWifiOff;
        private boolean mIsRttCapable;
        private boolean mIsShowPreciseFailedCause;
        private boolean mIsUsingSimCallManager;
        private boolean mIsVideoCapable;
        private boolean mIsVideoConferencingSupported;
        private boolean mIsVideoPauseSupported;
        private boolean mIsVideoPresenceSupported;
        private MmTelFeature.MmTelCapabilities mMmTelCapabilities;
        private ImsMmTelManager mMmTelManager;
        private ImsMmTelManager.CapabilityCallback mMmtelCapabilityCallback;
        private final Phone mPhone;
        private final PstnPhoneCapabilitiesNotifier mPhoneCapabilitiesNotifier;

        AccountEntry(Phone phone, boolean z, boolean z2) {
            this.mPhone = phone;
            this.mIsEmergency = z;
            this.mIsDummy = z2;
            this.mIsAdhocConfCapable = phone.isImsRegistered();
            PhoneAccount phoneAccountRegisterPstnPhoneAccount = registerPstnPhoneAccount(z, z2);
            this.mAccount = phoneAccountRegisterPstnPhoneAccount;
            Log.i(this, "Registered phoneAccount: %s with handle: %s", phoneAccountRegisterPstnPhoneAccount, phoneAccountRegisterPstnPhoneAccount.getAccountHandle());
            this.mIncomingCallNotifier = new PstnIncomingCallNotifier(this.mPhone);
            this.mPhoneCapabilitiesNotifier = new PstnPhoneCapabilitiesNotifier(this.mPhone, this);
            if (this.mIsDummy || z) {
                return;
            }
            try {
                if (this.mPhone.getContext().getPackageManager().hasSystemFeature("android.hardware.telephony.ims")) {
                    this.mMmTelManager = ImsMmTelManager.createForSubscriptionId(getSubId());
                }
                this.mMmtelCapabilityCallback = new ImsMmTelManager.CapabilityCallback(TelecomAccountRegistry.this) { // from class: com.android.services.telephony.TelecomAccountRegistry.AccountEntry.1
                    @Override // android.telephony.ims.ImsMmTelManager.CapabilityCallback
                    public void onCapabilitiesStatusChanged(MmTelFeature.MmTelCapabilities mmTelCapabilities) {
                        AccountEntry.this.mMmTelCapabilities = mmTelCapabilities;
                        AccountEntry.this.updateRttCapability();
                    }
                };
                registerMmTelCapabilityCallback();
                this.mImsRegistrationCallback = new RegistrationManager.RegistrationCallback(TelecomAccountRegistry.this) { // from class: com.android.services.telephony.TelecomAccountRegistry.AccountEntry.2
                    @Override // android.telephony.ims.RegistrationManager.RegistrationCallback
                    public void onRegistered(int i) {
                        AccountEntry.this.updateAdhocConfCapability(true);
                    }

                    @Override // android.telephony.ims.RegistrationManager.RegistrationCallback
                    public void onRegistering(int i) {
                        AccountEntry.this.updateAdhocConfCapability(false);
                    }

                    @Override // android.telephony.ims.RegistrationManager.RegistrationCallback
                    public void onUnregistered(ImsReasonInfo imsReasonInfo) {
                        AccountEntry.this.updateAdhocConfCapability(false);
                    }
                };
                registerImsRegistrationCallback();
            } catch (IllegalArgumentException e) {
                Log.i(this, "Not registering MmTel capabilities listener because the subid '" + getSubId() + "' is invalid: " + e.getMessage(), new Object[0]);
            }
        }

        void teardown() {
            this.mIncomingCallNotifier.teardown();
            this.mPhoneCapabilitiesNotifier.teardown();
            ImsMmTelManager imsMmTelManager = this.mMmTelManager;
            if (imsMmTelManager != null) {
                ImsMmTelManager.CapabilityCallback capabilityCallback = this.mMmtelCapabilityCallback;
                if (capabilityCallback != null) {
                    imsMmTelManager.unregisterMmTelCapabilityCallback(capabilityCallback);
                }
                RegistrationManager.RegistrationCallback registrationCallback = this.mImsRegistrationCallback;
                if (registrationCallback != null) {
                    this.mMmTelManager.unregisterImsRegistrationCallback(registrationCallback);
                }
            }
        }

        private void registerMmTelCapabilityCallback() {
            ImsMmTelManager imsMmTelManager = this.mMmTelManager;
            if (imsMmTelManager == null || this.mMmtelCapabilityCallback == null) {
                return;
            }
            try {
                imsMmTelManager.registerMmTelCapabilityCallback(TelecomAccountRegistry.this.mContext.getMainExecutor(), this.mMmtelCapabilityCallback);
            } catch (ImsException e) {
                Log.w(this, "registerMmTelCapabilityCallback: registration failed, no ImsService available. Exception: " + e.getMessage(), new Object[0]);
            } catch (IllegalArgumentException e2) {
                Log.w(this, "registerMmTelCapabilityCallback: registration failed, invalid subscription, Exception" + e2.getMessage(), new Object[0]);
            }
        }

        private void registerImsRegistrationCallback() {
            ImsMmTelManager imsMmTelManager = this.mMmTelManager;
            if (imsMmTelManager == null || this.mImsRegistrationCallback == null) {
                return;
            }
            try {
                imsMmTelManager.registerImsRegistrationCallback(TelecomAccountRegistry.this.mContext.getMainExecutor(), this.mImsRegistrationCallback);
            } catch (ImsException e) {
                Log.w(this, "registerImsRegistrationCallback: registration failed, no ImsService available. Exception: " + e.getMessage(), new Object[0]);
            } catch (IllegalArgumentException e2) {
                Log.w(this, "registerImsRegistrationCallback: registration failed, invalid subscription, Exception" + e2.getMessage(), new Object[0]);
            }
        }

        public void reRegisterPstnPhoneAccount() {
            PhoneAccount phoneAccountBuildPstnPhoneAccount = buildPstnPhoneAccount(this.mIsEmergency, this.mIsDummy);
            if (!phoneAccountBuildPstnPhoneAccount.equals(this.mAccount)) {
                Log.i(this, "reRegisterPstnPhoneAccount: subId: " + getSubId() + " - re-register due to account change.", new Object[0]);
                TelecomAccountRegistry.this.mTelecomManager.registerPhoneAccount(phoneAccountBuildPstnPhoneAccount);
                this.mAccount = phoneAccountBuildPstnPhoneAccount;
                return;
            }
            Log.i(this, "reRegisterPstnPhoneAccount: subId: " + getSubId() + " - no change", new Object[0]);
        }

        private PhoneAccount registerPstnPhoneAccount(boolean z, boolean z2) {
            PhoneAccount phoneAccountBuildPstnPhoneAccount = buildPstnPhoneAccount(this.mIsEmergency, this.mIsDummy);
            TelecomAccountRegistry.this.mTelecomManager.registerPhoneAccount(phoneAccountBuildPstnPhoneAccount);
            return phoneAccountBuildPstnPhoneAccount;
        }

        /* JADX WARN: Removed duplicated region for block: B:100:0x0317  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x013d  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x014d  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0155  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x015a  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x0181  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x0190  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0194  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x01c1  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x01d7  */
        /* JADX WARN: Removed duplicated region for block: B:78:0x022c  */
        /* JADX WARN: Removed duplicated region for block: B:85:0x0278  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x02c2  */
        /* JADX WARN: Removed duplicated region for block: B:97:0x02e5  */
        /* JADX WARN: Removed duplicated region for block: B:99:0x02e8  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private android.telecom.PhoneAccount buildPstnPhoneAccount(boolean r21, boolean r22) {
            /*
                Method dump skipped, instruction units count: 861
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.services.telephony.TelecomAccountRegistry.AccountEntry.buildPstnPhoneAccount(boolean, boolean):android.telecom.PhoneAccount");
        }

        public PhoneAccountHandle getPhoneAccountHandle() {
            PhoneAccount phoneAccount = this.mAccount;
            if (phoneAccount != null) {
                return phoneAccount.getAccountHandle();
            }
            return null;
        }

        public int getSubId() {
            return this.mPhone.getSubId();
        }

        private boolean isEmergencyPreferredAccount(int i, int i2) {
            Log.d(this, "isEmergencyPreferredAccount: subId=" + i + ", activeData=" + i2, new Object[0]);
            if (!TelecomAccountRegistry.this.mContext.getResources().getBoolean(R.bool.config_gnss_supl_requires_default_data_for_emergency)) {
                Log.d(this, "isEmergencyPreferredAccount: Device does not require preference.", new Object[0]);
                return false;
            }
            SubscriptionController subscriptionController = SubscriptionController.getInstance();
            if (subscriptionController == null) {
                Log.d(this, "isEmergencyPreferredAccount: SubscriptionController not available.", new Object[0]);
                return false;
            }
            int[] activeSubIdList = subscriptionController.getActiveSubIdList(false);
            if (activeSubIdList == null || activeSubIdList.length <= 1) {
                Log.d(this, "isEmergencyPreferredAccount: one or less active subscriptions.", new Object[0]);
                return false;
            }
            if (!SubscriptionManager.isValidSubscriptionId(i)) {
                Log.d(this, "isEmergencyPreferredAccount: provided subId " + i + "is not valid.", new Object[0]);
                return false;
            }
            int defaultDataSubId = subscriptionController.getDefaultDataSubId();
            boolean zIsValidSubscriptionId = SubscriptionManager.isValidSubscriptionId(i2);
            boolean z = zIsValidSubscriptionId && subscriptionController.isOpportunistic(i2);
            Log.d(this, "isEmergencyPreferredAccount: userDefaultData=" + defaultDataSubId + ", isActiveDataOppurtunistic=" + z, new Object[0]);
            if (!zIsValidSubscriptionId || z) {
                i2 = defaultDataSubId;
            }
            return i == i2;
        }

        private boolean isCarrierVideoPauseSupported() {
            PersistableBundle carrierConfigForSubId = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId());
            return carrierConfigForSubId != null && carrierConfigForSubId.getBoolean("support_pause_ims_video_calls_bool");
        }

        private boolean isCarrierVideoPresenceSupported() {
            PersistableBundle carrierConfigForSubId = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId());
            return (carrierConfigForSubId != null && carrierConfigForSubId.getBoolean("use_rcs_presence_bool")) && isUserContactDiscoverySettingEnabled();
        }

        private boolean isUserContactDiscoverySettingEnabled() {
            try {
                return TelecomAccountRegistry.this.mImsManager.getImsRcsManager(this.mPhone.getSubId()).getUceAdapter().isUceSettingEnabled();
            } catch (Exception e) {
                Log.w("TelecomAccountRegistry", "isUserContactDiscoverySettingEnabled caught exception: " + e, new Object[0]);
                return false;
            }
        }

        private boolean isCarrierInstantLetteringSupported() {
            PersistableBundle carrierConfigForSubId = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId());
            return carrierConfigForSubId != null && carrierConfigForSubId.getBoolean("carrier_instant_lettering_available_bool");
        }

        private boolean isCarrierAdhocConferenceCallSupported() {
            PersistableBundle carrierConfigForSubId = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId());
            return carrierConfigForSubId != null && carrierConfigForSubId.getBoolean("support_adhoc_conference_calls_bool");
        }

        private boolean isCarrierMergeCallSupported() {
            PersistableBundle carrierConfigForSubId = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId());
            return carrierConfigForSubId != null && carrierConfigForSubId.getBoolean("support_conference_call_bool");
        }

        private boolean isCarrierMergeImsCallSupported() {
            return PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId()).getBoolean("support_ims_conference_call_bool");
        }

        private boolean isCarrierEmergencyVideoCallsAllowed() {
            PersistableBundle carrierConfigForSubId = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId());
            return carrierConfigForSubId != null && carrierConfigForSubId.getBoolean("allow_emergency_video_calls_bool");
        }

        private boolean isCarrierVideoConferencingSupported() {
            PersistableBundle carrierConfigForSubId = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId());
            return carrierConfigForSubId != null && carrierConfigForSubId.getBoolean("support_video_conference_call_bool");
        }

        private boolean isCarrierMergeOfWifiCallsAllowedWhenVoWifiOff() {
            PersistableBundle carrierConfigForSubId = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId());
            return carrierConfigForSubId != null && carrierConfigForSubId.getBoolean("allow_merge_wifi_calls_when_vowifi_off_bool");
        }

        private boolean isCarrierManageImsConferenceCallSupported() {
            return PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId()).getBoolean("support_manage_ims_conference_call_bool");
        }

        private boolean isCarrierUsingSimCallManager() {
            return !TextUtils.isEmpty(PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId()).getString("default_sim_call_manager_string"));
        }

        private boolean isCarrierShowPreciseFailedCause() {
            return PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId()).getBoolean("show_precise_failed_cause_bool");
        }

        private boolean isCarrierUseCallRecordingTone() {
            return PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId()).getBoolean("play_call_recording_tone_bool");
        }

        private boolean isCarrierAllowRttWhenRoaming() {
            return PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId()).getBoolean("rtt_supported_while_roaming_bool");
        }

        private Bundle getPhoneAccountExtras() {
            PersistableBundle carrierConfigForSubId = PhoneGlobals.getInstance().getCarrierConfigForSubId(this.mPhone.getSubId());
            int i = carrierConfigForSubId.getInt("carrier_instant_lettering_length_limit_int");
            String string = carrierConfigForSubId.getString("carrier_instant_lettering_encoding_string");
            Bundle bundle = new Bundle();
            bundle.putInt("android.telecom.extra.CALL_SUBJECT_MAX_LENGTH", i);
            bundle.putString("android.telecom.extra.CALL_SUBJECT_CHARACTER_ENCODING", string);
            return bundle;
        }

        @Override // com.android.services.telephony.PstnPhoneCapabilitiesNotifier.Listener
        public void onVideoCapabilitiesChanged(boolean z) {
            this.mIsVideoCapable = z;
            synchronized (TelecomAccountRegistry.this.mAccountsLock) {
                if (TelecomAccountRegistry.this.mAccounts.contains(this)) {
                    this.mAccount = registerPstnPhoneAccount(this.mIsEmergency, this.mIsDummy);
                }
            }
        }

        public void updateAdhocConfCapability(boolean z) {
            synchronized (TelecomAccountRegistry.this.mAccountsLock) {
                if (TelecomAccountRegistry.this.mAccounts.contains(this)) {
                    if (z != this.mIsAdhocConfCapable) {
                        Log.i(this, "updateAdhocConfCapability - changed, new value: " + z, new Object[0]);
                        this.mIsAdhocConfCapable = z;
                        this.mAccount = registerPstnPhoneAccount(this.mIsEmergency, this.mIsDummy);
                    }
                }
            }
        }

        public void updateVideoPresenceCapability() {
            synchronized (TelecomAccountRegistry.this.mAccountsLock) {
                if (TelecomAccountRegistry.this.mAccounts.contains(this)) {
                    boolean zIsCarrierVideoPresenceSupported = isCarrierVideoPresenceSupported();
                    if (this.mIsVideoPresenceSupported != zIsCarrierVideoPresenceSupported) {
                        Log.i(this, "updateVideoPresenceCapability for subId=" + this.mPhone.getSubId() + ", new value= " + zIsCarrierVideoPresenceSupported, new Object[0]);
                        this.mAccount = registerPstnPhoneAccount(this.mIsEmergency, this.mIsDummy);
                    }
                }
            }
        }

        public void updateRttCapability() {
            boolean zIsRttCurrentlySupported = isRttCurrentlySupported();
            if (zIsRttCurrentlySupported != this.mIsRttCapable) {
                Log.i(this, "updateRttCapability - changed, new value: " + zIsRttCurrentlySupported, new Object[0]);
                this.mAccount = registerPstnPhoneAccount(this.mIsEmergency, this.mIsDummy);
            }
        }

        public void updateDefaultDataSubId(int i) {
            boolean zIsEmergencyPreferredAccount = isEmergencyPreferredAccount(this.mPhone.getSubId(), i);
            if (zIsEmergencyPreferredAccount != this.mIsEmergencyPreferred) {
                Log.i(this, "updateDefaultDataSubId - changed, new value: " + zIsEmergencyPreferredAccount, new Object[0]);
                this.mAccount = registerPstnPhoneAccount(this.mIsEmergency, this.mIsDummy);
            }
        }

        private boolean isRttCurrentlySupported() {
            if (this.mIsEmergency) {
                boolean z = TelecomAccountRegistry.this.mContext.getResources().getBoolean(R.bool.config_support_rtt);
                if (!TelecomAccountRegistry.this.mContext.getResources().getBoolean(R.bool.config_support_simless_emergency_rtt) || !z) {
                    Log.i(this, "isRttCurrentlySupported -- emergency acct and no device support", new Object[0]);
                    return false;
                }
                String lowerCase = this.mPhone.getServiceStateTracker().getLocaleTracker().getLastKnownCountryIso().toLowerCase();
                String[] stringArray = TelecomAccountRegistry.this.mContext.getResources().getStringArray(R.array.config_simless_emergency_rtt_supported_countries);
                if (stringArray != null && !Arrays.stream(stringArray).noneMatch(Predicate.isEqual(lowerCase))) {
                    return true;
                }
                Log.i(this, "isRttCurrentlySupported -- emergency acct and not supported in this country: " + lowerCase, new Object[0]);
                return false;
            }
            boolean zIsImsVoiceAvailable = isImsVoiceAvailable();
            boolean zIsRttEnabled = PhoneGlobals.getInstance().phoneMgr.isRttEnabled(this.mPhone.getSubId());
            boolean zIsNetworkRoaming = TelecomAccountRegistry.this.mTelephonyManager.isNetworkRoaming(this.mPhone.getSubId());
            boolean z2 = this.mPhone.getImsRegistrationTech() == 1;
            boolean zIsCarrierAllowRttWhenRoaming = isCarrierAllowRttWhenRoaming();
            boolean z3 = (!zIsNetworkRoaming || z2 || zIsCarrierAllowRttWhenRoaming) ? false : true;
            Log.i(this, "isRttCurrentlySupported -- regular acct, hasVoiceAvailability: " + zIsImsVoiceAvailable + "\n isRttSupported: " + zIsRttEnabled + "\n alwaysAllowWhileRoaming: " + zIsCarrierAllowRttWhenRoaming + "\n isRoaming: " + zIsNetworkRoaming + "\n isOnWfc: " + z2 + "\n", new Object[0]);
            return zIsImsVoiceAvailable && zIsRttEnabled && !z3;
        }

        public boolean isVideoPauseSupported() {
            return this.mIsVideoCapable && this.mIsVideoPauseSupported;
        }

        public boolean isMergeCallSupported() {
            return this.mIsMergeCallSupported;
        }

        public boolean isMergeImsCallSupported() {
            return this.mIsMergeImsCallSupported;
        }

        public boolean isVideoConferencingSupported() {
            return this.mIsVideoConferencingSupported;
        }

        public boolean isMergeOfWifiCallsAllowedWhenVoWifiOff() {
            return this.mIsMergeOfWifiCallsAllowedWhenVoWifiOff;
        }

        public boolean isManageImsConferenceCallSupported() {
            return this.mIsManageImsConferenceCallSupported;
        }

        public boolean isUsingSimCallManager() {
            return this.mIsUsingSimCallManager;
        }

        public boolean isShowPreciseFailedCause() {
            return this.mIsShowPreciseFailedCause;
        }

        private boolean isImsVoiceAvailable() {
            MmTelFeature.MmTelCapabilities mmTelCapabilities = this.mMmTelCapabilities;
            if (mmTelCapabilities != null) {
                return mmTelCapabilities.isCapable(1);
            }
            if (this.mMmTelManager == null) {
                return false;
            }
            registerMmTelCapabilityCallback();
            return this.mMmTelManager.isAvailable(0, 1) || this.mMmTelManager.isAvailable(1, 1);
        }
    }

    TelecomAccountRegistry(Context context) {
        this.mContext = context;
        this.mTelecomManager = (TelecomManager) context.getSystemService(TelecomManager.class);
        this.mImsManager = (ImsManager) context.getSystemService(ImsManager.class);
        this.mTelephonyManager = TelephonyManager.from(context);
        this.mSubscriptionManager = SubscriptionManager.from(context);
        this.mHandlerThread.start();
        this.mRegisterSubscriptionListenerBackoff = new ExponentialBackoff(1000L, 60000L, 2, this.mHandlerThread.getLooper(), this.mRegisterOnSubscriptionsChangedListenerRunnable);
    }

    public static synchronized TelecomAccountRegistry getInstance(Context context) {
        if (sInstance == null && context != null) {
            sInstance = new TelecomAccountRegistry(context);
        }
        return sInstance;
    }

    void setTelephonyConnectionService(TelephonyConnectionService telephonyConnectionService) {
        this.mTelephonyConnectionService = telephonyConnectionService;
    }

    TelephonyConnectionService getTelephonyConnectionService() {
        return this.mTelephonyConnectionService;
    }

    boolean isVideoPauseSupported(PhoneAccountHandle phoneAccountHandle) {
        synchronized (this.mAccountsLock) {
            for (AccountEntry accountEntry : this.mAccounts) {
                if (accountEntry.getPhoneAccountHandle().equals(phoneAccountHandle)) {
                    return accountEntry.isVideoPauseSupported();
                }
            }
            return false;
        }
    }

    boolean isMergeCallSupported(PhoneAccountHandle phoneAccountHandle) {
        synchronized (this.mAccountsLock) {
            for (AccountEntry accountEntry : this.mAccounts) {
                if (accountEntry.getPhoneAccountHandle().equals(phoneAccountHandle)) {
                    return accountEntry.isMergeCallSupported();
                }
            }
            return false;
        }
    }

    boolean isVideoConferencingSupported(PhoneAccountHandle phoneAccountHandle) {
        synchronized (this.mAccountsLock) {
            for (AccountEntry accountEntry : this.mAccounts) {
                if (accountEntry.getPhoneAccountHandle().equals(phoneAccountHandle)) {
                    return accountEntry.isVideoConferencingSupported();
                }
            }
            return false;
        }
    }

    boolean isMergeOfWifiCallsAllowedWhenVoWifiOff(final PhoneAccountHandle phoneAccountHandle) {
        synchronized (this.mAccountsLock) {
            Optional<AccountEntry> optionalFindFirst = this.mAccounts.stream().filter(new Predicate() { // from class: com.android.services.telephony.-$$Lambda$TelecomAccountRegistry$fe3nPlrVlXnDDguTh-3OIdgY7JE
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((TelecomAccountRegistry.AccountEntry) obj).getPhoneAccountHandle().equals(phoneAccountHandle);
                }
            }).findFirst();
            if (!optionalFindFirst.isPresent()) {
                return false;
            }
            return optionalFindFirst.get().isMergeOfWifiCallsAllowedWhenVoWifiOff();
        }
    }

    boolean isMergeImsCallSupported(PhoneAccountHandle phoneAccountHandle) {
        synchronized (this.mAccountsLock) {
            for (AccountEntry accountEntry : this.mAccounts) {
                if (accountEntry.getPhoneAccountHandle().equals(phoneAccountHandle)) {
                    return accountEntry.isMergeImsCallSupported();
                }
            }
            return false;
        }
    }

    boolean isManageImsConferenceCallSupported(PhoneAccountHandle phoneAccountHandle) {
        synchronized (this.mAccountsLock) {
            for (AccountEntry accountEntry : this.mAccounts) {
                if (accountEntry.getPhoneAccountHandle().equals(phoneAccountHandle)) {
                    return accountEntry.isManageImsConferenceCallSupported();
                }
            }
            return false;
        }
    }

    boolean isShowPreciseFailedCause(PhoneAccountHandle phoneAccountHandle) {
        synchronized (this.mAccountsLock) {
            for (AccountEntry accountEntry : this.mAccounts) {
                if (accountEntry.getPhoneAccountHandle().equals(phoneAccountHandle)) {
                    return accountEntry.isShowPreciseFailedCause();
                }
            }
            return false;
        }
    }

    SubscriptionManager getSubscriptionManager() {
        return this.mSubscriptionManager;
    }

    public Uri getAddress(PhoneAccountHandle phoneAccountHandle) {
        synchronized (this.mAccountsLock) {
            for (AccountEntry accountEntry : this.mAccounts) {
                if (accountEntry.getPhoneAccountHandle().equals(phoneAccountHandle)) {
                    return accountEntry.mAccount.getAddress();
                }
            }
            return null;
        }
    }

    public void refreshAdhocConference(boolean z) {
        synchronized (this.mAccountsLock) {
            Log.v(this, "refreshAdhocConference isEnable = " + z, new Object[0]);
            for (AccountEntry accountEntry : this.mAccounts) {
                boolean zHasCapabilities = accountEntry.mAccount.hasCapabilities(16384);
                if (!z && zHasCapabilities) {
                    accountEntry.updateAdhocConfCapability(z);
                } else if (z && !zHasCapabilities) {
                    accountEntry.updateAdhocConfCapability(accountEntry.mPhone.isImsRegistered());
                }
            }
        }
    }

    public boolean isUsingSimCallManager(PhoneAccountHandle phoneAccountHandle) {
        synchronized (this.mAccountsLock) {
            for (AccountEntry accountEntry : this.mAccounts) {
                if (accountEntry.getPhoneAccountHandle().equals(phoneAccountHandle)) {
                    return accountEntry.isUsingSimCallManager();
                }
            }
            return false;
        }
    }

    public void setupOnBoot() {
        Log.i(this, "TelecomAccountRegistry: setupOnBoot - register subscription listener", new Object[0]);
        SubscriptionManager.from(this.mContext).addOnSubscriptionsChangedListener(this.mOnSubscriptionsChangedListener);
        this.mTelephonyManager.listen(this.mPhoneStateListener, 4194305);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_SWITCHED");
        intentFilter.addAction("android.telephony.action.CARRIER_CONFIG_CHANGED");
        this.mContext.registerReceiver(this.mReceiver, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter("android.intent.action.LOCALE_CHANGED");
        intentFilter2.addAction("android.telephony.action.NETWORK_COUNTRY_CHANGED");
        this.mContext.registerReceiver(this.mLocaleChangeReceiver, intentFilter2);
        registerContentObservers();
    }

    private void registerContentObservers() {
        ContentObserver contentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.android.services.telephony.TelecomAccountRegistry.6
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                synchronized (TelecomAccountRegistry.this.mAccountsLock) {
                    Iterator it = TelecomAccountRegistry.this.mAccounts.iterator();
                    while (it.hasNext()) {
                        ((AccountEntry) it.next()).updateRttCapability();
                    }
                }
            }
        };
        this.mContext.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("rtt_calling_mode"), false, contentObserver);
        ContentObserver contentObserver2 = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.android.services.telephony.TelecomAccountRegistry.7
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                synchronized (TelecomAccountRegistry.this.mAccountsLock) {
                    Iterator it = TelecomAccountRegistry.this.mAccounts.iterator();
                    while (it.hasNext()) {
                        ((AccountEntry) it.next()).updateVideoPresenceCapability();
                    }
                }
            }
        };
        this.mContext.getContentResolver().registerContentObserver(Uri.withAppendedPath(Telephony.SimInfo.CONTENT_URI, "ims_rcs_uce_enabled"), true, contentObserver2);
    }

    boolean hasAccountEntryForPhoneAccount(PhoneAccountHandle phoneAccountHandle) {
        synchronized (this.mAccountsLock) {
            Iterator<AccountEntry> it = this.mAccounts.iterator();
            while (it.hasNext()) {
                if (it.next().getPhoneAccountHandle().equals(phoneAccountHandle)) {
                    return true;
                }
            }
            return false;
        }
    }

    private void cleanupPhoneAccounts() {
        List callCapablePhoneAccounts;
        ComponentName componentName = new ComponentName(this.mContext, (Class<?>) TelephonyConnectionService.class);
        if (this.mContext.getResources().getBoolean(R.bool.config_emergency_account_emergency_calls_only)) {
            callCapablePhoneAccounts = this.mTelecomManager.getAllPhoneAccountHandles();
        } else {
            callCapablePhoneAccounts = this.mTelecomManager.getCallCapablePhoneAccounts();
        }
        for (PhoneAccountHandle phoneAccountHandle : callCapablePhoneAccounts) {
            if (componentName.equals(phoneAccountHandle.getComponentName()) && !hasAccountEntryForPhoneAccount(phoneAccountHandle)) {
                Log.i(this, "Unregistering phone account %s.", phoneAccountHandle);
                this.mTelecomManager.unregisterPhoneAccount(phoneAccountHandle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupAccounts() {
        Phone[] phones = PhoneFactory.getPhones();
        Log.i(this, "setupAccounts: Found %d phones.  Attempting to register.", Integer.valueOf(phones.length));
        boolean z = this.mContext.getResources().getBoolean(R.bool.config_pstn_phone_accounts_enabled);
        synchronized (this.mAccountsLock) {
            if (z) {
                try {
                    for (Phone phone : phones) {
                        int subId = phone.getSubId();
                        Log.i(this, "setupAccounts: Phone with subscription id %d", Integer.valueOf(subId));
                        if (!SubscriptionManager.isValidSubscriptionId(subId) || phone.getFullIccSerialNumber() == null) {
                            Log.d(this, "setupAccounts: skipping invalid subid %d", Integer.valueOf(subId));
                        } else {
                            SubscriptionInfo activeSubscriptionInfo = SubscriptionManager.from(this.mContext).getActiveSubscriptionInfo(subId);
                            if (activeSubscriptionInfo == null || activeSubscriptionInfo.isOpportunistic()) {
                                Log.d(this, "setupAccounts: skipping unknown or opportunistic subid %d", Integer.valueOf(subId));
                            } else {
                                this.mAccounts.add(new AccountEntry(phone, false, false));
                            }
                        }
                    }
                } catch (Throwable th) {
                    if (this.mAccounts.isEmpty()) {
                        Log.i(this, "setupAccounts: adding default", new Object[0]);
                        this.mAccounts.add(new AccountEntry(PhoneFactory.getDefaultPhone(), true, false));
                    }
                    throw th;
                }
            }
            if (this.mAccounts.isEmpty()) {
                Log.i(this, "setupAccounts: adding default", new Object[0]);
                this.mAccounts.add(new AccountEntry(PhoneFactory.getDefaultPhone(), true, false));
            }
        }
        cleanupPhoneAccounts();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tearDownAccounts() {
        synchronized (this.mAccountsLock) {
            Iterator<AccountEntry> it = this.mAccounts.iterator();
            while (it.hasNext()) {
                it.next().teardown();
            }
            this.mAccounts.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCarrierConfigChange(int i) {
        if (i == -1) {
            return;
        }
        synchronized (this.mAccountsLock) {
            for (AccountEntry accountEntry : this.mAccounts) {
                if (accountEntry.getSubId() == i) {
                    Log.d(this, "handleCarrierConfigChange: subId=%d, accountSubId=%d", Integer.valueOf(i), Integer.valueOf(accountEntry.getSubId()));
                    accountEntry.reRegisterPstnPhoneAccount();
                }
            }
        }
    }
}
