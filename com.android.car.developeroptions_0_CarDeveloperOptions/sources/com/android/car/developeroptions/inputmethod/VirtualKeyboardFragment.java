package com.android.car.developeroptions.inputmethod;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.inputmethod.InputMethodAndSubtypeUtilCompat;
import com.android.settingslib.inputmethod.InputMethodPreference;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class VirtualKeyboardFragment extends SettingsPreferenceFragment {
    private static final Drawable NO_ICON = new ColorDrawable(0);
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.inputmethod.VirtualKeyboardFragment.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.virtual_keyboard_settings;
            return Arrays.asList(searchIndexableResource);
        }
    };
    private Preference mAddVirtualKeyboardScreen;
    private DevicePolicyManager mDpm;
    private InputMethodManager mImm;
    private final ArrayList<InputMethodPreference> mInputMethodPreferenceList = new ArrayList<>();

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 345;
    }

    @Override // com.android.car.developeroptions.core.InstrumentedPreferenceFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        FragmentActivity activity = getActivity();
        Objects.requireNonNull(activity);
        FragmentActivity fragmentActivity = activity;
        addPreferencesFromResource(R.xml.virtual_keyboard_settings);
        InputMethodManager inputMethodManager = (InputMethodManager) fragmentActivity.getSystemService(InputMethodManager.class);
        Objects.requireNonNull(inputMethodManager);
        this.mImm = inputMethodManager;
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) fragmentActivity.getSystemService(DevicePolicyManager.class);
        Objects.requireNonNull(devicePolicyManager);
        this.mDpm = devicePolicyManager;
        Preference preferenceFindPreference = findPreference("add_virtual_keyboard_screen");
        Objects.requireNonNull(preferenceFindPreference);
        this.mAddVirtualKeyboardScreen = preferenceFindPreference;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updateInputMethodPreferenceViews();
    }

    private void updateInputMethodPreferenceViews() {
        Drawable applicationIcon;
        this.mInputMethodPreferenceList.clear();
        List permittedInputMethodsForCurrentUser = this.mDpm.getPermittedInputMethodsForCurrentUser();
        Context prefContext = getPrefContext();
        List<InputMethodInfo> enabledInputMethodList = this.mImm.getEnabledInputMethodList();
        int size = enabledInputMethodList == null ? 0 : enabledInputMethodList.size();
        for (int i = 0; i < size; i++) {
            InputMethodInfo inputMethodInfo = enabledInputMethodList.get(i);
            boolean z = permittedInputMethodsForCurrentUser == null || permittedInputMethodsForCurrentUser.contains(inputMethodInfo.getPackageName());
            try {
                applicationIcon = getActivity().getPackageManager().getApplicationIcon(inputMethodInfo.getPackageName());
            } catch (Exception unused) {
                applicationIcon = NO_ICON;
            }
            InputMethodPreference inputMethodPreference = new InputMethodPreference(prefContext, inputMethodInfo, false, z, (InputMethodPreference.OnSavePreferenceListener) null);
            inputMethodPreference.setIcon(applicationIcon);
            this.mInputMethodPreferenceList.add(inputMethodPreference);
        }
        final Collator collator = Collator.getInstance();
        this.mInputMethodPreferenceList.sort(new Comparator() { // from class: com.android.car.developeroptions.inputmethod.-$$Lambda$VirtualKeyboardFragment$tXk-tXDkK-vOK6A54331kl3ymo0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((InputMethodPreference) obj).compareTo((InputMethodPreference) obj2, collator);
            }
        });
        getPreferenceScreen().removeAll();
        for (int i2 = 0; i2 < size; i2++) {
            InputMethodPreference inputMethodPreference2 = this.mInputMethodPreferenceList.get(i2);
            inputMethodPreference2.setOrder(i2);
            getPreferenceScreen().addPreference(inputMethodPreference2);
            InputMethodAndSubtypeUtilCompat.removeUnnecessaryNonPersistentPreference(inputMethodPreference2);
            inputMethodPreference2.updatePreferenceViews();
        }
        this.mAddVirtualKeyboardScreen.setIcon(R.drawable.ic_add_24dp);
        this.mAddVirtualKeyboardScreen.setOrder(size);
        getPreferenceScreen().addPreference(this.mAddVirtualKeyboardScreen);
    }
}
