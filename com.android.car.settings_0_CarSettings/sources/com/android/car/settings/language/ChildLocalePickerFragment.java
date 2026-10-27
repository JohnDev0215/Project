package com.android.car.settings.language;

import android.content.Context;
import android.os.Bundle;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.language.LanguageBasePreferenceController;
import com.android.internal.app.LocaleStore;
import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
public class ChildLocalePickerFragment extends SettingsFragment {
    private LanguageBasePreferenceController.LocaleSelectedListener mLocaleSelectedListener;
    private LocaleStore.LocaleInfo mParentLocaleInfo;

    public static ChildLocalePickerFragment newInstance(LocaleStore.LocaleInfo parentLocaleInfo) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(LocaleUtil.LOCALE_BUNDLE_KEY, parentLocaleInfo.getLocale());
        ChildLocalePickerFragment fragment = new ChildLocalePickerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void registerChildLocaleSelectedListener(LanguageBasePreferenceController.LocaleSelectedListener listener) {
        this.mLocaleSelectedListener = listener;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        Locale locale = (Locale) getArguments().getSerializable(LocaleUtil.LOCALE_BUNDLE_KEY);
        this.mParentLocaleInfo = LocaleStore.getLocaleInfo(locale);
        ((ChildLocalePickerPreferenceController) use(ChildLocalePickerPreferenceController.class, R.string.pk_child_locale_picker)).setParentLocaleInfo(this.mParentLocaleInfo);
        ((ChildLocalePickerPreferenceController) use(ChildLocalePickerPreferenceController.class, R.string.pk_child_locale_picker)).setLocaleSelectedListener(this.mLocaleSelectedListener);
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getToolbar().setTitle(this.mParentLocaleInfo.getFullNameNative());
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.child_locale_picker_fragment;
    }
}
