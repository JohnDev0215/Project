package com.android.car.developeroptions.wifi;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.NetworkScoreManager;
import android.net.NetworkScorerAppData;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class UseOpenWifiPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener, LifecycleObserver, OnResume, OnPause {
    private final ContentResolver mContentResolver;
    private boolean mDoFeatureSupportedScorersExist;
    private ComponentName mEnableUseWifiComponentName;
    private final Fragment mFragment;
    private final NetworkScoreManager mNetworkScoreManager;
    private Preference mPreference;
    private final SettingObserver mSettingObserver;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "use_open_wifi_automatically";
    }

    public UseOpenWifiPreferenceController(Context context, Fragment fragment, Lifecycle lifecycle) {
        super(context);
        this.mContentResolver = context.getContentResolver();
        this.mFragment = fragment;
        this.mNetworkScoreManager = (NetworkScoreManager) context.getSystemService("network_score");
        this.mSettingObserver = new SettingObserver();
        updateEnableUseWifiComponentName();
        checkForFeatureSupportedScorers();
        lifecycle.addObserver(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateEnableUseWifiComponentName() {
        NetworkScorerAppData activeScorer = this.mNetworkScoreManager.getActiveScorer();
        this.mEnableUseWifiComponentName = activeScorer == null ? null : activeScorer.getEnableUseOpenWifiActivity();
    }

    private void checkForFeatureSupportedScorers() {
        if (this.mEnableUseWifiComponentName != null) {
            this.mDoFeatureSupportedScorersExist = true;
            return;
        }
        Iterator it = this.mNetworkScoreManager.getAllValidScorers().iterator();
        while (it.hasNext()) {
            if (((NetworkScorerAppData) it.next()).getEnableUseOpenWifiActivity() != null) {
                this.mDoFeatureSupportedScorersExist = true;
                return;
            }
        }
        this.mDoFeatureSupportedScorersExist = false;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference("use_open_wifi_automatically");
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        this.mSettingObserver.register(this.mContentResolver);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPause
    public void onPause() {
        this.mSettingObserver.unregister(this.mContentResolver);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mDoFeatureSupportedScorersExist;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (preference instanceof SwitchPreference) {
            SwitchPreference switchPreference = (SwitchPreference) preference;
            boolean z = this.mNetworkScoreManager.getActiveScorerPackage() != null;
            boolean z2 = this.mEnableUseWifiComponentName != null;
            switchPreference.setChecked(isSettingEnabled());
            switchPreference.setVisible(isAvailable());
            switchPreference.setEnabled(z && z2);
            if (!z) {
                switchPreference.setSummary(R.string.use_open_wifi_automatically_summary_scoring_disabled);
            } else if (!z2) {
                switchPreference.setSummary(R.string.use_open_wifi_automatically_summary_scorer_unsupported_disabled);
            } else {
                switchPreference.setSummary(R.string.use_open_wifi_automatically_summary);
            }
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (TextUtils.equals(preference.getKey(), "use_open_wifi_automatically") && isAvailable()) {
            if (isSettingEnabled()) {
                Settings.Global.putString(this.mContentResolver, "use_open_wifi_package", "");
                return true;
            }
            Intent intent = new Intent("android.net.scoring.CUSTOM_ENABLE");
            intent.setComponent(this.mEnableUseWifiComponentName);
            this.mFragment.startActivityForResult(intent, 400);
        }
        return false;
    }

    private boolean isSettingEnabled() {
        String string = Settings.Global.getString(this.mContentResolver, "use_open_wifi_package");
        ComponentName componentName = this.mEnableUseWifiComponentName;
        return TextUtils.equals(string, componentName == null ? null : componentName.getPackageName());
    }

    public boolean onActivityResult(int i, int i2) {
        if (i != 400) {
            return false;
        }
        if (i2 != -1) {
            return true;
        }
        Settings.Global.putString(this.mContentResolver, "use_open_wifi_package", this.mEnableUseWifiComponentName.getPackageName());
        return true;
    }

    class SettingObserver extends ContentObserver {
        private final Uri NETWORK_RECOMMENDATIONS_ENABLED_URI;

        public SettingObserver() {
            super(new Handler(Looper.getMainLooper()));
            this.NETWORK_RECOMMENDATIONS_ENABLED_URI = Settings.Global.getUriFor("network_recommendations_enabled");
        }

        public void register(ContentResolver contentResolver) {
            contentResolver.registerContentObserver(this.NETWORK_RECOMMENDATIONS_ENABLED_URI, false, this);
            onChange(true, this.NETWORK_RECOMMENDATIONS_ENABLED_URI);
        }

        public void unregister(ContentResolver contentResolver) {
            contentResolver.unregisterContentObserver(this);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            if (this.NETWORK_RECOMMENDATIONS_ENABLED_URI.equals(uri)) {
                UseOpenWifiPreferenceController.this.updateEnableUseWifiComponentName();
                UseOpenWifiPreferenceController useOpenWifiPreferenceController = UseOpenWifiPreferenceController.this;
                useOpenWifiPreferenceController.updateState(useOpenWifiPreferenceController.mPreference);
            }
        }
    }
}
