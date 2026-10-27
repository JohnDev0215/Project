package com.android.car.developeroptions.bluetooth;

import android.content.Context;
import android.util.FeatureFlagUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.RestrictedDashboardFragment;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.BlockingSlicePrefController;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.core.AbstractPreferenceController;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothDeviceDetailsFragment extends RestrictedDashboardFragment {
    static int EDIT_DEVICE_NAME_ITEM_ID = 1;
    static TestDataFactory sTestDataFactory;
    CachedBluetoothDevice mCachedDevice;
    String mDeviceAddress;
    LocalBluetoothManager mManager;

    interface TestDataFactory {
        CachedBluetoothDevice getDevice(String str);

        LocalBluetoothManager getManager(Context context);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return null;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "BTDeviceDetailsFrg";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1009;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.bluetooth_device_details_fragment;
    }

    public BluetoothDeviceDetailsFragment() {
        super("no_config_bluetooth");
    }

    LocalBluetoothManager getLocalBluetoothManager(Context context) {
        TestDataFactory testDataFactory = sTestDataFactory;
        if (testDataFactory != null) {
            return testDataFactory.getManager(context);
        }
        return Utils.getLocalBtManager(context);
    }

    CachedBluetoothDevice getCachedDevice(String str) {
        TestDataFactory testDataFactory = sTestDataFactory;
        if (testDataFactory != null) {
            return testDataFactory.getDevice(str);
        }
        return this.mManager.getCachedDeviceManager().findDevice(this.mManager.getBluetoothAdapter().getRemoteDevice(str));
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        this.mDeviceAddress = getArguments().getString("device_address");
        this.mManager = getLocalBluetoothManager(context);
        CachedBluetoothDevice cachedDevice = getCachedDevice(this.mDeviceAddress);
        this.mCachedDevice = cachedDevice;
        if (cachedDevice == null) {
            finish();
            return;
        }
        super.onAttach(context);
        BluetoothFeatureProvider bluetoothFeatureProvider = FeatureFactory.getFactory(context).getBluetoothFeatureProvider(context);
        ((BlockingSlicePrefController) use(BlockingSlicePrefController.class)).setSliceUri(FeatureFlagUtils.isEnabled(context, "settings_slice_injection") ? bluetoothFeatureProvider.getBluetoothDeviceSettingsUri(this.mCachedDevice.getDevice()) : null);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        MenuItem menuItemAdd = menu.add(0, EDIT_DEVICE_NAME_ITEM_ID, 0, R.string.bluetooth_rename_button);
        menuItemAdd.setIcon(android.R.drawable.ic_media_route_connecting_dark_01_mtrl);
        menuItemAdd.setShowAsAction(2);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == EDIT_DEVICE_NAME_ITEM_ID) {
            RemoteDeviceNameDialogFragment.newInstance(this.mCachedDevice).show(getFragmentManager(), "RemoteDeviceName");
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
