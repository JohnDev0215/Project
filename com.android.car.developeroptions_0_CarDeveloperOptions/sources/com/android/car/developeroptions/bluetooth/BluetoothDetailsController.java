package com.android.car.developeroptions.bluetooth;

import android.content.Context;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;

/* JADX INFO: loaded from: classes.dex */
public abstract class BluetoothDetailsController extends AbstractPreferenceController implements PreferenceControllerMixin, CachedBluetoothDevice.Callback, LifecycleObserver, OnPause, OnResume {
    protected final CachedBluetoothDevice mCachedDevice;
    protected final Context mContext;

    protected abstract void init(PreferenceScreen preferenceScreen);

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    protected abstract void refresh();

    @Override // com.android.settingslib.core.lifecycle.events.OnPause
    public void onPause() {
        this.mCachedDevice.unregisterCallback(this);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        this.mCachedDevice.registerCallback(this);
        refresh();
    }

    @Override // com.android.settingslib.bluetooth.CachedBluetoothDevice.Callback
    public void onDeviceAttributesChanged() {
        refresh();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public final void displayPreference(PreferenceScreen preferenceScreen) {
        init(preferenceScreen);
        super.displayPreference(preferenceScreen);
    }
}
