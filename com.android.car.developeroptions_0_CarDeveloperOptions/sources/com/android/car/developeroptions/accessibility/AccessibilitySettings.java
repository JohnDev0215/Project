package com.android.car.developeroptions.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.hardware.display.ColorDisplayManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.os.Vibrator;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.KeyCharacterMap;
import android.view.accessibility.AccessibilityManager;
import androidx.core.content.ContextCompat;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.display.DarkUIPreferenceController;
import com.android.car.developeroptions.display.ToggleFontSizePreferenceFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.internal.accessibility.AccessibilityShortcutController;
import com.android.internal.content.PackageMonitor;
import com.android.internal.view.RotationPolicy;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreference;
import com.android.settingslib.accessibility.AccessibilityUtils;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class AccessibilitySettings extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private Preference mAutoclickPreferenceScreen;
    private Preference mCaptioningPreferenceScreen;
    private Preference mDarkUIModePreference;
    private DarkUIPreferenceController mDarkUIPreferenceController;
    private Preference mDisplayDaltonizerPreferenceScreen;
    private Preference mDisplayMagnificationPreferenceScreen;
    private DevicePolicyManager mDpm;
    private Preference mFontSizePreferenceScreen;
    private Preference mHearingAidPreference;
    private AccessibilityHearingAidPreferenceController mHearingAidPreferenceController;
    private ColorInversionPreferenceController mInversionPreferenceController;
    private Preference mLiveCaptionPreference;
    private LiveCaptionPreferenceController mLiveCaptionPreferenceController;
    private int mLongPressTimeoutDefault;
    private ListPreference mSelectLongPressTimeoutPreference;
    private final SettingsContentObserver mSettingsContentObserver;
    private SwitchPreference mToggleDisableAnimationsPreference;
    private SwitchPreference mToggleHighTextContrastPreference;
    private SwitchPreference mToggleInversionPreference;
    private SwitchPreference mToggleLargePointerIconPreference;
    private SwitchPreference mToggleLockScreenRotationPreference;
    private SwitchPreference mToggleMasterMonoPreference;
    private SwitchPreference mTogglePowerButtonEndsCallPreference;
    private Preference mVibrationPreferenceScreen;
    private static final String[] CATEGORIES = {"screen_reader_category", "audio_and_captions_category", "display_category", "interaction_control_category", "experimental_category", "user_installed_services_category"};
    private static final String[] TOGGLE_ANIMATION_TARGETS = {"window_animation_scale", "transition_animation_scale", "animator_duration_scale"};
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.accessibility.AccessibilitySettings.5
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.accessibility_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private final Map<String, String> mLongPressTimeoutValueToTitleMap = new HashMap();
    private final Handler mHandler = new Handler();
    private final Runnable mUpdateRunnable = new Runnable() { // from class: com.android.car.developeroptions.accessibility.AccessibilitySettings.1
        @Override // java.lang.Runnable
        public void run() {
            if (AccessibilitySettings.this.getActivity() != null) {
                AccessibilitySettings.this.updateServicePreferences();
            }
        }
    };
    private final PackageMonitor mSettingsPackageMonitor = new PackageMonitor() { // from class: com.android.car.developeroptions.accessibility.AccessibilitySettings.2
        public void onPackageAdded(String str, int i) {
            sendUpdate();
        }

        public void onPackageAppeared(String str, int i) {
            sendUpdate();
        }

        public void onPackageDisappeared(String str, int i) {
            sendUpdate();
        }

        public void onPackageRemoved(String str, int i) {
            sendUpdate();
        }

        private void sendUpdate() {
            AccessibilitySettings.this.mHandler.postDelayed(AccessibilitySettings.this.mUpdateRunnable, 1000L);
        }
    };
    private final RotationPolicy.RotationPolicyListener mRotationPolicyListener = new RotationPolicy.RotationPolicyListener() { // from class: com.android.car.developeroptions.accessibility.AccessibilitySettings.3
        public void onChange() {
            AccessibilitySettings.this.updateLockScreenRotationCheckbox();
        }
    };
    private final Map<String, PreferenceCategory> mCategoryToPrefCategoryMap = new ArrayMap();
    private final Map<Preference, PreferenceCategory> mServicePreferenceToPreferenceCategoryMap = new ArrayMap();
    private final Map<ComponentName, PreferenceCategory> mPreBundledServiceComponentToCategoryMap = new ArrayMap();

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_uri_accessibility;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 2;
    }

    public AccessibilitySettings() {
        Collection collectionValues = AccessibilityShortcutController.getFrameworkShortcutFeaturesMap().values();
        ArrayList arrayList = new ArrayList(collectionValues.size());
        Iterator it = collectionValues.iterator();
        while (it.hasNext()) {
            arrayList.add(((AccessibilityShortcutController.ToggleableFrameworkFeatureInfo) it.next()).getSettingKey());
        }
        this.mSettingsContentObserver = new SettingsContentObserver(this.mHandler, arrayList) { // from class: com.android.car.developeroptions.accessibility.AccessibilitySettings.4
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                AccessibilitySettings.this.updateAllPreferences();
            }
        };
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.accessibility_settings);
        initializeAllPreferences();
        this.mDpm = (DevicePolicyManager) getActivity().getSystemService("device_policy");
    }

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        AccessibilityHearingAidPreferenceController accessibilityHearingAidPreferenceController = new AccessibilityHearingAidPreferenceController(context, "hearing_aid_preference");
        this.mHearingAidPreferenceController = accessibilityHearingAidPreferenceController;
        accessibilityHearingAidPreferenceController.setFragmentManager(getFragmentManager());
        getLifecycle().addObserver(this.mHearingAidPreferenceController);
        this.mLiveCaptionPreferenceController = new LiveCaptionPreferenceController(context, "live_caption");
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updateAllPreferences();
        this.mSettingsPackageMonitor.register(getActivity(), getActivity().getMainLooper(), false);
        this.mSettingsContentObserver.register(getContentResolver());
        if (RotationPolicy.isRotationSupported(getActivity())) {
            RotationPolicy.registerRotationPolicyListener(getActivity(), this.mRotationPolicyListener);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        this.mSettingsPackageMonitor.unregister();
        this.mSettingsContentObserver.unregister(getContentResolver());
        if (RotationPolicy.isRotationSupported(getActivity())) {
            RotationPolicy.unregisterRotationPolicyListener(getActivity(), this.mRotationPolicyListener);
        }
        super.onPause();
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (this.mSelectLongPressTimeoutPreference != preference) {
            return false;
        }
        handleLongPressTimeoutPreferenceChange((String) obj);
        return true;
    }

    private void handleLongPressTimeoutPreferenceChange(String str) {
        Settings.Secure.putInt(getContentResolver(), "long_press_timeout", Integer.parseInt(str));
        this.mSelectLongPressTimeoutPreference.setSummary(this.mLongPressTimeoutValueToTitleMap.get(str));
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if (this.mToggleHighTextContrastPreference == preference) {
            handleToggleTextContrastPreferenceClick();
            return true;
        }
        if (this.mTogglePowerButtonEndsCallPreference == preference) {
            handleTogglePowerButtonEndsCallPreferenceClick();
            return true;
        }
        if (this.mToggleLockScreenRotationPreference == preference) {
            handleLockScreenRotationPreferenceClick();
            return true;
        }
        if (this.mToggleLargePointerIconPreference == preference) {
            handleToggleLargePointerIconPreferenceClick();
            return true;
        }
        if (this.mToggleDisableAnimationsPreference == preference) {
            handleToggleDisableAnimations();
            return true;
        }
        if (this.mToggleMasterMonoPreference == preference) {
            handleToggleMasterMonoPreferenceClick();
            return true;
        }
        if (this.mHearingAidPreferenceController.handlePreferenceTreeClick(preference)) {
            return true;
        }
        return super.onPreferenceTreeClick(preference);
    }

    public static CharSequence getServiceSummary(Context context, AccessibilityServiceInfo accessibilityServiceInfo, boolean z) {
        String string;
        if (z) {
            string = context.getString(R.string.accessibility_summary_state_enabled);
        } else {
            string = context.getString(R.string.accessibility_summary_state_disabled);
        }
        CharSequence charSequenceLoadSummary = accessibilityServiceInfo.loadSummary(context.getPackageManager());
        return TextUtils.isEmpty(charSequenceLoadSummary) ? string : context.getString(R.string.preference_summary_default_combination, string, charSequenceLoadSummary);
    }

    private void handleToggleTextContrastPreferenceClick() {
        Settings.Secure.putInt(getContentResolver(), "high_text_contrast_enabled", this.mToggleHighTextContrastPreference.isChecked() ? 1 : 0);
    }

    private void handleTogglePowerButtonEndsCallPreferenceClick() {
        Settings.Secure.putInt(getContentResolver(), "incall_power_button_behavior", this.mTogglePowerButtonEndsCallPreference.isChecked() ? 2 : 1);
    }

    private void handleLockScreenRotationPreferenceClick() {
        RotationPolicy.setRotationLockForAccessibility(getActivity(), !this.mToggleLockScreenRotationPreference.isChecked());
    }

    private void handleToggleLargePointerIconPreferenceClick() {
        Settings.Secure.putInt(getContentResolver(), "accessibility_large_pointer_icon", this.mToggleLargePointerIconPreference.isChecked() ? 1 : 0);
    }

    private void handleToggleDisableAnimations() {
        String str = this.mToggleDisableAnimationsPreference.isChecked() ? "0" : "1";
        for (String str2 : TOGGLE_ANIMATION_TARGETS) {
            Settings.Global.putString(getContentResolver(), str2, str);
        }
    }

    private void handleToggleMasterMonoPreferenceClick() {
        Settings.System.putIntForUser(getContentResolver(), "master_mono", this.mToggleMasterMonoPreference.isChecked() ? 1 : 0, -2);
    }

    private void initializeAllPreferences() {
        String[] strArr = CATEGORIES;
        for (int i = 0; i < strArr.length; i++) {
            this.mCategoryToPrefCategoryMap.put(strArr[i], (PreferenceCategory) findPreference(strArr[i]));
        }
        this.mToggleHighTextContrastPreference = (SwitchPreference) findPreference("toggle_high_text_contrast_preference");
        this.mToggleInversionPreference = (SwitchPreference) findPreference("toggle_inversion_preference");
        ColorInversionPreferenceController colorInversionPreferenceController = new ColorInversionPreferenceController(getContext(), "toggle_inversion_preference");
        this.mInversionPreferenceController = colorInversionPreferenceController;
        colorInversionPreferenceController.displayPreference(getPreferenceScreen());
        this.mTogglePowerButtonEndsCallPreference = (SwitchPreference) findPreference("toggle_power_button_ends_call_preference");
        if (!KeyCharacterMap.deviceHasKey(26) || !Utils.isVoiceCapable(getActivity())) {
            this.mCategoryToPrefCategoryMap.get("interaction_control_category").removePreference(this.mTogglePowerButtonEndsCallPreference);
        }
        this.mToggleLockScreenRotationPreference = (SwitchPreference) findPreference("toggle_lock_screen_rotation_preference");
        if (!RotationPolicy.isRotationSupported(getActivity())) {
            this.mCategoryToPrefCategoryMap.get("interaction_control_category").removePreference(this.mToggleLockScreenRotationPreference);
        }
        this.mToggleLargePointerIconPreference = (SwitchPreference) findPreference("toggle_large_pointer_icon");
        this.mToggleDisableAnimationsPreference = (SwitchPreference) findPreference("toggle_disable_animations");
        this.mToggleMasterMonoPreference = (SwitchPreference) findPreference("toggle_master_mono");
        ListPreference listPreference = (ListPreference) findPreference("select_long_press_timeout_preference");
        this.mSelectLongPressTimeoutPreference = listPreference;
        listPreference.setOnPreferenceChangeListener(this);
        if (this.mLongPressTimeoutValueToTitleMap.size() == 0) {
            String[] stringArray = getResources().getStringArray(R.array.long_press_timeout_selector_values);
            this.mLongPressTimeoutDefault = Integer.parseInt(stringArray[0]);
            String[] stringArray2 = getResources().getStringArray(R.array.long_press_timeout_selector_titles);
            int length = stringArray.length;
            for (int i2 = 0; i2 < length; i2++) {
                this.mLongPressTimeoutValueToTitleMap.put(stringArray[i2], stringArray2[i2]);
            }
        }
        this.mHearingAidPreference = findPreference("hearing_aid_preference");
        this.mHearingAidPreferenceController.displayPreference(getPreferenceScreen());
        this.mCaptioningPreferenceScreen = findPreference("captioning_preference_screen");
        this.mLiveCaptionPreference = findPreference("live_caption");
        this.mLiveCaptionPreferenceController.displayPreference(getPreferenceScreen());
        Preference preferenceFindPreference = findPreference("magnification_preference_screen");
        this.mDisplayMagnificationPreferenceScreen = preferenceFindPreference;
        configureMagnificationPreferenceIfNeeded(preferenceFindPreference);
        this.mFontSizePreferenceScreen = findPreference("font_size_preference_screen");
        this.mAutoclickPreferenceScreen = findPreference("autoclick_preference");
        this.mDisplayDaltonizerPreferenceScreen = findPreference("daltonizer_preference");
        this.mVibrationPreferenceScreen = findPreference("vibration_preference_screen");
        this.mDarkUIModePreference = findPreference("dark_ui_mode_accessibility");
        DarkUIPreferenceController darkUIPreferenceController = new DarkUIPreferenceController(getContext(), "dark_ui_mode_accessibility");
        this.mDarkUIPreferenceController = darkUIPreferenceController;
        darkUIPreferenceController.displayPreference(getPreferenceScreen());
        this.mDarkUIModePreference.setSummary(this.mDarkUIPreferenceController.getSummary());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAllPreferences() {
        updateSystemPreferences();
        updateServicePreferences();
    }

    protected void updateServicePreferences() {
        Drawable drawableLoadIcon;
        List<AccessibilityServiceInfo> list;
        boolean z;
        ArrayList arrayList = new ArrayList(this.mServicePreferenceToPreferenceCategoryMap.keySet());
        for (int i = 0; i < arrayList.size(); i++) {
            Preference preference = (Preference) arrayList.get(i);
            this.mServicePreferenceToPreferenceCategoryMap.get(preference).removePreference(preference);
        }
        initializePreBundledServicesMapFromArray("screen_reader_category", R.array.config_preinstalled_screen_reader_services);
        initializePreBundledServicesMapFromArray("audio_and_captions_category", R.array.config_preinstalled_audio_and_caption_services);
        initializePreBundledServicesMapFromArray("display_category", R.array.config_preinstalled_display_services);
        initializePreBundledServicesMapFromArray("interaction_control_category", R.array.config_preinstalled_interaction_control_services);
        AccessibilityManager accessibilityManager = AccessibilityManager.getInstance(getActivity());
        List<AccessibilityServiceInfo> installedAccessibilityServiceList = accessibilityManager.getInstalledAccessibilityServiceList();
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(-1);
        Set<ComponentName> enabledServicesFromSettings = AccessibilityUtils.getEnabledServicesFromSettings(getActivity());
        List permittedAccessibilityServices = this.mDpm.getPermittedAccessibilityServices(UserHandle.myUserId());
        PreferenceCategory preferenceCategory = this.mCategoryToPrefCategoryMap.get("user_installed_services_category");
        if (findPreference("user_installed_services_category") == null) {
            getPreferenceScreen().addPreference(preferenceCategory);
        }
        int size = installedAccessibilityServiceList.size();
        int i2 = 0;
        while (i2 < size) {
            AccessibilityServiceInfo accessibilityServiceInfo = installedAccessibilityServiceList.get(i2);
            ResolveInfo resolveInfo = accessibilityServiceInfo.getResolveInfo();
            RestrictedPreference restrictedPreference = new RestrictedPreference(preferenceCategory.getContext());
            String string = resolveInfo.loadLabel(getPackageManager()).toString();
            if (resolveInfo.getIconResource() == 0) {
                drawableLoadIcon = ContextCompat.getDrawable(getContext(), R.drawable.ic_accessibility_generic);
            } else {
                drawableLoadIcon = resolveInfo.loadIcon(getPackageManager());
            }
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            String str = serviceInfo.packageName;
            List<AccessibilityServiceInfo> list2 = installedAccessibilityServiceList;
            ComponentName componentName = new ComponentName(str, serviceInfo.name);
            restrictedPreference.setKey(componentName.flattenToString());
            restrictedPreference.setTitle(string);
            restrictedPreference.setIconSize(1);
            Utils.setSafeIcon(restrictedPreference, drawableLoadIcon);
            boolean zContains = enabledServicesFromSettings.contains(componentName);
            String strLoadDescription = accessibilityServiceInfo.loadDescription(getPackageManager());
            if (TextUtils.isEmpty(strLoadDescription)) {
                strLoadDescription = getString(R.string.accessibility_service_default_description);
            }
            if (zContains && AccessibilityUtils.hasServiceCrashed(str, serviceInfo.name, enabledAccessibilityServiceList)) {
                restrictedPreference.setSummary(R.string.accessibility_summary_state_stopped);
                strLoadDescription = getString(R.string.accessibility_description_state_stopped);
            } else {
                restrictedPreference.setSummary(getServiceSummary(getContext(), accessibilityServiceInfo, zContains));
            }
            if (!(permittedAccessibilityServices == null || permittedAccessibilityServices.contains(str)) && !zContains) {
                list = enabledAccessibilityServiceList;
                RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfAccessibilityServiceDisallowed = RestrictedLockUtilsInternal.checkIfAccessibilityServiceDisallowed(getActivity(), str, UserHandle.myUserId());
                if (enforcedAdminCheckIfAccessibilityServiceDisallowed != null) {
                    restrictedPreference.setDisabledByAdmin(enforcedAdminCheckIfAccessibilityServiceDisallowed);
                } else {
                    restrictedPreference.setEnabled(false);
                }
                z = true;
            } else {
                list = enabledAccessibilityServiceList;
                z = true;
                restrictedPreference.setEnabled(true);
            }
            restrictedPreference.setFragment(ToggleAccessibilityServicePreferenceFragment.class.getName());
            restrictedPreference.setPersistent(z);
            Bundle extras = restrictedPreference.getExtras();
            Set<ComponentName> set = enabledServicesFromSettings;
            extras.putString("preference_key", restrictedPreference.getKey());
            extras.putBoolean("checked", zContains);
            extras.putString("title", string);
            extras.putParcelable("resolve_info", resolveInfo);
            extras.putString("summary", strLoadDescription);
            String settingsActivityName = accessibilityServiceInfo.getSettingsActivityName();
            if (!TextUtils.isEmpty(settingsActivityName)) {
                extras.putString("settings_title", getString(R.string.accessibility_menu_item_settings));
                extras.putString("settings_component_name", new ComponentName(str, settingsActivityName).flattenToString());
            }
            extras.putParcelable("component_name", componentName);
            PreferenceCategory preferenceCategory2 = this.mPreBundledServiceComponentToCategoryMap.containsKey(componentName) ? this.mPreBundledServiceComponentToCategoryMap.get(componentName) : preferenceCategory;
            restrictedPreference.setOrder(-1);
            preferenceCategory2.addPreference(restrictedPreference);
            this.mServicePreferenceToPreferenceCategoryMap.put(restrictedPreference, preferenceCategory2);
            i2++;
            enabledServicesFromSettings = set;
            installedAccessibilityServiceList = list2;
            enabledAccessibilityServiceList = list;
        }
        if (preferenceCategory.getPreferenceCount() == 0) {
            getPreferenceScreen().removePreference(preferenceCategory);
        }
    }

    private void initializePreBundledServicesMapFromArray(String str, int i) {
        String[] stringArray = getResources().getStringArray(i);
        PreferenceCategory preferenceCategory = this.mCategoryToPrefCategoryMap.get(str);
        for (String str2 : stringArray) {
            this.mPreBundledServiceComponentToCategoryMap.put(ComponentName.unflattenFromString(str2), preferenceCategory);
        }
    }

    protected void updateSystemPreferences() {
        if (ColorDisplayManager.isColorTransformAccelerated(getContext())) {
            PreferenceCategory preferenceCategory = this.mCategoryToPrefCategoryMap.get("experimental_category");
            PreferenceCategory preferenceCategory2 = this.mCategoryToPrefCategoryMap.get("display_category");
            preferenceCategory.removePreference(this.mToggleInversionPreference);
            preferenceCategory.removePreference(this.mDisplayDaltonizerPreferenceScreen);
            this.mDisplayDaltonizerPreferenceScreen.setOrder(this.mDisplayMagnificationPreferenceScreen.getOrder() + 1);
            this.mToggleInversionPreference.setOrder(this.mDisplayDaltonizerPreferenceScreen.getOrder() + 1);
            this.mToggleLargePointerIconPreference.setOrder(this.mToggleInversionPreference.getOrder() + 1);
            this.mToggleDisableAnimationsPreference.setOrder(this.mToggleLargePointerIconPreference.getOrder() + 1);
            findPreference("accessibility_content_timeout_preference_fragment").setOrder(this.mToggleDisableAnimationsPreference.getOrder() + 1);
            this.mToggleInversionPreference.setSummary(R.string.summary_empty);
            preferenceCategory2.addPreference(this.mToggleInversionPreference);
            preferenceCategory2.addPreference(this.mDisplayDaltonizerPreferenceScreen);
        }
        this.mToggleHighTextContrastPreference.setChecked(Settings.Secure.getInt(getContentResolver(), "high_text_contrast_enabled", 0) == 1);
        this.mInversionPreferenceController.updateState(this.mToggleInversionPreference);
        this.mDarkUIPreferenceController.updateState(this.mDarkUIModePreference);
        if (KeyCharacterMap.deviceHasKey(26) && Utils.isVoiceCapable(getActivity())) {
            this.mTogglePowerButtonEndsCallPreference.setChecked(Settings.Secure.getInt(getContentResolver(), "incall_power_button_behavior", 1) == 2);
        }
        updateLockScreenRotationCheckbox();
        this.mToggleLargePointerIconPreference.setChecked(Settings.Secure.getInt(getContentResolver(), "accessibility_large_pointer_icon", 0) != 0);
        updateDisableAnimationsToggle();
        updateMasterMono();
        String strValueOf = String.valueOf(Settings.Secure.getInt(getContentResolver(), "long_press_timeout", this.mLongPressTimeoutDefault));
        this.mSelectLongPressTimeoutPreference.setValue(strValueOf);
        this.mSelectLongPressTimeoutPreference.setSummary(this.mLongPressTimeoutValueToTitleMap.get(strValueOf));
        updateVibrationSummary(this.mVibrationPreferenceScreen);
        this.mHearingAidPreferenceController.updateState(this.mHearingAidPreference);
        this.mLiveCaptionPreferenceController.updateState(this.mLiveCaptionPreference);
        updateFeatureSummary("accessibility_captioning_enabled", this.mCaptioningPreferenceScreen);
        updateFeatureSummary("accessibility_display_daltonizer_enabled", this.mDisplayDaltonizerPreferenceScreen);
        updateMagnificationSummary(this.mDisplayMagnificationPreferenceScreen);
        updateFontSizeSummary(this.mFontSizePreferenceScreen);
        updateAutoclickSummary(this.mAutoclickPreferenceScreen);
        updateAccessibilityTimeoutSummary(getContentResolver(), findPreference("accessibility_content_timeout_preference_fragment"));
        updateAccessibilityTimeoutSummary(getContentResolver(), findPreference("accessibility_control_timeout_preference_fragment"));
    }

    void updateAccessibilityTimeoutSummary(ContentResolver contentResolver, Preference preference) {
        int secureAccessibilityTimeoutValue;
        String[] stringArray = getResources().getStringArray(R.array.accessibility_timeout_summaries);
        int[] intArray = getResources().getIntArray(R.array.accessibility_timeout_selector_values);
        if (preference.getKey().equals("accessibility_content_timeout_preference_fragment")) {
            secureAccessibilityTimeoutValue = AccessibilityTimeoutController.getSecureAccessibilityTimeoutValue(contentResolver, "accessibility_non_interactive_ui_timeout_ms");
        } else {
            secureAccessibilityTimeoutValue = preference.getKey().equals("accessibility_control_timeout_preference_fragment") ? AccessibilityTimeoutController.getSecureAccessibilityTimeoutValue(contentResolver, "accessibility_interactive_ui_timeout_ms") : 0;
        }
        int iIndexOf = Ints.indexOf(intArray, secureAccessibilityTimeoutValue);
        preference.setSummary(stringArray[iIndexOf != -1 ? iIndexOf : 0]);
    }

    private void updateMagnificationSummary(Preference preference) {
        boolean z = Settings.Secure.getInt(getContentResolver(), "accessibility_display_magnification_enabled", 0) == 1;
        boolean z2 = Settings.Secure.getInt(getContentResolver(), "accessibility_display_magnification_navbar_enabled", 0) == 1;
        preference.setSummary((z || z2) ? (z || !z2) ? (!z || z2) ? R.string.accessibility_screen_magnification_state_navbar_gesture : R.string.accessibility_screen_magnification_gestures_title : R.string.accessibility_screen_magnification_navbar_title : R.string.accessibility_feature_state_off);
    }

    private void updateFeatureSummary(String str, Preference preference) {
        preference.setSummary(Settings.Secure.getInt(getContentResolver(), str, 0) == 1 ? R.string.accessibility_feature_state_on : R.string.accessibility_feature_state_off);
    }

    private void updateAutoclickSummary(Preference preference) {
        if (!(Settings.Secure.getInt(getContentResolver(), "accessibility_autoclick_enabled", 0) == 1)) {
            preference.setSummary(R.string.accessibility_feature_state_off);
        } else {
            preference.setSummary(ToggleAutoclickPreferenceFragment.getAutoclickPreferenceSummary(getResources(), Settings.Secure.getInt(getContentResolver(), "accessibility_autoclick_delay", 600)));
        }
    }

    private void updateFontSizeSummary(Preference preference) {
        float f = Settings.System.getFloat(getContext().getContentResolver(), "font_scale", 1.0f);
        Resources resources = getContext().getResources();
        preference.setSummary(resources.getStringArray(R.array.entries_font_size)[ToggleFontSizePreferenceFragment.fontSizeValueToIndex(f, resources.getStringArray(R.array.entryvalues_font_size))]);
    }

    void updateVibrationSummary(Preference preference) {
        Context context = getContext();
        Vibrator vibrator = (Vibrator) context.getSystemService(Vibrator.class);
        int i = Settings.System.getInt(context.getContentResolver(), "ring_vibration_intensity", vibrator.getDefaultRingVibrationIntensity());
        if (Settings.System.getInt(context.getContentResolver(), "vibrate_when_ringing", 0) == 0) {
            i = 0;
        }
        CharSequence intensityString = VibrationIntensityPreferenceController.getIntensityString(context, i);
        int i2 = Settings.System.getInt(context.getContentResolver(), "notification_vibration_intensity", vibrator.getDefaultNotificationVibrationIntensity());
        Object intensityString2 = VibrationIntensityPreferenceController.getIntensityString(context, i2);
        int i3 = Settings.System.getInt(context.getContentResolver(), "haptic_feedback_intensity", vibrator.getDefaultHapticFeedbackIntensity());
        if (Settings.System.getInt(context.getContentResolver(), "haptic_feedback_enabled", 0) == 0) {
            i3 = 0;
        }
        Object intensityString3 = VibrationIntensityPreferenceController.getIntensityString(context, i3);
        if (this.mVibrationPreferenceScreen == null) {
            this.mVibrationPreferenceScreen = findPreference("vibration_preference_screen");
        }
        if (i == i3 && i == i2) {
            this.mVibrationPreferenceScreen.setSummary(intensityString);
        } else {
            this.mVibrationPreferenceScreen.setSummary(getString(R.string.accessibility_vibration_summary, intensityString, intensityString2, intensityString3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLockScreenRotationCheckbox() {
        if (getActivity() != null) {
            this.mToggleLockScreenRotationPreference.setChecked(!RotationPolicy.isRotationLocked(r0));
        }
    }

    private void updateDisableAnimationsToggle() {
        String[] strArr = TOGGLE_ANIMATION_TARGETS;
        int length = strArr.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            }
            if (!TextUtils.equals(Settings.Global.getString(getContentResolver(), strArr[i]), "0")) {
                break;
            } else {
                i++;
            }
        }
        this.mToggleDisableAnimationsPreference.setChecked(z);
    }

    private void updateMasterMono() {
        this.mToggleMasterMonoPreference.setChecked(Settings.System.getIntForUser(getContentResolver(), "master_mono", 0, -2) == 1);
    }

    private static void configureMagnificationPreferenceIfNeeded(Preference preference) {
        Context context = preference.getContext();
        if (MagnificationPreferenceFragment.isApplicable(context.getResources())) {
            return;
        }
        preference.setFragment(ToggleScreenMagnificationPreferenceFragment.class.getName());
        MagnificationGesturesPreferenceController.populateMagnificationGesturesPreferenceExtras(preference.getExtras(), context);
    }
}
