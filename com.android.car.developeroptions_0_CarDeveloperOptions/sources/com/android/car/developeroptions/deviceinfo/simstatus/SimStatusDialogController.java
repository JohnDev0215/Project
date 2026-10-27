package com.android.car.developeroptions.deviceinfo.simstatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.SmsCbMessage;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.euicc.EuiccManager;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import com.android.car.developeroptions.R;
import com.android.settingslib.DeviceInfoUtils;
import com.android.settingslib.Utils;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SimStatusDialogController implements LifecycleObserver, OnResume, OnPause {
    static final int CELLULAR_NETWORK_STATE = 2131362098;
    static final int CELL_DATA_NETWORK_TYPE_VALUE_ID = 2131362095;
    static final int CELL_VOICE_NETWORK_TYPE_VALUE_ID = 2131363000;
    static final int EID_INFO_VALUE_ID = 2131362216;
    static final int ICCID_INFO_LABEL_ID = 2131362307;
    static final int ICCID_INFO_VALUE_ID = 2131362308;
    static final int IMS_REGISTRATION_STATE_LABEL_ID = 2131362330;
    static final int IMS_REGISTRATION_STATE_VALUE_ID = 2131362331;
    static final int NETWORK_PROVIDER_VALUE_ID = 2131362546;
    static final int OPERATOR_INFO_LABEL_ID = 2131362384;
    static final int OPERATOR_INFO_VALUE_ID = 2131362385;
    static final int PHONE_NUMBER_VALUE_ID = 2131362534;
    static final int ROAMING_INFO_VALUE_ID = 2131362680;
    static final int SERVICE_STATE_VALUE_ID = 2131362736;
    static final int SIGNAL_STRENGTH_LABEL_ID = 2131362759;
    static final int SIGNAL_STRENGTH_VALUE_ID = 2131362760;
    private final BroadcastReceiver mAreaInfoReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.deviceinfo.simstatus.SimStatusDialogController.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Bundle extras;
            SmsCbMessage smsCbMessage;
            if (!TextUtils.equals(intent.getAction(), "com.android.cellbroadcastreceiver.CB_AREA_INFO_RECEIVED") || (extras = intent.getExtras()) == null || (smsCbMessage = (SmsCbMessage) extras.get("message")) == null) {
                return;
            }
            int[] subscriptionIds = SimStatusDialogController.this.mSubscriptionManager.getSubscriptionIds(smsCbMessage.getSlotIndex());
            if (SimStatusDialogController.this.mSubscriptionInfo.getSubscriptionId() == ((subscriptionIds == null || subscriptionIds.length == 0) ? -1 : subscriptionIds[0])) {
                SimStatusDialogController.this.mDialog.setText(R.id.latest_area_info_value, smsCbMessage.getMessageBody());
            }
        }
    };
    private final CarrierConfigManager mCarrierConfigManager;
    private final Context mContext;
    private final SimStatusDialogFragment mDialog;
    private final EuiccManager mEuiccManager;
    private PhoneStateListener mPhoneStateListener;
    private final Resources mRes;
    private boolean mShowLatestAreaInfo;
    private final SubscriptionInfo mSubscriptionInfo;
    private final SubscriptionManager mSubscriptionManager;
    private final TelephonyManager mTelephonyManager;

    public SimStatusDialogController(SimStatusDialogFragment simStatusDialogFragment, Lifecycle lifecycle, int i) {
        this.mDialog = simStatusDialogFragment;
        this.mContext = simStatusDialogFragment.getContext();
        this.mSubscriptionInfo = getPhoneSubscriptionInfo(i);
        this.mTelephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        this.mCarrierConfigManager = (CarrierConfigManager) this.mContext.getSystemService("carrier_config");
        this.mEuiccManager = (EuiccManager) this.mContext.getSystemService("euicc");
        this.mSubscriptionManager = (SubscriptionManager) this.mContext.getSystemService("telephony_subscription_service");
        this.mRes = this.mContext.getResources();
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    public void initialize() {
        updateEid();
        if (this.mSubscriptionInfo == null) {
            return;
        }
        this.mPhoneStateListener = getPhoneStateListener();
        ServiceState currentServiceState = getCurrentServiceState();
        updateNetworkProvider(currentServiceState);
        updatePhoneNumber();
        updateLatestAreaInfo();
        updateServiceState(currentServiceState);
        updateSignalStrength(getSignalStrength());
        updateNetworkType();
        updateRoamingStatus(currentServiceState);
        updateIccidNumber();
        updateImsRegistrationState();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        SubscriptionInfo subscriptionInfo = this.mSubscriptionInfo;
        if (subscriptionInfo == null) {
            return;
        }
        this.mTelephonyManager.createForSubscriptionId(subscriptionInfo.getSubscriptionId()).listen(this.mPhoneStateListener, 321);
        if (this.mShowLatestAreaInfo) {
            this.mContext.registerReceiver(this.mAreaInfoReceiver, new IntentFilter("com.android.cellbroadcastreceiver.CB_AREA_INFO_RECEIVED"), "android.permission.RECEIVE_EMERGENCY_BROADCAST", null);
            Intent intent = new Intent("com.android.cellbroadcastreceiver.GET_LATEST_CB_AREA_INFO");
            intent.setPackage("com.android.cellbroadcastreceiver");
            this.mContext.sendBroadcastAsUser(intent, UserHandle.ALL, "android.permission.RECEIVE_EMERGENCY_BROADCAST");
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPause
    public void onPause() {
        SubscriptionInfo subscriptionInfo = this.mSubscriptionInfo;
        if (subscriptionInfo == null) {
            return;
        }
        this.mTelephonyManager.createForSubscriptionId(subscriptionInfo.getSubscriptionId()).listen(this.mPhoneStateListener, 0);
        if (this.mShowLatestAreaInfo) {
            this.mContext.unregisterReceiver(this.mAreaInfoReceiver);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNetworkProvider(ServiceState serviceState) {
        this.mDialog.setText(R.id.operator_name_value, serviceState.getOperatorAlphaLong());
    }

    private void updatePhoneNumber() {
        this.mDialog.setText(R.id.number_value, BidiFormatter.getInstance().unicodeWrap(getPhoneNumber(), TextDirectionHeuristics.LTR));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDataState(int i) {
        String string;
        if (i == 0) {
            string = this.mRes.getString(R.string.radioInfo_data_disconnected);
        } else if (i == 1) {
            string = this.mRes.getString(R.string.radioInfo_data_connecting);
        } else if (i == 2) {
            string = this.mRes.getString(R.string.radioInfo_data_connected);
        } else if (i == 3) {
            string = this.mRes.getString(R.string.radioInfo_data_suspended);
        } else {
            string = this.mRes.getString(R.string.radioInfo_unknown);
        }
        this.mDialog.setText(R.id.data_state_value, string);
    }

    private void updateLatestAreaInfo() {
        boolean z = Resources.getSystem().getBoolean(android.R.bool.config_cecQuerySadMpeg2Disabled_allowed) && this.mTelephonyManager.getPhoneType() != 2;
        this.mShowLatestAreaInfo = z;
        if (z) {
            return;
        }
        this.mDialog.removeSettingFromScreen(R.id.latest_area_info_label);
        this.mDialog.removeSettingFromScreen(R.id.latest_area_info_value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateServiceState(ServiceState serviceState) {
        String string;
        int combinedServiceState = Utils.getCombinedServiceState(serviceState);
        if (!Utils.isInService(serviceState)) {
            resetSignalStrength();
        }
        if (combinedServiceState == 0) {
            string = this.mRes.getString(R.string.radioInfo_service_in);
        } else if (combinedServiceState == 1 || combinedServiceState == 2) {
            string = this.mRes.getString(R.string.radioInfo_service_out);
        } else if (combinedServiceState == 3) {
            string = this.mRes.getString(R.string.radioInfo_service_off);
        } else {
            string = this.mRes.getString(R.string.radioInfo_unknown);
        }
        this.mDialog.setText(R.id.service_state_value, string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSignalStrength(SignalStrength signalStrength) {
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(this.mSubscriptionInfo.getSubscriptionId());
        if (!(configForSubId != null ? configForSubId.getBoolean("show_signal_strength_in_sim_status_bool") : true)) {
            this.mDialog.removeSettingFromScreen(R.id.signal_strength_label);
            this.mDialog.removeSettingFromScreen(R.id.signal_strength_value);
            return;
        }
        ServiceState currentServiceState = getCurrentServiceState();
        if (currentServiceState == null || !Utils.isInService(currentServiceState)) {
            return;
        }
        int dbm = getDbm(signalStrength);
        int asuLevel = getAsuLevel(signalStrength);
        if (dbm == -1) {
            dbm = 0;
        }
        if (asuLevel == -1) {
            asuLevel = 0;
        }
        this.mDialog.setText(R.id.signal_strength_value, this.mRes.getString(R.string.sim_signal_strength, Integer.valueOf(dbm), Integer.valueOf(asuLevel)));
    }

    private void resetSignalStrength() {
        this.mDialog.setText(R.id.signal_strength_value, "0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNetworkType() {
        int subscriptionId = this.mSubscriptionInfo.getSubscriptionId();
        int dataNetworkType = this.mTelephonyManager.getDataNetworkType(subscriptionId);
        int voiceNetworkType = this.mTelephonyManager.getVoiceNetworkType(subscriptionId);
        String networkTypeName = dataNetworkType != 0 ? TelephonyManager.getNetworkTypeName(dataNetworkType) : null;
        String networkTypeName2 = voiceNetworkType != 0 ? TelephonyManager.getNetworkTypeName(voiceNetworkType) : null;
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(subscriptionId);
        if (configForSubId != null ? configForSubId.getBoolean("show_4g_for_lte_data_icon_bool") : false) {
            if ("LTE".equals(networkTypeName)) {
                networkTypeName = "4G";
            }
            if ("LTE".equals(networkTypeName2)) {
                networkTypeName2 = "4G";
            }
        }
        this.mDialog.setText(R.id.voice_network_type_value, networkTypeName2);
        this.mDialog.setText(R.id.data_network_type_value, networkTypeName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRoamingStatus(ServiceState serviceState) {
        if (serviceState.getRoaming()) {
            this.mDialog.setText(R.id.roaming_state_value, this.mRes.getString(R.string.radioInfo_roaming_in));
        } else {
            this.mDialog.setText(R.id.roaming_state_value, this.mRes.getString(R.string.radioInfo_roaming_not));
        }
    }

    private void updateIccidNumber() {
        int subscriptionId = this.mSubscriptionInfo.getSubscriptionId();
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(subscriptionId);
        if (!(configForSubId != null ? configForSubId.getBoolean("show_iccid_in_sim_status_bool") : false)) {
            this.mDialog.removeSettingFromScreen(R.id.icc_id_label);
            this.mDialog.removeSettingFromScreen(R.id.icc_id_value);
        } else {
            this.mDialog.setText(R.id.icc_id_value, getSimSerialNumber(subscriptionId));
        }
    }

    private void updateEid() {
        if (this.mEuiccManager.isEnabled()) {
            this.mDialog.setText(R.id.esim_id_value, this.mEuiccManager.getEid());
        } else {
            this.mDialog.removeSettingFromScreen(R.id.esim_id_value);
        }
    }

    private void updateImsRegistrationState() {
        int subscriptionId = this.mSubscriptionInfo.getSubscriptionId();
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(subscriptionId);
        if (configForSubId == null ? false : configForSubId.getBoolean("show_ims_registration_status_bool")) {
            this.mDialog.setText(R.id.ims_reg_state_value, this.mRes.getString(this.mTelephonyManager.isImsRegistered(subscriptionId) ? R.string.ims_reg_status_registered : R.string.ims_reg_status_not_registered));
        } else {
            this.mDialog.removeSettingFromScreen(R.id.ims_reg_state_label);
            this.mDialog.removeSettingFromScreen(R.id.ims_reg_state_value);
        }
    }

    private SubscriptionInfo getPhoneSubscriptionInfo(int i) {
        List activeSubscriptionInfoList = SubscriptionManager.from(this.mContext).getActiveSubscriptionInfoList(true);
        if (activeSubscriptionInfoList == null || activeSubscriptionInfoList.size() <= i) {
            return null;
        }
        return (SubscriptionInfo) activeSubscriptionInfoList.get(i);
    }

    ServiceState getCurrentServiceState() {
        return this.mTelephonyManager.getServiceStateForSubscriber(this.mSubscriptionInfo.getSubscriptionId());
    }

    int getDbm(SignalStrength signalStrength) {
        return signalStrength.getDbm();
    }

    int getAsuLevel(SignalStrength signalStrength) {
        return signalStrength.getAsuLevel();
    }

    PhoneStateListener getPhoneStateListener() {
        return new PhoneStateListener() { // from class: com.android.car.developeroptions.deviceinfo.simstatus.SimStatusDialogController.2
            @Override // android.telephony.PhoneStateListener
            public void onDataConnectionStateChanged(int i) {
                SimStatusDialogController.this.updateDataState(i);
                SimStatusDialogController.this.updateNetworkType();
            }

            @Override // android.telephony.PhoneStateListener
            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                SimStatusDialogController.this.updateSignalStrength(signalStrength);
            }

            @Override // android.telephony.PhoneStateListener
            public void onServiceStateChanged(ServiceState serviceState) {
                SimStatusDialogController.this.updateNetworkProvider(serviceState);
                SimStatusDialogController.this.updateServiceState(serviceState);
                SimStatusDialogController.this.updateRoamingStatus(serviceState);
            }
        };
    }

    String getPhoneNumber() {
        return DeviceInfoUtils.getFormattedPhoneNumber(this.mContext, this.mSubscriptionInfo);
    }

    SignalStrength getSignalStrength() {
        return this.mTelephonyManager.getSignalStrength();
    }

    String getSimSerialNumber(int i) {
        return this.mTelephonyManager.getSimSerialNumber(i);
    }
}
