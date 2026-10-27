package com.android.car.settings.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.search.CarBaseSearchIndexProvider;
import com.android.car.ui.toolbar.MenuItem;
import com.android.settingslib.Utils;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class LocationSettingsFragment extends SettingsFragment {
    private static final IntentFilter INTENT_FILTER_LOCATION_MODE_CHANGED = new IntentFilter("android.location.MODE_CHANGED");
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.location_settings_fragment, "android.settings.LOCATION_SOURCE_SETTINGS");
    private LocationManager mLocationManager;
    private MenuItem mLocationSwitch;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.location.LocationSettingsFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LocationSettingsFragment.this.mLocationSwitch.setChecked(LocationSettingsFragment.this.mLocationManager.isLocationEnabled());
        }
    };

    @Override // com.android.car.settings.common.SettingsFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mLocationSwitch);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mLocationSwitch = new MenuItem.Builder(getContext()).setCheckable().setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.location.-$$Lambda$LocationSettingsFragment$x5HkxZO14TeB8IB7m9yUJfInPLM
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$LocationSettingsFragment(menuItem);
            }
        }).build();
    }

    public /* synthetic */ void lambda$onCreate$0$LocationSettingsFragment(MenuItem menuItem) {
        Utils.updateLocationEnabled(requireContext(), menuItem.isChecked(), UserHandle.myUserId(), 1);
    }

    @Override // com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        enableRotaryScroll();
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.location_settings_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mLocationManager = (LocationManager) context.getSystemService("location");
    }

    @Override // com.android.car.settings.common.SettingsFragment, com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        requireContext().registerReceiver(this.mReceiver, INTENT_FILTER_LOCATION_MODE_CHANGED);
        this.mLocationSwitch.setChecked(this.mLocationManager.isLocationEnabled());
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        requireContext().unregisterReceiver(this.mReceiver);
    }
}
