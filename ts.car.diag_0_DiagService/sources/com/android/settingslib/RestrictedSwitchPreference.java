package com.android.settingslib;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.preference.SwitchPreference;
import androidx.preference.f;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class RestrictedSwitchPreference extends SwitchPreference {
    b X;
    boolean Y;
    CharSequence Z;
    private int a0;

    public RestrictedSwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.Y = false;
        y0(R$layout.restricted_switch_widget);
        this.X = new b(context, this, attributeSet);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RestrictedSwitchPreference);
            TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(R$styleable.RestrictedSwitchPreference_useAdditionalSummary);
            if (typedValuePeekValue != null) {
                this.Y = typedValuePeekValue.type == 18 && typedValuePeekValue.data != 0;
            }
            TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(R$styleable.RestrictedSwitchPreference_restrictedSwitchSummary);
            if (typedValuePeekValue2 != null && typedValuePeekValue2.type == 3) {
                int i3 = typedValuePeekValue2.resourceId;
                if (i3 != 0) {
                    this.Z = context.getText(i3);
                } else {
                    this.Z = typedValuePeekValue2.string;
                }
            }
        }
        if (this.Y) {
            m0(R$layout.restricted_switch_preference);
            Q0(false);
        }
    }

    @Override // androidx.preference.SwitchPreference, androidx.preference.Preference
    public void M(f fVar) {
        super.M(fVar);
        this.X.b(fVar);
        CharSequence text = this.Z;
        if (text == null) {
            text = j().getText(E0() ? R$string.enabled_by_admin : R$string.disabled_by_admin);
        }
        View viewM = fVar.M(R$id.restricted_icon);
        View viewM2 = fVar.M(R.id.switch_widget);
        if (viewM != null) {
            viewM.setVisibility(P0() ? 0 : 8);
        }
        if (viewM2 != null) {
            viewM2.setVisibility(P0() ? 8 : 0);
        }
        ImageView imageView = (ImageView) fVar.f1092a.findViewById(R.id.icon);
        if (this.a0 > 0) {
            int i = this.a0;
            imageView.setLayoutParams(new LinearLayout.LayoutParams(i, i));
        }
        if (!this.Y) {
            TextView textView = (TextView) fVar.M(R.id.summary);
            if (textView == null || !P0()) {
                return;
            }
            textView.setText(text);
            textView.setVisibility(0);
            return;
        }
        TextView textView2 = (TextView) fVar.M(R$id.additional_summary);
        if (textView2 != null) {
            if (!P0()) {
                textView2.setVisibility(8);
            } else {
                textView2.setText(text);
                textView2.setVisibility(0);
            }
        }
    }

    public boolean P0() {
        return this.X.a();
    }

    public void Q0(boolean z) {
        this.X.d(z);
    }

    @Override // androidx.preference.Preference
    public void V() {
        if (this.X.c()) {
            return;
        }
        super.V();
    }

    public RestrictedSwitchPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public RestrictedSwitchPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, androidx.core.content.res.f.a(context, R$attr.switchPreferenceStyle, R.attr.switchPreferenceStyle));
    }

    public RestrictedSwitchPreference(Context context) {
        this(context, null);
    }
}
