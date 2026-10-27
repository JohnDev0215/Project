package com.android.systemui.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public class SplitButtonView extends KeyButtonView {
    @Override // com.android.systemui.widget.KeyButtonView
    public int getImageResId() {
        return R.mipmap.sys_nav_bar_split_screen;
    }

    public SplitButtonView(Context context) {
        super(context);
    }

    public SplitButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SplitButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
