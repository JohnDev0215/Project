package com.android.settingslib.widget;

import android.R;
import android.content.Context;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.core.content.res.f;
import androidx.preference.Preference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class FooterPreference extends Preference {
    public FooterPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, f.a(context, com.android.settingslib.R$attr.footerPreferenceStyle, R.attr.preferenceStyle));
        E0();
    }

    private void E0() {
        if (m() == null) {
            h0(com.android.settingslib.R$drawable.ic_info_outline_24);
        }
        q0(2147483646);
        if (TextUtils.isEmpty(p())) {
            l0("footer_preference");
        }
    }

    @Override // androidx.preference.Preference
    public void M(androidx.preference.f fVar) {
        super.M(fVar);
        TextView textView = (TextView) fVar.f1092a.findViewById(R.id.title);
        textView.setMovementMethod(new LinkMovementMethod());
        textView.setClickable(false);
        textView.setLongClickable(false);
    }

    @Override // androidx.preference.Preference
    public void u0(CharSequence charSequence) {
        x0(charSequence);
    }

    @Override // androidx.preference.Preference
    public CharSequence y() {
        return A();
    }
}
