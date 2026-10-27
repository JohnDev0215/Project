package com.byd.mvp.ui.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

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

    public LengthMaxTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public LengthMaxTextView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        a(context);
    }
}
