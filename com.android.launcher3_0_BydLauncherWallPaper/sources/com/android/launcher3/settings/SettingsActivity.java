package com.android.launcher3.settings;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.LauncherFiles;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.config.FeatureFlags;
import com.android.launcher3.graphics.GridOptionsProvider;
import com.android.launcher3.states.RotationHelper;
import com.android.launcher3.uioverrides.plugins.PluginManagerWrapper;
import com.android.launcher3.util.SecureSettingsObserver;

/* JADX INFO: loaded from: classes4.dex */
public class SettingsActivity extends Activity implements PreferenceFragment.OnPreferenceStartFragmentCallback, PreferenceFragment.OnPreferenceStartScreenCallback, SharedPreferences.OnSharedPreferenceChangeListener {
    private static final int DELAY_HIGHLIGHT_DURATION_MILLIS = 600;
    private static final String DEVELOPER_OPTIONS_KEY = "pref_developer_options";
    public static final String EXTRA_FRAGMENT_ARG_KEY = ":settings:fragment_args_key";
    public static final String EXTRA_SHOW_FRAGMENT_ARGS = ":settings:show_fragment_args";
    private static final String FLAGS_PREFERENCE_KEY = "flag_toggler";
    public static final String GRID_OPTIONS_PREFERENCE_KEY = "pref_grid_options";
    private static final String NOTIFICATION_DOTS_PREFERENCE_KEY = "pref_icon_badging";
    private static final String NOTIFICATION_ENABLED_LISTENERS = "enabled_notification_listeners";
    public static final String SAVE_HIGHLIGHTED_KEY = "android:preference_highlighted";

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            String stringExtra = getIntent().getStringExtra(EXTRA_FRAGMENT_ARG_KEY);
            if (!TextUtils.isEmpty(stringExtra)) {
                bundle.putString(EXTRA_FRAGMENT_ARG_KEY, stringExtra);
            }
            getFragmentManager().beginTransaction().replace(16908290, Fragment.instantiate(this, getString(R.string.settings_fragment_name), bundle)).commit();
        }
        Utilities.getPrefs(getApplicationContext()).registerOnSharedPreferenceChangeListener(this);
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (GRID_OPTIONS_PREFERENCE_KEY.equals(key)) {
            ComponentName componentName = new ComponentName(getApplicationContext(), (Class<?>) GridOptionsProvider.class);
            Context applicationContext = getApplicationContext();
            int componentEnabledSetting = applicationContext.getPackageManager().getComponentEnabledSetting(componentName);
            int i = Utilities.getPrefs(applicationContext).getBoolean(GRID_OPTIONS_PREFERENCE_KEY, false) ? 1 : 2;
            if (componentEnabledSetting != i) {
                applicationContext.getPackageManager().setComponentEnabledSetting(componentName, i, 1);
            }
        }
    }

    private boolean startFragment(String fragment, Bundle args, String key) {
        if (Utilities.ATLEAST_P && getFragmentManager().isStateSaved()) {
            return false;
        }
        Fragment fragmentInstantiate = Fragment.instantiate(this, fragment, args);
        if (fragmentInstantiate instanceof DialogFragment) {
            ((DialogFragment) fragmentInstantiate).show(getFragmentManager(), key);
            return true;
        }
        getFragmentManager().beginTransaction().replace(16908290, fragmentInstantiate).addToBackStack(key).commit();
        return true;
    }

    @Override // androidx.preference.PreferenceFragment.OnPreferenceStartFragmentCallback
    public boolean onPreferenceStartFragment(PreferenceFragment preferenceFragment, Preference pref) {
        return startFragment(pref.getFragment(), pref.getExtras(), pref.getKey());
    }

    @Override // androidx.preference.PreferenceFragment.OnPreferenceStartScreenCallback
    public boolean onPreferenceStartScreen(PreferenceFragment caller, PreferenceScreen pref) {
        Bundle bundle = new Bundle();
        bundle.putString("androidx.preference.PreferenceFragmentCompat.PREFERENCE_ROOT", pref.getKey());
        return startFragment(getString(R.string.settings_fragment_name), bundle, pref.getKey());
    }

    public static class LauncherSettingsFragment extends PreferenceFragment {
        private String mHighLightKey;
        private SecureSettingsObserver mNotificationDotsObserver;
        private boolean mPreferenceHighlighted = false;

        protected String getParentKeyForPref(String key) {
            return null;
        }

        @Override // androidx.preference.PreferenceFragment
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            Bundle arguments = getArguments();
            String string = arguments == null ? null : arguments.getString(SettingsActivity.EXTRA_FRAGMENT_ARG_KEY);
            this.mHighLightKey = string;
            if (rootKey == null && !TextUtils.isEmpty(string)) {
                rootKey = getParentKeyForPref(this.mHighLightKey);
            }
            if (savedInstanceState != null) {
                this.mPreferenceHighlighted = savedInstanceState.getBoolean(SettingsActivity.SAVE_HIGHLIGHTED_KEY);
            }
            getPreferenceManager().setSharedPreferencesName(LauncherFiles.SHARED_PREFERENCES_KEY);
            setPreferencesFromResource(R.xml.launcher_preferences, rootKey);
            PreferenceScreen preferenceScreen = getPreferenceScreen();
            for (int preferenceCount = preferenceScreen.getPreferenceCount() - 1; preferenceCount >= 0; preferenceCount--) {
                Preference preference = preferenceScreen.getPreference(preferenceCount);
                if (!initPreference(preference)) {
                    preferenceScreen.removePreference(preference);
                }
            }
        }

        @Override // androidx.preference.PreferenceFragment, android.app.Fragment
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putBoolean(SettingsActivity.SAVE_HIGHLIGHTED_KEY, this.mPreferenceHighlighted);
        }

        protected boolean initPreference(Preference preference) {
            String key = preference.getKey();
            key.hashCode();
            switch (key) {
                case "pref_icon_badging":
                    if (!Utilities.ATLEAST_OREO || !getResources().getBoolean(R.bool.notification_dots_enabled)) {
                        return false;
                    }
                    SecureSettingsObserver secureSettingsObserverNewNotificationSettingsObserver = SecureSettingsObserver.newNotificationSettingsObserver(getActivity(), (NotificationDotsPreference) preference);
                    this.mNotificationDotsObserver = secureSettingsObserverNewNotificationSettingsObserver;
                    secureSettingsObserverNewNotificationSettingsObserver.register();
                    this.mNotificationDotsObserver.getResolver().registerContentObserver(Settings.Secure.getUriFor("enabled_notification_listeners"), false, this.mNotificationDotsObserver);
                    this.mNotificationDotsObserver.dispatchOnChange();
                    return true;
                case "pref_developer_options":
                    return FeatureFlags.showFlagTogglerUi(getContext()) || PluginManagerWrapper.hasPlugins(getContext());
                case "pref_allowRotation":
                    if (getResources().getBoolean(R.bool.allow_rotation)) {
                        return false;
                    }
                    preference.setDefaultValue(Boolean.valueOf(RotationHelper.getAllowRotationDefaultValue()));
                    return true;
                case "pref_add_icon_to_home":
                    return Utilities.ATLEAST_OREO;
                case "flag_toggler":
                    return FeatureFlags.showFlagTogglerUi(getContext());
                case "pref_grid_options":
                    return Utilities.isDevelopersOptionsEnabled(getContext()) && Utilities.IS_DEBUG_DEVICE && Utilities.existsStyleWallpapers(getContext());
                default:
                    return true;
            }
        }

        @Override // android.app.Fragment
        public void onResume() {
            PreferenceHighlighter preferenceHighlighterCreateHighlighter;
            super.onResume();
            if (!isAdded() || this.mPreferenceHighlighted || (preferenceHighlighterCreateHighlighter = createHighlighter()) == null) {
                return;
            }
            getView().postDelayed(preferenceHighlighterCreateHighlighter, 600L);
            this.mPreferenceHighlighted = true;
        }

        private PreferenceHighlighter createHighlighter() {
            if (TextUtils.isEmpty(this.mHighLightKey) || getPreferenceScreen() == null) {
                return null;
            }
            RecyclerView listView = getListView();
            int preferenceAdapterPosition = ((PreferenceGroup.PreferencePositionCallback) listView.getAdapter()).getPreferenceAdapterPosition(this.mHighLightKey);
            if (preferenceAdapterPosition >= 0) {
                return new PreferenceHighlighter(listView, preferenceAdapterPosition);
            }
            return null;
        }

        @Override // android.app.Fragment
        public void onDestroy() {
            SecureSettingsObserver secureSettingsObserver = this.mNotificationDotsObserver;
            if (secureSettingsObserver != null) {
                secureSettingsObserver.unregister();
                this.mNotificationDotsObserver = null;
            }
            super.onDestroy();
        }
    }
}
