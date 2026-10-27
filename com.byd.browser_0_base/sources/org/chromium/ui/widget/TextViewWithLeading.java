package org.chromium.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import org.chromium.ui.R$styleable;

/* JADX INFO: loaded from: classes3.dex */
public class TextViewWithLeading extends TextView {
    public TextViewWithLeading(Context context) {
        super(context);
    }

    public TextViewWithLeading(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.TextViewWithLeading, 0, 0);
        int i = R$styleable.TextViewWithLeading_leading;
        if (typedArrayObtainStyledAttributes.hasValue(i)) {
            setLineSpacing(typedArrayObtainStyledAttributes.getDimension(i, 0.0f) - getPaint().getFontMetrics(null), 1.0f);
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}
