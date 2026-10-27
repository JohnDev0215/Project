package org.chromium.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import defpackage.AbstractC0678dn;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class TextViewWithLeading extends TextView {
    public TextViewWithLeading(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0678dn.F0, 0, 0);
        if (typedArrayObtainStyledAttributes.hasValue(0)) {
            setLineSpacing(typedArrayObtainStyledAttributes.getDimension(0, 0.0f) - getPaint().getFontMetrics(null), 1.0f);
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}
