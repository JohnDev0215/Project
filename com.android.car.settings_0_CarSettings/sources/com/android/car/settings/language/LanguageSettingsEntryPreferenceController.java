package com.android.car.settings.language;

import android.app.ActivityManager;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.RemoteException;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.internal.app.LocaleHelper;
import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
public class LanguageSettingsEntryPreferenceController extends PreferenceController<Preference> {
    public LanguageSettingsEntryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        Locale locale = getConfiguredLocale();
        preference.setSummary(LocaleHelper.getDisplayName(locale, locale, true));
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    private static Locale getConfiguredLocale() {
        try {
            Locale configLocale = ActivityManager.getService().getConfiguration().getLocales().get(0);
            return configLocale != null ? configLocale : Locale.getDefault();
        } catch (RemoteException e) {
            return Locale.getDefault();
        }
    }
}
