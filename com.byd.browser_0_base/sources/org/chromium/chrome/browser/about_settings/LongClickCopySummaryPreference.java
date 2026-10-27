package org.chromium.chrome.browser.about_settings;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.Preference;
import defpackage.la1;
import defpackage.n32;

/* JADX INFO: loaded from: classes.dex */
public class LongClickCopySummaryPreference extends Preference {
    public LongClickCopySummaryPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.preference.Preference
    public void y(n32 n32Var) {
        super.y(n32Var);
        n32Var.e.setOnLongClickListener(new la1(0, this));
    }
}
