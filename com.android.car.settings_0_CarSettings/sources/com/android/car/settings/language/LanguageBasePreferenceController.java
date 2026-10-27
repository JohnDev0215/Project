package com.android.car.settings.language;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.internal.app.LocalePicker;
import com.android.internal.app.LocaleStore;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public abstract class LanguageBasePreferenceController extends PreferenceController<PreferenceGroup> implements Preference.OnPreferenceClickListener {
    private Set<String> mExclusionSet;
    private LocaleSelectedListener mLocaleSelectedListener;

    public interface LocaleSelectedListener {
        void onLocaleSelected(LocaleStore.LocaleInfo localeInfo);
    }

    protected abstract LocalePreferenceProvider defineLocaleProvider();

    public LanguageBasePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mExclusionSet = new HashSet();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    public void setLocaleSelectedListener(LocaleSelectedListener listener) {
        this.mLocaleSelectedListener = listener;
    }

    public Set<String> getExclusionSet() {
        return this.mExclusionSet;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preferenceGroup) {
        if (preferenceGroup.getPreferenceCount() == 0) {
            defineLocaleProvider().populateBasePreference(preferenceGroup, getExclusionSet(), this);
        }
    }

    protected void handleLocaleWithChildren(LocaleStore.LocaleInfo parentLocaleInfo) {
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        LocaleStore.LocaleInfo localeInfo = LocaleUtil.getLocaleArgument(preference);
        if (localeInfo == null) {
            return false;
        }
        if (localeInfo.getParent() == null) {
            Set<LocaleStore.LocaleInfo> subLocales = LocaleStore.getLevelLocales(getContext(), getExclusionSet(), localeInfo, true);
            if (subLocales.size() > 1) {
                handleLocaleWithChildren(localeInfo);
                return true;
            }
            if (subLocales.size() < 1) {
                return false;
            }
            localeInfo = subLocales.iterator().next();
        }
        LocalePicker.updateLocale(localeInfo.getLocale());
        LocaleSelectedListener localeSelectedListener = this.mLocaleSelectedListener;
        if (localeSelectedListener != null) {
            localeSelectedListener.onLocaleSelected(localeInfo);
        }
        getFragmentController().goBack();
        return true;
    }
}
