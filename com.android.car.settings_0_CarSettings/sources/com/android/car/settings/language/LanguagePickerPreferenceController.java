package com.android.car.settings.language;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.Build;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.language.LanguageBasePreferenceController;
import com.android.internal.app.LocaleStore;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class LanguagePickerPreferenceController extends LanguageBasePreferenceController {
    public LanguagePickerPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.language.LanguageBasePreferenceController
    protected LocalePreferenceProvider defineLocaleProvider() {
        Set<LocaleStore.LocaleInfo> localeInfoSet = LocaleStore.getLevelLocales(getContext(), getExclusionSet(), (LocaleStore.LocaleInfo) null, true);
        maybeAddPseudoLocale(localeInfoSet);
        return LocalePreferenceProvider.newInstance(getContext(), localeInfoSet, null);
    }

    @Override // com.android.car.settings.language.LanguageBasePreferenceController
    protected void handleLocaleWithChildren(LocaleStore.LocaleInfo parentLocaleInfo) {
        ChildLocalePickerFragment fragment = ChildLocalePickerFragment.newInstance(parentLocaleInfo);
        fragment.registerChildLocaleSelectedListener(new LanguageBasePreferenceController.LocaleSelectedListener() { // from class: com.android.car.settings.language.-$$Lambda$LanguagePickerPreferenceController$LOtotuHSQnAAkmX4_kj6yFuJ9cE
            @Override // com.android.car.settings.language.LanguageBasePreferenceController.LocaleSelectedListener
            public final void onLocaleSelected(LocaleStore.LocaleInfo localeInfo) {
                this.f$0.lambda$handleLocaleWithChildren$0$LanguagePickerPreferenceController(localeInfo);
            }
        });
        getFragmentController().launchFragment(fragment);
    }

    public /* synthetic */ void lambda$handleLocaleWithChildren$0$LanguagePickerPreferenceController(LocaleStore.LocaleInfo localeInfo) {
        getFragmentController().goBack();
    }

    private void maybeAddPseudoLocale(Set<LocaleStore.LocaleInfo> localeInfos) {
        if (Build.IS_USERDEBUG) {
            localeInfos.add(LocaleStore.getLocaleInfo(new Locale("ar", "XB")));
        }
    }
}
