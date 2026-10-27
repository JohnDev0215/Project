package com.android.car.settings.wifi.preferences;

import android.car.drivingstate.CarUxRestrictions;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.URLSpan;
import androidx.preference.TwoStatePreference;
import com.android.car.settings.R;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.settingslib.HelpUtils;

/* JADX INFO: loaded from: classes5.dex */
public class WifiWakeupTogglePreferenceController extends PreferenceController<TwoStatePreference> {
    private static final Logger LOG = new Logger((Class<?>) WifiWakeupTogglePreferenceController.class);
    final ConfirmationDialogFragment.ConfirmListener mConfirmListener;
    private LocationManager mLocationManager;
    private final ConfirmationDialogFragment.NeutralListener mNeutralListener;
    WifiManager mWifiManager;

    public /* synthetic */ void lambda$new$0$WifiWakeupTogglePreferenceController(Bundle arguments) {
        enableWifiScanning();
        if (this.mLocationManager.isLocationEnabled()) {
            setWifiWakeupEnabled(true);
        }
        refreshUi();
    }

    public /* synthetic */ void lambda$new$1$WifiWakeupTogglePreferenceController(Bundle arguments) {
        Intent intent = HelpUtils.getHelpIntent(getContext(), getContext().getString(R.string.help_uri_wifi_scanning_required), getContext().getClass().getName());
        if (intent != null) {
            try {
                getContext().startActivity(intent);
            } catch (ActivityNotFoundException e) {
                LOG.e("Activity was not found for intent, " + intent.toString());
            }
        }
    }

    public WifiWakeupTogglePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mConfirmListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.wifi.preferences.-$$Lambda$WifiWakeupTogglePreferenceController$qAEOL-qhH5AX6usEngTFe9p_JWs
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public final void onConfirm(Bundle bundle) {
                this.f$0.lambda$new$0$WifiWakeupTogglePreferenceController(bundle);
            }
        };
        this.mNeutralListener = new ConfirmationDialogFragment.NeutralListener() { // from class: com.android.car.settings.wifi.preferences.-$$Lambda$WifiWakeupTogglePreferenceController$9nzruEghWhKRUC_HpJeODslMGvg
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.NeutralListener
            public final void onNeutral(Bundle bundle) {
                this.f$0.lambda$new$1$WifiWakeupTogglePreferenceController(bundle);
            }
        };
        this.mLocationManager = (LocationManager) context.getSystemService("location");
        this.mWifiManager = (WifiManager) context.getSystemService(WifiManager.class);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<TwoStatePreference> getPreferenceType() {
        return TwoStatePreference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        ConfirmationDialogFragment dialog = (ConfirmationDialogFragment) getFragmentController().findDialogByTag(ConfirmationDialogFragment.TAG);
        ConfirmationDialogFragment.resetListeners(dialog, this.mConfirmListener, null, this.mNeutralListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(TwoStatePreference preference) {
        preference.setChecked(getWifiWakeupEnabled() && getWifiScanningEnabled() && this.mLocationManager.isLocationEnabled());
        if (!this.mLocationManager.isLocationEnabled()) {
            preference.setSummary(getNoLocationSummary());
        } else {
            preference.setSummary(R.string.wifi_wakeup_summary);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceClicked(TwoStatePreference preference) {
        if (!this.mLocationManager.isLocationEnabled()) {
            Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
            getContext().startActivity(intent);
        } else if (getWifiWakeupEnabled()) {
            setWifiWakeupEnabled(false);
        } else if (!getWifiScanningEnabled()) {
            showScanningDialog();
        } else {
            setWifiWakeupEnabled(true);
        }
        refreshUi();
        return true;
    }

    private boolean getWifiWakeupEnabled() {
        return Settings.Global.getInt(getContext().getContentResolver(), "wifi_wakeup_enabled", 0) == 1;
    }

    private void setWifiWakeupEnabled(boolean z) {
        Settings.Global.putInt(getContext().getContentResolver(), "wifi_wakeup_enabled", z ? 1 : 0);
    }

    private boolean getWifiScanningEnabled() {
        return this.mWifiManager.isScanAlwaysAvailable();
    }

    private void enableWifiScanning() {
        this.mWifiManager.setScanAlwaysAvailable(true);
    }

    private CharSequence getNoLocationSummary() {
        CharSequence locationText = getContext().getText(R.string.wifi_wakeup_summary_no_location);
        SpannableString msg = new SpannableString(locationText);
        int startIndex = locationText.toString().indexOf("link");
        if (startIndex >= 0) {
            msg.setSpan(new URLSpan((String) null), startIndex, "link".length() + startIndex, 33);
            return msg;
        }
        LOG.e("Cannot create URL span");
        return locationText;
    }

    private void showScanningDialog() {
        ConfirmationDialogFragment dialogFragment = getConfirmEnableWifiScanningDialogFragment();
        getFragmentController().showDialog(dialogFragment, ConfirmationDialogFragment.TAG);
    }

    private ConfirmationDialogFragment getConfirmEnableWifiScanningDialogFragment() {
        ConfirmationDialogFragment.Builder builder = new ConfirmationDialogFragment.Builder(getContext()).setTitle(R.string.wifi_settings_scanning_required_title).setPositiveButton(R.string.wifi_settings_scanning_required_turn_on, this.mConfirmListener).setNegativeButton(R.string.cancel, (ConfirmationDialogFragment.RejectListener) null);
        if (!TextUtils.isEmpty(getContext().getString(R.string.help_uri_wifi_scanning_required))) {
            builder.setNeutralButton(R.string.learn_more, this.mNeutralListener);
        }
        return builder.build();
    }
}
