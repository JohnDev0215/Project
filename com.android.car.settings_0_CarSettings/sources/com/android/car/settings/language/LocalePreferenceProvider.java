package com.android.car.settings.language;

import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.R;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceUtil;
import com.android.car.ui.preference.CarUiPreference;
import com.android.internal.app.LocaleHelper;
import com.android.internal.app.LocaleStore;
import com.android.internal.app.SuggestedLocaleAdapter;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class LocalePreferenceProvider {
    private static final Logger LOG = new Logger((Class<?>) LanguagePickerPreferenceController.class);
    static final int TYPE_HEADER_ALL_OTHERS = 1;
    static final int TYPE_HEADER_SUGGESTED = 0;
    static final int TYPE_LOCALE = 2;
    private final Context mContext;
    private SuggestedLocaleAdapter mSuggestedLocaleAdapter;

    public static LocalePreferenceProvider newInstance(Context context, Set<LocaleStore.LocaleInfo> localeInfoSet, LocaleStore.LocaleInfo parentLocale) {
        SuggestedLocaleAdapter adapter = createSuggestedLocaleAdapter(context, localeInfoSet, parentLocale);
        return new LocalePreferenceProvider(context, adapter);
    }

    LocalePreferenceProvider(Context context, SuggestedLocaleAdapter localeAdapter) {
        this.mContext = context;
        this.mSuggestedLocaleAdapter = localeAdapter;
    }

    public void populateBasePreference(PreferenceGroup base, Set<String> ignorables, Preference.OnPreferenceClickListener listener) {
        PreferenceCategory category = null;
        for (int position = 0; position < this.mSuggestedLocaleAdapter.getCount(); position++) {
            Preference preference = getPreference(position, ignorables);
            if (PreferenceUtil.checkPreferenceType(preference, PreferenceCategory.class)) {
                category = (PreferenceCategory) preference;
                base.addPreference(category);
            } else {
                preference.setOnPreferenceClickListener(listener);
                if (category == null) {
                    base.addPreference(preference);
                } else {
                    category.addPreference(preference);
                }
            }
        }
    }

    private Preference getPreference(int position, Set<String> ignorables) {
        int i;
        int type = this.mSuggestedLocaleAdapter.getItemViewType(position);
        if (type != 0) {
            if (type != 1) {
                if (type == 2) {
                    LocaleStore.LocaleInfo info = (LocaleStore.LocaleInfo) this.mSuggestedLocaleAdapter.getItem(position);
                    CarUiPreference preference = new CarUiPreference(this.mContext);
                    preference.setTitle(info.getFullNameNative());
                    Set<LocaleStore.LocaleInfo> subLocales = LocaleStore.getLevelLocales(this.mContext, ignorables, info, true);
                    preference.setShowChevron(subLocales.size() > 1);
                    LocaleUtil.setLocaleArgument(preference, info);
                    return preference;
                }
                LOG.d("Attempting to get unknown type: " + type);
                throw new IllegalStateException("Unknown locale list item type");
            }
        }
        PreferenceCategory category = new PreferenceCategory(this.mContext);
        if (type == 0) {
            i = R.string.language_picker_list_suggested_header;
        } else {
            i = R.string.language_picker_list_all_header;
        }
        category.setTitle(i);
        return category;
    }

    private static SuggestedLocaleAdapter createSuggestedLocaleAdapter(Context context, Set<LocaleStore.LocaleInfo> localeInfoSet, LocaleStore.LocaleInfo parent) {
        boolean countryMode = parent != null;
        Locale displayLocale = countryMode ? parent.getLocale() : Locale.getDefault();
        SuggestedLocaleAdapter adapter = new SuggestedLocaleAdapter(localeInfoSet, countryMode);
        LocaleHelper.LocaleInfoComparator comp = new LocaleHelper.LocaleInfoComparator(displayLocale, countryMode);
        adapter.sort(comp);
        adapter.setDisplayLocale(context, displayLocale);
        return adapter;
    }
}
