package org.chromium.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import org.chromium.ui.R;

/* JADX INFO: loaded from: classes4.dex */
public class TextViewWithLeading extends TextView {
    public TextViewWithLeading(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TextViewWithLeading, 0, 0);
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.TextViewWithLeading_leading)) {
            setLineSpacing(typedArrayObtainStyledAttributes.getDimension(R.styleable.TextViewWithLeading_leading, 0.0f) - getPaint().getFontMetrics(null), 1.0f);
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}
