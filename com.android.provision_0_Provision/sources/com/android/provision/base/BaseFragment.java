package com.android.provision.base;

import android.app.Fragment;
import android.app.UiModeManager;
import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class BaseFragment extends Fragment {
    protected boolean isNightMode() {
        return ((UiModeManager) getContext().getSystemService(UiModeManager.class)).getNightMode() == 2;
    }

    public static boolean isZh() {
        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        BydLogHelper.logW("BaseFragment", "language:" + language + ",country:" + country);
        return !TextUtils.isEmpty(language) && language.endsWith("zh") && !TextUtils.isEmpty(country) && country.endsWith("CN");
    }
}
