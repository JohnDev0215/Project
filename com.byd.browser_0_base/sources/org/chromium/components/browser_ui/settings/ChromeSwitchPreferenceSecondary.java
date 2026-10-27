package org.chromium.components.browser_ui.settings;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import defpackage.n32;

/* JADX INFO: loaded from: classes2.dex */
public class ChromeSwitchPreferenceSecondary extends ChromeSwitchPreference {
    public int e0;
    public int f0;
    public final int g0;
    public final int h0;
    public final int i0;
    public final int j0;

    public ChromeSwitchPreferenceSecondary(Context context) {
        this(context, null);
    }

    @Override // org.chromium.components.browser_ui.settings.ChromeSwitchPreference, androidx.preference.SwitchPreferenceCompat, androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        Context context = this.e;
        if (context == null || context.getResources() == null) {
            return;
        }
        View view = n32Var.e;
        if (view != null) {
            view.setPadding(this.e0, 0, this.f0, 0);
        }
        TextView textView = (TextView) n32Var.u(R.id.title);
        if (textView != null) {
            textView.setTextSize(0, this.g0);
            textView.setTextColor(this.h0);
        }
        TextView textView2 = (TextView) n32Var.u(R.id.summary);
        if (textView2 != null) {
            textView2.setTextSize(0, this.i0);
            textView2.setTextColor(this.j0);
        }
    }

    public ChromeSwitchPreferenceSecondary(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ChromeSwitchPreferenceSecondary);
        this.e0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ChromeSwitchPreferenceSecondary_android_paddingStart, 0);
        this.f0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ChromeSwitchPreferenceSecondary_android_paddingEnd, 0);
        this.g0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ChromeSwitchPreferenceSecondary_titleTextSize, context.getResources().getDimensionPixelSize(R$dimen.preference_title_text_size));
        this.h0 = typedArrayObtainStyledAttributes.getColor(R$styleable.ChromeSwitchPreferenceSecondary_titleTextColor, context.getColor(R$color.settings_preference_title_text_color));
        this.i0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ChromeSwitchPreferenceSecondary_summaryTextSize, context.getResources().getDimensionPixelSize(R$dimen.preference_title_summary_size));
        this.j0 = typedArrayObtainStyledAttributes.getColor(R$styleable.ChromeSwitchPreferenceSecondary_summaryTextColor, context.getColor(R$color.settings_preference_summary_text_color));
        typedArrayObtainStyledAttributes.recycle();
    }
}
