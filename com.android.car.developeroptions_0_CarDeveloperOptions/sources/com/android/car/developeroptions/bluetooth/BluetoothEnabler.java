package com.android.car.developeroptions.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.widget.SwitchWidgetController;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.WirelessUtils;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothEnabler implements SwitchWidgetController.OnSwitchChangeListener {
    private final BluetoothAdapter mBluetoothAdapter;
    private SwitchWidgetController.OnSwitchChangeListener mCallback;
    private Context mContext;
    private final IntentFilter mIntentFilter;
    private final int mMetricsEvent;
    private final MetricsFeatureProvider mMetricsFeatureProvider;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.bluetooth.BluetoothEnabler.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothEnabler.this.handleStateChanged(intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE));
        }
    };
    private final RestrictionUtils mRestrictionUtils;
    private final SwitchWidgetController mSwitchController;
    private boolean mValidListener;

    public BluetoothEnabler(Context context, SwitchWidgetController switchWidgetController, MetricsFeatureProvider metricsFeatureProvider, int i, RestrictionUtils restrictionUtils) {
        this.mContext = context;
        this.mMetricsFeatureProvider = metricsFeatureProvider;
        this.mSwitchController = switchWidgetController;
        switchWidgetController.setListener(this);
        this.mValidListener = false;
        this.mMetricsEvent = i;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothAdapter = defaultAdapter;
        if (defaultAdapter == null) {
            this.mSwitchController.setEnabled(false);
        }
        this.mIntentFilter = new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
        this.mRestrictionUtils = restrictionUtils;
    }

    public void resume(Context context) {
        if (this.mContext != context) {
            this.mContext = context;
        }
        boolean zMaybeEnforceRestrictions = maybeEnforceRestrictions();
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null) {
            this.mSwitchController.setEnabled(false);
            return;
        }
        if (!zMaybeEnforceRestrictions) {
            handleStateChanged(bluetoothAdapter.getState());
        }
        this.mSwitchController.startListening();
        this.mContext.registerReceiver(this.mReceiver, this.mIntentFilter);
        this.mValidListener = true;
    }

    public void pause() {
        if (this.mBluetoothAdapter != null && this.mValidListener) {
            this.mSwitchController.stopListening();
            this.mContext.unregisterReceiver(this.mReceiver);
            this.mValidListener = false;
        }
    }

    void handleStateChanged(int i) {
        switch (i) {
            case 10:
                setChecked(false);
                this.mSwitchController.setEnabled(true);
                break;
            case 11:
                this.mSwitchController.setEnabled(false);
                break;
            case 12:
                setChecked(true);
                this.mSwitchController.setEnabled(true);
                break;
            case 13:
                this.mSwitchController.setEnabled(false);
                break;
            default:
                setChecked(false);
                this.mSwitchController.setEnabled(true);
                break;
        }
    }

    private void setChecked(boolean z) {
        if (z != this.mSwitchController.isChecked()) {
            if (this.mValidListener) {
                this.mSwitchController.stopListening();
            }
            this.mSwitchController.setChecked(z);
            if (this.mValidListener) {
                this.mSwitchController.startListening();
            }
        }
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController.OnSwitchChangeListener
    public boolean onSwitchToggled(boolean z) {
        if (maybeEnforceRestrictions()) {
            triggerParentPreferenceCallback(z);
            return true;
        }
        if (z && !WirelessUtils.isRadioAllowed(this.mContext, "bluetooth")) {
            Toast.makeText(this.mContext, R.string.wifi_in_airplane_mode, 0).show();
            this.mSwitchController.setChecked(false);
            triggerParentPreferenceCallback(false);
            return false;
        }
        this.mMetricsFeatureProvider.action(this.mContext, this.mMetricsEvent, z);
        if (this.mBluetoothAdapter != null) {
            boolean bluetoothEnabled = setBluetoothEnabled(z);
            if (z && !bluetoothEnabled) {
                this.mSwitchController.setChecked(false);
                this.mSwitchController.setEnabled(true);
                this.mSwitchController.updateTitle(false);
                triggerParentPreferenceCallback(false);
                return false;
            }
        }
        this.mSwitchController.setEnabled(false);
        triggerParentPreferenceCallback(z);
        return true;
    }

    public void setToggleCallback(SwitchWidgetController.OnSwitchChangeListener onSwitchChangeListener) {
        this.mCallback = onSwitchChangeListener;
    }

    boolean maybeEnforceRestrictions() {
        RestrictedLockUtils.EnforcedAdmin enforcedAdmin = getEnforcedAdmin(this.mRestrictionUtils, this.mContext);
        this.mSwitchController.setDisabledByAdmin(enforcedAdmin);
        if (enforcedAdmin != null) {
            this.mSwitchController.setChecked(false);
            this.mSwitchController.setEnabled(false);
        }
        return enforcedAdmin != null;
    }

    public static RestrictedLockUtils.EnforcedAdmin getEnforcedAdmin(RestrictionUtils restrictionUtils, Context context) {
        RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfRestrictionEnforced = restrictionUtils.checkIfRestrictionEnforced(context, "no_bluetooth");
        return enforcedAdminCheckIfRestrictionEnforced == null ? restrictionUtils.checkIfRestrictionEnforced(context, "no_config_bluetooth") : enforcedAdminCheckIfRestrictionEnforced;
    }

    private void triggerParentPreferenceCallback(boolean z) {
        SwitchWidgetController.OnSwitchChangeListener onSwitchChangeListener = this.mCallback;
        if (onSwitchChangeListener != null) {
            onSwitchChangeListener.onSwitchToggled(z);
        }
    }

    private boolean setBluetoothEnabled(boolean z) {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        return z ? bluetoothAdapter.enable() : bluetoothAdapter.disable();
    }
}
