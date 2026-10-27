package com.android.phone;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.internal.telephony.sysprop.TelephonyProperties;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.PowerManager;
import android.os.UserManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.telephony.AnomalyReporter;
import android.telephony.CarrierConfigManager;
import android.telephony.ServiceState;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import com.android.internal.telephony.CallManager;
import com.android.internal.telephony.IndentingPrintWriter;
import com.android.internal.telephony.LocalLog;
import com.android.internal.telephony.MmiCode;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;
import com.android.internal.telephony.SettingsObserver;
import com.android.internal.telephony.TelephonyCapabilities;
import com.android.internal.telephony.TelephonyComponentFactory;
import com.android.internal.telephony.dataconnection.DataConnectionReasons;
import com.android.internal.telephony.ims.ImsResolver;
import com.android.phone.vvm.CarrierVvmPackageInstalledReceiver;
import com.android.services.telephony.rcs.TelephonyRcsService;
import com.android.services.telephony.sip.SipAccountRegistry;
import com.android.services.telephony.sip.SipUtil;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PhoneGlobals extends ContextWrapper {
    private static final boolean DBG = false;
    private static PhoneGlobals sMe = null;
    static boolean sVoiceCapable = true;
    CdmaPhoneCallState cdmaPhoneCallState;
    CarrierConfigLoader configLoader;
    public ImsRcsController imsRcsController;
    CallManager mCM;
    private final CarrierVvmPackageInstalledReceiver mCarrierVvmPackageInstalledReceiver;
    private final LocalLog mDataRoamingNotifLog;
    private int mDefaultDataSubId;
    Handler mHandler;
    ImsResolver mImsResolver;
    private KeyguardManager mKeyguardManager;
    private Activity mPUKEntryActivity;
    private ProgressDialog mPUKEntryProgressDialog;
    private PowerManager mPowerManager;
    private int mPrevRoamingNotification;
    private final BroadcastReceiver mReceiver;
    private final SettingsObserver mSettingsObserver;
    private final SipReceiver mSipReceiver;
    TelephonyRcsService mTelephonyRcsService;
    NotificationMgr notificationMgr;
    CallNotifier notifier;
    private Phone phoneInEcm;
    public PhoneInterfaceManager phoneMgr;

    public enum WakeState {
        SLEEP,
        PARTIAL,
        FULL
    }

    private static class EventSimStateChangedBag {
        final String mIccStatus;
        final int mPhoneId;

        EventSimStateChangedBag(int i, String str) {
            this.mPhoneId = i;
            this.mIccStatus = str;
        }
    }

    public PhoneGlobals(Context context) {
        super(context);
        this.mPrevRoamingNotification = 0;
        WakeState wakeState = WakeState.SLEEP;
        this.mDefaultDataSubId = -1;
        this.mDataRoamingNotifLog = new LocalLog(50);
        this.mReceiver = new PhoneAppBroadcastReceiver();
        this.mSipReceiver = new SipReceiver();
        this.mCarrierVvmPackageInstalledReceiver = new CarrierVvmPackageInstalledReceiver();
        this.mHandler = new Handler() { // from class: com.android.phone.PhoneGlobals.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 3) {
                    if (PhoneGlobals.this.getCarrierConfig().getBoolean("ignore_sim_network_locked_events_bool")) {
                        Log.i("PhoneGlobals", "Ignoring EVENT_SIM_NETWORK_LOCKED event; not showing 'SIM network unlock' PIN entry screen");
                        return;
                    }
                    Log.i("PhoneGlobals", "show sim depersonal panel");
                    Object obj = message.obj;
                    IccNetworkDepersonalizationPanel.showDialog((Phone) ((AsyncResult) obj).userObj, ((Integer) ((AsyncResult) obj).result).intValue());
                    return;
                }
                if (i == 8) {
                    EventSimStateChangedBag eventSimStateChangedBag = (EventSimStateChangedBag) message.obj;
                    if ("READY".equals(eventSimStateChangedBag.mIccStatus) || "LOADED".equals(eventSimStateChangedBag.mIccStatus)) {
                        if (PhoneGlobals.this.mPUKEntryActivity != null) {
                            Log.i("PhoneGlobals", "Dismiss puk entry activity");
                            PhoneGlobals.this.mPUKEntryActivity.finish();
                            PhoneGlobals.this.mPUKEntryActivity = null;
                        }
                        if (PhoneGlobals.this.mPUKEntryProgressDialog != null) {
                            Log.i("PhoneGlobals", "Dismiss puk progress dialog");
                            PhoneGlobals.this.mPUKEntryProgressDialog.dismiss();
                            PhoneGlobals.this.mPUKEntryProgressDialog = null;
                        }
                        Log.i("PhoneGlobals", "Dismissing depersonal panel");
                        IccNetworkDepersonalizationPanel.dialogDismiss(eventSimStateChangedBag.mPhoneId);
                        return;
                    }
                    return;
                }
                if (i == 52) {
                    PhoneGlobals.this.onMMIComplete((AsyncResult) message.obj);
                    return;
                }
                if (i != 53) {
                    switch (i) {
                        case 10:
                            PhoneGlobals.this.notificationMgr.showDataRoamingNotification(message.arg1, false);
                            break;
                        case 11:
                            PhoneGlobals.this.notificationMgr.showDataRoamingNotification(message.arg1, true);
                            break;
                        case 12:
                            PhoneGlobals.this.notificationMgr.hideDataRoamingNotification();
                            break;
                        default:
                            switch (i) {
                                case 14:
                                    UserManager userManager = (UserManager) PhoneGlobals.sMe.getSystemService("user");
                                    if (userManager != null && userManager.isUserUnlocked()) {
                                        SipUtil.startSipService();
                                        break;
                                    }
                                    break;
                                case 15:
                                case 16:
                                    PhoneGlobals.this.updateDataRoamingStatus();
                                    break;
                                case 17:
                                    PhoneGlobals.this.refreshMwiIndicator(((Integer) message.obj).intValue());
                                    break;
                            }
                            break;
                    }
                    return;
                }
                PhoneUtils.cancelMmiCode(PhoneGlobals.this.mCM.getFgPhone());
            }
        };
        sMe = this;
        this.mSettingsObserver = new SettingsObserver(context, this.mHandler);
    }

    public void onCreate() {
        ContentResolver contentResolver = getContentResolver();
        sVoiceCapable = ((TelephonyManager) getSystemService("phone")).isVoiceCapable();
        if (this.mCM == null) {
            AnomalyReporter.initialize(this);
            TelephonyComponentFactory.getInstance().injectTheComponentFactory(getResources().getXml(R.xml.telephony_injection));
            PhoneFactory.makeDefaultPhones(this);
            if (getPackageManager().hasSystemFeature("android.hardware.telephony.ims")) {
                ImsResolver imsResolver = new ImsResolver(this, getResources().getString(R.string.config_ims_mmtel_package), getResources().getString(R.string.config_ims_rcs_package), PhoneFactory.getPhones().length);
                this.mImsResolver = imsResolver;
                imsResolver.initialize();
            }
            startService(new Intent(this, (Class<?>) TelephonyDebugService.class));
            this.mCM = CallManager.getInstance();
            this.notificationMgr = NotificationMgr.init(this);
            this.mHandler.sendEmptyMessage(14);
            CdmaPhoneCallState cdmaPhoneCallState = new CdmaPhoneCallState();
            this.cdmaPhoneCallState = cdmaPhoneCallState;
            cdmaPhoneCallState.CdmaPhoneCallStateInit();
            PowerManager powerManager = (PowerManager) getSystemService("power");
            this.mPowerManager = powerManager;
            powerManager.newWakeLock(26, "PhoneGlobals");
            this.mPowerManager.newWakeLock(536870913, "PhoneGlobals");
            this.mKeyguardManager = (KeyguardManager) getSystemService("keyguard");
            CallerInfoCache.init(this);
            this.phoneMgr = PhoneInterfaceManager.init(this);
            this.imsRcsController = ImsRcsController.init(this);
            if (getPackageManager().hasSystemFeature("android.hardware.telephony.ims")) {
                TelephonyRcsService telephonyRcsService = new TelephonyRcsService(this, PhoneFactory.getPhones().length);
                this.mTelephonyRcsService = telephonyRcsService;
                telephonyRcsService.initialize();
                this.imsRcsController.setRcsService(this.mTelephonyRcsService);
            }
            this.configLoader = CarrierConfigLoader.init(this);
            this.notifier = CallNotifier.init(this);
            PhoneUtils.registerIccStatus(this.mHandler, 3);
            this.mCM.registerForMmiComplete(this.mHandler, 52, (Object) null);
            handleAirplaneModeChange(this, Settings.Global.getInt(getContentResolver(), "airplane_mode_on", 0));
            IntentFilter intentFilter = new IntentFilter("android.intent.action.AIRPLANE_MODE");
            intentFilter.addAction("android.intent.action.SIM_STATE_CHANGED");
            intentFilter.addAction("android.intent.action.RADIO_TECHNOLOGY");
            intentFilter.addAction("android.intent.action.SERVICE_STATE");
            intentFilter.addAction("android.intent.action.EMERGENCY_CALLBACK_MODE_CHANGED");
            intentFilter.addAction("android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED");
            intentFilter.addAction("android.telephony.action.CARRIER_CONFIG_CHANGED");
            registerReceiver(this.mReceiver, intentFilter);
            IntentFilter intentFilter2 = new IntentFilter("android.intent.action.BOOT_COMPLETED");
            intentFilter2.addAction("android.net.sip.action.SIP_SERVICE_UP");
            intentFilter2.addAction("android.net.sip.action.SIP_CALL_OPTION_CHANGED");
            intentFilter2.addAction("android.net.sip.action.SIP_REMOVE_PROFILE");
            registerReceiver(this.mSipReceiver, intentFilter2);
            this.mCarrierVvmPackageInstalledReceiver.register(this);
            PreferenceManager.setDefaultValues(this, R.xml.call_feature_setting, false);
        }
        contentResolver.getType(Uri.parse("content://icc/adn"));
        if (getResources().getBoolean(R.bool.hac_enabled)) {
            int i = Settings.System.getInt(getContentResolver(), "hearing_aid", 0);
            AudioManager audioManager = (AudioManager) getSystemService("audio");
            StringBuilder sb = new StringBuilder();
            sb.append("HACSetting=");
            sb.append(i == 1 ? "ON" : "OFF");
            audioManager.setParameters(sb.toString());
        }
    }

    public static PhoneGlobals getInstance() {
        PhoneGlobals phoneGlobals = sMe;
        if (phoneGlobals != null) {
            return phoneGlobals;
        }
        throw new IllegalStateException("No PhoneGlobals here!");
    }

    public static Phone getPhone() {
        return PhoneFactory.getDefaultPhone();
    }

    public static Phone getPhone(int i) {
        return PhoneFactory.getPhone(SubscriptionManager.getPhoneId(i));
    }

    public ImsResolver getImsResolver() {
        return this.mImsResolver;
    }

    CallManager getCallManager() {
        return this.mCM;
    }

    public PersistableBundle getCarrierConfig() {
        return getCarrierConfigForSubId(SubscriptionManager.getDefaultSubscriptionId());
    }

    public PersistableBundle getCarrierConfigForSubId(int i) {
        return this.configLoader.getConfigForSubIdWithFeature(i, getOpPackageName(), getAttributionTag());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerSettingsObserver() {
        int i;
        this.mSettingsObserver.unobserve();
        String str = "data_roaming";
        String str2 = "mobile_data";
        if (TelephonyManager.getDefault().getSimCount() > 1 && (i = this.mDefaultDataSubId) != -1) {
            str = "data_roaming" + i;
            str2 = "mobile_data" + i;
        }
        this.mSettingsObserver.observe(Settings.Global.getUriFor(str), 15);
        this.mSettingsObserver.observe(Settings.Global.getUriFor(str2), 16);
    }

    void setPukEntryActivity(Activity activity) {
        StringBuilder sb = new StringBuilder();
        sb.append("setPukEntryActivity - set to ");
        sb.append(activity == null ? "null" : "activity");
        Log.i("PhoneGlobals", sb.toString());
        this.mPUKEntryActivity = activity;
    }

    Activity getPUKEntryActivity() {
        return this.mPUKEntryActivity;
    }

    void setPukEntryProgressDialog(ProgressDialog progressDialog) {
        StringBuilder sb = new StringBuilder();
        sb.append("setPukEntryProgressDialog - set to ");
        sb.append(progressDialog == null ? "null" : "activity");
        Log.i("PhoneGlobals", sb.toString());
        this.mPUKEntryProgressDialog = progressDialog;
    }

    KeyguardManager getKeyguardManager() {
        return this.mKeyguardManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMMIComplete(AsyncResult asyncResult) {
        MmiCode mmiCode = (MmiCode) asyncResult.result;
        PhoneUtils.displayMMIComplete(mmiCode.getPhone(), getInstance(), mmiCode, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initForNewRadioTechnology() {
        if (DBG) {
            Log.d("PhoneGlobals", "initForNewRadioTechnology...");
        }
        this.notifier.updateCallNotifierRegistrationsAfterRadioTechnologyChange();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAirplaneModeChange(Context context, int i) {
        int i2 = Settings.Global.getInt(context.getContentResolver(), "cell_on", 1);
        boolean z = i == 1;
        if (i2 == 1) {
            maybeTurnCellOff(context, z);
        } else if (i2 == 2) {
            maybeTurnCellOn(context, z);
        }
        for (Phone phone : PhoneFactory.getPhones()) {
            phone.getServiceStateTracker().onAirplaneModeChanged(z);
        }
    }

    private boolean isCellOffInAirplaneMode(Context context) {
        String string = Settings.Global.getString(context.getContentResolver(), "airplane_mode_radios");
        return string == null || string.contains("cell");
    }

    private void setRadioPowerOff(Context context) {
        Log.i("PhoneGlobals", "Turning radio off - airplane");
        Settings.Global.putInt(context.getContentResolver(), "cell_on", 2);
        TelephonyProperties.airplane_mode_on(Boolean.TRUE);
        Settings.Global.putInt(getContentResolver(), "enable_cellular_on_boot", 0);
        PhoneUtils.setRadioPower(false);
    }

    private void setRadioPowerOn(Context context) {
        Log.i("PhoneGlobals", "Turning radio on - airplane");
        Settings.Global.putInt(context.getContentResolver(), "cell_on", 1);
        Settings.Global.putInt(getContentResolver(), "enable_cellular_on_boot", 1);
        TelephonyProperties.airplane_mode_on(Boolean.FALSE);
        PhoneUtils.setRadioPower(true);
    }

    private void maybeTurnCellOff(Context context, boolean z) {
        if (z) {
            TelecomManager telecomManager = (TelecomManager) context.getSystemService("telecom");
            if (telecomManager != null && telecomManager.isInEmergencyCall()) {
                ((ConnectivityManager) context.getSystemService("connectivity")).setAirplaneMode(false);
                Toast.makeText(this, R.string.radio_off_during_emergency_call, 1).show();
                Log.i("PhoneGlobals", "Ignoring airplane mode: emergency call. Turning airplane off");
            } else if (isCellOffInAirplaneMode(context)) {
                setRadioPowerOff(context);
            } else {
                Log.i("PhoneGlobals", "Ignoring airplane mode: settings prevent cell radio power off");
            }
        }
    }

    private void maybeTurnCellOn(Context context, boolean z) {
        if (z) {
            return;
        }
        setRadioPowerOn(context);
    }

    private class PhoneAppBroadcastReceiver extends BroadcastReceiver {
        private PhoneAppBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.intent.action.AIRPLANE_MODE")) {
                int i = Settings.Global.getInt(PhoneGlobals.this.getContentResolver(), "airplane_mode_on", 0);
                if (i != 0) {
                    i = 1;
                }
                PhoneGlobals.this.handleAirplaneModeChange(context, i);
                return;
            }
            if (action.equals("android.intent.action.SIM_STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("phone", -1);
                if (SubscriptionManager.isValidPhoneId(intExtra)) {
                    PhoneUtils.unregisterIccStatus(PhoneGlobals.this.mHandler, intExtra);
                    PhoneUtils.registerIccStatus(PhoneGlobals.this.mHandler, 3, intExtra);
                }
                String stringExtra = intent.getStringExtra("ss");
                Handler handler = PhoneGlobals.this.mHandler;
                handler.sendMessage(handler.obtainMessage(8, new EventSimStateChangedBag(intExtra, stringExtra)));
                return;
            }
            if (action.equals("android.intent.action.RADIO_TECHNOLOGY")) {
                Log.d("PhoneGlobals", "Radio technology switched. Now " + intent.getStringExtra("phoneName") + " is active.");
                PhoneGlobals.this.initForNewRadioTechnology();
                return;
            }
            if (action.equals("android.intent.action.SERVICE_STATE")) {
                PhoneGlobals.this.handleServiceStateChanged(intent);
                return;
            }
            if (action.equals("android.intent.action.EMERGENCY_CALLBACK_MODE_CHANGED")) {
                int intExtra2 = intent.getIntExtra("phone", 0);
                PhoneGlobals.this.phoneInEcm = PhoneFactory.getPhone(intExtra2);
                Log.d("PhoneGlobals", "Emergency Callback Mode. phoneId:" + intExtra2);
                if (PhoneGlobals.this.phoneInEcm != null) {
                    if (TelephonyCapabilities.supportsEcm(PhoneGlobals.this.phoneInEcm)) {
                        Log.d("PhoneGlobals", "Emergency Callback Mode arrived in PhoneApp.");
                        if (!intent.getBooleanExtra("android.telephony.extra.PHONE_IN_ECM_STATE", false)) {
                            PhoneGlobals.this.phoneInEcm = null;
                            return;
                        } else {
                            context.startService(new Intent(context, (Class<?>) EmergencyCallbackModeService.class));
                            return;
                        }
                    }
                    Log.e("PhoneGlobals", "Got ACTION_EMERGENCY_CALLBACK_MODE_CHANGED, but ECM isn't supported for phone: " + PhoneGlobals.this.phoneInEcm.getPhoneName());
                    PhoneGlobals.this.phoneInEcm = null;
                    return;
                }
                Log.w("PhoneGlobals", "phoneInEcm is null.");
                return;
            }
            if (action.equals("android.telephony.action.CARRIER_CONFIG_CHANGED")) {
                PhoneGlobals.this.updateDataRoamingStatus();
                PhoneGlobals.this.updateLimitedSimFunctionForDualSim();
                int intExtra3 = intent.getIntExtra("android.telephony.extra.SUBSCRIPTION_INDEX", -1);
                if (SubscriptionManager.isValidSubscriptionId(intExtra3)) {
                    Handler handler2 = PhoneGlobals.this.mHandler;
                    handler2.sendMessage(handler2.obtainMessage(17, new Integer(intExtra3)));
                    return;
                }
                return;
            }
            if (action.equals("android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED")) {
                PhoneGlobals.this.mDefaultDataSubId = SubscriptionManager.getDefaultDataSubscriptionId();
                PhoneGlobals.this.registerSettingsObserver();
                if (PhoneGlobals.getPhone(PhoneGlobals.this.mDefaultDataSubId) != null) {
                    PhoneGlobals.this.updateDataRoamingStatus();
                }
            }
        }
    }

    private class SipReceiver extends BroadcastReceiver {
        private SipReceiver(PhoneGlobals phoneGlobals) {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            SipAccountRegistry sipAccountRegistry = SipAccountRegistry.getInstance();
            if (action.equals("android.intent.action.BOOT_COMPLETED")) {
                SipUtil.startSipService();
                return;
            }
            if (action.equals("android.net.sip.action.SIP_SERVICE_UP") || action.equals("android.net.sip.action.SIP_CALL_OPTION_CHANGED")) {
                sipAccountRegistry.setup(context);
                return;
            }
            if (action.equals("android.net.sip.action.SIP_REMOVE_PROFILE")) {
                if (PhoneGlobals.DBG) {
                    Log.d("PhoneGlobals", "SIP_REMOVE_PHONE " + intent.getStringExtra("android:localSipUri"));
                }
                sipAccountRegistry.removeSipProfile(intent.getStringExtra("android:localSipUri"));
                return;
            }
            if (PhoneGlobals.DBG) {
                Log.d("PhoneGlobals", "onReceive, action not processed: " + action);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleServiceStateChanged(Intent intent) {
        ServiceState serviceStateNewFromBundle;
        Bundle extras = intent.getExtras();
        if (extras == null || (serviceStateNewFromBundle = ServiceState.newFromBundle(extras)) == null) {
            return;
        }
        int state = serviceStateNewFromBundle.getState();
        int intExtra = intent.getIntExtra("subscription", -1);
        this.notificationMgr.updateNetworkSelection(state, intExtra);
        if (intExtra == this.mDefaultDataSubId) {
            updateDataRoamingStatus();
        }
    }

    private boolean shouldShowDataConnectedRoaming(int i) {
        return getCarrierConfigForSubId(i).getBoolean("show_data_connected_roaming_notification");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDataRoamingStatus() {
        Phone phone = getPhone(this.mDefaultDataSubId);
        if (phone == null) {
            Log.w("PhoneGlobals", "Can't get phone with sub id = " + this.mDefaultDataSubId);
            return;
        }
        DataConnectionReasons dataConnectionReasons = new DataConnectionReasons();
        boolean zIsDataAllowed = phone.isDataAllowed(17, dataConnectionReasons);
        this.mDataRoamingNotifLog.log("dataAllowed=" + zIsDataAllowed + ", reasons=" + dataConnectionReasons);
        if (!zIsDataAllowed && dataConnectionReasons.containsOnly(DataConnectionReasons.DataDisallowedReasonType.ROAMING_DISABLED)) {
            if (this.mPrevRoamingNotification == 2) {
                return;
            }
            this.mPrevRoamingNotification = 2;
            Log.d("PhoneGlobals", "Show roaming disconnected notification");
            this.mDataRoamingNotifLog.log("Show roaming off.");
            Message messageObtainMessage = this.mHandler.obtainMessage(10);
            messageObtainMessage.arg1 = this.mDefaultDataSubId;
            messageObtainMessage.sendToTarget();
            return;
        }
        if (zIsDataAllowed && dataIsNowRoaming(this.mDefaultDataSubId) && shouldShowDataConnectedRoaming(this.mDefaultDataSubId)) {
            if (this.mPrevRoamingNotification == 1) {
                return;
            }
            this.mPrevRoamingNotification = 1;
            Log.d("PhoneGlobals", "Show roaming connected notification");
            this.mDataRoamingNotifLog.log("Show roaming on.");
            Message messageObtainMessage2 = this.mHandler.obtainMessage(11);
            messageObtainMessage2.arg1 = this.mDefaultDataSubId;
            messageObtainMessage2.sendToTarget();
            return;
        }
        if (this.mPrevRoamingNotification != 0) {
            this.mPrevRoamingNotification = 0;
            Log.d("PhoneGlobals", "Dismiss roaming notification");
            this.mDataRoamingNotifLog.log("Hide. data allowed=" + zIsDataAllowed + ", reasons=" + dataConnectionReasons);
            this.mHandler.sendEmptyMessage(12);
        }
    }

    private boolean dataIsNowRoaming(int i) {
        return getPhone(i).getServiceState().getDataRoaming();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLimitedSimFunctionForDualSim() {
        if (DBG) {
            Log.d("PhoneGlobals", "updateLimitedSimFunctionForDualSim");
        }
        List<SubscriptionInfo> activeSubscriptionInfoList = ((SubscriptionManager) getSystemService("telephony_subscription_service")).getActiveSubscriptionInfoList(false);
        if (activeSubscriptionInfoList != null && activeSubscriptionInfoList.size() > 1) {
            CarrierConfigManager carrierConfigManager = (CarrierConfigManager) getSystemService("carrier_config");
            for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
                PersistableBundle configForSubId = carrierConfigManager.getConfigForSubId(subscriptionInfo.getSubscriptionId());
                if (configForSubId != null) {
                    if (configForSubId.getBoolean("limited_sim_function_notification_for_dsds_bool")) {
                        this.notificationMgr.showLimitedSimFunctionWarningNotification(subscriptionInfo.getSubscriptionId(), subscriptionInfo.getDisplayName().toString());
                    } else {
                        this.notificationMgr.dismissLimitedSimFunctionWarningNotification(subscriptionInfo.getSubscriptionId());
                    }
                }
            }
        } else {
            this.notificationMgr.dismissLimitedSimFunctionWarningNotification(-1);
        }
        this.notificationMgr.dismissLimitedSimFunctionWarningNotificationForInactiveSubs();
    }

    public Phone getPhoneInEcm() {
        return this.phoneInEcm;
    }

    public void refreshMwiIndicator(int i) {
        this.notificationMgr.refreshMwi(i);
    }

    public void onNetworkSelectionChanged(int i) {
        Phone phone = getPhone(i);
        if (phone != null) {
            this.notificationMgr.updateNetworkSelection(phone.getServiceState().getState(), i);
            return;
        }
        Log.w("PhoneGlobals", "onNetworkSelectionChanged on null phone, subId: " + i);
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter, "  ");
        indentingPrintWriter.println("------- PhoneGlobals -------");
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.println("mPrevRoamingNotification=" + this.mPrevRoamingNotification);
        indentingPrintWriter.println("mDefaultDataSubId=" + this.mDefaultDataSubId);
        indentingPrintWriter.println("mDataRoamingNotifLog:");
        indentingPrintWriter.println("isSmsCapable=" + TelephonyManager.from(this).isSmsCapable());
        indentingPrintWriter.increaseIndent();
        this.mDataRoamingNotifLog.dump(fileDescriptor, indentingPrintWriter, strArr);
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("ImsResolver:");
        indentingPrintWriter.increaseIndent();
        try {
            if (this.mImsResolver != null) {
                this.mImsResolver.dump(fileDescriptor, indentingPrintWriter, strArr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("RcsService:");
        try {
            if (this.mTelephonyRcsService != null) {
                this.mTelephonyRcsService.dump(fileDescriptor, indentingPrintWriter, strArr);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("------- End PhoneGlobals -------");
    }
}
