package com.android.car.developeroptions.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.google.android.setupdesign.GlifPreferenceLayout;

/* JADX INFO: loaded from: classes.dex */
public class AccessibilitySettingsForSetupWizard extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private Preference mDisplayMagnificationPreference;
    private Preference mScreenReaderPreference;
    private Preference mSelectToSpeakPreference;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 367;
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        return false;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        GlifPreferenceLayout glifPreferenceLayout = (GlifPreferenceLayout) view;
        glifPreferenceLayout.setDividerInsets(Integer.MAX_VALUE, 0);
        glifPreferenceLayout.setHeaderText(R.string.vision_settings_title);
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public RecyclerView onCreateRecyclerView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return ((GlifPreferenceLayout) viewGroup).onCreateRecyclerView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.accessibility_settings_for_setup_wizard);
        this.mDisplayMagnificationPreference = findPreference("screen_magnification_preference");
        this.mScreenReaderPreference = findPreference("screen_reader_preference");
        this.mSelectToSpeakPreference = findPreference("select_to_speak_preference");
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updateAccessibilityServicePreference(this.mScreenReaderPreference, findService("com.google.android.marvin.talkback", "com.google.android.marvin.talkback.TalkBackService"));
        updateAccessibilityServicePreference(this.mSelectToSpeakPreference, findService("com.google.android.marvin.talkback", "com.google.android.accessibility.selecttospeak.SelectToSpeakService"));
        configureMagnificationPreferenceIfNeeded(this.mDisplayMagnificationPreference);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(false);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        Preference preference2 = this.mDisplayMagnificationPreference;
        if (preference2 == preference) {
            preference2.getExtras().putBoolean("from_suw", true);
        }
        return super.onPreferenceTreeClick(preference);
    }

    private AccessibilityServiceInfo findService(String str, String str2) {
        for (AccessibilityServiceInfo accessibilityServiceInfo : ((AccessibilityManager) getActivity().getSystemService(AccessibilityManager.class)).getInstalledAccessibilityServiceList()) {
            ServiceInfo serviceInfo = accessibilityServiceInfo.getResolveInfo().serviceInfo;
            if (str.equals(serviceInfo.packageName) && str2.equals(serviceInfo.name)) {
                return accessibilityServiceInfo;
            }
        }
        return null;
    }

    private void updateAccessibilityServicePreference(Preference preference, AccessibilityServiceInfo accessibilityServiceInfo) {
        if (accessibilityServiceInfo == null) {
            getPreferenceScreen().removePreference(preference);
            return;
        }
        ServiceInfo serviceInfo = accessibilityServiceInfo.getResolveInfo().serviceInfo;
        String string = accessibilityServiceInfo.getResolveInfo().loadLabel(getPackageManager()).toString();
        preference.setTitle(string);
        ComponentName componentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
        preference.setKey(componentName.flattenToString());
        Bundle extras = preference.getExtras();
        extras.putParcelable("component_name", componentName);
        extras.putString("preference_key", preference.getKey());
        extras.putString("title", string);
        String strLoadDescription = accessibilityServiceInfo.loadDescription(getPackageManager());
        if (TextUtils.isEmpty(strLoadDescription)) {
            strLoadDescription = getString(R.string.accessibility_service_default_description);
        }
        extras.putString("summary", strLoadDescription);
    }

    private static void configureMagnificationPreferenceIfNeeded(Preference preference) {
        Context context = preference.getContext();
        if (MagnificationPreferenceFragment.isApplicable(context.getResources())) {
            return;
        }
        preference.setFragment(ToggleScreenMagnificationPreferenceFragmentForSetupWizard.class.getName());
        MagnificationGesturesPreferenceController.populateMagnificationGesturesPreferenceExtras(preference.getExtras(), context);
    }
}
