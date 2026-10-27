package com.android.car.developeroptions.network.telephony;

import android.content.Context;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneStateListener;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.network.telephony.Enhanced4gLtePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.ims.ImsManager;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

/* JADX INFO: loaded from: classes.dex */
public class VideoCallingPreferenceController extends TelephonyTogglePreferenceController implements LifecycleObserver, OnStart, OnStop, Enhanced4gLtePreferenceController.On4gLteUpdateListener {
    private CarrierConfigManager mCarrierConfigManager;
    private DataContentObserver mDataContentObserver;
    ImsManager mImsManager;
    private PhoneCallStateListener mPhoneStateListener;
    private Preference mPreference;
    private TelephonyManager mTelephonyManager;

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public VideoCallingPreferenceController(Context context, String str) {
        super(context, str);
        this.mCarrierConfigManager = (CarrierConfigManager) context.getSystemService(CarrierConfigManager.class);
        this.mDataContentObserver = new DataContentObserver(new Handler(Looper.getMainLooper()));
        this.mPhoneStateListener = new PhoneCallStateListener(Looper.getMainLooper());
    }

    @Override // com.android.car.developeroptions.network.telephony.TelephonyTogglePreferenceController, com.android.car.developeroptions.network.telephony.TelephonyAvailabilityCallback
    public int getAvailabilityStatus(int i) {
        return (i != -1 && MobileNetworkUtils.isWifiCallingEnabled(this.mContext, SubscriptionManager.getPhoneId(i)) && isVideoCallEnabled(i)) ? 0 : 2;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mPhoneStateListener.register(this.mSubId);
        this.mDataContentObserver.register(this.mContext, this.mSubId);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mPhoneStateListener.unregister();
        this.mDataContentObserver.unRegister(this.mContext);
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        SwitchPreference switchPreference = (SwitchPreference) preference;
        boolean zIsVideoCallEnabled = isVideoCallEnabled(this.mSubId, this.mImsManager);
        switchPreference.setVisible(zIsVideoCallEnabled);
        if (zIsVideoCallEnabled) {
            boolean z = this.mImsManager.isEnhanced4gLteModeSettingEnabledByUser() && this.mImsManager.isNonTtyOrTtyOnVolteEnabled();
            preference.setEnabled(z && this.mTelephonyManager.getCallState(this.mSubId) == 0);
            switchPreference.setChecked(z && this.mImsManager.isVtEnabledByUser());
        }
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        this.mImsManager.setVtSetting(z);
        return true;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        return this.mImsManager.isVtEnabledByUser();
    }

    public VideoCallingPreferenceController init(int i) {
        this.mSubId = i;
        this.mTelephonyManager = TelephonyManager.from(this.mContext).createForSubscriptionId(this.mSubId);
        int i2 = this.mSubId;
        if (i2 != -1) {
            this.mImsManager = ImsManager.getInstance(this.mContext, SubscriptionManager.getPhoneId(i2));
        }
        return this;
    }

    private boolean isVideoCallEnabled(int i) {
        return isVideoCallEnabled(i, i != -1 ? ImsManager.getInstance(this.mContext, SubscriptionManager.getPhoneId(i)) : null);
    }

    boolean isVideoCallEnabled(int i, ImsManager imsManager) {
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(i);
        return configForSubId != null && imsManager != null && imsManager.isVtEnabledByPlatform() && imsManager.isVtProvisionedOnDevice() && MobileNetworkUtils.isImsServiceStateReady(imsManager) && (configForSubId.getBoolean("ignore_data_enabled_changed_for_video_calls") || TelephonyManager.from(this.mContext).createForSubscriptionId(i).isDataEnabled());
    }

    @Override // com.android.car.developeroptions.network.telephony.Enhanced4gLtePreferenceController.On4gLteUpdateListener
    public void on4gLteUpdated() {
        updateState(this.mPreference);
    }

    private class PhoneCallStateListener extends PhoneStateListener {
        public PhoneCallStateListener(Looper looper) {
            super(looper);
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            VideoCallingPreferenceController videoCallingPreferenceController = VideoCallingPreferenceController.this;
            videoCallingPreferenceController.updateState(videoCallingPreferenceController.mPreference);
        }

        public void register(int i) {
            ((PhoneStateListener) this).mSubId = Integer.valueOf(i);
            VideoCallingPreferenceController.this.mTelephonyManager.listen(this, 32);
        }

        public void unregister() {
            VideoCallingPreferenceController.this.mTelephonyManager.listen(this, 0);
        }
    }

    public class DataContentObserver extends ContentObserver {
        public DataContentObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            VideoCallingPreferenceController videoCallingPreferenceController = VideoCallingPreferenceController.this;
            videoCallingPreferenceController.updateState(videoCallingPreferenceController.mPreference);
        }

        public void register(Context context, int i) {
            Uri uriFor = Settings.Global.getUriFor("mobile_data");
            if (TelephonyManager.getDefault().getSimCount() != 1) {
                uriFor = Settings.Global.getUriFor("mobile_data" + i);
            }
            context.getContentResolver().registerContentObserver(uriFor, false, this);
        }

        public void unRegister(Context context) {
            context.getContentResolver().unregisterContentObserver(this);
        }
    }
}
