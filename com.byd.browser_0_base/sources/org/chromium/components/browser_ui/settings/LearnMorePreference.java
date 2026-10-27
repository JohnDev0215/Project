package org.chromium.components.browser_ui.settings;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.preference.Preference;
import defpackage.c6;
import defpackage.gs;
import defpackage.n32;

/* JADX INFO: loaded from: classes2.dex */
public class LearnMorePreference extends Preference {
    public LearnMorePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        S(R$string.learn_more);
        O();
        P();
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        TextView textView = (TextView) n32Var.u(R.id.title);
        c6.f(textView, R$style.TextAppearance_TextLarge_LearnMore_Preference);
        textView.setClickable(true);
        textView.setOnClickListener(new gs(16, this));
    }
}
