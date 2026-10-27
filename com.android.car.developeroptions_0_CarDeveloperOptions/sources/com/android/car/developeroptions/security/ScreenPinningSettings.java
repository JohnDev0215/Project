package com.android.car.developeroptions.security;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.internal.widget.LockPatternUtils;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ScreenPinningSettings extends SettingsPreferenceFragment implements SwitchBar.OnSwitchChangeListener {
    private static final CharSequence KEY_USE_SCREEN_LOCK = "use_screen_lock";
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.security.ScreenPinningSettings.2
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.screen_pinning_settings;
            return Arrays.asList(searchIndexableResource);
        }
    };
    private LockPatternUtils mLockPatternUtils;
    private SwitchBar mSwitchBar;
    private SwitchPreference mUseScreenLock;

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_screen_pinning;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 86;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        settingsActivity.setTitle(R.string.screen_pinning_title);
        this.mLockPatternUtils = new LockPatternUtils(settingsActivity);
        SwitchBar switchBar = settingsActivity.getSwitchBar();
        this.mSwitchBar = switchBar;
        switchBar.addOnSwitchChangeListener(this);
        this.mSwitchBar.show();
        this.mSwitchBar.setChecked(isLockToAppEnabled(getActivity()));
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.list_container);
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.screen_pinning_instructions, viewGroup, false);
        viewGroup.addView(viewInflate);
        setEmptyView(viewInflate);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mSwitchBar.removeOnSwitchChangeListener(this);
        this.mSwitchBar.hide();
    }

    private static boolean isLockToAppEnabled(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "lock_to_app_enabled", 0) != 0;
    }

    private void setLockToAppEnabled(boolean z) {
        Settings.System.putInt(getContentResolver(), "lock_to_app_enabled", z ? 1 : 0);
        if (z) {
            setScreenLockUsedSetting(isScreenLockUsed());
        }
    }

    private boolean isScreenLockUsed() {
        return Settings.Secure.getInt(getContentResolver(), "lock_to_app_exit_locked", getCurrentSecurityTitle() != R.string.screen_pinning_unlock_none ? 1 : 0) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setScreenLockUsed(boolean z) {
        if (z && new LockPatternUtils(getActivity()).getKeyguardStoredPasswordQuality(UserHandle.myUserId()) == 0) {
            Intent intent = new Intent("android.app.action.SET_NEW_PASSWORD");
            intent.putExtra("minimum_quality", 65536);
            startActivityForResult(intent, 43);
            return false;
        }
        setScreenLockUsedSetting(z);
        return true;
    }

    private void setScreenLockUsedSetting(boolean z) {
        Settings.Secure.putInt(getContentResolver(), "lock_to_app_exit_locked", z ? 1 : 0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 43) {
            boolean z = new LockPatternUtils(getActivity()).getKeyguardStoredPasswordQuality(UserHandle.myUserId()) != 0;
            setScreenLockUsed(z);
            this.mUseScreenLock.setChecked(z);
        }
    }

    private int getCurrentSecurityTitle() {
        int keyguardStoredPasswordQuality = this.mLockPatternUtils.getKeyguardStoredPasswordQuality(UserHandle.myUserId());
        return keyguardStoredPasswordQuality != 65536 ? (keyguardStoredPasswordQuality == 131072 || keyguardStoredPasswordQuality == 196608) ? R.string.screen_pinning_unlock_pin : (keyguardStoredPasswordQuality == 262144 || keyguardStoredPasswordQuality == 327680 || keyguardStoredPasswordQuality == 393216 || keyguardStoredPasswordQuality == 524288) ? R.string.screen_pinning_unlock_password : R.string.screen_pinning_unlock_none : this.mLockPatternUtils.isLockPatternEnabled(UserHandle.myUserId()) ? R.string.screen_pinning_unlock_pattern : R.string.screen_pinning_unlock_none;
    }

    @Override // com.android.car.developeroptions.widget.SwitchBar.OnSwitchChangeListener
    public void onSwitchChanged(Switch r1, boolean z) {
        setLockToAppEnabled(z);
        updateDisplay();
    }

    public void updateDisplay() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        if (preferenceScreen != null) {
            preferenceScreen.removeAll();
        }
        if (isLockToAppEnabled(getActivity())) {
            addPreferencesFromResource(R.xml.screen_pinning_settings);
            SwitchPreference switchPreference = (SwitchPreference) getPreferenceScreen().findPreference(KEY_USE_SCREEN_LOCK);
            this.mUseScreenLock = switchPreference;
            switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.car.developeroptions.security.ScreenPinningSettings.1
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public boolean onPreferenceChange(Preference preference, Object obj) {
                    return ScreenPinningSettings.this.setScreenLockUsed(((Boolean) obj).booleanValue());
                }
            });
            this.mUseScreenLock.setChecked(isScreenLockUsed());
            this.mUseScreenLock.setTitle(getCurrentSecurityTitle());
        }
    }
}
