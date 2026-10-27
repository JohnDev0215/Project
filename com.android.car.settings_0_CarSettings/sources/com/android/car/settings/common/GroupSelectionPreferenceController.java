package com.android.car.settings.common;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.TwoStatePreference;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class GroupSelectionPreferenceController extends PreferenceController<PreferenceGroup> {
    protected abstract String getCurrentCheckedKey();

    protected abstract List<TwoStatePreference> getGroupPreferences();

    public GroupSelectionPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public final void updateState(PreferenceGroup preferenceGroup) {
        List<TwoStatePreference> items = getGroupPreferences();
        preferenceGroup.removeAll();
        for (final TwoStatePreference preference : items) {
            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.settings.common.-$$Lambda$GroupSelectionPreferenceController$N1uA22KlpnZ14vA4qo_s3yRNya0
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference2) {
                    return this.f$0.lambda$updateState$0$GroupSelectionPreferenceController(preference, preference2);
                }
            });
            preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.car.settings.common.-$$Lambda$GroupSelectionPreferenceController$Pf1EzRPaFSp6Mfv-0WpVoyBadiE
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public final boolean onPreferenceChange(Preference preference2, Object obj) {
                    return GroupSelectionPreferenceController.lambda$updateState$1(preference2, obj);
                }
            });
            preference.setPersistent(false);
            preferenceGroup.addPreference(preference);
        }
        notifyCheckedKeyChanged();
    }

    public /* synthetic */ boolean lambda$updateState$0$GroupSelectionPreferenceController(TwoStatePreference preference, Preference pref) {
        if (handleGroupItemSelected(preference)) {
            notifyCheckedKeyChanged();
            return true;
        }
        return true;
    }

    static /* synthetic */ boolean lambda$updateState$1(Preference pref, Object o) {
        return false;
    }

    protected boolean handleGroupItemSelected(TwoStatePreference preference) {
        return true;
    }

    protected void notifyCheckedKeyChanged() {
        for (int i = 0; i < getPreference().getPreferenceCount(); i++) {
            TwoStatePreference preference = (TwoStatePreference) getPreference().getPreference(i);
            preference.setChecked(TextUtils.equals(preference.getKey(), getCurrentCheckedKey()));
        }
    }
}
