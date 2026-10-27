package com.android.car.developeroptions.connecteddevice.usb;

import android.content.Context;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.connecteddevice.DevicePreferenceCallback;
import com.android.car.developeroptions.connecteddevice.usb.UsbConnectionBroadcastReceiver;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.dashboard.DashboardFragment;

/* JADX INFO: loaded from: classes.dex */
public class ConnectedUsbDeviceUpdater {
    private DevicePreferenceCallback mDevicePreferenceCallback;
    private DashboardFragment mFragment;
    private UsbBackend mUsbBackend;
    UsbConnectionBroadcastReceiver.UsbConnectionListener mUsbConnectionListener;
    Preference mUsbPreference;
    UsbConnectionBroadcastReceiver mUsbReceiver;

    public static int getSummary(long j, int i) {
        return i != 1 ? i != 2 ? R.string.usb_summary_charging_only : j == 4 ? R.string.usb_summary_file_transfers : j == 32 ? R.string.usb_summary_tether : j == 16 ? R.string.usb_summary_photo_transfers : j == 8 ? R.string.usb_summary_MIDI : R.string.usb_summary_charging_only : j == 4 ? R.string.usb_summary_file_transfers_power : j == 32 ? R.string.usb_summary_tether_power : j == 16 ? R.string.usb_summary_photo_transfers_power : j == 8 ? R.string.usb_summary_MIDI_power : R.string.usb_summary_power_only;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$0$ConnectedUsbDeviceUpdater(boolean z, long j, int i, int i2) {
        if (z) {
            Preference preference = this.mUsbPreference;
            if (i2 != 2) {
                j = 0;
            }
            preference.setSummary(getSummary(j, i));
            this.mDevicePreferenceCallback.onDeviceAdded(this.mUsbPreference);
            return;
        }
        this.mDevicePreferenceCallback.onDeviceRemoved(this.mUsbPreference);
    }

    public ConnectedUsbDeviceUpdater(Context context, DashboardFragment dashboardFragment, DevicePreferenceCallback devicePreferenceCallback) {
        this(context, dashboardFragment, devicePreferenceCallback, new UsbBackend(context));
    }

    ConnectedUsbDeviceUpdater(Context context, DashboardFragment dashboardFragment, DevicePreferenceCallback devicePreferenceCallback, UsbBackend usbBackend) {
        this.mUsbConnectionListener = new UsbConnectionBroadcastReceiver.UsbConnectionListener() { // from class: com.android.car.developeroptions.connecteddevice.usb.-$$Lambda$ConnectedUsbDeviceUpdater$__3YvmIOzj141YaL3NouPoeLoWU
            @Override // com.android.car.developeroptions.connecteddevice.usb.UsbConnectionBroadcastReceiver.UsbConnectionListener
            public final void onUsbConnectionChanged(boolean z, long j, int i, int i2) {
                this.f$0.lambda$new$0$ConnectedUsbDeviceUpdater(z, j, i, i2);
            }
        };
        this.mFragment = dashboardFragment;
        this.mDevicePreferenceCallback = devicePreferenceCallback;
        this.mUsbBackend = usbBackend;
        this.mUsbReceiver = new UsbConnectionBroadcastReceiver(context, this.mUsbConnectionListener, this.mUsbBackend);
    }

    public void registerCallback() {
        this.mUsbReceiver.register();
    }

    public void unregisterCallback() {
        this.mUsbReceiver.unregister();
    }

    public void initUsbPreference(Context context) {
        Preference preference = new Preference(context, null);
        this.mUsbPreference = preference;
        preference.setTitle(R.string.usb_pref);
        this.mUsbPreference.setIcon(R.drawable.ic_usb);
        this.mUsbPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.connecteddevice.usb.-$$Lambda$ConnectedUsbDeviceUpdater$mvE9jD3PoG_XL0iDJ2gZH6GnsLU
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference2) {
                return this.f$0.lambda$initUsbPreference$1$ConnectedUsbDeviceUpdater(preference2);
            }
        });
        forceUpdate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$initUsbPreference$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$initUsbPreference$1$ConnectedUsbDeviceUpdater(Preference preference) {
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mFragment.getContext());
        subSettingLauncher.setDestination(UsbDetailsFragment.class.getName());
        subSettingLauncher.setTitleRes(R.string.device_details_title);
        subSettingLauncher.setSourceMetricsCategory(this.mFragment.getMetricsCategory());
        subSettingLauncher.launch();
        return true;
    }

    private void forceUpdate() {
        this.mUsbReceiver.register();
    }
}
