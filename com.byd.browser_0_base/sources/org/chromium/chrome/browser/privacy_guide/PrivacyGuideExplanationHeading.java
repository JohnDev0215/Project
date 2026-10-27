package org.chromium.chrome.browser.privacy_guide;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$styleable;

/* JADX INFO: loaded from: classes2.dex */
public class PrivacyGuideExplanationHeading extends LinearLayout {
    public PrivacyGuideExplanationHeading(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.privacy_guide_explanation_heading, this);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PrivacyGuideExplanationHeading, 0, 0);
        ((TextView) viewInflate.findViewById(R$id.title)).setText(typedArrayObtainStyledAttributes.getText(R$styleable.PrivacyGuideExplanationHeading_titleText));
        typedArrayObtainStyledAttributes.recycle();
    }
}
