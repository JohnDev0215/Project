package com.byd.music.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes5.dex */
public class ChoiceView extends LinearLayout {
    public void setData(String[] strArr) {
    }

    public ChoiceView(Context context) {
        this(context, null);
    }

    public ChoiceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChoiceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOrientation(1);
    }
}
