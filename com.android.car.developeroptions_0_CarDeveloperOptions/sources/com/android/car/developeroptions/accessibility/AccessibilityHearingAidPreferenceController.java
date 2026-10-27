package com.android.car.developeroptions.accessibility;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.bluetooth.BluetoothDeviceDetailsFragment;
import com.android.car.developeroptions.bluetooth.Utils;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes.dex */
public class AccessibilityHearingAidPreferenceController extends BasePreferenceController implements LifecycleObserver {
    private static final String TAG = "AccessibilityHearingAidPreferenceController";
    private final BluetoothAdapter mBluetoothAdapter;
    private FragmentManager mFragmentManager;
    private final BroadcastReceiver mHearingAidChangedReceiver;
    private Preference mHearingAidPreference;
    private boolean mHearingAidProfileSupported;
    private final LocalBluetoothManager mLocalBluetoothManager;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public AccessibilityHearingAidPreferenceController(Context context, String str) {
        super(context, str);
        this.mHearingAidChangedReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.accessibility.AccessibilityHearingAidPreferenceController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                    if (intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0) == 2) {
                        AccessibilityHearingAidPreferenceController accessibilityHearingAidPreferenceController = AccessibilityHearingAidPreferenceController.this;
                        accessibilityHearingAidPreferenceController.updateState(accessibilityHearingAidPreferenceController.mHearingAidPreference);
                        return;
                    } else {
                        AccessibilityHearingAidPreferenceController.this.mHearingAidPreference.setSummary(R.string.accessibility_hearingaid_not_connected_summary);
                        return;
                    }
                }
                if (!"android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction()) || intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE) == 12) {
                    return;
                }
                AccessibilityHearingAidPreferenceController.this.mHearingAidPreference.setSummary(R.string.accessibility_hearingaid_not_connected_summary);
            }
        };
        this.mLocalBluetoothManager = getLocalBluetoothManager();
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mHearingAidProfileSupported = isHearingAidProfileSupported();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mHearingAidPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mHearingAidProfileSupported ? 0 : 3;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        if (this.mHearingAidProfileSupported) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            this.mContext.registerReceiver(this.mHearingAidChangedReceiver, intentFilter);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        if (this.mHearingAidProfileSupported) {
            this.mContext.unregisterReceiver(this.mHearingAidChangedReceiver);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            return false;
        }
        CachedBluetoothDevice connectedHearingAidDevice = getConnectedHearingAidDevice();
        if (connectedHearingAidDevice == null) {
            launchHearingAidInstructionDialog();
            return true;
        }
        launchBluetoothDeviceDetailSetting(connectedHearingAidDevice);
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        CachedBluetoothDevice connectedHearingAidDevice = getConnectedHearingAidDevice();
        if (connectedHearingAidDevice == null) {
            return this.mContext.getText(R.string.accessibility_hearingaid_not_connected_summary);
        }
        return connectedHearingAidDevice.getName();
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    CachedBluetoothDevice getConnectedHearingAidDevice() {
        BluetoothAdapter bluetoothAdapter;
        if (this.mHearingAidProfileSupported && (bluetoothAdapter = this.mBluetoothAdapter) != null && bluetoothAdapter.isEnabled()) {
            for (BluetoothDevice bluetoothDevice : this.mLocalBluetoothManager.getProfileManager().getHearingAidProfile().getConnectedDevices()) {
                if (!this.mLocalBluetoothManager.getCachedDeviceManager().isSubDevice(bluetoothDevice)) {
                    return this.mLocalBluetoothManager.getCachedDeviceManager().findDevice(bluetoothDevice);
                }
            }
        }
        return null;
    }

    private boolean isHearingAidProfileSupported() {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled() && this.mBluetoothAdapter.getSupportedProfiles().contains(21);
    }

    private LocalBluetoothManager getLocalBluetoothManager() {
        FutureTask futureTask = new FutureTask(new Callable() { // from class: com.android.car.developeroptions.accessibility.-$$Lambda$AccessibilityHearingAidPreferenceController$1iW_bYrzKTvyopkb-mc6DIszNUo
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$getLocalBluetoothManager$0$AccessibilityHearingAidPreferenceController();
            }
        });
        try {
            futureTask.run();
            return (LocalBluetoothManager) futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.w(TAG, "Error getting LocalBluetoothManager.", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$getLocalBluetoothManager$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ LocalBluetoothManager lambda$getLocalBluetoothManager$0$AccessibilityHearingAidPreferenceController() throws Exception {
        return Utils.getLocalBtManager(this.mContext);
    }

    void setPreference(Preference preference) {
        this.mHearingAidPreference = preference;
    }

    void launchBluetoothDeviceDetailSetting(CachedBluetoothDevice cachedBluetoothDevice) {
        if (cachedBluetoothDevice == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("device_address", cachedBluetoothDevice.getDevice().getAddress());
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(BluetoothDeviceDetailsFragment.class.getName());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setTitleRes(R.string.device_details_title);
        subSettingLauncher.setSourceMetricsCategory(2);
        subSettingLauncher.launch();
    }

    void launchHearingAidInstructionDialog() {
        HearingAidDialogFragment.newInstance().show(this.mFragmentManager, HearingAidDialogFragment.class.toString());
    }
}
