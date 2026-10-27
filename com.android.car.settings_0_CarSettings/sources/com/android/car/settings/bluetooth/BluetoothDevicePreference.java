package com.android.car.settings.bluetooth;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.SystemProperties;
import android.util.Pair;
import androidx.preference.Preference;
import com.android.car.apps.common.util.Themes;
import com.android.car.settings.R;
import com.android.car.ui.preference.CarUiTwoActionIconPreference;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothDevicePreference extends CarUiTwoActionIconPreference {
    private static final String BLUETOOTH_SHOW_DEVICES_WITHOUT_NAMES_PROPERTY = "persist.bluetooth.showdeviceswithoutnames";
    private final CachedBluetoothDevice mCachedDevice;
    private final CachedBluetoothDevice.Callback mDeviceCallback;
    private final boolean mShowDevicesWithoutNames;

    public BluetoothDevicePreference(Context context, CachedBluetoothDevice cachedDevice) {
        super(context);
        this.mDeviceCallback = new CachedBluetoothDevice.Callback() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothDevicePreference$IpaPO6scxJKnM8y3Mm10A3JkwIo
            @Override // com.android.settingslib.bluetooth.CachedBluetoothDevice.Callback
            public final void onDeviceAttributesChanged() {
                this.f$0.refreshUi();
            }
        };
        this.mCachedDevice = cachedDevice;
        this.mShowDevicesWithoutNames = SystemProperties.getBoolean(BLUETOOTH_SHOW_DEVICES_WITHOUT_NAMES_PROPERTY, false);
        setSecondaryActionVisible(false);
    }

    public CachedBluetoothDevice getCachedDevice() {
        return this.mCachedDevice;
    }

    @Override // com.android.car.ui.preference.CarUiPreference, androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        this.mCachedDevice.registerCallback(this.mDeviceCallback);
        refreshUi();
    }

    @Override // androidx.preference.Preference
    public void onDetached() {
        super.onDetached();
        this.mCachedDevice.unregisterCallback(this.mDeviceCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshUi() {
        setTitle(this.mCachedDevice.getName());
        setSummary(this.mCachedDevice.getCarConnectionSummary());
        Pair<Drawable, String> pair = com.android.settingslib.bluetooth.BluetoothUtils.getBtClassDrawableWithDescription(getContext(), this.mCachedDevice);
        if (pair.first != null) {
            setIcon((Drawable) pair.first);
            getIcon().setTintList(Themes.getAttrColorStateList(getContext(), R.attr.iconColor));
        }
        boolean z = true;
        setEnabled(!this.mCachedDevice.isBusy());
        if (!this.mShowDevicesWithoutNames && !this.mCachedDevice.hasHumanReadableName()) {
            z = false;
        }
        setVisible(z);
        notifyHierarchyChanged();
    }

    public boolean equals(Object o) {
        if (!(o instanceof BluetoothDevicePreference)) {
            return false;
        }
        return this.mCachedDevice.equals(((BluetoothDevicePreference) o).mCachedDevice);
    }

    public int hashCode() {
        return this.mCachedDevice.hashCode();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.preference.Preference, java.lang.Comparable
    public int compareTo(Preference another) {
        if (!(another instanceof BluetoothDevicePreference)) {
            return super.compareTo(another);
        }
        return this.mCachedDevice.compareTo(((BluetoothDevicePreference) another).mCachedDevice);
    }
}
