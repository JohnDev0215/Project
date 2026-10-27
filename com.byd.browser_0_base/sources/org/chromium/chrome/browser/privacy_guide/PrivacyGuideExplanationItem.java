package org.chromium.chrome.browser.privacy_guide;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$styleable;

/* JADX INFO: loaded from: classes2.dex */
public class PrivacyGuideExplanationItem extends LinearLayout {
    public PrivacyGuideExplanationItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.privacy_guide_explanation_item, this);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PrivacyGuideExplanationItem, 0, 0);
        TextView textView = (TextView) viewInflate.findViewById(R$id.summary);
        textView.setText(typedArrayObtainStyledAttributes.getText(R$styleable.PrivacyGuideExplanationItem_summaryText));
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.PrivacyGuideExplanationItem_iconImage);
        ColorStateList colorStateList = typedArrayObtainStyledAttributes.getColorStateList(R$styleable.PrivacyGuideExplanationItem_iconTint);
        if (drawable != null && colorStateList != null) {
            drawable.setColorFilter(colorStateList.getDefaultColor(), PorterDuff.Mode.SRC_IN);
        }
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        typedArrayObtainStyledAttributes.recycle();
    }
}
