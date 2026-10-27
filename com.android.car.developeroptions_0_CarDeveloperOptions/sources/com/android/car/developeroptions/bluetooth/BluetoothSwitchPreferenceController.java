package com.android.car.developeroptions.bluetooth;

import android.content.Context;
import android.view.View;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.location.ScanningSettings;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.utils.AnnotationSpan;
import com.android.car.developeroptions.widget.SwitchWidgetController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.widget.FooterPreference;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothSwitchPreferenceController implements LifecycleObserver, OnStart, OnStop, SwitchWidgetController.OnSwitchChangeListener, View.OnClickListener {
    private BluetoothEnabler mBluetoothEnabler;
    private Context mContext;
    private FooterPreference mFooterPreference;
    private RestrictionUtils mRestrictionUtils;
    private SwitchWidgetController mSwitch;

    public BluetoothSwitchPreferenceController(Context context, SwitchWidgetController switchWidgetController, FooterPreference footerPreference) {
        this(context, new RestrictionUtils(), switchWidgetController, footerPreference);
    }

    public BluetoothSwitchPreferenceController(Context context, RestrictionUtils restrictionUtils, SwitchWidgetController switchWidgetController, FooterPreference footerPreference) {
        this.mRestrictionUtils = restrictionUtils;
        this.mSwitch = switchWidgetController;
        this.mContext = context;
        this.mFooterPreference = footerPreference;
        switchWidgetController.setupView();
        updateText(this.mSwitch.isChecked());
        BluetoothEnabler bluetoothEnabler = new BluetoothEnabler(context, switchWidgetController, FeatureFactory.getFactory(context).getMetricsFeatureProvider(), 870, this.mRestrictionUtils);
        this.mBluetoothEnabler = bluetoothEnabler;
        bluetoothEnabler.setToggleCallback(this);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mBluetoothEnabler.resume(this.mContext);
        SwitchWidgetController switchWidgetController = this.mSwitch;
        if (switchWidgetController != null) {
            updateText(switchWidgetController.isChecked());
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mBluetoothEnabler.pause();
    }

    @Override // com.android.car.developeroptions.widget.SwitchWidgetController.OnSwitchChangeListener
    public boolean onSwitchToggled(boolean z) {
        updateText(z);
        return true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(ScanningSettings.class.getName());
        subSettingLauncher.setSourceMetricsCategory(1390);
        subSettingLauncher.launch();
    }

    void updateText(boolean z) {
        if (!z && Utils.isBluetoothScanningEnabled(this.mContext)) {
            this.mFooterPreference.setTitle(AnnotationSpan.linkify(this.mContext.getText(R.string.bluetooth_scanning_on_info_message), new AnnotationSpan.LinkInfo("link", this)));
        } else {
            this.mFooterPreference.setTitle(R.string.bluetooth_empty_list_bluetooth_off);
        }
    }
}
