package org.chromium.components.browser_ui.settings;

import android.content.Context;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import defpackage.n32;
import org.chromium.ui.widget.TextViewWithClickableSpans;

/* JADX INFO: loaded from: classes2.dex */
public class ClickableSpansTextMessagePreference extends ChromeBasePreference {
    public CharSequence Y;
    public CharSequence Z;
    public TextViewWithClickableSpans a0;
    public TextViewWithClickableSpans b0;

    public ClickableSpansTextMessagePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f17J = R$layout.clickable_spans_text_message_preference_layout;
        O();
        P();
    }

    @Override // androidx.preference.Preference
    public final void Q(int i) {
        R(this.e.getString(i));
    }

    @Override // androidx.preference.Preference
    public final void R(CharSequence charSequence) {
        if (TextUtils.equals(this.Z, charSequence)) {
            return;
        }
        this.Z = charSequence;
        s();
    }

    @Override // androidx.preference.Preference
    public final void S(int i) {
        T(this.e.getString(i));
    }

    @Override // androidx.preference.Preference
    public final void T(CharSequence charSequence) {
        if (TextUtils.equals(this.Y, charSequence)) {
            return;
        }
        this.Y = charSequence;
        s();
    }

    @Override // org.chromium.components.browser_ui.settings.ChromeBasePreference, androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        this.a0 = (TextViewWithClickableSpans) n32Var.u(R$id.title);
        this.b0 = (TextViewWithClickableSpans) n32Var.u(R$id.summary);
        if (TextUtils.isEmpty(this.Y)) {
            this.a0.setVisibility(8);
        } else {
            this.a0.setText(this.Y);
            this.a0.setVisibility(0);
        }
        if (TextUtils.isEmpty(this.Z)) {
            this.b0.setVisibility(8);
            return;
        }
        this.b0.setText(this.Z);
        this.b0.setVisibility(0);
        this.b0.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
