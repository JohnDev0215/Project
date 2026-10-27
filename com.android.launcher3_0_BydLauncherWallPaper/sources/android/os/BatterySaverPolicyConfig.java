package android.os;

import android.annotation.SystemApi;
import android.os.Parcelable;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class BatterySaverPolicyConfig implements Parcelable {
    public static final Parcelable.Creator<BatterySaverPolicyConfig> CREATOR = new Parcelable.Creator<BatterySaverPolicyConfig>() { // from class: android.os.BatterySaverPolicyConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BatterySaverPolicyConfig createFromParcel(Parcel parcel) {
            return new BatterySaverPolicyConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BatterySaverPolicyConfig[] newArray(int i) {
            return new BatterySaverPolicyConfig[i];
        }
    };
    private final float mAdjustBrightnessFactor;
    private final boolean mAdvertiseIsEnabled;
    private final boolean mDeferFullBackup;
    private final boolean mDeferKeyValueBackup;
    private final Map<String, String> mDeviceSpecificSettings;
    private final boolean mDisableAnimation;
    private final boolean mDisableAod;
    private final boolean mDisableLaunchBoost;
    private final boolean mDisableOptionalSensors;
    private final boolean mDisableSoundTrigger;
    private final boolean mDisableVibration;
    private final boolean mEnableAdjustBrightness;
    private final boolean mEnableDataSaver;
    private final boolean mEnableFirewall;
    private final boolean mEnableNightMode;
    private final boolean mEnableQuickDoze;
    private final boolean mForceAllAppsStandby;
    private final boolean mForceBackgroundCheck;
    private final int mLocationMode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private BatterySaverPolicyConfig(Builder builder) {
        this.mAdjustBrightnessFactor = Math.max(0.0f, Math.min(builder.mAdjustBrightnessFactor, 1.0f));
        this.mAdvertiseIsEnabled = builder.mAdvertiseIsEnabled;
        this.mDeferFullBackup = builder.mDeferFullBackup;
        this.mDeferKeyValueBackup = builder.mDeferKeyValueBackup;
        this.mDeviceSpecificSettings = Collections.unmodifiableMap(new ArrayMap(builder.mDeviceSpecificSettings));
        this.mDisableAnimation = builder.mDisableAnimation;
        this.mDisableAod = builder.mDisableAod;
        this.mDisableLaunchBoost = builder.mDisableLaunchBoost;
        this.mDisableOptionalSensors = builder.mDisableOptionalSensors;
        this.mDisableSoundTrigger = builder.mDisableSoundTrigger;
        this.mDisableVibration = builder.mDisableVibration;
        this.mEnableAdjustBrightness = builder.mEnableAdjustBrightness;
        this.mEnableDataSaver = builder.mEnableDataSaver;
        this.mEnableFirewall = builder.mEnableFirewall;
        this.mEnableNightMode = builder.mEnableNightMode;
        this.mEnableQuickDoze = builder.mEnableQuickDoze;
        this.mForceAllAppsStandby = builder.mForceAllAppsStandby;
        this.mForceBackgroundCheck = builder.mForceBackgroundCheck;
        this.mLocationMode = Math.max(0, Math.min(builder.mLocationMode, 4));
    }

    private BatterySaverPolicyConfig(Parcel parcel) {
        this.mAdjustBrightnessFactor = Math.max(0.0f, Math.min(parcel.readFloat(), 1.0f));
        this.mAdvertiseIsEnabled = parcel.readBoolean();
        this.mDeferFullBackup = parcel.readBoolean();
        this.mDeferKeyValueBackup = parcel.readBoolean();
        int i = parcel.readInt();
        ArrayMap arrayMap = new ArrayMap(i);
        for (int i2 = 0; i2 < i; i2++) {
            String strEmptyIfNull = TextUtils.emptyIfNull(parcel.readString());
            String strEmptyIfNull2 = TextUtils.emptyIfNull(parcel.readString());
            if (!strEmptyIfNull.trim().isEmpty()) {
                arrayMap.put(strEmptyIfNull, strEmptyIfNull2);
            }
        }
        this.mDeviceSpecificSettings = Collections.unmodifiableMap(arrayMap);
        this.mDisableAnimation = parcel.readBoolean();
        this.mDisableAod = parcel.readBoolean();
        this.mDisableLaunchBoost = parcel.readBoolean();
        this.mDisableOptionalSensors = parcel.readBoolean();
        this.mDisableSoundTrigger = parcel.readBoolean();
        this.mDisableVibration = parcel.readBoolean();
        this.mEnableAdjustBrightness = parcel.readBoolean();
        this.mEnableDataSaver = parcel.readBoolean();
        this.mEnableFirewall = parcel.readBoolean();
        this.mEnableNightMode = parcel.readBoolean();
        this.mEnableQuickDoze = parcel.readBoolean();
        this.mForceAllAppsStandby = parcel.readBoolean();
        this.mForceBackgroundCheck = parcel.readBoolean();
        this.mLocationMode = Math.max(0, Math.min(parcel.readInt(), 4));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.mAdjustBrightnessFactor);
        parcel.writeBoolean(this.mAdvertiseIsEnabled);
        parcel.writeBoolean(this.mDeferFullBackup);
        parcel.writeBoolean(this.mDeferKeyValueBackup);
        Set<Map.Entry<String, String>> setEntrySet = this.mDeviceSpecificSettings.entrySet();
        parcel.writeInt(setEntrySet.size());
        for (Map.Entry<String, String> entry : setEntrySet) {
            parcel.writeString(entry.getKey());
            parcel.writeString(entry.getValue());
        }
        parcel.writeBoolean(this.mDisableAnimation);
        parcel.writeBoolean(this.mDisableAod);
        parcel.writeBoolean(this.mDisableLaunchBoost);
        parcel.writeBoolean(this.mDisableOptionalSensors);
        parcel.writeBoolean(this.mDisableSoundTrigger);
        parcel.writeBoolean(this.mDisableVibration);
        parcel.writeBoolean(this.mEnableAdjustBrightness);
        parcel.writeBoolean(this.mEnableDataSaver);
        parcel.writeBoolean(this.mEnableFirewall);
        parcel.writeBoolean(this.mEnableNightMode);
        parcel.writeBoolean(this.mEnableQuickDoze);
        parcel.writeBoolean(this.mForceAllAppsStandby);
        parcel.writeBoolean(this.mForceBackgroundCheck);
        parcel.writeInt(this.mLocationMode);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : this.mDeviceSpecificSettings.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(SmsManager.REGEX_PREFIX_DELIMITER);
        }
        return "adjust_brightness_disabled=" + (!this.mEnableAdjustBrightness) + ",adjust_brightness_factor=" + this.mAdjustBrightnessFactor + ",advertise_is_enabled=" + this.mAdvertiseIsEnabled + ",animation_disabled=" + this.mDisableAnimation + ",aod_disabled=" + this.mDisableAod + ",datasaver_disabled=" + (!this.mEnableDataSaver) + ",enable_night_mode=" + this.mEnableNightMode + ",firewall_disabled=" + (!this.mEnableFirewall) + ",force_all_apps_standby=" + this.mForceAllAppsStandby + ",force_background_check=" + this.mForceBackgroundCheck + ",fullbackup_deferred=" + this.mDeferFullBackup + ",gps_mode=" + this.mLocationMode + ",keyvaluebackup_deferred=" + this.mDeferKeyValueBackup + ",launch_boost_disabled=" + this.mDisableLaunchBoost + ",optional_sensors_disabled=" + this.mDisableOptionalSensors + ",quick_doze_enabled=" + this.mEnableQuickDoze + ",soundtrigger_disabled=" + this.mDisableSoundTrigger + ",vibration_disabled=" + this.mDisableVibration + SmsManager.REGEX_PREFIX_DELIMITER + sb.toString();
    }

    public float getAdjustBrightnessFactor() {
        return this.mAdjustBrightnessFactor;
    }

    public boolean getAdvertiseIsEnabled() {
        return this.mAdvertiseIsEnabled;
    }

    public boolean getDeferFullBackup() {
        return this.mDeferFullBackup;
    }

    public boolean getDeferKeyValueBackup() {
        return this.mDeferKeyValueBackup;
    }

    public Map<String, String> getDeviceSpecificSettings() {
        return this.mDeviceSpecificSettings;
    }

    public boolean getDisableAnimation() {
        return this.mDisableAnimation;
    }

    public boolean getDisableAod() {
        return this.mDisableAod;
    }

    public boolean getDisableLaunchBoost() {
        return this.mDisableLaunchBoost;
    }

    public boolean getDisableOptionalSensors() {
        return this.mDisableOptionalSensors;
    }

    public boolean getDisableSoundTrigger() {
        return this.mDisableSoundTrigger;
    }

    public boolean getDisableVibration() {
        return this.mDisableVibration;
    }

    public boolean getEnableAdjustBrightness() {
        return this.mEnableAdjustBrightness;
    }

    public boolean getEnableDataSaver() {
        return this.mEnableDataSaver;
    }

    public boolean getEnableFirewall() {
        return this.mEnableFirewall;
    }

    public boolean getEnableNightMode() {
        return this.mEnableNightMode;
    }

    public boolean getEnableQuickDoze() {
        return this.mEnableQuickDoze;
    }

    public boolean getForceAllAppsStandby() {
        return this.mForceAllAppsStandby;
    }

    public boolean getForceBackgroundCheck() {
        return this.mForceBackgroundCheck;
    }

    public int getLocationMode() {
        return this.mLocationMode;
    }

    public static final class Builder {
        private float mAdjustBrightnessFactor = 1.0f;
        private boolean mAdvertiseIsEnabled = false;
        private boolean mDeferFullBackup = false;
        private boolean mDeferKeyValueBackup = false;
        private final ArrayMap<String, String> mDeviceSpecificSettings = new ArrayMap<>();
        private boolean mDisableAnimation = false;
        private boolean mDisableAod = false;
        private boolean mDisableLaunchBoost = false;
        private boolean mDisableOptionalSensors = false;
        private boolean mDisableSoundTrigger = false;
        private boolean mDisableVibration = false;
        private boolean mEnableAdjustBrightness = false;
        private boolean mEnableDataSaver = false;
        private boolean mEnableFirewall = false;
        private boolean mEnableNightMode = false;
        private boolean mEnableQuickDoze = false;
        private boolean mForceAllAppsStandby = false;
        private boolean mForceBackgroundCheck = false;
        private int mLocationMode = 0;

        public Builder setAdjustBrightnessFactor(float f) {
            this.mAdjustBrightnessFactor = f;
            return this;
        }

        public Builder setAdvertiseIsEnabled(boolean z) {
            this.mAdvertiseIsEnabled = z;
            return this;
        }

        public Builder setDeferFullBackup(boolean z) {
            this.mDeferFullBackup = z;
            return this;
        }

        public Builder setDeferKeyValueBackup(boolean z) {
            this.mDeferKeyValueBackup = z;
            return this;
        }

        public Builder addDeviceSpecificSetting(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }
            String strTrim = str.trim();
            if (TextUtils.isEmpty(strTrim)) {
                throw new IllegalArgumentException("Key cannot be empty");
            }
            this.mDeviceSpecificSettings.put(strTrim, TextUtils.emptyIfNull(str2));
            return this;
        }

        public Builder setDisableAnimation(boolean z) {
            this.mDisableAnimation = z;
            return this;
        }

        public Builder setDisableAod(boolean z) {
            this.mDisableAod = z;
            return this;
        }

        public Builder setDisableLaunchBoost(boolean z) {
            this.mDisableLaunchBoost = z;
            return this;
        }

        public Builder setDisableOptionalSensors(boolean z) {
            this.mDisableOptionalSensors = z;
            return this;
        }

        public Builder setDisableSoundTrigger(boolean z) {
            this.mDisableSoundTrigger = z;
            return this;
        }

        public Builder setDisableVibration(boolean z) {
            this.mDisableVibration = z;
            return this;
        }

        public Builder setEnableAdjustBrightness(boolean z) {
            this.mEnableAdjustBrightness = z;
            return this;
        }

        public Builder setEnableDataSaver(boolean z) {
            this.mEnableDataSaver = z;
            return this;
        }

        public Builder setEnableFirewall(boolean z) {
            this.mEnableFirewall = z;
            return this;
        }

        public Builder setEnableNightMode(boolean z) {
            this.mEnableNightMode = z;
            return this;
        }

        public Builder setEnableQuickDoze(boolean z) {
            this.mEnableQuickDoze = z;
            return this;
        }

        public Builder setForceAllAppsStandby(boolean z) {
            this.mForceAllAppsStandby = z;
            return this;
        }

        public Builder setForceBackgroundCheck(boolean z) {
            this.mForceBackgroundCheck = z;
            return this;
        }

        public Builder setLocationMode(int i) {
            this.mLocationMode = i;
            return this;
        }

        public BatterySaverPolicyConfig build() {
            return new BatterySaverPolicyConfig(this);
        }
    }
}
