package com.android.phone.settings;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneStateListener;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.ims.ImsManager;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;
import com.android.internal.telephony.SubscriptionController;
import com.android.phone.PhoneGlobals;
import com.android.phone.R;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class AccessibilitySettingsFragment extends PreferenceFragment {
    private static final String LOG_TAG = AccessibilitySettingsFragment.class.getSimpleName();
    private AudioManager mAudioManager;
    private SwitchPreference mButtonHac;
    private SwitchPreference mButtonRtt;
    private TtyModeListPreference mButtonTty;
    private Context mContext;
    private final PhoneStateListener mPhoneStateListener = new PhoneStateListener() { // from class: com.android.phone.settings.AccessibilitySettingsFragment.1
        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            Preference preferenceFindPreference = AccessibilitySettingsFragment.this.getPreferenceScreen().findPreference("button_tty_mode_key");
            if (preferenceFindPreference != null) {
                preferenceFindPreference.setEnabled((AccessibilitySettingsFragment.this.isVolteTtySupportedInAnySlot() && !AccessibilitySettingsFragment.this.isVideoCallOrConferenceInProgress()) || ((TelephonyManager) AccessibilitySettingsFragment.this.mContext.getSystemService("phone")).getCallState() == 0);
            }
        }
    };

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context applicationContext = getActivity().getApplicationContext();
        this.mContext = applicationContext;
        this.mAudioManager = (AudioManager) applicationContext.getSystemService("audio");
        addPreferencesFromResource(R.xml.accessibility_settings);
        this.mButtonTty = (TtyModeListPreference) findPreference(getResources().getString(R.string.tty_mode_key));
        this.mButtonHac = (SwitchPreference) findPreference("button_hac_key");
        this.mButtonRtt = (SwitchPreference) findPreference("button_rtt_key");
        if (PhoneGlobals.getInstance().phoneMgr.isTtyModeSupported() && isTtySupportedByCarrier()) {
            this.mButtonTty.init();
        } else {
            getPreferenceScreen().removePreference(this.mButtonTty);
            this.mButtonTty = null;
        }
        if (PhoneGlobals.getInstance().phoneMgr.isHearingAidCompatibilitySupported()) {
            this.mButtonHac.setChecked(Settings.System.getInt(this.mContext.getContentResolver(), "hearing_aid", 0) == 1);
        } else {
            getPreferenceScreen().removePreference(this.mButtonHac);
            this.mButtonHac = null;
        }
        if (shouldShowRttSetting()) {
            if ((!((TelephonyManager) this.mContext.getSystemService("phone")).isNetworkRoaming(SubscriptionManager.getDefaultVoiceSubscriptionId()) || isOnWfc() || isCarrierAllowRttWhenRoaming(SubscriptionManager.getDefaultVoiceSubscriptionId())) ? false : true) {
                this.mButtonRtt.setSummary(TextUtils.concat(getText(R.string.rtt_mode_summary), "\n", getText(R.string.no_rtt_when_roaming)));
            }
            this.mButtonRtt.setChecked(Settings.Secure.getInt(this.mContext.getContentResolver(), "rtt_calling_mode", 0) != 0);
        } else {
            getPreferenceScreen().removePreference(this.mButtonRtt);
            getPreferenceScreen().removePreference(findPreference("button_rtt_more_information_key"));
            this.mButtonRtt = null;
        }
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        ((TelephonyManager) this.mContext.getSystemService("phone")).listen(this.mPhoneStateListener, 32);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        ((TelephonyManager) this.mContext.getSystemService("phone")).listen(this.mPhoneStateListener, 0);
    }

    @Override // android.preference.PreferenceFragment
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == this.mButtonTty) {
            return true;
        }
        SwitchPreference switchPreference = this.mButtonHac;
        if (preference == switchPreference) {
            boolean zIsChecked = switchPreference.isChecked();
            Settings.System.putInt(this.mContext.getContentResolver(), "hearing_aid", zIsChecked ? 1 : 0);
            AudioManager audioManager = this.mAudioManager;
            StringBuilder sb = new StringBuilder();
            sb.append("HACSetting=");
            sb.append(zIsChecked ? "ON" : "OFF");
            audioManager.setParameters(sb.toString());
            return true;
        }
        if (preference != this.mButtonRtt) {
            return false;
        }
        Log.i(LOG_TAG, "RTT setting changed -- now " + this.mButtonRtt.isChecked());
        Settings.Secure.putInt(this.mContext.getContentResolver(), "rtt_calling_mode", this.mButtonRtt.isChecked() ? 1 : 0);
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) this.mContext.getSystemService("carrier_config");
        for (int i : SubscriptionController.getInstance().getActiveSubIdList(true)) {
            if (!carrierConfigManager.getConfigForSubId(i).getBoolean("ignore_rtt_mode_setting_bool", false)) {
                ImsManager.getInstance(getContext(), SubscriptionController.getInstance().getPhoneId(i)).setRttEnabled(this.mButtonRtt.isChecked());
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isVolteTtySupportedInAnySlot() {
        Phone[] phones = PhoneFactory.getPhones();
        if (phones == null) {
            return false;
        }
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) this.mContext.getSystemService("carrier_config");
        for (Phone phone : phones) {
            ImsManager imsManager = ImsManager.getInstance(this.mContext, phone.getPhoneId());
            boolean zIsVolteEnabledByPlatform = imsManager != null ? imsManager.isVolteEnabledByPlatform() : false;
            PersistableBundle configForSubId = carrierConfigManager.getConfigForSubId(phone.getSubId());
            boolean z = configForSubId != null ? configForSubId.getBoolean("carrier_volte_tty_supported_bool") : false;
            if (zIsVolteEnabledByPlatform && z) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isVideoCallOrConferenceInProgress() {
        Phone[] phones = PhoneFactory.getPhones();
        if (phones == null) {
            return false;
        }
        for (Phone phone : phones) {
            if (phone.isImsVideoCallOrConferencePresent()) {
                return true;
            }
        }
        return false;
    }

    private boolean isOnWfc() {
        final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(1);
        ((android.telephony.ims.ImsManager) this.mContext.getSystemService(android.telephony.ims.ImsManager.class)).getImsMmTelManager(SubscriptionManager.getDefaultSubscriptionId()).getRegistrationTransportType(Executors.newSingleThreadExecutor(), new Consumer() { // from class: com.android.phone.settings.-$$Lambda$cfVs37q2lDPsKVvvSX6tEDJf-OA
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                linkedBlockingQueue.offer((Integer) obj);
            }
        });
        try {
            Integer num = (Integer) linkedBlockingQueue.poll(20L, TimeUnit.MILLISECONDS);
            if (num != null) {
                if (num.intValue() == 2) {
                    return true;
                }
            }
            return false;
        } catch (InterruptedException unused) {
            return false;
        }
    }

    private boolean shouldShowRttSetting() {
        for (int i : SubscriptionController.getInstance().getActiveSubIdList(true)) {
            if (PhoneGlobals.getInstance().phoneMgr.isRttSupported(i)) {
                return true;
            }
        }
        return false;
    }

    private boolean isTtySupportedByCarrier() {
        return ((CarrierConfigManager) this.mContext.getSystemService("carrier_config")).getConfig().getBoolean("tty_supported_bool");
    }

    private boolean isCarrierAllowRttWhenRoaming(int i) {
        return PhoneGlobals.getInstance().getCarrierConfigForSubId(i).getBoolean("rtt_supported_while_roaming_bool");
    }
}
