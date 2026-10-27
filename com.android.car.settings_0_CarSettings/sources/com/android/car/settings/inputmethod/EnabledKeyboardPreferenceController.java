package com.android.car.settings.inputmethod;

import android.app.admin.DevicePolicyManager;
import android.car.drivingstate.CarUxRestrictions;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.ArrayMap;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.car.ui.preference.CarUiPreference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: loaded from: classes5.dex */
public class EnabledKeyboardPreferenceController extends PreferenceController<PreferenceGroup> {
    private static final Logger LOG = new Logger((Class<?>) EnabledKeyboardPreferenceController.class);
    private final DevicePolicyManager mDevicePolicyManager;
    private final InputMethodManager mInputMethodManager;
    private final PackageManager mPackageManager;
    private final Map<String, Preference> mPreferences;

    public EnabledKeyboardPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mPreferences = new ArrayMap();
        this.mPackageManager = context.getPackageManager();
        this.mDevicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        this.mInputMethodManager = (InputMethodManager) context.getSystemService("input_method");
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preferenceGroup) {
        List<Preference> preferencesToDisplay = new ArrayList<>();
        Set<String> preferencesToRemove = new HashSet<>(this.mPreferences.keySet());
        List<InputMethodInfo> inputMethodInfos = InputMethodUtil.getPermittedAndEnabledInputMethodList(this.mInputMethodManager, this.mDevicePolicyManager);
        int size = inputMethodInfos == null ? 0 : inputMethodInfos.size();
        for (int i = 0; i < size; i++) {
            InputMethodInfo inputMethodInfo = inputMethodInfos.get(i);
            Preference preference = createPreference(inputMethodInfo);
            if (this.mPreferences.containsKey(preference.getKey())) {
                Preference displayedPreference = this.mPreferences.get(preference.getKey());
                if (arePreferencesDifferent(displayedPreference, preference)) {
                    preferencesToDisplay.add(preference);
                } else {
                    preferencesToRemove.remove(preference.getKey());
                }
            } else {
                preferencesToDisplay.add(preference);
            }
        }
        updatePreferenceGroup(preferenceGroup, preferencesToDisplay, preferencesToRemove);
    }

    private void updatePreferenceGroup(PreferenceGroup preferenceGroup, List<Preference> preferencesToDisplay, Set<String> preferencesToRemove) {
        Collections.sort(preferencesToDisplay, Comparator.comparing(new Function() { // from class: com.android.car.settings.inputmethod.-$$Lambda$EnabledKeyboardPreferenceController$HkMYf2txBkdWXBQ5a4-NL0cuyw4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Preference) obj).getTitle().toString();
            }
        }).thenComparing(new Function() { // from class: com.android.car.settings.inputmethod.-$$Lambda$EnabledKeyboardPreferenceController$5AmmES_U-Ctgbt-fS_zFvgUqWt4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Preference) obj).getSummary().toString();
            }
        }));
        for (String key : preferencesToRemove) {
            preferenceGroup.removePreference(this.mPreferences.get(key));
            this.mPreferences.remove(key);
        }
        for (int i = 0; i < preferencesToDisplay.size(); i++) {
            Preference pref = preferencesToDisplay.get(i);
            pref.setOrder(i);
            this.mPreferences.put(pref.getKey(), pref);
            preferenceGroup.addPreference(pref);
        }
    }

    private Preference createPreference(final InputMethodInfo inputMethodInfo) {
        CarUiPreference preference = new CarUiPreference(getContext());
        preference.setKey(String.valueOf(inputMethodInfo.hashCode()));
        preference.setIcon(InputMethodUtil.getPackageIcon(this.mPackageManager, inputMethodInfo));
        preference.setTitle(InputMethodUtil.getPackageLabel(this.mPackageManager, inputMethodInfo));
        preference.setSummary(InputMethodUtil.getSummaryString(getContext(), this.mInputMethodManager, inputMethodInfo));
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.settings.inputmethod.-$$Lambda$EnabledKeyboardPreferenceController$M0eXuz7TKuEreRmGBH7OIYKkZSA
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference2) {
                return this.f$0.lambda$createPreference$2$EnabledKeyboardPreferenceController(inputMethodInfo, preference2);
            }
        });
        return preference;
    }

    public /* synthetic */ boolean lambda$createPreference$2$EnabledKeyboardPreferenceController(InputMethodInfo inputMethodInfo, Preference pref) {
        Intent intent;
        String settingsActivity;
        try {
            intent = new Intent("android.intent.action.MAIN");
            settingsActivity = inputMethodInfo.getSettingsActivity();
        } catch (ActivityNotFoundException e) {
            LOG.d("IME's Settings Activity Not Found. " + e);
        }
        if (settingsActivity == null) {
            LOG.d("IME's Settings Activity not defined.");
            return true;
        }
        intent.setClassName(inputMethodInfo.getPackageName(), settingsActivity);
        getContext().startActivity(intent);
        return true;
    }

    private boolean arePreferencesDifferent(Preference lhs, Preference rhs) {
        return (Objects.equals(lhs.getTitle(), rhs.getTitle()) && Objects.equals(lhs.getSummary(), rhs.getSummary())) ? false : true;
    }
}
