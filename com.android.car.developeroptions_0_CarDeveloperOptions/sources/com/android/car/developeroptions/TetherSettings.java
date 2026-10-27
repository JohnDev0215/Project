package com.android.car.developeroptions;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothPan;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.SearchIndexableResource;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.datausage.DataSaverBackend;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.wifi.tether.WifiTetherPreferenceController;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public class TetherSettings extends RestrictedSettingsFragment implements DataSaverBackend.Listener {
    static final String KEY_ENABLE_BLUETOOTH_TETHERING = "enable_bluetooth_tethering";
    static final String KEY_TETHER_PREFS_SCREEN = "tether_prefs_screen";
    static final String KEY_USB_TETHER_SETTINGS = "usb_tether_settings";
    static final String KEY_WIFI_TETHER = "wifi_tether";
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.TetherSettings.2
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.tether_prefs;
            return Arrays.asList(searchIndexableResource);
        }
    };
    private boolean mBluetoothEnableForTether;
    private AtomicReference<BluetoothPan> mBluetoothPan;
    private String[] mBluetoothRegexs;
    private SwitchPreference mBluetoothTether;
    private ConnectivityManager mCm;
    private DataSaverBackend mDataSaverBackend;
    private boolean mDataSaverEnabled;
    private Preference mDataSaverFooter;
    private Handler mHandler;
    private boolean mMassStorageActive;
    private BluetoothProfile.ServiceListener mProfileServiceListener;
    private OnStartTetheringCallback mStartTetheringCallback;
    private BroadcastReceiver mTetherChangeReceiver;
    private boolean mUnavailable;
    private boolean mUsbConnected;
    private String[] mUsbRegexs;
    private SwitchPreference mUsbTether;
    private WifiTetherPreferenceController mWifiTetherPreferenceController;

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_tether;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 90;
    }

    @Override // com.android.car.developeroptions.datausage.DataSaverBackend.Listener
    public void onBlacklistStatusChanged(int i, boolean z) {
    }

    @Override // com.android.car.developeroptions.datausage.DataSaverBackend.Listener
    public void onWhitelistStatusChanged(int i, boolean z) {
    }

    public TetherSettings() {
        super("no_config_tethering");
        this.mBluetoothPan = new AtomicReference<>();
        this.mHandler = new Handler();
        this.mProfileServiceListener = new BluetoothProfile.ServiceListener() { // from class: com.android.car.developeroptions.TetherSettings.1
            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                TetherSettings.this.mBluetoothPan.set((BluetoothPan) bluetoothProfile);
            }

            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceDisconnected(int i) {
                TetherSettings.this.mBluetoothPan.set(null);
            }
        };
    }

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mWifiTetherPreferenceController = new WifiTetherPreferenceController(context, getSettingsLifecycle());
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.tether_prefs);
        DataSaverBackend dataSaverBackend = new DataSaverBackend(getContext());
        this.mDataSaverBackend = dataSaverBackend;
        this.mDataSaverEnabled = dataSaverBackend.isDataSaverEnabled();
        this.mDataSaverFooter = findPreference("disabled_on_data_saver");
        setIfOnlyAvailableForAdmins(true);
        if (isUiRestricted()) {
            this.mUnavailable = true;
            getPreferenceScreen().removeAll();
            return;
        }
        FragmentActivity activity = getActivity();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            defaultAdapter.getProfileProxy(activity.getApplicationContext(), this.mProfileServiceListener, 5);
        }
        this.mUsbTether = (SwitchPreference) findPreference(KEY_USB_TETHER_SETTINGS);
        this.mBluetoothTether = (SwitchPreference) findPreference(KEY_ENABLE_BLUETOOTH_TETHERING);
        this.mDataSaverBackend.addListener(this);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
        this.mCm = connectivityManager;
        this.mUsbRegexs = connectivityManager.getTetherableUsbRegexs();
        this.mBluetoothRegexs = this.mCm.getTetherableBluetoothRegexs();
        boolean z = this.mUsbRegexs.length != 0;
        boolean z2 = this.mBluetoothRegexs.length != 0;
        if (!z || Utils.isMonkeyRunning()) {
            getPreferenceScreen().removePreference(this.mUsbTether);
        }
        this.mWifiTetherPreferenceController.displayPreference(getPreferenceScreen());
        if (!z2) {
            getPreferenceScreen().removePreference(this.mBluetoothTether);
        } else {
            BluetoothPan bluetoothPan = this.mBluetoothPan.get();
            if (bluetoothPan != null && bluetoothPan.isTetheringOn()) {
                this.mBluetoothTether.setChecked(true);
            } else {
                this.mBluetoothTether.setChecked(false);
            }
        }
        onDataSaverChanged(this.mDataSaverBackend.isDataSaverEnabled());
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.mDataSaverBackend.remListener(this);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothProfile bluetoothProfile = (BluetoothProfile) this.mBluetoothPan.getAndSet(null);
        if (bluetoothProfile != null && defaultAdapter != null) {
            defaultAdapter.closeProfileProxy(5, bluetoothProfile);
        }
        super.onDestroy();
    }

    @Override // com.android.car.developeroptions.datausage.DataSaverBackend.Listener
    public void onDataSaverChanged(boolean z) {
        this.mDataSaverEnabled = z;
        this.mUsbTether.setEnabled(!z);
        this.mBluetoothTether.setEnabled(!this.mDataSaverEnabled);
        this.mDataSaverFooter.setVisible(this.mDataSaverEnabled);
    }

    private class TetherChangeReceiver extends BroadcastReceiver {
        private TetherChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.net.conn.TETHER_STATE_CHANGED")) {
                ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("availableArray");
                ArrayList<String> stringArrayListExtra2 = intent.getStringArrayListExtra("tetherArray");
                ArrayList<String> stringArrayListExtra3 = intent.getStringArrayListExtra("erroredArray");
                TetherSettings.this.updateState((String[]) stringArrayListExtra.toArray(new String[stringArrayListExtra.size()]), (String[]) stringArrayListExtra2.toArray(new String[stringArrayListExtra2.size()]), (String[]) stringArrayListExtra3.toArray(new String[stringArrayListExtra3.size()]));
                return;
            }
            if (action.equals("android.intent.action.MEDIA_SHARED")) {
                TetherSettings.this.mMassStorageActive = true;
                TetherSettings.this.updateState();
                return;
            }
            if (action.equals("android.intent.action.MEDIA_UNSHARED")) {
                TetherSettings.this.mMassStorageActive = false;
                TetherSettings.this.updateState();
                return;
            }
            if (action.equals("android.hardware.usb.action.USB_STATE")) {
                TetherSettings.this.mUsbConnected = intent.getBooleanExtra("connected", false);
                TetherSettings.this.updateState();
            } else if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                if (TetherSettings.this.mBluetoothEnableForTether) {
                    int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                    if (intExtra == Integer.MIN_VALUE || intExtra == 10) {
                        TetherSettings.this.mBluetoothEnableForTether = false;
                    } else if (intExtra == 12) {
                        TetherSettings.this.startTethering(2);
                        TetherSettings.this.mBluetoothEnableForTether = false;
                    }
                }
                TetherSettings.this.updateState();
            }
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.mUnavailable) {
            if (!isUiRestrictedByOnlyAdmin()) {
                getEmptyTextView().setText(R.string.tethering_settings_not_available);
            }
            getPreferenceScreen().removeAll();
            return;
        }
        FragmentActivity activity = getActivity();
        this.mStartTetheringCallback = new OnStartTetheringCallback(this);
        this.mMassStorageActive = "shared".equals(Environment.getExternalStorageState());
        this.mTetherChangeReceiver = new TetherChangeReceiver();
        Intent intentRegisterReceiver = activity.registerReceiver(this.mTetherChangeReceiver, new IntentFilter("android.net.conn.TETHER_STATE_CHANGED"));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_STATE");
        activity.registerReceiver(this.mTetherChangeReceiver, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.MEDIA_SHARED");
        intentFilter2.addAction("android.intent.action.MEDIA_UNSHARED");
        intentFilter2.addDataScheme("file");
        activity.registerReceiver(this.mTetherChangeReceiver, intentFilter2);
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        activity.registerReceiver(this.mTetherChangeReceiver, intentFilter3);
        if (intentRegisterReceiver != null) {
            this.mTetherChangeReceiver.onReceive(activity, intentRegisterReceiver);
        }
        updateState();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (this.mUnavailable) {
            return;
        }
        getActivity().unregisterReceiver(this.mTetherChangeReceiver);
        this.mTetherChangeReceiver = null;
        this.mStartTetheringCallback = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateState() {
        updateState(this.mCm.getTetherableIfaces(), this.mCm.getTetheredIfaces(), this.mCm.getTetheringErroredIfaces());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateState(String[] strArr, String[] strArr2, String[] strArr3) {
        updateUsbState(strArr, strArr2, strArr3);
        updateBluetoothState();
    }

    private void updateUsbState(String[] strArr, String[] strArr2, String[] strArr3) {
        boolean z = this.mUsbConnected && !this.mMassStorageActive;
        int lastTetherError = 0;
        for (String str : strArr) {
            for (String str2 : this.mUsbRegexs) {
                if (str.matches(str2) && lastTetherError == 0) {
                    lastTetherError = this.mCm.getLastTetherError(str);
                }
            }
        }
        boolean z2 = false;
        for (String str3 : strArr2) {
            for (String str4 : this.mUsbRegexs) {
                if (str3.matches(str4)) {
                    z2 = true;
                }
            }
        }
        for (String str5 : strArr3) {
            for (String str6 : this.mUsbRegexs) {
                str5.matches(str6);
            }
        }
        if (z2) {
            this.mUsbTether.setEnabled(!this.mDataSaverEnabled);
            this.mUsbTether.setChecked(true);
        } else if (z) {
            this.mUsbTether.setEnabled(!this.mDataSaverEnabled);
            this.mUsbTether.setChecked(false);
        } else {
            this.mUsbTether.setEnabled(false);
            this.mUsbTether.setChecked(false);
        }
    }

    private void updateBluetoothState() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            return;
        }
        int state = defaultAdapter.getState();
        if (state == 13) {
            this.mBluetoothTether.setEnabled(false);
            return;
        }
        if (state == 11) {
            this.mBluetoothTether.setEnabled(false);
            return;
        }
        BluetoothPan bluetoothPan = this.mBluetoothPan.get();
        if (state == 12 && bluetoothPan != null && bluetoothPan.isTetheringOn()) {
            this.mBluetoothTether.setChecked(true);
            this.mBluetoothTether.setEnabled(!this.mDataSaverEnabled);
        } else {
            this.mBluetoothTether.setEnabled(!this.mDataSaverEnabled);
            this.mBluetoothTether.setChecked(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startTethering(int i) {
        if (i == 2) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter.getState() == 10) {
                this.mBluetoothEnableForTether = true;
                defaultAdapter.enable();
                this.mBluetoothTether.setEnabled(false);
                return;
            }
        }
        this.mCm.startTethering(i, true, this.mStartTetheringCallback, this.mHandler);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        SwitchPreference switchPreference = this.mUsbTether;
        if (preference == switchPreference) {
            if (switchPreference.isChecked()) {
                startTethering(1);
            } else {
                this.mCm.stopTethering(1);
            }
        } else {
            SwitchPreference switchPreference2 = this.mBluetoothTether;
            if (preference == switchPreference2) {
                if (switchPreference2.isChecked()) {
                    startTethering(2);
                } else {
                    this.mCm.stopTethering(2);
                }
            }
        }
        return super.onPreferenceTreeClick(preference);
    }

    private static final class OnStartTetheringCallback extends ConnectivityManager.OnStartTetheringCallback {
        final WeakReference<TetherSettings> mTetherSettings;

        OnStartTetheringCallback(TetherSettings tetherSettings) {
            this.mTetherSettings = new WeakReference<>(tetherSettings);
        }

        public void onTetheringStarted() {
            update();
        }

        public void onTetheringFailed() {
            update();
        }

        private void update() {
            TetherSettings tetherSettings = this.mTetherSettings.get();
            if (tetherSettings != null) {
                tetherSettings.updateState();
            }
        }
    }
}
