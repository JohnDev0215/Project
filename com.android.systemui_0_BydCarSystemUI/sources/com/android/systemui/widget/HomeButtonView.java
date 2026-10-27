package com.android.systemui.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public class HomeButtonView extends KeyButtonView {
    @Override // com.android.systemui.widget.KeyButtonView
    public int getImageResId() {
        return R.mipmap.sys_nav_bar_home;
    }

    public HomeButtonView(Context context) {
        super(context);
    }

    public HomeButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
