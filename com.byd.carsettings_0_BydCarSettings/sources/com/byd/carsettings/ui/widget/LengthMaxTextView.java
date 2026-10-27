package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class LengthMaxTextView extends TextView {
    public LengthMaxTextView(Context context) {
        super(context);
        a(context);
    }

    public final void a(Context context) {
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.MIDDLE);
    }

    public LengthMaxTextView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public LengthMaxTextView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }
}
