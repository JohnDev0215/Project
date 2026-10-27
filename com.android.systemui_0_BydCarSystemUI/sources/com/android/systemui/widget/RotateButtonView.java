package com.android.systemui.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public class RotateButtonView extends KeyButtonView {
    @Override // com.android.systemui.widget.KeyButtonView
    public int getImageResId() {
        return R.mipmap.sys_nav_bar_rotate;
    }

    public RotateButtonView(Context context) {
        super(context);
    }

    public RotateButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RotateButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
