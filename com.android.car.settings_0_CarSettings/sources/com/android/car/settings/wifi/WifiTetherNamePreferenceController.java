package com.android.car.settings.wifi;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.net.wifi.SoftApConfiguration;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.ValidatedEditTextPreference;

/* JADX INFO: loaded from: classes5.dex */
public class WifiTetherNamePreferenceController extends WifiTetherBasePreferenceController<ValidatedEditTextPreference> {
    private static final int HOTSPOT_NAME_MAX_LENGTH = 32;
    private static final int HOTSPOT_NAME_MIN_LENGTH = 1;
    private static final ValidatedEditTextPreference.Validator NAME_VALIDATOR = new ValidatedEditTextPreference.Validator() { // from class: com.android.car.settings.wifi.-$$Lambda$WifiTetherNamePreferenceController$zooTVPkgEcF3I_NDbIeMETRRNwg
        @Override // com.android.car.settings.common.ValidatedEditTextPreference.Validator
        public final boolean isTextValid(String str) {
            return WifiTetherNamePreferenceController.lambda$static$0(str);
        }
    };
    private String mName;

    static /* synthetic */ boolean lambda$static$0(String value) {
        return value.length() >= 1 && value.length() <= 32;
    }

    public WifiTetherNamePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<ValidatedEditTextPreference> getPreferenceType() {
        return ValidatedEditTextPreference.class;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.car.settings.wifi.WifiTetherBasePreferenceController, com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        super.onCreateInternal();
        ((ValidatedEditTextPreference) getPreference()).setValidator(NAME_VALIDATOR);
        this.mName = getCarSoftApConfig().getSsid();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(ValidatedEditTextPreference preference, Object newValue) {
        String string = newValue.toString();
        this.mName = string;
        updateSSID(string);
        refreshUi();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.wifi.WifiTetherBasePreferenceController, com.android.car.settings.common.PreferenceController
    public void updateState(ValidatedEditTextPreference preference) {
        super.updateState(preference);
        preference.setText(this.mName);
    }

    private void updateSSID(String ssid) {
        SoftApConfiguration config = new SoftApConfiguration.Builder(getCarSoftApConfig()).setSsid(ssid).build();
        setCarSoftApConfig(config);
    }

    @Override // com.android.car.settings.wifi.WifiTetherBasePreferenceController
    protected String getSummary() {
        return this.mName;
    }

    @Override // com.android.car.settings.wifi.WifiTetherBasePreferenceController
    protected String getDefaultSummary() {
        return null;
    }
}
