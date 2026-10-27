package org.chromium.chrome.browser.privacy_sandbox.v4;

import android.R;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import androidx.preference.PreferenceCategory;
import defpackage.n32;
import org.chromium.chrome.R$layout;
import org.chromium.ui.widget.TextViewWithClickableSpans;

/* JADX INFO: loaded from: classes2.dex */
public class PreferenceCategoryWithClickableSummary extends PreferenceCategory {
    public PreferenceCategoryWithClickableSummary(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f17J = R$layout.category_with_clickable_summary_preference;
    }

    @Override // androidx.preference.PreferenceCategory, androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        ((TextViewWithClickableSpans) n32Var.u(R.id.summary)).setMovementMethod(LinkMovementMethod.getInstance());
    }
}
