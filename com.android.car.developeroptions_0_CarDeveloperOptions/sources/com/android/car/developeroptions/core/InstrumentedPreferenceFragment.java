package com.android.car.developeroptions.core;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.survey.SurveyMixin;
import com.android.settingslib.core.instrumentation.Instrumentable;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.core.instrumentation.VisibilityLoggerMixin;
import com.android.settingslib.core.lifecycle.ObservablePreferenceFragment;

/* JADX INFO: loaded from: classes.dex */
public abstract class InstrumentedPreferenceFragment extends ObservablePreferenceFragment implements Instrumentable {
    protected MetricsFeatureProvider mMetricsFeatureProvider;
    private VisibilityLoggerMixin mVisibilityLoggerMixin;

    protected int getPreferenceScreenResId() {
        return -1;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
        this.mVisibilityLoggerMixin = new VisibilityLoggerMixin(getMetricsCategory(), this.mMetricsFeatureProvider);
        getSettingsLifecycle().addObserver(this.mVisibilityLoggerMixin);
        getSettingsLifecycle().addObserver(new SurveyMixin(this, getClass().getSimpleName()));
        super.onAttach(context);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        this.mVisibilityLoggerMixin.setSourceMetricsCategory(getActivity());
        super.onResume();
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        int preferenceScreenResId = getPreferenceScreenResId();
        if (preferenceScreenResId > 0) {
            addPreferencesFromResource(preferenceScreenResId);
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void addPreferencesFromResource(int i) {
        super.addPreferencesFromResource(i);
        updateActivityTitleWithScreenTitle(getPreferenceScreen());
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.DialogPreference.TargetFragment
    public <T extends Preference> T findPreference(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        return (T) super.findPreference(charSequence);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Context getPrefContext() {
        return getPreferenceManager().getContext();
    }

    private void updateActivityTitleWithScreenTitle(PreferenceScreen preferenceScreen) {
        if (preferenceScreen != null) {
            CharSequence title = preferenceScreen.getTitle();
            if (!TextUtils.isEmpty(title)) {
                getActivity().setTitle(title);
                return;
            }
            Log.w("InstrumentedPrefFrag", "Screen title missing for fragment " + getClass().getName());
        }
    }
}
