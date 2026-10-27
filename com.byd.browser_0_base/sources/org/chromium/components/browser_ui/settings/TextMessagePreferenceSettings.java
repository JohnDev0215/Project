package org.chromium.components.browser_ui.settings;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import defpackage.n32;

/* JADX INFO: loaded from: classes2.dex */
public class TextMessagePreferenceSettings extends TextMessagePreference {
    public final int a0;
    public final int b0;
    public final int c0;
    public final int d0;
    public final int e0;
    public final int f0;
    public final int g0;
    public final int h0;
    public final int i0;

    public TextMessagePreferenceSettings(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.TextMessagePreferenceSettings);
        this.g0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.TextMessagePreferenceSettings_android_paddingTop, 0);
        this.h0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.TextMessagePreferenceSettings_android_paddingBottom, 0);
        this.a0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.TextMessagePreferenceSettings_android_paddingStart, 0);
        this.b0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.TextMessagePreferenceSettings_android_paddingEnd, 0);
        this.i0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.TextMessagePreferenceSettings_android_minHeight, context.getResources().getDimensionPixelSize(R$dimen.settings_preference_min_height));
        this.f0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.TextMessagePreferenceSettings_titleTextSize, context.getResources().getDimensionPixelSize(R$dimen.preference_title_text_size));
        this.e0 = typedArrayObtainStyledAttributes.getColor(R$styleable.TextMessagePreferenceSettings_titleTextColor, context.getColor(R$color.settings_preference_title_text_color));
        this.c0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.TextMessagePreferenceSettings_summaryTextSize, context.getResources().getDimensionPixelSize(R$dimen.preference_title_summary_size));
        this.d0 = typedArrayObtainStyledAttributes.getColor(R$styleable.TextMessagePreferenceSettings_summaryTextColor, context.getColor(R$color.settings_preference_summary_text_color));
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // org.chromium.components.browser_ui.settings.TextMessagePreference, org.chromium.components.browser_ui.settings.ChromeBasePreference, androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        View view = n32Var.e;
        if (view != null) {
            view.setPadding(this.a0, this.g0, this.b0, this.h0);
            view.setMinimumHeight(this.i0);
        }
        TextView textView = (TextView) n32Var.u(R.id.title);
        if (textView != null) {
            textView.setTextSize(0, this.f0);
            textView.setTextColor(this.e0);
        }
        TextView textView2 = (TextView) n32Var.u(R.id.summary);
        if (textView2 != null) {
            textView2.setTextSize(0, this.c0);
            textView2.setTextColor(this.d0);
        }
    }
}
