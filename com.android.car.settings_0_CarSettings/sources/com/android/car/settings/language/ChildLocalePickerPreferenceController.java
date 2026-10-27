package com.android.car.settings.language;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import com.android.car.settings.common.FragmentController;
import com.android.internal.app.LocaleStore;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class ChildLocalePickerPreferenceController extends LanguageBasePreferenceController {
    private LocaleStore.LocaleInfo mParentLocaleInfo;

    public ChildLocalePickerPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    public void setParentLocaleInfo(LocaleStore.LocaleInfo parentLocaleInfo) {
        this.mParentLocaleInfo = parentLocaleInfo;
    }

    @Override // com.android.car.settings.language.LanguageBasePreferenceController
    protected LocalePreferenceProvider defineLocaleProvider() {
        Set<LocaleStore.LocaleInfo> mLocaleInfoSet = LocaleStore.getLevelLocales(getContext(), getExclusionSet(), this.mParentLocaleInfo, true);
        return LocalePreferenceProvider.newInstance(getContext(), mLocaleInfoSet, this.mParentLocaleInfo);
    }
}
