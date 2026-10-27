package org.chromium.components.browser_ui.settings;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.PreferenceCategory;
import defpackage.n32;

/* JADX INFO: loaded from: classes2.dex */
public class ChromeBasicsSectionCategoryPreference extends PreferenceCategory {
    public ChromeBasicsSectionCategoryPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, 0);
        this.f17J = R$layout.preference_category_chrome_setting;
    }

    @Override // androidx.preference.PreferenceCategory, androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        n32Var.B = true;
        n32Var.C = false;
    }

    public ChromeBasicsSectionCategoryPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ChromeBasicsSectionCategoryPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChromeBasicsSectionCategoryPreference(Context context) {
        this(context, null);
    }
}
