package com.android.systemui.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.android.systemui.R;
import com.android.systemui.util.StyleUtil;

/* JADX INFO: loaded from: classes.dex */
public class CarButtonView extends KeyButtonView {
    public CarButtonView(Context context) {
        super(context);
    }

    public CarButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CarButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // com.android.systemui.widget.KeyButtonView
    public int getImageResId() {
        return StyleUtil.isDenza() ? R.mipmap.denza_sys_nav_bar_car : R.mipmap.sys_nav_bar_car;
    }
}
