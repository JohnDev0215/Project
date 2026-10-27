package org.chromium.components.browser_ui.settings;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.preference.SwitchPreferenceCompat;
import defpackage.bz;
import defpackage.mb1;
import defpackage.n32;
import defpackage.nb1;
import defpackage.ya0;

/* JADX INFO: loaded from: classes2.dex */
public class ChromeSwitchPreference extends SwitchPreferenceCompat {
    public mb1 a0;
    public View b0;
    public Integer c0;
    public final boolean d0;

    public ChromeSwitchPreference(Context context) {
        this(context, null);
    }

    public final void b0(mb1 mb1Var) {
        this.a0 = mb1Var;
        nb1.c(mb1Var, this, true, this.d0);
    }

    public final void c0() {
        Integer num;
        View view = this.b0;
        if (view == null || (num = this.c0) == null) {
            return;
        }
        view.setBackgroundColor(bz.b(this.e, num.intValue()).getDefaultColor());
    }

    @Override // androidx.preference.SwitchPreferenceCompat, androidx.preference.Preference
    public void y(n32 n32Var) {
        super.y(n32Var);
        TextView textView = (TextView) n32Var.u(R.id.title);
        textView.setSingleLine(false);
        TextView textView2 = (TextView) n32Var.u(R.id.summary);
        if (TextUtils.isEmpty(this.l)) {
            textView.setText(textView2.getText());
            textView.setVisibility(0);
            textView2.setVisibility(8);
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) textView2.getLayoutParams();
        marginLayoutParams.setMarginEnd((int) ((14 * ya0.a(this.e).d) + 0.5f));
        textView2.setLayoutParams(marginLayoutParams);
        View view = n32Var.e;
        this.b0 = view;
        c0();
        nb1.d(this.a0, this, view);
    }

    @Override // androidx.preference.TwoStatePreference, androidx.preference.Preference
    public void z() {
        if (nb1.e(this.a0, this)) {
            return;
        }
        super.z();
    }

    public ChromeSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Preference);
        this.d0 = (typedArrayObtainStyledAttributes.getResourceId(R$styleable.Preference_android_layout, 0) == 0 && typedArrayObtainStyledAttributes.getResourceId(R$styleable.Preference_layout, 0) == 0) ? false : true;
    }
}
