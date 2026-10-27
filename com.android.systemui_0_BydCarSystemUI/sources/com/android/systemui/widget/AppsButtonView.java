package com.android.systemui.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public class AppsButtonView extends KeyButtonView {
    @Override // com.android.systemui.widget.KeyButtonView
    public int getImageResId() {
        return R.mipmap.sys_nav_bar_apps;
    }

    public AppsButtonView(Context context) {
        super(context);
    }

    public AppsButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AppsButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
