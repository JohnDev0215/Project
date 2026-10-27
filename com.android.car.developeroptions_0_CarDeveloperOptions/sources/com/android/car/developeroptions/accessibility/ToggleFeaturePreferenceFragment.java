package com.android.car.developeroptions.accessibility;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.car.developeroptions.widget.ToggleSwitch;
import com.android.settingslib.widget.FooterPreference;

/* JADX INFO: loaded from: classes.dex */
public abstract class ToggleFeaturePreferenceFragment extends SettingsPreferenceFragment {
    protected String mPreferenceKey;
    protected Intent mSettingsIntent;
    protected CharSequence mSettingsTitle;
    protected SwitchBar mSwitchBar;
    protected ToggleSwitch mToggleSwitch;

    protected void onInstallSwitchBarToggleSwitch() {
    }

    protected void onRemoveSwitchBarToggleSwitch() {
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getPreferenceScreenResId() <= 0) {
            setPreferenceScreen(getPreferenceManager().createPreferenceScreen(getActivity()));
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        SwitchBar switchBar = ((SettingsActivity) getActivity()).getSwitchBar();
        this.mSwitchBar = switchBar;
        updateSwitchBarText(switchBar);
        this.mToggleSwitch = this.mSwitchBar.getSwitch();
        onProcessArguments(getArguments());
        if (this.mSettingsTitle == null || this.mSettingsIntent == null) {
            return;
        }
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        Preference preference = new Preference(preferenceScreen.getContext());
        preference.setTitle(this.mSettingsTitle);
        preference.setIconSpaceReserved(true);
        preference.setIntent(this.mSettingsIntent);
        preferenceScreen.addPreference(preference);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        installActionBarToggleSwitch();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        removeActionBarToggleSwitch();
    }

    protected void updateSwitchBarText(SwitchBar switchBar) {
        switchBar.setSwitchBarText(R.string.accessibility_service_master_switch_title, R.string.accessibility_service_master_switch_title);
    }

    private void installActionBarToggleSwitch() {
        this.mSwitchBar.show();
        onInstallSwitchBarToggleSwitch();
    }

    private void removeActionBarToggleSwitch() {
        this.mToggleSwitch.setOnBeforeCheckedChangeListener(null);
        onRemoveSwitchBarToggleSwitch();
        this.mSwitchBar.hide();
    }

    public void setTitle(String str) {
        getActivity().setTitle(str);
    }

    protected void onProcessArguments(Bundle bundle) {
        this.mPreferenceKey = bundle.getString("preference_key");
        if (bundle.containsKey("checked")) {
            this.mSwitchBar.setCheckedInternal(bundle.getBoolean("checked"));
        }
        if (bundle.containsKey("resolve_info")) {
            getActivity().setTitle(((ResolveInfo) bundle.getParcelable("resolve_info")).loadLabel(getPackageManager()).toString());
        } else if (bundle.containsKey("title")) {
            setTitle(bundle.getString("title"));
        }
        if (bundle.containsKey("summary_res")) {
            createFooterPreference(getText(bundle.getInt("summary_res")));
        } else if (bundle.containsKey("summary")) {
            createFooterPreference(bundle.getCharSequence("summary"));
        }
    }

    private void createFooterPreference(CharSequence charSequence) {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        FooterPreference.Builder builder = new FooterPreference.Builder(getActivity());
        builder.setTitle(charSequence);
        preferenceScreen.addPreference(builder.build());
    }
}
