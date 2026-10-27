package org.chromium.components.browser_ui.settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;
import androidx.preference.Preference;
import defpackage.jp2;
import defpackage.n32;
import defpackage.vt;

/* JADX INFO: loaded from: classes2.dex */
public class ButtonPreference extends Preference {
    public ButtonPreference(Context context, AttributeSet attributeSet) {
        int iIntValue;
        super(context, attributeSet);
        this.f17J = R$layout.button_preference_layout;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ButtonPreference);
        try {
            iIntValue = ((Integer) jp2.a.getMethod("getPreferenceButtonLayoutRes", new Class[0]).invoke(null, new Object[0])).intValue();
        } catch (Exception unused) {
            iIntValue = R$layout.button_preference_button;
        }
        this.K = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ButtonPreference_buttonLayout, iIntValue);
        typedArrayObtainStyledAttributes.recycle();
        O();
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        Button button = (Button) n32Var.u(R$id.button_preference);
        button.setText(this.l);
        button.setOnClickListener(new vt(26, this));
    }
}
